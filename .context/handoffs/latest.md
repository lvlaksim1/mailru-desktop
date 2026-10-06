# Latest handoff

MailRu Desktop v0.1.13 is now released and `main` is authoritative.

Release state:

- PR #18 was merged into `main`;
- product commit: `6c5638ae8fa85ca9db63378bec8531d8356fe692`;
- GitHub Release: `v0.1.13`;
- existing installations use `MailRuDesktop_Update_v0.1.13.exe`;
- first install/recovery uses `MailRuDesktop_Setup_v0.1.13.exe`.

Authoritative runtime rule:

`https://aj-https.mail.ru` is the only allowed Mail.ru API host.

Authorization:

`POST /cgi-bin/auth?mp=android&udid=mailru_app`
with `oauth2=1`, `useragent=android`, `mobile=1`, `mob_json=1`, `simple=1`.

Success requires `access_token`. If CAPTCHA/reCAPTCHA/additional verification appears, notify the user and stop; do not solve it and do not fall back to web/touch authorization.

Controlled live probe `37410564363` confirmed the test account currently receives HTTP 200 JSON containing both access and refresh tokens.

Active AJ operations:

- folder/thread status: `/api/v1/m/threads/status/smart`;
- outgoing attachment upload: `/api/v1/messages/attaches/add`;
- immediate send: `/api/v1/messages/send`;
- scheduled send: `/api/v1/messages/schedule`.

Until AJ equivalents are verified, full-message retrieval, incoming attachment download, contacts server lookup, move/archive and permanent delete are disabled.

Historical note:

- `research/web-api-token` contains obsolete exploratory web/touch auth research;
- it is evidence only and is not an allowed product runtime path;
- do not merge or revive it without an explicit Owner reversal.

Immediate work:

1. owner runtime-validation of v0.1.13;
2. delayed-send validation;
3. AJ-only discovery of the missing message operations;
4. maintain at least five seconds between project network requests.

Version: 0.1.13
