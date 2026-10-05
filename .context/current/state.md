# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.7`

Verified release evidence:

- product CI run `37379908035` — success
- release workflow `37380041150` — success
- release assets: `MailRuDesktop_Setup_v0.1.7.exe` and `MailRuDesktop_Update_v0.1.7.exe`
- GitHub Actions artifacts: none
- older binary Releases pruned; source tags retained

v0.1.7 authorization change:

- challenge handling now preserves the original `HttpClient + HttpClientHandler + CookieContainer` across the whole login flow;
- a pending auth session is retained in memory until CAPTCHA/2FA completes or expires;
- WebView2 only supplies the user's manual challenge result and resulting Mail.ru cookies back into that original session;
- for reCAPTCHA, the client attempts to read `g-recaptcha-response` from the challenge page and repeats `POST https://aj-https.mail.ru/cgi-bin/auth` with that response, matching the Hackus continuation step while keeping the same cookie jar;
- after challenge completion, web token and touch token are derived using that same original cookie session;
- browser-result cookies from account/mail/e/touch/aj hosts are merged back into the original cookie container before validation;
- repeated Mail.ru challenges can be handled for several rounds in the same logical login flow;
- auth-related HTTP calls in this continuation flow are spaced by at least five seconds.

The v0.1.6 mailbox/UI changes remain in place: web-first full-message/attachment operations, integrated filters, modal Add Account, theme settings, Trash-only permanent delete, and DPAPI-protected saved authorization.
