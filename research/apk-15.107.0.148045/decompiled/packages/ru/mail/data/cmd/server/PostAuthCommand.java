package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.data.cmd.database.UpdateSessionWithProfileCmd;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.mailbox.cmd.CommandGroup;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PostAuthCommand extends CommandGroup {
    public PostAuthCommand(Context context, MailboxProfile mailboxProfile) {
        addCommand(new UpdateSessionWithProfileCmd(context, mailboxProfile));
    }
}
