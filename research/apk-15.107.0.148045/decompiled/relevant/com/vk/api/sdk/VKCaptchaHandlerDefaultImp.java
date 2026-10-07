package com.vk.api.sdk;

import android.content.Context;
import com.vk.api.sdk.ui.VKCaptchaActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/vk/api/sdk/VKCaptchaHandlerDefaultImp;", "Lcom/vk/api/sdk/VKCaptchaHandler;", "<init>", "()V", "showCaptcha", "", "context", "Landroid/content/Context;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "getLastKey", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VKCaptchaHandlerDefaultImp implements VKCaptchaHandler {
    @Override // com.vk.api.sdk.VKCaptchaHandler
    @Nullable
    public VKApiValidationHandler.CaptchaResult getLastKey() {
        return new VKApiValidationHandler.CaptchaResult(VKCaptchaActivity.INSTANCE.getLastKey(), false, false, false, 8, null);
    }

    @Override // com.vk.api.sdk.VKCaptchaHandler
    public void showCaptcha(@NotNull Context context, @NotNull VKApiValidationHandler.Captcha captcha) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        VKCaptchaActivity.INSTANCE.start(context, captcha.getImg(), captcha.getHeight(), captcha.getWidth());
    }
}
