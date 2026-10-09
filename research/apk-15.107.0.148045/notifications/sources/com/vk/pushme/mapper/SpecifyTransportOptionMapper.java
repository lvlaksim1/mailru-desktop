package com.vk.pushme.mapper;

import com.vk.pushme.model.SpecifyTransportOption;
import com.vk.pushme.model.Transport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005¨\u0006\u000e"}, d2 = {"Lcom/vk/pushme/mapper/SpecifyTransportOptionMapper;", "", "<init>", "()V", "parse", "Lcom/vk/pushme/model/SpecifyTransportOption;", "includeTransports", "", "", "excludeTransports", "serialize", "Lcom/vk/pushme/mapper/SpecifyTransportOptionMapper$SerializeResult;", "option", "SerializeResult", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSpecifyTransportOptionMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SpecifyTransportOptionMapper.kt\ncom/vk/pushme/mapper/SpecifyTransportOptionMapper\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 5 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,43:1\n1617#2,9:44\n1869#2:53\n1870#2:55\n1626#2:56\n1617#2,9:59\n1869#2:68\n1870#2:70\n1626#2:71\n1#3:54\n1#3:69\n37#4,2:57\n37#4,2:72\n11561#5:74\n11896#5,3:75\n11561#5:78\n11896#5,3:79\n*S KotlinDebug\n*F\n+ 1 SpecifyTransportOptionMapper.kt\ncom/vk/pushme/mapper/SpecifyTransportOptionMapper\n*L\n13#1:44,9\n13#1:53\n13#1:55\n13#1:56\n19#1:59,9\n19#1:68\n19#1:70\n19#1:71\n13#1:54\n19#1:69\n15#1:57,2\n21#1:72,2\n31#1:74\n31#1:75,3\n36#1:78\n36#1:79,3\n*E\n"})
public final class SpecifyTransportOptionMapper {

    @NotNull
    public static final SpecifyTransportOptionMapper INSTANCE = new SpecifyTransportOptionMapper();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/vk/pushme/mapper/SpecifyTransportOptionMapper$SerializeResult;", "", "include", "", "", "exclude", "<init>", "(Ljava/util/Set;Ljava/util/Set;)V", "getInclude", "()Ljava/util/Set;", "getExclude", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SerializeResult {

        @NotNull
        private final Set<String> exclude;

        @NotNull
        private final Set<String> include;

        public SerializeResult(@NotNull Set<String> include, @NotNull Set<String> exclude) {
            Intrinsics.checkNotNullParameter(include, "include");
            Intrinsics.checkNotNullParameter(exclude, "exclude");
            this.include = include;
            this.exclude = exclude;
        }

        @NotNull
        public final Set<String> getExclude() {
            return this.exclude;
        }

        @NotNull
        public final Set<String> getInclude() {
            return this.include;
        }
    }

    private SpecifyTransportOptionMapper() {
    }

    @NotNull
    public final SpecifyTransportOption parse(@NotNull Set<String> includeTransports, @NotNull Set<String> excludeTransports) {
        Intrinsics.checkNotNullParameter(includeTransports, "includeTransports");
        Intrinsics.checkNotNullParameter(excludeTransports, "excludeTransports");
        if (!includeTransports.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = includeTransports.iterator();
            while (it.hasNext()) {
                Transport transportFromString = Transport.INSTANCE.fromString((String) it.next());
                if (transportFromString != null) {
                    arrayList.add(transportFromString);
                }
            }
            if (!arrayList.isEmpty()) {
                Transport[] transportArr = (Transport[]) arrayList.toArray(new Transport[0]);
                return new SpecifyTransportOption.Include((Transport[]) Arrays.copyOf(transportArr, transportArr.length));
            }
        }
        if (!excludeTransports.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it2 = excludeTransports.iterator();
            while (it2.hasNext()) {
                Transport transportFromString2 = Transport.INSTANCE.fromString((String) it2.next());
                if (transportFromString2 != null) {
                    arrayList2.add(transportFromString2);
                }
            }
            if (!arrayList2.isEmpty()) {
                Transport[] transportArr2 = (Transport[]) arrayList2.toArray(new Transport[0]);
                return new SpecifyTransportOption.Exclude((Transport[]) Arrays.copyOf(transportArr2, transportArr2.length));
            }
        }
        return SpecifyTransportOption.AllTransports.INSTANCE;
    }

    @NotNull
    public final SerializeResult serialize(@NotNull SpecifyTransportOption option) {
        Intrinsics.checkNotNullParameter(option, "option");
        if (Intrinsics.areEqual(option, SpecifyTransportOption.AllTransports.INSTANCE)) {
            return new SerializeResult(SetsKt.emptySet(), SetsKt.emptySet());
        }
        int i10 = 0;
        if (option instanceof SpecifyTransportOption.Include) {
            Transport[] transports = ((SpecifyTransportOption.Include) option).getTransports();
            ArrayList arrayList = new ArrayList(transports.length);
            int length = transports.length;
            while (i10 < length) {
                arrayList.add(transports[i10].getValue());
                i10++;
            }
            return new SerializeResult(CollectionsKt.toSet(arrayList), SetsKt.emptySet());
        }
        if (!(option instanceof SpecifyTransportOption.Exclude)) {
            throw new NoWhenBranchMatchedException();
        }
        Transport[] transports2 = ((SpecifyTransportOption.Exclude) option).getTransports();
        ArrayList arrayList2 = new ArrayList(transports2.length);
        int length2 = transports2.length;
        while (i10 < length2) {
            arrayList2.add(transports2[i10].getValue());
            i10++;
        }
        return new SerializeResult(SetsKt.emptySet(), CollectionsKt.toSet(arrayList2));
    }
}
