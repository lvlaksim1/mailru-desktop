package ru.mail.data.cmd.server;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBase;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", ProductAction.ACTION_ADD})
public class CreateFolder extends UpdateFolder {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends UpdateFolder.Params {
        public Params(@Nullable String str, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(-1L, str, accountInfo, folderState);
        }

        @Override // ru.mail.data.cmd.server.UpdateFolder.Params
        protected JSONObject getJsonFolder() throws JSONException {
            JSONObject jsonFolder = super.getJsonFolder();
            jsonFolder.put(ToastDialogDto.KEY_TYPE_PARENT, "-1");
            jsonFolder.put("only_web", false);
            return jsonFolder;
        }
    }

    public CreateFolder(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    CreateFolder(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.data.cmd.server.UpdateFolder, ru.mail.network.NetworkCommand
    public ServerCommandBase<UpdateFolder.Params, Long>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.data.cmd.server.UpdateFolder, ru.mail.network.NetworkCommand
    public Long onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            return Long.valueOf(Long.parseLong(new JSONObject(response.getRespString()).getJSONArray("body").getString(0)));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
