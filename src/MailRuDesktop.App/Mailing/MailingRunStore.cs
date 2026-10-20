using System.IO;
using System.Text;
using System.Text.Json;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App.Mailing;

// These states describe submission to Mail.ru, not delivery to the recipient.
internal enum MailingJobStatus
{
    Prepared,
    Submitting,
    AcceptedScheduled,
    Rejected,
    Indeterminate,
    ExcelWritePending,
    CompletedRecord
}

internal sealed class MailingJobRecord
{
    public int RowNumber { get; set; }
    public string Email { get; set; } = "";
    public string Inn { get; set; } = "";
    public string Region { get; set; } = "";
    public string Organization { get; set; } = "";
    public string Sender { get; set; } = "";
    public string MessageId { get; set; } = "";
    public string Subject { get; set; } = "";
    public string Body { get; set; } = "";
    public DateTimeOffset ScheduledAt { get; set; }
    public MailingJobStatus Status { get; set; } = MailingJobStatus.Prepared;
    public string ErrorCode { get; set; } = "";
    public DateTimeOffset? ConfirmedAtUtc { get; set; }
    public bool ExcelWritten { get; set; }
}

internal sealed class MailingRunRecord
{
    public string Id { get; set; } = "";
    public string ProfileId { get; set; } = "";
    public string ProfileName { get; set; } = "";
    public string WorkbookPath { get; set; } = "";
    public string SheetName { get; set; } = "";
    public DateTimeOffset CreatedAtUtc { get; set; }
    public MailingProfile ProfileSnapshot { get; set; } = new();
    public List<MailingJobRecord> Jobs { get; set; } = [];
    public int AcceptedCount => Jobs.Count(x => x.Status is
        MailingJobStatus.AcceptedScheduled or MailingJobStatus.ExcelWritePending or
        MailingJobStatus.CompletedRecord);
}

/// <summary>
/// Atomic, DPAPI-protected local history. Never stores credentials, tokens,
/// or plaintext message bodies on disk. No network or Excel operations here.
/// </summary>
internal sealed class MailingRunStore
{
    private static readonly JsonSerializerOptions JsonOptions = new()
    {
        WriteIndented = false
    };

    private readonly string _directory;

