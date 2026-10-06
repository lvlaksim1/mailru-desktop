package ru.mail.filter.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Filter;
import ru.mail.kit.result.tools.Result;
import ru.mail.logic.content.CommonError;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\fJ6\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u000f\"\u00020\u0007H¦@¢\u0006\u0002\u0010\u0010¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lru/mail/filter/data/FiltersRepository;", "", "getFilters", "Lkotlinx/coroutines/flow/Flow;", "", "Lru/mail/data/entities/Filter;", "account", "", "requestFilters", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/logic/content/CommonError;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFilter", "filters", "", "(Ljava/lang/String;[Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FiltersRepository {
    @Nullable
    Object deleteFilter(@NotNull String str, @NotNull String[] strArr, @NotNull Continuation<? super Result<Unit, CommonError>> continuation);

    @NotNull
    Flow<List<Filter>> getFilters(@NotNull String account);

    @Nullable
    Object requestFilters(@NotNull String str, @NotNull Continuation<? super Result<Unit, CommonError>> continuation);
}
