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
    private MarkdownTemplateStore? _templateFiles;
    private MarkdownTemplateStore TemplateFiles =>
        _templateFiles ??= new MarkdownTemplateStore(_settingsStore.LoadTemplateDirectory());
    private SavedSignature? _signatureAtDropdownOpen;
    private SavedMailTemplate? _templateAtDropdownOpen;
    private FileSystemWatcher? _templateWatcher;
    private FileSystemWatcher? _signatureWatcher;
    private MarkdownSignatureStore? _signatureFiles;
    private MarkdownSignatureStore SignatureFiles =>
        _signatureFiles ??= new MarkdownSignatureStore();
    private bool _refreshingSignatures;
    private bool _refreshingTemplateList;
    private string? _previewInsertedSignature;
    private string? _composeInsertedSignature;

    private void InitializeUserContentSettings()
    {
        SignatureFiles.ImportLegacyOnce(_settingsStore.LoadSignatures());
        RefreshSignaturesFromDisk();

        TemplateDirectoryText.Text = TemplateFiles.DirectoryPath;
        TemplateFiles.ImportLegacyOnce(_settingsStore.LoadMailTemplates());
        RefreshTemplatesFromDisk();

        SignaturesListBox.ItemsSource = _signatures;
        MailTemplatesListBox.ItemsSource = _mailTemplates;

        PreviewSignatureComboBox.ItemsSource = _signatures;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
        ComposeSignatureComboBox.ItemsSource = _signatures;
        ComposeTemplateComboBox.ItemsSource = _mailTemplates;

        StartTemplateWatcher();
        StartSignatureWatcher();
        UpdateDefaultSignatureButton();
        Closed += (_, _) =>
        {
            _templateWatcher?.Dispose();
            _signatureWatcher?.Dispose();
        };
    }

    private void StartSignatureWatcher()
    {
        _signatureWatcher?.Dispose();
        _signatureWatcher = new FileSystemWatcher(SignatureFiles.DirectoryPath, "*.md");
        _signatureWatcher.Changed += (_, _) =>
            Dispatcher.BeginInvoke(new Action(() => RefreshSignaturesFromDisk()));
        _signatureWatcher.Created += (_, _) =>
            Dispatcher.BeginInvoke(new Action(() => RefreshSignaturesFromDisk()));
        _signatureWatcher.Deleted += (_, _) =>
            Dispatcher.BeginInvoke(new Action(() => RefreshSignaturesFromDisk()));
        _signatureWatcher.Renamed += (_, _) =>
            Dispatcher.BeginInvoke(new Action(() => RefreshSignaturesFromDisk()));
        _signatureWatcher.EnableRaisingEvents = true;
    }

    private void RefreshSignaturesFromDisk(string? selection = null)
    {
        var selectedName = selection ??
            (SignaturesListBox.SelectedItem as SavedSignature)?.Name;
        var previewName = (PreviewSignatureComboBox.SelectedItem as SavedSignature)?.Name;
        var composeName = (ComposeSignatureComboBox.SelectedItem as SavedSignature)?.Name;
        var disk = SignatureFiles.LoadAll();
        if (_signatures.Count == disk.Count &&
            _signatures.Zip(disk).All(p =>
                p.First.Name == p.Second.Name && p.First.Body == p.Second.Body))
            return;
        _refreshingSignatures = true;
        try
        {
            _signatures.Clear();
            foreach (var entry in disk) _signatures.Add(entry);
            if (selectedName is not null)
                SignaturesListBox.SelectedItem = _signatures.FirstOrDefault(
                    entry => entry.Name.Equals(selectedName, StringComparison.OrdinalIgnoreCase));
            if (previewName is not null)
                PreviewSignatureComboBox.SelectedItem = _signatures.FirstOrDefault(
                    entry => entry.Name.Equals(previewName, StringComparison.OrdinalIgnoreCase));
            if (composeName is not null)
                ComposeSignatureComboBox.SelectedItem = _signatures.FirstOrDefault(
                    entry => entry.Name.Equals(composeName, StringComparison.OrdinalIgnoreCase));
        }
        finally { _refreshingSignatures = false; }
        UpdateDefaultSignatureButton();
    }

    private void OpenSignaturesFolderButton_Click(object sender, RoutedEventArgs e)
    {
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo
        {
            FileName = SignatureFiles.DirectoryPath,
            UseShellExecute = true
        });
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
        UpdateDefaultSignatureButton();
    }

    private SavedSignature? GetDefaultSignature() =>
        _signatures.FirstOrDefault(entry => string.Equals(entry.Name,
            _settingsStore.LoadDefaultSignatureName(), StringComparison.OrdinalIgnoreCase));

    private void UpdateDefaultSignatureButton()
    {
        if (DefaultSignatureButton is null) return;
        var selected = SignaturesListBox.SelectedItem as SavedSignature;
        DefaultSignatureButton.IsEnabled = selected is not null;
        DefaultSignatureButton.Content = selected is not null &&
            string.Equals(selected.Name, _settingsStore.LoadDefaultSignatureName(),
                StringComparison.OrdinalIgnoreCase)
            ? "Убрать основную"
            : "Сделать основной";
    }

    private void DefaultSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        if (SignaturesListBox.SelectedItem is not SavedSignature selected) return;
        var current = _settingsStore.LoadDefaultSignatureName();
        _settingsStore.SaveDefaultSignatureName(
            string.Equals(current, selected.Name, StringComparison.OrdinalIgnoreCase)
                ? null : selected.Name);
        UpdateDefaultSignatureButton();
    }

    private void ApplyDefaultComposeSignature()
    {
        if (GetDefaultSignature() is not { } signature) return;
        if (string.Equals(_composeInsertedSignature, signature.Body, StringComparison.Ordinal))
            return;
        ComposeSignatureComboBox.SelectedItem = signature;
        // Setting SelectedItem invokes the existing selection handler.
        if (!string.Equals(_composeInsertedSignature, signature.Body, StringComparison.Ordinal))
            ApplyComposeSignature(signature, false);
    }

    private void ApplyDefaultPreviewSignature()
    {
        if (GetDefaultSignature() is not { } signature) return;
        PreviewSignatureComboBox.SelectedItem = signature;
        if (!string.Equals(_previewInsertedSignature, signature.Body, StringComparison.Ordinal))
            ApplySignature(signature);
    }

    private void NewSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        SignaturesListBox.SelectedItem = null;
        SignatureNameTextBox.Clear();
        SignatureBodyTextBox.Clear();
        UpdateDefaultSignatureButton();
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

        var selected = SignaturesListBox.SelectedItem as SavedSignature;
        var sameFile = selected is not null &&
            string.Equals(selected.Name, name, StringComparison.OrdinalIgnoreCase);
        try
        {
            if (SignatureFiles.Exists(name) && !sameFile &&
                !AppDialog.Confirm(this, "Перезапись подписи",
                    $"Подпись «{name}» уже существует. Заменить файл?",
                    "Перезаписать", "Отмена"))
                return;

            SignatureFiles.Save(new SavedSignature { Name = name,
                Body = SignatureBodyTextBox.Text });
            if (selected is not null && !sameFile &&
                string.Equals(selected.Name, _settingsStore.LoadDefaultSignatureName(),
                    StringComparison.OrdinalIgnoreCase))
                _settingsStore.SaveDefaultSignatureName(name);
            // If an existing entry is renamed, remove its previous file only
            // after the replacement was written successfully.
            if (selected is not null && !sameFile)
                SignatureFiles.Delete(selected.Name);
            RefreshSignaturesFromDisk(name);
            SignaturesListBox.SelectedItem = _signatures.FirstOrDefault(
                entry => entry.Name.Equals(name, StringComparison.OrdinalIgnoreCase));
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                                        or ArgumentException)
        {
            AppDialog.Info(this, "Сохранение подписи", ex.Message);
        }
    }

    private void DeleteSignatureButton_Click(object sender, RoutedEventArgs e)
    {
        if (SignaturesListBox.SelectedItem is not SavedSignature signature)
            return;
        if (!AppDialog.Confirm(this, "Удаление подписи",
                $"Удалить подпись «{signature.Name}»?", "Удалить", "Отмена"))
            return;
        try
        {
            SignatureFiles.Delete(signature.Name);
            if (string.Equals(signature.Name, _settingsStore.LoadDefaultSignatureName(),
                    StringComparison.OrdinalIgnoreCase))
                _settingsStore.SaveDefaultSignatureName(null);
            RefreshSignaturesFromDisk();
            SignatureNameTextBox.Clear();
            SignatureBodyTextBox.Clear();
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException)
        {
            AppDialog.Info(this, "Удаление подписи", ex.Message);
        }
    }

    private void MailTemplatesListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        var selected = MailTemplatesListBox.SelectedItem is SavedMailTemplate;
        EditSelectedTemplateButton.IsEnabled = selected;
        DeleteSelectedTemplateButton.IsEnabled = selected;
    }

    private void NewMailTemplateButton_Click(object sender, RoutedEventArgs e) =>
        OpenTemplateEditor(null);

    private void EditMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        if (MailTemplatesListBox.SelectedItem is SavedMailTemplate template)
            OpenTemplateEditor(template);
    }

    private void MailTemplatesListBox_MouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        if (MailTemplatesListBox.SelectedItem is SavedMailTemplate template)
            OpenTemplateEditor(template);
    }

    private void OpenTemplateEditor(SavedMailTemplate? template)
    {
        RefreshSignaturesFromDisk();
        var editor = new TemplateEditorWindow(this, TemplateFiles, SignatureFiles,
            _settingsStore, template);
        if (editor.ShowDialog() == true && editor.SavedName is { } savedName)
            RefreshTemplatesFromDisk(savedName);
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
            EditSelectedTemplateButton.IsEnabled = false;
            DeleteSelectedTemplateButton.IsEnabled = false;
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException)
        {
            AppDialog.Info(this, "Не удалось удалить шаблон", ex.Message);
        }
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
            var template = source.ReadExternalFile(path);
            var managed = string.Equals(
                Path.GetDirectoryName(path), TemplateFiles.DirectoryPath,
                StringComparison.OrdinalIgnoreCase);
            if (!managed)
            {
                // Imported files are copies. The original remains untouched.
                template = new SavedMailTemplate
                {
                    Name = template.Name + " — копия",
                    Subject = template.Subject,
                    Body = template.Body,
                    Attachments = template.Attachments.ToList()
                };
            }
            OpenTemplateEditor(template);
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                                         or ArgumentException)
        {
            AppDialog.Info(this, "Шаблоны", "Не удалось загрузить файл: " + ex.Message);
        }
    }

    private SavedSignature? _composeSignatureAtOpen;
    private SavedMailTemplate? _composeTemplateAtOpen;

    private void ComposeSignatureComboBox_DropDownOpened(object sender, EventArgs e)
    {
        RefreshSignaturesFromDisk();
        _composeSignatureAtOpen = ComposeSignatureComboBox.SelectedItem as SavedSignature;
    }

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
        if (_refreshingSignatures ||
            ComposeSignatureComboBox.SelectedItem is not SavedSignature signature ||
            string.IsNullOrWhiteSpace(signature.Body))
            return;
        ApplyComposeSignature(signature, false);
    }

    private void ApplyComposeSignature(SavedSignature signature, bool repeat)
    {
        if (_composeRichEditor is not null)
        {
            _composeRichEditor.ApplyMarkdownSignature(signature.Body,
                _signatures.Select(item => item.Body));
            _composeInsertedSignature = signature.Body;
            return;
        }
        var updated = SignaturePlacement.Replace(
            ComposeBodyTextBox.Text, _composeInsertedSignature, signature.Body,
            _signatures.Select(item => item.Body));
        ComposeBodyTextBox.Text = updated;
        _composeInsertedSignature = signature.Body;
        ComposeBodyTextBox.CaretIndex = updated.Length;
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
        if (_composeRichEditor is { IsReplyMode: true } replyEditor)
            replyEditor.ApplyTemplateMarkdown(template.Body, _signatures.Select(item => item.Body));
        else if (_composeRichEditor is not null)
            _composeRichEditor.LoadMarkdown(template.Body);
        else
            ComposeBodyTextBox.Text = template.Body;
        _composeInsertedSignature = null;
        // A template replaces only the body; the user's default/selected
        // signature remains a separate, nonduplicating trailing region.
        if (_composeRichEditor is { IsReplyMode: false })
            ApplyDefaultComposeSignature();
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

    private void PreviewSignatureComboBox_DropDownOpened(object sender, EventArgs e)
    {
        RefreshSignaturesFromDisk();
        _signatureAtDropdownOpen = PreviewSignatureComboBox.SelectedItem as SavedSignature;
    }

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
        if (!_refreshingSignatures &&
            PreviewSignatureComboBox.SelectedItem is SavedSignature signature)
            ApplySignature(signature);
    }

    private void ApplySignature(SavedSignature signature, bool repeat = false)
    {
        if (string.IsNullOrWhiteSpace(signature.Body)) return;
        if (_previewRichEditor is not null)
        {
            _previewRichEditor.ApplyMarkdownSignature(signature.Body,
                _signatures.Select(item => item.Body));
            _previewInsertedSignature = signature.Body;
            return;
        }
        var updated = SignaturePlacement.Replace(
            PreviewComposeBodyTextBox.Text, _previewInsertedSignature, signature.Body,
            _signatures.Select(item => item.Body));
        PreviewComposeBodyTextBox.Text = updated;
        _previewInsertedSignature = signature.Body;
        PreviewComposeBodyTextBox.CaretIndex = updated.Length;
    }

    private void PreviewTemplateComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (!_refreshingTemplateList &&
            PreviewTemplateComboBox.SelectedItem is SavedMailTemplate template)
            ApplyTemplate(template);
    }

    private void ApplyTemplate(SavedMailTemplate template)
    {
        if (_previewRichEditor is not null)
            _previewRichEditor.ApplyTemplateMarkdown(template.Body, _signatures.Select(item => item.Body));
        else
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
