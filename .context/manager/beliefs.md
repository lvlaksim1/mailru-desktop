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

## B-004 — technical baseline

statement: The implementation uses .NET 8 + WPF with protocol logic isolated in `MailRuDesktop.Protocol` and UI in `MailRuDesktop.App`. The repository also owns an evidence-ranked reverse API specification.

source: verified repository history.
authority: verified-repository
supersedes: none

## B-005 — initial protocol vertical slice

statement: The application implements the A-level protocol slice for mobile auth, smart thread status, attachment upload, send, and scheduled send.

source: product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.
authority: verified-ci
supersedes: none

## B-006 — compose UI is wired to verified transport

statement: The compose UI supports recipient, subject, body and multiple file attachments. Attachment uploads and immediate send use only locally verified A-level `aj-https.mail.ru` operations.

source: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.
authority: verified-ci
supersedes: none

## B-007 — installer and update policy

statement: First installation is distributed as `MailRuDesktop_Setup_vX.Y.Z.exe`; subsequent releases must provide `MailRuDesktop_Update_vX.Y.Z.exe` for in-place update without uninstall/reinstall.

source: direct Owner directive; release pipeline commit `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`.
authority: owner-directive + verified-repository
supersedes: no previous release packaging policy

## B-008 — v0.1.0 release

statement: GitHub Release `v0.1.0` was published with both full and update installers.

source: GitHub Release id `403897280`.
authority: verified-repository
supersedes: none

## B-009 — current runtime validates auth and folder 0

statement: Owner-operated MailRu Desktop v0.1.0 successfully authenticated against the implemented mobile-style auth flow and successfully loaded folder id 0 through `/api/v1/m/threads/status/smart`, returning status 200 and current mailbox data.

source: direct Owner runtime report and screenshot on 2026-10-05; sanitized observation stored at `docs/protocol/observations/2026-10-05-folder0.md`.
authority: verified-runtime
supersedes: none

## B-010 — current smart-thread response shape

statement: The current folder-0 response visibly includes `messages_total`, `messages_unread`, and message objects with at least `id`, `subject`, `snippet`, `date`, `size`, `folder`, `flags`, and `correspondents`; correspondents include from/to/cc/bcc collections, and visible flags include unread/flagged/attach/reply/forward.

source: Owner-provided current runtime screenshot; sanitized observation document.
authority: verified-runtime
supersedes: none

## B-011 — v0.1.1 structured mailbox release

statement: v0.1.1 adds a tolerant parser for the verified smart-thread response, a structured mailbox table and metadata/snippet preview, raw JSON diagnostics, a project-owned cloud/envelope application mark based on the Owner-approved visual direction, and an assembly-derived version in the native window title. CI run `37342048282` and release workflow run `37342356199` both completed successfully.

source: commits `065748b056f47fefba0972157078e8a5064037fc` and `45204412fe8394e403eb46d8916961c9d75d86a7`; GitHub Release `v0.1.1`.
authority: verified-ci + verified-repository
supersedes: none
