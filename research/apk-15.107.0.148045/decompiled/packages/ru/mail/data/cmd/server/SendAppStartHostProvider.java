package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.setup.action.AppStartTriggerStartup;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/data/cmd/server/SendAppStartHostProvider;", "Lru/mail/serverapi/MailHostProvider;", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendAppStartHostProvider extends MailHostProvider {
    public static final int $stable = 8;

    @NotNull
    private final Context appContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendAppStartHostProvider(@NotNull Context appContext) {
        super(appContext, AppStartTriggerStartup.TRIGGER_APP_START, new PlatformInfoImpl(appContext, null, 2, null));
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
    }
}
