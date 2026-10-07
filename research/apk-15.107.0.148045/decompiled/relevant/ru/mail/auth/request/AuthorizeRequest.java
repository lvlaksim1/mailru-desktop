package ru.mail.auth.request;

import android.content.Context;
import android.preference.PreferenceManager;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.mail.Authenticator.R;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.DoregistrationParameter;
import ru.mail.auth.OAuthTransitionManager;
import ru.mail.auth.request.AuthorizeRequestCommand;
import ru.mail.authorizesdk.auth.request.ProgressLoginCmd;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class AuthorizeRequest<P extends AuthorizeRequestCommand> extends ProgressLoginCmd {
    private static final Log LOG = Log.getLog("AuthorizeRequest");
    private final Analytics mAnalytics;
    private final Context mAppContext;
    private DoregistrationParameter mParameters;

    protected AuthorizeRequest(Context context, @NotNull P p10) {
        this.mAppContext = context;
        this.mAnalytics = AuthenticatorEntryPoint.analytics(context);
        addCommand(p10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void handleExternalRegistration(T t10) {
        setResult(t10);
        DoregistrationParameter data = ((AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED) t10).getData();
        this.mParameters = data;
        if (data.isCaptchaRequired()) {
            addCommand(new GetCaptchaRequest(this.mAppContext, new PreferenceHostProvider(this.mAppContext, "doreg_captcha", R.string.doreg_captcha_default_scheme, R.string.doreg_captcha_default_host), AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12132Enabled()));
        }
        if (PreferenceManager.getDefaultSharedPreferences(this.mAppContext).getBoolean("enable_doreg_name", false)) {
            addCommand(new GetNameRequest(this.mAppContext, new PreferenceHostProvider(this.mAppContext, "doreg_name", R.string.doreg_name_default_scheme, R.string.doreg_name_default_host), this.mParameters.getRegId(), AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12132Enabled()));
        }
    }

    private <T> void handleGetCaptchaRequest(CommandStatus.OK<GetCaptchaRequest.Result> ok) {
        GetCaptchaRequest.Result data = ok.getData();
        DoregistrationParameter doregistrationParameter = this.mParameters;
        if (doregistrationParameter != null) {
            DoregistrationParameter doregistrationParameterBuild = doregistrationParameter.getBuilder().setCaptcha(data.getBitmap()).setCookie(data.getMrcuCookie()).build();
            this.mParameters = doregistrationParameterBuild;
            setResult(new AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED(doregistrationParameterBuild));
        }
    }

    private <T> void handleGetNameRequest(CommandStatus.OK<GetNameRequest.Result> ok) {
        GetNameRequest.Result data = ok.getData();
        DoregistrationParameter doregistrationParameter = this.mParameters;
        if (doregistrationParameter != null) {
            DoregistrationParameter doregistrationParameterBuild = doregistrationParameter.getBuilder().setFirstName(data.getFirstName()).setLastName(data.getLastName()).build();
            this.mParameters = doregistrationParameterBuild;
            setResult(new AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED(doregistrationParameterBuild));
        }
    }

    private <T> boolean isAuthCommand(Command<?, T> command) {
        return command instanceof AuthorizeRequestCommand;
    }

    public static <T> boolean isAuthFailedResult(T t10) {
        return t10 == null || t10.getClass().equals(CommandStatus.ERROR.class);
    }

    private <T> boolean shouldHandleExternalRegistration(Command<?, T> command, T t10) {
        return isAuthCommand(command) && (t10 instanceof AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED);
    }

    public Context getContext() {
        return this.mAppContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        if (shouldHandleOAuthResult(command)) {
            T t10 = (T) executeCommand(command, priority, executorSelector);
            if (!isAuthFailedResult(t10) || AuthenticatorConfig.getInstance().needRemoveSwitchToMpop()) {
                removeCommand(command);
                if (shouldHandleExternalRegistration(command, t10)) {
                    handleExternalRegistration(t10);
                }
            } else {
                OAuthTransitionManager oAuthTransitionManager = new OAuthTransitionManager(this.mAppContext);
                oAuthTransitionManager.incrementTryCount();
                AuthenticatorConfig.getInstance().setOAuthEnabledForSession(false);
                this.mAnalytics.failedToRetrieveOAuth(oAuthTransitionManager.getTryCount(), oAuthTransitionManager.isOAuthTransitionPermitted());
            }
            setResult(t10);
            return t10;
        }
        T t11 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (isAuthCommand(command) && (t11 instanceof CommandStatus.OK)) {
            setResult(t11);
            return t11;
        }
        if (shouldHandleExternalRegistration(command, t11)) {
            handleExternalRegistration(t11);
            return t11;
        }
        if ((command instanceof GetNameRequest) && (t11 instanceof CommandStatus.OK)) {
            handleGetNameRequest((CommandStatus.OK) t11);
            return t11;
        }
        if ((command instanceof GetCaptchaRequest) && (t11 instanceof CommandStatus.OK)) {
            handleGetCaptchaRequest((CommandStatus.OK) t11);
            return t11;
        }
        setResult(t11);
        return t11;
    }

    protected <T> boolean shouldHandleOAuthResult(Command<?, T> command) {
        return isAuthCommand(command) && AuthenticatorConfig.getInstance().isOAuthEnabled();
    }
}
