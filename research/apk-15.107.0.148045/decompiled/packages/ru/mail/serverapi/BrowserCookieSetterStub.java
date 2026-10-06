package ru.mail.serverapi;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailLoginFragment;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\f"}, d2 = {"Lru/mail/serverapi/BrowserCookieSetterStub;", "Lru/mail/serverapi/BrowserCookieSetter;", "<init>", "()V", "setUpSessionInBrowser", "", "context", "Landroid/content/Context;", "accountName", "", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "Companion", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BrowserCookieSetterStub implements BrowserCookieSetter {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("BrowserCookieSetterStub");

    @Override // ru.mail.serverapi.BrowserCookieSetter
    public void setUpSessionInBrowser(@Nullable Context context, @Nullable String accountName, @Nullable String accountType) {
        LOG.d("Setting up session in browser: stub implementation");
    }
}
