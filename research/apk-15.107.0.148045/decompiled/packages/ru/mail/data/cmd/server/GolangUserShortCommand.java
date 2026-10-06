package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailLoginFragment;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.parser.BoxQuotasParser;
import ru.mail.data.cmd.server.parser.MetaThreadsSettingsParser;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.data.entities.PrivacySettings;
import ru.mail.data.entities.ReaderMode;
import ru.mail.dependencies.NetworkEntryPoint;
import ru.mail.logic.SocialBind;
import ru.mail.logic.child.DisablingParentalControlMapper;
import ru.mail.logic.child.DisablingParentalMode;
import ru.mail.logic.child.ParentalMode;
import ru.mail.logic.child.ParentalModeStorageMapper;
import ru.mail.logic.content.MetaThreadEnableState;
import ru.mail.logic.mailboxquotas.BoxQuotas;
import ru.mail.logic.mailboxquotas.SharedPrefMailQuotasStorage;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002%&B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010#\u001a\u00020$H\u0014R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lru/mail/data/cmd/server/GolangUserShortCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/data/cmd/server/GolangUserShortCommand$UserData;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;)V", "managerWrapper", "Lru/mail/auth/AccountManagerWrapper;", "kotlin.jvm.PlatformType", "account", "Landroid/accounts/Account;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "response", "Lru/mail/network/NetworkCommand$Response;", "parsePrivacySettings", "Lru/mail/data/entities/PrivacySettings;", "userSecurity", "Lorg/json/JSONObject;", "parseMetaThreads", "", "Lru/mail/logic/content/MetaThreadEnableState;", "body", "parseParentalMode", "Lru/mail/logic/child/ParentalMode;", "parseParentControlHasChildren", "", "parseDisableParentalMode", "Lru/mail/logic/child/DisablingParentalMode;", "parseSocialBind", "Lru/mail/logic/SocialBind;", "onDone", "", "UserData", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "user", "short"})
public final class GolangUserShortCommand extends ServerCommandBase<ServerCommandEmailParams, UserData> {

    @NotNull
    private final Account account;
    private final AccountManagerWrapper managerWrapper;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GolangUserShortCommand");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GolangUserShortCommand(@NotNull Context context, @NotNull ServerCommandEmailParams params) {
        super(context, params, MigrateToPostUtils.is12130Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.managerWrapper = Authenticator.getAccountManagerWrapper(context);
        String login = params.getLogin();
        Intrinsics.checkNotNull(login);
        this.account = new Account(login, BuildConfigVariablesHolder.accountType);
    }

    private final DisablingParentalMode parseDisableParentalMode(JSONObject body) {
        String strOptString = body.optString("parental_control_can_disable");
        Intrinsics.checkNotNull(strOptString);
        return DisablingParentalControlMapper.map(strOptString);
    }

