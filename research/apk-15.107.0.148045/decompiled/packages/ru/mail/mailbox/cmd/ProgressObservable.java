package ru.mail.mailbox.cmd;

import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface ProgressObservable<T> {
    void addObserver(ProgressListener<T> progressListener);

    List<ProgressListener<T>> getObservers();

    void notifyObservers(T t10);

    void removeObserver(ProgressListener<T> progressListener);
}
