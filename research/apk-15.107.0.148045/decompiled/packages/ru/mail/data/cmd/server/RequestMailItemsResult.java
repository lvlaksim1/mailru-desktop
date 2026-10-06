package ru.mail.data.cmd.server;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public interface RequestMailItemsResult<T, V> extends RequestItemsResult<T, V> {
    long getLastModified();

    boolean isContainersPartiallyLoaded();
}