    private final List<MetaThreadEnableState> parseMetaThreads(JSONObject body) throws JSONException {
        JSONArray jSONArrayOptJSONArray = body.optJSONArray("metathreads_visible");
        Log log = LOG;
        log.i("Parsing metathreads array: " + jSONArrayOptJSONArray);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            log.w("MetaThreads json array is null or empty!");
            return CollectionsKt.emptyList();
        }
        List<MetaThreadEnableState> list = new MetaThreadsSettingsParser().parse(jSONArrayOptJSONArray);
        Intrinsics.checkNotNullExpressionValue(list, "parse(...)");
        return list;
    }

    private final boolean parseParentControlHasChildren(JSONObject body) {
        return body.optBoolean("parental_control_has_children", false);
    }

    private final ParentalMode parseParentalMode(JSONObject body) {
        return ParentalModeNetworkMapper.map(body.optString("parental_control_mode"));
    }

    private final PrivacySettings parsePrivacySettings(JSONObject userSecurity) {
        String userData = this.managerWrapper.getUserData(this.account, MailboxProfile.ACCOUNT_KEY_READER_MODE);
        ReaderMode.Companion companion = ReaderMode.INSTANCE;
        ReaderMode readerModeFromString = companion.fromString(userData);
        if (!ConfigurationRepository.from(getContext()).getConfiguration().getSecureViewerConfig().getReaderModeEnabled()) {
            ReaderMode readerMode = ReaderMode.OFF;
            return new PrivacySettings(readerMode, readerModeFromString != readerMode);
        }
        JSONObject jSONObjectOptJSONObject = userSecurity.optJSONObject("privacy_settings");
        ReaderMode readerModeFromString2 = companion.fromString(jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString("reader_mode") : null);
        return readerModeFromString2 != readerModeFromString ? new PrivacySettings(readerModeFromString2, true) : new PrivacySettings(readerModeFromString, false);
    }

    private final SocialBind parseSocialBind(JSONObject body) {
        JSONObject jSONObjectOptJSONObject = body.optJSONObject("social_bind");
        return jSONObjectOptJSONObject != null ? new SocialBind(jSONObjectOptJSONObject.optBoolean("vkid"), jSONObjectOptJSONObject.optBoolean("esia")) : new SocialBind(false, false, 3, null);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (statusOK() && !isCancelled()) {
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_USER_TYPE, getOkData().getAccountType());
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_SHOW_ME_ADS_DISABLED, String.valueOf(!getOkData().getIsAdsEnabled()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_TWO_FACTOR_ENABLED, String.valueOf(getOkData().getIsTwoFactorEnabled()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_PARENTAL_MODE, ParentalModeStorageMapper.toString(getOkData().getParentalMode()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_READER_MODE, getOkData().getPrivacySettings().getReaderMode().getValue());
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_CREATED_VIA_VKC, String.valueOf(getOkData().getCreatedViaVkc()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_SOCIAL_BIND, getOkData().getSocialBind().toString());
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_HAS_CHILDREN, String.valueOf(getOkData().getParentControlHasChildren()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_PARENT_CONTROL_CAN_DISABLE, DisablingParentalControlMapper.toString(getOkData().getMDisablingParentalMode()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_REG_DATE, String.valueOf(getOkData().getRegTimestamp()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_BILLING_BITMASK, String.valueOf(getOkData().getBillingBitmask()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_B2B_FLAGS, getOkData().getB2bFlags());
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_SOFT_VKID_BIND, String.valueOf(getOkData().getSoftVkidBind()));
            this.managerWrapper.setUserData(this.account, MailboxProfile.ACCOUNT_KEY_MAIN_PHONE, getOkData().getMainPhone());
            this.managerWrapper.setUserData(this.account, "account_key_first_name", getOkData().getFirstName());
        }
        NetworkEntryPoint.Companion companion = NetworkEntryPoint.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        RequestListenerManager requestListenerManager = companion.requestListenerManager(context);
        CommandStatus<?> result = getResult();
        Intrinsics.checkNotNullExpressionValue(result, "getResult(...)");
        requestListenerManager.pushResponse(this, result, ((ServerCommandEmailParams) getParams()).getLogin());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public UserData onPostExecuteRequest(@NotNull NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String strOptString;
        String str = "";
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            BoxQuotasParser boxQuotasParser = new BoxQuotasParser();
            Intrinsics.checkNotNull(jSONObject);
            BoxQuotas boxLimits = boxQuotasParser.parseBoxLimits(jSONObject);
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            String login = getLogin();
            Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
            SharedPrefMailQuotasStorage sharedPrefMailQuotasStorage = new SharedPrefMailQuotasStorage(context, login);
            if (boxLimits != null) {
                sharedPrefMailQuotasStorage.addBoxLimits(boxLimits);
            } else {
                sharedPrefMailQuotasStorage.clearData();
            }
            if (jSONObject.has(Authenticator.KEY_VKC_ID)) {
                this.managerWrapper.setUserData(this.account, Authenticator.KEY_VKC_ID, jSONObject.getString(Authenticator.KEY_VKC_ID));
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("common_purpose_flags");
            JSONObject jSONObject3 = jSONObject.getJSONObject(BaseSettingsActivity.KEY_PREF_SECURITY);
            UserData userDataWithEnteredBetaProgram = new UserData().withAccountType(jSONObject.getString("account_type")).withVkcId(jSONObject.has(Authenticator.KEY_VKC_ID) ? jSONObject.getString(Authenticator.KEY_VKC_ID) : null).withMailboxCheckDisabled(jSONObject2.optBoolean(MailboxProfile.COL_NAME_DENY_PERSONAL_DATA_PROCESSING, false)).withTheme(jSONObject.getString("theme")).withMetaThreadsEnabled(jSONObject2.optBoolean("metathreads_on", false)).withMetaThreadsStates(parseMetaThreads(jSONObject)).with2FactorEnabled(jSONObject3.optBoolean("2_step_auth", false)).withAdsEnabled(jSONObject.optBoolean("show_me_ads", true)).withSocialBind(parseSocialBind(jSONObject)).withParentalMode(parseParentalMode(jSONObject)).withParentControlHasChildren(parseParentControlHasChildren(jSONObject)).withDisableParentalMode(parseDisableParentalMode(jSONObject)).withEnteredBetaProgram(jSONObject.optBoolean("beta_user", false));
            String strOptString2 = jSONObject.optString("login");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            UserData userDataWithEnteredLogin = userDataWithEnteredBetaProgram.withEnteredLogin(strOptString2);
            String strOptString3 = jSONObject.optString("domain");
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            UserData userDataWithRegDate = userDataWithEnteredLogin.withEnteredDomain(strOptString3).withCreatedViaVkc(jSONObject2.optBoolean("created_via_vkc", false)).withRegDate(jSONObject.optInt("reg_date", 0));
            Intrinsics.checkNotNull(jSONObject3);
            UserData userDataWithBillingBitmask = userDataWithRegDate.withPrivacySettings(parsePrivacySettings(jSONObject3)).withBillingBitmask(jSONObject.optLong(R7Analytics.BILLING_BITMASK, 0L));
            String strOptString4 = jSONObject.optString("b2b_flags");
            Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
            UserData userDataWithSoftVkidBind = userDataWithBillingBitmask.withB2BFlags(strOptString4).withSoftVkidBind(jSONObject2.optBoolean("soft_vkid_bind", false));
            String strOptString5 = jSONObject.optString("phone_main", "");
            Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
            UserData userDataWithMainPhone = userDataWithSoftVkidBind.withMainPhone(strOptString5);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("name");
            if (jSONObjectOptJSONObject != null && (strOptString = jSONObjectOptJSONObject.optString(PreferenceHostProvider.URL_PARAM_FIRST)) != null) {
                str = strOptString;
            }
            return userDataWithMainPhone.withFirstName(str);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b-\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020\u0005J\u000e\u0010D\u001a\u00020\u00002\u0006\u0010C\u001a\u00020\u0005J\u000e\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020\u0005J\u000e\u0010G\u001a\u00020\u00002\u0006\u0010C\u001a\u00020\u0005J\u0014\u0010H\u001a\u00020\u00002\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010J\u001a\u00020\u00002\b\u0010K\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010L\u001a\u00020\u00002\b\u0010M\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010N\u001a\u00020\u00002\u0006\u0010(\u001a\u00020'J\u0010\u0010O\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010P\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010Q\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020!J\u000e\u0010R\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0005J\u000e\u0010S\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010T\u001a\u00020\u00002\u0006\u0010+\u001a\u00020\u0005J\u000e\u0010U\u001a\u00020\u00002\u0006\u0010V\u001a\u00020-J\u000e\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\u0005J\u000e\u0010Y\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u000fJ\u000e\u0010Z\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u000fJ\u000e\u0010[\u001a\u00020\u00002\u0006\u00107\u001a\u000206J\u000e\u0010\\\u001a\u00020\u00002\u0006\u0010:\u001a\u00020\u000fJ\u000e\u0010]\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u0005J\u000e\u0010^\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u000fJ\u000e\u0010_\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u000fJ\u0013\u0010`\u001a\u00020\u00052\b\u0010a\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010b\u001a\u00020\u0018H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R&\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@BX\u0087\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0003\u001a\u0004\b\b\u0010\u0007R*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000b0\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\"\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010\"\u001a\u00020!2\u0006\u0010\u0004\u001a\u00020!@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001e\u0010%\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u001e\u0010(\u001a\u00020'2\u0006\u0010\u0004\u001a\u00020'@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0007R\u001e\u0010.\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020-@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001e\u00101\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\"\u00102\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0012R\"\u00104\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0012R\u001e\u00107\u001a\u0002062\u0006\u0010\u0004\u001a\u000206@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\"\u0010:\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0012R\u001e\u0010<\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u001e\u0010>\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0012R\u001e\u0010@\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0012¨\u0006c"}, d2 = {"Lru/mail/data/cmd/server/GolangUserShortCommand$UserData;", "", "<init>", "()V", "value", "", "isMailboxCheckDisabled", "()Z", "isMetaThreadsEnabled", "isMetaThreadsEnabled$annotations", "", "Lru/mail/logic/content/MetaThreadEnableState;", "metaThreadsStates", "getMetaThreadsStates", "()Ljava/util/List;", "", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "getAccountType", "()Ljava/lang/String;", "vkcId", "getVkcId", "theme", "getTheme", "isAdsEnabled", "", "regTimestamp", "getRegTimestamp", "()I", "isTwoFactorEnabled", "Lru/mail/data/entities/PrivacySettings;", "privacySettings", "getPrivacySettings", "()Lru/mail/data/entities/PrivacySettings;", "Lru/mail/logic/child/ParentalMode;", "parentalMode", "getParentalMode", "()Lru/mail/logic/child/ParentalMode;", "createdViaVkc", "getCreatedViaVkc", "Lru/mail/logic/SocialBind;", "socialBind", "getSocialBind", "()Lru/mail/logic/SocialBind;", "parentControlHasChildren", "getParentControlHasChildren", "Lru/mail/logic/child/DisablingParentalMode;", "mDisablingParentalMode", "getMDisablingParentalMode", "()Lru/mail/logic/child/DisablingParentalMode;", "isEnteredBetaProgram", "login", "getLogin", "domain", "getDomain", "", R7WebViewConfigInjector.BILLING_BITMASK, "getBillingBitmask", "()J", R7WebViewConfigInjector.B2B_FLAGS, "getB2bFlags", "softVkidBind", "getSoftVkidBind", "mainPhone", "getMainPhone", "firstName", "getFirstName", "withAdsEnabled", "enabled", "with2FactorEnabled", "withMailboxCheckDisabled", "disabled", "withMetaThreadsEnabled", "withMetaThreadsStates", "states", "withAccountType", "type", "withVkcId", "id", "withSocialBind", "withTheme", "withPrivacySettings", "withParentalMode", "withCreatedViaVkc", "withRegDate", "withParentControlHasChildren", "withDisableParentalMode", "disablingParentalMode", "withEnteredBetaProgram", "isEntered", "withEnteredLogin", "withEnteredDomain", "withBillingBitmask", "withB2BFlags", "withSoftVkidBind", "withMainPhone", "withFirstName", "equals", "other", "hashCode", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class UserData {
        public static final int $stable = 8;

        @Nullable
        private String accountType;

        @Nullable
        private String b2bFlags;
        private long billingBitmask;
        private boolean createdViaVkc;

        @Nullable
        private String domain;
        private boolean isAdsEnabled;
        private boolean isEnteredBetaProgram;
        private boolean isMailboxCheckDisabled;
        private boolean isMetaThreadsEnabled;
        private boolean isTwoFactorEnabled;

        @Nullable
        private String login;
        private boolean parentControlHasChildren;
        private boolean softVkidBind;

        @Nullable
        private String theme;

        @Nullable
        private String vkcId;

        @NotNull
        private List<MetaThreadEnableState> metaThreadsStates = CollectionsKt.emptyList();
        private int regTimestamp = -1;

        @NotNull
        private PrivacySettings privacySettings = new PrivacySettings(ReaderMode.OFF, false);

        @NotNull
        private ParentalMode parentalMode = ParentalMode.OFF;

        @NotNull
        private SocialBind socialBind = new SocialBind(false, false, 3, null);

        @NotNull
        private DisablingParentalMode mDisablingParentalMode = DisablingParentalMode.NULL;

        @NotNull
        private String mainPhone = "";

        @NotNull
        private String firstName = "";

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(UserData.class, other != null ? other.getClass() : null)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ru.mail.data.cmd.server.GolangUserShortCommand.UserData");
            UserData userData = (UserData) other;
            return this.isMailboxCheckDisabled == userData.isMailboxCheckDisabled && this.isMetaThreadsEnabled == userData.isMetaThreadsEnabled && Intrinsics.areEqual(this.metaThreadsStates, userData.metaThreadsStates) && Intrinsics.areEqual(this.accountType, userData.accountType) && Intrinsics.areEqual(this.theme, userData.theme) && this.isAdsEnabled == userData.isAdsEnabled && this.isTwoFactorEnabled == userData.isTwoFactorEnabled && this.createdViaVkc == userData.createdViaVkc && Intrinsics.areEqual(this.socialBind, userData.socialBind) && this.parentControlHasChildren == userData.parentControlHasChildren && this.mDisablingParentalMode == userData.mDisablingParentalMode && this.regTimestamp == userData.regTimestamp && Intrinsics.areEqual(this.privacySettings, userData.privacySettings) && this.billingBitmask == userData.billingBitmask && Intrinsics.areEqual(this.b2bFlags, userData.b2bFlags) && this.softVkidBind == userData.softVkidBind && Intrinsics.areEqual(this.mainPhone, userData.mainPhone) && Intrinsics.areEqual(this.firstName, userData.firstName);
        }

        @Nullable
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        public final String getB2bFlags() {
            return this.b2bFlags;
        }

        public final long getBillingBitmask() {
            return this.billingBitmask;
        }

        public final boolean getCreatedViaVkc() {
            return this.createdViaVkc;
        }

        @Nullable
        public final String getDomain() {
            return this.domain;
        }

        @NotNull
        public final String getFirstName() {
            return this.firstName;
        }

        @Nullable
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final DisablingParentalMode getMDisablingParentalMode() {
            return this.mDisablingParentalMode;
        }

        @NotNull
        public final String getMainPhone() {
            return this.mainPhone;
        }

        @NotNull
        public final List<MetaThreadEnableState> getMetaThreadsStates() {
            return this.metaThreadsStates;
        }

        public final boolean getParentControlHasChildren() {
            return this.parentControlHasChildren;
        }

        @NotNull
        public final ParentalMode getParentalMode() {
            return this.parentalMode;
        }

        @NotNull
        public final PrivacySettings getPrivacySettings() {
            return this.privacySettings;
        }

        public final int getRegTimestamp() {
            return this.regTimestamp;
        }

        @NotNull
        public final SocialBind getSocialBind() {
            return this.socialBind;
        }

        public final boolean getSoftVkidBind() {
            return this.softVkidBind;
        }

        @Nullable
        public final String getTheme() {
            return this.theme;
        }

        @Nullable
        public final String getVkcId() {
            return this.vkcId;
        }

        public int hashCode() {
            int iHashCode = ((((Boolean.hashCode(this.isMailboxCheckDisabled) * 31) + Boolean.hashCode(this.isMetaThreadsEnabled)) * 31) + this.metaThreadsStates.hashCode()) * 31;
            String str = this.accountType;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.theme;
            int iHashCode3 = (((((((((((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Boolean.hashCode(this.isAdsEnabled)) * 31) + Boolean.hashCode(this.isTwoFactorEnabled)) * 31) + Boolean.hashCode(this.createdViaVkc)) * 31) + this.socialBind.hashCode()) * 31) + Boolean.hashCode(this.parentControlHasChildren)) * 31) + this.mDisablingParentalMode.hashCode()) * 31) + Integer.hashCode(this.regTimestamp)) * 31) + this.privacySettings.hashCode()) * 31) + Long.hashCode(this.billingBitmask)) * 31;
            String str3 = this.b2bFlags;
            return ((((((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Boolean.hashCode(this.softVkidBind)) * 31) + this.mainPhone.hashCode()) * 31) + this.firstName.hashCode();
        }

        /* JADX INFO: renamed from: isAdsEnabled, reason: from getter */
        public final boolean getIsAdsEnabled() {
            return this.isAdsEnabled;
        }

        /* JADX INFO: renamed from: isEnteredBetaProgram, reason: from getter */
        public final boolean getIsEnteredBetaProgram() {
            return this.isEnteredBetaProgram;
        }

        /* JADX INFO: renamed from: isMailboxCheckDisabled, reason: from getter */
        public final boolean getIsMailboxCheckDisabled() {
            return this.isMailboxCheckDisabled;
        }

        /* JADX INFO: renamed from: isMetaThreadsEnabled, reason: from getter */
        public final boolean getIsMetaThreadsEnabled() {
            return this.isMetaThreadsEnabled;
        }

        /* JADX INFO: renamed from: isTwoFactorEnabled, reason: from getter */
        public final boolean getIsTwoFactorEnabled() {
            return this.isTwoFactorEnabled;
        }

        @NotNull
        public final UserData with2FactorEnabled(boolean enabled) {
            this.isTwoFactorEnabled = enabled;
            return this;
        }

        @NotNull
        public final UserData withAccountType(@Nullable String type) {
            this.accountType = type;
            return this;
        }

        @NotNull
        public final UserData withAdsEnabled(boolean enabled) {
            this.isAdsEnabled = enabled;
            return this;
        }

        @NotNull
        public final UserData withB2BFlags(@NotNull String b2bFlags) {
            Intrinsics.checkNotNullParameter(b2bFlags, "b2bFlags");
            this.b2bFlags = b2bFlags;
            return this;
        }

        @NotNull
        public final UserData withBillingBitmask(long billingBitmask) {
            this.billingBitmask = billingBitmask;
            return this;
        }

        @NotNull
        public final UserData withCreatedViaVkc(boolean createdViaVkc) {
            this.createdViaVkc = createdViaVkc;
            return this;
        }

        @NotNull
        public final UserData withDisableParentalMode(@NotNull DisablingParentalMode disablingParentalMode) {
            Intrinsics.checkNotNullParameter(disablingParentalMode, "disablingParentalMode");
            this.mDisablingParentalMode = disablingParentalMode;
            return this;
        }

        @NotNull
        public final UserData withEnteredBetaProgram(boolean isEntered) {
            this.isEnteredBetaProgram = isEntered;
            return this;
        }

        @NotNull
        public final UserData withEnteredDomain(@NotNull String domain) {
            Intrinsics.checkNotNullParameter(domain, "domain");
            this.domain = domain;
            return this;
        }

        @NotNull
        public final UserData withEnteredLogin(@NotNull String login) {
            Intrinsics.checkNotNullParameter(login, "login");
            this.login = login;
            return this;
        }

        @NotNull
        public final UserData withFirstName(@NotNull String firstName) {
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            this.firstName = firstName;
            return this;
        }

        @NotNull
        public final UserData withMailboxCheckDisabled(boolean disabled) {
            this.isMailboxCheckDisabled = disabled;
            return this;
        }

        @NotNull
        public final UserData withMainPhone(@NotNull String mainPhone) {
            Intrinsics.checkNotNullParameter(mainPhone, "mainPhone");
            this.mainPhone = mainPhone;
            return this;
        }

        @NotNull
        public final UserData withMetaThreadsEnabled(boolean enabled) {
            this.isMetaThreadsEnabled = enabled;
            return this;
        }

        @NotNull
        public final UserData withMetaThreadsStates(@NotNull List<MetaThreadEnableState> states) {
            Intrinsics.checkNotNullParameter(states, "states");
            this.metaThreadsStates = states;
            return this;
        }

        @NotNull
        public final UserData withParentControlHasChildren(boolean parentControlHasChildren) {
            this.parentControlHasChildren = parentControlHasChildren;
            return this;
        }

        @NotNull
        public final UserData withParentalMode(@NotNull ParentalMode parentalMode) {
            Intrinsics.checkNotNullParameter(parentalMode, "parentalMode");
            this.parentalMode = parentalMode;
            return this;
        }

        @NotNull
        public final UserData withPrivacySettings(@NotNull PrivacySettings privacySettings) {
            Intrinsics.checkNotNullParameter(privacySettings, "privacySettings");
            this.privacySettings = privacySettings;
            return this;
        }

        @NotNull
        public final UserData withRegDate(int regTimestamp) {
            this.regTimestamp = regTimestamp;
            return this;
        }

        @NotNull
        public final UserData withSocialBind(@NotNull SocialBind socialBind) {
            Intrinsics.checkNotNullParameter(socialBind, "socialBind");
            this.socialBind = socialBind;
            return this;
        }

        @NotNull
        public final UserData withSoftVkidBind(boolean softVkidBind) {
            this.softVkidBind = softVkidBind;
            return this;
        }

        @NotNull
        public final UserData withTheme(@Nullable String theme) {
            this.theme = theme;
            return this;
        }

        @NotNull
        public final UserData withVkcId(@Nullable String id2) {
            this.vkcId = id2;
            return this;
        }

        @Deprecated(message = "Use new metaThreadsStates variable")
        public static /* synthetic */ void isMetaThreadsEnabled$annotations() {
        }
    }
}
