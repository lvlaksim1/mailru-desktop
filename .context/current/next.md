# Next actions

1. Finish **v0.3.4 hotfix** for the second-account message-list regression:
   - PR #34 is open from `fix/v0.3.4-representations-parser`;
   - CI and repository-storage checks are green;
   - merge only after confirming the parser change remains isolated to response decoding;
   - publish both full installer and `MailRuDesktop_Update_v0.3.4.exe`.
2. Owner validates v0.3.4 on the account that produced `threads[].representations[]`:
   - Inbox must show actual messages, not only folders/counts;
   - first visible rows must carry sender, subject, snippet, date/time and marks;
   - Inbox badge must remain equal to the server's unread count;
   - switching back and forth between accounts must preserve independent mailbox contents and counts.
3. Add/keep regression coverage for all known smart-thread shapes:
   - `base_message`;
   - `messages[]`;
   - direct thread-like message;
   - `representations[]` with `message_id_last`.
4. After the parser hotfix is stable, resume UI refinement from the v0.3.3 baseline rather than reopening the older v0.2.0 plan.
5. Then continue staged access-token feature expansion: bulk/thread actions, spam/unspam, unsubscribe/categories, attachment lifecycle, EML/metadata/read receipt, search suggestions, filters, snooze/color tags and other already mapped OAuth-compatible commands.
6. Investigate `go.mail.ru/api/v1/go/search/emails` HTTP 520 separately; do not make it the default until a live probe passes.
7. Preserve at least five seconds between repeated research/probe/test network requests; normal application runtime remains unthrottled by a fixed delay.
8. Keep folder `last_modified` unchanged unless explicitly authorized.
9. Delayed-send `send_date` and modern compose-session message-id semantics still need dedicated live validation before deeper compose changes.
