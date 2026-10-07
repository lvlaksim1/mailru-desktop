package ru.mail.authorizationsdk.feature.customserver.presentation.delegates;

import androidx.compose.runtime.Stable;
import androidx.compose.ui.graphics.ImageBitmap;
import kotlin.Metadata;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Stable
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH&J\b\u0010\u0010\u001a\u00020\u000eH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u001a\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0005¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaProvider;", "", "isNeedShowCaptcha", "Lkotlinx/coroutines/flow/StateFlow;", "", "()Lkotlinx/coroutines/flow/StateFlow;", "isCaptchaLoading", "captchaBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "getCaptchaBitmap", "captchaCode", "", "getCaptchaCode", "onUpdateCaptchaCode", "", "newCaptchaCode", "needNewCaptchaCode", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PikachuCaptchaProvider {
    @NotNull
    StateFlow<ImageBitmap> getCaptchaBitmap();

    @NotNull
    StateFlow<String> getCaptchaCode();

    @NotNull
    StateFlow<Boolean> isCaptchaLoading();

    @NotNull
    StateFlow<Boolean> isNeedShowCaptcha();

    void needNewCaptchaCode();

    void onUpdateCaptchaCode(@NotNull String newCaptchaCode);
}
