package com.vk.pushme.model;

import com.huawei.hms.android.SystemUtils;
import com.vk.push.core.filedatastore.FileDataSource;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/vk/pushme/model/Transport;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "FIREBASE", SystemUtils.PRODUCT_HUAWEI, "VKPNS", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum Transport {
    FIREBASE("fcm"),
    HUAWEI("hms"),
    VKPNS(FileDataSource.FILE_DATASOURCE_DIR);


    @NotNull
    private final String value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/model/Transport$Companion;", "", "<init>", "()V", "fromString", "Lcom/vk/pushme/model/Transport;", "value", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transport.kt\ncom/vk/pushme/model/Transport$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,14:1\n1400#2,2:15\n*S KotlinDebug\n*F\n+ 1 Transport.kt\ncom/vk/pushme/model/Transport$Companion\n*L\n10#1:15,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final Transport fromString(@Nullable String value) {
            for (Transport transport : Transport.values()) {
                if (StringsKt.equals(transport.getValue(), value, true)) {
                    return transport;
                }
            }
            return null;
        }

        private Companion() {
        }
    }

    Transport(String str) {
        this.value = str;
    }

    @NotNull
    public static EnumEntries<Transport> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
