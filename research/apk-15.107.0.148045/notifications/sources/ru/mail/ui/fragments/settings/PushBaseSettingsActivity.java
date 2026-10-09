package ru.mail.ui.fragments.settings;

import android.os.Bundle;
import ru.mail.MailApplication;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.data.dao.ResourceObserver;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.pushfilters.OnFiltersLoadedListener;
import ru.mail.logic.pushfilters.PushFilterChangedObserver;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.SettingsUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public abstract class PushBaseSettingsActivity extends SwitchActivity implements OnFiltersLoadedListener.Subscriber {
    private CommonDataManager mDataManager;
    private PushFiltersLoader mFiltersLoader;
    private PushFilterChangedObserver mFiltersObserver;

    protected abstract PushFilterChangedObserver createObserver();

    protected PushFiltersLoader getFiltersLoader() {
        return this.mFiltersLoader;
    }

    protected boolean isPushTransportRegistered() {
        for (PushMessagesTransport pushMessagesTransport : ((MailApplication) getApplicationContext()).getPushComponent().getPushMessagesTransports()) {
            if (pushMessagesTransport.isRegistered() && pushMessagesTransport.getPushType() != PushType.VKPNS) {
                return true;
            }
        }
        return false;
    }

    @Override // ru.mail.ui.fragments.settings.SwitchActivity, ru.mail.ui.fragments.settings.BaseSettingsActivityCompat, ru.mail.ui.fragments.settings.BaseSettingsActivity, android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(this);
        this.mDataManager = commonDataManagerFrom;
        this.mFiltersLoader = new PushFiltersLoader(this, commonDataManagerFrom);
        this.mFiltersObserver = createObserver();
    }

    @Override // ru.mail.ui.fragments.settings.BaseSettingsActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mDataManager.registerObserver((ResourceObserver) this.mFiltersObserver);
        this.mFiltersLoader.load();
    }

    @Override // ru.mail.ui.fragments.settings.BaseSettingsActivityCompat, ru.mail.ui.fragments.settings.BaseSettingsActivity, android.preference.PreferenceActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mFiltersLoader.onStop();
        this.mDataManager.unregisterObserver((ResourceObserver) this.mFiltersObserver);
    }

    public void sendPushSettings(boolean z10) {
        SettingsUtil.sendSettingsAllAccounts(this);
        MailAppDependencies.analytics(getApplicationContext()).notificationsState(z10);
    }
}
