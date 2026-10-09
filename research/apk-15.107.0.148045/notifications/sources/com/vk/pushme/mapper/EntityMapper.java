package com.vk.pushme.mapper;

import com.vk.pushme.database.entity.Subscription;
import com.vk.pushme.logic.PendingAction;
import com.vk.pushme.model.DeliveryTime;
import com.vk.pushme.model.SpecifyTransportOption;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0005*\u00060\u0006j\u0002`\u0007J\u000e\u0010\b\u001a\u00060\u0006j\u0002`\u0007*\u00020\u0005J\u000e\u0010\u0004\u001a\u00020\t*\u00060\nj\u0002`\u000bJ\u000e\u0010\b\u001a\u00060\nj\u0002`\u000b*\u00020\t¨\u0006\f"}, d2 = {"Lcom/vk/pushme/mapper/EntityMapper;", "", "<init>", "()V", "toDomainModel", "Lcom/vk/pushme/logic/Subscription;", "Lcom/vk/pushme/database/entity/Subscription;", "Lcom/vk/pushme/mapper/DbSubscription;", "toDatabaseModel", "Lcom/vk/pushme/logic/PendingAction;", "Lcom/vk/pushme/database/entity/PendingAction;", "Lcom/vk/pushme/mapper/DbPendingAction;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEntityMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntityMapper.kt\ncom/vk/pushme/mapper/EntityMapper\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,92:1\n1#2:93\n29#3,3:94\n29#3,3:97\n*S KotlinDebug\n*F\n+ 1 EntityMapper.kt\ncom/vk/pushme/mapper/EntityMapper\n*L\n73#1:94,3\n83#1:97,3\n*E\n"})
public final class EntityMapper {

    @NotNull
    public static final EntityMapper INSTANCE = new EntityMapper();

    private EntityMapper() {
    }

    @NotNull
    public final Subscription toDatabaseModel(@NotNull com.vk.pushme.logic.Subscription subscription) {
        Intrinsics.checkNotNullParameter(subscription, "<this>");
        SpecifyTransportOptionMapper.SerializeResult serializeResultSerialize = SpecifyTransportOptionMapper.INSTANCE.serialize(subscription.getSpecifyTransportOption());
        String account = subscription.getAccount();
        String application = subscription.getApplication();
        Set<Integer> tags = subscription.getTags();
        Set<String> include = serializeResultSerialize.getInclude();
        Set<String> exclude = serializeResultSerialize.getExclude();
        DeliveryTime deliveryTime = subscription.getDeliveryTime();
        Subscription.DeliveryTime deliveryTime2 = deliveryTime != null ? new Subscription.DeliveryTime(new Subscription.TimePoint(deliveryTime.getFrom().getHour(), deliveryTime.getFrom().getMinute()), new Subscription.TimePoint(deliveryTime.getTo().getHour(), deliveryTime.getTo().getMinute())) : null;
        JsonObject extras = subscription.getExtras();
        return new Subscription(0L, account, application, tags, include, exclude, deliveryTime2, extras != null ? extras.toString() : null, 1, null);
    }

    @NotNull
    public final com.vk.pushme.logic.Subscription toDomainModel(@NotNull Subscription subscription) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(subscription, "<this>");
        String account = subscription.getAccount();
        String application = subscription.getApplication();
        Set<Integer> tags = subscription.getTags();
        SpecifyTransportOption specifyTransportOption = SpecifyTransportOptionMapper.INSTANCE.parse(subscription.getIncludeTransports(), subscription.getExcludeTransports());
        Subscription.DeliveryTime deliveryTime = subscription.getDeliveryTime();
        DeliveryTime deliveryTime2 = deliveryTime != null ? new DeliveryTime(new DeliveryTime.TimePoint(deliveryTime.getFrom().getHour(), deliveryTime.getFrom().getMinute()), new DeliveryTime.TimePoint(deliveryTime.getTo().getHour(), deliveryTime.getTo().getMinute())) : null;
        String extras = subscription.getExtras();
        return new com.vk.pushme.logic.Subscription(account, application, tags, specifyTransportOption, deliveryTime2, extras != null ? JsonElementKt.getJsonObject(Json.INSTANCE.parseToJsonElement(extras)) : null);
    }

    @NotNull
    public final PendingAction toDomainModel(@NotNull com.vk.pushme.database.entity.PendingAction pendingAction) throws IllegalArgumentException, NoSuchElementException {
        Intrinsics.checkNotNullParameter(pendingAction, "<this>");
        return PendingActionParser.INSTANCE.parse(pendingAction.getType(), pendingAction.getData());
    }

    @NotNull
    public final com.vk.pushme.database.entity.PendingAction toDatabaseModel(@NotNull PendingAction pendingAction) {
        Intrinsics.checkNotNullParameter(pendingAction, "<this>");
        if (pendingAction instanceof PendingAction.Subscribe) {
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            jsonObjectBuilder.put("application", JsonElementKt.JsonPrimitive(((PendingAction.Subscribe) pendingAction).getApplication()));
            Unit unit = Unit.INSTANCE;
            return new com.vk.pushme.database.entity.PendingAction(0L, PendingAction.SUBSCRIBE_TYPE, jsonObjectBuilder.build().toString(), 1, null);
        }
        if (pendingAction instanceof PendingAction.Unsubscribe) {
            PendingAction.Unsubscribe unsubscribe = (PendingAction.Unsubscribe) pendingAction;
            String strJoinToString$default = CollectionsKt.joinToString$default(unsubscribe.getAccounts(), ",", null, null, 0, null, null, 62, null);
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            jsonObjectBuilder2.put("accounts", JsonElementKt.JsonPrimitive(strJoinToString$default));
            jsonObjectBuilder2.put("application", JsonElementKt.JsonPrimitive(unsubscribe.getApplication()));
            Unit unit2 = Unit.INSTANCE;
            return new com.vk.pushme.database.entity.PendingAction(0L, PendingAction.UNSUBSCRIBE_TYPE, jsonObjectBuilder2.build().toString(), 1, null);
        }
        throw new NoWhenBranchMatchedException();
    }
}
