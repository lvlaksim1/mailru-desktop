package ru.mail.util.push.component;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.pusher.PusherTransport;
import ru.mail.util.push.vkpns.component.VkpnsComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u001a\u001a\u00020\u0019H$J\b\u0010\u001b\u001a\u00020\u0019H\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lru/mail/util/push/component/BasePushComponent;", "Lru/mail/util/push/component/PushComponent;", "context", "Landroid/content/Context;", "isPushMeSdkInitEnabled", "", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "pushMessagesTransports", "", "Lru/mail/util/push/PushMessagesTransport;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "vkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "<init>", "(Landroid/content/Context;ZLru/mail/config/section/RuStoreSdkDto;Ljava/util/Collection;Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/util/push/vkpns/component/VkpnsComponent;)V", "getContext", "()Landroid/content/Context;", "()Z", "getRuStoreConfig", "()Lru/mail/config/section/RuStoreSdkDto;", "pusherTransport", "Lru/mail/util/push/pusher/PusherTransport;", "createPusherTransport", "getPusherTransport", "getPushMessagesTransports", "getPushMessageReceivedNotifier", "getPushTokenRefreshedNotifier", "getVkpnsComponent", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BasePushComponent implements PushComponent {
    public static final int $stable = 8;

    @NotNull
    private final Context context;
    private final boolean isPushMeSdkInitEnabled;

    @NotNull
    private final PushMessageReceivedNotifier pushMessageReceivedNotifier;

    @NotNull
    private final Collection<PushMessagesTransport> pushMessagesTransports;

    @NotNull
    private final PushTokenRefreshedNotifier pushTokenRefreshedNotifier;

    @NotNull
    private PusherTransport pusherTransport;

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;

    @NotNull
    private final VkpnsComponent vkpnsComponent;

    /* JADX WARN: Multi-variable type inference failed */
    public BasePushComponent(@NotNull Context context, boolean z10, @NotNull RuStoreSdkDto ruStoreConfig, @NotNull Collection<? extends PushMessagesTransport> pushMessagesTransports, @NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull VkpnsComponent vkpnsComponent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        Intrinsics.checkNotNullParameter(pushMessagesTransports, "pushMessagesTransports");
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(vkpnsComponent, "vkpnsComponent");
        this.context = context;
        this.isPushMeSdkInitEnabled = z10;
        this.ruStoreConfig = ruStoreConfig;
        this.pushMessagesTransports = pushMessagesTransports;
        this.pushTokenRefreshedNotifier = pushTokenRefreshedNotifier;
        this.pushMessageReceivedNotifier = pushMessageReceivedNotifier;
        this.vkpnsComponent = vkpnsComponent;
        this.pusherTransport = createPusherTransport();
        vkpnsComponent.setPushTokenListener(pushTokenRefreshedNotifier);
        vkpnsComponent.setMessagesListener(pushMessageReceivedNotifier);
    }

    @NotNull
    protected abstract PusherTransport createPusherTransport();

    @NotNull
    protected final Context getContext() {
        return this.context;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public PushMessageReceivedNotifier getPushMessageReceivedNotifier() {
        return this.pushMessageReceivedNotifier;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public Collection<PushMessagesTransport> getPushMessagesTransports() {
        return this.pushMessagesTransports;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public PushTokenRefreshedNotifier getPushTokenRefreshedNotifier() {
        return this.pushTokenRefreshedNotifier;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public PusherTransport getPusherTransport() {
        return this.pusherTransport;
    }

    @NotNull
    protected final RuStoreSdkDto getRuStoreConfig() {
        return this.ruStoreConfig;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public VkpnsComponent getVkpnsComponent() {
        return this.vkpnsComponent;
    }

    /* JADX INFO: renamed from: isPushMeSdkInitEnabled, reason: from getter */
    protected final boolean getIsPushMeSdkInitEnabled() {
        return this.isPushMeSdkInitEnabled;
    }
}
