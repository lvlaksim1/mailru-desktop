using System.Collections.ObjectModel;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Threading;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly ObservableCollection<object> _accountRailDisplayItems = [];
    private List<AccountRailLayoutEntryState> _accountRailLayout = [];
    private bool _accountRailLayoutInitialized;

    private readonly DispatcherTimer _accountDragHoldTimer = new()
    {
        Interval = TimeSpan.FromMilliseconds(275)
    };

    private AccountRailItem? _accountDragCandidate;
    private AccountRailItem? _draggedAccount;
    private ListBoxItem? _draggedAccountContainer;
    private Point _accountDragPressPoint;
    private Point _accountDragStartPoint;
    private int _accountDragSourceDisplayIndex = -1;
    private int _accountDragTargetDisplayIndex = -1;
    private bool _accountDragActive;
    private bool _accountPressHandled;

    private void InitializeAccountRailLayout()
    {
        if (_accountRailLayoutInitialized)
            return;

        _accountRailLayoutInitialized = true;
        _accountRailLayout = _settingsStore.LoadAccountRailLayout();

        AccountRailListBox.ItemsSource = _accountRailDisplayItems;
        AccountRailListBox.PreviewMouseLeftButtonDown += AccountRail_PreviewMouseLeftButtonDown;
        AccountRailListBox.PreviewMouseMove += AccountRail_PreviewMouseMove;
        AccountRailListBox.PreviewMouseLeftButtonUp += AccountRail_PreviewMouseLeftButtonUp;
        AccountRailListBox.LostMouseCapture += AccountRail_LostMouseCapture;

        _accountDragHoldTimer.Tick += AccountDragHoldTimer_Tick;
    }

    private void RefreshAccountRailLayout(string? preferredLogin)
    {
        InitializeAccountRailLayout();

        var selectedLogin =
            preferredLogin ??
            _activeLogin ??
            _authStore.LastLogin ??
            _authStore.Logins.FirstOrDefault();

        var existingByLogin = _accountRailItems.ToDictionary(
            item => item.Login,
            StringComparer.OrdinalIgnoreCase);

        var reconciled = new List<AccountRailItem>();
        foreach (var login in _authStore.Logins)
        {
            reconciled.Add(existingByLogin.TryGetValue(login, out var existing)
                ? existing
                : new AccountRailItem(login));
        }

        _accountRailItems.Clear();
        _accountRailItems.AddRange(reconciled);

        var changed = ReconcileStoredAccountLayout(_authStore.Logins);
        RebuildAccountRailDisplay();

        _updatingAccountRail = true;
        try
        {
            AccountRailListBox.SelectedItem =
                _accountRailDisplayItems
                    .OfType<AccountRailItem>()
                    .FirstOrDefault(item =>
                        string.Equals(
                            item.Login,
                            selectedLogin,
                            StringComparison.OrdinalIgnoreCase));

            foreach (var item in _accountRailItems)
            {
                item.IsActive = string.Equals(
                    item.Login,
                    _activeLogin,
                    StringComparison.OrdinalIgnoreCase);
            }
        }
        finally
        {
            _updatingAccountRail = false;
        }

        if (changed)
            SaveAccountRailLayout();
    }

    private bool ReconcileStoredAccountLayout(IReadOnlyList<string> logins)
    {
        var changed = false;
        var known = new HashSet<string>(logins, StringComparer.OrdinalIgnoreCase);

        for (var i = _accountRailLayout.Count - 1; i >= 0; i--)
        {
            var entry = _accountRailLayout[i];

            if (entry.Kind.Equals("account", StringComparison.OrdinalIgnoreCase))
            {
                if (string.IsNullOrWhiteSpace(entry.Login) ||
                    !known.Contains(entry.Login))
                {
                    _accountRailLayout.RemoveAt(i);
                    changed = true;
                }

                continue;
            }

            if (!entry.Kind.Equals("section", StringComparison.OrdinalIgnoreCase))
            {
                _accountRailLayout.RemoveAt(i);
                changed = true;
            }
        }

        var already = _accountRailLayout
            .Where(entry =>
                entry.Kind.Equals("account", StringComparison.OrdinalIgnoreCase) &&
                !string.IsNullOrWhiteSpace(entry.Login))
            .Select(entry => entry.Login!)
            .ToHashSet(StringComparer.OrdinalIgnoreCase);

        foreach (var login in logins)
        {
            if (already.Contains(login))
                continue;

            _accountRailLayout.Add(new AccountRailLayoutEntryState
            {
                Kind = "account",
                Login = login,
                Id = Guid.NewGuid().ToString("N")
            });
            changed = true;
        }

        return changed;
    }

    private void RebuildAccountRailDisplay()
    {
        var accountByLogin = _accountRailItems.ToDictionary(
            item => item.Login,
            StringComparer.OrdinalIgnoreCase);

        _accountRailDisplayItems.Clear();

        var sectionCollapsed = false;
        foreach (var entry in _accountRailLayout)
        {
            if (entry.Kind.Equals("section", StringComparison.OrdinalIgnoreCase))
            {
                var section = new AccountSectionItem(
                    entry.Id,
                    string.IsNullOrWhiteSpace(entry.Title) ? "Раздел" : entry.Title!,
                    entry.IsCollapsed);

                _accountRailDisplayItems.Add(section);
                sectionCollapsed = entry.IsCollapsed;
                continue;
            }

            if (sectionCollapsed ||
                string.IsNullOrWhiteSpace(entry.Login) ||
                !accountByLogin.TryGetValue(entry.Login, out var account))
            {
                continue;
            }

            _accountRailDisplayItems.Add(account);
        }
    }

    private void SaveAccountRailLayout() =>
        _settingsStore.SaveAccountRailLayout(_accountRailLayout);

    private void AddAccountSectionButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new TextPromptWindow(
            this,
            "Новый раздел аккаунтов",
            "Название раздела:");

        if (dialog.ShowDialog() != true)
            return;

        _accountRailLayout.Add(new AccountRailLayoutEntryState
        {
            Kind = "section",
            Id = Guid.NewGuid().ToString("N"),
            Title = dialog.Value,
            IsCollapsed = false
        });

        SaveAccountRailLayout();
        RefreshAccountRailLayout(_activeLogin);
    }

    private void AccountSectionButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as FrameworkElement)?.DataContext is not AccountSectionItem section)
            return;

        var entry = _accountRailLayout.FirstOrDefault(item =>
            item.Kind.Equals("section", StringComparison.OrdinalIgnoreCase) &&
            string.Equals(item.Id, section.Id, StringComparison.Ordinal));

        if (entry is null)
            return;

        entry.IsCollapsed = !entry.IsCollapsed;
        SaveAccountRailLayout();
        RefreshAccountRailLayout(_activeLogin);
        e.Handled = true;
    }

    private bool RenameAccountSection(AccountSectionItem section)
    {
        var entry = _accountRailLayout.FirstOrDefault(item =>
            item.Kind.Equals("section", StringComparison.OrdinalIgnoreCase) &&
            string.Equals(item.Id, section.Id, StringComparison.Ordinal));

        if (entry is null)
            return false;

        var dialog = new TextPromptWindow(
            this,
            "Переименовать раздел",
            "Название раздела:",
            entry.Title ?? section.Name);

        if (dialog.ShowDialog() != true)
            return false;

        entry.Title = dialog.Value;
        SaveAccountRailLayout();
        RefreshAccountRailLayout(_activeLogin);
        return true;
    }

    private void DeleteAccountSection(AccountSectionItem section)
    {
        var sectionIndex = _accountRailLayout.FindIndex(item =>
            item.Kind.Equals("section", StringComparison.OrdinalIgnoreCase) &&
            string.Equals(item.Id, section.Id, StringComparison.Ordinal));

        if (sectionIndex < 0)
            return;

        var children = new List<AccountRailLayoutEntryState>();
        for (var i = sectionIndex + 1; i < _accountRailLayout.Count; i++)
        {
            if (_accountRailLayout[i].Kind.Equals(
                    "section",
                    StringComparison.OrdinalIgnoreCase))
            {
                break;
            }

            children.Add(_accountRailLayout[i]);
        }

        foreach (var child in children)
            _accountRailLayout.Remove(child);

        _accountRailLayout.RemoveAt(sectionIndex);

        var firstSection = _accountRailLayout.FindIndex(item =>
            item.Kind.Equals("section", StringComparison.OrdinalIgnoreCase));
        if (firstSection < 0)
            firstSection = _accountRailLayout.Count;

        _accountRailLayout.InsertRange(firstSection, children);

        SaveAccountRailLayout();
        RefreshAccountRailLayout(_activeLogin);
    }

    private void RemoveAccountFromRailLayout(string login)
    {
        _accountRailLayout.RemoveAll(entry =>
            entry.Kind.Equals("account", StringComparison.OrdinalIgnoreCase) &&
            string.Equals(entry.Login, login, StringComparison.OrdinalIgnoreCase));

        SaveAccountRailLayout();
    }

    private void AccountRail_PreviewMouseLeftButtonDown(
        object sender,
        MouseButtonEventArgs e)
    {
        if (e.ChangedButton != MouseButton.Left ||
            e.OriginalSource is not DependencyObject source ||
            ItemsControl.ContainerFromElement(
                AccountRailListBox,
                source) is not ListBoxItem container ||
            container.DataContext is not AccountRailItem account)
        {
            StopAccountDragHold();
            _accountPressHandled = false;
            return;
        }

        _accountPressHandled = true;
        _accountDragCandidate = account;
        _accountDragPressPoint = e.GetPosition(AccountRailListBox);
        _accountDragStartPoint = _accountDragPressPoint;

        _accountDragHoldTimer.Stop();
        _accountDragHoldTimer.Start();

        // Delay selection until mouse-up. Otherwise a long press used only for
        // reordering would start an expensive account switch before the drag.
        e.Handled = true;
    }

    private void AccountDragHoldTimer_Tick(object? sender, EventArgs e)
    {
        _accountDragHoldTimer.Stop();

        if (_accountDragCandidate is null ||
            Mouse.LeftButton != MouseButtonState.Pressed)
        {
            _accountDragCandidate = null;
            return;
        }

        var now = Mouse.GetPosition(AccountRailListBox);
        if (Math.Abs(now.X - _accountDragPressPoint.X) > 7 ||
            Math.Abs(now.Y - _accountDragPressPoint.Y) > 7)
        {
            _accountDragCandidate = null;
            return;
        }

        if (AccountRailListBox.ItemContainerGenerator.ContainerFromItem(
                _accountDragCandidate) is not ListBoxItem container)
        {
            _accountDragCandidate = null;
            return;
        }

        _draggedAccount = _accountDragCandidate;
        _draggedAccountContainer = container;
        _accountDragSourceDisplayIndex =
            _accountRailDisplayItems.IndexOf(_draggedAccount);
        _accountDragTargetDisplayIndex = _accountDragSourceDisplayIndex;
        _accountDragStartPoint = now;
        _accountDragActive = true;

        container.RenderTransform = new TranslateTransform();
        container.Opacity = 0.96;
        container.Effect = new DropShadowEffect
        {
            BlurRadius = 16,
            ShadowDepth = 3,
            Opacity = 0.30
        };
        Panel.SetZIndex(container, 100);

        AccountRailListBox.CaptureMouse();
    }

    private void AccountRail_PreviewMouseMove(
        object sender,
        MouseEventArgs e)
    {
        if (!_accountDragActive ||
            _draggedAccount is null ||
            _draggedAccountContainer is null ||
            Mouse.LeftButton != MouseButtonState.Pressed)
        {
            return;
        }

        var position = e.GetPosition(AccountRailListBox);
        var deltaY = position.Y - _accountDragStartPoint.Y;

        if (_draggedAccountContainer.RenderTransform is TranslateTransform dragTransform)
            dragTransform.Y = deltaY;

        var target = FindAccountDragTargetDisplayIndex(position.Y);
        if (target == _accountDragTargetDisplayIndex)
            return;

        _accountDragTargetDisplayIndex = target;
        AnimateAccountDragPreview(
            _accountDragSourceDisplayIndex,
            _accountDragTargetDisplayIndex,
            Math.Max(1, _draggedAccountContainer.ActualHeight));

        e.Handled = true;
    }

    private int FindAccountDragTargetDisplayIndex(double y)
    {
        // Compare against the ORIGINAL row centers. TranslatePoint includes the
        // running neighbor animations, so measuring the transformed row positions
        // creates a feedback loop: downward targets disappear or oscillate.
        // Thresholds are the centers of the OTHER rows, never the dragged row.
        var from = _accountDragSourceDisplayIndex;
        if (from < 0)
            return -1;

        if (TryGetUnanimatedRowMidpoint(from, out var origin) && y < origin)
        {
            for (var i = 0; i < from; i++)
            {
                if (TryGetUnanimatedRowMidpoint(i, out var center) && y < center)
                    return i;
            }
            return from;
        }

        var target = from;
        for (var i = from + 1; i < _accountRailDisplayItems.Count; i++)
        {
            if (TryGetUnanimatedRowMidpoint(i, out var center) && y >= center)
                target = i;
        }

        return target;
    }

    private bool TryGetUnanimatedRowMidpoint(int index, out double midpoint)
    {
        midpoint = 0;
        if (AccountRailListBox.ItemContainerGenerator.ContainerFromIndex(index)
            is not ListBoxItem container)
            return false;

        var shiftedTop = container.TranslatePoint(new Point(0, 0), AccountRailListBox).Y;
        var displacement = container.RenderTransform is TranslateTransform transform
            ? transform.Y
            : 0;

        midpoint = shiftedTop - displacement + container.ActualHeight / 2;
        return true;
    }

    private void AnimateAccountDragPreview(
        int from,
        int to,
        double draggedHeight)
    {
        for (var i = 0; i < _accountRailDisplayItems.Count; i++)
        {
            if (i == from ||
                AccountRailListBox.ItemContainerGenerator.ContainerFromIndex(i)
                    is not ListBoxItem container)
            {
                continue;
            }

            var targetOffset = 0d;

            if (to > from && i > from && i <= to)
                targetOffset = -draggedHeight;
            else if (to < from && i >= to && i < from)
                targetOffset = draggedHeight;

            var transform = container.RenderTransform as TranslateTransform;
            if (transform is null)
            {
                transform = new TranslateTransform();
                container.RenderTransform = transform;
            }

            transform.BeginAnimation(
                TranslateTransform.YProperty,
                new DoubleAnimation(
                    targetOffset,
                    TimeSpan.FromMilliseconds(135))
                {
                    EasingFunction = new CubicEase
                    {
                        EasingMode = EasingMode.EaseOut
                    },
                    FillBehavior = FillBehavior.HoldEnd
                });
        }
    }

    private void AccountRail_PreviewMouseLeftButtonUp(
        object sender,
        MouseButtonEventArgs e)
    {
        _accountDragHoldTimer.Stop();

        if (_accountDragActive)
        {
            CommitAccountDrag();
            e.Handled = true;
            return;
        }

        if (!_accountPressHandled)
            return;

        // A short ordinary click selects only the exact account pressed.
        // A pointer movement that cancelled the long press must NOT select
        // the neighboring row below the pointer on mouse-up.
        _accountPressHandled = false;
        var clickedAccount = _accountDragCandidate;
        _accountDragCandidate = null;
        e.Handled = true;

        if (clickedAccount is null)
            return;

        var released = e.GetPosition(AccountRailListBox);
        if (Math.Abs(released.X - _accountDragPressPoint.X) > 7 ||
            Math.Abs(released.Y - _accountDragPressPoint.Y) > 7)
            return;

        if (!ReferenceEquals(AccountRailListBox.SelectedItem, clickedAccount))
            AccountRailListBox.SelectedItem = clickedAccount;
        else if (string.Equals(clickedAccount.Login, _activeLogin,
                     StringComparison.OrdinalIgnoreCase))
            ShowWorkspace(MailWorkspace);
    }

    private void AccountRail_LostMouseCapture(
        object sender,
        MouseEventArgs e)
    {
        if (_accountDragActive && Mouse.LeftButton != MouseButtonState.Pressed)
            CommitAccountDrag();
    }

    private void CommitAccountDrag()
    {
        if (!_accountDragActive || _draggedAccount is null)
        {
            StopAccountDragHold();
            return;
        }

        var accountEntryIndex = _accountRailLayout.FindIndex(entry =>
            entry.Kind.Equals("account", StringComparison.OrdinalIgnoreCase) &&
            string.Equals(
                entry.Login,
                _draggedAccount.Login,
                StringComparison.OrdinalIgnoreCase));

        if (accountEntryIndex >= 0 &&
            _accountDragTargetDisplayIndex != _accountDragSourceDisplayIndex &&
            _accountDragTargetDisplayIndex >= 0 &&
            _accountDragTargetDisplayIndex < _accountRailDisplayItems.Count)
        {
            var movingEntry = _accountRailLayout[accountEntryIndex];
            _accountRailLayout.RemoveAt(accountEntryIndex);

            var targetObject =
                _accountRailDisplayItems[_accountDragTargetDisplayIndex];

            var targetLayoutIndex = ResolveLayoutIndex(targetObject);
            if (targetLayoutIndex < 0)
                targetLayoutIndex = _accountRailLayout.Count;

            if (_accountDragTargetDisplayIndex > _accountDragSourceDisplayIndex)
                targetLayoutIndex++;

            targetLayoutIndex = Math.Clamp(
                targetLayoutIndex,
                0,
                _accountRailLayout.Count);

            _accountRailLayout.Insert(targetLayoutIndex, movingEntry);
            SaveAccountRailLayout();
        }

        ResetAccountDragVisuals();
        _accountDragActive = false;
        _accountPressHandled = false;
        _accountDragCandidate = null;
        _draggedAccount = null;
        _draggedAccountContainer = null;
        _accountDragSourceDisplayIndex = -1;
        _accountDragTargetDisplayIndex = -1;

        if (Mouse.Captured == AccountRailListBox)
            AccountRailListBox.ReleaseMouseCapture();

        RefreshAccountRailLayout(_activeLogin);
    }

    private int ResolveLayoutIndex(object item)
    {
        if (item is AccountRailItem account)
        {
            return _accountRailLayout.FindIndex(entry =>
                entry.Kind.Equals("account", StringComparison.OrdinalIgnoreCase) &&
                string.Equals(
                    entry.Login,
                    account.Login,
                    StringComparison.OrdinalIgnoreCase));
        }

        if (item is AccountSectionItem section)
        {
            return _accountRailLayout.FindIndex(entry =>
                entry.Kind.Equals("section", StringComparison.OrdinalIgnoreCase) &&
                string.Equals(
                    entry.Id,
                    section.Id,
                    StringComparison.Ordinal));
        }

        return -1;
    }

    private void ResetAccountDragVisuals()
    {
        // Do not leave animated transforms on recycled ListBoxItem containers.
        // Stale offsets otherwise cause the next click to hit the row underneath.
        for (var i = 0; i < _accountRailDisplayItems.Count; i++)
        {
            if (AccountRailListBox.ItemContainerGenerator.ContainerFromIndex(i)
                is not ListBoxItem container)
                continue;

            if (container.RenderTransform is TranslateTransform transform)
            {
                transform.BeginAnimation(TranslateTransform.YProperty, null);
                transform.Y = 0;
            }

            container.Opacity = 1;
            container.Effect = null;
            Panel.SetZIndex(container, 0);
        }
    }

    private void StopAccountDragHold()
    {
        _accountDragHoldTimer.Stop();
        _accountDragCandidate = null;
    }
}
