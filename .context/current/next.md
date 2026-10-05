# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.4.exe`.
2. Re-test the `expert.sout@mail.ru` account that previously produced reCAPTCHA and complete the interactive challenge in the embedded Mail.ru window.
3. Verify that the resulting authorization persists across an application restart and a later Update install.
4. Open one real message and verify full body rendering plus whether opening it changes unread/read state.
5. Open a message with attachments, verify original filename/MIME display, and download one attachment.
6. Test server search by sender/subject/body and verify returned message ids map to real messages.
7. Test contacts retrieval against the current account.
8. Test moving one disposable test message to Trash, then separately test permanent remove only on a disposable test message.
9. Record actual response shapes/errors from any failing B-level touch/web endpoint and promote only reproduced behavior to A-level evidence.
10. Continue release policy: only Setup + Update in the newest binary GitHub Release, no Actions artifacts.
