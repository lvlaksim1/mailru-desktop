package com.vk.pushme.mapper;

import com.vk.pushme.logic.PendingAction;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/mapper/PendingActionParser;", "", "<init>", "()V", "parse", "Lcom/vk/pushme/logic/PendingAction;", "type", "", "data", "parseSubscribe", "Lcom/vk/pushme/logic/PendingAction$Subscribe;", "json", "Lkotlinx/serialization/json/JsonObject;", "parseUnsubscribe", "Lcom/vk/pushme/logic/PendingAction$Unsubscribe;", "ACCOUNTS_DELIMITER", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PendingActionParser {

    @NotNull
    public static final String ACCOUNTS_DELIMITER = ",";

    @NotNull
    public static final PendingActionParser INSTANCE = new PendingActionParser();

    private PendingActionParser() {
    }

    private final PendingAction.Subscribe parseSubscribe(JsonObject json) {
        return new PendingAction.Subscribe(JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue(json, "application")).getContent());
    }

    private final PendingAction.Unsubscribe parseUnsubscribe(JsonObject json) {
        return new PendingAction.Unsubscribe(CollectionsKt.toSet(StringsKt.split$default((CharSequence) JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue(json, "accounts")).getContent(), new String[]{","}, false, 0, 6, (Object) null)), JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue(json, "application")).getContent());
    }

    @NotNull
    public final PendingAction parse(@NotNull String type, @NotNull String data) throws IllegalArgumentException, NoSuchElementException {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(data, "data");
        JsonObject jsonObject = JsonElementKt.getJsonObject(Json.INSTANCE.parseToJsonElement(data));
        String lowerCase = type.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (Intrinsics.areEqual(lowerCase, PendingAction.SUBSCRIBE_TYPE)) {
            return parseSubscribe(jsonObject);
        }
        if (Intrinsics.areEqual(lowerCase, PendingAction.UNSUBSCRIBE_TYPE)) {
            return parseUnsubscribe(jsonObject);
        }
        throw new IllegalStateException("Unknown action type: " + type);
    }
}
