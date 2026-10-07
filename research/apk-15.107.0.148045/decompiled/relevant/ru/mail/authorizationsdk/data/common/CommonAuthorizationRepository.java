package ru.mail.authorizationsdk.data.common;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.extension.ContextKt;
import ru.mail.auth.LoginActivity;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.data.externalaccount.BaseOauthParams;
import ru.mail.authorizationsdk.data.model.MailOAuthCredentials;
import ru.mail.authorizationsdk.domain.model.OAuthCredentials;
import ru.mail.authorizationsdk.domain.model.Result;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthDelegate;
import ru.mail.authorizationsdk.feature.authactivity.domain.interactor.AuthMailApiCommonResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJv\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u001bJb\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\r2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010\u001fJJ\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0016H\u0086@¢\u0006\u0002\u0010\"JJ\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0016H\u0086@¢\u0006\u0002\u0010\"JR\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010%\u001a\u00020&2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0086@¢\u0006\u0002\u0010'JR\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\"\u0010)\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0,0+\u0012\u0006\u0012\u0004\u0018\u00010\u00010*H\u0082@¢\u0006\u0002\u0010.J>\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\r2\f\u00100\u001a\b\u0012\u0004\u0012\u00020-0,2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lru/mail/authorizationsdk/data/common/CommonAuthorizationRepository;", "", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "commonAuthorizationMailApi", "Lru/mail/authorizationsdk/data/common/CommonAuthorizationMailApi;", "basePasswordOauthParams", "Lru/mail/authorizationsdk/data/externalaccount/BaseOauthParams;", "authDelegate", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthDelegate;", "urlsResolver", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "clientId", "", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/authorizationsdk/data/common/CommonAuthorizationMailApi;Lru/mail/authorizationsdk/data/externalaccount/BaseOauthParams;Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthDelegate;Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;Ljava/lang/String;)V", "getMailTokensByOneTimeCode", "Lru/mail/authorizationsdk/domain/model/Result;", "Lru/mail/authorizationsdk/domain/model/OAuthCredentials;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "email", "params", "", "cookies", "bindToken", "additionalParams", "authFlowType", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMailTokensByPassword", "password", "tsaCookie", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMailTokensByAgToken", "agToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVkPasswordMailTokensByAgToken", "getSocialAuthTokens", "isResetSoftVKIDBind", "", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMailTokensByAgTokenQuery", "queryBlock", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeResult", "result", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CommonAuthorizationRepository {

    @NotNull
    private static final String TSA_COOKIE_PREFIX = "tsa=";

    @NotNull
    private final AuthDelegate authDelegate;

    @NotNull
    private final BaseOauthParams basePasswordOauthParams;

    @Nullable
    private final String clientId;

    @NotNull
    private final CommonAuthorizationMailApi commonAuthorizationMailApi;

    @NotNull
    private final CoroutineDispatcher ioDispatcher;

    @NotNull
    private final AuthorizationSdkUrlsResolver urlsResolver;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByAgToken$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByAgToken$2", f = "CommonAuthorizationRepository.kt", i = {0}, l = {82}, m = "invokeSuspend", n = {"bodyParams"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super NetResponse<? extends MailOAuthCredentials>>, Object> {
        final /* synthetic */ Map<String, String> $additionalParams;
        final /* synthetic */ String $agToken;
        final /* synthetic */ String $email;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Map<String, String> map, String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$additionalParams = map;
            this.$email = str;
            this.$agToken = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return CommonAuthorizationRepository.this.new AnonymousClass2(this.$additionalParams, this.$email, this.$agToken, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super NetResponse<? extends MailOAuthCredentials>> continuation) {
            return invoke2((Continuation<? super NetResponse<MailOAuthCredentials>>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            Map map = MapsKt.toMap(CommonAuthorizationRepository.this.basePasswordOauthParams.getParamsForRequest());
            CommonAuthorizationMailApi commonAuthorizationMailApi = CommonAuthorizationRepository.this.commonAuthorizationMailApi;
            Map<String, String> mapPlus = MapsKt.plus(map, this.$additionalParams);
            String str = CommonAuthorizationRepository.this.clientId;
            String str2 = this.$email;
            String str3 = this.$agToken;
            this.L$0 = SpillingKt.nullOutSpilledVariable(map);
            this.label = 1;
            Object mailAgTokenAuthorization = commonAuthorizationMailApi.getMailAgTokenAuthorization(str2, str3, str, mapPlus, this);
            return mailAgTokenAuthorization == coroutine_suspended ? coroutine_suspended : mailAgTokenAuthorization;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super NetResponse<MailOAuthCredentials>> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByAgTokenQuery$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository", f = "CommonAuthorizationRepository.kt", i = {0, 0, 0}, l = {LoginActivity.REQUEST_MY_COM_TUTORIAL}, m = "getMailTokensByAgTokenQuery", n = {"email", "authFlowType", "queryBlock"}, s = {"L$0", "L$1", "L$2"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
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
            return CommonAuthorizationRepository.this.getMailTokensByAgTokenQuery(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByOneTimeCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository", f = "CommonAuthorizationRepository.kt", i = {0, 0, 0, 0, 0, 0}, l = {32}, m = "getMailTokensByOneTimeCode", n = {"email", "params", "cookies", "bindToken", "additionalParams", "authFlowType"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 1)
    static final class C15871 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C15871(Continuation<? super C15871> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonAuthorizationRepository.this.getMailTokensByOneTimeCode(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByPassword$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository", f = "CommonAuthorizationRepository.kt", i = {0, 0, 0, 0, 0, 0}, l = {53}, m = "getMailTokensByPassword", n = {"email", "password", "tsaCookie", "bindToken", "additionalParams", "authFlowType"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 1)
    static final class C15881 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C15881(Continuation<? super C15881> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommonAuthorizationRepository.this.getMailTokensByPassword(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getSocialAuthTokens$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getSocialAuthTokens$2", f = "CommonAuthorizationRepository.kt", i = {0, 0}, l = {119}, m = "invokeSuspend", n = {"bodyParams", "resetSoftVKIDBind"}, s = {"L$0", "I$0"}, v = 1)
    static final class C15892 extends SuspendLambda implements Function1<Continuation<? super NetResponse<? extends MailOAuthCredentials>>, Object> {
        final /* synthetic */ Map<String, String> $additionalParams;
        final /* synthetic */ String $agToken;
        final /* synthetic */ String $email;
        final /* synthetic */ boolean $isResetSoftVKIDBind;
        final /* synthetic */ String $url;
        int I$0;
        Object L$0;
        int label;
        final /* synthetic */ CommonAuthorizationRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C15892(boolean z10, CommonAuthorizationRepository commonAuthorizationRepository, Map<String, String> map, String str, String str2, String str3, Continuation<? super C15892> continuation) {
            super(1, continuation);
            this.$isResetSoftVKIDBind = z10;
            this.this$0 = commonAuthorizationRepository;
            this.$additionalParams = map;
            this.$url = str;
            this.$email = str2;
            this.$agToken = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new C15892(this.$isResetSoftVKIDBind, this.this$0, this.$additionalParams, this.$url, this.$email, this.$agToken, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super NetResponse<? extends MailOAuthCredentials>> continuation) {
            return invoke2((Continuation<? super NetResponse<MailOAuthCredentials>>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            boolean z10 = this.$isResetSoftVKIDBind;
            Map map = MapsKt.toMap(this.this$0.basePasswordOauthParams.getParamsForRequest());
            CommonAuthorizationMailApi commonAuthorizationMailApi = this.this$0.commonAuthorizationMailApi;
            Map<String, String> mapPlus = MapsKt.plus(map, this.$additionalParams);
            String str = this.this$0.clientId;
            String str2 = this.$url;
            String str3 = this.$email;
            String str4 = this.$agToken;
            this.L$0 = SpillingKt.nullOutSpilledVariable(map);
            this.I$0 = z10 ? 1 : 0;
            this.label = 1;
            Object socialAuthAgTokenAuthorization = commonAuthorizationMailApi.getSocialAuthAgTokenAuthorization(str2, str3, str4, str, z10 ? 1 : 0, mapPlus, this);
            return socialAuthAgTokenAuthorization == coroutine_suspended ? coroutine_suspended : socialAuthAgTokenAuthorization;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super NetResponse<MailOAuthCredentials>> continuation) {
            return ((C15892) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getVkPasswordMailTokensByAgToken$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getVkPasswordMailTokensByAgToken$2", f = "CommonAuthorizationRepository.kt", i = {0}, l = {99}, m = "invokeSuspend", n = {"bodyParams"}, s = {"L$0"}, v = 1)
    static final class C15902 extends SuspendLambda implements Function1<Continuation<? super NetResponse<? extends MailOAuthCredentials>>, Object> {
        final /* synthetic */ Map<String, String> $additionalParams;
        final /* synthetic */ String $agToken;
        final /* synthetic */ String $email;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C15902(Map<String, String> map, String str, String str2, Continuation<? super C15902> continuation) {
            super(1, continuation);
            this.$additionalParams = map;
            this.$email = str;
            this.$agToken = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return CommonAuthorizationRepository.this.new C15902(this.$additionalParams, this.$email, this.$agToken, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super NetResponse<? extends MailOAuthCredentials>> continuation) {
            return invoke2((Continuation<? super NetResponse<MailOAuthCredentials>>) continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            Map map = MapsKt.toMap(CommonAuthorizationRepository.this.basePasswordOauthParams.getParamsForRequest());
            CommonAuthorizationMailApi commonAuthorizationMailApi = CommonAuthorizationRepository.this.commonAuthorizationMailApi;
            Map<String, String> mapPlus = MapsKt.plus(map, this.$additionalParams);
            String str = CommonAuthorizationRepository.this.clientId;
            String str2 = this.$email;
            String str3 = this.$agToken;
            this.L$0 = SpillingKt.nullOutSpilledVariable(map);
            this.label = 1;
            Object vkAgTokenAuthorization = commonAuthorizationMailApi.getVkAgTokenAuthorization(str2, str3, str, mapPlus, this);
            return vkAgTokenAuthorization == coroutine_suspended ? coroutine_suspended : vkAgTokenAuthorization;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super NetResponse<MailOAuthCredentials>> continuation) {
            return ((C15902) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public CommonAuthorizationRepository(@NotNull CoroutineDispatcher ioDispatcher, @NotNull CommonAuthorizationMailApi commonAuthorizationMailApi, @NotNull BaseOauthParams basePasswordOauthParams, @NotNull AuthDelegate authDelegate, @NotNull AuthorizationSdkUrlsResolver urlsResolver, @Nullable String str) {
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        Intrinsics.checkNotNullParameter(commonAuthorizationMailApi, "commonAuthorizationMailApi");
        Intrinsics.checkNotNullParameter(basePasswordOauthParams, "basePasswordOauthParams");
        Intrinsics.checkNotNullParameter(authDelegate, "authDelegate");
        Intrinsics.checkNotNullParameter(urlsResolver, "urlsResolver");
        this.ioDispatcher = ioDispatcher;
        this.commonAuthorizationMailApi = commonAuthorizationMailApi;
        this.basePasswordOauthParams = basePasswordOauthParams;
        this.authDelegate = authDelegate;
        this.urlsResolver = urlsResolver;
        this.clientId = str;
    }

    private final Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase> executeResult(String email, String password, NetResponse<MailOAuthCredentials> result, String authFlowType) {
        if (!(result instanceof NetResponse.Success)) {
            if (result instanceof NetResponse.Error) {
                return new Result.Failure(AuthDelegate.onError$default(this.authDelegate, email, password, (NetResponse.Error) result, false, authFlowType, 8, null));
            }
            throw new NoWhenBranchMatchedException();
        }
        NetResponse.Success success = (NetResponse.Success) result;
        String accessToken = ((MailOAuthCredentials) success.getBody()).getOauth().getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        String refreshToken = ((MailOAuthCredentials) success.getBody()).getOauth().getRefreshToken();
        return new Result.Success(new OAuthCredentials(email, accessToken, refreshToken != null ? refreshToken : ""));
    }

    static /* synthetic */ Result executeResult$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, NetResponse netResponse, String str3, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return commonAuthorizationRepository.executeResult(str, str2, netResponse, str3);
    }

    public static /* synthetic */ Object getMailTokensByAgToken$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, String str3, Map map, Continuation continuation, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        return commonAuthorizationRepository.getMailTokensByAgToken(str, str2, str3, map, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getMailTokensByAgTokenQuery(String str, String str2, Function1<? super Continuation<? super NetResponse<MailOAuthCredentials>>, ? extends Object> function1, Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CoroutineDispatcher coroutineDispatcher = this.ioDispatcher;
            CommonAuthorizationRepository$getMailTokensByAgTokenQuery$result$1 commonAuthorizationRepository$getMailTokensByAgTokenQuery$result$1 = new CommonAuthorizationRepository$getMailTokensByAgTokenQuery$result$1(function1, null);
            anonymousClass1.L$0 = str;
            anonymousClass1.L$1 = str2;
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(function1);
            anonymousClass1.label = 1;
            objWithContext = BuildersKt.withContext(coroutineDispatcher, commonAuthorizationRepository$getMailTokensByAgTokenQuery$result$1, anonymousClass1);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) anonymousClass1.L$1;
            str = (String) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objWithContext);
        }
        return executeResult(str, "", (NetResponse) objWithContext, str2);
    }

    static /* synthetic */ Object getMailTokensByAgTokenQuery$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, Function1 function1, Continuation continuation, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        return commonAuthorizationRepository.getMailTokensByAgTokenQuery(str, str2, function1, continuation);
    }

    public static /* synthetic */ Object getMailTokensByOneTimeCode$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, Map map, Map map2, String str2, Map map3, String str3, Continuation continuation, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        if ((i10 & 32) != 0) {
            str3 = null;
        }
        return commonAuthorizationRepository.getMailTokensByOneTimeCode(str, map, map2, str2, map3, str3, continuation);
    }

    public static /* synthetic */ Object getMailTokensByPassword$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, String str3, String str4, Map map, String str5, Continuation continuation, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        if ((i10 & 32) != 0) {
            str5 = null;
        }
        return commonAuthorizationRepository.getMailTokensByPassword(str, str2, str3, str4, map, str5, continuation);
    }

    public static /* synthetic */ Object getSocialAuthTokens$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, boolean z10, Map map, String str3, Continuation continuation, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            str3 = null;
        }
        return commonAuthorizationRepository.getSocialAuthTokens(str, str2, z10, map, str3, continuation);
    }

    public static /* synthetic */ Object getVkPasswordMailTokensByAgToken$default(CommonAuthorizationRepository commonAuthorizationRepository, String str, String str2, String str3, Map map, Continuation continuation, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        return commonAuthorizationRepository.getVkPasswordMailTokensByAgToken(str, str2, str3, map, continuation);
    }

    @Nullable
    public final Object getMailTokensByAgToken(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Map<String, String> map, @NotNull Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        return getMailTokensByAgTokenQuery(str, str3, new AnonymousClass2(map, str, str2, null), continuation);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    @Nullable
    public final Object getMailTokensByOneTimeCode(@NotNull String str, @NotNull Map<String, String> map, @NotNull Map<String, String> map2, @Nullable String str2, @NotNull Map<String, String> map3, @Nullable String str3, @NotNull Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        C15871 c15871;
        String str4;
        String str5;
        if (continuation instanceof C15871) {
            c15871 = (C15871) continuation;
            int i10 = c15871.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c15871.label = i10 - Integer.MIN_VALUE;
            } else {
                c15871 = new C15871(continuation);
            }
        } else {
            c15871 = new C15871(continuation);
        }
        C15871 c15872 = c15871;
        Object objWithContext = c15872.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c15872.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CoroutineDispatcher coroutineDispatcher = this.ioDispatcher;
            CommonAuthorizationRepository$getMailTokensByOneTimeCode$result$1 commonAuthorizationRepository$getMailTokensByOneTimeCode$result$1 = new CommonAuthorizationRepository$getMailTokensByOneTimeCode$result$1(this, map, map2, map3, str, str2, null);
            c15872.L$0 = str;
            c15872.L$1 = SpillingKt.nullOutSpilledVariable(map);
            c15872.L$2 = SpillingKt.nullOutSpilledVariable(map2);
            c15872.L$3 = SpillingKt.nullOutSpilledVariable(str2);
            c15872.L$4 = SpillingKt.nullOutSpilledVariable(map3);
            str4 = str3;
            c15872.L$5 = str4;
            c15872.label = 1;
            objWithContext = BuildersKt.withContext(coroutineDispatcher, commonAuthorizationRepository$getMailTokensByOneTimeCode$result$1, c15872);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            str5 = str;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str4 = (String) c15872.L$5;
            str5 = (String) c15872.L$0;
            ResultKt.throwOnFailure(objWithContext);
        }
        return executeResult(str5, "", (NetResponse) objWithContext, str4);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    @Nullable
    public final Object getMailTokensByPassword(@NotNull String str, @NotNull String str2, @Nullable String str3, @Nullable String str4, @NotNull Map<String, String> map, @Nullable String str5, @NotNull Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        C15881 c15881;
        String str6;
        String str7;
        String str8;
        if (continuation instanceof C15881) {
            c15881 = (C15881) continuation;
            int i10 = c15881.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c15881.label = i10 - Integer.MIN_VALUE;
            } else {
                c15881 = new C15881(continuation);
            }
        } else {
            c15881 = new C15881(continuation);
        }
        C15881 c15882 = c15881;
        Object objWithContext = c15882.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c15882.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CoroutineDispatcher coroutineDispatcher = this.ioDispatcher;
            CommonAuthorizationRepository$getMailTokensByPassword$result$1 commonAuthorizationRepository$getMailTokensByPassword$result$1 = new CommonAuthorizationRepository$getMailTokensByPassword$result$1(this, str3, map, str, str2, str4, null);
            c15882.L$0 = str;
            c15882.L$1 = str2;
            c15882.L$2 = SpillingKt.nullOutSpilledVariable(str3);
            c15882.L$3 = SpillingKt.nullOutSpilledVariable(str4);
            c15882.L$4 = SpillingKt.nullOutSpilledVariable(map);
            str6 = str5;
            c15882.L$5 = str6;
            c15882.label = 1;
            objWithContext = BuildersKt.withContext(coroutineDispatcher, commonAuthorizationRepository$getMailTokensByPassword$result$1, c15882);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            str7 = str;
            str8 = str2;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str6 = (String) c15882.L$5;
            str8 = (String) c15882.L$1;
            str7 = (String) c15882.L$0;
            ResultKt.throwOnFailure(objWithContext);
        }
        return executeResult(str7, str8, (NetResponse) objWithContext, str6);
    }

    @Nullable
    public final Object getSocialAuthTokens(@NotNull String str, @NotNull String str2, boolean z10, @NotNull Map<String, String> map, @Nullable String str3, @NotNull Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        return getMailTokensByAgTokenQuery(str, str3, new C15892(z10, this, map, ContextKt.withPath(this.urlsResolver.getSwaUrl(), "cgi-bin/auth"), str, str2, null), continuation);
    }

    @Nullable
    public final Object getVkPasswordMailTokensByAgToken(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull Map<String, String> map, @NotNull Continuation<? super Result<OAuthCredentials, AuthMailApiCommonResult.AdditionalCase>> continuation) {
        return getMailTokensByAgTokenQuery(str, str3, new C15902(map, str, str2, null), continuation);
    }

    public /* synthetic */ CommonAuthorizationRepository(CoroutineDispatcher coroutineDispatcher, CommonAuthorizationMailApi commonAuthorizationMailApi, BaseOauthParams baseOauthParams, AuthDelegate authDelegate, AuthorizationSdkUrlsResolver authorizationSdkUrlsResolver, String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineDispatcher, commonAuthorizationMailApi, baseOauthParams, authDelegate, authorizationSdkUrlsResolver, (i10 & 32) != 0 ? null : str);
    }
}
