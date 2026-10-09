package ru.mail.setup;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.MainThread;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.preference.PreferenceManager;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.PushMeSdkConfig;
import com.vk.pushme.provider.AuthProvider;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.analytics.RequestAnalyticsInterceptor;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.config.ConfigRetriever;
import ru.mail.config.section.PushMeSdkDto;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.dependencies.PushMeEntryPoint;
import ru.mail.kit.network.tools.HttpLogger;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailapp.R;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.util.log.InternalLogger;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushMeSdkLogger;
import ru.mail.util.push.PushTokenUpdater;
import ru.mail.util.push.SettingsUtil;
import ru.mail.util.push.analytics.PushMeSdkAnalyticsImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0013H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006%"}, d2 = {"Lru/mail/setup/SetUpPushMeSdk;", "Lru/mail/setup/SetUp;", "<init>", "()V", "logger", "Lru/mail/util/push/PushMeSdkLogger;", "getLogger", "()Lru/mail/util/push/PushMeSdkLogger;", "logger$delegate", "Lkotlin/Lazy;", "setUp", "", "app", "Landroid/app/Application;", "resubscribeMailPushIfNeeded", "configRetriever", "Lru/mail/config/ConfigRetriever;", "markEmailIgnoreCaseResubscriptionRequestedIfNeeded", "isEmailIgnoreCaseResubscriptionNeeded", "", "markEmailIgnoreCaseResubscriptionRequested", "peekAuthTokenForAccount", "", "account", "context", "Landroid/content/Context;", "getHostForPusher", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "wasSdkEnabledOnPreviousLaunch", "saveSdkEnabledOnCurrentLaunch", "enabled", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "turnOnMailAnalytics", "Lru/mail/util/push/analytics/PushMeSdkAnalyticsImpl;", "isSendAnalyticEnabled", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSetUpPushMeSdk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SetUpPushMeSdk.kt\nru/mail/setup/SetUpPushMeSdk\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,241:1\n41#2,12:242\n41#2,12:254\n*S KotlinDebug\n*F\n+ 1 SetUpPushMeSdk.kt\nru/mail/setup/SetUpPushMeSdk\n*L\n143#1:242,12\n191#1:254,12\n*E\n"})
public final class SetUpPushMeSdk implements SetUp {

    @NotNull
    private static final String EMAIL_IGNORE_CASE_CONFIG_KEY = "notifications.is_email_ignorecase_enabled";

    @NotNull
    private static final String EMAIL_IGNORE_CASE_RESUBSCRIPTION_REQUESTED = "email_ignore_case_resubscription_requested";

