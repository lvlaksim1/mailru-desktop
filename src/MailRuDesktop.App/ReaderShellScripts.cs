using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Single permanent WebView2 document. The browser keeps its own opaque
/// loading layer and stages mail within sandboxed frames; the WPF control
/// never changes Visibility and is never navigated for individual messages.
/// </summary>
internal static class ReaderShellScripts
{
    public static string CreateShell(string background) => """
<!doctype html><html><head><meta charset="utf-8">
<meta http-equiv="Content-Security-Policy"
content="default-src 'none'; img-src data: https: http:; style-src 'unsafe-inline'; frame-src 'self' about: data:">
<style>
html,body { margin:0; padding:0; width:100%; height:100%; overflow:hidden; }
#surface { position:absolute; inset:0; overflow:hidden; }
#frames { position:absolute; inset:0; overflow:hidden; }
#status { position:absolute; inset:0; z-index:10; display:flex;
  justify-content:center; align-items:center; font:14px Segoe UI,Arial,sans-serif; }
iframe.mail-frame { display:block; position:absolute; inset:0; width:100%;
  height:100%; border:0; background:transparent; }
</style></head><body data-revision="-1">
<div id="surface"><div id="frames"></div>
<div id="status">Выберите письмо.</div></div>
</body></html>
""".Replace("</head>", "<style>html,body,#status{background:" + background + ";}</style></head>",
    StringComparison.Ordinal);

    public static string Begin(long revision, string background, string caption)
    {
        var js = """
(() => {
 const rev = __REV__;
 if (!document.body || !document.getElementById('frames')) return false;
 const previous = Number(document.body.dataset.revision || '-1');
 if (rev < previous) return false;
 document.body.dataset.revision = String(rev);
 const pending = document.querySelector('iframe[data-stage]');
 if (pending) pending.remove();
 const status = document.getElementById('status');
 status.textContent = __CAPTION__;
 status.style.background = __BACKGROUND__;
 status.style.display = 'flex';
 document.documentElement.style.background = __BACKGROUND__;
 document.body.style.background = __BACKGROUND__;
 return true;
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
                StringComparison.Ordinal)
            .Replace("__CAPTION__", JsonSerializer.Serialize(caption), StringComparison.Ordinal)
            .Replace("__BACKGROUND__", JsonSerializer.Serialize(background), StringComparison.Ordinal);
    }

    public static string Stage(long revision, string html)
    {
        var js = """
(() => {
 const rev = __REV__;
 if (Number(document.body.dataset.revision) !== rev) return false;
 document.querySelector('iframe[data-stage]')?.remove();
 const frame = document.createElement('iframe');
 frame.className = 'mail-frame';
 frame.dataset.stage = String(rev);
 // Scripts, forms, popups and top navigation remain disallowed.
 // Same-origin allows ONLY the trusted host script to read image state.
 frame.setAttribute('sandbox', 'allow-same-origin');
 frame.setAttribute('referrerpolicy', 'no-referrer');
 frame.setAttribute('loading', 'eager');
 frame.style.visibility = 'hidden';
 frame.style.pointerEvents = 'none';
 document.getElementById('frames').appendChild(frame);
 frame.srcdoc = __HTML__;
 return true;
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
                StringComparison.Ordinal)
            .Replace("__HTML__", JsonSerializer.Serialize(html), StringComparison.Ordinal);
    }

    public static string Poll(long revision)
    {
        var js = """
(() => {
 if (Number(document.body.dataset.revision) !== __REV__) return 'stale';
 const frame = document.querySelector('iframe[data-stage="__REV__"]');
 if (!frame || !frame.contentDocument) return 'pending';
 const doc = frame.contentDocument;
 for (const image of doc.images) image.loading = 'eager';
 if (doc.readyState !== 'complete') return 'pending';
 return Array.from(doc.images).every(image => image.complete) ? 'ready' : 'pending';
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
            StringComparison.Ordinal);
    }

    public static string FinishPendingImages(long revision)
    {
        var js = """
(() => {
 if (Number(document.body.dataset.revision) !== __REV__) return false;
 const frame = document.querySelector('iframe[data-stage="__REV__"]');
 if (!frame?.contentDocument) return false;
 // Slow images cannot redraw the page after its first visible frame.
 for (const image of frame.contentDocument.images) {
   if (image.complete) continue;
   image.removeAttribute('srcset');
   image.removeAttribute('sizes');
   image.src = 'data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=';
 }
 return true;
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
            StringComparison.Ordinal);
    }

    public static string Commit(long revision)
    {
        var js = """
(() => {
 if (Number(document.body.dataset.revision) !== __REV__) return false;
 const staged = document.querySelector('iframe[data-stage="__REV__"]');
 if (!staged?.contentDocument) return false;
 const frames = document.getElementById('frames');
 const old = frames.querySelector('iframe[data-active]');
 staged.removeAttribute('data-stage');
 staged.dataset.active = String(__REV__);
 staged.style.visibility = 'visible';
 staged.style.pointerEvents = 'auto';
 if (old) old.remove();
 document.getElementById('status').style.display = 'none';
 return true;
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
            StringComparison.Ordinal);
    }

    public static string StatusOnly(long revision, string caption)
    {
        var js = """
(() => {
 if (Number(document.body.dataset.revision) !== __REV__) return false;
 const status = document.getElementById('status');
 status.textContent = __CAPTION__;
 status.style.display = 'flex';
 return true;
})()
""";
        return js.Replace("__REV__", revision.ToString(System.Globalization.CultureInfo.InvariantCulture),
                StringComparison.Ordinal)
            .Replace("__CAPTION__", JsonSerializer.Serialize(caption), StringComparison.Ordinal);
    }
}
