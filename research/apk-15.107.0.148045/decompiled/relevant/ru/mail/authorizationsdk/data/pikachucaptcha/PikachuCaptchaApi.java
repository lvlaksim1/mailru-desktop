package ru.mail.authorizationsdk.data.pikachucaptcha;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.mail.ads.core.api.ui.disclaimer.CategoryDisclaimerDelegateKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaApi;", "", "getPikachuCaptcha", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "size", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PikachuCaptchaApi {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaApi$Companion;", "", "<init>", "()V", "DEFAULT_CAPTCHA_SIZE", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        private static final String DEFAULT_CAPTCHA_SIZE = "6";

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object getPikachuCaptcha$default(PikachuCaptchaApi pikachuCaptchaApi, String str, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPikachuCaptcha");
        }
        if ((i10 & 1) != 0) {
            str = CategoryDisclaimerDelegateKt.CATEGORY_DISCLAIMER_MEDICAL;
        }
        return pikachuCaptchaApi.getPikachuCaptcha(str, continuation);
    }

    @POST("c/{size}")
    @Nullable
    Object getPikachuCaptcha(@Path("size") @NotNull String str, @NotNull Continuation<? super Response<ResponseBody>> continuation);
}
