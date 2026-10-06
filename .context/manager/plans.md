# Manager plans

## Access-token protocol plan

1. Classify API eligibility by credential identity: the official client must use the same `ru.mail.oauth2.access`; hostname and token parameter spelling are not exclusion criteria.
2. Use branch `research/mail-apk-15.107.0.148045` as the static evidence base.
3. Preserve method, resolved host, token transport, request fields, response fields and error behavior for each candidate.
4. Static APK evidence establishes an official-client contract, not a live-server pass. Make one controlled live validation before enabling a newly discovered function in normal UI.
5. Keep at least five seconds between research/probe/test network requests. Do not restore that fixed delay to normal application runtime.
6. Refactor the existing fixed-host runtime/CI guard before adding valid same-token endpoints on other hosts.

## Authorization plan

1. Continue obtaining the mailbox OAuth token through the current mobile-style authorization flow.
2. Success requires `access_token`; store access/refresh tokens with Windows DPAPI and never persist the password.
3. If CAPTCHA/reCAPTCHA/additional verification is required, notify the user and stop.
4. Do not introduce independent web-cookie sessions, IMAP/SMTP or app-specific passwords as fallbacks.

## Near-term product plan

1. Preserve v0.1.15 behavior while removing obsolete host-only assumptions from protocol policy and CI.
2. Live-validate permanent remove and `flagged`/`pinned` marks, then wire them.
3. Live-validate server search; new `https://go.mail.ru/api/v1/go/search/emails` uses `t=<same ru.mail.oauth2.access>`.
4. Live-validate address book/autocomplete and folder add/edit/remove/clear/archive.
5. Validate draft/schedule and outgoing attachment remove/reattach.
6. Then consider aliases, collectors, filters, cloud attachment bundles, translation and other secondary capabilities.
7. Keep current folder `last_modified` behavior unchanged unless the Owner explicitly authorizes work on it.
8. Determine modern compose-session message-id semantics and validate delayed-send `send_date`.
