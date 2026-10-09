package team.vk.cloud.core.provider;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calendar.domain.entities.CongratulationContract;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lteam/vk/cloud/core/provider/AccessTokenUpdaterInterface;", "", "updateAccessToken", "", CongratulationContract.Param.USERNAME, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AccessTokenUpdaterInterface {
    @Nullable
    Object updateAccessToken(@NotNull String str, @NotNull Continuation<? super String> continuation);
}
