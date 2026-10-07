package com.vk.auth.captcha.impl;

import android.content.Context;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.utils.VKValidationLocker;
import com.vk.auth.captcha.api.SakCaptchaHandler;
import com.vk.id.captcha.api.VKCaptcha;
import com.vk.id.captcha.api.data.VKCaptchaResult;
import com.vk.id.captcha.api.listener.VKCaptchaResultListener;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.toggle.anonymous.SakFeatures;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/vk/auth/captcha/impl/SakCaptchaHandlerImpl;", "Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "<init>", "()V", "Landroid/content/Context;", "context", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "captcha", "", "showCaptcha", "(Landroid/content/Context;Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;)V", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "getLastKey", "()Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SakCaptchaHandlerImpl implements SakCaptchaHandler {
    private boolean lpmiahctpackvmoca;
    private boolean lpmiahctpackvmocb;

    @NotNull
    private VKApiValidationHandler.CaptchaResult lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(null, false, true, false, 8, null);

    @Override // com.vk.api.sdk.VKCaptchaHandler
    @Nullable
    public VKApiValidationHandler.CaptchaResult getLastKey() {
        return (this.lpmiahctpackvmoca || this.lpmiahctpackvmocb) ? this.lpmiahctpackvmocc : SakCaptchaFragment.INSTANCE.getCaptchaResult();
    }

    @Override // com.vk.api.sdk.VKCaptchaHandler
    public void showCaptcha(@NotNull Context context, @NotNull VKApiValidationHandler.Captcha captcha) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        String redirectUri = captcha.getRedirectUri();
        String hitmanChallengeUrl = captcha.getHitmanChallengeUrl();
        String hitmanChallengeDomain = captcha.getHitmanChallengeDomain();
        String requestDomain = captcha.getRequestDomain();
        VKCaptcha vKCaptcha = VKCaptcha.INSTANCE;
        vKCaptcha.setLocale(new Locale(SuperappApiCore.INSTANCE.getApiManager().getConfig().getLang()));
        if (hitmanChallengeUrl == null || hitmanChallengeDomain == null || !SakFeatures.Type.VKC_HITMAN_CAPTCHA_ANDROID.hasFeatureEnabled()) {
            if (redirectUri == null || requestDomain == null) {
                this.lpmiahctpackvmoca = false;
                SakCaptchaActivity.INSTANCE.start(context, captcha);
                return;
            } else {
                this.lpmiahctpackvmoca = true;
                vKCaptcha.openCaptcha(requestDomain, redirectUri, new VKCaptchaResultListener() { // from class: com.vk.auth.captcha.impl.SakCaptchaHandlerImpl.showCaptcha.1
                    @Override // com.vk.id.captcha.api.listener.VKCaptchaResultListener
                    public void onResult(VKCaptchaResult result) {
                        Intrinsics.checkNotNullParameter(result, "result");
                        if (result instanceof VKCaptchaResult.Success) {
                            SakCaptchaHandlerImpl.this.lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(((VKCaptchaResult.Success) result).getToken(), false, true, false);
                            VKValidationLocker.INSTANCE.signal();
                        } else {
                            if (!(result instanceof VKCaptchaResult.Error)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            SakCaptchaHandlerImpl.this.lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(null, false, true, false);
                            VKValidationLocker.INSTANCE.signal();
                        }
                    }
                });
                return;
            }
        }
        this.lpmiahctpackvmocb = true;
        String token = vKCaptcha.getToken(hitmanChallengeDomain);
        if (token == null) {
            vKCaptcha.openCaptcha(hitmanChallengeDomain, hitmanChallengeDomain.concat(hitmanChallengeUrl), new VKCaptchaResultListener() { // from class: com.vk.auth.captcha.impl.SakCaptchaHandlerImpl$startHitmanChallenge$1
                @Override // com.vk.id.captcha.api.listener.VKCaptchaResultListener
                public void onResult(VKCaptchaResult result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    if (result instanceof VKCaptchaResult.Success) {
                        this.lpmiahctpackvmoca.lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(((VKCaptchaResult.Success) result).getToken(), false, false, true);
                        VKValidationLocker.INSTANCE.signal();
                    } else {
                        if (!(result instanceof VKCaptchaResult.Error)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        this.lpmiahctpackvmoca.lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(null, false, false, true);
                        VKValidationLocker.INSTANCE.signal();
                    }
                }
            });
        } else {
            this.lpmiahctpackvmocc = new VKApiValidationHandler.CaptchaResult(token, false, false, true);
            VKValidationLocker.INSTANCE.signal();
        }
    }
}
