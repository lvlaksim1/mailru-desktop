package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0011B9\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/auth/request/CgiBinAuthSendAgentRequest;", "Lru/mail/auth/request/OAuthSendAgentRequest;", "context", "Landroid/content/Context;", "hostProvider", "Lru/mail/network/HostProvider;", "authUri", "", "email", "typeTag", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getTypeTag", "CgiBinAuthParams", "Lru/mail/auth/request/QrAuthSendAgentRequest;", "Lru/mail/auth/request/WebAuthNSendAgentRequest;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class CgiBinAuthSendAgentRequest extends OAuthSendAgentRequest {

    @NotNull
    private final String email;

    @NotNull
    private final String typeTag;

    public /* synthetic */ CgiBinAuthSendAgentRequest(Context context, HostProvider hostProvider, String str, String str2, String str3, boolean z10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, hostProvider, str, str2, str3, z10);
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getTypeTag() {
        return this.typeTag;
    }

    private CgiBinAuthSendAgentRequest(Context context, HostProvider hostProvider, String str, String str2, String str3, boolean z10) {
        super(context, new CgiBinAuthParams(context), hostProvider, str, z10);
        this.email = str2;
        this.typeTag = str3;
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0006\u001a\u00020\u00078\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\u00020\u000b8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/request/CgiBinAuthSendAgentRequest$CgiBinAuthParams;", "Lru/mail/auth/request/OAuthSendAgentCommand$Params;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "simple", "", "mobJson", "oauth2", "bindToken", "", "getBindToken$annotations", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CgiBinAuthParams extends OAuthSendAgentCommand.Params {

        @Param(method = HttpMethod.GET, name = "bind_token")
        @NotNull
        private final String bindToken;

        @Param(method = HttpMethod.GET, name = "mob_json")
        private final int mobJson;

        @Param(method = HttpMethod.GET, name = "oauth2")
        private final int oauth2;

        @Param(method = HttpMethod.GET, name = "simple")
        private final int simple;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CgiBinAuthParams(@NotNull Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.simple = 1;
            this.mobJson = 1;
            this.oauth2 = AuthenticatorConfig.getInstance().isOAuthEnabled() ? 1 : 0;
            String bindToken = SocialLoginInfoHolder.getBindToken();
            this.bindToken = bindToken == null ? "" : bindToken;
        }

        private static /* synthetic */ void getBindToken$annotations() {
        }
    }
}
