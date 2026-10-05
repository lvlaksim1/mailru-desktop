# Hackus / VBA Mail.ru reverse-API reconciliation — 2026-10-05

Purpose: preserve the useful protocol knowledge found in the user's working VBA implementation and the independent Hackus reverse client without treating third-party behavior as locally verified.

## Sources

- Owner-provided VBA source: working `aj-https.mail.ru` mobile auth, smart thread listing, attachment upload, immediate send and scheduled send.
- Owner-provided legacy VBA auth specification: cookie session through `auth.mail.ru`, SDC hand-off and token extraction from `e.mail.ru/inbox/`.
- Independent reverse implementation: `ahmedelkfafy/Hackus`, `MailRuClientNew.cs` and MailRu attachment models.
- Independent corroboration for web thread API: `xRubin/unapi-mailru`.

Official Mail.ru client documentation is intentionally not used as protocol authority.

## Authentication paths

### A. Mobile OAuth-like path — owner verified

`POST https://aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app`

Form fields:

- `Password`
- `Login`
- `oauth2=1`
- `useragent=android`
- `mobile=1`
- `mob_json=1`
- `simple=1`

Known successful response contains `access_token` and may contain `refresh_token`.

Important finding: absence of `access_token` is not sufficient evidence that credentials are invalid.

### B. Hackus cookie-session fallback — external B evidence

`POST https://aj-https.mail.ru/cgi-bin/auth`

Hackus intentionally disables automatic redirects, keeps a cookie jar, sends `Login` and `Password`, then classifies the response `Location`:

- contains `inbox` → authenticated cookie-session candidate;
- contains `user/login?login` → secondary verification path;
- contains `recaptcha` → reCAPTCHA;
- contains `fail` → bad credentials;
- contains `recovery` or `ukey` → blocked/recovery state.

For the secondary-verification path Hackus probes:

`GET https://account.mail.ru/api/v1/user/copper`

A response containing `captcha` is treated as CAPTCHA; otherwise the state is treated as 2FA.

Legacy image CAPTCHA payload in Hackus:

`GET https://c.mail.ru/c/6`

CAPTCHA answer submission in Hackus:

`POST https://account.mail.ru/api/v1/user/copper`

Form fields include `fields={"captcha":"..."}` and `htmlencoded=false`. The returned URL is then followed to finish the session.

The desktop client currently imports the state classification and cookie-session fallback. It does **not** automate CAPTCHA solving.

### C. Touch token — external B evidence

After a valid cookie session Hackus requests:

`GET https://touch.mail.ru/api/v1/tokens?email=<mailbox>`

The returned JSON `token` becomes the token for the touch API family.

### D. Web token — owner legacy evidence

The owner's legacy VBA obtains a web token by opening:

`GET https://e.mail.ru/inbox/`

with valid Mail.ru cookies and extracting the token associated with the `/api/v1/user/short` bootstrap data.

The older owner flow can establish those cookies through:

1. `POST https://auth.mail.ru/cgi-bin/auth`
2. `GET https://auth.mail.ru/sdc?from=...`
3. `GET <Location>`
4. `GET https://e.mail.ru/inbox/`

Known cookie names from that flow: `Mpop`, `ssdc`, `sdcs`.

Saved-session validation used by the VBA:

`GET https://auth.mail.ru/cgi-bin/auth?mac=1&Login=<email>`

and regards `"status":"ok"` as valid.

## Hackus touch API inventory

### Search/API token

`GET https://touch.mail.ru/api/v1/tokens`

Query: `email`.

### Search

`GET https://touch.mail.ru/cgi-bin/gosearch`

Observed parameters:

- `token`
- `json=1`
- `ajax_call=1`
- `page=1`
- `q_folder=all` or `*`
- `count`
- `x-email`
- `q_from`
- `q_subj`
- `q_query`
- `q_attach=1`
- date bounds: `ddb`, `dmb`, `dyb`, `dde`, `dme`, `dye`

Hackus extracts message ids from successful search results.

### Full message

`GET https://touch.mail.ru/api/v1/messages/message`

Query:

- `id`
- `email`
- `token`

Hackus reads at least:

- `subject`
- `date`
- HTML body
- correspondents/email fields
- `attaches.list`

### Attachment representation

Each attachment object used by Hackus contains at least:

- `name`
- `content_type`
- `href.download`

The binary attachment is fetched directly from the returned `href.download` URL while retaining the authenticated cookie-session.

### Move to Trash

`POST https://touch.mail.ru/api/v1`

Form:

- `__urlp=/messages/move`
- `ids=[...]`
- `folder=500002`
- `email`
- `htmlencoded=false`
- `token`

### Permanent remove

Same dispatcher endpoint, with:

`__urlp=/messages/remove`

### Address-book lookup

`POST https://touch.mail.ru/api/v1`

`__urlp` routes to:

`/k8s/ab/smart?fields=["emails"]&filter={"flags":{"has_mailbox":null}}&email=...&htmlencoded=false&token=...`

## Owner-verified aj API inventory

### Folder/thread status

`GET https://aj-https.mail.ru/api/v1/m/threads/status/smart`

Parameters: encoded `folders` JSON, `last_modified`, `access_token`.

### Attachment upload

`POST https://aj-https.mail.ru/api/v1/messages/attaches/add?htmlencoded=false&mp=android&access_token=...`

Multipart fields:

- `message_id`
- `file`

### Immediate send

`POST https://aj-https.mail.ru/api/v1/messages/send?htmlencoded=false&mp=android&access_token=...`

### Scheduled send

`POST https://aj-https.mail.ru/api/v1/messages/schedule?htmlencoded=false&mp=android&access_token=...`

Known compose fields: `attaches`, `body`, `correspondents`, `id`, `source`, `subject`, `send_date`, `priority`.

## Web thread listing — external B evidence

Independent reverse projects corroborate:

`GET https://e.mail.ru/api/v1/threads/status/golang`

with cookie-session + web token. Common parameters include email, folder, offset, limit, sort, `htmlencoded=false`, `last_modified`, `api=1`, and `token`.

This endpoint is now available only as a fallback transport in the protocol layer; it remains B evidence until the owner reproduces it with a current account.

## Product decisions from this research

1. `token_missing` is no longer terminal by itself.
2. Desktop auth first attempts the owner-verified mobile token flow.
3. Independently, it attempts the Hackus cookie-session path.
4. Cookie-session success is used to derive touch and web tokens.
5. Tokens and cookie headers are persisted with Windows DPAPI; passwords remain unsaved.
6. Folder loading prefers the locally verified `aj` smart endpoint and falls back to the external web thread endpoint when only the web session is available.
7. Touch full-message/search/move/remove/contacts calls are implemented in the protocol layer but remain B evidence until locally reproduced.
8. CAPTCHA/2FA are classified explicitly rather than being misreported as `token_missing`; interactive challenge UI is a separate product increment.
