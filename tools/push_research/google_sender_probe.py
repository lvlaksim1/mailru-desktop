#!/usr/bin/env python3
"""One-shot, credential-free Google GCM registration experiment.

Privacy boundary: Google check-in creates ONLY this runner's temporary device ID.
No Mail.ru mailbox token, login, password, existing device token, or message is
accessed. Never log or persist Google registration tokens/credentials.

This is an EXPERIMENT, not a supported Windows FCM implementation.
"""
from __future__ import annotations

import argparse
import os
from pathlib import Path
import re
import secrets
import socket
import ssl
import struct
import subprocess
import time
import urllib.error
import urllib.parse
import urllib.request

SENDER_ID = "1098335887158"  # exact official APK R.string.push_sender_id
DEFAULT_SENDER_ID = "61247752867"  # distinct, not substituted
APP_ID = "ru.mail.mailapp"
CHECKIN_URL = "https://android.clients.google.com/checkin"
REGISTER_URL = "https://android.clients.google.com/c2dm/register3"


def varint(value: int) -> bytes:
    if value < 0:
        raise ValueError("negative varint not supported")
    result = bytearray()
    while value > 127:
        result.append((value & 0x7F) | 0x80)
        value >>= 7
    result.append(value)
    return bytes(result)


def pb_int(field: int, value: int) -> bytes:
    return varint(field << 3) + varint(value)


def pb_bytes(field: int, data: bytes) -> bytes:
    return varint((field << 3) | 2) + varint(len(data)) + data


def parse_varint(data: bytes, pos: int) -> tuple[int, int]:
    value = 0
    shift = 0
    for _ in range(10):
        if pos >= len(data):
            raise ValueError("truncated protobuf integer")
        c = data[pos]
        pos += 1
        value |= (c & 127) << shift
        if not (c & 128):
            return value, pos
        shift += 7
    raise ValueError("excessively long protobuf integer")


def parse_fields(data: bytes) -> dict[int, list[int | bytes]]:
    fields: dict[int, list[int | bytes]] = {}
    pos = 0
    while pos < len(data):
        tag, pos = parse_varint(data, pos)
        number, wire = tag >> 3, tag & 7
        if number <= 0:
            raise ValueError("bad protobuf field")
        if wire == 0:
            value, pos = parse_varint(data, pos)
        elif wire == 1:
            if pos + 8 > len(data):
                raise ValueError("truncated 64-bit field")
            value = struct.unpack("<Q", data[pos:pos+8])[0]
            pos += 8
        elif wire == 2:
            count, pos = parse_varint(data, pos)
            if count > 1_000_000 or pos + count > len(data):
                raise ValueError("bad length field")
            value = data[pos:pos+count]
            pos += count
        elif wire == 5:
            if pos + 4 > len(data):
                raise ValueError("truncated 32-bit field")
            value = struct.unpack("<I", data[pos:pos+4])[0]
            pos += 4
        else:
            raise ValueError("unsupported protobuf wire type")
        fields.setdefault(number, []).append(value)
    return fields


def checkin_payload() -> bytes:
    # Chrome-on-Windows checkin: intentionally does not claim to be an Android device.
    build = pb_int(1, 1) + pb_bytes(2, b"63.0.3234.0") + pb_int(3, 1)
    checkin = pb_int(12, 3) + pb_bytes(13, build)
    return pb_bytes(4, checkin) + pb_int(14, 3) + pb_int(22, 0)


def post(url: str, data: bytes, headers: dict[str, str], timeout: int = 30) -> bytes:
    request = urllib.request.Request(url, data=data, headers=headers, method="POST")
    with urllib.request.urlopen(request, timeout=timeout) as response:
        return response.read(1_000_000)


def find_certificate_sha1(apk: Path) -> str:
    home = Path(os.environ.get("ANDROID_HOME", "/opt/android-sdk"))
    versions = list((home / "build-tools").glob("*/apksigner"))
    if not versions:
        raise RuntimeError("Android apksigner not available")
    signer = sorted(versions, key=lambda p: p.parent.name)[-1]
    result = subprocess.run([str(signer), "verify", "--print-certs", str(apk)],
                            check=True, capture_output=True, text=True)
    match = re.search(r"certificate SHA-1 digest:\s*([0-9a-fA-F]{40})", result.stdout)
    if not match:
        raise RuntimeError("original APK certificate SHA-1 not found")
    return match.group(1).lower()


