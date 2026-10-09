package com.vk.pushme.model.result;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0006\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult;", "", "<init>", "()V", "OK", "ChangesNotFound", "SubscriptionNotFoundError", "NoAuthError", "MissingPushTokenError", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/model/result/EditSubscriptionResult$ChangesNotFound;", "Lcom/vk/pushme/model/result/EditSubscriptionResult$MissingPushTokenError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult$NoAuthError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult$OK;", "Lcom/vk/pushme/model/result/EditSubscriptionResult$SubscriptionNotFoundError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class EditSubscriptionResult {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$ChangesNotFound;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ChangesNotFound extends EditSubscriptionResult {

        @NotNull
        public static final ChangesNotFound INSTANCE = new ChangesNotFound();

        private ChangesNotFound() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$MissingPushTokenError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MissingPushTokenError extends EditSubscriptionResult {

        @NotNull
        public static final MissingPushTokenError INSTANCE = new MissingPushTokenError();

        private MissingPushTokenError() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$NoAuthError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class NoAuthError extends EditSubscriptionResult {

        @NotNull
        public static final NoAuthError INSTANCE = new NoAuthError();

        private NoAuthError() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$OK;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OK extends EditSubscriptionResult {

        @NotNull
        public static final OK INSTANCE = new OK();

        private OK() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$SubscriptionNotFoundError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "account", "", "application", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getApplication", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SubscriptionNotFoundError extends EditSubscriptionResult {

        @NotNull
        private final String account;

        @NotNull
        private final String application;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SubscriptionNotFoundError(@NotNull String account, @NotNull String application) {
            super(null);
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(application, "application");
            this.account = account;
            this.application = application;
        }

        public static /* synthetic */ SubscriptionNotFoundError copy$default(SubscriptionNotFoundError subscriptionNotFoundError, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = subscriptionNotFoundError.account;
            }
            if ((i10 & 2) != 0) {
                str2 = subscriptionNotFoundError.application;
            }
            return subscriptionNotFoundError.copy(str, str2);
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
        public final SubscriptionNotFoundError copy(@NotNull String account, @NotNull String application) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(application, "application");
            return new SubscriptionNotFoundError(account, application);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubscriptionNotFoundError)) {
                return false;
            }
            SubscriptionNotFoundError subscriptionNotFoundError = (SubscriptionNotFoundError) other;
            return Intrinsics.areEqual(this.account, subscriptionNotFoundError.account) && Intrinsics.areEqual(this.application, subscriptionNotFoundError.application);
        }

        @NotNull
        public final String getAccount() {
            return this.account;
        }

        @NotNull
        public final String getApplication() {
            return this.application;
        }

        public int hashCode() {
            return (this.account.hashCode() * 31) + this.application.hashCode();
        }

        @NotNull
        public String toString() {
            return "SubscriptionNotFoundError(account=" + this.account + ", application=" + this.application + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/model/result/EditSubscriptionResult$UnknownError;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnknownError extends EditSubscriptionResult {

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

    public /* synthetic */ EditSubscriptionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private EditSubscriptionResult() {
    }
}
