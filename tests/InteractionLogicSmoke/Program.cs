using System.Net;
using MailRuDesktop.App;
using MailRuDesktop.Protocol;

static void Expect<T>(T actual, T expected, string label)
{
    if (!EqualityComparer<T>.Default.Equals(actual, expected))
        throw new Exception($"{label}: expected {expected}, got {actual}");
    Console.WriteLine("PASS " + label);
}

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

    templates.ChangeDirectory(next);
    Expect(templates.DirectoryPath, Path.GetFullPath(next), "template folder is configurable");
    Expect(templates.LoadAll().Count, 4, "template files preserved on directory change");
    var moved = templates.LoadAll().Single(t => t.Name == "Reply");
    if (!File.Exists(moved.Attachments.Single()))
        throw new Exception("Attachment missing after template folder change");
    Console.WriteLine("PASS referenced attachments preserved after changing folder");
    if (!File.Exists(Path.Combine(first, "Reply.md")))
        throw new Exception("Original template folder was deleted");

    File.WriteAllText(Path.Combine(next, "AddedManually.md"), "New text");
    Expect(templates.LoadAll().Count, 5, "manually added file appears without restart");

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
