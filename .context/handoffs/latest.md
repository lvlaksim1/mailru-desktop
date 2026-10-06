# Latest handoff

MailRu Desktop v0.1.14 is released and `main` is authoritative.

Release state:

- GitHub Release: `v0.1.14`;
- existing installations use `MailRuDesktop_Update_v0.1.14.exe`;
- first install/recovery uses `MailRuDesktop_Setup_v0.1.14.exe`.

Allowed runtime hosts:

- primary API host: `https://aj-https.mail.ru`;
- explicitly approved auxiliary host: `https://af.attachmail.ru`;
- current verified auxiliary use: incoming attachment download through `/cgi-bin/readmsg`;
- other Mail.ru hosts remain disallowed unless separately approved by the Owner.

Authorization remains AJ mobile auth through `/cgi-bin/auth?mp=android&udid=mailru_app`. Success requires `access_token`. CAPTCHA/reCAPTCHA/additional verification stops authorization.

Verified message operations from Owner-provided VBA:

- folder/thread status: `aj-https.mail.ru/api/v1/m/threads/status/smart`;
- full message: `aj-https.mail.ru/api/v1/messages/message`;
- unread/read marks: `aj-https.mail.ru/api/v1/messages/marks`;
- move/archive/trash: `aj-https.mail.ru/api/v1/messages/move`;
- outgoing attachment upload: `aj-https.mail.ru/api/v1/messages/attaches/add`;
- immediate send: `aj-https.mail.ru/api/v1/messages/send`;
- scheduled send: `aj-https.mail.ru/api/v1/messages/schedule`;
- incoming attachment download: `af.attachmail.ru/cgi-bin/readmsg`.

Still unresolved for permitted hosts:

- permanent delete;
- server contacts/address book;
- server-side search;
- confirmed flag mutation.

Runtime pacing:

- no artificial fixed five-second delay in normal application requests;
- protocol requests remain serialized;
- `last_modified` remains unchanged;
- five-second spacing remains for project research/probes/tests.

Immediate work:

1. validate v0.1.14 Inbox refresh speed;
2. wire the verified full-message, marks, move/archive/trash operations;
3. wire incoming attachment download through the explicitly approved `af.attachmail.ru`;
4. continue research only for the still unresolved functions.

Version: 0.1.14
