using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace MailRuDesktop.App;

internal static class AppDialog
{
    public static bool Confirm(
        Window owner,
        string title,
        string message,
        string primaryText = "Да",
        string secondaryText = "Нет")
    {
        var result = false;
        var window = CreateWindow(owner, title, message);

        var buttons = (StackPanel)window.Tag;
        var primary = CreateButton(primaryText);
        primary.IsDefault = true;
        primary.Click += (_, _) =>
        {
            result = true;
            window.DialogResult = true;
        };

        var secondary = CreateButton(secondaryText);
        secondary.IsCancel = true;
        secondary.Click += (_, _) =>
        {
            result = false;
            window.DialogResult = false;
        };

        buttons.Children.Add(primary);
        buttons.Children.Add(secondary);

        window.ShowDialog();
        return result;
    }

    public static void Info(
        Window owner,
        string title,
        string message,
        string buttonText = "Понятно")
    {
        var window = CreateWindow(owner, title, message);
        var buttons = (StackPanel)window.Tag;

        var close = CreateButton(buttonText);
        close.IsDefault = true;
        close.IsCancel = true;
        close.Click += (_, _) => window.DialogResult = true;
        buttons.Children.Add(close);

        window.ShowDialog();
    }

    private static Window CreateWindow(
        Window owner,
        string title,
        string message)
    {
        var window = new Window
        {
            Owner = owner,
            Title = title,
            Width = 460,
            SizeToContent = SizeToContent.Height,
            MinHeight = 190,
            MaxHeight = 560,
            WindowStartupLocation = WindowStartupLocation.CenterOwner,
            ResizeMode = ResizeMode.NoResize,
            WindowStyle = WindowStyle.None,
            ShowInTaskbar = false
        };
        window.SetResourceReference(Window.BackgroundProperty, "AppDialogBrush");
        window.SetResourceReference(Window.ForegroundProperty, "AppTextBrush");

        var root = new Border
        {
            BorderThickness = new Thickness(1),
            Padding = new Thickness(0),
            CornerRadius = new CornerRadius(8)
        };
        root.SetResourceReference(Border.BorderBrushProperty, "AppBorderBrush");
        root.SetResourceReference(Border.BackgroundProperty, "AppDialogBrush");

        var grid = new Grid();
        grid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        grid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        grid.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });

        var header = new Grid
        {
            Height = 42,
            Margin = new Thickness(0)
        };
        header.SetResourceReference(Panel.BackgroundProperty, "AppControlBrush");
        header.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(1, GridUnitType.Star) });
        header.ColumnDefinitions.Add(new ColumnDefinition { Width = GridLength.Auto });

        var titleText = new TextBlock
        {
            Text = title,
            Margin = new Thickness(14, 0, 8, 0),
            VerticalAlignment = VerticalAlignment.Center,
            FontWeight = FontWeights.SemiBold,
            FontSize = 14
        };
        titleText.SetResourceReference(TextBlock.ForegroundProperty, "AppTextBrush");

        var closeButton = new Button
        {
            Content = "×",
            Width = 42,
            Height = 42,
            Padding = new Thickness(0),
            BorderThickness = new Thickness(0),
            Background = Brushes.Transparent,
            FontSize = 18
        };
        closeButton.SetResourceReference(Control.ForegroundProperty, "AppMutedTextBrush");
        closeButton.Click += (_, _) => window.DialogResult = false;

        Grid.SetColumn(closeButton, 1);
        header.Children.Add(titleText);
        header.Children.Add(closeButton);
        header.MouseLeftButtonDown += (_, e) =>
        {
            if (e.ButtonState == MouseButtonState.Pressed)
                window.DragMove();
        };

        var messageText = new TextBlock
        {
            Text = message,
            TextWrapping = TextWrapping.Wrap,
            Margin = new Thickness(18, 18, 18, 14),
            FontSize = 13,
            LineHeight = 20
        };
        messageText.SetResourceReference(TextBlock.ForegroundProperty, "AppTextBrush");

        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Right,
            Margin = new Thickness(18, 0, 18, 16)
        };

        Grid.SetRow(header, 0);
        Grid.SetRow(messageText, 1);
        Grid.SetRow(buttons, 2);
        grid.Children.Add(header);
        grid.Children.Add(messageText);
        grid.Children.Add(buttons);
        root.Child = grid;
        window.Content = root;
        window.Tag = buttons;
        return window;
    }

    private static Button CreateButton(string text)
    {
        var button = new Button
        {
            Content = text,
            MinWidth = 96,
            Padding = new Thickness(14, 7, 14, 7),
            Margin = new Thickness(8, 0, 0, 0)
        };
        return button;
    }
}
