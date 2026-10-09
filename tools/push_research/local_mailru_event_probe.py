#!/usr/bin/env python3
"""Windows-only opt-in end-to-end Mail.ru mobile push experiment.

Uses the official Android APK's sender and public certificate fingerprint,
reuses the current Windows user's protected MailRuDesktop OAuth access token,
creates an independent ephemeral Google receiver, starts an authenticated TLS
MCS session, and registers that *new* receiver with Mail.ru PushMe.

IMPORTANT: --live --subscribe must be explicitly supplied on the test PC.
NEVER run this script in GitHub Actions using a real mailbox.
No token, account ID, email content, MCS payload or Google device secret
is printed, written to disk or sent to GitHub. The ONLY subscribed account
is the selected locally stored MailRuDesktop login.

The program terminates normally without unsubscribing; the temporary Google
receiver's token will no longer be usable after it exits. Mail.ru subscription
might remain on its server until expiration. No existing device is unregistered.

This is research software, not the shipping MailRuDesktop notification service.
"""
from __future__ import annotations

import argparse
import base64
import ctypes
from ctypes import wintypes
from datetime import datetime
import json
import os
from pathlib import Path
import secrets
import socket
import ssl
import sys
import time
import urllib.error
import urllib.parse
import urllib.request

from google_sender_probe import (
    APP_ID, CHECKIN_URL, REGISTER_URL, SENDER_ID,
    checkin_payload, mcs_login_payload, parse_fields,
    pb_bytes, post, recv_exact, recv_frame_length, varint,
)
from pushme_contract_probe import (
    PushMeSubscription, SERVER, V2_PATH,
    evaluate_reply, prepare_batch, original_apk_timezone_format,
)

# Public signing-certificate digest verified from the SHA-256 checked APK
# by GitHub Actions 37876843642. NEVER a key or token.
PUBLIC_APK_CERT_SHA1 = "daa4e5d1b055cdce8cdf297e412238a3476e70cf"
USER_AGENT = "mobmail android 11.13.0.29089 ru.mail.mailapp"
DEFAULT_SECONDS = 180
MAX_MESSAGE_BYTES = 65536


class AuthError(RuntimeError):
    pass


def decrypt_windows_dpapi(encoded: str) -> str:
    """Decrypt only using the current Windows user's original DPAPI context."""
    if os.name != "nt":
        raise AuthError("Windows DPAPI required")
    data = base64.b64decode(encoded, validate=True)

    class DataBlob(ctypes.Structure):
        _fields_ = [("cbData", wintypes.DWORD), ("pbData", ctypes.POINTER(ctypes.c_ubyte))]

    crypt = ctypes.windll.crypt32
    kernel = ctypes.windll.kernel32
    crypt.CryptUnprotectData.argtypes = [
        ctypes.POINTER(DataBlob), ctypes.c_void_p,
        ctypes.c_void_p, ctypes.c_void_p, ctypes.c_void_p,
        wintypes.DWORD, ctypes.POINTER(DataBlob)
    ]
    crypt.CryptUnprotectData.restype = wintypes.BOOL
    kernel.LocalFree.argtypes = [ctypes.c_void_p]
    kernel.LocalFree.restype = ctypes.c_void_p
    source = (ctypes.c_ubyte * len(data)).from_buffer_copy(data)
    input_blob = DataBlob(len(data), source)
    output_blob = DataBlob()
    if not crypt.CryptUnprotectData(
        ctypes.byref(input_blob), None, None, None, None, 0x1,
        ctypes.byref(output_blob)
    ):
        raise AuthError("DPAPI decrypt failed: must use same Windows user")
    try:
        raw = ctypes.string_at(output_blob.pbData, output_blob.cbData)
        return raw.decode("utf-8")
    finally:
        kernel.LocalFree(ctypes.cast(output_blob.pbData, ctypes.c_void_p))


def find_saved_oauth(login: str, auth_file: Path | None = None) -> str:
    if auth_file is None:
        home = os.environ.get("LOCALAPPDATA")
        if not home:
            raise AuthError("LOCALAPPDATA not found")
        auth_file = Path(home) / "MailRuDesktop" / "auth.json"
    if not auth_file.is_file():
        raise AuthError("Existing MailRuDesktop auth.json was not found")
    state = json.loads(auth_file.read_text(encoding="utf-8"))
    for account in state.get("Accounts", []):
        if account.get("Login", "").casefold() == login.casefold():
            encrypted = account.get("AccessToken")
            if not encrypted:
                raise AuthError("Selected account has no mobile access token")
            return decrypt_windows_dpapi(encrypted)
    raise AuthError("Selected login is not saved in MailRuDesktop")


