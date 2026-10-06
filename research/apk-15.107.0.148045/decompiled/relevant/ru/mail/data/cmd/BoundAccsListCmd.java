package ru.mail.data.cmd;

import android.accounts.Account;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.credentialsexchanger.data.entity.SocialAccount;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.content.BoundAccsListResult;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000f\u0010B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/BoundAccsListCmd;", "Lru/mail/authorizesdk/data/request/common/SingleRequest;", "Lru/mail/data/cmd/BoundAccsListCmd$Params;", "Lru/mail/logic/content/BoundAccsListResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/BoundAccsListCmd$Params;)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "onDone", "", "getResultFromResponse", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/mail_api_default_host", defSchemeStrRes = "string/mail_api_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = MailOAuthRequest.BODY_KEY)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "social", "bind", "list"})
public final class BoundAccsListCmd extends SingleRequest<Params, BoundAccsListResult> {

    @NotNull
    private static final String FIRST_NAME_IS_NULL_STRING = "First name of social account is null";

    @NotNull
    private static final String LAST_NAME_IS_NULL_STRING = "Last name of social account is null";

    @NotNull
    private static final String LOGIN_IS_NULL_STRING = "Login of social account is null";

    @NotNull
    private final Context context;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("BoundAccsListCmd");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0014J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/BoundAccsListCmd$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", CommonConstant.KEY_ACCESS_TOKEN, "", "emailAddress", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getEmailAddress", "needAppendLocale", "", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @Keep
        @Param(method = HttpMethod.GET, name = "access_token")
        @NotNull
        private final String accessToken;

        @Keep
        @Param(method = HttpMethod.GET, name = "email")
        @NotNull
        private final String emailAddress;

        public Params(@NotNull String accessToken, @NotNull String emailAddress) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(emailAddress, "emailAddress");
            this.accessToken = accessToken;
            this.emailAddress = emailAddress;
        }

        public static /* synthetic */ Params copy$default(Params params, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = params.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = params.emailAddress;
            }
            return params.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEmailAddress() {
            return this.emailAddress;
        }

        @NotNull
        public final Params copy(@NotNull String accessToken, @NotNull String emailAddress) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(emailAddress, "emailAddress");
            return new Params(accessToken, emailAddress);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.accessToken, params.accessToken) && Intrinsics.areEqual(this.emailAddress, params.emailAddress);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getEmailAddress() {
            return this.emailAddress;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (this.accessToken.hashCode() * 31) + this.emailAddress.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(accessToken=" + this.accessToken + ", emailAddress=" + this.emailAddress + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BoundAccsListCmd(@NotNull Context context, @NotNull Params params) {
        super(context, params, MigrateToPostUtils.is12130Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.context = context;
    }

    private final BoundAccsListResult getResultFromResponse(NetworkCommand.Response resp) throws JSONException {
        JSONArray jSONArray = new JSONObject(resp.getRespString()).getJSONObject("body").getJSONArray("accounts");
        Intrinsics.checkNotNullExpressionValue(jSONArray, "getJSONArray(...)");
        MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
        if (jSONArray.length() > 0) {
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                String strOptString = jSONObject.optString("login", LOGIN_IS_NULL_STRING);
                String strOptString2 = jSONObject.optString("first_name", FIRST_NAME_IS_NULL_STRING);
                String strOptString3 = jSONObject.optString("last_name", LAST_NAME_IS_NULL_STRING);
                Intrinsics.checkNotNull(strOptString);
                Intrinsics.checkNotNull(strOptString2);
                Intrinsics.checkNotNull(strOptString3);
                mutableObjectList.add(new SocialAccount(strOptString, strOptString2, strOptString3));
            }
        }
        return new BoundAccsListResult(mutableObjectList.asMutableList());
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        String login;
        super.onDone();
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(this.context);
        Account account = new Account(getParams().getEmailAddress(), BuildConfigVariablesHolder.accountType);
        if (!statusOK() || isCancelled()) {
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BOUND_TO_VK, "undefined");
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BOUND_TO_ESIA, "undefined");
            return;
        }
        accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BOUND_TO_ESIA, String.valueOf(getOkData().haveEsiaBind()));
        accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BOUND_TO_VK, String.valueOf(getOkData().haveVkBind()));
        SocialAccount socialAccount = (SocialAccount) CollectionsKt.firstOrNull((List) getOkData().boundVkAccounts());
        if (socialAccount == null || (login = socialAccount.getLogin()) == null) {
            login = "";
        }
        accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BOUND_TO_VK_EMAIL, login);
        accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BIND_INFO_LAST_UPDATE_TIME, String.valueOf(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public BoundAccsListResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            return getResultFromResponse(resp);
        } catch (JSONException e10) {
            LOG.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
