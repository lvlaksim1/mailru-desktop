package com.vk.pushme.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.vk.pushme.database.converter.Converters;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.database.dao.PushTokenDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.database.entity.PendingAction;
import com.vk.pushme.database.entity.Push;
import com.vk.pushme.database.entity.PushToken;
import com.vk.pushme.database.entity.Subscription;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.parser.BetaStateParser;
import ru.mail.database.DatabaseEntryPoint;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@TypeConverters({Converters.class})
@Database(entities = {Subscription.class, PendingAction.class, PushToken.class, Push.class}, version = 1)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&¨\u0006\r"}, d2 = {"Lcom/vk/pushme/database/PushMeSdkDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "pushTokenDao", "Lcom/vk/pushme/database/dao/PushTokenDao;", "pushDao", "Lcom/vk/pushme/database/dao/PushDao;", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "Companion", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PushMeSdkDatabase extends RoomDatabase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/database/PushMeSdkDatabase$Companion;", "", "<init>", "()V", BetaStateParser.BUILD_NUMBER, "Lcom/vk/pushme/database/PushMeSdkDatabase;", "context", "Landroid/content/Context;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PushMeSdkDatabase build(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (PushMeSdkDatabase) Room.databaseBuilder(context, PushMeSdkDatabase.class, "pushmesdk_db").openHelperFactory(DatabaseEntryPoint.INSTANCE.sqliteFactory(context).createOpenHelperFactory()).fallbackToDestructiveMigrationOnDowngrade(true).build();
        }

        private Companion() {
        }
    }

    @NotNull
    public abstract PendingActionDao pendingActionDao();

    @NotNull
    public abstract PushDao pushDao();

    @NotNull
    public abstract PushTokenDao pushTokenDao();

    @NotNull
    public abstract SubscriptionDao subscriptionDao();
}
