namespace MailRuDesktop.App;

// Lightweight non-UI contract types for exercising the production file store
// in the headless regression test. The application owns the actual model.
internal sealed class SavedMailTemplate
{
    public string Id { get; set; } = "";
    public string Name { get; set; } = "";
    public string Subject { get; set; } = "";
    public string Body { get; set; } = "";
    public List<string> Attachments { get; set; } = [];
}

internal static class DiagnosticLog
{
    public static void Write(string source, string message) =>
        Console.Error.WriteLine($"DIAGNOSTIC {source}: {message}");
}
