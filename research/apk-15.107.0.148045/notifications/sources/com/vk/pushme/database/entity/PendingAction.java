package com.vk.pushme.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Entity(tableName = "pending_action")
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/vk/pushme/database/entity/PendingAction;", "", "id", "", "type", "", "data", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getType", "()Ljava/lang/String;", "getData", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PendingAction {

    @ColumnInfo(name = "data")
    @NotNull
    private final String data;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;

    @ColumnInfo(name = "type")
    @NotNull
    private final String type;

    public PendingAction(long j10, @NotNull String type, @NotNull String data) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(data, "data");
        this.id = j10;
        this.type = type;
        this.data = data;
    }

    @NotNull
    public final String getData() {
        return this.data;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public /* synthetic */ PendingAction(long j10, String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? 0L : j10, str, str2);
    }
}
