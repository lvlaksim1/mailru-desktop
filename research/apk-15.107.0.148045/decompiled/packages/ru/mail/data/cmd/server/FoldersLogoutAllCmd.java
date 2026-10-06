package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.sun.mail.imap.IMAPStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.database.AllFoldersWithPasswordCommand;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.DependentStatusCmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JA\u0010\b\u001a\u0004\u0018\u0001H\t\"\n\b\u0000\u0010\t*\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\t\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/FoldersLogoutAllCmd;", "Lru/mail/serverapi/DependentStatusCmd;", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;)V", "onExecuteCommand", "R", "", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "priority", "Lru/mail/mailbox/cmd/Priority;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/Priority;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FoldersLogoutAllCmd extends DependentStatusCmd {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldersLogoutAllCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext) {
        super(context, (Class<?>) FoldersLogoutCommand.class, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        String login = mailboxContext.getProfile().getLogin();
        Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
        addCommand(new AllFoldersWithPasswordCommand(context, login));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <R> R onExecuteCommand(@Nullable Command<?, R> command, @NotNull Priority priority, @Nullable ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        R r10 = (R) super.onExecuteCommand(command, priority, selector);
        if (command instanceof AllFoldersWithPasswordCommand) {
            Intrinsics.checkNotNull(r10, "null cannot be cast to non-null type ru.mail.data.cmd.database.AsyncDbHandler.CommonResponse<ru.mail.data.entities.MailBoxFolder, kotlin.Int>");
            List list = ((AsyncDbHandler.CommonResponse) r10).getList();
            List list2 = list;
            if (list2 != null && !list2.isEmpty()) {
                String login = getLogin();
                CommonDataManager commonDataManagerFrom = CommonDataManager.from(getContext());
                Intrinsics.checkNotNullExpressionValue(commonDataManagerFrom, "from(...)");
                AccountInfo accountInfo = new AccountInfo(login, commonDataManagerFrom);
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                addCommand(new FoldersLogoutCommand(context, new FoldersLogoutCommand.Params(list, accountInfo, getFolderState())));
                return r10;
            }
            setResult(new CommandStatus.OK());
        }
        return r10;
    }
}
