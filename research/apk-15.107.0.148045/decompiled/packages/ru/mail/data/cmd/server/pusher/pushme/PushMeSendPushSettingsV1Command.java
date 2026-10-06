package ru.mail.data.cmd.server.pusher.pushme;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0014\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000e"}, d2 = {"Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsV1Command;", "Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand;", "context", "Landroid/content/Context;", "params", "Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;Z)V", "parseResponseData", "Lru/mail/mailbox/cmd/CommandStatus;", "resp", "Lru/mail/network/NetworkCommand$Response;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHost = R.string.push_default_host, defScheme = R.string.push_default_scheme, prefKey = "push")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "set_settings"})
public final class PushMeSendPushSettingsV1Command extends PushMeSendPushSettingsCommand {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeSendPushSettingsV1Command(@NotNull Context context, @NotNull PushMeSendPushSettingsCommand.Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand
    @NotNull
    protected CommandStatus<?> parseResponseData(@NotNull NetworkCommand.Response resp) throws JSONException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        int i10 = new JSONObject(resp.getRespString()).getJSONObject("error").getInt("code");
        if (i10 == 0) {
            return new CommandStatus.OK();
        }
        MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("error jsonCode " + i10);
        return new CommandStatus.ERROR();
    }
}
