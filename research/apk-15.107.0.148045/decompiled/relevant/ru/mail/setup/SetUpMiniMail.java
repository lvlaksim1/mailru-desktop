package ru.mail.setup;

import android.app.Application;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.ConfigRetriever;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.mails.R;
import ru.mail.mini_mail_api.HostProvider;
import ru.mail.mini_mail_api.MiniMailManager;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\u000b"}, d2 = {"Lru/mail/setup/SetUpMiniMail;", "Lru/mail/setup/SetUpService;", "Lru/mail/mini_mail_api/MiniMailManager;", "<init>", "()V", "onCreateServiceImpl", "app", "Landroid/app/Application;", "createHostProvider", "Lru/mail/mini_mail_api/HostProvider;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SetUpMiniMail extends SetUpService<MiniMailManager> {
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SetUpMiniMail");

    public SetUpMiniMail() {
        super(MiniMailManager.class);
    }

    private final HostProvider createHostProvider(Application app) {
        HostProvider hostProviderMiniMailHostProvider = MailSdkEntryPoint.INSTANCE.miniMailHostProvider(app);
        hostProviderMiniMailHostProvider.setPreferenceSchemeAuth(R.string.auth_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostAuth(R.string.auth_default_host);
        int i10 = ru.mail.Authenticator.R.string.doreg_captcha_default_scheme;
        hostProviderMiniMailHostProvider.setPreferenceSchemeDoregCaptcha(i10);
        hostProviderMiniMailHostProvider.setPreferenceHostDoregCaptcha(ru.mail.Authenticator.R.string.doreg_captcha_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeDoreg(i10);
        hostProviderMiniMailHostProvider.setPreferenceHostDoreg(ru.mail.Authenticator.R.string.doreg_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemePush(R.string.push_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostPush(R.string.push_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeNewMailApi(R.string.mail_api_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostNewMailApi(R.string.new_mail_api_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeAvatar(R.string.avatar_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostAvatar(R.string.avatar_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeAttachPreview(R.string.attach_preview_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostAttachPreview(R.string.attach_preview_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeDomainSettings(R.string.domain_settings_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostDomainSettings(R.string.domain_settings_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeAuthstat(R.string.authstat_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostAuthstat(R.string.authstat_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeRegistration(R.string.registration_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostRegistration(R.string.registration_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeCloudDispatcher(R.string.cloud_dispatcher_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostCloudDispatcher(R.string.cloud_dispatcher_default_host);
        hostProviderMiniMailHostProvider.setPreferenceSchemeCloudReferer(R.string.cloud_referer_default_scheme);
        hostProviderMiniMailHostProvider.setPreferenceHostCloudReferer(R.string.cloud_referer_default_host);
        return hostProviderMiniMailHostProvider;
    }

    @Override // ru.mail.setup.SetUpService
    @NotNull
    public MiniMailManager onCreateServiceImpl(@NotNull Application app) {
        Intrinsics.checkNotNullParameter(app, "app");
        MiniMailManager miniMailManager = MailSdkEntryPoint.INSTANCE.miniMailManager(app);
        miniMailManager.init(ConfigRetriever.getBoolean$default(AppCoreModuleEntryPoint.INSTANCE.configRetriever(app), "mini_mail", false, 2, null));
        Locator.INSTANCE.from(app).register(HostProvider.class, createHostProvider(app));
        LOG.d("Prepare MiniMailManager set up");
        return miniMailManager;
    }
}
