package com.vk.superapp.api.internal.oauthrequests;

import com.vk.auth.api.models.AuthResult;
import com.vk.superapp.api.internal.BaseAuthCommand;
import com.vk.superapp.api.states.VkAuthState;
import com.vk.superapp.core.api.models.AuthAnswer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/internal/oauthrequests/AuthGetAccessToken;", "Lcom/vk/superapp/api/internal/BaseAuthCommand;", "oauthHost", "", "clientId", "", "codeVerifier", "code", "redirectUri", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "onAuthResponse", "Lcom/vk/auth/api/models/AuthResult;", "authAnswer", "Lcom/vk/superapp/core/api/models/AuthAnswer;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuthGetAccessToken extends BaseAuthCommand {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthGetAccessToken(@NotNull String oauthHost, int i10, @NotNull String codeVerifier, @NotNull String code, @NotNull String redirectUri) {
        super("https://" + oauthHost + "/access_token", i10, true);
        Intrinsics.checkNotNullParameter(oauthHost, "oauthHost");
        Intrinsics.checkNotNullParameter(codeVerifier, "codeVerifier");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(redirectUri, "redirectUri");
        addParam("code_verifier", codeVerifier);
        addParam("code", code);
        addParam("redirect_uri", redirectUri);
    }

    @Override // com.vk.superapp.api.internal.BaseAuthCommand
    @NotNull
    public AuthResult onAuthResponse(@NotNull AuthAnswer authAnswer) {
        Intrinsics.checkNotNullParameter(authAnswer, "authAnswer");
        return AuthCommandHelper.toAuthResultOrThrow$default(AuthCommandHelper.INSTANCE, authAnswer, VkAuthState.Companion.empty$default(VkAuthState.INSTANCE, null, 1, null), false, null, 12, null);
    }
}
