package ru.mail.serverapi;

import com.sun.mail.imap.IMAPStore;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailLoginFragment;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&J\"\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u0005H&J \u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lru/mail/serverapi/Analytics;", "", "authCommandError", "", IMAPStore.ID_COMMAND, "", "refreshTokenError", "error", "badSession", "type", ApiUris.AUTHORITY_API, "tokenType", "refreshToken", "result", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Analytics {
    void authCommandError(@NotNull String command);

    void badSession(@NotNull String type, @Nullable String api, @NotNull String tokenType);

    void refreshToken(@NotNull String api, @NotNull String result, @NotNull String accountType);

    void refreshTokenError(@NotNull String error);
}
