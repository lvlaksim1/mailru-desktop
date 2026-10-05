# MailRu Desktop

A native Windows desktop mail client built from scratch around reverse-engineered internal Mail.ru APIs.

## Project rules

- No IMAP/SMTP.
- No app-specific passwords.
- Official Mail.ru mail-client documentation is not a protocol source for this project.
- Protocol truth comes from observed traffic, verified working legacy code, and independently reverse-engineered implementations.
- Third-party projects are research sources only; their code is not copied into this product.
- Secrets, passwords, access tokens, mailbox contents, captures, and generated binary build artifacts must never be committed.
- Repository history must contain source, protocol evidence and genuinely required project assets only.
- GitHub Actions must not upload build artifacts. Release binaries are published only as release assets.
- Only the newest binary GitHub Release is retained; older source tags may remain because they are tiny and preserve provenance.

## Architecture

The implementation uses **.NET 8 + WPF**.

- `MailRuDesktop.App` — native Windows UI.
- `MailRuDesktop.Protocol` — isolated Mail.ru transport/protocol layer.
- `docs/protocol` — evidence-backed reverse API specification.

The UI is intentionally thin. Mail.ru protocol behavior must stay testable without WPF.

## Current vertical slice

Implemented in the protocol layer:

- mobile-style authentication through `aj-https.mail.ru/cgi-bin/auth`;
- Hackus-derived cookie-session fallback when the mobile response has no `access_token`;
- touch/web token derivation for cookie sessions;
- folder/thread status through `/api/v1/m/threads/status/smart`;
- web-thread and touch-search fallbacks for accounts without a mobile access token;
- tolerant parsing of the currently observed smart-thread response;
- attachment upload through `/api/v1/messages/attaches/add`;
- send through `/api/v1/messages/send`;
- server-side scheduled send through `/api/v1/messages/schedule`.

Current UI:

- login using the verified mobile-style auth flow with cookie-session fallback;
- saved-account selector with Windows-protected authorization state;
- real folder sidebar for the verified smart response;
- sender, subject, date, size, unread/star/attachment indicators;
- metadata/snippet preview for the selected item;
- expandable raw JSON diagnostics for reverse-engineering;
- compose with recipient, subject and body;
- multi-file attachment upload;
- immediate send using the verified compose endpoint;
- version shown in the native window title;
- project-owned cloud/envelope application mark.

The password is never persisted. Access, refresh, web and touch tokens plus session cookie headers are protected with Windows DPAPI for the current Windows user and survive application restarts and update installs.

## Installation and uninstall hygiene

The application is a per-user install and does not require administrator rights.

- Program files: `%LOCALAPPDATA%\Programs\MailRuDesktop`
- Start-menu shortcut: the current user's Start Menu Programs area.
- Runtime data root: `%LOCALAPPDATA%\MailRuDesktop`
- Compatibility cleanup also covers `%APPDATA%\MailRuDesktop`
- Canonical app-owned registry root: `HKCU\Software\MailRuDesktop`

The application must not write mutable runtime state into the program directory. Tokens, settings, cache and logs must live only under the application-owned data root above.

Uninstall removes application-installed files and shortcuts, recursively removes both application-owned data roots, and removes the application-owned registry key. Automatic installer logging is disabled so ordinary installs do not leave an Inno Setup log in the user's temp directory.

Windows itself may retain OS-managed forensic/history data such as Prefetch, event logs, download/browser history, or a shortcut the user manually pinned. Those are not application-owned data and are not deleted by the uninstaller.

## Releases

- First installation: `MailRuDesktop_Setup_vX.Y.Z.exe`
- Normal upgrades: `MailRuDesktop_Update_vX.Y.Z.exe`

Updates install over the current-user installation and preserve application data. Each new release automatically deletes older GitHub Release objects and their large binary assets while preserving the old Git tags.

## Build

Requirements:

- Windows 10/11
- .NET 8 SDK

```powershell
dotnet build src/MailRuDesktop.App/MailRuDesktop.App.csproj -c Release
```

CI performs restore/build only and uploads no workflow artifacts. Publish/dist outputs and common binary/archive formats are ignored by Git.

## Status

Early development. The internal Mail.ru API is undocumented and may change. Every endpoint is tracked with an evidence level in `docs/protocol/README.md`.
