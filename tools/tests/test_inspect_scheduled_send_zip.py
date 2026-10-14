"""Offline tests: output never contains mailbox content, tokens, or message IDs."""
import importlib.util
import json
import tempfile
import unittest
import zipfile
from pathlib import Path
from urllib.parse import urlencode


ROOT = Path(__file__).resolve().parents[1]
spec = importlib.util.spec_from_file_location(
    "inspect_scheduled_send_zip", ROOT / "inspect_scheduled_send_zip.py"
)
tool = importlib.util.module_from_spec(spec)
spec.loader.exec_module(tool)


class InspectScheduledSendZipTests(unittest.TestCase):
    def _archive(self, root, rows, bodies=None):
        path = Path(root) / "synthetic.research.zip"
        with zipfile.ZipFile(path, "w") as z:
            z.writestr(tool.JOURNAL, "\n".join(json.dumps(r) for r in rows))
            for name, payload in (bodies or {}).items():
                z.writestr(tool.BODIES + name, payload)
        return path

    def test_sanitized_schedule_and_unknown_candidate(self):
        secret = "DO_NOT_PRINT_access_token_private_9324"
        correspondents = "private@example.invalid"
        form = urlencode({
            "id": "original_message_secret_id_51",
            "send_date": "1933443990",
            "source": json.dumps({
                "schedule": "secret_schedule_id_281",
                "reply": "",
                "draft": "",
            }),
            "correspondents": correspondents,
            "body": "The private body of the letter",
        })
        row = {
            "url": "https://aj-https.mail.ru/api/v1/messages/schedule/ABC987?"
                   "access_token=" + secret + "&email=" + correspondents,
            "method": "POST",
            "request": {"body": {"present": True, "path": "req.bin"}},
            "response": {
                "status_code": 200,
                "body": {"present": True, "path": "resp.bin"},
            },
        }
        unknown = {"url": "https://aj-https.mail.ru/api/v1/messages/dispatch",
                   "method": "POST", "request": {}, "response": {}}
        unrelated = {"url": "https://evil.invalid/api/v1/messages/send?"
                          "access_token=" + secret, "method": "POST"}
        with tempfile.TemporaryDirectory() as tmp:
            archive = self._archive(tmp, [row, unknown, unrelated], {
                "req.bin": form.encode(),
                "resp.bin": json.dumps({
                    "status": 200, "message": "The private body of the letter",
                    "private_key": secret
                }).encode(),
            })
            result = tool.inspect_zip(archive)
        raw = json.dumps(result, ensure_ascii=False)
        for sensitive in (
            secret, correspondents, "secret_schedule_id_281",
            "original_message_secret_id_51", "The private body of the letter",
            "ABC987",
        ):
            self.assertNotIn(sensitive, raw)
        self.assertEqual(result["matching_transactions"], 2)
        self.assertEqual(result["observations"][0]["path"],
                         "/api/v1/messages/schedule/{value}")
        self.assertEqual(result["observations"][0]["request"]["source_nonempty"],
                         ["schedule"])
        self.assertEqual(result["observations"][0]["response"]["http_status"], 200)
        self.assertFalse(result["safe_send_now_operation_verified"])
        self.assertTrue(result["read_only"])

    def test_no_recording_is_not_success(self):
        with tempfile.TemporaryDirectory() as tmp:
            archive = self._archive(tmp, [
                {"url": "https://example.com/api/v1/messages/send", "method": "POST"}
            ])
            result = tool.inspect_zip(archive)
        self.assertEqual(result["matching_transactions"], 0)
        self.assertFalse(result["safe_send_now_operation_verified"])

    def test_large_body_is_not_read(self):
        with tempfile.TemporaryDirectory() as tmp:
            archive = self._archive(tmp, [{
                "url": "https://aj-https.mail.ru/api/v1/messages/send",
                "method": "POST",
                "request": {"body": {"present": True, "path": "huge.bin"}},
            }], {"huge.bin": b"Z" * (tool.MAX_BODY + 1)})
            result = tool.inspect_zip(archive)
        self.assertEqual(result["observations"][0]["request"]["known_form_keys"], [])
        self.assertFalse(result["safe_send_now_operation_verified"])

    def test_absent_journal_is_reported(self):
        with tempfile.TemporaryDirectory() as tmp:
            path = Path(tmp) / "invalid.zip"
            with zipfile.ZipFile(path, "w") as z:
                z.writestr("random.txt", "irrelevant")
            with self.assertRaises(tool.EvidenceError):
                tool.inspect_zip(path)


if __name__ == "__main__":
    unittest.main()
