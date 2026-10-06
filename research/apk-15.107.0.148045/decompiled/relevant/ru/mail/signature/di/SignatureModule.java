package ru.mail.signature.di;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.config.Configuration;
import ru.mail.deviceinfo.AppVersionProvider;
import ru.mail.hitman.HitmanLib;
import ru.mail.kit.auth.AuthManager;
import ru.mail.locator.Locator;
import ru.mail.mails.R;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.sdk.MailSdkModule;
import ru.mail.serverapi.AccountManagerSettings;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.serverapi.PlatformInfo;
import ru.mail.serverapi.retrofit.AppBuildParamsInterceptor;
import ru.mail.serverapi.retrofit.PlatformParamsInterceptor;
import ru.mail.serverapi.retrofit.PostParamsSignatureInterceptor;
import ru.mail.serverapi.retrofit.RetrofitConfig;
import ru.mail.serverapi.retrofit.TornadoStatusInterceptor;
import ru.mail.serverapi.retrofit.TornadoStatusMetricTagInterceptor;
import ru.mail.serverapi.retrofit.session.SessionRecoveryInterceptor;
import ru.mail.serverapi.retrofit.session.TokenProvider;
import ru.mail.serverapi.retrofit.session.TokenRepository;
import ru.mail.serverapi.retrofit.session.TornadoSessionCreator;
import ru.mail.serverapi.retrofit.session.TornadoSessionInterceptor;
import ru.mail.signature.api.UserInfoApi;
import ru.mail.signature.repository.UserInfoRepository;
import ru.mail.signature.repository.impl.UserInfoRepositoryImpl;
import ru.mail.signature.usecase.GetSignatureUseCase;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;
import ru.mail.vkteams.gost.MailSdkOkHttpManager;
import ru.mail.vkteams.gost.MailsGostQualifier;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JV\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0012\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u001a\u001a\u00020\u0005H\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0019H\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001cH\u0007¨\u0006#"}, d2 = {"Lru/mail/signature/di/SignatureModule;", "", "<init>", "()V", "provideBaseAuthRetrofit", "Lretrofit2/Retrofit;", "context", "Landroid/content/Context;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "appLogger", "Lru/mail/util/log/Logger;", "tokenRepository", "Lru/mail/serverapi/retrofit/session/TokenRepository;", "configuration", "Lru/mail/config/Configuration;", "mailSdkOkHttpManager", "Lru/mail/vkteams/gost/MailSdkOkHttpManager;", "appVersionProvider", "Lru/mail/deviceinfo/AppVersionProvider;", "provideUserInfoApi", "Lru/mail/signature/api/UserInfoApi;", "retrofit", "provideUserInfoRepository", "Lru/mail/signature/repository/UserInfoRepository;", "authManager", "Lru/mail/kit/auth/AuthManager;", "userInfoApi", "provideGetSignatureUseCase", "Lru/mail/signature/usecase/GetSignatureUseCase;", "userInfoRepository", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
public final class SignatureModule {
    public static final int $stable = 0;

    @Provides
    @SignatureRetrofit
    @NotNull
    public final Retrofit provideBaseAuthRetrofit(@ApplicationContext @NotNull Context context, @NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull TokenRepository tokenRepository, @NotNull Configuration configuration, @MailsGostQualifier @NotNull MailSdkOkHttpManager mailSdkOkHttpManager, @NotNull AppVersionProvider appVersionProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(tokenRepository, "tokenRepository");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(mailSdkOkHttpManager, "mailSdkOkHttpManager");
        Intrinsics.checkNotNullParameter(appVersionProvider, "appVersionProvider");
        Locator locatorFrom = Locator.INSTANCE.from(context);
        TornadoSessionCreator tornadoSessionCreator = new TornadoSessionCreator(new TokenProvider(accountManager, accountManagerSettings));
        MailHostProvider mailHostProvider = new MailHostProvider(context, "new_mail_api", R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host, platformInfo);
        Logger loggerCreateNetworkLogger = MailSdkModule.createNetworkLogger(appLogger);
        RetrofitConfig retrofitConfig = new RetrofitConfig(configuration.getRetrofitConfig().getRethrowRequestSessionException());
        Gson gsonCreate = new GsonBuilder().create();
        OkHttpClient.Builder builderNewBuilder = ((NetworkServiceFactory) locatorFrom.locate(NetworkServiceFactory.class)).getOkHttpClient().newBuilder();
        String string = mailHostProvider.getUrlBuilder().build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(mailSdkOkHttpManager.configureOkHttpClient(builderNewBuilder, string).addNetworkInterceptor(new TornadoStatusMetricTagInterceptor(loggerCreateNetworkLogger)).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new SessionRecoveryInterceptor(accountManager, accountManagerSettings, tokenRepository)).addInterceptor(new TornadoSessionInterceptor(loggerCreateNetworkLogger, tornadoSessionCreator, retrofitConfig)).addInterceptor(new TornadoStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(new AppBuildParamsInterceptor(appVersionProvider)).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(MailSdkModule.createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(GsonConverterFactory.create(gsonCreate)).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    @Provides
    @NotNull
    public final GetSignatureUseCase provideGetSignatureUseCase(@NotNull UserInfoRepository userInfoRepository) {
        Intrinsics.checkNotNullParameter(userInfoRepository, "userInfoRepository");
        return new GetSignatureUseCase(userInfoRepository);
    }

    @Provides
    @NotNull
    public final UserInfoApi provideUserInfoApi(@SignatureRetrofit @NotNull Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(UserInfoApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (UserInfoApi) objCreate;
    }

    @Provides
    @NotNull
    public final UserInfoRepository provideUserInfoRepository(@NotNull AuthManager authManager, @NotNull UserInfoApi userInfoApi) {
        Intrinsics.checkNotNullParameter(authManager, "authManager");
        Intrinsics.checkNotNullParameter(userInfoApi, "userInfoApi");
        return new UserInfoRepositoryImpl(authManager, userInfoApi);
    }
}
