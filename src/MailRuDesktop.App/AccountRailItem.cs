using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace MailRuDesktop.App;

public sealed class AccountRailItem : INotifyPropertyChanged
{
    private long? _unread;
    private string _status = "Ожидание";
    private bool _isActive;

    public AccountRailItem(string login)
    {
        Login = login;
        Initials = BuildInitials(login);
    }

    public string Login { get; }
    public string Initials { get; }
    public string IconText => Initials;
    public string? AvatarUrl => MailRuDesktop.Protocol.MailRuAvatarUrls.ForEmail(Login);

    public long? Unread
    {
        get => _unread;
        set
        {
            if (_unread == value) return;
            _unread = value;
            OnPropertyChanged();
            OnPropertyChanged(nameof(UnreadDisplay));
            OnPropertyChanged(nameof(HasUnread));
        }
    }

    public string Status
    {
        get => _status;
        set
        {
            if (string.Equals(_status, value, StringComparison.Ordinal)) return;
            _status = value;
            OnPropertyChanged();
            OnPropertyChanged(nameof(ToolTipText));
        }
    }

    public bool IsActive
    {
        get => _isActive;
        set
        {
            if (_isActive == value) return;
            _isActive = value;
            OnPropertyChanged();
        }
    }

    public string UnreadDisplay => Unread is > 99 ? "99+" : Unread is > 0 ? Unread.Value.ToString() : string.Empty;
    public bool HasUnread => Unread is > 0;
    public string ToolTipText => string.IsNullOrWhiteSpace(Status) ? Login : $"{Login}\n{Status}";

    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged([CallerMemberName] string? name = null) =>
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));

    private static string BuildInitials(string login)
    {
        var local = login.Split('@', 2)[0].Trim();
        if (local.Length == 0) return "?";

        var parts = local.Split(new[] { '.', '_', '-', ' ' }, StringSplitOptions.RemoveEmptyEntries);
        if (parts.Length >= 2)
            return (char.ToUpperInvariant(parts[0][0]).ToString() +
                    char.ToUpperInvariant(parts[1][0])).Trim();

        return char.ToUpperInvariant(local[0]).ToString();
    }
}


public sealed class AccountSectionItem : INotifyPropertyChanged
{
    private bool _isCollapsed;
    private string _name;

    public AccountSectionItem(string id, string name, bool isCollapsed)
    {
        Id = id;
        _name = name;
        _isCollapsed = isCollapsed;
    }

    public string Id { get; }

    public string Name
    {
        get => _name;
        set
        {
            if (string.Equals(_name, value, StringComparison.Ordinal))
                return;

            _name = value;
            OnPropertyChanged();
        }
    }

    public bool IsCollapsed
    {
        get => _isCollapsed;
        set
        {
            if (_isCollapsed == value)
                return;

            _isCollapsed = value;
            OnPropertyChanged();
            OnPropertyChanged(nameof(Arrow));
        }
    }

    public string Arrow => IsCollapsed ? "▸" : "▾";

    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged([CallerMemberName] string? name = null) =>
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
}
