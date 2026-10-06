package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lru/mail/auth/request/VKOauth2SendAgentRequest;", "Lru/mail/auth/request/OAuthSendAgentRequest;", "context", "Landroid/content/Context;", "hostProvider", "Lru/mail/network/HostProvider;", "authUri", "", "email", "isResetSoftVkidBind", "", "usePostParams", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getEmail", "()Ljava/lang/String;", "getResult", "", "VKParams", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VKOauth2SendAgentRequest extends OAuthSendAgentRequest {

    @NotNull
    private final String email;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\t8\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\t8\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/auth/request/VKOauth2SendAgentRequest$VKParams;", "Lru/mail/auth/request/OAuthSendAgentCommand$Params;", "context", "Landroid/content/Context;", "isResetSoftVkidBind", "", "<init>", "(Landroid/content/Context;Z)V", "simple", "", "mobJson", "oauth2", "resetSoftVkidBind", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class VKParams extends OAuthSendAgentCommand.Params {

        @Param(method = HttpMethod.GET, name = "mob_json")
        private final int mobJson;

        @Param(method = HttpMethod.GET, name = "oauth2")
        private final int oauth2;

        @Param(method = HttpMethod.GET, name = "reset_soft_vkid_bind")
        private final int resetSoftVkidBind;

        @Param(method = HttpMethod.GET, name = "simple")
        private final int simple;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VKParams(@NotNull Context context, boolean z10) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.simple = 1;
            this.mobJson = 1;
            this.oauth2 = AuthenticatorConfig.getInstance().isOAuthEnabled() ? 1 : 0;
            this.resetSoftVkidBind = z10 ? 1 : 0;
        }
    }

    public /* synthetic */ VKOauth2SendAgentRequest(Context context, HostProvider hostProvider, String str, String str2, boolean z10, boolean z11, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, hostProvider, str, (i10 & 8) != 0 ? "" : str2, (i10 & 16) != 0 ? false : z10, z11);
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    public Object getResult() {
        Object result = super.getResult();
        if ((result instanceof CommandStatus.OK) && this.email.length() > 0) {
            return new AuthCommandStatus.SOCIAL_AUTH_OK(this.email, super.getResult());
        }
        Intrinsics.checkNotNull(result);
        return result;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKOauth2SendAgentRequest(@NotNull Context context, @NotNull HostProvider hostProvider, @NotNull String authUri, @NotNull String email, boolean z10, boolean z11) {
        super(context, new VKParams(context, z10), hostProvider, authUri, z11);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hostProvider, "hostProvider");
        Intrinsics.checkNotNullParameter(authUri, "authUri");
        Intrinsics.checkNotNullParameter(email, "email");
        this.email = email;
    }
}
