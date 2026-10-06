package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionError;", "", "NoAuth", "SwitchToImap", "ConnectionError", "Unexpected", "Lru/mail/serverapi/retrofit/session/SessionError$ConnectionError;", "Lru/mail/serverapi/retrofit/session/SessionError$NoAuth;", "Lru/mail/serverapi/retrofit/session/SessionError$SwitchToImap;", "Lru/mail/serverapi/retrofit/session/SessionError$Unexpected;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionError {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionError$ConnectionError;", "Lru/mail/serverapi/retrofit/session/SessionError;", "<init>", "()V", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ConnectionError implements SessionError {

        @NotNull
        public static final ConnectionError INSTANCE = new ConnectionError();

        private ConnectionError() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionError$NoAuth;", "Lru/mail/serverapi/retrofit/session/SessionError;", "login", "", "<init>", "(Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NoAuth implements SessionError {

        @NotNull
        private final String login;

        public NoAuth(@NotNull String login) {
            Intrinsics.checkNotNullParameter(login, "login");
            this.login = login;
        }

        public static /* synthetic */ NoAuth copy$default(NoAuth noAuth, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = noAuth.login;
            }
            return noAuth.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final NoAuth copy(@NotNull String login) {
            Intrinsics.checkNotNullParameter(login, "login");
            return new NoAuth(login);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NoAuth) && Intrinsics.areEqual(this.login, ((NoAuth) other).login);
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        public int hashCode() {
            return this.login.hashCode();
        }

        @NotNull
        public String toString() {
            return "NoAuth(login=" + this.login + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionError$SwitchToImap;", "Lru/mail/serverapi/retrofit/session/SessionError;", "imapSettings", "", "<init>", "(Ljava/lang/String;)V", "getImapSettings", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SwitchToImap implements SessionError {

        @NotNull
        private final String imapSettings;

        public SwitchToImap(@NotNull String imapSettings) {
            Intrinsics.checkNotNullParameter(imapSettings, "imapSettings");
            this.imapSettings = imapSettings;
        }

        public static /* synthetic */ SwitchToImap copy$default(SwitchToImap switchToImap, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = switchToImap.imapSettings;
            }
            return switchToImap.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImapSettings() {
            return this.imapSettings;
        }

        @NotNull
        public final SwitchToImap copy(@NotNull String imapSettings) {
            Intrinsics.checkNotNullParameter(imapSettings, "imapSettings");
            return new SwitchToImap(imapSettings);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SwitchToImap) && Intrinsics.areEqual(this.imapSettings, ((SwitchToImap) other).imapSettings);
        }

        @NotNull
        public final String getImapSettings() {
            return this.imapSettings;
        }

        public int hashCode() {
            return this.imapSettings.hashCode();
        }

        @NotNull
        public String toString() {
            return "SwitchToImap(imapSettings=" + this.imapSettings + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionError$Unexpected;", "Lru/mail/serverapi/retrofit/session/SessionError;", "<init>", "()V", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Unexpected implements SessionError {

        @NotNull
        public static final Unexpected INSTANCE = new Unexpected();

        private Unexpected() {
        }
    }
}
