package ru.mail.dependencies.vpn;

import android.content.Context;
import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import ru.mail.analytics.VpnInfoProvider;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.authorizationsdk.data.serialization.FactoryKt;
import ru.mail.config.ConfigRetriever;
import ru.mail.config.Configuration;
import ru.mail.deviceinfo.AppVersionProvider;
import ru.mail.hitman.HitmanLib;
import ru.mail.locator.Locator;
import ru.mail.mails.R;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.network.retrofit.ApiResultCallAdapterFactory;
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
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;
import ru.mail.vkteams.gost.MailSdkOkHttpManager;
import ru.mail.vkteams.gost.MailsGostQualifier;
import ru.mail.vpn.detect.api.VpnBlockingAnalytics;
import ru.mail.vpn.detect.api.VpnBlockingConfig;
import ru.mail.vpn.detect.api.VpnCheckRetrofit;
import ru.mail.vpn.detect.impl.MailApiVpnBlockingInterceptor;
import ru.mail.vpn.detect.impl.NetworkServiceVpnBlockingInterceptor;
import ru.mail.vpn.detect.impl.VpnBlockingAnalyticsImpl;
import ru.mail.vpn.detect.impl.VpnBlockingChecker;
import ru.mail.vpn.detect.impl.VpnBlockingInterceptor;
import ru.mail.vpn.detect.impl.VpnCheckApi;
import ru.mail.vpn.detect.impl.VpnInfoProviderImpl;
import ru.ok.android.commons.http.Http;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \t2\u00020\u0001:\u0001\tJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\bH'¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/dependencies/vpn/VpnBlockingModule;", "", "provideVpnBlockingAnalytics", "Lru/mail/vpn/detect/api/VpnBlockingAnalytics;", "impl", "Lru/mail/vpn/detect/impl/VpnBlockingAnalyticsImpl;", "provideVpnInfoProvider", "Lru/mail/analytics/VpnInfoProvider;", "Lru/mail/vpn/detect/impl/VpnInfoProviderImpl;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
public interface VpnBlockingModule {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007JV\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0001\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u001e\u001a\u00020\u001f2\b\b\u0001\u0010 \u001a\u00020\tH\u0007J\u001e\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0007J\u001e\u0010(\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010&\u001a\u00020'H\u0007¨\u0006)"}, d2 = {"Lru/mail/dependencies/vpn/VpnBlockingModule$Companion;", "", "<init>", "()V", "provideVpnBlockingConfig", "Lru/mail/vpn/detect/api/VpnBlockingConfig;", "configRetriever", "Lru/mail/config/ConfigRetriever;", "provideVpnCheckRetrofit", "Lretrofit2/Retrofit;", "context", "Landroid/content/Context;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "appLogger", "Lru/mail/util/log/Logger;", "tokenRepository", "Lru/mail/serverapi/retrofit/session/TokenRepository;", "configuration", "Lru/mail/config/Configuration;", "mailSdkOkHttpManager", "Lru/mail/vkteams/gost/MailSdkOkHttpManager;", "appVersionProvider", "Lru/mail/deviceinfo/AppVersionProvider;", "createConfig", "Lru/mail/serverapi/retrofit/RetrofitConfig;", "provideVpnCheckApi", "Lru/mail/vpn/detect/impl/VpnCheckApi;", "retrofit", "provideMailApiVpnBlockingInterceptor", "Lru/mail/vpn/detect/impl/VpnBlockingInterceptor;", "checker", "Ldagger/Lazy;", "Lru/mail/vpn/detect/impl/VpnBlockingChecker;", "analytics", "Lru/mail/vpn/detect/api/VpnBlockingAnalytics;", "provideSmartStatusVpnBlockingInterceptor", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        private final RetrofitConfig createConfig(Configuration configuration) {
            return new RetrofitConfig(configuration.getRetrofitConfig().getRethrowRequestSessionException());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit provideVpnCheckRetrofit$lambda$0(JsonBuilder Json) {
            Intrinsics.checkNotNullParameter(Json, "$this$Json");
            Json.setIgnoreUnknownKeys(true);
            Json.setCoerceInputValues(true);
            Json.setLenient(true);
            return Unit.INSTANCE;
        }

        @Provides
        @MailApiVpnBlockingInterceptor
        @Reusable
        @NotNull
        public final VpnBlockingInterceptor provideMailApiVpnBlockingInterceptor(@NotNull Lazy<VpnBlockingChecker> checker, @NotNull VpnBlockingAnalytics analytics) {
            Intrinsics.checkNotNullParameter(checker, "checker");
            Intrinsics.checkNotNullParameter(analytics, "analytics");
            return new VpnBlockingInterceptor(checker, analytics, VpnBlockingInterceptor.BlockType.MailApi);
        }

        @Provides
        @Reusable
        @NotNull
        @NetworkServiceVpnBlockingInterceptor
        public final VpnBlockingInterceptor provideSmartStatusVpnBlockingInterceptor(@NotNull Lazy<VpnBlockingChecker> checker, @NotNull VpnBlockingAnalytics analytics) {
            Intrinsics.checkNotNullParameter(checker, "checker");
            Intrinsics.checkNotNullParameter(analytics, "analytics");
            return new VpnBlockingInterceptor(checker, analytics, VpnBlockingInterceptor.BlockType.NetworkService);
        }

        @Provides
        @Reusable
        @NotNull
        public final VpnBlockingConfig provideVpnBlockingConfig(@NotNull ConfigRetriever configRetriever) {
            Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
            return (VpnBlockingConfig) configRetriever.getSerializable("vpn_blocking", Reflection.getOrCreateKotlinClass(VpnBlockingConfig.class));
        }

        @Provides
        @Singleton
        @NotNull
        public final VpnCheckApi provideVpnCheckApi(@NotNull @VpnCheckRetrofit Retrofit retrofit) {
            Intrinsics.checkNotNullParameter(retrofit, "retrofit");
            Object objCreate = retrofit.create(VpnCheckApi.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
            return (VpnCheckApi) objCreate;
        }

        @Provides
        @NotNull
        @VpnCheckRetrofit
        @Singleton
        public final Retrofit provideVpnCheckRetrofit(@ApplicationContext @NotNull Context context, @NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull TokenRepository tokenRepository, @NotNull Configuration configuration, @MailsGostQualifier @NotNull MailSdkOkHttpManager mailSdkOkHttpManager, @NotNull AppVersionProvider appVersionProvider) {
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
            RetrofitConfig retrofitConfigCreateConfig = createConfig(configuration);
            OkHttpClient.Builder builderNewBuilder = ((NetworkServiceFactory) locatorFrom.locate(NetworkServiceFactory.class)).getOkHttpClient().newBuilder();
            String string = mailHostProvider.getUrlBuilder().build().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(mailSdkOkHttpManager.configureOkHttpClient(builderNewBuilder, string).addNetworkInterceptor(new TornadoStatusMetricTagInterceptor(loggerCreateNetworkLogger)).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new SessionRecoveryInterceptor(accountManager, accountManagerSettings, tokenRepository)).addInterceptor(new TornadoSessionInterceptor(loggerCreateNetworkLogger, tornadoSessionCreator, retrofitConfigCreateConfig)).addInterceptor(new TornadoStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(new AppBuildParamsInterceptor(appVersionProvider)).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(MailSdkModule.createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(FactoryKt.create(JsonKt.Json$default(null, new Function1() { // from class: ru.mail.dependencies.vpn.a
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VpnBlockingModule.Companion.provideVpnCheckRetrofit$lambda$0((JsonBuilder) obj);
                }
            }, 1, null), MediaType.INSTANCE.get(Http.ContentType.APPLICATION_JSON))).addCallAdapterFactory(new ApiResultCallAdapterFactory()).build();
            Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
            return retrofitBuild;
        }
    }

    @Reusable
    @Binds
    @NotNull
    VpnBlockingAnalytics provideVpnBlockingAnalytics(@NotNull VpnBlockingAnalyticsImpl impl);

    @Reusable
    @Binds
    @NotNull
    VpnInfoProvider provideVpnInfoProvider(@NotNull VpnInfoProviderImpl impl);
}
