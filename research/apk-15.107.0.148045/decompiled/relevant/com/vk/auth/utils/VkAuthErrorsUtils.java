package com.vk.auth.utils;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.auth.common.R;
import com.vk.auth.erochtuakvmocc;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/vk/auth/utils/VkAuthErrorsUtils;", "", "<init>", "()V", "", "error", "", "isIOError", "(Ljava/lang/Throwable;)Z", "Landroid/content/Context;", "context", "hideErrorCode", "Lcom/vk/auth/utils/VkAuthErrorsUtils$VkError;", "getDetailedError", "(Landroid/content/Context;Ljava/lang/Throwable;Z)Lcom/vk/auth/utils/VkAuthErrorsUtils$VkError;", "VkError", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VkAuthErrorsUtils {
    public static final int $stable = 0;

    @NotNull
    public static final VkAuthErrorsUtils INSTANCE = new VkAuthErrorsUtils();

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\rJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0005\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001d\u001a\u0004\b\u0006\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001d\u001a\u0004\b \u0010\r¨\u0006!"}, d2 = {"Lcom/vk/auth/utils/VkAuthErrorsUtils$VkError;", "", "", "text", "", "isToast", "isUnknown", "shouldSkip", "<init>", "(Ljava/lang/String;ZZZ)V", "component1", "()Ljava/lang/String;", "component2", "()Z", "component3", "component4", "copy", "(Ljava/lang/String;ZZZ)Lcom/vk/auth/utils/VkAuthErrorsUtils$VkError;", "toString", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "erochtuakvmoca", "Ljava/lang/String;", "getText", "erochtuakvmocb", "Z", "erochtuakvmocc", "erochtuakvmocd", "getShouldSkip", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class VkError {
        public static final int $stable = 0;

        /* JADX INFO: renamed from: erochtuakvmoca, reason: from kotlin metadata */
        @NotNull
        private final String text;

        /* JADX INFO: renamed from: erochtuakvmocb, reason: from kotlin metadata */
        private final boolean isToast;

        /* JADX INFO: renamed from: erochtuakvmocc, reason: from kotlin metadata */
        private final boolean isUnknown;

        /* JADX INFO: renamed from: erochtuakvmocd, reason: from kotlin metadata */
        private final boolean shouldSkip;

        public VkError(@NotNull String text, boolean z10, boolean z11, boolean z12) {
            Intrinsics.checkNotNullParameter(text, "text");
            this.text = text;
            this.isToast = z10;
            this.isUnknown = z11;
            this.shouldSkip = z12;
        }

        public static /* synthetic */ VkError copy$default(VkError vkError, String str, boolean z10, boolean z11, boolean z12, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = vkError.text;
            }
            if ((i10 & 2) != 0) {
                z10 = vkError.isToast;
            }
            if ((i10 & 4) != 0) {
                z11 = vkError.isUnknown;
            }
            if ((i10 & 8) != 0) {
                z12 = vkError.shouldSkip;
            }
            return vkError.copy(str, z10, z11, z12);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsToast() {
            return this.isToast;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsUnknown() {
            return this.isUnknown;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getShouldSkip() {
            return this.shouldSkip;
        }

        @NotNull
        public final VkError copy(@NotNull String text, boolean isToast, boolean isUnknown, boolean shouldSkip) {
            Intrinsics.checkNotNullParameter(text, "text");
            return new VkError(text, isToast, isUnknown, shouldSkip);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VkError)) {
                return false;
            }
            VkError vkError = (VkError) other;
            return Intrinsics.areEqual(this.text, vkError.text) && this.isToast == vkError.isToast && this.isUnknown == vkError.isUnknown && this.shouldSkip == vkError.shouldSkip;
        }

        public final boolean getShouldSkip() {
            return this.shouldSkip;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        public int hashCode() {
            return Boolean.hashCode(this.shouldSkip) + erochtuakvmocc.erochtuakvmoca(this.isUnknown, erochtuakvmocc.erochtuakvmoca(this.isToast, this.text.hashCode() * 31, 31), 31);
        }

        public final boolean isToast() {
            return this.isToast;
        }

        public final boolean isUnknown() {
            return this.isUnknown;
        }

        @NotNull
        public String toString() {
            return "VkError(text=" + this.text + ", isToast=" + this.isToast + ", isUnknown=" + this.isUnknown + ", shouldSkip=" + this.shouldSkip + ')';
        }
    }

    private VkAuthErrorsUtils() {
    }

    public static /* synthetic */ VkError getDetailedError$default(VkAuthErrorsUtils vkAuthErrorsUtils, Context context, Throwable th2, boolean z10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        return vkAuthErrorsUtils.getDetailedError(context, th2, z10);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0080  */
    /* JADX WARN: Code duplicated, block: B:24:0x0091  */
    /* JADX WARN: Instruction removed from duplicated block: B:22:0x0080, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:24:0x0091, please report this as an issue */
    @NotNull
    public final VkError getDetailedError(@NotNull Context context, @NotNull Throwable error, boolean hideErrorCode) {
        boolean z10;
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(error, "error");
        if (isIOError(error)) {
            String string2 = context.getString(R.string.vk_auth_load_network_error);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return new VkError(string2, false, false, false);
        }
        boolean z11 = true;
        if (!(error instanceof VKApiExecutionException)) {
            String string3 = context.getString(R.string.vk_auth_unknown_error);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
            return new VkError(string3, false, true, false);
        }
        VKApiExecutionException vKApiExecutionException = (VKApiExecutionException) error;
        String errorMsg = vKApiExecutionException.getErrorMsg();
        if (!vKApiExecutionException.getHasLocalizedMessage()) {
            if (vKApiExecutionException.getCode() == 14) {
                errorMsg = context.getString(R.string.vk_captcha_code);
                Intrinsics.checkNotNullExpressionValue(errorMsg, "getString(...)");
                z10 = true;
                z11 = false;
            } else if (errorMsg == null || StringsKt.isBlank(errorMsg)) {
                errorMsg = context.getString(R.string.vk_auth_unknown_api_error);
                Intrinsics.checkNotNullExpressionValue(errorMsg, "getString(...)");
                z10 = false;
            }
            string = context.getString(R.string.vk_auth_error_code_suffix, String.valueOf(vKApiExecutionException.getCode()));
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (!StringsKt.endsWith$default((CharSequence) errorMsg, '.', false, 2, (Object) null)) {
                errorMsg = errorMsg + '.';
            }
            if (!hideErrorCode) {
                errorMsg = errorMsg + TokenParser.SP + string;
            }
            return new VkError(errorMsg, false, z11, z10);
        }
        errorMsg = vKApiExecutionException.getDetailMessage();
        z11 = false;
        z10 = false;
        string = context.getString(R.string.vk_auth_error_code_suffix, String.valueOf(vKApiExecutionException.getCode()));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (!StringsKt.endsWith$default((CharSequence) errorMsg, '.', false, 2, (Object) null)) {
            errorMsg = errorMsg + '.';
        }
        if (!hideErrorCode) {
            errorMsg = errorMsg + TokenParser.SP + string;
        }
        return new VkError(errorMsg, false, z11, z10);
    }

    public final boolean isIOError(@Nullable Throwable error) {
        if (error instanceof IOException) {
            return true;
        }
        return (error instanceof VKApiExecutionException) && ((VKApiExecutionException) error).getCode() == -1;
    }
}
