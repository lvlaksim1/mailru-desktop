# Manager goals

## G-001 — usable native client

Deliver a standalone native Windows Mail.ru desktop client whose active network protocol is restricted to verified `aj-https.mail.ru` endpoints. Features without a verified AJ endpoint stay disabled rather than falling back to another Mail.ru host.

## G-002 — evidence-backed AJ protocol

Grow and maintain a project-owned reverse specification of the internal `aj-https.mail.ru` API, with explicit evidence levels and no silent promotion of speculative endpoints.

## G-003 — resilient protocol boundary

Keep AJ transport/version churn isolated from the UI and local application state so endpoint evolution does not require rewriting the product.

## G-004 — secure local state

Ensure passwords, access/refresh tokens, private mailbox data, captures, and other sensitive material never enter Git history and are stored locally with appropriate Windows protection.

## G-005 — reproducible development

Keep the public repository buildable in CI without committed or uploaded build artifacts, enforce the AJ-only host rule in CI, and verify each protocol increment before enabling it in normal UI flows.
