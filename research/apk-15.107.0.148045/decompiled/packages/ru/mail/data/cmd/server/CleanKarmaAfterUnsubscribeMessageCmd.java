package ru.mail.data.cmd.server;

import android.content.Context;
import com.sun.mail.imap.IMAPStore;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.karma.CleanDeleteActionCommand;
import ru.mail.data.cmd.database.karma.CollectSendersCommand;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.TypedCommandGroup;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b¢\u0006\u0004\b\f\u0010\rJ9\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\n\b\u0000\u0010\u000f*\u0004\u0018\u00010\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/CleanKarmaAfterUnsubscribeMessageCmd;", "Lru/mail/mailbox/cmd/TypedCommandGroup;", "Lru/mail/mailbox/cmd/CommandStatus$OK;", "", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "extraLoginToClean", "", "mailIds", "", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;Ljava/util/List;)V", "onExecuteCommand", "R", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "getSenders", "r", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCleanKarmaAfterUnsubscribeMessageCmd.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CleanKarmaAfterUnsubscribeMessageCmd.kt\nru/mail/data/cmd/server/CleanKarmaAfterUnsubscribeMessageCmd\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,51:1\n1#2:52\n*E\n"})
public final class CleanKarmaAfterUnsubscribeMessageCmd extends TypedCommandGroup<CommandStatus.OK<Object>> {

    @NotNull
    private final Context context;

    @Nullable
    private final String extraLoginToClean;

    @NotNull
    private final MailboxContext mailboxContext;

    public CleanKarmaAfterUnsubscribeMessageCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext, @Nullable String str, @NotNull List<String> mailIds) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        Intrinsics.checkNotNullParameter(mailIds, "mailIds");
        this.context = context;
        this.mailboxContext = mailboxContext;
        this.extraLoginToClean = str;
        String login = mailboxContext.getProfile().getLogin();
        Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
        addCommand(new CollectSendersCommand(context, login, mailIds));
    }

    private final List<String> getSenders(Object r10) {
        AsyncDbHandler.CommonResponse commonResponse = r10 instanceof AsyncDbHandler.CommonResponse ? (AsyncDbHandler.CommonResponse) r10 : null;
        Object obj = commonResponse != null ? commonResponse.getObj() : null;
        if (obj instanceof List) {
            return (List) obj;
        }
        return null;
    }

    @Override // ru.mail.mailbox.cmd.TypedCommandGroup
    @Nullable
    protected <R> R onExecuteCommand(@Nullable Command<?, R> command, @Nullable ExecutorSelector selector) {
        List<String> listPlus;
        R r10 = (R) super.onExecuteCommand(command, selector);
        if (command instanceof CollectSendersCommand) {
            List<String> senders = getSenders(r10);
            if (senders != null) {
                Context context = this.context;
                String login = this.mailboxContext.getProfile().getLogin();
                Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                String str = this.extraLoginToClean;
                if (str != null && (listPlus = CollectionsKt.plus((Collection<? extends String>) senders, str)) != null) {
                    senders = listPlus;
                }
                addCommand(new CleanDeleteActionCommand(context, new CleanDeleteActionCommand.DeleteActionParams(login, senders)));
                return r10;
            }
        } else if (command instanceof CleanDeleteActionCommand) {
            setResult(new CommandStatus.OK());
        }
        return r10;
    }
}
