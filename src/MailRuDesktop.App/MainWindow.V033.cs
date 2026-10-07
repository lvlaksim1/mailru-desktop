using System.ComponentModel;
using System.Runtime.InteropServices;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Input;
using System.Windows.Interop;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _v033Initialized;
    private bool _v033InitialFocusApplied;
    private TextBox? _v033SelectableSubject;
    private DependencyPropertyDescriptor? _v033ItemsSourceDescriptor;

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
        ConfigureMessageListTemplate();
        ConfigurePinnedSorting();

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

        if (_v033ItemsSourceDescriptor is not null)
            _v033ItemsSourceDescriptor.RemoveValueChanged(MessagesGrid, V033_ItemsSourceChanged);
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
            // Older Windows versions can ignore non-client refresh requests.
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

        MailWorkspace.ColumnDefinitions[0].Width = new GridLength(2, GridUnitType.Star);
        MailWorkspace.ColumnDefinitions[0].MinWidth = 320;
        MailWorkspace.ColumnDefinitions[2].Width = new GridLength(3, GridUnitType.Star);
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

    private void ConfigureMessageListTemplate()
    {
        try
        {
            var dictionary = (ResourceDictionary)XamlReader.Parse(MessageTemplateXaml);
            MessagesGrid.ItemTemplate = (DataTemplate)dictionary["V033MessageTemplate"];
            MessagesGrid.AddHandler(
                Button.ClickEvent,
                new RoutedEventHandler(V033_MessageActionButton_Click));
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("v033_message_template", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void V033_MessageActionButton_Click(object sender, RoutedEventArgs e)
    {
        if (e.Handled)
            return;

        var button = FindAncestor<Button>(e.OriginalSource as DependencyObject);
        if (button?.DataContext is not MailRuMessageSummary)
            return;

        switch (button.Name)
        {
            case "UnreadActionButton":
                MessageUnreadWithCounterButton_Click(button, e);
                break;
            case "FlagActionButton":
                MessageFlagButton_Click(button, e);
                break;
            case "PinActionButton":
                MessagePinButton_Click(button, e);
                break;
            case "ArchiveActionButton":
                MessageArchiveWithCounterButton_Click(button, e);
                break;
        }
    }

    private void ConfigurePinnedSorting()
    {
        _v033ItemsSourceDescriptor = DependencyPropertyDescriptor.FromProperty(
            ItemsControl.ItemsSourceProperty,
            typeof(ItemsControl));
        _v033ItemsSourceDescriptor?.AddValueChanged(MessagesGrid, V033_ItemsSourceChanged);
        ApplyPinnedSorting();
    }

    private void V033_ItemsSourceChanged(object? sender, EventArgs e) =>
        Dispatcher.BeginInvoke(
            DispatcherPriority.DataBind,
            new Action(ApplyPinnedSorting));

    private void ApplyPinnedSorting()
    {
        if (MessagesGrid.ItemsSource is null)
            return;

        var view = CollectionViewSource.GetDefaultView(MessagesGrid.ItemsSource);
        if (!view.CanSort)
            return;

        using (view.DeferRefresh())
        {
            view.SortDescriptions.Clear();
            view.SortDescriptions.Add(new SortDescription(
                nameof(MailRuMessageSummary.Pinned),
                ListSortDirection.Descending));
            view.SortDescriptions.Add(new SortDescription(
                nameof(MailRuMessageSummary.DateUnix),
                ListSortDirection.Descending));
        }
    }

    private static Button? FindAncestor<Button>(DependencyObject? source)
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

    private const string MessageTemplateXaml = """
<ResourceDictionary
    xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
    xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
    xmlns:local="clr-namespace:MailRuDesktop.App;assembly=MailRuDesktop.App">
    <BooleanToVisibilityConverter x:Key="BoolToVisibility"/>
    <local:MailDateConverter x:Key="MailDateConverter"/>
    <local:MailTimeConverter x:Key="MailTimeConverter"/>
    <local:SenderLineConverter x:Key="SenderLineConverter"/>
    <local:FirstLineConverter x:Key="FirstLineConverter"/>

    <DataTemplate x:Key="V033MessageTemplate">
        <Grid Height="58">
            <Grid.ColumnDefinitions>
                <ColumnDefinition Width="64"/>
                <ColumnDefinition Width="*"/>
                <ColumnDefinition Width="34"/>
            </Grid.ColumnDefinitions>

            <StackPanel Grid.Column="0"
                        VerticalAlignment="Center"
                        HorizontalAlignment="Center">
                <TextBlock FontSize="11"
                           HorizontalAlignment="Center"
                           Foreground="{DynamicResource AppMutedTextBrush}"
                           Text="{Binding DateUnix, Converter={StaticResource MailDateConverter}}"/>
                <TextBlock Margin="0,3,0,0"
                           FontSize="11"
                           HorizontalAlignment="Center"
                           Foreground="{DynamicResource AppMutedTextBrush}"
                           Text="{Binding DateUnix, Converter={StaticResource MailTimeConverter}}"/>
            </StackPanel>

            <Grid Grid.Column="1">
                <Grid.ColumnDefinitions>
                    <ColumnDefinition Width="26"/>
                    <ColumnDefinition Width="*"/>
                </Grid.ColumnDefinitions>
                <Grid.RowDefinitions>
                    <RowDefinition Height="19"/>
                    <RowDefinition Height="19"/>
                    <RowDefinition Height="19"/>
                </Grid.RowDefinitions>

                <Button x:Name="UnreadActionButton"
                        Grid.Row="0" Grid.Column="0"
                        Width="21" Height="19" Padding="0"
                        Tag="{Binding}"
                        ToolTip="Прочитано / непрочитано">
                    <TextBlock FontSize="13">
                        <TextBlock.Style>
                            <Style TargetType="TextBlock">
                                <Setter Property="Foreground" Value="{DynamicResource AppMutedTextBrush}"/>
                                <Setter Property="Text" Value="○"/>
                                <Style.Triggers>
                                    <DataTrigger Binding="{Binding Unread}" Value="True">
                                        <Setter Property="Text" Value="●"/>
                                        <Setter Property="Foreground" Value="{DynamicResource AppAccentBrush}"/>
                                    </DataTrigger>
                                </Style.Triggers>
                            </Style>
                        </TextBlock.Style>
                    </TextBlock>
                </Button>

                <Button x:Name="FlagActionButton"
                        Grid.Row="1" Grid.Column="0"
                        Width="21" Height="19" Padding="0"
                        Tag="{Binding}"
                        ToolTip="Флажок">
                    <TextBlock FontSize="15">
                        <TextBlock.Style>
                            <Style TargetType="TextBlock">
                                <Setter Property="Foreground" Value="{DynamicResource AppMutedTextBrush}"/>
                                <Setter Property="Text" Value="☆"/>
                                <Style.Triggers>
                                    <DataTrigger Binding="{Binding Flagged}" Value="True">
                                        <Setter Property="Text" Value="★"/>
                                        <Setter Property="Foreground" Value="#FFD54A"/>
                                    </DataTrigger>
                                </Style.Triggers>
                            </Style>
                        </TextBlock.Style>
                    </TextBlock>
                </Button>

                <Path Grid.Row="2" Grid.Column="0"
                      Width="15" Height="15"
                      VerticalAlignment="Center"
                      HorizontalAlignment="Center"
                      Visibility="{Binding HasAttachment, Converter={StaticResource BoolToVisibility}}"
                      Stretch="Uniform"
                      Stroke="{DynamicResource AppMutedTextBrush}"
                      StrokeThickness="1.5"
                      StrokeStartLineCap="Round"
                      StrokeEndLineCap="Round"
                      Data="M21.44,11.05 L12.25,20.24 C9.91,22.58 6.11,22.58 3.76,20.24 C1.42,17.90 1.42,14.10 3.76,11.75 L12.95,2.56 C14.51,1 17.05,1 18.61,2.56 C20.17,4.12 20.17,6.66 18.61,8.22 L9.41,17.41 C8.63,18.19 7.37,18.19 6.59,17.41 C5.81,16.63 5.81,15.37 6.59,14.59 L15.08,6.10"/>

                <TextBlock Grid.Row="0" Grid.Column="1"
                           VerticalAlignment="Center"
                           TextTrimming="CharacterEllipsis"
                           FontWeight="SemiBold">
                    <TextBlock.Text>
                        <MultiBinding Converter="{StaticResource SenderLineConverter}">
                            <Binding Path="SenderEmail"/>
                            <Binding Path="SenderName"/>
                        </MultiBinding>
                    </TextBlock.Text>
                </TextBlock>
                <TextBlock Grid.Row="1" Grid.Column="1"
                           VerticalAlignment="Center"
                           TextTrimming="CharacterEllipsis"
                           FontWeight="SemiBold"
                           Text="{Binding Subject}"/>
                <TextBlock Grid.Row="2" Grid.Column="1"
                           VerticalAlignment="Center"
                           TextTrimming="CharacterEllipsis"
                           Foreground="{DynamicResource AppMutedTextBrush}"
                           Text="{Binding Snippet, Converter={StaticResource FirstLineConverter}}"/>
            </Grid>

            <Grid Grid.Column="2" HorizontalAlignment="Stretch">
                <Grid.RowDefinitions>
                    <RowDefinition Height="29"/>
                    <RowDefinition Height="29"/>
                </Grid.RowDefinitions>

                <Button x:Name="PinActionButton"
                        Grid.Row="0"
                        Width="25" Height="23" Padding="2"
                        HorizontalAlignment="Right"
                        Tag="{Binding}"
                        ToolTip="Закрепить / открепить">
                    <Path Width="16" Height="18"
                          Stretch="Uniform"
                          StrokeThickness="1.45"
                          StrokeLineJoin="Round"
                          StrokeStartLineCap="Round"
                          StrokeEndLineCap="Round"
                          Data="M6,2 L18,2 L18,4 L16,6 L16,10 L19,13 L19,15 L13,15 L13,21 L12,24 L11,21 L11,15 L5,15 L5,13 L8,10 L8,6 L6,4 Z">
                        <Path.Style>
                            <Style TargetType="Path">
                                <Setter Property="Stroke" Value="{DynamicResource AppMutedTextBrush}"/>
                                <Setter Property="Fill" Value="Transparent"/>
                                <Style.Triggers>
                                    <DataTrigger Binding="{Binding Pinned}" Value="True">
                                        <Setter Property="Stroke" Value="#E5484D"/>
                                        <Setter Property="Fill" Value="#E5484D"/>
                                    </DataTrigger>
                                </Style.Triggers>
                            </Style>
                        </Path.Style>
                    </Path>
                </Button>

                <Button x:Name="ArchiveActionButton"
                        Grid.Row="1"
                        Width="25" Height="23" Padding="2"
                        HorizontalAlignment="Right"
                        Tag="{Binding}"
                        ToolTip="В архив">
                    <Path Width="14" Height="14"
                          Stretch="Uniform"
                          Stroke="{DynamicResource AppMutedTextBrush}"
                          StrokeThickness="1.4"
                          Data="M2,4 L14,4 M3,5 L13,5 L12,14 L4,14 Z M6,8 L10,8"/>
                </Button>
            </Grid>
        </Grid>
    </DataTemplate>
</ResourceDictionary>
""";
}
