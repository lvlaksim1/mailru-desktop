package ru.mail.authorizationsdk.feature.captcha.analytics;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bf\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\b\u0010\u0010\u001a\u00020\u0003H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH&¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "", "captchaCanceledByUser", "", "durationMls", "", "isDomStorageEnabled", "", "captchaCriticalError", "error", "", "captchaShowErrorBadConnection", "captchaShowed", "captchaSuccessDone", "updateWebViewDialogNegativeButtonClicked", "updateWebViewDialogPositiveButtonClicked", "updateWebViewDialogShowed", "onWebViewError", "errorDescription", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LudwigCaptchaAnalyticEvents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @NotNull
    public static final String DURATION_KEY = "duration_mls";

    @NotNull
    public static final String ERROR_DESCRIPTION = "error_description";

    @NotNull
    public static final String EVENT_PREFIX = "LudwigEvent_";

    @NotNull
    public static final String IS_DOM_STORAGE_ENABLED = "is_dom_storage_enabled";

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents$Companion;", "", "<init>", "()V", "EVENT_PREFIX", "", "DURATION_KEY", "IS_DOM_STORAGE_ENABLED", "ERROR_DESCRIPTION", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public static final String DURATION_KEY = "duration_mls";

        @NotNull
        public static final String ERROR_DESCRIPTION = "error_description";

        @NotNull
        public static final String EVENT_PREFIX = "LudwigEvent_";

        @NotNull
        public static final String IS_DOM_STORAGE_ENABLED = "is_dom_storage_enabled";

        private Companion() {
        }
    }

    void captchaCanceledByUser(long durationMls, boolean isDomStorageEnabled);

    void captchaCriticalError(@NotNull String error, boolean isDomStorageEnabled);

    void captchaShowErrorBadConnection();

    void captchaShowed();

    void captchaSuccessDone(long durationMls, boolean isDomStorageEnabled);

    void onWebViewError(@NotNull String errorDescription);

    void updateWebViewDialogNegativeButtonClicked();

    void updateWebViewDialogPositiveButtonClicked();

    void updateWebViewDialogShowed();
}
