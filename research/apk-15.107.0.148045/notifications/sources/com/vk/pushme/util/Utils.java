package com.vk.pushme.util;

import android.os.Looper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/util/Utils;", "", "<init>", "()V", "checkNotMainThread", "", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Utils {

    @NotNull
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final void checkNotMainThread() throws IllegalStateException {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("It's not allowed to call this method on main thread");
        }
    }
}
