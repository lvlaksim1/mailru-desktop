package com.vk.pushme.util;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
public abstract class MD5 {
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;

    private static final byte[] Encode(byte[] bArr, int[] iArr, int i10) {
        int i11 = 0;
        int i12 = 0;
        while (i11 < i10) {
            int i13 = iArr[i12];
            bArr[i11] = (byte) i13;
            bArr[i11 + 1] = (byte) (i13 >>> 8);
            int i14 = i11 + 3;
            bArr[i11 + 2] = (byte) (i13 >>> 16);
            i11 += 4;
            bArr[i14] = (byte) (i13 >>> 24);
            i12++;
        }
        return bArr;
    }

    private static final int FF(int i10, int i11, int i12, int i13, int i14) {
        int i15 = ((i11 & i10) | (i12 & (~i10))) + i14;
        return ((i15 >>> (32 - i13)) | (i15 << i13)) + i10;
    }

    private static final int GG(int i10, int i11, int i12, int i13, int i14) {
        int i15 = ((i11 & (~i12)) | (i12 & i10)) + i14;
        return ((i15 >>> (32 - i13)) | (i15 << i13)) + i10;
    }

    private static final int HH(int i10, int i11, int i12, int i13, int i14) {
        int i15 = ((i11 ^ i10) ^ i12) + i14;
        return ((i15 >>> (32 - i13)) | (i15 << i13)) + i10;
    }

    private static final int II(int i10, int i11, int i12, int i13, int i14) {
        int i15 = (i11 ^ ((~i12) | i10)) + i14;
        return ((i15 >>> (32 - i13)) | (i15 << i13)) + i10;
    }

    @Nullable
    public static String calcMD5(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return calcMD5Internal(str);
    }

