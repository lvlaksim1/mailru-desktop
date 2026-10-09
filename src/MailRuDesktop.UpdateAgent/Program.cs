using System.Diagnostics;
using System.IO.Pipes;
using System.Net.Http;
using System.Windows.Forms;

namespace MailRuDesktop.UpdateAgent;

internal static class Program
{
    [STAThread]
    private static void Main(string[] args)
    {
        ApplicationConfiguration.Initialize();
        if (args.Length != 4 || !int.TryParse(args[0], out var parentId) ||
            !Uri.TryCreate(args[1], UriKind.Absolute, out var source) ||
            !string.Equals(source.Scheme, "https", StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(source.Host, "github.com", StringComparison.OrdinalIgnoreCase) ||
            !source.AbsolutePath.StartsWith("/lvlaksim1/mailru-desktop/releases/download/v",
                StringComparison.OrdinalIgnoreCase) ||
            !Path.IsPathFullyQualified(args[2]) ||
            args[3].Length is < 8 or > 100)
            return;

        Application.Run(new UpdateWindow(parentId, source, args[2], args[3]));
    }
}

internal sealed class UpdateWindow : Form
{
    private readonly int _parentId;
    private readonly Uri _source;
    private readonly string _installedExe;
    private readonly string _pipeName;
    private readonly Label _phase = new()
    {
        AutoSize = false, Left = 22, Top = 20, Width = 440, Height = 42,
        Text = "Подготовка обновления…"
    };
    private readonly ProgressBar _progress = new()
    {
        Left = 22, Top = 72, Width = 440, Height = 23,
        Minimum = 0, Maximum = 100
    };
    private readonly Button _close = new()
    {
        Text = "Закрыть", Width = 100, Left = 362, Top = 110, Visible = false
    };

    internal UpdateWindow(int parentId, Uri source, string installedExe, string pipeName)
    {
        _parentId = parentId;
        _source = source;
        _installedExe = installedExe;
        _pipeName = pipeName;
        Text = "Обновление MailRu Desktop";
        Width = 500;
        Height = 196;
        MaximizeBox = false;
        MinimizeBox = false;
        FormBorderStyle = FormBorderStyle.FixedDialog;
        StartPosition = FormStartPosition.CenterScreen;
        Controls.AddRange([_phase, _progress, _close]);
        _close.Click += (_, _) => Close();
        Shown += async (_, _) => await PerformUpdateAsync();
    }

    private void Display(string message, int? percent = null)
    {
        _phase.Text = message;
        if (percent.HasValue)
        {
            _progress.Style = ProgressBarStyle.Continuous;
            _progress.Value = Math.Clamp(percent.Value, 0, 100);
        }
    }

    private async Task PerformUpdateAsync()
    {
        try
        {
            var folder = Path.Combine(
                Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
                "MailRuDesktop", "Updates");
            Directory.CreateDirectory(folder);
            var installer = Path.Combine(folder, "MailRuDesktop_Update_Staged.exe");
            var temporary = installer + ".part";
            using (var client = new HttpClient { Timeout = TimeSpan.FromMinutes(10) })
            using (var response = await client.GetAsync(
                       _source, HttpCompletionOption.ResponseHeadersRead))
            {
                response.EnsureSuccessStatusCode();
                var length = response.Content.Headers.ContentLength;
                await using var input = await response.Content.ReadAsStreamAsync();
                await using var output = File.Create(temporary);
                var buffer = new byte[65536];
                long received = 0;
                int size;
                Display("Скачивание обновления…", 0);
                while ((size = await input.ReadAsync(buffer)) > 0)
                {
                    await output.WriteAsync(buffer.AsMemory(0, size));
                    received += size;
                    if (length is > 0)
                        Display("Скачивание обновления… " +
                            (received * 100 / length.Value) + "%",
                            (int)Math.Min(65, received * 65 / length.Value));
                }
                await output.FlushAsync();
            }
            File.Move(temporary, installer, true);
            Display("Загрузка завершена. Закрытие программы…", 70);

            using (var pipe = new NamedPipeClientStream(".", _pipeName,
                       PipeDirection.Out, PipeOptions.Asynchronous))
            {
                await pipe.ConnectAsync(10000);
                using var writer = new StreamWriter(pipe) { AutoFlush = true };
                await writer.WriteLineAsync("UPDATE_READY");
            }

            try
            {
                using var parent = Process.GetProcessById(_parentId);
                using var timeout = new CancellationTokenSource(TimeSpan.FromMinutes(2));
                await parent.WaitForExitAsync(timeout.Token);
            }
            catch (ArgumentException) { /* Main app exited before the lookup. */ }

            Display("Установка обновления…");
            _progress.Style = ProgressBarStyle.Marquee;
            using var setup = Process.Start(new ProcessStartInfo(installer)
            {
                UseShellExecute = true,
                Arguments = "/VERYSILENT /SUPPRESSMSGBOXES /NORESTART /CLOSEAPPLICATIONS"
            }) ?? throw new InvalidOperationException("Не удалось запустить установщик.");
            await setup.WaitForExitAsync();
            if (setup.ExitCode != 0)
                throw new InvalidOperationException(
                    "Установка завершилась с кодом " + setup.ExitCode + ".");

            Display("Обновлённая программа запускается…", 100);
            // Inno [Run] starts the updated application only after copying
            // has finished. There is no second launch from this helper.
            await Task.Delay(1200);
            Close();
        }
        catch (Exception failure)
        {
            Display("Не удалось завершить обновление: " +
                    failure.GetType().Name + ". Программа и настройки сохранены.");
            _progress.Style = ProgressBarStyle.Continuous;
            _close.Visible = true;
        }
    }
}
