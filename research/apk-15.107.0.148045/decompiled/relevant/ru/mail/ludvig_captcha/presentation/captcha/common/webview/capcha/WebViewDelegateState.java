package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "", "<init>", "()V", "Loading", "Loaded", "Error", "CriticalError", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$CriticalError;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Error;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Loaded;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Loading;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WebViewDelegateState {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$CriticalError;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CriticalError extends WebViewDelegateState {

        @NotNull
        public static final CriticalError INSTANCE = new CriticalError();

        private CriticalError() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Error;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error extends WebViewDelegateState {

        @NotNull
        public static final Error INSTANCE = new Error();

        private Error() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Loaded;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Loaded extends WebViewDelegateState {

        @NotNull
        public static final Loaded INSTANCE = new Loaded();

        private Loaded() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState$Loading;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "attemptNumber", "", "needLoadPageAgain", "", "<init>", "(IZ)V", "getAttemptNumber", "()I", "getNeedLoadPageAgain", "()Z", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Loading extends WebViewDelegateState {
        private final int attemptNumber;
        private final boolean needLoadPageAgain;

        public Loading(int i10, boolean z10) {
            super(null);
            this.attemptNumber = i10;
            this.needLoadPageAgain = z10;
        }

        public final int getAttemptNumber() {
            return this.attemptNumber;
        }

        public final boolean getNeedLoadPageAgain() {
            return this.needLoadPageAgain;
        }
    }

    public /* synthetic */ WebViewDelegateState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private WebViewDelegateState() {
    }
}
