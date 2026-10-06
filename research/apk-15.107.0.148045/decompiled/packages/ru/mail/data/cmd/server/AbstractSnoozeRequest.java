package ru.mail.data.cmd.server;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.NetworkCommand;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u0013*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00040\u0003:\u0001\u0013B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\f\u001a\"0\rR\u001e\u0012\f\u0012\n \u000f*\u0004\u0018\u00018\u00008\u0000\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00040\u00040\u000eH\u0014J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0014¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/AbstractSnoozeRequest;", "P", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/mailbox/cmd/EmptyResult;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;Z)V", "getCustomDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Companion", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AbstractSnoozeRequest<P extends ServerCommandEmailParams> extends PostServerRequest<P, EmptyResult> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("AbstractSnoozeRequest");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractSnoozeRequest(@NotNull Context context, @NotNull P params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkCommand<P, EmptyResult>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<P, EmptyResult>.TornadoDelegate(this) { // from class: ru.mail.data.cmd.server.AbstractSnoozeRequest.getCustomDelegate.1
            {
                super();
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject responseBody) {
                Intrinsics.checkNotNullParameter(responseBody, "responseBody");
                try {
                    if (responseBody.has("ids[0]")) {
                        if (Intrinsics.areEqual(responseBody.getJSONObject("ids[0]").getString("error"), "invalid")) {
                            return new MailCommandStatus.MESSAGE_NOT_EXIST();
                        }
                    } else if (responseBody.has("date") && Intrinsics.areEqual(responseBody.getJSONObject("date").getString("error"), "invalid")) {
                        return new MailCommandStatus.ERROR_DATE_RANGE();
                    }
                } catch (JSONException e10) {
                    AbstractSnoozeRequest.LOG.e("Unable to parse response", e10);
                }
                CommandStatus<?> commandStatusOnBadRequest = super.onBadRequest(responseBody);
                Intrinsics.checkNotNullExpressionValue(commandStatusOnBadRequest, "onBadRequest(...)");
                return commandStatusOnBadRequest;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        return new EmptyResult();
    }
}
