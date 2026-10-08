using System.Collections;
using System.ComponentModel;
using System.Globalization;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _modernMailListConfigured;
    private DependencyPropertyDescriptor? _modernMailItemsSourceDescriptor;

    private void ConfigureModernMailList()
    {
        var dictionary = (ResourceDictionary)XamlReader.Parse(ModernMailListTemplateXaml);
        MessagesGrid.ItemTemplate = (DataTemplate)dictionary["ModernMailRowTemplate"];
        MessagesGrid.ItemContainerStyle = (Style)dictionary["ModernMailRowItemStyle"];

        MessagesGrid.GroupStyle.Clear();
        MessagesGrid.GroupStyle.Add(new GroupStyle
        {
            HeaderTemplate = (DataTemplate)dictionary["ModernDateHeaderTemplate"],
            HidesIfEmpty = true
        });

        if (!_modernMailListConfigured)
        {
            _modernMailListConfigured = true;

            _modernMailItemsSourceDescriptor = DependencyPropertyDescriptor.FromProperty(
                ItemsControl.ItemsSourceProperty,
                typeof(ItemsControl));
            _modernMailItemsSourceDescriptor?.AddValueChanged(
                MessagesGrid,
                ModernMailItemsSourceChanged);

            MessagesGrid.AddHandler(
                Button.ClickEvent,
                new RoutedEventHandler(ModernMailListButton_Click),
                handledEventsToo: true);
            MessagesGrid.SelectionChanged += ModernMailListSelectionChanged;
            MessagesGrid.PreviewMouseRightButtonDown += ModernMailList_PreviewMouseRightButtonDown;
            Closed += ModernMailListClosed;

        }

        RefreshModernMailListView();
    }

    private void ModernMailListClosed(object? sender, EventArgs e)
    {
        _modernMailItemsSourceDescriptor?.RemoveValueChanged(
            MessagesGrid,
            ModernMailItemsSourceChanged);
        MessagesGrid.SelectionChanged -= ModernMailListSelectionChanged;
        MessagesGrid.PreviewMouseRightButtonDown -= ModernMailList_PreviewMouseRightButtonDown;
        Closed -= ModernMailListClosed;
    }

    private void ModernMailItemsSourceChanged(object? sender, EventArgs e) =>
        ScheduleModernMailListRefresh();

    private void ScheduleModernMailListRefresh() =>
        Dispatcher.BeginInvoke(
            DispatcherPriority.ApplicationIdle,
            new Action(RefreshModernMailListView));

    private void RefreshModernMailListView()
    {
        RefreshThreadCountIndex();

        if (MessagesGrid.ItemsSource is null)
            return;

        var view = CollectionViewSource.GetDefaultView(MessagesGrid.ItemsSource);
        if (view is null)
            return;

        using (view.DeferRefresh())
        {
            if (view.CanGroup)
            {
                view.GroupDescriptions.Clear();
                view.GroupDescriptions.Add(new MailSectionGroupDescription());
            }

            if (view is ListCollectionView listView)
            {
                listView.SortDescriptions.Clear();
                listView.CustomSort = new MailDateThenPinnedComparer();
            }
            else if (view.CanSort)
            {
                view.SortDescriptions.Clear();
                view.SortDescriptions.Add(new SortDescription(
                    nameof(MailRuMessageSummary.DateUnix),
                    ListSortDirection.Descending));
            }
        }

        view.Refresh();
    }

    private void RefreshThreadCountIndex()
    {
        var raw = ResponseTextBox.Text;
        if (string.IsNullOrWhiteSpace(raw))
        {
            MailThreadCountRegistry.Replace(new Dictionary<string, int>(StringComparer.Ordinal));
            return;
        }

        try
        {
            MailThreadCountRegistry.Replace(BuildThreadCountIndex(raw));
        }
        catch (JsonException ex)
        {
            MailThreadCountRegistry.Replace(new Dictionary<string, int>(StringComparer.Ordinal));
            DiagnosticLog.Write("thread_count_index", ex.Message);
        }
    }

    private static Dictionary<string, int> BuildThreadCountIndex(string payload)
    {
        using var document = JsonDocument.Parse(payload);
        var result = new Dictionary<string, int>(StringComparer.Ordinal);
        IndexThreadArrays(document.RootElement, result);
        return result;
    }

    private static void IndexThreadArrays(
        JsonElement element,
        Dictionary<string, int> result)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (element.TryGetProperty("threads", out var threads) &&
                threads.ValueKind == JsonValueKind.Array)
            {
                foreach (var thread in threads.EnumerateArray())
                    IndexThread(thread, result);
            }

            foreach (var property in element.EnumerateObject())
            {
                if (!property.NameEquals("threads"))
                    IndexThreadArrays(property.Value, result);
            }

            return;
        }

        if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                IndexThreadArrays(item, result);
        }
    }

    private static void IndexThread(
        JsonElement thread,
        Dictionary<string, int> result)
    {
        if (thread.ValueKind != JsonValueKind.Object)
            return;

        var count =
            ReadPositiveThreadCount(thread, "messages_count") ??
            ReadPositiveThreadCount(thread, "message_count") ??
            ReadPositiveThreadCount(thread, "messages_total") ??
            ReadPositiveThreadCount(thread, "count") ??
            0;

        var ids = new HashSet<string>(StringComparer.Ordinal);
        AddThreadId(thread, "id", ids);
        AddThreadId(thread, "message_id_last", ids);

        if (thread.TryGetProperty("base_message", out var baseMessage) &&
            baseMessage.ValueKind == JsonValueKind.Object)
        {
            AddThreadId(baseMessage, "id", ids);
            AddThreadId(baseMessage, "message_id_last", ids);
            count = Math.Max(
                count,
                ReadPositiveThreadCount(baseMessage, "messages_count") ??
                ReadPositiveThreadCount(baseMessage, "message_count") ??
                0);
        }

        if (thread.TryGetProperty("messages", out var messages) &&
            messages.ValueKind == JsonValueKind.Array)
        {
            count = Math.Max(count, messages.GetArrayLength());
            foreach (var message in messages.EnumerateArray())
            {
                if (message.ValueKind != JsonValueKind.Object)
                    continue;

                AddThreadId(message, "id", ids);
                AddThreadId(message, "message_id_last", ids);
            }
        }

        if (thread.TryGetProperty("representations", out var representations) &&
            representations.ValueKind == JsonValueKind.Array)
        {
            foreach (var representation in representations.EnumerateArray())
            {
                if (representation.ValueKind != JsonValueKind.Object)
                    continue;

                AddThreadId(representation, "id", ids);
                AddThreadId(representation, "message_id_last", ids);
                count = Math.Max(
                    count,
                    ReadPositiveThreadCount(representation, "messages_count") ??
                    ReadPositiveThreadCount(representation, "message_count") ??
                    0);
            }
        }

        count = Math.Max(count, 1);
        foreach (var id in ids)
        {
            if (!result.TryGetValue(id, out var existing) || count > existing)
                result[id] = count;
        }
    }

    private static int? ReadPositiveThreadCount(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        if (value.ValueKind == JsonValueKind.Number &&
            value.TryGetInt32(out var number) &&
            number > 0)
        {
            return number;
        }

        if (value.ValueKind == JsonValueKind.String &&
            int.TryParse(value.GetString(), NumberStyles.Integer, CultureInfo.InvariantCulture, out number) &&
            number > 0)
        {
            return number;
        }

        return null;
    }

    private static void AddThreadId(
        JsonElement element,
        string propertyName,
        HashSet<string> ids)
    {
        if (!element.TryGetProperty(propertyName, out var value))
            return;

        var id = value.ValueKind switch
        {
            JsonValueKind.String => value.GetString(),
            JsonValueKind.Number => value.GetRawText(),
            _ => null
        };

        if (!string.IsNullOrWhiteSpace(id))
            ids.Add(id);
    }

    private void ModernMailList_PreviewMouseRightButtonDown(
        object sender,
        MouseButtonEventArgs e)
    {
        if (e.OriginalSource is not DependencyObject source ||
            ItemsControl.ContainerFromElement(MessagesGrid, source) is not ListBoxItem item ||
            item.DataContext is not MailRuMessageSummary message)
        {
            return;
        }

        var menu = CreateCompactContextMenu();

        var pinItem = CreateCompactMenuItem(
            message.Pinned ? "Открепить" : "Закрепить");
        pinItem.Click += async (_, _) => await SetPinnedFromContextAsync(message);
        menu.Items.Add(pinItem);

        var archiveItem = CreateCompactMenuItem("Добавить в архив");
        archiveItem.Click += async (_, _) =>
        {
            var folders = (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>)?.ToArray()
                ?? Array.Empty<MailRuFolderSummary>();
            var archive = folders.FirstOrDefault(folder =>
                folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
                folder.Name.Equals("Архив", StringComparison.CurrentCultureIgnoreCase) ||
                folder.Id == 500010);

            await MoveMessageFromContextAsync(
                message,
                archive?.Id ?? 500010,
                "Письмо перемещено в архив.");
        };
        menu.Items.Add(archiveItem);

        var deleteItem = CreateCompactMenuItem("Удалить");
        deleteItem.Click += async (_, _) =>
        {
            if ((message.FolderId ?? _currentFolderId) == 500002)
            {
                FolderStatusText.Text = "Письмо уже находится в Корзине.";
                return;
            }

            await MoveMessageFromContextAsync(
                message,
                500002,
                "Письмо перемещено в Корзину.");
        };
        menu.Items.Add(deleteItem);

        if (CurrentFolderIsOutbox)
        {
            var immediate = CreateCompactMenuItem("Отправить сейчас");
            immediate.IsEnabled = false;
            immediate.ToolTip = "Серверная команда без повторной отправки ещё не подтверждена.";
            menu.Items.Add(immediate);
        }

        item.ContextMenu = menu;
        menu.PlacementTarget = item;
        menu.IsOpen = true;
        e.Handled = true;
    }

    private async Task SetPinnedFromContextAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        try
        {
            var makePinned = !message.Pinned;
            var result = await _mailRu.SetPinnedAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                message.FolderId ?? _currentFolderId,
                makePinned);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение закрепления.";
                return;
            }

            ReplaceMessage(message, message with { Pinned = makePinned });
            FolderStatusText.Text = makePinned
                ? "Письмо закреплено."
                : "Письмо откреплено.";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения закрепления.";
            DiagnosticLog.Write(
                "message_pin_context",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async Task MoveMessageFromContextAsync(
        MailRuMessageSummary message,
        int destinationFolderId,
        string successText)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        var selectedId = (MessagesGrid.SelectedItem as MailRuMessageSummary)?.Id;

        try
        {
            var result = await _mailRu.MoveMessagesAsync(
                _accessToken,
                new[] { message.Id },
                destinationFolderId);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил перемещение.";
                return;
            }

            if (_currentFolderId == 0 && message.Unread)
                AdjustActiveInboxUnread(-1);

            _currentMessages.RemoveAll(item =>
                string.Equals(item.Id, message.Id, StringComparison.Ordinal));
            ApplyFilters();

            if (string.Equals(selectedId, message.Id, StringComparison.Ordinal))
                ClearSelectedMessage();

            FolderStatusText.Text = successText;
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка перемещения письма.";
            DiagnosticLog.Write(
                "message_context_move",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void ModernMailListButton_Click(object sender, RoutedEventArgs e)
    {
        var button = FindAncestorButton(e.OriginalSource as DependencyObject);
        if (button?.DataContext is not MailRuMessageSummary message)
            return;

        switch (button.Name)
        {
            case "ModernUnreadActionButton":
                await SetUnreadPreservingSelectionAsync(message, !message.Unread);
                e.Handled = true;
                break;

            case "FlagActionButton":
                MessageFlagButton_Click(button, e);
                break;
        }
    }

    private void ModernMailListSelectionChanged(object sender, SelectionChangedEventArgs e)
    {
    }

    private async Task SetUnreadPreservingSelectionAsync(
        MailRuMessageSummary message,
        bool makeUnread)
    {
        if (string.IsNullOrWhiteSpace(_accessToken) || message.Unread == makeUnread)
            return;

        var previousUnread = message.Unread;

        try
        {
            var result = await _mailRu.SetUnreadAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                makeUnread);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение статуса.";
                return;
            }

            if (makeUnread)
                _previewAutoReadMessageId = null;

            ReplaceMessage(message, message with { Unread = makeUnread });

            if (_currentFolderId == 0 && previousUnread != makeUnread)
                AdjustActiveInboxUnread(makeUnread ? 1 : -1);

            FolderStatusText.Text = makeUnread
                ? "Письмо помечено непрочитанным."
                : "Письмо помечено прочитанным.";

        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения статуса.";
            DiagnosticLog.Write("message_marks", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private const string ModernMailListTemplateXaml = """
<ResourceDictionary
    xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
    xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
    xmlns:local="clr-namespace:MailRuDesktop.App;assembly=MailRuDesktop.App">
    <BooleanToVisibilityConverter x:Key="BoolToVisibility"/>
    <local:MailTimeConverter x:Key="MailTimeConverter"/>
    <local:FirstLineConverter x:Key="FirstLineConverter"/>
    <local:ThreadCountConverter x:Key="ThreadCountConverter"/>
    <local:ThreadCountVisibilityConverter x:Key="ThreadCountVisibilityConverter"/>
    <local:MailActivePreviewConverter x:Key="MailActivePreviewConverter"/>

    <Style x:Key="ModernMailRowItemStyle" TargetType="{x:Type ListBoxItem}">
        <Setter Property="HorizontalContentAlignment" Value="Stretch"/>
        <Setter Property="Padding" Value="0"/>
        <Setter Property="Margin" Value="0"/>
        <Setter Property="BorderThickness" Value="0"/>
        <Setter Property="Background" Value="Transparent"/>
        <Setter Property="Template">
            <Setter.Value>
                <ControlTemplate TargetType="{x:Type ListBoxItem}">
                    <Border x:Name="Row"
                            Padding="2,0"
                            Background="Transparent"
                            BorderBrush="{DynamicResource AppBorderBrush}"
                            BorderThickness="0,0,0,1"
                            SnapsToDevicePixels="True">
                        <ContentPresenter HorizontalAlignment="Stretch"/>
                    </Border>
                    <ControlTemplate.Triggers>
                        <Trigger Property="IsMouseOver" Value="True">
                            <Setter TargetName="Row"
                                    Property="Background"
                                    Value="{DynamicResource AppControlHoverBrush}"/>
                        </Trigger>
                        <Trigger Property="IsSelected" Value="True">
                            <Setter TargetName="Row"
                                    Property="Background"
                                    Value="{DynamicResource AppSelectionBrush}"/>
                        </Trigger>
                        <DataTrigger Value="True">
                            <DataTrigger.Binding>
                                <MultiBinding Converter="{StaticResource MailActivePreviewConverter}">
                                    <Binding Path="Id"/>
                                    <Binding Path="ActiveId"
                                             Source="{x:Static local:MailPreviewState.Instance}"/>
                                </MultiBinding>
                            </DataTrigger.Binding>
                            <Setter TargetName="Row" Property="BorderBrush"
                                    Value="{DynamicResource AppAccentBrush}"/>
                            <Setter TargetName="Row" Property="BorderThickness"
                                    Value="3,0,0,1"/>
                        </DataTrigger>
                    </ControlTemplate.Triggers>
                </ControlTemplate>
            </Setter.Value>
        </Setter>
    </Style>

    <DataTemplate x:Key="ModernDateHeaderTemplate">
        <Border Padding="4,10,4,5"
                Background="{DynamicResource AppWindowBrush}">
            <TextBlock Text="{Binding Name}"
                       FontSize="{DynamicResource AppFontBodySize}"
                       FontWeight="SemiBold"
                       Foreground="{DynamicResource AppMutedTextBrush}"/>
        </Border>
    </DataTemplate>

    <DataTemplate x:Key="ModernMailRowTemplate">
        <Grid Height="42">
            <Grid.ColumnDefinitions>
                <ColumnDefinition Width="30"/>
                <ColumnDefinition MinWidth="44"
                                  Width="{Binding Time, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="24"
                                  Width="{Binding Flag, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="24"
                                  Width="{Binding Unread, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="26"
                                  Width="{Binding ThreadCount, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="24"
                                  Width="{Binding Attachment, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="90"
                                  Width="{Binding Sender, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
                <ColumnDefinition Width="4"/>
                <ColumnDefinition MinWidth="140"
                                  Width="{Binding Subject, Source={x:Static local:MailColumnLayout.Instance}, Mode=TwoWay}"/>
            </Grid.ColumnDefinitions>

            <CheckBox x:Name="BulkSelectCheckBox" Grid.Column="0" Width="16" Height="16"
                      HorizontalAlignment="Center" VerticalAlignment="Center"
                      ToolTip="Выделить письмо"
                      IsChecked="{Binding IsSelected, Mode=TwoWay, RelativeSource={RelativeSource AncestorType={x:Type ListBoxItem}}}"/>

            <TextBlock Grid.Column="1"
                       Margin="3,0,5,0"
                       VerticalAlignment="Center"
                       HorizontalAlignment="Right"
                       Foreground="{DynamicResource AppMutedTextBrush}"
                       Text="{Binding DateUnix, Converter={StaticResource MailTimeConverter}}"/>

            <GridSplitter Grid.Column="2"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <Button x:Name="FlagActionButton"
                    Grid.Column="3"
                    Tag="{Binding}"
                    Width="26"
                    Height="40"
                    Padding="0"
                    Background="Transparent"
                    BorderThickness="0"
                    ToolTip="Флажок">
                <TextBlock FontSize="{DynamicResource AppFontEmphasisSize}">
                    <TextBlock.Style>
                        <Style TargetType="TextBlock">
                            <Setter Property="Text" Value="☆"/>
                            <Setter Property="Foreground"
                                    Value="{DynamicResource AppMutedTextBrush}"/>
                            <Style.Triggers>
                                <DataTrigger Binding="{Binding Flagged}" Value="True">
                                    <Setter Property="Text" Value="★"/>
                                    <Setter Property="Foreground" Value="{DynamicResource AppStarBrush}"/>
                                </DataTrigger>
                            </Style.Triggers>
                        </Style>
                    </TextBlock.Style>
                </TextBlock>
            </Button>

            <GridSplitter Grid.Column="4"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <Button x:Name="ModernUnreadActionButton"
                    Grid.Column="5"
                    Tag="{Binding}"
                    Width="24"
                    Height="40"
                    Padding="0"
                    Background="Transparent"
                    BorderThickness="0"
                    ToolTip="Прочитано / непрочитано">
                <Ellipse Width="8"
                         Height="8"
                         StrokeThickness="1.4">
                    <Ellipse.Style>
                        <Style TargetType="Ellipse">
                            <Setter Property="Stroke"
                                    Value="{DynamicResource AppMutedTextBrush}"/>
                            <Setter Property="Fill"
                                    Value="Transparent"/>
                            <Style.Triggers>
                                <DataTrigger Binding="{Binding Unread}" Value="True">
                                    <Setter Property="Stroke"
                                            Value="{DynamicResource AppAccentBrush}"/>
                                    <Setter Property="Fill"
                                            Value="{DynamicResource AppAccentBrush}"/>
                                </DataTrigger>
                            </Style.Triggers>
                        </Style>
                    </Ellipse.Style>
                </Ellipse>
            </Button>

            <GridSplitter Grid.Column="6"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <Border Grid.Column="7"
                    MinWidth="22"
                    Height="22"
                    Margin="4,0"
                    Padding="4,0"
                    VerticalAlignment="Center"
                    HorizontalAlignment="Center"
                    CornerRadius="11"
                    Background="{DynamicResource AppControlBrush}"
                    Visibility="{Binding Id, Converter={StaticResource ThreadCountVisibilityConverter}}">
                <TextBlock HorizontalAlignment="Center"
                           VerticalAlignment="Center"
                           FontSize="{DynamicResource AppFontTinySize}"
                           Text="{Binding Id, Converter={StaticResource ThreadCountConverter}}"/>
            </Border>

            <GridSplitter Grid.Column="8"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <Path Grid.Column="9"
                  Width="14"
                  Height="14"
                  VerticalAlignment="Center"
                  HorizontalAlignment="Center"
                  Visibility="{Binding HasAttachment, Converter={StaticResource BoolToVisibility}}"
                  Stretch="Uniform"
                  Stroke="{DynamicResource AppMutedTextBrush}"
                  StrokeThickness="1.5"
                  StrokeStartLineCap="Round"
                  StrokeEndLineCap="Round"
                  Data="M21.44,11.05 L12.25,20.24 C9.91,22.58 6.11,22.58 3.76,20.24 C1.42,17.90 1.42,14.10 3.76,11.75 L12.95,2.56 C14.51,1 17.05,1 18.61,2.56 C20.17,4.12 20.17,6.66 18.61,8.22 L9.41,17.41 C8.63,18.19 7.37,18.19 6.59,17.41 C5.81,16.63 5.81,15.37 6.59,14.59 L15.08,6.10"/>

            <GridSplitter Grid.Column="10"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <Grid Grid.Column="11"
                  Margin="5,0,8,0"
                  VerticalAlignment="Center">
                <Grid.ColumnDefinitions>
                    <ColumnDefinition Width="18"/>
                    <ColumnDefinition Width="*"/>
                </Grid.ColumnDefinitions>
                <Border Width="16"
                        Height="16"
                        CornerRadius="8"
                        VerticalAlignment="Center"
                        Background="{DynamicResource AppControlHoverBrush}">
                    <Grid>
                    <TextBlock HorizontalAlignment="Center"
                               VerticalAlignment="Center"
                               FontSize="{DynamicResource AppFontTinySize}"
                               FontWeight="SemiBold"
                               Text="{Binding SenderInitials}"/>
                    <Image Width="16" Height="16" Stretch="UniformToFill"
                           Source="{Binding AvatarUrl}"/>
                </Grid>
                </Border>
                <TextBlock Grid.Column="1"
                           Margin="5,0,0,0"
                           VerticalAlignment="Center"
                           TextTrimming="CharacterEllipsis"
                           Text="{Binding SenderDisplay}"/>
            </Grid>

            <GridSplitter Grid.Column="12"
                          Width="4"
                          HorizontalAlignment="Stretch"
                          VerticalAlignment="Stretch"
                          Background="Transparent"
                          Cursor="SizeWE"
                          ResizeDirection="Columns"
                          ResizeBehavior="PreviousAndNext"/>

            <TextBlock Grid.Column="13"
                       Margin="7,0,8,0"
                       VerticalAlignment="Center"
                       TextTrimming="CharacterEllipsis">
                <TextBlock.Style>
                    <Style TargetType="TextBlock">
                        <Setter Property="FontWeight" Value="Normal"/>
                        <Style.Triggers>
                            <DataTrigger Binding="{Binding Unread}" Value="True">
                                <Setter Property="FontWeight" Value="SemiBold"/>
                            </DataTrigger>
                        </Style.Triggers>
                    </Style>
                </TextBlock.Style>
                <Run Text="{Binding Subject}"/>
                <Run Text="  "/>
                <Run Foreground="{DynamicResource AppMutedTextBrush}"
                     Text="{Binding Snippet, Converter={StaticResource FirstLineConverter}}"/>
            </TextBlock>
        </Grid>

    </DataTemplate>
</ResourceDictionary>
""";
}

public sealed class MailDateGroupConverter : IValueConverter
{
    private static readonly CultureInfo Russian = CultureInfo.GetCultureInfo("ru-RU");

    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        if (value is not long unix)
            return "Без даты";

        try
        {
            var text = DateTimeOffset
                .FromUnixTimeSeconds(unix)
                .ToLocalTime()
                .ToString("dddd, d MMMM yyyy", Russian);

            return text.Length == 0
                ? "Без даты"
                : char.ToUpper(text[0], Russian) + text[1..];
        }
        catch
        {
            return "Без даты";
        }
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;
}

public sealed class MailSectionGroupDescription : GroupDescription
{
    private static readonly MailDateGroupConverter DateConverter = new();

    public override object GroupNameFromItem(
        object item,
        int level,
        CultureInfo culture)
    {
        if (item is not MailRuMessageSummary message)
            return "Без даты";

        if (message.Pinned)
            return "Закреплённые";

        if (message.DateUnix is null)
            return "Без даты";

        return DateConverter.Convert(
            message.DateUnix.Value,
            typeof(string),
            parameter: null!,
            culture);
    }
}

public sealed class ThreadCountConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        var id = value?.ToString();
        if (string.IsNullOrWhiteSpace(id))
            return string.Empty;

        var count = MailThreadCountRegistry.Get(id);
        return count > 1
            ? count.ToString(CultureInfo.InvariantCulture)
            : string.Empty;
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;
}

public sealed class ThreadCountVisibilityConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        var id = value?.ToString();
        return !string.IsNullOrWhiteSpace(id) && MailThreadCountRegistry.Get(id) > 1
            ? Visibility.Visible
            : Visibility.Collapsed;
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;
}

