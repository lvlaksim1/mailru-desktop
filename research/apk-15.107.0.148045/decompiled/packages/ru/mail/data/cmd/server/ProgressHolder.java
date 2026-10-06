package ru.mail.data.cmd.server;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class ProgressHolder {
    private final long allSize;
    private final String attachName;
    private final Boolean decryptingProgress;
    private final long progress;

    public ProgressHolder(long j10, long j11, String str, Boolean bool) {
        this.progress = j10;
        this.allSize = j11;
        this.attachName = str;
        this.decryptingProgress = bool;
    }

    public long getAllSize() {
        return this.allSize;
    }

    public String getAttachName() {
        return this.attachName;
    }

    public Boolean getDecryptingProgress() {
        return this.decryptingProgress;
    }

    public long getProgress() {
        return this.progress;
    }
}
