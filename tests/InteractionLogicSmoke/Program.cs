using System.Net;
using MailRuDesktop.App;
using MailRuDesktop.Protocol;

static void Expect<T>(T actual, T expected, string label)
{
    if (!EqualityComparer<T>.Default.Equals(actual, expected))
        throw new Exception($"{label}: expected {expected}, got {actual}");
    Console.WriteLine("PASS " + label);
}

// Synthetic MCS event=4: only structured data is extracted. Other events
// and remote commands remain ignored; no external requests in these tests.
static byte[] PushKv(string key, string value) =>
    PushWire.BytesField(7, PushWire.Append(
        PushWire.TextField(1, key), PushWire.TextField(2, value)));
var mailFrame = PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushKv("event", "4"),
    PushKv("account", "test@example.invalid"),
    PushKv("id", "exact-message-42"),
    PushKv("folder_id", "0"),
    PushKv("sender_orig", "Иван Петров"),
    PushKv("text", "Документы к согласованию"),
    PushKv("snippet", "Договор приложен"),
    PushKv("uts", "1760000000"),
    PushKv("has_attachment", "1"),
    PushKv("importance", "1"),
    PushKv("ack", "https://untrusted.invalid/side-effect"));
var parsedMail = PushMailEvent.Parse(mailFrame);
Expect(parsedMail?.Account, "test@example.invalid",
    "event 4 notification keeps exact destination mailbox");
Expect(parsedMail?.MessageId, "exact-message-42",
    "event 4 keeps exact message identity for click navigation");
Expect(parsedMail?.Sender, "Иван Петров",
    "event 4 reads the sender displayed in notification");
Expect(parsedMail?.Subject, "Документы к согласованию",
    "event 4 reads the subject displayed in notification");
Expect(parsedMail?.HasAttachment, true,
    "notification shows confirmed attachment flag");
Expect(parsedMail?.Important, true,
    "notification shows confirmed importance flag");
Expect(PushMailEvent.MailboxLabel(parsedMail!), "test@example.invalid",
    "mailbox, not redundant new-message title, occupies notification header");
Expect(PushMailEvent.PreviewLine(parsedMail!).Contains("Вложения"), true,
    "notification includes attachment information");
Expect(PushMailEvent.Parse(PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushKv("event", "30"), PushKv("account", "test@example.invalid"))) is null,
    true, "remote-command events remain unhandled");
Expect(PushMailEvent.Parse(PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushKv("event", "10"), PushKv("account", "test@example.invalid"))) is null,
    true, "counter events remain unhandled");

// Conversation membership is sourced from independent message records, never
// from citations inside the body or duplicate folder representations.
var conversationJson = """
{"body":{"folders":[{"folder":0,"threads":[{
 "id":"thread-A","messages_count":5,
 "base_message":{"id":"m5","subject":"Re: договор","snippet":"Последнее","date":500,"folder":0,
   "correspondents":{"from":[{"name":"Иван","email":"sender@example.invalid"}]}},
 "messages":[
   {"id":"m5","subject":"Re: договор","date":500},
   {"id":"m4","subject":"Договор","date":400,"snippet":"Ответ"},
   {"id":"m3","subject":"Договор","date":300,"snippet":"Исходное"}],
 "representations":[{"id":"m5","date":500,"folder":0},
                    {"id":"m4","date":400,"folder":500010}]
}]}]}}
""";
var allConversations = MailRuConversationParser.Parse(conversationJson);
Expect(allConversations["m5"].VerifiedCount, 5,
    "server-declared conversation count is shown in existing mail-list column");
Expect(allConversations["m5"].Members.Count, 3,
    "multiple representations of the same message are not independent mail");
Expect(allConversations["m4"].ThreadId, "thread-A",
    "an older message can open its real conversation by exact id");
var renderedBodies = new Dictionary<string, MailRuFullMessage>(StringComparer.Ordinal)
{
    ["m5"] = new MailRuFullMessage("m5", "Re: договор", "Иван",
        "sender@example.invalid", ["test@example.invalid"], [],
        500, "<p>Текущий ответ</p>", "Текущий ответ", [],
        ""),
    ["m4"] = new MailRuFullMessage("m4", "Договор", "Анна",
        "other@example.invalid", ["test@example.invalid"], [],
        400, "<p>Другое письмо</p>", "Другое письмо", [], "")
};
var conversationMarkup =
    MailRuConversationHtml.Render(allConversations["m5"], "m4", renderedBodies);
Expect(conversationMarkup.Split("<details").Length - 1, 3,
    "three separate server messages become separate expandable cards");
Expect(conversationMarkup.Split("<details open").Length - 1, 1,
    "when opening old push notification only its exact message is expanded");
Expect(conversationMarkup.Contains("Писем в диалоге: 5"), true,
    "conversation header uses authoritative count");
Expect(conversationMarkup.Contains("Текущий ответ"), true,
    "newest message body is present");
Expect(conversationMarkup.Contains("Другое письмо"), true,
    "historical message has a distinct original body");
Expect(conversationMarkup.Contains("sandbox=\"allow-same-origin\""), true,
    "individual untrusted mail HTML is sandboxed separately");
var quotingJson = """
{"threads":[{"id":"thread-quote","base_message":
    {"id":"only","subject":"Re: вопрос","snippet":"----- Исходное письмо -----\\n> старое сообщение"},
    "representations":[{"id":"only","subject":"Re: вопрос"}]}]}
""";
var quotedConversation = MailRuConversationParser.Parse(quotingJson);
Expect(quotedConversation["only"].Members.Count, 1,
    "quoted text does not produce fake conversation members");

// Some smart-thread responses contain one mailbox row per real mail.
// A shared server-supplied thread_id, not a matching subject, must join them.
var splitThreadResponse = """
{"body":{"folders_content":[{"id":0,"threads":[
{"id":"independent-1","thread_id":"shared-conversation-5",
 "base_message":{"id":"m1","date":100,"subject":"Re: договор"}},
{"id":"independent-2","thread_id":"shared-conversation-5",
 "base_message":{"id":"m2","date":200,"subject":"Договор"}},
{"id":"independent-3","thread_id":"unrelated",
 "base_message":{"id":"m3","date":300,"subject":"Договор"}}
]}]}}
""";
var splitConversations = MailRuConversationParser.Parse(splitThreadResponse);
Expect(splitConversations["m1"].Members.Count, 2,
    "separate smart-thread objects with the same explicit server thread id form one conversation");
Expect(splitConversations["m2"].VerifiedCount, 2,
    "real thread count is rebuilt from confirmed unique message IDs");
Expect(splitConversations["m3"].Members.Count, 1,
    "same-looking subjects never merge into a different server conversation");
var replayTestDir = Path.Combine(Path.GetTempPath(),
    "mailru-event-replay-" + Guid.NewGuid().ToString("N"));
Directory.CreateDirectory(replayTestDir);
try
{
    var frameA = PushWire.Append(PushWire.TextField(5, "ru.mail.mailapp"),
        PushWire.BytesField(9, [1, 2, 3]));
    var frameRetransmitted = PushWire.Append(PushWire.TextField(5, "ru.mail.mailapp"),
        PushWire.BytesField(9, [4, 5, 6]));
    var firstStore = new PushProcessedEventStore(replayTestDir);
    Expect(firstStore.Record("first@example.invalid", "message-42", frameA,
        out var firstRemembered), true,
        "first occurrence of a real mail is delivered");
    Expect(firstRemembered, true, "first event is persisted under user DPAPI");
    var afterRestart = new PushProcessedEventStore(replayTestDir);
    Expect(afterRestart.Record("first@example.invalid", "message-42",
        frameRetransmitted, out var replayRemembered), false,
        "same mailbox and mail id is suppressed after restart even with a new MCS id");
    Expect(replayRemembered, true, "replay was verified against protected storage");
    Expect(afterRestart.Record("first@example.invalid", "message-43",
        frameA, out _), true,
        "another genuine new mail is never blocked");
    Expect(afterRestart.Record("second@example.invalid", "message-42",
        frameA, out _), true,
        "same mail id in another account cannot be suppressed");
    var file = File.ReadAllText(Path.Combine(replayTestDir,
        "processed-push-events.dat"));
    Expect(file.Contains("first@example.invalid"), false,
        "persisted replay store contains no plaintext mailbox addresses");
    Expect(file.Contains("message-42"), false,
        "persisted replay store contains no plaintext message ids");
}
finally
{
    Directory.Delete(replayTestDir, true);
}

// Diagnostic tests are entirely offline. No fake or real token may appear in a report.
PushDiagnostics.Clear();
PushDiagnostics.Record("GOOGLE", "MCS_LOGIN_OK");
PushDiagnostics.Record("PUSHME", "HTTP_STATUS", 403);
PushDiagnostics.Record("account@example.invalid", "SECRET-TOKEN");
PushDiagnostics.Failure("MCS_READ",
    new EndOfStreamException("token=PRIVATE_SECRET account@example.invalid"));
