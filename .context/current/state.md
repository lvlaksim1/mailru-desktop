# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.15`

Release state:

- GitHub Release `v0.1.15` was published on 2026-10-06;
- release target commit: `e87a47fcdc4f107f2b22095ec7b77a0a3eba20be`;
- version commit: `9743ffdb07486b6647dafd168055ddcf80ae552e`;
- CI run `37465331504` completed successfully;
- release workflow run `37465590209` completed successfully;
- release assets:
  - `MailRuDesktop_Setup_v0.1.15.exe`;
  - `MailRuDesktop_Update_v0.1.15.exe`.

Final Owner protocol decision on 2026-10-06:

- primary Mail.ru API host is `https://aj-https.mail.ru`;
- `https://af.attachmail.ru` is explicitly allowed as an auxiliary runtime host; the currently verified use is downloading incoming attachments through `/cgi-bin/readmsg`;
- mobile `access_token` is the sole accepted mailbox credential;
- CAPTCHA/reCAPTCHA/additional interactive verification is reported to the user and authorization stops;
- no challenge solving;
- no `touch.mail.ru`, `e.mail.ru`, `account.mail.ru`, `auth.mail.ru`, `c.mail.ru`, or other sibling Mail.ru runtime hosts unless separately approved;
- the previous web-API-token research course is cancelled for product runtime.

Live evidence:

- GitHub Actions run `37410564363` executed the verified mobile auth request against `aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`;
- result: HTTP 200 JSON, top-level `oauth` and `status`, both `access_token` and `refresh_token` present, no CAPTCHA/reCAPTCHA signal for the test account.

v0.1.15 runtime state:

- mobile AJ auth restored as the only auth path;
- normal application requests no longer have an artificial fixed five-second delay; protocol requests remain serialized to avoid overlap;
- runtime host policy must allow `aj-https.mail.ru` and the explicitly approved `af.attachmail.ru`; other Mail.ru hosts remain rejected;
- endpoint catalog contains verified operations on approved hosts;
- web/touch session authenticator and CAPTCHA solver UI removed;
- saved sessions restore only when an AJ access token exists; legacy web/touch credentials are ignored and cleared on save;
- folder loading uses only `/api/v1/m/threads/status/smart`;
- outgoing attachment upload, immediate send, and scheduled send remain on verified AJ endpoints;
- full-message retrieval is wired through `aj-https.mail.ru/api/v1/messages/message`;
- read/unread is wired through `aj-https.mail.ru/api/v1/messages/marks`;
- move/archive/trash are wired through `aj-https.mail.ru/api/v1/messages/move`;
- incoming attachment download is wired through the approved `af.attachmail.ru/cgi-bin/readmsg` path;
- CI includes a source guard allowing only the explicitly approved Mail.ru runtime hosts;
- folder `last_modified` behavior remains unchanged by explicit Owner instruction;
- permanent delete, server contacts, server-side search and confirmed flag mutation remain unresolved;
- application version is 0.1.15.

Historical research status:

- branch `research/web-api-token` is non-authoritative research evidence only;
- it must not be merged into runtime and must not be used to reintroduce web/touch fallbacks without an explicit new Owner decision.

Next phase is owner runtime validation of the released v0.1.15, especially full-message rendering, attachment downloads, marks and move/archive/trash behavior. The five-second spacing rule remains in force for research/probes/tests only.
