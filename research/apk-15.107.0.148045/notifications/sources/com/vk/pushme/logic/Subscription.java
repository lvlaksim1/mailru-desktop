package com.vk.pushme.logic;

import com.vk.pushme.model.DeliveryTime;
import com.vk.pushme.model.SpecifyTransportOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rHÆ\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0007HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006'"}, d2 = {"Lcom/vk/pushme/logic/Subscription;", "", "account", "", "application", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "", "", "specifyTransportOption", "Lcom/vk/pushme/model/SpecifyTransportOption;", "deliveryTime", "Lcom/vk/pushme/model/DeliveryTime;", PushProcessor.DATAKEY_EXTRAS, "Lkotlinx/serialization/json/JsonObject;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Lcom/vk/pushme/model/SpecifyTransportOption;Lcom/vk/pushme/model/DeliveryTime;Lkotlinx/serialization/json/JsonObject;)V", "getAccount", "()Ljava/lang/String;", "getApplication", "getTags", "()Ljava/util/Set;", "getSpecifyTransportOption", "()Lcom/vk/pushme/model/SpecifyTransportOption;", "getDeliveryTime", "()Lcom/vk/pushme/model/DeliveryTime;", "getExtras", "()Lkotlinx/serialization/json/JsonObject;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Subscription {

    @NotNull
    private final String account;

    @NotNull
    private final String application;

    @Nullable
    private final DeliveryTime deliveryTime;

    @Nullable
    private final JsonObject extras;

    @NotNull
    private final SpecifyTransportOption specifyTransportOption;

    @NotNull
    private final Set<Integer> tags;

    public Subscription(@NotNull String account, @NotNull String application, @NotNull Set<Integer> tags, @NotNull SpecifyTransportOption specifyTransportOption, @Nullable DeliveryTime deliveryTime, @Nullable JsonObject jsonObject) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(specifyTransportOption, "specifyTransportOption");
        this.account = account;
        this.application = application;
        this.tags = tags;
        this.specifyTransportOption = specifyTransportOption;
        this.deliveryTime = deliveryTime;
        this.extras = jsonObject;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Subscription copy$default(Subscription subscription, String str, String str2, Set set, SpecifyTransportOption specifyTransportOption, DeliveryTime deliveryTime, JsonObject jsonObject, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = subscription.account;
        }
        if ((i10 & 2) != 0) {
            str2 = subscription.application;
        }
        if ((i10 & 4) != 0) {
            set = subscription.tags;
        }
        if ((i10 & 8) != 0) {
            specifyTransportOption = subscription.specifyTransportOption;
        }
        if ((i10 & 16) != 0) {
            deliveryTime = subscription.deliveryTime;
        }
        if ((i10 & 32) != 0) {
            jsonObject = subscription.extras;
        }
        DeliveryTime deliveryTime2 = deliveryTime;
        JsonObject jsonObject2 = jsonObject;
        return subscription.copy(str, str2, set, specifyTransportOption, deliveryTime2, jsonObject2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getApplication() {
        return this.application;
    }

    @NotNull
    public final Set<Integer> component3() {
        return this.tags;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SpecifyTransportOption getSpecifyTransportOption() {
        return this.specifyTransportOption;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final DeliveryTime getDeliveryTime() {
        return this.deliveryTime;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final JsonObject getExtras() {
        return this.extras;
    }

    @NotNull
    public final Subscription copy(@NotNull String account, @NotNull String application, @NotNull Set<Integer> tags, @NotNull SpecifyTransportOption specifyTransportOption, @Nullable DeliveryTime deliveryTime, @Nullable JsonObject extras) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(specifyTransportOption, "specifyTransportOption");
        return new Subscription(account, application, tags, specifyTransportOption, deliveryTime, extras);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) other;
        return Intrinsics.areEqual(this.account, subscription.account) && Intrinsics.areEqual(this.application, subscription.application) && Intrinsics.areEqual(this.tags, subscription.tags) && Intrinsics.areEqual(this.specifyTransportOption, subscription.specifyTransportOption) && Intrinsics.areEqual(this.deliveryTime, subscription.deliveryTime) && Intrinsics.areEqual(this.extras, subscription.extras);
    }

    @NotNull
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    public final String getApplication() {
        return this.application;
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
    public final SpecifyTransportOption getSpecifyTransportOption() {
        return this.specifyTransportOption;
    }

    @NotNull
    public final Set<Integer> getTags() {
        return this.tags;
    }

    public int hashCode() {
        int iHashCode = ((((((this.account.hashCode() * 31) + this.application.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.specifyTransportOption.hashCode()) * 31;
        DeliveryTime deliveryTime = this.deliveryTime;
        int iHashCode2 = (iHashCode + (deliveryTime == null ? 0 : deliveryTime.hashCode())) * 31;
        JsonObject jsonObject = this.extras;
        return iHashCode2 + (jsonObject != null ? jsonObject.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Subscription(account=" + this.account + ", application=" + this.application + ", tags=" + this.tags + ", specifyTransportOption=" + this.specifyTransportOption + ", deliveryTime=" + this.deliveryTime + ", extras=" + this.extras + ")";
    }
}
