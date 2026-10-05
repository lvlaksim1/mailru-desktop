# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.6.exe`.
2. Re-test `expert.sout@mail.ru`: if lightweight auth disagrees with the browser flow, the app must open interactive Mail.ru login/CAPTCHA instead of reporting a false wrong password.
3. Verify the resulting authorization survives restart and later Update installation.
4. Open a real message and verify body rendering in light/system/dark themes.
5. Open a message with attachments, verify original filename/MIME and download one attachment.
6. Verify mailbox filters above the list.
7. Test move to Trash on a disposable message and permanent delete only from Trash.
8. Record actual current response shapes for web full-message/move/delete endpoints and promote reproduced behavior to A-level evidence.
9. Continue release policy: newest Setup + Update only; no Actions artifacts.
