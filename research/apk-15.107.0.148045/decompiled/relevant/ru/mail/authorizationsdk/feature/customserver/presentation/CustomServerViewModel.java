package ru.mail.authorizationsdk.feature.customserver.presentation;

import android.os.Bundle;
import androidx.compose.runtime.Stable;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.vk.superapp.browser.ui.VkBrowserActivity;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.data.common.suggestions.LoginSuggestion;
import ru.mail.authorizationsdk.di.IsTest;
import ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory;
import ru.mail.authorizationsdk.domain.usecase.suggestions.DomainSuggestionsUseCase;
import ru.mail.authorizationsdk.domain.usecase.suggestions.EmailSuggestionsUseCase;
import ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics;
import ru.mail.authorizationsdk.feature.common.model.EmailServiceResources;
import ru.mail.authorizationsdk.feature.core.presentation.suggestions.EmailSuggestionsDelegate;
import ru.mail.authorizationsdk.feature.customserver.analytics.CustomServerAnalytics;
import ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams;
import ru.mail.authorizationsdk.feature.customserver.domain.CustomServerUseCase;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsVmDelegate;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.params.ServerParamsVmDelegate;
import ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.LocalImapInteractor;
import ru.mail.authorizationsdk.navigation.DestBase;
import ru.mail.march.concurrent.IoDispatcher;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.kotlin.extension.LazyKt;
import ru.mail.util.log.InternalLogger;
import statusnavbars.StatusNavBarHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Stable
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u0000 u2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002tuB\u008f\u0001\b\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u0006\u0010 \u001a\u00020!¢\u0006\u0004\b\"\u0010#J\u0006\u0010E\u001a\u00020FJ\u0006\u0010G\u001a\u00020FJ\u000e\u0010H\u001a\u00020F2\u0006\u0010I\u001a\u00020\u000eJ\u0016\u0010J\u001a\u00020F2\u0006\u0010K\u001a\u00020LH\u0082@¢\u0006\u0002\u0010MJ\u001a\u0010N\u001a\u00020F2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010-H\u0082@¢\u0006\u0002\u0010PJ\b\u0010Q\u001a\u00020FH\u0002J\f\u0010R\u001a\u00020S*\u00020BH\u0002J\b\u0010T\u001a\u00020FH\u0014J\u0006\u0010U\u001a\u00020FJ\u000e\u0010V\u001a\u00020F2\u0006\u0010W\u001a\u00020-J\u000e\u0010X\u001a\u00020F2\u0006\u0010W\u001a\u00020YJ\t\u0010Z\u001a\u00020FH\u0096\u0001J\u0011\u0010[\u001a\u00020F2\u0006\u0010\\\u001a\u00020-H\u0096\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010&R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010/\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b0\u00101R\u0011\u00104\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0011\u00105\u001a\u000206¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u00109\u001a\u00020:¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000e0>¢\u0006\b\n\u0000\u001a\u0004\b=\u0010?R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020B0A¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u001a\u0010]\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010_0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0018\u0010b\u001a\b\u0012\u0004\u0012\u00020-0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u0010aR\u0018\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bd\u0010aR\u0018\u0010e\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\be\u0010aR\u0018\u0010f\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bf\u0010aR\u0018\u0010g\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010aR\u0018\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bh\u0010aR\u0018\u0010i\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bi\u0010aR\u0018\u0010j\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010aR\u0018\u0010k\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010aR\u0018\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000e0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bl\u0010aR\u0018\u0010m\u001a\b\u0012\u0004\u0012\u00020-0^X\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010aR\u0018\u0010o\u001a\b\u0012\u0004\u0012\u00020q0pX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010s¨\u0006v"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel;", "Landroidx/lifecycle/ViewModel;", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/error/ServerParamsErrorsProvider;", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaProvider;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "commonAnalytics", "Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;", "featureAnalytics", "Lru/mail/authorizationsdk/feature/customserver/analytics/CustomServerAnalytics;", "viewModelDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "viewModelIODispatcher", "isTest", "", "sendServerParamsUseCase", "Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerUseCase;", "emailSuggestionsUseCase", "Lru/mail/authorizationsdk/domain/usecase/suggestions/EmailSuggestionsUseCase;", "domainSuggestionsUseCase", "Lru/mail/authorizationsdk/domain/usecase/suggestions/DomainSuggestionsUseCase;", "serverParamsErrorsVmDelegate", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/error/ServerParamsErrorsVmDelegate;", "pikachuCaptchaVmDelegate", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaVmDelegate;", "resources", "Lru/mail/android_utils/wrapper/Resources;", "statusNavBarHelper", "Lstatusnavbars/StatusNavBarHelper;", "lazyImapInteractor", "Ldagger/Lazy;", "Lru/mail/authorizationsdk/feature/imaplocal/domain/login/interactor/LocalImapInteractor;", "baseLogger", "Lru/mail/util/log/InternalLogger;", "<init>", "(Landroidx/lifecycle/SavedStateHandle;Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;Lru/mail/authorizationsdk/feature/customserver/analytics/CustomServerAnalytics;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;ZLru/mail/authorizationsdk/feature/customserver/domain/CustomServerUseCase;Lru/mail/authorizationsdk/domain/usecase/suggestions/EmailSuggestionsUseCase;Lru/mail/authorizationsdk/domain/usecase/suggestions/DomainSuggestionsUseCase;Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/error/ServerParamsErrorsVmDelegate;Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaVmDelegate;Lru/mail/android_utils/wrapper/Resources;Lstatusnavbars/StatusNavBarHelper;Ldagger/Lazy;Lru/mail/util/log/InternalLogger;)V", "getCommonAnalytics", "()Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;", "()Z", "getStatusNavBarHelper", "()Lstatusnavbars/StatusNavBarHelper;", "log", "args", "Landroid/os/Bundle;", "serviceType", "", "needCaptcha", "imapInteractor", "getImapInteractor", "()Lru/mail/authorizationsdk/feature/imaplocal/domain/login/interactor/LocalImapInteractor;", "imapInteractor$delegate", "Lkotlin/Lazy;", "isForImapOnly", "emailSuggestionsDelegate", "Lru/mail/authorizationsdk/feature/core/presentation/suggestions/EmailSuggestionsDelegate;", "getEmailSuggestionsDelegate", "()Lru/mail/authorizationsdk/feature/core/presentation/suggestions/EmailSuggestionsDelegate;", "serverParamsUIDelegate", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/params/ServerParamsVmDelegate;", "getServerParamsUIDelegate", "()Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/params/ServerParamsVmDelegate;", "isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "resultFlow", "Lru/mail/march/viewmodel/MutableEventFlow;", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "getResultFlow", "()Lru/mail/march/viewmodel/MutableEventFlow;", "onBackClick", "", "sendServerParams", "passwordVisibilityChanged", "isVisible", "sendRequest", "customServerParams", "Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerParams;", "(Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showCaptcha", "message", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hideCaptcha", "emit", "Lkotlinx/coroutines/Job;", "onCleared", "onDismissSuggests", "onChoseSuggestEmail", "email", "onChangeEmail", "Landroidx/compose/ui/text/input/TextFieldValue;", "needNewCaptchaCode", "onUpdateCaptchaCode", "newCaptchaCode", "captchaBitmap", "Lkotlinx/coroutines/flow/StateFlow;", "Landroidx/compose/ui/graphics/ImageBitmap;", "getCaptchaBitmap", "()Lkotlinx/coroutines/flow/StateFlow;", "captchaCode", "getCaptchaCode", "isCaptchaLoading", "isIncomeHostError", "isIncomePortError", "isIncomeSSLError", "isNeedShowCaptcha", "isOutgoingHostError", "isOutgoingPortError", "isOutgoingSSLError", "isPikachuCaptchaCodeError", "mainErrorMessage", "getMainErrorMessage", "makeScroll", "Lkotlinx/coroutines/flow/SharedFlow;", "", "getMakeScroll", "()Lkotlinx/coroutines/flow/SharedFlow;", "Factory", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCustomServerViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomServerViewModel.kt\nru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,259:1\n1#2:260\n*E\n"})
public final class CustomServerViewModel extends ViewModel implements ServerParamsErrorsProvider, PikachuCaptchaProvider {
    public static final int $stable = 0;

    @NotNull
    public static final String CUSTOM_SERVER_IS_FOR_IMAP_ONLY = "CUSTOM_SERVER_IS_FOR_IMAP_ONLY";

    @NotNull
    public static final String CUSTOM_SERVER_LOGIN = "CUSTOM_SERVER_LOGIN";

    @NotNull
    public static final String CUSTOM_SERVER_NEED_CAPTCHA = "CUSTOM_SERVER_NEED_CAPTCHA";

    @NotNull
    public static final String CUSTOM_SERVER_PASSWORD = "CUSTOM_SERVER_PASSWORD";

    @NotNull
    public static final String CUSTOM_SERVER_SERVICE_TYPE = "CUSTOM_SERVER_SERVICE_TYPE";

    @Nullable
    private final Bundle args;

    @NotNull
    private final CommonAnalytics commonAnalytics;

    @NotNull
    private final DomainSuggestionsUseCase domainSuggestionsUseCase;

    @NotNull
    private final EmailSuggestionsDelegate emailSuggestionsDelegate;

    @NotNull
    private final EmailSuggestionsUseCase emailSuggestionsUseCase;

    @NotNull
    private final CustomServerAnalytics featureAnalytics;

    /* JADX INFO: renamed from: imapInteractor$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy imapInteractor;
    private final boolean isForImapOnly;

    @NotNull
    private final MutableStateFlow<Boolean> isLoading;
    private final boolean isTest;

    @NotNull
    private final InternalLogger log;
    private final boolean needCaptcha;

    @NotNull
    private final PikachuCaptchaVmDelegate pikachuCaptchaVmDelegate;

    @NotNull
    private final Resources resources;

    @NotNull
    private final MutableEventFlow<CustomServerResult> resultFlow;

    @NotNull
    private final SavedStateHandle savedStateHandle;

    @NotNull
    private final CustomServerUseCase sendServerParamsUseCase;

    @NotNull
    private final ServerParamsErrorsVmDelegate serverParamsErrorsVmDelegate;

    @NotNull
    private final ServerParamsVmDelegate serverParamsUIDelegate;

    @NotNull
    private final String serviceType;

    @NotNull
    private final StatusNavBarHelper statusNavBarHelper;

    @NotNull
    private final CoroutineDispatcher viewModelDispatcher;

    @NotNull
    private final CoroutineDispatcher viewModelIODispatcher;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$1", f = "CustomServerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomServerViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            EmailServiceResources byName = EmailServiceResources.INSTANCE.getByName(CustomServerViewModel.this.serviceType);
            LoginSuggestion loginSuggestionInvoke$default = DomainSuggestionsUseCase.invoke$default(CustomServerViewModel.this.domainSuggestionsUseCase, byName, false, false, 4, null);
            CustomServerViewModel.this.getEmailSuggestionsDelegate().initialize(CustomServerViewModel.this.emailSuggestionsUseCase.invoke(byName), loginSuggestionInvoke$default.getSuggestions(), loginSuggestionInvoke$default.getSuggestionsLimit());
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$3", f = "CustomServerViewModel.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomServerViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                CustomServerViewModel customServerViewModel = CustomServerViewModel.this;
                this.label = 1;
                if (CustomServerViewModel.showCaptcha$default(customServerViewModel, null, this, 1, null) == coroutine_suspended) {
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
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes15.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel$Factory;", "Lru/mail/authorizationsdk/di/viewmodel/CommonViewModelFactory;", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel;", "create", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @AssistedFactory
    public interface Factory extends CommonViewModelFactory<CustomServerViewModel> {
        @Override // ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory
        @NotNull
        CustomServerViewModel create(@NotNull SavedStateHandle savedStateHandle);
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$emit$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$emit$1", f = "CustomServerViewModel.kt", i = {}, l = {225}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C16301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CustomServerResult $this_emit;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C16301(CustomServerResult customServerResult, Continuation<? super C16301> continuation) {
            super(2, continuation);
            this.$this_emit = customServerResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomServerViewModel.this.new C16301(this.$this_emit, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                CustomServerViewModel.this.featureAnalytics.customServerScreenClosed(this.$this_emit.getClass().getSimpleName().toString());
                MutableEventFlow<CustomServerResult> resultFlow = CustomServerViewModel.this.getResultFlow();
                CustomServerResult customServerResult = this.$this_emit;
                this.label = 1;
                if (resultFlow.emit(customServerResult, this) == coroutine_suspended) {
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
            return ((C16301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$sendRequest$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$sendRequest$2", f = "CustomServerViewModel.kt", i = {0, 1, 1, 2, 2, 3, 3, 4, 4}, l = {182, 197, 200, 204, 207}, m = "invokeSuspend", n = {"$this$withContext", "$this$withContext", "result", "$this$withContext", "result", "$this$withContext", "result", "$this$withContext", "result"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CustomServerParams $customServerParams;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(CustomServerParams customServerParams, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$customServerParams = customServerParams;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = CustomServerViewModel.this.new AnonymousClass2(this.$customServerParams, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:48:0x0132, code lost:
        
            if (r9.emit(r4, r8) == r1) goto L49;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 318
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$sendServerParams$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel$sendServerParams$1", f = "CustomServerViewModel.kt", i = {1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7}, l = {117, VkBrowserActivity.REQUEST_CODE_BLOCKED, 144, 148, 156, 159, 167, 170}, m = "invokeSuspend", n = {"customServerParams", "wrongFields", "customServerParams", "wrongFields", "customServerParams", "wrongFields", "customServerParams", "wrongFields", "localImapResult", "customServerParams", "wrongFields", "localImapResult", "customServerParams", "wrongFields", "localImapResult", "customServerParams", "wrongFields"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1"}, v = 1)
    @SourceDebugExtension({"SMAP\nCustomServerViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomServerViewModel.kt\nru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel$sendServerParams$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,259:1\n1#2:260\n*E\n"})
    static final class C16311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C16311(Continuation<? super C16311> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CustomServerViewModel.this.new C16311(continuation);
        }

        /* JADX WARN: Code duplicated, block: B:10:0x004c A[PHI: r1 r3 r15
          0x004c: PHI (r1v11 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>) = 
          (r1v3 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
          (r1v15 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
         binds: [B:38:0x022c, B:9:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x004c: PHI (r3v6 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams) = 
          (r3v1 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
          (r3v9 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
         binds: [B:38:0x022c, B:9:0x0041] A[DONT_GENERATE, DONT_INLINE]
          0x004c: PHI (r15v86 java.lang.Object) = (r15v74 java.lang.Object), (r15v0 java.lang.Object) binds: [B:38:0x022c, B:9:0x0041] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:19:0x00ca  */
        /* JADX WARN: Code duplicated, block: B:21:0x00ce  */
        /* JADX WARN: Code duplicated, block: B:24:0x0162  */
        /* JADX WARN: Code duplicated, block: B:27:0x0195  */
        /* JADX WARN: Code duplicated, block: B:30:0x01df A[PHI: r1 r3
          0x01df: PHI (r1v6 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>) = 
          (r1v3 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
          (r1v10 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
         binds: [B:28:0x01db, B:12:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x01df: PHI (r3v2 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams) = 
          (r3v1 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
          (r3v5 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
         binds: [B:28:0x01db, B:12:0x005d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:35:0x0203  */
        /* JADX WARN: Code duplicated, block: B:37:0x020b  */
        /* JADX WARN: Code duplicated, block: B:42:0x023b  */
        /* JADX WARN: Code duplicated, block: B:58:0x029a  */
        /* JADX WARN: Code duplicated, block: B:61:0x02c8 A[PHI: r1 r3 r4
          0x02c8: PHI (r1v16 ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.ImapLoginInteractorResult) = 
          (r1v13 ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.ImapLoginInteractorResult)
          (r1v13 ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.ImapLoginInteractorResult)
          (r1v13 ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.ImapLoginInteractorResult)
          (r1v20 ru.mail.authorizationsdk.feature.imaplocal.domain.login.interactor.ImapLoginInteractorResult)
         binds: [B:59:0x02c5, B:55:0x028d, B:48:0x026e, B:8:0x0030] A[DONT_GENERATE, DONT_INLINE]
          0x02c8: PHI (r3v10 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>) = 
          (r3v7 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
          (r3v7 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
          (r3v7 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
          (r3v13 java.util.List<ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName>)
         binds: [B:59:0x02c5, B:55:0x028d, B:48:0x026e, B:8:0x0030] A[DONT_GENERATE, DONT_INLINE]
          0x02c8: PHI (r4v13 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams) = 
          (r4v12 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
          (r4v12 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
          (r4v12 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
          (r4v16 ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams)
         binds: [B:59:0x02c5, B:55:0x028d, B:48:0x026e, B:8:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:66:0x02f1  */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x01fc, code lost:
        
            if (r15.emit(r2, r14) == r0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x02eb, code lost:
        
            if (r15.emit(r2, r14) == r0) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x0307, code lost:
        
            if (r15.sendRequest(r3, r14) == r0) goto L68;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 804
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel.C16311.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C16311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @AssistedInject
    public CustomServerViewModel(@Assisted @NotNull SavedStateHandle savedStateHandle, @NotNull CommonAnalytics commonAnalytics, @NotNull CustomServerAnalytics featureAnalytics, @ViewModelDispatcher @NotNull CoroutineDispatcher viewModelDispatcher, @IoDispatcher @NotNull CoroutineDispatcher viewModelIODispatcher, @IsTest boolean z10, @NotNull CustomServerUseCase sendServerParamsUseCase, @NotNull EmailSuggestionsUseCase emailSuggestionsUseCase, @NotNull DomainSuggestionsUseCase domainSuggestionsUseCase, @NotNull ServerParamsErrorsVmDelegate serverParamsErrorsVmDelegate, @NotNull PikachuCaptchaVmDelegate pikachuCaptchaVmDelegate, @NotNull Resources resources, @NotNull StatusNavBarHelper statusNavBarHelper, @NotNull final dagger.Lazy<LocalImapInteractor> lazyImapInteractor, @NotNull InternalLogger baseLogger) {
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        Intrinsics.checkNotNullParameter(commonAnalytics, "commonAnalytics");
        Intrinsics.checkNotNullParameter(featureAnalytics, "featureAnalytics");
        Intrinsics.checkNotNullParameter(viewModelDispatcher, "viewModelDispatcher");
        Intrinsics.checkNotNullParameter(viewModelIODispatcher, "viewModelIODispatcher");
        Intrinsics.checkNotNullParameter(sendServerParamsUseCase, "sendServerParamsUseCase");
        Intrinsics.checkNotNullParameter(emailSuggestionsUseCase, "emailSuggestionsUseCase");
        Intrinsics.checkNotNullParameter(domainSuggestionsUseCase, "domainSuggestionsUseCase");
        Intrinsics.checkNotNullParameter(serverParamsErrorsVmDelegate, "serverParamsErrorsVmDelegate");
        Intrinsics.checkNotNullParameter(pikachuCaptchaVmDelegate, "pikachuCaptchaVmDelegate");
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(statusNavBarHelper, "statusNavBarHelper");
        Intrinsics.checkNotNullParameter(lazyImapInteractor, "lazyImapInteractor");
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        this.savedStateHandle = savedStateHandle;
        this.commonAnalytics = commonAnalytics;
        this.featureAnalytics = featureAnalytics;
        this.viewModelDispatcher = viewModelDispatcher;
        this.viewModelIODispatcher = viewModelIODispatcher;
        this.isTest = z10;
        this.sendServerParamsUseCase = sendServerParamsUseCase;
        this.emailSuggestionsUseCase = emailSuggestionsUseCase;
        this.domainSuggestionsUseCase = domainSuggestionsUseCase;
        this.serverParamsErrorsVmDelegate = serverParamsErrorsVmDelegate;
        this.pikachuCaptchaVmDelegate = pikachuCaptchaVmDelegate;
        this.resources = resources;
        this.statusNavBarHelper = statusNavBarHelper;
        InternalLogger internalLoggerCreateLogger = baseLogger.createLogger("CustomServerViewModel");
        this.log = internalLoggerCreateLogger;
        Bundle bundle = (Bundle) savedStateHandle.get(DestBase.PARAMS);
        this.args = bundle;
        String string = (bundle == null || (string = bundle.getString(CUSTOM_SERVER_SERVICE_TYPE)) == null) ? "" : string;
        this.serviceType = string;
        boolean z11 = bundle != null ? bundle.getBoolean(CUSTOM_SERVER_NEED_CAPTCHA) : false;
        this.needCaptcha = z11;
        this.imapInteractor = LazyKt.lazyUnsafe(new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.h0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CustomServerViewModel.imapInteractor_delegate$lambda$0(lazyImapInteractor);
            }
        });
        boolean z12 = bundle != null ? bundle.getBoolean(CUSTOM_SERVER_IS_FOR_IMAP_ONLY) : false;
        this.isForImapOnly = z12;
        this.emailSuggestionsDelegate = new EmailSuggestionsDelegate(viewModelDispatcher);
        String string2 = bundle != null ? bundle.getString(CUSTOM_SERVER_LOGIN) : null;
        TextFieldValue textFieldValue = new TextFieldValue(string2 == null ? "" : string2, 0L, (TextRange) null, 6, (DefaultConstructorMarker) null);
        String string3 = bundle != null ? bundle.getString(CUSTOM_SERVER_PASSWORD) : null;
        this.serverParamsUIDelegate = new ServerParamsVmDelegate(textFieldValue, string3 != null ? string3 : "");
        this.isLoading = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.resultFlow = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, internalLoggerCreateLogger.getLogger(), new Flow[0]);
        InternalLogger.d$default(internalLoggerCreateLogger, "onNeedSendMailServerSettings()", null, 2, null);
        ExtensionsKt.launch$default(this, viewModelDispatcher, null, new AnonymousClass1(null), 2, null);
        pikachuCaptchaVmDelegate.setErrorListener(new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.i0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CustomServerViewModel._init_$lambda$0(this.f81130a, (String) obj);
            }
        });
        commonAnalytics.showPassAuth(string);
        commonAnalytics.serviceTypeOnCustomServerScreen(string);
        featureAnalytics.manualSettingsLoginView(z12);
        if (z11) {
            ExtensionsKt.launch$default(this, null, null, new AnonymousClass3(null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(CustomServerViewModel customServerViewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ExtensionsKt.launch$default(customServerViewModel, null, null, new CustomServerViewModel$2$1(customServerViewModel, it, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job emit(CustomServerResult customServerResult) {
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.viewModelDispatcher, null, new C16301(customServerResult, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LocalImapInteractor getImapInteractor() {
        return (LocalImapInteractor) this.imapInteractor.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideCaptcha() {
        this.pikachuCaptchaVmDelegate.setCaptchaCookie(null);
        this.pikachuCaptchaVmDelegate.onUpdateCaptchaCode("");
        this.pikachuCaptchaVmDelegate.setShowCaptcha(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LocalImapInteractor imapInteractor_delegate$lambda$0(dagger.Lazy lazy) {
        Object obj = lazy.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (LocalImapInteractor) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object sendRequest(CustomServerParams customServerParams, Continuation<? super Unit> continuation) {
        this.commonAnalytics.commonLoginSignIn();
        Object objWithContext = BuildersKt.withContext(this.viewModelIODispatcher, new AnonymousClass2(customServerParams, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object showCaptcha(String str, Continuation<? super Unit> continuation) {
        Object objShowError;
        this.pikachuCaptchaVmDelegate.setShowCaptcha(true);
        this.pikachuCaptchaVmDelegate.needNewCaptchaCode();
        return (str == null || (objShowError = this.serverParamsErrorsVmDelegate.showError(str, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : objShowError;
    }

    static /* synthetic */ Object showCaptcha$default(CustomServerViewModel customServerViewModel, String str, Continuation continuation, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        return customServerViewModel.showCaptcha(str, continuation);
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public StateFlow<ImageBitmap> getCaptchaBitmap() {
        return this.pikachuCaptchaVmDelegate.getCaptchaBitmap();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public StateFlow<String> getCaptchaCode() {
        return this.pikachuCaptchaVmDelegate.getCaptchaCode();
    }

    @NotNull
    public final CommonAnalytics getCommonAnalytics() {
        return this.commonAnalytics;
    }

    @NotNull
    public final EmailSuggestionsDelegate getEmailSuggestionsDelegate() {
        return this.emailSuggestionsDelegate;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<String> getMainErrorMessage() {
        return this.serverParamsErrorsVmDelegate.getMainErrorMessage();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public SharedFlow<Object> getMakeScroll() {
        return this.serverParamsErrorsVmDelegate.getMakeScroll();
    }

    @NotNull
    public final MutableEventFlow<CustomServerResult> getResultFlow() {
        return this.resultFlow;
    }

    @NotNull
    public final ServerParamsVmDelegate getServerParamsUIDelegate() {
        return this.serverParamsUIDelegate;
    }

    @NotNull
    public final StatusNavBarHelper getStatusNavBarHelper() {
        return this.statusNavBarHelper;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public StateFlow<Boolean> isCaptchaLoading() {
        return this.pikachuCaptchaVmDelegate.isCaptchaLoading();
    }

    /* JADX INFO: renamed from: isForImapOnly, reason: from getter */
    public final boolean getIsForImapOnly() {
        return this.isForImapOnly;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isIncomeHostError() {
        return this.serverParamsErrorsVmDelegate.isIncomeHostError();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isIncomePortError() {
        return this.serverParamsErrorsVmDelegate.isIncomePortError();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isIncomeSSLError() {
        return this.serverParamsErrorsVmDelegate.isIncomeSSLError();
    }

    @NotNull
    public final MutableStateFlow<Boolean> isLoading() {
        return this.isLoading;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public StateFlow<Boolean> isNeedShowCaptcha() {
        return this.pikachuCaptchaVmDelegate.isNeedShowCaptcha();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isOutgoingHostError() {
        return this.serverParamsErrorsVmDelegate.isOutgoingHostError();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isOutgoingPortError() {
        return this.serverParamsErrorsVmDelegate.isOutgoingPortError();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isOutgoingSSLError() {
        return this.serverParamsErrorsVmDelegate.isOutgoingSSLError();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider
    @NotNull
    public StateFlow<Boolean> isPikachuCaptchaCodeError() {
        return this.serverParamsErrorsVmDelegate.isPikachuCaptchaCodeError();
    }

    /* JADX INFO: renamed from: isTest, reason: from getter */
    public final boolean getIsTest() {
        return this.isTest;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    public void needNewCaptchaCode() {
        this.pikachuCaptchaVmDelegate.needNewCaptchaCode();
    }

    public final void onBackClick() {
        emit(CustomServerResult.BackClick.INSTANCE);
    }

    public final void onChangeEmail(@NotNull TextFieldValue email) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.serverParamsUIDelegate.onChangeEmail(email);
        this.emailSuggestionsDelegate.onChangeEmail(email.getText());
    }

    public final void onChoseSuggestEmail(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.serverParamsUIDelegate.onChangeEmail(new TextFieldValue(email, 0L, (TextRange) null, 6, (DefaultConstructorMarker) null));
        onDismissSuggests();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.pikachuCaptchaVmDelegate.onDestroy();
    }

    public final void onDismissSuggests() {
        this.emailSuggestionsDelegate.onChooseEmail();
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    public void onUpdateCaptchaCode(@NotNull String newCaptchaCode) {
        Intrinsics.checkNotNullParameter(newCaptchaCode, "newCaptchaCode");
        this.pikachuCaptchaVmDelegate.onUpdateCaptchaCode(newCaptchaCode);
    }

    public final void passwordVisibilityChanged(boolean isVisible) {
        this.commonAnalytics.loginViewPassword("custom_server", isVisible);
    }

    public final void sendServerParams() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.viewModelDispatcher, null, new C16311(null), 2, null);
    }
}
