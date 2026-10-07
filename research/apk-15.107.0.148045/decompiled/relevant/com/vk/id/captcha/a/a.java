package com.vk.id.captcha.a;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\bR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\t8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\n"}, d2 = {"Lcom/vk/id/captcha/a/a;", "", "", "p0", "p1", "", "a", "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/ConcurrentHashMap;", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class a {

    @NotNull
    private final ConcurrentHashMap<String, String> a = new ConcurrentHashMap<>();

    public final void a(@NotNull String p10, @NotNull String p11) {
        Intrinsics.checkNotNullParameter(p10, "");
        Intrinsics.checkNotNullParameter(p11, "");
        this.a.put(p10, p11);
    }

    @Nullable
    public final String a(@NotNull String p10) {
        Intrinsics.checkNotNullParameter(p10, "");
        return this.a.get(p10);
    }
}
