package com.vk.superapp.base.js.bridge;

import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import com.vk.superapp.core.utils.WebLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001:\u000201B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H$¢\u0006\u0004\b\u0005\u0010\u0006JK\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J5\u0010\u0019\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00162\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001d\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u0004\u0018\u00010\u00072\u0006\u0010!\u001a\u00020\u0011¢\u0006\u0004\b\"\u0010#J\u0017\u0010$\u001a\u0004\u0018\u00010\u00112\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b&\u0010'J+\u0010+\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00112\u0006\u0010*\u001a\u00020)2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0004\b+\u0010,J\u0017\u0010.\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\rH\u0014¢\u0006\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/vk/superapp/base/js/bridge/BaseJsBridge;", "", "<init>", "()V", "Landroid/webkit/WebView;", "webView", "()Landroid/webkit/WebView;", "Lcom/vk/superapp/base/js/bridge/JsMethod;", "method", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;", "response", "Lcom/vk/superapp/base/js/bridge/CustomTypeAdapter;", "customAdapter", "Lcom/vk/superapp/base/js/bridge/CustomAnalyticsData;", "analyticsData", "", "logEvent", "", "callArguments", "", "sendEventSuccess", "(Lcom/vk/superapp/base/js/bridge/JsMethod;Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;Lcom/vk/superapp/base/js/bridge/CustomTypeAdapter;Lcom/vk/superapp/base/js/bridge/CustomAnalyticsData;ZLjava/lang/String;)V", "Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;", "error", "requestId", "sendEventFailed", "(Lcom/vk/superapp/base/js/bridge/JsMethod;Lcom/vk/superapp/base/js/bridge/BaseEvent$Error;Ljava/lang/String;Ljava/lang/String;)V", "sendEventDataInstantly", "(Lcom/vk/superapp/base/js/bridge/JsMethod;Lcom/vk/superapp/base/js/bridge/BaseEvent$Response;)V", "onJsApiCalled", "(Lcom/vk/superapp/base/js/bridge/JsMethod;Ljava/lang/String;)V", "clearRequestId", "(Lcom/vk/superapp/base/js/bridge/JsMethod;)V", "fullName", "getMethodByFullName", "(Ljava/lang/String;)Lcom/vk/superapp/base/js/bridge/JsMethod;", "getRequestId", "(Lcom/vk/superapp/base/js/bridge/JsMethod;)Ljava/lang/String;", "isAlreadyRunning", "(Lcom/vk/superapp/base/js/bridge/JsMethod;)Z", "methodName", "Lorg/json/JSONObject;", "jsonData", "onSendEvent", "(Ljava/lang/String;Lorg/json/JSONObject;Ljava/lang/String;)V", "data", "onSendCustomEvent", "(Lcom/vk/superapp/base/js/bridge/CustomAnalyticsData;)V", "egdirbsjesabkvmoca", "egdirbsjesabkvmocb", "base-js-bridge_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseJsBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseJsBridge.kt\ncom/vk/superapp/base/js/bridge/BaseJsBridge\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,215:1\n1#2:216\n*E\n"})
public abstract class BaseJsBridge {

    @NotNull
    private final ConcurrentHashMap egdirbsjesabkvmoca = new ConcurrentHashMap();

    @NotNull
    private final egdirbsjesabkvmoca egdirbsjesabkvmocb = new egdirbsjesabkvmoca(new egdirbsjesabkvmocb());
    private final Gson egdirbsjesabkvmocc;

    /* JADX INFO: compiled from: ProGuard */
    private static final class egdirbsjesabkvmoca {
        public egdirbsjesabkvmoca(@NotNull egdirbsjesabkvmocb hider) {
            Intrinsics.checkNotNullParameter(hider, "hider");
        }

        public final void egdirbsjesabkvmoca(@NotNull JsMethod method, @NotNull JsonObject jsonData, boolean z10) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JsonObject jsonObjectEgdirbsjesabkvmoca = egdirbsjesabkvmocb.egdirbsjesabkvmoca(jsonData);
            String asString = jsonData.get("type").getAsString();
            String str = z10 ? "send event instantly" : "send event";
            WebLogger.INSTANCE.d(str + ": " + method.getFullName() + ", eventName=" + asString + " json=" + jsonObjectEgdirbsjesabkvmoca);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @SourceDebugExtension({"SMAP\nBaseJsBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseJsBridge.kt\ncom/vk/superapp/base/js/bridge/BaseJsBridge$EventSensitiveDataHider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,215:1\n774#2:216\n865#2,2:217\n1869#2,2:219\n*S KotlinDebug\n*F\n+ 1 BaseJsBridge.kt\ncom/vk/superapp/base/js/bridge/BaseJsBridge$EventSensitiveDataHider\n*L\n199#1:216\n199#1:217,2\n200#1:219,2\n*E\n"})
    private static final class egdirbsjesabkvmocb {

        @NotNull
        private static final List<String> egdirbsjesabkvmoca = CollectionsKt.listOf((Object[]) new String[]{"access_token", "token", AccountManagerRepositoryImpl.SECRET_ARG});

