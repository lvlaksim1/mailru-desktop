package ru.mail.data.cmd.server;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/AvatarHostInitializer;", "", "initialize", "", "context", "Landroid/content/Context;", "avatarName", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AvatarHostInitializer {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ boolean initialize$default(AvatarHostInitializer avatarHostInitializer, Context context, String str, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initialize");
        }
        if ((i10 & 2) != 0) {
            str = "avatar";
        }
        return avatarHostInitializer.initialize(context, str);
    }

    boolean initialize(@NotNull Context context, @NotNull String avatarName);
}
