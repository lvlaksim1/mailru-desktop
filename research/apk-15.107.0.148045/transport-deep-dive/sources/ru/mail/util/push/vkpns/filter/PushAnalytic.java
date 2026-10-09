package ru.mail.util.push.vkpns.filter;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lru/mail/util/push/vkpns/filter/PushAnalytic;", "", "timestamp", "", "type", "Lru/mail/util/push/PushType;", "<init>", "(JLru/mail/util/push/PushType;)V", "getTimestamp", "()J", "getType", "()Lru/mail/util/push/PushType;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushAnalytic {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String DELIMITER = "___";
    private final long timestamp;

    @NotNull
    private final PushType type;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/vkpns/filter/PushAnalytic$Companion;", "", "<init>", "()V", "DELIMITER", "", "toString", "timestamp", "", "type", "Lru/mail/util/push/PushType;", "fromString", "Lru/mail/util/push/vkpns/filter/PushAnalytic;", "serializedPushAnalytic", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPushAnalytic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushAnalytic.kt\nru/mail/util/push/vkpns/filter/PushAnalytic$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,29:1\n1#2:30\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final PushAnalytic fromString(@NotNull String serializedPushAnalytic) {
            Intrinsics.checkNotNullParameter(serializedPushAnalytic, "serializedPushAnalytic");
            List listSplit$default = StringsKt.split$default((CharSequence) serializedPushAnalytic, new String[]{PushAnalytic.DELIMITER}, false, 0, 6, (Object) null);
            if (listSplit$default.size() < 2) {
                listSplit$default = null;
            }
            if (listSplit$default != null) {
                String str = (String) CollectionsKt.getOrNull(listSplit$default, 0);
                Long longOrNull = str != null ? StringsKt.toLongOrNull(str) : null;
                String str2 = (String) CollectionsKt.getOrNull(listSplit$default, 1);
                if (longOrNull != null && str2 != null && !StringsKt.isBlank(str2)) {
                    return new PushAnalytic(longOrNull.longValue(), PushType.INSTANCE.getByAnalyticsName(str2));
                }
            }
            return null;
        }

        @NotNull
        public final String toString(long timestamp, @NotNull PushType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return timestamp + PushAnalytic.DELIMITER + type.getAnalyticsName();
        }

        private Companion() {
        }
    }

    public PushAnalytic(long j10, @NotNull PushType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.timestamp = j10;
        this.type = type;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    @NotNull
    public final PushType getType() {
        return this.type;
    }
}
