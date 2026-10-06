package ru.mail.data.cmd.server;

import java.util.Collection;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public interface RequestItemsResult<T, V> {
    Collection<V> getContainers();

    Collection<T> getMailItems();
}
