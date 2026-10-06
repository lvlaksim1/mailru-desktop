package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001:\u0001\u0011B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/ChildboxListCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/serverapi/ServerCommandEmailParams;", "", "Lru/mail/data/cmd/server/ChildboxListCommand$ChildrenAccount;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "ChildrenAccount", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/swa_default_host", defSchemeStrRes = "string/swa_default_scheme", prefKey = "swa")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "childbox", "list"})
public final class ChildboxListCommand extends ServerCommandBase<ServerCommandEmailParams, List<? extends ChildrenAccount>> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/server/ChildboxListCommand$ChildrenAccount;", "", "login", "", "firstName", "lastName", "image", "mail_counter", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getLogin", "()Ljava/lang/String;", "getFirstName", "getLastName", "getImage", "getMail_counter", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChildrenAccount {
        public static final int $stable = 0;

        @NotNull
        private final String firstName;

        @NotNull
        private final String image;

        @NotNull
        private final String lastName;

        @NotNull
        private final String login;
        private final int mail_counter;

        public ChildrenAccount(@NotNull String login, @NotNull String firstName, @NotNull String lastName, @NotNull String image, int i10) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Intrinsics.checkNotNullParameter(image, "image");
            this.login = login;
            this.firstName = firstName;
            this.lastName = lastName;
            this.image = image;
            this.mail_counter = i10;
        }

        public static /* synthetic */ ChildrenAccount copy$default(ChildrenAccount childrenAccount, String str, String str2, String str3, String str4, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = childrenAccount.login;
            }
            if ((i11 & 2) != 0) {
                str2 = childrenAccount.firstName;
            }
            if ((i11 & 4) != 0) {
                str3 = childrenAccount.lastName;
            }
            if ((i11 & 8) != 0) {
                str4 = childrenAccount.image;
            }
            if ((i11 & 16) != 0) {
                i10 = childrenAccount.mail_counter;
            }
            int i12 = i10;
            String str5 = str3;
            return childrenAccount.copy(str, str2, str5, str4, i12);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFirstName() {
            return this.firstName;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLastName() {
            return this.lastName;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getMail_counter() {
            return this.mail_counter;
        }

        @NotNull
        public final ChildrenAccount copy(@NotNull String login, @NotNull String firstName, @NotNull String lastName, @NotNull String image, int mail_counter) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Intrinsics.checkNotNullParameter(image, "image");
            return new ChildrenAccount(login, firstName, lastName, image, mail_counter);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChildrenAccount)) {
                return false;
            }
            ChildrenAccount childrenAccount = (ChildrenAccount) other;
            return Intrinsics.areEqual(this.login, childrenAccount.login) && Intrinsics.areEqual(this.firstName, childrenAccount.firstName) && Intrinsics.areEqual(this.lastName, childrenAccount.lastName) && Intrinsics.areEqual(this.image, childrenAccount.image) && this.mail_counter == childrenAccount.mail_counter;
        }

        @NotNull
        public final String getFirstName() {
            return this.firstName;
        }

        @NotNull
        public final String getImage() {
            return this.image;
        }

        @NotNull
        public final String getLastName() {
            return this.lastName;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        public final int getMail_counter() {
            return this.mail_counter;
        }

        public int hashCode() {
            return (((((((this.login.hashCode() * 31) + this.firstName.hashCode()) * 31) + this.lastName.hashCode()) * 31) + this.image.hashCode()) * 31) + Integer.hashCode(this.mail_counter);
        }

        @NotNull
        public String toString() {
            return "ChildrenAccount(login=" + this.login + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", image=" + this.image + ", mail_counter=" + this.mail_counter + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildboxListCommand(@NotNull Context context, @NotNull ServerCommandEmailParams params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public List<ChildrenAccount> onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("children");
            if (jSONArrayOptJSONArray != null) {
                int length = jSONArrayOptJSONArray.length();
                for (int i10 = 0; i10 < length; i10++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i10);
                    String string = jSONObject2.getString("login");
                    String string2 = jSONObject2.getString("first_name");
                    String string3 = jSONObject2.getString("last_name");
                    String string4 = jSONObject2.getString("image");
                    int i11 = jSONObject2.getInt("mail_counter");
                    Intrinsics.checkNotNull(string);
                    Intrinsics.checkNotNull(string2);
                    Intrinsics.checkNotNull(string3);
                    Intrinsics.checkNotNull(string4);
                    arrayList.add(new ChildrenAccount(string, string2, string3, string4, i11));
                }
            }
            return arrayList;
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
