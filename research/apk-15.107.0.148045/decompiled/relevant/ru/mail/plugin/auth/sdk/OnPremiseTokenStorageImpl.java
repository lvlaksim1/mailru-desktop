package ru.mail.plugin.auth.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage;
import ru.mail.utils.prefs.EncryptedPreferences;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\u0012\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\rH\u0016J\u0014\u0010\u001e\u001a\u00020\u000f*\u00020\r2\u0006\u0010\u001f\u001a\u00020\rH\u0002R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006!"}, d2 = {"Lru/mail/plugin/auth/sdk/OnPremiseTokenStorageImpl;", "Lru/mail/onpremiseauthsdk/api/OnPremiseTokenStorage;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "prefs", "Lru/mail/utils/prefs/EncryptedPreferences;", "getPrefs", "()Lru/mail/utils/prefs/EncryptedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "getAccessToken", "", "saveAccessToken", "", CommonConstant.KEY_ACCESS_TOKEN, "getRefreshToken", "saveRefreshToken", "refreshToken", "getExpiresIn", "", "saveExpiresIn", "expiresIn", "", "isTokenValid", "", "isRefreshTokenExists", "invalidateAuthToken", "authToken", "saveIfNotBlank", "key", "Companion", "mail-plugin-auth-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseTokenStorageImpl implements OnPremiseTokenStorage {
    private static final long DEFAULT_EXPIRES_IN = -1;
    private static final long ONE_SECOND_IN_MILLIS = 1000;

    @NotNull
    private static final String ON_PREM_ACCESS_TOKEN = "on_prem_access_token";

    @NotNull
    private static final String ON_PREM_EXPIRES_IN = "on_prem_expires_in";

    @NotNull
    private static final String ON_PREM_REFRESH_TOKEN = "on_prem_refresh_token";

    /* JADX INFO: renamed from: prefs$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy prefs;

    public OnPremiseTokenStorageImpl(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.prefs = LazyKt.lazy(new Function0() { // from class: ru.mail.plugin.auth.sdk.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseTokenStorageImpl.prefs_delegate$lambda$0(context);
            }
        });
    }

    private final EncryptedPreferences getPrefs() {
        return (EncryptedPreferences) this.prefs.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EncryptedPreferences prefs_delegate$lambda$0(Context context) {
        return AuthenticatorEntryPoint.INSTANCE.encryptedPreferences(context);
    }

    private final void saveIfNotBlank(String str, String str2) {
        if (StringsKt.isBlank(str)) {
            return;
        }
        getPrefs().edit().putString(str2, str).apply();
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    @NotNull
    public String getAccessToken() {
        return String.valueOf(getPrefs().getString(ON_PREM_ACCESS_TOKEN, ""));
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public long getExpiresIn() {
        return getPrefs().getLong(ON_PREM_EXPIRES_IN, -1L);
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    @NotNull
    public String getRefreshToken() {
        return String.valueOf(getPrefs().getString(ON_PREM_REFRESH_TOKEN, ""));
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public void invalidateAuthToken(@Nullable String authToken) {
        if (authToken == null || StringsKt.isBlank(authToken)) {
            return;
        }
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        boolean zAreEqual = Intrinsics.areEqual(authToken, getAccessToken());
        boolean zAreEqual2 = Intrinsics.areEqual(authToken, getRefreshToken());
        if (zAreEqual || zAreEqual2) {
            editorEdit.remove(ON_PREM_ACCESS_TOKEN);
        }
        if (zAreEqual2) {
            editorEdit.remove(ON_PREM_REFRESH_TOKEN);
        }
        editorEdit.apply();
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public boolean isRefreshTokenExists() {
        return !StringsKt.isBlank(getRefreshToken());
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public boolean isTokenValid() {
        return !StringsKt.isBlank(getAccessToken()) && ((System.currentTimeMillis() > getExpiresIn() ? 1 : (System.currentTimeMillis() == getExpiresIn() ? 0 : -1)) < 0);
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public void saveAccessToken(@NotNull String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        saveIfNotBlank(accessToken, ON_PREM_ACCESS_TOKEN);
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public void saveExpiresIn(int expiresIn) {
        getPrefs().edit().putLong(ON_PREM_EXPIRES_IN, System.currentTimeMillis() + (((long) expiresIn) * 1000)).apply();
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage
    public void saveRefreshToken(@NotNull String refreshToken) {
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
        saveIfNotBlank(refreshToken, ON_PREM_REFRESH_TOKEN);
    }
}