var diagnosticReport = PushDiagnostics.Report();
Expect(diagnosticReport.Contains("MCS_LOGIN_OK"), true, "push report records successful MCS stage");
Expect(diagnosticReport.Contains("count_or_code=403"), true,
    "push report records numeric HTTP code");
Expect(diagnosticReport.Contains("MCS_READ_MCS_END_OF_STREAM"), true,
    "push report records safe exception kind without its message");
PushDiagnostics.BeginAttempt(2);
PushDiagnostics.Failure("PUSHME_HTTP_SEND",
    new HttpRequestException("SECRET", null, System.Net.HttpStatusCode.BadRequest));
PushDiagnostics.Failure("WORKER_HTTP",
    new HttpRequestException("SECRET", null, System.Net.HttpStatusCode.BadRequest));
Expect(PushDiagnostics.LastFailure.StartsWith("PUSHME_HTTP_SEND:", StringComparison.Ordinal),
    true, "last failure retains exact failing PushMe stage, not generic retry catch");

Expect(diagnosticReport.Contains("PRIVATE_SECRET"), false,
    "push diagnostics never contain exception message secrets");
Expect(diagnosticReport.Contains("account@example.invalid"), false,
    "push diagnostics never contain mailbox addresses");
Expect(diagnosticReport.Contains("SECRET-TOKEN"), false,
    "push diagnostics reject unsafe arbitrary codes");
PushDiagnostics.RecordAccount("PUSHME", "ACCOUNT_UNSUBSCRIBE_START",
    "member19@example.invalid", "0123456789abcdef", 7, 19, "opabc123");
PushDiagnostics.RecordAccount("PUSHME", "ACCOUNT_UNSUBSCRIBE_OK",
    "member19@example.invalid", "0123456789abcdef", 7, 19, "opabc123");
var completeAccountReport = PushDiagnostics.Report();
Expect(completeAccountReport.Contains("account=member19@example.invalid"), true,
    "detailed local PushMe log shows which exact account was processed");
Expect(completeAccountReport.Contains("item=7/19"), true,
    "account operation records its index within the batch");
Expect(completeAccountReport.Contains("group=0123456789abcdef"), true,
    "account operation records the stable group identifier");
Expect(completeAccountReport.Contains("op=opabc123"), true,
    "subscription START and OK share one operation identifier");
var sanitizedAccountReport = PushDiagnostics.Report(redactAccounts: true);
Expect(sanitizedAccountReport.Contains("member19@example.invalid"), false,
    "anonymized export never contains the account address");
Expect(sanitizedAccountReport.Contains("ACCOUNT_001"), true,
    "anonymized export preserves consistent pseudonym for events");
Expect(sanitizedAccountReport.Contains("item=7/19"), true,
    "anonymization retains batch index and outcome correlation");
Expect(sanitizedAccountReport.Contains("PRIVATE_SECRET"), false,
    "full and anonymized report never contains exception text or tokens");

Expect(PushDiagnostics.SafeServerReason(
    "Invalid access token for mail@example.invalid: abcdefghijklmnopqrstuvwxyz123456"),
    "INVALID_ACCESS_TOKEN",
    "SDK response message is reduced to safe server vocabulary");
Expect(PushDiagnostics.SafeServerReason(
    "Неверный токен у test@mail.ru"),
    "INVALID_TOKEN",
    "Russian server error message is mapped to safe diagnostic words");
Expect(PushDiagnostics.SafeServerReason(
    "Bearer VERYPRIVATECREDENTIAL12345678901234567"), "UNCLASSIFIED",
    "unknown server message never leaks bearer or token");
var serverCode = MailRuPushProbe.ParseSharedSubscriptionResponse(
    """{"error":{"code":499,"message":"Invalid access token for test@mail.ru"}}""",
    ["test@example.invalid"]);
Expect(serverCode.Error?.Contains("499", StringComparison.Ordinal), true,
    "original PushMe SDK nonzero error is recorded without claiming unknown code semantics");
var serverReport = PushDiagnostics.Report();
Expect(serverReport.Contains("SERVER_REASON_INVALID_ACCESS_TOKEN"), true,
    "server refusal reason stored as safe category only");
Expect(serverReport.Contains("test@mail.ru"), false,
    "PushMe SDK error message mailbox never reaches exported report");

PushDiagnostics.Clear();
Expect(PushDiagnostics.Report().Contains("MCS_LOGIN_OK"), false,
    "clearing push diagnostics removes persisted history");

Expect(MailRuEndpointCatalog.IsRuntimeHostAllowed("push-me.mail.ru"), true,
    "Original APK PushMe Prod host is registered for strict host policy");
Expect(MailRuPushProbe.ClassifyNetworkError(
    new HttpRequestException(HttpRequestError.SecureConnectionError, "SECRET_CERT_DETAIL")),
    "ошибка проверки сертификата TLS", "TLS failure classified without leaking details");
Expect(MailRuPushProbe.ClassifyNetworkError(
    new HttpRequestException(HttpRequestError.NameResolutionError, "SECRET_DNS_DETAIL")),
    "ошибка DNS: адрес сервера не найден", "DNS failure classified without leaking details");

// Source-based MCS validation: Chromium mcs.proto LoginResponse tag=3,
// required id=1, optional ErrorInfo=3 with required int32 code=1.
// The old implementation rejected even ErrorInfo.code=0.
var loginOk = PushWire.TextField(1, "non-sensitive-example-server-id");
var loginCodeZero = PushWire.Append(loginOk,
    PushWire.BytesField(3, PushWire.VarintField(1, 0)));
var loginCodeFailure = PushWire.Append(loginOk,
    PushWire.BytesField(3, PushWire.VarintField(1, 7)));
var loginErrorMissingCode = PushWire.Append(loginOk,
    PushWire.BytesField(3, Array.Empty<byte>()));
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, loginOk).Accepted, true,
    "MCS login succeeds with required id and absent ErrorInfo");
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, loginCodeZero).Accepted, true,
    "MCS login accepts ErrorInfo.code=0 as original Chromium implementation");
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, loginCodeFailure).Accepted, false,
    "MCS login rejects actual server ErrorInfo.code != 0");
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, loginCodeFailure).ErrorCode, 7,
    "MCS diagnostic extracts numeric error code only");
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, loginErrorMissingCode).Accepted, false,
    "Malformed Google MCS ErrorInfo without required code is rejected");
Expect(PushWire.ClassifyMcsLoginResponse(41, 3, PushWire.VarintField(5, 1)).Accepted,
    false, "MCS login requires field 1 server id");
Expect(PushWire.ClassifyMcsLoginResponse(41, 4, loginOk).Accepted, false,
    "MCS CLOSE tag does not count as successful LOGIN");
Expect(PushWire.ClassifyMcsLoginResponse(40, 3, loginOk).Accepted, false,
    "Unsupported MCS wire version is rejected");

// Constant receiving: duplicate protection, backoff and user opt-out are offline.
var msgData = PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushWire.BytesField(7, PushWire.Append(
        PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))),
    PushWire.BytesField(7, PushWire.Append(
        PushWire.TextField(1, "account"), PushWire.TextField(2, "test@example.invalid"))),
    PushWire.TextField(9, "test-persistent-id"));
Expect(PushWire.IsMailNewMessage(msgData), true, "continuous Mail.ru new-mail event");
var ack = PushWire.SelectiveAcknowledgment(msgData);
if (ack is null || !PushWire.Parse(ack).Any(field => field.Field == 7))
    throw new Exception("MCS selective acknowledgment extension absent");
Expect(PushWire.MessageIdentifier(msgData), PushWire.MessageIdentifier(msgData),
    "stable MCS persistent message identity");
