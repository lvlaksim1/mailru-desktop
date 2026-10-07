package com.vk.superapp.api.generated.captcha;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.core.ApiMethodCall;
import com.vk.api.generated.core.ApiResponseParser;
import com.vk.api.generated.core.ApiStreamResponseParser;
import com.vk.api.generated.core.RootResponseDto;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.superapp.api.generated.GsonHolder;
import com.vk.superapp.api.generated.InternalApiMethodCall;
import com.vk.superapp.api.generated.SingleRootResponseDto;
import d.detarenegipakvmoca;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u008b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/superapp/api/generated/captcha/CaptchaService;", "", "captchaForce", "Lcom/vk/api/generated/core/ApiMethodCall;", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "captchaSid", "", "captchaKey", "isSoundCaptcha", "", "uiuxChanges", "isRefreshEnabled", "needAdaptiveCaptcha", "isSoundCaptchaAvailable", "ui", "notRobotCaptcha", "successToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CaptchaService {
    @NotNull
    ApiMethodCall<BaseOkResponseDto> captchaForce(@Nullable String captchaSid, @Nullable String captchaKey, @Nullable Integer isSoundCaptcha, @Nullable Integer uiuxChanges, @Nullable Integer isRefreshEnabled, @Nullable Integer needAdaptiveCaptcha, @Nullable Integer isSoundCaptchaAvailable, @Nullable Integer ui, @Nullable Integer notRobotCaptcha, @Nullable String successToken);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCaptchaService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptchaService.kt\ncom/vk/superapp/api/generated/captcha/CaptchaService$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 GsonExt.kt\ncom/vk/superapp/api/generated/GsonExtKt\n*L\n1#1,88:1\n1#2:89\n45#3,2:90\n49#3,2:92\n*S KotlinDebug\n*F\n+ 1 CaptchaService.kt\ncom/vk/superapp/api/generated/captcha/CaptchaService$DefaultImpls\n*L\n71#1:90,2\n72#1:92,2\n*E\n"})
    public static final class DefaultImpls {
        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> captchaForce(@NotNull CaptchaService captchaService, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, @Nullable Integer num6, @Nullable Integer num7, @Nullable String str3) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("captcha.force", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.captcha.a
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return CaptchaService.DefaultImpls.detarenegipakvmoca(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.captcha.b
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return CaptchaService.DefaultImpls.detarenegipakvmoca(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_SID, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_KEY, str2, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_IS_SOUND, num.intValue(), 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "uiux_changes", num2.intValue(), 0, 0, 12, (Object) null);
            }
            if (num3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, num3.intValue(), 0, 0, 12, (Object) null);
            }
            if (num4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "need_adaptive_captcha", num4.intValue(), 0, 0, 12, (Object) null);
            }
            if (num5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, num5.intValue(), 0, 0, 12, (Object) null);
            }
            if (num6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "ui", num6.intValue(), 0, 0, 12, (Object) null);
            }
            if (num7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "not_robot_captcha", num7.intValue(), 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, VKApiCodes.EXTRA_CAPTCHA_SUCCESS_TOKEN, str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall captchaForce$default(CaptchaService captchaService, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, String str3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: captchaForce");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            if ((i10 & 8) != 0) {
                num2 = null;
            }
            if ((i10 & 16) != 0) {
                num3 = null;
            }
            if ((i10 & 32) != 0) {
                num4 = null;
            }
            if ((i10 & 64) != 0) {
                num5 = null;
            }
            if ((i10 & 128) != 0) {
                num6 = null;
            }
            if ((i10 & 256) != 0) {
                num7 = null;
            }
            if ((i10 & 512) != 0) {
                str3 = null;
            }
            return captchaService.captchaForce(str, str2, num, num2, num3, num4, num5, num6, num7, str3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmoca(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoca(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }
    }
}
