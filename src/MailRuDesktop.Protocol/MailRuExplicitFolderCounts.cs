using System.Text.Json;

namespace MailRuDesktop.Protocol;

/// <summary>
/// Reads ONLY explicitly supplied folder unread counters. A missing property
/// is not a zero. This prevents a partial/compact Mail.ru status response from
/// resetting unrelated folder counters during background refresh.
/// </summary>
public static class MailRuExplicitFolderCounts
{
    public static IReadOnlyDictionary<int, long> Read(string payload)
    {
        var result = new Dictionary<int, long>();
        if (string.IsNullOrWhiteSpace(payload))
            return result;

        try
        {
            using var document = JsonDocument.Parse(payload);
            var root = document.RootElement;
            if (root.ValueKind != JsonValueKind.Object)
                return result;

            var body = root.TryGetProperty("body", out var b) &&
                       b.ValueKind == JsonValueKind.Object ? b : root;
            if (!body.TryGetProperty("folders", out var folders) ||
                folders.ValueKind != JsonValueKind.Array)
                return result;

            foreach (var folder in folders.EnumerateArray())
            {
                if (folder.ValueKind != JsonValueKind.Object ||
                    !folder.TryGetProperty("id", out var idNode) ||
                    !folder.TryGetProperty("messages_unread", out var countNode))
                    continue;

                if (!int.TryParse(idNode.ToString(), out var id) ||
                    !long.TryParse(countNode.ToString(), out var count) ||
                    count < 0)
                    continue;

                result[id] = count;
            }
        }
        catch (JsonException)
        {
            // A background refresh must never erase a known good counter.
        }
        return result;
    }
}
