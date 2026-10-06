package ru.mail.filter.data;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.dao.ResourceObservable;
import ru.mail.data.entities.Filter;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.kit.result.tools.Result;
import ru.mail.logic.cmd.FiltersLoader;
import ru.mail.logic.content.AccessError;
import ru.mail.logic.content.CommonError;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxCommandHandlerKt;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextProvider;
import ru.mail.logic.content.impl.DataManagerChecker;
import ru.mail.network.NetworkCommand;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001BU\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\"\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010 J6\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001d2\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0#\"\u00020\u001bH\u0096@¢\u0006\u0002\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/filter/data/FiltersRepositoryImpl;", "Lru/mail/filter/data/FiltersRepository;", "appContext", "Landroid/content/Context;", "logger", "Lru/mail/util/log/Logger;", "dataManager", "Lru/mail/logic/content/DataManager;", "requestArbiter", "Lru/mail/arbiter/RequestArbiter;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "databaseRepository", "Lru/mail/filter/data/FiltersDatabaseRepository;", "memoryCacheRepository", "Lru/mail/filter/data/FiltersMemoryCacheRepository;", "mailboxContextProvider", "Lru/mail/logic/content/MailboxContextProvider;", "resourceObservable", "Lru/mail/data/dao/ResourceObservable;", "<init>", "(Landroid/content/Context;Lru/mail/util/log/Logger;Lru/mail/logic/content/DataManager;Lru/mail/arbiter/RequestArbiter;Lru/mail/analytics/MailAppAnalytics;Lru/mail/filter/data/FiltersDatabaseRepository;Lru/mail/filter/data/FiltersMemoryCacheRepository;Lru/mail/logic/content/MailboxContextProvider;Lru/mail/data/dao/ResourceObservable;)V", "getFilters", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/mail/data/entities/Filter;", "account", "", "requestFilters", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/logic/content/CommonError;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFilter", "filters", "", "(Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFiltersRepositoryImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FiltersRepositoryImpl.kt\nru/mail/filter/data/FiltersRepositoryImpl\n+ 2 Results.kt\nru/mail/kit/result/tools/Result\n*L\n1#1,134:1\n38#2,3:135\n45#2,3:138\n38#2,3:141\n45#2,3:144\n*S KotlinDebug\n*F\n+ 1 FiltersRepositoryImpl.kt\nru/mail/filter/data/FiltersRepositoryImpl\n*L\n78#1:135,3\n87#1:138,3\n106#1:141,3\n122#1:144,3\n*E\n"})
public final class FiltersRepositoryImpl implements FiltersRepository {
    public static final int $stable = 8;

    @NotNull
    private final MailAppAnalytics analytics;

    @NotNull
    private final Context appContext;

    @NotNull
    private final DataManager dataManager;

    @NotNull
    private final FiltersDatabaseRepository databaseRepository;

    @NotNull
    private final Logger logger;

    @NotNull
    private final MailboxContextProvider mailboxContextProvider;

    @NotNull
    private final FiltersMemoryCacheRepository memoryCacheRepository;

    @NotNull
    private final RequestArbiter requestArbiter;

    @NotNull
    private final ResourceObservable resourceObservable;

    /* JADX INFO: renamed from: ru.mail.filter.data.FiltersRepositoryImpl$deleteFilter$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.filter.data.FiltersRepositoryImpl", f = "FiltersRepositoryImpl.kt", i = {0, 0, 1, 1, 1, 1, 1}, l = {103, 121}, m = "deleteFilter", n = {"account", "filters", "account", "filters", "mailboxContext", "login", "folderState"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return FiltersRepositoryImpl.this.deleteFilter(null, null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Lru/mail/data/entities/Filter;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1", f = "FiltersRepositoryImpl.kt", i = {0, 0, 1, 1, 1}, l = {45, 69}, m = "invokeSuspend", n = {"$this$callbackFlow", "filtersFromCache", "$this$callbackFlow", "filtersFromCache", "observer"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 1)
    static final class C21391 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends Filter>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C21391(String str, Continuation<? super C21391> continuation) {
            super(2, continuation);
            this.$account = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0(FiltersRepositoryImpl filtersRepositoryImpl, FiltersRepositoryImpl$getFilters$1$observer$1 filtersRepositoryImpl$getFilters$1$observer$1) {
            filtersRepositoryImpl.dataManager.unregisterObserver(filtersRepositoryImpl$getFilters$1$observer$1);
            return Unit.INSTANCE;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C21391 c21391 = FiltersRepositoryImpl.this.new C21391(this.$account, continuation);
            c21391.L$0 = obj;
            return c21391;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x005a, code lost:
        
            if (r8 == r1) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00b8, code lost:
        
            if (kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r5, r7) == r1) goto L21;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, ru.mail.data.dao.ResourceObserver, ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1$observer$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r7.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L2f
                if (r2 == r4) goto L27
                if (r2 != r3) goto L1f
                java.lang.Object r0 = r7.L$2
                ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1$observer$1 r0 = (ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1$observer$1) r0
                java.lang.Object r0 = r7.L$1
                java.util.List r0 = (java.util.List) r0
                kotlin.ResultKt.throwOnFailure(r8)
                goto Lbb
            L1f:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L27:
                java.lang.Object r2 = r7.L$1
                java.util.List r2 = (java.util.List) r2
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5d
            L2f:
                kotlin.ResultKt.throwOnFailure(r8)
                ru.mail.filter.data.FiltersRepositoryImpl r8 = ru.mail.filter.data.FiltersRepositoryImpl.this
                ru.mail.filter.data.FiltersMemoryCacheRepository r8 = ru.mail.filter.data.FiltersRepositoryImpl.access$getMemoryCacheRepository$p(r8)
                java.lang.String r2 = r7.$account
                java.util.List r2 = r8.getFilters(r2)
                boolean r8 = r2.isEmpty()
                if (r8 == 0) goto L73
                ru.mail.filter.data.FiltersRepositoryImpl r8 = ru.mail.filter.data.FiltersRepositoryImpl.this
                ru.mail.filter.data.FiltersDatabaseRepository r8 = ru.mail.filter.data.FiltersRepositoryImpl.access$getDatabaseRepository$p(r8)
                java.lang.String r5 = r7.$account
                r7.L$0 = r0
                java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
                r7.L$1 = r6
                r7.label = r4
                java.lang.Object r8 = r8.getFilters(r5, r7)
                if (r8 != r1) goto L5d
                goto Lba
            L5d:
                ru.mail.kit.result.tools.Result r8 = (ru.mail.kit.result.tools.Result) r8
                boolean r4 = r8 instanceof ru.mail.kit.result.tools.Result.Success
                if (r4 == 0) goto L81
                ru.mail.kit.result.tools.Result$Success r8 = (ru.mail.kit.result.tools.Result.Success) r8
                java.lang.Object r8 = r8.getResult()
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.List r8 = kotlin.collections.CollectionsKt.toList(r8)
                r0.mo9018trySendJP2dKIU(r8)
                goto L81
            L73:
                r8 = r2
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.List r8 = kotlin.collections.CollectionsKt.toList(r8)
                java.lang.Object r8 = r0.mo9018trySendJP2dKIU(r8)
                kotlinx.coroutines.channels.ChannelResult.m14334boximpl(r8)
            L81:
                java.lang.String r8 = ru.mail.data.entities.Filter.CONTENT_TYPE
                java.lang.String[] r8 = new java.lang.String[]{r8}
                ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1$observer$1 r4 = new ru.mail.filter.data.FiltersRepositoryImpl$getFilters$1$observer$1
                ru.mail.filter.data.FiltersRepositoryImpl r5 = ru.mail.filter.data.FiltersRepositoryImpl.this
                java.lang.String r6 = r7.$account
                r4.<init>(r8)
                ru.mail.filter.data.FiltersRepositoryImpl r8 = ru.mail.filter.data.FiltersRepositoryImpl.this
                ru.mail.logic.content.DataManager r8 = ru.mail.filter.data.FiltersRepositoryImpl.access$getDataManager$p(r8)
                r8.registerObserver(r4)
                ru.mail.filter.data.FiltersRepositoryImpl r8 = ru.mail.filter.data.FiltersRepositoryImpl.this
                ru.mail.filter.data.a r5 = new ru.mail.filter.data.a
                r5.<init>()
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r7.L$0 = r8
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
                r7.L$1 = r8
                java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)
                r7.L$2 = r8
                r7.label = r3
                java.lang.Object r8 = kotlinx.coroutines.channels.ProduceKt.awaitClose(r0, r5, r7)
                if (r8 != r1) goto Lbb
            Lba:
                return r1
            Lbb:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.filter.data.FiltersRepositoryImpl.C21391.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super List<? extends Filter>> producerScope, Continuation<? super Unit> continuation) {
            return ((C21391) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.filter.data.FiltersRepositoryImpl$requestFilters$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.filter.data.FiltersRepositoryImpl", f = "FiltersRepositoryImpl.kt", i = {0, 1, 1, 1}, l = {76, 81}, m = "requestFilters", n = {"account", "account", "mailboxContext", "profile"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
    static final class C21401 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C21401(Continuation<? super C21401> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FiltersRepositoryImpl.this.requestFilters(null, this);
        }
    }

    @Inject
    public FiltersRepositoryImpl(@ApplicationContext @NotNull Context appContext, @Named("FiltersRepository") @NotNull Logger logger, @NotNull DataManager dataManager, @NotNull RequestArbiter requestArbiter, @NotNull MailAppAnalytics analytics, @NotNull FiltersDatabaseRepository databaseRepository, @NotNull FiltersMemoryCacheRepository memoryCacheRepository, @NotNull MailboxContextProvider mailboxContextProvider, @NotNull ResourceObservable resourceObservable) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(requestArbiter, "requestArbiter");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(databaseRepository, "databaseRepository");
        Intrinsics.checkNotNullParameter(memoryCacheRepository, "memoryCacheRepository");
        Intrinsics.checkNotNullParameter(mailboxContextProvider, "mailboxContextProvider");
        Intrinsics.checkNotNullParameter(resourceObservable, "resourceObservable");
        this.appContext = appContext;
        this.logger = logger;
        this.dataManager = dataManager;
        this.requestArbiter = requestArbiter;
        this.analytics = analytics;
        this.databaseRepository = databaseRepository;
        this.memoryCacheRepository = memoryCacheRepository;
        this.mailboxContextProvider = mailboxContextProvider;
        this.resourceObservable = resourceObservable;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00fa, code lost:
    
        if (r14 == r0) goto L24;
     */
    @Override // ru.mail.filter.data.FiltersRepository
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object deleteFilter(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String[] r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super ru.mail.kit.result.tools.Result<kotlin.Unit, ru.mail.logic.content.CommonError>> r14) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ru.mail.filter.data.FiltersRepositoryImpl.deleteFilter(java.lang.String, java.lang.String[], kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // ru.mail.filter.data.FiltersRepository
    @NotNull
    public Flow<List<Filter>> getFilters(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        return FlowKt.callbackFlow(new C21391(account, null));
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:30:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:32:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:34:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:36:0x00da  */
    /* JADX WARN: Code duplicated, block: B:38:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    @Override // ru.mail.filter.data.FiltersRepository
    @Nullable
    public Object requestFilters(@NotNull String str, @NotNull Continuation<? super Result<Unit, CommonError>> continuation) {
        C21401 c21401;
        MailboxProfile mailboxProfile;
        Result result;
        if (continuation instanceof C21401) {
            c21401 = (C21401) continuation;
            int i10 = c21401.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c21401.label = i10 - Integer.MIN_VALUE;
            } else {
                c21401 = new C21401(continuation);
            }
        } else {
            c21401 = new C21401(continuation);
        }
        C21401 c21402 = c21401;
        Object objRequest = c21402.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c21402.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objRequest);
            DataManagerChecker dataManagerChecker = new DataManagerChecker(this.dataManager);
            c21402.L$0 = str;
            c21402.label = 1;
            if (dataManagerChecker.check(c21402) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i11 == 1) {
            str = (String) c21402.L$0;
            ResultKt.throwOnFailure(objRequest);
        } else {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mailboxProfile = (MailboxProfile) c21402.L$2;
            ResultKt.throwOnFailure(objRequest);
        }
        result = (Result) objRequest;
        if (result instanceof Result.Success) {
            if (NetworkCommand.statusOK(((Result.Success) result).getResult())) {
                return Result.INSTANCE.failure(CommonError.Unrecoverable.INSTANCE);
            }
            this.resourceObservable.notifyResourceChanged(Filter.getContentUri(mailboxProfile.getLogin()));
            return Result.INSTANCE.success();
        }
        if (result instanceof Result.Failure) {
            throw new NoWhenBranchMatchedException();
        }
        return Result.INSTANCE.failure((CommonError) ((Result.Failure) result).getError());
        Result<MailboxContext, AccessError.AuthAccessDenied> mailboxContext = this.mailboxContextProvider.getMailboxContext(str);
        if (!(mailboxContext instanceof Result.Success)) {
            if (!(mailboxContext instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            AccessError.AuthAccessDenied authAccessDenied = (AccessError.AuthAccessDenied) ((Result.Failure) mailboxContext).getError();
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull(authAccessDenied);
            return companion.failure(new CommonError.Access(authAccessDenied));
        }
        MailboxContext mailboxContext2 = (MailboxContext) ((Result.Success) mailboxContext).getResult();
        MailboxProfile profile = mailboxContext2.getProfile();
        FiltersLoader filtersLoader = new FiltersLoader(this.appContext, mailboxContext2);
        Logger logger = this.logger;
        RequestArbiter requestArbiter = this.requestArbiter;
        Intrinsics.checkNotNull(profile);
        DataManager dataManager = this.dataManager;
        MailAppAnalytics mailAppAnalytics = this.analytics;
        c21402.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c21402.L$1 = SpillingKt.nullOutSpilledVariable(mailboxContext2);
        c21402.L$2 = profile;
        c21402.label = 2;
        objRequest = MailboxCommandHandlerKt.request(filtersLoader, logger, requestArbiter, profile, dataManager, mailAppAnalytics, c21402);
        if (objRequest != coroutine_suspended) {
            mailboxProfile = profile;
            result = (Result) objRequest;
            if (result instanceof Result.Success) {
                if (NetworkCommand.statusOK(((Result.Success) result).getResult())) {
                    return Result.INSTANCE.failure(CommonError.Unrecoverable.INSTANCE);
                }
                this.resourceObservable.notifyResourceChanged(Filter.getContentUri(mailboxProfile.getLogin()));
                return Result.INSTANCE.success();
            }
            if (result instanceof Result.Failure) {
                throw new NoWhenBranchMatchedException();
            }
            return Result.INSTANCE.failure((CommonError) ((Result.Failure) result).getError());
        }
        return coroutine_suspended;
    }
}
