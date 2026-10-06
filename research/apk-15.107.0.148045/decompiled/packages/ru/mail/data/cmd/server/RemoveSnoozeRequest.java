package ru.mail.data.cmd.server;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.cmd.reminder.SyncSnoozedMessagesCommandGroup;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/RemoveSnoozeRequest;", "Lru/mail/data/cmd/server/AbstractSnoozeRequest;", "Lru/mail/data/cmd/server/RemoveSnoozeRequest$Params;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/RemoveSnoozeRequest$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Params", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "snoozes", "remove"})
public final class RemoveSnoozeRequest extends AbstractSnoozeRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/RemoveSnoozeRequest$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/logic/cmd/reminder/SyncSnoozedMessagesCommandGroup$RollbackParam;", "context", "Lru/mail/logic/content/MailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "mailId", "", "prevSnoozeDate", "", "<init>", "(Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;Ljava/lang/String;J)V", "getMailId", "()Ljava/lang/String;", "getPrevSnoozeDate", "()J", "equals", "", "other", "", "hashCode", "", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams implements SyncSnoozedMessagesCommandGroup.RollbackParam {

        @Param(method = HttpMethod.POST, name = "id")
        @NotNull
        private final String mailId;
        private final long prevSnoozeDate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull MailboxContext context, @NotNull DataManager dataManager, @NotNull String mailId, long j10) {
            super(MailboxContextUtil.getAccountInfo(context, dataManager), MailboxContextUtil.getFolderState(context));
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            Intrinsics.checkNotNullParameter(mailId, "mailId");
            this.mailId = mailId;
            this.prevSnoozeDate = j10;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params) || !super.equals(other)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(getMailId(), params.getMailId()) && getPrevSnoozeDate() == params.getPrevSnoozeDate();
        }

        @Override // ru.mail.logic.cmd.reminder.SyncSnoozedMessagesCommandGroup.RollbackParam
        @NotNull
        public String getMailId() {
            return this.mailId;
        }

        @Override // ru.mail.logic.cmd.reminder.SyncSnoozedMessagesCommandGroup.RollbackParam
        public long getPrevSnoozeDate() {
            return this.prevSnoozeDate;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((super.hashCode() * 31) + getMailId().hashCode()) * 31) + Long.hashCode(getPrevSnoozeDate());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoveSnoozeRequest(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }
}
