package ru.mail.filter.data;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.UserFiltersStorage;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.retrofit.MailApiCommand;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00050\u0001B\u001f\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u000eH\u0014J\u001b\u0010\u0013\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/filter/data/RequestHasFiltersRetrofitCommand;", "Lru/mail/serverapi/retrofit/MailApiCommand;", "Lru/mail/serverapi/ServerCommandEmailParams;", "", "Lru/mail/filter/data/FilterDto;", "", ApiUris.AUTHORITY_API, "Lru/mail/filter/data/FiltersApi;", "params", "userFiltersStorage", "Lru/mail/data/cmd/server/UserFiltersStorage;", "<init>", "(Lru/mail/filter/data/FiltersApi;Lru/mail/serverapi/ServerCommandEmailParams;Lru/mail/data/cmd/server/UserFiltersStorage;)V", "executeRequest", "Lru/mail/network/retrofit/ApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processResponse", "Lru/mail/mailbox/cmd/CommandStatus;", "result", "transformDataToDomainModel", "(Ljava/util/List;)Ljava/lang/Boolean;", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRequestHasFiltersRetrofitCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestHasFiltersRetrofitCommand.kt\nru/mail/filter/data/RequestHasFiltersRetrofitCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,43:1\n1869#2,2:44\n*S KotlinDebug\n*F\n+ 1 RequestHasFiltersRetrofitCommand.kt\nru/mail/filter/data/RequestHasFiltersRetrofitCommand\n*L\n36#1:44,2\n*E\n"})
public final class RequestHasFiltersRetrofitCommand extends MailApiCommand<ServerCommandEmailParams, List<? extends FilterDto>, Boolean> {

    @NotNull
    private final FiltersApi api;

    @NotNull
    private final UserFiltersStorage userFiltersStorage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestHasFiltersRetrofitCommand(@NotNull FiltersApi api, @NotNull ServerCommandEmailParams params, @NotNull UserFiltersStorage userFiltersStorage) {
        super(params);
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(userFiltersStorage, "userFiltersStorage");
        this.api = api;
        this.userFiltersStorage = userFiltersStorage;
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @Nullable
    public Object executeRequest(@NotNull Continuation<? super ApiResult<List<? extends FilterDto>>> continuation) {
        FiltersApi filtersApi = this.api;
        String login = getParams().getLogin();
        if (login == null) {
            login = "";
        }
        String activeMode = getParams().getActiveMode();
        Intrinsics.checkNotNullExpressionValue(activeMode, "getActiveMode(...)");
        return FiltersApi.getFilters$default(filtersApi, login, activeMode, false, continuation, 4, null);
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @NotNull
    protected CommandStatus<?> processResponse(@NotNull ApiResult<List<? extends FilterDto>> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof ApiResult.Error) {
            ApiResult.Error error = (ApiResult.Error) result;
            if (error.getCode() == 403 && Intrinsics.areEqual(error.getBody(), "folder")) {
                FolderState folderState = getParams().getFolderState();
                folderState.clearFolderLogin(folderState.getFolderId());
                return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(folderState.getFolderId()));
            }
        }
        return super.processResponse(result);
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    public /* bridge */ /* synthetic */ Boolean transformDataToDomainModel(List<? extends FilterDto> list) {
        return transformDataToDomainModel2((List<FilterDto>) list);
    }

    @NotNull
    /* JADX INFO: renamed from: transformDataToDomainModel, reason: avoid collision after fix types in other method */
    public Boolean transformDataToDomainModel2(@NotNull List<FilterDto> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        Iterator<T> it = result.iterator();
        boolean enabled = false;
        while (it.hasNext()) {
            enabled |= ((FilterDto) it.next()).getEnabled();
        }
        this.userFiltersStorage.saveHasFilters(getParams().getLogin(), enabled);
        return Boolean.valueOf(enabled);
    }
}
