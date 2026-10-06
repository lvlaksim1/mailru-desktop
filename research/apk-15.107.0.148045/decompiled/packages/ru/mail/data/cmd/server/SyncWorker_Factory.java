package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class SyncWorker_Factory {

    /* JADX INFO: compiled from: ProGuard */
    private static final class InstanceHolder {
        static final SyncWorker_Factory INSTANCE = new SyncWorker_Factory();

        private InstanceHolder() {
        }
    }

    public static SyncWorker_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SyncWorker newInstance(Context context, WorkerParameters workerParameters) {
        return new SyncWorker(context, workerParameters);
    }

    public SyncWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters);
    }
}
