package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/PushMessagesAnalyticsCollector;", "", "startCollectAnalytics", "", "transports", "", "Lru/mail/util/push/PushMessagesTransport;", "VkpnsHostInfo", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushMessagesAnalyticsCollector {

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lru/mail/util/push/PushMessagesAnalyticsCollector$VkpnsHostInfo;", "", "packageName", "", "version", "", "hasBackgroundWorkPermission", "", "<init>", "(Ljava/lang/String;IZ)V", "getPackageName", "()Ljava/lang/String;", "getVersion", "()I", "getHasBackgroundWorkPermission", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkpnsHostInfo {
        public static final int $stable = 0;
        private final boolean hasBackgroundWorkPermission;

        @NotNull
        private final String packageName;
        private final int version;

        public VkpnsHostInfo(@NotNull String packageName, int i10, boolean z10) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            this.packageName = packageName;
            this.version = i10;
            this.hasBackgroundWorkPermission = z10;
        }

        public static /* synthetic */ VkpnsHostInfo copy$default(VkpnsHostInfo vkpnsHostInfo, String str, int i10, boolean z10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = vkpnsHostInfo.packageName;
            }
            if ((i11 & 2) != 0) {
                i10 = vkpnsHostInfo.version;
            }
            if ((i11 & 4) != 0) {
                z10 = vkpnsHostInfo.hasBackgroundWorkPermission;
            }
            return vkpnsHostInfo.copy(str, i10, z10);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageName() {
            return this.packageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getVersion() {
            return this.version;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getHasBackgroundWorkPermission() {
            return this.hasBackgroundWorkPermission;
        }

        @NotNull
        public final VkpnsHostInfo copy(@NotNull String packageName, int version, boolean hasBackgroundWorkPermission) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return new VkpnsHostInfo(packageName, version, hasBackgroundWorkPermission);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VkpnsHostInfo)) {
                return false;
            }
            VkpnsHostInfo vkpnsHostInfo = (VkpnsHostInfo) other;
            return Intrinsics.areEqual(this.packageName, vkpnsHostInfo.packageName) && this.version == vkpnsHostInfo.version && this.hasBackgroundWorkPermission == vkpnsHostInfo.hasBackgroundWorkPermission;
        }

        public final boolean getHasBackgroundWorkPermission() {
            return this.hasBackgroundWorkPermission;
        }

        @NotNull
        public final String getPackageName() {
            return this.packageName;
        }

        public final int getVersion() {
            return this.version;
        }

        public int hashCode() {
            return (((this.packageName.hashCode() * 31) + Integer.hashCode(this.version)) * 31) + Boolean.hashCode(this.hasBackgroundWorkPermission);
        }

        @NotNull
        public String toString() {
            return "VkpnsHostInfo(packageName=" + this.packageName + ", version=" + this.version + ", hasBackgroundWorkPermission=" + this.hasBackgroundWorkPermission + ")";
        }
    }

    void startCollectAnalytics(@NotNull Collection<? extends PushMessagesTransport> transports);
}
