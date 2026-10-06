package com.vk.superapp.api.internal.oauthrequests;

import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.auth.api.models.AuthResult;
import com.vk.superapp.api.internal.BaseAuthCommand;
import com.vk.superapp.api.states.VkAuthState;
import com.vk.superapp.core.api.models.AuthAnswer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lcom/vk/superapp/api/internal/oauthrequests/AuthByAccessToken;", "Lcom/vk/superapp/api/internal/BaseAuthCommand;", "oauthHost", "", "clientId", "", CommonConstant.KEY_ACCESS_TOKEN, "validateSession", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "onAuthResponse", "Lcom/vk/auth/api/models/AuthResult;", "authAnswer", "Lcom/vk/superapp/core/api/models/AuthAnswer;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuthByAccessToken extends BaseAuthCommand {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthByAccessToken(@NotNull String oauthHost, int i10, @NotNull String accessToken, @Nullable String str) {
        super("https://" + oauthHost + "/auth_by_access_token", i10, false, 4, null);
        Intrinsics.checkNotNullParameter(oauthHost, "oauthHost");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        addParam("access_token", accessToken);
        addParam("validate_session", str);
    }

    @Override // com.vk.superapp.api.internal.BaseAuthCommand
    @NotNull
    public AuthResult onAuthResponse(@NotNull AuthAnswer authAnswer) {
        Intrinsics.checkNotNullParameter(authAnswer, "authAnswer");
        return AuthCommandHelper.toAuthResultOrThrow$default(AuthCommandHelper.INSTANCE, authAnswer, VkAuthState.Companion.empty$default(VkAuthState.INSTANCE, null, 1, null), false, null, 12, null);
    }
}
