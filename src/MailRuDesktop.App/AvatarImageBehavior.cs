using System.Collections.Concurrent;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// WPF's default network BitmapImage requests do not share Mail.ru's browser
/// profile. Use the known PUBLIC Filin avatar endpoint with the official
/// client user-agent, decode off-thread and keep the letter initials fallback.
/// No mailbox token, cookie or account content is transmitted.
/// </summary>
public static class AvatarImageBehavior
{
    private static readonly HttpClient Images = new(new HttpClientHandler
    {
        AutomaticDecompression = DecompressionMethods.All,
        UseCookies = false,
        AllowAutoRedirect = false
    }) { Timeout = TimeSpan.FromSeconds(12) };

    private static readonly ConcurrentDictionary<string, Task<ImageSource?>> Cache =
        new(StringComparer.Ordinal);

    public static readonly DependencyProperty SourceUrlProperty =
        DependencyProperty.RegisterAttached("SourceUrl", typeof(string),
            typeof(AvatarImageBehavior),
            new PropertyMetadata(null, OnSourceChanged));

    public static string? GetSourceUrl(DependencyObject target) =>
        target.GetValue(SourceUrlProperty) as string;

    public static void SetSourceUrl(DependencyObject target, string? value) =>
        target.SetValue(SourceUrlProperty, value);

    private static async void OnSourceChanged(
        DependencyObject sender, DependencyPropertyChangedEventArgs e)
    {
        if (sender is not Image image) return;
        var url = e.NewValue as string;
        image.Source = null;
        if (!Uri.TryCreate(url, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps ||
            !uri.Host.Equals("filin.mail.ru", StringComparison.OrdinalIgnoreCase) ||
            !uri.AbsolutePath.Equals("/pic", StringComparison.Ordinal))
            return;

        try
        {
            var photo = await Cache.GetOrAdd(url!, ReadImageAsync);
            // Item containers are reused on scroll. A late download must
            // never display the previous sender's photo on a new account.
            if (string.Equals(GetSourceUrl(image), url, StringComparison.Ordinal))
                image.Source = photo;
        }
        catch (Exception ex)
        {
            Cache.TryRemove(url!, out _);
            DiagnosticLog.Write("avatar_image", ex.GetType().Name);
        }
    }

    private static async Task<ImageSource?> ReadImageAsync(string url)
    {
        using var request = new HttpRequestMessage(HttpMethod.Get, url);
        request.Headers.UserAgent.ParseAdd(MailRuFixedProfile.UserAgent);
        request.Headers.Accept.ParseAdd("image/avif,image/webp,image/png,image/jpeg,image/*");
        using var response = await Images.SendAsync(request,
            HttpCompletionOption.ResponseHeadersRead).ConfigureAwait(false);
        if (!response.IsSuccessStatusCode)
        {
            DiagnosticLog.Write("avatar_image", "http=" + (int)response.StatusCode);
            return null;
        }
        var type = response.Content.Headers.ContentType?.MediaType ?? "";
        if (!type.StartsWith("image/", StringComparison.OrdinalIgnoreCase))
        {
            DiagnosticLog.Write("avatar_image", "invalid-content-type");
            return null;
        }
        if (response.Content.Headers.ContentLength is > 1_048_576)
            return null;

        var bytes = await response.Content.ReadAsByteArrayAsync().ConfigureAwait(false);
        if (bytes.Length is 0 or > 1_048_576) return null;
        using var stream = new MemoryStream(bytes);
        var bitmap = new BitmapImage();
        bitmap.BeginInit();
        bitmap.CacheOption = BitmapCacheOption.OnLoad;
        bitmap.DecodePixelWidth = 64;
        bitmap.StreamSource = stream;
        bitmap.EndInit();
        bitmap.Freeze();
        return bitmap;
    }
}
