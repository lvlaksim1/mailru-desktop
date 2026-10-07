package ru.mail.authorizationsdk.feature.captcha;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@DaggerGenerated
public final class WebCaptchaComposeViewModel_Factory_Impl implements WebCaptchaComposeViewModel.Factory {
    private final C1625WebCaptchaComposeViewModel_Factory delegateFactory;

    WebCaptchaComposeViewModel_Factory_Impl(C1625WebCaptchaComposeViewModel_Factory c1625WebCaptchaComposeViewModel_Factory) {
        this.delegateFactory = c1625WebCaptchaComposeViewModel_Factory;
    }

    public static Provider<WebCaptchaComposeViewModel.Factory> createFactoryProvider(C1625WebCaptchaComposeViewModel_Factory c1625WebCaptchaComposeViewModel_Factory) {
        return InstanceFactory.create(new WebCaptchaComposeViewModel_Factory_Impl(c1625WebCaptchaComposeViewModel_Factory));
    }

    @Override // ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory
    public WebCaptchaComposeViewModel create(SavedStateHandle savedStateHandle) {
        return this.delegateFactory.get(savedStateHandle);
    }

    public static javax.inject.Provider<WebCaptchaComposeViewModel.Factory> create(C1625WebCaptchaComposeViewModel_Factory c1625WebCaptchaComposeViewModel_Factory) {
        return InstanceFactory.create(new WebCaptchaComposeViewModel_Factory_Impl(c1625WebCaptchaComposeViewModel_Factory));
    }
}
