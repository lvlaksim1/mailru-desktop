package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "filters"})
public class RequestHasFiltersCommand extends ServerCommandBase<ServerCommandEmailParams, Boolean> {
    private final UserFiltersStorage mUserFiltersStorage;

    public RequestHasFiltersCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10, UserFiltersStorage userFiltersStorage) {
        this(context, serverCommandEmailParams, null, z10, userFiltersStorage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void saveHasFilters(boolean z10) {
        this.mUserFiltersStorage.saveHasFilters(((ServerCommandEmailParams) getParams()).getLogin(), z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    RequestHasFiltersCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, HostProvider hostProvider, boolean z10, UserFiltersStorage userFiltersStorage) {
        super(context, serverCommandEmailParams, hostProvider, z10);
        this.mUserFiltersStorage = userFiltersStorage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public Boolean onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
            int length = jSONArray.length();
            boolean z10 = false;
            for (int i10 = 0; i10 < length; i10++) {
                z10 |= jSONArray.getJSONObject(i10).getBoolean("enabled");
            }
            saveHasFilters(z10);
            return Boolean.valueOf(z10);
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
