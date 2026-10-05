# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.1`

Verified build/release evidence:

- structured-mailbox product commit: `065748b056f47fefba0972157078e8a5064037fc`
- observation/docs commit: `45204412fe8394e403eb46d8916961c9d75d86a7`
- product CI: `37342048282` — success
- release workflow: `37342356199` — success

v0.1.1 UI now includes:

- successful mobile-style authentication;
- folder loading through A-level `m/threads/status/smart`;
- structured message list with sender, subject, date, size and indicators;
- selected-message metadata/snippet preview;
- expandable raw JSON diagnostics;
- compose and multi-file attachment send;
- cloud/envelope application mark;
- version in native window title.

Current runtime evidence from the Owner confirms v0.1.0 authentication and folder 0 retrieval work against live Mail.ru as of 2026-10-05.

Full message-body retrieval is intentionally not enabled yet because candidate read endpoints have not been revalidated against current traffic.

Password persistence remains absent. Access token is still process-memory only.

Release policy remains: full Setup for first install/recovery; Update installer for normal upgrades.
