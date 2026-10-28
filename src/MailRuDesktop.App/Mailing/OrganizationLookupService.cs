using System.IO;
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
    private readonly HttpClient _http;

    internal OrganizationLookupService(HttpMessageHandler? handler = null)
    {
        _http = handler is null ? new HttpClient() : new HttpClient(handler);
        _http.Timeout = TimeSpan.FromSeconds(15);
    }
    private readonly Dictionary<string, string> _cache =
        new(StringComparer.Ordinal);
    private DateTimeOffset? _previousRequest;
    private const string Url =
        "https://www.tinkoff.ru/api/common/dadata/suggestions/api/4_1/rs/suggest/party?appName=company-pages";

    // Keep normal lookups spaced by five seconds. The single recovery attempt
    // is explicitly allowed after a two-second delay from a failed request.
    internal async Task<(string? Name, bool NetworkFallback)> ResolveWithNetworkRetryAsync(
        string inn, Action<string> log, CancellationToken cancellationToken = default,
        TimeSpan? testDelay = null)
    {
        try
        {
            return (await ResolveAsync(inn, cancellationToken), false);
        }
        catch (Exception ex) when (IsRetryableNetworkFailure(ex, cancellationToken))
        {
            log($"Сбой запроса справочника ({ex.Message}). Повтор через 2 секунды.");
            await Task.Delay(testDelay ?? TimeSpan.FromSeconds(2), cancellationToken);
            try
            {
                return (await ResolveAsync(inn, cancellationToken, immediateRetry: true), false);
            }
            catch (Exception retryError) when (
                IsRetryableNetworkFailure(retryError, cancellationToken))
            {
                log($"Повторный запрос справочника не удался ({retryError.Message}). " +
                    "Используем «Наименование работодателя» из Excel.");
                return (null, true);
            }
        }
    }

    private static bool IsRetryableNetworkFailure(
        Exception ex, CancellationToken cancellationToken)
    {
        if (ex is OperationCanceledException)
            return !cancellationToken.IsCancellationRequested;
        if (ex is IOException) return true;
        if (ex is not HttpRequestException httpError) return false;
        if (httpError.StatusCode is null) return true;
        var status = (int)httpError.StatusCode.Value;
        return status is 408 or 429 || status >= 500;
    }

    internal async Task<string?> ResolveAsync(string inn,
        CancellationToken cancellationToken = default, bool immediateRetry = false)
    {
        if (!MailingPlanner.IsValidInn(inn)) return null;
        if (_cache.TryGetValue(inn, out var known)) return known;
        // Ordinary lookup requests remain at least five seconds apart.
        if (!immediateRetry && _previousRequest is DateTimeOffset previous)
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
                $"Справочник организаций вернул HTTP {(int)response.StatusCode}.",
                null, response.StatusCode);
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
