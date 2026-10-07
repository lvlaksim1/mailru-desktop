package ru.mail.data.transport;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ru.mail.analytics.MetaSearchAnalyticsHolder;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.data.cmd.AttachRequest;
import ru.mail.data.cmd.fs.AttachRequestCompositeCommand;
import ru.mail.data.cmd.server.AttachRequestCommand;
import ru.mail.data.cmd.server.BatchSmartStatusCommand;
import ru.mail.data.cmd.server.DirectoriesListRequest;
import ru.mail.data.cmd.server.FolderLoginCommandImpl;
import ru.mail.data.cmd.server.FoldersLogoutAllCmd;
import ru.mail.data.cmd.server.GetSocialAndServicesPushFiltersCommand;
import ru.mail.data.cmd.server.GetSuggestionsCommand;
import ru.mail.data.cmd.server.LoadPreviewCommandTornado;
import ru.mail.data.cmd.server.MailMessageRequestCommand;
import ru.mail.data.cmd.server.MessagesSearchCommand;
import ru.mail.data.cmd.server.MessagesSearchCommandNew;
import ru.mail.data.cmd.server.MessagesStatusCommand;
import ru.mail.data.cmd.server.RequestInitiator;
import ru.mail.data.cmd.server.TornadoBaseMoveMessage;
import ru.mail.data.cmd.server.TornadoCleanFolder;
import ru.mail.data.cmd.server.TornadoMoveMessage;
import ru.mail.data.cmd.server.TornadoMoveMessageCommandGroup;
import ru.mail.data.cmd.server.TornadoNoSpam;
import ru.mail.data.cmd.server.TornadoSpamAbuse;
import ru.mail.data.cmd.server.UnsubscribeMessageCommand;
import ru.mail.data.cmd.server.pusher.SendPushSettingsCmdImpl;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailboxSearch;
import ru.mail.data.transport.send.HttpSendCommandFactory;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.imageloader.ImageParameters;
import ru.mail.imageloader.cmd.LoadPreviewCommand;
import ru.mail.imageloader.downloader.DirectUrlImageDownloaderDelegate;
import ru.mail.logic.cmd.LoadMailsParams;
import ru.mail.logic.cmd.MarkMailsCmd;
import ru.mail.logic.cmd.MarkNoSpamRequest;
import ru.mail.logic.cmd.SearchSuggestionsCmd;
import ru.mail.logic.cmd.SyncFoldersWithMoveFlagCommand;
import ru.mail.logic.cmd.SyncMarkSpamRequest;
import ru.mail.logic.cmd.SyncMovedThreadsCmd;
import ru.mail.logic.cmd.SyncPendingOperationCmd;
import ru.mail.logic.cmd.SyncUnsubscribeMessageRequest;
import ru.mail.logic.cmd.attachments.ProgressData;
import ru.mail.logic.cmd.karma.SaveKarmaEffectCmd;
import ru.mail.logic.cmd.sync.SyncPendingActionsCommandGroup;
import ru.mail.logic.cmd.sync.threads.SyncMarkedThreadsCmd;
import ru.mail.logic.content.AttachInformation;
import ru.mail.logic.content.FolderLogin;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.SendMessageParams;
import ru.mail.logic.content.feature.features.MediaQueriesFeature;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.search.GetSearchSuggestionsCmd;
import ru.mail.logic.search.GetSearchSuggestionsCmdImpl;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.mails.R;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.FolderMatcherImpl;
import ru.mail.util.Limits;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.mail.utils.TimeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class HttpTransport implements Transport {
    private static final Log LOG = Log.getLog("HttpTransport");
    private final HttpSendCommandFactory mSendCommandFactory = new HttpSendCommandFactory();

    private void getAuthToken(Context context, AccountManagerWrapper accountManagerWrapper, Account account) throws OperationCanceledException, IOException, AuthenticatorException {
        if (accountManagerWrapper.getAuthToken(account, "ru.mail", getOptionBundle(context), false, null, null).getResult() == null) {
            throw new RuntimeException("Auth token bundle is null");
        }
    }

    private Configuration getConfiguration(Context context) {
        return ConfigurationRepository.from(context).getConfiguration();
    }

    private Bundle getOptionBundle(Context context) {
        Bundle bundle = new Bundle();
        new Authenticator.OAuthSuppressSetterGetter(bundle).setDomainsSuppressed(ConfigurationRepository.from(context).getConfiguration().getExistingLoginSuppressedOauth());
        return bundle;
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createClearFolderCommand(Context context, MailboxContext mailboxContext, long j10) {
        return new TornadoCleanFolder(context, new TornadoCleanFolder.Params(new long[]{j10}, MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), MigrateToPostUtils.is12161Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createFolderLoginCmd(Context context, MailboxContext mailboxContext, FolderLogin folderLogin) {
        return new FolderLoginCommandImpl(context, new FolderLoginCommandImpl.Params(folderLogin, MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), MigrateToPostUtils.is12161Enabled(context), context.getString(R.string.mailbox_folder_password_error));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createFoldersLogoutCmd(Context context, MailboxContext mailboxContext) {
        return new FoldersLogoutAllCmd(context, mailboxContext);
    }

    @Override // ru.mail.data.transport.Transport
    public GetSearchSuggestionsCmd createGetSearchSuggestionsCmd(Context context, MailboxContext mailboxContext, String str) {
        return new GetSearchSuggestionsCmdImpl(context, mailboxContext, new GetSuggestionsCommand(context, new GetSuggestionsCommand.Params(mailboxContext, CommonDataManager.from(context), str), MigrateToPostUtils.is12168Enabled(context)));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createGetSocialAndServicesPushFiltersCommand(Context context, String str, FolderState folderState) {
        return new GetSocialAndServicesPushFiltersCommand(context, new GetSocialAndServicesPushFiltersCommand.Params(str, folderState, BuildConfigVariablesHolder.pusherAppName));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createLoadAttachCmd(Context context, MailboxContext mailboxContext, String str, String str2, AttachInformation attachInformation, ProgressListener<AttachRequest.ProgressData> progressListener) {
        return new AttachRequestCompositeCommand(context, new AttachRequestCommand.Params(attachInformation, str, str2, MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), progressListener, getConfiguration(context).isUnifiedAttachDownloadEnabled());
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createLoadFoldersCommand(Context context, MailboxContext mailboxContext) {
        FolderMatcherImpl folderMatcherImpl = new FolderMatcherImpl(context);
        ServerCommandEmailParams serverCommandEmailParams = new ServerCommandEmailParams(MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext));
        return new DirectoriesListRequest(context, serverCommandEmailParams, MigrateToPostUtils.is12161Enabled(context), folderMatcherImpl, CommonDataManager.from(context).getFoldersManager(), CommonDataManager.from(context).isUserChild(serverCommandEmailParams.getLogin()));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createLoadMessageContentCommand(Context context, MailboxContext mailboxContext, String str, RequestInitiator requestInitiator) {
        return new MailMessageRequestCommand(context, new MailMessageRequestCommand.Params(mailboxContext, CommonDataManager.from(context), SharedFoldersModuleEntryPoint.folderGrantsManager(context), str, false, getConfiguration(context).getShouldShowCalendarThumbnailInHtml(), CommonDataManager.from(context).isFeatureSupported(MediaQueriesFeature.INSTANCE, context), false, requestInitiator == RequestInitiator.PUSH), MigrateToPostUtils.is11954Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createLoadMessagesRequestCommand(Context context, LoadMailsParams<Long> loadMailsParams, RequestInitiator requestInitiator) {
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        MessagesStatusCommand.Params params = new MessagesStatusCommand.Params(loadMailsParams, Limits.from(context).getSnippetLimit(), requestInitiator, commonDataManagerFrom);
        return new MessagesStatusCommand(context, params, MigrateToPostUtils.is11954Enabled(context), ConfigurationRepository.from(context).getConfiguration().getColoredTagsConfig().getParseByDefault(), commonDataManagerFrom.isUserChild(params.getLogin()), new FolderMatcherImpl(context), commonDataManagerFrom.getFoldersManager());
    }

    @Override // ru.mail.data.transport.Transport
    public ServerCommandBase<?, ?> createLoadPreviewCommand(Context context, MailboxContext mailboxContext, ImageParameters imageParameters, OutputStream outputStream) {
        return new LoadPreviewCommand(outputStream, context, new LoadPreviewCommand.Params(imageParameters.getUrl(), null, 0L, MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), DirectUrlImageDownloaderDelegate.getAvatarLoadPeriodOverrideSeconds(context), MigrateToPostUtils.is12179Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public ServerCommandBase<?, ?> createLoadPreviewCommandTornado(Context context, MailboxContext mailboxContext, ImageParameters imageParameters, OutputStream outputStream) {
        return new LoadPreviewCommandTornado(outputStream, context, new LoadPreviewCommandTornado.Params(imageParameters.getUrl(), null, 0L, MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), MigrateToPostUtils.is12179Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ? extends CommandStatus<?>> createMarkNoSpamCommand(Context context, MailboxContext mailboxContext, String[] strArr) {
        return new TornadoNoSpam(context, new TornadoBaseMoveMessage.Params(mailboxContext, CommonDataManager.from(context), strArr), MigrateToPostUtils.is12158Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ? extends CommandStatus<?>> createMarkSpamCommand(Context context, MailboxContext mailboxContext, long j10, String[] strArr) {
        return new TornadoSpamAbuse(context, new TornadoSpamAbuse.Params(mailboxContext, CommonDataManager.from(context), Long.valueOf(j10), strArr), MigrateToPostUtils.is12158Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ? extends CommandStatus<?>> createMoveCommand(Context context, MailboxContext mailboxContext, MailBoxFolder mailBoxFolder, String[] strArr) {
        return new TornadoMoveMessageCommandGroup(context, new TornadoMoveMessage.Params(mailboxContext, CommonDataManager.from(context), mailBoxFolder.getOwner(), mailBoxFolder.getId().longValue(), strArr));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createSearchCommand(Context context, MailboxContext mailboxContext, int i10, int i11, int i12, int i13, MailboxSearch mailboxSearch) {
        return mailboxSearch.getIsNewSearchEnabled().booleanValue() ? new MessagesSearchCommandNew(context, new MessagesSearchCommandNew.Params(mailboxContext, CommonDataManager.from(context), mailboxSearch, MessagesSearchCommandNew.Params.getQueryParameter(mailboxSearch), MessagesSearchCommandNew.Params.convertFlagsToJson(mailboxSearch), i13, Integer.valueOf(i10), Integer.valueOf(i11), MetaSearchAnalyticsHolder.INSTANCE.getAppQid(), getConfiguration(context).getColoredTagsConfig().getEnabled()), MigrateToPostUtils.is12168Enabled(context)) : new MessagesSearchCommand(context, new MessagesSearchCommand.Params(mailboxContext, CommonDataManager.from(context), mailboxSearch, i10, i11, i13), MigrateToPostUtils.is12168Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, Object> createSearchSuggestionsCommand(Context context, MailboxContext mailboxContext) {
        return new SearchSuggestionsCmd(context, mailboxContext, MigrateToPostUtils.is12168Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public CommandGroup createSendCommand(@NonNull Context context, @NonNull MailboxContext mailboxContext, @NonNull SendMessageParams sendMessageParams, @Nullable ProgressListener<ProgressData> progressListener) {
        LOG.d("Create send command with params " + sendMessageParams);
        return this.mSendCommandFactory.create(context, mailboxContext, sendMessageParams, progressListener);
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createSendPushSettingsCmd(Context context, MailboxContext mailboxContext) {
        return new SendPushSettingsCmdImpl(context, mailboxContext);
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ?> createSmartLoadCommand(Context context, LoadMailsParams<Long> loadMailsParams, RequestInitiator requestInitiator) {
        BatchSmartStatusCommand.Params.Folder folder = new BatchSmartStatusCommand.Params.Folder(loadMailsParams.getContainerId(), loadMailsParams.getOffset(), loadMailsParams.getLimit());
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        BatchSmartStatusCommand.Params params = new BatchSmartStatusCommand.Params(loadMailsParams, commonDataManagerFrom, Collections.singletonList(folder), Limits.from(context).getSnippetLimit(), requestInitiator, commonDataManagerFrom.getRemoveEmojisParam());
        ConfigurationWithRawData configuration = ConfigurationRepository.from(context).getConfiguration();
        return new BatchSmartStatusCommand(context, params, MigrateToPostUtils.is11954Enabled(context), configuration.getColoredTagsConfig().getParseByDefault(), configuration.getSubscriptionsPromoSheetConfig().getEnabled(), commonDataManagerFrom.getFoldersManager(), commonDataManagerFrom.isUserChild(params.getLogin()), new FolderMatcherImpl(context));
    }

    @Override // ru.mail.data.transport.Transport
    public List<Command<?, ?>> createSyncChangesCommands(Context context, MailboxContext mailboxContext, boolean z10) {
        return Arrays.asList(new SaveKarmaEffectCmd(context, mailboxContext.getProfile().getLogin(), TimeUtils.Time.from(context)), new SyncPendingActionsCommandGroup(context, mailboxContext), new SyncMovedThreadsCmd(context, mailboxContext), new SyncMarkedThreadsCmd(context, mailboxContext), new MarkMailsCmd(context, mailboxContext, z10), new SyncMarkSpamRequest(context, mailboxContext, z10), new MarkNoSpamRequest(context, mailboxContext, z10), new SyncUnsubscribeMessageRequest(context, mailboxContext, z10), new SyncFoldersWithMoveFlagCommand(context, mailboxContext, z10), new SyncPendingOperationCmd(context, mailboxContext));
    }

    @Override // ru.mail.data.transport.Transport
    public Command<?, ? extends CommandStatus<?>> createUnsubscribeCommand(Context context, MailboxContext mailboxContext, String[] strArr) {
        return new UnsubscribeMessageCommand(context, new UnsubscribeMessageCommand.Params(mailboxContext, CommonDataManager.from(context), strArr), MigrateToPostUtils.is12159Enabled(context));
    }

    @Override // ru.mail.data.transport.Transport
    public boolean isValidMessageId(String str) {
        return true;
    }

    @Override // ru.mail.data.transport.Transport
    public boolean logout(Context context, Account account) {
        String str = account.name + "3465436";
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context);
        accountManagerWrapper.setAuthToken(account, "ru.mail", null);
        accountManagerWrapper.setAuthToken(account, "ru.mail.oauth2.access", null);
        accountManagerWrapper.setAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, null);
        MailSecondStepFragment.setTsaCookie(context, "111111", account.name);
        accountManagerWrapper.setPassword(account, str);
        try {
            getAuthToken(context, accountManagerWrapper, account);
            return accountManagerWrapper.getPassword(account) == null;
        } catch (AuthenticatorException | OperationCanceledException | IOException e10) {
            LOG.e("logout error", e10);
            throw new RuntimeException(e10);
        }
    }
}
