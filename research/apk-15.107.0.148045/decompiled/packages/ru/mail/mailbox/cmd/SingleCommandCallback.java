package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public interface SingleCommandCallback {
    <T> void onSingleComplete(Command<?, T> command, T t10);
}
