package ru.mail.util.push;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 E2\u00020\u0001:\u0001EB\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0013\u0010\u0014B\u0011\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0013\u0010\u0017J\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\b\b\u0001\u0010)\u001a\u00020*H\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\tH\u0016J\u000e\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00107\u001a\u00020\tHÂ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010;\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¥\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010@\u001a\u00020/2\b\u0010A\u001a\u0004\u0018\u00010BHÖ\u0003J\t\u0010C\u001a\u00020\tHÖ\u0001J\t\u0010D\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019¨\u0006F"}, d2 = {"Lru/mail/util/push/PortalPush;", "Lru/mail/util/push/PushMessage;", "deepLink", "", "title", "body", "pushCampaign", "emailFromPush", "pushEventId", "", "imgUrl", "imgType", "langFilter", "buttons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "app", "onOpenAnalyticUrl", "summaryText", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getDeepLink", "()Ljava/lang/String;", "getTitle", "getBody", "getPushCampaign", "getEmailFromPush", "getImgUrl", "getImgType", "getLangFilter", "getButtons", "()Ljava/util/List;", "getApp", "getOnOpenAnalyticUrl", "getSummaryText", "accept", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "visitor", "Lru/mail/util/push/PushMessageVisitor;", "writeToParcel", "", Collector.FLAGS, "isApplicable", "", "context", "Landroid/content/Context;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "", "hashCode", "toString", "CREATOR", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PortalPush extends PushMessage {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private final String app;

    @Nullable
    private final String body;

    @Nullable
    private final List<PortalPushButton> buttons;

    @NotNull
    private final String deepLink;

    @Nullable
    private final String emailFromPush;

    @Nullable
    private final String imgType;

    @Nullable
    private final String imgUrl;

    @Nullable
    private final String langFilter;

    @Nullable
    private final String onOpenAnalyticUrl;

    @Nullable
    private final String pushCampaign;
    private final int pushEventId;

    @Nullable
    private final String summaryText;

    @NotNull
    private final String title;

    /* JADX INFO: renamed from: ru.mail.util.push.PortalPush$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/util/push/PortalPush$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/util/push/PortalPush;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lru/mail/util/push/PortalPush;", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<PortalPush> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public PortalPush createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PortalPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public PortalPush[] newArray(int size) {
            return new PortalPush[size];
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PortalPush(@NotNull String deepLink, @NotNull String title, @Nullable String str, @Nullable String str2, @Nullable String str3, int i10, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<PortalPushButton> list, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        super(i10, str3);
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        Intrinsics.checkNotNullParameter(title, "title");
        this.deepLink = deepLink;
        this.title = title;
        this.body = str;
        this.pushCampaign = str2;
        this.emailFromPush = str3;
        this.pushEventId = i10;
        this.imgUrl = str4;
        this.imgType = str5;
        this.langFilter = str6;
        this.buttons = list;
        this.app = str7;
        this.onOpenAnalyticUrl = str8;
        this.summaryText = str9;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    private final int getPushEventId() {
        return this.pushEventId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PortalPush copy$default(PortalPush portalPush, String str, String str2, String str3, String str4, String str5, int i10, String str6, String str7, String str8, List list, String str9, String str10, String str11, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = portalPush.deepLink;
        }
        return portalPush.copy(str, (i11 & 2) != 0 ? portalPush.title : str2, (i11 & 4) != 0 ? portalPush.body : str3, (i11 & 8) != 0 ? portalPush.pushCampaign : str4, (i11 & 16) != 0 ? portalPush.emailFromPush : str5, (i11 & 32) != 0 ? portalPush.pushEventId : i10, (i11 & 64) != 0 ? portalPush.imgUrl : str6, (i11 & 128) != 0 ? portalPush.imgType : str7, (i11 & 256) != 0 ? portalPush.langFilter : str8, (i11 & 512) != 0 ? portalPush.buttons : list, (i11 & 1024) != 0 ? portalPush.app : str9, (i11 & 2048) != 0 ? portalPush.onOpenAnalyticUrl : str10, (i11 & 4096) != 0 ? portalPush.summaryText : str11);
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    @NotNull
    public ObservableFuture<Void> accept(@NonNull @NotNull PushMessageVisitor visitor) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        ObservableFuture<Void> observableFutureVisit = visitor.visit(this);
        Intrinsics.checkNotNullExpressionValue(observableFutureVisit, "visit(...)");
        return observableFutureVisit;
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDeepLink() {
        return this.deepLink;
    }

    @Nullable
    public final List<PortalPushButton> component10() {
        return this.buttons;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getApp() {
        return this.app;
    }

    @Nullable
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getOnOpenAnalyticUrl() {
        return this.onOpenAnalyticUrl;
    }

    @Nullable
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSummaryText() {
        return this.summaryText;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBody() {
        return this.body;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPushCampaign() {
        return this.pushCampaign;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEmailFromPush() {
        return this.emailFromPush;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getImgUrl() {
        return this.imgUrl;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getImgType() {
        return this.imgType;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getLangFilter() {
        return this.langFilter;
    }

    @NotNull
    public final PortalPush copy(@NotNull String deepLink, @NotNull String title, @Nullable String body, @Nullable String pushCampaign, @Nullable String emailFromPush, int pushEventId, @Nullable String imgUrl, @Nullable String imgType, @Nullable String langFilter, @Nullable List<PortalPushButton> buttons, @Nullable String app, @Nullable String onOpenAnalyticUrl, @Nullable String summaryText) {
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        Intrinsics.checkNotNullParameter(title, "title");
        return new PortalPush(deepLink, title, body, pushCampaign, emailFromPush, pushEventId, imgUrl, imgType, langFilter, buttons, app, onOpenAnalyticUrl, summaryText);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PortalPush)) {
            return false;
        }
        PortalPush portalPush = (PortalPush) other;
        return Intrinsics.areEqual(this.deepLink, portalPush.deepLink) && Intrinsics.areEqual(this.title, portalPush.title) && Intrinsics.areEqual(this.body, portalPush.body) && Intrinsics.areEqual(this.pushCampaign, portalPush.pushCampaign) && Intrinsics.areEqual(this.emailFromPush, portalPush.emailFromPush) && this.pushEventId == portalPush.pushEventId && Intrinsics.areEqual(this.imgUrl, portalPush.imgUrl) && Intrinsics.areEqual(this.imgType, portalPush.imgType) && Intrinsics.areEqual(this.langFilter, portalPush.langFilter) && Intrinsics.areEqual(this.buttons, portalPush.buttons) && Intrinsics.areEqual(this.app, portalPush.app) && Intrinsics.areEqual(this.onOpenAnalyticUrl, portalPush.onOpenAnalyticUrl) && Intrinsics.areEqual(this.summaryText, portalPush.summaryText);
    }

    @Nullable
    public final String getApp() {
        return this.app;
    }

    @Nullable
    public final String getBody() {
        return this.body;
    }

    @Nullable
    public final List<PortalPushButton> getButtons() {
        return this.buttons;
    }

    @NotNull
    public final String getDeepLink() {
        return this.deepLink;
    }

    @Nullable
    public final String getEmailFromPush() {
        return this.emailFromPush;
    }

    @Nullable
    public final String getImgType() {
        return this.imgType;
    }

    @Nullable
    public final String getImgUrl() {
        return this.imgUrl;
    }

    @Nullable
    public final String getLangFilter() {
        return this.langFilter;
    }

    @Nullable
    public final String getOnOpenAnalyticUrl() {
        return this.onOpenAnalyticUrl;
    }

    @Nullable
    public final String getPushCampaign() {
        return this.pushCampaign;
    }

    @Nullable
    public final String getSummaryText() {
        return this.summaryText;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = ((this.deepLink.hashCode() * 31) + this.title.hashCode()) * 31;
        String str = this.body;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.pushCampaign;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.emailFromPush;
        int iHashCode4 = (((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + Integer.hashCode(this.pushEventId)) * 31;
        String str4 = this.imgUrl;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.imgType;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.langFilter;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<PortalPushButton> list = this.buttons;
        int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        String str7 = this.app;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.onOpenAnalyticUrl;
        int iHashCode10 = (iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.summaryText;
        return iHashCode10 + (str9 != null ? str9.hashCode() : 0);
    }

    public final boolean isApplicable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.langFilter;
        if (str == null || str.length() == 0) {
            return true;
        }
        return Intrinsics.areEqual(context.getResources().getConfiguration().locale.getLanguage(), new Locale(this.langFilter).getLanguage());
    }

    @NotNull
    public String toString() {
        return "PortalPush(deepLink=" + this.deepLink + ", title=" + this.title + ", body=" + this.body + ", pushCampaign=" + this.pushCampaign + ", emailFromPush=" + this.emailFromPush + ", pushEventId=" + this.pushEventId + ", imgUrl=" + this.imgUrl + ", imgType=" + this.imgType + ", langFilter=" + this.langFilter + ", buttons=" + this.buttons + ", app=" + this.app + ", onOpenAnalyticUrl=" + this.onOpenAnalyticUrl + ", summaryText=" + this.summaryText + ")";
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.deepLink);
        parcel.writeString(this.title);
        parcel.writeString(this.body);
        parcel.writeString(this.pushCampaign);
        parcel.writeString(this.emailFromPush);
        parcel.writeInt(this.pushEventId);
        parcel.writeString(this.imgUrl);
        parcel.writeString(this.imgType);
        parcel.writeString(this.langFilter);
        parcel.writeList(this.buttons);
        parcel.writeString(this.app);
        parcel.writeString(this.onOpenAnalyticUrl);
        parcel.writeString(this.summaryText);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PortalPush(@NotNull Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        this(string, string2, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readArrayList(PortalPushButton.class.getClassLoader()), parcel.readString(), parcel.readString(), parcel.readString());
    }
}
