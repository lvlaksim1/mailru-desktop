package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class CancelledException extends InterruptedException {
    private static final long serialVersionUID = 516034836393167142L;

    public CancelledException(Throwable th2) {
        initCause(th2);
    }
}
