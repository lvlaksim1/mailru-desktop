package ru.mail.auth.request;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/auth/request/RemoteFingerprintRequest;", "Lru/mail/authorizesdk/data/request/common/SingleRequest;", "Ljava/lang/Void;", "", "context", "Landroid/content/Context;", "requestUrl", "<init>", "(Landroid/content/Context;Ljava/lang/String;)V", "getHostProvider", "Lru/mail/network/HostProvider;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RemoteFingerprintRequest extends SingleRequest<Void, String> {

    @NotNull
    private final String requestUrl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteFingerprintRequest(@NotNull Context context, @NotNull String requestUrl) {
        super(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(requestUrl, "requestUrl");
        this.requestUrl = requestUrl;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        final HostProvider hostProvider = super.getHostProvider();
        Intrinsics.checkNotNullExpressionValue(hostProvider, "getHostProvider(...)");
        return new HostProvider() { // from class: ru.mail.auth.request.RemoteFingerprintRequest.getHostProvider.1
            @Override // ru.mail.network.HostProvider
            public void getPlatformSpecificParams(Uri.Builder url) {
                hostProvider.getPlatformSpecificParams(url);
            }

            @Override // ru.mail.network.HostProvider
            public Uri.Builder getUrlBuilder() {
                Uri.Builder builderBuildUpon = Uri.parse(this.requestUrl).buildUpon();
                Intrinsics.checkNotNullExpressionValue(builderBuildUpon, "buildUpon(...)");
                return builderBuildUpon;
            }

            @Override // ru.mail.network.HostProvider
            public String getUserAgent() {
                String userAgent = hostProvider.getUserAgent();
                Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent(...)");
                return userAgent;
            }

            @Override // ru.mail.network.HostProvider
            public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
                hostProvider.sign(builder, signCreator);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public String onPostExecuteRequest(@Nullable NetworkCommand.Response resp) {
        return String.valueOf(resp != null ? resp.getRespString() : null);
    }
}
