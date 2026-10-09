package com.vk.pushme.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.vk.pushme.database.entity.Push;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Dao
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\u000b¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/vk/pushme/database/dao/PushDao;", "", "insert", "", OnlineResultEntity.FIELD_ENTITY, "Lcom/vk/pushme/database/entity/Push;", "(Lcom/vk/pushme/database/entity/Push;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "countAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllReceivedBefore", "", "timestamp", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushDao {
    @Query("SELECT COUNT(*) FROM push")
    @Nullable
    Object countAll(@NotNull Continuation<? super Long> continuation);

    @Delete
    @Nullable
    Object delete(@NotNull Push push, @NotNull Continuation<? super Unit> continuation);

    @Query("DELETE FROM push WHERE receive_time < :timestamp")
    @Nullable
    Object deleteAllReceivedBefore(long j10, @NotNull Continuation<? super Integer> continuation);

    @Query("SELECT * FROM push WHERE id = :id")
    @Nullable
    Object getById(long j10, @NotNull Continuation<? super Push> continuation);

    @Insert
    @Nullable
    Object insert(@NotNull Push push, @NotNull Continuation<? super Long> continuation);
}
