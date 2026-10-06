package ru.mail.credentialsexchanger.data.network;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.data.entity.EmailAccountModel;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R0\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020 0\u001fj\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020 `!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006("}, d2 = {"Lru/mail/credentialsexchanger/data/network/GetAuthEmailDataByVKIDRequest;", "Lru/mail/credentialsexchanger/data/network/BaseAsyncRequest;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", "preflightToken", "", "email", "tech", "", "<init>", "(Lokhttp3/OkHttpClient;Ljava/lang/String;Ljava/lang/String;I)V", "ACCOUNT_TYPE_IS_EMPTY_STRING", "LOGIN_IS_EMPTY_STRING", "FIRST_NAME_IS_EMPTY_STRING", "LAST_NAME_IS_EMPTY_STRING", "NAME_IS_EMPTY_STRING", "IMAGE_URL_IS_EMPTY_STRING", "AUTH_URL_IS_EMPTY_STRING", "AUTH_ERROR_IS_EMPTY_STRING", "BLOCK_REASON_IS_EMPTY_STRING", "FAIL_URL_IS_EMPTY_STRING", "VK_ACCESS_TOKEN_IS_NULL_STRING", "VK_ID_IS_NULL_STRING", "requestScheme", "getRequestScheme", "()Ljava/lang/String;", "requestHost", "getRequestHost", "requestPath", "getRequestPath", "requestBody", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRequestBody", "()Ljava/util/HashMap;", "processResponseBody", "Lru/mail/credentialsexchanger/data/network/Response;", "responseBody", "Lorg/json/JSONObject;", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetAuthEmailDataByVKIDRequest extends BaseAsyncRequest {
    public static final int $stable = 8;

    @NotNull
    private final String ACCOUNT_TYPE_IS_EMPTY_STRING;

    @NotNull
    private final String AUTH_ERROR_IS_EMPTY_STRING;

    @NotNull
    private final String AUTH_URL_IS_EMPTY_STRING;

    @NotNull
    private final String BLOCK_REASON_IS_EMPTY_STRING;

    @NotNull
    private final String FAIL_URL_IS_EMPTY_STRING;

    @NotNull
    private final String FIRST_NAME_IS_EMPTY_STRING;

    @NotNull
    private final String IMAGE_URL_IS_EMPTY_STRING;

    @NotNull
    private final String LAST_NAME_IS_EMPTY_STRING;

    @NotNull
    private final String LOGIN_IS_EMPTY_STRING;

    @NotNull
    private final String NAME_IS_EMPTY_STRING;

    @NotNull
    private final String VK_ACCESS_TOKEN_IS_NULL_STRING;

    @NotNull
    private final String VK_ID_IS_NULL_STRING;

    @NotNull
    private final HashMap<String, Object> requestBody;

    @NotNull
    private final String requestHost;

    @NotNull
    private final String requestPath;

    @NotNull
    private final String requestScheme;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetAuthEmailDataByVKIDRequest(@NotNull OkHttpClient client, @NotNull String preflightToken, @NotNull String email, int i10) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(preflightToken, "preflightToken");
        Intrinsics.checkNotNullParameter(email, "email");
        this.ACCOUNT_TYPE_IS_EMPTY_STRING = "type_not_found_in_response_body";
        this.LOGIN_IS_EMPTY_STRING = "login_not_found_in_response_body";
        this.FIRST_NAME_IS_EMPTY_STRING = "first_name_not_found_in_response_body";
        this.LAST_NAME_IS_EMPTY_STRING = "last_name_not_found_in_response_body";
        this.NAME_IS_EMPTY_STRING = "name_not_found_in_response_body";
        this.IMAGE_URL_IS_EMPTY_STRING = "image_url_not_found_in_response_body";
        this.AUTH_URL_IS_EMPTY_STRING = "auth_url_not_found_in_response_body";
        this.AUTH_ERROR_IS_EMPTY_STRING = "auth_error_not_found_in_response_body";
        this.BLOCK_REASON_IS_EMPTY_STRING = "block_reason_not_found_in_response_body";
        this.FAIL_URL_IS_EMPTY_STRING = "fail_url_not_found_in_response_body";
        this.VK_ACCESS_TOKEN_IS_NULL_STRING = "vk_access_token_not_found_in_response_body";
        this.VK_ID_IS_NULL_STRING = "vk_id_not_found_in_response_body";
        this.requestScheme = getUrlProvider().getDefaultScheme();
        this.requestHost = getUrlProvider().getAuthHost();
        this.requestPath = "jsapi/oauth2/vk/preflighted";
        HashMap<String, Object> map = new HashMap<>();
        map.put("preflight_token", preflightToken);
        map.put("email", email);
        map.put("tech", Integer.valueOf(i10));
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
    public Response processResponseBody(@NotNull JSONObject responseBody) throws JSONException {
        String strOptString;
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        JSONArray jSONArray = responseBody.getJSONArray("accounts");
        String strOptString2 = responseBody.optString("vk_token", this.VK_ACCESS_TOKEN_IS_NULL_STRING);
        String strOptString3 = responseBody.optString("vk_user_id", this.VK_ID_IS_NULL_STRING);
        Intrinsics.checkNotNull(strOptString3);
        Long longOrNull = StringsKt.toLongOrNull(strOptString3);
        if (longOrNull == null || Intrinsics.areEqual(strOptString2, this.VK_ACCESS_TOKEN_IS_NULL_STRING)) {
            return new AuthEmailDataResponse.Error("VkAccessToken = " + strOptString2 + ". VkId = " + strOptString3);
        }
        int length = jSONArray.length();
        boolean z10 = false;
        JSONObject jSONObject = null;
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i10);
            if (Intrinsics.areEqual(jSONObjectOptJSONObject.optString("type", this.ACCOUNT_TYPE_IS_EMPTY_STRING), "e")) {
                jSONObject = jSONObjectOptJSONObject;
            }
        }
        if (jSONObject == null) {
            return new AuthEmailDataResponse.Error("Email auth data was not received");
        }
        String strOptString4 = jSONObject.optString("type", this.ACCOUNT_TYPE_IS_EMPTY_STRING);
        String strOptString5 = jSONObject.optString("login", this.LOGIN_IS_EMPTY_STRING);
        String strOptString6 = jSONObject.optString("first_name", this.FIRST_NAME_IS_EMPTY_STRING);
        String strOptString7 = jSONObject.optString("last_name", this.LAST_NAME_IS_EMPTY_STRING);
        String strOptString8 = jSONObject.optString("name", this.NAME_IS_EMPTY_STRING);
        String strOptString9 = jSONObject.optString("image", this.IMAGE_URL_IS_EMPTY_STRING);
        String strOptString10 = jSONObject.optString("auth_url", this.AUTH_URL_IS_EMPTY_STRING);
        if (Intrinsics.areEqual(strOptString10, this.AUTH_URL_IS_EMPTY_STRING)) {
            strOptString = jSONObject.optString("auth_error", this.AUTH_ERROR_IS_EMPTY_STRING);
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String strOptString11 = jSONObject.optString("block_reason", this.BLOCK_REASON_IS_EMPTY_STRING);
            Intrinsics.checkNotNullExpressionValue(strOptString11, "optString(...)");
            String strOptString12 = jSONObject.optString("fail_url", this.FAIL_URL_IS_EMPTY_STRING);
            Intrinsics.checkNotNullExpressionValue(strOptString12, "optString(...)");
            z10 = true;
            str = strOptString12;
            str2 = strOptString11;
        } else {
            strOptString = this.AUTH_ERROR_IS_EMPTY_STRING;
            str2 = this.BLOCK_REASON_IS_EMPTY_STRING;
            str = this.FAIL_URL_IS_EMPTY_STRING;
        }
        String str3 = strOptString;
        boolean z11 = z10;
        Intrinsics.checkNotNull(strOptString4);
        Intrinsics.checkNotNull(strOptString5);
        Intrinsics.checkNotNull(strOptString6);
        Intrinsics.checkNotNull(strOptString7);
        Intrinsics.checkNotNull(strOptString8);
        Intrinsics.checkNotNull(strOptString9);
        Intrinsics.checkNotNull(strOptString10);
        long jLongValue = longOrNull.longValue();
        Intrinsics.checkNotNull(strOptString2);
        return new AuthEmailDataResponse.Success(new EmailAccountModel(strOptString4, strOptString5, strOptString6, strOptString7, strOptString8, strOptString9, strOptString10, z11, str3, str2, str, jLongValue, strOptString2));
    }
}
