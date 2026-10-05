using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow : Window
{
    private readonly MailRuClient _mailRu = new();
    private string? _accessToken;

    public MainWindow()
    {
        InitializeComponent();
        Closed += (_, _) => _mailRu.Dispose();
    }

    private async void AuthenticateButton_Click(object sender, RoutedEventArgs e)
    {
        AuthenticateButton.IsEnabled = false;
        AuthStatusText.Text = "Авторизация...";
        ResponseTextBox.Clear();

        try
        {
            var result = await _mailRu.AuthenticateAsync(
                LoginTextBox.Text.Trim(),
                PasswordBox.Password);

            PasswordBox.Clear();

            if (!result.Success || string.IsNullOrWhiteSpace(result.AccessToken))
            {
                _accessToken = null;
                AuthStatusText.Text = $"Ошибка: {result.ErrorCode ?? "unknown"}";
                return;
            }

            _accessToken = result.AccessToken;
            AuthStatusText.Text = "Авторизация успешна";
        }
        catch (Exception ex)
        {
            _accessToken = null;
            PasswordBox.Clear();
            AuthStatusText.Text = "Ошибка";
            ResponseTextBox.Text = ex.Message;
        }
        finally
        {
            AuthenticateButton.IsEnabled = true;
        }
    }

    private async void LoadFolderButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            AuthStatusText.Text = "Сначала выполните вход";
            return;
        }

        if (!int.TryParse(FolderIdTextBox.Text, out var folderId))
        {
            ResponseTextBox.Text = "Некорректный ID папки.";
            return;
        }

        LoadFolderButton.IsEnabled = false;
        ResponseTextBox.Text = "Загрузка...";

        try
        {
            ResponseTextBox.Text = await _mailRu.GetFolderThreadsAsync(_accessToken, folderId);
        }
        catch (Exception ex)
        {
            ResponseTextBox.Text = ex.Message;
        }
        finally
        {
            LoadFolderButton.IsEnabled = true;
        }
    }
}
