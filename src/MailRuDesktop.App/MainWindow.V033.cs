using System.Runtime.InteropServices;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Media;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _v033Initialized;
    private bool _v033InitialFocusApplied;
    private TextBox? _v033SelectableSubject;

    protected override void OnSourceInitialized(EventArgs e)
    {
        base.OnSourceInitialized(e);
        RefreshMainWindowChrome();
    }

    protected override void OnContentRendered(EventArgs e)
    {
        base.OnContentRendered(e);

        if (!_v033Initialized)
        {
            _v033Initialized = true;
            ConfigureV033Ui();
        }

        Dispatcher.BeginInvoke(
            DispatcherPriority.ApplicationIdle,
            new Action(() =>
            {
                RefreshMainWindowChrome();
                if (!_v033InitialFocusApplied)
                {
                    _v033InitialFocusApplied = true;
                    Keyboard.Focus(FilterQueryTextBox);
                }
            }));
    }

    private void ConfigureV033Ui()
    {
        CollapseSidebarApplicationTitle();
        ConfigureResizableMailColumns();
        ConfigureSelectablePreviewSubject();
        ConfigureDateRangeFilter();

        Activated += V033_Activated;
        ThemeManager.ThemeChanged += V033_ThemeChanged;
        Closed += V033_Closed;

        RefreshMainWindowChrome();
    }

    private void V033_Activated(object? sender, EventArgs e)
    {
        Dispatcher.BeginInvoke(
            DispatcherPriority.Render,
            new Action(RefreshMainWindowChrome));
    }

    private void V033_ThemeChanged(object? sender, EventArgs e)
    {
        ApplyDatePickerTheme(FilterFromDatePicker);
        ApplyDatePickerTheme(FilterToDatePicker);
        RefreshMainWindowChrome();
    }

    private void V033_Closed(object? sender, EventArgs e)
    {
        Activated -= V033_Activated;
        ThemeManager.ThemeChanged -= V033_ThemeChanged;
        Closed -= V033_Closed;

    }

    private void RefreshMainWindowChrome()
    {
        ThemeManager.RefreshWindowChrome(this);

        try
        {
            var handle = new WindowInteropHelper(this).Handle;
            if (handle == IntPtr.Zero)
                return;

            SetWindowPos(
                handle,
                IntPtr.Zero,
                0,
                0,
                0,
                0,
                SwpNoSize | SwpNoMove | SwpNoZOrder | SwpNoActivate | SwpFrameChanged);
        }
        catch
        {
        }
    }

    private void CollapseSidebarApplicationTitle()
    {
        foreach (var child in EnumerateVisualDescendants(this))
        {
            if (child is TextBlock textBlock &&
                string.Equals(textBlock.Text, "MailRu Desktop", StringComparison.Ordinal))
            {
                textBlock.Visibility = Visibility.Collapsed;
                textBlock.Margin = new Thickness(0);
                break;
            }
        }
    }

    private void ConfigureResizableMailColumns()
    {
        if (MailWorkspace.ColumnDefinitions.Count < 3)
            return;

        // Widths are restored by MainWindow.UserInterfaceState. This compatibility
        // layer must only enforce safe minimums and splitter behavior; assigning
        // star widths here used to erase the user's saved divider position.
        MailWorkspace.ColumnDefinitions[0].MinWidth = 320;
        MailWorkspace.ColumnDefinitions[2].MinWidth = 360;

        foreach (var splitter in MailWorkspace.Children.OfType<GridSplitter>())
        {
            if (Grid.GetColumn(splitter) != 1)
                continue;

            splitter.ResizeDirection = GridResizeDirection.Columns;
            splitter.ResizeBehavior = GridResizeBehavior.PreviousAndNext;
            splitter.HorizontalAlignment = HorizontalAlignment.Stretch;
        }
    }

    private void ConfigureSelectablePreviewSubject()
    {
        if (SelectedSubjectText.Parent is not Panel parent)
            return;

        var index = parent.Children.IndexOf(SelectedSubjectText);
        if (index < 0)
            return;

        _v033SelectableSubject = new TextBox
        {
            Padding = new Thickness(0),
            Margin = SelectedSubjectText.Margin,
            BorderThickness = new Thickness(0),
            Background = Brushes.Transparent,
            IsReadOnly = true,
            IsReadOnlyCaretVisible = true,
            TextWrapping = TextWrapping.Wrap,
            FontSize = 22,
            FontWeight = FontWeights.SemiBold,
            AcceptsReturn = false,
            HorizontalScrollBarVisibility = ScrollBarVisibility.Disabled,
            VerticalScrollBarVisibility = ScrollBarVisibility.Disabled
        };
        _v033SelectableSubject.SetResourceReference(Control.ForegroundProperty, "AppTextBrush");
        _v033SelectableSubject.SetBinding(
            TextBox.TextProperty,
            new Binding(nameof(TextBlock.Text))
            {
                Source = SelectedSubjectText,
                Mode = BindingMode.OneWay
            });

        parent.Children.Insert(index, _v033SelectableSubject);
        SelectedSubjectText.Visibility = Visibility.Collapsed;
    }

    private void ConfigureDateRangeFilter()
    {
        if (FilterFromDatePicker.Parent is not Grid host)
            return;

        host.Visibility = Visibility.Visible;
        host.Margin = new Thickness(0, 0, 0, 8);
        host.Children.Clear();

        var row = new WrapPanel
        {
            Orientation = Orientation.Horizontal,
            VerticalAlignment = VerticalAlignment.Center
        };

        var caption = CreateMutedText("Период:");
        caption.Margin = new Thickness(0, 5, 8, 0);
        row.Children.Add(caption);

        var fromCaption = CreateMutedText("с");
        fromCaption.Margin = new Thickness(0, 5, 5, 0);
        row.Children.Add(fromCaption);

        ConfigureDatePicker(FilterFromDatePicker);
        FilterFromDatePicker.Margin = new Thickness(0, 0, 10, 0);
        row.Children.Add(FilterFromDatePicker);

        var toCaption = CreateMutedText("по");
        toCaption.Margin = new Thickness(0, 5, 5, 0);
        row.Children.Add(toCaption);

        ConfigureDatePicker(FilterToDatePicker);
        row.Children.Add(FilterToDatePicker);

        host.Children.Add(row);

        FilterFromDatePicker.SelectedDateChanged += V033_DateFilterChanged;
        FilterToDatePicker.SelectedDateChanged += V033_DateFilterChanged;
    }

    private TextBlock CreateMutedText(string text)
    {
        var block = new TextBlock
        {
            Text = text,
            VerticalAlignment = VerticalAlignment.Center
        };
        block.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        return block;
    }

    private void ConfigureDatePicker(DatePicker picker)
    {
        picker.Width = 132;
        picker.Padding = new Thickness(6, 3, 6, 3);
        picker.SelectedDateFormat = DatePickerFormat.Short;
        picker.SelectedDateChanged -= V033_DateFilterChanged;
        ApplyDatePickerTheme(picker);
    }

    private void ApplyDatePickerTheme(DatePicker picker)
    {
        var panel = (Brush)FindResource("AppPanelBrush");
        var control = (Brush)FindResource("AppControlBrush");
        var hover = (Brush)FindResource("AppControlHoverBrush");
        var text = (Brush)FindResource("AppTextBrush");
        var muted = (Brush)FindResource("AppMutedTextBrush");
        var border = (Brush)FindResource("AppBorderBrush");
        var selection = (Brush)FindResource("AppSelectionBrush");
        var selectionText = (Brush)FindResource("AppSelectionTextBrush");

        picker.Background = control;
        picker.Foreground = text;
        picker.BorderBrush = border;

        var calendarStyle = new Style(typeof(Calendar));
        calendarStyle.Setters.Add(new Setter(Control.BackgroundProperty, panel));
        calendarStyle.Setters.Add(new Setter(Control.ForegroundProperty, text));
        calendarStyle.Setters.Add(new Setter(Control.BorderBrushProperty, border));

        var itemStyle = new Style(typeof(CalendarItem));
        itemStyle.Setters.Add(new Setter(Control.BackgroundProperty, panel));
        itemStyle.Setters.Add(new Setter(Control.ForegroundProperty, text));
        itemStyle.Setters.Add(new Setter(Control.BorderBrushProperty, border));
        calendarStyle.Resources[typeof(CalendarItem)] = itemStyle;

        var dayStyle = new Style(typeof(CalendarDayButton));
        dayStyle.Setters.Add(new Setter(Control.BackgroundProperty, Brushes.Transparent));
        dayStyle.Setters.Add(new Setter(Control.ForegroundProperty, text));
        dayStyle.Setters.Add(new Setter(Control.BorderBrushProperty, Brushes.Transparent));
        dayStyle.Triggers.Add(new Trigger
        {
            Property = CalendarDayButton.IsMouseOverProperty,
            Value = true,
            Setters = { new Setter(Control.BackgroundProperty, hover) }
        });
        dayStyle.Triggers.Add(new Trigger
        {
            Property = CalendarDayButton.IsSelectedProperty,
            Value = true,
            Setters =
            {
                new Setter(Control.BackgroundProperty, selection),
                new Setter(Control.ForegroundProperty, selectionText)
            }
        });
        dayStyle.Triggers.Add(new Trigger
        {
            Property = CalendarDayButton.IsInactiveProperty,
            Value = true,
            Setters = { new Setter(Control.ForegroundProperty, muted) }
        });
        calendarStyle.Resources[typeof(CalendarDayButton)] = dayStyle;

        var monthYearStyle = new Style(typeof(CalendarButton));
        monthYearStyle.Setters.Add(new Setter(Control.BackgroundProperty, Brushes.Transparent));
        monthYearStyle.Setters.Add(new Setter(Control.ForegroundProperty, text));
        monthYearStyle.Setters.Add(new Setter(Control.BorderBrushProperty, Brushes.Transparent));
        monthYearStyle.Triggers.Add(new Trigger
        {
            Property = CalendarButton.IsMouseOverProperty,
            Value = true,
            Setters = { new Setter(Control.BackgroundProperty, hover) }
        });
        calendarStyle.Resources[typeof(CalendarButton)] = monthYearStyle;

        picker.CalendarStyle = calendarStyle;
    }

    private void V033_DateFilterChanged(object? sender, SelectionChangedEventArgs e)
    {
        if (IsLoaded)
            ApplyFilters();
    }

    private static Button? FindAncestorButton(DependencyObject? source)
    {
        while (source is not null)
        {
            if (source is Button button)
                return button;
            source = VisualTreeHelper.GetParent(source);
        }

        return null;
    }

    private static IEnumerable<DependencyObject> EnumerateVisualDescendants(DependencyObject root)
    {
        var count = VisualTreeHelper.GetChildrenCount(root);
        for (var i = 0; i < count; i++)
        {
            var child = VisualTreeHelper.GetChild(root, i);
            yield return child;
            foreach (var descendant in EnumerateVisualDescendants(child))
                yield return descendant;
        }
    }

    private const uint SwpNoSize = 0x0001;
    private const uint SwpNoMove = 0x0002;
    private const uint SwpNoZOrder = 0x0004;
    private const uint SwpNoActivate = 0x0010;
    private const uint SwpFrameChanged = 0x0020;

    [DllImport("user32.dll", SetLastError = true)]
    private static extern bool SetWindowPos(
        IntPtr hWnd,
        IntPtr hWndInsertAfter,
        int x,
        int y,
        int cx,
        int cy,
        uint flags);


}
