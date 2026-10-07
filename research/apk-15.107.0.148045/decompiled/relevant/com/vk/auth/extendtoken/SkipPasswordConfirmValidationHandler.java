package com.vk.auth.extendtoken;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0003\u0010\u0004J%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007H\u0016¢\u0006\u0004\b\u0014\u0010\u0011J\u000f\u0010\u0015\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/vk/auth/extendtoken/SkipPasswordConfirmValidationHandler;", "Lcom/vk/api/sdk/VKApiValidationHandler;", "internalHandler", "<init>", "(Lcom/vk/api/sdk/VKApiValidationHandler;)V", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "cb", "", "handleCaptcha", "(Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "", "validationUrl", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "handleValidation", "(Ljava/lang/String;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "confirmationText", "", "handleConfirm", "handleCaptchaSolved", "()V", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "ex", "Lcom/vk/api/sdk/VKApiManager;", "apiManager", "tryToHandleException", "(Lcom/vk/api/sdk/exceptions/VKApiExecutionException;Lcom/vk/api/sdk/VKApiManager;)V", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SkipPasswordConfirmValidationHandler implements VKApiValidationHandler {
    public static final int $stable = 8;

    @Nullable
    private final VKApiValidationHandler tcennockvkvmoca;

    public SkipPasswordConfirmValidationHandler(@Nullable VKApiValidationHandler vKApiValidationHandler) {
        this.tcennockvkvmoca = vKApiValidationHandler;
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptcha(@NotNull VKApiValidationHandler.Captcha captcha, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> cb2) {
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKApiValidationHandler vKApiValidationHandler = this.tcennockvkvmoca;
        if (vKApiValidationHandler != null) {
            vKApiValidationHandler.handleCaptcha(captcha, cb2);
        }
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptchaSolved() {
        VKApiValidationHandler vKApiValidationHandler = this.tcennockvkvmoca;
        if (vKApiValidationHandler != null) {
            vKApiValidationHandler.handleCaptchaSolved();
        }
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleConfirm(@NotNull String confirmationText, @NotNull VKApiValidationHandler.Callback<Boolean> cb2) {
        Intrinsics.checkNotNullParameter(confirmationText, "confirmationText");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKApiValidationHandler vKApiValidationHandler = this.tcennockvkvmoca;
        if (vKApiValidationHandler != null) {
            vKApiValidationHandler.handleConfirm(confirmationText, cb2);
        }
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleValidation(@NotNull String validationUrl, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.Credentials> cb2) {
        Intrinsics.checkNotNullParameter(validationUrl, "validationUrl");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKApiValidationHandler vKApiValidationHandler = this.tcennockvkvmoca;
        if (vKApiValidationHandler != null) {
            vKApiValidationHandler.handleValidation(validationUrl, cb2);
        }
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void tryToHandleException(@NotNull VKApiExecutionException ex, @NotNull VKApiManager apiManager) throws VKApiExecutionException {
        VKApiValidationHandler vKApiValidationHandler;
        Intrinsics.checkNotNullParameter(ex, "ex");
        Intrinsics.checkNotNullParameter(apiManager, "apiManager");
        if (ex.isPasswordConfirmRequired() || (vKApiValidationHandler = this.tcennockvkvmoca) == null) {
            throw ex;
        }
        vKApiValidationHandler.tryToHandleException(ex, apiManager);
    }
}