    @NotNull
    private static final String PUSH_ME_SDK_ENABLED = "push_me_sdk_enabled_state";

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger = LazyKt.lazy(new Function0() { // from class: ru.mail.setup.u3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SetUpPushMeSdk.logger_delegate$lambda$0();
        }
    });
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SetUpPushMeSdk");

    private final PushMeSdkConfig.PusherHost getHostForPusher(Context context, ConfigRetriever configRetriever) {
        return ConfigRetriever.getBoolean$default(configRetriever, "mini_mail", false, 2, null) ? PushMeSdkConfig.PusherHost.MiniMail.INSTANCE : PushMeSdkConfig.PusherHost.AltProd.INSTANCE;
    }

    private final PushMeSdkLogger getLogger() {
        return (PushMeSdkLogger) this.logger.getValue();
    }

    private final boolean isEmailIgnoreCaseResubscriptionNeeded(Application app, ConfigRetriever configRetriever) {
        if (configRetriever.getBoolean(EMAIL_IGNORE_CASE_CONFIG_KEY, false)) {
            return !PreferenceManager.getDefaultSharedPreferences(app).getBoolean(EMAIL_IGNORE_CASE_RESUBSCRIPTION_REQUESTED, false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushMeSdkLogger logger_delegate$lambda$0() {
        return new PushMeSdkLogger("PushMeSDK", Log.INSTANCE.getLog("PushMeSDK"));
    }

    private final void markEmailIgnoreCaseResubscriptionRequested(Application app) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
        Intrinsics.checkNotNull(defaultSharedPreferences);
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        editorEdit.putBoolean(EMAIL_IGNORE_CASE_RESUBSCRIPTION_REQUESTED, true);
        editorEdit.apply();
    }

    private final void markEmailIgnoreCaseResubscriptionRequestedIfNeeded(Application app, ConfigRetriever configRetriever) {
        if (isEmailIgnoreCaseResubscriptionNeeded(app, configRetriever)) {
            LOG.i("Mail push resubscription is already requested by PushMe SDK state change");
            markEmailIgnoreCaseResubscriptionRequested(app);
        }
    }

    private final String peekAuthTokenForAccount(String account, Context context) {
        String strPeekAuthToken;
        Log log = LOG;
        log.i("Requesting auth token for " + account);
        List<MailboxProfile> accounts = CommonDataManager.from(context).getAccounts();
        if (accounts.isEmpty()) {
            log.i("No accounts found");
            return null;
        }
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context);
        for (MailboxProfile mailboxProfile : accounts) {
            if (StringsKt.equals(mailboxProfile.getLogin(), account, true) && (strPeekAuthToken = accountManagerWrapper.peekAuthToken(new Account(mailboxProfile.getLogin(), "ru.mail"), "ru.mail.oauth2.access")) != null && !StringsKt.isBlank(strPeekAuthToken)) {
                LOG.i("Found access token for profile " + mailboxProfile.getLogin());
                return strPeekAuthToken;
            }
        }
        LOG.w("Failed to obtain access token for account " + account);
        return null;
    }

    private final OkHttpClient provideOkHttpClient(Context context) {
        NetworkServiceFactory networkServiceFactory = (NetworkServiceFactory) Locator.INSTANCE.from(context).locate(NetworkServiceFactory.class);
        InternalLogger internalLoggerCreateLogger = InternalLogger.INSTANCE.createLogger("PushMeNetworkInterceptor", MailSdkEntryPoint.INSTANCE.appLogger(context), null);
        RequestAnalyticsInterceptor requestAnalyticsInterceptorAnalyticsInterceptor = PushMeEntryPoint.INSTANCE.analyticsInterceptor(context);
        HttpLogger httpLogger = new HttpLogger(internalLoggerCreateLogger);
        OkHttpClient.Builder builderRetryOnConnectionFailure = networkServiceFactory.getOkHttpClient().newBuilder().retryOnConnectionFailure(true);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(httpLogger);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        return builderRetryOnConnectionFailure.addInterceptor(httpLoggingInterceptor).addInterceptor(requestAnalyticsInterceptorAnalyticsInterceptor).build();
    }

    private final void resubscribeMailPushIfNeeded(Application app, ConfigRetriever configRetriever) {
        if (isEmailIgnoreCaseResubscriptionNeeded(app, configRetriever)) {
            LOG.i("Email ignore-case fix is enabled, resending all mail push subscriptions");
            SettingsUtil.sendSettingsAllAccounts(app);
            markEmailIgnoreCaseResubscriptionRequested(app);
        }
    }

    private final void saveSdkEnabledOnCurrentLaunch(Context context, boolean enabled) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNull(defaultSharedPreferences);
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        editorEdit.putBoolean(PUSH_ME_SDK_ENABLED, enabled);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String setUp$lambda$0(SetUpPushMeSdk setUpPushMeSdk, Application application, String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        try {
            Context applicationContext = application.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return setUpPushMeSdk.peekAuthTokenForAccount(account, applicationContext);
        } catch (Exception e10) {
            LOG.e("Unable to peek auth token", e10);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setUp$lambda$1(SetUpPushMeSdk setUpPushMeSdk, Application application, ConfigRetriever configRetriever) {
        setUpPushMeSdk.markEmailIgnoreCaseResubscriptionRequestedIfNeeded(application, configRetriever);
        return Unit.INSTANCE;
    }

    private final PushMeSdkAnalyticsImpl turnOnMailAnalytics(Context context, boolean isSendAnalyticEnabled) {
        if (isSendAnalyticEnabled) {
            return new PushMeSdkAnalyticsImpl(MailAppDependencies.analyticsKt(context), getLogger());
        }
        return null;
    }

    private final boolean wasSdkEnabledOnPreviousLaunch(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PUSH_ME_SDK_ENABLED, false);
    }

    @Override // ru.mail.setup.SetUp
    @MainThread
    public void setUp(@NotNull final Application app) {
        Intrinsics.checkNotNullParameter(app, "app");
        final ConfigRetriever configRetriever = AppCoreModuleEntryPoint.INSTANCE.configRetriever(app);
        PushMeSdkDto pushMeSdkDtoProvidePushMeSdkConfig = ConfigModuleEntryPoint.INSTANCE.providePushMeSdkConfig(app);
        Context applicationContext = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        boolean zWasSdkEnabledOnPreviousLaunch = wasSdkEnabledOnPreviousLaunch(applicationContext);
        if (!pushMeSdkDtoProvidePushMeSdkConfig.isInitSdkEnabled()) {
            if (!zWasSdkEnabledOnPreviousLaunch) {
                resubscribeMailPushIfNeeded(app, configRetriever);
                return;
            }
            LOG.i("SDK was enabled and now it is disabled, resending all subscriptions again");
            Context applicationContext2 = app.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "getApplicationContext(...)");
            saveSdkEnabledOnCurrentLaunch(applicationContext2, false);
            SettingsUtil.sendSettingsAllAccounts(app);
            markEmailIgnoreCaseResubscriptionRequestedIfNeeded(app, configRetriever);
            return;
        }
        Log log = LOG;
        log.i("Initializing PushMe SDK");
        PushMeSdk.Companion companion = PushMeSdk.INSTANCE;
        Context applicationContext3 = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "getApplicationContext(...)");
        companion.initialize(applicationContext3);
        companion.setAuthProvider(new AuthProvider() { // from class: ru.mail.setup.s3
            @Override // com.vk.pushme.provider.AuthProvider
            public final String getAuthToken(String str) {
                return SetUpPushMeSdk.setUp$lambda$0(this.f97361a, app, str);
            }
        });
        Context applicationContext4 = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "getApplicationContext(...)");
        PushMeSdkConfig.PusherHost hostForPusher = getHostForPusher(applicationContext4, configRetriever);
        String string = app.getResources().getString(R.string.app_version);
        long sendSubscriptionIntervalInMinutes = pushMeSdkDtoProvidePushMeSdkConfig.getSendSubscriptionIntervalInMinutes();
        boolean z10 = configRetriever.getBoolean("skip_worker_connection_check", false);
        PushMeSdkLogger logger = getLogger();
        OkHttpClient okHttpClientProvideOkHttpClient = provideOkHttpClient(app);
        Context applicationContext5 = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext5, "getApplicationContext(...)");
        companion.applyConfig(new PushMeSdkConfig("mail", hostForPusher, string, Long.valueOf(sendSubscriptionIntervalInMinutes), true, z10, logger, false, okHttpClientProvideOkHttpClient, turnOnMailAnalytics(applicationContext5, pushMeSdkDtoProvidePushMeSdkConfig.isSendAnalyticEnabled())));
        if (zWasSdkEnabledOnPreviousLaunch) {
            resubscribeMailPushIfNeeded(app, configRetriever);
        } else {
            log.i("SDK was disabled and now it is enabled, copying push tokens into SDK...");
            new PushTokenUpdater().copyPushTokenToSdk(app, new Function0() { // from class: ru.mail.setup.t3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SetUpPushMeSdk.setUp$lambda$1(this.f97373a, app, configRetriever);
                }
            });
        }
        Context applicationContext6 = app.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext6, "getApplicationContext(...)");
        saveSdkEnabledOnCurrentLaunch(applicationContext6, true);
    }
}
