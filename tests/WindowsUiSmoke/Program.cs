using System.IO;
using System.Net.Http;
using System.Diagnostics;
using System.Reflection;
using System.Windows.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Documents;
using System.Windows.Data;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using MailRuDesktop.App;

internal static class Program
{
    [STAThread]
    private static int Main()
    {
        Window? mail = null;
        Window? editor = null;
        Window? notice = null;
        Window? colorPicker = null;
        try
        {
            var app = new App();
            app.InitializeComponent();

            // Portable backups: authenticated archives, but no tokens in payload.
            {
                var model = new PortablePackage
                {
                    Accounts = [new PortableAccount { Login = "test@example.invalid", Password = "a secret" }]
                };
                var encrypted = PortableBackupCrypto.Encrypt(model, "correct-password");
                var restored = PortableBackupCrypto.Decrypt(encrypted, "correct-password");
                if (restored.Accounts.Count != 1 ||
                    restored.Accounts[0].Password != "a secret" ||
                    restored.Accounts[0].Login != "test@example.invalid")
                    throw new InvalidOperationException("Encrypted backup round-trip failed.");
                var wrongPasswordRejected = false;
                try { PortableBackupCrypto.Decrypt(encrypted, "wrong-password"); }
                catch (InvalidDataException) { wrongPasswordRejected = true; }
                if (!wrongPasswordRejected)
                    throw new InvalidOperationException("Encrypted backup accepted a wrong password.");
                PortableSettingsService.Validate(restored);
                Console.WriteLine("PASS encrypted settings backup round-trip");
            }


            // Browser password-reset capture: only the exact confirmation POST is used.
            {
                var resetEvent = System.Text.Json.JsonSerializer.Serialize(new
                {
                    request = new
                    {
                        url = "https://account.mail.ru/api/v1/user/password/restore/confirm",
                        method = "POST",
                        postData = "type=restore&password=Test%2BPass%26%21&flag=1"
                    }
                });
                Expect(WebView2NetworkJournal.ExtractConfirmedPasswordSubmission(resetEvent),
                    "Test+Pass&!", "password-reset confirmation form decodes special characters");
                var wrongHost = resetEvent.Replace("account.mail.ru", "example.invalid");
                Expect(WebView2NetworkJournal.ExtractConfirmedPasswordSubmission(wrongHost),
                    (string?)null, "other domains cannot set the recovery password");
                var wrongRoute = resetEvent.Replace("/confirm", "/strength");
                Expect(WebView2NetworkJournal.ExtractConfirmedPasswordSubmission(wrongRoute),
                    (string?)null, "strength checks cannot set the recovery password");
            }



            // Unique 32-character IDs follow the original Android send command.
            var generatedIds = Enumerable.Range(0, 128)
                .Select(_ => MailRuDesktop.Protocol.MailRuClient.CreateMessageId())
                .ToArray();
            Check(generatedIds.All(id => id.Length == 32 &&
                      id.All(char.IsLetterOrDigit)) &&
                  generatedIds.Distinct(StringComparer.Ordinal).Count() == generatedIds.Length,
                "outgoing message IDs are per-operation unique 32-character tokens");

            // One rendering pipeline for both the preview and the outgoing
            // HTML payload: formatting tags must not leak into plain text.
            var sampleMarkdown = "**Важное** *курсив* <u>подчёркнуто</u> " +
                "<s>зачёркнуто</s> <span style=\"color:#FF0000\">цвет</span>\n" +
                "[ссылка](https://example.com)\n# Заголовок";
            var formattedMail = MailRuDesktop.App.Mailing.MailingMarkdownFormatting
                .Render(sampleMarkdown);
            Check(formattedMail.PlainText.Contains("Важное курсив подчёркнуто", StringComparison.Ordinal), "plain text includes words");
            Check(!formattedMail.PlainText.Contains("**", StringComparison.Ordinal) && !formattedMail.PlainText.Contains("<u>", StringComparison.Ordinal), "plain text hides raw tags");
            Check(formattedMail.Html.Contains("font-weight:bold", StringComparison.Ordinal), "HTML bold");
            Check(formattedMail.Html.Contains("font-style:italic", StringComparison.Ordinal), "HTML italic");
            Check(formattedMail.Html.Contains("text-decoration:underline", StringComparison.Ordinal), "HTML underline");
            Check(formattedMail.Html.Contains("text-decoration:line-through", StringComparison.Ordinal), "HTML strikethrough");
            Check(formattedMail.Html.Contains("color:#FF0000", StringComparison.Ordinal), "HTML color");
            Check(formattedMail.Html.Contains("href=\"https://example.com", StringComparison.Ordinal), "HTML link");
            Check(formattedMail.Html.Contains("font-size:20", StringComparison.Ordinal), "HTML heading");
            Check(!formattedMail.Html.Contains("**Важное**", StringComparison.Ordinal), "HTML removed markdown syntax");
            Check(new TextRange(formattedMail.Document.ContentStart,
                      formattedMail.Document.ContentEnd).Text.Contains("Важное курсив", StringComparison.Ordinal),
                "mailing preview WPF document displays rendered content rather than markdown tags");
            // Inspect the ACTUAL AJ POST form, not just the formatted preview.
            using (var record = new MailingWireRecorder())
            using (var http = new HttpClient(record))
            using (var client = new MailRuDesktop.Protocol.MailRuClient(httpClient: http))
            {
                var scheduled = client.SendMessageAsync("test-token",
                    new MailRuDesktop.Protocol.MailRuOutgoingMessage(
                        To: "recipient@example.test", Subject: "Тест оформления",
                        Text: formattedMail.PlainText, Html: formattedMail.Html,
                        SendDate: "1791507600",
                        MessageId: MailRuDesktop.Protocol.MailRuClient.CreateMessageId()))
                    .GetAwaiter().GetResult();
                var encodedBody = record.LastForm.Split('&')
                    .First(x => x.StartsWith("body=", StringComparison.Ordinal))
                    ["body=".Length..].Replace("+", " ", StringComparison.Ordinal);
                var decodedBody = Uri.UnescapeDataString(encodedBody);
                using var posted = System.Text.Json.JsonDocument.Parse(decodedBody);
                var htmlSent = posted.RootElement.GetProperty("html").GetString() ?? "";
                var textSent = posted.RootElement.GetProperty("text").GetString() ?? "";
                Check(scheduled.Success && record.LastPath == "/api/v1/messages/schedule" &&
                      htmlSent.Contains("font-weight:bold", StringComparison.Ordinal) &&
                      htmlSent.Contains("color:#FF0000", StringComparison.Ordinal) &&
                      htmlSent.Contains("href=\"https://example.com", StringComparison.Ordinal) &&
                      !textSent.Contains("**", StringComparison.Ordinal) &&
                      !textSent.Contains("<u>", StringComparison.Ordinal),
                    "actual scheduled Mail.ru form carries formatted HTML and clean text");
            }



            // Reply-to-thread is To/Cc only; sender, blind-copy and the
            // current mailbox never become automatic recipients.
            var threadMessages = new[]
            {
                new MailRuDesktop.Protocol.MailRuFullMessage(
                    "id-1", "subject", "Sender", "sender@other.test",
                    new[] { "First@example.test", "me@example.test" },
                    new[] { "Second@example.test" }, null, "", "",
                    Array.Empty<MailRuDesktop.Protocol.MailRuIncomingAttachment>(), "")
                {
                    Bcc = new[] { "blind@example.test" }
                },
                new MailRuDesktop.Protocol.MailRuFullMessage(
                    "id-2", "subject", "Another", "another@other.test",
                    new[] { "first@EXAMPLE.test", "Third@example.test" },
                    new[] { "SECOND@example.test" }, null, "", "",
                    Array.Empty<MailRuDesktop.Protocol.MailRuIncomingAttachment>(), "")
            };
            var ownOutgoing = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "own-last", "Re: Договор", "Продолжение", "Я", "me@example.test",
                null, null, 0, false, false, false)
            {
                ToAddresses = ["client@example.test", "me@example.test"],
                CcAddresses = ["copy@example.test", "CLIENT@example.test"]
            };
            var selectedOwnAddresses = ConversationReplyAddresses.CollectSelected(
                ownOutgoing, null, "ME@example.test");
            Expect(string.Join(";", selectedOwnAddresses),
                "client@example.test;copy@example.test",
                "reply to own last message takes only its To/Cc and excludes self");
            Check(ConversationReplyAddresses.IsOwnSender(
                      "Me <me@example.test>", "ME@example.test") &&
                  !ConversationReplyAddresses.IsOwnSender(
                      "other@example.test", "me@example.test"),
                "own outgoing message is identified by normalized From address");
            var parsedOwn = MailRuDesktop.Protocol.MailRuFullMessageParser.Parse(
                """
                {"body":{"messages":[{"id":"own-last","thread_id":"thread-of-ours",
                "subject":"Re: Договор","body":{"text":"Продолжение"},
                "correspondents":{"from":[{"email":"me@example.test"}],
                "to":[{"email":"client@example.test"}]}}]}}
                """, "own-last");
            Check(parsedOwn.ThreadId == "thread-of-ours" &&
                  parsedOwn.To.Count == 1,
                "full message retains explicit server thread ID for reply recovery");
            // The browser renders native-receiver commands only for exact,
            // server-verified member IDs. Reply and forwarding must target
            // the clicked letter rather than the last member.
            var conversationForActions = new MailRuDesktop.Protocol.MailRuConversation(
                "id-2", "thread-verified", 2,
                [
                    new MailRuDesktop.Protocol.MailRuConversationMember(
                        "id-1", "Sender", "sender@other.test", "First", "Original", null, null),
                    new MailRuDesktop.Protocol.MailRuConversationMember(
                        "id-2", "Another", "another@other.test", "Second", "Last", null, null)
                ]);
            var actionNonce = Guid.NewGuid().ToString("N");
            var replyLink = ConversationActionNavigation.Create("reply", "id-1", actionNonce);
            var forwardLink = ConversationActionNavigation.Create("forward", "id-2", actionNonce);
            var secureMarkup = MailRuConversationHtml.Render(
                conversationForActions, "id-2",
                new Dictionary<string, MailRuDesktop.Protocol.MailRuFullMessage>
                { ["id-1"] = threadMessages[0], ["id-2"] = threadMessages[1] },
                actionNonce: actionNonce);
            Check(secureMarkup.Contains(System.Net.WebUtility.HtmlEncode(replyLink)) &&
                  secureMarkup.Contains(System.Net.WebUtility.HtmlEncode(forwardLink)) &&
                  secureMarkup.Contains(">Ответить</a>") &&
                  secureMarkup.Contains(">Переслать</a>"),
                "each verified letter gets native reply/forward links without scripts");
            Check(ConversationActionNavigation.TryParse(replyLink, actionNonce,
                    out var clickedAction, out var clickedId) &&
                  clickedAction == "reply" && clickedId == "id-1" &&
                  ConversationMessageActions.IsVerifiedMember(conversationForActions, clickedId),
                "native action resolves the exact clicked mail ID");
            // Both the main and detached readers use the same Mail.ru image
            // handler. Inline images may belong to any VERIFIED displayed
            // message of a conversation, never another mailbox/message.
            var verifiedInline = new Uri(
                "https://af12.mail.ru/cgi-bin/readmsg?mode=attachment" +
                "&id=id-2%3B5%3B7&email=me%40example.test");
            Check(MailImageProxyHandler.TrySelectInlineImage(
                      verifiedInline, "me@example.test", new[] { "id-1", "id-2" },
                      out var resolvedImageMailId, out var resolvedImageAttachmentId) &&
                  resolvedImageMailId == "id-2" &&
                  resolvedImageAttachmentId == "5;7",
                "separate reader may load inline images from another verified conversation member");
            Check(!MailImageProxyHandler.TrySelectInlineImage(
                      verifiedInline, "other@example.test", new[] { "id-1", "id-2" },
                      out _, out _) &&
                  !MailImageProxyHandler.TrySelectInlineImage(
                      verifiedInline, "me@example.test", new[] { "id-1" },
                      out _, out _) &&
                  !MailImageProxyHandler.TrySelectInlineImage(
                      new Uri("https://malicious.example/cgi-bin/readmsg?" +
                              "mode=attachment&id=id-2%3B5%3B7&email=me%40example.test"),
                      "me@example.test", new[] { "id-2" }, out _, out _),
                "shared image handler refuses a different account, message or host");
            Check(!ConversationActionNavigation.TryParse(replyLink, "wrong-token",
                      out _, out _) &&
                  !ConversationActionNavigation.TryParse(
                      ConversationActionNavigation.Create("forward", "guessed-id", actionNonce),
                      "wrong-token", out _, out _) &&
                  !ConversationActionNavigation.IsCommandUrl("https://other.example/mail") &&
                  !ConversationMessageActions.IsVerifiedMember(
                      conversationForActions, "guessed-id"),
                "external, forged and stale action links cannot invoke mailing actions");
            Check(!ReaderShellScripts.Commit(17).Contains("conversation-action") &&
                  !ReaderShellScripts.CreateShell("#ffffff").Contains("<script"),
                "untrusted mail readers require no clickable JavaScript handler");
            var richForwardSource = threadMessages[0] with
            {
                Html = "<p>Важное <strong>письмо</strong> и <em>условие</em></p>",
                Text = "Важное письмо и условие"
            };
            var markdownForward = MainWindow.BuildForwardMarkdown(richForwardSource);
            Check(markdownForward.Contains("**письмо**") &&
                  markdownForward.Contains("*условие*") &&
                  markdownForward.Contains("От: " + richForwardSource.SenderDisplay),
                "forwarded HTML retains common bold and italic styles and original sender");
            var spacedForward = richForwardSource with
            {
                Html = "<div>Добрый день.</div><div><br></div>" +
                       "<div>Прошу предоставить сведения.</div><div><br></div>" +
                       "<div><br></div><div>С уважением, <strong>Имя</strong></div>" +
                       "<p>Первый абзац.</p><p>Второй абзац.</p>",
                Text = "Добрый день.\n\nПрошу предоставить сведения.\n\n\n" +
                       "С уважением, Имя\nПервый абзац.\n\nВторой абзац."
            };
            var spacedForwardMarkdown = MainWindow.BuildForwardMarkdown(spacedForward);
            Check(spacedForwardMarkdown.Contains(
                      "Добрый день.\n\nПрошу предоставить сведения.", StringComparison.Ordinal) &&
                  spacedForwardMarkdown.Contains(
                      "Прошу предоставить сведения.\n\n\nС уважением, **Имя**",
                      StringComparison.Ordinal) &&
                  spacedForwardMarkdown.Contains(
                      "Первый абзац.\n\nВторой абзац.", StringComparison.Ordinal),
                "forward conversion retains original blank divs, repeated empty lines and paragraph spacing");

            var replyAddresses = ConversationReplyAddresses.Collect(
                threadMessages, "ME@example.test");
            Expect(string.Join(";", replyAddresses.Select(x => x.ToLowerInvariant())),
                "first@example.test;second@example.test;third@example.test",
                "group reply includes deduplicated To/Cc only, excluding sender, Bcc and self");
            Expect(EmailAddressText.ForClipboard(
                "От: Иван Иванов <ivan@example.com>, Пётр <petr@example.com>"),
                "ivan@example.com, petr@example.com",
                "address copy strips names and labels");
            var counterpart = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "c1", "Тема", "Текст", "Владелец", "me@example.test",
                null, null, 500001, false, false, false)
            {
                ToAddresses = new[] { "Пётр <petr@example.com>",
                    "Мария <maria@example.com>", "me@example.test" }
            };
            var counterpartShown = MailCounterparty.Resolve(
                counterpart, "me@example.test", sentFolder: true);
            Check(counterpartShown.CorrespondentDisplay.Contains("petr@example.com") &&
                  counterpartShown.CorrespondentDisplay.Contains("maria@example.com") &&
                  !counterpartShown.CorrespondentDisplay.Contains("me@example.test") &&
                  counterpartShown.SenderEmail == "me@example.test",
                "outgoing correspondence shows recipients but preserves sender");
            var inboundCounterpart = counterpart with
            {
                SenderName = "Олег", SenderEmail = "oleg@example.com",
                ToAddresses = new[] { "me@example.test" }
            };
            var inboundShown = MailCounterparty.Resolve(
                inboundCounterpart, "me@example.test", sentFolder: false);
            Check(inboundShown.CorrespondentDisplay.Contains("oleg@example.com"),
                "incoming correspondence shows the other party");
            var listRows = new System.Collections.ObjectModel.ObservableCollection<
                MailRuDesktop.Protocol.MailRuMessageSummary> { inboundShown, counterpartShown };
            var changes = new List<System.Collections.Specialized.NotifyCollectionChangedAction>();
            listRows.CollectionChanged += (_, args) => changes.Add(args.Action);
            MailListReconciliation.Apply(listRows, new[]
            {
                inboundShown, counterpartShown with { Unread = true }
            });
            Check(listRows.Count == 2 && ReferenceEquals(listRows[0], inboundShown) &&
                  listRows[1].Unread && !changes.Contains(
                      System.Collections.Specialized.NotifyCollectionChangedAction.Reset),
                "refresh patches row without empty reset");
            var copyableText = new SelectableText { Text = "Тема письма" };
            Check(copyableText.IsReadOnly && copyableText.IsReadOnlyCaretVisible,
                "selectable read-only text supports highlighting and copying");
            var readerHeader = new MailReaderHeader();
            Check(readerHeader.SubjectText is SelectableText &&
                  readerHeader.DateText is SelectableText,
                "reader header subject and date are selectable");

