package com.vk.pushme.analytcis;

import com.vk.pushme.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0016J \u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0016¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/analytcis/StubAnalyticsHandler;", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/common/Logger;)V", "tokenError", "", "errorType", "", "errorMessage", "pushError", "transport", "subscriptionError", "application", "unsubscribeError", "analyticsError", "successSubscription", "successUnsubscribe", "successPushReceived", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StubAnalyticsHandler extends AnalyticsHandler {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StubAnalyticsHandler(@NotNull Logger logger) {
        super(logger);
        Intrinsics.checkNotNullParameter(logger, "logger");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void analyticsError(@NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void pushError(@NotNull String errorType, @NotNull String errorMessage, @NotNull String transport) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Intrinsics.checkNotNullParameter(transport, "transport");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void subscriptionError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successSubscription(@NotNull String application) {
        Intrinsics.checkNotNullParameter(application, "application");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successUnsubscribe(@NotNull String application) {
        Intrinsics.checkNotNullParameter(application, "application");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void tokenError(@NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void unsubscribeError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
    }

    @Override // com.vk.pushme.analytcis.AnalyticsHandler, com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successPushReceived() {
    }
}
