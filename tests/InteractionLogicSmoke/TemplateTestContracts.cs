namespace MailRuDesktop.App;

// Lightweight non-UI contract types for exercising the production file store
// in the headless regression test. The application owns the actual model.
internal static class DiagnosticLog
{
    public static void Write(string source, string message) =>
        Console.Error.WriteLine($"DIAGNOSTIC {source}: {message}");
}
