package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import android.app.Activity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.utils.KeyboardVisibilityHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/KeyboardVisibilityHelperDelegate;", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "keyboardVisibilityListener", "Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;", "getActivity", "Lkotlin/Function0;", "Landroid/app/Activity;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;Lkotlin/jvm/functions/Function0;)V", "getGetActivity", "()Lkotlin/jvm/functions/Function0;", "keyboardHelper", "Lru/mail/ludvig_captcha/utils/KeyboardVisibilityHelper;", "onStateChanged", "", "source", "event", "Landroidx/lifecycle/Lifecycle$Event;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KeyboardVisibilityHelperDelegate implements LifecycleEventObserver {

    @NotNull
    private final Function0<Activity> getActivity;

    @Nullable
    private KeyboardVisibilityHelper keyboardHelper;

    @NotNull
    private final KeyboardVisibilityHelper.KeyboardVisibilityListener keyboardVisibilityListener;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public KeyboardVisibilityHelperDelegate(@NotNull LifecycleOwner lifecycleOwner, @NotNull KeyboardVisibilityHelper.KeyboardVisibilityListener keyboardVisibilityListener, @NotNull Function0<? extends Activity> getActivity) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(keyboardVisibilityListener, "keyboardVisibilityListener");
        Intrinsics.checkNotNullParameter(getActivity, "getActivity");
        this.keyboardVisibilityListener = keyboardVisibilityListener;
        this.getActivity = getActivity;
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @NotNull
    public final Function0<Activity> getGetActivity() {
        return this.getActivity;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NotNull LifecycleOwner source, @NotNull Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(event, "event");
        int i10 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i10 != 1) {
            if (i10 != 2) {
                return;
            }
            KeyboardVisibilityHelper keyboardVisibilityHelper = this.keyboardHelper;
            if (keyboardVisibilityHelper != null) {
                keyboardVisibilityHelper.release();
            }
            this.keyboardHelper = null;
            return;
        }
        Activity activityInvoke = this.getActivity.invoke();
        if (activityInvoke == null) {
            return;
        }
        KeyboardVisibilityHelper keyboardVisibilityHelperFrom = KeyboardVisibilityHelper.INSTANCE.from(activityInvoke);
        this.keyboardHelper = keyboardVisibilityHelperFrom;
        if (keyboardVisibilityHelperFrom != null) {
            keyboardVisibilityHelperFrom.setKeyboardVisibilityListener(this.keyboardVisibilityListener);
        }
    }
}
