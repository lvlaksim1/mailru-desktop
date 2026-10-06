package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.preference.PreferenceManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.mails.R;
import ru.mail.network.HostProviderWrapperImpl;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/AvatarHostInitializerImpl;", "Lru/mail/data/cmd/server/AvatarHostInitializer;", "<init>", "()V", "initialize", "", "context", "Landroid/content/Context;", "avatarName", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AvatarHostInitializerImpl implements AvatarHostInitializer {
    public static final int $stable = 0;

    @Override // ru.mail.data.cmd.server.AvatarHostInitializer
    public boolean initialize(@NotNull Context context, @NotNull String avatarName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(avatarName, "avatarName");
        HostProviderWrapperImpl hostProviderWrapperImpl = new HostProviderWrapperImpl(context);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceHostProvider.SCHEME_PREFIX + avatarName, hostProviderWrapperImpl.getSchemeOrHost(R.string.avatar_default_scheme)).putString(PreferenceHostProvider.HOST_PREFIX + avatarName, hostProviderWrapperImpl.getSchemeOrHost(R.string.avatar_default_host)).apply();
        return true;
    }
}
