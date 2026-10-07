package com.vk.auth.captcha.impl.base;

import android.os.CountDownTimer;
import com.vk.pushme.logic.PendingAction;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001:\u0001*B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\u0003J\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\bR\"\u0010\u001a\u001a\u00020\u00138\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R*\u0010#\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b8\u0004@DX\u0084\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010)\u001a\u00020$8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006+"}, d2 = {"Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter;", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$Presenter;", "<init>", "()V", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;", "callback", "", PendingAction.SUBSCRIBE_TYPE, "(Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;)V", PendingAction.UNSUBSCRIBE_TYPE, "", "input", "check", "(Ljava/lang/String;)V", "lpmiahctpackvmoca", "Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;", "getCallback", "()Lcom/vk/auth/captcha/impl/base/CaptchaContract$CaptchaStatusCallback;", "setCallback", "", "lpmiahctpackvmocb", "I", "getRefreshCountdown", "()I", "setRefreshCountdown", "(I)V", "refreshCountdown", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "value", "lpmiahctpackvmocc", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "getCaptchaStatus", "()Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "setCaptchaStatus", "(Lcom/vk/auth/captcha/impl/base/CaptchaStatus;)V", "captchaStatus", "Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter$CaptchaTimer;", "lpmiahctpackvmocd", "Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter$CaptchaTimer;", "getRefreshTimer", "()Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter$CaptchaTimer;", "refreshTimer", "CaptchaTimer", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BaseCaptchaPresenter implements CaptchaContract.Presenter {

    @Deprecated
    public static final long REFRESH_CAPTCHA_TIMEOUT = 5000;

    @Deprecated
    public static final long SECOND = 1000;

    /* JADX INFO: renamed from: lpmiahctpackvmoca, reason: from kotlin metadata */
    @Nullable
    private CaptchaContract.CaptchaStatusCallback callback;

    /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
    private int refreshCountdown;

    /* JADX INFO: renamed from: lpmiahctpackvmocc, reason: from kotlin metadata */
    @NotNull
    private CaptchaStatus captchaStatus = new CaptchaStatus.Inactive(this.refreshCountdown);

    /* JADX INFO: renamed from: lpmiahctpackvmocd, reason: from kotlin metadata */
    @NotNull
    private final CaptchaTimer refreshTimer = new CaptchaTimer(new Function1() { // from class: com.vk.auth.captcha.impl.base.a
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return BaseCaptchaPresenter.lpmiahctpackvmoca(this.f40196a, ((Long) obj).longValue());
        }
    });

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter$CaptchaTimer;", "Landroid/os/CountDownTimer;", "Lkotlin/Function1;", "", "", "onTickAction", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "millisUntilFinished", "onTick", "(J)V", "onFinish", "()V", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class CaptchaTimer extends CountDownTimer {

        @NotNull
        private final Function1<Long, Unit> lpmiahctpackvmoca;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public CaptchaTimer(@NotNull Function1<? super Long, Unit> onTickAction) {
            super(5000L, 1000L);
            Intrinsics.checkNotNullParameter(onTickAction, "onTickAction");
            this.lpmiahctpackvmoca = onTickAction;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            this.lpmiahctpackvmoca.invoke(0L);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long millisUntilFinished) {
            this.lpmiahctpackvmoca.invoke(Long.valueOf(millisUntilFinished));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lpmiahctpackvmoca(BaseCaptchaPresenter baseCaptchaPresenter, long j10) {
        baseCaptchaPresenter.getClass();
        int iCeil = (int) Math.ceil(j10 / 1000);
        baseCaptchaPresenter.refreshCountdown = iCeil;
        baseCaptchaPresenter.setCaptchaStatus(baseCaptchaPresenter.captchaStatus.updateCountdown(iCeil));
        return Unit.INSTANCE;
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void check(@NotNull String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (StringsKt.isBlank(input)) {
            return;
        }
        setCaptchaStatus(new CaptchaStatus.Checking(input, this.refreshCountdown));
    }

    @Nullable
    protected final CaptchaContract.CaptchaStatusCallback getCallback() {
        return this.callback;
    }

    @NotNull
    protected final CaptchaStatus getCaptchaStatus() {
        return this.captchaStatus;
    }

    protected final int getRefreshCountdown() {
        return this.refreshCountdown;
    }

    @NotNull
    protected final CaptchaTimer getRefreshTimer() {
        return this.refreshTimer;
    }

    protected final void setCallback(@Nullable CaptchaContract.CaptchaStatusCallback captchaStatusCallback) {
        this.callback = captchaStatusCallback;
    }

    protected final void setCaptchaStatus(@NotNull CaptchaStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.captchaStatus = value;
        CaptchaContract.CaptchaStatusCallback captchaStatusCallback = this.callback;
        if (captchaStatusCallback != null) {
            captchaStatusCallback.onCaptchaStatusChanged(value);
        }
    }

    protected final void setRefreshCountdown(int i10) {
        this.refreshCountdown = i10;
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void subscribe(@NotNull CaptchaContract.CaptchaStatusCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void unsubscribe() {
        this.callback = null;
    }
}
