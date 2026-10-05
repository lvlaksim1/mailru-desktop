# Project constraints

Owner directives:

- Ignore official Mail.ru mail-client documentation as a protocol source for this project.
- Do not use IMAP or SMTP.
- Do not use special/app-specific passwords.
- Build the application from scratch.
- Use good practices and lessons from other projects without cloning their product identity or copying their implementation.
- If another project uses `aj-https.mail.ru` or the same internal API family, its endpoint knowledge must be investigated and captured in our reverse-spec.
- Prefer observed traffic, verified working legacy code, and reproducible reverse-engineering evidence over assumptions.
- Do not promote externally discovered endpoints to locally verified status without evidence.
- Never commit real passwords, access/refresh tokens, mailbox content, HAR/PCAP captures containing private data, or other secrets.
- Keep the GitHub repository public unless the Owner explicitly changes that policy.
- Do not accumulate build artifacts in Git; CI should not upload unnecessary artifacts.
