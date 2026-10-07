package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.params;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0010HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "Landroid/os/Parcelable;", "url", "", "imitationHost", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getUrl", "()Ljava/lang/String;", "getImitationHost", "writeToParcel", "", Collector.FLAGS, "", "describeContents", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "CREATOR", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LudwigHost implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final String imitationHost;

    @NotNull
    private final String url;

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.params.LudwigHost$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<LudwigHost> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public LudwigHost createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LudwigHost(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public LudwigHost[] newArray(int size) {
            return new LudwigHost[size];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LudwigHost() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ LudwigHost copy$default(LudwigHost ludwigHost, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = ludwigHost.url;
        }
        if ((i10 & 2) != 0) {
            str2 = ludwigHost.imitationHost;
        }
        return ludwigHost.copy(str, str2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getImitationHost() {
        return this.imitationHost;
    }

    @NotNull
    public final LudwigHost copy(@NotNull String url, @NotNull String imitationHost) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imitationHost, "imitationHost");
        return new LudwigHost(url, imitationHost);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LudwigHost)) {
            return false;
        }
        LudwigHost ludwigHost = (LudwigHost) other;
        return Intrinsics.areEqual(this.url, ludwigHost.url) && Intrinsics.areEqual(this.imitationHost, ludwigHost.imitationHost);
    }

    @NotNull
    public final String getImitationHost() {
        return this.imitationHost;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return (this.url.hashCode() * 31) + this.imitationHost.hashCode();
    }

    @NotNull
    public String toString() {
        return "LudwigHost(url=" + this.url + ", imitationHost=" + this.imitationHost + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.url);
        parcel.writeString(this.imitationHost);
    }

    public LudwigHost(@NotNull String url, @NotNull String imitationHost) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(imitationHost, "imitationHost");
        this.url = url;
        this.imitationHost = imitationHost;
    }

    public /* synthetic */ LudwigHost(String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? "https://access.mini-mail.ru?mp=android" : str, (i10 & 2) != 0 ? "https://access.mini-mail.ru/" : str2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LudwigHost(@NotNull Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        string = string == null ? "" : string;
        String string2 = parcel.readString();
        this(string, string2 != null ? string2 : "");
    }
}
