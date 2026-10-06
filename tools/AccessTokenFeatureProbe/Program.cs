using System.Text.Json;
using MailRuDesktop.Protocol;

var login = Environment.GetEnvironmentVariable("MAILRU_TEST_LOGIN");
var password = Environment.GetEnvironmentVariable("MAILRU_TEST_PASSWORD");
var runId = Environment.GetEnvironmentVariable("GITHUB_RUN_ID") ?? "local";

if (string.IsNullOrWhiteSpace(login) || string.IsNullOrWhiteSpace(password))
{
    Console.WriteLine("credentials: missing");
    return 2;
}

using var client = new MailRuClient();
var failures = new List<string>();
DateTimeOffset? lastRequest = null;

async Task PaceAsync()
{
    if (lastRequest is DateTimeOffset previous)
    {
        var remaining = TimeSpan.FromMilliseconds(5200) - (DateTimeOffset.UtcNow - previous);
        if (remaining > TimeSpan.Zero)
            await Task.Delay(remaining);
    }
}

async Task<T?> ProbeAsync<T>(string name, Func<Task<T>> action, Func<T, bool>? success = null)
{
    await PaceAsync();
    try
    {
        var result = await action();
        lastRequest = DateTimeOffset.UtcNow;
        var ok = success?.Invoke(result) ?? true;
        Console.WriteLine($"{name}: {(ok ? "PASS" : "FAIL")}");
        if (!ok) failures.Add(name);
        return result;
    }
    catch (Exception ex)
    {
        lastRequest = DateTimeOffset.UtcNow;
        Console.WriteLine($"{name}: FAIL ({ex.GetType().Name})");
        failures.Add(name);
        return default;
    }
}

var auth = await ProbeAsync(
    "auth",
    () => client.AuthenticateAsync(login!, password!),
    x => x.Success && !string.IsNullOrWhiteSpace(x.AccessToken));

if (auth is null || !auth.Success || string.IsNullOrWhiteSpace(auth.AccessToken))
{
    Console.WriteLine("probe: STOP — authorization unavailable");
    return 3;
}

var token = auth.AccessToken!;
var inboxRaw = await ProbeAsync("inbox", () => client.GetFolderThreadsAsync(token, 0));
MailRuFolderSnapshot? inbox = null;
if (!string.IsNullOrWhiteSpace(inboxRaw))
{
    try
    {
        inbox = MailRuThreadStatusParser.Parse(inboxRaw, 0);
        Console.WriteLine($"inbox_parse: PASS (messages={inbox.Messages.Count})");
    }
    catch
    {
        failures.Add("inbox_parse");
        Console.WriteLine("inbox_parse: FAIL");
    }
}

await ProbeAsync("folders_list",
    () => client.GetFoldersAsync(token, login!),
    x => x.Count > 0);

await ProbeAsync("address_book",
    () => client.GetAddressBookAsync(token, login!),
    _ => true);

await ProbeAsync("recipient_lookup",
    () => client.SearchPeopleAsync(token, login!),
    _ => true);

await ProbeAsync("search_classic",
    () => client.SearchMessagesClassicAsync(token, login!, "a", limit: 20),
    x => x.RawResponse.Length > 0);

await ProbeAsync("search_new",
    () => client.SearchMessagesNewAsync(token, login!, "a", limit: 20),
    x => x.RawResponse.Length > 0);

var probeMessage = inbox?.Messages.FirstOrDefault();
if (probeMessage is not null)
{
    var originalFlag = probeMessage.Flagged;
    var originalPin = probeMessage.Pinned;

    try
    {
        await ProbeAsync("flag_change",
            () => client.SetFlaggedAsync(token, login!, probeMessage.Id, probeMessage.FolderId ?? 0, !originalFlag),
            x => x.Success);
    }
    finally
    {
        await ProbeAsync("flag_restore",
            () => client.SetFlaggedAsync(token, login!, probeMessage.Id, probeMessage.FolderId ?? 0, originalFlag),
            x => x.Success);
    }

    try
    {
        await ProbeAsync("pin_change",
            () => client.SetPinnedAsync(token, login!, probeMessage.Id, probeMessage.FolderId ?? 0, !originalPin),
            x => x.Success);
    }
    finally
    {
        await ProbeAsync("pin_restore",
            () => client.SetPinnedAsync(token, login!, probeMessage.Id, probeMessage.FolderId ?? 0, originalPin),
            x => x.Success);
    }
}
else
{
    Console.WriteLine("flag_change: SKIP (no inbox message)");
    Console.WriteLine("pin_change: SKIP (no inbox message)");
    failures.Add("flag_change:no_message");
    failures.Add("pin_change:no_message");
}

int? temporaryFolderId = null;
var folderName = $"MailRuDesktop probe {runId}";
var renamedFolderName = $"MailRuDesktop probe {runId} ok";

