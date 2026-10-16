using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// Read-only selectable text with the same borderless visual appearance as a
/// TextBlock. Selected substrings can be copied with Ctrl+C or the standard
/// TextBox context menu; this control never edits mailbox or settings data.
/// </summary>
public sealed class SelectableText : TextBox
{
    public SelectableText()
    {
        IsReadOnly = true;
        IsReadOnlyCaretVisible = true;
        IsTabStop = false;
        BorderThickness = new Thickness(0);
        Padding = new Thickness(0);
        Background = Brushes.Transparent;
        HorizontalScrollBarVisibility = ScrollBarVisibility.Disabled;
        VerticalScrollBarVisibility = ScrollBarVisibility.Disabled;
        Cursor = Cursors.IBeam;
        SetResourceReference(ForegroundProperty, "AppTextBrush");
    }
}
