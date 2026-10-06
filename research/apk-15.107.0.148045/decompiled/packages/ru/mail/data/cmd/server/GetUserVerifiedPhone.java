package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "payment", "phone", XmailMigrationPromoSheet.BUTTON_INFO})
public class GetUserVerifiedPhone extends ServerCommandBase<ServerCommandEmailParams, CommandStatus<Result>> {
    private final AccountManagerWrapper mAccountManager;

    /* JADX INFO: compiled from: ProGuard */
    static class Result {

        @Nullable
        private String mOperator;

        @Nullable
        private String mPhoneNumber;

        Result() {
        }

        @Nullable
        String getOperator() {
            return this.mOperator;
        }

        @Nullable
        String getPhoneNumber() {
            return this.mPhoneNumber;
        }

        Result withOperator(@Nullable String str) {
            this.mOperator = str;
            return this;
        }

        Result withPhoneNumber(@Nullable String str) {
            this.mPhoneNumber = str;
            return this;
        }
    }

    public GetUserVerifiedPhone(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10) {
        this(context, serverCommandEmailParams, null, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setAccountInfo(Result result) {
        Account account = new Account(((ServerCommandEmailParams) getParams()).getLogin(), BuildConfigVariablesHolder.accountType);
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_VERIFIED_PHONE_OPERATOR, result.getOperator());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_VERIFIED_PHONE_NUMBER, result.getPhoneNumber());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_HAS_VERIFIED_PHONE, Boolean.toString(!TextUtils.isEmpty(result.getPhoneNumber())));
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<ServerCommandEmailParams, CommandStatus<Result>>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.GetUserVerifiedPhone.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int i10) {
                return i10 == 417 ? new CommandStatus.OK() : super.processResponse(i10);
            }
        };
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (isCancelled() || !statusOK()) {
            return;
        }
        if (hasData()) {
            setAccountInfo(getOkData().getData());
        } else {
            setAccountInfo(new Result());
        }
    }

    public GetUserVerifiedPhone(Context context, ServerCommandEmailParams serverCommandEmailParams, HostProvider hostProvider, boolean z10) {
        super(context, serverCommandEmailParams, hostProvider, z10);
        this.mAccountManager = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public CommandStatus<Result> onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new CommandStatus.OK(new Result().withOperator(jSONObject.getString("operator")).withPhoneNumber(jSONObject.getString("phone")));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
