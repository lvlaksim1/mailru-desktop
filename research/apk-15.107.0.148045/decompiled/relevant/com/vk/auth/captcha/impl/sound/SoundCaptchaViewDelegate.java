package com.vk.auth.captcha.impl.sound;

import android.app.Activity;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.vk.auth.captcha.impl.R;
import com.vk.auth.captcha.impl.base.CaptchaStatus;
import com.vk.auth.captcha.impl.utils.ViewUtilsKt;
import com.vk.core.extensions.ContextExtKt;
import com.vk.core.extensions.DesignViewExtKt;
import com.vk.core.extensions.SimpleTextWatcher;
import com.vk.superapp.utils.VkUiUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/vk/auth/captcha/impl/sound/SoundCaptchaViewDelegate;", "", "Landroid/view/View;", "rootView", "Lcom/vk/auth/captcha/impl/sound/SoundCaptchaContract$Presenter;", "presenter", "<init>", "(Landroid/view/View;Lcom/vk/auth/captcha/impl/sound/SoundCaptchaContract$Presenter;)V", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "status", "", "onStatusChanged", "(Lcom/vk/auth/captcha/impl/base/CaptchaStatus;)V", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSoundCaptchaViewDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SoundCaptchaViewDelegate.kt\ncom/vk/auth/captcha/impl/sound/SoundCaptchaViewDelegate\n+ 2 DelegateUtils.kt\ncom/vk/core/util/DelegateUtilsKt\n*L\n1#1,220:1\n38#2:221\n38#2:222\n38#2:223\n38#2:224\n38#2:225\n38#2:226\n*S KotlinDebug\n*F\n+ 1 SoundCaptchaViewDelegate.kt\ncom/vk/auth/captcha/impl/sound/SoundCaptchaViewDelegate\n*L\n90#1:221\n98#1:222\n102#1:223\n106#1:224\n117#1:225\n127#1:226\n*E\n"})
public final class SoundCaptchaViewDelegate {
    static final /* synthetic */ KProperty<Object>[] lpmiahctpackvmocs = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "soundCaptchaVisible", "getSoundCaptchaVisible()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "refreshEnabled", "getRefreshEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "switchEnabled", "getSwitchEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "refreshEnabledText", "getRefreshEnabledText()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "playEnabled", "getPlayEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoundCaptchaViewDelegate.class, "visibleViewType", "getVisibleViewType()Lcom/vk/auth/captcha/impl/sound/VisibleViewType;", 0))};

    @NotNull
    private final View lpmiahctpackvmoca;

    @NotNull
    private final SoundCaptchaContract.Presenter lpmiahctpackvmocb;

    @NotNull
    private final View lpmiahctpackvmocc;

    @NotNull
    private final View lpmiahctpackvmocd;

    @NotNull
    private final ViewGroup lpmiahctpackvmoce;

    @NotNull
    private final View lpmiahctpackvmocf;

    @NotNull
    private final View lpmiahctpackvmocg;

    @NotNull
    private final View lpmiahctpackvmoch;

    @NotNull
    private final View lpmiahctpackvmoci;

    @NotNull
    private final Button lpmiahctpackvmocj;

    @NotNull
    private final EditText lpmiahctpackvmock;

    @NotNull
    private final View lpmiahctpackvmocl;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 lpmiahctpackvmocm;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2 lpmiahctpackvmocn;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$3 lpmiahctpackvmoco;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$4 lpmiahctpackvmocp;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$5 lpmiahctpackvmocq;

