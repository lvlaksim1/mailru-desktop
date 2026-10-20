using System.Globalization;
using System.Windows;
using System.Windows.Controls;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App.Mailing;

internal sealed record MailingRunHistoryItem(string Id, string Display);

public partial class ExcelMailingWindow
{
    private readonly MailingRunStore _runStore = new();
    private MailRuClient _mailRu = null!;
    private MailingRunRecord? _activeRun;
    private string? _preparedWorkbookPath;
    private string? _preparedSheetName;
    private bool _mailingExecutionRunning;
    private bool _pauseMailing;
    private bool _stopMailing;
    private bool _loadingRunHistory;

    private void RefreshRunHistory(string? selectId = null)
    {
        _loadingRunHistory = true;
        try
        {
            var runs = _runStore.ListRecent();
            RunHistoryCombo.ItemsSource = runs.Select(run =>
                new MailingRunHistoryItem(run.Id,
                    run.CreatedAtUtc.LocalDateTime.ToString("dd.MM.yyyy HH:mm",
                        CultureInfo.CurrentCulture) + " — " + run.ProfileName +
                    " (" + run.Jobs.Count + ")")).ToArray();
            RunHistoryCombo.SelectedItem =
                (RunHistoryCombo.ItemsSource as IEnumerable<MailingRunHistoryItem>)?
                .FirstOrDefault(x => x.Id == selectId);
        }
        finally { _loadingRunHistory = false; }
    }

