package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.sun.mail.imap.IMAPStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.GetSmartRepliesDbCommand;
import ru.mail.data.entities.SmartReplyInfo;
import ru.mail.kit.result.tools.Result;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.TypedCommandGroup;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ&\u0010\r\u001a\u00020\u000e\"\n\b\u0000\u0010\u000f*\u0004\u0018\u00010\u00102\u0010\u0010\u0011\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000f0\u0012H&J9\u0010\u0013\u001a\u0004\u0018\u0001H\u000f\"\n\b\u0000\u0010\u000f*\u0004\u0018\u00010\u00102\u0012\u0010\u0014\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lru/mail/data/cmd/server/BaseLoadSmartReplyCmd;", "Lru/mail/mailbox/cmd/TypedCommandGroup;", "Lru/mail/kit/result/tools/Result;", "Lru/mail/data/entities/SmartReplyInfo;", "", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", RemoteMessageConst.MSGID, "", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;)V", "isCheck", "", "R", "", "cmd", "Lru/mail/mailbox/cmd/Command;", "onExecuteCommand", IMAPStore.ID_COMMAND, "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseLoadSmartReplyCmd.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseLoadSmartReplyCmd.kt\nru/mail/data/cmd/server/BaseLoadSmartReplyCmd\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,57:1\n1#2:58\n*E\n"})
public abstract class BaseLoadSmartReplyCmd extends TypedCommandGroup<Result<SmartReplyInfo, Unit>> {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final MailboxContext mailboxContext;

    @NotNull
    private final String msgId;

    public BaseLoadSmartReplyCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext, @NotNull String msgId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        this.context = context;
        this.mailboxContext = mailboxContext;
        this.msgId = msgId;
        setResult(Result.INSTANCE.failure());
    }

    public abstract <R> boolean isCheck(@NotNull Command<?, R> cmd);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.TypedCommandGroup
    @Nullable
    protected <R> R onExecuteCommand(@Nullable Command<?, R> command, @Nullable ExecutorSelector selector) {
        R r10 = (R) super.onExecuteCommand(command, selector);
        Object obj = null;
        if (command != null && isCheck(command)) {
            Intrinsics.checkNotNull(r10, "null cannot be cast to non-null type ru.mail.data.cmd.database.AsyncDbHandler.CommonResponse<*, kotlin.String>");
            Object obj2 = ((AsyncDbHandler.CommonResponse) r10).getObj();
            if (!Intrinsics.areEqual(obj2 instanceof Boolean ? (Boolean) obj2 : null, Boolean.TRUE)) {
                setResult(Result.INSTANCE.success(new SmartReplyInfo(CollectionsKt.emptyList(), false)));
                return r10;
            }
            Context context = this.context;
            String login = this.mailboxContext.getProfile().getLogin();
            Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
            addCommand(new GetSmartRepliesDbCommand(context, new GetSmartRepliesDbCommand.Param(login, this.msgId)));
            return r10;
        }
        if (!(command instanceof GetSmartRepliesDbCommand)) {
            if (command instanceof SmartReplyRequestCmd) {
                if ((r10 instanceof Result.Success ? (Result.Success) r10 : null) != null) {
                    setResult(r10);
                }
            }
            return r10;
        }
        Intrinsics.checkNotNull(r10, "null cannot be cast to non-null type ru.mail.data.cmd.database.AsyncDbHandler.CommonResponse<ru.mail.data.entities.SmartReply, kotlin.Int>");
        AsyncDbHandler.CommonResponse commonResponse = (AsyncDbHandler.CommonResponse) r10;
        SmartReplyInfo smartReplyInfo = (SmartReplyInfo) commonResponse.getObj();
        if (smartReplyInfo != null && commonResponse.isSuccess() && !smartReplyInfo.getReplies().isEmpty()) {
            obj = smartReplyInfo;
        }
        if (obj != null) {
            setResult(Result.INSTANCE.success(obj));
            return r10;
        }
        addCommand(new SmartReplyRequestCmd(this.context, this.mailboxContext, this.msgId));
        return r10;
    }
}
