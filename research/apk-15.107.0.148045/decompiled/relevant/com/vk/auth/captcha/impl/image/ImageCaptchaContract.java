package com.vk.auth.captcha.impl.image;

import android.view.View;
import com.vk.auth.captcha.impl.base.CaptchaContract;
import com.vk.core.ui.image.VKImageController;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaContract;", "", "Presenter", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ImageCaptchaContract {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaContract$Presenter;", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$Presenter;", "setImageLoadController", "", "imageLoadController", "Lcom/vk/core/ui/image/VKImageController;", "Landroid/view/View;", "updateImageCaptchaUrl", "width", "", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Presenter extends CaptchaContract.Presenter {
        void setImageLoadController(@NotNull VKImageController<? extends View> imageLoadController);

        void updateImageCaptchaUrl(int width);
    }
}
