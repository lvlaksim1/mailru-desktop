package ru.mail.filter.data;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cache.FiltersCache;
import ru.mail.data.entities.Filter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/filter/data/FiltersMemoryCacheRepositoryImpl;", "Lru/mail/filter/data/FiltersMemoryCacheRepository;", "cache", "Lru/mail/data/cache/FiltersCache;", "<init>", "(Lru/mail/data/cache/FiltersCache;)V", "getFilters", "", "Lru/mail/data/entities/Filter;", "account", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FiltersMemoryCacheRepositoryImpl implements FiltersMemoryCacheRepository {
    public static final int $stable = 8;

    @NotNull
    private final FiltersCache cache;

    @Inject
    public FiltersMemoryCacheRepositoryImpl(@NotNull FiltersCache cache) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.cache = cache;
    }

    @Override // ru.mail.filter.data.FiltersMemoryCacheRepository
    @NotNull
    public List<Filter> getFilters(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        this.cache.setAccountName(account);
        List<Filter> all = this.cache.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        return all;
    }
}
