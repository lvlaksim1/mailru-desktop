package com.vk.pushme.database.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.vk.pushme.database.entity.PendingAction;
import java.util.ArrayList;
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
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0010H\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/vk/pushme/database/dao/PendingActionDao_Impl;", "Lcom/vk/pushme/database/dao/PendingActionDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfPendingAction", "Landroidx/room/EntityInsertAdapter;", "Lcom/vk/pushme/database/entity/PendingAction;", "__deleteAdapterOfPendingAction", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insert", "", OnlineResultEntity.FIELD_ENTITY, "(Lcom/vk/pushme/database/entity/PendingAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "entities", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "getAll", "", "limit", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllOlderThan", "lastRecordId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PendingActionDao_Impl implements PendingActionDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final RoomDatabase __db;

    @NotNull
    private final EntityDeleteOrUpdateAdapter<PendingAction> __deleteAdapterOfPendingAction;

    @NotNull
    private final EntityInsertAdapter<PendingAction> __insertAdapterOfPendingAction;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/database/dao/PendingActionDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    public PendingActionDao_Impl(@NotNull RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfPendingAction = new EntityInsertAdapter<PendingAction>() { // from class: com.vk.pushme.database.dao.PendingActionDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR ABORT INTO `pending_action` (`id`,`type`,`data`) VALUES (nullif(?, 0),?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, PendingAction entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
                statement.mo8774bindText(2, entity.getType());
                statement.mo8774bindText(3, entity.getData());
            }
        };
        this.__deleteAdapterOfPendingAction = new EntityDeleteOrUpdateAdapter<PendingAction>() { // from class: com.vk.pushme.database.dao.PendingActionDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `pending_action` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, PendingAction entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$0(PendingActionDao_Impl pendingActionDao_Impl, PendingAction pendingAction, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pendingActionDao_Impl.__deleteAdapterOfPendingAction.handle(_connection, pendingAction);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int deleteAllOlderThan$lambda$0(String str, long j10, SQLiteConnection _connection) {
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
    public static final List getAll$lambda$0(String str, int i10, int i11, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8772bindLong(1, i10);
            sQLiteStatementPrepare.mo8772bindLong(2, i11);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "type");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "data");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new PendingAction(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3)));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            sQLiteStatementPrepare.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$0(PendingActionDao_Impl pendingActionDao_Impl, PendingAction pendingAction, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pendingActionDao_Impl.__insertAdapterOfPendingAction.insert(_connection, pendingAction);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$1(PendingActionDao_Impl pendingActionDao_Impl, Iterable iterable, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pendingActionDao_Impl.__insertAdapterOfPendingAction.insert(_connection, (Iterable<? extends PendingAction>) iterable);
        return Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PendingActionDao
    @Nullable
    public Object delete(@NotNull final PendingAction pendingAction, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingActionDao_Impl.delete$lambda$0(this.f51441a, pendingAction, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PendingActionDao
    @Nullable
    public Object deleteAllOlderThan(final long j10, @NotNull Continuation<? super Integer> continuation) {
        final String str = "DELETE FROM pending_action WHERE id <= ?";
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.e
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(PendingActionDao_Impl.deleteAllOlderThan$lambda$0(str, j10, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PendingActionDao
    @Nullable
    public Object getAll(final int i10, final int i11, @NotNull Continuation<? super List<PendingAction>> continuation) {
        final String str = "SELECT * FROM pending_action ORDER BY id LIMIT ? OFFSET ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.c
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingActionDao_Impl.getAll$lambda$0(str, i10, i11, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PendingActionDao
    @Nullable
    public Object insert(@NotNull final PendingAction pendingAction, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.b
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingActionDao_Impl.insert$lambda$0(this.f51443a, pendingAction, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PendingActionDao
    @Nullable
    public Object insert(@NotNull final Iterable<PendingAction> iterable, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.d
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PendingActionDao_Impl.insert$lambda$1(this.f51448a, iterable, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }
}
