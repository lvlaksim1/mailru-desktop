package com.vk.pushme.logic;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\f"}, d2 = {"Lcom/vk/pushme/logic/ShrinkPushTableStrategy;", "", "<init>", "()V", "getBoundaryDate", "", "totalPushesCount", "(J)Ljava/lang/Long;", "subtractFromNow", "days", "", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ShrinkPushTableStrategy {
    private static final int KEEP_1000_PUSHES_PERIOD_IN_DAYS = 2;
    private static final int KEEP_100_PUSHES_PERIOD_IN_DAYS = 10;
    private static final int KEEP_500_PUSHES_PERIOD_IN_DAYS = 5;
    private static final int KEEP_MORE_THAN_1000_PUSHES_PERIOD_IN_DAYS = 1;

    private final long subtractFromNow(int days) {
        return System.currentTimeMillis() - TimeUnit.DAYS.toMillis(days);
    }

    @Nullable
    public final Long getBoundaryDate(long totalPushesCount) {
        if (totalPushesCount < 10) {
            return null;
        }
        if (totalPushesCount < 100) {
            return Long.valueOf(subtractFromNow(10));
        }
        if (totalPushesCount < 500) {
            return Long.valueOf(subtractFromNow(5));
        }
        return totalPushesCount < 1000 ? Long.valueOf(subtractFromNow(2)) : Long.valueOf(subtractFromNow(1));
    }
}
