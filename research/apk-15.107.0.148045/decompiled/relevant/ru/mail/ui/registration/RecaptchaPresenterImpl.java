package ru.mail.ui.registration;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.AccountData;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class RecaptchaPresenterImpl implements RecaptchaPresenter {
    private static final Log LOG = Log.getLog("RecaptchaPresenterImpl");
    private AccountData mAccountData;
    private Context mAppContext;

    @Nullable
    private RecaptchaPresenter.View mView;

    public RecaptchaPresenterImpl(AccountData accountData, Context context) {
        this.mAccountData = accountData;
        this.mAppContext = context.getApplicationContext();
    }

    @Override // ru.mail.logic.content.Detachable
    public boolean isEmpty() {
        return this.mView == null;
    }

    @Override // ru.mail.logic.content.Detachable
    public void onDetach() {
        this.mView = null;
    }

    @Override // ru.mail.ui.registration.RecaptchaPresenter
    public void onStartRecaptchaValidation(Activity activity, String str, RegFlowAnalytics regFlowAnalytics) {
        SafetyNet.getClient(activity).verifyWithRecaptcha(str).addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() { // from class: ru.mail.ui.registration.RecaptchaPresenterImpl.2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                if (RecaptchaPresenterImpl.this.mView != null) {
                    if (TextUtils.isEmpty(recaptchaTokenResponse.getTokenResult())) {
                        RecaptchaPresenterImpl.this.mView.showRecaptchaFail();
                    } else {
                        RecaptchaPresenterImpl.this.mAccountData.setCode(recaptchaTokenResponse.getTokenResult());
                        RecaptchaPresenterImpl.this.mView.showRecaptchaSuccess();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: ru.mail.ui.registration.RecaptchaPresenterImpl.1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(@NonNull Exception exc) {
                if (RecaptchaPresenterImpl.this.mView != null) {
                    if (exc instanceof ApiException) {
                        int statusCode = ((ApiException) exc).getStatusCode();
                        RecaptchaPresenterImpl.LOG.e("Error: " + CommonStatusCodes.getStatusCodeString(statusCode));
                    } else {
                        RecaptchaPresenterImpl.LOG.e("Error: " + exc.getMessage());
                    }
                    RecaptchaPresenterImpl.this.mView.showRecaptchaFail();
                }
            }
        });
        MailAppDependencies.analytics(this.mAppContext).registrationCaptchaViewTypeRecapthca(regFlowAnalytics.toString());
    }

    @Override // ru.mail.logic.content.Detachable
    public void onAttach(RecaptchaPresenter.View view) {
        this.mView = view;
    }
}
