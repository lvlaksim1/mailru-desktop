# Next actions

1. Require CI success for pull request #18, including the AJ-only host guard.
2. Merge `release/0.1.13-aj-only` to `main`.
3. Publish `MailRuDesktop_Update_v0.1.13.exe` and `MailRuDesktop_Setup_v0.1.13.exe`.
4. Owner installs the update and validates:
   - ordinary AJ mobile authorization succeeds;
   - if an account triggers CAPTCHA/reCAPTCHA/additional verification, the app only notifies and does not authorize;
   - saved AJ access-token sessions still restore;
   - Inbox/folders load through `m/threads/status/smart`;
   - immediate send/attachment upload still work.
5. Validate one delayed-send operation.
6. Continue reverse-engineering only on `aj-https.mail.ru` for the currently disabled message operations.
7. Do not continue or merge the `research/web-api-token` runtime approach unless the Owner explicitly changes the AJ-only rule.
