# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.3`

Verified release evidence:

- product CI for v0.1.3 head: `37352759223` — success
- release workflow: `37353057630` — success
- release assets: `MailRuDesktop_Setup_v0.1.3.exe` and `MailRuDesktop_Update_v0.1.3.exe`
- release workflow artifacts: none
- older binary GitHub Releases are pruned automatically; source tags remain.

Current product state:

- native Windows icon embedded in the executable and installers;
- real folder sidebar and parsed message list from the live smart-thread response;
- successful authorizations persist across restart and update using Windows DPAPI;
- passwords are never persisted;
- mobile `aj-https.mail.ru` access-token authentication remains the primary locally verified path;
- missing mobile `access_token` is no longer terminal: the client also attempts a Hackus-derived cookie-session path;
- cookie sessions can derive `touch.mail.ru/api/v1/tokens` search/API tokens and an `e.mail.ru` web token;
- saved state can contain protected access/refresh/web/touch tokens and cookie headers;
- folder loading prefers the locally verified `aj` smart endpoint, then web-thread fallback, then touch-search fallback.

Research imported into the protocol layer at B-level evidence:

- touch search;
- full-message fetch;
- attachment download representation;
- move to Trash;
- permanent remove;
- address-book lookup;
- web thread listing.

The `expert.sout@mail.ru` token-missing scenario is the current owner regression case. The new fallback compiles and CI passes, but live success for that account still requires owner runtime validation.

Full message-body reading remains intentionally disabled in the user-facing reading pane until the touch/web read endpoint is locally reproduced and its read/unread side effect is understood.

Runtime data lives under `%LOCALAPPDATA%\MailRuDesktop`; update installs preserve it and uninstall removes application-owned runtime data.