def own_google_identity() -> tuple[int, int, str]:
    """One independent Google check-in and one sender-specific registration."""
    response = post(
        CHECKIN_URL, checkin_payload(),
        {"Content-Type": "application/x-protobuf",
         "User-Agent": "Android-Checkin/1.0"}
    )
    fields = parse_fields(response)
    if fields.get(1, [0])[0] != 1:
        raise RuntimeError("Google check-in rejected")
    device_id = fields.get(7, [None])[0]
    security_token = fields.get(8, [None])[0]
    if not isinstance(device_id, int) or not isinstance(security_token, int):
        raise RuntimeError("Google check-in did not return device credentials")
    print("google_checkin=OK")
    time.sleep(5)  # fixed anti-DDOS interval for research calls
    request_data = {
        "app": APP_ID, "sender": SENDER_ID,
        "device": str(device_id),
        "cert": PUBLIC_APK_CERT_SHA1,
        "app_ver": "151070",
        "X-subtype": SENDER_ID,
        "X-scope": "FCM",
        "X-appid": secrets.token_urlsafe(18),
    }
    answer = post(
        REGISTER_URL,
        urllib.parse.urlencode(request_data).encode(),
        {"Authorization": "AidLogin " + str(device_id) + ":" + str(security_token),
         "Content-Type": "application/x-www-form-urlencoded",
         "User-Agent": "Android-GCM/1.5 (Windows Research)"}
    ).decode("utf-8", "replace")
    if not answer.startswith("token="):
        raise RuntimeError("Google registration rejected (body withheld)")
    token = answer[len("token="):].strip()
    if len(token) < 10:
        raise RuntimeError("Google registration response does not contain a valid token")
    print("sender_registration=TOKEN_ISSUED")
    return device_id, security_token, token


def mcs_start(android_id: int, security_token: int) -> ssl.SSLSocket:
    raw = socket.create_connection(("mtalk.google.com", 5228), timeout=18)
    tls = ssl.create_default_context().wrap_socket(raw, server_hostname="mtalk.google.com")
    try:
        tls.settimeout(18)
        login = mcs_login_payload(android_id, security_token)
        tls.sendall(bytes((41, 2)) + varint(len(login)) + login)
        version = recv_exact(tls, 1)[0]
        tag = recv_exact(tls, 1)[0]
        length = recv_frame_length(tls)
        body = recv_exact(tls, length)
        if version not in (38, 41) or tag != 3:
            raise RuntimeError("MCS login: unexpected server reply")
        parsed = parse_fields(body)
        if 1 not in parsed or 3 in parsed:
            raise RuntimeError("MCS login rejected by Google")
        print("google_mcs_login=OK")
        return tls
    except Exception:
        tls.close()
        raise


def subscribe_mailru(
    oauth: str, login: str, google_token: str, android_id: int
) -> str:
    """Exactly one server-side subscription mutation, explicit opt-in."""
    # Unique device key must not match the genuine Android phone or another
    # independent MailRuDesktop receiver.
    device_name = "mailru-windows-research-" + secrets.token_hex(12)
    record = PushMeSubscription(
        account=login,
        access_token=oauth,
        google_sender_token=google_token,
        android_id=str(android_id),
        device_id=device_name,
        sdk_device_id=device_name,
        client_time_zone=original_apk_timezone_format(),
        # Exact field names from com.vk.pushme.util.provider.impl.ClientInfoProviderImpl.
        # Hardware details below are explicitly SYNTHETIC: there is no
        # physical Android handset behind the Windows experiment.
        client={
            "name": APP_ID,
            "version": "15.107.0.148045",
            "platform": "Android 13",
            "type": "Smartphone",
            "lang": "ru_RU",
            "info": "Windows Research;0 cameras;360.0x800.0;NONE",
        },
        capabilities={},
    )
    payload = prepare_batch([record])
    req = urllib.request.Request(
        SERVER + V2_PATH,
        data=payload,
        headers={"Content-Type": "application/json; charset=utf-8",
                 "User-Agent": USER_AGENT},
        method="POST",
    )
    try:
        with urllib.request.urlopen(req, timeout=20) as reply:
            answer = reply.read(65536).decode("utf-8", errors="replace")
        result = evaluate_reply(answer, login)
    except urllib.error.HTTPError as e:
        result = "HTTP_" + str(e.code)
    # Never print the actual server JSON; it can contain account identifiers.
    print("pushme_subscription=" + result)
    return result


def remove_only_own_token(google_token: str) -> None:
    """Release only the newly created trial subscription, never another device."""
    data = urllib.parse.urlencode({
        "token": google_token, "application": "mail"
    }).encode("utf-8")
    req = urllib.request.Request(
        SERVER + "/api/v2/unsubscribe_by_token", data=data,
        headers={"Content-Type": "application/x-www-form-urlencoded",
                 "User-Agent": USER_AGENT},
        method="POST",
    )
    try:
        with urllib.request.urlopen(req, timeout=20) as reply:
            body = reply.read(65536)
        parsed = json.loads(body)
        code = parsed.get("error", {}).get("code")
        print("research_pushme_token_cleanup=" + ("CONFIRMED" if code == 0 else "NOT_CONFIRMED"))
    except (urllib.error.URLError, OSError, json.JSONDecodeError, ValueError):
        print("research_pushme_token_cleanup=NOT_CONFIRMED")


