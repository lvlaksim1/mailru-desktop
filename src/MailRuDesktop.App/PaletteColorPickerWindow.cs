using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>A dependency-free WPF RGB color picker matching the application's theme.</summary>
internal sealed class PaletteColorPickerWindow : Window
{
    private readonly Slider _red = new() { Minimum = 0, Maximum = 255, TickFrequency = 1, IsSnapToTickEnabled = true };
    private readonly Slider _green = new() { Minimum = 0, Maximum = 255, TickFrequency = 1, IsSnapToTickEnabled = true };
    private readonly Slider _blue = new() { Minimum = 0, Maximum = 255, TickFrequency = 1, IsSnapToTickEnabled = true };
    private readonly TextBox _hexBox = new() { Padding = new Thickness(8), Width = 110 };
    private readonly Border _preview = new() { Height = 56, CornerRadius = new CornerRadius(5) };
    private bool _updating;

    public string SelectedHex { get; private set; }

    public PaletteColorPickerWindow(Window owner, string roleName, string hex)
    {
        Owner = owner;
        Title = "Выбор цвета — " + roleName;
        SelectedHex = hex;
        Width = 410;
        Height = 370;
        MinWidth = 390;
        ResizeMode = ResizeMode.NoResize;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        ShowInTaskbar = false;
        SetResourceReference(BackgroundProperty, "AppDialogBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");

        var root = new StackPanel { Margin = new Thickness(20) };
        root.Children.Add(new TextBlock
        {
            Text = roleName,
            FontSize = 17,
            FontWeight = FontWeights.SemiBold,
            Margin = new Thickness(0, 0, 0, 12)
        });

        AddSlider(root, "Красный", _red);
        AddSlider(root, "Зелёный", _green);
        AddSlider(root, "Синий", _blue);

        var bottom = new DockPanel { Margin = new Thickness(0, 14, 0, 10) };
        bottom.Children.Add(new TextBlock
        {
            Text = "Код цвета:",
            VerticalAlignment = VerticalAlignment.Center,
            Margin = new Thickness(0, 0, 10, 0)
        });
        bottom.Children.Add(_hexBox);
        root.Children.Add(bottom);
        root.Children.Add(_preview);

        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Right,
            Margin = new Thickness(0, 14, 0, 0)
        };
        var save = new Button
        {
            Content = "Применить",
            Padding = new Thickness(14, 7, 14, 7),
            IsDefault = true,
            MinWidth = 100
        };
        save.Click += (_, _) =>
        {
            if (!ThemePalette.TryNormalize(_hexBox.Text, out var chosen))
            {
                AppDialog.Info(this, "Выбор цвета", "Нужен шестнадцатеричный цвет #RRGGBB.");
                return;
            }

            SelectedHex = chosen;
            DialogResult = true;
        };
        var cancel = new Button
        {
            Content = "Отмена",
            Padding = new Thickness(14, 7, 14, 7),
            IsCancel = true,
            Margin = new Thickness(8, 0, 0, 0)
        };
        buttons.Children.Add(save);
        buttons.Children.Add(cancel);
        root.Children.Add(buttons);
        Content = root;

        _red.ValueChanged += (_, _) => SlidersChanged();
        _green.ValueChanged += (_, _) => SlidersChanged();
        _blue.ValueChanged += (_, _) => SlidersChanged();
        _hexBox.TextChanged += (_, _) => HexChanged();

        _updating = true;
        _red.Value = Convert.ToInt32(hex.Substring(1, 2), 16);
        _green.Value = Convert.ToInt32(hex.Substring(3, 2), 16);
        _blue.Value = Convert.ToInt32(hex.Substring(5, 2), 16);
        _hexBox.Text = hex;
        _updating = false;
        UpdatePreview(hex);

        SourceInitialized += (_, _) => ThemeManager.RefreshWindowChrome(this);
        Loaded += (_, _) => ThemeManager.RefreshWindowChrome(this);
        Activated += (_, _) => Dispatcher.BeginInvoke(
            new Action(() => ThemeManager.RefreshWindowChrome(this)));
    }

    private static void AddSlider(Panel container, string title, Slider slider)
    {
        var row = new DockPanel { Margin = new Thickness(0, 4, 0, 4) };
        var label = new TextBlock
        {
            Text = title,
            Width = 75,
            VerticalAlignment = VerticalAlignment.Center
        };
        DockPanel.SetDock(label, Dock.Left);
        row.Children.Add(label);
        row.Children.Add(slider);
        container.Children.Add(row);
    }

    private void SlidersChanged()
    {
        if (_updating)
            return;

        _updating = true;
        var hex = $"#{(int)_red.Value:X2}{(int)_green.Value:X2}{(int)_blue.Value:X2}";
        _hexBox.Text = hex;
        UpdatePreview(hex);
        _updating = false;
    }

    private void HexChanged()
    {
        if (_updating || !ThemePalette.TryNormalize(_hexBox.Text, out var hex))
            return;

        _updating = true;
        _red.Value = Convert.ToInt32(hex.Substring(1, 2), 16);
        _green.Value = Convert.ToInt32(hex.Substring(3, 2), 16);
        _blue.Value = Convert.ToInt32(hex.Substring(5, 2), 16);
        UpdatePreview(hex);
        _updating = false;
    }

    private void UpdatePreview(string hex) =>
        _preview.Background = new SolidColorBrush(
            (Color)ColorConverter.ConvertFromString(hex));
}
