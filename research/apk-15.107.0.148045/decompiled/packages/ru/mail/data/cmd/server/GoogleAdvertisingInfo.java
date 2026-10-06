package ru.mail.data.cmd.server;

import android.content.Context;
import android.content.pm.PackageManager;
import com.vk.lists.PaginationHelper;
import java.io.Serializable;
import ru.mail.ads.info.provider.api.di.AdInfoProviderEntryPoint;
import ru.mail.utils.safeutils.Handler;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class GoogleAdvertisingInfo implements Serializable {

    /* JADX INFO: compiled from: ProGuard */
    private static class AdvertisingIdHandler implements Handler<PackageManager, String> {
        private final Context mContext;

        AdvertisingIdHandler(Context context) {
            this.mContext = context;
        }

        @Override // ru.mail.utils.safeutils.Handler
        public String call(PackageManager packageManager) {
            return AdInfoProviderEntryPoint.googleAdvertisingInfoProvider(this.mContext).getAdvertisingId();
        }
    }

    public static /* synthetic */ String a(Context context, PackageManager packageManager) {
        return AdInfoProviderEntryPoint.googleAdvertisingInfoProvider(context).isAdvertisingTrackingEnabled() ? "1" : PaginationHelper.DEFAULT_NEXT_FROM;
    }

    public static String getAdvertisingId(Context context) {
        return (String) PackageManagerUtil.from(context).doWithPackageManager(new AdvertisingIdHandler(context)).onErrorReturn(null).perform();
    }

    public static String isAdsEnabled(final Context context) {
        return (String) PackageManagerUtil.from(context).doWithPackageManager(new Handler() { // from class: ru.mail.data.cmd.server.o
            @Override // ru.mail.utils.safeutils.Handler
            public final Object call(Object obj) {
                return GoogleAdvertisingInfo.a(context, (PackageManager) obj);
            }
        }).onErrorReturn(PaginationHelper.DEFAULT_NEXT_FROM).perform();
    }
}
