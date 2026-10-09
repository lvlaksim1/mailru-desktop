package com.vk.pushme.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.vk.pushme.database.entity.Subscription;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Dao
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH§@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H§@¢\u0006\u0002\u0010\u0012J \u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH§@¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0017H§@¢\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u000e\u001a\u00020\u000fH§@¢\u0006\u0002\u0010\u001a¨\u0006\u001bÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/database/dao/SubscriptionDao;", "", "insert", "", OnlineResultEntity.FIELD_ENTITY, "Lcom/vk/pushme/database/entity/Subscription;", "(Lcom/vk/pushme/database/entity/Subscription;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "entities", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "delete", "deleteForApplicationAndAccounts", "", "application", "", "accounts", "", "(Ljava/lang/String;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findSubscription", "account", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllForApplication", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SubscriptionDao {
    @Delete
    @Nullable
    Object delete(@NotNull Subscription subscription, @NotNull Continuation<? super Unit> continuation);

    @Query("DELETE FROM subscription WHERE application = :application AND account IN (:accounts)")
    @Nullable
    Object deleteForApplicationAndAccounts(@NotNull String str, @NotNull Set<String> set, @NotNull Continuation<? super Integer> continuation);

    @Query("SELECT * FROM subscription WHERE application = :application AND account = :account")
    @Nullable
    Object findSubscription(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Subscription> continuation);

    @Query("SELECT * FROM subscription ORDER BY id")
    @Nullable
    Object getAll(@NotNull Continuation<? super List<Subscription>> continuation);

    @Query("SELECT * FROM subscription WHERE application = :application ORDER BY id")
    @Nullable
    Object getAllForApplication(@NotNull String str, @NotNull Continuation<? super List<Subscription>> continuation);

    @Insert(onConflict = 1)
    @Nullable
    Object insert(@NotNull Subscription subscription, @NotNull Continuation<? super Unit> continuation);

    @Insert(onConflict = 1)
    @Nullable
    Object insert(@NotNull Iterable<Subscription> iterable, @NotNull Continuation<? super Unit> continuation);

    @Update
    @Nullable
    Object update(@NotNull Subscription subscription, @NotNull Continuation<? super Unit> continuation);
}
