package ru.mail.util.push;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.mail.imageloader.ContextWrapper;
import ru.mail.imageloader.ImageLoaderRepository;
import ru.mail.locator.Locator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class NotificationAvatarLoader {
    private static final long AVATAR_LOAD_TIMEOUT_MS = 1000;
    private static final int COLOR = -16777216;
    private final Context mApplicationContext;

    @Nullable
    private Bitmap mAvatar;
    private final String mEmail;
    private final boolean mHide;
    private boolean mIsDefault;
    private final String mName;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    public static abstract class LoadAvatarStrategy {
        private static final /* synthetic */ LoadAvatarStrategy[] $VALUES = $values();
        public static final LoadAvatarStrategy FROM_CACHE;
        public static final LoadAvatarStrategy FROM_CACHE_OR_NETWORK;

        /* JADX INFO: renamed from: ru.mail.util.push.NotificationAvatarLoader$LoadAvatarStrategy$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends LoadAvatarStrategy {
            @Override // ru.mail.util.push.NotificationAvatarLoader.LoadAvatarStrategy
            public BitmapDrawable load(NotificationAvatarLoader notificationAvatarLoader) {
                return ((ImageLoaderRepository) Locator.from(notificationAvatarLoader.mApplicationContext).locate(ImageLoaderRepository.class)).getAvatarLoader(notificationAvatarLoader.mEmail).loadAvatarDirectly(notificationAvatarLoader.mName, ContextWrapper.toContextWrapper(notificationAvatarLoader.mApplicationContext), 1000L, null);
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.util.push.NotificationAvatarLoader$LoadAvatarStrategy$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends LoadAvatarStrategy {
            @Override // ru.mail.util.push.NotificationAvatarLoader.LoadAvatarStrategy
            public BitmapDrawable load(NotificationAvatarLoader notificationAvatarLoader) {
                return ((ImageLoaderRepository) Locator.from(notificationAvatarLoader.mApplicationContext).locate(ImageLoaderRepository.class)).getAvatarLoader(notificationAvatarLoader.mEmail).loadAvatarFromCache(notificationAvatarLoader.mName, ContextWrapper.toContextWrapper(notificationAvatarLoader.mApplicationContext));
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ LoadAvatarStrategy[] $values() {
            return new LoadAvatarStrategy[]{FROM_CACHE_OR_NETWORK, FROM_CACHE};
        }

        static {
            FROM_CACHE_OR_NETWORK = new AnonymousClass1("FROM_CACHE_OR_NETWORK", 0);
            FROM_CACHE = new AnonymousClass2("FROM_CACHE", 1);
        }

        public static LoadAvatarStrategy valueOf(String str) {
            return (LoadAvatarStrategy) Enum.valueOf(LoadAvatarStrategy.class, str);
        }

        public static LoadAvatarStrategy[] values() {
            return (LoadAvatarStrategy[]) $VALUES.clone();
        }

        public abstract BitmapDrawable load(NotificationAvatarLoader notificationAvatarLoader);

        private LoadAvatarStrategy(String str, int i10) {
            super(str, i10);
        }
    }

    public NotificationAvatarLoader(@NonNull Context context, boolean z10) {
        this(context, null, null, z10);
    }

    private Bitmap getCroppedBitmap(@NonNull Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setColor(COLOR);
        canvas.drawCircle(bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f, bitmap.getWidth() / 2.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    private void loadAvatarBitmap(LoadAvatarStrategy loadAvatarStrategy, int i10) {
        BitmapDrawable bitmapDrawableLoad;
        if (!this.mHide && !TextUtils.isEmpty(this.mEmail) && (bitmapDrawableLoad = loadAvatarStrategy.load(this)) != null) {
            this.mAvatar = bitmapDrawableLoad.getBitmap();
            this.mIsDefault = false;
        }
        if (this.mAvatar == null) {
            this.mAvatar = BitmapFactory.decodeResource(this.mApplicationContext.getResources(), i10);
        }
    }

    private void scaleAndCropAvatar() {
        int iMin = Math.min((int) this.mApplicationContext.getResources().getDimension(android.R.dimen.notification_large_icon_height), (int) this.mApplicationContext.getResources().getDimension(android.R.dimen.notification_large_icon_width));
        Bitmap bitmap = this.mAvatar;
        if (bitmap == null || iMin <= 0) {
            this.mAvatar = null;
            return;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, iMin, iMin, true);
        Bitmap croppedBitmap = getCroppedBitmap(bitmapCreateScaledBitmap);
        this.mAvatar = croppedBitmap;
        if (bitmapCreateScaledBitmap != bitmap && bitmapCreateScaledBitmap != croppedBitmap) {
            bitmapCreateScaledBitmap.recycle();
        }
        if (bitmap != this.mAvatar) {
            bitmap.recycle();
        }
    }

    @Nullable
    public Bitmap getAvatar() {
        return this.mAvatar;
    }

    public boolean isDefaultIcon() {
        return this.mIsDefault;
    }

    public NotificationAvatarLoader load(int i10) {
        loadAvatarBitmap(LoadAvatarStrategy.FROM_CACHE, i10);
        scaleAndCropAvatar();
        return this;
    }

    public NotificationAvatarLoader(@NonNull Context context, @Nullable String str, @Nullable String str2, boolean z10) {
        this.mApplicationContext = context;
        this.mName = str;
        this.mEmail = str2;
        this.mHide = z10;
        this.mIsDefault = true;
    }
}
