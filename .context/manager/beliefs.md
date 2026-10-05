# Manager beliefs

## B-001 — repository bootstrap

statement: The project repository is `lvlaksim1/mailru-desktop`, public, created through `lvlaksim1/repo-factory` with the `project-manager` profile and without standard Telegram secrets.

source: Repo Factory issue #182 and live repository metadata.
authority: verified-repository
supersedes: none

## B-002 — owner protocol constraints

statement: This project must not use IMAP/SMTP, app-specific passwords, or official Mail.ru mail-client documentation as protocol authority. The protocol source of truth is reverse-engineered internal Mail.ru HTTP behavior, starting with `aj-https.mail.ru`, supported by locally verified legacy code, traffic captures, and independent reverse-engineering evidence.

source: direct Owner directives in the active project conversation.
authority: owner-directive
supersedes: any generic mail-client assumption that IMAP/SMTP is an acceptable fallback

## B-003 — implementation approach

statement: The product is implemented from scratch as a native Windows application. Third-party projects are research/evidence sources for practices and endpoint discovery; their product code is not copied.

source: direct Owner directive; product history on `main`.
authority: owner-directive + verified-repository
supersedes: prior exploratory idea of forking an existing mail client

## B-004 — initial technical baseline

statement: The current implementation uses .NET 8 + WPF with protocol logic isolated in `MailRuDesktop.Protocol` and UI in `MailRuDesktop.App`. The repository also owns an evidence-ranked reverse API specification.

source: product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`.
authority: verified-repository
supersedes: none

## B-005 — first vertical slice verified by CI

statement: Product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af` implements the A-level protocol slice for mobile auth, smart thread status, attachment upload, send, and scheduled send; GitHub Actions Windows CI run `37335195622` completed successfully.

source: product commit and CI run.
authority: verified-ci
supersedes: none

## B-006 — compose UI is wired to verified transport

statement: Product commit `1420d1619c16deb2dce150cd8882ee86431b2465` adds a compose UI for recipient, subject, body and multiple file attachments. Attachment uploads and immediate send use only locally verified A-level `aj-https.mail.ru` operations. CI run `37335834260` completed successfully.

source: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.
authority: verified-ci
supersedes: none

## B-007 — installer and update policy is implemented

statement: First installation is distributed as `MailRuDesktop_Setup_vX.Y.Z.exe`; subsequent releases must provide `MailRuDesktop_Update_vX.Y.Z.exe` for in-place update without uninstall/reinstall. Both are produced by the repository release workflow and published as GitHub Release assets.

source: direct Owner directive; repository commit `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`; GitHub Release `v0.1.0`.
authority: owner-directive + verified-repository
supersedes: no previous release packaging policy

## B-008 — v0.1.0 release published

statement: GitHub Release `v0.1.0` is published with a full installer and update installer. The full installer asset is `MailRuDesktop_Setup_v0.1.0.exe` (51,038,338 bytes, SHA-256 `4be3fbd03c72b55dbfdd1999d44b8834c988f90551c14631db36639366aa821c`). The update installer asset is `MailRuDesktop_Update_v0.1.0.exe` (51,038,697 bytes, SHA-256 `978c9753a3237668bcdeaa6d706a931d25872a3f8041fe6df8bc24bfd253bf43`).

source: GitHub Release API for release id `403897280`.
authority: verified-repository
supersedes: none
