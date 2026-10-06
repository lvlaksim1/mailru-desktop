# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: **v0.1.15**

## Product state

- latest GitHub Release is `v0.1.15`, published 2026-10-06;
- release assets remain `MailRuDesktop_Setup_v0.1.15.exe` and `MailRuDesktop_Update_v0.1.15.exe`;
- released operations include mobile OAuth authorization, mailbox/thread status, full message, unread/read marks, move/archive/trash, outgoing attachment upload, immediate send, scheduled send and incoming attachment download;
- normal application requests are serialized but do not contain the fixed five-second research delay;
- folder `last_modified` behavior remains unchanged by Owner instruction.

## Current Owner protocol boundary

The former fixed host allow-list is superseded. A mechanism is in scope when the official Mail.ru client uses the same mailbox OAuth credential `ru.mail.oauth2.access` obtained by our authorization flow. The credential may be transported as `access_token`, another parameter such as `t`, or an authorization header. A different Mail.ru hostname is not a reason to reject an operation. A mechanism requiring an independent credential/session remains out of current scope.

Authorization challenge policy is unchanged: CAPTCHA/reCAPTCHA/additional verification stops authorization and is reported to the user.

## APK research state

Research branch: `research/mail-apk-15.107.0.148045`
Research head: `e549fd60dc7380d9a24c58c4ef931e25c4f5b67d`
Official package: `ru.mail.mailapp` 15.107.0.148045.

Verified static findings:

- 151 network classes with `@UrlPath`;
- 101 OAuth/access-token candidates;
- 100 commands proven to use the same `ru.mail.oauth2.access`;
- one rejected false positive: `QrGetInfoCommand`;
- token transport distribution: 97 via `access_token`, 1 via `t`, 2 via custom authorization header;
- retained source/contract coverage: 101/101 candidates, 0 missing source classes.

Key evidence: `FULL_ACCESS_TOKEN_DISCOVERY`, `ACCESS_TOKEN_TRANSPORT_AUDIT`, `ACCESS_TOKEN_CONTRACTS`, `ACCESS_TOKEN_EXTENDED_API_SPEC`, `AJ_API_SPEC`, `APK_ROUTE_MAP`, `APK_HOST_RESOURCES` in the research branch.

High-value statically confirmed operations now include permanent remove, bulk remove/clear, spam/unspam, unread/flagged/pinned marks, folder list/add/edit/remove/clear/archive/open/close, old and new server search, drafts, scheduling, attachment remove/reattach, address book, filters, unsubscribe, categories, snooze, EML, metadata, read notification, smart replies, aliases, collectors, cloud operations, translation and profile/account information.

The new search `https://go.mail.ru/api/v1/go/search/emails` is in scope because the official client sends the same mailbox access token as `t`.

## Evidence boundary

APK confirmation is strong static evidence of official-client behavior, but it is not automatically equivalent to a successful live call from MailRu Desktop. Newly discovered functions require controlled runtime validation before default product enablement.
