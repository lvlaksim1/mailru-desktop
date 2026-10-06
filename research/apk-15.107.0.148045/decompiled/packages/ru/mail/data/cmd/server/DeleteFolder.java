package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", "remove"})
public class DeleteFolder extends UpdateFolder {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends UpdateFolder.Params {

        @Param(method = HttpMethod.POST, useGetter = true)
        private static final String ids = "";

        public Params(long j10, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(j10, null, accountInfo, folderState);
        }

        @Override // ru.mail.data.cmd.server.UpdateFolder.Params, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Params) && super.equals(obj);
        }

        public String getIds() {
            return Arrays.toString(new long[]{getId()});
        }

        @Override // ru.mail.data.cmd.server.UpdateFolder.Params, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return super.hashCode() * 31;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendEmail() {
            return true;
        }
    }

    public DeleteFolder(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.data.cmd.server.UpdateFolder, ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.data.cmd.server.UpdateFolder
    protected CommandStatus<?> processErrorResponse(NetworkCommand.Response response, NetworkCommand<UpdateFolder.Params, Long>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        try {
            String string = new JSONObject(response.getRespString()).getJSONObject("body").getJSONObject("ids[0]").getString("error");
            if ("not_open".equals(string)) {
                return networkCommandBaseDelegate.onFolderAccessDenied();
            }
            if ("not_exists".equals(string)) {
                return new MailCommandStatus.ERROR_FOLDER_NOT_EXIST(Long.valueOf(((UpdateFolder.Params) getParams()).getId()));
            }
            if ("invalid".equals(string)) {
                return new CommandStatus.ERROR();
            }
            return new CommandStatus.ERROR();
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
    }

    DeleteFolder(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
