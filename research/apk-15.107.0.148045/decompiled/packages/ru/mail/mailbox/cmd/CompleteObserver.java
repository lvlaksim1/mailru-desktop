package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class CompleteObserver<R> implements ObservableFuture.Observer<R> {
    @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
    public void onCancelled() {
        onComplete();
    }

    public abstract void onComplete();

    @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
    public void onDone(R r10) {
        onComplete();
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
    public void onError(Exception exc) {
        throw new RuntimeException(exc);
    }
}
