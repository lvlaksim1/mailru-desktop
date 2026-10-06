package com.vk.superapp.core.errors;

import android.os.Bundle;
import com.google.android.gms.ads.RequestConfiguration;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.superapp.advertisement.api.dto.AdsRequestError;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ9\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000e\u0010\u0013J\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u0014J7\u0010\u0017\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u001c\u0010\u001bJ@\u0010#\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u001d*\u00028\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020 0\u001f¢\u0006\u0002\b!H\u0086\bø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010%\u001a\u00020\r¢\u0006\u0004\b'\u0010(J\u0015\u0010)\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\r¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00108\u0000X\u0080T¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00108\u0000X\u0080T¢\u0006\u0006\n\u0004\b/\u0010.\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00063"}, d2 = {"Lcom/vk/superapp/core/errors/VkAppsErrors;", "", "<init>", "()V", "", "e", "Lcom/vk/superapp/core/errors/VkAppsErrors$Client;", "provideForApi", "(Ljava/lang/Throwable;)Lcom/vk/superapp/core/errors/VkAppsErrors$Client;", "", "", "requestParams", "requestId", "Lorg/json/JSONObject;", "createForApi", "(Ljava/lang/Throwable;Ljava/util/Map;Ljava/lang/String;)Lorg/json/JSONObject;", "", "code", "description", "(ILjava/lang/String;)Lorg/json/JSONObject;", "()Lorg/json/JSONObject;", "error", "reason", "createForAuth", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;", "", "isIOError", "(I)Z", "isCodeConfirmationRequiredError", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "value", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "block", "applyIf", "(Ljava/lang/Object;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "data", "Lcom/vk/superapp/core/errors/VkAppsErrors$ErrorTypes;", "getErrorType", "(Lorg/json/JSONObject;)Lcom/vk/superapp/core/errors/VkAppsErrors$ErrorTypes;", "getErrorCode", "(Lorg/json/JSONObject;)I", "getErrorMessage", "(Ljava/lang/Throwable;)Ljava/lang/String;", "ERROR_IO", "I", "ERROR_CODE_CONFIRMATION_REQUIRED", "Client", "CustomError", "ErrorTypes", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVkAppsErrors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VkAppsErrors.kt\ncom/vk/superapp/core/errors/VkAppsErrors\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,240:1\n212#1,3:247\n8704#2,2:241\n8964#2,4:243\n1#3:250\n*S KotlinDebug\n*F\n+ 1 VkAppsErrors.kt\ncom/vk/superapp/core/errors/VkAppsErrors\n*L\n204#1:247,3\n195#1:241,2\n195#1:243,4\n*E\n"})
public final class VkAppsErrors {
    public static final int ERROR_CODE_CONFIRMATION_REQUIRED = 24;
    public static final int ERROR_IO = -1;

