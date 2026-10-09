package com.vk.core.deviceid.core;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.vk.core.deviceid.core.RealDeviceIdProvider;
import com.vk.deviceid.contentresolver.DeviceIdContentResolver;
import com.vk.log.L;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.logic.plates.StatementStatusesPlate;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0010\u0010\u000fJ!\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\rH\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u0016R\u0011\u0010#\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/vk/core/deviceid/core/RealDeviceIdProvider;", "", "<init>", "()V", "Lcom/vk/core/deviceid/core/DeviceIdStorage;", "deviceIdStorage", "Lkotlin/Function0;", "", "deviceIdChangedListener", "init", "(Lcom/vk/core/deviceid/core/DeviceIdStorage;Lkotlin/jvm/functions/Function0;)V", "Landroid/content/Context;", "context", "", "getDeviceId", "(Landroid/content/Context;)Ljava/lang/String;", "getNextDeviceId", "Lorg/json/JSONObject;", "config", "requestFromCompanions", "(Landroid/content/Context;Lorg/json/JSONObject;)V", "getNullableDeviceId", "()Ljava/lang/String;", "", "memberId", "getDeviceToken", "(J)Ljava/lang/String;", "deviceToken", "setDeviceToken", "(JLjava/lang/String;)V", "clearDeviceToken", "(J)V", "", "getInitialized", "()Z", "initialized", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RealDeviceIdProvider {

    @NotNull
    public static final RealDeviceIdProvider INSTANCE = new RealDeviceIdProvider();

    @NotNull
    private static volatile String diecivedkvmoca = new String();

    @NotNull
    private static volatile String diecivedkvmocb = new String();
    private static volatile DeviceIdStorage diecivedkvmocc;

    static {
        new Function0() { // from class: z2.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RealDeviceIdProvider.diecivedkvmoca();
            }
        };
    }

    private RealDeviceIdProvider() {
    }

    @JvmStatic
    public static final synchronized void clearDeviceToken(long memberId) {
        try {
            DeviceIdStorage deviceIdStorage = diecivedkvmocc;
            if (deviceIdStorage == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                deviceIdStorage = null;
            }
            deviceIdStorage.clearDeviceToken(memberId);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit diecivedkvmoca() {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit diecivedkvmocb() {
        return Unit.INSTANCE;
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getDeviceId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.diecivedkvmoca(context);
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getDeviceToken(long memberId) {
        DeviceIdStorage deviceIdStorage;
        try {
            deviceIdStorage = diecivedkvmocc;
            if (deviceIdStorage == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                deviceIdStorage = null;
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return deviceIdStorage.getDeviceToken(memberId);
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getNextDeviceId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.diecivedkvmoca(context);
    }

    @JvmStatic
    @Nullable
    public static final synchronized String getNullableDeviceId() {
        if (diecivedkvmocb.length() > 0) {
            return diecivedkvmocb;
        }
        L.d("next_device_id is null or empty: " + diecivedkvmoca);
        DeviceIdStorage deviceIdStorage = diecivedkvmocc;
        if (deviceIdStorage == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
            deviceIdStorage = null;
        }
        diecivedkvmocb = deviceIdStorage.getDeviceId();
        if (TextUtils.isEmpty(diecivedkvmocb)) {
            return null;
        }
        return diecivedkvmocb;
    }

    @JvmStatic
    public static final void init(@NotNull DeviceIdStorage deviceIdStorage, @NotNull Function0<Unit> deviceIdChangedListener) {
        Intrinsics.checkNotNullParameter(deviceIdStorage, "deviceIdStorage");
        Intrinsics.checkNotNullParameter(deviceIdChangedListener, "deviceIdChangedListener");
        diecivedkvmocc = deviceIdStorage;
    }

    public static /* synthetic */ void init$default(DeviceIdStorage deviceIdStorage, Function0 function0, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            function0 = new Function0() { // from class: z2.c
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RealDeviceIdProvider.diecivedkvmocb();
                }
            };
        }
        init(deviceIdStorage, function0);
    }

    @JvmStatic
    public static final synchronized void requestFromCompanions(@NotNull Context context, @Nullable JSONObject config) {
        String strOptString;
        try {
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceIdStorage deviceIdStorage = diecivedkvmocc;
            DeviceIdStorage deviceIdStorage2 = null;
            if (deviceIdStorage == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                deviceIdStorage = null;
            }
            if (!deviceIdStorage.isCompanionDeviceIdRequested()) {
                INSTANCE.getClass();
                if (config != null) {
                    try {
                        strOptString = config.optString("installed_after");
                    } catch (Exception e10) {
                        L.e("couldn't check whether device id should be requested", e10);
                    }
                } else {
                    strOptString = null;
                }
                if (strOptString == null || strOptString.length() == 0) {
                    L.e("No installed_after date supplied!");
                } else {
                    Date date = new SimpleDateFormat(StatementStatusesPlate.dateFormat, Locale.ENGLISH).parse(strOptString);
                    Long lValueOf = date != null ? Long.valueOf(date.getTime()) : null;
                    if (lValueOf == null) {
                        L.e("Cannot parse date: ".concat(strOptString));
                    } else if (context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime > lValueOf.longValue()) {
                        L.d("request device id from companions");
                        String strRequestDeviceIdFromCompanions = DeviceIdContentResolver.INSTANCE.requestDeviceIdFromCompanions(context);
                        if (strRequestDeviceIdFromCompanions != null) {
                            DeviceIdStorage deviceIdStorage3 = diecivedkvmocc;
                            if (deviceIdStorage3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                                deviceIdStorage3 = null;
                            }
                            deviceIdStorage3.setDeviceId(strRequestDeviceIdFromCompanions);
                        }
                        DeviceIdStorage deviceIdStorage4 = diecivedkvmocc;
                        if (deviceIdStorage4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                        } else {
                            deviceIdStorage2 = deviceIdStorage4;
                        }
                        deviceIdStorage2.setCompanionDeviceIdRequested(true);
                    }
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @JvmStatic
    public static final synchronized void setDeviceToken(long memberId, @NotNull String deviceToken) {
        try {
            Intrinsics.checkNotNullParameter(deviceToken, "deviceToken");
            DeviceIdStorage deviceIdStorage = diecivedkvmocc;
            if (deviceIdStorage == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
                deviceIdStorage = null;
            }
            deviceIdStorage.setDeviceToken(memberId, deviceToken);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final boolean getInitialized() {
        return diecivedkvmocc != null;
    }

    private final String diecivedkvmoca(Context context) {
        if (diecivedkvmocb.length() > 0) {
            return diecivedkvmocb;
        }
        L.d("next_device_id is null or empty: " + diecivedkvmoca);
        DeviceIdStorage deviceIdStorage = diecivedkvmocc;
        DeviceIdStorage deviceIdStorage2 = null;
        if (deviceIdStorage == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
            deviceIdStorage = null;
        }
        diecivedkvmocb = deviceIdStorage.getDeviceId();
        if (TextUtils.isEmpty(diecivedkvmocb)) {
            String string = Settings.Secure.getString(context.getContentResolver(), RbParams.Default.URL_PARAM_KEY_ANDROID_ID);
            String deviceId = getDeviceId();
            ArrayList arrayList = new ArrayList();
            if (TextUtils.isEmpty(string)) {
                string = "default";
            }
            arrayList.add(string);
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = "default";
            }
            arrayList.add(deviceId);
            diecivedkvmocb = CollectionsKt.joinToString$default(arrayList, ":", null, null, 0, null, null, 62, null);
            DeviceIdStorage deviceIdStorage3 = diecivedkvmocc;
            if (deviceIdStorage3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceIdStorage");
            } else {
                deviceIdStorage2 = deviceIdStorage3;
            }
            deviceIdStorage2.setDeviceId(diecivedkvmocb);
        }
        L.d("new next_device_id: " + diecivedkvmocb);
        return diecivedkvmocb;
    }

    @NotNull
    public final String getDeviceId() {
        String str = Build.PRODUCT + Build.BOARD + Build.BOOTLOADER + Build.BRAND + Build.DEVICE + Build.DISPLAY + Build.FINGERPRINT + Build.HARDWARE + Build.HOST + Build.ID + Build.MANUFACTURER + Build.MODEL + Build.TAGS;
        Intrinsics.checkNotNullExpressionValue(str, "toString(...)");
        return MD5.convert(str);
    }
}
