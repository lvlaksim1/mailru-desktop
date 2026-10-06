package ru.mail.addressbook.backup.server;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.kit.result.tools.Result;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00030\u0001:\u0001\u0011B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/addressbook/backup/server/UploadContactsToServerCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/addressbook/backup/server/UploadContactsToServerCommand$Params;", "Lru/mail/kit/result/tools/Result;", "", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/addressbook/backup/server/UploadContactsToServerCommand$Params;Z)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Params", "addressbook_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "ab", "contacts", RbParams.Default.URL_PARAM_KEY_DEVICE, "backup"})
public final class UploadContactsToServerCommand extends PostServerRequest<Params, Result<Unit, Unit>> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadContactsToServerCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
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
    public Result<Unit, Unit> onPostExecuteRequest(@Nullable NetworkCommand.Response resp) {
        return Result.INSTANCE.success();
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u0013"}, d2 = {"Lru/mail/addressbook/backup/server/UploadContactsToServerCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "", "contacts", "", "Lru/mail/addressbook/backup/server/ContactNwDto;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getDeviceId", "()Ljava/lang/String;", "contactsJSON", "getContactsJSON$annotations", "()V", "getContactsJSON", "addressbook_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "contacts")
        @NotNull
        private final String contactsJSON;

        @Param(method = HttpMethod.POST, name = "device_id")
        @NotNull
        private final String deviceId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String deviceId, @NotNull List<ContactNwDto> contacts, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(contacts, "contacts");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            this.deviceId = deviceId;
            String string = new ContactsToJsonParser().parse(contacts).toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            this.contactsJSON = string;
        }

        @NotNull
        public final String getContactsJSON() {
            return this.contactsJSON;
        }

        @NotNull
        public final String getDeviceId() {
            return this.deviceId;
        }

        public static /* synthetic */ void getContactsJSON$annotations() {
        }
    }
}
