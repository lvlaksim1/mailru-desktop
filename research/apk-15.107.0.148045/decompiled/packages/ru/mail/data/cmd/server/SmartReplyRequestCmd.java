package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.LoadStageFlagCommand;
import ru.mail.data.cmd.database.SaveSmartReplyCommand;
import ru.mail.data.entities.SmartReply;
import ru.mail.data.entities.SmartReplyInfo;
import ru.mail.kit.result.tools.Result;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.TypedCommandGroup;
import ru.mail.network.AccountAndIDParams;
import ru.mail.network.NetworkCommand;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ9\u0010\u0012\u001a\u0004\u0018\u0001H\u0013\"\n\b\u0000\u0010\u0013*\u0004\u0018\u00010\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0013\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/SmartReplyRequestCmd;", "Lru/mail/mailbox/cmd/TypedCommandGroup;", "Lru/mail/kit/result/tools/Result;", "Lru/mail/data/entities/SmartReplyInfo;", "", "context", "Landroid/content/Context;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", RemoteMessageConst.MSGID, "", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;)V", "getMsgId", "()Ljava/lang/String;", "replies", "", "Lru/mail/data/entities/SmartReply;", "onExecuteCommand", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "cmd", "Lru/mail/mailbox/cmd/Command;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSmartReplyRequestCmd.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SmartReplyRequestCmd.kt\nru/mail/data/cmd/server/SmartReplyRequestCmd\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,54:1\n1563#2:55\n1634#2,3:56\n*S KotlinDebug\n*F\n+ 1 SmartReplyRequestCmd.kt\nru/mail/data/cmd/server/SmartReplyRequestCmd\n*L\n39#1:55\n39#1:56,3\n*E\n"})
public final class SmartReplyRequestCmd extends TypedCommandGroup<Result<SmartReplyInfo, Unit>> {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final MailboxContext mailboxContext;

    @NotNull
    private final String msgId;

    @NotNull
    private List<SmartReply> replies;

    public SmartReplyRequestCmd(@NotNull Context context, @NotNull MailboxContext mailboxContext, @NotNull String msgId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        this.context = context;
        this.mailboxContext = mailboxContext;
        this.msgId = msgId;
        this.replies = CollectionsKt.emptyList();
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        Intrinsics.checkNotNullExpressionValue(commonDataManagerFrom, "from(...)");
        addCommand(new SmartReplyRequestCommand(context, new SmartReplyRequestCommand.Params(mailboxContext, commonDataManagerFrom, msgId), MigrateToPostUtils.is12176Enabled(context)));
    }

    @NotNull
    public final String getMsgId() {
        return this.msgId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.TypedCommandGroup
    @Nullable
    protected <T> T onExecuteCommand(@Nullable Command<?, T> cmd, @Nullable ExecutorSelector selector) {
        T t10 = (T) super.onExecuteCommand(cmd, selector);
        if (cmd instanceof SmartReplyRequestCommand) {
            if (NetworkCommand.statusOK(t10)) {
                Intrinsics.checkNotNull(t10, "null cannot be cast to non-null type ru.mail.mailbox.cmd.CommandStatus.OK<*>");
                V data = ((CommandStatus.OK) t10).getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.data.cmd.server.SmartReplyRequestCommand.SmartResponse");
                SmartReplyRequestCommand.SmartResponse smartResponse = (SmartReplyRequestCommand.SmartResponse) data;
                List<String> replies = smartResponse.getReplies();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(replies, 10));
                for (String str : replies) {
                    String login = this.mailboxContext.getProfile().getLogin();
                    Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                    arrayList.add(new SmartReply(login, this.msgId, str, smartResponse.isDefault()));
                }
                this.replies = arrayList;
                addCommand(new LoadStageFlagCommand(this.context, new AccountAndIDParams(this.msgId, this.mailboxContext.getProfile().getLogin())));
                addCommand(new SaveSmartReplyCommand(this.context, this.replies));
            } else {
                setResult(Result.INSTANCE.failure());
            }
        }
        if (cmd instanceof LoadStageFlagCommand) {
            Intrinsics.checkNotNull(t10, "null cannot be cast to non-null type ru.mail.data.cmd.database.AsyncDbHandler.CommonResponse<*, *>");
            Object obj = ((AsyncDbHandler.CommonResponse) t10).getObj();
            Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
            setResult(Result.INSTANCE.success(new SmartReplyInfo(this.replies, bool != null ? bool.booleanValue() : false)));
        }
        return t10;
    }
}
