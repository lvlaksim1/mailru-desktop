package ru.mail.data.cmd.server;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.entities.SendInlineAttach2cid;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.ui.fragments.adapter.AttachmentsEditor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class TornadoSendEditableParams extends TornadoSendParams {
    public TornadoSendEditableParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
        super(mailboxContext, dataManager);
    }

    public abstract void setAttachmentsEditor(AttachmentsEditor attachmentsEditor);

    public abstract void setBcc(String str);

    public abstract TornadoSendParams setBlockQuote(String str);

    public abstract void setBodyHtmlWithQuote(String str);

    public abstract void setBodyText(String str);

    public abstract void setCc(String str);

    public abstract void setDraft(String str);

    public abstract void setForward(String str);

    public abstract void setFrom(String str);

    public abstract void setHasInlineAttaches(boolean z10);

    public abstract void setId(String str);

    public abstract void setOriginalBodyHtml(String str);

    public abstract void setPriority(TornadoSendParams.Priority priority);

    public abstract void setProgressListener(ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> progressListener);

    public abstract void setReadVerify(boolean z10);

    public abstract void setRedirect(String str);

    public abstract void setReply(String str);

    public abstract void setSendAttaches2cid(Collection<SendInlineAttach2cid> collection);

    public abstract void setSendDate(long j10);

    public abstract void setSubject(String str);

    public abstract void setTo(String str);
}
