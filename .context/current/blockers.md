# Current blockers and open risks

1. **Owner runtime validation of v0.3.4 parser:** the `threads[].representations[]` defect is fixed and released, but the exact second account that exposed it still needs Owner confirmation that messages, preview content and Inbox unread count behave correctly.
2. **v0.3.5 visual/runtime validation:** CI proves build and startup, not pixel-level layout. Owner should verify the seven-column row proportions, date headers, fixed-size unread dot, unread bolding, attachment icon, thread count and re-mark-unread behavior in the real mailbox.
3. **Thread-count response diversity:** v0.3.5 reads count metadata/arrays from the actual smart-thread response when present. Mail.ru response diversity means the count must be treated as runtime-validated only after real threads with more than one message are observed.
4. **Parser diversity risk:** Mail.ru does not expose one universal smart-thread schema across all accounts/mailboxes. Future parser changes must preserve all known shapes and be tested against real sanitized payloads.
5. **New search:** `go.mail.ru/api/v1/go/search/emails` is statically confirmed to use the same mailbox OAuth token, but controlled live probes return HTTP 520. It remains disabled by default.
6. **Delayed send:** exact live `send_date` semantics still need dedicated validation.
7. **Compose message id:** modern constraints for the message id shared by upload/send/draft remain to be determined before replacing the legacy sentinel.
8. **Broader API surface:** remaining statically confirmed OAuth-compatible commands must still cross the live-validation gate before product enablement.
9. **System chrome/theme:** Windows title-bar/calendar/native rendering can vary by OS build. Automated launch tests do not prove all visual details.
