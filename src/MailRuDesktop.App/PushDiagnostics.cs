using System.IO;
using System.Net.Http;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;

namespace MailRuDesktop.App;

/// <summary>
/// Local bounded technical trace for Google MCS / PushMe.
/// ONLY fixed diagnostic codes, safe numeric metrics and exception categories.
/// Never log accounts, OAuth/FCM tokens, identifiers, raw server bodies or
/// exception messages (which can contain URLs or credentials).
/// Does not send diagnostics anywhere.
/// </summary>
internal static class PushDiagnostics
{
    private static readonly object Sync = new();
    private static readonly string LogDirectory = Path.Combine(
        Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
        "MailRuDesktop");
    private static readonly string LogPath = Path.Combine(LogDirectory, "push-diagnostics.log");
    private const int MaxLogBytes = 256 * 1024;
    private const int RetainLines = 900;
    private static long _sequence;
    private static string _lastFailure = "NONE";
    internal static event Action? Changed;

    private static string SafeCode(string? code)
    {
        if (string.IsNullOrWhiteSpace(code) ||
            !Regex.IsMatch(code, @"\A[A-Z][A-Z0-9_]{0,59}\z",
                RegexOptions.CultureInvariant))
            return "UNRECOGNIZED";
        return code;
    }

    // Account addresses are deliberately included ONLY when supplied to the
    // dedicated account operation logger. General Record() still validates
    // fixed codes; network messages, tokens and raw payloads are never logged.
    // The local view is detailed; copied/saved reports must explicitly choose
    // between full and anonymized form.
    internal static void RecordAccount(
        string stage, string code, string account,
        string? groupId = null, int? index = null, int? total = null,
        string? operationId = null, int? number = null)
    {
        var safeAccount = SafeAccount(account);
        var normalizedStage = SafeCode(stage);
        var normalizedCode = SafeCode(code);
        var safeGroup = SafeIdentifier(groupId);
        var safeOperation = SafeIdentifier(operationId);
        var item = index.HasValue && total.HasValue &&
            index.Value > 0 && total.Value >= index.Value
            ? " item=" + index.Value + "/" + total.Value : "";
        var metrics = number is null ? "" : " count_or_code=" + number.Value;
        var line = DateTimeOffset.Now.ToString("yyyy-MM-dd HH:mm:ss.fff zzz") +
            " #" + Interlocked.Increment(ref _sequence) +
            " [" + normalizedStage + "] " + normalizedCode +
            " account=" + safeAccount +
            (safeGroup is null ? "" : " group=" + safeGroup) +
            (safeOperation is null ? "" : " op=" + safeOperation) +
            item + metrics;
        AppendLine(line);
    }

    private static string SafeAccount(string? input)
    {
        if (string.IsNullOrWhiteSpace(input) || input.Length > 254 ||
            !Regex.IsMatch(input, @"\A[A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9](?:[A-Za-z0-9.-]{0,251}[A-Za-z0-9])?\z",
                RegexOptions.CultureInvariant))
            return "INVALID_ACCOUNT";
        return input.ToLowerInvariant();
    }

    private static string? SafeIdentifier(string? id) =>
        !string.IsNullOrEmpty(id) &&
        Regex.IsMatch(id, @"\A[a-zA-Z0-9_-]{1,36}\z",
            RegexOptions.CultureInvariant) ? id : null;

    private static void AppendLine(string line)
    {
        try
        {
            lock (Sync)
            {
                Directory.CreateDirectory(LogDirectory);
                File.AppendAllText(LogPath, line + Environment.NewLine,
                    new UTF8Encoding(false));
                var file = new FileInfo(LogPath);
                if (file.Length > MaxLogBytes)
                {
                    var lines = File.ReadAllLines(LogPath, Encoding.UTF8);
                    File.WriteAllLines(LogPath, lines.TakeLast(RetainLines),
                        new UTF8Encoding(false));
                }
            }
        }
        catch (IOException) { /* disk diagnostics must never interrupt mail */ }
        catch (UnauthorizedAccessException) { }
        NotifyChanged();
    }

