package com.vk.pushme.model;

import com.vk.pushme.logic.Subscription;
import com.vk.pushme.logic.request.EditSubscriptionRequest;
import com.vk.pushme.model.result.EditSubscriptionResult;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.parser.BetaStateParser;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u001d\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\tJ\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0018J\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/vk/pushme/model/SubscriptionSettingsBuilder;", "", "requestCreator", "Lkotlin/Function1;", "Lcom/vk/pushme/logic/request/EditSubscriptionRequest;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "addedTags", "", "", "removedTags", "tagsChanged", "", "deliveryTime", "Lcom/vk/pushme/model/DeliveryTime;", "deliveryTimeChanged", PushProcessor.DATAKEY_EXTRAS, "Lkotlinx/serialization/json/JsonObject;", "extrasChanged", "addTag", "tag", "removeTag", "setTags", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "", "setDeliveryTime", "setExtras", BetaStateParser.BUILD_NUMBER, "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubscriptionSettingsBuilder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final Set<Integer> addedTags;

    @Nullable
    private DeliveryTime deliveryTime;
    private boolean deliveryTimeChanged;

    @Nullable
    private JsonObject extras;
    private boolean extrasChanged;

    @NotNull
    private final Set<Integer> removedTags;

    @NotNull
    private final Function1<SubscriptionSettingsBuilder, EditSubscriptionRequest> requestCreator;
    private boolean tagsChanged;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/vk/pushme/model/SubscriptionSettingsBuilder$Companion;", "", "<init>", "()V", "copyWith", "Lcom/vk/pushme/logic/Subscription;", "builder", "Lcom/vk/pushme/model/SubscriptionSettingsBuilder;", "copyWith$push_me_sdk_release", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Subscription copyWith$push_me_sdk_release(@NotNull Subscription subscription, @NotNull SubscriptionSettingsBuilder builder) {
            Intrinsics.checkNotNullParameter(subscription, "<this>");
            Intrinsics.checkNotNullParameter(builder, "builder");
            return Subscription.copy$default(subscription, null, null, builder.tagsChanged ? SetsKt.minus(builder.addedTags, (Iterable) builder.removedTags) : subscription.getTags(), null, builder.deliveryTimeChanged ? builder.deliveryTime : subscription.getDeliveryTime(), builder.extrasChanged ? builder.extras : subscription.getExtras(), 11, null);
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubscriptionSettingsBuilder(@NotNull Function1<? super SubscriptionSettingsBuilder, EditSubscriptionRequest> requestCreator) {
        Intrinsics.checkNotNullParameter(requestCreator, "requestCreator");
        this.requestCreator = requestCreator;
        this.addedTags = new LinkedHashSet();
        this.removedTags = new LinkedHashSet();
    }

    @NotNull
    public final SubscriptionSettingsBuilder addTag(int tag) {
        this.addedTags.add(Integer.valueOf(tag));
        this.tagsChanged = true;
        return this;
    }

    @NotNull
    public final Request<EditSubscriptionResult> build() {
        return this.requestCreator.invoke(this);
    }

    @NotNull
    public final SubscriptionSettingsBuilder removeTag(int tag) {
        this.removedTags.add(Integer.valueOf(tag));
        this.tagsChanged = true;
        return this;
    }

    @NotNull
    public final SubscriptionSettingsBuilder setDeliveryTime(@Nullable DeliveryTime deliveryTime) {
        this.deliveryTime = deliveryTime;
        this.deliveryTimeChanged = true;
        return this;
    }

    @NotNull
    public final SubscriptionSettingsBuilder setExtras(@Nullable JsonObject extras) {
        this.extras = extras;
        this.extrasChanged = true;
        return this;
    }

    @NotNull
    public final SubscriptionSettingsBuilder setTags(@NotNull Set<Integer> tags) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.addedTags.clear();
        this.removedTags.clear();
        CollectionsKt.addAll(this.addedTags, tags);
        this.tagsChanged = true;
        return this;
    }
}
