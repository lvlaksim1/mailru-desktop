package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Collection;
import ru.mail.data.cmd.server.parser.SmartStatusParser;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.FoldersNotifyer;
import ru.mail.util.FolderMatcher;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Deprecated
public class SmartMessagesStatusCommand extends BatchSmartStatusCommand<MailMessage> {
    public SmartMessagesStatusCommand(Context context, BatchSmartStatusCommand.Params params, boolean z10, boolean z11, boolean z12, FoldersNotifyer foldersNotifyer, boolean z13, FolderMatcher folderMatcher) {
        super(context, params, z10, z11, z12, foldersNotifyer, z13, folderMatcher);
    }

    @Override // ru.mail.data.cmd.server.BatchSmartStatusCommand
    protected Collection<MailMessage> getItemsFromStatus(SmartStatusParser.Result result) {
        return result.getMessages();
    }
}
