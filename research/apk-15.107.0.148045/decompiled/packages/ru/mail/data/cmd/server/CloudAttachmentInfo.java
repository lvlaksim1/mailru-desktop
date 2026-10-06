package ru.mail.data.cmd.server;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import ru.mail.logic.content.MailAttacheEntry;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class CloudAttachmentInfo {
    private static final String CLOUD_SALT = "mrCloud";
    private static final Log LOG = Log.getLog("CloudAttachmentInfo");
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final String mHash;
    private final long mSize;
    private int mUploadAttemptCount;

    private CloudAttachmentInfo(String str, long j10) {
        this.mHash = str;
        this.mSize = j10;
    }

    private static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i10 = 0; i10 < bArr.length; i10++) {
            byte b10 = bArr[i10];
            int i11 = i10 * 2;
            char[] cArr2 = hexArray;
            cArr[i11] = cArr2[(b10 & 255) >>> 4];
            cArr[i11 + 1] = cArr2[b10 & 15];
        }
        return new String(cArr);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x0087 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    @Nullable
    public static CloudAttachmentInfo prepareInfo(Context context, MailAttacheEntry mailAttacheEntry) throws Throwable {
        InputStream inputStreamBlocking;
        ?? r10 = 0;
        try {
            try {
                inputStreamBlocking = mailAttacheEntry.getInputStreamBlocking(context);
                try {
                    if (inputStreamBlocking == null) {
                        LOG.e("Unable to get file stream");
                        if (inputStreamBlocking != null) {
                            try {
                                inputStreamBlocking.close();
                                return null;
                            } catch (IOException e10) {
                                LOG.e("Unable to close file stream", e10);
                            }
                        }
                        return null;
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                    messageDigest.update(CLOUD_SALT.getBytes(StandardCharsets.UTF_8), 0, 7);
                    byte[] bArr = new byte[524288];
                    long j10 = 0;
                    while (true) {
                        int i10 = inputStreamBlocking.read(bArr);
                        if (i10 == -1) {
                            String string = Long.toString(j10);
                            messageDigest.update(string.getBytes(StandardCharsets.UTF_8), 0, string.length());
                            CloudAttachmentInfo cloudAttachmentInfo = new CloudAttachmentInfo(bytesToHex(messageDigest.digest()), j10);
                            try {
                                inputStreamBlocking.close();
                                return cloudAttachmentInfo;
                            } catch (IOException e11) {
                                LOG.e("Unable to close file stream", e11);
                                return cloudAttachmentInfo;
                            }
                        }
                        messageDigest.update(bArr, 0, i10);
                        j10 += (long) i10;
                    }
                } catch (IOException e12) {
                    e = e12;
                    LOG.e("Unable to get file hash", e);
                    e.printStackTrace();
                    if (inputStreamBlocking != null) {
                        try {
                            inputStreamBlocking.close();
                        } catch (IOException e13) {
                            LOG.e("Unable to close file stream", e13);
                        }
                    }
                    return null;
                } catch (NoSuchAlgorithmException e14) {
                    e = e14;
                    LOG.e("Unable to get file hash", e);
                    e.printStackTrace();
                    if (inputStreamBlocking != null) {
                        inputStreamBlocking.close();
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                r10 = context;
                if (r10 != 0) {
                    try {
                        r10.close();
                    } catch (IOException e15) {
                        LOG.e("Unable to close file stream", e15);
                    }
                }
                throw th;
            }
        } catch (IOException e16) {
            e = e16;
            inputStreamBlocking = null;
            LOG.e("Unable to get file hash", e);
            e.printStackTrace();
            if (inputStreamBlocking != null) {
                inputStreamBlocking.close();
            }
            return null;
        } catch (NoSuchAlgorithmException e17) {
            e = e17;
            inputStreamBlocking = null;
            LOG.e("Unable to get file hash", e);
            e.printStackTrace();
            if (inputStreamBlocking != null) {
                inputStreamBlocking.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (r10 != 0) {
                r10.close();
            }
            throw th;
        }
    }

    public String getHash() {
        return this.mHash;
    }

    public long getSize() {
        return this.mSize;
    }

    public int getUploadAttemptCount() {
        return this.mUploadAttemptCount;
    }

    public void incrementAttemptCount() {
        this.mUploadAttemptCount++;
    }
}
