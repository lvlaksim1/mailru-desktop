package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.AccountType;
import ru.mail.auth.Authenticator;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.dependencies.OverQuotaModule;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.news_feed.util.analytics.NewsAnalyticsHandler;
import ru.mail.overquota.OverQuotaConfigDelegate;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.mail.utils.TimeProvider;
import ru.mail.utils.TimeUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "cloud", "status"})
public class GetCloudInfoCommand extends ServerCommandBase<Params, Result> {
    private static final String STARTED = "started";
    private final Account mAccount;
    private final AccountManagerWrapper mAccountManager;
    private final String mDefaultStatus;
    private final OverQuotaConfigDelegate mDelegate;
    private final boolean mIsCloudOverquotaStateInRequest;
    private final long mPreviousCloudOverquotaBlockTime;
    private final long mPreviousCloudOverquotaStartTime;
    private final String mPreviousStatus;
    private final TimeProvider timeProvider;
    private static final Log LOG = Log.getLog("GetCloudInfoCommand");
    private static final String BLOCKED = "blocked";
    private static final String FULL_BLOCKED = "full_blocked";
    private static final String UNBLOCKED = "unblocked";
    private static final Set<String> CLOUD_BLOCKED_STATES = i.a(new Object[]{BLOCKED, FULL_BLOCKED, UNBLOCKED});

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "cloud_overquota_state")
        private final Boolean mCloudOverquotaState;

        @Param(method = HttpMethod.POST, name = "remember_overquota")
        private final Boolean mRememberOverQuota;

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @Nullable Boolean bool, @Nullable Boolean bool2) {
            super(accountInfo, folderState);
            this.mRememberOverQuota = bool;
            this.mCloudOverquotaState = bool2;
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
            return Objects.equals(this.mRememberOverQuota, params.mRememberOverQuota) && Objects.equals(this.mCloudOverquotaState, params.mCloudOverquotaState);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            Boolean bool = this.mRememberOverQuota;
            int iHashCode2 = (iHashCode + (bool != null ? bool.hashCode() : 0)) * 31;
            Boolean bool2 = this.mCloudOverquotaState;
            return iHashCode2 + (bool2 != null ? bool2.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private AccountType mAccountType;

        @NonNull
        private String mCloudOverQuotaStatus = "";
        private boolean mIsBlocked;
        private boolean mIsExists;
        private boolean mIsFrozen;
        private boolean mIsOverQuota;
        private final String mLogin;
        private long mSharedOverQuotaStartBlockDate;
        private long mSharedOverQuotaStartWarningDate;
        private long mTotalBytes;
        private long mUsedBytes;
        private long mUsedMailBytes;

        public Result(String str) {
            this.mLogin = str;
        }

        public AccountType getAccountType() {
            return this.mAccountType;
        }

        @NonNull
        public String getCloudOverQuotaStatus() {
            return this.mCloudOverQuotaStatus;
        }

        public String getLogin() {
            return this.mLogin;
        }

        public long getSharedOverQuotaStartBlockDate() {
            return this.mSharedOverQuotaStartBlockDate;
        }

        public long getSharedOverQuotaStartWarningDate() {
            return this.mSharedOverQuotaStartWarningDate;
        }

        public long getTotalBytes() {
            return this.mTotalBytes;
        }

        public long getUsedBytes() {
            return this.mUsedBytes;
        }

        public long getUsedMailBytes() {
            return this.mUsedMailBytes;
        }

        public boolean isBlocked() {
            return this.mIsBlocked;
        }

        public boolean isExists() {
            return this.mIsExists;
        }

        public boolean isFrozen() {
            return this.mIsFrozen;
        }

        public boolean isOverQuota() {
            return this.mIsOverQuota;
        }

        Result withAccountType(AccountType accountType) {
            this.mAccountType = accountType;
            return this;
        }

        Result withBlocked(boolean z10) {
            this.mIsBlocked = z10;
            return this;
        }

        Result withCloudOverQuotaState(@NonNull String str) {
            this.mCloudOverQuotaStatus = str;
            return this;
        }

        Result withExists(boolean z10) {
            this.mIsExists = z10;
            return this;
        }

        Result withFrozen(boolean z10) {
            this.mIsFrozen = z10;
            return this;
        }

        Result withOverQuota(boolean z10) {
            this.mIsOverQuota = z10;
            return this;
        }

        Result withSharedOverQuotaStartBlockDate(long j10) {
            this.mSharedOverQuotaStartBlockDate = j10;
            return this;
        }

        Result withSharedOverQuotaStartWarningDate(long j10) {
            this.mSharedOverQuotaStartWarningDate = j10;
            return this;
        }

        Result withTotalBytes(long j10) {
            this.mTotalBytes = j10;
            return this;
        }

        Result withUsedBytes(long j10) {
            this.mUsedBytes = j10;
            return this;
        }

        Result withUsedMailBytes(long j10) {
            this.mUsedMailBytes = j10;
            return this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public GetCloudInfoCommand(Context context, Params params, String str, boolean z10) {
        super(context, params, z10);
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        this.mAccountManager = accountManagerWrapper;
        OverQuotaConfigDelegate overQuotaConfigDelegate = OverQuotaModule.getOverQuotaConfigDelegate(context);
        this.mDelegate = overQuotaConfigDelegate;
        boolean zIsCloudOverquotaStateInRequest = overQuotaConfigDelegate.isCloudOverquotaStateInRequest();
        this.mIsCloudOverquotaStateInRequest = zIsCloudOverquotaStateInRequest;
        LOG.d("Get cloud info cloud state request: " + zIsCloudOverquotaStateInRequest);
        this.timeProvider = TimeUtils.Time.from(getContext().getApplicationContext());
        this.mDefaultStatus = str;
        Account account = new Account(((Params) getParams()).getLogin(), BuildConfigVariablesHolder.accountType);
        this.mAccount = account;
        this.mPreviousStatus = accountManagerWrapper.getUserData(account, MailboxProfile.ACCOUNT_KEY_CLOUD_OVERQUOTA_STATUS);
        this.mPreviousCloudOverquotaStartTime = getPreviousCloudOverquotaValue(accountManagerWrapper.getUserData(account, MailboxProfile.ACCOUNT_KEY_SHARED_OVER_QUOTA_STARTED_WARNING_DATE));
        this.mPreviousCloudOverquotaBlockTime = getPreviousCloudOverquotaValue(accountManagerWrapper.getUserData(account, MailboxProfile.ACCOUNT_KEY_SHARED_OVER_QUOTA_BLOCK_DATE));
    }

    private boolean checkIsCloudOverQuotaStatus(boolean z10, String str) {
        if (!this.mDelegate.isSharedConfigEnabled()) {
            return false;
        }
        if (z10) {
            return CLOUD_BLOCKED_STATES.contains(str) || "started".equals(str);
        }
        return CLOUD_BLOCKED_STATES.contains(this.mPreviousStatus) || "started".equals(this.mPreviousStatus);
    }

    private static AccountType getAccountType(JSONObject jSONObject, AccountType accountType) throws JSONException {
        String string = jSONObject.getString("account_type");
        if (string.isEmpty()) {
            return accountType;
        }
        try {
            return AccountType.valueOf(string.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException unused) {
            LOG.d("Failed on parse account type field");
            return AccountType.UNKNOWN;
        }
    }

    private String getNextRequestWithParamTimestamp() {
        long currentTimeMillis = this.timeProvider.getCurrentTimeMillis() + TimeUnit.SECONDS.toMillis(this.mDelegate.getStateRequestTimeoutSeconds());
        LOG.d("Get cloud info next update: " + currentTimeMillis);
        return String.valueOf(currentTimeMillis);
    }

    private long getPreviousCloudOverquotaValue(String str) {
        Long longOrNull;
        if (str == null || (longOrNull = StringsKt.toLongOrNull(str)) == null) {
            return 0L;
        }
        return longOrNull.longValue();
    }

    private void updateOverQuotaData(Account account, Result result) {
        this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_IS_CLOUD_OVER_QUOTA, Boolean.toString(result.isOverQuota()));
        String userData = this.mAccountManager.getUserData(account, MailboxProfile.ACCOUNT_KEY_CLOUD_OVERQUOTA_STATUS);
        Log log = LOG;
        log.d("Get cloud info old status: " + userData);
        if (this.mIsCloudOverquotaStateInRequest) {
            this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_CLOUD_OVERQUOTA_STATUS_REQUEST_TS, getNextRequestWithParamTimestamp());
            String cloudOverQuotaStatus = result.getCloudOverQuotaStatus();
            log.d("Get cloud info new status: " + cloudOverQuotaStatus);
            if (!cloudOverQuotaStatus.equals(this.mPreviousStatus)) {
                this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_CLOUD_OVERQUOTA_STATUS, cloudOverQuotaStatus);
            }
            String string = Long.toString(result.getSharedOverQuotaStartWarningDate());
            String string2 = Long.toString(result.getSharedOverQuotaStartBlockDate());
            log.d("Get cloud info new shared warning date: " + string);
            this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_SHARED_OVER_QUOTA_STARTED_WARNING_DATE, string);
            log.d("Get cloud info new shared block date: " + string2);
            this.mAccountManager.setUserData(account, MailboxProfile.ACCOUNT_KEY_SHARED_OVER_QUOTA_BLOCK_DATE, string2);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (!statusOK() || isCancelled()) {
            LOG.d("Get cloud info has been cancelled or status is not OK for account : " + this.mAccount.name);
        } else {
            Result okData = getOkData();
            LOG.d("Get cloud info has been sent successfully for account : " + this.mAccount.name);
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_CLOUD_ACCOUNT_TYPE, okData.getAccountType().name());
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_IS_CLOUD_BLOCKED, Boolean.toString(okData.isBlocked()));
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_IS_CLOUD_EXISTS, Boolean.toString(okData.isExists()));
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_CLOUD_TOTAL_BYTES, Long.toString(okData.getTotalBytes()));
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_CLOUD_USED_BYTES, Long.toString(okData.getUsedBytes()));
            this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_CLOUD_USED_MAIL_BYTES, Long.toString(okData.getUsedMailBytes()));
            updateOverQuotaData(this.mAccount, okData);
        }
        this.mAccountManager.setUserData(this.mAccount, MailboxProfile.ACCOUNT_KEY_REQUEST_CLOUD_INFO_TIME_MS, Long.toString(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:26:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:28:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:29:0x00e3 A[Catch: JSONException -> 0x0074, TryCatch #0 {JSONException -> 0x0074, blocks: (B:3:0x0002, B:5:0x0071, B:8:0x0077, B:10:0x007d, B:12:0x0085, B:13:0x0087, B:18:0x0098, B:24:0x00ac, B:33:0x00ec, B:40:0x0118, B:42:0x0133, B:36:0x010b, B:38:0x010f, B:29:0x00e3, B:31:0x00e7), top: B:46:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x00e7 A[Catch: JSONException -> 0x0074, TryCatch #0 {JSONException -> 0x0074, blocks: (B:3:0x0002, B:5:0x0071, B:8:0x0077, B:10:0x007d, B:12:0x0085, B:13:0x0087, B:18:0x0098, B:24:0x00ac, B:33:0x00ec, B:40:0x0118, B:42:0x0133, B:36:0x010b, B:38:0x010f, B:29:0x00e3, B:31:0x00e7), top: B:46:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:35:0x0104  */
    /* JADX WARN: Code duplicated, block: B:36:0x010b A[Catch: JSONException -> 0x0074, TryCatch #0 {JSONException -> 0x0074, blocks: (B:3:0x0002, B:5:0x0071, B:8:0x0077, B:10:0x007d, B:12:0x0085, B:13:0x0087, B:18:0x0098, B:24:0x00ac, B:33:0x00ec, B:40:0x0118, B:42:0x0133, B:36:0x010b, B:38:0x010f, B:29:0x00e3, B:31:0x00e7), top: B:46:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x010f A[Catch: JSONException -> 0x0074, TryCatch #0 {JSONException -> 0x0074, blocks: (B:3:0x0002, B:5:0x0071, B:8:0x0077, B:10:0x007d, B:12:0x0085, B:13:0x0087, B:18:0x0098, B:24:0x00ac, B:33:0x00ec, B:40:0x0118, B:42:0x0133, B:36:0x010b, B:38:0x010f, B:29:0x00e3, B:31:0x00e7), top: B:46:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0114  */
    /* JADX WARN: Code duplicated, block: B:41:0x012f  */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        boolean z10;
        boolean z11;
        long startWarningDate;
        long startBlockDate;
        long jOptLong;
        long jOptLong2;
        long j10;
        long j11;
        try {
            String respString = response.getRespString();
            Log log = LOG;
            log.d("Get cloud info result: " + respString);
            JSONObject jSONObject = new JSONObject(respString);
            String string = jSONObject.getString("email");
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            AccountType accountType = getAccountType(jSONObject2, AccountType.UNKNOWN);
            JSONObject jSONObject3 = jSONObject2.getJSONObject("cloudflags");
            boolean z12 = jSONObject3.getBoolean(BLOCKED);
            boolean z13 = jSONObject3.getBoolean(AddFilterCommand.EXISTS);
            boolean z14 = jSONObject3.getBoolean("frozen");
            JSONObject jSONObject4 = jSONObject2.getJSONObject("space");
            long j12 = jSONObject4.getLong(NewsAnalyticsHandler.PARAM_TOTAL);
            long j13 = jSONObject4.getLong("used");
            long j14 = jSONObject4.getLong("used_mail");
            String strOptString = jSONObject2.optString("cloud_overquota_state");
            if (!TextUtils.isEmpty(this.mDefaultStatus)) {
                strOptString = this.mDefaultStatus;
            }
            if (strOptString.isEmpty() && !TextUtils.isEmpty(this.mPreviousStatus)) {
                strOptString = this.mPreviousStatus;
            }
            boolean z15 = j13 + j14 > j12;
            if (z15) {
                z10 = z15;
                if (checkIsCloudOverQuotaStatus(this.mIsCloudOverquotaStateInRequest, strOptString)) {
                    z11 = true;
                }
                String str = strOptString;
                log.d("Get cloud info is shared: " + z11);
                startWarningDate = this.mDelegate.getStartWarningDate();
                startBlockDate = this.mDelegate.getStartBlockDate();
                jOptLong = jSONObject2.optLong("cloud_overquota_start_time");
                jOptLong2 = jSONObject2.optLong("cloud_overquota_block_time");
                if (z11) {
                    if (startWarningDate == 0) {
                        if (this.mIsCloudOverquotaStateInRequest) {
                            startWarningDate = jOptLong;
                        } else {
                            startWarningDate = this.mPreviousCloudOverquotaStartTime;
                        }
                    }
                    log.d("Get cloud info start time: " + startWarningDate);
                    if (startBlockDate != 0) {
                        j11 = startBlockDate;
                    } else if (this.mIsCloudOverquotaStateInRequest) {
                        j11 = jOptLong2;
                    } else {
                        j11 = this.mPreviousCloudOverquotaBlockTime;
                    }
                    log.d("Get cloud info block time: " + j11);
                    j10 = startWarningDate;
                } else {
                    j10 = jOptLong;
                    j11 = jOptLong2;
                }
                return new Result(string).withAccountType(accountType).withBlocked(z12).withExists(z13).withFrozen(z14).withTotalBytes(j12).withUsedBytes(j13).withUsedMailBytes(j14).withOverQuota(z10).withSharedOverQuotaStartWarningDate(j10).withSharedOverQuotaStartBlockDate(j11).withCloudOverQuotaState(str);
            }
            z10 = z15;
            z11 = false;
            String str2 = strOptString;
            log.d("Get cloud info is shared: " + z11);
            startWarningDate = this.mDelegate.getStartWarningDate();
            startBlockDate = this.mDelegate.getStartBlockDate();
            jOptLong = jSONObject2.optLong("cloud_overquota_start_time");
            jOptLong2 = jSONObject2.optLong("cloud_overquota_block_time");
            if (z11) {
                if (startWarningDate == 0) {
                    if (this.mIsCloudOverquotaStateInRequest) {
                        startWarningDate = this.mPreviousCloudOverquotaStartTime;
                    } else {
                        startWarningDate = jOptLong;
                    }
                }
                log.d("Get cloud info start time: " + startWarningDate);
                if (startBlockDate != 0) {
                    j11 = startBlockDate;
                } else if (this.mIsCloudOverquotaStateInRequest) {
                    j11 = this.mPreviousCloudOverquotaBlockTime;
                } else {
                    j11 = jOptLong2;
                }
                log.d("Get cloud info block time: " + j11);
                j10 = startWarningDate;
            } else {
                j10 = jOptLong;
                j11 = jOptLong2;
            }
            return new Result(string).withAccountType(accountType).withBlocked(z12).withExists(z13).withFrozen(z14).withTotalBytes(j12).withUsedBytes(j13).withUsedMailBytes(j14).withOverQuota(z10).withSharedOverQuotaStartWarningDate(j10).withSharedOverQuotaStartBlockDate(j11).withCloudOverQuotaState(str2);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
