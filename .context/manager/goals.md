# Manager goals

## G-001 — usable native client

Deliver a standalone native Windows Mail.ru desktop client with normal mailbox, reading, compose, attachment, folder, search, notification, and multi-account workflows.

## G-002 — evidence-backed protocol

Grow and maintain a project-owned reverse specification of the internal Mail.ru API, with explicit A/B/C/D evidence levels and no silent promotion of speculative endpoints.

## G-003 — resilient protocol boundary

Keep Mail.ru transport/version churn isolated from the UI and local application state so endpoint evolution does not require rewriting the product.

## G-004 — secure local state

Ensure passwords, access/refresh tokens, private mailbox data, captures, and other sensitive material never enter Git history and are stored locally with appropriate Windows protection.

## G-005 — reproducible development

Keep the public repository buildable in CI without committed or uploaded build artifacts, and verify each protocol increment before making it part of normal UI flows.
