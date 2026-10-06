package net.openid.appauth;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes7.dex */
public class RegistrationResponse {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Set f77478j = new HashSet(Arrays.asList("client_id", SharedKt.PARAM_CLIENT_SECRET, "client_secret_expires_at", "registration_access_token", "registration_client_uri", "client_id_issued_at", "token_endpoint_auth_method"));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RegistrationRequest f77479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f77480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Long f77481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f77482d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Long f77483e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f77484f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Uri f77485g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f77486h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f77487i;

    /* JADX INFO: compiled from: ProGuard */
    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private RegistrationRequest f77488a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f77489b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Long f77490c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f77491d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Long f77492e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f77493f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Uri f77494g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f77495h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private Map f77496i = Collections.EMPTY_MAP;

        public Builder(@NonNull RegistrationRequest registrationRequest) {
            setRequest(registrationRequest);
        }

        public RegistrationResponse build() {
            return new RegistrationResponse(this.f77488a, this.f77489b, this.f77490c, this.f77491d, this.f77492e, this.f77493f, this.f77494g, this.f77495h, this.f77496i);
        }

        @NonNull
        public Builder fromResponseJson(@NonNull JSONObject jSONObject) throws MissingArgumentException, JSONException {
            setClientId(JsonUtil.getString(jSONObject, "client_id"));
            setClientIdIssuedAt(JsonUtil.getLongIfDefined(jSONObject, "client_id_issued_at"));
            if (jSONObject.has(SharedKt.PARAM_CLIENT_SECRET)) {
                if (!jSONObject.has("client_secret_expires_at")) {
                    throw new MissingArgumentException("client_secret_expires_at");
                }
                setClientSecret(jSONObject.getString(SharedKt.PARAM_CLIENT_SECRET));
                setClientSecretExpiresAt(Long.valueOf(jSONObject.getLong("client_secret_expires_at")));
            }
            if (jSONObject.has("registration_access_token") != jSONObject.has("registration_client_uri")) {
                throw new MissingArgumentException(jSONObject.has("registration_access_token") ? "registration_client_uri" : "registration_access_token");
            }
            setRegistrationAccessToken(JsonUtil.getStringIfDefined(jSONObject, "registration_access_token"));
            setRegistrationClientUri(JsonUtil.getUriIfDefined(jSONObject, "registration_client_uri"));
            setTokenEndpointAuthMethod(JsonUtil.getStringIfDefined(jSONObject, "token_endpoint_auth_method"));
            setAdditionalParameters(AdditionalParamsProcessor.d(jSONObject, RegistrationResponse.f77478j));
            return this;
        }

        @NonNull
        public Builder fromResponseJsonString(@NonNull String str) throws MissingArgumentException, JSONException {
            Preconditions.checkNotEmpty(str, "json cannot be null or empty");
            return fromResponseJson(new JSONObject(str));
        }

        public Builder setAdditionalParameters(Map<String, String> map) {
            this.f77496i = AdditionalParamsProcessor.b(map, RegistrationResponse.f77478j);
            return this;
        }

        public Builder setClientId(@NonNull String str) {
            Preconditions.checkNotEmpty(str, "client ID cannot be null or empty");
            this.f77489b = str;
            return this;
        }

        public Builder setClientIdIssuedAt(@Nullable Long l10) {
            this.f77490c = l10;
            return this;
        }

        public Builder setClientSecret(@Nullable String str) {
            this.f77491d = str;
            return this;
        }

        public Builder setClientSecretExpiresAt(@Nullable Long l10) {
            this.f77492e = l10;
            return this;
        }

        public Builder setRegistrationAccessToken(@Nullable String str) {
            this.f77493f = str;
            return this;
        }

        public Builder setRegistrationClientUri(@Nullable Uri uri) {
            this.f77494g = uri;
            return this;
        }

        @NonNull
        public Builder setRequest(@NonNull RegistrationRequest registrationRequest) {
            this.f77488a = (RegistrationRequest) Preconditions.checkNotNull(registrationRequest, "request cannot be null");
            return this;
        }

