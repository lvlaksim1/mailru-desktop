package ru.mail.authorizationsdk.feature.captcha;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;
import ru.mail.util.log.Logger;
import statusnavbars.StatusNavBarHelper;

/* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel_Factory, reason: case insensitive filesystem */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"ru.mail.march.concurrent.ViewModelDispatcher"})
public final class C1625WebCaptchaComposeViewModel_Factory {
    private final Provider<LudwigCaptchaAnalyticEvents> analyticsProvider;
    private final Provider<Logger> baseLoggerProvider;
    private final Provider<LudwigConfig> configProvider;
    private final Provider<StatusNavBarHelper> statusNavBarHelperProvider;
    private final Provider<AuthorizationSdkUrlsResolver> urlsResolverProvider;
    private final Provider<CoroutineDispatcher> viewModelDispatcherProvider;

    private C1625WebCaptchaComposeViewModel_Factory(Provider<CoroutineDispatcher> provider, Provider<Logger> provider2, Provider<LudwigConfig> provider3, Provider<StatusNavBarHelper> provider4, Provider<LudwigCaptchaAnalyticEvents> provider5, Provider<AuthorizationSdkUrlsResolver> provider6) {
        this.viewModelDispatcherProvider = provider;
        this.baseLoggerProvider = provider2;
        this.configProvider = provider3;
        this.statusNavBarHelperProvider = provider4;
        this.analyticsProvider = provider5;
        this.urlsResolverProvider = provider6;
    }

    public static C1625WebCaptchaComposeViewModel_Factory create(Provider<CoroutineDispatcher> provider, Provider<Logger> provider2, Provider<LudwigConfig> provider3, Provider<StatusNavBarHelper> provider4, Provider<LudwigCaptchaAnalyticEvents> provider5, Provider<AuthorizationSdkUrlsResolver> provider6) {
        return new C1625WebCaptchaComposeViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static WebCaptchaComposeViewModel newInstance(SavedStateHandle savedStateHandle, CoroutineDispatcher coroutineDispatcher, Logger logger, LudwigConfig ludwigConfig, StatusNavBarHelper statusNavBarHelper, LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents, AuthorizationSdkUrlsResolver authorizationSdkUrlsResolver) {
        return new WebCaptchaComposeViewModel(savedStateHandle, coroutineDispatcher, logger, ludwigConfig, statusNavBarHelper, ludwigCaptchaAnalyticEvents, authorizationSdkUrlsResolver);
    }

    public WebCaptchaComposeViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(savedStateHandle, this.viewModelDispatcherProvider.get(), this.baseLoggerProvider.get(), this.configProvider.get(), this.statusNavBarHelperProvider.get(), this.analyticsProvider.get(), this.urlsResolverProvider.get());
    }
}
