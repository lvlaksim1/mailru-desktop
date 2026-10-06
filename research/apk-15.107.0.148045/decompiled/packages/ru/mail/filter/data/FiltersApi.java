package ru.mail.filter.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.network.retrofit.ApiResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000 \f2\u00020\u0001:\u0001\fJ8\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\nH§@¢\u0006\u0002\u0010\u000b¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lru/mail/filter/data/FiltersApi;", "", "getFilters", "Lru/mail/network/retrofit/ApiResult;", "", "Lru/mail/filter/data/FilterDto;", "email", "", "activeMode", "htmlEncoded", "", "(Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FiltersApi {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lru/mail/filter/data/FiltersApi$Companion;", "", "<init>", "()V", "EMAIL", "", "ACTIVE_MODE", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final String ACTIVE_MODE = "act_mode";

        @NotNull
        private static final String EMAIL = "email";

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object getFilters$default(FiltersApi filtersApi, String str, String str2, boolean z10, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFilters");
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        return filtersApi.getFilters(str, str2, z10, continuation);
    }

    @FormUrlEncoded
    @POST("api/v1/filters")
    @Nullable
    Object getFilters(@Field("email") @NotNull String str, @NotNull @Query(AccountInfoUtilsKt.PARAM_KEY_ACT_MODE) String str2, @Field("htmlencoded") boolean z10, @NotNull Continuation<? super ApiResult<List<FilterDto>>> continuation);
}
