package com.vk.auth.captcha.impl.utils;

import com.vk.auth.captcha.impl.utils.SoundCaptchaUtilsKt;
import com.vk.superapp.api.core.SuperappApiCore;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0000¨\u0006\u0006"}, d2 = {"loadCaptchaFromUrlWithToken", "Lio/reactivex/rxjava3/core/Observable;", "Ljava/io/FileDescriptor;", "url", "", "token", "impl_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SoundCaptchaUtilsKt {
    @NotNull
    public static final Observable<FileDescriptor> loadCaptchaFromUrlWithToken(@NotNull String url, @Nullable String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        Observable<byte[]> observableLoadFromUrlWithToken = SuperappApiCore.INSTANCE.loadFromUrlWithToken(url, str);
        final Function1 function1 = new Function1() { // from class: z0.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SoundCaptchaUtilsKt.lpmiahctpackvmoca((byte[]) obj);
            }
        };
        Observable map = observableLoadFromUrlWithToken.map(new Function() { // from class: z0.b
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SoundCaptchaUtilsKt.lpmiahctpackvmoca(function1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDescriptor lpmiahctpackvmoca(Function1 function1, Object obj) {
        return (FileDescriptor) function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FileDescriptor lpmiahctpackvmoca(byte[] bArr) throws IOException {
        File fileCreateTempFile = File.createTempFile("sdk_sak_captcha", ".wav");
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
            FileInputStream fileInputStream = new FileInputStream(fileCreateTempFile);
            fileCreateTempFile.delete();
            return fileInputStream.getFD();
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(fileOutputStream, th2);
                throw th3;
            }
        }
    }
}
