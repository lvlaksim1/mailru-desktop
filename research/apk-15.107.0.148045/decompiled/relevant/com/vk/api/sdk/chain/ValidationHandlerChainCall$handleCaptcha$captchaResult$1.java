package com.vk.api.sdk.chain;

import com.vk.api.sdk.VKApiValidationHandler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final /* synthetic */ class ValidationHandlerChainCall$handleCaptcha$captchaResult$1 extends FunctionReferenceImpl implements Function3<VKApiValidationHandler, VKApiValidationHandler.Captcha, VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult>, Unit> {
    public static final ValidationHandlerChainCall$handleCaptcha$captchaResult$1 INSTANCE = new ValidationHandlerChainCall$handleCaptcha$captchaResult$1();

    ValidationHandlerChainCall$handleCaptcha$captchaResult$1() {
        super(3, VKApiValidationHandler.class, "handleCaptcha", "handleCaptcha(Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;Lcom/vk/api/sdk/VKApiValidationHandler$Callback;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(VKApiValidationHandler vKApiValidationHandler, VKApiValidationHandler.Captcha captcha, VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> callback) {
        invoke2(vKApiValidationHandler, captcha, callback);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(VKApiValidationHandler p10, VKApiValidationHandler.Captcha p11, VKApiValidationHandler.Callback<VKApiValidationHandler.CaptchaResult> p12) {
        Intrinsics.checkNotNullParameter(p10, "p0");
        Intrinsics.checkNotNullParameter(p11, "p1");
        Intrinsics.checkNotNullParameter(p12, "p2");
        p10.handleCaptcha(p11, p12);
    }
}
