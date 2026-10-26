using System.Net.Http;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App.Mailing;

/// <summary>
/// Best-effort adapter to the legacy VBA data source. Unverified results are
/// never silently replaced by worksheet labels or used in prepared messages.
/// </summary>
internal sealed class OrganizationLookupService : IDisposable
{
    private readonly HttpClient _http = new()
    {
        Timeout = TimeSpan.FromSeconds(15)
    };
    private readonly Dictionary<string, string> _cache =
        new(StringComparer.Ordinal);
    private DateTimeOffset? _previousRequest;
    private const string Url =
        "https://www.tinkoff.ru/api/common/dadata/suggestions/api/4_1/rs/suggest/party?appName=company-pages";

    internal async Task<string?> ResolveAsync(string inn,
        CancellationToken cancellationToken = default)
    {
        if (!MailingPlanner.IsValidInn(inn)) return null;
        if (_cache.TryGetValue(inn, out var known)) return known;
        // Minimum 5 sec between outbound lookup calls; one at a time.
        if (_previousRequest is DateTimeOffset previous)
        {
            var remaining = TimeSpan.FromSeconds(5) -
                (DateTimeOffset.UtcNow - previous);
            if (remaining > TimeSpan.Zero)
                await Task.Delay(remaining, cancellationToken);
        }
        _previousRequest = DateTimeOffset.UtcNow;
        using var request = new HttpRequestMessage(HttpMethod.Post, Url)
        {
            Content = new StringContent(
                JsonSerializer.Serialize(new { count = 20, query = inn }),
                Encoding.UTF8, "application/json")
        };
        request.Headers.UserAgent.ParseAdd("MailRuDesktop/ExcelMailingPreparation");
        using var response = await _http.SendAsync(request, cancellationToken);
        if (!response.IsSuccessStatusCode)
            throw new HttpRequestException(
                $"Справочник организаций вернул HTTP {(int)response.StatusCode}.");
        using var stream = await response.Content.ReadAsStreamAsync(cancellationToken);
        using var json = await JsonDocument.ParseAsync(stream, cancellationToken: cancellationToken);
        var root = json.RootElement;
        if (root.ValueKind == JsonValueKind.Object &&
            root.TryGetProperty("body", out var body) &&
            body.ValueKind == JsonValueKind.Object) root = body;
        if (!root.TryGetProperty("suggestions", out var suggestions) ||
            suggestions.ValueKind != JsonValueKind.Array)
            throw new InvalidDataException(
                "Справочник организаций вернул неожиданный формат ответа.");
        var matches = new HashSet<string>(StringComparer.Ordinal);
        foreach (var suggestion in suggestions.EnumerateArray())
        {
            if (suggestion.ValueKind != JsonValueKind.Object ||
                !suggestion.TryGetProperty("data", out var data) ||
                data.ValueKind != JsonValueKind.Object ||
                !data.TryGetProperty("inn", out var responseInn)) continue;
            var returned = responseInn.ValueKind == JsonValueKind.String
                ? responseInn.GetString() : responseInn.ToString();
            if (!string.Equals(returned, inn, StringComparison.Ordinal)) continue;
            string? shortName = null;
            if (data.TryGetProperty("name", out var name) &&
                name.ValueKind == JsonValueKind.Object &&
                name.TryGetProperty("short_with_opf", out var nested) &&
                nested.ValueKind == JsonValueKind.String)
                shortName = nested.GetString();
            if (string.IsNullOrWhiteSpace(shortName) &&
                data.TryGetProperty("short_with_opf", out var direct) &&
                direct.ValueKind == JsonValueKind.String)
                shortName = direct.GetString();
            if (!string.IsNullOrWhiteSpace(shortName))
                matches.Add(shortName.Trim());
        }
        if (matches.Count != 1) return null;
        var result = matches.Single();
        _cache[inn] = result;
        return result;
    }

    public void Dispose() => _http.Dispose();
}
