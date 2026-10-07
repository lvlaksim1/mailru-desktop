package ru.mail.registration.ui;

import com.google.api.client.googleapis.notifications.ResourceStates;
import ru.mail.Authenticator.R;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ASUSERNAME' uses external variables
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
/* JADX INFO: loaded from: classes11.dex */
public final class ErrorStatus {
    private static final /* synthetic */ ErrorStatus[] $VALUES;
    public static final ErrorStatus ACCESS_DENIED;
    public static final ErrorStatus ASSECRET;
    public static final ErrorStatus ASUSERNAME;
    public static final ErrorStatus CAPTCHA;
    public static final ErrorStatus CHILD_LIMIT_EXCEEDED;
    public static final ErrorStatus LIMIT_EXCEED;
    public static final ErrorStatus LIMIT_EXCEED_MIN;
    public static final ErrorStatus METHOD_UNAVAILABLE;
    public static final ErrorStatus PASSWORD_LIKE_USERNAME;
    public static final ErrorStatus REACHED_ACCOUNTS;
    public static final ErrorStatus SERVERERROR;
    public static final ErrorStatus SERVER_UNAVAILABLE;
    public static final ErrorStatus WRONG_PHONE_NUMBER;
    private int errorMsg;
    public static final ErrorStatus REQUIRED = new ErrorStatus("REQUIRED", 0, R.string.reg_err_network_400_required);
    public static final ErrorStatus INVALID = new ErrorStatus("INVALID", 1, R.string.reg_err_network_400_invalid);
    public static final ErrorStatus INVALID_COMPROMISED = new ErrorStatus("INVALID_COMPROMISED", 2, R.string.reg_err_network_400_invalid_compromised);
    public static final ErrorStatus INVALID_END = new ErrorStatus("INVALID_END", 3, R.string.reg_err_network_400_invalid_continue);
    public static final ErrorStatus EXISTS = new ErrorStatus(ResourceStates.EXISTS, 4, R.string.reg_err_email_already_exists);
    public static final ErrorStatus DIGISTS = new ErrorStatus("DIGISTS", 5, R.string.reg_err_network_400_invalid_only_digits);
    public static final ErrorStatus WEAK = new ErrorStatus("WEAK", 6, R.string.reg_err_network_400_required_invalid_weak);

    private static /* synthetic */ ErrorStatus[] $values() {
        return new ErrorStatus[]{REQUIRED, INVALID, INVALID_COMPROMISED, INVALID_END, EXISTS, DIGISTS, WEAK, ASUSERNAME, ASSECRET, WRONG_PHONE_NUMBER, REACHED_ACCOUNTS, PASSWORD_LIKE_USERNAME, SERVERERROR, SERVER_UNAVAILABLE, LIMIT_EXCEED, LIMIT_EXCEED_MIN, METHOD_UNAVAILABLE, ACCESS_DENIED, CAPTCHA, CHILD_LIMIT_EXCEEDED};
    }

    static {
        int i10 = R.string.reg_err_network_400_required_invalid_as_username;
        ASUSERNAME = new ErrorStatus("ASUSERNAME", 7, i10);
        ASSECRET = new ErrorStatus("ASSECRET", 8, R.string.reg_err_network_400_required_invalid_as_secret);
        WRONG_PHONE_NUMBER = new ErrorStatus("WRONG_PHONE_NUMBER", 9, R.string.reg_err_network_wrong_pnone_number);
        REACHED_ACCOUNTS = new ErrorStatus("REACHED_ACCOUNTS", 10, R.string.reg_err_network_400_reached_accounts);
        PASSWORD_LIKE_USERNAME = new ErrorStatus("PASSWORD_LIKE_USERNAME", 11, i10);
        int i11 = R.string.reg_err_network_server_error;
        SERVERERROR = new ErrorStatus("SERVERERROR", 12, i11);
        SERVER_UNAVAILABLE = new ErrorStatus("SERVER_UNAVAILABLE", 13, i11);
        LIMIT_EXCEED = new ErrorStatus("LIMIT_EXCEED", 14, R.string.reg_err_network_limit_exceeded);
        LIMIT_EXCEED_MIN = new ErrorStatus("LIMIT_EXCEED_MIN", 15, R.string.reg_err_network_limit_exceeded_min);
        METHOD_UNAVAILABLE = new ErrorStatus("METHOD_UNAVAILABLE", 16, R.string.reg_err_network_method_unavailable);
        ACCESS_DENIED = new ErrorStatus("ACCESS_DENIED", 17, R.string.reg_err_network_access_denied);
        CAPTCHA = new ErrorStatus("CAPTCHA", 18, R.string.authenticator_captcha_error);
        CHILD_LIMIT_EXCEEDED = new ErrorStatus("CHILD_LIMIT_EXCEEDED", 19, R.string.reg_child_limit_exceeded_error);
        $VALUES = $values();
    }

    private ErrorStatus(String str, int i10, int i11) {
        super(str, i10);
        this.errorMsg = i11;
    }

    public static ErrorStatus valueOf(String str) {
        return (ErrorStatus) Enum.valueOf(ErrorStatus.class, str);
    }

    public static ErrorStatus[] values() {
        return (ErrorStatus[]) $VALUES.clone();
    }

    public int getErrorMsg() {
        return this.errorMsg;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
