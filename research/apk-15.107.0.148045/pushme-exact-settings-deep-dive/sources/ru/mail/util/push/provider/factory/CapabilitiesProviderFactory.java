package ru.mail.util.push.provider.factory;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.util.push.provider.CapabilitiesProvider;
import ru.mail.util.push.provider.impl.MailCapabilitiesProvider;
import ru.mail.util.push.provider.impl.PortalAppCapabilitiesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\u0005¨\u0006\r"}, d2 = {"Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;", "", "<init>", "()V", "createProviderForMailApp", "Lru/mail/util/push/provider/CapabilitiesProvider;", "needPushMsg", "", "accessor", "Lru/mail/logic/pushfilters/FilterAccessor;", "enabledReminderPush", "(ZLru/mail/logic/pushfilters/FilterAccessor;Ljava/lang/Boolean;)Lru/mail/util/push/provider/CapabilitiesProvider;", "createProviderForPortalApps", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CapabilitiesProviderFactory {
    public static final int $stable = 0;

    @NotNull
    public static final CapabilitiesProviderFactory INSTANCE = new CapabilitiesProviderFactory();

    private CapabilitiesProviderFactory() {
    }

    @NotNull
    public final CapabilitiesProvider createProviderForMailApp(boolean needPushMsg, @NotNull FilterAccessor accessor, @Nullable Boolean enabledReminderPush) {
        Intrinsics.checkNotNullParameter(accessor, "accessor");
        return new MailCapabilitiesProvider(needPushMsg, accessor, enabledReminderPush);
    }

    @NotNull
    public final CapabilitiesProvider createProviderForPortalApps() {
        return new PortalAppCapabilitiesProvider();
    }
}
