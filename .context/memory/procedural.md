# Procedural memory

## Procedure — determine whether a Mail.ru endpoint belongs to the current API scope

1. Do not classify by hostname or literal parameter name.
2. Trace the official-client command to its authorization type and session setup.
3. Prove that the credential value originates from mailbox OAuth token `ru.mail.oauth2.access`.
4. Record actual transport: query `access_token`, alternate query key such as `t`, authorization header, or another proven carrier.
5. Extract route, host resource, method, parameters and response parsing from decompiled command/base classes.
6. Treat this as static contract evidence.
7. Before product enablement, make a controlled live call with at least five seconds spacing from other research/probe calls; sanitize and persist the result.
8. Never commit real tokens, credentials or private mailbox payloads.

For large APKs, retain a focused decompiled network corpus plus targeted single-class extraction for missing classes. Maintain machine-readable contract/audit reports so manual summaries can be checked for omissions.

## Procedure — live validation of destructive mail operations

- Prefer a disposable object created by the probe rather than touching an existing user message.
- For permanent-remove validation, create a uniquely named test draft, locate it in Drafts, move it to Trash, then call the permanent-remove route. This matches the verified server lifecycle and allows clean test-data removal.
- Always preserve and restore pre-existing reversible state such as flagged/pinned rather than assuming the initial value.
- A server denial from the wrong lifecycle state is evidence about operation semantics, not proof that the endpoint itself is broken.
- Keep at least five seconds between every network request in the probe.


## Procedure — include dynamically constructed windows in UI registry (2026-10-08)

A source-derived catalogue that records `new Window` as an element of MainWindow but does not register it as a distinct window violates the all-window inventory. Count every runtime-created Window as an independent window, associate subsequently created controls with its runtime window and annotate any XAML subtree reparented at runtime (e.g. ComposeWorkspace) with `runtime_host_window`. Verify the generator itself as well as the checked-in generated file; passing `--check` on an incomplete generator is not evidence of complete inventory. Corrected in PR #91, after v0.3.26 code release.


## Procedure — resilient GitHub update discovery (2026-10-08)

For public MailRu Desktop releases, prefer https://api.github.com/repos/lvlaksim1/mailru-desktop/releases/latest metadata and an exact version-matching update installer asset. If API access fails, a GET with redirects disabled to https://github.com/lvlaksim1/mailru-desktop/releases/latest can return a trusted Location release tag; strictly validate HTTPS github.com, repository path and semver vX.Y.Z before deriving the release-policy installer address. Never accept untrusted redirect domains. On failure of both independent hosts, report sanitized per-host HTTP status/timeout class and offer an external browser release page rather than only a generic «Не удалось проверить обновления». Test against mock HTTP handlers (normal API, 403 site fallback, malicious redirect, two-host failures) to avoid live repeated probes. GitHub site/browser may still be unreachable in the Owner's environment; only local runtime evidence can resolve that. Existing installed clients without this fallback need one manual update.