using (var manager = new MailRuPushBackgroundService(
    (_, _) => { }, _ => { }))
{
    // Set state directly for offline tests: never open a real Google connection.
    var f = System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.NonPublic;
    var accountField = typeof(MailRuPushBackgroundService).GetField("_accounts", f)!;
    accountField.SetValue(manager, new Dictionary<string, string>(
        StringComparer.OrdinalIgnoreCase) { ["test@example.invalid"] = "FAKE_ACCESS" });
    typeof(MailRuPushBackgroundService).GetField("_enabled", f)!.SetValue(manager, true);
    Expect(manager.AcceptMessage("TEST@EXAMPLE.INVALID", msgData), true,
        "first delivery is accepted");
    Expect(manager.AcceptMessage("test@example.invalid", msgData), false,
        "same persistent message id is not processed twice");
    var distinctMessage = PushWire.Append(
        PushWire.TextField(5, "ru.mail.mailapp"),
        PushWire.BytesField(7, PushWire.Append(
            PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))),
        PushWire.BytesField(7, PushWire.Append(
            PushWire.TextField(1, "account"), PushWire.TextField(2, "test@example.invalid"))),
        PushWire.TextField(9, "another-persistent-id"));
    Expect(manager.AcceptMessage("test@example.invalid", distinctMessage), true,
        "two different letters must be handled even within two seconds");
    Expect(manager.AcceptMessage("test@example.invalid", distinctMessage), false,
        "repeated second letter is not handled twice");
    Expect(PushWire.NewMailAccount(msgData), "test@example.invalid",
        "shared Google receiver extracts mailbox from original message");
    Expect(manager.AcceptMessage("other@example.invalid", msgData), false,
        "shared receiver cannot route a letter to a different mailbox");
    var missingAccount = PushWire.Append(
        PushWire.TextField(5, "ru.mail.mailapp"),
        PushWire.BytesField(7, PushWire.Append(
            PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))));
    Expect(PushWire.NewMailAccount(missingAccount), null,
        "event without mailbox never targets active UI account");
    Expect(manager.AcceptMessage("test@example.invalid", missingAccount), false,
        "no mailbox means no delivery");
    var pending = SharedGooglePushIdentityStore.PendingAccountUnsubscriptions(
        ["old@example.invalid", "kept@example.invalid"], ["kept@example.invalid"]);
    Expect(pending.SequenceEqual(["old@example.invalid"]), true,
        "removing one account queues only its server-side unsubscribe");
    Expect(SharedGooglePushIdentityStore.PendingAccountUnsubscriptions(
        ["kept@example.invalid"], ["kept@example.invalid", "new@example.invalid"]).Length,
        0, "adding an account never rotates the common Google token");
    Expect(SharedGooglePushIdentityStore.PendingAccountUnsubscriptions(
        ["TEST@example.invalid"], ["test@example.invalid"]).Length,
        0, "case change does not unsubscribe a mailbox");
    var twoAccounts = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase)
    {
        ["a@example.invalid"] = "FAKE_A",
        ["b@example.invalid"] = "FAKE_B"
    };
    var withoutB = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase)
    {
        ["a@example.invalid"] = "FAKE_A"
    };
    Expect(MailRuPushBackgroundService.CanRemoveWithoutReconnect(twoAccounts, withoutB),
        true, "delete one account without stopping the Google MCS channel");
    Expect(MailRuPushBackgroundService.CanRemoveWithoutReconnect(twoAccounts,
        new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase)
        { ["a@example.invalid"] = "REFRESHED" }), false,
        "OAuth changes require subscription reconciliation");
    Expect(MailRuPushBackgroundService.CanRemoveWithoutReconnect(twoAccounts,
        new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase)), false,
        "last account removal uses separate address-unsubscribe path");

    Expect(MailRuPushProbe.UnsubscribeAccountUrl,
        "https://push-me.mail.ru/api/v1/unsubscribe_by_device_id",
        "unsubscribe URL exactly matches original APK PushMeApiImpl DEX");
    var originalCommonId = SharedGooglePushIdentityStore.ComposePushMeCommonId(
        "0123456789abcdef", "");
    Expect(originalCommonId,
        "0123456789abcdef:d41d8cd98f00b204e9800998ecf8427e",
        "source-based CommonId = Android ID colon MD5 of Android Build values");
    Expect(SharedGooglePushIdentityStore.IsValidPushMeCommonId(originalCommonId),
        true, "CommonId has official APK's persisted two-part form");
    var generatedCommonId = SharedGooglePushIdentityStore.GeneratePushMeCommonId();
    Expect(SharedGooglePushIdentityStore.IsValidPushMeCommonId(generatedCommonId),
        true, "Windows virtual Android ID remains private and APK-shaped");
    var unsubscribeFields = MailRuPushProbe.BuildAccountUnsubscribeFields(
        "TEST@EXAMPLE.INVALID", originalCommonId);
    Expect(unsubscribeFields.Count, 3,
        "original APK unsubscribe contains exactly three form fields");
    Expect(unsubscribeFields["account"], "test@example.invalid",
        "original APK unsubscribe lowercases account");
    Expect(unsubscribeFields["device_id"], originalCommonId,
        "original APK unsubscribe device_id matches settings.device_id, not Google MCS ID");
    Expect(unsubscribeFields["application"], "mail",
        "original APK unsubscribe uses mail application");
    accountField.SetValue(manager,
        new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase));
    Expect(manager.AcceptMessage("test@example.invalid", distinctMessage), false,
        "removed account cannot receive an event even before remote unsubscribe");
    // Restore neutral fake service state; tests must NEVER start network cleanup.
    typeof(MailRuPushBackgroundService).GetField("_enabled", f)!.SetValue(manager, false);
    var secretRoot = Path.Combine(Path.GetTempPath(), "MailRuGoogleReceiver-" +
        Guid.NewGuid().ToString("N"));
    try
    {
        var store = new SharedGooglePushIdentityStore(secretRoot);
        var original = new SharedGooglePushIdentityStore.State(
            101, 202, "FAKE_GOOGLE_TOKEN_FOR_OFFLINE_TEST", ["test@example.invalid"]);
        store.Save(original);
        Expect(store.Load()!.RegistrationToken, original.RegistrationToken,
            "shared Google identity survives app restart");
        Expect(store.Load()!.PushMeCommonId, null,
            "legacy DPAPI Google registration remains readable for safe CommonId migration");
        var migrated = original with { PushMeCommonId = generatedCommonId };
        store.Save(migrated);
        Expect(store.Load()!.PushMeCommonId, generatedCommonId,
            "independent PushMe CommonId persists across app restart without changing Google token");
        Expect(store.Load()!.RegistrationToken, original.RegistrationToken,
            "migrating PushMe device ID does not rotate the working Google token");
        var protectedContent = File.ReadAllText(
            Path.Combine(secretRoot, "google-push-receiver.dat"));
        Expect(protectedContent.Contains(original.RegistrationToken, StringComparison.Ordinal),
            false, "Google token never stored as plaintext");
        var secondaryStore = new SharedGooglePushIdentityStore(
            secretRoot, "abcdef012345");
        var alternate = new SharedGooglePushIdentityStore.State(
            303, 404, "FAKE_SECONDARY_GOOGLE_TOKEN", ["second@example.invalid"],
            SharedGooglePushIdentityStore.GeneratePushMeCommonId());
        secondaryStore.Save(alternate);
        Expect(SharedGooglePushIdentityStore.ListRecipientIds(secretRoot)
            .SequenceEqual(["primary", "abcdef012345"]), true,
            "both independent, protected Google slots are discoverable");
        Expect(secondaryStore.Load()!.DeviceId, (ulong)303,
            "second Google slot retains independent device identity");
        Expect(store.Load()!.DeviceId, (ulong)101,
            "creating another Google slot cannot change legacy primary identity");
        store.Delete();
        Expect(store.Load(), null, "server-confirmed unsubscribe removes protected identity");
        Expect(secondaryStore.Load()!.RegistrationToken, alternate.RegistrationToken,
            "removing one Google slot preserves every other sender token");
        secondaryStore.Delete();
        Expect(SharedGooglePushIdentityStore.ListRecipientIds(secretRoot).Length, 0,
            "all slots are released without leftover index records");
    }
    finally
    {
        if (Directory.Exists(secretRoot)) Directory.Delete(secretRoot, recursive: true);
    }
    var emptyRegistry = new PushGroupRegistryStore.Registry(false, []);
    var nineteen = Enumerable.Range(1, 19).Select(i =>
        "g" + i + "@example.invalid").ToArray();
    var registered = PushGroupRegistryStore.Register(
        emptyRegistry, nineteen, nineteen);
    Expect(registered.Groups.Length, 1,
        "19 confirmed accounts are persisted in one group, not 19 unrelated groups");
    Expect(registered.Groups[0].Accounts.Length, 19,
        "confirmed PushMe group retains all exact selected accounts");
    var withSecond = PushGroupRegistryStore.Register(
        registered, ["g20@example.invalid"], ["g20@example.invalid"]);
    Expect(withSecond.Groups.Length, 2,
        "next batch adds a group without rewriting the previous accepted group");
    Expect(withSecond.Groups[0].State, "RECHECK_REQUIRED",
        "a second PushMe batch cannot be assumed to preserve first group's delivery");
    var independent = PushGroupRegistryStore.Register(
        registered, ["g20@example.invalid"], ["g20@example.invalid"],
        recipientId: "abcdef012345");
    Expect(independent.Groups[0].State, "CONFIRMED",
        "another Google recipient cannot mark the primary group replaced");
    Expect(independent.Groups[1].RecipientId, "abcdef012345",
        "PushMe group retains its assigned independent Google recipient");
    var onlySecondaryEnabled = PushGroupRegistryStore.SetGroupReceiving(
        independent with { ReceiveEnabled = false }, independent.Groups[1].Id, true);
    Expect(onlySecondaryEnabled.Groups[0].ReceiveEnabled, false,
        "starting group B never activates group A");
    Expect(onlySecondaryEnabled.Groups[1].ReceiveEnabled, true,
        "group B receives independently");
    var allStopped = PushGroupRegistryStore.SetAllReceiving(
        onlySecondaryEnabled, false);
    Expect(allStopped.Groups.All(g => g.ReceiveEnabled == false), true,
        "global Stop closes all readers without deleting Google/group records");
    var afterObservedMail = PushGroupRegistryStore.MarkObservedMail(
        withSecond, "G1@example.invalid");
    Expect(afterObservedMail.Groups[0].State, "EVENT_SEEN",
        "real new-mail event confirms that one mailbox in older group still delivers");
    Expect(afterObservedMail.Groups[1].State, "CONFIRMED",
        "event for first group cannot certify delivery of other groups");
    Expect(PushGroupRegistryStore.Ungrouped(
        Enumerable.Range(1, 21).Select(i => "g" + i + "@example.invalid"),
        withSecond).SequenceEqual(["g21@example.invalid"]), true,
        "account registry maps each mailbox to one group and exposes ungrouped");
    var removedOne = PushGroupRegistryStore.RemoveAccounts(
        withSecond, ["g1@example.invalid"]);
    Expect(removedOne.Groups[0].Accounts.Length, 18, 
        "removing a mailbox affects only its own registered group");
    Expect(removedOne.Groups[1].Accounts.Length, 1,
        "other group stays intact after address-specific removal");
    var duplicateRejected = false;
    try { PushGroupRegistryStore.Register(
        registered, ["G1@example.invalid"], ["G1@example.invalid"]); }
    catch (InvalidOperationException) { duplicateRejected = true; }
    Expect(duplicateRejected, true,
        "existing group member cannot be registered in another group");
    // The new stop operation is cancellation ONLY. Its pure state change
    // must not start unsubscribe jobs or clear the saved Google credentials.
    manager.StopListeningOnly();
    Expect(manager.Enabled, false,
        "MCS-only Stop changes reader state without invoking global token revoke");
    var groupRoot = Path.Combine(Path.GetTempPath(), "MailRuPushGroups-" +
        Guid.NewGuid().ToString("N"));
    try
    {
        var groupStore = new PushGroupRegistryStore(groupRoot);
        groupStore.Save(withSecond with { ReceiveEnabled = true });
        Expect(groupStore.Load().Groups.Length, 2,
            "confirmed group mapping survives app restart under DPAPI");
        Expect(groupStore.Load().ReceiveEnabled, true,
            "desired MCS reconnect state persists independently of group memberships");
        var protectedGroups = File.ReadAllText(Path.Combine(groupRoot, "push-groups.dat"));
        Expect(protectedGroups.Contains("g1@example.invalid", StringComparison.Ordinal),
            false, "group addresses never stored in plaintext");
    }
    finally { if (Directory.Exists(groupRoot)) Directory.Delete(groupRoot, true); }
    Expect(manager.ActiveAccountCount, 0, "manager has no unsolicited account connections");
    manager.Reconcile(Array.Empty<(string Login, string Token)>(), enabled: false);
    Expect(manager.Enabled, false, "user opt-out cancels the receiving service");
    Expect(MailRuPushBackgroundService.RetryDelay(5),
        TimeSpan.FromMinutes(10), "reconnect has bounded ten-minute backoff");
}
// Static contract reconstructed from official APK PushMeApiImpl:
var sharedSuccess = MailRuPushProbe.ParseSharedSubscriptionResponse(
    """{"error":{"code":0}}""", ["a@example.invalid", "b@example.invalid"]);
