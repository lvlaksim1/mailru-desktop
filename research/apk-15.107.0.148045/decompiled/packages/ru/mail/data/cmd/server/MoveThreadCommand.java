package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", "threads", "move"})
public class MoveThreadCommand extends ThreadPostServerRequest<Params> {
    private static final Log LOG = Log.getLog("MoveThreadCommand");
    private static final String PARAM_KEY_FOLDER = "folder";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ThreadPostBaseParams {

        @Param(method = HttpMethod.POST, name = "folder")
        private final long mFolderId;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, long j10, @Nullable Collection<MailThreadRepresentation> collection) {
            super(mailboxContext, dataManager, collection);
            this.mFolderId = j10;
        }

        @Override // ru.mail.data.cmd.server.ThreadPostBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && super.equals(obj) && this.mFolderId == ((Params) obj).mFolderId;
        }

        public long getFolderId() {
            return this.mFolderId;
        }

        @Override // ru.mail.data.cmd.server.ThreadPostBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            long j10 = this.mFolderId;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        @Override // ru.mail.data.cmd.server.ThreadPostBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public String toString() {
            return super.toString() + ", dstFolderId:" + this.mFolderId;
        }
    }

    public MoveThreadCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public MoveThreadCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        LOG.d("SyncMovedThreadsCmd params : " + params);
    }
}
