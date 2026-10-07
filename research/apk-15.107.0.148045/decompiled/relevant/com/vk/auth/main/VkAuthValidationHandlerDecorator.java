package com.vk.auth.main;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.utils.VKValidationLocker;
import com.vk.auth.captcha.api.SakCaptchaHandler;
import com.vk.auth.captcha.api.di.CaptchaComponent;
import com.vk.auth.internal.AuthLibBridge;
import com.vk.di.api.ComponentConsumer;
import com.vk.di.context.DiContextKt;
import com.vk.registration.funnels.RegistrationFunnel;
import com.vk.superapp.core.utils.ThreadUtils;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00102\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00120\bH\u0096\u0001¢\u0006\u0004\b\u0013\u0010\u0014J&\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00102\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00160\bH\u0096\u0001¢\u0006\u0004\b\u0017\u0010\u0014J \u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0096\u0001¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lcom/vk/auth/main/VkAuthValidationHandlerDecorator;", "Lcom/vk/di/api/ComponentConsumer;", "Lcom/vk/api/sdk/VKApiValidationHandler;", "decorated", "<init>", "(Lcom/vk/api/sdk/VKApiValidationHandler;)V", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "cb", "", "handleCaptcha", "(Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "handleCaptchaSolved", "()V", "", "validationUrl", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "handleValidation", "(Ljava/lang/String;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", "confirmationText", "", "handleConfirm", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "ex", "Lcom/vk/api/sdk/VKApiManager;", "apiManager", "tryToHandleException", "(Lcom/vk/api/sdk/exceptions/VKApiExecutionException;Lcom/vk/api/sdk/VKApiManager;)V", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VkAuthValidationHandlerDecorator implements ComponentConsumer, VKApiValidationHandler {
    public static final int $stable = 8;

    @NotNull
    private final VKApiValidationHandler erochtuakvmoca;

    @NotNull
    private final Lazy erochtuakvmocb;

    public VkAuthValidationHandlerDecorator(@NotNull VKApiValidationHandler decorated) {
        Intrinsics.checkNotNullParameter(decorated, "decorated");
        this.erochtuakvmoca = decorated;
        this.erochtuakvmocb = LazyKt.lazy(new Function0() { // from class: com.vk.auth.main.r
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkAuthValidationHandlerDecorator.erochtuakvmoca(this.f40799a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SakCaptchaHandler erochtuakvmoca(VkAuthValidationHandlerDecorator vkAuthValidationHandlerDecorator) {
        return ((CaptchaComponent) DiContextKt.getWeakRefDiContext(vkAuthValidationHandlerDecorator).mo12517obtainComponent(Reflection.getOrCreateKotlinClass(CaptchaComponent.class))).getSakCaptchaHandler();
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptcha(@NotNull VKApiValidationHandler.Captcha captcha, @NotNull final VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> cb2) {
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKApiValidationHandler vKApiValidationHandler = this.erochtuakvmoca;
        if (vKApiValidationHandler instanceof VkAuthValidationHandlerDecorator) {
            ((VkAuthValidationHandlerDecorator) vKApiValidationHandler).handleCaptcha(captcha, cb2);
            return;
        }
        ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.auth.main.q
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkAuthValidationHandlerDecorator.erochtuakvmoca();
            }
        }, 1, null);
        final VKApiValidationHandler.ValidationLock lock = cb2.getLock();
        VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> callback = new VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult>(lock) { // from class: com.vk.auth.main.VkAuthValidationHandlerDecorator$handleCaptcha$wrappedCallback$1
            @Override // com.vk.api.sdk.VKApiValidationHandler.Callback
            public void cancel() {
                cb2.cancel();
                RegistrationFunnel.INSTANCE.onReturnFromCaptcha(false);
            }

            @Override // com.vk.api.sdk.VKApiValidationHandler.Callback
            public void submit(VKApiValidationHandler.CaptchaResult value) {
                Intrinsics.checkNotNullParameter(value, "value");
                cb2.submit(value);
                RegistrationFunnel.INSTANCE.onReturnFromCaptcha(true);
            }
        };
        ((SakCaptchaHandler) this.erochtuakvmocb.getValue()).showCaptcha(AuthLibBridge.INSTANCE.getAppContext(), captcha);
        VKValidationLocker.INSTANCE.await();
        VKApiValidationHandler.CaptchaResult lastKey = ((SakCaptchaHandler) this.erochtuakvmocb.getValue()).getLastKey();
        if ((lastKey != null ? lastKey.getKey() : null) == null) {
            callback.cancel();
            return;
        }
        VKApiValidationHandler.CaptchaResult lastKey2 = ((SakCaptchaHandler) this.erochtuakvmocb.getValue()).getLastKey();
        Intrinsics.checkNotNull(lastKey2);
        callback.submit(lastKey2);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptchaSolved() {
        RegistrationFunnel.INSTANCE.onCaptchaSolved();
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit erochtuakvmoca() {
        RegistrationFunnel.INSTANCE.onProceedToCaptcha();
        return Unit.INSTANCE;
    }
}
