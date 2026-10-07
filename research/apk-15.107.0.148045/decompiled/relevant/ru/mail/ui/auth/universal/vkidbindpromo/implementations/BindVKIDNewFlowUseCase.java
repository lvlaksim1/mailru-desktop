package ru.mail.ui.auth.universal.vkidbindpromo.implementations;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.config.Configuration;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase;", "", "config", "Lru/mail/config/Configuration;", "getLudwigTokenUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase;", "verifyPasswordCheckUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/VerifyPasswordCheckUseCase;", "getVKPreflightUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKPreflightUseCase;", "addSocialBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/AddSocialBindUseCase;", "clientId", "", "<init>", "(Lru/mail/config/Configuration;Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase;Lru/mail/ui/auth/universal/vkidbindpromo/implementations/VerifyPasswordCheckUseCase;Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKPreflightUseCase;Lru/mail/ui/auth/universal/vkidbindpromo/implementations/AddSocialBindUseCase;Ljava/lang/String;)V", "execute", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result;", "", "localDataForVKIDBind", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLocalDataForVKIDBindUseCase$LocalDataForVKIDBind;", "Result", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBindVKIDNewFlowUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BindVKIDNewFlowUseCase.kt\nru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,83:1\n434#2:84\n507#2,5:85\n*S KotlinDebug\n*F\n+ 1 BindVKIDNewFlowUseCase.kt\nru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase\n*L\n44#1:84\n44#1:85,5\n*E\n"})
public final class BindVKIDNewFlowUseCase {

    @NotNull
    private static final String SOCIAL_BIND_ADD_TARGET = "user/social/bind/add";

    @NotNull
    private final AddSocialBindUseCase addSocialBindUseCase;

    @NotNull
    private final String clientId;

    @NotNull
    private final Configuration config;

    @NotNull
    private final GetLudwigTokenUseCase getLudwigTokenUseCase;

    @NotNull
    private final GetVKPreflightUseCase getVKPreflightUseCase;

