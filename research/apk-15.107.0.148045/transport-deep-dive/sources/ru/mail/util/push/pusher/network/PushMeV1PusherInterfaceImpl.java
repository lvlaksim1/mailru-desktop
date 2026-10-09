package ru.mail.util.push.pusher.network;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand;
import ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsV1Command;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0006\u001a\u0010\u0012\u0002\b\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lru/mail/util/push/pusher/network/PushMeV1PusherInterfaceImpl;", "Lru/mail/util/push/pusher/network/PushMePusherInterfaceImpl;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "createSetSettingsCommand", "Lru/mail/mailbox/cmd/Command;", "Lru/mail/mailbox/cmd/CommandStatus;", "login", "", "settings", "Lorg/json/JSONArray;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeV1PusherInterfaceImpl extends PushMePusherInterfaceImpl {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeV1PusherInterfaceImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // ru.mail.util.push.pusher.network.PushMePusherInterfaceImpl
    @NotNull
    protected Command<?, CommandStatus<?>> createSetSettingsCommand(@NotNull String login, @NotNull JSONArray settings) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return new PushMeSendPushSettingsV1Command(getContext(), new PushMeSendPushSettingsCommand.Params(login, settings), MigrateToPostUtils.is12153Enabled(getContext()));
    }
}
