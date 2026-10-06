package ru.mail.credentialsexchanger.data.network;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.framework.common.ContainerUtils;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.analytics.CredAnalyticsEvent;
import ru.mail.credentialsexchanger.analytics.CredExchangerAnalytics;
import ru.mail.credentialsexchanger.data.AnalyticsHolder;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\b\u0001\u0018\u0000  2\u00020\u0001:\u0001 B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J4\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0014`\u0015*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u001fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR0\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0014`\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006!"}, d2 = {"Lru/mail/credentialsexchanger/data/network/MailOAuthRequest;", "Lru/mail/credentialsexchanger/data/network/BaseSyncRequest;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", "o2ClientId", "", "authUrl", "<init>", "(Lokhttp3/OkHttpClient;Ljava/lang/String;Ljava/lang/String;)V", "prepareUrl", "Ljava/net/URI;", "requestScheme", "getRequestScheme", "()Ljava/lang/String;", "requestHost", "getRequestHost", "requestPath", "getRequestPath", "requestBody", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRequestBody", "()Ljava/util/HashMap;", "processPrepareBody", "Lorg/json/JSONObject;", "rowBody", "processResponseBody", "Lru/mail/credentialsexchanger/data/network/Response;", "responseBody", "appendUriQuery", "", "Companion", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailOAuthRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailOAuthRequest.kt\nru/mail/credentialsexchanger/data/network/MailOAuthRequest\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,99:1\n1193#2,2:100\n1267#2,4:102\n*S KotlinDebug\n*F\n+ 1 MailOAuthRequest.kt\nru/mail/credentialsexchanger/data/network/MailOAuthRequest\n*L\n71#1:100,2\n71#1:102,4\n*E\n"})
public final class MailOAuthRequest extends BaseSyncRequest {

    @Deprecated
    @NotNull
    public static final String BODY_KEY = "oauth";

    @NotNull
    private final URI prepareUrl;

    @NotNull
    private final HashMap<String, Object> requestBody;

    @NotNull
    private final String requestHost;

    @NotNull
    private final String requestPath;

    @NotNull
    private final String requestScheme;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/credentialsexchanger/data/network/MailOAuthRequest$Companion;", "", "<init>", "()V", "BODY_KEY", "", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MailOAuthRequest(@NotNull OkHttpClient client, @NotNull String o2ClientId, @NotNull String authUrl) {
        super(client);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(o2ClientId, "o2ClientId");
        Intrinsics.checkNotNullParameter(authUrl, "authUrl");
        URI uri = new URI(authUrl);
        this.prepareUrl = uri;
        String scheme = uri.getScheme();
        Intrinsics.checkNotNullExpressionValue(scheme, "getScheme(...)");
        this.requestScheme = scheme;
        String host = uri.getHost();
        Intrinsics.checkNotNullExpressionValue(host, "getHost(...)");
        this.requestHost = host;
        String path = uri.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        this.requestPath = path;
        this.requestBody = appendUriQuery(MapsKt.mutableMapOf(TuplesKt.to("client_id", o2ClientId), TuplesKt.to("oauth2", 1), TuplesKt.to("simple", 1), TuplesKt.to("mob_json", 1)));
    }

    private final HashMap<String, Object> appendUriQuery(Map<String, Object> map) {
        String query = this.prepareUrl.getQuery();
        Intrinsics.checkNotNullExpressionValue(query, "getQuery(...)");
        List listSplit$default = StringsKt.split$default((CharSequence) query, new String[]{ContainerUtils.FIELD_DELIMITER}, false, 0, 6, (Object) null);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10)), 16));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            List listSplit$default2 = StringsKt.split$default((CharSequence) it.next(), new String[]{"="}, false, 0, 6, (Object) null);
            Pair pair = TuplesKt.to((String) listSplit$default2.get(0), (String) listSplit$default2.get(1));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        map.putAll(linkedHashMap);
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.Any>");
        return (HashMap) map;
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
    @Nullable
    public JSONObject processPrepareBody(@NotNull JSONObject rowBody) {
        Intrinsics.checkNotNullParameter(rowBody, "rowBody");
        return rowBody.optJSONObject(BODY_KEY);
    }

    @Override // ru.mail.credentialsexchanger.data.network.BaseRequest
    @NotNull
    public Response processResponseBody(@NotNull JSONObject responseBody) {
        Intrinsics.checkNotNullParameter(responseBody, "responseBody");
        CredExchangerAnalytics analytics = AnalyticsHolder.INSTANCE.getAnalytics();
        if (analytics != null) {
            try {
                analytics.sendEvent$credentials_exchanger_release(new CredAnalyticsEvent.MailOauthTokenResult(CredAnalyticsEvent.Status.OK, null, 2, null));
            } catch (JSONException e10) {
                String message = e10.getMessage();
                if (message == null) {
                    message = "Can't read json body";
                }
                if (analytics != null) {
                    analytics.sendEvent$credentials_exchanger_release(new CredAnalyticsEvent.MailOauthTokenResult(CredAnalyticsEvent.Status.FAIL, message));
                }
                return new MailOAuthResponse.Fail(-1, message);
            }
        }
        long j10 = responseBody.getLong("expires_in");
        String string = responseBody.getString("refresh_token");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = responseBody.getString("access_token");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        return new MailOAuthResponse.Success(j10, string, string2);
    }
}
