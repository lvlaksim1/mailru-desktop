package ru.mail.util.push;

import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public interface PushMessageVisitable {
    ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor);
}