    @NotNull
    public static final VkAppsErrors INSTANCE = new VkAppsErrors();

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v4 com.vk.superapp.core.errors.VkAppsErrors$Client[], still in use, count: 1, list:
      (r0v4 com.vk.superapp.core.errors.VkAppsErrors$Client[]) from 0x00c2: INVOKE (r0v4 com.vk.superapp.core.errors.VkAppsErrors$Client[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:195)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001c\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001J=\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0010\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0017\u0010\u0014R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0018\u0010\u0014j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&¨\u0006'"}, d2 = {"Lcom/vk/superapp/core/errors/VkAppsErrors$Client;", "", "", "requestId", "customDescription", "Lkotlin/Pair;", "", "additions", "Lorg/json/JSONObject;", "toJSON", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/Pair;)Lorg/json/JSONObject;", "", "erockvmoca", "I", "getCode", "()I", "code", "erockvmocb", "Ljava/lang/String;", "getReason", "()Ljava/lang/String;", "reason", "description", "getDescription", "getRequestId", AnalyticsErrorType.UNKNOWN_ERROR, "MISSING_PARAMS", "CONNECTION_LOST", "USER_DENIED", "INVALID_PARAMS", "UNSUPPORTED_PLATFORM", "NO_PERMISSIONS", "NEED_USER_PERMISSIONS", "INACTIVE_SCREEN", "LIMIT_REACHED", "ACCESS_DENIED", "CUSTOM_ERROR", "ALREADY_IN_PROGRESS", "NO_ADS", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Client {
        UNKNOWN_ERROR(1, "Unknown error"),
        MISSING_PARAMS(2, "Missing required params"),
        CONNECTION_LOST(3, "Connection lost"),
        USER_DENIED(4, "User denied"),
        INVALID_PARAMS(5, AdsRequestError.Message.INVALID_PARAMS),
        UNSUPPORTED_PLATFORM(6, "Unsupported platform"),
        NO_PERMISSIONS(7, "No device permission"),
        NEED_USER_PERMISSIONS(8, "Need user permission"),
        INACTIVE_SCREEN(9, "This action cannot be performed in the background"),
        LIMIT_REACHED(10, AdsRequestError.Message.REQUESTS_LIMIT_REACHED),
        ACCESS_DENIED(11, "Access denied"),
        CUSTOM_ERROR(13, "Custom error"),
        ALREADY_IN_PROGRESS(14, "Request already in progress"),
        NO_ADS(20, "No ads");

        private static final /* synthetic */ EnumEntries erockvmocd;

        /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
        private final int code;

        /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
        @NotNull
        private final String reason;

        static {
            erockvmocd = EnumEntriesKt.enumEntries(clientArr);
        }

        private Client() {
            throw null;
        }

        @NotNull
        public static EnumEntries<Client> getEntries() {
            return erockvmocd;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ JSONObject toJSON$default(Client client, String str, String str2, Pair pair, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toJSON");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                pair = null;
            }
            return client.toJSON(str, str2, pair);
        }

        public static Client valueOf(String str) {
            return (Client) Enum.valueOf(Client.class, str);
        }

        public static Client[] values() {
            return (Client[]) erockvmocc.clone();
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final String getDescription() {
            return null;
        }

        @NotNull
        public final String getReason() {
            return this.reason;
        }

        @Nullable
        public final String getRequestId() {
            return null;
        }

        @NotNull
        public final JSONObject toJSON(@Nullable String requestId, @Nullable String customDescription, @Nullable Pair<String, ? extends Object> additions) throws JSONException {
            JSONObject jSONObjectPut = new JSONObject().put("error_code", this.code).put("error_reason", this.reason);
            if (customDescription != null) {
                jSONObjectPut.put("error_description", customDescription);
            }
            if (additions != null) {
                jSONObjectPut.put(additions.getFirst(), additions.getSecond());
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error_type", ErrorTypes.CLIENT.getType());
            jSONObject.put("error_data", jSONObjectPut);
            if (requestId != null && !StringsKt.isBlank(requestId)) {
                jSONObject.put(VkUiActivityResultDelegate.KEY_REQUEST_ID, requestId);
            }
            return jSONObject;
        }

        Client(int i10, String str) {
            super(str, i);
            this.code = i10;
            this.reason = str;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.superapp.core.errors.VkAppsErrors$ErrorTypes[], still in use, count: 1, list:
      (r0v1 com.vk.superapp.core.errors.VkAppsErrors$ErrorTypes[]) from 0x002a: INVOKE (r0v1 com.vk.superapp.core.errors.VkAppsErrors$ErrorTypes[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:43)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/core/errors/VkAppsErrors$ErrorTypes;", "", "", "erockvmoca", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "type", "CLIENT", "API", "AUTH", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ErrorTypes {
        CLIENT("client_error"),
        API("api_error"),
        AUTH("auth_error");

        private static final /* synthetic */ EnumEntries erockvmocc;

        /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
        @NotNull
        private final String type;

        static {
            erockvmocc = EnumEntriesKt.enumEntries(errorTypesArr);
        }

        private ErrorTypes(String str) {
            super(str, i);
            this.type = str;
        }

        @NotNull
        public static EnumEntries<ErrorTypes> getEntries() {
            return erockvmocc;
        }

        public static ErrorTypes valueOf(String str) {
            return (ErrorTypes) Enum.valueOf(ErrorTypes.class, str);
        }

        public static ErrorTypes[] values() {
            return (ErrorTypes[]) erockvmocb.clone();
        }

        @NotNull
        public final String getType() {
            return this.type;
        }
    }

    private VkAppsErrors() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JSONObject createForApi$default(VkAppsErrors vkAppsErrors, Throwable th2, Map map, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = null;
        }
        if ((i10 & 4) != 0) {
            str = null;
        }
        return vkAppsErrors.createForApi(th2, map, str);
    }

    public static /* synthetic */ JSONObject createForAuth$default(VkAppsErrors vkAppsErrors, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        return vkAppsErrors.createForAuth(str, str2, str3, str4);
    }

    private static JSONObject erockvmoca(int i10, String str, Map map, String str2, Bundle bundle) throws JSONException {
        Set<String> setEmptySet;
        JSONObject jSONObjectPut = new JSONObject().put("error_code", i10).put("error_msg", str);
        if (bundle == null || (setEmptySet = bundle.keySet()) == null) {
            setEmptySet = SetsKt.emptySet();
        }
        for (String str3 : setEmptySet) {
            if (!Intrinsics.areEqual(str3, "access_token")) {
                jSONObjectPut.put(str3, bundle != null ? bundle.get(str3) : null);
            }
        }
        if (map != null) {
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry entry : map.entrySet()) {
                if (!Intrinsics.areEqual(entry.getKey(), "access_token")) {
                    jSONArray.put(new JSONObject().put("key", entry.getKey()).put("value", entry.getValue()));
                }
            }
            jSONObjectPut.put("request_params", jSONArray);
        }
        JSONObject jSONObjectPut2 = new JSONObject().put("error_type", ErrorTypes.API.getType()).put("error_data", jSONObjectPut).put(VkUiActivityResultDelegate.KEY_REQUEST_ID, str2);
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut2, "put(...)");
        return jSONObjectPut2;
    }

    public final <T> T applyIf(T t10, @Nullable Boolean bool, @NotNull Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            block.invoke(t10);
        }
        return t10;
    }

