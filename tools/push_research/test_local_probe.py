#!/usr/bin/env python3
"""Isolated deterministic tests: no network, Windows secrets or mail data."""
from __future__ import annotations

from contextlib import redirect_stdout
import io
import json
from pathlib import Path
import struct
import tempfile
import unittest
from unittest import mock
import urllib.parse

import google_sender_probe as google
import local_mailru_event_probe as probe


class FakeResponse:
    def __init__(self, value):
        self.value = value
    def __enter__(self):
        return self
    def __exit__(self, *_):
        pass
    def read(self, *_):
        return self.value


class FakeSocket:
    def __init__(self, payload):
        self.payload = bytearray(payload)
        self.sent = []
    def settimeout(self, *_):
        pass
    def recv(self, n):
        if not self.payload:
            return b""
        part = bytes(self.payload[:n])
        del self.payload[:n]
        return part
    def sendall(self, data):
        self.sent.append(data)


class LocalProbeTests(unittest.TestCase):
    def test_sender_registration_matches_original_apk(self):
        original_id = 987654321
        credential = 111222333
        checkin = google.pb_int(1, 1)
        checkin += google.varint((7 << 3) | 1) + struct.pack("<Q", original_id)
        checkin += google.varint((8 << 3) | 1) + struct.pack("<Q", credential)
        calls = []
        def send(url, data, headers, **kwargs):
            calls.append((url, data, headers))
            if url == probe.CHECKIN_URL:
                return checkin
            if url == probe.REGISTER_URL:
                return b"token=TEST_TOKEN_ONLY_123456789"
            raise AssertionError("Unexpected URL")
        with mock.patch.object(probe, "post", side_effect=send):
            with mock.patch.object(probe.time, "sleep"):
                with redirect_stdout(io.StringIO()) as capture:
                    android_id, secret, token = probe.own_google_identity()
        self.assertEqual(android_id, original_id)
        self.assertEqual(secret, credential)
        self.assertEqual(token, "TEST_TOKEN_ONLY_123456789")
        self.assertEqual(len(calls), 2)
        fields = urllib.parse.parse_qs(calls[1][1].decode())
        self.assertEqual(fields["sender"], [probe.SENDER_ID])
        self.assertEqual(fields["cert"], [probe.PUBLIC_APK_CERT_SHA1])
        self.assertEqual(fields["app"], ["ru.mail.mailapp"])
        self.assertEqual(
            calls[1][2]["Authorization"], f"AidLogin {original_id}:{credential}"
        )
        self.assertNotIn(token, capture.getvalue())

    def test_account_subscription_request_and_classified_reply(self):
        seen = []
        def send(request, **kwargs):
            seen.append(request)
            return FakeResponse(b'{"error":{"code":0},"validate_result":[{"account":"probe@example.invalid","is_valid":true}]}')
        with mock.patch.object(probe.urllib.request, "urlopen", side_effect=send):
            with redirect_stdout(io.StringIO()) as capture:
                result = probe.subscribe_mailru(
                    "TEST_OAUTH_SECRET", "probe@example.invalid",
                    "TEST_GOOGLE_SECRET", 123)
        self.assertEqual(result, "ACCOUNT_ACCEPTED")
        self.assertEqual(len(seen), 1)
        request = seen[0]
        self.assertEqual(request.full_url, "https://alt-push-me.mail.ru/api/v2/set_settings")
        self.assertEqual(request.get_method(), "POST")
        data = json.loads(request.data)
        self.assertEqual(len(data), 1)
        self.assertEqual(data[0]["account"], "probe@example.invalid")
        self.assertEqual(data[0]["token"], "TEST_GOOGLE_SECRET")
        self.assertEqual(data[0]["access_token"], "TEST_OAUTH_SECRET")
        self.assertEqual(data[0]["platform"], "android")
        self.assertEqual(data[0]["application"], "mail")
        self.assertNotIn("TEST_OAUTH_SECRET", capture.getvalue())
        self.assertNotIn("TEST_GOOGLE_SECRET", capture.getvalue())
        self.assertNotIn("probe@example.invalid", capture.getvalue())

    def test_unsubscribe_only_trial_token(self):
        seen = []
        def send(request, **kwargs):
            seen.append(request)
            return FakeResponse(b'{"error":{"code":0}}')
        with mock.patch.object(probe.urllib.request, "urlopen", side_effect=send):
            with redirect_stdout(io.StringIO()) as output:
                probe.remove_only_own_token("ONLY_NEW_TOKEN")
        self.assertEqual(len(seen), 1)
        self.assertEqual(seen[0].full_url,
                         "https://alt-push-me.mail.ru/api/v2/unsubscribe_by_token")
        args = urllib.parse.parse_qs(seen[0].data.decode())
        self.assertEqual(args, {"token": ["ONLY_NEW_TOKEN"], "application": ["mail"]})
        self.assertNotIn("ONLY_NEW_TOKEN", output.getvalue())
        self.assertIn("cleanup=CONFIRMED", output.getvalue())

    def test_mcs_event_type_four_is_detected_without_message_details(self):
        app_data = google.pb_bytes(1, b"event") + google.pb_bytes(2, b"4")
        sender_data = google.pb_bytes(1, b"sender") + google.pb_bytes(2, b"SECRET_SENDER")
        body = google.pb_bytes(5, b"ru.mail.mailapp") + google.pb_bytes(7, app_data) + google.pb_bytes(7, sender_data)
        frame = bytes((8,)) + google.varint(len(body)) + body
        transport = FakeSocket(frame)
        with redirect_stdout(io.StringIO()) as output:
            result = probe.mcs_receive(transport, 30)
        self.assertTrue(result)
        self.assertIn("MAILRU_NEW_MAIL_EVENT_RECEIVED=YES", output.getvalue())
        self.assertNotIn("SECRET_SENDER", output.getvalue())

    def test_mcs_login_response_is_required(self):
        result = google.pb_bytes(1, b"mock-login-response")
        data = bytes((41, 3)) + google.varint(len(result)) + result
        sock = FakeSocket(data)
        with mock.patch.object(probe.socket, "create_connection", return_value="FAKE_TCP"):
            with mock.patch.object(probe.ssl, "create_default_context") as ctx:
                ctx.return_value.wrap_socket.return_value = sock
                with redirect_stdout(io.StringIO()) as output:
                    returned = probe.mcs_start(789, 456)
        self.assertIs(returned, sock)
        self.assertEqual(sock.sent[0][:2], bytes((41, 2)))
        self.assertIn("google_mcs_login=OK", output.getvalue())

    @unittest.skipUnless(__import__("os").name == "nt", "Windows-only DPAPI integration")
    def test_real_windows_dpapi_round_trip_with_fake_data(self):
        import ctypes
        from ctypes import wintypes
        class DataBlob(ctypes.Structure):
            _fields_ = [("cbData", wintypes.DWORD),
                        ("pbData", ctypes.POINTER(ctypes.c_ubyte))]
        raw = b"FAKE_DPAPI_UNIT_TEST_ONLY"
        array = (ctypes.c_ubyte * len(raw)).from_buffer_copy(raw)
        source = DataBlob(len(raw), array)
        output = DataBlob()
        crypto = ctypes.windll.crypt32
        crypto.CryptProtectData.argtypes = [
            ctypes.POINTER(DataBlob), wintypes.LPCWSTR,
            ctypes.c_void_p, ctypes.c_void_p, ctypes.c_void_p,
            wintypes.DWORD, ctypes.POINTER(DataBlob)]
        crypto.CryptProtectData.restype = wintypes.BOOL
        self.assertTrue(crypto.CryptProtectData(
            ctypes.byref(source), "MailRu Desktop authorization",
            None, None, None, 0x1, ctypes.byref(output)))
        try:
            encrypted = ctypes.string_at(output.pbData, output.cbData)
        finally:
            ctypes.windll.kernel32.LocalFree.argtypes = [ctypes.c_void_p]
            ctypes.windll.kernel32.LocalFree.restype = ctypes.c_void_p
            ctypes.windll.kernel32.LocalFree(
                ctypes.cast(output.pbData, ctypes.c_void_p))
        import base64
        self.assertEqual(
            probe.decrypt_windows_dpapi(base64.b64encode(encrypted).decode()),
            raw.decode())

    def test_stored_account_authentication_uses_dpapi_not_plaintext(self):
        with tempfile.TemporaryDirectory() as folder:
            filename = Path(folder) / "auth.json"
            filename.write_text(json.dumps({"Accounts": [
                {"Login": "probe@example.invalid", "AccessToken": "PROTECTED_TEXT"}
            ]}), encoding="utf-8")
            with mock.patch.object(probe, "decrypt_windows_dpapi",
                                   return_value="IN_MEMORY_OAUTH") as decrypt:
                received = probe.find_saved_oauth("PROBE@example.invalid", filename)
            self.assertEqual(received, "IN_MEMORY_OAUTH")
            decrypt.assert_called_once_with("PROTECTED_TEXT")

    def test_temporary_subscription_is_removed_after_server_rejection(self):
        from types import SimpleNamespace
        class FakeSession:
            def __enter__(self):
                return self
            def __exit__(self, *_):
                pass

        with mock.patch.object(probe, "os", SimpleNamespace(name="nt")):
            with mock.patch.object(probe, "find_saved_oauth", return_value="FAKE_OAUTH"):
                with mock.patch.object(probe, "own_google_identity",
                                       return_value=(123, 456, "NEW_TOKEN_ONLY")):
                    with mock.patch.object(probe, "mcs_start", return_value=FakeSession()):
                        with mock.patch.object(probe, "subscribe_mailru",
                                               return_value="ACCOUNT_REJECTED"):
                            with mock.patch.object(probe, "remove_only_own_token") as cleanup:
                                with mock.patch.object(probe.time, "sleep"):
                                    with redirect_stdout(io.StringIO()):
                                        with self.assertRaises(SystemExit):
                                            probe.run(True, True, "probe@example.invalid", 60)
        cleanup.assert_called_once_with("NEW_TOKEN_ONLY")

    def test_local_flow_does_not_auto_send_to_mailru(self):
        with mock.patch.object(probe, "own_google_identity") as google_identity:
            with mock.patch.object(probe, "subscribe_mailru") as mailru:
                probe.offline_test()
        google_identity.assert_not_called()
        mailru.assert_not_called()


if __name__ == "__main__":
    unittest.main(verbosity=2)
