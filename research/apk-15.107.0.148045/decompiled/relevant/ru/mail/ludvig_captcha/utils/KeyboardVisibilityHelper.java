package ru.mail.ludvig_captcha.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0002\u0012\u0013B\u0013\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "activity", "Landroid/app/Activity;", "<init>", "(Landroid/app/Activity;)V", "windowVisibleDisplayFrame", "Landroid/graphics/Rect;", "lastVisibleDecorViewHeight", "", "isVisible", "", "keyboardVisibilityListener", "Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;", "release", "", "setKeyboardVisibilityListener", "onGlobalLayout", "KeyboardVisibilityListener", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KeyboardVisibilityHelper implements ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private Activity activity;
    private boolean isVisible;

    @Nullable
    private KeyboardVisibilityListener keyboardVisibilityListener;
    private int lastVisibleDecorViewHeight;

    @NotNull
    private final Rect windowVisibleDisplayFrame;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper$Companion;", "", "<init>", "()V", "from", "Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper;", "activity", "Landroid/app/Activity;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KeyboardVisibilityHelper from(@NotNull Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            return new KeyboardVisibilityHelper(activity, null);
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;", "", "onKeyboardShown", "", "onKeyboardHidden", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface KeyboardVisibilityListener {
        void onKeyboardHidden();

        void onKeyboardShown();
    }

    public /* synthetic */ KeyboardVisibilityHelper(Activity activity, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Window window;
        View decorView;
        Activity activity = this.activity;
        if (activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.getWindowVisibleDisplayFrame(this.windowVisibleDisplayFrame);
        int iHeight = this.windowVisibleDisplayFrame.height();
        int i10 = this.lastVisibleDecorViewHeight;
        if (i10 != 0) {
            if (i10 > iHeight + 150) {
                KeyboardVisibilityListener keyboardVisibilityListener = this.keyboardVisibilityListener;
                if (keyboardVisibilityListener != null && keyboardVisibilityListener != null) {
                    keyboardVisibilityListener.onKeyboardShown();
                }
                this.isVisible = true;
            } else if (i10 + 150 < iHeight) {
                KeyboardVisibilityListener keyboardVisibilityListener2 = this.keyboardVisibilityListener;
                if (keyboardVisibilityListener2 != null && keyboardVisibilityListener2 != null) {
                    keyboardVisibilityListener2.onKeyboardHidden();
                }
                this.isVisible = false;
            }
        }
        this.lastVisibleDecorViewHeight = iHeight;
    }

    public final void release() {
        Window window;
        View decorView;
        ViewTreeObserver viewTreeObserver;
        Activity activity = this.activity;
        if (activity != null && (window = activity.getWindow()) != null && (decorView = window.getDecorView()) != null && (viewTreeObserver = decorView.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
        this.activity = null;
    }

    public final void setKeyboardVisibilityListener(@Nullable KeyboardVisibilityListener keyboardVisibilityListener) {
        this.keyboardVisibilityListener = keyboardVisibilityListener;
        if (keyboardVisibilityListener != null) {
            if (this.isVisible) {
                keyboardVisibilityListener.onKeyboardShown();
            } else {
                keyboardVisibilityListener.onKeyboardHidden();
            }
        }
    }

    private KeyboardVisibilityHelper(Activity activity) {
        Window window;
        View decorView;
        ViewTreeObserver viewTreeObserver;
        this.activity = activity;
        this.windowVisibleDisplayFrame = new Rect();
        Activity activity2 = this.activity;
        if (activity2 == null || (window = activity2.getWindow()) == null || (decorView = window.getDecorView()) == null || (viewTreeObserver = decorView.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }
}
