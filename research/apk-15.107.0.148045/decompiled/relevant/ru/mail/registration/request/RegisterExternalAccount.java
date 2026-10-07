package ru.mail.registration.request;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import ru.mail.Authenticator.R;
import ru.mail.auth.request.ExternalAccountRegistrationRequest;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.network.HostProvider;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.registration.ui.DoregistrationFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class RegisterExternalAccount extends AsyncTask<Void, Void, ExternalAccountRegistrationRequest> {
    private final String captcha;
    private final String firstName;
    private final WeakReference<DoregistrationFragment> fragmentRef;
    private final String lastname;
    private final MigrateToPostConfig mMigrateToPostConfig;
    private final String mMrcuCookie;
    private final HostProvider mProvider;
    private final String mRegistrationId;

    public RegisterExternalAccount(String str, String str2, String str3, String str4, String str5, MigrateToPostConfig migrateToPostConfig, DoregistrationFragment doregistrationFragment) {
        this.firstName = str;
        this.lastname = str2;
        this.captcha = str3;
        this.mRegistrationId = str4;
        this.mMrcuCookie = str5;
        this.fragmentRef = new WeakReference<>(doregistrationFragment);
        this.mProvider = new PreferenceHostProvider(doregistrationFragment.getActivity(), "doreg", R.string.doreg_default_scheme, R.string.doreg_default_host);
        this.mMigrateToPostConfig = migrateToPostConfig;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public ExternalAccountRegistrationRequest doInBackground(Void... voidArr) {
        DoregistrationFragment doregistrationFragment = this.fragmentRef.get();
        ExternalAccountRegistrationRequest externalAccountRegistrationRequest = new ExternalAccountRegistrationRequest(doregistrationFragment == null ? null : doregistrationFragment.getActivity(), this.mProvider, this.lastname, this.firstName, this.captcha, this.mRegistrationId, this.mMrcuCookie, this.mMigrateToPostConfig.getIs12143Enabled());
        try {
            externalAccountRegistrationRequest.execute(ExecutorSelectors.defaultSelector()).getOrThrow();
            return externalAccountRegistrationRequest;
        } catch (InterruptedException unused) {
            return externalAccountRegistrationRequest;
        } catch (ExecutionException e10) {
            throw new RuntimeException(e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(ExternalAccountRegistrationRequest externalAccountRegistrationRequest) {
        DoregistrationFragment doregistrationFragment = this.fragmentRef.get();
        if (doregistrationFragment != null) {
            if (externalAccountRegistrationRequest.getResult() instanceof CommandStatus.OK) {
                doregistrationFragment.onCaptchaVerifyOK();
            } else {
                doregistrationFragment.onCaptchaVerifyFail(externalAccountRegistrationRequest);
            }
        }
    }
}
