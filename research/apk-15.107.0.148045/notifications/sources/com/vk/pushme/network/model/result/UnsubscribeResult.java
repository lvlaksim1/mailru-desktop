package com.vk.pushme.network.model.result;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "", "<init>", "()V", "OK", GoogleErrorDescriptions.SERVER_ERROR, GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/network/model/result/UnsubscribeResult$OK;", "Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;", "Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class UnsubscribeResult {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/network/model/result/UnsubscribeResult$OK;", "Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "<init>", "()V", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OK extends UnsubscribeResult {

        @NotNull
        public static final OK INSTANCE = new OK();

        private OK() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/vk/pushme/network/model/result/UnsubscribeResult$ServerError;", "Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "code", "", "message", "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ServerError extends UnsubscribeResult {
        private final int code;

        @NotNull
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ServerError(int i10, @NotNull String message) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.code = i10;
            this.message = message;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/network/model/result/UnsubscribeResult$UnknownError;", "Lcom/vk/pushme/network/model/result/UnsubscribeResult;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UnknownError extends UnsubscribeResult {

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

    public /* synthetic */ UnsubscribeResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private UnsubscribeResult() {
    }
}
