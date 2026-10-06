package ru.mail.auth.request;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.CgiBinAuthStrategy;
import ru.mail.auth.OAuthLoginRequests;
import ru.mail.mailbox.cmd.Command;
import ru.mail.network.HostProvider;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lru/mail/auth/request/QrLoginCommandProvider;", "Lru/mail/auth/CgiBinAuthStrategy$CommandProvider;", "<init>", "()V", "getCommand", "Lru/mail/mailbox/cmd/Command;", "context", "Landroid/content/Context;", "provider", "Lru/mail/network/HostProvider;", PushProcessor.DATAKEY_EXTRAS, "Landroid/os/Bundle;", "usePostParams", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class QrLoginCommandProvider implements CgiBinAuthStrategy.CommandProvider {
    @Override // ru.mail.auth.CgiBinAuthStrategy.CommandProvider
    @NotNull
    public Command<?, ?> getCommand(@NotNull Context context, @NotNull HostProvider provider, @NotNull Bundle extras, boolean usePostParams) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(extras, "extras");
        return OAuthLoginRequests.authQrLoginToMail(context, provider, extras, usePostParams);
    }
}
