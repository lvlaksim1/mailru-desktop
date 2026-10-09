package ru.mail.setup;

import android.app.Application;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.auth.restore.RestoreConstants;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.PushMeSdkConfig;
import com.vk.pushme.provider.AuthProvider;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.am.AccountManagerDelegate;
import ru.mail.locator.Locator;
import ru.mail.push.MailSdkPushComponent;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.sdk.MailSdk;
import ru.mail.sdk.MailSdkAppConfig;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.sdk.MailSdkPushConfig;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;
import ru.mail.vkteams.gost.MailSdkOkHttpManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/setup/SetUpPushMeSDKInitializer;", "Lru/mail/setup/SetUp;", "<init>", "()V", "log", "Lru/mail/util/log/Log;", "instance", "Lru/mail/util/push/component/PushComponent;", "setUp", "", "app", "Landroid/app/Application;", "initConfig", "applicationContext", "Landroid/content/Context;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSetUpPushMeSDKInitializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SetUpPushMeSDKInitializer.kt\nru/mail/setup/SetUpPushMeSDKInitializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,78:1\n1#2:79\n*E\n"})
public final class SetUpPushMeSDKInitializer implements SetUp {
    public static final int $stable = 8;

    @Nullable
    private volatile PushComponent instance;

    @NotNull
    private final Log log = Log.INSTANCE.getLog("SetUpPushesMailSdk");

    private final void initConfig(Context applicationContext) {
        MailSdkOkHttpManager mailSdkOkHttpManager = MailSdkEntryPoint.INSTANCE.mailSdkOkHttpManager(applicationContext);
        MailSdk mailSdk = MailSdk.INSTANCE;
        MailSdkPushConfig mailSdkPushConfig = mailSdk.getMailSdkPushConfig();
        MailSdkAppConfig mailSdkAppConfig = mailSdk.getMailSdkAppConfig();
        PushMeSdk.INSTANCE.applyConfig(new PushMeSdkConfig(mailSdk.getMailSdkPushConfig().getPusherApplicationName(), new PushMeSdkConfig.PusherHost.Custom(RestoreConstants.DEFAULT_URL_SCHEME, mailSdkPushConfig.getPusherHost(), null, 4, null), mailSdkAppConfig.getAppVersion(), 15L, false, true, null, mailSdkAppConfig.getIsDebug(), mailSdkOkHttpManager.configureOkHttpClient(new OkHttpClient.Builder(), mailSdkPushConfig.getPusherHost()).build(), null, 576, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String setUp$lambda$0$0(SetUpPushMeSDKInitializer setUpPushMeSDKInitializer, Application application, String account) {
        Object objM13123constructorimpl;
        Intrinsics.checkNotNullParameter(account, "account");
        try {
            Result.Companion companion = Result.INSTANCE;
            AccountManagerDelegate accountManagerDelegate = AuthenticatorEntryPoint.INSTANCE.accountManagerDelegate(application);
            objM13123constructorimpl = Result.m13123constructorimpl(accountManagerDelegate.peekAuthToken(accountManagerDelegate.getAccounts()[0], BuildConfigVariablesHolder.accountType));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m13126exceptionOrNullimpl(objM13123constructorimpl) != null) {
            objM13123constructorimpl = "";
        }
        return (String) objM13123constructorimpl;
    }

    @Override // ru.mail.setup.SetUp
    public void setUp(@NotNull final Application app) {
        Intrinsics.checkNotNullParameter(app, "app");
        if (this.instance == null) {
            synchronized (this) {
                try {
                    if (this.instance == null) {
                        this.log.i("PushMeSdk in mail sdk start initializing");
                        this.instance = new MailSdkPushComponent(app);
                        PushMeSdk.Companion companion = PushMeSdk.INSTANCE;
                        companion.initialize(app);
                        Context applicationContext = app.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                        initConfig(applicationContext);
                        companion.setAuthProvider(new AuthProvider() { // from class: ru.mail.setup.r3
                            @Override // com.vk.pushme.provider.AuthProvider
                            public final String getAuthToken(String str) {
                                return SetUpPushMeSDKInitializer.setUp$lambda$0$0(this.f97355a, app, str);
                            }
                        });
                        this.log.i("PushMeSdk in mail sdk initialized");
                        Locator.INSTANCE.from(app).register(PushComponent.class, this.instance);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }
}
