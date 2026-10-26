using System.Globalization;
using System.IO;
using System.Net.Mail;
using System.Text;
using System.Text.Encodings.Web;
using System.Text.Json;
using System.Text.RegularExpressions;

namespace MailRuDesktop.App.Mailing;

internal sealed class MailingSenderQuota
{
    public string Login { get; set; } = "";
    public int Quantity { get; set; } = 50;
}

internal sealed class MailingProfile
{
    public string Id { get; set; } = Guid.NewGuid().ToString("N");
    public string Name { get; set; } = "Новая рассылка";
    public DateTime StartDate { get; set; } = DateTime.Today.AddDays(1);
    public string StartTime { get; set; } = "09:00";
    public string TimeZoneId { get; set; } = TimeZoneInfo.Local.Id;
    public int IntervalSeconds { get; set; } = 65;
    public bool JournalEnabled { get; set; } = true;
    public string TemplateName { get; set; } = "";
    public string SubjectTemplate { get; set; } =
        "О завершении срока действия СОУТ (Специальной оценки условий труда) в {Название_организации} (ИНН {ИНН_организации}) регион {Регион}";
    public List<MailingSenderQuota> Senders { get; set; } = [];
    public override string ToString() => Name;
}

internal sealed class MailingProfileStore
{
    private readonly string _path;
    internal MailingProfileStore(string? root = null)
    {
        root ??= Path.Combine(Environment.GetFolderPath(
            Environment.SpecialFolder.LocalApplicationData), "MailRuDesktop", "Mailing");
        Directory.CreateDirectory(root);
        _path = Path.Combine(root, "profiles.json");
        ExclusionsPath = Path.Combine(root, "excluded-addresses.txt");
    }
    internal string ExclusionsPath { get; }
    internal IReadOnlyList<MailingProfile> Load()
    {
        if (!File.Exists(_path)) return [];
        try
        {
            return JsonSerializer.Deserialize<List<MailingProfile>>(File.ReadAllText(_path))
                ?? [];
        }
        catch (Exception ex)
        {
            throw new IOException("Ошибка чтения профилей. Исходный файл сохранён: " + ex.Message, ex);
        }
    }
    internal void Save(IEnumerable<MailingProfile> profiles)
    {
        var json = JsonSerializer.Serialize(profiles.ToArray(), new JsonSerializerOptions
        {
            WriteIndented = true,
            Encoder = JavaScriptEncoder.UnsafeRelaxedJsonEscaping
        });
        var temporary = _path + ".tmp";
        File.WriteAllText(temporary, json, Encoding.UTF8);
        File.Move(temporary, _path, true);
    }
    internal HashSet<string> ReadExclusions() =>
        File.Exists(ExclusionsPath)
            ? File.ReadLines(ExclusionsPath)
                .Select(x => x.Split('#')[0].Trim())
                .Where(x => x.Length > 0)
                .ToHashSet(StringComparer.OrdinalIgnoreCase)
            : new HashSet<string>(StringComparer.OrdinalIgnoreCase);
}

internal sealed record MailingExcelRow(
    int RowNumber, bool Visible, string Email, string Inn,
    string Region, string ExistingMark, string EmployerName,
    string ExistingSender);

internal sealed record MailingPreparedRow(
    int RowNumber, string Email, string Inn, string Region,
    string Organization, string Sender, DateTimeOffset? ScheduledAt,
    string Subject, string Body, string Status, string Reason)
{
    public string ScheduledText => ScheduledAt?.ToString("dd.MM.yyyy HH:mm:ss") ?? "—";
    public string DisplayStatus => Status == "Ready" ? "Готово к проверке" : Status;
}

internal sealed class MailingPreparationResult
{
    public List<MailingPreparedRow> Rows { get; } = [];
    public int ReadyCount => Rows.Count(x => x.Status == "Ready");
    public int SkippedCount => Rows.Count(x => x.Status == "Пропуск");
    public int ReviewCount => Rows.Count(x => x.Status == "Требует проверки");
}

/// <summary>Pure preparation: no COM, HTTP, server submission or Excel writes.</summary>
internal static class MailingPlanner
{
    private static readonly Regex Placeholder = new(@"\{[^{}]+\}", RegexOptions.Compiled);
    internal static bool IsValidEmail(string value)
    {
        if (string.IsNullOrWhiteSpace(value) || value.Contains(';') || value.Contains(','))
            return false;
        try
        {
            var address = new MailAddress(value.Trim());
            return address.Address.Equals(value.Trim(), StringComparison.OrdinalIgnoreCase)
                   && address.Host.Contains('.');
        }
        catch (FormatException) { return false; }
    }

