package ru.mail.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.vkpns.VkpnsHostResolver;
import ru.mail.util.push.vkpns.component.VkpnsComponent;
import ru.mail.util.push.vkpns.component.VkpnsErrorListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lru/mail/push/VkpnsComponentStub;", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "setPushTokenListener", "", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "setMessagesListener", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "setErrorListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "getPushKitWrapper", "Lru/mail/util/push/PushKitWrapper;", "getHostResolver", "Lru/mail/util/push/vkpns/VkpnsHostResolver;", "isShowPushEnabled", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsComponentStub implements VkpnsComponent {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    public VkpnsComponentStub(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    @NotNull
    public VkpnsHostResolver getHostResolver() {
        Context context = this.context;
        ConfigModuleEntryPoint.Companion companion = ConfigModuleEntryPoint.INSTANCE;
        return new VkpnsHostResolver(context, companion.provideRuStoreConfig(context), companion.provideVkPnsHostConfig(this.context));
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    @NotNull
    /* JADX INFO: renamed from: getPushKitWrapper */
    public PushKitWrapper mo15888getPushKitWrapper() {
        return new PushKitWrapper() { // from class: ru.mail.push.VkpnsComponentStub.getPushKitWrapper.1
            @Override // ru.mail.util.push.AvailabilityChecker
            public AvailabilityCheckResult checkForAvailability() {
                return new AvailabilityCheckResult() { // from class: ru.mail.push.VkpnsComponentStub$getPushKitWrapper$1$checkForAvailability$1
                    @Override // ru.mail.util.push.AvailabilityCheckResult
                    /* JADX INFO: renamed from: isAvailable */
                    public boolean getIsAvailable() {
                        return false;
                    }

                    @Override // ru.mail.util.push.AvailabilityCheckResult
                    public boolean isUserRecoverable() {
                        return false;
                    }

                    @Override // ru.mail.util.push.AvailabilityCheckResult
                    public void showUserRecoveryNotification(Context context) {
                        Intrinsics.checkNotNullParameter(context, "context");
                    }
                };
            }

            @Override // ru.mail.util.push.PushKitWrapper
            public String getPushTokenFromPushKit() {
                return "";
            }

            @Override // ru.mail.util.push.PushKitWrapper
            public void deleteToken() {
            }
        };
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public boolean isShowPushEnabled() {
        return false;
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setErrorListener(@NotNull VkpnsErrorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setMessagesListener(@NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier) {
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setPushTokenListener(@NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier) {
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
    }
}
