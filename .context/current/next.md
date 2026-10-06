# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.10.exe`.
2. Re-add/re-authenticate `expert.sout@mail.ru` once: the saved v0.1.9 session has only the touch/search token and cannot reconstruct mobile access_token without the password.
3. Confirm reCAPTCHA completes, status reaches active, then folders and Inbox load automatically.
4. Verify diagnostics no longer show the touch q_query=* response as the mailbox source.
5. Verify dark theme: checkbox background, selected tab headers, account/folder combos and HTML Inbox preview.
6. Test delayed sending with a near-future message and verify Mail.ru returns status 200 and delivery occurs at the chosen local time.
7. Continue validation of incoming attachment display/download and Trash/delete behavior.
