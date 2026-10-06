package ru.mail.data.cmd.server;

import java.util.Collection;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MetaThread;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public interface RequestStatusResult {
    Collection<MailBoxFolder> getFolders();

    Collection<MailMessage> getMessages();

    Collection<MetaThread> getMetaThreads();

    Collection<MailThread> getThreads();
}
