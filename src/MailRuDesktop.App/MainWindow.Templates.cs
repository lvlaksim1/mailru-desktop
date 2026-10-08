using System.Collections.ObjectModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Input;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly ObservableCollection<SavedSignature> _signatures = [];
    private readonly ObservableCollection<SavedMailTemplate> _mailTemplates = [];
    private readonly ObservableCollection<string> _templateDraftAttachments = [];
    private MarkdownTemplateStore? _templateFiles;
    private MarkdownTemplateStore TemplateFiles =>
        _templateFiles ??= new MarkdownTemplateStore(_settingsStore.LoadTemplateDirectory());
    private SavedSignature? _signatureAtDropdownOpen;
    private SavedMailTemplate? _templateAtDropdownOpen;
    private FileSystemWatcher? _templateWatcher;
    private bool _refreshingTemplateList;
    private string? _previewInsertedSignature;
    private string? _composeInsertedSignature;

    private void InitializeUserContentSettings()
    {
        _signatures.Clear();
        foreach (var signature in _settingsStore.LoadSignatures())
            _signatures.Add(signature);

        TemplateDirectoryText.Text = TemplateFiles.DirectoryPath;
        TemplateFiles.ImportLegacyOnce(_settingsStore.LoadMailTemplates());
        RefreshTemplatesFromDisk();

        SignaturesListBox.ItemsSource = _signatures;
        MailTemplatesListBox.ItemsSource = _mailTemplates;
        TemplateAttachmentsListBox.ItemsSource = _templateDraftAttachments;

        PreviewSignatureComboBox.ItemsSource = _signatures;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
        ComposeSignatureComboBox.ItemsSource = _signatures;
        ComposeTemplateComboBox.ItemsSource = _mailTemplates;

        StartTemplateWatcher();
        Closed += (_, _) => _templateWatcher?.Dispose();
    }

    private void StartTemplateWatcher()
    {
        if (_templateWatcher is not null)
        {
            _templateWatcher.EnableRaisingEvents = false;
            _templateWatcher.Changed -= TemplatesChangedOnDisk;
            _templateWatcher.Created -= TemplatesChangedOnDisk;
            _templateWatcher.Deleted -= TemplatesChangedOnDisk;
            _templateWatcher.Renamed -= TemplatesRenamedOnDisk;
            _templateWatcher.Dispose();
        }

        _templateWatcher = new FileSystemWatcher(TemplateFiles.DirectoryPath, "*.md");
        _templateWatcher.Changed += TemplatesChangedOnDisk;
        _templateWatcher.Created += TemplatesChangedOnDisk;
        _templateWatcher.Deleted += TemplatesChangedOnDisk;
        _templateWatcher.Renamed += TemplatesRenamedOnDisk;
        _templateWatcher.EnableRaisingEvents = true;
    }

    private void BrowseTemplateDirectoryButton_Click(object sender, RoutedEventArgs e)
    {
        var picker = new OpenFolderDialog
        {
            Title = "Выберите папку для шаблонов",
            InitialDirectory = TemplateFiles.DirectoryPath
        };
        if (picker.ShowDialog(this) == true)
        {
            TemplateDirectoryText.Text = picker.FolderName;
            ApplyTemplateDirectory();
        }
    }

    private void ApplyTemplateDirectory()
    {
        try
        {
            TemplateFiles.ChangeDirectory(TemplateDirectoryText.Text);
            _settingsStore.SaveTemplateDirectory(TemplateFiles.DirectoryPath);
            TemplateDirectoryText.Text = TemplateFiles.DirectoryPath;
            StartTemplateWatcher();
            RefreshTemplatesFromDisk();
            TemplateDirectoryStatusText.Text = "Папка шаблонов сохранена.";
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or ArgumentException)
        {
            TemplateDirectoryStatusText.Text = "Не удалось изменить папку.";
            AppDialog.Info(this, "Папка шаблонов", ex.Message);
            TemplateDirectoryText.Text = TemplateFiles.DirectoryPath;
        }
    }

    private void TemplatesChangedOnDisk(object sender, FileSystemEventArgs e) =>
        Dispatcher.BeginInvoke(new Action(() => RefreshTemplatesFromDisk()));

    private void TemplatesRenamedOnDisk(object sender, RenamedEventArgs e) =>
        Dispatcher.BeginInvoke(new Action(() => RefreshTemplatesFromDisk()));

    private void PreviewTemplateComboBox_DropDownOpened(object sender, EventArgs e)
    {
        RefreshTemplatesFromDisk();
        _templateAtDropdownOpen = PreviewTemplateComboBox.SelectedItem as SavedMailTemplate;
    }

    private void OpenTemplatesFolderButton_Click(object sender, RoutedEventArgs e)
    {
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
        {
            FileName = TemplateFiles.DirectoryPath,
            UseShellExecute = true
        });
    }

    private void RefreshTemplatesFromDisk(string? selectName = null)
    {
        var oldName = selectName ?? (MailTemplatesListBox.SelectedItem as SavedMailTemplate)?.Name;
        var selectedPreview = (PreviewTemplateComboBox.SelectedItem as SavedMailTemplate)?.Name;
        var selectedCompose = (ComposeTemplateComboBox.SelectedItem as SavedMailTemplate)?.Name;
        var disk = TemplateFiles.LoadAll();
        if (_mailTemplates.Count == disk.Count &&
            _mailTemplates.Zip(disk).All(pair =>
                pair.First.Name == pair.Second.Name &&
                pair.First.Subject == pair.Second.Subject &&
                pair.First.Body == pair.Second.Body &&
                pair.First.Attachments.SequenceEqual(pair.Second.Attachments)))
            return;

        _refreshingTemplateList = true;
        try
        {
            _mailTemplates.Clear();
            foreach (var template in disk)
                _mailTemplates.Add(template);

            if (oldName is not null)
                MailTemplatesListBox.SelectedItem = _mailTemplates.FirstOrDefault(
                    item => string.Equals(item.Name, oldName, StringComparison.OrdinalIgnoreCase));

            if (selectedPreview is not null)
                PreviewTemplateComboBox.SelectedItem = _mailTemplates.FirstOrDefault(
                    item => string.Equals(item.Name, selectedPreview, StringComparison.OrdinalIgnoreCase));
            if (selectedCompose is not null)
                ComposeTemplateComboBox.SelectedItem = _mailTemplates.FirstOrDefault(
                    item => string.Equals(item.Name, selectedCompose, StringComparison.OrdinalIgnoreCase));
        }
        finally
        {
            _refreshingTemplateList = false;
        }
    }

    private void SignaturesListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (SignaturesListBox.SelectedItem is not SavedSignature signature)
            return;

        SignatureNameTextBox.Text = signature.Name;
        SignatureBodyTextBox.Text = signature.Body;
    }

    private void NewSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        SignaturesListBox.SelectedItem = null;
        SignatureNameTextBox.Clear();
        SignatureBodyTextBox.Clear();
        SignatureNameTextBox.Focus();
    }

    private void SaveSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        var name = SignatureNameTextBox.Text.Trim();
        if (name.Length == 0)
        {
            AppDialog.Info(this, "Подпись", "Укажите название подписи.");
            return;
        }

        var replacement = new SavedSignature
        {
            Id = (SignaturesListBox.SelectedItem as SavedSignature)?.Id
                 ?? Guid.NewGuid().ToString("N"),
            Name = name,
            Body = SignatureBodyTextBox.Text
        };

        if (SignaturesListBox.SelectedItem is SavedSignature selected)
        {
            var index = _signatures.IndexOf(selected);
            if (index >= 0)
                _signatures[index] = replacement;
        }
        else
        {
            _signatures.Add(replacement);
        }

        _settingsStore.SaveSignatures(_signatures);
        SignaturesListBox.SelectedItem = replacement;
        PreviewSignatureComboBox.ItemsSource = null;
        PreviewSignatureComboBox.ItemsSource = _signatures;
        ComposeSignatureComboBox.ItemsSource = null;
        ComposeSignatureComboBox.ItemsSource = _signatures;
    }

    private void DeleteSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        if (SignaturesListBox.SelectedItem is not SavedSignature signature)
            return;

        if (!AppDialog.Confirm(
                this,
                "Удаление подписи",
                $"Удалить подпись «{signature.Name}»?",
                "Удалить",
                "Отмена"))
        {
            return;
        }

        _signatures.Remove(signature);
        _settingsStore.SaveSignatures(_signatures);
        SignatureNameTextBox.Clear();
        SignatureBodyTextBox.Clear();

        PreviewSignatureComboBox.ItemsSource = null;
        PreviewSignatureComboBox.ItemsSource = _signatures;
        ComposeSignatureComboBox.ItemsSource = null;
        ComposeSignatureComboBox.ItemsSource = _signatures;
    }

    private void MailTemplatesListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (MailTemplatesListBox.SelectedItem is not SavedMailTemplate template)
            return;

        TemplateNameTextBox.Text = template.Name;
        TemplateSubjectTextBox.Text = template.Subject;
        TemplateBodyTextBox.Text = template.Body;

        _templateDraftAttachments.Clear();
        foreach (var path in template.Attachments)
            _templateDraftAttachments.Add(path);
    }

    private void NewMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        MailTemplatesListBox.SelectedItem = null;
        TemplateNameTextBox.Clear();
        TemplateSubjectTextBox.Clear();
        TemplateBodyTextBox.Clear();
        _templateDraftAttachments.Clear();
        TemplateNameTextBox.Focus();
    }

    private void SaveMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        var name = TemplateNameTextBox.Text.Trim();
        if (name.Length == 0)
        {
            AppDialog.Info(this, "Шаблон", "Укажите название шаблона.");
            return;
        }

        // The name in the editor determines the target file. A changed name
        // creates a new independent template, never renames/deletes the old one.
        // Even overwriting the currently selected file requires confirmation.
        bool existing;
        try
        {
            existing = TemplateFiles.Exists(name);
        }
        catch (ArgumentException ex)
        {
            AppDialog.Info(this, "Некорректное название шаблона", ex.Message);
            return;
        }

        if (existing && !AppDialog.Confirm(
                this, "Перезапись шаблона",
                $"Шаблон с названием «{name}» уже существует и будет перезаписан. Продолжить?",
                "Перезаписать", "Отмена"))
            return;

        var replacement = new SavedMailTemplate
        {
            Name = name,
            Subject = TemplateSubjectTextBox.Text,
            Body = TemplateBodyTextBox.Text,
            Attachments = _templateDraftAttachments.ToList()
        };

        try
        {
            var saved = TemplateFiles.Save(replacement,
                previousName: existing ? name : null);
            RefreshTemplatesFromDisk(saved.Name);
            MailTemplatesListBox.SelectedItem = _mailTemplates.FirstOrDefault(
                item => string.Equals(item.Name, saved.Name, StringComparison.OrdinalIgnoreCase));
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or ArgumentException)
        {
            AppDialog.Info(this, "Не удалось сохранить шаблон", ex.Message);
        }
    }

    private void DeleteMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        if (MailTemplatesListBox.SelectedItem is not SavedMailTemplate template)
            return;

        if (!AppDialog.Confirm(this, "Удаление шаблона",
                $"Удалить шаблон «{template.Name}»?", "Удалить", "Отмена"))
            return;

        try
        {
            TemplateFiles.Delete(template.Name);
            RefreshTemplatesFromDisk();
            TemplateNameTextBox.Clear();
            TemplateSubjectTextBox.Clear();
            TemplateBodyTextBox.Clear();
            _templateDraftAttachments.Clear();
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException)
        {
            AppDialog.Info(this, "Не удалось удалить шаблон", ex.Message);
        }
    }

    private void AddTemplateAttachmentsButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new OpenFileDialog
        {
            Multiselect = true,
            CheckFileExists = true,
            Title = "Добавить вложения в шаблон"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        foreach (var path in dialog.FileNames)
        {
            if (!_templateDraftAttachments.Contains(
                    path,
                    StringComparer.OrdinalIgnoreCase))
            {
                _templateDraftAttachments.Add(path);
            }
        }
    }

    private void RemoveTemplateAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if (TemplateAttachmentsListBox.SelectedItem is string path)
            _templateDraftAttachments.Remove(path);
    }

    private void ImportMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        var picker = new OpenFileDialog
        {
            Title = "Загрузить готовый шаблон для редактирования",
            Filter = "Шаблоны Markdown (*.md)|*.md",
            InitialDirectory = TemplateFiles.DirectoryPath,
            CheckFileExists = true
        };
        if (picker.ShowDialog(this) != true)
            return;

        try
        {
            var path = Path.GetFullPath(picker.FileName);
            var source = new MarkdownTemplateStore(Path.GetDirectoryName(path));
            var name = Path.GetFileNameWithoutExtension(path);
            var template = source.ReadExternalFile(path);

            var managed = string.Equals(
                Path.GetDirectoryName(path),
                TemplateFiles.DirectoryPath,
                StringComparison.OrdinalIgnoreCase);
            var selected = managed
                ? _mailTemplates.FirstOrDefault(item =>
                    string.Equals(item.Name, name, StringComparison.OrdinalIgnoreCase))
                : null;
            MailTemplatesListBox.SelectedItem = selected;
            TemplateNameTextBox.Text = managed ? template.Name : template.Name + " — копия";
            TemplateSubjectTextBox.Text = template.Subject;
            TemplateBodyTextBox.Text = template.Body;
            _templateDraftAttachments.Clear();
            foreach (var attachment in template.Attachments)
                _templateDraftAttachments.Add(attachment);
            TemplateNameTextBox.Focus();
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                                         or ArgumentException)
        {
            AppDialog.Info(this, "Шаблоны", "Не удалось загрузить файл: " + ex.Message);
        }
    }

    private SavedSignature? _composeSignatureAtOpen;
    private SavedMailTemplate? _composeTemplateAtOpen;

    private void ComposeSignatureComboBox_DropDownOpened(object sender, EventArgs e) =>
        _composeSignatureAtOpen = ComposeSignatureComboBox.SelectedItem as SavedSignature;

    private void ComposeTemplateComboBox_DropDownOpened(object sender, EventArgs e)
    {
        RefreshTemplatesFromDisk();
        _composeTemplateAtOpen = ComposeTemplateComboBox.SelectedItem as SavedMailTemplate;
    }

    private void ComposeSignatureComboBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
    {
        if (GetClickedComboItem(ComposeSignatureComboBox, e.OriginalSource) is { } clicked &&
            ReferenceEquals(clicked.DataContext, _composeSignatureAtOpen) &&
            clicked.DataContext is SavedSignature signature)
            ApplyComposeSignature(signature, true);
    }

    private void ComposeTemplateComboBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
    {
        if (GetClickedComboItem(ComposeTemplateComboBox, e.OriginalSource) is { } clicked &&
            ReferenceEquals(clicked.DataContext, _composeTemplateAtOpen) &&
            clicked.DataContext is SavedMailTemplate template)
            ApplyComposeTemplate(template);
    }

    private void ComposeSignatureComboBox_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key == Key.Enter && ComposeSignatureComboBox.IsDropDownOpen &&
            ReferenceEquals(ComposeSignatureComboBox.SelectedItem, _composeSignatureAtOpen) &&
            _composeSignatureAtOpen is not null)
            ApplyComposeSignature(_composeSignatureAtOpen, true);
    }

    private void ComposeTemplateComboBox_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key == Key.Enter && ComposeTemplateComboBox.IsDropDownOpen &&
            ReferenceEquals(ComposeTemplateComboBox.SelectedItem, _composeTemplateAtOpen) &&
            _composeTemplateAtOpen is not null)
            ApplyComposeTemplate(_composeTemplateAtOpen);
    }

    private void ComposeSignatureComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (ComposeSignatureComboBox.SelectedItem is not SavedSignature signature ||
            string.IsNullOrWhiteSpace(signature.Body))
            return;
        ApplyComposeSignature(signature, false);
    }

    private void ApplyComposeSignature(SavedSignature signature, bool repeat)
    {
        var body = ComposeBodyTextBox.Text;
        if (!repeat && !string.IsNullOrWhiteSpace(_composeInsertedSignature) &&
            body.EndsWith(_composeInsertedSignature, StringComparison.Ordinal))
            body = body[..^_composeInsertedSignature.Length].TrimEnd();
        ComposeBodyTextBox.Text = body.TrimEnd() +
            (body.TrimEnd().Length == 0 ? "" : Environment.NewLine + Environment.NewLine) +
            signature.Body;
        _composeInsertedSignature = signature.Body;
        ComposeBodyTextBox.CaretIndex = ComposeBodyTextBox.Text.Length;
    }

    private void ComposeTemplateComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_refreshingTemplateList ||
            ComposeTemplateComboBox.SelectedItem is not SavedMailTemplate template)
            return;
        ApplyComposeTemplate(template);
    }

    private void ApplyComposeTemplate(SavedMailTemplate template)
    {
        ComposeSubjectTextBox.Text = template.Subject;
        ComposeBodyTextBox.Text = template.Body;
        _composeInsertedSignature = null;
        _attachmentPaths.Clear();
        var missing = 0;
        foreach (var path in template.Attachments)
        {
            if (!File.Exists(path))
                missing++;
            else if (!_attachmentPaths.Contains(path, StringComparer.OrdinalIgnoreCase))
                _attachmentPaths.Add(path);
        }
        RefreshComposeAttachments();
        ComposeStatusText.Text = missing == 0
            ? $"Применён шаблон «{template.Name}»."
            : $"Применён шаблон «{template.Name}». Недоступных вложений: {missing}.";
    }

    private void ResetComposeTemplateSelectors()
    {
        _composeInsertedSignature = null;
        ComposeSignatureComboBox.SelectedItem = null;
        ComposeTemplateComboBox.SelectedItem = null;
    }

    private void PreviewSignatureComboBox_DropDownOpened(object sender, EventArgs e) =>
        _signatureAtDropdownOpen = PreviewSignatureComboBox.SelectedItem as SavedSignature;

    private void PreviewTemplateComboBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
    {
        if (GetClickedComboItem(PreviewTemplateComboBox, e.OriginalSource) is { } clicked &&
            ReferenceEquals(clicked.DataContext, _templateAtDropdownOpen) &&
            clicked.DataContext is SavedMailTemplate template)
            ApplyTemplate(template);
    }

    private void PreviewSignatureComboBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
    {
        if (GetClickedComboItem(PreviewSignatureComboBox, e.OriginalSource) is { } clicked &&
            ReferenceEquals(clicked.DataContext, _signatureAtDropdownOpen) &&
            clicked.DataContext is SavedSignature signature)
            ApplySignature(signature, repeat: true);
    }

    private void PreviewSignatureComboBox_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key == Key.Enter && PreviewSignatureComboBox.IsDropDownOpen &&
            ReferenceEquals(PreviewSignatureComboBox.SelectedItem, _signatureAtDropdownOpen) &&
            _signatureAtDropdownOpen is not null)
            ApplySignature(_signatureAtDropdownOpen, repeat: true);
    }

    private void PreviewTemplateComboBox_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key == Key.Enter && PreviewTemplateComboBox.IsDropDownOpen &&
            ReferenceEquals(PreviewTemplateComboBox.SelectedItem, _templateAtDropdownOpen) &&
            _templateAtDropdownOpen is not null)
            ApplyTemplate(_templateAtDropdownOpen);
    }

    private static ComboBoxItem? GetClickedComboItem(ComboBox combo, object source) =>
        source is DependencyObject element
            ? ItemsControl.ContainerFromElement(combo, element) as ComboBoxItem
            : null;

    private void PreviewSignatureComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (PreviewSignatureComboBox.SelectedItem is SavedSignature signature)
            ApplySignature(signature);
    }

    private void ApplySignature(SavedSignature signature, bool repeat = false)
    {
        if (string.IsNullOrWhiteSpace(signature.Body))
            return;

        var body = PreviewComposeBodyTextBox.Text;
        if (!repeat &&
            !string.IsNullOrWhiteSpace(_previewInsertedSignature) &&
            body.EndsWith(_previewInsertedSignature, StringComparison.Ordinal))
        {
            body = body[..^_previewInsertedSignature.Length].TrimEnd();
        }

        PreviewComposeBodyTextBox.Text =
            body.TrimEnd() +
            (body.TrimEnd().Length == 0 ? string.Empty : Environment.NewLine + Environment.NewLine) +
            signature.Body;
        _previewInsertedSignature = signature.Body;
        PreviewComposeBodyTextBox.CaretIndex = PreviewComposeBodyTextBox.Text.Length;
    }

    private void PreviewTemplateComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (!_refreshingTemplateList &&
            PreviewTemplateComboBox.SelectedItem is SavedMailTemplate template)
            ApplyTemplate(template);
    }

    private void ApplyTemplate(SavedMailTemplate template)
    {
        PreviewComposeSubjectTextBox.Text = template.Subject;
        PreviewComposeBodyTextBox.Text = template.Body;
        _previewInsertedSignature = null;
        _previewAttachmentPaths.Clear();

        var missing = 0;
        foreach (var path in template.Attachments)
        {
            if (File.Exists(path))
            {
                if (!_previewAttachmentPaths.Contains(path, StringComparer.OrdinalIgnoreCase))
                    _previewAttachmentPaths.Add(path);
            }
            else
                missing++;
        }

        RefreshPreviewAttachments();
        PreviewComposeStatusText.Text = missing == 0
            ? $"Применён шаблон «{template.Name}»."
            : $"Применён шаблон «{template.Name}». Недоступных вложений: {missing}.";
    }

    private void ResetPreviewTemplateSelectors()
    {
        _previewInsertedSignature = null;
        PreviewSignatureComboBox.SelectedItem = null;
        PreviewTemplateComboBox.SelectedItem = null;
    }
}
