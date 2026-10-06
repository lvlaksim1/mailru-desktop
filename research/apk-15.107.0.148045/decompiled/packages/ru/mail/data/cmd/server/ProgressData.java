package ru.mail.data.cmd.server;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class ProgressData {
    final long allSize;
    final long progress;

    public ProgressData(long j10, long j11) {
        this.progress = j10;
        this.allSize = j11;
    }

    public long getProgress() {
        return this.progress;
    }

    public long getTotalSize() {
        return this.allSize;
    }
}
