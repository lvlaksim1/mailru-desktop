# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice

status: completed

verification: repository `lvlaksim1/mailru-desktop`; product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.

## I-002 — turn raw thread status into mailbox MVP

status: active

commitment: Render the verified smart-thread response as a usable mailbox and keep protocol parsing outside WPF.

progress: v0.1.1 now shows a structured list with sender, subject, date, size and message indicators plus selected-item metadata/snippet. The remaining part of this intention is full message-body reading, which must not be wired until its endpoint is locally promoted to A-level evidence.

## I-003 — expand reverse API coverage

status: active

commitment: Reconcile externally discovered Mail.ru endpoint families against fresh local traffic and promote only locally reproduced operations to A-level evidence.

priority operations: full message/thread read, folder discovery/mapping, read/unread, star/flag, move/archive/trash/delete, search, contacts/autocomplete, drafts, attachment download, richer compose semantics.

## I-004 — expose verified send flow in UI

status: completed

verification: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.

## I-005 — establish installer/update release channel

status: completed

verification: release pipeline commit `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`; releases `v0.1.0` and `v0.1.1`.

## I-006 — identify and verify full-message read

status: active

commitment: Use current traffic evidence to determine the live full-message/thread endpoint, response schema, attachment download representation, and any read-state side effects before enabling full message opening in the UI.

completion contract: current application can open a selected message/thread and display the real body without accidentally changing state unless explicitly intended, using an A-level documented endpoint.
