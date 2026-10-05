# Latest handoff

Published MailRu Desktop `v0.1.6`.

Key changes requested by owner:

- no permanent login/password controls in main window; use saved account selector + “Добавить аккаунт” dialog;
- removed duplicate in-window app title;
- Settings tab with persistent System / Light / Dark themes;
- fixed reader background to follow selected theme;
- separate Search tab removed; filtering controls moved above message list;
- normal UI no longer exposes “touch session” as a user concept;
- full-message read now prefers web token/cookies and supports current nested body/attachment shapes;
- incoming attachments preserve original name/MIME and download using authenticated web cookies;
- move/delete prefer web-session APIs, touch retained only as internal fallback;
- “Удалить навсегда” appears only in Trash via the same dynamic Trash button;
- challenge/CAPTCHA states are evaluated before generic invalid-password text;
- when aj token-missing conflicts with an auxiliary invalid-password result, the app falls back to real interactive Mail.ru browser login, allowing CAPTCHA/2FA instead of falsely rejecting the password.

Release:
- Update: `MailRuDesktop_Update_v0.1.6.exe`
- Setup: `MailRuDesktop_Setup_v0.1.6.exe`
- workflow `37375038704`: success
- Actions artifacts: none
- older binary Releases pruned

Acceptance focus: `expert.sout@mail.ru` CAPTCHA flow, full-message body, incoming attachment display/download, filters, Trash/delete behavior and theme switching.
