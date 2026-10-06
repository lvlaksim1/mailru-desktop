package ru.mail.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.request.AuthPhoneToken;
import ru.mail.auth.request.AuthTypeResolver;
import ru.mail.auth.request.AuthorizeRequestCommand;
import ru.mail.auth.request.AuthorizeTokenRequest;
import ru.mail.auth.request.CgiBinAuthSendAgentRequest;
import ru.mail.auth.request.GoogleOauth2SendAgentRequest;
import ru.mail.auth.request.HttpsAuthorizeLoginRequest;
import ru.mail.auth.request.MpopTokenRequest;
import ru.mail.auth.request.OutlookOauthSendAgentRequest;
import ru.mail.auth.request.QrLoginCommandProvider;
import ru.mail.auth.request.VKOauth2SendAgentRequest;
import ru.mail.auth.request.WebAuthNCommandProvider;
import ru.mail.auth.request.YahooOauth2SendAgentRequest;
import ru.mail.auth.request.YandexOauth2SendAgentRequest;
import ru.mail.auth.util.DomainUtils;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParamsHelper;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.setup.PortalMailAppConfigurationRetrieverImpl;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.utils.feature.reversed.matching.ReversedMatchingVkConstants;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class Authenticator extends AbstractAccountAuthenticator {
    public static final String ACCOUNT_KEY_DELETED = "account_key_deleted";
    public static final String ACCOUNT_KEY_FIRST_NAME = "account_key_first_name";
    public static final String ACCOUNT_KEY_LAST_NAME = "account_key_last_name";
    public static final String ACCOUNT_KEY_PARENT_CONTROL_BOUNDED_PARENT_ACCOUNT = "account_key_parent_control_bounded_parent_account";
    public static final String ACCOUNT_PARAMETER_ACCOUNT_TYPE = "type";
    public static final String ACCOUNT_PARAMETER_EMAIL_SERVICE_TYPE = "email_service_type";
    public static final String ACCOUNT_VALUE_DELETED = "account_value_deleted";
    private static final String ACCOUNT_VALUE_DELETING_ERROR = "account_value_deleting_error";
    public static final String AUTH_RESTORE_PARAMS = "proxy_auth_restore_params";
    public static final String BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE = "key_2factor_auth_tsa_cookie";
    public static final String BUNDLE_PARAM_FROM_BACKGROUND = "fromBackground";
    public static final String BUNDLE_PARAM_PASSWORD = "BUNDLE_PARAM_PASSWORD";
    public static final String BUNDLE_PARAM_PERMISSION_GRANTED = "PERMISSION_GRANTED";
    public static final String BUNDLE_PARAM_PHONE = "phone_number";
    public static final String BUNDLE_PARAM_SECSTEP_REDIRECT_COOKIES = "BUNDLE_PARAM_SECSTEP_REDIRECT_COOKIES";
    public static final String BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS = "BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS";
    public static final int ERROR_CODE_EMPTY_MAIL_ACCOUNT = 25;
    public static final int ERROR_CODE_INVALID_LOGIN = 22;
    public static final int ERROR_CODE_INVALID_LOGIN_OK0 = 24;
    public static final int ERROR_CODE_NETWORK_ERROR = 23;
    public static final String EXTRA_ADD_ACCOUNT_LOGIN = "add_account_login";
    public static final String EXTRA_EMAILS = "emails";
    public static final String EXTRA_EMAILS_RESULT = "emails_result";
    public static final String EXTRA_ERROR_CODE_ADDITIONAL = "error_code_additional";
    public static final String EXTRA_FROM = "from";
    public static final String EXTRA_FROM_BACKGROUND = "from_background";
    public static final String EXTRA_FROM_REGISTRATION = "FROM_REGISTRATION";
    public static final String EXTRA_IVR = "request_call";
    public static final String EXTRA_SKIP_SERVICE_CHOOSER = "skip_service_chooser";
    public static final String EXTRA_SMS_CODE = "sms_code";
    public static final String EXTRA_SMS_CODE_PHONE = "normalized_phone";
    public static final String EXTRA_SMS_CODE_SIZE = "sms_code_size";
    public static final String EXTRA_SMS_CODE_STATUS = "sms_code_status";
    public static final String EXTRA_SMS_CODE_WAIT = "sms_code_wait";
    public static final String EXTRA_SMS_PHONE = "sms_phone";
    public static final String EXTRA_STATISTIC_MESSAGE = "statistic_message";
    public static final String IS_ENTERED_BY_ONE_TIME_CODE = "is_entered_by_one_time_code";
    public static final String IS_HIDE_UI_ON_START = "is_hide_ui_on_start";
    public static final String IS_LOGIN_EXISTING_ACCOUNT = "is_login_existing_account";
    public static final String IS_MIGRANT_REGISTRATION = "IS_MIGRANT_REGISTRATION";
    public static final String IS_PREFILL_LOGIN_DATA = "is_prefill_login_data";
    public static final String IS_USER_LOGGED_OUT_HIMSELF = "is_maual_logout";
    public static final String IS_VK_SIGN_IN_DELEGATE_DISABLED = "is_vk_sign_in_delegate_disabled";
    public static final String KEY_ACCESS_TOKEN = "google_access_token";
    public static final String KEY_ALLOW_REMOVE_ACCOUNT_FROM_SYSTEM = "allow_remove_account_from_system";
    public static final String KEY_AUTHTOKEN_ACCESS = "authtoken_access";
    public static final String KEY_AUTHTOKEN_REFRESH = "authtoken_refresh";
    public static final String KEY_BIND_SOCIAL_TYPE = "bind_social_type";
    public static final String KEY_HAS_REGISTRATION = "has_registration";
    public static final String KEY_IMAP_SETTINGS = "imap_settings";
    public static final String KEY_OAUTH_ENABLED = "oauth_enabled";
    public static final String KEY_SWA_CODE = "error_reason_code";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_TRANSPORT_TYPE = "transport_type";
    public static final String KEY_UNAUTHORIZED = "key_unauthorized";
    public static final String KEY_USER_2FACTOR_TSA_COOKIE = "key_user_2factor_tsa_cookie";
    public static final String KEY_VKC_ID = "vkc_user_id";
    public static final String LOGIN_TO_126COM_DOMAIN = "LOGIN_TO_126COM_DOMAIN";
    public static final String LOGIN_TO_163COM_DOMAIN = "LOGIN_TO_163COM_DOMAIN";
    public static final String LOGIN_TO_ALICEIT_DOMAIN = "LOGIN_TO_ALICEIT_DOMAIN";
    public static final String LOGIN_TO_AOL_DOMAIN = "LOGIN_TO_AOL_DOMAIN";
    public static final String LOGIN_TO_ARCOR_DOMAIN = "LOGIN_TO_ARCOR_DOMAIN";
    public static final String LOGIN_TO_BLU_DOMAIN = "LOGIN_TO_BLU_DOMAIN";
    public static final String LOGIN_TO_BOL_DOMAIN = "LOGIN_TO_BOL_DOMAIN";
    public static final String LOGIN_TO_BTINTERNET_DOMAIN = "LOGIN_TO_BTINTERNET_DOMAIN";
    public static final String LOGIN_TO_DMX_DOMAIN = "LOGIN_TO_DMX_DOMAIN";
    public static final String LOGIN_TO_DOCOMO_DOMAIN = "LOGIN_TO_DOCOMO_DOMAIN";
    public static final String LOGIN_TO_EXCHANGE_DOMAIN = "LOGIN_TO_EXCHANGE_DOMAIN";
    public static final String LOGIN_TO_FREENET_DOMAIN = "LOGIN_TO_FREENET_DOMAIN";
    public static final String LOGIN_TO_FREE_DOMAIN = "LOGIN_TO_FREE_DOMAIN";
    public static final String LOGIN_TO_GIALLO_DOMAIN = "LOGIN_TO_GIALLO_DOMAIN";
    public static final String LOGIN_TO_GOOGLE_DOMAIN = "LOGIN_TO_GOOGLE_DOMAIN";
    public static final String LOGIN_TO_HOTMAIL_DOMAIN = "LOGIN_TO_HOTMAIL_DOMAIN";
    public static final String LOGIN_TO_IG_DOMAIN = "LOGIN_TO_IG_DOMAIN";
    public static final String LOGIN_TO_INWIND_DOMAIN = "LOGIN_TO_INWIND_DOMAIN";
    public static final String LOGIN_TO_IOL_DOMAIN = "LOGIN_TO_IOL_DOMAIN";
    public static final String LOGIN_TO_LAPOSTE_DOMAIN = "LOGIN_TO_LAPOSTE_DOMAIN";
    public static final String LOGIN_TO_LIBERO_DOMAIN = "LOGIN_TO_LIBERO_DOMAIN";
    public static final String LOGIN_TO_LIVE_DOMAIN = "LOGIN_TO_LIVE_DOMAIN";
    public static final String LOGIN_TO_MAILRU_DOMAIN = "LOGIN_TO_MAILRU_DOMAIN";
    public static final String LOGIN_TO_MYCOM_DOMAIN = "LOGIN_TO_MYCOM_DOMAIN";
    public static final String LOGIN_TO_MYNET_DOMAIN = "LOGIN_TO_MYNET_DOMAIN";
    public static final String LOGIN_TO_ORANGE_DOMAIN = "LOGIN_TO_ORANGE_DOMAIN";
    public static final String LOGIN_TO_OTHER_DOMAIN = "LOGIN_TO_OTHER_DOMAIN";
    public static final String LOGIN_TO_OUTLOOK_DOMAIN = "LOGIN_TO_OUTLOOK_DOMAIN";
    public static final String LOGIN_TO_QIP_DOMAIN = "LOGIN_TO_QIP_DOMAIN";
    public static final String LOGIN_TO_QQ_DOMAIN = "LOGIN_TO_QQ_DOMAIN";
    public static final String LOGIN_TO_RAMBLER_DOMAIN = "LOGIN_TO_RAMBLER_DOMAIN";
    public static final String LOGIN_TO_ROGERS_DOMAIN = "LOGIN_TO_ROGERS_DOMAIN";
    public static final String LOGIN_TO_SHAW_DOMAIN = "LOGIN_TO_SHAW_DOMAIN";
    public static final String LOGIN_TO_SINA_DOMAIN = "LOGIN_TO_SINA_DOMAIN";
    public static final String LOGIN_TO_TONLINE_DOMAIN = "LOGIN_TO_TONLINE_DOMAIN";
    public static final String LOGIN_TO_TUT_DOMAIN = "LOGIN_TO_TUT_DOMAIN";
    public static final String LOGIN_TO_UA_DOMAIN = "LOGIN_TO_UA_DOMAIN";
    public static final String LOGIN_TO_UKR_DOMAIN = "LOGIN_TO_UKR_DOMAIN";
    public static final String LOGIN_TO_VIRGILIO_DOMAIN = "LOGIN_TO_VIRGILIO_DOMAIN";
    public static final String LOGIN_TO_VK_CONNECT_DOMAIN = "LOGIN_TO_VKCONNECT";
    public static final String LOGIN_TO_WEB_DOMAIN = "LOGIN_TO_WEB_DOMAIN";
    public static final String LOGIN_TO_WP_PL_DOMAIN = "LOGIN_TO_WP_PL_DOMAIN";
    public static final String LOGIN_TO_YAHOO_BR_DOMAIN = "LOGIN_TO_YAHOO_BR_DOMAIN";
    public static final String LOGIN_TO_YAHOO_CA_DOMAIN = "LOGIN_TO_YAHOO_CA_DOMAIN";
    public static final String LOGIN_TO_YAHOO_DOMAIN = "LOGIN_TO_YAHOO_DOMAIN";
    public static final String LOGIN_TO_YAHOO_ID_DOMAIN = "LOGIN_TO_YAHOO_ID_DOMAIN";
    public static final String LOGIN_TO_YAHOO_JP_DOMAIN = "LOGIN_TO_YAHOO_JP_DOMAIN";
    public static final String LOGIN_TO_YAHOO_MX_DOMAIN = "LOGIN_TO_YAHOO_MX_DOMAIN";
    public static final String LOGIN_TO_YAHOO_UK_DOMAIN = "LOGIN_TO_YAHOO_UK_DOMAIN";
    public static final String LOGIN_TO_YANDEX_DOMAIN = "LOGIN_TO_YANDEX_DOMAIN";
    public static final String LOGIN_TO_YEAH_DOMAIN = "LOGIN_TO_YEAH_DOMAIN";
    public static final String MARK_TO_REMOVE_KEY = "mark_to_remove";
    public static final String MARK_TO_REMOVE_VALUE_TRUE = "remove_it";
    public static final String MOVE_TO_REG_PARAMS = "move_to_reg_params";
    public static final String MYCOM_SMS_ACCOUNT_TYPE = "mycom_sms_accountType";
    public static final String NEED_ACCESS_TOKEN = "need_access";
    public static final String NEED_FORCE_CREATE_COLLECTOR = "NEED_FORCE_CREATE_COLLECTOR";
    public static final String PARAM_EMAIL_SERVICE_TYPE = "EMAIL_SERVICE_TYPE";
    public static final String PHONE_TOKEN = "phone_token";
    public static final String REGISTER_NEW_MYCOM_ACCOUNT = "REGISTER_NEW_MYCOM_ACCOUNT";
    static final int REQUEST_CODE_DOREGISTRATION = 28;
    static final int REQUEST_CODE_DOREGISTRATION_GOOGLE_OAUTH = 29;
    static final int REQUEST_CODE_DOREGISTRATION_OUTLOOK_OAUTH = 30;
    static final int REQUEST_CODE_DOREGISTRATION_VK_OAUTH = 33;
    static final int REQUEST_CODE_DOREGISTRATION_YAHOO_OAUTH = 31;
    static final int REQUEST_CODE_DOREGISTRATION_YANDEX_OAUTH = 32;
    public static final String RESTORE_AUTH_FLOW_PHONE_NUMBER = "REGISTER_NEW_MYCOM_ACCOUNT";
    static final int RESULT_CODE_START_LOGIN = 14;
    public static final String SHOULD_CHECK_LOGIN_STATUS = "should_check_login_status";
    public static final String SHOULD_RESET_PASSWORD = "should_reset_password";
    public static final String SIMPLE_LOGIN_TO_MAILRU = "SIMPLE_LOGIN_TO_MAILRU";
    public static final String SSO_AUTH_AG_TOKEN = "key_sso_auth_ag_token";
    private static final String UNICODE = "\\xAA\\xB5\\xBA\\xC0-\\xD6\\xD8-\\xF6\\xF8-\\u02C1\\u02C6-\\u02D1\\u02E0-\\u02E4\\u02EC\\u02EE\\u0370-\\u0374\\u0376\\u0377\\u037A-\\u037D\\u0386\\u0388-\\u038A\\u038C\\u038E-\\u03A1\\u03A3-\\u03F5\\u03F7-\\u0481\\u048A-\\u0527\\u0531-\\u0556\\u0559\\u0561-\\u0587\\u05D0-\\u05EA\\u05F0-\\u05F2\\u0620-\\u064A\\u066E\\u066F\\u0671-\\u06D3\\u06D5\\u06E5\\u06E6\\u06EE\\u06EF\\u06FA-\\u06FC\\u06FF\\u0710\\u0712-\\u072F\\u074D-\\u07A5\\u07B1\\u07CA-\\u07EA\\u07F4\\u07F5\\u07FA\\u0800-\\u0815\\u081A\\u0824\\u0828\\u0840-\\u0858\\u08A0\\u08A2-\\u08AC\\u0904-\\u0939\\u093D\\u0950\\u0958-\\u0961\\u0971-\\u0977\\u0979-\\u097F\\u0985-\\u098C\\u098F\\u0990\\u0993-\\u09A8\\u09AA-\\u09B0\\u09B2\\u09B6-\\u09B9\\u09BD\\u09CE\\u09DC\\u09DD\\u09DF-\\u09E1\\u09F0\\u09F1\\u0A05-\\u0A0A\\u0A0F\\u0A10\\u0A13-\\u0A28\\u0A2A-\\u0A30\\u0A32\\u0A33\\u0A35\\u0A36\\u0A38\\u0A39\\u0A59-\\u0A5C\\u0A5E\\u0A72-\\u0A74\\u0A85-\\u0A8D\\u0A8F-\\u0A91\\u0A93-\\u0AA8\\u0AAA-\\u0AB0\\u0AB2\\u0AB3\\u0AB5-\\u0AB9\\u0ABD\\u0AD0\\u0AE0\\u0AE1\\u0B05-\\u0B0C\\u0B0F\\u0B10\\u0B13-\\u0B28\\u0B2A-\\u0B30\\u0B32\\u0B33\\u0B35-\\u0B39\\u0B3D\\u0B5C\\u0B5D\\u0B5F-\\u0B61\\u0B71\\u0B83\\u0B85-\\u0B8A\\u0B8E-\\u0B90\\u0B92-\\u0B95\\u0B99\\u0B9A\\u0B9C\\u0B9E\\u0B9F\\u0BA3\\u0BA4\\u0BA8-\\u0BAA\\u0BAE-\\u0BB9\\u0BD0\\u0C05-\\u0C0C\\u0C0E-\\u0C10\\u0C12-\\u0C28\\u0C2A-\\u0C33\\u0C35-\\u0C39\\u0C3D\\u0C58\\u0C59\\u0C60\\u0C61\\u0C85-\\u0C8C\\u0C8E-\\u0C90\\u0C92-\\u0CA8\\u0CAA-\\u0CB3\\u0CB5-\\u0CB9\\u0CBD\\u0CDE\\u0CE0\\u0CE1\\u0CF1\\u0CF2\\u0D05-\\u0D0C\\u0D0E-\\u0D10\\u0D12-\\u0D3A\\u0D3D\\u0D4E\\u0D60\\u0D61\\u0D7A-\\u0D7F\\u0D85-\\u0D96\\u0D9A-\\u0DB1\\u0DB3-\\u0DBB\\u0DBD\\u0DC0-\\u0DC6\\u0E01-\\u0E30\\u0E32\\u0E33\\u0E40-\\u0E46\\u0E81\\u0E82\\u0E84\\u0E87\\u0E88\\u0E8A\\u0E8D\\u0E94-\\u0E97\\u0E99-\\u0E9F\\u0EA1-\\u0EA3\\u0EA5\\u0EA7\\u0EAA\\u0EAB\\u0EAD-\\u0EB0\\u0EB2\\u0EB3\\u0EBD\\u0EC0-\\u0EC4\\u0EC6\\u0EDC-\\u0EDF\\u0F00\\u0F40-\\u0F47\\u0F49-\\u0F6C\\u0F88-\\u0F8C\\u1000-\\u102A\\u103F\\u1050-\\u1055\\u105A-\\u105D\\u1061\\u1065\\u1066\\u106E-\\u1070\\u1075-\\u1081\\u108E\\u10A0-\\u10C5\\u10C7\\u10CD\\u10D0-\\u10FA\\u10FC-\\u1248\\u124A-\\u124D\\u1250-\\u1256\\u1258\\u125A-\\u125D\\u1260-\\u1288\\u128A-\\u128D\\u1290-\\u12B0\\u12B2-\\u12B5\\u12B8-\\u12BE\\u12C0\\u12C2-\\u12C5\\u12C8-\\u12D6\\u12D8-\\u1310\\u1312-\\u1315\\u1318-\\u135A\\u1380-\\u138F\\u13A0-\\u13F4\\u1401-\\u166C\\u166F-\\u167F\\u1681-\\u169A\\u16A0-\\u16EA\\u1700-\\u170C\\u170E-\\u1711\\u1720-\\u1731\\u1740-\\u1751\\u1760-\\u176C\\u176E-\\u1770\\u1780-\\u17B3\\u17D7\\u17DC\\u1820-\\u1877\\u1880-\\u18A8\\u18AA\\u18B0-\\u18F5\\u1900-\\u191C\\u1950-\\u196D\\u1970-\\u1974\\u1980-\\u19AB\\u19C1-\\u19C7\\u1A00-\\u1A16\\u1A20-\\u1A54\\u1AA7\\u1B05-\\u1B33\\u1B45-\\u1B4B\\u1B83-\\u1BA0\\u1BAE\\u1BAF\\u1BBA-\\u1BE5\\u1C00-\\u1C23\\u1C4D-\\u1C4F\\u1C5A-\\u1C7D\\u1CE9-\\u1CEC\\u1CEE-\\u1CF1\\u1CF5\\u1CF6\\u1D00-\\u1DBF\\u1E00-\\u1F15\\u1F18-\\u1F1D\\u1F20-\\u1F45\\u1F48-\\u1F4D\\u1F50-\\u1F57\\u1F59\\u1F5B\\u1F5D\\u1F5F-\\u1F7D\\u1F80-\\u1FB4\\u1FB6-\\u1FBC\\u1FBE\\u1FC2-\\u1FC4\\u1FC6-\\u1FCC\\u1FD0-\\u1FD3\\u1FD6-\\u1FDB\\u1FE0-\\u1FEC\\u1FF2-\\u1FF4\\u1FF6-\\u1FFC\\u2071\\u207F\\u2090-\\u209C\\u2102\\u2107\\u210A-\\u2113\\u2115\\u2119-\\u211D\\u2124\\u2126\\u2128\\u212A-\\u212D\\u212F-\\u2139\\u213C-\\u213F\\u2145-\\u2149\\u214E\\u2183\\u2184\\u2C00-\\u2C2E\\u2C30-\\u2C5E\\u2C60-\\u2CE4\\u2CEB-\\u2CEE\\u2CF2\\u2CF3\\u2D00-\\u2D25\\u2D27\\u2D2D\\u2D30-\\u2D67\\u2D6F\\u2D80-\\u2D96\\u2DA0-\\u2DA6\\u2DA8-\\u2DAE\\u2DB0-\\u2DB6\\u2DB8-\\u2DBE\\u2DC0-\\u2DC6\\u2DC8-\\u2DCE\\u2DD0-\\u2DD6\\u2DD8-\\u2DDE\\u2E2F\\u3005\\u3006\\u3031-\\u3035\\u303B\\u303C\\u3041-\\u3096\\u309D-\\u309F\\u30A1-\\u30FA\\u30FC-\\u30FF\\u3105-\\u312D\\u3131-\\u318E\\u31A0-\\u31BA\\u31F0-\\u31FF\\u3400-\\u4DB5\\u4E00-\\u9FCC\\uA000-\\uA48C\\uA4D0-\\uA4FD\\uA500-\\uA60C\\uA610-\\uA61F\\uA62A\\uA62B\\uA640-\\uA66E\\uA67F-\\uA697\\uA6A0-\\uA6E5\\uA717-\\uA71F\\uA722-\\uA788\\uA78B-\\uA78E\\uA790-\\uA793\\uA7A0-\\uA7AA\\uA7F8-\\uA801\\uA803-\\uA805\\uA807-\\uA80A\\uA80C-\\uA822\\uA840-\\uA873\\uA882-\\uA8B3\\uA8F2-\\uA8F7\\uA8FB\\uA90A-\\uA925\\uA930-\\uA946\\uA960-\\uA97C\\uA984-\\uA9B2\\uA9CF\\uAA00-\\uAA28\\uAA40-\\uAA42\\uAA44-\\uAA4B\\uAA60-\\uAA76\\uAA7A\\uAA80-\\uAAAF\\uAAB1\\uAAB5\\uAAB6\\uAAB9-\\uAABD\\uAAC0\\uAAC2\\uAADB-\\uAADD\\uAAE0-\\uAAEA\\uAAF2-\\uAAF4\\uAB01-\\uAB06\\uAB09-\\uAB0E\\uAB11-\\uAB16\\uAB20-\\uAB26\\uAB28-\\uAB2E\\uABC0-\\uABE2\\uAC00-\\uD7A3\\uD7B0-\\uD7C6\\uD7CB-\\uD7FB\\uF900-\\uFA6D\\uFA70-\\uFAD9\\uFB00-\\uFB06\\uFB13-\\uFB17\\uFB1D\\uFB1F-\\uFB28\\uFB2A-\\uFB36\\uFB38-\\uFB3C\\uFB3E\\uFB40\\uFB41\\uFB43\\uFB44\\uFB46-\\uFBB1\\uFBD3-\\uFD3D\\uFD50-\\uFD8F\\uFD92-\\uFDC7\\uFDF0-\\uFDFB\\uFE70-\\uFE74\\uFE76-\\uFEFC\\uFF21-\\uFF3A\\uFF41-\\uFF5A\\uFF66-\\uFFBE\\uFFC2-\\uFFC7\\uFFCA-\\uFFCF\\uFFD2-\\uFFD7\\uFFDA-\\uFFDC";
    public static final String VALUE_NEED_SEND_SERVER_PARAMS = "need_send_server_params";
    public static final String VALUE_TRANSPORT_TYPE_IMAP = "IMAP";
    public static final String VALUE_UNAUTHORIZED = "value_unauthorized";
    public static final String VK_PASSWORD_AUTH_AG_TOKEN = "key_vk_password_auth_ag_token";
    public static AccountManagerWrapper mAccountManagerWrapper;
    public static boolean sIsInDebugMode;
    private final Context mContext;
    private final LogFilter mLogFilter;
    private static final Log LOG = Log.getLog("Authenticator");
    private static final Pattern RE_EMAIL_PATTERN = Pattern.compile("([\\-\\w.+!#$%&\"*=/?^_`|~{}]+)@([\\w.а-яё\\-\\xAA\\xB5\\xBA\\xC0-\\xD6\\xD8-\\xF6\\xF8-\\u02C1\\u02C6-\\u02D1\\u02E0-\\u02E4\\u02EC\\u02EE\\u0370-\\u0374\\u0376\\u0377\\u037A-\\u037D\\u0386\\u0388-\\u038A\\u038C\\u038E-\\u03A1\\u03A3-\\u03F5\\u03F7-\\u0481\\u048A-\\u0527\\u0531-\\u0556\\u0559\\u0561-\\u0587\\u05D0-\\u05EA\\u05F0-\\u05F2\\u0620-\\u064A\\u066E\\u066F\\u0671-\\u06D3\\u06D5\\u06E5\\u06E6\\u06EE\\u06EF\\u06FA-\\u06FC\\u06FF\\u0710\\u0712-\\u072F\\u074D-\\u07A5\\u07B1\\u07CA-\\u07EA\\u07F4\\u07F5\\u07FA\\u0800-\\u0815\\u081A\\u0824\\u0828\\u0840-\\u0858\\u08A0\\u08A2-\\u08AC\\u0904-\\u0939\\u093D\\u0950\\u0958-\\u0961\\u0971-\\u0977\\u0979-\\u097F\\u0985-\\u098C\\u098F\\u0990\\u0993-\\u09A8\\u09AA-\\u09B0\\u09B2\\u09B6-\\u09B9\\u09BD\\u09CE\\u09DC\\u09DD\\u09DF-\\u09E1\\u09F0\\u09F1\\u0A05-\\u0A0A\\u0A0F\\u0A10\\u0A13-\\u0A28\\u0A2A-\\u0A30\\u0A32\\u0A33\\u0A35\\u0A36\\u0A38\\u0A39\\u0A59-\\u0A5C\\u0A5E\\u0A72-\\u0A74\\u0A85-\\u0A8D\\u0A8F-\\u0A91\\u0A93-\\u0AA8\\u0AAA-\\u0AB0\\u0AB2\\u0AB3\\u0AB5-\\u0AB9\\u0ABD\\u0AD0\\u0AE0\\u0AE1\\u0B05-\\u0B0C\\u0B0F\\u0B10\\u0B13-\\u0B28\\u0B2A-\\u0B30\\u0B32\\u0B33\\u0B35-\\u0B39\\u0B3D\\u0B5C\\u0B5D\\u0B5F-\\u0B61\\u0B71\\u0B83\\u0B85-\\u0B8A\\u0B8E-\\u0B90\\u0B92-\\u0B95\\u0B99\\u0B9A\\u0B9C\\u0B9E\\u0B9F\\u0BA3\\u0BA4\\u0BA8-\\u0BAA\\u0BAE-\\u0BB9\\u0BD0\\u0C05-\\u0C0C\\u0C0E-\\u0C10\\u0C12-\\u0C28\\u0C2A-\\u0C33\\u0C35-\\u0C39\\u0C3D\\u0C58\\u0C59\\u0C60\\u0C61\\u0C85-\\u0C8C\\u0C8E-\\u0C90\\u0C92-\\u0CA8\\u0CAA-\\u0CB3\\u0CB5-\\u0CB9\\u0CBD\\u0CDE\\u0CE0\\u0CE1\\u0CF1\\u0CF2\\u0D05-\\u0D0C\\u0D0E-\\u0D10\\u0D12-\\u0D3A\\u0D3D\\u0D4E\\u0D60\\u0D61\\u0D7A-\\u0D7F\\u0D85-\\u0D96\\u0D9A-\\u0DB1\\u0DB3-\\u0DBB\\u0DBD\\u0DC0-\\u0DC6\\u0E01-\\u0E30\\u0E32\\u0E33\\u0E40-\\u0E46\\u0E81\\u0E82\\u0E84\\u0E87\\u0E88\\u0E8A\\u0E8D\\u0E94-\\u0E97\\u0E99-\\u0E9F\\u0EA1-\\u0EA3\\u0EA5\\u0EA7\\u0EAA\\u0EAB\\u0EAD-\\u0EB0\\u0EB2\\u0EB3\\u0EBD\\u0EC0-\\u0EC4\\u0EC6\\u0EDC-\\u0EDF\\u0F00\\u0F40-\\u0F47\\u0F49-\\u0F6C\\u0F88-\\u0F8C\\u1000-\\u102A\\u103F\\u1050-\\u1055\\u105A-\\u105D\\u1061\\u1065\\u1066\\u106E-\\u1070\\u1075-\\u1081\\u108E\\u10A0-\\u10C5\\u10C7\\u10CD\\u10D0-\\u10FA\\u10FC-\\u1248\\u124A-\\u124D\\u1250-\\u1256\\u1258\\u125A-\\u125D\\u1260-\\u1288\\u128A-\\u128D\\u1290-\\u12B0\\u12B2-\\u12B5\\u12B8-\\u12BE\\u12C0\\u12C2-\\u12C5\\u12C8-\\u12D6\\u12D8-\\u1310\\u1312-\\u1315\\u1318-\\u135A\\u1380-\\u138F\\u13A0-\\u13F4\\u1401-\\u166C\\u166F-\\u167F\\u1681-\\u169A\\u16A0-\\u16EA\\u1700-\\u170C\\u170E-\\u1711\\u1720-\\u1731\\u1740-\\u1751\\u1760-\\u176C\\u176E-\\u1770\\u1780-\\u17B3\\u17D7\\u17DC\\u1820-\\u1877\\u1880-\\u18A8\\u18AA\\u18B0-\\u18F5\\u1900-\\u191C\\u1950-\\u196D\\u1970-\\u1974\\u1980-\\u19AB\\u19C1-\\u19C7\\u1A00-\\u1A16\\u1A20-\\u1A54\\u1AA7\\u1B05-\\u1B33\\u1B45-\\u1B4B\\u1B83-\\u1BA0\\u1BAE\\u1BAF\\u1BBA-\\u1BE5\\u1C00-\\u1C23\\u1C4D-\\u1C4F\\u1C5A-\\u1C7D\\u1CE9-\\u1CEC\\u1CEE-\\u1CF1\\u1CF5\\u1CF6\\u1D00-\\u1DBF\\u1E00-\\u1F15\\u1F18-\\u1F1D\\u1F20-\\u1F45\\u1F48-\\u1F4D\\u1F50-\\u1F57\\u1F59\\u1F5B\\u1F5D\\u1F5F-\\u1F7D\\u1F80-\\u1FB4\\u1FB6-\\u1FBC\\u1FBE\\u1FC2-\\u1FC4\\u1FC6-\\u1FCC\\u1FD0-\\u1FD3\\u1FD6-\\u1FDB\\u1FE0-\\u1FEC\\u1FF2-\\u1FF4\\u1FF6-\\u1FFC\\u2071\\u207F\\u2090-\\u209C\\u2102\\u2107\\u210A-\\u2113\\u2115\\u2119-\\u211D\\u2124\\u2126\\u2128\\u212A-\\u212D\\u212F-\\u2139\\u213C-\\u213F\\u2145-\\u2149\\u214E\\u2183\\u2184\\u2C00-\\u2C2E\\u2C30-\\u2C5E\\u2C60-\\u2CE4\\u2CEB-\\u2CEE\\u2CF2\\u2CF3\\u2D00-\\u2D25\\u2D27\\u2D2D\\u2D30-\\u2D67\\u2D6F\\u2D80-\\u2D96\\u2DA0-\\u2DA6\\u2DA8-\\u2DAE\\u2DB0-\\u2DB6\\u2DB8-\\u2DBE\\u2DC0-\\u2DC6\\u2DC8-\\u2DCE\\u2DD0-\\u2DD6\\u2DD8-\\u2DDE\\u2E2F\\u3005\\u3006\\u3031-\\u3035\\u303B\\u303C\\u3041-\\u3096\\u309D-\\u309F\\u30A1-\\u30FA\\u30FC-\\u30FF\\u3105-\\u312D\\u3131-\\u318E\\u31A0-\\u31BA\\u31F0-\\u31FF\\u3400-\\u4DB5\\u4E00-\\u9FCC\\uA000-\\uA48C\\uA4D0-\\uA4FD\\uA500-\\uA60C\\uA610-\\uA61F\\uA62A\\uA62B\\uA640-\\uA66E\\uA67F-\\uA697\\uA6A0-\\uA6E5\\uA717-\\uA71F\\uA722-\\uA788\\uA78B-\\uA78E\\uA790-\\uA793\\uA7A0-\\uA7AA\\uA7F8-\\uA801\\uA803-\\uA805\\uA807-\\uA80A\\uA80C-\\uA822\\uA840-\\uA873\\uA882-\\uA8B3\\uA8F2-\\uA8F7\\uA8FB\\uA90A-\\uA925\\uA930-\\uA946\\uA960-\\uA97C\\uA984-\\uA9B2\\uA9CF\\uAA00-\\uAA28\\uAA40-\\uAA42\\uAA44-\\uAA4B\\uAA60-\\uAA76\\uAA7A\\uAA80-\\uAAAF\\uAAB1\\uAAB5\\uAAB6\\uAAB9-\\uAABD\\uAAC0\\uAAC2\\uAADB-\\uAADD\\uAAE0-\\uAAEA\\uAAF2-\\uAAF4\\uAB01-\\uAB06\\uAB09-\\uAB0E\\uAB11-\\uAB16\\uAB20-\\uAB26\\uAB28-\\uAB2E\\uABC0-\\uABE2\\uAC00-\\uD7A3\\uD7B0-\\uD7C6\\uD7CB-\\uD7FB\\uF900-\\uFA6D\\uFA70-\\uFAD9\\uFB00-\\uFB06\\uFB13-\\uFB17\\uFB1D\\uFB1F-\\uFB28\\uFB2A-\\uFB36\\uFB38-\\uFB3C\\uFB3E\\uFB40\\uFB41\\uFB43\\uFB44\\uFB46-\\uFBB1\\uFBD3-\\uFD3D\\uFD50-\\uFD8F\\uFD92-\\uFDC7\\uFDF0-\\uFDFB\\uFE70-\\uFE74\\uFE76-\\uFEFC\\uFF21-\\uFF3A\\uFF41-\\uFF5A\\uFF66-\\uFFBE\\uFFC2-\\uFFC7\\uFFCA-\\uFFCF\\uFFD2-\\uFFD7\\uFFDA-\\uFFDC]+)\\.[\\wа-яё\\xAA\\xB5\\xBA\\xC0-\\xD6\\xD8-\\xF6\\xF8-\\u02C1\\u02C6-\\u02D1\\u02E0-\\u02E4\\u02EC\\u02EE\\u0370-\\u0374\\u0376\\u0377\\u037A-\\u037D\\u0386\\u0388-\\u038A\\u038C\\u038E-\\u03A1\\u03A3-\\u03F5\\u03F7-\\u0481\\u048A-\\u0527\\u0531-\\u0556\\u0559\\u0561-\\u0587\\u05D0-\\u05EA\\u05F0-\\u05F2\\u0620-\\u064A\\u066E\\u066F\\u0671-\\u06D3\\u06D5\\u06E5\\u06E6\\u06EE\\u06EF\\u06FA-\\u06FC\\u06FF\\u0710\\u0712-\\u072F\\u074D-\\u07A5\\u07B1\\u07CA-\\u07EA\\u07F4\\u07F5\\u07FA\\u0800-\\u0815\\u081A\\u0824\\u0828\\u0840-\\u0858\\u08A0\\u08A2-\\u08AC\\u0904-\\u0939\\u093D\\u0950\\u0958-\\u0961\\u0971-\\u0977\\u0979-\\u097F\\u0985-\\u098C\\u098F\\u0990\\u0993-\\u09A8\\u09AA-\\u09B0\\u09B2\\u09B6-\\u09B9\\u09BD\\u09CE\\u09DC\\u09DD\\u09DF-\\u09E1\\u09F0\\u09F1\\u0A05-\\u0A0A\\u0A0F\\u0A10\\u0A13-\\u0A28\\u0A2A-\\u0A30\\u0A32\\u0A33\\u0A35\\u0A36\\u0A38\\u0A39\\u0A59-\\u0A5C\\u0A5E\\u0A72-\\u0A74\\u0A85-\\u0A8D\\u0A8F-\\u0A91\\u0A93-\\u0AA8\\u0AAA-\\u0AB0\\u0AB2\\u0AB3\\u0AB5-\\u0AB9\\u0ABD\\u0AD0\\u0AE0\\u0AE1\\u0B05-\\u0B0C\\u0B0F\\u0B10\\u0B13-\\u0B28\\u0B2A-\\u0B30\\u0B32\\u0B33\\u0B35-\\u0B39\\u0B3D\\u0B5C\\u0B5D\\u0B5F-\\u0B61\\u0B71\\u0B83\\u0B85-\\u0B8A\\u0B8E-\\u0B90\\u0B92-\\u0B95\\u0B99\\u0B9A\\u0B9C\\u0B9E\\u0B9F\\u0BA3\\u0BA4\\u0BA8-\\u0BAA\\u0BAE-\\u0BB9\\u0BD0\\u0C05-\\u0C0C\\u0C0E-\\u0C10\\u0C12-\\u0C28\\u0C2A-\\u0C33\\u0C35-\\u0C39\\u0C3D\\u0C58\\u0C59\\u0C60\\u0C61\\u0C85-\\u0C8C\\u0C8E-\\u0C90\\u0C92-\\u0CA8\\u0CAA-\\u0CB3\\u0CB5-\\u0CB9\\u0CBD\\u0CDE\\u0CE0\\u0CE1\\u0CF1\\u0CF2\\u0D05-\\u0D0C\\u0D0E-\\u0D10\\u0D12-\\u0D3A\\u0D3D\\u0D4E\\u0D60\\u0D61\\u0D7A-\\u0D7F\\u0D85-\\u0D96\\u0D9A-\\u0DB1\\u0DB3-\\u0DBB\\u0DBD\\u0DC0-\\u0DC6\\u0E01-\\u0E30\\u0E32\\u0E33\\u0E40-\\u0E46\\u0E81\\u0E82\\u0E84\\u0E87\\u0E88\\u0E8A\\u0E8D\\u0E94-\\u0E97\\u0E99-\\u0E9F\\u0EA1-\\u0EA3\\u0EA5\\u0EA7\\u0EAA\\u0EAB\\u0EAD-\\u0EB0\\u0EB2\\u0EB3\\u0EBD\\u0EC0-\\u0EC4\\u0EC6\\u0EDC-\\u0EDF\\u0F00\\u0F40-\\u0F47\\u0F49-\\u0F6C\\u0F88-\\u0F8C\\u1000-\\u102A\\u103F\\u1050-\\u1055\\u105A-\\u105D\\u1061\\u1065\\u1066\\u106E-\\u1070\\u1075-\\u1081\\u108E\\u10A0-\\u10C5\\u10C7\\u10CD\\u10D0-\\u10FA\\u10FC-\\u1248\\u124A-\\u124D\\u1250-\\u1256\\u1258\\u125A-\\u125D\\u1260-\\u1288\\u128A-\\u128D\\u1290-\\u12B0\\u12B2-\\u12B5\\u12B8-\\u12BE\\u12C0\\u12C2-\\u12C5\\u12C8-\\u12D6\\u12D8-\\u1310\\u1312-\\u1315\\u1318-\\u135A\\u1380-\\u138F\\u13A0-\\u13F4\\u1401-\\u166C\\u166F-\\u167F\\u1681-\\u169A\\u16A0-\\u16EA\\u1700-\\u170C\\u170E-\\u1711\\u1720-\\u1731\\u1740-\\u1751\\u1760-\\u176C\\u176E-\\u1770\\u1780-\\u17B3\\u17D7\\u17DC\\u1820-\\u1877\\u1880-\\u18A8\\u18AA\\u18B0-\\u18F5\\u1900-\\u191C\\u1950-\\u196D\\u1970-\\u1974\\u1980-\\u19AB\\u19C1-\\u19C7\\u1A00-\\u1A16\\u1A20-\\u1A54\\u1AA7\\u1B05-\\u1B33\\u1B45-\\u1B4B\\u1B83-\\u1BA0\\u1BAE\\u1BAF\\u1BBA-\\u1BE5\\u1C00-\\u1C23\\u1C4D-\\u1C4F\\u1C5A-\\u1C7D\\u1CE9-\\u1CEC\\u1CEE-\\u1CF1\\u1CF5\\u1CF6\\u1D00-\\u1DBF\\u1E00-\\u1F15\\u1F18-\\u1F1D\\u1F20-\\u1F45\\u1F48-\\u1F4D\\u1F50-\\u1F57\\u1F59\\u1F5B\\u1F5D\\u1F5F-\\u1F7D\\u1F80-\\u1FB4\\u1FB6-\\u1FBC\\u1FBE\\u1FC2-\\u1FC4\\u1FC6-\\u1FCC\\u1FD0-\\u1FD3\\u1FD6-\\u1FDB\\u1FE0-\\u1FEC\\u1FF2-\\u1FF4\\u1FF6-\\u1FFC\\u2071\\u207F\\u2090-\\u209C\\u2102\\u2107\\u210A-\\u2113\\u2115\\u2119-\\u211D\\u2124\\u2126\\u2128\\u212A-\\u212D\\u212F-\\u2139\\u213C-\\u213F\\u2145-\\u2149\\u214E\\u2183\\u2184\\u2C00-\\u2C2E\\u2C30-\\u2C5E\\u2C60-\\u2CE4\\u2CEB-\\u2CEE\\u2CF2\\u2CF3\\u2D00-\\u2D25\\u2D27\\u2D2D\\u2D30-\\u2D67\\u2D6F\\u2D80-\\u2D96\\u2DA0-\\u2DA6\\u2DA8-\\u2DAE\\u2DB0-\\u2DB6\\u2DB8-\\u2DBE\\u2DC0-\\u2DC6\\u2DC8-\\u2DCE\\u2DD0-\\u2DD6\\u2DD8-\\u2DDE\\u2E2F\\u3005\\u3006\\u3031-\\u3035\\u303B\\u303C\\u3041-\\u3096\\u309D-\\u309F\\u30A1-\\u30FA\\u30FC-\\u30FF\\u3105-\\u312D\\u3131-\\u318E\\u31A0-\\u31BA\\u31F0-\\u31FF\\u3400-\\u4DB5\\u4E00-\\u9FCC\\uA000-\\uA48C\\uA4D0-\\uA4FD\\uA500-\\uA60C\\uA610-\\uA61F\\uA62A\\uA62B\\uA640-\\uA66E\\uA67F-\\uA697\\uA6A0-\\uA6E5\\uA717-\\uA71F\\uA722-\\uA788\\uA78B-\\uA78E\\uA790-\\uA793\\uA7A0-\\uA7AA\\uA7F8-\\uA801\\uA803-\\uA805\\uA807-\\uA80A\\uA80C-\\uA822\\uA840-\\uA873\\uA882-\\uA8B3\\uA8F2-\\uA8F7\\uA8FB\\uA90A-\\uA925\\uA930-\\uA946\\uA960-\\uA97C\\uA984-\\uA9B2\\uA9CF\\uAA00-\\uAA28\\uAA40-\\uAA42\\uAA44-\\uAA4B\\uAA60-\\uAA76\\uAA7A\\uAA80-\\uAAAF\\uAAB1\\uAAB5\\uAAB6\\uAAB9-\\uAABD\\uAAC0\\uAAC2\\uAADB-\\uAADD\\uAAE0-\\uAAEA\\uAAF2-\\uAAF4\\uAB01-\\uAB06\\uAB09-\\uAB0E\\uAB11-\\uAB16\\uAB20-\\uAB26\\uAB28-\\uAB2E\\uABC0-\\uABE2\\uAC00-\\uD7A3\\uD7B0-\\uD7C6\\uD7CB-\\uD7FB\\uF900-\\uFA6D\\uFA70-\\uFAD9\\uFB00-\\uFB06\\uFB13-\\uFB17\\uFB1D\\uFB1F-\\uFB28\\uFB2A-\\uFB36\\uFB38-\\uFB3C\\uFB3E\\uFB40\\uFB41\\uFB43\\uFB44\\uFB46-\\uFBB1\\uFBD3-\\uFD3D\\uFD50-\\uFD8F\\uFD92-\\uFDC7\\uFDF0-\\uFDFB\\uFE70-\\uFE74\\uFE76-\\uFEFC\\uFF21-\\uFF3A\\uFF41-\\uFF5A\\uFF66-\\uFFBE\\uFFC2-\\uFFC7\\uFFCA-\\uFFCF\\uFFD2-\\uFFD7\\uFFDA-\\uFFDC]+");
    static final AuthVisitor sVisitor = new AuthTypeVisitor();

    /* JADX INFO: compiled from: ProGuard */
    public static class AuthTypeVisitor implements AuthVisitor {
        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(HttpsAuthorizeLoginRequest httpsAuthorizeLoginRequest, Bundle bundle) {
            Type type = Type.DEFAULT;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(AuthorizeTokenRequest authorizeTokenRequest, Bundle bundle) {
            Type type = Type.DEFAULT;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            bundle.putString(Authenticator.EXTRA_STATISTIC_MESSAGE, "Login_TwoFactor_Success");
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(GoogleOauth2SendAgentRequest googleOauth2SendAgentRequest, Bundle bundle) {
            Type type = Type.OAUTH;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(OutlookOauthSendAgentRequest outlookOauthSendAgentRequest, Bundle bundle) {
            Type type = Type.OUTLOOK_OAUTH;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(YahooOauth2SendAgentRequest yahooOauth2SendAgentRequest, Bundle bundle) {
            Type type = Type.YAHOO_OAUTH;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(YandexOauth2SendAgentRequest yandexOauth2SendAgentRequest, Bundle bundle) {
            Type type = Type.YANDEX_OAUTH;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(VKOauth2SendAgentRequest vKOauth2SendAgentRequest, Bundle bundle) {
            Type type = Type.VK_CONNECT;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(AuthPhoneToken authPhoneToken, Bundle bundle) {
            Type type = Type.SMS;
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
            return type;
        }

        @Override // ru.mail.auth.Authenticator.AuthVisitor
        public Type visit(CgiBinAuthSendAgentRequest cgiBinAuthSendAgentRequest, Bundle bundle) {
            Type typeResolveCgiBin = new AuthTypeResolver().resolveCgiBin(cgiBinAuthSendAgentRequest);
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, typeResolveCgiBin.toString());
            return typeResolveCgiBin;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public interface AuthVisitor {
        Type visit(AuthPhoneToken authPhoneToken, Bundle bundle);

        Type visit(AuthorizeTokenRequest authorizeTokenRequest, Bundle bundle);

        Type visit(CgiBinAuthSendAgentRequest cgiBinAuthSendAgentRequest, Bundle bundle);

        Type visit(GoogleOauth2SendAgentRequest googleOauth2SendAgentRequest, Bundle bundle);

        Type visit(HttpsAuthorizeLoginRequest httpsAuthorizeLoginRequest, Bundle bundle);

        Type visit(OutlookOauthSendAgentRequest outlookOauthSendAgentRequest, Bundle bundle);

        Type visit(VKOauth2SendAgentRequest vKOauth2SendAgentRequest, Bundle bundle);

        Type visit(YahooOauth2SendAgentRequest yahooOauth2SendAgentRequest, Bundle bundle);

        Type visit(YandexOauth2SendAgentRequest yandexOauth2SendAgentRequest, Bundle bundle);
    }

    /* JADX INFO: compiled from: ProGuard */
    private abstract class AuthenticatorException extends Exception {
        private static final long serialVersionUID = 1;
        private Bundle mFailureBundle;

        protected AuthenticatorException(int i10, String str) {
            Bundle bundle = new Bundle();
            this.mFailureBundle = bundle;
            bundle.putInt("errorCode", i10);
            this.mFailureBundle.putString("errorMessage", str);
        }

        public Bundle getFailureBundle() {
            return this.mFailureBundle;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public interface CredentialsValidator {
        public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");

        boolean isValid(String str, String str2);
    }

    /* JADX INFO: compiled from: ProGuard */
    private static abstract class IntentFactory {
        private static final String LOGIN_ACTIVITY_CLASS = "ru.mail.ui.auth.MailRuLoginActivity";

        public abstract Intent getAddAccountIntent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle);

        protected Intent getChooserIntent(Context context) {
            Intent intent = new Intent();
            intent.setAction(MailAccountConstants.CHOOSE_EMAIL_SERVICE);
            intent.setPackage(context.getPackageName());
            intent.addFlags(67108864);
            return intent;
        }

        public String getLoginActivityClass() {
            return LOGIN_ACTIVITY_CLASS;
        }

        public Intent getUpdateCredentialsIntent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) {
            Intent chooserIntent = getChooserIntent(context);
            chooserIntent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
            chooserIntent.putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, account.name);
            chooserIntent.putExtra(Authenticator.IS_LOGIN_EXISTING_ACCOUNT, true);
            chooserIntent.addFlags(131072);
            AuthUtil.setEmailServiceType(context, chooserIntent, account);
            return chooserIntent;
        }

        public Intent getUpdateCredentialsIntentExplicitComponent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) {
            Intent updateCredentialsIntent = getUpdateCredentialsIntent(context, accountAuthenticatorResponse, account);
            updateCredentialsIntent.setComponent(new ComponentName(context.getPackageName(), getLoginActivityClass()));
            return updateCredentialsIntent;
        }

        private IntentFactory() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class MailAddressValidator {
        public static boolean isValid(String str) {
            return !TextUtils.isEmpty(str) && Authenticator.isEmailValid(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MailCredentialsValidator implements CredentialsValidator {
        @Override // ru.mail.auth.Authenticator.CredentialsValidator
        public boolean isValid(String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return false;
            }
            return CredentialsValidator.EMAIL_ADDRESS_PATTERN.matcher(str).matches();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class MailRuAddAccountIntentFactory extends IntentFactory {
        @Override // ru.mail.auth.Authenticator.IntentFactory
        public Intent getAddAccountIntent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle) {
            Intent chooserIntent = getChooserIntent(context);
            chooserIntent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
            if (bundle != null) {
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.IS_LOGIN_EXISTING_ACCOUNT);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.IS_PREFILL_LOGIN_DATA);
                AuthUtil.proxyStringParam(chooserIntent, bundle, Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
                AuthUtil.proxyStringParam(chooserIntent, bundle, Authenticator.PARAM_EMAIL_SERVICE_TYPE);
                AuthUtil.proxyStringParam(chooserIntent, bundle, AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
                AuthUtil.proxyByteArray(chooserIntent, bundle, Authenticator.AUTH_RESTORE_PARAMS);
                AuthUtil.proxyByteArray(chooserIntent, bundle, ReturnParamsHelper.RESTORE_AUTH_PARAMS);
                AuthUtil.proxyStringParam(chooserIntent, bundle, LoginActivity.EXTRA_LOGIN_FROM);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.MOVE_TO_REG_PARAMS);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.IS_HIDE_UI_ON_START);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, "is_maual_logout");
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, ReversedMatchingVkConstants.IS_FROM_VK_APP);
            }
            return chooserIntent;
        }

        private MailRuAddAccountIntentFactory() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class MyComAddAccountIntentFactory extends IntentFactory {
        @Override // ru.mail.auth.Authenticator.IntentFactory
        public Intent getAddAccountIntent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle) {
            Intent chooserIntent = getChooserIntent(context);
            chooserIntent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
            if (bundle != null) {
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, "REGISTER_NEW_MYCOM_ACCOUNT");
                AuthUtil.proxyStringParam(chooserIntent, bundle, Authenticator.PARAM_EMAIL_SERVICE_TYPE);
                AuthUtil.proxyStringParam(chooserIntent, bundle, AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
                AuthUtil.proxyStringParam(chooserIntent, bundle, "REGISTER_NEW_MYCOM_ACCOUNT");
                AuthUtil.proxyByteArray(chooserIntent, bundle, Authenticator.AUTH_RESTORE_PARAMS);
                AuthUtil.proxyByteArray(chooserIntent, bundle, ReturnParamsHelper.RESTORE_AUTH_PARAMS);
                AuthUtil.proxyStringParam(chooserIntent, bundle, LoginActivity.EXTRA_LOGIN_FROM);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.MOVE_TO_REG_PARAMS);
            }
            return chooserIntent;
        }

        private MyComAddAccountIntentFactory() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static class OAuthCredentialsValidator implements CredentialsValidator {
        OAuthCredentialsValidator() {
        }

        @Override // ru.mail.auth.Authenticator.CredentialsValidator
        public boolean isValid(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return CredentialsValidator.EMAIL_ADDRESS_PATTERN.matcher(str).matches();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class OAuthSuppressSetterGetter {
        private static final String NOTHING_MATCH_REGEXP = "^(?!.*)$";
        private static final String SUPPRESS_PATTERN = "suppress_pattern";
        private final Bundle mBundle;

        public OAuthSuppressSetterGetter(Bundle bundle) {
            this.mBundle = bundle;
        }

        @NonNull
        public Pattern getSuppressedDomainPattern() {
            return Pattern.compile(this.mBundle.getString(SUPPRESS_PATTERN, NOTHING_MATCH_REGEXP));
        }

        public void setDomainsSuppressed(@NonNull Pattern pattern) {
            this.mBundle.putString(SUPPRESS_PATTERN, pattern.toString());
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class OnPremiseAddAccountIntentFactory extends IntentFactory {
        @Override // ru.mail.auth.Authenticator.IntentFactory
        public Intent getAddAccountIntent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle) {
            Intent chooserIntent = getChooserIntent(context);
            chooserIntent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
            if (bundle != null) {
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, Authenticator.IS_LOGIN_EXISTING_ACCOUNT);
                AuthUtil.proxyStringParam(chooserIntent, bundle, Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
                AuthUtil.proxyStringParam(chooserIntent, bundle, AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
                AuthUtil.proxyByteArray(chooserIntent, bundle, Authenticator.AUTH_RESTORE_PARAMS);
                AuthUtil.proxyByteArray(chooserIntent, bundle, ReturnParamsHelper.RESTORE_AUTH_PARAMS);
                AuthUtil.proxyStringParam(chooserIntent, bundle, LoginActivity.EXTRA_LOGIN_FROM);
                AuthUtil.proxyBooleanParam(chooserIntent, bundle, "is_maual_logout");
            }
            return chooserIntent;
        }

        private OnPremiseAddAccountIntentFactory() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public enum Type {
        DEFAULT(new Supplier() { // from class: ru.mail.auth.d
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.MailCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.b0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.u();
            }
        }, new Supplier() { // from class: ru.mail.auth.l
            @Override // java.util.function.Supplier
            public final Object get() {
                return new MailO2AuthStrategy();
            }
        }, null, 28, false, "mail.ru", "corp.mail.ru", "@inbox.ru", "@bk.ru", "@list.ru", "@yandex.ru", "@rambler.ru", "@mail.ua", "@xmail.ru"),
        OAUTH(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.n
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.getGoogleOAuthStrategy();
            }
        }, new Supplier() { // from class: ru.mail.auth.p
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.getGoogleO2AuthStrategy();
            }
        }, new Supplier() { // from class: ru.mail.auth.q
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.s();
            }
        }, 29, true, "gmail.com"),
        OUTLOOK_OAUTH(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.s
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.getMicrosoftStrategy();
            }
        }, new Supplier() { // from class: ru.mail.auth.t
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.d();
            }
        }, new Supplier() { // from class: ru.mail.auth.o
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.m();
            }
        }, 30, true, "outlook.com", "hotmail.com"),
        YAHOO_OAUTH(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.v
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.o();
            }
        }, new Supplier() { // from class: ru.mail.auth.w
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.p();
            }
        }, new Supplier() { // from class: ru.mail.auth.x
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.a();
            }
        }, 31, true, "yahoo.com"),
        YANDEX_OAUTH(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.y
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.i();
            }
        }, new Supplier() { // from class: ru.mail.auth.z
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.q();
            }
        }, new Supplier() { // from class: ru.mail.auth.a0
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.b();
            }
        }, 32, true, "yandex.ru", "yandex.com", "yandex.ua", "yandex.kz", "yandex.by", "yandex.com.tr", "ya.ru"),
        VK_CONNECT(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.e
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.n();
            }
        }, new Supplier() { // from class: ru.mail.auth.f
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.g();
            }
        }, new Supplier() { // from class: ru.mail.auth.g
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.f();
            }
        }, 33, true, "vk.com"),
        SMS(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.h
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.k();
            }
        }, null, null, 0, false, "my.com"),
        WEB_AUTH_N(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.i
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.l();
            }
        }, new Supplier() { // from class: ru.mail.auth.j
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.e();
            }
        }, null, 0, false, "mail.ru", "corp.mail.ru", "@inbox.ru", "@bk.ru", "@list.ru", "@yandex.ru", "@rambler.ru", "@mail.ua"),
        QR_LOGIN(new Supplier() { // from class: ru.mail.auth.u
            @Override // java.util.function.Supplier
            public final Object get() {
                return new Authenticator.OAuthCredentialsValidator();
            }
        }, new Supplier() { // from class: ru.mail.auth.k
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.t();
            }
        }, new Supplier() { // from class: ru.mail.auth.m
            @Override // java.util.function.Supplier
            public final Object get() {
                return Authenticator.Type.j();
            }
        }, null, 0, false, "mail.ru", "corp.mail.ru", "@inbox.ru", "@bk.ru", "@list.ru", "@yandex.ru", "@rambler.ru", "@mail.ua");

        private final Supplier<CredentialsValidator> mCredentialsValidator;
        private final int mDescription;
        private final Supplier<AuthStrategy> mDirectAuthStrategy;
        private final boolean mIsOAuth;
        private final Set<String> mKnownDomains;
        private final Supplier<AuthStrategy> mOAuthStrategy;
        private final Supplier<AuthStrategy> mPopStrategy;

        Type(Supplier supplier, Supplier supplier2, Supplier supplier3, Supplier supplier4, int i10, boolean z10, String... strArr) {
            this.mCredentialsValidator = memoize(supplier);
            this.mPopStrategy = memoize(supplier2);
            this.mOAuthStrategy = memoize(supplier3);
            this.mDirectAuthStrategy = memoize(supplier4);
            this.mIsOAuth = z10;
            HashSet hashSet = new HashSet();
            this.mKnownDomains = hashSet;
            this.mDescription = i10;
            hashSet.addAll(Arrays.asList(strArr));
        }

        public static /* synthetic */ AuthStrategy a() {
            return new YahooOauth2DirectAccessAuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYahooProvider());
        }

        public static /* synthetic */ AuthStrategy b() {
            return new YandexO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYandexProvider());
        }

        public static /* synthetic */ AuthStrategy d() {
            return new MicrosoftO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createOutlookProvider());
        }

        public static /* synthetic */ AuthStrategy e() {
            return new CgiBinAuthO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createMailProvider());
        }

        public static /* synthetic */ AuthStrategy f() {
            return new VKConnectO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createVKConnectProvider());
        }

        public static /* synthetic */ AuthStrategy g() {
            return new VKConnectO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createVKConnectProvider());
        }

        public static Type getDefaultType() {
            return DEFAULT;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthStrategy getGoogleO2AuthStrategy() {
            return new GoogleO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createGoogleProvider());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthStrategy getGoogleOAuthStrategy() {
            return new GoogleAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createGoogleProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig());
        }

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public static MicrosoftAuthStrategy getMicrosoftStrategy() {
            return new MicrosoftAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createOutlookProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig());
        }

        public static Type getTypeByDomain(String str) {
            for (Type type : values()) {
                if (type.mKnownDomains.contains(str)) {
                    return type;
                }
            }
            return DEFAULT;
        }

        public static /* synthetic */ AuthStrategy i() {
            return new YandexAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYandexProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig());
        }

        public static /* synthetic */ AuthStrategy j() {
            return new CgiBinAuthO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createMailProvider());
        }

        public static /* synthetic */ AuthStrategy k() {
            return new SmsAuthStrategy(Authenticator.sVisitor);
        }

        public static /* synthetic */ AuthStrategy l() {
            return new CgiBinAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createMailProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), new WebAuthNCommandProvider());
        }

        public static /* synthetic */ AuthStrategy m() {
            return new MicrosoftOauth2DirectAccessAuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createOutlookProvider());
        }

        @Nullable
        private static <T> Supplier<T> memoize(@Nullable final Supplier<T> supplier) {
            if (supplier == null) {
                return null;
            }
            return new Supplier<T>() { // from class: ru.mail.auth.Authenticator.Type.1
                private volatile boolean initialized;
                private T value;

                @Override // java.util.function.Supplier
                public T get() {
                    if (!this.initialized) {
                        synchronized (this) {
                            try {
                                if (!this.initialized) {
                                    this.value = (T) supplier.get();
                                    this.initialized = true;
                                }
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                    return this.value;
                }
            };
        }

        public static /* synthetic */ AuthStrategy n() {
            return new VKConnectAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createVKConnectProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), AuthenticatorConfig.getInstance().isFixVkAccountBreakRefreshTokenEnabled());
        }

        public static /* synthetic */ AuthStrategy o() {
            return new YahooAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYahooProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig());
        }

        public static /* synthetic */ AuthStrategy p() {
            return new YahooO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYahooProvider());
        }

        public static /* synthetic */ AuthStrategy q() {
            return new YandexO2AuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createYandexProvider());
        }

        public static /* synthetic */ AuthStrategy s() {
            return new GoogleOauth2DirectAccessAuthStrategy(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createGoogleProvider());
        }

        public static /* synthetic */ AuthStrategy t() {
            return new CgiBinAuthStrategy(Authenticator.sVisitor, AuthenticatorConfig.getInstance().getOAuthParamsCreator().createMailProvider(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), new QrLoginCommandProvider());
        }

        public static /* synthetic */ AuthStrategy u() {
            return new MailAuthStrategy(Authenticator.sVisitor);
        }

        public CredentialsValidator getCredentialsValidator() {
            Supplier<CredentialsValidator> supplier = this.mCredentialsValidator;
            if (supplier == null) {
                return null;
            }
            return supplier.get();
        }

        public int getDescription() {
            return this.mDescription;
        }

        public AuthStrategy getDirectAuthStrategy() {
            Supplier<AuthStrategy> supplier = this.mDirectAuthStrategy;
            if (supplier == null) {
                return null;
            }
            return supplier.get();
        }

        public Set<String> getKnownDomains() {
            return this.mKnownDomains;
        }

        public AuthStrategy getMPopStrategy() {
            Supplier<AuthStrategy> supplier = this.mPopStrategy;
            if (supplier == null) {
                return null;
            }
            return supplier.get();
        }

        public AuthStrategy getOAuthStrategy() {
            Supplier<AuthStrategy> supplier = this.mOAuthStrategy;
            if (supplier == null) {
                return null;
            }
            return supplier.get();
        }

        public boolean isOAuth() {
            return this.mIsOAuth;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    private class UnSupportedAccountTypeException extends AuthenticatorException {
        private static final long serialVersionUID = 1;

        public UnSupportedAccountTypeException() {
            super(6, "error unsupported account type");
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    private class UnsupportedAuthTokenTypeException extends AuthenticatorException {
        private static final long serialVersionUID = 1;

        public UnsupportedAuthTokenTypeException() {
            super(6, "error unsupported auth token type");
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'MAIL_RU' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    public static final class ValidAccountTypes {
        private static final /* synthetic */ ValidAccountTypes[] $VALUES = $values();
        public static final ValidAccountTypes FLY;
        public static final ValidAccountTypes HOTMAIL_COM;
        public static final ValidAccountTypes ITALIA_ONLINE;
        public static final ValidAccountTypes LITE;
        public static final ValidAccountTypes MAIL_RU;
        public static final ValidAccountTypes MY_COM;
        public static final ValidAccountTypes ON_PREMISE;
        public static final ValidAccountTypes OUTLOOK;
        public static final ValidAccountTypes OUTLOOK_OLD;
        public static final ValidAccountTypes TIM;
        public static final ValidAccountTypes VK;
        public static final ValidAccountTypes WIRTUALNA_PL;
        public static final ValidAccountTypes YAHOO;
        private final String mCookieDomain;
        private final IntentFactory mIntentFactory;
        private final String value;

        private static /* synthetic */ ValidAccountTypes[] $values() {
            return new ValidAccountTypes[]{MAIL_RU, MY_COM, HOTMAIL_COM, ITALIA_ONLINE, WIRTUALNA_PL, YAHOO, OUTLOOK, OUTLOOK_OLD, TIM, FLY, LITE, ON_PREMISE, VK};
        }

        static {
            MAIL_RU = new ValidAccountTypes("MAIL_RU", 0, "ru.mail", new MailRuAddAccountIntentFactory(), "mail.ru");
            MY_COM = new ValidAccountTypes("MY_COM", 1, "com.my.mail", new MyComAddAccountIntentFactory(), "my.com");
            HOTMAIL_COM = new ValidAccountTypes("HOTMAIL_COM", 2, "park.hotm.email.app", new MyComAddAccountIntentFactory(), "my.com");
            ITALIA_ONLINE = new ValidAccountTypes("ITALIA_ONLINE", 3, "it.italiaonline.mail", new MyComAddAccountIntentFactory(), "my.com");
            WIRTUALNA_PL = new ValidAccountTypes("WIRTUALNA_PL", 4, "pl.wirtualna.mail", new MyComAddAccountIntentFactory(), "my.com");
            YAHOO = new ValidAccountTypes("YAHOO", 5, "park.yahoo.sign.in.app", new MyComAddAccountIntentFactory(), "my.com");
            OUTLOOK = new ValidAccountTypes("OUTLOOK", 6, "park.exchange.client.app", new MyComAddAccountIntentFactory(), "my.com");
            OUTLOOK_OLD = new ValidAccountTypes("OUTLOOK_OLD", 7, "park.outlook.sign.in.client", new MyComAddAccountIntentFactory(), "my.com");
            TIM = new ValidAccountTypes("TIM", 8, "tim.alice.mail.it.app", new MyComAddAccountIntentFactory(), "my.com");
            FLY = new ValidAccountTypes("FLY", 9, "com.fly.app.mail", new MyComAddAccountIntentFactory(), "my.com");
            LITE = new ValidAccountTypes("LITE", 10, "park.lite.email.client", new MyComAddAccountIntentFactory(), "my.com");
            ON_PREMISE = new ValidAccountTypes("ON_PREMISE", 11, PortalMailAppConfigurationRetrieverImpl.ON_PREMISE_PACKAGE, new OnPremiseAddAccountIntentFactory(), "mail.ru");
            VK = new ValidAccountTypes("VK", 12, "com.vk.mail", new MailRuAddAccountIntentFactory(), "vk.com");
        }

        private ValidAccountTypes(@NotNull String str, @NotNull int i10, @NotNull String str2, IntentFactory intentFactory, String str3) {
            super(str, i10);
            this.value = str2;
            this.mIntentFactory = intentFactory;
            this.mCookieDomain = str3;
        }

        public static ValidAccountTypes getEnumByValue(String str) {
            for (ValidAccountTypes validAccountTypes : values()) {
                if (validAccountTypes.getValue().equals(str)) {
                    return validAccountTypes;
                }
            }
            return null;
        }

        public static ValidAccountTypes valueOf(String str) {
            return (ValidAccountTypes) Enum.valueOf(ValidAccountTypes.class, str);
        }

        public static ValidAccountTypes[] values() {
            return (ValidAccountTypes[]) $VALUES.clone();
        }

        @NotNull
        public String getCookieDomain() {
            return this.mCookieDomain;
        }

        public Intent getIntentForAddAccount(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Bundle bundle) {
            return this.mIntentFactory.getAddAccountIntent(context, accountAuthenticatorResponse, bundle);
        }

        public Intent getIntentforUpdateCredentials(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) {
            return this.mIntentFactory.getUpdateCredentialsIntent(context, accountAuthenticatorResponse, account);
        }

        public Intent getIntentforUpdateCredentialsExplicitComponent(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) {
            return this.mIntentFactory.getUpdateCredentialsIntentExplicitComponent(context, accountAuthenticatorResponse, account);
        }

        @NotNull
        public String getValue() {
            return this.value;
        }
    }

    public Authenticator(Context context) {
        super(context);
        this.mLogFilter = new LogFilter(AuthorizeRequestCommand.MPOP_COOKIE_FORMAT, MpopTokenRequest.TOKEN_FORMAT, Formats.newJsonFormat("authtoken"), Formats.newUrlFormat("authtoken"), Formats.newJsonFormat(KEY_ACCESS_TOKEN), Formats.newUrlFormat(KEY_ACCESS_TOKEN), Formats.newJsonFormat(KEY_AUTHTOKEN_ACCESS), Formats.newUrlFormat(KEY_AUTHTOKEN_ACCESS), Formats.newJsonFormat(KEY_AUTHTOKEN_REFRESH), Formats.newUrlFormat(KEY_AUTHTOKEN_REFRESH), Formats.newJsonFormat(PHONE_TOKEN), Formats.newUrlFormat(PHONE_TOKEN));
        this.mContext = context;
    }

    private void addAuthFailMessage(Bundle bundle, Bundle bundle2) {
        if (bundle == null || !bundle.containsKey("authFailedMessage")) {
            return;
        }
        bundle2.putString("authFailedMessage", bundle.getString("authFailedMessage"));
    }

    private void checkAccountType(String str) throws AuthenticatorException {
        if (str == null || !Arrays.asList(ValidAccountTypes.values()).contains(ValidAccountTypes.getEnumByValue(str))) {
            throw new UnSupportedAccountTypeException();
        }
    }

    private void checkAuthTokenType(String str) throws AuthenticatorException {
        if (str == null || !str.equals("ru.mail")) {
            throw new UnsupportedAuthTokenTypeException();
        }
    }

    private void cleanMpopTokens(Account account) {
        AccountManagerWrapper accountManagerWrapper = getAccountManagerWrapper(this.mContext);
        accountManagerWrapper.setAuthToken(account, "ru.mail", null);
        accountManagerWrapper.setAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN, null);
    }

    public static void clearAccountDeleteState(AccountManagerWrapper accountManagerWrapper, Account account) {
        if (account != null) {
            accountManagerWrapper.setUserData(account, ACCOUNT_KEY_DELETED, null);
        }
    }

    public static void debugMode(boolean z10) {
        sIsInDebugMode = z10;
    }

    public static AccountManagerWrapper getAccountManagerWrapper(Context context, RerfreshParamsProvider rerfreshParamsProvider) {
        AccountManagerWrapper accountManagerWrapper = mAccountManagerWrapper;
        return accountManagerWrapper == null ? initAccountManagerWrapper(context.getApplicationContext(), rerfreshParamsProvider) : accountManagerWrapper;
    }

    public static Type getAccountType(String str, Bundle bundle) {
        return (bundle == null || !bundle.containsKey(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE)) ? Type.getTypeByDomain(DomainUtils.getDomain(str)) : Type.valueOf(bundle.getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE));
    }

    public static Intent getConfirmationActivityIntent(String str) {
        return new Intent(MailAccountConstants.ACTION_CONFIRMATION).setPackage(str);
    }

    public static String getCookieDomainByAccountType(String str) {
        return ValidAccountTypes.getEnumByValue(str).getCookieDomain();
    }

    static Intent getLoginActivityIntent(String str) {
        return new Intent(MailAccountConstants.ACTION_LOGIN).setPackage(str);
    }

    @Nullable
    private Bundle getMpopCookieBlocking(Account account, Bundle bundle) throws NetworkErrorException {
        return getMpopCookieBlocking(account, bundle, false);
    }

    private Bundle getMpopCookieResult(Account account) {
        Bundle bundle = new Bundle();
        bundle.putString("authAccount", account.name);
        bundle.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, account.type);
        return bundle;
    }

    static String getPassword(Bundle bundle) {
        if (bundle != null) {
            return bundle.getString(BUNDLE_PARAM_PASSWORD);
        }
        return null;
    }

    public static Intent getRegistrationActivityIntent(String str) {
        return new Intent(MailAccountConstants.ACTION_REGISTRATION).setPackage(str);
    }

    private Intent getUpdateCredentialsExplicitIntent(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) {
        Intent intentforUpdateCredentialsExplicitComponent = ValidAccountTypes.getEnumByValue(account.type).getIntentforUpdateCredentialsExplicitComponent(this.mContext, accountAuthenticatorResponse, account);
        intentforUpdateCredentialsExplicitComponent.putExtra(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, getAccountType(account.name, bundle, new OAuthSuppressSetterGetter(bundle).getSuppressedDomainPattern()).toString());
        return intentforUpdateCredentialsExplicitComponent;
    }

    private static synchronized AccountManagerWrapper initAccountManagerWrapper(Context context, RerfreshParamsProvider rerfreshParamsProvider) {
        try {
            if (mAccountManagerWrapper == null) {
                mAccountManagerWrapper = new SynchronizedAccountManagerWrapper(context, rerfreshParamsProvider);
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return mAccountManagerWrapper;
    }

    public static boolean isAccountDeleted(AccountManagerWrapper accountManagerWrapper, Account account) {
        return (account == null || accountManagerWrapper.getUserData(account, ACCOUNT_KEY_DELETED) == null) ? false : true;
    }

    public static boolean isAccountDeletedWithError(AccountManagerWrapper accountManagerWrapper, Account account) {
        return account != null && ACCOUNT_VALUE_DELETING_ERROR.equals(accountManagerWrapper.getUserData(account, ACCOUNT_KEY_DELETED));
    }

    public static boolean isAccountMarkedForDeletion(AccountManagerWrapper accountManagerWrapper, Account account) {
        return account != null && ACCOUNT_VALUE_DELETED.equals(accountManagerWrapper.getUserData(account, ACCOUNT_KEY_DELETED));
    }

    public static boolean isEmailValid(String str) {
        return !TextUtils.isEmpty(str) && RE_EMAIL_PATTERN.matcher(str).matches();
    }

    public static boolean isInDebugMode() {
        return sIsInDebugMode;
    }

    private boolean isResolved(Intent intent) {
        return this.mContext.getPackageManager().resolveActivity(intent, ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != null;
    }

    public static boolean isUserDataValid(String str, String str2) {
        try {
            return Type.getTypeByDomain(DomainUtils.getDomain(str)).getCredentialsValidator().isValid(str, str2);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static void markAccountForDeletion(AccountManagerWrapper accountManagerWrapper, Account account) {
        if (account != null) {
            accountManagerWrapper.setUserData(account, ACCOUNT_KEY_DELETED, ACCOUNT_VALUE_DELETED);
        }
    }

    public static void markAccountWithDeleteError(AccountManagerWrapper accountManagerWrapper, Account account) {
        if (account != null) {
            accountManagerWrapper.setUserData(account, ACCOUNT_KEY_DELETED, ACCOUNT_VALUE_DELETING_ERROR);
        }
    }

    private void setUnauthorizedData(Account account) {
        AccountManagerWrapper accountManagerWrapper = getAccountManagerWrapper(this.mContext.getApplicationContext());
        accountManagerWrapper.setPassword(account, null);
        accountManagerWrapper.setUserData(account, KEY_UNAUTHORIZED, VALUE_UNAUTHORIZED);
        LOG.d("setUnauthorizedData  account = " + account);
    }

    private Bundle updateCookieAndGetToken(Account account, Bundle bundle) throws NetworkErrorException {
        LOG.d("Requesting Mpop Token");
        Bundle authToken = getAuthToken(null, account, "ru.mail", bundle);
        return (authToken == null || !authToken.containsKey("authtoken")) ? authToken : getMpopCookieBlocking(account, bundle, true);
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws NetworkErrorException {
        LOG.v("addAccount() accountType=" + str + " authTokenType=" + str2);
        try {
            checkAccountType(str);
            Intent intentForAddAccount = ValidAccountTypes.getEnumByValue(str).getIntentForAddAccount(this.mContext, accountAuthenticatorResponse, bundle);
            if (isResolved(intentForAddAccount)) {
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, intentForAddAccount);
                return bundle2;
            }
            Bundle bundle3 = new Bundle();
            bundle3.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=ru.mail.mailapp")));
            return bundle3;
        } catch (AuthenticatorException e10) {
            return e10.getFailureBundle();
        }
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        LOG.v("confirmCredentials()");
        if (bundle != null && bundle.containsKey("password")) {
            Type accountType = getAccountType(account.name, bundle);
            String string = bundle.getString("password");
            if (accountType.getCredentialsValidator().isValid(account.name, string)) {
                bundle.putString(BUNDLE_PARAM_PASSWORD, string);
                Bundle bundleAuthenticate = accountType.getMPopStrategy().authenticate(this.mContext, new MailAccount(account), bundle);
                if (bundleAuthenticate.containsKey("authtoken")) {
                    bundleAuthenticate.putBoolean("booleanResult", !TextUtils.isEmpty(bundleAuthenticate.getString("authtoken")));
                }
                if (bundleAuthenticate.containsKey("errorCode") && bundleAuthenticate.getInt("errorCode") == 22) {
                    bundleAuthenticate.remove("errorCode");
                    bundleAuthenticate.putBoolean("booleanResult", false);
                }
                if (!bundleAuthenticate.containsKey("booleanResult") && !bundleAuthenticate.containsKey("errorCode") && !bundleAuthenticate.containsKey(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK)) {
                    bundleAuthenticate.putInt("errorCode", 5);
                }
                return bundleAuthenticate;
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("errorCode", 7);
        bundle2.putString("errorMessage", "Check whether you have specified valid parameters for password and mail.ru account type (DEFAULT, OAUTH etc)");
        return bundle2;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        LOG.v("editProperties()");
        throw new UnsupportedOperationException();
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    @NotNull
    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) {
        boolean zHasMarkToRemove = getRemoveAllowedFromPreferences() ? true : hasMarkToRemove(account);
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", zHasMarkToRemove);
        LOG.d("getAccountRemovalAllowed() for " + account.name + " return = " + zHasMarkToRemove);
        return bundle;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0081 A[PHI: r18
      0x0081: PHI (r18v7 ru.mail.util.log.Formats$ParamFormat) = 
      (r18v1 ru.mail.util.log.Formats$ParamFormat)
      (r18v2 ru.mail.util.log.Formats$ParamFormat)
      (r18v3 ru.mail.util.log.Formats$ParamFormat)
      (r18v4 ru.mail.util.log.Formats$ParamFormat)
      (r18v5 ru.mail.util.log.Formats$ParamFormat)
      (r18v8 ru.mail.util.log.Formats$ParamFormat)
     binds: [B:25:0x00c1, B:21:0x00b3, B:17:0x00a5, B:13:0x0097, B:9:0x008c, B:6:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException {
        Analytics analytics;
        String str2;
        Formats.ParamFormat paramFormat;
        byte b10;
        String str3;
        Bundle mpopCookieBlocking;
        Formats.ParamFormat paramFormat2;
        Formats.ParamFormat paramFormat3;
        Log log = LOG;
        log.d("getAuthToken(" + str + ")");
        Analytics analytics2 = AuthenticatorEntryPoint.analytics(this.mContext);
        Bundle bundle2 = new Bundle();
        AccountManagerWrapper accountManagerWrapper = getAccountManagerWrapper(this.mContext);
        String password = accountManagerWrapper.getPassword(account);
        Type accountType = getAccountType(account.name, bundle);
        if (accountType.getCredentialsValidator().isValid(account.name, password)) {
            bundle.putString(BUNDLE_PARAM_PASSWORD, password);
            bundle.putString(EXTRA_SMS_PHONE, accountManagerWrapper.getUserData(account, "phone_number"));
            bundle.putBoolean(EXTRA_FROM_BACKGROUND, true);
            String tokenType = MailAccountConstants.getTokenType(str);
            Formats.ParamFormat paramFormat4 = MpopTokenRequest.TOKEN_FORMAT;
            tokenType.getClass();
            switch (tokenType.hashCode()) {
                case -4982469:
                    paramFormat = paramFormat4;
                    if (!tokenType.equals(MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN)) {
                        b10 = -1;
                    } else {
                        b10 = 0;
                    }
                    break;
                case 586980692:
                    paramFormat = paramFormat4;
                    if (!tokenType.equals(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH)) {
                        b10 = -1;
                    } else {
                        b10 = 1;
                    }
                    break;
                case 954210867:
                    paramFormat = paramFormat4;
                    if (!tokenType.equals(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS)) {
                        b10 = -1;
                    } else {
                        b10 = 2;
                    }
                    break;
                case 1491640962:
                    paramFormat = paramFormat4;
                    if (!tokenType.equals("ru.mail")) {
                        b10 = -1;
                    } else {
                        b10 = 3;
                    }
                    break;
                case 2024142795:
                    paramFormat = paramFormat4;
                    if (!tokenType.equals("ru.mail.oauth2.access")) {
                        b10 = -1;
                    } else {
                        b10 = 4;
                    }
                    break;
                default:
                    paramFormat = paramFormat4;
                    b10 = -1;
                    break;
            }
            switch (b10) {
                case 0:
                    str3 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                    mpopCookieBlocking = getMpopCookieBlocking(account, bundle);
                    paramFormat3 = paramFormat;
                    break;
                case 1:
                case 4:
                    str3 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                    bundle.putString(MailO2AuthStrategy.EXTRA_TOKEN_TYPE, str);
                    mpopCookieBlocking = accountType.getOAuthStrategy().authenticate(this.mContext, new MailAccount(account), bundle);
                    paramFormat3 = paramFormat;
                    break;
                case 2:
                    str3 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                    bundle.putString(MailO2AuthStrategy.EXTRA_TOKEN_TYPE, str);
                    mpopCookieBlocking = accountType.getDirectAuthStrategy().authenticate(this.mContext, new MailAccount(account), bundle);
                    paramFormat3 = paramFormat;
                    break;
                case 3:
                    AuthStrategy mPopStrategy = accountType.getMPopStrategy();
                    Context context = this.mContext;
                    str3 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                    Bundle bundleAuthenticate = mPopStrategy.authenticate(context, new MailAccount(account), bundle);
                    if (bundleAuthenticate.getInt("errorCode") == 22) {
                        analytics2.onUnauthorized(accountType, str, true);
                        setUnauthorizedData(account);
                    }
                    cleanMpopTokens(account);
                    if (!bundleAuthenticate.containsKey("authtoken")) {
                        mpopCookieBlocking = bundleAuthenticate;
                        paramFormat3 = paramFormat;
                    } else {
                        paramFormat2 = AuthorizeRequestCommand.MPOP_COOKIE_FORMAT;
                        analytics2 = analytics2;
                        new TokenParser(new DefaultTokenPairListener(accountManagerWrapper, account)).handleSignsAndTokens(bundleAuthenticate.getString(MailAccountConstants.SECURITY_TOKEN));
                        bundleAuthenticate.remove(MailAccountConstants.SECURITY_TOKEN);
                        bundleAuthenticate.remove("password");
                        mpopCookieBlocking = bundleAuthenticate;
                    }
                    break;
                default:
                    str3 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
                    mpopCookieBlocking = bundle2;
                    paramFormat3 = paramFormat;
                    break;
            }
            if (mpopCookieBlocking != null) {
                paramFormat3 = paramFormat2;
                String string = mpopCookieBlocking.getString("authtoken");
                String string2 = mpopCookieBlocking.getString(str);
                if (str.equals("ru.mail")) {
                    analytics2.onUseLegacyTokenType();
                }
                if (string != null) {
                    log.i("New token obtained. Type: " + tokenType + ", value: " + this.mLogFilter.filter(paramFormat3.getFormattedMsg(string)));
                    accountManagerWrapper.setAuthToken(account, tokenType, string);
                    return mpopCookieBlocking;
                }
                if (!TextUtils.isEmpty(string2)) {
                    log.i("New access token obtained. Type: " + tokenType);
                    return mpopCookieBlocking;
                }
                if (mpopCookieBlocking.getInt(KEY_SWA_CODE, -1) == 723) {
                    log.i("Imap redirect SWA code. Imap config: " + mpopCookieBlocking.getString("imap_settings", null));
                    return mpopCookieBlocking;
                }
                if (mpopCookieBlocking.getInt("errorCode") != 22) {
                    analytics2.onUnauthorized(accountType, str, false);
                    mpopCookieBlocking.putParcelable(str3, getUpdateCredentialsExplicitIntent(accountAuthenticatorResponse, account, bundle));
                    setUnauthorizedData(account);
                    addAuthFailMessage(bundle, mpopCookieBlocking);
                    return mpopCookieBlocking;
                }
            }
            paramFormat3 = paramFormat2;
            str2 = str3;
            analytics = analytics2;
            bundle2 = mpopCookieBlocking;
        } else {
            analytics = analytics2;
            str2 = CommonCode.Resolution.HAS_RESOLUTION_FROM_APK;
        }
        log.i("getAuthToken bad result");
        analytics.onTokenSpoiled(accountType, str);
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        setUnauthorizedData(account);
        bundle2.putParcelable(str2, getUpdateCredentialsExplicitIntent(accountAuthenticatorResponse, account, bundle));
        addAuthFailMessage(bundle, bundle2);
        return bundle2;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public String getAuthTokenLabel(String str) {
        return null;
    }

    public boolean getRemoveAllowedFromPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean(KEY_ALLOW_REMOVE_ACCOUNT_FROM_SYSTEM, false);
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) {
        LOG.v("hasFeatures()");
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", false);
        return bundle;
    }

    boolean hasMarkToRemove(Account account) {
        return MARK_TO_REMOVE_VALUE_TRUE.equals(getAccountManagerWrapper(this.mContext.getApplicationContext()).getUserData(account, MARK_TO_REMOVE_KEY));
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        LOG.v("updateCredentials() accountType=" + str + " account=" + account.name);
        try {
            checkAuthTokenType(str);
            Intent intentforUpdateCredentials = ValidAccountTypes.getEnumByValue(account.type).getIntentforUpdateCredentials(this.mContext, accountAuthenticatorResponse, account);
            intentforUpdateCredentials.putExtra(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, getAccountType(account.name, bundle, new OAuthSuppressSetterGetter(bundle).getSuppressedDomainPattern()).toString());
            setUnauthorizedData(account);
            cleanMpopTokens(account);
            if (!isResolved(intentforUpdateCredentials)) {
                return null;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, intentforUpdateCredentials);
            return bundle2;
        } catch (AuthenticatorException e10) {
            return e10.getFailureBundle();
        }
    }

    @Nullable
    private Bundle getMpopCookieBlocking(Account account, Bundle bundle, boolean z10) throws NetworkErrorException {
        AccountManagerWrapper accountManagerWrapper = getAccountManagerWrapper(this.mContext.getApplicationContext());
        String strPeekAuthToken = accountManagerWrapper.peekAuthToken(account, "ru.mail");
        if (TextUtils.isEmpty(strPeekAuthToken)) {
            if (z10) {
                return null;
            }
            return updateCookieAndGetToken(account, bundle);
        }
        PreferenceHostProvider preferenceHostProvider = new PreferenceHostProvider(this.mContext, "new_mail_api", ru.mail.Authenticator.R.string.new_mail_api_default_scheme, ru.mail.Authenticator.R.string.new_mail_api_default_host, bundle);
        String cookieHeader = MailAccountConstants.getCookieHeader(strPeekAuthToken, ValidAccountTypes.getEnumByValue(account.type).getCookieDomain());
        LOG.d("Requesting Mpop Token using cookie " + this.mLogFilter.filter(cookieHeader));
        boolean zIs12132Enabled = AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12132Enabled();
        Context context = this.mContext;
        try {
            CommandStatus<?> orThrow = new MpopTokenRequest(context, preferenceHostProvider, cookieHeader, new AccountInfo(account.name, context), zIs12132Enabled).execute(ExecutorSelectors.defaultSelector()).getOrThrow();
            if (orThrow instanceof CommandStatus.OK) {
                MpopTokenRequest.Result result = (MpopTokenRequest.Result) orThrow.getData();
                Bundle mpopCookieResult = getMpopCookieResult(account);
                mpopCookieResult.putString("authtoken", result.getMpopToken());
                return mpopCookieResult;
            }
            if (!(orThrow instanceof AuthCommandStatus.ERROR_INVALID_LOGIN)) {
                if (orThrow instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) {
                    throw new NetworkErrorException("Error while retrieving mpop token");
                }
                return getMpopCookieResult(account);
            }
            accountManagerWrapper.invalidateAuthToken(account.type, strPeekAuthToken);
            if (!z10) {
                return updateCookieAndGetToken(account, bundle);
            }
            Bundle mpopCookieResult2 = getMpopCookieResult(account);
            mpopCookieResult2.putInt("errorCode", 22);
            return mpopCookieResult2;
        } catch (InterruptedException e10) {
            e = e10;
            LOG.i("Unable to execute MpopTokenRequest command", e);
            return getMpopCookieResult(account);
        } catch (ExecutionException e11) {
            e = e11;
            LOG.i("Unable to execute MpopTokenRequest command", e);
            return getMpopCookieResult(account);
        }
    }

    public static AccountManagerWrapper getAccountManagerWrapper(Context context) {
        return getAccountManagerWrapper(context, new EmptyRerfreshParamsProvider());
    }

    public static Type getAccountType(String str, Bundle bundle, @NonNull Pattern pattern) {
        if (pattern.matcher(DomainUtils.getDomain(str)).find()) {
            return Type.DEFAULT;
        }
        return getAccountType(str, bundle);
    }
}
