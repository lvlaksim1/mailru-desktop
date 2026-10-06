# Latest handoff

MailRu Desktop is being moved from the v0.1.12 Hackus touch/web architecture to owner-mandated **AJ-only** v0.1.13.

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

Implementation branch: `release/0.1.13-aj-only`
PR: #18
Version: 0.1.13
