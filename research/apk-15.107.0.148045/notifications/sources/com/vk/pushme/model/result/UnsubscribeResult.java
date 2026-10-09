package com.vk.pushme.model.result;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/model/result/UnsubscribeResult;", "", "<init>", "()V", "OK", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/model/result/UnsubscribeResult$OK;", "Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class UnsubscribeResult {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/model/result/UnsubscribeResult$OK;", "Lcom/vk/pushme/model/result/UnsubscribeResult;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OK extends UnsubscribeResult {

        @NotNull
        public static final OK INSTANCE = new OK();

        private OK() {
            super(null);
        }

        @NotNull
        public String toString() {
            return "OK";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/vk/pushme/model/result/UnsubscribeResult$UnknownError;", "Lcom/vk/pushme/model/result/UnsubscribeResult;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        @NotNull
        public String toString() {
            return "UnknownError(t=" + this.t + ")";
        }
    }

    public /* synthetic */ UnsubscribeResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private UnsubscribeResult() {
    }
}
