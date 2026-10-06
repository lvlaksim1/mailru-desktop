package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.superapp.api.dto.app.WebOrder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThread;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mails.R;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.fragments.settings.ConfirmPhoneFragment;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "mobile", "phone", "change"})
public class ChangePhoneCommand extends ServerCommandBase<Params, TokenResult> {
    private static final Log LOG = Log.getLog("ChangePhoneCommand");
    static final String PARAM_LANG = "lang";
    static final String PARAM_PHONE = "phone";
    static final String PARAM_TOKEN = "reg_token_check";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.GET, name = "phone")
        private final String mNewPhone;

        @Param(method = HttpMethod.GET, name = "reg_token_check")
        private final String mRegTokenCheck;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mRegTokenCheck = str;
            this.mNewPhone = str2;
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
            String str = this.mNewPhone;
            if (str == null ? params.mNewPhone != null : !str.equals(params.mNewPhone)) {
                return false;
            }
            String str2 = this.mRegTokenCheck;
            String str3 = params.mRegTokenCheck;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mRegTokenCheck;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mNewPhone;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return true;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final int mCodeLength;
        private final int mCodeWait;
        private final String mRegTokenId;

        public Result(String str, int i10, int i11) {
            this.mRegTokenId = str;
            this.mCodeLength = i10;
            this.mCodeWait = i11;
        }

        public int getCodeLength() {
            return this.mCodeLength;
        }

        public int getCodeWait() {
            return this.mCodeWait;
        }

        public String getRegTokenId() {
            return this.mRegTokenId;
        }
    }

    public ChangePhoneCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, TokenResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.ChangePhoneCommand.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int i10) {
                try {
                    JSONObject jSONObject = new JSONObject(getResponse().getRespString()).getJSONObject("body");
                    if (jSONObject.has("phone")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("phone");
                        if (jSONObject2.has("error")) {
                            if (jSONObject2.getString("error").equals("invalid")) {
                                return processError("invalid phone", R.string.change_phone_phone_invalid);
                            }
                            if (jSONObject2.getString("error").equals("reached_accounts")) {
                                return processError("to many account for one phone", R.string.change_phone_phone_reached);
                            }
                            if (jSONObject2.getString("error").equals("same_phone")) {
                                return processError("same phone", R.string.change_phone_phone_same);
                            }
                        }
                    }
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
                return super.processResponse(i10);
            }
        };
    }

    ChangePhoneCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public TokenResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body").getJSONObject(ConfirmPhoneFragment.EXT_REG_TOKEN);
            return new TokenResult(jSONObject.getString("id"), jSONObject.getInt(MailThread.COL_NAME_LENGTH), jSONObject.getInt(WebOrder.STATUS_WAIT));
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
