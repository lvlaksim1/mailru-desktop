package ru.mail.authorizationsdk.feature.captcha;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEvent;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEventListener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
final class LudwigCaptchaScreenKt$sam$ru_mail_authorizationsdk_feature_captcha_ludochka_LudochkaEventListener$0 implements LudochkaEventListener, FunctionAdapter {
    private final /* synthetic */ Function1 function;

    LudwigCaptchaScreenKt$sam$ru_mail_authorizationsdk_feature_captcha_ludochka_LudochkaEventListener$0(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }

    public final boolean equals(@Nullable Object obj) {
        if ((obj instanceof LudochkaEventListener) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    @Override // kotlin.jvm.internal.FunctionAdapter
    @NotNull
    public final Function<?> getFunctionDelegate() {
        return this.function;
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEventListener
    public final /* synthetic */ void onEvent(LudochkaEvent ludochkaEvent) {
        this.function.invoke(ludochkaEvent);
    }
}
