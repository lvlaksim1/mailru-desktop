package ru.mail.util.push;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.MailPriority;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public abstract class LocalNotificationsBundle {
    private PushMessage createDeletedMessagePush(MailMessage mailMessage) {
        DeleteNotificationPush deleteNotificationPush = new DeleteNotificationPush();
        deleteNotificationPush.setProfileId(mailMessage.getAccountName());
        deleteNotificationPush.setMessageId(mailMessage.getMailMessageId());
        return deleteNotificationPush;
    }

    private PushMessage createNewMessagePush(MailMessage mailMessage) {
        NewMailPush newMailPush = new NewMailPush();
        newMailPush.setMessageId(mailMessage.getId());
        newMailPush.setFolderId(mailMessage.getFolderId());
        newMailPush.setSnippet(mailMessage.getSnippet());
        newMailPush.setTimestamp(mailMessage.getDate().getTime());
        newMailPush.setSender(mailMessage.getFrom());
        newMailPush.setSubject(mailMessage.getSubject());
        newMailPush.setHasAttachments(mailMessage.hasAttach());
        newMailPush.setIsImportant(mailMessage.getPriority() == MailPriority.HIGH);
        newMailPush.setThreadId(mailMessage.getMailThreadId());
        newMailPush.setProfileId(mailMessage.getAccountName());
        return newMailPush;
    }

    public abstract List<MailMessage> getMessagesToHide();

    public abstract List<MailMessage> getMessagesToShow();

    protected void populate(LocalPushHandler localPushHandler) {
        LinkedList linkedList = new LinkedList();
        Iterator<MailMessage> it = getMessagesToShow().iterator();
        while (it.hasNext()) {
            linkedList.add(createNewMessagePush(it.next()));
        }
        Iterator<MailMessage> it2 = getMessagesToHide().iterator();
        while (it2.hasNext()) {
            linkedList.add(createDeletedMessagePush(it2.next()));
        }
        localPushHandler.handleLocalPush(linkedList);
    }
}