    internal static bool IsValidInn(string inn)
    {
        if (!inn.All(char.IsAsciiDigit) || inn.Length is not (10 or 12)) return false;
        int Check(int[] weights) =>
            weights.Select((weight, i) => weight * (inn[i] - '0')).Sum() % 11 % 10;
        return inn.Length == 10
            ? Check([2, 4, 10, 3, 5, 9, 4, 6, 8]) == inn[9] - '0'
            : Check([7, 2, 4, 10, 3, 5, 9, 4, 6, 8]) == inn[10] - '0'
              && Check([3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8]) == inn[11] - '0';
    }

    internal static DateTimeOffset ResolveStart(MailingProfile profile)
    {
        if (!TimeSpan.TryParseExact(profile.StartTime.Trim(),
                new[] { @"hh\:mm", @"hh\:mm\:ss" },
                CultureInfo.InvariantCulture, out var time))
            throw new InvalidOperationException("Время начала должно быть в формате ЧЧ:ММ.");
        TimeZoneInfo tz;
        try { tz = TimeZoneInfo.FindSystemTimeZoneById(profile.TimeZoneId); }
        catch (TimeZoneNotFoundException ex)
        {
            throw new InvalidOperationException("Недоступен часовой пояс профиля.", ex);
        }
        var local = DateTime.SpecifyKind(profile.StartDate.Date + time, DateTimeKind.Unspecified);
        if (tz.IsInvalidTime(local) || tz.IsAmbiguousTime(local))
            throw new InvalidOperationException("Время начала неоднозначно из-за перехода часового пояса.");
        return new DateTimeOffset(local, tz.GetUtcOffset(local));
    }

    internal static string Render(string template, string organization,
        string inn, string region)
    {
        var result = template.Replace("{Название_организации}", organization,
                StringComparison.Ordinal)
            .Replace("{ИНН_организации}", inn, StringComparison.Ordinal)
            .Replace("{Регион}", region, StringComparison.Ordinal);
        if (Placeholder.IsMatch(result))
            throw new InvalidOperationException(
                "Неизвестная подстановка в шаблоне: " + Placeholder.Match(result).Value);
        return result;
    }

    internal static MailingPlanSession CreateSession(
        MailingProfile profile, string templateBody,
        IReadOnlySet<string> excluded, IReadOnlySet<string> authorized,
        DateTimeOffset? now = null, bool collectRows = true,
        IReadOnlyDictionary<string, int>? usedSenderCounts = null) =>
        new(profile, templateBody, excluded, authorized, now, collectRows,
            usedSenderCounts);

    internal static MailingPreparationResult Prepare(
        MailingProfile profile, IReadOnlyList<MailingExcelRow> rows,
        IReadOnlyDictionary<string, string> names, string templateBody,
        IReadOnlySet<string> excluded, IReadOnlySet<string> authorized,
        DateTimeOffset? now = null)
    {
        var session = CreateSession(profile, templateBody, excluded, authorized, now);
        foreach (var row in rows)
            session.Accept(row, names.GetValueOrDefault(row.Inn));
        return session.Result;
    }
}

/// <summary>
/// Incremental state for large Excel sheets. Each row is visited once and
/// sender quotas persist across batches; no rescanning or stale schedules.
/// </summary>
internal sealed class MailingPlanSession
{
    private readonly MailingProfile _profile;
    private readonly string _templateBody;
    private readonly IReadOnlySet<string> _excluded;
    private readonly DateTimeOffset _now;
    private readonly DateTimeOffset _start;
    private readonly bool _collectRows;
    private readonly (string Login, int Quantity, int Used)[] _quotas;
    private int _currentSender;
    private int _readyCount;
    private readonly int _totalQuota;
    internal MailingPreparationResult Result { get; } = new();
    internal int Quota => _totalQuota;
    internal int ReadyCount => _readyCount;
    internal bool IsFull => _readyCount >= _totalQuota ||
        _quotas.All(x => x.Used >= x.Quantity);

    internal MailingPlanSession(MailingProfile profile, string templateBody,
        IReadOnlySet<string> excluded, IReadOnlySet<string> authorized,
        DateTimeOffset? now, bool collectRows,
        IReadOnlyDictionary<string, int>? usedSenderCounts = null)
    {
        _collectRows = collectRows;
        _profile = profile;
        _templateBody = templateBody;
        _excluded = excluded;
        _now = now ?? DateTimeOffset.Now;
        if (profile.IntervalSeconds < 5 || profile.IntervalSeconds > 86400)
            throw new InvalidOperationException(
                "Интервал между письмами должен составлять 5–86400 секунд.");
        if (profile.Senders.Count == 0)
            throw new InvalidOperationException(
                "Выберите хотя бы один аккаунт-отправитель.");
        if (profile.Senders.Any(x => x.Quantity <= 0 || !authorized.Contains(x.Login)) ||
            profile.Senders.Select(x => x.Login)
                .Distinct(StringComparer.OrdinalIgnoreCase).Count() != profile.Senders.Count)
            throw new InvalidOperationException(
                "Проверьте аккаунты: авторизация, уникальность и квоты.");
        if (string.IsNullOrWhiteSpace(templateBody))
            throw new InvalidOperationException("Выберите один непустой шаблон.");
        _start = MailingPlanner.ResolveStart(profile);
        if (_start <= _now.AddMinutes(2))
            throw new InvalidOperationException(
                "Начало рассылки должно быть минимум через 2 минуты.");
        // Quotas include sends already accepted in earlier runs and pending
        // reservations whose outcome is unknown; a new run starts with the
        // remaining capacity, not a fresh full quota.
        _quotas = profile.Senders.Select(x =>
        {
            var used = usedSenderCounts is not null &&
                usedSenderCounts.TryGetValue(x.Login, out var previous)
                    ? Math.Clamp(previous, 0, x.Quantity) : 0;
            return (x.Login, x.Quantity, Used: used);
        }).ToArray();
        _totalQuota = _quotas.Sum(x => x.Quantity - x.Used);
    }

