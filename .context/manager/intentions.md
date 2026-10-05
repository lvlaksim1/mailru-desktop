# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: completed

v0.1.2 established live folders and structured messages. v0.1.6 integrates filtering into the mailbox view.

## I-003 — expand reverse API coverage
status: active — owner runtime validation pending

v0.1.6 shifts normal incoming-mail operations away from mandatory touch credentials where a web-session mechanism is available. Web full-message, attachment download, move and delete are preferred; touch remains an internal fallback.

## I-004 — verified send flow
status: completed

## I-005 — installer/update release channel
status: completed

Current release: `v0.1.6`. Release workflow `37375038704` succeeded, Setup + Update are release assets, Actions artifacts are absent and older binary Releases are pruned.

## I-006 — full-message read and attachments
status: active — implementation complete, owner validation pending

v0.1.6 uses e.mail.ru web full-message routes first, supports nested `body.text/body.html`, parses `attaches.list`, preserves name/MIME and downloads using authenticated web cookies. Touch read remains fallback only.

## I-007 — robust account/challenge authorization
status: active — implementation complete, owner validation pending

Challenge/Blocked/recovery classification precedes invalid-password classification. If lightweight aj auth says token-missing while the auxiliary web probe says invalid credentials, v0.1.6 opens the real Mail.ru browser login flow instead of issuing a false wrong-password result. CAPTCHA/2FA can be completed interactively. Every explicit login starts from a fresh cookie session.

## I-008 — incoming message actions
status: active — owner validation pending

Move to folder/Trash and permanent delete prefer e.mail.ru web-session operations. Permanent delete is exposed only while viewing Trash.

## I-009 — desktop UX/settings
status: completed for requested v0.1.6 scope

Saved-account selector + modal Add Account flow, integrated filtering, persistent System/Light/Dark themes, and theme-aware message reader are implemented.
