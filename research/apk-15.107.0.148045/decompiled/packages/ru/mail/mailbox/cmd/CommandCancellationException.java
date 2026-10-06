package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class CommandCancellationException extends RuntimeException {
    private static final long serialVersionUID = -2181170361888633127L;

    public CommandCancellationException() {
    }

    public CommandCancellationException(String str) {
        super(str);
    }

    public CommandCancellationException(String str, Throwable th2) {
        super(str, th2);
    }

    public CommandCancellationException(Throwable th2) {
        super(th2);
    }
}
