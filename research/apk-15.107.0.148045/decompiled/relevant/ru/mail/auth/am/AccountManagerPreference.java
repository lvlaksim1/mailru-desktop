package ru.mail.auth.am;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.VisibleForTesting;
import com.huawei.hms.common.AccountPicker;
import com.huawei.hms.framework.common.BundleUtil;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.AuthenticatorBuildConfig;
import ru.mail.android_utils.SdkUtils;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.MailLoginFragment;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.util.log.Log;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.utils.prefs.EncryptedPreferences;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0007\b\u0016\u0018\u0000 ]2\u00020\u0001:\u0002\\]B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0013\u0010#\u001a\b\u0012\u0004\u0012\u00020 0$H\u0016¢\u0006\u0002\u0010%J%\u0010&\u001a\b\u0012\u0004\u0012\u00020 0$2\b\u0010'\u001a\u0004\u0018\u00010\u00132\u0006\u0010(\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\b\u0012\u0004\u0012\u00020 0$2\b\u0010'\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010+J0\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001e0-2\u0006\u0010\u001f\u001a\u00020 2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0017J:\u0010,\u001a\b\u0012\u0004\u0012\u0002020-2\u0006\u0010\u001f\u001a\u00020 2\b\u00103\u001a\u0004\u0018\u0001042\u000e\u0010.\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016Jc\u00105\u001a\b\u0012\u0004\u0012\u0002020-2\u0006\u0010'\u001a\u00020\u00132\b\u00106\u001a\u0004\u0018\u00010\u00132\u000e\u00107\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010$2\b\u00108\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u0001042\u000e\u0010.\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016¢\u0006\u0002\u00109JN\u0010:\u001a\b\u0012\u0004\u0012\u0002020-2\u0006\u0010\u001f\u001a\u00020 2\b\u00106\u001a\u0004\u0018\u00010\u00132\b\u0010;\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u0001042\u000e\u0010.\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J0\u0010<\u001a\b\u0012\u0004\u0012\u0002020-2\b\u00103\u001a\u0004\u0018\u0001042\u0006\u0010=\u001a\u0002022\u000e\u0010.\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/H\u0002JJ\u0010>\u001a\b\u0012\u0004\u0012\u0002020-2\u0006\u0010\u001f\u001a\u00020 2\u0006\u00106\u001a\u00020\u00132\b\u0010;\u001a\u0004\u0018\u0001022\u0006\u0010?\u001a\u00020\u001e2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\"\u0010@\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010A\u001a\u0004\u0018\u00010\u00132\u0006\u0010B\u001a\u000202H\u0016J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010D\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 H\u0014J\u001a\u0010E\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\b\u0010F\u001a\u0004\u0018\u00010\u0013H\u0002J\u001a\u0010G\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u0013H\u0016J\"\u0010I\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\b\u0010F\u001a\u0004\u0018\u00010\u0013H\u0016J2\u0010J\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u0013H\u0002J.\u0010M\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\b\u0010F\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u0013H\u0002J$\u0010N\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u0013H\u0002J,\u0010O\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\u0006\u0010F\u001a\u00020\u001e2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\u0013H\u0002J\u001a\u0010P\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\b\u0010A\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010Q\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010R\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010S\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u00106\u001a\u00020\u0013H\u0016J\"\u0010T\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 2\u0006\u00106\u001a\u00020\u00132\b\u0010U\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010V\u001a\u00020W2\u0006\u0010\u001f\u001a\u00020 2\b\u0010(\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010X\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u00132\b\u0010U\u001a\u0004\u0018\u00010\u0013H\u0016J\"\u0010Y\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010H\u001a\u00020\u00132\b\u0010K\u001a\u0004\u0018\u00010\u0013H\u0002J\u0010\u0010Z\u001a\u00020\u001e2\u0006\u0010H\u001a\u00020\u0013H\u0002J\b\u0010[\u001a\u00020\"H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u0013X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lru/mail/auth/am/AccountManagerPreference;", "Lru/mail/auth/am/AccountManagerDelegate;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "Lkotlin/Lazy;", "encryptedPreferences", "Lru/mail/utils/prefs/EncryptedPreferences;", "getEncryptedPreferences", "()Lru/mail/utils/prefs/EncryptedPreferences;", "encryptedPreferences$delegate", "appAccountType", "", "getAppAccountType", "()Ljava/lang/String;", "encryptedKeys", "", "getEncryptedKeys", "()Ljava/util/Set;", "encryptedKeys$delegate", "accounts", "Lru/mail/auth/am/AccountsStorePreferences;", "isFallback", "", "account", "Landroid/accounts/Account;", "setFallback", "", "getAccounts", "", "()[Landroid/accounts/Account;", "getAccountsByTypeForPackage", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "packageName", "(Ljava/lang/String;Ljava/lang/String;)[Landroid/accounts/Account;", "getAccountsByType", "(Ljava/lang/String;)[Landroid/accounts/Account;", "removeAccount", "Landroid/accounts/AccountManagerFuture;", "callback", "Landroid/accounts/AccountManagerCallback;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "Landroid/os/Bundle;", "activity", "Landroid/app/Activity;", "addAccount", AccountPicker.EXTRA_ADD_ACCOUNT_AUTH_TOKEN_TYPE_STRING, "requiredFeatures", AccountPicker.EXTRA_ADD_ACCOUNT_OPTIONS_BUNDLE, "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Landroid/os/Bundle;Landroid/app/Activity;Landroid/accounts/AccountManagerCallback;Landroid/os/Handler;)Landroid/accounts/AccountManagerFuture;", "updateCredentials", "options", "toFuture", "bundle", "getAuthToken", "notifyAuthFailure", "addAccountExplicitly", "password", "userdata", "removeAccountExplicitly", "getUnauthorized", "setUnauthorized", "value", "getUserData", "key", "setUserData", "getUserDataString", "postfix", "default", "setUserDataString", "getUserDataBoolean", "setUserDataBoolean", "setPassword", "getPassword", "clearPassword", "peekAuthToken", "setAuthToken", "authToken", "getAccountVisibility", "", "invalidateAuthToken", "keyForPreference", "isEncryptedKey", "clearAllPreferences", "Key", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAccountManagerPreference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountManagerPreference.kt\nru/mail/auth/am/AccountManagerPreference\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n+ 5 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,368:1\n3919#2:369\n4434#2,2:370\n37#3,2:372\n210#4,5:374\n41#5,12:379\n1#6:391\n*S KotlinDebug\n*F\n+ 1 AccountManagerPreference.kt\nru/mail/auth/am/AccountManagerPreference\n*L\n85#1:369\n85#1:370,2\n85#1:372,2\n147#1:374,5\n319#1:379,12\n*E\n"})
public class AccountManagerPreference implements AccountManagerDelegate {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("AccountManagerFallback");

