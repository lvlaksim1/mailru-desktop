package ru.mail.util.push;

import android.content.Context;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import dagger.assisted.AssistedFactory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AssistedFactory
public interface CreateSnapshotLogsWorker_AssistedFactory extends WorkerAssistedFactory<CreateSnapshotLogsWorker> {
    @Override // androidx.hilt.work.WorkerAssistedFactory
    /* synthetic */ ListenableWorker create(Context context, WorkerParameters workerParameters);
}
