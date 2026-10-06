package ru.mail.data.cmd.server;

import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.adapter.AttachmentsEditor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class TornadoSendParams extends ServerCommandEmailParams {

    /* JADX INFO: compiled from: ProGuard */
    public enum Priority {
        HIGH(1),
        NORMAL(3),
        LOW(5);

        private int mValue;

        Priority(int i10) {
            this.mValue = i10;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public TornadoSendParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
        super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
    }

    public abstract TornadoSendEditableParams edit(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager);

    public abstract AttachmentsEditor getAttachmentsEditor();

    public abstract String getBcc();

    public abstract String getBlockQuote();

    public abstract String getBodyText();

    public abstract String getCc();

    public abstract String getFrom();

    public abstract String getId();

    public abstract String getOriginalBodyHtml();

    public abstract Priority getPriority();

    public abstract ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> getProgressListener();

    public abstract long getSendDate();

    public abstract String getSourceId();

    public abstract String getSubject();

    public abstract String getTo();

    public abstract boolean isHasInlineAttaches();

    public abstract boolean needReadVerify();
}
