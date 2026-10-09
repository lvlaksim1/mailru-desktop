package ru.mail.deviceinfo;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import java.security.NoSuchAlgorithmException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.util.log.Log;
import ru.mail.utils.MD5;
import ru.mail.utils.RandomStringGenerator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J \u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011H\u0002J\b\u0010\u001d\u001a\u00020\u0011H\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lru/mail/deviceinfo/DefaultDeviceIdProvider;", "Lru/mail/deviceinfo/DeviceIdProvider;", "context", "Landroid/content/Context;", "getGoogleAccounts", "Lkotlin/Function0;", "", "Landroid/accounts/Account;", "<init>", "(Landroid/content/Context;Lkotlin/jvm/functions/Function0;)V", "accountManager", "Landroid/accounts/AccountManager;", "(Landroid/content/Context;Landroid/accounts/AccountManager;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "androidIdValue", "", "getAndroidIdValue", "()Ljava/lang/String;", "androidIdValue$delegate", "Lkotlin/Lazy;", "getDeviceId", "getUdid", "getAndroidId", "generateDeviceId", "androidId", "buildSerial", "googleAccountPrimary", "generateUdid", "getBuildSerial", "Companion", "network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
    private final Function0<Account[]> getGoogleAccounts;
    private final SharedPreferences sharedPreferences;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("DefaultDeviceIdProvider");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/deviceinfo/DefaultDeviceIdProvider$Companion;", "", "<init>", "()V", "GOOGLE_ACCOUNT_TYPE", "", "UDID_PREF", "LOG", "Lru/mail/util/log/Log;", "getGoogleAccounts", "", "Landroid/accounts/Account;", "accountManager", "Landroid/accounts/AccountManager;", "(Landroid/accounts/AccountManager;)[Landroid/accounts/Account;", "network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Account[] getGoogleAccounts(AccountManager accountManager) {
            try {
                Account[] accountsByType = accountManager.getAccountsByType("com.google");
                Intrinsics.checkNotNull(accountsByType);
                return accountsByType;
            } catch (Throwable th2) {
                DefaultDeviceIdProvider.LOG.e(th2.getMessage(), th2);
                return new Account[0];
            }
        }

        private Companion() {
        }
    }

    public DefaultDeviceIdProvider(@NotNull final Context context, @NotNull Function0<Account[]> getGoogleAccounts) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(getGoogleAccounts, "getGoogleAccounts");
        this.getGoogleAccounts = getGoogleAccounts;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.sharedPreferences = defaultSharedPreferences;
        this.androidIdValue = LazyKt.lazy(new Function0() { // from class: ru.mail.deviceinfo.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultDeviceIdProvider.androidIdValue_delegate$lambda$0(context);
            }
        });
        if (getUdid().length() == 0) {
            defaultSharedPreferences.edit().putString(UDID_PREF, generateUdid()).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Account[] _init_$lambda$0(AccountManager accountManager) {
        return INSTANCE.getGoogleAccounts(accountManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String androidIdValue_delegate$lambda$0(Context context) {
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), RbParams.Default.URL_PARAM_KEY_ANDROID_ID);
            return string == null ? "" : string;
        } catch (Throwable th2) {
            LOG.e(th2.getMessage(), th2);
            return "";
        }
    }

    private final String generateDeviceId(String androidId, String buildSerial, String googleAccountPrimary) {
        try {
            String strDigest_hex = MD5.digest_hex(androidId + buildSerial + googleAccountPrimary);
            Intrinsics.checkNotNull(strDigest_hex);
            return strDigest_hex;
        } catch (NoSuchAlgorithmException e10) {
            LOG.d("Exception occurred while getting device id", e10);
            return "INVALID_DEVICE_ID";
        }
    }

    private final String generateUdid() {
        String strGenerateHexString = RandomStringGenerator.generateHexString(32);
        Intrinsics.checkNotNullExpressionValue(strGenerateHexString, "generateHexString(...)");
        return strGenerateHexString;
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

    @Override // ru.mail.deviceinfo.DeviceIdProvider
    @NotNull
    public String getAndroidId() {
        return getAndroidIdValue();
    }

    @Override // ru.mail.deviceinfo.DeviceIdProvider
    @NotNull
    public String getDeviceId() {
        Account account = (Account) ArraysKt.firstOrNull(this.getGoogleAccounts.invoke());
        String str = account != null ? account.name : null;
        if (str == null) {
            str = "";
        }
        return generateDeviceId(getAndroidId(), getBuildSerial(), str);
    }

    @Override // ru.mail.deviceinfo.DeviceIdProvider
    @NotNull
    public String getUdid() {
        String string = this.sharedPreferences.getString(UDID_PREF, "");
        return string == null ? "" : string;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultDeviceIdProvider(@NotNull Context context, @NotNull final AccountManager accountManager) {
        this(context, (Function0<Account[]>) new Function0() { // from class: ru.mail.deviceinfo.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultDeviceIdProvider._init_$lambda$0(accountManager);
            }
        });
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
    }
}