    @NotNull
    private final VerifyPasswordCheckUseCase verifyPasswordCheckUseCase;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 2)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "<init>", "()V", "Success", "Error", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result$Error;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result$Success;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result<T> {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007HÆ\u0003J%\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result$Error;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result;", "", "errorCode", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "(Ljava/lang/String;Ljava/lang/Exception;)V", "getErrorCode", "()Ljava/lang/String;", "getE", "()Ljava/lang/Exception;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error extends Result {
            public static final int $stable = 8;

            @Nullable
            private final Exception e;

            @NotNull
            private final String errorCode;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Error(@NotNull String errorCode, @Nullable Exception exc) {
                super(null);
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                this.errorCode = errorCode;
                this.e = exc;
            }

            public static /* synthetic */ Error copy$default(Error error, String str, Exception exc, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = error.errorCode;
                }
                if ((i10 & 2) != 0) {
                    exc = error.e;
                }
                return error.copy(str, exc);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getErrorCode() {
                return this.errorCode;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Exception getE() {
                return this.e;
            }

            @NotNull
            public final Error copy(@NotNull String errorCode, @Nullable Exception e10) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
                return new Error(errorCode, e10);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return Intrinsics.areEqual(this.errorCode, error.errorCode) && Intrinsics.areEqual(this.e, error.e);
            }

            @Nullable
            public final Exception getE() {
                return this.e;
            }

            @NotNull
            public final String getErrorCode() {
                return this.errorCode;
            }

            public int hashCode() {
                int iHashCode = this.errorCode.hashCode() * 31;
                Exception exc = this.e;
                return iHashCode + (exc == null ? 0 : exc.hashCode());
            }

            @NotNull
            public String toString() {
                return "Error(errorCode=" + this.errorCode + ", e=" + this.e + ")";
            }

            public /* synthetic */ Error(String str, Exception exc, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i10 & 2) != 0 ? null : exc);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result$Success;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase$Result;", "", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success extends Result {
            public static final int $stable = 0;

            @NotNull
            public static final Success INSTANCE = new Success();

            private Success() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Success);
            }

            public int hashCode() {
                return 991280982;
            }

            @NotNull
            public String toString() {
                return "Success";
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    public BindVKIDNewFlowUseCase(@NotNull Configuration config, @NotNull GetLudwigTokenUseCase getLudwigTokenUseCase, @NotNull VerifyPasswordCheckUseCase verifyPasswordCheckUseCase, @NotNull GetVKPreflightUseCase getVKPreflightUseCase, @NotNull AddSocialBindUseCase addSocialBindUseCase, @NotNull String clientId) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(getLudwigTokenUseCase, "getLudwigTokenUseCase");
        Intrinsics.checkNotNullParameter(verifyPasswordCheckUseCase, "verifyPasswordCheckUseCase");
        Intrinsics.checkNotNullParameter(getVKPreflightUseCase, "getVKPreflightUseCase");
        Intrinsics.checkNotNullParameter(addSocialBindUseCase, "addSocialBindUseCase");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        this.config = config;
        this.getLudwigTokenUseCase = getLudwigTokenUseCase;
        this.verifyPasswordCheckUseCase = verifyPasswordCheckUseCase;
        this.getVKPreflightUseCase = getVKPreflightUseCase;
        this.addSocialBindUseCase = addSocialBindUseCase;
        this.clientId = clientId;
    }

    @NotNull
    public final Result execute(@NotNull GetLocalDataForVKIDBindUseCase.LocalDataForVKIDBind localDataForVKIDBind) throws IOException {
        Intrinsics.checkNotNullParameter(localDataForVKIDBind, "localDataForVKIDBind");
        GetLudwigTokenUseCase.Result<GetLudwigTokenUseCase.LudwigData> resultExecute = this.getLudwigTokenUseCase.execute(localDataForVKIDBind.getMailData().getEmail(), SOCIAL_BIND_ADD_TARGET);
        if (!(resultExecute instanceof GetLudwigTokenUseCase.Result.Success)) {
            if (!(resultExecute instanceof GetLudwigTokenUseCase.Result.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            GetLudwigTokenUseCase.Result.Error error = (GetLudwigTokenUseCase.Result.Error) resultExecute;
            return new Result.Error(error.getErrorCode(), error.getE());
        }
        GetLudwigTokenUseCase.Result.Success success = (GetLudwigTokenUseCase.Result.Success) resultExecute;
        VerifyPasswordCheckUseCase.Result<VerifyPasswordCheckUseCase.VerifyPasswordData> resultExecute2 = this.verifyPasswordCheckUseCase.execute(success.getData().getLudwigToken(), localDataForVKIDBind.getMailData().getPassword());
        if (!(resultExecute2 instanceof VerifyPasswordCheckUseCase.Result.Success)) {
            if (!(resultExecute2 instanceof VerifyPasswordCheckUseCase.Result.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            VerifyPasswordCheckUseCase.Result.Error error2 = (VerifyPasswordCheckUseCase.Result.Error) resultExecute2;
            return new Result.Error(error2.getErrorCode(), error2.getE());
        }
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        StringBuilder sb2 = new StringBuilder();
        int length = string.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = string.charAt(i10);
            if (cCharAt != '-') {
                sb2.append(cCharAt);
            }
        }
        String string2 = sb2.toString();
        GetVKPreflightUseCase.Result<GetVKPreflightUseCase.PreflightData> resultExecute3 = this.getVKPreflightUseCase.execute(localDataForVKIDBind.getVkidData().getSilentToken(), localDataForVKIDBind.getVkidData().getUuid(), this.clientId, this.config.getSocialLoginConfig().getVkConnectScopes(), string2);
        if (!(resultExecute3 instanceof GetVKPreflightUseCase.Result.Success)) {
            if (!(resultExecute3 instanceof GetVKPreflightUseCase.Result.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            GetVKPreflightUseCase.Result.Error error3 = (GetVKPreflightUseCase.Result.Error) resultExecute3;
            return new Result.Error(error3.getErrorCode(), error3.getE());
        }
        AddSocialBindUseCase.Result resultExecute4 = this.addSocialBindUseCase.execute(localDataForVKIDBind.getMailData().getAccessToken(), success.getData().getLudwigToken(), ((GetVKPreflightUseCase.Result.Success) resultExecute3).getData().getPreflightToken(), this.config.getVkIdBindEmailPromoConfig().getRatPromo().isResetPassEnabled(), string2);
        if (resultExecute4 instanceof AddSocialBindUseCase.Result.Success) {
            return Result.Success.INSTANCE;
        }
        if (!(resultExecute4 instanceof AddSocialBindUseCase.Result.Error)) {
            throw new NoWhenBranchMatchedException();
        }
        AddSocialBindUseCase.Result.Error error4 = (AddSocialBindUseCase.Result.Error) resultExecute4;
        return new Result.Error(error4.getErrorCode(), error4.getE());
    }
}
