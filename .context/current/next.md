# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.15.exe` and validates:
   - saved AJ authorization still restores;
   - Inbox/folders still load normally with unchanged `last_modified`;
   - selecting a message loads the full body through `aj-https.mail.ru/api/v1/messages/message`;
   - incoming attachments download through the approved `af.attachmail.ru/cgi-bin/readmsg`;
   - read/unread changes work through `/api/v1/messages/marks`;
   - move, archive and trash work through `/api/v1/messages/move`;
   - reply/send/outgoing attachments remain functional.
2. Validate one delayed-send operation and record actual `send_date` behavior.
3. Continue research for:
   - permanent delete;
   - server contacts/address book;
   - server-side search;
   - confirmed flag mutation.
4. Preserve at least five seconds between network requests made by project research, probes, and automated tests; do not add this fixed delay back to normal application runtime.
5. Keep current folder `last_modified` behavior unchanged unless the Owner explicitly authorizes work on it.
6. Determine valid modern compose-session message-id semantics before replacing the known legacy fixed 32-character sentinel.
7. Do not continue or merge the `research/web-api-token` runtime approach unless the Owner explicitly changes the current host policy.
