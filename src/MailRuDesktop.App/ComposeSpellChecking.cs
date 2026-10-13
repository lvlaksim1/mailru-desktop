using System.Windows.Controls;
using System.Windows.Markup;

namespace MailRuDesktop.App;

/// <summary>
/// Shared Windows/WPF spell-check switch for all outgoing text editors.
/// Spelling is performed locally by dictionaries installed in Windows.
/// Only weak references are kept: closing a compose window cannot retain it.
/// </summary>
internal static class ComposeSpellChecking
{
    private static readonly List<WeakReference<RichTextBox>> Editors = [];

    public static bool Enabled { get; private set; } = true;

    public static void Register(RichTextBox editor)
    {
        ArgumentNullException.ThrowIfNull(editor);
        RemoveCollectedEditors();
        // WPF defaults the language to en-US even when the letter is Russian.
        // That caused almost every Cyrillic word to be marked as misspelled.
        // Keep the language explicit; installed Windows dictionaries determine
        // whether Russian spellchecking is available on this machine.
        editor.Language = XmlLanguage.GetLanguage("ru-RU");
        editor.SpellCheck.IsEnabled = Enabled;
        Editors.Add(new WeakReference<RichTextBox>(editor));
    }

    public static void SetEnabled(bool enabled)
    {
        Enabled = enabled;
        foreach (var reference in Editors)
            if (reference.TryGetTarget(out var editor))
                editor.SpellCheck.IsEnabled = enabled;
        RemoveCollectedEditors();
    }

    private static void RemoveCollectedEditors() =>
        Editors.RemoveAll(reference => !reference.TryGetTarget(out _));
}
