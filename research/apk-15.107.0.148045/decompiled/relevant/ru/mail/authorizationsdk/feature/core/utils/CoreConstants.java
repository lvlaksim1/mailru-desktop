package ru.mail.authorizationsdk.feature.core.utils;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lru/mail/authorizationsdk/feature/core/utils/CoreConstants;", "", "<init>", "()V", "ACCESS_TOKEN_PARAM", "", "EXPIRES_PARAM", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CoreConstants {
    public static final int $stable = 0;

    @NotNull
    public static final String ACCESS_TOKEN_PARAM = "access_token";

    @NotNull
    public static final String EXPIRES_PARAM = "expires";

    @NotNull
    public static final CoreConstants INSTANCE = new CoreConstants();

    private CoreConstants() {
    }
}
