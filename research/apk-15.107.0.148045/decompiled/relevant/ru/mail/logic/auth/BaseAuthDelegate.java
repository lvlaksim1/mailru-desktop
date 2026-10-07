package ru.mail.logic.auth;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.appsflyer.AppsFlyerLib;
import com.vk.auth.main.VkClientAuthLib;
import com.vk.lists.PaginationHelper;
import java.util.Arrays;
import java.util.Set;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.BaseAuthActivity;
import ru.mail.auth.DefaultTokenPairListener;
import ru.mail.auth.EncryptedPreferencesEntryPoint;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.MailLoginFragment;
import ru.mail.auth.TokenParser;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.util.DomainUtils;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.auth.webview.OAuth2Helper;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.data.VkRestoreParamHolder;
import ru.mail.credentialsexchanger.data.entity.VkIdAuthSource;
import ru.mail.data.cmd.VkStatEventsManager;
import ru.mail.data.cmd.account_manager.AddAccountToAccountManager;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.ClearProfileCommand;
import ru.mail.data.cmd.imap.ProviderInfo;
import ru.mail.data.cmd.imap.ProvidersParser;
import ru.mail.data.cmd.server.PostAuthCommand;
import ru.mail.data.dao.AuthorityProvider;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.feature.features.UserDataFeature;
import ru.mail.logic.content.imap.ImapPersistProviderInfo;
import ru.mail.logic.content.imap.ImapProviderInfoWrapper;
import ru.mail.logic.content.imap.Source;
import ru.mail.logic.content.impl.BaseMailboxContext;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.content.impl.ImapLogger;
import ru.mail.logic.content.impl.UsedDomainsEvaluator;
import ru.mail.logic.navigation.restoreauth.SessionRestoreHelper;
import ru.mail.logic.share.MailFileProvider;
import ru.mail.logic.sync.ChildAccountsAuthManager;
import ru.mail.mailbox.cmd.AlreadyDoneObservableFuture;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.CompleteObserver;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.mails.R;
import ru.mail.mytracker.MyTrackerWrapper;
import ru.mail.mytracker.di.MyTrackerEntryPoint;
import ru.mail.portal.PortalManager;
import ru.mail.registration.Statistic;
import ru.mail.registration.ui.AuthDelegate;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.ui.RequestCode;
import ru.mail.ui.fragments.mailbox.newmail.SendNewMessageHandler;
import ru.mail.ui.fragments.settings.navigation.ClassProvider;
import ru.mail.util.ReferenceTableStateKeeper;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.SettingsUtil;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class BaseAuthDelegate<T extends Activity> extends AuthDelegate<T> {
    public static final String BUNDLE_WELCOME_SCREEN_ACCOUNT_KEY = "welcome_screen_account_key";
    public static final String EXTRA_TRANSPORT = "extra_transport";
    private static final String FORCED_TRANSPORT_EXTRA = "forced_transport";
    private Bundle mResult;
    private static final Log LOG = Log.getLog("BaseAuthDelegate");
    private static final Authenticator.Type[] sOauthProviders = {Authenticator.Type.YAHOO_OAUTH, Authenticator.Type.OAUTH, Authenticator.Type.OUTLOOK_OAUTH, Authenticator.Type.YANDEX_OAUTH};
    private static final LogFilter sLogFilter = new LogFilter(OAuth2Helper.ACCESS_TOKEN_FORMAT, OAuth2Helper.REFRESH_TOKEN_FORMAT);
    public static final Parcelable.Creator<BaseAuthDelegate> CREATOR = new Parcelable.Creator<BaseAuthDelegate>() { // from class: ru.mail.logic.auth.BaseAuthDelegate.5
        @Override // android.os.Parcelable.Creator
        public BaseAuthDelegate createFromParcel(Parcel parcel) {
            return new BaseAuthDelegate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BaseAuthDelegate[] newArray(int i10) {
            return new BaseAuthDelegate[i10];
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    private static class AccountCheckResult {
        private Account[] mAccounts;
        private boolean mIsAccountAlreadyExists;

        AccountCheckResult(boolean z10, Account[] accountArr) {
            this.mIsAccountAlreadyExists = z10;
            this.mAccounts = accountArr;
        }

        Account[] getAccounts() {
            return this.mAccounts;
        }

        boolean isAccountAlreadyExists() {
            return this.mIsAccountAlreadyExists;
        }
    }

    public BaseAuthDelegate() {
    }

    public static void addDistributorParams(Context context, Bundle bundle) {
        bundle.putString("authCurrent", Distributors.CURRENT_DISTRIBUTOR);
        bundle.putString("authFirst", Distributors.getFirstDistributor(context).toString());
    }

    private AccountCheckResult checkAccountsInAccountManager(Context context, String str) {
        Account[] appAccounts = Authenticator.getAccountManagerWrapper(context.getApplicationContext()).getAppAccounts();
        for (Account account : appAccounts) {
            if (account.name.equals(str)) {
                return new AccountCheckResult(true, appAccounts);
            }
        }
        return new AccountCheckResult(false, appAccounts);
    }

    private Authenticator.Type extractType(Bundle bundle) {
        String string = bundle.getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
        if (string == null) {
            string = bundle.getString("type");
        }
        return string == null ? Authenticator.Type.DEFAULT : Authenticator.Type.valueOf(string);
    }

    private String getAuthSource(Bundle bundle) {
        if (bundle.getString("login_extra_xmail_migration_from") != null) {
            return "Migration";
        }
        if (bundle.getParcelable(VkIdAuthSource.KEY) == VkIdAuthSource.AutoLogin) {
            return "Autologin";
        }
        if (bundle.getParcelable(VkIdAuthSource.KEY) == VkIdAuthSource.Restore) {
            return "Restore";
        }
        return isRegistration(bundle) ? "Registration" : "Auth";
    }

    private String getBindType(Bundle bundle) {
        if (bundle.containsKey(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY)) {
            return bundle.getString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, "none");
        }
        return SocialLoginInfoHolder.getBindType() != null ? SocialLoginInfoHolder.getBindType().getStringToken() : "none";
    }

    private DataManager getDataManager(Context context) {
        return CommonDataManager.from(context);
    }

    private String getForcedTransport(T t10, MailboxProfile mailboxProfile) {
        return (isOauthProvider(mailboxProfile.getLogin()) && isImapProviderExistInAccountManager(t10, mailboxProfile)) ? MailboxProfile.TransportType.IMAP.name() : PreferenceManager.getDefaultSharedPreferences(t10).getString(FORCED_TRANSPORT_EXTRA, null);
    }

    private boolean isImapProviderExistInAccountManager(T t10, MailboxProfile mailboxProfile) {
        return Authenticator.getAccountManagerWrapper(t10.getApplicationContext()).getUserData(new Account(mailboxProfile.getLogin(), getAccountType()), ProviderInfo.KEY_PROVIDER_INFO) != null;
    }

    public static boolean isOauthProvider(String str) {
        return Arrays.asList(sOauthProviders).contains(Authenticator.Type.getTypeByDomain(DomainUtils.getDomainWithoutCheck(str)));
    }

    private boolean isRegistration(Bundle bundle) {
        return bundle.getBoolean(Authenticator.EXTRA_FROM_REGISTRATION, false);
    }

    private void onAccountAuthDone(String str, Context context) {
        if (str != null) {
            VkStatEventsManager.notifyExternalAuth(context, str);
        } else {
            LOG.i("onAccountAuthDone account name is null");
            MailAppDependencies.analytics(context).onAccountAuthDoneError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddAccountError(T t10) {
        MailSdkEntryPoint.appReporter(t10).builder().withText(R.string.add_account_explicitly_error).report();
    }

    private void onAddAccountSuccess(final T t10, final MailboxProfile mailboxProfile, final Account account, final boolean z10) {
        final DataManager dataManager = getDataManager(t10);
        dataManager.setAcceptReceiveNewsletters(mailboxProfile.getLogin(), dataManager.isAcceptReceiveNewslettersShared());
        dataManager.lockSyncUntil(new DataManager.LockSyncUntil<CommandStatus<?>>() { // from class: ru.mail.logic.auth.BaseAuthDelegate.3
            @Override // ru.mail.logic.content.DataManager.LockSyncUntil
            public ObservableFuture<CommandStatus<?>> lockUntil() {
                BaseAuthDelegate.LOG.d("Setting account = " + account);
                new SessionRestoreHelper(t10).cancel();
                BaseAuthDelegate.this.requestSync(t10, account);
                if (!z10) {
                    BaseAuthDelegate.this.setAccount(t10, mailboxProfile);
                    ChildAccountsAuthManager.from(t10).setForceAuthChildAccounts();
                }
                BaseAuthDelegate.this.setAccountAuthenticatorResultOK(mailboxProfile);
                BaseAuthDelegate.this.registerAccountToGCM(t10);
                ObservableFuture<CommandStatus<?>> alreadyDoneObservableFuture = new AlreadyDoneObservableFuture<>(null);
                if (dataManager.isFeatureSupported(UserDataFeature.INSTANCE, new Void[0])) {
                    if (z10) {
                        BaseAuthDelegate.this.refreshDataOnAccount(dataManager, mailboxProfile);
                    } else {
                        alreadyDoneObservableFuture = dataManager.refreshUserDataWithoutAuth();
                    }
                }
                dataManager.forceAppSettingsSync();
                SendNewMessageHandler lazyFactoryHolder = SendNewMessageHandler.INSTANCE.getInstance();
                if (lazyFactoryHolder != null) {
                    lazyFactoryHolder.sendPendingMessages(t10.getApplicationContext());
                }
                BaseAuthDelegate.this.setOkResult(t10);
                return alreadyDoneObservableFuture;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDataOnAccount(DataManager dataManager, final MailboxProfile mailboxProfile) {
        dataManager.refreshUserData(new BaseMailboxContext(mailboxProfile), new DataManager.Callback<DataManager.OnCompleteListener>() { // from class: ru.mail.logic.auth.BaseAuthDelegate.4
            @Override // ru.mail.logic.content.DataManager.Callback
            public void handle(DataManager.Call<DataManager.OnCompleteListener> call) {
                BaseAuthDelegate.LOG.d("Refresh user data for " + mailboxProfile.getLogin() + " || is successful");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerAccountToGCM(Context context) {
        boolean z10 = false;
        for (PushMessagesTransport pushMessagesTransport : ((PushComponent) Locator.from(context).locate(PushComponent.class)).getPushMessagesTransports()) {
            if (pushMessagesTransport.isRegistered()) {
                z10 = true;
            } else {
                pushMessagesTransport.launchPushUpdater();
            }
        }
        if (z10) {
            SettingsUtil.registerAccount(context);
        }
    }

    private void removeUnauthorizedKey(T t10, Bundle bundle) {
        Authenticator.getAccountManagerWrapper(t10.getApplicationContext()).setUserData(new Account(bundle.getString("authAccount"), getAccountType()), Authenticator.KEY_UNAUTHORIZED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSync(T t10, Account account) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("force", true);
        bundle.putBoolean("expedited", true);
        getDataManager(t10).requestSync(account, AuthorityProvider.getMailContentProviderAuthority(t10), bundle);
    }

    private void saveDomain(Context context, String str) {
        for (String str2 : context.getResources().getStringArray(ru.mail.Authenticator.R.array.corp_domains)) {
            if (str.endsWith(str2)) {
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str2, true).apply();
                return;
            }
        }
    }

    private void saveImapSettings(@NonNull String str, Context context, String str2) {
        ImapSettingsProcessor imapSettingsProcessor = (ImapSettingsProcessor) Locator.locate(context, ImapSettingsProcessor.class);
        ImapPersistProviderInfo providerWithTypeCheck = new ProvidersParser().parseProviderWithTypeCheck(str);
        if (providerWithTypeCheck == null || !providerWithTypeCheck.isProvidedFromExternal()) {
            return;
        }
        imapSettingsProcessor.saveImapSettings(new ImapProviderInfoWrapper(providerWithTypeCheck, str, str2, Source.EXTERNAL), str2);
    }

    private MailboxProfile selectTransport(T t10, MailboxProfile mailboxProfile, Bundle bundle) {
        String forcedTransport = getForcedTransport(t10, mailboxProfile);
        if (!TextUtils.isEmpty(forcedTransport)) {
            return mailboxProfile.switchTransport(MailboxProfile.TransportType.valueOf(forcedTransport));
        }
        String string = bundle.getString(EXTRA_TRANSPORT);
        return !TextUtils.isEmpty(string) ? mailboxProfile.switchTransport(MailboxProfile.TransportType.valueOf(string)) : mailboxProfile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:13:0x00bc  */
    public void sendAnalytics(Context context, Bundle bundle, boolean z10, int i10) {
        Set<String> set;
        String str;
        Set<String> domains = CommonDataManager.from(context).getDomains();
        if (bundle.getBoolean(Statistic.NEED_SEND_AUTH_DONE)) {
            String string = bundle.getString("EMAIL");
            UsedDomainsEvaluator usedDomainsEvaluator = new UsedDomainsEvaluator(domains);
            if (string == null) {
                string = bundle.getString("authAccount");
            }
            String strEvaluate = usedDomainsEvaluator.evaluate(new MailboxProfile(string));
            String string2 = bundle.getString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_FROM, "");
            if (usedDomainsEvaluator.getAbort()) {
                set = domains;
                str = "authAccount";
            } else {
                str = "authAccount";
                set = domains;
                MailAppDependencies.analytics(context).authDoneAction(bundle.getBoolean(Authenticator.IS_LOGIN_EXISTING_ACCOUNT), bundle.getString(Statistic.RESTORE_TYPE, ""), bundle.getString(Statistic.IS_RESTORE, ""), bundle.getString(Statistic.HAS_ACCOUNTS, ""), bundle.getString("from"), strEvaluate, bundle.getString(Statistic.AUTH_TYPE, ""), getBindType(bundle), string, string2, bundle.getString(Statistic.TOKEN_TYPE, "unknown"));
                if (bundle.getBoolean(MailAccountConstants.AUTH_ANALYTICS_BY_GROUP_ENABLED)) {
                    MailAppDependencies.analytics(context).authDoneActionByGroup(bundle.getBoolean(MailAccountConstants.AUTH_ANALYTICS_IS_A_GROUP, false), bundle.getBoolean(Authenticator.IS_LOGIN_EXISTING_ACCOUNT), bundle.getString(Statistic.HAS_ACCOUNTS, ""), bundle.getString("from"), strEvaluate, bundle.getString(Statistic.AUTH_TYPE, ""), getBindType(bundle));
                }
            }
        } else {
            set = domains;
            str = "authAccount";
        }
        UsedDomainsEvaluator usedDomainsEvaluator2 = new UsedDomainsEvaluator(set);
        String string3 = bundle.getString(str);
        MailAppDependencies.analytics(context).sendAuthDoneAnalytics(usedDomainsEvaluator2.evaluate(new MailboxProfile(string3)), getAuthSource(bundle), z10, i10, bundle.getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, "UNKNOWN_TYPE"), getBindType(bundle), string3, bundle.getString("login_extra_xmail_migration_from", ""), VkRestoreParamHolder.getAndResetParam());
        AuthenticatorEntryPoint.accountManagerFallbackAnalytics(context).sendAuthDone(string3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccount(Context context, MailboxProfile mailboxProfile) {
        getDataManager(context).setAccount(mailboxProfile, true);
        saveDomain(context, mailboxProfile.getLogin());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccountAuthenticatorResultOK(MailboxProfile mailboxProfile) {
        Bundle bundle = new Bundle();
        bundle.putString("authAccount", mailboxProfile.getLogin());
        bundle.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, getAccountType());
        this.mResult = bundle;
    }

    private void setDirectAccessToken(String str, AccountManagerWrapper accountManagerWrapper, Account account) {
        if (str != null) {
            LOG.d("Direct access token set: " + sLogFilter.filter(OAuth2Helper.ACCESS_TOKEN_FORMAT.getFormattedMsg(str)));
            accountManagerWrapper.setAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS, str);
        }
    }

    private void setExtraUserData(AccountManagerWrapper accountManagerWrapper, Account account, Bundle bundle) {
        String string = bundle.getString(Authenticator.PARAM_EMAIL_SERVICE_TYPE);
        boolean z10 = bundle.getBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, false);
        if (string != null) {
            accountManagerWrapper.setUserData(account, Authenticator.ACCOUNT_PARAMETER_EMAIL_SERVICE_TYPE, string);
        }
        boolean z11 = bundle.getBoolean(Authenticator.NEED_FORCE_CREATE_COLLECTOR);
        boolean z12 = bundle.getBoolean(Authenticator.IS_MIGRANT_REGISTRATION);
        accountManagerWrapper.setUserData(account, Authenticator.NEED_FORCE_CREATE_COLLECTOR, Boolean.toString(z11));
        accountManagerWrapper.setUserData(account, Authenticator.IS_MIGRANT_REGISTRATION, Boolean.toString(z12));
        accountManagerWrapper.setUserData(account, "phone_number", bundle.getString(Authenticator.EXTRA_SMS_PHONE));
        accountManagerWrapper.setUserData(account, MailboxProfile.ACCOUNT_KEY_USER_ID, BaseAuthDelegateUtils.generateAccountUserId(account.name));
        accountManagerWrapper.setUserData(account, Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, Boolean.toString(z10));
    }

    private void setMailruOAuthTokens(String str, String str2, AccountManagerWrapper accountManagerWrapper, Account account) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        accountManagerWrapper.setUserData(account, Authenticator.KEY_OAUTH_ENABLED, Boolean.toString(true));
        accountManagerWrapper.setAuthToken(account, "ru.mail.oauth2.access", str);
        accountManagerWrapper.setAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOkResult(T t10) {
        Intent intent = new Intent();
        intent.putExtra(BaseAuthActivity.AUTHORIZATION_RESULT_KEY, this.mResult);
        t10.setResult(-1, intent);
    }

    private void setYandexExpiredTokenTime(long j10, AccountManagerWrapper accountManagerWrapper, Account account) {
        if (j10 != -1) {
            accountManagerWrapper.setUserData(account, MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN_EXPIRED_TIME, String.valueOf(j10));
        }
    }

    private void startMailActivity(T t10, int i10) {
        PortalManager portalManager = (PortalManager) Locator.locate(t10, PortalManager.class);
        Intent intentAddFlags = new Intent().addFlags(i10);
        if (portalManager.checkForPortalModeIsActive(true)) {
            Class<?> mailPortalActivity = ClassProvider.getMailPortalActivity();
            if (mailPortalActivity != null) {
                intentAddFlags.setClass(t10, mailPortalActivity);
            }
        } else {
            Class<?> slideStackActivity = ClassProvider.getSlideStackActivity();
            if (slideStackActivity != null) {
                intentAddFlags.setClass(t10, slideStackActivity);
            }
        }
        t10.startActivity(intentAddFlags);
        t10.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void startWelcomeActivity(T t10, String str) {
        t10.startActivityForResult(getWelcomeActivityIntent(t10, str), RequestCode.START_WELCOME.id());
    }

    private void trackMyTrackerEvent(@NonNull Context context, Bundle bundle, String str, String str2) {
        MyTrackerWrapper myTrackerWrapper = MyTrackerEntryPoint.myTrackerWrapper(context);
        if (isRegistration(bundle)) {
            myTrackerWrapper.trackRegistrationEvent(str, str2);
            LOG.d("MyTracker: track registration event complete");
        } else {
            myTrackerWrapper.trackLoginEvent(str, str2);
            LOG.d("MyTracker: track login event complete");
        }
    }

    protected void addNewAccount(final T t10, MailboxProfile mailboxProfile, Bundle bundle) {
        String string = bundle.getString("authtoken");
        String string2 = bundle.getString(MailAccountConstants.SECURITY_TOKEN);
        String string3 = bundle.getString("account_key_first_name");
        String string4 = bundle.getString("account_key_last_name");
        String string5 = bundle.getString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, null);
        String string6 = bundle.getString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN);
        String string7 = bundle.getString("ru.mail.oauth2.access");
        String string8 = bundle.getString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH);
        String string9 = bundle.getString(ProviderInfo.KEY_PROVIDER_INFO);
        String string10 = bundle.getString("unread_count", PaginationHelper.DEFAULT_NEXT_FROM);
        String string11 = bundle.getString(Authenticator.ACCOUNT_KEY_PARENT_CONTROL_BOUNDED_PARENT_ACCOUNT, null);
        boolean z10 = (isRegistration(bundle) || string11 == null) ? false : true;
        long j10 = bundle.getLong(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN_EXPIRED_TIME, -1L);
        String strValueOf = Authenticator.getAccountType(mailboxProfile.getLogin(), bundle) == Authenticator.Type.VK_CONNECT ? String.valueOf(VkClientAuthLib.INSTANCE.getUserId()) : null;
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(t10.getApplicationContext());
        boolean z11 = z10;
        Account account = new Account(mailboxProfile.getLogin(), BuildConfigVariablesHolder.accountType);
        String domainWithoutCheck = DomainUtils.getDomainWithoutCheck(mailboxProfile.getLogin());
        if (string9 != null && !TextUtils.isEmpty(domainWithoutCheck)) {
            saveImapSettings(string9, t10.getApplicationContext(), domainWithoutCheck);
        }
        if (checkAccountsInAccountManager(t10, mailboxProfile.getLogin()).isAccountAlreadyExists()) {
            LOG.d("Account = " + mailboxProfile.getLogin() + " is already exists in account manager");
            accountManagerWrapper.setPassword(account, mailboxProfile.getPassword());
            mailboxProfile.clearPassword();
            if (!TextUtils.isEmpty(string)) {
                accountManagerWrapper.setAuthToken(account, "ru.mail", string);
            }
            if (accountManagerWrapper.getUserData(account, "type") == null) {
                accountManagerWrapper.setUserData(account, "type", Authenticator.Type.DEFAULT.toString());
            }
            accountManagerWrapper.setUserData(account, Authenticator.KEY_VKC_ID, strValueOf);
            accountManagerWrapper.setUserData(account, ProviderInfo.KEY_PROVIDER_INFO, string9);
            setDirectAccessToken(string6, accountManagerWrapper, account);
            setMailruOAuthTokens(string7, string8, accountManagerWrapper, account);
            setYandexExpiredTokenTime(j10, accountManagerWrapper, account);
            new TokenParser(new DefaultTokenPairListener(accountManagerWrapper, account)).handleSignsAndTokens(string2);
            onAddAccountSuccess(t10, mailboxProfile, account, z11);
            if (z11) {
                return;
            }
            onAddAccountFinish(t10);
            return;
        }
        AddAccountToAccountManager addAccountToAccountManager = new AddAccountToAccountManager(accountManagerWrapper, account, mailboxProfile.getPassword());
        addAccountToAccountManager.addUserData(MailboxProfile.COL_NAME_ORDER_NUMBER, String.valueOf(mailboxProfile.getOrderNumber())).addUserData("type", mailboxProfile.getType().toString()).addUserData("account_key_first_name", string3).addUserData("account_key_last_name", string4).addUserData("unread_count", string10).addUserData(ProviderInfo.KEY_PROVIDER_INFO, string9).addUserData(Authenticator.KEY_VKC_ID, strValueOf).addUserData(Authenticator.ACCOUNT_KEY_PARENT_CONTROL_BOUNDED_PARENT_ACCOUNT, string11);
        if (string5 != null) {
            addAccountToAccountManager.addUserData(Authenticator.KEY_USER_2FACTOR_TSA_COOKIE, string5);
            EncryptedPreferencesEntryPoint.encryptedPreferences(t10).edit().putString(MailSecondStepFragment.getPrefKey(account.name), string5).apply();
        }
        Account[] appAccounts = accountManagerWrapper.getAppAccounts();
        DataManager dataManager = getDataManager(t10);
        Account account2 = appAccounts.length > 0 ? appAccounts[0] : null;
        if (!addAccountToAccountManager.addAccountExplicitly()) {
            LOG.e("Add account = " + account.name + " explicitly was failed");
            AccountManagerFallback.addAccountFallback(t10.getApplicationContext(), account, "BaseAuthDelegate");
            new ClearProfileCommand(t10, mailboxProfile, accountManagerWrapper, ReferenceTableStateKeeper.from(t10.getApplicationContext()).getReferenceRepoFactory()).execute((RequestArbiter) Locator.locate(t10.getApplicationContext(), RequestArbiter.class)).observe(Schedulers.mainThread(), new CompleteObserver<AsyncDbHandler.CommonResponse<MailboxProfile, String>>() { // from class: ru.mail.logic.auth.BaseAuthDelegate.2
                @Override // ru.mail.mailbox.cmd.CompleteObserver
                public void onComplete() {
                    BaseAuthDelegate.this.onAddAccountError(t10);
                }
            });
            return;
        }
        LOG.d("Add account = " + account.name + " explicitly was success");
        mailboxProfile.clearPassword();
        setExtraUserData(accountManagerWrapper, account, bundle);
        if (!TextUtils.isEmpty(string)) {
            accountManagerWrapper.setAuthToken(account, "ru.mail", string);
        }
        setMailruOAuthTokens(string7, string8, accountManagerWrapper, account);
        setYandexExpiredTokenTime(j10, accountManagerWrapper, account);
        setDirectAccessToken(string6, accountManagerWrapper, account);
        new TokenParser(new DefaultTokenPairListener(accountManagerWrapper, account)).handleSignsAndTokens(string2);
        addAccountToAccountManager.setSyncAutomatically(AuthorityProvider.getMailContentProviderAuthority(dataManager.getApplicationContext()), AuthorityProvider.getContactsContentProviderAuthority(dataManager.getApplicationContext()));
        MailFileProvider.invalidateFilesPathsCache();
        if (account2 != null) {
            dataManager.getPinStorage().refreshPinDataForAccount(account2, account);
        }
        onAddAccountSuccess(t10, mailboxProfile, account, z11);
        trackMyTrackerEvent(t10, bundle, mailboxProfile.getLogin(), strValueOf);
        if (z11) {
            return;
        }
        startWelcomeActivity(t10, mailboxProfile.getLogin());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public String getAccountType() {
        return BuildConfigVariablesHolder.accountType;
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public Bundle getExtraAuthParameters(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("extenid", AppsFlyerLib.getInstance().getAppsFlyerUID(context));
        addDistributorParams(context, bundle);
        return bundle;
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public Bundle getResult() {
        return this.mResult;
    }

    @NonNull
    protected Intent getWelcomeActivityIntent(T t10, String str) {
        return new Intent(t10, ClassProvider.getWelcomeActivity()).setAction(IntentActionsProvider.actionWelcome).addCategory("android.intent.category.DEFAULT").putExtra(BUNDLE_WELCOME_SCREEN_ACCOUNT_KEY, str);
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public void goToAccount(String str, T t10) {
        LOG.d("Go to account = " + str);
        DataManager dataManager = getDataManager(t10);
        dataManager.setAccount(dataManager.getAccount(str));
        startMailActivity(t10, 268468224);
    }

    protected void onAddAccountFinish(T t10) {
        setOkResult(t10);
        t10.finish();
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public void onAuthSucceeded(final T t10, final Bundle bundle) {
        final Context applicationContext = t10.getApplicationContext();
        String string = bundle.getString("authAccount");
        final AccountCheckResult accountCheckResultCheckAccountsInAccountManager = checkAccountsInAccountManager(applicationContext, string);
        onAccountAuthDone(string, applicationContext);
        Statistic.setEnterType(PreferenceManager.getDefaultSharedPreferences(applicationContext), Authenticator.getAccountManagerWrapper(applicationContext).getAppAccounts().length > 0 ? 3 : 2);
        removeUnauthorizedKey(t10, bundle);
        RequestArbiter requestArbiter = (RequestArbiter) Locator.locate(applicationContext, RequestArbiter.class);
        MailboxProfile mailboxProfile = new MailboxProfile(bundle.getString("authAccount"), bundle.getString("password"));
        mailboxProfile.setType(extractType(bundle));
        final MailboxProfile mailboxProfileSelectTransport = selectTransport(t10, mailboxProfile, bundle);
        new PostAuthCommand(t10, mailboxProfileSelectTransport).execute(requestArbiter).observe(Schedulers.mainThread(), new CompleteObserver<Object>() { // from class: ru.mail.logic.auth.BaseAuthDelegate.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.mailbox.cmd.CompleteObserver
            public void onComplete() {
                BaseAuthDelegate.this.addNewAccount(t10, mailboxProfileSelectTransport, bundle);
                BaseAuthDelegate.this.sendAnalytics(applicationContext, bundle, accountCheckResultCheckAccountsInAccountManager.isAccountAlreadyExists(), accountCheckResultCheckAccountsInAccountManager.getAccounts().length);
            }
        });
        if (mailboxProfileSelectTransport.getTransportType() == MailboxProfile.TransportType.IMAP) {
            ImapLogger.onTransportSwitchedToImap(MailAppDependencies.analytics(applicationContext), mailboxProfileSelectTransport, true);
        } else {
            ImapLogger.resetForProfile(mailboxProfileSelectTransport);
        }
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public void openMailForCurrentAccount(T t10) {
        startMailActivity(t10, 268468224);
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public void setResult(Bundle bundle) {
        this.mResult = bundle;
    }

    @Override // ru.mail.registration.ui.AuthDelegate
    public void switchToAccount(String str, T t10) {
        LOG.d("switchToAccount " + str);
        DataManager dataManager = getDataManager(t10);
        dataManager.setAccount(dataManager.getAccount(str));
        if (t10.getIntent().getBooleanExtra(Authenticator.SHOULD_CHECK_LOGIN_STATUS, true)) {
            Toast.makeText(t10, t10.getResources().getString(R.string.already_connected, str), 0).show();
        }
        startMailActivity(t10, 67108864);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeBundle(this.mResult);
    }

    protected BaseAuthDelegate(Parcel parcel) {
        this.mResult = parcel.readBundle();
    }
}
