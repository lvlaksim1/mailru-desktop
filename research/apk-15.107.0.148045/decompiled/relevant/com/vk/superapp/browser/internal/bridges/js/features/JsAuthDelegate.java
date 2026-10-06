package com.vk.superapp.browser.internal.bridges.js.features;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.api.sdk.auth.VKAuthParams;
import com.vk.core.extensions.KotlinStringExtKt;
import com.vk.external.miniapp.net.app.WebApiApplication;
import com.vk.superapp.api.contract.SuperappApi;
import com.vk.superapp.api.states.VkGetOauthTokenArgs;
import com.vk.superapp.base.js.bridge.VkWebAppPermissionCallback;
import com.vk.superapp.base.js.bridge.delegate.LocalAuthResult;
import com.vk.superapp.base.js.bridge.delegate.LocalTokenDelegate;
import com.vk.superapp.bridges.SuperappBridgesKt;
import com.vk.superapp.bridges.dto.AuthData;
import com.vk.superapp.bridges.dto.DeactivatedInfo;
import com.vk.superapp.browser.internal.bridges.JsApiMethodType;
import com.vk.superapp.browser.internal.bridges.WebAppBridge;
import com.vk.superapp.browser.internal.bridges.js.JsVkBrowserCoreBridge;
import com.vk.superapp.browser.internal.delegates.VkUiBrowserPresenter;
import com.vk.superapp.browser.internal.delegates.VkUiBrowserView;
import com.vk.superapp.browser.internal.utils.WebClients;
import com.vk.superapp.core.api.models.BanInfo;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import com.vk.superapp.core.errors.VkAppsErrors;
import com.vk.superapp.core.extensions.CollectionsExtKt;
import com.vk.superapp.core.utils.ThreadUtils;
import com.vk.superapp.core.utils.WebLogger;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0013\b\u0016\u0018\u00002\u00020\u0001:\u0001\u001fB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010JI\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\nH\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001e\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00028\u0014X\u0094\u0004¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u001a\u0010\u0005\u001a\u00020\u00048\u0014X\u0094\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/js/features/JsAuthDelegate;", "", "Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;", "bridge", "Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;", "localTokenDelegate", "<init>", "(Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;)V", "", "data", "", "isForCommunity", "Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;", "method", "", "handleGetAuthTokenData$browser_release", "(Ljava/lang/String;ZLcom/vk/superapp/browser/internal/bridges/JsApiMethodType;)V", "handleGetAuthTokenData", "", "appId", "", SharedKt.PARAM_SCOPES, "skipConsent", "groupId", "useLocalToken", "requestAuthToken$browser_release", "(JLjava/util/List;ZLcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Ljava/lang/Long;Z)V", "requestAuthToken", "checkAllowedScopes$browser_release", "(Ljava/lang/String;)V", "checkAllowedScopes", "resworbkvmoca", "Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;", "getBridge", "()Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;", "resworbkvmocb", "Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;", "getLocalTokenDelegate", "()Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsAuthDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsAuthDelegate.kt\ncom/vk/superapp/browser/internal/bridges/js/features/JsAuthDelegate\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,293:1\n1563#2:294\n1634#2,3:295\n774#2:298\n865#2,2:299\n1563#2:305\n1634#2,3:306\n774#2:309\n865#2,2:310\n126#3:301\n153#3,3:302\n*S KotlinDebug\n*F\n+ 1 JsAuthDelegate.kt\ncom/vk/superapp/browser/internal/bridges/js/features/JsAuthDelegate\n*L\n190#1:294\n190#1:295,3\n191#1:298\n191#1:299,2\n223#1:305\n223#1:306,3\n224#1:309\n224#1:310,2\n206#1:301\n206#1:302,3\n*E\n"})
public class JsAuthDelegate {

    /* JADX INFO: renamed from: resworbkvmoca, reason: from kotlin metadata */
    @NotNull
    private final JsVkBrowserCoreBridge bridge;

