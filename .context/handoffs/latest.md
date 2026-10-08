# Последняя передача менеджера MailRu Desktop — v0.3.30

Дата: 2026-10-09
manager_id: project-manager
repository: lvlaksim1/mailru-desktop
product authority: main; manager state authority: main
published release: https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.30
PR: #98; merge: 8b509be0592d3b3bdc4674419dc09d6ec637512f
Windows CI PR: 37855438488 PASS; main: 37855678396 PASS; installer run: 37855812685 PASS.
Official assets: MailRuDesktop_Update_v0.3.30.exe, MailRuDesktop_Setup_v0.3.30.exe.

Owner tested v0.3.29, confirmed «в остальном всё вроде бы ок», and reported six outstanding defects: persistent visual flicker, missing images in HTML (prior Magnet receipt with af12.mail.ru GIF), some letters much slower than browser, Load .md opens download rather than template folder, saving loaded template under new name modifies old file rather than making a copy, Send Now on scheduled message remains disabled. Technical debts expressly remain technical debts. Owner approved action.

v0.3.30: removed obsolete sequential pre-download of every HTML image before rendering; reader now receives full HTML immediately after mail body fetch and reveals at DOMContentLoaded, while images fetch separately through WebView2's resource events. Strict new MailRuInlineImageSource validator checks exact afNN.mail.ru HTTPS /cgi-bin/readmsg URL, same message, same account, numeric attachment ID and correct mode. Incoming attachment bytes fetched using existing mailbox access token and verified as an image; proxied afNN URLs supported; mailbox Cookies are never forwarded to a public proxy. Old full-message fetches are canceled on selection change. Safe stage timing recorded as mail_render_timing.

Markdown import starts at TemplateFiles.DirectoryPath. Save As with unused name creates new file and independent attachment copies, without deleting source; any existing target name requires explicit warning and permission, the store refuses implicit overwrite or cross-name rename. Unit tests reproduce sample Magnet URL, account/ID validation, byte-for-byte source integrity, clone attachments, and guarded overwrite.

**Owner GUI/credential acceptance still pending:** CI cannot show real user's HTML images, first-paint flicker or exact mail timings. Request only sanitized mail_image_inline/mail_render_timing diagnostic categories if trouble persists. Do not claim these three defects conclusively fixed before user confirms.

**Send Now (#77) remains blocked:** current source proves ordinary /send and /schedule creation only, not atomic change/unschedule of an existing scheduled item; ordinary resend risks duplicate. Detailed safe experiment plan: docs/protocol/scheduled-send-now-safety.md. No real mail sends made. Other unpaid debts: delayed delivery, receipt acknowledgments, unverified search routes. Accepted avatar retrieval, account drag, contact picker, updater and other v0.3.29 UI preserved.
