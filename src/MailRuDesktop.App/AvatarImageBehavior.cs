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

    // Image controls are recycled by the virtualized message list. Each one
    // needs its own bounded retry state so a temporary network error does not
    // leave a sender's avatar missing until the next application restart.
    private sealed class ImageRequestState
    {
        public string? Url;
        public int Attempts;
        public bool InFlight;
    }

    private static readonly System.Runtime.CompilerServices.ConditionalWeakTable<Image, ImageRequestState>
        Requests = new();

    internal static bool IsSupportedAvatarUrl(string? candidate) =>
        Uri.TryCreate(candidate, UriKind.Absolute, out var uri) &&
        uri.Scheme == Uri.UriSchemeHttps &&
        uri.Host.Equals("filin.mail.ru", StringComparison.OrdinalIgnoreCase) &&
        uri.AbsolutePath.Equals("/pic", StringComparison.Ordinal);

    private static void OnSourceChanged(
        DependencyObject sender, DependencyPropertyChangedEventArgs e)
    {
        if (sender is not Image image) return;
        var url = e.NewValue as string;
        image.Source = null;
        // A previous request may still be finishing for a recycled row.
        // Give the new sender a distinct request state.
        var state = new ImageRequestState { Url = url };
        Requests.Remove(image);
        Requests.Add(image, state);
        image.Loaded -= AvatarImage_Loaded;
        image.Loaded += AvatarImage_Loaded;

        if (!IsSupportedAvatarUrl(url))
        {
            if (!string.IsNullOrWhiteSpace(url))
                DiagnosticLog.Write("avatar_image", "unsupported-url");
            return;
        }

        _ = LoadImageAsync(image, url!, state);
    }

    private static void AvatarImage_Loaded(object sender, RoutedEventArgs e)
    {
        if (sender is not Image image || image.Source is not null)
            return;
        var state = Requests.GetValue(image, _ => new ImageRequestState());
        var url = GetSourceUrl(image);
        if (state.Url != url)
        {
            state = new ImageRequestState { Url = url };
            Requests.Remove(image);
            Requests.Add(image, state);
        }
        if (IsSupportedAvatarUrl(url))
            _ = LoadImageAsync(image, url!, state);
    }

    private static async Task LoadImageAsync(
        Image image, string url, ImageRequestState state)
    {
        if (state.InFlight || state.Attempts >= 3 ||
            !string.Equals(state.Url, url, StringComparison.Ordinal))
            return;

        state.InFlight = true;
        state.Attempts++;
        try
        {
            var photo = await Cache.GetOrAdd(url, ReadImageAsync);
            if (photo is null)
                Cache.TryRemove(url, out _);
            if (string.Equals(GetSourceUrl(image), url, StringComparison.Ordinal))
                image.Source = photo;
            if (photo is not null) return;
        }
        catch (Exception ex)
        {
            Cache.TryRemove(url, out _);
            DiagnosticLog.Write("avatar_image", ex.GetType().Name);
        }
        finally
        {
            state.InFlight = false;
        }

        if (state.Attempts < 3 && image.IsLoaded &&
            string.Equals(GetSourceUrl(image), url, StringComparison.Ordinal))
        {
            // Retry only this visible image and only twice, with a delay.
            // No reload loop for empty/unsupported URLs or recycled rows.
            await Task.Delay(TimeSpan.FromSeconds(state.Attempts * 7));
            if (image.IsLoaded && image.Source is null &&
                string.Equals(GetSourceUrl(image), url, StringComparison.Ordinal))
                await LoadImageAsync(image, url, state);
        }
    }

    private static async Task<ImageSource?> ReadImageAsync(string url)
    {
        var current = new Uri(url);
        HttpResponseMessage? response = null;
        try
        {
            for (var hop = 0; hop < 4; hop++)
            {
                if (current.Scheme != Uri.UriSchemeHttps ||
                    !(current.Host.Equals("filin.mail.ru", StringComparison.OrdinalIgnoreCase) ||
                      current.Host.EndsWith(".mail.ru", StringComparison.OrdinalIgnoreCase) ||
                      current.Host.EndsWith(".imgsmail.ru", StringComparison.OrdinalIgnoreCase)))
                {
                    DiagnosticLog.Write("avatar_image", "redirect-disallowed-host");
                    return null;
                }
                using var request = new HttpRequestMessage(HttpMethod.Get, current);
                request.Headers.UserAgent.ParseAdd(MailRuFixedProfile.UserAgent);
                request.Headers.Accept.ParseAdd("image/png,image/jpeg,image/webp,image/*");
                response?.Dispose();
                response = await Images.SendAsync(request,
                    HttpCompletionOption.ResponseHeadersRead).ConfigureAwait(false);
                if ((int)response.StatusCode is not (301 or 302 or 303 or 307 or 308))
                    break;
                var location = response.Headers.Location;
                if (location is null) return null;
                current = location.IsAbsoluteUri ? location : new Uri(current, location);
            }
            if (response is null || (int)response.StatusCode is 301 or 302 or 303 or 307 or 308)
            {
                DiagnosticLog.Write("avatar_image", "redirect-limit");
                return null;
            }
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
        finally { response?.Dispose(); }
    }
}