    private void RunHistoryCombo_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_loadingRunHistory || _mailingExecutionRunning ||
            RunHistoryCombo.SelectedItem is not MailingRunHistoryItem selected)
            return;
        try
        {
            ClearCorrectionState();
            _activeRun = _runStore.Load(selected.Id);
            PreviewGrid.ItemsSource = _activeRun.Jobs.Select(ToPreviewRow).ToArray();
            StartMailingButton.IsEnabled = false;
            RunStatusText.Text = DescribeRun(_activeRun);
            StatusText.Text = "Загружен журнал рассылки. Для незавершённых заданий выберите " +
                "«Продолжить» или «Повторить запись в Excel». " +
                "Задания с неопределённым результатом автоматически не повторяются.";
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private static MailingPreparedRow ToPreviewRow(MailingJobRecord job) =>
        new(job.RowNumber, job.Email, job.Inn, job.Region, job.Organization,
            job.Sender, job.ScheduledAt, job.Subject, job.Body,
            job.Status switch
            {
                MailingJobStatus.Prepared => "Ready",
                MailingJobStatus.Submitting => "Постановка",
                MailingJobStatus.AcceptedScheduled => "Принято сервером",
                MailingJobStatus.ExcelWritePending => "Ожидает Excel",
                MailingJobStatus.CompletedRecord => "Запланировано",
                MailingJobStatus.Rejected => "Ошибка",
                MailingJobStatus.Indeterminate => "Требует проверки",
                _ => "Неизвестно"
            },
            job.ErrorCode);

    private static string DescribeRun(MailingRunRecord run) =>
        "Запуск: " + run.ProfileName +
        " · записано в Excel: " +
        run.Jobs.Count(x => x.Status == MailingJobStatus.CompletedRecord) +
        " · принято, ожидает Excel: " +
        run.Jobs.Count(x => x.Status == MailingJobStatus.ExcelWritePending) +
        " · не начато: " +
        run.Jobs.Count(x => x.Status == MailingJobStatus.Prepared) +
        " · неопределённо: " +
        run.Jobs.Count(x => x.Status == MailingJobStatus.Indeterminate) +
        " · отказов/ошибок: " +
        run.Jobs.Count(x => x.Status == MailingJobStatus.Rejected);

    private void PublishRunProgress(MailingRunRecord run, MailingJobRecord job)
    {
        var rows = (PreviewGrid.ItemsSource as IEnumerable<MailingPreparedRow>)?.ToList();
        if (rows is not null)
        {
            var index = rows.FindIndex(x => x.RowNumber == job.RowNumber &&
                string.Equals(x.Email, job.Email, StringComparison.OrdinalIgnoreCase));
            if (index >= 0)
            {
                rows[index] = ToPreviewRow(job);
                var selectedNumber = (PreviewGrid.SelectedItem as MailingPreparedRow)?.RowNumber;
                PreviewGrid.ItemsSource = rows;
                PreviewGrid.SelectedItem = rows.FirstOrDefault(x =>
                    x.RowNumber == selectedNumber);
            }
        }
        RunStatusText.Text = DescribeRun(run);
    }

    private OpenExcelBook RequireCurrentWorkbook(MailingRunRecord run)
    {
        if (BookCombo.SelectedItem is not OpenExcelBook book ||
            SheetCombo.SelectedItem is not string sheet ||
            !string.Equals(book.FullName, run.WorkbookPath,
                StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(sheet, run.SheetName, StringComparison.OrdinalIgnoreCase))
            throw new InvalidOperationException(
                "Выберите исходную открытую книгу и лист рассылки. " +
                "Повторно обнаружьте книгу, если Excel был закрыт.");
        return book;
    }

    private async Task<MailRuCommandResult> SubmitOneAsync(MailingJobRecord job)
    {
        if (!_authorizations.TryRestore(job.Sender, out var auth) ||
            string.IsNullOrWhiteSpace(auth?.AccessToken))
            throw new InvalidOperationException("Отсутствует авторизация выбранного аккаунта.");

        // The exact same Markdown rendering is used in preview and the
        // server payload. Text is readable without tags; HTML keeps styles.
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

    private async Task ExecuteRunAsync(MailingRunRecord run)
    {
        if (_mailingExecutionRunning) return;
        _mailingExecutionRunning = true;
        _pauseMailing = false;
        _stopMailing = false;
        StartMailingButton.IsEnabled = false;
        PauseMailingButton.IsEnabled = true;
        StopMailingButton.IsEnabled = true;
        ResumeMailingButton.IsEnabled = false;
        RetryExcelWriteButton.IsEnabled = false;
        RetryOrganizationButton.IsEnabled = false;
        ManualOrganizationButton.IsEnabled = false;
        IncludeOrganizationButton.IsEnabled = false;
        PrepareButton.IsEnabled = false;
        ProfileCombo.IsEnabled = false;
        RunHistoryCombo.IsEnabled = false;
        try
        {
            RequireCurrentWorkbook(run); // avoid any POST without source book
            var engine = new MailingSubmissionEngine(_runStore);
            await engine.ExecuteAsync(run, SubmitOneAsync,
                job => WriteOneExcelAsync(run, job),
                () => _pauseMailing || _stopMailing || !IsVisible,
                job => PublishRunProgress(run, job));
            RunStatusText.Text = DescribeRun(run);
            StatusText.Text = _stopMailing
                ? "Постановка новых писем остановлена. Принятые сервером письма не отменялись."
                : _pauseMailing
                    ? "Пауза. Новые задания не ставятся на сервер."
                    : run.Jobs.Any(x => x.Status == MailingJobStatus.ExcelWritePending)
                        ? "Запись в Excel не завершена. Проверьте книгу и повторите запись."
                        : "Текущий проход завершён. Проверьте состояние каждой строки.";
        }
        catch (Exception ex) { ShowError(ex); }
        finally
        {
            _mailingExecutionRunning = false;
            PauseMailingButton.IsEnabled = false;
            StopMailingButton.IsEnabled = false;
            ResumeMailingButton.IsEnabled = true;
            RetryExcelWriteButton.IsEnabled = true;
            RetryOrganizationButton.IsEnabled = true;
            ManualOrganizationButton.IsEnabled = true;
            IncludeOrganizationButton.IsEnabled = true;
            PrepareButton.IsEnabled = true;
            ProfileCombo.IsEnabled = true;
            RunHistoryCombo.IsEnabled = true;
            RefreshRunHistory(run.Id);
        }
    }

    private async void StartMailing_Click(object sender, RoutedEventArgs e)
    {
        if (_mailingExecutionRunning || _editingPreparedRow ||
            _preparationCancellation is not null ||
            _preparedProfile is null || _preparedWorkbookPath is null ||
            _preparedSheetName is null ||
            PreviewGrid.ItemsSource is not IEnumerable<MailingPreparedRow> preview)
            return;
        var ready = preview.Where(x => x.Status == "Ready").ToArray();
        if (ready.Length == 0)
        {
            StatusText.Text = "Нет готовых писем для постановки.";
            return;
        }
        if (BookCombo.SelectedItem is not OpenExcelBook book ||
            SheetCombo.SelectedItem is not string sheet ||
            !string.Equals(book.FullName, _preparedWorkbookPath,
                StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(sheet, _preparedSheetName, StringComparison.OrdinalIgnoreCase))
        {
            StatusText.Text = "Подготовленная книга или лист изменились. Повторите проверку.";
            return;
        }

        if (MessageBox.Show(this,
                $"Поставить на отложенную отправку {ready.Length} писем?\n" +
                "Письма будут переданы Mail.ru. Остановка не отменяет принятые задания.",
                "Подтверждение запуска рассылки",
                MessageBoxButton.YesNo, MessageBoxImage.Warning) != MessageBoxResult.Yes)
            return;

        try
        {
            _activeRun = _runStore.Create(_preparedProfile, book.FullName, sheet, ready);
            PreviewGrid.ItemsSource = _activeRun.Jobs.Select(ToPreviewRow).ToArray();
            await ExecuteRunAsync(_activeRun);
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private void PauseMailing_Click(object sender, RoutedEventArgs e)
    {
        _pauseMailing = true;
        StatusText.Text = "Пауза будет выполнена после текущей серверной операции.";
    }

    private void StopMailing_Click(object sender, RoutedEventArgs e)
    {
        _stopMailing = true;
        StatusText.Text = "Остановка после текущей операции. Уже принятые письма остаются в расписании.";
    }

    private async void ResumeMailing_Click(object sender, RoutedEventArgs e)
    {
        if (_mailingExecutionRunning || _activeRun is null) return;
        try
        {
            _activeRun = _runStore.Load(_activeRun.Id);
            if (!_activeRun.Jobs.Any(x => x.Status is MailingJobStatus.Prepared or
                    MailingJobStatus.ExcelWritePending))
            {
                StatusText.Text = "Нет заданий для продолжения.";
                return;
            }
            if (MessageBox.Show(this,
                    "Продолжить незавершённые задания этого запуска? " +
                    "Подтверждённые и неопределённые операции повторяться не будут.",
                    "Продолжение рассылки",
                    MessageBoxButton.YesNo, MessageBoxImage.Question) != MessageBoxResult.Yes)
                return;
            await ExecuteRunAsync(_activeRun);
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private async void RetryExcelWrite_Click(object sender, RoutedEventArgs e)
    {
        if (_mailingExecutionRunning || _activeRun is null) return;
        RetryExcelWriteButton.IsEnabled = false;
        try
        {
            var run = _runStore.Load(_activeRun.Id);
            RequireCurrentWorkbook(run);
            foreach (var job in run.Jobs.Where(x =>
                         x.Status == MailingJobStatus.ExcelWritePending))
            {
                await WriteOneExcelAsync(run, job);
                job.ExcelWritten = true;
                job.Status = MailingJobStatus.CompletedRecord;
                job.ErrorCode = "";
                _runStore.Save(run);
                PublishRunProgress(run, job);
            }
            _activeRun = run;
            RunStatusText.Text = DescribeRun(run);
            StatusText.Text = "Подтверждённые сервером задания записаны в Excel. " +
                "Сохраните книгу средствами Excel.";
        }
        catch (Exception ex) { ShowError(ex); }
        finally { RetryExcelWriteButton.IsEnabled = true; }
    }
}
