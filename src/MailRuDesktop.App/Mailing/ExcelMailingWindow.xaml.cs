using System.Collections.ObjectModel;
using System.ComponentModel;
using System.IO;
using System.Runtime.InteropServices;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Input;
using MailRuDesktop.App;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App.Mailing;

internal sealed class MailingSenderChoice : INotifyPropertyChanged
{
    public required string Login { get; init; }
    public string Availability { get; init; } = "Авторизован";
    private bool _selected;
    public bool IsSelected
    {
        get => _selected;
        set { _selected = value; PropertyChanged?.Invoke(this, new(nameof(IsSelected))); }
    }
    private int _quantity = 50;
    public int Quantity
    {
        get => _quantity;
        set { _quantity = value; PropertyChanged?.Invoke(this, new(nameof(Quantity))); }
    }
    public event PropertyChangedEventHandler? PropertyChanged;
}

public partial class ExcelMailingWindow : Window
{
    private readonly MailingProfileStore _store = new();
    private readonly AuthorizationStore _authorizations;
    private readonly AppSettingsStore _settings;
    private readonly ObservableCollection<MailingProfile> _profiles = [];
    private readonly ObservableCollection<MailingSenderChoice> _senders = [];
    private readonly List<SavedMailTemplate> _templates;
    private MailingProfile? _current;
    private bool _loading;
    private bool _dirty;
    private bool _storeReadable = true;
    private bool _senderHeightChanged;

    internal ExcelMailingWindow(Window owner,
        AuthorizationStore authorizations, AppSettingsStore settings,
        MailRuClient mailRu)
    {
        InitializeComponent();
        Owner = owner;
        Icon = owner.Icon;
        _authorizations = authorizations;
        _settings = settings;
        _mailRu = mailRu;
        SenderGrid.Height = NormalizeSenderGridHeight(
            _settings.LoadUserInterfaceState().ExcelMailingSenderGridHeight);
        ThemeManager.AttachWindowChrome(this);
        ThemeManager.RefreshWindowChrome(this);

        var templates = new MarkdownTemplateStore(_settings.LoadTemplateDirectory());
        _templates = templates.LoadAll().ToList();
        TemplateCombo.ItemsSource = _templates;
        SenderGrid.ItemsSource = _senders;
        ProfileCombo.ItemsSource = _profiles;
        TimeZoneText.Text = TimeZoneInfo.Local.Id;
        try
        {
            foreach (var profile in _store.Load()) _profiles.Add(profile);
        }
        catch (IOException ex)
        {
            _storeReadable = false;
            SaveProfileButton.IsEnabled = false;
            StatusText.Text = ex.Message;
        }
        if (_profiles.Count == 0 && _storeReadable)
            _profiles.Add(new MailingProfile());
        if (_profiles.Count > 0)
            ProfileCombo.SelectedIndex = 0;
        Closed += (_, _) => { _sendCancellation?.Cancel(); _stopMailing = true; };
        Closing += Window_Closing;
    }

    internal static double NormalizeSenderGridHeight(double desired) =>
        double.IsFinite(desired) ? Math.Clamp(desired, 110, 520) : 124;

    internal void ResizeSenderGrid(double verticalDelta)
    {
        var maxHeight = Math.Max(110, Math.Min(520, ActualHeight - 320));
        var height = Math.Clamp(SenderGrid.Height + verticalDelta, 110, maxHeight);
        if (Math.Abs(height - SenderGrid.Height) < 0.5) return;
        SenderGrid.Height = height;
        _senderHeightChanged = true;
    }

    private void SenderGridResizeThumb_DragDelta(object sender, DragDeltaEventArgs e) =>
        ResizeSenderGrid(e.VerticalChange);

    private void SenderGridResizeThumb_DragCompleted(object sender,
        DragCompletedEventArgs e) => SaveSenderGridHeight();

