package com.vk.api.sdk.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.vk.api.sdk.R;
import com.vk.api.sdk.VKScheduler;
import com.vk.api.sdk.utils.VKLoader;
import com.vk.api.sdk.utils.VKUtils;
import com.vk.api.sdk.utils.VKValidationLocker;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0002J\b\u0010\u001c\u001a\u00020\u000bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/vk/api/sdk/ui/VKCaptchaActivity;", "Landroid/app/Activity;", "<init>", "()V", "input", "Landroid/widget/EditText;", "image", "Landroid/widget/ImageView;", "progress", "Landroid/widget/ProgressBar;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "finish", "loadImage", "displayImage", "bitmap", "Landroid/graphics/Bitmap;", "captchaDone", "captchaCancelled", "getHeight", "", "getWidth", "getValidSizeFromIntent", "key", "", "defaultValue", "onDestroy", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VKCaptchaActivity extends Activity {
    public static final float CAPTCHA_DEFAULT_HEIGHT = 50.0f;
    public static final float CAPTCHA_DEFAULT_WIDTH = 130.0f;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String KEY_HEIGHT = "key_height";

    @NotNull
    private static final String KEY_URL = "key_url";

    @NotNull
    private static final String KEY_WIDTH = "key_width";

    @Nullable
    private static String lastKey;
    private ImageView image;
    private EditText input;
    private ProgressBar progress;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/vk/api/sdk/ui/VKCaptchaActivity$Companion;", "", "<init>", "()V", "lastKey", "", "getLastKey", "()Ljava/lang/String;", "setLastKey", "(Ljava/lang/String;)V", "CAPTCHA_DEFAULT_HEIGHT", "", "CAPTCHA_DEFAULT_WIDTH", "KEY_URL", "KEY_HEIGHT", "KEY_WIDTH", "start", "", "context", "Landroid/content/Context;", "img", "height", "", "width", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void start$default(Companion companion, Context context, String str, Integer num, Integer num2, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                num = null;
            }
            if ((i10 & 8) != 0) {
                num2 = null;
            }
            companion.start(context, str, num, num2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void start$lambda$0(Context context, String str, Integer num, Integer num2) {
            Intent intentPutExtra = new Intent(context, (Class<?>) VKCaptchaActivity.class).addFlags(SQLiteDatabase.CREATE_IF_NECESSARY).putExtra("key_url", str).putExtra(VKCaptchaActivity.KEY_HEIGHT, num).putExtra(VKCaptchaActivity.KEY_WIDTH, num2);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            context.startActivity(intentPutExtra);
        }

        @Nullable
        public final String getLastKey() {
            return VKCaptchaActivity.lastKey;
        }

        public final void setLastKey(@Nullable String str) {
            VKCaptchaActivity.lastKey = str;
        }

        public final void start(@NotNull final Context context, @NotNull final String img, @Nullable final Integer height, @Nullable final Integer width) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(img, "img");
            VKScheduler.runOnMainThread$default(new Runnable() { // from class: com.vk.api.sdk.ui.f
                @Override // java.lang.Runnable
                public final void run() {
                    VKCaptchaActivity.Companion.start$lambda$0(context, img, height, width);
                }
            }, 0L, 2, null);
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void captchaCancelled() {
        lastKey = null;
        VKValidationLocker.INSTANCE.signal();
        setResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void captchaDone() {
        EditText editText = this.input;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            editText = null;
        }
        lastKey = editText.getText().toString();
        VKValidationLocker.INSTANCE.signal();
        finish();
    }

    private final void displayImage(final Bitmap bitmap) {
        VKScheduler.runOnMainThread$default(new Runnable() { // from class: com.vk.api.sdk.ui.a
            @Override // java.lang.Runnable
            public final void run() {
                VKCaptchaActivity.displayImage$lambda$5(this.f40050a, bitmap);
            }
        }, 0L, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void displayImage$lambda$5(VKCaptchaActivity vKCaptchaActivity, Bitmap bitmap) {
        ImageView imageView = vKCaptchaActivity.image;
        ProgressBar progressBar = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("image");
            imageView = null;
        }
        imageView.setImageBitmap(bitmap);
        ProgressBar progressBar2 = vKCaptchaActivity.progress;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progress");
        } else {
            progressBar = progressBar2;
        }
        progressBar.setVisibility(8);
    }

    private final float getHeight() {
        return getValidSizeFromIntent(KEY_HEIGHT, 50.0f);
    }

    private final float getValidSizeFromIntent(String key, float defaultValue) {
        int intExtra = getIntent().getIntExtra(key, -1);
        return intExtra <= 0 ? defaultValue : intExtra;
    }

    private final float getWidth() {
        return getValidSizeFromIntent(KEY_WIDTH, 130.0f);
    }

    private final void loadImage() {
        final String stringExtra = getIntent().getStringExtra("key_url");
        if (stringExtra == null) {
            return;
        }
        VKScheduler.INSTANCE.getNetworkExecutor().submit(new Runnable() { // from class: com.vk.api.sdk.ui.e
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                VKCaptchaActivity.loadImage$lambda$4(stringExtra, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadImage$lambda$4(String str, VKCaptchaActivity vKCaptchaActivity) throws Throwable {
        byte[] bArrLoad = VKLoader.INSTANCE.load(str);
        if (bArrLoad != null) {
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrLoad, 0, bArrLoad.length);
            Intrinsics.checkNotNullExpressionValue(bitmapDecodeByteArray, "decodeByteArray(...)");
            vKCaptchaActivity.displayImage(bitmapDecodeByteArray);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(new FrameLayout(this));
        LinearLayout linearLayout = new LinearLayout(this);
        VKUtils vKUtils = VKUtils.INSTANCE;
        int iDp = vKUtils.dp(12);
        int width = (int) (getWidth() * Math.max(1.0f, vKUtils.density()));
        int height = (int) (getHeight() * Math.max(1.0f, vKUtils.density()));
        linearLayout.setPadding(iDp, iDp, iDp, iDp);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        FrameLayout frameLayout = new FrameLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.bottomMargin = iDp;
        frameLayout.setLayoutParams(layoutParams);
        this.progress = new ProgressBar(this);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        ProgressBar progressBar = this.progress;
        EditText editText = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progress");
            progressBar = null;
        }
        progressBar.setLayoutParams(layoutParams2);
        ProgressBar progressBar2 = this.progress;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progress");
            progressBar2 = null;
        }
        frameLayout.addView(progressBar2);
        this.image = new ImageView(this);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 17;
        ImageView imageView = this.image;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("image");
            imageView = null;
        }
        imageView.setLayoutParams(layoutParams3);
        ImageView imageView2 = this.image;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("image");
            imageView2 = null;
        }
        frameLayout.addView(imageView2);
        linearLayout.addView(frameLayout);
        EditText editText2 = new EditText(this);
        this.input = editText2;
        editText2.setInputType(176);
        EditText editText3 = this.input;
        if (editText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            editText3 = null;
        }
        editText3.setSingleLine(true);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(width, -2);
        EditText editText4 = this.input;
        if (editText4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            editText4 = null;
        }
        editText4.setLayoutParams(layoutParams4);
        View view = this.input;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
            view = null;
        }
        linearLayout.addView(view);
        new AlertDialog.Builder(this, 5).setView(linearLayout).setTitle(R.string.vk_captcha_hint).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.vk.api.sdk.ui.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                this.f40052a.captchaDone();
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.vk.api.sdk.ui.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                this.f40053a.captchaCancelled();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vk.api.sdk.ui.d
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f40054a.captchaCancelled();
            }
        }).show();
        EditText editText5 = this.input;
        if (editText5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("input");
        } else {
            editText = editText5;
        }
        editText.requestFocus();
        loadImage();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        VKValidationLocker.INSTANCE.signal();
        super.onDestroy();
    }
}
