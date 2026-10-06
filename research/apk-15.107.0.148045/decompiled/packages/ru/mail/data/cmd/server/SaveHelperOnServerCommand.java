package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.helpers.DTOHelper;
import ru.mail.logic.helpers.HelperUpdateTransaction;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "helpers", "update"})
public class SaveHelperOnServerCommand extends PostServerRequest<Params, DTOHelper> {
    private static final Log LOG = Log.getLog("SaveHelperOnServerCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "index")
        private final int mIndex;

        @Param(method = HttpMethod.POST, name = "update")
        private final String mUpdateInfo;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull HelperUpdateTransaction helperUpdateTransaction) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mIndex = helperUpdateTransaction.getIndex();
            this.mUpdateInfo = getUpdateInfo(helperUpdateTransaction);
        }

        private String getUpdateInfo(HelperUpdateTransaction helperUpdateTransaction) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (helperUpdateTransaction.hasState()) {
                    jSONObject.put("state", helperUpdateTransaction.getState());
                }
                if (helperUpdateTransaction.hasCount()) {
                    JSONObject jSONObject2 = new JSONObject();
                    if (helperUpdateTransaction.hasShowCount()) {
                        jSONObject2.put("show", helperUpdateTransaction.getShowCount());
                    }
                    if (helperUpdateTransaction.hasCloseCount()) {
                        jSONObject2.put("close", helperUpdateTransaction.getCloseCount());
                    }
                    jSONObject.put("count", jSONObject2);
                }
                if (!helperUpdateTransaction.hasWithoutTime()) {
                    jSONObject.put("time", true);
                }
                return jSONObject.toString();
            } catch (JSONException e10) {
                e10.printStackTrace();
                return "";
            }
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
            if (this.mIndex != params.mIndex) {
                return false;
            }
            String str = this.mUpdateInfo;
            String str2 = params.mUpdateInfo;
            if (str != null) {
                return str.equals(str2);
            }
            return str2 == null;
        }

        public int getIndex() {
            return this.mIndex;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((super.hashCode() * 31) + this.mIndex) * 31;
            String str = this.mUpdateInfo;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    public SaveHelperOnServerCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public DTOHelper onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (!jSONObject.has("status") || jSONObject.getInt("status") != 200) {
                throw new NetworkCommand.PostExecuteException();
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            return new DTOHelper(jSONObject2.getInt("index"), jSONObject2.getBoolean("state"), jSONObject2.getLong("time") * 1000, jSONObject2.getJSONObject("count").getInt("show"), jSONObject2.getJSONObject("count").getInt("close"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse update helper response", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
