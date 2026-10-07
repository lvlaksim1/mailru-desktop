package ru.mail.auth.webview;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.auth.restore.RestoreConstants;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class CustomTabHelper {
    private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    static final String BETA_PACKAGE = "com.chrome.beta";
    static final String DEV_PACKAGE = "com.chrome.dev";
    static final String LOCAL_PACKAGE = "com.google.android.apps.chrome";
    static final String STABLE_PACKAGE = "com.android.chrome";
    private static final String TAG = "CustomTabsHelper";

    /* JADX INFO: compiled from: ProGuard */
    public enum DefaultIntentFactory {
        TELEPHONE("android.intent.action.DIAL", new Uri.Builder().scheme("tel").build()),
        VIEW_HTTP(CommonConstant.ACTION.HWID_SCHEME_URL, new Uri.Builder().scheme(HttpHost.DEFAULT_SCHEME_NAME).authority("www.google.com").build()),
        VIEW_HTTPS(CommonConstant.ACTION.HWID_SCHEME_URL, new Uri.Builder().scheme(RestoreConstants.DEFAULT_URL_SCHEME).authority("www.google.com").build());

        private final String mAction;
        private final Uri mTemplateUri;

        DefaultIntentFactory(String str, Uri uri) {
            this.mAction = str;
            this.mTemplateUri = uri;
        }

        public static DefaultIntentFactory getInstance(Uri uri) {
            for (DefaultIntentFactory defaultIntentFactory : values()) {
                if (defaultIntentFactory.mTemplateUri.getScheme().equalsIgnoreCase(uri.getScheme())) {
                    return defaultIntentFactory;
                }
            }
            return VIEW_HTTPS;
        }

        public Intent makeIntent() {
            return new Intent(this.mAction, this.mTemplateUri);
        }
    }

    private CustomTabHelper() {
    }

    public static String getPackageNameToUse(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(intent, 0);
        String str = resolveInfoResolveActivity != null ? resolveInfoResolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (String) arrayList.get(0);
        }
        if (!TextUtils.isEmpty(str) && !hasSpecializedHandlerIntents(context, intent) && arrayList.contains(str)) {
            return str;
        }
        if (arrayList.contains("com.android.chrome")) {
            return "com.android.chrome";
        }
        if (arrayList.contains(BETA_PACKAGE)) {
            return BETA_PACKAGE;
        }
        if (arrayList.contains(DEV_PACKAGE)) {
            return DEV_PACKAGE;
        }
        if (arrayList.contains(LOCAL_PACKAGE)) {
            return LOCAL_PACKAGE;
        }
        return null;
    }

    public static String[] getPackages() {
        return new String[]{"", "com.android.chrome", BETA_PACKAGE, DEV_PACKAGE, LOCAL_PACKAGE};
    }

    private static boolean hasSpecializedHandlerIntents(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = PackageManagerUtils.queryIntentActivities(context, intent, 64);
        if (listQueryIntentActivities.size() == 0) {
            return false;
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            IntentFilter intentFilter = resolveInfo.filter;
            if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                return true;
            }
        }
        return false;
    }
}
