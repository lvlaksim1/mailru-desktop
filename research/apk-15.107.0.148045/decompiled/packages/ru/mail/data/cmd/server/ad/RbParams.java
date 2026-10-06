package ru.mail.data.cmd.server.ad;

import android.accounts.Account;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.MyTargetPrivacy;
import com.my.target.common.MyTargetUtils;
import com.my.target.common.MyTargetVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import ru.mail.ads.config.api.data.model.AdRemoteConfig;
import ru.mail.ads.core.api.di.AdCoreApiEntryPoint;
import ru.mail.android_utils.SystemUtils;
import ru.mail.android_utils.WebViewUtils;
import ru.mail.auth.Authenticator;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.command.R;
import ru.mail.data.cmd.server.GoogleAdvertisingInfo;
import ru.mail.data.cmd.server.HuaweiAdvertisingInfo;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.deviceinfo.DeviceInfoFactory;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlParamUtils;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.ui.cloud.utils.CloudUtils;
import ru.mail.util.DaysOfUsageCounter;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.analytics.Partnership;
import ru.mail.util.network_state.NetworkStateReceiver;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class RbParams extends ServerCommandBaseParams {
    public static final String KEY_PREF_AD_RB_SLOT = "ad_rb_slot";

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes9.dex */
    public static class Stub extends RbParams {
        public Stub(Context context) {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Default extends RbParams {
        private static final Lazy<Set<String>> ALL_URL_PARAMS = LazyKt.lazy(new Function0() { // from class: ru.mail.data.cmd.server.ad.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RbParams.Default.getAllUrlParams();
            }
        });
        private static final String REGEXP = "(\\d+\\.\\d+\\.\\d+)\\.(\\d+)\\s*(alpha)?";
        public static final String TEST_FORCE_ALL_RB_PIXELS = "statisticsV2tps";
        public static final String TEST_NEW_RB_PIXELS = "statisticsV2";
        public static final String URL_FIELD_PREFIX = "URL_PARAM_";
        public static final String URL_PARAM_ADVERTISING_ID = "advertising_id";
        public static final String URL_PARAM_ADVERTISING_TRACKING_ENABLED = "advertising_tracking_enabled";
        public static final String URL_PARAM_APPSFLYER_REQUEST_KEY_PUBNATIVE = "install_compaign_id";
        public static final String URL_PARAM_CUSTOM_USER_ID = "custom_user_id";
        public static final String URL_PARAM_EXCLUDE = "exb";
        public static final String URL_PARAM_HUAWEI_ADVERTISING_ID = "oaid";
        public static final String URL_PARAM_HUAWEI_ADVERTISING_TRACKING_ENABLED = "oaid_tracking_enabled";
        public static final String URL_PARAM_IAB_CONSENT = "iab_user_consent";
        public static final String URL_PARAM_KEY_ANDROID_ID = "android_id";
        public static final String URL_PARAM_KEY_APP_BUILD = "appbuild";
        public static final String URL_PARAM_KEY_APP_LANG = "app_lang";
        public static final String URL_PARAM_KEY_APP_PACKAGE = "app";
        public static final String URL_PARAM_KEY_APP_VERSION = "appver";
        public static final String URL_PARAM_KEY_BANNERS_STAT_COUNT = "banners_stat";
        public static final String URL_PARAM_KEY_CLOUD_SINGLE_QUOTA_SIZE = "cloud_quota_segment";
        public static final String URL_PARAM_KEY_CONNECTION = "connection";
        public static final String URL_PARAM_KEY_CURRENT_TIME = "currentTime";
        public static final String URL_PARAM_KEY_DAYS_INSTALLED = "days_installed";
        public static final String URL_PARAM_KEY_DENSITY = "density";
        public static final String URL_PARAM_KEY_DEVICE = "device";
        public static final String URL_PARAM_KEY_DEVICE_ID = "deviceId";
        public static final String URL_PARAM_KEY_EMAIL = "email";
        public static final String URL_PARAM_KEY_EUNAME = "euname";
        public static final String URL_PARAM_KEY_HANDLE_DATA = "handle_data";
        public static final String URL_PARAM_KEY_HEIGHT = "h";
        public static final String URL_PARAM_KEY_ISO_CODE = "isoCode";
        public static final String URL_PARAM_KEY_LANG = "lang";
        public static final String URL_PARAM_KEY_LOCALIZED_MODEL = "localizedModel";
        public static final String URL_PARAM_KEY_MANUFACTURE = "manufacture";
        public static final String URL_PARAM_KEY_MEMORY_MAX = "memoryMax";
        public static final String URL_PARAM_KEY_MEMORY_USE = "memoryUse";
        public static final String URL_PARAM_KEY_MT_SDK_VERSION = "sdk_ver_int";
        public static final String URL_PARAM_KEY_MT_SDK_VERSION_ADMAN = "adman_ver";
        public static final String URL_PARAM_KEY_MYTRACKER_ID = "mtr_id";
        public static final String URL_PARAM_KEY_OPERATOR_ID = "operator_id";
        public static final String URL_PARAM_KEY_OS = "os";
        public static final String URL_PARAM_KEY_OS_VERSION = "osver";
        public static final String URL_PARAM_KEY_PARALLAX = "parallax";
        public static final String URL_PARAM_KEY_PARTNERSHIP = "partnership";
        public static final String URL_PARAM_KEY_PLATFORM = "platform";
        public static final String URL_PARAM_KEY_RB_BANNER = "rb_banner";
        public static final String URL_PARAM_KEY_RB_BIG = "rb_big";
        public static final String URL_PARAM_KEY_RB_BIG_IMAGE = "rb_big_image";
        public static final String URL_PARAM_KEY_RB_EXPANDABLE = "rb_expandable";
        public static final String URL_PARAM_KEY_RB_HTML5 = "rb_html5";
        public static final String URL_PARAM_KEY_RB_HTML_200H = "rb_html_200h";
        public static final String URL_PARAM_KEY_RB_HTML_300H = "rb_html_300h";
        public static final String URL_PARAM_KEY_RB_HTML_PROMO = "rb_html_promo";
        public static final String URL_PARAM_KEY_RB_MULTIFORMAT = "rb_multiformat";
        public static final String URL_PARAM_KEY_RB_WIDE_IMAGE = "rb_wide_image";
        public static final String URL_PARAM_KEY_SESSION_CURRENT = "sessionscurrent";
        public static final String URL_PARAM_KEY_SESSION_TOTAL = "sessionstotal";
        public static final String URL_PARAM_KEY_SIM_LOCALE = "sim_loc";
        public static final String URL_PARAM_KEY_SIM_OPERATOR_ID = "sim_operator_id";
        public static final String URL_PARAM_KEY_SIM_OPERATOR_NAME = "operator_name";
        public static final String URL_PARAM_KEY_SLOT = "slot";
        public static final String URL_PARAM_KEY_STUBS_COUNT = "stubs";
        public static final String URL_PARAM_KEY_SYSTEM_THEME = "dkm";
        public static final String URL_PARAM_KEY_TIME_ZONE = "timezone";
        public static final String URL_PARAM_KEY_VERSION = "version";
        public static final String URL_PARAM_KEY_WEBVIEW_VESION = "swvv";
        public static final String URL_PARAM_KEY_WIDTH = "w";

        @Param(getterName = "getAccounts", method = HttpMethod.GET, name = "email", useGetter = true)
        private final Account[] accounts;

        @Param(method = HttpMethod.GET, name = "connection")
        private final String connection;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MEMORY_USE)
        private final Long currentHeapSize;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_CURRENT_TIME)
        private final Long currentTime;

        @Param(getterName = "getCustomUserId", method = HttpMethod.GET, name = URL_PARAM_CUSTOM_USER_ID, useGetter = true)
        private final String customUserId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_DENSITY)
        private final Float density;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_DEVICE)
        private final String device;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_DEVICE_ID)
        private final String deviceId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MEMORY_MAX)
        private final Long heapSize;

        @Param(method = HttpMethod.GET, name = "h")
        private final Integer height;

        @Param(getterName = "getCompainIdQueryValue", method = HttpMethod.GET, name = URL_PARAM_APPSFLYER_REQUEST_KEY_PUBNATIVE, useGetter = true)
        private final String installCompaignId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_ISO_CODE)
        private final String isoCode;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MT_SDK_VERSION_ADMAN)
        private final String mAdmanVersion;

        @Param(method = HttpMethod.GET, type = Param.Type.COMPLEX_OBJECT)
        private Map<String, String> mAdsCustomParams;

        @Param(method = HttpMethod.GET, name = URL_PARAM_ADVERTISING_ID)
        private final String mAdvertisingId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_ADVERTISING_TRACKING_ENABLED)
        private final String mAdvertisingTracking;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_ANDROID_ID)
        private final String mAndroidId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_APP_LANG)
        private final String mAppLang;

        @Param(method = HttpMethod.GET, name = "app")
        private final String mAppPackage;
        private final String mAppVersion;

        @Param(method = HttpMethod.GET, name = "appbuild")
        private final String mBuildNumber;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_CLOUD_SINGLE_QUOTA_SIZE)
        private final String mCloudSingleQuotaSize;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SYSTEM_THEME)
        private final Integer mDarkMode;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_DAYS_INSTALLED)
        private final Long mDaysInstalled;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_EUNAME)
        private final String mDeviceName;

        @Param(method = HttpMethod.GET, name = URL_PARAM_EXCLUDE)
        private String mExcludeBanners;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_HANDLE_DATA)
        private Integer mHandleData;

        @Param(method = HttpMethod.GET, name = "oaid")
        private final String mHuaweiAdvertisingId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_HUAWEI_ADVERTISING_TRACKING_ENABLED)
        private final String mHuaweiAdvertisingTracking;

        @Param(method = HttpMethod.GET, name = URL_PARAM_IAB_CONSENT)
        private final Integer mIABConsent;

        @Param(method = HttpMethod.GET, name = "lang")
        private final String mLang;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SIM_LOCALE)
        private final String mLocale;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MT_SDK_VERSION)
        private final String mMtVersion;

        @Param(method = HttpMethod.GET, type = Param.Type.COMPLEX_OBJECT)
        private Map<String, String> mMyTargetInfo;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MYTRACKER_ID)
        private final String mMyTrackerId;

        @Param(method = HttpMethod.GET, name = "os")
        private final String mOs;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_OS_VERSION)
        private final String mOsVersion;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_PARALLAX)
        private Integer mParallax;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_PARTNERSHIP)
        private final String mPartnership;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_BANNER)
        private Integer mRbBanner;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_BIG)
        private Integer mRbBig;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_BIG_IMAGE)
        private Integer mRbBigImage;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_EXPANDABLE)
        private Integer mRbExpandable;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_HTML_200H)
        private Integer mRbHtml200h;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_HTML_300H)
        private Integer mRbHtml300h;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_HTML5)
        private Integer mRbHtml5;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_HTML_PROMO)
        private Integer mRbHtmlPromo;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_MULTIFORMAT)
        private Integer mRbMultiformat;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_RB_WIDE_IMAGE)
        private Integer mRbWideImage;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SESSION_CURRENT)
        private final String mSessionCurrent;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SESSION_TOTAL)
        private final String mSessionTotal;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_OPERATOR_ID)
        private final String mSimOperator;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SIM_OPERATOR_ID)
        private final String mSimOperatorId;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_SIM_OPERATOR_NAME)
        private final String mSimOperatorName;

        @Param(method = HttpMethod.URL, name = URL_PARAM_KEY_SLOT)
        @NotNull
        private final String mSlot;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_STUBS_COUNT)
        private Integer mStubsCount;

        @Param(method = HttpMethod.GET, name = TEST_FORCE_ALL_RB_PIXELS)
        public Integer mTestForceAllRbPixels;

        @Param(method = HttpMethod.GET, name = TEST_NEW_RB_PIXELS)
        public Integer mTestNewRbPixels;

        @Param(method = HttpMethod.GET, name = "timezone")
        private final String mTimezone;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_MANUFACTURE)
        private final String mVendor;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_APP_VERSION)
        private final String mVersionNumber;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_WEBVIEW_VESION)
        private final Integer mWebViewVersion;

        @Param(method = HttpMethod.GET, name = URL_PARAM_KEY_LOCALIZED_MODEL)
        private final String phoneModel;

        @Param(method = HttpMethod.GET, name = "platform")
        private final String platform;

        @Param(method = HttpMethod.GET, name = "version")
        private final String version;

        @Param(method = HttpMethod.GET, name = "w")
        private final Integer width;

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes9.dex */
        private static class BuildNumberHolder {
            private final String mBuildNumber;
            private final String mVersionNumber;

            private BuildNumberHolder(String str, String str2) {
                this.mBuildNumber = str;
                this.mVersionNumber = str2;
            }
        }

        public Default(Context context, @NonNull String str, @Nullable String str2, @NonNull Map<String, String> map) {
            this(context, str, Authenticator.getAccountManagerWrapper(context).getAppAccounts(), str2, map);
        }

        private Map<String, String> filterDuplicated(Map<String, String> map) {
            Set<String> value = ALL_URL_PARAMS.getValue();
            HashMap map2 = new HashMap(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!value.contains(entry.getKey())) {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
            return map2;
        }

        public static Default from(Context context, List<Long> list, String str, Map<String, String> map) {
            return new Default(context, str, (list == null || list.isEmpty()) ? null : TextUtils.join(",", list), map);
        }

        public static Default fromSlot(Context context, String str, Map<String, String> map) {
            return new Default(context, str, null, map);
        }

        public static String getAdRbSlot(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(RbParams.KEY_PREF_AD_RB_SLOT, BuildConfigVariablesHolder.pubNativeSlot);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public static Set<String> getAllUrlParams() {
            HashSet hashSet = new HashSet();
            hashSet.addAll(PreferenceHostProvider.urlParamsNames());
            hashSet.addAll(ServerCommandBaseParams.urlParamsNames());
            hashSet.addAll(UrlParamUtils.getUrlParamNamesForClass(Default.class, URL_FIELD_PREFIX));
            return hashSet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private BuildNumberHolder getBuildNumber() {
            String str = this.mAppVersion;
            if (str == null) {
                throw new IllegalArgumentException("mAppVersion not initialized!!!");
            }
            List<String> matchResults = getMatchResults(str);
            return matchResults.size() >= 2 ? new BuildNumberHolder(matchResults.get(1), matchResults.get(0)) : new BuildNumberHolder(0 == true ? 1 : 0, 0 == true ? 1 : 0);
        }

        private String getCloudQuotaStorageSize(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getString(CloudUtils.PREF_CLOUD_QUOTA_STORAGE_SIZE, "none");
        }

        private String getConnection(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return NetworkStateReceiver.NetworkState.NONE.toString();
            }
            return activeNetworkInfo.getType() == 1 ? NetworkStateReceiver.NetworkState.WIFI.toString() : NetworkStateReceiver.NetworkState.MOBILE.toString();
        }

        public static List<String> getMatchResults(String str) {
            Matcher matcher = Pattern.compile(REGEXP).matcher(str);
            ArrayList arrayList = new ArrayList();
            while (matcher.find()) {
                try {
                    arrayList.add(matcher.group(1));
                    arrayList.add(matcher.group(2));
                    arrayList.add(matcher.group(3));
                } catch (RuntimeException unused) {
                }
            }
            return arrayList;
        }

        private int getScreenHeight(Context context) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }

        private int getScreenWidth(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }

        @Nullable
        private String retrieveOperatorName(Context context) {
            try {
                if (SystemUtils.hasTelephonyFeature(context)) {
                    return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
                }
                return null;
            } catch (IllegalStateException unused) {
                return null;
            }
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Default r10 = (Default) obj;
            return Objects.equals(this.mSlot, r10.mSlot) && Objects.equals(this.mParallax, r10.mParallax) && Objects.equals(this.mRbBanner, r10.mRbBanner) && Objects.equals(this.mRbMultiformat, r10.mRbMultiformat) && Objects.equals(this.mRbBig, r10.mRbBig) && Objects.equals(this.mRbBigImage, r10.mRbBigImage) && Objects.equals(this.mRbWideImage, r10.mRbWideImage) && Objects.equals(this.mRbExpandable, r10.mRbExpandable) && Objects.equals(this.mRbHtml200h, r10.mRbHtml200h) && Objects.equals(this.mRbHtml300h, r10.mRbHtml300h) && Objects.equals(this.mRbHtml5, r10.mRbHtml5) && Objects.equals(this.mRbHtmlPromo, r10.mRbHtmlPromo);
        }

        public String getAccounts() {
            StringBuilder sb2 = new StringBuilder();
            for (Account account : this.accounts) {
                sb2.append(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER);
                sb2.append(account.name);
            }
            return sb2.toString().replaceFirst(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, "");
        }

        public String getCompainIdQueryValue() {
            if (TextUtils.isEmpty(this.installCompaignId)) {
                return null;
            }
            return this.installCompaignId;
        }

        public String getCustomUserId() {
            return this.customUserId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mParallax, this.mRbBanner, this.mRbMultiformat, this.mRbBig, this.mRbBigImage, this.mRbWideImage, this.mRbExpandable, this.mRbHtml200h, this.mRbHtml300h, this.mRbHtml5, this.mRbHtmlPromo);
        }

        public Default withSchemeParams(boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, int i10) {
            this.mHandleData = z10 ? 1 : null;
            this.mParallax = z11 ? 1 : null;
            this.mRbBanner = z12 ? 1 : null;
            this.mRbMultiformat = z13 ? 1 : null;
            this.mRbBig = z14 ? 1 : null;
            this.mRbBigImage = z15 ? 1 : null;
            this.mRbWideImage = z16 ? 1 : null;
            this.mRbExpandable = z17 ? 1 : null;
            this.mRbHtml200h = z18 ? 1 : null;
            this.mRbHtml300h = z19 ? 1 : null;
            this.mRbHtml5 = z20 ? 1 : null;
            this.mRbHtmlPromo = z21 ? 1 : null;
            this.mStubsCount = Integer.valueOf(i10);
            return this;
        }

        public Default(Context context, @NonNull String str, @NonNull Account[] accountArr, @Nullable String str2, @NonNull Map<String, String> map) {
            String str3 = Build.MODEL;
            this.mDeviceName = str3;
            DataManager dataManager = (DataManager) Locator.from(context).locate(CommonDataManager.class);
            DeviceIdProvider deviceIdProvider = DeviceInfoEntryPoint.deviceIdProvider(context);
            DeviceInfoFactory deviceInfoFactory = DeviceInfoEntryPoint.deviceInfoFactory(context);
            this.installCompaignId = Distributors.getLocalPubNativeId();
            this.mSlot = str;
            this.heapSize = Long.valueOf(Runtime.getRuntime().maxMemory());
            DeviceInfo deviceInfo = deviceInfoFactory.getDeviceInfo();
            this.version = deviceInfo.getAppVersion();
            this.currentHeapSize = Long.valueOf(Runtime.getRuntime().totalMemory());
            this.phoneModel = str3;
            this.density = Float.valueOf(context.getResources().getDisplayMetrics().density);
            this.platform = String.format("Android %s", Build.VERSION.RELEASE);
            if (SystemUtils.hasTelephonyFeature(context)) {
                this.isoCode = ((TelephonyManager) context.getSystemService("phone")).getSimCountryIso();
            } else {
                this.isoCode = "unknown";
            }
            this.deviceId = deviceInfo.getId();
            this.device = deviceInfo.getDeviceName();
            this.currentTime = Long.valueOf(System.currentTimeMillis());
            this.connection = getConnection(context);
            this.width = Integer.valueOf(getScreenWidth(context));
            this.height = Integer.valueOf(getScreenHeight(context));
            this.accounts = (Account[]) Arrays.copyOf(accountArr, accountArr.length);
            this.customUserId = dataManager.getActiveLogin();
            this.mSimOperatorName = retrieveOperatorName(context);
            this.mAppPackage = context.getPackageName();
            this.mAndroidId = deviceIdProvider.getAndroidId();
            this.mAdvertisingId = GoogleAdvertisingInfo.getAdvertisingId(context);
            this.mAdvertisingTracking = GoogleAdvertisingInfo.isAdsEnabled(context);
            this.mHuaweiAdvertisingId = HuaweiAdvertisingInfo.getAdvertisingId(context);
            this.mHuaweiAdvertisingTracking = HuaweiAdvertisingInfo.isAdsEnabled(context);
            this.mAppVersion = context.getPackageName() + context.getResources().getString(R.string.app_version);
            this.mOs = deviceInfo.getOs();
            this.mAppLang = deviceInfo.getLanguage();
            String simOperator = deviceInfo.getSimOperator();
            this.mSimOperator = simOperator;
            this.mOsVersion = deviceInfo.getOsVersion();
            this.mLang = Locale.getDefault().getLanguage();
            this.mTimezone = deviceInfo.getTimeZone();
            this.mLocale = deviceInfo.getLanguage();
            this.mSessionCurrent = String.valueOf(DaysOfUsageCounter.getRunsCountAfterUpdate(context));
            this.mSessionTotal = String.valueOf(DaysOfUsageCounter.getRunsCountIgnoreUpdate(context));
            this.mVendor = deviceInfo.getVendor();
            this.mSimOperatorId = simOperator;
            BuildNumberHolder buildNumber = getBuildNumber();
            this.mBuildNumber = buildNumber.mBuildNumber;
            this.mVersionNumber = buildNumber.mVersionNumber;
            this.mAdmanVersion = MyTargetVersion.VERSION;
            this.mMtVersion = MyTargetVersion.VERSION_INT;
            this.mDarkMode = Integer.valueOf(DarkThemeUtils.isNightModeEnabled(context) ? 1 : 0);
            this.mWebViewVersion = Integer.valueOf(WebViewUtils.getWebViewVersionCode(-1));
            this.mMyTrackerId = context.getResources().getString(ru.mail.mails.R.string.adman_install_tracker_app_id);
            if (AdCoreApiEntryPoint.INSTANCE.adConfiguration(context).getConsent().getShowStrategy() != AdRemoteConfig.Consent.ShowStrategy.need_to_show || MyTargetPrivacy.currentPrivacy().iabUserConsent == null) {
                this.mIABConsent = null;
            } else {
                this.mIABConsent = Integer.valueOf(MyTargetPrivacy.currentPrivacy().iabUserConsent.booleanValue() ? 1 : 0);
            }
            this.mCloudSingleQuotaSize = getCloudQuotaStorageSize(context);
            Partnership partnership = Partnership.INSTANCE;
            String partnership2 = partnership.getPartnership(context);
            if (TextUtils.isEmpty(partnership2)) {
                this.mPartnership = null;
                this.mDaysInstalled = null;
            } else {
                this.mPartnership = partnership2;
                this.mDaysInstalled = Long.valueOf(partnership.getDaysInstalled(context));
            }
            this.mExcludeBanners = str2;
            this.mMyTargetInfo = filterDuplicated(MyTargetUtils.collectInfo(context));
            this.mAdsCustomParams = filterDuplicated(map);
        }
    }
}