Expect(sharedSuccess.Error, null, "SDK accepts error=0 when validate_result is omitted");
Expect(sharedSuccess.Accepted.Count, 2, "SDK accepts all accounts when validation is omitted");
var sharedPartial = MailRuPushProbe.ParseSharedSubscriptionResponse(
    """{"error":{"code":0},"validate_result":[{"account":"b@example.invalid","is_valid":false}]}""",
    ["a@example.invalid", "b@example.invalid"]);
Expect(sharedPartial.Error, null, "SDK partial validation is readable");
Expect(sharedPartial.Accepted.SetEquals(["a@example.invalid"]), true,
    "SDK rejects only explicitly invalid account");
var sharedFailed = MailRuPushProbe.ParseSharedSubscriptionResponse(
    """{"error":{"code":403}}""", ["a@example.invalid"]);
Expect(sharedFailed.Error is not null, true, "SDK error.code nonzero rejects request");

var pushSettingsDirectory = System.IO.Path.Combine(
    System.IO.Path.GetTempPath(), "MailRuPushSettings_" + Guid.NewGuid().ToString("N"));
try
{
    var storedSettings = new AppSettingsStore(pushSettingsDirectory);
    Expect(storedSettings.LoadBackgroundPushEnabled(), true,
        "first start enables background push after feature approval");
    storedSettings.SaveBackgroundPushEnabled(false);
    Expect(new AppSettingsStore(pushSettingsDirectory).LoadBackgroundPushEnabled(),
        false, "background push opt-out persists");
    storedSettings.SaveBackgroundPushEnabled(true);
    Expect(new AppSettingsStore(pushSettingsDirectory).LoadBackgroundPushEnabled(),
        true, "background push can be enabled again");
    Expect(storedSettings.LoadTaskbarNotificationsEnabled(), true,
        "Windows popup notifications enabled by default");
    storedSettings.SaveTaskbarNotificationsEnabled(false);
    Expect(new AppSettingsStore(pushSettingsDirectory).LoadTaskbarNotificationsEnabled(),
        false, "disabling Windows popups persists independently");
    Expect(storedSettings.LoadBackgroundPushEnabled(), true,
        "turning off popups does not stop mail delivery");
}
finally
{
    if (System.IO.Directory.Exists(pushSettingsDirectory))
        System.IO.Directory.Delete(pushSettingsDirectory, recursive: true);
}

using (var folder = new System.IO.MemoryStream())
{
    var copy = PushWire.Varint(300);
    await folder.WriteAsync(copy);
    if (folder.Length < 2) throw new Exception("protobuf multi-byte varint not encoded");
}

// Offline regression against the EXACT v0.3.35 single-mailbox request profile
// that previously obtained ACCOUNT_ACCEPTED and a real new-mail event.
const string historicalTrialId = "mailru-windows-abcdef0123456789abcdef01";
var historicalSingle = MailRuPushProbe.BuildVerifiedSingleAccountSubscription(
    "SINGLE@EXAMPLE.INVALID", "FAKE_OAUTH", "FAKE_GOOGLE_TOKEN",
    123456789UL, historicalTrialId);
using (var oneRequest = System.Text.Json.JsonDocument.Parse(
    System.Text.Json.JsonSerializer.Serialize(new[] { historicalSingle })))
{
    Expect(oneRequest.RootElement.GetArrayLength(), 1,
        "manual verification sends exactly one selected account");
    var message = oneRequest.RootElement[0];
    Expect(message.GetProperty("account").GetString(), "single@example.invalid",
        "single-account test selects only the explicitly requested mailbox");
    Expect(message.GetProperty("token").GetString(), "FAKE_GOOGLE_TOKEN",
        "single-account test binds its own temporary Google token");
    Expect(message.GetProperty("android_id").GetString(), "123456789",
        "v0.3.35 verified single-mailbox wire format uses decimal Google device ID");
    Expect(message.GetProperty("sdk_device_id").GetString(), historicalTrialId,
        "v0.3.35 trial device identifier is not silently changed to CommonId");
    Expect(message.GetProperty("settings").GetProperty("device_id").GetString(),
        historicalTrialId,
        "v0.3.35 trial subscription device ID matches SDK device ID");
    Expect(message.GetProperty("application").GetString(), "mail",
        "manual test preserves original Mail.ru subscription application");
}

// Original Android-app push protocol: all tests are offline and use fake tokens.
var pushRequest = MailRuPushProbe.BuildSubscription(
    "TEST@EXAMPLE.INVALID", "NOT_A_REAL_OAUTH", "NOT_A_REAL_GOOGLE_TOKEN",
    "0123456789abcdef",
    SharedGooglePushIdentityStore.ComposePushMeCommonId("0123456789abcdef", "fake Android Build"));
