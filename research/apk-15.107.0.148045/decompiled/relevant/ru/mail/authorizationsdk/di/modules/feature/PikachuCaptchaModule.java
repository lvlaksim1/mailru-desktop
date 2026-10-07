package ru.mail.authorizationsdk.di.modules.feature;

import androidx.compose.runtime.internal.StabilityInferred;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaApi;
import ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaRepository;
import ru.mail.authorizationsdk.di.ClientAppOkHttpClient;
import ru.mail.authorizationsdk.di.modules.AuthPlatformParamsInterceptor;
import ru.mail.authorizationsdk.domain.usecase.pikachu.PikachuUseCase;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.network.utils.client.OkHttpClientFactory;
import ru.mail.network.utils.client.interceptor.platform.PlatformParamsInterceptor;
import ru.mail.network.utils.sign.PostParamsSignatureInterceptor;
import ru.mail.util.log.InternalLogger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u001a\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0012\u0010\u0018\u001a\u00020\u00122\b\b\u0001\u0010\u0019\u001a\u00020\u0014H\u0007¨\u0006\u001a"}, d2 = {"Lru/mail/authorizationsdk/di/modules/feature/PikachuCaptchaModule;", "", "<init>", "()V", "provideMailOkHttpClient", "Lokhttp3/OkHttpClient;", "logger", "Lru/mail/util/log/InternalLogger;", "baseOkHttpClient", "logInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "platformParamsInterceptor", "Lru/mail/network/utils/client/interceptor/platform/PlatformParamsInterceptor;", "providePikachuUseCase", "Lru/mail/authorizationsdk/domain/usecase/pikachu/PikachuUseCase;", "resources", "Lru/mail/android_utils/wrapper/Resources;", "pikachuCaptchaApi", "Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaApi;", "providePikachuCaptchaRetrofit", "Lretrofit2/Retrofit;", "okHttpClient", "urlsResolver", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "providePikachuCaptchaApi", "retrofit", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
public final class PikachuCaptchaModule {
    public static final int $stable = 0;

    @Provides
    @PikachuCaptchaClient
    @NotNull
    @Singleton
    public final OkHttpClient provideMailOkHttpClient(@NotNull InternalLogger logger, @ClientAppOkHttpClient @NotNull OkHttpClient baseOkHttpClient, @NotNull HttpLoggingInterceptor logInterceptor, @AuthPlatformParamsInterceptor @NotNull PlatformParamsInterceptor platformParamsInterceptor) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(baseOkHttpClient, "baseOkHttpClient");
        Intrinsics.checkNotNullParameter(logInterceptor, "logInterceptor");
        Intrinsics.checkNotNullParameter(platformParamsInterceptor, "platformParamsInterceptor");
        return OkHttpClientFactory.createNewClient$default(new OkHttpClientFactory(logger), baseOkHttpClient, CollectionsKt.listOf((Object[]) new Interceptor[]{logInterceptor, platformParamsInterceptor, new PostParamsSignatureInterceptor()}), CollectionsKt.emptyList(), false, 8, null);
    }

    @Provides
    @NotNull
    public final PikachuCaptchaApi providePikachuCaptchaApi(@PikachuCaptchaRetrofit @NotNull Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(PikachuCaptchaApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (PikachuCaptchaApi) objCreate;
    }

    @Provides
    @PikachuCaptchaRetrofit
    @NotNull
    public final Retrofit providePikachuCaptchaRetrofit(@PikachuCaptchaClient @NotNull OkHttpClient okHttpClient, @NotNull AuthorizationSdkUrlsResolver urlsResolver) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(urlsResolver, "urlsResolver");
        Retrofit retrofitBuild = new Retrofit.Builder().client(okHttpClient).baseUrl(urlsResolver.getDoregCaptchaUrl()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    @NotNull
    public final PikachuUseCase providePikachuUseCase(@NotNull Resources resources, @NotNull PikachuCaptchaApi pikachuCaptchaApi) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(pikachuCaptchaApi, "pikachuCaptchaApi");
        return new PikachuUseCase(new PikachuCaptchaRepository(resources, pikachuCaptchaApi));
    }
}
