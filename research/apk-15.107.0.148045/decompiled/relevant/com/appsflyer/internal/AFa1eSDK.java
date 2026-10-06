package com.appsflyer.internal;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AFKeystoreWrapper;
import com.appsflyer.AFLogger;
import com.appsflyer.AFVersionDeclaration;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.PurchaseHandler;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.internal.AFc1bSDK.AnonymousClass4;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.appsflyer.internal.platform_extension.PluginInfo;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.KeyStoreException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.kotlett.divkit.VariableConstants;
import ru.mail.kotlett.runtime.divkit.InterpolatorFields;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
public final class AFa1eSDK extends AppsFlyerLib {
    public static final String AFInAppEventType;
    public static final String AFKeystoreWrapper;

    @VisibleForTesting
    private static String afErrorLog = null;

    @VisibleForTesting
    private static AFa1eSDK afRDLog = null;
    private static int onAttributionFailure = 0;
    private static int onDeepLinking = 1;
    private static int onResponse;
    static AppsFlyerInAppPurchaseValidatorListener valueOf;
    static final String values;
    String AFLogger;
    private Map<Long, String> AFLogger$LogLevel;
    public AFa1cSDK afInfoLog;
    private String getLevel;
    private Application init;
    private boolean onAppOpenAttributionNative;
    private String onAttributionFailureNative;

