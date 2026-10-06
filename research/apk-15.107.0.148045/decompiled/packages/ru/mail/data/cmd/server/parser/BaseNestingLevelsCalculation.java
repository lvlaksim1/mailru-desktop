package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmInline;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\b&\u0018\u0000 \u001c2\u00020\u0001:\u0003\u001a\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H$J\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005JM\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00172\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0010H\u0002¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation;", "", "<init>", "()V", "buildParentLinks", "", "Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ChildId;", "Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ParentId;", "getNestingLevelsMap", "", "", "computeDepthFor", "", "startId", "parentById", "depthById", "", "path", "", "computeDepthFor-0yU8ui4", "(JLjava/util/Map;Ljava/util/Map;Ljava/util/List;)V", "propagateDepthsFrom", "root", "", "propagateDepthsFrom-KgcCjac", "(JLjava/util/List;Ljava/util/Map;)V", "ChildId", "ParentId", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseNestingLevelsCalculation {
    protected static final long ROOT_PARENT_FALLBACK = -1;

    /* JADX INFO: renamed from: computeDepthFor-0yU8ui4, reason: not valid java name */
    private final void m15537computeDepthFor0yU8ui4(long startId, Map<ChildId, ParentId> parentById, Map<Long, Integer> depthById, List<ChildId> path) {
        int size = parentById.size();
        path.clear();
        for (int i10 = 0; i10 < size; i10++) {
            if (depthById.get(Long.valueOf(startId)) != null) {
                m15538propagateDepthsFromKgcCjac(startId, path, depthById);
                return;
            }
            ParentId parentId = parentById.get(ChildId.m15539boximpl(startId));
            long jM15552unboximpl = parentId != null ? parentId.m15552unboximpl() : -1L;
            if (jM15552unboximpl == -1) {
                depthById.put(Long.valueOf(startId), 0);
                m15538propagateDepthsFromKgcCjac(startId, path, depthById);
                return;
            } else {
                path.add(ChildId.m15539boximpl(startId));
                startId = ChildId.m15540constructorimpl(jM15552unboximpl);
            }
        }
        m15538propagateDepthsFromKgcCjac(startId, path, depthById);
    }

    /* JADX INFO: renamed from: propagateDepthsFrom-KgcCjac, reason: not valid java name */
    private final void m15538propagateDepthsFromKgcCjac(long root, List<ChildId> path, Map<Long, Integer> depthById) {
        Integer num = depthById.get(Long.valueOf(root));
        int iIntValue = num != null ? num.intValue() : 0;
        for (int lastIndex = CollectionsKt.getLastIndex(path); -1 < lastIndex; lastIndex--) {
            iIntValue++;
            depthById.put(Long.valueOf(path.get(lastIndex).m15545unboximpl()), Integer.valueOf(iIntValue));
        }
    }

    @NotNull
    protected abstract Map<ChildId, ParentId> buildParentLinks();

    @NotNull
    public final Map<Long, Integer> getNestingLevelsMap() {
        Map<ChildId, ParentId> mapBuildParentLinks = buildParentLinks();
        if (mapBuildParentLinks.isEmpty()) {
            return MapsKt.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        Iterator<ChildId> it = mapBuildParentLinks.keySet().iterator();
        while (it.hasNext()) {
            long jM15545unboximpl = it.next().m15545unboximpl();
            if (!linkedHashMap.containsKey(Long.valueOf(jM15545unboximpl))) {
                m15537computeDepthFor0yU8ui4(jM15545unboximpl, mapBuildParentLinks, linkedHashMap, arrayList);
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0085@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ChildId;", "", "value", "", "constructor-impl", "(J)J", "getValue", "()J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @JvmInline
    protected static final class ChildId {
        private final long value;

        private /* synthetic */ ChildId(long j10) {
            this.value = j10;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ChildId m15539boximpl(long j10) {
            return new ChildId(j10);
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m15541equalsimpl(long j10, Object obj) {
            return (obj instanceof ChildId) && j10 == ((ChildId) obj).m15545unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m15542equalsimpl0(long j10, long j11) {
            return j10 == j11;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m15543hashCodeimpl(long j10) {
            return Long.hashCode(j10);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m15544toStringimpl(long j10) {
            return "ChildId(value=" + j10 + ")";
        }

        public boolean equals(Object obj) {
            return m15541equalsimpl(this.value, obj);
        }

        public final long getValue() {
            return this.value;
        }

        public int hashCode() {
            return m15543hashCodeimpl(this.value);
        }

        public String toString() {
            return m15544toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ long m15545unboximpl() {
            return this.value;
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static long m15540constructorimpl(long j10) {
            return j10;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0085@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/parser/BaseNestingLevelsCalculation$ParentId;", "", "value", "", "constructor-impl", "(J)J", "getValue", "()J", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @JvmInline
    protected static final class ParentId {
        private final long value;

        private /* synthetic */ ParentId(long j10) {
            this.value = j10;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ ParentId m15546boximpl(long j10) {
            return new ParentId(j10);
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m15548equalsimpl(long j10, Object obj) {
            return (obj instanceof ParentId) && j10 == ((ParentId) obj).m15552unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m15549equalsimpl0(long j10, long j11) {
            return j10 == j11;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m15550hashCodeimpl(long j10) {
            return Long.hashCode(j10);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m15551toStringimpl(long j10) {
            return "ParentId(value=" + j10 + ")";
        }

        public boolean equals(Object obj) {
            return m15548equalsimpl(this.value, obj);
        }

        public final long getValue() {
            return this.value;
        }

        public int hashCode() {
            return m15550hashCodeimpl(this.value);
        }

        public String toString() {
            return m15551toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ long m15552unboximpl() {
            return this.value;
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static long m15547constructorimpl(long j10) {
            return j10;
        }
    }
}