            // Markdown signatures are discovered from files, not an independent JSON list.
            // Old values migrate without deleting the settings.json backup.
            var signaturesTemp = Path.Combine(Path.GetTempPath(),
                "MailRuSignatures-" + Guid.NewGuid().ToString("N"));
            try
            {
                var store = new MarkdownSignatureStore(signaturesTemp);
                store.ImportLegacyOnce(new[]
                {
                    new SavedSignature { Name = "Рабочая", Body = "**С уважением**" }
                });
                Expect(store.LoadAll().Count, 1, "legacy signature migrates to one .md file");
                Expect(store.LoadAll()[0].Body, "**С уважением**",
                    "Markdown signature body is preserved byte-for-byte");
                File.WriteAllText(Path.Combine(signaturesTemp, "Личная.md"), "С уважением,\nПользователь");
                Expect(store.LoadAll().Count, 2,
                    "manually added .md signature is visible without application UI");
                store.ImportLegacyOnce(new[]
                {
                    new SavedSignature { Name = "Рабочая", Body = "OLD AGAIN" }
                });
                Expect(store.LoadAll().Single(x => x.Name == "Рабочая").Body,
                    "**С уважением**", "migration does not overwrite managed signature files");
                store.Save(new SavedSignature { Name = "Личная", Body = "Новый вариант" });
                Expect(File.ReadAllText(Path.Combine(signaturesTemp, "Личная.md")),
                    "Новый вариант", "saving a signature updates its standalone file");
                store.Delete("Личная");
                Expect(store.LoadAll().Count, 1, "deleting a signature removes its .md file");
                try
                {
                    store.Save(new SavedSignature { Name = "../bad", Body = "x" });
                    throw new Exception("Traversal should have failed");
                }
                catch (ArgumentException) { }
            }
            finally
            {
                if (Directory.Exists(signaturesTemp))
                    Directory.Delete(signaturesTemp, recursive: true);
            }
            
            Expect(SignaturePlacement.Replace("Текст\n\nС уважением", "С уважением",
                "Новая подпись"), "Текст" + Environment.NewLine + Environment.NewLine +
                "Новая подпись", "replacement avoids duplicate signature at end");
            Expect(SignaturePlacement.Replace("Текст\n\nС уважением", null,
                "Новая подпись", new[] { "С уважением" }),
                "Текст" + Environment.NewLine + Environment.NewLine + "Новая подпись",
                "existing known signature from a template is replaced");
            Expect(SignaturePlacement.Replace("Цитата: С уважением\nДалее текст", null,
                "Подпись", new[] { "С уважением" }),
                "Цитата: С уважением\nДалее текст" + Environment.NewLine +
                Environment.NewLine + "Подпись", "quoted signatures inside body are preserved");
            Expect(SignaturePlacement.Replace("Письмо\n\nПодпись", "Подпись",
                "Подпись"), "Письмо" + Environment.NewLine +
                Environment.NewLine + "Подпись", "reselecting signature does not duplicate it");
            Expect(SignaturePlacement.Replace("Письмо\nПодпись", "Подпись",
                "Новая"), "Письмо" + Environment.NewLine +
                Environment.NewLine + "Новая",
                "existing signature with a single separator line is replaced");

            
            var geometryTemp = Path.Combine(Path.GetTempPath(),
                "MailRuWindowBounds-" + Guid.NewGuid().ToString("N"));
            try
            {
                var state = new AppSettingsStore(geometryTemp);
                state.SaveWindowGeometry("compose", new SavedWindowGeometry
                { Left = 100, Top = 110, Width = 880, Height = 720 });
                state.SaveWindowGeometry("reply", new SavedWindowGeometry
                { Left = 220, Top = 230, Width = 940, Height = 760 });
                var reopened = new AppSettingsStore(geometryTemp);
                Expect(reopened.LoadWindowGeometry("compose")!.Left, 100.0,
                    "new-mail location survives restart");
                Expect(reopened.LoadWindowGeometry("reply")!.Width, 940.0,
                    "reply window size is independent from new-mail size");
            }
            finally
            {
                if (Directory.Exists(geometryTemp))
                    Directory.Delete(geometryTemp, recursive: true);
            }
            
            
            // The native spelling option belongs to application settings, not a cloud service.
            var spellSettingsRoot = Path.Combine(Path.GetTempPath(),
                "MailRuSpellCheck-" + Guid.NewGuid().ToString("N"));
            try
            {
                var spellSettings = new AppSettingsStore(spellSettingsRoot);
                Check(spellSettings.LoadSpellCheckEnabled(),
                    "spelling is enabled by default for an existing installation");
                spellSettings.SaveSpellCheckEnabled(false);
                Check(!new AppSettingsStore(spellSettingsRoot).LoadSpellCheckEnabled(),
                    "spelling toggle survives restart");
                spellSettings.SaveSpellCheckEnabled(true);
                Check(new AppSettingsStore(spellSettingsRoot).LoadSpellCheckEnabled(),
                    "spelling can be re-enabled after restart");
            }
            finally
            {
                if (Directory.Exists(spellSettingsRoot))
                    Directory.Delete(spellSettingsRoot, recursive: true);
            }

            // The test owns the STA thread without Application.Run().
            // Preserve WPF's dispatcher synchronization context across await.
            SynchronizationContext.SetSynchronizationContext(
                new DispatcherSynchronizationContext(Dispatcher.CurrentDispatcher));
            // Formatting survives signature changes; a selection must replace
            // rather than append the same signature twice.
            // A readable diagnostic must expose the exact action, stage and
            // known account. It must never attribute older anonymous entries
            // to whatever mailbox happens to be selected in the UI.
            var sampleAppLog =
                "2026-10-25T22:25:24.3230000+03:00\tauth_token_flow\t" +
                "account=ACCOUNT_011; flow=background_counter; stage=initial_access; " +
                "result=rejected; http=403; account=one@example.org\n" +
                "2026-10-25T22:25:56.3400000+03:00\tmail_image_inline\t" +
                "loaded; inlineBytes=38475; type=image/png; account=two@example.org\n" +
                "2026-10-25T22:25:57.3400000+03:00\tfolder_load\t" +
                "MailRuAuthorizationException: код 403\n";
            var appFriendly = HumanDiagnostics.Application(sampleAppLog, true);
            Check(appFriendly.Contains("one@example.org", StringComparison.Ordinal) &&
                  appFriendly.Contains("two@example.org", StringComparison.Ordinal) &&
                  appFriendly.Contains("обновление счётчиков", StringComparison.Ordinal) &&
                  appFriendly.Contains("проверка действующего ключа", StringComparison.Ordinal) &&
                  appFriendly.Contains("HTTP 403", StringComparison.Ordinal) &&
                  appFriendly.Contains("Загрузка встроенного изображения", StringComparison.Ordinal) &&
                  appFriendly.Contains("Аккаунт не указан", StringComparison.Ordinal),
                "human diagnostics show action, actual account and failed stage without guessing");
            var appExport = HumanDiagnostics.Application(sampleAppLog, false);
            Check(!appExport.Contains("one@example.org", StringComparison.Ordinal) &&
                  !appExport.Contains("two@example.org", StringComparison.Ordinal) &&
                  appExport.Contains("Аккаунт №001", StringComparison.Ordinal) &&
                  appExport.Contains("Аккаунт №002", StringComparison.Ordinal),
                "shared application report redacts distinct account addresses by default");
            var appTechnical = HumanDiagnostics.Application(sampleAppLog, false, true);
            Check(appTechnical.Contains("auth_token_flow", StringComparison.Ordinal) &&
                  !appTechnical.Contains("one@example.org", StringComparison.Ordinal),
                "technical diagnostics remain available and safe for anonymous export");
            var responseRaw =
                "{\"error\":\"invalid_grant\",\"error_description\":\"refresh expired\"}";
            var responseEncoded = Convert.ToBase64String(
                System.Text.Encoding.UTF8.GetBytes(responseRaw));
            var refreshLine = "2026-10-25T23:38:40+03:00\tauth_refresh_http\t" +
                "flow=active_folder; method=POST; http=200; outcome=rejected; " +
                "raw_base64=" + responseEncoded + "; account=one@example.org\n";
            var refreshHuman = HumanDiagnostics.Application(refreshLine, true);
            Check(refreshHuman.Contains("HTTP 200", StringComparison.Ordinal) &&
                  refreshHuman.Contains("invalid_grant", StringComparison.Ordinal) &&
                  refreshHuman.Contains("refresh expired", StringComparison.Ordinal) &&
                  refreshHuman.Contains(responseRaw, StringComparison.Ordinal) &&
                  refreshHuman.Contains("access_token в корне: отсутствует",
                      StringComparison.Ordinal),
                "refresh diagnostic contains exact HTTP response, original JSON and missing token field");
            var internalDenial = HumanDiagnostics.Application(
                "2026-10-25T23:38:40+03:00\tauth_local_failure\t" +
                "HTTP 403 создан приложением; account=one@example.org\n", true);
            Check(internalDenial.Contains("не HTTP-код нового ответа сервера",
                    StringComparison.Ordinal),
                "local rejection is distinguished from a server HTTP refusal");
            var oldAlias = HumanDiagnostics.Application(
                "2026-10-25T22:25:24+03:00\tauth_token_flow\t" +
                "account=ACCOUNT_030; flow=background_counter; stage=result; " +
                "result=unverified; http=not_available\n", true);
            Check(oldAlias.Contains("старый журнал", StringComparison.Ordinal) &&
                  !oldAlias.Contains("one@example.org", StringComparison.Ordinal),
                "historical numeric account aliases are never mapped to new mailboxes");
            var samplePush = "MailRu Desktop — диагностика Google / PushMe\n" +
                "2026-10-25 22:30:01.000 +03:00 #1 [PUSHME] " +
                "ACCOUNT_SUBSCRIBE_QUEUED account=one@example.org item=1/2\n" +
                "2026-10-25 22:30:05.000 +03:00 #2 [ERROR] " +
                "GROUP_ACCOUNT_REMOVE_HTTP_403 account=two@example.org\n";
            var readablePush = HumanDiagnostics.Push(samplePush, true);
            Check(readablePush.Contains("one@example.org", StringComparison.Ordinal) &&
                  readablePush.Contains("two@example.org", StringComparison.Ordinal) &&
                  readablePush.Contains("поставлен в очередь", StringComparison.Ordinal) &&
                  readablePush.Contains("ОШИБКА", StringComparison.Ordinal) &&
                  readablePush.Contains("Удаление аккаунта из группы", StringComparison.Ordinal),
                "PushMe diagnostics show each mailbox, phase and explicit failure");
            Check(!HumanDiagnostics.Push(samplePush, false).Contains(
                      "one@example.org", StringComparison.Ordinal),
                "PushMe export hides mailboxes by default");
            Check(HumanDiagnostics.Application("", true).Contains("Записей пока нет") &&
                  HumanDiagnostics.Push("", true).Contains("Записей пока нет"),
                "empty diagnostic journals present a readable message");
            var previewTestGrid = new Grid();
            previewTestGrid.RowDefinitions.Add(new RowDefinition());
            var previewTestPlain = new TextBox();
            previewTestGrid.Children.Add(previewTestPlain);
            var previewTestWindow = new Window { Content = previewTestGrid };
            var previewTestRich = RichComposeEditor.Attach(previewTestPlain, previewTestWindow);
            // Both conversation commands use the existing reply-history editor.
            // Earlier reply fixes protect blank Enter paragraphs through HTML
            // generation; forwarded HTML must reach that path without collapse.
            previewTestRich.LoadReplyQuotedMarkdown(spacedForwardMarkdown);
            var forwardedSentHtml = previewTestRich.ToHtml();
            var forwardedEditorText = new TextRange(
                previewTestRich.TextControl.Document.ContentStart,
                previewTestRich.TextControl.Document.ContentEnd).Text;
            Check(forwardedEditorText.Contains(
                      "Добрый день.\r\n\r\nПрошу предоставить сведения.",
                      StringComparison.Ordinal) &&
                  forwardedSentHtml.Contains("<br></p>", StringComparison.Ordinal) &&
                  forwardedSentHtml.Contains("font-weight:bold", StringComparison.Ordinal),
                "forward history preserves visible empty lines and rich formatting in sent HTML");
            previewTestRich.LoadReplyQuotedText(
                "----- Исходное письмо -----\n\nДобрый день.\n\nПрошу сведения.");
            var replySentHtml = previewTestRich.ToHtml();
            Check(replySentHtml.Contains("Добрый день.", StringComparison.Ordinal) &&
                  replySentHtml.Contains("Прошу сведения.", StringComparison.Ordinal) &&
                  replySentHtml.Contains("<br></p>", StringComparison.Ordinal),
                "ordinary and per-message replies retain existing blank paragraph behavior");
            var checkEditor = previewTestRich.TextControl;
            Check(checkEditor is not null, "rich editor surface exists for spell checking");
            Check(string.Equals(checkEditor!.Language.IetfLanguageTag, "ru-RU",
                      StringComparison.OrdinalIgnoreCase) &&
                  string.Equals(checkEditor.Document.Language.IetfLanguageTag, "ru-RU",
                      StringComparison.OrdinalIgnoreCase),
                "Russian compose host and document use ru-RU, not en-US; host=" +
                checkEditor.Language.IetfLanguageTag + ", document=" +
                checkEditor.Document.Language.IetfLanguageTag);
            Check(MailRuDesktop.Protocol.MailRuAvatarUrls.ForEmail(
                      "recipient@example.org") ==
                  "https://filin.mail.ru/pic?email=recipient%40example.org",
                "restored native WPF avatar binding retains the known HTTPS endpoint");
            Check(MailRuDesktop.Protocol.MailRuAvatarUrls.ForEmail(
                      "invalid address@example.org") is null,
                "invalid sender address never becomes an image URL");
            Check(VisualTreeHelper.GetParent(checkEditor!) is Border
                  { BorderThickness.Left: 1, CornerRadius.TopLeft: 5 },
                "rich mail editing area has a rounded visible border");
            ComposeSpellChecking.SetEnabled(false);
            Check(!checkEditor!.SpellCheck.IsEnabled,
                "global spelling switch turns off a live editor");
            ComposeSpellChecking.SetEnabled(true);
            Check(checkEditor.SpellCheck.IsEnabled,
                "global spelling switch turns on a live editor");
            previewTestRich.LoadMarkdown("# Заголовок\n- Пункт\n<u>Подчёркнуто</u>\n[Ссылка](https://example.org)");
            var roundTrip = previewTestRich.ToMarkdown();
            Console.WriteLine("SAFE_MARKDOWN_ROUNDTRIP: " +
                roundTrip.Replace("\n", "\\n", StringComparison.Ordinal));
            Check(roundTrip.Contains("# Заголовок", StringComparison.Ordinal),
                "rich editor preserves heading as Markdown");
            Check(roundTrip.Contains("- Пункт", StringComparison.Ordinal),
                "rich editor preserves bullets as Markdown");
            Check(roundTrip.Contains("<u>Подчёркнуто</u>", StringComparison.Ordinal),
                "rich editor preserves underline as Markdown");
            Check(roundTrip.Contains("[Ссылка](https://example.org/)", StringComparison.Ordinal),
                "rich editor preserves hyperlinks as Markdown");
            // Two additional Enter keystrokes create TWO empty paragraphs,
            // both of which must take space in sent HTML (not <p></p>).
            previewTestRich.LoadMarkdown(
                "Здравствуйте!\n\n\nНаправляю документы.\n\n\nС уважением, Иван.");
            var spacedHtml = previewTestRich.ToHtml();
            Check(spacedHtml.Split("<br></p>", StringSplitOptions.None).Length == 5 &&
                  spacedHtml.Contains("Здравствуйте!", StringComparison.Ordinal) &&
                  spacedHtml.Contains("Направляю документы.", StringComparison.Ordinal) &&
                  spacedHtml.Contains("С уважением, Иван.", StringComparison.Ordinal),
                "outgoing HTML preserves all four empty Enter paragraphs");
            Check(previewTestPlain.Text.Contains(
                    "Здравствуйте!\r\n\r\n\r\nНаправляю документы.", StringComparison.Ordinal),
                "plain-text outgoing body preserves repeated Enter lines");

            previewTestRich.LoadMarkdown("**Жирный текст**\n\n\nВторая строка");
            var richEditor = previewTestRich.TextControl;
            richEditor.SelectAll();
            var editorFrame = (Border)richEditor.Parent;
            var editorHost = (Grid)editorFrame.Parent;
            var cleanButton = editorHost.Children.OfType<WrapPanel>()
                .SelectMany(panel => panel.Children.OfType<Button>())
                .Single(button => button.ToolTip?.ToString() == "Удалить форматирование");
            cleanButton.RaiseEvent(new RoutedEventArgs(
                ButtonBase.ClickEvent, cleanButton));
            var plainAfterClear = previewTestRich.ToHtml();
            Check(!plainAfterClear.Contains("font-weight:bold", StringComparison.Ordinal) &&
                  plainAfterClear.Contains("Жирный текст", StringComparison.Ordinal) &&
                  plainAfterClear.Split("<br></p>", StringSplitOptions.None).Length == 3,
                "clear formatting removes bold without erasing extra empty lines");
            var cleanMarkdown = previewTestRich.ToMarkdown();
            Check(!cleanMarkdown.Contains("**Жирный текст**", StringComparison.Ordinal) &&
                  !cleanMarkdown.Contains("# Жирный текст", StringComparison.Ordinal),
                "saved template Markdown no longer reintroduces cleared formatting");
            previewTestRich.LoadMarkdown(cleanMarkdown);
            Check(!previewTestRich.ToHtml().Contains("font-weight:bold", StringComparison.Ordinal),
                "reopening saved Markdown and sending it keeps formatting removed");

