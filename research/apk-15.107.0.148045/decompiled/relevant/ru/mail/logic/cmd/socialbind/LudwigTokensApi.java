package ru.mail.logic.cmd.socialbind;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\b¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "", "getLudwigToken", "Lru/mail/network/retrofit/ApiResult;", "Lru/mail/logic/cmd/socialbind/LudwigTokenDTO;", "email", "", ToastDialogDto.KEY_TARGET, "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LudwigTokensApi {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lru/mail/logic/cmd/socialbind/LudwigTokensApi$Companion;", "", "<init>", "()V", "EMAIL", "", "TARGET", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final String EMAIL = "email";

        @NotNull
        private static final String TARGET = "target";

        private Companion() {
        }
    }

    @FormUrlEncoded
    @POST("api/v1/tokens/ludwig")
    @Nullable
    Object getLudwigToken(@Field("email") @NotNull String str, @Field(ToastDialogDto.KEY_TARGET) @NotNull String str2, @NotNull Continuation<? super ApiResult<LudwigTokenDTO>> continuation);
}
