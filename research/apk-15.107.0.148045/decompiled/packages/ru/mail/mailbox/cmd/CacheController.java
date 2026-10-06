package ru.mail.mailbox.cmd;

import java.util.concurrent.Future;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface CacheController {

    /* JADX INFO: compiled from: ProGuard */
    public interface ExecutorApi {
        void executeAnotherCommand(Command<?, ?> command);

        void remove();
    }

    void onExecutionDone(Command<?, ?> command, Future<?> future, ExecutorApi executorApi);

    void onExecutionStarted(Command<?, ?> command, Future<?> future);
}