            Check(Equals(richEditor.Selection.GetPropertyValue(
                      TextElement.FontFamilyProperty), richEditor.FontFamily) &&
                  Equals(richEditor.Selection.GetPropertyValue(
                      TextElement.FontSizeProperty), richEditor.FontSize),
                "clear formatting restores application font family and body font size");

            previewTestRich.LoadMarkdown(
                "**Полужирный**\n*Курсив*\n<u>Подчёркнутый</u>\n<s>Зачёркнутый</s>\n" +
                "<span style=\"color:#FF0000\">Красный</span>");
            richEditor.SelectAll();
            cleanButton.RaiseEvent(new RoutedEventArgs(
                ButtonBase.ClickEvent, cleanButton));
            var cleanSaved = previewTestRich.ToMarkdown();
            var cleanSent = previewTestRich.ToHtml();
            Check(!cleanSaved.Contains("**Полужирный**", StringComparison.Ordinal) &&
                  !cleanSaved.Contains("*Курсив*", StringComparison.Ordinal) &&
                  !cleanSaved.Contains("<u>", StringComparison.Ordinal) &&
                  !cleanSaved.Contains("<s>", StringComparison.Ordinal) &&
                  !cleanSaved.Contains("color:#FF0000", StringComparison.OrdinalIgnoreCase),
                "cleared bold/italic/underline/strike/color are absent from saved template");
            Check(!cleanSent.Contains("font-weight:bold", StringComparison.OrdinalIgnoreCase) &&
                  !cleanSent.Contains("font-style:italic", StringComparison.OrdinalIgnoreCase) &&
                  !cleanSent.Contains("text-decoration:", StringComparison.OrdinalIgnoreCase) &&
                  !cleanSent.Contains("color:#FF0000", StringComparison.OrdinalIgnoreCase),
                "cleared bold/italic/underline/strike/color are absent from sent HTML");

            previewTestRich.LoadMarkdown("**Выделенный текст**");
            Check(previewTestRich.ToHtml().Contains("font-weight:bold", StringComparison.Ordinal) &&
                  previewTestRich.ToHtml().Contains("Выделенный текст", StringComparison.Ordinal),
                "template Markdown becomes actual formatted mail HTML");
            previewTestRich.ApplyMarkdownSignature("**Подпись**", new[] { "**Подпись**" });
            previewTestRich.ApplyMarkdownSignature("**Подпись**", new[] { "**Подпись**" });
            var resultingHtml = previewTestRich.ToHtml();
            Check(resultingHtml.Contains("font-weight:bold", StringComparison.Ordinal) &&
                  resultingHtml.Contains("Выделенный текст", StringComparison.Ordinal),
                "changing signature preserves original bold text");
            Check(resultingHtml.Split("Подпись", StringSplitOptions.None).Length == 2,
                "reselecting signature keeps exactly one formatted signature");
            previewTestRich.LoadReplyQuotedText("----- Исходное письмо -----\n> Прежняя переписка\n> С уважением");
            previewTestRich.ApplyTemplateMarkdown("**Новый ответ**\n\n**Подпись**",
                new[] { "**Подпись**" });
            previewTestRich.ApplyMarkdownSignature("Другая подпись",
                new[] { "**Подпись**", "Другая подпись" });
            var structuredReply = previewTestPlain.Text;
            Check(structuredReply.IndexOf("Новый ответ", StringComparison.Ordinal) <
                  structuredReply.IndexOf("Другая подпись", StringComparison.Ordinal) &&
                  structuredReply.IndexOf("Другая подпись", StringComparison.Ordinal) <
                  structuredReply.IndexOf("Исходное письмо", StringComparison.Ordinal) &&
                  structuredReply.Contains("> Прежняя переписка", StringComparison.Ordinal),
                "reply template and signature precede the unchanged historical correspondence");
            Check(!structuredReply.Contains("**Подпись**", StringComparison.Ordinal) &&
                  structuredReply.Split("Другая подпись", StringSplitOptions.None).Length == 2,
                "changing an embedded template signature does not duplicate it");
            previewTestWindow.Content = null;

            mail = new MainWindow();

