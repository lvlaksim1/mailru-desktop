package ru.mail.data.cmd.server.ad;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ads.config.api.data.model.AdRemoteConfig;
import ru.mail.ads.core.impl.model.source.remote.AdTrackingRemoteSource;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.PlayMarketIntentCreator;
import ru.mail.logic.navigation.Navigator;
import ru.mail.logic.navigation.PendingActionObserver;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.CompleteObserver;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkServiceFactory;
import ru.mail.network.RequestDurationAnalytics;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Logger;
import ru.mail.utils.IntentUtils;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001aH\u0002J\u001a\u0010!\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001a2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010\"\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001aH\u0002J\u0012\u0010#\u001a\u00020\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006'"}, d2 = {"Lru/mail/data/cmd/server/ad/AdTrackingSender;", "", "context", "Landroid/content/Context;", "executorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "logger", "Lru/mail/util/log/Logger;", "adNetworkConfig", "Lru/mail/ads/config/api/data/model/AdRemoteConfig$AdNetworkConfig;", "<init>", "(Landroid/content/Context;Lru/mail/mailbox/cmd/ExecutorSelector;Lru/mail/util/log/Logger;Lru/mail/ads/config/api/data/model/AdRemoteConfig$AdNetworkConfig;)V", "networkService", "Lru/mail/network/service/NetworkService;", "durationAnalytics", "Lru/mail/network/RequestDurationAnalytics;", "navigator", "Lru/mail/logic/navigation/Navigator;", "useNewTrackAdvertisingUrlRequest", "", "getUseNewTrackAdvertisingUrlRequest", "()Z", "requestSync", "Lru/mail/mailbox/cmd/ObservableFuture;", "Lru/mail/ads/core/impl/model/source/remote/AdTrackingRemoteSource$Result;", "url", "", "openLink", "", "link", "packageName", "onBannerTrackingRedirectSuccess", "uri", "onBannerTrackingGooglePlayRedirectSuccess", "onBannerTrackingDeeplinkRedirectSuccess", "onBannerTrackingRedirectFailed", "startIntent", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAdTrackingSender.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AdTrackingSender.kt\nru/mail/data/cmd/server/ad/AdTrackingSender\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,141:1\n29#2:142\n*S KotlinDebug\n*F\n+ 1 AdTrackingSender.kt\nru/mail/data/cmd/server/ad/AdTrackingSender\n*L\n113#1:142\n*E\n"})
public final class AdTrackingSender {
    public static final int $stable = 8;

    @NotNull
    private final AdRemoteConfig.AdNetworkConfig adNetworkConfig;

    @NotNull
    private final Context context;

    @NotNull
    private final RequestDurationAnalytics durationAnalytics;

    @NotNull
    private final ExecutorSelector executorSelector;

    @NotNull
    private final Logger logger;

    @NotNull
    private final Navigator navigator;

    @NotNull
    private final NetworkService networkService;

    public AdTrackingSender(@NotNull Context context, @NotNull ExecutorSelector executorSelector, @NotNull Logger logger, @NotNull AdRemoteConfig.AdNetworkConfig adNetworkConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(adNetworkConfig, "adNetworkConfig");
        this.context = context;
        this.executorSelector = executorSelector;
        this.logger = logger;
        this.adNetworkConfig = adNetworkConfig;
        Locator.Companion companion = Locator.INSTANCE;
        this.networkService = ((NetworkServiceFactory) companion.from(context).locate(NetworkServiceFactory.class)).createNetworkService();
        this.durationAnalytics = (RequestDurationAnalytics) companion.from(context).locate(RequestDurationAnalytics.class);
        this.navigator = (Navigator) companion.from(context).locate(Navigator.class);
    }

    private final boolean getUseNewTrackAdvertisingUrlRequest() {
        return this.adNetworkConfig.getUseNewTrackAdvertisingUrlRequest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBannerTrackingDeeplinkRedirectSuccess(String uri) {
        this.navigator.findPathFor(uri).observe(Schedulers.mainThread(), new PendingActionObserver(new AppContextExecutor(this.context.getApplicationContext())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBannerTrackingGooglePlayRedirectSuccess(String uri, String packageName) {
        if (packageName != null) {
            IntentUtils.openGooglePlay$default(this.context, packageName, true, false, null, 24, null);
        } else {
            IntentUtils.openGooglePlay$default(this.context, Uri.parse(uri), true, false, 8, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBannerTrackingRedirectFailed(String packageName) {
        Intent intentCreateIntentFromPackage = new PlayMarketIntentCreator(this.context).createIntentFromPackage(packageName);
        Intrinsics.checkNotNull(intentCreateIntentFromPackage);
        startIntent(intentCreateIntentFromPackage);
        MailAppDependencies.analytics(this.context).sendBannerTrackingRedirectFailedAnalytics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBannerTrackingRedirectSuccess(String uri) {
        startIntent(IntentUtils.createIntentFromUri(uri));
    }

    private final void startIntent(Intent intent) {
        List<ResolveInfo> listPerform = PackageManagerUtil.from(this.context).queryIntentActivities(intent, 0).onErrorReturn(new ArrayList()).perform();
        Intrinsics.checkNotNullExpressionValue(listPerform, "perform(...)");
        if (listPerform.isEmpty()) {
            return;
        }
        this.context.startActivity(intent);
    }

    public final void openLink(@NotNull String link, @Nullable final String packageName) {
        Intrinsics.checkNotNullParameter(link, "link");
        final AdTrackingLinkCommand adTrackingLinkCommand = new AdTrackingLinkCommand(this.context, this.networkService, this.durationAnalytics, link, this.logger, getUseNewTrackAdvertisingUrlRequest());
        adTrackingLinkCommand.execute(this.executorSelector).observe(Schedulers.mainThread(), new CompleteObserver<Object>() { // from class: ru.mail.data.cmd.server.ad.AdTrackingSender.openLink.1
            @Override // ru.mail.mailbox.cmd.CompleteObserver
            public void onComplete() {
                Object data;
                Object result = adTrackingLinkCommand.getResult();
                CommandStatus commandStatus = result instanceof CommandStatus ? (CommandStatus) result : null;
                if (!NetworkCommand.statusOK(commandStatus)) {
                    this.onBannerTrackingRedirectFailed(packageName);
                    return;
                }
                if (commandStatus == null || (data = commandStatus.getData()) == null) {
                    return;
                }
                AdTrackingSender adTrackingSender = this;
                String str = packageName;
                String str2 = (String) data;
                if (commandStatus instanceof AdTrackingLinkCommand.GooglePlayRedirect) {
                    adTrackingSender.onBannerTrackingGooglePlayRedirectSuccess(str2, str);
                } else if (commandStatus instanceof AdTrackingLinkCommand.DeeplinkRedirect) {
                    adTrackingSender.onBannerTrackingDeeplinkRedirectSuccess(str2);
                } else {
                    adTrackingSender.onBannerTrackingRedirectSuccess(str2);
                }
            }
        });
    }

    @NotNull
    public final ObservableFuture<AdTrackingRemoteSource.Result> requestSync(@NotNull String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ObservableFuture<AdTrackingRemoteSource.Result> observableFutureExecute = new AdTrackingUrlCommand(this.context, this.networkService, this.durationAnalytics, url, this.logger, getUseNewTrackAdvertisingUrlRequest()).execute(this.executorSelector);
        Intrinsics.checkNotNullExpressionValue(observableFutureExecute, "execute(...)");
        return observableFutureExecute;
    }
}
