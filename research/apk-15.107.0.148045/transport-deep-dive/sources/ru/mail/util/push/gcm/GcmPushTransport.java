package ru.mail.util.push.gcm;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.ConfigurationRepository;
import ru.mail.dependecies.MailUtilsModuleEntryPoint;
import ru.mail.libverify.api.VerificationFactory;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.util.push.PushFactory;
import ru.mail.util.push.PushFactoryCreatorKt;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.token.PushTokenManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J5\u0010\u0014\u001a\u00020\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u000fH\u0016J\b\u0010 \u001a\u00020\u000fH\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lru/mail/util/push/gcm/GcmPushTransport;", "Lru/mail/util/push/PushMessagesTransport;", "context", "Landroid/content/Context;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "pushTokenManager", "Lru/mail/util/push/token/PushTokenManager;", "availabilityChecker", "Lru/mail/util/push/AvailabilityChecker;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/util/push/token/PushTokenManager;Lru/mail/util/push/AvailabilityChecker;)V", "gcmPushKitWrapper", "Lru/mail/util/push/PushKitWrapper;", "onNewToken", "", "token", "", "onMessageReceived", "data", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;)V", "getPushMessageType", "Lru/mail/util/push/PushType;", "getPushFactory", "Lru/mail/util/push/PushFactory;", "getPushKitWrapper", "createPushKitWrapper", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GcmPushTransport extends PushMessagesTransport {

    @NotNull
    private final PushKitWrapper gcmPushKitWrapper;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GcmPushTransport");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GcmPushTransport(@NotNull Context context, @NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull PushTokenManager pushTokenManager, @NotNull AvailabilityChecker availabilityChecker) {
        super(context, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, pushTokenManager, availabilityChecker);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(pushTokenManager, "pushTokenManager");
        Intrinsics.checkNotNullParameter(availabilityChecker, "availabilityChecker");
        this.gcmPushKitWrapper = createPushKitWrapper();
    }

    private final PushKitWrapper createPushKitWrapper() {
        if (BaseSettingsActivity.isUseDevPushes(getContext())) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            return new GcmDevPushKitWrapper(context);
        }
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        Context context3 = getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        GCMAvailabilityChecker gCMAvailabilityChecker = new GCMAvailabilityChecker(context3);
        MailUtilsModuleEntryPoint.Companion companion = MailUtilsModuleEntryPoint.INSTANCE;
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        return new GcmPushKitWrapper(context2, gCMAvailabilityChecker, companion.firebaseInfoProvider(context4));
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    public PushFactory getPushFactory() {
        return PushFactoryCreatorKt.createPushFactory(getPushMessageType());
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    /* JADX INFO: renamed from: getPushKitWrapper, reason: from getter */
    public PushKitWrapper getGcmPushKitWrapper() {
        return this.gcmPushKitWrapper;
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    public PushType getPushMessageType() {
        return PushType.GCM;
    }

    @Override // ru.mail.util.push.PushMessagesTransport, ru.mail.util.push.notifier.PushMessageReceivedNotifier.Listener
    public void onMessageReceived(@NotNull Map<String, String> data, @Nullable String from, @Nullable Long pushMeSdkPushId) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (ConfigurationRepository.from(getContext()).getConfiguration().isLibverifyPushesPassEnabled()) {
            Log log = LOG;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("message delivered to libverify from %s with data %s", Arrays.copyOf(new Object[]{from, data}, 2));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            log.d(str);
            MailAppDependencies.analytics(getContext()).sendLibVerifyAnalytic();
            VerificationFactory.deliverGcmMessageIntent(getContext(), from, data);
        }
        super.onMessageReceived(data, from, pushMeSdkPushId);
    }

    @Override // ru.mail.util.push.PushMessagesTransport, ru.mail.util.push.notifier.PushTokenRefreshedNotifier.Listener
    public void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        super.onNewToken(token);
        VerificationFactory.refreshGcmToken(getContext());
    }
}