    @NotNull
    private final SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$6 lpmiahctpackvmocr;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[lpmiahctpackvmoca.lpmiahctpackvmoca().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                lpmiahctpackvmoca lpmiahctpackvmocaVar = lpmiahctpackvmoca.PROGRESS_BAR;
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                lpmiahctpackvmoca lpmiahctpackvmocaVar2 = lpmiahctpackvmoca.PROGRESS_BAR;
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$6] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$3] */
    /* JADX WARN: Type inference failed for: r2v9, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$5] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$4] */
    public SoundCaptchaViewDelegate(@NotNull View rootView, @NotNull SoundCaptchaContract.Presenter presenter) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        this.lpmiahctpackvmoca = rootView;
        this.lpmiahctpackvmocb = presenter;
        View viewFindViewById = rootView.findViewById(R.id.sound_captcha_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.lpmiahctpackvmocc = viewFindViewById;
        View viewFindViewById2 = rootView.findViewById(R.id.captcha_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.lpmiahctpackvmocd = viewFindViewById2;
        View viewFindViewById3 = rootView.findViewById(R.id.sound_captcha_player);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        this.lpmiahctpackvmoce = (ViewGroup) viewFindViewById3;
        View viewFindViewById4 = rootView.findViewById(R.id.sound_captcha_play_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.lpmiahctpackvmocf = viewFindViewById4;
        View viewFindViewById5 = rootView.findViewById(R.id.sound_captcha_retry);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(...)");
        this.lpmiahctpackvmocg = viewFindViewById5;
        View viewFindViewById6 = rootView.findViewById(R.id.sound_captcha_progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(...)");
        this.lpmiahctpackvmoch = viewFindViewById6;
        View viewFindViewById7 = rootView.findViewById(R.id.switch_to_text_captcha);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
        this.lpmiahctpackvmoci = viewFindViewById7;
        View viewFindViewById8 = rootView.findViewById(R.id.sound_captcha_refresh);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
        Button button = (Button) viewFindViewById8;
        this.lpmiahctpackvmocj = button;
        View viewFindViewById9 = rootView.findViewById(R.id.sound_captcha_code);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
        EditText editText = (EditText) viewFindViewById9;
        this.lpmiahctpackvmock = editText;
        View viewFindViewById10 = rootView.findViewById(R.id.sound_captcha_btn);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
        this.lpmiahctpackvmocl = viewFindViewById10;
        View viewFindViewById11 = rootView.findViewById(R.id.sound_captcha_hint);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(...)");
        viewFindViewById7.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.sound.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SoundCaptchaViewDelegate.lpmiahctpackvmoca(this.f40212a, view);
            }
        });
        viewFindViewById5.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.sound.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SoundCaptchaViewDelegate.lpmiahctpackvmocb(this.f40213a, view);
            }
        });
        VkUiUtils.setRippleDrawableBackground$default(VkUiUtils.INSTANCE, button, 0.0f, 1, null);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.sound.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SoundCaptchaViewDelegate.lpmiahctpackvmocc(this.f40214a, view);
            }
        });
        viewFindViewById4.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.sound.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SoundCaptchaViewDelegate.lpmiahctpackvmocd(this.f40215a, view);
            }
        });
        viewFindViewById10.setOnClickListener(new View.OnClickListener() { // from class: com.vk.auth.captcha.impl.sound.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SoundCaptchaViewDelegate.lpmiahctpackvmoce(this.f40216a, view);
            }
        });
        editText.addTextChangedListener(new SimpleTextWatcher() { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate.6
            @Override // com.vk.core.extensions.SimpleTextWatcher, android.text.TextWatcher
            public void onTextChanged(CharSequence s10, int start, int before, int count) {
                Intrinsics.checkNotNullParameter(s10, "s");
                SoundCaptchaViewDelegate.this.lpmiahctpackvmocl.setEnabled(s10.length() > 0);
            }
        });
        Editable text = editText.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        viewFindViewById10.setEnabled(text.length() > 0);
        final Boolean bool = Boolean.FALSE;
        this.lpmiahctpackvmocm = new ObservableProperty<Boolean>(bool) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                if (zBooleanValue) {
                    this.lpmiahctpackvmoca();
                } else {
                    SoundCaptchaViewDelegate.access$hideSoundCaptcha(this);
                }
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        final Boolean bool2 = Boolean.TRUE;
        this.lpmiahctpackvmocn = new ObservableProperty<Boolean>(bool2) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                ViewUtilsKt.setRefreshButtonEnabled(this.lpmiahctpackvmocj, zBooleanValue);
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        this.lpmiahctpackvmoco = new ObservableProperty<Boolean>(bool2) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                ViewUtilsKt.setRefreshButtonEnabled(this.lpmiahctpackvmoci, zBooleanValue);
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        final int i10 = 0;
        this.lpmiahctpackvmocp = new ObservableProperty<Integer>(i10) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                int iIntValue = newValue.intValue();
                oldValue.intValue();
                if (iIntValue == 0) {
                    this.lpmiahctpackvmocj.setContentDescription(this.lpmiahctpackvmoca.getContext().getString(R.string.vk_sound_captcha_new_refresh_content_description));
                    this.lpmiahctpackvmocj.setText(this.lpmiahctpackvmoca.getContext().getString(R.string.vk_captcha_refresh));
                } else {
                    this.lpmiahctpackvmocj.setContentDescription(this.lpmiahctpackvmoca.getContext().getString(R.string.vk_sound_captcha_new_refresh_in_content_description, Integer.valueOf(iIntValue)));
                    this.lpmiahctpackvmocj.setText(this.lpmiahctpackvmoca.getContext().getString(R.string.vk_captcha_refresh_in, Integer.valueOf(iIntValue)));
                }
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Integer oldValue, Integer newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        this.lpmiahctpackvmocq = new ObservableProperty<Boolean>(bool2) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                boolean zBooleanValue = newValue.booleanValue();
                oldValue.getClass();
                if (zBooleanValue) {
                    this.lpmiahctpackvmoce.setEnabled(true);
                    this.lpmiahctpackvmocf.setAlpha(1.0f);
                } else {
                    this.lpmiahctpackvmoce.setEnabled(false);
                    this.lpmiahctpackvmocf.setAlpha(0.64f);
                }
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        final lpmiahctpackvmoca lpmiahctpackvmocaVar = lpmiahctpackvmoca.PROGRESS_BAR;
        this.lpmiahctpackvmocr = new ObservableProperty<lpmiahctpackvmoca>(lpmiahctpackvmocaVar) { // from class: com.vk.auth.captcha.impl.sound.SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, lpmiahctpackvmoca oldValue, lpmiahctpackvmoca newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                int i11 = SoundCaptchaViewDelegate.WhenMappings.$EnumSwitchMapping$0[newValue.ordinal()];
                if (i11 == 1) {
                    this.lpmiahctpackvmoch.setVisibility(0);
                    this.lpmiahctpackvmocg.setVisibility(8);
                    this.lpmiahctpackvmoce.setVisibility(8);
                } else if (i11 == 2) {
                    this.lpmiahctpackvmoch.setVisibility(8);
                    this.lpmiahctpackvmocg.setVisibility(0);
                    this.lpmiahctpackvmoce.setVisibility(8);
                } else {
                    if (i11 != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    this.lpmiahctpackvmoch.setVisibility(8);
                    this.lpmiahctpackvmocg.setVisibility(8);
                    this.lpmiahctpackvmoce.setVisibility(0);
                }
            }

            @Override // kotlin.properties.ObservableProperty
            protected boolean beforeChange(KProperty<?> property, lpmiahctpackvmoca oldValue, lpmiahctpackvmoca newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                return !Intrinsics.areEqual(oldValue, newValue);
            }
        };
        viewFindViewById4.setContentDescription(rootView.getContext().getString(R.string.vk_sound_captcha_new_play_content_description));
        button.setContentDescription(rootView.getContext().getString(R.string.vk_sound_captcha_new_refresh_content_description));
        DesignViewExtKt.setMarginTop(editText, 0);
        editText.setHint(R.string.vk_sound_captcha_new_input_hint);
        viewFindViewById11.setVisibility(0);
        onStatusChanged(new CaptchaStatus.Inactive(0));
    }

    public static final void access$hideSoundCaptcha(SoundCaptchaViewDelegate soundCaptchaViewDelegate) {
        DesignViewExtKt.setGone(soundCaptchaViewDelegate.lpmiahctpackvmocc);
        DesignViewExtKt.setVisible(soundCaptchaViewDelegate.lpmiahctpackvmocd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SoundCaptchaViewDelegate soundCaptchaViewDelegate, View view) {
        soundCaptchaViewDelegate.lpmiahctpackvmocb.deactivate();
        soundCaptchaViewDelegate.lpmiahctpackvmock.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocb(SoundCaptchaViewDelegate soundCaptchaViewDelegate, View view) {
        soundCaptchaViewDelegate.lpmiahctpackvmocb.retry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocc(SoundCaptchaViewDelegate soundCaptchaViewDelegate, View view) {
        soundCaptchaViewDelegate.lpmiahctpackvmocb.refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocd(SoundCaptchaViewDelegate soundCaptchaViewDelegate, View view) {
        soundCaptchaViewDelegate.lpmiahctpackvmocb.play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoce(SoundCaptchaViewDelegate soundCaptchaViewDelegate, View view) {
        soundCaptchaViewDelegate.lpmiahctpackvmocb.check(soundCaptchaViewDelegate.lpmiahctpackvmock.getText().toString());
    }

    public final void onStatusChanged(@NotNull CaptchaStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        boolean z10 = false;
        if (status instanceof CaptchaStatus.Inactive) {
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr = lpmiahctpackvmocs;
            KProperty<?> kProperty = kPropertyArr[0];
            Boolean bool = Boolean.FALSE;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$1.setValue(this, kProperty, bool);
            setValue(this, kPropertyArr[1], bool);
            setValue(this, kPropertyArr[2], bool);
            setValue(this, kPropertyArr[3], Integer.valueOf(((CaptchaStatus.Inactive) status).getRefreshCountdown()));
            setValue(this, kPropertyArr[5], lpmiahctpackvmoca.PROGRESS_BAR);
            return;
        }
        if (status instanceof CaptchaStatus.LoadingError) {
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$2 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr2 = lpmiahctpackvmocs;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$2.setValue(this, kPropertyArr2[0], Boolean.TRUE);
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$3 = this.lpmiahctpackvmocn;
            KProperty<?> kProperty2 = kPropertyArr2[1];
            Boolean bool2 = Boolean.FALSE;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$3.setValue(this, kProperty2, bool2);
            setValue(this, kPropertyArr2[2], bool2);
            setValue(this, kPropertyArr2[3], Integer.valueOf(((CaptchaStatus.LoadingError) status).getRefreshCountdown()));
            setValue(this, kPropertyArr2[5], lpmiahctpackvmoca.RETRY);
            return;
        }
        if (status instanceof CaptchaStatus.Loading) {
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$4 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr3 = lpmiahctpackvmocs;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$4.setValue(this, kPropertyArr3[0], Boolean.TRUE);
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$5 = this.lpmiahctpackvmocn;
            KProperty<?> kProperty3 = kPropertyArr3[1];
            Boolean bool3 = Boolean.FALSE;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$5.setValue(this, kProperty3, bool3);
            setValue(this, kPropertyArr3[2], bool3);
            setValue(this, kPropertyArr3[3], Integer.valueOf(((CaptchaStatus.Loading) status).getRefreshCountdown()));
            setValue(this, kPropertyArr3[5], lpmiahctpackvmoca.PROGRESS_BAR);
            return;
        }
        if (!(status instanceof CaptchaStatus.Ready)) {
            if (!(status instanceof CaptchaStatus.Checking)) {
                throw new NoWhenBranchMatchedException();
            }
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$6 = this.lpmiahctpackvmocm;
            KProperty<?>[] kPropertyArr4 = lpmiahctpackvmocs;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$6.setValue(this, kPropertyArr4[0], Boolean.TRUE);
            SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$2 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$7 = this.lpmiahctpackvmocn;
            KProperty<?> kProperty4 = kPropertyArr4[1];
            Boolean bool4 = Boolean.FALSE;
            soundCaptchaViewDelegate$special$$inlined$onChangeObservable$7.setValue(this, kProperty4, bool4);
            setValue(this, kPropertyArr4[2], bool4);
            setValue(this, kPropertyArr4[3], Integer.valueOf(((CaptchaStatus.Checking) status).getRefreshCountdown()));
            setValue(this, kPropertyArr4[5], lpmiahctpackvmoca.PLAYER);
            setValue(this, kPropertyArr4[4], bool4);
            return;
        }
        SoundCaptchaViewDelegate$special$$inlined$onChangeObservable$1 soundCaptchaViewDelegate$special$$inlined$onChangeObservable$8 = this.lpmiahctpackvmocm;
        KProperty<?>[] kPropertyArr5 = lpmiahctpackvmocs;
        soundCaptchaViewDelegate$special$$inlined$onChangeObservable$8.setValue(this, kPropertyArr5[0], Boolean.TRUE);
        setValue(this, kPropertyArr5[5], lpmiahctpackvmoca.PLAYER);
        CaptchaStatus.Ready ready = (CaptchaStatus.Ready) status;
        setValue(this, kPropertyArr5[4], Boolean.valueOf(!ready.isPlaying()));
        this.lpmiahctpackvmoce.setEnabled(!ready.isPlaying());
        if (!ready.isPlaying() && status.isCountdownStop()) {
            z10 = true;
        }
        setValue(this, kPropertyArr5[1], Boolean.valueOf(z10));
        setValue(this, kPropertyArr5[2], Boolean.valueOf(status.isCountdownStop()));
        setValue(this, kPropertyArr5[3], Integer.valueOf(ready.getRefreshCountdown()));
        this.lpmiahctpackvmock.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lpmiahctpackvmoca() {
        DesignViewExtKt.setVisible(this.lpmiahctpackvmocc);
        DesignViewExtKt.setGone(this.lpmiahctpackvmocd);
        this.lpmiahctpackvmock.requestFocus();
        this.lpmiahctpackvmock.postDelayed(new Runnable() { // from class: com.vk.auth.captcha.impl.sound.m
            @Override // java.lang.Runnable
            public final void run() {
                SoundCaptchaViewDelegate.lpmiahctpackvmoca(this.f40217a);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SoundCaptchaViewDelegate soundCaptchaViewDelegate) {
        Activity activity = ContextExtKt.getActivity(soundCaptchaViewDelegate.lpmiahctpackvmoca);
        Object systemService = activity != null ? activity.getSystemService("input_method") : null;
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(soundCaptchaViewDelegate.lpmiahctpackvmock, 0);
        }
    }
}
