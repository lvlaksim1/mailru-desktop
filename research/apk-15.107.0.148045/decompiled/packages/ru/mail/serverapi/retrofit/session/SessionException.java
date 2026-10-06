package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.network.retrofit.InterceptorException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionException;", "Lru/mail/network/retrofit/InterceptorException;", "reason", "Lru/mail/serverapi/retrofit/session/SessionError;", "<init>", "(Lru/mail/serverapi/retrofit/session/SessionError;)V", "getReason", "()Lru/mail/serverapi/retrofit/session/SessionError;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionException extends InterceptorException {

    @NotNull
    private final SessionError reason;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionException(@NotNull SessionError reason) {
        super(null, null, 3, null);
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.reason = reason;
    }

    @NotNull
    public final SessionError getReason() {
        return this.reason;
    }
}