    @NotNull
    private final AccountsStorePreferences accounts;

    @NotNull
    private final String appAccountType;

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: encryptedKeys$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy encryptedKeys;

    /* JADX INFO: renamed from: encryptedPreferences$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy encryptedPreferences;

    /* JADX INFO: renamed from: preferences$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy preferences;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/auth/am/AccountManagerPreference$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "clearByPrefix", "", "Landroid/content/SharedPreferences;", "prefixKey", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAccountManagerPreference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountManagerPreference.kt\nru/mail/auth/am/AccountManagerPreference$Companion\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,368:1\n41#2,6:369\n47#2,6:377\n1869#3,2:375\n*S KotlinDebug\n*F\n+ 1 AccountManagerPreference.kt\nru/mail/auth/am/AccountManagerPreference$Companion\n*L\n357#1:369,6\n357#1:377,6\n358#1:375,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void clearByPrefix(SharedPreferences sharedPreferences, String str) {
            Set<Map.Entry<String, ?>> setEntrySet = sharedPreferences.getAll().entrySet();
            if (setEntrySet.isEmpty()) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            Iterator<T> it = setEntrySet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
                if (StringsKt.startsWith$default((String) key, str, false, 2, (Object) null)) {
                    editorEdit.remove((String) entry.getKey());
                }
            }
            editorEdit.apply();
            editorEdit.apply();
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lru/mail/auth/am/AccountManagerPreference$Key;", "", "<init>", "()V", "AM_PASSWORD", "", "AM_AUTH_TOKEN", "IS_AUTHORIZED", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Key {

        @NotNull
        public static final String AM_AUTH_TOKEN = "am_auth_token";

        @NotNull
        public static final String AM_PASSWORD = "am_password";

        @NotNull
        public static final Key INSTANCE = new Key();

        @NotNull
        public static final String IS_AUTHORIZED = "is_authorized";

        private Key() {
        }
    }

    public AccountManagerPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.preferences = LazyKt.lazy(new Function0() { // from class: ru.mail.auth.am.t
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerPreference.preferences_delegate$lambda$0(this.f80731a);
            }
        });
        this.encryptedPreferences = LazyKt.lazy(new Function0() { // from class: ru.mail.auth.am.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerPreference.encryptedPreferences_delegate$lambda$0(this.f80732a);
            }
        });
        this.appAccountType = AuthenticatorBuildConfig.getAccountType();
        this.encryptedKeys = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.auth.am.v
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerPreference.encryptedKeys_delegate$lambda$0();
            }
        });
        SharedPreferences preferences = getPreferences();
        Intrinsics.checkNotNullExpressionValue(preferences, "<get-preferences>(...)");
        this.accounts = new AccountsStorePreferences(preferences, LOG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set encryptedKeys_delegate$lambda$0() {
        return SetsKt.setOf((Object[]) new String[]{Key.AM_PASSWORD, Key.AM_AUTH_TOKEN, AccountManagerWrapper.Key.SAFE_COOKIES, "access_token", "refresh_token"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EncryptedPreferences encryptedPreferences_delegate$lambda$0(AccountManagerPreference accountManagerPreference) {
        return AuthenticatorEntryPoint.INSTANCE.encryptedPreferences(accountManagerPreference.context);
    }

    private final Set<String> getEncryptedKeys() {
        return (Set) this.encryptedKeys.getValue();
    }

    private final EncryptedPreferences getEncryptedPreferences() {
        return (EncryptedPreferences) this.encryptedPreferences.getValue();
    }

    private final SharedPreferences getPreferences() {
        return (SharedPreferences) this.preferences.getValue();
    }

    private final boolean getUserDataBoolean(Account account, String key, String postfix) {
        return getPreferences(key).getBoolean(keyForPreference(account, key, postfix), false);
    }

    static /* synthetic */ boolean getUserDataBoolean$default(AccountManagerPreference accountManagerPreference, Account account, String str, String str2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUserDataBoolean");
        }
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        return accountManagerPreference.getUserDataBoolean(account, str, str2);
    }

    private final String getUserDataString(Account account, String key, String postfix, String str) {
        return getPreferences(key).getString(keyForPreference(account, key, postfix), str);
    }

    static /* synthetic */ String getUserDataString$default(AccountManagerPreference accountManagerPreference, Account account, String str, String str2, String str3, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getUserDataString");
        }
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return accountManagerPreference.getUserDataString(account, str, str2, str3);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:9:0x0023 A[RETURN] */
    private final boolean isEncryptedKey(String key) {
        Object obj;
        Object next;
        Iterator<T> it = getEncryptedKeys().iterator();
        do {
            obj = null;
            if (it.hasNext()) {
                next = it.next();
            }
            if (obj != null) {
                return true;
            }
            return false;
        } while (!StringsKt.endsWith$default((String) next, key, false, 2, (Object) null));
        obj = next;
        if (obj != null) {
            return true;
        }
        return false;
    }

    private final String keyForPreference(Account account, String key, String postfix) {
        if (postfix == null) {
            return account.type + BundleUtil.UNDERLINE_TAG + account.name + BundleUtil.UNDERLINE_TAG + key;
        }
        return account.type + BundleUtil.UNDERLINE_TAG + account.name + BundleUtil.UNDERLINE_TAG + key + BundleUtil.UNDERLINE_TAG + postfix;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SharedPreferences preferences_delegate$lambda$0(AccountManagerPreference accountManagerPreference) {
        return accountManagerPreference.context.getSharedPreferences("account_manager_fallback", 0);
    }

    private final void setUnauthorized(Account account, String value) {
        setUserDataBoolean$default(this, account, Key.IS_AUTHORIZED, !Intrinsics.areEqual(Authenticator.VALUE_UNAUTHORIZED, value), null, 8, null);
    }

    private final void setUserDataBoolean(Account account, String key, boolean value, String postfix) {
        SharedPreferences preferences = getPreferences(key);
        preferences.edit().putBoolean(keyForPreference(account, key, postfix), value).apply();
    }

    static /* synthetic */ void setUserDataBoolean$default(AccountManagerPreference accountManagerPreference, Account account, String str, boolean z10, String str2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setUserDataBoolean");
        }
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        accountManagerPreference.setUserDataBoolean(account, str, z10, str2);
    }

    private final void setUserDataString(Account account, String key, String value, String postfix) {
        SharedPreferences preferences = getPreferences(key);
        String strKeyForPreference = keyForPreference(account, key, postfix);
        SharedPreferences.Editor editorEdit = preferences.edit();
        if (value != null) {
            editorEdit.putString(strKeyForPreference, value);
        } else {
            editorEdit.remove(strKeyForPreference);
        }
        editorEdit.apply();
    }

    static /* synthetic */ void setUserDataString$default(AccountManagerPreference accountManagerPreference, Account account, String str, String str2, String str3, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setUserDataString");
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        accountManagerPreference.setUserDataString(account, str, str2, str3);
    }

    private final AccountManagerFuture<Bundle> toFuture(Activity activity, Bundle bundle, AccountManagerCallback<Bundle> callback) {
        Parcelable parcelable;
        if (SdkUtils.hasTiramisu()) {
            parcelable = (Parcelable) bundle.getParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, Intent.class);
        } else {
            Parcelable parcelable2 = bundle.getParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK);
            if (!(parcelable2 instanceof Intent)) {
                parcelable2 = null;
            }
            parcelable = (Intent) parcelable2;
        }
        Intent intent = (Intent) parcelable;
        return (activity == null || intent == null) ? AccountManagerBlockedFuture.INSTANCE.wrapBundle(callback, false) : new AccountManagerFallbackFuture(activity, intent, callback);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public AccountManagerFuture<Bundle> addAccount(@NotNull String accountType, @Nullable String authTokenType, @Nullable String[] requiredFeatures, @Nullable Bundle addAccountOptions, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler) throws NetworkErrorException {
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        Bundle bundleAddAccount = new Authenticator(this.context).addAccount(null, accountType, authTokenType, requiredFeatures, addAccountOptions);
        Intrinsics.checkNotNull(bundleAddAccount);
        return toFuture(activity, bundleAddAccount, callback);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public boolean addAccountExplicitly(@NotNull Account account, @Nullable String password, @NotNull Bundle userdata) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(userdata, "userdata");
        boolean zContains = this.accounts.contains(account);
        if (!zContains) {
            this.accounts.addAccount(account);
        }
        setPassword(account, password);
        for (String str : userdata.keySet()) {
            Intrinsics.checkNotNull(str);
            setUserData(account, str, userdata.getString(str));
        }
        return !zContains;
    }

    @Keep
    @VisibleForTesting
    public final void clearAllPreferences() {
        getPreferences().edit().clear().apply();
        getEncryptedPreferences().edit().clear().apply();
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public void clearPassword(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        setUserData(account, Key.AM_PASSWORD, null);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public int getAccountVisibility(@NotNull Account account, @Nullable String packageName) {
        Intrinsics.checkNotNullParameter(account, "account");
        return (Intrinsics.areEqual(packageName, this.context.getPackageName()) && this.accounts.contains(account)) ? 3 : 0;
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public Account[] getAccounts() {
        return this.accounts.getAccountsArray();
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public Account[] getAccountsByType(@Nullable String accountType) {
        if (accountType == null) {
            return getAccounts();
        }
        Account[] accounts = getAccounts();
        ArrayList arrayList = new ArrayList();
        for (Account account : accounts) {
            if (Intrinsics.areEqual(account.type, accountType)) {
                arrayList.add(account);
            }
        }
        return (Account[]) arrayList.toArray(new Account[0]);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public Account[] getAccountsByTypeForPackage(@Nullable String accountType, @NotNull String packageName) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return Intrinsics.areEqual(packageName, this.context.getPackageName()) ? getAccountsByType(accountType) : new Account[0];
    }

    @NotNull
    protected final String getAppAccountType() {
        return this.appAccountType;
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public AccountManagerFuture<Bundle> getAuthToken(@NotNull Account account, @NotNull String authTokenType, @Nullable Bundle options, boolean notifyAuthFailure, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(authTokenType, "authTokenType");
        return new AccountManagerBlockedFuture(callback, new Authenticator(this.context).getAuthToken(null, account, authTokenType, options));
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @Nullable
    public String getPassword(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        return getUserData(account, Key.AM_PASSWORD);
    }

    @Nullable
    protected String getUnauthorized(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        if (getUserDataBoolean$default(this, account, Key.IS_AUTHORIZED, null, 4, null)) {
            return null;
        }
        return Authenticator.KEY_UNAUTHORIZED;
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @Nullable
    public String getUserData(@NotNull Account account, @NotNull String key) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(key, "key");
        if (Intrinsics.areEqual(key, AccountManagerWrapper.Key.IS_ACCOUNT_FALLBACK)) {
            return "true";
        }
        return Intrinsics.areEqual(key, Authenticator.KEY_UNAUTHORIZED) ? getUnauthorized(account) : getUserDataString$default(this, account, key, null, null, 12, null);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public void invalidateAuthToken(@NotNull String accountType, @Nullable String authToken) {
        Intrinsics.checkNotNullParameter(accountType, "accountType");
        for (Map.Entry<String, ?> entry : getEncryptedPreferences().getAll().entrySet()) {
            if (StringsKt.startsWith$default(entry.getKey(), accountType, false, 2, (Object) null) && Intrinsics.areEqual(entry.getValue(), authToken)) {
                SharedPreferences.Editor editorEdit = getEncryptedPreferences().edit();
                editorEdit.remove(entry.getKey());
                editorEdit.apply();
                editorEdit.apply();
            }
        }
    }

    public boolean isFallback(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        return this.accounts.isFallback(account);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @Nullable
    public String peekAuthToken(@NotNull Account account, @NotNull String authTokenType) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(authTokenType, "authTokenType");
        return getUserDataString$default(this, account, Key.AM_AUTH_TOKEN, authTokenType, null, 8, null);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @Deprecated(message = "use removeAccountInternal(Account, Activity, AccountManagerCallback, Handler)")
    @NotNull
    public AccountManagerFuture<Boolean> removeAccount(@NotNull Account account, @Nullable AccountManagerCallback<Boolean> callback, @Nullable Handler handler) {
        Intrinsics.checkNotNullParameter(account, "account");
        removeAccountExplicitly(account);
        return new AccountManagerBlockedFuture(callback, Boolean.TRUE);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public boolean removeAccountExplicitly(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        String str = account.type + BundleUtil.UNDERLINE_TAG + account.name;
        boolean zContains = this.accounts.contains(account);
        this.accounts.removeAccount(account);
        Companion companion = INSTANCE;
        SharedPreferences preferences = getPreferences();
        Intrinsics.checkNotNullExpressionValue(preferences, "<get-preferences>(...)");
        companion.clearByPrefix(preferences, str);
        companion.clearByPrefix(getEncryptedPreferences(), str);
        return zContains;
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public /* bridge */ void setAccountAuthProvider(@NotNull AccountAuthProvider accountAuthProvider) {
        super.setAccountAuthProvider(accountAuthProvider);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public void setAuthToken(@NotNull Account account, @NotNull String authTokenType, @Nullable String authToken) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(authTokenType, "authTokenType");
        setUserDataString(account, Key.AM_AUTH_TOKEN, authToken, authTokenType);
    }

    public void setFallback(@NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        this.accounts.addAccount(account);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public void setPassword(@NotNull Account account, @Nullable String password) {
        Intrinsics.checkNotNullParameter(account, "account");
        setUserData(account, Key.AM_PASSWORD, password);
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    public void setUserData(@NotNull Account account, @NotNull String key, @Nullable String value) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(key, "key");
        if (Intrinsics.areEqual(key, Authenticator.KEY_UNAUTHORIZED)) {
            setUnauthorized(account, value);
        } else {
            setUserDataString$default(this, account, key, value, null, 8, null);
        }
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public AccountManagerFuture<Bundle> updateCredentials(@NotNull Account account, @Nullable String authTokenType, @Nullable Bundle options, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler) {
        Intrinsics.checkNotNullParameter(account, "account");
        Bundle bundleUpdateCredentials = new Authenticator(this.context).updateCredentials(null, account, authTokenType, options);
        Intrinsics.checkNotNull(bundleUpdateCredentials);
        return toFuture(activity, bundleUpdateCredentials, callback);
    }

    private final SharedPreferences getPreferences(String key) {
        SharedPreferences encryptedPreferences = isEncryptedKey(key) ? getEncryptedPreferences() : getPreferences();
        Intrinsics.checkNotNull(encryptedPreferences);
        return encryptedPreferences;
    }

    @Override // ru.mail.auth.am.AccountManagerDelegate
    @NotNull
    public AccountManagerFuture<Bundle> removeAccount(@NotNull Account account, @Nullable Activity activity, @Nullable AccountManagerCallback<Bundle> callback, @Nullable Handler handler) {
        Intrinsics.checkNotNullParameter(account, "account");
        removeAccountExplicitly(account);
        return AccountManagerBlockedFuture.INSTANCE.wrapBundle(callback, true);
    }
}
