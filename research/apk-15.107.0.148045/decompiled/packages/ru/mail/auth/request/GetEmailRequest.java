package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.NonNull;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class GetEmailRequest<P> extends SingleRequest<P, Result> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mEmail;

        public Result(String str) {
            this.mEmail = str;
        }

        public String getEmail() {
            return this.mEmail;
        }
    }

    public GetEmailRequest(Context context, P p10) {
        super(context, p10);
    }

    @Override // ru.mail.authorizesdk.data.request.common.SingleRequest, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }
}
