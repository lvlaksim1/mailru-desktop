# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release before this change: `v0.1.12`
Release being prepared: `v0.1.13`

Owner decision on 2026-10-06:

- runtime protocol is **AJ-only**;
- every Mail.ru API request must target `https://aj-https.mail.ru`;
- mobile `access_token` is the sole accepted mailbox credential;
- CAPTCHA/reCAPTCHA/additional interactive verification is reported to the user and authorization stops;
- no challenge solving;
- no `touch.mail.ru`, `e.mail.ru`, `account.mail.ru`, `auth.mail.ru`, or `c.mail.ru` runtime fallbacks.

Live evidence:

- GitHub Actions run `37410564363` executed the verified mobile auth request against `aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`;
- result: HTTP 200 JSON, top-level `oauth` and `status`, both `access_token` and `refresh_token` present, no CAPTCHA/reCAPTCHA signal for the test account.

v0.1.13 implementation:

- mobile AJ auth restored as the only auth path;
- global five-second request pacing retained;
- runtime host guard rejects requests whose host is not `aj-https.mail.ru`;
- endpoint catalog contains only active AJ endpoints;
- web/touch session authenticator and CAPTCHA solver UI removed;
- saved sessions restore only when an AJ access token exists; legacy web/touch credentials are ignored and cleared on save;
- folder loading uses only `/api/v1/m/threads/status/smart`;
- outgoing attachment upload, immediate send, and scheduled send remain on verified AJ endpoints;
- full-message retrieval, incoming-attachment download, contacts server lookup, move/archive and permanent delete are disabled until AJ endpoints are verified;
- CI includes a source guard against non-AJ Mail.ru runtime URLs;
- application version bumped to 0.1.13.

Release branch: `release/0.1.13-aj-only`
Pull request: #18.
