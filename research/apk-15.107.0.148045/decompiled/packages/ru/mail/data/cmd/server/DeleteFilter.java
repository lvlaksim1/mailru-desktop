package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "filters", "remove"})
public class DeleteFilter extends PostServerRequest<Params, EmptyResult> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getMIdsStr", method = HttpMethod.POST, name = "ids", useGetter = true)
        private final String[] mIds;

        public Params(AccountInfo accountInfo, FolderState folderState, @Nullable String... strArr) {
            super(accountInfo, folderState);
            this.mIds = strArr;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && super.equals(obj) && Arrays.equals(this.mIds, ((Params) obj).mIds);
        }

        public String getMIdsStr() {
            JSONArray jSONArray = new JSONArray();
            int i10 = 0;
            while (true) {
                String[] strArr = this.mIds;
                if (i10 >= strArr.length) {
                    return jSONArray.toString();
                }
                jSONArray.put(strArr[i10]);
                i10++;
            }
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String[] strArr = this.mIds;
            return iHashCode + (strArr != null ? Arrays.hashCode(strArr) : 0);
        }
    }

    public DeleteFilter(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
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

    public DeleteFilter(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, EmptyResult>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, EmptyResult>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.DeleteFilter.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onResponseOk(NetworkCommand.Response response) {
                try {
                    JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
                    if (((Params) DeleteFilter.this.getParams()).mIds.length != jSONArray.length()) {
                        return new CommandStatus.ERROR();
                    }
                    for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                        if (!jSONArray.getString(i10).equals(((Params) DeleteFilter.this.getParams()).mIds[i10])) {
                            return new CommandStatus.ERROR();
                        }
                    }
                    return new CommandStatus.OK(new EmptyResult());
                } catch (JSONException unused) {
                    return new CommandStatus.ERROR();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
