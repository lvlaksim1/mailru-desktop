package ru.mail.util.push;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.vk.pushme.PushMeSdk;
import java.util.Iterator;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.mailapp.service.MailServiceImpl;
import ru.mail.util.push.ack.AckPushHandler;
import ru.mail.utils.UriUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class AckPushProxyHandler implements AckPushHandler {
    public static final String ACKNOWLEDGE_URL_PARAM = "ru.mail.mailapp.extra.acknowledge_url_param";
    public static final String PROXY_PUSH_ACTION_PARAM = "ru.mail.mailapp.extra.proxy_push_action_param";
    public static final String PUSH_ME_SDK_PUSH_IDS = "ru.mail.mailapp.extra.push_me_sdk_push_identificators";
    private final Context mContext;

    /* JADX INFO: compiled from: ProGuard */
    enum AllowedPushAction {
        SHOW_THREAD_MESSAGE(IntentActionsProvider.actionShowPushThreadMessage),
        SHOW_THREAD(IntentActionsProvider.actionViewThread),
        SHOW_MESSAGES_IN_FOLDER(IntentActionsProvider.actionShowPushMessageInFolder),
        SHOW_MESSAGE(IntentActionsProvider.actionShowPushMessage);

        private String mAction;

        AllowedPushAction(String str) {
            this.mAction = str;
        }

        public String getAction() {
            return this.mAction;
        }
    }

    public AckPushProxyHandler(Context context) {
        this.mContext = context;
    }

    private Uri getResultUri(String str) {
        return UriUtils.mergeQueryParameters(Uri.parse(str), new Uri.Builder().appendQueryParameter("action", "open").build());
    }

    private void sendAnalytic(Intent intent) {
        MailAppDependencies.analytics(this.mContext).sendPushAnalytics(MailServiceImpl.hasSmartChoices(intent), MailServiceImpl.hasStageSmartReply(intent), MailServiceImpl.extractIsDefaultSmartReply(intent).booleanValue(), MailServiceImpl.extractPushType(intent), MailServiceImpl.extractCategory(intent), MailServiceImpl.extractIsReminder(intent));
    }

    @Override // ru.mail.util.push.ack.AckPushHandler
    public void handleAckPushIntent(Intent intent) {
        if (intent == null || ((AllowedPushAction) intent.getSerializableExtra(PROXY_PUSH_ACTION_PARAM)) == null) {
            return;
        }
        sendAnalytic(intent);
        Iterator<String> it = intent.getStringArrayListExtra(ACKNOWLEDGE_URL_PARAM).iterator();
        while (it.hasNext()) {
            AnalyticUrlRequest.execute(this.mContext, getResultUri(it.next()).toString());
            intent.removeExtra(ACKNOWLEDGE_URL_PARAM);
            intent.removeExtra(PROXY_PUSH_ACTION_PARAM);
        }
        long[] longArrayExtra = intent.getLongArrayExtra(PUSH_ME_SDK_PUSH_IDS);
        if (longArrayExtra != null) {
            for (long j10 : longArrayExtra) {
                PushMeSdk.INSTANCE.onNotificationClicked(Long.valueOf(j10));
            }
        }
        intent.removeExtra(PUSH_ME_SDK_PUSH_IDS);
    }
}
