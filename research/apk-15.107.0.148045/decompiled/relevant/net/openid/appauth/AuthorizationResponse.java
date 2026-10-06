package net.openid.appauth;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.openid.appauth.internal.UriUtil;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.MailO2AuthStrategy;
import ru.mail.authorizationsdk.feature.ok.data.OKAuthRemoteSource;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes7.dex */
public class AuthorizationResponse extends AuthorizationManagementResponse {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Set f77326j = Collections.unmodifiableSet(new HashSet(Arrays.asList(MailO2AuthStrategy.EXTRA_TOKEN_TYPE, "state", "code", "access_token", "expires_in", "id_token", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AuthorizationRequest f77327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f77328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f77329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f77330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f77331e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Long f77332f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f77333g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f77334h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f77335i;

    /* JADX INFO: compiled from: ProGuard */
    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private AuthorizationRequest f77336a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f77337b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f77338c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f77339d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f77340e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Long f77341f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f77342g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f77343h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private Map f77344i = new LinkedHashMap();

        public Builder(@NonNull AuthorizationRequest authorizationRequest) {
            this.f77336a = (AuthorizationRequest) Preconditions.checkNotNull(authorizationRequest, "authorization request cannot be null");
        }

        Builder a(Uri uri, Clock clock) {
            setState(uri.getQueryParameter("state"));
            setTokenType(uri.getQueryParameter(MailO2AuthStrategy.EXTRA_TOKEN_TYPE));
            setAuthorizationCode(uri.getQueryParameter("code"));
            setAccessToken(uri.getQueryParameter("access_token"));
            setAccessTokenExpiresIn(UriUtil.getLongQueryParameter(uri, "expires_in"), clock);
            setIdToken(uri.getQueryParameter("id_token"));
            setScope(uri.getQueryParameter(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL));
            setAdditionalParameters(AdditionalParamsProcessor.c(uri, AuthorizationResponse.f77326j));
            return this;
        }

        @NonNull
        public AuthorizationResponse build() {
            return new AuthorizationResponse(this.f77336a, this.f77337b, this.f77338c, this.f77339d, this.f77340e, this.f77341f, this.f77342g, this.f77343h, Collections.unmodifiableMap(this.f77344i));
        }

        @NonNull
        public Builder fromUri(@NonNull Uri uri) {
            return a(uri, SystemClock.f77497a);
        }

        @NonNull
        public Builder setAccessToken(@Nullable String str) {
            Preconditions.checkNullOrNotEmpty(str, "accessToken must not be empty");
            this.f77340e = str;
            return this;
        }

        @NonNull
        public Builder setAccessTokenExpirationTime(@Nullable Long l10) {
            this.f77341f = l10;
            return this;
        }

        @NonNull
        public Builder setAccessTokenExpiresIn(@Nullable Long l10) {
            return setAccessTokenExpiresIn(l10, SystemClock.f77497a);
        }

        @NonNull
        public Builder setAdditionalParameters(@Nullable Map<String, String> map) {
            this.f77344i = AdditionalParamsProcessor.b(map, AuthorizationResponse.f77326j);
            return this;
        }

        @NonNull
        public Builder setAuthorizationCode(@Nullable String str) {
            Preconditions.checkNullOrNotEmpty(str, "authorizationCode must not be empty");
            this.f77339d = str;
            return this;
        }

        @NonNull
        public Builder setIdToken(@Nullable String str) {
            Preconditions.checkNullOrNotEmpty(str, "idToken cannot be empty");
            this.f77342g = str;
            return this;
        }

        @NonNull
        public Builder setScope(@Nullable String str) {
            if (TextUtils.isEmpty(str)) {
                this.f77343h = null;
                return this;
            }
            setScopes(str.split(" +"));
            return this;
        }

        @NonNull
        public Builder setScopes(String... strArr) {
            if (strArr == null) {
                this.f77343h = null;
                return this;
            }
            setScopes(Arrays.asList(strArr));
            return this;
        }

        @NonNull
        public Builder setState(@Nullable String str) {
            Preconditions.checkNullOrNotEmpty(str, "state must not be empty");
            this.f77337b = str;
            return this;
        }

        @NonNull
        public Builder setTokenType(@Nullable String str) {
            Preconditions.checkNullOrNotEmpty(str, "tokenType must not be empty");
            this.f77338c = str;
            return this;
        }

        @NonNull
        @VisibleForTesting
        public Builder setAccessTokenExpiresIn(@Nullable Long l10, @NonNull Clock clock) {
            if (l10 == null) {
                this.f77341f = null;
                return this;
            }
            this.f77341f = Long.valueOf(clock.getCurrentTimeMillis() + TimeUnit.SECONDS.toMillis(l10.longValue()));
            return this;
        }

        @NonNull
        public Builder setScopes(@Nullable Iterable<String> iterable) {
            this.f77343h = AsciiStringListUtil.iterableToString(iterable);
            return this;
        }
    }

    @Nullable
    public static AuthorizationResponse fromIntent(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent, "dataIntent must not be null");
        if (!intent.hasExtra("net.openid.appauth.AuthorizationResponse")) {
            return null;
        }
        try {
            return jsonDeserialize(intent.getStringExtra("net.openid.appauth.AuthorizationResponse"));
        } catch (JSONException e10) {
            throw new IllegalArgumentException("Intent contains malformed auth response", e10);
        }
    }

    @NonNull
    public static AuthorizationResponse jsonDeserialize(@NonNull JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(Event.Companion.Network.Fail.REQUEST_TAG)) {
            return new AuthorizationResponse(AuthorizationRequest.jsonDeserialize(jSONObject.getJSONObject(Event.Companion.Network.Fail.REQUEST_TAG)), JsonUtil.getStringIfDefined(jSONObject, "state"), JsonUtil.getStringIfDefined(jSONObject, MailO2AuthStrategy.EXTRA_TOKEN_TYPE), JsonUtil.getStringIfDefined(jSONObject, "code"), JsonUtil.getStringIfDefined(jSONObject, "access_token"), JsonUtil.getLongIfDefined(jSONObject, "expires_at"), JsonUtil.getStringIfDefined(jSONObject, "id_token"), JsonUtil.getStringIfDefined(jSONObject, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL), JsonUtil.getStringMap(jSONObject, "additional_parameters"));
        }
        throw new IllegalArgumentException("authorization request not provided and not found in JSON");
    }

