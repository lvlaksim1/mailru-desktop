package ru.mail.ui.fragments.settings;

import android.accounts.Account;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import com.vk.lists.PaginationHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import ru.mail.android_utils.SdkUtils;
import ru.mail.android_utils.webview.WebViewUpdateDialogCreator;
import ru.mail.auth.Authenticator;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.glasha.domain.managers.FolderGrantsManager;
import ru.mail.locator.Locator;
import ru.mail.logic.cmd.ReplyMessageCmd;
import ru.mail.logic.content.FolderMatcher;
import ru.mail.logic.content.Permission;
import ru.mail.logic.content.feature.features.SaveSentMessagesSettingsFeature;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.mytarget.MyTargetAdsManagerImpl;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mails.R;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.snackbar.SnackbarParams;
import ru.mail.snackbar.SnackbarUpdater;
import ru.mail.snackbar.SnackbarWrapper;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.ui.dialogs.RateTheAppDialog;
import ru.mail.ui.fragments.view.statusbar.StatusBarConfigurator;
import ru.mail.ui.fragments.view.toolbar.base.ToolbarConfigurator;
import ru.mail.ui.fragments.view.toolbar.base.ToolbarManager;
import ru.mail.util.DaysOfUsageCounter;
import ru.mail.util.log.Log;
import ru.mail.util.push.NotificationConfiguration;
import ru.mail.util.reporter.AbstractErrorReporter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public abstract class BaseSettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, ActivityDestroyObservable, SnackbarUpdater {
    public static final String CHECK_PUSH_TOKEN = "check_push_token";
    public static final String KEY_ALLOW_SCREENSHOTS = "allow_screenshots";
    public static final String KEY_CATEGORY_ACCOUNTS = "accounts";
    private static final String KEY_CATEGORY_DEVELOP = "develop_category";
    public static final String KEY_PREF_ACTION_BAR_ANIMATION_DURATION = "action_bar_animation_duration";
    public static final String KEY_PREF_ADD_CONTACT_SHOWED = "add_contact_showed";
    public static final String KEY_PREF_ADMAN_SLOT = "adman_slot_key";
    public static final String KEY_PREF_ADS_SUBSCRIPTIONS = "prefs_key_appearance_ads_subscriptions";
    public static final String KEY_PREF_ADVERTISING_CONTENT_EXPIRED = "advertising_content_expired";
    public static final String KEY_PREF_ADVERTISING_ENABLED = "dont_use_this_password_jgeVjtimgjvjxm";
    public static final String KEY_PREF_API_PUSH = "ru.mail.preference_api_push";
    public static final String KEY_PREF_ARCHIVE = "archive";
    public static final String KEY_PREF_AUTH_TYPE_CHANGE = "auth_type_change";
    public static final String KEY_PREF_BETA_BUILD_VERSION_NUMBER = "key_pref_beta_build_version_number";
    public static final String KEY_PREF_BETA_EXPIRATION_DATE = "key_pref_beta_expired_date";
    public static final String KEY_PREF_BLIND_COPY = "blind_copy";
    public static final String KEY_PREF_BREAK_PASSWORD = "break_password";
    public static final String KEY_PREF_CHANGE_PASSWORD = "key_change_password";
    public static final String KEY_PREF_CHANGE_PHONE_NUMBER = "change_phone_number";
    public static final String KEY_PREF_CLEAR_ACCOUNT_MANAGER_DATA = "prefs_key_clear_account_manager_data";
    public static final String KEY_PREF_CLEAR_CONFIG_OVERRIDING = "prefs_key_clear_config_overriding";
    public static final String KEY_PREF_CLEAR_PASSWORD = "clear_password";
    public static final String KEY_PREF_CLOUD_AUTO_UPLOAD = "key_pref_cloud_auto_upload";
    public static final String KEY_PREF_CLOUD_WIFI_ONLY_UPLOAD = "key_pref_cloud_wifi_only_upload";
    public static final String KEY_PREF_CONFIRM_NO_SPAM = "prefs_confirm_no_spam";
    public static final String KEY_PREF_CONFIRM_REMOVE_FROM_TRASH = "prefs_confirm_remove_from_trash";
    public static final String KEY_PREF_DECOR = "decor";
    public static final String KEY_PREF_DEFENCE_HTTPS = "develop_defence_https";
    public static final String KEY_PREF_DELETE_ACCOUNT = "delete_account";
    public static final String KEY_PREF_DELETE_ACCOUNT_IN_BROWSER = "delete_account_in_browser";
    public static final String KEY_PREF_DIALOG_MARK_SPAM = "dialog_mark_spam";
    public static final String KEY_PREF_DIALOG_MOVE_TO_BIN = "dialog_move_to_bin";
    public static final String KEY_PREF_FACEBOOK_INSTALLED = "fb_installed";
    public static final String KEY_PREF_FACEBOOK_LIKE_WAS_MADE = "fb_like_made";
    public static final String KEY_PREF_FILTERS = "filters";
    public static final String KEY_PREF_FOLDERS = "folders";
    public static final String KEY_PREF_GENERAL = "general";
    public static final String KEY_PREF_HELP_LINK = "help_link";
    public static final String KEY_PREF_HOST_PUSH = "ru.mail.preference_host_push";
    public static final String KEY_PREF_INACTIVE_SNACK_BAR_DELAY = "inactive_snack_bar_delay";
    public static final String KEY_PREF_IS_BACKUP_TURNED_ON = "key_pref_contact_export";
    public static final String KEY_PREF_IS_BETA_VERSION = "key_pref_is_beta_version";
    public static final String KEY_PREF_MAIL_FROM_OTHER_BOXES = "mail_from_other_boxes";
    public static final String KEY_PREF_MANAGE_SUBSCRIPTIONS = "manage_subscriptions";
    public static final String KEY_PREF_MINI_RESET_VKID = "mini_reset_vkid";
    public static final String KEY_PREF_MIN_SCHEDULE_SEND_DELAY = "min_schedule_send_delay";
    public static final String KEY_PREF_NAME_AND_AVATAR = "name_and_avatar";
    public static final String KEY_PREF_OPEN_LINKS_IN_BROWSER = "open_in_browser";
    public static final String KEY_PREF_OPEN_PORTAL_KIT = "key_open_portal_kit";
    public static final String KEY_PREF_PERSONAL_DATA_PROCESSING = "prefs_key_appearance_personal_data_processing";
    public static final String KEY_PREF_PLATES_WITH_EXTRA = "key_pref_plates_with_extra";
    public static final String KEY_PREF_PREFETCH_ATTACH = "prefs_key_prefetch_attach";
    public static final String KEY_PREF_PROTECTION_CHANGE_PIN = "app_protection_pin_change";
    public static final String KEY_PREF_PROTECTION_ENABLE_PIN = "app_protection_pin_enable";
    public static final String KEY_PREF_PROTECTION_PIN_LOCK_TIMEOUT = "app_protection_pin_lock_timeout";
    public static final String KEY_PREF_PROTECTION__ENABLE_FINGER = "app_protection_fingerprint_enable";
    public static final String KEY_PREF_PUSH = "push";
    public static final String KEY_PREF_PUSH_FILTER_COUPON_SCREEN = "push_filtration_coupon_screen";
    public static final String KEY_PREF_PUSH_FILTER_COUPON_SET = "push_filtration_coupon_set";
    public static final String KEY_PREF_PUSH_FILTER_COUPON_SET_RESERVED = "push_filtration_coupon_set_reserved";
    public static final String KEY_PREF_PUSH_FILTER_FOLDER_SET = "push_filtration_folder_set";
    public static final String KEY_PREF_PUSH_FILTER_SCREEN = "push_filtration_screen";
    public static final String KEY_PREF_PUSH_FILTER_SOCIAL = "push_filtration_social";
    public static final String KEY_PREF_PUSH_FILTER_SOCIAL_SCREEN = "push_filtration_social_screen";
    public static final String KEY_PREF_PUSH_FILTER_SOCIAL_SET = "push_filtration_social_set";
    public static final String KEY_PREF_PUSH_FILTER_SOCIAL_SET_RESERVED = "push_filtration_social_set_reserved";
    public static final String KEY_PREF_PUSH_PRIVACY_SCREEN = "push_privacy_screen";
    public static final String KEY_PREF_PUSH_TOKEN_CHECKING_PERIOD = "push_token_checking_period";
    public static final String KEY_PREF_QUICK_ACTION_SETTINGS = "quick_action_settings";
    public static final String KEY_PREF_RATE_APP = "rate_app";
    public static final String KEY_PREF_RESET_SENDERS_HINT = "reset_senders_hint";
    public static final String KEY_PREF_SAK_SSL_PINNING_ENABLED = "prefs_key_sak_ssl_pinning_enabled";
    public static final String KEY_PREF_SCREEN_ROTATION = "screen_rotation";
    public static final String KEY_PREF_SECURITY = "security";
    public static final String KEY_PREF_SECURITY_PHONE = "security_phone_settings";
    public static final String KEY_PREF_SEND_APP_SIZE_ANALYTICS = "send_app_size_analytics";
    public static final String KEY_PREF_SHOW_IMAGE_SETTINGS = "show_images_settings_pref";
    public static final String KEY_PREF_SHRINK_DB = "db_shrink";
    public static final String KEY_PREF_SIGNAL_INDICATOR = "is_signal_indicator";
    public static final String KEY_PREF_SOUND_SETTINGS = "sound_settings";
    public static final String KEY_PREF_START_CONTACTS_BACKUP = "key_pref_start_contacts_backup";
    public static final String KEY_PREF_START_SYNC = "key_pref_start_sync";
    public static final String KEY_PREF_STORAGE_SIZE = "storage_size";
    public static final String KEY_PREF_SUBSCRIPT_SCREEN = "subscript_screen";
    public static final String KEY_PREF_SYSTEM_NOTIFICATION = "push_system_setting";
    public static final String KEY_PREF_TEST_SIGNAL_QUALITY = "test_signal_quality";
    public static final String KEY_PREF_THEME_PICKER = "prefs_key_theme_picker";
    public static final String KEY_PREF_TOGGLE_PREFETCHER_ENABLED = "toggle_prefetcher_enabled";
    public static final String KEY_PREF_USE_DEV_PUSH = "ru.mail.use_dev_host_and_api";
    public static final String KEY_PREF_XMAIL_MIGRATION_HINT = "reset_xmail_migration_hint";
    public static final String KEY_SAVE_ACCOUNT_WITH_WRONG_PASSWORD_TO_SMARTLOCK = "save_account_with_wrong_pass";
    public static final String KEY_SAVE_TO_SMARTLOCK = "save_to_smart_lock";
    public static final String KEY_SAVE_TO_SMARTLOCK_AFTER_LOGIN = "save_to_smartlock_after_login";
    public static final String KEY_SAVE_WRONG_ACCOUNT_TO_SMARTLOCK = "save_wrong_account_to_smart_lock";
    public static final String KEY_SUBSCRIPT_CHANGED = "subscript_changed";
    private static final Log LOG = Log.getLog("BaseSettingsActivity");
    public static final String PREFERENCE_SEND_PUSH_SETTINGS_FAIL = "send_push_settings_fail";
    public static final String PREFERENCE_SEND_SETTINGS_INPROGRESS = "send_settings_inprogress";
    public static final String PREF_KEY_ADD_GOOGLE_IS_SHOWING = "add_google_plate_is_showing";
    public static final String PREF_KEY_ADD_MAILBOX_IS_SHOWING = "add_mailbox_plate_is_showing";
    public static final String PREF_KEY_ANDROID_DEVICE_ID_OMICRON = "android_device_ID_omicron";
    public static final String PREF_KEY_ASSERT_ME = "tratatatatam";
    public static final String PREF_KEY_BREAK_ACCESS_TOKEN_TO_ALL = "break_access_token_to_all";
    public static final String PREF_KEY_BREAK_DIRECT_ACCESS_TOKEN_TO_ALL = "break_direct_token_all";
    public static final String PREF_KEY_BREAK_MPOP_COOKIE_TO_ALL = "break_mpop_cookie_to_all";
    public static final String PREF_KEY_BREAK_MPOP_TOKEN_TO_ALL = "break_mpop_token_to_all";
    public static final String PREF_KEY_BREAK_PUSH_TOKEN = "break_push_token";
    public static final String PREF_KEY_BREAK_REFRESH_TOKEN_TO_ALL = "break_refresh_token_to_all";
    public static final String PREF_KEY_CLEAR_MAILS_CACHE = "clear_mails_db";
    public static final String PREF_KEY_CONFIGURATION_SLOT = "threads_test_slot";
    public static final String PREF_KEY_CONTACTS_PERMISSION_PLATE_SHOWED = "contacts_permission_plate_showed";
    public static final String PREF_KEY_CONTACTS_PERMISSION_PLATE_SHOWING_COUNT = "plate_showing_count";
    public static final String PREF_KEY_COPY_PUSH_TOKEN = "copy_push_token_key";
    public static final String PREF_KEY_CRASH_ME = "tratatata";
    public static final String PREF_KEY_CRASH_REMOTE_EXCEPTION_MAIN_THREAD = "remote_ex_main_thread";
    public static final String PREF_KEY_CRASH_REMOTE_EXCEPTION_NEW_THREAD = "remote_ex_new_thread";
    public static final String PREF_KEY_DAYS_OF_USAGE = "duc_counter";
    public static final String PREF_KEY_DAYS_OF_USAGE_LAST_UPDATE = "duc_counter_last_update";
    public static final String PREF_KEY_DETECT_WEBVIEW_BROKEN = "detect_webview_broken";
    public static final String PREF_KEY_GOOGLE_PLUS_ACTION_WAS_MADE = "google_plus_action_was_made";
    public static final String PREF_KEY_IMAP_SETTING_SAVE_SENT_MESSAGES_ACTIVITY = "sent_messages_imap_setting_activity";
    public static final String PREF_KEY_INVALIDATE_ACCESS_TOKEN = "invalidate_token";
    public static final String PREF_KEY_INVALIDATE_REFRESH_TOKEN = "invalidate_refresh_token";
    public static final String PREF_KEY_LAUNCH_CLEAN_CACHE = "launch_clean_cache_folder_work";
    public static final String PREF_KEY_NATIVE_CRASH = "ulalala";
    public static final String PREF_KEY_NEED_SHOW_CONTACTS_PERMISSION_PLATE = "need_show_contacts_permission_plate";
    public static final String PREF_KEY_RATE_APP_IS_SHOWING = "rate_app_plate_is_showing";
    public static final String PREF_KEY_RATE_PLATE_MARKET_WAS_OPENED = "rate_plate_market_opened";
    public static final String PREF_KEY_RATE_PLATE_RUSTORE_WAS_OPENED = "rate_plate_rustore_opened";
    public static final String PREF_KEY_REFRESH_ACCESS_TOKEN_TO_CURRENT_ACCOUNT = "refresh_access_token_to_current_account";
    public static final String PREF_KEY_REMOVE_TSA_COOKIE_FROM_ACCOUNT_MANAGER_TO_ALL = "remove_all_tsa_cookie_from_account_manager";
    public static final String PREF_KEY_RESTART_APP = "restart_app";
    public static final String PREF_KEY_RUNS_COUNT_AFTER_UPDATE_MUTABLE = "runs_count_after_update_imm";
    public static final String PREF_KEY_SPLASH_SCREEN_DELAY = "splash_screen_delay";
    public static final String PREF_KEY_THREADS_PLATE_IS_SHOWING = "threads_plate_is_showing";
    public static final String PREF_KEY_TRY_BETA_IS_SHOWING = "try_beta_plate_is_showing";
    public static final String PREF_KEY_USED_PUSH_TRANSPORTS = "used_push_transports";
    public static final String PREF_KEY_USE_DEBUG_WEB_VIEW = "use_debug_web_view";
    public static final String PREF_KEY_USE_GECKO_VIEW = "use_gecko_view";
    public static final String PREF_KEY_USE_USA_LOCATION = "use_usa_location_secret_key";
    public static final String TAG_RATE_THE_APP = "RateTheApp";
    private List<PreferenceManager.OnActivityDestroyListener> mDestroyListener;
    private AlertDialog webViewErrorDialog;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    public static abstract class ShowImages {
        private static final /* synthetic */ ShowImages[] $VALUES = $values();
        public static final ShowImages ALWAYS;
        public static final ShowImages NEVER;
        public static final ShowImages WIFI;

        /* JADX INFO: renamed from: ru.mail.ui.fragments.settings.BaseSettingsActivity$ShowImages$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends ShowImages {
            @Override // ru.mail.ui.fragments.settings.BaseSettingsActivity.ShowImages
            int getStringId() {
                return R.string.prefs_show_images_label_1;
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.ui.fragments.settings.BaseSettingsActivity$ShowImages$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends ShowImages {
            @Override // ru.mail.ui.fragments.settings.BaseSettingsActivity.ShowImages
            int getStringId() {
                return R.string.prefs_show_images_label_2;
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.ui.fragments.settings.BaseSettingsActivity$ShowImages$3, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass3 extends ShowImages {
            @Override // ru.mail.ui.fragments.settings.BaseSettingsActivity.ShowImages
            int getStringId() {
                return R.string.prefs_show_images_label_3;
            }

            private AnonymousClass3(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ ShowImages[] $values() {
            return new ShowImages[]{NEVER, WIFI, ALWAYS};
        }

        static {
            NEVER = new AnonymousClass1("NEVER", 0);
            WIFI = new AnonymousClass2("WIFI", 1);
            ALWAYS = new AnonymousClass3("ALWAYS", 2);
        }

        public static ShowImages valueOf(String str) {
            return (ShowImages) Enum.valueOf(ShowImages.class, str);
        }

        public static ShowImages[] values() {
            return (ShowImages[]) $VALUES.clone();
        }

        @StringRes
        abstract int getStringId();

        private ShowImages(String str, int i10) {
            super(str, i10);
        }
    }

    private void configureToolbar(@NonNull Toolbar toolbar) {
        ToolbarManager toolbarManagerConfigureToolbar = new ToolbarConfigurator().configureToolbar(this, toolbar);
        toolbar.setBackgroundColor(toolbarManagerConfigureToolbar.getToolbarConfiguration().getActionBarColor());
        Drawable drawable = AppCompatResources.getDrawable(this, toolbarManagerConfigureToolbar.getToolbarConfiguration().getActionBackDrawableResId());
        if (drawable != null) {
            drawable.mutate();
            drawable.setTint(toolbarManagerConfigureToolbar.getToolbarConfiguration().getNavigationIconTint(false));
            getActionBar().setHomeAsUpIndicator(drawable);
        }
    }

    public static int getActionBarAnimationDuration(Context context) {
        return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_ACTION_BAR_ANIMATION_DURATION, String.valueOf(context.getResources().getInteger(android.R.integer.config_shortAnimTime))));
    }

    public static CharSequence getBorderString(SharedPreferences sharedPreferences, Context context) {
        return sharedPreferences.getBoolean(NotificationConfiguration.KEY_PREF_PUSH_DONT_DISTURB, true) ? pushBorderToString(context) : context.getString(R.string.mapp_settings_disable);
    }

    public static String getDateString(Date date, Date date2, Context context) {
        return String.format(context.getString(NotificationConfiguration.getPushDisturbMode(context).getRangeSummaryId()), Integer.valueOf(date.getHours()), Integer.valueOf(date.getMinutes()), Integer.valueOf(date2.getHours()), Integer.valueOf(date2.getMinutes()));
    }

    private static long getLongValue(Context context, String str, String str2) {
        return Long.parseLong(PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2));
    }

    public static String getPushBorder(Context context) {
        return pushBorderToString(context);
    }

    @Deprecated(message = "Can be removed after PushMe SDK integration")
    public static long getPushTokenCheckingPeriod(Context context) {
        try {
            return Long.parseLong(PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_PUSH_TOKEN_CHECKING_PERIOD, "10800000"));
        } catch (NumberFormatException unused) {
            return 10800000L;
        }
    }

    public static long getRateDialogShowUpdateAppTimeout(Context context) {
        return Long.parseLong(PreferenceManager.getDefaultSharedPreferences(context).getString("ru.mail.preference_estimate_dialog_application_update_timeout", "43200")) * 60000;
    }

    public static int getScreenOrientation(Context context) {
        return isScreenRotationEnabled(context) ? -1 : 1;
    }

    public static boolean getShouldAddRules(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_PLATES_WITH_EXTRA, true);
    }

    public static ShowImages getShowImages(Context context) {
        return ShowImages.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_SHOW_IMAGE_SETTINGS, "WIFI"));
    }

    public static long getShrinkDelay(Context context) {
        return Long.parseLong(PreferenceManager.getDefaultSharedPreferences(context).getString("shrinker_delay_dialog", PaginationHelper.DEFAULT_NEXT_FROM)) * 1000;
    }

    public static long getSplashScreenDelay(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_KEY_SPLASH_SCREEN_DELAY, 100L);
    }

    public static String getStorageSize(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_STORAGE_SIZE, context.getString(R.string.settings_storage_size_default));
    }

    @Nullable
    private Toolbar getToolbar() {
        View decorView = getWindow().getDecorView();
        int identifier = getResources().getIdentifier("action_bar", "id", "android");
        if (identifier <= 0) {
            return null;
        }
        View viewFindViewById = decorView.findViewById(identifier);
        if (viewFindViewById instanceof Toolbar) {
            return (Toolbar) viewFindViewById;
        }
        return null;
    }

    public static long getUndoDuration(Context context) {
        return getLongValue(context, "undo_duration", "3000");
    }

    static void handleNoNetwork(Context context) {
        AbstractErrorReporter.from(context).builder().withText(R.string.no_connection).longPeriod().report();
    }

    static void handleNoRegistrationId(Context context) {
        AbstractErrorReporter.from(context).builder().withText(R.string.no_gcm_registration_id).longPeriod().report();
    }

    private void initStatusBar() {
        StatusBarConfigurator.applyDefault(this);
    }

    public static boolean isAddGooglePlateShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_ADD_GOOGLE_IS_SHOWING, false);
    }

    public static boolean isAddMailBoxPlateShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_ADD_MAILBOX_IS_SHOWING, false);
    }

    public static boolean isArchiveEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_ARCHIVE, false);
    }

    public static boolean isAutoSentMessageDisallowed(Context context, String str) {
        Authenticator.Type typeValueOf = Authenticator.Type.valueOf(Authenticator.getAccountManagerWrapper(context.getApplicationContext()).getUserData(new Account(str, BuildConfigVariablesHolder.accountType), "type"));
        return (typeValueOf == Authenticator.Type.OAUTH || typeValueOf == Authenticator.Type.OUTLOOK_OAUTH || typeValueOf == Authenticator.Type.YAHOO_OAUTH) ? false : true;
    }

    public static Boolean isBlindCopyCheck(Context context) {
        return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("blind_copy", false));
    }

    public static Boolean isCloudAutoUploadEnabled(Context context) {
        return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_CLOUD_AUTO_UPLOAD, false));
    }

    public static Boolean isCloudUploadWifiOnly(Context context) {
        return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("key_pref_cloud_wifi_only_upload", true));
    }

    public static boolean isContactFooterShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_ADD_CONTACT_SHOWED, false);
    }

    public static boolean isCurrentAccountThreadsEnabled(Context context) {
        return ThreadPreferenceActivity.isCurrentAccountThreadsAvailable(context);
    }

    private static boolean isDevPushesSupported(@NonNull Context context) {
        return (context.getString(R.string.fcm_dev_pushes_project_id).isEmpty() || context.getString(R.string.fcm_dev_pushes_application_id).isEmpty() || context.getString(R.string.fcm_dev_pushes_api_key).isEmpty()) ? false : true;
    }

    public static boolean isFacebookLikeMade(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_FACEBOOK_LIKE_WAS_MADE, false);
    }

    public static boolean isGooglePlusActionMade(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_GOOGLE_PLUS_ACTION_WAS_MADE, false);
    }

    public static boolean isHttpsEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_DEFENCE_HTTPS, true);
    }

    public static boolean isOpenLinksInBrowser(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_OPEN_LINKS_IN_BROWSER, context.getResources().getBoolean(R.bool.open_links_in_browser));
    }

    public static boolean isPushEnabled(Context context) {
        return Permission.POST_NOTIFICATIONS.isGranted(context) && PreferenceManager.getDefaultSharedPreferences(context).getBoolean("push", true);
    }

    public static boolean isRateAppPlateShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_RATE_APP_IS_SHOWING, false);
    }

    public static boolean isRatePlateMarketOpened(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_RATE_PLATE_MARKET_WAS_OPENED, false);
    }

    public static boolean isRatePlateRuStoreOpened(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_RATE_PLATE_RUSTORE_WAS_OPENED, false);
    }

    public static boolean isRuStoreRateAppUrlIsNotEmpty(Context context) {
        return !((ConfigurationRepository) Locator.from(context).locate(ConfigurationRepository.class)).getConfiguration().getRuStoreRateAppUrl().isEmpty();
    }

    private static boolean isScreenRotationEnabled(Context context) {
        if (SdkUtils.isOreo()) {
            return true;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_SCREEN_ROTATION, true);
    }

    public static boolean isSignalIndicatorShown(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_SIGNAL_INDICATOR, false);
    }

    public static boolean isThreadsPlateShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_THREADS_PLATE_IS_SHOWING, false);
    }

    public static boolean isTryBetaPlateShowed(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_TRY_BETA_IS_SHOWING, false);
    }

    public static boolean isUseDevPushes(@NonNull Context context) {
        if (isDevPushesSupported(context)) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_USE_DEV_PUSH, false);
        }
        return false;
    }

    public static boolean isUserDisabledAds(Context context) {
        return !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_ADVERTISING_ENABLED, context.getResources().getBoolean(R.bool.prefs_advertising_enabled));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        this.webViewErrorDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$onCreate$1(AlertDialog alertDialog) {
        this.webViewErrorDialog = alertDialog;
        return Unit.INSTANCE;
    }

    public static boolean needDetectWebViewBroken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_DETECT_WEBVIEW_BROKEN, false);
    }

    public static boolean needShowAnyPlateInCurrentRun(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_NEED_SHOW_CONTACTS_PERMISSION_PLATE, false);
    }

    public static boolean needUseDebugWebView(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_USE_DEBUG_WEB_VIEW, false);
    }

    public static boolean needUseGeckoView(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREF_KEY_USE_GECKO_VIEW, false);
    }

    public static CharSequence preferenceToString(boolean z10, Context context) {
        return context.getString(z10 ? R.string.mapp_settings_enable : R.string.mapp_settings_disable);
    }

    public static String pushBorderToString(Context context) {
        return getDateString(new Date(NotificationConfiguration.getPushBorderFrom(context)), new Date(NotificationConfiguration.getPushBorderTo(context)), context);
    }

    public static void resetRunsAfterUpdateCount(Context context) {
        DaysOfUsageCounter.setRunsAfterUpdateCount(context, 0);
        DaysOfUsageCounter.setRunsAfterUpdateMutableCount(context, 0);
    }

    public static boolean saveToSmartlockAfterLogin(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_SAVE_TO_SMARTLOCK_AFTER_LOGIN, true);
    }

    public static void setAddGooglePlateShowed(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_ADD_GOOGLE_IS_SHOWING, z10).apply();
    }

    public static void setAddMailBoxPlateShowed(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_ADD_MAILBOX_IS_SHOWING, z10).apply();
    }

    public static void setBccMyself(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("blind_copy", z10).apply();
    }

    public static void setDefaultSubscription(Context context, String str, String str2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(ReplyMessageCmd.getAccountKey(str), str2).apply();
    }

    protected static void setDevPrefIsChildAccount(@NonNull Context context, @NonNull String str, @NonNull String str2) {
        if (str.isEmpty()) {
            return;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(CommonDataManager.KEY_DEV_PREF_CHILD_ACCOUNT_PREFIX + str, str2).apply();
    }

    public static void setFacebookLikeMade(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(KEY_PREF_FACEBOOK_LIKE_WAS_MADE, z10).apply();
    }

    public static void setGooglePlusActionMade(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_GOOGLE_PLUS_ACTION_WAS_MADE, z10).apply();
    }

    public static void setIsThreadsPlateShowed(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_THREADS_PLATE_IS_SHOWING, z10).apply();
    }

    public static void setNeedShowAnyPlateInCurrentRun(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_NEED_SHOW_CONTACTS_PERMISSION_PLATE, z10).apply();
    }

    public static void setRateAppShowed(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_RATE_APP_IS_SHOWING, z10).apply();
    }

    public static void setRatePlateMarketOpened(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_RATE_PLATE_MARKET_WAS_OPENED, true).apply();
    }

    public static void setRatePlateRuStoreOpened(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_RATE_PLATE_RUSTORE_WAS_OPENED, true).apply();
    }

    public static void setSplashScreenDelay(Context context, long j10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(PREF_KEY_SPLASH_SCREEN_DELAY, j10).apply();
    }

    public static void setTryBetaPlateShowed(Context context, boolean z10) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREF_KEY_TRY_BETA_IS_SHOWING, z10).apply();
    }

    public static void setWindowContentOverlayCompat(Context context, View view) {
        if (view instanceof FrameLayout) {
            ((FrameLayout) view).setForeground(context.getResources().getDrawable(R.drawable.preference_overlay));
        }
    }

    public static boolean showArchiveAction(Context context, long j10) {
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        FolderGrantsManager folderGrantsManager = SharedFoldersModuleEntryPoint.folderGrantsManager(context);
        return (FolderMatcher.isTrash(j10, folderGrantsManager) || FolderMatcher.isSpam(j10, folderGrantsManager) || !commonDataManagerFrom.needArchiveAction()) ? false : true;
    }

    protected void addDevelopPreference(@Nullable Preference preference) {
        PreferenceCategory preferenceCategory;
        if (preference == null || (preferenceCategory = (PreferenceCategory) getPreferenceScreen().findPreference(KEY_CATEGORY_DEVELOP)) == null) {
            return;
        }
        preferenceCategory.addPreference(preference);
    }

    protected void disablePreferences(Preference preference) {
        if (preference != null) {
            preference.setEnabled(false);
        }
    }

    protected void enablePreferences(Preference preference) {
        if (preference != null) {
            preference.setEnabled(true);
        }
    }

    @Nullable
    protected Preference findPreference(String str, String str2) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(str);
        if (preferenceCategory != null) {
            return preferenceCategory.findPreference(str2);
        }
        return null;
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public void hide(@NonNull SnackbarParams snackbarParams) {
        SnackbarWrapper.in(getListView()).hide(snackbarParams);
    }

    protected void initAdvertisingSettings() {
        DTOConfiguration.Config.AllowedAdsManagement allowedAdsManagement = ((ConfigurationRepository) Locator.from(getApplicationContext()).locate(ConfigurationRepository.class)).getConfiguration().getAllowedAdsManagement();
        if (allowedAdsManagement == DTOConfiguration.Config.AllowedAdsManagement.CAN_DISABLE) {
            removePreference(KEY_PREF_DECOR, KEY_PREF_ADS_SUBSCRIPTIONS);
        } else if (allowedAdsManagement == DTOConfiguration.Config.AllowedAdsManagement.CAN_BUY_SUBSCRIPTION) {
            removePreference(KEY_PREF_DECOR, KEY_PREF_ADVERTISING_ENABLED);
        } else {
            removePreference(KEY_PREF_DECOR, KEY_PREF_ADVERTISING_ENABLED);
            removePreference(KEY_PREF_DECOR, KEY_PREF_ADS_SUBSCRIPTIONS);
        }
    }

    protected void initArchive() {
        if (CommonDataManager.from(getApplicationContext()).haveArchiveAction()) {
            return;
        }
        removePreference(KEY_PREF_GENERAL, KEY_PREF_ARCHIVE);
    }

    protected void initImapSendMsgSetting() {
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(getApplicationContext());
        Iterator<MailboxProfile> it = commonDataManagerFrom.getAccounts().iterator();
        boolean zIsFeatureSupported = false;
        while (it.hasNext()) {
            zIsFeatureSupported |= commonDataManagerFrom.isFeatureSupported(it.next().getLogin(), SaveSentMessagesSettingsFeature.INSTANCE, new Void[0]);
        }
        if (zIsFeatureSupported) {
            return;
        }
        removePreference(KEY_PREF_GENERAL, PREF_KEY_IMAP_SETTING_SAVE_SENT_MESSAGES_ACTIVITY);
    }

    protected void initSoundSettings() {
        if (((ConfigurationRepository) Locator.from(getApplicationContext()).locate(ConfigurationRepository.class)).getConfiguration().getEnabledSounds().isEmpty()) {
            removePreference(KEY_PREF_GENERAL, KEY_PREF_SOUND_SETTINGS);
        }
    }

    public void initializeActionBar(Preference preference) {
        if (preference == null) {
            return;
        }
        if (preference instanceof PreferenceScreen) {
            initializeActionBar(((PreferenceScreen) preference).getDialog());
        }
        if (preference instanceof PreferenceGroup) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
            int preferenceCount = preferenceGroup.getPreferenceCount();
            for (int i10 = 0; i10 < preferenceCount; i10++) {
                initializeActionBar(preferenceGroup.getPreference(i10));
            }
        }
    }

    public boolean isInProgress() {
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(PREFERENCE_SEND_SETTINGS_INPROGRESS, false);
    }

    @Override // ru.mail.ui.fragments.settings.ActivityDestroyObservable
    public void notifyOnDestroyListeners() {
        Iterator<PreferenceManager.OnActivityDestroyListener> it = this.mDestroyListener.iterator();
        while (it.hasNext()) {
            it.next().onActivityDestroy();
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i10, int i11, Intent intent) {
        super.onActivityResult(i10, i11, intent);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        DynamicStringsInstallerHolder lazyFactoryHolder = DynamicStringsInstallerHolder.INSTANCE.getInstance();
        if (lazyFactoryHolder != null) {
            lazyFactoryHolder.install(this);
        }
        DarkThemeUtils.darkThemeWebViewHotFix(this, new WebViewUpdateDialogCreator.DialogCallback() { // from class: ru.mail.ui.fragments.settings.p
            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCreator.DialogCallback
            public final void dismissDialog() {
                this.f100250a.lambda$onCreate$0();
            }
        }, new Function1() { // from class: ru.mail.ui.fragments.settings.q
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f100264a.lambda$onCreate$1((AlertDialog) obj);
            }
        });
        new DarkThemeUtils(this).setResourceConfigurationUiFlag(this);
        super.onCreate(bundle);
        this.mDestroyListener = new ArrayList();
        setWindowContentOverlayCompat(this, findViewById(android.R.id.content));
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            configureToolbar(toolbar);
        }
        initStatusBar();
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        notifyOnDestroyListeners();
        super.onDestroy();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        onBackPressed();
        return true;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.webViewErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.webViewErrorDialog.dismiss();
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        super.onPreferenceTreeClick(preferenceScreen, preference);
        if (!(preference instanceof PreferenceScreen)) {
            return false;
        }
        initializeActionBar(((PreferenceScreen) preference).getDialog());
        return false;
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        initializeActionBar(getPreferenceScreen());
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Preference preferenceFindPreference = findPreference(KEY_PREF_DELETE_ACCOUNT);
        if (preferenceFindPreference != null) {
            preferenceFindPreference.notifyDependencyChange(false);
        }
        if (getPreferenceScreen() != null) {
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onStop() {
        if (getPreferenceScreen() != null) {
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
        super.onStop();
    }

    @Override // ru.mail.ui.fragments.settings.ActivityDestroyObservable
    public void registerOnDestroyListener(PreferenceManager.OnActivityDestroyListener onActivityDestroyListener) {
        this.mDestroyListener.add(onActivityDestroyListener);
    }

    protected void removePreference(String str, String str2) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(str);
        Preference preferenceFindPreference = findPreference(str2);
        if (preferenceFindPreference == null || preferenceCategory == null) {
            return;
        }
        preferenceCategory.removePreference(preferenceFindPreference);
    }

    protected void setAdmanListener() {
        Preference preferenceFindPreference = findPreference(KEY_PREF_ADMAN_SLOT);
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: ru.mail.ui.fragments.settings.BaseSettingsActivity.2
                @Override // android.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    MyTargetAdsManagerImpl.from(BaseSettingsActivity.this.getApplicationContext()).resetAds();
                    return true;
                }
            });
        }
    }

    protected void setRateAppListener() {
        Preference preferenceFindPreference = findPreference(KEY_PREF_RATE_APP);
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: ru.mail.ui.fragments.settings.BaseSettingsActivity.3
                @Override // android.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    RateTheAppDialog.newInstance(true).show(BaseSettingsActivity.this.getFragmentManager(), BaseSettingsActivity.TAG_RATE_THE_APP);
                    return true;
                }
            });
        }
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public boolean showSnackbar(@NotNull SnackbarParams snackbarParams) {
        SnackbarWrapper.in(getListView()).show(snackbarParams);
        return true;
    }

    @Override // ru.mail.ui.fragments.settings.ActivityDestroyObservable
    public void unregisterOnDestroyListener(PreferenceManager.OnActivityDestroyListener onActivityDestroyListener) {
        this.mDestroyListener.remove(onActivityDestroyListener);
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public void update(@NonNull SnackbarParams snackbarParams, @NonNull SnackbarParams snackbarParams2) {
        SnackbarWrapper.in(getListView()).update(snackbarParams, snackbarParams2);
    }

    public void initializeActionBar(final Dialog dialog) {
        if (dialog != null) {
            setWindowContentOverlayCompat(this, dialog.getWindow().findViewById(android.R.id.content));
            dialog.getActionBar().setDisplayHomeAsUpEnabled(true);
            View viewFindViewById = dialog.findViewById(android.R.id.home);
            if (viewFindViewById != null) {
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: ru.mail.ui.fragments.settings.BaseSettingsActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                };
                ViewParent parent = viewFindViewById.getParent();
                if (parent instanceof FrameLayout) {
                    ViewGroup viewGroup = (ViewGroup) parent.getParent();
                    if (viewGroup instanceof LinearLayout) {
                        viewGroup.setOnClickListener(onClickListener);
                        return;
                    } else {
                        ((FrameLayout) parent).setOnClickListener(onClickListener);
                        return;
                    }
                }
                viewFindViewById.setOnClickListener(onClickListener);
            }
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
    }
}