        @NotNull
        public static JsonObject egdirbsjesabkvmoca(@NotNull JsonObject jsonData) {
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            JsonObject jsonObjectDeepCopy = jsonData.deepCopy();
            List<String> list = egdirbsjesabkvmoca;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (jsonObjectDeepCopy.has((String) obj)) {
                    arrayList.add(obj);
                }
            }
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj2 = arrayList.get(i10);
                i10++;
                jsonObjectDeepCopy.addProperty((String) obj2, "HIDE");
            }
            Intrinsics.checkNotNull(jsonObjectDeepCopy);
            return jsonObjectDeepCopy;
        }
    }

    public BaseJsBridge() {
        GsonBuilder gsonBuilderRegisterTypeHierarchyAdapter = new GsonBuilder().registerTypeAdapter(Responses.ClientError.class, VkClientErrorSerializer.INSTANCE).registerTypeHierarchyAdapter(BaseEvent.Error.Data.class, ErrorDataSerializer.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(gsonBuilderRegisterTypeHierarchyAdapter, "registerTypeHierarchyAdapter(...)");
        this.egdirbsjesabkvmocc = gsonBuilderRegisterTypeHierarchyAdapter.create();
    }

    private final void egdirbsjesabkvmoca(final JsonObject jsonObject) {
        WebView webView = webView();
        if (webView != null) {
            webView.post(new Runnable() { // from class: com.vk.superapp.base.js.bridge.a
                @Override // java.lang.Runnable
                public final void run() {
                    BaseJsBridge.egdirbsjesabkvmoca(this.f52053a, jsonObject);
                }
            });
        }
    }

    private final void egdirbsjesabkvmocb(JsonObject jsonObject) {
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add(ProductAction.ACTION_DETAIL, jsonObject);
        String str = "window.dispatchEvent(new CustomEvent('VKWebAppEvent', " + jsonObject2 + "));";
        WebView webView = webView();
        if (webView != null) {
            String str2 = "javascript:" + str;
            try {
                webView.evaluateJavascript(str2, null);
            } catch (Exception unused) {
                webView.loadUrl("javascript:" + str2);
            }
        }
    }

    public static /* synthetic */ void onSendEvent$default(BaseJsBridge baseJsBridge, String str, JSONObject jSONObject, String str2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onSendEvent");
        }
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        baseJsBridge.onSendEvent(str, jSONObject, str2);
    }

    public static /* synthetic */ void sendEventFailed$default(BaseJsBridge baseJsBridge, JsMethod jsMethod, BaseEvent.Error error, String str, String str2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEventFailed");
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        baseJsBridge.sendEventFailed(jsMethod, error, str, str2);
    }

    public static /* synthetic */ void sendEventSuccess$default(BaseJsBridge baseJsBridge, JsMethod jsMethod, BaseEvent.Response response, CustomTypeAdapter customTypeAdapter, CustomAnalyticsData customAnalyticsData, boolean z10, String str, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEventSuccess");
        }
        if ((i10 & 4) != 0) {
            customTypeAdapter = null;
        }
        if ((i10 & 8) != 0) {
            customAnalyticsData = null;
        }
        if ((i10 & 16) != 0) {
            z10 = true;
        }
        if ((i10 & 32) != 0) {
            str = null;
        }
        baseJsBridge.sendEventSuccess(jsMethod, response, customTypeAdapter, customAnalyticsData, z10, str);
    }

    public final void clearRequestId(@NotNull JsMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.egdirbsjesabkvmoca.remove(method);
    }

    @Nullable
    public final JsMethod getMethodByFullName(@NotNull String fullName) {
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Iterator it = this.egdirbsjesabkvmoca.entrySet().iterator();
        while (it.hasNext()) {
            JsMethod jsMethod = (JsMethod) ((Map.Entry) it.next()).getKey();
            if (Intrinsics.areEqual(jsMethod.getFullName(), fullName)) {
                return jsMethod;
            }
        }
        return null;
    }

    @Nullable
    public final String getRequestId(@NotNull JsMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return (String) this.egdirbsjesabkvmoca.get(method);
    }

    public boolean isAlreadyRunning(@NotNull JsMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return this.egdirbsjesabkvmoca.get(method) != null;
    }

    public void onJsApiCalled(@NotNull JsMethod method, @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        this.egdirbsjesabkvmoca.put(method, requestId);
    }

    protected void onSendCustomEvent(@NotNull CustomAnalyticsData data) {
        Intrinsics.checkNotNullParameter(data, "data");
    }

    protected void onSendEvent(@NotNull String methodName, @NotNull JSONObject jsonData, @Nullable String callArguments) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
    }

    public final void sendEventDataInstantly(@NotNull JsMethod method, @NotNull BaseEvent.Response response) {
        BaseJsBridge baseJsBridge;
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(response, "response");
        JsonObject asJsonObject = this.egdirbsjesabkvmocc.toJsonTree(response).getAsJsonObject();
        JsonElement jsonElement = asJsonObject.get("data");
        JsonObject asJsonObject2 = jsonElement != null ? jsonElement.getAsJsonObject() : null;
        Intrinsics.checkNotNull(asJsonObject);
        egdirbsjesabkvmoca(asJsonObject, method, null);
        if (asJsonObject2 != null) {
            egdirbsjesabkvmoca(asJsonObject2, method, null);
        }
        if (asJsonObject2 != null) {
            baseJsBridge = this;
            onSendEvent$default(baseJsBridge, method.getFullName(), new JSONObject(asJsonObject.toString()), null, 4, null);
        } else {
            baseJsBridge = this;
        }
        baseJsBridge.egdirbsjesabkvmocb.egdirbsjesabkvmoca(method, asJsonObject, true);
        egdirbsjesabkvmocb(asJsonObject);
        baseJsBridge.egdirbsjesabkvmoca.remove(method);
    }

    public final void sendEventFailed(@NotNull JsMethod method, @NotNull BaseEvent.Error error, @Nullable String callArguments, @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(error, "error");
        JsonObject asJsonObject = this.egdirbsjesabkvmocc.toJsonTree(error).getAsJsonObject();
        JsonElement jsonElement = asJsonObject.get("data");
        JsonObject asJsonObject2 = jsonElement != null ? jsonElement.getAsJsonObject() : null;
        if (requestId == null) {
            requestId = (String) this.egdirbsjesabkvmoca.get(method);
            clearRequestId(method);
        }
        Intrinsics.checkNotNull(asJsonObject);
        egdirbsjesabkvmoca(asJsonObject, method, requestId);
        if (asJsonObject2 != null) {
            egdirbsjesabkvmoca(asJsonObject2, method, requestId);
        }
        if (asJsonObject2 != null) {
            onSendEvent(method.getFullName(), new JSONObject(asJsonObject2.toString()), callArguments);
        }
        this.egdirbsjesabkvmocb.egdirbsjesabkvmoca(method, asJsonObject, false);
        egdirbsjesabkvmoca(asJsonObject);
    }

    public final void sendEventSuccess(@NotNull JsMethod method, @NotNull BaseEvent.Response response, @Nullable CustomTypeAdapter customAdapter, @Nullable CustomAnalyticsData analyticsData, boolean logEvent, @Nullable String callArguments) {
        Gson gsonCreate;
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(response, "response");
        if (customAdapter != null) {
            GsonBuilder gsonBuilderRegisterTypeHierarchyAdapter = new GsonBuilder().registerTypeAdapter(Responses.ClientError.class, VkClientErrorSerializer.INSTANCE).registerTypeHierarchyAdapter(BaseEvent.Error.Data.class, ErrorDataSerializer.INSTANCE);
            Intrinsics.checkNotNullExpressionValue(gsonBuilderRegisterTypeHierarchyAdapter, "registerTypeHierarchyAdapter(...)");
            gsonCreate = gsonBuilderRegisterTypeHierarchyAdapter.registerTypeAdapter(customAdapter.getType(), customAdapter.getSerializer()).create();
            Intrinsics.checkNotNullExpressionValue(gsonCreate, "create(...)");
        } else {
            gsonCreate = this.egdirbsjesabkvmocc;
        }
        JsonObject asJsonObject = gsonCreate.toJsonTree(response).getAsJsonObject();
        JsonElement jsonElement = asJsonObject.get("data");
        JsonObject asJsonObject2 = jsonElement != null ? jsonElement.getAsJsonObject() : null;
        Intrinsics.checkNotNull(asJsonObject);
        egdirbsjesabkvmoca(asJsonObject, method, null);
        if (asJsonObject2 != null) {
            egdirbsjesabkvmoca(asJsonObject2, method, null);
        }
        if (analyticsData != null) {
            onSendCustomEvent(analyticsData);
        }
        if (asJsonObject2 != null) {
            onSendEvent(method.getFullName(), new JSONObject(asJsonObject2.toString()), callArguments);
        }
        if (logEvent) {
            this.egdirbsjesabkvmocb.egdirbsjesabkvmoca(method, asJsonObject, false);
        }
        egdirbsjesabkvmoca(asJsonObject);
        this.egdirbsjesabkvmoca.remove(method);
    }

    @Nullable
    protected abstract WebView webView();

    /* JADX INFO: Access modifiers changed from: private */
    public static final void egdirbsjesabkvmoca(BaseJsBridge baseJsBridge, JsonObject jsonObject) {
        baseJsBridge.egdirbsjesabkvmocb(jsonObject);
    }

    private final void egdirbsjesabkvmoca(JsonObject jsonObject, JsMethod jsMethod, String str) {
        if (str == null) {
            str = (String) this.egdirbsjesabkvmoca.get(jsMethod);
        }
        if (jsonObject.has(VkUiActivityResultDelegate.KEY_REQUEST_ID) || str == null) {
            return;
        }
        jsonObject.addProperty(VkUiActivityResultDelegate.KEY_REQUEST_ID, str);
    }
}
