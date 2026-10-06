package ru.mail.filter.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Filter;
import ru.mail.kit.result.tools.Result;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\bH¦@¢\u0006\u0002\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/filter/data/FiltersDatabaseRepository;", "", "getFilters", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/data/entities/Filter;", "", "account", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FiltersDatabaseRepository {
    @Nullable
    Object getFilters(@NotNull String str, @NotNull Continuation<? super Result<List<Filter>, Throwable>> continuation);
}
