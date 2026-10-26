using System.Windows;

namespace MailRuDesktop.App;

public partial class AddAccountWindow : Window
{
    private bool _isPasswordVisible;
    public string Login => LoginTextBox.Text.Trim();
    public string Password => _isPasswordVisible ? VisiblePasswordTextBox.Text : PasswordBox.Password;

    public AddAccountWindow(string? existingLogin = null, string? knownPassword = null,
        bool recoveryPassword = false)
    {
        InitializeComponent();
        if (!string.IsNullOrEmpty(knownPassword))
            PasswordBox.Password = knownPassword;
        if (!string.IsNullOrWhiteSpace(existingLogin))
        {
            Title = "Повторная авторизация Mail.ru";
            LoginTextBox.Text = existingLogin;
            LoginTextBox.IsReadOnly = true;
            ConfirmButton.Content = "Авторизовать";
            Loaded += (_, _) => PasswordBox.Focus();
        }
        else
        {
            Loaded += (_, _) => LoginTextBox.Focus();
        }
        if (recoveryPassword)
        {
            Title = "Действующий пароль после проверки Mail.ru";
            ConfirmButton.Content = "Продолжить";
        }
    }

    private void PasswordVisibilityButton_Click(object sender, RoutedEventArgs e)
    {
        if (_isPasswordVisible)
        {
            PasswordBox.Password = VisiblePasswordTextBox.Text;
            VisiblePasswordTextBox.Visibility = Visibility.Collapsed;
            PasswordBox.Visibility = Visibility.Visible;
            PasswordVisibilityButton.ToolTip = "Показать пароль";
            PasswordBox.Focus();
        }
        else
        {
            VisiblePasswordTextBox.Text = PasswordBox.Password;
            PasswordBox.Visibility = Visibility.Collapsed;
            VisiblePasswordTextBox.Visibility = Visibility.Visible;
            PasswordVisibilityButton.ToolTip = "Скрыть пароль";
            VisiblePasswordTextBox.Focus();
            VisiblePasswordTextBox.CaretIndex = VisiblePasswordTextBox.Text.Length;
        }
        _isPasswordVisible = !_isPasswordVisible;
    }

    private void AddButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(Login))
        {
            MessageBox.Show(this, "Введите логин.", "MailRu Desktop",
                MessageBoxButton.OK, MessageBoxImage.Information);
            return;
        }

        if (string.IsNullOrEmpty(Password))
        {
            MessageBox.Show(this, "Введите пароль.", "MailRu Desktop",
                MessageBoxButton.OK, MessageBoxImage.Information);
            return;
        }

        DialogResult = true;
        Close();
    }
}
