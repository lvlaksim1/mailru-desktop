package ru.mail.logic.cmd.socialbind;

import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.apache.http.cookie.SM;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel;
import ru.mail.network.retrofit.ApiResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000 \f2\u00020\u0001:\u0001\fJF\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u000b¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lru/mail/logic/cmd/socialbind/SocialBindAddApi;", "", "addSocialBind", "Lru/mail/network/retrofit/ApiResult;", CommonConstant.KEY_ACCESS_TOKEN, "", "ludwigToken", "preflightToken", "isResetPassword", "", "cookie", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SocialBindAddApi {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/logic/cmd/socialbind/SocialBindAddApi$Companion;", "", "<init>", "()V", "ACCESS_TOKEN", "", "LUDWIG_TOKEN", "PREFLIGHT_TOKEN", "RESET_PASSWORD", "COOKIE", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final String ACCESS_TOKEN = "access_token";

        @NotNull
        private static final String COOKIE = "Cookie";

        @NotNull
        private static final String LUDWIG_TOKEN = "ludwig_token";

        @NotNull
        private static final String PREFLIGHT_TOKEN = "preflight_token";

        @NotNull
        private static final String RESET_PASSWORD = "reset_password";

        private Companion() {
        }
    }

    @FormUrlEncoded
    @POST("api/v1/user/social/bind/add")
    @Nullable
    Object addSocialBind(@Field("access_token") @NotNull String str, @Field(WebCaptchaComposeViewModel.LUDWIG_TOKEN) @NotNull String str2, @Field("preflight_token") @NotNull String str3, @Field("reset_password") boolean z10, @Header(SM.COOKIE) @NotNull String str4, @NotNull Continuation<? super ApiResult<Object>> continuation);
}
