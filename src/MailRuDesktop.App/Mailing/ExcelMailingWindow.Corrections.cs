using System.Windows;
using System.Windows.Controls;

namespace MailRuDesktop.App.Mailing;

/// <summary>Only the current preview changes; no Excel writes or mail sends.</summary>
public partial class ExcelMailingWindow
{
    private readonly List<MailingExcelRow> _preparedSourceRows = [];
    private readonly Dictionary<string, string?> _preparedNames = new(StringComparer.Ordinal);
    private readonly Dictionary<int, string> _pendingOrganizations = [];
    private readonly Dictionary<int, string> _approvedOrganizations = [];
    private MailingProfile? _preparedProfile;
    private string? _preparedTemplate;
    private HashSet<string>? _preparedExclusions;
    private HashSet<string>? _preparedAuthorized;
    private DateTimeOffset _lastOrganizationRequestAt;
    private bool _editingPreparedRow;

    private void ClearCorrectionState()
    {
        _preparedSourceRows.Clear();
        _preparedNames.Clear();
        _pendingOrganizations.Clear();
        _approvedOrganizations.Clear();
        _preparedProfile = null;
        _preparedTemplate = null;
        _preparedExclusions = null;
        _preparedAuthorized = null;
        _preparedWorkbookPath = null;
        _preparedSheetName = null;
        StartMailingButton.IsEnabled = false;
    }

    private void CapturePreparationSettings(MailingProfile profile, string template,
        HashSet<string> excluded, HashSet<string> authorized)
    {
        _preparedProfile = profile;
        _preparedTemplate = template;
        _preparedExclusions = excluded;
        _preparedAuthorized = authorized;
    }

    private bool TryGetCorrectionRow(out MailingPreparedRow selected,
        out MailingExcelRow source)
    {
        selected = null!;
        source = null!;
        if (_preparationCancellation is not null || _editingPreparedRow ||
            _preparedProfile is null || _preparedTemplate is null ||
            PreviewGrid.SelectedItem is not MailingPreparedRow row)
        {
            StatusText.Text = "Сначала завершите подготовку и выделите одну строку.";
            return false;
        }
        var original = _preparedSourceRows.FirstOrDefault(x => x.RowNumber == row.RowNumber);
        if (original is null || !original.Visible ||
            !string.IsNullOrWhiteSpace(original.ExistingMark) ||
            !MailingPlanner.IsValidInn(original.Inn) ||
            !MailingPlanner.IsValidEmail(original.Email) ||
            string.IsNullOrWhiteSpace(original.Region) ||
            _preparedExclusions?.Contains(original.Email.Trim()) == true)
        {
            StatusText.Text = "Эту запись нельзя вернуть: проверьте ИНН, адрес, регион, отметки и исключения.";
            return false;
        }
        selected = row;
        source = original;
        return true;
    }

    private async void RetryOrganization_Click(object sender, RoutedEventArgs e)
    {
        if (!TryGetCorrectionRow(out var selected, out var source)) return;
        _editingPreparedRow = true;
        RetryOrganizationButton.IsEnabled = false;
        ManualOrganizationButton.IsEnabled = false;
        IncludeOrganizationButton.IsEnabled = false;
        try
        {
            var remaining = TimeSpan.FromSeconds(5) -
                (DateTimeOffset.UtcNow - _lastOrganizationRequestAt);
            if (remaining > TimeSpan.Zero) await Task.Delay(remaining);
            _lastOrganizationRequestAt = DateTimeOffset.UtcNow;
            StatusText.Text = "Повторная проверка организации по ИНН " + source.Inn + "...";
            // New service instance: never reuse the previous preparation cache.
            using var lookup = new OrganizationLookupService();
            var name = await lookup.ResolveAsync(source.Inn);
            if (string.IsNullOrWhiteSpace(name))
            {
                StatusText.Text = "Название не подтверждено. Можно указать его вручную.";
                return;
            }
            StageOrganizationName(selected, name, manual: false);
        }
        catch (Exception ex)
        {
            StatusText.Text = "Ошибка повторной проверки: " + ex.Message;
            DiagnosticLog.Write("mailing_org_retry", ex.GetType().Name);
        }
        finally
        {
            _editingPreparedRow = false;
            RetryOrganizationButton.IsEnabled = true;
            ManualOrganizationButton.IsEnabled = true;
            IncludeOrganizationButton.IsEnabled = true;
        }
    }

