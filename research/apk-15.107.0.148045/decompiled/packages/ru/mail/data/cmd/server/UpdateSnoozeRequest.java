package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.cmd.reminder.SyncSnoozedMessagesCommandGroup;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0014¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/UpdateSnoozeRequest;", "Lru/mail/data/cmd/server/AbstractSnoozeRequest;", "Lru/mail/data/cmd/server/UpdateSnoozeRequest$Params;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/UpdateSnoozeRequest$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Params", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "snoozes", "update"})
public final class UpdateSnoozeRequest extends AbstractSnoozeRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0013\u001a\u00020\nH\u0007J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\u0007\u001a\u00020\b8\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u000b\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/UpdateSnoozeRequest$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/logic/cmd/reminder/SyncSnoozedMessagesCommandGroup$RollbackParam;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "mailId", "", "snoozeDate", "", "prevSnoozeDate", "<init>", "(Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;Ljava/lang/String;JJ)V", "getMailId", "()Ljava/lang/String;", "getSnoozeDate", "()J", "getPrevSnoozeDate", "getDateInSec", "equals", "", "other", "", "hashCode", "", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams implements SyncSnoozedMessagesCommandGroup.RollbackParam {

        @Param(method = HttpMethod.POST, name = "id")
        @NotNull
        private final String mailId;
        private final long prevSnoozeDate;

        @Param(getterName = "getDateInSec", method = HttpMethod.POST, name = "date", useGetter = true)
        private final long snoozeDate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @NotNull String mailId, long j10, long j11) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            Intrinsics.checkNotNullParameter(mailId, "mailId");
            this.mailId = mailId;
            this.snoozeDate = j10;
            this.prevSnoozeDate = j11;
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
            return Intrinsics.areEqual(getMailId(), params.getMailId()) && this.snoozeDate == params.snoozeDate && getPrevSnoozeDate() == params.getPrevSnoozeDate();
        }

        @Keep
        public final long getDateInSec() {
            return this.snoozeDate / ((long) 1000);
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

        public final long getSnoozeDate() {
            return this.snoozeDate;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((((super.hashCode() * 31) + getMailId().hashCode()) * 31) + Long.hashCode(this.snoozeDate)) * 31) + Long.hashCode(getPrevSnoozeDate());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateSnoozeRequest(@NotNull Context context, @NotNull Params params, boolean z10) {
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
