package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.database.HasThreadSmartRepliesDbCommand;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.network.AccountAndIDParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ \u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\r2\u0010\u0010\u000e\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\r0\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/LoadThreadSmartReplyCmd;", "Lru/mail/data/cmd/server/BaseLoadSmartReplyCmd;", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", RemoteMessageConst.MSGID, "", "threadId", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;Ljava/lang/String;)V", "isCheck", "", "R", "cmd", "Lru/mail/mailbox/cmd/Command;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadThreadSmartReplyCmd extends BaseLoadSmartReplyCmd {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadThreadSmartReplyCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext, @NotNull String msgId, @NotNull String threadId) {
        super(context, mailboxContext, msgId);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        Intrinsics.checkNotNullParameter(threadId, "threadId");
        addCommand(new HasThreadSmartRepliesDbCommand(context, new AccountAndIDParams(threadId, mailboxContext.getProfile().getLogin())));
    }

    @Override // ru.mail.data.cmd.server.BaseLoadSmartReplyCmd
    public <R> boolean isCheck(@NotNull Command<?, R> cmd) {
        Intrinsics.checkNotNullParameter(cmd, "cmd");
        return cmd instanceof HasThreadSmartRepliesDbCommand;
    }
}
