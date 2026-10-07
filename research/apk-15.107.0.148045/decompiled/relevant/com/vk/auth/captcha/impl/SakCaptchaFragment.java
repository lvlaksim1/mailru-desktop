package com.vk.auth.captcha.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.FragmentActivity;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.utils.VKValidationLocker;
import com.vk.auth.captcha.impl.base.CaptchaContract;
import com.vk.auth.captcha.impl.base.CaptchaStatus;
import com.vk.auth.captcha.impl.image.ImageCaptchaArguments;
import com.vk.auth.captcha.impl.image.ImageCaptchaPresenter;
import com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate;
import com.vk.auth.captcha.impl.sound.SoundCaptchaPresenter;
import com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate;
import com.vk.auth.captcha.impl.utils.CaptchaInstance;
import com.vk.core.extensions.DesignContextExtKt;
import com.vk.core.ui.bottomsheet.ModalBottomSheet;
import com.vk.core.ui.image.VKImageController;
import com.vk.superapp.api.dto.story.WebStoryAttachment;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.remotelayout.data.dto.ItemDto;
import ru.mail.ui.quickactions.QuickActionOptionProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u0003J\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0012\u0010\u0003J\u0017\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/vk/auth/captcha/impl/SakCaptchaFragment;", "Lcom/vk/core/ui/bottomsheet/ModalBottomSheet;", "<init>", "()V", "", "getTheme", "()I", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/app/Dialog;", "onCreateDialog", "(Landroid/os/Bundle;)Landroid/app/Dialog;", "", "onDestroyView", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "onPause", "Landroid/content/DialogInterface;", ItemDto.KEY_TYPE_DIALOG, "onDismiss", "(Landroid/content/DialogInterface;)V", "Companion", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSakCaptchaFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SakCaptchaFragment.kt\ncom/vk/auth/captcha/impl/SakCaptchaFragment\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,277:1\n257#2,2:278\n*S KotlinDebug\n*F\n+ 1 SakCaptchaFragment.kt\ncom/vk/auth/captcha/impl/SakCaptchaFragment\n*L\n121#1:278,2\n*E\n"})
public final class SakCaptchaFragment extends ModalBottomSheet {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String TAG = "SAK_CAPTCHA";

    @Nullable
    private static VKApiValidationHandler.CaptchaResult lpmiahctpackvmock;

    @Nullable
    private EditText lpmiahctpackvmoca;

    @Nullable
    private LinearLayout lpmiahctpackvmocb;

    @Nullable
    private View lpmiahctpackvmocc;

    @Nullable
    private View lpmiahctpackvmocd;
    private boolean lpmiahctpackvmoce;
    private boolean lpmiahctpackvmocf;

    @Nullable
    private SoundCaptchaViewDelegate lpmiahctpackvmocg;

    @Nullable
    private SoundCaptchaPresenter lpmiahctpackvmoch;

    @Nullable
    private ImageCaptchaPresenter lpmiahctpackvmoci;

    @Nullable
    private ImageCaptchaViewDelegate lpmiahctpackvmocj;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/vk/auth/captcha/impl/SakCaptchaFragment$Companion;", "", "<init>", "()V", "value", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "captchaResult", "getCaptchaResult", "()Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", QuickActionOptionProvider.OPTION_TAG, "", "KEY_INIT_URL", "KEY_INIT_HEIGHT", "KEY_INIT_WIDTH", "KEY_RATIO", "KEY_IS_REFRESH_ENABLED", "KEY_CAPTCHA_SID", "KEY_IS_SOUND_CAPTCHA_AVAILABLE", "KEY_CAPTCHA_TRACK", "KEY_TOKEN", "getInstance", "Lcom/vk/auth/captcha/impl/SakCaptchaFragment;", "captchaInstance", "Lcom/vk/auth/captcha/impl/utils/CaptchaInstance;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final VKApiValidationHandler.CaptchaResult getCaptchaResult() {
            return SakCaptchaFragment.lpmiahctpackvmock;
        }

