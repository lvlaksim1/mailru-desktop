# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.12.exe`.
2. Re-test `expert.sout@mail.ru` without trying to obtain a mobile access_token: after CAPTCHA, the saved Hackus touch/search token should load messages through gosearch with no `q_query=*`.
3. Confirm standard folders are reconstructed correctly from returned message folder ids and that the folder destination ComboBox displays names only.
4. Verify dark theme: softer title bar, fully dark calendar popup, checkbox/tab/combo surfaces and Inbox HTML preview.
5. Double-click a message and validate full body, sender/recipient/date/time header, compact attachment buttons/tooltips, download, Reply, Forward, Archive and Move to folder.
6. Validate one delayed-send operation and capture the raw Mail.ru response if scheduling is rejected.
7. Continue with any runtime defects found in attachments, message actions or folder reconstruction.
