package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.hilt.work.HiltWorker;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.io.File;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.Configuration;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.logic.content.impl.CreateLogsArchiveUseCase;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.util.log.Logger;
import ru.mail.utils.FileUtils;
import ru.mail.utils.SafetyDependenciesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@HiltWorker
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%Bm\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u001b\u001a\u00020\u001cH\u0096@¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u001e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010$R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lru/mail/util/push/CreateSnapshotLogsWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "createLogsArchiveUseCaseProvider", "Ljavax/inject/Provider;", "Lru/mail/logic/content/impl/CreateLogsArchiveUseCase;", "configurationProvider", "Lru/mail/config/Configuration;", "workSchedulerProvider", "Lru/mail/march/internal/work/WorkScheduler;", "analyticsProvider", "Lru/mail/analytics/MailAppAnalytics;", "internalStorageProvider", "Lru/mail/util/push/InternalStorageProvider;", "provider", "Lru/mail/utils/SafetyDependenciesProvider;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Lru/mail/util/push/InternalStorageProvider;Lru/mail/utils/SafetyDependenciesProvider;Lru/mail/util/log/Logger;)V", "getLogger", "()Lru/mail/util/log/Logger;", "logger$delegate", "Lkotlin/Lazy;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "prepareDir", "Ljava/io/File;", "scheduleUploadFile", "", "file", "analytics", "(Ljava/io/File;Lru/mail/analytics/MailAppAnalytics;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CreateSnapshotLogsWorker extends CoroutineWorker {

    @NotNull
    private static final String LOGS_SNAPSHOTS_DIR = "LOGS_SNAPSHOTS_DIR";

    @NotNull
    private static final String LOG_TAG = "CreateSnapshotLogsWorker";
    private static final int MAX_RETRIES = 3;

    @NotNull
    public static final String UNIQUE_ID = "CreateSnapshotLogsWorker";

    @NotNull
    private final Provider<MailAppAnalytics> analyticsProvider;

    @NotNull
    private final Provider<Configuration> configurationProvider;

    @NotNull
    private final Provider<CreateLogsArchiveUseCase> createLogsArchiveUseCaseProvider;

    @NotNull
    private final InternalStorageProvider internalStorageProvider;

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    @NotNull
    private final SafetyDependenciesProvider provider;

    @NotNull
    private final Provider<WorkScheduler> workSchedulerProvider;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: ru.mail.util.push.CreateSnapshotLogsWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.CreateSnapshotLogsWorker", f = "CreateSnapshotLogsWorker.kt", i = {}, l = {36}, m = "doWork", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return CreateSnapshotLogsWorker.this.doWork(this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.util.push.CreateSnapshotLogsWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f0\u0001¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lorg/jspecify/annotations/NonNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.CreateSnapshotLogsWorker$doWork$2", f = "CreateSnapshotLogsWorker.kt", i = {1, 2, 2, 3, 3, 4, 4, 4, 4}, l = {37, 41, 57, 57, 91}, m = "invokeSuspend", n = {"analytics", "analytics", "isSendLogsByPushEnabled", "analytics", "isSendLogsByPushEnabled", "analytics", "logsArchive", "logsSnapshot", "isSendLogsByPushEnabled"}, s = {"L$0", "L$0", "Z$0", "L$0", "Z$0", "L$0", "L$1", "L$2", "Z$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return CreateSnapshotLogsWorker.this.new AnonymousClass2(continuation);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x0096  */
        /* JADX WARN: Code duplicated, block: B:27:0x00a9  */
        /* JADX WARN: Code duplicated, block: B:29:0x00b1  */
        /* JADX WARN: Code duplicated, block: B:31:0x00c4  */
        /* JADX WARN: Code duplicated, block: B:34:0x00e9  */
        /* JADX WARN: Code duplicated, block: B:38:0x00fc  */
        /* JADX WARN: Code duplicated, block: B:41:0x0101  */
        /* JADX WARN: Code duplicated, block: B:43:0x0114  */
        /* JADX WARN: Code duplicated, block: B:46:0x014c A[Catch: Exception -> 0x015d, TryCatch #0 {Exception -> 0x015d, blocks: (B:44:0x0146, B:46:0x014c, B:50:0x015f), top: B:59:0x0146 }] */
        /* JADX WARN: Code duplicated, block: B:50:0x015f A[Catch: Exception -> 0x015d, TRY_LEAVE, TryCatch #0 {Exception -> 0x015d, blocks: (B:44:0x0146, B:46:0x014c, B:50:0x015f), top: B:59:0x0146 }] */
        /* JADX WARN: Code duplicated, block: B:54:0x019b  */
        /* JADX WARN: Instruction removed from duplicated block: B:43:0x0114, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:50:0x015f, please report this as an issue */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MailAppAnalytics mailAppAnalytics;
            boolean sendLogsByPushEnabled;
            Object objInvoke;
            boolean z10;
            MailAppAnalytics mailAppAnalytics2;
            File file;
            File file2;
            CreateSnapshotLogsWorker createSnapshotLogsWorker;
            MailAppAnalytics mailAppAnalytics3;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                SafetyDependenciesProvider safetyDependenciesProvider = CreateSnapshotLogsWorker.this.provider;
                Provider provider = CreateSnapshotLogsWorker.this.analyticsProvider;
                this.label = 1;
                obj = safetyDependenciesProvider.invoke(provider, this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i10 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i10 == 2) {
                    MailAppAnalytics mailAppAnalytics4 = (MailAppAnalytics) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mailAppAnalytics = mailAppAnalytics4;
                    sendLogsByPushEnabled = ((Configuration) obj).getSendLogsByPushEnabled();
                    if (!sendLogsByPushEnabled) {
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Sending logs disabled :(", null, 2, null);
                        mailAppAnalytics.sendSnapshotLogsWorkerScheduledButDisabledInConfig();
                        return ListenableWorker.Result.success();
                    }
                    if (CreateSnapshotLogsWorker.this.getRunAttemptCount() > 3) {
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "runAttemptCount > 3, return Result.failure()", null, 2, null);
                        mailAppAnalytics.sendSnapshotLogsWorkerRetryLimitExceed();
                        return ListenableWorker.Result.failure();
                    }
                    Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Sending logs enabled. Start making archive", null, 2, null);
                    SafetyDependenciesProvider safetyDependenciesProvider2 = CreateSnapshotLogsWorker.this.provider;
                    Provider provider2 = CreateSnapshotLogsWorker.this.createLogsArchiveUseCaseProvider;
                    this.L$0 = mailAppAnalytics;
                    this.Z$0 = sendLogsByPushEnabled;
                    this.label = 3;
                    objInvoke = safetyDependenciesProvider2.invoke(provider2, this);
                    if (objInvoke != coroutine_suspended) {
                        z10 = sendLogsByPushEnabled;
                        obj = objInvoke;
                        this.L$0 = mailAppAnalytics;
                        this.Z$0 = z10;
                        this.label = 4;
                        obj = ((CreateLogsArchiveUseCase) obj).invoke(this);
                        if (obj != coroutine_suspended) {
                            mailAppAnalytics2 = mailAppAnalytics;
                            file = (File) obj;
                            if (file == null) {
                                Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Error occurred while logs archive creating, so logsArchive is null", null, 2, null);
                                mailAppAnalytics2.sendSnapshotLogsWorkerError("Error occurred while logs archive creating, so logsArchive is null");
                                return ListenableWorker.Result.retry();
                            }
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs archive created successfully", null, 2, null);
                            file2 = new File(CreateSnapshotLogsWorker.this.prepareDir().getAbsolutePath() + "/" + file.getName());
                            if (!FileUtils.copyFile(file, file2)) {
                                Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Exception occurred while moving file", null, 2, null);
                                mailAppAnalytics2.sendSnapshotLogsWorkerError("Exception occurred while moving file");
                                return ListenableWorker.Result.retry();
                            }
                            file.delete();
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs snapshot created successfully and moved to " + file2.getAbsolutePath(), null, 2, null);
                            createSnapshotLogsWorker = CreateSnapshotLogsWorker.this;
                            this.L$0 = mailAppAnalytics2;
                            this.L$1 = SpillingKt.nullOutSpilledVariable(file);
                            this.L$2 = SpillingKt.nullOutSpilledVariable(file2);
                            this.Z$0 = z10;
                            this.label = 5;
                            if (createSnapshotLogsWorker.scheduleUploadFile(file2, mailAppAnalytics2, this) != coroutine_suspended) {
                                mailAppAnalytics3 = mailAppAnalytics2;
                            }
                        }
                    }
                    return coroutine_suspended;
                }
                if (i10 == 3) {
                    z10 = this.Z$0;
                    mailAppAnalytics = (MailAppAnalytics) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    this.L$0 = mailAppAnalytics;
                    this.Z$0 = z10;
                    this.label = 4;
                    obj = ((CreateLogsArchiveUseCase) obj).invoke(this);
                    if (obj != coroutine_suspended) {
                        mailAppAnalytics2 = mailAppAnalytics;
                        file = (File) obj;
                        if (file == null) {
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Error occurred while logs archive creating, so logsArchive is null", null, 2, null);
                            mailAppAnalytics2.sendSnapshotLogsWorkerError("Error occurred while logs archive creating, so logsArchive is null");
                            return ListenableWorker.Result.retry();
                        }
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs archive created successfully", null, 2, null);
                        file2 = new File(CreateSnapshotLogsWorker.this.prepareDir().getAbsolutePath() + "/" + file.getName());
                        if (!FileUtils.copyFile(file, file2)) {
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Exception occurred while moving file", null, 2, null);
                            mailAppAnalytics2.sendSnapshotLogsWorkerError("Exception occurred while moving file");
                            return ListenableWorker.Result.retry();
                        }
                        file.delete();
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs snapshot created successfully and moved to " + file2.getAbsolutePath(), null, 2, null);
                        createSnapshotLogsWorker = CreateSnapshotLogsWorker.this;
                        this.L$0 = mailAppAnalytics2;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(file);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(file2);
                        this.Z$0 = z10;
                        this.label = 5;
                        if (createSnapshotLogsWorker.scheduleUploadFile(file2, mailAppAnalytics2, this) != coroutine_suspended) {
                            mailAppAnalytics3 = mailAppAnalytics2;
                        }
                    }
                    return coroutine_suspended;
                }
                if (i10 == 4) {
                    z10 = this.Z$0;
                    mailAppAnalytics2 = (MailAppAnalytics) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    file = (File) obj;
                    if (file == null) {
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Error occurred while logs archive creating, so logsArchive is null", null, 2, null);
                        mailAppAnalytics2.sendSnapshotLogsWorkerError("Error occurred while logs archive creating, so logsArchive is null");
                        return ListenableWorker.Result.retry();
                    }
                    Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs archive created successfully", null, 2, null);
                    file2 = new File(CreateSnapshotLogsWorker.this.prepareDir().getAbsolutePath() + "/" + file.getName());
                    try {
                        if (!FileUtils.copyFile(file, file2)) {
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Exception occurred while moving file", null, 2, null);
                            mailAppAnalytics2.sendSnapshotLogsWorkerError("Exception occurred while moving file");
                            return ListenableWorker.Result.retry();
                        }
                        file.delete();
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs snapshot created successfully and moved to " + file2.getAbsolutePath(), null, 2, null);
                        createSnapshotLogsWorker = CreateSnapshotLogsWorker.this;
                        this.L$0 = mailAppAnalytics2;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(file);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(file2);
                        this.Z$0 = z10;
                        this.label = 5;
                        if (createSnapshotLogsWorker.scheduleUploadFile(file2, mailAppAnalytics2, this) != coroutine_suspended) {
                            mailAppAnalytics3 = mailAppAnalytics2;
                        }
                        return coroutine_suspended;
                    } catch (Exception e10) {
                        CreateSnapshotLogsWorker.this.getLogger().d("Exception occurred while creating logs snapshot", e10);
                        mailAppAnalytics2.sendSnapshotLogsWorkerError("Exception occurred while creating logs snapshot" + e10);
                        return ListenableWorker.Result.retry();
                    }
                }
                if (i10 != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mailAppAnalytics3 = (MailAppAnalytics) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mailAppAnalytics3.sendSnapshotLogsWorkerFinishedSuccessfully();
            return ListenableWorker.Result.success();
            MailAppAnalytics mailAppAnalytics5 = (MailAppAnalytics) obj;
            mailAppAnalytics5.sendSnapshotLogsWorkerStarted();
            SafetyDependenciesProvider safetyDependenciesProvider3 = CreateSnapshotLogsWorker.this.provider;
            Provider provider3 = CreateSnapshotLogsWorker.this.configurationProvider;
            this.L$0 = mailAppAnalytics5;
            this.label = 2;
            Object objInvoke2 = safetyDependenciesProvider3.invoke(provider3, this);
            if (objInvoke2 != coroutine_suspended) {
                mailAppAnalytics = mailAppAnalytics5;
                obj = objInvoke2;
                sendLogsByPushEnabled = ((Configuration) obj).getSendLogsByPushEnabled();
                if (!sendLogsByPushEnabled) {
                    Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Sending logs disabled :(", null, 2, null);
                    mailAppAnalytics.sendSnapshotLogsWorkerScheduledButDisabledInConfig();
                    return ListenableWorker.Result.success();
                }
                if (CreateSnapshotLogsWorker.this.getRunAttemptCount() > 3) {
                    Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "runAttemptCount > 3, return Result.failure()", null, 2, null);
                    mailAppAnalytics.sendSnapshotLogsWorkerRetryLimitExceed();
                    return ListenableWorker.Result.failure();
                }
                Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Sending logs enabled. Start making archive", null, 2, null);
                SafetyDependenciesProvider safetyDependenciesProvider4 = CreateSnapshotLogsWorker.this.provider;
                Provider provider4 = CreateSnapshotLogsWorker.this.createLogsArchiveUseCaseProvider;
                this.L$0 = mailAppAnalytics;
                this.Z$0 = sendLogsByPushEnabled;
                this.label = 3;
                objInvoke = safetyDependenciesProvider4.invoke(provider4, this);
                if (objInvoke != coroutine_suspended) {
                    z10 = sendLogsByPushEnabled;
                    obj = objInvoke;
                    this.L$0 = mailAppAnalytics;
                    this.Z$0 = z10;
                    this.label = 4;
                    obj = ((CreateLogsArchiveUseCase) obj).invoke(this);
                    if (obj != coroutine_suspended) {
                        mailAppAnalytics2 = mailAppAnalytics;
                        file = (File) obj;
                        if (file == null) {
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Error occurred while logs archive creating, so logsArchive is null", null, 2, null);
                            mailAppAnalytics2.sendSnapshotLogsWorkerError("Error occurred while logs archive creating, so logsArchive is null");
                            return ListenableWorker.Result.retry();
                        }
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs archive created successfully", null, 2, null);
                        file2 = new File(CreateSnapshotLogsWorker.this.prepareDir().getAbsolutePath() + "/" + file.getName());
                        if (!FileUtils.copyFile(file, file2)) {
                            Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Exception occurred while moving file", null, 2, null);
                            mailAppAnalytics2.sendSnapshotLogsWorkerError("Exception occurred while moving file");
                            return ListenableWorker.Result.retry();
                        }
                        file.delete();
                        Logger.d$default(CreateSnapshotLogsWorker.this.getLogger(), "Logs snapshot created successfully and moved to " + file2.getAbsolutePath(), null, 2, null);
                        createSnapshotLogsWorker = CreateSnapshotLogsWorker.this;
                        this.L$0 = mailAppAnalytics2;
                        this.L$1 = SpillingKt.nullOutSpilledVariable(file);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(file2);
                        this.Z$0 = z10;
                        this.label = 5;
                        if (createSnapshotLogsWorker.scheduleUploadFile(file2, mailAppAnalytics2, this) != coroutine_suspended) {
                            mailAppAnalytics3 = mailAppAnalytics2;
                            mailAppAnalytics3.sendSnapshotLogsWorkerFinishedSuccessfully();
                            return ListenableWorker.Result.success();
                        }
                    }
                }
            }
            return coroutine_suspended;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.util.push.CreateSnapshotLogsWorker$scheduleUploadFile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.CreateSnapshotLogsWorker", f = "CreateSnapshotLogsWorker.kt", i = {0, 0, 0, 0}, l = {120}, m = "scheduleUploadFile", n = {"file", "analytics", "params", Event.Companion.Network.Fail.REQUEST_TAG}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 1)
    static final class C28611 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C28611(Continuation<? super C28611> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CreateSnapshotLogsWorker.this.scheduleUploadFile(null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public CreateSnapshotLogsWorker(@Assisted @NotNull Context context, @Assisted @NotNull WorkerParameters workerParameters, @NotNull Provider<CreateLogsArchiveUseCase> createLogsArchiveUseCaseProvider, @NotNull Provider<Configuration> configurationProvider, @NotNull Provider<WorkScheduler> workSchedulerProvider, @NotNull Provider<MailAppAnalytics> analyticsProvider, @NotNull InternalStorageProvider internalStorageProvider, @NotNull SafetyDependenciesProvider provider, @NotNull final Logger logger) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        Intrinsics.checkNotNullParameter(createLogsArchiveUseCaseProvider, "createLogsArchiveUseCaseProvider");
        Intrinsics.checkNotNullParameter(configurationProvider, "configurationProvider");
        Intrinsics.checkNotNullParameter(workSchedulerProvider, "workSchedulerProvider");
        Intrinsics.checkNotNullParameter(analyticsProvider, "analyticsProvider");
        Intrinsics.checkNotNullParameter(internalStorageProvider, "internalStorageProvider");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.createLogsArchiveUseCaseProvider = createLogsArchiveUseCaseProvider;
        this.configurationProvider = configurationProvider;
        this.workSchedulerProvider = workSchedulerProvider;
        this.analyticsProvider = analyticsProvider;
        this.internalStorageProvider = internalStorageProvider;
        this.provider = provider;
        this.logger = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CreateSnapshotLogsWorker.logger_delegate$lambda$0(logger);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0(Logger logger) {
        return logger.createLogger("CreateSnapshotLogsWorker");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File prepareDir() {
        File file = new File(this.internalStorageProvider.provide().getAbsolutePath() + "/LOGS_SNAPSHOTS_DIR");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object scheduleUploadFile(File file, MailAppAnalytics mailAppAnalytics, Continuation<? super Unit> continuation) {
        C28611 c28611;
        WorkRequest workRequest;
        if (continuation instanceof C28611) {
            c28611 = (C28611) continuation;
            int i10 = c28611.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c28611.label = i10 - Integer.MIN_VALUE;
            } else {
                c28611 = new C28611(continuation);
            }
        } else {
            c28611 = new C28611(continuation);
        }
        Object objInvoke = c28611.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c28611.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objInvoke);
            UploadLogsWorker.Params params = new UploadLogsWorker.Params();
            String absolutePath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            params.setFilePath(absolutePath);
            WorkRequest request = new WorkRequest.Builder(UploadLogsWorker.class, UploadLogsWorker.UNIQUE_ID).data(params.toData()).constraints(WorkRequest.Constraints.NETWORK).getRequest();
            SafetyDependenciesProvider safetyDependenciesProvider = this.provider;
            Provider<WorkScheduler> provider = this.workSchedulerProvider;
            c28611.L$0 = SpillingKt.nullOutSpilledVariable(file);
            c28611.L$1 = mailAppAnalytics;
            c28611.L$2 = SpillingKt.nullOutSpilledVariable(params);
            c28611.L$3 = request;
            c28611.label = 1;
            objInvoke = safetyDependenciesProvider.invoke(provider, c28611);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            workRequest = request;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            workRequest = (WorkRequest) c28611.L$3;
            mailAppAnalytics = (MailAppAnalytics) c28611.L$1;
            ResultKt.throwOnFailure(objInvoke);
        }
        ((WorkScheduler) objInvoke).schedule(workRequest);
        mailAppAnalytics.sendUploadLogsWorkerScheduled();
        Logger.d$default(getLogger(), "Upload logs file scheduled", null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    @Nullable
    public Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
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
            CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            anonymousClass1.label = 1;
            objWithContext = BuildersKt.withContext(completableJobSupervisorJob$default, anonymousClass2, anonymousClass1);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objWithContext);
        }
        Intrinsics.checkNotNullExpressionValue(objWithContext, "withContext(...)");
        return objWithContext;
    }
}
