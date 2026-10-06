package ru.mail.search.metasearch.data.api;

import android.content.Context;
import android.net.Uri;
import com.vk.lists.PaginationHelper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import ru.mail.cloud.stories.Constants;
import ru.mail.data.entities.Collector;
import ru.mail.search.metasearch.R;
import ru.mail.search.metasearch.data.model.MailFiltersData;
import ru.mail.search.metasearch.data.model.RequestParams;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u0016\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007J\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0007J \u0010\u001b\u001a\u00020\u00072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u001d2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0002J\f\u0010!\u001a\u00020\"*\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lru/mail/search/metasearch/data/api/UrlsBuilder;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "buildHistoryUrl", "", "buildDeleteHistoryUrl", "query", "buildSuggestsUrl", "requestParams", "Lru/mail/search/metasearch/data/model/RequestParams;", "buildSearchResultFullUrl", "buildContactsUrl", "buildMailUrl", "buildSitesUrl", "buildCloudUrl", "buildAvatarUrl", "email", "formatRelativeReference", "relativeRef", "sourceUrl", "getO2TokenUrl", "buildConvertCloudTokenBody", "Lokhttp3/RequestBody;", "token", "buildUrl", "paths", "", "buildMailFiltersJson", "mailFiltersData", "Lru/mail/search/metasearch/data/model/MailFiltersData;", "toSeconds", "", "Companion", "metasearch_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUrlsBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UrlsBuilder.kt\nru/mail/search/metasearch/data/api/UrlsBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,173:1\n1#2:174\n1869#3,2:175\n*S KotlinDebug\n*F\n+ 1 UrlsBuilder.kt\nru/mail/search/metasearch/data/api/UrlsBuilder\n*L\n106#1:175,2\n*E\n"})
public final class UrlsBuilder {

    @NotNull
    private static final String AVATARS_HOST = "https://filin.mail.ru/pic";
    private static final int DEFAULT_AVATAR_SIZE = 180;

    @NotNull
    private static final String HTTP_SCHEME = "http";

    @NotNull
    public static final String O2_TOKEN_HOST = "https://o2.mail.ru/token";

    @NotNull
    private static final String PATH_FULL = "full";

    @NotNull
    private static final String PATH_SEARCH = "search";

    @NotNull
    private static final String SEARCH_API_HOST = "https://go.mail.ru/api/v1/go";

    @NotNull
    public static final String SUGGESTS_GO_HOST = "https://suggests.go.mail.ru";

