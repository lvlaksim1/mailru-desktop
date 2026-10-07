package ru.mail.authorizationsdk.feature.customserver.data;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.serialization.json.JsonObject;
import org.apache.http.cookie.SM;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.feature.customserver.data.model.CustomServerResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0001\u0010\f\u001a\u00020\nH§@¢\u0006\u0002\u0010\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/data/CustomServerMailApi;", "", "customServerRegisterAccount", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/feature/customserver/data/model/CustomServerResult;", "actMode", "", "captchaCookie", "captchaCode", "collect", "Lkotlinx/serialization/json/JsonObject;", "user", "smtp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/json/JsonObject;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CustomServerMailApi {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object customServerRegisterAccount$default(CustomServerMailApi customServerMailApi, String str, String str2, String str3, JsonObject jsonObject, JsonObject jsonObject2, JsonObject jsonObject3, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: customServerRegisterAccount");
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
        return customServerMailApi.customServerRegisterAccount(str, str2, str3, jsonObject, jsonObject2, jsonObject3, continuation);
    }

    @FormUrlEncoded
    @POST("api/v1/user/signup/external/unknown")
    @Nullable
    Object customServerRegisterAccount(@Nullable @Query(AccountInfoUtilsKt.PARAM_KEY_ACT_MODE) String str, @Header(SM.COOKIE) @Nullable String str2, @Field("code") @Nullable String str3, @Field("collect") @NotNull JsonObject jsonObject, @Field("user") @NotNull JsonObject jsonObject2, @Field("smtp") @NotNull JsonObject jsonObject3, @NotNull Continuation<? super NetResponse<CustomServerResult>> continuation);
}
