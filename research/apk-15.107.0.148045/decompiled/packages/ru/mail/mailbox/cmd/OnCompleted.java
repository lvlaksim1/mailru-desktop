package ru.mail.mailbox.cmd;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H&¨\u0006\u0011"}, d2 = {"Lru/mail/mailbox/cmd/OnCompleted;", "R", "Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "<init>", "()V", "onSuccess", "", "result", "(Ljava/lang/Object;)V", "onException", "cause", "", "onCancelled", "onInterrupted", "onCompleted", "executionResult", "Lru/mail/mailbox/cmd/ExecutionResult;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class OnCompleted<R> implements ObservableFuture.ResultObserver<R> {
    @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
    public void onCancelled(@NotNull Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        onCompleted(new ExecutionResult.Cancelled(cause));
    }

    public abstract void onCompleted(@NotNull ExecutionResult<R> executionResult);

    @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
    public void onException(@NotNull Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        onCompleted(new ExecutionResult.Exception(cause));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
    public void onInterrupted(@NotNull Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        onCompleted(new ExecutionResult.Interrupted(cause));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
    public void onSuccess(R result) {
        onCompleted(new ExecutionResult.Success(result));
    }
}
