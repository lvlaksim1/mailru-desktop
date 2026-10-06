# Latest handoff

MailRu Desktop **v0.2.0** is released. `main` is authoritative.

## Release

- product commit: `32436a114f1384a36e9fe42c328b064f6c804f3d`;
- CI: `37539922817` success;
- live release probe: `37539371650` success;
- release workflow: `37540083902` success;
- update: `MailRuDesktop_Update_v0.2.0.exe`;
- setup: `MailRuDesktop_Setup_v0.2.0.exe`.

## New in v0.2.0

User-facing:
- permanent deletion from Trash;
- message flag;
- message pin;
- classic server-side mail search;
- server address book;
- fast recipient lookup;
- user-folder create/rename/clear/delete;
- server draft save.

Infrastructure:
- endpoint registry replaces the obsolete fixed Mail.ru host allow-list;
- direct folder-list contract is implemented and live-validated;
- new `go.mail.ru` search implementation remains present as static/opt-in code but is not enabled in UI.

## Important live results

Final release probe passed with zero failures for the enabled set.

`go.mail.ru/api/v1/go/search/emails` returned HTTP 520 in live probes. Use classic `/api/v1/messages/search` until resolved.

Permanent `/api/v1/messages/remove` returned `denied` for a message still in Drafts, but passed after the same test draft was moved to Trash. Keep permanent delete Trash-only.

## Next stage

Prioritize bulk/thread remove and move, thread/bulk marks, spam/unspam, unsubscribe/categories, attachment lifecycle, EML/metadata/read receipt and search suggestions. Investigate new-search HTTP 520 separately.

Authorization and safety rules remain: same mailbox OAuth token defines scope; CAPTCHA stops auth; no IMAP/SMTP/app-password/web-cookie fallback; research requests spaced by at least five seconds.
