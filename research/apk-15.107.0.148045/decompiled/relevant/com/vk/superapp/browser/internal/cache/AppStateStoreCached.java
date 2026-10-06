package com.vk.superapp.browser.internal.cache;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.core.extensions.UriExtKt;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.browser.internal.bridges.VkJsInterfaceProvider;
import com.vk.superapp.browser.internal.cache.contract.AppStateHolder;
import com.vk.superapp.browser.internal.cache.contract.AppStateStore;
import com.vk.superapp.browser.internal.cache.contract.AppsCacheManager;
import com.vk.superapp.browser.internal.delegates.data.VkUiData;
import com.vk.superapp.browser.ui.webview.contract.VkWebViewPool;
import com.vk.superapp.browser.ui.webview.contract.VkWebViewProvider;
import com.vk.utils.time.ServerClock;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0019\u0010\u0015J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/vk/superapp/browser/internal/cache/AppStateStoreCached;", "Lcom/vk/superapp/browser/internal/cache/contract/AppStateStore;", "Lcom/vk/superapp/browser/internal/cache/contract/AppsCacheManager;", "manager", "Lcom/vk/superapp/browser/ui/webview/contract/VkWebViewProvider;", "webViewProvider", "Lcom/vk/superapp/browser/internal/bridges/VkJsInterfaceProvider;", "jsProvider", "Lcom/vk/superapp/browser/ui/webview/contract/VkWebViewPool;", "webViewPool", "<init>", "(Lcom/vk/superapp/browser/internal/cache/contract/AppsCacheManager;Lcom/vk/superapp/browser/ui/webview/contract/VkWebViewProvider;Lcom/vk/superapp/browser/internal/bridges/VkJsInterfaceProvider;Lcom/vk/superapp/browser/ui/webview/contract/VkWebViewPool;)V", "Lcom/vk/superapp/browser/internal/cache/AppCache;", "cache", "Lcom/vk/superapp/browser/internal/delegates/data/VkUiData;", "data", "Lcom/vk/superapp/browser/internal/cache/AppStateHolderCached;", "getAppStateHolder", "(Lcom/vk/superapp/browser/internal/cache/AppCache;Lcom/vk/superapp/browser/internal/delegates/data/VkUiData;)Lcom/vk/superapp/browser/internal/cache/AppStateHolderCached;", "Lcom/vk/superapp/browser/internal/cache/contract/AppStateHolder;", "find", "(Lcom/vk/superapp/browser/internal/delegates/data/VkUiData;)Lcom/vk/superapp/browser/internal/cache/contract/AppStateHolder;", "", "isContextInvalid", "(Lcom/vk/superapp/browser/internal/cache/AppCache;)Z", "obtain", "", "removeGame", "(Lcom/vk/superapp/browser/internal/delegates/data/VkUiData;)V", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAppStateStoreCached.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppStateStoreCached.kt\ncom/vk/superapp/browser/internal/cache/AppStateStoreCached\n+ 2 KotlinCommonExt.kt\ncom/vk/core/extensions/KotlinCommonExtKt\n*L\n1#1,150:1\n9#2,2:151\n*S KotlinDebug\n*F\n+ 1 AppStateStoreCached.kt\ncom/vk/superapp/browser/internal/cache/AppStateStoreCached\n*L\n65#1:151,2\n*E\n"})
public class AppStateStoreCached implements AppStateStore {

    @NotNull
    private final AppsCacheManager resworbkvmoca;

    @NotNull
    private final VkWebViewProvider resworbkvmocb;

    @NotNull
    private final VkJsInterfaceProvider resworbkvmocc;

    @NotNull
    private final VkWebViewPool resworbkvmocd;

