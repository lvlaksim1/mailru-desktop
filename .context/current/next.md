# Next actions

1. Obtain/inspect current traffic for opening one message from folder 0 and identify the live full-message or full-thread endpoint.
2. Verify whether reading a message mutates read/unread state and determine how to request content without unintended side effects.
3. Add typed full-message/body/attachment models and wire the right-hand reading pane to the newly A-level endpoint.
4. Discover the live folder-list endpoint and replace numeric folder-id entry with a real folder sidebar.
5. Verify read/unread and flag/star mutation endpoints.
6. Verify move/archive/trash/delete operations.
7. Introduce Windows-protected access-token storage; never persist the mailbox password.
8. Preserve raw protocol diagnostics behind an expandable developer/research surface.
9. Publish every new user-facing version primarily as `MailRuDesktop_Update_vX.Y.Z.exe`.
