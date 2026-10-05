# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Repo Factory bootstrap is complete with Project Manager v2 and Context Capsule installed.

Current product baseline:

- product commit: `1420d1619c16deb2dce150cd8882ee86431b2465`
- stack: .NET 8 + WPF
- protocol assembly: `src/MailRuDesktop.Protocol`
- application shell: `src/MailRuDesktop.App`
- reverse API registry: `docs/protocol/README.md`
- latest product CI: run `37335834260`, success

Implemented locally verified operations:

- `POST aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`
- `GET aj-https.mail.ru/api/v1/m/threads/status/smart`
- `POST aj-https.mail.ru/api/v1/messages/attaches/add`
- `POST aj-https.mail.ru/api/v1/messages/send`
- `POST aj-https.mail.ru/api/v1/messages/schedule`

Current UI:

- login;
- raw smart folder/thread retrieval for protocol inspection;
- compose with recipient, subject and body;
- multi-file attachment selection/upload;
- immediate send.

Password persistence is intentionally absent. Access token is currently process-memory only. Scheduled-send transport exists but does not yet have end-user UI because the exact modern `send_date` representation remains unverified.
