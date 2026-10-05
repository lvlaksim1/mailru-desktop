# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice

status: completed

verification: repository `lvlaksim1/mailru-desktop`; product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.

## I-002 — turn raw thread status into mailbox MVP

status: active

commitment: Render the verified smart-thread response as a usable mailbox and keep protocol parsing outside WPF.

progress: v0.1.2 fixed the live `folders_content -> threads -> base_message` shape, added a real folder sidebar, accurate folder counters and a usable structured list. The remaining part is full message-body reading, which stays gated on local read-endpoint validation.

## I-003 — expand reverse API coverage

status: active

commitment: Reconcile externally discovered Mail.ru endpoint families against owner VBA and fresh local traffic, and promote only locally reproduced operations to A-level evidence.

progress: Hackus and owner VBA have been reconciled. Touch search, full message, attachment representation, move/remove and address-book routes plus web thread listing are now represented in the protocol layer as external/B evidence.

priority operations: full message/thread read, read-state side effects, attachment download, read/unread, star/flag, move/archive/trash/delete, search, contacts/autocomplete, drafts and richer compose semantics.

## I-004 — expose verified send flow in UI

status: completed

verification: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.

## I-005 — establish installer/update release channel

status: completed

verification: release workflow publishes Setup + Update, uploads no Actions artifacts, and retains only the newest binary GitHub Release. Current release is `v0.1.3`.

## I-006 — identify and verify full-message read

status: active

commitment: Use current owner/runtime evidence to determine the live full-message/thread endpoint, response schema, attachment download representation, and read-state side effects before enabling full message opening in the UI.

progress: Hackus externally confirms `GET touch.mail.ru/api/v1/messages/message` with `id`, `email`, `token`, including HTML and attachment metadata. The protocol wrapper exists but remains B-level until local reproduction.

completion contract: current application can open a selected message/thread and display the real body without accidental state mutation, using an A-level documented endpoint.

## I-007 — eliminate false token_missing login failures

status: active — implementation complete, owner validation pending

commitment: Do not treat absence of the mobile `access_token` as proof of failed credentials. Fall back to Mail.ru cookie-session authentication, derive web/touch tokens where possible, classify account challenges explicitly, and persist successful authorization state with Windows protection.

progress: v0.1.3 implements mobile -> cookie session -> web/touch credential fallback, persists the resulting state with DPAPI, and provides web/touch mailbox fallback transports. CI and release build pass. The owner regression account that previously produced `token_missing` is the required live acceptance test.
