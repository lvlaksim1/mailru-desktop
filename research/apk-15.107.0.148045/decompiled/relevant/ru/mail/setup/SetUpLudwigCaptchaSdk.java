package ru.mail.setup;

import android.app.Application;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.section.NewAuthSdkConfigDto;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lru/mail/setup/SetUpLudwigCaptchaSdk;", "Lru/mail/setup/SetUp;", "<init>", "()V", "setUp", "", "app", "Landroid/app/Application;", "createLudwigCaptchaAnalyticsCallback", "Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "context", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SetUpLudwigCaptchaSdk implements SetUp {
    public static final int $stable = 0;

    private final LudwigAnalyticsCallback createLudwigCaptchaAnalyticsCallback(Context context) {
        final MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(context);
        return new LudwigAnalyticsCallback() { // from class: ru.mail.setup.SetUpLudwigCaptchaSdk.createLudwigCaptchaAnalyticsCallback.1
            @Override // ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback
            public void onAnalyticEvent(String eventName, Map<String, String> params) {
                Intrinsics.checkNotNullParameter(eventName, "eventName");
                mailAppAnalyticsAnalytics.onLudwigScreenSdkEvent(eventName, params);
            }
        };
    }

    @Override // ru.mail.setup.SetUp
    public void setUp(@NotNull Application app) {
        Intrinsics.checkNotNullParameter(app, "app");
        if (((NewAuthSdkConfigDto) AppCoreModuleEntryPoint.INSTANCE.configRetriever(app).getSerializable("new_auth_sdk_config", Reflection.getOrCreateKotlinClass(NewAuthSdkConfigDto.class))).getLudwigCaptcha().isLudwigCaptchaEnabled()) {
            LudwigSdkInitializer.INSTANCE.initialize(createLudwigCaptchaAnalyticsCallback(app));
        }
    }
}
