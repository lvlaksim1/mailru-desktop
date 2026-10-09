package com.vk.pushme.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.vk.pushme.database.entity.PushToken;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.offlinecache.OnlineResultEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Dao
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H¥@¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\bJ\u0018\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fH§@¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0010H§@¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u0010H§@¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015H§@¢\u0006\u0002\u0010\u0016J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0097@¢\u0006\u0002\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/vk/pushme/database/dao/PushTokenDao;", "", "<init>", "()V", "insert", "", OnlineResultEntity.FIELD_ENTITY, "Lcom/vk/pushme/database/entity/PushToken;", "(Lcom/vk/pushme/database/entity/PushToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByToken", "token", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForTransport", "transport", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "replaceTokenForGivenTransport", "newToken", "push-me-database_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushTokenDao.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushTokenDao.kt\ncom/vk/pushme/database/dao/PushTokenDao\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,40:1\n1#2:41\n*E\n"})
public abstract class PushTokenDao {

    /* JADX INFO: renamed from: com.vk.pushme.database.dao.PushTokenDao$replaceTokenForGivenTransport$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.database.dao.PushTokenDao", f = "PushTokenDao.kt", i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2}, l = {34, 35, 36}, m = "replaceTokenForGivenTransport$suspendImpl", n = {"$this", "newToken", "$this", "newToken", "existing", "it", "$i$a$-let-PushTokenDao$replaceTokenForGivenTransport$2", "$this", "newToken", "existing"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushTokenDao.replaceTokenForGivenTransport$suspendImpl(PushTokenDao.this, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00b2 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Transaction
    static /* synthetic */ Object replaceTokenForGivenTransport$suspendImpl(PushTokenDao pushTokenDao, PushToken pushToken, Continuation<? super PushToken> continuation) {
        AnonymousClass1 anonymousClass1;
        PushToken pushToken2;
        PushTokenDao pushTokenDao2;
        PushToken pushToken3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = pushTokenDao.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = pushTokenDao.new AnonymousClass1(continuation);
        }
        Object forTransport = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(forTransport);
            String transport = pushToken.getTransport();
            anonymousClass1.L$0 = pushTokenDao;
            anonymousClass1.L$1 = pushToken;
            anonymousClass1.label = 1;
            forTransport = pushTokenDao.getForTransport(transport, anonymousClass1);
            if (forTransport != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i11 == 1) {
            pushToken = (PushToken) anonymousClass1.L$1;
            pushTokenDao = (PushTokenDao) anonymousClass1.L$0;
            ResultKt.throwOnFailure(forTransport);
        } else {
            if (i11 != 2) {
                if (i11 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                PushToken pushToken4 = (PushToken) anonymousClass1.L$2;
                ResultKt.throwOnFailure(forTransport);
                return pushToken4;
            }
            pushToken3 = (PushToken) anonymousClass1.L$2;
            pushToken = (PushToken) anonymousClass1.L$1;
            pushTokenDao2 = (PushTokenDao) anonymousClass1.L$0;
            ResultKt.throwOnFailure(forTransport);
        }
        pushToken2 = pushToken3;
        pushTokenDao = pushTokenDao2;
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pushTokenDao);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(pushToken);
        anonymousClass1.L$2 = pushToken2;
        anonymousClass1.L$3 = null;
        anonymousClass1.label = 3;
        if (pushTokenDao.insert(pushToken, anonymousClass1) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return pushToken2;
        pushToken2 = (PushToken) forTransport;
        if (pushToken2 != null) {
            anonymousClass1.L$0 = pushTokenDao;
            anonymousClass1.L$1 = pushToken;
            anonymousClass1.L$2 = pushToken2;
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(pushToken2);
            anonymousClass1.I$0 = 0;
            anonymousClass1.label = 2;
            if (pushTokenDao.delete(pushToken2, anonymousClass1) != coroutine_suspended) {
                pushTokenDao2 = pushTokenDao;
                pushToken3 = pushToken2;
                pushToken2 = pushToken3;
                pushTokenDao = pushTokenDao2;
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pushTokenDao);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(pushToken);
                anonymousClass1.L$2 = pushToken2;
                anonymousClass1.L$3 = null;
                anonymousClass1.label = 3;
                if (pushTokenDao.insert(pushToken, anonymousClass1) == coroutine_suspended) {
                    return pushToken2;
                }
            }
        } else {
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(pushTokenDao);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(pushToken);
            anonymousClass1.L$2 = pushToken2;
            anonymousClass1.L$3 = null;
            anonymousClass1.label = 3;
            if (pushTokenDao.insert(pushToken, anonymousClass1) == coroutine_suspended) {
                return pushToken2;
            }
        }
        return coroutine_suspended;
    }

    @Delete
    @Nullable
    public abstract Object delete(@NotNull PushToken pushToken, @NotNull Continuation<? super Unit> continuation);

    @Query("SELECT * FROM push_token")
    @Nullable
    public abstract Object getAll(@NotNull Continuation<? super List<PushToken>> continuation);

    @Query("SELECT * FROM push_token WHERE id = :id")
    @Nullable
    public abstract Object getById(long j10, @NotNull Continuation<? super PushToken> continuation);

    @Query("SELECT * FROM push_token WHERE token = :token")
    @Nullable
    public abstract Object getByToken(@NotNull String str, @NotNull Continuation<? super PushToken> continuation);

    @Query("SELECT * FROM push_token WHERE transport = :transport")
    @Nullable
    public abstract Object getForTransport(@NotNull String str, @NotNull Continuation<? super PushToken> continuation);

    @Insert(onConflict = 3)
    @Nullable
    protected abstract Object insert(@NotNull PushToken pushToken, @NotNull Continuation<? super Unit> continuation);

    @Transaction
    @Nullable
    public Object replaceTokenForGivenTransport(@NotNull PushToken pushToken, @NotNull Continuation<? super PushToken> continuation) {
        return replaceTokenForGivenTransport$suspendImpl(this, pushToken, continuation);
    }
}
