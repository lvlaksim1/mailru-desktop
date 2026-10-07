package ru.mail.registration.ui;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Iterator;
import java.util.List;
import ru.mail.Authenticator.R;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.registration.request.RegQstCmd;
import ru.mail.registration.request.RegServerIdRequest;
import ru.mail.uikit.dialog.ProgressDialog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class RegQuestionChooser {
    private AccountData mAccountData;
    private Context mAppContext;
    private MigrateToPostConfig mMigrateToPostConfig;

    @Nullable
    private PhoneRequiredReceiver mPhoneRequired;
    private ProgressDialog mProgressDialog;
    private RegChooserResultReceiver mResultReceiver;

    /* JADX INFO: compiled from: ProGuard */
    interface PhoneRequiredReceiver {
        void onPhoneRequired(List<ErrorValue> list);
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class SignupObserver implements ObservableFuture.Observer<CommandStatus<?>>, LifecycleEventObserver {
        private final ConfirmationActivity.CaptchaQuestionAnalyticsFlow mCaptchaFlow;

        @Nullable
        private RegQuestionChooser regQuestionChooser;

        public SignupObserver(@Nullable RegQuestionChooser regQuestionChooser, ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow, LifecycleOwner lifecycleOwner) {
            this.regQuestionChooser = regQuestionChooser;
            this.mCaptchaFlow = captchaQuestionAnalyticsFlow;
            lifecycleOwner.getLifecycleRegistry().addObserver(this);
        }

        private boolean isContainsUnrecoverablePhoneError(@Nullable List<ErrorValue> list) {
            if (list == null) {
                return false;
            }
            Iterator<ErrorValue> it = list.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().getKey(), "phones")) {
                    return true;
                }
            }
            return false;
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onCancelled() {
            RegQuestionChooser regQuestionChooser = this.regQuestionChooser;
            if (regQuestionChooser != null) {
                regQuestionChooser.mProgressDialog.dismiss();
                regQuestionChooser.mResultReceiver.onChooseQuestionError(null, regQuestionChooser.mAccountData, ConfirmationQuestionFragment.ACTION);
            }
            this.regQuestionChooser = null;
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onError(Exception exc) {
            this.regQuestionChooser = null;
            throw new RuntimeException(exc);
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                this.regQuestionChooser = null;
            }
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onDone(CommandStatus<?> commandStatus) {
            RegQuestionChooser regQuestionChooser = this.regQuestionChooser;
            if (regQuestionChooser != null) {
                regQuestionChooser.mProgressDialog.dismiss();
                RegChooserResultReceiver regChooserResultReceiver = regQuestionChooser.mResultReceiver;
                if (commandStatus instanceof CommandStatus.OK) {
                    RegServerIdRequest.TokenResponse tokenResponse = (RegServerIdRequest.TokenResponse) commandStatus.getData();
                    regQuestionChooser.mAccountData.setId(tokenResponse.getRegToken());
                    if (tokenResponse.getCaptcha() == RegServerIdRequest.TokenResponse.Captcha.RECAPTCHA) {
                        regChooserResultReceiver.onSwitchToRecaptchaQuestionFragment(tokenResponse.getSiteKey(), this.mCaptchaFlow);
                    } else {
                        regChooserResultReceiver.onSwitchToCaptchaQuestionFragment(this.mCaptchaFlow);
                    }
                } else if ((commandStatus instanceof CommandStatus.ERROR) && (commandStatus.getData() instanceof List)) {
                    List<ErrorValue> list = (List) commandStatus.getData();
                    if (!isContainsUnrecoverablePhoneError(list) || regQuestionChooser.mPhoneRequired == null) {
                        regChooserResultReceiver.onChooseQuestionError(list, regQuestionChooser.mAccountData, ConfirmationQuestionFragment.ACTION);
                    } else {
                        regQuestionChooser.mPhoneRequired.onPhoneRequired(list);
                    }
                } else {
                    regChooserResultReceiver.onChooseQuestionError(null, regQuestionChooser.mAccountData, ConfirmationQuestionFragment.ACTION);
                }
            }
            this.regQuestionChooser = null;
        }
    }

    public RegQuestionChooser(Context context, ConfirmationActivity confirmationActivity, AccountData accountData, MigrateToPostConfig migrateToPostConfig, @Nullable PhoneRequiredReceiver phoneRequiredReceiver) {
        this.mAppContext = context.getApplicationContext();
        this.mResultReceiver = confirmationActivity;
        this.mAccountData = accountData;
        ProgressDialog progressDialog = new ProgressDialog(confirmationActivity);
        this.mProgressDialog = progressDialog;
        this.mPhoneRequired = phoneRequiredReceiver;
        progressDialog.setMessage(confirmationActivity.getResources().getString(R.string.reg_dialog_text));
        this.mProgressDialog.setCancelable(false);
        this.mMigrateToPostConfig = migrateToPostConfig;
    }

    public void performSignupCheck(ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow, LifecycleOwner lifecycleOwner) {
        this.mProgressDialog.show();
        new RegQstCmd(this.mAppContext, this.mAccountData, this.mMigrateToPostConfig.getIs12144Enabled()).execute(ExecutorSelectors.defaultSelector()).observe(Schedulers.mainThread(), new SignupObserver(this, captchaQuestionAnalyticsFlow, lifecycleOwner));
    }
}
