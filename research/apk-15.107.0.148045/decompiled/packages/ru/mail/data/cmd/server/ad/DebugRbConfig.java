package ru.mail.data.cmd.server.ad;

import android.content.Context;
import android.preference.PreferenceManager;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/data/cmd/server/ad/DebugRbConfig;", "", "<init>", "()V", "RB_CONFIG_KEY", "", "get", "context", "Landroid/content/Context;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DebugRbConfig {
    public static final int $stable = 0;

    @NotNull
    public static final DebugRbConfig INSTANCE = new DebugRbConfig();

    @NotNull
    public static final String RB_CONFIG_KEY = "rb_config_key";

    private DebugRbConfig() {
    }

    @JvmStatic
    @NotNull
    public static final String get(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(RB_CONFIG_KEY, "");
        return string == null ? "" : string;
    }
}