    @NotNull
    public final JSONObject createForApi(@NotNull Throwable e10, @Nullable Map<String, String> requestParams, @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(e10, "e");
        boolean z10 = e10 instanceof VKApiExecutionException;
        if (z10 && ((VKApiExecutionException) e10).getCode() == -1) {
            return Client.toJSON$default(Client.CONNECTION_LOST, requestId, null, null, 6, null);
        }
        if (z10 && ((VKApiExecutionException) e10).getCode() == 24) {
            return Client.toJSON$default(Client.USER_DENIED, requestId, null, null, 6, null);
        }
        if (!z10) {
            return e10 instanceof JSONException ? Client.toJSON$default(Client.INVALID_PARAMS, requestId, null, null, 6, null) : Client.toJSON$default(Client.UNKNOWN_ERROR, requestId, null, null, 6, null);
        }
        VKApiExecutionException vKApiExecutionException = (VKApiExecutionException) e10;
        String errorMsg = vKApiExecutionException.getCode() == 14 ? vKApiExecutionException.getErrorMsg() : vKApiExecutionException.getErrorMsg();
        int code = vKApiExecutionException.getCode();
        if (requestParams == null) {
            requestParams = vKApiExecutionException.getRequestParams();
        }
        return erockvmoca(code, errorMsg, requestParams, requestId, vKApiExecutionException.getExtra());
    }

