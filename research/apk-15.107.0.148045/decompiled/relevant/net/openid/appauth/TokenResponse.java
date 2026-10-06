package net.openid.appauth;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.MailO2AuthStrategy;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes7.dex */
public class TokenResponse {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final Set f77519i = new HashSet(Arrays.asList(MailO2AuthStrategy.EXTRA_TOKEN_TYPE, "access_token", "expires_in", "refresh_token", "id_token", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TokenRequest f77520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f77521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f77522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Long f77523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f77524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f77525f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f77526g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f77527h;

    TokenResponse(TokenRequest tokenRequest, String str, String str2, Long l10, String str3, String str4, String str5, Map map) {
        this.f77520a = tokenRequest;
        this.f77521b = str;
        this.f77522c = str2;
        this.f77523d = l10;
        this.f77524e = str3;
        this.f77525f = str4;
        this.f77526g = str5;
        this.f77527h = map;
    }

    @NonNull
    public static TokenResponse jsonDeserialize(@NonNull JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(Event.Companion.Network.Fail.REQUEST_TAG)) {
            return new TokenResponse(TokenRequest.jsonDeserialize(jSONObject.getJSONObject(Event.Companion.Network.Fail.REQUEST_TAG)), JsonUtil.getStringIfDefined(jSONObject, MailO2AuthStrategy.EXTRA_TOKEN_TYPE), JsonUtil.getStringIfDefined(jSONObject, "access_token"), JsonUtil.getLongIfDefined(jSONObject, "expires_at"), JsonUtil.getStringIfDefined(jSONObject, "id_token"), JsonUtil.getStringIfDefined(jSONObject, "refresh_token"), JsonUtil.getStringIfDefined(jSONObject, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL), JsonUtil.getStringMap(jSONObject, "additionalParameters"));
        }
        throw new IllegalArgumentException("token request not provided and not found in JSON");
    }

    @Nullable
    public Set<String> getScopeSet() {
        return AsciiStringListUtil.stringToSet(this.f77526g);
    }

    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.put(jSONObject, Event.Companion.Network.Fail.REQUEST_TAG, this.f77520a.jsonSerialize());
        JsonUtil.putIfNotNull(jSONObject, MailO2AuthStrategy.EXTRA_TOKEN_TYPE, this.f77521b);
        JsonUtil.putIfNotNull(jSONObject, "access_token", this.f77522c);
        JsonUtil.putIfNotNull(jSONObject, "expires_at", this.f77523d);
        JsonUtil.putIfNotNull(jSONObject, "id_token", this.f77524e);
        JsonUtil.putIfNotNull(jSONObject, "refresh_token", this.f77525f);
        JsonUtil.putIfNotNull(jSONObject, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, this.f77526g);
        JsonUtil.put(jSONObject, "additionalParameters", JsonUtil.mapToJsonObject(this.f77527h));
        return jSONObject;
    }

    public String jsonSerializeString() {
        return jsonSerialize().toString();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private TokenRequest f77528a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f77529b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f77530c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Long f77531d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f77532e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f77533f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f77534g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private Map f77535h;

        public Builder(@NonNull TokenRequest tokenRequest) {
            setRequest(tokenRequest);
            this.f77535h = Collections.EMPTY_MAP;
        }

        Builder a(Long l10, Clock clock) {
            if (l10 == null) {
                this.f77531d = null;
                return this;
            }
            this.f77531d = Long.valueOf(clock.getCurrentTimeMillis() + TimeUnit.SECONDS.toMillis(l10.longValue()));
            return this;
        }

        public TokenResponse build() {
            return new TokenResponse(this.f77528a, this.f77529b, this.f77530c, this.f77531d, this.f77532e, this.f77533f, this.f77534g, this.f77535h);
        }

        @NonNull
        public Builder fromResponseJson(@NonNull JSONObject jSONObject) throws JSONException {
            setTokenType(JsonUtil.getString(jSONObject, MailO2AuthStrategy.EXTRA_TOKEN_TYPE));
            setAccessToken(JsonUtil.getStringIfDefined(jSONObject, "access_token"));
            setAccessTokenExpirationTime(JsonUtil.getLongIfDefined(jSONObject, "expires_at"));
            if (jSONObject.has("expires_in")) {
                setAccessTokenExpiresIn(Long.valueOf(jSONObject.getLong("expires_in")));
            }
            setRefreshToken(JsonUtil.getStringIfDefined(jSONObject, "refresh_token"));
            setIdToken(JsonUtil.getStringIfDefined(jSONObject, "id_token"));
            setScope(JsonUtil.getStringIfDefined(jSONObject, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL));
            setAdditionalParameters(AdditionalParamsProcessor.d(jSONObject, TokenResponse.f77519i));
            return this;
        }

        @NonNull
        public Builder fromResponseJsonString(@NonNull String str) throws JSONException {
            Preconditions.checkNotEmpty(str, "json cannot be null or empty");
            return fromResponseJson(new JSONObject(str));
        }

        @NonNull
        public Builder setAccessToken(@Nullable String str) {
            this.f77530c = Preconditions.checkNullOrNotEmpty(str, "access token cannot be empty if specified");
            return this;
        }

        @NonNull
        public Builder setAccessTokenExpirationTime(@Nullable Long l10) {
            this.f77531d = l10;
            return this;
        }

        @NonNull
        public Builder setAccessTokenExpiresIn(@NonNull Long l10) {
            return a(l10, SystemClock.f77497a);
        }

        @NonNull
        public Builder setAdditionalParameters(@Nullable Map<String, String> map) {
            this.f77535h = AdditionalParamsProcessor.b(map, TokenResponse.f77519i);
            return this;
        }

        public Builder setIdToken(@Nullable String str) {
            this.f77532e = Preconditions.checkNullOrNotEmpty(str, "id token must not be empty if defined");
            return this;
        }

        public Builder setRefreshToken(@Nullable String str) {
            this.f77533f = Preconditions.checkNullOrNotEmpty(str, "refresh token must not be empty if defined");
            return this;
        }

        @NonNull
        public Builder setRequest(@NonNull TokenRequest tokenRequest) {
            this.f77528a = (TokenRequest) Preconditions.checkNotNull(tokenRequest, "request cannot be null");
            return this;
        }

        @NonNull
        public Builder setScope(@Nullable String str) {
            if (TextUtils.isEmpty(str)) {
                this.f77534g = null;
                return this;
            }
            setScopes(str.split(" +"));
            return this;
        }

        @NonNull
        public Builder setScopes(String... strArr) {
            if (strArr == null) {
                strArr = new String[0];
            }
            setScopes(Arrays.asList(strArr));
            return this;
        }

        @NonNull
        public Builder setTokenType(@Nullable String str) {
            this.f77529b = Preconditions.checkNullOrNotEmpty(str, "token type must not be empty if defined");
            return this;
        }

        @NonNull
        public Builder setScopes(@Nullable Iterable<String> iterable) {
            this.f77534g = AsciiStringListUtil.iterableToString(iterable);
            return this;
        }
    }

    @NonNull
    public static TokenResponse jsonDeserialize(@NonNull String str) throws JSONException {
        Preconditions.checkNotEmpty(str, "jsonStr cannot be null or empty");
        return jsonDeserialize(new JSONObject(str));
    }
}
