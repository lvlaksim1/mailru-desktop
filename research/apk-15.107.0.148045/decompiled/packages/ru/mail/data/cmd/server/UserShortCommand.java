package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.data.cmd.server.parser.BoxQuotasParser;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.dependencies.NetworkEntryPoint;
import ru.mail.logic.child.DisablingParentalControlMapper;
import ru.mail.logic.child.DisablingParentalMode;
import ru.mail.logic.child.ParentalMode;
import ru.mail.logic.child.ParentalModeStorageMapper;
import ru.mail.logic.mailboxquotas.BoxQuotas;
import ru.mail.logic.mailboxquotas.SharedPrefMailQuotasStorage;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.config.MigrateToPostUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "short"})
public class UserShortCommand extends ServerCommandBase<ServerCommandEmailParams, UserData> {

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;
    private final RequestListenerManager mRequestListenerManager;

    /* JADX INFO: compiled from: ProGuard */
    public static class UserData {
        private String mAccountType;
        private boolean mAdsEnabled;
        private String mB2BFlags;
        private long mBillingBitmask;
        private boolean mIsMailboxCheckDisabled;
        private boolean mIsMetaThreadsEnabled;
        private String mMainPhone;
        private boolean mParentalControlHasChildren;
        private int mRegTimestamp;
        private boolean mSoftVkidBind;
        private String mTheme;
        private boolean mTwoFactor;
        private String mVkcId;

