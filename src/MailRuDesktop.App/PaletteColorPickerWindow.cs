using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace MailRuDesktop.App;

/// <summary>
/// Single two-dimensional hue/saturation surface with a vertical brightness
/// strip. A user chooses a color by clicking, not by editing RGB sliders.
/// </summary>
internal sealed class PaletteColorPickerWindow : Window
{
    private const int SurfaceWidth = 274;
    private const int SurfaceHeight = 196;
    private readonly WriteableBitmap _surfaceBitmap = new(SurfaceWidth, SurfaceHeight, 96, 96,
        PixelFormats.Bgra32, null);
    private readonly WriteableBitmap _brightnessBitmap = new(18, SurfaceHeight, 96, 96,
        PixelFormats.Bgra32, null);
    private readonly Image _surfaceImage;
    private readonly Image _brightnessImage;
    private readonly Canvas _surfaceCanvas = new()
    {
        Width = SurfaceWidth, Height = SurfaceHeight, ClipToBounds = true
    };
    private readonly Canvas _brightnessCanvas = new()
    {
        Width = 18, Height = SurfaceHeight, ClipToBounds = true
    };
    private readonly Ellipse _cursor = new()
    {
        Width = 12, Height = 12, Stroke = Brushes.White, StrokeThickness = 2,
        Fill = Brushes.Transparent, IsHitTestVisible = false
    };
    private readonly Border _brightnessCursor = new()
    {
        Width = 20, Height = 3, Background = Brushes.White, IsHitTestVisible = false
    };
    private readonly TextBox _hexBox = new() { Width = 100, Padding = new Thickness(8) };
    private readonly Border _preview = new()
    {
        Height = 36, Width = 68, CornerRadius = new CornerRadius(4)
    };
    private bool _updating;
    private double _hue;
    private double _saturation;
    private double _brightness;

    public string SelectedHex { get; private set; }

    public PaletteColorPickerWindow(Window owner, string roleName, string hex)
    {
        Owner = owner;
        Title = "Выбор цвета — " + roleName;
        SelectedHex = hex;
        Width = 390;
        Height = 420;
        MinWidth = 390;
        MinHeight = 390;
        ResizeMode = ResizeMode.CanResize;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        ShowInTaskbar = false;
        SetResourceReference(BackgroundProperty, "AppDialogBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");

        var rgb = (Color)ColorConverter.ConvertFromString(hex);
        FromRgb(rgb, out _hue, out _saturation, out _brightness);
        _surfaceImage = new Image
        {
            Width = SurfaceWidth, Height = SurfaceHeight, Source = _surfaceBitmap,
            Stretch = Stretch.None
        };
        _brightnessImage = new Image
        {
            Width = 18, Height = SurfaceHeight, Source = _brightnessBitmap,
            Stretch = Stretch.None
        };

        var root = new StackPanel { Margin = new Thickness(18) };
        root.Children.Add(new TextBlock
        {
            Text = roleName, FontSize = 16, FontWeight = FontWeights.SemiBold,
            Margin = new Thickness(0, 0, 0, 12)
        });

        var palette = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Left
        };
        _surfaceCanvas.Children.Add(_surfaceImage);
        _surfaceCanvas.Children.Add(_cursor);
        _brightnessCanvas.Children.Add(_brightnessImage);
        _brightnessCanvas.Children.Add(_brightnessCursor);
        palette.Children.Add(_surfaceCanvas);
        _brightnessCanvas.Margin = new Thickness(12, 0, 0, 0);
        palette.Children.Add(_brightnessCanvas);
        root.Children.Add(palette);

        _surfaceCanvas.MouseLeftButtonDown += (_, e) =>
        {
            _surfaceCanvas.CaptureMouse();
            PickSurface(e.GetPosition(_surfaceCanvas));
        };
        _surfaceCanvas.MouseMove += (_, e) =>
        {
            if (e.LeftButton == MouseButtonState.Pressed &&
                _surfaceCanvas.IsMouseCaptured)
                PickSurface(e.GetPosition(_surfaceCanvas));
        };
        _surfaceCanvas.MouseLeftButtonUp += (_, _) => _surfaceCanvas.ReleaseMouseCapture();
        _brightnessCanvas.MouseLeftButtonDown += (_, e) =>
        {
            _brightnessCanvas.CaptureMouse();
            PickBrightness(e.GetPosition(_brightnessCanvas));
        };
        _brightnessCanvas.MouseMove += (_, e) =>
        {
            if (e.LeftButton == MouseButtonState.Pressed &&
                _brightnessCanvas.IsMouseCaptured)
                PickBrightness(e.GetPosition(_brightnessCanvas));
        };
        _brightnessCanvas.MouseLeftButtonUp += (_, _) =>
            _brightnessCanvas.ReleaseMouseCapture();