    private static String calcMD5Internal(String str) {
        try {
            return digest_hex(str);
        } catch (NoSuchAlgorithmException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public static final byte[] digest(byte[] bArr, int i10) {
        int[] iArr = new int[16];
        int[] iArr2 = {1732584193, -271733879, -1732584194, 271733878};
        int[] iArr3 = new int[2];
        byte[] bArr2 = new byte[64];
        md5Update(bArr, i10, iArr, iArr2, iArr3, bArr2);
        byte[] bArrEncode = Encode(new byte[16], iArr3, 8);
        byte[] bArr3 = new byte[64];
        bArr3[0] = ByteCompanionObject.MIN_VALUE;
        int i11 = (iArr3[0] >>> 3) & 63;
        md5Update(bArr3, i11 < 56 ? 56 - i11 : 120 - i11, iArr, iArr2, iArr3, bArr2);
        md5Update(bArrEncode, 8, iArr, iArr2, iArr3, bArr2);
        return Encode(bArrEncode, iArr2, 16);
    }

    public static final String digest_hex(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        messageDigest.update(str.getBytes(Charset.defaultCharset()));
        byte[] bArrDigest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b10 : bArrDigest) {
            String hexString = Integer.toHexString(b10 & 255);
            if (hexString.length() == 1) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append('0');
                stringBuffer2.append(hexString);
                hexString = stringBuffer2.toString();
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    private static final void md5Transform(byte[] bArr, int[] iArr, int[] iArr2) {
        int i10 = -1;
        int i11 = 0;
        do {
            int i12 = (bArr[i10 + 1] & 255) | ((bArr[i10 + 2] & 255) << 8) | ((bArr[i10 + 3] & 255) << 16);
            i10 += 4;
            iArr[i11] = i12 | (bArr[i10] << 24);
            i11++;
        } while (i11 < 16);
        int i13 = iArr2[1];
        int i14 = iArr2[2];
        int i15 = iArr2[3];
        int iFF = FF(i13, i14, i15, 7, (iArr2[0] + iArr[0]) - 680876936);
        int iFF2 = FF(iFF, i13, i14, 12, (i15 + iArr[1]) - 389564586);
        int iFF3 = FF(iFF2, iFF, i13, 17, i14 + iArr[2] + 606105819);
        int iFF4 = FF(iFF3, iFF2, iFF, 22, (i13 + iArr[3]) - 1044525330);
        int iFF5 = FF(iFF4, iFF3, iFF2, 7, (iFF + iArr[4]) - 176418897);
        int iFF6 = FF(iFF5, iFF4, iFF3, 12, iFF2 + iArr[5] + 1200080426);
        int iFF7 = FF(iFF6, iFF5, iFF4, 17, (iFF3 + iArr[6]) - 1473231341);
        int iFF8 = FF(iFF7, iFF6, iFF5, 22, (iFF4 + iArr[7]) - 45705983);
        int iFF9 = FF(iFF8, iFF7, iFF6, 7, iFF5 + iArr[8] + 1770035416);
        int iFF10 = FF(iFF9, iFF8, iFF7, 12, (iFF6 + iArr[9]) - 1958414417);
        int iFF11 = FF(iFF10, iFF9, iFF8, 17, (iFF7 + iArr[10]) - 42063);
        int iFF12 = FF(iFF11, iFF10, iFF9, 22, (iFF8 + iArr[11]) - 1990404162);
        int iFF13 = FF(iFF12, iFF11, iFF10, 7, iFF9 + iArr[12] + 1804603682);
        int iFF14 = FF(iFF13, iFF12, iFF11, 12, (iFF10 + iArr[13]) - 40341101);
        int iFF15 = FF(iFF14, iFF13, iFF12, 17, (iFF11 + iArr[14]) - 1502002290);
        int iFF16 = FF(iFF15, iFF14, iFF13, 22, iFF12 + iArr[15] + 1236535329);
        int iGG = GG(iFF16, iFF15, iFF14, 5, (iFF13 + iArr[1]) - 165796510);
        int iGG2 = GG(iGG, iFF16, iFF15, 9, (iFF14 + iArr[6]) - 1069501632);
        int iGG3 = GG(iGG2, iGG, iFF16, 14, iFF15 + iArr[11] + 643717713);
        int iGG4 = GG(iGG3, iGG2, iGG, 20, (iFF16 + iArr[0]) - 373897302);
        int iGG5 = GG(iGG4, iGG3, iGG2, 5, (iGG + iArr[5]) - 701558691);
        int iGG6 = GG(iGG5, iGG4, iGG3, 9, iGG2 + iArr[10] + 38016083);
        int iGG7 = GG(iGG6, iGG5, iGG4, 14, (iGG3 + iArr[15]) - 660478335);
        int iGG8 = GG(iGG7, iGG6, iGG5, 20, (iGG4 + iArr[4]) - 405537848);
        int iGG9 = GG(iGG8, iGG7, iGG6, 5, iGG5 + iArr[9] + 568446438);
        int iGG10 = GG(iGG9, iGG8, iGG7, 9, (iGG6 + iArr[14]) - 1019803690);
        int iGG11 = GG(iGG10, iGG9, iGG8, 14, (iGG7 + iArr[3]) - 187363961);
        int iGG12 = GG(iGG11, iGG10, iGG9, 20, iGG8 + iArr[8] + 1163531501);
        int iGG13 = GG(iGG12, iGG11, iGG10, 5, (iGG9 + iArr[13]) - 1444681467);
        int iGG14 = GG(iGG13, iGG12, iGG11, 9, (iGG10 + iArr[2]) - 51403784);
        int iGG15 = GG(iGG14, iGG13, iGG12, 14, iGG11 + iArr[7] + 1735328473);
        int iGG16 = GG(iGG15, iGG14, iGG13, 20, (iGG12 + iArr[12]) - 1926607734);
        int iHH = HH(iGG16, iGG15, iGG14, 4, (iGG13 + iArr[5]) - 378558);
        int iHH2 = HH(iHH, iGG16, iGG15, 11, (iGG14 + iArr[8]) - 2022574463);
        int iHH3 = HH(iHH2, iHH, iGG16, 16, iGG15 + iArr[11] + 1839030562);
        int iHH4 = HH(iHH3, iHH2, iHH, 23, (iGG16 + iArr[14]) - 35309556);
        int iHH5 = HH(iHH4, iHH3, iHH2, 4, (iHH + iArr[1]) - 1530992060);
        int iHH6 = HH(iHH5, iHH4, iHH3, 11, iHH2 + iArr[4] + 1272893353);
        int iHH7 = HH(iHH6, iHH5, iHH4, 16, (iHH3 + iArr[7]) - 155497632);
        int iHH8 = HH(iHH7, iHH6, iHH5, 23, (iHH4 + iArr[10]) - 1094730640);
        int iHH9 = HH(iHH8, iHH7, iHH6, 4, iHH5 + iArr[13] + 681279174);
        int iHH10 = HH(iHH9, iHH8, iHH7, 11, (iHH6 + iArr[0]) - 358537222);
        int iHH11 = HH(iHH10, iHH9, iHH8, 16, (iHH7 + iArr[3]) - 722521979);
        int iHH12 = HH(iHH11, iHH10, iHH9, 23, iHH8 + iArr[6] + 76029189);
        int iHH13 = HH(iHH12, iHH11, iHH10, 4, (iHH9 + iArr[9]) - 640364487);
        int iHH14 = HH(iHH13, iHH12, iHH11, 11, (iHH10 + iArr[12]) - 421815835);
        int iHH15 = HH(iHH14, iHH13, iHH12, 16, iHH11 + iArr[15] + 530742520);
        int iHH16 = HH(iHH15, iHH14, iHH13, 23, (iHH12 + iArr[2]) - 995338651);
        int iII = II(iHH16, iHH15, iHH14, 6, (iHH13 + iArr[0]) - 198630844);
        int iII2 = II(iII, iHH16, iHH15, 10, iHH14 + iArr[7] + 1126891415);
        int iII3 = II(iII2, iII, iHH16, 15, (iHH15 + iArr[14]) - 1416354905);
        int iII4 = II(iII3, iII2, iII, 21, (iHH16 + iArr[5]) - 57434055);
        int iII5 = II(iII4, iII3, iII2, 6, iII + iArr[12] + 1700485571);
        int iII6 = II(iII5, iII4, iII3, 10, (iII2 + iArr[3]) - 1894986606);
        int iII7 = II(iII6, iII5, iII4, 15, (iII3 + iArr[10]) - 1051523);
        int iII8 = II(iII7, iII6, iII5, 21, (iII4 + iArr[1]) - 2054922799);
        int iII9 = II(iII8, iII7, iII6, 6, iII5 + iArr[8] + 1873313359);
        int iII10 = II(iII9, iII8, iII7, 10, (iII6 + iArr[15]) - 30611744);
        int iII11 = II(iII10, iII9, iII8, 15, (iII7 + iArr[6]) - 1560198380);
        int iII12 = II(iII11, iII10, iII9, 21, iII8 + iArr[13] + 1309151649);
        int iII13 = II(iII12, iII11, iII10, 6, (iII9 + iArr[4]) - 145523070);
        int iII14 = II(iII13, iII12, iII11, 10, (iII10 + iArr[11]) - 1120210379);
        int iII15 = II(iII14, iII13, iII12, 15, iII11 + iArr[2] + 718787259);
        int iII16 = II(iII15, iII14, iII13, 21, (iII12 + iArr[9]) - 343485551);
        iArr2[0] = iArr2[0] + iII13;
        iArr2[1] = iArr2[1] + iII16;
        iArr2[2] = iArr2[2] + iII15;
        iArr2[3] = iArr2[3] + iII14;
    }

    private static final void md5Update(byte[] bArr, int i10, int[] iArr, int[] iArr2, int[] iArr3, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        int i11 = 0;
        int i12 = iArr3[0];
        int i13 = (i12 >>> 3) & 63;
        int i14 = i10 << 3;
        int i15 = i12 + i14;
        iArr3[0] = i15;
        if (i15 < i14) {
            iArr3[1] = iArr3[1] + 1;
        }
        iArr3[1] = iArr3[1] + (i10 >>> 29);
        int i16 = 64 - i13;
        if (i10 >= i16) {
            System.arraycopy(bArr, 0, bArr2, i13, i16);
            md5Transform(bArr2, iArr, iArr2);
            while (i16 + 63 < i10) {
                System.arraycopy(bArr, i16, bArr3, 0, 64);
                md5Transform(bArr3, iArr, iArr2);
                i16 += 64;
            }
            i13 = 0;
            i11 = i16;
        }
        System.arraycopy(bArr, i11, bArr2, i13, i10 - i11);
    }

    public static final byte[] digest(String str) {
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        return digest(bytes, bytes.length);
    }
}