def safe_state(value: str) -> str:
    return re.sub(r"[^A-Za-z0-9_\-]", "_", value)[:64]


def emit_report(report: Path, checkin: str, gcm: str, details: str, mcs: str = "NOT_ATTEMPTED") -> None:
    report.parent.mkdir(parents=True, exist_ok=True)
    lines = [
        "# Research: original sender Google registration — single trial",
        "",
        "An independent temporary Google check-in and ONE /c2dm/register3 request.",
        "Original official APK package, public signing-certificate fingerprint and",
        "R.string.push_sender_id were used; no mailbox account, OAuth, or Mail.ru",
        "network endpoint was contacted.",
        "",
        "- Google check-in: **" + safe_state(checkin) + "**",
        "- Google sender-specific registration: **" + safe_state(gcm) + "**",
        "- Google MCS protected-channel login: **" + safe_state(mcs) + "**",
        "- Diagnostic class: " + safe_state(details),
        "",
        "## Scope and limits",
        "",
        "Temporary check-in/registration credentials were never logged or stored.",
        "MCS login, if successful, establishes an authenticated delivery-channel session,",
        "but does not prove receipt of any Mail.ru message.",
        "The existence of a registration token, even if returned, would **not**",
        "prove persistent delivery or acceptance by Mail.ru PushMe.",
        "This is not the native FirebaseInstanceId.getToken implementation:",
        "a Chrome-on-Windows check-in is a controlled approximation.",
        "No repeated calls or retry loop. Delay >=5 seconds between network calls.",
        "",
    ]
    report.write_text("\n".join(lines), encoding="utf-8")



def mcs_login_payload(android_id: int, security_token: int) -> bytes:
    """Encode the minimal MCS LoginRequest format observed in Chromium."""
    device = str(android_id)
    pieces = [
        pb_bytes(1, b"chrome-63.0.3234.0"),
        pb_bytes(2, b"mcs.android.com"),
        pb_bytes(3, device.encode()),
        pb_bytes(4, device.encode()),
        pb_bytes(5, str(security_token).encode()),
        pb_bytes(6, ("android-" + format(android_id, "x")).encode()),
        pb_bytes(8, pb_bytes(1, b"new_vc") + pb_bytes(2, b"1")),
        pb_int(12, 0),
        pb_int(14, 1),
        pb_int(16, 2),
        pb_int(17, 1),
    ]
    return b"".join(pieces)


def recv_exact(sock: ssl.SSLSocket, length: int) -> bytes:
    data = bytearray()
    while len(data) < length:
        part = sock.recv(length - len(data))
        if not part:
            raise OSError("socket closed")
        data.extend(part)
    return bytes(data)


def recv_frame_length(sock: ssl.SSLSocket) -> int:
    value = 0
    for i in range(5):
        byte = recv_exact(sock, 1)[0]
        value |= (byte & 127) << (i * 7)
        if not (byte & 128):
            if value > 65536:
                raise ValueError("MCS response too large")
            return value
    raise ValueError("MCS frame length invalid")


def probe_mcs(android_id: int, security_token: int) -> str:
    """Try one authenticated, TLS-protected MCS login. No message data retained."""
    try:
        context = ssl.create_default_context()
        with socket.create_connection(("mtalk.google.com", 5228), timeout=12) as raw:
            with context.wrap_socket(raw, server_hostname="mtalk.google.com") as conn:
                conn.settimeout(12)
                data = mcs_login_payload(android_id, security_token)
                conn.sendall(bytes((41, 2)) + varint(len(data)) + data)
                version = recv_exact(conn, 1)[0]
                tag = recv_exact(conn, 1)[0]
                length = recv_frame_length(conn)
                body = recv_exact(conn, length)
                if version not in (38, 41):
                    return "BAD_VERSION"
                if tag != 3:
                    return "NON_LOGIN_RESPONSE"
                fields = parse_fields(body)
                if 1 not in fields:
                    return "BAD_LOGIN_RESPONSE"
                if 3 in fields:
                    return "LOGIN_REJECTED"
                return "LOGIN_OK"
    except socket.timeout:
        return "NETWORK_TIMEOUT"
    except (OSError, ssl.SSLError):
        return "NETWORK_OR_TLS_ERROR"
    except ValueError:
        return "PROTOCOL_ERROR"

