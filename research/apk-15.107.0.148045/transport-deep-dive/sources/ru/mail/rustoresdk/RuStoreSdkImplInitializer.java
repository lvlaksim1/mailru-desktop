package ru.mail.rustoresdk;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.utils.feature.FunctionalityProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkImplInitializer;", "", "<init>", "()V", "init", "", "featureProvider", "Lru/mail/utils/feature/FunctionalityProvider;", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreSdkImplInitializer {

    @NotNull
    public static final RuStoreSdkImplInitializer INSTANCE = new RuStoreSdkImplInitializer();

    private RuStoreSdkImplInitializer() {
    }

    @JvmStatic
    public static final void init(@NotNull FunctionalityProvider featureProvider) {
        Intrinsics.checkNotNullParameter(featureProvider, "featureProvider");
        featureProvider.register(RuStoreSdkConnector.class, new RuStoreSdkConnectorImpl());
    }
}
