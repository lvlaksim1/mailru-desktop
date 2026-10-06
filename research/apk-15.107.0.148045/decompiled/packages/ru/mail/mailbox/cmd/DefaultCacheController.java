package ru.mail.mailbox.cmd;

import java.util.concurrent.Future;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class DefaultCacheController implements CacheController {
    @Override // ru.mail.mailbox.cmd.CacheController
    public void onExecutionDone(Command<?, ?> command, Future<?> future, CacheController.ExecutorApi executorApi) {
        executorApi.remove();
    }

    @Override // ru.mail.mailbox.cmd.CacheController
    public void onExecutionStarted(Command<?, ?> command, Future<?> future) {
    }
}
