package com.vk.pushme.database.dao;

import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.vk.pushme.database.converter.Converters;
import com.vk.pushme.database.entity.Subscription;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.offline.bundle.utils.HashRoutingUrl;
import ru.mail.search.offlinecache.OnlineResultEntity;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u001c\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J \u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0096@¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0096@¢\u0006\u0002\u0010\u001eJ\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u001d2\u0006\u0010\u0018\u001a\u00020\u0019H\u0096@¢\u0006\u0002\u0010 J$\u0010!\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00190$H\u0096@¢\u0006\u0002\u0010%R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/vk/pushme/database/dao/SubscriptionDao_Impl;", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfSubscription", "Landroidx/room/EntityInsertAdapter;", "Lcom/vk/pushme/database/entity/Subscription;", "__converters", "Lcom/vk/pushme/database/converter/Converters;", "__deleteAdapterOfSubscription", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfSubscription", "insert", "", OnlineResultEntity.FIELD_ENTITY, "(Lcom/vk/pushme/database/entity/Subscription;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "entities", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "update", "findSubscription", "application", "", "account", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllForApplication", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteForApplicationAndAccounts", "", "accounts", "", "(Ljava/lang/String;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SubscriptionDao_Impl implements SubscriptionDao {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final Converters __converters;

    @NotNull
    private final RoomDatabase __db;

    @NotNull
    private final EntityDeleteOrUpdateAdapter<Subscription> __deleteAdapterOfSubscription;

    @NotNull
    private final EntityInsertAdapter<Subscription> __insertAdapterOfSubscription;

    @NotNull
    private final EntityDeleteOrUpdateAdapter<Subscription> __updateAdapterOfSubscription;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/pushme/database/dao/SubscriptionDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    public SubscriptionDao_Impl(@NotNull RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__converters = new Converters();
        this.__db = __db;
        this.__insertAdapterOfSubscription = new EntityInsertAdapter<Subscription>() { // from class: com.vk.pushme.database.dao.SubscriptionDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `subscription` (`id`,`account`,`application`,`tags`,`include_transports`,`exclude_transports`,`extras`,`from_hour`,`from_minute`,`to_hour`,`to_minute`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Subscription entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
                statement.mo8774bindText(2, entity.getAccount());
                statement.mo8774bindText(3, entity.getApplication());
                String strIntSetToString = SubscriptionDao_Impl.this.__converters.intSetToString(entity.getTags());
                if (strIntSetToString == null) {
                    statement.mo8773bindNull(4);
                } else {
                    statement.mo8774bindText(4, strIntSetToString);
                }
                String strStringSetToString = SubscriptionDao_Impl.this.__converters.stringSetToString(entity.getIncludeTransports());
                if (strStringSetToString == null) {
                    statement.mo8773bindNull(5);
                } else {
                    statement.mo8774bindText(5, strStringSetToString);
                }
                String strStringSetToString2 = SubscriptionDao_Impl.this.__converters.stringSetToString(entity.getExcludeTransports());
                if (strStringSetToString2 == null) {
                    statement.mo8773bindNull(6);
                } else {
                    statement.mo8774bindText(6, strStringSetToString2);
                }
                String extras = entity.getExtras();
                if (extras == null) {
                    statement.mo8773bindNull(7);
                } else {
                    statement.mo8774bindText(7, extras);
                }
                Subscription.DeliveryTime deliveryTime = entity.getDeliveryTime();
                if (deliveryTime == null) {
                    statement.mo8773bindNull(8);
                    statement.mo8773bindNull(9);
                    statement.mo8773bindNull(10);
                    statement.mo8773bindNull(11);
                    return;
                }
                Subscription.TimePoint from = deliveryTime.getFrom();
                statement.mo8772bindLong(8, from.getHour());
                statement.mo8772bindLong(9, from.getMinute());
                Subscription.TimePoint to = deliveryTime.getTo();
                statement.mo8772bindLong(10, to.getHour());
                statement.mo8772bindLong(11, to.getMinute());
            }
        };
        this.__deleteAdapterOfSubscription = new EntityDeleteOrUpdateAdapter<Subscription>() { // from class: com.vk.pushme.database.dao.SubscriptionDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `subscription` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Subscription entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfSubscription = new EntityDeleteOrUpdateAdapter<Subscription>() { // from class: com.vk.pushme.database.dao.SubscriptionDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `subscription` SET `id` = ?,`account` = ?,`application` = ?,`tags` = ?,`include_transports` = ?,`exclude_transports` = ?,`extras` = ?,`from_hour` = ?,`from_minute` = ?,`to_hour` = ?,`to_minute` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Subscription entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo8772bindLong(1, entity.getId());
                statement.mo8774bindText(2, entity.getAccount());
                statement.mo8774bindText(3, entity.getApplication());
                String strIntSetToString = SubscriptionDao_Impl.this.__converters.intSetToString(entity.getTags());
                if (strIntSetToString == null) {
                    statement.mo8773bindNull(4);
                } else {
                    statement.mo8774bindText(4, strIntSetToString);
                }
                String strStringSetToString = SubscriptionDao_Impl.this.__converters.stringSetToString(entity.getIncludeTransports());
                if (strStringSetToString == null) {
                    statement.mo8773bindNull(5);
                } else {
                    statement.mo8774bindText(5, strStringSetToString);
                }
                String strStringSetToString2 = SubscriptionDao_Impl.this.__converters.stringSetToString(entity.getExcludeTransports());
                if (strStringSetToString2 == null) {
                    statement.mo8773bindNull(6);
                } else {
                    statement.mo8774bindText(6, strStringSetToString2);
                }
                String extras = entity.getExtras();
                if (extras == null) {
                    statement.mo8773bindNull(7);
                } else {
                    statement.mo8774bindText(7, extras);
                }
                Subscription.DeliveryTime deliveryTime = entity.getDeliveryTime();
                if (deliveryTime != null) {
                    Subscription.TimePoint from = deliveryTime.getFrom();
                    statement.mo8772bindLong(8, from.getHour());
                    statement.mo8772bindLong(9, from.getMinute());
                    Subscription.TimePoint to = deliveryTime.getTo();
                    statement.mo8772bindLong(10, to.getHour());
                    statement.mo8772bindLong(11, to.getMinute());
                } else {
                    statement.mo8773bindNull(8);
                    statement.mo8773bindNull(9);
                    statement.mo8773bindNull(10);
                    statement.mo8773bindNull(11);
                }
                statement.mo8772bindLong(12, entity.getId());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit delete$lambda$0(SubscriptionDao_Impl subscriptionDao_Impl, Subscription subscription, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        subscriptionDao_Impl.__deleteAdapterOfSubscription.handle(_connection, subscription);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int deleteForApplicationAndAccounts$lambda$0(String str, String str2, Set set, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8774bindText(1, str2);
            Iterator it = set.iterator();
            int i10 = 2;
            while (it.hasNext()) {
                sQLiteStatementPrepare.mo8774bindText(i10, (String) it.next());
                i10++;
            }
            sQLiteStatementPrepare.step();
            return SQLiteConnectionUtil.getTotalChangedRows(_connection);
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Subscription findSubscription$lambda$0(String str, String str2, String str3, SubscriptionDao_Impl subscriptionDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8774bindText(1, str2);
            sQLiteStatementPrepare.mo8774bindText(2, str3);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "account");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "application");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS);
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "include_transports");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "exclude_transports");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, PushProcessor.DATAKEY_EXTRAS);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_hour");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_minute");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_hour");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_minute");
            Subscription subscription = null;
            if (sQLiteStatementPrepare.step()) {
                long j10 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow2);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                Set<Integer> setInt = subscriptionDao_Impl.__converters.toSetInt(sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4));
                if (setInt == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.Int>', but it was NULL.");
                }
                Set<String> setString = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5));
                if (setString == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                Set<String> setString2 = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6));
                if (setString2 == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                subscription = new Subscription(j10, text, text2, setInt, setString, setString2, (sQLiteStatementPrepare.isNull(columnIndexOrThrow8) && sQLiteStatementPrepare.isNull(columnIndexOrThrow9) && sQLiteStatementPrepare.isNull(columnIndexOrThrow10) && sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) ? null : new Subscription.DeliveryTime(new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9)), new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11))), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7));
            }
            sQLiteStatementPrepare.close();
            return subscription;
        } catch (Throwable th2) {
            sQLiteStatementPrepare.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAll$lambda$0(String str, SubscriptionDao_Impl subscriptionDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "account");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "application");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS);
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "include_transports");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "exclude_transports");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, PushProcessor.DATAKEY_EXTRAS);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_hour");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_minute");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_hour");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_minute");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j10 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow2);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                Set<Integer> setInt = subscriptionDao_Impl.__converters.toSetInt(sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4));
                if (setInt == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.Int>', but it was NULL.");
                }
                Set<String> setString = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5));
                if (setString == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                int i10 = columnIndexOrThrow;
                Set<String> setString2 = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6));
                if (setString2 == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                arrayList.add(new Subscription(j10, text, text2, setInt, setString, setString2, (sQLiteStatementPrepare.isNull(columnIndexOrThrow8) && sQLiteStatementPrepare.isNull(columnIndexOrThrow9) && sQLiteStatementPrepare.isNull(columnIndexOrThrow10) && sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) ? null : new Subscription.DeliveryTime(new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9)), new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11))), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7)));
                columnIndexOrThrow2 = columnIndexOrThrow2;
                columnIndexOrThrow = i10;
                columnIndexOrThrow3 = columnIndexOrThrow3;
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            sQLiteStatementPrepare.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAllForApplication$lambda$0(String str, String str2, SubscriptionDao_Impl subscriptionDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo8774bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "account");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "application");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS);
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "include_transports");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "exclude_transports");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, PushProcessor.DATAKEY_EXTRAS);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_hour");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "from_minute");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_hour");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "to_minute");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j10 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow2);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                Set<Integer> setInt = subscriptionDao_Impl.__converters.toSetInt(sQLiteStatementPrepare.isNull(columnIndexOrThrow4) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow4));
                if (setInt == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.Int>', but it was NULL.");
                }
                Set<String> setString = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow5) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow5));
                if (setString == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                int i10 = columnIndexOrThrow;
                Set<String> setString2 = subscriptionDao_Impl.__converters.toSetString(sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow6));
                if (setString2 == null) {
                    throw new IllegalStateException("Expected NON-NULL 'kotlin.collections.Set<kotlin.String>', but it was NULL.");
                }
                arrayList.add(new Subscription(j10, text, text2, setInt, setString, setString2, (sQLiteStatementPrepare.isNull(columnIndexOrThrow8) && sQLiteStatementPrepare.isNull(columnIndexOrThrow9) && sQLiteStatementPrepare.isNull(columnIndexOrThrow10) && sQLiteStatementPrepare.isNull(columnIndexOrThrow11)) ? null : new Subscription.DeliveryTime(new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9)), new Subscription.TimePoint((int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11))), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7)));
                columnIndexOrThrow2 = columnIndexOrThrow2;
                columnIndexOrThrow = i10;
                columnIndexOrThrow3 = columnIndexOrThrow3;
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th2) {
            sQLiteStatementPrepare.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$0(SubscriptionDao_Impl subscriptionDao_Impl, Subscription subscription, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        subscriptionDao_Impl.__insertAdapterOfSubscription.insert(_connection, subscription);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit insert$lambda$1(SubscriptionDao_Impl subscriptionDao_Impl, Iterable iterable, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        subscriptionDao_Impl.__insertAdapterOfSubscription.insert(_connection, (Iterable<? extends Subscription>) iterable);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit update$lambda$0(SubscriptionDao_Impl subscriptionDao_Impl, Subscription subscription, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        subscriptionDao_Impl.__updateAdapterOfSubscription.handle(_connection, subscription);
        return Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object delete(@NotNull final Subscription subscription, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.t
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.delete$lambda$0(this.f51479a, subscription, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object deleteForApplicationAndAccounts(@NotNull final String str, @NotNull final Set<String> set, @NotNull Continuation<? super Integer> continuation) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("DELETE FROM subscription WHERE application = ");
        sb2.append(HashRoutingUrl.END_HASH_ROUTING);
        sb2.append(" AND account IN (");
        StringUtil.appendPlaceholders(sb2, set.size());
        sb2.append(")");
        final String string = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.w
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(SubscriptionDao_Impl.deleteForApplicationAndAccounts$lambda$0(string, str, set, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object findSubscription(@NotNull final String str, @NotNull final String str2, @NotNull Continuation<? super Subscription> continuation) {
        final String str3 = "SELECT * FROM subscription WHERE application = ? AND account = ?";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.u
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.findSubscription$lambda$0(str3, str, str2, this, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object getAll(@NotNull Continuation<? super List<Subscription>> continuation) {
        final String str = "SELECT * FROM subscription ORDER BY id";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.q
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.getAll$lambda$0(str, this, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object getAllForApplication(@NotNull final String str, @NotNull Continuation<? super List<Subscription>> continuation) {
        final String str2 = "SELECT * FROM subscription WHERE application = ? ORDER BY id";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.vk.pushme.database.dao.r
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.getAllForApplication$lambda$0(str2, str, this, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object insert(@NotNull final Subscription subscription, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.s
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.insert$lambda$0(this.f51477a, subscription, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object update(@NotNull final Subscription subscription, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.v
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.update$lambda$0(this.f51485a, subscription, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    @Override // com.vk.pushme.database.dao.SubscriptionDao
    @Nullable
    public Object insert(@NotNull final Iterable<Subscription> iterable, @NotNull Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.vk.pushme.database.dao.x
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubscriptionDao_Impl.insert$lambda$1(this.f51490a, iterable, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }
}