def self_test() -> None:
    assert varint(300) == b"\xac\x02"
    assert parse_varint(varint(300), 0) == (300, 2)
    payload = checkin_payload()
    fields = parse_fields(payload)
    assert fields[14] == [3]
    assert fields[22] == [0]
    nested = parse_fields(fields[4][0])
    assert nested[12] == [3] and 13 in nested
    assert safe_state("token=SECRET:ABC") == "token_SECRET_ABC"
    assert SENDER_ID != DEFAULT_SENDER_ID
    login = parse_fields(mcs_login_payload(123456789, 987654321))
    assert login[2] == [b"mcs.android.com"]
    assert login[5] == [b"987654321"]
    assert login[16] == [2]
    print("offline_self_test=PASS")


def run_live(apk: Path, report: Path) -> None:
    cert = find_certificate_sha1(apk)
    checkin = "NOT_ATTEMPTED"
    registration = "NOT_ATTEMPTED"
    mcs = "NOT_ATTEMPTED"
    detail = "NONE"
    try:
        answer = post(CHECKIN_URL, checkin_payload(),
                      {"Content-Type": "application/x-protobuf", "User-Agent": "Android-Checkin/1.0"})
        decoded = parse_fields(answer)
        if not decoded.get(1, [0])[0]:
            checkin, detail = "REJECTED", "CHECKIN_STATS_NOT_OK"
            emit_report(report, checkin, registration, detail)
            return
        android_id = decoded.get(7, [None])[0]
        security_token = decoded.get(8, [None])[0]
        if not isinstance(android_id, int) or not isinstance(security_token, int):
            checkin, detail = "INCOMPLETE", "DEVICE_CREDENTIALS_MISSING"
            emit_report(report, checkin, registration, detail)
            return
        checkin = "OK"
        # Experimental compatibility question: can the registered Chrome
        # instance obtain a sender-specific, Android-app addressed token?
        # No Firebase API key, user mailbox credential or existing token.
        time.sleep(5)
        form = {
            "app": APP_ID,
            "sender": SENDER_ID,
            "device": str(android_id),
            "cert": cert,
            "app_ver": "151070",
            "X-subtype": SENDER_ID,
            "X-scope": "FCM",
            "X-appid": secrets.token_urlsafe(18),
        }
        response = post(REGISTER_URL, urllib.parse.urlencode(form).encode(),
                        {"Authorization": "AidLogin " + str(android_id) + ":" + str(security_token),
                         "Content-Type": "application/x-www-form-urlencoded",
                         "User-Agent": "Android-GCM/1.5 (Windows Research)"}).decode("utf-8", errors="replace")
        if response.startswith("token=") and len(response) > len("token=") + 10:
            registration = "TOKEN_ISSUED"
            detail = "TOKEN_NOT_PERSISTED"
            time.sleep(5)
            mcs = probe_mcs(android_id, security_token)
        elif response.startswith("Error="):
            registration = "REJECTED"
            detail = safe_state(response.split("=", 1)[1].strip())
        else:
            registration = "UNRECOGNIZED_RESPONSE"
            detail = "REDACTED"
    except urllib.error.HTTPError as exc:
        detail = "HTTP_" + str(exc.code)
        if checkin != "OK":
            checkin = "ERROR"
        else:
            registration = "ERROR"
    except urllib.error.URLError:
        detail = "NETWORK_UNAVAILABLE"
        if checkin != "OK":
            checkin = "ERROR"
        else:
            registration = "ERROR"
    except (ValueError, OSError) as exc:
        detail = safe_state(type(exc).__name__)
        if checkin != "OK":
            checkin = "ERROR"
        else:
            registration = "ERROR"
    finally:
        emit_report(report, checkin, registration, detail, mcs)
        print("mcs_login=" + safe_state(mcs))
        print("checkin=" + safe_state(checkin))
        print("sender_registration=" + safe_state(registration))
        print("diagnostic=" + safe_state(detail))
        print("No credentials/tokens printed or retained.")


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--self-test", action="store_true")
    parser.add_argument("--live", action="store_true")
    parser.add_argument("--apk", type=Path)
    parser.add_argument("--report", type=Path)
    args = parser.parse_args()
    if args.self_test:
        self_test()
    if args.live:
        if not args.apk or not args.report:
            parser.error("--live requires --apk and --report")
        run_live(args.apk, args.report)
    elif not args.self_test:
        parser.error("choose --self-test or --live")


if __name__ == "__main__":
    main()
