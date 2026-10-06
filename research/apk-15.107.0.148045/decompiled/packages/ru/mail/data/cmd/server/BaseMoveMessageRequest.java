package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.BaseMoveMessageRequest.Params;
import ru.mail.logic.cmd.MoveOperation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class BaseMoveMessageRequest<T extends Params> extends PostServerRequest<T, EmptyResult> implements MoveOperation {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.POST, name = "id", type = Param.Type.COLLECTION)
        public final String[] mIdsToMove;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String[] strArr) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mIdsToMove = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                return Arrays.equals(this.mIdsToMove, ((Params) obj).mIdsToMove);
            }
            return false;
        }

        public String[] getIdsToMove() {
            String[] strArr = this.mIdsToMove;
            return (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + Arrays.hashCode(this.mIdsToMove);
        }
    }

    public BaseMoveMessageRequest(Context context, T t10, HostProvider hostProvider) {
        super(context, t10, hostProvider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.logic.cmd.MoveOperation
    public String[] getMovedMessagesIds() {
        return (String[]) Arrays.copyOf(((Params) getParams()).mIdsToMove, ((Params) getParams()).mIdsToMove.length);
    }

    public BaseMoveMessageRequest(Context context, T t10) {
        super(context, t10);
    }
}
