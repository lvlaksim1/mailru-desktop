package ru.mail.util.push.provider.impl;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.commonid.CommonIdProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.log.Log;
import ru.mail.util.push.provider.DeviceIdProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/util/push/provider/impl/DeviceIdProviderImpl;", "Lru/mail/util/push/provider/DeviceIdProvider;", "context", "Landroid/content/Context;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "<init>", "(Landroid/content/Context;Lru/mail/deviceinfo/DeviceIdProvider;)V", "getDeviceId", "", "getAndroidId", "getSdkDeviceId", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeviceIdProviderImpl implements DeviceIdProvider {

    @NotNull
    private final Context context;

    @NotNull
    private final ru.mail.deviceinfo.DeviceIdProvider deviceIdProvider;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("DeviceIdProviderImpl");

    public DeviceIdProviderImpl(@NotNull Context context, @NotNull ru.mail.deviceinfo.DeviceIdProvider deviceIdProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        this.context = context;
        this.deviceIdProvider = deviceIdProvider;
    }

    @Override // ru.mail.util.push.provider.DeviceIdProvider
    @NotNull
    public String getAndroidId() {
        return this.deviceIdProvider.getAndroidId();
    }

    @Override // ru.mail.util.push.provider.DeviceIdProvider
    @NotNull
    public String getDeviceId() {
        return this.deviceIdProvider.getDeviceId();
    }

    @Override // ru.mail.util.push.provider.DeviceIdProvider
    @NotNull
    public String getSdkDeviceId() {
        try {
            return CommonIdProvider.Companion.getCommonIdGenerated$default(CommonIdProvider.INSTANCE, this.context, null, 2, null);
        } catch (Exception e10) {
            LOG.e("Failed to get SDK device ID", e10);
            return "";
        }
    }
}
