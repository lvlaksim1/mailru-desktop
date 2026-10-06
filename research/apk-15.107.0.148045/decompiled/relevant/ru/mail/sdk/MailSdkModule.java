package ru.mail.sdk;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.util.Supplier;
import androidx.preference.PreferenceManager;
import com.jakewharton.retrofit2.converter.kotlinx.serialization.KotlinSerializationConverterFactory;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoSet;
import java.io.File;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.mail.HostsParser;
import ru.mail.HostsParserOnPremiseImpl;
import ru.mail.HostsParserSdkImpl;
import ru.mail.SdkHosts;
import ru.mail.ads.config.api.data.model.AdConfiguration;
import ru.mail.ads.core.api.domain.usecase.CheckSubscriptionSupportUseCase;
import ru.mail.analytics.AnalyticTracker;
import ru.mail.analytics.DataStoreAnalyticsImpl;
import ru.mail.analytics.EncryptedPreferencesAnalyticsImpl;
import ru.mail.analytics.EventLogger;
import ru.mail.analytics.EventLoggerWrapper;
import ru.mail.analytics.ImapPromoAnalytics;
import ru.mail.analytics.MailAnalyticInitializer;
import ru.mail.analytics.MailAnalytics;
import ru.mail.analytics.MailAnalyticsKt;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.OverQuotaAnalytics;
import ru.mail.analytics.WebViewErrorAnalytics;
import ru.mail.analytics.WebViewErrorAnalyticsImpl;
import ru.mail.analytics.gotoaction.GoToActionAnalyticTracker;
import ru.mail.analytics.gotoaction.GoToActionAnalyticTrackerImpl;
import ru.mail.analytics.monitor.SessionMonitor;
import ru.mail.analytics.timer.TimeTracker;
import ru.mail.android_utils.SdkUtils;
import ru.mail.android_utils.connection.BandwidthConstants;
import ru.mail.api.MailDeeplinkCreator;
import ru.mail.appmetricstracker.api.AppMetricsTracker;
import ru.mail.appmetricstracker.monitors.AppMonitor;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.arbiter.RequestArbiterConfigHelper;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.attachments.lock.AttachSizeLockLogger;
import ru.mail.attachments.lock.CheckAttachSizeExceededUseCase;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.util.CurrentAccountUtils;
import ru.mail.auth.webview.VKConnectSignInDelegate;
import ru.mail.clipboard.domain.config.ClipboardConfiguration;
import ru.mail.clipboard.domain.config.CreateMailByClipboardConfig;
import ru.mail.clipboard.domain.interactor.CreateMailByTextInteractor;
import ru.mail.clipboard.presentation.executor.ActionExecutor;
import ru.mail.clipboard.presentation.executor.CreateMailActionExecutor;
import ru.mail.clipboard.presentation.plate.configuration.FromMenuPlusConfiguration;
import ru.mail.clipboard.presentation.plate.stateResolver.CreateMailPlateStateResolver;
import ru.mail.clipboard.presentation.plate.viewModel.stateResolver.ClipboardPlateResolver;
import ru.mail.clipboard.presentation.snackbar.configuration.SnackbarOnEmailsConfiguration;
import ru.mail.config.ConfigRetriever;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.config.HelpersStorage;
import ru.mail.config.HelpersStorageImpl;
import ru.mail.config.OkHttpConfig;
import ru.mail.csat.SurveyAnalytics;
import ru.mail.csat.SurveyInteractorImpl;
import ru.mail.csat.SurveyRepository;
import ru.mail.csat.di.CsatLogger;
import ru.mail.csat.listeners.AnswerListener;
import ru.mail.csat.listeners.AnswerListenerProvider;
import ru.mail.csat.listeners.OnResultListenerProvider;
import ru.mail.csat.listeners.OnResultsListenerProviderImpl;
import ru.mail.csat.survey.CsatCondition;
import ru.mail.csat.survey.CsatConfig;
import ru.mail.csat.survey.CsatTrigger;
import ru.mail.csat.survey.ShowPlace;
import ru.mail.csat.survey.SurveyConfig;
import ru.mail.csat.survey.SurveyInteractor;
import ru.mail.csat.survey.SurveyIntroConfig;
import ru.mail.data.api.FolderApi;
import ru.mail.data.api.SimpleApiResultHandler;
import ru.mail.data.cloud.MailCloudInfoRepositoryImpl;
import ru.mail.data.cmd.server.AvatarHostInitializer;
import ru.mail.data.cmd.server.AvatarHostInitializerImpl;
import ru.mail.data.cmd.server.DomainResolver;
import ru.mail.data.cmd.server.DomainResolverImpl;
import ru.mail.data.cmd.server.MainLinkKeyProvider;
import ru.mail.data.cmd.server.MainLinkKeyProviderOnPremiseImpl;
import ru.mail.data.cmd.server.MainLinkKeyProviderStubImpl;
import ru.mail.data.cmd.server.RequestListenerManager;
import ru.mail.data.cmd.server.helper.api.HelperApi;
import ru.mail.data.cmd.server.summarize.SummarizeChunkInterceptor;
import ru.mail.data.dao.ResourceObservable;
import ru.mail.deeplink.DeeplinkInteractor;
import ru.mail.dependencies.ApplicationScope;
import ru.mail.dependencies.DefaultLoadBucketSize;
import ru.mail.dependencies.DefaultOnePageLimit;
import ru.mail.dependencies.DisableDownloadAttachInterfaceProviderKt;
import ru.mail.dependencies.LoadMoreToLimit;
import ru.mail.dependencies.MailAppID;
import ru.mail.dependencies.mainpage.DivUrlHandlerImpl;
import ru.mail.dependencies.mainpage.MainPageAnalyticsImpl;
import ru.mail.deviceinfo.AppVersionProvider;
import ru.mail.deviceinfo.DefaultDeviceIdProvider;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.download.facade.models.OfflineAttachesConfig;
import ru.mail.feature.appmetricstracker.AppMonitorFactory;
import ru.mail.filter.data.FiltersApi;
import ru.mail.glasha.data.network.api.SharedFolderApi;
import ru.mail.glasha.domain.managers.FolderGrantsManager;
import ru.mail.glasha.domain.usecases.AccountProvider;
import ru.mail.glasha.domain.usecases.SyncUseCase;
import ru.mail.hitman.HitmanLib;
import ru.mail.imageloader.ImageLoaderRepository;
import ru.mail.imageloader.NameProvider;
import ru.mail.interactor.AccessCoroutineExecutor;
import ru.mail.interactor.AccessCoroutineExecutorImpl;
import ru.mail.kit.analytics.Analytics;
import ru.mail.kit.routing.Router;
import ru.mail.locator.Locator;
import ru.mail.logic.betastate.BetaStateKeeper;
import ru.mail.logic.cmd.sync.xmailmigration.MigrantApi;
import ru.mail.logic.content.AuthTokenProvider;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.DefaultAuthTokenProvider;
import ru.mail.logic.content.ExtendedDataManager;
import ru.mail.logic.content.FoldersManager;
import ru.mail.logic.content.MailboxContextProvider;
import ru.mail.logic.content.PermissionAccess;
import ru.mail.logic.content.PermissionAccessHistoryImpl;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.content.impl.FoldersRepository;
import ru.mail.logic.content.impl.ShortcutUpdater;
import ru.mail.logic.design.DesignManager;
import ru.mail.logic.gotoaction.GoToActionInMailsListHandler;
import ru.mail.logic.gotoaction.GoToActionInMailsListHandlerImpl;
import ru.mail.logic.navigation.LinkNavigator;
import ru.mail.logic.navigation.Navigator;
import ru.mail.logic.navigation.WebViewWorkaroundManager;
import ru.mail.logic.processors.auth.NoAuthHandler;
import ru.mail.logic.sharedfolder.SyncUseCaseImpl;
import ru.mail.logic.shrink.service.ShrinkManager;
import ru.mail.logic.shrink.service.ShrinkManagerSharedPrefStorage;
import ru.mail.logic.shrink.service.ShrinkManagerStorage;
import ru.mail.logic.sync.AddressBookApi;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mails.R;
import ru.mail.mainpage.MainPageAnalytics;
import ru.mail.mainpage.api.MainPageApi;
import ru.mail.mainpage.data.MainPageApiResultHandler;
import ru.mail.mainpage.data.MainPageApiResultHandlerImpl;
import ru.mail.mainpage.data.MainPageDataStoreRepositoryImpl;
import ru.mail.mainpage.data.MainPageDirectoryProvider;
import ru.mail.mainpage.data.MainPageLocalSource;
import ru.mail.mainpage.data.MainPageRemoteSource;
import ru.mail.mainpage.data.MainPageRepositoryImpl;
import ru.mail.mainpage.di.MainPage;
import ru.mail.mainpage.domain.LoadMainPageUseCase;
import ru.mail.mainpage.domain.MainPageRepository;
import ru.mail.mainpage.ui.divkit.DivUrlHandler;
import ru.mail.mainpage.utils.LocationPermissionHelper;
import ru.mail.mainpage.utils.LocationPermissionHelperImpl;
import ru.mail.mainpage.utils.LocationProvider;
import ru.mail.march.concurrent.DefaultDispatcher;
import ru.mail.march.concurrent.IoDispatcher;
import ru.mail.march.internal.work.NetworkRequirementManager;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.network.HostProviderWrapper;
import ru.mail.network.HostProviderWrapperImpl;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.network.dns.DnsWithCacheClear;
import ru.mail.network.eventlistener.LoggingEventListener;
import ru.mail.network.interceptor.RetryInterceptor;
import ru.mail.network.networkchecker.NetworkErrorAnalyticsInterceptor;
import ru.mail.network.qualifier.KSerializationRetrofit;
import ru.mail.network.qualifier.MailApiRetrofit;
import ru.mail.network.retrofit.ApiResultCallAdapterFactory;
import ru.mail.network.utils.filter.LogFilters;
import ru.mail.offline.attaches.storage.api.MailOfflineAttachmentQuotaController;
import ru.mail.offline.attaches.storage.api.MailOfflineAttachmentShrinkerConfig;
import ru.mail.offline.attaches.storage.api.OfflineAttachmentsStorageDependenciesProvider;
import ru.mail.overquota.OverQuotaConfigDelegate;
import ru.mail.overquota.OverQuotaDataRepository;
import ru.mail.portal.PortalManager;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.portal.app.adapter.di.PortalAnalytic;
import ru.mail.portal.app.adapter.mainpage.MainPageDataStoreRepository;
import ru.mail.portal.features.EmptyMailFeature;
import ru.mail.portal.features.MailFeature;
import ru.mail.portal.kit.util.PortalAnalytics;
import ru.mail.router.MailDeeplinkCreatorImpl;
import ru.mail.sdk.core.presentation.attach.DisableDownloadAttachInterface;
import ru.mail.serverapi.AccountManagerSettings;
import ru.mail.serverapi.BrowserCookieSetter;
import ru.mail.serverapi.BrowserCookieSetterOld;
import ru.mail.serverapi.BrowserCookieSetterStub;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.serverapi.PlatformInfo;
import ru.mail.serverapi.retrofit.AppBuildParamsInterceptor;
import ru.mail.serverapi.retrofit.PlatformParamsInterceptor;
import ru.mail.serverapi.retrofit.PostParamsSignatureInterceptor;
import ru.mail.serverapi.retrofit.RetrofitConfig;
import ru.mail.serverapi.retrofit.TornadoStatusInterceptor;
import ru.mail.serverapi.retrofit.TornadoStatusMetricTagInterceptor;
import ru.mail.serverapi.retrofit.session.NoAuthInfoCreator;
import ru.mail.serverapi.retrofit.session.SessionRecoveryInterceptor;
import ru.mail.serverapi.retrofit.session.TokenProvider;
import ru.mail.serverapi.retrofit.session.TokenRepository;
import ru.mail.serverapi.retrofit.session.TornadoSessionCreator;
import ru.mail.serverapi.retrofit.session.TornadoSessionInterceptor;
import ru.mail.serverapi.retrofit.session.TornadoSessionSensitiveFilters;
import ru.mail.setup.MailDependenciesInitHelper;
import ru.mail.survey.MailSurveyAnalytics;
import ru.mail.survey.MailSurveyRepository;
import ru.mail.survey.SurveyAnswerTimeJsonParserImpl;
import ru.mail.survey.SurveyJsonParserImpl;
import ru.mail.survey.network.AnswerJsonBuilderImpl;
import ru.mail.survey.stars.QuestionParserImpl;
import ru.mail.survey.webview.UrlConstructor;
import ru.mail.survey.webview.UrlConstructorImpl;
import ru.mail.timespent.storage.TimeSpentStorage;
import ru.mail.ui.auth.universal.UserBoundByVKIDDelegate;
import ru.mail.ui.cloud.MailCloudInfoRepository;
import ru.mail.ui.cloud.overquota.LoadOverQuotaStateUseCase;
import ru.mail.ui.folder.chooser.viewmodel.FolderChooserInteractor;
import ru.mail.ui.folder.chooser.viewmodel.FolderChooserInteractorImpl;
import ru.mail.ui.fragments.mailbox.grouping.GroupingInteractor;
import ru.mail.ui.fragments.mailbox.grouping.GroupingInteractorImpl;
import ru.mail.ui.fragments.mailbox.mailview.interactor.share.ShareMailAnalytics;
import ru.mail.ui.fragments.mailbox.mailview.interactor.share.ShareMailAnalyticsImpl;
import ru.mail.ui.fragments.mailbox.newmail.bundlestorage.BundleStorageInteractor;
import ru.mail.ui.fragments.mailbox.newmail.bundlestorage.BundleStorageInteractorImpl;
import ru.mail.ui.fragments.mailbox.ozonfeed.di.OzonFeed;
import ru.mail.ui.fragments.settings.navigation.ClassProvider;
import ru.mail.ui.navigation.MailSdkHandleURIDelegate;
import ru.mail.ui.quickactions.folders.FoldersWatcherInteractor;
import ru.mail.ui.quickactions.folders.FoldersWatcherInteractorImpl;
import ru.mail.uikit.reporter.ErrorReporter;
import ru.mail.util.AnalyticProjectProvider;
import ru.mail.util.BuildVariantHelper;
import ru.mail.util.DirectoryRepository;
import ru.mail.util.LoggerImpl;
import ru.mail.util.NetworkServiceInterceptorsHolder;
import ru.mail.util.NetworkServiceInterceptorsHolderImpl;
import ru.mail.util.PerDomainOkHttpModifierImpl;
import ru.mail.util.SafeProxySelector;
import ru.mail.util.ShortcutUpdaterImpl;
import ru.mail.util.StringResolverImpl;
import ru.mail.util.analytics.counter.EventLoggerCounter;
import ru.mail.util.analytics.counter.SessionEventLoggerCounter;
import ru.mail.util.analytics.counter.SessionEventLoggerCounterImpl;
import ru.mail.util.analytics.logger.AdditionalAnalyticsParamsProvider;
import ru.mail.util.analytics.logger.EventLoggerImpl;
import ru.mail.util.connection.ConnectionClassManager;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.AsyncFileHandlerFactory;
import ru.mail.util.log.AsyncFileHandlerFactoryImpl;
import ru.mail.util.log.AuthSdkFilter;
import ru.mail.util.log.ConstraintsFactory;
import ru.mail.util.log.FileHandlerAnalyticsError;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Level;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogCollector;
import ru.mail.util.log.LogConstraints;
import ru.mail.util.log.LogFilter;
import ru.mail.util.log.LogRepository;
import ru.mail.util.log.LogRepositoryImpl;
import ru.mail.util.log.Logger;
import ru.mail.util.log.LoggerWrapper;
import ru.mail.util.log.TransformLogger;
import ru.mail.util.push.MailSdkIntentProvider;
import ru.mail.util.relocation.AccountRelocationManager;
import ru.mail.util.relocation.AccountRelocationManagerImpl;
import ru.mail.util.relocation.AccountRelocationMarkerImpl;
import ru.mail.util.relocation.AccountRelocationNoAccountsCaseKeyStorageImpl;
import ru.mail.util.relocation.ActualRelocationConfigRepository;
import ru.mail.util.relocation.ActualRelocationConfigRepositoryImpl;
import ru.mail.util.relocation.LicenseAgreementConfigRepository;
import ru.mail.util.relocation.LicenseAgreementConfigRepositoryImpl;
import ru.mail.util.reporter.AbstractErrorReporter;
import ru.mail.util.sound.SoundService;
import ru.mail.util.work.ConfigurationNetworkRequirementManager;
import ru.mail.utils.AppVersionCode;
import ru.mail.utils.ApplicationStartupTypeChecker;
import ru.mail.utils.BetaVersion;
import ru.mail.utils.GooglePlayServicesAvailabilityProvider;
import ru.mail.utils.GooglePlayServicesAvailabilityProviderImpl;
import ru.mail.utils.SafetyDependenciesProvider;
import ru.mail.utils.StartupTypeChecker;
import ru.mail.utils.StringResolver;
import ru.mail.utils.TimeProvider;
import ru.mail.utils.TimeUtils;
import ru.mail.utils.analytics.UrlParamsAnalyticsSender;
import ru.mail.utils.lifecycle.ActivityLifecycleHandler;
import ru.mail.utils.prefs.EncryptedSharedPreferencesProvider;
import ru.mail.utils.prefs.EncryptedSharedPreferencesProviderImpl;
import ru.mail.utils.prefs.analytics.DataStoreAnalytics;
import ru.mail.utils.prefs.analytics.EncryptedPreferencesAnalytics;
import ru.mail.vkteams.gost.MailSdkOkHttpManager;
import ru.mail.vkteams.gost.MailsGostQualifier;
import ru.mail.vpn.detect.impl.MailApiVpnBlockingInterceptor;
import ru.mail.vpn.detect.impl.NetworkServiceVpnBlockingInterceptor;
import ru.mail.vpn.detect.impl.VpnBlockingInterceptor;
import ru.mail.webcomponent.ssl.SSLCertificatesManager;
import ru.ok.android.commons.http.Http;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0098\t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001c\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J$\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0007J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\u0012\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001cH\u0007J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\"H\u0007J\u0010\u0010'\u001a\u00020%2\u0006\u0010&\u001a\u00020\"H\u0007J\b\u0010(\u001a\u00020\u001eH\u0007J\u0012\u0010)\u001a\u00020*2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010+\u001a\u00020 2\u0006\u0010,\u001a\u00020*H\u0007J\u0012\u0010-\u001a\u00020.2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0007J\u001c\u00103\u001a\u0002042\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u00105\u001a\u000206H\u0007J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0007J\u0014\u0010;\u001a\u0004\u0018\u00010<2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010=\u001a\u00020>2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010?\u001a\u00020@2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010A\u001a\u00020BH\u0007J0\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0007J\u001a\u0010O\u001a\u00020P2\b\b\u0001\u0010Q\u001a\u00020\r2\u0006\u0010R\u001a\u00020SH\u0007J\u001a\u0010T\u001a\u00020P2\b\b\u0001\u0010Q\u001a\u00020\r2\u0006\u0010R\u001a\u00020SH\u0007J\u001a\u0010U\u001a\u00020V2\b\b\u0001\u0010Q\u001a\u00020\r2\u0006\u0010R\u001a\u00020SH\u0007J\u0012\u0010W\u001a\u00020X2\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u001a\u0010[\u001a\u00020\\2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010]\u001a\u00020\u0014H\u0007J\u0012\u0010^\u001a\u00020_2\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u0010\u0010`\u001a\u00020\r2\u0006\u0010a\u001a\u00020bH\u0007J\b\u0010c\u001a\u00020bH\u0007J\u0018\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020iH\u0007J\u0012\u0010j\u001a\u00020k2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010l\u001a\u00020m2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010n\u001a\u00020o2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010p\u001a\u00020q2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010r\u001a\u00020s2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010t\u001a\u00020u2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010v\u001a\u0002022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0018\u0010w\u001a\u00020x2\u0006\u0010]\u001a\u00020\u00142\u0006\u0010y\u001a\u00020oH\u0007J\b\u0010z\u001a\u00020{H\u0007J\b\u0010|\u001a\u00020}H\u0007J\b\u0010~\u001a\u00020\u007fH\u0007J\u0014\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0007J\u0014\u0010\u0084\u0001\u001a\u00030\u0085\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010y\u001a\u00020oH\u0007J(\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u0002022\u0007\u0010\u008b\u0001\u001a\u00020J2\n\b\u0001\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0007J\n\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0007J\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u00012\n\b\u0001\u0010\u0092\u0001\u001a\u00030\u008d\u0001H\u0007J\u0013\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020JH\u0007J\u0014\u0010\u0096\u0001\u001a\u00030\u0097\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\n\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0007J\u001b\u0010\u009a\u0001\u001a\u00030\u009b\u00012\u0007\u0010\u008b\u0001\u001a\u00020J2\u0006\u00101\u001a\u000202H\u0007J\u001c\u0010\u009c\u0001\u001a\u00030\u009d\u00012\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\u0006\u00101\u001a\u000202H\u0007J\t\u0010 \u0001\u001a\u00020\rH\u0007J\u0014\u0010¡\u0001\u001a\u00030¢\u00012\b\u0010£\u0001\u001a\u00030¤\u0001H\u0007J\u0013\u0010¥\u0001\u001a\u00020F2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007Jn\u0010¦\u0001\u001a\u00020Z2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0007\u0010§\u0001\u001a\u00020\u00142\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\b\b\u0001\u0010Q\u001a\u00020\r2\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0006\u0010R\u001a\u00020S2\n\b\u0001\u0010®\u0001\u001a\u00030¯\u00012\b\u0010°\u0001\u001a\u00030±\u00012\n\b\u0001\u0010²\u0001\u001a\u00030³\u0001H\u0007Jn\u0010´\u0001\u001a\u00020Z2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0007\u0010§\u0001\u001a\u00020\u00142\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\b\b\u0001\u0010Q\u001a\u00020\r2\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0006\u0010R\u001a\u00020S2\n\b\u0001\u0010®\u0001\u001a\u00030¯\u00012\b\u0010°\u0001\u001a\u00030±\u00012\n\b\u0001\u0010²\u0001\u001a\u00030³\u0001H\u0007Jp\u0010µ\u0001\u001a\u00020Z2\u0006\u0010\u0006\u001a\u00020\u00072\u0007\u0010§\u0001\u001a\u00020\u00142\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010Q\u001a\u00020\r2\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0006\u0010R\u001a\u00020S2\b\u0010®\u0001\u001a\u00030¯\u00012\b\u0010°\u0001\u001a\u00030±\u00012\b\u0010²\u0001\u001a\u00030³\u00012\b\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u0012\u0010¸\u0001\u001a\u00030¹\u00012\u0006\u0010R\u001a\u00020SH\u0002J\u0012\u0010º\u0001\u001a\u00030»\u00012\u0006\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010¼\u0001\u001a\u00020\r2\u0007\u0010½\u0001\u001a\u00020\rH\u0007J\u0014\u0010¾\u0001\u001a\u00030¿\u00012\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J \u0010À\u0001\u001a\u00030Á\u00012\b\u0010Â\u0001\u001a\u00030Ã\u00012\n\b\u0001\u0010²\u0001\u001a\u00030³\u0001H\u0007J\u0014\u0010Ä\u0001\u001a\u00030Å\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010Æ\u0001\u001a\u00030Ç\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010È\u0001\u001a\u00030É\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\n\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0007J\u0014\u0010Ì\u0001\u001a\u00030Í\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0013\u0010Î\u0001\u001a\u00020L2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0013\u0010Ï\u0001\u001a\u00020N2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\n\u0010Ð\u0001\u001a\u00030Ñ\u0001H\u0007J1\u0010Ò\u0001\u001a\u00030Ó\u00012\b\u0010Ô\u0001\u001a\u00030Õ\u00012\b\u0010Ö\u0001\u001a\u00030×\u00012\b\u0010Ø\u0001\u001a\u00030Ù\u00012\u0007\u0010G\u001a\u00030Ú\u0001H\u0007J\u001e\u0010Û\u0001\u001a\u00030Õ\u00012\u0006\u0010y\u001a\u00020o2\n\b\u0001\u0010Ü\u0001\u001a\u00030\u0091\u0001H\u0007J\u0012\u0010Ý\u0001\u001a\u00030Ú\u00012\u0006\u0010G\u001a\u00020sH\u0007J\u0012\u0010Þ\u0001\u001a\u00030ß\u00012\u0006\u0010G\u001a\u00020sH\u0007J.\u0010à\u0001\u001a\u00030á\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010I\u001a\u00020J2\b\u0010â\u0001\u001a\u00030ã\u00012\u0006\u0010]\u001a\u00020\u0014H\u0007J\u001c\u0010ä\u0001\u001a\u00030å\u00012\u0006\u0010I\u001a\u00020J2\b\u0010â\u0001\u001a\u00030ã\u0001H\u0007J\u0013\u0010æ\u0001\u001a\u00020\r2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J\t\u0010ç\u0001\u001a\u00020PH\u0007J\t\u0010è\u0001\u001a\u00020VH\u0007J\u0014\u0010é\u0001\u001a\u00030ê\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0013\u0010ë\u0001\u001a\u00020H2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001d\u0010ì\u0001\u001a\u00030í\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0007\u0010G\u001a\u00030î\u0001H\u0007J\u0014\u0010ï\u0001\u001a\u00030î\u00012\b\u0010ð\u0001\u001a\u00030ñ\u0001H\u0007J\u0014\u0010ò\u0001\u001a\u00030ó\u00012\b\u0010ð\u0001\u001a\u00030ñ\u0001H\u0007J\u0011\u0010ô\u0001\u001a\u0002062\u0006\u0010G\u001a\u00020HH\u0007J\n\u0010õ\u0001\u001a\u00030ñ\u0001H\u0007J\u001c\u0010ö\u0001\u001a\u00030÷\u00012\u0006\u0010R\u001a\u00020S2\b\u0010ø\u0001\u001a\u00030ñ\u0001H\u0007J\u0013\u0010ù\u0001\u001a\u00020\u00142\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010ú\u0001\u001a\u00030©\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010û\u0001\u001a\u00030«\u00012\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001e\u0010ü\u0001\u001a\u00030ý\u00012\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010ª\u0001\u001a\u00030«\u0001H\u0007J\u0013\u0010þ\u0001\u001a\u00030ÿ\u00012\u0007\u0010\u0080\u0002\u001a\u00020\u0016H\u0007J\n\u0010\u0081\u0002\u001a\u00030Ã\u0001H\u0007J\u0014\u0010\u0082\u0002\u001a\u00030\u0083\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010\u0084\u0002\u001a\u00030\u0085\u00022\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0007J\u0014\u0010\u0086\u0002\u001a\u00030\u0087\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J8\u0010\u0088\u0002\u001a\u00030\u0089\u00022\b\u0010\u008a\u0002\u001a\u00030\u0085\u00022\b\b\u0001\u0010\f\u001a\u00020\r2\u000e\u0010\u008b\u0002\u001a\t\u0012\u0004\u0012\u00020s0\u008c\u00022\b\u0010\u008d\u0002\u001a\u00030\u008e\u0002H\u0007J\n\u0010\u008f\u0002\u001a\u00030¯\u0001H\u0007J\u0012\u0010\u0090\u0002\u001a\u00030ã\u00012\u0006\u0010I\u001a\u00020JH\u0007J\u0014\u0010\u0091\u0002\u001a\u00030\u0092\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0013\u0010\u0093\u0002\u001a\u00030\u0094\u00022\u0007\u0010\u0095\u0001\u001a\u00020JH\u0007J\u0013\u0010\u0095\u0002\u001a\u00030\u0096\u00022\u0007\u0010\u0095\u0001\u001a\u00020JH\u0007J\u001b\u0010\u0097\u0002\u001a\u00020g2\u0006\u0010h\u001a\u00020i2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J\u0012\u0010\u0098\u0002\u001a\u00020i2\u0007\u0010\u0095\u0001\u001a\u00020JH\u0007J\u0012\u0010\u0099\u0002\u001a\u00030\u009a\u00022\u0006\u0010y\u001a\u00020oH\u0007J\u0012\u0010\u009b\u0002\u001a\u00030\u009c\u00022\u0006\u0010y\u001a\u00020oH\u0007J\u0012\u0010\u009d\u0002\u001a\u00030\u009e\u00022\u0006\u0010y\u001a\u00020oH\u0007J\u0013\u0010\u009f\u0002\u001a\u00030 \u00022\u0007\u0010h\u001a\u00030\u0096\u0002H\u0007J\t\u0010¡\u0002\u001a\u00020\rH\u0007J'\u0010¢\u0002\u001a\u00030£\u00022\u0007\u0010\u008b\u0001\u001a\u00020J2\b\u0010¤\u0002\u001a\u00030¥\u00022\b\u0010¦\u0002\u001a\u00030§\u0002H\u0007JT\u0010¨\u0002\u001a\u00030©\u00022\u0006\u0010y\u001a\u00020o2\u0006\u0010E\u001a\u00020F2\b\u0010ª\u0002\u001a\u00030«\u00022\b\u0010¬\u0002\u001a\u00030\u0091\u00012\n\b\u0001\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u00101\u001a\u0002022\u0006\u0010M\u001a\u00020N2\b\u0010\u009e\u0001\u001a\u00030\u009b\u0001H\u0007J\u0014\u0010\u00ad\u0002\u001a\u00030®\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010¯\u0002\u001a\u00030°\u00022\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J\n\u0010±\u0002\u001a\u00030²\u0002H\u0007J\u0013\u0010³\u0002\u001a\u00030«\u00022\u0007\u0010´\u0002\u001a\u00020HH\u0007J\u0012\u0010µ\u0002\u001a\u00030¶\u00022\u0006\u0010G\u001a\u00020sH\u0007J\u001e\u0010·\u0002\u001a\u00030¸\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\u0010¹\u0002\u001a\u00030\u009d\u0001H\u0007J\u0014\u0010º\u0002\u001a\u00030»\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010¼\u0002\u001a\u00030½\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010¾\u0002\u001a\u00030¿\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001e\u0010À\u0002\u001a\u00030Á\u00022\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\b\u0010Â\u0002\u001a\u00030Ç\u0001H\u0007J\n\u0010Ã\u0002\u001a\u00030Ä\u0002H\u0007J\u0014\u0010Å\u0002\u001a\u00030Æ\u00022\b\u0010£\u0001\u001a\u00030¤\u0001H\u0007J\u0014\u0010Ç\u0002\u001a\u00030È\u00022\b\u0010Â\u0002\u001a\u00030Ç\u0001H\u0007J\u001c\u0010É\u0002\u001a\u00030Ê\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010h\u001a\u00020iH\u0007J\u001b\u0010Ë\u0002\u001a\u00030\u009d\u00012\u0007\u0010\u008b\u0001\u001a\u00020J2\u0006\u00101\u001a\u000202H\u0007J\u0013\u0010Ì\u0002\u001a\u00030Í\u00022\u0007\u0010Î\u0002\u001a\u00020sH\u0007J\u0014\u0010Ï\u0002\u001a\u00030Ð\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010Ñ\u0002\u001a\u00030Ò\u00022\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u0014\u0010Ó\u0002\u001a\u00030Ô\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010Õ\u0002\u001a\u00030Ö\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001d\u0010×\u0002\u001a\u00020\r2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J;\u0010Ø\u0002\u001a\u00030Ù\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\u0010Ú\u0002\u001a\u00030Ð\u00022\n\b\u0001\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0007\u0010G\u001a\u00030Í\u00022\u0006\u00101\u001a\u000202H\u0007JA\u0010Û\u0002\u001a\u00030Ü\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\u0010Ý\u0002\u001a\u00030Ù\u00022\b\u0010Þ\u0002\u001a\u00030ß\u00022\u0006\u0010y\u001a\u00020o2\u0007\u0010G\u001a\u00030Í\u00022\u0006\u0010R\u001a\u00020SH\u0007J\u0013\u0010à\u0002\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007JY\u0010á\u0002\u001a\u00030ß\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010â\u0002\u001a\u00030Ò\u00022\b\u0010ã\u0002\u001a\u00030ä\u00022\u0006\u0010R\u001a\u00020S2\b\u0010å\u0002\u001a\u00030Ô\u00022\b\b\u0001\u0010\f\u001a\u00020\r2\u0007\u0010G\u001a\u00030Í\u0002H\u0007J\u0014\u0010æ\u0002\u001a\u00030ç\u00022\b\u0010è\u0002\u001a\u00030Ü\u0002H\u0007J\u001d\u0010é\u0002\u001a\u00030ä\u00022\b\u0010ê\u0002\u001a\u00030ë\u00022\u0007\u0010G\u001a\u00030Í\u0002H\u0007J\u0014\u0010ì\u0002\u001a\u00030ë\u00022\b\u0010í\u0002\u001a\u00030î\u0002H\u0007J'\u0010ï\u0002\u001a\u00030¤\u00012\b\u0010ð\u0002\u001a\u00030©\u00022\b\u0010ñ\u0002\u001a\u00030²\u00022\u0007\u0010G\u001a\u00030«\u0002H\u0007J\u0012\u0010ò\u0002\u001a\u00030ó\u00022\u0006\u0010R\u001a\u00020SH\u0007J\n\u0010ô\u0002\u001a\u00030õ\u0002H\u0007J\u0014\u0010ö\u0002\u001a\u00030÷\u00022\b\u0010ø\u0002\u001a\u00030Í\u0002H\u0007J\t\u0010ù\u0002\u001a\u00020VH\u0007J\u0014\u0010ú\u0002\u001a\u00030û\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J$\u0010ü\u0002\u001a\u00030ý\u00022\u0006\u0010y\u001a\u00020o2\u0006\u0010G\u001a\u00020H2\b\u0010þ\u0002\u001a\u00030ÿ\u0002H\u0007J\u001a\u0010\u0080\u0003\u001a\u00030\u0081\u00032\u0006\u0010I\u001a\u00020J2\u0006\u0010G\u001a\u00020sH\u0007J&\u0010\u0082\u0003\u001a\u00030\u0083\u00032\b\u0010ø\u0001\u001a\u00030ñ\u00012\u0006\u0010I\u001a\u00020J2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J\u0014\u0010\u0084\u0003\u001a\u00030\u0085\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010\u0086\u0003\u001a\u00030\u0087\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010\u0088\u0003\u001a\u00030\u0089\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\n\u0010\u008a\u0003\u001a\u00030\u008b\u0003H\u0007J\n\u0010\u008c\u0003\u001a\u00030\u008d\u0003H\u0007J\u001a\u0010\u008e\u0003\u001a\u00030\u008f\u00032\u0006\u0010y\u001a\u00020o2\u0006\u0010G\u001a\u00020HH\u0007J\u0014\u0010\u0090\u0003\u001a\u00030\u0091\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010\u0092\u0003\u001a\u00030\u0093\u00032\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u0014\u0010\u0094\u0003\u001a\u00030\u0095\u00032\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u0014\u0010\u0096\u0003\u001a\u00030\u0097\u00032\b\b\u0001\u0010Y\u001a\u00020ZH\u0007J\u0013\u0010\u0098\u0003\u001a\u00030\u0099\u00032\u0007\u0010\u009a\u0003\u001a\u00020oH\u0007J\u001d\u0010\u009b\u0003\u001a\u00030\u009c\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0007\u0010\u009a\u0003\u001a\u00020oH\u0007J\n\u0010\u009d\u0003\u001a\u00030\u009e\u0003H\u0007J\u0014\u0010\u009f\u0003\u001a\u00030 \u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010¡\u0003\u001a\u00030¢\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0014\u0010£\u0003\u001a\u00030¤\u00032\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0007J\u0014\u0010¥\u0003\u001a\u00030¦\u00032\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0007J\u0014\u0010§\u0003\u001a\u00030¨\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010©\u0003\u001a\u00030ª\u00032\b\u0010«\u0003\u001a\u00030¬\u0003H\u0001¢\u0006\u0003\b\u00ad\u0003J\u000f\u0010®\u0003\u001a\u00030¯\u0003*\u00030°\u0003H\u0002¨\u0006±\u0003"}, d2 = {"Lru/mail/sdk/MailSdkModule;", "", "<init>", "()V", "provideShortcutUpdater", "Lru/mail/logic/content/impl/ShortcutUpdater;", "context", "Landroid/content/Context;", "provideMailSdkHandleURIDelegate", "Lru/mail/ui/navigation/MailSdkHandleURIDelegate;", "appId", "", "logger", "Lru/mail/util/log/Logger;", "provideAppCacheCleaner", "Lru/mail/sdk/AppCacheCleaner;", "imageLoaderRepositoryProvider", "Ljavax/inject/Provider;", "Lru/mail/imageloader/ImageLoaderRepository;", "accountManagerProvider", "Lru/mail/auth/AccountManagerWrapper;", "provideRequestArbiter", "Lru/mail/arbiter/RequestArbiter;", "provideDomainResolver", "Lru/mail/data/cmd/server/DomainResolver;", "provideHostUpdater", "Lru/mail/sdk/HostUpdater;", "provideEventLoggerWrapper", "Lru/mail/analytics/EventLoggerWrapper;", "additionalAnalyticsParamsProvider", "Lru/mail/util/analytics/logger/AdditionalAnalyticsParamsProvider;", "eventLoggerCounter", "Lru/mail/util/analytics/counter/EventLoggerCounter;", "provideEventLogger", "Lru/mail/analytics/EventLogger;", "eventLoggerWrapper", "providePortalAnalytics", "Lru/mail/kit/analytics/Analytics;", "eventLogger", "provideOzonFeedAnalytics", "provideAdditionalAnalyticsParamsProvider", "provideSessionEventLoggerCounter", "Lru/mail/util/analytics/counter/SessionEventLoggerCounter;", "provideEventLoggerCounter", "sessionEventLoggerCounter", "providePermissionAccessHistory", "Lru/mail/logic/content/PermissionAccess$History;", "provideRequestArbiterConfigHelper", "Lru/mail/arbiter/RequestArbiterConfigHelper;", "sharedPreferences", "Landroid/content/SharedPreferences;", "provideLogHandlerRepository", "Lru/mail/util/log/LogRepository;", "asyncFileHandlerFactory", "Lru/mail/util/log/AsyncFileHandlerFactory;", "provideWebViewErrorAnalytics", "Lru/mail/analytics/WebViewErrorAnalytics;", "impl", "Lru/mail/analytics/WebViewErrorAnalyticsImpl;", "provideAppMetricsTracker", "Lru/mail/appmetricstracker/api/AppMetricsTracker;", "provideErrorReporter", "Lru/mail/uikit/reporter/ErrorReporter;", "provideDeeplinkInteractor", "Lru/mail/deeplink/DeeplinkInteractor;", "provideRequestListenerManager", "Lru/mail/data/cmd/server/RequestListenerManager;", "provideShrinkManager", "Lru/mail/logic/shrink/service/ShrinkManager;", "workScheduler", "Lru/mail/march/internal/work/WorkScheduler;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "configurationRepository", "Lru/mail/config/ConfigurationRepository;", "storage", "Lru/mail/logic/shrink/service/ShrinkManagerStorage;", "timeProvider", "Lru/mail/utils/TimeProvider;", "provideDefaultLoadBucketSize", "", "appLogger", "configuration", "Lru/mail/config/Configuration;", "provideDefaultOnePageLimit", "provideLoadMoreToLimit", "", "provideFiltersApi", "Lru/mail/filter/data/FiltersApi;", "retrofit", "Lretrofit2/Retrofit;", "provideDeviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "accountManagerWrapper", "provideAddressBookApi", "Lru/mail/logic/sync/AddressBookApi;", "provideMailAppLogger", "loggerWrapper", "Lru/mail/util/log/LoggerWrapper;", "provideLoggerWrapper", "provideActionExecutor", "Lru/mail/clipboard/presentation/executor/ActionExecutor;", "interactor", "Lru/mail/clipboard/domain/interactor/CreateMailByTextInteractor;", "config", "Lru/mail/clipboard/domain/config/CreateMailByClipboardConfig;", "provideWebViewWorkaroundManager", "Lru/mail/logic/navigation/WebViewWorkaroundManager;", "provideHelpersStorage", "Lru/mail/config/HelpersStorage;", "provideDataManager", "Lru/mail/logic/content/DataManager;", "provideLogCollector", "Lru/mail/util/log/LogCollector;", "provideMailAnalyticsKt", "Lru/mail/analytics/MailAnalyticsKt;", "provideAsserterConfigFactory", "Lru/mail/asserter/core/AsserterConfigFactory;", "provideSharedPreferences", "provideAuthTokenProvider", "Lru/mail/logic/content/AuthTokenProvider;", "dataManager", "provideHostsParser", "Lru/mail/HostsParser;", "provideAvatarHostInitializer", "Lru/mail/data/cmd/server/AvatarHostInitializer;", "provideMainLinkKeyProvider", "Lru/mail/data/cmd/server/MainLinkKeyProvider;", "provideBrowserCookieSetter", "Lru/mail/serverapi/BrowserCookieSetter;", "configRetriever", "Lru/mail/config/ConfigRetriever;", "provideNameProvider", "Lru/mail/imageloader/NameProvider;", "provideFoldersWatcherInteractor", "Lru/mail/ui/quickactions/folders/FoldersWatcherInteractor;", "provideGroupingInteractor", "Lru/mail/ui/fragments/mailbox/grouping/GroupingInteractor;", "prefs", "configRepo", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "provideAuthSdkFilter", "Lru/mail/util/log/LogFilter;", "providesCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "defaultDispatcher", "provideSnackbarOnEmailsConfiguration", "Lru/mail/clipboard/presentation/snackbar/configuration/SnackbarOnEmailsConfiguration;", "configRepository", "provideStringResolver", "Lru/mail/utils/StringResolver;", "provideVkSignInDelegate", "Lru/mail/auth/webview/VKConnectSignInDelegate;", "provideSurveysListConfig", "Lru/mail/csat/survey/CsatConfig;", "createSurveyConfig", "Lru/mail/csat/survey/SurveyConfig;", "csatConfig", "Lru/mail/config/Configuration$CsatConfig;", "provideCsatLogger", "provideSurveyInteractor", "Lru/mail/csat/survey/SurveyInteractor;", "surveyInteractor", "Lru/mail/csat/SurveyInteractorImpl;", "provideWorkScheduler", "provideMailApiRetrofit", "accountManager", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "tokenRepository", "Lru/mail/serverapi/retrofit/session/TokenRepository;", "mailSdkOkHttpManager", "Lru/mail/vkteams/gost/MailSdkOkHttpManager;", "appVersionProvider", "Lru/mail/deviceinfo/AppVersionProvider;", "vpnBlockingInterceptor", "Lru/mail/vpn/detect/impl/VpnBlockingInterceptor;", "provideKSerializationRetrofit", "provideMailApiRetrofitInternal", "converterFactory", "Lretrofit2/Converter$Factory;", "createConfig", "Lru/mail/serverapi/retrofit/RetrofitConfig;", "createHttpLoggingInterceptor", "Lokhttp3/Interceptor;", "createNetworkLogger", "baseLogger", "provideMigrantApi", "Lru/mail/logic/cmd/sync/xmailmigration/MigrantApi;", "provideNetworkServiceInterceptorsHolder", "Lru/mail/util/NetworkServiceInterceptorsHolder;", "summarizeChunkInterceptor", "Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor;", "provideActivityLifecycleHandler", "Lru/mail/utils/lifecycle/ActivityLifecycleHandler;", "provideNavigator", "Lru/mail/logic/navigation/Navigator;", "provideExtendedDataManager", "Lru/mail/logic/content/ExtendedDataManager;", "provideMailFeature", "Lru/mail/portal/features/MailFeature;", "provideMailSdkIntentProvider", "Lru/mail/util/push/MailSdkIntentProvider;", "provideShrinkManagerStorage", "provideTimeProvider", "provideAppMonitorFactory", "Lru/mail/feature/appmetricstracker/AppMonitorFactory;", "provideGetOverQuotaDateUseCase", "Lru/mail/ui/cloud/overquota/LoadOverQuotaStateUseCase;", "mailCloudInfoRepository", "Lru/mail/ui/cloud/MailCloudInfoRepository;", "overQutaRepository", "Lru/mail/overquota/OverQuotaDataRepository;", "overQuotaConfigDelegate", "Lru/mail/overquota/OverQuotaConfigDelegate;", "Lru/mail/analytics/OverQuotaAnalytics;", "provideMailCloudInfoRepository", "applicationScope", "overQuotaAnalytics", "imapPromoAnalytics", "Lru/mail/analytics/ImapPromoAnalytics;", "provideAccountRelocationManager", "Lru/mail/util/relocation/AccountRelocationManager;", "actualRelocationConfigRepository", "Lru/mail/util/relocation/ActualRelocationConfigRepository;", "provideLicenseAgreementConfigRepository", "Lru/mail/util/relocation/LicenseAgreementConfigRepository;", "provideDefaultLogger", "provideAppVersionCode", "provideIsBeta", "provideMailAnalytics", "Lru/mail/analytics/MailAnalytics;", "provideMailAppAnalytics", "provideEncryptedSharedPreferencesProvider", "Lru/mail/utils/prefs/EncryptedSharedPreferencesProvider;", "Lru/mail/utils/prefs/analytics/EncryptedPreferencesAnalytics;", "provideEncryptedPreferencesAnalytics", "tracker", "Lru/mail/analytics/AnalyticTracker;", "provideDataStoreAnalytics", "Lru/mail/utils/prefs/analytics/DataStoreAnalytics;", "provideAsyncFileHandlerFactoryLogLevelAll", "provideAnalyticTracker", "timeTrackerFactory", "Lru/mail/analytics/timer/TimeTracker$Factory;", "analyticTracker", "provideAccountManager", "provideAccountManagerSettings", "providePlatformInfo", "provideNoAuthInfoCreator", "Lru/mail/serverapi/retrofit/session/NoAuthInfoCreator;", "provideExecutorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "requestArbiter", "provideSummarizeChunkInterceptor", "provideGooglePlayServicesAvailabilityProvider", "Lru/mail/utils/GooglePlayServicesAvailabilityProvider;", "providesOkHttpConfig", "Lru/mail/config/OkHttpConfig;", "providesOkHttpClient", "Lokhttp3/OkHttpClient;", "providesCoreOkHttpClient", "Lokhttp3/OkHttpClient$Builder;", "okHttpConfig", "mailAnalyticsProvider", "Ldagger/Lazy;", "networkErrorAnalyticsInterceptor", "Lru/mail/network/networkchecker/NetworkErrorAnalyticsInterceptor;", "provideMailSdkOkHttpManager", "provideActualRelocationConfigRepository", "provideTimeSpentTrackerStorage", "Lru/mail/timespent/storage/TimeSpentStorage;", "provideConfiguration", "Lru/mail/clipboard/domain/config/ClipboardConfiguration;", "provideFromMenuPlusConfiguration", "Lru/mail/clipboard/presentation/plate/configuration/FromMenuPlusConfiguration;", "provideCreateMailByClipboardInteractor", "providerCreateMailByClipboardConfig", "provideMailboxContextProvider", "Lru/mail/logic/content/MailboxContextProvider;", "provideResourceObservable", "Lru/mail/data/dao/ResourceObservable;", "provideFoldersRepository", "Lru/mail/logic/content/impl/FoldersRepository;", "provideOnboardingConfiguration", "Lru/mail/clipboard/presentation/plate/configuration/FromMenuPlusConfiguration$OnboardingAnimationConfig;", "provideAttachSizeLockLogger", "provideCheckAttachSizeExceededUseCase", "Lru/mail/attachments/lock/CheckAttachSizeExceededUseCase;", "checkSubscriptionSupportUseCase", "Lru/mail/ads/core/api/domain/usecase/CheckSubscriptionSupportUseCase;", "adConfig", "Lru/mail/ads/config/api/data/model/AdConfiguration;", "provideMailSurveyRepository", "Lru/mail/csat/SurveyRepository;", "surveyAnalytics", "Lru/mail/csat/SurveyAnalytics;", "appScope", "provideBundleStorageInteractor", "Lru/mail/ui/fragments/mailbox/newmail/bundlestorage/BundleStorageInteractor;", "provideDivUrlHandler", "Lru/mail/mainpage/ui/divkit/DivUrlHandler;", "provideSurveyInteractorProvider", "Lru/mail/csat/listeners/OnResultListenerProvider;", "provideSurveyAnalytics", "mailAppAnalytics", "provideGoToActionAnalyticTracker", "Lru/mail/analytics/gotoaction/GoToActionAnalyticTracker;", "provideUrlConstructor", "Lru/mail/survey/webview/UrlConstructor;", "surveyConfig", "providePortalManager", "Lru/mail/portal/PortalManager;", "provideDesignManager", "Lru/mail/logic/design/DesignManager;", "provideSSLCertificatesManager", "Lru/mail/webcomponent/ssl/SSLCertificatesManager;", "provideGoToActionInMailsListHandler", "Lru/mail/logic/gotoaction/GoToActionInMailsListHandler;", "navigator", "provideUserBoundByVIDDelegate", "Lru/mail/ui/auth/universal/UserBoundByVKIDDelegate;", "provideAnswerListenerProvider", "Lru/mail/csat/listeners/AnswerListenerProvider;", "provideLinkNavigator", "Lru/mail/logic/navigation/LinkNavigator;", "providePlateResolver", "Lru/mail/clipboard/presentation/plate/viewModel/stateResolver/ClipboardPlateResolver;", "provideSurveyConfig", "provideAnalytics", "Lru/mail/mainpage/MainPageAnalytics;", "mailAnalyticsKt", "provideDirectoryRepository", "Lru/mail/util/DirectoryRepository;", "provideMainPageApi", "Lru/mail/mainpage/api/MainPageApi;", "provideLocationProvider", "Lru/mail/mainpage/utils/LocationProvider;", "provideMainPageDataStoreRepository", "Lru/mail/portal/app/adapter/mainpage/MainPageDataStoreRepository;", "provideLogger", "provideLocalSource", "Lru/mail/mainpage/data/MainPageLocalSource;", "directoryRepository", "provideMainPageRepository", "Lru/mail/mainpage/domain/MainPageRepository;", "localSource", "remoteSource", "Lru/mail/mainpage/data/MainPageRemoteSource;", "provideAppId", "provideRemoteSource", "mainPageApi", "mainPageApiResultHandler", "Lru/mail/mainpage/data/MainPageApiResultHandler;", "locationProvider", "provideLoadMainPageUseCase", "Lru/mail/mainpage/domain/LoadMainPageUseCase;", "repository", "provideApiResultHandler", "simpleApiResultHandler", "Lru/mail/data/api/SimpleApiResultHandler;", "provideSimpleApiResultHandler", "noAuthHandler", "Lru/mail/logic/processors/auth/NoAuthHandler;", "provideSurveyInteractorImpl", "surveyRepository", "onResultListenerProvider", "provideBandwidthConstants", "Lru/mail/android_utils/connection/BandwidthConstants;", "provideStartupTypeChecker", "Lru/mail/utils/StartupTypeChecker;", "provideLocationPermissionHelper", "Lru/mail/mainpage/utils/LocationPermissionHelper;", "mainPageAnalytics", "provideUseSetupAsyncService", "provideSoundService", "Lru/mail/util/sound/SoundService;", "provideFolderChooserInteractor", "Lru/mail/ui/folder/chooser/viewmodel/FolderChooserInteractor;", "grantManager", "Lru/mail/glasha/domain/managers/FolderGrantsManager;", "provideShareMailAnalytics", "Lru/mail/ui/fragments/mailbox/mailview/interactor/share/ShareMailAnalytics;", "provideUrlParamsAnalyticsSender", "Lru/mail/utils/analytics/UrlParamsAnalyticsSender;", "provideNetworkServiceFactory", "Lru/mail/network/NetworkServiceFactory;", "provideConnectionClassManager", "Lru/mail/util/connection/ConnectionClassManager;", "provideFoldersManager", "Lru/mail/logic/content/FoldersManager;", "provideRouter", "Lru/mail/kit/routing/Router;", "provideSafetyDependenciesProvider", "Lru/mail/utils/SafetyDependenciesProvider;", "provideAccessCoroutineExecutor", "Lru/mail/interactor/AccessCoroutineExecutor;", "provideNotificationManager", "Landroidx/core/app/NotificationManagerCompat;", "provideFolderApi", "Lru/mail/data/api/FolderApi;", "provideHelpersApi", "Lru/mail/data/cmd/server/helper/api/HelperApi;", "provideSharedFolderApi", "Lru/mail/glasha/data/network/api/SharedFolderApi;", "provideAccountProvider", "Lru/mail/glasha/domain/usecases/AccountProvider;", "dataManger", "provideSyncUseCase", "Lru/mail/glasha/domain/usecases/SyncUseCase;", "provideMailDeeplinkCreator", "Lru/mail/api/MailDeeplinkCreator;", "provideDisableDownloadAttachInterface", "Lru/mail/sdk/core/presentation/attach/DisableDownloadAttachInterface;", "provideBetaStateKeeper", "Lru/mail/logic/betastate/BetaStateKeeper;", "provideNetworkRequirementManager", "Lru/mail/march/internal/work/NetworkRequirementManager;", "provideMailDependenciesInitHelper", "Lru/mail/setup/MailDependenciesInitHelper;", "providesHostProviderWrapper", "Lru/mail/network/HostProviderWrapper;", "provideMailOfflineAttachmentQuotaController", "Lru/mail/offline/attaches/storage/api/MailOfflineAttachmentQuotaController;", "storageDependenciesProvider", "Lru/mail/offline/attaches/storage/api/OfflineAttachmentsStorageDependenciesProvider;", "provideMailOfflineAttachmentQuotaController$mails_release", "toMailShrinkerConfig", "Lru/mail/offline/attaches/storage/api/MailOfflineAttachmentShrinkerConfig;", "Lru/mail/download/facade/models/OfflineAttachesConfig;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
@SourceDebugExtension({"SMAP\nMailSdkModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailSdkModule.kt\nru/mail/sdk/MailSdkModule\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2019:1\n37#2,2:2020\n37#2,2:2025\n1869#3,2:2022\n1563#3:2027\n1634#3,3:2028\n1#4:2024\n*S KotlinDebug\n*F\n+ 1 MailSdkModule.kt\nru/mail/sdk/MailSdkModule\n*L\n743#1:2020,2\n1006#1:2025,2\n807#1:2022,2\n1163#1:2027\n1163#1:2028,3\n*E\n"})
public final class MailSdkModule {
    public static final int $stable = 0;

    @NotNull
    public static final MailSdkModule INSTANCE = new MailSdkModule();

    private MailSdkModule() {
    }

    private final RetrofitConfig createConfig(Configuration configuration) {
        return new RetrofitConfig(configuration.getRetrofitConfig().getRethrowRequestSessionException());
    }

    @JvmStatic
    @NotNull
    public static final Interceptor createHttpLoggingInterceptor(@NotNull final Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: ru.mail.sdk.n
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public final void log(String str) {
                MailSdkModule.createHttpLoggingInterceptor$lambda$0(logger, str);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createHttpLoggingInterceptor$lambda$0(Logger logger, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Logger.debug$default(logger, it, null, 2, null);
    }

    @JvmStatic
    @NotNull
    public static final Logger createNetworkLogger(@NotNull Logger baseLogger) {
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        final LogFilter logFilter = new LogFilter();
        Formats.ParamFormat[] paramFormatArr = (Formats.ParamFormat[]) TornadoSessionSensitiveFilters.INSTANCE.getFilters().toArray(new Formats.ParamFormat[0]);
        logFilter.addTokenConstraints((Formats.ParamFormat[]) Arrays.copyOf(paramFormatArr, paramFormatArr.length));
        return new TransformLogger(baseLogger.createLogger("Network"), new Function1() { // from class: ru.mail.sdk.l
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailSdkModule.createNetworkLogger$lambda$0(logFilter, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String createNetworkLogger$lambda$0(LogFilter logFilter, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return logFilter.filter(it);
    }

    @JvmStatic
    @NotNull
    public static final SurveyConfig createSurveyConfig(@NotNull Configuration.CsatConfig csatConfig, @NotNull SharedPreferences sharedPreferences) {
        SurveyIntroConfig surveyIntroConfig;
        Intrinsics.checkNotNullParameter(csatConfig, "csatConfig");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        boolean zAreEqual = Intrinsics.areEqual(Locale.getDefault().getLanguage(), "ru");
        Long surveyId = csatConfig.getSurveyId();
        ShowPlace showPlace = csatConfig.getShowPlace();
        Integer showAfterDays = csatConfig.getShowAfterDays();
        Integer repeatAfterDays = csatConfig.getRepeatAfterDays();
        String webviewUrl = csatConfig.getWebviewUrl();
        List<CsatTrigger> triggers = csatConfig.getTriggers();
        List<CsatCondition> conditions = csatConfig.getConditions();
        boolean z10 = sharedPreferences.getBoolean("always_show_survey", false);
        Configuration.SurveyIntroConfig intro = csatConfig.getIntro();
        if (intro != null) {
            String image = intro.getImage();
            String imageDark = intro.getImageDark();
            String imageAspect = intro.getImageAspect();
            String titleRu = zAreEqual ? intro.getTitleRu() : intro.getTitleEn();
            String subtitleRu = zAreEqual ? intro.getSubtitleRu() : intro.getSubtitleEn();
            String buttonRu = zAreEqual ? intro.getButtonRu() : intro.getButtonEn();
            surveyIntroConfig = new SurveyIntroConfig(image, imageDark, imageAspect, titleRu, subtitleRu, buttonRu.length() == 0 ? null : buttonRu);
        } else {
            surveyIntroConfig = null;
        }
        return new SurveyConfig(surveyId, showPlace, showAfterDays, repeatAfterDays, webviewUrl, triggers, conditions, z10, surveyIntroConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final AppMonitor provideAppMonitorFactory$lambda$0(AppMetricsTracker tracker) {
        Intrinsics.checkNotNullParameter(tracker, "tracker");
        if (ClassProvider.getSplashScreenActivity() == null) {
            return new SessionMonitor(tracker, null, 2, 0 == true ? 1 : 0);
        }
        Class<?> splashScreenActivity = ClassProvider.getSplashScreenActivity();
        Intrinsics.checkNotNull(splashScreenActivity);
        return new SessionMonitor(tracker, CollectionsKt.listOf(splashScreenActivity));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LogConstraints provideAsyncFileHandlerFactoryLogLevelAll$lambda$0() {
        return new ConstraintsFactory().provideLogConstraints();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit provideAsyncFileHandlerFactoryLogLevelAll$lambda$1(MailAppAnalytics mailAppAnalytics, FileHandlerAnalyticsError it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mailAppAnalytics.logArchivationFailed(it.getErrorCode(), it.getErrorMessage());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Account[] provideDeviceIdProvider$lambda$0(AccountManagerWrapper accountManagerWrapper) {
        return accountManagerWrapper.getExternalAccountsByType("com.google");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit provideKSerializationRetrofit$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setCoerceInputValues(true);
        Json.setLenient(true);
        Json.setEncodeDefaults(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File provideLocalSource$lambda$0(DirectoryRepository directoryRepository, String login) {
        Intrinsics.checkNotNullParameter(login, "login");
        return directoryRepository.getMainPageDir(login);
    }

    private final Retrofit provideMailApiRetrofitInternal(Context context, AccountManagerWrapper accountManager, AccountManagerSettings accountManagerSettings, PlatformInfo platformInfo, Logger appLogger, TokenRepository tokenRepository, Configuration configuration, MailSdkOkHttpManager mailSdkOkHttpManager, AppVersionProvider appVersionProvider, VpnBlockingInterceptor vpnBlockingInterceptor, Converter.Factory converterFactory) {
        Locator locatorFrom = Locator.INSTANCE.from(context);
        TornadoSessionCreator tornadoSessionCreator = new TornadoSessionCreator(new TokenProvider(accountManager, accountManagerSettings));
        MailHostProvider mailHostProvider = new MailHostProvider(context, "new_mail_api", R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host, platformInfo);
        Logger loggerCreateNetworkLogger = createNetworkLogger(appLogger);
        RetrofitConfig retrofitConfigCreateConfig = createConfig(configuration);
        OkHttpClient.Builder builderNewBuilder = ((NetworkServiceFactory) locatorFrom.locate(NetworkServiceFactory.class)).getOkHttpClient().newBuilder();
        String string = mailHostProvider.getUrlBuilder().build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Retrofit retrofitBuild = new Retrofit.Builder().baseUrl(mailHostProvider.getUrlBuilder().build().toString()).client(mailSdkOkHttpManager.configureOkHttpClient(builderNewBuilder, string).addNetworkInterceptor(new TornadoStatusMetricTagInterceptor(loggerCreateNetworkLogger)).addInterceptor(vpnBlockingInterceptor).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(new SessionRecoveryInterceptor(accountManager, accountManagerSettings, tokenRepository)).addInterceptor(new TornadoSessionInterceptor(loggerCreateNetworkLogger, tornadoSessionCreator, retrofitConfigCreateConfig)).addInterceptor(new TornadoStatusInterceptor(loggerCreateNetworkLogger)).addInterceptor(new AppBuildParamsInterceptor(appVersionProvider)).addInterceptor(new PlatformParamsInterceptor(mailHostProvider)).addInterceptor(new PostParamsSignatureInterceptor()).addInterceptor(createHttpLoggingInterceptor(loggerCreateNetworkLogger)).build()).addConverterFactory(converterFactory).addCallAdapterFactory(new ApiResultCallAdapterFactory()).build();
        Intrinsics.checkNotNullExpressionValue(retrofitBuild, "build(...)");
        return retrofitBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List provideUrlParamsAnalyticsSender$lambda$0(ConfigurationRepository configurationRepository) {
        return configurationRepository.getConfiguration().getMarketingParamsForSendToAnalytics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit providesCoreOkHttpClient$lambda$0$2$0(Logger logger, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Logger.d$default(logger, message, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void providesCoreOkHttpClient$lambda$0$2$1(Lazy lazy, boolean z10) {
        ((MailAnalyticsKt) lazy.get()).dnsLookupRetryResult(z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean timeTrackerFactory$lambda$0(Configuration configuration, String track) {
        Intrinsics.checkNotNullParameter(track, "track");
        return configuration.getConfig().getTimeTrackerEvents().contains(track);
    }

    private final MailOfflineAttachmentShrinkerConfig toMailShrinkerConfig(OfflineAttachesConfig offlineAttachesConfig) {
        return new MailOfflineAttachmentShrinkerConfig(offlineAttachesConfig.getTargetQuotaBytes(), offlineAttachesConfig.getMaxFileSizeBytes(), offlineAttachesConfig.getShrinkTargetUsagePercent());
    }

    @Provides
    @NotNull
    public final ImapPromoAnalytics imapPromoAnalytics(@NotNull MailAnalyticsKt analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return analytics;
    }

    @Provides
    @NotNull
    public final OverQuotaAnalytics overQuotaAnalytics(@NotNull MailAnalyticsKt analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return analytics;
    }

    @Provides
    @Singleton
    @NotNull
    public final AccessCoroutineExecutor provideAccessCoroutineExecutor(@NotNull DataManager dataManager, @NotNull MailAppAnalytics analytics) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new AccessCoroutineExecutorImpl(dataManager, Dispatchers.getIO(), analytics);
    }

    @Provides
    @Reusable
    @NotNull
    public final AccountManagerWrapper provideAccountManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context);
        Intrinsics.checkNotNullExpressionValue(accountManagerWrapper, "getAccountManagerWrapper(...)");
        return accountManagerWrapper;
    }

    @Provides
    @NotNull
    public final AccountManagerSettings provideAccountManagerSettings(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (AccountManagerSettings) Locator.INSTANCE.locate(context, AccountManagerSettings.class);
    }

    @Provides
    @NotNull
    public final AccountProvider provideAccountProvider(@NotNull final DataManager dataManger) {
        Intrinsics.checkNotNullParameter(dataManger, "dataManger");
        return new AccountProvider() { // from class: ru.mail.sdk.m
            @Override // ru.mail.glasha.domain.usecases.AccountProvider
            public final String currentLoginOrNull() {
                return dataManger.getActiveLogin();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Provides
    @NotNull
    public final AccountRelocationManager provideAccountRelocationManager(@ApplicationContext @NotNull Context context, @NotNull ConfigurationRepository configurationRepository, @NotNull ActualRelocationConfigRepository actualRelocationConfigRepository, @NotNull AccountManagerWrapper accountManagerWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        Intrinsics.checkNotNullParameter(actualRelocationConfigRepository, "actualRelocationConfigRepository");
        Intrinsics.checkNotNullParameter(accountManagerWrapper, "accountManagerWrapper");
        ConfigurationWithRawData configuration = configurationRepository.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        String relocationName = actualRelocationConfigRepository.getRelocationName();
        List<Configuration.RelocationAgreementConfig> relocationAgreementConfigs = configuration.getRelocationAgreementConfigs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(relocationAgreementConfigs, 10));
        Iterator<T> it = relocationAgreementConfigs.iterator();
        while (it.hasNext()) {
            arrayList.add(((Configuration.RelocationAgreementConfig) it.next()).getName());
        }
        if (relocationName != null && relocationName.length() != 0) {
            return new AccountRelocationManagerImpl(new AccountRelocationNoAccountsCaseKeyStorageImpl(context, relocationName), new AccountRelocationMarkerImpl(accountManagerWrapper, relocationName), arrayList, relocationName);
        }
        return new AccountRelocationManagerImpl(new AccountRelocationNoAccountsCaseKeyStorageImpl(context, null, 2, null), new AccountRelocationMarkerImpl(accountManagerWrapper, null, 2, 0 == true ? 1 : 0), arrayList, null, 8, null);
    }

    @Provides
    @IntoSet
    @NotNull
    public final ActionExecutor provideActionExecutor(@NotNull CreateMailByTextInteractor interactor, @NotNull CreateMailByClipboardConfig config) {
        Intrinsics.checkNotNullParameter(interactor, "interactor");
        Intrinsics.checkNotNullParameter(config, "config");
        MailFeature emptyMailFeature = (MailFeature) Portal.featureProvider().provide(MailFeature.class);
        if (emptyMailFeature == null) {
            emptyMailFeature = new EmptyMailFeature();
        }
        return new CreateMailActionExecutor(emptyMailFeature, interactor, config);
    }

    @Provides
    @NotNull
    public final ActivityLifecycleHandler provideActivityLifecycleHandler(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ActivityLifecycleHandler) Locator.INSTANCE.from(context).locate(ActivityLifecycleHandler.class);
    }

    @Provides
    @NotNull
    public final ActualRelocationConfigRepository provideActualRelocationConfigRepository(@NotNull ConfigurationRepository configurationRepository) {
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        ConfigurationWithRawData configuration = configurationRepository.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        return new ActualRelocationConfigRepositoryImpl(configuration);
    }

    @Provides
    @Singleton
    @NotNull
    public final AdditionalAnalyticsParamsProvider provideAdditionalAnalyticsParamsProvider() {
        return new AdditionalAnalyticsParamsProvider();
    }

    @Provides
    @Singleton
    @NotNull
    public final AddressBookApi provideAddressBookApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(AddressBookApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (AddressBookApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final AnalyticTracker provideAnalyticTracker() {
        return new AnalyticTracker(null, 1, null);
    }

    @Provides
    @NotNull
    public final MainPageAnalytics provideAnalytics(@NotNull MailAnalyticsKt mailAnalyticsKt) {
        Intrinsics.checkNotNullParameter(mailAnalyticsKt, "mailAnalyticsKt");
        return new MainPageAnalyticsImpl(mailAnalyticsKt);
    }

    @Provides
    @NotNull
    public final AnswerListenerProvider provideAnswerListenerProvider(@NotNull final SurveyInteractorImpl surveyInteractor) {
        Intrinsics.checkNotNullParameter(surveyInteractor, "surveyInteractor");
        return new AnswerListenerProvider() { // from class: ru.mail.sdk.MailSdkModule.provideAnswerListenerProvider.1
            @Override // ru.mail.csat.listeners.AnswerListenerProvider
            public AnswerListener getAnswerListener() {
                return surveyInteractor;
            }
        };
    }

    @Provides
    @NotNull
    public final MainPageApiResultHandler provideApiResultHandler(@NotNull SimpleApiResultHandler simpleApiResultHandler, @NotNull MainPageAnalytics analytics) {
        Intrinsics.checkNotNullParameter(simpleApiResultHandler, "simpleApiResultHandler");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new MainPageApiResultHandlerImpl(simpleApiResultHandler, analytics);
    }

    @Provides
    @NotNull
    public final AppCacheCleaner provideAppCacheCleaner(@NotNull Provider<ImageLoaderRepository> imageLoaderRepositoryProvider, @NotNull Provider<AccountManagerWrapper> accountManagerProvider) {
        Intrinsics.checkNotNullParameter(imageLoaderRepositoryProvider, "imageLoaderRepositoryProvider");
        Intrinsics.checkNotNullParameter(accountManagerProvider, "accountManagerProvider");
        return new DefaultCacheCleaner(imageLoaderRepositoryProvider, accountManagerProvider);
    }

    @Provides
    @MainPage
    @NotNull
    public final String provideAppId(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(ru.mail.mainpage.R.string.main_page_tab_adapter_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Provides
    @Nullable
    public final AppMetricsTracker provideAppMetricsTracker(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return (AppMetricsTracker) Locator.INSTANCE.from(context).locate(AppMetricsTracker.class);
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    @Provides
    @NotNull
    public final AppMonitorFactory provideAppMonitorFactory() {
        return new AppMonitorFactory() { // from class: ru.mail.sdk.k
            @Override // ru.mail.feature.appmetricstracker.AppMonitorFactory
            public final AppMonitor create(AppMetricsTracker appMetricsTracker) {
                return MailSdkModule.provideAppMonitorFactory$lambda$0(appMetricsTracker);
            }
        };
    }

    @Provides
    @AppVersionCode
    public final int provideAppVersionCode() {
        return BuildConfigVariablesHolder.versionCode;
    }

    @Provides
    @NotNull
    public final AsserterConfigFactory provideAsserterConfigFactory(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (AsserterConfigFactory) Locator.INSTANCE.from(context).locate(AsserterConfigFactory.class);
    }

    @Provides
    @Named("log_level_all")
    @NotNull
    @Singleton
    public final AsyncFileHandlerFactory provideAsyncFileHandlerFactoryLogLevelAll(@NotNull final MailAppAnalytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new AsyncFileHandlerFactoryImpl(new Function0() { // from class: ru.mail.sdk.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailSdkModule.provideAsyncFileHandlerFactoryLogLevelAll$lambda$0();
            }
        }, new Function1() { // from class: ru.mail.sdk.e
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailSdkModule.provideAsyncFileHandlerFactoryLogLevelAll$lambda$1(analytics, (FileHandlerAnalyticsError) obj);
            }
        }, Level.ALL);
    }

    @Provides
    @AttachSizeLockLogger
    @NotNull
    public final Logger provideAttachSizeLockLogger() {
        return new LoggerImpl("AttachSizeLock", Log.INSTANCE.getLog("AttachSizeLock"));
    }

    @Provides
    @AuthSdkFilter
    @NotNull
    public final LogFilter provideAuthSdkFilter() {
        LogFilter logFilter = new LogFilter();
        Formats.ParamFormat[] paramFormatArr = (Formats.ParamFormat[]) LogFilters.INSTANCE.getFilters().toArray(new Formats.ParamFormat[0]);
        logFilter.addTokenConstraints((Formats.ParamFormat[]) Arrays.copyOf(paramFormatArr, paramFormatArr.length));
        return logFilter;
    }

    @Provides
    @NotNull
    public final AuthTokenProvider provideAuthTokenProvider(@NotNull AccountManagerWrapper accountManagerWrapper, @NotNull DataManager dataManager) {
        Intrinsics.checkNotNullParameter(accountManagerWrapper, "accountManagerWrapper");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        return new DefaultAuthTokenProvider(accountManagerWrapper, dataManager);
    }

    @Provides
    @NotNull
    public final AvatarHostInitializer provideAvatarHostInitializer() {
        return new AvatarHostInitializerImpl();
    }

    @Provides
    @NotNull
    public final BandwidthConstants provideBandwidthConstants(@NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return configuration.getBandwidthConstants();
    }

    @Provides
    @NotNull
    public final BetaStateKeeper provideBetaStateKeeper(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (BetaStateKeeper) Locator.INSTANCE.locate(context, BetaStateKeeper.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final BrowserCookieSetter provideBrowserCookieSetter(@NotNull ConfigRetriever configRetriever) {
        Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
        return (AuthenticatorConfig.getInstance().isOAuthEnabled() && configRetriever.getBoolean("need_use_stub_cookie_setter", false)) ? new BrowserCookieSetterStub() : new BrowserCookieSetterOld();
    }

    @Provides
    @NotNull
    public final BundleStorageInteractor provideBundleStorageInteractor(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DirectoryRepository directoryRepositoryFrom = DirectoryRepository.from(context);
        Intrinsics.checkNotNullExpressionValue(directoryRepositoryFrom, "from(...)");
        return new BundleStorageInteractorImpl(directoryRepositoryFrom);
    }

    @Provides
    @NotNull
    public final CheckAttachSizeExceededUseCase provideCheckAttachSizeExceededUseCase(@NotNull ConfigurationRepository configRepo, @NotNull CheckSubscriptionSupportUseCase checkSubscriptionSupportUseCase, @NotNull AdConfiguration adConfig) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(checkSubscriptionSupportUseCase, "checkSubscriptionSupportUseCase");
        Intrinsics.checkNotNullParameter(adConfig, "adConfig");
        return new CheckAttachSizeExceededUseCase(configRepo.getConfiguration().getSubscription().getAttachSizeLock(), checkSubscriptionSupportUseCase, adConfig);
    }

    @Provides
    @Singleton
    @NotNull
    public final ClipboardConfiguration provideConfiguration(@NotNull ConfigurationRepository configRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        return Configuration.ClipboardPlatesConfig.INSTANCE.toClipboardConfiguration(configRepository.getConfiguration().getClipboardPlatesConfig());
    }

    @Provides
    @NotNull
    public final ConnectionClassManager provideConnectionClassManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ConnectionClassManager) Locator.INSTANCE.from(context).locate(ConnectionClassManager.class);
    }

    @Provides
    @NotNull
    public final CreateMailByTextInteractor provideCreateMailByClipboardInteractor(@NotNull CreateMailByClipboardConfig config, @AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new CreateMailByTextInteractor(config, logger);
    }

    @Provides
    @CsatLogger
    @NotNull
    public final Logger provideCsatLogger() {
        return new LoggerImpl("Csat", Log.INSTANCE.getLog("Csat"));
    }

    @Provides
    @NotNull
    public final DataManager provideDataManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (DataManager) Locator.INSTANCE.from(context).locate(CommonDataManager.class);
    }

    @Provides
    @NotNull
    public final DataStoreAnalytics provideDataStoreAnalytics(@NotNull AnalyticTracker tracker) {
        Intrinsics.checkNotNullParameter(tracker, "tracker");
        return new DataStoreAnalyticsImpl(tracker);
    }

    @Provides
    @NotNull
    public final DeeplinkInteractor provideDeeplinkInteractor(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (DeeplinkInteractor) Locator.INSTANCE.locate(context, DeeplinkInteractor.class);
    }

    @Provides
    @Singleton
    @DefaultLoadBucketSize
    public final int provideDefaultLoadBucketSize(@AppLogger @NotNull Logger appLogger, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        int bucketSize = configuration.getMailListLoadConfig().getBucketSize();
        Logger.d$default(appLogger.createLogger("LoadMailList"), "Use bucket size: " + bucketSize, null, 2, null);
        return bucketSize;
    }

    @Provides
    @Reusable
    @NotNull
    public final Logger provideDefaultLogger(@AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        return logger.createLogger("MailApp");
    }

    @Provides
    @Singleton
    @DefaultOnePageLimit
    public final int provideDefaultOnePageLimit(@AppLogger @NotNull Logger appLogger, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        int onePageLimit = configuration.getMailListLoadConfig().getOnePageLimit();
        Logger.d$default(appLogger.createLogger("LoadMailList"), "Use one page limit: " + onePageLimit, null, 2, null);
        return onePageLimit;
    }

    @Provides
    @NotNull
    public final DesignManager provideDesignManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (DesignManager) Locator.INSTANCE.from(context).locate(DesignManager.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final DeviceIdProvider provideDeviceIdProvider(@ApplicationContext @NotNull Context context, @NotNull final AccountManagerWrapper accountManagerWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManagerWrapper, "accountManagerWrapper");
        return new DefaultDeviceIdProvider(context, (Function0<Account[]>) new Function0() { // from class: ru.mail.sdk.j
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailSdkModule.provideDeviceIdProvider$lambda$0(accountManagerWrapper);
            }
        });
    }

    @Provides
    @NotNull
    public final DirectoryRepository provideDirectoryRepository(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DirectoryRepository directoryRepositoryFrom = DirectoryRepository.from(context);
        Intrinsics.checkNotNullExpressionValue(directoryRepositoryFrom, "from(...)");
        return directoryRepositoryFrom;
    }

    @Provides
    @NotNull
    public final DisableDownloadAttachInterface provideDisableDownloadAttachInterface(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DisableDownloadAttachInterfaceProviderKt.locateDisableDownloadAttachInterface(context);
    }

    @Provides
    @NotNull
    public final DivUrlHandler provideDivUrlHandler(@MainPage @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new DivUrlHandlerImpl(logger.createLogger("DivUrlHandler"));
    }

    @Provides
    @NotNull
    public final DomainResolver provideDomainResolver() {
        return new DomainResolverImpl();
    }

    @Provides
    @NotNull
    public final EncryptedPreferencesAnalytics provideEncryptedPreferencesAnalytics(@NotNull AnalyticTracker tracker) {
        Intrinsics.checkNotNullParameter(tracker, "tracker");
        return new EncryptedPreferencesAnalyticsImpl(tracker);
    }

    @Provides
    @NotNull
    public final EncryptedSharedPreferencesProvider provideEncryptedSharedPreferencesProvider(@ApplicationContext @NotNull Context context, @NotNull EncryptedPreferencesAnalytics analytics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new EncryptedSharedPreferencesProviderImpl(context, analytics);
    }

    @Provides
    @NotNull
    public final ErrorReporter provideErrorReporter(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ErrorReporter) Locator.INSTANCE.from(context).locate(AbstractErrorReporter.class);
    }

    @Provides
    @NotNull
    public final EventLogger provideEventLogger(@NotNull EventLoggerWrapper eventLoggerWrapper) {
        Intrinsics.checkNotNullParameter(eventLoggerWrapper, "eventLoggerWrapper");
        return eventLoggerWrapper;
    }

    @Provides
    @Singleton
    @NotNull
    public final EventLoggerCounter provideEventLoggerCounter(@NotNull SessionEventLoggerCounter sessionEventLoggerCounter) {
        Intrinsics.checkNotNullParameter(sessionEventLoggerCounter, "sessionEventLoggerCounter");
        return sessionEventLoggerCounter;
    }

    @Provides
    @Singleton
    @NotNull
    public final EventLoggerWrapper provideEventLoggerWrapper(@NotNull AdditionalAnalyticsParamsProvider additionalAnalyticsParamsProvider, @NotNull EventLoggerCounter eventLoggerCounter) {
        Intrinsics.checkNotNullParameter(additionalAnalyticsParamsProvider, "additionalAnalyticsParamsProvider");
        Intrinsics.checkNotNullParameter(eventLoggerCounter, "eventLoggerCounter");
        return new EventLoggerImpl(additionalAnalyticsParamsProvider, eventLoggerCounter);
    }

    @Provides
    @Singleton
    @NotNull
    public final ExecutorSelector provideExecutorSelector(@NotNull RequestArbiter requestArbiter) {
        Intrinsics.checkNotNullParameter(requestArbiter, "requestArbiter");
        return requestArbiter;
    }

    @Provides
    @NotNull
    public final ExtendedDataManager provideExtendedDataManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (ExtendedDataManager) Locator.INSTANCE.from(context).locate(CommonDataManager.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final FiltersApi provideFiltersApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(FiltersApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (FiltersApi) objCreate;
    }

    @Provides
    @Singleton
    @NotNull
    public final FolderApi provideFolderApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(FolderApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (FolderApi) objCreate;
    }

    @Provides
    @NotNull
    public final FolderChooserInteractor provideFolderChooserInteractor(@NotNull DataManager dataManager, @NotNull MailAppAnalytics analytics, @NotNull FolderGrantsManager grantManager) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(grantManager, "grantManager");
        FoldersManager foldersManager = dataManager.getFoldersManager();
        Intrinsics.checkNotNullExpressionValue(foldersManager, "getFoldersManager(...)");
        return new FolderChooserInteractorImpl(dataManager, foldersManager, analytics, grantManager);
    }

    @Provides
    @NotNull
    public final FoldersManager provideFoldersManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (FoldersManager) Locator.INSTANCE.from(context).locate(CommonDataManager.class);
    }

    @Provides
    @NotNull
    public final FoldersRepository provideFoldersRepository(@NotNull DataManager dataManager) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        FoldersRepository foldersRepository = dataManager.getFoldersRepository();
        Intrinsics.checkNotNullExpressionValue(foldersRepository, "getFoldersRepository(...)");
        return foldersRepository;
    }

    @Provides
    @NotNull
    public final FoldersWatcherInteractor provideFoldersWatcherInteractor(@NotNull DataManager dataManager) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        FoldersManager foldersManager = dataManager.getFoldersManager();
        Intrinsics.checkNotNullExpressionValue(foldersManager, "getFoldersManager(...)");
        return new FoldersWatcherInteractorImpl(dataManager, foldersManager);
    }

    @Provides
    @Singleton
    @NotNull
    public final FromMenuPlusConfiguration provideFromMenuPlusConfiguration(@NotNull ConfigurationRepository configRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Configuration.ClipboardPlatesConfig.FromMenuPlusConfig fromMenuPlusConfig = configRepository.getConfiguration().getClipboardPlatesConfig().getFromMenuPlusConfig();
        return new FromMenuPlusConfiguration(fromMenuPlusConfig.isEnabled(), new FromMenuPlusConfiguration.OnboardingAnimationConfig(fromMenuPlusConfig.getOnboardingAnimation().isEnabled(), fromMenuPlusConfig.getOnboardingAnimation().getStartDelay(), fromMenuPlusConfig.getOnboardingAnimation().getStepCount(), fromMenuPlusConfig.getOnboardingAnimation().getStepDelay(), fromMenuPlusConfig.getOnboardingAnimation().getStepsWithDelay()), fromMenuPlusConfig.getEnabledActionsInOrder());
    }

    @Provides
    @NotNull
    public final LoadOverQuotaStateUseCase provideGetOverQuotaDateUseCase(@NotNull MailCloudInfoRepository mailCloudInfoRepository, @NotNull OverQuotaDataRepository overQutaRepository, @NotNull OverQuotaConfigDelegate overQuotaConfigDelegate, @NotNull OverQuotaAnalytics analytics) {
        Intrinsics.checkNotNullParameter(mailCloudInfoRepository, "mailCloudInfoRepository");
        Intrinsics.checkNotNullParameter(overQutaRepository, "overQutaRepository");
        Intrinsics.checkNotNullParameter(overQuotaConfigDelegate, "overQuotaConfigDelegate");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new LoadOverQuotaStateUseCase(mailCloudInfoRepository, overQutaRepository, overQuotaConfigDelegate, analytics);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Provides
    @Singleton
    @NotNull
    public final GoToActionAnalyticTracker provideGoToActionAnalyticTracker(@NotNull MailAnalyticsKt analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new GoToActionAnalyticTrackerImpl(analytics, null, 2, 0 == true ? 1 : 0);
    }

    @Provides
    @Singleton
    @NotNull
    public final GoToActionInMailsListHandler provideGoToActionInMailsListHandler(@NotNull ConfigRetriever configRetriever, @NotNull Navigator navigator) {
        Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        return new GoToActionInMailsListHandlerImpl(configRetriever, navigator);
    }

    @Provides
    @NotNull
    public final GooglePlayServicesAvailabilityProvider provideGooglePlayServicesAvailabilityProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new GooglePlayServicesAvailabilityProviderImpl(context);
    }

    @Provides
    @NotNull
    public final GroupingInteractor provideGroupingInteractor(@NotNull SharedPreferences prefs, @NotNull ConfigurationRepository configRepo, @IoDispatcher @NotNull CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        return new GroupingInteractorImpl(prefs, configRepo, dispatcher);
    }

    @Provides
    @Singleton
    @NotNull
    public final HelperApi provideHelpersApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(HelperApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (HelperApi) objCreate;
    }

    @Provides
    @NotNull
    public final HelpersStorage provideHelpersStorage(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HelpersStorageImpl(context);
    }

    @Provides
    @NotNull
    public final HostUpdater provideHostUpdater(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HostUpdater(context);
    }

    @Provides
    @NotNull
    public final HostsParser provideHostsParser() {
        return BuildVariantHelper.isOnPremise() ? new HostsParserOnPremiseImpl() : new HostsParserSdkImpl(SdkHosts.INSTANCE.getSdkHostsInstance());
    }

    @Provides
    @BetaVersion
    public final boolean provideIsBeta() {
        return BuildConfigVariablesHolder.isBeta;
    }

    @Provides
    @KSerializationRetrofit
    @NotNull
    @Singleton
    public final Retrofit provideKSerializationRetrofit(@ApplicationContext @NotNull Context context, @NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull TokenRepository tokenRepository, @NotNull Configuration configuration, @MailsGostQualifier @NotNull MailSdkOkHttpManager mailSdkOkHttpManager, @NotNull AppVersionProvider appVersionProvider, @MailApiVpnBlockingInterceptor @NotNull VpnBlockingInterceptor vpnBlockingInterceptor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(tokenRepository, "tokenRepository");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(mailSdkOkHttpManager, "mailSdkOkHttpManager");
        Intrinsics.checkNotNullParameter(appVersionProvider, "appVersionProvider");
        Intrinsics.checkNotNullParameter(vpnBlockingInterceptor, "vpnBlockingInterceptor");
        return provideMailApiRetrofitInternal(context, accountManager, accountManagerSettings, platformInfo, appLogger, tokenRepository, configuration, mailSdkOkHttpManager, appVersionProvider, vpnBlockingInterceptor, KotlinSerializationConverterFactory.create(JsonKt.Json$default(null, new Function1() { // from class: ru.mail.sdk.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailSdkModule.provideKSerializationRetrofit$lambda$0((JsonBuilder) obj);
            }
        }, 1, null), MediaType.INSTANCE.get(Http.ContentType.APPLICATION_JSON)));
    }

    @Provides
    @NotNull
    public final LicenseAgreementConfigRepository provideLicenseAgreementConfigRepository(@NotNull ConfigurationRepository configurationRepository, @NotNull ActualRelocationConfigRepository actualRelocationConfigRepository) {
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        Intrinsics.checkNotNullParameter(actualRelocationConfigRepository, "actualRelocationConfigRepository");
        ConfigurationWithRawData configuration = configurationRepository.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        return new LicenseAgreementConfigRepositoryImpl(configuration, actualRelocationConfigRepository);
    }

    @Provides
    @NotNull
    public final LinkNavigator provideLinkNavigator(@NotNull Navigator navigator) {
        Intrinsics.checkNotNullParameter(navigator, "navigator");
        return new LinkNavigator(navigator);
    }

    @Provides
    @NotNull
    public final LoadMainPageUseCase provideLoadMainPageUseCase(@NotNull MainPageRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        return new LoadMainPageUseCase(repository);
    }

    @Provides
    @Singleton
    @LoadMoreToLimit
    public final boolean provideLoadMoreToLimit(@AppLogger @NotNull Logger appLogger, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        boolean useLoadMoreToLimit = configuration.getMailListLoadConfig().getUseLoadMoreToLimit();
        Logger.d$default(appLogger.createLogger("LoadMailList"), "Use load more to limit: " + useLoadMoreToLimit, null, 2, null);
        return useLoadMoreToLimit;
    }

    @Provides
    @Singleton
    @NotNull
    public final MainPageLocalSource provideLocalSource(@ApplicationContext @NotNull Context context, @NotNull final DirectoryRepository directoryRepository, @IoDispatcher @NotNull CoroutineDispatcher dispatcher, @NotNull MainPageAnalytics analytics, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(directoryRepository, "directoryRepository");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        return new MainPageLocalSource(context, new MainPageDirectoryProvider() { // from class: ru.mail.sdk.i
            @Override // ru.mail.mainpage.data.MainPageDirectoryProvider
            public final File getMainPageDir(String str) {
                return MailSdkModule.provideLocalSource$lambda$0(directoryRepository, str);
            }
        }, dispatcher, analytics, sharedPreferences);
    }

    @Provides
    @NotNull
    public final LocationPermissionHelper provideLocationPermissionHelper(@NotNull MainPageAnalytics mainPageAnalytics) {
        Intrinsics.checkNotNullParameter(mainPageAnalytics, "mainPageAnalytics");
        return new LocationPermissionHelperImpl(mainPageAnalytics);
    }

    @Provides
    @NotNull
    public final LocationProvider provideLocationProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new LocationProvider(context);
    }

    @Provides
    @NotNull
    public final LogCollector provideLogCollector(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (LogCollector) Locator.INSTANCE.from(context).locate(LogCollector.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final LogRepository provideLogHandlerRepository(@ApplicationContext @NotNull Context context, @Named("log_level_all") @NotNull AsyncFileHandlerFactory asyncFileHandlerFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(asyncFileHandlerFactory, "asyncFileHandlerFactory");
        DirectoryRepository directoryRepositoryFrom = DirectoryRepository.from(context);
        Intrinsics.checkNotNullExpressionValue(directoryRepositoryFrom, "from(...)");
        return new LogRepositoryImpl(directoryRepositoryFrom, context, asyncFileHandlerFactory);
    }

    @Provides
    @MainPage
    @NotNull
    public final Logger provideLogger(@MainPage @NotNull String appId, @AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(logger, "logger");
        return logger.createLogger(appId);
    }

    @Provides
    @Singleton
    @NotNull
    public final LoggerWrapper provideLoggerWrapper() {
        return new LoggerWrapper(new LoggerImpl("MailApp", Log.INSTANCE.getLog("MailApp")));
    }

    @Provides
    @NotNull
    public final MailAnalytics provideMailAnalytics(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((MailAnalyticInitializer) Locator.INSTANCE.from(context).locate(MailAnalyticInitializer.class)).getMailAnalytic();
    }

    @Provides
    @NotNull
    public final MailAnalyticsKt provideMailAnalyticsKt(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((MailAnalyticInitializer) Locator.INSTANCE.from(context).locate(MailAnalyticInitializer.class)).getMailAnalyticKt();
    }

    @Provides
    @NotNull
    @MailApiRetrofit
    @Singleton
    public final Retrofit provideMailApiRetrofit(@ApplicationContext @NotNull Context context, @NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo, @AppLogger @NotNull Logger appLogger, @NotNull TokenRepository tokenRepository, @NotNull Configuration configuration, @MailsGostQualifier @NotNull MailSdkOkHttpManager mailSdkOkHttpManager, @NotNull AppVersionProvider appVersionProvider, @MailApiVpnBlockingInterceptor @NotNull VpnBlockingInterceptor vpnBlockingInterceptor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(appLogger, "appLogger");
        Intrinsics.checkNotNullParameter(tokenRepository, "tokenRepository");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(mailSdkOkHttpManager, "mailSdkOkHttpManager");
        Intrinsics.checkNotNullParameter(appVersionProvider, "appVersionProvider");
        Intrinsics.checkNotNullParameter(vpnBlockingInterceptor, "vpnBlockingInterceptor");
        MoshiConverterFactory moshiConverterFactoryCreate = MoshiConverterFactory.create(new Moshi.Builder().add((JsonAdapter.Factory) new KotlinJsonAdapterFactory()).build());
        Intrinsics.checkNotNullExpressionValue(moshiConverterFactoryCreate, "create(...)");
        return provideMailApiRetrofitInternal(context, accountManager, accountManagerSettings, platformInfo, appLogger, tokenRepository, configuration, mailSdkOkHttpManager, appVersionProvider, vpnBlockingInterceptor, moshiConverterFactoryCreate);
    }

    @Provides
    @NotNull
    public final MailAppAnalytics provideMailAppAnalytics(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (MailAppAnalytics) Locator.INSTANCE.from(context).locate(MailAppAnalytics.class);
    }

    @Provides
    @NotNull
    @Singleton
    @AppLogger
    public final Logger provideMailAppLogger(@NotNull LoggerWrapper loggerWrapper) {
        Intrinsics.checkNotNullParameter(loggerWrapper, "loggerWrapper");
        return loggerWrapper;
    }

    @Provides
    @Singleton
    @NotNull
    public final MailCloudInfoRepository provideMailCloudInfoRepository(@NotNull DataManager dataManager, @ApplicationScope @NotNull CoroutineScope applicationScope) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(applicationScope, "applicationScope");
        return new MailCloudInfoRepositoryImpl(dataManager);
    }

    @Provides
    @NotNull
    public final MailDeeplinkCreator provideMailDeeplinkCreator() {
        return new MailDeeplinkCreatorImpl();
    }

    @Provides
    @Singleton
    @NotNull
    public final MailDependenciesInitHelper provideMailDependenciesInitHelper(@NotNull ConfigRetriever configRetriever) {
        Object objM13123constructorimpl;
        Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(Boolean.valueOf(configRetriever.getBoolean("is_mail_dependencies_init_helper_enabled", false)));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m13126exceptionOrNullimpl(objM13123constructorimpl) != null) {
            objM13123constructorimpl = Boolean.TRUE;
        }
        return new MailDependenciesInitHelper(((Boolean) objM13123constructorimpl).booleanValue(), null, 2, null);
    }

    @Provides
    @NotNull
    public final MailFeature provideMailFeature() {
        MailFeature mailFeature = (MailFeature) Portal.featureProvider().provide(MailFeature.class);
        return mailFeature == null ? new EmptyMailFeature() : mailFeature;
    }

    @Provides
    @Singleton
    @NotNull
    public final MailOfflineAttachmentQuotaController provideMailOfflineAttachmentQuotaController$mails_release(@NotNull OfflineAttachmentsStorageDependenciesProvider storageDependenciesProvider) {
        Intrinsics.checkNotNullParameter(storageDependenciesProvider, "storageDependenciesProvider");
        return storageDependenciesProvider.getMailShrinkerProvider().createQuotaController(toMailShrinkerConfig(MailSdk.getMailOfflineAttachesConfig()));
    }

    @Provides
    @Reusable
    @NotNull
    public final MailSdkHandleURIDelegate provideMailSdkHandleURIDelegate(@MailAppID @NotNull String appId, @AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new MailSdkHandleURIDelegate(appId, logger, null, 4, null);
    }

    @Provides
    @NotNull
    public final MailSdkIntentProvider provideMailSdkIntentProvider(@ApplicationContext @NotNull Context context) {
        Object objM13123constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl((MailSdkIntentProvider) Locator.INSTANCE.from(context).locate(MailSdkIntentProvider.class));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m13126exceptionOrNullimpl(objM13123constructorimpl) != null) {
            objM13123constructorimpl = new MailSdkIntentProvider() { // from class: ru.mail.sdk.MailSdkModule$provideMailSdkIntentProvider$2$1
                @Override // ru.mail.util.push.MailSdkIntentProvider
                public Intent provideMailPushPendingIntent() {
                    return new Intent();
                }
            };
        }
        return (MailSdkIntentProvider) objM13123constructorimpl;
    }

    @Provides
    @Reusable
    @MailsGostQualifier
    @NotNull
    public final MailSdkOkHttpManager provideMailSdkOkHttpManager() {
        return new PerDomainOkHttpModifierImpl();
    }

    @Provides
    @Singleton
    @NotNull
    public final SurveyRepository provideMailSurveyRepository(@NotNull DataManager dataManager, @NotNull WorkScheduler workScheduler, @NotNull SurveyAnalytics surveyAnalytics, @NotNull CoroutineScope appScope, @IoDispatcher @NotNull CoroutineDispatcher dispatcher, @NotNull SharedPreferences sharedPreferences, @NotNull TimeProvider timeProvider, @NotNull CsatConfig csatConfig) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(surveyAnalytics, "surveyAnalytics");
        Intrinsics.checkNotNullParameter(appScope, "appScope");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(csatConfig, "csatConfig");
        return new MailSurveyRepository(dataManager, new SurveyJsonParserImpl(new QuestionParserImpl(), surveyAnalytics, csatConfig), new SurveyAnswerTimeJsonParserImpl(), new AnswerJsonBuilderImpl(), workScheduler, dispatcher, appScope, sharedPreferences, timeProvider, surveyAnalytics);
    }

    @Provides
    @NotNull
    public final MailboxContextProvider provideMailboxContextProvider(@NotNull DataManager dataManager) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        return dataManager;
    }

    @Provides
    @NotNull
    public final MainLinkKeyProvider provideMainLinkKeyProvider() {
        return BuildVariantHelper.isOnPremise() ? new MainLinkKeyProviderOnPremiseImpl() : new MainLinkKeyProviderStubImpl();
    }

    @Provides
    @Singleton
    @NotNull
    public final MainPageApi provideMainPageApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(MainPageApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (MainPageApi) objCreate;
    }

    @Provides
    @NotNull
    public final MainPageDataStoreRepository provideMainPageDataStoreRepository(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new MainPageDataStoreRepositoryImpl(context);
    }

    @Provides
    @Singleton
    @NotNull
    public final MainPageRepository provideMainPageRepository(@ApplicationContext @NotNull final Context context, @NotNull MainPageLocalSource localSource, @NotNull MainPageRemoteSource remoteSource, @NotNull DataManager dataManager, @NotNull MainPageAnalytics analytics, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(localSource, "localSource");
        Intrinsics.checkNotNullParameter(remoteSource, "remoteSource");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new MainPageRepositoryImpl(new Supplier() { // from class: ru.mail.sdk.g
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return CurrentAccountUtils.getLastActiveProfileLogin(context);
            }
        }, localSource, remoteSource, dataManager, analytics, configuration.getMainPageConfig());
    }

    @Provides
    @Singleton
    @NotNull
    public final MigrantApi provideMigrantApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(MigrantApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (MigrantApi) objCreate;
    }

    @Provides
    @Reusable
    @NotNull
    public final NameProvider provideNameProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DataManagerNameProvider((DataManager) Locator.INSTANCE.from(context).locate(CommonDataManager.class));
    }

    @Provides
    @NotNull
    public final Navigator provideNavigator(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (Navigator) Locator.INSTANCE.from(context).locate(Navigator.class);
    }

    @Provides
    @NotNull
    public final NetworkRequirementManager provideNetworkRequirementManager(@NotNull ConfigRetriever configRetriever) {
        Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
        return new ConfigurationNetworkRequirementManager(configRetriever);
    }

    @Provides
    @NotNull
    public final NetworkServiceFactory provideNetworkServiceFactory(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (NetworkServiceFactory) Locator.INSTANCE.from(context).locate(NetworkServiceFactory.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final NetworkServiceInterceptorsHolder provideNetworkServiceInterceptorsHolder(@NotNull SummarizeChunkInterceptor summarizeChunkInterceptor, @NotNull @NetworkServiceVpnBlockingInterceptor VpnBlockingInterceptor vpnBlockingInterceptor) {
        Intrinsics.checkNotNullParameter(summarizeChunkInterceptor, "summarizeChunkInterceptor");
        Intrinsics.checkNotNullParameter(vpnBlockingInterceptor, "vpnBlockingInterceptor");
        NetworkServiceInterceptorsHolderImpl networkServiceInterceptorsHolderImpl = new NetworkServiceInterceptorsHolderImpl();
        networkServiceInterceptorsHolderImpl.addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor());
        networkServiceInterceptorsHolderImpl.addInterceptor(vpnBlockingInterceptor);
        networkServiceInterceptorsHolderImpl.addNetworkInterceptor(summarizeChunkInterceptor);
        return networkServiceInterceptorsHolderImpl;
    }

    @Provides
    @NotNull
    public final NoAuthInfoCreator provideNoAuthInfoCreator(@NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo) {
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        return new NoAuthInfoCreator(accountManagerSettings, platformInfo);
    }

    @Provides
    @NotNull
    public final NotificationManagerCompat provideNotificationManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        return notificationManagerCompatFrom;
    }

    @Provides
    @Singleton
    @NotNull
    public final FromMenuPlusConfiguration.OnboardingAnimationConfig provideOnboardingConfiguration(@NotNull FromMenuPlusConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getOnboardingAnimation();
    }

    @Provides
    @OzonFeed
    @Reusable
    @NotNull
    public final Analytics provideOzonFeedAnalytics(@NotNull EventLogger eventLogger) {
        Intrinsics.checkNotNullParameter(eventLogger, "eventLogger");
        return new PortalAnalytics(null, eventLogger, 1, null);
    }

    @Provides
    @Singleton
    @NotNull
    public final PermissionAccess.History providePermissionAccessHistory(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new PermissionAccessHistoryImpl(context);
    }

    @Provides
    @IntoSet
    @NotNull
    public final ClipboardPlateResolver providePlateResolver(@ApplicationContext @NotNull Context context, @NotNull CreateMailByClipboardConfig config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        return new CreateMailPlateStateResolver(context, config);
    }

    @Provides
    @NotNull
    public final PlatformInfo providePlatformInfo(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (PlatformInfo) Locator.INSTANCE.locate(context, PlatformInfo.class);
    }

    @Provides
    @Reusable
    @NotNull
    @PortalAnalytic
    public final Analytics providePortalAnalytics(@NotNull EventLogger eventLogger) {
        Intrinsics.checkNotNullParameter(eventLogger, "eventLogger");
        return new PortalAnalytics(null, eventLogger, 1, null);
    }

    @Provides
    @NotNull
    public final PortalManager providePortalManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (PortalManager) Locator.INSTANCE.from(context).locate(PortalManager.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final MainPageRemoteSource provideRemoteSource(@ApplicationContext @NotNull Context context, @IoDispatcher @NotNull CoroutineDispatcher dispatcher, @NotNull MainPageApi mainPageApi, @NotNull MainPageApiResultHandler mainPageApiResultHandler, @NotNull Configuration configuration, @NotNull LocationProvider locationProvider, @MainPage @NotNull Logger logger, @NotNull MainPageAnalytics analytics) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(mainPageApi, "mainPageApi");
        Intrinsics.checkNotNullParameter(mainPageApiResultHandler, "mainPageApiResultHandler");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(locationProvider, "locationProvider");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new MainPageRemoteSource(context, dispatcher, mainPageApi, mainPageApiResultHandler, configuration.getMainPageConfig(), locationProvider, logger.createLogger("MainPageRemoteSource"), analytics);
    }

    @Provides
    @NotNull
    public final RequestArbiter provideRequestArbiter(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (RequestArbiter) Locator.INSTANCE.from(context).locate(RequestArbiter.class);
    }

    @Provides
    @NotNull
    public final RequestArbiterConfigHelper provideRequestArbiterConfigHelper(@NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        return new RequestArbiterConfigHelper(sharedPreferences);
    }

    @Provides
    @Singleton
    @NotNull
    public final RequestListenerManager provideRequestListenerManager() {
        return new RequestListenerManager();
    }

    @Provides
    @NotNull
    public final ResourceObservable provideResourceObservable(@NotNull DataManager dataManager) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        ResourceObservable resourceObservable = dataManager.getResourceObservable();
        Intrinsics.checkNotNullExpressionValue(resourceObservable, "getResourceObservable(...)");
        return resourceObservable;
    }

    @Provides
    @MainPage
    @NotNull
    public final Router provideRouter() {
        return Portal.router();
    }

    @Provides
    @NotNull
    public final SSLCertificatesManager provideSSLCertificatesManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (SSLCertificatesManager) Locator.INSTANCE.locate(context, SSLCertificatesManager.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final SafetyDependenciesProvider provideSafetyDependenciesProvider() {
        return new SafetyDependenciesProvider();
    }

    @Provides
    @Singleton
    @NotNull
    public final SessionEventLoggerCounter provideSessionEventLoggerCounter(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNull(defaultSharedPreferences);
        return new SessionEventLoggerCounterImpl(defaultSharedPreferences);
    }

    @Provides
    @NotNull
    public final ShareMailAnalytics provideShareMailAnalytics(@NotNull ConfigurationRepository configurationRepository, @NotNull MailAnalyticsKt analytics) {
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new ShareMailAnalyticsImpl(analytics, configurationRepository.getConfiguration().getNeedSendAnalytics());
    }

    @Provides
    @Singleton
    @NotNull
    public final SharedFolderApi provideSharedFolderApi(@NotNull @MailApiRetrofit Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "retrofit");
        Object objCreate = retrofit.create(SharedFolderApi.class);
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        return (SharedFolderApi) objCreate;
    }

    @Provides
    @NotNull
    public final SharedPreferences provideSharedPreferences(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        return defaultSharedPreferences;
    }

    @Provides
    @NotNull
    public final ShortcutUpdater provideShortcutUpdater(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ShortcutUpdaterImpl(context);
    }

    @Provides
    @NotNull
    public final ShrinkManager provideShrinkManager(@NotNull WorkScheduler workScheduler, @NotNull MailAppAnalytics analytics, @NotNull ConfigurationRepository configurationRepository, @NotNull ShrinkManagerStorage storage, @NotNull TimeProvider timeProvider) {
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        return new ShrinkManager(workScheduler, analytics, storage, configurationRepository.getConfiguration().getShrinkConfig(), timeProvider);
    }

    @Provides
    @NotNull
    public final ShrinkManagerStorage provideShrinkManagerStorage(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources resources = context.getResources();
        Intrinsics.checkNotNull(defaultSharedPreferences);
        Intrinsics.checkNotNull(resources);
        return new ShrinkManagerSharedPrefStorage(defaultSharedPreferences, resources);
    }

    @Provides
    @Singleton
    @NotNull
    public final SimpleApiResultHandler provideSimpleApiResultHandler(@NotNull NoAuthHandler noAuthHandler) {
        Intrinsics.checkNotNullParameter(noAuthHandler, "noAuthHandler");
        return new SimpleApiResultHandler(noAuthHandler);
    }

    @Provides
    @Singleton
    @NotNull
    public final SnackbarOnEmailsConfiguration provideSnackbarOnEmailsConfiguration(@NotNull ConfigurationRepository configRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Configuration.ClipboardPlatesConfig.PlateOnEmailsConfig plateOnEmailsConfig = configRepository.getConfiguration().getClipboardPlatesConfig().getPlateOnEmailsConfig();
        return new SnackbarOnEmailsConfiguration(plateOnEmailsConfig.isEnabled(), plateOnEmailsConfig.getShowSeconds(), plateOnEmailsConfig.getEnabledActionsInOrder(), plateOnEmailsConfig.getShowCloseButton());
    }

    @Provides
    @NotNull
    public final SoundService provideSoundService(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SoundService soundServiceFrom = SoundService.from(context);
        Intrinsics.checkNotNullExpressionValue(soundServiceFrom, "from(...)");
        return soundServiceFrom;
    }

    @Provides
    @Singleton
    @NotNull
    public final StartupTypeChecker provideStartupTypeChecker() {
        return new ApplicationStartupTypeChecker();
    }

    @Provides
    @NotNull
    public final StringResolver provideStringResolver(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new StringResolverImpl(context);
    }

    @Provides
    @Singleton
    @NotNull
    public final SummarizeChunkInterceptor provideSummarizeChunkInterceptor() {
        return new SummarizeChunkInterceptor();
    }

    @Provides
    @Singleton
    @NotNull
    public final SurveyAnalytics provideSurveyAnalytics(@NotNull MailAppAnalytics mailAppAnalytics) {
        Intrinsics.checkNotNullParameter(mailAppAnalytics, "mailAppAnalytics");
        return new MailSurveyAnalytics(mailAppAnalytics);
    }

    @Provides
    @NotNull
    public final SurveyConfig provideSurveyConfig(@NotNull ConfigurationRepository configRepo, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        return createSurveyConfig(configRepo.getConfiguration().getCsatConfig(), sharedPreferences);
    }

    @Provides
    @Singleton
    @NotNull
    public final SurveyInteractor provideSurveyInteractor(@NotNull SurveyInteractorImpl surveyInteractor) {
        Intrinsics.checkNotNullParameter(surveyInteractor, "surveyInteractor");
        return surveyInteractor;
    }

    @Provides
    @Singleton
    @NotNull
    public final SurveyInteractorImpl provideSurveyInteractorImpl(@NotNull SurveyRepository surveyRepository, @NotNull OnResultListenerProvider onResultListenerProvider, @NotNull SurveyAnalytics analytics) {
        Intrinsics.checkNotNullParameter(surveyRepository, "surveyRepository");
        Intrinsics.checkNotNullParameter(onResultListenerProvider, "onResultListenerProvider");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        return new SurveyInteractorImpl(surveyRepository, onResultListenerProvider, analytics, AnalyticProjectProvider.INSTANCE.getProject());
    }

    @Provides
    @Singleton
    @NotNull
    public final OnResultListenerProvider provideSurveyInteractorProvider() {
        return new OnResultsListenerProviderImpl();
    }

    @Provides
    @NotNull
    public final CsatConfig provideSurveysListConfig(@NotNull ConfigurationRepository configRepo, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(sharedPreferences, "sharedPreferences");
        ArrayList arrayList = new ArrayList();
        Configuration.CsatListConfig csatListConfig = configRepo.getConfiguration().getCsatListConfig();
        if (csatListConfig.getCsatListConfig().isEmpty()) {
            Configuration.CsatConfig csatConfig = configRepo.getConfiguration().getCsatConfig();
            Long surveyId = csatConfig.getSurveyId();
            if (surveyId != null && surveyId.longValue() > -1 && (csatConfig.getShowAfterDays() != null || csatConfig.getRepeatAfterDays() != null)) {
                Integer showAfterDays = csatConfig.getShowAfterDays();
                if (showAfterDays == null) {
                    showAfterDays = csatConfig.getRepeatAfterDays();
                }
                Integer num = showAfterDays;
                Integer repeatAfterDays = csatConfig.getRepeatAfterDays();
                if (repeatAfterDays == null) {
                    repeatAfterDays = csatConfig.getShowAfterDays();
                }
                arrayList.add(new SurveyConfig(surveyId, null, num, repeatAfterDays, csatConfig.getWebviewUrl(), csatConfig.getTriggers(), null, sharedPreferences.getBoolean("always_show_survey", false), null, 322, null));
            }
        } else {
            Iterator<T> it = csatListConfig.getCsatListConfig().iterator();
            while (it.hasNext()) {
                arrayList.add(createSurveyConfig((Configuration.CsatConfig) it.next(), sharedPreferences));
            }
        }
        return new CsatConfig(csatListConfig.isSurveysAnswerCount(), csatListConfig.getQuarantineByShowDays(), csatListConfig.getQuarantineByAnswerDays(), csatListConfig.getQuarantineIds(), arrayList);
    }

    @Provides
    @NotNull
    public final SyncUseCase provideSyncUseCase(@ApplicationContext @NotNull Context context, @NotNull DataManager dataManger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataManger, "dataManger");
        return new SyncUseCaseImpl(context, dataManger);
    }

    @Provides
    @NotNull
    public final TimeProvider provideTimeProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return TimeUtils.Time.INSTANCE.from(context);
    }

    @Provides
    @NotNull
    public final TimeSpentStorage provideTimeSpentTrackerStorage(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (TimeSpentStorage) Locator.INSTANCE.from(context).locate(TimeSpentStorage.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final UrlConstructor provideUrlConstructor(@ApplicationContext @NotNull Context context, @NotNull SurveyConfig surveyConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surveyConfig, "surveyConfig");
        return new UrlConstructorImpl(context, surveyConfig.getWebviewUrl());
    }

    @Provides
    @UniversalLink
    @NotNull
    public final UrlParamsAnalyticsSender provideUrlParamsAnalyticsSender(@NotNull AnalyticTracker analyticTracker, @NotNull final ConfigurationRepository configurationRepository, @AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(analyticTracker, "analyticTracker");
        Intrinsics.checkNotNullParameter(configurationRepository, "configurationRepository");
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new UrlParamsAnalyticsSender(analyticTracker, new Function0() { // from class: ru.mail.sdk.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailSdkModule.provideUrlParamsAnalyticsSender$lambda$0(configurationRepository);
            }
        }, logger);
    }

    @Provides
    @Singleton
    @UseSetupAsyncService
    public final boolean provideUseSetupAsyncService() {
        return SdkUtils.hasQ();
    }

    @Provides
    @Singleton
    @NotNull
    public final UserBoundByVKIDDelegate provideUserBoundByVIDDelegate() {
        return new UserBoundByVKIDDelegate();
    }

    @Provides
    @Singleton
    @NotNull
    public final VKConnectSignInDelegate provideVkSignInDelegate() {
        return new VKConnectSignInDelegate();
    }

    @Provides
    @NotNull
    public final WebViewErrorAnalytics provideWebViewErrorAnalytics(@NotNull WebViewErrorAnalyticsImpl impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        return impl;
    }

    @Provides
    @Singleton
    @NotNull
    public final WebViewWorkaroundManager provideWebViewWorkaroundManager(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new WebViewWorkaroundManager(context);
    }

    @Provides
    @NotNull
    public final WorkScheduler provideWorkScheduler(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (WorkScheduler) Locator.INSTANCE.from(context).locate(WorkScheduler.class);
    }

    @Provides
    @Singleton
    @NotNull
    public final CreateMailByClipboardConfig providerCreateMailByClipboardConfig(@NotNull ConfigurationRepository configRepository) {
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Configuration.ClipboardPlatesConfig.ClipboardActions.CreateLetterAction createLetter = configRepository.getConfiguration().getClipboardPlatesConfig().getActions().getCreateLetter();
        return new CreateMailByClipboardConfig(createLetter.getActionId(), createLetter.getEmailRegex(), createLetter.getSpecialCharactersToRemove());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Provides
    @CoreOkHttpClient
    @NotNull
    public final OkHttpClient.Builder providesCoreOkHttpClient(@NotNull OkHttpConfig okHttpConfig, @AppLogger @NotNull final Logger logger, @NotNull final Lazy<MailAnalyticsKt> mailAnalyticsProvider, @NotNull NetworkErrorAnalyticsInterceptor networkErrorAnalyticsInterceptor) {
        Intrinsics.checkNotNullParameter(okHttpConfig, "okHttpConfig");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(mailAnalyticsProvider, "mailAnalyticsProvider");
        Intrinsics.checkNotNullParameter(networkErrorAnalyticsInterceptor, "networkErrorAnalyticsInterceptor");
        Long lValueOf = Long.valueOf(okHttpConfig.getPingInterval());
        ProxySelector proxySelector = null;
        Object[] objArr = 0;
        if (lValueOf.longValue() >= okHttpConfig.getConnectionPool().getKeepAliveDuration()) {
            lValueOf = null;
        }
        long jLongValue = lValueOf != null ? lValueOf.longValue() : 0L;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        int maxIdleConnections = okHttpConfig.getConnectionPool().getMaxIdleConnections();
        long keepAliveDuration = okHttpConfig.getConnectionPool().getKeepAliveDuration();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        OkHttpClient.Builder builderProxySelector = builder.connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, timeUnit)).connectTimeout(okHttpConfig.getConnectTimeout(), timeUnit).readTimeout(okHttpConfig.getReadTimeout(), timeUnit).writeTimeout(okHttpConfig.getWriteTimeout(), timeUnit).pingInterval(jLongValue, timeUnit).proxySelector(new SafeProxySelector(proxySelector, 1, objArr == true ? 1 : 0));
        if (okHttpConfig.getUserRetryInterceptor()) {
            builderProxySelector.addInterceptor(new RetryInterceptor(null, 0, 3, null));
        }
        OkHttpClient.Builder builderAddNetworkInterceptor = builderProxySelector.addNetworkInterceptor(networkErrorAnalyticsInterceptor);
        if (okHttpConfig.getUseLoggingListener()) {
            builderAddNetworkInterceptor.eventListener(new LoggingEventListener(new Function1() { // from class: ru.mail.sdk.b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MailSdkModule.providesCoreOkHttpClient$lambda$0$2$0(logger, (String) obj);
                }
            }));
        }
        if (okHttpConfig.getUseDnsCacheCleaner()) {
            builderAddNetworkInterceptor.dns(new DnsWithCacheClear(new DnsWithCacheClear.DnsLookupRetryResultCallback() { // from class: ru.mail.sdk.c
                @Override // ru.mail.network.dns.DnsWithCacheClear.DnsLookupRetryResultCallback
                public final void onDnsLookupResult(boolean z10) {
                    MailSdkModule.providesCoreOkHttpClient$lambda$0$2$1(mailAnalyticsProvider, z10);
                }
            }));
        }
        return builderAddNetworkInterceptor;
    }

    @Provides
    @ApplicationScope
    @NotNull
    @Singleton
    public final CoroutineScope providesCoroutineScope(@DefaultDispatcher @NotNull CoroutineDispatcher defaultDispatcher) {
        Intrinsics.checkNotNullParameter(defaultDispatcher, "defaultDispatcher");
        return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(defaultDispatcher));
    }

    @Provides
    @NotNull
    public final HostProviderWrapper providesHostProviderWrapper(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HostProviderWrapperImpl(context);
    }

    @Provides
    @NotNull
    public final OkHttpClient providesOkHttpClient(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ((NetworkServiceFactory) Locator.INSTANCE.from(context).locate(NetworkServiceFactory.class)).getOkHttpClient();
    }

    @Provides
    @Reusable
    @NotNull
    public final OkHttpConfig providesOkHttpConfig(@NotNull ConfigRetriever configRetriever) {
        Intrinsics.checkNotNullParameter(configRetriever, "configRetriever");
        return (OkHttpConfig) configRetriever.getSerializable("ok_http", Reflection.getOrCreateKotlinClass(OkHttpConfig.class));
    }

    @Provides
    @Singleton
    @NotNull
    public final TimeTracker.Factory timeTrackerFactory(@NotNull final Configuration configuration, @NotNull AnalyticTracker analyticTracker) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(analyticTracker, "analyticTracker");
        return new TimeTracker.Factory(new LoggerImpl("Timer", Log.INSTANCE.getLog("Timer")), analyticTracker, new Function1() { // from class: ru.mail.sdk.f
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MailSdkModule.timeTrackerFactory$lambda$0(configuration, (String) obj));
            }
        });
    }
}
