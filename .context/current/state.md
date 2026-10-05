# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.9`

Verified release evidence:

- product CI run `37390492256` — success
- release workflow `37390587718` — success
- release assets: `MailRuDesktop_Setup_v0.1.9.exe` and `MailRuDesktop_Update_v0.1.9.exe`
- GitHub Actions artifacts: none
- older binary Releases pruned; source tags retained

v0.1.9 auth/challenge correction:

- removed the preliminary mobile/OAuth auth probe that still ran before the Hackus flow in v0.1.8;
- explicit login now starts directly with the Hackus Reset() -> CreateSession() sequence;
- fixed iPhone Safari/GSA User-Agent remains identical to Hackus;
- CreateSession uses POST aj-https.mail.ru/cgi-bin/auth with Login/Password, auto-redirect disabled, and Hackus Location ordering;
- reCAPTCHA uses GetReCaptchaSiteKey in the original HTTP/cookie session;
- manual reCAPTCHA solver no longer loads the real Mail.ru login/challenge page;
- instead WebView2 serves a minimal local solver page through virtual secure origin https://account.mail.ru, using only websiteKey + websiteURL semantics equivalent to Hackus RecaptchaV2TaskProxyless;
- the solver page returns only g-recaptcha-response; it cannot consume the token through Mail.ru login JavaScript;
- that token is passed to the same original CookieContainer and same CreateSession(token) continuation;
- obsolete browser-session cookie transfer fields/paths were removed;
- a second reCAPTCHA returned by CreateSession(token) is treated as terminal recaptcha_rejected, matching Hackus state-machine boundary rather than recursively opening browser challenges;
- classic CAPTCHA remains GetVerificationType -> GET c.mail.ru/c/6 -> manual answer -> POST user/copper -> CreateSessionByLink;
- auth-related Mail.ru HTTP calls remain spaced by at least five seconds per owner safety rule.

v0.1.9 installer/theme changes:

- Setup and Update installers expose an optional desktop-shortcut task;
- theme is centralized in application-level DynamicResource palette plus implicit styles for standard WPF controls;
- WPF SystemColors brush keys are redirected to the active palette, so future standard controls inherit the theme even without bespoke styling;
- dark/light/system theme now covers windows, title bars, buttons, text inputs, combo/list/grid/tab/date/tree/menu/tooltip/scroll/progress controls, challenge window and message reader;
- system-theme mode re-evaluates when Windows settings/app activation changes;
- native window title bars use immersive dark mode where supported;
- message WebView rerenders when effective theme changes.

Mailbox/UI functionality from previous releases remains in place.
