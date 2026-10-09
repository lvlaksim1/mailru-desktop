package com.vk.pushme.logic;

import androidx.annotation.WorkerThread;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.logic.request.EditSubscriptionRequest;
import com.vk.pushme.logic.request.NewSubscriptionRequest;
import com.vk.pushme.logic.request.UnsubscribeRequest;
import com.vk.pushme.logic.usecase.DeleteTokenUseCase;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import com.vk.pushme.logic.usecase.UnsubscribeUseCase;
import com.vk.pushme.mapper.EntityMapper;
import com.vk.pushme.model.Application;
import com.vk.pushme.model.Request;
import com.vk.pushme.model.SubscriptionSettings;
import com.vk.pushme.model.SubscriptionSettingsBuilder;
import com.vk.pushme.model.result.SubscriptionResult;
import com.vk.pushme.model.result.UnsubscribeResult;
import com.vk.pushme.util.Utils;
import com.vk.pushme.work.util.WorkScheduler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u0003H\u0016J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00192\u0006\u0010\u001b\u001a\u00020\u0003H\u0016J\u000e\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0019H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0003H\u0017J\u0018\u0010*\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001b\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010+J\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001d0-H\u0096@¢\u0006\u0002\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/vk/pushme/logic/ApplicationImpl;", "Lcom/vk/pushme/model/Application;", "name", "", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "subscriptionUseCase", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "unsubscribeUseCase", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "appendSdkDeviceId", "", "logger", "Lcom/vk/pushme/common/Logger;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Ljava/lang/String;Lcom/vk/pushme/work/util/WorkScheduler;Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;Lcom/vk/pushme/database/dao/SubscriptionDao;Lcom/vk/pushme/database/dao/PendingActionDao;ZLcom/vk/pushme/common/Logger;Lkotlinx/coroutines/CoroutineScope;)V", "getName", "()Ljava/lang/String;", "registerAccount", "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "account", "settings", "Lcom/vk/pushme/model/SubscriptionSettings;", "registerAccounts", "accounts", "", "Lcom/vk/pushme/model/Application$AccountRequest;", "editSubscription", "Lcom/vk/pushme/model/SubscriptionSettingsBuilder;", "unregisterAccount", "Lcom/vk/pushme/model/result/UnsubscribeResult;", "unregisterAllAccounts", "unsubscribeByToken", "", "token", "findSubscription", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSubscriptions", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nApplicationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApplicationImpl.kt\ncom/vk/pushme/logic/ApplicationImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,149:1\n1#2:150\n1740#3,3:151\n1563#3:154\n1634#3,3:155\n1563#3:158\n1634#3,3:159\n*S KotlinDebug\n*F\n+ 1 ApplicationImpl.kt\ncom/vk/pushme/logic/ApplicationImpl\n*L\n46#1:151,3\n47#1:154\n47#1:155,3\n138#1:158\n138#1:159,3\n*E\n"})
public final class ApplicationImpl implements Application {
    private final boolean appendSdkDeviceId;

    @NotNull
    private final CoroutineScope coroutineScope;

    @NotNull
    private final Logger logger;

    @NotNull
    private final String name;

    @NotNull
    private final PendingActionDao pendingActionDao;

    @NotNull
    private final SubscriptionDao subscriptionDao;

    @NotNull
    private final SubscriptionUseCase subscriptionUseCase;

