package ru.mail.sdk;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\bHÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lru/mail/sdk/MailSdkPushConfig;", "", "pusherApplicationName", "", "pusherHost", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPusherApplicationName$annotations", "()V", "getPusherApplicationName", "()Ljava/lang/String;", "getPusherHost$annotations", "getPusherHost", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$mails_release", "Companion", "$serializer", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final /* data */ class MailSdkPushConfig {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final String pusherApplicationName;

    @NotNull
    private final String pusherHost;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¨\u0006\b"}, d2 = {"Lru/mail/sdk/MailSdkPushConfig$Companion;", "", "<init>", "()V", "getDefault", "Lru/mail/sdk/MailSdkPushConfig;", "serializer", "Lkotlinx/serialization/KSerializer;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final MailSdkPushConfig getDefault() {
            return new MailSdkPushConfig("mail_plugin_teams", "push-me.mail.ru");
        }

        @NotNull
        public final KSerializer<MailSdkPushConfig> serializer() {
            return MailSdkPushConfig$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public /* synthetic */ MailSdkPushConfig(int i10, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i10 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i10, 3, MailSdkPushConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.pusherApplicationName = str;
        this.pusherHost = str2;
    }

    public static /* synthetic */ MailSdkPushConfig copy$default(MailSdkPushConfig mailSdkPushConfig, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mailSdkPushConfig.pusherApplicationName;
        }
        if ((i10 & 2) != 0) {
            str2 = mailSdkPushConfig.pusherHost;
        }
        return mailSdkPushConfig.copy(str, str2);
    }

    @JvmStatic
    @NotNull
    public static final MailSdkPushConfig getDefault() {
        return INSTANCE.getDefault();
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$mails_release(MailSdkPushConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.pusherApplicationName);
        output.encodeStringElement(serialDesc, 1, self.pusherHost);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPusherApplicationName() {
        return this.pusherApplicationName;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPusherHost() {
        return this.pusherHost;
    }

    @NotNull
    public final MailSdkPushConfig copy(@NotNull String pusherApplicationName, @NotNull String pusherHost) {
        Intrinsics.checkNotNullParameter(pusherApplicationName, "pusherApplicationName");
        Intrinsics.checkNotNullParameter(pusherHost, "pusherHost");
        return new MailSdkPushConfig(pusherApplicationName, pusherHost);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MailSdkPushConfig)) {
            return false;
        }
        MailSdkPushConfig mailSdkPushConfig = (MailSdkPushConfig) other;
        return Intrinsics.areEqual(this.pusherApplicationName, mailSdkPushConfig.pusherApplicationName) && Intrinsics.areEqual(this.pusherHost, mailSdkPushConfig.pusherHost);
    }

    @NotNull
    public final String getPusherApplicationName() {
        return this.pusherApplicationName;
    }

    @NotNull
    public final String getPusherHost() {
        return this.pusherHost;
    }

    public int hashCode() {
        return (this.pusherApplicationName.hashCode() * 31) + this.pusherHost.hashCode();
    }

    @NotNull
    public String toString() {
        return "MailSdkPushConfig(pusherApplicationName=" + this.pusherApplicationName + ", pusherHost=" + this.pusherHost + ")";
    }

    public MailSdkPushConfig(@NotNull String pusherApplicationName, @NotNull String pusherHost) {
        Intrinsics.checkNotNullParameter(pusherApplicationName, "pusherApplicationName");
        Intrinsics.checkNotNullParameter(pusherHost, "pusherHost");
        this.pusherApplicationName = pusherApplicationName;
        this.pusherHost = pusherHost;
    }

    @SerialName("pusher_application_name")
    public static /* synthetic */ void getPusherApplicationName$annotations() {
    }

    @SerialName("pusher_host")
    public static /* synthetic */ void getPusherHost$annotations() {
    }
}
