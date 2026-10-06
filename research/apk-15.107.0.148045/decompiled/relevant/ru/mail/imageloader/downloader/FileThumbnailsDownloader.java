package ru.mail.imageloader.downloader;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Size;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import ru.mail.android_utils.IOCompatUtils;
import ru.mail.android_utils.SdkUtils;
import ru.mail.imageloader.DecodeBitmapFileMemoryError;
import ru.mail.imageloader.ImageDownloader;
import ru.mail.imageloader.ImageParameters;
import ru.mail.imageloader.ImageResizer;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.logic.content.Permission;
import ru.mail.util.DirectoryRepository;
import ru.mail.util.bitmapfun.upgrade.CacheSizeInfoImpl;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.utils.UriUtils;
import ru.mail.utils.safeutils.BaseRequestImpl;
import ru.mail.utils.streams.AbstractByteArrayOutputStreamWrapper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class FileThumbnailsDownloader implements ImageDownloader {
    private static final Log LOG = Log.getLog("FileThumbnailsDownloader");
    protected static final LogFilter sLogFilter = new LogFilter(Formats.newJsonFormat("token"), Formats.newJsonFormat("access_token"));

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    private static abstract class BitmapFactory {
        private static final /* synthetic */ BitmapFactory[] $VALUES = $values();
        public static final BitmapFactory PROVIDER_IMAGE;
        public static final BitmapFactory PROVIDER_VIDEO;
        public static final BitmapFactory RAW_FILE;

        /* JADX INFO: renamed from: ru.mail.imageloader.downloader.FileThumbnailsDownloader$BitmapFactory$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends BitmapFactory {
            private Bitmap getImagesThumbnail(Context context, final Long l10) {
                return getThumbnail(new BaseRequestImpl<Bitmap, Context>(context) { // from class: ru.mail.imageloader.downloader.FileThumbnailsDownloader.BitmapFactory.1.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // ru.mail.utils.safeutils.BaseRequestImpl
                    public Bitmap executeRequest(Context context2) {
                        return MediaStore.Images.Thumbnails.getThumbnail(context2.getContentResolver(), l10.longValue(), 1, new android.graphics.BitmapFactory.Options());
                    }
                });
            }

            @Override // ru.mail.imageloader.downloader.FileThumbnailsDownloader.BitmapFactory
            public Bitmap create(Context context, String str, OutputStream outputStream, int i10, int i11) {
                Uri uri = Uri.parse(str);
                Bitmap thumbnail = (UriUtils.isContentUri(uri) && SdkUtils.hasQ()) ? getThumbnail(context.getContentResolver(), uri, i10, i11) : getImagesThumbnail(context, FileThumbnailsDownloader.getImageId(context.getContentResolver(), str));
                if (thumbnail == null) {
                    return null;
                }
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                return thumbnail;
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.imageloader.downloader.FileThumbnailsDownloader$BitmapFactory$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends BitmapFactory {
            private Bitmap getVideoThumbnail(Context context, final Long l10) {
                return getThumbnail(new BaseRequestImpl<Bitmap, Context>(context) { // from class: ru.mail.imageloader.downloader.FileThumbnailsDownloader.BitmapFactory.2.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // ru.mail.utils.safeutils.BaseRequestImpl
                    public Bitmap executeRequest(Context context2) {
                        return MediaStore.Video.Thumbnails.getThumbnail(context2.getContentResolver(), l10.longValue(), 1, new android.graphics.BitmapFactory.Options());
                    }
                });
            }

            @Override // ru.mail.imageloader.downloader.FileThumbnailsDownloader.BitmapFactory
            public Bitmap create(Context context, String str, OutputStream outputStream, int i10, int i11) {
                Uri uri = Uri.parse(str);
                Bitmap thumbnail = (UriUtils.isContentUri(uri) && SdkUtils.hasQ()) ? getThumbnail(context.getContentResolver(), uri, i10, i11) : getVideoThumbnail(context, FileThumbnailsDownloader.getVideoId(context.getContentResolver(), str));
                if (thumbnail == null) {
                    return null;
                }
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                return thumbnail;
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.imageloader.downloader.FileThumbnailsDownloader$BitmapFactory$3, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass3 extends BitmapFactory {
            @Override // ru.mail.imageloader.downloader.FileThumbnailsDownloader.BitmapFactory
            public Bitmap create(Context context, String str, OutputStream outputStream, int i10, int i11) throws Throwable {
                FileInputStream fileInputStream;
                FileInputStream fileInputStream2 = null;
                try {
                    try {
                        fileInputStream = new FileInputStream(str);
                        try {
                            Bitmap bitmapDecodeSampledBitmapFromFile = ImageResizer.decodeSampledBitmapFromFile(str, i10, i11);
                            if (bitmapDecodeSampledBitmapFromFile != null) {
                                AttachmentHelper.copyStreamToStream(fileInputStream, outputStream);
                            }
                            IOCompatUtils.closeQuietly(fileInputStream);
                            return bitmapDecodeSampledBitmapFromFile;
                        } catch (IOException unused) {
                            IOCompatUtils.closeQuietly(fileInputStream);
                            return null;
                        } catch (DecodeBitmapFileMemoryError.HolderException e10) {
                            e = e10;
                            throw e.getBuilder().build(CacheSizeInfoImpl.from(context));
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream2 = fileInputStream;
                            IOCompatUtils.closeQuietly(fileInputStream2);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (IOException unused2) {
                    fileInputStream = null;
                } catch (DecodeBitmapFileMemoryError.HolderException e11) {
                    e = e11;
                }
            }

            private AnonymousClass3(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ BitmapFactory[] $values() {
            return new BitmapFactory[]{PROVIDER_IMAGE, PROVIDER_VIDEO, RAW_FILE};
        }

        static {
            PROVIDER_IMAGE = new AnonymousClass1("PROVIDER_IMAGE", 0);
            PROVIDER_VIDEO = new AnonymousClass2("PROVIDER_VIDEO", 1);
            RAW_FILE = new AnonymousClass3("RAW_FILE", 2);
        }

        public static BitmapFactory valueOf(String str) {
            return (BitmapFactory) Enum.valueOf(BitmapFactory.class, str);
        }

        public static BitmapFactory[] values() {
            return (BitmapFactory[]) $VALUES.clone();
        }

        public abstract Bitmap create(Context context, String str, OutputStream outputStream, int i10, int i11);

        @RequiresApi(29)
        protected Bitmap getThumbnail(ContentResolver contentResolver, Uri uri, int i10, int i11) {
            try {
                return contentResolver.loadThumbnail(uri, new Size(i10, i11), null);
            } catch (IOException e10) {
                Log log = FileThumbnailsDownloader.LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Exception occured, when load thumbnail via content resolver with uri");
                sb2.append(FileThumbnailsDownloader.sLogFilter.filter("" + uri));
                log.e(sb2.toString(), e10);
                return null;
            }
        }

        private BitmapFactory(String str, int i10) {
            super(str, i10);
        }

        Bitmap getThumbnail(BaseRequestImpl<Bitmap, Context> baseRequestImpl) {
            return baseRequestImpl.onErrorReturn(null).perform();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long getImageId(ContentResolver contentResolver, String str) {
        Long imageId = UriUtils.getImageId(contentResolver, Uri.parse(str));
        if (imageId != null) {
            return imageId;
        }
        Cursor cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    long j10 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                    cursorQuery.close();
                    Long lValueOf = Long.valueOf(j10);
                    cursorQuery.close();
                    return lValueOf;
                }
            } catch (Throwable th2) {
                try {
                    cursorQuery.close();
                    throw th2;
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                    throw th2;
                }
            }
        }
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    private Bitmap getThumbnailBitmap(Context context, String str, OutputStream outputStream, int i10, int i11) throws Throwable {
        OutputStream outputStream2;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (isImage(contentResolver, str)) {
                try {
                    Bitmap bitmapCreate = BitmapFactory.PROVIDER_IMAGE.create(context, str, outputStream, i10, i11);
                    IOCompatUtils.closeQuietly(outputStream);
                    return bitmapCreate;
                } catch (Throwable th2) {
                    th = th2;
                    outputStream2 = outputStream;
                }
            } else {
                outputStream2 = outputStream;
                try {
                    if (isVideo(contentResolver, str)) {
                        Bitmap bitmapCreate2 = BitmapFactory.PROVIDER_VIDEO.create(context, str, outputStream2, i10, i11);
                        IOCompatUtils.closeQuietly(outputStream2);
                        return bitmapCreate2;
                    }
                    Bitmap bitmapCreate3 = BitmapFactory.RAW_FILE.create(context, str, outputStream2, i10, i11);
                    IOCompatUtils.closeQuietly(outputStream2);
                    return bitmapCreate3;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            outputStream2 = outputStream;
        }
        Throwable th5 = th;
        IOCompatUtils.closeQuietly(outputStream2);
        throw th5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long getVideoId(ContentResolver contentResolver, String str) {
        Long videoId = UriUtils.getVideoId(contentResolver, Uri.parse(str));
        if (videoId != null) {
            return videoId;
        }
        Cursor cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    long j10 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                    cursorQuery.close();
                    Long lValueOf = Long.valueOf(j10);
                    cursorQuery.close();
                    return lValueOf;
                }
            } catch (Throwable th2) {
                try {
                    cursorQuery.close();
                    throw th2;
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                    throw th2;
                }
            }
        }
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    private AbstractByteArrayOutputStreamWrapper.Persistent instantiateStream(Context context, ImageParameters imageParameters) throws IOException {
        File tempThumbnailAttachDir = DirectoryRepository.from(context).getTempThumbnailAttachDir();
        if (tempThumbnailAttachDir == null || tempThumbnailAttachDir.exists() || tempThumbnailAttachDir.mkdirs()) {
            return new AbstractByteArrayOutputStreamWrapper.Persistent(new File(tempThumbnailAttachDir, imageParameters.getDiskLruCacheKey()));
        }
        String str = "error creating folder :" + tempThumbnailAttachDir.getAbsolutePath();
        LOG.e(str);
        throw new IOException(str);
    }

    private boolean isImage(ContentResolver contentResolver, String str) {
        Uri uri = Uri.parse(str);
        if (UriUtils.isContentUri(uri)) {
            return UriUtils.isImageMimeType(contentResolver.getType(uri));
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                long j10 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                cursorQuery.close();
                if (j10 > 0) {
                    cursorQuery.close();
                    return true;
                }
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    private boolean isVideo(ContentResolver contentResolver, String str) {
        Uri uri = Uri.parse(str);
        if (UriUtils.isContentUri(uri)) {
            return UriUtils.isVideoMimeType(contentResolver.getType(uri));
        }
        Cursor cursorQuery = null;
        try {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, null);
            if (cursorQuery != null && cursorQuery.moveToFirst()) {
                long j10 = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
                cursorQuery.close();
                if (j10 > 0) {
                    cursorQuery.close();
                    return true;
                }
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    @Override // ru.mail.imageloader.ImageDownloader
    public final ImageDownloader.Result downloadToStream(ImageParameters imageParameters, Context context, int i10, int i11) throws Throwable {
        Throwable th2;
        if (!Permission.WRITE_EXTERNAL_STORAGE.isGranted(context)) {
            return ImageDownloader.Result.failed(new Exception("Permission not granted"));
        }
        AbstractByteArrayOutputStreamWrapper.Persistent persistent = null;
        try {
            try {
                AbstractByteArrayOutputStreamWrapper.Persistent persistentInstantiateStream = instantiateStream(context, imageParameters);
                try {
                    ImageDownloader.Result resultDownloadToStreamInternal = downloadToStreamInternal(context, imageParameters, persistentInstantiateStream, i10, i11);
                    IOCompatUtils.closeQuietly(persistentInstantiateStream);
                    return resultDownloadToStreamInternal;
                } catch (IOException e10) {
                    e = e10;
                    persistent = persistentInstantiateStream;
                    ImageDownloader.Result resultFailed = ImageDownloader.Result.failed(e);
                    IOCompatUtils.closeQuietly(persistent);
                    return resultFailed;
                } catch (Throwable th3) {
                    th2 = th3;
                    persistent = persistentInstantiateStream;
                    IOCompatUtils.closeQuietly(persistent);
                    throw th2;
                }
            } catch (IOException e11) {
                e = e11;
            }
        } catch (Throwable th4) {
            th2 = th4;
        }
    }

    protected ImageDownloader.Result downloadToStreamInternal(Context context, ImageParameters imageParameters, AbstractByteArrayOutputStreamWrapper.Persistent persistent, int i10, int i11) throws IOException {
        boolean z10 = getThumbnailBitmap(context, imageParameters.toString(), persistent, i10, i11) != null;
        return new ImageDownloader.Result(z10, persistent.toInputStream(), z10 ? persistent.getSrcFile() : null);
    }

    @Override // ru.mail.imageloader.ImageDownloader
    public String getEtag() {
        return null;
    }

    @Override // ru.mail.imageloader.ImageDownloader
    public Date getExpiredDate() {
        return null;
    }

    @Override // ru.mail.imageloader.ImageDownloader
    public long getMaxAge() {
        return 0L;
    }

    @Override // ru.mail.imageloader.ImageDownloader
    public DataSource getSourceType() {
        return DataSource.LOCAL;
    }
}
