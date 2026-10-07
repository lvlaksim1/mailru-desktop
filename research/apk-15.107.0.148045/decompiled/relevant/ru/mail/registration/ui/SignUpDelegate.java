package ru.mail.registration.ui;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.registration.request.RegServerIdRequest;
import ru.mail.registration.request.RegTokenCmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002%&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH&J(\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\b2\f\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 H\u0004J\u001c\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006'"}, d2 = {"Lru/mail/registration/ui/SignUpDelegate;", "", "migrateToPostConfig", "Lru/mail/auth/request/MigrateToPostConfig;", "<init>", "(Lru/mail/auth/request/MigrateToPostConfig;)V", "resultReceiver", "Ljava/lang/ref/WeakReference;", "Lru/mail/registration/ui/SignUpDelegate$SignupResultReceiver;", "getResultReceiver", "()Ljava/lang/ref/WeakReference;", "setResultReceiver", "(Ljava/lang/ref/WeakReference;)V", "accountData", "Lru/mail/registration/ui/AccountData;", "getAccountData", "()Lru/mail/registration/ui/AccountData;", "setAccountData", "(Lru/mail/registration/ui/AccountData;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "performCodeSignup", "", "sendSms", "", "processOkResult", "signupResultReceiver", "ok", "Lru/mail/mailbox/cmd/CommandStatus$OK;", "sendSmsCode", "token", "", "sitekey", "SignupObserver", "SignupResultReceiver", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SignUpDelegate {

    @Nullable
    private AccountData accountData;

    @Nullable
    private Context context;

    @NotNull
    private final MigrateToPostConfig migrateToPostConfig;

    @Nullable
    private WeakReference<SignupResultReceiver> resultReceiver;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b$\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0001B\u001b\b\u0004\u0012\u0010\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00022\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0014J\u0012\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\r"}, d2 = {"Lru/mail/registration/ui/SignUpDelegate$SignupObserver;", "Lru/mail/registration/ui/AuthResponseObserver;", "Lru/mail/registration/ui/SignUpDelegate$SignupResultReceiver;", "Lru/mail/mailbox/cmd/CommandStatus;", "resultReceiver", "Ljava/lang/ref/WeakReference;", "<init>", "(Ljava/lang/ref/WeakReference;)V", "handleErrorResponse", "", "signupResultReceiver", "result", "handleCancelled", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class SignupObserver extends AuthResponseObserver<SignupResultReceiver, CommandStatus<?>> {
        protected SignupObserver(@Nullable WeakReference<SignupResultReceiver> weakReference) {
            super(weakReference);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.registration.ui.AuthResponseObserver
        public void handleCancelled(@Nullable SignupResultReceiver signupResultReceiver) {
            if (signupResultReceiver != null) {
                signupResultReceiver.onSignupCancelled();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.registration.ui.AuthResponseObserver
        public void handleErrorResponse(@Nullable SignupResultReceiver signupResultReceiver, @Nullable CommandStatus<?> result) {
            if (result instanceof CommandStatus.ERROR) {
                if (signupResultReceiver != null) {
                    V data = ((CommandStatus.ERROR) result).getData();
                    List<? extends ErrorValue> listEmptyList = data instanceof List ? (List) data : null;
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    signupResultReceiver.onSignupError(listEmptyList);
                    return;
                }
                return;
            }
            if (result instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) {
                if (signupResultReceiver != null) {
                    signupResultReceiver.onSignupError(CollectionsKt.listOf(new ErrorValue(ErrorStatus.SERVER_UNAVAILABLE, "")));
                }
            } else if (signupResultReceiver != null) {
                signupResultReceiver.onSignupError(CollectionsKt.emptyList());
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/registration/ui/SignUpDelegate$SignupResultReceiver;", "", "onSignupOk", "", "regId", "", "recaptchaSiteKey", "onSignupError", "valueList", "", "Lru/mail/registration/ui/ErrorValue;", "onSignupCancelled", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SignupResultReceiver {
        void onSignupCancelled();

        void onSignupError(@NotNull List<? extends ErrorValue> valueList);

        void onSignupOk(@Nullable String regId, @Nullable String recaptchaSiteKey);
    }

    public SignUpDelegate(@NotNull MigrateToPostConfig migrateToPostConfig) {
        Intrinsics.checkNotNullParameter(migrateToPostConfig, "migrateToPostConfig");
        this.migrateToPostConfig = migrateToPostConfig;
    }

    private final void sendSmsCode(final String token, final String sitekey) {
        new RegTokenCmd(this.context, this.accountData, this.migrateToPostConfig.getIs12144Enabled()).execute(ExecutorSelectors.defaultSelector()).observe(Schedulers.mainThread(), new SignupObserver(this.resultReceiver) { // from class: ru.mail.registration.ui.SignUpDelegate.sendSmsCode.1
            @Override // ru.mail.registration.ui.AuthResponseObserver
            public /* bridge */ /* synthetic */ void handleOkResult(SignupResultReceiver signupResultReceiver, CommandStatus.OK ok) {
                handleOkResult2(signupResultReceiver, (CommandStatus.OK<?>) ok);
            }

            /* JADX INFO: renamed from: handleOkResult, reason: avoid collision after fix types in other method */
            protected void handleOkResult2(SignupResultReceiver signupResultReceiver, CommandStatus.OK<?> ok) {
                if (signupResultReceiver != null) {
                    signupResultReceiver.onSignupOk(token, sitekey);
                }
            }
        });
    }

    @Nullable
    public final AccountData getAccountData() {
        return this.accountData;
    }

    @Nullable
    public final Context getContext() {
        return this.context;
    }

    @Nullable
    public final WeakReference<SignupResultReceiver> getResultReceiver() {
        return this.resultReceiver;
    }

    public abstract void performCodeSignup(boolean sendSms);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void processOkResult(boolean sendSms, @Nullable SignupResultReceiver signupResultReceiver, @Nullable CommandStatus.OK<?> ok) {
        Object data = ok != null ? ok.getData() : null;
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.registration.request.RegServerIdRequest.TokenResponse");
        RegServerIdRequest.TokenResponse tokenResponse = (RegServerIdRequest.TokenResponse) data;
        String siteKey = tokenResponse.getCaptcha() == RegServerIdRequest.TokenResponse.Captcha.RECAPTCHA ? tokenResponse.getSiteKey() : null;
        AccountData accountData = this.accountData;
        if (accountData != null) {
            accountData.setId(tokenResponse.getRegToken());
        }
        if (sendSms) {
            sendSmsCode(tokenResponse.getRegToken(), siteKey);
        } else if (signupResultReceiver != null) {
            signupResultReceiver.onSignupOk(tokenResponse.getRegToken(), siteKey);
        }
    }

    public final void setAccountData(@Nullable AccountData accountData) {
        this.accountData = accountData;
    }

    public final void setContext(@Nullable Context context) {
        this.context = context;
    }

    public final void setResultReceiver(@Nullable WeakReference<SignupResultReceiver> weakReference) {
        this.resultReceiver = weakReference;
    }
}
