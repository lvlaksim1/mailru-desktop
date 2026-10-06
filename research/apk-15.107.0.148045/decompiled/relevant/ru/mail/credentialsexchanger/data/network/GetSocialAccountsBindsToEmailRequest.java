package ru.mail.credentialsexchanger.data.network;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.data.entity.SocialAccount;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\t\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR0\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00150\u0014j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0015`\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lru/mail/credentialsexchanger/data/network/GetSocialAccountsBindsToEmailRequest;", "Lru/mail/credentialsexchanger/data/network/BaseAsyncRequest;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", CommonConstant.KEY_ACCESS_TOKEN, "", "email", "<init>", "(Lokhttp3/OkHttpClient;Ljava/lang/String;Ljava/lang/String;)V", "LOGIN_IS_NULL_STRING", "FIRST_NAME_IS_NULL_STRING", "LAST_NAME_IS_NULL_STRING", "requestScheme", "getRequestScheme", "()Ljava/lang/String;", "requestHost", "getRequestHost", "requestPath", "getRequestPath", "requestBody", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRequestBody", "()Ljava/util/HashMap;", "processResponseBody", "Lru/mail/credentialsexchanger/data/network/Response;", "responseBody", "Lorg/json/JSONObject;", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetSocialAccountsBindsToEmailRequest extends BaseAsyncRequest {
    public static final int $stable = 8;

    @NotNull
    private final String FIRST_NAME_IS_NULL_STRING;

    @NotNull
    private final String LAST_NAME_IS_NULL_STRING;

    @NotNull
    private final String LOGIN_IS_NULL_STRING;

    @NotNull
    private final HashMap<String, Object> requestBody;

    @NotNull
    private final String requestHost;

    @NotNull
    private final String requestPath;

    @NotNull
    private final String requestScheme;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetSocialAccountsBindsToEmailRequest(@NotNull OkHttpClient client, @NotNull String accessToken, @NotNull String email) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(email, "email");
        this.LOGIN_IS_NULL_STRING = "Login of social account is null";
        this.FIRST_NAME_IS_NULL_STRING = "First name of social account is null";
        this.LAST_NAME_IS_NULL_STRING = "Last name of social account is null";
        this.requestScheme = getUrlProvider().getDefaultScheme();
        this.requestHost = getUrlProvider().getAltAjMailHost();
        this.requestPath = "api/v1/user/social/bind/list";
        HashMap<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("email", email);
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
    public Response processResponseBody(@NotNull JSONObject responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        try {
            JSONArray jSONArray = responseBody.getJSONArray("accounts");
            Intrinsics.checkNotNullExpressionValue(jSONArray, "getJSONArray(...)");
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i10 = 0; i10 < length; i10++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i10);
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                    String strOptString = jSONObject.optString("login", this.LOGIN_IS_NULL_STRING);
                    String strOptString2 = jSONObject.optString("first_name", this.FIRST_NAME_IS_NULL_STRING);
                    String strOptString3 = jSONObject.optString("last_name", this.LAST_NAME_IS_NULL_STRING);
                    Intrinsics.checkNotNull(strOptString);
                    Intrinsics.checkNotNull(strOptString2);
                    Intrinsics.checkNotNull(strOptString3);
                    arrayList.add(new SocialAccount(strOptString, strOptString2, strOptString3));
                }
            }
            return new GetAccountsBoundToEmailResponse.Success(arrayList);
        } catch (JSONException e10) {
            return new GetAccountsBoundToEmailResponse.Fail(e10.toString());
        }
    }
}
