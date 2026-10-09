package ru.mail.util.push.gcm;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/gcm/AsserterThrowable;", "", "handleThrowable", "", "thread", "Ljava/lang/Thread;", OkListenerKt.KEY_EXCEPTION, "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AsserterThrowable {
    boolean handleThrowable(@NotNull Thread thread, @NotNull Throwable exception);
}
