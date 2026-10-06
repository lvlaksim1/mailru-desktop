# Latest handoff

MailRu Desktop v0.1.15 is released and `main` is authoritative.

Release state:

- GitHub Release: `v0.1.15`;
- release target commit: `e87a47fcdc4f107f2b22095ec7b77a0a3eba20be`;
- existing installations use `MailRuDesktop_Update_v0.1.15.exe`;
- first install/recovery uses `MailRuDesktop_Setup_v0.1.15.exe`;
- CI run `37465331504` and release workflow run `37465590209` succeeded.

Allowed runtime hosts:

- primary API host: `https://aj-https.mail.ru`;
- explicitly approved auxiliary host: `https://af.attachmail.ru`;
- other Mail.ru hosts remain disallowed unless separately approved by the Owner.

Authorization remains AJ mobile auth through `/cgi-bin/auth?mp=android&udid=mailru_app`. Success requires `access_token`. CAPTCHA/reCAPTCHA/additional verification stops authorization.

Released message operations:

- folder/thread status: `aj-https.mail.ru/api/v1/m/threads/status/smart`;
- full message: `aj-https.mail.ru/api/v1/messages/message`;
- unread/read marks: `aj-https.mail.ru/api/v1/messages/marks`;
- move/archive/trash: `aj-https.mail.ru/api/v1/messages/move`;
- outgoing attachment upload: `aj-https.mail.ru/api/v1/messages/attaches/add`;
- immediate send: `aj-https.mail.ru/api/v1/messages/send`;
- scheduled send: `aj-https.mail.ru/api/v1/messages/schedule`;
- incoming attachment download: `af.attachmail.ru/cgi-bin/readmsg`.

Still unresolved:

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

1. owner runtime validation of v0.1.15;
2. validate full-message rendering, attachment download, marks, move/archive/trash;
3. continue research only for the unresolved functions above.

Version: 0.1.15
