package ru.mail.auth.request;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import ru.mail.Authenticator.R;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class OAuthSendAgentCommand extends AuthorizeRequestCommand<Params, AuthorizeResult> {

    /* JADX INFO: compiled from: ProGuard */
    private static class OAuthSendAgentHostProvider implements HostProvider {
        private final String mAuthUri;
        private final HostProvider mHostProvider;

        public OAuthSendAgentHostProvider(HostProvider hostProvider, String str) {
            this.mAuthUri = str;
            this.mHostProvider = hostProvider;
        }

        @Override // ru.mail.network.HostProvider
        public void getPlatformSpecificParams(Uri.Builder builder) {
            this.mHostProvider.getPlatformSpecificParams(builder);
        }

        @Override // ru.mail.network.HostProvider
        public Uri.Builder getUrlBuilder() {
            Uri.Builder urlBuilder = this.mHostProvider.getUrlBuilder();
            Uri uri = Uri.parse(this.mAuthUri);
            Iterator<String> it = uri.getPathSegments().iterator();
            while (it.hasNext()) {
                urlBuilder.appendPath(it.next());
            }
            for (String str : uri.getQueryParameterNames()) {
                urlBuilder.appendQueryParameter(str, uri.getQueryParameter(str));
            }
            return urlBuilder;
        }

        @Override // ru.mail.network.HostProvider
        public String getUserAgent() {
            return this.mHostProvider.getUserAgent();
        }

        @Override // ru.mail.network.HostProvider
        public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
            this.mHostProvider.sign(builder, signCreator);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Keep
        @Param(method = HttpMethod.HEADER_ADD, name = "X-Mobile-App")
        private String mMobileAppHeader;

        public Params(Context context) {
            this.mMobileAppHeader = context.getString(R.string.auth_csrf_header);
        }
    }

    public OAuthSendAgentCommand(Context context, HostProvider hostProvider, String str, boolean z10) {
        super(context, new Params(context), new OAuthSendAgentHostProvider(hostProvider, str), z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean prepareUrlMigrateToPost() {
        return true;
    }

    public OAuthSendAgentCommand(Context context, Params params, HostProvider hostProvider, String str, boolean z10) {
        super(context, params, new OAuthSendAgentHostProvider(hostProvider, str), z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public AuthorizeResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return getAuthResult(response);
    }
}
