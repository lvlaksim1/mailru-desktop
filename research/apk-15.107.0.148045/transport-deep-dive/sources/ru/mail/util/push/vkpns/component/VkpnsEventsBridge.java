package ru.mail.util.push.vkpns.component;

import android.content.Context;
import androidx.annotation.WorkerThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Transport;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.rustoresdk.VkpnsEventsListener;
import ru.mail.rustoresdk.VkpnsException;
import ru.mail.rustoresdk.message.VkpnsRemoteMessage;
import ru.mail.util.push.PushType;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0017J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0017J\b\u0010!\u001a\u00020\u001bH\u0016J\u0016\u0010\"\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lru/mail/util/push/vkpns/component/VkpnsEventsBridge;", "Lru/mail/rustoresdk/VkpnsEventsListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "getPushTokenRefreshedNotifier", "()Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "setPushTokenRefreshedNotifier", "(Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;)V", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "getPushMessageReceivedNotifier", "()Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "setPushMessageReceivedNotifier", "(Lru/mail/util/push/notifier/PushMessageReceivedNotifier;)V", "errorListener", "Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "getErrorListener", "()Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "setErrorListener", "(Lru/mail/util/push/vkpns/component/VkpnsErrorListener;)V", "onNewToken", "", "token", "", "onMessageReceived", "message", "Lru/mail/rustoresdk/message/VkpnsRemoteMessage;", "onDeletedMessages", BatchApiRequest.FIELD_NAME_ON_ERROR, VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/rustoresdk/VkpnsException;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsEventsBridge implements VkpnsEventsListener {
    public static final int $stable = 8;

    @NotNull
    private final MailAppAnalytics analytics;

    @Nullable
    private volatile VkpnsErrorListener errorListener;

    @Nullable
    private volatile PushMessageReceivedNotifier pushMessageReceivedNotifier;

    @Nullable
    private volatile PushTokenRefreshedNotifier pushTokenRefreshedNotifier;

    public VkpnsEventsBridge(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.analytics = MailAppDependencies.analytics(context);
    }

    @Nullable
    public final VkpnsErrorListener getErrorListener() {
        return this.errorListener;
    }

    @Nullable
    public final PushMessageReceivedNotifier getPushMessageReceivedNotifier() {
        return this.pushMessageReceivedNotifier;
    }

    @Nullable
    public final PushTokenRefreshedNotifier getPushTokenRefreshedNotifier() {
        return this.pushTokenRefreshedNotifier;
    }

    @Override // ru.mail.rustoresdk.VkpnsEventsListener
    public void onDeletedMessages() {
        this.analytics.onDeletedMessages();
    }

    @Override // ru.mail.rustoresdk.VkpnsEventsListener
    public void onError(@NotNull List<? extends VkpnsException> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        VkpnsErrorListener vkpnsErrorListener = this.errorListener;
        if (vkpnsErrorListener != null) {
            vkpnsErrorListener.onError(errors);
        }
    }

    @Override // ru.mail.rustoresdk.VkpnsEventsListener
    @WorkerThread
    public void onMessageReceived(@NotNull VkpnsRemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Long lOnMessageReceived = PushMeSdk.INSTANCE.onMessageReceived(message.getData(), Transport.VKPNS);
        PushMessageReceivedNotifier pushMessageReceivedNotifier = this.pushMessageReceivedNotifier;
        if (pushMessageReceivedNotifier != null) {
            pushMessageReceivedNotifier.onMessageReceived(message.getData(), PushType.VKPNS, null, lOnMessageReceived);
        }
    }

    @Override // ru.mail.rustoresdk.VkpnsEventsListener
    @WorkerThread
    public void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        PushMeSdk.INSTANCE.onNewToken(token, Transport.VKPNS);
        PushTokenRefreshedNotifier pushTokenRefreshedNotifier = this.pushTokenRefreshedNotifier;
        if (pushTokenRefreshedNotifier != null) {
            pushTokenRefreshedNotifier.onNewToken(token, PushType.VKPNS);
        }
    }

    public final void setErrorListener(@Nullable VkpnsErrorListener vkpnsErrorListener) {
        this.errorListener = vkpnsErrorListener;
    }

    public final void setPushMessageReceivedNotifier(@Nullable PushMessageReceivedNotifier pushMessageReceivedNotifier) {
        this.pushMessageReceivedNotifier = pushMessageReceivedNotifier;
    }

    public final void setPushTokenRefreshedNotifier(@Nullable PushTokenRefreshedNotifier pushTokenRefreshedNotifier) {
        this.pushTokenRefreshedNotifier = pushTokenRefreshedNotifier;
    }
}
