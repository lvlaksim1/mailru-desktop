package com.vk.pushme.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.uikit.dialog.TimePickerDialog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/vk/pushme/model/DeliveryTime;", "", "from", "Lcom/vk/pushme/model/DeliveryTime$TimePoint;", "to", "<init>", "(Lcom/vk/pushme/model/DeliveryTime$TimePoint;Lcom/vk/pushme/model/DeliveryTime$TimePoint;)V", "getFrom", "()Lcom/vk/pushme/model/DeliveryTime$TimePoint;", "getTo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "TimePoint", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DeliveryTime {

    @NotNull
    private final TimePoint from;

    @NotNull
    private final TimePoint to;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0000H\u0096\u0002J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/vk/pushme/model/DeliveryTime$TimePoint;", "", TimePickerDialog.HOUR, "", TimePickerDialog.MINUTE, "<init>", "(II)V", "getHour", "()I", "getMinute", "compareTo", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class TimePoint implements Comparable<TimePoint> {
        private final int hour;
        private final int minute;

        public TimePoint(int i10, int i11) {
            this.hour = i10;
            this.minute = i11;
            if (i10 < 0 || i10 >= 24) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            if (i11 < 0 || i11 >= 60) {
                throw new IllegalArgumentException("Failed requirement.");
            }
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

        @Override // java.lang.Comparable
        public int compareTo(@NotNull TimePoint other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i10 = this.hour;
            int i11 = other.hour;
            return i10 == i11 ? Intrinsics.compare(this.minute, other.minute) : Intrinsics.compare(i10, i11);
        }
    }

    public DeliveryTime(@NotNull TimePoint from, @NotNull TimePoint to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        this.from = from;
        this.to = to;
        if (from.compareTo(to) >= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
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
