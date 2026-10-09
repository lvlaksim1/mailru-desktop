package com.vk.pushme.logic;

import com.vk.pushme.network.model.request.SubscriptionRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/SubscriptionBatcher;", "", "limit", "", "<init>", "(I)V", "batch", "", "Lcom/vk/pushme/network/model/request/SubscriptionRequest;", "input", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSubscriptionBatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SubscriptionBatcher.kt\ncom/vk/pushme/logic/SubscriptionBatcher\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,42:1\n1491#2:43\n1516#2,3:44\n1519#2,3:54\n382#3,7:47\n*S KotlinDebug\n*F\n+ 1 SubscriptionBatcher.kt\ncom/vk/pushme/logic/SubscriptionBatcher\n*L\n22#1:43\n22#1:44,3\n22#1:54,3\n22#1:47,7\n*E\n"})
public final class SubscriptionBatcher {
    private final int limit;

    public SubscriptionBatcher(int i10) {
        this.limit = i10;
    }

    @NotNull
    public final Collection<Collection<SubscriptionRequest>> batch(@NotNull Collection<SubscriptionRequest> input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (input.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : input) {
            String application = ((SubscriptionRequest) obj).getApplication();
            Object arrayList = linkedHashMap.get(application);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(application, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i10 = 0;
        for (List list : linkedHashMap.values()) {
            int size = list.size();
            if (i10 + size > this.limit) {
                arrayList2.add(arrayList3);
                arrayList3 = new ArrayList();
                i10 = 0;
            }
            arrayList3.addAll(list);
            i10 += size;
        }
        arrayList2.add(arrayList3);
        return arrayList2;
    }
}
