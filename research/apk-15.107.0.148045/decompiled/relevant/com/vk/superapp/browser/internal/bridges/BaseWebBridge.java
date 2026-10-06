package com.vk.superapp.browser.internal.bridges;

import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.superapp.SuperappBrowserCore;
import com.vk.superapp.base.js.bridge.BaseEvent;
import com.vk.superapp.base.js.bridge.BaseJsBridge;
import com.vk.superapp.base.js.bridge.JsMethod;
import com.vk.superapp.base.js.bridge.Responses;
import com.vk.superapp.base.js.bridge.VkClientErrorSerializer;
import com.vk.superapp.browser.internal.bridges.js.JsGeneratedBridge;
import com.vk.superapp.browser.internal.utils.WebClients;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import com.vk.superapp.browser.utils.WebViewExtKt;
import com.vk.superapp.core.errors.VkAppsErrors;
import com.vk.superapp.core.utils.ThreadUtils;
import com.vk.superapp.core.utils.WebLogger;
import com.vk.superapp.js.bridge.events.EventNames;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010#\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000 [2\u00020\u00012\u00020\u0002:\u0003\\][B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0014¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010!\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010!\u001a\u00020\u00142\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b!\u0010%J\u0017\u0010&\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b&\u0010'J\u001f\u0010&\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b&\u0010(J)\u0010&\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010 \u001a\u00020)2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b&\u0010*J\u001f\u0010&\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010 \u001a\u00020+H\u0016¢\u0006\u0004\b&\u0010,JS\u0010&\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010 \u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00112\u0014\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u000200\u0018\u00010/2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b&\u00102J\u001f\u00104\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u0002032\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b4\u00105J'\u00104\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u00106\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0017¢\u0006\u0004\b4\u00107J'\u00108\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u00106\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b8\u00107J\u001d\u00109\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u0002032\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b9\u00105J\u001d\u0010:\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b<\u0010=J\u0017\u0010<\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b<\u0010>J\u0015\u0010?\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b?\u0010'J\u0015\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00110@H\u0004¢\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u00020\u0014H\u0004¢\u0006\u0004\bC\u0010DJ\u0017\u0010E\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\bE\u0010FJ!\u0010G\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\bG\u0010HJ\u0017\u0010I\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0014¢\u0006\u0004\bI\u0010FJ!\u0010G\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\bG\u0010JJ!\u0010K\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0004\bK\u0010LJ!\u0010K\u001a\u00020\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001a\u001a\u00020\u0019H\u0014¢\u0006\u0004\bK\u0010MJ\u001b\u0010<\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0010¢\u0006\u0004\bN\u0010OJ\u001d\u0010T\u001a\u00020\u00142\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00140PH\u0010¢\u0006\u0004\bR\u0010SR\u001e\u0010Z\u001a\u0004\u0018\u00010U8&@&X¦\u000e¢\u0006\f\u001a\u0004\bV\u0010W\"\u0004\bX\u0010Y¨\u0006^"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/BaseWebBridge;", "Lcom/vk/superapp/browser/internal/bridges/js/JsGeneratedBridge;", "Lcom/vk/superapp/browser/internal/bridges/WebAppBridge;", "Lcom/vk/superapp/browser/internal/bridges/MethodScope;", "allowedMethodsScope", "<init>", "(Lcom/vk/superapp/browser/internal/bridges/MethodScope;)V", "Landroid/webkit/WebView;", "webView", "()Landroid/webkit/WebView;", "", "isBackgroundCall", "()Z", "Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;", "method", "Lorg/json/JSONObject;", "data", "", "requestId", "callArguments", "", "sendEventSuccess", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;)V", "sendEventSuccessWithoutAnalytic", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Lorg/json/JSONObject;Ljava/lang/String;)V", "Lcom/vk/superapp/js/bridge/events/EventNames;", "event", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;", "rawResponse", "sendResponse", "(Lcom/vk/superapp/js/bridge/events/EventNames;Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;)V", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;", "error", "sendError", "(Lcom/vk/superapp/js/bridge/events/EventNames;Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;)V", "Lcom/vk/superapp/browser/internal/bridges/ErrorCreator;", "createError", "(Lcom/vk/superapp/browser/internal/bridges/ErrorCreator;)V", "sendEventFailed", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;)V", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Lorg/json/JSONObject;)V", "", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Ljava/lang/Throwable;Ljava/lang/String;)V", "Lcom/vk/superapp/core/errors/VkAppsErrors$CustomError;", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Lcom/vk/superapp/core/errors/VkAppsErrors$CustomError;)V", "Lcom/vk/superapp/core/errors/VkAppsErrors$Client;", "customDescription", "Lkotlin/Pair;", "", "additions", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Lcom/vk/superapp/core/errors/VkAppsErrors$Client;Ljava/lang/String;Lkotlin/Pair;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/vk/superapp/browser/internal/bridges/JsApiEvent;", "sendEventData", "(Lcom/vk/superapp/browser/internal/bridges/JsApiEvent;Lorg/json/JSONObject;)V", "eventName", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Ljava/lang/String;Lorg/json/JSONObject;)V", "emitEventData", "sendEventInstantly", "sendCustomEventInstantly", "(Ljava/lang/String;Lorg/json/JSONObject;)V", "getRequestId", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;)Ljava/lang/String;", "(Lcom/vk/superapp/js/bridge/events/EventNames;)Ljava/lang/String;", "clearRequestId", "", "getMethodsCalledWithoutRequestId", "()Ljava/util/Set;", "clearMethodsCalledWithoutRequestId", "()V", "isAlreadyRunning", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;)Z", "onJsApiCalled", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Ljava/lang/String;)Z", "isMethodAllowed", "(Ljava/lang/String;Lcom/vk/superapp/browser/internal/bridges/ErrorCreator;)Z", "registerJsMethod", "(Lcom/vk/superapp/browser/internal/bridges/JsApiMethodType;Ljava/lang/String;)V", "(Ljava/lang/String;Lcom/vk/superapp/js/bridge/events/EventNames;)V", "getRequestId$browser_release", "(Ljava/lang/String;)Ljava/lang/String;", "Lkotlin/Function0;", "runnable", "runUiThread$browser_release", "(Lkotlin/jvm/functions/Function0;)V", "runUiThread", "Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;", "getWebViewHolder", "()Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;", "setWebViewHolder", "(Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;)V", "webViewHolder", "Companion", "resworbkvmoca", "resworbkvmocb", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BaseWebBridge extends JsGeneratedBridge implements WebAppBridge {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static String resworbkvmoch = VkUiActivityResultDelegate.KEY_REQUEST_ID;

    @NotNull
    private final MethodScope resworbkvmocb;

    @NotNull
    private final Map<JsApiMethodType, String> resworbkvmocc;

    @NotNull
    private final Map<EventNames, String> resworbkvmocd;

    @NotNull
    private Set<String> resworbkvmoce;
    private final Gson resworbkvmocf;

    @NotNull
    private final resworbkvmoca resworbkvmocg;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\u0006R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/BaseWebBridge$Companion;", "", "<init>", "()V", "Lorg/json/JSONObject;", "createSuccessData", "()Lorg/json/JSONObject;", "createFailedData", "", "REQUEST_ID", "Ljava/lang/String;", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final JSONObject access$createJsonEvent(Companion companion, String str, JSONObject jSONObject, String str2) throws JSONException {
            companion.getClass();
            if (str2 != null && !StringsKt.isBlank(str2)) {
                jSONObject.put(BaseWebBridge.resworbkvmoch, str2);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", str);
            jSONObject2.put("data", jSONObject);
            if (str2 != null && !StringsKt.isBlank(str2)) {
                jSONObject2.put(BaseWebBridge.resworbkvmoch, str2);
            }
            return jSONObject2;
        }

        static JSONObject resworbkvmoca(Companion companion, String str, JSONObject jSONObject) throws JSONException {
            companion.getClass();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", str);
            jSONObject2.put("data", jSONObject);
            return jSONObject2;
        }

        @NotNull
        public final JSONObject createFailedData() throws JSONException {
            JSONObject jSONObjectPut = new JSONObject().put("result", false);
            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
            return jSONObjectPut;
        }

        @NotNull
        public final JSONObject createSuccessData() throws JSONException {
            JSONObject jSONObjectPut = new JSONObject().put("result", true);
            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
            return jSONObjectPut;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static final class resworbkvmoca {
        public resworbkvmoca(@NotNull resworbkvmocb hider) {
            Intrinsics.checkNotNullParameter(hider, "hider");
        }

        public final void resworbkvmoca(@NotNull JsApiMethodType method, @NotNull String eventName, @NotNull JSONObject jsonData) throws JSONException {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JSONObject jSONObjectResworbkvmoca = resworbkvmocb.resworbkvmoca(jsonData);
            WebLogger.INSTANCE.d("send event: " + method.getFullName() + ", eventName=" + eventName + " json=" + jSONObjectResworbkvmoca);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @SourceDebugExtension({"SMAP\nBaseWebBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseWebBridge.kt\ncom/vk/superapp/browser/internal/bridges/BaseWebBridge$EventSensitiveDataHider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,371:1\n774#2:372\n865#2,2:373\n1869#2,2:375\n*S KotlinDebug\n*F\n+ 1 BaseWebBridge.kt\ncom/vk/superapp/browser/internal/bridges/BaseWebBridge$EventSensitiveDataHider\n*L\n333#1:372\n333#1:373,2\n334#1:375,2\n*E\n"})
    private static final class resworbkvmocb {

        @NotNull
        private static final List<String> resworbkvmoca = CollectionsKt.listOf((Object[]) new String[]{"access_token", "token", AccountManagerRepositoryImpl.SECRET_ARG});

        @NotNull
        public static JSONObject resworbkvmoca(@NotNull JSONObject jsonData) throws JSONException {
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JSONObject jSONObject = new JSONObject(jsonData.toString());
            List<String> list = resworbkvmoca;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (jSONObject.has((String) obj)) {
                    arrayList.add(obj);
                }
            }
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj2 = arrayList.get(i10);
                i10++;
                jSONObject.put((String) obj2, "HIDE");
            }
            return jSONObject;
        }
    }

    public BaseWebBridge(@NotNull MethodScope allowedMethodsScope) {
        Intrinsics.checkNotNullParameter(allowedMethodsScope, "allowedMethodsScope");
        this.resworbkvmocb = allowedMethodsScope;
        Map<JsApiMethodType, String> mapSynchronizedMap = Collections.synchronizedMap(new EnumMap(JsApiMethodType.class));
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(...)");
        this.resworbkvmocc = mapSynchronizedMap;
        Map<EventNames, String> mapSynchronizedMap2 = Collections.synchronizedMap(new EnumMap(EventNames.class));
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap2, "synchronizedMap(...)");
        this.resworbkvmocd = mapSynchronizedMap2;
        Set<String> setSynchronizedSet = Collections.synchronizedSet(new HashSet());
        Intrinsics.checkNotNullExpressionValue(setSynchronizedSet, "synchronizedSet(...)");
        this.resworbkvmoce = setSynchronizedSet;
        this.resworbkvmocf = new GsonBuilder().registerTypeAdapter(Responses.ClientError.class, VkClientErrorSerializer.INSTANCE).create();
        this.resworbkvmocg = new resworbkvmoca(new resworbkvmocb());
    }

    private final void resworbkvmoca(final JSONObject jSONObject) {
        WebView webView = webView();
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.vk.superapp.browser.internal.bridges.b
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    BaseWebBridge.resworbkvmoca(this.f52062a, jSONObject);
                }
            });
        }
    }

    private final void sendEvent(final JsonObject jsonObject) {
        WebView webView = webView();
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.vk.superapp.browser.internal.bridges.a
                @Override // java.lang.Runnable
                public final void run() {
                    BaseWebBridge.resworbkvmoca(this.f52060a, jsonObject);
                }
            });
        }
    }

    protected final void clearMethodsCalledWithoutRequestId() {
        this.resworbkvmoce.clear();
    }

    public final void clearRequestId(@NotNull JsApiMethodType method) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.resworbkvmocc.remove(method);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void emitEventData(@NotNull JsApiMethodType method, @NotNull String eventName, @NotNull JSONObject data) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.d("send multiple event: " + method.getFullName() + ", eventName=" + eventName + ", jsonData=" + data);
        resworbkvmoca(Companion.access$createJsonEvent(INSTANCE, eventName, data, this.resworbkvmocc.get(method)));
    }

    @NotNull
    protected final Set<String> getMethodsCalledWithoutRequestId() {
        return this.resworbkvmoce;
    }

    @Nullable
    public final String getRequestId(@NotNull JsApiMethodType method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return this.resworbkvmocc.get(method);
    }

    @Nullable
    public String getRequestId$browser_release(@Nullable String data) {
        if (data != null) {
            try {
                return new JSONObject(data).optString(resworbkvmoch);
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    @Nullable
    public abstract WebClients.Holder getWebViewHolder();

    public boolean isAlreadyRunning(@NotNull JsApiMethodType method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return this.resworbkvmocc.get(method) != null;
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public boolean isBackgroundCall() {
        return SuperappBrowserCore.INSTANCE.isApplicationBackground$browser_release();
    }

    protected boolean isMethodAllowed(@NotNull JsApiMethodType method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return this.resworbkvmocb.isMethodAllowed(method);
    }

    public boolean onJsApiCalled(@NotNull JsApiMethodType method, @Nullable String data) {
        Intrinsics.checkNotNullParameter(method, "method");
        registerJsMethod(method, getRequestId$browser_release(data));
        if (!isMethodAllowed(method)) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(this, method, VkAppsErrors.Client.ACCESS_DENIED, null, null, null, null, 60, null);
            return false;
        }
        WebLogger webLogger = WebLogger.INSTANCE;
        webLogger.i("call " + method.getFullName());
        webLogger.d("data " + data);
        return true;
    }

    protected void registerJsMethod(@NotNull JsApiMethodType method, @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.resworbkvmocc.put(method, requestId);
        if (requestId == null || requestId.length() == 0) {
            this.resworbkvmoce.add(StringsKt.replace$default(method.getFullName(), "VKWebApp", "", false, 4, (Object) null));
        }
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void release() {
        WebAppBridge.DefaultImpls.release(this);
    }

    public void runUiThread$browser_release(@NotNull Function0<Unit> runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        ThreadUtils.runUiThread$default(null, runnable, 1, null);
    }

    public final void sendCustomEventInstantly(@NotNull String eventName, @NotNull JSONObject data) throws JSONException {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.d("send custom event instantly: eventName=" + eventName + ", jsonData=" + data);
        JSONObject jSONObjectResworbkvmoca = Companion.resworbkvmoca(INSTANCE, eventName, data);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ProductAction.ACTION_DETAIL, jSONObjectResworbkvmoca);
        String str = "window.dispatchEvent(new CustomEvent('VKWebAppEvent', " + jSONObject + "));";
        WebView webView = webView();
        if (webView != null) {
            WebViewExtKt.runJS(webView, "javascript:" + str);
        }
        BaseJsBridge.onSendEvent$default(this, eventName, data, null, 4, null);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendError(@NotNull EventNames event, @NotNull BaseEvent.Error error) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(error, "error");
        JsonObject asJsonObject = this.resworbkvmocf.toJsonTree(error).getAsJsonObject();
        BaseJsBridge.onSendEvent$default(this, EventNames.INSTANCE.getFullName(event), new JSONObject(asJsonObject.toString()), null, 4, null);
        Intrinsics.checkNotNull(asJsonObject);
        sendEvent(asJsonObject);
        this.resworbkvmocd.remove(event);
        WebLogger.INSTANCE.d("Send error to js for event: " + event);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventData(@NotNull JsApiEvent event, @NotNull JSONObject data) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.d("send event: " + event.getFullName() + ", json=" + data);
        resworbkvmoca(Companion.resworbkvmoca(INSTANCE, event.getFullName(), data));
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventFailed(@NotNull JsApiMethodType method) {
        Intrinsics.checkNotNullParameter(method, "method");
        resworbkvmoca(this, method, method.getFailedResult(), VkAppsErrors.INSTANCE.createForApi(), null, 24);
    }

    public final void sendEventInstantly(@NotNull JsApiEvent event, @NotNull JSONObject data) throws JSONException {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(data, "data");
        sendCustomEventInstantly(event.getFullName(), data);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventSuccess(@NotNull JsApiMethodType method, @NotNull JSONObject data, @Nullable String requestId, @Nullable String callArguments) throws JSONException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(data, "data");
        String successResult = method.getSuccessResult();
        this.resworbkvmocg.resworbkvmoca(method, successResult, data);
        onSendEvent(method.getFullName(), data, callArguments);
        resworbkvmoca(method, successResult, data, requestId);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventSuccessWithoutAnalytic(@NotNull JsApiMethodType method, @NotNull JSONObject data, @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(data, "data");
        resworbkvmoca(method, method.getSuccessResult(), data, requestId);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendResponse(@NotNull EventNames event, @NotNull BaseEvent.Response rawResponse) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(rawResponse, "rawResponse");
        String str = this.resworbkvmocd.get(event);
        if (str != null) {
            rawResponse = rawResponse.setRequestId(str);
        }
        JsonObject asJsonObject = this.resworbkvmocf.toJsonTree(rawResponse).getAsJsonObject();
        BaseJsBridge.onSendEvent$default(this, EventNames.INSTANCE.getFullName(event), new JSONObject(asJsonObject.toString()), null, 4, null);
        Intrinsics.checkNotNull(asJsonObject);
        sendEvent(asJsonObject);
        this.resworbkvmocd.remove(event);
        WebLogger.INSTANCE.d("Send event to js for event: " + event);
    }

    public abstract void setWebViewHolder(@Nullable WebClients.Holder holder);

    @Override // com.vk.superapp.base.js.bridge.BaseJsBridge
    @Nullable
    protected WebView webView() {
        WebClients.Holder webViewHolder = getWebViewHolder();
        if (webViewHolder != null) {
            return webViewHolder.getWebView();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(BaseWebBridge baseWebBridge, JSONObject jSONObject) throws JSONException {
        baseWebBridge.getClass();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(ProductAction.ACTION_DETAIL, jSONObject);
        String str = "window.dispatchEvent(new CustomEvent('VKWebAppEvent', " + jSONObject2 + "));";
        WebView webView = baseWebBridge.webView();
        if (webView != null) {
            WebViewExtKt.runJS(webView, "javascript:" + str);
        }
    }

    @Nullable
    public final String getRequestId(@NotNull EventNames event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return this.resworbkvmocd.get(event);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventFailed(@NotNull JsApiMethodType method, @NotNull JSONObject data) throws JSONException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(data, "data");
        resworbkvmoca(this, method, method.getFailedResult(), data, null, 24);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    @Deprecated(message = "This method will be deleted after deletion sa_open_contact_result toggle")
    public void sendEventData(@NotNull JsApiMethodType method, @NotNull String eventName, @NotNull JSONObject data) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(data, "data");
        resworbkvmoca(this, method, eventName, data, null, 24);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventFailed(@NotNull JsApiMethodType method, @NotNull Throwable error, @Nullable String callArguments) throws JSONException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(error, "error");
        resworbkvmoca(this, method, method.getFailedResult(), VkAppsErrors.createForApi$default(VkAppsErrors.INSTANCE, error, null, null, 6, null), callArguments, 8);
    }

    protected void registerJsMethod(@Nullable String requestId, @NotNull EventNames event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.resworbkvmocd.put(event, requestId);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventFailed(@NotNull JsApiMethodType method, @NotNull VkAppsErrors.CustomError error) throws JSONException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(error, "error");
        resworbkvmoca(this, method, method.getFailedResult(), error.toJson(), null, 24);
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendEventFailed(@NotNull JsApiMethodType method, @NotNull VkAppsErrors.Client error, @Nullable String customDescription, @Nullable Pair<String, ? extends Object> additions, @Nullable String requestId, @Nullable String callArguments) throws JSONException {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(error, "error");
        String failedResult = method.getFailedResult();
        JSONObject jSON$default = VkAppsErrors.Client.toJSON$default(error, null, customDescription, additions, 1, null);
        this.resworbkvmocg.resworbkvmoca(method, failedResult, jSON$default);
        onSendEvent(method.getFullName(), jSON$default, callArguments);
        resworbkvmoca(method, failedResult, jSON$default, requestId);
    }

    public boolean onJsApiCalled(@Nullable String data, @NotNull ErrorCreator createError) {
        Intrinsics.checkNotNullParameter(createError, "createError");
        EventNames eventName = createError.getEventName();
        registerJsMethod(getRequestId$browser_release(data), eventName);
        if (!this.resworbkvmocb.isMethodAllowed(eventName.getIsPublic())) {
            sendError(eventName, createError.wrapClientError(EventFactory.createAccessDeniedError$default(EventFactory.INSTANCE, eventName, this, null, 4, null)));
            return false;
        }
        WebLogger webLogger = WebLogger.INSTANCE;
        webLogger.i("call " + eventName.name());
        webLogger.d("data " + data);
        return true;
    }

    @Override // com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void sendError(@NotNull ErrorCreator createError) {
        Intrinsics.checkNotNullParameter(createError, "createError");
        EventFactory.INSTANCE.sendBackgroundError(createError.getEventName(), this, createError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(BaseWebBridge baseWebBridge, JsonObject jsonObject) {
        baseWebBridge.getClass();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add(ProductAction.ACTION_DETAIL, jsonObject);
        String str = "window.dispatchEvent(new CustomEvent('VKWebAppEvent', " + jsonObject2 + "));";
        WebView webView = baseWebBridge.webView();
        if (webView != null) {
            WebViewExtKt.runJS(webView, "javascript:" + str);
        }
    }

    static void resworbkvmoca(BaseWebBridge baseWebBridge, JsApiMethodType jsApiMethodType, String str, JSONObject jSONObject, String str2, int i10) throws JSONException {
        if ((i10 & 16) != 0) {
            str2 = null;
        }
        baseWebBridge.resworbkvmocg.resworbkvmoca(jsApiMethodType, str, jSONObject);
        baseWebBridge.onSendEvent(jsApiMethodType.getFullName(), jSONObject, str2);
        baseWebBridge.resworbkvmoca(jsApiMethodType, str, jSONObject, null);
    }

    private final void resworbkvmoca(JsApiMethodType jsApiMethodType, String str, JSONObject jSONObject, String str2) {
        if (str2 == null) {
            str2 = this.resworbkvmocc.get(jsApiMethodType);
            if (str2 != null) {
                this.resworbkvmocc.remove(jsApiMethodType);
            } else {
                JsMethod methodByFullName = getMethodByFullName(jsApiMethodType.getFullName());
                if (methodByFullName == null) {
                    str2 = null;
                } else {
                    str2 = getRequestId(methodByFullName);
                    clearRequestId(methodByFullName);
                }
            }
        }
        resworbkvmoca(Companion.access$createJsonEvent(INSTANCE, str, jSONObject, str2));
    }
}
