package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.mail.mailbox.cmd.Command;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class AuthPhoneToken extends AuthorizeRequest<AuthPhoneTokenCommand> {

    /* JADX INFO: compiled from: ProGuard */
    @UrlPath(pathSegments = {"cgi-bin", "auth"})
    public static class AuthPhoneTokenCommand extends AuthorizeRequestCommand<Params, AuthorizeRequestCommand.MpopCookieResult> {

        /* JADX INFO: compiled from: ProGuard */
        public static class Params {
            private static final String PARAM_KEY_LOGIN = "Login";
            private static final String PARAM_KEY_PHONE_TOKEN = "PhoneToken";
            private static final String PARAM_KEY_SIMPLE = "simple";

            @Keep
            @Param(method = HttpMethod.POST, name = PARAM_KEY_SIMPLE)
            private static final int SIMPLE = 1;

            @Param(method = HttpMethod.POST, name = PARAM_KEY_LOGIN)
            private final String mLogin;

            @Param(method = HttpMethod.POST, name = PARAM_KEY_PHONE_TOKEN)
            private final String mPhoneToken;

            public Params(String str, String str2) {
                this.mLogin = str;
                this.mPhoneToken = str2;
            }
        }

        public AuthPhoneTokenCommand(Context context, HostProvider hostProvider, String str, String str2, boolean z10) {
            super(context, new Params(str, str2), hostProvider, z10);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.network.NetworkCommand
        @NonNull
        @NotNull
        public AuthorizeRequestCommand.MpopCookieResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
            return getMpopCookieResult(response);
        }
    }

    public AuthPhoneToken(Context context, HostProvider hostProvider, String str, String str2, boolean z10) {
        super(context, new AuthPhoneTokenCommand(context, hostProvider, str, str2, z10));
    }

    @Override // ru.mail.auth.request.AuthorizeRequest
    protected <T> boolean shouldHandleOAuthResult(Command<?, T> command) {
        return false;
    }
}
