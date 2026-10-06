# Project constraints

Owner directives:

- Ignore official Mail.ru mail-client documentation as protocol authority for this project.
- Do not use IMAP or SMTP.
- Do not use special/app-specific passwords.
- Build the application from scratch.
- Use good practices and lessons from other projects without copying their product identity or implementation.
- Prefer observed traffic, verified working legacy code, official-client APK reverse engineering and reproducible runtime evidence over assumptions.
- Never commit real passwords, access/refresh tokens, mailbox content, private captures or other secrets.
- Keep the GitHub repository public unless the Owner explicitly changes that policy.
- Do not accumulate unnecessary build artifacts in Git.

Current access-token scope:

- Do not restrict eligible internal Mail.ru APIs by hostname alone.
- An operation is in current scope when official-client evidence shows it uses the same mailbox OAuth credential `ru.mail.oauth2.access` obtained by the product authorization flow.
- The token may be carried as `access_token`, under another parameter name, or in an authorization header.
- Mechanisms requiring an independent credential/session are out of current scope unless the Owner explicitly changes the rule.
- CAPTCHA/reCAPTCHA/additional interactive verification during authorization must be reported and authorization stopped; do not solve or bypass the challenge.
- Static APK confirmation is implementation evidence; newly discovered operations require controlled live validation before default product enablement.
- Preserve at least five seconds between research/probe/test network requests. This fixed delay does not belong in normal application runtime.

Release/install policy:

- First installation: `MailRuDesktop_Setup_vX.Y.Z.exe`.
- Updates: `MailRuDesktop_Update_vX.Y.Z.exe` without uninstall/reinstall.
- Install under `%LOCALAPPDATA%\Programs\MailRuDesktop` and preserve user settings/data across updates.
- GitHub Release assets tied to immutable version tags are authoritative distributables.
