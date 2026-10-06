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
public class MetaThreadsEditParams extends UserEditCommand.Params {
    private static final String META_THREADS_ON = "metathreads_on";

    @Param(getterName = "getJsonFlags", method = HttpMethod.GET, name = "common_purpose_flags", useGetter = true)
    private final boolean mEnabled;

    public MetaThreadsEditParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, boolean z10) {
        super(mailboxContext, dataManager);
        this.mEnabled = z10;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MetaThreadsEditParams) && super.equals(obj) && this.mEnabled == ((MetaThreadsEditParams) obj).mEnabled;
    }

    public String getJsonFlags() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(META_THREADS_ON, this.mEnabled);
            return jSONObject.toString();
        } catch (JSONException e10) {
            throw new UnsupportedOperationException(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (super.hashCode() * 31) + (this.mEnabled ? 1 : 0);
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }
}
