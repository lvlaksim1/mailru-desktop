package ru.mail.authorizationsdk.feature.registration.presentation.mappers;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.feature.registration.domain.model.ErrorStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"mapToErrorMsg", "", "Lru/mail/authorizationsdk/feature/registration/domain/model/ErrorStatus;", "resources", "Lru/mail/android_utils/wrapper/Resources;", "authorizationsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ErrorStatusMapperKt {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ErrorStatus.values().length];
            try {
                iArr[ErrorStatus.REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ErrorStatus.INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ErrorStatus.INVALID_COMPROMISED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ErrorStatus.INVALID_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ErrorStatus.EXISTS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ErrorStatus.DIGISTS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ErrorStatus.WEAK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ErrorStatus.ASUSERNAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ErrorStatus.ASSECRET.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ErrorStatus.WRONG_PHONE_NUMBER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ErrorStatus.REACHED_ACCOUNTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ErrorStatus.PASSWORD_LIKE_USERNAME.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ErrorStatus.SERVERERROR.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ErrorStatus.SERVER_UNAVAILABLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ErrorStatus.LIMIT_EXCEED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ErrorStatus.LIMIT_EXCEED_MIN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ErrorStatus.METHOD_UNAVAILABLE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ErrorStatus.ACCESS_DENIED.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ErrorStatus.CAPTCHA.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ErrorStatus.CHILD_LIMIT_EXCEEDED.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public static final String mapToErrorMsg(@NotNull ErrorStatus errorStatus, @NotNull Resources resources) {
        Intrinsics.checkNotNullParameter(errorStatus, "<this>");
        Intrinsics.checkNotNullParameter(resources, "resources");
        switch (WhenMappings.$EnumSwitchMapping$0[errorStatus.ordinal()]) {
            case 1:
                return resources.getString(R.string.reg_err_network_400_required);
            case 2:
                return resources.getString(R.string.reg_err_network_400_invalid);
            case 3:
                return resources.getString(R.string.reg_err_network_400_invalid_compromised);
            case 4:
                return resources.getString(R.string.reg_err_network_400_invalid_continue);
            case 5:
                return resources.getString(R.string.reg_err_email_already_exists);
            case 6:
                return resources.getString(R.string.reg_err_network_400_invalid_only_digits);
            case 7:
                return resources.getString(R.string.reg_err_network_400_required_invalid_weak);
            case 8:
                return resources.getString(R.string.reg_err_network_400_required_invalid_as_username);
            case 9:
                return resources.getString(R.string.reg_err_network_400_required_invalid_as_secret);
            case 10:
                return resources.getString(R.string.reg_err_network_wrong_pnone_number);
            case 11:
                return resources.getString(R.string.reg_err_network_400_reached_accounts);
            case 12:
                return resources.getString(R.string.reg_err_network_400_required_invalid_as_username);
            case 13:
                return resources.getString(R.string.reg_err_network_server_error);
            case 14:
                return resources.getString(R.string.reg_err_network_server_error);
            case 15:
                return resources.getString(R.string.reg_err_network_limit_exceeded);
            case 16:
                return resources.getString(R.string.reg_err_network_limit_exceeded_min);
            case 17:
                return resources.getString(R.string.reg_err_network_method_unavailable);
            case 18:
                return resources.getString(R.string.reg_err_network_access_denied);
            case 19:
                return resources.getString(R.string.authenticator_captcha_error);
            case 20:
                return resources.getString(R.string.reg_child_limit_exceeded_error);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
