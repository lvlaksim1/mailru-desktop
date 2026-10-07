package com.vk.id.captcha.init;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.vk.id.captcha.api.VKCaptcha;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.Constants;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ3\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013JQ\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0017¢\u0006\u0004\b\u0017\u0010\u0018J=\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0019\u0010\u001a"}, d2 = {"Lcom/vk/id/captcha/init/SdkInitContentProvider;", "Landroid/content/ContentProvider;", "Landroid/net/Uri;", "uri", "", Constants.SELECTION_STORY_ITEM_TYPE, "", "selectionArgs", "", "delete", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "(Landroid/net/Uri;)Ljava/lang/String;", "Landroid/content/ContentValues;", "values", "insert", "(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;", "", "onCreate", "()Z", "projection", "sortOrder", "Landroid/database/Cursor;", "query", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SdkInitContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(@NotNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "");
        throw new IllegalStateException("Not supported");
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final String getType(@NotNull Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        throw new IllegalStateException("Not supported");
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Uri insert(@NotNull Uri uri, @Nullable ContentValues values) {
        Intrinsics.checkNotNullParameter(uri, "");
        throw new IllegalStateException("Not supported");
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        try {
            VKCaptcha vKCaptcha = VKCaptcha.INSTANCE;
            Context context = getContext();
            if (context == null) {
                throw new IllegalStateException("Required value was null.");
            }
            Intrinsics.checkNotNullExpressionValue(context, "");
            vKCaptcha.init(context);
            return true;
        } catch (Throwable unused) {
            Log.e(Reflection.getOrCreateKotlinClass(SdkInitContentProvider.class).getSimpleName(), "Failed to initialize Captcha SDK");
            return true;
        }
    }

    @Override // android.content.ContentProvider
    @Nullable
    public final Cursor query(@NotNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Intrinsics.checkNotNullParameter(uri, "");
        throw new IllegalStateException("Not supported");
    }

    @Override // android.content.ContentProvider
    public final int update(@NotNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "");
        throw new IllegalStateException("Not supported");
    }
}
