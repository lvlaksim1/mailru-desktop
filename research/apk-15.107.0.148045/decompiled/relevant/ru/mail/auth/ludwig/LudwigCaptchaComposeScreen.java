package ru.mail.auth.ludwig;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizesdk.util.mvi.navigation.Screen;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/ludwig/LudwigCaptchaComposeScreen;", "Lru/mail/authorizesdk/util/mvi/navigation/Screen;", "", "ludwigToken", "<init>", "(Ljava/lang/String;)V", "getLudwigToken", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudwigCaptchaComposeScreen extends Screen<String> {

    @NotNull
    private final String ludwigToken;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LudwigCaptchaComposeScreen(@NotNull String ludwigToken) {
        super("", null, null, 6, null);
        Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
        this.ludwigToken = ludwigToken;
    }

    @NotNull
    public final String getLudwigToken() {
        return this.ludwigToken;
    }
}
