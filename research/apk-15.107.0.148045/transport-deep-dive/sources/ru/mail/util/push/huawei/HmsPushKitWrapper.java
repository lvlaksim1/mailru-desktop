package ru.mail.util.push.huawei;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.agconnect.AGConnectOptionsBuilder;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.PushKitWrapper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/util/push/huawei/HmsPushKitWrapper;", "Lru/mail/util/push/PushKitWrapper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "availabilityChecker", "Lru/mail/util/push/huawei/HMSAvailabilityChecker;", "getPushTokenFromPushKit", "", "deleteToken", "", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "getHms", "Lcom/huawei/hms/aaid/HmsInstanceId;", "getAppId", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HmsPushKitWrapper implements PushKitWrapper {

    @NotNull
    private final HMSAvailabilityChecker availabilityChecker;

    @NotNull
    private final Context context;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("HmsPushKitWrapper");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/util/push/huawei/HmsPushKitWrapper$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public HmsPushKitWrapper(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.availabilityChecker = new HMSAvailabilityChecker(context);
    }

    private final String getAppId() {
        String string = new AGConnectOptionsBuilder().build(this.context).getString("client/app_id");
        if (string == null || string.length() == 0) {
            LOG.w("app_id is null or empty");
            return string;
        }
        LOG.d("app_id is not null or empty");
        return string;
    }

    private final HmsInstanceId getHms() {
        HmsInstanceId hmsInstanceId = HmsInstanceId.getInstance(this.context);
        Intrinsics.checkNotNullExpressionValue(hmsInstanceId, "getInstance(...)");
        return hmsInstanceId;
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        return this.availabilityChecker.checkForAvailability();
    }

    @Override // ru.mail.util.push.PushKitWrapper
    public void deleteToken() throws ApiException {
        getHms().deleteToken(getAppId(), HmsMessaging.DEFAULT_TOKEN_SCOPE);
    }

    @Override // ru.mail.util.push.PushKitWrapper
    @Nullable
    public String getPushTokenFromPushKit() throws ApiException {
        String token = getHms().getToken(getAppId(), HmsMessaging.DEFAULT_TOKEN_SCOPE);
        if (token == null || token.length() == 0) {
            LOG.w("token is null or empty");
            return token;
        }
        LOG.d("token is not null or empty");
        return token;
    }
}
