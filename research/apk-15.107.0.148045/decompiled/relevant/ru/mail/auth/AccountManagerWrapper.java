package ru.mail.auth;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.huawei.hms.common.AccountPicker;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001AJ:\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J0\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH'J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H&Jc\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\u0016JN\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&JJ\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\r2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H&¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H&¢\u0006\u0002\u0010\u001cJ\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010\u0005\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u001fJ\u001a\u0010 \u001a\u00020!2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u0011H'J\u001b\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010$\u001a\u00020\u0011H&¢\u0006\u0002\u0010\u001fJ\u001a\u0010%\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0011H&J\u001a\u0010&\u001a\u00020'2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010\u0011H&J\u001a\u0010)\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010+\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010,\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u0006H&J\"\u0010-\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010\u0011H&J$\u0010.\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010\u00112\b\u0010/\u001a\u0004\u0018\u00010\u0004H&J\u001a\u00100\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0011H&J\"\u00102\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u00112\b\u00103\u001a\u0004\u0018\u00010\u0011H&J \u00104\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020!H&J \u00107\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00112\u0006\u00108\u001a\u00020\rH&J$\u00109\u001a\u00020'2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u00105\u001a\u0004\u0018\u00010\u00112\u0006\u0010:\u001a\u00020\u0004H&J\u0018\u0010;\u001a\u00020'2\u0006\u00101\u001a\u00020\u00112\u0006\u0010<\u001a\u00020=H&J\u0018\u0010>\u001a\u00020'2\u0006\u00101\u001a\u00020\u00112\u0006\u0010<\u001a\u00020=H&J\u0010\u0010?\u001a\u00020'2\u0006\u0010@\u001a\u00020\rH&¨\u0006BÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/AccountManagerWrapper;", "", "removeAccount", "Landroid/accounts/AccountManagerFuture;", "Landroid/os/Bundle;", "account", "Landroid/accounts/Account;", "activity", "Landroid/app/Activity;", "callback", "Landroid/accounts/AccountManagerCallback;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "", "removeAccountExplicitly", "addAccount", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "", AccountPicker.EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING, "requiredFeatures", "", AccountPicker.EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE, "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Landroid/os/Bundle;Landroid/app/Activity;Landroid/accounts/AccountManagerCallback;Landroid/os/Handler;)Landroid/accounts/AccountManagerFuture;", "updateCredentials", "options", "getAuthToken", "notifyAuthFailure", "getAccounts", "()[Landroid/accounts/Account;", "getAppAccounts", "getAppAccount", "(Ljava/lang/String;)[Landroid/accounts/Account;", "getAccountVisibility", "", "packageName", "getExternalAccountsByType", "type", "peekAuthToken", "invalidateAuthToken", "", "authToken", "setPassword", "password", "getPassword", "clearPassword", "setAuthToken", "addAccountExplicitly", "userData", "getUserData", "key", "setUserData", "value", "setIsSyncable", "authority", "syncable", "setSyncAutomatically", "sync", "requestSync", PushProcessor.DATAKEY_EXTRAS, "addUserDataListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/auth/AccountManagerListener;", "removeUserDataListener", "refresh", "refreshAllData", "Key", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAccountManagerWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountManagerWrapper.kt\nru/mail/auth/AccountManagerWrapper\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,108:1\n1400#2,2:109\n*S KotlinDebug\n*F\n+ 1 AccountManagerWrapper.kt\nru/mail/auth/AccountManagerWrapper\n*L\n63#1:109,2\n*E\n"})
public interface AccountManagerWrapper {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static Account[] getAppAccount(@NotNull AccountManagerWrapper accountManagerWrapper, @NotNull String account) {
            Intrinsics.checkNotNullParameter(account, "account");
            return AccountManagerWrapper.super.getAppAccount(account);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/auth/AccountManagerWrapper$Key;", "", "<init>", "()V", "SAFE_COOKIES", "", "REFRESH_TOKEN", "ACCESS_TOKEN", "ACCESS_TOKEN_EXPIRE", "AUTH_CODE", "AUTH_CODE_EXPIRE", "IS_ACCOUNT_FALLBACK", "MASTER_ACCESS_TOKEN_EXPIRE", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Key {

