# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active

The released mailbox-list transport remains `/api/v1/m/threads/status/smart` with the mailbox OAuth token.

## I-003 — complete access-token API coverage
status: active — static discovery completed; staged product integration active

APK 15.107.0.148045 is statically mapped: 100 commands are confirmed to use the same `ru.mail.oauth2.access`. v0.2.0 promoted the first high-value subset to live-validated product functionality.

## I-004 — send/scheduled-send flow
status: active

Immediate send remains released. Server draft saving is released in v0.2.0. Exact delayed-send `send_date` semantics still require dedicated validation.

## I-005 — installer/update release channel
status: completed

Latest public release is v0.2.0 with both full and update installers.

## I-006 — full-message read and incoming attachments
status: released

## I-007 — mailbox OAuth authorization
status: released; challenge policy fixed

Authorization obtains the mailbox OAuth `access_token`. CAPTCHA/reCAPTCHA/additional verification is reported and authorization stops. No challenge solver, web-cookie fallback, IMAP/SMTP or app-password fallback.

## I-008 — incoming message actions
status: active — first expansion released in v0.2.0

Released operations now include read/unread, move/archive/trash, permanent removal from Trash, flagged and pinned marks. Bulk/thread actions, spam/unspam, categories and related operations remain for subsequent stages.

## I-009 — desktop UX/settings
status: active

v0.2.0 exposes server search, server contacts, user-folder management, flags/pinning, permanent delete and server draft saving in the desktop UI.

## I-010 — high-value access-token expansion
status: active — stage 1 completed, stage 2 pending

Stage 1 was released as v0.2.0. Stage 2 will focus on thread/bulk message operations, spam/unspam, subscription/category actions, attachment lifecycle, EML/metadata/read receipt, and search improvements. The new `go.mail.ru` search remains deferred until its HTTP 520 live failure is explained.
