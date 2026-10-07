package ru.mail.config;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.calleridentification.CallerIdentificationConfig;
import ru.mail.config.dto.DTORawConfiguration;
import ru.mail.flexsettings.field.Field;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mytracker.MyTrackerConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lru/mail/config/ConfigurationWithRawData;", "Lru/mail/config/Configuration;", "dtoConfiguration", "Lru/mail/config/dto/DTORawConfiguration;", "getDtoConfiguration", "()Lru/mail/config/dto/DTORawConfiguration;", "configurationDataObject", "Lorg/json/JSONObject;", "getConfigurationDataObject", "()Lorg/json/JSONObject;", "configurationDataFiled", "Lru/mail/flexsettings/field/Field;", "getConfigurationDataFiled", "()Lru/mail/flexsettings/field/Field;", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ConfigurationWithRawData extends Configuration {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes9.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AccountManagerDelegate getAccountManagerDelegate(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAccountManagerDelegate();
        }

        @Deprecated
        @NotNull
        public static List<String> getAccountManagerTypesForSignInSuggests(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAccountManagerTypesForSignInSuggests();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AccountsPopup getAccountsPopupConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAccountsPopupConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AdditionalAppSizeTracking getAdditionalAppSizeTrackingConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAdditionalAppSizeTrackingConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AddressBook getAddressBookConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAddressBookConfig();
        }

        @Deprecated
        @NotNull
        public static String getAgreementUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAgreementUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AllowedAdsManagement getAllowedAdsManagement(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAllowedAdsManagement();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AmpConfig getAmpConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAmpConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AndroidOsSystemFeatureConfig getAndroidOsSystemFeatureConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAndroidOsSystemFeatureConfig();
        }

        @Deprecated
        public static long getAppMetricsTrackerAnrTimeout(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAppMetricsTrackerAnrTimeout();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AppSyncConfig getAppSettingsSyncIntervals(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAppSettingsSyncIntervals();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AutoUploadOnMailsList getAutoUploadOnMailsList(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAutoUploadOnMailsList();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AutoUploadPromoConfig getAutoUploadPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getAutoUploadPromoConfig();
        }

        @Deprecated
        @NotNull
        public static String getBehaviorName(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getBehaviorName();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.BigBundleSaveConfig getBigBundleSaveConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getBigBundleSaveConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ByteTooltip getByteTooltipConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getByteTooltipConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.CalendarNotificationConfig getCalendarNotificationConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCalendarNotificationConfig();
        }

        @Deprecated
        @Nullable
        public static String getCalendarWebConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCalendarWebConfig();
        }

        @Deprecated
        @NotNull
        public static CallerIdentificationConfig getCallerIdentificationConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCallerIdentificationConfig();
        }

        @Deprecated
        public static boolean getCameraAutoUploadRegexPromoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCameraAutoUploadRegexPromoEnabled();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.CategoryFeedbackConfig getCategoryFeedbackConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCategoryFeedbackConfig();
        }

        @Deprecated
        @NotNull
        public static String getCleanMasterUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCleanMasterUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ClickerConfig getClickerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getClickerConfig();
        }

        @Deprecated
        @NotNull
        public static String getCodeAuthUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCodeAuthUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ColoredTagsConfig getColoredTagsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getColoredTagsConfig();
        }

        @Deprecated
        public static int getConnectionSamplingPeriodSeconds(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getConnectionSamplingPeriodSeconds();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ContactsExport getContactsExportConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getContactsExportConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ContactsOrm getContactsOrm(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getContactsOrm();
        }

        @Deprecated
        public static int getContactsPageSize(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getContactsPageSize();
        }

        @Deprecated
        public static boolean getContactsRequestAgreementUsage(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getContactsRequestAgreementUsage();
        }

        @Deprecated
        public static int getCopyrightYear(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCopyrightYear();
        }

        @Deprecated
        @NotNull
        public static String getCorpConfidentUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCorpConfidentUrl();
        }

        @Deprecated
        @NotNull
        public static String getCovidUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCovidUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.CreateEventFromEmailConfig getCreateEventFromEmailConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCreateEventFromEmailConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.CreateNoteFromMail getCreateNoteFromMail(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getCreateNoteFromMail();
        }

        @Deprecated
        @NotNull
        public static String getDeleteAccountUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDeleteAccountUrl();
        }

        @Deprecated
        @NotNull
        public static String getDkimMoreUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDkimMoreUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.DkimWarning getDkimWarning(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDkimWarning();
        }

        @Deprecated
        @NotNull
        public static List<String> getDomainsForSignInSuggests(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDomainsForSignInSuggests();
        }

        @Deprecated
        @NotNull
        public static List<String> getDomainsWithoutImapCheckHotfix(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDomainsWithoutImapCheckHotfix();
        }

        @Deprecated
        public static int getDrawerScrollAngle(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getDrawerScrollAngle();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.EditModeTutorial getEditModeTutorial(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getEditModeTutorial();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.EmailToMyselfSuggestions getEmailToMySelfSuggestionsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getEmailToMySelfSuggestionsConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.EmptyState getEmptyStateConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getEmptyStateConfig();
        }

        @Deprecated
        public static boolean getEnabledRecreateOrmContentProvider(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getEnabledRecreateOrmContentProvider();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.EsiaConfig getEsiaConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getEsiaConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.FeedbackInAccountDrawerConfiguration getFeedbackInAccountDrawerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getFeedbackInAccountDrawerConfig();
        }

        @Deprecated
        public static boolean getFixEndlessAdapter(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getFixEndlessAdapter();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.FoldersDrawerConfig getFoldersDrawerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getFoldersDrawerConfig();
        }

        @Deprecated
        public static int getGalleryMaxSelectionCount(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getGalleryMaxSelectionCount();
        }

        @Deprecated
        public static int getGlideCacheSizeKb(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getGlideCacheSizeKb();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.GoToSpamDialogConfig getGoToSpamDialogConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getGoToSpamDialogConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.GptBirthdayPromo getGptBirthdayPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getGptBirthdayPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.GptProject getGptProjectConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getGptProjectConfig();
        }

        @Deprecated
        @NotNull
        public static String getHelpLink(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getHelpLink();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.HideLoginServices getHideLoginServicesConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getHideLoginServicesConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.InitExecutionTimeTracker getInitExecutionTimeTrackerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getInitExecutionTimeTrackerConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.IntegrationPlateConfig getIntegrationPlateConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getIntegrationPlateConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.KasperskyConfig getKasperskyConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKasperskyConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.KasperskyLogoInSettings getKasperskyLogoInSettings(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKasperskyLogoInSettings();
        }

        @Deprecated
        public static boolean getKotlettAccountsPromoSectionEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKotlettAccountsPromoSectionEnabled();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.Kotlett getKotlettConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKotlettConfig();
        }

        @Deprecated
        public static boolean getKotlettFarmEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKotlettFarmEnabled();
        }

        @Deprecated
        public static boolean getKotlettOnboardingPromoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKotlettOnboardingPromoEnabled();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.KotlettPlatesOnReadMailScreen getKotlettPlatesOnReadMailConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getKotlettPlatesOnReadMailConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.LeelooDesign getLeelooDesign(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLeelooDesign();
        }

        @Deprecated
        public static int getLocalPushesFetchPeriodSeconds(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLocalPushesFetchPeriodSeconds();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.LocationPermissionDialog getLocationPermissionDialog(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLocationPermissionDialog();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.LoginButtonGmail getLoginButtonGmailConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLoginButtonGmailConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.LoginServicesGmailExp getLoginServicesGmailConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLoginServicesGmailConfig();
        }

        @Deprecated
        @NotNull
        public static List<String> getLoginSuggestedDomains(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getLoginSuggestedDomains();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MailActivityLauncher getMailActivityLauncher(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailActivityLauncher();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MailListLoad getMailListLoadConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailListLoadConfig();
        }

        @Deprecated
        public static boolean getMailPostponeLoaderEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailPostponeLoaderEnabled();
        }

        @Deprecated
        public static boolean getMailPostponeViewerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailPostponeViewerEnabled();
        }

        @Deprecated
        @NotNull
        public static List<String> getMailViewInlineImagesDomains(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailViewInlineImagesDomains();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MailViewInlineImagesResizeConfig getMailViewInlineImagesResizeConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailViewInlineImagesResizeConfig();
        }

        @Deprecated
        public static int getMailViewInlineImagesToLoadImmediate(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailViewInlineImagesToLoadImmediate();
        }

        @Deprecated
        public static boolean getMailViewPostponeReplyShow(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailViewPostponeReplyShow();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MailsPin getMailsPin(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMailsPin();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MainPageConfig getMainPageConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMainPageConfig();
        }

        @Deprecated
        public static int getMainPromoPeriod(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMainPromoPeriod();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MainScreenPoint getMainScreenPointConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMainScreenPointConfig();
        }

        @Deprecated
        @NotNull
        public static List<String> getMarketingParamsForSendToAnalytics(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMarketingParamsForSendToAnalytics();
        }

        @Deprecated
        public static int getMaxNestingFoldersLevel(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMaxNestingFoldersLevel();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MetaThreadConfig.MetaThreadsPromoConfig getMetaThreadPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMetaThreadPromoConfig();
        }

        @Deprecated
        @NotNull
        public static List<Long> getMetaThreadsFolderId(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMetaThreadsFolderId();
        }

        @Deprecated
        public static boolean getMetathreadRefsFixEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMetathreadRefsFixEnabled();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MigrateToInternalStorage getMigrateToInternalStorageConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMigrateToInternalStorageConfig();
        }

        @Deprecated
        public static boolean getMigrationFromImapEnable(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMigrationFromImapEnable();
        }

        @Deprecated
        @NotNull
        public static String getMinSupportedSBrowserVersion(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMinSupportedSBrowserVersion();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MlKit getMlKitConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMlKitConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.MultiaccPromo getMultiaccPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMultiaccPromoConfig();
        }

        @Deprecated
        @NotNull
        public static MyTrackerConfig getMyTrackerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getMyTrackerConfig();
        }

        @Deprecated
        public static boolean getNeedDisableBatteryChangeReceiver(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedDisableBatteryChangeReceiver();
        }

        @Deprecated
        public static boolean getNeedLimitThread(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedLimitThread();
        }

        @Deprecated
        public static boolean getNeedLimitThreadForLowRamDevice(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedLimitThreadForLowRamDevice();
        }

        @Deprecated
        public static boolean getNeedRemoveSwitchToMpop(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedRemoveSwitchToMpop();
        }

        @Deprecated
        public static boolean getNeedSendAnalytics(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedSendAnalytics();
        }

        @Deprecated
        public static boolean getNeedSendPortalNetworkAnalytics(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedSendPortalNetworkAnalytics();
        }

        @Deprecated
        public static boolean getNeedUseStubCookieSetter(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNeedUseStubCookieSetter();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.NewEmailPopup getNewEmailPopupConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNewEmailPopupConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.NewUserAgreement getNewUserAgreementConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNewUserAgreementConfig();
        }

        @Deprecated
        @Nullable
        public static String getNotesWebConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNotesWebConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.NotificationSmartReplies getNotificationSmartRepliesSettings(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNotificationSmartRepliesSettings();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.NpcPromo getNpcPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getNpcPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.OAuthButtonConfig getOAuthButtonAppearance(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getOAuthButtonAppearance();
        }

        @Deprecated
        public static boolean getOpenCloudLinksByNavigator(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getOpenCloudLinksByNavigator();
        }

        @Deprecated
        public static long getOutDatePeriod(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getOutDatePeriod();
        }

        @Deprecated
        @NotNull
        public static List<String> getPackagesToCheckInstalledAppDetailed(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPackagesToCheckInstalledAppDetailed();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ParentalControl getParentalControlConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getParentalControlConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PermissionsRebrandingConfig getPermRebrandingConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPermRebrandingConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PermissionViewDesign getPermissionViewConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPermissionViewConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PopularContactSection getPopularContactSectionConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPopularContactSectionConfig();
        }

        @Deprecated
        public static int getPrefetchAttachmentsLimitSizeMb(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPrefetchAttachmentsLimitSizeMb();
        }

        @Deprecated
        public static boolean getPrivacyPolicyPermissionEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPrivacyPolicyPermissionEnabled();
        }

        @Deprecated
        public static int getPromoGoogleAccountMaxCountForShowing(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPromoGoogleAccountMaxCountForShowing();
        }

        @Deprecated
        @NotNull
        public static String getProvidersInfo(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getProvidersInfo();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PushAnalytics getPushAnalyticsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPushAnalyticsConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PushmeSdk getPushMeSdk(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPushMeSdk();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PushPromoConfig getPushPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPushPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PushPromoV2Config getPushPromoV2Config(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPushPromoV2Config();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.PushRateLimitAnalytics getPushRateLimitAnalytics(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getPushRateLimitAnalytics();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.QrAuth getQrAuthConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getQrAuthConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.QrLogin getQrLoginConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getQrLoginConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.RatBindPush getRatBindPushConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRatBindPushConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.RatingPromoConfig getRatingPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRatingPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.RegFlow getRegFlowConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRegFlowConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.RegRebrandingConfig getRegRebrandingConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRegRebrandingConfig();
        }

        @Deprecated
        @NotNull
        public static String getRegexpForImapMigration(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRegexpForImapMigration();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.RegistrationExperiments getRegistrationExpsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRegistrationExpsConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ReminderPushInSettings getReminderPushSettingsInConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getReminderPushSettingsInConfig();
        }

        @Deprecated
        @NotNull
        public static String getReportSuspiciousEmailSecurityAddress(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getReportSuspiciousEmailSecurityAddress();
        }

        @Deprecated
        public static boolean getRequestInitSyncFromMailPortal(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRequestInitSyncFromMailPortal();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ResponsibleTagsConfig getResponsibleTagsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getResponsibleTagsConfig();
        }

        @Deprecated
        @NotNull
        public static String getRestoreAccessUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRestoreAccessUrl();
        }

        @Deprecated
        public static boolean getRethrowForExternalAccount(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRethrowForExternalAccount();
        }

        @Deprecated
        @NotNull
        public static List<String> getRetrofitUsages(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRetrofitUsages();
        }

        @Deprecated
        @NotNull
        public static String getRuStoreRateAppUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getRuStoreRateAppUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SafeFolder getSafeFolderConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSafeFolderConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.Search getSearchConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSearchConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SecureViewerConfig getSecureViewerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSecureViewerConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SecurityCheckup getSecurityCheckupConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSecurityCheckupConfig();
        }

        @Deprecated
        @NotNull
        public static String getSecuritySettingsUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSecuritySettingsUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SendCancellation getSendCancellation(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSendCancellation();
        }

        @Deprecated
        public static boolean getSendFeedbackToHelp(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSendFeedbackToHelp();
        }

        @Deprecated
        public static boolean getSendLogsByPushEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSendLogsByPushEnabled();
        }

        @Deprecated
        public static boolean getSendNonFatalsToOkTracer(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSendNonFatalsToOkTracer();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SenderKarmaSettings getSenderKarmaSettings(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSenderKarmaSettings();
        }

        @Deprecated
        public static long getSendingEmailOutdatedPeriodInSeconds(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSendingEmailOutdatedPeriodInSeconds();
        }

        @Deprecated
        public static long getServerQuotationThrashold(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getServerQuotationThrashold();
        }

        @Deprecated
        public static boolean getShouldRequestPhonePermissions(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldRequestPhonePermissions();
        }

        @Deprecated
        public static boolean getShouldShowCalendarThumbnailInHtml(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowCalendarThumbnailInHtml();
        }

        @Deprecated
        public static boolean getShouldShowCloudQuota(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowCloudQuota();
        }

        @Deprecated
        public static boolean getShouldShowDefinitelySpam(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowDefinitelySpam();
        }

        @Deprecated
        public static boolean getShouldShowQuotaWebPurchase(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowQuotaWebPurchase();
        }

        @Deprecated
        public static boolean getShouldShowRemoveDialogFromMailView(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowRemoveDialogFromMailView();
        }

        @Deprecated
        public static boolean getShouldShowSpamOrUnsubscribeDialog(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShouldShowSpamOrUnsubscribeDialog();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ShrinkConfig getShrinkConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getShrinkConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SignOutSection getSignOutSectionConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSignOutSectionConfig();
        }

        @Deprecated
        public static boolean getSkipWorkerConnectionCheck(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSkipWorkerConnectionCheck();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SpamFolderConfig getSpamFolderConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSpamFolderConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.StatementStatusesPlateConfig getStatementStatusesPlateConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getStatementStatusesPlateConfig();
        }

        @Deprecated
        @NotNull
        public static List<String> getSubscriptionList(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSubscriptionList();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SubscriptionsPromoConfig getSubscriptionsPromoSheetConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSubscriptionsPromoSheetConfig();
        }

        @Deprecated
        public static boolean getSurveysDisabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSurveysDisabled();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SwipeSort getSwipeSort(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSwipeSort();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SyncMessagesConnectionCheck getSyncMessagesConnectionCheck(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSyncMessagesConnectionCheck();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.SyncSocialAccountsListConfig getSyncSocialAccountsListConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getSyncSocialAccountsListConfig();
        }

        @Deprecated
        @NotNull
        public static String getThemePickerUrl(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getThemePickerUrl();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ThreadViewActionMode getThreadViewActionsMode(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getThreadViewActionsMode();
        }

        @Deprecated
        public static int getThresholdForDetectOmicronPromoShowLong(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getThresholdForDetectOmicronPromoShowLong();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.TimeSpent getTimeSpentTrackerConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getTimeSpentTrackerConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.ToMyselfMetaThreadConfig getToMyselfMetaThreadConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getToMyselfMetaThreadConfig();
        }

        @Deprecated
        public static int getTranslateModel(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getTranslateModel();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.AuthFlow.TwoStepAuth getTwoStepAuth(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getTwoStepAuth();
        }

        @Deprecated
        @NotNull
        public static Map<String, String> getUniversalToPortalPaths(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUniversalToPortalPaths();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.Upmetric getUpmetricBanner(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUpmetricBanner();
        }

        @Deprecated
        public static boolean getUseAnimationOnSplash(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseAnimationOnSplash();
        }

        @Deprecated
        public static boolean getUseAttachUploadPersistParams(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseAttachUploadPersistParams();
        }

        @Deprecated
        public static boolean getUseCachingAdvertisingInfoProvider(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseCachingAdvertisingInfoProvider();
        }

        @Deprecated
        public static boolean getUseMessageStyleNotification(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseMessageStyleNotification();
        }

        @Deprecated
        public static boolean getUseNewAuthInfo(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseNewAuthInfo();
        }

        @Deprecated
        public static boolean getUseNewEulaStrings(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseNewEulaStrings();
        }

        @Deprecated
        public static boolean getUseSystemSplash(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUseSystemSplash();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.UserShort getUserShortTimeout(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUserShortTimeout();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.LastSeen getUsersLastSeenConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getUsersLastSeenConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.VkBindInSettings getVkBindInSettingsConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getVkBindInSettingsConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.Vk getVkConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getVkConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.VkidBindEmailPromo getVkIdBindEmailPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getVkIdBindEmailPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.WalletOnMailsList getWalletOnMailsList(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getWalletOnMailsList();
        }

        @Deprecated
        @Nullable
        public static String getWalletWebConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getWalletWebConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.WebAuthNConfig getWebAuthNConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getWebAuthNConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.WebAuthNDisablerPromoConfig getWebAuthNDisablerPromoConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getWebAuthNDisablerPromoConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.XmailPromoConfig getXmailPlateConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getXmailPlateConfig();
        }

        @Deprecated
        @NotNull
        public static DTOConfiguration.Config.XmailRegDeeplinkConfig getXmailRegDeeplinkConfig(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.getXmailRegDeeplinkConfig();
        }

        @Deprecated
        public static boolean isAccountManagerAddingFallbackEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAccountManagerAddingFallbackEnabled();
        }

        @Deprecated
        public static boolean isAccountManagerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAccountManagerEnabled();
        }

        @Deprecated
        public static boolean isAccountManagerFallbackEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAccountManagerFallbackEnabled();
        }

        @Deprecated
        public static boolean isAdBannerReloadEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAdBannerReloadEnabled();
        }

        @Deprecated
        public static boolean isAddContactFooterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAddContactFooterEnabled();
        }

        @Deprecated
        public static boolean isAdsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAdsEnabled();
        }

        @Deprecated
        public static boolean isAllowedRegistrationWithoutPhone(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAllowedRegistrationWithoutPhone();
        }

        @Deprecated
        public static boolean isAnalyticSendingAckAndOpenEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAnalyticSendingAckAndOpenEnabled();
        }

        @Deprecated
        public static boolean isAnrSendingInTracerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAnrSendingInTracerEnabled();
        }

        @Deprecated
        public static boolean isAnyFolderMassOperationsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAnyFolderMassOperationsEnabled();
        }

        @Deprecated
        public static boolean isAppMetricsTrackerAnrDetectEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAppMetricsTrackerAnrDetectEnabled();
        }

        @Deprecated
        public static boolean isAppMetricsTrackerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAppMetricsTrackerEnabled();
        }

        @Deprecated
        public static boolean isAuthTypeChangePreferenceEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAuthTypeChangePreferenceEnabled();
        }

        @Deprecated
        public static boolean isAutoBlockQuoteEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAutoBlockQuoteEnabled();
        }

        @Deprecated
        public static boolean isAutodetectToTranslateLetterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isAutodetectToTranslateLetterEnabled();
        }

        @Deprecated
        public static boolean isBackendQuotationParserDisabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isBackendQuotationParserDisabled();
        }

        @Deprecated
        public static boolean isBatchPrefetchMetaThreadsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isBatchPrefetchMetaThreadsEnabled();
        }

        @Deprecated
        public static boolean isBetaStateEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isBetaStateEnabled();
        }

        @Deprecated
        public static boolean isBottomInsetEnabledForBottomSheets(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isBottomInsetEnabledForBottomSheets();
        }

        @Deprecated
        public static boolean isByteEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isByteEnabled();
        }

        @Deprecated
        public static boolean isCheckConnectionBeforeSend(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCheckConnectionBeforeSend();
        }

        @Deprecated
        public static boolean isCheckFacebookInstalled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCheckFacebookInstalled();
        }

        @Deprecated
        public static boolean isChildRegistrationFixEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isChildRegistrationFixEnabled();
        }

        @Deprecated
        public static boolean isCodeAuthEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCodeAuthEnabled();
        }

        @Deprecated
        public static boolean isCollectorsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCollectorsEnabled();
        }

        @Deprecated
        public static boolean isCollectorsHintEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCollectorsHintEnabled();
        }

        @Deprecated
        public static boolean isCommonMailAdapterPreferred(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCommonMailAdapterPreferred();
        }

        @Deprecated
        public static boolean isCopyInAccountDrawerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCopyInAccountDrawerEnabled();
        }

        @Deprecated
        public static boolean isCrashlyticsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isCrashlyticsEnabled();
        }

        @Deprecated
        public static boolean isDarkThemeActivityRecreateFixEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDarkThemeActivityRecreateFixEnabled();
        }

        @Deprecated
        public static boolean isDataAttributesExtractionEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDataAttributesExtractionEnabled();
        }

        @Deprecated
        public static boolean isDebugAnalyticsOfMigrationFromMpopEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDebugAnalyticsOfMigrationFromMpopEnabled();
        }

        @Deprecated
        public static boolean isDeeplinkSmartRepliesEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDeeplinkSmartRepliesEnabled();
        }

        @Deprecated
        public static boolean isDeleteMsgByPushEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDeleteMsgByPushEnabled();
        }

        @Deprecated
        public static boolean isDivKitImgAnalyticsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDivKitImgAnalyticsEnabled();
        }

        @Deprecated
        public static boolean isDividersInMailsListEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isDividersInMailsListEnabled();
        }

        @Deprecated
        public static boolean isEmailServicesLocaleIndependent(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEmailServicesLocaleIndependent();
        }

        @Deprecated
        public static boolean isEmailWhiteSpaceProhibitionEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEmailWhiteSpaceProhibitionEnabled();
        }

        @Deprecated
        public static boolean isEmojisNeedToRemove(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEmojisNeedToRemove();
        }

        @Deprecated
        public static boolean isEmojisSettingsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEmojisSettingsEnabled();
        }

        @Deprecated
        public static boolean isEnableForceAuthByVKID(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEnableForceAuthByVKID();
        }

        @Deprecated
        public static boolean isEnableReportLastExitReasonId(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEnableReportLastExitReasonId();
        }

        @Deprecated
        public static boolean isEventReactionSurveyEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isEventReactionSurveyEnabled();
        }

        @Deprecated
        public static boolean isFeedbackButtonInAboutEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFeedbackButtonInAboutEnabled();
        }

        @Deprecated
        public static boolean isFeedbackInAccountDrawerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFeedbackInAccountDrawerEnabled();
        }

        @Deprecated
        public static boolean isFirebasePerformanceAvailable(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFirebasePerformanceAvailable();
        }

        @Deprecated
        public static boolean isFixVkAccountBreakRefreshTokenEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFixVkAccountBreakRefreshTokenEnabled();
        }

        @Deprecated
        public static boolean isFoldingDevicesSupportEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFoldingDevicesSupportEnabled();
        }

        @Deprecated
        public static boolean isForcedForegroundServiceStart(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isForcedForegroundServiceStart();
        }

        @Deprecated
        public static boolean isFormatterHyphenEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFormatterHyphenEnabled();
        }

        @Deprecated
        public static boolean isFormatterNbspDisabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isFormatterNbspDisabled();
        }

        @Deprecated
        public static boolean isGoToActionButtonInMailsListEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isGoToActionButtonInMailsListEnabled();
        }

        @Deprecated
        public static boolean isHelpInAccountDrawerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isHelpInAccountDrawerEnabled();
        }

        @Deprecated
        public static boolean isHelpInAuthScreenEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isHelpInAuthScreenEnabled();
        }

        @Deprecated
        public static boolean isHideKeyboardOnLoginScreen(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isHideKeyboardOnLoginScreen();
        }

        @Deprecated
        public static boolean isHmsMessageServicesEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isHmsMessageServicesEnabled();
        }

        @Deprecated
        public static boolean isImapAuthAccessWorkaroundEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isImapAuthAccessWorkaroundEnabled();
        }

        @Deprecated
        public static boolean isImapFixAuthWorkaroundEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isImapFixAuthWorkaroundEnabled();
        }

        @Deprecated
        public static boolean isImapOnly(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isImapOnly();
        }

        @Deprecated
        public static boolean isImapPushSubscriptionEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isImapPushSubscriptionEnabled();
        }

        @Deprecated
        public static boolean isImapSkipMailruOauthSteps(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isImapSkipMailruOauthSteps();
        }

        @Deprecated
        public static boolean isInsetsHandlingEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isInsetsHandlingEnabled();
        }

        @Deprecated
        public static boolean isInternetRuRegistrationEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isInternetRuRegistrationEnabled();
        }

        @Deprecated
        public static boolean isInternetRuSecurityEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isInternetRuSecurityEnabled();
        }

        @Deprecated
        public static boolean isLibverifyEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isLibverifyEnabled();
        }

        @Deprecated
        public static boolean isLibverifyPushesPassEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isLibverifyPushesPassEnabled();
        }

        @Deprecated
        public static boolean isLightModeEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isLightModeEnabled();
        }

        @Deprecated
        public static boolean isLogsInCrashReportEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isLogsInCrashReportEnabled();
        }

        @Deprecated
        public static boolean isMailFromOtherBoxesEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailFromOtherBoxesEnabled();
        }

        @Deprecated
        public static boolean isMailListRemoveDuplicatesEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailListRemoveDuplicatesEnabled();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesAsyncLoading(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesAsyncLoading();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesDirectDownload(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesDirectDownload();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesLazyLoading(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesLazyLoading();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesSequentialLoading(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesSequentialLoading();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesUseCache(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesUseCache();
        }

        @Deprecated
        public static boolean isMailViewInlineImagesUsePrefetch(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailViewInlineImagesUsePrefetch();
        }

        @Deprecated
        public static boolean isMailWebViewImagesDirectDownload(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailWebViewImagesDirectDownload();
        }

        @Deprecated
        public static boolean isMailWebviewThemeFixEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMailWebviewThemeFixEnabled();
        }

        @Deprecated
        public static boolean isMapPlateEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMapPlateEnabled();
        }

        @Deprecated
        public static boolean isMetaThreadBoldDomainsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMetaThreadBoldDomainsEnabled();
        }

        @Deprecated
        public static boolean isMetaThreadDomainsSubjectEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMetaThreadDomainsSubjectEnabled();
        }

        @Deprecated
        public static boolean isMetaThreadsActionsUndoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMetaThreadsActionsUndoEnabled();
        }

        @Deprecated
        public static boolean isMetaThreadsNewCounterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMetaThreadsNewCounterEnabled();
        }

        @Deprecated
        public static boolean isMigrationFromMpopToOauthEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMigrationFromMpopToOauthEnabled();
        }

        @Deprecated
        public static boolean isMiniMailEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMiniMailEnabled();
        }

        @Deprecated
        public static boolean isMovePushSupported(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMovePushSupported();
        }

        @Deprecated
        public static boolean isMsgBodyAdBlockEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMsgBodyAdBlockEnabled();
        }

        @Deprecated
        public static boolean isMultiAccountEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isMultiAccountEnabled();
        }

        @Deprecated
        public static boolean isNewExternalAuthEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNewExternalAuthEnabled();
        }

        @Deprecated
        public static boolean isNewExternalAuthForceCollectorsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNewExternalAuthForceCollectorsEnabled();
        }

        @Deprecated
        public static boolean isNewMetaThreadsSettingsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNewMetaThreadsSettingsEnabled();
        }

        @Deprecated
        public static boolean isNewNetworkRequestEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNewNetworkRequestEnabled();
        }

        @Deprecated
        public static boolean isNewSettingsHelpMenuTextEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNewSettingsHelpMenuTextEnabled();
        }

        @Deprecated
        public static boolean isNotificationDetailedLogEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNotificationDetailedLogEnabled();
        }

        @Deprecated
        public static boolean isNotificationFilterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNotificationFilterEnabled();
        }

        @Deprecated
        public static boolean isNotificationPromoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNotificationPromoEnabled();
        }

        @Deprecated
        public static boolean isNotificationRouterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isNotificationRouterEnabled();
        }

        @Deprecated
        public static boolean isOAuthEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isOAuthEnabled();
        }

        @Deprecated
        public static boolean isOauthForcedEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isOauthForcedEnabled();
        }

        @Deprecated
        public static boolean isOrderStatusConfigEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isOrderStatusConfigEnabled();
        }

        @Deprecated
        public static boolean isPersonalDataProcessingDenialVisible(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isPersonalDataProcessingDenialVisible();
        }

        @Deprecated
        public static boolean isPriceThresholdEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isPriceThresholdEnabled();
        }

        @Deprecated
        public static boolean isPromoGoogleAccountEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isPromoGoogleAccountEnabled();
        }

        @Deprecated
        public static boolean isPushActionIconAllowed(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isPushActionIconAllowed();
        }

        @Deprecated
        public static boolean isPushMarkReadSingleAllowed(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isPushMarkReadSingleAllowed();
        }

        @Deprecated
        public static boolean isQrCodeFromImageEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isQrCodeFromImageEnabled();
        }

        @Deprecated
        public static boolean isQuickactionMoveToBinSplited(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isQuickactionMoveToBinSplited();
        }

        @Deprecated
        public static boolean isRealSelectAllEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRealSelectAllEnabled();
        }

        @Deprecated
        public static boolean isRealSelectAllEnabledInTrash(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRealSelectAllEnabledInTrash();
        }

        @Deprecated
        public static boolean isRecaptchaEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRecaptchaEnabled();
        }

        @Deprecated
        public static boolean isRedirectLetterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRedirectLetterEnabled();
        }

        @Deprecated
        public static boolean isReferenceTablePreferred(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isReferenceTablePreferred();
        }

        @Deprecated
        public static boolean isRefreshNotificationsOnStartEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRefreshNotificationsOnStartEnabled();
        }

        @Deprecated
        public static boolean isRefreshTokenUpdateAllowed(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRefreshTokenUpdateAllowed();
        }

        @Deprecated
        public static boolean isRegFormAnalyticsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRegFormAnalyticsEnabled();
        }

        @Deprecated
        public static boolean isRegServerValidationPasswordEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRegServerValidationPasswordEnabled();
        }

        @Deprecated
        public static boolean isRelevantFlagEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRelevantFlagEnabled();
        }

        @Deprecated
        public static boolean isReminderPushEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isReminderPushEnabled();
        }

        @Deprecated
        public static boolean isReminderPushOnlyForInactiveUsers(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isReminderPushOnlyForInactiveUsers();
        }

        @Deprecated
        public static boolean isRemoveAfterSpamEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRemoveAfterSpamEnabled();
        }

        @Deprecated
        public static boolean isRemoveAfterSpamGrantedByDefault(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRemoveAfterSpamGrantedByDefault();
        }

        @Deprecated
        public static boolean isRemoveAfterSpamNewslettersOnly(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRemoveAfterSpamNewslettersOnly();
        }

        @Deprecated
        public static boolean isReportBugInAccountDrawerEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isReportBugInAccountDrawerEnabled();
        }

        @Deprecated
        public static boolean isReportSuspiciousEmailEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isReportSuspiciousEmailEnabled();
        }

        @Deprecated
        public static boolean isRequestDurationAnalyticsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRequestDurationAnalyticsEnabled();
        }

        @Deprecated
        public static boolean isRequestPinAppWidgetSupported(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRequestPinAppWidgetSupported();
        }

        @Deprecated
        public static boolean isResourcesOverridden(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isResourcesOverridden();
        }

        @Deprecated
        public static boolean isRestorePasswordWebViewEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isRestorePasswordWebViewEnabled();
        }

        @Deprecated
        public static boolean isSSLCertificatesInstallationEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSSLCertificatesInstallationEnabled();
        }

        @Deprecated
        public static boolean isSafePendingIntentEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSafePendingIntentEnabled();
        }

        @Deprecated
        public static boolean isSafetyFormatterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSafetyFormatterEnabled();
        }

        @Deprecated
        public static boolean isSafetyVerificationEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSafetyVerificationEnabled();
        }

        @Deprecated
        public static boolean isSanitizeCookieEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSanitizeCookieEnabled();
        }

        @Deprecated
        public static boolean isSanitizeHtmlContentEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSanitizeHtmlContentEnabled();
        }

        @Deprecated
        public static boolean isSanitizedScriptForAllAccountEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSanitizedScriptForAllAccountEnabled();
        }

        @Deprecated
        public static boolean isSaveAnalyticOpenUrlInLocalDataBaseEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSaveAnalyticOpenUrlInLocalDataBaseEnabled();
        }

        @Deprecated
        public static boolean isScheduleWorkToBackgroundThread(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isScheduleWorkToBackgroundThread();
        }

        @Deprecated
        public static boolean isSearchByLabelsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSearchByLabelsEnabled();
        }

        @Deprecated
        public static boolean isSearchMassOperationsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSearchMassOperationsEnabled();
        }

        @Deprecated
        public static boolean isSelectFromOtherAppButtonEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSelectFromOtherAppButtonEnabled();
        }

        @Deprecated
        public static boolean isShimmerInsteadOfLoaderMailview(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isShimmerInsteadOfLoaderMailview();
        }

        @Deprecated
        public static boolean isShowEveryoneXmailEntryPoint(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isShowEveryoneXmailEntryPoint();
        }

        @Deprecated
        public static boolean isShowSelectorAfterPermissions(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isShowSelectorAfterPermissions();
        }

        @Deprecated
        public static boolean isShowSelectorOnAddingNewAccount(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isShowSelectorOnAddingNewAccount();
        }

        @Deprecated
        public static boolean isShowWalletAfterClickOnReceiptsMetathread(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isShowWalletAfterClickOnReceiptsMetathread();
        }

        @Deprecated
        public static boolean isSmartLockEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSmartLockEnabled();
        }

        @Deprecated
        public static boolean isSmartReplyEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSmartReplyEnabled();
        }

        @Deprecated
        public static boolean isSmartSortInMailSettings(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSmartSortInMailSettings();
        }

        @Deprecated
        public static boolean isSubmitFormEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isSubmitFormEnabled();
        }

        @Deprecated
        public static boolean isTranslateLetterEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isTranslateLetterEnabled();
        }

        @Deprecated
        public static boolean isTwoStepCodeAuthEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isTwoStepCodeAuthEnabled();
        }

        @Deprecated
        public static boolean isUnescapeMailtoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUnescapeMailtoEnabled();
        }

        @Deprecated
        public static boolean isUnifiedAttachDownloadEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUnifiedAttachDownloadEnabled();
        }

        @Deprecated
        public static boolean isUnsubscribeEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUnsubscribeEnabled();
        }

        @Deprecated
        public static boolean isUriDecodeInAttachmentsEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUriDecodeInAttachmentsEnabled();
        }

        @Deprecated
        public static boolean isUseExpeditedSettingsForForceSync(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseExpeditedSettingsForForceSync();
        }

        @Deprecated
        public static boolean isUseFakeHelpLink(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseFakeHelpLink();
        }

        @Deprecated
        public static boolean isUseNativeXmailReg(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseNativeXmailReg();
        }

        @Deprecated
        public static boolean isUseNativeXmailWithVkidReg(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseNativeXmailWithVkidReg();
        }

        @Deprecated
        public static boolean isUseSupervisorJobInWorkersEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseSupervisorJobInWorkersEnabled();
        }

        @Deprecated
        public static boolean isUseSystemUserAgentHelpersUpdate(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseSystemUserAgentHelpersUpdate();
        }

        @Deprecated
        public static boolean isUseUpdateSnackbarsMethod(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUseUpdateSnackbarsMethod();
        }

        @Deprecated
        public static boolean isUserDataRefreshEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUserDataRefreshEnabled();
        }

        @Deprecated
        public static boolean isUserRegisteredByVKIDPromoEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUserRegisteredByVKIDPromoEnabled();
        }

        @Deprecated
        public static boolean isUsingJsCalculatedHeight(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isUsingJsCalculatedHeight();
        }

        @Deprecated
        public static boolean isWebHistoryNavigationEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isWebHistoryNavigationEnabled();
        }

        @Deprecated
        public static boolean isWebHistoryTrustedUrlsNavigationEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isWebHistoryTrustedUrlsNavigationEnabled();
        }

        @Deprecated
        public static boolean isWebViewMixedSourcesEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isWebViewMixedSourcesEnabled();
        }

        @Deprecated
        public static boolean isWebviewWorkaroundEnabled(@NotNull ConfigurationWithRawData configurationWithRawData) {
            return ConfigurationWithRawData.super.isWebviewWorkaroundEnabled();
        }
    }

    @NotNull
    Field getConfigurationDataFiled();

    @NotNull
    JSONObject getConfigurationDataObject();

    @NotNull
    DTORawConfiguration getDtoConfiguration();
}
