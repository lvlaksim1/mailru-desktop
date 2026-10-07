package ru.mail.authorizationsdk.feature.customserver.domain;

import android.util.Patterns;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.domain.model.MailProtocol;
import ru.mail.authorizationsdk.domain.model.serversettings.ServerSettings;
import ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\u001b"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/domain/CustomServerParams;", "", "email", "", "password", "protocol", "Lru/mail/authorizationsdk/domain/model/MailProtocol;", "serverSettings", "Lru/mail/authorizationsdk/domain/model/serversettings/ServerSettings;", "captchaCookie", "captchaCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/domain/model/MailProtocol;Lru/mail/authorizationsdk/domain/model/serversettings/ServerSettings;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getProtocol", "()Lru/mail/authorizationsdk/domain/model/MailProtocol;", "getServerSettings", "()Lru/mail/authorizationsdk/domain/model/serversettings/ServerSettings;", "getCaptchaCookie", "getCaptchaCode", "getNotValidFields", "", "Lru/mail/authorizationsdk/feature/customserver/domain/model/InvalidFieldName;", "isCaptchaCodeRequired", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomServerParams {
    public static final int $stable = 8;

    @Nullable
    private final String captchaCode;

    @Nullable
    private final String captchaCookie;

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final MailProtocol protocol;

    @NotNull
    private final ServerSettings serverSettings;

    public CustomServerParams(@NotNull String email, @NotNull String password, @NotNull MailProtocol protocol, @NotNull ServerSettings serverSettings, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(serverSettings, "serverSettings");
        this.email = email;
        this.password = password;
        this.protocol = protocol;
        this.serverSettings = serverSettings;
        this.captchaCookie = str;
        this.captchaCode = str2;
    }

    @Nullable
    public final String getCaptchaCode() {
        return this.captchaCode;
    }

    @Nullable
    public final String getCaptchaCookie() {
        return this.captchaCookie;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final List<InvalidFieldName> getNotValidFields(boolean isCaptchaCodeRequired) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (this.email.length() == 0 || this.password.length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(this.email).matches()) {
            arrayList.add(InvalidFieldName.LOCAL_ERROR_EMAIL_LOGIN);
        }
        if (this.serverSettings.getIncomingHost().length() == 0) {
            arrayList.add(InvalidFieldName.COLLECT_SERVER);
        }
        if (this.serverSettings.getIncomingPort().length() == 0) {
            arrayList.add(InvalidFieldName.COLLECT_PORT);
        }
        if (this.serverSettings.getOutgoingHost().length() == 0) {
            arrayList.add(InvalidFieldName.SMTP_SERVER);
        }
        if (this.serverSettings.getOutgoingPort().length() == 0) {
            arrayList.add(InvalidFieldName.SMTP_PORT);
        }
        if (!isCaptchaCodeRequired || ((str = this.captchaCode) != null && str.length() != 0)) {
            return arrayList;
        }
        arrayList.add(InvalidFieldName.CODE);
        return arrayList;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final MailProtocol getProtocol() {
        return this.protocol;
    }

    @NotNull
    public final ServerSettings getServerSettings() {
        return this.serverSettings;
    }

    public /* synthetic */ CustomServerParams(String str, String str2, MailProtocol mailProtocol, ServerSettings serverSettings, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, mailProtocol, serverSettings, (i10 & 16) != 0 ? null : str3, (i10 & 32) != 0 ? null : str4);
    }
}
