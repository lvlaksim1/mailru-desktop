# Current blockers and open risks

1. Fresh modern Mail.ru traffic captures have not yet been supplied, so externally discovered move/flags/search/folder/message-read endpoints cannot be promoted to locally verified A-level evidence.
2. The exact modern response schema of `m/threads/status/smart` must be captured before typed DTOs are treated as stable.
3. The exact server format/semantics of `send_date` remain intentionally untyped; the known legacy code passes a raw value.
4. Attachment upload and compose share a message id; the known legacy implementation uses a fixed 32-character sentinel. The accepted modern id constraints must be experimentally determined before replacing that compatibility behavior.
5. Authentication failure/captcha/2FA response variants for the current mobile endpoint are not yet fully characterized.
