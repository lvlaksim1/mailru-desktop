package com.vk.pushme;

import com.fasterxml.jackson.core.io.doubleparser.FastDoubleMath;
import com.huawei.hms.push.AttributionReporter;
import com.vk.auth.restore.RestoreConstants;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.analytcis.StubAnalyticsHandler;
import com.vk.pushme.common.DefaultLogger;
import com.vk.pushme.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.PreferenceHostProvider;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00018Bu\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001bJ\t\u0010+\u001a\u00020\nHÆ\u0003J\t\u0010,\u001a\u00020\nHÆ\u0003J\t\u0010-\u001a\u00020\rHÆ\u0003J\t\u0010.\u001a\u00020\nHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0012HÆ\u0003J|\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u00102J\u0013\u00103\u001a\u00020\n2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&¨\u00069"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig;", "", "defaultApplication", "", "pusherHost", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", AttributionReporter.APP_VERSION, "sendSubscriptionsIntervalInMinutes", "", "shouldAppendSdkDeviceId", "", "skipConnectionCheckByGoogle", "logger", "Lcom/vk/pushme/common/Logger;", "debugLogsEnabled", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Ljava/lang/String;Lcom/vk/pushme/PushMeSdkConfig$PusherHost;Ljava/lang/String;Ljava/lang/Long;ZZLcom/vk/pushme/common/Logger;ZLokhttp3/OkHttpClient;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "getDefaultApplication", "()Ljava/lang/String;", "getPusherHost", "()Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "getAppVersion", "getSendSubscriptionsIntervalInMinutes", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getShouldAppendSdkDeviceId", "()Z", "getSkipConnectionCheckByGoogle", "getLogger", "()Lcom/vk/pushme/common/Logger;", "getDebugLogsEnabled", "getClient", "()Lokhttp3/OkHttpClient;", "getAnalyticsHandler", "()Lcom/vk/pushme/analytcis/AnalyticsHandler;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Lcom/vk/pushme/PushMeSdkConfig$PusherHost;Ljava/lang/String;Ljava/lang/Long;ZZLcom/vk/pushme/common/Logger;ZLokhttp3/OkHttpClient;Lcom/vk/pushme/analytcis/AnalyticsHandler;)Lcom/vk/pushme/PushMeSdkConfig;", "equals", "other", "hashCode", "", "toString", "PusherHost", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PushMeSdkConfig {

    @Nullable
    private final AnalyticsHandler analyticsHandler;

    @Nullable
    private final String appVersion;

    @Nullable
    private final OkHttpClient client;
    private final boolean debugLogsEnabled;

    @Nullable
    private final String defaultApplication;

    @NotNull
    private final Logger logger;

    @NotNull
    private final PusherHost pusherHost;

    @Nullable
    private final Long sendSubscriptionsIntervalInMinutes;
    private final boolean shouldAppendSdkDeviceId;
    private final boolean skipConnectionCheckByGoogle;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0013\u0014\u0015\u0016\u0017B9\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\b\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u0082\u0001\u0005\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "", "scheme", "", "host", "port", "", "apiPathV1", "apiPathV2", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getScheme$push_me_sdk_release", "()Ljava/lang/String;", "getHost$push_me_sdk_release", "getPort$push_me_sdk_release", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getApiPathV1$push_me_sdk_release", "getApiPathV2$push_me_sdk_release", "Prod", "AltProd", "Dev", "MiniMail", "Custom", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost$AltProd;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Custom;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Dev;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost$MiniMail;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Prod;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class PusherHost {

        @NotNull
        private final String apiPathV1;

        @NotNull
        private final String apiPathV2;

        @NotNull
        private final String host;

        @Nullable
        private final Integer port;

        @NotNull
        private final String scheme;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost$AltProd;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AltProd extends PusherHost {

            @NotNull
            public static final AltProd INSTANCE = new AltProd();

            private AltProd() {
                super(RestoreConstants.DEFAULT_URL_SCHEME, "alt-push-me.mail.ru", null, null, null, 28, null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes19.dex */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Dev;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Dev extends PusherHost {

            @NotNull
            public static final Dev INSTANCE = new Dev();

            private Dev() {
                super(RestoreConstants.DEFAULT_URL_SCHEME, "push-me.devmail.ru", null, ApiUris.AUTHORITY_API, "internal_api", 4, null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes19.dex */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost$MiniMail;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class MiniMail extends PusherHost {

            @NotNull
            public static final MiniMail INSTANCE = new MiniMail();

            private MiniMail() {
                super(RestoreConstants.DEFAULT_URL_SCHEME, "push-me.mini-mail.ru", null, null, null, 28, null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Prod;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Prod extends PusherHost {

            @NotNull
            public static final Prod INSTANCE = new Prod();

            private Prod() {
                super(RestoreConstants.DEFAULT_URL_SCHEME, "push-me.mail.ru", null, null, null, 28, null);
            }
        }

        public /* synthetic */ PusherHost(String str, String str2, Integer num, String str3, String str4, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, num, str3, str4);
        }

        @NotNull
        /* JADX INFO: renamed from: getApiPathV1$push_me_sdk_release, reason: from getter */
        public final String getApiPathV1() {
            return this.apiPathV1;
        }

        @NotNull
        /* JADX INFO: renamed from: getApiPathV2$push_me_sdk_release, reason: from getter */
        public final String getApiPathV2() {
            return this.apiPathV2;
        }

        @NotNull
        /* JADX INFO: renamed from: getHost$push_me_sdk_release, reason: from getter */
        public final String getHost() {
            return this.host;
        }

        @Nullable
        /* JADX INFO: renamed from: getPort$push_me_sdk_release, reason: from getter */
        public final Integer getPort() {
            return this.port;
        }

        @NotNull
        /* JADX INFO: renamed from: getScheme$push_me_sdk_release, reason: from getter */
        public final String getScheme() {
            return this.scheme;
        }

        private PusherHost(String str, String str2, Integer num, String str3, String str4) {
            this.scheme = str;
            this.host = str2;
            this.port = num;
            this.apiPathV1 = str3;
            this.apiPathV2 = str4;
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes19.dex */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/vk/pushme/PushMeSdkConfig$PusherHost$Custom;", "Lcom/vk/pushme/PushMeSdkConfig$PusherHost;", "scheme", "", "host", "port", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Custom extends PusherHost {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Custom(@NotNull String scheme, @NotNull String host, @Nullable Integer num) {
                super(scheme, host, num, null, null, 24, null);
                Intrinsics.checkNotNullParameter(scheme, "scheme");
                Intrinsics.checkNotNullParameter(host, "host");
                if (StringsKt.isBlank(scheme)) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                if (StringsKt.isBlank(host)) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }

            public /* synthetic */ Custom(String str, String str2, Integer num, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i10 & 4) != 0 ? null : num);
            }
        }

        public /* synthetic */ PusherHost(String str, String str2, Integer num, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? null : num, (i10 & 8) != 0 ? ApiUris.AUTHORITY_API : str3, (i10 & 16) != 0 ? ApiUris.AUTHORITY_API : str4, null);
        }
    }

    public PushMeSdkConfig() {
        this(null, null, null, null, false, false, null, false, null, null, FastDoubleMath.DOUBLE_EXPONENT_BIAS, null);
    }

    public static /* synthetic */ PushMeSdkConfig copy$default(PushMeSdkConfig pushMeSdkConfig, String str, PusherHost pusherHost, String str2, Long l10, boolean z10, boolean z11, Logger logger, boolean z12, OkHttpClient okHttpClient, AnalyticsHandler analyticsHandler, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pushMeSdkConfig.defaultApplication;
        }
        if ((i10 & 2) != 0) {
            pusherHost = pushMeSdkConfig.pusherHost;
        }
        if ((i10 & 4) != 0) {
            str2 = pushMeSdkConfig.appVersion;
        }
        if ((i10 & 8) != 0) {
            l10 = pushMeSdkConfig.sendSubscriptionsIntervalInMinutes;
        }
        if ((i10 & 16) != 0) {
            z10 = pushMeSdkConfig.shouldAppendSdkDeviceId;
        }
        if ((i10 & 32) != 0) {
            z11 = pushMeSdkConfig.skipConnectionCheckByGoogle;
        }
        if ((i10 & 64) != 0) {
            logger = pushMeSdkConfig.logger;
        }
        if ((i10 & 128) != 0) {
            z12 = pushMeSdkConfig.debugLogsEnabled;
        }
        if ((i10 & 256) != 0) {
            okHttpClient = pushMeSdkConfig.client;
        }
        if ((i10 & 512) != 0) {
            analyticsHandler = pushMeSdkConfig.analyticsHandler;
        }
        OkHttpClient okHttpClient2 = okHttpClient;
        AnalyticsHandler analyticsHandler2 = analyticsHandler;
        Logger logger2 = logger;
        boolean z13 = z12;
        boolean z14 = z10;
        boolean z15 = z11;
        return pushMeSdkConfig.copy(str, pusherHost, str2, l10, z14, z15, logger2, z13, okHttpClient2, analyticsHandler2);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDefaultApplication() {
        return this.defaultApplication;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final AnalyticsHandler getAnalyticsHandler() {
        return this.analyticsHandler;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PusherHost getPusherHost() {
        return this.pusherHost;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAppVersion() {
        return this.appVersion;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getSendSubscriptionsIntervalInMinutes() {
        return this.sendSubscriptionsIntervalInMinutes;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getShouldAppendSdkDeviceId() {
        return this.shouldAppendSdkDeviceId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getSkipConnectionCheckByGoogle() {
        return this.skipConnectionCheckByGoogle;
    }

    @NotNull
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Logger getLogger() {
        return this.logger;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getDebugLogsEnabled() {
        return this.debugLogsEnabled;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final OkHttpClient getClient() {
        return this.client;
    }

    @NotNull
    public final PushMeSdkConfig copy(@Nullable String defaultApplication, @NotNull PusherHost pusherHost, @Nullable String appVersion, @Nullable Long sendSubscriptionsIntervalInMinutes, boolean shouldAppendSdkDeviceId, boolean skipConnectionCheckByGoogle, @NotNull Logger logger, boolean debugLogsEnabled, @Nullable OkHttpClient client, @Nullable AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(pusherHost, "pusherHost");
        Intrinsics.checkNotNullParameter(logger, "logger");
        return new PushMeSdkConfig(defaultApplication, pusherHost, appVersion, sendSubscriptionsIntervalInMinutes, shouldAppendSdkDeviceId, skipConnectionCheckByGoogle, logger, debugLogsEnabled, client, analyticsHandler);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PushMeSdkConfig)) {
            return false;
        }
        PushMeSdkConfig pushMeSdkConfig = (PushMeSdkConfig) other;
        return Intrinsics.areEqual(this.defaultApplication, pushMeSdkConfig.defaultApplication) && Intrinsics.areEqual(this.pusherHost, pushMeSdkConfig.pusherHost) && Intrinsics.areEqual(this.appVersion, pushMeSdkConfig.appVersion) && Intrinsics.areEqual(this.sendSubscriptionsIntervalInMinutes, pushMeSdkConfig.sendSubscriptionsIntervalInMinutes) && this.shouldAppendSdkDeviceId == pushMeSdkConfig.shouldAppendSdkDeviceId && this.skipConnectionCheckByGoogle == pushMeSdkConfig.skipConnectionCheckByGoogle && Intrinsics.areEqual(this.logger, pushMeSdkConfig.logger) && this.debugLogsEnabled == pushMeSdkConfig.debugLogsEnabled && Intrinsics.areEqual(this.client, pushMeSdkConfig.client) && Intrinsics.areEqual(this.analyticsHandler, pushMeSdkConfig.analyticsHandler);
    }

    @Nullable
    public final AnalyticsHandler getAnalyticsHandler() {
        return this.analyticsHandler;
    }

    @Nullable
    public final String getAppVersion() {
        return this.appVersion;
    }

    @Nullable
    public final OkHttpClient getClient() {
        return this.client;
    }

    public final boolean getDebugLogsEnabled() {
        return this.debugLogsEnabled;
    }

    @Nullable
    public final String getDefaultApplication() {
        return this.defaultApplication;
    }

    @NotNull
    public final Logger getLogger() {
        return this.logger;
    }

    @NotNull
    public final PusherHost getPusherHost() {
        return this.pusherHost;
    }

    @Nullable
    public final Long getSendSubscriptionsIntervalInMinutes() {
        return this.sendSubscriptionsIntervalInMinutes;
    }

    public final boolean getShouldAppendSdkDeviceId() {
        return this.shouldAppendSdkDeviceId;
    }

    public final boolean getSkipConnectionCheckByGoogle() {
        return this.skipConnectionCheckByGoogle;
    }

    public int hashCode() {
        String str = this.defaultApplication;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.pusherHost.hashCode()) * 31;
        String str2 = this.appVersion;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l10 = this.sendSubscriptionsIntervalInMinutes;
        int iHashCode3 = (((((((((iHashCode2 + (l10 == null ? 0 : l10.hashCode())) * 31) + Boolean.hashCode(this.shouldAppendSdkDeviceId)) * 31) + Boolean.hashCode(this.skipConnectionCheckByGoogle)) * 31) + this.logger.hashCode()) * 31) + Boolean.hashCode(this.debugLogsEnabled)) * 31;
        OkHttpClient okHttpClient = this.client;
        int iHashCode4 = (iHashCode3 + (okHttpClient == null ? 0 : okHttpClient.hashCode())) * 31;
        AnalyticsHandler analyticsHandler = this.analyticsHandler;
        return iHashCode4 + (analyticsHandler != null ? analyticsHandler.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "PushMeSdkConfig(defaultApplication=" + this.defaultApplication + ", pusherHost=" + this.pusherHost + ", appVersion=" + this.appVersion + ", sendSubscriptionsIntervalInMinutes=" + this.sendSubscriptionsIntervalInMinutes + ", shouldAppendSdkDeviceId=" + this.shouldAppendSdkDeviceId + ", skipConnectionCheckByGoogle=" + this.skipConnectionCheckByGoogle + ", logger=" + this.logger + ", debugLogsEnabled=" + this.debugLogsEnabled + ", client=" + this.client + ", analyticsHandler=" + this.analyticsHandler + ")";
    }

    public PushMeSdkConfig(@Nullable String str, @NotNull PusherHost pusherHost, @Nullable String str2, @Nullable Long l10, boolean z10, boolean z11, @NotNull Logger logger, boolean z12, @Nullable OkHttpClient okHttpClient, @Nullable AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(pusherHost, "pusherHost");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.defaultApplication = str;
        this.pusherHost = pusherHost;
        this.appVersion = str2;
        this.sendSubscriptionsIntervalInMinutes = l10;
        this.shouldAppendSdkDeviceId = z10;
        this.skipConnectionCheckByGoogle = z11;
        this.logger = logger;
        this.debugLogsEnabled = z12;
        this.client = okHttpClient;
        this.analyticsHandler = analyticsHandler;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PushMeSdkConfig(String str, PusherHost pusherHost, String str2, Long l10, boolean z10, boolean z11, Logger logger, boolean z12, OkHttpClient okHttpClient, AnalyticsHandler analyticsHandler, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        str = (i10 & 1) != 0 ? null : str;
        pusherHost = (i10 & 2) != 0 ? PusherHost.Prod.INSTANCE : pusherHost;
        str2 = (i10 & 4) != 0 ? null : str2;
        l10 = (i10 & 8) != 0 ? 1440L : l10;
        z10 = (i10 & 16) != 0 ? false : z10;
        z11 = (i10 & 32) != 0 ? false : z11;
        logger = (i10 & 64) != 0 ? new DefaultLogger("PushMeSDK") : logger;
        this(str, pusherHost, str2, l10, z10, z11, logger, (i10 & 128) != 0 ? false : z12, (i10 & 256) != 0 ? null : okHttpClient, (i10 & 512) != 0 ? new StubAnalyticsHandler(logger) : analyticsHandler);
    }
}
