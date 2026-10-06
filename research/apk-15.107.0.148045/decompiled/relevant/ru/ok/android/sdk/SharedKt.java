package ru.ok.android.sdk;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010\u0011\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u0019\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030'¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)¨\u0006+"}, d2 = {"API_ERR_PERMISSION_DENIED", "", "APP_PLATFORM", "", "LOG_TAG", "OK_AUTH_REQUEST_CODE", "OK_INVITING_REQUEST_CODE", "OK_POSTING_REQUEST_CODE", "OK_SUGGESTING_REQUEST_CODE", "PARAM_ACCESS_TOKEN", "PARAM_ACTIVITY_RESULT", "PARAM_APP_ID", "PARAM_APP_KEY", "PARAM_ATTACHMENT", "PARAM_AUTH_TYPE", "PARAM_CLIENT_ID", "PARAM_CLIENT_SECRET", "PARAM_CODE", "PARAM_ERROR", "PARAM_ERROR_MSG", "PARAM_EXPIRES_IN", "PARAM_LOGGED_IN_USER", "PARAM_MESSAGE", "PARAM_METHOD", "PARAM_PLATFORM", "PARAM_REDIRECT_URI", "PARAM_REFRESH_TOKEN", "PARAM_RESULT", "PARAM_SCOPES", "PARAM_SESSION_SECRET_KEY", "PARAM_SIGN", "PARAM_TYPE", "PARAM_USER_TEXT_ENABLE", "PARAM_WIDGET_ARGS", "PARAM_WIDGET_RETRY_ALLOWED", "PREFERENCES_FILE", "REMOTE_API", "REMOTE_WIDGETS", "WIDGET_SIGNED_ARGS", "", "getWIDGET_SIGNED_ARGS", "()[Ljava/lang/String;", "[Ljava/lang/String;", "odnoklassniki-android-sdk_release"}, k = 2, mv = {1, 1, 15})
public final class SharedKt {
    public static final int API_ERR_PERMISSION_DENIED = 10;

    @NotNull
    public static final String APP_PLATFORM = "ANDROID";

    @NotNull
    public static final String LOG_TAG = "ok_android_sdk";
    public static final int OK_AUTH_REQUEST_CODE = 22890;
    public static final int OK_INVITING_REQUEST_CODE = 22892;
    public static final int OK_POSTING_REQUEST_CODE = 22891;
    public static final int OK_SUGGESTING_REQUEST_CODE = 22893;

    @NotNull
    public static final String PARAM_ACCESS_TOKEN = "access_token";

    @NotNull
    public static final String PARAM_ACTIVITY_RESULT = "activity_result";

    @NotNull
    public static final String PARAM_APP_ID = "appId";

    @NotNull
    public static final String PARAM_APP_KEY = "application_key";

    @NotNull
    public static final String PARAM_ATTACHMENT = "attachment";

    @NotNull
    public static final String PARAM_AUTH_TYPE = "auth_type";

    @NotNull
    public static final String PARAM_CLIENT_ID = "client_id";

    @NotNull
    public static final String PARAM_CLIENT_SECRET = "client_secret";

    @NotNull
    public static final String PARAM_CODE = "code";

    @NotNull
    public static final String PARAM_ERROR = "error";

    @NotNull
    public static final String PARAM_ERROR_MSG = "error_msg";

    @NotNull
    public static final String PARAM_EXPIRES_IN = "expires_in";

    @NotNull
    public static final String PARAM_LOGGED_IN_USER = "logged_in_user";

    @NotNull
    public static final String PARAM_MESSAGE = "message";

    @NotNull
    public static final String PARAM_METHOD = "method";

    @NotNull
    public static final String PARAM_PLATFORM = "platform";

    @NotNull
    public static final String PARAM_REDIRECT_URI = "redirect_uri";

    @NotNull
    public static final String PARAM_REFRESH_TOKEN = "refresh_token";

    @NotNull
    public static final String PARAM_RESULT = "result";

    @NotNull
    public static final String PARAM_SCOPES = "scopes";

    @NotNull
    public static final String PARAM_SESSION_SECRET_KEY = "session_secret_key";

    @NotNull
    public static final String PARAM_SIGN = "sig";

    @NotNull
    public static final String PARAM_TYPE = "type";

    @NotNull
    public static final String PARAM_USER_TEXT_ENABLE = "utext";

    @NotNull
    public static final String PARAM_WIDGET_ARGS = "widget_args";

    @NotNull
    public static final String PARAM_WIDGET_RETRY_ALLOWED = "widget_retry_allowed";

    @NotNull
    public static final String PREFERENCES_FILE = "oksdkprefs";

    @NotNull
    public static final String REMOTE_API = "https://api.ok.ru/";

    @NotNull
    public static final String REMOTE_WIDGETS = "https://connect.ok.ru/";

    @NotNull
    private static final String[] WIDGET_SIGNED_ARGS = {"st.attachment", "st.return", "st.redirect_uri", "st.state"};

    @NotNull
    public static final String[] getWIDGET_SIGNED_ARGS() {
        return WIDGET_SIGNED_ARGS;
    }
}
