# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: **v0.2.0**

## Release evidence

- product commit: `32436a114f1384a36e9fe42c328b064f6c804f3d`;
- main CI run `37539922817`: success;
- controlled live feature probe `37539371650`: success, zero failures for the release set;
- release workflow run `37540083902`: success;
- update installer: `MailRuDesktop_Update_v0.2.0.exe`, 51,383,814 bytes, SHA-256 `5210faef1bc3626046bd7032de38712616ac52b6354e90ebc9fad57283048379`;
- full installer: `MailRuDesktop_Setup_v0.2.0.exe`, 51,383,440 bytes, SHA-256 `b8232e253fbf18ab9719136b8d988a1c080582cd4a48bad5ac244eed15ba8c0c`;
- release pruning completed; v0.2.0 is the current binary release.

## Released v0.2.0 additions

- permanent deletion from Trash via `/api/v1/messages/remove`;
- flagged and pinned marks via `/api/v1/messages/marks`;
- classic server search via `/api/v1/messages/search`;
- server address book via `/api/v1/ab/smart`;
- fast recipient lookup via `/api/v1/ab/fast`;
- folder list contract plus create, rename, clear and delete;
- server draft saving;
- endpoint-registry host policy replacing the obsolete fixed host allow-list;
- UI controls for these operations.

## Live findings

The final probe passed: auth, Inbox, folder list, address book, recipient lookup, classic search, flag toggle/restore, pin toggle/restore, folder create/rename/clear/delete, draft save/list/move-to-trash, permanent remove.

The new `go.mail.ru/api/v1/go/search/emails` route remains statically valid but live calls returned HTTP 520. It is not active in the v0.2.0 UI.

Direct permanent remove of a draft returned `status=400,error=denied`; move to Trash then permanent remove passed. Product UI follows the validated Trash-only permanent-delete workflow.

## Stable policies

- same mailbox OAuth credential `ru.mail.oauth2.access` defines API scope, not host;
- CAPTCHA/reCAPTCHA/additional verification stops authorization;
- no IMAP/SMTP, app passwords or independent web-cookie fallback;
- normal runtime has no fixed five-second delay;
- research/probe/test requests keep at least five seconds spacing;
- folder `last_modified` behavior remains unchanged unless explicitly authorized.
