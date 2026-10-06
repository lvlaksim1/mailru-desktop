package com.vk.superapp.api.core;

import com.vk.core.extensions.CollectionExtKt;
import com.vk.core.serialize.Serializer;
import com.vk.superapp.api.internal.WebApiRequest;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 (2\u00020\u0001:\u0001(B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0096\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u001cR#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/vk/superapp/api/core/WebPersistentRequest;", "Lcom/vk/core/serialize/Serializer$StreamParcelableAdapter;", "", "method", "", "params", "Ljava/lang/reflect/Method;", "successCallback", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/reflect/Method;)V", "Lcom/vk/core/serialize/Serializer;", "s", "", "serializeTo", "(Lcom/vk/core/serialize/Serializer;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lcom/vk/superapp/api/internal/WebApiRequest;", "Lorg/json/JSONObject;", "toWebApiRequest", "()Lcom/vk/superapp/api/internal/WebApiRequest;", "toString", "()Ljava/lang/String;", "ipakvmoca", "Ljava/lang/String;", "getMethod", "ipakvmocb", "Ljava/util/Map;", "getParams", "()Ljava/util/Map;", "ipakvmocc", "Ljava/lang/reflect/Method;", "getSuccessCallback", "()Ljava/lang/reflect/Method;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebPersistentRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebPersistentRequest.kt\ncom/vk/superapp/api/core/WebPersistentRequest\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Serializer.kt\ncom/vk/core/serialize/SerializerKt\n*L\n1#1,149:1\n216#2,2:150\n1038#3,4:152\n*S KotlinDebug\n*F\n+ 1 WebPersistentRequest.kt\ncom/vk/superapp/api/core/WebPersistentRequest\n*L\n142#1:150,2\n58#1:152,4\n*E\n"})
public final class WebPersistentRequest extends Serializer.StreamParcelableAdapter {

    /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
    @NotNull
    private final String method;

    /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
    @NotNull
    private final Map<String, String> params;

    /* JADX INFO: renamed from: ipakvmocc, reason: from kotlin metadata */
    @Nullable
    private final Method successCallback;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @JvmField
    @NotNull
    public static final Serializer.Creator<WebPersistentRequest> CREATOR = new Serializer.Creator<WebPersistentRequest>() { // from class: com.vk.superapp.api.core.WebPersistentRequest$special$$inlined$createSerializer$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vk.core.serialize.Serializer.Creator
        public WebPersistentRequest createFromSerializer(Serializer s10) {
            Intrinsics.checkNotNullParameter(s10, "s");
            try {
                String string = s10.readString();
                Intrinsics.checkNotNull(string);
                WebPersistentRequest.Companion companion = WebPersistentRequest.INSTANCE;
                return new WebPersistentRequest(string, WebPersistentRequest.Companion.access$deserializeMap(companion, s10), WebPersistentRequest.Companion.access$deserializeMethod(companion, s10));
            } catch (Throwable unused) {
                return null;
            }
        }

        @Override // android.os.Parcelable.Creator
        public WebPersistentRequest[] newArray(int size) {
            return new WebPersistentRequest[size];
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/core/WebPersistentRequest$Companion;", "", "<init>", "()V", "Lcom/vk/core/serialize/Serializer$Creator;", "Lcom/vk/superapp/api/core/WebPersistentRequest;", "CREATOR", "Lcom/vk/core/serialize/Serializer$Creator;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final Map access$deserializeMap(Companion companion, Serializer serializer) {
            companion.getClass();
            String[] strArrCreateStringArray = serializer.createStringArray();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (strArrCreateStringArray != null) {
                int i10 = 0;
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, strArrCreateStringArray.length - 1, 2);
                if (progressionLastElement >= 0) {
                    while (true) {
                        String str = strArrCreateStringArray[i10];
                        Intrinsics.checkNotNull(str);
                        String str2 = strArrCreateStringArray[i10 + 1];
                        Intrinsics.checkNotNull(str2);
                        linkedHashMap.put(str, str2);
                        if (i10 == progressionLastElement) {
                            break;
                        }
                        i10 += 2;
                    }
                }
            }
            return linkedHashMap;
        }

        public static final Method access$deserializeMethod(Companion companion, Serializer serializer) throws NoSuchMethodException {
            companion.getClass();
            String string = serializer.readString();
            String string2 = serializer.readString();
            if (string == null || string2 == null) {
                return null;
            }
            Method declaredMethod = Class.forName(string).getDeclaredMethod(string2, JSONObject.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        }

        public static final void access$serializeMap(Companion companion, Map map, Serializer serializer) {
            String str;
            String str2;
            companion.getClass();
            Iterator it = map.keySet().iterator();
            int size = map.size() * 2;
            String[] strArr = new String[size];
            String str3 = null;
            int i10 = 0;
            while (i10 < size) {
                if (i10 % 2 == 0) {
                    str2 = (String) it.next();
                    str = str2;
                } else {
                    str = str3;
                    str2 = (String) map.get(str3);
                }
                strArr[i10] = str2;
                i10++;
                str3 = str;
            }
            serializer.writeStringArray(strArr);
        }

        public static final void access$serializeMethod(Companion companion, Method method, Serializer serializer) {
            companion.getClass();
            if (method == null) {
                serializer.writeString(null);
                serializer.writeString(null);
            } else {
                serializer.writeString(method.getDeclaringClass().getName());
                serializer.writeString(method.getName());
            }
        }

        private Companion() {
        }
    }

    public /* synthetic */ WebPersistentRequest(String str, Map map, Method method, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i10 & 4) != 0 ? null : method);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(WebPersistentRequest.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.vk.superapp.api.core.WebPersistentRequest");
        WebPersistentRequest webPersistentRequest = (WebPersistentRequest) other;
        return Intrinsics.areEqual(this.method, webPersistentRequest.method) && CollectionExtKt.equalsTo(this.params, webPersistentRequest.params) && Intrinsics.areEqual(this.successCallback, webPersistentRequest.successCallback);
    }

    @NotNull
    public final String getMethod() {
        return this.method;
    }

    @NotNull
    public final Map<String, String> getParams() {
        return this.params;
    }

    @Nullable
    public final Method getSuccessCallback() {
        return this.successCallback;
    }

    public int hashCode() {
        int iHashCode = this.method.hashCode() * 31;
        Method method = this.successCallback;
        return iHashCode + (method != null ? method.hashCode() : 0);
    }

    @Override // com.vk.core.serialize.Serializer.StreamParcelable
    public void serializeTo(@NotNull Serializer s10) {
        Intrinsics.checkNotNullParameter(s10, "s");
        s10.writeString(this.method);
        Companion companion = INSTANCE;
        Companion.access$serializeMap(companion, this.params, s10);
        Companion.access$serializeMethod(companion, this.successCallback, s10);
    }

    @NotNull
    public String toString() {
        return "PersistentRequest(method='" + this.method + "', params=" + this.params + ", successCallback=" + this.successCallback + ')';
    }

    @NotNull
    public final WebApiRequest<JSONObject> toWebApiRequest() {
        WebApiRequest<JSONObject> webApiRequest = new WebApiRequest<>(this.method);
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            webApiRequest.param(entry.getKey(), entry.getValue());
        }
        return webApiRequest;
    }

    public WebPersistentRequest(@NotNull String method, @NotNull Map<String, String> params, @Nullable Method method2) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(params, "params");
        this.method = method;
        this.params = params;
        this.successCallback = method2;
        params.remove("method");
        params.remove(Logger.METHOD_V);
        params.remove("access_token");
        params.remove("sig");
    }
}
