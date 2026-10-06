package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B/\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB7\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\u000f¨\u0006\u0010"}, d2 = {"Lru/mail/auth/request/OAuthSendAgentRequest;", "Lru/mail/auth/request/AuthorizeRequest;", "Lru/mail/auth/request/OAuthSendAgentCommand;", "context", "Landroid/content/Context;", "hostProvider", "Lru/mail/network/HostProvider;", "authUri", "", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Ljava/lang/String;Z)V", "params", "Lru/mail/auth/request/OAuthSendAgentCommand$Params;", "(Landroid/content/Context;Lru/mail/auth/request/OAuthSendAgentCommand$Params;Lru/mail/network/HostProvider;Ljava/lang/String;Z)V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class OAuthSendAgentRequest extends AuthorizeRequest<OAuthSendAgentCommand> {
    public OAuthSendAgentRequest(@Nullable Context context, @Nullable HostProvider hostProvider, @Nullable String str, boolean z10) {
        super(context, new OAuthSendAgentCommand(context, hostProvider, str, z10));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OAuthSendAgentRequest(@Nullable Context context, @NotNull OAuthSendAgentCommand.Params params, @Nullable HostProvider hostProvider, @Nullable String str, boolean z10) {
        super(context, new OAuthSendAgentCommand(context, params, hostProvider, str, z10));
        Intrinsics.checkNotNullParameter(params, "params");
    }
}
