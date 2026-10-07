package ru.mail.ludvig_captcha.utils.ludochka;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.test.recognition.TestRecognition;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lru/mail/ludvig_captcha/utils/ludochka/LudochkaConfig;", "", "ludwigToken", "", "ludwigUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLudwigToken", "()Ljava/lang/String;", "getLudwigUrl", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudochkaConfig {

    @NotNull
    private final String ludwigToken;

    @Nullable
    private final String ludwigUrl;

    public LudochkaConfig(@NotNull String ludwigToken, @Nullable String str) {
        Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
        this.ludwigToken = ludwigToken;
        this.ludwigUrl = str;
    }

    @NotNull
    public final String getLudwigToken() {
        return this.ludwigToken;
    }

    @Nullable
    public final String getLudwigUrl() {
        return this.ludwigUrl;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LudochkaConfig(String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i10 & 2) != 0) {
            if (TestRecognition.getIsTest()) {
                str2 = "https://access.mini-mail.ru?mp=android";
            } else {
                str2 = "https://access.mail.ru?mp=android";
            }
        }
        this(str, str2);
    }
}
