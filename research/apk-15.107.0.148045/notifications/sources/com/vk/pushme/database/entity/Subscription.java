package com.vk.pushme.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.uikit.dialog.TimePickerDialog;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Entity(indices = {@Index(unique = true, value = {"application", "account"})}, tableName = "subscription")
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/vk/pushme/database/entity/Subscription;", "", "id", "", "account", "", "application", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "", "", "includeTransports", "excludeTransports", "deliveryTime", "Lcom/vk/pushme/database/entity/Subscription$DeliveryTime;", PushProcessor.DATAKEY_EXTRAS, "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lcom/vk/pushme/database/entity/Subscription$DeliveryTime;Ljava/lang/String;)V", "getId", "()J", "getAccount", "()Ljava/lang/String;", "getApplication", "getTags", "()Ljava/util/Set;", "getIncludeTransports", "getExcludeTransports", "getDeliveryTime", "()Lcom/vk/pushme/database/entity/Subscription$DeliveryTime;", "getExtras", "DeliveryTime", "TimePoint", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Subscription {

    @ColumnInfo(name = "account")
    @NotNull
    private final String account;

    @ColumnInfo(name = "application")
    @NotNull
    private final String application;

    @Embedded
    @Nullable
    private final DeliveryTime deliveryTime;

    @ColumnInfo(name = "exclude_transports")
    @NotNull
    private final Set<String> excludeTransports;

    @ColumnInfo(name = PushProcessor.DATAKEY_EXTRAS)
    @Nullable
    private final String extras;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;

    @ColumnInfo(name = "include_transports")
    @NotNull
    private final Set<String> includeTransports;

    @ColumnInfo(name = ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS)
    @NotNull
    private final Set<Integer> tags;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/database/entity/Subscription$DeliveryTime;", "", "from", "Lcom/vk/pushme/database/entity/Subscription$TimePoint;", "to", "<init>", "(Lcom/vk/pushme/database/entity/Subscription$TimePoint;Lcom/vk/pushme/database/entity/Subscription$TimePoint;)V", "getFrom", "()Lcom/vk/pushme/database/entity/Subscription$TimePoint;", "getTo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DeliveryTime {

        @Embedded(prefix = "from_")
        @NotNull
        private final TimePoint from;

        @Embedded(prefix = "to_")
        @NotNull
        private final TimePoint to;

        public DeliveryTime(@NotNull TimePoint from, @NotNull TimePoint to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            this.from = from;
            this.to = to;
        }

        public static /* synthetic */ DeliveryTime copy$default(DeliveryTime deliveryTime, TimePoint timePoint, TimePoint timePoint2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                timePoint = deliveryTime.from;
            }
            if ((i10 & 2) != 0) {
                timePoint2 = deliveryTime.to;
            }
            return deliveryTime.copy(timePoint, timePoint2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TimePoint getFrom() {
            return this.from;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final TimePoint getTo() {
            return this.to;
        }

        @NotNull
        public final DeliveryTime copy(@NotNull TimePoint from, @NotNull TimePoint to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            return new DeliveryTime(from, to);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeliveryTime)) {
                return false;
            }
            DeliveryTime deliveryTime = (DeliveryTime) other;
            return Intrinsics.areEqual(this.from, deliveryTime.from) && Intrinsics.areEqual(this.to, deliveryTime.to);
        }

        @NotNull
        public final TimePoint getFrom() {
            return this.from;
        }

        @NotNull
        public final TimePoint getTo() {
            return this.to;
        }

        public int hashCode() {
            return (this.from.hashCode() * 31) + this.to.hashCode();
        }

        @NotNull
        public String toString() {
            return "DeliveryTime(from=" + this.from + ", to=" + this.to + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/vk/pushme/database/entity/Subscription$TimePoint;", "", TimePickerDialog.HOUR, "", TimePickerDialog.MINUTE, "<init>", "(II)V", "getHour", "()I", "getMinute", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TimePoint {

        @ColumnInfo(name = TimePickerDialog.HOUR)
        private final int hour;

        @ColumnInfo(name = TimePickerDialog.MINUTE)
        private final int minute;

        public TimePoint(int i10, int i11) {
            this.hour = i10;
            this.minute = i11;
        }

        public static /* synthetic */ TimePoint copy$default(TimePoint timePoint, int i10, int i11, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                i10 = timePoint.hour;
            }
            if ((i12 & 2) != 0) {
                i11 = timePoint.minute;
            }
            return timePoint.copy(i10, i11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getHour() {
            return this.hour;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMinute() {
            return this.minute;
        }

        @NotNull
        public final TimePoint copy(int hour, int minute) {
            return new TimePoint(hour, minute);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TimePoint)) {
                return false;
            }
            TimePoint timePoint = (TimePoint) other;
            return this.hour == timePoint.hour && this.minute == timePoint.minute;
        }

        public final int getHour() {
            return this.hour;
        }

        public final int getMinute() {
            return this.minute;
        }

        public int hashCode() {
            return (Integer.hashCode(this.hour) * 31) + Integer.hashCode(this.minute);
        }

        @NotNull
        public String toString() {
            return "TimePoint(hour=" + this.hour + ", minute=" + this.minute + ")";
        }
    }

    public Subscription(long j10, @NotNull String account, @NotNull String application, @NotNull Set<Integer> tags, @NotNull Set<String> includeTransports, @NotNull Set<String> excludeTransports, @Nullable DeliveryTime deliveryTime, @Nullable String str) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(includeTransports, "includeTransports");
        Intrinsics.checkNotNullParameter(excludeTransports, "excludeTransports");
        this.id = j10;
        this.account = account;
        this.application = application;
        this.tags = tags;
        this.includeTransports = includeTransports;
        this.excludeTransports = excludeTransports;
        this.deliveryTime = deliveryTime;
        this.extras = str;
    }

    @NotNull
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    public final String getApplication() {
        return this.application;
    }

    @Nullable
    public final DeliveryTime getDeliveryTime() {
        return this.deliveryTime;
    }

    @NotNull
    public final Set<String> getExcludeTransports() {
        return this.excludeTransports;
    }

    @Nullable
    public final String getExtras() {
        return this.extras;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final Set<String> getIncludeTransports() {
        return this.includeTransports;
    }

    @NotNull
    public final Set<Integer> getTags() {
        return this.tags;
    }

    public /* synthetic */ Subscription(long j10, String str, String str2, Set set, Set set2, Set set3, DeliveryTime deliveryTime, String str3, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? 0L : j10, str, str2, set, set2, set3, deliveryTime, str3);
    }
}
