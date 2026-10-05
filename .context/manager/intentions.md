# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: completed

## I-003 — expand reverse API coverage
status: active — owner runtime validation pending

Web-session operations are preferred where available; touch remains internal fallback.

## I-004 — verified send flow
status: completed

## I-005 — installer/update release channel
status: completed

Current release: `v0.1.8`. Release workflow `37385570525` succeeded; Setup + Update are release assets; Actions artifacts are absent and older binary Releases are pruned.

## I-006 — full-message read and attachments
status: active — owner validation pending

## I-007 — Hackus-equivalent account/challenge authorization
status: active — implementation complete, owner validation pending

Commitment: preserve the Hackus auth/challenge state machine and change only the CAPTCHA solver source from external service to manual user solving. No alternate browser-login architecture.

v0.1.8 implements CreateSession ordering, GetReCaptchaSiteKey, same-session CreateSession(g-recaptcha-response), GetVerificationType, GetCaptchaImage, SubmitCaptchaAnswer and CreateSessionByLink. WebView2 is only the manual reCAPTCHA solver and is prevented from completing the login itself.

## I-008 — incoming message actions
status: active — owner validation pending

## I-009 — desktop UX/settings
status: completed for current requested scope
