# Latest handoff

This is a convenience/emergency summary, not the primary manager continuity mechanism.

## Last completed work

Created and published the first Windows release channel.

Release-pipeline commit: `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`.

GitHub Release `v0.1.0` contains:

- `MailRuDesktop_Setup_v0.1.0.exe` — full first-install package;
- `MailRuDesktop_Update_v0.1.0.exe` — in-place update package.

The installer targets `%LOCALAPPDATA%\Programs\MailRuDesktop` and does not require administrator rights for normal installation. The update package refuses to act as a first installer when the application is absent.

## Verified current state

The application compiles on Windows. The compose flow is wired only to A-level verified endpoints. Externally discovered search/move/flags/folder/message routes remain disabled pending local traffic verification.

The project intentionally does not use IMAP/SMTP or app-specific passwords and does not treat official Mail.ru mail-client documentation as protocol authority.

The Owner requires subsequent updates to be delivered as update installers rather than requiring full reinstall.

## Next operation

Implement typed smart-thread parsing and structured mailbox rendering, then ingest fresh traffic evidence to validate full-message and mutation endpoint families. Preserve the established update-installer release path for every new version.
