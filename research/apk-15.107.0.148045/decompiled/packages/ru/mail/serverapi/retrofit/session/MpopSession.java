package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.Request;
import org.apache.http.cookie.SM;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/serverapi/retrofit/session/MpopSession;", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", "mpopToken", "mpopCookie", "cookieHeader", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "applyToRequest", "", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request$Builder;", "body", "Lokhttp3/FormBody$Builder;", "prepareSessionException", "Lru/mail/serverapi/retrofit/session/RequestSessionException;", "reason", "Lru/mail/serverapi/retrofit/session/RequestSessionException$Reason;", "Creator", "Companion", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MpopSession implements TornadoSession {

    @NotNull
    public static final String NAME_LEGACY = "LEGACY";

    @NotNull
    public static final String NAME_TORNADO_MPOP = "TORNADO_MPOP";

    @NotNull
    public static final String TYPE = "ru.mail.oauth2.access";

    @NotNull
    private final String cookieHeader;

    @NotNull
    private final String login;

    @NotNull
    private final String mpopCookie;

    @NotNull
    private final String mpopToken;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/retrofit/session/MpopSession$Creator;", "Lru/mail/serverapi/retrofit/session/SessionCreator;", "tokenProvider", "Lru/mail/serverapi/retrofit/session/TokenProvider;", "<init>", "(Lru/mail/serverapi/retrofit/session/TokenProvider;)V", "create", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
            if (login == null) {
                throw new BadSessionException("Unable to get email", null, MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN, MpopSession.NAME_TORNADO_MPOP, 2, null);
            }
            String strPeekAuthToken = this.tokenProvider.peekAuthToken(login, MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN, MpopSession.NAME_TORNADO_MPOP);
            String strPeekAuthToken2 = this.tokenProvider.peekAuthToken(login, "ru.mail", MpopSession.NAME_LEGACY);
            String cookieDomain = Authenticator.ValidAccountTypes.getEnumByValue(this.tokenProvider.getAccountType()).getCookieDomain();
            Intrinsics.checkNotNullExpressionValue(cookieDomain, "getCookieDomain(...)");
            String cookieHeader = MailAccountConstants.getCookieHeader(strPeekAuthToken2, cookieDomain);
            Intrinsics.checkNotNull(cookieHeader);
            return new MpopSession(login, strPeekAuthToken, strPeekAuthToken2, cookieHeader, null);
        }
    }

    public /* synthetic */ MpopSession(String str, String str2, String str3, String str4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4);
    }

    @Override // ru.mail.serverapi.retrofit.session.TornadoSession
    public void applyToRequest(@NotNull Request.Builder request, @NotNull FormBody.Builder body) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(body, "body");
        request.header(SM.COOKIE, this.cookieHeader);
        body.add("token", this.mpopToken);
    }

    @Override // ru.mail.serverapi.retrofit.session.TornadoSession
    @NotNull
    public RequestSessionException prepareSessionException(@NotNull RequestSessionException.Reason reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new RequestSessionException(reason, NAME_TORNADO_MPOP, MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN, this.login, reason == RequestSessionException.Reason.User ? this.mpopCookie : this.mpopToken);
    }

    private MpopSession(String str, String str2, String str3, String str4) {
        this.login = str;
        this.mpopToken = str2;
        this.mpopCookie = str3;
        this.cookieHeader = str4;
    }
}
