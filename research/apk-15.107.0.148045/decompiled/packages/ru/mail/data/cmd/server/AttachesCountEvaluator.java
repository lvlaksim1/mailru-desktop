package ru.mail.data.cmd.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import ru.mail.analytics.LogEvaluator;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachesCountEvaluator implements LogEvaluator<CommandStatus<?>> {
    public static final String CLASS_NAME = "ru.mail.data.cmd.server.AttachesCountEvaluator";
    private boolean mAbort = true;

    @Override // ru.mail.analytics.LogEvaluator
    /* JADX INFO: renamed from: abort */
    public boolean getAbort() {
        return this.mAbort;
    }

    @Override // ru.mail.analytics.LogEvaluator
    public String evaluate(CommandStatus<?> commandStatus) {
        if (!NetworkCommand.statusOK(commandStatus)) {
            return null;
        }
        MailMessageContent mailMessageContent = (MailMessageContent) commandStatus.getData();
        HashSet hashSet = new HashSet();
        Collection<Attach> attachList = mailMessageContent.getAttachList();
        if (attachList == null) {
            return null;
        }
        Iterator<Attach> it = attachList.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getPartId());
        }
        if (hashSet.size() == attachList.size()) {
            return null;
        }
        this.mAbort = false;
        return "attaches size = " + attachList.size() + " partIds size = " + hashSet.size();
    }
}
