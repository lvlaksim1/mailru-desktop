using System.Collections.ObjectModel;
using System.IO;
using System.Windows;
using System.Windows.Controls;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly ObservableCollection<SavedSignature> _signatures = [];
    private readonly ObservableCollection<SavedMailTemplate> _mailTemplates = [];
    private readonly ObservableCollection<string> _templateDraftAttachments = [];
    private string? _previewInsertedSignature;

    private void InitializeUserContentSettings()
    {
        _signatures.Clear();
        foreach (var signature in _settingsStore.LoadSignatures())
            _signatures.Add(signature);

        _mailTemplates.Clear();
        foreach (var template in _settingsStore.LoadMailTemplates())
            _mailTemplates.Add(template);

        SignaturesListBox.ItemsSource = _signatures;
        MailTemplatesListBox.ItemsSource = _mailTemplates;
        TemplateAttachmentsListBox.ItemsSource = _templateDraftAttachments;

        PreviewSignatureComboBox.ItemsSource = _signatures;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
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

        var replacement = new SavedMailTemplate
        {
            Id = (MailTemplatesListBox.SelectedItem as SavedMailTemplate)?.Id
                 ?? Guid.NewGuid().ToString("N"),
            Name = name,
            Subject = TemplateSubjectTextBox.Text,
            Body = TemplateBodyTextBox.Text,
            Attachments = _templateDraftAttachments
                .Distinct(StringComparer.OrdinalIgnoreCase)
                .ToList()
        };

        if (MailTemplatesListBox.SelectedItem is SavedMailTemplate selected)
        {
            var index = _mailTemplates.IndexOf(selected);
            if (index >= 0)
                _mailTemplates[index] = replacement;
        }
        else
        {
            _mailTemplates.Add(replacement);
        }

        _settingsStore.SaveMailTemplates(_mailTemplates);
        MailTemplatesListBox.SelectedItem = replacement;

        PreviewTemplateComboBox.ItemsSource = null;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
    }

    private void DeleteMailTemplateButton_Click(object sender, RoutedEventArgs e)
    {
        if (MailTemplatesListBox.SelectedItem is not SavedMailTemplate template)
            return;

        if (!AppDialog.Confirm(
                this,
                "Удаление шаблона",
                $"Удалить шаблон «{template.Name}»?",
                "Удалить",
                "Отмена"))
        {
            return;
        }

        _mailTemplates.Remove(template);
        _settingsStore.SaveMailTemplates(_mailTemplates);

        TemplateNameTextBox.Clear();
        TemplateSubjectTextBox.Clear();
        TemplateBodyTextBox.Clear();
        _templateDraftAttachments.Clear();

        PreviewTemplateComboBox.ItemsSource = null;
        PreviewTemplateComboBox.ItemsSource = _mailTemplates;
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

    private void PreviewSignatureComboBox_SelectionChanged(
        object sender,
        SelectionChangedEventArgs e)
    {
        if (PreviewSignatureComboBox.SelectedItem is not SavedSignature signature ||
            string.IsNullOrWhiteSpace(signature.Body))
        {
            return;
        }

        var body = PreviewComposeBodyTextBox.Text;

        if (!string.IsNullOrWhiteSpace(_previewInsertedSignature) &&
            body.EndsWith(
                _previewInsertedSignature,
                StringComparison.Ordinal))
        {
            body = body[..^_previewInsertedSignature.Length].TrimEnd();
        }

        PreviewComposeBodyTextBox.Text =
            body.TrimEnd() +
            (body.TrimEnd().Length == 0 ? string.Empty : Environment.NewLine + Environment.NewLine) +
            signature.Body;

        _previewInsertedSignature = signature.Body;
        PreviewComposeBodyTextBox.CaretIndex =
            PreviewComposeBodyTextBox.Text.Length;
    }

    private void PreviewTemplateComboBox_SelectionChanged(
        object sender,
        SelectionChangedEventArgs e)
    {
        if (PreviewTemplateComboBox.SelectedItem is not SavedMailTemplate template)
            return;

        PreviewComposeSubjectTextBox.Text = template.Subject;
        PreviewComposeBodyTextBox.Text = template.Body;
        _previewInsertedSignature = null;

        _previewAttachmentPaths.Clear();

        var missing = 0;
        foreach (var path in template.Attachments)
        {
            if (File.Exists(path))
            {
                if (!_previewAttachmentPaths.Contains(
                        path,
                        StringComparer.OrdinalIgnoreCase))
                {
                    _previewAttachmentPaths.Add(path);
                }
            }
            else
            {
                missing++;
            }
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
