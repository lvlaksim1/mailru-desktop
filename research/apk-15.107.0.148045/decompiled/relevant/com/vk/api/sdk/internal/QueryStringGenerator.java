package com.vk.api.sdk.internal;

import android.net.Uri;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.sdk.utils.ThreadLocalDelegate;
import com.vk.api.sdk.utils.ThreadLocalDelegateKt;
import com.vk.api.sdk.utils.VKUtils;
import com.vk.auth.restore.RestoreConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jh\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\u0006\u0010\u0019\u001a\u00020\u0016Jx\u0010\u001a\u001a\u00020\f2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u001c\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u001a\b\u0002\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001e0\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0016J\u008c\u0001\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\u001c\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u001a\b\u0002\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001e0\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0016JH\u0010!\u001a\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\u001a\b\u0002\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001e0\u000fR\u001f\u0010\u0004\u001a\u00060\u0005j\u0002`\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\""}, d2 = {"Lcom/vk/api/sdk/internal/QueryStringGenerator;", "", "<init>", "()V", "strBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getStrBuilder", "()Ljava/lang/StringBuilder;", "strBuilder$delegate", "Lcom/vk/api/sdk/utils/ThreadLocalDelegate;", "buildSignedQueryStringForMethod", "", "methodName", "methodArgs", "", "methodVersion", "activeAccessToken", AccountManagerRepositoryImpl.SECRET_ARG, "appId", "", "isMultipleTokens", "", "accessTokens", "", "forceAnonymous", "buildNotSignedQueryString", "args", "version", "arrayArgs", "", "buildSignedQueryString", "path", "buildSignedQueryStringForce", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nQueryStringGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 QueryStringGenerator.kt\ncom/vk/api/sdk/internal/QueryStringGenerator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,197:1\n1869#2,2:198\n1869#2:200\n1869#2,2:201\n1870#2:203\n*S KotlinDebug\n*F\n+ 1 QueryStringGenerator.kt\ncom/vk/api/sdk/internal/QueryStringGenerator\n*L\n149#1:198,2\n156#1:200\n157#1:201,2\n156#1:203\n*E\n"})
public final class QueryStringGenerator {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(QueryStringGenerator.class, "strBuilder", "getStrBuilder()Ljava/lang/StringBuilder;", 0))};

    @NotNull
    public static final QueryStringGenerator INSTANCE = new QueryStringGenerator();

    /* JADX INFO: renamed from: strBuilder$delegate, reason: from kotlin metadata */
    @NotNull
    private static final ThreadLocalDelegate strBuilder = ThreadLocalDelegateKt.threadLocal(new Function0() { // from class: com.vk.api.sdk.internal.a
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return QueryStringGenerator.strBuilder_delegate$lambda$0();
        }
    });

    private QueryStringGenerator() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String buildNotSignedQueryString$default(QueryStringGenerator queryStringGenerator, Map map, String str, String str2, int i10, Map map2, boolean z10, Collection collection, boolean z11, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            str2 = null;
        }
        return queryStringGenerator.buildNotSignedQueryString(map, str, str2, (i11 & 8) != 0 ? 0 : i10, (i11 & 16) != 0 ? MapsKt.emptyMap() : map2, (i11 & 32) != 0 ? false : z10, (i11 & 64) != 0 ? SetsKt.emptySet() : collection, (i11 & 128) != 0 ? false : z11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String buildSignedQueryString$default(QueryStringGenerator queryStringGenerator, String str, Map map, String str2, String str3, String str4, int i10, Map map2, boolean z10, Collection collection, boolean z11, int i11, Object obj) {
        if ((i11 & 8) != 0) {
            str3 = null;
        }
        if ((i11 & 16) != 0) {
            str4 = null;
        }
        if ((i11 & 32) != 0) {
            i10 = 0;
        }
        if ((i11 & 64) != 0) {
            map2 = MapsKt.emptyMap();
        }
        if ((i11 & 128) != 0) {
            z10 = false;
        }
        if ((i11 & 256) != 0) {
            collection = SetsKt.emptySet();
        }
        if ((i11 & 512) != 0) {
            z11 = false;
        }
        return queryStringGenerator.buildSignedQueryString(str, map, str2, str3, str4, i10, map2, z10, collection, z11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String buildSignedQueryStringForce$default(QueryStringGenerator queryStringGenerator, String str, Map map, String str2, Map map2, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            map2 = MapsKt.emptyMap();
        }
        return queryStringGenerator.buildSignedQueryStringForce(str, map, str2, map2);
    }

    private final StringBuilder getStrBuilder() {
        return (StringBuilder) strBuilder.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StringBuilder strBuilder_delegate$lambda$0() {
        return new StringBuilder();
    }

    @NotNull
    public final String buildNotSignedQueryString(@NotNull Map<String, String> args, @NotNull String version, @Nullable String activeAccessToken, int appId, @NotNull Map<String, ? extends List<String>> arrayArgs, boolean isMultipleTokens, @NotNull Collection<String> accessTokens, boolean forceAnonymous) {
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(arrayArgs, "arrayArgs");
        Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
        return buildSignedQueryString("", args, version, activeAccessToken, null, appId, arrayArgs, isMultipleTokens, accessTokens, forceAnonymous);
    }

    @NotNull
    public final String buildSignedQueryString(@NotNull String path, @NotNull Map<String, String> args, @NotNull String version, @Nullable String activeAccessToken, @Nullable String secret, int appId, @NotNull Map<String, ? extends List<String>> arrayArgs, boolean isMultipleTokens, @NotNull Collection<String> accessTokens, boolean forceAnonymous) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(arrayArgs, "arrayArgs");
        Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
        Map<String, String> mutableMap = MapsKt.toMutableMap(args);
        mutableMap.put(Logger.METHOD_V, version);
        mutableMap.put(RestoreConstants.DEFAULT_URL_SCHEME, "1");
        if (isMultipleTokens) {
            mutableMap.put("access_tokens", CollectionsKt.joinToString$default(accessTokens, ",", null, null, 0, null, null, 62, null));
        } else if (activeAccessToken != null && !StringsKt.isBlank(activeAccessToken) && !forceAnonymous) {
            mutableMap.put("access_token", activeAccessToken);
        } else if (appId != 0) {
            mutableMap.put("api_id", String.valueOf(appId));
        }
        return buildSignedQueryStringForce(path, mutableMap, secret, arrayArgs);
    }

    @NotNull
    public final String buildSignedQueryStringForMethod(@NotNull String methodName, @NotNull Map<String, String> methodArgs, @NotNull String methodVersion, @Nullable String activeAccessToken, @Nullable String secret, int appId, boolean isMultipleTokens, @NotNull Collection<String> accessTokens, boolean forceAnonymous) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(methodArgs, "methodArgs");
        Intrinsics.checkNotNullParameter(methodVersion, "methodVersion");
        Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
        return buildSignedQueryString$default(this, "/method/" + methodName, methodArgs, methodVersion, activeAccessToken, secret, appId, null, isMultipleTokens, accessTokens, forceAnonymous, 64, null);
    }

    @NotNull
    public final String buildSignedQueryStringForce(@NotNull String path, @NotNull Map<String, String> args, @Nullable String secret, @NotNull Map<String, ? extends List<String>> arrayArgs) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(arrayArgs, "arrayArgs");
        Uri.Builder builder = new Uri.Builder();
        Iterator<T> it = args.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!Intrinsics.areEqual(entry.getKey(), "sig")) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Iterator<T> it2 = arrayArgs.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            String str = (String) entry2.getKey();
            Iterator it3 = ((List) entry2.getValue()).iterator();
            while (it3.hasNext()) {
                builder.appendQueryParameter(str + "[]", (String) it3.next());
            }
        }
        Uri uriBuild = builder.build();
        if (secret == null || secret.length() == 0) {
            String encodedQuery = uriBuild.getEncodedQuery();
            return encodedQuery == null ? "" : encodedQuery;
        }
        String query = uriBuild.getQuery();
        getStrBuilder().setLength(0);
        StringBuilder strBuilder2 = getStrBuilder();
        strBuilder2.append(path);
        strBuilder2.append('?');
        if (query != null && !StringsKt.isBlank(query)) {
            getStrBuilder().append(query);
        }
        getStrBuilder().append(secret);
        String string = getStrBuilder().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String encodedQuery2 = uriBuild.buildUpon().appendQueryParameter("sig", VKUtils.MD5.convert(string)).build().getEncodedQuery();
        return encodedQuery2 == null ? "" : encodedQuery2;
    }
}
