package com.vk.pushme.database.converter;

import androidx.room.TypeConverter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007J\u001a\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005H\u0007¨\u0006\r"}, d2 = {"Lcom/vk/pushme/database/converter/Converters;", "", "<init>", "()V", "toSetInt", "", "", "data", "", "intSetToString", "toSetString", "stringSetToString", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConverters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Converters.kt\ncom/vk/pushme/database/converter/Converters\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1617#2,9:46\n1869#2:55\n1870#2:57\n1626#2:58\n774#2:59\n865#2,2:60\n1#3:56\n*S KotlinDebug\n*F\n+ 1 Converters.kt\ncom/vk/pushme/database/converter/Converters\n*L\n11#1:46,9\n11#1:55\n11#1:57\n11#1:58\n29#1:59\n29#1:60,2\n11#1:56\n*E\n"})
public final class Converters {

    @NotNull
    private static final String SEPARATOR = ";";

    @TypeConverter
    @Nullable
    public final String intSetToString(@Nullable Set<Integer> data) {
        if (data == null) {
            return null;
        }
        return data.isEmpty() ? "" : CollectionsKt.joinToString$default(data, ";", null, null, 0, null, null, 62, null);
    }

    @TypeConverter
    @Nullable
    public final String stringSetToString(@Nullable Set<String> data) {
        if (data == null) {
            return null;
        }
        return data.isEmpty() ? "" : CollectionsKt.joinToString$default(data, ";", null, null, 0, null, null, 62, null);
    }

    @TypeConverter
    @Nullable
    public final Set<Integer> toSetInt(@Nullable String data) {
        if (data == null) {
            return null;
        }
        if (data.length() == 0) {
            return SetsKt.emptySet();
        }
        List listSplit$default = StringsKt.split$default((CharSequence) data, new String[]{";"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull((String) it.next());
            if (intOrNull != null) {
                arrayList.add(intOrNull);
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    @TypeConverter
    @Nullable
    public final Set<String> toSetString(@Nullable String data) {
        if (data == null) {
            return null;
        }
        if (data.length() == 0) {
            return SetsKt.emptySet();
        }
        List listSplit$default = StringsKt.split$default((CharSequence) data, new String[]{";"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit$default) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.toSet(arrayList);
    }
}
