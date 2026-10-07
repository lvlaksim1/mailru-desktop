package ru.mail.ui.auth.universal.vkidbindpromo.implementations;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;
import ru.mail.logic.cmd.socialbind.LudwigTokensCommand;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0003\u000e\u000f\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase;", "", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "ludwigTokensApi", "Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "<init>", "(Lru/mail/mailbox/cmd/ExecutorSelector;Lru/mail/logic/cmd/socialbind/LudwigTokensApi;)V", "execute", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$LudwigData;", "email", "", ToastDialogDto.KEY_TARGET, "Result", "LudwigData", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetLudwigTokenUseCase {

    @NotNull
    private static final String ERROR_LUDWIG_TOKEN_CODE = "ERROR_LUDWIG_TOKEN_CODE";

    @NotNull
    private static final String ERROR_LUDWIG_TOKEN_EXCEPTION = "ERROR_LUDWIG_TOKEN_EXCEPTION";

    @NotNull
    private static final String ERROR_LUDWIG_TOKEN_UNKNOWN = "ERROR_LUDWIG_TOKEN_UNKNOWN";

    @NotNull
    private final LudwigTokensApi ludwigTokensApi;

    @NotNull
    private final ExecutorSelector selector;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$LudwigData;", "", "ludwigToken", "", "<init>", "(Ljava/lang/String;)V", "getLudwigToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LudwigData {
        public static final int $stable = 0;

        @NotNull
        private final String ludwigToken;

        public LudwigData(@NotNull String ludwigToken) {
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            this.ludwigToken = ludwigToken;
        }

        public static /* synthetic */ LudwigData copy$default(LudwigData ludwigData, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = ludwigData.ludwigToken;
            }
            return ludwigData.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        @NotNull
        public final LudwigData copy(@NotNull String ludwigToken) {
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            return new LudwigData(ludwigToken);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LudwigData) && Intrinsics.areEqual(this.ludwigToken, ((LudwigData) other).ludwigToken);
        }

        @NotNull
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        public int hashCode() {
            return this.ludwigToken.hashCode();
        }

        @NotNull
        public String toString() {
            return "LudwigData(ludwigToken=" + this.ludwigToken + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 2)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "<init>", "()V", "Success", "Error", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result$Error;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result$Success;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result<T> {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007HÆ\u0003J%\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result$Error;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result;", "", "errorCode", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "<init>", "(Ljava/lang/String;Ljava/lang/Exception;)V", "getErrorCode", "()Ljava/lang/String;", "getE", "()Ljava/lang/Exception;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0002HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result$Success;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$Result;", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$LudwigData;", "data", "<init>", "(Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$LudwigData;)V", "getData", "()Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase$LudwigData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success extends Result<LudwigData> {
            public static final int $stable = 0;

            @NotNull
            private final LudwigData data;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(@NotNull LudwigData data) {
                super(null);
                Intrinsics.checkNotNullParameter(data, "data");
                this.data = data;
            }

            public static /* synthetic */ Success copy$default(Success success, LudwigData ludwigData, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    ludwigData = success.data;
                }
                return success.copy(ludwigData);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final LudwigData getData() {
                return this.data;
            }

            @NotNull
            public final Success copy(@NotNull LudwigData data) {
                Intrinsics.checkNotNullParameter(data, "data");
                return new Success(data);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && Intrinsics.areEqual(this.data, ((Success) other).data);
            }

            @NotNull
            public final LudwigData getData() {
                return this.data;
            }

            public int hashCode() {
                return this.data.hashCode();
            }

            @NotNull
            public String toString() {
                return "Success(data=" + this.data + ")";
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    public GetLudwigTokenUseCase(@NotNull ExecutorSelector selector, @NotNull LudwigTokensApi ludwigTokensApi) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        Intrinsics.checkNotNullParameter(ludwigTokensApi, "ludwigTokensApi");
        this.selector = selector;
        this.ludwigTokensApi = ludwigTokensApi;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Result<LudwigData> execute(@NotNull String email, @NotNull String target) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(target, "target");
        try {
            CommandStatus<?> orThrow = new LudwigTokensCommand(this.ludwigTokensApi, email, target).execute(this.selector).getOrThrow();
            if (orThrow instanceof CommandStatus.OK) {
                return new Result.Success(new LudwigData(((CommandStatus.OK) orThrow).getData().toString()));
            }
            int i10 = 2;
            Exception exc = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            if (!(orThrow instanceof CommandStatus.ERROR)) {
                return new Result.Error(ERROR_LUDWIG_TOKEN_UNKNOWN, objArr2 == true ? 1 : 0, i10, objArr == true ? 1 : 0);
            }
            return new Result.Error("ERROR_LUDWIG_TOKEN_CODE: " + ((CommandStatus.ERROR) orThrow).getData().toString(), exc, i10, objArr3 == true ? 1 : 0);
        } catch (Exception e10) {
            return new Result.Error(ERROR_LUDWIG_TOKEN_EXCEPTION, e10);
        }
    }
}
