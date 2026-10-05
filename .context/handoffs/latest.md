# Latest handoff

Published MailRu Desktop `v0.1.7`.

Critical correction:

- v0.1.6 incorrectly treated WebView2 challenge completion as a separate browser session and then created another HTTP client to derive tokens.
- v0.1.7 keeps the original Mail.ru HTTP cookie session alive throughout the whole challenge flow.
- the pending session stores the original login/password only in memory, the original `CookieContainer`, handler and HTTP client;
- challenge cookies are merged back into that original cookie jar;
- reCAPTCHA attempts to capture `g-recaptcha-response` and repeats the original `aj-https.mail.ru/cgi-bin/auth` POST with the same cookies, which mirrors Hackus after its solver returns a token;
- only after the same session yields working web/touch credentials is authorization considered complete;
- repeated challenge rounds are supported.

Release:
- Update: `MailRuDesktop_Update_v0.1.7.exe`
- Setup: `MailRuDesktop_Setup_v0.1.7.exe`
- CI `37379908035`: success
- release workflow `37380041150`: success
- Actions artifacts: none

Acceptance focus: `expert.sout@mail.ru` must load mailbox data immediately after manual CAPTCHA completion.