    @NonNull
    private final AFc1ySDK onConversionDataFail;
    private SharedPreferences onConversionDataSuccess;
    private boolean onInstallConversionFailureNative;
    private Map<String, Object> onResponseErrorNative;
    private AFb1lSDK onResponseNative;
    public volatile AppsFlyerConversionListener AFInAppEventParameterName = null;
    private long afDebugLog = -1;
    private long afWarnLog = -1;
    private long AFVersionDeclaration = TimeUnit.SECONDS.toMillis(5);
    private boolean afErrorLogForExcManagerOnly = false;
    private final AFb1xSDK onInstallConversionDataLoadedNative = new AFb1xSDK();
    private boolean AppsFlyer2dXConversionCallback = false;
    private boolean onDeepLinkingNative = false;
    private final Executor onAppOpenAttribution = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: com.appsflyer.internal.AFa1eSDK$5, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] valueOf;

        static {
            int[] iArr = new int[AppsFlyerProperties.EmailsCryptType.values().length];
            valueOf = iArr;
            try {
                iArr[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                valueOf[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    class AFa1vSDK implements Runnable {
        private final AFa1qSDK AFInAppEventParameterName;

        /* synthetic */ AFa1vSDK(AFa1eSDK aFa1eSDK, AFa1qSDK aFa1qSDK, byte b10) {
            this(aFa1qSDK);
        }

        @Override // java.lang.Runnable
        public final void run() {
            AFa1eSDK.valueOf(AFa1eSDK.this, this.AFInAppEventParameterName);
        }

        private AFa1vSDK(AFa1qSDK aFa1qSDK) {
            this.AFInAppEventParameterName = aFa1qSDK;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    class AFa1wSDK implements AFc1cSDK {
        private AFa1wSDK() {
        }

        @Override // com.appsflyer.internal.AFc1cSDK
        public final void AFInAppEventType(AFd1zSDK<?> aFd1zSDK, AFd1ySDK aFd1ySDK) {
            JSONObject jSONObjectAFKeystoreWrapper;
            AFb1uSDK aFb1uSDKAFInAppEventParameterName;
            if (!(aFd1zSDK instanceof AFd1hSDK)) {
                if (!(aFd1zSDK instanceof AFe1pSDK) || aFd1ySDK == AFd1ySDK.SUCCESS) {
                    return;
                }
                AFe1rSDK aFe1rSDK = new AFe1rSDK(AFa1eSDK.this.AFInAppEventType());
                AFc1bSDK aFc1bSDKAFVersionDeclaration = AFa1eSDK.this.AFInAppEventType().AFVersionDeclaration();
                aFc1bSDKAFVersionDeclaration.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration.new AnonymousClass4(aFe1rSDK));
                return;
            }
            AFd1hSDK aFd1hSDK = (AFd1hSDK) aFd1zSDK;
            boolean z10 = aFd1zSDK instanceof AFd1fSDK;
            if (z10 && values()) {
                AFd1fSDK aFd1fSDK = (AFd1fSDK) aFd1zSDK;
                if (aFd1fSDK.valueOf == AFd1ySDK.SUCCESS || aFd1fSDK.AFInAppEventType == 1) {
                    AFe1pSDK aFe1pSDK = new AFe1pSDK(aFd1fSDK, AFa1eSDK.this.AFInAppEventType().values());
                    AFc1bSDK aFc1bSDKAFVersionDeclaration2 = AFa1eSDK.this.AFInAppEventType().AFVersionDeclaration();
                    aFc1bSDKAFVersionDeclaration2.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration2.new AnonymousClass4(aFe1pSDK));
                }
            }
            if (aFd1ySDK == AFd1ySDK.SUCCESS) {
                AFa1eSDK aFa1eSDK = AFa1eSDK.this;
                aFa1eSDK.values(AFa1eSDK.AFInAppEventType(aFa1eSDK)).AFInAppEventParameterName("sentSuccessfully", "true");
                if (!(aFd1zSDK instanceof AFd1eSDK) && (aFb1uSDKAFInAppEventParameterName = new AFe1ySDK(AFa1eSDK.AFInAppEventType(AFa1eSDK.this)).AFInAppEventParameterName()) != null && aFb1uSDKAFInAppEventParameterName.AFKeystoreWrapper()) {
                    String str = aFb1uSDKAFInAppEventParameterName.valueOf;
                    AFLogger.afDebugLog("Resending Uninstall token to AF servers: ".concat(String.valueOf(str)));
                    AFe1ySDK.valueOf(str);
                }
                ResponseNetwork responseNetwork = ((AFd1oSDK) aFd1hSDK).afErrorLog;
                if (responseNetwork != null && (jSONObjectAFKeystoreWrapper = AFb1vSDK.AFKeystoreWrapper((String) responseNetwork.getBody())) != null) {
                    AFa1eSDK.valueOf(AFa1eSDK.this, jSONObjectAFKeystoreWrapper.optBoolean("send_background", false));
                }
                if (z10) {
                    AFa1eSDK.AFKeystoreWrapper(AFa1eSDK.this, System.currentTimeMillis());
                }
            }
        }

        @Override // com.appsflyer.internal.AFc1cSDK
        public final void values(AFd1zSDK<?> aFd1zSDK) {
            if (aFd1zSDK instanceof AFd1fSDK) {
                AFa1eSDK.this.AFInAppEventType().afDebugLog().AFKeystoreWrapper(((AFd1hSDK) aFd1zSDK).afInfoLog.AFLogger);
            }
        }

        /* synthetic */ AFa1wSDK(AFa1eSDK aFa1eSDK, byte b10) {
            this();
        }

        private boolean values() {
            return AFa1eSDK.this.AFInAppEventParameterName != null;
        }

        @Override // com.appsflyer.internal.AFc1cSDK
        public final void AFInAppEventParameterName(AFd1zSDK<?> aFd1zSDK) {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    class AFa1ySDK implements Runnable {
        private final AFa1qSDK values;

        /* synthetic */ AFa1ySDK(AFa1eSDK aFa1eSDK, AFa1qSDK aFa1qSDK, byte b10) {
            this(aFa1qSDK);
        }

        @Override // java.lang.Runnable
        public final void run() {
            AFd1zSDK aFd1hSDK;
            if (this.values.AFInAppEventParameterName()) {
                AFd1fSDK aFd1fSDK = new AFd1fSDK(this.values, AFa1eSDK.this.AFInAppEventType());
                aFd1fSDK.AFVersionDeclaration = AFa1eSDK.values(AFa1eSDK.this);
                aFd1hSDK = aFd1fSDK;
            } else {
                aFd1hSDK = new AFd1hSDK(this.values, AFa1eSDK.this.AFInAppEventType());
            }
            AFc1bSDK aFc1bSDKAFVersionDeclaration = AFa1eSDK.this.AFInAppEventType().AFVersionDeclaration();
            aFc1bSDKAFVersionDeclaration.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration.new AnonymousClass4(aFd1hSDK));
        }

        private AFa1ySDK(AFa1qSDK aFa1qSDK) {
            this.values = aFa1qSDK;
        }
    }

    static {
        values();
        values = "262";
        AFKeystoreWrapper = "6.12";
        afErrorLog = "https://%sstats.%s/stats";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("6.12");
        sb2.append("/androidevent?buildnumber=6.12.2&app_id=");
        AFInAppEventType = sb2.toString();
        valueOf = null;
        afRDLog = new AFa1eSDK();
        onAttributionFailure = (onDeepLinking + 93) % 128;
    }

    @VisibleForTesting
    public AFa1eSDK() {
        AFVersionDeclaration.init();
        this.onConversionDataFail = new AFc1ySDK();
        AFc1bSDK aFc1bSDKAFVersionDeclaration = AFInAppEventType().AFVersionDeclaration();
        aFc1bSDKAFVersionDeclaration.values.add(new AFa1wSDK(this, (byte) 0));
    }

    static /* synthetic */ void AFInAppEventParameterName(AFa1eSDK aFa1eSDK) {
        onAttributionFailure = (onDeepLinking + 75) % 128;
        aFa1eSDK.afWarnLog();
        int i10 = onDeepLinking + 21;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 65 / 0;
        }
    }

    static /* synthetic */ Application AFInAppEventType(AFa1eSDK aFa1eSDK) {
        int i10 = onAttributionFailure + 33;
        int i11 = i10 % 128;
        onDeepLinking = i11;
        int i12 = i10 % 2;
        Application application = aFa1eSDK.init;
        if (i12 == 0) {
            int i13 = 57 / 0;
        }
        int i14 = i11 + 35;
        onAttributionFailure = i14 % 128;
        if (i14 % 2 == 0) {
            return application;
        }
        throw null;
    }

    static /* synthetic */ long AFKeystoreWrapper(AFa1eSDK aFa1eSDK, long j10) {
        int i10 = onDeepLinking;
        int i11 = i10 + 95;
        onAttributionFailure = i11 % 128;
        int i12 = i11 % 2;
        aFa1eSDK.afWarnLog = j10;
        if (i12 != 0) {
            throw null;
        }
        onAttributionFailure = (i10 + 77) % 128;
        return j10;
    }

    @VisibleForTesting
    private boolean AFLogger() {
        boolean zAFKeystoreWrapper;
        int i10 = onAttributionFailure + 119;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            zAFKeystoreWrapper = AFInAppEventType().AFInAppEventParameterName().AFKeystoreWrapper("AF_PREINSTALL_DISABLED");
            int i11 = 93 / 0;
        } else {
            zAFKeystoreWrapper = AFInAppEventType().AFInAppEventParameterName().AFKeystoreWrapper("AF_PREINSTALL_DISABLED");
        }
        int i12 = onAttributionFailure + 79;
        onDeepLinking = i12 % 128;
        if (i12 % 2 != 0) {
            return zAFKeystoreWrapper;
        }
        throw null;
    }

    private boolean AFLogger$LogLevel() {
        Map<String, Object> map = this.onResponseErrorNative;
        if (map == null) {
            return false;
        }
        onDeepLinking = (onAttributionFailure + 75) % 128;
        if (map.isEmpty()) {
            return false;
        }
        onAttributionFailure = (onDeepLinking + 5) % 128;
        return true;
    }

    @NonNull
    private AFf1pSDK[] AFVersionDeclaration() {
        onDeepLinking = (onAttributionFailure + 73) % 128;
        AFf1pSDK[] aFf1pSDKArrValueOf = AFInAppEventType().AFLogger$LogLevel().valueOf();
        onDeepLinking = (onAttributionFailure + 43) % 128;
        return aFf1pSDKArrValueOf;
    }

    private void afDebugLog() {
        AFf1oSDK aFf1oSDKAFLogger$LogLevel = AFInAppEventType().AFLogger$LogLevel();
        AFf1qSDK aFf1qSDKAfErrorLog = afErrorLog();
        Runnable runnableAFKeystoreWrapper = AFKeystoreWrapper(aFf1qSDKAfErrorLog);
        aFf1oSDKAFLogger$LogLevel.AFKeystoreWrapper(aFf1qSDKAfErrorLog);
        aFf1oSDKAFLogger$LogLevel.AFKeystoreWrapper(new AFf1jSDK(runnableAFKeystoreWrapper));
        aFf1oSDKAFLogger$LogLevel.AFKeystoreWrapper(new AFf1nSDK(runnableAFKeystoreWrapper, AFInAppEventType()));
        aFf1oSDKAFLogger$LogLevel.AFKeystoreWrapper(new AFf1mSDK(runnableAFKeystoreWrapper, AFInAppEventType()));
        if (!AFLogger()) {
            int i10 = onAttributionFailure + 21;
            onDeepLinking = i10 % 128;
            if (i10 % 2 == 0) {
                aFf1oSDKAFLogger$LogLevel.values(this.init, runnableAFKeystoreWrapper, AFInAppEventType());
                throw null;
            }
            aFf1oSDKAFLogger$LogLevel.values(this.init, runnableAFKeystoreWrapper, AFInAppEventType());
            onAttributionFailure = (onDeepLinking + 29) % 128;
        }
        AFf1pSDK[] aFf1pSDKArrValueOf = aFf1oSDKAFLogger$LogLevel.valueOf();
        int length = aFf1pSDKArrValueOf.length;
        int i11 = 0;
        while (i11 < length) {
            int i12 = onDeepLinking + 1;
            onAttributionFailure = i12 % 128;
            if (i12 % 2 != 0) {
                aFf1pSDKArrValueOf[i11].valueOf(this.init);
                i11 += 12;
            } else {
                aFf1pSDKArrValueOf[i11].valueOf(this.init);
                i11++;
            }
            onAttributionFailure = (onDeepLinking + 51) % 128;
        }
    }

    private AFf1qSDK afErrorLog() {
        AFf1qSDK aFf1qSDK = new AFf1qSDK(new Runnable() { // from class: com.appsflyer.internal.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f17160a.afErrorLogForExcManagerOnly();
            }
        }, AFInAppEventType().AFInAppEventType());
        int i10 = onDeepLinking + 45;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            return aFf1qSDK;
        }
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void afErrorLogForExcManagerOnly() {
        ScheduledExecutorService scheduledExecutorServiceValueOf;
        Runnable runnable;
        long j10;
        int i10 = onAttributionFailure + 25;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            scheduledExecutorServiceValueOf = AFInAppEventType().valueOf();
            runnable = new Runnable() { // from class: com.appsflyer.internal.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17167a.getLevel();
                }
            };
            j10 = 1;
        } else {
            scheduledExecutorServiceValueOf = AFInAppEventType().valueOf();
            runnable = new Runnable() { // from class: com.appsflyer.internal.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17167a.getLevel();
                }
            };
            j10 = 0;
        }
        AFInAppEventParameterName(scheduledExecutorServiceValueOf, runnable, j10, TimeUnit.MILLISECONDS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        if (r4.getResources().getIdentifier("appsflyer_backup_rules", "xml", r4.getPackageName()) != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
    
        if (r4.getResources().getIdentifier("appsflyer_backup_rules", "xml", r4.getPackageName()) != 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("appsflyer_backup_rules.xml detected, using AppsFlyer defined backup rules for AppsFlyer SDK data", true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004f, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = (com.appsflyer.internal.AFa1eSDK.onDeepLinking + 23) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
    
        com.appsflyer.AFLogger.AFKeystoreWrapper("'allowBackup' is set to true; appsflyer_backup_rules.xml not detected.\nAppsFlyer shared preferences should be excluded from auto backup by adding: <exclude domain=\"sharedpref\" path=\"appsflyer-data\"/> to the Application's <full-backup-content> rules");
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void afInfoLog(android.content.Context r4) {
        /*
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch: java.lang.Exception -> L5e
            java.lang.String r1 = r4.getPackageName()     // Catch: java.lang.Exception -> L5e
            r2 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r1, r2)     // Catch: java.lang.Exception -> L5e
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch: java.lang.Exception -> L5e
            int r0 = r0.flags     // Catch: java.lang.Exception -> L5e
            r1 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L5d
            int r0 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r0 = r0 + 9
            int r1 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r1
            int r0 = r0 % 2
            java.lang.String r1 = "xml"
            java.lang.String r3 = "appsflyer_backup_rules"
            if (r0 == 0) goto L3b
            android.content.res.Resources r0 = r4.getResources()     // Catch: java.lang.Exception -> L5e
            java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Exception -> L5e
            int r4 = r0.getIdentifier(r3, r1, r4)     // Catch: java.lang.Exception -> L5e
            r0 = 16
            int r0 = r0 / r2
            if (r4 == 0) goto L58
            goto L49
        L39:
            r4 = move-exception
            throw r4
        L3b:
            android.content.res.Resources r0 = r4.getResources()     // Catch: java.lang.Exception -> L5e
            java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Exception -> L5e
            int r4 = r0.getIdentifier(r3, r1, r4)     // Catch: java.lang.Exception -> L5e
            if (r4 == 0) goto L58
        L49:
            java.lang.String r4 = "appsflyer_backup_rules.xml detected, using AppsFlyer defined backup rules for AppsFlyer SDK data"
            r0 = 1
            com.appsflyer.AFLogger.afInfoLog(r4, r0)     // Catch: java.lang.Exception -> L5e
            int r4 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r4 = r4 + 23
            int r4 = r4 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r4
            return
        L58:
            java.lang.String r4 = "'allowBackup' is set to true; appsflyer_backup_rules.xml not detected.\nAppsFlyer shared preferences should be excluded from auto backup by adding: <exclude domain=\"sharedpref\" path=\"appsflyer-data\"/> to the Application's <full-backup-content> rules"
            com.appsflyer.AFLogger.AFKeystoreWrapper(r4)     // Catch: java.lang.Exception -> L5e
        L5d:
            return
        L5e:
            r4 = move-exception
            java.lang.String r0 = "checkBackupRules Exception"
            com.appsflyer.AFLogger.afErrorLogForExcManagerOnly(r0, r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r0 = "checkBackupRules Exception: "
            java.lang.String r4 = r0.concat(r4)
            com.appsflyer.AFLogger.afRDLog(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.afInfoLog(android.content.Context):void");
    }

    private static String afRDLog() {
        int i10 = onAttributionFailure + 121;
        onDeepLinking = i10 % 128;
        if (i10 % 2 != 0) {
            return values("appid");
        }
        values("appid");
        throw null;
    }

    private void afWarnLog() {
        onAttributionFailure = (onDeepLinking + 21) % 128;
        if (AFd1lSDK.afInfoLog()) {
            int i10 = onAttributionFailure + 83;
            onDeepLinking = i10 % 128;
            if (i10 % 2 == 0) {
                throw null;
            }
            return;
        }
        AFc1xSDK aFc1xSDKAFInAppEventType = AFInAppEventType();
        AFc1bSDK aFc1bSDKAFVersionDeclaration = aFc1xSDKAFInAppEventType.AFVersionDeclaration();
        aFc1bSDKAFVersionDeclaration.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration.new AnonymousClass4(new AFd1lSDK(aFc1xSDKAFInAppEventType)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void getLevel() {
        try {
            AFe1lSDK aFe1lSDK = new AFe1lSDK();
            if (AFInAppEventParameterName(aFe1lSDK, values(this.init))) {
                onDeepLinking = (onAttributionFailure + 91) % 128;
                valueOf(aFe1lSDK);
                onDeepLinking = (onAttributionFailure + 1) % 128;
            }
            onAttributionFailure = (onDeepLinking + 53) % 128;
        } catch (Throwable th2) {
            AFLogger.afErrorLog(th2.getMessage(), th2);
        }
    }

    static /* synthetic */ void valueOf(AFa1eSDK aFa1eSDK, AFa1qSDK aFa1qSDK) {
        onAttributionFailure = (onDeepLinking + 55) % 128;
        aFa1eSDK.valueOf(aFa1qSDK);
        onAttributionFailure = (onDeepLinking + 83) % 128;
    }

    static void values() {
        onResponse = 139;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void addPushNotificationDeepLinkPath(String... strArr) {
        int i10 = onDeepLinking + 95;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            AFInAppEventType().AppsFlyer2dXConversionCallback().AFInAppEventType.contains(Arrays.asList(strArr));
            throw null;
        }
        List<String> listAsList = Arrays.asList(strArr);
        List<List<String>> list = AFInAppEventType().AppsFlyer2dXConversionCallback().AFInAppEventType;
        if (list.contains(listAsList)) {
            return;
        }
        list.add(listAsList);
        onAttributionFailure = (onDeepLinking + 23) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void anonymizeUser(boolean z10) {
        onAttributionFailure = (onDeepLinking + 99) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("anonymizeUser", String.valueOf(z10));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, z10);
        int i10 = onDeepLinking + 5;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 34 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void appendParametersToDeepLinkingURL(String str, Map<String, String> map) {
        int i10 = onDeepLinking + 1;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback = AFInAppEventType().AppsFlyer2dXConversionCallback();
            aFb1sSDKAppsFlyer2dXConversionCallback.AFKeystoreWrapper = str;
            aFb1sSDKAppsFlyer2dXConversionCallback.values = map;
        } else {
            AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback2 = AFInAppEventType().AppsFlyer2dXConversionCallback();
            aFb1sSDKAppsFlyer2dXConversionCallback2.AFKeystoreWrapper = str;
            aFb1sSDKAppsFlyer2dXConversionCallback2.values = map;
            int i11 = 14 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void enableFacebookDeferredApplinks(boolean z10) {
        int i10 = onAttributionFailure;
        this.onDeepLinkingNative = z10;
        onDeepLinking = (i10 + 33) % 128;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0039, code lost:
    
        if ((r4 % 2) != 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003b, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003c, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
    
        AFKeystoreWrapper(r4);
        r4 = AFInAppEventType().AFInAppEventParameterName();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
    
        return com.appsflyer.internal.AFb1zSDK.valueOf(r4.valueOf, r4.AFInAppEventType);
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001c, code lost:
    
        if (r4 == null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
    
        if (r4 == null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
    
        r4 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 11;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r4 % 128;
     */
    @Override // com.appsflyer.AppsFlyerLib
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getAppsFlyerUID(@androidx.annotation.NonNull android.content.Context r4) {
        /*
            r3 = this;
            int r0 = com.appsflyer.internal.AFa1eSDK.onAttributionFailure
            int r0 = r0 + 105
            int r1 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onDeepLinking = r1
            int r0 = r0 % 2
            r1 = 0
            java.lang.String r2 = "getAppsFlyerUID"
            if (r0 != 0) goto L1f
            com.appsflyer.internal.AFc1xSDK r0 = r3.AFInAppEventType()
            com.appsflyer.internal.AFb1tSDK r0 = r0.afErrorLogForExcManagerOnly()
            java.lang.String[] r1 = new java.lang.String[r1]
            r0.AFInAppEventParameterName(r2, r1)
            if (r4 != 0) goto L3d
            goto L2e
        L1f:
            com.appsflyer.internal.AFc1xSDK r0 = r3.AFInAppEventType()
            com.appsflyer.internal.AFb1tSDK r0 = r0.afErrorLogForExcManagerOnly()
            java.lang.String[] r1 = new java.lang.String[r1]
            r0.AFInAppEventParameterName(r2, r1)
            if (r4 != 0) goto L3d
        L2e:
            int r4 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r4 = r4 + 11
            int r0 = r4 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r0
            int r4 = r4 % 2
            r0 = 0
            if (r4 != 0) goto L3c
            return r0
        L3c:
            throw r0
        L3d:
            r3.AFKeystoreWrapper(r4)
            com.appsflyer.internal.AFc1xSDK r4 = r3.AFInAppEventType()
            com.appsflyer.internal.AFb1gSDK r4 = r4.AFInAppEventParameterName()
            com.appsflyer.internal.AFb1bSDK r0 = r4.valueOf
            com.appsflyer.internal.AFb1dSDK r4 = r4.AFInAppEventType
            java.lang.String r4 = com.appsflyer.internal.AFb1zSDK.valueOf(r0, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.getAppsFlyerUID(android.content.Context):java.lang.String");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getAttributionId(Context context) {
        try {
            String strAFInAppEventType = new AFa1dSDK(context, AFInAppEventType()).AFInAppEventType();
            onDeepLinking = (onAttributionFailure + 19) % 128;
            return strAFInAppEventType;
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Could not collect facebook attribution id. ", th2);
            return null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getHostName() {
        onAttributionFailure = (onDeepLinking + 63) % 128;
        String strAFInAppEventParameterName = AFInAppEventType().onInstallConversionFailureNative().AFInAppEventParameterName();
        int i10 = onAttributionFailure + 83;
        onDeepLinking = i10 % 128;
        if (i10 % 2 != 0) {
            return strAFInAppEventParameterName;
        }
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getHostPrefix() {
        String strValueOf;
        int i10 = onDeepLinking + 51;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            strValueOf = AFInAppEventType().onInstallConversionFailureNative().valueOf();
            int i11 = 58 / 0;
        } else {
            strValueOf = AFInAppEventType().onInstallConversionFailureNative().valueOf();
        }
        int i12 = onAttributionFailure + 119;
        onDeepLinking = i12 % 128;
        if (i12 % 2 != 0) {
            return strValueOf;
        }
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002f, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r3 = values(r3, "AF_STORE");
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r3 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 55) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0040, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("No out-of-store value set");
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
    
        if (r0 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0025, code lost:
    
        if (r0 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = (com.appsflyer.internal.AFa1eSDK.onDeepLinking + 93) % 128;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getOutOfStore(android.content.Context r3) {
        /*
            r2 = this;
            int r0 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r0 = r0 + 45
            int r1 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r1
            int r0 = r0 % 2
            java.lang.String r1 = "api_store_value"
            if (r0 == 0) goto L1d
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r0.getString(r1)
            r1 = 36
            int r1 = r1 / 0
            if (r0 == 0) goto L30
            goto L27
        L1d:
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r0 = r0.getString(r1)
            if (r0 == 0) goto L30
        L27:
            int r3 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r3 = r3 + 93
            int r3 = r3 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r3
            return r0
        L30:
            java.lang.String r0 = "AF_STORE"
            java.lang.String r3 = r2.values(r3, r0)
            if (r3 == 0) goto L41
            int r0 = com.appsflyer.internal.AFa1eSDK.onAttributionFailure
            int r0 = r0 + 55
            int r0 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onDeepLinking = r0
            return r3
        L41:
            java.lang.String r3 = "No out-of-store value set"
            com.appsflyer.AFLogger.afInfoLog(r3)
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.getOutOfStore(android.content.Context):java.lang.String");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final String getSdkVersion() {
        onAttributionFailure = (onDeepLinking + 91) % 128;
        AFc1xSDK aFc1xSDKAFInAppEventType = AFInAppEventType();
        aFc1xSDKAFInAppEventType.afErrorLogForExcManagerOnly().AFInAppEventParameterName("getSdkVersion", new String[0]);
        aFc1xSDKAFInAppEventType.AFInAppEventParameterName();
        String strValueOf = AFb1gSDK.valueOf();
        int i10 = onAttributionFailure + 1;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            int i11 = 93 / 0;
        }
        return strValueOf;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final AppsFlyerLib init(@NonNull String str, AppsFlyerConversionListener appsFlyerConversionListener, @NonNull Context context) {
        String str2;
        if (this.onAppOpenAttributionNative) {
            return this;
        }
        this.onAppOpenAttributionNative = true;
        AFInAppEventType().afWarnLog().AFInAppEventParameterName = str;
        AFa1aSDK.AFInAppEventType(str);
        if (context != null) {
            this.init = (Application) context.getApplicationContext();
            AFKeystoreWrapper(context);
            AFInAppEventType().afDebugLog().values = System.currentTimeMillis();
            AFInAppEventType().onConversionDataSuccess().values();
            AFInAppEventType().afRDLog().AFInAppEventParameterName(new AFd1bSDK() { // from class: com.appsflyer.internal.c
                @Override // com.appsflyer.internal.AFd1bSDK
                public final void onRemoteConfigUpdateFinished(AFd1dSDK aFd1dSDK) {
                    this.f17161a.AFInAppEventParameterName(aFd1dSDK);
                }
            });
            afDebugLog();
            this.onConversionDataFail.afWarnLog().AFInAppEventParameterName();
            onDeepLinking = (onAttributionFailure + 85) % 128;
        } else {
            AFLogger.afWarnLog("context is null, Google Install Referrer will be not initialized");
        }
        AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
        if (appsFlyerConversionListener == null) {
            int i10 = onDeepLinking + 47;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 != 0) {
                throw null;
            }
            str2 = "null";
        } else {
            onDeepLinking = (onAttributionFailure + 69) % 128;
            str2 = "conversionDataListener";
        }
        aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("init", str, str2);
        AFLogger.AFInAppEventType(String.format("Initializing AppsFlyer SDK: (v%s.%s)", "6.12.2", values));
        this.AFInAppEventParameterName = appsFlyerConversionListener;
        int i11 = onAttributionFailure + 125;
        onDeepLinking = i11 % 128;
        if (i11 % 2 == 0) {
            int i12 = 27 / 0;
        }
        return this;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final boolean isPreInstalledApp(Context context) {
        onAttributionFailure = (onDeepLinking + 79) % 128;
        try {
            if ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0) {
                onAttributionFailure = (onDeepLinking + 1) % 128;
                return true;
            }
            onAttributionFailure = (onDeepLinking + 113) % 128;
            return false;
        } catch (PackageManager.NameNotFoundException e10) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e10);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final boolean isStopped() {
        onAttributionFailure = (onDeepLinking + 49) % 128;
        boolean zAFKeystoreWrapper = AFInAppEventType().afWarnLog().AFKeystoreWrapper();
        onAttributionFailure = (onDeepLinking + 103) % 128;
        return zAFKeystoreWrapper;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logEvent(Context context, String str, Map<String, Object> map) {
        int i10 = onDeepLinking + 15;
        onAttributionFailure = i10 % 128;
        int i11 = i10 % 2;
        logEvent(context, str, map, null);
        if (i11 != 0) {
            int i12 = 68 / 0;
        }
        int i13 = onDeepLinking + 103;
        onAttributionFailure = i13 % 128;
        if (i13 % 2 != 0) {
            int i14 = 62 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logLocation(Context context, double d10, double d11) {
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("logLocation", String.valueOf(d10), String.valueOf(d11));
        HashMap map = new HashMap();
        map.put(AFInAppEventParameterName.LONGITUDE, Double.toString(d11));
        map.put(AFInAppEventParameterName.LATITUDE, Double.toString(d10));
        AFInAppEventType(context, AFInAppEventType.LOCATION_COORDINATES, map);
        onAttributionFailure = (onDeepLinking + 115) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logSession(Context context) {
        onDeepLinking = (onAttributionFailure + 41) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("logSession", new String[0]);
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName();
        AFInAppEventType(context, AFe1nSDK.logSession);
        AFInAppEventType(context, null, null);
        int i10 = onDeepLinking + 47;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void onPause(Context context) {
        onDeepLinking = (onAttributionFailure + 5) % 128;
        AFInAppEventType().onResponseNative().AFInAppEventType(context);
        onAttributionFailure = (onDeepLinking + 43) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0022 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:12:0x0024  */
    /* JADX WARN: Code duplicated, block: B:14:0x0043  */
    /* JADX WARN: Code duplicated, block: B:8:0x0017  */
    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void performOnAppAttribution(@NonNull Context context, @NonNull URI uri) {
        int i10 = onDeepLinking + 121;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 83 / 0;
            if (uri != null) {
                if (!uri.toString().isEmpty()) {
                    if (context == null) {
                        AFInAppEventType().AppsFlyer2dXConversionCallback().valueOf(context, new HashMap(), Uri.parse(uri.toString()));
                        onDeepLinking = (onAttributionFailure + 119) % 128;
                        return;
                    }
                    AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback = AFInAppEventType().AppsFlyer2dXConversionCallback();
                    StringBuilder sb2 = new StringBuilder("Context is \"");
                    sb2.append(context);
                    sb2.append("\"");
                    aFb1sSDKAppsFlyer2dXConversionCallback.AFKeystoreWrapper(sb2.toString(), DeepLinkResult.Error.NETWORK);
                    return;
                }
            }
        } else if (uri != null) {
            if (!uri.toString().isEmpty()) {
                if (context == null) {
                    AFInAppEventType().AppsFlyer2dXConversionCallback().valueOf(context, new HashMap(), Uri.parse(uri.toString()));
                    onDeepLinking = (onAttributionFailure + 119) % 128;
                    return;
                }
                AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback2 = AFInAppEventType().AppsFlyer2dXConversionCallback();
                StringBuilder sb3 = new StringBuilder("Context is \"");
                sb3.append(context);
                sb3.append("\"");
                aFb1sSDKAppsFlyer2dXConversionCallback2.AFKeystoreWrapper(sb3.toString(), DeepLinkResult.Error.NETWORK);
                return;
            }
        }
        AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback3 = AFInAppEventType().AppsFlyer2dXConversionCallback();
        StringBuilder sb4 = new StringBuilder("Link is \"");
        sb4.append(uri);
        sb4.append("\"");
        aFb1sSDKAppsFlyer2dXConversionCallback3.AFKeystoreWrapper(sb4.toString(), DeepLinkResult.Error.NETWORK);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void performOnDeepLinking(@NonNull final Intent intent, @NonNull Context context) {
        if (intent == null) {
            AFInAppEventType().AppsFlyer2dXConversionCallback().AFKeystoreWrapper("performOnDeepLinking was called with null intent", DeepLinkResult.Error.DEVELOPER_ERROR);
            return;
        }
        if (context != null) {
            final Context applicationContext = context.getApplicationContext();
            AFKeystoreWrapper(applicationContext);
            AFInAppEventType().AFInAppEventType().execute(new Runnable() { // from class: com.appsflyer.internal.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f17162a.values(applicationContext, intent);
                }
            });
            return;
        }
        int i10 = onAttributionFailure + 125;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFInAppEventType().AppsFlyer2dXConversionCallback().AFKeystoreWrapper("performOnDeepLinking was called with null context", DeepLinkResult.Error.DEVELOPER_ERROR);
            throw null;
        }
        AFInAppEventType().AppsFlyer2dXConversionCallback().AFKeystoreWrapper("performOnDeepLinking was called with null context", DeepLinkResult.Error.DEVELOPER_ERROR);
        onDeepLinking = (onAttributionFailure + 113) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        onDeepLinking = (onAttributionFailure + 59) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("registerConversionListener", new String[0]);
        AFKeystoreWrapper(appsFlyerConversionListener);
        int i10 = onAttributionFailure + 93;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if (appsFlyerInAppPurchaseValidatorListener != null) {
            valueOf = appsFlyerInAppPurchaseValidatorListener;
            return;
        }
        onDeepLinking = (onAttributionFailure + 113) % 128;
        AFLogger.afDebugLog("registerValidatorListener null listener");
        int i10 = onDeepLinking + 3;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendAdImpression(Context context, Map<String, Object> map) {
        int iAFInAppEventType = AFInAppEventType(values(context));
        HashMap map2 = new HashMap();
        map2.put("ad_network", map);
        map2.put("adimpression_counter", Integer.valueOf(iAFInAppEventType));
        AFKeystoreWrapper(context, map2, new AFe1kSDK());
        onAttributionFailure = (onDeepLinking + 17) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendAdRevenue(Context context, Map<String, Object> map) {
        int iAFInAppEventParameterName = AFInAppEventParameterName(values(context));
        HashMap map2 = new HashMap();
        map2.put("ad_network", map);
        map2.put("adrevenue_counter", Integer.valueOf(iAFInAppEventParameterName));
        AFKeystoreWrapper(context, map2, new AFe1oSDK());
        int i10 = onDeepLinking + 59;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendInAppPurchaseData(Context context, Map<String, Object> map, PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback) {
        onDeepLinking = (onAttributionFailure + 59) % 128;
        AFKeystoreWrapper(context);
        PurchaseHandler purchaseHandlerAfErrorLog = AFInAppEventType().afErrorLog();
        if (purchaseHandlerAfErrorLog.AFInAppEventParameterName(map, purchaseValidationCallback, "purchases")) {
            AFd1qSDK aFd1qSDK = new AFd1qSDK(map, purchaseValidationCallback, purchaseHandlerAfErrorLog.values);
            AFc1bSDK aFc1bSDK = purchaseHandlerAfErrorLog.valueOf;
            aFc1bSDK.AFKeystoreWrapper.execute(aFc1bSDK.new AnonymousClass4(aFd1qSDK));
        }
        int i10 = onDeepLinking + 25;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void sendPurchaseData(Context context, Map<String, Object> map, PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback) {
        onAttributionFailure = (onDeepLinking + 63) % 128;
        AFKeystoreWrapper(context);
        PurchaseHandler purchaseHandlerAfErrorLog = AFInAppEventType().afErrorLog();
        if (purchaseHandlerAfErrorLog.AFInAppEventParameterName(map, purchaseValidationCallback, "subscriptions")) {
            AFd1wSDK aFd1wSDK = new AFd1wSDK(map, purchaseValidationCallback, purchaseHandlerAfErrorLog.values);
            AFc1bSDK aFc1bSDK = purchaseHandlerAfErrorLog.valueOf;
            aFc1bSDK.AFKeystoreWrapper.execute(aFc1bSDK.new AnonymousClass4(aFd1wSDK));
        }
        onAttributionFailure = (onDeepLinking + 35) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:52:0x018a  */
    @Override // com.appsflyer.AppsFlyerLib
    public final void sendPushNotificationData(@Nullable Activity activity) {
        int i10;
        long jLongValue;
        int i11 = onAttributionFailure + 85;
        onDeepLinking = i11 % 128;
        int i12 = 2;
        if (i11 % 2 == 0) {
            throw null;
        }
        if (activity != null && activity.getIntent() != null) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String localClassName = activity.getLocalClassName();
            StringBuilder sb2 = new StringBuilder("activity_intent_");
            sb2.append(activity.getIntent().toString());
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("sendPushNotificationData", localClassName, sb2.toString());
        } else if (activity != null) {
            onDeepLinking = (onAttributionFailure + 3) % 128;
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("sendPushNotificationData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("sendPushNotificationData", "activity_null");
        }
        String strAFInAppEventParameterName = AFInAppEventParameterName(activity);
        this.getLevel = strAFInAppEventParameterName;
        if (strAFInAppEventParameterName != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.AFLogger$LogLevel == null) {
                AFLogger.afInfoLog("pushes: initializing pushes history..");
                this.AFLogger$LogLevel = new ConcurrentHashMap();
                i10 = 2;
                jLongValue = jCurrentTimeMillis;
            } else {
                try {
                    long j10 = AppsFlyerProperties.getInstance().getLong("pushPayloadMaxAging", 1800000L);
                    jLongValue = jCurrentTimeMillis;
                    for (Long l10 : this.AFLogger$LogLevel.keySet()) {
                        try {
                            JSONObject jSONObject = new JSONObject(this.getLevel);
                            i10 = i12;
                            try {
                                JSONObject jSONObject2 = new JSONObject(this.AFLogger$LogLevel.get(l10));
                                if (jSONObject.opt("pid").equals(jSONObject2.opt("pid"))) {
                                    onAttributionFailure = (onDeepLinking + 53) % 128;
                                    if (jSONObject.opt("c").equals(jSONObject2.opt("c"))) {
                                        StringBuilder sb3 = new StringBuilder("PushNotificationMeasurement: A previous payload with same PID and campaign was already acknowledged! (old: ");
                                        sb3.append(jSONObject2);
                                        sb3.append(", new: ");
                                        sb3.append(jSONObject);
                                        sb3.append(")");
                                        AFLogger.afInfoLog(sb3.toString());
                                        this.getLevel = null;
                                        int i13 = onAttributionFailure + 73;
                                        onDeepLinking = i13 % 128;
                                        if (i13 % 2 == 0) {
                                            int i14 = 35 / 0;
                                            return;
                                        }
                                        return;
                                    }
                                }
                                if (jCurrentTimeMillis - l10.longValue() > j10) {
                                    this.AFLogger$LogLevel.remove(l10);
                                }
                                if (l10.longValue() <= jLongValue) {
                                    jLongValue = l10.longValue();
                                    onDeepLinking = (onAttributionFailure + 113) % 128;
                                }
                                i12 = i10;
                            } catch (Throwable th2) {
                                th = th2;
                                StringBuilder sb4 = new StringBuilder("Error while handling push notification measurement: ");
                                sb4.append(th.getClass().getSimpleName());
                                AFLogger.afErrorLog(sb4.toString(), th);
                                if (this.AFLogger$LogLevel.size() == AppsFlyerProperties.getInstance().getInt("pushPayloadHistorySize", i10)) {
                                    StringBuilder sb5 = new StringBuilder("pushes: removing oldest overflowing push (oldest push:");
                                    sb5.append(jLongValue);
                                    sb5.append(")");
                                    AFLogger.afInfoLog(sb5.toString());
                                    this.AFLogger$LogLevel.remove(Long.valueOf(jLongValue));
                                }
                                this.AFLogger$LogLevel.put(Long.valueOf(jCurrentTimeMillis), this.getLevel);
                                start(activity);
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            i10 = i12;
                        }
                    }
                    i10 = i12;
                } catch (Throwable th4) {
                    th = th4;
                    i10 = 2;
                    jLongValue = jCurrentTimeMillis;
                }
            }
            if (this.AFLogger$LogLevel.size() == AppsFlyerProperties.getInstance().getInt("pushPayloadHistorySize", i10)) {
                StringBuilder sb6 = new StringBuilder("pushes: removing oldest overflowing push (oldest push:");
                sb6.append(jLongValue);
                sb6.append(")");
                AFLogger.afInfoLog(sb6.toString());
                this.AFLogger$LogLevel.remove(Long.valueOf(jLongValue));
            }
            this.AFLogger$LogLevel.put(Long.valueOf(jCurrentTimeMillis), this.getLevel);
            start(activity);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAdditionalData(Map<String, Object> map) {
        int i10 = onAttributionFailure + 29;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
        if (map != null) {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setAdditionalData", map.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(map).toString());
        }
        onDeepLinking = (onAttributionFailure + 99) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAndroidIdData(String str) {
        int i10 = onAttributionFailure + 23;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[0] = str;
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setAndroidIdData", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setAndroidIdData", str);
        }
        this.AFLogger = str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setAppId(String str) {
        int i10 = onAttributionFailure + 83;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[1] = str;
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setAppId", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setAppId", str);
        }
        AFInAppEventType("appid", str);
        onAttributionFailure = (onDeepLinking + 53) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0055  */
    @Override // com.appsflyer.AppsFlyerLib
    public final void setAppInviteOneLink(String str) {
        onAttributionFailure = (onDeepLinking + 53) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setAppInviteOneLink", str);
        AFLogger.afInfoLog("setAppInviteOneLink = ".concat(String.valueOf(str)));
        if (str != null) {
            int i10 = onAttributionFailure + 91;
            onDeepLinking = i10 % 128;
            if (i10 % 2 == 0) {
                int i11 = 2 / 0;
                if (!str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
                    AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
                    AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_VERSION);
                    AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
                    onDeepLinking = (onAttributionFailure + 121) % 128;
                }
            } else if (!str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
                AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
                AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_VERSION);
                AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
                onDeepLinking = (onAttributionFailure + 121) % 128;
            }
        } else {
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_VERSION);
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
            onDeepLinking = (onAttributionFailure + 121) % 128;
        }
        AFInAppEventType(AppsFlyerProperties.ONELINK_ID, str);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCollectAndroidID(boolean z10) {
        int i10 = onAttributionFailure + 107;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCollectAndroidID", String.valueOf(z10));
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCollectAndroidID", String.valueOf(z10));
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z10));
        AFInAppEventType(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z10));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCollectIMEI(boolean z10) {
        int i10 = onAttributionFailure + 79;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[0] = String.valueOf(z10);
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setCollectIMEI", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCollectIMEI", String.valueOf(z10));
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z10));
        AFInAppEventType(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z10));
        onAttributionFailure = (onDeepLinking + 59) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setCollectOaid(boolean z10) {
        int i10 = onDeepLinking + 29;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[1] = String.valueOf(z10);
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setCollectOaid", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCollectOaid", String.valueOf(z10));
        }
        AFInAppEventType(AppsFlyerProperties.COLLECT_OAID, Boolean.toString(z10));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCurrencyCode(String str) {
        int i10 = onAttributionFailure + 125;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[1] = str;
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setCurrencyCode", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCurrencyCode", str);
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
        onAttributionFailure = (onDeepLinking + 69) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCustomerIdAndLogSession(String str, @NonNull Context context) {
        if (context != null) {
            onAttributionFailure = (onDeepLinking + 63) % 128;
            if (!AFKeystoreWrapper()) {
                setCustomerUserId(str);
                AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
                return;
            }
            setCustomerUserId(str);
            StringBuilder sb2 = new StringBuilder("CustomerUserId set: ");
            sb2.append(str);
            sb2.append(" - Initializing AppsFlyer Tacking");
            AFLogger.afInfoLog(sb2.toString(), true);
            String referrer = AppsFlyerProperties.getInstance().getReferrer(AFInAppEventType().values());
            AFInAppEventType(context, AFe1nSDK.setCustomerIdAndLogSession);
            String str2 = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
            if (referrer == null) {
                referrer = "";
            }
            String str3 = referrer;
            if (context instanceof Activity) {
                int i10 = onAttributionFailure + 29;
                onDeepLinking = i10 % 128;
                if (i10 % 2 == 0) {
                    ((Activity) context).getIntent();
                    int i11 = 72 / 0;
                } else {
                    ((Activity) context).getIntent();
                }
            }
            AFKeystoreWrapper(context, null, null, str3, null);
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setCustomerUserId(String str) {
        onAttributionFailure = (onDeepLinking + 83) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AFInAppEventType(AppsFlyerProperties.APP_USER_ID, str);
        AFInAppEventType(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false);
        onAttributionFailure = (onDeepLinking + 119) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0019  */
    /* JADX WARN: Code duplicated, block: B:9:0x0016  */
    @Override // com.appsflyer.AppsFlyerLib
    public final void setDebugLog(boolean z10) {
        AFLogger.LogLevel logLevel;
        int i10 = onDeepLinking + 11;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 83 / 0;
            if (z10) {
                logLevel = AFLogger.LogLevel.DEBUG;
            } else {
                logLevel = AFLogger.LogLevel.NONE;
            }
        } else if (!z10) {
            logLevel = AFLogger.LogLevel.NONE;
        } else {
            logLevel = AFLogger.LogLevel.DEBUG;
        }
        setLogLevel(logLevel);
        onAttributionFailure = (onDeepLinking + 103) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setDisableAdvertisingIdentifiers(boolean z10) {
        boolean z11;
        int i10 = onAttributionFailure + 15;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFLogger.afDebugLog("setDisableAdvertisingIdentifiers: ".concat(String.valueOf(z10)));
            throw null;
        }
        AFLogger.afDebugLog("setDisableAdvertisingIdentifiers: ".concat(String.valueOf(z10)));
        if (z10) {
            onAttributionFailure = (onDeepLinking + 35) % 128;
            z11 = false;
        } else {
            onAttributionFailure = (onDeepLinking + 29) % 128;
            z11 = true;
        }
        AFa1cSDK.valueOf = Boolean.valueOf(z11);
        AppsFlyerProperties.getInstance().remove("advertiserIdEnabled");
        AppsFlyerProperties.getInstance().remove("advertiserId");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setDisableNetworkData(boolean z10) {
        onDeepLinking = (onAttributionFailure + 15) % 128;
        AFLogger.afDebugLog("setDisableNetworkData: ".concat(String.valueOf(z10)));
        AFInAppEventType(AppsFlyerProperties.DISABLE_NETWORK_DATA, z10);
        int i10 = onDeepLinking + 93;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 97 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setExtension(String str) {
        onAttributionFailure = (onDeepLinking + 81) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
        int i10 = onAttributionFailure + 65;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setHost(@Nullable String str, @NonNull String str2) {
        String strTrim;
        if (!(!AFb1uSDK.AFInAppEventType(str2))) {
            AFLogger.afWarnLog("hostname was empty or null - call for setHost is skipped");
            return;
        }
        onDeepLinking = (onAttributionFailure + 29) % 128;
        if (str != null) {
            strTrim = str.trim();
            onAttributionFailure = (onDeepLinking + 95) % 128;
        } else {
            strTrim = "";
        }
        AFc1dSDK.valueOf(new AFc1fSDK(strTrim, str2.trim()));
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setImeiData(String str) {
        int i10 = onDeepLinking + 27;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
            String[] strArr = new String[0];
            strArr[1] = str;
            aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("setImeiData", strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setImeiData", str);
        }
        AFInAppEventType().afWarnLog().values = str;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setIsUpdate(boolean z10) {
        onDeepLinking = (onAttributionFailure + 119) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setIsUpdate", String.valueOf(z10));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z10);
        int i10 = onAttributionFailure + 43;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setLogLevel(@NonNull AFLogger.LogLevel logLevel) {
        boolean z10 = false;
        if (logLevel.getLevel() > AFLogger.LogLevel.NONE.getLevel()) {
            int i10 = onDeepLinking + 85;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 == 0) {
                z10 = true;
            }
        }
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("log", String.valueOf(z10));
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getLevel());
        onAttributionFailure = (onDeepLinking + 95) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setMinTimeBetweenSessions(int i10) {
        int i11 = onAttributionFailure + 77;
        onDeepLinking = i11 % 128;
        if (i11 % 2 != 0) {
            this.AFVersionDeclaration = TimeUnit.SECONDS.toMillis(i10);
        } else {
            this.AFVersionDeclaration = TimeUnit.SECONDS.toMillis(i10);
            int i12 = 39 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setOaidData(String str) {
        onAttributionFailure = (onDeepLinking + 123) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setOaidData", str);
        AFa1cSDK.values = str;
        int i10 = onAttributionFailure + 95;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setOneLinkCustomDomain(String... strArr) {
        String str;
        int i10 = onAttributionFailure + 77;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            Object[] objArr = new Object[0];
            objArr[1] = Arrays.toString(strArr);
            str = String.format("setOneLinkCustomDomain %s", objArr);
        } else {
            str = String.format("setOneLinkCustomDomain %s", Arrays.toString(strArr));
        }
        AFLogger.afDebugLog(str);
        AFInAppEventType().AppsFlyer2dXConversionCallback().afDebugLog = strArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0035, code lost:
    
        com.appsflyer.AFLogger.AFKeystoreWrapper("Cannot set setOutOfStore with null");
        r3 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 91;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r3 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0044, code lost:
    
        if ((r3 % 2) != 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
    
        if (r3 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r3 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0015, code lost:
    
        r3 = r3.toLowerCase(java.util.Locale.getDefault());
        com.appsflyer.AppsFlyerProperties.getInstance().set(com.appsflyer.AppsFlyerProperties.AF_STORE_FROM_API, r3);
        com.appsflyer.AFLogger.afInfoLog("Store API set with value: ".concat(java.lang.String.valueOf(r3)), true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0034, code lost:
    
        return;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setOutOfStore(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r0 = r0 + 27
            int r1 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r1
            int r0 = r0 % 2
            if (r0 == 0) goto L13
            r0 = 65
            int r0 = r0 / 0
            if (r3 == 0) goto L35
            goto L15
        L13:
            if (r3 == 0) goto L35
        L15:
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r3 = r3.toLowerCase(r0)
            com.appsflyer.AppsFlyerProperties r0 = com.appsflyer.AppsFlyerProperties.getInstance()
            java.lang.String r1 = "api_store_value"
            r0.set(r1, r3)
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r0 = "Store API set with value: "
            java.lang.String r3 = r0.concat(r3)
            r0 = 1
            com.appsflyer.AFLogger.afInfoLog(r3, r0)
            return
        L35:
            java.lang.String r3 = "Cannot set setOutOfStore with null"
            com.appsflyer.AFLogger.AFKeystoreWrapper(r3)
            int r3 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r3 = r3 + 91
            int r0 = r3 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r0
            int r3 = r3 % 2
            if (r3 != 0) goto L47
            return
        L47:
            r3 = 0
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.setOutOfStore(java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
    
        if (r4.isEmpty() != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
    
        if ((!r4.isEmpty()) != true) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r5 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        r1 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 27;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r1 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
    
        if ((r1 % 2) == 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
    
        r2 = 40 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0047, code lost:
    
        if (r5.isEmpty() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
    
        if (r5.isEmpty() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
    
        r1 = new java.lang.StringBuilder("Setting partner data for ");
        r1.append(r4);
        r1.append(": ");
        r1.append(r5);
        com.appsflyer.AFLogger.afDebugLog(r1.toString());
        r1 = new org.json.JSONObject(r5).toString().length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
    
        if (r1 <= 1000) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007b, code lost:
    
        com.appsflyer.AFLogger.afWarnLog("Partner data 1000 characters limit exceeded");
        r5 = new java.util.HashMap();
        r5.put("error", "limit exceeded: ".concat(java.lang.String.valueOf(r1)));
        r0.AFInAppEventParameterName.put(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0099, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009a, code lost:
    
        r0.values.put(r4, r5);
        r0.AFInAppEventParameterName.remove(r4);
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 77) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ac, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b3, code lost:
    
        if (r0.values.remove(r4) != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b5, code lost:
    
        r4 = "Partner data is missing or `null`";
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b8, code lost:
    
        r4 = "Cleared partner data for ".concat(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00be, code lost:
    
        com.appsflyer.AFLogger.afWarnLog(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c1, code lost:
    
        return;
     */
    @Override // com.appsflyer.AppsFlyerLib
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setPartnerData(@androidx.annotation.NonNull java.lang.String r4, java.util.Map<java.lang.String, java.lang.Object> r5) {
        /*
            r3 = this;
            com.appsflyer.internal.AFb1lSDK r0 = r3.onResponseNative
            if (r0 != 0) goto Lb
            com.appsflyer.internal.AFb1lSDK r0 = new com.appsflyer.internal.AFb1lSDK
            r0.<init>()
            r3.onResponseNative = r0
        Lb:
            com.appsflyer.internal.AFb1lSDK r0 = r3.onResponseNative
            if (r4 == 0) goto Lc2
            int r1 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r1 = r1 + 47
            int r2 = r1 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r2
            int r1 = r1 % 2
            if (r1 == 0) goto L27
            boolean r1 = r4.isEmpty()
            r2 = 92
            int r2 = r2 / 0
            if (r1 == 0) goto L31
            goto Lc2
        L27:
            boolean r1 = r4.isEmpty()
            r2 = 1
            r1 = r1 ^ r2
            if (r1 == r2) goto L31
            goto Lc2
        L31:
            if (r5 == 0) goto Lad
            int r1 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r1 = r1 + 27
            int r2 = r1 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r2
            int r1 = r1 % 2
            if (r1 == 0) goto L4a
            boolean r1 = r5.isEmpty()
            r2 = 40
            int r2 = r2 / 0
            if (r1 == 0) goto L51
            goto Lad
        L4a:
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L51
            goto Lad
        L51:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Setting partner data for "
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r2 = ": "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            com.appsflyer.AFLogger.afDebugLog(r1)
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>(r5)
            java.lang.String r1 = r1.toString()
            int r1 = r1.length()
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r2) goto L9a
            java.lang.String r5 = "Partner data 1000 characters limit exceeded"
            com.appsflyer.AFLogger.afWarnLog(r5)
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            java.lang.String r2 = "limit exceeded: "
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r1 = r2.concat(r1)
            java.lang.String r2 = "error"
            r5.put(r2, r1)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.AFInAppEventParameterName
            r0.put(r4, r5)
            return
        L9a:
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.values
            r1.put(r4, r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r0.AFInAppEventParameterName
            r5.remove(r4)
            int r4 = com.appsflyer.internal.AFa1eSDK.onAttributionFailure
            int r4 = r4 + 77
            int r4 = r4 % 128
            com.appsflyer.internal.AFa1eSDK.onDeepLinking = r4
            return
        Lad:
            java.util.Map<java.lang.String, java.lang.Object> r5 = r0.values
            java.lang.Object r5 = r5.remove(r4)
            if (r5 != 0) goto Lb8
            java.lang.String r4 = "Partner data is missing or `null`"
            goto Lbe
        Lb8:
            java.lang.String r5 = "Cleared partner data for "
            java.lang.String r4 = r5.concat(r4)
        Lbe:
            com.appsflyer.AFLogger.afWarnLog(r4)
            return
        Lc2:
            java.lang.String r4 = "Partner ID is missing or `null`"
            com.appsflyer.AFLogger.afWarnLog(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.setPartnerData(java.lang.String, java.util.Map):void");
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setPhoneNumber(String str) {
        int i10 = onAttributionFailure + 35;
        onDeepLinking = i10 % 128;
        int i11 = i10 % 2;
        this.onAttributionFailureNative = AFc1nSDK.AFInAppEventType(str);
        if (i11 == 0) {
            throw null;
        }
        onAttributionFailure = (onDeepLinking + 77) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setPluginInfo(@NonNull PluginInfo pluginInfo) {
        onDeepLinking = (onAttributionFailure + 93) % 128;
        Objects.requireNonNull(pluginInfo);
        AFInAppEventType().onInstallConversionDataLoadedNative().AFInAppEventParameterName(pluginInfo);
        onDeepLinking = (onAttributionFailure + 25) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0027 A[Catch: JSONException -> 0x0023, TRY_ENTER, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0023, blocks: (B:5:0x0017, B:10:0x0027, B:14:0x003c, B:15:0x0040), top: B:28:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x002e  */
    /* JADX WARN: Code duplicated, block: B:14:0x003c A[Catch: JSONException -> 0x0023, TRY_ENTER, TryCatch #0 {JSONException -> 0x0023, blocks: (B:5:0x0017, B:10:0x0027, B:14:0x003c, B:15:0x0040), top: B:28:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0040 A[Catch: JSONException -> 0x0023, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0023, blocks: (B:5:0x0017, B:10:0x0027, B:14:0x003c, B:15:0x0040), top: B:28:0x0017 }] */
    @Override // com.appsflyer.AppsFlyerLib
    public final void setPreinstallAttribution(String str, String str2, String str3) {
        int i10;
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            onDeepLinking = (onAttributionFailure + 93) % 128;
            try {
                jSONObject.put("pid", str);
                onDeepLinking = (onAttributionFailure + 17) % 128;
                if (str2 != null) {
                    jSONObject.put("c", str2);
                }
                if (str3 != null) {
                    i10 = onDeepLinking + 41;
                    onAttributionFailure = i10 % 128;
                    if (i10 % 2 == 0) {
                        jSONObject.put("af_siteid", str3);
                        throw null;
                    }
                    jSONObject.put("af_siteid", str3);
                }
            } catch (JSONException e10) {
                AFLogger.afErrorLog(e10.getMessage(), e10);
            }
        } else {
            if (str2 != null) {
                jSONObject.put("c", str2);
            }
            if (str3 != null) {
                i10 = onDeepLinking + 41;
                onAttributionFailure = i10 % 128;
                if (i10 % 2 == 0) {
                    jSONObject.put("af_siteid", str3);
                    throw null;
                }
                jSONObject.put("af_siteid", str3);
            }
        }
        if (!jSONObject.has("pid")) {
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
            return;
        }
        int i11 = onDeepLinking + 117;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 == 0) {
            AFInAppEventType("preInstallName", jSONObject.toString());
        } else {
            AFInAppEventType("preInstallName", jSONObject.toString());
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setResolveDeepLinkURLs(String... strArr) {
        onDeepLinking = (onAttributionFailure + 87) % 128;
        AFLogger.afDebugLog(String.format("setResolveDeepLinkURLs %s", Arrays.toString(strArr)));
        AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback = AFInAppEventType().AppsFlyer2dXConversionCallback();
        aFb1sSDKAppsFlyer2dXConversionCallback.AFLogger.clear();
        aFb1sSDKAppsFlyer2dXConversionCallback.AFLogger.addAll(Arrays.asList(strArr));
        onAttributionFailure = (onDeepLinking + 65) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setSharingFilter(@NonNull String... strArr) {
        onAttributionFailure = (onDeepLinking + 69) % 128;
        setSharingFilterForPartners(strArr);
        onAttributionFailure = (onDeepLinking + 63) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    @Deprecated
    public final void setSharingFilterForAllPartners() {
        int i10 = onDeepLinking + 47;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            setSharingFilterForPartners("all");
            return;
        }
        String[] strArr = new String[0];
        strArr[1] = "all";
        setSharingFilterForPartners(strArr);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setSharingFilterForPartners(String... strArr) {
        this.afInfoLog = new AFa1cSDK(strArr);
        onDeepLinking = (onAttributionFailure + 95) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setUserEmails(String... strArr) {
        int i10 = onAttributionFailure + 43;
        onDeepLinking = i10 % 128;
        if (i10 % 2 != 0) {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setUserEmails", strArr);
            setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
        } else {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setUserEmails", strArr);
            setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
            int i11 = 7 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(@NonNull Context context) {
        onAttributionFailure = (onDeepLinking + 61) % 128;
        start(context, null);
        int i10 = onAttributionFailure + 49;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            throw null;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void stop(boolean z10, Context context) {
        onDeepLinking = (onAttributionFailure + 121) % 128;
        AFKeystoreWrapper(context);
        final AFc1xSDK aFc1xSDKAFInAppEventType = AFInAppEventType();
        aFc1xSDKAFInAppEventType.afWarnLog().AFInAppEventType = z10;
        aFc1xSDKAFInAppEventType.AFInAppEventType().submit(new Runnable() { // from class: com.appsflyer.internal.a
            @Override // java.lang.Runnable
            public final void run() {
                AFa1eSDK.valueOf(aFc1xSDKAFInAppEventType);
            }
        });
        if (z10) {
            onAttributionFailure = (onDeepLinking + 69) % 128;
            aFc1xSDKAFInAppEventType.values().values("is_stop_tracking_used", true);
        }
        int i10 = onAttributionFailure + 37;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            int i11 = 93 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void subscribeForDeepLink(@NonNull DeepLinkListener deepLinkListener) {
        int i10 = onDeepLinking + 99;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            subscribeForDeepLink(deepLinkListener, TimeUnit.SECONDS.toMillis(3L));
        } else {
            subscribeForDeepLink(deepLinkListener, TimeUnit.SECONDS.toMillis(3L));
            int i11 = 39 / 0;
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void unregisterConversionListener() {
        onDeepLinking = (onAttributionFailure + 55) % 128;
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("unregisterConversionListener", new String[0]);
        this.AFInAppEventParameterName = null;
        onAttributionFailure = (onDeepLinking + 123) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void updateServerUninstallToken(Context context, String str) {
        AFKeystoreWrapper(context);
        AFe1ySDK aFe1ySDK = new AFe1ySDK(context);
        if (str == null || str.trim().isEmpty()) {
            AFLogger.afWarnLog("[register] Firebase Token is either empty or null and was not registered.");
            return;
        }
        AFLogger.afInfoLog("[register] Firebase Refreshed Token = ".concat(str));
        AFb1uSDK aFb1uSDKAFInAppEventParameterName = aFe1ySDK.AFInAppEventParameterName();
        if (aFb1uSDKAFInAppEventParameterName == null || !str.equals(aFb1uSDKAFInAppEventParameterName.valueOf)) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z10 = aFb1uSDKAFInAppEventParameterName == null || jCurrentTimeMillis - aFb1uSDKAFInAppEventParameterName.AFKeystoreWrapper > TimeUnit.SECONDS.toMillis(2L);
            AFb1uSDK aFb1uSDK = new AFb1uSDK(str, jCurrentTimeMillis, !z10);
            aFe1ySDK.values.AFInAppEventParameterName("afUninstallToken", aFb1uSDK.valueOf);
            aFe1ySDK.values.AFInAppEventParameterName("afUninstallToken_received_time", aFb1uSDK.AFKeystoreWrapper);
            aFe1ySDK.values.values("afUninstallToken_queued", aFb1uSDK.AFKeystoreWrapper());
            if (z10) {
                AFe1ySDK.valueOf(str);
            }
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void validateAndLogInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("validateAndTrackInAppPurchase", str, str2, str3, str4, str5, map == null ? "" : map.toString());
        if (!isStopped()) {
            StringBuilder sb2 = new StringBuilder("Validate in app called with parameters: ");
            sb2.append(str3);
            sb2.append(StringUtils.SPACE);
            sb2.append(str4);
            sb2.append(StringUtils.SPACE);
            sb2.append(str5);
            AFLogger.afInfoLog(sb2.toString());
        }
        if (str != null && str4 != null && str2 != null && str5 != null && str3 != null) {
            new Thread(new AFa1gSDK(context.getApplicationContext(), AFInAppEventType().afWarnLog().AFInAppEventParameterName, str, str2, str3, str4, str5, map, context instanceof Activity ? ((Activity) context).getIntent() : null)).start();
            return;
        }
        AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = valueOf;
        if (appsFlyerInAppPurchaseValidatorListener != null) {
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure("Please provide purchase parameters");
        }
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void waitForCustomerUserId(boolean z10) {
        onAttributionFailure = (onDeepLinking + 77) % 128;
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z10)), true);
        AFInAppEventType(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z10);
        int i10 = onDeepLinking + 45;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void AFInAppEventParameterName(AFd1dSDK aFd1dSDK) {
        onDeepLinking = (onAttributionFailure + 111) % 128;
        if (aFd1dSDK == AFd1dSDK.SUCCESS) {
            int i10 = onDeepLinking + 75;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 != 0) {
                AFInAppEventType().onConversionDataSuccess().AFInAppEventType();
                throw null;
            }
            AFInAppEventType().onConversionDataSuccess().AFInAppEventType();
        }
        int i11 = onDeepLinking + 81;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 != 0) {
            throw null;
        }
    }

    private long AFLogger(Context context) {
        onDeepLinking = (onAttributionFailure + 115) % 128;
        AFb1dSDK aFb1dSDKValues = values(context);
        long jValueOf = aFb1dSDKValues.valueOf("AppsFlyerTimePassedSincePrevLaunch", 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        aFb1dSDKValues.AFInAppEventParameterName("AppsFlyerTimePassedSincePrevLaunch", jCurrentTimeMillis);
        if (jValueOf <= 0) {
            int i10 = onDeepLinking + 65;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 == 0) {
                return -1L;
            }
            throw null;
        }
        int i11 = onAttributionFailure;
        int i12 = i11 + 83;
        onDeepLinking = i12 % 128;
        long j10 = (i12 % 2 == 0 ? jCurrentTimeMillis / jValueOf : jCurrentTimeMillis - jValueOf) / 1000;
        int i13 = i11 + 15;
        onDeepLinking = i13 % 128;
        if (i13 % 2 != 0) {
            return j10;
        }
        throw null;
    }

    static /* synthetic */ boolean valueOf(AFa1eSDK aFa1eSDK, boolean z10) {
        int i10 = onDeepLinking;
        onAttributionFailure = (i10 + 119) % 128;
        aFa1eSDK.AppsFlyer2dXConversionCallback = z10;
        int i11 = i10 + 21;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 == 0) {
            return z10;
        }
        throw null;
    }

    static /* synthetic */ Map values(AFa1eSDK aFa1eSDK) {
        int i10 = (onAttributionFailure + 125) % 128;
        onDeepLinking = i10;
        Map<String, Object> map = aFa1eSDK.onResponseErrorNative;
        onAttributionFailure = (i10 + 47) % 128;
        return map;
    }

    public final AFc1xSDK AFInAppEventType() {
        int i10 = onDeepLinking;
        AFc1ySDK aFc1ySDK = this.onConversionDataFail;
        int i11 = i10 + 5;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 != 0) {
            int i12 = 48 / 0;
        }
        return aFc1ySDK;
    }

    public final boolean AFKeystoreWrapper() {
        int i10 = onDeepLinking + 93;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0 ? valueOf(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) : valueOf(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
            int i11 = onAttributionFailure + 67;
            onDeepLinking = i11 % 128;
            if (i11 % 2 == 0) {
                int i12 = 46 / 0;
                if (AFInAppEventParameterName() == null) {
                    return true;
                }
            } else if (AFInAppEventParameterName() == null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void logEvent(@NonNull Context context, String str, Map<String, Object> map, AppsFlyerRequestListener appsFlyerRequestListener) {
        HashMap map2 = map == null ? null : new HashMap(map);
        AFKeystoreWrapper(context);
        AFe1iSDK aFe1iSDK = new AFe1iSDK();
        aFe1iSDK.afDebugLog = str;
        aFe1iSDK.AFInAppEventParameterName = appsFlyerRequestListener;
        if (map2 != null && map2.containsKey(AFInAppEventParameterName.TOUCH_OBJ)) {
            HashMap map3 = new HashMap();
            Object obj = map2.get(AFInAppEventParameterName.TOUCH_OBJ);
            if (obj instanceof MotionEvent) {
                MotionEvent motionEvent = (MotionEvent) obj;
                HashMap map4 = new HashMap();
                map4.put(InterpolatorFields.Path.X, Float.valueOf(motionEvent.getX()));
                map4.put(InterpolatorFields.Path.Y, Float.valueOf(motionEvent.getY()));
                map3.put("loc", map4);
                map3.put("pf", Float.valueOf(motionEvent.getPressure()));
                map3.put("rad", Float.valueOf(motionEvent.getTouchMajor() / 2.0f));
            } else {
                map3.put("error", "Parsing failed due to invalid input in 'af_touch_obj'.");
                AFLogger.AFKeystoreWrapper("Parsing failed due to invalid input in 'af_touch_obj'.");
            }
            Map<String, ?> mapSingletonMap = Collections.singletonMap("tch_data", map3);
            map2.remove(AFInAppEventParameterName.TOUCH_OBJ);
            aFe1iSDK.values(mapSingletonMap);
        }
        aFe1iSDK.values = map2;
        AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFInAppEventType().afErrorLogForExcManagerOnly();
        Map map5 = aFe1iSDK.values;
        if (map5 == null) {
            map5 = new HashMap();
        }
        aFb1tSDKAfErrorLogForExcManagerOnly.AFInAppEventParameterName("logEvent", str, new JSONObject(map5).toString());
        if (str == null) {
            AFInAppEventType(context, AFe1nSDK.logEvent);
        }
        values(aFe1iSDK, context instanceof Activity ? (Activity) context : null);
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(@NonNull Context context, String str) {
        onDeepLinking = (onAttributionFailure + 39) % 128;
        start(context, str, null);
        onAttributionFailure = (onDeepLinking + 37) % 128;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void subscribeForDeepLink(@NonNull DeepLinkListener deepLinkListener, long j10) {
        onAttributionFailure = (onDeepLinking + 83) % 128;
        AFInAppEventType().AppsFlyer2dXConversionCallback().AFInAppEventParameterName = deepLinkListener;
        AFInAppEventType().AppsFlyer2dXConversionCallback().afInfoLog = j10;
        int i10 = onAttributionFailure + 45;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            int i11 = 87 / 0;
        }
    }

    private Runnable AFKeystoreWrapper(final AFf1qSDK aFf1qSDK) {
        onDeepLinking = (onAttributionFailure + 81) % 128;
        Runnable runnable = new Runnable() { // from class: com.appsflyer.internal.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f17165a.AFInAppEventType(aFf1qSDK);
            }
        };
        int i10 = onAttributionFailure + 13;
        onDeepLinking = i10 % 128;
        if (i10 % 2 != 0) {
            return runnable;
        }
        throw null;
    }

    private void afErrorLog(Context context) {
        int i10;
        if (AFa1fSDK.valueOf()) {
            AFLogger.afRDLog("OPPO device found");
            i10 = 23;
        } else {
            i10 = 18;
        }
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= i10 && !valueOf(AppsFlyerProperties.DISABLE_KEYSTORE, true)) {
            StringBuilder sb2 = new StringBuilder("OS SDK is=");
            sb2.append(i11);
            sb2.append("; use KeyStore");
            AFLogger.afRDLog(sb2.toString());
            AFKeystoreWrapper aFKeystoreWrapper = new AFKeystoreWrapper(context);
            if (!aFKeystoreWrapper.AFKeystoreWrapper()) {
                aFKeystoreWrapper.AFKeystoreWrapper = AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values());
                aFKeystoreWrapper.values = 0;
                aFKeystoreWrapper.AFKeystoreWrapper(aFKeystoreWrapper.AFInAppEventParameterName());
            } else {
                String strAFInAppEventParameterName = aFKeystoreWrapper.AFInAppEventParameterName();
                synchronized (aFKeystoreWrapper.valueOf) {
                    aFKeystoreWrapper.values++;
                    AFLogger.afInfoLog("Deleting key with alias: ".concat(String.valueOf(strAFInAppEventParameterName)));
                    try {
                        synchronized (aFKeystoreWrapper.valueOf) {
                            aFKeystoreWrapper.AFInAppEventType.deleteEntry(strAFInAppEventParameterName);
                        }
                    } catch (KeyStoreException e10) {
                        StringBuilder sb3 = new StringBuilder("Exception ");
                        sb3.append(e10.getMessage());
                        sb3.append(" occurred");
                        AFLogger.afErrorLog(sb3.toString(), e10);
                    }
                }
                aFKeystoreWrapper.AFKeystoreWrapper(aFKeystoreWrapper.AFInAppEventParameterName());
            }
            AFInAppEventType("KSAppsFlyerId", aFKeystoreWrapper.valueOf());
            AFInAppEventType("KSAppsFlyerRICounter", String.valueOf(aFKeystoreWrapper.AFInAppEventType()));
            return;
        }
        StringBuilder sb4 = new StringBuilder("OS SDK is=");
        sb4.append(i11);
        sb4.append("; no KeyStore usage");
        AFLogger.afRDLog(sb4.toString());
    }

    public static AFa1eSDK valueOf() {
        AFa1eSDK aFa1eSDK;
        int i10 = onDeepLinking + 33;
        int i11 = i10 % 128;
        onAttributionFailure = i11;
        if (i10 % 2 != 0) {
            aFa1eSDK = afRDLog;
            int i12 = 80 / 0;
        } else {
            aFa1eSDK = afRDLog;
        }
        onDeepLinking = (i11 + 51) % 128;
        return aFa1eSDK;
    }

    private static String values(String str) {
        int i10 = onDeepLinking + 117;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            return AppsFlyerProperties.getInstance().getString(str);
        }
        AppsFlyerProperties.getInstance().getString(str);
        throw null;
    }

    public final void AFInAppEventType(Context context, Intent intent) {
        int i10 = onDeepLinking + 71;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            if (intent.getStringExtra("appsflyer_preinstall") != null) {
                onDeepLinking = (onAttributionFailure + 23) % 128;
                AFKeystoreWrapper(intent.getStringExtra("appsflyer_preinstall"));
                onAttributionFailure = (onDeepLinking + 59) % 128;
            }
            AFLogger.afInfoLog("****** onReceive called *******");
            AppsFlyerProperties.getInstance();
            String stringExtra = intent.getStringExtra("referrer");
            AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(stringExtra)));
            if (stringExtra != null) {
                values(context).AFInAppEventParameterName("referrer", stringExtra);
                AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
                appsFlyerProperties.set("AF_REFERRER", stringExtra);
                appsFlyerProperties.AFKeystoreWrapper = stringExtra;
                if (AppsFlyerProperties.getInstance().AFInAppEventType()) {
                    AFLogger.afInfoLog("onReceive: isLaunchCalled");
                    AFInAppEventType(context, AFe1nSDK.onReceive);
                    AFKeystoreWrapper(context, stringExtra);
                }
            }
            onAttributionFailure = (onDeepLinking + 29) % 128;
            return;
        }
        intent.getStringExtra("appsflyer_preinstall");
        throw null;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void start(@NonNull Context context, String str, final AppsFlyerRequestListener appsFlyerRequestListener) {
        int i10 = onDeepLinking + 57;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            if (AFInAppEventType().onResponseNative().AFInAppEventType()) {
                return;
            }
            if (!this.onAppOpenAttributionNative) {
                AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'start()' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
                if (str == null) {
                    if (appsFlyerRequestListener != null) {
                        int i11 = onAttributionFailure + 75;
                        onDeepLinking = i11 % 128;
                        if (i11 % 2 == 0) {
                            appsFlyerRequestListener.onError(7, "No dev key");
                            return;
                        } else {
                            appsFlyerRequestListener.onError(41, "No dev key");
                            return;
                        }
                    }
                    return;
                }
            }
            AFKeystoreWrapper(context);
            final AFe1mSDK aFe1mSDKAfDebugLog = AFInAppEventType().afDebugLog();
            aFe1mSDKAfDebugLog.AFKeystoreWrapper(AFa1rSDK.AFInAppEventParameterName(context));
            this.init = (Application) context.getApplicationContext();
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("start", str);
            String str2 = values;
            AFLogger.afInfoLog(String.format("Starting AppsFlyer: (v%s.%s)", "6.12.2", str2));
            StringBuilder sb2 = new StringBuilder("Build Number: ");
            sb2.append(str2);
            AFLogger.afInfoLog(sb2.toString());
            AppsFlyerProperties.getInstance().loadProperties(AFInAppEventType().values());
            if (!TextUtils.isEmpty(str)) {
                onDeepLinking = (onAttributionFailure + 17) % 128;
                AFInAppEventType().afWarnLog().AFInAppEventParameterName = str;
                AFa1aSDK.AFInAppEventType(str);
            } else if (TextUtils.isEmpty(AFInAppEventType().afWarnLog().AFInAppEventParameterName)) {
                int i12 = onAttributionFailure + 111;
                onDeepLinking = i12 % 128;
                if (i12 % 2 != 0) {
                    AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the start() API (should be called on Activity's onCreate).");
                    if (appsFlyerRequestListener != null) {
                        appsFlyerRequestListener.onError(41, "No dev key");
                    }
                    onDeepLinking = (onAttributionFailure + 31) % 128;
                    return;
                }
                AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the start() API (should be called on Activity's onCreate).");
                throw null;
            }
            AFInAppEventType().afRDLog().AFInAppEventParameterName((AFd1bSDK) null);
            afWarnLog();
            afInfoLog(this.init.getBaseContext());
            if (this.onDeepLinkingNative) {
                AFInAppEventType(this.init.getApplicationContext());
            }
            this.onConversionDataFail.onResponseNative().AFKeystoreWrapper(context, new AFb1iSDK.AFa1wSDK() { // from class: com.appsflyer.internal.AFa1eSDK.3
                @Override // com.appsflyer.internal.AFb1iSDK.AFa1wSDK
                public final void AFInAppEventType(@NonNull Context context2) {
                    AFLogger.afInfoLog("onBecameBackground");
                    AFe1mSDK aFe1mSDK = aFe1mSDKAfDebugLog;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j10 = aFe1mSDK.afErrorLog;
                    if (j10 != 0) {
                        long j11 = jCurrentTimeMillis - j10;
                        if (j11 > 0 && j11 < 1000) {
                            j11 = 1000;
                        }
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(j11);
                        aFe1mSDK.afWarnLog = seconds;
                        aFe1mSDK.valueOf.AFInAppEventParameterName("prev_session_dur", seconds);
                    } else {
                        AFLogger.afInfoLog("Metrics: fg ts is missing");
                    }
                    AFLogger.afInfoLog("callStatsBackground background call");
                    AFa1eSDK.this.AFKeystoreWrapper(new WeakReference<>(context2));
                    AFa1eSDK.this.AFInAppEventType().onConversionDataSuccess().valueOf();
                    AFb1tSDK aFb1tSDKAfErrorLogForExcManagerOnly = AFa1eSDK.this.AFInAppEventType().afErrorLogForExcManagerOnly();
                    if (aFb1tSDKAfErrorLogForExcManagerOnly.afInfoLog()) {
                        aFb1tSDKAfErrorLogForExcManagerOnly.values();
                        if (context2 != null && !AppsFlyerLib.getInstance().isStopped()) {
                            aFb1tSDKAfErrorLogForExcManagerOnly.valueOf(context2.getPackageName(), context2.getPackageManager(), AFa1eSDK.this.AFInAppEventType());
                        }
                        aFb1tSDKAfErrorLogForExcManagerOnly.valueOf();
                    } else {
                        AFLogger.afDebugLog("RD status is OFF");
                    }
                    AFa1eSDK.this.AFInAppEventType().getLevel().AFInAppEventType();
                }

                @Override // com.appsflyer.internal.AFb1iSDK.AFa1wSDK
                public final void values(@NonNull Activity activity) {
                    aFe1mSDKAfDebugLog.values();
                    AFa1eSDK.this.AFInAppEventType().afRDLog().AFInAppEventParameterName((AFd1bSDK) null);
                    AFa1eSDK.AFInAppEventParameterName(AFa1eSDK.this);
                    AFa1eSDK aFa1eSDK = AFa1eSDK.this;
                    int iValueOf = aFa1eSDK.valueOf(aFa1eSDK.values(activity), false);
                    AFLogger.afInfoLog("onBecameForeground");
                    if (iValueOf < 2) {
                        AFa1eSDK.this.AFInAppEventType().getLevel().AFInAppEventParameterName();
                    }
                    AFe1hSDK aFe1hSDK = new AFe1hSDK();
                    if (activity != null) {
                        AFa1eSDK.this.AFInAppEventType().AppsFlyer2dXConversionCallback().valueOf(aFe1hSDK.valueOf(), activity.getIntent(), activity.getApplication());
                    }
                    AFa1eSDK aFa1eSDK2 = AFa1eSDK.this;
                    aFe1hSDK.AFInAppEventParameterName = appsFlyerRequestListener;
                    aFa1eSDK2.values(aFe1hSDK, activity);
                }
            });
            return;
        }
        AFInAppEventType().onResponseNative().AFInAppEventType();
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:42:0x00da A[Catch: JSONException -> 0x00a0, TRY_ENTER, TryCatch #1 {JSONException -> 0x00a0, blocks: (B:23:0x006e, B:24:0x007a, B:28:0x008d, B:37:0x00bc, B:42:0x00da, B:45:0x00f3, B:33:0x00a2), top: B:56:0x006e }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00f3 A[Catch: JSONException -> 0x00a0, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00a0, blocks: (B:23:0x006e, B:24:0x007a, B:28:0x008d, B:37:0x00bc, B:42:0x00da, B:45:0x00f3, B:33:0x00a2), top: B:56:0x006e }] */
    /* JADX WARN: Code duplicated, block: B:70:0x00f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x005f A[EDGE_INSN: B:73:0x005f->B:71:0x005f BREAK  A[LOOP:3: B:19:0x0060->B:78:0x0060], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x005f A[EDGE_INSN: B:75:0x005f->B:71:0x005f BREAK  A[LOOP:3: B:19:0x0060->B:78:0x0060], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x010c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x010c A[SYNTHETIC] */
    private static void valueOf(JSONObject jSONObject) {
        String str;
        int i10;
        ArrayList arrayList = new ArrayList();
        Iterator<String> itKeys = jSONObject.keys();
        while (true) {
            int i11 = 0;
            if (!itKeys.hasNext()) {
                break;
            }
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(itKeys.next()));
                while (i11 < jSONArray.length()) {
                    int i12 = onDeepLinking + 73;
                    onAttributionFailure = i12 % 128;
                    if (i12 % 2 != 0) {
                        arrayList.add(Long.valueOf(jSONArray.getLong(i11)));
                        i11 += 107;
                    } else {
                        arrayList.add(Long.valueOf(jSONArray.getLong(i11)));
                        i11++;
                    }
                }
            } catch (JSONException e10) {
                AFLogger.afErrorLogForExcManagerOnly("error at timeStampArr", e10);
            }
        }
        Collections.sort(arrayList);
        Iterator<String> itKeys2 = jSONObject.keys();
        loop2: while (true) {
            str = null;
            while (true) {
                if (!itKeys2.hasNext() || str != null) {
                    break loop2;
                }
                String next = itKeys2.next();
                try {
                    JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                    int i13 = 0;
                    while (i13 < jSONArray2.length()) {
                        int i14 = onAttributionFailure + 13;
                        onDeepLinking = i14 % 128;
                        if (i14 % 2 != 0) {
                            if (jSONArray2.getLong(i13) == ((Long) arrayList.get(0)).longValue()) {
                                break;
                            }
                            onAttributionFailure = (onDeepLinking + 61) % 128;
                            if (jSONArray2.getLong(i13) != ((Long) arrayList.get(1)).longValue()) {
                                break;
                                break;
                            }
                            i10 = onAttributionFailure + 61;
                            onDeepLinking = i10 % 128;
                            if (i10 % 2 == 0) {
                                if (jSONArray2.getLong(i13) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                    break;
                                    break;
                                } else {
                                    i13++;
                                    str = next;
                                }
                            } else if (jSONArray2.getLong(i13) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                break;
                                break;
                            } else {
                                i13++;
                                str = next;
                            }
                        } else {
                            if (jSONArray2.getLong(i13) == ((Long) arrayList.get(1)).longValue()) {
                                break;
                            }
                            onAttributionFailure = (onDeepLinking + 61) % 128;
                            if (jSONArray2.getLong(i13) != ((Long) arrayList.get(1)).longValue()) {
                                break;
                            }
                            i10 = onAttributionFailure + 61;
                            onDeepLinking = i10 % 128;
                            if (i10 % 2 == 0) {
                                if (jSONArray2.getLong(i13) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                    break;
                                }
                                i13++;
                                str = next;
                            } else {
                                if (jSONArray2.getLong(i13) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                    break;
                                }
                                i13++;
                                str = next;
                            }
                        }
                    }
                } catch (JSONException e11) {
                    AFLogger.afErrorLogForExcManagerOnly("error at manageExtraReferrers", e11);
                }
            }
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    final void AFKeystoreWrapper(WeakReference<Context> weakReference) {
        onAttributionFailure = (onDeepLinking + 3) % 128;
        if (weakReference.get() == null) {
            return;
        }
        AFLogger.afInfoLog("app went to background");
        AFb1dSDK aFb1dSDKValues = values(weakReference.get());
        AppsFlyerProperties.getInstance().saveProperties(aFb1dSDKValues);
        long j10 = AFInAppEventType().afDebugLog().afWarnLog;
        HashMap map = new HashMap();
        String str = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
        if (str == null) {
            AFLogger.afWarnLog("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        String strValues = values("KSAppsFlyerId");
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            map.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
            onAttributionFailure = (onDeepLinking + 57) % 128;
        }
        AFc1uSDK.AFa1wSDK aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(weakReference.get().getContentResolver());
        if (aFa1wSDKAFInAppEventParameterName != null) {
            map.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
            map.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
        }
        String string = AppsFlyerProperties.getInstance().getString("advertiserId");
        if (string != null) {
            map.put("advertiserId", string);
            onDeepLinking = (onAttributionFailure + 105) % 128;
        }
        map.put("app_id", weakReference.get().getPackageName());
        map.put("devkey", str);
        map.put("uid", AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values()));
        map.put("time_in_app", String.valueOf(j10));
        map.put("statType", "user_closed_app");
        map.put("platform", "Android");
        map.put("launch_counter", Integer.toString(valueOf(aFb1dSDKValues, false)));
        map.put("channel", AFInAppEventType().AFInAppEventParameterName().values());
        if (strValues == null) {
            strValues = "";
        }
        map.put("originalAppsflyerId", strValues);
        if (this.AppsFlyer2dXConversionCallback) {
            AFe1bSDK aFe1bSDK = new AFe1bSDK();
            aFe1bSDK.afErrorLogForExcManagerOnly = isStopped();
            AFd1hSDK aFd1hSDK = new AFd1hSDK((AFe1bSDK) aFe1bSDK.AFInAppEventParameterName(AFInAppEventType().AFInAppEventParameterName().AFInAppEventType.values("appsFlyerCount", 0)).values(map).AFKeystoreWrapper(String.format(afErrorLog, AppsFlyerLib.getInstance().getHostPrefix(), valueOf().getHostName())), AFInAppEventType());
            AFc1bSDK aFc1bSDKAFVersionDeclaration = AFInAppEventType().AFVersionDeclaration();
            aFc1bSDKAFVersionDeclaration.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration.new AnonymousClass4(aFd1hSDK));
            return;
        }
        AFLogger.afDebugLog("Stats call is disabled, ignore ...");
        int i10 = onDeepLinking + 99;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            int i11 = 16 / 0;
        }
    }

    @VisibleForTesting
    final void values(@NonNull AFa1qSDK aFa1qSDK, @Nullable Activity activity) {
        AFInAppEventType(aFa1qSDK, activity);
        if (AFInAppEventType().afWarnLog().AFInAppEventParameterName == null) {
            int i10 = onDeepLinking + 65;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 == 0) {
                AFLogger.afWarnLog("[LogEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
                AppsFlyerRequestListener appsFlyerRequestListener = aFa1qSDK.AFInAppEventParameterName;
                if (appsFlyerRequestListener != null) {
                    appsFlyerRequestListener.onError(41, "No dev key");
                    return;
                }
                return;
            }
            AFLogger.afWarnLog("[LogEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
            AppsFlyerRequestListener appsFlyerRequestListener2 = aFa1qSDK.AFInAppEventParameterName;
            throw null;
        }
        String referrer = AppsFlyerProperties.getInstance().getReferrer(AFInAppEventType().values());
        if (referrer == null) {
            referrer = "";
        } else {
            onAttributionFailure = (onDeepLinking + 99) % 128;
        }
        aFa1qSDK.afErrorLog = referrer;
        AFInAppEventType(aFa1qSDK);
        onAttributionFailure = (onDeepLinking + 95) % 128;
    }

    public static String AFInAppEventParameterName() {
        onAttributionFailure = (onDeepLinking + 3) % 128;
        String strValues = values(AppsFlyerProperties.APP_USER_ID);
        onDeepLinking = (onAttributionFailure + 99) % 128;
        return strValues;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0044  */
    private boolean AFInAppEventParameterName(AFa1qSDK aFa1qSDK, AFb1dSDK aFb1dSDK) {
        boolean z10;
        boolean z11;
        int iValueOf = valueOf(aFb1dSDK, false);
        if (iValueOf != 1 || (aFa1qSDK instanceof AFe1lSDK)) {
            z10 = false;
        } else {
            onDeepLinking = (onAttributionFailure + 81) % 128;
            z10 = true;
        }
        if (aFb1dSDK.valueOf(AppsFlyerProperties.NEW_REFERRER_SENT)) {
            z11 = false;
        } else {
            int i10 = onDeepLinking;
            int i11 = i10 + 83;
            onAttributionFailure = i11 % 128;
            if (i11 % 2 == 0 ? iValueOf != 1 : iValueOf != 0) {
                z11 = false;
            } else {
                int i12 = i10 + 17;
                onAttributionFailure = i12 % 128;
                z11 = i12 % 2 == 0;
                onAttributionFailure = (i10 + 67) % 128;
            }
        }
        if (!z11) {
            int i13 = onDeepLinking + 97;
            onAttributionFailure = i13 % 128;
            if (i13 % 2 != 0) {
                throw null;
            }
            if (!z10) {
                return false;
            }
        }
        return true;
    }

    @Override // com.appsflyer.AppsFlyerLib
    public final void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName("setUserEmails", (String[]) arrayList.toArray(new String[strArr.length + 1]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap map = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        onDeepLinking = (onAttributionFailure + 93) % 128;
        String str = null;
        for (String str2 : strArr) {
            onAttributionFailure = (onDeepLinking + 33) % 128;
            if (AnonymousClass5.valueOf[emailsCryptType.ordinal()] != 2) {
                arrayList2.add(AFc1nSDK.AFInAppEventType(str2));
                str = "sha256_el_arr";
            } else {
                arrayList2.add(str2);
                str = "plain_el_arr";
            }
        }
        map.put(str, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(map).toString());
    }

    private void AFInAppEventParameterName(Map<String, Object> map) {
        if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false)) {
            onAttributionFailure = (onDeepLinking + 81) % 128;
            if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) {
                if (map.get("advertiserId") != null) {
                    try {
                        if (AFb1uSDK.values(this.AFLogger) && map.remove(RbParams.Default.URL_PARAM_KEY_ANDROID_ID) != null) {
                            int i10 = onAttributionFailure + 89;
                            onDeepLinking = i10 % 128;
                            if (i10 % 2 != 0) {
                                AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                            } else {
                                AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                                try {
                                    throw null;
                                } catch (Throwable th2) {
                                    throw th2;
                                }
                            }
                        }
                        if (!AFb1uSDK.values(AFInAppEventType().afWarnLog().values) || map.remove("imei") == null) {
                            return;
                        }
                        AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
                        return;
                    } catch (Exception e10) {
                        AFLogger.afErrorLog("failed to remove IMEI or AndroidID key from params; ", e10);
                        return;
                    }
                }
                return;
            }
        }
        onAttributionFailure = (onDeepLinking + 9) % 128;
    }

    private boolean afInfoLog() {
        onAttributionFailure = (onDeepLinking + 93) % 128;
        if (this.afDebugLog > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.afDebugLog;
            Locale locale = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", locale);
            String strAFInAppEventType = AFInAppEventType(simpleDateFormat, this.afDebugLog);
            String strAFInAppEventType2 = AFInAppEventType(simpleDateFormat, this.afWarnLog);
            if (jCurrentTimeMillis < this.AFVersionDeclaration) {
                onDeepLinking = (onAttributionFailure + 35) % 128;
                if (!isStopped()) {
                    onDeepLinking = (onAttributionFailure + 121) % 128;
                    AFLogger.afInfoLog(String.format(locale, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", strAFInAppEventType, strAFInAppEventType2, Long.valueOf(jCurrentTimeMillis), Long.valueOf(this.AFVersionDeclaration)));
                    return true;
                }
            }
            if (!isStopped()) {
                AFLogger.afInfoLog(String.format(locale, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", strAFInAppEventType, strAFInAppEventType2, Long.valueOf(jCurrentTimeMillis)));
            }
        } else if (!isStopped()) {
            int i10 = onAttributionFailure + 11;
            onDeepLinking = i10 % 128;
            if (i10 % 2 == 0) {
                AFLogger.afInfoLog("Sending first launch for this session!");
                throw null;
            }
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        int i11 = onDeepLinking + 59;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 == 0) {
            return false;
        }
        throw null;
    }

    public static Map<String, Object> values(Map<String, Object> map) {
        onAttributionFailure = (onDeepLinking + 73) % 128;
        if (map.containsKey("meta")) {
            int i10 = onDeepLinking + 13;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 != 0) {
                throw null;
            }
            return (Map) map.get("meta");
        }
        HashMap map2 = new HashMap();
        map.put("meta", map2);
        return map2;
    }

    public final void AFInAppEventType(Context context, String str) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strValues = values(context).values("extraReferrers", (String) null);
            if (strValues == null) {
                jSONObject = new JSONObject();
                jSONArray2 = new JSONArray();
            } else {
                JSONObject jSONObject2 = new JSONObject(strValues);
                if (jSONObject2.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject2.get(str));
                } else {
                    jSONArray = new JSONArray();
                    onAttributionFailure = (onDeepLinking + 49) % 128;
                }
                jSONArray2 = jSONArray;
                jSONObject = jSONObject2;
            }
            if (jSONArray2.length() < 5) {
                jSONArray2.put(jCurrentTimeMillis);
            }
            if (jSONObject.length() >= 4) {
                onDeepLinking = (onAttributionFailure + 53) % 128;
                valueOf(jSONObject);
                onAttributionFailure = (onDeepLinking + 1) % 128;
            }
            jSONObject.put(str, jSONArray2.toString());
            values(context).AFInAppEventParameterName("extraReferrers", jSONObject.toString());
        } catch (JSONException e10) {
            AFLogger.afErrorLogForExcManagerOnly("error at addReferrer", e10);
        } catch (Throwable th2) {
            StringBuilder sb2 = new StringBuilder("Couldn't save referrer - ");
            sb2.append(str);
            sb2.append(": ");
            AFLogger.afErrorLog(sb2.toString(), th2);
        }
    }

    @Nullable
    @VisibleForTesting
    private String values(Context context, String str) {
        if (context == null) {
            int i10 = onDeepLinking + 7;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 == 0) {
                return null;
            }
            throw null;
        }
        AFKeystoreWrapper(context);
        String strAFInAppEventType = AFInAppEventType().AFInAppEventParameterName().AFInAppEventType(str);
        onAttributionFailure = (onDeepLinking + 63) % 128;
        return strAFInAppEventType;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x02fc A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:109:0x030f A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:112:0x0322 A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x033d A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x0349 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:125:0x0358 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:128:0x0365 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:130:0x036c A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:133:0x037c A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:136:0x038c A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x0392 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:142:0x03b5 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:155:0x03f9 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:156:0x03fe A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:159:0x0419 A[Catch: all -> 0x00c2, Exception -> 0x041f, TRY_LEAVE, TryCatch #9 {Exception -> 0x041f, blocks: (B:157:0x0403, B:159:0x0419), top: B:273:0x0403, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:185:0x04b9 A[Catch: all -> 0x04c3, TryCatch #2 {all -> 0x04c3, blocks: (B:180:0x04a2, B:183:0x04b5, B:185:0x04b9, B:190:0x04cb), top: B:261:0x04a2 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x05a0 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:215:0x05a9 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:217:0x05ad A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:219:0x05b5 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:221:0x05bc A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:224:0x05e5 A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:232:0x061f A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:234:0x062a  */
    /* JADX WARN: Code duplicated, block: B:235:0x062c  */
    /* JADX WARN: Code duplicated, block: B:239:0x063e A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:242:0x066e  */
    /* JADX WARN: Code duplicated, block: B:245:0x067d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:249:0x06a9 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:252:0x0709 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:275:0x05ee A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:283:0x0435 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:287:0x03d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x01a9 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x01af A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x01b5 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:53:0x01f2 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:57:0x0213 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0220 A[Catch: all -> 0x00c2, TRY_ENTER, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x022a A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x024e A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x025b A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0265 A[Catch: all -> 0x00c2, TRY_ENTER, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0272 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0283 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x02b0 A[Catch: all -> 0x00c2, TryCatch #1 {all -> 0x00c2, blocks: (B:12:0x00b0, B:14:0x00b6, B:20:0x00c6, B:22:0x00d6, B:23:0x00e1, B:25:0x00fd, B:28:0x0105, B:30:0x010d, B:31:0x0112, B:33:0x0118, B:35:0x0120, B:38:0x012b, B:40:0x01a9, B:42:0x01af, B:44:0x01b5, B:46:0x01d1, B:48:0x01de, B:50:0x01e5, B:51:0x01ec, B:53:0x01f2, B:55:0x01fc, B:57:0x0213, B:58:0x0218, B:61:0x0220, B:62:0x0223, B:64:0x022a, B:65:0x022d, B:67:0x023f, B:69:0x0245, B:70:0x0248, B:72:0x024e, B:73:0x0257, B:75:0x025b, B:78:0x0265, B:79:0x026a, B:81:0x0272, B:83:0x0288, B:86:0x0298, B:88:0x029e, B:89:0x02a8, B:91:0x02b0, B:92:0x02b5, B:94:0x02c8, B:96:0x02ce, B:97:0x02d1, B:99:0x02e7, B:103:0x02f1, B:104:0x02f6, B:106:0x02fc, B:107:0x0309, B:109:0x030f, B:110:0x031c, B:112:0x0322, B:115:0x0333, B:117:0x0339, B:123:0x0352, B:125:0x0358, B:126:0x035d, B:128:0x0365, B:130:0x036c, B:131:0x0376, B:133:0x037c, B:134:0x0383, B:136:0x038c, B:138:0x0392, B:139:0x03a8, B:140:0x03ad, B:142:0x03b5, B:143:0x03ba, B:155:0x03f9, B:156:0x03fe, B:157:0x0403, B:159:0x0419, B:163:0x0435, B:167:0x0449, B:171:0x045d, B:175:0x0474, B:176:0x0483, B:211:0x0583, B:213:0x05a0, B:215:0x05a9, B:217:0x05ad, B:219:0x05b5, B:221:0x05bc, B:222:0x05d2, B:224:0x05e5, B:226:0x05ee, B:230:0x0619, B:232:0x061f, B:236:0x062d, B:237:0x0634, B:239:0x063e, B:240:0x0650, B:243:0x066f, B:246:0x067f, B:247:0x0683, B:249:0x06a9, B:250:0x06b6, B:252:0x0709, B:254:0x070d, B:229:0x05ff, B:210:0x057d, B:179:0x049d, B:174:0x046e, B:170:0x0458, B:166:0x0444, B:162:0x0420, B:151:0x03e8, B:153:0x03ed, B:118:0x033d, B:120:0x0349, B:122:0x034f, B:255:0x0713, B:82:0x0283, B:37:0x0126, B:21:0x00d1, B:145:0x03d0), top: B:260:0x00b0, inners: #3, #5, #9, #10, #12, #14, #15, #16 }] */
    @WorkerThread
    final Map<String, Object> AFInAppEventParameterName(AFa1qSDK aFa1qSDK) {
        AFe1vSDK aFe1vSDKAFLogger;
        AppsFlyerProperties appsFlyerProperties;
        AFe1mSDK aFe1mSDKAfDebugLog;
        String strValues;
        String strValues2;
        String strValues3;
        String string;
        String strValues4;
        String strAFInAppEventType;
        String strAFInAppEventType2;
        String strAFLogger;
        String strAfInfoLog;
        String str;
        String strAFInAppEventParameterName;
        String string2;
        String strValues5;
        String strValues6;
        String attributionId;
        AppsFlyerProperties appsFlyerProperties2;
        String str2;
        boolean z10;
        AFc1uSDK.AFa1wSDK aFa1wSDKAFInAppEventParameterName;
        AFa1cSDK aFa1cSDK;
        String[] strArr;
        boolean z11;
        PackageInfo packageInfo;
        String strValueOf;
        String str3;
        String strValues7;
        String referrer;
        long j10;
        AFb1lSDK aFb1lSDK;
        UiModeManager uiModeManager;
        String str4 = "Exception while collecting facebook's attribution ID. ";
        Context context = AFInAppEventType().init().AFInAppEventType;
        String str5 = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
        String str6 = aFa1qSDK.afDebugLog;
        Map map = aFa1qSDK.values;
        if (map == null) {
            map = new HashMap();
        }
        String string3 = new JSONObject(map).toString();
        String str7 = aFa1qSDK.afErrorLog;
        AFb1dSDK aFb1dSDKValues = values(context);
        boolean zAFInAppEventParameterName = aFa1qSDK.AFInAppEventParameterName();
        Map<String, Object> map2 = aFa1qSDK.AFKeystoreWrapper;
        AFa1cSDK.values(context, map2);
        Boolean bool = AFa1cSDK.valueOf;
        if (bool != null && !bool.booleanValue()) {
            values(map2).put("ad_ids_disabled", Boolean.TRUE);
        }
        long time = new Date().getTime();
        Object[] objArr = new Object[1];
        values(7 - (ViewConfiguration.getScrollBarFadeDuration() >> 16), "\u0003\ufffb\t\n\ufff7\u0003\u0006\ufff7￼\ufff5\n\uffff", View.MeasureSpec.getSize(0) + 245, TextUtils.getOffsetAfter("", 0) + 12, false, objArr);
        map2.put(((String) objArr[0]).intern(), Long.toString(time));
        try {
            if (!isStopped()) {
                StringBuilder sb2 = new StringBuilder("******* sendTrackingWithEvent: ");
                sb2.append(zAFInAppEventParameterName ? "Launch" : str6);
                AFLogger.afInfoLog(sb2.toString());
            } else {
                AFLogger.afInfoLog("Reporting has been stopped");
            }
            AFInAppEventType().onAppOpenAttributionNative().valueOf();
            try {
                List listAsList = Arrays.asList(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
                if (!listAsList.contains("android.permission.INTERNET")) {
                    AFLogger.afWarnLog("Permission android.permission.INTERNET is missing in the AndroidManifest.xml");
                }
                if (!listAsList.contains("android.permission.ACCESS_NETWORK_STATE")) {
                    AFLogger.afWarnLog("Permission android.permission.ACCESS_NETWORK_STATE is missing in the AndroidManifest.xml");
                }
                if (Build.VERSION.SDK_INT > 32 && !listAsList.contains("com.google.android.gms.permission.AD_ID")) {
                    AFLogger.afWarnLog("Permission com.google.android.gms.permission.AD_ID is missing in the AndroidManifest.xml");
                    aFe1vSDKAFLogger = AFInAppEventType().AFLogger();
                    map2.put("af_events_api", "1");
                    Object[] objArr2 = new Object[1];
                    values((ViewConfiguration.getKeyRepeatDelay() >> 16) + 2, "\u0007�\ufffb\u000b\ufffa", (ExpandableListView.getPackedPositionForGroup(0) > 0L ? 1 : (ExpandableListView.getPackedPositionForGroup(0) == 0L ? 0 : -1)) + 242, TextUtils.lastIndexOf("", '0') + 6, false, objArr2);
                    map2.put(((String) objArr2[0]).intern(), Build.BRAND);
                    map2.put(RbParams.Default.URL_PARAM_KEY_DEVICE, Build.DEVICE);
                    map2.put("product", Build.PRODUCT);
                    map2.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
                    map2.put(DeviceInfo.PARAM_KEY_MODEL, Build.MODEL);
                    map2.put("deviceType", Build.TYPE);
                    aFe1vSDKAFLogger.AFInAppEventParameterName(map2);
                    appsFlyerProperties = AppsFlyerProperties.getInstance();
                    aFe1mSDKAfDebugLog = AFInAppEventType().afDebugLog();
                    if (zAFInAppEventParameterName) {
                        if (aFe1vSDKAFLogger.afRDLog()) {
                            if (!appsFlyerProperties.isOtherSdkStringDisabled()) {
                                map2.put("batteryLevel", String.valueOf(AFInAppEventType().onDeepLinkingNative().AFInAppEventType(context).AFInAppEventParameterName));
                            }
                            afErrorLog(context);
                            uiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
                            if (uiModeManager != null) {
                                map2.put("tv", Boolean.TRUE);
                            }
                            if (AFe1qSDK.AFKeystoreWrapper(context)) {
                                map2.put("inst_app", Boolean.TRUE);
                            }
                        } else {
                            str4 = "Exception while collecting facebook's attribution ID. ";
                        }
                        map2.put("timepassedsincelastlaunch", Long.toString(AFLogger(context)));
                        aFe1vSDKAFLogger.values(map2);
                        aFe1vSDKAFLogger.valueOf(map2);
                        str3 = this.onAttributionFailureNative;
                        if (str3 != null) {
                            map2.put("phone", str3);
                        }
                        if (!TextUtils.isEmpty(str7)) {
                            map2.put("referrer", str7);
                        }
                        strValues7 = aFb1dSDKValues.values("extraReferrers", (String) null);
                        if (strValues7 != null) {
                            map2.put("extraReferrers", strValues7);
                        }
                        referrer = appsFlyerProperties.getReferrer(AFInAppEventType().values());
                        if (!TextUtils.isEmpty(referrer)) {
                            map2.put("referrer", referrer);
                        }
                        j10 = aFe1mSDKAfDebugLog.afWarnLog;
                        if (j10 != 0) {
                            map2.put("prev_session_dur", Long.valueOf(j10));
                        }
                        aFb1lSDK = this.onResponseNative;
                        if (aFb1lSDK != null) {
                            if (!aFb1lSDK.values.isEmpty()) {
                                map2.put("partner_data", aFb1lSDK.values);
                            }
                            if (!aFb1lSDK.AFInAppEventParameterName.isEmpty()) {
                                values(map2).put("partner_data", aFb1lSDK.AFInAppEventParameterName);
                                aFb1lSDK.AFInAppEventParameterName = new HashMap();
                            }
                        }
                    } else {
                        str4 = "Exception while collecting facebook's attribution ID. ";
                        aFe1vSDKAFLogger.valueOf(map2, str6);
                    }
                    strValues = values("KSAppsFlyerId");
                    strValues2 = values("KSAppsFlyerRICounter");
                    if (strValues != null) {
                        map2.put("reinstallCounter", strValues2);
                        map2.put("originalAppsflyerId", strValues);
                    }
                    strValues3 = values(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
                    if (strValues3 != null) {
                        map2.put("customData", strValues3);
                    }
                    map2.putAll(this.onConversionDataFail.afInfoLog().AFInAppEventParameterName());
                    string = appsFlyerProperties.getString(AppsFlyerProperties.EXTENSION);
                    if (string != null) {
                        map2.put(AppsFlyerProperties.EXTENSION, string);
                    }
                    strValues4 = AFInAppEventType().AFInAppEventParameterName().values();
                    strAFInAppEventType = AFInAppEventType(values(context), strValues4);
                    if (strAFInAppEventType == null) {
                        map2.put("af_latestchannel", strValues4);
                    } else {
                        map2.put("af_latestchannel", strValues4);
                    }
                    strAFInAppEventType2 = aFe1vSDKAFLogger.AFInAppEventType();
                    if (strAFInAppEventType2 != null) {
                        map2.put("af_installstore", strAFInAppEventType2.toLowerCase(Locale.getDefault()));
                    }
                    strAFLogger = aFe1vSDKAFLogger.AFLogger();
                    if (strAFLogger != null) {
                        map2.put("af_preinstall_name", strAFLogger.toLowerCase(Locale.getDefault()));
                    }
                    strAfInfoLog = aFe1vSDKAFLogger.afInfoLog();
                    if (strAfInfoLog != null) {
                        map2.put("af_currentstore", strAfInfoLog.toLowerCase(Locale.getDefault()));
                    }
                    if (str5 == null) {
                        str = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
                        if (str == null) {
                        }
                        AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                        AFLogger.afInfoLog("AppsFlyer will not track this event.");
                        return null;
                    }
                    str = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
                    if (str == null) {
                    }
                    AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                    AFLogger.afInfoLog("AppsFlyer will not track this event.");
                    return null;
                    strAFInAppEventParameterName = AFInAppEventParameterName();
                    if (strAFInAppEventParameterName != null) {
                        map2.put("appUserId", strAFInAppEventParameterName);
                    }
                    string2 = appsFlyerProperties.getString(AppsFlyerProperties.USER_EMAILS);
                    if (string2 != null) {
                        map2.put("user_emails", string2);
                    }
                    if (str6 != null) {
                        map2.put("eventName", str6);
                        map2.put("eventValue", string3);
                    }
                    if (afRDLog() != null) {
                        map2.put("appid", values("appid"));
                    }
                    strValues5 = values(AppsFlyerProperties.CURRENCY_CODE);
                    if (strValues5 != null) {
                        if (strValues5.length() != 3) {
                            StringBuilder sb3 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                            sb3.append(strValues5);
                            sb3.append("' is not a legal value.");
                            AFLogger.afWarnLog(sb3.toString());
                        }
                        map2.put(FirebaseAnalytics.Param.CURRENCY, strValues5);
                    }
                    strValues6 = values(AppsFlyerProperties.IS_UPDATE);
                    if (strValues6 != null) {
                        map2.put("isUpdate", strValues6);
                    }
                    map2.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                    if (appsFlyerProperties.getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                        context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                        attributionId = getAttributionId(context);
                        if (attributionId != null) {
                            map2.put("fb", attributionId);
                        }
                        aFe1vSDKAFLogger.values(map2, this.AFLogger);
                        strValueOf = AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values());
                        if (strValueOf != null) {
                            map2.put("uid", strValueOf);
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context2 = aFb1gSDKAFInAppEventParameterName.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context2, context2.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j11 = packageInfo.firstInstallTime;
                            long j12 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale).format(new Date(j11)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale).format(new Date(j12)));
                            String strValues8 = aFe1vSDKAFLogger.values(simpleDateFormat);
                            z10 = false;
                            Object[] objArr3 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr3);
                            map2.put(((String) objArr3[0]).intern(), strValues8);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb4 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb4.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb4.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject = new JSONObject(this.getLevel);
                                        jSONObject.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map3 = new HashMap();
                            map3.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map3.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map3);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        } else {
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat2, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName2 = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context3 = aFb1gSDKAFInAppEventParameterName2.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context3, context3.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName2.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j13 = packageInfo.firstInstallTime;
                            long j14 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale2 = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale2).format(new Date(j13)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale2).format(new Date(j14)));
                            String strValues9 = aFe1vSDKAFLogger.values(simpleDateFormat2);
                            z10 = false;
                            Object[] objArr4 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr4);
                            map2.put(((String) objArr4[0]).intern(), strValues9);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb5 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb5.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb5.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject2 = new JSONObject(this.getLevel);
                                        jSONObject2.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject2.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf2 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf2));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf2);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map4 = new HashMap();
                            map4.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map4.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map4);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        }
                    } else {
                        aFe1vSDKAFLogger.values(map2, this.AFLogger);
                        strValueOf = AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values());
                        if (strValueOf != null) {
                            map2.put("uid", strValueOf);
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat3, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName3 = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context4 = aFb1gSDKAFInAppEventParameterName3.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context4, context4.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName3.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j15 = packageInfo.firstInstallTime;
                            long j16 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale3 = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale3).format(new Date(j15)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale3).format(new Date(j16)));
                            String strValues10 = aFe1vSDKAFLogger.values(simpleDateFormat3);
                            z10 = false;
                            Object[] objArr5 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr5);
                            map2.put(((String) objArr5[0]).intern(), strValues10);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb6 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb6.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb6.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject3 = new JSONObject(this.getLevel);
                                        jSONObject3.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject3.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf3 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf3));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf3);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map5 = new HashMap();
                            map5.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map5.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map5);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        } else {
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat4, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName4 = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context5 = aFb1gSDKAFInAppEventParameterName4.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context5, context5.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName4.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j17 = packageInfo.firstInstallTime;
                            long j18 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale4 = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale4).format(new Date(j17)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale4).format(new Date(j18)));
                            String strValues11 = aFe1vSDKAFLogger.values(simpleDateFormat4);
                            z10 = false;
                            Object[] objArr6 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr6);
                            map2.put(((String) objArr6[0]).intern(), strValues11);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb7 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb7.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb7.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject4 = new JSONObject(this.getLevel);
                                        jSONObject4.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject4.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf4 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf4));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf4);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map6 = new HashMap();
                            map6.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map6.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map6);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        }
                    }
                } else {
                    aFe1vSDKAFLogger = AFInAppEventType().AFLogger();
                    map2.put("af_events_api", "1");
                    Object[] objArr7 = new Object[1];
                    values((ViewConfiguration.getKeyRepeatDelay() >> 16) + 2, "\u0007�\ufffb\u000b\ufffa", (ExpandableListView.getPackedPositionForGroup(0) > 0L ? 1 : (ExpandableListView.getPackedPositionForGroup(0) == 0L ? 0 : -1)) + 242, TextUtils.lastIndexOf("", '0') + 6, false, objArr7);
                    map2.put(((String) objArr7[0]).intern(), Build.BRAND);
                    map2.put(RbParams.Default.URL_PARAM_KEY_DEVICE, Build.DEVICE);
                    map2.put("product", Build.PRODUCT);
                    map2.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
                    map2.put(DeviceInfo.PARAM_KEY_MODEL, Build.MODEL);
                    map2.put("deviceType", Build.TYPE);
                    aFe1vSDKAFLogger.AFInAppEventParameterName(map2);
                    appsFlyerProperties = AppsFlyerProperties.getInstance();
                    aFe1mSDKAfDebugLog = AFInAppEventType().afDebugLog();
                    if (zAFInAppEventParameterName) {
                        if (aFe1vSDKAFLogger.afRDLog()) {
                            if (!appsFlyerProperties.isOtherSdkStringDisabled()) {
                                map2.put("batteryLevel", String.valueOf(AFInAppEventType().onDeepLinkingNative().AFInAppEventType(context).AFInAppEventParameterName));
                            }
                            afErrorLog(context);
                            uiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
                            if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                                map2.put("tv", Boolean.TRUE);
                            }
                            if (AFe1qSDK.AFKeystoreWrapper(context)) {
                                map2.put("inst_app", Boolean.TRUE);
                            }
                        } else {
                            str4 = "Exception while collecting facebook's attribution ID. ";
                        }
                        map2.put("timepassedsincelastlaunch", Long.toString(AFLogger(context)));
                        aFe1vSDKAFLogger.values(map2);
                        aFe1vSDKAFLogger.valueOf(map2);
                        str3 = this.onAttributionFailureNative;
                        if (str3 != null) {
                            map2.put("phone", str3);
                        }
                        if (!TextUtils.isEmpty(str7)) {
                            map2.put("referrer", str7);
                        }
                        strValues7 = aFb1dSDKValues.values("extraReferrers", (String) null);
                        if (strValues7 != null) {
                            map2.put("extraReferrers", strValues7);
                        }
                        referrer = appsFlyerProperties.getReferrer(AFInAppEventType().values());
                        if (!TextUtils.isEmpty(referrer) && map2.get("referrer") == null) {
                            map2.put("referrer", referrer);
                        }
                        j10 = aFe1mSDKAfDebugLog.afWarnLog;
                        if (j10 != 0) {
                            map2.put("prev_session_dur", Long.valueOf(j10));
                        }
                        aFb1lSDK = this.onResponseNative;
                        if (aFb1lSDK != null) {
                            if (!aFb1lSDK.values.isEmpty()) {
                                map2.put("partner_data", aFb1lSDK.values);
                            }
                            if (!aFb1lSDK.AFInAppEventParameterName.isEmpty()) {
                                values(map2).put("partner_data", aFb1lSDK.AFInAppEventParameterName);
                                aFb1lSDK.AFInAppEventParameterName = new HashMap();
                            }
                        }
                    } else {
                        str4 = "Exception while collecting facebook's attribution ID. ";
                        aFe1vSDKAFLogger.valueOf(map2, str6);
                    }
                    strValues = values("KSAppsFlyerId");
                    strValues2 = values("KSAppsFlyerRICounter");
                    if (strValues != null && strValues2 != null && Integer.parseInt(strValues2) > 0) {
                        map2.put("reinstallCounter", strValues2);
                        map2.put("originalAppsflyerId", strValues);
                    }
                    strValues3 = values(AppsFlyerProperties.ADDITIONAL_CUSTOM_DATA);
                    if (strValues3 != null) {
                        map2.put("customData", strValues3);
                    }
                    map2.putAll(this.onConversionDataFail.afInfoLog().AFInAppEventParameterName());
                    string = appsFlyerProperties.getString(AppsFlyerProperties.EXTENSION);
                    if (string != null && string.length() > 0) {
                        map2.put(AppsFlyerProperties.EXTENSION, string);
                    }
                    strValues4 = AFInAppEventType().AFInAppEventParameterName().values();
                    strAFInAppEventType = AFInAppEventType(values(context), strValues4);
                    if ((strAFInAppEventType == null && !strAFInAppEventType.equals(strValues4)) || (strAFInAppEventType == null && strValues4 != null)) {
                        map2.put("af_latestchannel", strValues4);
                    }
                    strAFInAppEventType2 = aFe1vSDKAFLogger.AFInAppEventType();
                    if (strAFInAppEventType2 != null) {
                        map2.put("af_installstore", strAFInAppEventType2.toLowerCase(Locale.getDefault()));
                    }
                    strAFLogger = aFe1vSDKAFLogger.AFLogger();
                    if (strAFLogger != null) {
                        map2.put("af_preinstall_name", strAFLogger.toLowerCase(Locale.getDefault()));
                    }
                    strAfInfoLog = aFe1vSDKAFLogger.afInfoLog();
                    if (strAfInfoLog != null) {
                        map2.put("af_currentstore", strAfInfoLog.toLowerCase(Locale.getDefault()));
                    }
                    if (str5 == null && str5.length() > 0) {
                        map2.put("appsflyerKey", str5);
                    } else {
                        str = AFInAppEventType().afWarnLog().AFInAppEventParameterName;
                        if (str == null && str.length() > 0) {
                            map2.put("appsflyerKey", str);
                        } else {
                            AFLogger.afInfoLog("AppsFlyer dev key is missing!!! Please use  AppsFlyerLib.getInstance().setAppsFlyerKey(...) to set it. ");
                            AFLogger.afInfoLog("AppsFlyer will not track this event.");
                            return null;
                        }
                    }
                    strAFInAppEventParameterName = AFInAppEventParameterName();
                    if (strAFInAppEventParameterName != null) {
                        map2.put("appUserId", strAFInAppEventParameterName);
                    }
                    string2 = appsFlyerProperties.getString(AppsFlyerProperties.USER_EMAILS);
                    if (string2 != null) {
                        map2.put("user_emails", string2);
                    }
                    if (str6 != null) {
                        map2.put("eventName", str6);
                        map2.put("eventValue", string3);
                    }
                    if (afRDLog() != null) {
                        map2.put("appid", values("appid"));
                    }
                    strValues5 = values(AppsFlyerProperties.CURRENCY_CODE);
                    if (strValues5 != null) {
                        if (strValues5.length() != 3) {
                            StringBuilder sb8 = new StringBuilder("WARNING: currency code should be 3 characters!!! '");
                            sb8.append(strValues5);
                            sb8.append("' is not a legal value.");
                            AFLogger.afWarnLog(sb8.toString());
                        }
                        map2.put(FirebaseAnalytics.Param.CURRENCY, strValues5);
                    }
                    strValues6 = values(AppsFlyerProperties.IS_UPDATE);
                    if (strValues6 != null) {
                        map2.put("isUpdate", strValues6);
                    }
                    map2.put("af_preinstalled", Boolean.toString(isPreInstalledApp(context)));
                    if (appsFlyerProperties.getBoolean(AppsFlyerProperties.COLLECT_FACEBOOK_ATTR_ID, true)) {
                        try {
                            context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                            attributionId = getAttributionId(context);
                        } catch (PackageManager.NameNotFoundException e10) {
                            String str8 = str4;
                            AFLogger.afErrorLogForExcManagerOnly("com.facebook.katana not found", e10, true);
                            AFLogger.afWarnLog(str8);
                            attributionId = null;
                        } catch (Throwable th2) {
                            AFLogger.afErrorLog(str4, th2);
                            attributionId = null;
                        }
                        if (attributionId != null) {
                            map2.put("fb", attributionId);
                        }
                        aFe1vSDKAFLogger.values(map2, this.AFLogger);
                        try {
                            strValueOf = AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values());
                            if (strValueOf != null) {
                                map2.put("uid", strValueOf);
                                try {
                                    map2.put("lang", Locale.getDefault().getDisplayLanguage());
                                } catch (Exception e11) {
                                    AFLogger.afErrorLog("Exception while collecting display language name. ", e11);
                                }
                                try {
                                    map2.put("lang_code", Locale.getDefault().getLanguage());
                                } catch (Exception e12) {
                                    AFLogger.afErrorLog("Exception while collecting display language code. ", e12);
                                }
                                try {
                                    map2.put("country", Locale.getDefault().getCountry());
                                } catch (Exception e13) {
                                    AFLogger.afErrorLog("Exception while collecting country name. ", e13);
                                }
                                aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                                aFe1vSDKAFLogger.AFInAppEventType(map2);
                                SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                try {
                                    map2.put("installDate", AFInAppEventType(simpleDateFormat5, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                                } catch (Exception e14) {
                                    AFLogger.afErrorLog("Exception while collecting install date. ", e14);
                                }
                                try {
                                    z10 = false;
                                    try {
                                        packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                        if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                            values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                                        }
                                        AFb1gSDK aFb1gSDKAFInAppEventParameterName5 = AFInAppEventType().AFInAppEventParameterName();
                                        map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                        Context context6 = aFb1gSDKAFInAppEventParameterName5.valueOf.AFInAppEventType;
                                        map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context6, context6.getPackageName()));
                                        map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName5.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                                        long j19 = packageInfo.firstInstallTime;
                                        try {
                                            long j110 = packageInfo.lastUpdateTime;
                                            appsFlyerProperties2 = appsFlyerProperties;
                                            try {
                                                Locale locale5 = Locale.US;
                                                str2 = str6;
                                                try {
                                                    map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale5).format(new Date(j19)));
                                                    map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale5).format(new Date(j110)));
                                                    String strValues12 = aFe1vSDKAFLogger.values(simpleDateFormat5);
                                                    z10 = false;
                                                    try {
                                                        Object[] objArr8 = new Object[1];
                                                        values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr8);
                                                        map2.put(((String) objArr8[0]).intern(), strValues12);
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        AFLogger.afErrorLog("Exception while collecting app version data ", th, true);
                                                    }
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    z10 = false;
                                                    AFLogger.afErrorLog("Exception while collecting app version data ", th, true);
                                                    this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                                                    StringBuilder sb9 = new StringBuilder("didConfigureTokenRefreshService=");
                                                    sb9.append(this.onInstallConversionFailureNative);
                                                    AFLogger.afDebugLog(sb9.toString());
                                                    if (!this.onInstallConversionFailureNative) {
                                                        map2.put("tokenRefreshConfigured", Boolean.FALSE);
                                                    }
                                                    if (zAFInAppEventParameterName) {
                                                        if (this.getLevel != null) {
                                                            if (map2.get("af_deeplink") != null) {
                                                                AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                                            } else {
                                                                JSONObject jSONObject5 = new JSONObject(this.getLevel);
                                                                jSONObject5.put("isPush", "true");
                                                                map2.put("af_deeplink", jSONObject5.toString());
                                                            }
                                                        }
                                                        this.getLevel = null;
                                                        map2.put("open_referrer", aFa1qSDK.valueOf);
                                                        if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                                            map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                                        }
                                                    }
                                                    if (!zAFInAppEventParameterName) {
                                                        try {
                                                            map2.putAll(AFInAppEventType().getLevel().values());
                                                        } catch (Exception e15) {
                                                            AFLogger.afErrorLogForExcManagerOnly("error while getting sensors data", e15);
                                                            StringBuilder sb10 = new StringBuilder("Unexpected exception from AFSensorManager: ");
                                                            sb10.append(e15.getMessage());
                                                            AFLogger.afRDLog(sb10.toString());
                                                        }
                                                    }
                                                    if (values("advertiserId") == null) {
                                                        AFa1cSDK.values(context, map2);
                                                        if (values("advertiserId") != null) {
                                                            z11 = true;
                                                        } else {
                                                            z11 = z10;
                                                        }
                                                        map2.put("GAID_retry", String.valueOf(z11));
                                                    }
                                                    aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                                                    if (aFa1wSDKAFInAppEventParameterName != null) {
                                                        map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                                        map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                                                    }
                                                    map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                                                    int iValueOf5 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                                                    map2.put("counter", Integer.toString(iValueOf5));
                                                    if (str2 != null) {
                                                        z10 = true;
                                                    }
                                                    map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                                                    if (zAFInAppEventParameterName) {
                                                        appsFlyerProperties2.AFInAppEventParameterName = true;
                                                    }
                                                    map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                                                    aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf5);
                                                    map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                                                    if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                                        map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                                                    }
                                                    HashMap map7 = new HashMap();
                                                    map7.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                                                    map7.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                                                    map2.put("cell", map7);
                                                    map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                                                    map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                                                    map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                                                    aFa1cSDK = this.afInfoLog;
                                                    if (aFa1cSDK != null) {
                                                        map2.put("sharing_filter", strArr);
                                                    }
                                                    return map2;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                str2 = str6;
                                                z10 = false;
                                                AFLogger.afErrorLog("Exception while collecting app version data ", th, true);
                                                this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                                                StringBuilder sb11 = new StringBuilder("didConfigureTokenRefreshService=");
                                                sb11.append(this.onInstallConversionFailureNative);
                                                AFLogger.afDebugLog(sb11.toString());
                                                if (!this.onInstallConversionFailureNative) {
                                                    map2.put("tokenRefreshConfigured", Boolean.FALSE);
                                                }
                                                if (zAFInAppEventParameterName) {
                                                    if (this.getLevel != null) {
                                                        if (map2.get("af_deeplink") != null) {
                                                            AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                                        } else {
                                                            JSONObject jSONObject6 = new JSONObject(this.getLevel);
                                                            jSONObject6.put("isPush", "true");
                                                            map2.put("af_deeplink", jSONObject6.toString());
                                                        }
                                                    }
                                                    this.getLevel = null;
                                                    map2.put("open_referrer", aFa1qSDK.valueOf);
                                                    if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                                        map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                                    }
                                                }
                                                if (!zAFInAppEventParameterName) {
                                                    map2.putAll(AFInAppEventType().getLevel().values());
                                                }
                                                if (values("advertiserId") == null) {
                                                    AFa1cSDK.values(context, map2);
                                                    if (values("advertiserId") != null) {
                                                        z11 = true;
                                                    } else {
                                                        z11 = z10;
                                                    }
                                                    map2.put("GAID_retry", String.valueOf(z11));
                                                }
                                                aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                                                if (aFa1wSDKAFInAppEventParameterName != null) {
                                                    map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                                    map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                                                }
                                                map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                                                int iValueOf6 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                                                map2.put("counter", Integer.toString(iValueOf6));
                                                if (str2 != null) {
                                                    z10 = true;
                                                }
                                                map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                                                if (zAFInAppEventParameterName) {
                                                    appsFlyerProperties2.AFInAppEventParameterName = true;
                                                }
                                                map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                                                aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf6);
                                                map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                                                if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                                    map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                                                }
                                                HashMap map8 = new HashMap();
                                                map8.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                                                map8.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                                                map2.put("cell", map8);
                                                map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                                                map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                                                map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                                                aFa1cSDK = this.afInfoLog;
                                                if (aFa1cSDK != null) {
                                                    map2.put("sharing_filter", strArr);
                                                }
                                                return map2;
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            appsFlyerProperties2 = appsFlyerProperties;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        appsFlyerProperties2 = appsFlyerProperties;
                                        str2 = str6;
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                    appsFlyerProperties2 = appsFlyerProperties;
                                }
                                this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                                StringBuilder sb12 = new StringBuilder("didConfigureTokenRefreshService=");
                                sb12.append(this.onInstallConversionFailureNative);
                                AFLogger.afDebugLog(sb12.toString());
                                if (!this.onInstallConversionFailureNative) {
                                    map2.put("tokenRefreshConfigured", Boolean.FALSE);
                                }
                                if (zAFInAppEventParameterName) {
                                    if (this.getLevel != null) {
                                        if (map2.get("af_deeplink") != null) {
                                            AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                        } else {
                                            JSONObject jSONObject7 = new JSONObject(this.getLevel);
                                            jSONObject7.put("isPush", "true");
                                            map2.put("af_deeplink", jSONObject7.toString());
                                        }
                                    }
                                    this.getLevel = null;
                                    map2.put("open_referrer", aFa1qSDK.valueOf);
                                    if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                        map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                    }
                                }
                                if (!zAFInAppEventParameterName) {
                                    map2.putAll(AFInAppEventType().getLevel().values());
                                }
                                if (values("advertiserId") == null) {
                                    AFa1cSDK.values(context, map2);
                                    if (values("advertiserId") != null) {
                                        z11 = true;
                                    } else {
                                        z11 = z10;
                                    }
                                    map2.put("GAID_retry", String.valueOf(z11));
                                }
                                aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                                if (aFa1wSDKAFInAppEventParameterName != null) {
                                    map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                    map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                                }
                                map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                                int iValueOf7 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                                map2.put("counter", Integer.toString(iValueOf7));
                                if (str2 != null) {
                                    z10 = true;
                                }
                                map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                                if (zAFInAppEventParameterName && iValueOf7 == 1) {
                                    appsFlyerProperties2.AFInAppEventParameterName = true;
                                }
                                map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                                aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf7);
                                map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                                if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                    map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                                }
                                HashMap map9 = new HashMap();
                                map9.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                                map9.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                                map2.put("cell", map9);
                                map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                                map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                                map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                                aFa1cSDK = this.afInfoLog;
                                if (aFa1cSDK != null && (strArr = aFa1cSDK.AFInAppEventType) != null) {
                                    map2.put("sharing_filter", strArr);
                                }
                            } else {
                                map2.put("lang", Locale.getDefault().getDisplayLanguage());
                                map2.put("lang_code", Locale.getDefault().getLanguage());
                                map2.put("country", Locale.getDefault().getCountry());
                                aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                                aFe1vSDKAFLogger.AFInAppEventType(map2);
                                SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                                map2.put("installDate", AFInAppEventType(simpleDateFormat6, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                                z10 = false;
                                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                    values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                                }
                                AFb1gSDK aFb1gSDKAFInAppEventParameterName6 = AFInAppEventType().AFInAppEventParameterName();
                                map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                                Context context7 = aFb1gSDKAFInAppEventParameterName6.valueOf.AFInAppEventType;
                                map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context7, context7.getPackageName()));
                                map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName6.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                                long j111 = packageInfo.firstInstallTime;
                                long j112 = packageInfo.lastUpdateTime;
                                appsFlyerProperties2 = appsFlyerProperties;
                                Locale locale6 = Locale.US;
                                str2 = str6;
                                map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale6).format(new Date(j111)));
                                map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale6).format(new Date(j112)));
                                String strValues13 = aFe1vSDKAFLogger.values(simpleDateFormat6);
                                z10 = false;
                                Object[] objArr9 = new Object[1];
                                values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr9);
                                map2.put(((String) objArr9[0]).intern(), strValues13);
                                this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                                StringBuilder sb13 = new StringBuilder("didConfigureTokenRefreshService=");
                                sb13.append(this.onInstallConversionFailureNative);
                                AFLogger.afDebugLog(sb13.toString());
                                if (!this.onInstallConversionFailureNative) {
                                    map2.put("tokenRefreshConfigured", Boolean.FALSE);
                                }
                                if (zAFInAppEventParameterName) {
                                    if (this.getLevel != null) {
                                        if (map2.get("af_deeplink") != null) {
                                            AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                        } else {
                                            JSONObject jSONObject8 = new JSONObject(this.getLevel);
                                            jSONObject8.put("isPush", "true");
                                            map2.put("af_deeplink", jSONObject8.toString());
                                        }
                                    }
                                    this.getLevel = null;
                                    map2.put("open_referrer", aFa1qSDK.valueOf);
                                    if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                        map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                    }
                                }
                                if (!zAFInAppEventParameterName) {
                                    map2.putAll(AFInAppEventType().getLevel().values());
                                }
                                if (values("advertiserId") == null) {
                                    AFa1cSDK.values(context, map2);
                                    if (values("advertiserId") != null) {
                                        z11 = true;
                                    } else {
                                        z11 = z10;
                                    }
                                    map2.put("GAID_retry", String.valueOf(z11));
                                }
                                aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                                if (aFa1wSDKAFInAppEventParameterName != null) {
                                    map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                    map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                                }
                                map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                                int iValueOf8 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                                map2.put("counter", Integer.toString(iValueOf8));
                                if (str2 != null) {
                                    z10 = true;
                                }
                                map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                                if (zAFInAppEventParameterName) {
                                    appsFlyerProperties2.AFInAppEventParameterName = true;
                                }
                                map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                                aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf8);
                                map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                                if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                    map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                                }
                                HashMap map10 = new HashMap();
                                map10.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                                map10.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                                map2.put("cell", map10);
                                map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                                map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                                map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                                aFa1cSDK = this.afInfoLog;
                                if (aFa1cSDK != null) {
                                    map2.put("sharing_filter", strArr);
                                }
                            }
                        } catch (Exception e16) {
                            StringBuilder sb14 = new StringBuilder("ERROR: could not get uid ");
                            sb14.append(e16.getMessage());
                            AFLogger.afErrorLog(sb14.toString(), e16);
                        }
                    } else {
                        aFe1vSDKAFLogger.values(map2, this.AFLogger);
                        strValueOf = AFb1zSDK.valueOf(AFInAppEventType().init(), AFInAppEventType().values());
                        if (strValueOf != null) {
                            map2.put("uid", strValueOf);
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat7 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat7, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName7 = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context8 = aFb1gSDKAFInAppEventParameterName7.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context8, context8.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName7.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j113 = packageInfo.firstInstallTime;
                            long j114 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale7 = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale7).format(new Date(j113)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale7).format(new Date(j114)));
                            String strValues14 = aFe1vSDKAFLogger.values(simpleDateFormat7);
                            z10 = false;
                            Object[] objArr10 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr10);
                            map2.put(((String) objArr10[0]).intern(), strValues14);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb15 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb15.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb15.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject9 = new JSONObject(this.getLevel);
                                        jSONObject9.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject9.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf9 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf9));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf9);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map11 = new HashMap();
                            map11.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map11.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map11);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        } else {
                            map2.put("lang", Locale.getDefault().getDisplayLanguage());
                            map2.put("lang_code", Locale.getDefault().getLanguage());
                            map2.put("country", Locale.getDefault().getCountry());
                            aFe1vSDKAFLogger.AFKeystoreWrapper(map2, zAFInAppEventParameterName);
                            aFe1vSDKAFLogger.AFInAppEventType(map2);
                            SimpleDateFormat simpleDateFormat8 = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
                            map2.put("installDate", AFInAppEventType(simpleDateFormat8, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime));
                            z10 = false;
                            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            if (packageInfo.versionCode > aFb1dSDKValues.values("versionCode", 0)) {
                                values(context).AFInAppEventType("versionCode", packageInfo.versionCode);
                            }
                            AFb1gSDK aFb1gSDKAFInAppEventParameterName8 = AFInAppEventType().AFInAppEventParameterName();
                            map2.put("app_version_code", Integer.toString(packageInfo.versionCode));
                            Context context9 = aFb1gSDKAFInAppEventParameterName8.valueOf.AFInAppEventType;
                            map2.put("app_version_name", AFa1fSDK.AFKeystoreWrapper(context9, context9.getPackageName()));
                            map2.put("targetSDKver", Integer.valueOf(aFb1gSDKAFInAppEventParameterName8.valueOf.AFInAppEventType.getApplicationInfo().targetSdkVersion));
                            long j115 = packageInfo.firstInstallTime;
                            long j116 = packageInfo.lastUpdateTime;
                            appsFlyerProperties2 = appsFlyerProperties;
                            Locale locale8 = Locale.US;
                            str2 = str6;
                            map2.put("date1", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale8).format(new Date(j115)));
                            map2.put("date2", new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", locale8).format(new Date(j116)));
                            String strValues15 = aFe1vSDKAFLogger.values(simpleDateFormat8);
                            z10 = false;
                            Object[] objArr11 = new Object[1];
                            values((Process.getElapsedCpuTime() > 0L ? 1 : (Process.getElapsedCpuTime() == 0L ? 0 : -1)) + 3, "\r\f\u0003\u0000\uffff\u000e\ufffb\uffde\u0002�\b\u000f\ufffb￦\u000e", KeyEvent.keyCodeFromString("") + 241, 15 - TextUtils.indexOf("", "", 0), true, objArr11);
                            map2.put(((String) objArr11[0]).intern(), strValues15);
                            this.onInstallConversionFailureNative = AFe1ySDK.values(context);
                            StringBuilder sb16 = new StringBuilder("didConfigureTokenRefreshService=");
                            sb16.append(this.onInstallConversionFailureNative);
                            AFLogger.afDebugLog(sb16.toString());
                            if (!this.onInstallConversionFailureNative) {
                                map2.put("tokenRefreshConfigured", Boolean.FALSE);
                            }
                            if (zAFInAppEventParameterName) {
                                if (this.getLevel != null) {
                                    if (map2.get("af_deeplink") != null) {
                                        AFLogger.afDebugLog("Skip 'af' payload as deeplink was found by path");
                                    } else {
                                        JSONObject jSONObject10 = new JSONObject(this.getLevel);
                                        jSONObject10.put("isPush", "true");
                                        map2.put("af_deeplink", jSONObject10.toString());
                                    }
                                }
                                this.getLevel = null;
                                map2.put("open_referrer", aFa1qSDK.valueOf);
                                if (!AFb1uSDK.AFInAppEventType(aFa1qSDK.afInfoLog)) {
                                    map2.put("af_web_referrer", aFa1qSDK.afInfoLog);
                                }
                            }
                            if (!zAFInAppEventParameterName) {
                                map2.putAll(AFInAppEventType().getLevel().values());
                            }
                            if (values("advertiserId") == null) {
                                AFa1cSDK.values(context, map2);
                                if (values("advertiserId") != null) {
                                    z11 = true;
                                } else {
                                    z11 = z10;
                                }
                                map2.put("GAID_retry", String.valueOf(z11));
                            }
                            aFa1wSDKAFInAppEventParameterName = AFa1cSDK.AFInAppEventParameterName(context.getContentResolver());
                            if (aFa1wSDKAFInAppEventParameterName != null) {
                                map2.put("amazon_aid", aFa1wSDKAFInAppEventParameterName.valueOf);
                                map2.put("amazon_aid_limit", String.valueOf(aFa1wSDKAFInAppEventParameterName.AFInAppEventType));
                            }
                            map2.put("registeredUninstall", Boolean.valueOf(AFe1ySDK.AFInAppEventType(aFb1dSDKValues)));
                            int iValueOf10 = valueOf(aFb1dSDKValues, zAFInAppEventParameterName);
                            map2.put("counter", Integer.toString(iValueOf10));
                            if (str2 != null) {
                                z10 = true;
                            }
                            map2.put("iaecounter", Integer.toString(values(aFb1dSDKValues, z10)));
                            if (zAFInAppEventParameterName) {
                                appsFlyerProperties2.AFInAppEventParameterName = true;
                            }
                            map2.put("isFirstCall", Boolean.toString(!aFe1vSDKAFLogger.afDebugLog()));
                            aFe1vSDKAFLogger.AFInAppEventType(zAFInAppEventParameterName, map2, iValueOf10);
                            map2.put("ivc", Boolean.valueOf(aFe1vSDKAFLogger.afErrorLog()));
                            if (aFb1dSDKValues.values("is_stop_tracking_used")) {
                                map2.put("istu", String.valueOf(aFb1dSDKValues.valueOf("is_stop_tracking_used")));
                            }
                            HashMap map12 = new HashMap();
                            map12.put("mcc", Integer.valueOf(context.getResources().getConfiguration().mcc));
                            map12.put("mnc", Integer.valueOf(context.getResources().getConfiguration().mnc));
                            map2.put("cell", map12);
                            map2.put("sig", aFe1vSDKAFLogger.AFKeystoreWrapper());
                            map2.put("last_boot_time", Long.valueOf(aFe1vSDKAFLogger.values()));
                            map2.put("disk", aFe1vSDKAFLogger.AFInAppEventParameterName());
                            aFa1cSDK = this.afInfoLog;
                            if (aFa1cSDK != null) {
                                map2.put("sharing_filter", strArr);
                            }
                        }
                    }
                }
            } catch (Exception e17) {
                AFLogger.afErrorLog("Exception while validation permissions. ", e17);
            }
        } catch (Throwable th9) {
            AFLogger.afErrorLog(th9.getLocalizedMessage(), th9, true);
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void valueOf(AFc1xSDK aFc1xSDK) {
        onAttributionFailure = (onDeepLinking + 87) % 128;
        aFc1xSDK.onAppOpenAttributionNative().AFInAppEventType();
        int i10 = onDeepLinking + 53;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    private static boolean valueOf(String str, boolean z10) {
        int i10 = onDeepLinking + 21;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            AppsFlyerProperties.getInstance().getBoolean(str, z10);
            throw null;
        }
        boolean z11 = AppsFlyerProperties.getInstance().getBoolean(str, z10);
        int i11 = onAttributionFailure + 117;
        onDeepLinking = i11 % 128;
        if (i11 % 2 == 0) {
            int i12 = 36 / 0;
        }
        return z11;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
    
        r3 = values(r0);
        com.appsflyer.AppsFlyerProperties.getInstance().saveProperties(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        if (AFInAppEventType().afWarnLog().AFKeystoreWrapper() != false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
    
        r4 = new java.lang.StringBuilder("sendWithEvent from activity: ");
        r4.append(r0.getClass().getName());
        com.appsflyer.AFLogger.afInfoLog(r4.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0061, code lost:
    
        r0 = r12.AFInAppEventParameterName();
        r4 = AFInAppEventParameterName(r12);
        r5 = (java.lang.String) r4.get("appsflyerKey");
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0071, code lost:
    
        if (r5 == null) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0077, code lost:
    
        if (r5.length() != 0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007f, code lost:
    
        if (isStopped() != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0081, code lost:
    
        com.appsflyer.AFLogger.afInfoLog("AppsFlyerLib.sendWithEvent");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0086, code lost:
    
        r3 = valueOf(r3, false);
        r5 = new com.appsflyer.internal.AFf1fSDK(AFInAppEventType().AFInAppEventParameterName());
        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, "");
        r6 = r12.AFInAppEventParameterName();
        r7 = r12 instanceof com.appsflyer.internal.AFe1oSDK;
        r8 = r12 instanceof com.appsflyer.internal.AFe1kSDK;
        r9 = r12 instanceof com.appsflyer.internal.AFe1gSDK;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a8, code lost:
    
        if ((r12 instanceof com.appsflyer.internal.AFe1lSDK) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00aa, code lost:
    
        if (r9 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ae, code lost:
    
        if (r8 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b0, code lost:
    
        r1 = com.appsflyer.internal.AFf1fSDK.afErrorLogForExcManagerOnly;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b2, code lost:
    
        if (r1 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b4, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.valueOf);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00be, code lost:
    
        if (r7 == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c0, code lost:
    
        r1 = com.appsflyer.internal.AFf1fSDK.getLevel;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00c2, code lost:
    
        if (r1 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00c4, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.AFInAppEventParameterName);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ce, code lost:
    
        if (r6 == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d0, code lost:
    
        if (r3 >= 2) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d2, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 93) % 128;
        r6 = com.appsflyer.internal.AFf1fSDK.AppsFlyer2dXConversionCallback;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00dc, code lost:
    
        if (r6 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00de, code lost:
    
        r6 = com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 49;
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = r6 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e7, code lost:
    
        if ((r6 % 2) == 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e9, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.afRDLog);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f2, code lost:
    
        r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.afRDLog);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fa, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00fb, code lost:
    
        r1 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00fd, code lost:
    
        r1 = com.appsflyer.internal.AFf1fSDK.onAppOpenAttributionNative;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ff, code lost:
    
        if (r1 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0101, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.afErrorLog);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010a, code lost:
    
        r1 = com.appsflyer.internal.AFf1fSDK.onInstallConversionDataLoadedNative;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x010c, code lost:
    
        if (r1 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x010e, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.AFLogger);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0117, code lost:
    
        r6 = com.appsflyer.internal.AFf1fSDK.afWarnLog;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0119, code lost:
    
        if (r6 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011b, code lost:
    
        r6 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 73;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r6 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0124, code lost:
    
        if ((r6 % 2) == 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0126, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.AFKeystoreWrapper);
        r6 = 98 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0132, code lost:
    
        r1 = r5.AFInAppEventType.valueOf(com.appsflyer.internal.AFf1fSDK.AFKeystoreWrapper);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x013a, code lost:
    
        r6 = new java.lang.StringBuilder();
        r6.append(r1);
        r6.append(r5.values.valueOf.AFInAppEventType.getPackageName());
        r1 = r5.AFInAppEventParameterName(com.appsflyer.internal.AFf1fSDK.AFInAppEventParameterName(r6.toString(), r7));
        AFInAppEventParameterName(r4);
        r5 = new com.appsflyer.internal.AFa1eSDK.AFa1ySDK(r11, r12.AFKeystoreWrapper(r1).values(r4).AFInAppEventParameterName(r3), r2 ? 1 : 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x016f, code lost:
    
        if (r0 == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0171, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = (com.appsflyer.internal.AFa1eSDK.onDeepLinking + 43) % 128;
        r12 = AFVersionDeclaration();
        r0 = r12.length;
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001a, code lost:
    
        if (r0 == null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0180, code lost:
    
        if (r2 >= r0) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0182, code lost:
    
        r4 = r12[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0188, code lost:
    
        if (r4.afDebugLog != com.appsflyer.internal.AFf1pSDK.AFa1zSDK.STARTED) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x018a, code lost:
    
        r1 = new java.lang.StringBuilder("Failed to get ");
        r1.append(r4.AFKeystoreWrapper);
        r1.append(" referrer, wait ...");
        com.appsflyer.AFLogger.afDebugLog(r1.toString());
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a3, code lost:
    
        r2 = r2 + 1;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01a8, code lost:
    
        if (r11.onDeepLinkingNative == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01aa, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = (com.appsflyer.internal.AFa1eSDK.onDeepLinking + 27) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01b6, code lost:
    
        if (AFLogger$LogLevel() != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x01b8, code lost:
    
        com.appsflyer.AFLogger.afDebugLog("fetching Facebook deferred AppLink data, wait ...");
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x01bf, code lost:
    
        r2 = r1 ? 1 : 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x01c0, code lost:
    
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01cc, code lost:
    
        if (AFInAppEventType().afWarnLog().AFInAppEventType() == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x01ce, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 123) % 128;
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x01d7, code lost:
    
        r12 = AFInAppEventType().valueOf();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01df, code lost:
    
        if (r2 == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01e1, code lost:
    
        r0 = 500;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x01e4, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01e6, code lost:
    
        AFInAppEventParameterName(r12, r5, r0, java.util.concurrent.TimeUnit.MILLISECONDS);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01eb, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01ec, code lost:
    
        com.appsflyer.AFLogger.afDebugLog("Not sending data yet, waiting for dev key");
        r12 = r12.AFInAppEventParameterName;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01f3, code lost:
    
        if (r12 == null) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01f5, code lost:
    
        r12.onError(41, "No dev key");
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01fc, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0027, code lost:
    
        if (r0 == null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
    
        com.appsflyer.AFLogger.afDebugLog("sendWithEvent - got null context. skipping event/launch.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void valueOf(com.appsflyer.internal.AFa1qSDK r12) {
        /*
            Method dump skipped, instruction units count: 509
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.valueOf(com.appsflyer.internal.AFa1qSDK):void");
    }

    public final AFb1dSDK values(Context context) {
        onAttributionFailure = (onDeepLinking + 19) % 128;
        AFKeystoreWrapper(context);
        AFb1dSDK aFb1dSDKValues = AFInAppEventType().values();
        onAttributionFailure = (onDeepLinking + 33) % 128;
        return aFb1dSDKValues;
    }

    private int values(AFb1dSDK aFb1dSDK, boolean z10) {
        int i10 = onDeepLinking + 119;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            return AFKeystoreWrapper(aFb1dSDK, "appsFlyerInAppEventCount", z10);
        }
        AFKeystoreWrapper(aFb1dSDK, "appsFlyerInAppEventCount", z10);
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00ca A[Catch: all -> 0x00c6, TRY_LEAVE, TryCatch #1 {all -> 0x00c6, blocks: (B:45:0x00c2, B:49:0x00ca), top: B:55:0x00c2 }] */
    @NonNull
    @Deprecated
    public static String values(HttpURLConnection httpURLConnection) {
        InputStreamReader inputStreamReader;
        StringBuilder sb2 = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                InputStream errorStream = httpURLConnection.getErrorStream();
                if (errorStream == null) {
                    errorStream = httpURLConnection.getInputStream();
                }
                inputStreamReader = new InputStreamReader(errorStream, Charset.defaultCharset());
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                    onDeepLinking = (onAttributionFailure + 99) % 128;
                    boolean z10 = false;
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line != null) {
                                int i10 = onAttributionFailure + 111;
                                onDeepLinking = i10 % 128;
                                if (i10 % 2 == 0) {
                                    throw null;
                                }
                                sb2.append(z10 ? '\n' : "");
                                sb2.append(line);
                                z10 = true;
                            } else {
                                bufferedReader2.close();
                                inputStreamReader.close();
                                onAttributionFailure = (onDeepLinking + 97) % 128;
                                break;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            try {
                                StringBuilder sb3 = new StringBuilder("Could not read connection response from: ");
                                sb3.append(httpURLConnection.getURL().toString());
                                AFLogger.afErrorLog(sb3.toString(), th);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                            } catch (Throwable th3) {
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                        if (inputStreamReader != null) {
                                            inputStreamReader.close();
                                        }
                                    } catch (Throwable th4) {
                                        AFLogger.afErrorLogForExcManagerOnly("readServerResponse error", th4);
                                        throw th3;
                                    }
                                } else if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                throw th3;
                            }
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
                inputStreamReader = null;
            }
        } catch (Throwable th7) {
            AFLogger.afErrorLogForExcManagerOnly("readServerResponse error", th7);
        }
        String string = sb2.toString();
        try {
            new JSONObject(string);
            return string;
        } catch (JSONException e10) {
            AFLogger.afErrorLogForExcManagerOnly("error while parsing readServerResponse", e10);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("string_response", string);
                return jSONObject.toString();
            } catch (JSONException e11) {
                AFLogger.afErrorLogForExcManagerOnly("RESPONSE_NOT_JSON error", e11);
                return new JSONObject().toString();
            }
        }
    }

    private static void AFInAppEventType(String str, String str2) {
        int i10 = onDeepLinking + 43;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            AppsFlyerProperties.getInstance().set(str, str2);
        } else {
            AppsFlyerProperties.getInstance().set(str, str2);
            throw null;
        }
    }

    private static void AFInAppEventType(String str, boolean z10) {
        int i10 = onDeepLinking + 39;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            AppsFlyerProperties.getInstance().set(str, z10);
        } else {
            AppsFlyerProperties.getInstance().set(str, z10);
            int i11 = 70 / 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x0064  */
    /* JADX WARN: Code duplicated, block: B:9:0x004f A[PHI: r0 r3
      0x004f: PHI (r0v6 boolean) = (r0v5 boolean), (r0v10 boolean) binds: [B:8:0x004d, B:5:0x002e] A[DONT_GENERATE, DONT_INLINE]
      0x004f: PHI (r3v2 int) = (r3v1 int), (r3v4 int) binds: [B:8:0x004d, B:5:0x002e] A[DONT_GENERATE, DONT_INLINE]] */
    public /* synthetic */ void AFInAppEventType(AFf1qSDK aFf1qSDK) {
        int iValues;
        boolean zValueOf;
        int i10 = onAttributionFailure + 123;
        onDeepLinking = i10 % 128;
        boolean z10 = false;
        if (i10 % 2 == 0) {
            AFb1dSDK aFb1dSDKValues = values(this.init);
            iValues = AFInAppEventType().AFInAppEventParameterName().AFInAppEventType.values("appsFlyerCount", 0);
            zValueOf = aFb1dSDKValues.valueOf(AppsFlyerProperties.NEW_REFERRER_SENT);
            if (aFf1qSDK.afDebugLog == AFf1pSDK.AFa1zSDK.NOT_STARTED) {
                z10 = true;
            }
        } else {
            AFb1dSDK aFb1dSDKValues2 = values(this.init);
            iValues = AFInAppEventType().AFInAppEventParameterName().AFInAppEventType.values("appsFlyerCount", 0);
            zValueOf = aFb1dSDKValues2.valueOf(AppsFlyerProperties.NEW_REFERRER_SENT);
            if (aFf1qSDK.afDebugLog == AFf1pSDK.AFa1zSDK.NOT_STARTED) {
                z10 = true;
            }
        }
        if (iValues == 1) {
            int i11 = (onAttributionFailure + 99) % 128;
            onDeepLinking = i11;
            if (z10) {
                valueOf(new AFe1lSDK());
            } else {
                onAttributionFailure = (i11 + 35) % 128;
                if (zValueOf) {
                    valueOf(new AFe1lSDK());
                }
            }
        }
        onAttributionFailure = (onDeepLinking + 113) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0030  */
    /* JADX WARN: Code duplicated, block: B:9:0x0025  */
    private void AFKeystoreWrapper(Context context, Map<String, Object> map, AFa1qSDK aFa1qSDK) {
        Activity activity;
        int i10 = onAttributionFailure + 71;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            AFKeystoreWrapper(context);
            aFa1qSDK.values(map);
            int i11 = 19 / 0;
            if (context instanceof Activity) {
                onDeepLinking = (onAttributionFailure + 77) % 128;
                activity = (Activity) context;
            } else {
                activity = null;
            }
        } else {
            AFKeystoreWrapper(context);
            aFa1qSDK.values(map);
            if (context instanceof Activity) {
                onDeepLinking = (onAttributionFailure + 77) % 128;
                activity = (Activity) context;
            } else {
                activity = null;
            }
        }
        values(aFa1qSDK, activity);
    }

    private void AFInAppEventType(Context context) {
        this.onResponseErrorNative = new HashMap();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        final AFa1uSDK.AFa1zSDK aFa1zSDK = new AFa1uSDK.AFa1zSDK() { // from class: com.appsflyer.internal.AFa1eSDK.2
            @Override // com.appsflyer.internal.AFa1uSDK.AFa1zSDK
            public final void AFKeystoreWrapper(String str) {
                AFa1eSDK.values(AFa1eSDK.this).put("error", str);
            }

            @Override // com.appsflyer.internal.AFa1uSDK.AFa1zSDK
            public final void valueOf(String str, String str2, String str3) {
                if (str != null) {
                    AFLogger.afInfoLog("Facebook Deferred AppLink data received: ".concat(str));
                    AFa1eSDK.values(AFa1eSDK.this).put("link", str);
                    if (str2 != null) {
                        AFa1eSDK.values(AFa1eSDK.this).put("target_url", str2);
                    }
                    if (str3 != null) {
                        HashMap map = new HashMap();
                        HashMap map2 = new HashMap();
                        map2.put("promo_code", str3);
                        map.put("deeplink_context", map2);
                        AFa1eSDK.values(AFa1eSDK.this).put(PushProcessor.DATAKEY_EXTRAS, map);
                    }
                } else {
                    AFa1eSDK.values(AFa1eSDK.this).put("link", "");
                }
                AFa1eSDK.values(AFa1eSDK.this).put("ttr", String.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            }
        };
        try {
            Class.forName("com.facebook.FacebookSdk").getMethod("sdkInitialize", Context.class).invoke(null, context);
            final Class<?> cls = Class.forName("com.facebook.applinks.AppLinkData");
            Class<?> cls2 = Class.forName("com.facebook.applinks.AppLinkData$CompletionHandler");
            Method method = cls.getMethod("fetchDeferredAppLinkData", Context.class, String.class, cls2);
            Object objNewProxyInstance = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new InvocationHandler() { // from class: com.appsflyer.internal.AFa1uSDK.3
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method2, Object[] objArr) throws Throwable {
                    String string;
                    String string2;
                    String string3;
                    Bundle bundle;
                    if (!method2.getName().equals("onDeferredAppLinkDataFetched")) {
                        AFa1zSDK aFa1zSDK2 = aFa1zSDK;
                        if (aFa1zSDK2 != null) {
                            aFa1zSDK2.AFKeystoreWrapper("onDeferredAppLinkDataFetched invocation failed");
                        }
                        return null;
                    }
                    Object obj2 = objArr[0];
                    if (obj2 != null) {
                        Bundle bundle2 = (Bundle) Bundle.class.cast(cls.getMethod("getArgumentBundle", null).invoke(cls.cast(obj2), null));
                        if (bundle2 != null) {
                            string2 = bundle2.getString("com.facebook.platform.APPLINK_NATIVE_URL");
                            string3 = bundle2.getString("target_url");
                            Bundle bundle3 = bundle2.getBundle(PushProcessor.DATAKEY_EXTRAS);
                            string = (bundle3 == null || (bundle = bundle3.getBundle("deeplink_context")) == null) ? null : bundle.getString("promo_code");
                        } else {
                            string = null;
                            string2 = null;
                            string3 = null;
                        }
                        AFa1zSDK aFa1zSDK3 = aFa1zSDK;
                        if (aFa1zSDK3 != null) {
                            aFa1zSDK3.valueOf(string2, string3, string);
                        }
                    } else {
                        AFa1zSDK aFa1zSDK4 = aFa1zSDK;
                        if (aFa1zSDK4 != null) {
                            aFa1zSDK4.valueOf(null, null, null);
                        }
                    }
                    return null;
                }
            });
            String string = context.getString(context.getResources().getIdentifier("facebook_app_id", VariableConstants.TYPE_STRING, context.getPackageName()));
            if (!TextUtils.isEmpty(string)) {
                method.invoke(null, context, string, objNewProxyInstance);
                onDeepLinking = (onAttributionFailure + 3) % 128;
            } else {
                onDeepLinking = (onAttributionFailure + 93) % 128;
                aFa1zSDK.AFKeystoreWrapper("Facebook app id not defined in resources");
            }
        } catch (ClassNotFoundException e10) {
            AFLogger.afErrorLogForExcManagerOnly("FB class missing error", e10);
            aFa1zSDK.AFKeystoreWrapper(e10.toString());
        } catch (IllegalAccessException e11) {
            AFLogger.afErrorLogForExcManagerOnly("FB illegal access", e11);
            aFa1zSDK.AFKeystoreWrapper(e11.toString());
        } catch (NoSuchMethodException e12) {
            AFLogger.afErrorLogForExcManagerOnly("FB method missing error", e12);
            aFa1zSDK.AFKeystoreWrapper(e12.toString());
        } catch (InvocationTargetException e13) {
            AFLogger.afErrorLogForExcManagerOnly("FB invocation error", e13);
            aFa1zSDK.AFKeystoreWrapper(e13.toString());
        }
    }

    private void AFKeystoreWrapper(AppsFlyerConversionListener appsFlyerConversionListener) {
        onAttributionFailure = (onDeepLinking + 99) % 128;
        if (appsFlyerConversionListener == null) {
            return;
        }
        this.AFInAppEventParameterName = appsFlyerConversionListener;
        onAttributionFailure = (onDeepLinking + 103) % 128;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002e  */
    /* JADX WARN: Code duplicated, block: B:13:0x0034  */
    @VisibleForTesting
    private void AFKeystoreWrapper(Context context, String str, Map<String, Object> map, String str2, String str3) {
        AFa1qSDK aFe1hSDK;
        int i10 = onAttributionFailure;
        onDeepLinking = (i10 + 27) % 128;
        if (str != null) {
            int i11 = i10 + 117;
            onDeepLinking = i11 % 128;
            if (i11 % 2 == 0) {
                int i12 = 84 / 0;
                if (!str.trim().isEmpty()) {
                    aFe1hSDK = new AFe1iSDK();
                } else {
                    aFe1hSDK = new AFe1hSDK();
                }
            } else if (!str.trim().isEmpty()) {
                aFe1hSDK = new AFe1iSDK();
            } else {
                aFe1hSDK = new AFe1hSDK();
            }
        } else {
            aFe1hSDK = new AFe1hSDK();
        }
        AFKeystoreWrapper(context);
        aFe1hSDK.afDebugLog = str;
        aFe1hSDK.values = map;
        aFe1hSDK.afErrorLog = str2;
        aFe1hSDK.valueOf = str3;
        AFInAppEventType(aFe1hSDK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void values(Context context, Intent intent) {
        Uri data;
        onDeepLinking = (onAttributionFailure + 67) % 128;
        AFKeystoreWrapper(context);
        AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback = AFInAppEventType().AppsFlyer2dXConversionCallback();
        AFb1dSDK aFb1dSDKValues = AFInAppEventType().values();
        if (intent == null || !CommonConstant.ACTION.HWID_SCHEME_URL.equals(intent.getAction())) {
            data = null;
        } else {
            int i10 = onDeepLinking + 35;
            onAttributionFailure = i10 % 128;
            if (i10 % 2 != 0) {
                intent.getData();
                throw null;
            }
            data = intent.getData();
        }
        boolean z10 = false;
        if (data != null && !data.toString().isEmpty()) {
            int i11 = onDeepLinking + 115;
            onAttributionFailure = i11 % 128;
            if (i11 % 2 == 0) {
                z10 = true;
            }
        }
        if (aFb1dSDKValues.valueOf("ddl_sent") && !z10) {
            aFb1sSDKAppsFlyer2dXConversionCallback.AFKeystoreWrapper("No direct deep link", null);
        } else {
            aFb1sSDKAppsFlyer2dXConversionCallback.valueOf(new HashMap(), intent, context);
        }
    }

    private void AFKeystoreWrapper(Context context, String str) {
        byte b10 = 0;
        AFa1qSDK aFa1qSDKAFInAppEventParameterName = new AFe1gSDK().AFInAppEventParameterName(AFInAppEventType().AFInAppEventParameterName().AFInAppEventType.values("appsFlyerCount", 0));
        aFa1qSDKAFInAppEventParameterName.afErrorLog = str;
        if (str != null && str.length() > 5 && AFInAppEventParameterName(aFa1qSDKAFInAppEventParameterName, values(context))) {
            AFInAppEventParameterName(AFInAppEventType().valueOf(), new AFa1vSDK(this, aFa1qSDKAFInAppEventParameterName, b10), 5L, TimeUnit.MILLISECONDS);
            onDeepLinking = (onAttributionFailure + 63) % 128;
        }
        int i10 = onAttributionFailure + 61;
        onDeepLinking = i10 % 128;
        if (i10 % 2 == 0) {
            int i11 = 80 / 0;
        }
    }

    private static void values(int i10, String str, int i11, int i12, boolean z10, Object[] objArr) {
        String str2;
        Object charArray = str;
        if (str != null) {
            charArray = str.toCharArray();
        }
        char[] cArr = (char[]) charArray;
        synchronized (AFg1mSDK.AFKeystoreWrapper) {
            try {
                char[] cArr2 = new char[i12];
                AFg1mSDK.AFInAppEventParameterName = 0;
                while (true) {
                    int i13 = AFg1mSDK.AFInAppEventParameterName;
                    if (i13 >= i12) {
                        break;
                    }
                    AFg1mSDK.values = cArr[i13];
                    cArr2[AFg1mSDK.AFInAppEventParameterName] = (char) (AFg1mSDK.values + i11);
                    int i14 = AFg1mSDK.AFInAppEventParameterName;
                    cArr2[i14] = (char) (cArr2[i14] - onResponse);
                    AFg1mSDK.AFInAppEventParameterName = i14 + 1;
                }
                if (i10 > 0) {
                    AFg1mSDK.valueOf = i10;
                    char[] cArr3 = new char[i12];
                    System.arraycopy(cArr2, 0, cArr3, 0, i12);
                    int i15 = AFg1mSDK.valueOf;
                    System.arraycopy(cArr3, 0, cArr2, i12 - i15, i15);
                    int i16 = AFg1mSDK.valueOf;
                    System.arraycopy(cArr3, i16, cArr2, 0, i12 - i16);
                }
                if (z10) {
                    char[] cArr4 = new char[i12];
                    AFg1mSDK.AFInAppEventParameterName = 0;
                    while (true) {
                        int i17 = AFg1mSDK.AFInAppEventParameterName;
                        if (i17 >= i12) {
                            break;
                        }
                        cArr4[i17] = cArr2[(i12 - i17) - 1];
                        AFg1mSDK.AFInAppEventParameterName = i17 + 1;
                    }
                    cArr2 = cArr4;
                }
                str2 = new String(cArr2);
            } catch (Throwable th2) {
                throw th2;
            }
        }
        objArr[0] = str2;
    }

    private AFd1nSDK.AFa1xSDK AFKeystoreWrapper(final Map<String, String> map) {
        AFd1nSDK.AFa1xSDK aFa1xSDK = new AFd1nSDK.AFa1xSDK() { // from class: com.appsflyer.internal.AFa1eSDK.4
            @Override // com.appsflyer.internal.AFd1nSDK.AFa1xSDK
            public final void AFKeystoreWrapper(String str) {
                AFa1eSDK.this.AFInAppEventType().AppsFlyer2dXConversionCallback().AFKeystoreWrapper(str, DeepLinkResult.Error.NETWORK);
            }

            @Override // com.appsflyer.internal.AFd1nSDK.AFa1xSDK
            public final void values(Map<String, String> map2) {
                for (String str : map2.keySet()) {
                    map.put(str, map2.get(str));
                }
                AFa1eSDK.this.AFInAppEventType().AppsFlyer2dXConversionCallback().AFInAppEventType(map);
            }
        };
        int i10 = onDeepLinking + 25;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 == 0) {
            return aFa1xSDK;
        }
        throw null;
    }

    private static void AFKeystoreWrapper(String str) {
        try {
            if (new JSONObject(str).has("pid")) {
                int i10 = onAttributionFailure + 21;
                onDeepLinking = i10 % 128;
                if (i10 % 2 == 0) {
                    AFInAppEventType("preInstallName", str);
                    int i11 = 83 / 0;
                    return;
                } else {
                    AFInAppEventType("preInstallName", str);
                    return;
                }
            }
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
        } catch (JSONException e10) {
            AFLogger.afErrorLog("Error parsing JSON for preinstall", e10);
        }
    }

    private void AFInAppEventType(Context context, AFe1nSDK aFe1nSDK) {
        AFKeystoreWrapper(context);
        AFe1mSDK aFe1mSDKAfDebugLog = AFInAppEventType().afDebugLog();
        AFe1tSDK aFe1tSDKAFInAppEventParameterName = AFa1rSDK.AFInAppEventParameterName(context);
        if (aFe1mSDKAfDebugLog.valueOf()) {
            onDeepLinking = (onAttributionFailure + 27) % 128;
            aFe1mSDKAfDebugLog.AFInAppEventParameterName.put("api_name", aFe1nSDK.toString());
            aFe1mSDKAfDebugLog.AFKeystoreWrapper(aFe1tSDKAFInAppEventParameterName);
            onDeepLinking = (onAttributionFailure + 23) % 128;
        }
        aFe1mSDKAfDebugLog.values();
    }

    private int AFKeystoreWrapper(AFb1dSDK aFb1dSDK, String str, boolean z10) {
        int iValues = aFb1dSDK.values(str, 0);
        if (z10) {
            int i10 = onAttributionFailure + 15;
            onDeepLinking = i10 % 128;
            iValues = i10 % 2 == 0 ? iValues + 59 : iValues + 1;
            aFb1dSDK.AFInAppEventType(str, iValues);
        }
        if (!AFInAppEventType().afErrorLogForExcManagerOnly().afInfoLog()) {
            return iValues;
        }
        int i11 = onDeepLinking + 5;
        onAttributionFailure = i11 % 128;
        if (i11 % 2 == 0) {
            AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName(String.valueOf(iValues));
            return iValues;
        }
        AFInAppEventType().afErrorLogForExcManagerOnly().AFInAppEventParameterName(String.valueOf(iValues));
        throw null;
    }

    private void AFInAppEventType(Context context, String str, Map<String, Object> map) {
        Activity activity;
        AFe1iSDK aFe1iSDK = new AFe1iSDK();
        aFe1iSDK.afDebugLog = str;
        aFe1iSDK.values = map;
        if (context instanceof Activity) {
            onDeepLinking = (onAttributionFailure + 103) % 128;
            activity = (Activity) context;
        } else {
            onAttributionFailure = (onDeepLinking + 125) % 128;
            activity = null;
        }
        values(aFe1iSDK, activity);
        onAttributionFailure = (onDeepLinking + 9) % 128;
    }

    public final void AFKeystoreWrapper(@NonNull Context context) {
        int i10 = onAttributionFailure + 39;
        onDeepLinking = i10 % 128;
        if (i10 % 2 != 0) {
            AFc1ySDK aFc1ySDK = this.onConversionDataFail;
            if (context != null) {
                AFb1bSDK aFb1bSDK = aFc1ySDK.valueOf;
                if (context != null) {
                    aFb1bSDK.AFInAppEventType = context.getApplicationContext();
                    onAttributionFailure = (onDeepLinking + 91) % 128;
                    return;
                }
                return;
            }
            return;
        }
        throw null;
    }

    public final void valueOf(Context context, Map<String, Object> map, Uri uri) {
        AFKeystoreWrapper(context);
        if (!map.containsKey("af_deeplink")) {
            int i10 = onAttributionFailure + 29;
            onDeepLinking = i10 % 128;
            if (i10 % 2 != 0) {
                String strAFInAppEventType = AFInAppEventType(uri.toString());
                AFb1sSDK aFb1sSDKAppsFlyer2dXConversionCallback = AFInAppEventType().AppsFlyer2dXConversionCallback();
                String str = aFb1sSDKAppsFlyer2dXConversionCallback.AFKeystoreWrapper;
                if (str != null && aFb1sSDKAppsFlyer2dXConversionCallback.values != null && strAFInAppEventType.contains(str)) {
                    Uri.Builder builderBuildUpon = Uri.parse(strAFInAppEventType).buildUpon();
                    Uri.Builder builderBuildUpon2 = Uri.EMPTY.buildUpon();
                    Iterator<Map.Entry<String, String>> it = aFb1sSDKAppsFlyer2dXConversionCallback.values.entrySet().iterator();
                    int i11 = onDeepLinking + 115;
                    while (true) {
                        onAttributionFailure = i11 % 128;
                        if (!it.hasNext()) {
                            break;
                        }
                        onAttributionFailure = (onDeepLinking + 23) % 128;
                        Map.Entry<String, String> next = it.next();
                        builderBuildUpon.appendQueryParameter(next.getKey(), next.getValue());
                        builderBuildUpon2.appendQueryParameter(next.getKey(), next.getValue());
                        i11 = onDeepLinking + 93;
                    }
                    strAFInAppEventType = builderBuildUpon.build().toString();
                    map.put("appended_query_params", builderBuildUpon2.build().getEncodedQuery());
                }
                map.put("af_deeplink", strAFInAppEventType);
            } else {
                AFInAppEventType(uri.toString());
                String str2 = AFInAppEventType().AppsFlyer2dXConversionCallback().AFKeystoreWrapper;
                throw null;
            }
        }
        HashMap map2 = new HashMap();
        map2.put("link", uri.toString());
        AFd1nSDK aFd1nSDK = new AFd1nSDK(AFInAppEventType(), UUID.randomUUID(), uri);
        if (aFd1nSDK.afWarnLog()) {
            map.put("isBrandedDomain", Boolean.TRUE);
        }
        AFa1fSDK.AFInAppEventType(context, map2, uri);
        if (aFd1nSDK.AFVersionDeclaration()) {
            aFd1nSDK.afInfoLog = AFKeystoreWrapper(map2);
            AFc1bSDK aFc1bSDKAFVersionDeclaration = AFInAppEventType().AFVersionDeclaration();
            aFc1bSDKAFVersionDeclaration.AFKeystoreWrapper.execute(aFc1bSDKAFVersionDeclaration.new AnonymousClass4(aFd1nSDK));
            return;
        }
        AFInAppEventType().AppsFlyer2dXConversionCallback().AFInAppEventType(map2);
    }

    public static String AFInAppEventType(SimpleDateFormat simpleDateFormat, long j10) {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = simpleDateFormat.format(new Date(j10));
        onDeepLinking = (onAttributionFailure + 43) % 128;
        return str;
    }

    private void AFInAppEventType(AFa1qSDK aFa1qSDK) {
        onDeepLinking = (onAttributionFailure + 95) % 128;
        byte b10 = 0;
        boolean z10 = aFa1qSDK.afDebugLog == null;
        if (AFKeystoreWrapper()) {
            AFLogger.afInfoLog("CustomerUserId not set, reporting is disabled", true);
            return;
        }
        if (z10) {
            onDeepLinking = (onAttributionFailure + 23) % 128;
            if (!(!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true))) {
                if (afInfoLog()) {
                    int i10 = onDeepLinking + 109;
                    onAttributionFailure = i10 % 128;
                    if (i10 % 2 == 0) {
                        AppsFlyerRequestListener appsFlyerRequestListener = aFa1qSDK.AFInAppEventParameterName;
                        if (appsFlyerRequestListener != null) {
                            appsFlyerRequestListener.onError(10, "Event timeout. Check 'minTimeBetweenSessions' param");
                            return;
                        }
                        return;
                    }
                    throw null;
                }
            } else {
                AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
            }
            this.afDebugLog = System.currentTimeMillis();
        }
        AFInAppEventParameterName(AFInAppEventType().valueOf(), new AFa1vSDK(this, aFa1qSDK, b10), 0L, TimeUnit.MILLISECONDS);
        onDeepLinking = (onAttributionFailure + 59) % 128;
    }

    private int AFInAppEventType(AFb1dSDK aFb1dSDK) {
        int i10 = onAttributionFailure + 115;
        onDeepLinking = i10 % 128;
        int i11 = i10 % 2;
        int iAFKeystoreWrapper = AFKeystoreWrapper(aFb1dSDK, "appsFlyerAdImpressionCount", true);
        onDeepLinking = (onAttributionFailure + 83) % 128;
        return iAFKeystoreWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        if (r10.matches("fb\\d*?://authorize.*") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        if (r10.contains("access_token") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 59) % 128;
        r1 = valueOf(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r1.length() != 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003f, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
    
        r3 = new java.util.ArrayList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
    
        if (r1.contains(com.huawei.hms.framework.common.ContainerUtils.FIELD_DELIMITER) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
    
        r3 = new java.util.ArrayList(java.util.Arrays.asList(r1.split(com.huawei.hms.framework.common.ContainerUtils.FIELD_DELIMITER)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
    
        r3.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
    
        r5 = new java.lang.StringBuilder();
        r3 = r3.iterator();
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 7) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0073, code lost:
    
        if (r3.hasNext() != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007d, code lost:
    
        return r10.replace(r1, r5.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 9) % 128;
        r6 = (java.lang.String) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0090, code lost:
    
        if (r6.contains("access_token") == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0092, code lost:
    
        r6 = com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 95;
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = r6 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009c, code lost:
    
        if ((r6 % 2) != 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009e, code lost:
    
        r3.remove();
        r6 = 13 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a6, code lost:
    
        r3.remove();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ae, code lost:
    
        if (r5.length() == 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b0, code lost:
    
        r7 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 49;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r7 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ba, code lost:
    
        if ((r7 % 2) == 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bc, code lost:
    
        r5.append(com.huawei.hms.framework.common.ContainerUtils.FIELD_DELIMITER);
        r7 = 11 / 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c4, code lost:
    
        r5.append(com.huawei.hms.framework.common.ContainerUtils.FIELD_DELIMITER);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ce, code lost:
    
        if (r6.startsWith(ru.mail.offline.bundle.utils.HashRoutingUrl.END_HASH_ROUTING) != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d0, code lost:
    
        r8 = com.appsflyer.internal.AFa1eSDK.onDeepLinking + 35;
        com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r8 % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00da, code lost:
    
        if ((r8 % 2) != 0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00dc, code lost:
    
        r5.append(ru.mail.offline.bundle.utils.HashRoutingUrl.END_HASH_ROUTING);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e0, code lost:
    
        r5.append(ru.mail.offline.bundle.utils.HashRoutingUrl.END_HASH_ROUTING);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e3, code lost:
    
        throw null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e4, code lost:
    
        r5.append(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00e8, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (com.appsflyer.internal.AFa1eSDK.onAttributionFailure + 97) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00f0, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
    
        if (r10 == null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
    
        if (r10 == null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        com.appsflyer.internal.AFa1eSDK.onDeepLinking = (r1 + 29) % 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001c, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String AFInAppEventType(@androidx.annotation.Nullable java.lang.String r10) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.AFInAppEventType(java.lang.String):java.lang.String");
    }

    private static String valueOf(String str) {
        onDeepLinking = (onAttributionFailure + 117) % 128;
        int iIndexOf = str.indexOf(63);
        if (iIndexOf == -1) {
            int i10 = onAttributionFailure + 45;
            onDeepLinking = i10 % 128;
            if (i10 % 2 != 0) {
                return "";
            }
            throw null;
        }
        String strSubstring = str.substring(iIndexOf);
        onDeepLinking = (onAttributionFailure + 93) % 128;
        return strSubstring;
    }

    public static synchronized SharedPreferences valueOf(Context context) {
        try {
            onAttributionFailure = (onDeepLinking + 119) % 128;
            if (valueOf().onConversionDataSuccess == null) {
                onDeepLinking = (onAttributionFailure + 7) % 128;
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    valueOf().onConversionDataSuccess = context.getApplicationContext().getSharedPreferences("appsflyer-data", 0);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                } catch (Throwable th2) {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return valueOf().onConversionDataSuccess;
    }

    public final int valueOf(AFb1dSDK aFb1dSDK, boolean z10) {
        onAttributionFailure = (onDeepLinking + 9) % 128;
        int iAFKeystoreWrapper = AFKeystoreWrapper(aFb1dSDK, "appsFlyerCount", z10);
        onAttributionFailure = (onDeepLinking + 13) % 128;
        return iAFKeystoreWrapper;
    }

    public static String AFInAppEventType(AFb1dSDK aFb1dSDK, String str) {
        String strValues = aFb1dSDK.values("CACHED_CHANNEL", (String) null);
        if (strValues != null) {
            int i10 = (onAttributionFailure + 97) % 128;
            onDeepLinking = i10;
            onAttributionFailure = (i10 + 115) % 128;
            return strValues;
        }
        aFb1dSDK.AFInAppEventParameterName("CACHED_CHANNEL", str);
        return str;
    }

    private void AFInAppEventType(@NonNull AFa1qSDK aFa1qSDK, @Nullable Activity activity) {
        onAttributionFailure = (onDeepLinking + 117) % 128;
        AFf1vSDK aFf1vSDKOnResponseErrorNative = AFInAppEventType().onResponseErrorNative();
        aFa1qSDK.valueOf = aFf1vSDKOnResponseErrorNative.values(activity);
        aFa1qSDK.afInfoLog = aFf1vSDKOnResponseErrorNative.valueOf(activity);
        int i10 = onDeepLinking + 13;
        onAttributionFailure = i10 % 128;
        if (i10 % 2 != 0) {
            throw null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0030 A[Catch: all -> 0x0022, TRY_LEAVE, TryCatch #0 {all -> 0x0022, blocks: (B:8:0x0017, B:15:0x002a, B:17:0x0030, B:13:0x0024), top: B:22:0x0015 }] */
    private static String AFInAppEventParameterName(Activity activity) {
        Intent intent;
        Bundle extras;
        String string = null;
        if (activity != null && (intent = activity.getIntent()) != null) {
            int i10 = onAttributionFailure + 43;
            onDeepLinking = i10 % 128;
            try {
                if (i10 % 2 == 0) {
                    extras = intent.getExtras();
                    int i11 = 96 / 0;
                    if (extras != null) {
                        string = extras.getString("af");
                        if (string != null) {
                            AFLogger.afInfoLog("Push Notification received af payload = ".concat(String.valueOf(string)));
                            extras.remove("af");
                            activity.setIntent(intent.putExtras(extras));
                            onAttributionFailure = (onDeepLinking + 41) % 128;
                        }
                    }
                } else {
                    extras = intent.getExtras();
                    if (extras != null) {
                        string = extras.getString("af");
                        if (string != null) {
                            AFLogger.afInfoLog("Push Notification received af payload = ".concat(String.valueOf(string)));
                            extras.remove("af");
                            activity.setIntent(intent.putExtras(extras));
                            onAttributionFailure = (onDeepLinking + 41) % 128;
                        }
                    }
                }
                return string;
            } catch (Throwable th2) {
                AFLogger.afErrorLog(th2.getMessage(), th2);
            }
        }
        return string;
    }

    private int AFInAppEventParameterName(AFb1dSDK aFb1dSDK) {
        onAttributionFailure = (onDeepLinking + 119) % 128;
        int iAFKeystoreWrapper = AFKeystoreWrapper(aFb1dSDK, "appsFlyerAdRevenueCount", true);
        onAttributionFailure = (onDeepLinking + 59) % 128;
        return iAFKeystoreWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0026, code lost:
    
        if (com.google.android.gms.common.GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(r4) == 0) goto L11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.content.pm.PackageManager] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003f -> B:22:0x0044). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean AFInAppEventParameterName(android.content.Context r4) {
        /*
            int r0 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r0 = r0 + 35
            int r1 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r1
            int r0 = r0 % 2
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1e
            com.google.android.gms.common.GoogleApiAvailability r0 = com.google.android.gms.common.GoogleApiAvailability.getInstance()     // Catch: java.lang.Throwable -> L1c
            int r0 = r0.isGooglePlayServicesAvailable(r4)     // Catch: java.lang.Throwable -> L1c
            r3 = 53
            int r3 = r3 / r2
            if (r0 != 0) goto L36
            goto L28
        L1c:
            r0 = move-exception
            goto L3f
        L1e:
            com.google.android.gms.common.GoogleApiAvailability r0 = com.google.android.gms.common.GoogleApiAvailability.getInstance()     // Catch: java.lang.Throwable -> L1c
            int r0 = r0.isGooglePlayServicesAvailable(r4)     // Catch: java.lang.Throwable -> L1c
            if (r0 != 0) goto L36
        L28:
            int r4 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r4 = r4 + 107
            int r0 = r4 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r0
            int r4 = r4 % 2
            if (r4 == 0) goto L35
            return r2
        L35:
            return r1
        L36:
            int r0 = com.appsflyer.internal.AFa1eSDK.onDeepLinking
            int r0 = r0 + 55
            int r0 = r0 % 128
            com.appsflyer.internal.AFa1eSDK.onAttributionFailure = r0
            goto L44
        L3f:
            java.lang.String r3 = "WARNING:  Google play services is unavailable. "
            com.appsflyer.AFLogger.afErrorLog(r3, r0)
        L44:
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4e
            java.lang.String r0 = "com.google.android.gms"
            r4.getPackageInfo(r0, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L4e
            return r1
        L4e:
            r4 = move-exception
            java.lang.String r0 = "WARNING:  Google Play Services is unavailable. "
            com.appsflyer.AFLogger.afErrorLog(r0, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFa1eSDK.AFInAppEventParameterName(android.content.Context):boolean");
    }

    private static void AFInAppEventParameterName(@NonNull ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j10, TimeUnit timeUnit) {
        onAttributionFailure = (onDeepLinking + 111) % 128;
        try {
            scheduledExecutorService.schedule(runnable, j10, timeUnit);
            onAttributionFailure = (onDeepLinking + 125) % 128;
        } catch (RejectedExecutionException e10) {
            AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e10);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("scheduleJob failed with Exception", th2);
        }
    }
}
