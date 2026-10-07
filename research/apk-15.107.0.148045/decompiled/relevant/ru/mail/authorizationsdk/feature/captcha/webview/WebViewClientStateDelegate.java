package ru.mail.authorizationsdk.feature.captcha.webview;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\nR\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate;", "", "<init>", "()V", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "getState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "onPageStarted", "", "onPageFinished", "onPageError", "onLocalPageLoadError", "State", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewClientStateDelegate {
    public static final int $stable = 8;

    @NotNull
    private final MutableStateFlow<State> state = StateFlowKt.MutableStateFlow(null);

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "", "<init>", "()V", "Loading", "Loaded", "Error", "LocalPageLoadError", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Error;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Loaded;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Loading;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$LocalPageLoadError;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class State {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Error;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error extends State {
            public static final int $stable = 0;

            @NotNull
            public static final Error INSTANCE = new Error();

            private Error() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Error);
            }

            public int hashCode() {
                return 1260185393;
            }

            @NotNull
            public String toString() {
                return "Error";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Loaded;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Loaded extends State {
            public static final int $stable = 0;

            @NotNull
            public static final Loaded INSTANCE = new Loaded();

            private Loaded() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Loaded);
            }

            public int hashCode() {
                return 608157692;
            }

            @NotNull
            public String toString() {
                return "Loaded";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$Loading;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Loading extends State {
            public static final int $stable = 0;

            @NotNull
            public static final Loading INSTANCE = new Loading();

            private Loading() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Loading);
            }

            public int hashCode() {
                return 1673023525;
            }

            @NotNull
            public String toString() {
                return "Loading";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State$LocalPageLoadError;", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class LocalPageLoadError extends State {
            public static final int $stable = 0;

            @NotNull
            public static final LocalPageLoadError INSTANCE = new LocalPageLoadError();

            private LocalPageLoadError() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof LocalPageLoadError);
            }

            public int hashCode() {
                return -2061715777;
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
        this.state.setValue(State.Loading.INSTANCE);
    }
}
