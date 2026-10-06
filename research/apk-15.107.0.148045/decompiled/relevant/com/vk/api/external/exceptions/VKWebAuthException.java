package com.vk.api.external.exceptions;

import androidx.annotation.VisibleForTesting;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001c\u0018\u0000 )2\u00060\u0001j\u0002`\u0002:\u0001)BK\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0010J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0010J\r\u0010\u0014\u001a\u00020\u000e¢\u0006\u0004\b\u0014\u0010\u0010J\r\u0010\u0015\u001a\u00020\u000e¢\u0006\u0004\b\u0015\u0010\u0010J\r\u0010\u0016\u001a\u00020\u000e¢\u0006\u0004\b\u0016\u0010\u0010R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b!\u0010\u001c\u001a\u0004\b\"\u0010\u001eR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b'\u0010$\u001a\u0004\b(\u0010&¨\u0006*"}, d2 = {"Lcom/vk/api/external/exceptions/VKWebAuthException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "lastResponseCode", "", "error", "errorDescription", HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON, "Lorg/json/JSONObject;", XmailMigrationPromoSheet.BUTTON_INFO, "fullError", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Lorg/json/JSONObject;)V", "", "isInvalidTokenException", "()Z", "isNeedConfirmPasswordError", "isNeedCaptchaError", "isInvalidPasswordError", "isDeactivatedError", "isAccessTokenExpired", "isLastRequestSuccess", "lanretxesreganamipakvmoca", "I", "getLastResponseCode", "()I", "lanretxesreganamipakvmocb", "Ljava/lang/String;", "getError", "()Ljava/lang/String;", "lanretxesreganamipakvmocc", "getErrorDescription", "lanretxesreganamipakvmocd", "getErrorReason", "lanretxesreganamipakvmoce", "Lorg/json/JSONObject;", "getInfo", "()Lorg/json/JSONObject;", "lanretxesreganamipakvmocf", "getFullError", "Companion", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VKWebAuthException extends Exception {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String ERROR_ACCESS_TOKEN_EXPIRED = "access_token_expired";

    @NotNull
    public static final String ERROR_DEACTIVATED = "deactivated";

    @NotNull
    public static final String ERROR_INVALID_PASSWORD = "invalid_password";

    @NotNull
    public static final String ERROR_INVALID_TOKEN = "invalid_token";

    @NotNull
    public static final String ERROR_NEED_CAPTCHA = "need_captcha";

    @NotNull
    public static final String ERROR_NEED_PASSWORD = "need_password";

    /* JADX INFO: renamed from: lanretxesreganamipakvmoca, reason: from kotlin metadata */
    private final int lastResponseCode;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocb, reason: from kotlin metadata */
    @Nullable
    private final String error;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocc, reason: from kotlin metadata */
    @Nullable
    private final String errorDescription;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocd, reason: from kotlin metadata */
    @Nullable
    private final String errorReason;

    /* JADX INFO: renamed from: lanretxesreganamipakvmoce, reason: from kotlin metadata */
    @Nullable
    private final JSONObject info;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocf, reason: from kotlin metadata */
    @Nullable
    private final JSONObject fullError;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes2.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003R\u0016\u0010\r\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u0016\u0010\u000f\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003¨\u0006\u0011"}, d2 = {"Lcom/vk/api/external/exceptions/VKWebAuthException$Companion;", "", "<init>", "()V", "ERROR_INVALID_TOKEN", "", "getERROR_INVALID_TOKEN$annotations", "ERROR_NEED_PASSWORD", "getERROR_NEED_PASSWORD$annotations", "ERROR_NEED_CAPTCHA", "getERROR_NEED_CAPTCHA$annotations", "ERROR_INVALID_PASSWORD", "getERROR_INVALID_PASSWORD$annotations", "ERROR_DEACTIVATED", "getERROR_DEACTIVATED$annotations", "ERROR_ACCESS_TOKEN_EXPIRED", "getERROR_ACCESS_TOKEN_EXPIRED$annotations", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @VisibleForTesting(otherwise = 2)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_ACCESS_TOKEN_EXPIRED$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_DEACTIVATED$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_INVALID_PASSWORD$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_INVALID_TOKEN$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_NEED_CAPTCHA$annotations() {
        }

        @VisibleForTesting(otherwise = 2)
        public static /* synthetic */ void getERROR_NEED_PASSWORD$annotations() {
        }
    }

    public /* synthetic */ VKWebAuthException(int i10, String str, String str2, String str3, JSONObject jSONObject, JSONObject jSONObject2, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this(i10, (i11 & 2) != 0 ? null : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3, (i11 & 16) != 0 ? null : jSONObject, (i11 & 32) != 0 ? null : jSONObject2);
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @Nullable
    public final String getErrorDescription() {
        return this.errorDescription;
    }

    @Nullable
    public final String getErrorReason() {
        return this.errorReason;
    }

    @Nullable
    public final JSONObject getFullError() {
        return this.fullError;
    }

    @Nullable
    public final JSONObject getInfo() {
        return this.info;
    }

    public final int getLastResponseCode() {
        return this.lastResponseCode;
    }

    public final boolean isAccessTokenExpired() {
        return Intrinsics.areEqual(this.error, ERROR_ACCESS_TOKEN_EXPIRED);
    }

    public final boolean isDeactivatedError() {
        return Intrinsics.areEqual(this.error, "deactivated");
    }

    public final boolean isInvalidPasswordError() {
        return Intrinsics.areEqual(this.error, "invalid_password");
    }

    public final boolean isInvalidTokenException() {
        return Intrinsics.areEqual(this.error, ERROR_INVALID_TOKEN);
    }

    public final boolean isLastRequestSuccess() {
        int i10 = this.lastResponseCode;
        return 200 <= i10 && i10 < 300;
    }

    public final boolean isNeedCaptchaError() {
        return Intrinsics.areEqual(this.error, "need_captcha");
    }

    public final boolean isNeedConfirmPasswordError() {
        return Intrinsics.areEqual(this.error, ERROR_NEED_PASSWORD);
    }

    public VKWebAuthException(int i10, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable JSONObject jSONObject, @Nullable JSONObject jSONObject2) {
        super(str);
        this.lastResponseCode = i10;
        this.error = str;
        this.errorDescription = str2;
        this.errorReason = str3;
        this.info = jSONObject;
        this.fullError = jSONObject2;
    }
}
