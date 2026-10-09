package ru.mail.portal.app.adapter.notifications.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006 "}, d2 = {"Lru/mail/portal/app/adapter/notifications/config/ExperimentNotificationConfig;", "", "appId", "", "experimentId", PushProcessor.DATAKEY_IMPORTANCE, "channelName", "soundEnabled", "", "vibrationEnabled", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getAppId", "()Ljava/lang/String;", "getExperimentId", "getImportance", "getChannelName", "getSoundEnabled", "()Z", "getVibrationEnabled", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ExperimentNotificationConfig {

    @NotNull
    private final String appId;

    @NotNull
    private final String channelName;

    @NotNull
    private final String experimentId;

    @NotNull
    private final String importance;
    private final boolean soundEnabled;
    private final boolean vibrationEnabled;

    public ExperimentNotificationConfig(@NotNull String appId, @NotNull String experimentId, @NotNull String importance, @NotNull String channelName, boolean z10, boolean z11) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(experimentId, "experimentId");
        Intrinsics.checkNotNullParameter(importance, "importance");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        this.appId = appId;
        this.experimentId = experimentId;
        this.importance = importance;
        this.channelName = channelName;
        this.soundEnabled = z10;
        this.vibrationEnabled = z11;
    }

    public static /* synthetic */ ExperimentNotificationConfig copy$default(ExperimentNotificationConfig experimentNotificationConfig, String str, String str2, String str3, String str4, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = experimentNotificationConfig.appId;
        }
        if ((i10 & 2) != 0) {
            str2 = experimentNotificationConfig.experimentId;
        }
        if ((i10 & 4) != 0) {
            str3 = experimentNotificationConfig.importance;
        }
        if ((i10 & 8) != 0) {
            str4 = experimentNotificationConfig.channelName;
        }
        if ((i10 & 16) != 0) {
            z10 = experimentNotificationConfig.soundEnabled;
        }
        if ((i10 & 32) != 0) {
            z11 = experimentNotificationConfig.vibrationEnabled;
        }
        boolean z12 = z10;
        boolean z13 = z11;
        return experimentNotificationConfig.copy(str, str2, str3, str4, z12, z13);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getExperimentId() {
        return this.experimentId;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getImportance() {
        return this.importance;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getChannelName() {
        return this.channelName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getSoundEnabled() {
        return this.soundEnabled;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getVibrationEnabled() {
        return this.vibrationEnabled;
    }

    @NotNull
    public final ExperimentNotificationConfig copy(@NotNull String appId, @NotNull String experimentId, @NotNull String importance, @NotNull String channelName, boolean soundEnabled, boolean vibrationEnabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(experimentId, "experimentId");
        Intrinsics.checkNotNullParameter(importance, "importance");
        Intrinsics.checkNotNullParameter(channelName, "channelName");
        return new ExperimentNotificationConfig(appId, experimentId, importance, channelName, soundEnabled, vibrationEnabled);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExperimentNotificationConfig)) {
            return false;
        }
        ExperimentNotificationConfig experimentNotificationConfig = (ExperimentNotificationConfig) other;
        return Intrinsics.areEqual(this.appId, experimentNotificationConfig.appId) && Intrinsics.areEqual(this.experimentId, experimentNotificationConfig.experimentId) && Intrinsics.areEqual(this.importance, experimentNotificationConfig.importance) && Intrinsics.areEqual(this.channelName, experimentNotificationConfig.channelName) && this.soundEnabled == experimentNotificationConfig.soundEnabled && this.vibrationEnabled == experimentNotificationConfig.vibrationEnabled;
    }

    @NotNull
    public final String getAppId() {
        return this.appId;
    }

    @NotNull
    public final String getChannelName() {
        return this.channelName;
    }

    @NotNull
    public final String getExperimentId() {
        return this.experimentId;
    }

    @NotNull
    public final String getImportance() {
        return this.importance;
    }

    public final boolean getSoundEnabled() {
        return this.soundEnabled;
    }

    public final boolean getVibrationEnabled() {
        return this.vibrationEnabled;
    }

    public int hashCode() {
        return (((((((((this.appId.hashCode() * 31) + this.experimentId.hashCode()) * 31) + this.importance.hashCode()) * 31) + this.channelName.hashCode()) * 31) + Boolean.hashCode(this.soundEnabled)) * 31) + Boolean.hashCode(this.vibrationEnabled);
    }

    @NotNull
    public String toString() {
        return "ExperimentNotificationConfig(appId=" + this.appId + ", experimentId=" + this.experimentId + ", importance=" + this.importance + ", channelName=" + this.channelName + ", soundEnabled=" + this.soundEnabled + ", vibrationEnabled=" + this.vibrationEnabled + ")";
    }
}
