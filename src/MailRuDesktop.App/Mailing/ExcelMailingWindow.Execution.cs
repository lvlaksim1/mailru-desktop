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

        // Build the body before reserving capacity; a local formatting
        // failure must not leave an unsent message consuming a quota slot.
        var rendered = MailingMarkdownFormatting.Render(job.Body);
        if (!senderQuotas.TryGetValue(job.Sender, out var quota) ||
            !_senderCounters.TryReserve(job.Sender, job.MessageId, quota))
            return new MailRuCommandResult(false,
                "{\"status\":400,\"body\":{\"error\":\"sender_quota_reached\"}}");

        // Both POSTs (when token recovery is required) must use the same
        // scheduled message and MessageId, under ONE reserved quota slot.
        var outgoing = new MailRuOutgoingMessage(
            To: job.Email,
            Subject: job.Subject,
            Text: rendered.PlainText,
            Html: rendered.Html,
            SendDate: job.ScheduledAt.ToUnixTimeSeconds()
                .ToString(CultureInfo.InvariantCulture),
            MessageId: job.MessageId);
        var result = await MailingSubmissionEngine.SubmitWithTokenRecoveryAsync(
            auth.AccessToken,
            token => _mailRu.SendMessageAsync(token, outgoing),
            () => RecoverMailingAccessTokenAsync(job.Sender, auth.AccessToken),
            stage => AppendRunLog($"Строка {job.RowNumber}, аккаунт {job.Sender}: " +
                (stage switch
                {
                    "token_rejected" => "Mail.ru отклонил токен при отправке; " +
                        "проверяем автоматическое обновление.",
                    "retry_with_verified_token" => "Новый токен проверен; " +
                        "повторяем постановку того же письма без нового расхода квоты.",
                    "refresh_unavailable" => "Не удалось получить новый подтверждённый токен; " +
                        "рассылка будет остановлена.",
                    _ => "Ошибка обновления токена (" + stage + "); " +
                        "рассылка будет остановлена."
                })));
        if (result.Success)
            _senderCounters.ConfirmReserved(job.Sender, job.MessageId);
        else if (MailingSubmissionEngine.IsExplicitRejection(result.RawResponse))
            _senderCounters.ReleaseRejected(job.MessageId);
        // If the server response is uncertain, retain the durable quota slot.
        return result;
    }

    private async Task<string?> RecoverMailingAccessTokenAsync(
        string login, string rejectedAccessToken)
    {
        if (!_authorizations.TryRestore(login, out var stored) || stored is null)
            return null;

        // A background folder refresh can update this shared authorization
        // store while the scheduling request is in flight.
        if (!string.Equals(stored.AccessToken, rejectedAccessToken,
                StringComparison.Ordinal))
        {
            if (string.IsNullOrWhiteSpace(stored.AccessToken))
                return null;
            await _mailRu.GetFolderThreadsAsync(stored.AccessToken, 0, limit: 1);
            AppendRunLog($"Аккаунт {login}: уже обновлённый токен подтверждён чтением почты.");
            return stored.AccessToken;
        }

        if (string.IsNullOrWhiteSpace(stored.RefreshToken))
        {
            AppendRunLog($"Аккаунт {login}: отсутствует сохранённый refresh_token.");
            return null;
        }

        var refresh = await _mailRu.RefreshAccessTokenAsync(stored.RefreshToken);
        if (!refresh.Success || string.IsNullOrWhiteSpace(refresh.AccessToken))
        {
            AppendRunLog($"Аккаунт {login}: обновление токена отклонено; " +
                $"HTTP {refresh.HttpStatusCode}.");
            return null;
        }

        // A fresh token is not trusted until the server accepts a read-only
        // folder request; never POST mail with an unverified token.
        await _mailRu.GetFolderThreadsAsync(refresh.AccessToken, 0, limit: 1);
        var replacementRefresh = refresh.RefreshToken ?? stored.RefreshToken;
        if (_authorizations.UpdateTokensIfCurrent(
                login, rejectedAccessToken, refresh.AccessToken, replacementRefresh))
        {
            if (Owner is MainWindow main)
                main.ApplyVerifiedMailingToken(
                    login, rejectedAccessToken, refresh.AccessToken, replacementRefresh);
            AppendRunLog($"Аккаунт {login}: токен обновлён, проверен и сохранён.");
            return refresh.AccessToken;
        }

        // A newer background/manual refresh won the race. Never overwrite it
        // with tokens from this older operation.
        if (_authorizations.TryRestore(login, out var current) &&
            current is not null &&
            !string.IsNullOrWhiteSpace(current.AccessToken) &&
            !string.Equals(current.AccessToken, rejectedAccessToken,
                StringComparison.Ordinal))
        {
            await _mailRu.GetFolderThreadsAsync(current.AccessToken, 0, limit: 1);
            AppendRunLog($"Аккаунт {login}: используется более новый подтверждённый токен.");
            return current.AccessToken;
        }
        return null;
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
                foreach (var confirmed in previous.Jobs.Where(x =>
                             x.ConfirmedAtUtc is not null))
                {
                    if (_senderCounters.ReconcileConfirmed(
                            confirmed.Sender, confirmed.MessageId))
                    {
                        var senderRow = _senders.FirstOrDefault(x =>
                            string.Equals(x.Login, confirmed.Sender,
                                StringComparison.OrdinalIgnoreCase));
                        if (senderRow is not null)
                            senderRow.SentCount = _senderCounters.Get(confirmed.Sender);
                    }
                }
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
                                var result = await lookup.ResolveWithNetworkRetryAsync(
                                    row.Inn,
                                    message => AppendRunLog($"Строка {row.RowNumber}: {message}"),
                                    cancellation);
                                name = result.Name;
                                if (result.NetworkFallback)
                                {
                                    if (string.IsNullOrWhiteSpace(row.EmployerName))
                                        throw new InvalidOperationException(
                                            "Справочник недоступен после двух запросов, " +
                                            "а столбец «Наименование работодателя» в Excel пуст.");
                                    // Do not cache an unverified worksheet label by INN.
                                    // Each affected row retains its own employer name.
                                }
                                else
                                    names[row.Inn] = name;
                            }
                            catch (OperationCanceledException) { throw; }
                            catch (Exception ex)
                            {
                                throw new InvalidOperationException(
                                    $"Ошибка определения организации по ИНН {row.Inn}: {ex.Message}", ex);
                            }
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
                    else if (MailingSubmissionEngine.IsSkippableRecipientRefusal(job))
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
