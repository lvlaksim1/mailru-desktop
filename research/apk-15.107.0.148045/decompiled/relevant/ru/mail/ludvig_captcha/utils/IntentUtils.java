package ru.mail.ludvig_captcha.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0007J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0007J6\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u000eH\u0002J$\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u0002J\u001c\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u0002¨\u0006\u001a"}, d2 = {"Lru/mail/ludvig_captcha/utils/IntentUtils;", "", "<init>", "()V", "createIntentFromUri", "Landroid/content/Intent;", "uri", "", "openGooglePlay", "", "context", "Landroid/content/Context;", "packageName", "newTask", "", "withChooser", "refererAttachFile", "Landroid/net/Uri;", "createShareTextIntent", "textToShare", "createIntentAndStartActivity", "activityFound", "createMarketUri", "referrerAttachFilePath", "prepareReferrerMarketParameter", "url", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IntentUtils {

    @NotNull
    public static final IntentUtils INSTANCE = new IntentUtils();

    private IntentUtils() {
    }

    private final void createIntentAndStartActivity(Context context, Uri uri, boolean newTask, boolean withChooser, boolean activityFound) {
        Intent intentCreateChooser = (!activityFound && withChooser) ? Intent.createChooser(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, uri).addCategory("android.intent.category.BROWSABLE"), "Choose") : new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, uri);
        if (newTask) {
            intentCreateChooser.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        ContextCompat.startActivity(context, intentCreateChooser, null);
    }

    static /* synthetic */ void createIntentAndStartActivity$default(IntentUtils intentUtils, Context context, Uri uri, boolean z10, boolean z11, boolean z12, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        if ((i10 & 8) != 0) {
            z11 = false;
        }
        if ((i10 & 16) != 0) {
            z12 = true;
        }
        intentUtils.createIntentAndStartActivity(context, uri, z10, z11, z12);
    }

    @JvmStatic
    @NotNull
    public static final Intent createIntentFromUri(@NotNull String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intent flags = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(uri)).setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        Intrinsics.checkNotNullExpressionValue(flags, "setFlags(...)");
        return flags;
    }

    private final Uri createMarketUri(boolean activityFound, String packageName, String referrerAttachFilePath) {
        String str;
        if (activityFound) {
            str = "market://details?id=" + packageName;
        } else {
            str = "https://play.google.com/store/apps/details?id=" + packageName;
        }
        Uri uri = Uri.parse(prepareReferrerMarketParameter(str, referrerAttachFilePath));
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        return uri;
    }

    static /* synthetic */ Uri createMarketUri$default(IntentUtils intentUtils, boolean z10, String str, String str2, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        return intentUtils.createMarketUri(z10, str, str2);
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        openGooglePlay$default(context, uri, false, false, 12, null);
    }

    public static /* synthetic */ void openGooglePlay$default(Context context, String str, boolean z10, boolean z11, String str2, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        if ((i10 & 8) != 0) {
            z11 = false;
        }
        if ((i10 & 16) != 0) {
            str2 = null;
        }
        openGooglePlay(context, str, z10, z11, str2);
    }

    private final String prepareReferrerMarketParameter(String url, String referrerAttachFilePath) {
        if (url == null || url.length() == 0) {
            return url;
        }
        return url + "&referrer=attach_file" + Uri.encode("=" + referrerAttachFilePath);
    }

    static /* synthetic */ String prepareReferrerMarketParameter$default(IntentUtils intentUtils, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        return intentUtils.prepareReferrerMarketParameter(str, str2);
    }

    @NotNull
    public final Intent createShareTextIntent(@NotNull String textToShare) {
        Intrinsics.checkNotNullParameter(textToShare, "textToShare");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", textToShare);
        Intent intentCreateChooser = Intent.createChooser(intent, "Share");
        intentCreateChooser.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        Intrinsics.checkNotNullExpressionValue(intentCreateChooser, "apply(...)");
        return intentCreateChooser;
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull Uri uri, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        openGooglePlay$default(context, uri, z10, false, 8, null);
    }

    public static /* synthetic */ void openGooglePlay$default(Context context, Uri uri, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        if ((i10 & 8) != 0) {
            z11 = false;
        }
        openGooglePlay(context, uri, z10, z11);
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull String packageName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        openGooglePlay$default(context, packageName, false, false, null, 28, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull String packageName, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        openGooglePlay$default(context, packageName, z10, false, null, 24, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull String packageName, boolean z10, boolean z11) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        openGooglePlay$default(context, packageName, z10, z11, null, 16, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull String packageName, boolean newTask, boolean withChooser, @Nullable String refererAttachFile) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        try {
            try {
                IntentUtils intentUtils = INSTANCE;
                intentUtils.createIntentAndStartActivity(context, intentUtils.createMarketUri(true, packageName, refererAttachFile), newTask, withChooser, true);
            } catch (ActivityNotFoundException unused) {
                IntentUtils intentUtils2 = INSTANCE;
                intentUtils2.createIntentAndStartActivity(context, createMarketUri$default(intentUtils2, false, packageName, null, 4, null), newTask, withChooser, false);
            }
        } catch (Exception unused2) {
            Log.v(LudwigSdkInitializer.LUDWIG_SDK_TAG, "IntentUtils: Open " + packageName + " in Google Play fail!!");
        }
    }

    @JvmStatic
    @JvmOverloads
    public static final void openGooglePlay(@NotNull Context context, @NotNull Uri uri, boolean newTask, boolean withChooser) {
        Uri uri2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            uri2 = uri;
            try {
                INSTANCE.createIntentAndStartActivity(context, uri2, newTask, withChooser, true);
            } catch (Exception unused) {
                Log.v(LudwigSdkInitializer.LUDWIG_SDK_TAG, "IntentUtils: Open " + uri2 + " in Google Play fail!!");
            }
        } catch (Exception unused2) {
            uri2 = uri;
        }
    }
}
