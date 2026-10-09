package com.vk.pushme.network.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0001H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u0006\u001a\u0013\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\t¢\u0006\u0002\u0010\n\u001a\u000e\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u000b\u001a\u0012\u0010\f\u001a\u00020\r*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e\u001a3\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\"\u0010\u0011\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00120\t\"\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0012¢\u0006\u0002\u0010\u0013\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"JSON_MEDIA_TYPE", "", "toJsonRequestBody", "Lokhttp3/RequestBody;", "toJsonElement", "Lkotlinx/serialization/json/JsonElement;", "", "toJsonArray", "Lkotlinx/serialization/json/JsonArray;", "", "([Ljava/lang/Object;)Lkotlinx/serialization/json/JsonArray;", "", "toJsonObject", "Lkotlinx/serialization/json/JsonObject;", "", "encodeToString", "Lkotlinx/serialization/json/Json;", "pairs", "Lkotlin/Pair;", "(Lkotlinx/serialization/json/Json;[Lkotlin/Pair;)Ljava/lang/String;", "push-me-network_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extensions.kt\ncom/vk/pushme/network/util/ExtensionsKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,37:1\n11561#2:38\n11896#2,3:39\n1563#3:42\n1634#3,3:43\n1252#3,4:48\n478#4:46\n424#4:47\n463#4:52\n413#4:53\n205#5:54\n*S KotlinDebug\n*F\n+ 1 Extensions.kt\ncom/vk/pushme/network/util/ExtensionsKt\n*L\n29#1:38\n29#1:39,3\n30#1:42\n30#1:43,3\n32#1:48,4\n32#1:46\n32#1:47\n32#1:52\n32#1:53\n35#1:54\n*E\n"})
public final class ExtensionsKt {

    @NotNull
    private static final String JSON_MEDIA_TYPE = "application/json; charset=utf-8";

    @NotNull
    public static final String encodeToString(@NotNull Json json, @NotNull Pair<?, ?>... pairs) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        JsonElement jsonElement = toJsonElement(MapsKt.toMap(pairs));
        json.getSerializersModule();
        return json.encodeToString(JsonElement.INSTANCE.serializer(), jsonElement);
    }

    @NotNull
    public static final JsonArray toJsonArray(@NotNull Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<this>");
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            arrayList.add(toJsonElement(obj));
        }
        return new JsonArray(arrayList);
    }

    @NotNull
    public static final JsonElement toJsonElement(@Nullable Object obj) {
        if (obj instanceof Number) {
            return JsonElementKt.JsonPrimitive((Number) obj);
        }
        if (obj instanceof Boolean) {
            return JsonElementKt.JsonPrimitive((Boolean) obj);
        }
        if (obj instanceof String) {
            return JsonElementKt.JsonPrimitive((String) obj);
        }
        if (obj instanceof Object[]) {
            return toJsonArray((Object[]) obj);
        }
        if (obj instanceof List) {
            return toJsonArray((Iterable<?>) obj);
        }
        if (obj instanceof Map) {
            return toJsonObject((Map) obj);
        }
        return obj instanceof JsonElement ? (JsonElement) obj : JsonNull.INSTANCE;
    }

    @NotNull
    public static final JsonObject toJsonObject(@NotNull Map<?, ?> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), toJsonElement(entry2.getValue()));
        }
        return new JsonObject(linkedHashMap2);
    }

    @NotNull
    public static final RequestBody toJsonRequestBody(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return RequestBody.INSTANCE.create(str, MediaType.INSTANCE.get("application/json; charset=utf-8"));
    }

    @NotNull
    public static final JsonArray toJsonArray(@NotNull Iterable<?> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(toJsonElement(it.next()));
        }
        return new JsonArray(arrayList);
    }
}
