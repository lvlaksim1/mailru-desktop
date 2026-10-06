package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public abstract class NullCheckMapper<R, T> implements ObservableFuture.Mapper<R, T> {
    @Override // ru.mail.mailbox.cmd.ObservableFuture.Mapper
    public T map(R r10) {
        if (r10 != null) {
            return mapNonNullValue(r10);
        }
        return null;
    }

    public abstract T mapNonNullValue(@NonNull R r10);
}
