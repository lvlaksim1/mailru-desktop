package com.vk.api.sdk;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\n\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/VKCaptchaHandler;", "", "showCaptcha", "", "context", "Landroid/content/Context;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "getLastKey", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface VKCaptchaHandler {
    @Nullable
    VKApiValidationHandler.CaptchaResult getLastKey();

    void showCaptcha(@NotNull Context context, @NotNull VKApiValidationHandler.Captcha captcha);
}
