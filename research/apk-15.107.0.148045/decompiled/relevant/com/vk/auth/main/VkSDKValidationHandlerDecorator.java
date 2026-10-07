package com.vk.auth.main;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.utils.VKValidationLocker;
import com.vk.superapp.core.SdkCaptchaResolver;
import com.vk.superapp.core.utils.VKCLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\fJ&\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0096\u0001¢\u0006\u0004\b\u0010\u0010\u0011J&\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007H\u0096\u0001¢\u0006\u0004\b\u0014\u0010\u0011J \u0010\u0019\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0096\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\nH\u0096\u0001¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/vk/auth/main/VkSDKValidationHandlerDecorator;", "Lcom/vk/api/sdk/VKApiValidationHandler;", "decorated", "<init>", "(Lcom/vk/api/sdk/VKApiValidationHandler;)V", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "cb", "", "handleCaptcha", "(Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "", "validationUrl", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "handleValidation", "(Ljava/lang/String;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "confirmationText", "", "handleConfirm", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "ex", "Lcom/vk/api/sdk/VKApiManager;", "apiManager", "tryToHandleException", "(Lcom/vk/api/sdk/exceptions/VKApiExecutionException;Lcom/vk/api/sdk/VKApiManager;)V", "handleCaptchaSolved", "()V", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VkSDKValidationHandlerDecorator implements VKApiValidationHandler {
    public static final int $stable = 8;

    @NotNull
    private final VKApiValidationHandler erochtuakvmoca;

    @NotNull
    private final SdkCaptchaResolver erochtuakvmocb;

    public VkSDKValidationHandlerDecorator(@NotNull VKApiValidationHandler decorated) {
        Intrinsics.checkNotNullParameter(decorated, "decorated");
        this.erochtuakvmoca = decorated;
        this.erochtuakvmocb = new SdkCaptchaResolver();
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptcha(@NotNull VKApiValidationHandler.Captcha captcha, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> cb2) {
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        if (this.erochtuakvmocb.captchaEnabled()) {
            this.erochtuakvmoca.handleCaptcha(captcha, cb2);
            return;
        }
        VKCLogger.INSTANCE.i("VkSDKValidationHandlerDecorator: invoke handleCaptcha, but SdkCaptchaResolver#captchaEnabled return false");
        VKValidationLocker.INSTANCE.signal();
        cb2.cancel();
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptchaSolved() {
        this.erochtuakvmoca.handleCaptchaSolved();
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleConfirm(@NotNull String confirmationText, @NotNull VKApiValidationHandler.Callback<Boolean> cb2) {
        Intrinsics.checkNotNullParameter(confirmationText, "confirmationText");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        this.erochtuakvmoca.handleConfirm(confirmationText, cb2);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleValidation(@NotNull String validationUrl, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.Credentials> cb2) {
        Intrinsics.checkNotNullParameter(validationUrl, "validationUrl");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        this.erochtuakvmoca.handleValidation(validationUrl, cb2);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void tryToHandleException(@NotNull VKApiExecutionException ex, @NotNull VKApiManager apiManager) throws VKApiExecutionException {
        Intrinsics.checkNotNullParameter(ex, "ex");
        Intrinsics.checkNotNullParameter(apiManager, "apiManager");
        this.erochtuakvmoca.tryToHandleException(ex, apiManager);
    }
}
