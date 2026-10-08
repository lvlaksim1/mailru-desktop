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
