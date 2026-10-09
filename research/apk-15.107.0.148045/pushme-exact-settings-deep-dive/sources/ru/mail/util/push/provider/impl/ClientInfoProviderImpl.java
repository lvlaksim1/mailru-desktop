package ru.mail.util.push.provider.impl;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.framework.common.BundleUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.SystemUtils;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.kotlett.runtime.divkit.InterpolatorFields;
import ru.mail.mailapp.R;
import ru.mail.qrcodescanner.presentation.QrScreenViewModel;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.push.provider.ClientInfoProvider;
import ru.mail.utils.GooglePlayServicesUtil;
import ru.mail.utils.MD5;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/util/push/provider/impl/ClientInfoProviderImpl;", "Lru/mail/util/push/provider/ClientInfoProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getClientInfo", "", "", "ClientInfo", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ClientInfoProviderImpl implements ClientInfoProvider {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J.\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lru/mail/util/push/provider/impl/ClientInfoProviderImpl$ClientInfo;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "platform", "", "version", "name", "info", "screen", "type", RbParams.Default.URL_PARAM_KEY_DEVICE, QrScreenViewModel.CAMERA, "phoneModule", DeviceInfo.PARAM_KEY_LANGUAGE, "distributor", "playServicesVersion", "simOperator", "createMap", "", "retrieveSimOperator", "getDeviceName", "getSimOperatorHash", "getPhoneType", "putIfExist", "", BlockParser.MAP_TYPE, "", "parameterKey", "parameterValue", "getFormattedAppVersion", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nClientInfoProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientInfoProviderImpl.kt\nru/mail/util/push/provider/impl/ClientInfoProviderImpl$ClientInfo\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,135:1\n434#2:136\n507#2,5:137\n*S KotlinDebug\n*F\n+ 1 ClientInfoProviderImpl.kt\nru/mail/util/push/provider/impl/ClientInfoProviderImpl$ClientInfo\n*L\n114#1:136\n114#1:137,5\n*E\n"})
    private static final class ClientInfo {

        @NotNull
        private static final String JSON_KEY_CONNECT_ID = "connectid";

        @NotNull
        private static final String JSON_KEY_EXTRA = "extra";

        @NotNull
        private static final String JSON_KEY_INFO = "info";

        @NotNull
        private static final String JSON_KEY_LANG = "lang";

        @NotNull
        private static final String JSON_KEY_NAME = "name";

        @NotNull
        private static final String JSON_KEY_PLATFORM = "platform";

        @NotNull
        private static final String JSON_KEY_PLAY_SERVICES_ID = "playservices";

        @NotNull
        private static final String JSON_KEY_TYPE = "type";

        @NotNull
        private static final String JSON_KEY_VERSION = "version";

        @NotNull
        private static final String JSON_VALUE_PHONE_TYPE_CDMA = "CDMA";

        @NotNull
        private static final String JSON_VALUE_PHONE_TYPE_DEFAULT = "NONE";

        @NotNull
        private static final String JSON_VALUE_PHONE_TYPE_GSM = "GSM";

        @NotNull
        private static final String JSON_VALUE_PHONE_TYPE_SIP = "SIP";

        @NotNull
        private static final String JSON_VALUE_TYPE_SMARTPHOTE = "Smartphone";

        @NotNull
        private static final String JSON_VALUE_TYPE_TABLET = "Tablet";

        @NotNull
        private final String camera;

        @NotNull
        private final String device;

        @NotNull
        private final String distributor;

        @NotNull
        private final String info;

        @NotNull
        private final String language;

        @NotNull
        private final String name;

        @NotNull
        private final String phoneModule;

        @NotNull
        private final String platform;

        @NotNull
        private final String playServicesVersion;

        @NotNull
        private final String screen;

        @Nullable
        private final String simOperator;

        @NotNull
        private final String type;

        @NotNull
        private final String version;

        public ClientInfo(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.platform = "Android " + Build.VERSION.RELEASE;
            this.version = getFormattedAppVersion(context);
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            this.name = packageName;
            String deviceName = getDeviceName();
            this.device = deviceName;
            String str = Camera.getNumberOfCameras() + " cameras";
            this.camera = str;
            String phoneType = getPhoneType(context);
            this.phoneModule = phoneType;
            String CURRENT_DISTRIBUTOR = Distributors.CURRENT_DISTRIBUTOR;
            Intrinsics.checkNotNullExpressionValue(CURRENT_DISTRIBUTOR, "CURRENT_DISTRIBUTOR");
            this.distributor = CURRENT_DISTRIBUTOR;
            this.simOperator = retrieveSimOperator(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f10 = displayMetrics.widthPixels;
            float f11 = displayMetrics.density;
            String str2 = (f10 / f11) + InterpolatorFields.Path.X + (displayMetrics.heightPixels / f11);
            this.screen = str2;
            Locale locale = Locale.getDefault();
            this.language = locale.getLanguage() + BundleUtil.UNDERLINE_TAG + locale.getCountry();
            this.info = deviceName + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + str + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + str2 + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + phoneType;
            this.playServicesVersion = String.valueOf(GooglePlayServicesUtil.getPlayServicesVersion(context));
            this.type = SystemUtils.isTablet(context, true) ? JSON_VALUE_TYPE_TABLET : JSON_VALUE_TYPE_SMARTPHOTE;
        }

        private final String getDeviceName() {
            String str = Build.MANUFACTURER;
            String str2 = Build.MODEL;
            Intrinsics.checkNotNull(str2);
            Intrinsics.checkNotNull(str);
            if (StringsKt.startsWith$default(str2, str, false, 2, (Object) null)) {
                return str2;
            }
            return str + StringUtils.SPACE + str2;
        }

        private final String getFormattedAppVersion(Context context) throws IOException {
            String string = context.getResources().getString(R.string.app_version);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            StringBuilder sb2 = new StringBuilder();
            int length = string.length();
            for (int i10 = 0; i10 < length; i10++) {
                char cCharAt = string.charAt(i10);
                if (Character.isDigit(cCharAt) || cCharAt == '.') {
                    sb2.append(cCharAt);
                }
            }
            return sb2.toString();
        }

        private final String getPhoneType(Context context) {
            if (!SystemUtils.hasTelephonyFeature(context)) {
                return "NONE";
            }
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            int phoneType = ((TelephonyManager) systemService).getPhoneType();
            if (phoneType == 1) {
                return JSON_VALUE_PHONE_TYPE_GSM;
            }
            if (phoneType != 2) {
                return phoneType != 3 ? "NONE" : JSON_VALUE_PHONE_TYPE_SIP;
            }
            return JSON_VALUE_PHONE_TYPE_CDMA;
        }

        private final String getSimOperatorHash() {
            return MD5.calcMD5(this.simOperator);
        }

        private final void putIfExist(Map<String, String> map, String parameterKey, String parameterValue) {
            if (parameterValue == null || StringsKt.isBlank(parameterValue)) {
                return;
            }
            map.put(parameterKey, parameterValue);
        }

        private final String retrieveSimOperator(Context context) {
            try {
                if (!SystemUtils.hasTelephonyFeature(context)) {
                    return null;
                }
                Object systemService = context.getSystemService("phone");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                return ((TelephonyManager) systemService).getSimOperator();
            } catch (IllegalStateException unused) {
                return null;
            }
        }

        @NotNull
        public final Map<String, String> createMap() {
            HashMap map = new HashMap();
            map.put("info", this.info);
            map.put("platform", this.platform);
            map.put("version", this.version);
            map.put("name", this.name);
            map.put("type", this.type);
            map.put("lang", this.language);
            map.put(JSON_KEY_EXTRA, this.distributor);
            putIfExist(map, "playservices", this.playServicesVersion);
            putIfExist(map, "connectid", getSimOperatorHash());
            return map;
        }
    }

    public ClientInfoProviderImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.util.push.provider.ClientInfoProvider
    @NotNull
    public Map<String, String> getClientInfo() {
        return new ClientInfo(this.context).createMap();
    }
}
