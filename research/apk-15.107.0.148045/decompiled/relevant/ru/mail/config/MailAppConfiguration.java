package ru.mail.config;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import github.ankushsachdeva.emojicon.StickersGroup;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlinx.serialization.SerializersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.json.JSONObject;
import ru.mail.ads.config.api.data.model.AdRemoteConfig;
import ru.mail.android_utils.connection.BandwidthConstants;
import ru.mail.appreview.config.InAppReviewStoreConfig;
import ru.mail.appupdate.AppUpdateConfig;
import ru.mail.authorizesdk.domain.models.NewAuthSdkConfig;
import ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig;
import ru.mail.calleridentification.CallerIdentificationConfig;
import ru.mail.cloud.domain.model.RedirectFromViewerToCloudConfig;
import ru.mail.config.actionfarmanalytics.ActionFarmAnalyticsConfig;
import ru.mail.config.cloud.CloudLinksNavigatorParamsConfig;
import ru.mail.config.cloudentryscreen.CloudEntryScreenConfig;
import ru.mail.config.dto.DTOAccountManagerAnalyticsMapper;
import ru.mail.config.dto.DTOAccountSettingsMapper;
import ru.mail.config.dto.DTOAnalyzeDataAgreementMapper;
import ru.mail.config.dto.DTOAppUpdateMapper;
import ru.mail.config.dto.DTOAppWallSectionsMapperWrapper;
import ru.mail.config.dto.DTOAppendingQueryParamsRulesMapper;
import ru.mail.config.dto.DTOBandwidthMapper;
import ru.mail.config.dto.DTOBarActionsMapper;
import ru.mail.config.dto.DTOBonusMapper;
import ru.mail.config.dto.DTOCalendarMapper;
import ru.mail.config.dto.DTOCalendarPlatesConfigMapper;
import ru.mail.config.dto.DTOCalendarTodoMapper;
import ru.mail.config.dto.DTOCalendarWidgetConfigMapper;
import ru.mail.config.dto.DTOCallInRegistrationSettingsMapper;
import ru.mail.config.dto.DTOCallUIRegistrationSettingsMapper;
import ru.mail.config.dto.DTOCategoriesMapper;
import ru.mail.config.dto.DTOCategoryChangeMapper;
import ru.mail.config.dto.DTOClipboardPlateConfigMapper;
import ru.mail.config.dto.DTOCloudMapper;
import ru.mail.config.dto.DTOCloudQuotaMapper;
import ru.mail.config.dto.DTOCloudSharedOverQuotaMapper;
import ru.mail.config.dto.DTOContactCardConfigMapper;
import ru.mail.config.dto.DTOCsatListMapper;
import ru.mail.config.dto.DTOCsatMapper;
import ru.mail.config.dto.DTOCsatTriggersMapper;
import ru.mail.config.dto.DTODarkThemeConfigMapper;
import ru.mail.config.dto.DTODeeplinkSmartReplyMapper;
import ru.mail.config.dto.DTODistributorMapper;
import ru.mail.config.dto.DTODrawablesMapper;
import ru.mail.config.dto.DTODynamicStringsMapper;
import ru.mail.config.dto.DTOFullscreenMenuItemPromoMapper;
import ru.mail.config.dto.DTOImapBannerMapper;
import ru.mail.config.dto.DTOInAppReviewFullConfigMapper;
import ru.mail.config.dto.DTOInternalApiUrlsMapper;
import ru.mail.config.dto.DTOLetterReminderConfigMapper;
import ru.mail.config.dto.DTOLicenseAgreementMapper;
import ru.mail.config.dto.DTOLinksReplacementRulesMapper;
import ru.mail.config.dto.DTOMailAppDeepLinkMapper;
import ru.mail.config.dto.DTOMailsListAttachPreviewsMapper;
import ru.mail.config.dto.DTOMassOperationsAnyFolderWithUnread;
import ru.mail.config.dto.DTOMassOperationsAnyFolderWithoutUnread;
import ru.mail.config.dto.DTOMassOperationsMapper;
import ru.mail.config.dto.DTOMassOperationsSearchWithUnread;
import ru.mail.config.dto.DTOMassOperationsSearchWithoutUnread;
import ru.mail.config.dto.DTOMediascopeMapper;
import ru.mail.config.dto.DTOMenuFabConfigMapper;
import ru.mail.config.dto.DTOMetaThreadMassOperationsMapper;
import ru.mail.config.dto.DTOMetaThreadsStatusMapper;
import ru.mail.config.dto.DTONewActionsMapper;
import ru.mail.config.dto.DTONewAuthSdkMapper;
import ru.mail.config.dto.DTONewAuthorizationSdkMapper;
import ru.mail.config.dto.DTONewMailClipboardMapper;
import ru.mail.config.dto.DTONotesMapper;
import ru.mail.config.dto.DTONotificationPromoPlateMapper;
import ru.mail.config.dto.DTONotificationSettingsMapper;
import ru.mail.config.dto.DTOOmicronPromoMapper;
import ru.mail.config.dto.DTOOpenInWebViewConfigMapper;
import ru.mail.config.dto.DTOPackageCheckerItemMapper;
import ru.mail.config.dto.DTOPatternMapper;
import ru.mail.config.dto.DTOPermittedCookiesMapper;
import ru.mail.config.dto.DTOPhishingConfigMapper;
import ru.mail.config.dto.DTOPlateMapperWrapper;
import ru.mail.config.dto.DTOPortalMapper;
import ru.mail.config.dto.DTOPrefetcherDelayMapper;
import ru.mail.config.dto.DTOPromoFeaturesMapper;
import ru.mail.config.dto.DTOPromoHighlightMapper;
import ru.mail.config.dto.DTOPulseConfigMapper;
import ru.mail.config.dto.DTOPushCategoryMapper;
import ru.mail.config.dto.DTOPushConfigurationMapper;
import ru.mail.config.dto.DTOQuickActionMapper;
import ru.mail.config.dto.DTORawConfiguration;
import ru.mail.config.dto.DTORelocationsAgreementMapper;
import ru.mail.config.dto.DTOScheduledSendMapper;
import ru.mail.config.dto.DTOSendHttpRequestAnalyticEventsFilterMapper;
import ru.mail.config.dto.DTOShareMailConfigMapper;
import ru.mail.config.dto.DTOSocialLoginMapper;
import ru.mail.config.dto.DTOSoundsMapper;
import ru.mail.config.dto.DTOStickersMapper;
import ru.mail.config.dto.DTOStoriesMapper;
import ru.mail.config.dto.DTOStringsMapper;
import ru.mail.config.dto.DTOSubscriptionConfigMapper;
import ru.mail.config.dto.DTOTechStatConfigMapper;
import ru.mail.config.dto.DTOTotalCleanFolderIdsMapper;
import ru.mail.config.dto.DTOTrustedUrlsMapper;
import ru.mail.config.dto.DTOUserThemeMapper;
import ru.mail.config.dto.DTOVkpnsPushSdkConfigMapper;
import ru.mail.config.dto.DTOWalletMapper;
import ru.mail.config.dto.DTOWebAppsMapper;
import ru.mail.config.dto.DTOWebConfigMapper;
import ru.mail.config.dto.DTOWelcomeLoginMapper;
import ru.mail.config.dto.DTOWriteToDevLoggingMapper;
import ru.mail.config.forbusinessmen.ReadVerificationForBusinessmenConfig;
import ru.mail.config.forbusinessmen.SendCancellationForBusinessmenConfig;
import ru.mail.config.freemium.FreemiumConfig;
import ru.mail.config.imap.promo.ImapPromoConfig;
import ru.mail.config.kotlett.KotlettUrlRedirectConfig;
import ru.mail.config.max.MaxRemoteConfig;
import ru.mail.config.overquota.OverquotaInfoSheetConfig;
import ru.mail.config.prettyemail.PrettyEmailConfiguration;
import ru.mail.config.prettyemail.promo.PrettyEmailPromoConfig;
import ru.mail.config.remoteconfig.OmicronConfigImpl;
import ru.mail.config.remoteconfig.PdfViewerConfig;
import ru.mail.config.util.ConfigSerializableDelegate;
import ru.mail.config.util.OmicronSerializationKt;
import ru.mail.data.cache.StringsMemcache;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.dogfooding.DogfoodingConfig;
import ru.mail.feature.cloudcleanup.config.CloudCleanupConfig;
import ru.mail.feature.family.impl.config.FamilySubscriptionRemoteConfigImpl;
import ru.mail.feature.tgmigration.impl.config.TgMigrationConfig;
import ru.mail.feature.tgmigration.impl.domain.model.RedirectToTelegramMigrationConfig;
import ru.mail.feature.tgmigration.impl.domain.model.ServicesBannerConfig;
import ru.mail.file.utils.encrypted.remoteconfig.CryptoAwareFileRemoteConfig;
import ru.mail.flexsettings.field.Field;
import ru.mail.jsscriptfetcher.JsScriptFetcherConfig;
import ru.mail.kit.auth.helpers.TokenExchangeConfig;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.letter.ai.writer.config.LetterAiWriterRemoteConfig;
import ru.mail.logic.content.BarPlace;
import ru.mail.logic.content.Distributor;
import ru.mail.logic.content.DrawableResEntry;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.logic.content.StringResEntry;
import ru.mail.logic.plates.ShowRule;
import ru.mail.mailapp.AnalyticsSender;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mediascope.MediascopeConfiguration;
import ru.mail.mytracker.MyTrackerConfig;
import ru.mail.network.networkchecker.NetworkCheckerConfig;
import ru.mail.omicron.data.ConfigurationData;
import ru.mail.portal.kit.config.PortalMailAppConfiguration;
import ru.mail.r7editor.api.R7EditPromoConfig;
import ru.mail.r7editor.api.R7OfficeConfig;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;
import ru.mail.releasefetcher.ReleaseFetcherConfig;
import ru.mail.setup.PortalMailAppConfigurationRetrieverImpl;
import ru.mail.subscription.billing.BillingConfiguration;
import ru.mail.subscription.model.PersonalizationConfig;
import ru.mail.theme.utils.DarkThemeConfig;
import ru.mail.ui.configuration.ConfigurationSettingsDelegate;
import ru.mail.ui.presentation.Plate;
import ru.mail.util.log.clogs.SharedCloudLoggerRemoteConfig;
import ru.mail.utils.TimeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000º\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r¢\u0006\u0004\b\u0011\u0010\u0012B1\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0011\u0010\u0013J\u0017\u0010å\u0005\u001a\u00030æ\u00052\n\u0010ç\u0005\u001a\u0005\u0018\u00010è\u0005H\u0096\u0002J\t\u0010é\u0005\u001a\u00020\u0017H\u0016J\n\u0010ê\u0005\u001a\u00030ë\u0005H\u0016Ja\u0010ì\u0005\u001a\u0011\u0012\u0005\u0012\u0003Hî\u0005\u0012\u0005\u0012\u0003Hï\u00050í\u0005\"\n\b\u0000\u0010î\u0005*\u00030è\u0005\"\f\b\u0001\u0010ï\u0005\u0018\u0001*\u0003Hî\u00052\u0007\u0010ð\u0005\u001a\u00020\u00172\u0010\b\b\u0010ñ\u0005\u001a\t\u0012\u0005\u0012\u0003Hï\u00050\r2\u0011\b\n\u0010ò\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\rH\u0082\bJN\u0010ó\u0005\u001a\u0011\u0012\u0005\u0012\u0003Hî\u0005\u0012\u0005\u0012\u0003Hï\u00050í\u0005\"\n\b\u0000\u0010î\u0005*\u00030è\u0005\"\f\b\u0001\u0010ï\u0005\u0018\u0001*\u0003Hî\u00052\u0007\u0010ð\u0005\u001a\u00020\u00172\u0010\b\b\u0010ñ\u0005\u001a\t\u0012\u0005\u0012\u0003Hï\u00050\rH\u0083\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020%8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u001b\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020*8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u001b\u001a\u0004\b+\u0010,R!\u0010.\u001a\b\u0012\u0004\u0012\u0002000/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\u001b\u001a\u0004\b1\u00102R!\u00104\u001a\b\u0012\u0004\u0012\u0002050/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b7\u0010\u001b\u001a\u0004\b6\u00102R\u001b\u00108\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u001b\u001a\u0004\b:\u0010;R\u001b\u0010=\u001a\u00020>8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\u001b\u001a\u0004\b?\u0010@R\u001b\u0010B\u001a\u00020C8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\u001b\u001a\u0004\bD\u0010ER\u001b\u0010G\u001a\u00020H8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\u001b\u001a\u0004\bI\u0010JR\u001b\u0010L\u001a\u00020M8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bP\u0010\u001b\u001a\u0004\bN\u0010OR!\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00170R8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bU\u0010\u001b\u001a\u0004\bS\u0010TR!\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00170/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bX\u0010\u001b\u001a\u0004\bW\u00102R!\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00170/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b[\u0010\u001b\u001a\u0004\bZ\u00102R!\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00170/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b^\u0010\u001b\u001a\u0004\b]\u00102R!\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00170/8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\ba\u0010\u001b\u001a\u0004\b`\u00102R\u001b\u0010b\u001a\u00020c8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\u001b\u001a\u0004\bd\u0010eR\u001b\u0010g\u001a\u00020h8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bk\u0010\u001b\u001a\u0004\bi\u0010jR\u001b\u0010l\u001a\u00020m8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bp\u0010\u001b\u001a\u0004\bn\u0010oR\u001b\u0010q\u001a\u00020r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bu\u0010\u001b\u001a\u0004\bs\u0010tR\u001b\u0010v\u001a\u00020w8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bz\u0010\u001b\u001a\u0004\bx\u0010yR\u001b\u0010{\u001a\u00020|8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u007f\u0010\u001b\u001a\u0004\b}\u0010~R \u0010\u0080\u0001\u001a\u00030\u0081\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0084\u0001\u0010\u001b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R \u0010\u0085\u0001\u001a\u00030\u0086\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0089\u0001\u0010\u001b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u008a\u0001\u001a\u00030\u008b\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u008e\u0001\u0010\u001b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R \u0010\u008f\u0001\u001a\u00030\u0090\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0093\u0001\u0010\u001b\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R \u0010\u0094\u0001\u001a\u00030\u0095\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0098\u0001\u0010\u001b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R \u0010\u0099\u0001\u001a\u00030\u009a\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u009d\u0001\u0010\u001b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R \u0010\u009e\u0001\u001a\u00030\u009f\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b¢\u0001\u0010\u001b\u001a\u0006\b \u0001\u0010¡\u0001R \u0010£\u0001\u001a\u00030¤\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b§\u0001\u0010\u001b\u001a\u0006\b¥\u0001\u0010¦\u0001R!\u0010¨\u0001\u001a\u00030©\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¬\u0001\u0010\u00ad\u0001\u001a\u0006\bª\u0001\u0010«\u0001R!\u0010®\u0001\u001a\u00030¯\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b²\u0001\u0010\u00ad\u0001\u001a\u0006\b°\u0001\u0010±\u0001R!\u0010³\u0001\u001a\u00030´\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b·\u0001\u0010\u00ad\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001R!\u0010¸\u0001\u001a\u00030¹\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¼\u0001\u0010\u00ad\u0001\u001a\u0006\bº\u0001\u0010»\u0001R!\u0010½\u0001\u001a\u00030¾\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÁ\u0001\u0010\u00ad\u0001\u001a\u0006\b¿\u0001\u0010À\u0001R!\u0010Â\u0001\u001a\u00030Ã\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0001\u0010\u00ad\u0001\u001a\u0006\bÄ\u0001\u0010Å\u0001R!\u0010Ç\u0001\u001a\u00030È\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bË\u0001\u0010\u00ad\u0001\u001a\u0006\bÉ\u0001\u0010Ê\u0001R!\u0010Ì\u0001\u001a\u00030Í\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÐ\u0001\u0010\u00ad\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001R!\u0010Ñ\u0001\u001a\u00030Ò\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÕ\u0001\u0010\u00ad\u0001\u001a\u0006\bÓ\u0001\u0010Ô\u0001R!\u0010Ö\u0001\u001a\u00030×\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÚ\u0001\u0010\u00ad\u0001\u001a\u0006\bØ\u0001\u0010Ù\u0001R!\u0010Û\u0001\u001a\u00030Ü\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bß\u0001\u0010\u00ad\u0001\u001a\u0006\bÝ\u0001\u0010Þ\u0001R!\u0010à\u0001\u001a\u00030á\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bä\u0001\u0010\u00ad\u0001\u001a\u0006\bâ\u0001\u0010ã\u0001R!\u0010å\u0001\u001a\u00030æ\u00018VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bé\u0001\u0010\u00ad\u0001\u001a\u0006\bç\u0001\u0010è\u0001R \u0010ê\u0001\u001a\u00030ë\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bî\u0001\u0010\u001b\u001a\u0006\bì\u0001\u0010í\u0001R \u0010ï\u0001\u001a\u00030ð\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bó\u0001\u0010\u001b\u001a\u0006\bñ\u0001\u0010ò\u0001R \u0010ô\u0001\u001a\u00030õ\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bø\u0001\u0010\u001b\u001a\u0006\bö\u0001\u0010÷\u0001R \u0010ù\u0001\u001a\u00030ú\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bý\u0001\u0010\u001b\u001a\u0006\bû\u0001\u0010ü\u0001R \u0010þ\u0001\u001a\u00030ÿ\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0082\u0002\u0010\u001b\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002R \u0010\u0083\u0002\u001a\u00030\u0084\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0087\u0002\u0010\u001b\u001a\u0006\b\u0085\u0002\u0010\u0086\u0002R \u0010\u0088\u0002\u001a\u00030\u0089\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u008c\u0002\u0010\u001b\u001a\u0006\b\u008a\u0002\u0010\u008b\u0002R \u0010\u008d\u0002\u001a\u00030\u008e\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0091\u0002\u0010\u001b\u001a\u0006\b\u008f\u0002\u0010\u0090\u0002R \u0010\u0092\u0002\u001a\u00030\u0093\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0096\u0002\u0010\u001b\u001a\u0006\b\u0094\u0002\u0010\u0095\u0002R%\u0010\u0097\u0002\u001a\t\u0012\u0005\u0012\u00030\u0098\u00020/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u009a\u0002\u0010\u001b\u001a\u0005\b\u0099\u0002\u00102R \u0010\u009b\u0002\u001a\u00030\u009c\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u009f\u0002\u0010\u001b\u001a\u0006\b\u009d\u0002\u0010\u009e\u0002R%\u0010 \u0002\u001a\t\u0012\u0005\u0012\u00030¡\u00020/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b£\u0002\u0010\u001b\u001a\u0005\b¢\u0002\u00102R!\u0010¤\u0002\u001a\u00030¥\u00028VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¨\u0002\u0010\u00ad\u0001\u001a\u0006\b¦\u0002\u0010§\u0002R'\u0010©\u0002\u001a\n\u0012\u0005\u0012\u00030«\u00020ª\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b®\u0002\u0010\u001b\u001a\u0006\b¬\u0002\u0010\u00ad\u0002R \u0010¯\u0002\u001a\u00030°\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b³\u0002\u0010\u001b\u001a\u0006\b±\u0002\u0010²\u0002R'\u0010´\u0002\u001a\n\u0012\u0005\u0012\u00030µ\u00020ª\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b·\u0002\u0010\u001b\u001a\u0006\b¶\u0002\u0010\u00ad\u0002R'\u0010¸\u0002\u001a\n\u0012\u0005\u0012\u00030¹\u00020ª\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b»\u0002\u0010\u001b\u001a\u0006\bº\u0002\u0010\u00ad\u0002R%\u0010¼\u0002\u001a\t\u0012\u0005\u0012\u00030½\u00020/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¿\u0002\u0010\u001b\u001a\u0005\b¾\u0002\u00102R%\u0010À\u0002\u001a\t\u0012\u0005\u0012\u00030Á\u00020/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bÃ\u0002\u0010\u001b\u001a\u0005\bÂ\u0002\u00102R \u0010Ä\u0002\u001a\u00030Å\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÈ\u0002\u0010\u001b\u001a\u0006\bÆ\u0002\u0010Ç\u0002R \u0010É\u0002\u001a\u00030Ê\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÍ\u0002\u0010\u001b\u001a\u0006\bË\u0002\u0010Ì\u0002R,\u0010Î\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002050Ï\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÒ\u0002\u0010\u001b\u001a\u0006\bÐ\u0002\u0010Ñ\u0002R,\u0010Ó\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u0002050Ï\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÕ\u0002\u0010\u001b\u001a\u0006\bÔ\u0002\u0010Ñ\u0002R-\u0010Ö\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0005\u0012\u00030×\u00020Ï\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÙ\u0002\u0010\u001b\u001a\u0006\bØ\u0002\u0010Ñ\u0002R'\u0010Ú\u0002\u001a\n\u0012\u0005\u0012\u00030Û\u00020ª\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÝ\u0002\u0010\u001b\u001a\u0006\bÜ\u0002\u0010\u00ad\u0002R \u0010Þ\u0002\u001a\u00030ß\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bâ\u0002\u0010\u001b\u001a\u0006\bà\u0002\u0010á\u0002R%\u0010ã\u0002\u001a\t\u0012\u0005\u0012\u00030ä\u00020/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bæ\u0002\u0010\u001b\u001a\u0005\bå\u0002\u00102R \u0010ç\u0002\u001a\u00030è\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bë\u0002\u0010\u001b\u001a\u0006\bé\u0002\u0010ê\u0002R \u0010ì\u0002\u001a\u00030í\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bð\u0002\u0010\u001b\u001a\u0006\bî\u0002\u0010ï\u0002R \u0010ñ\u0002\u001a\u00030ò\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bõ\u0002\u0010\u001b\u001a\u0006\bó\u0002\u0010ô\u0002R \u0010ö\u0002\u001a\u00030÷\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bú\u0002\u0010\u001b\u001a\u0006\bø\u0002\u0010ù\u0002R \u0010û\u0002\u001a\u00030ü\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÿ\u0002\u0010\u001b\u001a\u0006\bý\u0002\u0010þ\u0002R.\u0010\u0080\u0003\u001a\u0011\u0012\u0005\u0012\u00030\u0081\u0003\u0012\u0005\u0012\u00030\u0082\u00030Ï\u00028VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0084\u0003\u0010\u001b\u001a\u0006\b\u0083\u0003\u0010Ñ\u0002R \u0010\u0085\u0003\u001a\u00030\u0086\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0089\u0003\u0010\u001b\u001a\u0006\b\u0087\u0003\u0010\u0088\u0003R%\u0010\u008a\u0003\u001a\t\u0012\u0005\u0012\u00030\u008b\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u008d\u0003\u0010\u001b\u001a\u0005\b\u008c\u0003\u00102R%\u0010\u008e\u0003\u001a\t\u0012\u0005\u0012\u00030\u008f\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u0091\u0003\u0010\u001b\u001a\u0005\b\u0090\u0003\u00102R \u0010\u0092\u0003\u001a\u00030\u0093\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0096\u0003\u0010\u001b\u001a\u0006\b\u0094\u0003\u0010\u0095\u0003R%\u0010\u0097\u0003\u001a\t\u0012\u0005\u0012\u00030\u0098\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u009a\u0003\u0010\u001b\u001a\u0005\b\u0099\u0003\u00102R \u0010\u009b\u0003\u001a\u00030\u009c\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u009f\u0003\u0010\u001b\u001a\u0006\b\u009d\u0003\u0010\u009e\u0003R%\u0010 \u0003\u001a\t\u0012\u0005\u0012\u00030¡\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b£\u0003\u0010\u001b\u001a\u0005\b¢\u0003\u00102R \u0010¤\u0003\u001a\u00030¥\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b¨\u0003\u0010\u001b\u001a\u0006\b¦\u0003\u0010§\u0003R%\u0010©\u0003\u001a\t\u0012\u0005\u0012\u00030ª\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¬\u0003\u0010\u001b\u001a\u0005\b«\u0003\u00102R%\u0010\u00ad\u0003\u001a\t\u0012\u0005\u0012\u00030ª\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¯\u0003\u0010\u001b\u001a\u0005\b®\u0003\u00102R \u0010°\u0003\u001a\u00030±\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b´\u0003\u0010\u001b\u001a\u0006\b²\u0003\u0010³\u0003R%\u0010µ\u0003\u001a\t\u0012\u0005\u0012\u00030¶\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¸\u0003\u0010\u001b\u001a\u0005\b·\u0003\u00102R%\u0010¹\u0003\u001a\t\u0012\u0005\u0012\u00030º\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¼\u0003\u0010\u001b\u001a\u0005\b»\u0003\u00102R\u0018\u0010½\u0003\u001a\u0004\u0018\u00010\u0017X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b¾\u0003\u0010\u0019R\u0018\u0010¿\u0003\u001a\u0004\u0018\u00010\u0017X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\bÀ\u0003\u0010\u0019R\u001f\u0010Á\u0003\u001a\u0002058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÄ\u0003\u0010\u001b\u001a\u0006\bÂ\u0003\u0010Ã\u0003R\u001f\u0010Å\u0003\u001a\u0002058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÇ\u0003\u0010\u001b\u001a\u0006\bÆ\u0003\u0010Ã\u0003R\u001f\u0010È\u0003\u001a\u0002058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÊ\u0003\u0010\u001b\u001a\u0006\bÉ\u0003\u0010Ã\u0003R%\u0010Ë\u0003\u001a\t\u0012\u0005\u0012\u00030Ì\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bÎ\u0003\u0010\u001b\u001a\u0005\bÍ\u0003\u00102R \u0010Ï\u0003\u001a\u00030Ð\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÓ\u0003\u0010\u001b\u001a\u0006\bÑ\u0003\u0010Ò\u0003R \u0010Ô\u0003\u001a\u00030Õ\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bØ\u0003\u0010\u001b\u001a\u0006\bÖ\u0003\u0010×\u0003R \u0010Ù\u0003\u001a\u00030Õ\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÛ\u0003\u0010\u001b\u001a\u0006\bÚ\u0003\u0010×\u0003R \u0010Ü\u0003\u001a\u00030Õ\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÞ\u0003\u0010\u001b\u001a\u0006\bÝ\u0003\u0010×\u0003R \u0010ß\u0003\u001a\u00030Õ\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bá\u0003\u0010\u001b\u001a\u0006\bà\u0003\u0010×\u0003R!\u0010â\u0003\u001a\u00030ã\u00038VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bæ\u0003\u0010\u00ad\u0001\u001a\u0006\bä\u0003\u0010å\u0003R!\u0010ç\u0003\u001a\u00030è\u00038VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bë\u0003\u0010\u00ad\u0001\u001a\u0006\bé\u0003\u0010ê\u0003R!\u0010ì\u0003\u001a\u00030í\u00038VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bð\u0003\u0010\u00ad\u0001\u001a\u0006\bî\u0003\u0010ï\u0003R%\u0010ñ\u0003\u001a\t\u0012\u0005\u0012\u00030ò\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bô\u0003\u0010\u001b\u001a\u0005\bó\u0003\u00102R \u0010õ\u0003\u001a\u00030ö\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bù\u0003\u0010\u001b\u001a\u0006\b÷\u0003\u0010ø\u0003R \u0010ú\u0003\u001a\u00030û\u00038VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bþ\u0003\u0010\u001b\u001a\u0006\bü\u0003\u0010ý\u0003R \u0010ÿ\u0003\u001a\u00030\u0080\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0083\u0004\u0010\u001b\u001a\u0006\b\u0081\u0004\u0010\u0082\u0004R \u0010\u0084\u0004\u001a\u00030\u0085\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0088\u0004\u0010\u001b\u001a\u0006\b\u0086\u0004\u0010\u0087\u0004R \u0010\u0089\u0004\u001a\u00030\u008a\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u008d\u0004\u0010\u001b\u001a\u0006\b\u008b\u0004\u0010\u008c\u0004R%\u0010\u008e\u0004\u001a\t\u0012\u0005\u0012\u00030\u008f\u00040/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u0091\u0004\u0010\u001b\u001a\u0005\b\u0090\u0004\u00102R%\u0010\u0092\u0004\u001a\t\u0012\u0005\u0012\u00030\u0093\u00040/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b\u0095\u0004\u0010\u001b\u001a\u0005\b\u0094\u0004\u00102R!\u0010\u0096\u0004\u001a\u00030\u0097\u00048VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b\u009a\u0004\u0010\u00ad\u0001\u001a\u0006\b\u0098\u0004\u0010\u0099\u0004R!\u0010\u009b\u0004\u001a\u00030\u009c\u00048VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b\u009f\u0004\u0010\u00ad\u0001\u001a\u0006\b\u009d\u0004\u0010\u009e\u0004R!\u0010 \u0004\u001a\u00030¡\u00048VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¤\u0004\u0010\u00ad\u0001\u001a\u0006\b¢\u0004\u0010£\u0004R%\u0010¥\u0004\u001a\t\u0012\u0005\u0012\u00030¦\u00040/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\b¨\u0004\u0010\u001b\u001a\u0005\b§\u0004\u00102R \u0010©\u0004\u001a\u00030ª\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u00ad\u0004\u0010\u001b\u001a\u0006\b«\u0004\u0010¬\u0004R \u0010®\u0004\u001a\u00030¯\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b²\u0004\u0010\u001b\u001a\u0006\b°\u0004\u0010±\u0004R \u0010³\u0004\u001a\u00030´\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b·\u0004\u0010\u001b\u001a\u0006\bµ\u0004\u0010¶\u0004R \u0010¸\u0004\u001a\u00030¹\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b¼\u0004\u0010\u001b\u001a\u0006\bº\u0004\u0010»\u0004R \u0010½\u0004\u001a\u00030¾\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÁ\u0004\u0010\u001b\u001a\u0006\b¿\u0004\u0010À\u0004R%\u0010Â\u0004\u001a\t\u0012\u0005\u0012\u00030Ã\u00040R8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bÅ\u0004\u0010\u001b\u001a\u0005\bÄ\u0004\u0010TR%\u0010Æ\u0004\u001a\t\u0012\u0005\u0012\u00030ª\u00030/8VX\u0096\u0084\u0002¢\u0006\u000e\n\u0005\bÈ\u0004\u0010\u001b\u001a\u0005\bÇ\u0004\u00102R \u0010É\u0004\u001a\u00030Ê\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÍ\u0004\u0010\u001b\u001a\u0006\bË\u0004\u0010Ì\u0004R \u0010Î\u0004\u001a\u00030Ï\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÒ\u0004\u0010\u001b\u001a\u0006\bÐ\u0004\u0010Ñ\u0004R \u0010Ó\u0004\u001a\u00030Ô\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b×\u0004\u0010\u001b\u001a\u0006\bÕ\u0004\u0010Ö\u0004R \u0010Ø\u0004\u001a\u00030Ù\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bÜ\u0004\u0010\u001b\u001a\u0006\bÚ\u0004\u0010Û\u0004R(\u0010Ý\u0004\u001a\u00030Þ\u00048VX\u0097\u0084\u0002¢\u0006\u0017\n\u0005\bã\u0004\u0010\u001b\u0012\u0006\bß\u0004\u0010à\u0004\u001a\u0006\bá\u0004\u0010â\u0004R \u0010ä\u0004\u001a\u00030å\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bè\u0004\u0010\u001b\u001a\u0006\bæ\u0004\u0010ç\u0004R\u0018\u0010é\u0004\u001a\u00030ê\u0004X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bë\u0004\u0010ì\u0004R \u0010í\u0004\u001a\u00030î\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bñ\u0004\u0010\u001b\u001a\u0006\bï\u0004\u0010ð\u0004R \u0010ò\u0004\u001a\u00030ó\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bö\u0004\u0010\u001b\u001a\u0006\bô\u0004\u0010õ\u0004R \u0010÷\u0004\u001a\u00030ø\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\bû\u0004\u0010\u001b\u001a\u0006\bù\u0004\u0010ú\u0004R \u0010ü\u0004\u001a\u00030ý\u00048VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0080\u0005\u0010\u001b\u001a\u0006\bþ\u0004\u0010ÿ\u0004R \u0010\u0081\u0005\u001a\u00030\u0082\u00058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0085\u0005\u0010\u001b\u001a\u0006\b\u0083\u0005\u0010\u0084\u0005R \u0010\u0086\u0005\u001a\u00030\u0087\u00058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u008a\u0005\u0010\u001b\u001a\u0006\b\u0088\u0005\u0010\u0089\u0005R \u0010\u008b\u0005\u001a\u00030\u008c\u00058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u008f\u0005\u0010\u001b\u001a\u0006\b\u008d\u0005\u0010\u008e\u0005R \u0010\u0090\u0005\u001a\u00030\u0091\u00058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0094\u0005\u0010\u001b\u001a\u0006\b\u0092\u0005\u0010\u0093\u0005R \u0010\u0095\u0005\u001a\u00030\u0096\u00058VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b\u0099\u0005\u0010\u001b\u001a\u0006\b\u0097\u0005\u0010\u0098\u0005R!\u0010\u009a\u0005\u001a\u00030\u009b\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b\u009e\u0005\u0010\u00ad\u0001\u001a\u0006\b\u009c\u0005\u0010\u009d\u0005R!\u0010\u009f\u0005\u001a\u00030 \u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b£\u0005\u0010\u00ad\u0001\u001a\u0006\b¡\u0005\u0010¢\u0005R!\u0010¤\u0005\u001a\u00030¥\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¨\u0005\u0010\u00ad\u0001\u001a\u0006\b¦\u0005\u0010§\u0005R!\u0010©\u0005\u001a\u00030ª\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b\u00ad\u0005\u0010\u00ad\u0001\u001a\u0006\b«\u0005\u0010¬\u0005R!\u0010®\u0005\u001a\u00030¯\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b²\u0005\u0010\u00ad\u0001\u001a\u0006\b°\u0005\u0010±\u0005R!\u0010³\u0005\u001a\u00030´\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b·\u0005\u0010\u00ad\u0001\u001a\u0006\bµ\u0005\u0010¶\u0005R!\u0010¸\u0005\u001a\u00030¹\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\b¼\u0005\u0010\u00ad\u0001\u001a\u0006\bº\u0005\u0010»\u0005R!\u0010½\u0005\u001a\u00030¾\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÁ\u0005\u0010\u00ad\u0001\u001a\u0006\b¿\u0005\u0010À\u0005R!\u0010Â\u0005\u001a\u00030Ã\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0005\u0010\u00ad\u0001\u001a\u0006\bÄ\u0005\u0010Å\u0005R!\u0010Ç\u0005\u001a\u00030È\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bË\u0005\u0010\u00ad\u0001\u001a\u0006\bÉ\u0005\u0010Ê\u0005R!\u0010Ì\u0005\u001a\u00030Í\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÐ\u0005\u0010\u00ad\u0001\u001a\u0006\bÎ\u0005\u0010Ï\u0005R!\u0010Ñ\u0005\u001a\u00030Ò\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÕ\u0005\u0010\u00ad\u0001\u001a\u0006\bÓ\u0005\u0010Ô\u0005R!\u0010Ö\u0005\u001a\u00030×\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bÚ\u0005\u0010\u00ad\u0001\u001a\u0006\bØ\u0005\u0010Ù\u0005R!\u0010Û\u0005\u001a\u00030Ü\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bß\u0005\u0010\u00ad\u0001\u001a\u0006\bÝ\u0005\u0010Þ\u0005R!\u0010à\u0005\u001a\u00030á\u00058VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bä\u0005\u0010\u00ad\u0001\u001a\u0006\bâ\u0005\u0010ã\u0005¨\u0006ô\u0005"}, d2 = {"Lru/mail/config/MailAppConfiguration;", "Lru/mail/config/ConfigurationWithRawData;", "dtoConfiguration", "Lru/mail/config/dto/DTORawConfiguration;", "configurationData", "Lru/mail/omicron/data/ConfigurationData;", "configurationSettingsDelegate", "Lru/mail/ui/configuration/ConfigurationSettingsDelegate;", "storageProvider", "Lru/mail/config/StorageProvider;", "analyticsSender", "Lru/mail/mailapp/AnalyticsSender;", "r7OfficeConfigProvider", "Lkotlin/Function0;", "Lru/mail/r7editor/api/R7OfficeConfig;", "r7EditPromoConfigProvider", "Lru/mail/r7editor/api/R7EditPromoConfig;", "<init>", "(Lru/mail/config/dto/DTORawConfiguration;Lru/mail/omicron/data/ConfigurationData;Lru/mail/ui/configuration/ConfigurationSettingsDelegate;Lru/mail/config/StorageProvider;Lru/mail/mailapp/AnalyticsSender;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "(Lru/mail/config/dto/DTORawConfiguration;Lru/mail/omicron/data/ConfigurationData;Lru/mail/ui/configuration/ConfigurationSettingsDelegate;Lru/mail/config/StorageProvider;Lru/mail/mailapp/AnalyticsSender;)V", "getDtoConfiguration", "()Lru/mail/config/dto/DTORawConfiguration;", "stringConfig", "", "getStringConfig", "()Ljava/lang/String;", "stringConfig$delegate", "Lkotlin/Lazy;", "config", "Lru/mail/mailapp/DTOConfiguration$Config;", "getConfig", "()Lru/mail/mailapp/DTOConfiguration$Config;", "configurationDataObject", "Lorg/json/JSONObject;", "getConfigurationDataObject", "()Lorg/json/JSONObject;", "configurationDataFiled", "Lru/mail/flexsettings/field/Field;", "getConfigurationDataFiled", "()Lru/mail/flexsettings/field/Field;", "configurationDataFiled$delegate", "licenseAgreementConfig", "Lru/mail/config/Configuration$LicenseAgreementConfig;", "getLicenseAgreementConfig", "()Lru/mail/config/Configuration$LicenseAgreementConfig;", "licenseAgreementConfig$delegate", "relocationAgreementConfigs", "", "Lru/mail/config/Configuration$RelocationAgreementConfig;", "getRelocationAgreementConfigs", "()Ljava/util/List;", "relocationAgreementConfigs$delegate", "sendHttpRequestAnalyticEventsFilter", "Ljava/util/regex/Pattern;", "getSendHttpRequestAnalyticEventsFilter", "sendHttpRequestAnalyticEventsFilter$delegate", "vkpnsHostSdk", "Lru/mail/config/Configuration$VkpnsHostSdk;", "getVkpnsHostSdk", "()Lru/mail/config/Configuration$VkpnsHostSdk;", "vkpnsHostSdk$delegate", "darkThemeConfig", "Lru/mail/theme/utils/DarkThemeConfig;", "getDarkThemeConfig", "()Lru/mail/theme/utils/DarkThemeConfig;", "darkThemeConfig$delegate", "userThemeData", "Lru/mail/config/Configuration$UserThemeData;", "getUserThemeData", "()Lru/mail/config/Configuration$UserThemeData;", "userThemeData$delegate", "stories", "Lru/mail/config/Configuration$Stories;", "getStories", "()Lru/mail/config/Configuration$Stories;", "stories$delegate", "subscription", "Lru/mail/config/Configuration$SubscriptionConfig;", "getSubscription", "()Lru/mail/config/Configuration$SubscriptionConfig;", "subscription$delegate", "enabledAssertions", "", "getEnabledAssertions", "()Ljava/util/Set;", "enabledAssertions$delegate", "adDomains", "getAdDomains", "adDomains$delegate", "liberoDomains", "getLiberoDomains", "liberoDomains$delegate", "virgilioDomains", "getVirgilioDomains", "virgilioDomains$delegate", "showQuotaRegions", "getShowQuotaRegions", "showQuotaRegions$delegate", "omicronPromoSettings", "Lru/mail/config/Configuration$OmicronPromoSettings;", "getOmicronPromoSettings", "()Lru/mail/config/Configuration$OmicronPromoSettings;", "omicronPromoSettings$delegate", MailMessageContent.COL_NAME_SUMMARIZE, "Lru/mail/config/Configuration$Summarize;", "getSummarize", "()Lru/mail/config/Configuration$Summarize;", "summarize$delegate", "huaweiWebViewErrorConfig", "Lru/mail/config/Configuration$HuaweiWebViewErrorConfig;", "getHuaweiWebViewErrorConfig", "()Lru/mail/config/Configuration$HuaweiWebViewErrorConfig;", "huaweiWebViewErrorConfig$delegate", "xmailMigrationEntryPointExp", "Lru/mail/config/Configuration$XmailMigrationEntryPointExp;", "getXmailMigrationEntryPointExp", "()Lru/mail/config/Configuration$XmailMigrationEntryPointExp;", "xmailMigrationEntryPointExp$delegate", "hitmanConfig", "Lru/mail/config/Configuration$HitmanConfig;", "getHitmanConfig", "()Lru/mail/config/Configuration$HitmanConfig;", "hitmanConfig$delegate", "dogfoodingConfig", "Lru/mail/dogfooding/DogfoodingConfig;", "getDogfoodingConfig", "()Lru/mail/dogfooding/DogfoodingConfig;", "dogfoodingConfig$delegate", "releaseFetcherConfig", "Lru/mail/releasefetcher/ReleaseFetcherConfig;", "getReleaseFetcherConfig", "()Lru/mail/releasefetcher/ReleaseFetcherConfig;", "releaseFetcherConfig$delegate", "retrofitConfig", "Lru/mail/config/Configuration$RetrofitConfig;", "getRetrofitConfig", "()Lru/mail/config/Configuration$RetrofitConfig;", "retrofitConfig$delegate", "jsScriptFetchingConfig", "Lru/mail/jsscriptfetcher/JsScriptFetcherConfig;", "getJsScriptFetchingConfig", "()Lru/mail/jsscriptfetcher/JsScriptFetcherConfig;", "jsScriptFetchingConfig$delegate", "readVerify", "Lru/mail/config/Configuration$ReadVerify;", "getReadVerify", "()Lru/mail/config/Configuration$ReadVerify;", "readVerify$delegate", "fastReplyConfig", "Lru/mail/config/FastReplyConfig;", "getFastReplyConfig", "()Lru/mail/config/FastReplyConfig;", "fastReplyConfig$delegate", "restoreAuthFlowConfig", "Lru/mail/config/Configuration$RestoreAuthFlowConfig;", "getRestoreAuthFlowConfig", "()Lru/mail/config/Configuration$RestoreAuthFlowConfig;", "restoreAuthFlowConfig$delegate", "ruStoreSdkConfig", "Lru/mail/config/Configuration$RuStoreSdkConfig;", "getRuStoreSdkConfig", "()Lru/mail/config/Configuration$RuStoreSdkConfig;", "ruStoreSdkConfig$delegate", "trustedMailConfig", "Lru/mail/config/Configuration$TrustedMailConfig;", "getTrustedMailConfig", "()Lru/mail/config/Configuration$TrustedMailConfig;", "trustedMailConfig$delegate", "freemiumConfig", "Lru/mail/config/freemium/FreemiumConfig;", "getFreemiumConfig", "()Lru/mail/config/freemium/FreemiumConfig;", "freemiumConfig$delegate", "Lru/mail/config/util/ConfigSerializableDelegate;", "adConfig", "Lru/mail/ads/config/api/data/model/AdRemoteConfig;", "getAdConfig", "()Lru/mail/ads/config/api/data/model/AdRemoteConfig;", "adConfig$delegate", "familySubscriptionConfig", "Lru/mail/feature/family/impl/config/FamilySubscriptionRemoteConfigImpl;", "getFamilySubscriptionConfig", "()Lru/mail/feature/family/impl/config/FamilySubscriptionRemoteConfigImpl;", "familySubscriptionConfig$delegate", "tgMigrationConfig", "Lru/mail/feature/tgmigration/impl/config/TgMigrationConfig;", "getTgMigrationConfig", "()Lru/mail/feature/tgmigration/impl/config/TgMigrationConfig;", "tgMigrationConfig$delegate", "maxConfig", "Lru/mail/config/max/MaxRemoteConfig;", "getMaxConfig", "()Lru/mail/config/max/MaxRemoteConfig;", "maxConfig$delegate", "cloudCleanupConfig", "Lru/mail/feature/cloudcleanup/config/CloudCleanupConfig;", "getCloudCleanupConfig", "()Lru/mail/feature/cloudcleanup/config/CloudCleanupConfig;", "cloudCleanupConfig$delegate", "cloudEntryScreenConfig", "Lru/mail/config/cloudentryscreen/CloudEntryScreenConfig;", "getCloudEntryScreenConfig", "()Lru/mail/config/cloudentryscreen/CloudEntryScreenConfig;", "cloudEntryScreenConfig$delegate", "redirectToTelegramMigrationConfig", "Lru/mail/feature/tgmigration/impl/domain/model/RedirectToTelegramMigrationConfig;", "getRedirectToTelegramMigrationConfig", "()Lru/mail/feature/tgmigration/impl/domain/model/RedirectToTelegramMigrationConfig;", "redirectToTelegramMigrationConfig$delegate", "servicesBannerConfig", "Lru/mail/feature/tgmigration/impl/domain/model/ServicesBannerConfig;", "getServicesBannerConfig", "()Lru/mail/feature/tgmigration/impl/domain/model/ServicesBannerConfig;", "servicesBannerConfig$delegate", "letterAiWriter", "Lru/mail/letter/ai/writer/config/LetterAiWriterRemoteConfig;", "getLetterAiWriter", "()Lru/mail/letter/ai/writer/config/LetterAiWriterRemoteConfig;", "letterAiWriter$delegate", "omicronConfig", "Lru/mail/config/remoteconfig/OmicronConfigImpl;", "getOmicronConfig", "()Lru/mail/config/remoteconfig/OmicronConfigImpl;", "omicronConfig$delegate", "billingConfiguration", "Lru/mail/subscription/billing/BillingConfiguration;", "getBillingConfiguration", "()Lru/mail/subscription/billing/BillingConfiguration;", "billingConfiguration$delegate", "personalizationConfig", "Lru/mail/subscription/model/PersonalizationConfig;", "getPersonalizationConfig", "()Lru/mail/subscription/model/PersonalizationConfig;", "personalizationConfig$delegate", "portal", "Lru/mail/config/Configuration$Portal;", "getPortal", "()Lru/mail/config/Configuration$Portal;", "portal$delegate", "writeToDevLogging", "Lru/mail/config/Configuration$WriteToDevLogging;", "getWriteToDevLogging", "()Lru/mail/config/Configuration$WriteToDevLogging;", "writeToDevLogging$delegate", "webViewConfig", "Lru/mail/config/Configuration$WebViewConfig;", "getWebViewConfig", "()Lru/mail/config/Configuration$WebViewConfig;", "webViewConfig$delegate", "calendarTodoConfig", "Lru/mail/config/Configuration$CalendarTodoConfig;", "getCalendarTodoConfig", "()Lru/mail/config/Configuration$CalendarTodoConfig;", "calendarTodoConfig$delegate", "calendarConfig", "Lru/mail/config/Configuration$CalendarConfig;", "getCalendarConfig", "()Lru/mail/config/Configuration$CalendarConfig;", "calendarConfig$delegate", "calendarPlatesConfig", "Lru/mail/config/Configuration$CalendarPlatesConfig;", "getCalendarPlatesConfig", "()Lru/mail/config/Configuration$CalendarPlatesConfig;", "calendarPlatesConfig$delegate", "pulseConfig", "Lru/mail/config/Configuration$PulseConfig;", "getPulseConfig", "()Lru/mail/config/Configuration$PulseConfig;", "pulseConfig$delegate", "cloudConfig", "Lru/mail/config/CloudConfig;", "getCloudConfig", "()Lru/mail/config/CloudConfig;", "cloudConfig$delegate", "calendarWidgetConfig", "Lru/mail/config/Configuration$CalendarWidgetConfig;", "getCalendarWidgetConfig", "()Lru/mail/config/Configuration$CalendarWidgetConfig;", "calendarWidgetConfig$delegate", "packagesToCheckInstalledApp", "Lru/mail/config/Configuration$PackageCheckerItem;", "getPackagesToCheckInstalledApp", "packagesToCheckInstalledApp$delegate", "metaThreadsStatus", "Lru/mail/config/MetaThreadsStatus;", "getMetaThreadsStatus", "()Lru/mail/config/MetaThreadsStatus;", "metaThreadsStatus$delegate", "permittedCookies", "Lru/mail/config/Configuration$PermittedCookie;", "getPermittedCookies", "permittedCookies$delegate", "mailsListViewConfig", "Lru/mail/config/MailsListViewConfig;", "getMailsListViewConfig", "()Lru/mail/config/MailsListViewConfig;", "mailsListViewConfig$delegate", "accountManagerAnalyticsConfig", "", "Lru/mail/config/Configuration$AccountManagerAnalytics;", "getAccountManagerAnalyticsConfig", "()Ljava/util/Collection;", "accountManagerAnalyticsConfig$delegate", "socialLoginConfig", "Lru/mail/config/Configuration$SocialLoginConfig;", "getSocialLoginConfig", "()Lru/mail/config/Configuration$SocialLoginConfig;", "socialLoginConfig$delegate", "drawables", "Lru/mail/logic/content/DrawableResEntry;", "getDrawables", "drawables$delegate", "strings", "Lru/mail/logic/content/StringResEntry;", "getStrings", "strings$delegate", "pushTypes", "Lru/mail/config/PushConfigurationType;", "getPushTypes", "pushTypes$delegate", "distributors", "Lru/mail/logic/content/Distributor;", "getDistributors", "distributors$delegate", "prefetcherDelayConfig", "Lru/mail/config/Configuration$PrefetcherDelayConfig;", "getPrefetcherDelayConfig", "()Lru/mail/config/Configuration$PrefetcherDelayConfig;", "prefetcherDelayConfig$delegate", "menuFabConfig", "Lru/mail/config/Configuration$MenuFabConfig;", "getMenuFabConfig", "()Lru/mail/config/Configuration$MenuFabConfig;", "menuFabConfig$delegate", "trustedUrls", "", "getTrustedUrls", "()Ljava/util/Map;", "trustedUrls$delegate", "filteredEmailUrls", "getFilteredEmailUrls", "filteredEmailUrls$delegate", "internalApiUrlsHandlers", "Lru/mail/config/Configuration$InternalApiHandler;", "getInternalApiUrlsHandlers", "internalApiUrlsHandlers$delegate", "plates", "Lru/mail/ui/presentation/Plate;", "getPlates", "plates$delegate", "mailsListAttachPreviewsConfig", "Lru/mail/config/Configuration$MailsListAttachPreviewsConfig;", "getMailsListAttachPreviewsConfig", "()Lru/mail/config/Configuration$MailsListAttachPreviewsConfig;", "mailsListAttachPreviewsConfig$delegate", "inAppReviewFullConfig", "Lru/mail/appreview/config/InAppReviewStoreConfig;", "getInAppReviewFullConfig", "inAppReviewFullConfig$delegate", "dynamicStrings", "Lru/mail/data/cache/StringsMemcache;", "getDynamicStrings", "()Lru/mail/data/cache/StringsMemcache;", "dynamicStrings$delegate", "bandwidthConstants", "Lru/mail/android_utils/connection/BandwidthConstants;", "getBandwidthConstants", "()Lru/mail/android_utils/connection/BandwidthConstants;", "bandwidthConstants$delegate", "promoHighlightInfo", "Lru/mail/config/Configuration$PromoHighlightInfo;", "getPromoHighlightInfo", "()Lru/mail/config/Configuration$PromoHighlightInfo;", "promoHighlightInfo$delegate", "categoryChangeBehavior", "Lru/mail/config/Configuration$CategoryChangeBehavior;", "getCategoryChangeBehavior", "()Lru/mail/config/Configuration$CategoryChangeBehavior;", "categoryChangeBehavior$delegate", "metaThreadMassOperationsConfig", "Lru/mail/config/Configuration$MetaThreadMassOperationsConfig;", "getMetaThreadMassOperationsConfig", "()Lru/mail/config/Configuration$MetaThreadMassOperationsConfig;", "metaThreadMassOperationsConfig$delegate", "barActionsOrder", "Lru/mail/logic/content/BarPlace;", "Lru/mail/config/Configuration$BarActionsOrder;", "getBarActionsOrder", "barActionsOrder$delegate", "notificationPromoRule", "Lru/mail/logic/plates/ShowRule;", "getNotificationPromoRule", "()Lru/mail/logic/plates/ShowRule;", "notificationPromoRule$delegate", "promoFeaturesConfig", "Lru/mail/config/Configuration$PromoFeatureConfig;", "getPromoFeaturesConfig", "promoFeaturesConfig$delegate", "deeplinkSmartReplies", "Lru/mail/config/Configuration$DeeplinkSmartReply;", "getDeeplinkSmartReplies", "deeplinkSmartReplies$delegate", "newActionsConfig", "Lru/mail/config/Configuration$NewActionsConfig;", "getNewActionsConfig", "()Lru/mail/config/Configuration$NewActionsConfig;", "newActionsConfig$delegate", "accountSettings", "Lru/mail/config/Configuration$AccountSettingsItem;", "getAccountSettings", "accountSettings$delegate", "phishingConfig", "Lru/mail/config/Configuration$PhishingConfig;", "getPhishingConfig", "()Lru/mail/config/Configuration$PhishingConfig;", "phishingConfig$delegate", "omicronPromoList", "Lru/mail/config/Configuration$OmicronPromo;", "getOmicronPromoList", "omicronPromoList$delegate", "openInWebViewConfig", "Lru/mail/config/Configuration$OpenInWebViewConfig;", "getOpenInWebViewConfig", "()Lru/mail/config/Configuration$OpenInWebViewConfig;", "openInWebViewConfig$delegate", "transactionCategoriesForSearch", "Lru/mail/logic/content/MailItemTransactionCategory;", "getTransactionCategoriesForSearch", "transactionCategoriesForSearch$delegate", "categoriesForSearch", "getCategoriesForSearch", "categoriesForSearch$delegate", TornadoSendRequest.FIELD_SCHEDULE, "Lru/mail/config/Configuration$Schedule;", "getSchedule", "()Lru/mail/config/Configuration$Schedule;", "schedule$delegate", "notificationSettingsManufacturers", "Lru/mail/config/Configuration$ManufacturerItem;", "getNotificationSettingsManufacturers", "notificationSettingsManufacturers$delegate", "stickers", "Lgithub/ankushsachdeva/emojicon/StickersGroup;", "getStickers", "stickers$delegate", "omicronConfigHash", "getOmicronConfigHash", "omicronConfigVersion", "getOmicronConfigVersion", "existingLoginSuppressedOauth", "getExistingLoginSuppressedOauth", "()Ljava/util/regex/Pattern;", "existingLoginSuppressedOauth$delegate", "newLoginSuppressedOauth", "getNewLoginSuppressedOauth", "newLoginSuppressedOauth$delegate", "securitySettingsDomains", "getSecuritySettingsDomains", "securitySettingsDomains$delegate", "appWallSections", "Lru/mail/config/Configuration$AppWallSection;", "getAppWallSections", "appWallSections$delegate", "notesConfig", "Lru/mail/config/Configuration$NotesConfig;", "getNotesConfig", "()Lru/mail/config/Configuration$NotesConfig;", "notesConfig$delegate", "anyFolderMassOpConfigWithUnread", "Lru/mail/config/Configuration$MassOperationToolBarConfiguration;", "getAnyFolderMassOpConfigWithUnread", "()Lru/mail/config/Configuration$MassOperationToolBarConfiguration;", "anyFolderMassOpConfigWithUnread$delegate", "anyFolderMassOpConfigWithoutUnread", "getAnyFolderMassOpConfigWithoutUnread", "anyFolderMassOpConfigWithoutUnread$delegate", "searchMassOpConfigWithUnread", "getSearchMassOpConfigWithUnread", "searchMassOpConfigWithUnread$delegate", "searchMassOpConfigWithoutUnread", "getSearchMassOpConfigWithoutUnread", "searchMassOpConfigWithoutUnread$delegate", "mailViewOzonFeedConfig", "Lru/mail/config/MailViewOzonFeedDTO;", "getMailViewOzonFeedConfig", "()Lru/mail/config/MailViewOzonFeedDTO;", "mailViewOzonFeedConfig$delegate", "walletAppConfig", "Lru/mail/config/WalletAppConfigDTO;", "getWalletAppConfig", "()Lru/mail/config/WalletAppConfigDTO;", "walletAppConfig$delegate", "checkBackInReceiptsMetaThread", "Lru/mail/config/CheckBackInReceiptsMetaThreadDTO;", "getCheckBackInReceiptsMetaThread", "()Lru/mail/config/CheckBackInReceiptsMetaThreadDTO;", "checkBackInReceiptsMetaThread$delegate", "fullscreenMenuItemPromos", "Lru/mail/config/Configuration$FullscreenMenuItemPromo;", "getFullscreenMenuItemPromos", "fullscreenMenuItemPromos$delegate", "clipboardPlatesConfig", "Lru/mail/config/Configuration$ClipboardPlatesConfig;", "getClipboardPlatesConfig", "()Lru/mail/config/Configuration$ClipboardPlatesConfig;", "clipboardPlatesConfig$delegate", "analyzeDataAgreementConfig", "Lru/mail/config/Configuration$AnalyzeDataAgreementConfig;", "getAnalyzeDataAgreementConfig", "()Lru/mail/config/Configuration$AnalyzeDataAgreementConfig;", "analyzeDataAgreementConfig$delegate", "reminderConfig", "Lru/mail/config/Configuration$ReminderConfiguration;", "getReminderConfig", "()Lru/mail/config/Configuration$ReminderConfiguration;", "reminderConfig$delegate", "newMailClipboardSuggestConfig", "Lru/mail/config/Configuration$NewMailClipboardConfig;", "getNewMailClipboardSuggestConfig", "()Lru/mail/config/Configuration$NewMailClipboardConfig;", "newMailClipboardSuggestConfig$delegate", "pushCategoryMapper", "Lru/mail/config/Configuration$PushCategoryMapper;", "getPushCategoryMapper", "()Lru/mail/config/Configuration$PushCategoryMapper;", "pushCategoryMapper$delegate", "linksReplacementRules", "Lru/mail/config/Configuration$LinksReplacementRule;", "getLinksReplacementRules", "linksReplacementRules$delegate", "appendingQueryParamsRules", "Lru/mail/config/Configuration$AppendingQueryParamsRule;", "getAppendingQueryParamsRules", "appendingQueryParamsRules$delegate", "pdfViewerConfig", "Lru/mail/config/remoteconfig/PdfViewerConfig;", "getPdfViewerConfig", "()Lru/mail/config/remoteconfig/PdfViewerConfig;", "pdfViewerConfig$delegate", "redirectFromViewerToCloudConfig", "Lru/mail/cloud/domain/model/RedirectFromViewerToCloudConfig;", "getRedirectFromViewerToCloudConfig", "()Lru/mail/cloud/domain/model/RedirectFromViewerToCloudConfig;", "redirectFromViewerToCloudConfig$delegate", "tokenExchangeConfig", "Lru/mail/kit/auth/helpers/TokenExchangeConfig;", "getTokenExchangeConfig", "()Lru/mail/kit/auth/helpers/TokenExchangeConfig;", "tokenExchangeConfig$delegate", "mailAppDeepLinks", "Lru/mail/config/Configuration$MailAppDeepLink;", "getMailAppDeepLinks", "mailAppDeepLinks$delegate", "appUpdateInfo", "Lru/mail/appupdate/AppUpdateConfig;", "getAppUpdateInfo", "()Lru/mail/appupdate/AppUpdateConfig;", "appUpdateInfo$delegate", "techStat", "Lru/mail/config/Configuration$TechStatConfig;", "getTechStat", "()Lru/mail/config/Configuration$TechStatConfig;", "techStat$delegate", "welcomeLoginScreen", "Lru/mail/config/Configuration$WelcomeLoginScreen;", "getWelcomeLoginScreen", "()Lru/mail/config/Configuration$WelcomeLoginScreen;", "welcomeLoginScreen$delegate", "contactCardConfig", "Lru/mail/config/Configuration$ContactCardConfig;", "getContactCardConfig", "()Lru/mail/config/Configuration$ContactCardConfig;", "contactCardConfig$delegate", "quickActionConfig", "Lru/mail/config/Configuration$QuickActionConfig;", "getQuickActionConfig", "()Lru/mail/config/Configuration$QuickActionConfig;", "quickActionConfig$delegate", "enabledSounds", "Lru/mail/config/Configuration$SoundKey;", "getEnabledSounds", "enabledSounds$delegate", "labelsForSearch", "getLabelsForSearch", "labelsForSearch$delegate", "csatConfig", "Lru/mail/config/Configuration$CsatConfig;", "getCsatConfig", "()Lru/mail/config/Configuration$CsatConfig;", "csatConfig$delegate", "csatListConfig", "Lru/mail/config/Configuration$CsatListConfig;", "getCsatListConfig", "()Lru/mail/config/Configuration$CsatListConfig;", "csatListConfig$delegate", "csatTriggersListConfig", "Lru/mail/config/Configuration$CsatTriggersListConfig;", "getCsatTriggersListConfig", "()Lru/mail/config/Configuration$CsatTriggersListConfig;", "csatTriggersListConfig$delegate", "totalCleanConfig", "Lru/mail/config/Configuration$TotalCleanConfig;", "getTotalCleanConfig", "()Lru/mail/config/Configuration$TotalCleanConfig;", "totalCleanConfig$delegate", "newAuthSdkConfig", "Lru/mail/authorizesdk/domain/models/NewAuthSdkConfig;", "getNewAuthSdkConfig$annotations", "()V", "getNewAuthSdkConfig", "()Lru/mail/authorizesdk/domain/models/NewAuthSdkConfig;", "newAuthSdkConfig$delegate", "newAuthorizationSdkConfig", "Lru/mail/authorizesdk/domain/models/NewAuthorizationSdkConfig;", "getNewAuthorizationSdkConfig", "()Lru/mail/authorizesdk/domain/models/NewAuthorizationSdkConfig;", "newAuthorizationSdkConfig$delegate", "mediascope", "Lru/mail/mediascope/MediascopeConfiguration;", "getMediascope", "()Lru/mail/mediascope/MediascopeConfiguration;", "shareMailConfig", "Lru/mail/config/Configuration$ShareMailConfig;", "getShareMailConfig", "()Lru/mail/config/Configuration$ShareMailConfig;", "shareMailConfig$delegate", "callInRegistrationConfig", "Lru/mail/config/Configuration$CallInRegistrationConfig;", "getCallInRegistrationConfig", "()Lru/mail/config/Configuration$CallInRegistrationConfig;", "callInRegistrationConfig$delegate", "callUIRegistrationConfig", "Lru/mail/config/Configuration$CallUIRegistrationConfig;", "getCallUIRegistrationConfig", "()Lru/mail/config/Configuration$CallUIRegistrationConfig;", "callUIRegistrationConfig$delegate", "cloudQuotaConfig", "Lru/mail/config/Configuration$CloudQuotaConfig;", "getCloudQuotaConfig", "()Lru/mail/config/Configuration$CloudQuotaConfig;", "cloudQuotaConfig$delegate", "imapBannerConfig", "Lru/mail/config/Configuration$ImapBannerConfig;", "getImapBannerConfig", "()Lru/mail/config/Configuration$ImapBannerConfig;", "imapBannerConfig$delegate", "cloudSharedOverQuotaConfig", "Lru/mail/config/Configuration$CloudOverQuotaConfig;", "getCloudSharedOverQuotaConfig", "()Lru/mail/config/Configuration$CloudOverQuotaConfig;", "cloudSharedOverQuotaConfig$delegate", "bonusConfig", "Lru/mail/config/Configuration$BonusConfig;", "getBonusConfig", "()Lru/mail/config/Configuration$BonusConfig;", "bonusConfig$delegate", "walletConfig", "Lru/mail/config/Configuration$WalletConfig;", "getWalletConfig", "()Lru/mail/config/Configuration$WalletConfig;", "walletConfig$delegate", "webApps", "Lru/mail/config/Configuration$TabWebAppsConfig;", "getWebApps", "()Lru/mail/config/Configuration$TabWebAppsConfig;", "webApps$delegate", "accountManagerSyncConfig", "Lru/mail/config/AccountManagerSyncConfigDTO;", "getAccountManagerSyncConfig", "()Lru/mail/config/AccountManagerSyncConfigDTO;", "accountManagerSyncConfig$delegate", "cryptoAwareFileRemoteConfig", "Lru/mail/file/utils/encrypted/remoteconfig/CryptoAwareFileRemoteConfig;", "getCryptoAwareFileRemoteConfig", "()Lru/mail/file/utils/encrypted/remoteconfig/CryptoAwareFileRemoteConfig;", "cryptoAwareFileRemoteConfig$delegate", "mailMessageContainerConfig", "Lru/mail/config/MailMessageContainerConfigDTO;", "getMailMessageContainerConfig", "()Lru/mail/config/MailMessageContainerConfigDTO;", "mailMessageContainerConfig$delegate", "promotionNotificationWithImageConfig", "Lru/mail/config/Configuration$PromotionNotificationWithImageConfigImpl;", "getPromotionNotificationWithImageConfig", "()Lru/mail/config/Configuration$PromotionNotificationWithImageConfigImpl;", "promotionNotificationWithImageConfig$delegate", "overquotaInfoSheetConfig", "Lru/mail/config/overquota/OverquotaInfoSheetConfig;", "getOverquotaInfoSheetConfig", "()Lru/mail/config/overquota/OverquotaInfoSheetConfig;", "overquotaInfoSheetConfig$delegate", "sharedCloudLoggerRemoteConfig", "Lru/mail/util/log/clogs/SharedCloudLoggerRemoteConfig;", "getSharedCloudLoggerRemoteConfig", "()Lru/mail/util/log/clogs/SharedCloudLoggerRemoteConfig;", "sharedCloudLoggerRemoteConfig$delegate", "prettyEmailConfiguration", "Lru/mail/config/prettyemail/PrettyEmailConfiguration;", "getPrettyEmailConfiguration", "()Lru/mail/config/prettyemail/PrettyEmailConfiguration;", "prettyEmailConfiguration$delegate", "prettyEmailPromoConfig", "Lru/mail/config/prettyemail/promo/PrettyEmailPromoConfig;", "getPrettyEmailPromoConfig", "()Lru/mail/config/prettyemail/promo/PrettyEmailPromoConfig;", "prettyEmailPromoConfig$delegate", "sendCancellationForBusinessmenConfig", "Lru/mail/config/forbusinessmen/SendCancellationForBusinessmenConfig;", "getSendCancellationForBusinessmenConfig", "()Lru/mail/config/forbusinessmen/SendCancellationForBusinessmenConfig;", "sendCancellationForBusinessmenConfig$delegate", "readVerificationForBusinessmenConfig", "Lru/mail/config/forbusinessmen/ReadVerificationForBusinessmenConfig;", "getReadVerificationForBusinessmenConfig", "()Lru/mail/config/forbusinessmen/ReadVerificationForBusinessmenConfig;", "readVerificationForBusinessmenConfig$delegate", "networkCheckerConfig", "Lru/mail/network/networkchecker/NetworkCheckerConfig;", "getNetworkCheckerConfig", "()Lru/mail/network/networkchecker/NetworkCheckerConfig;", "networkCheckerConfig$delegate", "cloudLinksNavigatorParamsConfig", "Lru/mail/config/cloud/CloudLinksNavigatorParamsConfig;", "getCloudLinksNavigatorParamsConfig", "()Lru/mail/config/cloud/CloudLinksNavigatorParamsConfig;", "cloudLinksNavigatorParamsConfig$delegate", "imapPromoConfig", "Lru/mail/config/imap/promo/ImapPromoConfig;", "getImapPromoConfig", "()Lru/mail/config/imap/promo/ImapPromoConfig;", "imapPromoConfig$delegate", "actionFarmAnalyticsConfig", "Lru/mail/config/actionfarmanalytics/ActionFarmAnalyticsConfig;", "getActionFarmAnalyticsConfig", "()Lru/mail/config/actionfarmanalytics/ActionFarmAnalyticsConfig;", "actionFarmAnalyticsConfig$delegate", "kotlettUrlRedirectConfig", "Lru/mail/config/kotlett/KotlettUrlRedirectConfig;", "getKotlettUrlRedirectConfig", "()Lru/mail/config/kotlett/KotlettUrlRedirectConfig;", "kotlettUrlRedirectConfig$delegate", "equals", "", "other", "", "toString", "hashCode", "", "serializable", "Lru/mail/config/util/ConfigSerializableDelegate;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "R", "key", "default", "jsonProvider", "snakeSerializable", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailAppConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailAppConfiguration.kt\nru/mail/config/MailAppConfiguration\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,804:1\n776#1,12:805\n794#1,7:817\n776#1,12:824\n776#1,12:836\n776#1,12:848\n776#1,12:860\n776#1,12:872\n776#1,12:884\n776#1,12:896\n776#1,12:908\n776#1,12:920\n776#1,12:932\n776#1,12:944\n776#1,12:956\n776#1,12:968\n776#1,12:980\n776#1,12:992\n776#1,12:1004\n776#1,12:1016\n776#1,12:1028\n776#1,12:1040\n776#1,12:1052\n776#1,12:1064\n776#1,12:1076\n776#1,12:1088\n776#1,12:1100\n776#1,12:1112\n776#1,12:1124\n776#1,12:1136\n776#1,12:1148\n776#1,12:1160\n776#1,12:1172\n776#1,12:1184\n776#1,12:1196\n776#1,12:1208\n780#1,8:1221\n1#2:1220\n*S KotlinDebug\n*F\n+ 1 MailAppConfiguration.kt\nru/mail/config/MailAppConfiguration\n*L\n357#1:805,12\n360#1:817,7\n363#1:824,12\n366#1:836,12\n369#1:848,12\n372#1:860,12\n375#1:872,12\n378#1:884,12\n381#1:896,12\n384#1:908,12\n387#1:920,12\n390#1:932,12\n393#1:944,12\n437#1:956,12\n573#1:968,12\n576#1:980,12\n579#1:992,12\n606#1:1004,12\n609#1:1016,12\n612#1:1028,12\n692#1:1040,12\n695#1:1052,12\n699#1:1064,12\n703#1:1076,12\n707#1:1088,12\n711#1:1100,12\n715#1:1112,12\n719#1:1124,12\n723#1:1136,12\n727#1:1148,12\n731#1:1160,12\n736#1:1172,12\n740#1:1184,12\n744#1:1196,12\n748#1:1208,12\n398#1:1221,8\n*E\n"})
public final class MailAppConfiguration implements ConfigurationWithRawData {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "freemiumConfig", "getFreemiumConfig()Lru/mail/config/freemium/FreemiumConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "adConfig", "getAdConfig()Lru/mail/ads/config/api/data/model/AdRemoteConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "familySubscriptionConfig", "getFamilySubscriptionConfig()Lru/mail/feature/family/impl/config/FamilySubscriptionRemoteConfigImpl;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "tgMigrationConfig", "getTgMigrationConfig()Lru/mail/feature/tgmigration/impl/config/TgMigrationConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "maxConfig", "getMaxConfig()Lru/mail/config/max/MaxRemoteConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "cloudCleanupConfig", "getCloudCleanupConfig()Lru/mail/feature/cloudcleanup/config/CloudCleanupConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "cloudEntryScreenConfig", "getCloudEntryScreenConfig()Lru/mail/config/cloudentryscreen/CloudEntryScreenConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "redirectToTelegramMigrationConfig", "getRedirectToTelegramMigrationConfig()Lru/mail/feature/tgmigration/impl/domain/model/RedirectToTelegramMigrationConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "servicesBannerConfig", "getServicesBannerConfig()Lru/mail/feature/tgmigration/impl/domain/model/ServicesBannerConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "letterAiWriter", "getLetterAiWriter()Lru/mail/letter/ai/writer/config/LetterAiWriterRemoteConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "omicronConfig", "getOmicronConfig()Lru/mail/config/remoteconfig/OmicronConfigImpl;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "billingConfiguration", "getBillingConfiguration()Lru/mail/subscription/billing/BillingConfiguration;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "personalizationConfig", "getPersonalizationConfig()Lru/mail/subscription/model/PersonalizationConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "mailsListViewConfig", "getMailsListViewConfig()Lru/mail/config/MailsListViewConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "mailViewOzonFeedConfig", "getMailViewOzonFeedConfig()Lru/mail/config/MailViewOzonFeedDTO;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "walletAppConfig", "getWalletAppConfig()Lru/mail/config/WalletAppConfigDTO;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "checkBackInReceiptsMetaThread", "getCheckBackInReceiptsMetaThread()Lru/mail/config/CheckBackInReceiptsMetaThreadDTO;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "pdfViewerConfig", "getPdfViewerConfig()Lru/mail/config/remoteconfig/PdfViewerConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "redirectFromViewerToCloudConfig", "getRedirectFromViewerToCloudConfig()Lru/mail/cloud/domain/model/RedirectFromViewerToCloudConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "tokenExchangeConfig", "getTokenExchangeConfig()Lru/mail/kit/auth/helpers/TokenExchangeConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "accountManagerSyncConfig", "getAccountManagerSyncConfig()Lru/mail/config/AccountManagerSyncConfigDTO;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "cryptoAwareFileRemoteConfig", "getCryptoAwareFileRemoteConfig()Lru/mail/file/utils/encrypted/remoteconfig/CryptoAwareFileRemoteConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "mailMessageContainerConfig", "getMailMessageContainerConfig()Lru/mail/config/MailMessageContainerConfigDTO;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "promotionNotificationWithImageConfig", "getPromotionNotificationWithImageConfig()Lru/mail/config/Configuration$PromotionNotificationWithImageConfigImpl;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "overquotaInfoSheetConfig", "getOverquotaInfoSheetConfig()Lru/mail/config/overquota/OverquotaInfoSheetConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "sharedCloudLoggerRemoteConfig", "getSharedCloudLoggerRemoteConfig()Lru/mail/util/log/clogs/SharedCloudLoggerRemoteConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "prettyEmailConfiguration", "getPrettyEmailConfiguration()Lru/mail/config/prettyemail/PrettyEmailConfiguration;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "prettyEmailPromoConfig", "getPrettyEmailPromoConfig()Lru/mail/config/prettyemail/promo/PrettyEmailPromoConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "sendCancellationForBusinessmenConfig", "getSendCancellationForBusinessmenConfig()Lru/mail/config/forbusinessmen/SendCancellationForBusinessmenConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "readVerificationForBusinessmenConfig", "getReadVerificationForBusinessmenConfig()Lru/mail/config/forbusinessmen/ReadVerificationForBusinessmenConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "networkCheckerConfig", "getNetworkCheckerConfig()Lru/mail/network/networkchecker/NetworkCheckerConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "cloudLinksNavigatorParamsConfig", "getCloudLinksNavigatorParamsConfig()Lru/mail/config/cloud/CloudLinksNavigatorParamsConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "imapPromoConfig", "getImapPromoConfig()Lru/mail/config/imap/promo/ImapPromoConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "actionFarmAnalyticsConfig", "getActionFarmAnalyticsConfig()Lru/mail/config/actionfarmanalytics/ActionFarmAnalyticsConfig;", 0)), Reflection.property1(new PropertyReference1Impl(MailAppConfiguration.class, "kotlettUrlRedirectConfig", "getKotlettUrlRedirectConfig()Lru/mail/config/kotlett/KotlettUrlRedirectConfig;", 0))};
    public static final int $stable = 8;

    /* JADX INFO: renamed from: accountManagerAnalyticsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy accountManagerAnalyticsConfig;

    /* JADX INFO: renamed from: accountManagerSyncConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate accountManagerSyncConfig;

    /* JADX INFO: renamed from: accountSettings$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy accountSettings;

    /* JADX INFO: renamed from: actionFarmAnalyticsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate actionFarmAnalyticsConfig;

    /* JADX INFO: renamed from: adConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate adConfig;

    /* JADX INFO: renamed from: adDomains$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy adDomains;

    @NotNull
    private final AnalyticsSender analyticsSender;

    /* JADX INFO: renamed from: analyzeDataAgreementConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy analyzeDataAgreementConfig;

    /* JADX INFO: renamed from: anyFolderMassOpConfigWithUnread$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy anyFolderMassOpConfigWithUnread;

    /* JADX INFO: renamed from: anyFolderMassOpConfigWithoutUnread$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy anyFolderMassOpConfigWithoutUnread;

    /* JADX INFO: renamed from: appUpdateInfo$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy appUpdateInfo;

    /* JADX INFO: renamed from: appWallSections$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy appWallSections;

    /* JADX INFO: renamed from: appendingQueryParamsRules$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy appendingQueryParamsRules;

    /* JADX INFO: renamed from: bandwidthConstants$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy bandwidthConstants;

    /* JADX INFO: renamed from: barActionsOrder$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy barActionsOrder;

    /* JADX INFO: renamed from: billingConfiguration$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate billingConfiguration;

    /* JADX INFO: renamed from: bonusConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy bonusConfig;

    /* JADX INFO: renamed from: calendarConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy calendarConfig;

    /* JADX INFO: renamed from: calendarPlatesConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy calendarPlatesConfig;

    /* JADX INFO: renamed from: calendarTodoConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy calendarTodoConfig;

    /* JADX INFO: renamed from: calendarWidgetConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy calendarWidgetConfig;

    /* JADX INFO: renamed from: callInRegistrationConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy callInRegistrationConfig;

    /* JADX INFO: renamed from: callUIRegistrationConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy callUIRegistrationConfig;

    /* JADX INFO: renamed from: categoriesForSearch$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy categoriesForSearch;

    /* JADX INFO: renamed from: categoryChangeBehavior$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy categoryChangeBehavior;

    /* JADX INFO: renamed from: checkBackInReceiptsMetaThread$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate checkBackInReceiptsMetaThread;

    /* JADX INFO: renamed from: clipboardPlatesConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy clipboardPlatesConfig;

    /* JADX INFO: renamed from: cloudCleanupConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate cloudCleanupConfig;

    /* JADX INFO: renamed from: cloudConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy cloudConfig;

    /* JADX INFO: renamed from: cloudEntryScreenConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate cloudEntryScreenConfig;

    /* JADX INFO: renamed from: cloudLinksNavigatorParamsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate cloudLinksNavigatorParamsConfig;

    /* JADX INFO: renamed from: cloudQuotaConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy cloudQuotaConfig;

    /* JADX INFO: renamed from: cloudSharedOverQuotaConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy cloudSharedOverQuotaConfig;

    @NotNull
    private final DTOConfiguration.Config config;

    @NotNull
    private final ConfigurationData configurationData;

    /* JADX INFO: renamed from: configurationDataFiled$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy configurationDataFiled;

    @NotNull
    private final JSONObject configurationDataObject;

    @NotNull
    private final ConfigurationSettingsDelegate configurationSettingsDelegate;

    /* JADX INFO: renamed from: contactCardConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy contactCardConfig;

    /* JADX INFO: renamed from: cryptoAwareFileRemoteConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate cryptoAwareFileRemoteConfig;

    /* JADX INFO: renamed from: csatConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy csatConfig;

    /* JADX INFO: renamed from: csatListConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy csatListConfig;

    /* JADX INFO: renamed from: csatTriggersListConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy csatTriggersListConfig;

    /* JADX INFO: renamed from: darkThemeConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy darkThemeConfig;

    /* JADX INFO: renamed from: deeplinkSmartReplies$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy deeplinkSmartReplies;

    /* JADX INFO: renamed from: distributors$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy distributors;

    /* JADX INFO: renamed from: dogfoodingConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy dogfoodingConfig;

    /* JADX INFO: renamed from: drawables$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy drawables;

    @NotNull
    private final DTORawConfiguration dtoConfiguration;

    /* JADX INFO: renamed from: dynamicStrings$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy dynamicStrings;

    /* JADX INFO: renamed from: enabledAssertions$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy enabledAssertions;

    /* JADX INFO: renamed from: enabledSounds$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy enabledSounds;

    /* JADX INFO: renamed from: existingLoginSuppressedOauth$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy existingLoginSuppressedOauth;

    /* JADX INFO: renamed from: familySubscriptionConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate familySubscriptionConfig;

    /* JADX INFO: renamed from: fastReplyConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy fastReplyConfig;

    /* JADX INFO: renamed from: filteredEmailUrls$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy filteredEmailUrls;

    /* JADX INFO: renamed from: freemiumConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate freemiumConfig;

    /* JADX INFO: renamed from: fullscreenMenuItemPromos$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy fullscreenMenuItemPromos;

    /* JADX INFO: renamed from: hitmanConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy hitmanConfig;

    /* JADX INFO: renamed from: huaweiWebViewErrorConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy huaweiWebViewErrorConfig;

    /* JADX INFO: renamed from: imapBannerConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy imapBannerConfig;

    /* JADX INFO: renamed from: imapPromoConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate imapPromoConfig;

    /* JADX INFO: renamed from: inAppReviewFullConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy inAppReviewFullConfig;

    /* JADX INFO: renamed from: internalApiUrlsHandlers$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy internalApiUrlsHandlers;

    /* JADX INFO: renamed from: jsScriptFetchingConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy jsScriptFetchingConfig;

    /* JADX INFO: renamed from: kotlettUrlRedirectConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate kotlettUrlRedirectConfig;

    /* JADX INFO: renamed from: labelsForSearch$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy labelsForSearch;

    /* JADX INFO: renamed from: letterAiWriter$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate letterAiWriter;

    /* JADX INFO: renamed from: liberoDomains$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy liberoDomains;

    /* JADX INFO: renamed from: licenseAgreementConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy licenseAgreementConfig;

    /* JADX INFO: renamed from: linksReplacementRules$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy linksReplacementRules;

    /* JADX INFO: renamed from: mailAppDeepLinks$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy mailAppDeepLinks;

    /* JADX INFO: renamed from: mailMessageContainerConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate mailMessageContainerConfig;

    /* JADX INFO: renamed from: mailViewOzonFeedConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate mailViewOzonFeedConfig;

    /* JADX INFO: renamed from: mailsListAttachPreviewsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy mailsListAttachPreviewsConfig;

    /* JADX INFO: renamed from: mailsListViewConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate mailsListViewConfig;

    /* JADX INFO: renamed from: maxConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate maxConfig;

    @NotNull
    private final MediascopeConfiguration mediascope;

    /* JADX INFO: renamed from: menuFabConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy menuFabConfig;

    /* JADX INFO: renamed from: metaThreadMassOperationsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy metaThreadMassOperationsConfig;

    /* JADX INFO: renamed from: metaThreadsStatus$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy metaThreadsStatus;

    /* JADX INFO: renamed from: networkCheckerConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate networkCheckerConfig;

    /* JADX INFO: renamed from: newActionsConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newActionsConfig;

    /* JADX INFO: renamed from: newAuthSdkConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newAuthSdkConfig;

    /* JADX INFO: renamed from: newAuthorizationSdkConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newAuthorizationSdkConfig;

    /* JADX INFO: renamed from: newLoginSuppressedOauth$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newLoginSuppressedOauth;

    /* JADX INFO: renamed from: newMailClipboardSuggestConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newMailClipboardSuggestConfig;

    /* JADX INFO: renamed from: notesConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy notesConfig;

    /* JADX INFO: renamed from: notificationPromoRule$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy notificationPromoRule;

    /* JADX INFO: renamed from: notificationSettingsManufacturers$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy notificationSettingsManufacturers;

    /* JADX INFO: renamed from: omicronConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate omicronConfig;

    @Nullable
    private final String omicronConfigHash;

    @Nullable
    private final String omicronConfigVersion;

    /* JADX INFO: renamed from: omicronPromoList$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy omicronPromoList;

    /* JADX INFO: renamed from: omicronPromoSettings$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy omicronPromoSettings;

    /* JADX INFO: renamed from: openInWebViewConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy openInWebViewConfig;

    /* JADX INFO: renamed from: overquotaInfoSheetConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate overquotaInfoSheetConfig;

    /* JADX INFO: renamed from: packagesToCheckInstalledApp$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy packagesToCheckInstalledApp;

    /* JADX INFO: renamed from: pdfViewerConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate pdfViewerConfig;

    /* JADX INFO: renamed from: permittedCookies$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy permittedCookies;

    /* JADX INFO: renamed from: personalizationConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate personalizationConfig;

    /* JADX INFO: renamed from: phishingConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy phishingConfig;

    /* JADX INFO: renamed from: plates$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy plates;

    /* JADX INFO: renamed from: portal$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy portal;

    /* JADX INFO: renamed from: prefetcherDelayConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy prefetcherDelayConfig;

    /* JADX INFO: renamed from: prettyEmailConfiguration$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate prettyEmailConfiguration;

    /* JADX INFO: renamed from: prettyEmailPromoConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate prettyEmailPromoConfig;

    /* JADX INFO: renamed from: promoFeaturesConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy promoFeaturesConfig;

    /* JADX INFO: renamed from: promoHighlightInfo$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy promoHighlightInfo;

    /* JADX INFO: renamed from: promotionNotificationWithImageConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate promotionNotificationWithImageConfig;

    /* JADX INFO: renamed from: pulseConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pulseConfig;

    /* JADX INFO: renamed from: pushCategoryMapper$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushCategoryMapper;

    /* JADX INFO: renamed from: pushTypes$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushTypes;

    /* JADX INFO: renamed from: quickActionConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy quickActionConfig;

    @NotNull
    private final Function0<R7EditPromoConfig> r7EditPromoConfigProvider;

    @NotNull
    private final Function0<R7OfficeConfig> r7OfficeConfigProvider;

    /* JADX INFO: renamed from: readVerificationForBusinessmenConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate readVerificationForBusinessmenConfig;

    /* JADX INFO: renamed from: readVerify$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy readVerify;

    /* JADX INFO: renamed from: redirectFromViewerToCloudConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate redirectFromViewerToCloudConfig;

    /* JADX INFO: renamed from: redirectToTelegramMigrationConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate redirectToTelegramMigrationConfig;

    /* JADX INFO: renamed from: releaseFetcherConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy releaseFetcherConfig;

    /* JADX INFO: renamed from: relocationAgreementConfigs$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy relocationAgreementConfigs;

    /* JADX INFO: renamed from: reminderConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy reminderConfig;

    /* JADX INFO: renamed from: restoreAuthFlowConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy restoreAuthFlowConfig;

    /* JADX INFO: renamed from: retrofitConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy retrofitConfig;

    /* JADX INFO: renamed from: ruStoreSdkConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy ruStoreSdkConfig;

    /* JADX INFO: renamed from: schedule$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy schedule;

    /* JADX INFO: renamed from: searchMassOpConfigWithUnread$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy searchMassOpConfigWithUnread;

    /* JADX INFO: renamed from: searchMassOpConfigWithoutUnread$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy searchMassOpConfigWithoutUnread;

    /* JADX INFO: renamed from: securitySettingsDomains$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy securitySettingsDomains;

    /* JADX INFO: renamed from: sendCancellationForBusinessmenConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate sendCancellationForBusinessmenConfig;

    /* JADX INFO: renamed from: sendHttpRequestAnalyticEventsFilter$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy sendHttpRequestAnalyticEventsFilter;

    /* JADX INFO: renamed from: servicesBannerConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate servicesBannerConfig;

    /* JADX INFO: renamed from: shareMailConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy shareMailConfig;

    /* JADX INFO: renamed from: sharedCloudLoggerRemoteConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate sharedCloudLoggerRemoteConfig;

    /* JADX INFO: renamed from: showQuotaRegions$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy showQuotaRegions;

    /* JADX INFO: renamed from: socialLoginConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy socialLoginConfig;

    /* JADX INFO: renamed from: stickers$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy stickers;

    /* JADX INFO: renamed from: stories$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy stories;

    /* JADX INFO: renamed from: stringConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy stringConfig;

    /* JADX INFO: renamed from: strings$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy strings;

    /* JADX INFO: renamed from: subscription$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy subscription;

    /* JADX INFO: renamed from: summarize$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy summarize;

    /* JADX INFO: renamed from: techStat$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy techStat;

    /* JADX INFO: renamed from: tgMigrationConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate tgMigrationConfig;

    /* JADX INFO: renamed from: tokenExchangeConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate tokenExchangeConfig;

    /* JADX INFO: renamed from: totalCleanConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy totalCleanConfig;

    /* JADX INFO: renamed from: transactionCategoriesForSearch$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy transactionCategoriesForSearch;

    /* JADX INFO: renamed from: trustedMailConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy trustedMailConfig;

    /* JADX INFO: renamed from: trustedUrls$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy trustedUrls;

    /* JADX INFO: renamed from: userThemeData$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy userThemeData;

    /* JADX INFO: renamed from: virgilioDomains$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy virgilioDomains;

    /* JADX INFO: renamed from: vkpnsHostSdk$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy vkpnsHostSdk;

    /* JADX INFO: renamed from: walletAppConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final ConfigSerializableDelegate walletAppConfig;

    /* JADX INFO: renamed from: walletConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy walletConfig;

    /* JADX INFO: renamed from: webApps$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy webApps;

    /* JADX INFO: renamed from: webViewConfig$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy webViewConfig;

    /* JADX INFO: renamed from: welcomeLoginScreen$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy welcomeLoginScreen;

    /* JADX INFO: renamed from: writeToDevLogging$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy writeToDevLogging;

    /* JADX INFO: renamed from: xmailMigrationEntryPointExp$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy xmailMigrationEntryPointExp;

    /* JADX INFO: renamed from: ru.mail.config.MailAppConfiguration$serializable$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMailAppConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailAppConfiguration.kt\nru/mail/config/MailAppConfiguration$serializable$1\n*L\n1#1,804:1\n*E\n"})
    public static final class AnonymousClass1 implements Function0<String> {
        final /* synthetic */ String $key;

        public AnonymousClass1(String str) {
            this.$key = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return MailAppConfiguration.this.configurationData.getStringOrNull(this.$key);
        }
    }

    /* JADX INFO: renamed from: ru.mail.config.MailAppConfiguration$snakeSerializable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes9.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMailAppConfiguration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailAppConfiguration.kt\nru/mail/config/MailAppConfiguration$snakeSerializable$1\n*L\n1#1,804:1\n*E\n"})
    public static final class C20591 implements Function0<String> {
        final /* synthetic */ String $key;

        public C20591(String str) {
            this.$key = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return MailAppConfiguration.this.configurationData.getStringOrNull(this.$key);
        }
    }

    public MailAppConfiguration(@NotNull DTORawConfiguration dtoConfiguration, @NotNull ConfigurationData configurationData, @NotNull ConfigurationSettingsDelegate configurationSettingsDelegate, @NotNull final StorageProvider storageProvider, @NotNull AnalyticsSender analyticsSender, @NotNull Function0<R7OfficeConfig> r7OfficeConfigProvider, @NotNull Function0<R7EditPromoConfig> r7EditPromoConfigProvider) {
        Intrinsics.checkNotNullParameter(dtoConfiguration, "dtoConfiguration");
        Intrinsics.checkNotNullParameter(configurationData, "configurationData");
        Intrinsics.checkNotNullParameter(configurationSettingsDelegate, "configurationSettingsDelegate");
        Intrinsics.checkNotNullParameter(storageProvider, "storageProvider");
        Intrinsics.checkNotNullParameter(analyticsSender, "analyticsSender");
        Intrinsics.checkNotNullParameter(r7OfficeConfigProvider, "r7OfficeConfigProvider");
        Intrinsics.checkNotNullParameter(r7EditPromoConfigProvider, "r7EditPromoConfigProvider");
        this.dtoConfiguration = dtoConfiguration;
        this.configurationData = configurationData;
        this.configurationSettingsDelegate = configurationSettingsDelegate;
        this.analyticsSender = analyticsSender;
        this.r7OfficeConfigProvider = r7OfficeConfigProvider;
        this.r7EditPromoConfigProvider = r7EditPromoConfigProvider;
        this.stringConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.x0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.stringConfig_delegate$lambda$0(this.f84986a);
            }
        });
        this.config = getDtoConfiguration().getDtoConfig().getConfig();
        JSONObject jSONObjectOptJSONObject = getDtoConfiguration().getRawConfig().optJSONObject("config");
        this.configurationDataObject = jSONObjectOptJSONObject == null ? new JSONObject() : jSONObjectOptJSONObject;
        this.configurationDataFiled = LazyKt.lazy(new Function0() { // from class: ru.mail.config.u0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.configurationDataFiled_delegate$lambda$0(this.f84969a);
            }
        });
        this.licenseAgreementConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.g1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.licenseAgreementConfig_delegate$lambda$0(this.f84897a);
            }
        });
        this.relocationAgreementConfigs = LazyKt.lazy(new Function0() { // from class: ru.mail.config.s1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.relocationAgreementConfigs_delegate$lambda$0(this.f84956a);
            }
        });
        this.sendHttpRequestAnalyticEventsFilter = LazyKt.lazy(new Function0() { // from class: ru.mail.config.f2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.sendHttpRequestAnalyticEventsFilter_delegate$lambda$0(this.f84893a);
            }
        });
        this.vkpnsHostSdk = LazyKt.lazy(new Function0() { // from class: ru.mail.config.r2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.vkpnsHostSdk_delegate$lambda$0(this.f84952a);
            }
        });
        this.darkThemeConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.d3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.darkThemeConfig_delegate$lambda$0(this.f84884a);
            }
        });
        this.userThemeData = LazyKt.lazy(new Function0() { // from class: ru.mail.config.p3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.userThemeData_delegate$lambda$0(this.f84943a);
            }
        });
        this.stories = LazyKt.lazy(new Function0() { // from class: ru.mail.config.p
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.stories_delegate$lambda$0(this.f84939a);
            }
        });
        this.subscription = LazyKt.lazy(new Function0() { // from class: ru.mail.config.b0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.subscription_delegate$lambda$0(this.f84870a);
            }
        });
        this.enabledAssertions = LazyKt.lazy(new Function0() { // from class: ru.mail.config.f0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.enabledAssertions_delegate$lambda$0(this.f84891a);
            }
        });
        this.adDomains = LazyKt.lazy(new Function0() { // from class: ru.mail.config.k0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.adDomains_delegate$lambda$0(this.f84916a);
            }
        });
        this.liberoDomains = LazyKt.lazy(new Function0() { // from class: ru.mail.config.l0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.liberoDomains_delegate$lambda$0(this.f84921a);
            }
        });
        this.virgilioDomains = LazyKt.lazy(new Function0() { // from class: ru.mail.config.n0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.virgilioDomains_delegate$lambda$0(this.f84930a);
            }
        });
        this.showQuotaRegions = LazyKt.lazy(new Function0() { // from class: ru.mail.config.o0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.showQuotaRegions_delegate$lambda$0(this.f84935a);
            }
        });
        this.omicronPromoSettings = LazyKt.lazy(new Function0() { // from class: ru.mail.config.p0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.omicronPromoSettings_delegate$lambda$0(this.f84940a);
            }
        });
        this.summarize = LazyKt.lazy(new Function0() { // from class: ru.mail.config.q0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.summarize_delegate$lambda$0(this.f84945a);
            }
        });
        this.huaweiWebViewErrorConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.r0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.huaweiWebViewErrorConfig_delegate$lambda$0(this.f84950a);
            }
        });
        this.xmailMigrationEntryPointExp = LazyKt.lazy(new Function0() { // from class: ru.mail.config.s0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.xmailMigrationEntryPointExp_delegate$lambda$0(this.f84955a);
            }
        });
        this.hitmanConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.t0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.hitmanConfig_delegate$lambda$0(this.f84963a);
            }
        });
        this.dogfoodingConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.v0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.dogfoodingConfig_delegate$lambda$0(this.f84976a);
            }
        });
        this.releaseFetcherConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.w0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.releaseFetcherConfig_delegate$lambda$0(this.f84981a);
            }
        });
        this.retrofitConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.y0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.retrofitConfig_delegate$lambda$0(this.f84991a);
            }
        });
        this.jsScriptFetchingConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.z0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.jsScriptFetchingConfig_delegate$lambda$0(this.f84996a);
            }
        });
        this.readVerify = LazyKt.lazy(new Function0() { // from class: ru.mail.config.a1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.readVerify_delegate$lambda$0(this.f84866a);
            }
        });
        this.fastReplyConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.b1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.fastReplyConfig_delegate$lambda$0(this.f84871a);
            }
        });
        this.restoreAuthFlowConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.c1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.restoreAuthFlowConfig_delegate$lambda$0(this.f84877a);
            }
        });
        this.ruStoreSdkConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.d1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.ruStoreSdkConfig_delegate$lambda$0(this.f84882a);
            }
        });
        this.trustedMailConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.e1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.trustedMailConfig_delegate$lambda$0(this.f84888a);
            }
        });
        this.freemiumConfig = new ConfigSerializableDelegate("freemium_config", new AnonymousClass1("freemium_config"), MailAppConfiguration$freemiumConfig$3.INSTANCE, FreemiumConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.adConfig = new ConfigSerializableDelegate("ad_config", new C20591("ad_config"), MailAppConfiguration$adConfig$3.INSTANCE, AdRemoteConfig.INSTANCE.serializer(), analyticsSender, OmicronSerializationKt.getRemoteConfigJsonSnake());
        this.familySubscriptionConfig = new ConfigSerializableDelegate("family_subscription_config", new AnonymousClass1("family_subscription_config"), MailAppConfiguration$familySubscriptionConfig$3.INSTANCE, FamilySubscriptionRemoteConfigImpl.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.tgMigrationConfig = new ConfigSerializableDelegate("tg_migration_config", new AnonymousClass1("tg_migration_config"), MailAppConfiguration$tgMigrationConfig$3.INSTANCE, TgMigrationConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.maxConfig = new ConfigSerializableDelegate(PortalMailAppConfigurationRetrieverImpl.KEY_MAX_CONFIG, new AnonymousClass1(PortalMailAppConfigurationRetrieverImpl.KEY_MAX_CONFIG), MailAppConfiguration$maxConfig$3.INSTANCE, MaxRemoteConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.cloudCleanupConfig = new ConfigSerializableDelegate("cloud_cleanup_setting_config", new AnonymousClass1("cloud_cleanup_setting_config"), MailAppConfiguration$cloudCleanupConfig$3.INSTANCE, CloudCleanupConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.cloudEntryScreenConfig = new ConfigSerializableDelegate("cloud.cloud_entry_screen_config", new AnonymousClass1("cloud.cloud_entry_screen_config"), MailAppConfiguration$cloudEntryScreenConfig$3.INSTANCE, CloudEntryScreenConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.redirectToTelegramMigrationConfig = new ConfigSerializableDelegate("cloud.telegram_webview_config", new AnonymousClass1("cloud.telegram_webview_config"), MailAppConfiguration$redirectToTelegramMigrationConfig$3.INSTANCE, RedirectToTelegramMigrationConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.servicesBannerConfig = new ConfigSerializableDelegate("services_banner_config", new AnonymousClass1("services_banner_config"), MailAppConfiguration$servicesBannerConfig$3.INSTANCE, ServicesBannerConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.letterAiWriter = new ConfigSerializableDelegate("letter_ai_writer_config", new AnonymousClass1("letter_ai_writer_config"), MailAppConfiguration$letterAiWriter$3.INSTANCE, LetterAiWriterRemoteConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.omicronConfig = new ConfigSerializableDelegate(R7WebViewConfigInjector.WRAPPER_CONFIG, new AnonymousClass1(R7WebViewConfigInjector.WRAPPER_CONFIG), MailAppConfiguration$omicronConfig$3.INSTANCE, OmicronConfigImpl.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.billingConfiguration = new ConfigSerializableDelegate(Event.AREA, new AnonymousClass1(Event.AREA), MailAppConfiguration$billingConfiguration$3.INSTANCE, BillingConfiguration.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.personalizationConfig = new ConfigSerializableDelegate("personalization", new AnonymousClass1("personalization"), MailAppConfiguration$personalizationConfig$3.INSTANCE, PersonalizationConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.portal = LazyKt.lazy(new Function0() { // from class: ru.mail.config.f1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.portal_delegate$lambda$0(this.f84892a);
            }
        });
        this.writeToDevLogging = LazyKt.lazy(new Function0() { // from class: ru.mail.config.h1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.writeToDevLogging_delegate$lambda$0(this.f84902a);
            }
        });
        this.webViewConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.j1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.webViewConfig_delegate$lambda$0(this.f84913a);
            }
        });
        this.calendarTodoConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.k1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.calendarTodoConfig_delegate$lambda$0(this.f84917a);
            }
        });
        this.calendarConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.l1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.calendarConfig_delegate$lambda$0(this.f84922a);
            }
        });
        this.calendarPlatesConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.m1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.calendarPlatesConfig_delegate$lambda$0(this.f84926a);
            }
        });
        this.pulseConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.n1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.pulseConfig_delegate$lambda$0(this.f84931a);
            }
        });
        this.cloudConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.o1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.cloudConfig_delegate$lambda$0(this.f84936a);
            }
        });
        this.calendarWidgetConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.p1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.calendarWidgetConfig_delegate$lambda$0(this.f84941a);
            }
        });
        this.packagesToCheckInstalledApp = LazyKt.lazy(new Function0() { // from class: ru.mail.config.q1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.packagesToCheckInstalledApp_delegate$lambda$0(this.f84946a);
            }
        });
        this.metaThreadsStatus = LazyKt.lazy(new Function0() { // from class: ru.mail.config.r1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.metaThreadsStatus_delegate$lambda$0(this.f84951a);
            }
        });
        this.permittedCookies = LazyKt.lazy(new Function0() { // from class: ru.mail.config.u1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.permittedCookies_delegate$lambda$0(this.f84970a);
            }
        });
        this.mailsListViewConfig = new ConfigSerializableDelegate("mails_list_view", new AnonymousClass1("mails_list_view"), MailAppConfiguration$mailsListViewConfig$3.INSTANCE, MailsListViewConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.accountManagerAnalyticsConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.v1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.accountManagerAnalyticsConfig_delegate$lambda$0(this.f84977a);
            }
        });
        this.socialLoginConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.w1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.socialLoginConfig_delegate$lambda$0(this.f84982a);
            }
        });
        this.drawables = LazyKt.lazy(new Function0() { // from class: ru.mail.config.x1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.drawables_delegate$lambda$0(this.f84987a);
            }
        });
        this.strings = LazyKt.lazy(new Function0() { // from class: ru.mail.config.y1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.strings_delegate$lambda$0(this.f84992a);
            }
        });
        this.pushTypes = LazyKt.lazy(new Function0() { // from class: ru.mail.config.z1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.pushTypes_delegate$lambda$0(this.f84997a);
            }
        });
        this.distributors = LazyKt.lazy(new Function0() { // from class: ru.mail.config.a2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.distributors_delegate$lambda$0(this.f84867a);
            }
        });
        this.prefetcherDelayConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.b2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.prefetcherDelayConfig_delegate$lambda$0(this.f84872a);
            }
        });
        this.menuFabConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.c2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.menuFabConfig_delegate$lambda$0(this.f84878a);
            }
        });
        this.trustedUrls = LazyKt.lazy(new Function0() { // from class: ru.mail.config.d2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.trustedUrls_delegate$lambda$0(this.f84883a);
            }
        });
        this.filteredEmailUrls = LazyKt.lazy(new Function0() { // from class: ru.mail.config.g2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.filteredEmailUrls_delegate$lambda$0(this.f84898a);
            }
        });
        this.internalApiUrlsHandlers = LazyKt.lazy(new Function0() { // from class: ru.mail.config.h2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.internalApiUrlsHandlers_delegate$lambda$0(this.f84903a);
            }
        });
        this.plates = LazyKt.lazy(new Function0() { // from class: ru.mail.config.i2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.plates_delegate$lambda$0(this.f84909a);
            }
        });
        this.mailsListAttachPreviewsConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.j2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.mailsListAttachPreviewsConfig_delegate$lambda$0(this.f84914a);
            }
        });
        this.inAppReviewFullConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.k2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.inAppReviewFullConfig_delegate$lambda$0(this.f84918a);
            }
        });
        this.dynamicStrings = LazyKt.lazy(new Function0() { // from class: ru.mail.config.l2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.dynamicStrings_delegate$lambda$0(this.f84923a);
            }
        });
        this.bandwidthConstants = LazyKt.lazy(new Function0() { // from class: ru.mail.config.m2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.bandwidthConstants_delegate$lambda$0(this.f84927a);
            }
        });
        this.promoHighlightInfo = LazyKt.lazy(new Function0() { // from class: ru.mail.config.n2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.promoHighlightInfo_delegate$lambda$0(this.f84932a);
            }
        });
        this.categoryChangeBehavior = LazyKt.lazy(new Function0() { // from class: ru.mail.config.o2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.categoryChangeBehavior_delegate$lambda$0(this.f84937a);
            }
        });
        this.metaThreadMassOperationsConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.q2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.metaThreadMassOperationsConfig_delegate$lambda$0(this.f84947a);
            }
        });
        this.barActionsOrder = LazyKt.lazy(new Function0() { // from class: ru.mail.config.s2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.barActionsOrder_delegate$lambda$0(this.f84957a);
            }
        });
        this.notificationPromoRule = LazyKt.lazy(new Function0() { // from class: ru.mail.config.t2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.notificationPromoRule_delegate$lambda$0(storageProvider, this);
            }
        });
        this.promoFeaturesConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.u2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.promoFeaturesConfig_delegate$lambda$0(this.f84971a);
            }
        });
        this.deeplinkSmartReplies = LazyKt.lazy(new Function0() { // from class: ru.mail.config.v2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.deeplinkSmartReplies_delegate$lambda$0(this.f84978a);
            }
        });
        this.newActionsConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.w2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.newActionsConfig_delegate$lambda$0(this.f84983a);
            }
        });
        this.accountSettings = LazyKt.lazy(new Function0() { // from class: ru.mail.config.x2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.accountSettings_delegate$lambda$0(this.f84988a);
            }
        });
        this.phishingConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.y2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.phishingConfig_delegate$lambda$0(this.f84993a);
            }
        });
        this.omicronPromoList = LazyKt.lazy(new Function0() { // from class: ru.mail.config.z2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.omicronPromoList_delegate$lambda$0(this.f84998a);
            }
        });
        this.openInWebViewConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.b3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.openInWebViewConfig_delegate$lambda$0(this.f84873a);
            }
        });
        this.transactionCategoriesForSearch = LazyKt.lazy(new Function0() { // from class: ru.mail.config.c3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.transactionCategoriesForSearch_delegate$lambda$0(this.f84879a);
            }
        });
        this.categoriesForSearch = LazyKt.lazy(new Function0() { // from class: ru.mail.config.e3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.categoriesForSearch_delegate$lambda$0(this.f84890a);
            }
        });
        this.schedule = LazyKt.lazy(new Function0() { // from class: ru.mail.config.f3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.schedule_delegate$lambda$0(this.f84894a);
            }
        });
        this.notificationSettingsManufacturers = LazyKt.lazy(new Function0() { // from class: ru.mail.config.g3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.notificationSettingsManufacturers_delegate$lambda$0(this.f84899a);
            }
        });
        this.stickers = LazyKt.lazy(new Function0() { // from class: ru.mail.config.h3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.stickers_delegate$lambda$0(this.f84904a);
            }
        });
        this.omicronConfigHash = getDtoConfiguration().getDtoConfig().getCondS();
        this.omicronConfigVersion = getDtoConfiguration().getDtoConfig().getConfigV();
        this.existingLoginSuppressedOauth = LazyKt.lazy(new Function0() { // from class: ru.mail.config.i3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.existingLoginSuppressedOauth_delegate$lambda$0(this.f84910a);
            }
        });
        this.newLoginSuppressedOauth = LazyKt.lazy(new Function0() { // from class: ru.mail.config.j3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.newLoginSuppressedOauth_delegate$lambda$0(this.f84915a);
            }
        });
        this.securitySettingsDomains = LazyKt.lazy(new Function0() { // from class: ru.mail.config.k3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.securitySettingsDomains_delegate$lambda$0(this.f84919a);
            }
        });
        this.appWallSections = LazyKt.lazy(new Function0() { // from class: ru.mail.config.m3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.appWallSections_delegate$lambda$0(this.f84928a);
            }
        });
        this.notesConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.n3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.notesConfig_delegate$lambda$0(this.f84933a);
            }
        });
        this.anyFolderMassOpConfigWithUnread = LazyKt.lazy(new Function0() { // from class: ru.mail.config.o3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.anyFolderMassOpConfigWithUnread_delegate$lambda$0(this.f84938a);
            }
        });
        this.anyFolderMassOpConfigWithoutUnread = LazyKt.lazy(new Function0() { // from class: ru.mail.config.q3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.anyFolderMassOpConfigWithoutUnread_delegate$lambda$0(this.f84948a);
            }
        });
        this.searchMassOpConfigWithUnread = LazyKt.lazy(new Function0() { // from class: ru.mail.config.r3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.searchMassOpConfigWithUnread_delegate$lambda$0(this.f84953a);
            }
        });
        this.searchMassOpConfigWithoutUnread = LazyKt.lazy(new Function0() { // from class: ru.mail.config.s3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.searchMassOpConfigWithoutUnread_delegate$lambda$0(this.f84958a);
            }
        });
        this.mailViewOzonFeedConfig = new ConfigSerializableDelegate("mail_view_ozon_feed", new AnonymousClass1("mail_view_ozon_feed"), MailAppConfiguration$mailViewOzonFeedConfig$3.INSTANCE, MailViewOzonFeedDTO.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.walletAppConfig = new ConfigSerializableDelegate("wallet_app_config", new AnonymousClass1("wallet_app_config"), MailAppConfiguration$walletAppConfig$3.INSTANCE, WalletAppConfigDTO.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.checkBackInReceiptsMetaThread = new ConfigSerializableDelegate("checkback_in_receipts_metathread", new AnonymousClass1("checkback_in_receipts_metathread"), MailAppConfiguration$checkBackInReceiptsMetaThread$3.INSTANCE, CheckBackInReceiptsMetaThreadDTO.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.fullscreenMenuItemPromos = LazyKt.lazy(new Function0() { // from class: ru.mail.config.t3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.fullscreenMenuItemPromos_delegate$lambda$0(this.f84967a);
            }
        });
        this.clipboardPlatesConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.u3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.clipboardPlatesConfig_delegate$lambda$0(this.f84972a);
            }
        });
        this.analyzeDataAgreementConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.v3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.analyzeDataAgreementConfig_delegate$lambda$0(this.f84979a);
            }
        });
        this.reminderConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.l
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.reminderConfig_delegate$lambda$0(this.f84920a);
            }
        });
        this.newMailClipboardSuggestConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.m
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.newMailClipboardSuggestConfig_delegate$lambda$0(this.f84925a);
            }
        });
        this.pushCategoryMapper = LazyKt.lazy(new Function0() { // from class: ru.mail.config.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.pushCategoryMapper_delegate$lambda$0(this.f84929a);
            }
        });
        this.linksReplacementRules = LazyKt.lazy(new Function0() { // from class: ru.mail.config.o
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.linksReplacementRules_delegate$lambda$0(this.f84934a);
            }
        });
        this.appendingQueryParamsRules = LazyKt.lazy(new Function0() { // from class: ru.mail.config.q
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.appendingQueryParamsRules_delegate$lambda$0(this.f84944a);
            }
        });
        this.pdfViewerConfig = new ConfigSerializableDelegate("pdf_viewer", new AnonymousClass1("pdf_viewer"), MailAppConfiguration$pdfViewerConfig$3.INSTANCE, PdfViewerConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.redirectFromViewerToCloudConfig = new ConfigSerializableDelegate("cloud.redirect_from_viewer_to_cloud_config", new AnonymousClass1("cloud.redirect_from_viewer_to_cloud_config"), MailAppConfiguration$redirectFromViewerToCloudConfig$3.INSTANCE, RedirectFromViewerToCloudConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.tokenExchangeConfig = new ConfigSerializableDelegate("token_exchange_config", new AnonymousClass1("token_exchange_config"), MailAppConfiguration$tokenExchangeConfig$3.INSTANCE, TokenExchangeConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.mailAppDeepLinks = LazyKt.lazy(new Function0() { // from class: ru.mail.config.r
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.mailAppDeepLinks_delegate$lambda$0(this.f84949a);
            }
        });
        this.appUpdateInfo = LazyKt.lazy(new Function0() { // from class: ru.mail.config.s
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.appUpdateInfo_delegate$lambda$0(this.f84954a);
            }
        });
        this.techStat = LazyKt.lazy(new Function0() { // from class: ru.mail.config.t
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.techStat_delegate$lambda$0(this.f84962a);
            }
        });
        this.welcomeLoginScreen = LazyKt.lazy(new Function0() { // from class: ru.mail.config.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.welcomeLoginScreen_delegate$lambda$0(this.f84968a);
            }
        });
        this.contactCardConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.w
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.contactCardConfig_delegate$lambda$0(this.f84980a);
            }
        });
        this.quickActionConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.x
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.quickActionConfig_delegate$lambda$0(this.f84985a);
            }
        });
        this.enabledSounds = LazyKt.lazy(new Function0() { // from class: ru.mail.config.y
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.enabledSounds_delegate$lambda$0(this.f84990a);
            }
        });
        this.labelsForSearch = LazyKt.lazy(new Function0() { // from class: ru.mail.config.z
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.labelsForSearch_delegate$lambda$0(this.f84995a);
            }
        });
        this.csatConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.a0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.csatConfig_delegate$lambda$0(this.f84865a);
            }
        });
        this.csatListConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.i1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.csatListConfig_delegate$lambda$0(this.f84908a);
            }
        });
        this.csatTriggersListConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.t1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.csatTriggersListConfig_delegate$lambda$0(this.f84964a);
            }
        });
        this.totalCleanConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.e2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.totalCleanConfig_delegate$lambda$0(this.f84889a);
            }
        });
        this.newAuthSdkConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.p2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.newAuthSdkConfig_delegate$lambda$0(this.f84942a);
            }
        });
        this.newAuthorizationSdkConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.a3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.newAuthorizationSdkConfig_delegate$lambda$0(this.f84868a);
            }
        });
        this.mediascope = new DTOMediascopeMapper().mapEntity(getConfig().getMediascope());
        this.shareMailConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.l3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.shareMailConfig_delegate$lambda$0(this.f84924a);
            }
        });
        this.callInRegistrationConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.w3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.callInRegistrationConfig_delegate$lambda$0(this.f84984a);
            }
        });
        this.callUIRegistrationConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.v
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.callUIRegistrationConfig_delegate$lambda$0(this.f84975a);
            }
        });
        this.cloudQuotaConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.d0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.cloudQuotaConfig_delegate$lambda$0(this.f84881a);
            }
        });
        this.imapBannerConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.e0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.imapBannerConfig_delegate$lambda$0(this.f84887a);
            }
        });
        this.cloudSharedOverQuotaConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.g0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.cloudSharedOverQuotaConfig_delegate$lambda$0(this.f84896a);
            }
        });
        this.bonusConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.h0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.bonusConfig_delegate$lambda$0(this.f84901a);
            }
        });
        this.walletConfig = LazyKt.lazy(new Function0() { // from class: ru.mail.config.i0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.walletConfig_delegate$lambda$0(this.f84907a);
            }
        });
        this.webApps = LazyKt.lazy(new Function0() { // from class: ru.mail.config.j0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.webApps_delegate$lambda$0(this.f84912a);
            }
        });
        this.accountManagerSyncConfig = new ConfigSerializableDelegate("account_manager_sync_config", new AnonymousClass1("account_manager_sync_config"), MailAppConfiguration$accountManagerSyncConfig$3.INSTANCE, AccountManagerSyncConfigDTO.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.cryptoAwareFileRemoteConfig = new ConfigSerializableDelegate("crypto_aware_file_config", new AnonymousClass1("crypto_aware_file_config"), MailAppConfiguration$cryptoAwareFileRemoteConfig$3.INSTANCE, CryptoAwareFileRemoteConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.mailMessageContainerConfig = new ConfigSerializableDelegate("mail_message_container_config", new AnonymousClass1("mail_message_container_config"), MailAppConfiguration$mailMessageContainerConfig$3.INSTANCE, MailMessageContainerConfigDTO.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.promotionNotificationWithImageConfig = new ConfigSerializableDelegate("picture_promotion_notification_config", new AnonymousClass1("picture_promotion_notification_config"), MailAppConfiguration$promotionNotificationWithImageConfig$3.INSTANCE, Configuration.PromotionNotificationWithImageConfigImpl.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.overquotaInfoSheetConfig = new ConfigSerializableDelegate("shared_cloud_overquota.info_sheet", new AnonymousClass1("shared_cloud_overquota.info_sheet"), MailAppConfiguration$overquotaInfoSheetConfig$3.INSTANCE, OverquotaInfoSheetConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.sharedCloudLoggerRemoteConfig = new ConfigSerializableDelegate("shared_cloud_logger_remote_config", new AnonymousClass1("shared_cloud_logger_remote_config"), MailAppConfiguration$sharedCloudLoggerRemoteConfig$3.INSTANCE, SharedCloudLoggerRemoteConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.prettyEmailConfiguration = new ConfigSerializableDelegate("pretty_email_configuration", new AnonymousClass1("pretty_email_configuration"), MailAppConfiguration$prettyEmailConfiguration$3.INSTANCE, PrettyEmailConfiguration.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.prettyEmailPromoConfig = new ConfigSerializableDelegate("pretty_email_promo_config", new AnonymousClass1("pretty_email_promo_config"), MailAppConfiguration$prettyEmailPromoConfig$3.INSTANCE, PrettyEmailPromoConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.sendCancellationForBusinessmenConfig = new ConfigSerializableDelegate("send_cancellation_for_businessmen_config", new AnonymousClass1("send_cancellation_for_businessmen_config"), MailAppConfiguration$sendCancellationForBusinessmenConfig$3.INSTANCE, SendCancellationForBusinessmenConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.readVerificationForBusinessmenConfig = new ConfigSerializableDelegate("read_verification_for_businessmen_config", new AnonymousClass1("read_verification_for_businessmen_config"), MailAppConfiguration$readVerificationForBusinessmenConfig$3.INSTANCE, ReadVerificationForBusinessmenConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.networkCheckerConfig = new ConfigSerializableDelegate("network_checker_config", new AnonymousClass1("network_checker_config"), MailAppConfiguration$networkCheckerConfig$3.INSTANCE, NetworkCheckerConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.cloudLinksNavigatorParamsConfig = new ConfigSerializableDelegate("cloud_links_navigator_params_config", new AnonymousClass1("cloud_links_navigator_params_config"), MailAppConfiguration$cloudLinksNavigatorParamsConfig$3.INSTANCE, CloudLinksNavigatorParamsConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.imapPromoConfig = new ConfigSerializableDelegate("imap_promo_config", new AnonymousClass1("imap_promo_config"), MailAppConfiguration$imapPromoConfig$3.INSTANCE, ImapPromoConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.actionFarmAnalyticsConfig = new ConfigSerializableDelegate("action_farm_analytics_config", new AnonymousClass1("action_farm_analytics_config"), MailAppConfiguration$actionFarmAnalyticsConfig$3.INSTANCE, ActionFarmAnalyticsConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
        this.kotlettUrlRedirectConfig = new ConfigSerializableDelegate("kotlett_url_redirect_config", new AnonymousClass1("kotlett_url_redirect_config"), MailAppConfiguration$kotlettUrlRedirectConfig$3.INSTANCE, KotlettUrlRedirectConfig.INSTANCE.serializer(), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final R7OfficeConfig _init_$lambda$0() {
        return new R7OfficeConfig(false, (String) null, (String) null, (String) null, (List) null, (List) null, (List) null, false, (List) null, false, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, (String) null, false, false, false, 134217727, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final R7EditPromoConfig _init_$lambda$1() {
        return new R7EditPromoConfig(false, false, false, (String) null, (String) null, 31, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection accountManagerAnalyticsConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAccountManagerAnalyticsMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List accountSettings_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAccountSettingsMapper().mapEntity((List<? extends DTOConfiguration.Config.AccountSettings>) mailAppConfiguration.getConfig().getAccountSettings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List adDomains_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return Collections.unmodifiableList(mailAppConfiguration.getConfig().getAdDomains());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.AnalyzeDataAgreementConfig analyzeDataAgreementConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAnalyzeDataAgreementMapper().mapEntity(mailAppConfiguration.getConfig().getAnalyzeDataAgreement());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MassOperationToolBarConfiguration anyFolderMassOpConfigWithUnread_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMassOperationsMapper().mapEntity((DTOMassOperationsMapper.ActionsInfoProvider) new DTOMassOperationsAnyFolderWithUnread(mailAppConfiguration.getConfig().getMassOperationsAnyFolder()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MassOperationToolBarConfiguration anyFolderMassOpConfigWithoutUnread_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMassOperationsMapper().mapEntity((DTOMassOperationsMapper.ActionsInfoProvider) new DTOMassOperationsAnyFolderWithoutUnread(mailAppConfiguration.getConfig().getMassOperationsAnyFolder()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppUpdateConfig appUpdateInfo_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAppUpdateMapper().mapEntity(mailAppConfiguration.getConfig().getAppUpdate());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List appWallSections_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAppWallSectionsMapperWrapper().mapEntity((List<? extends DTOConfiguration.Config.AppWallSectionInfo>) mailAppConfiguration.getConfig().getAppWallSectionInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List appendingQueryParamsRules_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOAppendingQueryParamsRulesMapper().mapEntity((List<? extends DTOConfiguration.Config.AppendingQueryParamsRule>) mailAppConfiguration.getConfig().getAppendingQueryParamsRule());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BandwidthConstants bandwidthConstants_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOBandwidthMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map barActionsOrder_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOBarActionsMapper().mapEntity(mailAppConfiguration.getConfig().getBarActions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.BonusConfig bonusConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOBonusMapper(mailAppConfiguration.analyticsSender).mapEntity(mailAppConfiguration.getConfig().getBonus());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CalendarConfig calendarConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCalendarMapper(mailAppConfiguration.analyticsSender).mapEntity(mailAppConfiguration.getConfig().getCalendar());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CalendarPlatesConfig calendarPlatesConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCalendarPlatesConfigMapper().mapEntity(mailAppConfiguration.getConfig().getCalendarPlatesConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CalendarTodoConfig calendarTodoConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCalendarTodoMapper(mailAppConfiguration.analyticsSender).mapEntity(mailAppConfiguration.getConfig().getCalendarTodo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CalendarWidgetConfig calendarWidgetConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCalendarWidgetConfigMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CallInRegistrationConfig callInRegistrationConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCallInRegistrationSettingsMapper().mapEntity(mailAppConfiguration.getConfig().getCallinRegistrationSettings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CallUIRegistrationConfig callUIRegistrationConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCallUIRegistrationSettingsMapper().mapEntity(mailAppConfiguration.getConfig().getCalluiRegistrationSettings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List categoriesForSearch_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCategoriesMapper().mapEntity2(mailAppConfiguration.getConfig().getSearchCategories());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CategoryChangeBehavior categoryChangeBehavior_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCategoryChangeMapper().mapEntity(mailAppConfiguration.getConfig().getChangeCategoryConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ClipboardPlatesConfig clipboardPlatesConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOClipboardPlateConfigMapper().mapEntity(mailAppConfiguration.getConfig().getClipboardPlates());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CloudConfig cloudConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCloudMapper().mapEntity(mailAppConfiguration.getConfig(), mailAppConfiguration.r7OfficeConfigProvider.invoke(), mailAppConfiguration.r7EditPromoConfigProvider.invoke());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CloudQuotaConfig cloudQuotaConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCloudQuotaMapper().mapEntity(mailAppConfiguration.getConfig().getCloudQuota());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CloudOverQuotaConfig cloudSharedOverQuotaConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCloudSharedOverQuotaMapper().mapEntity(mailAppConfiguration.getConfig().getSharedCloudOverquota());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Field configurationDataFiled_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return mailAppConfiguration.configurationSettingsDelegate.createDefinition(mailAppConfiguration.getDtoConfiguration().getDtoConfig()).asObject().getField("config");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ContactCardConfig contactCardConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOContactCardConfigMapper().mapEntity(mailAppConfiguration.getConfig().getContactCard());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CsatConfig csatConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCsatMapper().mapEntity(mailAppConfiguration.getConfig().getCurrentSurvey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CsatListConfig csatListConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCsatListMapper().mapEntity(mailAppConfiguration.getConfig().getSurveys());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.CsatTriggersListConfig csatTriggersListConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCsatTriggersMapper().mapEntity(mailAppConfiguration.getConfig().getSurveysTriggersList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DarkThemeConfig darkThemeConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTODarkThemeConfigMapper().mapEntity(mailAppConfiguration.getConfig().getDarkTheme());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List deeplinkSmartReplies_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTODeeplinkSmartReplyMapper().mapEntity((List<? extends DTOConfiguration.Config.DeeplinkSmartReply>) mailAppConfiguration.getConfig().getDeeplinkSmartReply());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List distributors_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTODistributorMapper().mapEntity(mailAppConfiguration.getConfig().getDistributorAnchores());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DogfoodingConfig dogfoodingConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        DTOConfiguration.Config.Dogfooding dogfooding = mailAppConfiguration.getConfig().getDogfooding();
        return new DogfoodingConfig(dogfooding.getDogfoodingEnabled(), dogfooding.getDogfoodingText(), dogfooding.getOpenText(), dogfooding.getCloseText(), dogfooding.getDogfoodingForce(), dogfooding.getDogfoodingUrl(), dogfooding.getDialogTimeout());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection drawables_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTODrawablesMapper().mapEntity(mailAppConfiguration.getConfig().getResources().getDrawable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StringsMemcache dynamicStrings_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTODynamicStringsMapper().mapEntity(mailAppConfiguration.getConfig().getDynamicStrings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HashSet enabledAssertions_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new HashSet(mailAppConfiguration.getConfig().getEnabledAssertions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set enabledSounds_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOSoundsMapper().mapEntity(mailAppConfiguration.getConfig().getEnabledSounds());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pattern existingLoginSuppressedOauth_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPatternMapper().mapEntity(mailAppConfiguration.getConfig().getAuthFlow().getExistingLoginsSuppressOauth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FastReplyConfig fastReplyConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new FastReplyConfig(mailAppConfiguration.getConfig().getFastReply());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map filteredEmailUrls_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOTrustedUrlsMapper().mapEntity(mailAppConfiguration.getConfig().getFilteredEmailUrls());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List fullscreenMenuItemPromos_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOFullscreenMenuItemPromoMapper().mapEntity((List<? extends DTOConfiguration.Config.FullscreenMenuItemPromos>) mailAppConfiguration.getConfig().getFullscreenMenuItemPromos());
    }

    private final String getStringConfig() {
        return (String) this.stringConfig.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.HitmanConfig hitmanConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.HitmanConfig(mailAppConfiguration.getConfig().getHitmanConfig().getEnabled(), mailAppConfiguration.getConfig().getHitmanConfig().getDefaultUserAgent(), mailAppConfiguration.getConfig().getHitmanConfig().getUseHostKey(), mailAppConfiguration.getConfig().getHitmanConfig().getUseSync(), mailAppConfiguration.getConfig().getHitmanConfig().getExpireTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.HuaweiWebViewErrorConfig huaweiWebViewErrorConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        DTOConfiguration.Config.HuaweiWebviewError huaweiWebviewError = mailAppConfiguration.getConfig().getHuaweiWebviewError();
        return new Configuration.HuaweiWebViewErrorConfig(huaweiWebviewError.getDialogEnabled(), huaweiWebviewError.getDialogTitle(), huaweiWebviewError.getDialogText(), huaweiWebviewError.getTimeout(), huaweiWebviewError.getWebViewInitTimeout(), huaweiWebviewError.getWebViewDoesntWorkTimeout(), huaweiWebviewError.getNewDetectEnabled(), huaweiWebviewError.getInstructionUrl(), huaweiWebviewError.getInstructionButtonText(), huaweiWebviewError.getCloseButtonText(), huaweiWebviewError.getReadInTabsButtonEnabled(), huaweiWebviewError.getReadInTabsUrl(), huaweiWebviewError.getReadInTabsButtonText(), huaweiWebviewError.getReadInTabsErrorText(), huaweiWebviewError.getGeckoViewFeatureEnabled(), huaweiWebviewError.getDownloadGeckoButtonEnabled(), huaweiWebviewError.getDownloadGeckoButtonText(), huaweiWebviewError.getGeckoLoadingDelay(), huaweiWebviewError.getCheckInstaller(), huaweiWebviewError.getAvailableInstallers());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ImapBannerConfig imapBannerConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOImapBannerMapper().mapEntity(mailAppConfiguration.getConfig().getImapBannerConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List inAppReviewFullConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOInAppReviewFullConfigMapper().mapEntity(mailAppConfiguration.getConfig().getInAppReviewFullConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map internalApiUrlsHandlers_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOInternalApiUrlsMapper().mapEntity2(mailAppConfiguration.getConfig().getInternalApiUrlsHandlers());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsScriptFetcherConfig jsScriptFetchingConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        DTOConfiguration.Config.JsScriptFetcher jsScriptFetcher = mailAppConfiguration.getConfig().getJsScriptFetcher();
        return new JsScriptFetcherConfig(jsScriptFetcher.getUpdateScriptEnable(), jsScriptFetcher.getJsScriptVersion(), jsScriptFetcher.getJsScriptCheckSum(), jsScriptFetcher.getJsScriptDownloadUrl(), jsScriptFetcher.getDomPurifyInterceptorEnable(), jsScriptFetcher.getDomPurifyVersion(), jsScriptFetcher.getJsCoreTransformSchemeEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List labelsForSearch_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCategoriesMapper().mapEntity2(mailAppConfiguration.getConfig().getLabelsForSearch());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List liberoDomains_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return Collections.unmodifiableList(mailAppConfiguration.getConfig().getDomainsForLiberoApi());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.LicenseAgreementConfig licenseAgreementConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOLicenseAgreementMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List linksReplacementRules_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOLinksReplacementRulesMapper().mapEntity((List<? extends DTOConfiguration.Config.LinksReplacementRule>) mailAppConfiguration.getConfig().getLinksReplacementRule());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List mailAppDeepLinks_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMailAppDeepLinkMapper().mapEntity((List<? extends DTOConfiguration.Config.MailAppDeepLink>) mailAppConfiguration.getConfig().getMailAppDeepLink());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MailsListAttachPreviewsConfig mailsListAttachPreviewsConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMailsListAttachPreviewsMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MenuFabConfig menuFabConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMenuFabConfigMapper().mapEntity(mailAppConfiguration.getConfig().getFab());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MetaThreadMassOperationsConfig metaThreadMassOperationsConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMetaThreadMassOperationsMapper().mapEntity((List<? extends DTOConfiguration.Config.MassOperationsMetaThread>) mailAppConfiguration.getConfig().getMassOperationsMetaThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MetaThreadsStatus metaThreadsStatus_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMetaThreadsStatusMapper().mapEntity(mailAppConfiguration.getConfig().getMetaThreadConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.NewActionsConfig newActionsConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONewActionsMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NewAuthSdkConfig newAuthSdkConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONewAuthSdkMapper().mapEntity(mailAppConfiguration.getConfig().getNewAuthSdkConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NewAuthorizationSdkConfig newAuthorizationSdkConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONewAuthorizationSdkMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pattern newLoginSuppressedOauth_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPatternMapper().mapEntity(mailAppConfiguration.getConfig().getAuthFlow().getNewLoginsSuppressOauth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.NewMailClipboardConfig newMailClipboardSuggestConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONewMailClipboardMapper().mapEntity(mailAppConfiguration.getConfig().getSuggestsFromClipboard());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.NotesConfig notesConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONotesMapper(mailAppConfiguration.analyticsSender).mapEntity(mailAppConfiguration.getConfig().getNotes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ShowRule notificationPromoRule_delegate$lambda$0(StorageProvider storageProvider, MailAppConfiguration mailAppConfiguration) {
        return new DTONotificationPromoPlateMapper(storageProvider, new TimeUtils.Time()).mapEntity(mailAppConfiguration.getConfig().getNotificationFilterPromo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List notificationSettingsManufacturers_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTONotificationSettingsMapper().mapEntity(mailAppConfiguration.getConfig().getNotificationSettingManufacturer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List omicronPromoList_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOOmicronPromoMapper().mapEntity(mailAppConfiguration.getConfig().getOmicronPromoPlates());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.OmicronPromoSettings omicronPromoSettings_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.OmicronPromoSettings(mailAppConfiguration.getConfig().getOmicronPromoPlates().getStatisticsRequestTimeout());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.OpenInWebViewConfig openInWebViewConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOOpenInWebViewConfigMapper().mapEntity(mailAppConfiguration.getConfig().getOpenInWebview());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List packagesToCheckInstalledApp_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPackageCheckerItemMapper().mapEntity(mailAppConfiguration.getConfig().getInstalledPackages());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List permittedCookies_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPermittedCookiesMapper().mapEntity((List<? extends DTOConfiguration.Config.PermittedCookies>) mailAppConfiguration.getConfig().getPermittedCookies());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.PhishingConfig phishingConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPhishingConfigMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection plates_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPlateMapperWrapper().mapEntity(mailAppConfiguration.getConfig().getPromo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.Portal portal_delegate$lambda$0(final MailAppConfiguration mailAppConfiguration) {
        return new DTOPortalMapper((PortalMailAppConfiguration.SplitScreenConfigDTO) new ConfigSerializableDelegate("split_screen_config", new Function0() { // from class: ru.mail.config.c0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration.portal_delegate$lambda$0$1(this.f84876a);
            }
        }, MailAppConfiguration$portal$2$2.INSTANCE, PortalMailAppConfiguration.SplitScreenConfigDTO.INSTANCE.serializer(), mailAppConfiguration.analyticsSender, OmicronSerializationKt.getRemoteConfigJson()).get()).mapEntity(mailAppConfiguration.getConfig().getPortal());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String portal_delegate$lambda$0$1(MailAppConfiguration mailAppConfiguration) {
        return mailAppConfiguration.getConfig().getPortal().getSplitScreenConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.PrefetcherDelayConfig prefetcherDelayConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPrefetcherDelayMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List promoFeaturesConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPromoFeaturesMapper().mapEntity((List<? extends DTOConfiguration.Config.PromoFeatureConfig>) mailAppConfiguration.getConfig().getPromoFeatureConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.PromoHighlightInfo promoHighlightInfo_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPromoHighlightMapper().mapEntity(mailAppConfiguration.getConfig().getHighlights());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.PulseConfig pulseConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPulseConfigMapper().mapEntity(mailAppConfiguration.getConfig().getPulse());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.PushCategoryMapper pushCategoryMapper_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPushCategoryMapper().mapEntity((List<? extends DTOConfiguration.Config.PushCategoryMap>) mailAppConfiguration.getConfig().getPushCategoryMap());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List pushTypes_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPushConfigurationMapper().mapEntity((List<? extends DTOConfiguration.Config.PushTypes>) mailAppConfiguration.getConfig().getPushTypes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.QuickActionConfig quickActionConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOQuickActionMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ReadVerify readVerify_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.ReadVerify(mailAppConfiguration.getConfig().getReadVerifyConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ReleaseFetcherConfig releaseFetcherConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        DTOConfiguration.Config.ReleaseFetcher releaseFetcher = mailAppConfiguration.getConfig().getReleaseFetcher();
        return new ReleaseFetcherConfig(releaseFetcher.getEnabled(), releaseFetcher.getReleasesInfoUrl(), releaseFetcher.getUpdateDelay(), releaseFetcher.getWithActual());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List relocationAgreementConfigs_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTORelocationsAgreementMapper().mapEntity((List<? extends DTOConfiguration.Config.RelocationAgreements>) mailAppConfiguration.getConfig().getRelocationAgreements());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ReminderConfiguration reminderConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOLetterReminderConfigMapper().mapEntity(mailAppConfiguration.getConfig().getImportantLetterReminder());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.RestoreAuthFlowConfig restoreAuthFlowConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.RestoreAuthFlowConfig(mailAppConfiguration.getConfig().getRestoreAuthFlowConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.RetrofitConfig retrofitConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.RetrofitConfig(mailAppConfiguration.getConfig().getRetrofitConfig().getRethrowRequestSessionException());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.RuStoreSdkConfig ruStoreSdkConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.RuStoreSdkConfig(mailAppConfiguration.getConfig().getRustoreSdk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.Schedule schedule_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOScheduledSendMapper().mapEntity(mailAppConfiguration.getConfig().getScheduleSendConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MassOperationToolBarConfiguration searchMassOpConfigWithUnread_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMassOperationsMapper().mapEntity((DTOMassOperationsMapper.ActionsInfoProvider) new DTOMassOperationsSearchWithUnread(mailAppConfiguration.getConfig().getMassOperationsSearch()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.MassOperationToolBarConfiguration searchMassOpConfigWithoutUnread_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOMassOperationsMapper().mapEntity((DTOMassOperationsMapper.ActionsInfoProvider) new DTOMassOperationsSearchWithoutUnread(mailAppConfiguration.getConfig().getMassOperationsSearch()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pattern securitySettingsDomains_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOPatternMapper().mapEntity(mailAppConfiguration.getConfig().getSecuritySettingsDomains());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List sendHttpRequestAnalyticEventsFilter_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOSendHttpRequestAnalyticEventsFilterMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    private final /* synthetic */ <T, R extends T> ConfigSerializableDelegate<T, R> serializable(String key, Function0<? extends R> function0, Function0<String> jsonProvider) {
        Intrinsics.reifiedOperationMarker(6, "R");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
        return new ConfigSerializableDelegate<>(key, jsonProvider, function0, SerializersKt.serializer((KType) null), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
    }

    static /* synthetic */ ConfigSerializableDelegate serializable$default(MailAppConfiguration mailAppConfiguration, String str, Function0 function0, Function0 function1, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            function1 = mailAppConfiguration.new AnonymousClass1(str);
        }
        Intrinsics.reifiedOperationMarker(6, "R");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
        return new ConfigSerializableDelegate(str, function1, function0, SerializersKt.serializer((KType) null), mailAppConfiguration.analyticsSender, OmicronSerializationKt.getRemoteConfigJson());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.ShareMailConfig shareMailConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOShareMailConfigMapper().mapEntity(mailAppConfiguration.getConfig().getShareMail());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List showQuotaRegions_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return Collections.unmodifiableList(mailAppConfiguration.getConfig().getShowCloudQuotaRegion());
    }

    @Deprecated(message = "Use serializable @see https://jira.vk.team/browse/ANDMAIL-19365")
    private final /* synthetic */ <T, R extends T> ConfigSerializableDelegate<T, R> snakeSerializable(String key, Function0<? extends R> function0) {
        C20591 c20591 = new C20591(key);
        Intrinsics.reifiedOperationMarker(6, "R");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
        return new ConfigSerializableDelegate<>(key, c20591, function0, SerializersKt.serializer((KType) null), this.analyticsSender, OmicronSerializationKt.getRemoteConfigJsonSnake());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.SocialLoginConfig socialLoginConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOSocialLoginMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List stickers_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOStickersMapper().mapEntity(mailAppConfiguration.getConfig().getStickerPack());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.Stories stories_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOStoriesMapper().mapEntity(mailAppConfiguration.getConfig().getStories());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String stringConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        Object objM13123constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(mailAppConfiguration.getDtoConfiguration().getRawConfig().toString());
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
            objM13123constructorimpl = null;
        }
        String str = (String) objM13123constructorimpl;
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection strings_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOStringsMapper().mapEntity(mailAppConfiguration.getConfig().getResources().getStrings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.SubscriptionConfig subscription_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOSubscriptionConfigMapper().mapEntity(mailAppConfiguration.getConfig().getSubscription());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.Summarize summarize_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.Summarize(mailAppConfiguration.getConfig().getSummarize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.TechStatConfig techStat_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOTechStatConfigMapper().mapEntity(mailAppConfiguration.getConfig().getTechStats());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.TotalCleanConfig totalCleanConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOTotalCleanFolderIdsMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List transactionCategoriesForSearch_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOCategoriesMapper().mapEntity2(mailAppConfiguration.getConfig().getSearchTransactionCategories());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.TrustedMailConfig trustedMailConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new Configuration.TrustedMailConfig(mailAppConfiguration.getConfig().getTrustedMailConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map trustedUrls_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOTrustedUrlsMapper().mapEntity(mailAppConfiguration.getConfig().getTrustedUrls());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.UserThemeData userThemeData_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOUserThemeMapper().mapEntity(mailAppConfiguration.getConfig().getUserThemes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List virgilioDomains_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return Collections.unmodifiableList(mailAppConfiguration.getConfig().getDomainsForVirgilioApi());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.VkpnsHostSdk vkpnsHostSdk_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOVkpnsPushSdkConfigMapper().mapEntity(mailAppConfiguration.getConfig().getVkpnsHostSdk());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.WalletConfig walletConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOWalletMapper(mailAppConfiguration.analyticsSender).mapEntity(mailAppConfiguration.getConfig().getWallet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.TabWebAppsConfig webApps_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOWebAppsMapper().mapEntity(mailAppConfiguration.getConfig().getWebApps());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.WebViewConfig webViewConfig_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOWebConfigMapper().mapEntity(mailAppConfiguration.getConfig().getWebviewConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.WelcomeLoginScreen welcomeLoginScreen_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOWelcomeLoginMapper().mapEntity(mailAppConfiguration.getConfig().getWelcomeLoginScreen());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.WriteToDevLogging writeToDevLogging_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return new DTOWriteToDevLoggingMapper().mapEntity(mailAppConfiguration.getConfig());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Configuration.XmailMigrationEntryPointExp xmailMigrationEntryPointExp_delegate$lambda$0(MailAppConfiguration mailAppConfiguration) {
        return Configuration.XmailMigrationEntryPointExp.INSTANCE.from(mailAppConfiguration.getConfig().getXmailMigrationEntryPointExp());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(MailAppConfiguration.class, other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ru.mail.config.MailAppConfiguration");
        MailAppConfiguration mailAppConfiguration = (MailAppConfiguration) other;
        return Intrinsics.areEqual(getDtoConfiguration().getDtoConfig().getConfigV(), mailAppConfiguration.getDtoConfiguration().getDtoConfig().getConfigV()) && Intrinsics.areEqual(getDtoConfiguration().getDtoConfig().getCondS(), mailAppConfiguration.getDtoConfiguration().getDtoConfig().getCondS()) && Intrinsics.areEqual(getStringConfig(), mailAppConfiguration.getStringConfig());
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Collection<Configuration.AccountManagerAnalytics> getAccountManagerAnalyticsConfig() {
        return (Collection) this.accountManagerAnalyticsConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AccountManagerDelegate getAccountManagerDelegate() {
        return super.getAccountManagerDelegate();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getAccountManagerTypesForSignInSuggests() {
        return super.getAccountManagerTypesForSignInSuggests();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.AccountSettingsItem> getAccountSettings() {
        return (List) this.accountSettings.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AccountsPopup getAccountsPopupConfig() {
        return super.getAccountsPopupConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ActionFarmAnalyticsConfig getActionFarmAnalyticsConfig() {
        return (ActionFarmAnalyticsConfig) this.actionFarmAnalyticsConfig.getValue(this, $$delegatedProperties[33]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<String> getAdDomains() {
        Object value = this.adDomains.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AdditionalAppSizeTracking getAdditionalAppSizeTrackingConfig() {
        return super.getAdditionalAppSizeTrackingConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AddressBook getAddressBookConfig() {
        return super.getAddressBookConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getAgreementUrl() {
        return super.getAgreementUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AllowedAdsManagement getAllowedAdsManagement() {
        return super.getAllowedAdsManagement();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AmpConfig getAmpConfig() {
        return super.getAmpConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.AnalyzeDataAgreementConfig getAnalyzeDataAgreementConfig() {
        return (Configuration.AnalyzeDataAgreementConfig) this.analyzeDataAgreementConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AndroidOsSystemFeatureConfig getAndroidOsSystemFeatureConfig() {
        return super.getAndroidOsSystemFeatureConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MassOperationToolBarConfiguration getAnyFolderMassOpConfigWithUnread() {
        Object value = this.anyFolderMassOpConfigWithUnread.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.MassOperationToolBarConfiguration) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MassOperationToolBarConfiguration getAnyFolderMassOpConfigWithoutUnread() {
        Object value = this.anyFolderMassOpConfigWithoutUnread.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.MassOperationToolBarConfiguration) value;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ long getAppMetricsTrackerAnrTimeout() {
        return super.getAppMetricsTrackerAnrTimeout();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AppSyncConfig getAppSettingsSyncIntervals() {
        return super.getAppSettingsSyncIntervals();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public AppUpdateConfig getAppUpdateInfo() {
        Object value = this.appUpdateInfo.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (AppUpdateConfig) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.AppWallSection> getAppWallSections() {
        return (List) this.appWallSections.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.AppendingQueryParamsRule> getAppendingQueryParamsRules() {
        return (List) this.appendingQueryParamsRules.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AutoUploadOnMailsList getAutoUploadOnMailsList() {
        return super.getAutoUploadOnMailsList();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AutoUploadPromoConfig getAutoUploadPromoConfig() {
        return super.getAutoUploadPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public BandwidthConstants getBandwidthConstants() {
        Object value = this.bandwidthConstants.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (BandwidthConstants) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Map<BarPlace, Configuration.BarActionsOrder> getBarActionsOrder() {
        return (Map) this.barActionsOrder.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getBehaviorName() {
        return super.getBehaviorName();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.BigBundleSaveConfig getBigBundleSaveConfig() {
        return super.getBigBundleSaveConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public BillingConfiguration getBillingConfiguration() {
        return (BillingConfiguration) this.billingConfiguration.getValue(this, $$delegatedProperties[11]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.BonusConfig getBonusConfig() {
        return (Configuration.BonusConfig) this.bonusConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ByteTooltip getByteTooltipConfig() {
        return super.getByteTooltipConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CalendarConfig getCalendarConfig() {
        return (Configuration.CalendarConfig) this.calendarConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.CalendarNotificationConfig getCalendarNotificationConfig() {
        return super.getCalendarNotificationConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CalendarPlatesConfig getCalendarPlatesConfig() {
        return (Configuration.CalendarPlatesConfig) this.calendarPlatesConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CalendarTodoConfig getCalendarTodoConfig() {
        return (Configuration.CalendarTodoConfig) this.calendarTodoConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @Nullable
    public /* bridge */ String getCalendarWebConfig() {
        return super.getCalendarWebConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CalendarWidgetConfig getCalendarWidgetConfig() {
        return (Configuration.CalendarWidgetConfig) this.calendarWidgetConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CallInRegistrationConfig getCallInRegistrationConfig() {
        return (Configuration.CallInRegistrationConfig) this.callInRegistrationConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CallUIRegistrationConfig getCallUIRegistrationConfig() {
        return (Configuration.CallUIRegistrationConfig) this.callUIRegistrationConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ CallerIdentificationConfig getCallerIdentificationConfig() {
        return super.getCallerIdentificationConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getCameraAutoUploadRegexPromoEnabled() {
        return super.getCameraAutoUploadRegexPromoEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<MailItemTransactionCategory> getCategoriesForSearch() {
        return (List) this.categoriesForSearch.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CategoryChangeBehavior getCategoryChangeBehavior() {
        Object value = this.categoryChangeBehavior.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.CategoryChangeBehavior) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.CategoryFeedbackConfig getCategoryFeedbackConfig() {
        return super.getCategoryFeedbackConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getCleanMasterUrl() {
        return super.getCleanMasterUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ClickerConfig getClickerConfig() {
        return super.getClickerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ClipboardPlatesConfig getClipboardPlatesConfig() {
        return (Configuration.ClipboardPlatesConfig) this.clipboardPlatesConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CloudCleanupConfig getCloudCleanupConfig() {
        return (CloudCleanupConfig) this.cloudCleanupConfig.getValue(this, $$delegatedProperties[5]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CloudConfig getCloudConfig() {
        return (CloudConfig) this.cloudConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CloudEntryScreenConfig getCloudEntryScreenConfig() {
        return (CloudEntryScreenConfig) this.cloudEntryScreenConfig.getValue(this, $$delegatedProperties[6]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CloudLinksNavigatorParamsConfig getCloudLinksNavigatorParamsConfig() {
        return (CloudLinksNavigatorParamsConfig) this.cloudLinksNavigatorParamsConfig.getValue(this, $$delegatedProperties[31]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CloudQuotaConfig getCloudQuotaConfig() {
        return (Configuration.CloudQuotaConfig) this.cloudQuotaConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CloudOverQuotaConfig getCloudSharedOverQuotaConfig() {
        return (Configuration.CloudOverQuotaConfig) this.cloudSharedOverQuotaConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getCodeAuthUrl() {
        return super.getCodeAuthUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ColoredTagsConfig getColoredTagsConfig() {
        return super.getColoredTagsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public DTOConfiguration.Config getConfig() {
        return this.config;
    }

    @Override // ru.mail.config.ConfigurationWithRawData
    @NotNull
    public Field getConfigurationDataFiled() {
        return (Field) this.configurationDataFiled.getValue();
    }

    @Override // ru.mail.config.ConfigurationWithRawData
    @NotNull
    public JSONObject getConfigurationDataObject() {
        return this.configurationDataObject;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getConnectionSamplingPeriodSeconds() {
        return super.getConnectionSamplingPeriodSeconds();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ContactCardConfig getContactCardConfig() {
        return (Configuration.ContactCardConfig) this.contactCardConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ContactsExport getContactsExportConfig() {
        return super.getContactsExportConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ContactsOrm getContactsOrm() {
        return super.getContactsOrm();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getContactsPageSize() {
        return super.getContactsPageSize();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getContactsRequestAgreementUsage() {
        return super.getContactsRequestAgreementUsage();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getCopyrightYear() {
        return super.getCopyrightYear();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getCorpConfidentUrl() {
        return super.getCorpConfidentUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getCovidUrl() {
        return super.getCovidUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.CreateEventFromEmailConfig getCreateEventFromEmailConfig() {
        return super.getCreateEventFromEmailConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.CreateNoteFromMail getCreateNoteFromMail() {
        return super.getCreateNoteFromMail();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CsatConfig getCsatConfig() {
        return (Configuration.CsatConfig) this.csatConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CsatListConfig getCsatListConfig() {
        return (Configuration.CsatListConfig) this.csatListConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.CsatTriggersListConfig getCsatTriggersListConfig() {
        return (Configuration.CsatTriggersListConfig) this.csatTriggersListConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public DarkThemeConfig getDarkThemeConfig() {
        return (DarkThemeConfig) this.darkThemeConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.DeeplinkSmartReply> getDeeplinkSmartReplies() {
        return (List) this.deeplinkSmartReplies.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getDeleteAccountUrl() {
        return super.getDeleteAccountUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Distributor> getDistributors() {
        Object value = this.distributors.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getDkimMoreUrl() {
        return super.getDkimMoreUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.DkimWarning getDkimWarning() {
        return super.getDkimWarning();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public DogfoodingConfig getDogfoodingConfig() {
        return (DogfoodingConfig) this.dogfoodingConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getDomainsForSignInSuggests() {
        return super.getDomainsForSignInSuggests();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getDomainsWithoutImapCheckHotfix() {
        return super.getDomainsWithoutImapCheckHotfix();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Collection<DrawableResEntry> getDrawables() {
        Object value = this.drawables.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Collection) value;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getDrawerScrollAngle() {
        return super.getDrawerScrollAngle();
    }

    @Override // ru.mail.config.ConfigurationWithRawData
    @NotNull
    public DTORawConfiguration getDtoConfiguration() {
        return this.dtoConfiguration;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public StringsMemcache getDynamicStrings() {
        Object value = this.dynamicStrings.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (StringsMemcache) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.EditModeTutorial getEditModeTutorial() {
        return super.getEditModeTutorial();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.EmailToMyselfSuggestions getEmailToMySelfSuggestionsConfig() {
        return super.getEmailToMySelfSuggestionsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.EmptyState getEmptyStateConfig() {
        return super.getEmptyStateConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Set<String> getEnabledAssertions() {
        return (Set) this.enabledAssertions.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getEnabledRecreateOrmContentProvider() {
        return super.getEnabledRecreateOrmContentProvider();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.EsiaConfig getEsiaConfig() {
        return super.getEsiaConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Pattern getExistingLoginSuppressedOauth() {
        Object value = this.existingLoginSuppressedOauth.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Pattern) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public FastReplyConfig getFastReplyConfig() {
        return (FastReplyConfig) this.fastReplyConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.FeedbackInAccountDrawerConfiguration getFeedbackInAccountDrawerConfig() {
        return super.getFeedbackInAccountDrawerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Map<String, Pattern> getFilteredEmailUrls() {
        Object value = this.filteredEmailUrls.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Map) value;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getFixEndlessAdapter() {
        return super.getFixEndlessAdapter();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.FoldersDrawerConfig getFoldersDrawerConfig() {
        return super.getFoldersDrawerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public FreemiumConfig getFreemiumConfig() {
        return (FreemiumConfig) this.freemiumConfig.getValue(this, $$delegatedProperties[0]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.FullscreenMenuItemPromo> getFullscreenMenuItemPromos() {
        return (List) this.fullscreenMenuItemPromos.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getGalleryMaxSelectionCount() {
        return super.getGalleryMaxSelectionCount();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getGlideCacheSizeKb() {
        return super.getGlideCacheSizeKb();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.GoToSpamDialogConfig getGoToSpamDialogConfig() {
        return super.getGoToSpamDialogConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.GptBirthdayPromo getGptBirthdayPromoConfig() {
        return super.getGptBirthdayPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.GptProject getGptProjectConfig() {
        return super.getGptProjectConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getHelpLink() {
        return super.getHelpLink();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.HideLoginServices getHideLoginServicesConfig() {
        return super.getHideLoginServicesConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.HitmanConfig getHitmanConfig() {
        return (Configuration.HitmanConfig) this.hitmanConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.HuaweiWebViewErrorConfig getHuaweiWebViewErrorConfig() {
        return (Configuration.HuaweiWebViewErrorConfig) this.huaweiWebViewErrorConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ImapBannerConfig getImapBannerConfig() {
        return (Configuration.ImapBannerConfig) this.imapBannerConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ImapPromoConfig getImapPromoConfig() {
        return (ImapPromoConfig) this.imapPromoConfig.getValue(this, $$delegatedProperties[32]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<InAppReviewStoreConfig> getInAppReviewFullConfig() {
        return (List) this.inAppReviewFullConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.InitExecutionTimeTracker getInitExecutionTimeTrackerConfig() {
        return super.getInitExecutionTimeTrackerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.IntegrationPlateConfig getIntegrationPlateConfig() {
        return super.getIntegrationPlateConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Map<String, Configuration.InternalApiHandler> getInternalApiUrlsHandlers() {
        return (Map) this.internalApiUrlsHandlers.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public JsScriptFetcherConfig getJsScriptFetchingConfig() {
        return (JsScriptFetcherConfig) this.jsScriptFetchingConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.KasperskyConfig getKasperskyConfig() {
        return super.getKasperskyConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.KasperskyLogoInSettings getKasperskyLogoInSettings() {
        return super.getKasperskyLogoInSettings();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getKotlettAccountsPromoSectionEnabled() {
        return super.getKotlettAccountsPromoSectionEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.Kotlett getKotlettConfig() {
        return super.getKotlettConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getKotlettFarmEnabled() {
        return super.getKotlettFarmEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getKotlettOnboardingPromoEnabled() {
        return super.getKotlettOnboardingPromoEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.KotlettPlatesOnReadMailScreen getKotlettPlatesOnReadMailConfig() {
        return super.getKotlettPlatesOnReadMailConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public KotlettUrlRedirectConfig getKotlettUrlRedirectConfig() {
        return (KotlettUrlRedirectConfig) this.kotlettUrlRedirectConfig.getValue(this, $$delegatedProperties[34]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<MailItemTransactionCategory> getLabelsForSearch() {
        return (List) this.labelsForSearch.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.LeelooDesign getLeelooDesign() {
        return super.getLeelooDesign();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<String> getLiberoDomains() {
        Object value = this.liberoDomains.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.LicenseAgreementConfig getLicenseAgreementConfig() {
        return (Configuration.LicenseAgreementConfig) this.licenseAgreementConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.LinksReplacementRule> getLinksReplacementRules() {
        return (List) this.linksReplacementRules.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getLocalPushesFetchPeriodSeconds() {
        return super.getLocalPushesFetchPeriodSeconds();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.LocationPermissionDialog getLocationPermissionDialog() {
        return super.getLocationPermissionDialog();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.LoginButtonGmail getLoginButtonGmailConfig() {
        return super.getLoginButtonGmailConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.LoginServicesGmailExp getLoginServicesGmailConfig() {
        return super.getLoginServicesGmailConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getLoginSuggestedDomains() {
        return super.getLoginSuggestedDomains();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MailActivityLauncher getMailActivityLauncher() {
        return super.getMailActivityLauncher();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.MailAppDeepLink> getMailAppDeepLinks() {
        return (List) this.mailAppDeepLinks.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MailListLoad getMailListLoadConfig() {
        return super.getMailListLoadConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getMailPostponeLoaderEnabled() {
        return super.getMailPostponeLoaderEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getMailPostponeViewerEnabled() {
        return super.getMailPostponeViewerEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getMailViewInlineImagesDomains() {
        return super.getMailViewInlineImagesDomains();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MailViewInlineImagesResizeConfig getMailViewInlineImagesResizeConfig() {
        return super.getMailViewInlineImagesResizeConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getMailViewInlineImagesToLoadImmediate() {
        return super.getMailViewInlineImagesToLoadImmediate();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getMailViewPostponeReplyShow() {
        return super.getMailViewPostponeReplyShow();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MailsListAttachPreviewsConfig getMailsListAttachPreviewsConfig() {
        return (Configuration.MailsListAttachPreviewsConfig) this.mailsListAttachPreviewsConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MailsListViewConfig getMailsListViewConfig() {
        return (MailsListViewConfig) this.mailsListViewConfig.getValue(this, $$delegatedProperties[13]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MailsPin getMailsPin() {
        return super.getMailsPin();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MainPageConfig getMainPageConfig() {
        return super.getMainPageConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getMainPromoPeriod() {
        return super.getMainPromoPeriod();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MainScreenPoint getMainScreenPointConfig() {
        return super.getMainScreenPointConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getMarketingParamsForSendToAnalytics() {
        return super.getMarketingParamsForSendToAnalytics();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MaxRemoteConfig getMaxConfig() {
        return (MaxRemoteConfig) this.maxConfig.getValue(this, $$delegatedProperties[4]);
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getMaxNestingFoldersLevel() {
        return super.getMaxNestingFoldersLevel();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MediascopeConfiguration getMediascope() {
        return this.mediascope;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MenuFabConfig getMenuFabConfig() {
        return (Configuration.MenuFabConfig) this.menuFabConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MetaThreadMassOperationsConfig getMetaThreadMassOperationsConfig() {
        return (Configuration.MetaThreadMassOperationsConfig) this.metaThreadMassOperationsConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MetaThreadConfig.MetaThreadsPromoConfig getMetaThreadPromoConfig() {
        return super.getMetaThreadPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<Long> getMetaThreadsFolderId() {
        return super.getMetaThreadsFolderId();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MetaThreadsStatus getMetaThreadsStatus() {
        Object value = this.metaThreadsStatus.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (MetaThreadsStatus) value;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getMetathreadRefsFixEnabled() {
        return super.getMetathreadRefsFixEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MigrateToInternalStorage getMigrateToInternalStorageConfig() {
        return super.getMigrateToInternalStorageConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getMigrationFromImapEnable() {
        return super.getMigrationFromImapEnable();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getMinSupportedSBrowserVersion() {
        return super.getMinSupportedSBrowserVersion();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MlKit getMlKitConfig() {
        return super.getMlKitConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.MultiaccPromo getMultiaccPromoConfig() {
        return super.getMultiaccPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ MyTrackerConfig getMyTrackerConfig() {
        return super.getMyTrackerConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedDisableBatteryChangeReceiver() {
        return super.getNeedDisableBatteryChangeReceiver();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedLimitThread() {
        return super.getNeedLimitThread();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedLimitThreadForLowRamDevice() {
        return super.getNeedLimitThreadForLowRamDevice();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedRemoveSwitchToMpop() {
        return super.getNeedRemoveSwitchToMpop();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedSendAnalytics() {
        return super.getNeedSendAnalytics();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedSendPortalNetworkAnalytics() {
        return super.getNeedSendPortalNetworkAnalytics();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getNeedUseStubCookieSetter() {
        return super.getNeedUseStubCookieSetter();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public NetworkCheckerConfig getNetworkCheckerConfig() {
        return (NetworkCheckerConfig) this.networkCheckerConfig.getValue(this, $$delegatedProperties[30]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.NewActionsConfig getNewActionsConfig() {
        return (Configuration.NewActionsConfig) this.newActionsConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public NewAuthSdkConfig getNewAuthSdkConfig() {
        return (NewAuthSdkConfig) this.newAuthSdkConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public NewAuthorizationSdkConfig getNewAuthorizationSdkConfig() {
        return (NewAuthorizationSdkConfig) this.newAuthorizationSdkConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.NewEmailPopup getNewEmailPopupConfig() {
        return super.getNewEmailPopupConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Pattern getNewLoginSuppressedOauth() {
        Object value = this.newLoginSuppressedOauth.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Pattern) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.NewMailClipboardConfig getNewMailClipboardSuggestConfig() {
        return (Configuration.NewMailClipboardConfig) this.newMailClipboardSuggestConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.NewUserAgreement getNewUserAgreementConfig() {
        return super.getNewUserAgreementConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.NotesConfig getNotesConfig() {
        return (Configuration.NotesConfig) this.notesConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @Nullable
    public /* bridge */ String getNotesWebConfig() {
        return super.getNotesWebConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ShowRule getNotificationPromoRule() {
        return (ShowRule) this.notificationPromoRule.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.ManufacturerItem> getNotificationSettingsManufacturers() {
        Object value = this.notificationSettingsManufacturers.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.NotificationSmartReplies getNotificationSmartRepliesSettings() {
        return super.getNotificationSmartRepliesSettings();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.NpcPromo getNpcPromoConfig() {
        return super.getNpcPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.OAuthButtonConfig getOAuthButtonAppearance() {
        return super.getOAuthButtonAppearance();
    }

    @Override // ru.mail.config.Configuration
    @Nullable
    public String getOmicronConfigHash() {
        return this.omicronConfigHash;
    }

    @Override // ru.mail.config.Configuration
    @Nullable
    public String getOmicronConfigVersion() {
        return this.omicronConfigVersion;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.OmicronPromo> getOmicronPromoList() {
        return (List) this.omicronPromoList.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.OmicronPromoSettings getOmicronPromoSettings() {
        return (Configuration.OmicronPromoSettings) this.omicronPromoSettings.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getOpenCloudLinksByNavigator() {
        return super.getOpenCloudLinksByNavigator();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.OpenInWebViewConfig getOpenInWebViewConfig() {
        return (Configuration.OpenInWebViewConfig) this.openInWebViewConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ long getOutDatePeriod() {
        return super.getOutDatePeriod();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public OverquotaInfoSheetConfig getOverquotaInfoSheetConfig() {
        return (OverquotaInfoSheetConfig) this.overquotaInfoSheetConfig.getValue(this, $$delegatedProperties[24]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.PackageCheckerItem> getPackagesToCheckInstalledApp() {
        Object value = this.packagesToCheckInstalledApp.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getPackagesToCheckInstalledAppDetailed() {
        return super.getPackagesToCheckInstalledAppDetailed();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ParentalControl getParentalControlConfig() {
        return super.getParentalControlConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public PdfViewerConfig getPdfViewerConfig() {
        return (PdfViewerConfig) this.pdfViewerConfig.getValue(this, $$delegatedProperties[17]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PermissionsRebrandingConfig getPermRebrandingConfig() {
        return super.getPermRebrandingConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PermissionViewDesign getPermissionViewConfig() {
        return super.getPermissionViewConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.PermittedCookie> getPermittedCookies() {
        return (List) this.permittedCookies.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public PersonalizationConfig getPersonalizationConfig() {
        return (PersonalizationConfig) this.personalizationConfig.getValue(this, $$delegatedProperties[12]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PhishingConfig getPhishingConfig() {
        return (Configuration.PhishingConfig) this.phishingConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Collection<Plate> getPlates() {
        return (Collection) this.plates.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PopularContactSection getPopularContactSectionConfig() {
        return super.getPopularContactSectionConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.Portal getPortal() {
        return (Configuration.Portal) this.portal.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getPrefetchAttachmentsLimitSizeMb() {
        return super.getPrefetchAttachmentsLimitSizeMb();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PrefetcherDelayConfig getPrefetcherDelayConfig() {
        return (Configuration.PrefetcherDelayConfig) this.prefetcherDelayConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public PrettyEmailConfiguration getPrettyEmailConfiguration() {
        return (PrettyEmailConfiguration) this.prettyEmailConfiguration.getValue(this, $$delegatedProperties[26]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public PrettyEmailPromoConfig getPrettyEmailPromoConfig() {
        return (PrettyEmailPromoConfig) this.prettyEmailPromoConfig.getValue(this, $$delegatedProperties[27]);
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getPrivacyPolicyPermissionEnabled() {
        return super.getPrivacyPolicyPermissionEnabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.PromoFeatureConfig> getPromoFeaturesConfig() {
        return (List) this.promoFeaturesConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getPromoGoogleAccountMaxCountForShowing() {
        return super.getPromoGoogleAccountMaxCountForShowing();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PromoHighlightInfo getPromoHighlightInfo() {
        Object value = this.promoHighlightInfo.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.PromoHighlightInfo) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getProvidersInfo() {
        return super.getProvidersInfo();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PulseConfig getPulseConfig() {
        return (Configuration.PulseConfig) this.pulseConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PushAnalytics getPushAnalyticsConfig() {
        return super.getPushAnalyticsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PushCategoryMapper getPushCategoryMapper() {
        return (Configuration.PushCategoryMapper) this.pushCategoryMapper.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PushmeSdk getPushMeSdk() {
        return super.getPushMeSdk();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PushPromoConfig getPushPromoConfig() {
        return super.getPushPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PushPromoV2Config getPushPromoV2Config() {
        return super.getPushPromoV2Config();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.PushRateLimitAnalytics getPushRateLimitAnalytics() {
        return super.getPushRateLimitAnalytics();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<PushConfigurationType> getPushTypes() {
        return (List) this.pushTypes.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.QrAuth getQrAuthConfig() {
        return super.getQrAuthConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.QrLogin getQrLoginConfig() {
        return super.getQrLoginConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.QuickActionConfig getQuickActionConfig() {
        return (Configuration.QuickActionConfig) this.quickActionConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.RatBindPush getRatBindPushConfig() {
        return super.getRatBindPushConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.RatingPromoConfig getRatingPromoConfig() {
        return super.getRatingPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ReadVerificationForBusinessmenConfig getReadVerificationForBusinessmenConfig() {
        return (ReadVerificationForBusinessmenConfig) this.readVerificationForBusinessmenConfig.getValue(this, $$delegatedProperties[29]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ReadVerify getReadVerify() {
        return (Configuration.ReadVerify) this.readVerify.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public RedirectFromViewerToCloudConfig getRedirectFromViewerToCloudConfig() {
        return (RedirectFromViewerToCloudConfig) this.redirectFromViewerToCloudConfig.getValue(this, $$delegatedProperties[18]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public RedirectToTelegramMigrationConfig getRedirectToTelegramMigrationConfig() {
        return (RedirectToTelegramMigrationConfig) this.redirectToTelegramMigrationConfig.getValue(this, $$delegatedProperties[7]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.RegFlow getRegFlowConfig() {
        return super.getRegFlowConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.RegRebrandingConfig getRegRebrandingConfig() {
        return super.getRegRebrandingConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getRegexpForImapMigration() {
        return super.getRegexpForImapMigration();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.RegistrationExperiments getRegistrationExpsConfig() {
        return super.getRegistrationExpsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ReleaseFetcherConfig getReleaseFetcherConfig() {
        return (ReleaseFetcherConfig) this.releaseFetcherConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Configuration.RelocationAgreementConfig> getRelocationAgreementConfigs() {
        return (List) this.relocationAgreementConfigs.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ReminderConfiguration getReminderConfig() {
        return (Configuration.ReminderConfiguration) this.reminderConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ReminderPushInSettings getReminderPushSettingsInConfig() {
        return super.getReminderPushSettingsInConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getReportSuspiciousEmailSecurityAddress() {
        return super.getReportSuspiciousEmailSecurityAddress();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getRequestInitSyncFromMailPortal() {
        return super.getRequestInitSyncFromMailPortal();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ResponsibleTagsConfig getResponsibleTagsConfig() {
        return super.getResponsibleTagsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getRestoreAccessUrl() {
        return super.getRestoreAccessUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.RestoreAuthFlowConfig getRestoreAuthFlowConfig() {
        return (Configuration.RestoreAuthFlowConfig) this.restoreAuthFlowConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getRethrowForExternalAccount() {
        return super.getRethrowForExternalAccount();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.RetrofitConfig getRetrofitConfig() {
        return (Configuration.RetrofitConfig) this.retrofitConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getRetrofitUsages() {
        return super.getRetrofitUsages();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getRuStoreRateAppUrl() {
        return super.getRuStoreRateAppUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.RuStoreSdkConfig getRuStoreSdkConfig() {
        return (Configuration.RuStoreSdkConfig) this.ruStoreSdkConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SafeFolder getSafeFolderConfig() {
        return super.getSafeFolderConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.Schedule getSchedule() {
        Object value = this.schedule.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.Schedule) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.Search getSearchConfig() {
        return super.getSearchConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MassOperationToolBarConfiguration getSearchMassOpConfigWithUnread() {
        Object value = this.searchMassOpConfigWithUnread.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.MassOperationToolBarConfiguration) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.MassOperationToolBarConfiguration getSearchMassOpConfigWithoutUnread() {
        Object value = this.searchMassOpConfigWithoutUnread.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.MassOperationToolBarConfiguration) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SecureViewerConfig getSecureViewerConfig() {
        return super.getSecureViewerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SecurityCheckup getSecurityCheckupConfig() {
        return super.getSecurityCheckupConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Pattern getSecuritySettingsDomains() {
        Object value = this.securitySettingsDomains.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Pattern) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getSecuritySettingsUrl() {
        return super.getSecuritySettingsUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SendCancellation getSendCancellation() {
        return super.getSendCancellation();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public SendCancellationForBusinessmenConfig getSendCancellationForBusinessmenConfig() {
        return (SendCancellationForBusinessmenConfig) this.sendCancellationForBusinessmenConfig.getValue(this, $$delegatedProperties[28]);
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getSendFeedbackToHelp() {
        return super.getSendFeedbackToHelp();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<Pattern> getSendHttpRequestAnalyticEventsFilter() {
        Object value = this.sendHttpRequestAnalyticEventsFilter.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getSendLogsByPushEnabled() {
        return super.getSendLogsByPushEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getSendNonFatalsToOkTracer() {
        return super.getSendNonFatalsToOkTracer();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SenderKarmaSettings getSenderKarmaSettings() {
        return super.getSenderKarmaSettings();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ long getSendingEmailOutdatedPeriodInSeconds() {
        return super.getSendingEmailOutdatedPeriodInSeconds();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ long getServerQuotationThrashold() {
        return super.getServerQuotationThrashold();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public ServicesBannerConfig getServicesBannerConfig() {
        return (ServicesBannerConfig) this.servicesBannerConfig.getValue(this, $$delegatedProperties[8]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.ShareMailConfig getShareMailConfig() {
        return (Configuration.ShareMailConfig) this.shareMailConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public SharedCloudLoggerRemoteConfig getSharedCloudLoggerRemoteConfig() {
        return (SharedCloudLoggerRemoteConfig) this.sharedCloudLoggerRemoteConfig.getValue(this, $$delegatedProperties[25]);
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldRequestPhonePermissions() {
        return super.getShouldRequestPhonePermissions();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowCalendarThumbnailInHtml() {
        return super.getShouldShowCalendarThumbnailInHtml();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowCloudQuota() {
        return super.getShouldShowCloudQuota();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowDefinitelySpam() {
        return super.getShouldShowDefinitelySpam();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowQuotaWebPurchase() {
        return super.getShouldShowQuotaWebPurchase();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowRemoveDialogFromMailView() {
        return super.getShouldShowRemoveDialogFromMailView();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getShouldShowSpamOrUnsubscribeDialog() {
        return super.getShouldShowSpamOrUnsubscribeDialog();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<String> getShowQuotaRegions() {
        Object value = this.showQuotaRegions.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ShrinkConfig getShrinkConfig() {
        return super.getShrinkConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SignOutSection getSignOutSectionConfig() {
        return super.getSignOutSectionConfig();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getSkipWorkerConnectionCheck() {
        return super.getSkipWorkerConnectionCheck();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.SocialLoginConfig getSocialLoginConfig() {
        return (Configuration.SocialLoginConfig) this.socialLoginConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SpamFolderConfig getSpamFolderConfig() {
        return super.getSpamFolderConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.StatementStatusesPlateConfig getStatementStatusesPlateConfig() {
        return super.getStatementStatusesPlateConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<StickersGroup> getStickers() {
        Object value = this.stickers.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.Stories getStories() {
        return (Configuration.Stories) this.stories.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Collection<StringResEntry> getStrings() {
        Object value = this.strings.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Collection) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.SubscriptionConfig getSubscription() {
        return (Configuration.SubscriptionConfig) this.subscription.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ List<String> getSubscriptionList() {
        return super.getSubscriptionList();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SubscriptionsPromoConfig getSubscriptionsPromoSheetConfig() {
        return super.getSubscriptionsPromoSheetConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.Summarize getSummarize() {
        return (Configuration.Summarize) this.summarize.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getSurveysDisabled() {
        return super.getSurveysDisabled();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SwipeSort getSwipeSort() {
        return super.getSwipeSort();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SyncMessagesConnectionCheck getSyncMessagesConnectionCheck() {
        return super.getSyncMessagesConnectionCheck();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.SyncSocialAccountsListConfig getSyncSocialAccountsListConfig() {
        return super.getSyncSocialAccountsListConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.TechStatConfig getTechStat() {
        return (Configuration.TechStatConfig) this.techStat.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public TgMigrationConfig getTgMigrationConfig() {
        return (TgMigrationConfig) this.tgMigrationConfig.getValue(this, $$delegatedProperties[3]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ String getThemePickerUrl() {
        return super.getThemePickerUrl();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ThreadViewActionMode getThreadViewActionsMode() {
        return super.getThreadViewActionsMode();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getThresholdForDetectOmicronPromoShowLong() {
        return super.getThresholdForDetectOmicronPromoShowLong();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.TimeSpent getTimeSpentTrackerConfig() {
        return super.getTimeSpentTrackerConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.ToMyselfMetaThreadConfig getToMyselfMetaThreadConfig() {
        return super.getToMyselfMetaThreadConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public TokenExchangeConfig getTokenExchangeConfig() {
        return (TokenExchangeConfig) this.tokenExchangeConfig.getValue(this, $$delegatedProperties[19]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.TotalCleanConfig getTotalCleanConfig() {
        return (Configuration.TotalCleanConfig) this.totalCleanConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<MailItemTransactionCategory> getTransactionCategoriesForSearch() {
        return (List) this.transactionCategoriesForSearch.getValue();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ int getTranslateModel() {
        return super.getTranslateModel();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.TrustedMailConfig getTrustedMailConfig() {
        return (Configuration.TrustedMailConfig) this.trustedMailConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Map<String, Pattern> getTrustedUrls() {
        Object value = this.trustedUrls.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Map) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.AuthFlow.TwoStepAuth getTwoStepAuth() {
        return super.getTwoStepAuth();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ Map<String, String> getUniversalToPortalPaths() {
        return super.getUniversalToPortalPaths();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.Upmetric getUpmetricBanner() {
        return super.getUpmetricBanner();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseAnimationOnSplash() {
        return super.getUseAnimationOnSplash();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseAttachUploadPersistParams() {
        return super.getUseAttachUploadPersistParams();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseCachingAdvertisingInfoProvider() {
        return super.getUseCachingAdvertisingInfoProvider();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseMessageStyleNotification() {
        return super.getUseMessageStyleNotification();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseNewAuthInfo() {
        return super.getUseNewAuthInfo();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseNewEulaStrings() {
        return super.getUseNewEulaStrings();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean getUseSystemSplash() {
        return super.getUseSystemSplash();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.UserShort getUserShortTimeout() {
        return super.getUserShortTimeout();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.UserThemeData getUserThemeData() {
        return (Configuration.UserThemeData) this.userThemeData.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.LastSeen getUsersLastSeenConfig() {
        return super.getUsersLastSeenConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public List<String> getVirgilioDomains() {
        Object value = this.virgilioDomains.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (List) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.VkBindInSettings getVkBindInSettingsConfig() {
        return super.getVkBindInSettingsConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.Vk getVkConfig() {
        return super.getVkConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.VkidBindEmailPromo getVkIdBindEmailPromoConfig() {
        return super.getVkIdBindEmailPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.VkpnsHostSdk getVkpnsHostSdk() {
        return (Configuration.VkpnsHostSdk) this.vkpnsHostSdk.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.WalletConfig getWalletConfig() {
        return (Configuration.WalletConfig) this.walletConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.WalletOnMailsList getWalletOnMailsList() {
        return super.getWalletOnMailsList();
    }

    @Override // ru.mail.config.Configuration
    @Nullable
    public /* bridge */ String getWalletWebConfig() {
        return super.getWalletWebConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.TabWebAppsConfig getWebApps() {
        return (Configuration.TabWebAppsConfig) this.webApps.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.WebAuthNConfig getWebAuthNConfig() {
        return super.getWebAuthNConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.WebAuthNDisablerPromoConfig getWebAuthNDisablerPromoConfig() {
        return super.getWebAuthNDisablerPromoConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.WebViewConfig getWebViewConfig() {
        return (Configuration.WebViewConfig) this.webViewConfig.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.WelcomeLoginScreen getWelcomeLoginScreen() {
        Object value = this.welcomeLoginScreen.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Configuration.WelcomeLoginScreen) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.WriteToDevLogging getWriteToDevLogging() {
        return (Configuration.WriteToDevLogging) this.writeToDevLogging.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.XmailMigrationEntryPointExp getXmailMigrationEntryPointExp() {
        return (Configuration.XmailMigrationEntryPointExp) this.xmailMigrationEntryPointExp.getValue();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.XmailPromoConfig getXmailPlateConfig() {
        return super.getXmailPlateConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public /* bridge */ DTOConfiguration.Config.XmailRegDeeplinkConfig getXmailRegDeeplinkConfig() {
        return super.getXmailRegDeeplinkConfig();
    }

    public int hashCode() {
        String configV = getDtoConfiguration().getDtoConfig().getConfigV();
        int iHashCode = (configV != null ? configV.hashCode() : 0) * 31;
        String condS = getDtoConfiguration().getDtoConfig().getCondS();
        return ((iHashCode + (condS != null ? condS.hashCode() : 0)) * 31) + getStringConfig().hashCode();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAccountManagerAddingFallbackEnabled() {
        return super.isAccountManagerAddingFallbackEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAccountManagerEnabled() {
        return super.isAccountManagerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAccountManagerFallbackEnabled() {
        return super.isAccountManagerFallbackEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAdBannerReloadEnabled() {
        return super.isAdBannerReloadEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAddContactFooterEnabled() {
        return super.isAddContactFooterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAdsEnabled() {
        return super.isAdsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAllowedRegistrationWithoutPhone() {
        return super.isAllowedRegistrationWithoutPhone();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAnalyticSendingAckAndOpenEnabled() {
        return super.isAnalyticSendingAckAndOpenEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAnrSendingInTracerEnabled() {
        return super.isAnrSendingInTracerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAnyFolderMassOperationsEnabled() {
        return super.isAnyFolderMassOperationsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAppMetricsTrackerAnrDetectEnabled() {
        return super.isAppMetricsTrackerAnrDetectEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAppMetricsTrackerEnabled() {
        return super.isAppMetricsTrackerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAuthTypeChangePreferenceEnabled() {
        return super.isAuthTypeChangePreferenceEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAutoBlockQuoteEnabled() {
        return super.isAutoBlockQuoteEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isAutodetectToTranslateLetterEnabled() {
        return super.isAutodetectToTranslateLetterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isBackendQuotationParserDisabled() {
        return super.isBackendQuotationParserDisabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isBatchPrefetchMetaThreadsEnabled() {
        return super.isBatchPrefetchMetaThreadsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isBetaStateEnabled() {
        return super.isBetaStateEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isBottomInsetEnabledForBottomSheets() {
        return super.isBottomInsetEnabledForBottomSheets();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isByteEnabled() {
        return super.isByteEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCheckConnectionBeforeSend() {
        return super.isCheckConnectionBeforeSend();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCheckFacebookInstalled() {
        return super.isCheckFacebookInstalled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isChildRegistrationFixEnabled() {
        return super.isChildRegistrationFixEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCodeAuthEnabled() {
        return super.isCodeAuthEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCollectorsEnabled() {
        return super.isCollectorsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCollectorsHintEnabled() {
        return super.isCollectorsHintEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCommonMailAdapterPreferred() {
        return super.isCommonMailAdapterPreferred();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCopyInAccountDrawerEnabled() {
        return super.isCopyInAccountDrawerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isCrashlyticsEnabled() {
        return super.isCrashlyticsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDarkThemeActivityRecreateFixEnabled() {
        return super.isDarkThemeActivityRecreateFixEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDataAttributesExtractionEnabled() {
        return super.isDataAttributesExtractionEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDebugAnalyticsOfMigrationFromMpopEnabled() {
        return super.isDebugAnalyticsOfMigrationFromMpopEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDeeplinkSmartRepliesEnabled() {
        return super.isDeeplinkSmartRepliesEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDeleteMsgByPushEnabled() {
        return super.isDeleteMsgByPushEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDivKitImgAnalyticsEnabled() {
        return super.isDivKitImgAnalyticsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isDividersInMailsListEnabled() {
        return super.isDividersInMailsListEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEmailServicesLocaleIndependent() {
        return super.isEmailServicesLocaleIndependent();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEmailWhiteSpaceProhibitionEnabled() {
        return super.isEmailWhiteSpaceProhibitionEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEmojisNeedToRemove() {
        return super.isEmojisNeedToRemove();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEmojisSettingsEnabled() {
        return super.isEmojisSettingsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEnableForceAuthByVKID() {
        return super.isEnableForceAuthByVKID();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEnableReportLastExitReasonId() {
        return super.isEnableReportLastExitReasonId();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isEventReactionSurveyEnabled() {
        return super.isEventReactionSurveyEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFeedbackButtonInAboutEnabled() {
        return super.isFeedbackButtonInAboutEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFeedbackInAccountDrawerEnabled() {
        return super.isFeedbackInAccountDrawerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFirebasePerformanceAvailable() {
        return super.isFirebasePerformanceAvailable();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFixVkAccountBreakRefreshTokenEnabled() {
        return super.isFixVkAccountBreakRefreshTokenEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFoldingDevicesSupportEnabled() {
        return super.isFoldingDevicesSupportEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isForcedForegroundServiceStart() {
        return super.isForcedForegroundServiceStart();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFormatterHyphenEnabled() {
        return super.isFormatterHyphenEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isFormatterNbspDisabled() {
        return super.isFormatterNbspDisabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isGoToActionButtonInMailsListEnabled() {
        return super.isGoToActionButtonInMailsListEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isHelpInAccountDrawerEnabled() {
        return super.isHelpInAccountDrawerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isHelpInAuthScreenEnabled() {
        return super.isHelpInAuthScreenEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isHideKeyboardOnLoginScreen() {
        return super.isHideKeyboardOnLoginScreen();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isHmsMessageServicesEnabled() {
        return super.isHmsMessageServicesEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isImapAuthAccessWorkaroundEnabled() {
        return super.isImapAuthAccessWorkaroundEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isImapFixAuthWorkaroundEnabled() {
        return super.isImapFixAuthWorkaroundEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isImapOnly() {
        return super.isImapOnly();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isImapPushSubscriptionEnabled() {
        return super.isImapPushSubscriptionEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isImapSkipMailruOauthSteps() {
        return super.isImapSkipMailruOauthSteps();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isInsetsHandlingEnabled() {
        return super.isInsetsHandlingEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isInternetRuRegistrationEnabled() {
        return super.isInternetRuRegistrationEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isInternetRuSecurityEnabled() {
        return super.isInternetRuSecurityEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isLibverifyEnabled() {
        return super.isLibverifyEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isLibverifyPushesPassEnabled() {
        return super.isLibverifyPushesPassEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isLightModeEnabled() {
        return super.isLightModeEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isLogsInCrashReportEnabled() {
        return super.isLogsInCrashReportEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailFromOtherBoxesEnabled() {
        return super.isMailFromOtherBoxesEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailListRemoveDuplicatesEnabled() {
        return super.isMailListRemoveDuplicatesEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesAsyncLoading() {
        return super.isMailViewInlineImagesAsyncLoading();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesDirectDownload() {
        return super.isMailViewInlineImagesDirectDownload();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesLazyLoading() {
        return super.isMailViewInlineImagesLazyLoading();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesSequentialLoading() {
        return super.isMailViewInlineImagesSequentialLoading();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesUseCache() {
        return super.isMailViewInlineImagesUseCache();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailViewInlineImagesUsePrefetch() {
        return super.isMailViewInlineImagesUsePrefetch();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailWebViewImagesDirectDownload() {
        return super.isMailWebViewImagesDirectDownload();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMailWebviewThemeFixEnabled() {
        return super.isMailWebviewThemeFixEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMapPlateEnabled() {
        return super.isMapPlateEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMetaThreadBoldDomainsEnabled() {
        return super.isMetaThreadBoldDomainsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMetaThreadDomainsSubjectEnabled() {
        return super.isMetaThreadDomainsSubjectEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMetaThreadsActionsUndoEnabled() {
        return super.isMetaThreadsActionsUndoEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMetaThreadsNewCounterEnabled() {
        return super.isMetaThreadsNewCounterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMigrationFromMpopToOauthEnabled() {
        return super.isMigrationFromMpopToOauthEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMiniMailEnabled() {
        return super.isMiniMailEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMovePushSupported() {
        return super.isMovePushSupported();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMsgBodyAdBlockEnabled() {
        return super.isMsgBodyAdBlockEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isMultiAccountEnabled() {
        return super.isMultiAccountEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNewExternalAuthEnabled() {
        return super.isNewExternalAuthEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNewExternalAuthForceCollectorsEnabled() {
        return super.isNewExternalAuthForceCollectorsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNewMetaThreadsSettingsEnabled() {
        return super.isNewMetaThreadsSettingsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNewNetworkRequestEnabled() {
        return super.isNewNetworkRequestEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNewSettingsHelpMenuTextEnabled() {
        return super.isNewSettingsHelpMenuTextEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNotificationDetailedLogEnabled() {
        return super.isNotificationDetailedLogEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNotificationFilterEnabled() {
        return super.isNotificationFilterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNotificationPromoEnabled() {
        return super.isNotificationPromoEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isNotificationRouterEnabled() {
        return super.isNotificationRouterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isOAuthEnabled() {
        return super.isOAuthEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isOauthForcedEnabled() {
        return super.isOauthForcedEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isOrderStatusConfigEnabled() {
        return super.isOrderStatusConfigEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isPersonalDataProcessingDenialVisible() {
        return super.isPersonalDataProcessingDenialVisible();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isPriceThresholdEnabled() {
        return super.isPriceThresholdEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isPromoGoogleAccountEnabled() {
        return super.isPromoGoogleAccountEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isPushActionIconAllowed() {
        return super.isPushActionIconAllowed();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isPushMarkReadSingleAllowed() {
        return super.isPushMarkReadSingleAllowed();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isQrCodeFromImageEnabled() {
        return super.isQrCodeFromImageEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isQuickactionMoveToBinSplited() {
        return super.isQuickactionMoveToBinSplited();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRealSelectAllEnabled() {
        return super.isRealSelectAllEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRealSelectAllEnabledInTrash() {
        return super.isRealSelectAllEnabledInTrash();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRecaptchaEnabled() {
        return super.isRecaptchaEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRedirectLetterEnabled() {
        return super.isRedirectLetterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isReferenceTablePreferred() {
        return super.isReferenceTablePreferred();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRefreshNotificationsOnStartEnabled() {
        return super.isRefreshNotificationsOnStartEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRefreshTokenUpdateAllowed() {
        return super.isRefreshTokenUpdateAllowed();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRegFormAnalyticsEnabled() {
        return super.isRegFormAnalyticsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRegServerValidationPasswordEnabled() {
        return super.isRegServerValidationPasswordEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRelevantFlagEnabled() {
        return super.isRelevantFlagEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isReminderPushEnabled() {
        return super.isReminderPushEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isReminderPushOnlyForInactiveUsers() {
        return super.isReminderPushOnlyForInactiveUsers();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRemoveAfterSpamEnabled() {
        return super.isRemoveAfterSpamEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRemoveAfterSpamGrantedByDefault() {
        return super.isRemoveAfterSpamGrantedByDefault();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRemoveAfterSpamNewslettersOnly() {
        return super.isRemoveAfterSpamNewslettersOnly();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isReportBugInAccountDrawerEnabled() {
        return super.isReportBugInAccountDrawerEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isReportSuspiciousEmailEnabled() {
        return super.isReportSuspiciousEmailEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRequestDurationAnalyticsEnabled() {
        return super.isRequestDurationAnalyticsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRequestPinAppWidgetSupported() {
        return super.isRequestPinAppWidgetSupported();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isResourcesOverridden() {
        return super.isResourcesOverridden();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isRestorePasswordWebViewEnabled() {
        return super.isRestorePasswordWebViewEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSSLCertificatesInstallationEnabled() {
        return super.isSSLCertificatesInstallationEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSafePendingIntentEnabled() {
        return super.isSafePendingIntentEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSafetyFormatterEnabled() {
        return super.isSafetyFormatterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSafetyVerificationEnabled() {
        return super.isSafetyVerificationEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSanitizeCookieEnabled() {
        return super.isSanitizeCookieEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSanitizeHtmlContentEnabled() {
        return super.isSanitizeHtmlContentEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSanitizedScriptForAllAccountEnabled() {
        return super.isSanitizedScriptForAllAccountEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSaveAnalyticOpenUrlInLocalDataBaseEnabled() {
        return super.isSaveAnalyticOpenUrlInLocalDataBaseEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isScheduleWorkToBackgroundThread() {
        return super.isScheduleWorkToBackgroundThread();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSearchByLabelsEnabled() {
        return super.isSearchByLabelsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSearchMassOperationsEnabled() {
        return super.isSearchMassOperationsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSelectFromOtherAppButtonEnabled() {
        return super.isSelectFromOtherAppButtonEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isShimmerInsteadOfLoaderMailview() {
        return super.isShimmerInsteadOfLoaderMailview();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isShowEveryoneXmailEntryPoint() {
        return super.isShowEveryoneXmailEntryPoint();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isShowSelectorAfterPermissions() {
        return super.isShowSelectorAfterPermissions();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isShowSelectorOnAddingNewAccount() {
        return super.isShowSelectorOnAddingNewAccount();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isShowWalletAfterClickOnReceiptsMetathread() {
        return super.isShowWalletAfterClickOnReceiptsMetathread();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSmartLockEnabled() {
        return super.isSmartLockEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSmartReplyEnabled() {
        return super.isSmartReplyEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSmartSortInMailSettings() {
        return super.isSmartSortInMailSettings();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isSubmitFormEnabled() {
        return super.isSubmitFormEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isTranslateLetterEnabled() {
        return super.isTranslateLetterEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isTwoStepCodeAuthEnabled() {
        return super.isTwoStepCodeAuthEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUnescapeMailtoEnabled() {
        return super.isUnescapeMailtoEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUnifiedAttachDownloadEnabled() {
        return super.isUnifiedAttachDownloadEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUnsubscribeEnabled() {
        return super.isUnsubscribeEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUriDecodeInAttachmentsEnabled() {
        return super.isUriDecodeInAttachmentsEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseExpeditedSettingsForForceSync() {
        return super.isUseExpeditedSettingsForForceSync();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseFakeHelpLink() {
        return super.isUseFakeHelpLink();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseNativeXmailReg() {
        return super.isUseNativeXmailReg();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseNativeXmailWithVkidReg() {
        return super.isUseNativeXmailWithVkidReg();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseSupervisorJobInWorkersEnabled() {
        return super.isUseSupervisorJobInWorkersEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseSystemUserAgentHelpersUpdate() {
        return super.isUseSystemUserAgentHelpersUpdate();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUseUpdateSnackbarsMethod() {
        return super.isUseUpdateSnackbarsMethod();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUserDataRefreshEnabled() {
        return super.isUserDataRefreshEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUserRegisteredByVKIDPromoEnabled() {
        return super.isUserRegisteredByVKIDPromoEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isUsingJsCalculatedHeight() {
        return super.isUsingJsCalculatedHeight();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isWebHistoryNavigationEnabled() {
        return super.isWebHistoryNavigationEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isWebHistoryTrustedUrlsNavigationEnabled() {
        return super.isWebHistoryTrustedUrlsNavigationEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isWebViewMixedSourcesEnabled() {
        return super.isWebViewMixedSourcesEnabled();
    }

    @Override // ru.mail.config.Configuration
    public /* bridge */ boolean isWebviewWorkaroundEnabled() {
        return super.isWebviewWorkaroundEnabled();
    }

    @NotNull
    public String toString() {
        return getStringConfig();
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public AccountManagerSyncConfigDTO getAccountManagerSyncConfig() {
        return (AccountManagerSyncConfigDTO) this.accountManagerSyncConfig.getValue(this, $$delegatedProperties[20]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public AdRemoteConfig getAdConfig() {
        return (AdRemoteConfig) this.adConfig.getValue(this, $$delegatedProperties[1]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CheckBackInReceiptsMetaThreadDTO getCheckBackInReceiptsMetaThread() {
        return (CheckBackInReceiptsMetaThreadDTO) this.checkBackInReceiptsMetaThread.getValue(this, $$delegatedProperties[16]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public CryptoAwareFileRemoteConfig getCryptoAwareFileRemoteConfig() {
        return (CryptoAwareFileRemoteConfig) this.cryptoAwareFileRemoteConfig.getValue(this, $$delegatedProperties[21]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Set<Configuration.SoundKey> getEnabledSounds() {
        Object value = this.enabledSounds.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (Set) value;
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public FamilySubscriptionRemoteConfigImpl getFamilySubscriptionConfig() {
        return (FamilySubscriptionRemoteConfigImpl) this.familySubscriptionConfig.getValue(this, $$delegatedProperties[2]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public LetterAiWriterRemoteConfig getLetterAiWriter() {
        return (LetterAiWriterRemoteConfig) this.letterAiWriter.getValue(this, $$delegatedProperties[9]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MailMessageContainerConfigDTO getMailMessageContainerConfig() {
        return (MailMessageContainerConfigDTO) this.mailMessageContainerConfig.getValue(this, $$delegatedProperties[22]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public MailViewOzonFeedDTO getMailViewOzonFeedConfig() {
        return (MailViewOzonFeedDTO) this.mailViewOzonFeedConfig.getValue(this, $$delegatedProperties[14]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public OmicronConfigImpl getOmicronConfig() {
        return (OmicronConfigImpl) this.omicronConfig.getValue(this, $$delegatedProperties[10]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public Configuration.PromotionNotificationWithImageConfigImpl getPromotionNotificationWithImageConfig() {
        return (Configuration.PromotionNotificationWithImageConfigImpl) this.promotionNotificationWithImageConfig.getValue(this, $$delegatedProperties[23]);
    }

    @Override // ru.mail.config.Configuration
    @NotNull
    public WalletAppConfigDTO getWalletAppConfig() {
        return (WalletAppConfigDTO) this.walletAppConfig.getValue(this, $$delegatedProperties[15]);
    }

    @Deprecated(message = "New sdk is newAuthorizationSdkConfig")
    public static /* synthetic */ void getNewAuthSdkConfig$annotations() {
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @TestOnly
    public MailAppConfiguration(@NotNull DTORawConfiguration dtoConfiguration, @NotNull ConfigurationData configurationData, @NotNull ConfigurationSettingsDelegate configurationSettingsDelegate, @NotNull StorageProvider storageProvider, @NotNull AnalyticsSender analyticsSender) {
        this(dtoConfiguration, configurationData, configurationSettingsDelegate, storageProvider, analyticsSender, new Function0() { // from class: ru.mail.config.k
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration._init_$lambda$0();
            }
        }, new Function0() { // from class: ru.mail.config.m0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAppConfiguration._init_$lambda$1();
            }
        });
        Intrinsics.checkNotNullParameter(dtoConfiguration, "dtoConfiguration");
        Intrinsics.checkNotNullParameter(configurationData, "configurationData");
        Intrinsics.checkNotNullParameter(configurationSettingsDelegate, "configurationSettingsDelegate");
        Intrinsics.checkNotNullParameter(storageProvider, "storageProvider");
        Intrinsics.checkNotNullParameter(analyticsSender, "analyticsSender");
    }
}