    internal static void Record(string stage, string code, int? number = null)
    {
        var normalizedStage = SafeCode(stage);
        var normalizedCode = SafeCode(code);
        var metric = number is null ? "" : " count_or_code=" + number.Value;
        var line = DateTimeOffset.Now.ToString("yyyy-MM-dd HH:mm:ss.fff zzz") +
                   " #" + Interlocked.Increment(ref _sequence) +
                   " [" + normalizedStage + "] " + normalizedCode + metric;
        AppendLine(line);

    }

    // PushMe SDK's original SubscriptionResponse.Error includes `message`.
    // Preserve ONLY selected safe error vocabulary, never any arbitrary server
    // string. Emails, token fragments, URLs, device IDs and unknown words
    // cannot appear in the exported diagnostic report.
    private static readonly HashSet<string> SafeServerTerms = new(
        StringComparer.OrdinalIgnoreCase)
    {
        "invalid", "expired", "token", "access", "authentication", "authorization",
        "unauthorized", "forbidden", "error", "bad", "request", "parameter",
        "parameters", "missing", "required", "unsupported", "format",
        "malformed", "incorrect", "not", "valid", "failed", "account",
        "application", "client", "device", "id", "registered", "registration",
        "push", "firebase", "google", "credential", "credentials", "permission",
        "denied", "limit", "rate", "too", "many", "exceeded", "maximum",
        "rejected", "unknown", "unavailable", "blocked", "disabled",
        "неверный", "неверная", "неверное", "невалидный", "ошибка",
        "токен", "доступ", "истёк", "истек", "авторизация", "аккаунт",
        "устройство", "клиент", "приложение", "лимит", "превышен",
        "недопустимый", "параметр", "отказано", "отклонён", "отклонен"
    };

    internal static string SafeServerReason(string? serverMessage)
    {
        if (string.IsNullOrWhiteSpace(serverMessage)) return "EMPTY";
        var cleaned = Regex.Replace(serverMessage,
            @"(?i)\b[\w.+%-]+@[\w.-]+\.[a-z]{2,}\b|https?://\S+|\b[A-Za-z0-9_-]{24,}\b",
            " ");
        var safe = Regex.Matches(cleaned, @"\p{L}+")
            .Select(m => m.Value.ToLowerInvariant())
            .Where(word => SafeServerTerms.Contains(word))
            .Select(word => word switch
            {
                "неверный" or "неверная" or "неверное" or "невалидный" or
                    "недопустимый" => "INVALID",
                "ошибка" => "ERROR",
                "токен" => "TOKEN",
                "доступ" => "ACCESS",
                "истёк" or "истек" => "EXPIRED",
                "авторизация" => "AUTHORIZATION",
                "аккаунт" => "ACCOUNT",
                "устройство" => "DEVICE",
                "клиент" => "CLIENT",
                "приложение" => "APPLICATION",
                "лимит" => "LIMIT",
                "превышен" => "EXCEEDED",
                "параметр" => "PARAMETER",
                "отказано" or "отклонён" or "отклонен" => "REJECTED",
                _ => word.ToUpperInvariant()
            })
            .Take(6)
            .ToArray();
        if (safe.Length == 0) return "UNCLASSIFIED";
        var code = string.Join("_", safe);
        return SafeCode(code.Length > 45 ? code[..45] : code);
    }

