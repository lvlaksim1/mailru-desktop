package ru.mail.portal.app.adapter.notifications;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0014H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u0014HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006#"}, d2 = {"Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "Landroid/os/Parcelable;", "title", "", "deepLink", "closeNotificationByClick", "", "needSendOpenAction", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getTitle", "()Ljava/lang/String;", "getDeepLink", "getCloseNotificationByClick", "()Z", "getNeedSendOpenAction", "describeContents", "", "writeToParcel", "", Collector.FLAGS, "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "CREATOR", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PortalPushButton implements Parcelable {
    private static final byte BYTE_ONE = 1;
    private static final byte BYTE_ZERO = 0;

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private final boolean closeNotificationByClick;

    @NotNull
    private final String deepLink;
    private final boolean needSendOpenAction;

    @NotNull
    private final String title;

    /* JADX INFO: renamed from: ru.mail.portal.app.adapter.notifications.PortalPushButton$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/portal/app/adapter/notifications/PortalPushButton$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "BYTE_ONE", "", "BYTE_ZERO", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<PortalPushButton> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public PortalPushButton createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PortalPushButton(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public PortalPushButton[] newArray(int size) {
            return new PortalPushButton[size];
        }
    }

    public PortalPushButton(@NotNull String title, @NotNull String deepLink, boolean z10, boolean z11) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        this.title = title;
        this.deepLink = deepLink;
        this.closeNotificationByClick = z10;
        this.needSendOpenAction = z11;
    }

    public static /* synthetic */ PortalPushButton copy$default(PortalPushButton portalPushButton, String str, String str2, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = portalPushButton.title;
        }
        if ((i10 & 2) != 0) {
            str2 = portalPushButton.deepLink;
        }
        if ((i10 & 4) != 0) {
            z10 = portalPushButton.closeNotificationByClick;
        }
        if ((i10 & 8) != 0) {
            z11 = portalPushButton.needSendOpenAction;
        }
        return portalPushButton.copy(str, str2, z10, z11);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDeepLink() {
        return this.deepLink;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getCloseNotificationByClick() {
        return this.closeNotificationByClick;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getNeedSendOpenAction() {
        return this.needSendOpenAction;
    }

    @NotNull
    public final PortalPushButton copy(@NotNull String title, @NotNull String deepLink, boolean closeNotificationByClick, boolean needSendOpenAction) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        return new PortalPushButton(title, deepLink, closeNotificationByClick, needSendOpenAction);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PortalPushButton)) {
            return false;
        }
        PortalPushButton portalPushButton = (PortalPushButton) other;
        return Intrinsics.areEqual(this.title, portalPushButton.title) && Intrinsics.areEqual(this.deepLink, portalPushButton.deepLink) && this.closeNotificationByClick == portalPushButton.closeNotificationByClick && this.needSendOpenAction == portalPushButton.needSendOpenAction;
    }

    public final boolean getCloseNotificationByClick() {
        return this.closeNotificationByClick;
    }

    @NotNull
    public final String getDeepLink() {
        return this.deepLink;
    }

    public final boolean getNeedSendOpenAction() {
        return this.needSendOpenAction;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return (((((this.title.hashCode() * 31) + this.deepLink.hashCode()) * 31) + Boolean.hashCode(this.closeNotificationByClick)) * 31) + Boolean.hashCode(this.needSendOpenAction);
    }

    @NotNull
    public String toString() {
        return "PortalPushButton(title=" + this.title + ", deepLink=" + this.deepLink + ", closeNotificationByClick=" + this.closeNotificationByClick + ", needSendOpenAction=" + this.needSendOpenAction + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeString(this.deepLink);
        if (this.closeNotificationByClick) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
        if (this.needSendOpenAction) {
            parcel.writeByte((byte) 1);
        } else {
            parcel.writeByte((byte) 0);
        }
    }

    public /* synthetic */ PortalPushButton(String str, String str2, boolean z10, boolean z11, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i10 & 4) != 0 ? true : z10, (i10 & 8) != 0 ? false : z11);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PortalPushButton(@NotNull Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        this(string, string2, parcel.readByte() == 1, parcel.readByte() == 1);
    }
}
