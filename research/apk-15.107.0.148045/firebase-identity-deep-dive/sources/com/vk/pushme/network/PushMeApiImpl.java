package com.vk.pushme.network;

import com.vk.pushme.network.model.request.InternalSubscriptionRequest;
import com.vk.pushme.network.model.request.SubscriptionRequest;
import com.vk.pushme.network.model.response.SubscriptionResponse;
import com.vk.pushme.network.model.response.UnsubscribeResponse;
import com.vk.pushme.network.model.result.SubscriptionResult;
import com.vk.pushme.network.model.result.UnsubscribeResult;
import com.vk.pushme.network.util.CallHandlerKt;
import com.vk.pushme.network.util.CallHandlerKt$handleCall$result$responseData$1;
import com.vk.pushme.network.util.ExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0096@¢\u0006\u0002\u0010\rJ\u001c\u0010\u000e\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0096@¢\u0006\u0002\u0010\rJ$\u0010\u000f\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@¢\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0018H\u0096@¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020 H\u0002J\u0014\u0010!\u001a\u00020\"*\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0002J\u0010\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u00162\u0006\u0010%\u001a\u00020(H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/vk/pushme/network/PushMeApiImpl;", "Lcom/vk/pushme/network/PushMeApi;", "okHttpClient", "Lokhttp3/OkHttpClient;", "hostInfoProvider", "Lcom/vk/pushme/network/HostInfoProvider;", "<init>", "(Lokhttp3/OkHttpClient;Lcom/vk/pushme/network/HostInfoProvider;)V", "setSettingsV1", "Lcom/vk/pushme/network/model/result/SubscriptionResult;", "subscriptions", "", "Lcom/vk/pushme/network/model/request/SubscriptionRequest;", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSettingsV2", "setSettingsInternal", "version", "Lcom/vk/pushme/network/ApiVersion;", "(Ljava/util/Collection;Lcom/vk/pushme/network/ApiVersion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapSubscriptions", "Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;", "unsubscribeByDeviceId", "Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "account", "", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "application", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unsubscribeByToken", "token", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlBuilder", "Lokhttp3/HttpUrl$Builder;", "combineWith", "Lkotlinx/serialization/json/JsonObject;", "other", "parseSubscriptionResponseToResult", "response", "Lcom/vk/pushme/network/model/response/SubscriptionResponse;", "parseUnsubscribeResponseToResult", "Lcom/vk/pushme/network/model/response/UnsubscribeResponse;", "Companion", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMeApiImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeApiImpl.kt\ncom/vk/pushme/network/PushMeApiImpl\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,232:1\n205#2:233\n222#2:239\n222#2:269\n222#2:284\n19#3,5:234\n24#3,9:240\n19#3,5:264\n24#3,9:270\n19#3,5:279\n24#3,9:285\n1563#4:249\n1634#4,2:250\n1563#4:256\n1634#4,3:257\n1636#4:263\n1563#4:294\n1634#4,3:295\n1563#4:298\n1634#4,3:299\n827#4:302\n855#4,2:303\n1563#4:305\n1634#4,3:306\n29#5,2:252\n29#5,2:254\n31#5:260\n31#5:261\n1#6:262\n*S KotlinDebug\n*F\n+ 1 PushMeApiImpl.kt\ncom/vk/pushme/network/PushMeApiImpl\n*L\n50#1:233\n57#1:239\n122#1:269\n151#1:284\n56#1:234,5\n56#1:240,9\n121#1:264,5\n121#1:270,9\n150#1:279,5\n150#1:285,9\n67#1:249\n67#1:250,2\n70#1:256\n70#1:257,3\n67#1:263\n174#1:294\n174#1:295,3\n175#1:298\n175#1:299,3\n213#1:302\n213#1:303,2\n213#1:305\n213#1:306,3\n68#1:252,2\n69#1:254,2\n69#1:260\n68#1:261\n*E\n"})
public final class PushMeApiImpl implements PushMeApi {
    private static final int CODE_OK = 0;

    @NotNull
    private final HostInfoProvider hostInfoProvider;

    @NotNull
    private final OkHttpClient okHttpClient;

    /* JADX INFO: renamed from: com.vk.pushme.network.PushMeApiImpl$setSettingsInternal$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.network.PushMeApiImpl", f = "PushMeApiImpl.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {235, 237}, m = "setSettingsInternal", n = {"subscriptions", "version", "url", "mappedSubscriptions", "jsonBody", Event.Companion.Network.Fail.REQUEST_TAG, "$this$handleCall$iv", "$i$f$handleCall", "subscriptions", "version", "url", "mappedSubscriptions", "jsonBody", Event.Companion.Network.Fail.REQUEST_TAG, "$this$handleCall$iv", "response$iv", "$i$f$handleCall"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushMeApiImpl.this.setSettingsInternal(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.network.PushMeApiImpl$unsubscribeByDeviceId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.network.PushMeApiImpl", f = "PushMeApiImpl.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {234, 236}, m = "unsubscribeByDeviceId", n = {"account", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "application", "url", "body", "okHttpRequest", "$this$handleCall$iv", "$i$f$handleCall", "account", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "application", "url", "body", "okHttpRequest", "$this$handleCall$iv", "response$iv", "$i$f$handleCall"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0"}, v = 1)
    static final class C10781 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C10781(Continuation<? super C10781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushMeApiImpl.this.unsubscribeByDeviceId(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.network.PushMeApiImpl$unsubscribeByToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.network.PushMeApiImpl", f = "PushMeApiImpl.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {234, 236}, m = "unsubscribeByToken", n = {"token", "application", "url", "body", "okHttpRequest", "$this$handleCall$iv", "$i$f$handleCall", "token", "application", "url", "body", "okHttpRequest", "$this$handleCall$iv", "response$iv", "$i$f$handleCall"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"}, v = 1)
    static final class C10791 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C10791(Continuation<? super C10791> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushMeApiImpl.this.unsubscribeByToken(null, null, this);
        }
    }

    public PushMeApiImpl(@NotNull OkHttpClient okHttpClient, @NotNull HostInfoProvider hostInfoProvider) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(hostInfoProvider, "hostInfoProvider");
        this.okHttpClient = okHttpClient;
        this.hostInfoProvider = hostInfoProvider;
    }

    private final JsonObject combineWith(JsonObject jsonObject, JsonObject jsonObject2) {
        Set<Map.Entry<String, JsonElement>> setEntrySet = jsonObject.entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            arrayList.add((String) ((Map.Entry) it.next()).getKey());
        }
        Set set = CollectionsKt.toSet(arrayList);
        Set<Map.Entry<String, JsonElement>> setEntrySet2 = jsonObject2.entrySet();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet2, 10));
        Iterator<T> it2 = setEntrySet2.iterator();
        while (it2.hasNext()) {
            arrayList2.add((String) ((Map.Entry) it2.next()).getKey());
        }
        Set<String> setIntersect = CollectionsKt.intersect(set, CollectionsKt.toSet(arrayList2));
        Map map = MapsKt.toMap(jsonObject);
        Map map2 = MapsKt.toMap(jsonObject2);
        if (setIntersect.isEmpty()) {
            return new JsonObject(MapsKt.plus(map, map2));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : map.keySet()) {
            if (!setIntersect.contains(str)) {
                Pair pair = TuplesKt.to(str, MapsKt.getValue(map, str));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
        }
        for (String str2 : map2.keySet()) {
            if (!setIntersect.contains(str2)) {
                Pair pair2 = TuplesKt.to(str2, MapsKt.getValue(map2, str2));
                linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
            }
        }
        for (String str3 : setIntersect) {
            JsonElement jsonElement = (JsonElement) MapsKt.getValue(map, str3);
            Object objCombineWith = (JsonElement) MapsKt.getValue(map2, str3);
            if ((jsonElement instanceof JsonObject) && (objCombineWith instanceof JsonObject)) {
                objCombineWith = combineWith((JsonObject) jsonElement, (JsonObject) objCombineWith);
            }
            Pair pair3 = TuplesKt.to(str3, objCombineWith);
            linkedHashMap.put(pair3.getFirst(), pair3.getSecond());
        }
        return new JsonObject(linkedHashMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, kotlinx.serialization.json.JsonObject] */
    /* JADX WARN: Type inference failed for: r5v5, types: [T, kotlinx.serialization.json.JsonObject] */
    private final Collection<InternalSubscriptionRequest> mapSubscriptions(Collection<SubscriptionRequest> subscriptions) {
        Collection<SubscriptionRequest> collection = subscriptions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (SubscriptionRequest subscriptionRequest : collection) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            Set<Integer> tags = subscriptionRequest.getSettings().getTags();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(tags, 10));
            Iterator<T> it = tags.iterator();
            while (it.hasNext()) {
                arrayList2.add(ExtensionsKt.toJsonElement(Integer.valueOf(((Number) it.next()).intValue())));
            }
            JsonArray jsonArray = new JsonArray(arrayList2);
            if (!jsonArray.isEmpty()) {
                jsonObjectBuilder2.put(ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, jsonArray);
            }
            jsonObjectBuilder.put("capabilities", jsonObjectBuilder2.build());
            jsonObjectBuilder.put(PreferenceHostProvider.URL_PARAM_CLIENT, ExtensionsKt.toJsonObject(subscriptionRequest.getClient()));
            jsonObjectBuilder.put("device_id", ExtensionsKt.toJsonElement(subscriptionRequest.getDeviceId()));
            jsonObjectBuilder.put("client_time_zone", ExtensionsKt.toJsonElement(subscriptionRequest.getTimeZone()));
            objectRef.element = jsonObjectBuilder.build();
            JsonObject extraSettings = subscriptionRequest.getSettings().getExtraSettings();
            if (extraSettings != null) {
                objectRef.element = combineWith((JsonObject) objectRef.element, extraSettings);
            }
            arrayList.add(new InternalSubscriptionRequest(subscriptionRequest.getAccount(), subscriptionRequest.getApplication(), subscriptionRequest.getTransport().getJsonValue(), subscriptionRequest.getPushToken(), subscriptionRequest.getAccessToken(), subscriptionRequest.getAndroidId(), subscriptionRequest.getSdkDeviceId(), (JsonObject) objectRef.element, subscriptionRequest.getStatus()));
        }
        return arrayList;
    }

    private final SubscriptionResult parseSubscriptionResponseToResult(SubscriptionResponse response) {
        ArrayList arrayList;
        if (response.getError().getCode() != 0) {
            return new SubscriptionResult.ServerError(response.getError().getCode(), response.getError().getMessage());
        }
        List<SubscriptionResponse.AccountResult> validateResult = response.getValidateResult();
        if (validateResult != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : validateResult) {
                if (!((SubscriptionResponse.AccountResult) obj).isValid()) {
                    arrayList2.add(obj);
                }
            }
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add(((SubscriptionResponse.AccountResult) it.next()).getAccount());
            }
        } else {
            arrayList = null;
        }
        return (arrayList == null || arrayList.isEmpty()) ? SubscriptionResult.OK.INSTANCE : new SubscriptionResult.HasInvalidAccounts(CollectionsKt.toSet(arrayList));
    }

    private final UnsubscribeResult parseUnsubscribeResponseToResult(UnsubscribeResponse response) {
        return response.getError().getCode() == 0 ? UnsubscribeResult.OK.INSTANCE : new UnsubscribeResult.ServerError(response.getError().getCode(), response.getError().getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:38:0x01af  */
    /* JADX WARN: Code duplicated, block: B:39:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x016a, code lost:
    
        if (r0 == r3) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object setSettingsInternal(Collection<SubscriptionRequest> collection, ApiVersion apiVersion, Continuation<? super SubscriptionResult> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objM13123constructorimpl;
        ApiVersion apiVersion2;
        Request request;
        Call call;
        int i10;
        HttpUrl httpUrl;
        Collection<InternalSubscriptionRequest> collection2;
        String str;
        Collection<SubscriptionRequest> collection3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i11 = anonymousClass1.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i11 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = anonymousClass1.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(objWithContext);
                apiVersion2 = apiVersion;
                HttpUrl httpUrlBuild = urlBuilder().addPathSegment(this.hostInfoProvider.getApiPathByVersion(apiVersion2)).addPathSegment(apiVersion2.getValue()).addPathSegment("set_settings").build();
                Collection<InternalSubscriptionRequest> collectionMapSubscriptions = mapSubscriptions(collection);
                Json.Companion companion = Json.INSTANCE;
                companion.getSerializersModule();
                String strEncodeToString = companion.encodeToString(new ArrayListSerializer(InternalSubscriptionRequest.INSTANCE.serializer()), collectionMapSubscriptions);
                Request requestBuild = new Request.Builder().url(httpUrlBuild).post(ExtensionsKt.toJsonRequestBody(strEncodeToString)).build();
                Call callNewCall = this.okHttpClient.newCall(requestBuild);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(collection);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(apiVersion2);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(httpUrlBuild);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(collectionMapSubscriptions);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(strEncodeToString);
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(requestBuild);
                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(callNewCall);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                Object objAwait = CallHandlerKt.await(callNewCall, anonymousClass1);
                if (objAwait != coroutine_suspended) {
                    request = requestBuild;
                    objWithContext = objAwait;
                    call = callNewCall;
                    i10 = 0;
                    httpUrl = httpUrlBuild;
                    collection2 = collectionMapSubscriptions;
                    str = strEncodeToString;
                    collection3 = collection;
                }
                return coroutine_suspended;
            }
            if (i12 == 1) {
                i10 = anonymousClass1.I$0;
                call = (Call) anonymousClass1.L$6;
                Request request2 = (Request) anonymousClass1.L$5;
                String str2 = (String) anonymousClass1.L$4;
                Collection<InternalSubscriptionRequest> collection4 = (Collection) anonymousClass1.L$3;
                HttpUrl httpUrl2 = (HttpUrl) anonymousClass1.L$2;
                ApiVersion apiVersion3 = (ApiVersion) anonymousClass1.L$1;
                collection3 = (Collection) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objWithContext);
                request = request2;
                apiVersion2 = apiVersion3;
                httpUrl = httpUrl2;
                collection2 = collection4;
                str = str2;
            } else {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objWithContext);
            }
            Json.Companion companion2 = Json.INSTANCE;
            companion2.getSerializersModule();
            objM13123constructorimpl = Result.m13123constructorimpl((SubscriptionResponse) companion2.decodeFromString(SubscriptionResponse.INSTANCE.serializer(), (String) objWithContext));
            if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
                ResultKt.throwOnFailure(objM13123constructorimpl);
                return parseSubscriptionResponseToResult((SubscriptionResponse) objM13123constructorimpl);
            }
            Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
            Intrinsics.checkNotNull(thM13126exceptionOrNullimpl);
            return new SubscriptionResult.UnknownError(thM13126exceptionOrNullimpl);
            Response response = (Response) objWithContext;
            if (response.isSuccessful()) {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                CallHandlerKt$handleCall$result$responseData$1 callHandlerKt$handleCall$result$responseData$1 = new CallHandlerKt$handleCall$result$responseData$1(response, null);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(collection3);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(apiVersion2);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(httpUrl);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(collection2);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(request);
                anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(call);
                anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(response);
                anonymousClass1.I$0 = i10;
                anonymousClass1.label = 2;
                objWithContext = BuildersKt.withContext(io2, callHandlerKt$handleCall$result$responseData$1, anonymousClass1);
            } else {
                PushMeRequestException pushMeRequestException = new PushMeRequestException(response.message(), response.code());
                Result.Companion companion3 = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(pushMeRequestException));
            }
        } catch (Exception e10) {
            Result.Companion companion4 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
        if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
            ResultKt.throwOnFailure(objM13123constructorimpl);
            return parseSubscriptionResponseToResult((SubscriptionResponse) objM13123constructorimpl);
        }
        Throwable thM13126exceptionOrNullimpl2 = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        Intrinsics.checkNotNull(thM13126exceptionOrNullimpl2);
        return new SubscriptionResult.UnknownError(thM13126exceptionOrNullimpl2);
    }

    private final HttpUrl.Builder urlBuilder() {
        HttpUrl.Builder builderHost = new HttpUrl.Builder().scheme(this.hostInfoProvider.getScheme()).host(this.hostInfoProvider.getHost());
        Integer port = this.hostInfoProvider.getPort();
        if (port != null) {
            builderHost.port(port.intValue());
        }
        return builderHost;
    }

    @Override // com.vk.pushme.network.PushMeApi
    @Nullable
    public Object setSettingsV1(@NotNull Collection<SubscriptionRequest> collection, @NotNull Continuation<? super SubscriptionResult> continuation) {
        return setSettingsInternal(collection, ApiVersion.V1, continuation);
    }

    @Override // com.vk.pushme.network.PushMeApi
    @Nullable
    public Object setSettingsV2(@NotNull Collection<SubscriptionRequest> collection, @NotNull Continuation<? super SubscriptionResult> continuation) {
        return setSettingsInternal(collection, ApiVersion.V2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:45:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0163, code lost:
    
        if (r2 == r4) goto L37;
     */
    @Override // com.vk.pushme.network.PushMeApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object unsubscribeByDeviceId(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Continuation<? super UnsubscribeResult> continuation) {
        C10781 c10781;
        Object objM13123constructorimpl;
        String str4;
        Object objAwait;
        FormBody formBody;
        int i10;
        String str5;
        Request request;
        Call call;
        HttpUrl httpUrl;
        String str6 = str3;
        if (continuation instanceof C10781) {
            c10781 = (C10781) continuation;
            int i11 = c10781.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                c10781.label = i11 - Integer.MIN_VALUE;
            } else {
                c10781 = new C10781(continuation);
            }
        } else {
            c10781 = new C10781(continuation);
        }
        Object objWithContext = c10781.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = c10781.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(objWithContext);
                HttpUrl httpUrlBuild = urlBuilder().addPathSegments("api/v1/unsubscribe_by_device_id").build();
                str4 = str2;
                FormBody.Builder builderAdd = new FormBody.Builder(null, 1, null).add("account", str).add("device_id", str4);
                if (str6 != null && !StringsKt.isBlank(str6)) {
                    builderAdd.add("application", str6);
                }
                FormBody formBodyBuild = builderAdd.build();
                Request requestBuild = new Request.Builder().url(httpUrlBuild).post(formBodyBuild).build();
                Call callNewCall = this.okHttpClient.newCall(requestBuild);
                c10781.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c10781.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                c10781.L$2 = SpillingKt.nullOutSpilledVariable(str6);
                c10781.L$3 = SpillingKt.nullOutSpilledVariable(httpUrlBuild);
                c10781.L$4 = SpillingKt.nullOutSpilledVariable(formBodyBuild);
                c10781.L$5 = SpillingKt.nullOutSpilledVariable(requestBuild);
                c10781.L$6 = SpillingKt.nullOutSpilledVariable(callNewCall);
                c10781.I$0 = 0;
                c10781.label = 1;
                objAwait = CallHandlerKt.await(callNewCall, c10781);
                if (objAwait != coroutine_suspended) {
                    formBody = formBodyBuild;
                    i10 = 0;
                    str5 = str;
                    request = requestBuild;
                    call = callNewCall;
                    httpUrl = httpUrlBuild;
                }
                return coroutine_suspended;
            }
            if (i12 == 1) {
                int i13 = c10781.I$0;
                call = (Call) c10781.L$6;
                Request request2 = (Request) c10781.L$5;
                FormBody formBody2 = (FormBody) c10781.L$4;
                HttpUrl httpUrl2 = (HttpUrl) c10781.L$3;
                String str7 = (String) c10781.L$2;
                String str8 = (String) c10781.L$1;
                str5 = (String) c10781.L$0;
                ResultKt.throwOnFailure(objWithContext);
                i10 = i13;
                str6 = str7;
                str4 = str8;
                httpUrl = httpUrl2;
                formBody = formBody2;
                request = request2;
                objAwait = objWithContext;
            } else {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objWithContext);
            }
            Json.Companion companion = Json.INSTANCE;
            companion.getSerializersModule();
            objM13123constructorimpl = Result.m13123constructorimpl((UnsubscribeResponse) companion.decodeFromString(UnsubscribeResponse.INSTANCE.serializer(), (String) objWithContext));
            if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
                ResultKt.throwOnFailure(objM13123constructorimpl);
                return parseUnsubscribeResponseToResult((UnsubscribeResponse) objM13123constructorimpl);
            }
            Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
            Intrinsics.checkNotNull(thM13126exceptionOrNullimpl);
            return new UnsubscribeResult.UnknownError(thM13126exceptionOrNullimpl);
            Response response = (Response) objAwait;
            if (response.isSuccessful()) {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                CallHandlerKt$handleCall$result$responseData$1 callHandlerKt$handleCall$result$responseData$1 = new CallHandlerKt$handleCall$result$responseData$1(response, null);
                c10781.L$0 = SpillingKt.nullOutSpilledVariable(str5);
                c10781.L$1 = SpillingKt.nullOutSpilledVariable(str4);
                c10781.L$2 = SpillingKt.nullOutSpilledVariable(str6);
                c10781.L$3 = SpillingKt.nullOutSpilledVariable(httpUrl);
                c10781.L$4 = SpillingKt.nullOutSpilledVariable(formBody);
                c10781.L$5 = SpillingKt.nullOutSpilledVariable(request);
                c10781.L$6 = SpillingKt.nullOutSpilledVariable(call);
                c10781.L$7 = SpillingKt.nullOutSpilledVariable(response);
                c10781.I$0 = i10;
                c10781.label = 2;
                objWithContext = BuildersKt.withContext(io2, callHandlerKt$handleCall$result$responseData$1, c10781);
            } else {
                PushMeRequestException pushMeRequestException = new PushMeRequestException(response.message(), response.code());
                Result.Companion companion2 = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(pushMeRequestException));
            }
        } catch (Exception e10) {
            Result.Companion companion3 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
        if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
            ResultKt.throwOnFailure(objM13123constructorimpl);
            return parseUnsubscribeResponseToResult((UnsubscribeResponse) objM13123constructorimpl);
        }
        Throwable thM13126exceptionOrNullimpl2 = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        Intrinsics.checkNotNull(thM13126exceptionOrNullimpl2);
        return new UnsubscribeResult.UnknownError(thM13126exceptionOrNullimpl2);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0172  */
    /* JADX WARN: Code duplicated, block: B:39:0x017c  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x012d, code lost:
    
        if (r15 == r1) goto L31;
     */
    @Override // com.vk.pushme.network.PushMeApi
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object unsubscribeByToken(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super UnsubscribeResult> continuation) {
        C10791 c10791;
        Object objM13123constructorimpl;
        HttpUrl httpUrlBuild;
        Request requestBuild;
        Call callNewCall;
        int i10;
        FormBody formBody;
        if (continuation instanceof C10791) {
            c10791 = (C10791) continuation;
            int i11 = c10791.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                c10791.label = i11 - Integer.MIN_VALUE;
            } else {
                c10791 = new C10791(continuation);
            }
        } else {
            c10791 = new C10791(continuation);
        }
        Object objWithContext = c10791.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = c10791.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(objWithContext);
                httpUrlBuild = urlBuilder().addPathSegments("api/v2/unsubscribe_by_token").build();
                FormBody formBodyBuild = new FormBody.Builder(null, 1, null).add("token", str).add("application", str2).build();
                requestBuild = new Request.Builder().url(httpUrlBuild).post(formBodyBuild).build();
                callNewCall = this.okHttpClient.newCall(requestBuild);
                c10791.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c10791.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                c10791.L$2 = SpillingKt.nullOutSpilledVariable(httpUrlBuild);
                c10791.L$3 = SpillingKt.nullOutSpilledVariable(formBodyBuild);
                c10791.L$4 = SpillingKt.nullOutSpilledVariable(requestBuild);
                c10791.L$5 = SpillingKt.nullOutSpilledVariable(callNewCall);
                i10 = 0;
                c10791.I$0 = 0;
                c10791.label = 1;
                Object objAwait = CallHandlerKt.await(callNewCall, c10791);
                if (objAwait != coroutine_suspended) {
                    formBody = formBodyBuild;
                    objWithContext = objAwait;
                }
                return coroutine_suspended;
            }
            if (i12 == 1) {
                int i13 = c10791.I$0;
                Call call = (Call) c10791.L$5;
                requestBuild = (Request) c10791.L$4;
                formBody = (FormBody) c10791.L$3;
                httpUrlBuild = (HttpUrl) c10791.L$2;
                String str3 = (String) c10791.L$1;
                String str4 = (String) c10791.L$0;
                ResultKt.throwOnFailure(objWithContext);
                i10 = i13;
                str = str4;
                callNewCall = call;
                str2 = str3;
            } else {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objWithContext);
            }
            Json.Companion companion = Json.INSTANCE;
            companion.getSerializersModule();
            objM13123constructorimpl = Result.m13123constructorimpl((UnsubscribeResponse) companion.decodeFromString(UnsubscribeResponse.INSTANCE.serializer(), (String) objWithContext));
            if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
                ResultKt.throwOnFailure(objM13123constructorimpl);
                return parseUnsubscribeResponseToResult((UnsubscribeResponse) objM13123constructorimpl);
            }
            Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
            Intrinsics.checkNotNull(thM13126exceptionOrNullimpl);
            return new UnsubscribeResult.UnknownError(thM13126exceptionOrNullimpl);
            Response response = (Response) objWithContext;
            if (response.isSuccessful()) {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                CallHandlerKt$handleCall$result$responseData$1 callHandlerKt$handleCall$result$responseData$1 = new CallHandlerKt$handleCall$result$responseData$1(response, null);
                c10791.L$0 = SpillingKt.nullOutSpilledVariable(str);
                c10791.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                c10791.L$2 = SpillingKt.nullOutSpilledVariable(httpUrlBuild);
                c10791.L$3 = SpillingKt.nullOutSpilledVariable(formBody);
                c10791.L$4 = SpillingKt.nullOutSpilledVariable(requestBuild);
                c10791.L$5 = SpillingKt.nullOutSpilledVariable(callNewCall);
                c10791.L$6 = SpillingKt.nullOutSpilledVariable(response);
                c10791.I$0 = i10;
                c10791.label = 2;
                objWithContext = BuildersKt.withContext(io2, callHandlerKt$handleCall$result$responseData$1, c10791);
            } else {
                PushMeRequestException pushMeRequestException = new PushMeRequestException(response.message(), response.code());
                Result.Companion companion2 = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(pushMeRequestException));
            }
        } catch (Exception e10) {
            Result.Companion companion3 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
        if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
            ResultKt.throwOnFailure(objM13123constructorimpl);
            return parseUnsubscribeResponseToResult((UnsubscribeResponse) objM13123constructorimpl);
        }
        Throwable thM13126exceptionOrNullimpl2 = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        Intrinsics.checkNotNull(thM13126exceptionOrNullimpl2);
        return new UnsubscribeResult.UnknownError(thM13126exceptionOrNullimpl2);
    }
}
