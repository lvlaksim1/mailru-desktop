package com.vk.pushme.work.util;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/work/util/WorkScheduler;", "", "enqueue", "", "workRequest", "Landroidx/work/OneTimeWorkRequest;", "enqueueUniqueWork", "uniqueWorkName", "", "existingWorkPolicy", "Landroidx/work/ExistingWorkPolicy;", "enqueueUniquePeriodicWork", "Landroidx/work/ExistingPeriodicWorkPolicy;", "Landroidx/work/PeriodicWorkRequest;", "cancelUniqueWork", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface WorkScheduler {
    void cancelUniqueWork(@NotNull String uniqueWorkName);

    void enqueue(@NotNull OneTimeWorkRequest workRequest);

    void enqueueUniquePeriodicWork(@NotNull String uniqueWorkName, @NotNull ExistingPeriodicWorkPolicy existingWorkPolicy, @NotNull PeriodicWorkRequest workRequest);

    void enqueueUniqueWork(@NotNull String uniqueWorkName, @NotNull ExistingWorkPolicy existingWorkPolicy, @NotNull OneTimeWorkRequest workRequest);
}
