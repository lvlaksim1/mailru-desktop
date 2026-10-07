package ru.mail.authorizationsdk.feature.customserver.data;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.external.service.ActiveAccountModeProvider;
import ru.mail.authorizationsdk.feature.customserver.domain.CustomServerParams;
import ru.mail.authorizationsdk.feature.customserver.domain.model.CustomServerSettingsResult;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/data/CustomServerRepository;", "", "customServerMailApi", "Lru/mail/authorizationsdk/feature/customserver/data/CustomServerMailApi;", "activeAccountModeProvider", "Lru/mail/authorizationsdk/external/service/ActiveAccountModeProvider;", "customServerErrorDelegate", "Lru/mail/authorizationsdk/feature/customserver/data/CustomServerErrorDelegate;", "<init>", "(Lru/mail/authorizationsdk/feature/customserver/data/CustomServerMailApi;Lru/mail/authorizationsdk/external/service/ActiveAccountModeProvider;Lru/mail/authorizationsdk/feature/customserver/data/CustomServerErrorDelegate;)V", "sendServerParams", "Lru/mail/authorizationsdk/feature/customserver/domain/model/CustomServerSettingsResult;", "customServerParams", "Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerParams;", "(Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCustomServerRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomServerRepository.kt\nru/mail/authorizationsdk/feature/customserver/data/CustomServerRepository\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,47:1\n29#2,3:48\n29#2,3:51\n29#2,3:54\n*S KotlinDebug\n*F\n+ 1 CustomServerRepository.kt\nru/mail/authorizationsdk/feature/customserver/data/CustomServerRepository\n*L\n19#1:48,3\n25#1:51,3\n29#1:54,3\n*E\n"})
public final class CustomServerRepository {
    public static final int $stable = 8;

    @NotNull
    private final ActiveAccountModeProvider activeAccountModeProvider;

    @NotNull
    private final CustomServerErrorDelegate customServerErrorDelegate;

    @NotNull
    private final CustomServerMailApi customServerMailApi;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.data.CustomServerRepository$sendServerParams$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.data.CustomServerRepository", f = "CustomServerRepository.kt", i = {0, 0, 0, 0}, l = {34}, m = "sendServerParams", n = {"customServerParams", "collect", "user", "smtp"}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
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
            return CustomServerRepository.this.sendServerParams(null, this);
        }
    }

    public CustomServerRepository(@NotNull CustomServerMailApi customServerMailApi, @NotNull ActiveAccountModeProvider activeAccountModeProvider, @NotNull CustomServerErrorDelegate customServerErrorDelegate) {
        Intrinsics.checkNotNullParameter(customServerMailApi, "customServerMailApi");
        Intrinsics.checkNotNullParameter(activeAccountModeProvider, "activeAccountModeProvider");
        Intrinsics.checkNotNullParameter(customServerErrorDelegate, "customServerErrorDelegate");
        this.customServerMailApi = customServerMailApi;
        this.activeAccountModeProvider = activeAccountModeProvider;
        this.customServerErrorDelegate = customServerErrorDelegate;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    @Nullable
    public final Object sendServerParams(@NotNull CustomServerParams customServerParams, @NotNull Continuation<? super CustomServerSettingsResult> continuation) {
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objCustomServerRegisterAccount = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass2.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objCustomServerRegisterAccount);
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            String lowerCase = customServerParams.getProtocol().name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            JsonElementBuildersKt.put(jsonObjectBuilder, "type", lowerCase);
            JsonElementBuildersKt.put(jsonObjectBuilder, Collector.SERVER, customServerParams.getServerSettings().getIncomingHost());
            JsonElementBuildersKt.put(jsonObjectBuilder, "port", customServerParams.getServerSettings().getIncomingPort());
            JsonElementBuildersKt.put(jsonObjectBuilder, Collector.SSL, Boxing.boxBoolean(customServerParams.getServerSettings().getIncomingSSLEnabled()));
            JsonObject jsonObjectBuild = jsonObjectBuilder.build();
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder2, "email", customServerParams.getEmail());
            JsonElementBuildersKt.put(jsonObjectBuilder2, "password", customServerParams.getPassword());
            JsonObject jsonObjectBuild2 = jsonObjectBuilder2.build();
            JsonObjectBuilder jsonObjectBuilder3 = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder3, Collector.SERVER, customServerParams.getServerSettings().getOutgoingHost());
            JsonElementBuildersKt.put(jsonObjectBuilder3, "port", customServerParams.getServerSettings().getOutgoingPort());
            JsonElementBuildersKt.put(jsonObjectBuilder3, Collector.SSL, Boxing.boxBoolean(customServerParams.getServerSettings().getOutgoingSSLEnabled()));
            JsonObject jsonObjectBuild3 = jsonObjectBuilder3.build();
            CustomServerMailApi customServerMailApi = this.customServerMailApi;
            String strIsActiveMode = this.activeAccountModeProvider.isActiveMode(customServerParams.getEmail());
            String captchaCookie = customServerParams.getCaptchaCookie();
            String captchaCode = customServerParams.getCaptchaCode();
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(customServerParams);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(jsonObjectBuild);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(jsonObjectBuild2);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(jsonObjectBuild3);
            anonymousClass2.label = 1;
            objCustomServerRegisterAccount = customServerMailApi.customServerRegisterAccount(strIsActiveMode, captchaCookie, captchaCode, jsonObjectBuild, jsonObjectBuild2, jsonObjectBuild3, anonymousClass2);
            if (objCustomServerRegisterAccount == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objCustomServerRegisterAccount);
        }
        NetResponse netResponse = (NetResponse) objCustomServerRegisterAccount;
        if (netResponse instanceof NetResponse.Success) {
            return CustomServerSettingsResult.ServerParamsSuccessfulDelivered.INSTANCE;
        }
        CustomServerErrorDelegate customServerErrorDelegate = this.customServerErrorDelegate;
        Intrinsics.checkNotNull(netResponse, "null cannot be cast to non-null type ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse.Error");
        return customServerErrorDelegate.parseApiError((NetResponse.Error) netResponse);
    }
}