        public Builder setTokenEndpointAuthMethod(@Nullable String str) {
            this.f77495h = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MissingArgumentException extends Exception {
        private String mMissingField;

        public MissingArgumentException(String str) {
            super("Missing mandatory registration field: " + str);
            this.mMissingField = str;
        }

        public String getMissingField() {
            return this.mMissingField;
        }
    }

    @NonNull
    public static RegistrationResponse fromJson(@NonNull RegistrationRequest registrationRequest, @NonNull String str) throws MissingArgumentException, JSONException {
        Preconditions.checkNotEmpty(str, "jsonStr cannot be null or empty");
        return fromJson(registrationRequest, new JSONObject(str));
    }

    public static RegistrationResponse jsonDeserialize(@NonNull JSONObject jSONObject) throws JSONException {
        Preconditions.checkNotNull(jSONObject, "json cannot be null");
        if (jSONObject.has(Event.Companion.Network.Fail.REQUEST_TAG)) {
            return new RegistrationResponse(RegistrationRequest.jsonDeserialize(jSONObject.getJSONObject(Event.Companion.Network.Fail.REQUEST_TAG)), JsonUtil.getString(jSONObject, "client_id"), JsonUtil.getLongIfDefined(jSONObject, "client_id_issued_at"), JsonUtil.getStringIfDefined(jSONObject, SharedKt.PARAM_CLIENT_SECRET), JsonUtil.getLongIfDefined(jSONObject, "client_secret_expires_at"), JsonUtil.getStringIfDefined(jSONObject, "registration_access_token"), JsonUtil.getUriIfDefined(jSONObject, "registration_client_uri"), JsonUtil.getStringIfDefined(jSONObject, "token_endpoint_auth_method"), JsonUtil.getStringMap(jSONObject, "additionalParameters"));
        }
        throw new IllegalArgumentException("registration request not found in JSON");
    }

    boolean b(Clock clock) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(((Clock) Preconditions.checkNotNull(clock)).getCurrentTimeMillis());
        Long l10 = this.f77483e;
        return l10 != null && seconds > l10.longValue();
    }

    public boolean hasClientSecretExpired() {
        return b(SystemClock.f77497a);
    }

    @NonNull
    public JSONObject jsonSerialize() {
        JSONObject jSONObject = new JSONObject();
        JsonUtil.put(jSONObject, Event.Companion.Network.Fail.REQUEST_TAG, this.f77479a.jsonSerialize());
        JsonUtil.put(jSONObject, "client_id", this.f77480b);
        JsonUtil.putIfNotNull(jSONObject, "client_id_issued_at", this.f77481c);
        JsonUtil.putIfNotNull(jSONObject, SharedKt.PARAM_CLIENT_SECRET, this.f77482d);
        JsonUtil.putIfNotNull(jSONObject, "client_secret_expires_at", this.f77483e);
        JsonUtil.putIfNotNull(jSONObject, "registration_access_token", this.f77484f);
        JsonUtil.putIfNotNull(jSONObject, "registration_client_uri", this.f77485g);
        JsonUtil.putIfNotNull(jSONObject, "token_endpoint_auth_method", this.f77486h);
        JsonUtil.put(jSONObject, "additionalParameters", JsonUtil.mapToJsonObject(this.f77487i));
        return jSONObject;
    }

    @NonNull
    public String jsonSerializeString() {
        return jsonSerialize().toString();
    }

    private RegistrationResponse(RegistrationRequest registrationRequest, String str, Long l10, String str2, Long l11, String str3, Uri uri, String str4, Map map) {
        this.f77479a = registrationRequest;
        this.f77480b = str;
        this.f77481c = l10;
        this.f77482d = str2;
        this.f77483e = l11;
        this.f77484f = str3;
        this.f77485g = uri;
        this.f77486h = str4;
        this.f77487i = map;
    }

    @NonNull
    public static RegistrationResponse fromJson(@NonNull RegistrationRequest registrationRequest, @NonNull JSONObject jSONObject) throws MissingArgumentException, JSONException {
        Preconditions.checkNotNull(registrationRequest, "registration request cannot be null");
        return new Builder(registrationRequest).fromResponseJson(jSONObject).build();
    }

    @NonNull
    public static RegistrationResponse jsonDeserialize(@NonNull String str) throws JSONException {
        Preconditions.checkNotEmpty(str, "jsonStr cannot be null or empty");
        return jsonDeserialize(new JSONObject(str));
    }
}
