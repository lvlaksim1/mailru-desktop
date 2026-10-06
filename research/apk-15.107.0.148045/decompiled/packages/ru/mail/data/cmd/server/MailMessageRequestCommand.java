package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.Authenticator;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.parser.MailMessageContentParser;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.data.entities.ReaderMode;
import ru.mail.glasha.domain.managers.FolderGrantsManager;
import ru.mail.js.dependencies.MessageJsModuleEntryPoint;
import ru.mail.jsscriptfetcher.JsScriptFetcherEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.content.ContextualMailBoxFolder;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.VirtualFoldersContainer;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.AccountAndIDParams;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "message"})
@WithSampling
public class MailMessageRequestCommand extends ServerCommandBase<Params, MailMessageContent> {
    private static final Log LOG = Log.getLog("MailMessageRequestCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.GET, name = "let_body_type")
        private static final String BODY_TYPE = "let_body";

        @Param(method = HttpMethod.GET, name = "no_banner")
        private static final String NO_BANNER = "Y";

        @Param(method = HttpMethod.GET, name = "htmlencoded")
        private static final boolean mHtmlEncoded = false;

        @Param(name = "bulk_show_images")
        private final int mBulkShowImages;
        private final boolean mBypassVpnBlocking;

        @Param(name = "disable_quotation_parser")
        private final boolean mDisabledQuotationParser;

        @Param(method = HttpMethod.HEADER_ADD, name = "X-DomPurify-Version")
        private final String mDomPurifyHeader;

        @Param(method = HttpMethod.GET, name = "folder_id")
        private final String mFolderId;

        @Param(method = HttpMethod.GET, name = "thumbnails")
        private final int mHtmlThumbnails;

        @Param(method = HttpMethod.GET, name = "id")
        private final String mId;

        @Param(method = HttpMethod.GET, name = "mark_read")
        private final boolean mMarkRead;

        @Param(name = "remove_emoji_opts")
        private final String mRemoveEmojiFlags;

        @Param(method = HttpMethod.GET, name = "use_color_scheme")
        private final int mUseColorScheme;

        @Param(method = HttpMethod.GET, name = "ajax_call")
        private static final String AJAX_CALL = String.valueOf(1);

        @Param(method = HttpMethod.GET, name = "multi_msg_prev")
        private static final String MULTI_MSG_PREV = String.valueOf(0);

        @Param(method = HttpMethod.GET, name = "multi_msg_past")
        private static final String MULTI_MSG_PAST = String.valueOf(0);

        @Param(method = HttpMethod.GET, name = "mobile")
        private static final String MOBILE = String.valueOf(1);

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull FolderGrantsManager folderGrantsManager, @Nullable String str, boolean z10, boolean z11, boolean z12, boolean z13) {
            this(mailboxContext, dataManager, folderGrantsManager, str, z10, z11, z12, z13, false);
        }

