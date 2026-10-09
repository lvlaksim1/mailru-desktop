package com.vk.pushme.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/vk/pushme/model/SpecifyTransportOption;", "", "<init>", "()V", "AllTransports", "Include", "Exclude", "Lcom/vk/pushme/model/SpecifyTransportOption$AllTransports;", "Lcom/vk/pushme/model/SpecifyTransportOption$Exclude;", "Lcom/vk/pushme/model/SpecifyTransportOption$Include;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SpecifyTransportOption {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/model/SpecifyTransportOption$AllTransports;", "Lcom/vk/pushme/model/SpecifyTransportOption;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AllTransports extends SpecifyTransportOption {

        @NotNull
        public static final AllTransports INSTANCE = new AllTransports();

        private AllTransports() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/vk/pushme/model/SpecifyTransportOption$Exclude;", "Lcom/vk/pushme/model/SpecifyTransportOption;", "transports", "", "Lcom/vk/pushme/model/Transport;", "<init>", "([Lcom/vk/pushme/model/Transport;)V", "getTransports", "()[Lcom/vk/pushme/model/Transport;", "[Lcom/vk/pushme/model/Transport;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Exclude extends SpecifyTransportOption {

        @NotNull
        private final Transport[] transports;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Exclude(@NotNull Transport... transports) {
            super(null);
            Intrinsics.checkNotNullParameter(transports, "transports");
            this.transports = transports;
            if (transports.length == 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }

        @NotNull
        public final Transport[] getTransports() {
            return this.transports;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/vk/pushme/model/SpecifyTransportOption$Include;", "Lcom/vk/pushme/model/SpecifyTransportOption;", "transports", "", "Lcom/vk/pushme/model/Transport;", "<init>", "([Lcom/vk/pushme/model/Transport;)V", "getTransports", "()[Lcom/vk/pushme/model/Transport;", "[Lcom/vk/pushme/model/Transport;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Include extends SpecifyTransportOption {

        @NotNull
        private final Transport[] transports;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Include(@NotNull Transport... transports) {
            super(null);
            Intrinsics.checkNotNullParameter(transports, "transports");
            this.transports = transports;
            if (transports.length == 0) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }

        @NotNull
        public final Transport[] getTransports() {
            return this.transports;
        }
    }

    public /* synthetic */ SpecifyTransportOption(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SpecifyTransportOption() {
    }
}
