package com.vk.auth.captcha.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.auth.captcha.impl.utils.CaptchaInstance;
import com.vk.superapp.bridges.SuperappBridgesKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014J\b\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lcom/vk/auth/captcha/impl/SakCaptchaActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "finish", "Companion", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SakCaptchaActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/vk/auth/captcha/impl/SakCaptchaActivity$Companion;", "", "<init>", "()V", "KEY_INIT_URL", "", "KEY_INIT_HEIGHT", "KEY_INIT_WIDTH", "KEY_INIT_RATIO", "KEY_IS_REFRESH_ENABLED", "KEY_CAPTCHA_SID", "KEY_IS_SOUND_CAPTCHA", "KEY_CAPTCHA_TRACK", "KEY_TOKEN", "start", "", "context", "Landroid/content/Context;", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(@NotNull Context context, @NotNull VKApiValidationHandler.Captcha captcha) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(captcha, "captcha");
            Intent intent = new Intent(context, (Class<?>) SakCaptchaActivity.class);
            intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            intent.putExtra("url", captcha.getImg());
            Integer height = captcha.getHeight();
            intent.putExtra("height", height != null ? height.intValue() : -1);
            Integer width = captcha.getWidth();
            intent.putExtra("width", width != null ? width.intValue() : -1);
            intent.putExtra("ratio", captcha.getRatio());
            intent.putExtra(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, captcha.isRefreshEnabled());
            intent.putExtra(VKApiCodes.EXTRA_CAPTCHA_SID, captcha.getCaptchaSid());
            Boolean boolIsSoundCaptcha = captcha.isSoundCaptcha();
            intent.putExtra(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, boolIsSoundCaptcha != null ? boolIsSoundCaptcha.booleanValue() : false);
            String captchaTrack = captcha.getCaptchaTrack();
            if (captchaTrack == null) {
                captchaTrack = "";
            }
            intent.putExtra(VKApiCodes.EXTRA_CAPTCHA_TRACK, captchaTrack);
            String token = captcha.getToken();
            intent.putExtra("captcha_token", token != null ? token : "");
            context.startActivity(intent);
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lpmiahctpackvmoca(SakCaptchaActivity sakCaptchaActivity) {
        sakCaptchaActivity.finish();
        return Unit.INSTANCE;
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(SuperappBridgesKt.getSuperappInternalUi().getSakTheme(SuperappBridgesKt.getSuperappUi()));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        String stringExtra = getIntent().getStringExtra("url");
        Intrinsics.checkNotNull(stringExtra);
        Integer numValueOf = Integer.valueOf(getIntent().getIntExtra("height", -1));
        Integer numValueOf2 = Integer.valueOf(getIntent().getIntExtra("width", -1));
        Double dValueOf = Double.valueOf(getIntent().getDoubleExtra("ratio", -1.0d));
        boolean booleanExtra = getIntent().getBooleanExtra(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, false);
        String stringExtra2 = getIntent().getStringExtra(VKApiCodes.EXTRA_CAPTCHA_SID);
        Intrinsics.checkNotNull(stringExtra2);
        Boolean boolValueOf = Boolean.valueOf(getIntent().getBooleanExtra(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, false));
        String stringExtra3 = getIntent().getStringExtra(VKApiCodes.EXTRA_CAPTCHA_TRACK);
        String str = stringExtra3 == null ? "" : stringExtra3;
        String stringExtra4 = getIntent().getStringExtra("captcha_token");
        SakCaptchaFragment companion = SakCaptchaFragment.INSTANCE.getInstance(new CaptchaInstance(stringExtra, numValueOf, numValueOf2, dValueOf, booleanExtra, stringExtra2, boolValueOf, str, stringExtra4 == null ? "" : stringExtra4));
        companion.setOnCancelListener(new Function0() { // from class: com.vk.auth.captcha.impl.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SakCaptchaActivity.lpmiahctpackvmoca(this.f40194a);
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        companion.show(supportFragmentManager, SakCaptchaFragment.TAG);
    }
}
