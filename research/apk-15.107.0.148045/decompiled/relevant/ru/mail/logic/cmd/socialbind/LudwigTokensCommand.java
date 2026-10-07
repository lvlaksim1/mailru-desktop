package ru.mail.logic.cmd.socialbind;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;
import ru.mail.serverapi.retrofit.MailApiCommand;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0096@¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/logic/cmd/socialbind/LudwigTokensCommand;", "Lru/mail/serverapi/retrofit/MailApiCommand;", "", "Lru/mail/logic/cmd/socialbind/LudwigTokenDTO;", "", ApiUris.AUTHORITY_API, "Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "email", ToastDialogDto.KEY_TARGET, "<init>", "(Lru/mail/logic/cmd/socialbind/LudwigTokensApi;Ljava/lang/String;Ljava/lang/String;)V", "executeRequest", "Lru/mail/network/retrofit/ApiResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformDataToDomainModel", "result", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudwigTokensCommand extends MailApiCommand<Object, LudwigTokenDTO, String> {
    public static final int $stable = 8;

    @NotNull
    private final LudwigTokensApi api;

    @NotNull
    private final String email;

    @NotNull
    private final String target;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LudwigTokensCommand(@NotNull LudwigTokensApi api, @NotNull String email, @NotNull String target) {
        super(new Object());
        Intrinsics.checkNotNullParameter(api, "api");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(target, "target");
        this.api = api;
        this.email = email;
        this.target = target;
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @Nullable
    public Object executeRequest(@NotNull Continuation<? super ApiResult<LudwigTokenDTO>> continuation) {
        return this.api.getLudwigToken(this.email, this.target, continuation);
    }

    @Override // ru.mail.serverapi.retrofit.MailApiCommand
    @NotNull
    public String transformDataToDomainModel(@NotNull LudwigTokenDTO result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return result.getToken();
    }
}
