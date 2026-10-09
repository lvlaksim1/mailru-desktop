package ru.mail.network.utils.device.deviceid;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import androidx.preference.PreferenceManager;
import java.security.NoSuchAlgorithmException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.network.utils.device.deviceid.DefaultDeviceIdProvider;
import ru.mail.network.utils.device.deviceid.accountprovider.GoogleAccountProvider;
import ru.mail.network.utils.sign.MD5;
import ru.mail.network.utils.utils.RandomStringGenerator;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u000fH\u0016J \u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u000fH\u0002J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lru/mail/network/utils/device/deviceid/DefaultDeviceIdProvider;", "Lru/mail/network/utils/device/deviceid/DeviceIdProvider;", "context", "Landroid/content/Context;", "baseLogger", "Lru/mail/util/log/Logger;", "googleAccountsProvider", "Lru/mail/network/utils/device/deviceid/accountprovider/GoogleAccountProvider;", "<init>", "(Landroid/content/Context;Lru/mail/util/log/Logger;Lru/mail/network/utils/device/deviceid/accountprovider/GoogleAccountProvider;)V", "logger", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "androidIdValue", "", "getAndroidIdValue", "()Ljava/lang/String;", "androidIdValue$delegate", "Lkotlin/Lazy;", "getDeviceId", "getUdid", "getAndroidId", "generateDeviceId", "androidId", "buildSerial", "googleAccountPrimary", "generateUdid", "getBuildSerial", "Companion", "network-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"HardwareIds"})
public final class DefaultDeviceIdProvider implements DeviceIdProvider {

    @NotNull
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";

    @NotNull
    private static final String UDID_PREF = "udid_pref";

    /* JADX INFO: renamed from: androidIdValue$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy androidIdValue;

    @NotNull
    private final GoogleAccountProvider googleAccountsProvider;

    @NotNull
    private final Logger logger;
    private final SharedPreferences sharedPreferences;

    public DefaultDeviceIdProvider(@NotNull final Context context, @NotNull Logger baseLogger, @NotNull GoogleAccountProvider googleAccountsProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        Intrinsics.checkNotNullParameter(googleAccountsProvider, "googleAccountsProvider");
        this.googleAccountsProvider = googleAccountsProvider;
        this.logger = baseLogger.createLogger("DefaultDeviceIdProvider");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.sharedPreferences = defaultSharedPreferences;
        this.androidIdValue = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: zd.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultDeviceIdProvider.androidIdValue_delegate$lambda$0(context, this);
            }
        });
        if (getUdid().length() == 0) {
            defaultSharedPreferences.edit().putString(UDID_PREF, generateUdid()).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String androidIdValue_delegate$lambda$0(Context context, DefaultDeviceIdProvider defaultDeviceIdProvider) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), RbParams.Default.URL_PARAM_KEY_ANDROID_ID);
            return string == null ? "" : string;
        } catch (Throwable th2) {
            Logger logger = defaultDeviceIdProvider.logger;
            String message = th2.getMessage();
            if (message == null) {
                message = "";
            }
            logger.e(message, th2);
            return "";
        }
    }

    private final String generateDeviceId(String androidId, String buildSerial, String googleAccountPrimary) {
        try {
            String strDigestHex = MD5.INSTANCE.digestHex(androidId + buildSerial + googleAccountPrimary);
            Logger.i$default(this.logger, "device Id = " + strDigestHex, null, 2, null);
            return strDigestHex;
        } catch (NoSuchAlgorithmException e10) {
            e10.printStackTrace();
            return "INVALID_DEVICE_ID";
        }
    }

    private final String generateUdid() {
        return RandomStringGenerator.INSTANCE.generateHexString(32);
    }

    private final String getAndroidIdValue() {
        return (String) this.androidIdValue.getValue();
    }

    private final String getBuildSerial() {
        if (Build.VERSION.SDK_INT > 26) {
            return "unknown";
        }
        String str = Build.SERIAL;
        Intrinsics.checkNotNull(str);
        return str;
    }

    @Override // ru.mail.network.utils.device.deviceid.DeviceIdProvider
    @NotNull
    public String getAndroidId() {
        return getAndroidIdValue();
    }

    @Override // ru.mail.network.utils.device.deviceid.DeviceIdProvider
    @NotNull
    public String getDeviceId() {
        Account account = (Account) ArraysKt.firstOrNull(this.googleAccountsProvider.getGetGoogleAccounts().invoke());
        String str = account != null ? account.name : null;
        if (str == null) {
            str = "";
        }
        return generateDeviceId(getAndroidId(), getBuildSerial(), str);
    }

    @Override // ru.mail.network.utils.device.deviceid.DeviceIdProvider
    @NotNull
    public String getUdid() {
        String string = this.sharedPreferences.getString(UDID_PREF, "");
        return string == null ? "" : string;
    }
}
