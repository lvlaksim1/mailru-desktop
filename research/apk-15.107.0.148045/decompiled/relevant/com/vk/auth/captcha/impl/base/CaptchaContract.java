package com.vk.auth.captcha.impl.base;

import com.vk.pushme.logic.PendingAction;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaContract;", "", "CaptchaStatusCallback", "Presenter", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CaptchaContract {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;", "", "onCaptchaStatusChanged", "", "status", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface CaptchaStatusCallback {
        void onCaptchaStatusChanged(@NotNull CaptchaStatus status);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000f"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaContract$Presenter;", "", "activate", "", "addSwapType", "", "deactivate", "refresh", "check", "input", "", PendingAction.SUBSCRIBE_TYPE, "callback", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;", PendingAction.UNSUBSCRIBE_TYPE, "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Presenter {
        void activate(boolean addSwapType);

        void check(@NotNull String input);

        void deactivate();

        void refresh();

        void subscribe(@NotNull CaptchaStatusCallback callback);

        void unsubscribe();
    }
}
