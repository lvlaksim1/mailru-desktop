package ru.mail.serverapi;

import androidx.annotation.AnyThread;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.kotlin.cookie.MailCookie;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH'J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\bH'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lru/mail/serverapi/CookieSanitizeManager;", "", "refreshSanitizedCookie", "", "login", "", "page", "callback", "Lkotlin/Function0;", "onSuccess", "onFail", "getSanitizedCookies", "", "Lru/mail/util/kotlin/cookie/MailCookie;", "setUpSanitizedCookie", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CookieSanitizeManager {
    @NotNull
    List<MailCookie> getSanitizedCookies(@NotNull String login);

    @AnyThread
    void refreshSanitizedCookie(@NotNull String login, @NotNull String page, @NotNull Function0<Unit> callback);

    @AnyThread
    void refreshSanitizedCookie(@NotNull String login, @NotNull String page, @NotNull Function0<Unit> onSuccess, @NotNull Function0<Unit> onFail);

    void setUpSanitizedCookie(@NotNull String login);
}
