package com.vk.pushme.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.vk.pushme.database.entity.PendingAction;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Dao
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH§@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH§@¢\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H§@¢\u0006\u0002\u0010\u0014¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/vk/pushme/database/dao/PendingActionDao;", "", "insert", "", OnlineResultEntity.FIELD_ENTITY, "Lcom/vk/pushme/database/entity/PendingAction;", "(Lcom/vk/pushme/database/entity/PendingAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "entities", "", "(Ljava/lang/Iterable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "getAll", "", "limit", "", "offset", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllOlderThan", "lastRecordId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PendingActionDao {
    @Delete
    @Nullable
    Object delete(@NotNull PendingAction pendingAction, @NotNull Continuation<? super Unit> continuation);

    @Query("DELETE FROM pending_action WHERE id <= :lastRecordId")
    @Nullable
    Object deleteAllOlderThan(long j10, @NotNull Continuation<? super Integer> continuation);

    @Query("SELECT * FROM pending_action ORDER BY id LIMIT :limit OFFSET :offset")
    @Nullable
    Object getAll(int i10, int i11, @NotNull Continuation<? super List<PendingAction>> continuation);

    @Insert
    @Nullable
    Object insert(@NotNull PendingAction pendingAction, @NotNull Continuation<? super Unit> continuation);

    @Insert
    @Nullable
    Object insert(@NotNull Iterable<PendingAction> iterable, @NotNull Continuation<? super Unit> continuation);
}
