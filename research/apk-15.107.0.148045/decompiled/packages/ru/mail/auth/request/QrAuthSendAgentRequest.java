package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \r2\u00020\u0001:\u0001\rB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lru/mail/auth/request/QrAuthSendAgentRequest;", "Lru/mail/auth/request/CgiBinAuthSendAgentRequest;", "context", "Landroid/content/Context;", "hostProvider", "Lru/mail/network/HostProvider;", "authUri", "", "email", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Ljava/lang/String;Ljava/lang/String;Z)V", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class QrAuthSendAgentRequest extends CgiBinAuthSendAgentRequest {

    @NotNull
    public static final String TYPE = "qr_login";

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QrAuthSendAgentRequest(@NotNull Context context, @NotNull HostProvider hostProvider, @NotNull String authUri, @NotNull String email, boolean z10) {
        super(context, hostProvider, authUri, email, TYPE, z10, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hostProvider, "hostProvider");
        Intrinsics.checkNotNullParameter(authUri, "authUri");
        Intrinsics.checkNotNullParameter(email, "email");
    }
}
