package com.vk.pushme.model;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/vk/pushme/model/SubscriptionSettings;", "", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "", "", "deliveryTime", "Lcom/vk/pushme/model/DeliveryTime;", PushProcessor.DATAKEY_EXTRAS, "Lkotlinx/serialization/json/JsonObject;", "specificTransports", "Lcom/vk/pushme/model/SpecifyTransportOption;", "<init>", "(Ljava/util/Set;Lcom/vk/pushme/model/DeliveryTime;Lkotlinx/serialization/json/JsonObject;Lcom/vk/pushme/model/SpecifyTransportOption;)V", "getTags", "()Ljava/util/Set;", "getDeliveryTime", "()Lcom/vk/pushme/model/DeliveryTime;", "getExtras", "()Lkotlinx/serialization/json/JsonObject;", "getSpecificTransports", "()Lcom/vk/pushme/model/SpecifyTransportOption;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubscriptionSettings {

    @Nullable
    private final DeliveryTime deliveryTime;

    @Nullable
    private final JsonObject extras;

    @NotNull
    private final SpecifyTransportOption specificTransports;

    @NotNull
    private final Set<Integer> tags;

    public SubscriptionSettings(@NotNull Set<Integer> tags, @Nullable DeliveryTime deliveryTime, @Nullable JsonObject jsonObject, @NotNull SpecifyTransportOption specificTransports) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(specificTransports, "specificTransports");
        this.tags = tags;
        this.deliveryTime = deliveryTime;
        this.extras = jsonObject;
        this.specificTransports = specificTransports;
    }

    @Nullable
    public final DeliveryTime getDeliveryTime() {
        return this.deliveryTime;
    }

    @Nullable
    public final JsonObject getExtras() {
        return this.extras;
    }

    @NotNull
    public final SpecifyTransportOption getSpecificTransports() {
        return this.specificTransports;
    }

    @NotNull
    public final Set<Integer> getTags() {
        return this.tags;
    }

    public /* synthetic */ SubscriptionSettings(Set set, DeliveryTime deliveryTime, JsonObject jsonObject, SpecifyTransportOption specifyTransportOption, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, deliveryTime, (i10 & 4) != 0 ? null : jsonObject, (i10 & 8) != 0 ? SpecifyTransportOption.AllTransports.INSTANCE : specifyTransportOption);
    }
}