    internal void SaveSenderGridHeight()
    {
        if (!_senderHeightChanged) return;
        try
        {
            // Refresh the current settings before writing to avoid clobbering
            // changes made by other settings panels during this session.
            var state = _settings.LoadUserInterfaceState();
            state.ExcelMailingSenderGridHeight = SenderGrid.Height;
            _settings.SaveUserInterfaceState(state);
            _senderHeightChanged = false;
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("mailing_sender_grid_height",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void Window_Closing(object? sender, CancelEventArgs e)
    {
        SaveSenderGridHeight();
        if (!_dirty || _sendCancellation is not null) return;
        var answer = MessageBox.Show(this,
            "Изменения профиля не сохранены. Закрыть без сохранения?",
            "Рассылки Excel", MessageBoxButton.YesNo, MessageBoxImage.Question);
        if (answer != MessageBoxResult.Yes) e.Cancel = true;
    }

    private void MarkDirty()
    {
        if (_loading || _current is null) return;
        _dirty = true;
        DirtyText.Text = "● Несохранённые изменения";
        UpdateQuotaSummary();
    }
    private void FieldChanged(object sender, SelectionChangedEventArgs e) => MarkDirty();
    private void FieldChangedText(object sender, TextChangedEventArgs e) => MarkDirty();
    private void DateChanged(object sender, SelectionChangedEventArgs e) => MarkDirty();
    private void JournalEnabled_Changed(object sender, RoutedEventArgs e) => MarkDirty();
    private void SenderGrid_CellEditEnding(object sender, DataGridCellEditEndingEventArgs e) =>
        Dispatcher.BeginInvoke(new Action(MarkDirty));

    private void UpdateQuotaSummary()
    {
        QuotaSummaryText.Text = "Квота: " + _senders.Where(x => x.IsSelected)
            .Sum(x => Math.Max(0, x.Quantity)) + " писем";
    }

    private bool CheckDiscardChanges()
    {
        if (!_dirty) return true;
        return MessageBox.Show(this, "Изменения текущего профиля не сохранены. Продолжить без сохранения?",
                   "Рассылки Excel", MessageBoxButton.YesNo, MessageBoxImage.Warning)
               == MessageBoxResult.Yes;
    }

    private void ProfileCombo_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_loading) return;
        var chosen = ProfileCombo.SelectedItem as MailingProfile;
        if (chosen is null || ReferenceEquals(chosen, _current)) return;
        if (!CheckDiscardChanges())
        {
            _loading = true;
            ProfileCombo.SelectedItem = _current;
            _loading = false;
            return;
        }
        LoadProfile(chosen);
    }

    private void LoadProfile(MailingProfile profile)
    {
        _loading = true;
        try
        {
            _current = profile;
            _senders.Clear();
            var loginSet = _authorizations.Logins.ToHashSet(StringComparer.OrdinalIgnoreCase);
            var all = profile.Senders.Select(x => x.Login)
                .Concat(_authorizations.Logins)
                .Distinct(StringComparer.OrdinalIgnoreCase);
            foreach (var login in all)
            {
                var existing = profile.Senders.FirstOrDefault(x =>
                    string.Equals(x.Login, login, StringComparison.OrdinalIgnoreCase));
                var row = new MailingSenderChoice
                {
                    Login = login, IsSelected = existing is not null,
                    Quantity = existing?.Quantity ?? 50,
                    Availability = loginSet.Contains(login) ? "Авторизован" : "Нет авторизации"
                };
                row.PropertyChanged += (_, _) => MarkDirty();
                _senders.Add(row);
            }
            StartDatePicker.SelectedDate = profile.StartDate;
            StartTimeBox.Text = profile.StartTime;
            IntervalBox.Text = profile.IntervalSeconds.ToString();
            JournalEnabledCheck.IsChecked = profile.JournalEnabled;
            SubjectBox.Text = profile.SubjectTemplate;
            TemplateCombo.SelectedItem = _templates.FirstOrDefault(x =>
                string.Equals(x.Name, profile.TemplateName,
                    StringComparison.OrdinalIgnoreCase));
            TimeZoneText.Text = profile.TimeZoneId;
            _dirty = false;
            DirtyText.Text = "";
            UpdateQuotaSummary();
        }
        finally { _loading = false; }
    }

    private void CommitGrid() 
    {
        SenderGrid.CommitEdit(DataGridEditingUnit.Cell, true);
        SenderGrid.CommitEdit(DataGridEditingUnit.Row, true);
    }

    private MailingProfile SnapshotProfile()
    {
        if (_current is null) throw new InvalidOperationException("Профиль не выбран.");
        CommitGrid();
        if (StartDatePicker.SelectedDate is not DateTime date)
            throw new InvalidOperationException("Не указана дата начала рассылки.");
        if (!int.TryParse(IntervalBox.Text.Trim(), out var interval))
            throw new InvalidOperationException("Интервал должен быть числом секунд.");
        if (interval < 5 || interval > 86400)
            throw new InvalidOperationException("Интервал должен составлять 5–86400 секунд.");
        var selected = _senders.Where(x => x.IsSelected).ToList();
        if (selected.Count == 0 || selected.Any(x => x.Quantity <= 0))
            throw new InvalidOperationException("Выберите отправителей и задайте положительные квоты.");
        if (selected.Any(x => x.Availability != "Авторизован"))
            throw new InvalidOperationException("Один из выбранных отправителей не авторизован.");
        if (TemplateCombo.SelectedItem is not SavedMailTemplate template)
            throw new InvalidOperationException("Выберите один существующий шаблон письма.");
        if (string.IsNullOrWhiteSpace(SubjectBox.Text))
            throw new InvalidOperationException("Укажите шаблон темы.");
        var prepared = new MailingProfile
        {
            Id = _current.Id, Name = _current.Name,
            StartDate = date.Date, StartTime = StartTimeBox.Text.Trim(),
            TimeZoneId = _current.TimeZoneId, IntervalSeconds = interval,
            JournalEnabled = JournalEnabledCheck.IsChecked == true,
            TemplateName = template.Name,
            SubjectTemplate = SubjectBox.Text,
            Senders = selected.Select(x => new MailingSenderQuota
            {
                Login = x.Login, Quantity = x.Quantity
            }).ToList()
        };
        _ = MailingPlanner.ResolveStart(prepared);
        return prepared;
    }

    private bool Persist()
    {
        if (!_storeReadable) return false;
        try
        {
            var next = SnapshotProfile();
            var index = _profiles.IndexOf(_current!);
            if (index < 0) throw new InvalidOperationException("Профиль не найден.");
            var saved = _profiles.ToList();
            saved[index] = next;
            _store.Save(saved);
            _loading = true;
            _profiles[index] = next;
            _current = next;
            ProfileCombo.SelectedItem = next;
            _dirty = false;
            DirtyText.Text = "";
            StatusText.Text = "Профиль сохранён.";
            return true;
        }
        catch (Exception ex)
        {
            ShowError(ex);
            return false;
        }
        finally { _loading = false; }
    }

    private void SaveProfile_Click(object sender, RoutedEventArgs e) => Persist();

    private string? AskName(string title, string initial)
    {
        var dialog = new TextPromptWindow(this, title, "Название профиля", initial);
        if (dialog.ShowDialog() != true) return null;
        if (_profiles.Any(x => x.Name.Equals(dialog.Value,
                StringComparison.CurrentCultureIgnoreCase) &&
                !ReferenceEquals(x, _current)))
            throw new InvalidOperationException("Профиль с таким названием уже есть.");
        return dialog.Value;
    }

    private void CreateProfile_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            if (!CheckDiscardChanges()) return;
            var name = AskName("Новый профиль", "Новая рассылка");
            if (name is null) return;
            var profile = new MailingProfile { Name = name };
            _loading = true;
            _profiles.Add(profile);
            ProfileCombo.SelectedItem = profile;
            _loading = false;
            LoadProfile(profile);
            MarkDirty();
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private void CopyProfile_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            if (_current is null || !CheckDiscardChanges()) return;
            var name = AskName("Копия профиля", _current.Name + " — копия");
            if (name is null) return;
            var copy = SnapshotProfile();
            copy.Id = Guid.NewGuid().ToString("N");
            copy.Name = name;
            _loading = true;
            _profiles.Add(copy);
            ProfileCombo.SelectedItem = copy;
            _loading = false;
            LoadProfile(copy);
            MarkDirty();
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private void RenameProfile_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            if (_current is null) return;
            var name = AskName("Переименование профиля", _current.Name);
            if (name is null) return;
            _current.Name = name;
            _loading = true;
            ProfileCombo.Items.Refresh();
            _loading = false;
            MarkDirty();
        }
        catch (Exception ex) { ShowError(ex); }
        finally { _loading = false; }
    }

    private void DeleteProfile_Click(object sender, RoutedEventArgs e)
    {
        if (_current is null || !_storeReadable) return;
        if (MessageBox.Show(this, "Удалить профиль «" + _current.Name + "»?",
                "Рассылки Excel", MessageBoxButton.YesNo, MessageBoxImage.Warning)
            != MessageBoxResult.Yes) return;
        try
        {
            _loading = true;
            _profiles.Remove(_current);
            _store.Save(_profiles);
            _dirty = false;
            _loading = false;
            if (_profiles.Count == 0) _profiles.Add(new MailingProfile());
            ProfileCombo.SelectedIndex = 0;
        }
        catch (Exception ex) { ShowError(ex); }
        finally { _loading = false; }
    }

    private void MoveSenderUp_Click(object sender, RoutedEventArgs e) => MoveSender(-1);
    private void MoveSenderDown_Click(object sender, RoutedEventArgs e) => MoveSender(1);
    private void MoveSender(int offset)
    {
        if (SenderGrid.SelectedItem is not MailingSenderChoice selected) return;
        var index = _senders.IndexOf(selected);
        var destination = index + offset;
        if (destination < 0 || destination >= _senders.Count) return;
        _senders.Move(index, destination);
        SenderGrid.SelectedItem = selected;
        MarkDirty();
    }

    private void FindBooks_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            var selectedPath = (BookCombo.SelectedItem as OpenExcelBook)?.FullName;
            var selectedSheet = SheetCombo.SelectedItem as string;
            var books = ExcelInteropService.Discover();
            BookCombo.ItemsSource = null;
            SheetCombo.ItemsSource = null;
            BookCombo.ItemsSource = books;
            if (selectedPath is null)
            {
                if (books.Count > 0) BookCombo.SelectedIndex = 0;
            }
            else
            {
                var matched = books.Where(x => string.Equals(x.FullName,
                    selectedPath, StringComparison.OrdinalIgnoreCase)).ToArray();
                if (matched.Length == 1)
                {
                    BookCombo.SelectedItem = matched[0];
                    if (selectedSheet is not null)
                        SheetCombo.SelectedItem = matched[0].Sheets.FirstOrDefault(x =>
                            string.Equals(x, selectedSheet, StringComparison.OrdinalIgnoreCase));
                }
                else
                    StatusText.Text = matched.Length == 0
                        ? "Прежняя книга больше не найдена. Выберите нужную книгу."
                        : "Открыто несколько экземпляров выбранного файла. Выберите книгу вручную.";
            }
            if (books.Count == 0)
                StatusText.Text = "Открытых книг Excel не обнаружено. Откройте книгу в Microsoft Excel.";
        }
        catch (Exception ex) { ShowError(ex); }
    }

    private void BookCombo_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (BookCombo.SelectedItem is not OpenExcelBook book)
        {
            SheetCombo.ItemsSource = null;
            BookPathText.Text = "";
            return;
        }
        SheetCombo.ItemsSource = book.Sheets;
        if (book.Sheets.Count > 0) SheetCombo.SelectedIndex = 0;
        BookPathText.Text = book.FullName == book.Name
            ? "Книга ещё не сохранена — сохраните её в Excel перед отправкой."
            : book.FullName;
    }

    private void ShowError(Exception ex)
    {
        StatusText.Text = "Ошибка: " + ex.Message;
        DiagnosticLog.Write("excel_mailing_direct_send", ex.GetType().Name + ": " + ex.Message);
    }
}