using (var pushJson = System.Text.Json.JsonDocument.Parse(
    System.Text.Json.JsonSerializer.Serialize(new[] { pushRequest })))
{
    var samplePush = pushJson.RootElement[0];
    Expect(samplePush.GetProperty("account").GetString(), "test@example.invalid",
        "PushMe account normalized to lower case");
    Expect(samplePush.GetProperty("application").GetString(), "mail", "PushMe application");
    Expect(samplePush.GetProperty("platform").GetString(), "android", "PushMe original FCM wire platform");
    Expect(samplePush.GetProperty("android_id").GetString(), "0123456789abcdef",
        "original APK Android secure ID uses stable 16-hex form, not decimal Google device ID");
    var sampleCommonId = samplePush.GetProperty("sdk_device_id").GetString();
    Expect(SharedGooglePushIdentityStore.IsValidPushMeCommonId(sampleCommonId),
        true, "subscription SDK device id uses original CommonId form");
    Expect(samplePush.GetProperty("settings").GetProperty("device_id").GetString(),
        sampleCommonId, "settings.device_id matches SDK identifier");
    Expect(sampleCommonId!.StartsWith("0123456789abcdef:", StringComparison.Ordinal),
        true, "CommonId contains same Android ID as request");
    Expect(samplePush.GetProperty("status").GetInt32(), 0, "PushMe enabled status");
    Expect(samplePush.GetProperty("settings").GetProperty("capabilities")
        .GetProperty("can_mail").GetProperty("Filter").GetProperty("Folder")
        .GetProperty("enabled").GetBoolean(), false, "PushMe all folders not excluded");
    Expect(samplePush.GetProperty("settings").GetProperty("client")
        .GetProperty("name").GetString(), "ru.mail.mailapp", "PushMe original package name");
    if (!samplePush.GetProperty("settings").GetProperty("client_time_zone")
            .GetString()!.StartsWith("GMT", StringComparison.Ordinal))
        throw new Exception("PushMe local timezone does not use original SDK GMT prefix");
}
Expect(MailRuPushProbe.ClassifySubscription(
    """{"error":{"code":0},"validate_result":[{"account":"test@example.invalid","is_valid":true}]}""",
    "test@example.invalid"), "ACCOUNT_ACCEPTED", "PushMe validated matching account");
Expect(MailRuPushProbe.ClassifySubscription(
    """{"error":{"code":0}}""", "test@example.invalid"),
    "Сервер не подтвердил выбранный аккаунт",
    "PushMe HTTP OK without account confirmation must not be accepted");
Expect(PushWire.GetUnsigned(PushWire.VarintField(7, 123456789), 7),
    123456789UL, "protobuf integer round-trip");
var source = PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushWire.BytesField(7, PushWire.Append(PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))));
Expect(PushWire.IsMailNewMessage(source), true, "Mail.ru event=4 matches original package");
Expect(PushWire.IsMailNewMessage(PushWire.Append(
    PushWire.TextField(5, "unrelated.package"),
    PushWire.BytesField(7, PushWire.Append(PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))))),
    false, "ignore other package event=4");


double?[] centers = [20, 60, 100, 140];
Expect(AccountDragMath.FindTarget(1, 60, centers), 1, "stationary hold");
Expect(AccountDragMath.FindTarget(1, 90, centers), 1, "down before next midpoint");
Expect(AccountDragMath.FindTarget(1, 101, centers), 2, "down crosses first neighbor");
Expect(AccountDragMath.FindTarget(1, 150, centers), 3, "down crosses all neighbors");
Expect(AccountDragMath.FindTarget(3, 40, centers), 1, "up crosses neighbors");
Expect(AccountDragMath.FindTarget(3, 1, centers), 0, "up to first");
Expect(AccountDragMath.FindTarget(0, 180, centers), 3, "first to last");
Expect(AccountDragMath.FindTarget(-1, 20, centers), -1, "invalid source index");
Expect(AccountDragMath.FindTarget(1, 140, [20, 60, null, 140]), 3,
    "virtualized row does not prevent other targets");

var blankName = new MailRuMessageSummary("1", "", "", "", "contact@mail.ru", null, null, 0, false, false, false);
Expect(blankName.SenderDisplay, "contact@mail.ru", "missing name displays email");
var spaces = blankName with { SenderName = "  " };
Expect(spaces.SenderDisplay, "contact@mail.ru", "whitespace name displays email");
var named = blankName with { SenderName = "  Татьяна  " };
Expect(named.SenderDisplay, "Татьяна", "present name displays trimmed name");

var sample = """
{"body":{"folders_content":[{"id":0,"threads":[
{"id":"m1","base_message":{"id":"m1","subject":"Test","correspondents":{"from":{"email":"single@mail.ru"}}}},
{"id":"m2","base_message":{"id":"m2","subject":"Test2","correspondents":{"from":[{"email":"array@mail.ru"}]}}}
]}]}}
""";
var parsed = MailRuThreadStatusParser.Parse(sample, 0);
Expect(parsed.Messages.Count, 2, "two sender shapes parsed");
Expect(parsed.Messages.Single(x => x.Id == "m1").SenderDisplay, "single@mail.ru", "single object from");
Expect(parsed.Messages.Single(x => x.Id == "m2").SenderDisplay, "array@mail.ru", "array from");

var threadSender = """
{"body":{"folders_content":[{"id":0,"threads":[{"id":"m3",
"correspondents":{"from":[{"email":"thread@mail.ru"}]},
"base_message":{"id":"m3","subject":"Thread-only sender"}}]}]}}
""";
var threadSnapshot = MailRuThreadStatusParser.Parse(threadSender, 0);
Expect(threadSnapshot.Messages.Single().SenderDisplay, "thread@mail.ru",
    "sender inherited from thread when base_message omits correspondent");

var senderInRepresentation = """
{"body":{"folders_content":[{"id":0,"threads":[{"id":"thread5",
"base_message":{"id":"m5","date":1700000000},
"representations":[
{"id":"other","correspondents":{"from":[{"email":"not-the-sender@mail.ru"}]}},
{"id":"m5","correspondents":{"from":[{"name":"Тестовый отправитель","email":"sender@mail.ru"}]}}
]}]}]}}
""";
var parsedRepresentation = MailRuThreadStatusParser.Parse(senderInRepresentation, 0);
Expect(parsedRepresentation.Messages.Single().Subject, "(без темы)",
    "missing subject does not prevent parsing initial sender");
Expect(parsedRepresentation.Messages.Single().SenderDisplay, "Тестовый отправитель",
    "initial list sender inherited only from same-message representation");
Expect(parsedRepresentation.Messages.Single().SenderEmail, "sender@mail.ru",
    "missing-subject sender email resolved without full-message request");



using (var handler = new RecordingHandler())
using (var http = new HttpClient(handler))
using (var client = new MailRuClient(httpClient: http))
{
    await client.SendMessageAsync("test-token", new MailRuOutgoingMessage(
        To: "recipient@mail.ru", Subject: "test", Text: "body", RequestReadReceipt: true));
    Expect(handler.LastPath, "/api/v1/messages/send", "immediate send route");
    if (!handler.LastForm.Contains("receipt=true", StringComparison.Ordinal))
        throw new Exception("Read-receipt checkbox did not transmit true");
    Console.WriteLine("PASS receipt=true in immediate send request");

    await client.SendMessageAsync("test-token", new MailRuOutgoingMessage(
        To: "recipient@mail.ru", Subject: "test", Text: "body",
        SendDate: "1791507600", RequestReadReceipt: true));
    Expect(handler.LastPath, "/api/v1/messages/schedule", "scheduled send route");
    if (!handler.LastForm.Contains("receipt=true", StringComparison.Ordinal) ||
        !handler.LastForm.Contains("send_date=1791507600", StringComparison.Ordinal))
        throw new Exception("Scheduled send missing date or read receipt");
    Console.WriteLine("PASS scheduled date and read receipt");

    await client.SendMessageAsync("test-token", new MailRuOutgoingMessage(
        To: "recipient@mail.ru", Subject: "test", Text: "body"));
    if (handler.LastForm.Contains("receipt=", StringComparison.Ordinal))
        throw new Exception("Unchecked receipt should leave wire protocol unchanged");
    Console.WriteLine("PASS unchecked receipt omits flag");
}

var exampleImage = new Uri(
    "https://af12.mail.ru/cgi-bin/readmsg?id=17913042710476616061;0;2&mode=attachment&email=7770082@bk.ru&ct=image%2fgif&cn=&cte=binary");
Expect(MailRuInlineImageSource.TryParse(exampleImage, "17913042710476616061",
    "7770082@bk.ru", out var inlineId), true,
    "Mail.ru embedded-image URL is accepted for matching account and mail");
Expect(inlineId, "0;2", "embedded-image attachment identifier is preserved");
Expect(MailRuInlineImageSource.TryParse(exampleImage, "wrong-mail-id",
    "7770082@bk.ru", out _), false, "other message image is rejected");
Expect(MailRuInlineImageSource.TryParse(exampleImage, "17913042710476616061",
    "other@mail.ru", out _), false, "other account image is rejected");
Expect(MailRuInlineImageSource.TryParse(
    new Uri("https://evil.example/cgi-bin/readmsg?id=17913042710476616061;0;2&mode=attachment&email=7770082@bk.ru"),
    "17913042710476616061", "7770082@bk.ru", out _), false,
    "untrusted image host is rejected");
Expect(MailRuInlineImageSource.TryParse(
    new Uri("https://af12.mail.ru/cgi-bin/readmsg?id=17913042710476616061;0;2&mode=mail&email=7770082@bk.ru"),
    "17913042710476616061", "7770082@bk.ru", out _), false,
    "only image attachment mode is allowed");

