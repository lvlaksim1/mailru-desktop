# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active

The released mailbox-list transport remains `/api/v1/m/threads/status/smart` with the mailbox OAuth token.

## I-003 — complete access-token API coverage
status: active — static discovery completed; validation/integration phase active

APK 15.107.0.148045 is statically mapped. Of 101 OAuth candidates, 100 are proven to carry the same `ru.mail.oauth2.access`; `QrGetInfoCommand` is excluded. Work now shifts from host-based discovery to live validation and product integration.

## I-004 — send/scheduled-send flow
status: active

Immediate send is released. Delayed-send `send_date` semantics remain to be validated. Send, draft and schedule share the recovered official-client request model.

## I-005 — installer/update release channel
status: completed

Latest public release remains v0.1.15 with full and update installers.

## I-006 — full-message read and incoming attachments
status: released in v0.1.15

## I-007 — mailbox OAuth authorization
status: released; challenge policy fixed

Authorization obtains the mailbox OAuth `access_token`. CAPTCHA/reCAPTCHA/additional verification is reported and authorization stops. No challenge solver, web-cookie fallback, IMAP/SMTP or app-password fallback.

## I-008 — incoming message actions
status: partially released; expansion ready for validation

Read/unread and move/archive/trash are released. Permanent remove, bulk remove/clear, spam/unspam, flagged and pinned marks are statically confirmed and await controlled live validation plus product wiring.

## I-009 — desktop UX/settings
status: active

Expose new operations only after their contract and live behavior are verified.

## I-010 — high-value access-token expansion
status: active

Validate and integrate in priority order: permanent delete and mark mutations; server search including `go.mail.ru` with token parameter `t`; address book/autocomplete; folder management; drafts/scheduled-message lifecycle; outgoing attachment lifecycle; then aliases/collectors/filters/cloud and other useful secondary functions.
