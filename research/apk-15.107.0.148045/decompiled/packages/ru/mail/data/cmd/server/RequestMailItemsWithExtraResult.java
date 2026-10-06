package ru.mail.data.cmd.server;

import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public interface RequestMailItemsWithExtraResult<T, V, E, S> extends RequestMailItemsResult<T, V> {
    List<E> getContainerExtra();

    List<S> getSecondContainerExtra();
}
