using System.Windows;

namespace MailRuDesktop.App;

public partial class AddAccountWindow : Window
{
    public string Login => LoginTextBox.Text.Trim();
    public string Password => PasswordBox.Password;

    public AddAccountWindow()
    {
        InitializeComponent();
        Loaded += (_, _) => LoginTextBox.Focus();
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
