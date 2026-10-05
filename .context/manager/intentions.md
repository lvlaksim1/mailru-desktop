# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice

status: completed

commitment: Create the project through Repo Factory, establish project context, implement the initial reverse-protocol client and native shell, and verify a clean Windows build.

verification: repository `lvlaksim1/mailru-desktop`; product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622` concluded success.

## I-002 — turn raw thread status into mailbox MVP

status: active

commitment: Define typed response models for the locally verified smart-thread endpoint, render an inbox/folder message list, and keep protocol parsing outside WPF.

completion contract: a user can authenticate, load a known folder, see a structured list instead of raw JSON, and open an item without exposing the access token.

## I-003 — expand reverse API coverage

status: active

commitment: Reconcile externally discovered Mail.ru endpoint families against fresh local traffic and promote only locally reproduced operations to A-level evidence.

priority operations: full message/thread read, folder discovery/mapping, read/unread, star/flag, move/archive/trash/delete, search, contacts/autocomplete, drafts, attachment download, richer compose semantics.

## I-004 — expose verified send flow in UI

status: completed

commitment: Wire immediate compose and multi-file attachment upload to the locally verified `aj-https.mail.ru` transport without enabling unverified mutation/search routes.

verification: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260` concluded success.

## I-005 — establish installer/update release channel

status: completed

commitment: Produce a full Windows installer for first installation and establish a durable release workflow where future versions are delivered as update installers for existing installations.

verification: repository commit `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`; GitHub Release `v0.1.0` contains both `MailRuDesktop_Setup_v0.1.0.exe` and `MailRuDesktop_Update_v0.1.0.exe`.
