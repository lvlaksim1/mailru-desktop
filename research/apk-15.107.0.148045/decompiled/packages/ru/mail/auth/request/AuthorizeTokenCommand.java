package ru.mail.auth.request;

import android.content.Context;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.share.MailToMyselfParameters;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0001\u0015B]\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\t\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0014¨\u0006\u0016"}, d2 = {"Lru/mail/auth/request/AuthorizeTokenCommand;", "Lru/mail/auth/request/AuthorizeRequestCommand;", "Lru/mail/auth/request/AuthorizeTokenCommand$Params;", "Lru/mail/auth/request/AuthorizeResult;", "context", "Landroid/content/Context;", "login", "", "queryParams", "", "cookies", "hostProvider", "Lru/mail/network/HostProvider;", "usePostParams", "", "isNoExternalFlow", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lru/mail/network/HostProvider;ZZ)V", "onPostExecuteRequest", "response", "Lru/mail/network/NetworkCommand$Response;", "Params", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {"cgi-bin", "auth"})
public final class AuthorizeTokenCommand extends AuthorizeRequestCommand<Params, AuthorizeResult> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001c\u0010\u000f\u001a\u00020\u00052\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007H\u0002R\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/auth/request/AuthorizeTokenCommand$Params;", "Lru/mail/auth/request/AuthorizeRequestCommand$Params;", "context", "Landroid/content/Context;", "login", "", "params", "", "cookies", "isNoExternalFlow", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Z)V", "body", "cookie", "buildCookieString", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends AuthorizeRequestCommand.Params {

        @NotNull
        private static final String PARAM_KEY_COOKIE = "Cookie";

        @Param(method = HttpMethod.POST, type = Param.Type.COMPLEX_OBJECT)
        @NotNull
        private final Map<String, String> body;

        @Param(method = HttpMethod.HEADER_ADD, name = "Cookie")
        @NotNull
        private final String cookie;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@Nullable Context context, @Nullable String str, @NotNull Map<String, String> params, @NotNull Map<String, String> cookies, boolean z10) {
            super(context, str, z10);
            Intrinsics.checkNotNullParameter(params, "params");
            Intrinsics.checkNotNullParameter(cookies, "cookies");
            this.body = params;
            this.cookie = buildCookieString(cookies);
        }

        private final String buildCookieString(Map<String, String> cookies) {
            return CollectionsKt.joinToString$default(cookies.entrySet(), MailToMyselfParameters.ATTACH_SUBJECT_DELIMITER, null, null, 0, null, new Function1() { // from class: ru.mail.auth.request.a
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AuthorizeTokenCommand.Params.buildCookieString$lambda$0((Map.Entry) obj);
                }
            }, 30, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence buildCookieString$lambda$0(Map.Entry it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getKey() + "=" + it.getValue();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthorizeTokenCommand(@Nullable Context context, @Nullable String str, @NotNull Map<String, String> queryParams, @NotNull Map<String, String> cookies, @Nullable HostProvider hostProvider, boolean z10, boolean z11) {
        super(context, new Params(context, str, queryParams, cookies, z11), hostProvider, z10);
        Intrinsics.checkNotNullParameter(queryParams, "queryParams");
        Intrinsics.checkNotNullParameter(cookies, "cookies");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public AuthorizeResult onPostExecuteRequest(@NotNull NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(response, "response");
        AuthorizeResult authResult = getAuthResult(response);
        Intrinsics.checkNotNullExpressionValue(authResult, "getAuthResult(...)");
        return authResult;
    }
}