try
{
    var created = await ProbeAsync("folder_create",
        () => client.CreateFolderAsync(token, login!, folderName),
        x => x.Success);

    if (created?.Success == true)
    {
        temporaryFolderId = ParseFirstBodyId(created.RawResponse);
        if (temporaryFolderId is null)
        {
            failures.Add("folder_create:id");
            Console.WriteLine("folder_create_id: FAIL");
        }
        else
        {
            Console.WriteLine("folder_create_id: PASS");

            await ProbeAsync("folder_rename",
                () => client.RenameFolderAsync(token, login!, temporaryFolderId.Value, renamedFolderName),
                x => x.Success);

            await ProbeAsync("folder_clear",
                () => client.ClearFolderAsync(token, login!, temporaryFolderId.Value),
                x => x.Success);
        }
    }
}
finally
{
    if (temporaryFolderId is int folderId)
    {
        await ProbeAsync("folder_delete",
            () => client.DeleteFolderAsync(token, login!, folderId),
            x => x.Success);
        temporaryFolderId = null;
    }
}

var draftSubject = $"MailRu Desktop probe draft {runId}";
var draft = await ProbeAsync("draft_save",
    () => client.SaveDraftAsync(
        token,
        login!,
        new MailRuOutgoingMessage(
            To: login!,
            Subject: draftSubject,
            Text: "MailRu Desktop access-token feature probe.",
            MessageId: MailRuClient.KnownWorkingMessageId)),
    x => x.Success);

if (draft?.Success == true)
{
    var draftsRaw = await ProbeAsync("draft_list", () => client.GetFolderThreadsAsync(token, 500001));
    string? draftId = null;
    if (!string.IsNullOrWhiteSpace(draftsRaw))
    {
        try
        {
            var snapshot = MailRuThreadStatusParser.Parse(draftsRaw, 500001);
            draftId = snapshot.Messages.FirstOrDefault(x =>
                string.Equals(x.Subject, draftSubject, StringComparison.Ordinal))?.Id;
        }
        catch { }
    }

    if (string.IsNullOrWhiteSpace(draftId))
    {
        Console.WriteLine("permanent_remove: FAIL (probe draft not found)");
        failures.Add("permanent_remove:draft_not_found");
    }
    else
    {
        await ProbeAsync("permanent_remove",
            () => client.RemoveMessagesAsync(token, login!, new[] { draftId }),
            x => x.Success);
    }
}

Console.WriteLine($"probe_total_failures: {failures.Count}");
if (failures.Count > 0)
{
    Console.WriteLine("failed_steps: " + string.Join(",", failures));
    return 1;
}

Console.WriteLine("probe: PASS");
return 0;

static int? ParseFirstBodyId(string payload)
{
    try
    {
        using var document = JsonDocument.Parse(payload);
        if (!document.RootElement.TryGetProperty("body", out var body) ||
            body.ValueKind != JsonValueKind.Array ||
            body.GetArrayLength() == 0)
            return null;

        var first = body[0];
        if (first.ValueKind == JsonValueKind.Number && first.TryGetInt32(out var n))
            return n;
        if (first.ValueKind == JsonValueKind.String && int.TryParse(first.GetString(), out n))
            return n;
    }
    catch { }

    return null;
}


static string DescribeApiResponse(string payload)
{
    try
    {
        using var document = JsonDocument.Parse(payload);
        var root = document.RootElement;
        var status = root.ValueKind == JsonValueKind.Object && root.TryGetProperty("status", out var s)
            ? s.ToString()
            : "none";
        var error = FindScalar(root, "error") ?? FindScalar(root, "error_code") ?? "none";
        return $"status={SafeText(status)},error={SafeText(error)}";
    }
    catch
    {
        return "non_json";
    }
}

static string? FindScalar(JsonElement element, string name)
{
    if (element.ValueKind == JsonValueKind.Object)
    {
        foreach (var property in element.EnumerateObject())
        {
            if (property.NameEquals(name) &&
                property.Value.ValueKind is JsonValueKind.String or JsonValueKind.Number or JsonValueKind.True or JsonValueKind.False)
                return property.Value.ToString();

            var nested = FindScalar(property.Value, name);
            if (nested is not null)
                return nested;
        }
    }
    else if (element.ValueKind == JsonValueKind.Array)
    {
        foreach (var item in element.EnumerateArray())
        {
            var nested = FindScalar(item, name);
            if (nested is not null)
                return nested;
        }
    }

    return null;
}

static string SafeText(string? value)
{
    if (string.IsNullOrWhiteSpace(value))
        return "none";

    return new string(value
        .Where(ch => char.IsLetterOrDigit(ch) || ch is ' ' or '_' or '-' or '.' or ':' or '/')
        .Take(160)
        .ToArray());
}
