using System.Collections.ObjectModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using Microsoft.Win32;

namespace MailRuDesktop.App;

/// <summary>
/// The single dedicated, resizable editor for file-backed mail templates.
/// It reuses the outgoing mail rich-text toolbar, while persisting Markdown.
/// </summary>
internal sealed class TemplateEditorWindow : Window
{
    private readonly MarkdownTemplateStore _store;
    private readonly MarkdownSignatureStore _signatureStore;
    private readonly AppSettingsStore _settings;
    private readonly TextBox _name;
    private readonly TextBox _plainBody;
    private readonly RichComposeEditor _editor;
    private readonly ComboBox _signatureSelector;
    private readonly ListBox _attachmentList;
    private readonly ObservableCollection<string> _attachments = [];
    private readonly string _initialName;
    private bool _initializing = true;
    private bool _dirty;
    private bool _saved;

    internal string? SavedName { get; private set; }

    internal TemplateEditorWindow(Window owner, MarkdownTemplateStore store,
        MarkdownSignatureStore signatureStore, AppSettingsStore settings,
        SavedMailTemplate? template = null)
    {
        Owner = owner;
        _store = store;
        _signatureStore = signatureStore;
        _settings = settings;
        _initialName = template?.Name ?? "";
        _initialTemplate = template;
        Title = template is null ? "Новый шаблон — MailRu Desktop" :
            "Редактор шаблона — MailRu Desktop";
        Icon = owner.Icon;
        Width = 950;
        Height = 780;
        MinWidth = 610;
        MinHeight = 540;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");
        ThemeManager.AttachWindowChrome(this);

        var root = new Grid { Margin = new Thickness(15) };
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        root.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        root.RowDefinitions.Add(new RowDefinition { Height = new GridLength(100) });
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });

        _name = new TextBox { Padding = new Thickness(7) };
        AddLabeledInput(root, 0, "Название шаблона", _name);

        var contentGrid = new Grid { Margin = new Thickness(0, 8, 0, 8) };
        contentGrid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        contentGrid.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });
        var bodyLabel = new TextBlock { Text = "Текст шаблона", Margin = new Thickness(0, 0, 0, 5) };
        contentGrid.Children.Add(bodyLabel);
        _plainBody = new TextBox
        {
            AcceptsReturn = true, TextWrapping = TextWrapping.Wrap,
            VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
            MinHeight = 120
        };
        Grid.SetRow(_plainBody, 1);
        contentGrid.Children.Add(_plainBody);
        Grid.SetRow(contentGrid, 1);
        root.Children.Add(contentGrid);

        var signatureRow = new WrapPanel { Margin = new Thickness(0, 4, 0, 8) };
        signatureRow.Children.Add(new TextBlock
        {
            Text = "Подпись:", VerticalAlignment = VerticalAlignment.Center,
            Margin = new Thickness(0, 0, 8, 0)
        });
        _signatureSelector = new ComboBox
        {
            Width = 225, DisplayMemberPath = nameof(SavedSignature.Name),
            Margin = new Thickness(0, 0, 7, 0)
        };
        _signatureSelector.DropDownOpened += (_, _) => ReloadSignatures();
        signatureRow.Children.Add(_signatureSelector);
        signatureRow.Children.Add(ActionButton("Вставить подпись", (_, _) =>
        {
            if (_signatureSelector.SelectedItem is not SavedSignature chosen)
            {
                AppDialog.Info(this, "Шаблоны", "Сначала выберите подпись.");
                return;
            }
            _editor.ApplyMarkdownSignature(chosen.Body,
                _signatureStore.LoadAll().Select(s => s.Body));
            _dirty = true;
        }));
        Grid.SetRow(signatureRow, 2);
        root.Children.Add(signatureRow);

        var attachmentHost = new Grid();
        attachmentHost.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        attachmentHost.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });
        var attachmentActions = new WrapPanel { Margin = new Thickness(0, 0, 0, 5) };
        attachmentActions.Children.Add(new TextBlock
        {
            Text = "Вложения:", VerticalAlignment = VerticalAlignment.Center,
            Margin = new Thickness(0, 0, 8, 0)
        });
        attachmentActions.Children.Add(ActionButton("Добавить", AddAttachments));
        attachmentActions.Children.Add(ActionButton("Убрать выбранное", (_, _) =>
        {
            if (_attachmentList.SelectedItem is string path)
                _attachments.Remove(path);
        }));
        attachmentHost.Children.Add(attachmentActions);
        _attachmentList = new ListBox { ItemsSource = _attachments };
        Grid.SetRow(_attachmentList, 1);
        attachmentHost.Children.Add(_attachmentList);
        Grid.SetRow(attachmentHost, 3);
        root.Children.Add(attachmentHost);

        var footer = new DockPanel { LastChildFill = false, Margin = new Thickness(0, 12, 0, 0) };
        var cancel = ActionButton("Отмена", (_, _) => Close());
        DockPanel.SetDock(cancel, Dock.Right);
        footer.Children.Add(cancel);
        var save = ActionButton("Сохранить", SaveTemplate);
        save.Margin = new Thickness(0, 0, 8, 0);
        DockPanel.SetDock(save, Dock.Right);
        footer.Children.Add(save);
        Grid.SetRow(footer, 4);
        root.Children.Add(footer);

        Content = root;
        _editor = RichComposeEditor.Attach(_plainBody, this);
        _name.Text = template?.Name ?? "";
        _editor.LoadMarkdown(template?.Body ?? "");
        foreach (var path in template?.Attachments ?? [])
            _attachments.Add(path);
        ReloadSignatures();

        _editor.Changed += (_, _) =>
        {
            if (_initializing) return;
            _bodyChanged = true;
            MarkDirty();
        };
        _name.TextChanged += (_, _) => MarkDirty();
        _plainBody.TextChanged += (_, _) =>
        {
            if (!_initializing) _bodyChanged = true;
            MarkDirty();
        };
        _attachments.CollectionChanged += (_, _) => MarkDirty();
        _initializing = false;

        WindowGeometryPersistence.Restore(this, _settings, "template-editor");
        Closing += (_, e) =>
        {
            if (!_saved && _dirty &&
                !AppDialog.Confirm(this, "Несохранённый шаблон",
                    "Закрыть редактор без сохранения изменений?",
                    "Закрыть без сохранения", "Продолжить редактирование"))
            {
                e.Cancel = true;
                return;
            }
            WindowGeometryPersistence.Save(this, _settings, "template-editor");
        };
        Loaded += (_, _) => ThemeManager.RefreshWindowChrome(this);
    }

    private void MarkDirty()
    {
        if (!_initializing) _dirty = true;
    }

    private static Button ActionButton(string label, RoutedEventHandler click)
    {
        var result = new Button
        {
            Content = label, Padding = new Thickness(11, 6, 11, 6),
            Margin = new Thickness(0, 0, 7, 0)
        };
        result.Click += click;
        return result;
    }

    private static void AddLabeledInput(Grid parent, int row, string label, TextBox input)
    {
        var holder = new StackPanel { Margin = new Thickness(0, 0, 0, 8) };
        holder.Children.Add(new TextBlock { Text = label, Margin = new Thickness(0, 0, 0, 4) });
        holder.Children.Add(input);
        Grid.SetRow(holder, row);
        parent.Children.Add(holder);
    }

    private void ReloadSignatures()
    {
        var previousName = (_signatureSelector.SelectedItem as SavedSignature)?.Name;
        _signatureSelector.ItemsSource = _signatureStore.LoadAll();
        if (previousName is not null)
            _signatureSelector.SelectedItem = ((IEnumerable<SavedSignature>)_signatureSelector.ItemsSource)
                .FirstOrDefault(s => string.Equals(s.Name, previousName,
                    StringComparison.OrdinalIgnoreCase));
    }

    private void AddAttachments(object sender, RoutedEventArgs e)
    {
        var picker = new OpenFileDialog
        {
            Title = "Добавить вложения в шаблон",
            CheckFileExists = true, Multiselect = true
        };
        if (picker.ShowDialog(this) != true) return;
        foreach (var path in picker.FileNames)
            if (!_attachments.Contains(path, StringComparer.OrdinalIgnoreCase))
                _attachments.Add(path);
    }

    private void SaveTemplate(object sender, RoutedEventArgs e)
    {
        var name = _name.Text.Trim();
        if (name.Length == 0)
        {
            AppDialog.Info(this, "Шаблоны", "Введите название шаблона.");
            _name.Focus();
            return;
        }

        bool exists;
        try { exists = _store.Exists(name); }
        catch (ArgumentException ex)
        {
            AppDialog.Info(this, "Название шаблона", ex.Message);
            return;
        }
        // Even an existing template is not overwritten without confirmation.
        if (exists && !AppDialog.Confirm(this, "Перезапись шаблона",
                $"Шаблон «{name}» уже существует. Перезаписать файл?",
                "Перезаписать", "Отмена"))
            return;

        try
        {
            var output = new SavedMailTemplate
            {
                Name = name,
                Body = _bodyChanged ? _editor.ToMarkdown() :
                    _initialTemplate?.Body ?? _editor.ToMarkdown(),
                Attachments = _attachments.ToList()
            };
            // When a template is opened and saved without text edits, avoid
            // unnecessarily normalizing unusual user-authored Markdown.
            if (!_bodyChanged && _initialTemplate is not null)
                output.Body = _initialTemplate.Body;
            var saved = _store.Save(output, previousName: exists ? name : null);
            SavedName = saved.Name;
            _saved = true;
            DialogResult = true;
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                                        or ArgumentException)
        {
            AppDialog.Info(this, "Не удалось сохранить шаблон", ex.Message);
        }
    }

    private bool _bodyChanged;
    private readonly SavedMailTemplate? _initialTemplate;
}
