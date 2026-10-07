package ru.mail.registration.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import java.lang.ref.WeakReference;
import org.jetbrains.annotations.NotNull;
import ru.mail.Authenticator.R;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.request.GetCaptchaRequest;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class LoadCaptchaDelegate {
    private final WeakReference<LoadCaptchaCallback> callbackRef;
    private final Context context;
    private final WeakReference<ImageView> imageViewRef;
    private final WeakReference<ProgressBar> mProgressBarRef;
    private final WeakReference<ImageButton> mRefreshCaptchaButtonRef;

    /* JADX INFO: compiled from: ProGuard */
    public interface LoadCaptchaCallback {
        void loadCaptchaFail();

        void loadCaptchaSuccess(CaptchaResult captchaResult);
    }

    public LoadCaptchaDelegate(@NotNull Context context, @NotNull LoadCaptchaCallback loadCaptchaCallback, @NotNull ImageView imageView, @NotNull ProgressBar progressBar, @NotNull ImageButton imageButton) {
        this.callbackRef = new WeakReference<>(loadCaptchaCallback);
        this.imageViewRef = new WeakReference<>(imageView);
        this.mProgressBarRef = new WeakReference<>(progressBar);
        this.mRefreshCaptchaButtonRef = new WeakReference<>(imageButton);
        this.context = context;
    }

    public void clearCallBackRef() {
        this.callbackRef.clear();
    }

    public void loadCaptcha() {
        ImageView imageView = this.imageViewRef.get();
        if (imageView != null) {
            imageView.setImageDrawable(null);
        }
        ProgressBar progressBar = this.mProgressBarRef.get();
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        ImageButton imageButton = this.mRefreshCaptchaButtonRef.get();
        if (imageButton != null) {
            imageButton.setVisibility(4);
        }
        new GetCaptchaRequest(this.context, new PreferenceHostProvider(this.context, "doreg_captcha", R.string.doreg_captcha_default_scheme, R.string.doreg_captcha_default_host), AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12132Enabled()).execute(ExecutorSelectors.defaultSelector()).observe(Schedulers.mainThread(), new ObservableFuture.Observer<CommandStatus<?>>() { // from class: ru.mail.registration.ui.LoadCaptchaDelegate.1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                onComplete(null, null, null);
            }

            public void onComplete(Bitmap bitmap, String str, String str2) {
                ImageView imageView2 = (ImageView) LoadCaptchaDelegate.this.imageViewRef.get();
                if (imageView2 != null && bitmap != null) {
                    imageView2.setImageBitmap(bitmap);
                }
                ProgressBar progressBar2 = (ProgressBar) LoadCaptchaDelegate.this.mProgressBarRef.get();
                if (progressBar2 != null) {
                    progressBar2.setVisibility(4);
                }
                ImageButton imageButton2 = (ImageButton) LoadCaptchaDelegate.this.mRefreshCaptchaButtonRef.get();
                if (imageButton2 != null) {
                    imageButton2.setVisibility(0);
                }
                LoadCaptchaCallback loadCaptchaCallback = (LoadCaptchaCallback) LoadCaptchaDelegate.this.callbackRef.get();
                if (loadCaptchaCallback != null) {
                    if (bitmap == null || str == null) {
                        loadCaptchaCallback.loadCaptchaFail();
                    } else {
                        loadCaptchaCallback.loadCaptchaSuccess(LoadCaptchaDelegate.this.new CaptchaResult(str, str2));
                    }
                }
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exc) {
                onComplete(null, null, null);
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(CommandStatus<?> commandStatus) {
                Bitmap bitmap;
                String xcaptchaId;
                String mrcuCookie;
                if (commandStatus instanceof CommandStatus.OK) {
                    GetCaptchaRequest.Result result = (GetCaptchaRequest.Result) commandStatus.getData();
                    bitmap = result.getBitmap();
                    mrcuCookie = result.getMrcuCookie();
                    xcaptchaId = result.getXcaptchaId();
                } else {
                    bitmap = null;
                    xcaptchaId = null;
                    mrcuCookie = null;
                }
                onComplete(bitmap, mrcuCookie, xcaptchaId);
            }
        });
    }

    /* JADX INFO: compiled from: ProGuard */
    public class CaptchaResult {
        private String cookie;
        private String xCaptchaId;

        public CaptchaResult(String str, String str2) {
            this.cookie = str;
            this.xCaptchaId = str2;
        }

        public String getCookie() {
            return this.cookie;
        }

        public String getxCaptchaId() {
            return this.xCaptchaId;
        }

        public void setCookie(String str) {
            this.cookie = str;
        }

        public void setxCaptchaId(String str) {
            this.xCaptchaId = str;
        }

        public CaptchaResult(String str) {
            this.cookie = str;
            this.xCaptchaId = "";
        }
    }
}
