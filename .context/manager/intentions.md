# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — Hackus touch implementation complete, owner validation pending

v0.1.12 no longer expects a mobile access_token from Hackus login. Mailbox loading follows Hackus touch gosearch without q_query wildcard and reconstructs standard folders from returned folder ids.

## I-003 — expand reverse API coverage
status: active — owner runtime validation pending

Touch token/cookie session is authoritative for Hackus-backed search, full message, contacts, move/delete and attachment download. Web/mobile routes remain compatibility or separate proven VBA paths where appropriate.

## I-004 — send/scheduled-send flow
status: active — immediate send previously established; delayed send runtime validation pending

## I-005 — installer/update release channel
status: completed

Current release: `v0.1.12`. Release workflow `37398787496` succeeded; Setup + Update are release assets; Actions artifacts are absent; only the latest binary Release is retained.

## I-006 — full-message read and attachments
status: active — UI implementation complete, owner validation pending

Dedicated message window opens on double click, renders full content, exposes compact attachments with tooltips/download, and uses Hackus touch full-message/attachment paths first.

## I-007 — Hackus-equivalent account/challenge authorization
status: active — CAPTCHA flow owner-verified; post-login mailbox path corrected to Hackus touch semantics

No mobile access_token is expected as part of Hackus Login. GetSearchToken is the post-login credential step.

## I-008 — incoming message actions
status: active — implementation complete, owner validation pending

Reply, Forward, Archive and Move to folder are exposed in the full-message window; touch move/delete is preferred.

## I-009 — desktop UX/settings
status: active — requested v0.1.12 fixes implemented, owner validation pending

Softer dark title bar, themed calendar, corrected folder ComboBox text, message-grid cleanup and centralized theme behavior are implemented.