    private void ManualOrganization_Click(object sender, RoutedEventArgs e)
    {
        if (!TryGetCorrectionRow(out var selected, out var source)) return;
        var dialog = new TextPromptWindow(this, "Организация: ИНН " + source.Inn,
            "Подтверждённое пользователем название",
            _pendingOrganizations.GetValueOrDefault(source.RowNumber) ?? selected.Organization);
        if (dialog.ShowDialog() != true) return;
        if (string.IsNullOrWhiteSpace(dialog.Value))
        {
            StatusText.Text = "Название не может быть пустым.";
            return;
        }
        StageOrganizationName(selected, dialog.Value.Trim(), manual: true);
    }

    private void StageOrganizationName(MailingPreparedRow selected, string name, bool manual)
    {
        _pendingOrganizations[selected.RowNumber] = name;
        var rows = (PreviewGrid.ItemsSource as IEnumerable<MailingPreparedRow>)?.ToList();
        if (rows is null) return;
        var at = rows.FindIndex(x => x.RowNumber == selected.RowNumber);
        if (at < 0) return;
        rows[at] = rows[at] with
        {
            Organization = name,
            Sender = "",
            ScheduledAt = null,
            Subject = "",
            Body = "",
            Status = "Требует проверки",
            Reason = manual
                ? "Название указано вручную. Нажмите «Вернуть в рассылку»."
                : "Название подтверждено по ИНН. Нажмите «Вернуть в рассылку»."
        };
        PreviewGrid.ItemsSource = rows;
        PreviewGrid.SelectedItem = rows[at];
        UpdateCorrectionSummary(rows);
        StartMailingButton.IsEnabled = rows.Any(x => x.Status == "Ready");
        StatusText.Text = "Название записано в предпросмотр. Для включения нажмите «Вернуть в рассылку».";
    }

    private void IncludeOrganization_Click(object sender, RoutedEventArgs e)
    {
        if (!TryGetCorrectionRow(out _, out var source)) return;
        if (!_pendingOrganizations.TryGetValue(source.RowNumber, out var name) ||
            string.IsNullOrWhiteSpace(name))
        {
            StatusText.Text = "Сначала повторите поиск или укажите название вручную.";
            return;
        }
        var previous = _approvedOrganizations.GetValueOrDefault(source.RowNumber);
        _approvedOrganizations[source.RowNumber] = name;
        try
        {
            var session = MailingPlanner.CreateSession(_preparedProfile!, _preparedTemplate!,
                _preparedExclusions!, _preparedAuthorized!);
            foreach (var row in _preparedSourceRows)
            {
                var candidate = _approvedOrganizations.TryGetValue(row.RowNumber, out var approved)
                    ? approved : _preparedNames.GetValueOrDefault(row.Inn);
                session.Accept(row, candidate);
            }
            _pendingOrganizations.Remove(source.RowNumber);
            var items = session.Result.Rows;
            PreviewGrid.ItemsSource = items;
            PreviewGrid.SelectedItem = items.FirstOrDefault(x => x.RowNumber == source.RowNumber);
            UpdateCorrectionSummary(items);
            StartMailingButton.IsEnabled = items.Any(x => x.Status == "Ready");
            var updated = items.First(x => x.RowNumber == source.RowNumber);
            StatusText.Text = updated.Status == "Ready"
                ? "Запись возвращена в рассылку. Квоты и время пересчитаны по порядку строк. " +
                  "При заполненной квоте последняя запись может стать сверх квоты."
                : "Название принято, но запись пока не входит в рассылку: " + updated.Reason;
        }
        catch (Exception ex)
        {
            if (previous is null) _approvedOrganizations.Remove(source.RowNumber);
            else _approvedOrganizations[source.RowNumber] = previous;
            StatusText.Text = "Не удалось пересчитать рассылку: " + ex.Message;
            DiagnosticLog.Write("mailing_org_include", ex.GetType().Name);
        }
    }

    private void UpdateCorrectionSummary(IReadOnlyList<MailingPreparedRow> rows)
    {
        SummaryText.Text = "Готово: " + rows.Count(x => x.Status == "Ready") +
            " · Требует проверки: " + rows.Count(x => x.Status == "Требует проверки") +
            " · Пропущено: " + rows.Count(x => x.Status == "Пропуск");
    }
}
