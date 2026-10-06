package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.api.sdk.exceptions.VKApiCodes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.DeleteAccountConfirmCommand.Params;
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
import ru.mail.registration.ui.ConfirmationCodeFragment;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "mobile", "remove", VKApiCodes.EXTRA_CONFIRM})
public abstract class DeleteAccountConfirmCommand<T extends Params, P> extends ServerCommandBase<T, P> {
    static final String PARAM_TOKEN = "reg_token";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        private final String mCode;

        @Param(getterName = "getRegTokenStr", method = HttpMethod.GET, name = "reg_token", useGetter = true)
        private final String mId;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mId = str;
            this.mCode = str2;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            return this.mCode.equals(params.mCode) && this.mId.equals(params.mId);
        }

        public String getRegTokenStr() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.mId);
                jSONObject.put("value", this.mCode);
            } catch (JSONException e10) {
                e10.printStackTrace();
            }
            return jSONObject.toString();
        }

        public String getmCode() {
            return this.mCode;
        }

        public String getmId() {
            return this.mId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((super.hashCode() * 31) + this.mId.hashCode()) * 31) + this.mCode.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return true;
        }
    }

    public DeleteAccountConfirmCommand(Context context, T t10, boolean z10) {
        super(context, t10, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<T, P>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.DeleteAccountConfirmCommand.1
            private CommandStatus<?> processBadRequestResponse() {
                try {
                    if (new JSONObject(getResponse().getRespString()).getJSONObject("body").has(ConfirmationCodeFragment.ATTR_TOKEN_VALUE)) {
                        return processError("invalid_code", R.string.invalid_code);
                    }
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
                return processError("request_error", R.string.unable_to_complete_request);
            }

            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int i10) {
                return i10 == 400 ? processBadRequestResponse() : super.processResponse(i10);
            }
        };
    }

    DeleteAccountConfirmCommand(Context context, T t10, HostProvider hostProvider, boolean z10) {
        super(context, t10, hostProvider, z10);
    }
}
