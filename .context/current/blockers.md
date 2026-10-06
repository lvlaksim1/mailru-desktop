# Current blockers and open risks

1. AJ endpoints for full-message retrieval, incoming-attachment download, move/archive, permanent delete, contacts/address book, and server-side search have not yet been verified; those product actions are intentionally disabled.
2. CAPTCHA/reCAPTCHA response variants of the mobile auth endpoint are not fully characterized. Policy is fixed: any detected interactive verification stops authorization.
3. The exact server format/semantics of `send_date` still needs owner runtime validation.
4. Attachment upload and compose share a message id; the known legacy implementation uses a fixed 32-character sentinel. Modern AJ constraints should be experimentally determined before replacing it.
5. The current app must not regress into calling sibling Mail.ru hosts; CI now guards runtime source for non-AJ Mail.ru URLs.
