package ru.mail.serverapi.retrofit.session;

import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/serverapi/retrofit/session/OAuthSession;", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", CommonConstant.KEY_ACCESS_TOKEN, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "applyToRequest", "", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request$Builder;", "body", "Lokhttp3/FormBody$Builder;", "prepareSessionException", "Lru/mail/serverapi/retrofit/session/RequestSessionException;", "reason", "Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "Creator", "Companion", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OAuthSession implements TornadoSession {

    @NotNull
    private static final String NAME = "TORNADO";

    @NotNull
    private static final String TYPE = "ru.mail.oauth2.access";

    @NotNull
    private final String accessToken;

    @NotNull
    private final String login;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/retrofit/session/OAuthSession$Creator;", "Lru/mail/serverapi/retrofit/session/SessionCreator;", "tokenProvider", "Lru/mail/serverapi/retrofit/session/TokenProvider;", "<init>", "(Lru/mail/serverapi/retrofit/session/TokenProvider;)V", "create", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Creator implements SessionCreator {

        @NotNull
        private final TokenProvider tokenProvider;

        public Creator(@NotNull TokenProvider tokenProvider) {
            Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
            this.tokenProvider = tokenProvider;
        }

        @Override // ru.mail.serverapi.retrofit.session.SessionCreator
        @NotNull
        public TornadoSession create(@Nullable String login) throws BadSessionException {
            if (login != null) {
                return new OAuthSession(login, this.tokenProvider.peekAuthToken(login, "ru.mail.oauth2.access", OAuthSession.NAME), null);
            }
            throw new BadSessionException("Unable to get email", null, "ru.mail.oauth2.access", OAuthSession.NAME, 2, null);
        }
    }

    public /* synthetic */ OAuthSession(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    @Override // ru.mail.serverapi.retrofit.session.TornadoSession
    public void applyToRequest(@NotNull Request.Builder request, @NotNull FormBody.Builder body) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(body, "body");
        body.add("access_token", this.accessToken);
    }

    @Override // ru.mail.serverapi.retrofit.session.TornadoSession
    @NotNull
    public RequestSessionException prepareSessionException(@NotNull RequestSessionException.Reason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new RequestSessionException(reason, NAME, "ru.mail.oauth2.access", this.login, this.accessToken);
    }

    private OAuthSession(String str, String str2) {
        this.login = str;
        this.accessToken = str2;
    }
}
