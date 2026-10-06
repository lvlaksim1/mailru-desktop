# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.14.exe` and validates:
   - ordinary AJ mobile authorization succeeds;
   - if an account triggers CAPTCHA/reCAPTCHA/additional verification, the app only notifies and does not authorize;
   - saved AJ access-token sessions still restore;
   - Inbox/folders load through `m/threads/status/smart` without the former artificial five-second runtime delay;
   - immediate send/attachment upload still work.
2. Validate one delayed-send operation and record actual `send_date` behavior.
3. Implement the Owner-verified operations:
   - full-message retrieval through `aj-https.mail.ru/api/v1/messages/message`;
   - unread/read marks through `aj-https.mail.ru/api/v1/messages/marks`;
   - move/archive/trash through `aj-https.mail.ru/api/v1/messages/move`;
   - incoming attachment download through the explicitly allowed `af.attachmail.ru/cgi-bin/readmsg`.
4. Continue research for permanent delete, contacts/address book, server-side search, and confirmed flag mutation.
5. Preserve at least five seconds between network requests made by project research, probes, and automated tests; do not add this fixed delay back to normal application runtime.
6. Keep current folder `last_modified` behavior unchanged unless the Owner explicitly authorizes work on it.
7. Determine valid modern compose-session message-id semantics before replacing the known legacy fixed 32-character sentinel.
8. Do not continue or merge the `research/web-api-token` runtime approach unless the Owner explicitly changes the current host policy.