        @NotNull
        public static final String ACCESS_TOKEN = "access_token";

        @NotNull
        public static final String ACCESS_TOKEN_EXPIRE = "access_token_expires";

        @NotNull
        public static final String AUTH_CODE = "auth_code";

        @NotNull
        public static final String AUTH_CODE_EXPIRE = "auth_code_expires";

        @NotNull
        public static final Key INSTANCE = new Key();

        @NotNull
        public static final String IS_ACCOUNT_FALLBACK = "is_account_fallback";

        @NotNull
        public static final String MASTER_ACCESS_TOKEN_EXPIRE = "master_access_token_expires";

        @NotNull
        public static final String REFRESH_TOKEN = "refresh_token";

        @NotNull
        public static final String SAFE_COOKIES = "safe_cookies";

        private Key() {
        }
    }

    @NotNull
    AccountManagerFuture<Bundle> addAccount(@NotNull String accountType, @Nullable String authTokenType, @Nullable String[] requiredFeatures, @Nullable Bundle addAccountOptions, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler);

    boolean addAccountExplicitly(@NotNull Account account, @Nullable String password, @Nullable Bundle userData);

    void addUserDataListener(@NotNull String key, @NotNull AccountManagerListener listener);

    void clearPassword(@NotNull Account account);

    @RequiresApi(26)
    int getAccountVisibility(@NotNull Account account, @Nullable String packageName);

    @NotNull
    Account[] getAccounts();

    /* JADX WARN: Code duplicated, block: B:10:0x001f  */
    /* JADX WARN: Code duplicated, block: B:12:0x0025  */
    @NotNull
    default Account[] getAppAccount(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        for (Account account2 : getAppAccounts()) {
            if (Intrinsics.areEqual(account2.name, account)) {
                return account2 != null ? new Account[]{account2} : new Account[0];
            }
        }
        account2 = null;
        if (account2 != null) {
        }
    }

    @NotNull
    Account[] getAppAccounts();

    @NotNull
    AccountManagerFuture<Bundle> getAuthToken(@NotNull Account account, @NotNull String authTokenType, @Nullable Bundle options, boolean notifyAuthFailure, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler);

    @NotNull
    Account[] getExternalAccountsByType(@NotNull String type);

    @Nullable
    String getPassword(@NotNull Account account);

    @Nullable
    String getUserData(@NotNull Account account, @NotNull String key);

    void invalidateAuthToken(@NotNull String accountType, @Nullable String authToken);

    @Nullable
    String peekAuthToken(@NotNull Account account, @NotNull String type);

    void refresh(boolean refreshAllData);

    @Deprecated(message = "use removeAccount(Account, Activity, AccountManagerCallback, Handler)")
    @NotNull
    AccountManagerFuture<Boolean> removeAccount(@NotNull Account account, @Nullable AccountManagerCallback<Boolean> callback, @Nullable Handler handler);

    @NotNull
    AccountManagerFuture<Bundle> removeAccount(@NotNull Account account, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler);

    boolean removeAccountExplicitly(@NotNull Account account);

    void removeUserDataListener(@NotNull String key, @NotNull AccountManagerListener listener);

    void requestSync(@Nullable Account account, @Nullable String authority, @NotNull Bundle extras);

    void setAuthToken(@NotNull Account account, @NotNull String authTokenType, @Nullable String authToken);

    void setIsSyncable(@NotNull Account account, @NotNull String authority, int syncable);

    void setPassword(@NotNull Account account, @Nullable String password);

    void setSyncAutomatically(@NotNull Account account, @NotNull String authority, boolean sync);

    void setUserData(@NotNull Account account, @NotNull String key, @Nullable String value);

    @NotNull
    AccountManagerFuture<Bundle> updateCredentials(@NotNull Account account, @Nullable String authTokenType, @Nullable Bundle options, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler);
}
