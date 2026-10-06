package ru.mail.serverapi.retrofit.session;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AuthenticatorConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/retrofit/session/TornadoSessionCreator;", "Lru/mail/serverapi/retrofit/session/SessionCreator;", "tokenProvider", "Lru/mail/serverapi/retrofit/session/TokenProvider;", "<init>", "(Lru/mail/serverapi/retrofit/session/TokenProvider;)V", "create", "Lru/mail/serverapi/retrofit/session/TornadoSession;", "login", "", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoSessionCreator implements SessionCreator {

    @NotNull
    private final TokenProvider tokenProvider;

    public TornadoSessionCreator(@NotNull TokenProvider tokenProvider) {
        Intrinsics.checkNotNullParameter(tokenProvider, "tokenProvider");
        this.tokenProvider = tokenProvider;
    }

    @Override // ru.mail.serverapi.retrofit.session.SessionCreator
    @NotNull
    public TornadoSession create(@Nullable String login) throws BadSessionException {
        return (AuthenticatorConfig.getInstance().isOAuthEnabled() ? new OAuthSession.Creator(this.tokenProvider) : new MpopSession.Creator(this.tokenProvider)).create(login);
    }
}
