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
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.ExtensionsKt;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.DatabaseCommandBase;
import ru.mail.data.cmd.database.GetFiltersCommand;
import ru.mail.data.entities.Filter;
import ru.mail.kit.result.tools.Result;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u000e0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/filter/data/FiltersDatabaseRepositoryImpl;", "Lru/mail/filter/data/FiltersDatabaseRepository;", "logger", "Lru/mail/util/log/Logger;", "appContext", "Landroid/content/Context;", "requestArbiter", "Lru/mail/arbiter/RequestArbiter;", "<init>", "(Lru/mail/util/log/Logger;Landroid/content/Context;Lru/mail/arbiter/RequestArbiter;)V", "getFilters", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/data/entities/Filter;", "", "account", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFiltersDatabaseRepositoryImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FiltersDatabaseRepositoryImpl.kt\nru/mail/filter/data/FiltersDatabaseRepositoryImpl\n+ 2 Extensions.kt\nru/mail/data/ExtensionsKt\n+ 3 Results.kt\nru/mail/kit/result/tools/Result\n*L\n1#1,23:1\n64#2:24\n65#2,13:28\n38#3,3:25\n*S KotlinDebug\n*F\n+ 1 FiltersDatabaseRepositoryImpl.kt\nru/mail/filter/data/FiltersDatabaseRepositoryImpl\n*L\n21#1:24\n21#1:28,13\n21#1:25,3\n*E\n"})
public final class FiltersDatabaseRepositoryImpl implements FiltersDatabaseRepository {
    public static final int $stable = 8;

    @NotNull
    private final Context appContext;

    @NotNull
    private final Logger logger;

    @NotNull
    private final RequestArbiter requestArbiter;

    /* JADX INFO: renamed from: ru.mail.filter.data.FiltersDatabaseRepositoryImpl$getFilters$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.filter.data.FiltersDatabaseRepositoryImpl", f = "FiltersDatabaseRepositoryImpl.kt", i = {0, 0, 0, 0, 0}, l = {24}, m = "getFilters", n = {"account", "$this$requestList$iv", "logger$iv", "executorSelector$iv", "$i$f$requestList"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return FiltersDatabaseRepositoryImpl.this.getFilters(null, this);
        }
    }

    @Inject
    public FiltersDatabaseRepositoryImpl(@Named("FiltersDatabaseRepository") @NotNull Logger logger, @ApplicationContext @NotNull Context appContext, @NotNull RequestArbiter requestArbiter) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(requestArbiter, "requestArbiter");
        this.logger = logger;
        this.appContext = appContext;
        this.requestArbiter = requestArbiter;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // ru.mail.filter.data.FiltersDatabaseRepository
    @Nullable
    public Object getFilters(@NotNull String str, @NotNull Continuation<? super Result<List<Filter>, Throwable>> continuation) {
        AnonymousClass1 anonymousClass1;
        DatabaseCommandBase databaseCommandBase;
        Logger logger;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            GetFiltersCommand getFiltersCommand = new GetFiltersCommand(this.appContext, str);
            Logger logger2 = this.logger;
            RequestArbiter requestArbiter = this.requestArbiter;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = getFiltersCommand;
            anonymousClass1.L$2 = logger2;
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(requestArbiter);
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 1;
            Object objRequest = ExtensionsKt.request(getFiltersCommand, logger2, requestArbiter, anonymousClass1);
            if (objRequest == coroutine_suspended) {
                return coroutine_suspended;
            }
            databaseCommandBase = getFiltersCommand;
            obj = objRequest;
            logger = logger2;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            logger = (Logger) anonymousClass1.L$2;
            databaseCommandBase = (DatabaseCommandBase) anonymousClass1.L$1;
            ResultKt.throwOnFailure(obj);
        }
        Result result = (Result) obj;
        if (!(result instanceof Result.Success)) {
            if (!(result instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            return Result.INSTANCE.failure((Throwable) ((Result.Failure) result).getError());
        }
        List list = ((AsyncDbHandler.CommonResponse) ((Result.Success) result).getResult()).getList();
        if (list != null) {
            return Result.INSTANCE.success(list);
        }
        IllegalStateException illegalStateException = new IllegalStateException("CommonResponse list parameter is null");
        logger.error("Database command " + databaseCommandBase.getClass().getSimpleName() + " result isn't a list", illegalStateException);
        return Result.INSTANCE.failure(illegalStateException);
    }
}
