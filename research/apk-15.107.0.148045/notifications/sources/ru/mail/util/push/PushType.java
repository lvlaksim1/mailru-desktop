package ru.mail.util.push;

import com.google.android.gms.stats.CodePackage;
import com.google.firebase.messaging.FirebaseMessaging;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lru/mail/util/push/PushType;", "", "analyticsName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getAnalyticsName", "()Ljava/lang/String;", CodePackage.GCM, "HMS", "VKPNS", "STUB", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum PushType {
    GCM(FirebaseMessaging.INSTANCE_ID_SCOPE),
    HMS("HMS"),
    VKPNS("VKPNS"),
    STUB("STUB");


    @NotNull
    private final String analyticsName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lru/mail/util/push/PushType$Companion;", "", "<init>", "()V", "getByAnalyticsName", "Lru/mail/util/push/PushType;", "analyticName", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPushType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushType.kt\nru/mail/util/push/PushType$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,16:1\n1400#2,2:17\n*S KotlinDebug\n*F\n+ 1 PushType.kt\nru/mail/util/push/PushType$Companion\n*L\n13#1:17,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Code duplicated, block: B:10:0x0020  */
        /* JADX WARN: Code duplicated, block: B:12:0x0023 A[RETURN] */
        @NotNull
        public final PushType getByAnalyticsName(@NotNull String analyticName) {
            Intrinsics.checkNotNullParameter(analyticName, "analyticName");
            for (PushType pushType : PushType.values()) {
                if (Intrinsics.areEqual(pushType.getAnalyticsName(), analyticName)) {
                    if (pushType == null) {
                        return PushType.STUB;
                    }
                    return pushType;
                }
            }
            pushType = null;
            if (pushType == null) {
                return PushType.STUB;
            }
            return pushType;
        }

        private Companion() {
        }
    }

    PushType(String str) {
        this.analyticsName = str;
    }

    @NotNull
    public static EnumEntries<PushType> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getAnalyticsName() {
        return this.analyticsName;
    }
}
