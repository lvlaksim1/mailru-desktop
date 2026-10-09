package com.vk.pushme.network.model.request;

import com.huawei.hms.android.SystemUtils;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.push.core.filedatastore.FileDataSource;
import com.vk.stat.push.PushAnalyticsConstants;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.uikit.dialog.TimePickerDialog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001:\u000289Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\rHÆ\u0003J\t\u0010/\u001a\u00020\u000fHÆ\u0003J\t\u00100\u001a\u00020\u0011HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u0091\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0003HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0011HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016¨\u0006:"}, d2 = {"Lcom/vk/pushme/network/model/request/SubscriptionRequest;", "", "account", "", "application", "transport", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;", "pushToken", CommonConstant.KEY_ACCESS_TOKEN, "androidId", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "sdkDeviceId", PreferenceHostProvider.URL_PARAM_CLIENT, "", "settings", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;", "status", "", "timeZone", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;ILjava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getApplication", "getTransport", "()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;", "getPushToken", "getAccessToken", "getAndroidId", "getDeviceId", "getSdkDeviceId", "getClient", "()Ljava/util/Map;", "getSettings", "()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;", "getStatus", "()I", "getTimeZone", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "Transport", "Settings", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SubscriptionRequest {

    @Nullable
    private final String accessToken;

    @NotNull
    private final String account;

    @NotNull
    private final String androidId;

    @NotNull
    private final String application;

    @NotNull
    private final Map<String, String> client;

    @NotNull
    private final String deviceId;

    @NotNull
    private final String pushToken;

    @Nullable
    private final String sdkDeviceId;

    @NotNull
    private final Settings settings;
    private final int status;

    @NotNull
    private final String timeZone;

    @NotNull
    private final Transport transport;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;", "", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "", "", "deliveryTime", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;", "extraSettings", "Lkotlinx/serialization/json/JsonObject;", "<init>", "(Ljava/util/Set;Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;Lkotlinx/serialization/json/JsonObject;)V", "getTags", "()Ljava/util/Set;", "getDeliveryTime", "()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;", "getExtraSettings", "()Lkotlinx/serialization/json/JsonObject;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "DeliveryTime", "TimePoint", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Settings {

        @Nullable
        private final DeliveryTime deliveryTime;

        @Nullable
        private final JsonObject extraSettings;

        @NotNull
        private final Set<Integer> tags;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;", "", "from", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$TimePoint;", "to", "<init>", "(Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$TimePoint;Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$TimePoint;)V", "getFrom", "()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$TimePoint;", "getTo", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class DeliveryTime {

            @NotNull
            private final TimePoint from;

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
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$TimePoint;", "", TimePickerDialog.HOUR, "", TimePickerDialog.MINUTE, "<init>", "(II)V", "getHour", "()I", "getMinute", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class TimePoint {
            private final int hour;
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

        public Settings(@NotNull Set<Integer> tags, @Nullable DeliveryTime deliveryTime, @Nullable JsonObject jsonObject) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            this.tags = tags;
            this.deliveryTime = deliveryTime;
            this.extraSettings = jsonObject;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Settings copy$default(Settings settings, Set set, DeliveryTime deliveryTime, JsonObject jsonObject, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                set = settings.tags;
            }
            if ((i10 & 2) != 0) {
                deliveryTime = settings.deliveryTime;
            }
            if ((i10 & 4) != 0) {
                jsonObject = settings.extraSettings;
            }
            return settings.copy(set, deliveryTime, jsonObject);
        }

        @NotNull
        public final Set<Integer> component1() {
            return this.tags;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DeliveryTime getDeliveryTime() {
            return this.deliveryTime;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final JsonObject getExtraSettings() {
            return this.extraSettings;
        }

        @NotNull
        public final Settings copy(@NotNull Set<Integer> tags, @Nullable DeliveryTime deliveryTime, @Nullable JsonObject extraSettings) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            return new Settings(tags, deliveryTime, extraSettings);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Settings)) {
                return false;
            }
            Settings settings = (Settings) other;
            return Intrinsics.areEqual(this.tags, settings.tags) && Intrinsics.areEqual(this.deliveryTime, settings.deliveryTime) && Intrinsics.areEqual(this.extraSettings, settings.extraSettings);
        }

        @Nullable
        public final DeliveryTime getDeliveryTime() {
            return this.deliveryTime;
        }

        @Nullable
        public final JsonObject getExtraSettings() {
            return this.extraSettings;
        }

        @NotNull
        public final Set<Integer> getTags() {
            return this.tags;
        }

        public int hashCode() {
            int iHashCode = this.tags.hashCode() * 31;
            DeliveryTime deliveryTime = this.deliveryTime;
            int iHashCode2 = (iHashCode + (deliveryTime == null ? 0 : deliveryTime.hashCode())) * 31;
            JsonObject jsonObject = this.extraSettings;
            return iHashCode2 + (jsonObject != null ? jsonObject.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Settings(tags=" + this.tags + ", deliveryTime=" + this.deliveryTime + ", extraSettings=" + this.extraSettings + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;", "", "jsonValue", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getJsonValue", "()Ljava/lang/String;", "FIREBASE", SystemUtils.PRODUCT_HUAWEI, "VKPNS", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Transport {
        FIREBASE("android"),
        HUAWEI(PushAnalyticsConstants.SOURCE_HUAWEI),
        VKPNS(FileDataSource.FILE_DATASOURCE_DIR);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        @NotNull
        private final String jsonValue;

        Transport(String str) {
            this.jsonValue = str;
        }

        @NotNull
        public static EnumEntries<Transport> getEntries() {
            return $ENTRIES;
        }

        @NotNull
        public final String getJsonValue() {
            return this.jsonValue;
        }
    }

    public SubscriptionRequest(@NotNull String account, @NotNull String application, @NotNull Transport transport, @NotNull String pushToken, @Nullable String str, @NotNull String androidId, @NotNull String deviceId, @Nullable String str2, @NotNull Map<String, String> client, @NotNull Settings settings, int i10, @NotNull String timeZone) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        Intrinsics.checkNotNullParameter(androidId, "androidId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        this.account = account;
        this.application = application;
        this.transport = transport;
        this.pushToken = pushToken;
        this.accessToken = str;
        this.androidId = androidId;
        this.deviceId = deviceId;
        this.sdkDeviceId = str2;
        this.client = client;
        this.settings = settings;
        this.status = i10;
        this.timeZone = timeZone;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubscriptionRequest copy$default(SubscriptionRequest subscriptionRequest, String str, String str2, Transport transport, String str3, String str4, String str5, String str6, String str7, Map map, Settings settings, int i10, String str8, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = subscriptionRequest.account;
        }
        if ((i11 & 2) != 0) {
            str2 = subscriptionRequest.application;
        }
        if ((i11 & 4) != 0) {
            transport = subscriptionRequest.transport;
        }
        if ((i11 & 8) != 0) {
            str3 = subscriptionRequest.pushToken;
        }
        if ((i11 & 16) != 0) {
            str4 = subscriptionRequest.accessToken;
        }
        if ((i11 & 32) != 0) {
            str5 = subscriptionRequest.androidId;
        }
        if ((i11 & 64) != 0) {
            str6 = subscriptionRequest.deviceId;
        }
        if ((i11 & 128) != 0) {
            str7 = subscriptionRequest.sdkDeviceId;
        }
        if ((i11 & 256) != 0) {
            map = subscriptionRequest.client;
        }
        if ((i11 & 512) != 0) {
            settings = subscriptionRequest.settings;
        }
        if ((i11 & 1024) != 0) {
            i10 = subscriptionRequest.status;
        }
        if ((i11 & 2048) != 0) {
            str8 = subscriptionRequest.timeZone;
        }
        int i12 = i10;
        String str9 = str8;
        Map map2 = map;
        Settings settings2 = settings;
        String str10 = str6;
        String str11 = str7;
        String str12 = str4;
        String str13 = str5;
        return subscriptionRequest.copy(str, str2, transport, str3, str12, str13, str10, str11, map2, settings2, i12, str9);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Settings getSettings() {
        return this.settings;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    @NotNull
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getTimeZone() {
        return this.timeZone;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getApplication() {
        return this.application;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Transport getTransport() {
        return this.transport;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPushToken() {
        return this.pushToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAndroidId() {
        return this.androidId;
    }

    @NotNull
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getSdkDeviceId() {
        return this.sdkDeviceId;
    }

    @NotNull
    public final Map<String, String> component9() {
        return this.client;
    }

    @NotNull
    public final SubscriptionRequest copy(@NotNull String account, @NotNull String application, @NotNull Transport transport, @NotNull String pushToken, @Nullable String accessToken, @NotNull String androidId, @NotNull String deviceId, @Nullable String sdkDeviceId, @NotNull Map<String, String> client, @NotNull Settings settings, int status, @NotNull String timeZone) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        Intrinsics.checkNotNullParameter(androidId, "androidId");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return new SubscriptionRequest(account, application, transport, pushToken, accessToken, androidId, deviceId, sdkDeviceId, client, settings, status, timeZone);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubscriptionRequest)) {
            return false;
        }
        SubscriptionRequest subscriptionRequest = (SubscriptionRequest) other;
        return Intrinsics.areEqual(this.account, subscriptionRequest.account) && Intrinsics.areEqual(this.application, subscriptionRequest.application) && this.transport == subscriptionRequest.transport && Intrinsics.areEqual(this.pushToken, subscriptionRequest.pushToken) && Intrinsics.areEqual(this.accessToken, subscriptionRequest.accessToken) && Intrinsics.areEqual(this.androidId, subscriptionRequest.androidId) && Intrinsics.areEqual(this.deviceId, subscriptionRequest.deviceId) && Intrinsics.areEqual(this.sdkDeviceId, subscriptionRequest.sdkDeviceId) && Intrinsics.areEqual(this.client, subscriptionRequest.client) && Intrinsics.areEqual(this.settings, subscriptionRequest.settings) && this.status == subscriptionRequest.status && Intrinsics.areEqual(this.timeZone, subscriptionRequest.timeZone);
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    public final String getAccount() {
        return this.account;
    }

    @NotNull
    public final String getAndroidId() {
        return this.androidId;
    }

    @NotNull
    public final String getApplication() {
        return this.application;
    }

    @NotNull
    public final Map<String, String> getClient() {
        return this.client;
    }

    @NotNull
    public final String getDeviceId() {
        return this.deviceId;
    }

    @NotNull
    public final String getPushToken() {
        return this.pushToken;
    }

    @Nullable
    public final String getSdkDeviceId() {
        return this.sdkDeviceId;
    }

    @NotNull
    public final Settings getSettings() {
        return this.settings;
    }

    public final int getStatus() {
        return this.status;
    }

    @NotNull
    public final String getTimeZone() {
        return this.timeZone;
    }

    @NotNull
    public final Transport getTransport() {
        return this.transport;
    }

    public int hashCode() {
        int iHashCode = ((((((this.account.hashCode() * 31) + this.application.hashCode()) * 31) + this.transport.hashCode()) * 31) + this.pushToken.hashCode()) * 31;
        String str = this.accessToken;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.androidId.hashCode()) * 31) + this.deviceId.hashCode()) * 31;
        String str2 = this.sdkDeviceId;
        return ((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.client.hashCode()) * 31) + this.settings.hashCode()) * 31) + Integer.hashCode(this.status)) * 31) + this.timeZone.hashCode();
    }

    @NotNull
    public String toString() {
        return "SubscriptionRequest(account=" + this.account + ", application=" + this.application + ", transport=" + this.transport + ", pushToken=" + this.pushToken + ", accessToken=" + this.accessToken + ", androidId=" + this.androidId + ", deviceId=" + this.deviceId + ", sdkDeviceId=" + this.sdkDeviceId + ", client=" + this.client + ", settings=" + this.settings + ", status=" + this.status + ", timeZone=" + this.timeZone + ")";
    }
}
