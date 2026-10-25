using System.Windows;

namespace MailRuDesktop.App;

public partial class AddAccountWindow : Window
{
    public string Login => LoginTextBox.Text.Trim();
    public string Password => PasswordBox.Password;

    public AddAccountWindow(string? existingLogin = null, string? knownPassword = null,
        bool recoveryPassword = false)
    {
        InitializeComponent();
        if (!string.IsNullOrEmpty(knownPassword))
            PasswordBox.Password = knownPassword;
        if (recoveryPassword)
        {
            Title = "Действующий пароль после проверки Mail.ru";
            ConfirmButton.Content = "Продолжить";
        }
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
