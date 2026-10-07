# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — parser hardening in progress

The released mailbox-list transport remains `/api/v1/m/threads/status/smart` with the mailbox OAuth token. Parser behavior must remain tolerant across all observed server shapes, including the newly observed `threads[].representations[]` form.

## I-003 — complete access-token API coverage
status: active — static discovery completed; staged product integration active

APK 15.107.0.148045 is statically mapped: 100 commands are confirmed to use the same `ru.mail.oauth2.access`. Product integration proceeds only after live or owner-runtime validation.

## I-004 — send/scheduled-send flow
status: active

Immediate send, reply with attachments and server draft saving are released. Exact delayed-send `send_date` semantics still require dedicated validation.

## I-005 — installer/update release channel
status: completed

Latest public release is v0.3.3 with both full and update installers. The next intended release is v0.3.4 after the parser hotfix is merged and packaged.

## I-006 — full-message read and incoming attachments
status: released

The preview pane is now the normal full-message workspace. It supports full body, selectable addresses and subject, reply, forward, move/archive/trash, attachments and reply attachments.

## I-007 — mailbox OAuth authorization
status: released; challenge policy fixed; refresh recovery released

Authorization obtains the mailbox OAuth `access_token`. CAPTCHA/reCAPTCHA/additional verification is reported and authorization stops. No challenge solver, web-cookie fallback, IMAP/SMTP or app-password fallback. Stored `refresh_token` is used to recover an expired access token through `https://o2.mail.ru/token`.

## I-008 — incoming message actions
status: active — core per-message actions released

Released operations include read/unread, move/archive/trash, permanent removal from Trash, flagged and pinned marks. UI actions are attached to compact message rows. Pinned messages are grouped above ordinary chronological mail and return to chronological position when unpinned. Bulk/thread actions, spam/unspam, categories and related operations remain later work.

## I-009 — desktop UX/settings
status: active — modern UI released and being refined from owner feedback

Current UI baseline v0.3.3:
- dark theme by default;
- left navigation/folders;
- compact three-line message list;
- resizable list/preview split;
- full preview workspace;
- right-side resizable textual account list;
- account badge equals Inbox unread count;
- yellow active flag star and red active pin;
- date-range filter plus unified text search;
- full-width compose and removable attachments.

## I-010 — high-value access-token expansion
status: active — temporarily secondary to v0.3.4 parser hotfix

After v0.3.4 stabilizes multi-account mailbox parsing, resume thread/bulk message operations, spam/unspam, subscription/category actions, attachment lifecycle, EML/metadata/read receipt, and search improvements. The new `go.mail.ru` search remains deferred until its HTTP 520 live failure is explained.

## I-011 — multi-account correctness
status: active — highest immediate priority

Each authorized account must independently load folders, Inbox messages, preview content and unread counts. Switching accounts must not reuse another account's parsed mailbox state. The badge is specifically Inbox `messages_unread`.

Current defect: v0.3.3 ignores the valid `threads[].representations[]` smart-thread shape observed on the Owner's second account. PR #34 on `fix/v0.3.4-representations-parser` implements the fix; CI is green and release is pending.
