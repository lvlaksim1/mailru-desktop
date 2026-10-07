package ru.mail.ludvig_captcha.external;

import androidx.annotation.Size;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00142\u00020\u0001:\r\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0013\b\u0004\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\f\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f ¨\u0006!"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents;", "", "eventName", "", "<init>", "(Ljava/lang/String;)V", "getEventName", "()Ljava/lang/String;", "CaptchaCanceledByUser", "CaptchaShowCriticalError", "CaptchaShowErrorBadConnection", "CaptchaShowed", "CaptchaSuccessDone", "WebViewInflateFailedToastShowed", "UpdateWebViewDialogCancelled", "UpdateWebViewDialogNegativeButtonClicked", "UpdateWebViewDialogPositiveButtonClicked", "UpdateWebViewDialogShowed", "WebViewCreatingError", "WebViewError", "Companion", "Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaCanceledByUser;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowCriticalError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowErrorBadConnection;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowed;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaSuccessDone;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogCancelled;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogNegativeButtonClicked;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogPositiveButtonClicked;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogShowed;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewCreatingError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewInflateFailedToastShowed;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AnalyticEvents {

    @NotNull
    private static final String DURATION_KEY = "duration_mls";

    @NotNull
    private static final String ERROR_DESCRIPTION = "error_description";

    @NotNull
    private static final String IS_DOM_STORAGE_ENABLED = "is_dom_storage_enabled";

    @NotNull
    private final String eventName;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaCanceledByUser;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "durationMls", "", "isDomStorageEnabled", "", "<init>", "(JZ)V", "params", "", "", "getParams", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaCanceledByUser extends AnalyticEvents {

        @NotNull
        private final Map<String, String> params;

        public CaptchaCanceledByUser(long j10, boolean z10) {
            super("captcha_canceled", null);
            this.params = MapsKt.mapOf(TuplesKt.to("duration_mls", String.valueOf(j10)), TuplesKt.to("is_dom_storage_enabled", String.valueOf(z10)));
        }

        @NotNull
        public final Map<String, String> getParams() {
            return this.params;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bR\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowCriticalError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "error", "", "durationMls", "", "isDomStorageEnabled", "", "<init>", "(Ljava/lang/String;JZ)V", "params", "", "getParams", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaShowCriticalError extends AnalyticEvents {

        @NotNull
        private final Map<String, String> params;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CaptchaShowCriticalError(@NotNull String error, long j10, boolean z10) {
            super("critical_error", null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.params = MapsKt.mapOf(TuplesKt.to("error", error), TuplesKt.to("duration_mls", String.valueOf(j10)), TuplesKt.to("is_dom_storage_enabled", String.valueOf(z10)));
        }

        @NotNull
        public final Map<String, String> getParams() {
            return this.params;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowErrorBadConnection;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaShowErrorBadConnection extends AnalyticEvents {

        @NotNull
        public static final CaptchaShowErrorBadConnection INSTANCE = new CaptchaShowErrorBadConnection();

        private CaptchaShowErrorBadConnection() {
            super("bad_network_connection", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaShowed;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaShowed extends AnalyticEvents {

        @NotNull
        public static final CaptchaShowed INSTANCE = new CaptchaShowed();

        private CaptchaShowed() {
            super("captcha_show", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tR\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$CaptchaSuccessDone;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "durationMls", "", "isDomStorageEnabled", "", "<init>", "(JZ)V", "params", "", "", "getParams", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaSuccessDone extends AnalyticEvents {

        @NotNull
        private final Map<String, String> params;

        public CaptchaSuccessDone(long j10, boolean z10) {
            super("captcha_success_done", null);
            this.params = MapsKt.mapOf(TuplesKt.to("duration_mls", String.valueOf(j10)), TuplesKt.to("is_dom_storage_enabled", String.valueOf(z10)));
        }

        @NotNull
        public final Map<String, String> getParams() {
            return this.params;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogCancelled;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateWebViewDialogCancelled extends AnalyticEvents {

        @NotNull
        public static final UpdateWebViewDialogCancelled INSTANCE = new UpdateWebViewDialogCancelled();

        private UpdateWebViewDialogCancelled() {
            super("webview_upd_dial_cancel", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogNegativeButtonClicked;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateWebViewDialogNegativeButtonClicked extends AnalyticEvents {

        @NotNull
        public static final UpdateWebViewDialogNegativeButtonClicked INSTANCE = new UpdateWebViewDialogNegativeButtonClicked();

        private UpdateWebViewDialogNegativeButtonClicked() {
            super("webview_upd_dial_negative", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogPositiveButtonClicked;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateWebViewDialogPositiveButtonClicked extends AnalyticEvents {

        @NotNull
        public static final UpdateWebViewDialogPositiveButtonClicked INSTANCE = new UpdateWebViewDialogPositiveButtonClicked();

        private UpdateWebViewDialogPositiveButtonClicked() {
            super("webview_upd_dial_positive", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$UpdateWebViewDialogShowed;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UpdateWebViewDialogShowed extends AnalyticEvents {

        @NotNull
        public static final UpdateWebViewDialogShowed INSTANCE = new UpdateWebViewDialogShowed();

        private UpdateWebViewDialogShowed() {
            super("webview_upd_dial_show", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewCreatingError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WebViewCreatingError extends AnalyticEvents {

        @NotNull
        public static final WebViewCreatingError INSTANCE = new WebViewCreatingError();

        private WebViewCreatingError() {
            super("webview_init_fail", null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewError;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "errorDescription", "", "<init>", "(Ljava/lang/String;)V", "params", "", "getParams", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WebViewError extends AnalyticEvents {

        @NotNull
        private final Map<String, String> params;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WebViewError(@NotNull String errorDescription) {
            super("webview_error", null);
            Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
            this.params = MapsKt.mapOf(TuplesKt.to("error_description", errorDescription));
        }

        @NotNull
        public final Map<String, String> getParams() {
            return this.params;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/external/AnalyticEvents$WebViewInflateFailedToastShowed;", "Lru/mail/ludvig_captcha/external/AnalyticEvents;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WebViewInflateFailedToastShowed extends AnalyticEvents {

        @NotNull
        public static final WebViewInflateFailedToastShowed INSTANCE = new WebViewInflateFailedToastShowed();

        private WebViewInflateFailedToastShowed() {
            super("init_fail_toast_show", null);
        }
    }

    public /* synthetic */ AnalyticEvents(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NotNull
    public final String getEventName() {
        return this.eventName;
    }

    private AnalyticEvents(@Size(max = 40, min = 1) String str) {
        this.eventName = str;
    }
}
