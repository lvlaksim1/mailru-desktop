package com.vk.auth.captcha.impl.image;

import android.net.Uri;
import android.view.View;
import com.vk.auth.captcha.impl.base.BaseCaptchaPresenter;
import com.vk.auth.captcha.impl.base.CaptchaStatus;
import com.vk.auth.captcha.impl.utils.UriUtilsKt;
import com.vk.core.extensions.UriExtKt;
import com.vk.core.ui.image.VKImageController;
import com.vk.core.ui.image.VKImageOnLoadCallback;
import com.vk.superapp.core.utils.VKCLogger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u001cB%\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001b\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaPresenter;", "Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter;", "Lcom/vk/auth/captcha/impl/image/ImageCaptchaContract$Presenter;", "Lcom/vk/core/ui/image/VKImageController$ImageParams;", "imageParams", "", "baseImageCaptchaUri", "Lkotlin/Function0;", "", "dialogCancel", "<init>", "(Lcom/vk/core/ui/image/VKImageController$ImageParams;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "Lcom/vk/core/ui/image/VKImageController;", "Landroid/view/View;", "imageLoadController", "setImageLoadController", "(Lcom/vk/core/ui/image/VKImageController;)V", "", "width", "updateImageCaptchaUrl", "(I)V", "", "addSwapType", "activate", "(Z)V", "deactivate", "()V", "refresh", "ImageCaptchaOnLoadCallback", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ImageCaptchaPresenter extends BaseCaptchaPresenter implements ImageCaptchaContract.Presenter {

    @NotNull
    private final VKImageController.ImageParams lpmiahctpackvmoce;

    @NotNull
    private String lpmiahctpackvmocf;

    @NotNull
    private final Function0<Unit> lpmiahctpackvmocg;

    @Nullable
    private VKImageController<? extends View> lpmiahctpackvmoch;

    @NotNull
    private final Lazy lpmiahctpackvmoci;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\f\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaPresenter$ImageCaptchaOnLoadCallback;", "Lcom/vk/core/ui/image/VKImageOnLoadCallback;", "Lkotlin/Function1;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "setImageCaptchaStatus", "<init>", "(Lcom/vk/auth/captcha/impl/image/ImageCaptchaPresenter;Lkotlin/jvm/functions/Function1;)V", "onSuccess", "()V", "", "throwable", "onFailure", "(Ljava/lang/Throwable;)V", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class ImageCaptchaOnLoadCallback implements VKImageOnLoadCallback {

        @NotNull
        private final Function1<CaptchaStatus, Unit> lpmiahctpackvmoca;
        final /* synthetic */ ImageCaptchaPresenter lpmiahctpackvmocb;

        /* JADX WARN: Multi-variable type inference failed */
        public ImageCaptchaOnLoadCallback(@NotNull ImageCaptchaPresenter imageCaptchaPresenter, Function1<? super CaptchaStatus, Unit> setImageCaptchaStatus) {
            Intrinsics.checkNotNullParameter(setImageCaptchaStatus, "setImageCaptchaStatus");
            this.lpmiahctpackvmocb = imageCaptchaPresenter;
            this.lpmiahctpackvmoca = setImageCaptchaStatus;
        }

        @Override // com.vk.core.ui.image.VKImageOnLoadCallback
        public void onFailure(@Nullable Throwable throwable) {
            this.lpmiahctpackvmoca.invoke(new CaptchaStatus.LoadingError(this.lpmiahctpackvmocb.getRefreshCountdown()));
        }

        @Override // com.vk.core.ui.image.VKImageOnLoadCallback
        public void onSuccess() {
            this.lpmiahctpackvmoca.invoke(new CaptchaStatus.Ready(false, this.lpmiahctpackvmocb.getRefreshCountdown()));
        }
    }

    public ImageCaptchaPresenter(@NotNull VKImageController.ImageParams imageParams, @NotNull String baseImageCaptchaUri, @NotNull Function0<Unit> dialogCancel) {
        Intrinsics.checkNotNullParameter(imageParams, "imageParams");
        Intrinsics.checkNotNullParameter(baseImageCaptchaUri, "baseImageCaptchaUri");
        Intrinsics.checkNotNullParameter(dialogCancel, "dialogCancel");
        this.lpmiahctpackvmoce = imageParams;
        this.lpmiahctpackvmocf = baseImageCaptchaUri;
        this.lpmiahctpackvmocg = dialogCancel;
        this.lpmiahctpackvmoci = LazyKt.lazy(new Function0() { // from class: com.vk.auth.captcha.impl.image.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ImageCaptchaPresenter.lpmiahctpackvmoca(this.f40201a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageCaptchaOnLoadCallback lpmiahctpackvmoca(final ImageCaptchaPresenter imageCaptchaPresenter) {
        return new ImageCaptchaOnLoadCallback(imageCaptchaPresenter, new Function1() { // from class: com.vk.auth.captcha.impl.image.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ImageCaptchaPresenter.lpmiahctpackvmoca(this.f40200a, (CaptchaStatus) obj);
            }
        });
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void activate(boolean addSwapType) {
        Uri.Builder builderBuildUpon = UriExtKt.toUri(this.lpmiahctpackvmocf).buildUpon();
        if (addSwapType) {
            Intrinsics.checkNotNull(builderBuildUpon);
            UriUtilsKt.querySwapType(builderBuildUpon);
        } else {
            Intrinsics.checkNotNull(builderBuildUpon);
            UriUtilsKt.queryFirst(builderBuildUpon);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        lpmiahctpackvmoca(uriBuild, addSwapType);
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void deactivate() {
        getRefreshTimer().cancel();
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void refresh() {
        Uri.Builder builderBuildUpon = UriExtKt.toUri(this.lpmiahctpackvmocf).buildUpon();
        Intrinsics.checkNotNull(builderBuildUpon);
        UriUtilsKt.queryRefresh(builderBuildUpon);
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        lpmiahctpackvmoca(uriBuild, true);
    }

    @Override // com.vk.auth.captcha.impl.image.ImageCaptchaContract.Presenter
    public void setImageLoadController(@NotNull VKImageController<? extends View> imageLoadController) {
        Intrinsics.checkNotNullParameter(imageLoadController, "imageLoadController");
        this.lpmiahctpackvmoch = imageLoadController;
    }

    @Override // com.vk.auth.captcha.impl.image.ImageCaptchaContract.Presenter
    public void updateImageCaptchaUrl(int width) {
        Uri.Builder builderBuildUpon = Uri.parse(this.lpmiahctpackvmocf).buildUpon();
        Intrinsics.checkNotNullExpressionValue(builderBuildUpon, "buildUpon(...)");
        String string = UriUtilsKt.queryWidth(builderBuildUpon, width).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "let(...)");
        this.lpmiahctpackvmocf = string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lpmiahctpackvmoca(ImageCaptchaPresenter imageCaptchaPresenter, CaptchaStatus newCaptchaStatus) {
        Intrinsics.checkNotNullParameter(newCaptchaStatus, "newCaptchaStatus");
        imageCaptchaPresenter.setCaptchaStatus(newCaptchaStatus);
        return Unit.INSTANCE;
    }

    private final void lpmiahctpackvmoca(Uri uri, boolean z10) {
        Object objM13123constructorimpl;
        setCaptchaStatus(new CaptchaStatus.Loading(getRefreshCountdown()));
        try {
            Result.Companion companion = Result.INSTANCE;
            VKImageController<? extends View> vKImageController = this.lpmiahctpackvmoch;
            Intrinsics.checkNotNull(vKImageController);
            vKImageController.load(uri.toString(), this.lpmiahctpackvmoce, (ImageCaptchaOnLoadCallback) this.lpmiahctpackvmoci.getValue());
            objM13123constructorimpl = Result.m13123constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        if (thM13126exceptionOrNullimpl != null) {
            VKCLogger.INSTANCE.e("SakCaptchaFragment failed load image captcha", thM13126exceptionOrNullimpl);
            this.lpmiahctpackvmocg.invoke();
        }
        if (z10) {
            getRefreshTimer().start();
        }
    }
}
