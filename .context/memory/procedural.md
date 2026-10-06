# Procedural memory

## Procedure — determine whether a Mail.ru endpoint belongs to the current API scope

1. Do not classify by hostname or literal parameter name.
2. Trace the official-client command to its authorization type and session setup.
3. Prove that the credential value originates from mailbox OAuth token `ru.mail.oauth2.access`.
4. Record actual transport: query `access_token`, alternate query key such as `t`, authorization header, or another proven carrier.
5. Extract route, host resource, method, `@Param` fields and response parsing from decompiled command/base classes.
6. Treat this as static contract evidence.
7. Before product enablement, make a controlled live call with at least five seconds spacing from other research/probe calls; sanitize and persist the result.
8. Never commit real tokens, credentials or private mailbox payloads.

For large APKs, retain a focused decompiled network corpus plus targeted single-class extraction for missing classes. Maintain machine-readable contract/audit reports so manual summaries can be checked for omissions.
