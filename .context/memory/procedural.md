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


## Procedure — v0.3.28 sender binding, HTML resources and palette migration

When detached New Mail is opened, make sender identity explicit and bind transport identity to that composition instance, not the mutable main-window current account. Display the sender address read-only; use the bound token/account for contact list, attachment uploads, drafts and sends; fail safely if no valid token remains instead of switching senders. Code PR #94 requires owner runtime acceptance.

For HTML mail, distinguish UI navigation timing from network image loading. Prevent prior navigation completion from exposing a stale message; cancel obsolete image preparation on selection changes and use a visible neutral loading indication until final document is ready. Do not execute arbitrary received scripts in WebView2. Embedded images in afNN.mail.ru URLs may need the existing access-token attachment endpoint rather than the browser-cookie URL. Validate host, message ID, mailbox, mode, image byte signature and size; never relay arbitrary private URLs or log secrets. Private image success requires authorized user-side check.

If Owner asks to consolidate overly similar palette controls, keep old WPF resource keys and saved settings legible; use a canonical editable role with alias resolution, honoring explicit canonical overrides first and migrating a previously edited alias if no canonical edit exists. Current count: 20 editable controls, 26 resource names. Source-generated catalog must list all windows including new runtime-created ones (9 windows, 1371 entries).

Never infer that a CI pass proves an actual Windows titlebar is dark, no mail flickers, or a private image is accessible. Record owner acceptance separately.


## Проверка действующего интерфейса вместо только сборки (2026-10-09)

Изменение исходного кода и успешный запуск программы недостаточны для приёмки требований владельца. Для v0.3.29 добавлен tests/WindowsUiSmoke: на Windows создаются реальные окна, открываются настройки и новое письмо, проверяются расположение цветовых настроек, подписи и шаблоны, адрес отправителя, высота уведомления и реальная перерисовка битовой шкалы яркости при выборе основного цвета. Операции с реальным аккаунтом и частными HTML-изображениями по-прежнему требуют подтверждения пользователя.

Особые выводы: при изменении размера фотографии нужно масштабировать и вложенное Image, и внешнюю рамку Border, и колонку списка; обработчик SelectionChanged не получает повторный щелчок уже выделенной папки; автоматическая перекраска при каждой активации приложения вызывает избыточные перерисовки HTML; при объединении нескольких старых ролей цвета использовать явный общий цвет или первое допустимое старое значение, а не последнее из прохода.


## Процедура v0.3.30 — отделять тело письма от изображений и беречь Save As

Если HTML-письмо открывается медленно, проверить не только сервер получения тела: прежний MainWindow последовательно дожидался каждой картинки в PrepareMailHtmlAsync перед ShowReaderHtml. Такой подход заставляет основной текст ждать медленного внешнего сервера. Правильный порядок: получить тело письма; передать HTML просмотрщику; показать текст по DOMContentLoaded; ресурсы загружать по запросам браузера асинхронно. При новой активной записи отменять устаревший полнотекстовый запрос. Сохранять замеры fetch-ms/body-ready-ms без содержания писем/токенов. Для afNN.mail.ru/CGI readmsg проверять точное сообщение, аккаунт, mode=attachment, numeric id, HTTPS, хост из белого списка, сигнатуру изображения и ограничение размера; использовать существующий OAuth API загрузки вложений. Внешнему прокси не пересылать Cookie текущего почтового аккаунта.

Markdown Save As: целевое имя в поле определяет целевой файл; если имя новое, создавать копию и не удалять/не переименовывать первоначально выбранный файл. При существующем имени предупреждать с подтверждением и отменой, включая случай редактирования собственного файла. Метод хранилища не должен неявно переименовывать при previousName != Name. Для клона копировать его вложения из папки первоначального шаблона в собственную папку. У «Загрузить .md» начальная папка — TemplateFiles.DirectoryPath, а не загрузки вложений. Автономные испытания обязательно покрывают целостность оригинала, независимость копии, запрет перезаписи без согласия и разрешение перезаписи совпадающего имени.

Для Send Now исходящего запланированного письма нельзя вызывать обычный send на копии письма: оно может отправиться повторно позже. Только установленная атомарная операция изменения существующего серверного расписания, с проверенным единичным результатом и поведением при тайм-ауте, позволит включить управление. Подробности в docs/protocol/scheduled-send-now-safety.md.


## v0.3.31 — предотвращение видимой перестройки HTML-писем

Подтверждённое видео владельца K-001.mp4 показало недостаток v0.3.30: отображение на DOMContentLoaded выдаёт сначала текст, после чего изображения меняют размеры таблицы и заставляют содержимое прыгать. DOMContentLoaded подтверждает разметку, но не готовность всех изображений. Не раскрывать видимое WebView2 только на этом событии. Держать нейтральную загрузку; сопоставлять NavigationId и поколение выбранного письма; отдельно ждать document.images[...].complete и завершения навигации. Для ленивых изображений можно запросить eager через строго постоянный скрипт, инициированный приложением, при отключённых сценариях из самого письма. Время ожидания должно быть ограничено: в v0.3.31 максимум 3 секунды с остановкой незавершённых ресурсов до раскрытия, чтобы запоздавшая картинка не перестраивала уже видимый текст. Компромисс — медленная картинка может не загрузиться вообще, что подлежит проверке владельцем; не скрывать такую потерю качества. Данные журнала reader_visual_ready: только reason=complete/limited и elapsed-ms, без личного содержимого. Прохождение сборки не заменяет испытания пользователя на том же письме.


## Правило v0.3.32: один постоянный WebView2, без WPF-перекрытия

При смене почтового письма запрещены циклы MessageWebView.Visibility=Hidden/Visible, перекрытие WebView2 обычным WPF Border/Grid с Loading, CoreWebView2.Stop() и повторный NavigateToString полного документа. Взаимодействие WPF и браузерной области визуализации асинхронно; предыдущее видео пользователя показывало старое содержимое под новым чеком. В v0.3.32 WebView2 и его основной документ создаются ОДИН раз; статус загрузки является обычным HTML в нём; данные письма создаются как изолированный sandboxed iframe с отключёнными сценариями. Приложение передаёт HTML через JsonSerializer.Serialize, никогда как исполняемые управляющие инструкции; лишь доверенный ExecuteScriptAsync из приложения управляет размещением. Пока готовится письмо, пользователь видит единую браузерную область загрузки; при готовности (или ограниченном ожидании с нейтрализацией поздних изображений) старый iframe заменяется новым в одной операции браузера. Сопоставлять поколение выбора, ID письма и актуальный аккаунт, отбрасывать поздние запросы. Заголовок WPF обновлять после успешной публикации тела, а не сразу при нажатии строки.

В автоматическом испытании обязательно создать реальный WPF WebView2, подготовить и показать сначала тестовое письмо A с data: GIF, затем B; проверить одно активное внутреннее содержимое, 0 повторных корневых NavigationStarting и постоянно Visibility.Visible. Обычная компиляция не доказывает этого. Реальное исчезновение мерцания и полноту встроенных изображений на пользовательской авторизованной сессии подтверждает только владелец.
