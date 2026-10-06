package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.sdk.BuildConfigVariablesHolder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class LogoutProfileCommand extends LogoutRequestCommand {
    public LogoutProfileCommand(Context context, MailboxProfile mailboxProfile, boolean z10) {
        super(context, new LogoutRequestCommand.Params(Authenticator.getAccountManagerWrapper(context.getApplicationContext()).peekAuthToken(new Account(mailboxProfile.getLogin(), BuildConfigVariablesHolder.accountType), "ru.mail")), z10);
    }
}
