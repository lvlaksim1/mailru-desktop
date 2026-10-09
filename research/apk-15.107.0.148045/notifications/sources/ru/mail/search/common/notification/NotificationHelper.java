package ru.mail.search.common.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Context;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\u001bB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0017J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0004J\u0010\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0004J\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lru/mail/search/common/notification/NotificationHelper;", "", "context", "Landroid/content/Context;", "channels", "", "Lru/mail/search/common/notification/NotificationHelper$NotificationChannelData;", "(Landroid/content/Context;Ljava/util/List;)V", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "getNotificationManager", "()Landroidx/core/app/NotificationManagerCompat;", "areNotificationsEnabled", "", "getNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", RemoteMessageConst.Notification.CHANNEL_ID, "", "hideNotification", "", "notificationId", "", "isNotificationsChannelEnabled", "prepareChannels", "showNotification", "notification", "Landroid/app/Notification;", "NotificationChannelData", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class NotificationHelper {

    @NotNull
    private final Context context;

    @NotNull
    private final NotificationManagerCompat notificationManager;

    public NotificationHelper(@NotNull Context context, @NotNull List<NotificationChannelData> channels) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(channels, "channels");
        this.context = context;
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(context)");
        this.notificationManager = notificationManagerCompatFrom;
        prepareChannels(channels);
    }

    private final void prepareChannels(List<NotificationChannelData> channels) {
        List<NotificationChannelData> list = channels;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (NotificationChannelData notificationChannelData : list) {
            NotificationChannel notificationChannel = new NotificationChannel(notificationChannelData.getId(), this.context.getString(notificationChannelData.getTitle()), notificationChannelData.getImportance());
            Integer description = notificationChannelData.getDescription();
            if (description != null) {
                notificationChannel.setDescription(this.context.getString(description.intValue()));
            }
            notificationChannel.enableLights(notificationChannelData.getEnableLights());
            Integer lightColor = notificationChannelData.getLightColor();
            if (lightColor != null) {
                notificationChannel.setLightColor(lightColor.intValue());
            }
            notificationChannel.enableVibration(notificationChannelData.getEnableVibration());
            arrayList.add(notificationChannel);
        }
        this.notificationManager.createNotificationChannels(arrayList);
    }

    @CallSuper
    public boolean areNotificationsEnabled() {
        return this.notificationManager.areNotificationsEnabled();
    }

    @NotNull
    protected final NotificationCompat.Builder getNotificationBuilder(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return new NotificationCompat.Builder(this.context, channelId);
    }

    @NotNull
    protected final NotificationManagerCompat getNotificationManager() {
        return this.notificationManager;
    }

    protected final void hideNotification(int notificationId) {
        this.notificationManager.cancel(notificationId);
    }

    protected final boolean isNotificationsChannelEnabled(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        NotificationChannel notificationChannel = this.notificationManager.getNotificationChannel(channelId);
        return notificationChannel == null || notificationChannel.getImportance() != 0;
    }

    protected final void showNotification(int notificationId, @NotNull Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        this.notificationManager.notify(notificationId, notification);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010\u001f\u001a\u00020\tHÆ\u0003JX\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006&"}, d2 = {"Lru/mail/search/common/notification/NotificationHelper$NotificationChannelData;", "", "id", "", "title", "", PushProcessor.DATAKEY_IMPORTANCE, "description", "enableLights", "", "lightColor", "enableVibration", "(Ljava/lang/String;IILjava/lang/Integer;ZLjava/lang/Integer;Z)V", "getDescription", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEnableLights", "()Z", "getEnableVibration", "getId", "()Ljava/lang/String;", "getImportance", "()I", "getLightColor", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;IILjava/lang/Integer;ZLjava/lang/Integer;Z)Lru/mail/search/common/notification/NotificationHelper$NotificationChannelData;", "equals", "other", "hashCode", "toString", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final /* data */ class NotificationChannelData {

        @Nullable
        private final Integer description;
        private final boolean enableLights;
        private final boolean enableVibration;

        @NotNull
        private final String id;
        private final int importance;

        @Nullable
        private final Integer lightColor;
        private final int title;

        public NotificationChannelData(@NotNull String id2, @StringRes int i10, int i11, @StringRes @Nullable Integer num, boolean z10, @ColorInt @Nullable Integer num2, boolean z11) {
            Intrinsics.checkNotNullParameter(id2, "id");
            this.id = id2;
            this.title = i10;
            this.importance = i11;
            this.description = num;
            this.enableLights = z10;
            this.lightColor = num2;
            this.enableVibration = z11;
        }

        public static /* synthetic */ NotificationChannelData copy$default(NotificationChannelData notificationChannelData, String str, int i10, int i11, Integer num, boolean z10, Integer num2, boolean z11, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                str = notificationChannelData.id;
            }
            if ((i12 & 2) != 0) {
                i10 = notificationChannelData.title;
            }
            if ((i12 & 4) != 0) {
                i11 = notificationChannelData.importance;
            }
            if ((i12 & 8) != 0) {
                num = notificationChannelData.description;
            }
            if ((i12 & 16) != 0) {
                z10 = notificationChannelData.enableLights;
            }
            if ((i12 & 32) != 0) {
                num2 = notificationChannelData.lightColor;
            }
            if ((i12 & 64) != 0) {
                z11 = notificationChannelData.enableVibration;
            }
            Integer num3 = num2;
            boolean z12 = z11;
            boolean z13 = z10;
            int i13 = i11;
            return notificationChannelData.copy(str, i10, i13, num, z13, num3, z12);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getImportance() {
            return this.importance;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getEnableLights() {
            return this.enableLights;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getLightColor() {
            return this.lightColor;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getEnableVibration() {
            return this.enableVibration;
        }

        @NotNull
        public final NotificationChannelData copy(@NotNull String id2, @StringRes int title, int importance, @StringRes @Nullable Integer description, boolean enableLights, @ColorInt @Nullable Integer lightColor, boolean enableVibration) {
            Intrinsics.checkNotNullParameter(id2, "id");
            return new NotificationChannelData(id2, title, importance, description, enableLights, lightColor, enableVibration);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotificationChannelData)) {
                return false;
            }
            NotificationChannelData notificationChannelData = (NotificationChannelData) other;
            return Intrinsics.areEqual(this.id, notificationChannelData.id) && this.title == notificationChannelData.title && this.importance == notificationChannelData.importance && Intrinsics.areEqual(this.description, notificationChannelData.description) && this.enableLights == notificationChannelData.enableLights && Intrinsics.areEqual(this.lightColor, notificationChannelData.lightColor) && this.enableVibration == notificationChannelData.enableVibration;
        }

        @Nullable
        public final Integer getDescription() {
            return this.description;
        }

        public final boolean getEnableLights() {
            return this.enableLights;
        }

        public final boolean getEnableVibration() {
            return this.enableVibration;
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        public final int getImportance() {
            return this.importance;
        }

        @Nullable
        public final Integer getLightColor() {
            return this.lightColor;
        }

        public final int getTitle() {
            return this.title;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v13, types: [int] */
        /* JADX WARN: Type inference failed for: r0v9, types: [int] */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v8, types: [int] */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1, types: [int] */
        /* JADX WARN: Type inference failed for: r3v2 */
        public int hashCode() {
            int iHashCode = ((((this.id.hashCode() * 31) + Integer.hashCode(this.title)) * 31) + Integer.hashCode(this.importance)) * 31;
            Integer num = this.description;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            boolean z10 = this.enableLights;
            ?? r10 = z10;
            if (z10) {
                r10 = 1;
            }
            int i10 = (iHashCode2 + r10) * 31;
            Integer num2 = this.lightColor;
            int iHashCode3 = (i10 + (num2 != null ? num2.hashCode() : 0)) * 31;
            boolean z11 = this.enableVibration;
            return iHashCode3 + (z11 ? 1 : z11);
        }

        @NotNull
        public String toString() {
            return "NotificationChannelData(id=" + this.id + ", title=" + this.title + ", importance=" + this.importance + ", description=" + this.description + ", enableLights=" + this.enableLights + ", lightColor=" + this.lightColor + ", enableVibration=" + this.enableVibration + ')';
        }

        public /* synthetic */ NotificationChannelData(String str, int i10, int i11, Integer num, boolean z10, Integer num2, boolean z11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i10, i11, (i12 & 8) != 0 ? null : num, (i12 & 16) != 0 ? false : z10, (i12 & 32) != 0 ? null : num2, (i12 & 64) != 0 ? false : z11);
        }
    }
}
