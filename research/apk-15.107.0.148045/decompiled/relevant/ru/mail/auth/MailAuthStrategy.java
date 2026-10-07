package ru.mail.auth;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.ludwig.LudwigParams;
import ru.mail.auth.request.AuthorizeRequest;
import ru.mail.auth.request.AuthorizeTokenRequest;
import ru.mail.auth.request.HttpsAuthorizeLoginRequest;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.mailbox.cmd.Command;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class MailAuthStrategy extends AuthStrategy {
    private static final Log LOG = Log.getLog("MailAuthStrategy");

    public MailAuthStrategy(Authenticator.AuthVisitor authVisitor) {
        super(authVisitor);
    }

    private Map<String, String> getRedirectCookies(Bundle bundle) {
        Map<String, String> map = (Map) bundle.getSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_COOKIES);
        return map == null ? Collections.EMPTY_MAP : map;
    }

    @Override // ru.mail.auth.AuthStrategy
    @NotNull
    public Bundle authenticate(Context context, MailAccount mailAccount, Bundle bundle) throws NetworkErrorException {
        AuthorizeRequest authorizeRequestHttpAuthResponse;
        Log log = LOG;
        log.i("Start authentication for " + mailAccount.name);
        mailAccount.checkName();
        String string = bundle.getString(Authenticator.BUNDLE_PARAM_PASSWORD);
        boolean z10 = bundle.getBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE);
        LudwigParams ludwigParams = LudwigParams.INSTANCE;
        Map<String, String> ludwigParams2 = ludwigParams.getLudwigParams(ludwigParams.getIsLudwigEnabled(bundle), ludwigParams.getLudwigToken(bundle));
        boolean zIsNoExternalFlow = AuthenticatorConfig.getInstance().isNoExternalFlow();
        if (bundle.containsKey(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS)) {
            Map map = (Map) bundle.getSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS);
            if (ludwigParams2 != null) {
                map.putAll(ludwigParams2);
            }
            Map<String, String> redirectCookies = getRedirectCookies(bundle);
            MigrateToPostConfig migrateToPostConfig = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
            log.i("Using second step");
            authorizeRequestHttpAuthResponse = AuthorizeTask.secondStepAuthResponse(context, createHostProvider(context, bundle), mailAccount.name, map, redirectCookies, migrateToPostConfig.getIs12144Enabled(), zIsNoExternalFlow, bundle);
        } else if (bundle.containsKey(Authenticator.SSO_AUTH_AG_TOKEN)) {
            string = bundle.getString(Authenticator.SSO_AUTH_AG_TOKEN);
            MigrateToPostConfig migrateToPostConfig2 = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
            HashMap map2 = new HashMap();
            map2.put("token", string);
            log.i("Using SSO");
            authorizeRequestHttpAuthResponse = AuthorizeTask.ssoAuthResponse(context, createHostProvider(context, bundle), mailAccount.name, map2, migrateToPostConfig2.getIs12144Enabled(), zIsNoExternalFlow, bundle);
        } else if (bundle.containsKey(Authenticator.VK_PASSWORD_AUTH_AG_TOKEN)) {
            string = bundle.getString(Authenticator.VK_PASSWORD_AUTH_AG_TOKEN);
            MigrateToPostConfig migrateToPostConfig3 = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
            HashMap map3 = new HashMap();
            map3.put("token", string);
            log.i("Using VkPassword");
            authorizeRequestHttpAuthResponse = AuthorizeTask.vkPasswordAuthResponse(context, createHostProvider(context, bundle), mailAccount.name, map3, migrateToPostConfig3.getIs12144Enabled(), zIsNoExternalFlow, bundle);
        } else {
            authorizeRequestHttpAuthResponse = AuthorizeTask.httpAuthResponse(context, createHostProvider(context, bundle), mailAccount.name, string, bundle, AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12127Enabled(), zIsNoExternalFlow, ludwigParams2);
        }
        Bundle bundleProcessAuthResponse = processAuthResponse(context, mailAccount, string, authorizeRequestHttpAuthResponse);
        bundleProcessAuthResponse.putBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, z10);
        return bundleProcessAuthResponse;
    }

    @Override // ru.mail.auth.AuthStrategy
    public void onRegisterRequired(Command<?, ?> command, Bundle bundle) {
        if (command instanceof HttpsAuthorizeLoginRequest) {
            this.mVisitor.visit((HttpsAuthorizeLoginRequest) command, bundle);
        } else if (command instanceof AuthorizeTokenRequest) {
            this.mVisitor.visit((AuthorizeTokenRequest) command, bundle);
        }
    }
}
