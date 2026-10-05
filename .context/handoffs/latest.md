# Latest handoff

Published MailRu Desktop `v0.1.9`.

Auth correction after owner reported v0.1.8 CAPTCHA still failed:

- v0.1.8 was not yet fully equivalent to Hackus because MailRu Desktop still performed a separate mobile/OAuth auth request before Hackus Login(), and the manual reCAPTCHA was solved inside Mail.ru's own challenge page.
- v0.1.9 removes both differences.
- authentication begins directly with Hackus-style Reset() -> CreateSession();
- GetReCaptchaSiteKey is performed in the same cookie session;
- WebView2 is now only a manual replacement for RecaptchaV2TaskProxyless: a minimal page is served at virtual secure origin https://account.mail.ru with the extracted sitekey;
- no Mail.ru login JavaScript runs in that solver page, so g-recaptcha-response cannot be consumed before the application receives it;
- token is fed directly to the original same-session CreateSession(token);
- obsolete browser cookie export/import challenge fields were removed;
- repeated reCAPTCHA after token submission is surfaced as `recaptcha_rejected` instead of recursively reopening windows.

Classic CAPTCHA remains the Hackus sequence:
GetVerificationType -> c.mail.ru/c/6 -> manual text answer -> user/copper -> CreateSessionByLink.

Theme/install changes:
- optional “Create desktop icon” task exists in both Setup and Update;
- theme architecture is global and resource-driven rather than per-control patching;
- standard WPF control families and SystemColors are mapped to the active palette;
- Windows title bars and message WebView follow the selected/effective theme;
- System mode reacts to Windows theme changes.

Release:
- Update: `MailRuDesktop_Update_v0.1.9.exe`
- Setup: `MailRuDesktop_Setup_v0.1.9.exe`
- CI `37390492256`: success
- release workflow `37390587718`: success
- Actions artifacts: none
- only binary Release retained: v0.1.9

Acceptance focus: expert.sout@mail.ru reCAPTCHA, desktop shortcut checkbox, and complete dark-theme coverage.
