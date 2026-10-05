# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.8.exe`.
2. Re-test `expert.sout@mail.ru`.
3. For reCAPTCHA, verify that the solver window closes immediately after the CAPTCHA is solved, before Mail.ru browser navigation reaches Inbox.
4. Confirm that the original HTTP session then repeats CreateSession with g-recaptcha-response and that mailbox data loads automatically.
5. If Mail.ru presents classic image CAPTCHA, verify the image/text-answer flow through user/copper and CreateSessionByLink.
6. Verify authorization survives restart.
7. Continue validation of full-message body, incoming attachments/download, filtering, Trash/delete and themes.