    @NotNull
    private final UnsubscribeUseCase unsubscribeUseCase;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.ApplicationImpl$findSubscription$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.ApplicationImpl", f = "ApplicationImpl.kt", i = {0}, l = {124}, m = "findSubscription", n = {"account"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
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
            return ApplicationImpl.this.findSubscription(null, this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.ApplicationImpl$getAllSubscriptions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.ApplicationImpl", f = "ApplicationImpl.kt", i = {}, l = {137}, m = "getAllSubscriptions", n = {}, s = {}, v = 1)
    static final class C10721 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C10721(Continuation<? super C10721> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApplicationImpl.this.getAllSubscriptions(this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.ApplicationImpl$unsubscribeByToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.ApplicationImpl$unsubscribeByToken$1", f = "ApplicationImpl.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C10731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DeleteTokenUseCase.Result>, Object> {
        final /* synthetic */ String $token;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10731(String str, Continuation<? super C10731> continuation) {
            super(2, continuation);
            this.$token = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C10731(this.$token, continuation);
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
            DeleteTokenUseCase deleteTokenUseCase$push_me_sdk_release = PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getDeleteTokenUseCase$push_me_sdk_release();
            String str = this.$token;
            this.label = 1;
            Object objInvoke = deleteTokenUseCase$push_me_sdk_release.invoke(str, this);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DeleteTokenUseCase.Result> continuation) {
            return ((C10731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public ApplicationImpl(@NotNull String name, @NotNull WorkScheduler workScheduler, @NotNull SubscriptionUseCase subscriptionUseCase, @NotNull UnsubscribeUseCase unsubscribeUseCase, @NotNull SubscriptionDao subscriptionDao, @NotNull PendingActionDao pendingActionDao, boolean z10, @NotNull Logger logger, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(subscriptionUseCase, "subscriptionUseCase");
        Intrinsics.checkNotNullParameter(unsubscribeUseCase, "unsubscribeUseCase");
        Intrinsics.checkNotNullParameter(subscriptionDao, "subscriptionDao");
        Intrinsics.checkNotNullParameter(pendingActionDao, "pendingActionDao");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.name = name;
        this.workScheduler = workScheduler;
        this.subscriptionUseCase = subscriptionUseCase;
        this.unsubscribeUseCase = unsubscribeUseCase;
        this.subscriptionDao = subscriptionDao;
        this.pendingActionDao = pendingActionDao;
        this.appendSdkDeviceId = z10;
        this.logger = logger;
        this.coroutineScope = coroutineScope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditSubscriptionRequest editSubscription$lambda$1(ApplicationImpl applicationImpl, String str, SubscriptionSettingsBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        WorkScheduler workScheduler = applicationImpl.workScheduler;
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return new EditSubscriptionRequest(workScheduler, lowerCase, applicationImpl.name, builder, applicationImpl.appendSdkDeviceId, applicationImpl.subscriptionUseCase, applicationImpl.subscriptionDao, applicationImpl.pendingActionDao, applicationImpl.coroutineScope, applicationImpl.logger);
    }

    @Override // com.vk.pushme.model.Application
    @NotNull
    public SubscriptionSettingsBuilder editSubscription(@NotNull final String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        if (StringsKt.isBlank(account)) {
            throw new IllegalArgumentException("Account cannot be empty or blank");
        }
        return new SubscriptionSettingsBuilder(new Function1() { // from class: com.vk.pushme.logic.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApplicationImpl.editSubscription$lambda$1(this.f51504a, account, (SubscriptionSettingsBuilder) obj);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.vk.pushme.model.Application
    @Nullable
    public Object findSubscription(@NotNull String str, @NotNull Continuation<? super SubscriptionSettings> continuation) {
        AnonymousClass1 anonymousClass1;
        Subscription domainModel;
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
        Object objFindSubscription = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objFindSubscription);
            if (StringsKt.isBlank(str)) {
                throw new IllegalArgumentException("Account must not be empty or blank");
            }
            SubscriptionDao subscriptionDao = this.subscriptionDao;
            String str2 = this.name;
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.label = 1;
            objFindSubscription = subscriptionDao.findSubscription(str2, lowerCase, anonymousClass1);
            if (objFindSubscription == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objFindSubscription);
        }
        com.vk.pushme.database.entity.Subscription subscription = (com.vk.pushme.database.entity.Subscription) objFindSubscription;
        if (subscription == null || (domainModel = EntityMapper.INSTANCE.toDomainModel(subscription)) == null) {
            return null;
        }
        return new SubscriptionSettings(domainModel.getTags(), domainModel.getDeliveryTime(), domainModel.getExtras(), domainModel.getSpecifyTransportOption());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // com.vk.pushme.model.Application
    @Nullable
    public Object getAllSubscriptions(@NotNull Continuation<? super Collection<SubscriptionSettings>> continuation) {
        C10721 c10721;
        if (continuation instanceof C10721) {
            c10721 = (C10721) continuation;
            int i10 = c10721.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c10721.label = i10 - Integer.MIN_VALUE;
            } else {
                c10721 = new C10721(continuation);
            }
        } else {
            c10721 = new C10721(continuation);
        }
        Object allForApplication = c10721.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c10721.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(allForApplication);
            SubscriptionDao subscriptionDao = this.subscriptionDao;
            String str = this.name;
            c10721.label = 1;
            allForApplication = subscriptionDao.getAllForApplication(str, c10721);
            if (allForApplication == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(allForApplication);
        }
        Iterable iterable = (Iterable) allForApplication;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Subscription domainModel = EntityMapper.INSTANCE.toDomainModel((com.vk.pushme.database.entity.Subscription) it.next());
            arrayList.add(new SubscriptionSettings(domainModel.getTags(), domainModel.getDeliveryTime(), domainModel.getExtras(), domainModel.getSpecifyTransportOption()));
        }
        return arrayList;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @Override // com.vk.pushme.model.Application
    @NotNull
    public Request<SubscriptionResult> registerAccount(@NotNull String account, @NotNull SubscriptionSettings settings) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return registerAccounts(CollectionsKt.listOf(new Application.AccountRequest(account, settings)));
    }

    @Override // com.vk.pushme.model.Application
    @NotNull
    public Request<SubscriptionResult> registerAccounts(@NotNull List<Application.AccountRequest> accounts) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("Accounts must not be empty");
        }
        List<Application.AccountRequest> list = accounts;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (StringsKt.isBlank(((Application.AccountRequest) it.next()).getAccount())) {
                    throw new IllegalArgumentException("Account must not be empty or blank");
                }
            }
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Application.AccountRequest accountRequest : list) {
            String lowerCase = accountRequest.getAccount().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            arrayList.add(new Subscription(lowerCase, this.name, accountRequest.getSettings().getTags(), accountRequest.getSettings().getSpecificTransports(), accountRequest.getSettings().getDeliveryTime(), accountRequest.getSettings().getExtras()));
        }
        return new NewSubscriptionRequest(this.workScheduler, arrayList, this.appendSdkDeviceId, this.subscriptionUseCase, this.subscriptionDao, this.pendingActionDao, this.coroutineScope, this.logger);
    }

    @Override // com.vk.pushme.model.Application
    @NotNull
    public Request<UnsubscribeResult> unregisterAccount(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        if (StringsKt.isBlank(account)) {
            throw new IllegalArgumentException("Account cannot be empty or blank");
        }
        return new UnsubscribeRequest(this.workScheduler, this.name, account, this.unsubscribeUseCase, this.subscriptionDao, this.pendingActionDao, this.coroutineScope, this.logger);
    }

    @Override // com.vk.pushme.model.Application
    @NotNull
    public Request<UnsubscribeResult> unregisterAllAccounts() {
        return new UnsubscribeRequest(this.workScheduler, this.name, null, this.unsubscribeUseCase, this.subscriptionDao, this.pendingActionDao, this.coroutineScope, this.logger);
    }

    @Override // com.vk.pushme.model.Application
    @WorkerThread
    public void unsubscribeByToken(@NotNull String token) throws InterruptedException {
        Intrinsics.checkNotNullParameter(token, "token");
        Utils.INSTANCE.checkNotMainThread();
        BuildersKt__BuildersKt.runBlocking$default(null, new C10731(token, null), 1, null);
    }
}
