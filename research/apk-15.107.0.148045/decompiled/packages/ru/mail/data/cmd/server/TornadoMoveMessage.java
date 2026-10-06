package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "move"})
public class TornadoMoveMessage extends TornadoBaseMoveMessage<Params> {
    private final Log mLog;

    /* JADX INFO: compiled from: ProGuard */
    private abstract class CustomTornadoDelegate extends ServerCommandBase.TornadoDelegate {
        abstract CommandStatus<?> onFolderAccessDenied(long j10);

        private CustomTornadoDelegate() {
            super();
        }
    }

    public TornadoMoveMessage(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage, ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.TornadoMoveMessage.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int i10) {
                JSONObject badRequestBody;
                if (i10 == 400 && (badRequestBody = getBadRequestBody(getResponse().getRespString())) != null && badRequestBody.has("folder")) {
                    try {
                        JSONObject jSONObject = badRequestBody.getJSONObject("folder");
                        String strOptString = jSONObject.optString("error", "");
                        String strOptString2 = jSONObject.optString("value", "");
                        if (!strOptString2.isEmpty() && (strOptString.equalsIgnoreCase("restricted") || strOptString.equalsIgnoreCase("not_open"))) {
                            try {
                                return TornadoMoveMessage.this.getCustomDelegate().onFolderAccessDenied(Long.parseLong(strOptString2));
                            } catch (NumberFormatException e10) {
                                TornadoMoveMessage.this.mLog.e("Parsing folder ID failed", e10);
                                return super.processResponse(i10);
                            }
                        }
                    } catch (JSONException e11) {
                        TornadoMoveMessage.this.mLog.e("Parsing JSON failed", e11);
                    }
                }
                return super.processResponse(i10);
            }
        };
    }

    public TornadoMoveMessage(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        this.mLog = Log.getLog("TornadoMoveMessage");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage, ru.mail.network.NetworkCommand
    public CustomTornadoDelegate getCustomDelegate() {
        return new CustomTornadoDelegate() { // from class: ru.mail.data.cmd.server.TornadoMoveMessage.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.data.cmd.server.TornadoMoveMessage.CustomTornadoDelegate
            CommandStatus<?> onFolderAccessDenied(long j10) {
                ((Params) TornadoMoveMessage.this.getParams()).getFolderState().clearFolderLogin(j10);
                return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(j10));
            }
        };
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends TornadoBaseMoveMessage.Params {

        @Param(method = HttpMethod.POST, name = "folder", type = Param.Type.STRING)
        private final long mDestinationFolder;

        @Nullable
        private final String mFolderOwner;
        private final MailboxContext mMailboxContext;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, long j10, @Nullable String... strArr) {
            super(mailboxContext, dataManager, strArr);
            this.mDestinationFolder = j10;
            this.mMailboxContext = mailboxContext;
            this.mFolderOwner = str;
        }

        @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage.Params, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mDestinationFolder != params.mDestinationFolder) {
                return false;
            }
            if (getMailboxContext() == null ? params.getMailboxContext() == null : getMailboxContext().equals(params.getMailboxContext())) {
                return Objects.equals(this.mFolderOwner, params.mFolderOwner);
            }
            return false;
        }

        public long getFolderIdTo() {
            return this.mDestinationFolder;
        }

        @Nullable
        public String getFolderOwner() {
            return this.mFolderOwner;
        }

        public MailboxContext getMailboxContext() {
            return this.mMailboxContext;
        }

        @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage.Params, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            int iHashCode2 = getMailboxContext() != null ? getMailboxContext().hashCode() : 0;
            long j10 = this.mDestinationFolder;
            int i10 = (((iHashCode + iHashCode2) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            String str = this.mFolderOwner;
            return i10 + (str != null ? str.hashCode() : 0);
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, long j10, @Nullable String... strArr) {
            this(mailboxContext, dataManager, null, j10, strArr);
        }
    }
}
