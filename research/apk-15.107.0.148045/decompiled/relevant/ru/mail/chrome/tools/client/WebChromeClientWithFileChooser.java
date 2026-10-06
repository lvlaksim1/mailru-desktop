package ru.mail.chrome.tools.client;

import android.net.Uri;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.chrome.tools.file.chooser.FileChooserOpener;
import ru.mail.chrome.tools.file.chooser.WebViewFileReceiver;
import ru.mail.util.log.EmptyLogger;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;
import ru.mail.util.log.Logger;
import ru.mail.utils.LogLevel;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ2\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0014\u0010\u0012\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/chrome/tools/client/WebChromeClientWithFileChooser;", "Landroid/webkit/WebChromeClient;", "fileChooserOpener", "Lru/mail/chrome/tools/file/chooser/FileChooserOpener;", "clientConfig", "Lru/mail/chrome/tools/client/ChromeClientConfig;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/chrome/tools/file/chooser/FileChooserOpener;Lru/mail/chrome/tools/client/ChromeClientConfig;Lru/mail/util/log/Logger;)V", "fileReceiver", "Lru/mail/chrome/tools/file/chooser/WebViewFileReceiver;", "logFilter", "Lru/mail/util/log/LogFilter;", "onShowFileChooser", "", "webView", "Landroid/webkit/WebView;", "filePathCallback", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "fileChooserParams", "Landroid/webkit/WebChromeClient$FileChooserParams;", "onConsoleMessage", "consoleMessage", "Landroid/webkit/ConsoleMessage;", "chrome-tools_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class WebChromeClientWithFileChooser extends WebChromeClient {

    @NotNull
    private final ChromeClientConfig clientConfig;

    @NotNull
    private final FileChooserOpener fileChooserOpener;

    @NotNull
    private final WebViewFileReceiver fileReceiver;

    @NotNull
    private LogFilter logFilter;

    @NotNull
    private final Logger logger;

    public WebChromeClientWithFileChooser(@NotNull FileChooserOpener fileChooserOpener, @NotNull ChromeClientConfig clientConfig, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(fileChooserOpener, "fileChooserOpener");
        Intrinsics.checkNotNullParameter(clientConfig, "clientConfig");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.fileChooserOpener = fileChooserOpener;
        this.clientConfig = clientConfig;
        this.logger = logger.createLogger("WebChromeClient");
        this.fileReceiver = new WebViewFileReceiver();
        Formats.ParamFormat paramFormatNewJsonFormat = Formats.newJsonFormat("token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat2 = Formats.newJsonFormat("access_token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat2, "newJsonFormat(...)");
        this.logFilter = new LogFilter(paramFormatNewJsonFormat, paramFormatNewJsonFormat2);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onConsoleMessage(@NotNull ConsoleMessage consoleMessage) {
        Intrinsics.checkNotNullParameter(consoleMessage, "consoleMessage");
        LogLevel.Companion companion = LogLevel.INSTANCE;
        ConsoleMessage.MessageLevel messageLevel = consoleMessage.messageLevel();
        Intrinsics.checkNotNullExpressionValue(messageLevel, "messageLevel(...)");
        LogLevel logLevelFrom = companion.from(messageLevel);
        if (logLevelFrom == null || logLevelFrom.getLevel() > this.clientConfig.getConsoleLogLevel().getLevel()) {
            return super.onConsoleMessage(consoleMessage);
        }
        LogFilter logFilter = this.logFilter;
        String strMessage = consoleMessage.message();
        if (strMessage == null) {
            strMessage = "";
        }
        logLevelFrom.log(this.logger, "Web Message : " + logFilter.filter(strMessage));
        return false;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(@Nullable WebView webView, @Nullable ValueCallback<Uri[]> filePathCallback, @Nullable WebChromeClient.FileChooserParams fileChooserParams) {
        this.fileReceiver.saveUploadCallback(filePathCallback);
        if (fileChooserParams != null) {
            return this.fileChooserOpener.openFileChooser(fileChooserParams, this.fileReceiver);
        }
        return false;
    }

    public /* synthetic */ WebChromeClientWithFileChooser(FileChooserOpener fileChooserOpener, ChromeClientConfig chromeClientConfig, Logger logger, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(fileChooserOpener, (i10 & 2) != 0 ? new ChromeClientConfig(null, 1, null) : chromeClientConfig, (i10 & 4) != 0 ? new EmptyLogger() : logger);
    }
}