var testRoot = Path.Combine(Path.GetTempPath(), "MailRuDesktopTemplateChecks-" + Guid.NewGuid().ToString("N"));
try
{
    var first = Path.Combine(testRoot, "first");
    var next = Path.Combine(testRoot, "next");
    Directory.CreateDirectory(first);
    File.WriteAllText(Path.Combine(first, "Manual.md"), "Здравствуйте!\n");
    var originalAttach = Path.Combine(testRoot, "price.txt");
    File.WriteAllText(originalAttach, "Тестовое вложение");
    var templates = new MarkdownTemplateStore(first);
    Expect(templates.LoadAll().Single().Name, "Manual", "manual Markdown template is discovered");

    templates.Save(new SavedMailTemplate
    {
        Name = "Reply",
        Subject = "Re: test",
        Body = "Reply text",
        Attachments = [originalAttach]
    }, previousName: null);
    var saved = templates.LoadAll().Single(t => t.Name == "Reply");
    Expect(saved.Subject, "Re: test", "template subject survives disk roundtrip");
    templates.Save(new SavedMailTemplate
    {
        Name = "Русское письмо",
        Subject = "Тема письма — проверка",
        Body = "Здравствуйте! Текст сообщения.",
        Attachments = []
    }, previousName: null);
    var readable = File.ReadAllText(Path.Combine(first, "Русское письмо.md"));
    if (!readable.Contains("Тема письма — проверка", StringComparison.Ordinal) ||
        readable.Contains("\\u0422", StringComparison.OrdinalIgnoreCase))
        throw new Exception("Russian template metadata must be directly readable in Markdown.");
    Expect(templates.LoadAll().Single(t => t.Name == "Русское письмо").Subject,
        "Тема письма — проверка", "Cyrillic subject roundtrip");

    var escapedPath = Path.Combine(first, "Escaped.md");
    File.WriteAllText(escapedPath,
        "---\nsubject: \"\\u0422\\u0435\\u043C\\u0430\"\nattachments:\n---\nТело письма");
    var legacy = templates.LoadAll().Single(item => item.Name == "Escaped");
    Expect(legacy.Subject, "Тема", "legacy escaped subject decoded");
    var rewritten = File.ReadAllText(escapedPath);
    if (!rewritten.Contains("Тема", StringComparison.Ordinal) ||
        rewritten.Contains("\\u0422", StringComparison.OrdinalIgnoreCase))
        throw new Exception("Legacy template metadata remains escaped.");
    if (!File.Exists(escapedPath + ".escaped-metadata.bak"))
        throw new Exception("Escaped source file backup missing");
    Console.WriteLine("PASS legacy escaped metadata rewritten with original backup");


    Expect(saved.Body, "Reply text", "template body survives disk roundtrip");
    if (saved.Attachments.Count != 1 || !File.Exists(saved.Attachments[0]))
        throw new Exception("Template attachment was not copied into managed folder");
    Console.WriteLine("PASS managed template attachment copied");

    // Copying a loaded template to another name must NOT rename or delete
    // the original. Only an explicitly confirmed exact-name overwrite is allowed.
    var originalReplyPath = Path.Combine(first, "Reply.md");
    var originalReplyBytes = File.ReadAllBytes(originalReplyPath);
    var clone = templates.Save(new SavedMailTemplate
    {
        Name = "Reply copy",
        Subject = "Новая тема",
        Body = "Новый текст",
        Attachments = saved.Attachments
    }, previousName: null);
    Expect(File.Exists(Path.Combine(first, "Reply copy.md")), true,
        "new template name creates an independent Markdown file");
    Expect(File.ReadAllBytes(originalReplyPath).SequenceEqual(originalReplyBytes), true,
        "source template stays byte-for-byte unchanged after Save As");
    Expect(clone.Attachments.Single() == saved.Attachments.Single(), false,
        "copy owns independent attachments instead of source template files");
    Expect(File.Exists(clone.Attachments.Single()), true,
        "copy attachment was written to independent location");

    bool collisionRejected = false;
    try
    {
        templates.Save(new SavedMailTemplate
        {
            Name = "Reply copy", Subject = "Нельзя перезаписывать",
            Body = "Данные", Attachments = []
        }, previousName: null);
    }
    catch (IOException) { collisionRejected = true; }
    Expect(collisionRejected, true, "template overwrite needs explicit permission");
    bool renameRejected = false;
    try
    {
        templates.Save(new SavedMailTemplate
        {
            Name = "Reply copy 2", Body = "Данные", Attachments = []
        }, previousName: "Reply");
    }
    catch (IOException) { renameRejected = true; }
    Expect(renameRejected, true, "Save cannot delete or rename a different template");
    templates.Save(new SavedMailTemplate
    {
        Name = "Reply copy", Subject = "Разрешённая перезапись",
        Body = "Изменено", Attachments = []
    }, previousName: "Reply copy");
    Expect(templates.LoadAll().Single(t => t.Name == "Reply copy").Subject,
        "Разрешённая перезапись", "exact-target authorized overwrite is allowed");
    Expect(File.ReadAllBytes(originalReplyPath).SequenceEqual(originalReplyBytes), true,
        "original still unchanged after copy overwrite");

    templates.ChangeDirectory(next);
    Expect(templates.DirectoryPath, Path.GetFullPath(next), "template folder is configurable");
    Expect(templates.LoadAll().Count, 5, "template files preserved on directory change");
    var moved = templates.LoadAll().Single(t => t.Name == "Reply");
    if (!File.Exists(moved.Attachments.Single()))
        throw new Exception("Attachment missing after template folder change");
    Console.WriteLine("PASS referenced attachments preserved after changing folder");
    if (!File.Exists(Path.Combine(first, "Reply.md")))
        throw new Exception("Original template folder was deleted");

    File.WriteAllText(Path.Combine(next, "AddedManually.md"), "New text");
    Expect(templates.LoadAll().Count, 6, "manually added file appears without restart");

    var conflicting = Path.Combine(testRoot, "conflicting");
    Directory.CreateDirectory(conflicting);
    File.WriteAllText(Path.Combine(conflicting, "Reply.md"), "Destination already exists");
    var rejectedConflict = false;
    try { templates.ChangeDirectory(conflicting); }
    catch (IOException) { rejectedConflict = true; }
    Expect(rejectedConflict, true, "conflicting folder switch is rejected safely");
    Expect(templates.DirectoryPath, Path.GetFullPath(next), "conflict keeps prior template directory");
}
finally
{
    if (Directory.Exists(testRoot))
        Directory.Delete(testRoot, recursive: true);
}

var roles = ThemePalette.Roles;
Expect(roles.Count, 26, "exactly 26 fixed color roles");
Expect(roles.Select(r => r.Key).Distinct(StringComparer.Ordinal).Count(), 26,
    "every role has a unique stable identifier");
foreach (var dark in new[] { false, true })
{
    var defaults = ThemePalette.Defaults(dark);
    Expect(defaults.Count, 26, "theme defaults cover every role");
    foreach (var (_, hex) in defaults)
        if (!ThemePalette.TryNormalize(hex, out var normalized) || normalized != hex)
            throw new Exception("Invalid default HEX color " + hex);
}
Expect(ThemePalette.TryNormalize("#ffcc00", out var normalizedYellow), true,
    "hex input accepted");
Expect(normalizedYellow, "#FFCC00", "hex color normalized");
Expect(ThemePalette.TryNormalize("#12345Z", out _), false,
    "invalid hex color rejected");
Expect(ThemePalette.TryNormalize("#11223344", out _), false,
    "transparency not accepted in semantic palette");

var changedLight = ThemePalette.Merge(false,
    new Dictionary<string, string>
    {
        ["AppWindowBrush"] = "#123456",
        ["ArbitraryOtherElement"] = "#ABCDEF",
        ["AppTextBrush"] = "not a color"
    });
Expect(changedLight.Count, 26, "unknown roles not introduced by saved overrides");
Expect(changedLight["AppWindowBrush"], "#123456", "valid override applied");
Expect(changedLight["AppTextBrush"], ThemePalette.Defaults(false)["AppTextBrush"],
    "invalid override ignored");
Expect(ThemePalette.Contrast("#FFFFFF", "#000000") > 20, true,
    "contrast calculation supports high contrast text");
Expect(ThemePalette.Contrast("#777777", "#777777"), 1.0,
    "identical colors contrast 1:1");

var isolatedPaletteFolder = Path.Combine(Path.GetTempPath(),
    "MailRuPalette-" + Guid.NewGuid().ToString("N"));
