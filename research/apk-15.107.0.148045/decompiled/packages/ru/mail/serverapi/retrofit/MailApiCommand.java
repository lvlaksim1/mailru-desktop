package ru.mail.serverapi.retrofit;

import com.google.android.gms.ads.RequestConfiguration;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.VpnBlockedException;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.retrofit.session.SessionError;
import ru.mail.serverapi.retrofit.session.SessionException;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u0012\u0012\u0004\u0012\u0002H\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004B\u000f\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0014\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fH¦@¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001a\u00028\u00022\u0006\u0010\u0012\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fH\u0014J\u0014\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lru/mail/serverapi/retrofit/MailApiCommand;", "P", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "E", "Lru/mail/mailbox/cmd/Command;", "Lru/mail/mailbox/cmd/CommandStatus;", "params", "<init>", "(Ljava/lang/Object;)V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "executeRequest", "Lru/mail/network/retrofit/ApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformDataToDomainModel", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "processResponse", "processSessionException", OkListenerKt.KEY_EXCEPTION, "Lru/mail/serverapi/retrofit/session/SessionException;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class MailApiCommand<P, T, E> extends Command<P, CommandStatus<?>> {

    /* JADX INFO: renamed from: ru.mail.serverapi.retrofit.MailApiCommand$onExecute$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lru/mail/mailbox/cmd/CommandStatus;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.serverapi.retrofit.MailApiCommand$onExecute$1", f = "MailApiCommand.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CommandStatus<?>>, Object> {
        int label;
        final /* synthetic */ MailApiCommand<P, T, E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MailApiCommand<P, T, E> mailApiCommand, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = mailApiCommand;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                MailApiCommand<P, T, E> mailApiCommand = this.this$0;
                this.label = 1;
                obj = mailApiCommand.executeRequest(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return this.this$0.processResponse((ApiResult) obj);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CommandStatus<?>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public MailApiCommand(P p10) {
        super(p10);
    }

    private final CommandStatus<?> processSessionException(SessionException exception) {
        SessionError reason = exception.getReason();
        if (reason instanceof SessionError.NoAuth) {
            return new NetworkCommandStatus.NO_AUTH(new NoAuthInfo(((SessionError.NoAuth) reason).getLogin(), null, null));
        }
        if (reason instanceof SessionError.SwitchToImap) {
            return new MailCommandStatus.SWITCH_TO_IMAP(((SessionError.SwitchToImap) reason).getImapSettings());
        }
        if (reason instanceof SessionError.ConnectionError) {
            return new NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED();
        }
        if (reason instanceof SessionError.Unexpected) {
            return new CommandStatus.ERROR();
        }
        throw new NoWhenBranchMatchedException();
    }

    @Nullable
    public abstract Object executeRequest(@NotNull Continuation<? super ApiResult<T>> continuation);

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    protected CommandStatus<?> processResponse(@NotNull ApiResult<T> result) throws Throwable {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof ApiResult.Success) {
            return new CommandStatus.OK(transformDataToDomainModel(((ApiResult.Success) result).getData()));
        }
        if (result instanceof ApiResult.Error) {
            ApiResult.Error error = (ApiResult.Error) result;
            return error.getCode() == 304 ? new CommandStatus.NOT_MODIFIED() : new CommandStatus.ERROR_WITH_STATUS_CODE(error.getCode());
        }
        if (!(result instanceof ApiResult.Exception)) {
            throw new NoWhenBranchMatchedException();
        }
        Throwable cause = ((ApiResult.Exception) result).getCause();
        if (cause instanceof SessionException) {
            return processSessionException((SessionException) cause);
        }
        if (cause instanceof VpnBlockedException) {
            return new NetworkCommandStatus.VPN_BLOCKED("Blocked by VPN");
        }
        if (cause instanceof IOException) {
            return new NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED(cause);
        }
        throw cause;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor("NETWORK");
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    public abstract E transformDataToDomainModel(T result);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<?> onExecute(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        return (CommandStatus) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(this, null), 1, null);
    }
}