    /* JADX INFO: renamed from: resworbkvmocb, reason: from kotlin metadata */
    @NotNull
    private final LocalTokenDelegate localTokenDelegate;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    static final class resworbkvmoca {

        @NotNull
        private final WebAuthAnswer resworbkvmoca;

        @Nullable
        private final LocalAuthResult resworbkvmocb;

        public resworbkvmoca(@NotNull WebAuthAnswer token, @Nullable LocalAuthResult localAuthResult) {
            Intrinsics.checkNotNullParameter(token, "token");
            this.resworbkvmoca = token;
            this.resworbkvmocb = localAuthResult;
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof resworbkvmoca)) {
                return false;
            }
            resworbkvmoca resworbkvmocaVar = (resworbkvmoca) obj;
            return Intrinsics.areEqual(this.resworbkvmoca, resworbkvmocaVar.resworbkvmoca) && Intrinsics.areEqual(this.resworbkvmocb, resworbkvmocaVar.resworbkvmocb);
        }

        public final int hashCode() {
            int iHashCode = this.resworbkvmoca.hashCode() * 31;
            LocalAuthResult localAuthResult = this.resworbkvmocb;
            return iHashCode + (localAuthResult == null ? 0 : localAuthResult.hashCode());
        }

        @Nullable
        public final LocalAuthResult resworbkvmoca() {
            return this.resworbkvmocb;
        }

        @NotNull
        public final WebAuthAnswer resworbkvmocb() {
            return this.resworbkvmoca;
        }

        @NotNull
        public final String toString() {
            return "ZippedTokenResult(token=" + this.resworbkvmoca + ", localToken=" + this.resworbkvmocb + ')';
        }
    }

    public JsAuthDelegate(@NotNull JsVkBrowserCoreBridge bridge, @NotNull LocalTokenDelegate localTokenDelegate) {
        Intrinsics.checkNotNullParameter(bridge, "bridge");
        Intrinsics.checkNotNullParameter(localTokenDelegate, "localTokenDelegate");
        this.bridge = bridge;
        this.localTokenDelegate = localTokenDelegate;
    }

    public static /* synthetic */ void requestAuthToken$browser_release$default(JsAuthDelegate jsAuthDelegate, long j10, List list, boolean z10, JsApiMethodType jsApiMethodType, Long l10, boolean z11, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestAuthToken");
        }
        if ((i10 & 16) != 0) {
            l10 = null;
        }
        jsAuthDelegate.requestAuthToken$browser_release(j10, list, z10, jsApiMethodType, l10, z11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(final List list, final JsAuthDelegate jsAuthDelegate, final long j10, final Long l10, final boolean z10, final boolean z11, final JsApiMethodType jsApiMethodType) {
        Pair pair;
        String str;
        Observable map;
        WebView webView;
        final String strJoin$default = CollectionsExtKt.join$default(list, ",", null, 2, null);
        WebClients.Holder webViewHolder = jsAuthDelegate.getBridge().getWebViewHolder();
        String url = (webViewHolder == null || (webView = webViewHolder.getWebView()) == null) ? null : webView.getUrl();
        if (url == null) {
            WebLogger.INSTANCE.w("empty url on auth request!");
            return Unit.INSTANCE;
        }
        AuthData auth = jsAuthDelegate.getBridge().getAuth();
        BanInfo kdskvkvmocb = SuperappBridgesKt.getSuperappAuth().getKdskvkvmocb();
        DeactivatedInfo kdskvkvmocc = SuperappBridgesKt.getSuperappAuth().getKdskvkvmocc();
        SuperappBridgesKt.getSuperappAuth().setDeactivatedInfo(null);
        if (KotlinStringExtKt.isNotEmpty(auth.getAccessToken())) {
            pair = TuplesKt.to(auth.getAccessToken(), auth.getSecret());
        } else if (kdskvkvmocb != null) {
            pair = TuplesKt.to(kdskvkvmocb.getAccessToken(), kdskvkvmocb.getSecret());
        } else {
            pair = kdskvkvmocc != null ? TuplesKt.to(kdskvkvmocc.getCom.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN java.lang.String(), kdskvkvmocc.getCom.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN java.lang.String()) : TuplesKt.to(null, null);
        }
        String str2 = (String) pair.component1();
        String str3 = (String) pair.component2();
        VkGetOauthTokenArgs.Companion companion = VkGetOauthTokenArgs.INSTANCE;
        if (str2 == null) {
            str2 = "";
        }
        VkGetOauthTokenArgs vkGetOauthTokenArgsCreateForVkUi = companion.createForVkUi(str2, str3, j10, strJoin$default, (1984 & 16) != 0 ? VKAuthParams.INSTANCE.getDEFAULT_REDIRECT_URL() : VKAuthParams.INSTANCE.getDEFAULT_REDIRECT_URL(), url, (1984 & 64) != 0 ? "android" : null, (1984 & 128) != 0 ? "token" : null, (1984 & 256) != 0 ? null : l10, (1984 & 512) != 0 ? false : false, (1984 & 1024) != 0 ? false : z10);
        SuperappApi.Account account = SuperappBridgesKt.getSuperappApi().getAccount();
        if (l10 != null) {
            str = "access_token_" + l10;
        } else {
            str = "access_token";
        }
        Observable<WebAuthAnswer> observableSendGetAccessToken = account.sendGetAccessToken(j10, vkGetOauthTokenArgsCreateForVkUi, str);
        if (z11) {
            Observable<LocalAuthResult> localAuthToken = jsAuthDelegate.getLocalTokenDelegate().getLocalAuthToken(j10, strJoin$default);
            final Function2 function2 = new Function2() { // from class: com.vk.superapp.browser.internal.bridges.js.features.j
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return JsAuthDelegate.resworbkvmoca((WebAuthAnswer) obj, (LocalAuthResult) obj2);
                }
            };
            map = Observable.zip(observableSendGetAccessToken, localAuthToken, new BiFunction() { // from class: com.vk.superapp.browser.internal.bridges.js.features.k
                @Override // io.reactivex.rxjava3.functions.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return JsAuthDelegate.resworbkvmoca(function2, obj, obj2);
                }
            });
        } else {
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.features.l
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return JsAuthDelegate.resworbkvmoca((WebAuthAnswer) obj);
                }
            };
            map = observableSendGetAccessToken.map(new Function() { // from class: com.vk.superapp.browser.internal.bridges.js.features.m
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return JsAuthDelegate.resworbkvmocc(function1, obj);
                }
            });
        }
        Observable observable = map;
        final Function1 function3 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.features.n
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsAuthDelegate.resworbkvmoca(z11, jsAuthDelegate, jsApiMethodType, strJoin$default, (JsAuthDelegate.resworbkvmoca) obj);
            }
        };
        Consumer consumer = new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.features.c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                JsAuthDelegate.resworbkvmocd(function3, obj);
            }
        };
        final Function1 function4 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.features.d
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsAuthDelegate.resworbkvmoca(z10, jsAuthDelegate, j10, list, l10, jsApiMethodType, z11, (Throwable) obj);
            }
        };
        observable.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.features.e
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                JsAuthDelegate.resworbkvmoce(function4, obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocb(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final resworbkvmoca resworbkvmocc(Function1 function1, Object obj) {
        return (resworbkvmoca) function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocd(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoce(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    public final void checkAllowedScopes$browser_release(@Nullable String data) {
        JsVkBrowserCoreBridge bridge = getBridge();
        JsApiMethodType jsApiMethodType = JsApiMethodType.CHECK_ALLOWED_SCOPES;
        if (bridge.onJsApiCalled(jsApiMethodType, data)) {
            VkUiBrowserPresenter presenter = getBridge().getPresenter();
            WebApiApplication webApiApplicationOptionalApp = presenter != null ? presenter.optionalApp() : null;
            if (webApiApplicationOptionalApp == null) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), jsApiMethodType, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, null, null, 60, null);
                return;
            }
            try {
                if (data == null) {
                    data = "";
                }
                String strOptString = new JSONObject(data).optString(SharedKt.PARAM_SCOPES);
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                List listSplit$default = StringsKt.split$default((CharSequence) strOptString, new String[]{","}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
                Iterator it = listSplit$default.iterator();
                while (it.hasNext()) {
                    arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
                }
                ArrayList arrayList2 = new ArrayList();
                int size = arrayList.size();
                int i10 = 0;
                while (i10 < size) {
                    Object obj = arrayList.get(i10);
                    i10++;
                    if (!StringsKt.isBlank((String) obj)) {
                        arrayList2.add(obj);
                    }
                }
                Observable<Map<String, Boolean>> observableSendAppsGetCheckAllowedScopes = SuperappBridgesKt.getSuperappApi().getApp().sendAppsGetCheckAllowedScopes(webApiApplicationOptionalApp.getId(), arrayList2);
                final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.features.b
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return JsAuthDelegate.resworbkvmoca(this.f52104a, (Map) obj2);
                    }
                };
                Consumer<? super Map<String, Boolean>> consumer = new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.features.f
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj2) {
                        JsAuthDelegate.resworbkvmoca(function1, obj2);
                    }
                };
                final Function1 function2 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.features.g
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return JsAuthDelegate.resworbkvmoca(this.f52136a, (Throwable) obj2);
                    }
                };
                observableSendAppsGetCheckAllowedScopes.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.features.h
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj2) {
                        JsAuthDelegate.resworbkvmocb(function2, obj2);
                    }
                });
            } catch (JSONException unused) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), JsApiMethodType.CHECK_ALLOWED_SCOPES, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            }
        }
    }

    @NotNull
    protected JsVkBrowserCoreBridge getBridge() {
        return this.bridge;
    }

    @NotNull
    protected LocalTokenDelegate getLocalTokenDelegate() {
        return this.localTokenDelegate;
    }

    public final void handleGetAuthTokenData$browser_release(@NotNull String data, boolean isForCommunity, @NotNull JsApiMethodType method) {
        Long lValueOf;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(method, "method");
        try {
            JSONObject jSONObject = new JSONObject(data);
            Pair<Long, List<String>> pairResworbkvmoca = resworbkvmoca(data, method);
            if (pairResworbkvmoca == null) {
                return;
            }
            long jLongValue = pairResworbkvmoca.component1().longValue();
            List<String> listComponent2 = pairResworbkvmoca.component2();
            if (!isForCommunity) {
                lValueOf = null;
            } else {
                if (!jSONObject.has("group_id")) {
                    WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), method, VkAppsErrors.Client.MISSING_PARAMS, null, null, null, null, 60, null);
                    return;
                }
                long j10 = jSONObject.getLong("group_id");
                lValueOf = Long.valueOf(j10);
                if (j10 < 0) {
                    WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), method, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
                    return;
                }
            }
            requestAuthToken$browser_release(jLongValue, listComponent2, false, method, lValueOf, jSONObject.optBoolean("append_local"));
        } catch (JSONException unused) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), method, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
        }
    }

    @SuppressLint({"CheckResult"})
    public final void requestAuthToken$browser_release(final long appId, @NotNull final List<String> scopes, final boolean skipConsent, @NotNull final JsApiMethodType method, @Nullable final Long groupId, final boolean useLocalToken) {
        Intrinsics.checkNotNullParameter(scopes, "scopes");
        Intrinsics.checkNotNullParameter(method, "method");
        ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.features.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsAuthDelegate.resworbkvmoca(scopes, this, appId, groupId, skipConsent, useLocalToken, method);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final resworbkvmoca resworbkvmoca(Function2 function2, Object obj, Object obj2) {
        return (resworbkvmoca) function2.invoke(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final resworbkvmoca resworbkvmoca(WebAuthAnswer webAuthAnswer, LocalAuthResult localAuthResult) {
        Intrinsics.checkNotNull(webAuthAnswer);
        return new resworbkvmoca(webAuthAnswer, localAuthResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final resworbkvmoca resworbkvmoca(WebAuthAnswer webAuthAnswer) {
        Intrinsics.checkNotNull(webAuthAnswer);
        return new resworbkvmoca(webAuthAnswer, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(boolean z10, JsAuthDelegate jsAuthDelegate, JsApiMethodType jsApiMethodType, String str, resworbkvmoca resworbkvmocaVar) throws JSONException {
        WebApiApplication webApiApplicationOptionalApp;
        VkUiBrowserPresenter presenter;
        VkUiBrowserView resworbkvmocs;
        if (z10 && (resworbkvmocaVar.resworbkvmoca() instanceof LocalAuthResult.ErrorResult)) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsAuthDelegate.getBridge(), jsApiMethodType, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, null, null, 60, null);
            return Unit.INSTANCE;
        }
        LocalAuthResult localAuthResultResworbkvmoca = resworbkvmocaVar.resworbkvmoca();
        LocalAuthResult.SuccessResult successResult = localAuthResultResworbkvmoca instanceof LocalAuthResult.SuccessResult ? (LocalAuthResult.SuccessResult) localAuthResultResworbkvmoca : null;
        JSONObject jSONObjectPut = new JSONObject().put("access_token", resworbkvmocaVar.resworbkvmocb().getAccessToken()).put("local_access_token", successResult != null ? successResult.getLocalAccessToken() : null).put(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, str);
        JsVkBrowserCoreBridge bridge = jsAuthDelegate.getBridge();
        Intrinsics.checkNotNull(jSONObjectPut);
        WebAppBridge.DefaultImpls.sendEventSuccess$default(bridge, jsApiMethodType, jSONObjectPut, null, null, 12, null);
        VkUiBrowserPresenter presenter2 = jsAuthDelegate.getBridge().getPresenter();
        if (presenter2 != null && (webApiApplicationOptionalApp = presenter2.optionalApp()) != null && !webApiApplicationOptionalApp.getInstalled() && (presenter = jsAuthDelegate.getBridge().getPresenter()) != null && (resworbkvmocs = presenter.getResworbkvmocs()) != null) {
            resworbkvmocs.updateAppInfo();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x0065  */
    /* JADX WARN: Code duplicated, block: B:28:0x0069  */
    /* JADX WARN: Code duplicated, block: B:30:0x0075  */
    /* JADX WARN: Code duplicated, block: B:31:0x0077  */
    /* JADX WARN: Code duplicated, block: B:34:0x007e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0085  */
    /* JADX WARN: Code duplicated, block: B:38:0x0087  */
    /* JADX WARN: Code duplicated, block: B:40:0x0092  */
    public static final Unit resworbkvmoca(boolean z10, final JsAuthDelegate jsAuthDelegate, final long j10, List list, final Long l10, final JsApiMethodType jsApiMethodType, final boolean z11, Throwable th2) throws JSONException {
        JSONObject jSONObjectCreateForApi$default;
        String error;
        String str;
        String errorDescription;
        String str2;
        String str3;
        VkUiBrowserPresenter presenter;
        VkUiBrowserView resworbkvmocs;
        Throwable cause = th2.getCause();
        if (cause == null) {
            cause = th2;
        }
        if (!z10) {
            WebApiApplication webApiApplicationRequireApp = null;
            VKWebAuthException vKWebAuthException = cause instanceof VKWebAuthException ? (VKWebAuthException) cause : null;
            if (vKWebAuthException != null && vKWebAuthException.isLastRequestSuccess()) {
                final JsVkBrowserCoreBridge bridge = jsAuthDelegate.getBridge();
                if (bridge.isBackgroundCall()) {
                    WebAppBridge.DefaultImpls.sendEventFailed$default(bridge, jsApiMethodType, VkAppsErrors.Client.INACTIVE_SCREEN, null, null, null, null, 60, null);
                } else {
                    try {
                        VkUiBrowserPresenter presenter2 = bridge.getPresenter();
                        if (presenter2 != null) {
                            webApiApplicationRequireApp = presenter2.requireApp();
                        }
                    } catch (Throwable unused) {
                    }
                    if (webApiApplicationRequireApp != null && (presenter = bridge.getPresenter()) != null && (resworbkvmocs = presenter.getResworbkvmocs()) != null) {
                        resworbkvmocs.requestPermissions(list, l10, webApiApplicationRequireApp, new VkWebAppPermissionCallback() { // from class: com.vk.superapp.browser.internal.bridges.js.features.JsAuthDelegate$requestScopes$1$1$1
                            @Override // com.vk.superapp.base.js.bridge.VkWebAppPermissionCallback
                            public void onAccessDenied() {
                                WebAppBridge.DefaultImpls.sendEventFailed$default(bridge, jsApiMethodType, VkAppsErrors.Client.USER_DENIED, null, null, null, null, 60, null);
                            }

                            @Override // com.vk.superapp.base.js.bridge.VkWebAppPermissionCallback
                            public void onPermissionsError(Throwable error2) {
                                Intrinsics.checkNotNullParameter(error2, "error");
                                WebAppBridge.DefaultImpls.sendEventFailed$default(bridge, jsApiMethodType, error2, null, 4, null);
                            }

                            @Override // com.vk.superapp.base.js.bridge.VkWebAppPermissionCallback
                            public void onPermissionsGranted(List<String> scopes) {
                                Intrinsics.checkNotNullParameter(scopes, "scopes");
                                this.resworbkvmoca.requestAuthToken$browser_release(j10, scopes, true, jsApiMethodType, l10, z11);
                            }
                        });
                    }
                }
            } else {
                if (cause instanceof VKWebAuthException) {
                    VkAppsErrors vkAppsErrors = VkAppsErrors.INSTANCE;
                    VKWebAuthException vKWebAuthException2 = (VKWebAuthException) cause;
                    error = vKWebAuthException2.getError();
                    if (error == null) {
                        str = "";
                    } else {
                        str = error;
                    }
                    errorDescription = vKWebAuthException2.getErrorDescription();
                    if (errorDescription == null) {
                        errorDescription = "";
                    }
                    str2 = vKWebAuthException2.getCom.huawei.hms.support.hianalytics.HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON java.lang.String();
                    if (str2 == null) {
                        str3 = "";
                    } else {
                        str3 = str2;
                    }
                    jSONObjectCreateForApi$default = VkAppsErrors.createForAuth$default(vkAppsErrors, errorDescription, str, str3, null, 8, null);
                } else {
                    VkAppsErrors vkAppsErrors2 = VkAppsErrors.INSTANCE;
                    Intrinsics.checkNotNull(th2);
                    jSONObjectCreateForApi$default = VkAppsErrors.createForApi$default(vkAppsErrors2, th2, null, null, 6, null);
                }
                jsAuthDelegate.getBridge().sendEventFailed(jsApiMethodType, jSONObjectCreateForApi$default);
            }
        } else {
            if (cause instanceof VKWebAuthException) {
                VkAppsErrors vkAppsErrors3 = VkAppsErrors.INSTANCE;
                VKWebAuthException vKWebAuthException3 = (VKWebAuthException) cause;
                error = vKWebAuthException3.getError();
                if (error == null) {
                    str = "";
                } else {
                    str = error;
                }
                errorDescription = vKWebAuthException3.getErrorDescription();
                if (errorDescription == null) {
                    errorDescription = "";
                }
                str2 = vKWebAuthException3.getCom.huawei.hms.support.hianalytics.HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON java.lang.String();
                if (str2 == null) {
                    str3 = "";
                } else {
                    str3 = str2;
                }
                jSONObjectCreateForApi$default = VkAppsErrors.createForAuth$default(vkAppsErrors3, errorDescription, str, str3, null, 8, null);
            } else {
                VkAppsErrors vkAppsErrors4 = VkAppsErrors.INSTANCE;
                Intrinsics.checkNotNull(th2);
                jSONObjectCreateForApi$default = VkAppsErrors.createForApi$default(vkAppsErrors4, th2, null, null, 6, null);
            }
            jsAuthDelegate.getBridge().sendEventFailed(jsApiMethodType, jSONObjectCreateForApi$default);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsAuthDelegate jsAuthDelegate, Map map) throws JSONException {
        JsVkBrowserCoreBridge bridge = jsAuthDelegate.getBridge();
        JsApiMethodType jsApiMethodType = JsApiMethodType.CHECK_ALLOWED_SCOPES;
        Intrinsics.checkNotNull(map);
        JSONObject jSONObject = new JSONObject();
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            arrayList.add(new JSONObject(MapsKt.mapOf(TuplesKt.to(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, entry.getKey()), TuplesKt.to("allowed", entry.getValue()))));
        }
        jSONObject.put("result", new JSONArray((Collection) arrayList));
        WebAppBridge.DefaultImpls.sendEventSuccess$default(bridge, jsApiMethodType, jSONObject, null, null, 12, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsAuthDelegate jsAuthDelegate, Throwable th2) throws JSONException {
        JsVkBrowserCoreBridge bridge = jsAuthDelegate.getBridge();
        JsApiMethodType jsApiMethodType = JsApiMethodType.CHECK_ALLOWED_SCOPES;
        VkAppsErrors vkAppsErrors = VkAppsErrors.INSTANCE;
        Intrinsics.checkNotNull(th2);
        bridge.sendEventFailed(jsApiMethodType, VkAppsErrors.createForApi$default(vkAppsErrors, th2, null, null, 6, null));
        return Unit.INSTANCE;
    }

    private final Pair<Long, List<String>> resworbkvmoca(String str, JsApiMethodType jsApiMethodType) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL);
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            List listSplit$default = StringsKt.split$default((CharSequence) strOptString, new String[]{","}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
            }
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj = arrayList.get(i10);
                i10++;
                if (!StringsKt.isBlank((String) obj)) {
                    arrayList2.add(obj);
                }
            }
            if (!jSONObject.has("app_id")) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), jsApiMethodType, VkAppsErrors.Client.MISSING_PARAMS, null, null, null, null, 60, null);
                return null;
            }
            long j10 = jSONObject.getLong("app_id");
            VkUiBrowserPresenter presenter = getBridge().getPresenter();
            long appId = presenter != null ? presenter.getAppId() : 0L;
            if (appId > 0 && appId != j10) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), jsApiMethodType, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
                return null;
            }
            return new Pair<>(Long.valueOf(j10), arrayList2);
        } catch (JSONException unused) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(getBridge(), jsApiMethodType, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            return null;
        }
    }
}
