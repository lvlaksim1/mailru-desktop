# Manager plans

## Access-token protocol plan

1. Keep eligibility credential-based: official-client use of the same `ru.mail.oauth2.access`, not hostname.
2. Keep the endpoint registry as the runtime/CI boundary; a host used by product code must belong to a registered endpoint.
3. APK evidence is static evidence. Enable a newly discovered operation in normal UI only after controlled live validation.
4. Preserve at least five seconds between research/probe/test network requests; ordinary application runtime has no artificial fixed delay.

## Current released baseline — v0.3.5

The current public build includes:
- v0.3.4 tolerant parsing for `threads[].representations[]` in multi-account mailbox responses;
- the v0.3.x three-pane desktop interface and account rail;
- v0.3.5 date-grouped mail rows with seven columns;
- a fixed-size filled/hollow unread dot;
- sender name with email fallback;
- flag and thread-count columns;
- subject plus first text line, bold when unread;
- attachment paperclip only when an attachment exists;
- time as the final column;
- explicit re-mark-unread action for the currently selected message.

## Immediate validation plan

1. Owner validates v0.3.5 visually in the normal application theme.
2. Owner validates the second account that produced `threads[].representations[]`.
3. Confirm that switching accounts preserves independent message contents and Inbox unread counts.
4. Correct any layout/parser regression found by Owner runtime evidence before expanding the protocol surface.

## Stage 2 plan

1. Add thread and bulk operations: bulk remove/move/marks and thread remove/move/marks.
2. Add spam/unspam for messages and threads.
3. Add unsubscribe and message category change/feedback.
4. Add attachment lifecycle: explicit attachment list, outgoing attachment removal, reattach from another message.
5. Add EML download, message metadata and read-receipt notification.
6. Add search suggestions and investigate the `go.mail.ru` HTTP 520 before any attempt to enable new search.
7. Then proceed to snooze, color tags, filters, aliases/collectors, cloud operations, translation, summarization and smart replies.
8. Continue separate validation of delayed-send `send_date` and modern compose-session message-id semantics.
