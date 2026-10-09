package com.vk.pushme.database.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.vk.pushme.database.entity.Push;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0013J\u000e\u0010\u0014\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/vk/pushme/database/dao/PushDao_Impl;", "Lcom/vk/pushme/database/dao/PushDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfPush", "Landroidx/room/EntityInsertAdapter;", "Lcom/vk/pushme/database/entity/Push;", "__deleteAdapterOfPush", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insert", "", OnlineResultEntity.FIELD_ENTITY, "(Lcom/vk/pushme/database/entity/Push;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllReceivedBefore", "", "timestamp", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushDao_Impl implements PushDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final RoomDatabase __db;

    @NotNull
    private final EntityDeleteOrUpdateAdapter<Push> __deleteAdapterOfPush;

    @NotNull
    private final EntityInsertAdapter<Push> __insertAdapterOfPush;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/database/dao/PushDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }

        private Companion() {
        }
    }

    public PushDao_Impl(@NotNull RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfPush = new EntityInsertAdapter<Push>() { // from class: com.vk.pushme.database.dao.PushDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR ABORT INTO `push` (`id`,`transport`,`open_url`,`receive_time`) VALUES (nullif(?, 0),?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Push entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
                statement.mo8774bindText(2, entity.getTransport());
                statement.mo8774bindText(3, entity.getOpenUrl());
                statement.mo8772bindLong(4, entity.getReceiveTime());
            }
        };
        this.__deleteAdapterOfPush = new EntityDeleteOrUpdateAdapter<Push>() { // from class: com.vk.pushme.database.dao.PushDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `push` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Push entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long countAll$lambda$0(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            return sQLiteStatementPrepare.step() ? sQLiteStatementPrepare.getLong(0) : 0L;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$0(PushDao_Impl pushDao_Impl, Push push, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pushDao_Impl.__deleteAdapterOfPush.handle(_connection, push);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int deleteAllReceivedBefore$lambda$0(String str, long j10, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8772bindLong(1, j10);
            sQLiteStatementPrepare.step();
            return SQLiteConnectionUtil.getTotalChangedRows(_connection);
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Push getById$lambda$0(String str, long j10, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8772bindLong(1, j10);
            return sQLiteStatementPrepare.step() ? new Push(sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "transport")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "open_url")), sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "receive_time"))) : null;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long insert$lambda$0(PushDao_Impl pushDao_Impl, Push push, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        return pushDao_Impl.__insertAdapterOfPush.insertAndReturnId(_connection, push);
    }

    @Override // com.vk.pushme.database.dao.PushDao
    @Nullable
    public Object countAll(@NotNull Continuation<? super Long> continuation) {
        final String str = "SELECT COUNT(*) FROM push";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.i
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(PushDao_Impl.countAll$lambda$0(str, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushDao
    @Nullable
    public Object delete(@NotNull final Push push, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.g
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushDao_Impl.delete$lambda$0(this.f51454a, push, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PushDao
    @Nullable
    public Object deleteAllReceivedBefore(final long j10, @NotNull Continuation<? super Integer> continuation) {
        final String str = "DELETE FROM push WHERE receive_time < ?";
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.f
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(PushDao_Impl.deleteAllReceivedBefore$lambda$0(str, j10, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushDao
    @Nullable
    public Object getById(final long j10, @NotNull Continuation<? super Push> continuation) {
        final String str = "SELECT * FROM push WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.j
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushDao_Impl.getById$lambda$0(str, j10, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushDao
    @Nullable
    public Object insert(@NotNull final Push push, @NotNull Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.h
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(PushDao_Impl.insert$lambda$0(this.f51456a, push, (SQLiteConnection) obj));
            }
        }, continuation);
    }
}
