# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: completed

## I-003 — expand reverse API coverage
status: active — owner runtime validation pending

Web-session operations are preferred where available; touch remains internal fallback.

## I-004 — verified send flow
status: completed for existing mobile-token sessions

## I-005 — installer/update release channel
status: completed

Current release: `v0.1.9`. Release workflow `37390587718` succeeded; Setup + Update are release assets; Actions artifacts are absent and older binary Releases are pruned. Both installer variants now offer an optional desktop shortcut.

## I-006 — full-message read and attachments
status: active — owner validation pending

## I-007 — Hackus-equivalent account/challenge authorization
status: active — corrected implementation, owner validation pending

Commitment: preserve the Hackus auth/challenge state machine and replace only its external CAPTCHA solver with manual solving. No alternate browser-login architecture.

v0.1.9 removes the remaining preliminary mobile auth probe and replaces the real Mail.ru browser challenge page with an isolated manual RecaptchaV2 solver hosted on virtual https://account.mail.ru using the extracted sitekey. The returned token is fed into the original same-session CreateSession(token). Classic CAPTCHA remains GetVerificationType/GetCaptchaImage/SubmitCaptchaAnswer/CreateSessionByLink. The only intentional behavior differences from Hackus are manual user solving instead of an external solver and the owner's >=5 second request-spacing safety rule.

## I-008 — incoming message actions
status: active — owner validation pending

## I-009 — desktop UX/settings
status: completed for v0.1.9 scope

Theme architecture is centralized: DynamicResource palette, implicit styles for standard controls, SystemColors aliases, native dark title bars and system-theme reactivity. New ordinary WPF controls should inherit theme automatically without per-element color work.
