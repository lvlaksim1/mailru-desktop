package com.vk.pushme.logic.usecase;

import android.net.Uri;
import androidx.work.OneTimeWorkRequest;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.database.entity.Push;
import com.vk.pushme.model.Transport;
import com.vk.pushme.work.SendAnalyticsWorker;
import com.vk.pushme.work.util.WorkScheduler;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0086\u0002¢\u0006\u0002\u0010\u0017J2\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00192\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/vk/pushme/logic/usecase/PushReceivedUseCase;", "", "pushDao", "Lcom/vk/pushme/database/dao/PushDao;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "logger", "Lcom/vk/pushme/common/Logger;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Lcom/vk/pushme/database/dao/PushDao;Lcom/vk/pushme/work/util/WorkScheduler;Lcom/vk/pushme/common/Logger;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "invoke", "", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "", "transport", "Lcom/vk/pushme/model/Transport;", "(Ljava/util/Map;Lcom/vk/pushme/model/Transport;)Ljava/lang/Long;", "parseAckAndOpenUrls", "Lkotlin/Pair;", "appendActionQueryParameter", "url", "paramValue", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushReceivedUseCase {

    @NotNull
    private static final String ACK_URL_KEY = "ack";

    @NotNull
    private static final String HUB_LINK_KEY = "hub_link";

    @NotNull
    private static final String OPEN_URL_KEY = "open";

    @NotNull
    private final AnalyticsHandler analyticsHandler;

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    @NotNull
    private final PushDao pushDao;

    @NotNull
    private final WorkScheduler workScheduler;

    public PushReceivedUseCase(@NotNull PushDao pushDao, @NotNull WorkScheduler workScheduler, @NotNull final Logger logger, @NotNull AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(pushDao, "pushDao");
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analyticsHandler, "analyticsHandler");
        this.pushDao = pushDao;
        this.workScheduler = workScheduler;
        this.analyticsHandler = analyticsHandler;
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.logic.usecase.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushReceivedUseCase.logger_delegate$lambda$0(logger);
            }
        });
    }

    private final String appendActionQueryParameter(String url, String paramValue) {
        Uri uri = Uri.parse(url);
        if (uri.getQueryParameterNames().contains("action")) {
            return url;
        }
        String string = uri.buildUpon().appendQueryParameter("action", paramValue).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0(Logger logger) {
        return logger.createLogger("PushReceivedUseCase");
    }

    private final Pair<String, String> parseAckAndOpenUrls(Map<String, String> payload, Transport transport) {
        JsonPrimitive jsonPrimitive;
        JsonPrimitive jsonPrimitive2;
        String str = payload.get("hub_link");
        if (str != null) {
            try {
                JsonObject jsonObject = JsonElementKt.getJsonObject(Json.INSTANCE.parseToJsonElement(str));
                JsonElement jsonElement = (JsonElement) jsonObject.get((Object) "ack");
                String content = (jsonElement == null || (jsonPrimitive2 = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : jsonPrimitive2.getContent();
                JsonElement jsonElement2 = (JsonElement) jsonObject.get((Object) "open");
                String content2 = (jsonElement2 == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement2)) == null) ? null : jsonPrimitive.getContent();
                if (content != null && !StringsKt.isBlank(content) && content2 != null && !StringsKt.isBlank(content2)) {
                    return TuplesKt.to(content, content2);
                }
            } catch (IllegalArgumentException e10) {
                this.analyticsHandler.pushError(AnalyticsErrorType.PAYLOAD_PARSE_ERROR, "Failed to parse hub_link JSON " + e10.getMessage(), transport.getValue());
                getLogger().error("Failed to parse hub_link JSON", e10);
            }
        }
        String str2 = payload.get("ack");
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        return TuplesKt.to(appendActionQueryParameter(str2, "ack"), appendActionQueryParameter(str2, "open"));
    }

    @Nullable
    public final Long invoke(@NotNull Map<String, String> payload, @NotNull Transport transport) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Pair<String, String> ackAndOpenUrls = parseAckAndOpenUrls(payload, transport);
        if (ackAndOpenUrls == null) {
            return null;
        }
        String strComponent1 = ackAndOpenUrls.component1();
        Push push = new Push(0L, transport.getValue(), ackAndOpenUrls.component2(), System.currentTimeMillis(), 1, null);
        try {
            long jLongValue = ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new PushReceivedUseCase$invoke$entityId$1(this, push, null), 1, null)).longValue();
            Logger.info$default(getLogger(), "Push with ID = " + jLongValue + " has been saved", null, 2, null);
            OneTimeWorkRequest oneTimeWorkRequestBuildWorkRequest = SendAnalyticsWorker.INSTANCE.buildWorkRequest(strComponent1, PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getConfig().getSkipConnectionCheckByGoogle());
            this.analyticsHandler.successPushReceived();
            this.workScheduler.enqueue(oneTimeWorkRequestBuildWorkRequest);
            return Long.valueOf(jLongValue);
        } catch (Exception e10) {
            AnalyticsHandler analyticsHandler = this.analyticsHandler;
            String message = e10.getMessage();
            if (message == null) {
                message = "Unknown error";
            }
            analyticsHandler.pushError(AnalyticsErrorType.PUSH_RECEIVED_ERROR, message, push.getTransport());
            getLogger().error("Failed to save push", e10);
            return null;
        }
    }
}
