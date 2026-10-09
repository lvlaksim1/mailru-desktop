package com.vk.pushme.util.provider.impl;

import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.huawei.hms.framework.common.BundleUtil;
import com.vk.pushme.util.Extensions;
import com.vk.pushme.util.GooglePlayServicesUtil;
import com.vk.pushme.util.MD5;
import com.vk.pushme.util.SystemUtils;
import com.vk.pushme.util.provider.ClientInfoProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.kotlett.runtime.divkit.InterpolatorFields;
import ru.mail.qrcodescanner.presentation.QrScreenViewModel;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tH\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/util/provider/impl/ClientInfoProviderImpl;", "Lcom/vk/pushme/util/provider/ClientInfoProvider;", "context", "Landroid/content/Context;", "appVersionFromClient", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "getClientInfo", "", "getClientTimeZone", "getGmtOffsetString", "offsetMillis", "", "ClientInfo", "Companion", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nClientInfoProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientInfoProviderImpl.kt\ncom/vk/pushme/util/provider/impl/ClientInfoProviderImpl\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,165:1\n434#2:166\n507#2,5:167\n*S KotlinDebug\n*F\n+ 1 ClientInfoProviderImpl.kt\ncom/vk/pushme/util/provider/impl/ClientInfoProviderImpl\n*L\n27#1:166\n27#1:167,5\n*E\n"})
public final class ClientInfoProviderImpl implements ClientInfoProvider {
    private static final int MINUTES_IN_HOUR = 60;

    @Nullable
    private final String appVersionFromClient;

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0005\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J.\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001d2\u0006\u0010\u001e\u001a\u00020\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0002J\u001c\u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/vk/pushme/util/provider/impl/ClientInfoProviderImpl$ClientInfo;", "", "context", "Landroid/content/Context;", "appVersionFromClient", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "platform", "version", "name", "info", "screen", "type", RbParams.Default.URL_PARAM_KEY_DEVICE, QrScreenViewModel.CAMERA, "phoneModule", DeviceInfo.PARAM_KEY_LANGUAGE, "playServicesVersion", "simOperator", "createMap", "", "retrieveSimOperator", "getDeviceName", "getSimOperatorHash", "getPhoneType", "putIfExist", "", BlockParser.MAP_TYPE, "", "parameterKey", "parameterValue", "getFormattedAppVersion", "Companion", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nClientInfoProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientInfoProviderImpl.kt\ncom/vk/pushme/util/provider/impl/ClientInfoProviderImpl$ClientInfo\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,165:1\n1#2:166\n434#3:167\n507#3,5:168\n*S KotlinDebug\n*F\n+ 1 ClientInfoProviderImpl.kt\ncom/vk/pushme/util/provider/impl/ClientInfoProviderImpl$ClientInfo\n*L\n141#1:167\n141#1:168,5\n*E\n"})
    private static final class ClientInfo {

        @NotNull
        private static final String JSON_KEY_CONNECT_ID = "connectid";

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

        @Nullable
        private final String version;

        public ClientInfo(@NotNull Context context, @Nullable String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.platform = "Android " + Build.VERSION.RELEASE;
            this.version = getFormattedAppVersion(context, str);
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            this.name = packageName;
            String deviceName = getDeviceName();
            this.device = deviceName;
            String str2 = Camera.getNumberOfCameras() + " cameras";
            this.camera = str2;
            String phoneType = getPhoneType(context);
            this.phoneModule = phoneType;
            this.simOperator = retrieveSimOperator(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f10 = displayMetrics.widthPixels;
            float f11 = displayMetrics.density;
            String str3 = (f10 / f11) + InterpolatorFields.Path.X + (displayMetrics.heightPixels / f11);
            this.screen = str3;
            Locale locale = Locale.getDefault();
            this.language = locale.getLanguage() + BundleUtil.UNDERLINE_TAG + locale.getCountry();
            this.info = deviceName + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + str2 + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + str3 + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + phoneType;
            this.playServicesVersion = String.valueOf(GooglePlayServicesUtil.INSTANCE.getPlayServicesVersion(context));
            this.type = SystemUtils.INSTANCE.isTablet(context) ? JSON_VALUE_TYPE_TABLET : JSON_VALUE_TYPE_SMARTPHOTE;
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

        private final String getFormattedAppVersion(Context context, String appVersionFromClient) throws IOException {
            if (appVersionFromClient == null) {
                appVersionFromClient = Extensions.INSTANCE.getVersionName(context);
            }
            if (appVersionFromClient != null) {
                StringBuilder sb2 = new StringBuilder();
                int length = appVersionFromClient.length();
                for (int i10 = 0; i10 < length; i10++) {
                    char cCharAt = appVersionFromClient.charAt(i10);
                    if (Character.isDigit(cCharAt) || cCharAt == '.') {
                        sb2.append(cCharAt);
                    }
                }
                String string = sb2.toString();
                if (string != null && !StringsKt.isBlank(string)) {
                    return string;
                }
            }
            return null;
        }

        private final String getPhoneType(Context context) {
            if (!SystemUtils.INSTANCE.hasTelephonyFeature(context)) {
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
                if (!SystemUtils.INSTANCE.hasTelephonyFeature(context)) {
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
            String str = this.version;
            if (str != null) {
                map.put("version", str);
            }
            map.put("name", this.name);
            map.put("type", this.type);
            map.put("lang", this.language);
            putIfExist(map, "playservices", this.playServicesVersion);
            putIfExist(map, "connectid", getSimOperatorHash());
            return map;
        }
    }

    public ClientInfoProviderImpl(@NotNull Context context, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.appVersionFromClient = str;
    }

    private final String getGmtOffsetString(int offsetMillis) {
        char c10;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(offsetMillis);
        if (minutes < 0) {
            minutes = -minutes;
            c10 = Soundex.SILENT_MARKER;
        } else {
            c10 = '+';
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.US, "GMT%c%02d:%02d", Arrays.copyOf(new Object[]{Character.valueOf(c10), Long.valueOf(TimeUnit.MINUTES.toHours(minutes)), Long.valueOf(minutes % ((long) 60))}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // com.vk.pushme.util.provider.ClientInfoProvider
    @NotNull
    public Map<String, String> getClientInfo() {
        return new ClientInfo(this.context, this.appVersionFromClient).createMap();
    }

    @Override // com.vk.pushme.util.provider.ClientInfoProvider
    @NotNull
    public String getClientTimeZone() throws IOException {
        String gmtOffsetString = getGmtOffsetString(TimeZone.getDefault().getRawOffset());
        StringBuilder sb2 = new StringBuilder();
        int length = gmtOffsetString.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = gmtOffsetString.charAt(i10);
            if (cCharAt != ':') {
                sb2.append(cCharAt);
            }
        }
        return sb2.toString();
    }
}
