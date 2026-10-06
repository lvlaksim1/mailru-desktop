package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import java.util.Map;
import javax.annotation.Nullable;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class HttpsAuthorizeLoginRequest extends AuthorizeRequest<HttpsAuthorizeLoginCommand> {
    public HttpsAuthorizeLoginRequest(Context context, HostProvider hostProvider, String str, String str2, String str3, @Nullable Map<String, String> map) {
        this(context, hostProvider, str, str2, str3, false, false, map, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.auth.request.AuthorizeRequest, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof HttpsAuthorizeLoginCommand) && (t10 instanceof CommandStatus.OK)) {
            HttpsAuthorizeLoginCommand.TsaCookieResult tsaCookieResult = (HttpsAuthorizeLoginCommand.TsaCookieResult) ((CommandStatus.OK) t10).getData();
            String tsaCookie = tsaCookieResult.getTsaCookie();
            if (!TextUtils.isEmpty(tsaCookie)) {
                MailSecondStepFragment.setTsaCookie(getContext(), tsaCookie, tsaCookieResult.getLogin());
            }
        }
        return t10;
    }

    public HttpsAuthorizeLoginRequest(Context context, HostProvider hostProvider, String str, String str2, String str3, boolean z10, boolean z11, @Nullable Map<String, String> map, boolean z12) {
        super(context, new HttpsAuthorizeLoginCommand(context, new HttpsAuthorizeLoginCommand.Params(context, str, str2, str3, map, z11, z12), hostProvider, z10));
    }
}
