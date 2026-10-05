# MailRu Desktop

A native Windows desktop mail client built from scratch around reverse-engineered internal Mail.ru APIs.

## Project rules

- No IMAP/SMTP.
- No app-specific passwords.
- Official Mail.ru mail-client documentation is not a protocol source for this project.
- Protocol truth comes from observed traffic, verified working legacy code, and independently reverse-engineered implementations.
- Third-party projects are research sources only; their code is not copied into this product.
- Secrets, passwords, access tokens, mailbox contents, captures, and binary build artifacts must never be committed.

## Architecture

The implementation uses **.NET 8 + WPF**.

- `MailRuDesktop.App` — native Windows UI.
- `MailRuDesktop.Protocol` — isolated Mail.ru transport/protocol layer.
- `docs/protocol` — evidence-backed reverse API specification.

The UI is intentionally thin. Mail.ru protocol behavior must stay testable without WPF.

## Current vertical slice

Implemented in the protocol layer:

- mobile-style authentication through `aj-https.mail.ru/cgi-bin/auth`;
- folder/thread status through `/api/v1/m/threads/status/smart`;
- tolerant parsing of the currently observed smart-thread response;
- attachment upload through `/api/v1/messages/attaches/add`;
- send through `/api/v1/messages/send`;
- server-side scheduled send through `/api/v1/messages/schedule`.

Current UI:

- login using the verified mobile-style auth flow;
- structured mailbox list for a numeric folder id;
- sender, subject, date, size, unread/star/attachment indicators;
- metadata/snippet preview for the selected item;
- expandable raw JSON diagnostics for reverse-engineering;
- compose with recipient, subject and body;
- multi-file attachment upload;
- immediate send using the verified compose endpoint;
- version shown in the native window title;
- project-owned cloud/envelope application mark.

The password is never persisted. The access token currently lives in process memory only; Windows-protected token persistence is a planned increment.

## Releases

- First installation: `MailRuDesktop_Setup_vX.Y.Z.exe`
- Normal upgrades: `MailRuDesktop_Update_vX.Y.Z.exe`

Updates install over the current-user installation and preserve user data.

## Build

Requirements:

- Windows 10/11
- .NET 8 SDK

```powershell
dotnet build src/MailRuDesktop.App/MailRuDesktop.App.csproj -c Release
```

CI performs restore/build only and uploads no unnecessary artifacts.

## Status

Early development. The internal Mail.ru API is undocumented and may change. Every endpoint is tracked with an evidence level in `docs/protocol/README.md`.
