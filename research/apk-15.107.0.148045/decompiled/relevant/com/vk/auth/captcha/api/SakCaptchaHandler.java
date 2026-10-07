package com.vk.auth.captcha.api;

import android.content.Context;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.VKCaptchaHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "Lcom/vk/api/sdk/VKCaptchaHandler;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SakCaptchaHandler extends VKCaptchaHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.ipaahctpackvmoca;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/auth/captcha/api/SakCaptchaHandler$Companion;", "", "<init>", "()V", "STUB", "Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "getSTUB$api_release", "()Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion ipaahctpackvmoca = new Companion();

        @NotNull
        private static final SakCaptchaHandler STUB = new SakCaptchaHandler() { // from class: com.vk.auth.captcha.api.SakCaptchaHandler$Companion$STUB$1
            @Override // com.vk.api.sdk.VKCaptchaHandler
            public VKApiValidationHandler.CaptchaResult getLastKey() {
                return null;
            }

            @Override // com.vk.api.sdk.VKCaptchaHandler
            public void showCaptcha(Context context, VKApiValidationHandler.Captcha captcha) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(captcha, "captcha");
            }
        };

        private Companion() {
        }

        @NotNull
        public final SakCaptchaHandler getSTUB$api_release() {
            return STUB;
        }
    }
}