    internal MailingRunStore(string? directoryOverride = null)
    {
        _directory = directoryOverride ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "MailingRuns");
    }

    internal MailingRunRecord Create(
        MailingProfile profile,
        string workbookPath,
        string sheetName,
        IEnumerable<MailingPreparedRow> prepared)
    {
        ArgumentNullException.ThrowIfNull(profile);
        ArgumentException.ThrowIfNullOrWhiteSpace(workbookPath);
        ArgumentException.ThrowIfNullOrWhiteSpace(sheetName);
        ArgumentNullException.ThrowIfNull(prepared);

        var ready = prepared.Where(x => x.Status == "Ready").ToArray();
        if (ready.Length == 0)
            throw new InvalidOperationException("Нет проверенных писем для постановки.");

        var rowNumbers = new HashSet<int>();
        var tasks = new List<MailingJobRecord>();
        foreach (var row in ready)
        {
            if (!rowNumbers.Add(row.RowNumber) ||
                string.IsNullOrWhiteSpace(row.Email) ||
                string.IsNullOrWhiteSpace(row.Sender) ||
                string.IsNullOrWhiteSpace(row.Subject) ||
                string.IsNullOrWhiteSpace(row.Body) ||
                row.ScheduledAt is null)
                throw new InvalidOperationException(
                    "Неполное или повторяющееся задание в подготовленной таблице.");

            tasks.Add(new MailingJobRecord
            {
                RowNumber = row.RowNumber,
                Email = row.Email,
                Inn = row.Inn,
                Region = row.Region,
                Organization = row.Organization,
                Sender = row.Sender,
                MessageId = MailRuClient.CreateMessageId(),
                Subject = row.Subject,
                Body = row.Body,
                ScheduledAt = row.ScheduledAt.Value,
                Status = MailingJobStatus.Prepared
            });
        }

        // A failed Excel write cannot make an already accepted mail eligible again.
        // An ambiguous network outcome also blocks a silent second submission.
        foreach (var previous in ForWorkbook(workbookPath, sheetName))
        {
            foreach (var old in previous.Jobs.Where(x => x.Status is not
                         MailingJobStatus.Rejected))
            {
                if (tasks.Any(x => x.RowNumber == old.RowNumber &&
                      string.Equals(x.Email, old.Email, StringComparison.OrdinalIgnoreCase) &&
                      string.Equals(x.Inn, old.Inn, StringComparison.Ordinal)))
                    throw new InvalidOperationException(
                        $"Строка {old.RowNumber} уже существует в журнале запуска " +
                        $"{previous.Id}. Сначала завершите или проверьте прежнюю операцию.");
            }
        }

        var run = new MailingRunRecord
        {
            Id = Guid.NewGuid().ToString("N"),
            ProfileId = profile.Id,
            ProfileName = profile.Name,
            WorkbookPath = workbookPath,
            SheetName = sheetName,
            CreatedAtUtc = DateTimeOffset.UtcNow,
            ProfileSnapshot = JsonSerializer.Deserialize<MailingProfile>(
                JsonSerializer.Serialize(profile, JsonOptions), JsonOptions)
                ?? throw new InvalidOperationException("Не удалось сохранить снимок профиля."),
            Jobs = tasks
        };
        Save(run);
        return run;
    }

    // A new letter is persisted in the protected journal before its POST.
    // No separate preview list or whole-sheet preparation is required.
    internal MailingJobRecord Append(MailingRunRecord run, MailingPreparedRow prepared)
    {
        if (prepared.Status != "Ready" ||
            run.Jobs.Any(x => x.RowNumber == prepared.RowNumber))
            throw new InvalidOperationException(
                "Строка уже есть в текущем запуске или не готова к отправке.");
        if (string.IsNullOrWhiteSpace(prepared.Email) ||
            string.IsNullOrWhiteSpace(prepared.Sender) ||
            string.IsNullOrWhiteSpace(prepared.Subject) ||
            string.IsNullOrWhiteSpace(prepared.Body) ||
            prepared.ScheduledAt is null)
            throw new InvalidOperationException("Неполное задание рассылки.");
        var task = new MailingJobRecord
        {
            RowNumber = prepared.RowNumber,
            Email = prepared.Email,
            Inn = prepared.Inn,
            Region = prepared.Region,
            Organization = prepared.Organization,
            Sender = prepared.Sender,
            MessageId = MailRuClient.CreateMessageId(),
            Subject = prepared.Subject,
            Body = prepared.Body,
            ScheduledAt = prepared.ScheduledAt.Value
        };
        run.Jobs.Add(task);
        try { Save(run); }
        catch
        {
            run.Jobs.Remove(task);
            throw;
        }
        return task;
    }

    // Transient letters bypass all journal reads and writes. The user expressly
    // accepts that restarted sends may duplicate earlier server operations.
    internal static MailingRunRecord CreateUnrecorded(
        MailingProfile profile, string workbookPath, string sheetName,
        MailingPreparedRow row)
    {
        if (row.Status != "Ready" || row.ScheduledAt is null)
            throw new InvalidOperationException("Неполное задание рассылки.");
        return new MailingRunRecord
        {
            Id = Guid.NewGuid().ToString("N"),
            ProfileId = profile.Id,
            ProfileName = profile.Name,
            WorkbookPath = workbookPath,
            SheetName = sheetName,
            CreatedAtUtc = DateTimeOffset.UtcNow,
            Jobs =
            [
                new MailingJobRecord
                {
                    RowNumber = row.RowNumber,
                    Email = row.Email,
                    Inn = row.Inn,
                    Region = row.Region,
                    Organization = row.Organization,
                    Sender = row.Sender,
                    MessageId = MailRuClient.CreateMessageId(),
                    Subject = row.Subject,
                    Body = row.Body,
                    ScheduledAt = row.ScheduledAt.Value
                }
            ]
        };
    }

    internal void Delete(string runId)
    {
        File.Delete(GetPath(runId));
    }

    internal static string RowKey(int row, string email, string inn) =>
        row.ToString(System.Globalization.CultureInfo.InvariantCulture) + "\u001F" +
        email.Trim().ToUpperInvariant() + "\u001F" + inn.Trim();

    // Unlike recent-run history, conflict checks must cover ALL protected
    // journals; the oldest pending or indeterminate POST still matters.
    internal IReadOnlyList<MailingRunRecord> ForWorkbook(string workbookPath, string sheetName)
    {
        if (!Directory.Exists(_directory)) return [];
        return Directory.EnumerateFiles(_directory, "*.run")
            .Select(path => Load(Path.GetFileNameWithoutExtension(path)))
            .Where(run => string.Equals(run.WorkbookPath, workbookPath,
                              StringComparison.OrdinalIgnoreCase) &&
                          string.Equals(run.SheetName, sheetName,
                              StringComparison.OrdinalIgnoreCase))
            .OrderBy(x => x.CreatedAtUtc)
            .ToArray();
    }

    internal void Save(MailingRunRecord run)
    {
        ArgumentNullException.ThrowIfNull(run);
        Validate(run);
        Directory.CreateDirectory(_directory);
        var path = GetPath(run.Id);
        var temp = path + "." + Guid.NewGuid().ToString("N") + ".tmp";
        var json = JsonSerializer.Serialize(run, JsonOptions);
        try
        {
            File.WriteAllText(temp, AuthorizationStore.Dpapi.Protect(json),
                new UTF8Encoding(false));
            File.Move(temp, path, overwrite: true);
        }
        finally
        {
            if (File.Exists(temp)) File.Delete(temp);
        }
    }

    internal MailingRunRecord Load(string runId, bool recoverSubmitting = true)
    {
        var protectedText = File.ReadAllText(GetPath(runId), Encoding.UTF8);
        var json = AuthorizationStore.Dpapi.Unprotect(protectedText);
        var run = JsonSerializer.Deserialize<MailingRunRecord>(json, JsonOptions)
                  ?? throw new InvalidDataException("Повреждён журнал рассылки.");
        Validate(run);
        if (!string.Equals(run.Id, runId, StringComparison.Ordinal))
            throw new InvalidDataException("Идентификатор журнала не совпадает с именем файла.");
        // A process can end after POST was sent but before its answer was saved.
        // Such a request must never be automatically submitted again.
        var modified = false;
        foreach (var task in run.Jobs.Where(x => recoverSubmitting && x.Status == MailingJobStatus.Submitting))
        {
            task.Status = MailingJobStatus.Indeterminate;
            task.ErrorCode = "interrupted_while_submitting";
            modified = true;
        }
        if (modified && recoverSubmitting) Save(run);
        return run;
    }

    internal IReadOnlyList<MailingRunRecord> ListRecent(int maxCount = 100,
        bool recoverSubmitting = true)
    {
        if (!Directory.Exists(_directory)) return [];
        return Directory.EnumerateFiles(_directory, "*.run")
            .OrderByDescending(File.GetLastWriteTimeUtc)
            .Take(Math.Clamp(maxCount, 1, 1000))
            .Select(path => Load(Path.GetFileNameWithoutExtension(path), recoverSubmitting))
            .OrderByDescending(x => x.CreatedAtUtc)
            .ToArray();
    }

    private string GetPath(string id)
    {
        if (!Guid.TryParseExact(id, "N", out _))
            throw new InvalidDataException("Некорректный идентификатор запуска рассылки.");
        return Path.Combine(_directory, id + ".run");
    }

    private static void Validate(MailingRunRecord run)
    {
        if (run.Jobs is null || run.Jobs.Count == 0 ||
            run.Jobs.Any(x => x.RowNumber < 2 || x.MessageId.Length != 32 ||
                string.IsNullOrWhiteSpace(x.Email) || string.IsNullOrWhiteSpace(x.Sender)) ||
            run.Jobs.Select(x => x.MessageId).Distinct(StringComparer.Ordinal).Count() !=
                run.Jobs.Count ||
            run.Jobs.Select(x => x.RowNumber).Distinct().Count() != run.Jobs.Count)
            throw new InvalidDataException("Неполные или повреждённые задания журнала.");
    }
}
