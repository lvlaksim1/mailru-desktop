# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — parser hotfix released; owner runtime validation remains

The released mailbox-list transport remains `/api/v1/m/threads/status/smart` with the mailbox OAuth token. Parser behavior must remain tolerant across all observed server shapes: `base_message`, `messages[]`, direct thread-like objects, and `threads[].representations[]`.

v0.3.4 released support for the `representations[]` form observed on the Owner's second account. Build/CI validation is complete; the exact affected account still requires Owner runtime confirmation.

## I-003 — complete access-token API coverage
status: active — static discovery completed; staged product integration active

APK 15.107.0.148045 is statically mapped: 100 commands are confirmed to use the same `ru.mail.oauth2.access`. Product integration proceeds only after live or owner-runtime validation.

## I-004 — send/scheduled-send flow
status: active

Immediate send, reply with attachments and server draft saving are released. Exact delayed-send `send_date` semantics still require dedicated validation.

## I-005 — installer/update release channel
status: completed

Current public release is v0.3.5 with both full and update installers. Future updates continue through the in-place update installer.

## I-006 — full-message read and incoming attachments
status: released

The preview pane is the normal full-message workspace. It supports full body, selectable addresses and subject, reply, forward, move/archive/trash, attachments and reply attachments.

## I-007 — mailbox OAuth authorization
status: released; challenge policy fixed; refresh recovery released

Authorization obtains the mailbox OAuth `access_token`. CAPTCHA/reCAPTCHA/additional verification is reported and authorization stops. No challenge solver, web-cookie fallback, IMAP/SMTP or app-password fallback. Stored `refresh_token` is used to recover an expired access token through `https://o2.mail.ru/token`.

## I-008 — incoming message actions
status: active — core per-message actions released

Released operations include read/unread, move/archive/trash, permanent removal from Trash, flagged and pinned marks. Opening a message still marks it read automatically. v0.3.5 adds an explicit action that lets the currently selected message be marked unread again without immediately undoing that user action. Bulk/thread actions, spam/unspam, categories and related operations remain later work.

## I-009 — desktop UX/settings
status: active — v0.3.5 mail-list redesign released; owner visual validation pending

Current v0.3.5 mail-list baseline:
- messages are grouped under separate date headers;
- each message row has seven columns: fixed-size read/unread dot, sender name or fallback email, flag, thread count, subject plus first text line, attachment indicator, and time;
- unread rows use a filled dot and bold text in the subject/text column;
- attachment column is blank without attachments and shows a paperclip otherwise;
- theme resources remain authoritative for light/dark/system appearance;
- the preview toolbar can mark the selected message unread after automatic read-on-open.

The surrounding v0.3.x three-pane layout, right-side account list, preview workspace, filters and compose workflow remain the current UX baseline unless the Owner revises them.

## I-010 — high-value access-token expansion
status: active — secondary to owner validation of v0.3.5 UI and multi-account parser

After the current UI/parser validation is stable, resume thread/bulk message operations, spam/unspam, subscription/category actions, attachment lifecycle, EML/metadata/read receipt, and search improvements. The new `go.mail.ru` search remains deferred until its HTTP 520 live failure is explained.

## I-011 — multi-account correctness
status: active — v0.3.4 fix released; owner validation pending

Each authorized account must independently load folders, Inbox messages, preview content and unread counts. Switching accounts must not reuse another account's parsed mailbox state. The badge is specifically Inbox `messages_unread`.

The confirmed `threads[].representations[]` parser defect was fixed and released in v0.3.4. The remaining gate is Owner runtime validation on the second account that originally exposed this response shape.
