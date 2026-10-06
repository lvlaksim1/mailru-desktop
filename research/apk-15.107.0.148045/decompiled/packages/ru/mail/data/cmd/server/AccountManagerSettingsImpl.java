package ru.mail.data.cmd.server;

import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.AccountManagerSettings;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class AccountManagerSettingsImpl implements AccountManagerSettings {
    @Override // ru.mail.serverapi.AccountManagerSettings
    public String getAccountType() {
        return BuildConfigVariablesHolder.accountType;
    }
}
