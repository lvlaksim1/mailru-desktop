package ru.mail.serverapi;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailLoginFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lru/mail/serverapi/BrowserCookieSetter;", "", "setUpSessionInBrowser", "", "context", "Landroid/content/Context;", "accountName", "", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface BrowserCookieSetter {
    void setUpSessionInBrowser(@Nullable Context context, @Nullable String accountName, @Nullable String accountType);
}
