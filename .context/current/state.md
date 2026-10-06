# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.14`

Release state:

- GitHub Release `v0.1.14` was published on 2026-10-06;
- runtime-delay removal commit: `0e95bfec07a86f66f89444377a43858be0232d7a`;
- version commit: `9070cae8824950dda1d6dc4c9b3ace66485ff5a2`;
- release assets:
  - `MailRuDesktop_Setup_v0.1.14.exe`;
  - `MailRuDesktop_Update_v0.1.14.exe`.

Final Owner protocol decision on 2026-10-06:

- runtime protocol is **AJ-only**;
- every Mail.ru API request must target `https://aj-https.mail.ru`;
- mobile `access_token` is the sole accepted mailbox credential;
- CAPTCHA/reCAPTCHA/additional interactive verification is reported to the user and authorization stops;
- no challenge solving;
- no `touch.mail.ru`, `e.mail.ru`, `account.mail.ru`, `auth.mail.ru`, `c.mail.ru`, or other sibling Mail.ru runtime fallbacks;
- the previous web-API-token research course is cancelled for product runtime.

Live evidence:

- GitHub Actions run `37410564363` executed the verified mobile auth request against `aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`;
- result: HTTP 200 JSON, top-level `oauth` and `status`, both `access_token` and `refresh_token` present, no CAPTCHA/reCAPTCHA signal for the test account.

v0.1.14 runtime state:

- mobile AJ auth restored as the only auth path;
- normal application requests no longer have an artificial fixed five-second delay; protocol requests remain serialized to avoid overlap;
- runtime host guard rejects requests whose host is not `aj-https.mail.ru`;
- endpoint catalog contains only active AJ endpoints;
- web/touch session authenticator and CAPTCHA solver UI removed;
- saved sessions restore only when an AJ access token exists; legacy web/touch credentials are ignored and cleared on save;
- folder loading uses only `/api/v1/m/threads/status/smart`;
- outgoing attachment upload, immediate send, and scheduled send remain on verified AJ endpoints;
- full-message retrieval, incoming-attachment download, contacts server lookup, move/archive and permanent delete are disabled until AJ endpoints are verified;
- CI includes a source guard against non-AJ Mail.ru runtime URLs;
- folder `last_modified` behavior remains unchanged by explicit Owner instruction;
- application version is 0.1.14.

Historical research status:

- branch `research/web-api-token` is non-authoritative research evidence only;
- it must not be merged into runtime and must not be used to reintroduce web/touch fallbacks without an explicit new Owner decision.

Next phase is owner runtime validation of the released v0.1.14, especially Inbox refresh speed, and continued AJ-only endpoint research. The five-second spacing rule remains in force for research/probes/tests only.
