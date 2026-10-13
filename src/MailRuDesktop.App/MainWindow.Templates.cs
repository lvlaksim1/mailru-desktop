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
        TemplateAttachmentsListBox.ItemsSource = _templateDraftAttachments;

        PreviewSignatureComboBox.ItemsSource = _signatures;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
        ComposeSignatureComboBox.ItemsSource = _signatures;
        TemplateSignatureComboBox.ItemsSource = _signatures;
        ComposeTemplateComboBox.ItemsSource = _mailTemplates;

        StartTemplateWatcher();
        StartSignatureWatcher();
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
        var templateName = (TemplateSignatureComboBox.SelectedItem as SavedSignature)?.Name;
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
            if (templateName is not null)
                TemplateSignatureComboBox.SelectedItem = _signatures.FirstOrDefault(
                    entry => entry.Name.Equals(templateName, StringComparison.OrdinalIgnoreCase));
        }
        finally { _refreshingSignatures = false; }
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
            RefreshSignaturesFromDisk();
            SignatureNameTextBox.Clear();
            SignatureBodyTextBox.Clear();
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException)
        {
            AppDialog.Info(this, "Удаление подписи", ex.Message);
        }
    }

    private void InsertSignatureIntoTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        if (TemplateSignatureComboBox.SelectedItem is not SavedSignature signature)
        {
            AppDialog.Info(this, "Шаблоны", "Выберите подпись.");
            return;
        }
        var result = SignaturePlacement.Replace(
            TemplateBodyTextBox.Text, null, signature.Body,
            _signatures.Select(item => item.Body));
        TemplateBodyTextBox.Text = result;
        TemplateBodyTextBox.CaretIndex = result.Length;
    }

    private void TemplateSignatureComboBox_DropDownOpened(object sender, EventArgs e) =>
        RefreshSignaturesFromDisk();

    private void TemplateFormatButton_Click(object sender, RoutedEventArgs e)
    {
        if (sender is not Button button || button.Tag is not string command)
            return;
        var editor = TemplateBodyTextBox;
        var selected = editor.SelectedText;
        var start = editor.SelectionStart;
        string replacement;
        int innerOffset;
        switch (command)
        {
            case "bold":
                replacement = "**" + (selected.Length == 0 ? "текст" : selected) + "**";
                innerOffset = 2;
                break;
            case "italic":
                replacement = "*" + (selected.Length == 0 ? "текст" : selected) + "*";
                innerOffset = 1;
                break;
            case "underline":
                replacement = "<u>" + (selected.Length == 0 ? "текст" : selected) + "</u>";
                innerOffset = 3;
                break;
            case "heading":
                replacement = "# " + (selected.Length == 0 ? "Заголовок" : selected);
                innerOffset = 2;
                break;
            case "bullet":
                replacement = "- " + (selected.Length == 0 ? "Элемент списка" : selected);
                innerOffset = 2;
                break;
            case "link":
                var prompt = new TextPromptWindow(this, "Ссылка",
                    "Укажите адрес ссылки (HTTPS):", "https://");
                if (prompt.ShowDialog() != true) return;
                if (!Uri.TryCreate(prompt.Value, UriKind.Absolute, out var uri) ||
                    uri.Scheme != Uri.UriSchemeHttps)
                {
                    AppDialog.Info(this, "Ссылка", "Допустима только HTTPS-ссылка.");
                    return;
                }
                replacement = "[" + (selected.Length == 0 ? "текст ссылки" : selected) +
                    "](" + uri.AbsoluteUri + ")";
                innerOffset = 1;
                break;
            default:
                return;
        }
        editor.SelectedText = replacement;
        editor.Focus();
        editor.Select(start + innerOffset,
            selected.Length == 0 ? replacement.Length - innerOffset -
            (command switch
            {
                "bold" => 2, "italic" => 1, "underline" => 4,
                "link" => replacement.Length - innerOffset -
                    (selected.Length == 0 ? "текст ссылки".Length : selected.Length),
                _ => 0
            }) : selected.Length);
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
        ComposeSubjectTextBox.Text = template.Subject;
        ComposeBodyTextBox.Text = template.Body;
        _composeRichEditor?.LoadMarkdown(template.Body);
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
        PreviewComposeSubjectTextBox.Text = template.Subject;
        PreviewComposeBodyTextBox.Text = template.Body;
        _previewRichEditor?.LoadMarkdown(template.Body);
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
