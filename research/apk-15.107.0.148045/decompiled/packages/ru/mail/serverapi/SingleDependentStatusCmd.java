package ru.mail.serverapi;

import android.content.Context;
import ru.mail.mailbox.cmd.Command;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class SingleDependentStatusCmd extends DependentStatusCmd {
    public SingleDependentStatusCmd(Context context, Command command, String str, FolderState folderState) {
        super(context, false, command.getClass(), str, folderState);
        addCommand(command);
    }
}
