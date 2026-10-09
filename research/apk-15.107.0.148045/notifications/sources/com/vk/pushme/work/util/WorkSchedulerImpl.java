package com.vk.pushme.work.util;

import android.content.Context;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.vk.pushme.work.util.WorkSchedulerImpl;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Lcom/vk/pushme/work/util/WorkSchedulerImpl;", "Lcom/vk/pushme/work/util/WorkScheduler;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "workManager", "Landroidx/work/WorkManager;", "getWorkManager", "()Landroidx/work/WorkManager;", "workManager$delegate", "Lkotlin/Lazy;", "enqueue", "", "workRequest", "Landroidx/work/OneTimeWorkRequest;", "enqueueUniqueWork", "uniqueWorkName", "", "existingWorkPolicy", "Landroidx/work/ExistingWorkPolicy;", "enqueueUniquePeriodicWork", "Landroidx/work/ExistingPeriodicWorkPolicy;", "Landroidx/work/PeriodicWorkRequest;", "cancelUniqueWork", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WorkSchedulerImpl implements WorkScheduler {

    /* JADX INFO: renamed from: workManager$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy workManager;

    public WorkSchedulerImpl(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.workManager = LazyKt.lazy(new Function0() { // from class: j5.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WorkSchedulerImpl.workManager_delegate$lambda$0(context);
            }
        });
    }

    private final WorkManager getWorkManager() {
        return (WorkManager) this.workManager.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WorkManager workManager_delegate$lambda$0(Context context) {
        return WorkManager.INSTANCE.getInstance(context);
    }

    @Override // com.vk.pushme.work.util.WorkScheduler
    public void cancelUniqueWork(@NotNull String uniqueWorkName) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        getWorkManager().cancelUniqueWork(uniqueWorkName);
    }

    @Override // com.vk.pushme.work.util.WorkScheduler
    public void enqueue(@NotNull OneTimeWorkRequest workRequest) {
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        getWorkManager().enqueue(workRequest);
    }

    @Override // com.vk.pushme.work.util.WorkScheduler
    public void enqueueUniquePeriodicWork(@NotNull String uniqueWorkName, @NotNull ExistingPeriodicWorkPolicy existingWorkPolicy, @NotNull PeriodicWorkRequest workRequest) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        getWorkManager().enqueueUniquePeriodicWork(uniqueWorkName, existingWorkPolicy, workRequest);
    }

    @Override // com.vk.pushme.work.util.WorkScheduler
    public void enqueueUniqueWork(@NotNull String uniqueWorkName, @NotNull ExistingWorkPolicy existingWorkPolicy, @NotNull OneTimeWorkRequest workRequest) {
        Intrinsics.checkNotNullParameter(uniqueWorkName, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(workRequest, "workRequest");
        getWorkManager().enqueueUniqueWork(uniqueWorkName, existingWorkPolicy, workRequest);
    }
}
