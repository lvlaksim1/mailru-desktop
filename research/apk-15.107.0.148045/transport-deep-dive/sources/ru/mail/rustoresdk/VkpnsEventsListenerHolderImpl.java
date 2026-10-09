package ru.mail.rustoresdk;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lru/mail/rustoresdk/VkpnsEventsListenerHolderImpl;", "Lru/mail/rustoresdk/VkpnsEventsListenerHolder;", "<init>", "()V", "value", "Lru/mail/rustoresdk/VkpnsEventsListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "getListener", "()Lru/mail/rustoresdk/VkpnsEventsListener;", "setListener", "", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsEventsListenerHolderImpl implements VkpnsEventsListenerHolder {

    @NotNull
    public static final VkpnsEventsListenerHolderImpl INSTANCE = new VkpnsEventsListenerHolderImpl();

    @Nullable
    private static volatile VkpnsEventsListener listener;

    private VkpnsEventsListenerHolderImpl() {
    }

    @Nullable
    public final VkpnsEventsListener getListener() {
        return listener;
    }

    @Override // ru.mail.rustoresdk.VkpnsEventsListenerHolder
    public void setListener(@NotNull VkpnsEventsListener listener2) {
        Intrinsics.checkNotNullParameter(listener2, "listener");
        listener = listener2;
    }
}
