package com.vk.id.captcha.api.listener;

import com.vk.id.captcha.api.data.VKCaptchaResult;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;", "", "Lcom/vk/id/captcha/api/data/VKCaptchaResult;", "result", "", "onResult", "(Lcom/vk/id/captcha/api/data/VKCaptchaResult;)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface VKCaptchaResultListener {
    void onResult(@NotNull VKCaptchaResult result);
}