def mcs_receive(sock: ssl.SSLSocket, seconds: int) -> bool:
    """Observe only event category, never disclose message body or metadata."""
    deadline = time.monotonic() + seconds
    data_events = 0
    ping_count = 0
    while time.monotonic() < deadline:
        sock.settimeout(min(15, max(0.1, deadline - time.monotonic())))
        try:
            tag = recv_exact(sock, 1)[0]
            size = recv_frame_length(sock)
            if size > MAX_MESSAGE_BYTES:
                raise RuntimeError("unusually large Google MCS frame")
            content = recv_exact(sock, size)
        except socket.timeout:
            continue
        except (OSError, ValueError):
            print("mcs_connection_closed_or_invalid_frame")
            return False
        if tag == 0:  # HeartbeatPing -> HeartbeatAck
            ping_count += 1
            sock.sendall(bytes((1, 0)))
            continue
        if tag == 4:
            print("mcs_server_closed_stream")
            return False
        if tag == 8:
            data_events += 1
            fields = parse_fields(content)
            # DataMessageStanza.app_data = field 7, repeated AppData(key=1,value=2)
            app_data = {}
            for item in fields.get(7, []):
                if not isinstance(item, bytes):
                    continue
                pairs = parse_fields(item)
                key = pairs.get(1, [b""])[0]
                value = pairs.get(2, [b""])[0]
                if isinstance(key, bytes) and isinstance(value, bytes):
                    # Decode only the event number, no personally identifying fields.
                    if key in (b"event", b"type"):
                        app_data[key] = value
            category = fields.get(5, [b""])[0]
            if app_data.get(b"event") == b"4" and category == APP_ID.encode("ascii"):
                print("MAILRU_NEW_MAIL_EVENT_RECEIVED=YES")
                return True
            if app_data.get(b"event") == b"4":
                print("mcs_event4_received_from_non_mail_app=YES")
            print("mcs_data_message_received_event4=NO")
        # No payload and no account data are printed or persisted.
    print("MAILRU_NEW_MAIL_EVENT_RECEIVED=NO")
    print("mcs_data_messages_observed=" + str(data_events))
    print("mcs_heartbeat_requests=" + str(ping_count))
    return False


def offline_test() -> None:
    assert len(PUBLIC_APK_CERT_SHA1) == 40
    assert len(bytes.fromhex(PUBLIC_APK_CERT_SHA1)) == 20
    assert SENDER_ID == "1098335887158"
    assert DEFAULT_SECONDS > 0
    assert SERVER + V2_PATH == "https://alt-push-me.mail.ru/api/v2/set_settings"
    # Input validation offline, no Windows storage read or external traffic.
    print("local_mailru_event_probe_offline_tests=PASS")


def run(live: bool, subscribe: bool, login: str | None, seconds: int) -> None:
    if not live:
        offline_test()
        return
    if not subscribe or not login:
        raise SystemExit("Live mode requires --subscribe --login and locally stored authorization")
    if os.name != "nt":
        raise SystemExit("Live mode requires Windows, never run on a public CI runner")
    if not 30 <= seconds <= 900:
        raise SystemExit("--seconds must be 30..900")
    oauth = find_saved_oauth(login)
    # Importantly, there are NO calls into Mail.ru just to refresh OAuth, nor
    # do we request password/refresh token. If OAuth expired, fail closed.
    device_id, secret, receiver_token = own_google_identity()
    time.sleep(5)
    with mcs_start(device_id, secret) as session:
        time.sleep(5)
        try:
            result = subscribe_mailru(oauth, login, receiver_token, device_id)
            if result != "ACCOUNT_ACCEPTED":
                # SDK can treat code==0 and no validation as OK; we prefer certainty.
                raise SystemExit("No explicit validated account subscription; stopping")
            print("Send a test message to your own selected mailbox on another device.")
            print("No message subject/sender/body will be printed or persisted.")
            mcs_receive(session, seconds)
        finally:
            # Always release precisely this trial token even on timeouts and
            # interrupted delivery checks. The real phone has another token.
            time.sleep(5)
            remove_only_own_token(receiver_token)


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--self-test", action="store_true")
    parser.add_argument("--live", action="store_true")
    parser.add_argument("--subscribe", action="store_true")
    parser.add_argument("--login")
    parser.add_argument("--seconds", type=int, default=DEFAULT_SECONDS)
    args = parser.parse_args()
    if args.self_test and not args.live:
        offline_test()
    elif args.live:
        run(True, args.subscribe, args.login, args.seconds)
    else:
        parser.error("specify --self-test or --live --subscribe --login ...")


if __name__ == "__main__":
    try:
        main()
    except (AuthError, urllib.error.URLError, RuntimeError, ValueError, OSError, json.JSONDecodeError) as e:
        # Only static exception class printed: prevents server response,
        # account ID, token or any per-user detail from reaching console logs.
        print("research_probe_error=" + type(e).__name__)
        sys.exit(1)
