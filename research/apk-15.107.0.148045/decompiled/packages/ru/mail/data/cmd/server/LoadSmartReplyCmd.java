package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.database.HasSmartRepliesDbCommand;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.network.AccountAndIDParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ \u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\f2\u0010\u0010\r\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\f0\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/LoadSmartReplyCmd;", "Lru/mail/data/cmd/server/BaseLoadSmartReplyCmd;", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", RemoteMessageConst.MSGID, "", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;)V", "isCheck", "", "R", "cmd", "Lru/mail/mailbox/cmd/Command;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadSmartReplyCmd extends BaseLoadSmartReplyCmd {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadSmartReplyCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext, @NotNull String msgId) {
        super(context, mailboxContext, msgId);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        addCommand(new HasSmartRepliesDbCommand(context, new AccountAndIDParams(msgId, mailboxContext.getProfile().getLogin())));
    }

    @Override // ru.mail.data.cmd.server.BaseLoadSmartReplyCmd
    public <R> boolean isCheck(@NotNull Command<?, R> cmd) {
        Intrinsics.checkNotNullParameter(cmd, "cmd");
        return cmd instanceof HasSmartRepliesDbCommand;
    }
}
