package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class CommandExecutionException extends RuntimeException {
    private static final long serialVersionUID = 7234014520111414317L;

    public CommandExecutionException() {
    }

    public CommandExecutionException(String str) {
        super(str);
    }

    public CommandExecutionException(String str, Throwable th2) {
        super(str, th2);
    }

    public CommandExecutionException(Throwable th2) {
        super(th2);
    }
}
