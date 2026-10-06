package ru.mail.search.metasearch.data.datasource;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.search.metasearch.data.api.SearchApi;
import ru.mail.search.metasearch.data.api.UrlsBuilder;
import ru.mail.search.metasearch.data.model.TokenData;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/search/metasearch/data/datasource/AuthRemoteDataSource;", "", "urlsBuilder", "Lru/mail/search/metasearch/data/api/UrlsBuilder;", ApiUris.AUTHORITY_API, "Lru/mail/search/metasearch/data/api/SearchApi;", "<init>", "(Lru/mail/search/metasearch/data/api/UrlsBuilder;Lru/mail/search/metasearch/data/api/SearchApi;)V", "convertCloudToken", "Lru/mail/search/metasearch/data/model/TokenData;", "token", "", "parseCloudToken", "result", "metasearch_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthRemoteDataSource {

    @NotNull
    private final SearchApi api;

    @NotNull
    private final UrlsBuilder urlsBuilder;

    public AuthRemoteDataSource(@NotNull UrlsBuilder urlsBuilder, @NotNull SearchApi api) {
        Intrinsics.checkNotNullParameter(urlsBuilder, "urlsBuilder");
        Intrinsics.checkNotNullParameter(api, "api");
        this.urlsBuilder = urlsBuilder;
        this.api = api;
    }

    private final TokenData parseCloudToken(String result) throws JSONException {
        JSONObject jSONObject = new JSONObject(result);
        String string = jSONObject.getString("access_token");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return new TokenData(string, TimeUnit.SECONDS.toMillis(jSONObject.getLong("expires_in")));
    }

    @NotNull
    public final TokenData convertCloudToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        return parseCloudToken(this.api.post(this.urlsBuilder.getO2TokenUrl(), this.urlsBuilder.buildConvertCloudTokenBody(token)));
    }
}
