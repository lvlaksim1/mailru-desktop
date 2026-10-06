package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import com.vk.lists.PaginationHelper;
import com.vk.superapp.api.dto.app.WebOrder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.ads.kotlett.simple.domain.KotlettAdFeatureParamsMapper;
import ru.mail.data.entities.MailThread;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.fragments.settings.ConfirmPhoneFragment;
import ru.mail.util.SubjectBuilder;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "tokens", "send"})
public class TokensSendCommand extends ServerCommandBase<Params, TokenResult> {
    private static final Log LOG = Log.getLog("TokensSendCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.GET, name = "ivr")
        private static final String IVR = String.valueOf(true);
        private final String mPhoneNumber;

        @Param(getterName = "getRegTokenStr", method = HttpMethod.GET, name = ConfirmPhoneFragment.EXT_REG_TOKEN, useGetter = true)
        private final String mRegTokenId;
        private final Target mTarget;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String str, @NotNull Target target, @NotNull String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mRegTokenId = str;
            this.mTarget = target;
            this.mPhoneNumber = str2.replaceAll(SubjectBuilder.NOT_DIGIT_REGEX, "");
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
            String str = this.mPhoneNumber;
            if (str == null ? params.mPhoneNumber != null : !str.equals(params.mPhoneNumber)) {
                return false;
            }
            String str2 = this.mRegTokenId;
            if (str2 == null ? params.mRegTokenId == null : str2.equals(params.mRegTokenId)) {
                return this.mTarget == params.mTarget;
            }
            return false;
        }

        public String getRegTokenStr() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.mRegTokenId);
                jSONObject.put(KotlettAdFeatureParamsMapper.KEY_FORMAT, "default");
                jSONObject.put("transport", "phone");
                jSONObject.put(ToastDialogDto.KEY_TARGET, this.mTarget.mValue);
                if (TextUtils.isEmpty(this.mPhoneNumber)) {
                    jSONObject.put("index", PaginationHelper.DEFAULT_NEXT_FROM);
                } else {
                    jSONObject.put("address", this.mPhoneNumber);
                }
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
            return jSONObject.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mRegTokenId;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            Target target = this.mTarget;
            int iHashCode3 = (iHashCode2 + (target != null ? target.hashCode() : 0)) * 31;
            String str2 = this.mPhoneNumber;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
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

    /* JADX INFO: compiled from: ProGuard */
    public enum Target {
        CHECK_PHONE("user/mobile/phone/change/confirm-current"),
        DELETE_ACCOUNT("user/mobile/remove"),
        CHANGE_PHONE("user/mobile/phone/change");

        private final String mValue;

        Target(String str) {
            this.mValue = str;
        }
    }

    public TokensSendCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, TokenResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    TokensSendCommand(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    public TokenResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new TokenResult(((Params) getParams()).mRegTokenId, jSONObject.getInt(MailThread.COL_NAME_LENGTH), jSONObject.getInt(WebOrder.STATUS_WAIT));
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
