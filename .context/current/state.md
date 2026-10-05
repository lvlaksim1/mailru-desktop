# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Repo Factory bootstrap is complete with Project Manager v2 and Context Capsule installed.

Current product baseline:

- commit: `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`
- stack: .NET 8 + WPF
- protocol assembly: `src/MailRuDesktop.Protocol`
- application shell: `src/MailRuDesktop.App`
- reverse API registry: `docs/protocol/README.md`
- CI: run `37335195622` completed successfully on Windows

Implemented locally verified operations:

- `POST aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`
- `GET aj-https.mail.ru/api/v1/m/threads/status/smart`
- `POST aj-https.mail.ru/api/v1/messages/attaches/add`
- `POST aj-https.mail.ru/api/v1/messages/send`
- `POST aj-https.mail.ru/api/v1/messages/schedule`

The UI currently supports authentication and loading a folder as raw thread-status JSON. Send/schedule/attachment operations exist in the protocol layer but are not yet exposed through the UI.
