package ru.mail.credentialsexchanger.data.network;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.analytics.CredAnalyticsEvent;
import ru.mail.credentialsexchanger.analytics.CredExchangerAnalytics;
import ru.mail.credentialsexchanger.data.AnalyticsHolder;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR0\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0011`\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0019"}, d2 = {"Lru/mail/credentialsexchanger/data/network/GetVkAuthRequest;", "Lru/mail/credentialsexchanger/data/network/BaseAsyncRequest;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", CommonConstant.KEY_ACCESS_TOKEN, "", "<init>", "(Lokhttp3/OkHttpClient;Ljava/lang/String;)V", "requestScheme", "getRequestScheme", "()Ljava/lang/String;", "requestHost", "getRequestHost", "requestPath", "getRequestPath", "requestBody", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRequestBody", "()Ljava/util/HashMap;", "processResponseBody", "Lru/mail/credentialsexchanger/data/network/Response;", "responseBody", "Lorg/json/JSONObject;", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetVkAuthRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetVkAuthRequest.kt\nru/mail/credentialsexchanger/data/network/GetVkAuthRequest\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,41:1\n434#2:42\n507#2,5:43\n*S KotlinDebug\n*F\n+ 1 GetVkAuthRequest.kt\nru/mail/credentialsexchanger/data/network/GetVkAuthRequest\n*L\n26#1:42\n26#1:43,5\n*E\n"})
public final class GetVkAuthRequest extends BaseAsyncRequest {
    public static final int $stable = 8;

    @NotNull
    private final HashMap<String, Object> requestBody;

    @NotNull
    private final String requestHost;

    @NotNull
    private final String requestPath;

    @NotNull
    private final String requestScheme;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetVkAuthRequest(@NotNull OkHttpClient client, @NotNull String accessToken) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        this.requestScheme = getUrlProvider().getDefaultScheme();
        this.requestHost = getUrlProvider().getAccountMailHost();
        this.requestPath = "jsapi/vk/superapp";
        HashMap<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        this.requestBody = map;
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public HashMap<String, Object> getRequestBody() {
        return this.requestBody;
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public String getRequestHost() {
        return this.requestHost;
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public String getRequestPath() {
        return this.requestPath;
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public String getRequestScheme() {
        return this.requestScheme;
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public Response processResponseBody(@NotNull JSONObject responseBody) throws IOException {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        CredExchangerAnalytics analytics = AnalyticsHolder.INSTANCE.getAnalytics();
        try {
            String string = responseBody.getString("vk_access_token");
            String string2 = responseBody.getString("vk_login");
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            StringBuilder sb2 = new StringBuilder();
            int length = string2.length();
            for (int i10 = 0; i10 < length; i10++) {
                char cCharAt = string2.charAt(i10);
                if (Character.isDigit(cCharAt)) {
                    sb2.append(cCharAt);
                }
            }
            String string3 = sb2.toString();
            if (analytics != null) {
                analytics.sendEvent$credentials_exchanger_release(new CredAnalyticsEvent.GetVkAuthRequestResult(CredAnalyticsEvent.Status.OK));
            }
            Intrinsics.checkNotNull(string);
            return new VkAuthResponse.SuccessLinkExists(string, string3);
        } catch (JSONException unused) {
            if (analytics != null) {
                analytics.sendEvent$credentials_exchanger_release(new CredAnalyticsEvent.GetVkAuthRequestResult(CredAnalyticsEvent.Status.FAIL));
            }
            return new VkAuthResponse.SuccessLinkNotExists();
        }
    }
}
