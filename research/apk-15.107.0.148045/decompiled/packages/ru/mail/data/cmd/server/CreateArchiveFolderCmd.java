package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", BaseSettingsActivity.KEY_PREF_ARCHIVE, "ensure"})
public class CreateArchiveFolderCmd extends PostServerRequest<Params, Long> {
    private static final Log LOG = Log.getLog("CreateArchiveFolderCmd");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.GET, name = "folder", type = Param.Type.STRING)
        private final Long folderId;

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @Nullable Long l10) {
            super(accountInfo, folderState);
            this.folderId = l10;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Params) && super.equals(obj)) {
                return Objects.equals(this.folderId, ((Params) obj).folderId);
            }
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            Long l10 = this.folderId;
            return iHashCode + (l10 != null ? l10.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendEmail() {
            return true;
        }
    }

    public CreateArchiveFolderCmd(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public CreateArchiveFolderCmd(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Long>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Long onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            return Long.valueOf(Long.parseLong(new JSONObject(response.getRespString()).getString("body")));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
