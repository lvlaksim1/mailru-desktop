package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.retrofit.InterceptorException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001fB7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e¨\u0006 "}, d2 = {"Lru/mail/serverapi/retrofit/session/RequestSessionException;", "Lru/mail/network/retrofit/InterceptorException;", "reason", "Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "authName", "", "tokenType", "login", "token", "<init>", "(Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getReason", "()Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "getAuthName", "()Ljava/lang/String;", "getTokenType", "getLogin", "getToken", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Reason", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RequestSessionException extends InterceptorException {

    @NotNull
    private final String authName;

    @Nullable
    private final String login;

    @NotNull
    private final Reason reason;

    @Nullable
    private final String token;

    @NotNull
    private final String tokenType;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "", "<init>", "(Ljava/lang/String;I)V", "User", "Token", "TwoStepRequired", "BindRequired", "BadSession", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Reason {
        User,
        Token,
        TwoStepRequired,
        BindRequired,
        BadSession;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        @NotNull
        public static EnumEntries<Reason> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestSessionException(@NotNull Reason reason, @NotNull String authName, @NotNull String tokenType, @Nullable String str, @Nullable String str2) {
        super(null, null, 3, null);
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(authName, "authName");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        this.reason = reason;
        this.authName = authName;
        this.tokenType = tokenType;
        this.login = str;
        this.token = str2;
    }

    public static /* synthetic */ RequestSessionException copy$default(RequestSessionException requestSessionException, Reason reason, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            reason = requestSessionException.reason;
        }
        if ((i10 & 2) != 0) {
            str = requestSessionException.authName;
        }
        if ((i10 & 4) != 0) {
            str2 = requestSessionException.tokenType;
        }
        if ((i10 & 8) != 0) {
            str3 = requestSessionException.login;
        }
        if ((i10 & 16) != 0) {
            str4 = requestSessionException.token;
        }
        String str5 = str4;
        String str6 = str2;
        return requestSessionException.copy(reason, str, str6, str3, str5);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Reason getReason() {
        return this.reason;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAuthName() {
        return this.authName;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLogin() {
        return this.login;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    @NotNull
    public final RequestSessionException copy(@NotNull Reason reason, @NotNull String authName, @NotNull String tokenType, @Nullable String login, @Nullable String token) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        Intrinsics.checkNotNullParameter(authName, "authName");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        return new RequestSessionException(reason, authName, tokenType, login, token);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RequestSessionException)) {
            return false;
        }
        RequestSessionException requestSessionException = (RequestSessionException) other;
        return this.reason == requestSessionException.reason && Intrinsics.areEqual(this.authName, requestSessionException.authName) && Intrinsics.areEqual(this.tokenType, requestSessionException.tokenType) && Intrinsics.areEqual(this.login, requestSessionException.login) && Intrinsics.areEqual(this.token, requestSessionException.token);
    }

    @NotNull
    public final String getAuthName() {
        return this.authName;
    }

    @Nullable
    public final String getLogin() {
        return this.login;
    }

    @NotNull
    public final Reason getReason() {
        return this.reason;
    }

    @Nullable
    public final String getToken() {
        return this.token;
    }

    @NotNull
    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        int iHashCode = ((((this.reason.hashCode() * 31) + this.authName.hashCode()) * 31) + this.tokenType.hashCode()) * 31;
        String str = this.login;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.token;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    @NotNull
    public String toString() {
        return "RequestSessionException(reason=" + this.reason + ", authName=" + this.authName + ", tokenType=" + this.tokenType + ", login=" + this.login + ", token=" + this.token + ")";
    }

    public /* synthetic */ RequestSessionException(Reason reason, String str, String str2, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(reason, str, str2, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? null : str4);
    }
}
