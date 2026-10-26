using System;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Collections.Generic;

namespace MailRuDesktop.App;

internal sealed class BackupPasswordWindow : Window
{
    private readonly PasswordBox _first = new() { MinWidth = 280, Margin = new Thickness(0, 4, 0, 12) };
    private readonly PasswordBox? _confirmation;
    internal string Password => _first.Password;

    internal BackupPasswordWindow(Window owner, bool export)
    {
        Owner = owner;
        Icon = owner.Icon;
        Title = export ? "Пароль резервной копии" : "Открыть резервную копию";
        Width = 455; SizeToContent = SizeToContent.Height;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        ResizeMode = ResizeMode.NoResize;
        var content = new StackPanel { Margin = new Thickness(20) };
        content.Children.Add(new TextBlock
        {
            Text = export
                ? "Создайте пароль для шифрования файла настроек (не менее 8 символов)."
                : "Введите пароль зашифрованного файла настроек.",
            TextWrapping = TextWrapping.Wrap, Margin = new Thickness(0, 0, 0, 14)
        });
        content.Children.Add(new TextBlock { Text = "Пароль" });
        content.Children.Add(_first);
        if (export)
        {
            _confirmation = new PasswordBox { MinWidth = 280, Margin = new Thickness(0, 4, 0, 12) };
            content.Children.Add(new TextBlock { Text = "Повторите пароль" });
            content.Children.Add(_confirmation);
        }
        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal, HorizontalAlignment = HorizontalAlignment.Right
        };
        var cancel = new Button { Content = "Отмена", Padding = new Thickness(18, 7, 18, 7),
            IsCancel = true, Margin = new Thickness(0, 0, 8, 0) };
        var ok = new Button { Content = "Продолжить", Padding = new Thickness(18, 7, 18, 7),
            IsDefault = true };
        ok.Click += (_, _) =>
        {
            if (_first.Password.Length < (export ? 8 : 1))
            {
                MessageBox.Show(this, export ? "Пароль должен содержать не менее 8 символов." :
                    "Введите пароль.", Title, MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }
            if (_confirmation is not null && _confirmation.Password != _first.Password)
            {
                MessageBox.Show(this, "Пароли не совпадают.", Title, MessageBoxButton.OK,
                    MessageBoxImage.Warning);
                return;
            }
            DialogResult = true;
        };
        buttons.Children.Add(cancel); buttons.Children.Add(ok);
        content.Children.Add(buttons);
        Content = content;
        ThemeManager.AttachWindowChrome(this);
        Loaded += (_, _) => _first.Focus();
    }
}

// The five sections are selected by default. Child checkboxes are individually
// selectable, while their parents show full / partial / empty selection.
internal sealed class BackupImportSelectionWindow : Window
{
    private readonly PortablePackage _package;
    private readonly Dictionary<string, CheckBox> _sections = new();
    private readonly Dictionary<string, List<(string Name, CheckBox Box)>> _children = new();
    private bool _updating;
    internal PortableSelection Selection { get; private set; } = new();

    internal BackupImportSelectionWindow(Window owner, PortablePackage package)
    {
        Owner = owner; Icon = owner.Icon; _package = package;
        Title = "Импорт настроек MailRu Desktop";
        Width = 670; Height = 670; MinWidth = 530; MinHeight = 470;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        var root = new DockPanel { Margin = new Thickness(18) };
        var title = new TextBlock
        {
            Text = "Выберите разделы и элементы для восстановления",
            FontSize = 17, FontWeight = FontWeights.SemiBold,
            Margin = new Thickness(0, 0, 0, 6)
        };
        DockPanel.SetDock(title, Dock.Top); root.Children.Add(title);
        var warning = new TextBlock
        {
            Text = "Перед импортом программа создаст резервную копию текущих настроек, " +
                   "сбросит прежнюю конфигурацию и сохранит действующие авторизации аккаунтов. " +
                   "Невыбранные разделы будут сброшены.",
            TextWrapping = TextWrapping.Wrap, Margin = new Thickness(0, 0, 0, 12)
        };
        DockPanel.SetDock(warning, Dock.Top); root.Children.Add(warning);
        var footer = new StackPanel
        {
            Orientation = Orientation.Horizontal, HorizontalAlignment = HorizontalAlignment.Right,
            Margin = new Thickness(0, 12, 0, 0)
        };
        DockPanel.SetDock(footer, Dock.Bottom); root.Children.Add(footer);
        var all = new Button { Content = "Выбрать всё", Margin = new Thickness(0, 0, 8, 0),
            Padding = new Thickness(8, 6, 8, 6) };
        all.Click += (_, _) => SetAll(true);
        var none = new Button { Content = "Снять выбор", Margin = new Thickness(0, 0, 18, 0),
            Padding = new Thickness(8, 6, 8, 6) };
        none.Click += (_, _) => SetAll(false);
        var cancel = new Button { Content = "Отмена", IsCancel = true,
            Margin = new Thickness(0, 0, 8, 0), Padding = new Thickness(10, 6, 10, 6) };
        var apply = new Button { Content = "Импортировать", IsDefault = true,
            Padding = new Thickness(10, 6, 10, 6) };
        apply.Click += (_, _) =>
        {
            Selection = CollectSelection();
            if (!Selection.Accounts && !Selection.Appearance && !Selection.Templates &&
                !Selection.Signatures && !Selection.Mailing)
            {
                MessageBox.Show(this, "Выберите хотя бы один раздел.", Title,
                    MessageBoxButton.OK, MessageBoxImage.Warning);
                return;
            }
            DialogResult = true;
        };
        foreach (var control in new Button[] { all, none, cancel, apply })
            footer.Children.Add(control);

        var scroll = new ScrollViewer { VerticalScrollBarVisibility = ScrollBarVisibility.Auto };
        var groups = new StackPanel();
        scroll.Content = groups; root.Children.Add(scroll);
        AddGroup(groups, "accounts", "Аккаунты", package.Accounts.Select(x => x.Login));
        AddGroup(groups, "appearance", "Оформление", null);
        AddGroup(groups, "templates", "Шаблоны писем", package.Templates.Select(x => x.Name));
        AddGroup(groups, "signatures", "Подписи", package.Signatures.Select(x => x.Name));
        AddGroup(groups, "mailing", "Профили рассылки", null);
        Content = root;
        ThemeManager.AttachWindowChrome(this);
    }

