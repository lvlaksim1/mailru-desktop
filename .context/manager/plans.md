# Manager plans

## Access-token protocol plan

1. Keep eligibility credential-based: official-client use of the same `ru.mail.oauth2.access`, not hostname.
2. Keep the endpoint registry as the runtime/CI boundary; a host used by product code must belong to a registered endpoint.
3. APK evidence is static evidence. Enable a newly discovered operation in normal UI only after controlled live validation.
4. Preserve at least five seconds between research/probe/test network requests; ordinary application runtime has no artificial fixed delay.

## Current released baseline — v0.2.0

Live-validated and released in addition to the previous baseline:

- permanent removal from Trash;
- flagged and pinned message marks;
- classic server-side search;
- server address book;
- fast recipient lookup;
- user-folder create, rename, clear and delete;
- server draft saving.

The new `go.mail.ru` search is not active because live probes returned HTTP 520.

## Stage 2 plan

1. Add thread and bulk operations:
   - bulk remove;
   - remove thread;
   - bulk move;
   - move thread;
   - thread marks;
   - bulk marks.
2. Add spam/unspam for messages and threads.
3. Add unsubscribe and message category change/feedback.
4. Add attachment lifecycle:
   - explicit attachment list;
   - remove outgoing attachment;
   - reattach from another message.
5. Add EML download, message metadata and read-receipt notification.
6. Add search suggestions and investigate the `go.mail.ru` HTTP 520 before any attempt to enable new search.
7. Then proceed to snooze, color tags, filters, aliases/collectors, cloud operations, translation, summarization and smart replies.
8. Continue separate validation of delayed-send `send_date` and modern compose-session message-id semantics.
