using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Media;
using MailRuDesktop.App;

internal static class Program
{
    [STAThread]
    private static int Main()
    {
        Window? mail = null;
        Window? editor = null;
        try
        {
            var app = new App();
            app.InitializeComponent();
            mail = new MainWindow();
            mail.Show();
            mail.UpdateLayout();

            var action = Require<Button>(mail, "MainComposeButton");
            Check(action.IsVisible, "New Mail action is visible above the message list");
            Check(Require<Grid>(mail, "SettingsWorkspace") is { }, "settings workspace loads");
            Check(Require<Border>(mail, "ReaderLoadingOverlay") is { },
                "reader contains stable loading overlay");

            var palette = FindDescendant<Expander>(mail,
                e => string.Equals(e.Header?.ToString(),
                    "Цвета элементов интерфейса", StringComparison.Ordinal));
            Check(palette is not null, "palette editor is present");
            Check(FindParent<GroupBox>(palette!)?.Header?.ToString() == "Внешний вид",
                "palette section is inside Appearance rather than beside it");

            var signature = FindDescendant<Expander>(mail,
                e => e.Header?.ToString() == "Подписи");
            var template = FindDescendant<Expander>(mail,
                e => e.Header?.ToString() == "Шаблоны");
            Check(signature is not null && template is not null,
                "signature/template expanders are present");
            Check(ReferenceEquals(VisualTreeHelper.GetParent(signature!),
                     VisualTreeHelper.GetParent(template!)),
                "signature and template are siblings");
            var settingsStack = (Panel)VisualTreeHelper.GetParent(signature!);
            Check(settingsStack.Children.IndexOf(template!) ==
                  settingsStack.Children.IndexOf(signature!) + 1,
                "Templates appears immediately after Signatures");

            action.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, action));
            editor = Application.Current.Windows
                .OfType<Window>()
                .FirstOrDefault(w => w.Title.StartsWith("Новое письмо", StringComparison.Ordinal));
            Check(editor is not null && editor.IsVisible, "New Mail opens its own window");
            var from = Require<TextBox>(mail, "ComposeFromTextBox");
            Check(from.IsReadOnly && from.Text.Length > 0,
                "From address is always visible and read-only");
            Check(ReferenceEquals(Window.GetWindow(from), editor),
                "From field belongs to the independent New Mail window");
            Check(Require<ComboBox>(mail, "ComposeSignatureComboBox").IsVisible,
                "signature chooser is visible in New Mail");
            Check(Require<ComboBox>(mail, "ComposeTemplateComboBox").IsVisible,
                "template chooser is visible in New Mail");
            var send = Require<Button>(mail, "SendButton");
            Check(ReferenceEquals(
                    send.ReadLocalValue(Control.BackgroundProperty),
                    DependencyProperty.UnsetValue),
                "send button uses the shared theme without a forced blue fill");

            Console.WriteLine("Windows WPF UI interaction smoke: PASS");
            return 0;
        }
        catch (Exception ex)
        {
            Console.Error.WriteLine("Windows WPF UI interaction smoke: FAIL");
            Console.Error.WriteLine(ex);
            return 1;
        }
        finally
        {
            editor?.Close();
            mail?.Close();
        }
    }

    private static T Require<T>(FrameworkElement root, string name)
        where T : FrameworkElement
    {
        var found = root.FindName(name) as T;
        Check(found is not null, $"XAML named control {name}");
        return found!;
    }

    private static T? FindDescendant<T>(DependencyObject source, Func<T,bool> match)
        where T : DependencyObject
    {
        if (source is T item && match(item))
            return item;
        for (var i = 0; i < VisualTreeHelper.GetChildrenCount(source); i++)
        {
            var found = FindDescendant(VisualTreeHelper.GetChild(source, i), match);
            if (found is not null)
                return found;
        }
        return null;
    }

    private static T? FindParent<T>(DependencyObject source) where T : DependencyObject
    {
        var parent = VisualTreeHelper.GetParent(source);
        while (parent is not null)
        {
            if (parent is T typed)
                return typed;
            parent = VisualTreeHelper.GetParent(parent);
        }
        return null;
    }

    private static void Check(bool condition, string description)
    {
        if (!condition)
            throw new InvalidOperationException(description);
        Console.WriteLine("PASS " + description);
    }
}
