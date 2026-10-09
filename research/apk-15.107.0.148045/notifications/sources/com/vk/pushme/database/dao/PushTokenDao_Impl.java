package com.vk.pushme.database.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.vk.pushme.database.entity.PushToken;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0094@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0011\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0096@¢\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/vk/pushme/database/dao/PushTokenDao_Impl;", "Lcom/vk/pushme/database/dao/PushTokenDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfPushToken", "Landroidx/room/EntityInsertAdapter;", "Lcom/vk/pushme/database/entity/PushToken;", "__deleteAdapterOfPushToken", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "insert", "", OnlineResultEntity.FIELD_ENTITY, "(Lcom/vk/pushme/database/entity/PushToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "replaceTokenForGivenTransport", "newToken", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByToken", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForTransport", "transport", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushTokenDao_Impl extends PushTokenDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final RoomDatabase __db;

    @NotNull
    private final EntityDeleteOrUpdateAdapter<PushToken> __deleteAdapterOfPushToken;

    @NotNull
    private final EntityInsertAdapter<PushToken> __insertAdapterOfPushToken;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/database/dao/PushTokenDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    /* JADX INFO: renamed from: com.vk.pushme.database.dao.PushTokenDao_Impl$replaceTokenForGivenTransport$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Lcom/vk/pushme/database/entity/PushToken;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.database.dao.PushTokenDao_Impl$replaceTokenForGivenTransport$2", f = "PushTokenDao_Impl.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C10712 extends SuspendLambda implements Function1<Continuation<? super PushToken>, Object> {
        final /* synthetic */ PushToken $newToken;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10712(PushToken pushToken, Continuation<? super C10712> continuation) {
            super(1, continuation);
            this.$newToken = pushToken;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return PushTokenDao_Impl.this.new C10712(this.$newToken, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            PushTokenDao_Impl pushTokenDao_Impl = PushTokenDao_Impl.this;
            PushToken pushToken = this.$newToken;
            this.label = 1;
            Object objReplaceTokenForGivenTransport = PushTokenDao_Impl.super.replaceTokenForGivenTransport(pushToken, this);
            return objReplaceTokenForGivenTransport == coroutine_suspended ? coroutine_suspended : objReplaceTokenForGivenTransport;
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super PushToken> continuation) {
            return ((C10712) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public PushTokenDao_Impl(@NotNull RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfPushToken = new EntityInsertAdapter<PushToken>() { // from class: com.vk.pushme.database.dao.PushTokenDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR ABORT INTO `push_token` (`id`,`token`,`transport`,`creation_date`) VALUES (nullif(?, 0),?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, PushToken entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
                statement.mo8774bindText(2, entity.getToken());
                statement.mo8774bindText(3, entity.getTransport());
                statement.mo8772bindLong(4, entity.getCreationDate());
            }
        };
        this.__deleteAdapterOfPushToken = new EntityDeleteOrUpdateAdapter<PushToken>() { // from class: com.vk.pushme.database.dao.PushTokenDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `push_token` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, PushToken entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$0(PushTokenDao_Impl pushTokenDao_Impl, PushToken pushToken, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pushTokenDao_Impl.__deleteAdapterOfPushToken.handle(_connection, pushToken);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAll$lambda$0(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "token");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "transport");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "creation_date");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new PushToken(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3), sQLiteStatementPrepare.getLong(columnIndexOrThrow4)));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            sQLiteStatementPrepare.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushToken getById$lambda$0(String str, long j10, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8772bindLong(1, j10);
            return sQLiteStatementPrepare.step() ? new PushToken(sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "token")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "transport")), sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "creation_date"))) : null;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushToken getByToken$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8774bindText(1, str2);
            return sQLiteStatementPrepare.step() ? new PushToken(sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "token")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "transport")), sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "creation_date"))) : null;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushToken getForTransport$lambda$0(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8774bindText(1, str2);
            return sQLiteStatementPrepare.step() ? new PushToken(sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "token")), sQLiteStatementPrepare.getText(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "transport")), sQLiteStatementPrepare.getLong(SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "creation_date"))) : null;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$0(PushTokenDao_Impl pushTokenDao_Impl, PushToken pushToken, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        pushTokenDao_Impl.__insertAdapterOfPushToken.insert(_connection, pushToken);
        return Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object delete(@NotNull final PushToken pushToken, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.p
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.delete$lambda$0(this.f51470a, pushToken, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object getAll(@NotNull Continuation<? super List<PushToken>> continuation) {
        final String str = "SELECT * FROM push_token";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.l
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.getAll$lambda$0(str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object getById(final long j10, @NotNull Continuation<? super PushToken> continuation) {
        final String str = "SELECT * FROM push_token WHERE id = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.n
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.getById$lambda$0(str, j10, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object getByToken(@NotNull final String str, @NotNull Continuation<? super PushToken> continuation) {
        final String str2 = "SELECT * FROM push_token WHERE token = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.m
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.getByToken$lambda$0(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object getForTransport(@NotNull final String str, @NotNull Continuation<? super PushToken> continuation) {
        final String str2 = "SELECT * FROM push_token WHERE transport = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.k
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.getForTransport$lambda$0(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    protected Object insert(@NotNull final PushToken pushToken, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.o
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushTokenDao_Impl.insert$lambda$0(this.f51468a, pushToken, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.PushTokenDao
    @Nullable
    public Object replaceTokenForGivenTransport(@NotNull PushToken pushToken, @NotNull Continuation<? super PushToken> continuation) {
        return DBUtil.performInTransactionSuspending(this.__db, new C10712(pushToken, null), continuation);
    }
}
