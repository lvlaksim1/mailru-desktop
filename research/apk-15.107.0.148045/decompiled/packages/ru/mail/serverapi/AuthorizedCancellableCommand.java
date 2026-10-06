package ru.mail.serverapi;

import android.content.Context;
import ru.mail.mailbox.cmd.CancelableCommand;
import ru.mail.mailbox.cmd.Command;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class AuthorizedCancellableCommand<T extends Command & CancelableCommand> extends AuthorizedCommandImpl implements CancelableCommand {
    private final T mCancelableCommand;

    public AuthorizedCancellableCommand(Context context, T t10, String str, FolderState folderState) {
        super(context, t10, str, folderState);
        this.mCancelableCommand = t10;
    }

    @Override // ru.mail.mailbox.cmd.CancelableCommand
    public boolean isAlreadyDone() {
        return this.mCancelableCommand.isAlreadyDone();
    }
}
