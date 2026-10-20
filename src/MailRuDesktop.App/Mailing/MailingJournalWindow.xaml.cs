using System.ComponentModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using MailRuDesktop.App;

namespace MailRuDesktop.App.Mailing;

internal sealed record MailingJournalChoice(string Id, string Display);

public partial class MailingJournalWindow : Window
{
    private readonly MailingRunStore _store;
    private MailingRunRecord? _run;
    private bool _loading;
    private bool _dirty;

    internal MailingJournalWindow(Window owner, MailingRunStore store)
    {
        InitializeComponent();
        Owner = owner;
        Icon = owner.Icon;
        _store = store;
        ThemeManager.AttachWindowChrome(this);
        ThemeManager.RefreshWindowChrome(this);
        StatusColumn.ItemsSource = Enum.GetValues<MailingJobStatus>();
        Closing += OnClosing;
        Reload();
    }

    private bool ConfirmDiscard()
    {
        if (!_dirty) return true;
        return MessageBox.Show(this,
            "Есть несохранённые изменения журнала. Отменить их?",
            "Журнал рассылок", MessageBoxButton.YesNo, MessageBoxImage.Warning)
            == MessageBoxResult.Yes;
    }

    private void Reload(string? selectId = null)
    {
        _loading = true;
        try
        {
            var items = _store.ListRecent(1000, recoverSubmitting: false)
                .Select(run => new MailingJournalChoice(run.Id,
                    run.CreatedAtUtc.LocalDateTime.ToString("dd.MM.yyyy HH:mm") +
                    " — " + run.ProfileName + " · " +
                    Path.GetFileName(run.WorkbookPath) + " / " + run.SheetName +
                    " (" + run.Jobs.Count + ")")).ToArray();
            RunCombo.ItemsSource = items;
            RunCombo.SelectedItem = items.FirstOrDefault(x => x.Id == selectId)
                ?? items.FirstOrDefault();
            LoadSelected();
        }
        finally { _loading = false; }
    }

    private void LoadSelected()
    {
        var entry = RunCombo.SelectedItem as MailingJournalChoice;
        _run = entry is null ? null : _store.Load(entry.Id, recoverSubmitting: false);
        JobsGrid.ItemsSource = _run?.Jobs;
        JobsGrid.SelectedIndex = _run is null ? -1 : 0;
        RunInfo.Text = _run is null ? "" :
            "Книга: " + _run.WorkbookPath + "\nЛист: " + _run.SheetName;
        _dirty = false;
        JournalStatus.Text = _run is null
            ? "Журнал пока пуст." : "Записи доступны для редактирования.";
        SetAvailability();
    }

    private void SetAvailability()
    {
        var active = _run is not null;
        JobsGrid.IsEnabled = active;
        SaveButton.IsEnabled = active;
        DeleteRunButton.IsEnabled = active;
        DeleteJobButton.IsEnabled = active;
        SubjectEditor.IsEnabled = active;
        BodyEditor.IsEnabled = active;
    }

    private void MarkDirty()
    {
        if (_loading || _run is null) return;
        _dirty = true;
        JournalStatus.Text = "● Несохранённые изменения журнала";
    }

    private void JobsGrid_CellEditEnding(object sender, DataGridCellEditEndingEventArgs e)
    {
        if (e.EditAction == DataGridEditAction.Commit) MarkDirty();
    }

    private void EditorTextChanged(object sender, TextChangedEventArgs e) => MarkDirty();

    private void RunCombo_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_loading) return;
        var previous = _run?.Id;
        if (!ConfirmDiscard())
        {
            _loading = true;
            RunCombo.SelectedItem =
                (RunCombo.ItemsSource as IEnumerable<MailingJournalChoice>)?
                .FirstOrDefault(x => x.Id == previous);
            _loading = false;
            return;
        }
        try { LoadSelected(); }
        catch (Exception ex) { Report(ex); }
    }

    private void Refresh_Click(object sender, RoutedEventArgs e)
    {
        if (!ConfirmDiscard()) return;
        try { Reload(_run?.Id); }
        catch (Exception ex) { Report(ex); }
    }

    private bool SaveChanges()
    {
        if (_run is null) return false;
        try
        {
            JobsGrid.CommitEdit(DataGridEditingUnit.Cell, true);
            JobsGrid.CommitEdit(DataGridEditingUnit.Row, true);
            _store.Save(_run);
            _dirty = false;
            JournalStatus.Text = "Изменения сохранены в защищённом журнале.";
            return true;
        }
        catch (Exception ex)
        {
            Report(ex);
            return false;
        }
    }

    private void Save_Click(object sender, RoutedEventArgs e) => SaveChanges();

    private void DeleteJob_Click(object sender, RoutedEventArgs e)
    {
        if (_run is null || JobsGrid.SelectedItem is not MailingJobRecord job)
            return;
        if (MessageBox.Show(this,
                "Удалить запись строки " + job.RowNumber +
                " из локального журнала? На сервере Mail.ru ничего не изменится.",
                "Удаление записи", MessageBoxButton.YesNo,
                MessageBoxImage.Warning) != MessageBoxResult.Yes) return;
        try
        {
            if (_run.Jobs.Count == 1)
            {
                _store.Delete(_run.Id);
                _dirty = false;
                Reload();
            }
            else
            {
                _run.Jobs.Remove(job);
                _store.Save(_run);
                _dirty = false;
                JobsGrid.ItemsSource = null;
                JobsGrid.ItemsSource = _run.Jobs;
                JobsGrid.SelectedIndex = 0;
                JournalStatus.Text = "Запись удалена.";
            }
        }
        catch (Exception ex) { Report(ex); }
    }

    private void DeleteRun_Click(object sender, RoutedEventArgs e)
    {
        if (_run is null) return;
        if (MessageBox.Show(this,
                "Удалить весь выбранный запуск из локального журнала? " +
                "Письма на сервере и отметки Excel не изменятся.",
                "Удаление запуска", MessageBoxButton.YesNo,
                MessageBoxImage.Warning) != MessageBoxResult.Yes) return;
        try
        {
            _store.Delete(_run.Id);
            _dirty = false;
            Reload();
        }
        catch (Exception ex) { Report(ex); }
    }

    private void OnClosing(object? sender, CancelEventArgs e)
    {
        if (!ConfirmDiscard()) e.Cancel = true;
    }

    private void Report(Exception ex)
    {
        JournalStatus.Text = "Ошибка журнала: " + ex.Message;
        DiagnosticLog.Write("mailing_journal_editor",
            ex.GetType().Name + ": " + ex.Message);
    }
}
