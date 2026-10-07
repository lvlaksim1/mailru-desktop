package ru.mail.auth.webview;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\r"}, d2 = {"Lru/mail/auth/webview/CriticalAuthRequests;", "", "<init>", "(Ljava/lang/String;I)V", "API", "CAPTCHA", "STATIC", "matches", "", "toCheck", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum CriticalAuthRequests {
    API { // from class: ru.mail.auth.webview.CriticalAuthRequests.API
        @Override // ru.mail.auth.webview.CriticalAuthRequests
        public boolean matches(@NotNull Uri toCheck, @Nullable Context context) {
            Intrinsics.checkNotNullParameter(toCheck, "toCheck");
            String path = toCheck.getPath();
            return path != null && (StringsKt.contains$default((CharSequence) path, (CharSequence) "cgi-bin/secstep", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) "cgi-bin/auth", false, 2, (Object) null));
        }
    },
    CAPTCHA { // from class: ru.mail.auth.webview.CriticalAuthRequests.CAPTCHA
        @Override // ru.mail.auth.webview.CriticalAuthRequests
        public boolean matches(@NotNull Uri toCheck, @Nullable Context context) {
            String string;
            Intrinsics.checkNotNullParameter(toCheck, "toCheck");
            if (context != null) {
                string = context.getResources().getString(R.string.auth_capcha_host);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            } else {
                string = "alt-c.mail.ru";
            }
            return Intrinsics.areEqual(string, toCheck.getAuthority());
        }
    },
    STATIC { // from class: ru.mail.auth.webview.CriticalAuthRequests.STATIC
        @Override // ru.mail.auth.webview.CriticalAuthRequests
        public boolean matches(@NotNull Uri toCheck, @Nullable Context context) {
            Intrinsics.checkNotNullParameter(toCheck, "toCheck");
            String lastPathSegment = toCheck.getLastPathSegment();
            return lastPathSegment != null && (StringsKt.endsWith$default(lastPathSegment, ".js", false, 2, (Object) null) || StringsKt.endsWith$default(lastPathSegment, ".css", false, 2, (Object) null));
        }
    };

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* synthetic */ CriticalAuthRequests(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public static EnumEntries<CriticalAuthRequests> getEntries() {
        return $ENTRIES;
    }

    public abstract boolean matches(@NotNull Uri toCheck, @Nullable Context context);
}
