package com.vk.superapp.api.internal.oauthrequests;

import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.auth.api.models.AuthResult;
import com.vk.dto.common.id.UserId;
import com.vk.superapp.api.internal.BaseAuthCommand;
import com.vk.superapp.core.api.models.AuthAnswer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/api/internal/oauthrequests/AuthExtendProvidedTokenCommand;", "Lcom/vk/superapp/api/internal/BaseAuthCommand;", "oauthHost", "", "clientId", "", CommonConstant.KEY_ACCESS_TOKEN, "providedHash", "providedUuid", "clientDeviceId", "clientExternalDeviceId", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "onAuthResponse", "Lcom/vk/auth/api/models/AuthResult;", "authAnswer", "Lcom/vk/superapp/core/api/models/AuthAnswer;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthExtendProvidedTokenCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthExtendProvidedTokenCommand.kt\ncom/vk/superapp/api/internal/oauthrequests/AuthExtendProvidedTokenCommand\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,72:1\n1#2:73\n*E\n"})
public final class AuthExtendProvidedTokenCommand extends BaseAuthCommand {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthExtendProvidedTokenCommand(@NotNull String oauthHost, int i10, @NotNull String accessToken, @NotNull String providedHash, @NotNull String providedUuid, @NotNull String clientDeviceId, @Nullable String str) {
        super("https://" + oauthHost + "/extend_provided_token", i10, false, 4, null);
        Intrinsics.checkNotNullParameter(oauthHost, "oauthHost");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(providedHash, "providedHash");
        Intrinsics.checkNotNullParameter(providedUuid, "providedUuid");
        Intrinsics.checkNotNullParameter(clientDeviceId, "clientDeviceId");
        addParam("access_token", accessToken);
        addParam("client_id", String.valueOf(i10));
        addParam("provided_hash", providedHash);
        addParam("provided_uuid", providedUuid);
        addParam("client_device_id", clientDeviceId);
        if (str != null) {
            addParam("client_external_device_id", str);
        }
    }

    @Override // com.vk.superapp.api.internal.BaseAuthCommand
    @NotNull
    public AuthResult onAuthResponse(@NotNull AuthAnswer authAnswer) throws VKWebAuthException {
        Intrinsics.checkNotNullParameter(authAnswer, "authAnswer");
        if (authAnswer.getError().length() != 0) {
            throw new VKWebAuthException(200, authAnswer.getError(), authAnswer.getErrorDescription(), null, null, null, 56, null);
        }
        return new AuthResult(authAnswer.getCom.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN java.lang.String(), "", UserId.DEFAULT, false, authAnswer.getExpiresIn(), null, null, null, null, 0, null, 0, null, null, null, 0L, null, null, null, null, null, null, null, 8388584, null);
    }
}
