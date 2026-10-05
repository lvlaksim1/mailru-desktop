# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.6`

Verified release evidence:

- product CI for v0.1.6 head `40d032d583773d17ed2d560edf87ef446cad493e` — success
- release workflow `37375038704` — success
- release assets: `MailRuDesktop_Setup_v0.1.6.exe` and `MailRuDesktop_Update_v0.1.6.exe`
- GitHub Actions artifacts: none
- older binary Releases pruned; source tags retained

v0.1.6 product state:

- user-visible touch-session dependency removed from normal UX;
- full-message reading prefers e.mail.ru web token/cookies and falls back to touch internally;
- full-message parser supports nested web body shapes and attachment metadata;
- attachment download prefers web-session cookies and preserves original filename/MIME;
- message move/delete prefer e.mail.ru web-session operations, with touch fallback;
- filters are integrated above the message list; separate Search tab removed;
- permanent delete button is absent outside Trash; the Trash button becomes “Удалить навсегда” inside folder 500002;
- account UI is now saved-account selector + “Добавить аккаунт” modal login/password form;
- large in-window MailRu Desktop title removed;
- Settings tab added with System/Light/Dark theme persisted across restarts;
- WebView reader explicitly follows selected theme and no longer defaults to an unexpected black background in light mode;
- CAPTCHA/challenge classification precedes generic invalid-password classification;
- disputed aj/web invalid-password results after mobile token-missing fall back to interactive real Mail.ru browser login instead of reporting a false bad password;
- interactive login/challenge uses a fresh isolated WebView2 session and supports CAPTCHA/2FA in the real Mail.ru flow;
- passwords remain unsaved; durable authorization state remains DPAPI-protected.

Runtime data: `%LOCALAPPDATA%\MailRuDesktop`.
