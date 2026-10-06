package ru.mail.mailbox.cmd;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.superapp.browser.ui.VkUIContactsDelegate;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kit.result.tools.Result;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0005!\"#$%B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J¥\u0001\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0001\u0010\u00062!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u0002H\u00060\b2#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00060\b2!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00060\b2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00060\bH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011J0\u0010\u0005\u001a\u00028\u00002#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\u0012J%\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0001\u0010\u00062\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00060\u0014¢\u0006\u0002\u0010\u0015J+\u0010\u0016\u001a\u00020\u00172#\u0010\u0018\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u00170\bJA\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0000\"\u0004\b\u0001\u0010\u00062-\u0010\u0007\u001a)\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u00000\bJ\u000b\u0010\u001a\u001a\u00028\u0000¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eJ\u0014\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\r0 \u0082\u0001\u0004&'()\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult;", "R", "", "<init>", "()V", "handle", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "successHandler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "result", "exceptionHandler", "", OkListenerKt.KEY_EXCEPTION, "cancelledHandler", "interruptedHandler", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Lru/mail/mailbox/cmd/ExecutionResult$Handler;", "(Lru/mail/mailbox/cmd/ExecutionResult$Handler;)Ljava/lang/Object;", "handleError", "", BatchApiRequest.FIELD_NAME_ON_ERROR, "successMapper", "successOrThrow", "()Ljava/lang/Object;", "onComplete", "callback", "Lkotlin/Function0;", "asResult", "Lru/mail/kit/result/tools/Result;", "Success", "Exception", VkUIContactsDelegate.CONTACTS_ERROR_CANCELLED, "Interrupted", "Handler", "Lru/mail/mailbox/cmd/ExecutionResult$Cancelled;", "Lru/mail/mailbox/cmd/ExecutionResult$Exception;", "Lru/mail/mailbox/cmd/ExecutionResult$Interrupted;", "Lru/mail/mailbox/cmd/ExecutionResult$Success;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExecutionResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExecutionResult.kt\nru/mail/mailbox/cmd/ExecutionResult\n*L\n1#1,97:1\n19#1,5:98\n*S KotlinDebug\n*F\n+ 1 ExecutionResult.kt\nru/mail/mailbox/cmd/ExecutionResult\n*L\n83#1:98,5\n*E\n"})
public abstract class ExecutionResult<R> {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult$Cancelled;", "R", "Lru/mail/mailbox/cmd/ExecutionResult;", OkListenerKt.KEY_EXCEPTION, "", "<init>", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Cancelled<R> extends ExecutionResult<R> {

        @NotNull
        private final Throwable exception;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Cancelled(@NotNull Throwable exception) {
            super(null);
            Intrinsics.checkNotNullParameter(exception, "exception");
            this.exception = exception;
        }

        public static /* synthetic */ Cancelled copy$default(Cancelled cancelled, Throwable th2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                th2 = cancelled.exception;
            }
            return cancelled.copy(th2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Throwable getException() {
            return this.exception;
        }

        @NotNull
        public final Cancelled<R> copy(@NotNull Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            return new Cancelled<>(exception);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Cancelled) && Intrinsics.areEqual(this.exception, ((Cancelled) other).exception);
        }

