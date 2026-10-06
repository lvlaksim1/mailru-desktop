package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.roomorama.caldroid.CaldroidFragment;
import com.vk.superapp.api.dto.story.actions.WebActionSituationalTemplate;
import java.util.Calendar;
import java.util.TimeZone;
import kotlin.Deprecated;
import org.apache.commons.lang3.time.TimeZones;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.child.DisablingParentalControlMapper;
import ru.mail.logic.child.ParentalModeStorageMapper;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HostProvider;
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
@Deprecated(message = "Should be replaced with GolangGetUserDataCommand in future.")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user"})
public class GetUserDataCommand extends ServerCommandBase<Params, GetUserDataResult> {
    private static final Log LOG = Log.getLog("GetUserDataCommand");

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;
    private final AccountManagerWrapper mAccountManager;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        private final MailboxContext mMailboxContext;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mMailboxContext = mailboxContext;
        }

        public MailboxContext getMailboxContext() {
            return this.mMailboxContext;
        }
    }

    public GetUserDataCommand(Context context, Params params) {
        this(context, params, MigrateToPostUtils.is12150Enabled(context));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    private void parseAvatar(JSONObject jSONObject, GetUserDataResult getUserDataResult) throws JSONException {
        String string;
        if (jSONObject.has(WebActionSituationalTemplate.AVATARS)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(WebActionSituationalTemplate.AVATARS);
            if (jSONObject2.has("90x90")) {
                string = jSONObject2.getString("90x90");
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        getUserDataResult.withAvatar(string);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004d  */
    private void parseBirthday(JSONObject jSONObject, GetUserDataResult getUserDataResult) throws JSONException {
        long timeInMillis;
        if (jSONObject.has("birthday")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("birthday");
            if (jSONObject2.has("day") && jSONObject2.has(CaldroidFragment.MONTH) && jSONObject2.has(CaldroidFragment.YEAR)) {
                int i10 = jSONObject2.getInt("day");
                int i11 = jSONObject2.getInt(CaldroidFragment.MONTH);
                int i12 = jSONObject2.getInt(CaldroidFragment.YEAR);
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
        getUserDataResult.withBirthday(timeInMillis);
    }

    private void parseDisablingParentalMode(JSONObject jSONObject, GetUserDataResult getUserDataResult) {
        getUserDataResult.withDisablingParentalControl(DisablingParentalControlMapper.map(jSONObject.optString("parental_control_can_disable")));
    }

    private void parseParentalMode(JSONObject jSONObject, GetUserDataResult getUserDataResult) {
        getUserDataResult.withParentalMode(ParentalModeNetworkMapper.map(jSONObject.optString("parental_control_mode")));
    }

    private void parsePhoneState(JSONObject jSONObject, GetUserDataResult getUserDataResult) throws JSONException {
        boolean z10;
        String strOptString = jSONObject.optString("main_phone", "");
        String str = null;
        boolean z11 = false;
        if (jSONObject.has("phones")) {
            JSONArray jSONArray = jSONObject.getJSONArray("phones");
            z10 = jSONArray.length() > 0;
            boolean z12 = false;
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                if (jSONObject2.has("phone")) {
                    String string = jSONObject2.getString("phone");
                    boolean z13 = jSONObject2.has("status") && TextUtils.equals(jSONObject2.getString("status"), "ok");
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
        getUserDataResult.withPhone(str).withPhoneVerifiedEnabled(z11).withAnyPhoneAvailable(z10);
    }

    @Keep
    public String getAcceptEncoding() {
        return "gzip";
    }

    @Override // ru.mail.serverapi.ServerCommandBase
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
        Account account = new Account(((Params) getParams()).getLogin(), BuildConfigVariablesHolder.accountType);
        GetUserDataResult okData = getOkData();
        this.mAccountManager.setUserData(account, Authenticator.KEY_VKC_ID, okData.getVkcId());
        this.mAccountManager.setUserData(account, "account_key_first_name", okData.getFirstName());
        this.mAccountManager.setUserData(account, "account_key_last_name", okData.getLastName());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_REG_DATE, Long.toString(okData.getRegDate()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_HAS_ANY_PHONE, Boolean.toString(okData.hasAnyPhone()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_BIRTHDATE, Long.toString(okData.getBirthday()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_BILLING_BITMASK, Long.toString(okData.getBillingBitmask()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_B2B_FLAGS, okData.getB2BFlags());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_CITY_ID, okData.getCityId());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_GENDER, okData.getGender());
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENTAL_MODE, ParentalModeStorageMapper.toString(okData.getParentalMode()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_HAS_CHILDREN, String.valueOf(okData.getParentalModeHasChildren()));
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_CAN_DISABLE, DisablingParentalControlMapper.toString(okData.getDisablingParentalMode()));
    }

    public GetUserDataCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public GetUserDataResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            LOG.d("getUserData result: " + response.getRespString());
            JSONObject jSONObject = new JSONObject(response.getRespString());
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("name");
            JSONObject jSONObject4 = jSONObject2.getJSONObject("common_purpose_flags");
            String string = jSONObject.getString("email");
            String strReplaceSpecialCodes = StringEscapeUtils.replaceSpecialCodes(jSONObject3.getString(PreferenceHostProvider.URL_PARAM_FIRST));
            GetUserDataResult getUserDataResultWithCityId = new GetUserDataResult(string).withVkcId(jSONObject2.has(Authenticator.KEY_VKC_ID) ? jSONObject2.getString(Authenticator.KEY_VKC_ID) : null).withFirstName(strReplaceSpecialCodes).withLastName(StringEscapeUtils.replaceSpecialCodes(jSONObject3.getString(MailThreadRepresentation.COL_NAME_LAST))).withGender(jSONObject2.optString("sex", "unknown")).withEnteredBetaOnWeb(jSONObject2.optBoolean("beta_user", false)).withCityId(jSONObject2.optString("city"));
            parseBirthday(jSONObject2, getUserDataResultWithCityId);
            parsePhoneState(jSONObject2, getUserDataResultWithCityId);
            parseAvatar(jSONObject2, getUserDataResultWithCityId);
            parseParentalMode(jSONObject2, getUserDataResultWithCityId);
            parseDisablingParentalMode(jSONObject2, getUserDataResultWithCityId);
            return getUserDataResultWithCityId.withMetaThreadsEnabled(jSONObject4.optBoolean("metathreads_on", false)).withTheme(jSONObject2.getString("theme")).withParentalModeHasChildren(jSONObject2.optBoolean("parental_control_has_children", false)).withBillingBitmask(jSONObject2.optLong(R7Analytics.BILLING_BITMASK, 0L)).withB2BFlags(jSONObject2.optString("b2b_flags")).withRegDate(jSONObject2.optLong("reg_date", 0L));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    GetUserDataCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        this.mAccountManager = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
    }
}
