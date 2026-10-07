package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H\u0016¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCapchaViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "imitationHost", "", "analyticsCallback", "Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "<init>", "(Ljava/lang/String;Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;)V", "getImitationHost", "()Ljava/lang/String;", "getAnalyticsCallback", "()Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "create", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebCapchaViewModelFactory implements ViewModelProvider.Factory {

    @Nullable
    private final LudwigAnalyticsCallback analyticsCallback;

    @NotNull
    private final String imitationHost;

    public WebCapchaViewModelFactory(@NotNull String imitationHost, @Nullable LudwigAnalyticsCallback ludwigAnalyticsCallback) {
        Intrinsics.checkNotNullParameter(imitationHost, "imitationHost");
        this.imitationHost = imitationHost;
        this.analyticsCallback = ludwigAnalyticsCallback;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public /* bridge */ <T extends ViewModel> T create(@NotNull Class<T> cls, @NotNull CreationExtras creationExtras) {
        return (T) super.create(cls, creationExtras);
    }

    @Nullable
    public final LudwigAnalyticsCallback getAnalyticsCallback() {
        return this.analyticsCallback;
    }

    @NotNull
    public final String getImitationHost() {
        return this.imitationHost;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public /* bridge */ <T extends ViewModel> T create(@NotNull KClass<T> kClass, @NotNull CreationExtras creationExtras) {
        return (T) super.create(kClass, creationExtras);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        return new WebCaptchaViewModel(this.imitationHost, this.analyticsCallback);
    }
}
