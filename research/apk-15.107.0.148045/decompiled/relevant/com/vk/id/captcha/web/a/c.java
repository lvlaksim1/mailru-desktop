package com.vk.id.captcha.web.a;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import com.vk.id.captcha.R;
import com.vk.id.captcha.api.VKCaptcha;
import com.vk.id.captcha.api.common.InternalVKCaptchaApi;
import com.vk.id.captcha.api.common.MainThread;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@InternalVKCaptchaApi
public final class c extends DialogFragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private ImageView f50832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private ImageView f50833b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private Button f50834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    private a f50835d = a.b.INSTANCE;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(c cVar, View view) {
        Intrinsics.checkNotNullParameter(cVar, "");
        cVar.f50835d = a.c.INSTANCE;
        cVar.a();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(@Nullable DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        a();
    }

    @Override // android.app.DialogFragment
    @NotNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Activity activity = getActivity();
        Intrinsics.checkNotNullExpressionValue(activity, "");
        return new b(activity);
    }

    @Override // android.app.Fragment
    @Nullable
    public final View onCreateView(@Nullable LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Window window;
        View viewInflate = layoutInflater != null ? layoutInflater.inflate(R.layout.vk_captcha_no_internet_fragment, (ViewGroup) null) : null;
        this.f50832a = viewInflate != null ? (ImageView) viewInflate.findViewById(R.id.vkid_logo) : null;
        this.f50833b = viewInflate != null ? (ImageView) viewInflate.findViewById(R.id.antenna) : null;
        this.f50834c = viewInflate != null ? (Button) viewInflate.findViewById(R.id.retry_btn) : null;
        ImageView imageView = this.f50832a;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.logo_vkid);
        }
        ImageView imageView2 = this.f50833b;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.antenna);
        }
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        Button button = this.f50834c;
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.vk.id.captcha.web.a.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    c.a(this.f50836a, view);
                }
            });
        }
        return viewInflate;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@Nullable DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        a();
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public final void onStart() {
        Window window;
        Window window2;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window2 = dialog.getWindow()) != null) {
            window2.setLayout(-1, -2);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 == null || (window = dialog2.getWindow()) == null) {
            return;
        }
        window.setGravity(80);
    }

    @Override // android.app.DialogFragment
    @MainThread
    public final void show(@Nullable FragmentManager fragmentManager, @Nullable String str) {
        if (fragmentManager != null) {
            try {
                fragmentManager.executePendingTransactions();
            } catch (Exception unused) {
                return;
            }
        }
        if ((fragmentManager != null ? fragmentManager.findFragmentByTag(str) : null) != null) {
            return;
        }
        super.show(fragmentManager, str);
    }

    private final void b() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        getActivity().finish();
        if (Build.VERSION.SDK_INT >= 34) {
            getActivity().overrideActivityTransition(1, R.anim.fade_in, R.anim.fade_out);
        } else {
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private final void a() {
        a aVar = this.f50835d;
        a.C0203a c0203a = a.C0203a.INSTANCE;
        if (Intrinsics.areEqual(aVar, c0203a)) {
            return;
        }
        if (Intrinsics.areEqual(aVar, a.c.INSTANCE)) {
            b();
            this.f50835d = c0203a;
            VKCaptcha.INSTANCE.closeCaptcha$captcha_release(com.vk.id.captcha.a.c.INSTANCE);
        } else {
            b();
            VKCaptcha.INSTANCE.closeCaptcha$captcha_release(com.vk.id.captcha.a.C0196a.INSTANCE);
        }
    }
}
