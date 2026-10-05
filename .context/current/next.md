# Next actions

1. Add sanitized fixture-driven parsing for `m/threads/status/smart`.
2. Build typed folder/thread models and replace raw JSON mailbox output with a structured list.
3. Introduce Windows-protected access-token storage; do not persist the password.
4. Refactor compose interaction out of window code-behind once the first UI behavior is exercised.
5. Experimentally determine valid modern compose-session message-id semantics for attachment/send coupling.
6. Verify the modern `send_date` format before exposing scheduled send as normal UI.
7. Ingest the next browser/mobile traffic log from the Owner and reconcile candidate endpoints for full message read, folder listing, flags, move/delete, search and contacts.
8. Keep `docs/protocol` evidence levels synchronized with every verified protocol change.
9. For every version after `v0.1.0`, publish `MailRuDesktop_Update_vX.Y.Z.exe` as the normal delivery path; full Setup remains only the first-install/recovery package.
