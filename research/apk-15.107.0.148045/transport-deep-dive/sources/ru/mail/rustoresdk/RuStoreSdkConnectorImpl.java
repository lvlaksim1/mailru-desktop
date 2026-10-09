package ru.mail.rustoresdk;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import ru.mail.rustoresdk.RuStoreSdkConnectorImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkConnectorImpl;", "Lru/mail/rustoresdk/RuStoreSdkConnector;", "<init>", "()V", "sdkWrapper", "Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "getSdkWrapper", "()Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "sdkWrapper$delegate", "Lkotlin/Lazy;", "setUpper", "Lru/mail/rustoresdk/RuStoreSdkSetUpperImpl;", "getSetUpper", "()Lru/mail/rustoresdk/RuStoreSdkSetUpperImpl;", "vkpnsEventsListenerHolder", "Lru/mail/rustoresdk/VkpnsEventsListenerHolderImpl;", "getVkpnsEventsListenerHolder", "()Lru/mail/rustoresdk/VkpnsEventsListenerHolderImpl;", "pushSdkWrapper", "getPushSdkWrapper", "testManager", "Lru/mail/rustoresdk/RuStoreSdkTestManager;", "getTestManager", "()Lru/mail/rustoresdk/RuStoreSdkTestManager;", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreSdkConnectorImpl implements RuStoreSdkConnector {

    /* JADX INFO: renamed from: sdkWrapper$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy sdkWrapper = LazyKt.lazy(new Function0() { // from class: bf.d
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return RuStoreSdkConnectorImpl.sdkWrapper_delegate$lambda$0();
        }
    });

    @NotNull
    private final RuStoreSdkSetUpperImpl setUpper = new RuStoreSdkSetUpperImpl();

    @NotNull
    private final VkpnsEventsListenerHolderImpl vkpnsEventsListenerHolder = VkpnsEventsListenerHolderImpl.INSTANCE;

    @NotNull
    private final RuStorePushSdkWrapper pushSdkWrapper = getSdkWrapper();

    @NotNull
    private final RuStoreSdkTestManager testManager = new RuStoreSdkTestManagerImpl();

    private final RuStorePushSdkWrapper getSdkWrapper() {
        return (RuStorePushSdkWrapper) this.sdkWrapper.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RuStorePushSdkWrapperImpl sdkWrapper_delegate$lambda$0() {
        return new RuStorePushSdkWrapperImpl();
    }

    @Override // ru.mail.rustoresdk.RuStoreSdkConnector
    @NotNull
    public RuStorePushSdkWrapper getPushSdkWrapper() {
        return this.pushSdkWrapper;
    }

    @Override // ru.mail.rustoresdk.RuStoreSdkConnector
    @NotNull
    public RuStoreSdkTestManager getTestManager() {
        return this.testManager;
    }

    @Override // ru.mail.rustoresdk.RuStoreSdkConnector
    @NotNull
    public RuStoreSdkSetUpperImpl getSetUpper() {
        return this.setUpper;
    }

    @Override // ru.mail.rustoresdk.RuStoreSdkConnector
    @NotNull
    public VkpnsEventsListenerHolderImpl getVkpnsEventsListenerHolder() {
        return this.vkpnsEventsListenerHolder;
    }
}
