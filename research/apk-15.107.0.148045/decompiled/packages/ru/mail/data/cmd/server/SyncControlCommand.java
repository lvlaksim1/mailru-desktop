package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.mailbox.cmd.Command;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class SyncControlCommand<P, V> extends Command<P, V> {
    protected final Context mContext;

    public SyncControlCommand(P p10, Context context) {
        super(p10);
        this.mContext = context;
    }
}
