package ru.mail.dependencies;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.mail.gamification.data.network.api.GamificationApi;
import ru.mail.hitman.HitmanLib;
import ru.mail.locator.Locator;
import ru.mail.logic.cmd.migrantreg.api.TokenMergeApi;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;
import ru.mail.logic.cmd.socialbind.SocialBindAddApi;
import ru.mail.logic.cmd.socialbind.VerificationPasswordCheckApi;
import ru.mail.logic.cmd.socialbind.VkPreflightApi;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.network.qualifier.MailApiRetrofit;
import ru.mail.network.retrofit.ApiResultCallAdapterFactory;
import ru.mail.sdk.MailSdkModule;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.serverapi.PlatformInfo;
import ru.mail.serverapi.retrofit.PlatformParamsInterceptor;
import ru.mail.serverapi.retrofit.PostParamsSignatureInterceptor;
import ru.mail.serverapi.retrofit.SwaStatusInterceptor;
import ru.mail.serverapi.retrofit.TornadoStatusInterceptor;
import ru.mail.serverapi.retrofit.TornadoStatusMetricTagInterceptor;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J$\u0010\u0012\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0007J,\u0010\u0019\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J,\u0010\u001c\u001a\u00020\u00072\b\b\u0001\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0007¨\u0006\u001d"}, d2 = {"Lru/mail/dependencies/MailApiModule;", "", "<init>", "()V", "provideTokenMergeApi", "Lru/mail/logic/cmd/migrantreg/api/TokenMergeApi;", "retrofit", "Lretrofit2/Retrofit;", "provideGamificationApi", "Lru/mail/gamification/data/network/api/GamificationApi;", "provideLudwigTokensApi", "Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "provideVerificationPasswordCheckApi", "Lru/mail/logic/cmd/socialbind/VerificationPasswordCheckApi;", "provideVkPreflightApi", "Lru/mail/logic/cmd/socialbind/VkPreflightApi;", "provideSocialBindAddApi", "Lru/mail/logic/cmd/socialbind/SocialBindAddApi;", "provideAccountApiRetrofit", "context", "Landroid/content/Context;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "appLogger", "Lru/mail/util/log/Logger;", "provideAccessApiRetrofit", "networkServiceFactory", "Lru/mail/network/NetworkServiceFactory;", "provideAuthApiRetrofit", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
public final class MailApiModule {
    public static final int $stable = 0;

    @NotNull
    public static final MailApiModule INSTANCE = new MailApiModule();

    private MailApiModule() {
    }

    @Provides
    @NotNull
    @AccessApiRetrofit
    @Singleton
    public final Retrofit provideAccessApiRetrofit(@ApplicationContext @NotNull Context context, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull NetworkServiceFactory networkServiceFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(networkServiceFactory, "networkServiceFactory");
        MailHostProvider mailHostProvider = new MailHostProvider(context, "access_api", ru.mail.mailapp.R.string.access_default_scheme, ru.mail.mailapp.R.string.access_default_host, platformInfo);
        Logger loggerCreateNetworkLogger = MailSdkModule.createNetworkLogger(appLogger);
        Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(networkServiceFactory.getOkHttpClient().newBuilder().addNetworkInterceptor(new TornadoStatusMetricTagInterceptor(loggerCreateNetworkLogger)).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new TornadoStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(MailSdkModule.createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build())).addCallAdapterFactory(new ApiResultCallAdapterFactory()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    @AccountApiRetrofit
    @NotNull
    @Singleton
    public final Retrofit provideAccountApiRetrofit(@ApplicationContext @NotNull Context context, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Locator locatorFrom = Locator.INSTANCE.from(context);
        MailHostProvider mailHostProvider = new MailHostProvider(context, "account_api", ru.mail.mailapp.R.string.account_default_scheme, ru.mail.mailapp.R.string.account_default_host, platformInfo);
        Logger loggerCreateNetworkLogger = MailSdkModule.createNetworkLogger(appLogger);
        Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(((NetworkServiceFactory) locatorFrom.locate(NetworkServiceFactory.class)).getOkHttpClient().newBuilder().addNetworkInterceptor(new TornadoStatusMetricTagInterceptor(loggerCreateNetworkLogger)).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new TornadoStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(MailSdkModule.createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build())).addCallAdapterFactory(new ApiResultCallAdapterFactory()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    @AuthApiRetrofit
    @NotNull
    @Singleton
    public final Retrofit provideAuthApiRetrofit(@ApplicationContext @NotNull Context context, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull NetworkServiceFactory networkServiceFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(networkServiceFactory, "networkServiceFactory");
        MailHostProvider mailHostProvider = new MailHostProvider(context, "auth_api", ru.mail.mailapp.R.string.swa_default_scheme, ru.mail.mailapp.R.string.swa_default_host, platformInfo);
        Logger loggerCreateNetworkLogger = MailSdkModule.createNetworkLogger(appLogger);
        Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(networkServiceFactory.getOkHttpClient().newBuilder().addNetworkInterceptor(new SwaStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(MailSdkModule.createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build())).addCallAdapterFactory(new ApiResultCallAdapterFactory()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    @Singleton
    @NotNull
    public final GamificationApi provideGamificationApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(GamificationApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (GamificationApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final LudwigTokensApi provideLudwigTokensApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(LudwigTokensApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (LudwigTokensApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final SocialBindAddApi provideSocialBindAddApi(@AccountApiRetrofit @NotNull Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(SocialBindAddApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (SocialBindAddApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final TokenMergeApi provideTokenMergeApi(@AccountApiRetrofit @NotNull Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(TokenMergeApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (TokenMergeApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final VerificationPasswordCheckApi provideVerificationPasswordCheckApi(@NotNull @AccessApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(VerificationPasswordCheckApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (VerificationPasswordCheckApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final VkPreflightApi provideVkPreflightApi(@AuthApiRetrofit @NotNull Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(VkPreflightApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (VkPreflightApi) objCreate;
    }
}
