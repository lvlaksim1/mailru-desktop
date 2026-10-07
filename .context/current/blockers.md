# Current blockers and open risks

1. **Second-account smart-thread variant:** v0.3.3 does not recognize `folders_content[].threads[].representations[]`, so a mailbox can show folders/counts while the message list is empty. Root cause is confirmed and fixed on PR #34; release v0.3.4 is pending merge/publish.
2. **Parser diversity risk:** Mail.ru does not expose one universal smart-thread schema across all accounts/mailboxes. Future parser changes must preserve all known shapes and be tested against real sanitized payloads.
3. **New search:** `go.mail.ru/api/v1/go/search/emails` is statically confirmed to use the same mailbox OAuth token, but controlled live probes return HTTP 520. It remains disabled by default.
4. **Delayed send:** exact live `send_date` semantics still need dedicated validation.
5. **Compose message id:** modern constraints for the message id shared by upload/send/draft remain to be determined before replacing the legacy sentinel.
6. **Broader API surface:** the remaining statically confirmed OAuth-compatible commands must still cross the live-validation gate before product enablement.
7. **System chrome/theme:** Windows title-bar and calendar rendering can vary by OS build. Automated launch tests catch crashes, not all visual defects; Owner visual validation remains necessary for these native controls.
