package ru.mail.util.push.vkpns;

import android.content.Context;
import android.content.pm.PackageInfo;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.sdk.Utils;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.config.section.VkpnsHostSdkDto;
import ru.mail.util.log.Log;
import ru.mail.utils.RuStoreUtil;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010\u0011\u001a\u00020\u000bH\u0002J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u001c"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsHostResolver;", "", "context", "Landroid/content/Context;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "vkpnsHostConfig", "Lru/mail/config/section/VkpnsHostSdkDto;", "<init>", "(Landroid/content/Context;Lru/mail/config/section/RuStoreSdkDto;Lru/mail/config/section/VkpnsHostSdkDto;)V", "preferredHost", "Lru/mail/util/push/vkpns/VkpnsHostResolver$HostInfo;", "getPreferredHost", "()Lru/mail/util/push/vkpns/VkpnsHostResolver$HostInfo;", "preferredHost$delegate", "Lkotlin/Lazy;", "getPreferredVkpnsHost", "autoDetectHost", "getRuStoreAppInfo", "getOwnAppInfo", "getVersionCode", "", "packageName", "", "hasBackgroundPermission", "", "HostInfo", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsHostResolver {

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: preferredHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy preferredHost;

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;

    @NotNull
    private final VkpnsHostSdkDto vkpnsHostConfig;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkpnsHostResolver");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsHostResolver$HostInfo;", "", "packageName", "", "pubKey", "versionCode", "", "hasBackgroundPermission", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;IZ)V", "getPackageName", "()Ljava/lang/String;", "getPubKey", "getVersionCode", "()I", "getHasBackgroundPermission", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class HostInfo {
        public static final int $stable = 0;
        private final boolean hasBackgroundPermission;

        @NotNull
        private final String packageName;

        @NotNull
        private final String pubKey;
        private final int versionCode;

        public HostInfo(@NotNull String packageName, @NotNull String pubKey, int i10, boolean z10) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(pubKey, "pubKey");
            this.packageName = packageName;
            this.pubKey = pubKey;
            this.versionCode = i10;
            this.hasBackgroundPermission = z10;
        }

        public static /* synthetic */ HostInfo copy$default(HostInfo hostInfo, String str, String str2, int i10, boolean z10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = hostInfo.packageName;
            }
            if ((i11 & 2) != 0) {
                str2 = hostInfo.pubKey;
            }
            if ((i11 & 4) != 0) {
                i10 = hostInfo.versionCode;
            }
            if ((i11 & 8) != 0) {
                z10 = hostInfo.hasBackgroundPermission;
            }
            return hostInfo.copy(str, str2, i10, z10);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPubKey() {
            return this.pubKey;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getVersionCode() {
            return this.versionCode;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getHasBackgroundPermission() {
            return this.hasBackgroundPermission;
        }

        @NotNull
        public final HostInfo copy(@NotNull String packageName, @NotNull String pubKey, int versionCode, boolean hasBackgroundPermission) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(pubKey, "pubKey");
            return new HostInfo(packageName, pubKey, versionCode, hasBackgroundPermission);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HostInfo)) {
                return false;
            }
            HostInfo hostInfo = (HostInfo) other;
            return Intrinsics.areEqual(this.packageName, hostInfo.packageName) && Intrinsics.areEqual(this.pubKey, hostInfo.pubKey) && this.versionCode == hostInfo.versionCode && this.hasBackgroundPermission == hostInfo.hasBackgroundPermission;
        }

        public final boolean getHasBackgroundPermission() {
            return this.hasBackgroundPermission;
        }

        @NotNull
        public final String getPackageName() {
            return this.packageName;
        }

        @NotNull
        public final String getPubKey() {
            return this.pubKey;
        }

        public final int getVersionCode() {
            return this.versionCode;
        }

        public int hashCode() {
            return (((((this.packageName.hashCode() * 31) + this.pubKey.hashCode()) * 31) + Integer.hashCode(this.versionCode)) * 31) + Boolean.hashCode(this.hasBackgroundPermission);
        }

        @NotNull
        public String toString() {
            return "HostInfo(packageName=" + this.packageName + ", pubKey=" + this.pubKey + ", versionCode=" + this.versionCode + ", hasBackgroundPermission=" + this.hasBackgroundPermission + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RuStoreSdkDto.PreferredHost.values().length];
            try {
                iArr[RuStoreSdkDto.PreferredHost.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RuStoreSdkDto.PreferredHost.FORCE_RUSTORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RuStoreSdkDto.PreferredHost.FORCE_MAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[RuStoreSdkDto.PreferredHost.AUTO_SDK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VkpnsHostResolver(@NotNull Context context, @NotNull RuStoreSdkDto ruStoreConfig, @NotNull VkpnsHostSdkDto vkpnsHostConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        Intrinsics.checkNotNullParameter(vkpnsHostConfig, "vkpnsHostConfig");
        this.context = context;
        this.ruStoreConfig = ruStoreConfig;
        this.vkpnsHostConfig = vkpnsHostConfig;
        this.preferredHost = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.vkpns.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f101207a.getPreferredVkpnsHost();
            }
        });
    }

    private final HostInfo autoDetectHost() {
        Log log = LOG;
        log.i("Auto detect host for VKPNS...");
        if (!this.vkpnsHostConfig.isEnabled()) {
            log.i("VKPNS host SDK is disabled in config so choosing RuStore as host");
            return getRuStoreAppInfo();
        }
        if (!RuStoreUtil.isRuStoreInstalled$default(this.context, null, 2, null)) {
            log.i("RuStore is not installed, using current app as a host");
            return getOwnAppInfo();
        }
        if (RuStoreUtil.isRuStoreHasBackgroundPermission(this.context)) {
            log.i("Using RuStore as a host");
            return getRuStoreAppInfo();
        }
        log.i("RuStore has not permission, using current app as a host");
        return getOwnAppInfo();
    }

    private final HostInfo getOwnAppInfo() {
        LOG.i("Getting current app info");
        String packageName = this.context.getPackageName();
        String[] certificateFingerprint = Utils.getCertificateFingerprint(this.context, packageName, Utils.DigestAlgorithm.SHA256);
        Intrinsics.checkNotNullExpressionValue(certificateFingerprint, "getCertificateFingerprint(...)");
        String str = (String) ArraysKt.firstOrNull(certificateFingerprint);
        if (str == null) {
            str = "";
        }
        Intrinsics.checkNotNull(packageName);
        return new HostInfo(packageName, str, getVersionCode(packageName), hasBackgroundPermission(packageName));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HostInfo getPreferredVkpnsHost() {
        RuStoreSdkDto.PreferredHost preferredHost = this.ruStoreConfig.toPreferredHost();
        Log log = LOG;
        log.i("Getting preferred host for VKPNS...Value from config: " + preferredHost);
        int i10 = WhenMappings.$EnumSwitchMapping$0[preferredHost.ordinal()];
        if (i10 == 1) {
            return autoDetectHost();
        }
        if (i10 == 2) {
            return getRuStoreAppInfo();
        }
        if (i10 == 3) {
            return getOwnAppInfo();
        }
        if (i10 != 4) {
            throw new NoWhenBranchMatchedException();
        }
        log.i("The host selection is inside the VKPNS SDK so preferred host is unknown to us");
        return null;
    }

    private final HostInfo getRuStoreAppInfo() {
        LOG.i("Getting RuStore app info");
        return new HostInfo("ru.vk.store", RuStoreUtil.PUB_KEY, getVersionCode("ru.vk.store"), hasBackgroundPermission("ru.vk.store"));
    }

    private final int getVersionCode(String packageName) {
        PackageInfo packageInfoPerform = PackageManagerUtil.from(this.context).getPackageInfo(packageName, 0).onErrorReturn(null).perform();
        if (packageInfoPerform != null) {
            return packageInfoPerform.versionCode;
        }
        return 0;
    }

    private final boolean hasBackgroundPermission(String packageName) {
        return UtilExtensionsKt.isIgnoringBatteryOptimizations(this.context, packageName);
    }

    @Nullable
    public final HostInfo getPreferredHost() {
        return (HostInfo) this.preferredHost.getValue();
    }
}
