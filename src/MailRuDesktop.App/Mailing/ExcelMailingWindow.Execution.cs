using System.Globalization;
using System.Threading;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App.Mailing;

public partial class ExcelMailingWindow
{
    private readonly MailingRunStore _runStore = new();
    private MailRuClient _mailRu = null!;
    private CancellationTokenSource? _sendCancellation;
    private bool _mailingExecutionRunning;
    private bool _stopMailing;
    private static int _activeDirectMailing;

    private OpenExcelBook RequireCurrentWorkbook(MailingRunRecord run)
    {
        if (BookCombo.SelectedItem is not OpenExcelBook book ||
            SheetCombo.SelectedItem is not string sheet ||
            !string.Equals(book.FullName, run.WorkbookPath,
                StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(sheet, run.SheetName, StringComparison.OrdinalIgnoreCase))
            throw new InvalidOperationException(
                "Исходная книга или лист рассылки изменились. Выберите прежнюю книгу.");
        return book;
    }

    private async Task<MailRuCommandResult> SubmitOneAsync(MailingJobRecord job)
    {
        if (!_authorizations.TryRestore(job.Sender, out var auth) ||
            string.IsNullOrWhiteSpace(auth?.AccessToken))
            throw new InvalidOperationException("Отсутствует авторизация выбранного аккаунта.");

        var rendered = MailingMarkdownFormatting.Render(job.Body);
        return await _mailRu.SendMessageAsync(auth.AccessToken,
            new MailRuOutgoingMessage(
                To: job.Email,
                Subject: job.Subject,
                Text: rendered.PlainText,
                Html: rendered.Html,
                SendDate: job.ScheduledAt.ToUnixTimeSeconds()
                    .ToString(CultureInfo.InvariantCulture),
                MessageId: job.MessageId));
    }

    private Task WriteOneExcelAsync(MailingRunRecord run, MailingJobRecord job)
    {
        ExcelInteropService.WriteConfirmedResult(
            RequireCurrentWorkbook(run), run.SheetName, job);
        return Task.CompletedTask;
    }

    private async void Send_Click(object sender, RoutedEventArgs e)
    {
        // Several windows may exist, but only one can submit a mailing.
        if (Interlocked.CompareExchange(ref _activeDirectMailing, 1, 0) != 0)
        {
            StatusText.Text = "Другая рассылка уже выполняется.";
            return;
        }

        var accepted = 0;
        var rejected = 0;
        var skipped = 0;
        var previouslyScheduled = 0;
        var employerFallbacks = 0;
        var stoppedForUncertain = false;
        try
        {
            var profile = SnapshotProfile();
            if (BookCombo.SelectedItem is not OpenExcelBook book ||
                SheetCombo.SelectedItem is not string sheet)
                throw new InvalidOperationException("Выберите открытую книгу Excel и лист.");
            if (book.FullName == book.Name)
                throw new InvalidOperationException("Сначала сохраните книгу в Excel.");
            var template = _templates.FirstOrDefault(x => x.Name == profile.TemplateName)
                ?? throw new InvalidOperationException("Выберите существующий шаблон.");
            var reader = ExcelInteropService.OpenReader(book, sheet);
            var excluded = _store.ReadExclusions();
            var authorized = _authorizations.Logins.ToHashSet(
                StringComparer.OrdinalIgnoreCase);
            // No intermediate table: one prepared result is returned at a time.
            var session = MailingPlanner.CreateSession(profile, template.Body,
                excluded, authorized, collectRows: false);
            var priorRuns = _runStore.ForWorkbook(book.FullName, sheet);
            var reserved = priorRuns.SelectMany(x => x.Jobs)
                .Where(x => x.Status != MailingJobStatus.Rejected)
                .Select(x => MailingRunStore.RowKey(x.RowNumber, x.Email, x.Inn))
                .ToHashSet(StringComparer.Ordinal);
            _mailingExecutionRunning = true;
            _stopMailing = false;
            _sendCancellation = new CancellationTokenSource();
            var cancellation = _sendCancellation.Token;
            SendButton.IsEnabled = false;
            FindBooksButton.IsEnabled = false;
            BookCombo.IsEnabled = false;
            SheetCombo.IsEnabled = false;
            ProfileControls.IsEnabled = false;
            OptionsBorder.IsEnabled = false;
            var engine = new MailingSubmissionEngine(_runStore);

            // When the server accepted a previous letter but Excel was unavailable,
            // recover the two Excel marks without any repeated server POST.
            foreach (var previous in priorRuns)
            {
                foreach (var old in previous.Jobs.Where(x =>
                             x.Status == MailingJobStatus.ExcelWritePending))
                {
                    cancellation.ThrowIfCancellationRequested();
                    await WriteOneExcelAsync(previous, old);
                    old.ExcelWritten = true;
                    old.Status = MailingJobStatus.CompletedRecord;
                    old.ErrorCode = "";
                    _runStore.Save(previous);
                }
            }

            MailingRunRecord? run = null;
            var names = new Dictionary<string, string?>(StringComparer.Ordinal);
            using var lookup = new OrganizationLookupService();
            var mustStop = false;
            while (reader.HasMore && !session.IsFull && !mustStop)
            {
                cancellation.ThrowIfCancellationRequested();
                if (_stopMailing || !IsVisible) break;
                var rows = reader.ReadNext(cancellation);
                foreach (var row in rows)
                {
                    cancellation.ThrowIfCancellationRequested();
                    if (_stopMailing || !IsVisible || session.IsFull) break;

                    var key = MailingRunStore.RowKey(row.RowNumber, row.Email, row.Inn);
                    if (session.NeedsOrganization(row) && reserved.Contains(key))
                    {
                        previouslyScheduled++;
                        continue;
                    }

                    string? name = null;
                    if (session.NeedsOrganization(row))
                    {
                        if (!names.TryGetValue(row.Inn, out name))
                        {
                            StatusText.Text =
                                $"Строка {row.RowNumber}: поиск организации по ИНН {row.Inn}. " +
                                $"Принято Mail.ru: {accepted}; квота {session.ReadyCount}/{session.Quota}.";
                            try
                            {
                                name = await lookup.ResolveAsync(row.Inn, cancellation);
                            }
                            catch (OperationCanceledException) { throw; }
                            catch (Exception ex)
                            {
                                DiagnosticLog.Write("mailing_org_lookup",
                                    ex.GetType().Name + ": " + ex.Message);
                            }
                            names[row.Inn] = name;
                        }
                        if (string.IsNullOrWhiteSpace(name) &&
                            !string.IsNullOrWhiteSpace(row.EmployerName))
                            employerFallbacks++;
                    }

                    var planned = session.Accept(row, name);
                    if (planned.Status != "Ready")
                    {
                        skipped++;
                        continue;
                    }
                    MailingJobRecord job;
                    if (run is null)
                    {
                        run = _runStore.Create(profile, book.FullName, sheet, [planned]);
                        job = run.Jobs.Single();
                    }
                    else job = _runStore.Append(run, planned);
                    // Reservation is durable BEFORE a single network request.
                    reserved.Add(key);
                    StatusText.Text =
                        $"Строка {row.RowNumber}: постановка письма {session.ReadyCount}/{session.Quota}. " +
                        $"Принято Mail.ru: {accepted}.";
                    await engine.ExecuteAsync(run, SubmitOneAsync,
                        current => WriteOneExcelAsync(run, current),
                        () => cancellation.IsCancellationRequested ||
                              _stopMailing || !IsVisible);
                    if (job.Status == MailingJobStatus.CompletedRecord)
                        accepted++;
                    else if (job.Status == MailingJobStatus.Rejected)
                        rejected++;
                    else
                    {
                        // Unknown reply or Excel write failure: the journal
                        // protects this row, but do not submit further letters.
                        stoppedForUncertain = true;
                        mustStop = true;
                        break;
                    }
                }
                if (!mustStop && !_stopMailing && IsVisible)
                    await Task.Yield(); // Excel COM stays on this WPF STA thread
            }

            StatusText.Text = stoppedForUncertain
                ? $"Отправка приостановлена: неопределённый ответ Mail.ru либо ошибка записи Excel. " +
                  $"Принято и отмечено: {accepted}, отказов: {rejected}. " +
                  "Повторная постановка спорного письма исключена журналом."
                : $"Проход завершён. Принято и отмечено в Excel: {accepted}; " +
                  $"отказов: {rejected}; пропущено: {skipped}; " +
                  $"ранее учтено журналом: {previouslyScheduled}; " +
                  $"названий взято из Excel: {employerFallbacks}. " +
                  "Сохраните книгу средствами Excel.";
        }
        catch (OperationCanceledException)
        {
            StatusText.Text =
                $"Обработка прервана. Ранее принятые Mail.ru задания не отменены. " +
                $"Подтверждено: {accepted}; неопределённые результаты повторяться не будут.";
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("excel_mailing_direct_send",
                ex.GetType().Name + ": " + ex.Message);
            StatusText.Text =
                $"Ошибка: {ex.Message}. Уже подтверждённые отправки: {accepted}. " +
                "Проверьте журнал и отметки Excel перед следующим запуском.";
        }
        finally
        {
            _mailingExecutionRunning = false;
            _sendCancellation?.Dispose();
            _sendCancellation = null;
            SendButton.IsEnabled = true;
            FindBooksButton.IsEnabled = true;
            BookCombo.IsEnabled = true;
            SheetCombo.IsEnabled = true;
            ProfileControls.IsEnabled = true;
            OptionsBorder.IsEnabled = true;
            Interlocked.Exchange(ref _activeDirectMailing, 0);
        }
    }
}