try
{
    var settings = new AppSettingsStore(isolatedPaletteFolder);
    Expect(settings.LoadPaletteOverrides(true)["AppWindowBrush"],
        ThemePalette.Defaults(true)["AppWindowBrush"], "new dark palette uses defaults");
    settings.SavePaletteOverride(true, "AppWindowBrush", "#123456");
    settings.SavePaletteOverride(false, "AppWindowBrush", "#ABCDEF");
    var reloaded = new AppSettingsStore(isolatedPaletteFolder);
    Expect(reloaded.LoadPaletteOverrides(true)["AppWindowBrush"], "#123456",
        "dark palette saved and reloaded");
    Expect(reloaded.LoadPaletteOverrides(false)["AppWindowBrush"], "#ABCDEF",
        "light palette saved independently and reloaded");
    reloaded.ResetPaletteOverride(true, "AppWindowBrush");
    Expect(reloaded.LoadPaletteOverrides(true)["AppWindowBrush"],
        ThemePalette.Defaults(true)["AppWindowBrush"],
        "one dark role reset without affecting light palette");
    Expect(reloaded.LoadPaletteOverrides(false)["AppWindowBrush"], "#ABCDEF",
        "light palette is unaffected by dark reset");
    reloaded.ResetPaletteOverride(false);
    Expect(reloaded.LoadPaletteOverrides(false)["AppWindowBrush"],
        ThemePalette.Defaults(false)["AppWindowBrush"], "whole light theme reset");
}
finally
{
    if (Directory.Exists(isolatedPaletteFolder))
        Directory.Delete(isolatedPaletteFolder, recursive: true);
}

using (var handler = new RecordingHandler())
using (var http = new HttpClient(handler))
using (var client = new MailRuClient(httpClient: http))
{
    var batch = new[]
    {
        blankName with { Id = "read1", FolderId = 0, Unread = true },
        blankName with { Id = "read2", FolderId = 0, Unread = true },
        blankName with { Id = "read3", FolderId = 500010, Unread = true }
    };
    var result = await client.MarkMessagesReadBatchAsync("test-token", "test@mail.ru", batch, 0);
    Expect(result.Success, true, "grouped unread marks accepted by fake server");
    Expect(handler.LastPath, "/api/v1/messages/marks", "grouped read marks route");
    var decoded = Uri.UnescapeDataString(handler.LastForm.Replace('+', ' '));
    if (!decoded.Contains("read1", StringComparison.Ordinal) ||
        !decoded.Contains("read3", StringComparison.Ordinal) ||
        !decoded.Contains("500010", StringComparison.Ordinal))
        throw new Exception("Folder-specific grouped marks missing ids or folder");
    Console.WriteLine("PASS batch read marks grouped by folder");
}


foreach (var (httpCode, payload, label) in new[]
{
    (HttpStatusCode.OK, "{\"status\":403,\"email\":\"\",\"htmlencoded\":true,\"body\":\"token\"}", "JSON 403 inside HTTP 200"),
    (HttpStatusCode.Forbidden, "{\"status\":403,\"body\":\"token\"}", "HTTP 403 authorization rejection"),
    (HttpStatusCode.OK, "{\"status\":\"401\",\"body\":\"token\"}", "string status 401 inside HTTP 200")
})
{
    using var handler = new TokenEnvelopeHandler(httpCode, payload);
    using var http = new HttpClient(handler);
    using var client = new MailRuClient(httpClient: http);
    var denied = false;
    try { await client.GetFolderThreadsAsync("old-token", 0); }
    catch (MailRuAuthorizationException) { denied = true; }
    Expect(denied, true, label + " triggers token recovery instead of empty mailbox");
}

using (var handler = new TokenEnvelopeHandler(HttpStatusCode.OK,
    "{\"status\":200,\"body\":{\"folders_content\":[]}}"))
using (var http = new HttpClient(handler))
using (var client = new MailRuClient(httpClient: http))
{
    var result = await client.GetFolderThreadsAsync("valid-token", 0);
    Expect(result.Contains("\"status\":200", StringComparison.Ordinal), true,
        "successful folder response remains unchanged");
}

var isolatedAuthFolder = Path.Combine(Path.GetTempPath(),
    "MailRuAuth-" + Guid.NewGuid().ToString("N"));
try
{
    var auth = new AuthorizationStore(isolatedAuthFolder);
    auth.Save("first@example.com", "old-first-token", "first-refresh",
        null, null, null, null);
    auth.Save("second@example.com", "second-token", "second-refresh",
        null, null, null, null);
    Expect(auth.LastLogin, "second@example.com", "initial last login account");

    Expect(auth.UpdateTokens("first@example.com", "new-first-token", "new-first-refresh"),
        true, "update first account tokens only");
    var reloaded = new AuthorizationStore(isolatedAuthFolder);
    Expect(reloaded.LastLogin, "second@example.com", "token renewal does not switch last used account");
    Expect(reloaded.TryRestore("first@example.com", out var first), true,
        "first account credentials survived refresh");
    Expect(first!.AccessToken, "new-first-token", "first account new access token");
    Expect(first.RefreshToken, "new-first-refresh", "first account rotated refresh token");
    Expect(reloaded.TryRestore("second@example.com", out var second), true,
        "second account credentials still present");
    Expect(second!.AccessToken, "second-token", "other mailbox token stays unchanged");
    Expect(second.RefreshToken, "second-refresh", "other mailbox refresh token stays unchanged");
    Expect(reloaded.UpdateTokens("missing@example.com", "must-not-add", null), false,
        "refresh cannot create an unrelated account");
}
finally
{
    if (Directory.Exists(isolatedAuthFolder))
        Directory.Delete(isolatedAuthFolder, recursive: true);
}

Expect(string.Join(",", MailTargetResolver.Resolve(["A", "B"], "C")),
    "A,B", "checked letters have priority over active preview");
Expect(string.Join(",", MailTargetResolver.Resolve(Array.Empty<string>(), "C")),
    "C", "active preview is fallback when no checkbox is checked");
Expect(MailTargetResolver.Resolve(Array.Empty<string>(), null).Length,
    0, "no checked or active letter produces no targets");
Expect(string.Join(",", MailTargetResolver.Resolve(["A", "A"], "C")),
    "A", "duplicate checked ids are never sent twice");

var fontRoles = ThemeTypography.Resolve(ThemeTypography.DefaultSize);
Expect(fontRoles.Count, 9, "nine fixed typography roles");
Expect(ThemeTypography.AvatarSize(12), 16.0, "default sender icon follows text size");
Expect(ThemeTypography.AvatarSize(18), 24.0, "sender icon grows with interface text");
Expect(ThemeTypography.AvatarSize(10), 14.0, "sender icon minimum is readable");
Expect(ThemePalette.EditableRoles.Count, 20, "similar color roles have merged settings");
var mergedColors = ThemePalette.Merge(false,
    new Dictionary<string, string> { ["AppPanelBrush"] = "#ABCDEF" });
Expect(mergedColors["AppWindowBrush"], "#ABCDEF",
    "legacy panel override is migrated to shared background color");
Expect(mergedColors["AppDialogBrush"], "#ABCDEF",
    "all merged dialog backgrounds follow the shared color");
var explicitColors = ThemePalette.Merge(false,
    new Dictionary<string, string>
    {
        ["AppWindowBrush"] = "#135724",
        ["AppPanelBrush"] = "#ABCDEF"
    });
Expect(explicitColors["AppPanelBrush"], "#135724",
    "explicit canonical background takes priority over older per-panel value");
var multipleLegacy = ThemePalette.Merge(false, new Dictionary<string,string>
{
    ["AppPanelBrush"] = "#ABCDEF",
    ["AppDialogBrush"] = "#557799"
});
Expect(multipleLegacy["AppWindowBrush"], "#ABCDEF",
    "old panel color wins deterministically over another collapsed legacy value");
Expect(multipleLegacy["AppDialogBrush"], "#ABCDEF",
    "merged group uses one color across all former roles");
Expect(fontRoles["AppFontBodySize"], 12.0, "default font size is 12");
Expect(ThemeTypography.Normalize(1), 10, "font size minimum is enforced");
Expect(ThemeTypography.Normalize(99), 18, "font size maximum is enforced");
Expect(ThemeTypography.Resolve(16)["AppFontHeadingSize"], 24.0,
    "headings scale together with body text");

var isolatedFontSettings = Path.Combine(Path.GetTempPath(),
    "MailRuFont-" + Guid.NewGuid().ToString("N"));
try
{
    var store = new AppSettingsStore(isolatedFontSettings);
    Expect(store.LoadInterfaceFontSize(), 12, "default persisted font size");
    store.SaveInterfaceFontSize(16);
    Expect(new AppSettingsStore(isolatedFontSettings).LoadInterfaceFontSize(),
        16, "font size survives app restart");
}
finally
{
    if (Directory.Exists(isolatedFontSettings))
        Directory.Delete(isolatedFontSettings, recursive: true);
}

var counts = MailRuExplicitFolderCounts.Read("""
    {"status":200,"body":{"folders":[
      {"id":0,"messages_unread":5},
      {"id":500010,"name":"Архив"},
      {"id":500002,"messages_unread":0},
      {"id":42,"messages_unread":-2}
    ]}}
    """);
