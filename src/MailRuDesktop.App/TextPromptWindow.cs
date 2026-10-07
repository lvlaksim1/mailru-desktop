using System.Windows;
using System.Windows.Controls;

namespace MailRuDesktop.App;

internal sealed class TextPromptWindow : Window
{
    private readonly TextBox _textBox;

    public string Value => _textBox.Text.Trim();

    public TextPromptWindow(
        Window owner,
        string title,
        string prompt,
        string initialValue = "")
    {
        Owner = owner;
        Title = title;
        Width = 420;
        Height = 190;
        MinWidth = 340;
        ResizeMode = ResizeMode.NoResize;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        ShowInTaskbar = false;

        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");

        var root = new Grid { Margin = new Thickness(16) };
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });

        var label = new TextBlock
        {
            Text = prompt,
            TextWrapping = TextWrapping.Wrap,
            Margin = new Thickness(0, 0, 0, 10)
        };
        root.Children.Add(label);

        _textBox = new TextBox
        {
            Text = initialValue,
            Padding = new Thickness(8),
            MinWidth = 300
        };
        Grid.SetRow(_textBox, 1);
        root.Children.Add(_textBox);

        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Right,
            Margin = new Thickness(0, 14, 0, 0)
        };

        var ok = new Button
        {
            Content = "Сохранить",
            Padding = new Thickness(14, 7, 14, 7),
            MinWidth = 95,
            IsDefault = true
        };
        ok.Click += (_, _) =>
        {
            if (Value.Length == 0)
                return;
            DialogResult = true;
        };

        var cancel = new Button
        {
            Content = "Отмена",
            Padding = new Thickness(14, 7, 14, 7),
            MinWidth = 85,
            Margin = new Thickness(8, 0, 0, 0),
            IsCancel = true
        };

        buttons.Children.Add(ok);
        buttons.Children.Add(cancel);
        Grid.SetRow(buttons, 2);
        root.Children.Add(buttons);

        Content = root;

        Loaded += (_, _) =>
        {
            _textBox.Focus();
            _textBox.SelectAll();
        };
    }
}
