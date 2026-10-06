using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace MailRuDesktop.App;

internal sealed class AccountRailItem : INotifyPropertyChanged
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

    public string UnreadDisplay => Unread is > 99 ? "99+" : Unread?.ToString() ?? string.Empty;
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
