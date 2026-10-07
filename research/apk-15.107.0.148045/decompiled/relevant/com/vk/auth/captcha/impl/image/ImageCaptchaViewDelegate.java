package com.vk.auth.captcha.impl.image;

import android.content.Context;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.sun.mail.imap.IMAPStore;
import com.vk.api.sdk.utils.VKUtils;
import com.vk.auth.captcha.impl.R;
import com.vk.auth.captcha.impl.base.CaptchaStatus;
import com.vk.auth.captcha.impl.utils.ViewUtilsKt;
import com.vk.auth.ui.VkLoadingButton;
import com.vk.core.extensions.SimpleTextWatcher;
import com.vk.core.ui.bottomsheet.ModalBottomSheet;
import com.vk.core.ui.image.VKImageController;
import com.vk.core.ui.image.VKImageControllerFactory;
import com.vk.core.ui.themes.VKReplacerView;
import com.vk.registration.funnels.RegistrationFunnel;
import com.vk.superapp.bridges.SuperappBridgesKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import ru.mail.appmetricstracker.monitors.battery.BatteryCorrectionRuleDefaults;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaViewDelegate;", "", "Landroid/view/View;", Promotion.ACTION_VIEW, "Lcom/vk/auth/captcha/impl/image/ImageCaptchaArguments;", IMAPStore.ID_ARGUMENTS, "Lcom/vk/auth/captcha/impl/image/ImageCaptchaPresenter;", "presenter", "<init>", "(Landroid/view/View;Lcom/vk/auth/captcha/impl/image/ImageCaptchaArguments;Lcom/vk/auth/captcha/impl/image/ImageCaptchaPresenter;)V", "", "onConfigurationChanged", "()V", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "status", "onStatusChanged", "(Lcom/vk/auth/captcha/impl/base/CaptchaStatus;)V", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nImageCaptchaViewDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ImageCaptchaViewDelegate.kt\ncom/vk/auth/captcha/impl/image/ImageCaptchaViewDelegate\n+ 2 DelegateUtils.kt\ncom/vk/core/util/DelegateUtilsKt\n+ 3 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,215:1\n38#2:216\n38#2:217\n38#2:218\n257#3,2:219\n257#3,2:221\n257#3,2:223\n257#3,2:225\n257#3,2:227\n257#3,2:229\n257#3,2:231\n*S KotlinDebug\n*F\n+ 1 ImageCaptchaViewDelegate.kt\ncom/vk/auth/captcha/impl/image/ImageCaptchaViewDelegate\n*L\n67#1:216\n75#1:217\n79#1:218\n85#1:219,2\n177#1:221,2\n178#1:223,2\n185#1:225,2\n186#1:227,2\n193#1:229,2\n194#1:231,2\n*E\n"})
public final class ImageCaptchaViewDelegate {

    @Deprecated
    public static final float CAPTCHA_DEFAULT_HEIGHT = 50.0f;

