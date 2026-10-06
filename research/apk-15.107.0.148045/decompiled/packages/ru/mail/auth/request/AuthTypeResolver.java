package ru.mail.auth.request;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.Authenticator;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/request/AuthTypeResolver;", "", "<init>", "()V", "resolveCgiBin", "Lru/mail/auth/Authenticator$Type;", Event.Companion.Network.Fail.REQUEST_TAG, "Lru/mail/auth/request/CgiBinAuthSendAgentRequest;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthTypeResolver {
    @NotNull
    public final Authenticator.Type resolveCgiBin(@NotNull CgiBinAuthSendAgentRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request instanceof QrAuthSendAgentRequest) {
            return Authenticator.Type.QR_LOGIN;
        }
        if (request instanceof WebAuthNSendAgentRequest) {
            return Authenticator.Type.WEB_AUTH_N;
        }
        throw new NoWhenBranchMatchedException();
    }
}
