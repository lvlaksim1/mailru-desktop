package ru.mail.authorizationsdk.feature.captcha.ludochka;

import androidx.compose.runtime.Immutable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Immutable
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;", "", "ludwigToken", "", "ludwigUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLudwigToken", "()Ljava/lang/String;", "getLudwigUrl", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudochkaConfig {
    public static final int $stable = 0;

    @NotNull
    private final String ludwigToken;

    @NotNull
    private final String ludwigUrl;

    public LudochkaConfig(@NotNull String ludwigToken, @NotNull String ludwigUrl) {
        Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
        Intrinsics.checkNotNullParameter(ludwigUrl, "ludwigUrl");
        this.ludwigToken = ludwigToken;
        this.ludwigUrl = ludwigUrl;
    }

    @NotNull
    public final String getLudwigToken() {
        return this.ludwigToken;
    }

    @NotNull
    public final String getLudwigUrl() {
        return this.ludwigUrl;
    }
}
