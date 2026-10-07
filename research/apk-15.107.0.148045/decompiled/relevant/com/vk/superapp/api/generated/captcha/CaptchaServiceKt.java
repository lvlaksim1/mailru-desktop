package com.vk.superapp.api.generated.captcha;

import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.core.ApiMethodCall;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"CaptchaService", "Lcom/vk/superapp/api/generated/captcha/CaptchaService;", "api-generated_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CaptchaServiceKt {
    @NotNull
    public static final CaptchaService CaptchaService() {
        return new CaptchaService() { // from class: com.vk.superapp.api.generated.captcha.CaptchaServiceKt.CaptchaService.1
            @Override // com.vk.superapp.api.generated.captcha.CaptchaService
            public ApiMethodCall<BaseOkResponseDto> captchaForce(String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, String str3) {
                return CaptchaService.DefaultImpls.captchaForce(this, str, str2, num, num2, num3, num4, num5, num6, num7, str3);
            }
        };
    }
}
