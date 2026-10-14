#!/usr/bin/env python3
"""Read-only, redacted inspection of APK Research HTTPS recordings.

This tool NEVER sends a request. Even an apparent send-now operation in a
recording is not evidence of exactly-once delivery or schedule cancellation.
"""
from __future__ import annotations

import argparse
import json
import re
import sys
import zipfile
from pathlib import Path
from urllib.parse import parse_qs, urlsplit

JOURNAL = "02_normalized/http-transactions.jsonl"
BODIES = "02_normalized/http-bodies/"
MAX_JOURNAL = 24 * 1024 * 1024
MAX_BODY = 128 * 1024
MAX_RECORDS = 5000
KNOWN_FIELDS = frozenset({
    "id", "ids", "source", "send_date", "body", "subject",
    "correspondents", "attaches", "priority", "folder", "action",
    "message_id", "schedule_id", "email", "token", "access_token",
})
SOURCE_FIELDS = frozenset({"draft", "reply", "forward", "schedule"})
RESPONSE_FIELDS = frozenset({"status", "body", "data", "error", "result", "message", "errors"})
SAFE_SEGMENT = re.compile(r"^[a-z][a-z_-]{0,30}$", re.ASCII)


class EvidenceError(ValueError):
    pass


def _safe_path(path: str) -> str:
    """Reveal only route words; never emit IDs, email addresses or query."""
    parts = path.split("/")
    if len(parts) < 5 or parts[:4] not in (
        ["", "api", "v1", "messages"], ["", "api", "v1", "m"]
    ):
        return "/{unrecognized}"
    # /api/v1 is a static protocol prefix, not a message identifier.
    fixed = ["", "api", "v1", parts[3]]
    tail = [
        part if SAFE_SEGMENT.fullmatch(part) else "{value}"
        for part in parts[4:7]
    ]
    return "/".join(fixed + tail)


def _body_bytes(archive: zipfile.ZipFile, row: dict, side: str) -> bytes:
    obj = row.get(side)
    if not isinstance(obj, dict):
        return b""
    body = obj.get("body")
    if not isinstance(body, dict) or not body.get("present"):
        return b""
    name = body.get("path")
    if not isinstance(name, str) or not name or Path(name).name != name:
        return b""
    archive_name = BODIES + name
    try:
        meta = archive.getinfo(archive_name)
    except KeyError:
        return b""
    if meta.file_size > MAX_BODY:
        return b""
    with archive.open(meta) as stream:
        return stream.read(MAX_BODY + 1)[:MAX_BODY]


def _decode_form(payload: bytes) -> dict:
    if not payload:
        return {}
    try:
        as_text = payload.decode("utf-8")
    except UnicodeDecodeError:
        return {}
    if len(as_text) > MAX_BODY:
        return {}
    if as_text.lstrip().startswith("{"):
        try:
            obj = json.loads(as_text)
            return obj if isinstance(obj, dict) else {}
        except ValueError:
            return {}
    return parse_qs(as_text, keep_blank_values=True)


def _first(value: object) -> str:
    if isinstance(value, list):
        return str(value[0]) if value else ""
    return str(value) if value is not None else ""


def _request_shape(payload: bytes) -> dict:
    form = _decode_form(payload)
    keys = sorted(k for k in form if k in KNOWN_FIELDS)
    result: dict = {"known_form_keys": keys}
    if "id" in form:
        result["message_id_nonempty"] = bool(_first(form["id"]))
    if "send_date" in form:
        result["send_date_present"] = True
        result["send_date_is_zero"] = _first(form["send_date"]) == "0"
    if "source" in form:
        try:
            source = json.loads(_first(form["source"]))
        except (ValueError, TypeError):
            source = None
        if isinstance(source, dict):
            result["source_fields"] = sorted(k for k in source if k in SOURCE_FIELDS)
            result["source_nonempty"] = sorted(
                k for k in source if k in SOURCE_FIELDS and bool(source[k])
            )
    return result


def _response_shape(payload: bytes, code: object) -> dict:
    result: dict = {}
    if isinstance(code, int) and 100 <= code <= 599:
        result["http_status"] = code
    try:
        data = json.loads(payload.decode("utf-8")) if payload else None
    except (UnicodeError, ValueError):
        data = None
    if isinstance(data, dict):
        result["known_response_keys"] = sorted(k for k in data if k in RESPONSE_FIELDS)
        status = data.get("status")
        if isinstance(status, (int, bool)) or (
            isinstance(status, str) and status in ("ok", "error", "success")
        ):
            result["server_status_kind"] = status
    return result


def inspect_zip(path: Path) -> dict:
    if not path.is_file():
        raise EvidenceError("Archive not found")
    with zipfile.ZipFile(path, "r") as archive:
        try:
            meta = archive.getinfo(JOURNAL)
        except KeyError as exc:
            raise EvidenceError("No HTTPS transactions journal in archive") from exc
        if meta.file_size > MAX_JOURNAL:
            raise EvidenceError("Journal exceeds the 24 MiB inspection limit")
        with archive.open(meta) as stream:
            journal = stream.read(MAX_JOURNAL + 1)
        if len(journal) > MAX_JOURNAL:
            raise EvidenceError("Journal exceeds the inspection limit")

        observations: list[dict] = []
        total = 0
        skipped = 0
        for number, raw in enumerate(journal.splitlines(), 1):
            if number > MAX_RECORDS:
                raise EvidenceError("Too many transactions for one inspection")
            try:
                row = json.loads(raw)
            except ValueError:
                skipped += 1
                continue
            if not isinstance(row, dict):
                skipped += 1
                continue
            url = row.get("url")
            if not isinstance(url, str):
                skipped += 1
                continue
            try:
                u = urlsplit(url)
            except ValueError:
                skipped += 1
                continue
            if u.scheme != "https" or (u.hostname or "").lower() != "aj-https.mail.ru":
                continue
            route = u.path.rstrip("/")
            if not (route.startswith("/api/v1/messages/") or
                    route == "/api/v1/m/threads/status/smart"):
                continue
            if route.startswith("/api/v1/messages/attaches/"):
                continue
            total += 1
            req = row.get("request") or {}
            response = row.get("response") or {}
            method = row.get("method") if isinstance(row.get("method"), str) else ""
            observations.append({
                "sequence": number,
                "method": method.upper() if method.upper() in
                          ("GET", "POST", "PUT", "PATCH", "DELETE") else "OTHER",
                "path": _safe_path(route),
                "request": _request_shape(_body_bytes(archive, row, "request")),
                "response": _response_shape(
                    _body_bytes(archive, row, "response"),
                    response.get("status_code") if isinstance(response, dict) else None
                ),
            })

    return {
        "format": "MailRuDesktop.scheduled_evidence.v1",
        "read_only": True,
        "contains_message_values": False,
        "approved_host": "aj-https.mail.ru",
        "matching_transactions": total,
        "invalid_lines": skipped,
        "observations": observations,
        "safe_send_now_operation_verified": False,
        "reason": "A captured request alone cannot establish cancellation of the original schedule or exactly-once delivery.",
    }


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("archive", type=Path, help="APK Research .research.zip")
    args = parser.parse_args()
    try:
        report = inspect_zip(args.archive)
    except (EvidenceError, OSError, zipfile.BadZipFile) as exc:
        print(f"Inspection unavailable: {type(exc).__name__}", file=sys.stderr)
        return 2
    print(json.dumps(report, ensure_ascii=False, indent=2))
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