    boolean b(Clock clock) {
        return this.f77332f != null && ((Clock) Preconditions.checkNotNull(clock)).getCurrentTimeMillis() > this.f77332f.longValue();
    }

    @NonNull
    public TokenRequest createTokenExchangeRequest() {
        return createTokenExchangeRequest(Collections.EMPTY_MAP);
    }

    @Nullable
    public Set<String> getScopeSet() {
        return AsciiStringListUtil.stringToSet(this.f77334h);
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    @Nullable
    public String getState() {
        return this.f77328b;
    }

    public boolean hasAccessTokenExpired() {
        return b(SystemClock.f77497a);
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    @NonNull
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.put(jSONObject, Event.Companion.Network.Fail.REQUEST_TAG, this.f77327a.jsonSerialize());
        JsonUtil.putIfNotNull(jSONObject, "state", this.f77328b);
        JsonUtil.putIfNotNull(jSONObject, MailO2AuthStrategy.EXTRA_TOKEN_TYPE, this.f77329c);
        JsonUtil.putIfNotNull(jSONObject, "code", this.f77330d);
        JsonUtil.putIfNotNull(jSONObject, "access_token", this.f77331e);
        JsonUtil.putIfNotNull(jSONObject, "expires_at", this.f77332f);
        JsonUtil.putIfNotNull(jSONObject, "id_token", this.f77333g);
        JsonUtil.putIfNotNull(jSONObject, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, this.f77334h);
        JsonUtil.put(jSONObject, "additional_parameters", JsonUtil.mapToJsonObject(this.f77335i));
        return jSONObject;
    }

    @Override // net.openid.appauth.AuthorizationManagementResponse
    @NonNull
    public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtra("net.openid.appauth.AuthorizationResponse", jsonSerializeString());
        return intent;
    }

    private AuthorizationResponse(AuthorizationRequest authorizationRequest, String str, String str2, String str3, String str4, Long l10, String str5, String str6, Map map) {
        this.f77327a = authorizationRequest;
        this.f77328b = str;
        this.f77329c = str2;
        this.f77330d = str3;
        this.f77331e = str4;
        this.f77332f = l10;
        this.f77333g = str5;
        this.f77334h = str6;
        this.f77335i = map;
    }

    @NonNull
    public TokenRequest createTokenExchangeRequest(@NonNull Map<String, String> map) {
        Preconditions.checkNotNull(map, "additionalExchangeParameters cannot be null");
        if (this.f77330d == null) {
            throw new IllegalStateException("authorizationCode not available for exchange request");
        }
        AuthorizationRequest authorizationRequest = this.f77327a;
        return new TokenRequest.Builder(authorizationRequest.f77290a, authorizationRequest.f77291b).setGrantType(OKAuthRemoteSource.AUTH_GRANT_TYPE).setRedirectUri(this.f77327a.f77297h).setCodeVerifier(this.f77327a.f77301l).setAuthorizationCode(this.f77330d).setAdditionalParameters(map).setNonce(this.f77327a.f77300k).build();
    }

    @NonNull
    public static AuthorizationResponse jsonDeserialize(@NonNull String str) throws JSONException {
        return jsonDeserialize(new JSONObject(str));
    }
}
