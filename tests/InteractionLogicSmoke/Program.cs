using System.Net;
using MailRuDesktop.App;
using MailRuDesktop.Protocol;

static void Expect<T>(T actual, T expected, string label)
{
    if (!EqualityComparer<T>.Default.Equals(actual, expected))
        throw new Exception($"{label}: expected {expected}, got {actual}");
    Console.WriteLine("PASS " + label);
}

Expect(MailRuEndpointCatalog.IsRuntimeHostAllowed("push-me.mail.ru"), true,
    "Original APK PushMe Prod host is registered for strict host policy");
Expect(MailRuPushProbe.ClassifyNetworkError(
    new HttpRequestException(HttpRequestError.SecureConnectionError, "SECRET_CERT_DETAIL")),
    "ошибка проверки сертификата TLS", "TLS failure classified without leaking details");
Expect(MailRuPushProbe.ClassifyNetworkError(
    new HttpRequestException(HttpRequestError.NameResolutionError, "SECRET_DNS_DETAIL")),
    "ошибка DNS: адрес сервера не найден", "DNS failure classified without leaking details");

// Constant receiving: duplicate protection, backoff and user opt-out are offline.
var msgData = PushWire.Append(
    PushWire.TextField(5, "ru.mail.mailapp"),
    PushWire.BytesField(7, PushWire.Append(
        PushWire.TextField(1, "event"), PushWire.TextField(2, "4"))),
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
    Expect(manager.AcceptMessage("TEST@EXAMPLE.INVALID", msgData), true,
        "first delivery is accepted");
    Expect(manager.AcceptMessage("test@example.invalid", msgData), false,
        "same persistent message id is not processed twice");
    Expect(manager.ActiveAccountCount, 0, "manager has no unsolicited account connections");
    manager.Reconcile(Array.Empty<(string Login, string Token)>(), enabled: false);
    Expect(manager.Enabled, false, "user opt-out cancels the receiving service");
    Expect(MailRuPushBackgroundService.RetryDelay(5),
        TimeSpan.FromMinutes(10), "reconnect has bounded ten-minute backoff");
}
using (var folder = new System.IO.MemoryStream())
{
    var copy = PushWire.Varint(300);
    await folder.WriteAsync(copy);
    if (folder.Length < 2) throw new Exception("protobuf multi-byte varint not encoded");
}

// Original Android-app push protocol: all tests are offline and use fake tokens.
var pushRequest = MailRuPushProbe.BuildSubscription(
    "TEST@EXAMPLE.INVALID", "NOT_A_REAL_OAUTH", "NOT_A_REAL_GOOGLE_TOKEN",
    12345, "fresh-trial-only");
using (var pushJson = System.Text.Json.JsonDocument.Parse(
    System.Text.Json.JsonSerializer.Serialize(new[] { pushRequest })))
{
    var samplePush = pushJson.RootElement[0];
    Expect(samplePush.GetProperty("account").GetString(), "test@example.invalid",
        "PushMe account normalized to lower case");
    Expect(samplePush.GetProperty("application").GetString(), "mail", "PushMe application");
    Expect(samplePush.GetProperty("platform").GetString(), "android", "PushMe original FCM wire platform");
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
