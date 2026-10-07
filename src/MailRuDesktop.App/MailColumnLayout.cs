using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows;

namespace MailRuDesktop.App;

public sealed class MailColumnLayout : INotifyPropertyChanged
{
    public static MailColumnLayout Instance { get; } = new();

    private GridLength _time = new(58);
    private GridLength _flag = new(28);
    private GridLength _unread = new(26);
    private GridLength _threadCount = new(32);
    private GridLength _attachment = new(28);
    private GridLength _sender = new(1.15, GridUnitType.Star);
    private GridLength _subject = new(2.15, GridUnitType.Star);

    private MailColumnLayout()
    {
    }

    public GridLength Time
    {
        get => _time;
        set => Set(ref _time, value);
    }

    public GridLength Flag
    {
        get => _flag;
        set => Set(ref _flag, value);
    }

    public GridLength Unread
    {
        get => _unread;
        set => Set(ref _unread, value);
    }

    public GridLength ThreadCount
    {
        get => _threadCount;
        set => Set(ref _threadCount, value);
    }

    public GridLength Attachment
    {
        get => _attachment;
        set => Set(ref _attachment, value);
    }

    public GridLength Sender
    {
        get => _sender;
        set => Set(ref _sender, value);
    }

    public GridLength Subject
    {
        get => _subject;
        set => Set(ref _subject, value);
    }

    public event PropertyChangedEventHandler? PropertyChanged;

    private void Set(
        ref GridLength field,
        GridLength value,
        [CallerMemberName] string? propertyName = null)
    {
        if (field == value)
            return;

        field = value;
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }
}