        @NotNull
        public final SakCaptchaFragment getInstance(@NotNull CaptchaInstance captchaInstance) {
            Intrinsics.checkNotNullParameter(captchaInstance, "captchaInstance");
            SakCaptchaFragment sakCaptchaFragment = new SakCaptchaFragment();
            Bundle bundle = new Bundle(3);
            bundle.putString("url", captchaInstance.getImg());
            Integer height = captchaInstance.getHeight();
            bundle.putInt("height", height != null ? height.intValue() : -1);
            Integer width = captchaInstance.getWidth();
            bundle.putInt("width", width != null ? width.intValue() : -1);
            Double ratio = captchaInstance.getRatio();
            bundle.putDouble("ratio", ratio != null ? ratio.doubleValue() : -1.0d);
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, captchaInstance.isRefreshEnabled());
            bundle.putString(VKApiCodes.EXTRA_CAPTCHA_SID, captchaInstance.getCaptchaSid());
            Boolean boolIsSoundCaptcha = captchaInstance.isSoundCaptcha();
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, boolIsSoundCaptcha != null ? boolIsSoundCaptcha.booleanValue() : false);
            String captchaTrack = captchaInstance.getCaptchaTrack();
            if (captchaTrack == null) {
                captchaTrack = "";
            }
            bundle.putString(VKApiCodes.EXTRA_CAPTCHA_TRACK, captchaTrack);
            String token = captchaInstance.getToken();
            bundle.putString("captcha_token", token != null ? token : "");
            sakCaptchaFragment.setArguments(bundle);
            return sakCaptchaFragment;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class lpmiahctpackvmoca implements CaptchaContract.CaptchaStatusCallback, FunctionAdapter {
        lpmiahctpackvmoca() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof CaptchaContract.CaptchaStatusCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, SakCaptchaFragment.this, SakCaptchaFragment.class, "onImageCaptchaStatusChanged", "onImageCaptchaStatusChanged(Lcom/vk/auth/captcha/impl/base/CaptchaStatus;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaContract.CaptchaStatusCallback
        public final void onCaptchaStatusChanged(CaptchaStatus p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            SakCaptchaFragment.access$onImageCaptchaStatusChanged(SakCaptchaFragment.this, p10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class lpmiahctpackvmocb implements CaptchaContract.CaptchaStatusCallback, FunctionAdapter {
        lpmiahctpackvmocb() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof CaptchaContract.CaptchaStatusCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, SakCaptchaFragment.this, SakCaptchaFragment.class, "onSoundCaptchaStatusChanged", "onSoundCaptchaStatusChanged(Lcom/vk/auth/captcha/impl/base/CaptchaStatus;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaContract.CaptchaStatusCallback
        public final void onCaptchaStatusChanged(CaptchaStatus p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            SakCaptchaFragment.this.lpmiahctpackvmoca(p10);
        }
    }

    public static final void access$onImageCaptchaStatusChanged(SakCaptchaFragment sakCaptchaFragment, CaptchaStatus captchaStatus) {
        if (captchaStatus instanceof CaptchaStatus.Checking) {
            sakCaptchaFragment.getClass();
            lpmiahctpackvmock = new VKApiValidationHandler.CaptchaResult(((CaptchaStatus.Checking) captchaStatus).getInput(), false, false, false, 8, null);
            sakCaptchaFragment.lpmiahctpackvmoce = true;
            VKValidationLocker.INSTANCE.signal();
            Dialog dialog = sakCaptchaFragment.getDialog();
            if (dialog != null) {
                dialog.cancel();
            }
        }
        ImageCaptchaViewDelegate imageCaptchaViewDelegate = sakCaptchaFragment.lpmiahctpackvmocj;
        if (imageCaptchaViewDelegate != null) {
            imageCaptchaViewDelegate.onStatusChanged(captchaStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SakCaptchaFragment sakCaptchaFragment) {
        FragmentActivity activity = sakCaptchaFragment.getActivity();
        Object systemService = activity != null ? activity.getSystemService("input_method") : null;
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(sakCaptchaFragment.lpmiahctpackvmoca, 0);
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0071  */
    private final void lpmiahctpackvmocb(View view) {
        int i10;
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(VKApiCodes.EXTRA_CAPTCHA_TRACK) : null;
        Bundle arguments2 = getArguments();
        String string2 = arguments2 != null ? arguments2.getString("captcha_token") : null;
        if (string == null || string.length() == 0) {
            return;
        }
        FragmentActivity activity = getActivity();
        Object systemService = activity != null ? activity.getSystemService(WebStoryAttachment.WEB_ATTACHMENT_TYPE_AUDIO) : null;
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        SoundCaptchaPresenter soundCaptchaPresenter = new SoundCaptchaPresenter((AudioManager) systemService, string, string2);
        this.lpmiahctpackvmoch = soundCaptchaPresenter;
        soundCaptchaPresenter.subscribe(new lpmiahctpackvmocb());
        SoundCaptchaPresenter soundCaptchaPresenter2 = this.lpmiahctpackvmoch;
        Intrinsics.checkNotNull(soundCaptchaPresenter2);
        this.lpmiahctpackvmocg = new SoundCaptchaViewDelegate(view, soundCaptchaPresenter2);
        View view2 = this.lpmiahctpackvmocd;
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    SakCaptchaFragment.lpmiahctpackvmoca(this.f40195a, view3);
                }
            });
            Bundle arguments3 = getArguments();
            if (arguments3 != null) {
                i10 = !arguments3.getBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, false) ? 8 : 0;
            }
            view2.setVisibility(i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lpmiahctpackvmocc(SakCaptchaFragment sakCaptchaFragment) {
        Dialog dialog = sakCaptchaFragment.getDialog();
        if (dialog != null) {
            dialog.cancel();
        }
        return Unit.INSTANCE;
    }

    @Override // com.vk.core.ui.bottomsheet.ModalBottomSheet, androidx.fragment.app.DialogFragment
    public int getTheme() {
        return R.style.VkIdBModalFloatingBottomSheetTheme;
    }

    @Override // com.vk.core.ui.bottomsheet.ModalBottomSheet, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        ImageCaptchaViewDelegate imageCaptchaViewDelegate = this.lpmiahctpackvmocj;
        if (imageCaptchaViewDelegate != null) {
            imageCaptchaViewDelegate.onConfigurationChanged();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override // com.vk.core.ui.bottomsheet.ModalBottomSheet, androidx.appcompat.app.AppCompatDialogFragment, androidx.fragment.app.DialogFragment
    @NotNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle arguments;
        View viewInflate = LayoutInflater.from(new ContextThemeWrapper(requireContext(), getTheme())).inflate(R.layout.vk_sak_composite_captcha_fragment, (ViewGroup) null, false);
        Intrinsics.checkNotNull(viewInflate);
        ModalBottomSheet.setCustomView$default(this, viewInflate, true, false, 4, null);
        this.lpmiahctpackvmocd = viewInflate.findViewById(R.id.switch_to_sound_captcha);
        this.lpmiahctpackvmoca = (EditText) viewInflate.findViewById(R.id.captcha_code);
        this.lpmiahctpackvmocb = (LinearLayout) viewInflate.findViewById(R.id.captcha_code_layout);
        this.lpmiahctpackvmocc = viewInflate.findViewById(R.id.captcha_img_frame);
        lpmiahctpackvmocb(viewInflate);
        lpmiahctpackvmoca(viewInflate);
        EditText editText = this.lpmiahctpackvmoca;
        if (editText != null) {
            editText.postDelayed(new Runnable() { // from class: com.vk.auth.captcha.impl.c
                @Override // java.lang.Runnable
                public final void run() {
                    SakCaptchaFragment.lpmiahctpackvmoca(this.f40197a);
                }
            }, 100L);
        }
        VKApiValidationHandler.CaptchaResult captchaResult = lpmiahctpackvmock;
        if (captchaResult == null || !captchaResult.isSoundCaptcha() || (arguments = getArguments()) == null || !arguments.getBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, false)) {
            ImageCaptchaPresenter imageCaptchaPresenter = this.lpmiahctpackvmoci;
            if (imageCaptchaPresenter != null) {
                imageCaptchaPresenter.activate(false);
            }
        } else {
            SoundCaptchaPresenter soundCaptchaPresenter = this.lpmiahctpackvmoch;
            if (soundCaptchaPresenter != null) {
                soundCaptchaPresenter.activate(false);
            }
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        SoundCaptchaPresenter soundCaptchaPresenter = this.lpmiahctpackvmoch;
        if (soundCaptchaPresenter != null) {
            soundCaptchaPresenter.unsubscribe();
            soundCaptchaPresenter.deactivate();
        }
        ImageCaptchaPresenter imageCaptchaPresenter = this.lpmiahctpackvmoci;
        if (imageCaptchaPresenter != null) {
            imageCaptchaPresenter.unsubscribe();
            imageCaptchaPresenter.deactivate();
        }
        super.onDestroyView();
    }

    @Override // com.vk.core.ui.bottomsheet.ModalBottomSheet, com.vk.core.ui.bottomsheet.BaseModalDialogFragment, androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NotNull DialogInterface dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        if (!this.lpmiahctpackvmoce) {
            lpmiahctpackvmock = new VKApiValidationHandler.CaptchaResult(null, this.lpmiahctpackvmocf, false, false, 8, null);
        }
        VKValidationLocker.INSTANCE.signal();
        super.onDismiss(dialog);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        SoundCaptchaPresenter soundCaptchaPresenter = this.lpmiahctpackvmoch;
        if (soundCaptchaPresenter != null) {
            soundCaptchaPresenter.pause();
        }
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SakCaptchaFragment sakCaptchaFragment, View view) {
        EditText editText = sakCaptchaFragment.lpmiahctpackvmoca;
        if (editText != null) {
            editText.clearFocus();
        }
        LinearLayout linearLayout = sakCaptchaFragment.lpmiahctpackvmocb;
        if (linearLayout != null) {
            linearLayout.requestFocus();
        }
        SoundCaptchaPresenter soundCaptchaPresenter = sakCaptchaFragment.lpmiahctpackvmoch;
        Intrinsics.checkNotNull(soundCaptchaPresenter);
        soundCaptchaPresenter.activate(true);
    }

    private final void lpmiahctpackvmoca(View view) {
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("url") : null;
        if (string == null || !(!StringsKt.isBlank(string))) {
            return;
        }
        ImageCaptchaArguments imageCaptchaArguments = new ImageCaptchaArguments(string, arguments.getBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED), arguments.getDouble("ratio"), arguments.getInt("width"), arguments.getInt("height"));
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        ImageCaptchaPresenter imageCaptchaPresenter = new ImageCaptchaPresenter(new VKImageController.ImageParams(0.0f, new VKImageController.RoundingParams(12.0f), false, null, 0, null, null, null, null, 2.0f, DesignContextExtKt.resolveColor(contextRequireContext, com.vk.core.ui.design.palette.R.attr.vk_legacy_image_border), null, false, true, null, null, 55805, null), string, new Function0() { // from class: com.vk.auth.captcha.impl.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SakCaptchaFragment.lpmiahctpackvmocc(this.f40199a);
            }
        });
        this.lpmiahctpackvmoci = imageCaptchaPresenter;
        imageCaptchaPresenter.subscribe(new lpmiahctpackvmoca());
        ImageCaptchaPresenter imageCaptchaPresenter2 = this.lpmiahctpackvmoci;
        Intrinsics.checkNotNull(imageCaptchaPresenter2);
        this.lpmiahctpackvmocj = new ImageCaptchaViewDelegate(view, imageCaptchaArguments, imageCaptchaPresenter2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocb(SakCaptchaFragment sakCaptchaFragment) {
        ImageCaptchaPresenter imageCaptchaPresenter = sakCaptchaFragment.lpmiahctpackvmoci;
        if (imageCaptchaPresenter != null) {
            imageCaptchaPresenter.activate(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lpmiahctpackvmoca(CaptchaStatus captchaStatus) {
        if (captchaStatus instanceof CaptchaStatus.Inactive) {
            EditText editText = this.lpmiahctpackvmoca;
            if (editText != null) {
                editText.requestFocus();
            }
            View view = this.lpmiahctpackvmocc;
            if (view != null) {
                view.post(new Runnable() { // from class: com.vk.auth.captcha.impl.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        SakCaptchaFragment.lpmiahctpackvmocb(this.f40198a);
                    }
                });
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.setVolumeControlStream(Integer.MIN_VALUE);
            }
            this.lpmiahctpackvmocf = false;
        } else {
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                activity2.setVolumeControlStream(10);
            }
            this.lpmiahctpackvmocf = true;
        }
        if (captchaStatus instanceof CaptchaStatus.Checking) {
            lpmiahctpackvmock = new VKApiValidationHandler.CaptchaResult(((CaptchaStatus.Checking) captchaStatus).getInput(), true, false, false, 8, null);
            this.lpmiahctpackvmoce = true;
            VKValidationLocker.INSTANCE.signal();
            Dialog dialog = getDialog();
            if (dialog != null) {
                dialog.cancel();
            }
        }
        SoundCaptchaViewDelegate soundCaptchaViewDelegate = this.lpmiahctpackvmocg;
        if (soundCaptchaViewDelegate != null) {
            soundCaptchaViewDelegate.onStatusChanged(captchaStatus);
        }
    }
}
