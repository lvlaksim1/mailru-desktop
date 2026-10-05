# Project goals

1. Deliver a usable standalone desktop Mail.ru client without browser-extension dependencies.
2. Reproduce and extend the proven capabilities of the legacy VBA integration: authentication, mailbox/thread retrieval, attachment upload, send, reply, and server-side scheduled send.
3. Build a complete evidence-backed map of the internal Mail.ru API through local captures and independent reverse-engineered implementations.
4. Add normal mail-client capabilities incrementally: folders, message/thread rendering, search, read/unread and star flags, move/delete/archive, contacts/autocomplete, drafts, attachments, local cache, notifications, and multi-account support.
5. Keep the product original: reuse engineering lessons and protocol knowledge, not third-party application code.
6. Keep the protocol layer isolated enough to survive endpoint/version changes without rewriting the UI.
