# Manager goals

## G-001 — usable native client
Deliver a standalone native Windows Mail.ru desktop client using verified internal HTTP mechanisms that operate from the mailbox OAuth credential `ru.mail.oauth2.access`, regardless of which Mail.ru host carries the request.

## G-002 — evidence-backed access-token API map
Maintain a project-owned reverse specification of useful official-client mechanisms reachable with the same mailbox OAuth token. Preserve route, method, host, token transport, request/response contract and evidence level.

## G-003 — resilient protocol boundary
Keep host selection, token transport and protocol/version churn isolated inside `MailRuDesktop.Protocol`. UI and local state must not depend on one fixed Mail.ru hostname or one spelling of the token parameter.

## G-004 — secure local state
Keep credentials, private mailbox data and captures out of Git. Store local secrets with Windows protection.

## G-005 — reproducible verified development
Keep the public repository buildable in CI. Replace obsolete host-only guards with endpoint/evidence/credential-aware checks. Static APK confirmation is implementation evidence, but newly discovered operations require controlled live validation before default product enablement.

## G-006 — progressively complete mail-client functionality
Prioritize permanent delete, flags/pinning, server search, address book, folders, drafts/scheduling and attachment lifecycle. Administrative or account-destructive endpoints remain documented but are not exposed without a separate product decision.

## Следующая цель после выпуска 0.3.50
Проверить отсутствие повторных всплывающих уведомлений между запусками и доставку новых сообщений. Не рассматривать MCS ACK как подтверждение сервером. Для группировки 5/2 обеспечить полный корректный состав реальных писем, но только при подтверждённой структуре AJ API. Диагностика справа увеличена, и её должно хватать для исследования.

## Цель после v0.3.51
Отделять транспортное подтверждение MCS от локального подавления повторов. Добиваться устранения причины серверного повтора, но не объявлять результат доказанным без проверки нового потока Google. Оставить Google/PushMe регистрации и состав групп нетронутыми. Единый диагностический инструмент занимает доступную площадь справа; журнал должен быть действительно большим.
