package ru.mail.data.cmd.server;

import android.text.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class NameUserEditParams extends UserEditCommand.Params {
    private static final String PARAM_NAME = "name";

    @Param(getterName = "getJsonName", method = HttpMethod.GET, name = "name", useGetter = true)
    private final String mNewFirstName;
    private final String mNewLastName;

    public NameUserEditParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable String str2) {
        super(mailboxContext, dataManager);
        this.mNewFirstName = str;
        this.mNewLastName = str2;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        NameUserEditParams nameUserEditParams = (NameUserEditParams) obj;
        String str = this.mNewFirstName;
        if (str == null ? nameUserEditParams.mNewFirstName != null : !str.equals(nameUserEditParams.mNewFirstName)) {
            return false;
        }
        String str2 = this.mNewLastName;
        String str3 = nameUserEditParams.mNewLastName;
        return str2 == null ? str3 == null : str2.equals(str3);
    }

    public String getJsonName() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.mNewFirstName)) {
                jSONObject.put(PreferenceHostProvider.URL_PARAM_FIRST, this.mNewFirstName);
            }
            if (!TextUtils.isEmpty(this.mNewLastName)) {
                jSONObject.put(MailThreadRepresentation.COL_NAME_LAST, this.mNewLastName);
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.mNewFirstName;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.mNewLastName;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }
}