        @Nullable
        private String getFolderIdIfNecessary(long j10, @NotNull FolderGrantsManager folderGrantsManager) {
            String strValueOf = String.valueOf(j10);
            if (VirtualFoldersContainer.isVirtual(j10)) {
                strValueOf = VirtualFoldersContainer.getSearchId(Long.valueOf(j10));
            }
            if (folderGrantsManager.isSharedFolder(strValueOf)) {
                return strValueOf;
            }
            return null;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mMarkRead != params.mMarkRead) {
                return false;
            }
            String str = this.mId;
            String str2 = params.mId;
            return str == null ? str2 == null : str.equals(str2);
        }

        public boolean getBulkShowImages(boolean z10, MailboxContext mailboxContext, DataManager dataManager) {
            if (z10) {
                return true;
            }
            if (ContextualMailBoxFolder.isSpam(mailboxContext.getFolderId())) {
                return false;
            }
            return ReaderMode.INSTANCE.fromString(Authenticator.getAccountManagerWrapper(dataManager.getApplicationContext()).getUserData(new Account(mailboxContext.getProfile().getLogin(), BuildConfigVariablesHolder.accountType), MailboxProfile.ACCOUNT_KEY_READER_MODE)) == ReaderMode.OFF;
        }

        public boolean getBypassVpnBlocking() {
            return this.mBypassVpnBlocking;
        }

        public String getDomPurifyHeader(DataManager dataManager) {
            Application applicationContext = dataManager.getApplicationContext();
            ConfigurationRepository configurationRepository = (ConfigurationRepository) Locator.from(applicationContext).locate(ConfigurationRepository.class);
            boolean interceptorEnable = configurationRepository.getConfiguration().getJsScriptFetchingConfig().getInterceptorEnable();
            boolean jsTransformMailJsonSchemeEnabled = configurationRepository.getConfiguration().getJsScriptFetchingConfig().getJsTransformMailJsonSchemeEnabled();
            String domPurifyVersion = JsScriptFetcherEntryPoint.from(applicationContext).jsScriptFetcherInteractor().getDomPurifyVersion();
            if (interceptorEnable && jsTransformMailJsonSchemeEnabled) {
                return domPurifyVersion;
            }
            return null;
        }

        public boolean getStateOfQuotationParser(DataManager dataManager) {
            ConfigurationRepository configurationRepository = (ConfigurationRepository) Locator.from(dataManager.getApplicationContext()).locate(ConfigurationRepository.class);
            return configurationRepository.getConfiguration().isBackendQuotationParserDisabled() && configurationRepository.getConfiguration().isSanitizedScriptForAllAccountEnabled();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mId;
            return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + (this.mMarkRead ? 1 : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public String toString() {
            return "Params{super=" + super.toString() + ", mId='" + this.mId + "', mMarkRead=" + this.mMarkRead + AbstractJsonLexerKt.END_OBJ;
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull FolderGrantsManager folderGrantsManager, @Nullable String str, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mId = str;
            this.mMarkRead = z10;
            this.mHtmlThumbnails = z11 ? 1 : 0;
            this.mUseColorScheme = z12 ? 1 : 0;
            this.mFolderId = getFolderIdIfNecessary(mailboxContext.getFolderId(), folderGrantsManager);
            this.mRemoveEmojiFlags = dataManager.getRemoveEmojisParam();
            this.mBulkShowImages = getBulkShowImages(z13, mailboxContext, dataManager) ? 1 : 0;
            this.mDisabledQuotationParser = getStateOfQuotationParser(dataManager);
            this.mDomPurifyHeader = getDomPurifyHeader(dataManager);
            this.mBypassVpnBlocking = z14;
        }

        public Params(MailboxContext mailboxContext, DataManager dataManager, FolderGrantsManager folderGrantsManager, String str) {
            this(mailboxContext, dataManager, folderGrantsManager, str, false, false, false, false);
        }

        public Params(MailboxContext mailboxContext, DataManager dataManager, FolderGrantsManager folderGrantsManager, String str, boolean z10, boolean z11) {
            this(mailboxContext, dataManager, folderGrantsManager, str, false, z10, z11, false);
        }

        public Params(MailboxContext mailboxContext, DataManager dataManager, FolderGrantsManager folderGrantsManager, String str, boolean z10) {
            this(mailboxContext, dataManager, folderGrantsManager, str, false, false, false, z10);
        }
    }

    public MailMessageRequestCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    protected boolean getBypassVpn() {
        return ((Params) getParams()).getBypassVpnBlocking();
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, MailMessageContent>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, MailMessageContent>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.MailMessageRequestCommand.1
            /* JADX WARN: Code duplicated, block: B:17:0x003b  */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                byte b10;
                try {
                    String key = ThreadDelegateUtil.getKey(jSONObject);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(key);
                    String string = jSONObject2.getString("error");
                    String string2 = jSONObject2.getString("value");
                    int iHashCode = key.hashCode();
                    if (iHashCode != -1268966290) {
                        if (iHashCode == 3355 && key.equals("id")) {
                            b10 = 0;
                        } else {
                            b10 = -1;
                        }
                    } else if (key.equals("folder")) {
                        b10 = 1;
                    } else {
                        b10 = -1;
                    }
                    if (b10 != 0) {
                        if (b10 != 1) {
                            return super.onBadRequest(jSONObject);
                        }
                        if ("not_open".equals(string)) {
                            return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(Long.parseLong(string2)));
                        }
                    } else {
                        if ("not_exist".equals(string)) {
                            return new MailCommandStatus.NO_MSG(new AccountAndIDParams(string2, ((Params) MailMessageRequestCommand.this.getParams()).getLogin()));
                        }
                        if ("no_body".equals(string)) {
                            return new MailCommandStatus.NO_BODY();
                        }
                    }
                    return super.onBadRequest(jSONObject);
                } catch (NumberFormatException e10) {
                    e = e10;
                    return new CommandStatus.ERROR(e);
                } catch (JSONException e11) {
                    e = e11;
                    return new CommandStatus.ERROR(e);
                }
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    boolean isTransformJsonMailSchemeEnabled() {
        return ((ConfigurationRepository) Locator.from(getContext()).locate(ConfigurationRepository.class)).getConfiguration().getJsScriptFetchingConfig().getJsTransformMailJsonSchemeEnabled();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        return ((Params) getParams()).toString();
    }

    protected MailMessageRequestCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public MailMessageContent onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            AtomicReference atomicReference = new AtomicReference(response.getRespString());
            if (isTransformJsonMailSchemeEnabled()) {
                try {
                    atomicReference.set(MessageJsModuleEntryPoint.transfromMailJsonScriptAppender(getContext()).getTransformInputMailBody(response.getRespString()));
                } catch (Exception e10) {
                    LOG.d("Error content parse, empty message return:" + e10);
                }
            }
            return new MailMessageContentParser(((Params) getParams()).getLogin(), getContext()).parse(new JSONObject((String) atomicReference.get()).getJSONObject("body"));
        } catch (JSONException e11) {
            LOG.e("whtf??", e11);
            throw new NetworkCommand.PostExecuteException("json", e11);
        }
    }
}