    // A definite recipient refusal did not consume a server send: return
    // the slot to the same sender so the next valid row can use that quota.
    internal void ReleaseRecipientRefusal(string login)
    {
        for (var i = 0; i < _quotas.Length; i++)
        {
            if (!string.Equals(_quotas[i].Login, login,
                    StringComparison.OrdinalIgnoreCase))
                continue;
            if (_quotas[i].Used <= 0 || _readyCount <= 0)
                throw new InvalidOperationException("Квота задания не была зарезервирована.");
            _quotas[i].Used--;
            _readyCount--;
            return;
        }
        throw new InvalidOperationException("Неизвестный отправитель рассылки.");
    }

    internal bool NeedsOrganization(MailingExcelRow row) =>
        row.Visible && !IsFull &&
        string.IsNullOrWhiteSpace(row.ExistingMark) &&
        MailingPlanner.IsValidEmail(row.Email) &&
        !_excluded.Contains(row.Email.Trim()) &&
        MailingPlanner.IsValidInn(row.Inn) &&
        !string.IsNullOrWhiteSpace(row.Region);

    // Direct mode returns each result without accumulating a preview table.
    private MailingPreparedRow Record(MailingPreparedRow row)
    {
        if (_collectRows) Result.Rows.Add(row);
        return row;
    }

    internal MailingPreparedRow Accept(MailingExcelRow row, string? organization)
    {
        organization = string.IsNullOrWhiteSpace(organization)
            ? row.EmployerName.Trim() : organization.Trim();
        var status = "Пропуск";
        var reason = "";
        if (!row.Visible) reason = "Строка скрыта";
        else if (!string.IsNullOrWhiteSpace(row.ExistingMark))
            reason = "Уже имеется отметка о рассылке";
        else if (!MailingPlanner.IsValidEmail(row.Email))
        { status = "Требует проверки"; reason = "Некорректный адрес"; }
        else if (_excluded.Contains(row.Email.Trim()))
            reason = "Адрес в списке исключений";
        else if (!MailingPlanner.IsValidInn(row.Inn))
        { status = "Требует проверки"; reason = "Некорректный ИНН"; }
        else if (string.IsNullOrWhiteSpace(row.Region))
        { status = "Требует проверки"; reason = "Не указан регион"; }
        else if (string.IsNullOrWhiteSpace(organization))
        { status = "Требует проверки"; reason = "Нет названия ни в справочнике, ни в Excel"; }
        else if (IsFull)
            reason = "Сверх суммарной квоты";
        else
        {
            while (_currentSender < _quotas.Length &&
                   _quotas[_currentSender].Used >= _quotas[_currentSender].Quantity)
                _currentSender++;
            if (_currentSender >= _quotas.Length)
                reason = "Сверх суммарной квоты";
            else
            {
                try
                {
                    var selected = _quotas[_currentSender];
                    var time = _start.AddSeconds(
                        (long)selected.Used * _profile.IntervalSeconds);
                    if (time <= _now)
                        throw new InvalidOperationException("Время задания уже прошло");
                    var subject = MailingPlanner.Render(_profile.SubjectTemplate,
                        organization, row.Inn, row.Region);
                    var body = MailingPlanner.Render(_templateBody,
                        organization, row.Inn, row.Region);
                    var ready = new MailingPreparedRow(row.RowNumber, row.Email,
                        row.Inn, row.Region, organization, selected.Login, time,
                        subject, body, "Ready", "");
                    Record(ready);
                    _quotas[_currentSender].Used++;
                    _readyCount++;
                    return ready;
                }
                catch (Exception ex) when (ex is InvalidOperationException or ArgumentOutOfRangeException)
                { status = "Требует проверки"; reason = ex.Message; }
            }
        }
        return Record(new MailingPreparedRow(row.RowNumber, row.Email,
            row.Inn, row.Region, organization, "", null,
            "", "", status, reason));
    }
}
