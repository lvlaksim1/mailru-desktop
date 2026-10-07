package ru.mail.auth.webview;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/webview/LoadingState;", "", "Loading", "PageLoaded", "ContentLoaded", "LoadingError", "Lru/mail/auth/webview/LoadingState$ContentLoaded;", "Lru/mail/auth/webview/LoadingState$Loading;", "Lru/mail/auth/webview/LoadingState$LoadingError;", "Lru/mail/auth/webview/LoadingState$PageLoaded;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LoadingState {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/webview/LoadingState$ContentLoaded;", "Lru/mail/auth/webview/LoadingState;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ContentLoaded implements LoadingState {

        @NotNull
        public static final ContentLoaded INSTANCE = new ContentLoaded();

        private ContentLoaded() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/webview/LoadingState$Loading;", "Lru/mail/auth/webview/LoadingState;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Loading implements LoadingState {

        @Nullable
        private final String url;

        public Loading(@Nullable String str) {
            this.url = str;
        }

        public static /* synthetic */ Loading copy$default(Loading loading, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = loading.url;
            }
            return loading.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final Loading copy(@Nullable String url) {
            return new Loading(url);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Loading) && Intrinsics.areEqual(this.url, ((Loading) other).url);
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            String str = this.url;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return "Loading(url=" + this.url + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/webview/LoadingState$LoadingError;", "Lru/mail/auth/webview/LoadingState;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LoadingError implements LoadingState {

        @NotNull
        public static final LoadingError INSTANCE = new LoadingError();

        private LoadingError() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/webview/LoadingState$PageLoaded;", "Lru/mail/auth/webview/LoadingState;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PageLoaded implements LoadingState {

        @NotNull
        public static final PageLoaded INSTANCE = new PageLoaded();

        private PageLoaded() {
        }
    }
}
