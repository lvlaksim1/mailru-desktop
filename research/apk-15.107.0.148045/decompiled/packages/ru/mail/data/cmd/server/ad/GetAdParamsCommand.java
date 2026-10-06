package ru.mail.data.cmd.server.ad;

import android.content.Context;
import android.text.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.ad.AdParamsKeys;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.requestbody.ParamsRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class GetAdParamsCommand extends PostServerRequest<Params, Result> {
    private static final Log LOG = Log.getLog("GetAdParamsCommand");

    /* JADX INFO: compiled from: ProGuard */
    private class LiberoResponseProcessor extends ResponseProcessor {
        private final NetworkCommand.Response mResp;

        public LiberoResponseProcessor(NetworkCommand.Response response, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            super(response, networkCommandBaseDelegate);
            this.mResp = response;
        }

        @Override // ru.mail.network.ResponseProcessor
        public CommandStatus<?> process() {
            try {
                return this.mResp.getStatusCode() == 200 ? new CommandStatus.OK(GetAdParamsCommand.this.onPostExecuteRequest(this.mResp)) : new CommandStatus.ERROR(this.mResp.getRespString());
            } catch (NetworkCommand.PostExecuteException e10) {
                return new CommandStatus.ERROR(e10);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.POST, name = "u")
        private final String mAccount;

        @Param(method = HttpMethod.POST, name = "c")
        private final String mPassword;

        public Params(@NotNull MailboxContext mailboxContext) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
            if (TextUtils.isEmpty(mailboxContext.getProfile().getPassword())) {
                throw new IllegalArgumentException("Password is empty!");
            }
            this.mAccount = mailboxContext.getProfile().getLogin();
            this.mPassword = mailboxContext.getProfile().getPassword();
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
            String str = this.mAccount;
            if (str == null ? params.mAccount != null : !str.equals(params.mAccount)) {
                return false;
            }
            String str2 = this.mPassword;
            String str3 = params.mPassword;
            if (str2 != null) {
                return str2.equals(str3);
            }
            return str3 == null;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mAccount;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mPassword;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mAge;
        private final String mGender;
        private final String mPremium;
        private final String mRegion;
        private final long mTtl;

        public Result(String str, String str2, String str3, String str4, long j10) {
            this.mAge = str;
            this.mGender = str2;
            this.mRegion = str3;
            this.mPremium = str4;
            this.mTtl = j10 * 1000;
        }

        public String getAge() {
            return this.mAge;
        }

        public String getGender() {
            return this.mGender;
        }

        public String getPremium() {
            return this.mPremium;
        }

        public String getRegion() {
            return this.mRegion;
        }

        public long getTtl() {
            return this.mTtl;
        }
    }

    public GetAdParamsCommand(Context context, Params params) {
        super(context, params);
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new LiberoResponseProcessor(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected RequestBody onPrepareRequestBody() {
        return new ParamsRequestBody(getPostParams(), "UTF-8");
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            String strOptString = jSONObject.optString(AdParamsKeys.AGE.targeting(), null);
            String strOptString2 = jSONObject.optString(AdParamsKeys.GENDER.targeting(), null);
            String strOptString3 = jSONObject.optString(AdParamsKeys.LOCATION.targeting(), null);
            String strOptString4 = jSONObject.optString(AdParamsKeys.PREMIUM.targeting(), null);
            long jOptLong = jSONObject.optLong(AdParamsKeys.TTL.targeting());
            LOG.d("result = " + jSONObject);
            return new Result(strOptString, strOptString2, strOptString3, strOptString4, jOptLong);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
