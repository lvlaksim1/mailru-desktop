package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class GetCloudAttachmentInfo extends ServerCommandBase<Params, Result> {
    private static final Pattern HASH_PATTERN = Pattern.compile("hash:(.*)");
    private static final Pattern SIZE_PATTERN = Pattern.compile("size:(.*)");
    private static final Pattern PART_SIZE_PATTERN = Pattern.compile("part_size:(.*)");
    private static final Pattern URL_PATTERN = Pattern.compile("url:(.*)");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        protected final String mAttachmentHash;
        private final String mLoaderUrl;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mAttachmentHash = str;
            this.mLoaderUrl = str2;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            String str = this.mAttachmentHash;
            if (str == null ? params.mAttachmentHash != null : !str.equals(params.mAttachmentHash)) {
                return false;
            }
            String str2 = this.mLoaderUrl;
            String str3 = params.mLoaderUrl;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        public String getAttachmentHash() {
            return this.mAttachmentHash;
        }

        public String getLoaderUrl() {
            return this.mLoaderUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mLoaderUrl;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mAttachmentHash;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mHash;

        @Nullable
        private final String mLoaderUrl;
        private final long mUploadedSize;

        public Result(String str, long j10, @Nullable String str2) {
            this.mHash = str;
            this.mUploadedSize = j10;
            this.mLoaderUrl = str2;
        }

        public String getHash() {
            return this.mHash;
        }

        @Nullable
        public String getLoaderUrl() {
            return this.mLoaderUrl;
        }

        public long getUploadedSize() {
            return this.mUploadedSize;
        }
    }

    public GetCloudAttachmentInfo(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected List<String> getAllowedGetParams() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("access_token");
        return arrayList;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new CloudHostProvider(super.getHostProvider(), ((Params) getParams()).getLoaderUrl(), XmailMigrationPromoSheet.BUTTON_INFO, ((Params) getParams()).getAttachmentHash());
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new CloudResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.GetCloudAttachmentInfo.1
            @Override // ru.mail.data.cmd.server.CloudResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                return getResponse().getStatusCode() == 404 ? new UploadCloudRequest.CHUNK_NOT_FOUND() : super.process();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        long j10;
        String strGroup;
        String respString = response.getRespString();
        Matcher matcher = HASH_PATTERN.matcher(respString);
        if (!matcher.find()) {
            throw new NetworkCommand.PostExecuteException("Unable to parse file hash");
        }
        String strGroup2 = matcher.group(1);
        Matcher matcher2 = PART_SIZE_PATTERN.matcher(respString);
        if (matcher2.find()) {
            try {
                j10 = Long.parseLong(matcher2.group(1));
                Matcher matcher3 = URL_PATTERN.matcher(respString);
                if (!matcher3.find()) {
                    throw new NetworkCommand.PostExecuteException("Unable to parse loader url");
                }
                strGroup = matcher3.group(1);
            } catch (NumberFormatException e10) {
                throw new NetworkCommand.PostExecuteException("Unable to parse file part size", e10);
            }
        } else {
            Matcher matcher4 = SIZE_PATTERN.matcher(respString);
            if (!matcher4.find()) {
                throw new NetworkCommand.PostExecuteException("Unable to parse file part size");
            }
            try {
                j10 = Long.parseLong(matcher4.group(1));
                strGroup = null;
            } catch (NumberFormatException e11) {
                throw new NetworkCommand.PostExecuteException("Unable to parse file size", e11);
            }
        }
        return new Result(strGroup2, j10, strGroup);
    }
}
