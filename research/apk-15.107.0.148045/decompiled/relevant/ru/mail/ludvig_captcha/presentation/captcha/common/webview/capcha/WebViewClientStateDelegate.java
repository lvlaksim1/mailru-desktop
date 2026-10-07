package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\nR\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate;", "", "<init>", "()V", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "getState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "onPageStarted", "", "onPageFinished", "onPageError", "onLocalPageLoadError", "fullRetry", "State", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewClientStateDelegate {

    @NotNull
    private final MutableStateFlow<State> state = StateFlowKt.MutableStateFlow(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "", "<init>", "()V", "Loading", "Loaded", "Error", "LocalPageLoadError", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Error;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Loaded;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Loading;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$LocalPageLoadError;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class State {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Error;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error extends State {

            @NotNull
            public static final Error INSTANCE = new Error();

            private Error() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Error);
            }

            public int hashCode() {
                return 19801301;
            }

            @NotNull
            public String toString() {
                return "Error";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Loaded;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Loaded extends State {

            @NotNull
            public static final Loaded INSTANCE = new Loaded();

            private Loaded() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Loaded);
            }

            public int hashCode() {
                return 810956504;
            }

            @NotNull
            public String toString() {
                return "Loaded";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$Loading;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "needLoadPageAgain", "", "<init>", "(Z)V", "getNeedLoadPageAgain", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Loading extends State {
            private final boolean needLoadPageAgain;

            public Loading(boolean z10) {
                super(null);
                this.needLoadPageAgain = z10;
            }

            public static /* synthetic */ Loading copy$default(Loading loading, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    z10 = loading.needLoadPageAgain;
                }
                return loading.copy(z10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final boolean getNeedLoadPageAgain() {
                return this.needLoadPageAgain;
            }

            @NotNull
            public final Loading copy(boolean needLoadPageAgain) {
                return new Loading(needLoadPageAgain);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Loading) && this.needLoadPageAgain == ((Loading) other).needLoadPageAgain;
            }

            public final boolean getNeedLoadPageAgain() {
                return this.needLoadPageAgain;
            }

            public int hashCode() {
                return Boolean.hashCode(this.needLoadPageAgain);
            }

            @NotNull
            public String toString() {
                return "Loading(needLoadPageAgain=" + this.needLoadPageAgain + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State$LocalPageLoadError;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class LocalPageLoadError extends State {

            @NotNull
            public static final LocalPageLoadError INSTANCE = new LocalPageLoadError();

            private LocalPageLoadError() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof LocalPageLoadError);
            }

            public int hashCode() {
                return 2110505883;
            }

            @NotNull
            public String toString() {
                return "LocalPageLoadError";
            }
        }

        public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private State() {
        }
    }

    public final void fullRetry() {
        this.state.setValue(new State.Loading(true));
    }

    @NotNull
    public final MutableStateFlow<State> getState() {
        return this.state;
    }

    public final void onLocalPageLoadError() {
        this.state.setValue(State.LocalPageLoadError.INSTANCE);
    }

    public final void onPageError() {
        this.state.setValue(State.Error.INSTANCE);
    }

    public final void onPageFinished() {
        this.state.setValue(State.Loaded.INSTANCE);
    }

    public final void onPageStarted() {
        this.state.setValue(new State.Loading(false));
    }
}
