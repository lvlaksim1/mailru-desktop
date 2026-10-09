package com.vk.pushme.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Entity(indices = {@Index(unique = true, value = {"token"}), @Index(unique = true, value = {"transport"})}, tableName = "push_token")
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/database/entity/PushToken;", "", "id", "", "token", "", "transport", "creationDate", "<init>", "(JLjava/lang/String;Ljava/lang/String;J)V", "getId", "()J", "getToken", "()Ljava/lang/String;", "getTransport", "getCreationDate", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushToken {

    @ColumnInfo(name = "creation_date")
    private final long creationDate;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private final long id;

    @ColumnInfo(name = "token")
    @NotNull
    private final String token;

    @ColumnInfo(name = "transport")
    @NotNull
    private final String transport;

    public PushToken(long j10, @NotNull String token, @NotNull String transport, long j11) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(transport, "transport");
        this.id = j10;
        this.token = token;
        this.transport = transport;
        this.creationDate = j11;
    }

    public final long getCreationDate() {
        return this.creationDate;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    @NotNull
    public final String getTransport() {
        return this.transport;
    }

    public /* synthetic */ PushToken(long j10, String str, String str2, long j11, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? 0L : j10, str, str2, j11);
    }
}
