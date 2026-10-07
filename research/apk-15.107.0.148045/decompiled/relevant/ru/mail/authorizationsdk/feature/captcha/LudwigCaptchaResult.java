package ru.mail.authorizationsdk.feature.captcha;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.os.BundleKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00072\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "Landroid/os/Parcelable;", "<init>", "()V", "BackClick", "CaptchaDone", "Error", "Companion", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$BackClick;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$CaptchaDone;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$Error;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class LudwigCaptchaResult implements Parcelable {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String RESULT_KEY = "CaptchaResult";

    @NotNull
    private static final String SERIALIZE_KEY = "CaptchaResult_SerializeKey";

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$BackClick;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BackClick extends LudwigCaptchaResult {

        @NotNull
        public static final BackClick INSTANCE = new BackClick();

        @NotNull
        public static final Parcelable.Creator<BackClick> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<BackClick> {
            @Override // android.os.Parcelable.Creator
            public final BackClick createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return BackClick.INSTANCE;
            }

            @Override // android.os.Parcelable.Creator
            public final BackClick[] newArray(int i10) {
                return new BackClick[i10];
            }
        }

        private BackClick() {
            super(null);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof BackClick);
        }

        public int hashCode() {
            return -1891206973;
        }

        @NotNull
        public String toString() {
            return "BackClick";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$CaptchaDone;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "ludwigToken", "", "<init>", "(Ljava/lang/String;)V", "getLudwigToken", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class CaptchaDone extends LudwigCaptchaResult {

        @NotNull
        private final String ludwigToken;

        @NotNull
        public static final Parcelable.Creator<CaptchaDone> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CaptchaDone> {
            @Override // android.os.Parcelable.Creator
            public final CaptchaDone createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CaptchaDone(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final CaptchaDone[] newArray(int i10) {
                return new CaptchaDone[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CaptchaDone(@NotNull String ludwigToken) {
            super(null);
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            this.ludwigToken = ludwigToken;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.ludwigToken);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u001a\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$Companion;", "", "<init>", "()V", "RESULT_KEY", "", "SERIALIZE_KEY", "getResult", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "res", "Landroid/os/Bundle;", "getBundle", "bundle", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nLudwigCaptchaResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LudwigCaptchaResult.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$Companion\n+ 2 Bundle.kt\nru/mail/android_utils/extension/BundleKt\n*L\n1#1,31:1\n8#2,5:32\n*S KotlinDebug\n*F\n+ 1 LudwigCaptchaResult.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$Companion\n*L\n24#1:32,5\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Bundle getBundle$default(Companion companion, LudwigCaptchaResult ludwigCaptchaResult, Bundle bundle, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                bundle = null;
            }
            return companion.getBundle(ludwigCaptchaResult, bundle);
        }

        @NotNull
        public final Bundle getBundle(@NotNull LudwigCaptchaResult res, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(res, "res");
            if (bundle == null) {
                bundle = BundleKt.bundleOf();
            }
            bundle.putParcelable(LudwigCaptchaResult.SERIALIZE_KEY, res);
            return bundle;
        }

        @Nullable
        public final LudwigCaptchaResult getResult(@Nullable Bundle res) {
            if (res != null) {
                return (LudwigCaptchaResult) (Build.VERSION.SDK_INT >= 33 ? res.getParcelable(LudwigCaptchaResult.SERIALIZE_KEY, LudwigCaptchaResult.class) : res.getParcelable(LudwigCaptchaResult.SERIALIZE_KEY));
            }
            return null;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult$Error;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error extends LudwigCaptchaResult {

        @NotNull
        private final String error;

        @NotNull
        public static final Parcelable.Creator<Error> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Error> {
            @Override // android.os.Parcelable.Creator
            public final Error createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Error(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final Error[] newArray(int i10) {
                return new Error[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(@NotNull String error) {
            super(null);
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final String getError() {
            return this.error;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.error);
        }
    }

    public /* synthetic */ LudwigCaptchaResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private LudwigCaptchaResult() {
    }
}
