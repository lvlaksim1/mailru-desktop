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

source: direct Owner directive; product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`.
authority: owner-directive + verified-repository
supersedes: prior exploratory idea of forking an existing mail client

## B-004 — initial technical baseline

statement: The current implementation uses .NET 8 + WPF with protocol logic isolated in `MailRuDesktop.Protocol` and UI in `MailRuDesktop.App`. The repository also owns an evidence-ranked reverse API specification.

source: product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`.
authority: verified-repository
supersedes: none

## B-005 — first vertical slice verified by CI

statement: The first product commit implements the A-level protocol slice for mobile auth, smart thread status, attachment upload, send, and scheduled send; GitHub Actions Windows CI restored and built the application successfully.

source: product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.
authority: verified-ci
supersedes: none
