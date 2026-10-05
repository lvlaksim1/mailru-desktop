# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.8`

Verified release evidence:

- product CI run `37385473466` — success
- release workflow `37385570525` — success
- release assets: `MailRuDesktop_Setup_v0.1.8.exe` and `MailRuDesktop_Update_v0.1.8.exe`
- GitHub Actions artifacts: none
- older binary Releases pruned; source tags retained

v0.1.8 authorization/challenge state:

- the auth/challenge state machine now follows Hackus ordering and endpoints instead of using a browser-login architecture;
- every explicit Hackus login starts with a fresh CookieContainer, matching Hackus Reset();
- fixed iPhone Safari/GSA User-Agent matches Hackus;
- CreateSession uses POST aj-https.mail.ru/cgi-bin/auth with Login/Password, auto-redirect disabled, and classifies Location in Hackus order: user/login?login, recaptcha, fail, recovery/ukey, inbox, unknown;
- reCAPTCHA branch runs GetReCaptchaSiteKey against the returned challenge URL, then WebView2 acts only as a manual replacement for Hackus captcha solver;
- WebView2 intercepts g-recaptcha-response immediately and blocks its own form continuation; the token is passed back to the original HTTP session and the same CreateSession(token) is repeated with the same CookieContainer;
- no browser cookies are exported back as an alternative login mechanism;
- classic CAPTCHA branch runs GetVerificationType -> GET c.mail.ru/c/6 -> manual text entry -> POST account.mail.ru/api/v1/user/copper -> CreateSessionByLink;
- invalid classic CAPTCHA restarts with a fresh cookie container, matching Hackus outer Login()/Reset() behavior;
- real TwoFactor remains a terminal TwoFactor state, as in Hackus;
- recovery/ukey are classified as Blocked;
- successful Login is followed first by touch GetSearchToken, then MailRu Desktop derives its additional web token;
- challenge flow has no arbitrary three-round UI limit;
- auth-related Mail.ru requests remain spaced by at least five seconds per owner safety rule.

The v0.1.6/v0.1.7 mailbox/UI work remains: web-first message operations, integrated filters, themes, modal Add Account, attachment handling and Trash-only permanent delete.
