package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00060\u0001j\u0002`\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Lru/mail/serverapi/retrofit/session/BadSessionException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", "login", "tokenType", "authName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getLogin", "getTokenType", "getAuthName", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BadSessionException extends IllegalStateException {

    @NotNull
    private final String authName;

    @Nullable
    private final String login;

    @NotNull
    private final String message;

    @NotNull
    private final String tokenType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BadSessionException(@NotNull String message, @Nullable String str, @NotNull String tokenType, @NotNull String authName) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        Intrinsics.checkNotNullParameter(authName, "authName");
        this.message = message;
        this.login = str;
        this.tokenType = tokenType;
        this.authName = authName;
    }

    @NotNull
    public final String getAuthName() {
        return this.authName;
    }

    @Nullable
    public final String getLogin() {
        return this.login;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String getMessage() {
        return this.message;
    }

    @NotNull
    public final String getTokenType() {
        return this.tokenType;
    }

    public /* synthetic */ BadSessionException(String str, String str2, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? null : str2, str3, str4);
    }
}
