package ru.mail.rustoresdk;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkConnector;", "", "setUpper", "Lru/mail/rustoresdk/RuStoreSdkSetUpper;", "getSetUpper", "()Lru/mail/rustoresdk/RuStoreSdkSetUpper;", "vkpnsEventsListenerHolder", "Lru/mail/rustoresdk/VkpnsEventsListenerHolder;", "getVkpnsEventsListenerHolder", "()Lru/mail/rustoresdk/VkpnsEventsListenerHolder;", "pushSdkWrapper", "Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "getPushSdkWrapper", "()Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "testManager", "Lru/mail/rustoresdk/RuStoreSdkTestManager;", "getTestManager", "()Lru/mail/rustoresdk/RuStoreSdkTestManager;", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RuStoreSdkConnector {
    @NotNull
    RuStorePushSdkWrapper getPushSdkWrapper();

    @NotNull
    RuStoreSdkSetUpper getSetUpper();

    @NotNull
    RuStoreSdkTestManager getTestManager();

    @NotNull
    VkpnsEventsListenerHolder getVkpnsEventsListenerHolder();
}
