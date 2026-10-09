package ru.mail.libverify.platform.firebase;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.platform.core.IInternalFactory;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.core.IPlatformUtils;
import ru.mail.libverify.platform.core.ISmsRetrieverService;
import ru.mail.libverify.platform.core.JwsService;
import ru.mail.libverify.platform.core.PlatformCoreService;
import ru.mail.libverify.platform.core.ServiceType;
import ru.mail.libverify.platform.core.SmsRetrieverResult;
import ru.mail.libverify.platform.firebase.sms.SmsRetrieverReceiver;
import ru.mail.libverify.platform.storage.KeyValueStorage;
import ru.mail.libverify.platform.utils.StringUtils;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 E2\u00020\u0001:\u0001%B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u000f\u0010\u0019J!\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ-\u0010\"\u001a\u00020!2\u0006\u0010\u0005\u001a\u00020\u00042\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001fH\u0016¢\u0006\u0004\b\"\u0010#R\u001a\u0010)\u001a\u00020$8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u001a\u0010/\u001a\u00020*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001a\u00105\u001a\u0002008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001a\u0010;\u001a\u0002068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u001a\u0010A\u001a\u00020<8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0014\u0010D\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010C¨\u0006F"}, d2 = {"Lru/mail/libverify/platform/firebase/FirebaseCoreService;", "Lru/mail/libverify/platform/core/PlatformCoreService;", "<init>", "()V", "Landroid/content/Context;", "context", "Lru/mail/libverify/platform/firebase/b/a;", "getIDv2ProviderService", "(Landroid/content/Context;)Lru/mail/libverify/platform/firebase/b/a;", "Lru/mail/libverify/platform/core/ILog;", "log", "", "setLog", "(Lru/mail/libverify/platform/core/ILog;)V", "Lru/mail/libverify/platform/core/ISmsRetrieverService;", "smsRetrieverService", "setSmsRetrieverService", "(Lru/mail/libverify/platform/core/ISmsRetrieverService;)V", "Lru/mail/libverify/platform/core/IInternalFactory;", "internalFactory", "setInternalFactory", "(Lru/mail/libverify/platform/core/IInternalFactory;)V", "Landroid/os/Bundle;", PushProcessor.DATAKEY_EXTRAS, "Lru/mail/libverify/platform/core/SmsRetrieverResult;", "(Landroid/os/Bundle;)Lru/mail/libverify/platform/core/SmsRetrieverResult;", "Lru/mail/libverify/platform/storage/KeyValueStorage;", "settings", "", "obtainAdvertisingId", "(Landroid/content/Context;Lru/mail/libverify/platform/storage/KeyValueStorage;)Ljava/lang/String;", "Lkotlin/Function1;", "callback", "", "isServiceAvailable", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)Z", "Lru/mail/libverify/platform/core/IPlatformUtils;", "a", "Lru/mail/libverify/platform/core/IPlatformUtils;", "getUtils", "()Lru/mail/libverify/platform/core/IPlatformUtils;", "utils", "Lru/mail/libverify/platform/firebase/b/b;", "b", "Lru/mail/libverify/platform/firebase/b/b;", "getIdProviderService", "()Lru/mail/libverify/platform/firebase/b/b;", "idProviderService", "Lru/mail/libverify/platform/firebase/c/a;", "c", "Lru/mail/libverify/platform/firebase/c/a;", "getSmsRetrieverPlatformManager", "()Lru/mail/libverify/platform/firebase/c/a;", "smsRetrieverPlatformManager", "Lru/mail/libverify/platform/core/JwsService;", "d", "Lru/mail/libverify/platform/core/JwsService;", "getJwsService", "()Lru/mail/libverify/platform/core/JwsService;", "jwsService", "Lru/mail/libverify/platform/core/ServiceType;", "e", "Lru/mail/libverify/platform/core/ServiceType;", "getServiceType", "()Lru/mail/libverify/platform/core/ServiceType;", "serviceType", "getPushSenderId", "()Ljava/lang/String;", "pushSenderId", "Companion", "platform-firebase_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FirebaseCoreService implements PlatformCoreService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion();

    @NotNull
    public static final String SENDER_ID = "Mjk3MTA5MDM2MzQ5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    public final ru.mail.libverify.platform.firebase.d.a f87683a = a.f87694g;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    @NotNull
    public final ru.mail.libverify.platform.firebase.b.b idProviderService = a.f87695h.getValue();

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    @NotNull
    public final ru.mail.libverify.platform.firebase.c.a smsRetrieverPlatformManager = a.f87696i.getValue();

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    @NotNull
    public final JwsService jwsService = a.f87697j.getValue();

    /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
    @NotNull
    public final ServiceType serviceType = ServiceType.Firebase;

    /* JADX INFO: renamed from: ru.mail.libverify.platform.firebase.FirebaseCoreService$a, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    public static final class Companion {
        @NotNull
        public static ILog a() {
            ILog iLog = a.f87688a;
            return iLog == null ? a.f87689b.getValue() : iLog;
        }
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public JwsService getJwsService() {
        return this.jwsService;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public String getPushSenderId() {
        return StringUtils.INSTANCE.decodeBase64(SENDER_ID);
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public ServiceType getServiceType() {
        return this.serviceType;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public IPlatformUtils getUtils() {
        return this.f87683a;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    public boolean isServiceAvailable(@NotNull Context context, @Nullable Function1<? super String, Unit> callback) {
        boolean z10;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(context, "context");
        INSTANCE.getClass();
        ILog iLogA = Companion.a();
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance(...)");
        int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
        String errorString = googleApiAvailability.getErrorString(iIsGooglePlayServicesAvailable);
        Intrinsics.checkNotNullExpressionValue(errorString, "getErrorString(...)");
        iLogA.v("FirebaseHelper", "play service check result: " + errorString);
        if (iIsGooglePlayServicesAvailable == 0 || !(iIsGooglePlayServicesAvailable == 1 || iIsGooglePlayServicesAvailable == 3 || iIsGooglePlayServicesAvailable == 9)) {
            z10 = false;
        } else {
            if (callback != null) {
                callback.invoke(errorString);
            }
            z10 = true;
        }
        return !z10;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @Nullable
    public String obtainAdvertisingId(@NotNull Context context, @NotNull KeyValueStorage settings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "settings");
        AtomicBoolean atomicBoolean = ru.mail.libverify.platform.firebase.a.a.f87702a;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (ru.mail.libverify.platform.firebase.a.a.f87702a.get()) {
            return null;
        }
        INSTANCE.getClass();
        ILog iLogA = Companion.a();
        iLogA.v("AdvertisingHelper", "getAdvertisingId - query android id");
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            Intrinsics.checkNotNullExpressionValue(advertisingIdInfo, "getAdvertisingIdInfo(...)");
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                iLogA.d("AdvertisingHelper", "getAdvertisingId - Google Play AdvertisingId usage blocked by a user");
                return null;
            }
            String id2 = advertisingIdInfo.getId();
            if (id2 != null && id2.length() != 0) {
                String id3 = advertisingIdInfo.getId();
                Intrinsics.checkNotNull(id3);
                settings.putValue("instance_advertising_id", id3).commit();
                return advertisingIdInfo.getId();
            }
            return settings.getValue("instance_advertising_id");
        } catch (GooglePlayServicesNotAvailableException e10) {
            if (ru.mail.libverify.platform.firebase.a.a.f87702a.compareAndSet(false, true)) {
                iLogA.e("AdvertisingHelper", "getAdvertisingId - Google Play services is not available entirely", e10);
            }
        } catch (GooglePlayServicesRepairableException e11) {
            iLogA.e("AdvertisingHelper", "getAdvertisingId - error", e11);
        } catch (IOException e12) {
            iLogA.e("AdvertisingHelper", "getAdvertisingId - Unrecoverable error connecting to Google Play services (e.g., the old version of the service doesn't support getting AdvertisingId)", e12);
        } catch (Exception e13) {
            iLogA.e("AdvertisingHelper", "getAdvertisingId - unknown error", e13);
        }
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    public void setInternalFactory(@Nullable IInternalFactory internalFactory) {
        a.f87690c = internalFactory;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    public void setLog(@Nullable ILog log) {
        a.f87688a = log;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    public void setSmsRetrieverService(@Nullable ISmsRetrieverService smsRetrieverService) {
        a.f87692e = smsRetrieverService;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @Nullable
    public SmsRetrieverResult smsRetrieverService(@NotNull Bundle extras) {
        int statusCode;
        Intrinsics.checkNotNullParameter(extras, "extras");
        int i10 = SmsRetrieverReceiver.f87708a;
        Intrinsics.checkNotNullParameter(extras, "extras");
        Status status = (Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS");
        if (status == null) {
            return null;
        }
        String str = "";
        if (status.getStatusCode() == 0) {
            String str2 = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
            if (str2 == null || StringsKt.isBlank(str2)) {
                statusCode = 13;
            } else {
                statusCode = status.getStatusCode();
                str = str2;
            }
        } else {
            statusCode = status.getStatusCode();
        }
        return new SmsRetrieverResult(statusCode, str);
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public ru.mail.libverify.platform.firebase.b.a getIDv2ProviderService(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ru.mail.libverify.platform.firebase.b.a(context, a.f87688a);
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public ru.mail.libverify.platform.firebase.b.b getIdProviderService() {
        return this.idProviderService;
    }

    @Override // ru.mail.libverify.platform.core.PlatformCoreService
    @NotNull
    public ru.mail.libverify.platform.firebase.c.a getSmsRetrieverPlatformManager() {
        return this.smsRetrieverPlatformManager;
    }
}