            // Issue #166: exercise MainWindow's actual acceptance predicate with
            // synthetic credentials; no saved login is modified or used for HTTP.
            var sessionType = typeof(MainWindow).Assembly.GetType(
                "MailRuDesktop.App.FolderLoadIdentity")!;
            var sessionGuard = typeof(MainWindow).GetMethod("IsCurrentFolderLoad",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var sessionGeneration = typeof(MainWindow).GetField("_accountSwitchGeneration",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var sessionLogin = typeof(MainWindow).GetField("_activeLogin",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var sessionToken = typeof(MainWindow).GetField("_accessToken",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var priorGeneration = sessionGeneration.GetValue(mail);
            var priorLogin = sessionLogin.GetValue(mail);
            var priorToken = sessionToken.GetValue(mail);
            try
            {
                sessionGeneration.SetValue(mail, 61L);
                sessionLogin.SetValue(mail, "synthetic@example.invalid");
                sessionToken.SetValue(mail, "SYNTHETIC_TOKEN_NOT_SAVED");
                var inFlight = Activator.CreateInstance(sessionType,
                    61L, "synthetic@example.invalid")!;
                Check(sessionGuard.Invoke(mail, new[] { inFlight }) is true,
                    "WPF window accepts a matching in-flight folder session");
                sessionToken.SetValue(mail, "SYNTHETIC_REFRESHED_TOKEN");
                Check(sessionGuard.Invoke(mail, new[] { inFlight }) is true,
                    "WPF window retains the folder session after normal token refresh");
                sessionGeneration.SetValue(mail, 62L); // Confirmed removal.
                Check(sessionGuard.Invoke(mail, new[] { inFlight }) is false,
                    "WPF window rejects the old response immediately upon deletion");
                sessionLogin.SetValue(mail, null);
                sessionToken.SetValue(mail, null);
                Check(sessionGuard.Invoke(mail, new[] { inFlight }) is false,
                    "WPF window cannot republish a removed mailbox from an old response");
            }
            finally
            {
                sessionGeneration.SetValue(mail, priorGeneration);
                sessionLogin.SetValue(mail, priorLogin);
                sessionToken.SetValue(mail, priorToken);
            }

            var previewTemplateSelector = Require<ComboBox>(mail, "PreviewTemplateComboBox");
            var previewSignatureSelector = Require<ComboBox>(mail, "PreviewSignatureComboBox");
            var previewSelectorRow = (WrapPanel)previewTemplateSelector.Parent;
            Check(previewSelectorRow.Children.IndexOf(previewTemplateSelector) <
                  previewSelectorRow.Children.IndexOf(previewSignatureSelector),
                "reply form places template before signature");
            var composeTemplateSelector = Require<ComboBox>(mail, "ComposeTemplateComboBox");
            var composeSignatureSelector = Require<ComboBox>(mail, "ComposeSignatureComboBox");
            var composeSelectorRow = (WrapPanel)composeTemplateSelector.Parent;
            Check(composeSelectorRow.Children.IndexOf(composeTemplateSelector) <
                  composeSelectorRow.Children.IndexOf(composeSignatureSelector),
                "new mail form places template before signature");
            Check(Require<Button>(mail, "DefaultSignatureButton") is not null,
                "signature settings have a primary-signature command");
            mail.Show();
            mail.UpdateLayout();

            var settingsPane = Require<Grid>(mail, "SettingsWorkspace");
            Check(settingsPane.Margin.Left == 16,
                "standalone settings retain a 16 px left inset");

            var greetingHost = new Grid();
            var greetingPlain = new TextBox { MinHeight = 120 };
            greetingHost.Children.Add(greetingPlain);
            var greetingEditor = RichComposeEditor.Attach(greetingPlain, mail);
            greetingEditor.LoadMarkdown("Обычное письмо");
            greetingEditor.EnsureOpeningGreeting();
            var greetingPlainText = greetingPlain.Text.Replace("\r\n", "\n");
            Check(greetingPlainText.StartsWith("Добрый день\n\nОбычное письмо",
                    StringComparison.Ordinal),
                "new letter has greeting followed by exactly two line breaks");
            greetingEditor.EnsureOpeningGreeting();
            Check(greetingPlain.Text.Split("Добрый день").Length == 2,
                "greeting is never inserted twice");
            greetingEditor.LoadReplyQuotedText(
                "\n\n----- Исходное письмо -----\n> Старое письмо");
            greetingEditor.EnsureOpeningGreeting();
            Check(greetingPlain.Text.StartsWith("Добрый день", StringComparison.Ordinal) &&
                  greetingPlain.Text.Contains("----- Исходное письмо"),
                "reply greeting precedes preserved message history");
            greetingEditor.ApplyMarkdownSignature("С уважением", new[] { "С уважением" });
            Check(greetingPlain.Text.IndexOf("Добрый день", StringComparison.Ordinal) <
                  greetingPlain.Text.IndexOf("С уважением", StringComparison.Ordinal) &&
                  greetingPlain.Text.IndexOf("С уважением", StringComparison.Ordinal) <
                  greetingPlain.Text.IndexOf("----- Исходное письмо", StringComparison.Ordinal),
                "reply greeting, signature and history keep their order");
            greetingEditor.ApplyTemplateMarkdown("Тело шаблона", Array.Empty<string>());
            greetingEditor.EnsureOpeningGreeting();
            Check(greetingPlain.Text.StartsWith("Добрый день", StringComparison.Ordinal) &&
                  greetingPlain.Text.Contains("Тело шаблона") &&
                  greetingPlain.Text.Split("Добрый день").Length == 2,
                "template leaves exactly one greeting in the reply body");
            greetingEditor.LoadMarkdown("Добрый день\n\nТекст из шаблона");
            greetingEditor.EnsureOpeningGreeting();
            Check(greetingPlain.Text.Split("Добрый день").Length == 2,
                "template beginning with greeting does not duplicate it");

            // Regression: a previous bold signature/heading must not become
            // the typing format of a fresh reply or a fresh new message.
            var formattedPlain = new TextBox { MinHeight = 120 };
            var formattedHost = new Grid();
            formattedHost.Children.Add(formattedPlain);
            var formatted = RichComposeEditor.Attach(formattedPlain, mail);
            formatted.LoadReplyQuotedText(
                "\n\n----- Исходное письмо -----\n> Было");
            formatted.EnsureOpeningGreeting();
            formatted.ApplyMarkdownSignature("**Жирная подпись**",
                new[] { "**Жирная подпись**" });
            formatted.PrepareInitialTypingFormat();
            Check(formatted.TextControl.CaretPosition.Paragraph is
                  { FontWeight: var replyWeight, FontStyle: var replyStyle } &&
                  replyWeight == FontWeights.Normal &&
                  replyStyle == FontStyles.Normal,
                "reply caret starts in a normal-weight paragraph");
            formatted.TextControl.Selection.Text = "обычный ответ";
            var replyTypedParagraph = formatted.TextControl.CaretPosition.Paragraph!;
            var replyTypedRange = new TextRange(
                replyTypedParagraph.ContentStart, replyTypedParagraph.ContentEnd);
            Check(Equals(replyTypedRange.GetPropertyValue(
                      TextElement.FontWeightProperty), FontWeights.Normal),
                "typing after a bold signature creates normal-weight reply text");
            Check(formatted.ToHtml().Contains("font-weight:bold") &&
                  formattedPlain.Text.Contains("Жирная подпись") &&
                  formattedPlain.Text.Contains("----- Исходное письмо"),
                "reset does not remove bold signature or quoted history");
            replyTypedRange.ApplyPropertyValue(
                TextElement.FontWeightProperty, FontWeights.Bold);
            formatted.PrepareInitialTypingFormat();
            Check(Equals(replyTypedRange.GetPropertyValue(
                      TextElement.FontWeightProperty), FontWeights.Bold),
                "explicit manual bold remains available after typing begins");

            formatted.LoadMarkdown("");
            formatted.ApplyMarkdownSignature("**Жирная подпись**",
                new[] { "**Жирная подпись**" });
            formatted.EnsureOpeningGreeting(reserveEditableLine: true);
            formatted.PrepareInitialTypingFormat();
            Check(formatted.TextControl.CaretPosition.Paragraph is
                  { FontWeight: var newWeight } &&
                  newWeight == FontWeights.Normal,
                "new mail starts with normal formatting before default signature");
            formatted.TextControl.Selection.Text = "обычное письмо";
            var newTypedParagraph = formatted.TextControl.CaretPosition.Paragraph!;
            var newTypedRange = new TextRange(
                newTypedParagraph.ContentStart, newTypedParagraph.ContentEnd);
            Check(Equals(newTypedRange.GetPropertyValue(
                      TextElement.FontWeightProperty), FontWeights.Normal),
                "new mail text is not bold after previous formatted compose session");

            // Excel direct sending uses single-row planning and a durable journal;
            // these regression checks use no real Excel or Mail.ru traffic.
            var testingDir = Path.Combine(Path.GetTempPath(),
                "MailRuMailingProfiles-" + Guid.NewGuid().ToString("N"));
            try
            {
                var mailingStore = new MailRuDesktop.App.Mailing.MailingProfileStore(testingDir);
                var localProfile = new MailRuDesktop.App.Mailing.MailingProfile
                {
                    Name = "Проверочная рассылка",
                    StartDate = DateTime.Today.AddDays(4),
                    StartTime = "09:00",
                    Senders =
                    [
                        new() { Login = "first@example.com", Quantity = 1 },
                        new() { Login = "second@example.com", Quantity = 1 }
                    ]
                };
                mailingStore.Save(new[] { localProfile });
                Check(mailingStore.Load().Single().JournalEnabled,
                    "journaling defaults to enabled for existing saved profiles");
                localProfile.JournalEnabled = false;
                mailingStore.Save([localProfile]);
                Check(!mailingStore.Load().Single().JournalEnabled,
                    "journal opt-out persists in the selected mailing profile");
                localProfile.JournalEnabled = true;
                mailingStore.Save([localProfile]);
                Check(mailingStore.Load().Single().Senders.Count == 2 &&
                      mailingStore.Load().Single().IntervalSeconds == 65,
                    "independent mailing profiles persist account quotas and 65-second interval");
                var counts = new MailRuDesktop.App.Mailing.MailingSenderCounters(testingDir);
                Check(counts.Get("test-account@example.com") == 0 &&
                      counts.RecordAccepted("test-account@example.com", "letter-1") &&
                      !counts.RecordAccepted("test-account@example.com", "letter-1") &&
                      counts.RecordAccepted("TEST-ACCOUNT@example.com", "letter-2") &&
                      counts.Get("test-account@example.com") == 2,
                    "confirmed sends count once by message identifier per account");
                var restoredCounts = new MailRuDesktop.App.Mailing.MailingSenderCounters(testingDir);
                Check(restoredCounts.Get("test-account@example.com") == 2,
                    "account send counters persist across application launches");
                restoredCounts.Reset();
                Check(new MailRuDesktop.App.Mailing.MailingSenderCounters(testingDir)
                      .Get("test-account@example.com") == 0,
                    "counter reset is persisted");
                var mailingRows = new[]
                {
                    new MailRuDesktop.App.Mailing.MailingExcelRow(2, true,
                        "one@example.com", "7707083893", "Москва", "", "", ""),
                    new MailRuDesktop.App.Mailing.MailingExcelRow(3, true,
                        "two@example.com", "7707083893", "Москва", "", "", ""),
                    new MailRuDesktop.App.Mailing.MailingExcelRow(4, true,
                        "three@example.com", "7707083893", "Москва", "", "", ""),
                    new MailRuDesktop.App.Mailing.MailingExcelRow(5, false,
                        "hidden@example.com", "7707083893", "Москва", "", "", ""),
                    new MailRuDesktop.App.Mailing.MailingExcelRow(6, true,
                        "old@example.com", "7707083893", "Москва",
                        "15.10.2026", "", ""),
                    new MailRuDesktop.App.Mailing.MailingExcelRow(7, true,
                        "excluded@example.com", "7707083893", "Москва", "", "", "")
                };
                var prepared = MailRuDesktop.App.Mailing.MailingPlanner.Prepare(
                    localProfile, mailingRows,
                    new Dictionary<string, string> { ["7707083893"] = "ООО Проверка" },
                    "Здравствуйте, {Название_организации} — {ИНН_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "excluded@example.com" },
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com", "second@example.com" });
                Check(prepared.ReadyCount == 2 &&
                      prepared.Rows.Single(x => x.RowNumber == 4).Status == "Пропуск" &&
                      prepared.Rows.Single(x => x.RowNumber == 5).Status == "Пропуск" &&
                      prepared.Rows.Single(x => x.RowNumber == 6).Status == "Пропуск" &&
                      prepared.Rows.Single(x => x.RowNumber == 7).Status == "Пропуск",
                    "mailing selection skips hidden, previously marked, excluded and over-quota rows");
                Check(prepared.Rows[0].Sender == "first@example.com" &&
                      prepared.Rows[1].Sender == "second@example.com" &&
                      prepared.Rows[0].ScheduledAt == prepared.Rows[1].ScheduledAt,
                    "sequential sender quotas share a common start but independent schedules");
                Check(prepared.Rows[0].Subject.Contains("ООО Проверка") &&
                      prepared.Rows[0].Body.Contains("7707083893"),
                    "organization and INN placeholders are rendered for preview");
                var twoLetters = MailRuDesktop.App.Mailing.MailingPlanner.Prepare(
                    new MailRuDesktop.App.Mailing.MailingProfile
                    {
                        StartDate = localProfile.StartDate, StartTime = "09:00",
                        Senders = [new() { Login = "first@example.com", Quantity = 3 }]
                    }, mailingRows.Take(3).ToArray(),
                    new Dictionary<string, string> { ["7707083893"] = "ООО Проверка" },
                    "Привет, {Название_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase),
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com" });
                Check(twoLetters.Rows[1].ScheduledAt!.Value -
                      twoLetters.Rows[0].ScheduledAt!.Value == TimeSpan.FromSeconds(65),
                    "per-account scheduling uses 65-second intervals");
                Check(MailRuDesktop.App.Mailing.MailingPlanner.IsValidInn("7707083893") &&
                      !MailRuDesktop.App.Mailing.MailingPlanner.IsValidInn("7707083894") &&
                      !MailRuDesktop.App.Mailing.MailingPlanner.IsValidEmail("test;other@example.com"),
                    "INN checksum and single recipient syntax are checked");
                var noName = MailRuDesktop.App.Mailing.MailingPlanner.Prepare(
                    localProfile, mailingRows.Take(1).ToArray(),
                    new Dictionary<string, string>(),
                    "Привет, {Название_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase),
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com", "second@example.com" });
                Check(noName.ReviewCount == 1 && noName.ReadyCount == 0,
                    "missing organization in both sources safely skips the row");
                var fallbackSource = mailingRows[0] with
                { EmployerName = "ООО Работодатель из Excel" };
                var fallbackPlan = MailRuDesktop.App.Mailing.MailingPlanner.Prepare(
                    localProfile, [fallbackSource],
                    new Dictionary<string, string>(),
                    "Привет, {Название_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase),
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com", "second@example.com" });
                Check(fallbackPlan.ReadyCount == 1 &&
                      fallbackPlan.Rows[0].Organization == "ООО Работодатель из Excel" &&
                      fallbackPlan.Rows[0].Body.Contains("ООО Работодатель из Excel"),
                    "Excel employer name is used when INN lookup has no organization");
                var streamSession = MailRuDesktop.App.Mailing.MailingPlanner.CreateSession(
                    localProfile, "Привет, {Название_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase),
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com", "second@example.com" },
                    collectRows: false);
                var streamed = streamSession.Accept(fallbackSource, null);
                Check(streamed.Status == "Ready" && streamSession.ReadyCount == 1 &&
                      streamSession.Result.Rows.Count == 0,
                    "direct mailing plans one row without accumulating a preview table");
                // Fake Excel COM object: start from its actual active cell,
                // skip AutoFilter-hidden rows, do not read >100000 rows at once.
                var fakeExcel = new FakeExcelApplication();
                var fakeSheet = new FakeExcelSheet { Name = "Компании" };
                string[] headings =
                [
                    "E-mail работодателя", "ИНН работодателя", "Регион",
                    "рассылка по СОУТ", "исх электронка", "Наименование работодателя"
                ];
                for (var col = 1; col <= headings.Length; col++)
                    fakeSheet.Put(1, col, headings[col - 1]);
                void AddExcelRow(int row, string email)
                {
                    fakeSheet.Put(row, 1, email);
                    fakeSheet.Put(row, 2, "7707083893");
                    fakeSheet.Put(row, 3, "Москва");
                }
                AddExcelRow(74199, "before@example.com");
                AddExcelRow(74210, "first-visible@example.com");
                AddExcelRow(74211, "filtered@example.com");
                fakeSheet.Hide(74211);
                AddExcelRow(74212, "second-visible@example.com");
                AddExcelRow(74213, "third-visible@example.com");
                AddExcelRow(145000, "after@example.com");
                var fakeBook = new FakeExcelWorkbook { Application = fakeExcel };
                fakeSheet.Parent = fakeBook;
                fakeBook.Worksheets = new FakeExcelWorksheets { Sheet = fakeSheet };
                fakeExcel.ActiveWorkbook = fakeBook;
                fakeExcel.ActiveSheet = fakeSheet;
                fakeExcel.ActiveCell = fakeSheet.Cells[74210, 4];
                var selectedWindow = new FakeExcelWindow
                {
                    Hwnd = 301, ActiveSheet = fakeSheet,
                    ActiveCell = fakeSheet.Cells[74210, 4]
                };
                fakeBook.Windows = new FakeExcelWindows(selectedWindow);
                fakeExcel.ActiveWindow = selectedWindow;
                var discovered = new MailRuDesktop.App.Mailing.OpenExcelBook
                {
                    Workbook = fakeBook, ApplicationHwnd = fakeExcel.Hwnd,
                    Name = fakeBook.Name, FullName = fakeBook.FullName,
                    Sheets = ["Компании"]
                };
                fakeExcel.Hwnd += 500;
                Check(MailRuDesktop.App.Mailing.ExcelInteropService
                         .OpenReader(discovered, "Компании").StartRow == 74210,
                    "Excel application HWND changes do not invalidate a selected workbook");
                var replacementBook = new FakeExcelWorkbook
                {
                    Application = fakeExcel, Worksheets = fakeBook.Worksheets,
                    Windows = fakeBook.Windows
                };
                var replacement = new MailRuDesktop.App.Mailing.OpenExcelBook
                {
                    Workbook = replacementBook, ApplicationHwnd = fakeExcel.Hwnd,
                    Name = replacementBook.Name, FullName = replacementBook.FullName,
                    Sheets = ["Компании"]
                };
                fakeBook.IsDisconnected = true;
                Check(ReferenceEquals(
                    MailRuDesktop.App.Mailing.ExcelInteropService
                        .ResolveSelectedWorkbook(discovered, () => [replacement]),
                    replacement), "disconnected workbook reconnects by exact path");
                var ambiguousRejected = false;
                try
                {
                    _ = MailRuDesktop.App.Mailing.ExcelInteropService
                        .ResolveSelectedWorkbook(discovered, () => [replacement, replacement]);
                }
                catch (InvalidOperationException) { ambiguousRejected = true; }
                Check(ambiguousRejected, "ambiguous same-path workbook rebind is rejected");
                fakeBook.IsDisconnected = false;
                var renamedBook = new FakeExcelWorkbook
                {
                    FullName = @"C:\fixture\renamed.xlsx", Application = fakeExcel,
                    Worksheets = fakeBook.Worksheets, Windows = fakeBook.Windows
                };
                var renamedSelection = new MailRuDesktop.App.Mailing.OpenExcelBook
                {
                    Workbook = renamedBook, ApplicationHwnd = fakeExcel.Hwnd,
                    Name = fakeBook.Name, FullName = fakeBook.FullName,
                    Sheets = ["Компании"]
                };
                var renamedRejected = false;
                try
                {
                    _ = MailRuDesktop.App.Mailing.ExcelInteropService
                        .ResolveSelectedWorkbook(renamedSelection, () =>
                            throw new Exception("Wrong-file silent rebinding"));
                }
                catch (InvalidOperationException) { renamedRejected = true; }
                Check(renamedRejected, "a Save As path change requires new workbook selection");
                var excelBatchReader = MailRuDesktop.App.Mailing.ExcelInteropService
                    .OpenReader(discovered, "Компании");
                Check(excelBatchReader.StartRow == 74210 && excelBatchReader.LastRow == 145000,
                    "Excel uses active cell row and data boundary even above 100000");
                excelBatchReader.ActivateRow(74210);
                Check(fakeBook.ActivationCount > 0 &&
                      fakeSheet.ActiveAddress == (74210, 4),
                    "Excel activates the current row in the originally selected column");
                excelBatchReader.ActivateRow(74212);
                Check(fakeSheet.ActiveAddress == (74212, 4),
                    "Excel follows the next visible data row with its active cell");
                Check(MailRuDesktop.App.Mailing.ExcelInteropService
                      .IsEligibleWorkbook("report.xlsx", false, true) &&
                      !MailRuDesktop.App.Mailing.ExcelInteropService
                      .IsEligibleWorkbook("addin.xlam", false, true) &&
                      !MailRuDesktop.App.Mailing.ExcelInteropService
                      .IsEligibleWorkbook("report.xlsx", true, true) &&
                      !MailRuDesktop.App.Mailing.ExcelInteropService
                      .IsEligibleWorkbook("hidden.xlsx", false, false),
                    "discovery excludes hidden books and Excel add-ins");
                var visibleBatch = excelBatchReader.ReadNext();
                Check(visibleBatch.Select(x => x.RowNumber)
                          .SequenceEqual(new[] { 74210, 74212, 74213 }),
                    "Excel reads in order, ignores filtered row and earlier data");
                // The selected workbook window is authoritative even if the
                // globally active Excel workbook belongs to a different file.
                var anotherBook = new FakeExcelWorkbook
                {
                    Name = "another.xlsx",
                    FullName = @"C:\fixture\another.xlsx",
                    Application = fakeExcel,
                    Worksheets = new FakeExcelWorksheets { Sheet = new FakeExcelSheet() },
                    Windows = new FakeExcelWindows(new FakeExcelWindow
                    {
                        Hwnd = 302, ActiveSheet = new FakeExcelSheet(),
                        ActiveCell = new FakeExcelSheet().Cells[3, 1]
                    })
                };
                fakeExcel.ActiveWorkbook = anotherBook;
                fakeExcel.ActiveWindow = anotherBook.Windows.Item[1];
                fakeExcel.ActiveSheet = anotherBook.Worksheets.Sheet;
                fakeExcel.ActiveCell = fakeSheet.Cells[74199, 1];
                Check(MailRuDesktop.App.Mailing.ExcelInteropService
                         .OpenReader(discovered, "Компании").StartRow == 74210,
                    "multiple open Excel books do not redirect the chosen workbook active row");
                // Multiple windows for the SAME book: use the one that is active.
                var secondWindow = new FakeExcelWindow
                {
                    Hwnd = 303, ActiveSheet = fakeSheet,
                    ActiveCell = fakeSheet.Cells[74212, 1]
                };
                fakeBook.Windows = new FakeExcelWindows(selectedWindow, secondWindow);
                fakeExcel.ActiveWindow = secondWindow;
                Check(MailRuDesktop.App.Mailing.ExcelInteropService
                         .OpenReader(discovered, "Компании").StartRow == 74212,
                    "multiple windows for one workbook use the active window cell");
                fakeBook.Windows = new FakeExcelWindows(selectedWindow);
                fakeExcel.ActiveWindow = selectedWindow;
                var incremental = MailRuDesktop.App.Mailing.MailingPlanner.CreateSession(
                    localProfile, "Body {ИНН_организации}",
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase),
                    new HashSet<string>(StringComparer.OrdinalIgnoreCase)
                    { "first@example.com", "second@example.com" });
                foreach (var visibleRow in visibleBatch)
                {
                    if (incremental.IsFull) break;
                    incremental.Accept(visibleRow, "ООО Тест");
                }
                Check(incremental.IsFull && incremental.Result.ReadyCount == 2 &&
                      incremental.Result.Rows[0].RowNumber == 74210 &&
                      incremental.Result.Rows[1].RowNumber == 74212 &&
                      excelBatchReader.HasMore,
                    "one-pass planning stops on quota without exhausting worksheet");
                selectedWindow.ActiveSheet = new FakeExcelSheet { Name = "Другой лист" };
                var mismatchRejected = false;
                try
                {
                    _ = MailRuDesktop.App.Mailing.ExcelInteropService
                        .OpenReader(discovered, "Компании");
                }
                catch (InvalidOperationException) { mismatchRejected = true; }
                Check(mismatchRejected,
                    "selected sheet cannot silently use another sheet's active row");
                selectedWindow.ActiveSheet = fakeSheet;

                var acceptedExcelJob = new MailRuDesktop.App.Mailing.MailingJobRecord
                {
                    RowNumber = 74210,
                    Email = "first-visible@example.com",
                    Inn = "7707083893",
                    Sender = "first@example.com",
                    ScheduledAt = DateTimeOffset.Now.AddDays(3),
                    MessageId = MailRuDesktop.Protocol.MailRuClient.CreateMessageId()
                };
                MailRuDesktop.App.Mailing.ExcelInteropService.WriteConfirmedResult(
                    discovered, "Компании", acceptedExcelJob);
                Check(Equals(fakeSheet.Get(74210, 4),
                          acceptedExcelJob.ScheduledAt.ToString("dd.MM.yyyy")) &&
                      Equals(fakeSheet.Get(74210, 5), "first@example.com") &&
                      fakeSheet.Get(74212, 4) is null,
                    "confirmed scheduling updates exactly two cells of its source row");
                // Reconciliation is idempotent, and changed source rows are never overwritten.
                MailRuDesktop.App.Mailing.ExcelInteropService.WriteConfirmedResult(
                    discovered, "Компании", acceptedExcelJob);
                fakeSheet.Put(74212, 1, "changed@example.com");
                var changedRowRejected = false;
                try
                {
                    MailRuDesktop.App.Mailing.ExcelInteropService.WriteConfirmedResult(
                        discovered, "Компании", new MailRuDesktop.App.Mailing.MailingJobRecord
                        {
                            RowNumber = 74212, Email = acceptedExcelJob.Email,
                            Inn = acceptedExcelJob.Inn, Sender = acceptedExcelJob.Sender,
                            ScheduledAt = acceptedExcelJob.ScheduledAt,
                            MessageId = acceptedExcelJob.MessageId
                        });
                }
                catch (InvalidOperationException) { changedRowRejected = true; }
                Check(changedRowRejected && fakeSheet.Get(74212, 4) is null,
                    "Excel writer refuses a row whose original recipient has changed");

                fakeExcel.ActiveSheet = fakeSheet;
                var sizingStore = new AppSettingsStore(Path.Combine(testingDir, "ui"));
                var layoutState = sizingStore.LoadUserInterfaceState();
                layoutState.ExcelMailingSenderGridHeight = 286;
                sizingStore.SaveUserInterfaceState(layoutState);
                Check(sizingStore.LoadUserInterfaceState()
                          .ExcelMailingSenderGridHeight == 286,
                    "sender table height is saved and restored in user settings");

                // Journals are created before network submission and survive a
                // process interruption without leaking the recipient or body.
                var journalDirectory = Path.Combine(testingDir, "runs");
                var journal = new MailRuDesktop.App.Mailing.MailingRunStore(journalDirectory);
                var journalProfile = new MailRuDesktop.App.Mailing.MailingProfile
                {
                    Name = "Контрольная рассылка",
                    Senders = [new() { Login = "sender@example.test", Quantity = 1 }]
                };
                var sampleTask = new MailRuDesktop.App.Mailing.MailingPreparedRow(
                    2, "private@example.test", "7707083893", "Москва",
                    "Тестовая организация", "sender@example.test",
                    DateTimeOffset.Now.AddDays(3), "Проверка", "Секретный текст",
                    "Ready", "");
                var noJournalDirectory = Path.Combine(testingDir, "no-journal");
                var noJournalStore = new MailRuDesktop.App.Mailing.MailingRunStore(
                    noJournalDirectory);
                var unrecorded = MailRuDesktop.App.Mailing.MailingRunStore
                    .CreateUnrecorded(journalProfile,
                        @"C:\mailing\source.xlsx", "Лист1", sampleTask);
                var unrecordedPostCount = 0;
                var unrecordedExcelCount = 0;
                new MailRuDesktop.App.Mailing.MailingSubmissionEngine(noJournalStore)
                    .ExecuteAsync(unrecorded, job =>
                    {
                        unrecordedPostCount++;
                        return Task.FromResult(new MailRuDesktop.Protocol.MailRuCommandResult(
                            true, "{\"status\":200}"));
                    }, job =>
                    {
                        unrecordedExcelCount++;
                        return Task.CompletedTask;
                    }, () => false, persist: false).GetAwaiter().GetResult();
                Check(!Directory.Exists(noJournalDirectory) &&
                      unrecordedPostCount == 1 && unrecordedExcelCount == 1 &&
                      unrecorded.Jobs[0].Status ==
                          MailRuDesktop.App.Mailing.MailingJobStatus.CompletedRecord,
                    "journal opt-out schedules and writes Excel without any protected record");
                var unrecordedFailure = MailRuDesktop.App.Mailing.MailingRunStore
                    .CreateUnrecorded(journalProfile,
                        @"C:\mailing\source.xlsx", "Лист1", sampleTask);
                new MailRuDesktop.App.Mailing.MailingSubmissionEngine(noJournalStore)
                    .ExecuteAsync(unrecordedFailure, _ => Task.FromResult(
                        new MailRuDesktop.Protocol.MailRuCommandResult(false,
                            "{\"status\":500}")),
                        _ => throw new Exception("must not write Excel"),
                        () => false, persist: false).GetAwaiter().GetResult();
                Check(!Directory.Exists(noJournalDirectory) &&
                      unrecordedFailure.Jobs[0].Status ==
                          MailRuDesktop.App.Mailing.MailingJobStatus.Indeterminate,
                    "uncertain no-journal POST stops without creating a service journal");
                var journalRun = journal.Create(journalProfile, @"C:\mailing\source.xlsx",
                    "Лист1", [sampleTask]);
                Check(journalRun.Jobs.Count == 1 &&
                      journalRun.Jobs[0].MessageId.Length == 32 &&
                      !File.ReadAllText(Path.Combine(journalDirectory,
                          journalRun.Id + ".run")).Contains("private@example.test"),
                    "mailing journal encrypts full per-letter state with Windows DPAPI");
                journalRun.Jobs[0].Status =
                    MailRuDesktop.App.Mailing.MailingJobStatus.Submitting;
                journal.Save(journalRun);
                var interrupted = journal.Load(journalRun.Id);
                Check(interrupted.Jobs[0].Status ==
                      MailRuDesktop.App.Mailing.MailingJobStatus.Indeterminate,
                    "an interrupted request is never automatically treated as unsent");
                var editRecord = journal.Load(journalRun.Id, recoverSubmitting: false);
                editRecord.Jobs[0].Organization = "Исправленная организация";
                editRecord.Jobs[0].Body = "Исправленный текст";
                journal.Save(editRecord);
                var reloadedEdit = journal.Load(journalRun.Id, recoverSubmitting: false);
                Check(reloadedEdit.Jobs[0].Organization == "Исправленная организация" &&
                      reloadedEdit.Jobs[0].Body == "Исправленный текст",
                    "manual edits of an existing journal round-trip through DPAPI");
                var blockedRepeat = false;
                try
                {
                    journal.Create(journalProfile, @"C:\mailing\source.xlsx",
                        "Лист1", [sampleTask]);
                }
                catch (InvalidOperationException) { blockedRepeat = true; }
                Check(blockedRepeat,
                    "indeterminate or previously accepted Excel row cannot be resubmitted");

                var nextTask = sampleTask with
                {
                    RowNumber = 3,
                    Email = "private2@example.test"
                };
                var nextRun = journal.Create(journalProfile, @"C:\mailing\source.xlsx",
                    "Лист1", [nextTask]);
                var engine = new MailRuDesktop.App.Mailing.MailingSubmissionEngine(journal);
                var sent = 0;
                var written = 0;
                var fakeAcceptance = new Func<
                    MailRuDesktop.App.Mailing.MailingJobRecord,
                    Task<MailRuDesktop.Protocol.MailRuCommandResult>>(_ =>
                {
                    sent++;
                    return Task.FromResult(new MailRuDesktop.Protocol.MailRuCommandResult(
                        true, "{\"status\":200}"));
                });
                engine.ExecuteAsync(nextRun, fakeAcceptance, _ =>
                    Task.FromException(new IOException("Excel closed")), () => false)
                    .GetAwaiter().GetResult();
                Check(sent == 1 && nextRun.Jobs[0].Status ==
                      MailRuDesktop.App.Mailing.MailingJobStatus.ExcelWritePending &&
                      !nextRun.Jobs[0].ExcelWritten,
                    "accepted server action survives failed Excel write");
                engine.ExecuteAsync(journal.Load(nextRun.Id), fakeAcceptance, _ =>
                {
                    written++;
                    return Task.CompletedTask;
                }, () => false).GetAwaiter().GetResult();
                var fullyRecovered = journal.Load(nextRun.Id);
                Check(sent == 1 && written == 1 &&
                      fullyRecovered.Jobs[0].Status ==
                      MailRuDesktop.App.Mailing.MailingJobStatus.CompletedRecord,
                    "Excel recovery writes once and never repeats the accepted POST");
                var extraRow = nextTask with
                { RowNumber = 10, Email = "streamed@example.test" };
                var appended = journal.Append(nextRun, extraRow);
                Check(nextRun.Jobs.Count == 2 &&
                      appended.Status == MailRuDesktop.App.Mailing.MailingJobStatus.Prepared &&
                      journal.Load(nextRun.Id).Jobs.Count == 2 &&
                      journal.ForWorkbook(@"C:\mailing\source.xlsx", "Лист1")
                          .Any(x => x.Id == nextRun.Id) &&
                      MailRuDesktop.App.Mailing.MailingRunStore.RowKey(
                          appended.RowNumber, appended.Email, appended.Inn) ==
                      MailRuDesktop.App.Mailing.MailingRunStore.RowKey(
                          10, "STREAMED@example.test", extraRow.Inn),
                    "new streaming job is durably appended before any POST and reserved by row");


                var refused = journal.Create(journalProfile, @"C:\mailing\source.xlsx",
                    "Лист1", [nextTask with { RowNumber = 4, Email = "refused@example.test" }]);
                engine.ExecuteAsync(refused, _ => Task.FromResult(
                    new MailRuDesktop.Protocol.MailRuCommandResult(false,
                        "{\"status\":400,\"body\":\"invalid\"}")),
                    _ => throw new Exception("Excel must not be written"),
                    () => false).GetAwaiter().GetResult();
                Check(refused.Jobs[0].Status ==
                      MailRuDesktop.App.Mailing.MailingJobStatus.Rejected,
                    "an explicit server refusal is never marked as accepted");

                var ambiguous = journal.Create(journalProfile, @"C:\mailing\source.xlsx",
                    "Лист1", [nextTask with { RowNumber = 5, Email = "ambiguous@example.test" }]);
                engine.ExecuteAsync(ambiguous, _ => Task.FromResult(
                    new MailRuDesktop.Protocol.MailRuCommandResult(false,
                        "{\"status\":500}")),
                    _ => throw new Exception("Excel must not be written"),
                    () => false).GetAwaiter().GetResult();
                Check(ambiguous.Jobs[0].Status ==
                      MailRuDesktop.App.Mailing.MailingJobStatus.Indeterminate,
                    "uncertain server failure is held for review, never replayed");




            }
            finally
            {
                if (Directory.Exists(testingDir))
                    Directory.Delete(testingDir, recursive: true);
            }
            var mailingAction = Require<Button>(mail, "OpenExcelMailingButton");
            mailingAction.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, mailingAction));
            var mailingWindow = Application.Current.Windows
                .OfType<MailRuDesktop.App.Mailing.ExcelMailingWindow>()
                .FirstOrDefault();
            Check(mailingWindow is { IsVisible: true } &&
                  mailingWindow.Owner == mail && mailingWindow.Title.Contains("Рассылки Excel"),
                "Excel mailing opens a distinct owned preparation window");
            var sizeHandle = Require<Thumb>(mailingWindow!, "SenderGridResizeThumb");
            var sizeTable = Require<DataGrid>(mailingWindow!, "SenderGrid");
            var initialHeight = sizeTable.Height;
            mailingWindow!.ResizeSenderGrid(55);
            Check(sizeHandle.Cursor == System.Windows.Input.Cursors.SizeNS &&
                  sizeTable.Height > initialHeight,
                "drag handle changes account-table height and shifts remaining layout");
            mailingWindow.SaveSenderGridHeight();
            Check(Require<CheckBox>(mailingWindow, "JournalEnabledCheck").IsChecked == true &&
                  Require<Button>(mailingWindow, "OpenJournalButton") is not null &&
                  Require<Button>(mailingWindow, "StopButton").IsEnabled == false &&
                  Require<Button>(mailingWindow, "ResetSenderCountersButton") is not null &&
                  Require<TextBox>(mailingWindow, "ExecutionLogTextBox").IsReadOnly &&
                  mailingWindow.FindName("FindBooksButton") is null,
                "mailing form has stop, reset, multi-line log and automatic workbook discovery");
            Check(Require<DatePicker>(mailingWindow, "StartDatePicker").SelectedDate
                      == DateTime.Today.AddDays(1),
                "Excel mailing start date defaults to tomorrow");
            // Instantiate the actual editor, modify one displayed message,
            // save it through the UI and verify protected journal round-trip.
            var editorPath = Path.Combine(Path.GetTempPath(),
                "MailRu-Journal-UI-" + Guid.NewGuid().ToString("N"));
            try
            {
                var editorStore = new MailRuDesktop.App.Mailing.MailingRunStore(editorPath);
                var editorProfile = new MailRuDesktop.App.Mailing.MailingProfile
                { Name = "Редактор", Senders = [new() { Login = "sender@example.test" }] };
                var editorTask = new MailRuDesktop.App.Mailing.MailingPreparedRow(
                    2, "recipient@example.test", "7707083893", "Москва",
                    "Тест", "sender@example.test", DateTimeOffset.Now.AddDays(4),
                    "Старая тема", "Старое письмо", "Ready", "");
                var editorRun = editorStore.Create(editorProfile,
                    @"C:\fixture\journal.xlsx", "Лист1", [editorTask]);
                var journalWindow = new MailRuDesktop.App.Mailing.MailingJournalWindow(
                    mailingWindow, editorStore);
                var journalGrid = Require<DataGrid>(journalWindow, "JobsGrid");
                Check(journalGrid.Items.Count == 1 &&
                      Require<ComboBox>(journalWindow, "RunCombo").SelectedItem is not null,
                    "journal editor opens decrypted runs and existing jobs");
                var subjectEditor = Require<TextBox>(journalWindow, "SubjectEditor");
                subjectEditor.Text = "Отредактированная тема";
                Require<Button>(journalWindow, "SaveButton").RaiseEvent(
                    new RoutedEventArgs(ButtonBase.ClickEvent,
                        Require<Button>(journalWindow, "SaveButton")));
                Check(editorStore.Load(editorRun.Id, recoverSubmitting: false)
                          .Jobs[0].Subject == "Отредактированная тема",
                    "journal editor saves changed letter content in DPAPI format");
                journalWindow.Close();
            }
            finally
            {
                if (Directory.Exists(editorPath))
                    Directory.Delete(editorPath, recursive: true);
            }

            Check(Require<Button>(mailingWindow, "SendButton").Content?.ToString() ==
                      "Отправить" &&
                  mailingWindow.FindName("PreviewGrid") is null &&
                  mailingWindow.FindName("StartMailingButton") is null &&
                  mailingWindow.FindName("RunHistoryCombo") is null &&
                  mailingWindow.FindName("MessagePreviewDocument") is null,
                "Excel mailing exposes one direct-send action and no preview, correction or history panel");
            mailingWindow!.Close();

            // Stage 4.1: metadata is one reusable WPF view, WebView2 is not moved.
            var extractedHeader = Require<MailReaderHeader>(mail, "ReaderHeader");
            Check(extractedHeader.Parent is Grid && Grid.GetRow(extractedHeader) == 2,
                "reader header occupies the original grid row");
            Check(extractedHeader.SubjectText.Text == "Выберите письмо" &&
                  extractedHeader.SenderAddressText.IsReadOnly &&
                  extractedHeader.RecipientAddressText.IsReadOnly,
                "reader header retains initial subject and read-only addresses");
            Check(FindDescendant<TextBox>(extractedHeader,
                      t => t.Name == "SelectedSenderText") is not null &&
                  FindDescendant<TextBox>(extractedHeader,
                      t => t.Name == "SelectedToText") is not null,
                "reader header preserves address controls and their positions");
            Check(FindDescendant<Microsoft.Web.WebView2.Wpf.WebView2>(mail, _ => true)
                      is not null &&
                  FindDescendant<Microsoft.Web.WebView2.Wpf.WebView2>(
                      extractedHeader, _ => true) is null,
                "single permanent WebView2 remains outside extracted header");

            // Detached reader: download-all is available only with multiple
            // real attachments, exactly like the main preview.
            using (var downloadTestMailClient = new MailRuDesktop.Protocol.MailRuClient())
            {
                var summary = new MailRuDesktop.Protocol.MailRuMessageSummary(
                    "zip-test", "Файлы", "", "Sender", "sender@example.test",
                    null, null, 0, false, false, true);
                var detachedReader = new MessageWindow(downloadTestMailClient,
                    summary, null, 0,
                    Array.Empty<MailRuDesktop.Protocol.MailRuFolderSummary>());
                var resendButton = Require<Button>(detachedReader,
                    "SendScheduledNowButton");
                Check((string?)resendButton.Content == "Отправить повторно" &&
                      resendButton.IsEnabled &&
                      typeof(MessageWindow).GetProperty("ResendRequested") is not null,
                    "outbox reader uses an enabled resend action and reports compose request");
                var zipButton = Require<Button>(detachedReader,
                    "DownloadAllAttachmentsButton");
                Check(zipButton.Visibility == Visibility.Collapsed,
                    "detached reader initially hides download-all ZIP button");
                var updateFull = typeof(MessageWindow).GetMethod(
                    "ApplyFullMessageAsync", BindingFlags.Instance | BindingFlags.NonPublic);
                Check(updateFull is not null,
                    "detached reader accepts full attachment metadata");
                var oneFile = new MailRuDesktop.Protocol.MailRuIncomingAttachment(
                    "file-1", "report.pdf", "application/pdf", "", 123);
                var anotherFile = new MailRuDesktop.Protocol.MailRuIncomingAttachment(
                    "file-2", "report.pdf", "application/pdf", "", 456);
                MailRuDesktop.Protocol.MailRuFullMessage WithAttachments(
                    params MailRuDesktop.Protocol.MailRuIncomingAttachment[] attachments) =>
                    new("zip-test", "Файлы", "Sender", "sender@example.test",
                        Array.Empty<string>(), Array.Empty<string>(), null,
                        "", "", attachments, "");
                void ShowMetadata(params MailRuDesktop.Protocol.MailRuIncomingAttachment[] attachments) =>
                    ((Task)updateFull!.Invoke(detachedReader,
                        new object[] { WithAttachments(attachments) })!).GetAwaiter().GetResult();
                ShowMetadata(oneFile);
                Check(zipButton.Visibility == Visibility.Collapsed,
                    "single attachment keeps ZIP batch action hidden");
                ShowMetadata(oneFile, anotherFile);
                Check(zipButton.Visibility == Visibility.Visible &&
                      zipButton.IsEnabled &&
                      Require<ItemsControl>(detachedReader, "AttachmentsItemsControl")
                          .Items.Count == 2,
                    "two attachments show an enabled ZIP action without hiding existing files");
                var archiveName = typeof(MessageWindow).GetMethod(
                    "UniqueZipEntryName", BindingFlags.Static | BindingFlags.NonPublic);
                Check(archiveName is not null,
                    "ZIP archive has a collision-safe entry naming method");
                var knownNames = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
                var first = (string)archiveName!.Invoke(null,
                    new object[] { "report.pdf", knownNames })!;
                var second = (string)archiveName.Invoke(null,
                    new object[] { "REPORT.pdf", knownNames })!;
                Check(first == "report.pdf" && second == "REPORT (2).pdf",
                    "ZIP preserves attachment names and distinguishes duplicates");
                ShowMetadata();
                Check(zipButton.Visibility == Visibility.Collapsed,
                    "no attachments hide ZIP batch action");
                detachedReader.Close();
            }

            // Stage 4.2: real WPF folder selections while a load is in progress.
            // Simulated mailbox state; no network requests or secrets.
            var folderList = Require<ListBox>(mail, "FolderListBox");
            var folders = (System.Collections.ObjectModel.ObservableCollection<
                MailRuDesktop.Protocol.MailRuFolderSummary>)folderList.ItemsSource;
            var loading = typeof(MainWindow).GetField("_loadingFolder",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var suppressFolderEvents = typeof(MainWindow).GetField("_updatingFolderSelection",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var deferredState = typeof(MainWindow).GetField("_pendingFolderSelection",
                BindingFlags.Instance | BindingFlags.NonPublic)!.GetValue(mail)!;
            var takePending = deferredState.GetType().GetMethod("Take",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var generation = (long)typeof(MainWindow).GetField("_accountSwitchGeneration",
                BindingFlags.Instance | BindingFlags.NonPublic)!.GetValue(mail)!;
            var activeLogin = (string?)typeof(MainWindow).GetField("_activeLogin",
                BindingFlags.Instance | BindingFlags.NonPublic)!.GetValue(mail);
            var originalFolder = (int)typeof(MainWindow).GetField("_currentFolderId",
                BindingFlags.Instance | BindingFlags.NonPublic)!.GetValue(mail)!;
            var originallyLoading = (bool)loading.GetValue(mail)!;
            loading.SetValue(mail, true);
            try
            {
                var first = new MailRuDesktop.Protocol.MailRuFolderSummary(
                    701, "custom", "Первая", 0, 0, false);
                var second = first with { Id = 702, Name = "Вторая" };
                var thirdSyntheticFolder = first with { Id = 703, Name = "Третья" };
                folders.Add(first);
                folders.Add(second);
                folders.Add(thirdSyntheticFolder);
                folderList.SelectedItem = first;
                folderList.SelectedItem = second;
                folderList.SelectedItem = thirdSyntheticFolder;
                var requested = takePending.Invoke(deferredState, new object?[]
                {
                    generation, activeLogin
                });
                Check(requested is int selected && selected == 703,
                    "WPF selection change retains the final folder while loading");
                Check((int)typeof(MainWindow).GetField("_currentFolderId",
                          BindingFlags.Instance | BindingFlags.NonPublic)!.GetValue(mail)! ==
                      originalFolder,
                    "pending selection does not mutate the server-confirmed folder");
            }
            finally
            {
                suppressFolderEvents.SetValue(mail, true);
                folders.Clear();
                suppressFolderEvents.SetValue(mail, false);
                loading.SetValue(mail, originallyLoading);
            }

            // Issue #77: all three entrypoints use one controller.
            // No click is fired here: outgoing mail requires explicit consent.
            Check(Require<Button>(mail, "PreviewSendNowButton") is not null,
                "message preview contains immediate scheduling action");
            Check(Require<Button>(mail, "BulkSendNowButton") is not null,
                "bulk toolbar contains immediate scheduling action");
            Check(typeof(MainWindow).GetMethod("SendScheduledCopiesAsync",
                  BindingFlags.Instance | BindingFlags.NonPublic) is not null,
                "single shared scheduled message executor exists");
            Check(typeof(MainWindow).GetMethod("PreviewSendNowButton_Click",
                  BindingFlags.Instance | BindingFlags.NonPublic) is not null &&
                  typeof(MainWindow).GetMethod("BulkSendNowButton_Click",
                  BindingFlags.Instance | BindingFlags.NonPublic) is not null,
                "immediate dispatch handlers are available for both buttons");

            var action = Require<Button>(mail, "MainComposeButton");
            Check(action.IsVisible, "New Mail action is visible above the message list");
            Check(Require<Grid>(mail, "SettingsWorkspace") is { }, "settings workspace loads");
            var reader = Require<Microsoft.Web.WebView2.Wpf.WebView2>(
                mail, "MessageWebView");
            Check(reader.Visibility == Visibility.Visible,
                "single WebView2 stays visible; loading occurs inside browser");
            Check(mail.FindName("ReaderLoadingOverlay") is null,
                "no WPF overlay can create WebView2 airspace flashes");

            // Account context command applies to the clicked account, not
            // the currently active mailbox. The login is fixed in its dialog.
            var accountMenuMethod = typeof(MainWindow).GetMethod(
                "CreateAccountContextMenu", BindingFlags.Instance | BindingFlags.NonPublic);
            Check(accountMenuMethod is not null, "account context-menu factory exists");
            var accountMenu = accountMenuMethod!.Invoke(mail, new object[]
            {
                new AccountRailItem("other@example.invalid")
            }) as ContextMenu;
            Check(accountMenu is not null && accountMenu.Items.Count == 3 &&
                  (string)((MenuItem)accountMenu.Items[0]).Header == "Копировать адрес" &&
                  (string)((MenuItem)accountMenu.Items[1]).Header == "Повторная авторизация" &&
                  (string)((MenuItem)accountMenu.Items[2]).Header == "Удалить",
                "account context menu copies address and keeps existing commands");

            var credentialTestRoot = Path.Combine(Path.GetTempPath(),
                "MailRuDesktop-DpapiSmoke-" + Guid.NewGuid().ToString("N"));
            try
            {
                var credentialStore = new AuthorizationStore(credentialTestRoot);
                credentialStore.Save("password-test@example.invalid", "access-1",
                    "refresh-1", null, null, null, null, "test-first-pass");
                Check(credentialStore.TryGetPassword("password-test@example.invalid",
                          out var storedPassword) &&
                      storedPassword == "test-first-pass",
                    "verified password is restored using per-user DPAPI");
                Check(!File.ReadAllText(Path.Combine(credentialTestRoot, "auth.json"))
                          .Contains("test-first-pass", StringComparison.Ordinal),
                    "saved authorization does not expose a plaintext password");
                Check(credentialStore.UpdateTokens("password-test@example.invalid",
                          "access-2", "refresh-2", "test-new-pass") &&
                      credentialStore.TryGetPassword("password-test@example.invalid",
                          out var updatedPassword) &&
                      updatedPassword == "test-new-pass",
                    "password is atomically replaced with verified refreshed tokens");
                Check(credentialStore.UpdateTokens("password-test@example.invalid",
                          "access-3", "refresh-3") &&
                      credentialStore.TryGetPassword("password-test@example.invalid",
                          out var retainedPassword) &&
                      retainedPassword == "test-new-pass",
                    "background token refresh retains the current stored password");
            }
            finally
            {
                if (Directory.Exists(credentialTestRoot))
                    Directory.Delete(credentialTestRoot, recursive: true);
            }
            Check(MailRuVerificationWindow.IsMailboxLanding(
                      new Uri("https://e.mail.ru/inbox/12345")) &&
                  MailRuVerificationWindow.IsMailboxLanding(
                      new Uri("https://e.mail.ru/sent")) &&
                  !MailRuVerificationWindow.IsMailboxLanding(
                      new Uri("https://e.mail.ru.evil.example/inbox/12345")) &&
                  !MailRuVerificationWindow.IsMailboxLanding(
                      new Uri("http://e.mail.ru/inbox/12345")) &&
                  !MailRuVerificationWindow.IsMailboxLanding(
                      new Uri("https://e.mail.ru/inbox-mock")),
                "mailbox navigation marks browser stage done only on trusted pages");

            var knownPasswordDialog = new AddAccountWindow(
                "other@example.invalid", knownPassword: "test-saved-password");
            Check(knownPasswordDialog.Password == "test-saved-password",
                "saved Windows DPAPI password is prefilled for requested reauthorization");
            knownPasswordDialog.Close();
            var recoveryPasswordDialog = new AddAccountWindow(
                "other@example.invalid", recoveryPassword: true);
            Check(recoveryPasswordDialog.Title.Contains("Действующий пароль",
                    StringComparison.Ordinal) &&
                  (string)Require<Button>(recoveryPasswordDialog, "ConfirmButton").Content
                     == "Продолжить",
                "after browser account recovery the user can supply the actual new password");
            recoveryPasswordDialog.Close();

            var reauthDialog = new AddAccountWindow("other@example.invalid");
            Check(reauthDialog.Title == "Повторная авторизация Mail.ru" &&
                  Require<TextBox>(reauthDialog, "LoginTextBox").Text == "other@example.invalid" &&
                  Require<TextBox>(reauthDialog, "LoginTextBox").IsReadOnly &&
                  (string)Require<Button>(reauthDialog, "ConfirmButton").Content == "Авторизовать",
                "reauth dialog fixes existing mailbox and requests only its password");
            reauthDialog.Close();

            var addDialog = new AddAccountWindow();
            Check(!Require<TextBox>(addDialog, "LoginTextBox").IsReadOnly &&
                  (string)Require<Button>(addDialog, "ConfirmButton").Content == "Добавить",
                "normal add-account flow remains unchanged");
            addDialog.Close();

            var modernList = Require<ListBox>(mail, "MessagesGrid");

            // Stage 2: diagnostic output must never become the source of
            // folder thread counts. Re-rendering after an unrelated command
            // cannot replace counts; publishing a new folder snapshot can.
            var diagnosticsBox = Require<TextBox>(mail, "ResponseTextBox");
            var conversationField = typeof(MainWindow).GetField("_conversationIndex",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var modelBuilder = typeof(MainWindow).GetMethod("BuildFolderThreadState",
                BindingFlags.Static | BindingFlags.NonPublic)!;
            var modelPublisher = typeof(MainWindow).GetMethod("PublishFolderThreadState",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var rerender = typeof(MainWindow).GetMethod("RefreshModernMailListView",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var registryType = typeof(MainWindow).Assembly.GetType(
                "MailRuDesktop.App.MailThreadCountRegistry", throwOnError: true)!;
            var registryGet = registryType.GetMethod("Get",
                BindingFlags.Static | BindingFlags.Public)!;
            var registryReplace = registryType.GetMethod("Replace",
                BindingFlags.Static | BindingFlags.Public)!;
            var registryCountsField = registryType.GetField("_counts",
                BindingFlags.Static | BindingFlags.NonPublic)!;
            var originalThreadCounts = registryCountsField.GetValue(null);
            var originalConversations = conversationField.GetValue(mail);
            var originalDiagnosticText = diagnosticsBox.Text;
            try
            {
                object Snapshot(string json) => modelBuilder.Invoke(null, new object[] { json })!;
                int Count(string id) => (int)registryGet.Invoke(null, new object[] { id })!;

                modelPublisher.Invoke(mail, new[] {
                    Snapshot("""{"threads":[{"id":"stage2-old-message","length":5}]}""") });
                diagnosticsBox.Text =
                    """{"threads":[{"id":"stage2-old-message","length":99}]}""";
                rerender.Invoke(mail, null);
                Check(Count("stage2-old-message") == 5,
                    "unrelated diagnostic JSON cannot modify folder conversation counts");

                modelPublisher.Invoke(mail, new[] {
                    Snapshot("""{"threads":[{"id":"stage2-new-message","length":2}]}""") });
                Check(Count("stage2-old-message") == 1 &&
                      Count("stage2-new-message") == 2,
                    "publishing next folder/account snapshot replaces the old index atomically");

                diagnosticsBox.Text = "another operation completed";
                rerender.Invoke(mail, null);
                Check(Count("stage2-new-message") == 2,
                    "non-JSON diagnostic text does not erase published thread counts");
            }
            finally
            {
                conversationField.SetValue(mail, originalConversations);
                registryReplace.Invoke(null, new[] { originalThreadCounts });
                diagnosticsBox.Text = originalDiagnosticText;
            }

            // Regression: the server finds matches inside FULL message bodies.
            // Filtering its result again by only Subject/Snippet must not hide them.
            var searchBox = Require<TextBox>(mail, "FilterQueryTextBox");
            var existingQuery = searchBox.Text;
            var existingStatus = Require<SelectableText>(mail, "FilterStatusText").Text;
            var sourceField = typeof(MainWindow).GetField("_currentMessages",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var searchModeField = typeof(MainWindow).GetField("_serverSearchMode",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var applySearchFilters = typeof(MainWindow).GetMethod("ApplyFilters",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var originalSource = sourceField.GetValue(mail);
            var originalServerMode = searchModeField.GetValue(mail);
            var serverResultA = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "body-hit-1", "Счёт", "Письмо без слов из полного текста",
                "Компания", "one@example.org", 1700000000, 1, 0,
                false, false, false);
            var serverResultB = serverResultA with
            {
                Id = "body-hit-2",
                Subject = "Документы",
                Snippet = "Ещё один краткий фрагмент"
            };
            try
            {
                sourceField.SetValue(mail, new List<MailRuDesktop.Protocol.MailRuMessageSummary>
                    { serverResultA, serverResultB });
                searchModeField.SetValue(mail, true);
                searchBox.Text = "совпадение-только-в-полном-тексте";
                applySearchFilters.Invoke(mail, null);
                Check(modernList.Items.Count == 2,
                    "server search retains both full-body matches absent from subjects/snippets");
                searchBox.Clear();
                Check(modernList.Items.Count == 2,
                    "clearing search text does not trigger server reload or hide fetched results");
                searchBox.Text = "новый запрос без отправки";
                Check(modernList.Items.Count == 2,
                    "typing a new query does not filter previous server results before clicking search");

                searchModeField.SetValue(mail, false);
                applySearchFilters.Invoke(mail, null);
                Check(modernList.Items.Count == 0,
                    "ordinary folder mode still filters the loaded list locally");
            }
            finally
            {
                sourceField.SetValue(mail, originalSource);
                searchModeField.SetValue(mail, originalServerMode);
                searchBox.Text = existingQuery;
                applySearchFilters.Invoke(mail, null);
                Require<SelectableText>(mail, "FilterStatusText").Text = existingStatus;
            }

            // A server count above 200 triggers another offset-based page.
            // The offset counts delivered records even if an ID repeats.
            var collector = new MailServerSearchPages();
            var firstPage = new MailRuDesktop.Protocol.MailRuSearchResult(
                5, new[] { serverResultA, serverResultB }, "{}", "test-server")
                { HasReportedTotal = true };
            collector.AddPage(firstPage);
            Check(collector.Messages.Count == 2 && collector.NextOffset == 2 &&
                  collector.ReportedTotal == 5 && collector.HasMore,
                "server paging continues when the reported total exceeds the first page");
            var third = serverResultA with { Id = "body-hit-3" };
            collector.AddPage(new MailRuDesktop.Protocol.MailRuSearchResult(
                5, new[] { serverResultB, third }, "{}", "test-server")
                { HasReportedTotal = true });
            Check(collector.Messages.Count == 3 && collector.NextOffset == 4 &&
                  collector.HasMore,
                "overlapping pages deduplicate IDs but advance the server offset");
            var fourth = serverResultA with { Id = "body-hit-4" };
            var fifth = serverResultA with { Id = "body-hit-5" };
            collector.AddPage(new MailRuDesktop.Protocol.MailRuSearchResult(
                5, new[] { fourth, fifth }, "{}", "test-server")
                { HasReportedTotal = true });
            Check(collector.Messages.Count == 5 && !collector.HasMore &&
                  collector.GetProgressText().Contains("загружено 5",
                      StringComparison.Ordinal),
                "all server results remain visible once the reported total is loaded");
            var loopingPages = new MailServerSearchPages();
            loopingPages.AddPage(new MailRuDesktop.Protocol.MailRuSearchResult(
                400, new[] { serverResultA }, "{}", "test-server")
                { HasReportedTotal = true });
            loopingPages.AddPage(new MailRuDesktop.Protocol.MailRuSearchResult(
                400, new[] { serverResultA }, "{}", "test-server")
                { HasReportedTotal = true });
            Check(!loopingPages.HasMore &&
                  loopingPages.GetProgressText().Contains("не полностью",
                      StringComparison.Ordinal),
                "repeated server pages stop safely and report incomplete results");
            var unknownTotal = new MailServerSearchPages();
            unknownTotal.AddPage(new MailRuDesktop.Protocol.MailRuSearchResult(
                1, new[] { serverResultA }, "{}", "test-server"));
            Check(!unknownTotal.HasMore && unknownTotal.ReportedTotal is null,
                "short page with no reported server count does not invent a total");

            // Stage 0 regression: the ACTIVE modern mail-row template,
            // not the obsolete XAML card, must expose a real pin control.
            var mailWorkspace = Require<Grid>(mail, "MailWorkspace");
            // ConfigureResizableMailColumns is invoked from OnContentRendered,
            // which may not have fired yet in this synchronous smoke test.
            // Invoke the same idempotent initialization explicitly.
            var initializeCentralColumns = typeof(MainWindow).GetMethod(
                "ConfigureResizableMailColumns",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(initializeCentralColumns is not null,
                "central splitter initialization remains callable");
            initializeCentralColumns!.Invoke(mail, null);
            // Experimental control: restore the exact pre-regression v0.3.60
            // column limits and list scrolling; leave mail/compose fixes intact.
            Check(!mailWorkspace.ClipToBounds &&
                  mailWorkspace.ColumnDefinitions[0].MinWidth == 320 &&
                  mailWorkspace.ColumnDefinitions[2].MinWidth == 360,
                "central mail columns use the v0.3.60 splitter minimums 320/360");
            var listPane = mailWorkspace.Children.OfType<Grid>()
                .Single(grid => Grid.GetColumn(grid) == 0);
            Check(!listPane.ClipToBounds &&
                  listPane.ReadLocalValue(FrameworkElement.MinWidthProperty) ==
                      DependencyProperty.UnsetValue,
                "list pane has the original v0.3.60 clipping and width behavior");
            Check(modernList.ReadLocalValue(
                      ScrollViewer.HorizontalScrollBarVisibilityProperty) ==
                      DependencyProperty.UnsetValue &&
                  modernList.ReadLocalValue(
                      ScrollViewer.CanContentScrollProperty) ==
                      DependencyProperty.UnsetValue,
                "list scrolling uses the original v0.3.60 defaults");
            var centralSplitter = mailWorkspace.Children.OfType<GridSplitter>()
                .Single(splitter => Grid.GetColumn(splitter) == 1);
            Check(centralSplitter.Width == 6 &&
                  centralSplitter.ResizeDirection == GridResizeDirection.Columns &&
                  centralSplitter.ResizeBehavior == GridResizeBehavior.PreviousAndNext,
                "central splitter uses the v0.3.60 geometry and resize behavior");
            var pinRow = modernList.ItemTemplate?.LoadContent() as Grid;
            Check(pinRow is not null, "active mail template can be instantiated");
            var pinAction = FindDescendant<Button>(pinRow!,
                b => b.Name == "ModernPinActionButton");
            Check(pinAction is not null, "active mail row contains a pin action");
            Check(Grid.GetColumn(pinAction!) == 4 &&
                  pinAction.ToolTip?.ToString() == "Закрепить / открепить",
                "pin sits next to the star and advertises both operations");
            var pinShape = pinAction!.Content as System.Windows.Shapes.Path;
            Check(pinShape is not null,
                "pin button uses the shared vector icon");
            var pinnedTestMail = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "pin-test-123", "Письмо", "Текст", "Отправитель",
                "sender@example.invalid", null, null, 0, false, false, false)
                with { Pinned = true };
            pinRow!.DataContext = pinnedTestMail;
            pinRow.Measure(new Size(1200, 42));
            pinRow.Arrange(new Rect(0, 0, 1200, 42));
            pinRow.UpdateLayout();
            Check(pinShape!.Fill is SolidColorBrush pinnedFill &&
                  pinnedFill.Color == (Color)ColorConverter.ConvertFromString("#C62832"),
                "pinned mail icon uses the fixed #C62832 in the modern row");
            pinRow.DataContext = pinnedTestMail with { Pinned = false };
            pinRow.UpdateLayout();
            Check(pinShape.Fill is SolidColorBrush unpinnedFill &&
                  unpinnedFill.Color.A == 0,
                "unpinning switches the icon back to a hollow outline");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "FlagActionButton") is not null,
                "restoring pin does not remove the star button");
            var senderAvatar = FindDescendant<Image>(pinRow, _ => true);
            Check(senderAvatar is not null &&
                  BindingOperations.GetBinding(senderAvatar, Image.SourceProperty)?.Path?.Path
                    == "CorrespondentAvatarUrl",
                "correspondent portraits use the original native WPF image binding");

            // Stage 1: only the active modern row owns columns and commands.
            // Legacy V033/XAML item templates must never silently replace it.
            Check(modernList.GroupStyle.Count == 1 &&
                  modernList.GroupStyle[0].HeaderTemplate is not null,
                "the single active list retains its date-group header");
            Check(pinRow.ColumnDefinitions.Count == 15,
                "the canonical row retains fifteen configurable column slots");
            Check(FindDescendant<CheckBox>(pinRow,
                c => c.Name == "BulkSelectCheckBox") is not null,
                "the canonical row retains checkbox-based bulk selection");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "ModernUnreadActionButton") is not null,
                "the canonical row retains its unread marker command");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "PinActionButton" ||
                     b.Name == "UnreadActionButton" ||
                     b.Name == "ArchiveActionButton") is null,
                "obsolete V033 row actions cannot coexist in the active template");
            Check(FindDescendant<System.Windows.Shapes.Path>(pinRow,
                p => Grid.GetColumn(p) == 10) is not null,
                "attachment indicator remains in the canonical row");

            var singleRowOrdering = new MailDateThenPinnedComparer();
            var earlier = pinnedTestMail with { Pinned = false, DateUnix = 1700000000 };
            var later = pinnedTestMail with { Pinned = false, DateUnix = 1700003600 };
            Check(singleRowOrdering.Compare(pinnedTestMail, later) < 0,
                "pinned mail stays ahead of a newer unpinned message");
            Check(singleRowOrdering.Compare(later, earlier) < 0,
                "unpinned messages remain ordered by descending timestamp");
            var dateGroups = new MailSectionGroupDescription();
            Check((string)dateGroups.GroupNameFromItem(
                      pinnedTestMail, 0, System.Globalization.CultureInfo.InvariantCulture)
                  == "Закреплённые",
                "pinned group remains distinct from ordinary date groups");



            var settingsAction = FindDescendant<Button>(mail,
                b => b.Content?.ToString()?.Contains("Настройки", StringComparison.Ordinal) == true);
            Check(settingsAction is not null, "Settings navigation is visible");
            settingsAction!.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, settingsAction));
            mail.UpdateLayout();
            var settingsWindow = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w => w.Title == "Настройки — MailRu Desktop");
            Check(settingsWindow is { IsVisible: true, Owner: not null } &&
                  ReferenceEquals(settingsWindow.Owner, mail) &&
                  ReferenceEquals(settingsWindow.Content,
                      Require<Grid>(mail, "SettingsWorkspace")),
                "Settings open in one independent owned window");
            var settingsGrid = Require<Grid>(mail, "SettingsWorkspace");
            Check(settingsGrid.ColumnDefinitions.Count == 1,
                "settings no longer contain the diagnostic panel or its splitter");

            var backgroundSwitch = Require<CheckBox>(mail, "BackgroundPushEnabledCheckBox");
            Check(!backgroundSwitch.IsVisible,
                "legacy automatic-subscription checkbox is removed from everyday settings");
            Check(Require<CheckBox>(mail, "TaskbarNotificationsEnabledCheckBox").IsVisible,
                "Windows popup preference remains visible in ordinary settings");
            var filterSwitch = Require<CheckBox>(
                mail, "ReplaySuppressionEnabledCheckBox");
            Check(filterSwitch.IsVisible && filterSwitch.IsEnabled,
                "local replay filter switch is visible and adjustable");
            var originalReplaySetting = filterSwitch.IsChecked == true;
            filterSwitch.IsChecked = !originalReplaySetting;
            Check(new AppSettingsStore().LoadReplaySuppressionEnabled() ==
                  !originalReplaySetting,
                "toggling the replay filter saves a real preference immediately");
            filterSwitch.IsChecked = originalReplaySetting;
            Check(new AppSettingsStore().LoadReplaySuppressionEnabled() ==
                  originalReplaySetting,
                "replay-filter preference can be restored without app restart");
            Check(Require<SelectableText>(mail, "BackgroundPushStatusText").IsVisible,
                "brief connection status remains visible");
            var diagnosticExpander = Require<Expander>(mail, "UnifiedDiagnosticsExpander");
            var diagnosticsHost = diagnosticExpander.Parent as Grid;
            var mailListGrid = FindDescendant<Grid>(
                Require<Grid>(mail, "MailWorkspace"),
                x => x.RowDefinitions.Cast<RowDefinition>().Any(
                    r => r.Name == "DiagnosticsPaneRow"));
            Check(diagnosticsHost is not null && Grid.GetRow(diagnosticsHost) == 4 &&
                  mailListGrid is not null &&
                  !diagnosticExpander.IsExpanded,
                "diagnostics are collapsed under the message list, not in settings");
            var diagnosticPane = mailListGrid!.RowDefinitions[4];
            var diagnosticSplitter = Require<GridSplitter>(mail, "DiagnosticsPaneSplitter");
            Check(diagnosticPane.Height.Value <= 40 && !diagnosticSplitter.IsVisible,
                "collapsed diagnostic panel gives vertical space back to messages");
            diagnosticExpander.IsExpanded = true;
            mail.UpdateLayout();
            Check(diagnosticPane.Height.Value >= 180 && diagnosticSplitter.IsVisible,
                "expanded diagnostics provide a resizable panel beneath the mail list");
            var diagTabs = Require<TabControl>(mail, "UnifiedDiagnosticsTabs");
            Check(diagTabs.Items.Count == 3,
                "Google PushMe, protocol and application diagnostics share one tool");
            diagTabs.SelectedIndex = 1;
            mail.UpdateLayout();
            Check(Require<TextBox>(mail, "HttpProtocolLogTextBox").IsReadOnly &&
                  Require<TextBox>(mail, "HttpProtocolLogTextBox").IsVisible,
                "full current-hour HTTP protocol viewer is present");
            diagTabs.SelectedIndex = 2;
            mail.UpdateLayout();
            Check(Require<TextBox>(mail, "ApplicationDiagnosticsLogTextBox").IsReadOnly,
                "old application diagnostic log is read-only in unified tool");
            diagTabs.SelectedIndex = 0;
            mail.UpdateLayout();
            var pushLog = Require<TextBox>(mail, "PushDiagnosticsLogTextBox");
            Check(pushLog.IsReadOnly && pushLog.IsVisible,
                "read-only Google PushMe diagnostic journal visible under mail list");
            foreach (var name in new[]
                     {
                         "RefreshPushDiagnosticsButton",
                         "CopyPushDiagnosticsButton",
                         "SavePushDiagnosticsButton",
                         "ClearPushDiagnosticsButton"
                     })
                Check(Require<Button>(mail, name).IsVisible,
                    "push diagnostics action exists: " + name);
            Check(mail.FindName("PushDiagnosticsExportModeComboBox") is null &&
                  mail.FindName("ApplicationDiagnosticsExportModeComboBox") is null,
                "diagnostics do not offer address masking or abbreviated export");
            var refreshPushLog = Require<Button>(mail, "RefreshPushDiagnosticsButton");
            refreshPushLog.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, refreshPushLog));
            Check(pushLog.Text.Contains("Google / PushMe", StringComparison.Ordinal),
                "diagnostic viewer refreshes without making network calls");
            var pushSettings = Require<Button>(mail, "OpenPushProbeButton");
            Check(pushSettings.IsVisible &&
                  pushSettings.Content?.ToString() == "Проверить один аккаунт",
                "single-account diagnostic test is inside collapsed unified tool");
            pushSettings.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, pushSettings));
            mail.UpdateLayout();
            var pushWindow = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w => w.Title.Contains("Проверка мгновенных уведомлений", StringComparison.Ordinal));
            Check(pushWindow is not null && pushWindow.IsVisible,
                "Native push experiment opens a separate diagnostics window");
            var runControl = FindDescendant<Button>(pushWindow!,
                b => b.Content?.ToString() == "Начать проверку");
            Check(runControl is not null && !runControl.IsEnabled,
                "No push subscription can start without explicit consent");
            var selectAccount = FindDescendant<ComboBox>(pushWindow!,
                cb => cb.MinWidth >= 300 && cb.IsEnabled);
            Check(selectAccount is not null,
                "manual test displays account selector before registration");
            var trayNotifyCheckbox = Require<CheckBox>(
                mail, "TaskbarNotificationsEnabledCheckBox");
            Check(trayNotifyCheckbox.IsEnabled,
                "taskbar popup preference remains adjustable during manual-only test");
            pushWindow!.Close();

            Check(Require<SelectableText>(mail, "GoogleRecipientStatusText").IsVisible,
                "protected Google registration has a separate visible status");
            Check(Require<SelectableText>(mail, "GoogleMcsStatusText").IsVisible,
                "Google MCS channel has an independent visible connection state");
            var groupButton = Require<Button>(mail, "OpenPushGroupManagerButton");
            Check(groupButton.IsVisible && groupButton.IsEnabled &&
                groupButton.Content?.ToString() == "Google и группы PushMe",
                "durable Google recipient and group manager is available");
            groupButton.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, groupButton));
            mail.UpdateLayout();
            var groupManager = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w =>
                    w.Title.Contains("Google и группы PushMe", StringComparison.Ordinal));
            Check(groupManager is not null && groupManager.IsVisible,
                "persistent group manager opens without network startup");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Зарегистрировать Google") is not null,
                "stage-one explicit Google registration exists");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Остановить приём") is not null,
                "MCS-only Stop button is separate from group deletion");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Удалить выбранную группу") is not null,
                "address-specific PushMe group deletion has an independent action");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Удалить регистрацию Google") is not null,
                "Google deletion requires its own explicit action");
            groupManager!.Close();

            // Functional offline UI test: with 20 synthetic mailboxes the
            // selector starts with 19, requires consent, and never selects 20.
            IReadOnlyList<string>? submitted = null;
            var fakeLogins = Enumerable.Range(1, 20)
                .Select(i => "dummy" + i + "@example.invalid");
            var simulated = new Group19PushProbeWindow(fakeLogins,
                selected => { submitted = selected.ToArray(); return true; },
                () => { }) { Owner = mail };
            simulated.Show();
            simulated.UpdateLayout();
            var simulatedStart = FindDescendant<Button>(simulated,
                b => b.Content?.ToString() == "Зарегистрировать 19 аккаунтов");
            var confirmation = FindDescendant<CheckBox>(simulated,
                c => c.Content?.ToString()?.Contains("Подтверждаю регистрацию только",
                    StringComparison.Ordinal) == true);
            Check(simulatedStart is not null && !simulatedStart.IsEnabled,
                "exactly nineteen are preselected but registration requires user consent");
            Check(confirmation is not null, "explicit nineteen-account consent exists");
            confirmation!.IsChecked = true;
            Check(simulatedStart!.IsEnabled,
                "nineteen preselected accounts enable registration after consent");
            simulatedStart.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, simulatedStart));
            Check(submitted is { Count: 19 } &&
                  submitted.Distinct(StringComparer.OrdinalIgnoreCase).Count() == 19,
                "manual group test passes exactly nineteen unique account IDs");
            simulated.Close();

            var palette = FindDescendant<Expander>(settingsWindow!,
                e => string.Equals(e.Header?.ToString(),
                    "Цветовая схема", StringComparison.Ordinal));
            Check(palette is not null, "palette editor is present");
            palette!.IsExpanded = true;
            mail.UpdateLayout();
            Check(FindDescendant<TextBox>(palette,
                b => b.Name == "PrimaryColorTextBox") is not null,
                "a single primary color input is present");
            Check(FindParent<GroupBox>(palette!)?.Header?.ToString() == "Внешний вид",
                "palette section is inside Appearance rather than beside it");

            var spellToggle = Require<CheckBox>(mail, "SpellCheckEnabledCheckBox");
            Check(spellToggle.IsChecked == ComposeSpellChecking.Enabled,
                "settings display the actual spelling state");
            var livePreviewEditor = FindDescendant<RichTextBox>(
                Require<FrameworkElement>(mail, "PreviewComposePanel"), _ => true);
            Check(livePreviewEditor is not null &&
                  VisualTreeHelper.GetParent(livePreviewEditor) is Border
                  { BorderThickness.Left: 1 },
                "inline forwarding/reply editor has the same themed border");
            var previousSpellSetting = spellToggle.IsChecked == true;
            spellToggle.IsChecked = !previousSpellSetting;
            Check(ComposeSpellChecking.Enabled == !previousSpellSetting,
                "spelling checkbox immediately changes the runtime setting");
            spellToggle.IsChecked = previousSpellSetting;
            Check(ComposeSpellChecking.Enabled == previousSpellSetting,
                "spelling checkbox restores the runtime setting");

            var signature = FindDescendant<Expander>(settingsWindow!,
                e => e.Header?.ToString() == "Подписи");
            var template = FindDescendant<Expander>(settingsWindow!,
                e => e.Header?.ToString() == "Шаблоны");
            Check(signature is not null && template is not null,
                "signature/template expanders are present");
            Check(ReferenceEquals(VisualTreeHelper.GetParent(signature!),
                     VisualTreeHelper.GetParent(template!)),
                "signature and template are siblings");
            var settingsStack = (Panel)VisualTreeHelper.GetParent(signature!);
            Check(settingsStack.Children.IndexOf(template!) ==
                  settingsStack.Children.IndexOf(signature!) + 1,
                "Templates appears immediately after Signatures");
            template!.IsExpanded = true;
            mail.UpdateLayout();
            Check(Require<ListBox>(mail, "MailTemplatesListBox") is { },
                "settings keep a template list");
            Check(Require<Button>(mail, "EditSelectedTemplateButton") is { IsEnabled: false },
                "Edit is disabled with no selected template");
            Check(Require<Button>(mail, "DeleteSelectedTemplateButton") is { IsEnabled: false },
                "Delete is disabled with no selected template");
            Check(mail.FindName("TemplateBodyTextBox") is null,
                "template body no longer occupies the settings sidebar");

            var templateTestRoot = Path.Combine(Path.GetTempPath(),
                "MailRuTemplateEditor-" + Guid.NewGuid().ToString("N"));
            try
            {
                var files = new MarkdownTemplateStore(Path.Combine(templateTestRoot, "Templates"));
                var signs = new MarkdownSignatureStore(Path.Combine(templateTestRoot, "Signatures"));
                var localSettings = new AppSettingsStore(templateTestRoot);
                signs.Save(new SavedSignature { Name = "Рабочая", Body = "**Подпись**" });
                var sampleTemplate = new SavedMailTemplate
                {
                    Name = "Проверочный", Subject = "Тема", Body = "**Текст письма**",
                    Attachments = new List<string>()
                };
                var detached = new TemplateEditorWindow(mail, files, signs, localSettings, sampleTemplate);
                detached.Show();
                detached.UpdateLayout();
                Check(detached.IsVisible && detached.MinWidth >= 600,
                    "template opens a separate resizable window");
                var templateRich = FindDescendant<RichTextBox>(detached, _ => true);
                Check(templateRich is not null, "template editor reuses rich mail formatting");
                Check(templateRich!.SpellCheck.IsEnabled == ComposeSpellChecking.Enabled,
                    "template editor inherits the shared spelling setting");
                Check(VisualTreeHelper.GetParent(templateRich) is Border
                      { BorderThickness.Left: 1 },
                    "standalone template body has a visible themed border");
                Check(FindDescendant<ComboBox>(detached,
                          x => x.DisplayMemberPath == nameof(SavedSignature.Name)) is not null,
                    "template editor exposes file-backed signature choices");
                Check(FindDescendant<TextBox>(detached,
                          x => x.Name == "ComposeToTextBox") is null,
                    "template editor never includes a recipient address field");
                Check(FindDescendant<TextBlock>(detached,
                          x => x.Text == "Тема письма") is null,
                    "template editor contains no subject field");
                detached.Close();
                var position = localSettings.LoadWindowGeometry("template-editor");
                Check(position is not null && position.Width > 0 && position.Height > 0,
                    "template editor remembers its independent window geometry");
            }
            finally
            {
                if (Directory.Exists(templateTestRoot))
                    Directory.Delete(templateTestRoot, recursive: true);
            }


            settingsAction!.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, settingsAction));
            Check(ReferenceEquals(settingsWindow,
                    Application.Current.Windows.OfType<Window>()
                        .FirstOrDefault(w => w.Title == "Настройки — MailRu Desktop")),
                "Repeated settings click reactivates the same window");
            settingsWindow!.Close();
            mail.UpdateLayout();
            Check(Require<Grid>(mail, "SettingsWorkspace").Visibility == Visibility.Collapsed &&
                  Require<Grid>(mail, "MailWorkspace").Visibility == Visibility.Visible,
                "Closing detached settings restores the hidden workspace");

            action.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, action));
            editor = Application.Current.Windows
                .OfType<Window>()
                .FirstOrDefault(w => w.Title.StartsWith("Новое письмо", StringComparison.Ordinal));
            Check(editor is not null && editor.IsVisible, "New Mail opens its own window");
            var from = Require<TextBox>(mail, "ComposeFromTextBox");
            Check(from.IsReadOnly && from.Text.Length > 0,
                "From address is always visible and read-only");
            Check(ReferenceEquals(Window.GetWindow(from), editor),
                "From field belongs to the independent New Mail window");
            Check(Require<ComboBox>(mail, "ComposeSignatureComboBox").IsVisible,
                "signature chooser is visible in New Mail");
            Check(Require<ComboBox>(mail, "ComposeTemplateComboBox").IsVisible,
                "template chooser is visible in New Mail");
            Check(Require<SelectableText>(mail, "ComposeHeaderText").IsVisible,
                "New Mail keeps its visible heading");
            var outgoingRich = FindDescendant<RichTextBox>(editor!, _ => true);
            Check(outgoingRich is not null &&
                  VisualTreeHelper.GetParent(outgoingRich) is Border
                  { BorderThickness.Left: 1 },
                "new-mail body has the same themed outline");
            var mainRichEditor = (RichComposeEditor?)typeof(MainWindow)
                .GetField("_composeRichEditor", BindingFlags.Instance | BindingFlags.NonPublic)!
                .GetValue(mail);
            Check(mainRichEditor is not null, "shared rich editor is initialized");
            outgoingRich!.Document.Blocks.Clear();
            outgoingRich.Document.Blocks.Add(new Paragraph(
                new Run("Пример https://example.com и www.example.org")));
            var linkedHtml = mainRichEditor!.ToHtml();
            Check(linkedHtml.Contains("href=\"https://example.com", StringComparison.Ordinal) &&
                  linkedHtml.Contains("href=\"https://www.example.org", StringComparison.Ordinal),
                "URL text becomes safe HTML links before sending");
            var send = Require<Button>(mail, "SendButton");
            Check(ReferenceEquals(
                    send.ReadLocalValue(Control.BackgroundProperty),
                    DependencyProperty.UnsetValue),
                "send button uses the shared theme without a forced blue fill");

            editor.Close();
            editor = null;
            var showReply = typeof(MainWindow).GetMethod("OpenReplyComposeWindow",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(showReply is not null,
                "standalone New Mail reply handler is available");
            var sample = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "reply-test-123", "Договор", "Текст письма", "Тест",
                "sender@example.invalid", null, null, 0, true, false, false);
            showReply!.Invoke(mail, new object?[] { sample, null });
            editor = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w => w.Title.StartsWith("Ответ: Договор", StringComparison.Ordinal));
            Check(editor is { IsVisible: true },
                "Reply launches the independent composer without the New Mail caption");
            var archiveAfterReply = Require<CheckBox>(mail, "ArchiveAfterReplyCheckBox");
            Check(archiveAfterReply.Visibility == Visibility.Visible &&
                  archiveAfterReply.IsChecked == true,
                "Reply defaults to archiving its source after confirmed sending");
            Check(!Require<SelectableText>(mail, "ComposeHeaderText").IsVisible,
                "reply form does not display the redundant New Mail heading");
            Check(Require<TextBox>(mail, "ComposeToTextBox").Text ==
                    "sender@example.invalid" &&
                  Require<TextBox>(mail, "ComposeSubjectTextBox").Text == "Re: Договор" &&
                  Require<TextBox>(mail, "ComposeBodyTextBox").Text.Contains("Текст письма"),
                "reply fills recipient, Re subject, and original quote");
            var applyTemplate = typeof(MainWindow).GetMethod("ApplyComposeTemplate",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(applyTemplate is not null, "compose template method exists");
            var replyEditor = FindDescendant<RichTextBox>(editor!, _ => true);
            Check(replyEditor is not null, "reply uses the rich editor");
            replyEditor!.CaretPosition = replyEditor.Document.ContentEnd;
            applyTemplate!.Invoke(mail, new object[] { new SavedMailTemplate
            {
                Name = "Ответ", Subject = "Запрещённая смена темы",
                Body = "**Текст из шаблона**", Attachments = new List<string>()
            } });
            var composedText = Require<TextBox>(mail, "ComposeBodyTextBox").Text;
            Check(Require<TextBox>(mail, "ComposeSubjectTextBox").Text == "Re: Договор" &&
                  composedText.Contains("Текст из шаблона", StringComparison.Ordinal) &&
                  composedText.Contains("> Текст письма", StringComparison.Ordinal) &&
                  composedText.IndexOf("Текст из шаблона", StringComparison.Ordinal) <
                  composedText.IndexOf("Исходное письмо", StringComparison.Ordinal),
                "template never changes reply subject or removes quoted correspondence");
            var applySignature = typeof(MainWindow).GetMethod("ApplyComposeSignature",
                BindingFlags.Instance | BindingFlags.NonPublic);
            replyEditor.CaretPosition = replyEditor.Document.ContentEnd;
            applySignature!.Invoke(mail, new object[] { new SavedSignature
            {
                Name = "Рабочая", Body = "С уважением,\nОтдел"
            }, true });
            var afterSignature = Require<TextBox>(mail, "ComposeBodyTextBox").Text;
            Check(afterSignature.IndexOf("С уважением,", StringComparison.Ordinal) <
                  afterSignature.IndexOf("Исходное письмо", StringComparison.Ordinal) &&
                  afterSignature.Contains("> Текст письма", StringComparison.Ordinal),
                "signature position is independent of caret in the quoted history");
            var replyId = typeof(MainWindow).GetField("_composeReplyToId",
                BindingFlags.Instance | BindingFlags.NonPublic)?.GetValue(mail) as string;
            Check(replyId == "reply-test-123",
                "sending from standalone reply retains precise source message ID");
            Check(Require<FrameworkElement>(mail, "PreviewComposePanel").Visibility !=
                  Visibility.Visible,
                "Reply no longer opens the inline editor above the mail body");
            editor.Close();
            editor = null;

            // Regression: the last message of an unindexed conversation was
            // sent by the account owner. Its own From must never fill To.
            var ownLoginField = typeof(MainWindow).GetField("_activeLogin",
                BindingFlags.Instance | BindingFlags.NonPublic)!;
            var originalLogin = ownLoginField.GetValue(mail);
            try
            {
                ownLoginField.SetValue(mail, "me@example.test");
                showReply.Invoke(mail, new object?[] { ownOutgoing, null });
                editor = Application.Current.Windows.OfType<Window>()
                    .FirstOrDefault(w => w.Title.StartsWith("Ответ: Re: Договор",
                        StringComparison.Ordinal));
                Check(editor is { IsVisible: true } &&
                      Require<TextBox>(mail, "ComposeToTextBox").Text ==
                      "client@example.test, copy@example.test" &&
                      !Require<TextBox>(mail, "ComposeToTextBox").Text
                          .Contains("me@example.test", StringComparison.OrdinalIgnoreCase),
                    "reply to own unindexed last message fills verified To/Cc, never own From");
                editor?.Close();
                editor = null;
            }
            finally { ownLoginField.SetValue(mail, originalLogin); }

            var dialogType = typeof(MainWindow).Assembly.GetType("MailRuDesktop.App.AppDialog");
            var createNotice = dialogType?.GetMethod("CreateWindow",
                BindingFlags.Static | BindingFlags.NonPublic);
            Check(createNotice is not null, "shared notification window factory exists");
            notice = createNotice!.Invoke(null,
                new object[] { mail, "Тест", "Короткое сообщение" }) as Window;
            Check(notice is not null, "short notification is created");
            ((StackPanel)notice!.Tag).Children.Add(new Button { Content = "Понятно",
                Padding = new Thickness(14, 7, 14, 7) });
            notice.Show();
            notice.UpdateLayout();
            Check(notice.SizeToContent == SizeToContent.Height,
                "notification height adapts to content");
            Check(notice.MinHeight <= 170 && notice.ActualHeight < 260,
                "short notification has no oversized lower empty region");
            notice.Close();
            notice = null;

            var pickerType = typeof(MainWindow).Assembly.GetType(
                "MailRuDesktop.App.PaletteColorPickerWindow");
            Check(pickerType is not null, "color picker window exists");
            colorPicker = Activator.CreateInstance(pickerType!,
                new object[] { mail, "Проверка цвета", "#FF0000" }) as Window;
            Check(colorPicker is not null, "palette picker can be constructed");
            var stripField = pickerType!.GetField("_brightnessBitmap",
                BindingFlags.Instance | BindingFlags.NonPublic);
            var strip = stripField?.GetValue(colorPicker) as WriteableBitmap;
            Check(strip is not null, "right-side brightness strip is available");
            var initial = new byte[strip!.PixelWidth * strip.PixelHeight * 4];
            strip.CopyPixels(initial, strip.PixelWidth * 4, 0);
            var moveColor = pickerType.GetMethod("PickSurface",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(moveColor is not null, "surface click operation exists");
            moveColor!.Invoke(colorPicker, new object[] { new Point(210, 50) });
            var updated = new byte[initial.Length];
            strip.CopyPixels(updated, strip.PixelWidth * 4, 0);
            Check(!initial.SequenceEqual(updated),
                "right brightness gradient redraws when main color surface changes");
            colorPicker!.Close();
            colorPicker = null;

            VerifyPermanentBrowserShell(mail, reader);

            var tray = typeof(MainWindow).GetField("_pushNotificationArea",
                BindingFlags.NonPublic | BindingFlags.Instance)?.GetValue(mail)
                as System.Windows.Forms.NotifyIcon;
            Check(tray is not null && tray.Visible,
                "tray icon remains visible independently of push subscription");
            Check(tray.ContextMenuStrip?.Items.Count == 2 &&
                  tray.ContextMenuStrip.Items[0].Text == "Развернуть" &&
                  tray.ContextMenuStrip.Items[1].Text == "Выход",
                "tray offers Restore and Exit");
            mail.Close();
            Check(!mail.IsVisible && !mail.ShowInTaskbar,
                "window X hides application into tray without terminating it");
            typeof(MainWindow).GetMethod("RestoreFromTray",
                BindingFlags.Instance | BindingFlags.NonPublic)?.Invoke(mail, null);
            Check(mail.IsVisible && mail.ShowInTaskbar,
                "tray Restore makes application visible again");
            typeof(MainWindow).GetMethod("ExitFromTray",
                BindingFlags.Instance | BindingFlags.NonPublic)?.Invoke(mail, null);

            Console.WriteLine("Windows WPF UI interaction smoke: PASS");
            return 0;
        }
        catch (Exception ex)
        {
            Console.Error.WriteLine("Windows WPF UI interaction smoke: FAIL");
            Console.Error.WriteLine(ex);
            return 1;
        }
        finally
        {
            colorPicker?.Close();
            notice?.Close();
            editor?.Close();
            mail?.Close();
        }
    }

    private static void VerifyPermanentBrowserShell(
        Window owner, Microsoft.Web.WebView2.Wpf.WebView2 reader)
    {
        var ready = AwaitOnDispatcher(WaitForShell(reader));
        Check(ready, "WebView2 has initialized exactly one persistent reader shell");

        var browser = reader.CoreWebView2!;
        var extraNavigations = 0;
        reader.NavigationStarting += (_, _) => extraNavigations++;
        var type = owner.GetType();
        var begin = type.GetMethod("BeginReaderTransition",
            BindingFlags.Instance | BindingFlags.NonPublic);
        var show = type.GetMethod("ShowReaderHtml",
            BindingFlags.Instance | BindingFlags.NonPublic);
        Check(begin is not null && show is not null,
            "browser-shell staging entry points are present");

        begin!.Invoke(owner, new object?[] { null, "Загрузка письма…" });
        show!.Invoke(owner, new object?[]
        {
            "<p id='integration-message'>FIRST</p>" +
            "<img src='data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs='>"
        });
        Check(AwaitOnDispatcher(WaitForMessage(browser, "FIRST")),
            "first message appears with its image in a single shell");

        begin.Invoke(owner, new object?[] { null, "Загрузка письма…" });
        show.Invoke(owner, new object?[]
        {
            "<p id='integration-message'>SECOND</p>"
        });
        Check(AwaitOnDispatcher(WaitForMessage(browser, "SECOND")),
            "new letter replaces previous document atomically");
        Check(extraNavigations == 0,
            "switching mail must not navigate the top-level WebView2");
        Check(reader.Visibility == Visibility.Visible,
            "the same WPF browser remains continuously visible");
        // Real WebView2 integration: synthesize a click on an ordinary link
        // inside the sandboxed iframe while IsScriptEnabled is false.
        // This must reach the native FrameNavigationStarting event; checking
        // only generated HTML would not detect a dead action button.
        var nonce = Guid.NewGuid().ToString("N");
        var clickedUrl = ConversationActionNavigation.Create("reply", "id-1", nonce);
        string? navigated = null;
        void ObserveClick(object? _, Microsoft.Web.WebView2.Core.CoreWebView2NavigationStartingEventArgs args)
        {
            if (!ConversationActionNavigation.IsCommandUrl(args.Uri)) return;
            navigated = args.Uri;
            args.Cancel = true;
        }
        browser.FrameNavigationStarting += ObserveClick;
        try
        {
            begin.Invoke(owner, new object?[] { null, "Загрузка письма…" });
            show.Invoke(owner, new object?[]
            {
                "<a id='native-reply-integration' href='" + clickedUrl +
                "'>Ответить на выбранное письмо</a>"
            });
            Check(AwaitOnDispatcher(WaitForNativeAction(browser)),
                "action link appears in the sandboxed mail reading frame");
            var clickResult = AwaitOnDispatcher(browser.ExecuteScriptAsync(
                "(() => {const frame=document.querySelector('iframe[data-active]');" +
                "const link=frame?.contentDocument?.getElementById('native-reply-integration');" +
                "if(!link)return false;link.click();return true;})()"));
            Check(clickResult == "true",
                "click can be dispatched to the action without scripts in mail content");
            Check(AwaitOnDispatcher(WaitForNativeNavigation(() => navigated)),
                "click reaches native FrameNavigationStarting and is cancelled");
            Check(ConversationActionNavigation.TryParse(navigated, nonce,
                    out var action, out var selectedId) &&
                  action == "reply" && selectedId == "id-1",
                "real browser click delivers the exact selected mail action");
        }
        finally
        {
            browser.FrameNavigationStarting -= ObserveClick;
        }
    }

    private static async Task<bool> WaitForShell(
        Microsoft.Web.WebView2.Wpf.WebView2 reader)
    {
        for (var i = 0; i < 120; i++)
        {
            if (reader.CoreWebView2 is not null)
            {
                var result = await reader.CoreWebView2.ExecuteScriptAsync(
                    "(Boolean(document.getElementById('frames')))");
                if (result == "true")
                    return true;
            }
            await Task.Delay(70);
        }
        return false;
    }

    private static async Task<bool> WaitForNativeAction(
        Microsoft.Web.WebView2.Core.CoreWebView2 browser)
    {
        for (var i = 0; i < 80; i++)
        {
            var result = await browser.ExecuteScriptAsync(
                "Boolean(document.querySelector('iframe[data-active]')?.contentDocument" +
                "?.getElementById('native-reply-integration'))");
            if (result == "true") return true;
            await Task.Delay(65);
        }
        return false;
    }

    private static async Task<bool> WaitForNativeNavigation(Func<string?> observe)
    {
        for (var i = 0; i < 80; i++)
        {
            if (observe() is not null) return true;
            await Task.Delay(65);
        }
        return false;
    }

    private static async Task<bool> WaitForMessage(
        Microsoft.Web.WebView2.Core.CoreWebView2 browser, string expected)
    {
        for (var i = 0; i < 100; i++)
        {
            var response = await browser.ExecuteScriptAsync(
                "(() => {const frames=document.querySelectorAll('iframe[data-active]');" +
                "return frames.length === 1 && " +
                "frames[0].contentDocument?.getElementById('integration-message')?.textContent " +
                "=== " + System.Text.Json.JsonSerializer.Serialize(expected) + ";})()");
            if (response == "true")
                return true;
            await Task.Delay(80);
        }
        return false;
    }

    private static T AwaitOnDispatcher<T>(Task<T> task)
    {
        var frame = new DispatcherFrame();
        var watch = Stopwatch.StartNew();
        var ticker = new DispatcherTimer(DispatcherPriority.Background)
        {
            Interval = TimeSpan.FromMilliseconds(30)
        };
        ticker.Tick += (_, _) =>
        {
            if (task.IsCompleted || watch.Elapsed > TimeSpan.FromSeconds(15))
                frame.Continue = false;
        };
        ticker.Start();
        try { Dispatcher.PushFrame(frame); }
        finally { ticker.Stop(); }
        if (!task.IsCompleted)
            throw new TimeoutException("Timed out waiting for WebView2 browser shell.");
        return task.GetAwaiter().GetResult();
    }

    private static T Require<T>(FrameworkElement root, string name)
        where T : FrameworkElement
    {
        var found = root.FindName(name) as T;
        Check(found is not null, $"XAML named control {name}");
        return found!;
    }

    private static T? FindDescendant<T>(DependencyObject source, Func<T,bool> match)
        where T : DependencyObject
    {
        if (source is T item && match(item))
            return item;
        for (var i = 0; i < VisualTreeHelper.GetChildrenCount(source); i++)
        {
            var found = FindDescendant(VisualTreeHelper.GetChild(source, i), match);
            if (found is not null)
                return found;
        }
        return null;
    }

    private static T? FindParent<T>(DependencyObject source) where T : DependencyObject
    {
        var parent = VisualTreeHelper.GetParent(source);
        while (parent is not null)
        {
            if (parent is T typed)
                return typed;
            parent = VisualTreeHelper.GetParent(parent);
        }
        return null;
    }

    private static void Expect<T>(T actual, T expected, string label)
    {
        Check(EqualityComparer<T>.Default.Equals(actual, expected), label);
    }

    private sealed class MailingWireRecorder : HttpMessageHandler
    {
        internal string LastPath { get; private set; } = "";
        internal string LastForm { get; private set; } = "";

        protected override async Task<HttpResponseMessage> SendAsync(
            HttpRequestMessage request, CancellationToken cancellationToken)
        {
            LastPath = request.RequestUri?.AbsolutePath ?? "";
            LastForm = request.Content is null ? "" :
                await request.Content.ReadAsStringAsync(cancellationToken);
            return new HttpResponseMessage(System.Net.HttpStatusCode.OK)
            {
                Content = new StringContent("{\"status\":200}")
            };
        }
    }

    private static void Check(bool condition, string description)
    {
        if (!condition)
            throw new InvalidOperationException(description);
        Console.WriteLine("PASS " + description);
    }
}
