package ru.mail.authorizationsdk.feature.secondfactor.presentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.compose.runtime.Stable;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.di.modules.feature.SecondStepWebClient;
import ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory;
import ru.mail.authorizationsdk.feature.common.webview.CommonWebViewClient;
import ru.mail.authorizationsdk.feature.common.webview.CommonWebViewRetryDelegate;
import ru.mail.authorizationsdk.feature.common.webview.LoadPageAgainState;
import ru.mail.authorizationsdk.feature.common.webview.WebViewDelegateState;
import ru.mail.authorizationsdk.feature.core.presentation.ExternalAuthRedirectPicker;
import ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics;
import ru.mail.authorizationsdk.feature.secondfactor.config.SecondStepConfig;
import ru.mail.authorizationsdk.feature.secondfactor.domain.SecondStepUseCase;
import ru.mail.authorizationsdk.navigation.DestBase;
import ru.mail.authorizationsdk.utils.extensions.MutableEventFlowKt;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.log.Logger;
import statusnavbars.StatusNavBarHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Stable
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 P2\u00020\u00012\u00020\u0002:\u0002OPBW\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010:\u001a\u00020;J\u000e\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020>J\"\u0010?\u001a\u00020;2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020;0AH\u0086@¢\u0006\u0002\u0010BJ\u0010\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020\"H\u0002J\u001a\u0010E\u001a\u00020;2\u0006\u0010=\u001a\u00020>2\b\u0010F\u001a\u0004\u0018\u00010\"H\u0002J\u0010\u0010G\u001a\u00020\"2\u0006\u0010H\u001a\u00020IH\u0002J\n\u0010J\u001a\u0004\u0018\u00010\"H\u0002J\u0006\u0010K\u001a\u00020;J\u0006\u0010L\u001a\u00020;J\f\u0010M\u001a\u00020N*\u000208H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010/\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020&05X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00106R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020805¢\u0006\b\n\u0000\u001a\u0004\b9\u00106¨\u0006Q"}, d2 = {"Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel;", "Landroidx/lifecycle/ViewModel;", "Lru/mail/authorizationsdk/feature/common/webview/LoadPageAgainState;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "viewModelDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "baseLogger", "Lru/mail/util/log/Logger;", "secondStepUseCase", "Lru/mail/authorizationsdk/feature/secondfactor/domain/SecondStepUseCase;", "analytics", "Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;", "config", "Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;", "statusNavBarHelper", "Lstatusnavbars/StatusNavBarHelper;", "stringResolver", "Lru/mail/android_utils/wrapper/Resources;", "webViewClient", "Lru/mail/authorizationsdk/feature/common/webview/CommonWebViewClient;", "<init>", "(Landroidx/lifecycle/SavedStateHandle;Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/util/log/Logger;Lru/mail/authorizationsdk/feature/secondfactor/domain/SecondStepUseCase;Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;Lstatusnavbars/StatusNavBarHelper;Lru/mail/android_utils/wrapper/Resources;Lru/mail/authorizationsdk/feature/common/webview/CommonWebViewClient;)V", "getAnalytics", "()Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;", "getConfig", "()Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;", "getStatusNavBarHelper", "()Lstatusnavbars/StatusNavBarHelper;", "getWebViewClient", "()Lru/mail/authorizationsdk/feature/common/webview/CommonWebViewClient;", "args", "Landroid/os/Bundle;", "rawUrl", "", "login", "secondStepCookieHeader", "isXmailMigration", "", "isNpcMode", "paramXmailFrom", "isFirstPageLoaded", "redirectPicker", "Lru/mail/authorizationsdk/feature/core/presentation/ExternalAuthRedirectPicker;", "log", "webViewRetryDelegate", "Lru/mail/authorizationsdk/feature/common/webview/CommonWebViewRetryDelegate;", "mainUrl", "getMainUrl", "()Ljava/lang/String;", "isRecaptchaMode", "()Z", "isNeedLoadPageAgain", "Lru/mail/march/viewmodel/MutableEventFlow;", "()Lru/mail/march/viewmodel/MutableEventFlow;", "resultFlow", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "getResultFlow", "onStart", "", "onWebViewReady", "context", "Landroid/content/Context;", "collectResult", "action", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toRecovery", "email", "setupCookie", "tsaCookie", "appendExtraParams", "uri", "Landroid/net/Uri;", "extractTsaCookieValue", "onBackClick", "onDestroy", "emit", "Lkotlinx/coroutines/Job;", "Factory", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSecondStepViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecondStepViewModel.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 6 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,254:1\n1#2:255\n739#3,9:256\n37#4,2:265\n13805#5:267\n13806#5:291\n106#6:268\n78#6,22:269\n*S KotlinDebug\n*F\n+ 1 SecondStepViewModel.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel\n*L\n208#1:256,9\n209#1:265,2\n210#1:267\n210#1:291\n211#1:268\n211#1:269,22\n*E\n"})
public final class SecondStepViewModel extends ViewModel implements LoadPageAgainState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String PARAM_LOGIN = "Login";

    @NotNull
    private static final String RECAPTCHA_PARAM = "captcha_type";

    @NotNull
    private static final String RECAPTCHA_VALUE = "recaptcha";

    @NotNull
    public static final String SECOND_FACTOR_COOKIE_HEADER = "SECOND_STEP_COOKIE_HEADER";

    @NotNull
    public static final String SECOND_FACTOR_IS_XMAIL_MIGRATION = "SECOND_STEP_IS_XMAIL_MIGRATION";

    @NotNull
    public static final String SECOND_FACTOR_LOGIN = "SECOND_STEP_LOGIN";

    @NotNull
    public static final String SECOND_FACTOR_URL = "SECOND_STEP_URL";

    @NotNull
    public static final String SECOND_FACTOR_XMAIL_FROM_PARAM = "SECOND_STEP_XMAIL_FROM_PARAM";

    @NotNull
    private static final String URL_PARAM_MP = "mp";

    @NotNull
    private static final String URL_PARAM_XMAIL_FROM = "from";

    @NotNull
    private final SecondFactorAnalytics analytics;

    @Nullable
    private final Bundle args;

    @NotNull
    private final SecondStepConfig config;
    private boolean isFirstPageLoaded;

    @NotNull
    private final MutableEventFlow<Boolean> isNeedLoadPageAgain;
    private boolean isNpcMode;
    private final boolean isRecaptchaMode;
    private final boolean isXmailMigration;

    @NotNull
    private final Logger log;

    @NotNull
    private final String login;

    @NotNull
    private final String mainUrl;

    @Nullable
    private final String paramXmailFrom;

    @NotNull
    private final String rawUrl;

    @NotNull
    private final ExternalAuthRedirectPicker redirectPicker;

    @NotNull
    private final MutableEventFlow<SecondStepResult> resultFlow;

    @NotNull
    private final String secondStepCookieHeader;

    @NotNull
    private final SecondStepUseCase secondStepUseCase;

    @NotNull
    private final StatusNavBarHelper statusNavBarHelper;

    @NotNull
    private final Resources stringResolver;

    @NotNull
    private final CoroutineDispatcher viewModelDispatcher;

    @NotNull
    private final CommonWebViewClient webViewClient;

    @NotNull
    private final CommonWebViewRetryDelegate webViewRetryDelegate;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/authorizationsdk/feature/common/webview/WebViewDelegateState;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$2", f = "SecondStepViewModel.kt", i = {0, 1, 1, 2}, l = {147, 158, 162}, m = "invokeSuspend", n = {"it", "it", "uri", "it"}, s = {"L$0", "L$0", "L$1", "L$0"}, v = 1)
    @SourceDebugExtension({"SMAP\nSecondStepViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecondStepViewModel.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel$2\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,254:1\n29#2:255\n*S KotlinDebug\n*F\n+ 1 SecondStepViewModel.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel$2\n*L\n151#1:255\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<WebViewDelegateState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = SecondStepViewModel.this.new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x006e, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x00dc, code lost:
        
            if (r3.emit(r2, r9) == r1) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00f9, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L35;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 272
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WebViewDelegateState webViewDelegateState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(webViewDelegateState, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel$Companion;", "", "<init>", "()V", "isRecaptchaMode", "", "url", "", "RECAPTCHA_PARAM", "RECAPTCHA_VALUE", "URL_PARAM_MP", "URL_PARAM_XMAIL_FROM", "PARAM_LOGIN", "SECOND_FACTOR_URL", "SECOND_FACTOR_LOGIN", "SECOND_FACTOR_COOKIE_HEADER", "SECOND_FACTOR_IS_XMAIL_MIGRATION", "SECOND_FACTOR_XMAIL_FROM_PARAM", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isRecaptchaMode(String url) {
            return StringsKt.equals$default(Uri.parse(url).getQueryParameter(SecondStepViewModel.RECAPTCHA_PARAM), SecondStepViewModel.RECAPTCHA_VALUE, false, 2, null);
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes15.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel$Factory;", "Lru/mail/authorizationsdk/di/viewmodel/CommonViewModelFactory;", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel;", "create", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @AssistedFactory
    public interface Factory extends CommonViewModelFactory<SecondStepViewModel> {
        @Override // ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory
        @NotNull
        SecondStepViewModel create(@NotNull SavedStateHandle savedStateHandle);
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$collectResult$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class C17932 extends FunctionReferenceImpl implements Function2<SecondStepResult, Continuation<? super Unit>, Object>, SuspendFunction {
        C17932(Object obj) {
            super(2, obj, Intrinsics.Kotlin.class, "suspendConversion0", "collectResult$suspendConversion0(Lkotlin/jvm/functions/Function1;Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SecondStepResult secondStepResult, Continuation<? super Unit> continuation) {
            return SecondStepViewModel.collectResult$suspendConversion0((Function1) this.receiver, secondStepResult, continuation);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$emit$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$emit$1", f = "SecondStepViewModel.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SecondStepResult $this_emit;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SecondStepResult secondStepResult, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_emit = secondStepResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecondStepViewModel.this.new AnonymousClass1(this.$this_emit, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                MutableEventFlow<SecondStepResult> resultFlow = SecondStepViewModel.this.getResultFlow();
                SecondStepResult secondStepResult = this.$this_emit;
                this.label = 1;
                if (resultFlow.emit(secondStepResult, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$onWebViewReady$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel$onWebViewReady$1", f = "SecondStepViewModel.kt", i = {1}, l = {175, 177}, m = "invokeSuspend", n = {"tsaCookie"}, s = {"L$0"}, v = 1)
    static final class C17941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C17941(Context context, Continuation<? super C17941> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SecondStepViewModel.this.new C17941(this.$context, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0059, code lost:
        
            if (r1.emit(r3, r5) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                java.lang.Object r0 = r5.L$0
                java.lang.String r0 = (java.lang.String) r0
                kotlin.ResultKt.throwOnFailure(r6)
                goto L5c
            L16:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1e:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L3a
            L22:
                kotlin.ResultKt.throwOnFailure(r6)
                ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel r6 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.this
                ru.mail.authorizationsdk.feature.secondfactor.domain.SecondStepUseCase r6 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.access$getSecondStepUseCase$p(r6)
                ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel r1 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.this
                java.lang.String r1 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.access$getLogin$p(r1)
                r5.label = r3
                java.lang.Object r6 = r6.getTsaCookie(r1, r5)
                if (r6 != r0) goto L3a
                goto L5b
            L3a:
                java.lang.String r6 = (java.lang.String) r6
                ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel r1 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.this
                android.content.Context r4 = r5.$context
                ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.access$setupCookie(r1, r4, r6)
                ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel r1 = ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.this
                ru.mail.march.viewmodel.MutableEventFlow r1 = r1.isNeedLoadPageAgain()
                java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
                r5.L$0 = r6
                r5.label = r2
                java.lang.Object r6 = r1.emit(r3, r5)
                if (r6 != r0) goto L5c
            L5b:
                return r0
            L5c:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel.C17941.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C17941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @AssistedInject
    public SecondStepViewModel(@Assisted @NotNull SavedStateHandle savedStateHandle, @ViewModelDispatcher @NotNull CoroutineDispatcher viewModelDispatcher, @NotNull Logger baseLogger, @NotNull SecondStepUseCase secondStepUseCase, @NotNull SecondFactorAnalytics analytics, @NotNull SecondStepConfig config, @NotNull StatusNavBarHelper statusNavBarHelper, @NotNull Resources stringResolver, @SecondStepWebClient @NotNull CommonWebViewClient webViewClient) {
        String string;
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        Intrinsics.checkNotNullParameter(viewModelDispatcher, "viewModelDispatcher");
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        Intrinsics.checkNotNullParameter(secondStepUseCase, "secondStepUseCase");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(statusNavBarHelper, "statusNavBarHelper");
        Intrinsics.checkNotNullParameter(stringResolver, "stringResolver");
        Intrinsics.checkNotNullParameter(webViewClient, "webViewClient");
        this.viewModelDispatcher = viewModelDispatcher;
        this.secondStepUseCase = secondStepUseCase;
        this.analytics = analytics;
        this.config = config;
        this.statusNavBarHelper = statusNavBarHelper;
        this.stringResolver = stringResolver;
        this.webViewClient = webViewClient;
        Bundle bundle = (Bundle) savedStateHandle.get(DestBase.PARAMS);
        this.args = bundle;
        if (bundle == null || (string = bundle.getString(SECOND_FACTOR_URL)) == null) {
            throw new IllegalArgumentException("no second step url!");
        }
        this.rawUrl = string;
        String string2 = bundle != null ? bundle.getString(SECOND_FACTOR_LOGIN) : null;
        this.login = string2 == null ? "" : string2;
        String string3 = bundle != null ? bundle.getString(SECOND_FACTOR_COOKIE_HEADER) : null;
        this.secondStepCookieHeader = string3 != null ? string3 : "";
        boolean z10 = bundle != null ? bundle.getBoolean(SECOND_FACTOR_IS_XMAIL_MIGRATION) : false;
        this.isXmailMigration = z10;
        this.paramXmailFrom = bundle != null ? bundle.getString(SECOND_FACTOR_XMAIL_FROM_PARAM) : null;
        this.redirectPicker = new ExternalAuthRedirectPicker();
        Logger loggerCreateLogger = baseLogger.createLogger("SecondStepViewModel");
        this.log = loggerCreateLogger;
        CommonWebViewRetryDelegate commonWebViewRetryDelegate = new CommonWebViewRetryDelegate(viewModelDispatcher, ViewModelKt.getViewModelScope(this), 0L, new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.r
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SecondStepViewModel.webViewRetryDelegate$lambda$0(this.f82783a);
            }
        }, 4, null);
        this.webViewRetryDelegate = commonWebViewRetryDelegate;
        if (z10) {
            Uri uri = Uri.parse(string);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            string = appendExtraParams(uri);
        }
        this.mainUrl = string;
        this.isRecaptchaMode = INSTANCE.isRecaptchaMode(string);
        this.isNeedLoadPageAgain = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, loggerCreateLogger, new Flow[0]);
        this.resultFlow = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, loggerCreateLogger, new Flow[0]);
        webViewClient.setRetryDelegate(commonWebViewRetryDelegate);
        webViewClient.setOnInternalResultObserver(new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.s
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SecondStepViewModel._init_$lambda$0(this.f82784a, (CommonWebViewClient.WebViewInternalResult) obj);
            }
        });
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(commonWebViewRetryDelegate.getState()), new AnonymousClass2(null)), ViewModelKt.getViewModelScope(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(SecondStepViewModel secondStepViewModel, CommonWebViewClient.WebViewInternalResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Logger.d$default(secondStepViewModel.log, "internal result = " + it, null, 2, null);
        if (Intrinsics.areEqual(it, CommonWebViewClient.WebViewInternalResult.Close.INSTANCE)) {
            secondStepViewModel.analytics.secondFactorWebViewClose(secondStepViewModel.isXmailMigration, secondStepViewModel.isNpcMode);
            secondStepViewModel.emit(new SecondStepResult.BackClick(secondStepViewModel.isXmailMigration));
        } else if (Intrinsics.areEqual(it, CommonWebViewClient.WebViewInternalResult.RedirectFail.INSTANCE)) {
            if (!secondStepViewModel.redirectPicker.shouldHandleFailure()) {
                return Unit.INSTANCE;
            }
            secondStepViewModel.analytics.secondFactorFail(secondStepViewModel.isXmailMigration, secondStepViewModel.isNpcMode);
            secondStepViewModel.emit(new SecondStepResult.Error(secondStepViewModel.stringResolver.getString(R.string.authenticator_error)));
        } else if (it instanceof CommonWebViewClient.WebViewInternalResult.RedirectRecovery) {
            secondStepViewModel.toRecovery(((CommonWebViewClient.WebViewInternalResult.RedirectRecovery) it).getEmail());
            Unit unit = Unit.INSTANCE;
        } else if (Intrinsics.areEqual(it, CommonWebViewClient.WebViewInternalResult.RedirectInternalError.INSTANCE)) {
            if (!secondStepViewModel.redirectPicker.shouldHandleFailure()) {
                return Unit.INSTANCE;
            }
            secondStepViewModel.analytics.secondFactorError(secondStepViewModel.isXmailMigration, secondStepViewModel.isNpcMode);
            secondStepViewModel.emit(new SecondStepResult.Error(secondStepViewModel.stringResolver.getString(R.string.reg_err_network_server_error)));
        } else if (it instanceof CommonWebViewClient.WebViewInternalResult.RedirectSuccess) {
            secondStepViewModel.redirectPicker.onSuccessRedirect();
            ExtensionsKt.launch$default(secondStepViewModel, secondStepViewModel.viewModelDispatcher, null, new SecondStepViewModel$1$1(secondStepViewModel, it, null), 2, null);
        } else if (Intrinsics.areEqual(it, CommonWebViewClient.WebViewInternalResult.SwitchToPassword.INSTANCE)) {
            secondStepViewModel.redirectPicker.onSuccessRedirect();
            secondStepViewModel.analytics.secondFactorSwitchToPass(secondStepViewModel.isXmailMigration, secondStepViewModel.isNpcMode);
            secondStepViewModel.emit(new SecondStepResult.SwitchToPassword(secondStepViewModel.rawUrl));
        } else {
            if (!Intrinsics.areEqual(it, CommonWebViewClient.WebViewInternalResult.GoToRecovery.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            secondStepViewModel.toRecovery("");
            Unit unit2 = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final String appendExtraParams(Uri uri) {
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.appendQueryParameter("mp", "android");
        String str = this.paramXmailFrom;
        if (str != null) {
            builderBuildUpon.appendQueryParameter("from", str);
        }
        String string = builderBuildUpon.build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object collectResult$suspendConversion0(Function1 function1, SecondStepResult secondStepResult, Continuation continuation) {
        function1.invoke(secondStepResult);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job emit(SecondStepResult secondStepResult) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.viewModelDispatcher, null, new AnonymousClass1(secondStepResult, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractTsaCookieValue() {
        List listEmptyList;
        String cookie = CookieManager.getInstance().getCookie(this.mainUrl);
        Intrinsics.checkNotNull(cookie);
        List<String> listSplit = new Regex(MailThreadRepresentation.PAYLOAD_DELIM_CHAR).split(cookie, 0);
        if (listSplit.isEmpty()) {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                listEmptyList = CollectionsKt.emptyList();
                break;
            }
            if (listIterator.previous().length() != 0) {
                listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                break;
            }
        }
        String strSubstring = null;
        for (Object obj : listEmptyList.toArray(new String[0])) {
            String str = (String) obj;
            int length = str.length() - 1;
            int i10 = 0;
            boolean z10 = false;
            while (i10 <= length) {
                boolean z11 = Intrinsics.compare((int) str.charAt(!z10 ? i10 : length), 32) <= 0;
                if (z10) {
                    if (!z11) {
                        break;
                    }
                    length--;
                } else if (z11) {
                    i10++;
                } else {
                    z10 = true;
                }
            }
            String string = str.subSequence(i10, length + 1).toString();
            if (StringsKt.startsWith$default(string, "tsa=", false, 2, (Object) null)) {
                Logger.d$default(this.log, string, null, 2, null);
                strSubstring = string.substring(4);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            }
        }
        return strSubstring;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupCookie(Context context, String tsaCookie) {
        CookieSyncManager cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(context);
        CookieManager.getInstance().setCookie(this.mainUrl, this.secondStepCookieHeader);
        if (tsaCookie != null) {
            CookieManager.getInstance().setCookie(this.mainUrl, "tsa=" + tsaCookie);
        }
        cookieSyncManagerCreateInstance.sync();
    }

    private final void toRecovery(String email) {
        this.analytics.secondFactorSwitchToRecovery();
        emit(new SecondStepResult.SwitchToRecovery(email));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit webViewRetryDelegate$lambda$0(SecondStepViewModel secondStepViewModel) {
        secondStepViewModel.analytics.secondFactorWebViewLoadingFailed();
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object collectResult(@NotNull Function1<? super SecondStepResult, Unit> function1, @NotNull Continuation<? super Unit> continuation) {
        Object objCollectLatestDebounced$default = MutableEventFlowKt.collectLatestDebounced$default(this.resultFlow, this.viewModelDispatcher, new C17932(function1), 0L, continuation, 4, null);
        return objCollectLatestDebounced$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollectLatestDebounced$default : Unit.INSTANCE;
    }

    @NotNull
    public final SecondFactorAnalytics getAnalytics() {
        return this.analytics;
    }

    @NotNull
    public final SecondStepConfig getConfig() {
        return this.config;
    }

    @NotNull
    public final String getMainUrl() {
        return this.mainUrl;
    }

    @NotNull
    public final MutableEventFlow<SecondStepResult> getResultFlow() {
        return this.resultFlow;
    }

    @NotNull
    public final StatusNavBarHelper getStatusNavBarHelper() {
        return this.statusNavBarHelper;
    }

    @NotNull
    public final CommonWebViewClient getWebViewClient() {
        return this.webViewClient;
    }

    /* JADX INFO: renamed from: isRecaptchaMode, reason: from getter */
    public final boolean getIsRecaptchaMode() {
        return this.isRecaptchaMode;
    }

    public final void onBackClick() {
        emit(new SecondStepResult.BackClick(this.isXmailMigration));
    }

    public final void onDestroy() {
        this.webViewRetryDelegate.detach();
    }

    public final void onStart() {
        this.redirectPicker.reset();
    }

    public final void onWebViewReady(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExtensionsKt.launch$default(this, this.viewModelDispatcher, null, new C17941(context, null), 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.common.webview.LoadPageAgainState
    @NotNull
    public MutableEventFlow<Boolean> isNeedLoadPageAgain() {
        return this.isNeedLoadPageAgain;
    }
}
