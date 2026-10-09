package com.vk.pushme.analytcis;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J \u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\b\u0010\u000f\u001a\u00020\u0003H&¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lcom/vk/pushme/analytcis/PushMeAnalyticsHandler;", "", "tokenError", "", "errorType", "", "errorMessage", "pushError", "transport", "subscriptionError", "application", "unsubscribeError", "analyticsError", "successSubscription", "successUnsubscribe", "successPushReceived", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushMeAnalyticsHandler {
    void analyticsError(@NotNull String errorType, @NotNull String errorMessage);

    void pushError(@NotNull String errorType, @NotNull String errorMessage, @NotNull String transport);

    void subscriptionError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage);

    void successPushReceived();

    void successSubscription(@NotNull String application);

    void successUnsubscribe(@NotNull String application);

    void tokenError(@NotNull String errorType, @NotNull String errorMessage);

    void unsubscribeError(@NotNull String application, @NotNull String errorType, @NotNull String errorMessage);
}
