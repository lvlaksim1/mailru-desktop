package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.ConfigurationRepository;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.util.PushAnalyticUrlData;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/util/push/AnalyticUrlRequest;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "execute", "", "context", "Landroid/content/Context;", "url", "", "isNeedSaveUrlInDB", "", "isNeedSendAnalytic", "isUseSupervisorJobEnabled", "getDataManager", "Lru/mail/logic/content/DataManager;", "getTimeStamp", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnalyticUrlRequest {

    @NotNull
    public static final AnalyticUrlRequest INSTANCE = new AnalyticUrlRequest();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("AnalyticUrlRequest");
    public static final int $stable = 8;

    private AnalyticUrlRequest() {
    }

    @JvmStatic
    public static final void execute(@NotNull final Context context, @NotNull final String url) {
        PushAnalyticUrlData fromUrl;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        AnalyticUrlRequest analyticUrlRequest = INSTANCE;
        boolean zIsNeedSendAnalytic = analyticUrlRequest.isNeedSendAnalytic(context);
        boolean zIsNeedSaveUrlInDB = analyticUrlRequest.isNeedSaveUrlInDB(context);
        final boolean zIsUseSupervisorJobEnabled = analyticUrlRequest.isUseSupervisorJobEnabled(context);
        if (zIsNeedSendAnalytic && (fromUrl = PushAnalyticUrlData.INSTANCE.parseFromUrl(url)) != null && Intrinsics.areEqual(fromUrl.getEvent(), "open")) {
            MailAppDependencies.analytics(context).sendingOpenUrlWorkPlanned(fromUrl.getPushTokenHash(), fromUrl.getCampaignId(), fromUrl.getAccount(), analyticUrlRequest.getTimeStamp(), zIsNeedSaveUrlInDB);
        }
        if (zIsNeedSaveUrlInDB) {
            LOG.d("Open request executed. Open url will be saved in DB");
            analyticUrlRequest.getDataManager(context).savePongUrl(url, new DataManager.SavePongUrlListener() { // from class: ru.mail.util.push.a
                @Override // ru.mail.logic.content.DataManager.SavePongUrlListener
                public final void onSuccess() {
                    AnalyticUrlRequest.execute$lambda$0(context, url, zIsUseSupervisorJobEnabled);
                }
            });
            return;
        }
        LOG.d("Open request executed. Open url will NOT be saved in DB");
        WorkRequest.Builder builder = new WorkRequest.Builder(SendPongWorker.class, url);
        SendPongWorker.Params params = new SendPongWorker.Params();
        params.setCallbackUrl(url);
        builder.data(params.toData());
        ((WorkScheduler) Locator.INSTANCE.locate(context, WorkScheduler.class)).schedule(builder.constraints(WorkRequest.Constraints.NETWORK).getRequest());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$0(Context context, String str, boolean z10) {
        WorkRequest.Builder builder = new WorkRequest.Builder(SendAllPongRequestWorker.class, SendAllPongRequestWorker.uniqueId);
        SendAllPongRequestWorker.Params params = new SendAllPongRequestWorker.Params();
        params.setUrlTriggeredWorker(str);
        params.setNeedUseSupervisorJob(z10);
        builder.data(params.toData());
        ((WorkScheduler) Locator.INSTANCE.locate(context, WorkScheduler.class)).schedule(builder.constraints(WorkRequest.Constraints.NETWORK).initialDelay(1L, TimeUnit.MINUTES).getRequest());
    }

    private final DataManager getDataManager(Context context) {
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        Intrinsics.checkNotNullExpressionValue(commonDataManagerFrom, "from(...)");
        return commonDataManagerFrom;
    }

    private final long getTimeStamp() {
        return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    private final boolean isNeedSaveUrlInDB(Context context) {
        return ConfigurationRepository.from(context).getConfiguration().isSaveAnalyticOpenUrlInLocalDataBaseEnabled();
    }

    private final boolean isNeedSendAnalytic(Context context) {
        return ConfigurationRepository.from(context).getConfiguration().isAnalyticSendingAckAndOpenEnabled();
    }

    private final boolean isUseSupervisorJobEnabled(Context context) {
        return ConfigurationRepository.from(context).getConfiguration().isUseSupervisorJobInWorkersEnabled();
    }
}
