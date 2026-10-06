package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.serverapi.AuthorizedCommandImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AuthorizedCommandWithProgress<T> extends AuthorizedCommandImpl implements ProgressListener<T> {
    private final ProgressListener<T> mProgressObserver;

    public AuthorizedCommandWithProgress(Context context, MailboxContext mailboxContext, boolean z10, ProgressListener<T> progressListener) {
        super(context, z10, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.mProgressObserver = progressListener;
    }

    @Override // ru.mail.mailbox.cmd.ProgressListener
    public void updateProgress(T t10) {
        ProgressListener<T> progressListener = this.mProgressObserver;
        if (progressListener != null) {
            progressListener.updateProgress(t10);
        }
    }
}
