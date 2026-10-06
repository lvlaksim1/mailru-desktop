package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface ExecutorSelector {
    CommandExecutor getCommandGroupExecutor();

    CommandExecutor getSingleCommandExecutor(String str);
}
