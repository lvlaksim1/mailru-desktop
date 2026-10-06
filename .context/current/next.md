# Next actions

1. Refactor the runtime/CI protocol guard so eligibility is not tied to a fixed Mail.ru host list. Keep an explicit endpoint registry and require the same mailbox OAuth credential/evidence policy.
2. Controlled live validation, in this order:
   - permanent remove `/api/v1/messages/remove` and relevant bulk/clear operation;
   - `flagged` and `pinned` mutations through marks;
   - server search, including `https://go.mail.ru/api/v1/go/search/emails?t=<same token>`;
   - address book/autocomplete;
   - folder add/edit/remove/clear/archive.
3. After each successful live validation, add sanitized evidence, regression coverage and only then wire the UI.
4. Validate drafts/scheduled-message lifecycle and outgoing attachment remove/reattach.
5. Then evaluate secondary access-token functions: aliases, collectors, filters, cloud attachment bundles/status, translation and profile information.
6. Preserve at least five seconds between network requests made by research/probes/automated tests; do not add a fixed delay to ordinary application runtime.
7. Keep current folder `last_modified` behavior unchanged unless explicitly authorized.
8. Validate delayed-send `send_date` and modern compose-session message-id semantics.
9. Do not revive independent web-cookie/touch sessions, IMAP/SMTP or app-password fallbacks; current scope is the mailbox OAuth access-token family.
