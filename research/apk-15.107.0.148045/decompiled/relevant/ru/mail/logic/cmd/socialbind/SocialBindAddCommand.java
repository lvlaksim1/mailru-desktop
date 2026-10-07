package ru.mail.logic.cmd.socialbind;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.serverapi.retrofit.MailApiCommand;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0096@¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/logic/cmd/socialbind/SocialBindAddCommand;", "Lru/mail/serverapi/retrofit/MailApiCommand;", "Lru/mail/logic/cmd/socialbind/SocialBindAddCommand$Params;", "", "", ApiUris.AUTHORITY_API, "Lru/mail/logic/cmd/socialbind/SocialBindAddApi;", "params", "<init>", "(Lru/mail/logic/cmd/socialbind/SocialBindAddApi;Lru/mail/logic/cmd/socialbind/SocialBindAddCommand$Params;)V", "executeRequest", "Lru/mail/network/retrofit/ApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformDataToDomainModel", "result", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SocialBindAddCommand extends MailApiCommand<Params, Object, Boolean> {
    public static final int $stable = 8;

    @NotNull
    private final SocialBindAddApi api;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0011"}, d2 = {"Lru/mail/logic/cmd/socialbind/SocialBindAddCommand$Params;", "", CommonConstant.KEY_ACCESS_TOKEN, "", "ludwigToken", "preflightToken", "isResetPassword", "", "cookie", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getLudwigToken", "getPreflightToken", "()Z", "getCookie", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params {
        public static final int $stable = 0;

        @NotNull
        private final String accessToken;

        @NotNull
        private final String cookie;
        private final boolean isResetPassword;

        @NotNull
        private final String ludwigToken;

        @NotNull
        private final String preflightToken;

        public Params(@NotNull String accessToken, @NotNull String ludwigToken, @NotNull String preflightToken, boolean z10, @NotNull String cookie) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            Intrinsics.checkNotNullParameter(preflightToken, "preflightToken");
            Intrinsics.checkNotNullParameter(cookie, "cookie");
            this.accessToken = accessToken;
            this.ludwigToken = ludwigToken;
            this.preflightToken = preflightToken;
            this.isResetPassword = z10;
            this.cookie = cookie;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getCookie() {
            return this.cookie;
        }

        @NotNull
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        @NotNull
        public final String getPreflightToken() {
            return this.preflightToken;
        }

        /* JADX INFO: renamed from: isResetPassword, reason: from getter */
        public final boolean getIsResetPassword() {
            return this.isResetPassword;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SocialBindAddCommand(@NotNull SocialBindAddApi api, @NotNull Params params) {
        super(params);
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(params, "params");
        this.api = api;
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @Nullable
    public Object executeRequest(@NotNull Continuation<? super ApiResult<Object>> continuation) {
        return this.api.addSocialBind(getParams().getAccessToken(), getParams().getLudwigToken(), getParams().getPreflightToken(), getParams().getIsResetPassword(), "act=" + getParams().getCookie(), continuation);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @NotNull
    public Boolean transformDataToDomainModel(@NotNull Object result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return Boolean.TRUE;
    }
}
