# Manager plans

## Plan for I-002 — mailbox list/folders

1. Keep `/api/v1/m/threads/status/smart` as the only active mailbox-list transport.
2. Capture representative AJ responses from Inbox and non-system/custom folders.
3. Harden typed/tolerant parsing only against observed AJ response shapes.
4. Add sanitized parsing fixtures and regression tests.
5. Do not use touch/web search as a mailbox fallback.

## Plan for I-003 — expand AJ API coverage

1. Use `aj-https.mail.ru` as the primary Mail.ru API host. `af.attachmail.ru` is explicitly allowed as an auxiliary runtime host; the currently verified use is incoming-attachment download.
2. For each candidate, capture method, path, query, request schema, response schema, and evidence provenance without secrets.
3. Preserve at least five seconds between requests made by project research, probes, and automated tests. Do not impose that fixed delay on normal application runtime.
4. Wire the Owner-verified operations first: AJ full-message retrieval, AJ marks, AJ move/archive, and `af.attachmail.ru` incoming-attachment download. Continue research for permanent delete, contacts, server-side search, and confirmed flag mutation.
5. Keep each unsupported UI action disabled until its permitted-host operation is verified.
6. Add a regression guard before enabling a newly verified AJ operation.

## Plan for I-007 — authorization

1. Use only the mobile OAuth-style request to `aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`.
2. Success requires a returned `access_token`.
3. Persist access/refresh token with Windows DPAPI; never persist the password.
4. If CAPTCHA/reCAPTCHA/additional interactive verification is required, notify the user and stop.
5. Never call account/auth/touch/e.mail.ru as an authorization fallback.

## Near-term product plan

- owner-install and runtime-validate released v0.1.14;
- validate that Inbox refresh is no longer artificially delayed while keeping current `last_modified` behavior unchanged;
- validate delayed-send `send_date` semantics through the verified AJ schedule endpoint;
- determine valid modern compose-session message-id semantics;
- discover and verify missing AJ endpoints before re-enabling disabled message actions.