Expect(counts.Count, 2, "only explicitly transmitted nonnegative folder counts");
Expect(counts[0], 5L, "nonzero folder count");
Expect(counts[500002], 0L, "explicit server zero is a valid folder count");
Expect(counts.ContainsKey(500010), false, "missing field must never reset known count");
Expect(MailRuExplicitFolderCounts.Read(
    """{"body":{"folders_content":[{"id":0}]}}""").Count,
    0, "compact folder response without folder metadata preserves counters");

// The updater must keep working if the API host is unavailable while
// github.com still exposes /releases/latest. These tests make NO network calls.
const string releaseJson = """
{
 "tag_name":"v0.3.27",
 "assets":[{"name":"MailRuDesktop_Update_v0.3.27.exe",
   "browser_download_url":"https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.27/MailRuDesktop_Update_v0.3.27.exe"}]
}
""";
using (var api = new HttpClient(new UpdateStubHandler(_ =>
           new HttpResponseMessage(HttpStatusCode.OK)
           { Content = new StringContent(releaseJson) })))
using (var site = new HttpClient(new UpdateStubHandler(_ =>
           throw new Exception("The website fallback must not be used when API succeeds."))))
{
    var release = await GitHubUpdateService.GetLatestReleaseAsync(api, site);
    Expect(release.Version, new Version(0, 3, 27), "update API parses current release");
    Expect(release.Tag, "v0.3.27", "update API retains authoritative tag");
}
using (var api = new HttpClient(new UpdateStubHandler(_ =>
           new HttpResponseMessage(HttpStatusCode.Forbidden))))
using (var site = new HttpClient(new UpdateStubHandler(_ =>
{
    var redirect = new HttpResponseMessage(HttpStatusCode.Redirect);
    redirect.Headers.Location = new Uri(
        "https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.27");
    return redirect;
})))
{
    var release = await GitHubUpdateService.GetLatestReleaseAsync(api, site);
    Expect(release.Version, new Version(0, 3, 27),
        "API HTTP403 falls back to website release redirect");
    Expect(release.UpdateDownloadUrl,
        "https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.27/MailRuDesktop_Update_v0.3.27.exe",
        "website fallback uses canonical same-repository installer path");
}
using (var api = new HttpClient(new UpdateStubHandler(_ =>
           new HttpResponseMessage(HttpStatusCode.ServiceUnavailable))))
using (var site = new HttpClient(new UpdateStubHandler(_ =>
{
    var redirect = new HttpResponseMessage(HttpStatusCode.Redirect);
    redirect.Headers.Location = new Uri("https://example.net/evil.exe");
    return redirect;
})))
{
    var refusedUnsafeDestination = false;
    try { await GitHubUpdateService.GetLatestReleaseAsync(api, site); }
    catch (InvalidOperationException error)
    {
        refusedUnsafeDestination = error.Message.Contains("HTTP 503", StringComparison.Ordinal) &&
                                   error.Message.Contains("неподходящий ответ", StringComparison.Ordinal);
    }
    Expect(refusedUnsafeDestination, true,
        "unsafe website redirect is rejected with useful diagnostic categories");
}
using (var api = new HttpClient(new UpdateStubHandler(_ =>
           new HttpResponseMessage(HttpStatusCode.Forbidden))))
using (var site = new HttpClient(new UpdateStubHandler(_ =>
           new HttpResponseMessage(HttpStatusCode.BadGateway))))
{
    var diagnosedBothFailures = false;
    try { await GitHubUpdateService.GetLatestReleaseAsync(api, site); }
    catch (InvalidOperationException error)
    {
        diagnosedBothFailures = error.Message.Contains("HTTP 403", StringComparison.Ordinal) &&
                                error.Message.Contains("HTTP 502", StringComparison.Ordinal);
    }
    Expect(diagnosedBothFailures, true,
        "both unavailable hosts report safe status codes instead of silent failure");
}

var importRoot = Path.Combine(Path.GetTempPath(),
    "MailRuTemplateImport-" + Guid.NewGuid().ToString("N"));
try
{
    Directory.CreateDirectory(importRoot);
    var importPath = Path.Combine(importRoot, "External.md");
    var original = """
---
subject: "Тема"
attachments:
---
Тело
""";
    File.WriteAllText(importPath, original);
    var imported = new MarkdownTemplateStore(importRoot).ReadExternalFile(importPath);
    Expect(imported.Name, "External", "single external Markdown file name");
    Expect(imported.Subject, "Тема", "external Markdown metadata is loaded");
    Expect(File.ReadAllText(importPath), original,
        "loading a template for editing never rewrites its source file");
}
finally
{
    if (Directory.Exists(importRoot))
        Directory.Delete(importRoot, recursive: true);
}

Expect(ReaderPresentationPolicy.CanReveal(
    domReady: true, imagesSettled: false, deadlineReached: false),
    false, "HTML DOM alone does not reveal unfinished images");
Expect(ReaderPresentationPolicy.CanReveal(
    domReady: true, imagesSettled: true, deadlineReached: false),
    true, "finished images allow single atomic reveal");
Expect(ReaderPresentationPolicy.CanReveal(
    domReady: false, imagesSettled: true, deadlineReached: true),
    false, "no early reveal before HTML DOM is ready");
Expect(ReaderPresentationPolicy.CanReveal(
    domReady: true, imagesSettled: false, deadlineReached: true),
    true, "resource deadline bounds waiting time");
Expect(ReaderPresentationPolicy.ShouldStopLoading(
    domReady: true, imagesSettled: false, deadlineReached: true),
    true, "timed-out images stop loading before reveal");
Expect(ReaderPresentationPolicy.ShouldStopLoading(
    domReady: true, imagesSettled: true, deadlineReached: true),
    false, "already complete document must never be stopped");
Expect(ReaderPresentationPolicy.MaximumResourceWait <= TimeSpan.FromSeconds(4),
    true, "image deadline remains bounded under four seconds");

var shell = ReaderShellScripts.CreateShell("#121212");
Expect(shell.Contains("id=\"surface\"", StringComparison.Ordinal), true,
    "single permanent reader shell contains the display surface");
Expect(shell.Contains("id=\"status\"", StringComparison.Ordinal), true,
    "browser-native loading layer replaces the WPF overlay");
Expect(shell.Contains("allow-scripts", StringComparison.Ordinal), false,
    "email content cannot run scripts in the shell");
var dangerousHtml = "<div>\";}(); window.alert('test'); // \\n</div>";
var stagedScript = ReaderShellScripts.Stage(27, dangerousHtml);
Expect(stagedScript.Contains("frame.setAttribute('sandbox', 'allow-same-origin')",
    StringComparison.Ordinal), true, "email iframe disallows scripts and navigation");
Expect(stagedScript.Contains(System.Text.Json.JsonSerializer.Serialize(dangerousHtml),
    StringComparison.Ordinal), true,
    "untrusted HTML is passed to script only as JSON string data");
Expect(ReaderShellScripts.Begin(27, "#111111", "Загрузка")
    .Contains("rev < previous", StringComparison.Ordinal), true,
    "stale selection cannot replace the current document");
Expect(ReaderShellScripts.Poll(27).Contains(
    "doc.readyState !== 'complete'", StringComparison.Ordinal), true,
    "actual document and image completion are checked before reveal");
Expect(ReaderShellScripts.Commit(27).Contains(
    "if (old) old.remove()", StringComparison.Ordinal), true,
    "new document atomically replaces previous frame inside one browser");
Expect(ReaderShellScripts.FinishPendingImages(27).Contains(
    "image.removeAttribute('srcset')", StringComparison.Ordinal), true,
    "late resource loads are detached before visible publication");

Console.WriteLine("All interaction logic tests passed.");

sealed class RecordingHandler : HttpMessageHandler
{
    public string LastPath { get; private set; } = "";
    public string LastForm { get; private set; } = "";

    protected override async Task<HttpResponseMessage> SendAsync(
        HttpRequestMessage request, CancellationToken cancellationToken)
    {
        LastPath = request.RequestUri?.AbsolutePath ?? "";
        LastForm = request.Content is null ? "" :
            await request.Content.ReadAsStringAsync(cancellationToken);
        return new HttpResponseMessage(HttpStatusCode.OK)
        {
            Content = new StringContent("{\"status\":200}")
        };
    }
}

sealed class TokenEnvelopeHandler(HttpStatusCode code, string payload) : HttpMessageHandler
{
    protected override Task<HttpResponseMessage> SendAsync(
        HttpRequestMessage request, CancellationToken cancellationToken) =>
        Task.FromResult(new HttpResponseMessage(code)
        {
            Content = new StringContent(payload)
        });
}

sealed class UpdateStubHandler(Func<HttpRequestMessage, HttpResponseMessage> responder)
    : HttpMessageHandler
{
    protected override Task<HttpResponseMessage> SendAsync(
        HttpRequestMessage request, CancellationToken cancellationToken) =>
        Task.FromResult(responder(request));
}
