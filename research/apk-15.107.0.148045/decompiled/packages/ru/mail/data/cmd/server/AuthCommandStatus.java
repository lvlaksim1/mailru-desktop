package ru.mail.data.cmd.server;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.mail.auth.DoregistrationParameter;
import ru.mail.auth.request.SendSmsCode;
import ru.mail.mailbox.cmd.CommandStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AuthCommandStatus {

    /* JADX INFO: compiled from: ProGuard */
    public static class CAPTCHA<V> extends CommandStatus.ERROR<V> {
        public CAPTCHA(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class CODE_ERROR<V> extends CommandStatus.ERROR<V> {
        public CODE_ERROR(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_INVALID_LOGIN extends CommandStatus.ERROR<String> {
        public ERROR_INVALID_LOGIN(String str) {
            super(str);
        }

        public ERROR_INVALID_LOGIN() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_RATE_LIMIT extends SEND_SMS_ERROR {
        private final SendSmsCode.Result result;

        public ERROR_RATE_LIMIT(SendSmsCode.SendSmsError sendSmsError, SendSmsCode.Result result) {
            super(sendSmsError);
            this.result = result;
        }

        public SendSmsCode.Result getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_WITH_IMAP_SETTINGS extends CommandStatus.ERROR<String> {
        public ERROR_WITH_IMAP_SETTINGS(@NonNull String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_WITH_STATUS_CODE extends CommandStatus.ERROR_WITH_STATUS_CODE {
        private final String message;

        public ERROR_WITH_STATUS_CODE(int i10, String str) {
            super(i10);
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED extends CommandStatus.ERROR<DoregistrationParameter> {
        public EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED(DoregistrationParameter doregistrationParameter) {
            super(doregistrationParameter);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class EXTERNAL_AUTH_PROHIBIT extends CommandStatus.ERROR<String> {
        public EXTERNAL_AUTH_PROHIBIT() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MAIL_SERVER_SETTINGS_REQUIRED<V> extends CommandStatus.ERROR<V> {
        public MAIL_SERVER_SETTINGS_REQUIRED(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MIGRANT_REG_REQUIRED<T> extends CommandStatus.OK<T> {
        public MIGRANT_REG_REQUIRED(T t10) {
            super(t10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MRIM_DISABLED extends CommandStatus.ERROR<Void> {
        public MRIM_DISABLED() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class OAUTH_OUTLOOK_REQUIRED extends OAUTH_REQUIRED {
        public OAUTH_OUTLOOK_REQUIRED(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class OAUTH_REQUIRED extends CommandStatus.ERROR<String> {
        public OAUTH_REQUIRED(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class OAUTH_YAHOO_REQUIRED extends OAUTH_REQUIRED {
        public OAUTH_YAHOO_REQUIRED(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class OAUTH_YANDEX_REQUIRED extends OAUTH_REQUIRED {
        public OAUTH_YANDEX_REQUIRED(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class SEND_SMS_ERROR extends CommandStatus.ERROR<SendSmsCode.SendSmsError> {
        public SEND_SMS_ERROR(SendSmsCode.SendSmsError sendSmsError) {
            super(sendSmsError);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class SOCIAL_AUTH_OK<T> extends CommandStatus.OK<T> {
        private final String bindedEmail;

        public SOCIAL_AUTH_OK(String str, T t10) {
            super(t10);
            this.bindedEmail = str;
        }

        public String getBindedEmail() {
            return this.bindedEmail;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class TWO_FACTOR_BIND_FORBIDDEN extends CommandStatus.ERROR<Params> {

        /* JADX INFO: compiled from: ProGuard */
        public static class Params {
            private final String mSocialType;
            private final int mSwaCode;

            public Params(int i10, String str) {
                this.mSwaCode = i10;
                this.mSocialType = str;
            }

            public String getSocialType() {
                return this.mSocialType;
            }

            public int getSwaCode() {
                return this.mSwaCode;
            }
        }

        public TWO_FACTOR_BIND_FORBIDDEN(Params params) {
            super(params);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MAIL_SECOND_STEP_REQUIRED extends CommandStatus.ERROR<SecondStepParams> {
        public MAIL_SECOND_STEP_REQUIRED(SecondStepParams secondStepParams) {
            super(secondStepParams);
        }

        /* JADX INFO: compiled from: ProGuard */
        public static class SecondStepParams {
            private final String mSecondStepCookie;

            @Nullable
            private final String mSecondStepLudwigToken;
            private final String mSecondStepUrl;

            public SecondStepParams(String str, String str2) {
                this.mSecondStepUrl = str;
                this.mSecondStepCookie = str2;
                this.mSecondStepLudwigToken = null;
            }

            public String getSecondStepCookie() {
                return this.mSecondStepCookie;
            }

            @Nullable
            public String getSecondStepLudwigToken() {
                return this.mSecondStepLudwigToken;
            }

            public String getSecondStepUrl() {
                return this.mSecondStepUrl;
            }

            public SecondStepParams(String str, String str2, @Nullable String str3) {
                this.mSecondStepUrl = str;
                this.mSecondStepCookie = str2;
                this.mSecondStepLudwigToken = str3;
            }
        }
    }
}