    internal static string FailureCategory(Exception failure)
    {
        // Avoid ex.Message and ex.ToString(): they can contain request URIs or
        // service-provided content with tokens.
        if (failure is HttpRequestException http)
        {
            var result = "HTTP_" + SafeCode(http.HttpRequestError.ToString()
                .ToUpperInvariant());
            if (http.StatusCode is { } status)
                result += "_" + (int)status;
            return result;
        }
        if (failure is SocketException socket)
            return "SOCKET_" + SafeCode(socket.SocketErrorCode.ToString().ToUpperInvariant());
        if (failure is System.Security.Authentication.AuthenticationException)
            return "TLS_AUTH_FAILURE";
        if (failure is EndOfStreamException)
            return "MCS_END_OF_STREAM";
        if (failure is IOException)
            return "IO_FAILURE";
        if (failure is InvalidOperationException)
            return "PROTOCOL_STATE_FAILURE";
        if (failure is OperationCanceledException)
            return "CANCELLED";
        if (failure is System.Text.Json.JsonException)
            return "RESPONSE_JSON_FAILURE";
        return "EXCEPTION_" + SafeCode(failure.GetType().Name.ToUpperInvariant());
    }

    internal static void BeginAttempt(int number)
    {
        lock (Sync) _lastFailure = "NONE";
        Record("WORKER", "CONNECT_ATTEMPT", number);
    }

    internal static void Failure(string stage, Exception failure)
    {
        var category = FailureCategory(failure);
        var safeStage = SafeCode(stage);
        lock (Sync)
        {
            // Keep the original failing step (e.g. PUSHME_HTTP_SEND), not
            // the later generic catch in the background retry controller.
            if (!safeStage.StartsWith("WORKER_", StringComparison.Ordinal) ||
                _lastFailure == "NONE")
                _lastFailure = safeStage + ":" + category;
        }
        Record("ERROR", safeStage + "_" + category);
    }

    internal static string LastFailure
    {
        get { lock (Sync) return _lastFailure; }
    }

    // Stable address pseudonyms within each generated report; never export
    // plaintext when redactAccounts=true. Mapping is local to this report.
    internal static string Anonymize(string report)
    {
        var aliases = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        return Regex.Replace(report,
            @"(?i)\b[A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b",
            match =>
            {
                if (!aliases.TryGetValue(match.Value, out var number))
                {
                    number = aliases.Count + 1;
                    aliases[match.Value] = number;
                }
                return "ACCOUNT_" + number.ToString("D3");
            });
    }

    internal static string Report(int maxLines = RetainLines,
        bool redactAccounts = false)
    {
        lock (Sync)
        {
            try
            {
                var all = File.Exists(LogPath)
                    ? File.ReadAllLines(LogPath, Encoding.UTF8)
                        .TakeLast(Math.Clamp(maxLines, 1, RetainLines))
                    : Enumerable.Empty<string>();
                var version = typeof(PushDiagnostics).Assembly.GetName().Version?.ToString() ??
                              "unknown";
                var body = string.Join(Environment.NewLine, all);
                if (redactAccounts) body = Anonymize(body);
                return "MailRu Desktop — диагностика Google / PushMe" +
                       Environment.NewLine +
                       "Версия: " + version + Environment.NewLine +
                       (redactAccounts
                           ? "Адреса заменены стабильными обозначениями ACCOUNT_001, ACCOUNT_002…"
                           : "Локальный подробный журнал: содержит адреса аккаунтов.") +
                       Environment.NewLine +
                       "Пароли, токены, содержимое писем и сырые ответы сервера не записываются." +
                       Environment.NewLine +
                       "Последний сбой: " + _lastFailure + Environment.NewLine +
                       new string('-', 56) + Environment.NewLine + body;
            }
            catch (IOException)
            {
                return "Журнал Google / PushMe недоступен для чтения.";
            }
            catch (UnauthorizedAccessException)
            {
                return "Отказано в чтении журнала Google / PushMe.";
            }
        }
    }

    internal static void Clear()
    {
        lock (Sync)
        {
            _lastFailure = "NONE";
            try { if (File.Exists(LogPath)) File.Delete(LogPath); }
            catch (IOException) { }
            catch (UnauthorizedAccessException) { }
        }
        NotifyChanged();
    }

    private static void NotifyChanged()
    {
        try { Changed?.Invoke(); }
        catch { /* No UI subscriber may affect the transport. */ }
    }
}
