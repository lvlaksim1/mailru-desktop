package ru.mail.auth.webview;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.MainThread;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessTokenProvider;
import com.vk.auth.api.models.AuthResult;
import com.vk.auth.main.MetadataExtKt;
import com.vk.auth.main.SignUpData;
import com.vk.auth.main.VkClientAuthCallback;
import com.vk.auth.main.VkClientAuthLib;
import com.vk.auth.oauth.VkOAuthConnectionResult;
import com.vk.auth.oauth.VkOAuthService;
import com.vk.auth.oauth.model.AdditionalOauthAuthResult;
import com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment;
import com.vk.auth.validation.VkPhoneValidationCompleteResult;
import com.vk.auth.validation.VkPhoneValidationErrorReason;
import com.vk.emailforwarding.VkEmailForwarding;
import com.vk.emailforwarding.VkEmailForwardingCallback;
import com.vk.emailforwarding.VkMailRestore;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.bridges.LogoutReason;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthErrors;
import ru.mail.auth.AuthMessageCallback;
import ru.mail.auth.AuthSource;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.BaseToolbarActivity;
import ru.mail.auth.LoginActivity;
import ru.mail.auth.Message;
import ru.mail.auth.OAuthTransitionManager;
import ru.mail.auth.ServiceChooserFragment;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.restore.RestorePasswordParams;
import ru.mail.authorizationsdk.di.VeryBadTemporaryMediator;
import ru.mail.authorizationsdk.feature.login.presentation.common.LoginResult;
import ru.mail.authorizesdk.domain.models.RestoreVkidFlags;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.core.VkAuthListener;
import ru.mail.credentialsexchanger.core.VkAuthResult;
import ru.mail.credentialsexchanger.core.VkCredResponse;
import ru.mail.credentialsexchanger.data.IsEmailEnteredHolder;
import ru.mail.credentialsexchanger.data.entity.VkIdAuthSource;
import ru.mail.credentialsexchanger.data.network.Response;
import ru.mail.credentialsexchanger.presentation.fragment.ScreenType;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelper;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelperHolder;
import ru.mail.data.entities.Collector;
import ru.mail.network.HostProviderWrapperImpl;
import ru.mail.social.auth.RestoreVkidStartSource;
import ru.mail.social.auth.domain.RestoreVkidHelperKt;
import ru.mail.social.auth.presentation.AutoLoginProvider;
import ru.mail.social_auth.domain.RestoreVkidResult;
import ru.mail.social_auth.domain.VkEmailForwardingResult;
import ru.mail.social_auth.domain.autologin.FinishAutologinControllerUseCase;
import ru.mail.social_auth.domain.autologin.FinishRestoreControllerUseCase;
import ru.mail.social_auth.presentation.SocialAuthSdk;
import ru.mail.social_auth.utils.UtilsKt;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000µ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001(\u0018\u0000 `2\u00020\u0001:\u0004^_`aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\rH\u0016J\u0018\u0010-\u001a\u00020#2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\rH\u0016J\b\u0010.\u001a\u00020#H\u0016J\u0012\u0010/\u001a\u00020#2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020&H\u0016J\b\u00103\u001a\u00020#H\u0016J\u000e\u00104\u001a\u00020#2\u0006\u00105\u001a\u000206J$\u00107\u001a\u00020#2\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u0012J\u0016\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u0012J\u000e\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u00020\u0018J\u001e\u0010>\u001a\u00020#2\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020#0@j\u0002`BJ\u0018\u0010C\u001a\u00020#2\u0006\u0010:\u001a\u00020\u00182\u0006\u0010;\u001a\u00020\u0012H\u0002J\u0010\u0010D\u001a\u00020#2\u0006\u0010E\u001a\u00020FH\u0002J!\u0010G\u001a\u00020#2\u0017\u0010H\u001a\u0013\u0012\u0004\u0012\u00020I\u0012\u0004\u0012\u00020#0@¢\u0006\u0002\bJH\u0002J\b\u0010K\u001a\u00020#H\u0002J\b\u0010L\u001a\u00020#H\u0002J\b\u0010M\u001a\u00020#H\u0002J\u0018\u0010N\u001a\u00020#2\b\u0010O\u001a\u0004\u0018\u00010\u00182\u0006\u0010P\u001a\u00020QJ\u0010\u0010R\u001a\u00020#2\u0006\u0010E\u001a\u00020SH\u0002J\u0010\u0010T\u001a\u00020#2\u0006\u0010U\u001a\u00020FH\u0002J\u001a\u0010V\u001a\u00020#2\b\u0010O\u001a\u0004\u0018\u00010\u00182\u0006\u0010W\u001a\u00020\u0012H\u0002J\u0010\u0010X\u001a\u00020Y2\u0006\u0010U\u001a\u00020SH\u0002J\u0010\u0010Z\u001a\u00020[2\u0006\u0010E\u001a\u00020FH\u0002J\b\u0010\\\u001a\u00020#H\u0002J\b\u0010]\u001a\u00020\u0012H\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00060\u0016R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)¨\u0006b"}, d2 = {"Lru/mail/auth/webview/VKConnectSignInDelegate;", "Lru/mail/auth/webview/LifecycleDelegate;", "<init>", "()V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "activity", "Landroidx/fragment/app/FragmentActivity;", "restoreVkEmailHelper", "Lru/mail/credentialsexchanger/unblockvkusers/RestoreVkEmailHelper;", "mailCallback", "Ljava/lang/ref/WeakReference;", "Lru/mail/auth/AuthMessageCallback;", "vkTokensListener", "Lru/mail/auth/webview/VKConnectSignInDelegate$VkTokensListener;", "progressBar", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getProgressBar", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "authCallback", "Lru/mail/auth/webview/VKConnectSignInDelegate$VKConnectLoginCallback;", "enteredEmail", "", "vkidAuthWithoutPassword", "vkidBindInLogin", "isSoftVkidAutologinDisabled", "isResetSoftVkIdEnabled", "isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled", "isFromVkEmailForwarding", "isFromRestoreVkid", "restoreVkidFlags", "Lru/mail/authorizesdk/domain/models/RestoreVkidFlags;", "writeToParcel", "", "dest", Collector.FLAGS, "", "vkAuthListener", "ru/mail/auth/webview/VKConnectSignInDelegate$vkAuthListener$1", "Lru/mail/auth/webview/VKConnectSignInDelegate$vkAuthListener$1;", "onCreate", "context", "callback", "onResume", "onStop", "onNewIntent", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "describeContents", "onDestroy", "setParams", "params", "Lru/mail/auth/webview/VKConnectSignInDelegate$Params;", "startVKConnectAuth", "secondStepAvailable", "startVkEmailForwarding", "superAppToken", "isMailPasswordEnabled", "onEmailEntered", "email", "startVKAutoLogin", "onResult", "Lkotlin/Function1;", "Lru/mail/social/auth/presentation/AutoLoginProvider$Result;", "Lru/mail/social/auth/presentation/OnResult;", "initiateVkEmailForwarding", "handleVkEmailForwarding", "result", "Lcom/vk/emailforwarding/VkEmailForwardingCallback$Result;", "analytics", "action", "Lru/mail/auth/Analytics;", "Lkotlin/ExtensionFunctionType;", "resetAuthFlags", "startLoading", "stopLoading", "startRestoreVkid", "emailForRestore", "source", "Lru/mail/social/auth/RestoreVkidStartSource;", "handleRestore", "Lcom/vk/emailforwarding/VkMailRestore$Result;", "handleEmailForwardingInRestore", "it", "openMailRestore", "isRebind", "mapResult", "Lru/mail/social_auth/domain/RestoreVkidResult;", "mapVkEmailForwardingCallback", "Lru/mail/social_auth/domain/VkEmailForwardingResult;", "addVkTokensListenerIfNeeded", "shouldAddRestoreParam", "VkTokensListener", "VKConnectLoginCallback", "CREATOR", "Params", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKConnectSignInDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKConnectSignInDelegate.kt\nru/mail/auth/webview/VKConnectSignInDelegate\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,796:1\n38#2,4:797\n*S KotlinDebug\n*F\n+ 1 VKConnectSignInDelegate.kt\nru/mail/auth/webview/VKConnectSignInDelegate\n*L\n323#1:797,4\n*E\n"})
public final class VKConnectSignInDelegate implements LifecycleDelegate {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VKConnectSignInDelegate");
    private static final int MAX_LENGTH = 20;

    @NotNull
    public static final String VKID_RESET_PREF_FILE_NAME = "VKID_RESET_PREF_FILE_NAME";

    @NotNull
    public static final String VKID_RESET_PREF_KEY = "VKID_RESET_PREF_KEY";

    @NotNull
    private static final String VKID_SIGN_IN_PROMO_TAG = "vkc";

    @Nullable
    private FragmentActivity activity;

    @NotNull
    private final VKConnectLoginCallback authCallback;

    @Nullable
    private String enteredEmail;
    private boolean isFromRestoreVkid;
    private boolean isFromVkEmailForwarding;
    private boolean isResetSoftVkIdEnabled;
    private boolean isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled;
    private boolean isSoftVkidAutologinDisabled;

    @NotNull
    private WeakReference<AuthMessageCallback> mailCallback;

    @NotNull
    private final MutableStateFlow<Boolean> progressBar;

    @Nullable
    private RestoreVkEmailHelper restoreVkEmailHelper;

    @NotNull
    private RestoreVkidFlags restoreVkidFlags;

    @NotNull
    private final VKConnectSignInDelegate$vkAuthListener$1 vkAuthListener;

    @NotNull
    private final VkTokensListener vkTokensListener;
    private boolean vkidAuthWithoutPassword;
    private boolean vkidBindInLogin;

    /* JADX INFO: renamed from: ru.mail.auth.webview.VKConnectSignInDelegate$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001d\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/auth/webview/VKConnectSignInDelegate$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/auth/webview/VKConnectSignInDelegate;", "<init>", "()V", VKConnectSignInDelegate.VKID_RESET_PREF_FILE_NAME, "", VKConnectSignInDelegate.VKID_RESET_PREF_KEY, "LOG", "Lru/mail/util/log/Log;", "VKID_SIGN_IN_PROMO_TAG", "MAX_LENGTH", "", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "(I)[Lru/mail/auth/webview/VKConnectSignInDelegate;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<VKConnectSignInDelegate> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public VKConnectSignInDelegate createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new VKConnectSignInDelegate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public VKConnectSignInDelegate[] newArray(int size) {
            return new VKConnectSignInDelegate[size];
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lru/mail/auth/webview/VKConnectSignInDelegate$Params;", "", "isSoftVkidAutologinDisabled", "", "isResetSoftVkIdEnabled", "isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled", Collector.FLAGS, "Lru/mail/authorizesdk/domain/models/RestoreVkidFlags;", "<init>", "(ZZZLru/mail/authorizesdk/domain/models/RestoreVkidFlags;)V", "()Z", "getFlags", "()Lru/mail/authorizesdk/domain/models/RestoreVkidFlags;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params {

        @NotNull
        private final RestoreVkidFlags flags;
        private final boolean isResetSoftVkIdEnabled;
        private final boolean isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled;
        private final boolean isSoftVkidAutologinDisabled;

        public Params(boolean z10, boolean z11, boolean z12, @NotNull RestoreVkidFlags flags) {
            Intrinsics.checkNotNullParameter(flags, "flags");
            this.isSoftVkidAutologinDisabled = z10;
            this.isResetSoftVkIdEnabled = z11;
            this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled = z12;
            this.flags = flags;
        }

        public static /* synthetic */ Params copy$default(Params params, boolean z10, boolean z11, boolean z12, RestoreVkidFlags restoreVkidFlags, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = params.isSoftVkidAutologinDisabled;
            }
            if ((i10 & 2) != 0) {
                z11 = params.isResetSoftVkIdEnabled;
            }
            if ((i10 & 4) != 0) {
                z12 = params.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled;
            }
            if ((i10 & 8) != 0) {
                restoreVkidFlags = params.flags;
            }
            return params.copy(z10, z11, z12, restoreVkidFlags);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsSoftVkidAutologinDisabled() {
            return this.isSoftVkidAutologinDisabled;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsResetSoftVkIdEnabled() {
            return this.isResetSoftVkIdEnabled;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled() {
            return this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final RestoreVkidFlags getFlags() {
            return this.flags;
        }

        @NotNull
        public final Params copy(boolean isSoftVkidAutologinDisabled, boolean isResetSoftVkIdEnabled, boolean isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled, @NotNull RestoreVkidFlags flags) {
            Intrinsics.checkNotNullParameter(flags, "flags");
            return new Params(isSoftVkidAutologinDisabled, isResetSoftVkIdEnabled, isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled, flags);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return this.isSoftVkidAutologinDisabled == params.isSoftVkidAutologinDisabled && this.isResetSoftVkIdEnabled == params.isResetSoftVkIdEnabled && this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled == params.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled && Intrinsics.areEqual(this.flags, params.flags);
        }

        @NotNull
        public final RestoreVkidFlags getFlags() {
            return this.flags;
        }

        public int hashCode() {
            return (((((Boolean.hashCode(this.isSoftVkidAutologinDisabled) * 31) + Boolean.hashCode(this.isResetSoftVkIdEnabled)) * 31) + Boolean.hashCode(this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled)) * 31) + this.flags.hashCode();
        }

        public final boolean isResetSoftVkIdEnabled() {
            return this.isResetSoftVkIdEnabled;
        }

        public final boolean isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled() {
            return this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled;
        }

        public final boolean isSoftVkidAutologinDisabled() {
            return this.isSoftVkidAutologinDisabled;
        }

        @NotNull
        public String toString() {
            return "Params(isSoftVkidAutologinDisabled=" + this.isSoftVkidAutologinDisabled + ", isResetSoftVkIdEnabled=" + this.isResetSoftVkIdEnabled + ", isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled=" + this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled + ", flags=" + this.flags + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J(\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0016J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\u0014\u0010\u0011\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/auth/webview/VKConnectSignInDelegate$VKConnectLoginCallback;", "Lcom/vk/auth/main/VkClientAuthCallback;", "<init>", "(Lru/mail/auth/webview/VKConnectSignInDelegate;)V", "previousRestoreLoginValue", "", "onAuth", "", "authResult", "Lcom/vk/auth/api/models/AuthResult;", "initCredExchanger", "authorizedEmails", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "onAnotherWayToLogin", "onCancel", "isValid", "Landroid/accounts/Account;", "accountManagerWrapper", "Lru/mail/auth/AccountManagerWrapper;", "isAuthorized", "login", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class VKConnectLoginCallback implements VkClientAuthCallback {
        private boolean previousRestoreLoginValue;

        public VKConnectLoginCallback() {
        }

        private final void initCredExchanger(AuthResult authResult, ArrayList<String> authorizedEmails) {
            CredentialsExchanger.Builder loggerEnabled = new CredentialsExchanger.Builder().setLoggerEnabled(true);
            CredentialsExchanger.SocialBindType socialBindType = VKConnectSignInDelegate.this.vkidBindInLogin ? CredentialsExchanger.SocialBindType.VK_BIND_IN_LOGIN_PROMO : CredentialsExchanger.SocialBindType.VK;
            VKConnectSignInDelegate.LOG.d("On auth start VK Auth with " + VKConnectSignInDelegate.this.activity);
            CredentialsExchanger.startVkAuth$default(loggerEnabled.setSocialBindType(socialBindType).setSoftVkidAutologinDisabled(VKConnectSignInDelegate.this.isSoftVkidAutologinDisabled).setResetSoftVkIdEnabled(VKConnectSignInDelegate.this.isResetSoftVkIdEnabled).build(), false, authorizedEmails, VKConnectSignInDelegate.this.enteredEmail, VKConnectSignInDelegate.this.vkidAuthWithoutPassword, VKConnectSignInDelegate.this.vkidBindInLogin, VKConnectSignInDelegate.this.isFromVkEmailForwarding, VKConnectSignInDelegate.this.isFromRestoreVkid, 1, null);
            VKConnectSignInDelegate.this.resetAuthFlags();
            this.previousRestoreLoginValue = authResult.getAuthTarget().isRestoreLogin();
        }

        private final boolean isAuthorized(String login, AccountManagerWrapper accountManagerWrapper) {
            VKConnectSignInDelegate.LOG.d("onAuth() isAuthorized() begin");
            Account account = new Account(login, "ru.mail");
            VKConnectSignInDelegate.LOG.d("onAuth() isAuthorized() account = " + account);
            String userData = accountManagerWrapper.getUserData(account, Authenticator.KEY_UNAUTHORIZED);
            VKConnectSignInDelegate.LOG.d("onAuth() isAuthorized() accountValue = " + userData);
            return !Intrinsics.areEqual(Authenticator.VALUE_UNAUTHORIZED, userData);
        }

        private final boolean isValid(Account account, AccountManagerWrapper accountManagerWrapper) {
            VKConnectSignInDelegate.LOG.d("onAuth() isValid(): " + account.name);
            String str = account.name;
            if (str == null || StringsKt.isBlank(str)) {
                return false;
            }
            String name = account.name;
            Intrinsics.checkNotNullExpressionValue(name, "name");
            return isAuthorized(name, accountManagerWrapper);
        }

        @Override // com.vk.auth.main.AuthCallback
        @MainThread
        public /* bridge */ void onAccessApproved(@NotNull String str) {
            VkClientAuthCallback.DefaultImpls.onAccessApproved(this, str);
        }

        @Override // com.vk.auth.main.AuthCallback
        @MainThread
        public /* bridge */ void onAccessFlowCancel() {
            VkClientAuthCallback.DefaultImpls.onAccessFlowCancel(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        @Deprecated(message = "Please don't use this method. Use [VkSilentTokenExchanger] for getting SilentAuthSource.")
        public /* bridge */ void onAdditionalOAuthAuth(@NotNull AdditionalOauthAuthResult additionalOauthAuthResult) {
            VkClientAuthCallback.DefaultImpls.onAdditionalOAuthAuth(this, additionalOauthAuthResult);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onAdditionalSignUpError() {
            VkClientAuthCallback.DefaultImpls.onAdditionalSignUpError(this);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        public void onAnotherWayToLogin() {
            Context applicationContext;
            VkClientAuthCallback.DefaultImpls.onAnotherWayToLogin(this);
            VKConnectSignInDelegate.LOG.d("On another way to login with " + VKConnectSignInDelegate.this.activity);
            FragmentActivity fragmentActivity = VKConnectSignInDelegate.this.activity;
            if (fragmentActivity != null && (applicationContext = fragmentActivity.getApplicationContext()) != null) {
                AuthenticatorEntryPoint.INSTANCE.analytics(applicationContext).onSkipRegistrationWithVkc();
            }
            FragmentActivity fragmentActivity2 = VKConnectSignInDelegate.this.activity;
            if (fragmentActivity2 != null) {
                ServiceChooserFragment.startRegistration(fragmentActivity2, AuthSource.LOGIN_VIEW, new Bundle());
            }
        }

        @Override // com.vk.auth.main.AuthCallback
        public void onAuth(@NotNull AuthResult authResult) {
            AuthMessageCallback authMessageCallback;
            Intrinsics.checkNotNullParameter(authResult, "authResult");
            VKConnectSignInDelegate.LOG.d("onAuth()");
            String restoreEmail = FinishRestoreControllerUseCase.INSTANCE.getRestoreEmail(authResult.getMetadata());
            if (restoreEmail == null) {
                restoreEmail = "";
            }
            AutoLoginProvider.Companion companion = AutoLoginProvider.INSTANCE;
            Bundle metadata = authResult.getMetadata();
            if (companion.getAutologinKeyWithClearKeyFromBundle(metadata != null ? MetadataExtKt.getPayload(metadata) : null)) {
                VeryBadTemporaryMediator veryBadTemporaryMediator = VeryBadTemporaryMediator.INSTANCE;
                if (veryBadTemporaryMediator.getFinishAutologinControllerUseCase() != null) {
                    FinishAutologinControllerUseCase finishAutologinControllerUseCase = veryBadTemporaryMediator.getFinishAutologinControllerUseCase();
                    if (finishAutologinControllerUseCase != null) {
                        finishAutologinControllerUseCase.onAutologinFinish();
                    }
                    VKConnectSignInDelegate.LOG.d("VkClientAuthCallback invoke new Autologin logic");
                    return;
                }
            }
            FinishRestoreControllerUseCase finishRestoreControllerUseCase = VeryBadTemporaryMediator.INSTANCE.getFinishRestoreControllerUseCase();
            if (finishRestoreControllerUseCase != null && finishRestoreControllerUseCase.onRestoreFinish(restoreEmail)) {
                VKConnectSignInDelegate.LOG.d("VkClientAuthCallback return after new restore Login logic");
                return;
            }
            if (authResult.getAuthTarget().isRestoreLogin() && !this.previousRestoreLoginValue && !VKConnectSignInDelegate.this.isFromRestoreVkid) {
                VKConnectSignInDelegate.LOG.d("return after restoreLogin");
                return;
            }
            VKConnectSignInDelegate.this.startLoading();
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                VKConnectSignInDelegate.LOG.d("On auth getAccountManager:");
                AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(VKConnectSignInDelegate.this.activity);
                VKConnectSignInDelegate.LOG.d("On auth sort emails:");
                for (Account account : accountManagerWrapper.getAppAccounts()) {
                    Intrinsics.checkNotNull(accountManagerWrapper);
                    if (isValid(account, accountManagerWrapper)) {
                        arrayList.add(account.name);
                    }
                }
            } catch (Exception e10) {
                VKConnectSignInDelegate.LOG.d("On auth exception message = " + e10.getMessage());
            }
            if (VKConnectSignInDelegate.this.vkidBindInLogin && (authMessageCallback = (AuthMessageCallback) VKConnectSignInDelegate.this.mailCallback.get()) != null) {
                authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_POP_BACK_STACK_IF_ALLOWED));
            }
            initCredExchanger(authResult, arrayList);
        }

        @Override // com.vk.auth.main.AuthCallback
        @MainThread
        public /* bridge */ void onCancel(@Nullable Bundle bundle) {
            VkClientAuthCallback.DefaultImpls.onCancel(this, bundle);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        @MainThread
        public /* bridge */ void onCancelEnterPassword() {
            VkClientAuthCallback.DefaultImpls.onCancelEnterPassword(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onEmailSignUpError() {
            VkClientAuthCallback.DefaultImpls.onEmailSignUpError(this);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        @MainThread
        public /* bridge */ void onExternalServiceAuth(@NotNull VkOAuthService vkOAuthService) {
            VkClientAuthCallback.DefaultImpls.onExternalServiceAuth(this, vkOAuthService);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        @MainThread
        public /* bridge */ void onLogout(@NotNull LogoutReason logoutReason) {
            VkClientAuthCallback.DefaultImpls.onLogout(this, logoutReason);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onOAuthConnectResult(@NotNull VkOAuthConnectionResult vkOAuthConnectionResult) {
            VkClientAuthCallback.DefaultImpls.onOAuthConnectResult(this, vkOAuthConnectionResult);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        @MainThread
        public /* bridge */ void onOAuthServiceSelected(@NotNull VkOAuthService vkOAuthService) {
            VkClientAuthCallback.DefaultImpls.onOAuthServiceSelected(this, vkOAuthService);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onPhoneValidationCompleted(@NotNull VkPhoneValidationCompleteResult vkPhoneValidationCompleteResult) {
            VkClientAuthCallback.DefaultImpls.onPhoneValidationCompleted(this, vkPhoneValidationCompleteResult);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onPhoneValidationError(@NotNull VkPhoneValidationErrorReason vkPhoneValidationErrorReason) {
            VkClientAuthCallback.DefaultImpls.onPhoneValidationError(this, vkPhoneValidationErrorReason);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onRestoreBannedUserError() {
            VkClientAuthCallback.DefaultImpls.onRestoreBannedUserError(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onRestoreDeactivatedUserError() {
            VkClientAuthCallback.DefaultImpls.onRestoreDeactivatedUserError(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onSignUp(long j10, @NotNull SignUpData signUpData) {
            VkClientAuthCallback.DefaultImpls.onSignUp(this, j10, signUpData);
        }

        @Override // com.vk.auth.main.VkClientAuthCallback
        @MainThread
        public /* bridge */ void onTertiaryButtonClick() {
            VkClientAuthCallback.DefaultImpls.onTertiaryButtonClick(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        public /* bridge */ void onValidatePhoneError() {
            VkClientAuthCallback.DefaultImpls.onValidatePhoneError(this);
        }

        @Override // com.vk.auth.main.AuthCallback
        public void onCancel() {
            VkClientAuthCallback.DefaultImpls.onCancel(this);
            VKConnectSignInDelegate.this.vkidAuthWithoutPassword = false;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lru/mail/auth/webview/VKConnectSignInDelegate$VkTokensListener;", "Lru/mail/credentialsexchanger/core/CredentialsExchanger$VkTokensListener;", "<init>", "(Lru/mail/auth/webview/VKConnectSignInDelegate;)V", "onVKIDSignIn", "Lru/mail/credentialsexchanger/core/CredentialsExchanger$VkTokensListener$VkTokensListenerResponse;", "silentToken", "", SilentAuthInfo.KEY_UUID, "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class VkTokensListener implements CredentialsExchanger.VkTokensListener {
        public VkTokensListener() {
        }

        @Override // ru.mail.credentialsexchanger.core.CredentialsExchanger.VkTokensListener
        @NotNull
        public CredentialsExchanger.VkTokensListener.VkTokensListenerResponse onVKIDSignIn(@NotNull String silentToken, @NotNull String uuid) {
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            CredentialsExchanger.INSTANCE.removeVkTokensListener();
            SocialAuthSdk.INSTANCE.setUnauthorized();
            VkCredResponse vkCredentials$default = CredentialsExchanger.getVkCredentials$default(new CredentialsExchanger.Builder().build(), silentToken, uuid, VkIdAuthSource.Restore, false, VKConnectSignInDelegate.this.shouldAddRestoreParam(), 8, null);
            if (vkCredentials$default instanceof VkCredResponse.Success) {
                VKConnectSignInDelegate.LOG.d("exchangeSilentToken() success");
                VkCredResponse.Success success = (VkCredResponse.Success) vkCredentials$default;
                return new CredentialsExchanger.VkTokensListener.VkTokensListenerResponse.Success(success.getVkToken(), success.getVkId());
            }
            if (!(vkCredentials$default instanceof VkCredResponse.Fail)) {
                throw new NoWhenBranchMatchedException();
            }
            VkCredResponse.Fail fail = (VkCredResponse.Fail) vkCredentials$default;
            VKConnectSignInDelegate.LOG.w("exchangeSilentToken() error = " + fail.getError());
            return new CredentialsExchanger.VkTokensListener.VkTokensListenerResponse.Fail(fail.getError());
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.webview.VKConnectSignInDelegate$initiateVkEmailForwarding$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 implements VkEmailForwardingCallback, FunctionAdapter {
        AnonymousClass1() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof VkEmailForwardingCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, VKConnectSignInDelegate.this, VKConnectSignInDelegate.class, "handleVkEmailForwarding", "handleVkEmailForwarding(Lcom/vk/emailforwarding/VkEmailForwardingCallback$Result;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.vk.emailforwarding.VkEmailForwardingCallback
        public final void onResult(VkEmailForwardingCallback.Result p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            VKConnectSignInDelegate.this.handleVkEmailForwarding(p10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.webview.VKConnectSignInDelegate$startRestoreVkid$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 implements VkEmailForwardingCallback, FunctionAdapter {
        AnonymousClass2() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof VkEmailForwardingCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, VKConnectSignInDelegate.this, VKConnectSignInDelegate.class, "handleEmailForwardingInRestore", "handleEmailForwardingInRestore(Lcom/vk/emailforwarding/VkEmailForwardingCallback$Result;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.vk.emailforwarding.VkEmailForwardingCallback
        public final void onResult(VkEmailForwardingCallback.Result p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            VKConnectSignInDelegate.this.handleEmailForwardingInRestore(p10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.webview.VKConnectSignInDelegate$startRestoreVkid$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass3 implements VkMailRestore.RestoreCallback, FunctionAdapter {
        AnonymousClass3() {
        }

        public final boolean equals(Object obj) {
            if ((obj instanceof VkMailRestore.RestoreCallback) && (obj instanceof FunctionAdapter)) {
                return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
            }
            return false;
        }

        @Override // kotlin.jvm.internal.FunctionAdapter
        public final Function<?> getFunctionDelegate() {
            return new FunctionReferenceImpl(1, VKConnectSignInDelegate.this, VKConnectSignInDelegate.class, "handleRestore", "handleRestore(Lcom/vk/emailforwarding/VkMailRestore$Result;)V", 0);
        }

        public final int hashCode() {
            return getFunctionDelegate().hashCode();
        }

        @Override // com.vk.emailforwarding.VkMailRestore.RestoreCallback
        public final void invoke(VkMailRestore.Result p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            VKConnectSignInDelegate.this.handleRestore(p10);
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [ru.mail.auth.webview.VKConnectSignInDelegate$vkAuthListener$1] */
    public VKConnectSignInDelegate() {
        this.mailCallback = new WeakReference<>(null);
        this.vkTokensListener = new VkTokensListener();
        this.progressBar = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.authCallback = new VKConnectLoginCallback();
        this.restoreVkidFlags = new RestoreVkidFlags(false, false, false, false, false);
        this.vkAuthListener = new VkAuthListener() { // from class: ru.mail.auth.webview.VKConnectSignInDelegate$vkAuthListener$1
            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void hideLoading() {
                this.this$0.stopLoading();
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void onError(Response.Fail error) {
                Intrinsics.checkNotNullParameter(error, "error");
                VKConnectSignInDelegate.LOG.w("onError = " + error);
                this.this$0.stopLoading();
                Bundle bundle = new Bundle();
                bundle.putInt("errorCode", error.getErrorCode());
                String errorMessage = AuthErrors.getErrorMessage(this.this$0.activity, "", error.getErrorCode());
                AuthMessageCallback authMessageCallback = (AuthMessageCallback) this.this$0.mailCallback.get();
                if (authMessageCallback != null) {
                    authMessageCallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, bundle, errorMessage));
                }
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void onProcessFinished(VkAuthResult result) {
                Context applicationContext;
                Intrinsics.checkNotNullParameter(result, "result");
                VKConnectSignInDelegate.LOG.d("onProcessFinished with mailCallback = " + this.this$0.mailCallback);
                if (result instanceof VkAuthResult.LoginResult) {
                    VKConnectSignInDelegate.LOG.d(LoginResult.RESULT_KEY);
                    this.this$0.startLoading();
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    VkAuthResult.LoginResult loginResult = (VkAuthResult.LoginResult) result;
                    SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(null, loginResult.getSocialBindType()));
                    AuthMessageCallback authMessageCallback = (AuthMessageCallback) this.this$0.mailCallback.get();
                    if (authMessageCallback != null) {
                        authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_LOGIN_RESULT, null, result));
                    }
                    FragmentActivity fragmentActivity = this.this$0.activity;
                    if (fragmentActivity == null || (applicationContext = fragmentActivity.getApplicationContext()) == null) {
                        return;
                    }
                    AuthenticatorEntryPoint.INSTANCE.analytics(applicationContext).startEmailAuthBySocial(loginResult.getSocialBindType().getStringToken());
                    return;
                }
                if (result instanceof VkAuthResult.GoToLogin) {
                    VKConnectSignInDelegate.LOG.d("GoToLogin");
                    this.this$0.stopLoading();
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    AuthMessageCallback authMessageCallback2 = (AuthMessageCallback) this.this$0.mailCallback.get();
                    if (authMessageCallback2 != null) {
                        authMessageCallback2.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_GO_TO_LOGIN, null, result));
                        return;
                    }
                    return;
                }
                if (result instanceof VkAuthResult.GoToSignup) {
                    VKConnectSignInDelegate.LOG.d("GoToSignup");
                    this.this$0.stopLoading();
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(true);
                    AuthMessageCallback authMessageCallback3 = (AuthMessageCallback) this.this$0.mailCallback.get();
                    if (authMessageCallback3 != null) {
                        authMessageCallback3.onMessageHandle(new Message(Message.Id.ON_REGISTRATION_STARTED));
                    }
                    AuthMessageCallback authMessageCallback4 = (AuthMessageCallback) this.this$0.mailCallback.get();
                    if (authMessageCallback4 != null) {
                        authMessageCallback4.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_GO_TO_SIGNUP, null, result));
                        return;
                    }
                    return;
                }
                if (result instanceof VkAuthResult.OnDestroyFragment) {
                    VKConnectSignInDelegate.LOG.d("OnDestroyFragment");
                    this.this$0.stopLoading();
                    SocialLoginInfoHolder.clear$default(false, 1, null);
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    return;
                }
                if (result instanceof VkAuthResult.OpenVkidBottomSheet) {
                    VKConnectSignInDelegate.LOG.d("OpenVkidBottomSheet");
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    VKConnectSignInDelegate.startVKConnectAuth$default(this.this$0, true, false, false, 4, null);
                    return;
                }
                if (result instanceof VkAuthResult.GoToSecondStep) {
                    VKConnectSignInDelegate.LOG.d("GoToSecondStep");
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    VkAuthResult.GoToSecondStep goToSecondStep = (VkAuthResult.GoToSecondStep) result;
                    SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(goToSecondStep.getBindToken(), goToSecondStep.getSocialBindType()));
                    AuthMessageCallback authMessageCallback5 = (AuthMessageCallback) this.this$0.mailCallback.get();
                    if (authMessageCallback5 != null) {
                        authMessageCallback5.onMessageHandle(new Message(Message.Id.START_SECOND_STEP_NEW));
                        return;
                    }
                    return;
                }
                if (!(result instanceof VkAuthResult.GoToRestoreVkid)) {
                    SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                    return;
                }
                SocialLoginInfoHolder.setSkipCleanSocialLogin(false);
                if (this.this$0.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled) {
                    this.this$0.startRestoreVkid(((VkAuthResult.GoToRestoreVkid) result).getEmail(), RestoreVkidStartSource.CHOICE);
                    return;
                }
                RestoreVkEmailHelper restoreVkEmailHelper = this.this$0.restoreVkEmailHelper;
                if (restoreVkEmailHelper != null) {
                    restoreVkEmailHelper.showUnblockWebViewFragment(((VkAuthResult.GoToRestoreVkid) result).getUrl(), true);
                }
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public /* bridge */ void shouldShowFragment(ScreenType screenType) {
                super.shouldShowFragment(screenType);
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public /* bridge */ void shouldShowFragmentSingleTop(ScreenType screenType) {
                super.shouldShowFragmentSingleTop(screenType);
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void showDialog(DialogFragment fragment) {
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                VKConnectSignInDelegate.LOG.d("showDialog");
                this.this$0.stopLoading();
                AuthMessageCallback authMessageCallback = (AuthMessageCallback) this.this$0.mailCallback.get();
                if (authMessageCallback != null) {
                    authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_POP_BACK_STACK_IF_ALLOWED, null, fragment));
                }
                AuthMessageCallback authMessageCallback2 = (AuthMessageCallback) this.this$0.mailCallback.get();
                if (authMessageCallback2 != null) {
                    authMessageCallback2.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_OPEN_DIALOG, null, fragment));
                }
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void showFragment(Fragment fragment) {
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                VKConnectSignInDelegate.LOG.d("showFragment");
                this.this$0.stopLoading();
                AuthMessageCallback authMessageCallback = (AuthMessageCallback) this.this$0.mailCallback.get();
                if (authMessageCallback != null) {
                    authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_OPEN_FRAGMENT, null, fragment));
                }
            }

            @Override // ru.mail.credentialsexchanger.core.VkAuthListener
            public void showFragmentSingleTop(Fragment fragment) {
                Intrinsics.checkNotNullParameter(fragment, "fragment");
                VKConnectSignInDelegate.LOG.d("showFragmentSingleTop");
                this.this$0.stopLoading();
                Bundle bundle = new Bundle();
                bundle.putString(LoginActivity.EXTRA_FRAGMENT_TAG, fragment.getClass().toString());
                AuthMessageCallback authMessageCallback = (AuthMessageCallback) this.this$0.mailCallback.get();
                if (authMessageCallback != null) {
                    authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_OPEN_FRAGMENT_SINGLE_TOP, bundle, fragment));
                }
            }
        };
    }

    private final void addVkTokensListenerIfNeeded() {
        if (shouldAddRestoreParam()) {
            CredentialsExchanger.INSTANCE.addVkTokensListener(this.vkTokensListener);
        }
    }

    private final void analytics(Function1<? super Analytics, Unit> action) {
        Context applicationContext;
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null || (applicationContext = fragmentActivity.getApplicationContext()) == null) {
            return;
        }
        action.invoke(AuthenticatorEntryPoint.INSTANCE.analytics(applicationContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleEmailForwardingInRestore(VkEmailForwardingCallback.Result it) {
        final VkEmailForwardingResult vkEmailForwardingResultMapVkEmailForwardingCallback = mapVkEmailForwardingCallback(it);
        if (vkEmailForwardingResultMapVkEmailForwardingCallback instanceof VkEmailForwardingResult.Error) {
            analytics(new Function1() { // from class: ru.mail.auth.webview.f
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleEmailForwardingInRestore$lambda$0(vkEmailForwardingResultMapVkEmailForwardingCallback, (Analytics) obj);
                }
            });
            stopLoading();
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", -1);
            String errorMessage = AuthErrors.getErrorMessage(this.activity, "", -1);
            AuthMessageCallback authMessageCallback = this.mailCallback.get();
            if (authMessageCallback != null) {
                authMessageCallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, bundle, errorMessage));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleEmailForwardingInRestore$lambda$0(VkEmailForwardingResult vkEmailForwardingResult, Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        String message = ((VkEmailForwardingResult.Error) vkEmailForwardingResult).getCause().getMessage();
        analytics.onRestoreVkidError(message != null ? UtilsKt.substringSafe(message, 0, 20) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRestore(VkMailRestore.Result result) {
        final RestoreVkidResult restoreVkidResultMapResult = mapResult(result);
        if (restoreVkidResultMapResult instanceof RestoreVkidResult.OpenMailRestore) {
            RestoreVkidResult.OpenMailRestore openMailRestore = (RestoreVkidResult.OpenMailRestore) restoreVkidResultMapResult;
            openMailRestore(openMailRestore.getEmail(), openMailRestore.isRebind());
            return;
        }
        if (Intrinsics.areEqual(restoreVkidResultMapResult, RestoreVkidResult.Cancel.INSTANCE)) {
            CredentialsExchanger.INSTANCE.removeVkTokensListener();
            analytics(new Function1() { // from class: ru.mail.auth.webview.g
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleRestore$lambda$0((Analytics) obj);
                }
            });
            return;
        }
        if (restoreVkidResultMapResult instanceof RestoreVkidResult.Error) {
            analytics(new Function1() { // from class: ru.mail.auth.webview.h
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleRestore$lambda$1(restoreVkidResultMapResult, (Analytics) obj);
                }
            });
            return;
        }
        if (restoreVkidResultMapResult instanceof RestoreVkidResult.OpenAuth) {
            this.enteredEmail = ((RestoreVkidResult.OpenAuth) restoreVkidResultMapResult).getEmail();
            analytics(new Function1() { // from class: ru.mail.auth.webview.i
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleRestore$lambda$2((Analytics) obj);
                }
            });
        } else if (restoreVkidResultMapResult instanceof RestoreVkidResult.OpenVkRestore) {
            this.enteredEmail = ((RestoreVkidResult.OpenVkRestore) restoreVkidResultMapResult).getEmail();
            analytics(new Function1() { // from class: ru.mail.auth.webview.j
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleRestore$lambda$3((Analytics) obj);
                }
            });
        } else {
            if (!(restoreVkidResultMapResult instanceof RestoreVkidResult.SuccessVkAuth)) {
                throw new NoWhenBranchMatchedException();
            }
            analytics(new Function1() { // from class: ru.mail.auth.webview.k
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleRestore$lambda$4((Analytics) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleRestore$lambda$0(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidCancel();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleRestore$lambda$1(RestoreVkidResult restoreVkidResult, Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        String msg = ((RestoreVkidResult.Error) restoreVkidResult).getMsg();
        analytics.onRestoreVkidError(msg != null ? UtilsKt.substringSafe(msg, 0, 20) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleRestore$lambda$2(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidOpenAuth();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleRestore$lambda$3(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidOpenVkRestore();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleRestore$lambda$4(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidSuccessVkAuth();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleVkEmailForwarding(final VkEmailForwardingCallback.Result result) {
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.AuthenticatedBySession.INSTANCE)) {
            LOG.d("AuthenticatedBySession");
            analytics(new Function1() { // from class: ru.mail.auth.webview.d
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleVkEmailForwarding$lambda$0((Analytics) obj);
                }
            });
            return;
        }
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.ValidationLaunched.INSTANCE)) {
            LOG.d("ValidationLaunched");
            analytics(new Function1() { // from class: ru.mail.auth.webview.l
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleVkEmailForwarding$lambda$1((Analytics) obj);
                }
            });
            return;
        }
        if (result instanceof VkEmailForwardingCallback.Result.Error) {
            analytics(new Function1() { // from class: ru.mail.auth.webview.m
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleVkEmailForwarding$lambda$2(result, (Analytics) obj);
                }
            });
            Log log = LOG;
            log.e("Error occurred during VK email forwarding", ((VkEmailForwardingCallback.Result.Error) result).getCause());
            log.d("Starting VK connect auth");
            startVKConnectAuth$default(this, false, false, false, 7, null);
            return;
        }
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.Cancelled.INSTANCE)) {
            analytics(new Function1() { // from class: ru.mail.auth.webview.n
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.handleVkEmailForwarding$lambda$3((Analytics) obj);
                }
            });
            return;
        }
        if (!Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.SwitchToPassword.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        }
        LOG.d("Switch to password");
        analytics(new Function1() { // from class: ru.mail.auth.webview.o
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VKConnectSignInDelegate.handleVkEmailForwarding$lambda$4((Analytics) obj);
            }
        });
        AuthMessageCallback authMessageCallback = this.mailCallback.get();
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.START_SECOND_STEP_NEW));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleVkEmailForwarding$lambda$0(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onVkEmailForwardingSuccess();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleVkEmailForwarding$lambda$1(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onVkEmailForwardingSuccess();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleVkEmailForwarding$lambda$2(VkEmailForwardingCallback.Result result, Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        String message = ((VkEmailForwardingCallback.Result.Error) result).getCause().getMessage();
        if (message == null) {
            message = "VkEmailForwardingCallback.Result.Error";
        }
        analytics.onVkEmailForwardingError(message);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleVkEmailForwarding$lambda$3(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onVkEmailForwardingCancel();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handleVkEmailForwarding$lambda$4(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onVkEmailForwardingGoToPassword();
        return Unit.INSTANCE;
    }

    private final void initiateVkEmailForwarding(String superAppToken, boolean isMailPasswordEnabled) {
        this.isFromVkEmailForwarding = true;
        LOG.d("Starting VK email forwarding with token: " + superAppToken);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            return;
        }
        new VkEmailForwarding(superAppToken, anonymousClass1, fragmentActivity, isMailPasswordEnabled).auth();
    }

    private final RestoreVkidResult mapResult(VkMailRestore.Result it) {
        if (it instanceof VkMailRestore.Result.OpenVkRestore) {
            return new RestoreVkidResult.OpenVkRestore(((VkMailRestore.Result.OpenVkRestore) it).getEmail());
        }
        if (Intrinsics.areEqual(it, VkMailRestore.Result.Cancel.INSTANCE)) {
            return RestoreVkidResult.Cancel.INSTANCE;
        }
        if (it instanceof VkMailRestore.Result.Error) {
            String message = ((VkMailRestore.Result.Error) it).getThrowable().getMessage();
            return new RestoreVkidResult.Error(message != null ? UtilsKt.substringSafe(message, 0, 20) : null);
        }
        if (it instanceof VkMailRestore.Result.OpenAuth) {
            return new RestoreVkidResult.OpenAuth(((VkMailRestore.Result.OpenAuth) it).getEmail());
        }
        if (it instanceof VkMailRestore.Result.OpenMailRestore) {
            return new RestoreVkidResult.OpenMailRestore(((VkMailRestore.Result.OpenMailRestore) it).getEmail(), false);
        }
        if (Intrinsics.areEqual(it, VkMailRestore.Result.Auth.INSTANCE)) {
            return new RestoreVkidResult.SuccessVkAuth(this.enteredEmail);
        }
        if (!Intrinsics.areEqual(it, VkMailRestore.Result.OpenSwitchVkId.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        }
        String str = this.enteredEmail;
        if (str == null) {
            str = "";
        }
        return new RestoreVkidResult.OpenMailRestore(str, true);
    }

    private final VkEmailForwardingResult mapVkEmailForwardingCallback(VkEmailForwardingCallback.Result result) {
        LOG.d("VkEmailForwarding result=" + result);
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.AuthenticatedBySession.INSTANCE)) {
            return VkEmailForwardingResult.AuthenticatedBySession.INSTANCE;
        }
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.Cancelled.INSTANCE)) {
            return VkEmailForwardingResult.Cancelled.INSTANCE;
        }
        if (result instanceof VkEmailForwardingCallback.Result.Error) {
            return new VkEmailForwardingResult.Error(((VkEmailForwardingCallback.Result.Error) result).getCause());
        }
        if (Intrinsics.areEqual(result, VkEmailForwardingCallback.Result.ValidationLaunched.INSTANCE)) {
            return VkEmailForwardingResult.ValidationLaunched.INSTANCE;
        }
        if (result instanceof VkEmailForwardingCallback.Result.SwitchToPassword) {
            return new VkEmailForwardingResult.GoToPassword(new ru.mail.social_auth.domain.AuthResult.GoToSecondStep(null, null, 3, null));
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void openMailRestore(String emailForRestore, final boolean isRebind) {
        Context applicationContext;
        if (this.activity == null) {
            analytics(new Function1() { // from class: ru.mail.auth.webview.q
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VKConnectSignInDelegate.openMailRestore$lambda$0((Analytics) obj);
                }
            });
            stopLoading();
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", -1);
            String errorMessage = AuthErrors.getErrorMessage(this.activity, "", -1);
            AuthMessageCallback authMessageCallback = this.mailCallback.get();
            if (authMessageCallback != null) {
                authMessageCallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, bundle, errorMessage));
                return;
            }
            return;
        }
        analytics(new Function1() { // from class: ru.mail.auth.webview.r
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VKConnectSignInDelegate.openMailRestore$lambda$2(isRebind, (Analytics) obj);
            }
        });
        BaseToolbarActivity.hideKeyboard(this.activity);
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null || (applicationContext = fragmentActivity.getApplicationContext()) == null) {
            return;
        }
        Uri uri = Uri.parse(new HostProviderWrapperImpl(applicationContext).getSchemeOrHost(R.string.restore_password_url));
        Bundle bundle2 = new Bundle();
        RestorePasswordParams restorePasswordParams = RestorePasswordParams.INSTANCE;
        Intrinsics.checkNotNull(uri);
        restorePasswordParams.putRestoreUri(uri, bundle2);
        if (emailForRestore == null) {
            emailForRestore = "";
        }
        restorePasswordParams.putRestoreLogin(emailForRestore, bundle2);
        restorePasswordParams.putIsRebind(isRebind, bundle2);
        AuthMessageCallback authMessageCallback2 = this.mailCallback.get();
        if (authMessageCallback2 != null) {
            authMessageCallback2.onMessageHandle(new Message(Message.Id.START_RESTORE_PASSWORD, bundle2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit openMailRestore$lambda$0(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        LOG.d("activity null during restore");
        analytics.onRestoreVkidError("activity null");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit openMailRestore$lambda$2(boolean z10, Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidOpenMailRestore(z10);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetAuthFlags() {
        this.vkidAuthWithoutPassword = false;
        this.vkidBindInLogin = false;
        this.isFromVkEmailForwarding = false;
        this.isFromRestoreVkid = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldAddRestoreParam() {
        if (this.isFromRestoreVkid) {
            return RestoreVkidHelperKt.shouldAddRestoreParamToAuthRequestDuringRestore(this.restoreVkidFlags.getAllEmailWithoutBlockedEnabled(), this.restoreVkidFlags.getMrimUnblockEnabled(), this.restoreVkidFlags.getNpcUnblockEnabled(), this.restoreVkidFlags.getRestoreAuthParamEnabled());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startLoading() {
        this.progressBar.tryEmit(Boolean.TRUE);
        AuthMessageCallback authMessageCallback = this.mailCallback.get();
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_START_LOADING, null, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startRestoreVkid$lambda$0(RestoreVkidStartSource restoreVkidStartSource, Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onRestoreVkidStart(restoreVkidStartSource.name());
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void startVKConnectAuth$default(VKConnectSignInDelegate vKConnectSignInDelegate, boolean z10, boolean z11, boolean z12, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = false;
        }
        if ((i10 & 2) != 0) {
            z11 = false;
        }
        if ((i10 & 4) != 0) {
            z12 = false;
        }
        vKConnectSignInDelegate.startVKConnectAuth(z10, z11, z12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVKConnectAuth$lambda$0$0$0(VKConnectSignInDelegate vKConnectSignInDelegate) {
        AuthMessageCallback authMessageCallback = vKConnectSignInDelegate.mailCallback.get();
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.START_SECOND_STEP_NEW));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVkEmailForwarding$lambda$0(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onStartVkEmailForwarding();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startVkEmailForwarding$lambda$1(Analytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "$this$analytics");
        analytics.onVkEmailForwardingError("Super app token is empty");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopLoading() {
        this.progressBar.tryEmit(Boolean.FALSE);
        AuthMessageCallback authMessageCallback = this.mailCallback.get();
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.TOKEN_EXCHANGER_STOP_LOADING, null, null));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NotNull
    public final MutableStateFlow<Boolean> getProgressBar() {
        return this.progressBar;
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onCreate(@NotNull FragmentActivity context, @NotNull AuthMessageCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        LOG.d("On create " + context);
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onDestroy() {
        SharedPreferences.Editor editorEdit;
        SharedPreferences.Editor editorPutBoolean;
        FragmentActivity fragmentActivity = this.activity;
        SharedPreferences sharedPreferences = fragmentActivity != null ? fragmentActivity.getSharedPreferences(VKID_RESET_PREF_FILE_NAME, 0) : null;
        if (sharedPreferences != null ? sharedPreferences.getBoolean(VKID_RESET_PREF_KEY, false) : false) {
            VkClientAuthLib.logout$default(VkClientAuthLib.INSTANCE, null, null, null, 7, null);
            VKAccessTokenProvider anonymousTokenProvider = SuperappApiCore.INSTANCE.getAnonymousTokenProvider();
            if (anonymousTokenProvider != null) {
                anonymousTokenProvider.clear();
            }
            FragmentActivity fragmentActivity2 = this.activity;
            if (fragmentActivity2 != null) {
                VK.clearAccessToken(fragmentActivity2);
                new OAuthTransitionManager(fragmentActivity2).reset();
            }
            AuthenticatorConfig.getInstance().setOAuthEnabledForSession(true);
            if (sharedPreferences != null && (editorEdit = sharedPreferences.edit()) != null && (editorPutBoolean = editorEdit.putBoolean(VKID_RESET_PREF_KEY, false)) != null) {
                editorPutBoolean.apply();
            }
        }
        VkClientAuthLib.INSTANCE.removeAuthCallback(this.authCallback);
        CredentialsExchanger.Companion companion = CredentialsExchanger.INSTANCE;
        companion.removeVkAuthCallback(this.vkAuthListener);
        SocialLoginInfoHolder.clear$default(false, 1, null);
        companion.removeVkTokensListener();
        LOG.d("On destroy " + this.activity);
        this.activity = null;
        this.progressBar.tryEmit(Boolean.FALSE);
    }

    public final void onEmailEntered(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.enteredEmail = email;
        IsEmailEnteredHolder.INSTANCE.setEmailEntered(ru.mail.credentialsexchanger.UtilsKt.isValidEmail(email));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onResume(@NotNull FragmentActivity context, @NotNull AuthMessageCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.activity = context;
        this.mailCallback = new WeakReference<>(callback);
        RestoreVkEmailHelperHolder restoreVkEmailHelperHolder = context instanceof RestoreVkEmailHelperHolder ? (RestoreVkEmailHelperHolder) context : null;
        this.restoreVkEmailHelper = restoreVkEmailHelperHolder != null ? restoreVkEmailHelperHolder.getRestoreVkEmailHelper() : null;
        LOG.d("On resume " + this.activity);
        VkClientAuthLib vkClientAuthLib = VkClientAuthLib.INSTANCE;
        vkClientAuthLib.removeAuthCallback(this.authCallback);
        vkClientAuthLib.addAuthCallback(this.authCallback);
        CredentialsExchanger.INSTANCE.addVkAuthCallback(this.vkAuthListener);
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onStop() {
        LOG.d("On stop " + this.activity);
    }

    public final void setParams(@NotNull Params params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.isSoftVkidAutologinDisabled = params.isSoftVkidAutologinDisabled();
        this.isResetSoftVkIdEnabled = params.isResetSoftVkIdEnabled();
        this.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled = params.isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled();
        this.restoreVkidFlags = params.getFlags();
    }

    public final void startRestoreVkid(@Nullable String emailForRestore, @NotNull final RestoreVkidStartSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        analytics(new Function1() { // from class: ru.mail.auth.webview.e
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VKConnectSignInDelegate.startRestoreVkid$lambda$0(source, (Analytics) obj);
            }
        });
        this.isFromRestoreVkid = true;
        onEmailEntered(emailForRestore == null ? "" : emailForRestore);
        addVkTokensListenerIfNeeded();
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            return;
        }
        new VkMailRestore(new VkMailRestore.Params(emailForRestore, fragmentActivity, new AnonymousClass2(), new AnonymousClass3(), this.restoreVkidFlags.getAllEmailWithoutBlockedEnabled(), this.restoreVkidFlags.getNpcUnblockEnabled(), this.restoreVkidFlags.getMailVkidRebindEnabled(), this.restoreVkidFlags.getMrimUnblockEnabled(), true)).start();
    }

    public final void startVKAutoLogin(@NotNull Function1<? super AutoLoginProvider.Result, Unit> onResult) {
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        LOG.d("Start VK AutoLogin by " + this.activity);
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity != null) {
            AutoLoginProvider.start$default(new AutoLoginProvider(), fragmentActivity, onResult, false, 4, null);
        }
    }

    public final void startVKConnectAuth(boolean vkidAuthWithoutPassword, boolean secondStepAvailable, boolean vkidBindInLogin) {
        this.vkidAuthWithoutPassword = vkidAuthWithoutPassword;
        this.vkidBindInLogin = vkidBindInLogin;
        LOG.d("Start VK Connect Auth by " + this.activity);
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity != null) {
            if (!vkidAuthWithoutPassword) {
                VkFastLoginBottomSheetFragment.Builder isMailBindFlow = new VkFastLoginBottomSheetFragment.Builder().setDismissOnComplete(true).setIsMailBindFlow(vkidBindInLogin);
                FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                isMailBindFlow.show(supportFragmentManager, VKID_SIGN_IN_PROMO_TAG);
                return;
            }
            VkFastLoginBottomSheetFragment vkFastLoginBottomSheetFragmentCreate = new CustomVKFastLoginViewFragment.Builder().setIsMailBindFlow(vkidBindInLogin).create();
            Intrinsics.checkNotNull(vkFastLoginBottomSheetFragmentCreate, "null cannot be cast to non-null type ru.mail.auth.webview.CustomVKFastLoginViewFragment");
            CustomVKFastLoginViewFragment customVKFastLoginViewFragment = (CustomVKFastLoginViewFragment) vkFastLoginBottomSheetFragmentCreate;
            if (secondStepAvailable) {
                customVKFastLoginViewFragment.setAlternativeAuthCallback(new Function0() { // from class: ru.mail.auth.webview.p
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return VKConnectSignInDelegate.startVKConnectAuth$lambda$0$0$0(this.f80849a);
                    }
                });
            }
            FragmentManager supportFragmentManager2 = fragmentActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "getSupportFragmentManager(...)");
            customVKFastLoginViewFragment.showAllowingStateLoss(supportFragmentManager2, VKID_SIGN_IN_PROMO_TAG);
        }
    }

    public final void startVkEmailForwarding(@NotNull String superAppToken, boolean isMailPasswordEnabled) {
        Intrinsics.checkNotNullParameter(superAppToken, "superAppToken");
        analytics(new Function1() { // from class: ru.mail.auth.webview.s
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VKConnectSignInDelegate.startVkEmailForwarding$lambda$0((Analytics) obj);
            }
        });
        if (!StringsKt.isBlank(superAppToken)) {
            initiateVkEmailForwarding(superAppToken, isMailPasswordEnabled);
            return;
        }
        analytics(new Function1() { // from class: ru.mail.auth.webview.t
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VKConnectSignInDelegate.startVkEmailForwarding$lambda$1((Analytics) obj);
            }
        });
        LOG.e("Super app token is empty, starting VK connect auth");
        startVKConnectAuth$default(this, false, false, false, 7, null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VKConnectSignInDelegate(@NotNull Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onNewIntent(@Nullable Intent intent) {
    }
}
