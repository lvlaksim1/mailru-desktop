package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.data.cmd.database.sync.MarkSyncOperation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "marks", "all"})
public class MarkAllMessageCommand extends PostServerRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("MarkAllMessageCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "action")
        private final String mAction;

        @Param(method = HttpMethod.POST, name = "folder")
        private final long mFolderId;

        @Param(method = HttpMethod.POST, name = "older_than")
        private final Long mMarkTime;

        @Param(getterName = "getMarks", method = HttpMethod.POST, name = "marks", useGetter = true)
        private final String[] mMarks;

        private Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, long j10, @Nullable Long l10, @NotNull String str, @Nullable String... strArr) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mFolderId = j10;
            this.mMarks = strArr;
            this.mAction = str;
            this.mMarkTime = l10;
        }

        public static Params markSyncOperation(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, long j10, long j11, @NotNull MarkSyncOperation markSyncOperation) {
            return new Params(mailboxContext, dataManager, j10, Long.valueOf(j11 + 1), markSyncOperation.getAction(), markSyncOperation.getMark());
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mFolderId != params.mFolderId || !Arrays.equals(this.mMarks, params.mMarks)) {
                return false;
            }
            String str = this.mAction;
            String str2 = params.mAction;
            if (str != null) {
                return str.equals(str2);
            }
            return str2 == null;
        }

        public long getFolderId() {
            return this.mFolderId;
        }

        public String getMarks() {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.mMarks) {
                jSONArray.put(str);
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            long j10 = this.mFolderId;
            int iHashCode2 = (((iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31) + Arrays.hashCode(this.mMarks)) * 31;
            String str = this.mAction;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }
    }

    public MarkAllMessageCommand(Context context, Params params) {
        super(context, params, MigrateToPostUtils.is12155Enabled(context));
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
