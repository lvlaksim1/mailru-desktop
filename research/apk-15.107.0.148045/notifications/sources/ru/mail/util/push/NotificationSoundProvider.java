package ru.mail.util.push;

import android.content.pm.ProviderInfo;
import ru.mail.logic.share.MailFileProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class NotificationSoundProvider extends MailFileProvider {
    @Override // ru.mail.logic.share.MailFileProvider
    protected void checkProviderSecurity(ProviderInfo providerInfo) {
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }
}
