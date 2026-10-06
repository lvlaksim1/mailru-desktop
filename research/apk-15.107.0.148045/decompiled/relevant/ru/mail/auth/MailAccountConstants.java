package ru.mail.auth;

import android.text.TextUtils;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class MailAccountConstants {
    public static final String ACTION_CONFIRMATION = "ru.mail.CONFIRM_REGISTRATION";
    public static final String ACTION_GET_GOOGLE_OAUTH_TOKEN = "ru.mail.auth.ADD_GOOGLE_ACCOUNT";
    public static final String ACTION_GET_OUTLOOK_OAUTH_TOKEN = "ru.mail.auth.ADD_OUTLOOK_ACCOUNT";
    public static final String ACTION_LOGIN = "ru.mail.auth.LOGIN";
    public static final String ACTION_MAIL_SECOND_STEP = "ru.mail.auth.MAIL_SECOND_STEP";
    public static final String ACTION_MAIL_SSO = "ru.mail.auth.SSO";
    public static final String ACTION_MAIL_VK_PASSWORD = "ru.mail.auth.VK_PASSWORD";
    public static final String ACTION_MRIM_DISABLED = "ru.mail.auth.MRIM_DISABLED";
    public static final String ACTION_PICK_MYCOM_ACCOUNT = "com.my.auth.PICK_ACCOUNT";
    public static final String ACTION_REGISTRATION = "ru.mail.auth.REGISTRATION";
    public static final String ACTION_REGISTRATION_CHILD = "ru.mail.auth.REGISTRATION_CHILD";
    public static final String ACTION_XMAIL_REG = "ru.mail.auth.ACTION_XMAIL_REG";
    public static final String AUTHTOKEN_TYPE = "ru.mail";
    public static final String AUTHTOKEN_TYPE_BOUNCE_PAIR = "ru.mail.security.bounce";
    public static final String AUTHTOKEN_TYPE_MOVE_PAIR = "ru.mail.security.move";
    public static final String AUTHTOKEN_TYPE_MPOP_TOKEN = "ru.mail.mpop.token";
    public static final String AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS = "ru.mail.oauth2.direct_access";
    public static final String AUTHTOKEN_TYPE_OAUTH_ACCESS = "ru.mail.oauth2.access";
    public static final String AUTHTOKEN_TYPE_OAUTH_REFRESH = "ru.mail.oauth2.refresh";
    public static final String AUTHTOKEN_TYPE_SENT_PAIR = "ru.mail.security.sent";
    public static final String AUTHTOKEN_TYPE_TORNADO = "ru.mail.mailapp:ru.mail.oauth2.access";
    public static final String AUTH_ANALYTICS_BY_GROUP_ENABLED = "auth_analytics_by_group_enabled";
    public static final String AUTH_ANALYTICS_IS_A_GROUP = "auth_analytics_is_a_group";
    public static final String CHOOSE_EMAIL_SERVICE = "ru.mail.auth.CHOOSE_EMAIL_SERVICE";
    public static final String EMAIL_EXTRA_GOOGLE_MAIL_PARAM = "EMAIL_EXTRA_GOOGLE_MAIL_PARAM";
    public static final String EXTERNAL_AUTH_PROHIBIT = "ru.mail.auth.EXTERNAL_AUTH_PROHIBIT";
    public static final String EXTRA_AUTH_CURRENT = "authCurrent";
    public static final String EXTRA_AUTH_EXTENID = "extenid";
    public static final String EXTRA_AUTH_FIRST = "authFirst";
    public static final String EXTRA_AUTH_USER_AGENT = "user-agent";
    public static final String EXTRA_DEVICE_INFO = "deviceInfo";
    public static final String EXTRA_EMAIL_FOR_SIGNUP = "email_for_signup";
    public static final String EXTRA_SCREEN_FOR_SOCIAL_BIND = "login_screen_for_bind";
    public static final String INVALID_TOKEN = "wrongtoken";
    public static final String LOGIN_EXTRA_ACCESS_TOKEN = "login_extra_access_token";
    public static final String LOGIN_EXTRA_ACCESS_TOKEN_EXPIRED_TIME = "access_token_expired_time";
    public static final String LOGIN_EXTRA_CGI_BIN_AUTH_EMAIL = "login_extra_web_auth_n_email";
    public static final String LOGIN_EXTRA_CGI_BIN_AUTH_FROM = "login_extra_cgi_bin_auth_from";
    public static final String LOGIN_EXTRA_CGI_BIN_AUTH_URL = "login_extra_web_auth_n_url";
    public static final String LOGIN_EXTRA_DOREGISTRATION_PARAM = "DoregistrationParam";
    public static final String LOGIN_EXTRA_GOOGLE_MAIL_PARAM = "LOGIN_EXTRA_GOOGLE_MAIL_PARAM";
    public static final String LOGIN_EXTRA_GOOGLE_REFRESH_TOKEN = "login_extra_google_refresh_token";
    public static final String LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM = "LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM";
    public static final String LOGIN_EXTRA_NO_PLAY_SERVICES = "LOGIN_EXTRA_NO_PLAY_SERVICES";
    public static final String LOGIN_EXTRA_OAUTH = "OAuth";
    public static final String LOGIN_EXTRA_OUATH2_ACCOUNT_TYPE = "oauth2_account_type";
    public static final String LOGIN_EXTRA_OUTLOOK_REFRESH_TOKEN = "login_extra_outlook_refresh_token";
    public static final String LOGIN_EXTRA_PERMISSION = "permission_intent";
    public static final String LOGIN_EXTRA_PICK_ACCOUNT = "login_extra_pick_account";
    public static final String LOGIN_EXTRA_REFRESH_TOKEN = "login_extra_refresh_token";
    public static final String LOGIN_EXTRA_REGISTRATION = "login_extra_registration";
    public static final String LOGIN_EXTRA_VKID_RESET_SOFT_BIND = "login_extra_vkid_reset_soft_bind";
    public static final String LOGIN_EXTRA_VK_CONNECT_AUTH_URL = "login_extra_vkc_auth_url";
    public static final String LOGIN_EXTRA_VK_CONNECT_EMAIL = "login_extra_vkc_email";
    public static final String LOGIN_EXTRA_VK_CONNECT_REFRESH_TOKEN = "login_extra_vkconnect_refresh_token";
    public static final String LOGIN_EXTRA_WEB_AUTH_N_REFRESH_TOKEN = "login_extra_web_auth_n_refresh_token";
    public static final String LOGIN_EXTRA_XMAIL_MIGRATION_FROM = "login_extra_xmail_migration_from";
    public static final String LOGIN_EXTRA_YAHOO_REFRESH_TOKEN = "login_extra_yahoo_refresh_token";
    public static final String LOGIN_EXTRA_YANDEX_REFRESH_TOKEN = "login_extra_yandex_refresh_token";
    public static final String LOGIN_GOOGLE_MAIL_FROM_PROMO = "login_google_from_promo";
    public static final String LOGIN_GRANTED_SCOPES = "login_granted_scope";
    public static final String MAIL_RU_REG_ACT_VK_ACCESS_TOKEN_KEY = "MAIL_RU_REG_ACT_VK_ACCESS_TOKEN_KEY";
    public static final String PARAMETR_REAL_EMAIL = "PARAMETR_REAL_EMAIL";
    public static final String SECURITY_TOKEN = "security_tokens_extra";
    public static final String SUCCESS_REGISTER_ACCOUNT = "SUCCESS_REGISTER_ACCOUNT";
    public static final String TOKEN_PAIR_SIGN = "sign";
    public static final String TOKEN_PAIR_TOKEN = "token";
    public static final String TWO_STEP_VK_ACCESS_TOKEN_KEY = "TWO_STEP_VK_ACCESS_TOKEN_KEY";
    public static final String XMAIL_MIGRATION_LOGIN = "xmail-hard-login";
    public static final String XMAIL_MIGRATION_MANUAL_LOGIN = "xmail-manual-login";
    public static final String XMAIL_MIGRATION_PROMO = "xmail-fullscreen";
    public static final String XMAIL_MIGRATION_SETTINGS = "xmail-account-settings";
    public static final String XMAIL_MIGRATION_SETTINGS_PROMO = "xmail-account-settings-fullscreen";
    public static final String XMAIL_MIGRATION_UNIVERSAL_LINK = "xmail-account-universal-link";

    public static String getAuthTokenType(O2AuthApp o2AuthApp, String str) {
        return o2AuthApp.getPackageName() + ":" + str;
    }

    public static String getCookieHeader(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Mpop=");
        stringBuffer.append(str);
        if (!TextUtils.isEmpty(str2)) {
            stringBuffer.append(";domain=");
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    static String getPackage(String str) {
        return str.substring(0, str.indexOf(":"));
    }

    static String getTokenType(String str) {
        return str.substring(str.indexOf(":") + 1);
    }

    public static boolean isXmailMigrationExceptLogin(String str) {
        return (TextUtils.isEmpty(str) || Objects.equals(str, XMAIL_MIGRATION_LOGIN)) ? false : true;
    }

    public static String packTokens(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sign", str);
            jSONObject.put("token", str2);
            return jSONObject.toString();
        } catch (JSONException e10) {
            throw new IllegalArgumentException("Failed to pack form sign or/and token to Json: sign " + str + ", token " + str2, e10);
        }
    }

    public static String unpackSign(String str) {
        return unpackSingle(str, "sign");
    }

    private static String unpackSingle(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("packed pair is empty");
        }
        try {
            return new JSONObject(str).getString(str2);
        } catch (JSONException e10) {
            throw new IllegalArgumentException("Failed to unpack form " + str2 + " from: " + str, e10);
        }
    }

    public static String unpackToken(String str) {
        return unpackSingle(str, "token");
    }
}
