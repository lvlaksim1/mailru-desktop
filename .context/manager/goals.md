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

## G-007 — Excel direct mailing end-to-end acceptance
Bind the selected workbook, worksheet and active starting row despite other open Excel files, respecting hidden/filter rows and sender quotas. The single «Отправить» action must process each row, look up organization by INN and fall back to Excel «Наименование работодателя», render styled HTML and clean text, schedule via existing Mail.ru AJ /schedule, and write only two original row cells after server acceptance. No preview/correction/history/pause interface. Preserve per-letter DPAPI journal, no blind repeat of unknown server outcomes, and protected Excel writeback recovery. Require small real Excel/Mail.ru acceptance before mass use.

## G-008 — Stable Excel workbook attachment for direct mailing
Treat a selected workbook as an exact full-path Excel document rather than a stable Excel.Application.Hwnd. Preserve its selected worksheet when refreshing discovery. Reconnect an obsolete COM proxy only to a unique matching full path, never another active or same-basename workbook; require manual reselection after Save As/path change or ambiguity. Apply the same identity rule to direct send and confirmed-result Excel writeback. Obtain owner acceptance on actual Microsoft Excel before closing I-091.

## G-009 — Editable Excel mailing journal and fully disabled logging
Offer editable DPAPI-protected historical mailing runs (inspect, modify, save or delete local jobs/runs) without modifying actual Mail.ru or Excel. Provide per-profile, default-on persisted “Записывать в журнал” checkbox. When unchecked, DO NOT read, reconcile or write the mailing-run journal, including previously used protection and deduplication; schedule each mail using only transient in-memory state and still write back confirmed results to the two Excel cells. Explicit owner accepts duplicates risk. Keep both modes testable with offline Windows UI and transport fakes.

## G-010 — Per-member conversation replies and forwards
Give every server-verified individual message in a conversation its own Reply and Forward actions, each operating on the exact clicked message ID, body, To/Cc, subject and attachments where appropriate. Keep global actions on the main selected/latest mail. Route both forward actions through the existing separate rich compose window (prepopulate the original To addresses, Fwd: subject, message content and attachments). Reply uses old To/Cc participant gathering and replies to chosen ID. Never archive a source merely because it was forwarded, never silently substitute the last message. Validate browser action commands against actual server-proven conversation members.
