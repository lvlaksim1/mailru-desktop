# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: completed

## I-003 — expand reverse API coverage
status: active — owner runtime validation pending

Web-session operations remain available; mobile access_token is restored after Hackus verification for the proven owner-VBA aj-https APIs. Touch token is reserved for search/contacts rather than folder enumeration.

## I-004 — verified send flow
status: active — immediate send established; scheduled send owner validation pending

v0.1.10 exposes delayed send and uses the existing /messages/schedule route and send_date payload derived from the owner VBA implementation.

## I-005 — installer/update release channel
status: completed

Current release: `v0.1.10`. Release workflow `37393220561` succeeded; Setup + Update are release assets; Actions artifacts are absent and older binary Releases are pruned. Optional desktop shortcut remains supported.

## I-006 — full-message read and attachments
status: active — owner validation pending

## I-007 — Hackus-equivalent account/challenge authorization
status: active — challenge verified by owner; mailbox credential completion pending v0.1.10 validation

v0.1.9 owner test confirmed manual reCAPTCHA completes and touch token is issued. v0.1.10 keeps the Hackus-first challenge sequence and acquires the owner-VBA mobile access_token only after successful verification, in the verified cookie session.

## I-008 — incoming message actions
status: active — owner validation pending

## I-009 — desktop UX/settings
status: active — dark-theme correction owner validation pending

Theme remains centralized and resource-driven. v0.1.10 adds custom templates for WPF controls whose default Windows templates ignored palette brushes (CheckBox/RadioButton/TabItem/ComboBox) and forces dark treatment of HTML mail containers.
