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

    private async Task<MailRuCommandResult> SubmitOneAsync(
        MailingJobRecord job, IReadOnlyDictionary<string, int> senderQuotas)
    {
        if (!_authorizations.TryRestore(job.Sender, out var auth) ||
            string.IsNullOrWhiteSpace(auth?.AccessToken))
            throw new InvalidOperationException("Отсутствует авторизация выбранного аккаунта.");

        if (!senderQuotas.TryGetValue(job.Sender, out var quota) ||
            !_senderCounters.TryReserve(job.Sender, job.MessageId, quota))
            return new MailRuCommandResult(false,
                "{\"status\":400,\"body\":{\"error\":\"sender_quota_reached\"}}");

        var rendered = MailingMarkdownFormatting.Render(job.Body);
        var result = await _mailRu.SendMessageAsync(auth.AccessToken,
            new MailRuOutgoingMessage(
                To: job.Email,
                Subject: job.Subject,
                Text: rendered.PlainText,
                Html: rendered.Html,
                SendDate: job.ScheduledAt.ToUnixTimeSeconds()
                    .ToString(CultureInfo.InvariantCulture),
                MessageId: job.MessageId));
        if (result.Success)
            _senderCounters.ConfirmReserved(job.Sender, job.MessageId);
        else if (MailingSubmissionEngine.IsExplicitRejection(result.RawResponse))
            _senderCounters.ReleaseRejected(job.MessageId);
        // If the server response is uncertain, retain the durable quota slot.
        return result;
    }

    private Task WriteOneExcelAsync(MailingRunRecord run, MailingJobRecord job)
    {
        ExcelInteropService.WriteConfirmedResult(
            RequireCurrentWorkbook(run), run.SheetName, job);
        return Task.CompletedTask;
    }

    private void OpenJournal_Click(object sender, RoutedEventArgs e)
    {
        if (Volatile.Read(ref _activeDirectMailing) != 0)
        {
            StatusText.Text = "Во время рассылки изменение журнала недоступно.";
            return;
        }
        var editor = new MailingJournalWindow(this, _runStore);
        editor.ShowDialog();
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
        var currentRow = 0;
        var currentSender = "";
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
            var authorized = _authorizations.Logins
                .Where(login => _authorizations.TryRestore(login, out var auth) &&
                    !string.IsNullOrWhiteSpace(auth?.AccessToken))
                .ToHashSet(StringComparer.OrdinalIgnoreCase);
            // No intermediate table: one prepared result is returned at a time.
            var senderQuotas = profile.Senders.ToDictionary(
                x => x.Login, x => x.Quantity, StringComparer.OrdinalIgnoreCase);
            var usedCounts = profile.Senders.ToDictionary(
                x => x.Login, x => _senderCounters.GetUsed(x.Login),
                StringComparer.OrdinalIgnoreCase);
            var session = MailingPlanner.CreateSession(profile, template.Body,
                excluded, authorized, collectRows: false,
                usedSenderCounts: usedCounts);
            // Checkbox applies to this entire send, not only the UI display.
            // Disabled means no journal access, no DPAPI files, no recovery.
            var journalEnabled = profile.JournalEnabled;
            var priorRuns = journalEnabled
                ? _runStore.ForWorkbook(book.FullName, sheet)
                : [];
            var reserved = priorRuns.SelectMany(x => x.Jobs)
                .Where(x => x.Status != MailingJobStatus.Rejected)
                .Select(x => MailingRunStore.RowKey(x.RowNumber, x.Email, x.Inn))
                .ToHashSet(StringComparer.Ordinal);
            _stopMailing = false;
            _sendCancellation = new CancellationTokenSource();
            ExecutionLogTextBox.Clear();
            AppendRunLog($"Начало рассылки. Книга: {book.Name}; лист: {sheet}; дата: {profile.StartDate:dd.MM.yyyy}.");
            var cancellation = _sendCancellation.Token;
            SendButton.IsEnabled = false;
            StopButton.IsEnabled = true;
            ResetSenderCountersButton.IsEnabled = false;
            BookCombo.IsEnabled = false;
            SheetCombo.IsEnabled = false;
            ProfileControls.IsEnabled = false;
            OptionsBorder.IsEnabled = false;
            OpenJournalButton.IsEnabled = false;
            JournalEnabledCheck.IsEnabled = false;
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
                    AppendRunLog($"Строка {old.RowNumber}: восстановлена отметка Excel без повторной отправки.");
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
                    currentRow = row.RowNumber;
                    currentSender = "";
                    reader.ActivateRow(row.RowNumber);
                    AppendRunLog($"Строка {row.RowNumber}: обработка {row.Email}.");

                    var key = MailingRunStore.RowKey(row.RowNumber, row.Email, row.Inn);
                    if (session.NeedsOrganization(row) && reserved.Contains(key))
                    {
                        previouslyScheduled++;
                        AppendRunLog($"Строка {row.RowNumber}: пропущена — уже учтена журналом.");
                        continue;
                    }

                    string? name = null;
                    if (session.NeedsOrganization(row))
                    {
                        if (!names.TryGetValue(row.Inn, out name))
                        {
                            AppendRunLog($"Строка {row.RowNumber}: определение названия по ИНН {row.Inn}.");
                            try
                            {
                                name = await lookup.ResolveAsync(row.Inn, cancellation);
                            }
                            catch (OperationCanceledException) { throw; }
                            catch (Exception ex)
                            {
                                throw new InvalidOperationException(
                                    $"Ошибка определения организации по ИНН {row.Inn}: {ex.Message}", ex);
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
                        AppendRunLog($"Строка {row.RowNumber}: пропущена — {planned.Reason}.");
                        continue;
                    }
                    MailingJobRecord job;
                    MailingRunRecord currentRun;
                    if (!journalEnabled)
                    {
                        // No persisted or service journal and no duplicate check.
                        currentRun = MailingRunStore.CreateUnrecorded(
                            profile, book.FullName, sheet, planned);
                        job = currentRun.Jobs.Single();
                    }
                    else
                    {
                        if (run is null)
                        {
                            run = _runStore.Create(profile, book.FullName, sheet, [planned]);
                            job = run.Jobs.Single();
                        }
                        else job = _runStore.Append(run, planned);
                        currentRun = run;
                        // Durable journal reservation BEFORE network traffic.
                        reserved.Add(key);
                    }
                    currentSender = job.Sender;
                    AppendRunLog($"Строка {row.RowNumber}: отправитель {job.Sender}; " +
                        $"постановка {session.ReadyCount}/{session.Quota} на {job.ScheduledAt:dd.MM.yyyy HH:mm:ss}.");
                    await engine.ExecuteAsync(currentRun,
                        current => SubmitOneAsync(current, senderQuotas),
                        current => WriteOneExcelAsync(currentRun, current),
                        () => cancellation.IsCancellationRequested ||
                              _stopMailing || !IsVisible,
                        persist: journalEnabled);
                    if (job.ConfirmedAtUtc is not null)
                    {
                        var counter = _senders.FirstOrDefault(x =>
                            string.Equals(x.Login, job.Sender, StringComparison.OrdinalIgnoreCase));
                        if (counter is not null)
                            counter.SentCount = _senderCounters.Get(job.Sender);
                        AppendRunLog($"Строка {row.RowNumber}: Mail.ru подтвердил постановку; " +
                            $"аккаунт {job.Sender}, всего {_senderCounters.Get(job.Sender)} " +
                            $"из квоты {senderQuotas[job.Sender]}.");
                    }
                    if (job.Status == MailingJobStatus.CompletedRecord)
                    {
                        accepted++;
                        AppendRunLog($"Строка {row.RowNumber}: данные подтверждённой отправки записаны в Excel.");
                    }
                    else if (job.Status == MailingJobStatus.Rejected &&
                             job.ErrorCode.StartsWith("recipient_", StringComparison.Ordinal))
                    {
                        rejected++;
                        session.ReleaseRecipientRefusal(job.Sender);
                        AppendRunLog($"Строка {row.RowNumber}, адрес {job.Email}, " +
                            $"аккаунт {job.Sender}: сервер отказал получателю " +
                            $"({job.ErrorCode}); письмо не поставлено в отправку. " +
                            "Квота не израсходована, продолжаем со следующей строки.");
                        continue;
                    }
                    else
                    {
                        if (job.Status == MailingJobStatus.Rejected) rejected++;
                        var message = $"Строка {row.RowNumber}, аккаунт {job.Sender}: " +
                            $"отправка остановлена. Результат {job.Status}; причина {job.ErrorCode}.";
                        AppendRunLog(message);
                        MessageBox.Show(this, message, "Ошибка рассылки",
                            MessageBoxButton.OK, MessageBoxImage.Error);
                        mustStop = true;
                        break;
                    }
                }
                if (!mustStop && !_stopMailing && IsVisible)
                    await Task.Yield(); // Excel COM stays on this WPF STA thread
            }

            if (!mustStop && !_stopMailing)
                AppendRunLog($"Проход завершён. Принято и отмечено: {accepted}; " +
                    $"отказов: {rejected}; пропущено: {skipped}; " +
                    $"ранее учтено: {previouslyScheduled}; названий из Excel: {employerFallbacks}. " +
                    "Сохраните книгу средствами Excel.");
            else if (_stopMailing)
                AppendRunLog($"Рассылка прервана. Подтверждено и отмечено: {accepted}; " +
                    "ранее принятые сервером задания не отменены.");
        }
        catch (OperationCanceledException)
        {
            AppendRunLog($"Обработка прервана пользователем. " +
                $"Ранее принятые задания не отменены. Подтверждено и отмечено: {accepted}.");
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("excel_mailing_direct_send",
                ex.GetType().Name + ": " + ex.Message);
            var detail = $"Строка {currentRow}, аккаунт " +
                $"{(currentSender.Length > 0 ? currentSender : "не выбран")}: {ex.Message}. " +
                $"Ранее подтверждено и отмечено: {accepted}.";
            AppendRunLog("ОШИБКА. " + detail);
            MessageBox.Show(this, detail, "Ошибка рассылки",
                MessageBoxButton.OK, MessageBoxImage.Error);
        }
        finally
        {
            _sendCancellation?.Dispose();
            _sendCancellation = null;
            SendButton.IsEnabled = true;
            StopButton.IsEnabled = false;
            ResetSenderCountersButton.IsEnabled = true;
            BookCombo.IsEnabled = true;
            SheetCombo.IsEnabled = true;
            ProfileControls.IsEnabled = true;
            OptionsBorder.IsEnabled = true;
            OpenJournalButton.IsEnabled = true;
            JournalEnabledCheck.IsEnabled = true;
            Interlocked.Exchange(ref _activeDirectMailing, 0);
        }
    }
}