        var hexRow = new StackPanel
        {
            Orientation = Orientation.Horizontal, Margin = new Thickness(0, 12, 0, 0)
        };
        hexRow.Children.Add(new TextBlock
        {
            Text = "Цвет:", VerticalAlignment = VerticalAlignment.Center,
            Margin = new Thickness(0, 0, 10, 0)
        });
        hexRow.Children.Add(_hexBox);
        _preview.Margin = new Thickness(12, 0, 0, 0);
        hexRow.Children.Add(_preview);
        root.Children.Add(hexRow);

        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Right,
            Margin = new Thickness(0, 15, 0, 0)
        };
        var apply = new Button
        {
            Content = "Применить", Padding = new Thickness(14, 7, 14, 7),
            MinWidth = 100, IsDefault = true
        };
        apply.Click += (_, _) =>
        {
            if (!ThemePalette.TryNormalize(_hexBox.Text, out var chosen))
            {
                AppDialog.Info(this, "Выбор цвета", "Укажите цвет в формате #RRGGBB.");
                return;
            }
            SelectedHex = chosen;
            DialogResult = true;
        };
        var cancel = new Button
        {
            Content = "Отмена", Padding = new Thickness(14, 7, 14, 7),
            IsCancel = true, Margin = new Thickness(8, 0, 0, 0)
        };
        buttons.Children.Add(apply);
        buttons.Children.Add(cancel);
        root.Children.Add(buttons);
        Content = new ScrollViewer
        {
            Content = root,
            VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
            HorizontalScrollBarVisibility = ScrollBarVisibility.Disabled
        };

        _hexBox.TextChanged += (_, _) =>
        {
            if (_updating || !ThemePalette.TryNormalize(_hexBox.Text, out var code))
                return;

            FromRgb((Color)ColorConverter.ConvertFromString(code),
                out _hue, out _saturation, out _brightness);
            RenderPalette();
            UpdateSelection(writeHex: false);
        };

        RenderPalette();
        UpdateSelection(writeHex: true);
        ThemeManager.AttachWindowChrome(this);
    }

    private void PickSurface(Point at)
    {
        _hue = Math.Clamp(at.X / (SurfaceWidth - 1), 0, 1) * 360;
        _saturation = 1 - Math.Clamp(at.Y / (SurfaceHeight - 1), 0, 1);
        // Hue/saturation affects the whole brightness strip, not only the swatch.
        RenderPalette();
        UpdateSelection(writeHex: true);
    }

    private void PickBrightness(Point at)
    {
        _brightness = 1 - Math.Clamp(at.Y / (SurfaceHeight - 1), 0, 1);
        RenderPalette();
        UpdateSelection(writeHex: true);
    }

    private void UpdateSelection(bool writeHex)
    {
        var rgb = ToRgb(_hue, _saturation, _brightness);
        var hex = $"#{rgb.R:X2}{rgb.G:X2}{rgb.B:X2}";
        if (writeHex)
        {
            _updating = true;
            _hexBox.Text = hex;
            _updating = false;
        }
        _preview.Background = new SolidColorBrush(rgb);
        Canvas.SetLeft(_cursor, Math.Clamp(_hue / 360 * (SurfaceWidth - 1) - 6,
            -4, SurfaceWidth - 8));
        Canvas.SetTop(_cursor, Math.Clamp((1 - _saturation) * (SurfaceHeight - 1) - 6,
            -4, SurfaceHeight - 8));
        Canvas.SetTop(_brightnessCursor,
            Math.Clamp((1 - _brightness) * (SurfaceHeight - 1) - 1, 0, SurfaceHeight - 3));
    }

    private void RenderPalette()
    {
        var pixels = new byte[SurfaceWidth * SurfaceHeight * 4];
        for (var y = 0; y < SurfaceHeight; y++)
        for (var x = 0; x < SurfaceWidth; x++)
        {
            var c = ToRgb(x * 360.0 / (SurfaceWidth - 1),
                1 - y / (double)(SurfaceHeight - 1), _brightness);
            var i = (y * SurfaceWidth + x) * 4;
            pixels[i] = c.B;
            pixels[i + 1] = c.G;
            pixels[i + 2] = c.R;
            pixels[i + 3] = 255;
        }
        _surfaceBitmap.WritePixels(new Int32Rect(0, 0, SurfaceWidth, SurfaceHeight),
            pixels, SurfaceWidth * 4, 0);

        var strip = new byte[18 * SurfaceHeight * 4];
        for (var y = 0; y < SurfaceHeight; y++)
        {
            var c = ToRgb(_hue, _saturation, 1 - y / (double)(SurfaceHeight - 1));
            for (var x = 0; x < 18; x++)
            {
                var i = (y * 18 + x) * 4;
                strip[i] = c.B;
                strip[i + 1] = c.G;
                strip[i + 2] = c.R;
                strip[i + 3] = 255;
            }
        }
        _brightnessBitmap.WritePixels(new Int32Rect(0, 0, 18, SurfaceHeight),
            strip, 18 * 4, 0);
    }

    private static Color ToRgb(double hue, double saturation, double value)
    {
        var c = value * saturation;
        var h = (hue % 360 + 360) % 360 / 60;
        var x = c * (1 - Math.Abs(h % 2 - 1));
        var (r, g, b) = h switch
        {
            < 1 => (c, x, 0.0),
            < 2 => (x, c, 0.0),
            < 3 => (0.0, c, x),
            < 4 => (0.0, x, c),
            < 5 => (x, 0.0, c),
            _ => (c, 0.0, x)
        };
        var m = value - c;
        return Color.FromRgb((byte)Math.Round((r + m) * 255),
            (byte)Math.Round((g + m) * 255), (byte)Math.Round((b + m) * 255));
    }

    private static void FromRgb(Color color, out double hue,
        out double saturation, out double brightness)
    {
        var r = color.R / 255.0;
        var g = color.G / 255.0;
        var b = color.B / 255.0;
        var high = Math.Max(r, Math.Max(g, b));
        var low = Math.Min(r, Math.Min(g, b));
        var span = high - low;
        brightness = high;
        saturation = high == 0 ? 0 : span / high;
        if (span == 0) { hue = 0; return; }
        if (high == r) hue = 60 * ((g - b) / span % 6);
        else if (high == g) hue = 60 * ((b - r) / span + 2);
        else hue = 60 * ((r - g) / span + 4);
        if (hue < 0) hue += 360;
    }
}
