# Manager goals

## G-001 — usable native client

Deliver a standalone native Windows Mail.ru desktop client using verified internal Mail.ru mechanisms that consume the same mailbox OAuth credential `ru.mail.oauth2.access`, regardless of Mail.ru host. APIs requiring an independent credential/session remain outside the current scope.

## G-002 — evidence-backed access-token protocol

Maintain a project-owned reverse specification of the internal Mail.ru API surface reachable with the same `ru.mail.oauth2.access`, with explicit evidence levels and no silent promotion of static APK findings to runtime-verified status.

## G-003 — resilient protocol boundary

Keep protocol transport/version churn isolated from UI and local application state so host or endpoint evolution does not require rewriting the product.

## G-004 — secure local state

Ensure passwords, access/refresh tokens, private mailbox data, captures, and other sensitive material never enter Git history and are stored locally with appropriate Windows protection.

## G-005 — reproducible development

Keep the public repository buildable in CI without unnecessary build artifacts. Enforce an evidence-backed endpoint/credential registry rather than an AJ-only hostname rule, and runtime-verify each new protocol operation before enabling it in normal UI flows.
