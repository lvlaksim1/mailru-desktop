package ru.mail.data.cmd.server;

import android.content.Context;
import kotlin.Deprecated;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class UserEditCommandFactory {
    private final Context mContext;
    private final MailboxContext mMailboxContext;

    public UserEditCommandFactory(Context context, MailboxContext mailboxContext) {
        this.mContext = context;
        this.mMailboxContext = mailboxContext;
    }

    public UserEditCommand createAcceptPrivacyPolicyCommand(boolean z10) {
        PrivacyPolicyUserEditParams privacyPolicyUserEditParams = new PrivacyPolicyUserEditParams(this.mMailboxContext, CommonDataManager.from(this.mContext), z10);
        Context context = this.mContext;
        return new UserEditCommand(context, privacyPolicyUserEditParams, MigrateToPostUtils.is12152Enabled(context));
    }

    public UserEditCommand createAgreeReceiveNewslettersCommand(boolean z10) {
        ReceiveNewslettersUserEditParams receiveNewslettersUserEditParams = new ReceiveNewslettersUserEditParams(this.mMailboxContext, CommonDataManager.from(this.mContext), z10);
        Context context = this.mContext;
        return new UserEditCommand(context, receiveNewslettersUserEditParams, MigrateToPostUtils.is12152Enabled(context));
    }

    public UserEditCommand createChangeNameCommand(String str, String str2) {
        NameUserEditParams nameUserEditParams = new NameUserEditParams(this.mMailboxContext, CommonDataManager.from(this.mContext), str, str2);
        Context context = this.mContext;
        return new UserEditCommand(context, nameUserEditParams, MigrateToPostUtils.is12152Enabled(context));
    }

    public UserEditCommand createMailCheckDisabledCommand(boolean z10) {
        MailCheckUserEditParams mailCheckUserEditParams = new MailCheckUserEditParams(this.mMailboxContext, CommonDataManager.from(this.mContext), !z10);
        Context context = this.mContext;
        return new UserEditCommand(context, mailCheckUserEditParams, MigrateToPostUtils.is12152Enabled(context));
    }

    @Deprecated(message = "You should use new UserEditMetathreadsCommand for metathreads management")
    public UserEditCommand createMetaThreadsEnableCommand(boolean z10) {
        MetaThreadsEditParams metaThreadsEditParams = new MetaThreadsEditParams(this.mMailboxContext, CommonDataManager.from(this.mContext), z10);
        Context context = this.mContext;
        return new UserEditCommand(context, metaThreadsEditParams, MigrateToPostUtils.is12152Enabled(context));
    }
}
