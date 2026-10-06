# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — AJ smart-thread path is the sole mailbox-list transport

The active endpoint is `aj-https.mail.ru/api/v1/m/threads/status/smart` using the mobile `access_token`. Touch/web mailbox fallbacks are removed.

## I-003 — expand reverse API coverage
status: active — AJ-only research

Only `aj-https.mail.ru` candidates may be promoted into runtime. Next priorities are full message, incoming attachments, move/archive/delete, contacts and search.

## I-004 — send/scheduled-send flow
status: active — immediate send established; delayed send runtime validation pending

Both outgoing attachment upload and send/schedule remain on verified AJ endpoints.

## I-005 — installer/update release channel
status: completed

v0.1.13 has been published with both `MailRuDesktop_Update_v0.1.13.exe` for existing installations and `MailRuDesktop_Setup_v0.1.13.exe` for first install/recovery.

## I-006 — full-message read and incoming attachments
status: blocked on AJ endpoint discovery

The previous touch/web implementation is removed from active runtime. UI falls back to the thread snippet and explains that full content/download is unavailable until an AJ endpoint is verified.

## I-007 — AJ-only account authorization
status: released; owner runtime validation of v0.1.13 remains pending

Authentication uses only the mobile OAuth-style AJ request. Success requires `access_token`. CAPTCHA/reCAPTCHA/additional verification produces a user notification and stops authorization; there is no challenge solver and no web/touch fallback.

## I-008 — incoming message actions
status: blocked on AJ endpoint discovery

Move/archive/permanent delete are disabled until verified AJ endpoints are found.

## I-009 — desktop UX/settings
status: active

Existing desktop UX remains; v0.1.13 changes protocol behavior rather than reopening unrelated UI work.
