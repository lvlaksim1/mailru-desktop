package ru.mail.portal.kit.auth;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.sun.mail.imap.IMAPStore;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.core.ui.floating_view.FloatingViewGesturesHelper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.MailAccountConstants;
import ru.mail.kit.auth.AuthProvider;
import ru.mail.kit.auth.account.HostAccountInfo;
import ru.mail.kit.auth.analytics.AuthAnalytics;
import ru.mail.kit.auth.info.AccessTokenAuthInfo;
import ru.mail.kit.auth.info.AuthErrorReason;
import ru.mail.kit.auth.session.provider.web.WebSessionCookieProvider;
import ru.mail.kit.result.tools.Result;
import ru.mail.logic.auth.AuthUtilsKt;
import ru.mail.logic.auth.GetAccessTokenByRefreshToken;
import ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand;
import ru.mail.logic.auth.GetConvertAuthCodeByAccessTokenCommand;
import ru.mail.logic.auth.SuperAppKitIds;
import ru.mail.logic.auth.TokenExchangeCommand;
import ru.mail.logic.auth.TokenExchangeParams;
import ru.mail.logic.content.DataManager;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutionResult;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.sdk.MailSdk;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.serverapi.FolderState;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiInvocationException;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\b\b\u0007\u0018\u0000 b2\u00020\u0001:\u0001bB7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016J!\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u001f2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002¢\u0006\u0002\u0010(J(\u0010)\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u001fH\u0096@¢\u0006\u0002\u0010+J(\u0010,\u001a\u0004\u0018\u00010\"2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dH\u0082@¢\u0006\u0002\u0010/J@\u00100\u001a\u0004\u0018\u00010\"2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H\u0082@¢\u0006\u0002\u00105J\u0018\u00106\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u001fH\u0002J\u0018\u00107\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u001fH\u0002J2\u00108\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\b\b\u0002\u00109\u001a\u00020$H\u0082@¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020\tH\u0016J\u0010\u0010<\u001a\u0004\u0018\u00010.2\u0006\u0010\u001c\u001a\u00020\u001dJ4\u0010=\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0082@¢\u0006\u0002\u0010>J\u001a\u0010?\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.H\u0002J0\u0010@\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010A\u001a\u000204H\u0082@¢\u0006\u0002\u0010>J0\u0010B\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010A\u001a\u000204H\u0082@¢\u0006\u0002\u0010>J8\u0010C\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010A\u001a\u0002042\u0006\u0010D\u001a\u00020EH\u0082@¢\u0006\u0002\u0010FJ8\u0010G\u001a\u0004\u0018\u00010\"2\u0006\u0010H\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0082@¢\u0006\u0002\u0010IJ0\u0010J\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u00102\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010KJH\u0010L\u001a\u0004\u0018\u00010\"2\u0006\u0010M\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010N\u001a\u00020\u001f2\u0006\u0010O\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\u001f2\u0006\u0010P\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010QJ\u001e\u0010R\u001a\u0004\u0018\u00010\u001f*\u00020.2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010S\u001a\u00020\u001fH\u0002J$\u0010T\u001a\u00020\u001b*\u00020.2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010S\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u001fH\u0002J\f\u0010V\u001a\u00020$*\u00020.H\u0002J\u0012\u0010W\u001a\u00020$2\b\u0010\u001c\u001a\u0004\u0018\u00010.H\u0002J\u001a\u0010X\u001a\u00020\u001b2\u0006\u0010Y\u001a\u00020\u001f2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J!\u0010\\\u001a\u00020$2\b\u0010]\u001a\u0004\u0018\u00010'2\u0006\u0010\u001c\u001a\u00020.H\u0000¢\u0006\u0004\b^\u0010_J \u0010`\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001c\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006c"}, d2 = {"Lru/mail/portal/kit/auth/PortalAuthProvider;", "Lru/mail/kit/auth/AuthProvider;", "context", "Landroid/content/Context;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "dataManager", "Lru/mail/logic/content/DataManager;", "sessionCookieProvider", "Lru/mail/kit/auth/session/provider/web/WebSessionCookieProvider;", "authAnalytics", "Lru/mail/kit/auth/analytics/AuthAnalytics;", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Landroid/content/Context;Lru/mail/auth/AccountManagerWrapper;Lru/mail/logic/content/DataManager;Lru/mail/kit/auth/session/provider/web/WebSessionCookieProvider;Lru/mail/kit/auth/analytics/AuthAnalytics;Lkotlinx/coroutines/CoroutineScope;)V", "commandExecutor", "Lru/mail/portal/kit/auth/AuthCommandExecutor;", "masterTokenRefreshMutex", "Lkotlinx/coroutines/sync/Mutex;", "authFailureListener", "Lru/mail/kit/auth/AuthProvider$AuthFailureListener;", "getAuthFailureListener", "()Lru/mail/kit/auth/AuthProvider$AuthFailureListener;", "setAuthFailureListener", "(Lru/mail/kit/auth/AuthProvider$AuthFailureListener;)V", "getAccessTokenAuthInfo", "", "account", "Lru/mail/kit/auth/account/HostAccountInfo;", "clientId", "", "callback", "Lru/mail/kit/auth/AuthProvider$AuthCallback;", "Lru/mail/kit/auth/info/AccessTokenAuthInfo;", "shouldUpdateAuthInfo", "", "authInfo", "expiresIn", "", "(Ljava/lang/String;Ljava/lang/Long;)Z", "updateAccessTokenAuthInfo", "source", "(Lru/mail/kit/auth/account/HostAccountInfo;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTokenForAccount", "androidAccount", "Landroid/accounts/Account;", "(Landroid/accounts/Account;Ljava/lang/String;Lru/mail/kit/auth/account/HostAccountInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "login", "app", "tokenExchangeParams", "Lru/mail/logic/auth/TokenExchangeParams;", "(Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lru/mail/logic/auth/TokenExchangeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAccessTokenFailureAnalytics", "sendBadRefreshTokenAnalytics", "requestRefreshAndAccessTokens", "afterReauthorize", "(Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWebSessionCookieProvider", "findAccountInAccountManager", "requestAccessToken", "(Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;Lru/mail/logic/auth/TokenExchangeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveSubjectTokenOrNull", "resolveSubjectTokenFallback", "params", "handleExpiredMasterToken", "fallbackToConvertOrNull", HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON, "Lru/mail/kit/auth/info/AuthErrorReason;", "(Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;Lru/mail/logic/auth/TokenExchangeParams;Lru/mail/kit/auth/info/AuthErrorReason;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performTokenExchangeWithFallback", "currentAccessToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;Lru/mail/logic/auth/TokenExchangeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryRefreshByToken", "(Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performTokenExchange", "subjectToken", "exchangeClientId", "originalClientId", "clientSecret", "(Ljava/lang/String;Ljava/lang/String;Landroid/accounts/Account;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getData", "key", "setData", "value", "isImap", "isAccountValid", "logError", "message", OkListenerKt.KEY_EXCEPTION, "", "isMasterTokenExpired", "expiresInMillis", "isMasterTokenExpired$mails_release", "(Ljava/lang/Long;Landroid/accounts/Account;)Z", "forceRefreshMasterToken", "(Landroid/accounts/Account;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalAuthProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalAuthProvider.kt\nru/mail/portal/kit/auth/PortalAuthProvider\n+ 2 AuthCommandExecutor.kt\nru/mail/portal/kit/auth/AuthCommandExecutor\n+ 3 Results.kt\nru/mail/kit/result/tools/Result\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,697:1\n21#2,5:698\n27#2,5:705\n34#2:711\n21#2,5:712\n27#2,5:719\n34#2:725\n21#2,5:727\n27#2,5:734\n34#2:740\n21#2,5:741\n27#2,5:748\n34#2:754\n45#3,2:703\n47#3:710\n45#3,2:717\n47#3:724\n45#3,2:732\n47#3:739\n45#3,2:746\n47#3:753\n1#4:726\n116#5,11:755\n*S KotlinDebug\n*F\n+ 1 PortalAuthProvider.kt\nru/mail/portal/kit/auth/PortalAuthProvider\n*L\n188#1:698,5\n188#1:705,5\n188#1:711\n276#1:712,5\n276#1:719,5\n276#1:725\n536#1:727,5\n536#1:734,5\n536#1:740\n588#1:741,5\n588#1:748,5\n588#1:754\n188#1:703,2\n188#1:710\n276#1:717,2\n276#1:724\n536#1:732,2\n536#1:739\n588#1:746,2\n588#1:753\n657#1:755,11\n*E\n"})
public final class PortalAuthProvider implements AuthProvider {

    @NotNull
    private static final String ACCESS_TOKEN_TYPE = "urn:ietf:params:oauth:token-type:access_token";

    @NotNull
    private static final String CONVERT_GRANT_TYPE = "convert";
    private static final int EXPIRE_MARGIN_MLS = 5000;
    private static final int TOKEN_EXCHANGE_EXPIRE_MARGIN_MLS = 300000;

    @NotNull
    private static final String TOKEN_EXCHANGE_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:token-exchange";

    @NotNull
    private final AccountManagerWrapper accountManager;

    @NotNull
    private final AuthAnalytics authAnalytics;

    @Nullable
    private volatile AuthProvider.AuthFailureListener authFailureListener;

    @NotNull
    private final AuthCommandExecutor commandExecutor;

    @NotNull
    private final Context context;

    @NotNull
    private final DataManager dataManager;

    @NotNull
    private final Mutex masterTokenRefreshMutex;

    @NotNull
    private final CoroutineScope scope;

    @NotNull
    private final WebSessionCookieProvider sessionCookieProvider;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PortalAuthProvider");

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$forceRefreshMasterToken$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {703, 669}, m = "forceRefreshMasterToken", n = {"account", "clientId", "$this$withLock_u24default$iv", "$i$f$withLock", "account", "clientId", "$this$withLock_u24default$iv", "currentToken", "currentExpiry", "$i$f$withLock", "$i$a$-withLock$default-PortalAuthProvider$forceRefreshMasterToken$2"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.forceRefreshMasterToken(null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1", f = "PortalAuthProvider.kt", i = {1}, l = {78, 79}, m = "invokeSuspend", n = {"authInfo"}, s = {"L$0"}, v = 1)
    static final class C23791 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ HostAccountInfo $account;
        final /* synthetic */ AuthProvider.AuthCallback<AccessTokenAuthInfo> $callback;
        final /* synthetic */ String $clientId;
        Object L$0;
        int label;

        /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1$1", f = "PortalAuthProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C04281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ HostAccountInfo $account;
            final /* synthetic */ AccessTokenAuthInfo $authInfo;
            final /* synthetic */ AuthProvider.AuthCallback<AccessTokenAuthInfo> $callback;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C04281(AccessTokenAuthInfo accessTokenAuthInfo, AuthProvider.AuthCallback<AccessTokenAuthInfo> authCallback, HostAccountInfo hostAccountInfo, Continuation<? super C04281> continuation) {
                super(2, continuation);
                this.$authInfo = accessTokenAuthInfo;
                this.$callback = authCallback;
                this.$account = hostAccountInfo;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C04281(this.$authInfo, this.$callback, this.$account, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                AccessTokenAuthInfo accessTokenAuthInfo = this.$authInfo;
                if (accessTokenAuthInfo != null) {
                    this.$callback.onSuccess(accessTokenAuthInfo, this.$account.getLogin());
                } else {
                    this.$callback.onError();
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C04281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C23791(HostAccountInfo hostAccountInfo, String str, AuthProvider.AuthCallback<AccessTokenAuthInfo> authCallback, Continuation<? super C23791> continuation) {
            super(2, continuation);
            this.$account = hostAccountInfo;
            this.$clientId = str;
            this.$callback = authCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PortalAuthProvider.this.new C23791(this.$account, this.$clientId, this.$callback, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
        
            if (kotlinx.coroutines.BuildersKt.withContext(r1, r3, r7) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                java.lang.Object r0 = r7.L$0
                ru.mail.kit.auth.info.AccessTokenAuthInfo r0 = (ru.mail.kit.auth.info.AccessTokenAuthInfo) r0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L55
            L16:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1e:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L36
            L22:
                kotlin.ResultKt.throwOnFailure(r8)
                ru.mail.portal.kit.auth.PortalAuthProvider r8 = ru.mail.portal.kit.auth.PortalAuthProvider.this
                ru.mail.kit.auth.account.HostAccountInfo r1 = r7.$account
                java.lang.String r4 = r7.$clientId
                r7.label = r3
                java.lang.String r3 = "PortalAuthProvider"
                java.lang.Object r8 = r8.updateAccessTokenAuthInfo(r1, r4, r3, r7)
                if (r8 != r0) goto L36
                goto L54
            L36:
                ru.mail.kit.auth.info.AccessTokenAuthInfo r8 = (ru.mail.kit.auth.info.AccessTokenAuthInfo) r8
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1$1 r3 = new ru.mail.portal.kit.auth.PortalAuthProvider$getAccessTokenAuthInfo$1$1
                ru.mail.kit.auth.AuthProvider$AuthCallback<ru.mail.kit.auth.info.AccessTokenAuthInfo> r4 = r7.$callback
                ru.mail.kit.auth.account.HostAccountInfo r5 = r7.$account
                r6 = 0
                r3.<init>(r8, r4, r5, r6)
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
                r7.L$0 = r8
                r7.label = r2
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r7)
                if (r8 != r0) goto L55
            L54:
                return r0
            L55:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.portal.kit.auth.PortalAuthProvider.C23791.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C23791) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$handleExpiredMasterToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {ApiInvocationException.ErrorCodes.UNAUTHORIZED_RESTRICTION, 460, 463}, m = "handleExpiredMasterToken", n = {"clientId", "login", "account", "params", "clientId", "login", "account", "params", "refreshedToken", "clientId", "login", "account", "params", "refreshedToken"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    static final class C23801 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C23801(Continuation<? super C23801> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.handleExpiredMasterToken(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$performTokenExchange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {FloatingViewGesturesHelper.HORIZONTAL_FLING_THRESHOLD}, m = "performTokenExchange", n = {"subjectToken", "login", "account", "exchangeClientId", "originalClientId", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "clientSecret", "app", "params", IMAPStore.ID_COMMAND, "this_$iv", "command$iv", "login$iv", "$i$f$execute$mails_release"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0"}, v = 1)
    static final class C23811 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C23811(Continuation<? super C23811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.performTokenExchange(null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$performTokenExchangeWithFallback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {493, 513, VKApiCodes.SUBCODE_PAID_REACTION_DAILY_LIMIT}, m = "performTokenExchangeWithFallback", n = {"currentAccessToken", "clientId", "login", "account", "tokenExchangeParams", "currentAccessToken", "clientId", "login", "account", "tokenExchangeParams", "exchangeResult", "currentAccessToken", "clientId", "login", "account", "tokenExchangeParams", "exchangeResult"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 1)
    static final class C23821 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C23821(Continuation<? super C23821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.performTokenExchangeWithFallback(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$refreshToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {FloatingViewGesturesHelper.HORIZONTAL_FLING_THRESHOLD, 213}, m = "refreshToken", n = {"androidAccount", "clientId", "login", "refreshToken", "app", "tokenExchangeParams", IMAPStore.ID_COMMAND, "this_$iv", "command$iv", "login$iv", "$i$f$execute$mails_release", "androidAccount", "clientId", "login", "refreshToken", "app", "tokenExchangeParams", IMAPStore.ID_COMMAND, "result", "error"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 1)
    static final class C23831 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C23831(Continuation<? super C23831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.refreshToken(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$requestRefreshAndAccessTokens$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {FloatingViewGesturesHelper.HORIZONTAL_FLING_THRESHOLD}, m = "requestRefreshAndAccessTokens", n = {"clientId", "login", "account", "params", "app", IMAPStore.ID_COMMAND, "this_$iv", "command$iv", "login$iv", "afterReauthorize", "$i$f$execute$mails_release"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0"}, v = 1)
    static final class C23841 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C23841(Continuation<? super C23841> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.requestRefreshAndAccessTokens(null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.portal.kit.auth.PortalAuthProvider$tryRefreshByToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.portal.kit.auth.PortalAuthProvider", f = "PortalAuthProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {FloatingViewGesturesHelper.HORIZONTAL_FLING_THRESHOLD}, m = "tryRefreshByToken", n = {"clientId", "login", "account", "app", "refreshToken", IMAPStore.ID_COMMAND, "this_$iv", "command$iv", "login$iv", "$i$f$execute$mails_release"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0"}, v = 1)
    static final class C23851 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        C23851(Continuation<? super C23851> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PortalAuthProvider.this.tryRefreshByToken(null, null, null, null, this);
        }
    }

    public PortalAuthProvider(@NotNull Context context, @NotNull AccountManagerWrapper accountManager, @NotNull DataManager dataManager, @NotNull WebSessionCookieProvider sessionCookieProvider, @NotNull AuthAnalytics authAnalytics, @NotNull CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(sessionCookieProvider, "sessionCookieProvider");
        Intrinsics.checkNotNullParameter(authAnalytics, "authAnalytics");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.context = context;
        this.accountManager = accountManager;
        this.dataManager = dataManager;
        this.sessionCookieProvider = sessionCookieProvider;
        this.authAnalytics = authAnalytics;
        this.scope = scope;
        Application applicationContext = dataManager.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.commandExecutor = new AuthCommandExecutor(applicationContext);
        this.masterTokenRefreshMutex = MutexKt.Mutex$default(false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fallbackToConvertOrNull(String str, String str2, Account account, TokenExchangeParams tokenExchangeParams, AuthErrorReason authErrorReason, Continuation<? super AccessTokenAuthInfo> continuation) {
        if (!tokenExchangeParams.getEnableFallback()) {
            return null;
        }
        LOG.w("Token exchange: falling back to convert, reason=" + authErrorReason);
        this.authAnalytics.onTokenExchangeFallbackToConvert(str, authErrorReason);
        return requestRefreshAndAccessTokens$default(this, str, str2, account, false, continuation, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:48:0x0127 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x0129 A[Catch: all -> 0x0049, Exception -> 0x004c, TRY_LEAVE, TryCatch #1 {Exception -> 0x004c, blocks: (B:13:0x0044, B:44:0x011b, B:46:0x011f, B:49:0x0129), top: B:61:0x0044 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0147 A[PHI: r3
      0x0147: PHI (r3v9 kotlinx.coroutines.sync.Mutex) = (r3v7 kotlinx.coroutines.sync.Mutex), (r3v11 kotlinx.coroutines.sync.Mutex) binds: [B:53:0x0131, B:48:0x0127] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object forceRefreshMasterToken(Account account, String str, Continuation<? super String> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Account account2;
        String str2;
        Mutex mutex;
        int i10;
        Mutex mutex2;
        String str3;
        String string;
        Bundle bundle;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i11 = anonymousClass1.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i11 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = anonymousClass1.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(objWithContext);
                Mutex mutex3 = this.masterTokenRefreshMutex;
                account2 = account;
                anonymousClass1.L$0 = account2;
                str2 = str;
                anonymousClass1.L$1 = str2;
                anonymousClass1.L$2 = mutex3;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                if (mutex3.lock(null, anonymousClass1) != coroutine_suspended) {
                    mutex = mutex3;
                    i10 = 0;
                }
                return coroutine_suspended;
            }
            if (i12 != 1) {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutex2 = (Mutex) anonymousClass1.L$2;
                str3 = (String) anonymousClass1.L$1;
                try {
                    try {
                        ResultKt.throwOnFailure(objWithContext);
                        bundle = (Bundle) objWithContext;
                        if (bundle != null || (string = bundle.getString("authtoken")) == null) {
                            if (bundle != null) {
                                string = bundle.getString("ru.mail.oauth2.access");
                            } else {
                                string = null;
                            }
                        }
                    } catch (Exception e10) {
                        e = e10;
                        LOG.e("Force refresh master token failed for clientId=" + str3, e);
                    }
                    mutex2.unlock(null);
                    return string;
                } catch (Throwable th2) {
                    th = th2;
                    mutex2.unlock(null);
                    throw th;
                }
            }
            int i13 = anonymousClass1.I$0;
            mutex = (Mutex) anonymousClass1.L$2;
            str2 = (String) anonymousClass1.L$1;
            Account account3 = (Account) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objWithContext);
            i10 = i13;
            account2 = account3;
            String userData = this.accountManager.getUserData(account2, AccountManagerWrapper.Key.MASTER_ACCESS_TOKEN_EXPIRE);
            Long longOrNull = userData != null ? StringsKt.toLongOrNull(userData) : null;
            if (!isMasterTokenExpired$mails_release(longOrNull, account2)) {
                LOG.i("Master token already refreshed by another request, clientId=" + str2);
                String strPeekAuthToken = this.accountManager.peekAuthToken(account2, "ru.mail.oauth2.access");
                mutex.unlock(null);
                return strPeekAuthToken;
            }
            LOG.i("Force refreshing master token, clientId=" + str2);
            String strPeekAuthToken2 = this.accountManager.peekAuthToken(account2, "ru.mail.oauth2.access");
            if (strPeekAuthToken2 != null) {
                AccountManagerWrapper accountManagerWrapper = this.accountManager;
                String type = account2.type;
                Intrinsics.checkNotNullExpressionValue(type, "type");
                accountManagerWrapper.invalidateAuthToken(type, strPeekAuthToken2);
            }
            try {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                PortalAuthProvider$forceRefreshMasterToken$2$bundle$1 portalAuthProvider$forceRefreshMasterToken$2$bundle$1 = new PortalAuthProvider$forceRefreshMasterToken$2$bundle$1(this, account2, null);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(account2);
                anonymousClass1.L$1 = str2;
                anonymousClass1.L$2 = mutex;
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(strPeekAuthToken2);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(longOrNull);
                anonymousClass1.I$0 = i10;
                anonymousClass1.I$1 = 0;
                anonymousClass1.label = 2;
                objWithContext = BuildersKt.withContext(io2, portalAuthProvider$forceRefreshMasterToken$2$bundle$1, anonymousClass1);
                if (objWithContext != coroutine_suspended) {
                    mutex2 = mutex;
                    str3 = str2;
                    bundle = (Bundle) objWithContext;
                    if (bundle != null) {
                        if (bundle != null) {
                            string = bundle.getString("ru.mail.oauth2.access");
                        } else {
                            string = null;
                        }
                    } else if (bundle != null) {
                        string = bundle.getString("ru.mail.oauth2.access");
                    } else {
                        string = null;
                    }
                    mutex2.unlock(null);
                    return string;
                }
                return coroutine_suspended;
            } catch (Exception e11) {
                e = e11;
                mutex2 = mutex;
                str3 = str2;
                LOG.e("Force refresh master token failed for clientId=" + str3, e);
            }
        } catch (Throwable th3) {
            th = th3;
            mutex2 = mutex;
            mutex2.unlock(null);
            throw th;
        }
    }

    private final String getData(Account account, String str, String str2) {
        return this.accountManager.getUserData(account, str + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object handleExpiredMasterToken(String str, String str2, Account account, TokenExchangeParams tokenExchangeParams, Continuation<? super AccessTokenAuthInfo> continuation) throws Throwable {
        C23801 c23801;
        if (continuation instanceof C23801) {
            c23801 = (C23801) continuation;
            int i10 = c23801.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23801.label = i10 - Integer.MIN_VALUE;
            } else {
                c23801 = new C23801(continuation);
            }
        } else {
            c23801 = new C23801(continuation);
        }
        C23801 c23802 = c23801;
        Object objForceRefreshMasterToken = c23802.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23802.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objForceRefreshMasterToken);
            this.authAnalytics.onTokenExchangeMasterTokenExpired(str);
            c23802.L$0 = str;
            c23802.L$1 = str2;
            c23802.L$2 = account;
            c23802.L$3 = tokenExchangeParams;
            c23802.label = 1;
            objForceRefreshMasterToken = forceRefreshMasterToken(account, str, c23802);
            if (objForceRefreshMasterToken != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i11 != 1) {
            if (i11 != 2 && i11 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objForceRefreshMasterToken);
            return objForceRefreshMasterToken;
        }
        tokenExchangeParams = (TokenExchangeParams) c23802.L$3;
        account = (Account) c23802.L$2;
        str2 = (String) c23802.L$1;
        str = (String) c23802.L$0;
        ResultKt.throwOnFailure(objForceRefreshMasterToken);
        String str3 = str2;
        Account account2 = account;
        TokenExchangeParams tokenExchangeParams2 = tokenExchangeParams;
        String str4 = (String) objForceRefreshMasterToken;
        if (str4 != null) {
            this.authAnalytics.onTokenExchangeMasterTokenRefreshSuccess(str);
            c23802.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c23802.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            c23802.L$2 = SpillingKt.nullOutSpilledVariable(account2);
            c23802.L$3 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams2);
            c23802.L$4 = SpillingKt.nullOutSpilledVariable(str4);
            c23802.label = 2;
            Object objPerformTokenExchangeWithFallback = performTokenExchangeWithFallback(str4, str, str3, account2, tokenExchangeParams2, c23802);
            if (objPerformTokenExchangeWithFallback != coroutine_suspended) {
                return objPerformTokenExchangeWithFallback;
            }
        } else {
            String str5 = str;
            this.authAnalytics.onTokenExchangeMasterTokenRefreshFailure(str5);
            AuthErrorReason authErrorReason = AuthErrorReason.TOKEN_EXCHANGE_MASTER_TOKEN_EXPIRED;
            c23802.L$0 = SpillingKt.nullOutSpilledVariable(str5);
            c23802.L$1 = SpillingKt.nullOutSpilledVariable(str3);
            c23802.L$2 = SpillingKt.nullOutSpilledVariable(account2);
            c23802.L$3 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams2);
            c23802.L$4 = SpillingKt.nullOutSpilledVariable(str4);
            c23802.label = 3;
            Object objFallbackToConvertOrNull = fallbackToConvertOrNull(str5, str3, account2, tokenExchangeParams2, authErrorReason, c23802);
            if (objFallbackToConvertOrNull != coroutine_suspended) {
                return objFallbackToConvertOrNull;
            }
        }
        return coroutine_suspended;
    }

    private final boolean isAccountValid(Account account) {
        if (account == null) {
            LOG.e("Account not found");
            return false;
        }
        if (!isImap(account)) {
            return true;
        }
        LOG.w("Account uses IMAP transport");
        return false;
    }

    private final boolean isImap(Account account) {
        return TextUtils.equals("IMAP", this.accountManager.getUserData(account, "transport_type"));
    }

    private final void logError(String message, Object exception) {
        if (exception instanceof Throwable) {
            LOG.e(message, (Throwable) exception);
        } else {
            LOG.e(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object performTokenExchange(String str, String str2, Account account, String str3, String str4, String str5, String str6, Continuation<? super AccessTokenAuthInfo> continuation) {
        C23811 c23811;
        Account account2;
        String str7;
        AuthCommandExecutor.AuthResult failure;
        if (continuation instanceof C23811) {
            c23811 = (C23811) continuation;
            int i10 = c23811.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23811.label = i10 - Integer.MIN_VALUE;
            } else {
                c23811 = new C23811(continuation);
            }
        } else {
            c23811 = new C23811(continuation);
        }
        Object objAwait = c23811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23811.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objAwait);
            LOG.i("Performing token exchange");
            String appByClientId = AuthUtilsKt.getAppByClientId(str4);
            GetAuthCodeByAccessTokenCommand.Params params = new GetAuthCodeByAccessTokenCommand.Params(null, str2, false, TOKEN_EXCHANGE_GRANT_TYPE, str3, str5, str, ACCESS_TOKEN_TYPE, ACCESS_TOKEN_TYPE, 4, null);
            Context context = this.context;
            TokenExchangeCommand tokenExchangeCommand = new TokenExchangeCommand(appByClientId, context, params, MigrateToPostUtils.is12181Enabled(context), str6, this.authAnalytics);
            AuthCommandExecutor authCommandExecutor = this.commandExecutor;
            ObservableFuture<Object> observableFutureExecute = new AuthorizedCommandImpl(authCommandExecutor.context, tokenExchangeCommand, str2, (FolderState) null).execute(ExecutorSelectors.defaultSelector());
            c23811.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c23811.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            account2 = account;
            c23811.L$2 = account2;
            c23811.L$3 = SpillingKt.nullOutSpilledVariable(str3);
            str7 = str4;
            c23811.L$4 = str7;
            c23811.L$5 = SpillingKt.nullOutSpilledVariable(str5);
            c23811.L$6 = SpillingKt.nullOutSpilledVariable(str6);
            c23811.L$7 = SpillingKt.nullOutSpilledVariable(appByClientId);
            c23811.L$8 = SpillingKt.nullOutSpilledVariable(params);
            c23811.L$9 = SpillingKt.nullOutSpilledVariable(tokenExchangeCommand);
            c23811.L$10 = SpillingKt.nullOutSpilledVariable(authCommandExecutor);
            c23811.L$11 = SpillingKt.nullOutSpilledVariable(tokenExchangeCommand);
            c23811.L$12 = SpillingKt.nullOutSpilledVariable(str2);
            c23811.I$0 = 0;
            c23811.label = 1;
            objAwait = observableFutureExecute.await(c23811);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str8 = (String) c23811.L$4;
            Account account3 = (Account) c23811.L$2;
            ResultKt.throwOnFailure(objAwait);
            str7 = str8;
            account2 = account3;
        }
        Result resultAsResult = ((ExecutionResult) objAwait).asResult();
        if (resultAsResult instanceof Result.Success) {
            Object result = ((Result.Success) resultAsResult).getResult();
            if (result instanceof CommandStatus.OK) {
                V data = ((CommandStatus.OK) result).getData();
                if (data == 0) {
                    throw new NullPointerException("null cannot be cast to non-null type ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.Result.ConvertResult");
                }
                failure = new AuthCommandExecutor.AuthResult.Success((GetAuthCodeByAccessTokenCommand.Result.ConvertResult) data);
            } else {
                failure = new AuthCommandExecutor.AuthResult.Failure(result);
            }
        } else {
            if (!(resultAsResult instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            failure = new AuthCommandExecutor.AuthResult.Failure((Throwable) ((Result.Failure) resultAsResult).getError());
        }
        if (failure instanceof AuthCommandExecutor.AuthResult.Success) {
            GetAuthCodeByAccessTokenCommand.Result.ConvertResult convertResult = (GetAuthCodeByAccessTokenCommand.Result.ConvertResult) ((AuthCommandExecutor.AuthResult.Success) failure).getData();
            if (convertResult.getRefreshToken().length() > 0) {
                setData(account2, str7, "refresh_token", convertResult.getRefreshToken());
            }
            LOG.i("Token exchange successful, saving refresh token for " + str7);
            return new AccessTokenAuthInfo(convertResult.getAccessToken(), convertResult.getExpiresIn().getTime());
        }
        if (!(failure instanceof AuthCommandExecutor.AuthResult.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        LOG.e("Token exchange failed: " + ((AuthCommandExecutor.AuthResult.Failure) failure).getError());
        this.authAnalytics.onTokenExchangeFailure(str7, AuthErrorReason.TOKEN_EXCHANGE_FAILURE);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:36:0x0131  */
    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    public final Object performTokenExchangeWithFallback(String str, String str2, String str3, Account account, TokenExchangeParams tokenExchangeParams, Continuation<? super AccessTokenAuthInfo> continuation) {
        C23821 c23821;
        TokenExchangeParams tokenExchangeParams2;
        String str4;
        Account account2;
        Object obj;
        String str5;
        String str6;
        String str7;
        Account account3;
        String str8;
        AccessTokenAuthInfo accessTokenAuthInfo;
        TokenExchangeParams tokenExchangeParams3;
        Object objTryRefreshByToken;
        if (continuation instanceof C23821) {
            c23821 = (C23821) continuation;
            int i10 = c23821.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23821.label = i10 - Integer.MIN_VALUE;
            } else {
                c23821 = new C23821(continuation);
            }
        } else {
            c23821 = new C23821(continuation);
        }
        C23821 c23822 = c23821;
        Object objRequestRefreshAndAccessTokens$default = c23822.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23822.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objRequestRefreshAndAccessTokens$default);
            this.authAnalytics.onTokenExchange(str2);
            String exchangeClientId = tokenExchangeParams.getExchangeClientId();
            String scope = tokenExchangeParams.getScope();
            String clientSecret = tokenExchangeParams.getClientSecret();
            c23822.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c23822.L$1 = str2;
            c23822.L$2 = str3;
            c23822.L$3 = account;
            tokenExchangeParams2 = tokenExchangeParams;
            c23822.L$4 = tokenExchangeParams2;
            c23822.label = 1;
            Object objPerformTokenExchange = performTokenExchange(str, str3, account, exchangeClientId, str2, scope, clientSecret, c23822);
            if (objPerformTokenExchange != coroutine_suspended) {
                c23822 = c23822;
                str4 = str2;
                account2 = account;
                obj = objPerformTokenExchange;
                str5 = str3;
            }
            c23822 = c23822;
            return coroutine_suspended;
        }
        if (i11 == 1) {
            TokenExchangeParams tokenExchangeParams4 = (TokenExchangeParams) c23822.L$4;
            Account account4 = (Account) c23822.L$3;
            String str9 = (String) c23822.L$2;
            str4 = (String) c23822.L$1;
            String str10 = (String) c23822.L$0;
            ResultKt.throwOnFailure(objRequestRefreshAndAccessTokens$default);
            tokenExchangeParams2 = tokenExchangeParams4;
            account2 = account4;
            str = str10;
            obj = objRequestRefreshAndAccessTokens$default;
            str5 = str9;
        } else {
            if (i11 != 2) {
                if (i11 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(objRequestRefreshAndAccessTokens$default);
                return objRequestRefreshAndAccessTokens$default;
            }
            accessTokenAuthInfo = (AccessTokenAuthInfo) c23822.L$5;
            tokenExchangeParams3 = (TokenExchangeParams) c23822.L$4;
            account3 = (Account) c23822.L$3;
            str7 = (String) c23822.L$2;
            str6 = (String) c23822.L$1;
            str8 = (String) c23822.L$0;
            ResultKt.throwOnFailure(objRequestRefreshAndAccessTokens$default);
        }
        objTryRefreshByToken = (AccessTokenAuthInfo) objRequestRefreshAndAccessTokens$default;
        if (objTryRefreshByToken == null) {
            String appByClientId = AuthUtilsKt.getAppByClientId(str6);
            c23822.L$0 = SpillingKt.nullOutSpilledVariable(str8);
            c23822.L$1 = SpillingKt.nullOutSpilledVariable(str6);
            c23822.L$2 = SpillingKt.nullOutSpilledVariable(str7);
            c23822.L$3 = SpillingKt.nullOutSpilledVariable(account3);
            c23822.L$4 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams3);
            c23822.L$5 = SpillingKt.nullOutSpilledVariable(accessTokenAuthInfo);
            c23822.label = 3;
            String str11 = str6;
            objTryRefreshByToken = tryRefreshByToken(str11, str7, account3, appByClientId, c23822);
            if (objTryRefreshByToken == coroutine_suspended) {
                c23822 = c23822;
                return coroutine_suspended;
            }
        }
        return objTryRefreshByToken;
        AccessTokenAuthInfo accessTokenAuthInfo2 = (AccessTokenAuthInfo) obj;
        if (accessTokenAuthInfo2 != null) {
            setData(account2, str4, "access_token", accessTokenAuthInfo2.getAccessToken());
            setData(account2, str4, AccountManagerWrapper.Key.ACCESS_TOKEN_EXPIRE, String.valueOf(accessTokenAuthInfo2.getExpiresIn()));
            this.authAnalytics.onTokenExchangeSuccess(str4);
            return accessTokenAuthInfo2;
        }
        if (!tokenExchangeParams2.getEnableFallback()) {
            return null;
        }
        LOG.w("Token exchange failed, falling back to convert with original clientId");
        this.authAnalytics.onTokenExchangeFallbackToConvert(str4, AuthErrorReason.TOKEN_EXCHANGE_FAILURE);
        c23822.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c23822.L$1 = str4;
        c23822.L$2 = str5;
        c23822.L$3 = account2;
        c23822.L$4 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams2);
        c23822.L$5 = SpillingKt.nullOutSpilledVariable(accessTokenAuthInfo2);
        c23822.label = 2;
        objRequestRefreshAndAccessTokens$default = requestRefreshAndAccessTokens$default(this, str4, str5, account2, false, c23822, 8, null);
        if (objRequestRefreshAndAccessTokens$default != coroutine_suspended) {
            String str12 = str5;
            str6 = str4;
            str7 = str12;
            account3 = account2;
            str8 = str;
            accessTokenAuthInfo = accessTokenAuthInfo2;
            tokenExchangeParams3 = tokenExchangeParams2;
            objTryRefreshByToken = (AccessTokenAuthInfo) objRequestRefreshAndAccessTokens$default;
            if (objTryRefreshByToken == null) {
                String appByClientId2 = AuthUtilsKt.getAppByClientId(str6);
                c23822.L$0 = SpillingKt.nullOutSpilledVariable(str8);
                c23822.L$1 = SpillingKt.nullOutSpilledVariable(str6);
                c23822.L$2 = SpillingKt.nullOutSpilledVariable(str7);
                c23822.L$3 = SpillingKt.nullOutSpilledVariable(account3);
                c23822.L$4 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams3);
                c23822.L$5 = SpillingKt.nullOutSpilledVariable(accessTokenAuthInfo);
                c23822.label = 3;
                String str13 = str6;
                objTryRefreshByToken = tryRefreshByToken(str13, str7, account3, appByClientId2, c23822);
                if (objTryRefreshByToken == coroutine_suspended) {
                }
            }
            return objTryRefreshByToken;
        }
        c23822 = c23822;
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object refreshToken(Account account, String str, String str2, String str3, String str4, TokenExchangeParams tokenExchangeParams, Continuation<? super AccessTokenAuthInfo> continuation) {
        C23831 c23831;
        Account account2;
        TokenExchangeParams tokenExchangeParams2;
        String str5;
        GetAccessTokenByRefreshToken getAccessTokenByRefreshToken;
        String str6;
        String str7;
        Object failure;
        String str8 = str4;
        if (continuation instanceof C23831) {
            c23831 = (C23831) continuation;
            int i10 = c23831.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23831.label = i10 - Integer.MIN_VALUE;
            } else {
                c23831 = new C23831(continuation);
            }
        } else {
            c23831 = new C23831(continuation);
        }
        Object obj = c23831.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23831.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            LOG.i("Updating access token by refresh token");
            this.authAnalytics.onGetClientTokenByClientCredentialsWithRefresh(str);
            Application applicationContext = this.dataManager.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            GetAccessTokenByRefreshToken getAccessTokenByRefreshToken2 = new GetAccessTokenByRefreshToken(str8, applicationContext, new GetAccessTokenByRefreshToken.Params(str2, str, str3), MigrateToPostUtils.is12181Enabled(this.context));
            AuthCommandExecutor authCommandExecutor = this.commandExecutor;
            ObservableFuture<Object> observableFutureExecute = new AuthorizedCommandImpl(authCommandExecutor.context, getAccessTokenByRefreshToken2, str2, (FolderState) null).execute(ExecutorSelectors.defaultSelector());
            account2 = account;
            c23831.L$0 = account2;
            c23831.L$1 = str;
            c23831.L$2 = str2;
            c23831.L$3 = SpillingKt.nullOutSpilledVariable(str3);
            c23831.L$4 = str8;
            tokenExchangeParams2 = tokenExchangeParams;
            c23831.L$5 = tokenExchangeParams2;
            c23831.L$6 = SpillingKt.nullOutSpilledVariable(getAccessTokenByRefreshToken2);
            c23831.L$7 = SpillingKt.nullOutSpilledVariable(authCommandExecutor);
            c23831.L$8 = SpillingKt.nullOutSpilledVariable(getAccessTokenByRefreshToken2);
            c23831.L$9 = SpillingKt.nullOutSpilledVariable(str2);
            c23831.I$0 = 0;
            c23831.label = 1;
            Object objAwait = observableFutureExecute.await(c23831);
            if (objAwait != coroutine_suspended) {
                str5 = str;
                getAccessTokenByRefreshToken = getAccessTokenByRefreshToken2;
                obj = objAwait;
                str6 = str3;
                str7 = str2;
            }
        }
        if (i11 != 1) {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        getAccessTokenByRefreshToken = (GetAccessTokenByRefreshToken) c23831.L$6;
        TokenExchangeParams tokenExchangeParams3 = (TokenExchangeParams) c23831.L$5;
        str8 = (String) c23831.L$4;
        str6 = (String) c23831.L$3;
        str7 = (String) c23831.L$2;
        str5 = (String) c23831.L$1;
        account2 = (Account) c23831.L$0;
        ResultKt.throwOnFailure(obj);
        tokenExchangeParams2 = tokenExchangeParams3;
        Result resultAsResult = ((ExecutionResult) obj).asResult();
        if (resultAsResult instanceof Result.Success) {
            Object result = ((Result.Success) resultAsResult).getResult();
            if (result instanceof CommandStatus.OK) {
                V data = ((CommandStatus.OK) result).getData();
                if (data == 0) {
                    throw new NullPointerException("null cannot be cast to non-null type ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.Result.RefreshResult");
                }
                failure = new AuthCommandExecutor.AuthResult.Success((GetAuthCodeByAccessTokenCommand.Result.RefreshResult) data);
            } else {
                failure = new AuthCommandExecutor.AuthResult.Failure(result);
            }
        } else {
            if (!(resultAsResult instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            failure = new AuthCommandExecutor.AuthResult.Failure((Throwable) ((Result.Failure) resultAsResult).getError());
        }
        if (failure instanceof AuthCommandExecutor.AuthResult.Success) {
            GetAuthCodeByAccessTokenCommand.Result.RefreshResult refreshResult = (GetAuthCodeByAccessTokenCommand.Result.RefreshResult) ((AuthCommandExecutor.AuthResult.Success) failure).getData();
            LOG.i("Access token update successful");
            String accessToken = refreshResult.getAccessToken();
            long time = refreshResult.getExpiresIn().getTime();
            setData(account2, str5, "access_token", accessToken);
            setData(account2, str5, AccountManagerWrapper.Key.ACCESS_TOKEN_EXPIRE, String.valueOf(time));
            this.authAnalytics.onGetClientTokenByClientCredentialsSuccess(str5, true);
            this.authAnalytics.onGetClientTokenByClientCredentialsSuccessByUpdate(str5);
            return new AccessTokenAuthInfo(accessToken, time);
        }
        if (!(failure instanceof AuthCommandExecutor.AuthResult.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        Object error = ((AuthCommandExecutor.AuthResult.Failure) failure).getError();
        if (!Intrinsics.areEqual(error, GetAccessTokenByRefreshToken.BadRefreshTokenError.INSTANCE)) {
            logError("Access token update error", error);
            sendAccessTokenFailureAnalytics(str5, str8);
            return null;
        }
        sendBadRefreshTokenAnalytics(str5, str8);
        c23831.L$0 = SpillingKt.nullOutSpilledVariable(account2);
        c23831.L$1 = SpillingKt.nullOutSpilledVariable(str5);
        c23831.L$2 = SpillingKt.nullOutSpilledVariable(str7);
        c23831.L$3 = SpillingKt.nullOutSpilledVariable(str6);
        c23831.L$4 = SpillingKt.nullOutSpilledVariable(str8);
        c23831.L$5 = SpillingKt.nullOutSpilledVariable(tokenExchangeParams2);
        c23831.L$6 = SpillingKt.nullOutSpilledVariable(getAccessTokenByRefreshToken);
        c23831.L$7 = SpillingKt.nullOutSpilledVariable(failure);
        c23831.L$8 = SpillingKt.nullOutSpilledVariable(error);
        c23831.L$9 = null;
        c23831.label = 2;
        Object objRequestAccessToken = requestAccessToken(str5, str7, account2, tokenExchangeParams2, c23831);
        return objRequestAccessToken == coroutine_suspended ? coroutine_suspended : objRequestAccessToken;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object refreshTokenForAccount(Account account, String str, HostAccountInfo hostAccountInfo, Continuation<? super AccessTokenAuthInfo> continuation) {
        TokenExchangeParams tokenExchangeParamsResolveTokenExchangeParams = SuperAppKitIds.INSTANCE.resolveTokenExchangeParams(this.context, str);
        if (tokenExchangeParamsResolveTokenExchangeParams.getShouldPerform()) {
            LOG.i("Token exchange enabled, skipping refresh, performing exchange");
            return requestAccessToken(str, hostAccountInfo.getLogin(), account, tokenExchangeParamsResolveTokenExchangeParams, continuation);
        }
        String data = getData(account, str, "refresh_token");
        String appByClientId = AuthUtilsKt.getAppByClientId(str);
        if (data != null && data.length() > 0) {
            return refreshToken(account, str, hostAccountInfo.getLogin(), data, appByClientId, tokenExchangeParamsResolveTokenExchangeParams, continuation);
        }
        LOG.i("No cached refresh token, request update");
        return requestAccessToken(str, hostAccountInfo.getLogin(), account, tokenExchangeParamsResolveTokenExchangeParams, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object requestAccessToken(String str, String str2, Account account, TokenExchangeParams tokenExchangeParams, Continuation<? super AccessTokenAuthInfo> continuation) {
        if (tokenExchangeParams == null) {
            tokenExchangeParams = SuperAppKitIds.INSTANCE.resolveTokenExchangeParams(this.context, str);
        }
        TokenExchangeParams tokenExchangeParams2 = tokenExchangeParams;
        if (tokenExchangeParams2.getShouldPerform()) {
            String strResolveSubjectTokenOrNull = resolveSubjectTokenOrNull(str, account);
            return strResolveSubjectTokenOrNull == null ? resolveSubjectTokenFallback(str, str2, account, tokenExchangeParams2, continuation) : performTokenExchangeWithFallback(strResolveSubjectTokenOrNull, str, str2, account, tokenExchangeParams2, continuation);
        }
        this.authAnalytics.onTokenExchangeDisabled(str);
        return requestRefreshAndAccessTokens$default(this, str, str2, account, false, continuation, 8, null);
    }

    static /* synthetic */ Object requestAccessToken$default(PortalAuthProvider portalAuthProvider, String str, String str2, Account account, TokenExchangeParams tokenExchangeParams, Continuation continuation, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            tokenExchangeParams = null;
        }
        return portalAuthProvider.requestAccessToken(str, str2, account, tokenExchangeParams, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object requestRefreshAndAccessTokens(String str, String str2, Account account, boolean z10, Continuation<? super AccessTokenAuthInfo> continuation) {
        C23841 c23841;
        final String str3;
        String str4;
        final String str5;
        boolean z11;
        Object obj;
        final Account account2;
        AuthCommandExecutor.AuthResult failure;
        if (continuation instanceof C23841) {
            c23841 = (C23841) continuation;
            int i10 = c23841.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23841.label = i10 - Integer.MIN_VALUE;
            } else {
                c23841 = new C23841(continuation);
            }
        } else {
            c23841 = new C23841(continuation);
        }
        C23841 c23842 = c23841;
        Object obj2 = c23842.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23842.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj2);
            str3 = str;
            str4 = null;
            str5 = str2;
            GetAuthCodeByAccessTokenCommand.Params params = new GetAuthCodeByAccessTokenCommand.Params(str3, str5, false, CONVERT_GRANT_TYPE, MailSdk.INSTANCE.getClientId(), null, null, null, null, 484, null);
            String appByClientId = AuthUtilsKt.getAppByClientId(str3);
            Context context = this.context;
            GetConvertAuthCodeByAccessTokenCommand getConvertAuthCodeByAccessTokenCommand = new GetConvertAuthCodeByAccessTokenCommand(appByClientId, context, params, MigrateToPostUtils.is12181Enabled(context));
            this.authAnalytics.onRefreshAndAccessTokens(str3);
            AuthCommandExecutor authCommandExecutor = this.commandExecutor;
            ObservableFuture<Object> observableFutureExecute = new AuthorizedCommandImpl(authCommandExecutor.context, getConvertAuthCodeByAccessTokenCommand, str5, (FolderState) null).execute(ExecutorSelectors.defaultSelector());
            c23842.L$0 = str3;
            c23842.L$1 = str5;
            c23842.L$2 = account;
            c23842.L$3 = SpillingKt.nullOutSpilledVariable(params);
            c23842.L$4 = SpillingKt.nullOutSpilledVariable(appByClientId);
            c23842.L$5 = SpillingKt.nullOutSpilledVariable(getConvertAuthCodeByAccessTokenCommand);
            c23842.L$6 = SpillingKt.nullOutSpilledVariable(authCommandExecutor);
            c23842.L$7 = SpillingKt.nullOutSpilledVariable(getConvertAuthCodeByAccessTokenCommand);
            c23842.L$8 = SpillingKt.nullOutSpilledVariable(str5);
            z11 = z10;
            c23842.Z$0 = z11;
            c23842.I$0 = 0;
            c23842.label = 1;
            Object objAwait = observableFutureExecute.await(c23842);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objAwait;
            account2 = account;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z12 = c23842.Z$0;
            account2 = (Account) c23842.L$2;
            String str6 = (String) c23842.L$1;
            String str7 = (String) c23842.L$0;
            ResultKt.throwOnFailure(obj2);
            str4 = null;
            str5 = str6;
            obj = obj2;
            z11 = z12;
            str3 = str7;
        }
        Result resultAsResult = ((ExecutionResult) obj).asResult();
        if (resultAsResult instanceof Result.Success) {
            Object result = ((Result.Success) resultAsResult).getResult();
            if (result instanceof CommandStatus.OK) {
                V data = ((CommandStatus.OK) result).getData();
                if (data == 0) {
                    throw new NullPointerException("null cannot be cast to non-null type ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.Result.ConvertResult");
                }
                failure = new AuthCommandExecutor.AuthResult.Success((GetAuthCodeByAccessTokenCommand.Result.ConvertResult) data);
            } else {
                failure = new AuthCommandExecutor.AuthResult.Failure(result);
            }
        } else {
            if (!(resultAsResult instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            failure = new AuthCommandExecutor.AuthResult.Failure((Throwable) ((Result.Failure) resultAsResult).getError());
        }
        if (failure instanceof AuthCommandExecutor.AuthResult.Success) {
            GetAuthCodeByAccessTokenCommand.Result.ConvertResult convertResult = (GetAuthCodeByAccessTokenCommand.Result.ConvertResult) ((AuthCommandExecutor.AuthResult.Success) failure).getData();
            LOG.i("Access and refresh tokens update successful");
            String accessToken = convertResult.getAccessToken();
            long time = convertResult.getExpiresIn().getTime();
            setData(account2, str3, "access_token", accessToken);
            setData(account2, str3, AccountManagerWrapper.Key.ACCESS_TOKEN_EXPIRE, String.valueOf(time));
            setData(account2, str3, "refresh_token", convertResult.getRefreshToken());
            this.authAnalytics.onRefreshAndAccessTokensSuccess(str3);
            return new AccessTokenAuthInfo(accessToken, time);
        }
        if (!(failure instanceof AuthCommandExecutor.AuthResult.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        Object error = ((AuthCommandExecutor.AuthResult.Failure) failure).getError();
        logError("Access and refresh tokens update error", error);
        Log log = LOG;
        log.w("Refresh failure: statusType=" + (error != null ? error.getClass().getSimpleName() : str4) + ", clientId=" + str3 + ", afterReauthorize=" + z11);
        if (!(error instanceof NetworkCommandStatus.NO_AUTH)) {
            log.w("Error is not connected with invalid token, aborting.");
            AuthAnalytics authAnalytics = this.authAnalytics;
            AuthErrorReason authErrorReason = AuthErrorReason.FAILURE_AUTH_REQUEST_TO_REFRESH_TOKEN;
            authAnalytics.onRefreshAndAccessTokensFailure(str3, authErrorReason);
            this.authAnalytics.onRefreshAndAccessTokensFailureByApp(AuthUtilsKt.getAppByClientId(str3), authErrorReason);
            return str4;
        }
        if (z11) {
            log.w("Unable to refresh tokens even after reauthorize, giving up.");
            AuthAnalytics authAnalytics2 = this.authAnalytics;
            AuthErrorReason authErrorReason2 = AuthErrorReason.FAILURE_REFRESH_TOKEN;
            authAnalytics2.onRefreshAndAccessTokensFailure(str3, authErrorReason2);
            this.authAnalytics.onRefreshAndAccessTokensFailureByApp(AuthUtilsKt.getAppByClientId(str3), authErrorReason2);
            return str4;
        }
        AuthProvider.AuthFailureListener authFailureListener = this.authFailureListener;
        if (authFailureListener != null) {
            log.i("Trying to reauthorize user");
            this.authAnalytics.onAuthAccessDenied(str3);
            authFailureListener.onAuthAccessDenied(str5, new Function0() { // from class: ru.mail.portal.kit.auth.f
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PortalAuthProvider.requestRefreshAndAccessTokens$lambda$0(this.f96250a, str3, str5, account2);
                }
            }, new Function0() { // from class: ru.mail.portal.kit.auth.g
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PortalAuthProvider.requestRefreshAndAccessTokens$lambda$1(this.f96254a, str3);
                }
            });
            return str4;
        }
        log.w("Auth failure listener is null, aborting.");
        AuthAnalytics authAnalytics3 = this.authAnalytics;
        AuthErrorReason authErrorReason3 = AuthErrorReason.AUTH_CALLBACK_ERRORS_NULL;
        authAnalytics3.onRefreshAndAccessTokensFailure(str3, authErrorReason3);
        this.authAnalytics.onRefreshAndAccessTokensFailureByApp(AuthUtilsKt.getAppByClientId(str3), authErrorReason3);
        return str4;
    }

    static /* synthetic */ Object requestRefreshAndAccessTokens$default(PortalAuthProvider portalAuthProvider, String str, String str2, Account account, boolean z10, Continuation continuation, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z10 = false;
        }
        return portalAuthProvider.requestRefreshAndAccessTokens(str, str2, account, z10, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestRefreshAndAccessTokens$lambda$0(PortalAuthProvider portalAuthProvider, String str, String str2, Account account) {
        LOG.i("On user successfully reauthorized! Refreshing tokens...");
        BuildersKt__Builders_commonKt.launch$default(portalAuthProvider.scope, Dispatchers.getIO(), null, new PortalAuthProvider$requestRefreshAndAccessTokens$2$1(portalAuthProvider, str, str2, account, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestRefreshAndAccessTokens$lambda$1(PortalAuthProvider portalAuthProvider, String str) {
        LOG.w("Reauthorize, giving up");
        AuthAnalytics authAnalytics = portalAuthProvider.authAnalytics;
        AuthErrorReason authErrorReason = AuthErrorReason.FAILURE_REAUTHORIZE_USER;
        authAnalytics.onRefreshAndAccessTokensFailure(str, authErrorReason);
        portalAuthProvider.authAnalytics.onRefreshAndAccessTokensFailureByApp(AuthUtilsKt.getAppByClientId(str), authErrorReason);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveSubjectTokenFallback(String str, String str2, Account account, TokenExchangeParams tokenExchangeParams, Continuation<? super AccessTokenAuthInfo> continuation) {
        return this.accountManager.peekAuthToken(account, "ru.mail.oauth2.access") != null ? handleExpiredMasterToken(str, str2, account, tokenExchangeParams, continuation) : fallbackToConvertOrNull(str, str2, account, tokenExchangeParams, AuthErrorReason.TOKEN_EXCHANGE_NO_ACCESS_TOKEN, continuation);
    }

    private final String resolveSubjectTokenOrNull(String clientId, Account account) {
        String strPeekAuthToken = this.accountManager.peekAuthToken(account, "ru.mail.oauth2.access");
        if (strPeekAuthToken == null) {
            LOG.w("Token exchange enabled but no access token found, clientId=" + clientId);
            return null;
        }
        String userData = this.accountManager.getUserData(account, AccountManagerWrapper.Key.MASTER_ACCESS_TOKEN_EXPIRE);
        if (!isMasterTokenExpired$mails_release(userData != null ? StringsKt.toLongOrNull(userData) : null, account)) {
            return strPeekAuthToken;
        }
        LOG.w("Master token expired or near expiry for token exchange with clientId=" + clientId);
        return null;
    }

    private final void sendAccessTokenFailureAnalytics(String clientId, String app) {
        AuthAnalytics authAnalytics = this.authAnalytics;
        AuthErrorReason authErrorReason = AuthErrorReason.FAILURE_ACCESS_TOKEN;
        authAnalytics.onGetClientTokenByClientCredentialsFailure(clientId, true, authErrorReason);
        this.authAnalytics.onGetClientTokenByClientCredentialsFailureByApp(app, authErrorReason);
    }

    private final void sendBadRefreshTokenAnalytics(String clientId, String app) {
        LOG.i("Bad refresh token, request update");
        AuthAnalytics authAnalytics = this.authAnalytics;
        AuthErrorReason authErrorReason = AuthErrorReason.BAD_REFRESH_TOKEN;
        authAnalytics.onGetClientTokenByClientCredentialsFailure(clientId, true, authErrorReason);
        this.authAnalytics.onGetClientTokenByClientCredentialsFailureByApp(app, authErrorReason);
    }

    private final void setData(Account account, String str, String str2, String str3) {
        this.accountManager.setUserData(account, str + str2, str3);
    }

    private final boolean shouldUpdateAuthInfo(String authInfo, Long expiresIn) {
        return authInfo == null || expiresIn == null || System.currentTimeMillis() > expiresIn.longValue() - ((long) 5000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object tryRefreshByToken(String str, String str2, Account account, String str3, Continuation<? super AccessTokenAuthInfo> continuation) {
        C23851 c23851;
        AuthCommandExecutor.AuthResult failure;
        if (continuation instanceof C23851) {
            c23851 = (C23851) continuation;
            int i10 = c23851.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c23851.label = i10 - Integer.MIN_VALUE;
            } else {
                c23851 = new C23851(continuation);
            }
        } else {
            c23851 = new C23851(continuation);
        }
        Object objAwait = c23851.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c23851.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objAwait);
            String data = getData(account, str, "refresh_token");
            if (data == null || data.length() == 0) {
                return null;
            }
            LOG.i("Convert failed, trying refresh token with original clientId");
            Application applicationContext = this.dataManager.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            GetAccessTokenByRefreshToken getAccessTokenByRefreshToken = new GetAccessTokenByRefreshToken(str3, applicationContext, new GetAccessTokenByRefreshToken.Params(str2, str, data), MigrateToPostUtils.is12181Enabled(this.context));
            AuthCommandExecutor authCommandExecutor = this.commandExecutor;
            ObservableFuture<Object> observableFutureExecute = new AuthorizedCommandImpl(authCommandExecutor.context, getAccessTokenByRefreshToken, str2, (FolderState) null).execute(ExecutorSelectors.defaultSelector());
            c23851.L$0 = str;
            c23851.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c23851.L$2 = account;
            c23851.L$3 = SpillingKt.nullOutSpilledVariable(str3);
            c23851.L$4 = SpillingKt.nullOutSpilledVariable(data);
            c23851.L$5 = SpillingKt.nullOutSpilledVariable(getAccessTokenByRefreshToken);
            c23851.L$6 = SpillingKt.nullOutSpilledVariable(authCommandExecutor);
            c23851.L$7 = SpillingKt.nullOutSpilledVariable(getAccessTokenByRefreshToken);
            c23851.L$8 = SpillingKt.nullOutSpilledVariable(str2);
            c23851.I$0 = 0;
            c23851.label = 1;
            objAwait = observableFutureExecute.await(c23851);
            if (objAwait == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            account = (Account) c23851.L$2;
            str = (String) c23851.L$0;
            ResultKt.throwOnFailure(objAwait);
        }
        Result resultAsResult = ((ExecutionResult) objAwait).asResult();
        if (resultAsResult instanceof Result.Success) {
            Object result = ((Result.Success) resultAsResult).getResult();
            if (result instanceof CommandStatus.OK) {
                V data2 = ((CommandStatus.OK) result).getData();
                if (data2 == 0) {
                    throw new NullPointerException("null cannot be cast to non-null type ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.Result.RefreshResult");
                }
                failure = new AuthCommandExecutor.AuthResult.Success((GetAuthCodeByAccessTokenCommand.Result.RefreshResult) data2);
            } else {
                failure = new AuthCommandExecutor.AuthResult.Failure(result);
            }
        } else {
            if (!(resultAsResult instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            failure = new AuthCommandExecutor.AuthResult.Failure((Throwable) ((Result.Failure) resultAsResult).getError());
        }
        if (!(failure instanceof AuthCommandExecutor.AuthResult.Success)) {
            if (!(failure instanceof AuthCommandExecutor.AuthResult.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            logError("Refresh token fallback failed", ((AuthCommandExecutor.AuthResult.Failure) failure).getError());
            return null;
        }
        GetAuthCodeByAccessTokenCommand.Result.RefreshResult refreshResult = (GetAuthCodeByAccessTokenCommand.Result.RefreshResult) ((AuthCommandExecutor.AuthResult.Success) failure).getData();
        setData(account, str, "access_token", refreshResult.getAccessToken());
        setData(account, str, AccountManagerWrapper.Key.ACCESS_TOKEN_EXPIRE, String.valueOf(refreshResult.getExpiresIn().getTime()));
        this.authAnalytics.onGetClientTokenByClientCredentialsSuccess(str, true);
        return new AccessTokenAuthInfo(refreshResult.getAccessToken(), refreshResult.getExpiresIn().getTime());
    }

    @Nullable
    public final Account findAccountInAccountManager(@NotNull HostAccountInfo account) {
        Intrinsics.checkNotNullParameter(account, "account");
        for (Account account2 : this.accountManager.getAppAccounts()) {
            if (Intrinsics.areEqual(account2.name, account.getLogin())) {
                return account2;
            }
        }
        return null;
    }

    @Override // ru.mail.kit.auth.AuthProvider
    public void getAccessTokenAuthInfo(@NotNull HostAccountInfo account, @NotNull String clientId, @NotNull AuthProvider.AuthCallback<AccessTokenAuthInfo> callback) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.authAnalytics.onGetClientTokenByClientCredentials(clientId);
        Log log = LOG;
        log.i("Getting access token request from client ID = " + clientId);
        Account accountFindAccountInAccountManager = findAccountInAccountManager(account);
        if (!isAccountValid(accountFindAccountInAccountManager) || accountFindAccountInAccountManager == null) {
            this.authAnalytics.onGetClientTokenByClientCredentialsFailure(clientId, false, AuthErrorReason.INVALID_ACCOUNT);
            this.authAnalytics.clientTokenByClientCredsInvalidAccountFailureV1(clientId);
            callback.onError();
            return;
        }
        String data = getData(accountFindAccountInAccountManager, clientId, "access_token");
        String data2 = getData(accountFindAccountInAccountManager, clientId, AccountManagerWrapper.Key.ACCESS_TOKEN_EXPIRE);
        Long longOrNull = data2 != null ? StringsKt.toLongOrNull(data2) : null;
        if (shouldUpdateAuthInfo(data, longOrNull)) {
            log.i("No cached token for account or token is expired, do request");
            BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new C23791(account, clientId, callback, null), 2, null);
            return;
        }
        log.i("Using access token from local cache");
        this.authAnalytics.onGetClientTokenByClientCredentialsSuccess(clientId, false);
        this.authAnalytics.onGetClientTokenByClientCredentialsSuccessFromCache(clientId);
        Intrinsics.checkNotNull(data);
        Intrinsics.checkNotNull(longOrNull);
        callback.onSuccess(new AccessTokenAuthInfo(data, longOrNull.longValue()), account.getLogin());
    }

    @Nullable
    public final AuthProvider.AuthFailureListener getAuthFailureListener() {
        return this.authFailureListener;
    }

    @Override // ru.mail.kit.auth.AuthProvider
    @NotNull
    /* JADX INFO: renamed from: getWebSessionCookieProvider, reason: from getter */
    public WebSessionCookieProvider getSessionCookieProvider() {
        return this.sessionCookieProvider;
    }

    public final boolean isMasterTokenExpired$mails_release(@Nullable Long expiresInMillis, @NotNull Account account) {
        Intrinsics.checkNotNullParameter(account, "account");
        if (expiresInMillis == null) {
            return this.accountManager.peekAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH) != null;
        }
        return System.currentTimeMillis() > expiresInMillis.longValue() - ((long) 300000);
    }

    public final void setAuthFailureListener(@Nullable AuthProvider.AuthFailureListener authFailureListener) {
        this.authFailureListener = authFailureListener;
    }

    @Override // ru.mail.kit.auth.AuthProvider
    @Nullable
    public Object updateAccessTokenAuthInfo(@NotNull HostAccountInfo hostAccountInfo, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super AccessTokenAuthInfo> continuation) {
        LOG.i("Access token update requested for client ID = " + str);
        Account accountFindAccountInAccountManager = findAccountInAccountManager(hostAccountInfo);
        if (accountFindAccountInAccountManager != null && isAccountValid(accountFindAccountInAccountManager)) {
            return refreshTokenForAccount(accountFindAccountInAccountManager, str, hostAccountInfo, continuation);
        }
        this.authAnalytics.onGetClientTokenByClientCredentialsFailure(str, true, AuthErrorReason.INVALID_ACCOUNT);
        this.authAnalytics.clientTokenByClientCredsInvalidAccountFailureV2(str);
        return null;
    }

    @Override // ru.mail.kit.auth.AuthProvider
    @TestOnly
    @Nullable
    public /* bridge */ AccessTokenAuthInfo updateAccessTokenAuthInfoBlocking(@NotNull HostAccountInfo hostAccountInfo, @NotNull String str, @NotNull String str2) {
        return super.updateAccessTokenAuthInfoBlocking(hostAccountInfo, str, str2);
    }
}
