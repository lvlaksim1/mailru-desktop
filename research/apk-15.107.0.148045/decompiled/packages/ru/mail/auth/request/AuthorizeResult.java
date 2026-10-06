package ru.mail.auth.request;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public interface AuthorizeResult {

    /* JADX INFO: compiled from: ProGuard */
    public interface AuthorizeResultVisitor<T> {
        T visit(AuthorizeRequestCommand.MpopCookieResult mpopCookieResult);

        T visit(AuthorizeRequestCommand.OAuthTokensResult oAuthTokensResult);
    }

    <T> T accept(AuthorizeResultVisitor<T> authorizeResultVisitor);
}
