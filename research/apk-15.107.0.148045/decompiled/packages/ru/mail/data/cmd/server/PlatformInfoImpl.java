package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.appsflyer.AppsFlyerLib;
import java.util.Collection;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.config.SegmentsProvider;
import ru.mail.dependencies.configuration.SegmentsModuleEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.experiment.LoginExperiment;
import ru.mail.serverapi.PlatformInfo;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.util.DeviceAge;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.connection.ConnectionClassManagerWrapper;
import ru.mail.util.connection.ConnectionClassQualityChecker;
import ru.mail.utils.lifecycle.ActivityLifecycleHandler;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Lru/mail/data/cmd/server/PlatformInfoImpl;", "Lru/mail/serverapi/PlatformInfo;", "context", "Landroid/content/Context;", "connectionClassQualityChecker", "Lru/mail/util/connection/ConnectionClassQualityChecker;", "<init>", "(Landroid/content/Context;Lru/mail/util/connection/ConnectionClassQualityChecker;)V", "segmentsProvider", "Lru/mail/config/SegmentsProvider;", "getSegmentsProvider", "()Lru/mail/config/SegmentsProvider;", "segmentsProvider$delegate", "Lkotlin/Lazy;", "getDeviceAge", "", "getConnectionQuality", "", "getCurrentDistributor", "getDarkThemeEnabled", "getFirstDistributor", "getAppsFlyerId", "isAppBackgrounded", "", "getBehaviorName", "getSegments", "", "getShortSegments", "appendLoginExperiment", "", "builder", "Landroid/net/Uri$Builder;", "getExistingLoginSuppressedOauth", "Ljava/util/regex/Pattern;", "getConfiguration", "Lru/mail/config/Configuration;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class PlatformInfoImpl implements PlatformInfo {
    public static final int $stable = 8;

    @NotNull
    private final ConnectionClassQualityChecker connectionClassQualityChecker;

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: segmentsProvider$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy segmentsProvider;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public PlatformInfoImpl(@NotNull Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final Configuration getConfiguration() {
        ConfigurationWithRawData configuration = ((ConfigurationRepository) Locator.INSTANCE.from(this.context.getApplicationContext()).locate(ConfigurationRepository.class)).getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        return configuration;
    }

    private final SegmentsProvider getSegmentsProvider() {
        return (SegmentsProvider) this.segmentsProvider.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SegmentsProvider segmentsProvider_delegate$lambda$0(PlatformInfoImpl platformInfoImpl) {
        return SegmentsModuleEntryPoint.INSTANCE.platformParmsSegmentsProvider(platformInfoImpl.context);
    }

    @Override // ru.mail.serverapi.PlatformInfo
    public void appendLoginExperiment(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        new LoginExperiment().addExperimentParam(this.context.getApplicationContext(), builder);
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @Nullable
    public String getAppsFlyerId() {
        return AppsFlyerLib.getInstance().getAppsFlyerUID(this.context.getApplicationContext());
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public String getBehaviorName() {
        return getConfiguration().getBehaviorName();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public String getConnectionQuality() {
        return this.connectionClassQualityChecker.getCurrentBandwidthQuality(this.context).name();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public String getCurrentDistributor() {
        String CURRENT_DISTRIBUTOR = Distributors.CURRENT_DISTRIBUTOR;
        Intrinsics.checkNotNullExpressionValue(CURRENT_DISTRIBUTOR, "CURRENT_DISTRIBUTOR");
        return CURRENT_DISTRIBUTOR;
    }

    @Override // ru.mail.serverapi.PlatformInfo
    public int getDarkThemeEnabled() {
        return DarkThemeUtils.INSTANCE.isNightModeEnabled(this.context) ? 1 : 0;
    }

    @Override // ru.mail.serverapi.PlatformInfo
    public int getDeviceAge() {
        return DeviceAge.getAge();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public Pattern getExistingLoginSuppressedOauth() {
        return getConfiguration().getExistingLoginSuppressedOauth();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @Nullable
    public String getFirstDistributor() {
        return Distributors.getFirstDistributor(this.context.getApplicationContext()).getName();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public Collection<String> getSegments() {
        return getSegmentsProvider().getSegments().values();
    }

    @Override // ru.mail.serverapi.PlatformInfo
    @NotNull
    public Collection<String> getShortSegments() {
        return StringsKt.split$default((CharSequence) getSegmentsProvider().getShortSegments(), new String[]{","}, false, 0, 6, (Object) null);
    }

    @Override // ru.mail.serverapi.PlatformInfo
    public boolean isAppBackgrounded() {
        return ((ActivityLifecycleHandler) Locator.INSTANCE.from(this.context).locate(ActivityLifecycleHandler.class)).isAppBackgrounded();
    }

    @JvmOverloads
    public PlatformInfoImpl(@NotNull Context context, @NotNull ConnectionClassQualityChecker connectionClassQualityChecker) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(connectionClassQualityChecker, "connectionClassQualityChecker");
        this.context = context;
        this.connectionClassQualityChecker = connectionClassQualityChecker;
        this.segmentsProvider = LazyKt.lazy(new Function0() { // from class: ru.mail.data.cmd.server.q
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlatformInfoImpl.segmentsProvider_delegate$lambda$0(this.f85219a);
            }
        });
    }

    public /* synthetic */ PlatformInfoImpl(Context context, ConnectionClassQualityChecker connectionClassQualityChecker, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i10 & 2) != 0 ? ConnectionClassManagerWrapper.INSTANCE : connectionClassQualityChecker);
    }
}
