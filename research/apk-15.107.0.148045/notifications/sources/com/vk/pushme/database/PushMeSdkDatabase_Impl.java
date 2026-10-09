package com.vk.pushme.database;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.PendingActionDao_Impl;
import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.database.dao.PushDao_Impl;
import com.vk.pushme.database.dao.PushTokenDao;
import com.vk.pushme.database.dao.PushTokenDao_Impl;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.database.dao.SubscriptionDao_Impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\"\u0010\u0013\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u00160\u0014H\u0014J\u0016\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u00150\u0018H\u0016J*\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00162\u001a\u0010\u001c\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00190\u0015\u0012\u0004\u0012\u00020\u00190\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\bH\u0016J\b\u0010\u001f\u001a\u00020\nH\u0016J\b\u0010 \u001a\u00020\fH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/vk/pushme/database/PushMeSdkDatabase_Impl;", "Lcom/vk/pushme/database/PushMeSdkDatabase;", "<init>", "()V", "_subscriptionDao", "Lkotlin/Lazy;", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "_pushTokenDao", "Lcom/vk/pushme/database/dao/PushTokenDao;", "_pushDao", "Lcom/vk/pushme/database/dao/PushDao;", "_pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "createOpenDelegate", "Landroidx/room/RoomOpenDelegate;", "createInvalidationTracker", "Landroidx/room/InvalidationTracker;", "clearAllTables", "", "getRequiredTypeConverterClasses", "", "Lkotlin/reflect/KClass;", "", "getRequiredAutoMigrationSpecClasses", "", "Landroidx/room/migration/AutoMigrationSpec;", "createAutoMigrations", "Landroidx/room/migration/Migration;", "autoMigrationSpecs", "subscriptionDao", "pushTokenDao", "pushDao", "pendingActionDao", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeSdkDatabase_Impl extends PushMeSdkDatabase {

    @NotNull
    private final Lazy<SubscriptionDao> _subscriptionDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.database.a
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PushMeSdkDatabase_Impl._subscriptionDao$lambda$0(this.f51437a);
        }
    });

    @NotNull
    private final Lazy<PushTokenDao> _pushTokenDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.database.b
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PushMeSdkDatabase_Impl._pushTokenDao$lambda$0(this.f51438a);
        }
    });

    @NotNull
    private final Lazy<PushDao> _pushDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.database.c
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PushMeSdkDatabase_Impl._pushDao$lambda$0(this.f51439a);
        }
    });

    @NotNull
    private final Lazy<PendingActionDao> _pendingActionDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.database.d
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PushMeSdkDatabase_Impl._pendingActionDao$lambda$0(this.f51440a);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final PendingActionDao_Impl _pendingActionDao$lambda$0(PushMeSdkDatabase_Impl pushMeSdkDatabase_Impl) {
        return new PendingActionDao_Impl(pushMeSdkDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushDao_Impl _pushDao$lambda$0(PushMeSdkDatabase_Impl pushMeSdkDatabase_Impl) {
        return new PushDao_Impl(pushMeSdkDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushTokenDao_Impl _pushTokenDao$lambda$0(PushMeSdkDatabase_Impl pushMeSdkDatabase_Impl) {
        return new PushTokenDao_Impl(pushMeSdkDatabase_Impl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubscriptionDao_Impl _subscriptionDao$lambda$0(PushMeSdkDatabase_Impl pushMeSdkDatabase_Impl) {
        return new SubscriptionDao_Impl(pushMeSdkDatabase_Impl);
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.performClear(false, "subscription", "pending_action", "push_token", "push");
    }

    @Override // androidx.room.RoomDatabase
    @NotNull
    public List<Migration> createAutoMigrations(@NotNull Map<KClass<? extends AutoMigrationSpec>, ? extends AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        return new ArrayList();
    }

    @Override // androidx.room.RoomDatabase
    @NotNull
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new LinkedHashMap(), new LinkedHashMap(), "subscription", "pending_action", "push_token", "push");
    }

    @Override // androidx.room.RoomDatabase
    @NotNull
    public Set<KClass<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecClasses() {
        return new LinkedHashSet();
    }

    @Override // androidx.room.RoomDatabase
    @NotNull
    protected Map<KClass<?>, List<KClass<?>>> getRequiredTypeConverterClasses() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(SubscriptionDao.class), SubscriptionDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(PushTokenDao.class), PushTokenDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(PushDao.class), PushDao_Impl.INSTANCE.getRequiredConverters());
        linkedHashMap.put(Reflection.getOrCreateKotlinClass(PendingActionDao.class), PendingActionDao_Impl.INSTANCE.getRequiredConverters());
        return linkedHashMap;
    }

    @Override // com.vk.pushme.database.PushMeSdkDatabase
    @NotNull
    public PendingActionDao pendingActionDao() {
        return this._pendingActionDao.getValue();
    }

    @Override // com.vk.pushme.database.PushMeSdkDatabase
    @NotNull
    public PushDao pushDao() {
        return this._pushDao.getValue();
    }

    @Override // com.vk.pushme.database.PushMeSdkDatabase
    @NotNull
    public PushTokenDao pushTokenDao() {
        return this._pushTokenDao.getValue();
    }

    @Override // com.vk.pushme.database.PushMeSdkDatabase
    @NotNull
    public SubscriptionDao subscriptionDao() {
        return this._subscriptionDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.room.RoomDatabase
    @NotNull
    public RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: com.vk.pushme.database.PushMeSdkDatabase_Impl$createOpenDelegate$_openDelegate$1
            {
                super(1, "d976194e783c058a6f12d4e09be8ac2a", "d7fb593c7c83ed98eab6a0a27713bb71");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void createAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `subscription` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `account` TEXT NOT NULL, `application` TEXT NOT NULL, `tags` TEXT NOT NULL, `include_transports` TEXT NOT NULL, `exclude_transports` TEXT NOT NULL, `extras` TEXT, `from_hour` INTEGER, `from_minute` INTEGER, `to_hour` INTEGER, `to_minute` INTEGER)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_subscription_application_account` ON `subscription` (`application`, `account`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `pending_action` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT NOT NULL, `data` TEXT NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `push_token` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `token` TEXT NOT NULL, `transport` TEXT NOT NULL, `creation_date` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_push_token_token` ON `push_token` (`token`)");
                SQLite.execSQL(connection, "CREATE UNIQUE INDEX IF NOT EXISTS `index_push_token_transport` ON `push_token` (`transport`)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `push` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `transport` TEXT NOT NULL, `open_url` TEXT NOT NULL, `receive_time` INTEGER NOT NULL)");
                SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd976194e783c058a6f12d4e09be8ac2a')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void dropAllTables(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `subscription`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `pending_action`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `push_token`");
                SQLite.execSQL(connection, "DROP TABLE IF EXISTS `push`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onCreate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onOpen(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                this.this$0.internalInitInvalidationTracker(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPostMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
            }

            @Override // androidx.room.RoomOpenDelegate
            public void onPreMigrate(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                DBUtil.dropFtsSyncTriggers(connection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection connection) {
                Intrinsics.checkNotNullParameter(connection, "connection");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap.put("account", new TableInfo.Column("account", "TEXT", true, 0, null, 1));
                linkedHashMap.put("application", new TableInfo.Column("application", "TEXT", true, 0, null, 1));
                linkedHashMap.put(ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, new TableInfo.Column(ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "TEXT", true, 0, null, 1));
                linkedHashMap.put("include_transports", new TableInfo.Column("include_transports", "TEXT", true, 0, null, 1));
                linkedHashMap.put("exclude_transports", new TableInfo.Column("exclude_transports", "TEXT", true, 0, null, 1));
                linkedHashMap.put(PushProcessor.DATAKEY_EXTRAS, new TableInfo.Column(PushProcessor.DATAKEY_EXTRAS, "TEXT", false, 0, null, 1));
                linkedHashMap.put("from_hour", new TableInfo.Column("from_hour", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("from_minute", new TableInfo.Column("from_minute", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("to_hour", new TableInfo.Column("to_hour", "INTEGER", false, 0, null, 1));
                linkedHashMap.put("to_minute", new TableInfo.Column("to_minute", "INTEGER", false, 0, null, 1));
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                linkedHashSet2.add(new TableInfo.Index("index_subscription_application_account", true, CollectionsKt.listOf((Object[]) new String[]{"application", "account"}), CollectionsKt.listOf((Object[]) new String[]{"ASC", "ASC"})));
                TableInfo tableInfo = new TableInfo("subscription", linkedHashMap, linkedHashSet, linkedHashSet2);
                TableInfo.Companion companion = TableInfo.INSTANCE;
                TableInfo tableInfo2 = companion.read(connection, "subscription");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenDelegate.ValidationResult(false, "subscription(com.vk.pushme.database.entity.Subscription).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                linkedHashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap2.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, 1));
                linkedHashMap2.put("data", new TableInfo.Column("data", "TEXT", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("pending_action", linkedHashMap2, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo4 = companion.read(connection, "pending_action");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenDelegate.ValidationResult(false, "pending_action(com.vk.pushme.database.entity.PendingAction).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                linkedHashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap3.put("token", new TableInfo.Column("token", "TEXT", true, 0, null, 1));
                linkedHashMap3.put("transport", new TableInfo.Column("transport", "TEXT", true, 0, null, 1));
                linkedHashMap3.put("creation_date", new TableInfo.Column("creation_date", "INTEGER", true, 0, null, 1));
                LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                linkedHashSet4.add(new TableInfo.Index("index_push_token_token", true, CollectionsKt.listOf("token"), CollectionsKt.listOf("ASC")));
                linkedHashSet4.add(new TableInfo.Index("index_push_token_transport", true, CollectionsKt.listOf("transport"), CollectionsKt.listOf("ASC")));
                TableInfo tableInfo5 = new TableInfo("push_token", linkedHashMap3, linkedHashSet3, linkedHashSet4);
                TableInfo tableInfo6 = companion.read(connection, "push_token");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenDelegate.ValidationResult(false, "push_token(com.vk.pushme.database.entity.PushToken).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                linkedHashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                linkedHashMap4.put("transport", new TableInfo.Column("transport", "TEXT", true, 0, null, 1));
                linkedHashMap4.put("open_url", new TableInfo.Column("open_url", "TEXT", true, 0, null, 1));
                linkedHashMap4.put("receive_time", new TableInfo.Column("receive_time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("push", linkedHashMap4, new LinkedHashSet(), new LinkedHashSet());
                TableInfo tableInfo8 = companion.read(connection, "push");
                if (tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenDelegate.ValidationResult(true, null);
                }
                return new RoomOpenDelegate.ValidationResult(false, "push(com.vk.pushme.database.entity.Push).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
            }
        };
    }
}