    @NotNull
    public final JSONObject createForAuth(@Nullable String description, @Nullable String error, @Nullable String reason, @Nullable String requestId) throws JSONException {
        Client[] clientArrValues = Client.values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(clientArrValues.length), 16));
        for (Client client : clientArrValues) {
            linkedHashMap.put(client.getDescription(), client);
        }
        JSONObject jSONObject = new JSONObject();
        Client client2 = (Client) linkedHashMap.get(error);
        if (client2 == null) {
            client2 = Client.UNKNOWN_ERROR;
        }
        JSONObject jSONObjectPut = new JSONObject().put("error_type", ErrorTypes.AUTH.getType()).put("error_data", jSONObject.put("error", client2.getCode()).put("error_description", description).put("error_reason", reason));
        if (!(requestId == null || StringsKt.isBlank(requestId))) {
            jSONObjectPut.put(VkUiActivityResultDelegate.KEY_REQUEST_ID, requestId);
        }
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "applyIf(...)");
        return jSONObjectPut;
    }

    public final int getErrorCode(@NotNull JSONObject data) {
        Intrinsics.checkNotNullParameter(data, "data");
        JSONObject jSONObjectOptJSONObject = data.optJSONObject("error_data");
        return jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optInt("error_code", Client.UNKNOWN_ERROR.getCode()) : Client.UNKNOWN_ERROR.getCode();
    }

    @Nullable
    public final String getErrorMessage(@NotNull Throwable e10) {
        Intrinsics.checkNotNullParameter(e10, "e");
        return e10 instanceof VKApiExecutionException ? ((VKApiExecutionException) e10).getErrorMsg() : e10.getMessage();
    }

    @Nullable
    public final ErrorTypes getErrorType(@NotNull JSONObject data) {
        Intrinsics.checkNotNullParameter(data, "data");
        String strOptString = data.optString("error_type");
        Intrinsics.checkNotNull(strOptString);
        if (strOptString.length() > 0) {
            for (ErrorTypes errorTypes : ErrorTypes.values()) {
                if (Intrinsics.areEqual(errorTypes.getType(), strOptString)) {
                    return errorTypes;
                }
            }
        }
        return null;
    }

    public final boolean isCodeConfirmationRequiredError(int code) {
        return code == 24;
    }

    public final boolean isIOError(int code) {
        return code == -1;
    }

    @NotNull
    public final Client provideForApi(@NotNull Throwable e10) {
        Intrinsics.checkNotNullParameter(e10, "e");
        boolean z10 = e10 instanceof VKApiExecutionException;
        if (z10 && ((VKApiExecutionException) e10).getCode() == -1) {
            return Client.CONNECTION_LOST;
        }
        if (z10 && ((VKApiExecutionException) e10).getCode() == 24) {
            return Client.USER_DENIED;
        }
        return e10 instanceof JSONException ? Client.INVALID_PARAMS : Client.UNKNOWN_ERROR;
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u000fJ0\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u000fJ\u0010\u0010\u0014\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0014\u0010\rJ\u001a\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u000fR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001d\u001a\u0004\b \u0010\u000f¨\u0006!"}, d2 = {"Lcom/vk/superapp/core/errors/VkAppsErrors$CustomError;", "", "", "code", "", "reason", "description", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "Lorg/json/JSONObject;", "toJson", "()Lorg/json/JSONObject;", "component1", "()I", "component2", "()Ljava/lang/String;", "component3", "copy", "(ILjava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/core/errors/VkAppsErrors$CustomError;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "erockvmoca", "I", "getCode", "erockvmocb", "Ljava/lang/String;", "getReason", "erockvmocc", "getDescription", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class CustomError {

        /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
        private final int code;

        /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
        @NotNull
        private final String reason;

        /* JADX INFO: renamed from: erockvmocc, reason: from kotlin metadata */
        @Nullable
        private final String description;

        public CustomError(int i10, @NotNull String reason, @Nullable String str) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.code = i10;
            this.reason = reason;
            this.description = str;
        }

        public static /* synthetic */ CustomError copy$default(CustomError customError, int i10, String str, String str2, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = customError.code;
            }
            if ((i11 & 2) != 0) {
                str = customError.reason;
            }
            if ((i11 & 4) != 0) {
                str2 = customError.description;
            }
            return customError.copy(i10, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final CustomError copy(int code, @NotNull String reason, @Nullable String description) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new CustomError(code, reason, description);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomError)) {
                return false;
            }
            CustomError customError = (CustomError) other;
            return this.code == customError.code && Intrinsics.areEqual(this.reason, customError.reason) && Intrinsics.areEqual(this.description, customError.description);
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final String getReason() {
            return this.reason;
        }

        public int hashCode() {
            int iHashCode = (this.reason.hashCode() + (Integer.hashCode(this.code) * 31)) * 31;
            String str = this.description;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public final JSONObject toJson() throws JSONException {
            JSONObject jSONObjectPut = new JSONObject().put("error_code", this.code).put("error_reason", this.reason);
            String str = this.description;
            if (str != null) {
                jSONObjectPut.put("error_description", str);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error_type", ErrorTypes.CLIENT.getType());
            jSONObject.put("error_data", jSONObjectPut);
            return jSONObject;
        }

        @NotNull
        public String toString() {
            return "CustomError(code=" + this.code + ", reason=" + this.reason + ", description=" + this.description + ')';
        }

        public /* synthetic */ CustomError(int i10, String str, String str2, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(i10, str, (i11 & 4) != 0 ? null : str2);
        }
    }

    @NotNull
    public final JSONObject createForApi(int code, @Nullable String description) {
        return erockvmoca(code, description, null, null, Bundle.EMPTY);
    }

    @NotNull
    public final JSONObject createForApi() throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("error_type", ErrorTypes.API.getType());
        Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "put(...)");
        return jSONObjectPut;
    }
}
