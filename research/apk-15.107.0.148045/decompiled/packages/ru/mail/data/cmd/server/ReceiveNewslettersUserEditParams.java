package ru.mail.data.cmd.server;

import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class ReceiveNewslettersUserEditParams extends UserEditCommand.Params {
    private static final String SENT_ME_ADS_PARAM = "sent_me_ads";

    @Param(method = HttpMethod.GET, name = SENT_ME_ADS_PARAM)
    private final boolean mReceiveNewsletters;

    ReceiveNewslettersUserEditParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, boolean z10) {
        super(mailboxContext, dataManager);
        this.mReceiveNewsletters = z10;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && super.equals(obj) && this.mReceiveNewsletters == ((ReceiveNewslettersUserEditParams) obj).mReceiveNewsletters;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (super.hashCode() * 31) + (this.mReceiveNewsletters ? 1 : 0);
    }
}