internal static class MailThreadCountRegistry
{
    private static readonly object Gate = new();
    private static Dictionary<string, int> _counts =
        new(StringComparer.Ordinal);

    public static int Get(string id)
    {
        lock (Gate)
            return _counts.TryGetValue(id, out var count) ? count : 1;
    }

    public static void Replace(Dictionary<string, int> counts)
    {
        lock (Gate)
            _counts = new Dictionary<string, int>(counts, StringComparer.Ordinal);
    }
}

internal sealed class MailDateThenPinnedComparer : IComparer
{
    public int Compare(object? x, object? y)
    {
        if (ReferenceEquals(x, y))
            return 0;
        if (x is not MailRuMessageSummary left)
            return 1;
        if (y is not MailRuMessageSummary right)
            return -1;

        var pinnedCompare = right.Pinned.CompareTo(left.Pinned);
        if (pinnedCompare != 0)
            return pinnedCompare;

        if (left.Pinned && right.Pinned)
            return Nullable.Compare(right.DateUnix, left.DateUnix);

        var leftDate = LocalDate(left.DateUnix);
        var rightDate = LocalDate(right.DateUnix);

        var dateGroupCompare = Nullable.Compare(rightDate, leftDate);
        if (dateGroupCompare != 0)
            return dateGroupCompare;

        return Nullable.Compare(right.DateUnix, left.DateUnix);
    }

    private static DateTime? LocalDate(long? unix)
    {
        if (unix is null)
            return null;

        try
        {
            return DateTimeOffset
                .FromUnixTimeSeconds(unix.Value)
                .ToLocalTime()
                .Date;
        }
        catch
        {
            return null;
        }
    }
}