    @Deprecated
    public static final float CAPTCHA_DEFAULT_WIDTH = 130.0f;
    static final /* synthetic */ KProperty<Object>[] lpmiahctpackvmocn = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ImageCaptchaViewDelegate.class, "refreshEnabledText", "getRefreshEnabledText()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ImageCaptchaViewDelegate.class, "refreshEnabled", "getRefreshEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ImageCaptchaViewDelegate.class, "switchEnabled", "getSwitchEnabled()Z", 0))};

    @NotNull
    private final View lpmiahctpackvmoca;

    @NotNull
    private final ImageCaptchaArguments lpmiahctpackvmocb;

    @NotNull
    private final ImageCaptchaPresenter lpmiahctpackvmocc;

    @NotNull
    private EditText lpmiahctpackvmocd;

    @NotNull
    private VkLoadingButton lpmiahctpackvmoce;

    @NotNull
    private Button lpmiahctpackvmocf;

    @NotNull
    private View lpmiahctpackvmocg;

    @NotNull
    private View lpmiahctpackvmoch;

    @NotNull
    private View lpmiahctpackvmoci;

    @NotNull
    private View lpmiahctpackvmocj;

    @NotNull
    private final ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$1 lpmiahctpackvmock;

    @NotNull
    private final ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$2 lpmiahctpackvmocl;

    @NotNull
    private final ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 lpmiahctpackvmocm;

    /* JADX WARN: Type inference failed for: r12v0, types: [com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$1] */
    /* JADX WARN: Type inference failed for: r12v1, types: [com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$2] */
    /* JADX WARN: Type inference failed for: r12v2, types: [com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3] */
    public ImageCaptchaViewDelegate(@NotNull View view, @NotNull ImageCaptchaArguments arguments, @NotNull ImageCaptchaPresenter presenter) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        this.lpmiahctpackvmoca = view;
        this.lpmiahctpackvmocb = arguments;
        this.lpmiahctpackvmocc = presenter;
        View viewFindViewById = view.findViewById(R.id.captcha_img);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        VKReplacerView vKReplacerView = (VKReplacerView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.captcha_code);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        EditText editText = (EditText) viewFindViewById2;
        this.lpmiahctpackvmocd = editText;
        View viewFindViewById3 = view.findViewById(R.id.captcha_btn);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        VkLoadingButton vkLoadingButton = (VkLoadingButton) viewFindViewById3;
        this.lpmiahctpackvmoce = vkLoadingButton;
        View viewFindViewById4 = view.findViewById(R.id.captcha_refresh);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        Button button = (Button) viewFindViewById4;
        this.lpmiahctpackvmocf = button;
        View viewFindViewById5 = view.findViewById(R.id.switch_to_sound_captcha);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.lpmiahctpackvmocg = viewFindViewById5;
        View viewFindViewById6 = view.findViewById(R.id.captcha_img_frame);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.lpmiahctpackvmoch = viewFindViewById6;
        View viewFindViewById7 = view.findViewById(R.id.captcha_img_progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        this.lpmiahctpackvmoci = viewFindViewById7;
        View viewFindViewById8 = view.findViewById(R.id.captcha_img_retry);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
        this.lpmiahctpackvmocj = viewFindViewById8;
        final int i10 = 0;
        this.lpmiahctpackvmock = new ObservableProperty<Integer>(i10) { // from class: com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                int iIntValue = newValue.intValue();
                oldValue.intValue();
                if (iIntValue == 0) {
                    this.lpmiahctpackvmocf.setText(this.lpmiahctpackvmoca.getResources().getString(R.string.vk_captcha_refresh));
                } else {
                    this.lpmiahctpackvmocf.setText(this.lpmiahctpackvmoca.getResources().getString(R.string.vk_captcha_refresh_in, Integer.valueOf(iIntValue)));
                }
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        final Boolean bool = Boolean.TRUE;
        this.lpmiahctpackvmocl = new ObservableProperty<Boolean>(bool) { // from class: com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                ViewUtilsKt.setRefreshButtonEnabled(this.lpmiahctpackvmocf, zBooleanValue);
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        this.lpmiahctpackvmocm = new ObservableProperty<Boolean>(bool) { // from class: com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                ViewUtilsKt.setRefreshButtonEnabled(this.lpmiahctpackvmocg, zBooleanValue);
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        button.setVisibility(arguments.isRefreshEnabled() ? 0 : 8);
        double ratio = arguments.getRatio();
        if (ratio > BatteryCorrectionRuleDefaults.CURRENT_MIN) {
            lpmiahctpackvmoca(ratio, true);
        } else {
            float width = 130.0f;
            if (arguments.getWidth() != -1 && arguments.getWidth() <= 130.0f) {
                width = arguments.getWidth();
            }
            VKUtils vKUtils = VKUtils.INSTANCE;
            int iMax = (int) (Math.max(1.0f, vKUtils.density()) * width);
            float height = 50.0f;
            if (arguments.getHeight() != -1 && arguments.getHeight() <= 50.0f) {
                height = arguments.getHeight();
            }
            int iMax2 = (int) (Math.max(1.0f, vKUtils.density()) * height);
            ViewGroup.LayoutParams layoutParams = viewFindViewById6.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = iMax;
            }
            ViewGroup.LayoutParams layoutParams2 = viewFindViewById6.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.height = iMax2;
            }
        }
        VKImageControllerFactory<View> factory = SuperappBridgesKt.getSuperappImage().getFactory();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        VKImageController<? extends View> vKImageControllerCreate = factory.create(context);
        vKReplacerView.replaceWith(vKImageControllerCreate.getView());
        presenter.setImageLoadController(vKImageControllerCreate);
        editText.requestFocus();
        editText.addTextChangedListener(new SimpleTextWatcher() { // from class: com.vk.auth.captcha.impl.image.ImageCaptchaViewDelegate.1
            @Override // com.vk.core.extensions.SimpleTextWatcher, android.text.TextWatcher
            public void onTextChanged(CharSequence s10, int start, int before, int count) {
                Intrinsics.checkNotNullParameter(s10, "s");
                ImageCaptchaViewDelegate.this.lpmiahctpackvmoce.setEnabled(s10.length() > 0);
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.vk.auth.captcha.impl.image.c
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i11, KeyEvent keyEvent) {
                return ImageCaptchaViewDelegate.lpmiahctpackvmoca(this.f40202a, textView, i11, keyEvent);
            }
        });
        Editable text = editText.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        vkLoadingButton.setEnabled(text.length() > 0);
        vkLoadingButton.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.image.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ImageCaptchaViewDelegate.lpmiahctpackvmoca(this.f40203a, view2);
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.image.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ImageCaptchaViewDelegate.lpmiahctpackvmocb(this.f40204a, view2);
            }
        };
        viewFindViewById8.setOnClickListener(onClickListener);
        button.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean lpmiahctpackvmoca(ImageCaptchaViewDelegate imageCaptchaViewDelegate, TextView textView, int i10, KeyEvent keyEvent) {
        if (i10 != 4) {
            return false;
        }
        imageCaptchaViewDelegate.lpmiahctpackvmocc.check(imageCaptchaViewDelegate.lpmiahctpackvmocd.getText().toString());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocb(ImageCaptchaViewDelegate imageCaptchaViewDelegate, View view) {
        RegistrationFunnel.INSTANCE.onCapthaRefresh();
        imageCaptchaViewDelegate.lpmiahctpackvmocc.refresh();
    }

    public final void onConfigurationChanged() {
        double ratio = this.lpmiahctpackvmocb.getRatio();
        if (ratio > BatteryCorrectionRuleDefaults.CURRENT_MIN) {
            lpmiahctpackvmoca(ratio, false);
        }
    }

    public final void onStatusChanged(@NotNull CaptchaStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        if (status instanceof CaptchaStatus.Loading) {
            ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$2 imageCaptchaViewDelegate$special$$inlined$onChangeObservable$2 = this.lpmiahctpackvmocl;
            KProperty<?>[] kPropertyArr = lpmiahctpackvmocn;
            KProperty<?> kProperty = kPropertyArr[1];
            Boolean bool = Boolean.FALSE;
            imageCaptchaViewDelegate$special$$inlined$onChangeObservable$2.setValue(this, kProperty, bool);
            setValue(this, kPropertyArr[2], bool);
            setValue(this, kPropertyArr[0], Integer.valueOf(((CaptchaStatus.Loading) status).getRefreshCountdown()));
            this.lpmiahctpackvmoci.setVisibility(0);
            this.lpmiahctpackvmocj.setVisibility(8);
            return;
        }
        if (status instanceof CaptchaStatus.LoadingError) {
            boolean zIsCountdownStop = status.isCountdownStop();
            ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 imageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr2 = lpmiahctpackvmocn;
            imageCaptchaViewDelegate$special$$inlined$onChangeObservable$3.setValue(this, kPropertyArr2[2], Boolean.valueOf(zIsCountdownStop));
            setValue(this, kPropertyArr2[1], Boolean.FALSE);
            setValue(this, kPropertyArr2[0], Integer.valueOf(((CaptchaStatus.LoadingError) status).getRefreshCountdown()));
            this.lpmiahctpackvmoci.setVisibility(8);
            this.lpmiahctpackvmocj.setVisibility(0);
            return;
        }
        if (status instanceof CaptchaStatus.Ready) {
            boolean zIsCountdownStop2 = status.isCountdownStop();
            ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 imageCaptchaViewDelegate$special$$inlined$onChangeObservable$4 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr3 = lpmiahctpackvmocn;
            imageCaptchaViewDelegate$special$$inlined$onChangeObservable$4.setValue(this, kPropertyArr3[2], Boolean.valueOf(zIsCountdownStop2));
            setValue(this, kPropertyArr3[1], Boolean.valueOf(status.isCountdownStop()));
            setValue(this, kPropertyArr3[0], Integer.valueOf(((CaptchaStatus.Ready) status).getRefreshCountdown()));
            this.lpmiahctpackvmoci.setVisibility(8);
            this.lpmiahctpackvmocj.setVisibility(8);
            return;
        }
        if (status instanceof CaptchaStatus.Checking) {
            ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 imageCaptchaViewDelegate$special$$inlined$onChangeObservable$5 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr4 = lpmiahctpackvmocn;
            KProperty<?> kProperty2 = kPropertyArr4[2];
            Boolean bool2 = Boolean.FALSE;
            imageCaptchaViewDelegate$special$$inlined$onChangeObservable$5.setValue(this, kProperty2, bool2);
            setValue(this, kPropertyArr4[1], bool2);
            setValue(this, kPropertyArr4[0], Integer.valueOf(((CaptchaStatus.Checking) status).getRefreshCountdown()));
            return;
        }
        if (!(status instanceof CaptchaStatus.Inactive)) {
            throw new NoWhenBranchMatchedException();
        }
        ImageCaptchaViewDelegate$special$$inlined$onChangeObservable$3 imageCaptchaViewDelegate$special$$inlined$onChangeObservable$6 = this.lpmiahctpackvmocm;
        KProperty<?>[] kPropertyArr5 = lpmiahctpackvmocn;
        KProperty<?> kProperty3 = kPropertyArr5[2];
        Boolean bool3 = Boolean.FALSE;
        imageCaptchaViewDelegate$special$$inlined$onChangeObservable$6.setValue(this, kProperty3, bool3);
        setValue(this, kPropertyArr5[1], bool3);
        setValue(this, kPropertyArr5[0], Integer.valueOf(((CaptchaStatus.Inactive) status).getRefreshCountdown()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(ImageCaptchaViewDelegate imageCaptchaViewDelegate, View view) {
        imageCaptchaViewDelegate.lpmiahctpackvmocc.check(imageCaptchaViewDelegate.lpmiahctpackvmocd.getText().toString());
    }

    private final void lpmiahctpackvmoca(double d10, boolean z10) {
        float dimension = this.lpmiahctpackvmoca.getResources().getDimension(R.dimen.vk_sak_captcha_fragment_padding) + (this.lpmiahctpackvmoca.getResources().getDimension(R.dimen.vk_sak_captcha_image_horizontal_padding) * 2);
        VKUtils vKUtils = VKUtils.INSTANCE;
        Context context = this.lpmiahctpackvmoca.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int iMin = (int) (Math.min(vKUtils.width(context), ModalBottomSheet.INSTANCE.getMAX_WIDTH()) - dimension);
        int i10 = (int) (((double) iMin) / d10);
        ViewGroup.LayoutParams layoutParams = this.lpmiahctpackvmoch.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = iMin;
        }
        ViewGroup.LayoutParams layoutParams2 = this.lpmiahctpackvmoch.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.height = i10;
        }
        if (z10) {
            this.lpmiahctpackvmocc.updateImageCaptchaUrl(iMin);
        }
    }
}
