using System.Net;
using System.Text;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// A complete conversation is composed only of server-proven message IDs.
/// Details work without JavaScript; original HTML is isolated inside sandboxed
/// srcdoc frames so individual messages cannot modify neighboring headers.
/// </summary>
internal static class MailRuConversationHtml
{
    internal static string Render(MailRuConversation conversation,
        string selectedId, IReadOnlyDictionary<string, MailRuFullMessage> bodies,
        string? background = null, string? foreground = null,
        string? muted = null)
    {
        var members = conversation.Members
            .OrderByDescending(m => m.DateUnix ?? long.MinValue).ToArray();
        if (members.Length == 0) return "";
        var e = new StringBuilder(8192);
        e.Append("""
<!doctype html><html lang="ru"><head><meta charset="utf-8">
<meta http-equiv="Content-Security-Policy"
content="default-src 'none'; img-src https: http: data:; frame-src 'self' about: data:; style-src 'unsafe-inline';">
<style>
:root{--surface:__SURFACE__;--ink:__INK__;--muted:__MUTED__}
*{box-sizing:border-box} body{font:14px Segoe UI,Arial,sans-serif;
margin:0;padding:10px 12px;background:var(--surface);color:var(--ink);overflow:auto}
.summary{margin-bottom:12px;color:var(--muted);font-size:12px}
details{border:1px solid #e0e3e7;border-radius:8px;margin-bottom:9px;
background:var(--surface);overflow:hidden}
summary{cursor:pointer;list-style:none;display:flex;align-items:center;
gap:10px;padding:13px 14px;min-height:62px}
summary::-webkit-details-marker{display:none}
.chevron{width:18px;color:var(--muted);font-size:17px;flex-shrink:0}
details[open] .chevron{transform:rotate(90deg)}
.who{font-weight:600;overflow-wrap:anywhere}
.email{color:var(--muted);font-weight:400;font-size:12px}
.when{color:var(--muted);font-size:12px;margin-left:auto;white-space:nowrap}
.preview{display:block;color:#667281;font-size:12px;margin-top:5px;
font-weight:400;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width:640px}
.mail-body{border-top:1px solid #eceef0;padding:13px 16px}
.mail-to{font-size:12px;color:var(--muted);margin-bottom:9px;overflow-wrap:anywhere}
.mail-text{white-space:pre-wrap;overflow-wrap:anywhere;line-height:1.5}
.mail-html{display:block;border:0;width:100%;height:460px;max-height:78vh;background:var(--surface)}
.attachments{border-top:1px solid #eceef0;padding-top:9px;margin-top:10px;
font-size:12px;color:var(--muted);overflow-wrap:anywhere}
.not-loaded{color:var(--muted);font-size:13px}
</style></head><body>
""".Replace("__SURFACE__", CssHex(background, "#ffffff"))
   .Replace("__INK__", CssHex(foreground, "#252525"))
   .Replace("__MUTED__", CssHex(muted, "#667281")));
        var n = conversation.VerifiedCount ?? members.Length;
        e.Append("<div class=\"summary\">Писем в диалоге: ")
            .Append(n).Append("</div>");
        foreach (var member in members)
        {
            bodies.TryGetValue(member.Id, out var full);
            var sender = full?.SenderDisplay ??
                (member.Sender.Length > 0 ? member.Sender :
                 member.SenderEmail.Length > 0 ? member.SenderEmail : "Отправитель не указан");
            var date = full?.DateDisplay ??
                (member.DateUnix is long unix ? SafeDate(unix) : "");
            var open = member.Id == selectedId;
            e.Append("<details").Append(open ? " open" : "")
                .Append("><summary><span class=\"chevron\">&#8250;</span><span style=\"min-width:0\">")
                .Append("<span class=\"who\">").Append(H(sender)).Append("</span>");
            var email = full?.FromEmail ?? member.SenderEmail;
            if (email.Length > 0 && !sender.Contains(email, StringComparison.OrdinalIgnoreCase))
                e.Append(" <span class=\"email\">&lt;").Append(H(email))
                    .Append("&gt;</span>");
            e.Append("<span class=\"preview\">").Append(H(
                    full?.Subject ?? (member.Subject.Length == 0
                        ? member.Snippet : member.Subject)))
                .Append("</span></span><span class=\"when\">")
                .Append(H(date)).Append("</span></summary>")
                .Append("<div class=\"mail-body\">");
            var to = full?.To;
            if (to is { Count: > 0 })
                e.Append("<div class=\"mail-to\">Кому: ")
                    .Append(H(string.Join(", ", to))).Append("</div>");
            if (full is not null && !string.IsNullOrWhiteSpace(full.Html))
            {
                // Attribute encoding is essential: untrusted mail markup
                // must stay inside its own sandboxed browser document.
                e.Append("<iframe class=\"mail-html\" sandbox=\"allow-same-origin\" ")
                    .Append("referrerpolicy=\"no-referrer\" srcdoc=\"")
                    .Append(H(full.Html)).Append("\"></iframe>");
            }
            else if (full is not null)
                e.Append("<div class=\"mail-text\">")
                    .Append(H(full.Text)).Append("</div>");
            else
                e.Append("<div class=\"not-loaded\">")
                    .Append(H(member.Snippet.Length == 0
                        ? "Содержимое этого письма пока недоступно."
                        : member.Snippet))
                    .Append("</div>");
            if (full?.Attachments.Count > 0)
                e.Append("<div class=\"attachments\">Вложения: ")
                    .Append(H(string.Join(", ", full.Attachments.Select(x => x.DisplayName))))
                    .Append("</div>");
            e.Append("</div></details>");
        }
        if (n > members.Length)
            e.Append("<p class=\"summary\">Сервер указал ").Append(n)
                .Append(" писем, но передал идентификаторы только ")
                .Append(members.Length)
                .Append(". Остальная история не подменяется догадками.</p>");
        e.Append("</body></html>");
        return e.ToString();
    }

    private static string CssHex(string? candidate, string fallback)
    {
        if (candidate is null || candidate.Length is not (7 or 9) ||
            candidate[0] != '#' ||
            !candidate.AsSpan(1).ToArray().All(Uri.IsHexDigit))
            return fallback;
        return candidate;
    }

    private static string H(string value) => WebUtility.HtmlEncode(value);

    private static string SafeDate(long unix)
    {
        try
        {
            return DateTimeOffset.FromUnixTimeSeconds(unix)
                .ToLocalTime().ToString("dd.MM.yyyy HH:mm");
        }
        catch (ArgumentOutOfRangeException) { return ""; }
    }
}
