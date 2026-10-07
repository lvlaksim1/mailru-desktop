package com.vk.api.sdk;

import android.content.Context;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.ui.VKConfirmationActivity;
import com.vk.api.sdk.ui.VKWebViewAuthActivity;
import com.vk.api.sdk.utils.VKValidationLocker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0016\u0010\u0011\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u001e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0016J\u001e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00142\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00180\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/vk/api/sdk/VKDefaultValidationHandler;", "Lcom/vk/api/sdk/VKApiValidationHandler;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "captchaHandler", "Lcom/vk/api/sdk/VKCaptchaHandlerDefaultImp;", "handleCaptcha", "", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "cb", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "checkCaptchaActivity", "handleConfirm", "confirmationText", "", "", "handleValidation", "validationUrl", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class VKDefaultValidationHandler implements VKApiValidationHandler {

    @NotNull
    private final VKCaptchaHandlerDefaultImp captchaHandler;

    @NotNull
    private final Context context;

    public VKDefaultValidationHandler(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.captchaHandler = new VKCaptchaHandlerDefaultImp();
    }

    private final void checkCaptchaActivity(VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> cb2) {
        VKApiValidationHandler.CaptchaResult lastKey = this.captchaHandler.getLastKey();
        if ((lastKey != null ? lastKey.getKey() : null) == null) {
            cb2.cancel();
            return;
        }
        VKApiValidationHandler.CaptchaResult lastKey2 = this.captchaHandler.getLastKey();
        Intrinsics.checkNotNull(lastKey2);
        cb2.submit(lastKey2);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptcha(@NotNull VKApiValidationHandler.Captcha captcha, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> cb2) {
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        this.captchaHandler.showCaptcha(this.context, captcha);
        VKValidationLocker.INSTANCE.await();
        checkCaptchaActivity(cb2);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleCaptchaSolved() {
        VKApiValidationHandler.DefaultImpls.handleCaptchaSolved(this);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleConfirm(@NotNull String confirmationText, @NotNull VKApiValidationHandler.Callback<Boolean> cb2) {
        Intrinsics.checkNotNullParameter(confirmationText, "confirmationText");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKConfirmationActivity.Companion companion = VKConfirmationActivity.INSTANCE;
        companion.setResult(false);
        companion.start(this.context, confirmationText);
        VKValidationLocker.INSTANCE.await();
        cb2.submit(Boolean.valueOf(companion.getResult()));
        companion.setResult(false);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void handleValidation(@NotNull String validationUrl, @NotNull VKApiValidationHandler.Callback<VKApiValidationHandler.Credentials> cb2) {
        Intrinsics.checkNotNullParameter(validationUrl, "validationUrl");
        Intrinsics.checkNotNullParameter(cb2, "cb");
        VKWebViewAuthActivity.Companion companion = VKWebViewAuthActivity.INSTANCE;
        companion.setValidationResult(null);
        companion.startForValidation(this.context, validationUrl);
        VKValidationLocker.INSTANCE.await();
        VKApiValidationHandler.Credentials validationResult = companion.getValidationResult();
        if (validationResult != null) {
            cb2.submit(validationResult);
        } else {
            cb2.cancel();
        }
        companion.setValidationResult(null);
    }

    @Override // com.vk.api.sdk.VKApiValidationHandler
    public void tryToHandleException(@NotNull VKApiExecutionException vKApiExecutionException, @NotNull VKApiManager vKApiManager) throws VKApiExecutionException {
        VKApiValidationHandler.DefaultImpls.tryToHandleException(this, vKApiExecutionException, vKApiManager);
    }
}
