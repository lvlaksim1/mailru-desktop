# Latest handoff

Published MailRu Desktop `v0.1.8`.

Critical change: auth/challenge is now implemented as the Hackus state machine, with only the external CAPTCHA solver replaced by manual user solving.

reCAPTCHA:
- CreateSession receives a recaptcha Location;
- GetReCaptchaSiteKey is executed in the same HTTP/cookie session;
- WebView2 is used only to let the user solve reCAPTCHA;
- injected code intercepts g-recaptcha-response immediately, prevents the browser form from continuing, closes the solver window, and returns the token to the original pending session;
- the same CreateSession(token) POST is then repeated with the same CookieContainer.

Classic CAPTCHA:
- GetVerificationType checks account.mail.ru/api/v1/user/copper;
- CAPTCHA image is fetched from c.mail.ru/c/6;
- user enters the text manually;
- answer is posted to user/copper;
- returned URL is followed through CreateSessionByLink and must redirect to inbox.

Removed:
- alternate interactive full-browser login path;
- browser-cookie export/merge as an authorization mechanism;
- arbitrary three-round challenge limit.

Release:
- Update: `MailRuDesktop_Update_v0.1.8.exe`
- Setup: `MailRuDesktop_Setup_v0.1.8.exe`
- CI `37385473466`: success
- release workflow `37385570525`: success
- Actions artifacts: none

Acceptance focus: `expert.sout@mail.ru` reCAPTCHA must return into CreateSession(token) and load mailbox data without the browser entering Inbox itself.
