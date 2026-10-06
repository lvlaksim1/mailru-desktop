package ru.mail.authorizationsdk.data.externalaccount;

import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.data.model.MailOAuthCredentials;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u000bJ@\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u000eJ@\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u000eJT\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u000bJJ\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0012\u001a\u00020\u00062\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u0014J^\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\b\b\u0001\u0010\u0016\u001a\u00020\u00062\b\b\u0001\u0010\u0017\u001a\u00020\u00062\b\b\u0001\u0010\u0018\u001a\u00020\u00062\u0014\b\u0001\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\tH§@¢\u0006\u0002\u0010\u0019¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/data/externalaccount/ExternalAccMailApi;", "", "exchangeExternalToMailTokens", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;", "dynamicAuthPath", "", "clientId", "queryParams", "", "bodyParams", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGoogleAccMailAuthUrl", "", "(Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getYandexAccMailAuthUrl", "getOutlookAccMailAuthUrl", "email", "redirectUri", "getYahooAccMailAuthUrl", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOKAccMailAuthUrl", "o2Client", CommonConstant.KEY_ACCESS_TOKEN, "expires", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ExternalAccMailApi {
    @FormUrlEncoded
    @POST("{path}")
    @Nullable
    Object exchangeExternalToMailTokens(@Path(encoded = true, value = "path") @NotNull String str, @Field("client_id") @Nullable String str2, @QueryMap @NotNull Map<String, String> map, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<MailOAuthCredentials>> continuation);

    @FormUrlEncoded
    @POST("oauth2_google_token")
    @Nullable
    Object getGoogleAccMailAuthUrl(@QueryMap @NotNull Map<String, String> map, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<Unit>> continuation);

    @FormUrlEncoded
    @POST("cgi-bin/oauth2_ok_token")
    @Nullable
    Object getOKAccMailAuthUrl(@QueryMap @NotNull Map<String, String> map, @Field("o2client") @NotNull String str, @Field("access_token") @NotNull String str2, @Field("expires") @NotNull String str3, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<Unit>> continuation);

    @FormUrlEncoded
    @POST("oauth2_outlook_token")
    @Nullable
    Object getOutlookAccMailAuthUrl(@Field("login") @NotNull String str, @Field("redirect_uri") @NotNull String str2, @QueryMap @NotNull Map<String, String> map, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<Unit>> continuation);

    @FormUrlEncoded
    @POST("oauth2_yahoo_token")
    @Nullable
    Object getYahooAccMailAuthUrl(@Field("redirect_uri") @NotNull String str, @QueryMap @NotNull Map<String, String> map, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<Unit>> continuation);

    @FormUrlEncoded
    @POST("oauth2_yandex_token")
    @Nullable
    Object getYandexAccMailAuthUrl(@QueryMap @NotNull Map<String, String> map, @FieldMap @NotNull Map<String, String> map2, @NotNull Continuation<? super NetResponse<Unit>> continuation);
}
