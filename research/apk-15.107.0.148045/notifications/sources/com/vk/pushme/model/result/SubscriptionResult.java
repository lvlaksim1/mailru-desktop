package com.vk.pushme.model.result;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/vk/pushme/model/result/SubscriptionResult;", "", "<init>", "()V", "OK", "NoAuthError", "MissingPushTokenError", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/model/result/SubscriptionResult$MissingPushTokenError;", "Lcom/vk/pushme/model/result/SubscriptionResult$NoAuthError;", "Lcom/vk/pushme/model/result/SubscriptionResult$OK;", "Lcom/vk/pushme/model/result/SubscriptionResult$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SubscriptionResult {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/SubscriptionResult$MissingPushTokenError;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MissingPushTokenError extends SubscriptionResult {

        @NotNull
        public static final MissingPushTokenError INSTANCE = new MissingPushTokenError();

        private MissingPushTokenError() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/vk/pushme/model/result/SubscriptionResult$NoAuthError;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "failedAccounts", "", "", "successAccounts", "<init>", "(Ljava/util/Set;Ljava/util/Set;)V", "getFailedAccounts", "()Ljava/util/Set;", "getSuccessAccounts", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class NoAuthError extends SubscriptionResult {

        @NotNull
        private final Set<String> failedAccounts;

        @NotNull
        private final Set<String> successAccounts;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NoAuthError(@NotNull Set<String> failedAccounts, @NotNull Set<String> successAccounts) {
            super(null);
            Intrinsics.checkNotNullParameter(failedAccounts, "failedAccounts");
            Intrinsics.checkNotNullParameter(successAccounts, "successAccounts");
            this.failedAccounts = failedAccounts;
            this.successAccounts = successAccounts;
        }

        @NotNull
        public final Set<String> getFailedAccounts() {
            return this.failedAccounts;
        }

        @NotNull
        public final Set<String> getSuccessAccounts() {
            return this.successAccounts;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/SubscriptionResult$OK;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OK extends SubscriptionResult {

        @NotNull
        public static final OK INSTANCE = new OK();

        private OK() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/model/result/SubscriptionResult$UnknownError;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnknownError extends SubscriptionResult {

        @NotNull
        private final Throwable t;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnknownError(@NotNull Throwable t10) {
            super(null);
            Intrinsics.checkNotNullParameter(t10, "t");
            this.t = t10;
        }

        @NotNull
        public final Throwable getT() {
            return this.t;
        }
    }

    public /* synthetic */ SubscriptionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SubscriptionResult() {
    }
}
