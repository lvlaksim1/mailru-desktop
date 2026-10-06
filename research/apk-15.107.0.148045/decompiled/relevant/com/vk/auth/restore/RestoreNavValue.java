package com.vk.auth.restore;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.auth.restore.RestoreNavValue[], still in use, count: 1, list:
  (r0v1 com.vk.auth.restore.RestoreNavValue[]) from 0x0073: INVOKE (r0v1 com.vk.auth.restore.RestoreNavValue[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:116)
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/vk/auth/restore/RestoreNavValue;", "", "", "erochtuakvmoca", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "value", "AUTH_SCREEN", "LOGIN_PASSWORD_SCREEN", "REG_SCREEN", "REG_EDU_SCREEN", "AUTH_SERVICE_EXTENDED_ACCESS_TOKEN_SCREEN", "AUTH_SERVICE_EXTENDED_SILENT_TOKEN_SCREEN", "AUTH_PRIMARY_FACTOR_CHOICE", "AUTH_ALERT_NO_AVAILABLE_FACTORS", "AUTH_MAIL_RU", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RestoreNavValue {
    AUTH_SCREEN("auth_forgot_password"),
    LOGIN_PASSWORD_SCREEN("auth_login_pwd_screen"),
    REG_SCREEN("reg_forgot_pwd"),
    REG_EDU_SCREEN("reg_edu_email_pwd_forgot_pwd"),
    AUTH_SERVICE_EXTENDED_ACCESS_TOKEN_SCREEN("auth_service_extended_access_token"),
    AUTH_SERVICE_EXTENDED_SILENT_TOKEN_SCREEN("auth_service_extended_silent_token"),
    AUTH_PRIMARY_FACTOR_CHOICE("auth_primary_factor_choice"),
    AUTH_ALERT_NO_AVAILABLE_FACTORS("auth_alert_no_available_factors"),
    AUTH_MAIL_RU("auth_mail_ru");

    private static final /* synthetic */ EnumEntries erochtuakvmocc;

    /* JADX INFO: renamed from: erochtuakvmoca, reason: from kotlin metadata */
    @NotNull
    private final String value;

    static {
        erochtuakvmocc = EnumEntriesKt.enumEntries(restoreNavValueArr);
    }

    private RestoreNavValue(String str) {
        super(str, i);
        this.value = str;
    }

    @NotNull
    public static EnumEntries<RestoreNavValue> getEntries() {
        return erochtuakvmocc;
    }

    public static RestoreNavValue valueOf(String str) {
        return (RestoreNavValue) Enum.valueOf(RestoreNavValue.class, str);
    }

    public static RestoreNavValue[] values() {
        return (RestoreNavValue[]) erochtuakvmocb.clone();
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
