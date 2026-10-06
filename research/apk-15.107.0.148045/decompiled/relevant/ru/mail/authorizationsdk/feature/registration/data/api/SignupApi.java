package ru.mail.authorizationsdk.feature.registration.data.api;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mail.ui.registration.MailRuRegistrationActivity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\bf\u0018\u00002\u00020\u0001JN\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\b\u001a\u00020\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0006H§@¢\u0006\u0002\u0010\fJp\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0014\u001a\u00020\t2\b\b\u0003\u0010\u0015\u001a\u00020\tH§@¢\u0006\u0002\u0010\u0016J8\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0006H§@¢\u0006\u0002\u0010\u0018¨\u0006\u0019À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/data/api/SignupApi;", "", "getSignupConfirm", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "regTokenCheck", "", "email", "htmlEncoded", "", "xmailFrom", "vkAccessToken", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSignup", "signupToken", "login", "domain", "sex", "birthDay", "name", "extended", "isSentMeAds", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserExists", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SignupApi {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object getSignup$default(SignupApi signupApi, String str, String str2, String str3, String str4, String str5, String str6, boolean z10, boolean z11, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSignup");
        }
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        if ((i10 & 16) != 0) {
            str5 = null;
        }
        if ((i10 & 32) != 0) {
            str6 = null;
        }
        if ((i10 & 64) != 0) {
            z10 = false;
        }
        if ((i10 & 128) != 0) {
            z11 = false;
        }
        return signupApi.getSignup(str, str2, str3, str4, str5, str6, z10, z11, continuation);
    }

    static /* synthetic */ Object getSignupConfirm$default(SignupApi signupApi, String str, String str2, boolean z10, String str3, String str4, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSignupConfirm");
        }
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        if ((i10 & 16) != 0) {
            str4 = null;
        }
        return signupApi.getSignupConfirm(str, str2, z10, str3, str4, continuation);
    }

    static /* synthetic */ Object getUserExists$default(SignupApi signupApi, String str, String str2, String str3, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUserExists");
        }
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        return signupApi.getUserExists(str, str2, str3, continuation);
    }

    @FormUrlEncoded
    @POST("api/v1/user/signup")
    @Nullable
    Object getSignup(@Field(MailRuRegistrationActivity.EXTRA_SIGNUP_TOKEN) @Nullable String str, @Field("login") @Nullable String str2, @Field("domain") @Nullable String str3, @Field("sex") @Nullable String str4, @Field("birthday") @Nullable String str5, @Field("name") @Nullable String str6, @Field("extended") boolean z10, @Field("sent_me_ads") boolean z11, @NotNull Continuation<? super Response<ResponseBody>> continuation);

    @FormUrlEncoded
    @POST("api/v1/user/signup/confirm")
    @Nullable
    Object getSignupConfirm(@Field("reg_token_check") @Nullable String str, @Field("email") @Nullable String str2, @Field("htmlencoded") boolean z10, @Field("from") @Nullable String str3, @Field("vk_access_token") @Nullable String str4, @NotNull Continuation<? super Response<ResponseBody>> continuation);

    @FormUrlEncoded
    @POST("api/v1/user/exists")
    @Nullable
    Object getUserExists(@Field("email") @Nullable String str, @Field("birthday") @Nullable String str2, @Field("name") @Nullable String str3, @NotNull Continuation<? super Response<ResponseBody>> continuation);
}