        @NonNull
        private ParentalMode mParentalMode = ParentalMode.OFF;
        private DisablingParentalMode mDisablingParentalMode = DisablingParentalMode.NULL;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                UserData userData = (UserData) obj;
                if (this.mIsMailboxCheckDisabled == userData.mIsMailboxCheckDisabled && this.mBillingBitmask == userData.mBillingBitmask && Objects.equals(this.mB2BFlags, userData.mB2BFlags) && this.mRegTimestamp == userData.mRegTimestamp && this.mSoftVkidBind == userData.mSoftVkidBind && this.mMainPhone.equals(userData.mMainPhone) && this.mIsMetaThreadsEnabled == userData.mIsMetaThreadsEnabled) {
                    return true;
                }
            }
            return false;
        }

        public String getAccountType() {
            return this.mAccountType;
        }

        @Nullable
        public String getB2BFlags() {
            return this.mB2BFlags;
        }

        public long getBillingBitmask() {
            return this.mBillingBitmask;
        }

        public DisablingParentalMode getDisableParentalMode() {
            return this.mDisablingParentalMode;
        }

        public boolean getParentalControlHasChildren() {
            return this.mParentalControlHasChildren;
        }

        @NonNull
        public ParentalMode getParentalMode() {
            return this.mParentalMode;
        }

        public int getRegTimestamp() {
            return this.mRegTimestamp;
        }

        public String getTheme() {
            return this.mTheme;
        }

        public String getVkcId() {
            return this.mVkcId;
        }

        public int hashCode() {
            int iHashCode = (((((((this.mIsMailboxCheckDisabled ? 1 : 0) * 31) + (this.mIsMetaThreadsEnabled ? 1 : 0)) * 31) + this.mRegTimestamp) * 31) + Long.hashCode(this.mBillingBitmask)) * 31;
            String str = this.mB2BFlags;
            return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.mSoftVkidBind)) * 31) + this.mMainPhone.hashCode();
        }

        public boolean isAdsEnabled() {
            return this.mAdsEnabled;
        }

        public boolean isMailboxCheckDisabled() {
            return this.mIsMailboxCheckDisabled;
        }

        public boolean isMetaThreadsEnabled() {
            return this.mIsMetaThreadsEnabled;
        }

        public boolean isTwoFactorEnabled() {
            return this.mTwoFactor;
        }

        public UserData with2FactorEnabled(boolean z10) {
            this.mTwoFactor = z10;
            return this;
        }

        UserData withAccountType(String str) {
            this.mAccountType = str;
            return this;
        }

        public UserData withAdsEnabled(boolean z10) {
            this.mAdsEnabled = z10;
            return this;
        }

        UserData withB2BFlags(String str) {
            this.mB2BFlags = str;
            return this;
        }

        UserData withBillingBitmask(long j10) {
            this.mBillingBitmask = j10;
            return this;
        }

        UserData withDisableParentalMode(DisablingParentalMode disablingParentalMode) {
            this.mDisablingParentalMode = disablingParentalMode;
            return this;
        }

        public UserData withMailboxCheckDisabled(boolean z10) {
            this.mIsMailboxCheckDisabled = z10;
            return this;
        }

        UserData withMainPhone(String str) {
            this.mMainPhone = str;
            return this;
        }

        public UserData withMetaThreadsEnabled(boolean z10) {
            this.mIsMetaThreadsEnabled = z10;
            return this;
        }

        UserData withParentalControlHasChildren(boolean z10) {
            this.mParentalControlHasChildren = z10;
            return this;
        }

        UserData withParentalMode(@NonNull ParentalMode parentalMode) {
            this.mParentalMode = parentalMode;
            return this;
        }

        UserData withRegDate(int i10) {
            this.mRegTimestamp = i10;
            return this;
        }

        UserData withSoftVkidBind(boolean z10) {
            this.mSoftVkidBind = z10;
            return this;
        }

        UserData withTheme(String str) {
            this.mTheme = str;
            return this;
        }

        UserData withVkcId(String str) {
            this.mVkcId = str;
            return this;
        }
    }

    public UserShortCommand(Context context, ServerCommandEmailParams serverCommandEmailParams) {
        super(context, serverCommandEmailParams, MigrateToPostUtils.is12150Enabled(context));
        this.mRequestListenerManager = NetworkEntryPoint.requestListenerManager(getContext());
    }

    private void parseDisableParentalMode(JSONObject jSONObject, UserData userData) {
        userData.withDisableParentalMode(DisablingParentalControlMapper.map(jSONObject.optString("parental_control_can_disable")));
    }

    private void parseParentalMode(JSONObject jSONObject, UserData userData) {
        userData.withParentalMode(ParentalModeNetworkMapper.map(jSONObject.optString("parental_control_mode")));
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
        if (statusOK() && !isCancelled()) {
            UserData okData = getOkData();
            AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getContext());
            Account account = new Account(((ServerCommandEmailParams) getParams()).getLogin(), BuildConfigVariablesHolder.accountType);
            accountManagerWrapper.setUserData(account, Authenticator.KEY_VKC_ID, okData.getVkcId());
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_USER_TYPE, okData.getAccountType());
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_BILLING_BITMASK, Long.toString(okData.getBillingBitmask()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_B2B_FLAGS, okData.getB2BFlags());
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_SHOW_ME_ADS_DISABLED, String.valueOf(!okData.isAdsEnabled()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_TWO_FACTOR_ENABLED, String.valueOf(okData.isTwoFactorEnabled()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_REG_DATE, String.valueOf(okData.getRegTimestamp()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_SOFT_VKID_BIND, String.valueOf(okData.mSoftVkidBind));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENTAL_MODE, ParentalModeStorageMapper.toString(okData.getParentalMode()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_HAS_CHILDREN, String.valueOf(okData.getParentalControlHasChildren()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_CAN_DISABLE, DisablingParentalControlMapper.toString(okData.getDisableParentalMode()));
            accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_MAIN_PHONE, okData.mMainPhone);
        }
        this.mRequestListenerManager.pushResponse(this, getResult(), ((ServerCommandEmailParams) getParams()).getLogin());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public UserData onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            JSONObject jSONObject2 = jSONObject.getJSONObject("common_purpose_flags");
            BoxQuotas boxLimits = new BoxQuotasParser().parseBoxLimits(jSONObject);
            SharedPrefMailQuotasStorage sharedPrefMailQuotasStorage = new SharedPrefMailQuotasStorage(getContext(), getLogin());
            if (boxLimits != null) {
                sharedPrefMailQuotasStorage.addBoxLimits(boxLimits);
            } else {
                sharedPrefMailQuotasStorage.clearData();
            }
            UserData userData = new UserData();
            parseParentalMode(jSONObject, userData);
            parseDisableParentalMode(jSONObject, userData);
            return userData.withAccountType(jSONObject.getString("account_type")).withVkcId(jSONObject.has(Authenticator.KEY_VKC_ID) ? jSONObject.getString(Authenticator.KEY_VKC_ID) : null).withMailboxCheckDisabled(jSONObject2.optBoolean(MailboxProfile.COL_NAME_DENY_PERSONAL_DATA_PROCESSING, false)).withTheme(jSONObject.getString("theme")).withMetaThreadsEnabled(jSONObject2.optBoolean("metathreads_on", false)).with2FactorEnabled(jSONObject.getJSONObject(BaseSettingsActivity.KEY_PREF_SECURITY).optBoolean("2_step_auth", false)).withParentalControlHasChildren(jSONObject.optBoolean("parental_control_has_children", false)).withAdsEnabled(jSONObject.optBoolean("show_me_ads", true)).withBillingBitmask(jSONObject.optLong(R7Analytics.BILLING_BITMASK, 0L)).withB2BFlags(jSONObject.optString("b2b_flags")).withRegDate(jSONObject.optInt("reg_date", 0)).withSoftVkidBind(jSONObject2.optBoolean("soft_vkid_bind")).withMainPhone(jSONObject.optString("phone_main"));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
