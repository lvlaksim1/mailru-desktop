# Latest handoff

## Last completed work

Published MailRu Desktop `v0.1.1`.

The release adds:

- project-owned cloud/envelope application mark based on the Owner-approved visual direction;
- assembly-derived version in the native window title;
- tolerant parsing of the current `m/threads/status/smart` response;
- structured mailbox table;
- sender/subject/date/size and unread/star/attachment indicators;
- selected-item metadata/snippet preview;
- raw JSON preserved as expandable diagnostics.

Owner runtime evidence from v0.1.0 confirmed live authentication and folder id 0 loading. The observed response schema was sanitized and recorded in `docs/protocol/observations/2026-10-05-folder0.md`.

CI run `37342048282` and release workflow `37342356199` both passed.

## Verified current state

Current release: `v0.1.1`.

Normal upgrade package:
`MailRuDesktop_Update_v0.1.1.exe`.

Full message body reading is not yet enabled because the full-message/thread endpoint is still only externally known/candidate and must be verified against current Mail.ru traffic first.

## Next operation

Capture/inspect the request generated when opening a real message, promote the current read endpoint to A-level evidence, and wire the reading pane to actual message content. Then proceed to real folder discovery and message state mutations.
