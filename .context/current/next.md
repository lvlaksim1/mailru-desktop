# Next actions

1. Owner installs `MailRuDesktop_Update_v0.2.0.exe` and checks the new UI in the normal Windows environment:
   - server search;
   - server contacts and fast recipient lookup;
   - flag/pin;
   - folder create/rename/clear/delete;
   - draft save;
   - permanent delete from Trash.
2. Stage 2 implementation:
   - bulk remove and thread remove;
   - bulk move and thread move;
   - thread/bulk marks;
   - spam/unspam for messages and threads;
   - unsubscribe and category change/feedback;
   - attachment list/remove/reattach;
   - EML download, metadata and read-receipt notification;
   - search suggestions.
3. Investigate the `go.mail.ru` search HTTP 520 independently; do not enable it until a live probe passes.
4. After Stage 2, proceed to snooze, color tags, filters, aliases/collectors, cloud operations, translation, summarization and smart replies.
5. Preserve at least five seconds between research/probe/test network requests; normal app runtime remains unthrottled by a fixed delay.
6. Keep folder `last_modified` unchanged unless explicitly authorized.
7. Validate delayed-send `send_date` and modern compose-session message-id semantics.
