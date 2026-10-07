package com.vk.superapp.core;

import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.superapp.core.ui.listener.VkSdkUiListenerKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/core/SdkCaptchaResolver;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResolver;", "<init>", "()V", "captchaEnabled", "", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SdkCaptchaResolver implements VKApiValidationHandler.CaptchaResolver {
    @Override // com.vk.api.sdk.VKApiValidationHandler.CaptchaResolver
    public boolean captchaEnabled() {
        return VkSdkUiListenerKt.getVkSdkUiListener().hasUiOnScreen();
    }
}
