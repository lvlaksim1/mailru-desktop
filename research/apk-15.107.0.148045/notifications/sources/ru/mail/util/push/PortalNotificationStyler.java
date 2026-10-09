package ru.mail.util.push;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spanned;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.text.HtmlCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ads.banner.list.ui.MissingFieldsInfo;
import ru.mail.imageloader.ContextWrapper;
import ru.mail.imageloader.ImageLoaderRepository;
import ru.mail.locator.Locator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u00020\f*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010\u0012\u001a\u00020\f*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010\u0013\u001a\u00020\f*\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\f\u0010\u0014\u001a\u00020\f*\u00020\u000eH\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/util/push/PortalNotificationStyler;", "", "context", "Landroid/content/Context;", "pushBody", "", MissingFieldsInfo.FIELD_IMAGE_URL, "imageType", "summaryText", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "setStyle", "", "builder", "Landroidx/core/app/NotificationCompat$Builder;", "setPictureStyle", "image", "Landroid/graphics/Bitmap;", "setBigPictureStyle", "setSmallPictureStyle", "setBigTextStyle", "loadImage", "getSpannedText", "Landroid/text/Spanned;", "text", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalNotificationStyler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalNotificationStyler.kt\nru/mail/util/push/PortalNotificationStyler\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,96:1\n38#2,4:97\n38#2,4:101\n38#2,4:105\n*S KotlinDebug\n*F\n+ 1 PortalNotificationStyler.kt\nru/mail/util/push/PortalNotificationStyler\n*L\n49#1:97,4\n61#1:101,4\n75#1:105,4\n*E\n"})
public final class PortalNotificationStyler {

    @NotNull
    private static final String BIG_IMAGE_STYLE = "big";

    @NotNull
    private final Context context;

    @Nullable
    private final String imageType;

    @Nullable
    private final String imageUrl;

    @Nullable
    private final String pushBody;

    @Nullable
    private final String summaryText;
    public static final int $stable = 8;

    public PortalNotificationStyler(@NotNull Context context, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.pushBody = str;
        this.imageUrl = str2;
        this.imageType = str3;
        this.summaryText = str4;
    }

    private final Spanned getSpannedText(String text) {
        Spanned spannedFromHtml = HtmlCompat.fromHtml(text, 63);
        Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
        return spannedFromHtml;
    }

    private final Bitmap loadImage() {
        return ((ImageLoaderRepository) Locator.INSTANCE.from(this.context).locate(ImageLoaderRepository.class)).getSharedImageLoader().loadImageByUrlDirectly(this.imageUrl, ContextWrapper.INSTANCE.toContextWrapper(this.context));
    }

    private final void setBigPictureStyle(NotificationCompat.Builder builder, Bitmap bitmap) {
        NotificationCompat.BigPictureStyle bigPictureStyleBigPicture = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
        String str = this.summaryText;
        if (!(str == null || str.length() == 0)) {
            bigPictureStyleBigPicture.setSummaryText(this.summaryText);
        }
        builder.setStyle(bigPictureStyleBigPicture).setLargeIcon(bitmap);
    }

    private final void setBigTextStyle(NotificationCompat.Builder builder) {
        String str = this.pushBody;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        NotificationCompat.BigTextStyle bigTextStyleBigText = new NotificationCompat.BigTextStyle().bigText(getSpannedText(this.pushBody));
        String str2 = this.summaryText;
        if (!(str2 == null || str2.length() == 0)) {
            bigTextStyleBigText.setSummaryText(this.summaryText);
        }
        builder.setStyle(bigTextStyleBigText);
    }

    private final void setPictureStyle(NotificationCompat.Builder builder, Bitmap bitmap) {
        if (Intrinsics.areEqual(this.imageType, BIG_IMAGE_STYLE)) {
            setBigPictureStyle(builder, bitmap);
        } else {
            setSmallPictureStyle(builder, bitmap);
        }
    }

    private final void setSmallPictureStyle(NotificationCompat.Builder builder, Bitmap bitmap) {
        String str = this.pushBody;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        NotificationCompat.BigTextStyle bigTextStyleBigText = new NotificationCompat.BigTextStyle().bigText(getSpannedText(this.pushBody));
        String str2 = this.summaryText;
        if (!(str2 == null || str2.length() == 0)) {
            bigTextStyleBigText.setSummaryText(this.summaryText);
        }
        builder.setStyle(bigTextStyleBigText).setLargeIcon(bitmap);
    }

    public final void setStyle(@NotNull NotificationCompat.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        String str = this.pushBody;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        builder.setContentText(getSpannedText(this.pushBody));
        String str2 = this.imageUrl;
        if (str2 == null || StringsKt.isBlank(str2)) {
            setBigTextStyle(builder);
            return;
        }
        Bitmap bitmapLoadImage = loadImage();
        if (bitmapLoadImage != null) {
            setPictureStyle(builder, bitmapLoadImage);
        } else {
            setBigTextStyle(builder);
        }
    }
}
