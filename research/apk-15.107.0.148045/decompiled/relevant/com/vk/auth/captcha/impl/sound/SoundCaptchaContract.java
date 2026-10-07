package com.vk.auth.captcha.impl.sound;

import com.vk.auth.captcha.impl.base.CaptchaContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/vk/auth/captcha/impl/sound/SoundCaptchaContract;", "", "Presenter", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SoundCaptchaContract {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, d2 = {"Lcom/vk/auth/captcha/impl/sound/SoundCaptchaContract$Presenter;", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$Presenter;", "retry", "", "play", "pause", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Presenter extends CaptchaContract.Presenter {
        void pause();

        void play();

        void retry();
    }
}
