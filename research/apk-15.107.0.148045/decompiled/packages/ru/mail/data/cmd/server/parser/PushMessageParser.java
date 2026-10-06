package ru.mail.data.cmd.server.parser;

import android.content.Context;
import java.util.Date;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.MailPriority;
import ru.mail.util.Limits;
import ru.mail.util.push.NewMailPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PushMessageParser extends SingleMessageParser<NewMailPush> {
    private static final String MAIL_MSG_META = "{from PushMessageParser},";

    public PushMessageParser(Context context, boolean z10) {
        super(Limits.from(context).getSnippetLimit(), z10);
    }

    private static void initMetaData(MailMessage mailMessage) {
        mailMessage.setMetaData(MAIL_MSG_META + "server_id = " + mailMessage.getId());
    }

    @Override // ru.mail.data.cmd.server.parser.SingleMessageParser
    public MailMessage parse(NewMailPush newMailPush) {
        MailMessage mailMessage = new MailMessage();
        mailMessage.setId(newMailPush.getMessageId());
        initMetaData(mailMessage);
        mailMessage.setFolderId(newMailPush.getFolderId());
        mailMessage.setAccountName(newMailPush.getProfileId());
        mailMessage.setDate(new Date(newMailPush.getTimestamp()));
        mailMessage.setFrom(newMailPush.getSender());
        mailMessage.setTo("");
        mailMessage.setSubject(getTrimmedSubject(newMailPush.getSubject()));
        mailMessage.setSnippet(getTrimmedSnippet(newMailPush.getSnippet()));
        mailMessage.setPriority(MailPriority.NORMAL);
        mailMessage.setHasAttaches(newMailPush.hasAttachments());
        mailMessage.setUnread(true);
        mailMessage.setMailThreadId(newMailPush.getThreadId());
        mailMessage.setTransactionCategory(newMailPush.getMailCategory());
        return mailMessage;
    }
}
