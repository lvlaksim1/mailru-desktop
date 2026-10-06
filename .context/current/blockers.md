# Current blockers and open risks

1. **New search:** `go.mail.ru/api/v1/go/search/emails` is statically confirmed to use the same mailbox token, but live project probes return HTTP 520. It remains disabled by default.
2. **Delayed send:** exact live `send_date` semantics still need dedicated validation.
3. **Compose message id:** modern constraints for the message id shared by upload/send/draft remain to be determined before replacing the legacy 32-character sentinel.
4. **Broader API surface:** the remaining statically confirmed commands must still cross the live-validation gate before product enablement.
5. **Owner-environment check:** v0.2.0 passed CI and controlled test-account probes, but the updated desktop UI should still be exercised in the Owner's normal Windows installation.
