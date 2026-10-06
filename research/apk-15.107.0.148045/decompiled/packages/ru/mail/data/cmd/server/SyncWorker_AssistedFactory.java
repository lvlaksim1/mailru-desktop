package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import dagger.assisted.AssistedFactory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@AssistedFactory
public interface SyncWorker_AssistedFactory extends WorkerAssistedFactory<SyncWorker> {
    @Override // androidx.hilt.work.WorkerAssistedFactory
    /* synthetic */ ListenableWorker create(Context context, WorkerParameters workerParameters);
}