    @NotNull
    private static final String TAG = "UrlsBuilder";

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MailFiltersData.SearchType.values().length];
            try {
                iArr[MailFiltersData.SearchType.FROM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MailFiltersData.SearchType.TO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MailFiltersData.SearchType.SUBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MailFiltersData.SearchType.ALL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public UrlsBuilder(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final String buildMailFiltersJson(MailFiltersData mailFiltersData) {
        HashMap map = new HashMap();
        JsonPrimitive JsonPrimitive = JsonElementKt.JsonPrimitive(mailFiltersData.getQuery());
        int i10 = WhenMappings.$EnumSwitchMapping$0[mailFiltersData.getSearchType().ordinal()];
        if (i10 == 1 || i10 == 2) {
            map.put("correspondents", new JsonObject(MapsKt.mapOf(TuplesKt.to(mailFiltersData.getSearchType() == MailFiltersData.SearchType.FROM ? "from" : "to", JsonPrimitive))));
        } else if (i10 == 3) {
            map.put("subject", JsonPrimitive);
        } else {
            if (i10 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            map.put("query", JsonPrimitive);
        }
        if (mailFiltersData.isWithAttachments() || mailFiltersData.isFlagged() || mailFiltersData.isUnread()) {
            HashMap map2 = new HashMap();
            JsonPrimitive JsonPrimitive2 = JsonElementKt.JsonPrimitive(Boolean.TRUE);
            if (mailFiltersData.isWithAttachments()) {
                map2.put("attach", JsonPrimitive2);
            }
            if (mailFiltersData.isFlagged()) {
                map2.put(AnalyticsUtilsExtKt.FILTER_FLAGGED, JsonPrimitive2);
            }
            if (mailFiltersData.isUnread()) {
                map2.put(AnalyticsUtilsExtKt.FILTER_UNREAD, JsonPrimitive2);
            }
            map.put(Collector.FLAGS, new JsonObject(map2));
        }
        if (mailFiltersData.getBeginDateMillis() != null && mailFiltersData.getEndDateMillis() != null) {
            map.put("interval", new JsonObject(MapsKt.mapOf(TuplesKt.to("from", JsonElementKt.JsonPrimitive(Long.valueOf(toSeconds(mailFiltersData.getBeginDateMillis().longValue())))), TuplesKt.to("to", JsonElementKt.JsonPrimitive(Long.valueOf(toSeconds(mailFiltersData.getEndDateMillis().longValue())))))));
        }
        Long folderId = mailFiltersData.getFolderId();
        if (folderId != null) {
            map.put("folder", JsonElementKt.JsonPrimitive(Long.valueOf(folderId.longValue())));
        }
        String transactionCategoryId = mailFiltersData.getTransactionCategoryId();
        if (transactionCategoryId != null) {
            map.put("transaction_category", JsonElementKt.JsonPrimitive(transactionCategoryId));
        }
        map.put("with_threads", JsonElementKt.JsonPrimitive(Boolean.TRUE));
        map.put("htmlencoded", JsonElementKt.JsonPrimitive(Boolean.FALSE));
        map.put("snippet_limit", JsonElementKt.JsonPrimitive((Number) 279));
        return new JsonObject(map).toString();
    }

    private final String buildUrl(List<String> paths, RequestParams requestParams) {
        Uri.Builder builderBuildUpon = Uri.parse(SEARCH_API_HOST).buildUpon();
        Iterator<T> it = paths.iterator();
        while (it.hasNext()) {
            builderBuildUpon.appendPath((String) it.next());
        }
        if (requestParams != null) {
            String query = requestParams.getQuery();
            if (query != null && !StringsKt.isBlank(query)) {
                builderBuildUpon.appendQueryParameter("q", requestParams.getQuery());
            }
            if (requestParams.getPagingData() != null) {
                builderBuildUpon.appendQueryParameter("offset", String.valueOf(requestParams.getPagingData().getCurrentCount()));
                builderBuildUpon.appendQueryParameter("limit", String.valueOf(requestParams.getPagingData().getCount()));
            }
            if (requestParams.getMailFiltersData() != null) {
                builderBuildUpon.appendQueryParameter("filters", buildMailFiltersJson(requestParams.getMailFiltersData()));
            }
            builderBuildUpon.appendQueryParameter("nosp", requestParams.isWithSpellchecker() ? PaginationHelper.DEFAULT_NEXT_FROM : "1");
        }
        String string = builderBuildUpon.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final long toSeconds(long j10) {
        return TimeUnit.MILLISECONDS.toSeconds(j10);
    }

    @NotNull
    public final String buildAvatarUrl(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        String string = Uri.parse(AVATARS_HOST).buildUpon().appendQueryParameter("email", email).appendQueryParameter("width", "180").appendQueryParameter("height", "180").toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @NotNull
    public final String buildCloudUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"search", "cloud"}), requestParams);
    }

    @NotNull
    public final String buildContactsUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"search", "contacts"}), requestParams);
    }

    @NotNull
    public final RequestBody buildConvertCloudTokenBody(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        FormBody.Builder builder = new FormBody.Builder(null, 1, null);
        String string = this.context.getString(R.string.superappsearch_metasearch_client_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        FormBody.Builder builderAdd = builder.add("client_id", string).add("grant_type", "convert").add("access_token", token);
        String string2 = this.context.getString(R.string.superappsearch_cloud_static_token);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        return builderAdd.add("for_client_id", string2).build();
    }

    @NotNull
    public final String buildDeleteHistoryUrl(@NotNull String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        String string = Uri.parse(buildHistoryUrl()).buildUpon().appendQueryParameter("q", query).appendQueryParameter("op", "delete_query").toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @NotNull
    public final String buildHistoryUrl() {
        return buildUrl(CollectionsKt.listOf(Constants.HISTORY_STORY_ITEM_TYPE), null);
    }

    @NotNull
    public final String buildMailUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"search", "emails"}), requestParams);
    }

    @NotNull
    public final String buildSearchResultFullUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"search", PATH_FULL}), requestParams);
    }

    @NotNull
    public final String buildSitesUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"search", "web"}), requestParams);
    }

    @NotNull
    public final String buildSuggestsUrl(@NotNull RequestParams requestParams) {
        Intrinsics.checkNotNullParameter(requestParams, "requestParams");
        return buildUrl(CollectionsKt.listOf((Object[]) new String[]{"suggests", PATH_FULL}), requestParams);
    }

    @NotNull
    public final String formatRelativeReference(@NotNull String relativeRef, @NotNull String sourceUrl) {
        String str;
        Intrinsics.checkNotNullParameter(relativeRef, "relativeRef");
        Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
        Uri uri = Uri.parse(sourceUrl);
        if (StringsKt.startsWith$default(relativeRef, "http", false, 2, (Object) null)) {
            return relativeRef;
        }
        if (StringsKt.startsWith$default(relativeRef, "//", false, 2, (Object) null)) {
            String scheme = uri.getScheme();
            return (scheme != null ? scheme : "http") + ":" + relativeRef;
        }
        if (StringsKt.startsWith$default(relativeRef, "/", false, 2, (Object) null)) {
            String scheme2 = uri.getScheme();
            str = scheme2 != null ? scheme2 : "http";
            String host = uri.getHost();
            return str + "://" + (host != null ? host : "") + relativeRef;
        }
        String scheme3 = uri.getScheme();
        str = scheme3 != null ? scheme3 : "http";
        String host2 = uri.getHost();
        return str + "://" + (host2 != null ? host2 : "") + "/" + relativeRef;
    }

    @NotNull
    public final String getO2TokenUrl() {
        return O2_TOKEN_HOST;
    }
}
