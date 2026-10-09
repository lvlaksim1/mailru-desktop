package ru.mail.rustoresdk.message;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001f\u0010\n\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lru/mail/rustoresdk/message/VkpnsRemoteMessage;", "", "data", "", "", "<init>", "(Ljava/util/Map;)V", "getData", "()Ljava/util/Map;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VkpnsRemoteMessage {

    @NotNull
    private final Map<String, String> data;

    public VkpnsRemoteMessage(@NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VkpnsRemoteMessage copy$default(VkpnsRemoteMessage vkpnsRemoteMessage, Map map, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            map = vkpnsRemoteMessage.data;
        }
        return vkpnsRemoteMessage.copy(map);
    }

    @NotNull
    public final Map<String, String> component1() {
        return this.data;
    }

    @NotNull
    public final VkpnsRemoteMessage copy(@NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new VkpnsRemoteMessage(data);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VkpnsRemoteMessage) && Intrinsics.areEqual(this.data, ((VkpnsRemoteMessage) other).data);
    }

    @NotNull
    public final Map<String, String> getData() {
        return this.data;
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    @NotNull
    public String toString() {
        return "VkpnsRemoteMessage(data=" + this.data + ")";
    }
}
