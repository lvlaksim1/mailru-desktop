package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.my.target.ao;
import java.util.regex.Pattern;
import ru.mail.auth.Authenticator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class AuthTokenAnalyticsClassifier {
    public static final String BAD_LOGIN = "BAD_LOGIN";
    public static final String NO_LOGIN_SUPPLIED = "NO_LOGIN_SUPPLIED";
    protected final Context mContext;

    /* JADX INFO: compiled from: ProGuard */
    public static class AccountManagerClassifier extends AuthTokenAnalyticsClassifier {
        private final AccountManagerSettings mAccountManagerSettings;

        public AccountManagerClassifier(Context context, AccountManagerSettings accountManagerSettings) {
            super(context);
            this.mAccountManagerSettings = accountManagerSettings;
        }

        @Override // ru.mail.serverapi.AuthTokenAnalyticsClassifier
        @Nullable
        public String getAuthToken(String str) {
            return Authenticator.getAccountManagerWrapper(this.mContext).getUserData(new Account(str, this.mAccountManagerSettings.getAccountType()), "type");
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class PatternDependableClassifier extends AuthTokenAnalyticsClassifier {
        private final Pattern mPattern;

        public PatternDependableClassifier(Context context, Pattern pattern) {
            super(context);
            this.mPattern = pattern;
        }

        @Override // ru.mail.serverapi.AuthTokenAnalyticsClassifier
        @Nullable
        public String getAuthToken(String str) {
            try {
                return Authenticator.getAccountType(str, null, this.mPattern).name();
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    public AuthTokenAnalyticsClassifier(Context context) {
        this.mContext = context;
    }

    public String classify(String str) {
        return TextUtils.isEmpty(str) ? NO_LOGIN_SUPPLIED : (String) ao.a(getAuthToken(str), BAD_LOGIN);
    }

    @Nullable
    public abstract String getAuthToken(String str);
}
