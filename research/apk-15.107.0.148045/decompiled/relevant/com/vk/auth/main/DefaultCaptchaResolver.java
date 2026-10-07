package com.vk.auth.main;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.lifecycle.AppLifecycleDispatcher;
import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/auth/main/DefaultCaptchaResolver;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResolver;", "<init>", "()V", "captchaEnabled", "", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DefaultCaptchaResolver implements VKApiValidationHandler.CaptchaResolver {
    public static final int $stable = 0;

    @Override // com.vk.api.sdk.VKApiValidationHandler.CaptchaResolver
    public boolean captchaEnabled() {
        return !AppLifecycleDispatcher.INSTANCE.isBackground();
    }
}
