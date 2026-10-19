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

    internal static MailingPreparationResult Prepare(
        MailingProfile profile, IReadOnlyList<MailingExcelRow> rows,
        IReadOnlyDictionary<string, string> names, string templateBody,
        IReadOnlySet<string> excluded, IReadOnlySet<string> authorized,
        DateTimeOffset? now = null)
    {
        var result = new MailingPreparationResult();
        if (profile.IntervalSeconds < 5 || profile.IntervalSeconds > 86400)
            throw new InvalidOperationException("Интервал между письмами должен составлять 5–86400 секунд.");
        if (profile.Senders.Count == 0)
            throw new InvalidOperationException("Выберите хотя бы один аккаунт-отправитель.");
        if (profile.Senders.Any(x => x.Quantity <= 0 || !authorized.Contains(x.Login)) ||
            profile.Senders.Select(x => x.Login).Distinct(StringComparer.OrdinalIgnoreCase)
                .Count() != profile.Senders.Count)
            throw new InvalidOperationException("Проверьте аккаунты: авторизация, уникальность и квоты.");
        if (string.IsNullOrWhiteSpace(templateBody))
            throw new InvalidOperationException("Выберите один непустой шаблон.");
        var start = ResolveStart(profile);
        if (start <= (now ?? DateTimeOffset.Now).AddMinutes(2))
            throw new InvalidOperationException("Начало рассылки должно быть минимум через 2 минуты.");
        var quotas = profile.Senders.Select(x => (x.Login, x.Quantity, Used: 0)).ToArray();
        var next = 0;
        foreach (var item in rows)
        {
            string status = "Пропуск", reason = "";
            if (!item.Visible) reason = "Строка скрыта";
            else if (!string.IsNullOrWhiteSpace(item.ExistingMark))
                reason = "Уже имеется отметка о рассылке";
            else if (!IsValidEmail(item.Email)) { status = "Требует проверки"; reason = "Некорректный адрес"; }
            else if (excluded.Contains(item.Email.Trim())) reason = "Адрес в списке исключений";
            else if (!IsValidInn(item.Inn)) { status = "Требует проверки"; reason = "Некорректный ИНН"; }
            else if (string.IsNullOrWhiteSpace(item.Region))
            { status = "Требует проверки"; reason = "Не указан регион"; }
            else if (!names.TryGetValue(item.Inn, out var organization) ||
                     string.IsNullOrWhiteSpace(organization))
            { status = "Требует проверки"; reason = "Нет подтверждённого названия организации"; }
            else
            {
                while (next < quotas.Length && quotas[next].Used >= quotas[next].Quantity) next++;
                if (next >= quotas.Length) reason = "Сверх суммарной квоты";
                else
                {
                    try
                    {
                        var scheduled = start.AddSeconds((long)quotas[next].Used * profile.IntervalSeconds);
                        if (scheduled <= (now ?? DateTimeOffset.Now))
                            throw new InvalidOperationException("Время задания уже прошло");
                        var subject = Render(profile.SubjectTemplate, organization, item.Inn, item.Region);
                        var body = Render(templateBody, organization, item.Inn, item.Region);
                        result.Rows.Add(new MailingPreparedRow(
                            item.RowNumber, item.Email, item.Inn, item.Region,
                            organization, quotas[next].Login, scheduled, subject, body,
                            "Ready", "Только предварительный просмотр — постановка на сервер отключена"));
                        quotas[next].Used++;
                        continue;
                    }
                    catch (Exception ex) when (ex is InvalidOperationException or ArgumentOutOfRangeException)
                    { status = "Требует проверки"; reason = ex.Message; }
                }
            }
            result.Rows.Add(new MailingPreparedRow(item.RowNumber, item.Email, item.Inn,
                item.Region, names.GetValueOrDefault(item.Inn, ""), "", null,
                "", "", status, reason));
        }
        return result;
    }
}
