# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.12`

Verified release evidence:

- product CI for v0.1.12 head `50c6332356f98c859b22f73f9e81270afeebaa12` — success
- release workflow `37398787496` — success
- release assets: `MailRuDesktop_Setup_v0.1.12.exe` and `MailRuDesktop_Update_v0.1.12.exe`
- GitHub Actions artifacts: none
- only latest binary Release retained

Critical Hackus correction after owner v0.1.10 runtime test:

- Hackus does NOT obtain or depend on the mobile `access_token` after web challenge login;
- Hackus Login() completes through the shared cookie session, then GetSearchToken() obtains `_searchToken` from `https://touch.mail.ru/api/v1/tokens`;
- mailbox/search operations use that search token and the same cookies;
- normal Hackus Search() calls `https://touch.mail.ru/cgi-bin/gosearch` with `token/json/ajax_call/page/q_folder/count/x-email` and does NOT add `q_query=*` unless an actual body query exists;
- MailRu Desktop now follows that path: ordinary mailbox load uses touch gosearch with `q_folder=all`, no wildcard query, then splits messages into standard folders locally by each message folder id;
- the previous attempt to obtain a post-challenge mobile access_token was removed from the Hackus path;
- full-message read prefers Hackus `touch.mail.ru/api/v1/messages/message`;
- move/delete prefer Hackus touch `/messages/move` and `/messages/remove`;
- touch cookies are preferred for incoming attachment downloads;
- all protocol HTTP requests remain paced at least five seconds apart.

UI work in v0.1.11/v0.1.12:

- dark title bar uses a softer dark-gray caption;
- calendar popup received a full themed Calendar/CalendarItem/CalendarDayButton/CalendarButton implementation;
- folder ComboBox selected value renders the folder name instead of the record object's default ToString;
- empty flag column between attachment marker and size was removed;
- double-clicking a message opens a dedicated full-message window;
- message window shows subject, sender, recipients, date/time, compact attachments with full filename tooltip, full body, and buttons Reply, Forward, Archive and Move to folder;
- Reply opens an inline reply form in that same message window;
- Forward opens the same inline compose area with forwarded-message text;
- Archive folder is exposed/resolved dynamically;
- attachment download in the message window uses touch cookies first;
- external image requests from rendered email HTML are blocked; embedded data images remain allowed.

Delayed-send functionality from v0.1.10 remains present and awaits owner runtime validation.
