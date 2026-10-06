package ru.mail.serverapi;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlParamUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class ServerCommandBaseParams {
    public static final String FIELD_PREFIX = "PARAM_KEY_";
    public static final String PARAM_KEY_EMAIL = "email";
    public static final String PARAM_KEY_LANG = "lang";

    @Keep
    @Param(method = HttpMethod.GET, name = AccountInfoUtilsKt.PARAM_KEY_ACT_MODE)
    private final String mActMode;
    private Long mFolder;
    private FolderState mFolderState;

    @Param(getterName = "getLocale", method = HttpMethod.GET, name = "lang", useGetter = true)
    private final String mLocale;

    @Param(getterName = "getEmail", method = HttpMethod.GET, name = "email", useGetter = true)
    private String mLogin;

    public ServerCommandBaseParams(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
        this.mLocale = String.valueOf(Locale.getDefault());
        this.mLogin = accountInfo.getLogin();
        this.mFolderState = folderState;
        if (folderState != null) {
            this.mFolder = Long.valueOf(folderState.getFolderId());
        }
        if (needAppendActMode()) {
            this.mActMode = AccountInfoUtilsKt.getActiveMode(accountInfo);
        } else {
            this.mActMode = null;
        }
    }

    @NonNull
    public static List<String> urlParamsNames() {
        return UrlParamUtils.getUrlParamNamesForClass(ServerCommandBaseParams.class, FIELD_PREFIX);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ServerCommandBaseParams)) {
            return false;
        }
        ServerCommandBaseParams serverCommandBaseParams = (ServerCommandBaseParams) obj;
        String str = this.mLocale;
        if (str == null ? serverCommandBaseParams.mLocale != null : !str.equals(serverCommandBaseParams.mLocale)) {
            return false;
        }
        String str2 = this.mLogin;
        if (str2 == null ? serverCommandBaseParams.mLogin != null : !str2.equals(serverCommandBaseParams.mLogin)) {
            return false;
        }
        Long l10 = this.mFolder;
        if (l10 == null ? serverCommandBaseParams.mFolder != null : !l10.equals(serverCommandBaseParams.mFolder)) {
            return false;
        }
        String str3 = this.mActMode;
        String str4 = serverCommandBaseParams.mActMode;
        return str3 == null ? str4 == null : str3.equals(str4);
    }

    public String getActiveMode() {
        return this.mActMode;
    }

    @Nullable
    public String getEmail() {
        if (needAppendEmail()) {
            return this.mLogin;
        }
        return null;
    }

    public FolderState getFolderState() {
        return this.mFolderState;
    }

    public String getLocale() {
        if (needAppendLocale()) {
            return this.mLocale;
        }
        return null;
    }

    @Nullable
    public String getLogin() {
        return this.mLogin;
    }

    public int hashCode() {
        String str = this.mLocale;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mLogin;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Long l10 = this.mFolder;
        int iHashCode3 = (iHashCode2 + (l10 != null ? l10.hashCode() : 0)) * 31;
        String str3 = this.mActMode;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    protected boolean needAppendActMode() {
        return true;
    }

    protected boolean needAppendEmail() {
        return false;
    }

    protected boolean needAppendLocale() {
        return false;
    }

    public String toString() {
        return "ServerCommandBaseParams{mLocale='" + this.mLocale + "', mLogin='" + this.mLogin + "', mFolder=" + this.mFolder + "', mActMode=" + this.mActMode + AbstractJsonLexerKt.END_OBJ;
    }

    public ServerCommandBaseParams() {
        this.mLocale = String.valueOf(Locale.getDefault());
        this.mActMode = null;
    }
}