    private void AddGroup(StackPanel parent, string id, string name, IEnumerable<string>? entries)
    {
        var group = new StackPanel { Margin = new Thickness(4, 5, 4, 10) };
        var items = entries?.ToList();
        var header = new CheckBox
        {
            Content = items is null ? name : $"{name} ({items.Count})",
            IsChecked = true, IsThreeState = false,
            FontWeight = FontWeights.SemiBold, Margin = new Thickness(0, 3, 0, 4)
        };
        _sections[id] = header;
        if (items is null)
        {
            group.Children.Add(header);
        }
        else
        {
            var expander = new Expander { Header = header,
                IsExpanded = id == "accounts", Margin = new Thickness(0, 0, 0, 0) };
            var children = new StackPanel { Margin = new Thickness(23, 4, 0, 0) };
            var checks = new List<(string Name, CheckBox Box)>();
            foreach (var item in items)
            {
                var child = new CheckBox { Content = item, IsChecked = true,
                    Margin = new Thickness(0, 3, 0, 3) };
                checks.Add((item, child)); children.Children.Add(child);
                child.Checked += (_, _) => UpdateParent(id);
                child.Unchecked += (_, _) => UpdateParent(id);
            }
            _children[id] = checks;
            expander.Content = children; group.Children.Add(expander);
            header.Checked += (_, _) => SetGroup(id, true);
            header.Unchecked += (_, _) => SetGroup(id, false);
        }
        parent.Children.Add(group);
        parent.Children.Add(new Separator { Margin = new Thickness(0, 0, 0, 2) });
    }

    private void SetGroup(string id, bool value)
    {
        if (_updating) return;
        _updating = true;
        try
        {
            foreach (var child in _children.GetValueOrDefault(id) ?? [])
                child.Box.IsChecked = value;
        }
        finally { _updating = false; }
    }
    private void UpdateParent(string id)
    {
        if (_updating) return;
        _updating = true;
        try
        {
            var group = _children[id];
            var count = group.Count(x => x.Box.IsChecked == true);
            _sections[id].IsChecked = count == 0 ? false :
                count == group.Count ? true : null;
        }
        finally { _updating = false; }
    }
    private void SetAll(bool value)
    {
        _updating = true;
        try
        {
            foreach (var header in _sections.Values) header.IsChecked = value;
            foreach (var group in _children.Values)
                foreach (var child in group) child.Box.IsChecked = value;
        }
        finally { _updating = false; }
    }
    private PortableSelection CollectSelection()
    {
        var result = new PortableSelection
        {
            Accounts = _sections["accounts"].IsChecked != false,
            Appearance = _sections["appearance"].IsChecked == true,
            Templates = _sections["templates"].IsChecked != false,
            Signatures = _sections["signatures"].IsChecked != false,
            Mailing = _sections["mailing"].IsChecked == true
        };
        foreach (var (name, check) in _children["accounts"])
            if (check.IsChecked == true) result.AccountNames.Add(name);
        foreach (var (name, check) in _children["templates"])
            if (check.IsChecked == true) result.TemplateNames.Add(name);
        foreach (var (name, check) in _children["signatures"])
            if (check.IsChecked == true) result.SignatureNames.Add(name);
        result.Accounts &= result.AccountNames.Count > 0;
        result.Templates &= result.TemplateNames.Count > 0;
        result.Signatures &= result.SignatureNames.Count > 0;
        return result;
    }
}
