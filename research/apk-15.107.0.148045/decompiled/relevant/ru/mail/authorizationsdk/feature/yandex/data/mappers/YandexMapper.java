package ru.mail.authorizationsdk.feature.yandex.data.mappers;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.vk.push.authsdk.data.source.SecretsDataSource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.data.externalaccount.BaseOauthParams;
import ru.mail.authorizationsdk.domain.model.Result;
import ru.mail.authorizationsdk.external.secret.Secrets;
import ru.mail.authorizationsdk.feature.core.data.client.calladapter.model.ExternalServiceNetResponse;
import ru.mail.authorizationsdk.feature.core.data.model.AuthUrlResult;
import ru.mail.authorizationsdk.feature.core.data.model.RequestParams;
import ru.mail.authorizationsdk.feature.yandex.data.yandexapi.YandexApiResponse;
import ru.mail.authorizationsdk.feature.yandex.domain.model.YandexCaseResult;
import ru.mail.network.utils.client.platform.NecessaryParamsProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u000e\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/yandex/data/mappers/YandexMapper;", "", SecretsDataSource.SECRETS_LIBRARY_NAME, "Lru/mail/authorizationsdk/external/secret/Secrets;", "<init>", "(Lru/mail/authorizationsdk/external/secret/Secrets;)V", "mapToYandexRequestParams", "Lru/mail/authorizationsdk/feature/core/data/model/RequestParams;", "tokenResponse", "Lcom/google/api/client/auth/oauth2/TokenResponse;", "mapAuthUrlEmailToFailureResult", "Lru/mail/authorizationsdk/domain/model/Result;", "Lru/mail/authorizationsdk/feature/core/data/model/AuthUrlResult;", "Lru/mail/authorizationsdk/feature/yandex/domain/model/YandexCaseResult$Error;", "error", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse$Error;", "mapEmailRequestToFailureResult", "Lru/mail/authorizationsdk/feature/yandex/data/yandexapi/YandexApiResponse;", "Lru/mail/authorizationsdk/feature/core/data/client/calladapter/model/ExternalServiceNetResponse$Error;", "calculateExpirationTime", "", "expiresIn", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class YandexMapper {
    public static final int $stable = 0;
    public static final int NO_SWA_STATUS = 0;
    public static final int YANDEX_API_ERROR = 1000;

    @NotNull
    private final Secrets secrets;

    public YandexMapper(@NotNull Secrets secrets) {
        Intrinsics.checkNotNullParameter(secrets, "secrets");
        this.secrets = secrets;
    }

    private final long calculateExpirationTime(long expiresIn) {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + expiresIn;
    }

    @NotNull
    public final Result<AuthUrlResult, YandexCaseResult.Error> mapAuthUrlEmailToFailureResult(@NotNull NetResponse.Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        if (!(error instanceof NetResponse.Error.ApiError)) {
            if (error instanceof NetResponse.Error.NetworkError) {
                return new Result.Failure(YandexCaseResult.Error.NetworkException.INSTANCE);
            }
            if (error instanceof NetResponse.Error.UnknownError) {
                return new Result.Failure(YandexCaseResult.Error.UnknownError.INSTANCE);
            }
            throw new NoWhenBranchMatchedException();
        }
        NetResponse.Error.ApiError apiError = (NetResponse.Error.ApiError) error;
        Integer swaStatus = apiError.getSwaStatus();
        if (swaStatus != null && swaStatus.intValue() == 812) {
            return new Result.Failure(YandexCaseResult.Error.ImapFailed.INSTANCE);
        }
        if (swaStatus != null && swaStatus.intValue() == 806) {
            return new Result.Failure(YandexCaseResult.Error.UserBlockedError.INSTANCE);
        }
        Integer swaStatus2 = apiError.getSwaStatus();
        return new Result.Failure(new YandexCaseResult.Error.ApiError(swaStatus2 != null ? swaStatus2.intValue() : 0));
    }

    @NotNull
    public final Result<YandexApiResponse, YandexCaseResult.Error> mapEmailRequestToFailureResult(@NotNull ExternalServiceNetResponse.Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        if (error instanceof ExternalServiceNetResponse.Error.ApiError) {
            return new Result.Failure(new YandexCaseResult.Error.ApiError(1000));
        }
        if (error instanceof ExternalServiceNetResponse.Error.NetworkError) {
            return new Result.Failure(YandexCaseResult.Error.NetworkException.INSTANCE);
        }
        if (error instanceof ExternalServiceNetResponse.Error.UnknownError) {
            return new Result.Failure(YandexCaseResult.Error.UnknownError.INSTANCE);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final RequestParams mapToYandexRequestParams(@NotNull TokenResponse tokenResponse) {
        Intrinsics.checkNotNullParameter(tokenResponse, "tokenResponse");
        BaseOauthParams baseOauthParams = new BaseOauthParams(this.secrets.getClientId(), this.secrets.getSecretId(), "", null, false, false, null, false, 248, null);
        Pair pair = TuplesKt.to("access_token", tokenResponse.getAccessToken());
        Long expiresInSeconds = tokenResponse.getExpiresInSeconds();
        Intrinsics.checkNotNullExpressionValue(expiresInSeconds, "getExpiresInSeconds(...)");
        Collection collection = null;
        NecessaryParamsProvider necessaryParamsProvider = new NecessaryParamsProvider(CollectionsKt.plus((Collection) baseOauthParams.getParamsForRequest(), (Iterable) CollectionsKt.listOf((Object[]) new Pair[]{pair, TuplesKt.to("expires", String.valueOf(calculateExpirationTime(expiresInSeconds.longValue())))})), collection, 2, 0 == true ? 1 : 0);
        return new RequestParams(necessaryParamsProvider.provideGetParams(null), necessaryParamsProvider.providePostParams());
    }
}
