package com.vk.pushme.analytcis;

import com.vk.pushme.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J \u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/vk/pushme/analytcis/AnalyticsHandler;", "Lcom/vk/pushme/analytcis/PushMeAnalyticsHandler;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/common/Logger;)V", "getLogger", "()Lcom/vk/pushme/common/Logger;", "tokenError", "", "errorType", "", "errorMessage", "pushError", "transport", "subscriptionError", "application", "unsubscribeError", "analyticsError", "successSubscription", "successUnsubscribe", "successPushReceived", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AnalyticsHandler implements PushMeAnalyticsHandler {

    @NotNull
    private final Logger logger;

    public AnalyticsHandler(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void analyticsError(@NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Logger.error$default(this.logger, errorType + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + errorMessage, null, 2, null);
    }

    @NotNull
    public final Logger getLogger() {
        return this.logger;
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void pushError(@NotNull String errorType, @NotNull String errorMessage, @NotNull String transport) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Logger.error$default(this.logger, errorType + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + transport + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + errorMessage, null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void subscriptionError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Logger.error$default(this.logger, errorType + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + errorMessage, null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successPushReceived() {
        Logger.info$default(this.logger, "Success pushMe sdk push received", null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successSubscription(@NotNull String application) {
        Intrinsics.checkNotNullParameter(application, "application");
        Logger.info$default(this.logger, "Success pushMe Sdk subscription(" + application + ")", null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void successUnsubscribe(@NotNull String application) {
        Intrinsics.checkNotNullParameter(application, "application");
        Logger.info$default(this.logger, "Success pushMe Sdk unsubscribe(" + application + ")", null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void tokenError(@NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Logger.error$default(this.logger, errorType + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + errorMessage, null, 2, null);
    }

    @Override // com.vk.pushme.analytcis.PushMeAnalyticsHandler
    public void unsubscribeError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        Logger.error$default(this.logger, errorType + AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER + errorMessage, null, 2, null);
    }
}
