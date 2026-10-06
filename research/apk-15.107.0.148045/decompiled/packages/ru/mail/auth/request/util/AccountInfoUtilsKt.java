package ru.mail.auth.request.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.util.CurrentAccountUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"PARAM_KEY_ACT_MODE", "", "PARAM_KEY_XMAIL_FROM", "PARAM_ACTIVE", "PARAM_INACTIVE", "activeMode", "Lru/mail/auth/request/AccountInfo;", "getActiveMode", "(Lru/mail/auth/request/AccountInfo;)Ljava/lang/String;", "Landroid/content/Context;", "login", "authenticator_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AccountInfoUtilsKt {

    @NotNull
    private static final String PARAM_ACTIVE = "act";

    @NotNull
    private static final String PARAM_INACTIVE = "inact";

    @NotNull
    public static final String PARAM_KEY_ACT_MODE = "act_mode";

    @NotNull
    public static final String PARAM_KEY_XMAIL_FROM = "from";

    @Nullable
    public static final String getActiveMode(@NotNull AccountInfo accountInfo) {
        Intrinsics.checkNotNullParameter(accountInfo, "<this>");
        String login = accountInfo.getLogin();
        if (login == null || login.length() == 0) {
            return null;
        }
        return accountInfo.isAccountActive() ? PARAM_ACTIVE : PARAM_INACTIVE;
    }

    @Nullable
    public static final String getActiveMode(@NotNull Context context, @Nullable String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (str == null || str.length() == 0) {
            return null;
        }
        return CurrentAccountUtils.isLastActiveProfileLogin(context, str) ? PARAM_ACTIVE : PARAM_INACTIVE;
    }
}
