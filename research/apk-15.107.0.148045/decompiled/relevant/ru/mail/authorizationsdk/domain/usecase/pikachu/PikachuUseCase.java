package ru.mail.authorizationsdk.domain.usecase.pikachu;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaRepository;
import ru.mail.authorizationsdk.domain.usecase.pikachu.model.PikachuResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u0007H\u0086B¢\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/authorizationsdk/domain/usecase/pikachu/PikachuUseCase;", "", "pikachuCaptchaRepository", "Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaRepository;", "<init>", "(Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaRepository;)V", "invoke", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PikachuUseCase {
    public static final int $stable = 8;

    @NotNull
    private final PikachuCaptchaRepository pikachuCaptchaRepository;

    public PikachuUseCase(@NotNull PikachuCaptchaRepository pikachuCaptchaRepository) {
        Intrinsics.checkNotNullParameter(pikachuCaptchaRepository, "pikachuCaptchaRepository");
        this.pikachuCaptchaRepository = pikachuCaptchaRepository;
    }

    @Nullable
    public final Object invoke(@NotNull Continuation<? super PikachuResult> continuation) {
        return this.pikachuCaptchaRepository.getPikachuCaptcha(continuation);
    }
}
