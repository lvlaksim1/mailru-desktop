package ru.mail.serverapi;

import android.content.Context;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@EntryPoint
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lru/mail/serverapi/CookieSetterEntryPoint;", "", "getBrowserCookieSetter", "Lru/mail/serverapi/BrowserCookieSetter;", "Companion", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@InstallIn({SingletonComponent.class})
public interface CookieSetterEntryPoint {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\n"}, d2 = {"Lru/mail/serverapi/CookieSetterEntryPoint$Companion;", "", "<init>", "()V", "browserCookieSetter", "Lru/mail/serverapi/BrowserCookieSetter;", "context", "Landroid/content/Context;", "entryPoint", "Lru/mail/serverapi/CookieSetterEntryPoint;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        private final CookieSetterEntryPoint entryPoint(Context context) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (CookieSetterEntryPoint) EntryPointAccessors.fromApplication(applicationContext, CookieSetterEntryPoint.class);
        }

        @JvmStatic
        @NotNull
        public final BrowserCookieSetter browserCookieSetter(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return entryPoint(context).getBrowserCookieSetter();
        }
    }

    @JvmStatic
    @NotNull
    static BrowserCookieSetter browserCookieSetter(@NotNull Context context) {
        return INSTANCE.browserCookieSetter(context);
    }

    @NotNull
    BrowserCookieSetter getBrowserCookieSetter();
}