    public AppStateStoreCached(@NotNull AppsCacheManager manager, @NotNull VkWebViewProvider webViewProvider, @NotNull VkJsInterfaceProvider jsProvider, @NotNull VkWebViewPool webViewPool) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(webViewProvider, "webViewProvider");
        Intrinsics.checkNotNullParameter(jsProvider, "jsProvider");
        Intrinsics.checkNotNullParameter(webViewPool, "webViewPool");
        this.resworbkvmoca = manager;
        this.resworbkvmocb = webViewProvider;
        this.resworbkvmocc = jsProvider;
        this.resworbkvmocd = webViewPool;
    }

    @Override // com.vk.superapp.browser.internal.cache.contract.AppStateStore
    @Nullable
    public AppStateHolder find(@NotNull VkUiData data) {
        boolean z10;
        Long longOrNull;
        Intrinsics.checkNotNullParameter(data, "data");
        AppCache appCache = this.resworbkvmoca.get(data.getAppId());
        if (appCache == null) {
            return null;
        }
        boolean z11 = data instanceof VkUiData.App;
        if (z11 && isContextInvalid(appCache)) {
            this.resworbkvmoca.remove(((VkUiData.App) data).getAppId());
            return null;
        }
        List listMutableListOf = CollectionsKt.mutableListOf("vk_ts", "sign");
        String urlToLoad = data.getUrlToLoad();
        if (z11 && ((VkUiData.App) data).getApp().isHtmlGame()) {
            Uri uri = Uri.parse(urlToLoad);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            String strQp = UriExtKt.qp(uri, "timestamp");
            Long lValueOf = (strQp == null || (longOrNull = StringsKt.toLongOrNull(strQp)) == null) ? null : Long.valueOf(longOrNull.longValue() * ((long) 1000));
            if (lValueOf != null && lValueOf.longValue() + 86400000 > ServerClock.currentServerTimeMillis()) {
                listMutableListOf.add("api_hash");
                listMutableListOf.add(PasskeyBeginResult.SID_KEY);
                listMutableListOf.add("lc_name");
                listMutableListOf.add("timestamp");
                listMutableListOf.add(AccountManagerRepositoryImpl.SECRET_ARG);
                listMutableListOf.add("access_token");
                listMutableListOf.add(BlockParser.REF_TYPE);
                listMutableListOf.add("referrer");
                listMutableListOf.add("fast");
            }
        }
        String lastLoadedUrl = appCache.getLastLoadedUrl();
        String urlToLoad2 = data.getUrlToLoad();
        if (lastLoadedUrl == null || urlToLoad2 == null) {
            z10 = false;
        } else {
            Uri uri2 = Uri.parse(lastLoadedUrl);
            Intrinsics.checkNotNullExpressionValue(uri2, "parse(...)");
            String string = UriExtKt.removeListOfQueryParameters(uri2, listMutableListOf).buildUpon().fragment("").build().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            Uri uri3 = Uri.parse(urlToLoad2);
            Intrinsics.checkNotNullExpressionValue(uri3, "parse(...)");
            String string2 = UriExtKt.removeListOfQueryParameters(uri3, listMutableListOf).buildUpon().fragment("").build().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            z10 = !Intrinsics.areEqual(string, string2);
        }
        if (!z10) {
            return getAppStateHolder(appCache, data);
        }
        this.resworbkvmoca.remove(data.getAppId());
        return null;
    }

    @NotNull
    public AppStateHolderCached getAppStateHolder(@NotNull AppCache cache, @NotNull VkUiData data) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(data, "data");
        return new AppStateHolderCached(this.resworbkvmocd, cache, data);
    }

    public boolean isContextInvalid(@NotNull AppCache cache) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        WebView webView = cache.getWebView();
        Context context = webView != null ? webView.getContext() : null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        return activity != null && activity.isDestroyed();
    }

    @Override // com.vk.superapp.browser.internal.cache.contract.AppStateStore
    @NotNull
    public AppStateHolder obtain(@NotNull VkUiData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AppCache appCache = new AppCache(this.resworbkvmocb.create(), this.resworbkvmocc.get(), null, null, null, null, null, false, false, false, null, false, 4092, null);
        appCache.setSwipeToCloseEnabled((data instanceof VkUiData.App) && ((VkUiData.App) data).getApp().isMiniApp());
        if (data.getCanCache()) {
            appCache.setCached(true);
            this.resworbkvmoca.put(data.getAppId(), appCache);
        }
        return getAppStateHolder(appCache, data);
    }

    @Override // com.vk.superapp.browser.internal.cache.contract.AppStateStore
    public void removeGame(@NotNull VkUiData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof VkUiData.App) {
            VkUiData.App app = (VkUiData.App) data;
            if (app.getApp().isHtmlGame()) {
                this.resworbkvmoca.remove(app.getApp().getId());
            }
        }
    }
}
