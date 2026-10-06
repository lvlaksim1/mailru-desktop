# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — AJ smart-thread path is the sole mailbox-list transport

The active endpoint is `aj-https.mail.ru/api/v1/m/threads/status/smart` using the mobile `access_token`. Touch/web mailbox fallbacks are removed.

## I-003 — expand reverse API coverage
status: active — permitted-host research

`aj-https.mail.ru` remains the primary API host and `af.attachmail.ru` is explicitly allowed for incoming attachment download. Full message, read/unread, move/archive/trash and incoming attachment download are now released; remaining priorities are permanent delete, contacts, server search and confirmed flag mutation.

## I-004 — send/scheduled-send flow
status: active — immediate send established; delayed send runtime validation pending

Both outgoing attachment upload and send/schedule remain on verified AJ endpoints.

## I-005 — installer/update release channel
status: completed

v0.1.15 has been published with both `MailRuDesktop_Update_v0.1.15.exe` for existing installations and `MailRuDesktop_Setup_v0.1.15.exe` for first install/recovery.

## I-006 — full-message read and incoming attachments
status: released in v0.1.15

Full-message retrieval uses `aj-https.mail.ru/api/v1/messages/message`. Incoming attachment download uses the Owner-approved `af.attachmail.ru/cgi-bin/readmsg` path.

## I-007 — AJ-only account authorization
status: released; owner runtime validation of v0.1.15 remains pending

Authentication uses only the mobile OAuth-style AJ request. Success requires `access_token`. CAPTCHA/reCAPTCHA/additional verification produces a user notification and stops authorization; there is no challenge solver and no web/touch fallback.

## I-008 — incoming message actions
status: partially released in v0.1.15

Read/unread uses `/api/v1/messages/marks`; move/archive/trash use `/api/v1/messages/move`. Permanent delete remains disabled pending a verified permitted-host operation.

## I-009 — desktop UX/settings
status: active

Existing desktop UX remains. v0.1.15 adds full-message viewing, incoming attachment download, read/unread control, move, archive and trash while leaving `last_modified` unchanged.
