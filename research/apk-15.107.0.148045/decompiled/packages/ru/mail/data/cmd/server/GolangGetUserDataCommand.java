package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.compose.runtime.internal.StabilityInferred;
import com.roomorama.caldroid.CaldroidFragment;
import com.vk.superapp.api.dto.story.actions.WebActionSituationalTemplate;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.time.TimeZones;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.cmd.server.parser.MetaThreadsSettingsParser;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.child.DisablingParentalControlMapper;
import ru.mail.logic.child.DisablingParentalMode;
import ru.mail.logic.child.ParentalMode;
import ru.mail.logic.child.ParentalModeStorageMapper;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.mail.utils.StringEscapeUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \"2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002!\"B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0010\u001a\u00020\rH\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J\b\u0010 \u001a\u00020\u0017H\u0014R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u000f¨\u0006#"}, d2 = {"Lru/mail/data/cmd/server/GolangGetUserDataCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/GolangGetUserDataCommand$Params;", "Lru/mail/data/cmd/server/GetUserDataResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/GolangGetUserDataCommand$Params;)V", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "kotlin.jvm.PlatformType", "acceptEncoding", "", "getAcceptEncoding$annotations", "()V", "getAcceptEncoding", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "parseBirthday", "", "bodyObj", "Lorg/json/JSONObject;", "result", "parsePhoneState", "parseAvatar", "parseMetaThreads", "parseParentalMode", "parseDisablingParentalControlMode", "onDone", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "user"})
public final class GolangGetUserDataCommand extends ServerCommandBase<Params, GetUserDataResult> {

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    @Nullable
    private final String acceptEncoding;
    private final AccountManagerWrapper accountManager;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GolangGetUserDataCommand");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/GolangGetUserDataCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "<init>", "(Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;)V", "getMailboxContext", "()Lru/mail/logic/content/MailboxContext;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @NotNull
        private final MailboxContext mailboxContext;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            this.mailboxContext = mailboxContext;
        }

        @NotNull
        public final MailboxContext getMailboxContext() {
            return this.mailboxContext;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GolangGetUserDataCommand(@NotNull Context context, @NotNull Params params) {
        super(context, params, MigrateToPostUtils.is12150Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.accountManager = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    private final void parseAvatar(JSONObject bodyObj, GetUserDataResult result) throws JSONException {
        String string;
        if (bodyObj.has(WebActionSituationalTemplate.AVATARS)) {
            JSONObject jSONObject = bodyObj.getJSONObject(WebActionSituationalTemplate.AVATARS);
            if (jSONObject.has("90x90")) {
                string = jSONObject.getString("90x90");
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        result.withAvatar(string);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004d  */
    private final void parseBirthday(JSONObject bodyObj, GetUserDataResult result) throws JSONException {
        long timeInMillis;
        if (bodyObj.has("birthday")) {
            JSONObject jSONObject = bodyObj.getJSONObject("birthday");
            if (jSONObject.has("day") && jSONObject.has(CaldroidFragment.MONTH) && jSONObject.has(CaldroidFragment.YEAR)) {
                int i10 = jSONObject.getInt("day");
                int i11 = jSONObject.getInt(CaldroidFragment.MONTH);
                int i12 = jSONObject.getInt(CaldroidFragment.YEAR);
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(TimeZones.GMT_ID));
                calendar.set(i12, i11 - 1, i10, 0, 0, 0);
                calendar.set(14, 0);
                timeInMillis = calendar.getTimeInMillis();
            } else {
                timeInMillis = Long.MIN_VALUE;
            }
        } else {
            timeInMillis = Long.MIN_VALUE;
        }
        result.withBirthday(timeInMillis);
    }

    private final void parseDisablingParentalControlMode(JSONObject bodyObj, GetUserDataResult result) {
        String strOptString = bodyObj.optString("parental_control_can_disable");
        Intrinsics.checkNotNull(strOptString);
        result.withDisablingParentalControl(DisablingParentalControlMapper.map(strOptString));
    }

    private final void parseMetaThreads(JSONObject bodyObj, GetUserDataResult result) throws JSONException {
        JSONArray jSONArrayOptJSONArray = bodyObj.optJSONArray("metathreads_visible");
        Log log = LOG;
        log.i("Parsing metathreads array: " + jSONArrayOptJSONArray);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            log.w("MetaThreads json array is null or empty!");
        } else {
            result.withMetaThreadStates(new MetaThreadsSettingsParser().parse(jSONArrayOptJSONArray));
        }
    }

    private final void parseParentalMode(JSONObject bodyObj, GetUserDataResult result) {
        result.withParentalMode(ParentalModeNetworkMapper.map(bodyObj.optString("parental_control_mode")));
    }

    private final void parsePhoneState(JSONObject bodyObj, GetUserDataResult result) throws JSONException {
        boolean z10;
        String strOptString = bodyObj.optString("main_phone", "");
        String str = null;
        boolean z11 = false;
        if (bodyObj.has("phones")) {
            JSONArray jSONArray = bodyObj.getJSONArray("phones");
            z10 = jSONArray.length() > 0;
            int length = jSONArray.length();
            boolean z12 = false;
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                if (jSONObject.has("phone")) {
                    String string = jSONObject.getString("phone");
                    boolean z13 = jSONObject.has("status") && TextUtils.equals(jSONObject.getString("status"), "ok");
                    if (TextUtils.equals(strOptString, string)) {
                        z11 = z13;
                        str = string;
                    } else if (i10 == 0 || (!z12 && z13)) {
                        z12 = z13;
                        str = string;
                    }
                }
            }
            z11 = z12;
        } else {
            z10 = false;
        }
        result.withPhone(str).withPhoneVerifiedEnabled(z11).withAnyPhoneAvailable(z10);
    }

    @Keep
    @NotNull
    public final String getAcceptEncoding() {
        return "gzip";
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (!statusOK() || isCancelled()) {
            return;
        }
        String login = ((Params) getParams()).getLogin();
        Intrinsics.checkNotNull(login);
        Account account = new Account(login, BuildConfigVariablesHolder.accountType);
        GetUserDataResult okData = getOkData();
        this.accountManager.setUserData(account, Authenticator.KEY_VKC_ID, okData.getVkcId());
        this.accountManager.setUserData(account, "account_key_first_name", okData.getFirstName());
        this.accountManager.setUserData(account, "account_key_last_name", okData.getLastName());
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_REG_DATE, String.valueOf(okData.getRegDate()));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_HAS_ANY_PHONE, String.valueOf(okData.hasAnyPhone()));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_BIRTHDATE, String.valueOf(okData.getBirthday()));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_CITY_ID, okData.getCityId());
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_GENDER, okData.getGender());
        ParentalMode parentalMode = okData.getParentalMode();
        Intrinsics.checkNotNullExpressionValue(parentalMode, "getParentalMode(...)");
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENTAL_MODE, ParentalModeStorageMapper.toString(parentalMode));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_HAS_CHILDREN, String.valueOf(okData.getParentalModeHasChildren()));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_BILLING_BITMASK, String.valueOf(okData.getBillingBitmask()));
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_B2B_FLAGS, okData.getB2BFlags());
        DisablingParentalMode disablingParentalMode = okData.getDisablingParentalMode();
        Intrinsics.checkNotNullExpressionValue(disablingParentalMode, "getDisablingParentalMode(...)");
        this.accountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_CAN_DISABLE, DisablingParentalControlMapper.toString(disablingParentalMode));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public GetUserDataResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            LOG.d("getUserData result: " + resp.getRespString());
            JSONObject jSONObject = new JSONObject(resp.getRespString());
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("name");
            JSONObject jSONObject4 = jSONObject2.getJSONObject("common_purpose_flags");
            String string = jSONObject.getString("email");
            String strReplaceSpecialCodes = StringEscapeUtils.replaceSpecialCodes(jSONObject3.getString(PreferenceHostProvider.URL_PARAM_FIRST));
            String strReplaceSpecialCodes2 = StringEscapeUtils.replaceSpecialCodes(jSONObject3.getString(MailThreadRepresentation.COL_NAME_LAST));
            String strOptString = jSONObject2.optString("sex", "unknown");
            String strOptString2 = jSONObject2.optString("city");
            boolean zOptBoolean = jSONObject2.optBoolean("beta_user", false);
            GetUserDataResult getUserDataResultWithCityId = new GetUserDataResult(string).withVkcId(jSONObject2.has(Authenticator.KEY_VKC_ID) ? jSONObject2.getString(Authenticator.KEY_VKC_ID) : null).withFirstName(strReplaceSpecialCodes).withLastName(strReplaceSpecialCodes2).withGender(strOptString).withCityId(strOptString2);
            Intrinsics.checkNotNull(jSONObject2);
            Intrinsics.checkNotNull(getUserDataResultWithCityId);
            parseBirthday(jSONObject2, getUserDataResultWithCityId);
            parsePhoneState(jSONObject2, getUserDataResultWithCityId);
            parseAvatar(jSONObject2, getUserDataResultWithCityId);
            parseMetaThreads(jSONObject2, getUserDataResultWithCityId);
            parseParentalMode(jSONObject2, getUserDataResultWithCityId);
            parseDisablingParentalControlMode(jSONObject2, getUserDataResultWithCityId);
            GetUserDataResult getUserDataResultWithB2BFlags = getUserDataResultWithCityId.withMetaThreadsEnabled(jSONObject4.optBoolean("metathreads_on", false)).withEnteredBetaOnWeb(zOptBoolean).withTheme(jSONObject2.getString("theme")).withParentalModeHasChildren(jSONObject2.optBoolean("parental_control_has_children", false)).withRegDate(jSONObject2.optLong("reg_date", 0L)).withBillingBitmask(jSONObject2.optLong(R7Analytics.BILLING_BITMASK, 0L)).withB2BFlags(jSONObject2.optString("b2b_flags"));
            Intrinsics.checkNotNull(getUserDataResultWithB2BFlags);
            return getUserDataResultWithB2BFlags;
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    private static /* synthetic */ void getAcceptEncoding$annotations() {
    }
}
