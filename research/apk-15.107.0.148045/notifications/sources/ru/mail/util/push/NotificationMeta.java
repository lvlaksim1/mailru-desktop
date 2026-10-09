package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.content.MailItemTransactionCategory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0001!B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011¨\u0006\""}, d2 = {"Lru/mail/util/push/NotificationMeta;", "Ljava/io/Serializable;", "categoryType", "Lru/mail/logic/content/MailItemTransactionCategory;", "pushType", "Lru/mail/util/push/PushMessageType;", "isStageSmartReply", "", "isDefaultSmartReply", "hasSmartReplies", "isReminder", "<init>", "(Lru/mail/logic/content/MailItemTransactionCategory;Lru/mail/util/push/PushMessageType;ZZZZ)V", "getCategoryType", "()Lru/mail/logic/content/MailItemTransactionCategory;", "getPushType", "()Lru/mail/util/push/PushMessageType;", "()Z", "getHasSmartReplies", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class NotificationMeta implements Serializable {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 167805866091887717L;

    @NotNull
    private final MailItemTransactionCategory categoryType;
    private final boolean hasSmartReplies;
    private final boolean isDefaultSmartReply;
    private final boolean isReminder;
    private final boolean isStageSmartReply;

    @NotNull
    private final PushMessageType pushType;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lru/mail/util/push/NotificationMeta$Companion;", "", "<init>", "()V", "serialVersionUID", "", "stub", "Lru/mail/util/push/NotificationMeta;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final NotificationMeta stub() {
            return new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, PushMessageType.SINGLE_MESSAGE, false, false, false, false, 60, null);
        }

        private Companion() {
        }
    }

    public NotificationMeta(@NotNull MailItemTransactionCategory categoryType, @NotNull PushMessageType pushType, boolean z10, boolean z11, boolean z12, boolean z13) {
        Intrinsics.checkNotNullParameter(categoryType, "categoryType");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        this.categoryType = categoryType;
        this.pushType = pushType;
        this.isStageSmartReply = z10;
        this.isDefaultSmartReply = z11;
        this.hasSmartReplies = z12;
        this.isReminder = z13;
    }

    public static /* synthetic */ NotificationMeta copy$default(NotificationMeta notificationMeta, MailItemTransactionCategory mailItemTransactionCategory, PushMessageType pushMessageType, boolean z10, boolean z11, boolean z12, boolean z13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            mailItemTransactionCategory = notificationMeta.categoryType;
        }
        if ((i10 & 2) != 0) {
            pushMessageType = notificationMeta.pushType;
        }
        if ((i10 & 4) != 0) {
            z10 = notificationMeta.isStageSmartReply;
        }
        if ((i10 & 8) != 0) {
            z11 = notificationMeta.isDefaultSmartReply;
        }
        if ((i10 & 16) != 0) {
            z12 = notificationMeta.hasSmartReplies;
        }
        if ((i10 & 32) != 0) {
            z13 = notificationMeta.isReminder;
        }
        boolean z14 = z12;
        boolean z15 = z13;
        return notificationMeta.copy(mailItemTransactionCategory, pushMessageType, z10, z11, z14, z15);
    }

    @JvmStatic
    @NotNull
    public static final NotificationMeta stub() {
        return INSTANCE.stub();
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MailItemTransactionCategory getCategoryType() {
        return this.categoryType;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PushMessageType getPushType() {
        return this.pushType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsStageSmartReply() {
        return this.isStageSmartReply;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsDefaultSmartReply() {
        return this.isDefaultSmartReply;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getHasSmartReplies() {
        return this.hasSmartReplies;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsReminder() {
        return this.isReminder;
    }

    @NotNull
    public final NotificationMeta copy(@NotNull MailItemTransactionCategory categoryType, @NotNull PushMessageType pushType, boolean isStageSmartReply, boolean isDefaultSmartReply, boolean hasSmartReplies, boolean isReminder) {
        Intrinsics.checkNotNullParameter(categoryType, "categoryType");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        return new NotificationMeta(categoryType, pushType, isStageSmartReply, isDefaultSmartReply, hasSmartReplies, isReminder);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationMeta)) {
            return false;
        }
        NotificationMeta notificationMeta = (NotificationMeta) other;
        return this.categoryType == notificationMeta.categoryType && this.pushType == notificationMeta.pushType && this.isStageSmartReply == notificationMeta.isStageSmartReply && this.isDefaultSmartReply == notificationMeta.isDefaultSmartReply && this.hasSmartReplies == notificationMeta.hasSmartReplies && this.isReminder == notificationMeta.isReminder;
    }

    @NotNull
    public final MailItemTransactionCategory getCategoryType() {
        return this.categoryType;
    }

    public final boolean getHasSmartReplies() {
        return this.hasSmartReplies;
    }

    @NotNull
    public final PushMessageType getPushType() {
        return this.pushType;
    }

    public int hashCode() {
        return (((((((((this.categoryType.hashCode() * 31) + this.pushType.hashCode()) * 31) + Boolean.hashCode(this.isStageSmartReply)) * 31) + Boolean.hashCode(this.isDefaultSmartReply)) * 31) + Boolean.hashCode(this.hasSmartReplies)) * 31) + Boolean.hashCode(this.isReminder);
    }

    public final boolean isDefaultSmartReply() {
        return this.isDefaultSmartReply;
    }

    public final boolean isReminder() {
        return this.isReminder;
    }

    public final boolean isStageSmartReply() {
        return this.isStageSmartReply;
    }

    @NotNull
    public String toString() {
        return "NotificationMeta(categoryType=" + this.categoryType + ", pushType=" + this.pushType + ", isStageSmartReply=" + this.isStageSmartReply + ", isDefaultSmartReply=" + this.isDefaultSmartReply + ", hasSmartReplies=" + this.hasSmartReplies + ", isReminder=" + this.isReminder + ")";
    }

    public /* synthetic */ NotificationMeta(MailItemTransactionCategory mailItemTransactionCategory, PushMessageType pushMessageType, boolean z10, boolean z11, boolean z12, boolean z13, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(mailItemTransactionCategory, pushMessageType, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? false : z11, (i10 & 16) != 0 ? false : z12, (i10 & 32) != 0 ? false : z13);
    }
}
