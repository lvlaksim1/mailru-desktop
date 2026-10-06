package com.vk.superapp.sessionmanagment.impl.data.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.vk.stat.model.builders.StorageSizeEventBuilder;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "Replace with SessionsPrefStorage")
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\"\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/vk/superapp/sessionmanagment/impl/data/source/SessionSQLiteHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreate", "", StorageSizeEventBuilder.STORAGE_NAME_DATABASES_DIR, "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "DB", "Companion", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SessionSQLiteHelper extends SQLiteOpenHelper {

    @NotNull
    public static final String COLUMN_AVATAR = "avatar";

    @NotNull
    public static final String COLUMN_CREATED_MS = "created_ms";

    @NotNull
    public static final String COLUMN_EMAIL = "email";

    @NotNull
    public static final String COLUMN_EXPIRES_IN_SEC = "expires_in_sec";

    @NotNull
    public static final String COLUMN_FIRST_NAME = "first_name";

    @NotNull
    public static final String COLUMN_LAST_NAME = "last_name";

    @NotNull
    public static final String COLUMN_PHONE = "phone";

    @NotNull
    public static final String COLUMN_PROFILE_TYPE = "profile_type";

    @NotNull
    public static final String COLUMN_TOKEN = "token_value";

    @NotNull
    public static final String COLUMN_USER_ID = "user_id";

    @NotNull
    public static final String COLUMN_USER_STATE = "user_state";

    @NotNull
    public static final String COLUMN_WEBVIEW_AT = "webview_access_token";

    @NotNull
    public static final String COLUMN_WEBVIEW_AT_EXPIRED = "webview_access_token_expired";

    @NotNull
    public static final String COLUMN_WEBVIEW_RT = "webview_refresh_token";

    @NotNull
    public static final String COLUMN_WEBVIEW_RT_EXPIRED = "webview_refresh_token_expired";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String TABLE_AUTHORIZED = "sessions_authorized";

    @NotNull
    public static final String TABLE_EXCHANGE = "sessions_exchange";

    @NotNull
    private static final List<String> lpmitnemganamnoisseskvmoca = CollectionsKt.listOf((Object[]) new String[]{TABLE_AUTHORIZED, TABLE_EXCHANGE});

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/vk/superapp/sessionmanagment/impl/data/source/SessionSQLiteHelper$Companion;", "Landroid/provider/BaseColumns;", "<init>", "()V", "TABLE_AUTHORIZED", "", "TABLE_EXCHANGE", "TABLES", "", "getTABLES", "()Ljava/util/List;", "COLUMN_TOKEN", "COLUMN_USER_ID", "COLUMN_FIRST_NAME", "COLUMN_LAST_NAME", "COLUMN_EMAIL", "COLUMN_PHONE", "COLUMN_AVATAR", "COLUMN_PROFILE_TYPE", "COLUMN_EXPIRES_IN_SEC", "COLUMN_CREATED_MS", "COLUMN_WEBVIEW_AT", "COLUMN_WEBVIEW_AT_EXPIRED", "COLUMN_WEBVIEW_RT", "COLUMN_WEBVIEW_RT_EXPIRED", "COLUMN_USER_STATE", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion implements BaseColumns {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<String> getTABLES() {
            return SessionSQLiteHelper.lpmitnemganamnoisseskvmoca;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0006\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0010\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/sessionmanagment/impl/data/source/SessionSQLiteHelper$DB;", "", "<init>", "()V", "", "NAME", "Ljava/lang/String;", "", "VERSION", "I", "lpmitnemganamnoisseskvmoca", "getSQL_CREATE_AUTHORIZED", "()Ljava/lang/String;", "SQL_CREATE_AUTHORIZED", "lpmitnemganamnoisseskvmocb", "getSQL_CREATE_EXCHANGE", "SQL_CREATE_EXCHANGE", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class DB {

        @NotNull
        public static final String NAME = "Sessions.db";
        public static final int VERSION = 1;

        @NotNull
        public static final DB INSTANCE = new DB();

        /* JADX INFO: renamed from: lpmitnemganamnoisseskvmoca, reason: from kotlin metadata */
        @NotNull
        private static final String SQL_CREATE_AUTHORIZED = "CREATE TABLE sessions_authorized(\n    token_value TEXT NOT NULL PRIMARY KEY,\n    \n    expires_in_sec INTEGER NOT NULL,\n    created_ms INTEGER NOT NULL,\n    webview_access_token TEXT,\n    webview_refresh_token TEXT,\n    webview_access_token_expired INTEGER,\n    webview_refresh_token_expired INTEGER,                \n    \n    user_id INTEGER NOT NULL UNIQUE,\n    first_name TEXT,\n    last_name TEXT,\n    email TEXT,\n    phone TEXT,\n    avatar TEXT,\n    profile_type INTEGER NOT NULL,\n    \n    user_state TEXT NOT NULL\n)";

        /* JADX INFO: renamed from: lpmitnemganamnoisseskvmocb, reason: from kotlin metadata */
        @NotNull
        private static final String SQL_CREATE_EXCHANGE = "CREATE TABLE sessions_exchange(\n    token_value TEXT NOT NULL PRIMARY KEY,\n    user_id INTEGER NOT NULL UNIQUE,\n    first_name TEXT,\n    last_name TEXT,\n    email TEXT,\n    phone TEXT,\n    avatar TEXT,\n    profile_type INTEGER NOT NULL\n)";

        private DB() {
        }

        @NotNull
        public final String getSQL_CREATE_AUTHORIZED() {
            return SQL_CREATE_AUTHORIZED;
        }

        @NotNull
        public final String getSQL_CREATE_EXCHANGE() {
            return SQL_CREATE_EXCHANGE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionSQLiteHelper(@NotNull Context context) {
        super(context, DB.NAME, (SQLiteDatabase.CursorFactory) null, 1);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(@NotNull SQLiteDatabase db2) {
        Intrinsics.checkNotNullParameter(db2, "db");
        DB db3 = DB.INSTANCE;
        db2.execSQL(db3.getSQL_CREATE_AUTHORIZED());
        db2.execSQL(db3.getSQL_CREATE_EXCHANGE());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(@Nullable SQLiteDatabase db2, int oldVersion, int newVersion) {
    }
}
