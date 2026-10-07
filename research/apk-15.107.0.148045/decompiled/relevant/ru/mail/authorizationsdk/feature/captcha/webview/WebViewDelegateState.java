package ru.mail.authorizationsdk.feature.captcha.webview;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState;", "", "<init>", "()V", "Loading", "Loaded", "Error", "LocalPageLoadError", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Error;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Loaded;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Loading;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$LocalPageLoadError;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WebViewDelegateState {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Error;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error extends WebViewDelegateState {
        public static final int $stable = 0;

        @NotNull
        public static final Error INSTANCE = new Error();

        private Error() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Loaded;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Loaded extends WebViewDelegateState {
        public static final int $stable = 0;

        @NotNull
        public static final Loaded INSTANCE = new Loaded();

        private Loaded() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$Loading;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState;", "attemptNumber", "", "<init>", "(I)V", "getAttemptNumber", "()I", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Loading extends WebViewDelegateState {
        public static final int $stable = 0;
        private final int attemptNumber;

        public Loading(int i10) {
            super(null);
            this.attemptNumber = i10;
        }

        public final int getAttemptNumber() {
            return this.attemptNumber;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState$LocalPageLoadError;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewDelegateState;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LocalPageLoadError extends WebViewDelegateState {
        public static final int $stable = 0;

        @NotNull
        public static final LocalPageLoadError INSTANCE = new LocalPageLoadError();

        private LocalPageLoadError() {
            super(null);
        }
    }

    public /* synthetic */ WebViewDelegateState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private WebViewDelegateState() {
    }
}
