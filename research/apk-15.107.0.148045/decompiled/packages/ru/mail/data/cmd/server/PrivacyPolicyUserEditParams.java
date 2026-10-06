package ru.mail.data.cmd.server;

import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PrivacyPolicyUserEditParams extends UserEditCommand.Params {
    private static final String ACCEPTED_PARAM = "privacy_policy_accepted";

    @Param(method = HttpMethod.GET, name = ACCEPTED_PARAM)
    private final boolean mAccepted;

    PrivacyPolicyUserEditParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, boolean z10) {
        super(mailboxContext, dataManager);
        this.mAccepted = z10;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && super.equals(obj) && this.mAccepted == ((PrivacyPolicyUserEditParams) obj).mAccepted;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (super.hashCode() * 31) + (this.mAccepted ? 1 : 0);
    }
}
