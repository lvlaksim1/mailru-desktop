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

Current release: `v0.1.7`. Release workflow `37380041150` succeeded; Setup + Update are release assets; Actions artifacts are absent and older binary Releases are pruned.

## I-006 — full-message read and attachments
status: active — owner validation pending

## I-007 — robust account/challenge authorization
status: active — corrected implementation, owner validation pending

v0.1.7 replaces the incorrect “browser session -> export cookies -> new HTTP client” approach. The original Hackus-style HTTP session now remains alive from the first `POST /cgi-bin/auth` through challenge completion and token derivation. Manual WebView2 challenge completion feeds its result back into that same session. reCAPTCHA attempts the exact Hackus continuation shape: same cookies + repeated `POST /cgi-bin/auth` + `g-recaptcha-response`.

## I-008 — incoming message actions
status: active — owner validation pending

## I-009 — desktop UX/settings
status: completed for current requested scope
