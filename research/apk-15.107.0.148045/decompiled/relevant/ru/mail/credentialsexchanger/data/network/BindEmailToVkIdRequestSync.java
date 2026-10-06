package ru.mail.credentialsexchanger.data.network;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.silentauth.SilentAuthInfo;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R0\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a0\u0019j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001a`\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"Lru/mail/credentialsexchanger/data/network/BindEmailToVkIdRequestSync;", "Lru/mail/credentialsexchanger/data/network/BaseSyncRequest;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", CommonConstant.KEY_ACCESS_TOKEN, "", "silentToken", SilentAuthInfo.KEY_UUID, "password", "ignoreErrors", "", "isUnattended", "", "<init>", "(Lokhttp3/OkHttpClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZ)V", "VK_ACCESS_TOKEN_IS_NULL_STRING", "VK_LOGIN_IS_NULL_STRING", "requestScheme", "getRequestScheme", "()Ljava/lang/String;", "requestHost", "getRequestHost", "requestPath", "getRequestPath", "requestBody", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRequestBody", "()Ljava/util/HashMap;", "processResponseBody", "Lru/mail/credentialsexchanger/data/network/Response;", "responseBody", "Lorg/json/JSONObject;", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBindEmailToVkIdRequestSync.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BindEmailToVkIdRequestSync.kt\nru/mail/credentialsexchanger/data/network/BindEmailToVkIdRequestSync\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,58:1\n434#2:59\n507#2,5:60\n*S KotlinDebug\n*F\n+ 1 BindEmailToVkIdRequestSync.kt\nru/mail/credentialsexchanger/data/network/BindEmailToVkIdRequestSync\n*L\n40#1:59\n40#1:60,5\n*E\n"})
public final class BindEmailToVkIdRequestSync extends BaseSyncRequest {
    public static final int $stable = 8;

    @NotNull
    private final String VK_ACCESS_TOKEN_IS_NULL_STRING;

    @NotNull
    private final String VK_LOGIN_IS_NULL_STRING;

    @NotNull
    private final HashMap<String, Object> requestBody;

    @NotNull
    private final String requestHost;

    @NotNull
    private final String requestPath;

    @NotNull
    private final String requestScheme;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BindEmailToVkIdRequestSync(@NotNull OkHttpClient client, @NotNull String accessToken, @NotNull String silentToken, @NotNull String uuid, @NotNull String password, int i10, boolean z10) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(silentToken, "silentToken");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(password, "password");
        this.VK_ACCESS_TOKEN_IS_NULL_STRING = "VK access token is null";
        this.VK_LOGIN_IS_NULL_STRING = "VK login is null";
        this.requestScheme = getUrlProvider().getDefaultScheme();
        this.requestHost = getUrlProvider().getAccountMailHost();
        this.requestPath = "jsapi/vk/superapp/silent";
        HashMap<String, Object> map = new HashMap<>();
        map.put("silent_token", silentToken);
        map.put(SilentAuthInfo.KEY_UUID, uuid);
        map.put("password", password);
        map.put("access_token", accessToken);
        map.put("ignore_errors", Integer.valueOf(i10));
        if (z10) {
            map.put("unattended", 1);
        }
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
        String string;
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        try {
            String strOptString = responseBody.optString("vk_access_token", this.VK_ACCESS_TOKEN_IS_NULL_STRING);
            String strOptString2 = responseBody.optString("vk_login", this.VK_LOGIN_IS_NULL_STRING);
            if (Intrinsics.areEqual(strOptString2, this.VK_LOGIN_IS_NULL_STRING)) {
                string = this.VK_LOGIN_IS_NULL_STRING;
            } else {
                Intrinsics.checkNotNull(strOptString2);
                StringBuilder sb2 = new StringBuilder();
                int length = strOptString2.length();
                for (int i10 = 0; i10 < length; i10++) {
                    char cCharAt = strOptString2.charAt(i10);
                    if (Character.isDigit(cCharAt)) {
                        sb2.append(cCharAt);
                    }
                }
                string = sb2.toString();
            }
            JSONObject jSONObjectOptJSONObject = responseBody.optJSONObject("error");
            Integer numValueOf = jSONObjectOptJSONObject != null ? Integer.valueOf(jSONObjectOptJSONObject.optInt("code_number", 0)) : null;
            Intrinsics.checkNotNull(strOptString);
            return new BindEmailResponseSync.Success(strOptString, string, numValueOf);
        } catch (JSONException e10) {
            String message = e10.getMessage();
            if (message == null) {
                message = BindEmailToVkIdRequestAsync.BIND_JSON_EXCEPTION;
            }
            return new BindEmailResponseSync.Fail(message);
        }
    }

    public /* synthetic */ BindEmailToVkIdRequestSync(OkHttpClient okHttpClient, String str, String str2, String str3, String str4, int i10, boolean z10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this(okHttpClient, str, str2, str3, str4, (i11 & 32) != 0 ? 0 : i10, (i11 & 64) != 0 ? false : z10);
    }
}
