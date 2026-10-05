# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Repo Factory bootstrap is complete with Project Manager v2 and Context Capsule installed.

Current product/release baseline:

- product UI commit: `1420d1619c16deb2dce150cd8882ee86431b2465`
- release-pipeline commit: `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`
- current public release: `v0.1.0`
- stack: .NET 8 + WPF
- protocol assembly: `src/MailRuDesktop.Protocol`
- application shell: `src/MailRuDesktop.App`
- reverse API registry: `docs/protocol/README.md`

Release assets:

- full installer: `MailRuDesktop_Setup_v0.1.0.exe`
- update installer: `MailRuDesktop_Update_v0.1.0.exe`
- release URL: `https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.1.0`

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

Release policy is now durable: first install via Setup; subsequent versions must expose an in-place Update installer and preserve user settings/data.
