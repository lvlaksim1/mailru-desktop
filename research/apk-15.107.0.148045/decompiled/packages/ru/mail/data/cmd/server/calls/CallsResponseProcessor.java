package ru.mail.data.cmd.server.calls;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.analytics.logger.AdditionalAnalyticsParamsProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e0\u0005R\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0014\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsResponseProcessor;", "Lru/mail/serverapi/TornadoResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "<init>", "(Lru/mail/network/NetworkCommand$Response;Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;)V", "process", "Lru/mail/mailbox/cmd/CommandStatus;", "isCsrfInvalid", "", "response", "csrfInvalidInBody", "handleUnauthorizedStatus", "respString", "", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class CallsResponseProcessor extends TornadoResponseProcessor {
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("CallsResponseProcessor");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallsResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate) {
        super(resp, customDelegate);
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
    }

    private final boolean csrfInvalidInBody(NetworkCommand.Response response) {
        try {
            return Intrinsics.areEqual(new JSONObject(response.getRespString()).getJSONObject("fields").getString("reason"), "csrf_invalid");
        } catch (JSONException unused) {
            return false;
        }
    }

    private final CommandStatus<?> handleUnauthorizedStatus(String respString) {
        try {
            JSONObject jSONObject = new JSONObject(respString);
            if (Intrinsics.areEqual(jSONObject.getString("type"), AdditionalAnalyticsParamsProvider.UNAUTHORIZED_EMAIL)) {
                String string = jSONObject.getJSONObject("fields").getString("reason");
                if (Intrinsics.areEqual(string, "token_invalid")) {
                    CommandStatus<?> commandStatusOnUnauthorized = getDelegate().onUnauthorized(string);
                    Intrinsics.checkNotNullExpressionValue(commandStatusOnUnauthorized, "onUnauthorized(...)");
                    return commandStatusOnUnauthorized;
                }
            }
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            e10.printStackTrace();
        }
        CommandStatus<?> commandStatusOnError = getDelegate().onError(getResponse());
        Intrinsics.checkNotNullExpressionValue(commandStatusOnError, "onError(...)");
        return commandStatusOnError;
    }

    private final boolean isCsrfInvalid(NetworkCommand.Response response) {
        return response.getStatusCode() == 403 && csrfInvalidInBody(response);
    }

    @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
    @NotNull
    public CommandStatus<?> process() {
        if (getResponse().getStatusCode() == 401) {
            String respString = getResponse().getRespString();
            Intrinsics.checkNotNullExpressionValue(respString, "getRespString(...)");
            return handleUnauthorizedStatus(respString);
        }
        NetworkCommand.Response response = getResponse();
        Intrinsics.checkNotNullExpressionValue(response, "getResponse(...)");
        if (isCsrfInvalid(response)) {
            CommandStatus<?> commandStatusOnUnauthorized = getDelegate().onUnauthorized("Csrf token needed");
            Intrinsics.checkNotNullExpressionValue(commandStatusOnUnauthorized, "onUnauthorized(...)");
            return commandStatusOnUnauthorized;
        }
        if (getResponse().getStatusCode() != 200) {
            CommandStatus<?> commandStatusOnError = getDelegate().onError(getResponse());
            Intrinsics.checkNotNullExpressionValue(commandStatusOnError, "onError(...)");
            return commandStatusOnError;
        }
        CommandStatus<?> commandStatusOnResponseOk = getDelegate().onResponseOk(getResponse());
        Intrinsics.checkNotNullExpressionValue(commandStatusOnResponseOk, "onResponseOk(...)");
        return commandStatusOnResponseOk;
    }
}
