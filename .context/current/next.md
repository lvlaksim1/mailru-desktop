# Next actions

1. Owner installs **v0.3.5** update and validates the new message list:
   - date appears as a separate group header;
   - rows are seven columns in the requested order;
   - read/unread dot keeps exactly one fixed size and only changes fill state;
   - fifth-column text is bold only for unread mail;
   - sender uses name first and email only as fallback;
   - thread count is correct on a real multi-message thread;
   - paperclip appears only when attachments exist;
   - final column shows time;
   - theme remains consistent with the selected application theme.
2. Validate `Не прочитано` on the currently selected message:
   - opening still marks unread mail read automatically;
   - the explicit action can return the selected mail to unread;
   - the filled dot/bold text and Inbox unread counter update accordingly.
3. Owner validates the second account that originally returned `threads[].representations[]`:
   - Inbox shows real messages;
   - preview loads full message content;
   - Inbox badge equals server `messages_unread`;
   - switching accounts in both directions does not mix mailbox state.
4. If Owner validation finds a UI/parser defect, fix that before expanding the protocol surface.
5. After validation, resume staged access-token feature expansion: bulk/thread actions, spam/unspam, unsubscribe/categories, attachment lifecycle, EML/metadata/read receipt, search suggestions, filters, snooze/color tags and other mapped OAuth-compatible commands.
6. Investigate `go.mail.ru/api/v1/go/search/emails` HTTP 520 separately; do not make it default until a live probe passes.
7. Preserve at least five seconds between repeated research/probe/test network requests; normal application runtime remains unthrottled by a fixed delay.
8. Keep folder `last_modified` unchanged unless explicitly authorized.
9. Delayed-send `send_date` and modern compose-session message-id semantics still need dedicated live validation before deeper compose changes.