        @NotNull
        public final Throwable getException() {
            return this.exception;
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            return "Cancelled(exception=" + this.exception + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u001b\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult$Exception;", "R", "Lru/mail/mailbox/cmd/ExecutionResult;", OkListenerKt.KEY_EXCEPTION, "", "<init>", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Exception<R> extends ExecutionResult<R> {

        @Nullable
        private final Throwable exception;

        /* JADX WARN: Multi-variable type inference failed */
        public Exception() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Exception copy$default(Exception exception, Throwable th2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                th2 = exception.exception;
            }
            return exception.copy(th2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Throwable getException() {
            return this.exception;
        }

        @NotNull
        public final Exception<R> copy(@Nullable Throwable exception) {
            return new Exception<>(exception);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Exception) && Intrinsics.areEqual(this.exception, ((Exception) other).exception);
        }

        @Nullable
        public final Throwable getException() {
            return this.exception;
        }

        public int hashCode() {
            Throwable th2 = this.exception;
            if (th2 == null) {
                return 0;
            }
            return th2.hashCode();
        }

        @NotNull
        public String toString() {
            return "Exception(exception=" + this.exception + ")";
        }

        public Exception(@Nullable Throwable th2) {
            super(null);
            this.exception = th2;
        }

        public /* synthetic */ Exception(Throwable th2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : th2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00028\u00022\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\u00028\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u0015\u0010\n\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ\u0015\u0010\u000b\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\t¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult$Handler;", "R", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "success", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", OkListenerKt.KEY_EXCEPTION, "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "cancelled", "interrupted", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Handler<R, T> {
        T cancelled(@NotNull Throwable exception);

        T exception(@Nullable Throwable exception);

        T interrupted(@NotNull Throwable exception);

        T success(R result);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J\u0019\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult$Interrupted;", "R", "Lru/mail/mailbox/cmd/ExecutionResult;", OkListenerKt.KEY_EXCEPTION, "", "<init>", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Interrupted<R> extends ExecutionResult<R> {

        @NotNull
        private final Throwable exception;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Interrupted(@NotNull Throwable exception) {
            super(null);
            Intrinsics.checkNotNullParameter(exception, "exception");
            this.exception = exception;
        }

        public static /* synthetic */ Interrupted copy$default(Interrupted interrupted, Throwable th2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                th2 = interrupted.exception;
            }
            return interrupted.copy(th2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Throwable getException() {
            return this.exception;
        }

        @NotNull
        public final Interrupted<R> copy(@NotNull Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            return new Interrupted<>(exception);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Interrupted) && Intrinsics.areEqual(this.exception, ((Interrupted) other).exception);
        }

        @NotNull
        public final Throwable getException() {
            return this.exception;
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        @NotNull
        public String toString() {
            return "Interrupted(exception=" + this.exception + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lru/mail/mailbox/cmd/ExecutionResult$Success;", "R", "Lru/mail/mailbox/cmd/ExecutionResult;", "result", "<init>", "(Ljava/lang/Object;)V", "getResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lru/mail/mailbox/cmd/ExecutionResult$Success;", "equals", "", "other", "", "hashCode", "", "toString", "", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success<R> extends ExecutionResult<R> {
        private final R result;

        public Success(R r10) {
            super(null);
            this.result = r10;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Success copy$default(Success success, Object obj, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                obj = success.result;
            }
            return success.copy(obj);
        }

        public final R component1() {
            return this.result;
        }

        @NotNull
        public final Success<R> copy(R result) {
            return new Success<>(result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.result, ((Success) other).result);
        }

        public final R getResult() {
            return this.result;
        }

        public int hashCode() {
            R r10 = this.result;
            if (r10 == null) {
                return 0;
            }
            return r10.hashCode();
        }

        @NotNull
        public String toString() {
            return "Success(result=" + this.result + ")";
        }
    }

    public /* synthetic */ ExecutionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Result<R, Throwable> asResult() {
        if (this instanceof Success) {
            return Result.INSTANCE.success(((Success) this).getResult());
        }
        if (this instanceof Exception) {
            return Result.INSTANCE.failure(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            return Result.INSTANCE.failure(((Cancelled) this).getException());
        }
        if (!(this instanceof Interrupted)) {
            throw new NoWhenBranchMatchedException();
        }
        return Result.INSTANCE.failure(((Interrupted) this).getException());
    }

    public final <T> T handle(@NotNull Function1<? super R, ? extends T> successHandler, @NotNull Function1<? super Throwable, ? extends T> exceptionHandler, @NotNull Function1<? super Throwable, ? extends T> cancelledHandler, @NotNull Function1<? super Throwable, ? extends T> interruptedHandler) {
        Intrinsics.checkNotNullParameter(successHandler, "successHandler");
        Intrinsics.checkNotNullParameter(exceptionHandler, "exceptionHandler");
        Intrinsics.checkNotNullParameter(cancelledHandler, "cancelledHandler");
        Intrinsics.checkNotNullParameter(interruptedHandler, "interruptedHandler");
        if (this instanceof Success) {
            return successHandler.invoke((Object) ((Success) this).getResult());
        }
        if (this instanceof Exception) {
            return exceptionHandler.invoke(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            return cancelledHandler.invoke(((Cancelled) this).getException());
        }
        if (this instanceof Interrupted) {
            return interruptedHandler.invoke(((Interrupted) this).getException());
        }
        throw new NoWhenBranchMatchedException();
    }

    public final void handleError(@NotNull Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (this instanceof Exception) {
            onError.invoke(((Exception) this).getException());
        } else if (this instanceof Cancelled) {
            onError.invoke(((Cancelled) this).getException());
        } else if (this instanceof Interrupted) {
            onError.invoke(((Interrupted) this).getException());
        }
    }

    public final void onComplete(@NotNull Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this instanceof Exception) {
            throw new RuntimeException(((Exception) this).getException());
        }
        callback.invoke();
    }

    @NotNull
    public final <T> ExecutionResult<T> successMapper(@NotNull Function1<? super ExecutionResult<R>, ? extends ExecutionResult<T>> successHandler) {
        Intrinsics.checkNotNullParameter(successHandler, "successHandler");
        if (this instanceof Exception) {
            return new Exception(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            return new Cancelled(((Cancelled) this).getException());
        }
        if (this instanceof Interrupted) {
            return new Interrupted(((Interrupted) this).getException());
        }
        if (this instanceof Success) {
            return successHandler.invoke(this);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final R successOrThrow() throws ExecutionException {
        if (this instanceof Success) {
            return (R) ((Success) this).getResult();
        }
        if (this instanceof Exception) {
            throw new ExecutionException(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            Throwable exception = ((Cancelled) this).getException();
            CancellationException cancellationException = new CancellationException(exception != null ? String.valueOf(exception) : null);
            cancellationException.initCause(exception);
            throw cancellationException;
        }
        if (!(this instanceof Interrupted)) {
            throw new NoWhenBranchMatchedException();
        }
        Throwable exception2 = ((Interrupted) this).getException();
        CancellationException cancellationException2 = new CancellationException(exception2 != null ? String.valueOf(exception2) : null);
        cancellationException2.initCause(exception2);
        throw cancellationException2;
    }

    private ExecutionResult() {
    }

    public final R handle(@NotNull Function1<? super Throwable, ? extends R> exceptionHandler) {
        Intrinsics.checkNotNullParameter(exceptionHandler, "exceptionHandler");
        if (this instanceof Success) {
            return (R) ((Success) this).getResult();
        }
        if (this instanceof Exception) {
            return exceptionHandler.invoke(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            return exceptionHandler.invoke(((Cancelled) this).getException());
        }
        if (this instanceof Interrupted) {
            return exceptionHandler.invoke(((Interrupted) this).getException());
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> T handle(@NotNull Handler<R, T> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (this instanceof Success) {
            return (T) handler.success(((Success) this).getResult());
        }
        if (this instanceof Exception) {
            return (T) handler.exception(((Exception) this).getException());
        }
        if (this instanceof Cancelled) {
            return (T) handler.cancelled(((Cancelled) this).getException());
        }
        if (this instanceof Interrupted) {
            return (T) handler.interrupted(((Interrupted) this).getException());
        }
        throw new NoWhenBranchMatchedException();
    }
}
