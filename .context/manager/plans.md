# Manager plans

## Plan for I-002

1. Capture representative `m/threads/status/smart` responses from at least Inbox and one non-system/custom folder.
2. Introduce typed protocol DTOs with tolerant unknown-field handling.
3. Add folder/thread service methods above the raw transport call.
4. Replace the raw-response-only UI with a three-pane mailbox skeleton: folders, message/thread list, reading pane.
5. Add protocol parsing tests using sanitized fixtures.

## Plan for I-003

1. Build a capture ingestion/checklist format that records host, method, path, query, request schema, response schema and evidence provenance without secrets.
2. Revalidate Hackus-discovered auth/search/move/remove/address-book operations.
3. Revalidate e.mail.ru families discovered in independent reverse projects.
4. Compare web and mobile generations and determine which operations map to `aj-https.mail.ru` directly versus sibling hosts.
5. Promote verified operations in `docs/protocol` and add regression tests before enabling them in UI.

## Near-term product plan

- introduce Windows-protected access-token persistence; do not persist the mailbox password;
- move compose logic from window code-behind into application services/view-model state after the first interaction contract is stable;
- determine valid modern compose-session message-id semantics and replace the fixed compatibility sentinel only after evidence;
- add user-friendly server-side schedule UI after the exact `send_date` format is verified;
- add local cache/index only after server models are stable enough to avoid schema churn.
