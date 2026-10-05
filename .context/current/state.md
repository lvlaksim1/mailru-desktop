# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.4`

Verified release evidence:

- release tag: `v0.1.4` -> product commit `0d97964959ef7069f43f369e2a1adee8f2c88a5a`
- release workflow: `37367733108` — success
- release assets: `MailRuDesktop_Setup_v0.1.4.exe` and `MailRuDesktop_Update_v0.1.4.exe`
- GitHub Actions workflow artifacts for the release: none
- older binary GitHub Releases are pruned automatically; source tags remain.

v0.1.4 product state:

- explicit authorization state machine: success, invalid credentials, reCAPTCHA, CAPTCHA, 2FA, Blocked, recovery, network/protocol/unknown states;
- interactive challenge handling uses an isolated WebView2 window and requires the user to complete Mail.ru verification manually;
- challenge browser uses a fresh ephemeral profile/cookie-session for each new login attempt;
- fixed Mail.ru web/touch User-Agent is the Hackus-observed mobile Safari/GSA profile on iPhone;
- unknown authorization results are stored as sanitized diagnostics without persisting password, token or cookie secrets;
- mobile `aj-https.mail.ru` access-token auth remains the primary locally verified path;
- cookie-session auth can derive web and touch tokens and survive restart/update through DPAPI-protected state;
- full-message touch API is wired to the reading pane;
- incoming attachment metadata preserves original filename and MIME type;
- incoming attachments can be downloaded directly from Mail.ru-provided download URLs;
- touch server search is exposed in the UI;
- Mail.ru contacts/address-book API is exposed for recipient selection/autocomplete support;
- Trash/move and permanent remove operations are exposed;
- message list, real folders, compose/send and outgoing attachment upload from earlier releases remain available.

Evidence discipline:

- owner/VBA-verified `aj` operations remain A-level;
- Hackus-derived touch/web operations remain B-level until owner runtime testing reproduces them on current Mail.ru;
- v0.1.4 intentionally exposes these B-level operations for controlled owner testing, with diagnostics retained for mismatches.

Runtime data lives under `%LOCALAPPDATA%\MailRuDesktop`; updates preserve it and uninstall removes application-owned runtime data.
