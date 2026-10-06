package ru.mail.data.cmd.server;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class MailCheckUserEditParams extends UserEditCommand.Params {
    private static final String MAIL_CHECK_DISABLED = "mail_check_disabled";

    @Param(getterName = "getJsonFlags", method = HttpMethod.GET, name = "common_purpose_flags", useGetter = true)
    private final boolean mDisabled;

    public MailCheckUserEditParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, boolean z10) {
        super(mailboxContext, dataManager);
        this.mDisabled = z10;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MailCheckUserEditParams) && super.equals(obj) && this.mDisabled == ((MailCheckUserEditParams) obj).mDisabled;
    }

    public String getJsonFlags() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mail_check_disabled", this.mDisabled);
            return jSONObject.toString();
        } catch (JSONException e10) {
            throw new UnsupportedOperationException(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (super.hashCode() * 31) + (this.mDisabled ? 1 : 0);
    }

    public boolean isDisabled() {
        return this.mDisabled;
    }
}
