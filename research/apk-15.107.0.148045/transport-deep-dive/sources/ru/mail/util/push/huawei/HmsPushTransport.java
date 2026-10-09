package ru.mail.util.push.huawei;

import android.content.Context;
import androidx.annotation.NonNull;
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
/* JADX INFO: loaded from: classes13.dex */
public class HmsPushTransport extends PushMessagesTransport {
    public HmsPushTransport(@NonNull Context context, @NonNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NonNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NonNull PushTokenManager pushTokenManager, @NonNull AvailabilityChecker availabilityChecker) {
        super(context, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, pushTokenManager, availabilityChecker);
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    protected PushFactory getPushFactory() {
        return PushFactoryCreatorKt.createPushFactory(getPushMessageType());
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    /* JADX INFO: renamed from: getPushKitWrapper */
    public PushKitWrapper getGcmPushKitWrapper() {
        return new HmsPushKitWrapper(getContext());
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    public PushType getPushMessageType() {
        return PushType.HMS;
    }
}
