package ru.mail.analytics;

import androidx.credentials.provider.CredentialEntry;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.huawei.hms.framework.common.BundleUtil;
import com.vk.lists.PaginationHelper;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.AuthSource;
import ru.mail.cloud.app.viewer.ui.ViewerActivity;
import ru.mail.credentialsexchanger.analytics.AnalyticsConstants;
import ru.mail.csat.vkws.analytics.core.MailCsatEventParam;
import ru.mail.customtags.impl.analytics.TagAnalyticsImpl;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailThread;
import ru.mail.kotlett.runtime.divkit.AnimationDirectionValues;
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.logic.content.AttachStatus;
import ru.mail.logic.content.impl.MarkSpamOperation;
import ru.mail.logic.folders.interactor.MailListDateInfo;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.news_feed.util.analytics.NewsAnalyticsHandler;
import ru.mail.sanselan.ImageInfo;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;
import ru.mail.ui.utils.InclusiveSettings;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
public class MailAnalytics {
    private volatile EventLogger mEventLogger;

    public MailAnalytics(EventLogger eventLogger) {
        this.mEventLogger = eventLogger;
    }

    @Analytics(name = "Access_Error_Profile_Null", type = Analytics.Type.ERROR)
    public void accessErrorProfileNull() {
        this.mEventLogger.logEvent("Access_Error_Profile_Null_Error", new LinkedHashMap());
    }

    @Analytics(name = "AccountManager_Dialogue", type = Analytics.Type.VIEW)
    public void accountManagerDialogueView(@Analytics.Param(name = "Domains") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Domains", String.valueOf(str));
        this.mEventLogger.logEvent("AccountManager_Dialogue_View", linkedHashMap);
    }

    @Analytics(name = "AccountManagerLimitExceeded", type = Analytics.Type.STATE)
    public void accountManagerLimitExceeded() {
        this.mEventLogger.logEvent("AccountManagerLimitExceeded_State", new LinkedHashMap());
    }

    @Analytics(name = "AccountManager_Panel_View_Click", type = Analytics.Type.ACTION)
    public void accountManagerPanelViewClickAction() {
        this.mEventLogger.logEvent("AccountManager_Panel_View_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "AccountManager_Panel", params = {@Analytics.Param(name = "Single", value = PaginationHelper.DEFAULT_NEXT_FROM)}, type = Analytics.Type.VIEW)
    public void accountManagerPanelViewMultiple(@Analytics.Param(name = "From") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Single", PaginationHelper.DEFAULT_NEXT_FROM);
        linkedHashMap.put("From", String.valueOf(str));
        this.mEventLogger.logEvent("AccountManager_Panel_View", linkedHashMap);
    }

    @Analytics(name = "AccountManager_Panel", params = {@Analytics.Param(name = "Single", value = "1")}, type = Analytics.Type.VIEW)
    public void accountManagerPanelViewSingle(@Analytics.Param(name = "Domain") String str, @Analytics.Param(name = "From") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Single", "1");
        linkedHashMap.put("Domain", String.valueOf(str));
        linkedHashMap.put("From", String.valueOf(str2));
        this.mEventLogger.logEvent("AccountManager_Panel_View", linkedHashMap);
    }

    @Analytics(name = "AccountsPopup_AccountClicked", type = Analytics.Type.ACTION)
    public void accountsPopupAccountClicked(@Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10, @Analytics.Param(name = "has_unread") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        linkedHashMap.put("has_unread", String.valueOf(z10));
        this.mEventLogger.logEvent("AccountsPopup_AccountClicked_Action", linkedHashMap);
    }

    @Analytics(name = "AccountsPopup_AccountsUpdated", type = Analytics.Type.EVENT)
    public void accountsPopupAccountsUpdated() {
        this.mEventLogger.logEvent("AccountsPopup_AccountsUpdated_Event", new LinkedHashMap());
    }

    @Analytics(name = "AccountsPopup_Cancelled", type = Analytics.Type.ACTION)
    public void accountsPopupCancelled(@Analytics.Param(name = "way") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("way", String.valueOf(str));
        this.mEventLogger.logEvent("AccountsPopup_Cancelled_Action", linkedHashMap);
    }

    @Analytics(name = "AccountsPopup", type = Analytics.Type.VIEW)
    public void accountsPopupView(@Analytics.Param(name = "accounts_count") int i10, @Analytics.Param(name = "has_unread") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("accounts_count", String.valueOf(i10));
        linkedHashMap.put("has_unread", String.valueOf(z10));
        this.mEventLogger.logEvent("AccountsPopup_View", linkedHashMap);
    }

    @Analytics(name = "AdClick", params = {@Analytics.Param(name = "location", value = "message"), @Analytics.Param(name = "adIndex", value = "message")}, type = Analytics.Type.EVENT)
    public void adClickEvent(@Analytics.Param(name = "adSource") String str, @Analytics.Param(name = "adJSONname") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("location", "message");
        linkedHashMap.put("adIndex", "message");
        linkedHashMap.put("adSource", String.valueOf(str));
        linkedHashMap.put("adJSONname", String.valueOf(str2));
        this.mEventLogger.logEvent("AdClick_Event", linkedHashMap);
    }

    @Analytics(name = "Add_Account_From_Popup", type = Analytics.Type.ACTION)
    public void addAccountFromPopup() {
        this.mEventLogger.logEvent("Add_Account_From_Popup_Action", new LinkedHashMap());
    }

    @Analytics(name = "Add_Account_From_Sign_Out_Section", type = Analytics.Type.ACTION)
    public void addAccountFromSignOutSection() {
        this.mEventLogger.logEvent("Add_Account_From_Sign_Out_Section_Action", new LinkedHashMap());
    }

    @Analytics(name = "AddCategoryAlert", type = Analytics.Type.ACTION)
    public void addCategoryAlertAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "view_type") String str2, @Analytics.Param(name = "metathreads") String str3, @Analytics.Param(name = "type") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("view_type", String.valueOf(str2));
        linkedHashMap.put("metathreads", String.valueOf(str3));
        linkedHashMap.put("type", String.valueOf(str4));
        this.mEventLogger.logEvent("AddCategoryAlert_Action", linkedHashMap);
    }

    @Analytics(name = "AddCategoryAlert", params = {@Analytics.Param(name = "action", value = ProductAction.ACTION_ADD)}, type = Analytics.Type.ACTION)
    public void addCategoryAlertActionAdd(@Analytics.Param(name = "category") String str, @Analytics.Param(name = "view_type") String str2, @Analytics.Param(name = "metathreads") String str3, @Analytics.Param(name = "type") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", ProductAction.ACTION_ADD);
        linkedHashMap.put("category", String.valueOf(str));
        linkedHashMap.put("view_type", String.valueOf(str2));
        linkedHashMap.put("metathreads", String.valueOf(str3));
        linkedHashMap.put("type", String.valueOf(str4));
        this.mEventLogger.logEvent("AddCategoryAlert_Action", linkedHashMap);
    }

    @Analytics(name = "AddCategoryAlert", type = Analytics.Type.ACTION)
    public void addCategoryAlertActionClick(@Analytics.Param(name = "view_type") String str, @Analytics.Param(name = "metathreads") String str2, @Analytics.Param(name = "type") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("view_type", String.valueOf(str));
        linkedHashMap.put("metathreads", String.valueOf(str2));
        linkedHashMap.put("type", String.valueOf(str3));
        this.mEventLogger.logEvent("AddCategoryAlert_Action", linkedHashMap);
    }

    @Analytics(name = "AddCategoryAlert_AddFilterAlert", type = Analytics.Type.VIEW)
    public void addCategoryAlertAddFilterAlert(@Analytics.Param(name = "view_type") String str, @Analytics.Param(name = "type") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("view_type", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        this.mEventLogger.logEvent("AddCategoryAlert_AddFilterAlert_View", linkedHashMap);
    }

    @Analytics(name = "AddCategoryAlert_AddFilterAlert", type = Analytics.Type.ACTION)
    public void addCategoryAlertAddFilterAlertAction(@Analytics.Param(name = "view_type") String str, @Analytics.Param(name = "action") String str2, @Analytics.Param(name = "type") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("view_type", String.valueOf(str));
        linkedHashMap.put("action", String.valueOf(str2));
        linkedHashMap.put("type", String.valueOf(str3));
        this.mEventLogger.logEvent("AddCategoryAlert_AddFilterAlert_Action", linkedHashMap);
    }

    @Analytics(name = "AddCategoryAlert", type = Analytics.Type.VIEW)
    public void addCategoryAlertView(@Analytics.Param(name = "metathreads") String str, @Analytics.Param(name = "type") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("metathreads", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        this.mEventLogger.logEvent("AddCategoryAlert_View", linkedHashMap);
    }

    @Analytics(name = "AddContact_Dialogue", type = Analytics.Type.ACTION)
    public void addContactDialogueAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("AddContact_Dialogue_Action", linkedHashMap);
    }

    @Analytics(name = "AMP_Loading", type = Analytics.Type.ERROR)
    public void ampLoadingError() {
        this.mEventLogger.logEvent("AMP_Loading_Error", new LinkedHashMap());
    }

    @Analytics(name = "AppInstall", type = Analytics.Type.STATE)
    public void appInstall(@Analytics.Param(name = "Version") String str, @Analytics.Param(name = "Platform") String str2, @Analytics.Param(name = "OS_Version") String str3, @Analytics.Param(name = "Language") String str4, @Analytics.Param(name = "Device_Model") String str5, @Analytics.Param(name = "getRegion") String str6, @Analytics.Param(name = "install_source") String str7, @Analytics.Param InclusiveSettings inclusiveSettings, @Analytics.Param(name = "vpnAnalyticsInfo") VpnInfoProvider vpnInfoProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Version", String.valueOf(str));
        linkedHashMap.put("Platform", String.valueOf(str2));
        linkedHashMap.put("OS_Version", String.valueOf(str3));
        linkedHashMap.put("Language", String.valueOf(str4));
        linkedHashMap.put("Device_Model", String.valueOf(str5));
        linkedHashMap.put("getRegion", String.valueOf(str6));
        linkedHashMap.put("textScale", String.valueOf(inclusiveSettings.getTextScale()));
        linkedHashMap.put("textWeight", String.valueOf(inclusiveSettings.getTextWeight()));
        linkedHashMap.put("colorCorrection", String.valueOf(inclusiveSettings.isColorCorrection()));
        linkedHashMap.put("contrast", String.valueOf(inclusiveSettings.isContrast()));
        linkedHashMap.put("textContrast", String.valueOf(inclusiveSettings.isTextContrast()));
        linkedHashMap.put("screenReader", String.valueOf(inclusiveSettings.isScreenReaderOn()));
        linkedHashMap.put("install_source", String.valueOf(str7));
        linkedHashMap.put("vpn_probability", vpnInfoProvider.getVpnProbability());
        linkedHashMap.put("vpn_state", vpnInfoProvider.getVpnState());
        linkedHashMap.put("vpn_throttle_delay_ms", vpnInfoProvider.getVpnThrottleDelay());
        linkedHashMap.put("is_vpn_enabled", vpnInfoProvider.isVpnEnabled());
        linkedHashMap.put("is_vpn_check_enabled", vpnInfoProvider.isVpnCheckEnabled());
        linkedHashMap.put("is_vpn_geoip", vpnInfoProvider.isVpnGeoip());
        linkedHashMap.put("is_wifi", vpnInfoProvider.isWifi());
        linkedHashMap.put("is_sdk", vpnInfoProvider.isSdk());
        this.mEventLogger.logEvent("AppInstall_State", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = "Archive")}, type = Analytics.Type.ACTION)
    public void archiveSelectedMailsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Archive");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = "Archive")}, type = Analytics.Type.ACTION)
    public void archiveSelectedMailsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Archive");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "Auth_Failed", type = Analytics.Type.ACTION)
    public void authFailedAction(@Analytics.Param(name = "Retry") boolean z10, @Analytics.Param(name = "domain") String str, @Analytics.Param(name = "AuthType") String str2, @Analytics.Param(name = "FailType") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Retry", String.valueOf(z10));
        linkedHashMap.put("domain", String.valueOf(str));
        linkedHashMap.put("AuthType", String.valueOf(str2));
        linkedHashMap.put("FailType", String.valueOf(str3));
        this.mEventLogger.logEvent("Auth_Failed_Action", linkedHashMap);
    }

    @Analytics(name = "Auth_PasswordsForApps", type = Analytics.Type.ERROR)
    public void authFailedPasswordsForApps() {
        this.mEventLogger.logEvent("Auth_PasswordsForApps_Error", new LinkedHashMap());
    }

    @Analytics(name = "AuthNavigationBack", type = Analytics.Type.ACTION)
    public void authNavigationBackAction(@Analytics.Param(name = "step") String str, @Analytics.Param(name = "type") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        this.mEventLogger.logEvent("AuthNavigationBack_Action", linkedHashMap);
    }

    @Analytics(name = "AuthTransition_Failed", params = {@Analytics.Param(name = "transition", value = "cookie_to_tokens")}, type = Analytics.Type.EVENT)
    public void authTransitionFailedEventTransitionCookieToTokens(@Analytics.Param(name = "Try") int i10, @Analytics.Param(name = "will_retry") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("transition", "cookie_to_tokens");
        linkedHashMap.put("Try", String.valueOf(i10));
        linkedHashMap.put("will_retry", String.valueOf(z10));
        this.mEventLogger.logEvent("AuthTransition_Failed_Event", linkedHashMap);
    }

    @Analytics(name = "AuthTransition_Failed", params = {@Analytics.Param(name = "transition", value = "cookie_to_tokens"), @Analytics.Param(name = "will_retry", value = CredentialEntry.FALSE_STRING), @Analytics.Param(name = "2fa", value = "true")}, type = Analytics.Type.EVENT)
    public void authTransitionFailedEventTransitionCookieToTokens2Fa() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("transition", "cookie_to_tokens");
        linkedHashMap.put("will_retry", CredentialEntry.FALSE_STRING);
        linkedHashMap.put("2fa", "true");
        this.mEventLogger.logEvent("AuthTransition_Failed_Event", linkedHashMap);
    }

    @Analytics(name = "AuthTransition_Failed", params = {@Analytics.Param(name = "transition", value = "tokens_to_cookie"), @Analytics.Param(name = "will_retry", value = CredentialEntry.FALSE_STRING)}, type = Analytics.Type.EVENT)
    public void authTransitionFailedEventTransitionTokensToCookie() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("transition", "tokens_to_cookie");
        linkedHashMap.put("will_retry", CredentialEntry.FALSE_STRING);
        this.mEventLogger.logEvent("AuthTransition_Failed_Event", linkedHashMap);
    }

    @Analytics(name = "AuthTransition_Success", type = Analytics.Type.EVENT)
    public void authTransitionSuccessEvent(@Analytics.Param(name = "transition") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("transition", String.valueOf(str));
        this.mEventLogger.logEvent("AuthTransition_Success_Event", linkedHashMap);
    }

    @Analytics(name = "AuthorizedCommandComplete", type = Analytics.Type.ERROR)
    public void authorizedCommandCompleteError(@Analytics.Param(name = "cmd") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("cmd", String.valueOf(str));
        this.mEventLogger.logEvent("AuthorizedCommandComplete_Error", linkedHashMap);
    }

    @Analytics(name = "BottomAppBar_LongClick_Account", type = Analytics.Type.ACTION)
    public void bottomAppBarLongClickAccount() {
        this.mEventLogger.logEvent("BottomAppBar_LongClick_Account_Action", new LinkedHashMap());
    }

    @Analytics(name = "BottomAppBar_Account_Invoke", type = Analytics.Type.ACTION)
    public void bottomAppBarSendAccountAnalyticInvoke(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("BottomAppBar_Account_Invoke_Action", linkedHashMap);
    }

    @Analytics(name = "BottomAppBar_Fab_Clicked", type = Analytics.Type.ACTION)
    public void bottomAppBarSendFabClickedAnalytics() {
        this.mEventLogger.logEvent("BottomAppBar_Fab_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "BottomAppBar_Folders_Invoke", type = Analytics.Type.ACTION)
    public void bottomAppBarSendFoldersAnalyticInvoke(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("BottomAppBar_Folders_Invoke_Action", linkedHashMap);
    }

    @Analytics(name = "BottomAppBar_Swipe_Account", type = Analytics.Type.ACTION)
    public void bottomAppBarSendSwipeAccountAnalytic(@Analytics.Param(name = "side") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("side", String.valueOf(str));
        this.mEventLogger.logEvent("BottomAppBar_Swipe_Account_Action", linkedHashMap);
    }

    @Analytics(name = "BrowserScreen", params = {@Analytics.Param(name = "Action", value = "CopyLink")}, type = Analytics.Type.ACTION)
    public void browserScreenActionCopyLink() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CopyLink");
        this.mEventLogger.logEvent("BrowserScreen_Action", linkedHashMap);
    }

    @Analytics(name = "BrowserScreen", params = {@Analytics.Param(name = "Action", value = "OpenIn")}, type = Analytics.Type.ACTION)
    public void browserScreenActionOpenIn() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "OpenIn");
        this.mEventLogger.logEvent("BrowserScreen_Action", linkedHashMap);
    }

    @Analytics(name = "BrowserScreen", params = {@Analytics.Param(name = "Action", value = "Share")}, type = Analytics.Type.ACTION)
    public void browserScreenActionShare() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Share");
        this.mEventLogger.logEvent("BrowserScreen_Action", linkedHashMap);
    }

    @Analytics(name = "CallerId_CallAnswered", type = Analytics.Type.ACTION)
    public void callerIdOnCallAnswered() {
        this.mEventLogger.logEvent("CallerId_CallAnswered_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_SystRequestCallLogPerm", type = Analytics.Type.VIEW)
    public void callerIdOnCallLogPermissionSystemDialogShown() {
        this.mEventLogger.logEvent("CallerId_SystRequestCallLogPerm_View", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_CallNotAnswered", type = Analytics.Type.ACTION)
    public void callerIdOnCallNotAnswered() {
        this.mEventLogger.logEvent("CallerId_CallNotAnswered_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_DbUpdated", type = Analytics.Type.EVENT)
    public void callerIdOnDbUpdated() {
        this.mEventLogger.logEvent("CallerId_DbUpdated_Event", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_DeniedCallLogPerm", type = Analytics.Type.ACTION)
    public void callerIdOnDeniedPermissionCallLog() {
        this.mEventLogger.logEvent("CallerId_DeniedCallLogPerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_DeniedPhoneStatePerm", type = Analytics.Type.ACTION)
    public void callerIdOnDeniedPermissionPhoneState() {
        this.mEventLogger.logEvent("CallerId_DeniedPhoneStatePerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_Disabled", type = Analytics.Type.ACTION)
    public void callerIdOnDisabled() {
        this.mEventLogger.logEvent("CallerId_Disabled_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_Enabled", type = Analytics.Type.ACTION)
    public void callerIdOnEnabled() {
        this.mEventLogger.logEvent("CallerId_Enabled_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_GrantedCallLogPerm", type = Analytics.Type.ACTION)
    public void callerIdOnGrantedPermissionCallLog() {
        this.mEventLogger.logEvent("CallerId_GrantedCallLogPerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_GrantedPhoneStatePerm", type = Analytics.Type.ACTION)
    public void callerIdOnGrantedPermissionPhoneState() {
        this.mEventLogger.logEvent("CallerId_GrantedPhoneStatePerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_IdentificationCanceled", type = Analytics.Type.ACTION)
    public void callerIdOnIdentificationCanceled() {
        this.mEventLogger.logEvent("CallerId_IdentificationCanceled_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_IdentificationError", type = Analytics.Type.EVENT)
    public void callerIdOnIdentificationError() {
        this.mEventLogger.logEvent("CallerId_IdentificationError_Event", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_Identified", type = Analytics.Type.EVENT)
    public void callerIdOnIdentified() {
        this.mEventLogger.logEvent("CallerId_Identified_Event", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_IncomingCall", type = Analytics.Type.ACTION)
    public void callerIdOnIncomingCall() {
        this.mEventLogger.logEvent("CallerId_IncomingCall_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_NeverAskCallLogPerm", type = Analytics.Type.ACTION)
    public void callerIdOnNeverAskAgainPermissionCallLog() {
        this.mEventLogger.logEvent("CallerId_NeverAskCallLogPerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_NeverAskPhoneStatePerm", type = Analytics.Type.ACTION)
    public void callerIdOnNeverAskAgainPermissionPhoneState() {
        this.mEventLogger.logEvent("CallerId_NeverAskPhoneStatePerm_Action", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_NotIdentified", type = Analytics.Type.EVENT)
    public void callerIdOnNotIdentified() {
        this.mEventLogger.logEvent("CallerId_NotIdentified_Event", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_NotificationShown", type = Analytics.Type.EVENT)
    public void callerIdOnNotificationShown() {
        this.mEventLogger.logEvent("CallerId_NotificationShown_Event", new LinkedHashMap());
    }

    @Analytics(name = "CallerId_SystRequestPhoneStatePerm", type = Analytics.Type.VIEW)
    public void callerIdOnPhoneStatePermissionSystemDialogShown() {
        this.mEventLogger.logEvent("CallerId_SystRequestPhoneStatePerm_View", new LinkedHashMap());
    }

    @Analytics(name = "CgiBinAuth", type = Analytics.Type.ERROR)
    public void cgiBinAuthError(@Analytics.Param(name = "swaStatus") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("swaStatus", String.valueOf(str));
        this.mEventLogger.logEvent("CgiBinAuth_Error", linkedHashMap);
    }

    @Analytics(name = "CheckAccountManager", type = Analytics.Type.RESULT)
    public void checkAccountManagerResult(@Analytics.Param(name = "accounts") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("accounts", String.valueOf(str));
        this.mEventLogger.logEvent("CheckAccountManager_Result", linkedHashMap);
    }

    @Analytics(name = "Choose_Image_Compression", type = Analytics.Type.ACTION)
    public void chooseImageCompressionAction(@Analytics.Param(name = "Quality") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Quality", String.valueOf(str));
        this.mEventLogger.logEvent("Choose_Image_Compression_Action", linkedHashMap);
    }

    @Analytics(name = "ChoseSenderEmail", params = {@Analytics.Param(name = "action", value = "chose")}, type = Analytics.Type.ACTION)
    public void choseSenderEmailActionChose(@Analytics.Param(name = "email") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "chose");
        linkedHashMap.put("email", String.valueOf(str));
        this.mEventLogger.logEvent("ChoseSenderEmail_Action", linkedHashMap);
    }

    @Analytics(name = "ChoseSenderEmail", params = {@Analytics.Param(name = "action", value = "list")}, type = Analytics.Type.ACTION)
    public void choseSenderEmailActionList() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "list");
        this.mEventLogger.logEvent("ChoseSenderEmail_Action", linkedHashMap);
    }

    @Analytics(name = "ClickSettingsItem", type = Analytics.Type.ACTION)
    public void clickSettingsItemAction(@Analytics.Param(name = "highlighted") boolean z10, @Analytics.Param(name = DivActionSpec.Scroll.PARAM_ITEM_INDEX) String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("highlighted", String.valueOf(z10));
        linkedHashMap.put(DivActionSpec.Scroll.PARAM_ITEM_INDEX, String.valueOf(str));
        this.mEventLogger.logEvent("ClickSettingsItem_Action", linkedHashMap);
    }

    @Analytics(name = "CloseAccountManager", type = Analytics.Type.ACTION)
    public void closeAccountManagerAction() {
        this.mEventLogger.logEvent("CloseAccountManager_Action", new LinkedHashMap());
    }

    @Analytics(name = "CloseSmartLock", type = Analytics.Type.ACTION)
    public void closeSmartLockAction() {
        this.mEventLogger.logEvent("CloseSmartLock_Action", new LinkedHashMap());
    }

    @Analytics(name = "CloudFixButton", type = Analytics.Type.VIEW)
    public void cloudFixButton(@Analytics.Param(name = "viewCase") String str, @Analytics.Param(name = "installed") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("viewCase", String.valueOf(str));
        linkedHashMap.put("installed", String.valueOf(z10));
        this.mEventLogger.logEvent("CloudFixButton_View", linkedHashMap);
    }

    @Analytics(name = "Compress_Image", type = Analytics.Type.STATE)
    public void compressImageState(@Analytics.Param(name = "Quality") String str, @Analytics.Param(name = "Attach_name") String str2, @Analytics.Param(name = "Processing_Time") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Quality", String.valueOf(str));
        linkedHashMap.put("Attach_name", String.valueOf(str2));
        linkedHashMap.put("Processing_Time", String.valueOf(j10));
        this.mEventLogger.logEvent("Compress_Image_State", linkedHashMap);
    }

    @Analytics(name = "DeleteAccount", params = {@Analytics.Param(name = "Action", value = "ConfirmSuccess")}, type = Analytics.Type.ACTION)
    public void confirmDeleteRequest() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ConfirmSuccess");
        this.mEventLogger.logEvent("DeleteAccount_Action", linkedHashMap);
    }

    @Analytics(name = "Contacts_Export_Result", type = Analytics.Type.EVENT)
    public void contactExportResult(@Analytics.Param(name = "result") String str, @Analytics.Param(name = "count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", String.valueOf(str));
        linkedHashMap.put("count", String.valueOf(i10));
        this.mEventLogger.logEvent("Contacts_Export_Result_Event", linkedHashMap);
    }

    @Analytics(name = "ContactInfoActionClicked", type = Analytics.Type.ACTION)
    public void contactInfoActionClicked(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "from") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("from", String.valueOf(str2));
        this.mEventLogger.logEvent("ContactInfoActionClicked_Action", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", params = {@Analytics.Param(name = "Action", value = "Save")}, type = Analytics.Type.ACTION)
    public void contactInfoSaveContact(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Save");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_Action", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", params = {@Analytics.Param(name = "Action", value = "Filter")}, type = Analytics.Type.ACTION)
    public void contactInfoStartFilter(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Filter");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_Action", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", type = Analytics.Type.VIEW)
    public void contactInfoView(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_View", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", params = {@Analytics.Param(name = "Action", value = "Compose")}, type = Analytics.Type.ACTION)
    public void contactInfoWriteLetter(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Compose");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_Action", linkedHashMap);
    }

    @Analytics(name = "ContactNotification", type = Analytics.Type.ACTION)
    public void contactNotificationAction(@Analytics.Param(name = "place") String str, @Analytics.Param(name = "mute") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("place", String.valueOf(str));
        linkedHashMap.put("mute", String.valueOf(z10));
        this.mEventLogger.logEvent("ContactNotification_Action", linkedHashMap);
    }

    @Analytics(name = "ContactNotification", params = {@Analytics.Param(name = "place", value = "mail")}, type = Analytics.Type.VIEW)
    public void contactNotificationView() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("place", "mail");
        this.mEventLogger.logEvent("ContactNotification_View", linkedHashMap);
    }

    @Analytics(name = "Contacts_Permission_To_Export_Request", type = Analytics.Type.ACTION)
    public void contactPermissionToExportRequest(@Analytics.Param(name = "result") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", String.valueOf(str));
        this.mEventLogger.logEvent("Contacts_Permission_To_Export_Request_Action", linkedHashMap);
    }

    @Analytics(name = "Contacts", type = Analytics.Type.VIEW)
    public void contactsView() {
        this.mEventLogger.logEvent("Contacts_View", new LinkedHashMap());
    }

    @Analytics(name = "CreateMailAcc", params = {@Analytics.Param(name = "Action", value = "click"), @Analytics.Param(name = "step", value = "serviceScreen")}, type = Analytics.Type.ACTION)
    public void createMailAccAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "click");
        linkedHashMap.put("step", "serviceScreen");
        this.mEventLogger.logEvent("CreateMailAcc_Action", linkedHashMap);
    }

    @Analytics(name = "Default_AuthType_Settings", type = Analytics.Type.ACTION)
    public void defaultAuthTypeSettingsAction(@Analytics.Param(name = "code_auth_enabled") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("code_auth_enabled", String.valueOf(z10));
        this.mEventLogger.logEvent("Default_AuthType_Settings_Action", linkedHashMap);
    }

    @Analytics(name = "Default_AuthType_Settings", type = Analytics.Type.ERROR)
    public void defaultAuthTypeSettingsError(@Analytics.Param(name = "code_auth_enabled") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("code_auth_enabled", String.valueOf(z10));
        this.mEventLogger.logEvent("Default_AuthType_Settings_Error", linkedHashMap);
    }

    @Analytics(name = "Default_AuthType_Settings", type = Analytics.Type.VIEW)
    public void defaultAuthTypeSettingsView() {
        this.mEventLogger.logEvent("Default_AuthType_Settings_View", new LinkedHashMap());
    }

    @Analytics(name = "Default_Or_Force_VKID_Auth_Success", type = Analytics.Type.EVENT)
    public void defaultOrForceVKIDAuthSuccess() {
        this.mEventLogger.logEvent("Default_Or_Force_VKID_Auth_Success_Event", new LinkedHashMap());
    }

    @Analytics(name = "Default_VKID_Auth_Clicked", type = Analytics.Type.ACTION)
    public void defaultVKIDAuthClicked() {
        this.mEventLogger.logEvent("Default_VKID_Auth_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "DeleteAccount", type = Analytics.Type.ERROR)
    public void deleteAccountError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("DeleteAccount_Error", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void deleteSelectedItemsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Delete");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void deleteSelectedItemsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Delete");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage", type = Analytics.Type.ACTION)
    public void editMessageAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("EditMessage_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage", params = {@Analytics.Param(name = "Action", value = "AddAttach")}, type = Analytics.Type.ACTION)
    public void editMessageActionAddAttach(@Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "AddAttach");
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("EditMessage_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage", params = {@Analytics.Param(name = "Action", value = "Send")}, type = Analytics.Type.ACTION)
    public void editMessageActionSend(@Analytics.Param(name = "AttachCount") int i10, @Analytics.Param(name = "StickersCount") int i11, @Analytics.Param(name = "Mail") String str, @Analytics.Param(name = "BodyLength") String str2, @Analytics.Param(name = "SignatureLength") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Send");
        linkedHashMap.put("AttachCount", String.valueOf(i10));
        linkedHashMap.put("StickersCount", String.valueOf(i11));
        linkedHashMap.put("Mail", String.valueOf(str));
        linkedHashMap.put("BodyLength", String.valueOf(str2));
        linkedHashMap.put("SignatureLength", String.valueOf(str3));
        this.mEventLogger.logEvent("EditMessage_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage_Attach", type = Analytics.Type.ACTION)
    public void editMessageAttachAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("EditMessage_Attach_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage_Attach", params = {@Analytics.Param(name = "Action", value = "Camera")}, type = Analytics.Type.ACTION)
    public void editMessageAttachActionCamera() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Camera");
        this.mEventLogger.logEvent("EditMessage_Attach_Action", linkedHashMap);
    }

    @Analytics(name = "EditMessage_Attach", type = Analytics.Type.VIEW)
    public void editMessageAttachView() {
        this.mEventLogger.logEvent("EditMessage_Attach_View", new LinkedHashMap());
    }

    @Analytics(name = "EditMessage", type = Analytics.Type.ERROR)
    public void editMessageError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("EditMessage_Error", linkedHashMap);
    }

    @Analytics(name = "EditMessage_ShareExtension", type = Analytics.Type.VIEW)
    public void editMessageShareExtension() {
        this.mEventLogger.logEvent("EditMessage_ShareExtension_View", new LinkedHashMap());
    }

    @Analytics(name = "EditMessage", type = Analytics.Type.VIEW)
    public void editMessageView(@Analytics.Param(name = "vpnAnalyticsInfo") VpnInfoProvider vpnInfoProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("vpn_probability", vpnInfoProvider.getVpnProbability());
        linkedHashMap.put("vpn_state", vpnInfoProvider.getVpnState());
        linkedHashMap.put("vpn_throttle_delay_ms", vpnInfoProvider.getVpnThrottleDelay());
        linkedHashMap.put("is_vpn_enabled", vpnInfoProvider.isVpnEnabled());
        linkedHashMap.put("is_vpn_check_enabled", vpnInfoProvider.isVpnCheckEnabled());
        linkedHashMap.put("is_vpn_geoip", vpnInfoProvider.isVpnGeoip());
        linkedHashMap.put("is_wifi", vpnInfoProvider.isWifi());
        linkedHashMap.put("is_sdk", vpnInfoProvider.isSdk());
        this.mEventLogger.logEvent("EditMessage_View", linkedHashMap);
    }

    @Analytics(name = "EditMessage", type = Analytics.Type.VIEW)
    public void editMessageViewInfo(@Analytics.Param(name = "HasReplies") boolean z10, @Analytics.Param(name = "PushReply") boolean z11, @Analytics.Param(name = "SmartPushReply") boolean z12, @Analytics.Param(name = "Stage") boolean z13, @Analytics.Param(name = "IsDefault") boolean z14, @Analytics.Param(name = "type") String str, @Analytics.Param(name = "vpnAnalyticsInfo") VpnInfoProvider vpnInfoProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("HasReplies", String.valueOf(z10));
        linkedHashMap.put("PushReply", String.valueOf(z11));
        linkedHashMap.put("SmartPushReply", String.valueOf(z12));
        linkedHashMap.put("Stage", String.valueOf(z13));
        linkedHashMap.put("IsDefault", String.valueOf(z14));
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put("vpn_probability", vpnInfoProvider.getVpnProbability());
        linkedHashMap.put("vpn_state", vpnInfoProvider.getVpnState());
        linkedHashMap.put("vpn_throttle_delay_ms", vpnInfoProvider.getVpnThrottleDelay());
        linkedHashMap.put("is_vpn_enabled", vpnInfoProvider.isVpnEnabled());
        linkedHashMap.put("is_vpn_check_enabled", vpnInfoProvider.isVpnCheckEnabled());
        linkedHashMap.put("is_vpn_geoip", vpnInfoProvider.isVpnGeoip());
        linkedHashMap.put("is_wifi", vpnInfoProvider.isWifi());
        linkedHashMap.put("is_sdk", vpnInfoProvider.isSdk());
        this.mEventLogger.logEvent("EditMessage_View", linkedHashMap);
    }

    @Analytics(name = "EditMessageStage", type = Analytics.Type.VIEW)
    public void editMessageViewInfoStage(@Analytics.Param(name = "HasReplies") boolean z10, @Analytics.Param(name = "PushReply") boolean z11, @Analytics.Param(name = "SmartPushReply") boolean z12, @Analytics.Param(name = "IsDefault") boolean z13, @Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("HasReplies", String.valueOf(z10));
        linkedHashMap.put("PushReply", String.valueOf(z11));
        linkedHashMap.put("SmartPushReply", String.valueOf(z12));
        linkedHashMap.put("IsDefault", String.valueOf(z13));
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("EditMessageStage_View", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Action", value = "Select_All")}, type = Analytics.Type.ACTION)
    public void editModeToggleSelection(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Select_All");
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Filters", params = {@Analytics.Param(name = "Action", value = "Edit")}, type = Analytics.Type.ACTION)
    public void editUpdateFilter(@Analytics.Param(name = "MarkAsRead") boolean z10, @Analytics.Param(name = "ApplyToAll") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Edit");
        linkedHashMap.put("MarkAsRead", String.valueOf(z10));
        linkedHashMap.put("ApplyToAll", String.valueOf(z11));
        this.mEventLogger.logEvent("Settings_Filters_Action", linkedHashMap);
    }

    @Analytics(name = "EmailToMyselfAddressBookClicked", type = Analytics.Type.ACTION)
    public void emailToMyselfAddressBookClicked(@Analytics.Param(name = "with_email") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("with_email", String.valueOf(z10));
        this.mEventLogger.logEvent("EmailToMyselfAddressBookClicked_Action", linkedHashMap);
    }

    @Analytics(name = "EmojiTabSelected", type = Analytics.Type.EVENT)
    public void emojiTabSelected(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("EmojiTabSelected_Event", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Action", value = "Edit")}, type = Analytics.Type.ACTION)
    public void enableEditModeWithMessageListAnalytic(@Analytics.Param(name = MailBoxFolder.COL_NAME_META_THREAD) String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Edit");
        linkedHashMap.put(MailBoxFolder.COL_NAME_META_THREAD, String.valueOf(str));
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "Exit_Or_Add_Account_Dialog_Shown", type = Analytics.Type.EVENT)
    public void exitOrAddAccountDialogShown() {
        this.mEventLogger.logEvent("Exit_Or_Add_Account_Dialog_Shown_Event", new LinkedHashMap());
    }

    @Analytics(name = "Experiment_simple_signin", type = Analytics.Type.EVENT)
    public void experimentSimpleSignin(@Analytics.Param(name = "isExperiment") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isExperiment", String.valueOf(z10));
        this.mEventLogger.logEvent("Experiment_simple_signin_Event", linkedHashMap);
    }

    @Analytics(name = "Feedback", params = {@Analytics.Param(name = "Action", value = "Send")}, type = Analytics.Type.ACTION)
    public void feedbackActionSend() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Send");
        this.mEventLogger.logEvent("Feedback_Action", linkedHashMap);
    }

    @Analytics(name = "FileBrowser_Path_Parsing", type = Analytics.Type.ERROR)
    public void fileBrowserPathParsingError(@Analytics.Param(name = "root_directory") String str, @Analytics.Param(name = "full_path") String str2, @Analytics.Param(name = "problem_path") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("root_directory", String.valueOf(str));
        linkedHashMap.put("full_path", String.valueOf(str2));
        linkedHashMap.put("problem_path", String.valueOf(str3));
        this.mEventLogger.logEvent("FileBrowser_Path_Parsing_Error", linkedHashMap);
    }

    @Analytics(name = "FolderList", params = {@Analytics.Param(name = "Action", value = "App_Click")}, type = Analytics.Type.ACTION)
    public void folderListActionAppClick(@Analytics.Param(name = "App_name") String str, @Analytics.Param(name = "adPosition") int i10, @Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_SLOT) int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "App_Click");
        linkedHashMap.put("App_name", String.valueOf(str));
        linkedHashMap.put("adPosition", String.valueOf(i10));
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_SLOT, String.valueOf(i11));
        this.mEventLogger.logEvent("FolderList_Action", linkedHashMap);
    }

    @Analytics(name = "FolderList", params = {@Analytics.Param(name = "Action", value = "App_View")}, type = Analytics.Type.ACTION)
    public void folderListActionAppView(@Analytics.Param(name = "adPosition") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "App_View");
        linkedHashMap.put("adPosition", String.valueOf(i10));
        this.mEventLogger.logEvent("FolderList_Action", linkedHashMap);
    }

    @Analytics(name = "FolderList_New_Account_Click", type = Analytics.Type.ACTION)
    public void folderListNewAccountClick() {
        this.mEventLogger.logEvent("FolderList_New_Account_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "FolderPassRequired", type = Analytics.Type.ERROR)
    public void folderPassRequiredError() {
        this.mEventLogger.logEvent("FolderPassRequired_Error", new LinkedHashMap());
    }

    @Analytics(name = "FolderSelectDialog", type = Analytics.Type.ACTION)
    public void folderSelectDialogAction(@Analytics.Param(name = "SelectedFolder") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("SelectedFolder", String.valueOf(str));
        this.mEventLogger.logEvent("FolderSelectDialog_Action", linkedHashMap);
    }

    @Analytics(name = "Force_VKID_Auth_Clicked", type = Analytics.Type.ACTION)
    public void forceVKIDAuthClicked() {
        this.mEventLogger.logEvent("Force_VKID_Auth_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "Force_VKID_Auth_Success", type = Analytics.Type.EVENT)
    public void forceVKIDAuthSuccess() {
        this.mEventLogger.logEvent("Force_VKID_Auth_Success_Event", new LinkedHashMap());
    }

    @Analytics(name = "Login_Image_Clicked", type = Analytics.Type.EVENT)
    public void imageClickedAnalytic() {
        this.mEventLogger.logEvent("Login_Image_Clicked_Event", new LinkedHashMap());
    }

    @Analytics(name = "InMailPromo", type = Analytics.Type.ACTION)
    public void inMailPromoAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("InMailPromo_Action", linkedHashMap);
    }

    @Analytics(name = "InMailPromoMenuOpen", type = Analytics.Type.ACTION)
    public void inMailPromoMenuOpenAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("InMailPromoMenuOpen_Action", linkedHashMap);
    }

    @Analytics(name = "InMailPromo", type = Analytics.Type.VIEW)
    public void inMailPromoView(@Analytics.Param(name = "resource") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("resource", String.valueOf(str));
        this.mEventLogger.logEvent("InMailPromo_View", linkedHashMap);
    }

    public void initEventLogger(EventLogger eventLogger) {
        this.mEventLogger = eventLogger;
    }

    @Analytics(name = "LeelooDesignPromoView", type = Analytics.Type.EVENT)
    public void leelooDesignPromoView() {
        this.mEventLogger.logEvent("LeelooDesignPromoView_Event", new LinkedHashMap());
    }

    @Analytics(name = "LetterReminder", type = Analytics.Type.ACTION)
    public void letterReminderAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "state") String str2, @Analytics.Param(name = "place") String str3, @Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_CURRENT_TIME) String str4, @Analytics.Param(name = "currentDayOfWeek") String str5, @Analytics.Param(name = "reminderTime") String str6, @Analytics.Param(name = "reminderDate") String str7) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("state", String.valueOf(str2));
        linkedHashMap.put("place", String.valueOf(str3));
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, String.valueOf(str4));
        linkedHashMap.put("currentDayOfWeek", String.valueOf(str5));
        linkedHashMap.put("reminderTime", String.valueOf(str6));
        linkedHashMap.put("reminderDate", String.valueOf(str7));
        this.mEventLogger.logEvent("LetterReminder_Action", linkedHashMap);
    }

    @Analytics(name = "LetterReminderDialog", type = Analytics.Type.ACTION)
    public void letterReminderDialogAction(@Analytics.Param(name = DivActionSpec.Scroll.PARAM_ITEM_INDEX) String str, @Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_CURRENT_TIME) String str2, @Analytics.Param(name = "currentDayOfWeek") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(DivActionSpec.Scroll.PARAM_ITEM_INDEX, String.valueOf(str));
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, String.valueOf(str2));
        linkedHashMap.put("currentDayOfWeek", String.valueOf(str3));
        this.mEventLogger.logEvent("LetterReminderDialog_Action", linkedHashMap);
    }

    @Analytics(name = "LetterReminder", type = Analytics.Type.VIEW)
    public void letterReminderView(@Analytics.Param(name = "state") String str, @Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_CURRENT_TIME) String str2, @Analytics.Param(name = "currentDayOfWeek") String str3, @Analytics.Param(name = "reminderTime") String str4, @Analytics.Param(name = "reminderDate") String str5) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("state", String.valueOf(str));
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, String.valueOf(str2));
        linkedHashMap.put("currentDayOfWeek", String.valueOf(str3));
        linkedHashMap.put("reminderTime", String.valueOf(str4));
        linkedHashMap.put("reminderDate", String.valueOf(str5));
        this.mEventLogger.logEvent("LetterReminder_View", linkedHashMap);
    }

    @Analytics(name = "LicenseAgreement", type = Analytics.Type.ACTION)
    public void licenseAgreement(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("LicenseAgreement_Action", linkedHashMap);
    }

    @Analytics(name = "LicenseAgreement", params = {@Analytics.Param(name = "Action", value = "Accept")}, type = Analytics.Type.ACTION)
    public void licenseAgreementAccept(@Analytics.Param(name = "contact") String str, @Analytics.Param(name = "rel_id") String str2, @Analytics.Param(name = "name") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Accept");
        linkedHashMap.put("contact", String.valueOf(str));
        linkedHashMap.put("rel_id", String.valueOf(str2));
        linkedHashMap.put("name", String.valueOf(str3));
        this.mEventLogger.logEvent("LicenseAgreement_Action", linkedHashMap);
    }

    @Analytics(name = "LicenseAgreementPrompt", type = Analytics.Type.ACTION)
    public void licenseAgreementPrompt(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("LicenseAgreementPrompt_Action", linkedHashMap);
    }

    @Analytics(name = "LicenseAgreementSettings", type = Analytics.Type.ACTION)
    public void licenseAgreementSettingsAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("LicenseAgreementSettings_Action", linkedHashMap);
    }

    @Analytics(name = "Acc_In_Use", type = Analytics.Type.EVENT)
    public void logAccount(@Analytics.Param(name = "domain") String str, @Analytics.Param(name = "userOptOut") String str2, @Analytics.Param(name = MailBoxFolder.COL_NAME_META_THREAD) String str3, @Analytics.Param(name = "theme") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("domain", String.valueOf(str));
        linkedHashMap.put("userOptOut", String.valueOf(str2));
        linkedHashMap.put(MailBoxFolder.COL_NAME_META_THREAD, String.valueOf(str3));
        linkedHashMap.put("theme", String.valueOf(str4));
        this.mEventLogger.logEvent("Acc_In_Use_Event", linkedHashMap);
    }

    @Analytics(name = "Welcome_Add_Account_Clicked", type = Analytics.Type.ACTION)
    public void logAddRegistrationClick() {
        this.mEventLogger.logEvent("Welcome_Add_Account_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "SendPushSettingsCmdAuth", type = Analytics.Type.EVENT)
    public void logAuthCmdSucceed(@Analytics.Param(name = "status") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("status", String.valueOf(str));
        this.mEventLogger.logEvent("SendPushSettingsCmdAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.EVENT)
    public void logCodeExchangeResult(@Analytics.Param(name = "CodeExchangeResult") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("CodeExchangeResult", String.valueOf(str));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "EditModeExit", type = Analytics.Type.ACTION)
    public void logDisablingEditMode(@Analytics.Param(name = "Reason") String str, @Analytics.Param(name = "Folder") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Reason", String.valueOf(str));
        linkedHashMap.put("Folder", String.valueOf(str2));
        this.mEventLogger.logEvent("EditModeExit_Action", linkedHashMap);
    }

    @Analytics(name = "EditModeStart", type = Analytics.Type.ACTION)
    public void logEnablingEditMode(@Analytics.Param(name = "Reason") String str, @Analytics.Param(name = "Folder") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Reason", String.valueOf(str));
        linkedHashMap.put("Folder", String.valueOf(str2));
        this.mEventLogger.logEvent("EditModeStart_Action", linkedHashMap);
    }

    @Analytics(name = "Sanitize_Cookie", type = Analytics.Type.ERROR)
    public void logFailedSanitizeUrl(@Analytics.Param(name = "page") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("page", String.valueOf(str));
        this.mEventLogger.logEvent("Sanitize_Cookie_Error", linkedHashMap);
    }

    @Analytics(name = "DeviceStorage_State", type = Analytics.Type.EVENT)
    public void logFreeSpaceInfo(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("DeviceStorage_State_Event", linkedHashMap);
    }

    @Analytics(name = "GeoUrlOpened", type = Analytics.Type.ACTION)
    public void logGeoUrlOpened(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("GeoUrlOpened_Action", linkedHashMap);
    }

    @Analytics(name = "Sanitize_Cookie", params = {@Analytics.Param(name = "type", value = "header_absent")}, type = Analytics.Type.ERROR)
    public void logHeaderCookieAbsent() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", "header_absent");
        this.mEventLogger.logEvent("Sanitize_Cookie_Error", linkedHashMap);
    }

    @Analytics(name = "Logo_Mail_Click", type = Analytics.Type.ACTION)
    public void logMailClick() {
        this.mEventLogger.logEvent("Logo_Mail_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "Message_content_updated", type = Analytics.Type.EVENT)
    public void logMessageContentUpdate(@Analytics.Param(name = "ACCOUNT") String str, @Analytics.Param(name = "FOLDER_ID") long j10, @Analytics.Param(name = "MESSAGE_ID") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ACCOUNT", String.valueOf(str));
        linkedHashMap.put("FOLDER_ID", String.valueOf(j10));
        linkedHashMap.put("MESSAGE_ID", String.valueOf(str2));
        this.mEventLogger.logEvent("Message_content_updated_Event", linkedHashMap);
    }

    @Analytics(name = "Welcome_New_Registration_Clicked", type = Analytics.Type.ACTION)
    public void logNewRegistrationClick() {
        this.mEventLogger.logEvent("Welcome_New_Registration_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "Sanitize_Cookie", params = {@Analytics.Param(name = "type", value = "non_redirect")}, type = Analytics.Type.ERROR)
    public void logNonRedirectStatus(@Analytics.Param(name = "status") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", "non_redirect");
        linkedHashMap.put("status", String.valueOf(i10));
        this.mEventLogger.logEvent("Sanitize_Cookie_Error", linkedHashMap);
    }

    @Analytics(name = "Attach_Download_v4", type = Analytics.Type.ERROR)
    public void logParticularError(@Analytics.Param(name = "all_attaches_exists") String str, @Analytics.Param(name = "folder_id") long j10, @Analytics.Param(name = "attach_ext") String str2, @Analytics.Param(name = "attach_size") String str3, @Analytics.Param(name = AttachStatus.COL_NAME_TIMES_REQUESTED) int i10, @Analytics.Param(name = AttachStatus.COL_NAME_TIMES_DOWNLOADED) int i11, @Analytics.Param(name = "reason") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("all_attaches_exists", String.valueOf(str));
        linkedHashMap.put("folder_id", String.valueOf(j10));
        linkedHashMap.put("attach_ext", String.valueOf(str2));
        linkedHashMap.put("attach_size", String.valueOf(str3));
        linkedHashMap.put(AttachStatus.COL_NAME_TIMES_REQUESTED, String.valueOf(i10));
        linkedHashMap.put(AttachStatus.COL_NAME_TIMES_DOWNLOADED, String.valueOf(i11));
        linkedHashMap.put("reason", String.valueOf(str4));
        this.mEventLogger.logEvent("Attach_Download_v4_Error", linkedHashMap);
    }

    @Analytics(name = "AppStartSending", type = Analytics.Type.ERROR)
    public void logSendingErrorAnalytics(@Analytics.Param(name = "reason") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("reason", String.valueOf(str));
        this.mEventLogger.logEvent("AppStartSending_Error", linkedHashMap);
    }

    @Analytics(name = "AppStartSent", type = Analytics.Type.EVENT)
    public void logSentSuccessfullyAnalytics() {
        this.mEventLogger.logEvent("AppStartSent_Event", new LinkedHashMap());
    }

    @Analytics(name = "Settings_Send", params = {@Analytics.Param(name = "State", value = "TrackingRedirectFailed")}, type = Analytics.Type.EVENT)
    public void logSettingsSendResponseState(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("State", "TrackingRedirectFailed");
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("Settings_Send_Event", linkedHashMap);
    }

    @Analytics(name = "ShrinkDeclined", type = Analytics.Type.EVENT)
    public void logShrinkDeclined() {
        this.mEventLogger.logEvent("ShrinkDeclined_Event", new LinkedHashMap());
    }

    @Analytics(name = "AppStartSending", type = Analytics.Type.EVENT)
    public void logStartSendingAnalytics(@Analytics.Param(name = "reason") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("reason", String.valueOf(str));
        this.mEventLogger.logEvent("AppStartSending_Event", linkedHashMap);
    }

    @Analytics(name = "AppSizeCalculatedV3", type = Analytics.Type.EVENT)
    public void logStorageInfo(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("AppSizeCalculatedV3_Event", linkedHashMap);
    }

    @Analytics(name = "AppSizeOtherCalculatedV3", type = Analytics.Type.EVENT)
    public void logStorageInfoOther(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("AppSizeOtherCalculatedV3_Event", linkedHashMap);
    }

    @Analytics(name = "SubscriptionPurchased", type = Analytics.Type.EVENT)
    public void logTrackPurchaseResult(@Analytics.Param(name = "subscriptionId") String str, @Analytics.Param(name = "isTracked") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("subscriptionId", String.valueOf(str));
        linkedHashMap.put("isTracked", String.valueOf(z10));
        this.mEventLogger.logEvent("SubscriptionPurchased_Event", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "Check")}, type = Analytics.Type.ACTION)
    public void loginActionCheck() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Check");
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "Focus_Login")}, type = Analytics.Type.ACTION)
    public void loginActionFocusLogin(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Focus_Login");
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "Focus_Password")}, type = Analytics.Type.ACTION)
    public void loginActionFocusPassword(@Analytics.Param(name = "State") boolean z10, @Analytics.Param("login_email") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Focus_Password");
        linkedHashMap.put("State", String.valueOf(z10));
        linkedHashMap.put("login_email", str);
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "action", value = "ImmediateCodeAuth")}, type = Analytics.Type.ACTION)
    public void loginActionImmediateCodeAuth() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "ImmediateCodeAuth");
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "missclick")}, type = Analytics.Type.ACTION)
    public void loginActionMissclick(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "missclick");
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "action", value = "OAuthLogin")}, type = Analytics.Type.ACTION)
    public void loginActionOAuthLogin(@Analytics.Param(name = "provider") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "OAuthLogin");
        linkedHashMap.put("provider", String.valueOf(str));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "RestorePassword")}, type = Analytics.Type.ACTION)
    public void loginActionRestorePassword(@Analytics.Param(name = "passwError") boolean z10, @Analytics.Param(name = "withemail") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "RestorePassword");
        linkedHashMap.put("passwError", String.valueOf(z10));
        linkedHashMap.put("withemail", String.valueOf(z11));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "LoginSSO", params = {@Analytics.Param(name = "action", value = "LoginSSO")}, type = Analytics.Type.ACTION)
    public void loginActionSSOOAuth() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "LoginSSO");
        this.mEventLogger.logEvent("Login_SSO_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "SelectService")}, type = Analytics.Type.ACTION)
    public void loginActionSelectService(@Analytics.Param(name = "Name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SelectService");
        linkedHashMap.put("Name", String.valueOf(str));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "signin"), @Analytics.Param(name = "step", value = "1step")}, type = Analytics.Type.ACTION)
    public void loginActionSignIn() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "signin");
        linkedHashMap.put("step", "1step");
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "View_Password")}, type = Analytics.Type.ACTION)
    public void loginActionViewPassword(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "View_Password");
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "LoginVkPassword", params = {@Analytics.Param(name = "action", value = "LoginVkPassword")}, type = Analytics.Type.ACTION)
    public void loginActionVkPasswordOAuth() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "LoginVkPassword");
        this.mEventLogger.logEvent("Login_VkPassword_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Error", value = "Error")}, type = Analytics.Type.ERROR)
    public void loginError(@Analytics.Param(name = "Domain_name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", "Error");
        linkedHashMap.put("Domain_name", String.valueOf(str));
        this.mEventLogger.logEvent("Login_Error", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Error", value = "invalid_login_or_password"), @Analytics.Param(name = "step", value = "1step")}, type = Analytics.Type.ERROR)
    public void loginErrorInvalidLoginOrPassword() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", "invalid_login_or_password");
        linkedHashMap.put("step", "1step");
        this.mEventLogger.logEvent("Login_Error", linkedHashMap);
    }

    @Analytics(name = "Login_ManualSettings", params = {@Analytics.Param(name = "Result", value = "Success")}, type = Analytics.Type.RESULT)
    public void loginManualSettingsResult(@Analytics.Param(name = "Domain_name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Success");
        linkedHashMap.put("Domain_name", String.valueOf(str));
        this.mEventLogger.logEvent("Login_ManualSettings_Result", linkedHashMap);
    }

    @Analytics(name = "Login_ManualSettings", type = Analytics.Type.VIEW)
    public void loginManualSettingsView() {
        this.mEventLogger.logEvent("Login_ManualSettings_View", new LinkedHashMap());
    }

    @Analytics(name = "LoginMycom", type = Analytics.Type.ACTION)
    public void loginMyComAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("LoginMycom_Action", linkedHashMap);
    }

    @Analytics(name = "LoginMycom", params = {@Analytics.Param(name = "Error", value = "invalid_code")}, type = Analytics.Type.ERROR)
    public void loginMyComErrorInvalidCode() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", "invalid_code");
        this.mEventLogger.logEvent("LoginMycom_Error", linkedHashMap);
    }

    @Analytics(name = "LoginMycom", type = Analytics.Type.VIEW)
    public void loginMyComView() {
        this.mEventLogger.logEvent("LoginMycom_View", new LinkedHashMap());
    }

    @Analytics(name = "LoginRequired", type = Analytics.Type.ERROR)
    public void loginRequiredError() {
        this.mEventLogger.logEvent("LoginRequired_Error", new LinkedHashMap());
    }

    @Analytics(name = "Login_SSO", type = Analytics.Type.VIEW)
    public void loginSSOView() {
        this.mEventLogger.logEvent("Login_SSO_View", new LinkedHashMap());
    }

    @Analytics(params = {@Analytics.Param(name = "step", value = "1step")}, type = Analytics.Type.ERROR)
    public void loginScreenAuthError(@Analytics.Name String str, @Analytics.Param(name = "Error") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", "1step");
        linkedHashMap.put("Error", String.valueOf(i10));
        this.mEventLogger.logEvent(str + "_Error", linkedHashMap);
    }

    @Analytics(name = "Login_TwoFactor", params = {@Analytics.Param(name = "Result", value = "Success")}, type = Analytics.Type.RESULT)
    public void loginTwoFactorResult() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Success");
        this.mEventLogger.logEvent("Login_TwoFactor_Result", linkedHashMap);
    }

    @Analytics(name = "Login_TwoFactor", type = Analytics.Type.VIEW)
    public void loginTwoFactorView() {
        this.mEventLogger.logEvent("Login_TwoFactor_View", new LinkedHashMap());
    }

    @Analytics(name = "Login", type = Analytics.Type.VIEW)
    public void loginView(@Analytics.Param(name = "From") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("From", String.valueOf(str));
        this.mEventLogger.logEvent("Login_View", linkedHashMap);
    }

    @Analytics(name = "Login_VkPassword", type = Analytics.Type.VIEW)
    public void loginVkPasswordView() {
        this.mEventLogger.logEvent("Login_VkPassword_View", new LinkedHashMap());
    }

    @Analytics(name = "Logo_List_Click", type = Analytics.Type.ACTION)
    public void logoListClick(@Analytics.Param(name = "service") String str, @Analytics.Param(name = "from") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("service", String.valueOf(str));
        linkedHashMap.put("from", String.valueOf(str2));
        this.mEventLogger.logEvent("Logo_List_Click_Action", linkedHashMap);
    }

    @Analytics(name = "MailAppWidget_setCounter", type = Analytics.Type.ERROR)
    public void mailAppWidgetSetCounterError(@Analytics.Param(name = "counter") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("counter", String.valueOf(i10));
        this.mEventLogger.logEvent("MailAppWidget_setCounter_Error", linkedHashMap);
    }

    @Analytics(name = "MailToMyself", params = {@Analytics.Param(name = "Action", value = "Send")}, type = Analytics.Type.ACTION)
    public void mailToMyselfActionSend() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Send");
        this.mEventLogger.logEvent("MailToMyself_Action", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", type = Analytics.Type.ACTION)
    public void markMailsAndUnselectSearchResults(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", type = Analytics.Type.ACTION)
    public void markMailsAndUnselectThreadList(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = "MarkNotSpam")}, type = Analytics.Type.ACTION)
    public void markNoSpamSelectedItemsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "MarkNotSpam");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = "MarkNotSpam")}, type = Analytics.Type.ACTION)
    public void markNoSpamSelectedItemsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "MarkNotSpam");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = MarkSpamOperation.TAG_MARK_SPAM)}, type = Analytics.Type.ACTION)
    public void markSpamSelectedItemsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", MarkSpamOperation.TAG_MARK_SPAM);
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = MarkSpamOperation.TAG_MARK_SPAM)}, type = Analytics.Type.ACTION)
    public void markSpamSelectedItemsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "Count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", MarkSpamOperation.TAG_MARK_SPAM);
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("Count", String.valueOf(str));
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "Message_Attach", type = Analytics.Type.ACTION)
    public void messageAttachAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("Message_Attach_Action", linkedHashMap);
    }

    @Analytics(name = "Message_Attach", type = Analytics.Type.VIEW)
    public void messageAttachView(@Analytics.Param(name = "MIME_Type") String str, @Analytics.Param(name = "Type") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("MIME_Type", String.valueOf(str));
        linkedHashMap.put("Type", String.valueOf(str2));
        this.mEventLogger.logEvent("Message_Attach_View", linkedHashMap);
    }

    @Analytics(name = "MessageList", type = Analytics.Type.ACTION)
    public void messageListAction(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "vpnAnalyticsInfo") VpnInfoProvider vpnInfoProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("vpn_probability", vpnInfoProvider.getVpnProbability());
        linkedHashMap.put("vpn_state", vpnInfoProvider.getVpnState());
        linkedHashMap.put("vpn_throttle_delay_ms", vpnInfoProvider.getVpnThrottleDelay());
        linkedHashMap.put("is_vpn_enabled", vpnInfoProvider.isVpnEnabled());
        linkedHashMap.put("is_vpn_check_enabled", vpnInfoProvider.isVpnCheckEnabled());
        linkedHashMap.put("is_vpn_geoip", vpnInfoProvider.isVpnGeoip());
        linkedHashMap.put("is_wifi", vpnInfoProvider.isWifi());
        linkedHashMap.put("is_sdk", vpnInfoProvider.isSdk());
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel_action1", type = Analytics.Type.ACTION)
    public void messageListPanelAction1(@Analytics.Param(name = "panelName") String str, @Analytics.Param(name = "panelId") String str2, @Analytics.Param(name = "viewCount") int i10, @Analytics.Param(name = "showSequenceIndex") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        linkedHashMap.put("panelId", String.valueOf(str2));
        linkedHashMap.put("viewCount", String.valueOf(i10));
        linkedHashMap.put("showSequenceIndex", String.valueOf(i11));
        this.mEventLogger.logEvent("MessageListPanel_action1_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel_action2", type = Analytics.Type.ACTION)
    public void messageListPanelAction2(@Analytics.Param(name = "panelName") String str, @Analytics.Param(name = "panelId") String str2, @Analytics.Param(name = "viewCount") int i10, @Analytics.Param(name = "showSequenceIndex") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        linkedHashMap.put("panelId", String.valueOf(str2));
        linkedHashMap.put("viewCount", String.valueOf(i10));
        linkedHashMap.put("showSequenceIndex", String.valueOf(i11));
        this.mEventLogger.logEvent("MessageListPanel_action2_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel_Skip", type = Analytics.Type.ACTION)
    public void messageListPanelActionSkip(@Analytics.Param(name = "panelName") String str, @Analytics.Param(name = "panelId") String str2, @Analytics.Param(name = "viewCount") int i10, @Analytics.Param(name = "showSequenceIndex") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        linkedHashMap.put("panelId", String.valueOf(str2));
        linkedHashMap.put("viewCount", String.valueOf(i10));
        linkedHashMap.put("showSequenceIndex", String.valueOf(i11));
        this.mEventLogger.logEvent("MessageListPanel_Skip_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel_action1", type = Analytics.Type.ACTION)
    public void messageListPanelFirstAction(@Analytics.Param(name = "panelName") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        this.mEventLogger.logEvent("MessageListPanel_action1_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel_action2", type = Analytics.Type.ACTION)
    public void messageListPanelSecondAction(@Analytics.Param(name = "panelName") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        this.mEventLogger.logEvent("MessageListPanel_action2_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListPanel", type = Analytics.Type.VIEW)
    public void messageListPanelView(@Analytics.Param(name = "panelName") String str, @Analytics.Param(name = "panelId") String str2, @Analytics.Param(name = "viewCount") int i10, @Analytics.Param(name = "showSequenceIndex") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("panelName", String.valueOf(str));
        linkedHashMap.put("panelId", String.valueOf(str2));
        linkedHashMap.put("viewCount", String.valueOf(i10));
        linkedHashMap.put("showSequenceIndex", String.valueOf(i11));
        this.mEventLogger.logEvent("MessageListPanel_View", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Action", value = "SelectFilter")}, type = Analytics.Type.ACTION)
    public void messageListSelectFilter(@Analytics.Param(name = "Filter") String str, MailListDateInfo mailListDateInfo) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SelectFilter");
        linkedHashMap.put("Filter", String.valueOf(str));
        linkedHashMap.put("DepartureDate", mailListDateInfo.getDepartureDate());
        linkedHashMap.put("DepartureDiffDay", mailListDateInfo.getDepartureDiffDay());
        linkedHashMap.put("DepartureDiffLetters", mailListDateInfo.getDepartureDiffLetters());
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListUndo", type = Analytics.Type.ACTION)
    public void messageListUndoAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("MessageListUndo_Action", linkedHashMap);
    }

    @Analytics(name = "MessageList", type = Analytics.Type.VIEW)
    public void messageListView(@Analytics.Param(name = "fromPush") boolean z10, @Analytics.Param(name = "install_source") String str, @Analytics.Param(name = "Folder_name") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fromPush", String.valueOf(z10));
        linkedHashMap.put("install_source", String.valueOf(str));
        linkedHashMap.put("Folder_name", String.valueOf(str2));
        this.mEventLogger.logEvent("MessageList_View", linkedHashMap);
    }

    @Analytics(name = "MessageList", type = Analytics.Type.VIEW)
    public void messageListViewWithThread(@Analytics.Param(name = "fromPush") boolean z10, @Analytics.Param(name = "Thread") boolean z11, @Analytics.Param(name = "install_source") String str, @Analytics.Param(name = "Folder_name") String str2, @Analytics.Param(name = "vpnAnalyticsInfo") VpnInfoProvider vpnInfoProvider) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fromPush", String.valueOf(z10));
        linkedHashMap.put("Thread", String.valueOf(z11));
        linkedHashMap.put("Folder_name", String.valueOf(str2));
        linkedHashMap.put("install_source", String.valueOf(str));
        linkedHashMap.put("vpn_probability", vpnInfoProvider.getVpnProbability());
        linkedHashMap.put("vpn_state", vpnInfoProvider.getVpnState());
        linkedHashMap.put("vpn_throttle_delay_ms", vpnInfoProvider.getVpnThrottleDelay());
        linkedHashMap.put("is_vpn_enabled", vpnInfoProvider.isVpnEnabled());
        linkedHashMap.put("is_vpn_check_enabled", vpnInfoProvider.isVpnCheckEnabled());
        linkedHashMap.put("is_vpn_geoip", vpnInfoProvider.isVpnGeoip());
        linkedHashMap.put("is_wifi", vpnInfoProvider.isWifi());
        linkedHashMap.put("is_sdk", vpnInfoProvider.isSdk());
        this.mEventLogger.logEvent("MessageList_View", linkedHashMap);
    }

    @Analytics(name = "Message_OptionMenu", type = Analytics.Type.VIEW)
    public void messageOptionMenuView() {
        this.mEventLogger.logEvent("Message_OptionMenu_View", new LinkedHashMap());
    }

    @Analytics(name = "Message_Send_Enqueue", type = Analytics.Type.EVENT)
    public void messageSendEnqueue(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("Message_Send_Enqueue_Event", linkedHashMap);
    }

    @Analytics(name = "MessageUndo", type = Analytics.Type.ACTION)
    public void messageUndoAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("MessageUndo_Action", linkedHashMap);
    }

    @Analytics(name = "MessageView", type = Analytics.Type.EVENT)
    public void messageViewEvent(@Analytics.Param(name = "html_thumbnail_shown") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("html_thumbnail_shown", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageView_Event", linkedHashMap);
    }

    @Analytics(name = "MessageView_MessageWithSmartReplyOpened", type = Analytics.Type.ACTION)
    public void messageViewMessageWithSmartReplyOpened(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("MessageView_MessageWithSmartReplyOpened_Action", linkedHashMap);
    }

    @Analytics(name = "MessageView_MessageWithSmartReplyOpened_Stage", type = Analytics.Type.ACTION)
    public void messageViewMessageWithSmartReplyOpenedStage(@Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageView_MessageWithSmartReplyOpened_Stage_Action", linkedHashMap);
    }

    @Analytics(name = "Message_View_Read", type = Analytics.Type.ACTION)
    public void messageViewRead(@Analytics.Param(name = MailThread.COL_NAME_LENGTH) String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(MailThread.COL_NAME_LENGTH, String.valueOf(str));
        this.mEventLogger.logEvent("Message_View_Read_Action", linkedHashMap);
    }

    @Analytics(name = "MessageViewScroll", type = Analytics.Type.EVENT)
    public void messageViewScrollEvent(@Analytics.Param(name = "isNewsletter") String str, @Analytics.Param(name = "mailId") String str2, @Analytics.Param(name = "from") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("is_newsletter", str);
        linkedHashMap.put("uidl", str2);
        linkedHashMap.put("email_sender", str3);
        this.mEventLogger.logEvent("MessageViewScroll_Event", linkedHashMap);
    }

    @Analytics(name = "MessageView_SmartReplyClicked", type = Analytics.Type.ACTION)
    public void messageViewSmartReplyClicked(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("MessageView_SmartReplyClicked_Action", linkedHashMap);
    }

    @Analytics(name = "MessageView_SmartReplyClickedStage", type = Analytics.Type.ACTION)
    public void messageViewSmartReplyClickedStage(@Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageView_SmartReplyClickedStage_Action", linkedHashMap);
    }

    @Analytics(name = "MessageView_SmartReplyShown_Stage", type = Analytics.Type.VIEW)
    public void messageViewSmartReplyShownStageView(@Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageView_SmartReplyShown_Stage_View", linkedHashMap);
    }

    @Analytics(name = "MessageView_SmartReplyShown", type = Analytics.Type.VIEW)
    public void messageViewSmartReplyShownView(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("MessageView_SmartReplyShown_View", linkedHashMap);
    }

    @Analytics(name = "MessageView_Squash_Button_Clicked", type = Analytics.Type.EVENT)
    public void messageViewSquashButtonClicked(@Analytics.Param(name = "isExpanded") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isExpanded", String.valueOf(z10));
        this.mEventLogger.logEvent("MessageView_Squash_Button_Clicked_Event", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.ACTION)
    public void metaThreadToolbarAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str2));
        this.mEventLogger.logEvent("MetaThreadToolbar_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.ACTION)
    public void metaThreadToolbarActionWithCountBucket(@Analytics.Param(name = "action") String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str2, @Analytics.Param(name = "CountBucket") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str2));
        linkedHashMap.put("CountBucket", String.valueOf(str3));
        this.mEventLogger.logEvent("MetaThreadToolbar_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.ACTION)
    public void metaThreadToolbarActionWithFolder(@Analytics.Param(name = "action") String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str2, @Analytics.Param(name = "folder") String str3, @Analytics.Param(name = "CountBucket") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(str3));
        linkedHashMap.put("CountBucket", String.valueOf(str4));
        this.mEventLogger.logEvent("MetaThreadToolbar_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.VIEW)
    public void metaThreadToolbarView(@Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str));
        this.mEventLogger.logEvent("MetaThreadToolbar_View", linkedHashMap);
    }

    @Analytics(name = "MiniAppsCatalog_Cancelled_Auth", type = Analytics.Type.ACTION)
    public void miniAppsCatalogCancelledAuth() {
        this.mEventLogger.logEvent("MiniAppsCatalog_Cancelled_Auth_Action", new LinkedHashMap());
    }

    @Analytics(name = "MiniAppsCatalog_User_Authorize_In_Vk_Connect", type = Analytics.Type.ACTION)
    public void miniAppsCatalogUserAuthorizeInVkConnect() {
        this.mEventLogger.logEvent("MiniAppsCatalog_User_Authorize_In_Vk_Connect_Action", new LinkedHashMap());
    }

    @Analytics(name = "Miniapp_Check_Bind", type = Analytics.Type.ERROR)
    public void miniappCheckBindError() {
        this.mEventLogger.logEvent("Miniapp_Check_Bind_Error", new LinkedHashMap());
    }

    @Analytics(name = "Miniapp_Check_Bind", type = Analytics.Type.EVENT)
    public void miniappCheckBindSuccess() {
        this.mEventLogger.logEvent("Miniapp_Check_Bind_Event", new LinkedHashMap());
    }

    @Analytics(name = "Miniapp_Set_Bind", type = Analytics.Type.ERROR)
    public void miniappSetBindError() {
        this.mEventLogger.logEvent("Miniapp_Set_Bind_Error", new LinkedHashMap());
    }

    @Analytics(name = "Miniapp_Set_Bind", type = Analytics.Type.EVENT)
    public void miniappSetBindSuccess() {
        this.mEventLogger.logEvent("Miniapp_Set_Bind_Event", new LinkedHashMap());
    }

    @Analytics(name = "MoreTabCleanMasterShown", type = Analytics.Type.EVENT)
    public void moreTabCleanMasterShown() {
        this.mEventLogger.logEvent("MoreTabCleanMasterShown_Event", new LinkedHashMap());
    }

    @Analytics(name = "MiniAppsCatalogShown", type = Analytics.Type.EVENT)
    public void moreTabMiniAppsCatalogShown(@Analytics.Param(name = "authorized_in_vk_connect") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("authorized_in_vk_connect", String.valueOf(z10));
        this.mEventLogger.logEvent("MiniAppsCatalogShown_Event", linkedHashMap);
    }

    @Analytics(name = "MoreTabCleanMasterClicked", type = Analytics.Type.ACTION)
    public void moreTabOnCleanMasterClicked() {
        this.mEventLogger.logEvent("MoreTabCleanMasterClicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "MiniAppsCatalogClicked", type = Analytics.Type.ACTION)
    public void moreTabOnMiniAppsCatalogClicked(@Analytics.Param(name = "authorized_in_vk_connect") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("authorized_in_vk_connect", String.valueOf(z10));
        this.mEventLogger.logEvent("MiniAppsCatalogClicked_Action", linkedHashMap);
    }

    @Analytics(name = "MoreTabShown", type = Analytics.Type.EVENT)
    public void moreTabShown(@Analytics.Param(name = "count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("count", String.valueOf(i10));
        this.mEventLogger.logEvent("MoreTabShown_Event", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = "Move")}, type = Analytics.Type.ACTION)
    public void moveSelectedItemsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str, @Analytics.Param(name = "From") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Move");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        linkedHashMap.put("From", str2);
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = "Move")}, type = Analytics.Type.ACTION)
    public void moveSelectedItemsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str, @Analytics.Param(name = "From") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Move");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        linkedHashMap.put("From", str2);
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "SearchResultsListEdit", params = {@Analytics.Param(name = "Action", value = "MoveToBin")}, type = Analytics.Type.ACTION)
    public void moveToBinSelectedItemsSearchResults(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "BlockedCount") int i11, @Analytics.Param(name = "CountBucket") String str, @Analytics.Param(name = "TrashType") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "MoveToBin");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("BlockedCount", String.valueOf(i11));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        linkedHashMap.put("TrashType", str2);
        this.mEventLogger.logEvent("SearchResultsListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadListEdit", params = {@Analytics.Param(name = "Action", value = "MoveToBin")}, type = Analytics.Type.ACTION)
    public void moveToBinSelectedItemsThreadList(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str, @Analytics.Param(name = "TrashType") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "MoveToBin");
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        linkedHashMap.put("TrashType", str2);
        this.mEventLogger.logEvent("ThreadListEdit_Action", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.EVENT)
    public void mrSdkAuthEvent(@Analytics.Param(name = "status") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("status", String.valueOf(str));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", params = {@Analytics.Param(name = "Action", value = "getAccounts")}, type = Analytics.Type.EVENT)
    public void mrSdkAuthGetAccount(@Analytics.Param(name = "Client") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "getAccounts");
        linkedHashMap.put("Client", String.valueOf(str));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.EVENT)
    public void mrSdkAuthGetAccountStatus(@Analytics.Param(name = "Client") String str, @Analytics.Param(name = "getAccountsStatus") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Client", String.valueOf(str));
        linkedHashMap.put("getAccountsStatus", String.valueOf(str2));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", params = {@Analytics.Param(name = "Action", value = "loginQuiet")}, type = Analytics.Type.EVENT)
    public void mrSdkAuthLoginQuiet(@Analytics.Param(name = "Client") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "loginQuiet");
        linkedHashMap.put("Client", String.valueOf(str));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.EVENT)
    public void mrSdkAuthLoginQuietStatus(@Analytics.Param(name = "Client") String str, @Analytics.Param(name = "LoginQuietStatus") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Client", String.valueOf(str));
        linkedHashMap.put("LoginQuietStatus", String.valueOf(str2));
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.VIEW)
    public void mrSdkAuthView(@Analytics.Param(name = "Client") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Client", String.valueOf(str));
        this.mEventLogger.logEvent("MRSDKAuth_View", linkedHashMap);
    }

    @Analytics(name = "MrimDisabled", params = {@Analytics.Param(name = "Action", value = "Unblock")}, type = Analytics.Type.ACTION)
    public void mrimDiabledAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Unblock");
        this.mEventLogger.logEvent("MrimDisabled_Action", linkedHashMap);
    }

    @Analytics(name = "MrimDisabled", type = Analytics.Type.VIEW)
    public void mrimDisabledView() {
        this.mEventLogger.logEvent("MrimDisabled_View", new LinkedHashMap());
    }

    @Analytics(name = "MRSDKAuth", params = {@Analytics.Param(name = "Action", value = "oauth_webview")}, type = Analytics.Type.EVENT)
    public void mrsdkAuthOAuthWebview() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "oauth_webview");
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MRSDKAuth", params = {@Analytics.Param(name = "Action", value = "silent_code")}, type = Analytics.Type.EVENT)
    public void mrsdkAuthSilentCode() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "silent_code");
        this.mEventLogger.logEvent("MRSDKAuth_Event", linkedHashMap);
    }

    @Analytics(name = "MultiSelectTutorial", type = Analytics.Type.ACTION)
    public void multiSelectTutorialAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("MultiSelectTutorial_Action", linkedHashMap);
    }

    @Analytics(type = Analytics.Type.EVENT)
    public void networkCommandAnalytics(@Analytics.Name String str, @Analytics.Param(name = "request_name") String str2, @Analytics.Param(name = "time") String str3, @Analytics.Param(name = "Network class") String str4, @Analytics.Param(name = "Device Age") String str5, @Analytics.Param(name = "vpn_mode_string") String str6, @Analytics.Param(name = "has_internet_connection") String str7, @Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("request_name", String.valueOf(str2));
        linkedHashMap.put("time", String.valueOf(str3));
        linkedHashMap.put("Network class", String.valueOf(str4));
        linkedHashMap.put("Device Age", String.valueOf(str5));
        linkedHashMap.put("vpn_mode_string", String.valueOf(str6));
        linkedHashMap.put("has_internet_connection", String.valueOf(str7));
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent(str + "_Event", linkedHashMap);
    }

    @Analytics(name = "NewEmailPopup_Cancelled", type = Analytics.Type.ACTION)
    public void newEmailPopupCancelled(@Analytics.Param(name = "way") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("way", String.valueOf(str));
        this.mEventLogger.logEvent("NewEmailPopup_Cancelled_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailPopup_ContactClicked", type = Analytics.Type.ACTION)
    public void newEmailPopupContactClicked(@Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        this.mEventLogger.logEvent("NewEmailPopup_ContactClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailPopup_ContactsUpdated", type = Analytics.Type.EVENT)
    public void newEmailPopupContactsUpdated() {
        this.mEventLogger.logEvent("NewEmailPopup_ContactsUpdated_Event", new LinkedHashMap());
    }

    @Analytics(name = "Portal_NewEmailPopup_CreateEvent", params = {@Analytics.Param(name = "mode", value = "portal")}, type = Analytics.Type.ACTION)
    public void newEmailPopupCreateEvent() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("mode", "portal");
        this.mEventLogger.logEvent("Portal_NewEmailPopup_CreateEvent_Action", linkedHashMap);
    }

    @Analytics(name = "Portal_NewEmailPopup_CreateTask", params = {@Analytics.Param(name = "mode", value = "portal")}, type = Analytics.Type.ACTION)
    public void newEmailPopupCreateTask() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("mode", "portal");
        this.mEventLogger.logEvent("Portal_NewEmailPopup_CreateTask_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailPopup_EmailToMyselfClicked", type = Analytics.Type.ACTION)
    public void newEmailPopupEmailToMyselfClicked() {
        this.mEventLogger.logEvent("NewEmailPopup_EmailToMyselfClicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "NewEmailPopup_EmailToMyselfFunnel", type = Analytics.Type.EVENT)
    public void newEmailPopupEmailToMyselfFunnelEvent() {
        this.mEventLogger.logEvent("NewEmailPopup_EmailToMyselfFunnel_Event", new LinkedHashMap());
    }

    @Analytics(name = "NewEmailPopup", type = Analytics.Type.VIEW)
    public void newEmailPopupView(@Analytics.Param(name = "contacts_count") int i10, @Analytics.Param(name = "email_to_myself_enabled") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("contacts_count", String.valueOf(i10));
        linkedHashMap.put("email_to_myself_enabled", String.valueOf(z10));
        this.mEventLogger.logEvent("NewEmailPopup_View", linkedHashMap);
    }

    @Analytics(name = "NewEmailPopup_WriteEmailClicked", type = Analytics.Type.ACTION)
    public void newEmailPopupWriteEmailClicked() {
        this.mEventLogger.logEvent("NewEmailPopup_WriteEmailClicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "NoAuth", type = Analytics.Type.ERROR)
    public void noAuthError(@Analytics.Param(name = "type") String str, @Analytics.Param(name = ApiUris.AUTHORITY_API) String str2, @Analytics.Param(name = "tokenType") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put(ApiUris.AUTHORITY_API, String.valueOf(str2));
        linkedHashMap.put("tokenType", String.valueOf(str3));
        this.mEventLogger.logEvent("NoAuth_Error", linkedHashMap);
    }

    @Analytics(name = "ContactNotification", params = {@Analytics.Param(name = "place", value = "contact_info")}, type = Analytics.Type.ACTION)
    public void notificationFilterStateChanged(@Analytics.Param(name = "mute") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("place", "contact_info");
        linkedHashMap.put("mute", String.valueOf(z10));
        this.mEventLogger.logEvent("ContactNotification_Action", linkedHashMap);
    }

    @Analytics(name = "ContactNotification", params = {@Analytics.Param(name = "place", value = "contact_info")}, type = Analytics.Type.VIEW)
    public void notificationPromoPlateShown() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("place", "contact_info");
        this.mEventLogger.logEvent("ContactNotification_View", linkedHashMap);
    }

    @Analytics(name = "Notifications", type = Analytics.Type.ACTION)
    public void notificationsAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("Notifications_Action", linkedHashMap);
    }

    @Analytics(name = "Notifications_DoNotDisturb", type = Analytics.Type.ACTION)
    public void notificationsDoNotDisturbAction(@Analytics.Param(name = "State") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("State", String.valueOf(str));
        this.mEventLogger.logEvent("Notifications_DoNotDisturb_Action", linkedHashMap);
    }

    @Analytics(name = "Notifications_DoNotDisturb", type = Analytics.Type.VIEW)
    public void notificationsDoNotDisturbView() {
        this.mEventLogger.logEvent("Notifications_DoNotDisturb_View", new LinkedHashMap());
    }

    @Analytics(name = "Notifications", type = Analytics.Type.STATE)
    public void notificationsState(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("Notifications_State", linkedHashMap);
    }

    @Analytics(name = "OAuth_Native", type = Analytics.Type.EVENT)
    public void oAuthNativeEvent() {
        this.mEventLogger.logEvent("OAuth_Native_Event", new LinkedHashMap());
    }

    @Analytics(name = "OAuthTokenRefresh", type = Analytics.Type.ERROR)
    public void oAuthTokenRefreshError(@Analytics.Param(name = "code") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("code", String.valueOf(str));
        this.mEventLogger.logEvent("OAuthTokenRefresh_Error", linkedHashMap);
    }

    @Analytics(name = "OAuth_WebView", type = Analytics.Type.EVENT)
    public void oAuthWebViewEvent(@Analytics.Param(name = "provider") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("provider", String.valueOf(str));
        this.mEventLogger.logEvent("OAuth_WebView_Event", linkedHashMap);
    }

    @Analytics(name = "Accounts_Drawer_Avatar_click", type = Analytics.Type.ACTION)
    public void onAccountDrawerAvatarClick(@Analytics.Param(name = "drawerState") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("drawerState", String.valueOf(str));
        this.mEventLogger.logEvent("Accounts_Drawer_Avatar_click_Action", linkedHashMap);
    }

    @Analytics(name = "Accounts_Drawer_Expanded", type = Analytics.Type.VIEW)
    public void onAccountDrawerExpanded() {
        this.mEventLogger.logEvent("Accounts_Drawer_Expanded_View", new LinkedHashMap());
    }

    @Analytics(name = "Accounts_Drawer_Opened", type = Analytics.Type.VIEW)
    public void onAccountDrawerOpened() {
        this.mEventLogger.logEvent("Accounts_Drawer_Opened_View", new LinkedHashMap());
    }

    @Analytics(name = "RegistrationMycom", params = {@Analytics.Param(name = "Result", value = "Success")}, type = Analytics.Type.RESULT)
    public void onAccountRegistered(@Analytics.Param(name = "LocalErrorsCount") int i10, @Analytics.Param(name = "ServerErrorsCount") int i11, @Analytics.Param(name = "NextButtonClicksCount") int i12, @Analytics.Param(name = "HasNameErrors") boolean z10, @Analytics.Param(name = "HasSecondNameErrors") boolean z11, @Analytics.Param(name = "SuggestedEmailClicked") boolean z12, @Analytics.Param(name = "SuggestedEmailUsed") boolean z13) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Success");
        linkedHashMap.put("LocalErrorsCount", String.valueOf(i10));
        linkedHashMap.put("ServerErrorsCount", String.valueOf(i11));
        linkedHashMap.put("NextButtonClicksCount", String.valueOf(i12));
        linkedHashMap.put("HasNameErrors", String.valueOf(z10));
        linkedHashMap.put("HasSecondNameErrors", String.valueOf(z11));
        linkedHashMap.put("SuggestedEmailClicked", String.valueOf(z12));
        linkedHashMap.put("SuggestedEmailUsed", String.valueOf(z13));
        this.mEventLogger.logEvent("RegistrationMycom_Result", linkedHashMap);
    }

    @Analytics(name = "Settings_Security", type = Analytics.Type.VIEW)
    public void onAccountSelectViewByClickAddPhonePref() {
        this.mEventLogger.logEvent("Settings_Security_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings_CleanMaster", type = Analytics.Type.VIEW)
    public void onAccountSelectViewByClickManageSubscriptionPref() {
        this.mEventLogger.logEvent("Settings_CleanMaster_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings_Security_Click", type = Analytics.Type.ACTION)
    public void onAccountSelectedAddPhonePref(@Analytics.Param(name = "MultipleAccounts") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("MultipleAccounts", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_Security_Click_Action", linkedHashMap);
    }

    @Analytics(name = "LicenseAgreement", type = Analytics.Type.VIEW)
    public void onAgreementDialogShown() {
        this.mEventLogger.logEvent("LicenseAgreement_View", new LinkedHashMap());
    }

    @Analytics(name = "LicenseAgreementPrompt", type = Analytics.Type.VIEW)
    public void onAgreementPromptDialogShown() {
        this.mEventLogger.logEvent("LicenseAgreementPrompt_View", new LinkedHashMap());
    }

    @Analytics(name = "AnimatedEmptyStateShown", type = Analytics.Type.EVENT)
    public void onAnimatedEmptyStateShown(@Analytics.Param(name = "folder") String str, @Analytics.Param(name = ViewerActivity.FILTER) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder", String.valueOf(str));
        linkedHashMap.put(ViewerActivity.FILTER, String.valueOf(str2));
        this.mEventLogger.logEvent("AnimatedEmptyStateShown_Event", linkedHashMap);
    }

    @Analytics(name = "AttachPreviewOnListClicked", radar = true, type = Analytics.Type.ACTION)
    public void onAttachPreviewFromListClicked(@Analytics.Param(name = "count") int i10, @Analytics.Param(dynamic = true, name = "message_id") String str, @Analytics.Param(name = "folder_id") long j10, @Analytics.Param(dynamic = true, name = "account") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        String strValueOf = String.valueOf(i10);
        linkedHashMap2.put("count", strValueOf);
        linkedHashMap.put("count", strValueOf);
        linkedHashMap2.put("message_id", String.valueOf(str));
        String strValueOf2 = String.valueOf(j10);
        linkedHashMap2.put("folder_id", strValueOf2);
        linkedHashMap.put("folder_id", strValueOf2);
        linkedHashMap2.put("account", String.valueOf(str2));
        this.mEventLogger.logEvent("AttachPreviewOnListClicked_Action", linkedHashMap);
        this.mEventLogger.logRadarEvent("AttachPreviewOnListClicked_Action", linkedHashMap2);
    }

    @Analytics(name = "AttachPreviewOnListLongClicked", radar = true, type = Analytics.Type.ACTION)
    public void onAttachPreviewFromListLongClicked(@Analytics.Param(name = "count") int i10, @Analytics.Param(dynamic = true, name = "message_id") String str, @Analytics.Param(name = "folder_id") long j10, @Analytics.Param(dynamic = true, name = "account") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        String strValueOf = String.valueOf(i10);
        linkedHashMap2.put("count", strValueOf);
        linkedHashMap.put("count", strValueOf);
        linkedHashMap2.put("message_id", String.valueOf(str));
        String strValueOf2 = String.valueOf(j10);
        linkedHashMap2.put("folder_id", strValueOf2);
        linkedHashMap.put("folder_id", strValueOf2);
        linkedHashMap2.put("account", String.valueOf(str2));
        this.mEventLogger.logEvent("AttachPreviewOnListLongClicked_Action", linkedHashMap);
        this.mEventLogger.logRadarEvent("AttachPreviewOnListLongClicked_Action", linkedHashMap2);
    }

    @Analytics(name = "AttachPreviewFromMailClicked", radar = true, type = Analytics.Type.ACTION)
    public void onAttachPreviewFromMailClicked(@Analytics.Param(dynamic = true, name = "message_id") String str, @Analytics.Param(dynamic = true, name = "account") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("message_id", String.valueOf(str));
        linkedHashMap2.put("account", String.valueOf(str2));
        this.mEventLogger.logEvent("AttachPreviewFromMailClicked_Action", linkedHashMap);
        this.mEventLogger.logRadarEvent("AttachPreviewFromMailClicked_Action", linkedHashMap2);
    }

    @Analytics(name = "AttachPreviewFromMailLongClicked", radar = true, type = Analytics.Type.ACTION)
    public void onAttachPreviewFromMailLongClicked(@Analytics.Param(dynamic = true, name = "message_id") String str, @Analytics.Param(dynamic = true, name = "account") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("message_id", String.valueOf(str));
        linkedHashMap2.put("account", String.valueOf(str2));
        this.mEventLogger.logEvent("AttachPreviewFromMailLongClicked_Action", linkedHashMap);
        this.mEventLogger.logRadarEvent("AttachPreviewFromMailLongClicked_Action", linkedHashMap2);
    }

    @Analytics(name = "AttachPreviewOnList", radar = true, type = Analytics.Type.VIEW)
    public void onAttachPreviewsShown(@Analytics.Param(name = "count") int i10, @Analytics.Param(dynamic = true, name = "message_id") String str, @Analytics.Param(name = "folder_id") long j10, @Analytics.Param(dynamic = true, name = "account") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        String strValueOf = String.valueOf(i10);
        linkedHashMap2.put("count", strValueOf);
        linkedHashMap.put("count", strValueOf);
        linkedHashMap2.put("message_id", String.valueOf(str));
        String strValueOf2 = String.valueOf(j10);
        linkedHashMap2.put("folder_id", strValueOf2);
        linkedHashMap.put("folder_id", strValueOf2);
        linkedHashMap2.put("account", String.valueOf(str2));
        this.mEventLogger.logEvent("AttachPreviewOnList_View", linkedHashMap);
        this.mEventLogger.logRadarEvent("AttachPreviewOnList_View", linkedHashMap2);
    }

    @Analytics(name = "AttachThumbnailLoaded", type = Analytics.Type.EVENT)
    public void onAttachThumbnailLoadedFromNetwork(@Analytics.Param(name = "location") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("location", String.valueOf(str));
        this.mEventLogger.logEvent("AttachThumbnailLoaded_Event", linkedHashMap);
    }

    @Analytics(name = "OnAuthCommandCompletedError", type = Analytics.Type.ERROR)
    public void onAuthCommandCompletedError(@Analytics.Param(name = "type") String str, @Analytics.Param(name = "domain") String str2, @Analytics.Param(name = "transport") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put("domain", String.valueOf(str2));
        linkedHashMap.put("transport", String.valueOf(str3));
        this.mEventLogger.logEvent("OnAuthCommandCompletedError_Error", linkedHashMap);
    }

    @Analytics(name = "Calendar_onCreateNewEvent", type = Analytics.Type.VIEW)
    public void onCalendarCreateNewEvent(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("Calendar_onCreateNewEvent_View", linkedHashMap);
    }

    @Analytics(name = "Calendar_onFailedCreateNewEvent", type = Analytics.Type.VIEW)
    public void onCalendarFailedCreateNewEvent(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("Calendar_onFailedCreateNewEvent_View", linkedHashMap);
    }

    @Analytics(name = "CalendarPushDismissed", type = Analytics.Type.ACTION)
    public void onCalendarPushDismissed(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "type") String str2, @Analytics.Param(name = "subtype") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        linkedHashMap.put("subtype", String.valueOf(str3));
        this.mEventLogger.logEvent("CalendarPushDismissed_Action", linkedHashMap);
    }

    @Analytics(name = "CalendarPushShowingDenied", type = Analytics.Type.EVENT)
    public void onCalendarPushShowingDenied(@Analytics.Param(name = "ml_type") String str, @Analytics.Param(name = "ml_subtype") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ml_type", String.valueOf(str));
        linkedHashMap.put("ml_subtype", String.valueOf(str2));
        this.mEventLogger.logEvent("CalendarPushShowingDenied_Event", linkedHashMap);
    }

    @Analytics(name = "Settings_CleanMaster", type = Analytics.Type.ACTION)
    public void onCleanMasterLoading() {
        this.mEventLogger.logEvent("Settings_CleanMaster_Action", new LinkedHashMap());
    }

    @Analytics(name = "FolderList", params = {@Analytics.Param(name = "Action", value = "ClearBin")}, type = Analytics.Type.ACTION)
    public void onClearBinActionFinished() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ClearBin");
        this.mEventLogger.logEvent("FolderList_Action", linkedHashMap);
    }

    @Analytics(name = "FolderList", params = {@Analytics.Param(name = "Action", value = "ClearSpam")}, type = Analytics.Type.ACTION)
    public void onClearSpamActionFinished() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ClearSpam");
        this.mEventLogger.logEvent("FolderList_Action", linkedHashMap);
    }

    @Analytics(name = "PushReceived", params = {@Analytics.Param(name = "Action", value = "click")}, type = Analytics.Type.ACTION)
    public void onClickPushMessageReceivedAnalytics(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "click");
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("PushReceived_Action", linkedHashMap);
    }

    @Analytics(name = "MessageList_Quick", params = {@Analytics.Param(name = "action", value = "HideNoAction")}, type = Analytics.Type.ACTION)
    public void onClosedWithoutAction(@Analytics.Param(name = MailBoxFolder.COL_NAME_META_THREAD) String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "HideNoAction");
        linkedHashMap.put(MailBoxFolder.COL_NAME_META_THREAD, String.valueOf(str));
        this.mEventLogger.logEvent("MessageList_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "Contact_Clicked", type = Analytics.Type.ACTION)
    public void onContactClickedAction(@Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10, @Analytics.Param(name = "type") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        linkedHashMap.put("type", String.valueOf(i11));
        this.mEventLogger.logEvent("Contact_Clicked_Action", linkedHashMap);
    }

    @Analytics(name = "Contacts_Export_Forced", type = Analytics.Type.EVENT)
    public void onContactExportForced() {
        this.mEventLogger.logEvent("Contacts_Export_Forced_Event", new LinkedHashMap());
    }

    @Analytics(name = "Contacts_Export_Settings", type = Analytics.Type.ACTION)
    public void onContactExportStatusChange(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("Contacts_Export_Settings_Action", linkedHashMap);
    }

    @Analytics(name = "Contact_Phoned", type = Analytics.Type.ACTION)
    public void onContactPhonedAction(@Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10, @Analytics.Param(name = "type") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        linkedHashMap.put("type", String.valueOf(i11));
        this.mEventLogger.logEvent("Contact_Phoned_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_ContactsSearchIconClicked", type = Analytics.Type.ACTION)
    public void onContactsSearchIconClicked(@Analytics.Param(name = "where") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("where", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_ContactsSearchIconClicked_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_ContactsSearchIconShown", type = Analytics.Type.EVENT)
    public void onContactsSearchIconShown(@Analytics.Param(name = "where") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("where", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_ContactsSearchIconShown_Event", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Action", value = "SelectFilter")}, type = Analytics.Type.ACTION)
    public void onCurrentFilterChanged(@Analytics.Param(name = "Filter") String str, @Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SelectFilter");
        linkedHashMap.put("Filter", String.valueOf(str));
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "DeepFastReplyClicked", type = Analytics.Type.ACTION)
    public void onDeepFastReplyClicked(@Analytics.Param(name = "brandName") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("brandName", String.valueOf(str));
        this.mEventLogger.logEvent("DeepFastReplyClicked_Action", linkedHashMap);
    }

    @Analytics(name = "DeleteAccount", type = Analytics.Type.VIEW)
    public void onDeleteAccountView() {
        this.mEventLogger.logEvent("DeleteAccount_View", new LinkedHashMap());
    }

    @Analytics(name = "Folders_Drawer_Header_Click", type = Analytics.Type.ACTION)
    public void onDrawerHeaderClick() {
        this.mEventLogger.logEvent("Folders_Drawer_Header_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "MultiSelectTutorial", type = Analytics.Type.VIEW)
    public void onEditModeTutorialListShown() {
        this.mEventLogger.logEvent("MultiSelectTutorial_View", new LinkedHashMap());
    }

    @Analytics(name = "MultiSelectTutorialSlide", type = Analytics.Type.VIEW)
    public void onEditModeTutorialSlideShown() {
        this.mEventLogger.logEvent("MultiSelectTutorialSlide_View", new LinkedHashMap());
    }

    @Analytics(name = "EmptyAttachOpened", type = Analytics.Type.EVENT)
    public void onEmptyAttachOpened(@Analytics.Param(name = "mailId") String str, @Analytics.Param(name = "attachName") String str2, @Analytics.Param(name = "attachExtension") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("mailId", String.valueOf(str));
        linkedHashMap.put("attachName", String.valueOf(str2));
        linkedHashMap.put("attachExtension", String.valueOf(str3));
        this.mEventLogger.logEvent("EmptyAttachOpened_Event", linkedHashMap);
    }

    @Analytics(name = "EmptyDeeplinkInWalletPush", type = Analytics.Type.EVENT)
    public void onEmptyDeeplinkInWalletPush() {
        this.mEventLogger.logEvent("EmptyDeeplinkInWalletPush_Event", new LinkedHashMap());
    }

    @Analytics(name = "LoadAttachActionFailed", type = Analytics.Type.EVENT)
    public void onFailLoadAttachAction() {
        this.mEventLogger.logEvent("LoadAttachActionFailed_Event", new LinkedHashMap());
    }

    @Analytics(name = "LoadMailMessageContentActionFailed", type = Analytics.Type.EVENT)
    public void onFailLoadMessageContentAction() {
        this.mEventLogger.logEvent("LoadMailMessageContentActionFailed_Event", new LinkedHashMap());
    }

    @Analytics(name = "SmartReplyCancel", type = Analytics.Type.ACTION)
    public void onFastReplyCancel(@Analytics.Param(name = "sendType") String str, @Analytics.Param(name = "afterAction") String str2, @Analytics.Param(name = "fromSnackbar") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sendType", String.valueOf(str));
        linkedHashMap.put("afterAction", String.valueOf(str2));
        linkedHashMap.put("fromSnackbar", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplyCancel_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyCleanInput", type = Analytics.Type.ACTION)
    public void onFastReplyCleanInput(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyCleanInput_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyCleanInputInThread", type = Analytics.Type.ACTION)
    public void onFastReplyCleanInputInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyCleanInputInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyCloseEdit", type = Analytics.Type.ACTION)
    public void onFastReplyCloseEdit(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyCloseEdit_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyCloseEditInThread", type = Analytics.Type.ACTION)
    public void onFastReplyCloseEditInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyCloseEditInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClicked", type = Analytics.Type.ACTION)
    public void onFastReplyItemClicked(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClicked_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClickedEdit", type = Analytics.Type.ACTION)
    public void onFastReplyItemClickedEdit(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClickedEdit_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClickedEditInThread", type = Analytics.Type.ACTION)
    public void onFastReplyItemClickedEditInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClickedEditInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClickedInThread", type = Analytics.Type.ACTION)
    public void onFastReplyItemClickedInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClickedInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClickedSend", type = Analytics.Type.ACTION)
    public void onFastReplyItemClickedSend(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClickedSend_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyItemClickedSendInThread", type = Analytics.Type.ACTION)
    public void onFastReplyItemClickedSendInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyItemClickedSendInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyOpenFullScreenEdit", type = Analytics.Type.ACTION)
    public void onFastReplyOpenFullScreenEdit(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyOpenFullScreenEdit_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyOpenFullScreenEditInThread", type = Analytics.Type.ACTION)
    public void onFastReplyOpenFullScreenEditInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyOpenFullScreenEditInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyScrollDetected", type = Analytics.Type.ACTION)
    public void onFastReplyScrollDetected(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyScrollDetected_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyScrollDetectedInThread", type = Analytics.Type.ACTION)
    public void onFastReplyScrollDetectedInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyScrollDetectedInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplySendClicked", type = Analytics.Type.ACTION)
    public void onFastReplySendClicked(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11, @Analytics.Param(name = "usedSmartReply") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        linkedHashMap.put("usedSmartReply", String.valueOf(z12));
        this.mEventLogger.logEvent("FastReplySendClicked_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplySendClickedInThread", type = Analytics.Type.ACTION)
    public void onFastReplySendClickedInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11, @Analytics.Param(name = "usedSmartReply") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        linkedHashMap.put("usedSmartReply", String.valueOf(z12));
        this.mEventLogger.logEvent("FastReplySendClickedInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyShown", type = Analytics.Type.VIEW)
    public void onFastReplyShown(@Analytics.Param(name = "count_visible") int i10, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("count_visible", String.valueOf(i10));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyShown_View", linkedHashMap);
    }

    @Analytics(name = "FastReplyShownInThread", type = Analytics.Type.VIEW)
    public void onFastReplyShownInThread(@Analytics.Param(name = "count_visible") int i10, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("count_visible", String.valueOf(i10));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyShownInThread_View", linkedHashMap);
    }

    @Analytics(name = "FastReplyToggleToCarousel", type = Analytics.Type.ACTION)
    public void onFastReplyToggleToCarousel(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyToggleToCarousel_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyToggleToCarouselInThread", type = Analytics.Type.ACTION)
    public void onFastReplyToggleToCarouselInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyToggleToCarouselInThread_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyToggleToEdit", type = Analytics.Type.ACTION)
    public void onFastReplyToggleToEdit(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyToggleToEdit_Action", linkedHashMap);
    }

    @Analytics(name = "FastReplyToggleToEditInThread", type = Analytics.Type.ACTION)
    public void onFastReplyToggleToEditInThread(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("FastReplyToggleToEditInThread_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Filters", params = {@Analytics.Param(name = "Action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void onFilterDeleted() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Delete");
        this.mEventLogger.logEvent("Settings_Filters_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Folders", params = {@Analytics.Param(name = "Action", value = "Add")}, type = Analytics.Type.ACTION)
    public void onFolderAdded() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Add");
        this.mEventLogger.logEvent("Settings_Folders_Action", linkedHashMap);
    }

    @Analytics(name = "FolderList", params = {@Analytics.Param(name = "Action", value = "Folder_Click")}, type = Analytics.Type.ACTION)
    public void onFolderClick(@Analytics.Param(name = "Folder_name") String str, @Analytics.Param(name = "Folder_owner") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Folder_Click");
        linkedHashMap.put("Folder_name", String.valueOf(str));
        linkedHashMap.put("Folder_owner", String.valueOf(str2));
        this.mEventLogger.logEvent("FolderList_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Folders", params = {@Analytics.Param(name = "Action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void onFolderDeleted() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Delete");
        this.mEventLogger.logEvent("Settings_Folders_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Folders", params = {@Analytics.Param(name = "Action", value = "Rename")}, type = Analytics.Type.ACTION)
    public void onFolderRenamed() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Rename");
        this.mEventLogger.logEvent("Settings_Folders_Action", linkedHashMap);
    }

    @Analytics(name = "FolderList", type = Analytics.Type.VIEW)
    public void onFoldersBecomeVisible(@Analytics.Param(name = "adEnabled") boolean z10, @Analytics.Param(name = "CurrentFolder") String str, @Analytics.Param(name = "adQty") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("adEnabled", String.valueOf(z10));
        linkedHashMap.put("CurrentFolder", String.valueOf(str));
        linkedHashMap.put("adQty", String.valueOf(i10));
        this.mEventLogger.logEvent("FolderList_View", linkedHashMap);
    }

    @Analytics(name = "SwipeTutorial", params = {@Analytics.Param(name = "Action", value = "HideNoAction")}, type = Analytics.Type.ACTION)
    public void onHideSwipeQuickActionViewWithTutorial() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "HideNoAction");
        this.mEventLogger.logEvent("SwipeTutorial_Action", linkedHashMap);
    }

    @Analytics(name = "InactivePrefClick", type = Analytics.Type.ACTION)
    public void onInactivePortalNotificationPreferenceClicked(@Analytics.Param(name = "app_id") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        this.mEventLogger.logEvent("InactivePrefClick_Action", linkedHashMap);
    }

    @Analytics(name = "InternalApiSkipped", type = Analytics.Type.ACTION)
    public void onInternalApiUrlHandled(@Analytics.Param(name = "url") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("url", String.valueOf(str));
        this.mEventLogger.logEvent("InternalApiSkipped_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplyEmpty", type = Analytics.Type.RESULT)
    public void onLoadEmptySmartReply() {
        this.mEventLogger.logEvent("SmartReplyEmpty_Result", new LinkedHashMap());
    }

    @Analytics(name = "SmartReply_ErrorResponse", type = Analytics.Type.ERROR)
    public void onLoadSmartReplyError(@Analytics.Param(name = "error") String str, @Analytics.Param(name = "status") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("error", String.valueOf(str));
        linkedHashMap.put("status", String.valueOf(i10));
        this.mEventLogger.logEvent("SmartReply_ErrorResponse_Error", linkedHashMap);
    }

    @Analytics(name = "MessageList", type = Analytics.Type.ACTION)
    public void onMailHeaderViewHolderClick(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "Folder") String str2, @Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) String str3, MailListDateInfo mailListDateInfo) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("Folder", String.valueOf(str2));
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(str3));
        linkedHashMap.put("DepartureDate", mailListDateInfo.getDepartureDate());
        linkedHashMap.put("DepartureDiffDay", mailListDateInfo.getDepartureDiffDay());
        linkedHashMap.put("DepartureDiffLetters", mailListDateInfo.getDepartureDiffLetters());
        this.mEventLogger.logEvent("MessageList_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_EmptyResult", type = Analytics.Type.EVENT)
    public void onMailSearchEmptyResult(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_EmptyResult_Event", linkedHashMap);
    }

    @Analytics(name = "SRCH_MailSearchIconClicked", type = Analytics.Type.ACTION)
    public void onMailSearchIconClicked(@Analytics.Param(name = "where") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("where", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_MailSearchIconClicked_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_MailSearchIconShown", type = Analytics.Type.EVENT)
    public void onMailSearchIconShown(@Analytics.Param(name = "where") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("where", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_MailSearchIconShown_Event", linkedHashMap);
    }

    @Analytics(name = "SRCH_Initialization", type = Analytics.Type.ERROR)
    public void onMailSearchInitializationError() {
        this.mEventLogger.logEvent("SRCH_Initialization_Error", new LinkedHashMap());
    }

    @Analytics(name = "SRCH_NotInitialized", type = Analytics.Type.ERROR)
    public void onMailSearchNotInitialized(@Analytics.Param(name = "request_duration") String str, @Analytics.Param(name = "init_duration") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("request_duration", String.valueOf(str));
        linkedHashMap.put("init_duration", String.valueOf(str2));
        this.mEventLogger.logEvent("SRCH_NotInitialized_Error", linkedHashMap);
    }

    @Analytics(name = "SRCH_Mail", type = Analytics.Type.ACTION)
    public void onMailSearchResultClicked(@Analytics.Param(name = "qid") String str, @Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        this.mEventLogger.logEvent("SRCH_Mail_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_ShowMail", type = Analytics.Type.EVENT)
    public void onMailSearchResultShown(@Analytics.Param(name = "qid") String str, @Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        this.mEventLogger.logEvent("SRCH_ShowMail_Event", linkedHashMap);
    }

    @Analytics(name = "MailViewFragment_append_scripts_content_null", type = Analytics.Type.ERROR)
    public void onMailViewAppendScriptsFinishedWithContentNull() {
        this.mEventLogger.logEvent("MailViewFragment_append_scripts_content_null_Error", new LinkedHashMap());
    }

    @Analytics(name = "MailViewFragment_not_ready", type = Analytics.Type.ERROR)
    public void onMailViewNotReadyForRendering(@Analytics.Param(name = "isNotAdded") boolean z10, @Analytics.Param(name = "webViewNull") boolean z11, @Analytics.Param(name = "contentNull") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("isNotAdded", String.valueOf(z10));
        linkedHashMap.put("webViewNull", String.valueOf(z11));
        linkedHashMap.put("contentNull", String.valueOf(z12));
        this.mEventLogger.logEvent("MailViewFragment_not_ready_Error", linkedHashMap);
    }

    @Analytics(name = "MailViewFragment_setting_content_null", type = Analytics.Type.ERROR)
    public void onMailViewSettingContentNull() {
        this.mEventLogger.logEvent("MailViewFragment_setting_content_null_Error", new LinkedHashMap());
    }

    @Analytics(name = "MenuOpen", type = Analytics.Type.ACTION)
    public void onMenuOpenedStatistics(@Analytics.Param(name = "highlighted") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("highlighted", String.valueOf(z10));
        this.mEventLogger.logEvent("MenuOpen_Action", linkedHashMap);
    }

    @Analytics(name = "Metathread_Disabled", type = Analytics.Type.ACTION)
    public void onMetaThreadDisabled(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("Metathread_Disabled_Action", linkedHashMap);
    }

    @Analytics(name = "Metathread_Enabled", type = Analytics.Type.ACTION)
    public void onMetaThreadEnabled(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("Metathread_Enabled_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Folder_Click", type = Analytics.Type.ACTION)
    public void onMetaThreadFolderSelected(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MetaThread_Folder_Click_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Folder", type = Analytics.Type.VIEW)
    public void onMetaThreadFolderShown() {
        this.mEventLogger.logEvent("MetaThread_Folder_View", new LinkedHashMap());
    }

    @Analytics(name = "MetaThread_Click", type = Analytics.Type.ACTION)
    public void onMetaThreadOpened(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MetaThread_Click_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Quick", params = {@Analytics.Param(name = "action", value = "HideNoAction")}, type = Analytics.Type.ACTION)
    public void onMetaThreadQaClosedWithoutAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "HideNoAction");
        this.mEventLogger.logEvent("MetaThread_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Quick", params = {@Analytics.Param(name = "action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void onMetaThreadQaDeleteAction(@Analytics.Param("TrashType") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "Delete");
        linkedHashMap.put("TrashType", str);
        this.mEventLogger.logEvent("MetaThread_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Quick", params = {@Analytics.Param(name = "action", value = "Mark")}, type = Analytics.Type.ACTION)
    public void onMetaThreadQaMarkAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "Mark");
        this.mEventLogger.logEvent("MetaThread_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread_Quick", params = {@Analytics.Param(name = "action", value = "Show")}, type = Analytics.Type.ACTION)
    public void onMetaThreadQaShowAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "Show");
        this.mEventLogger.logEvent("MetaThread_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "MetathreadFoldersListOnly_Disabled", type = Analytics.Type.ACTION)
    public void onMetaThreadShowInFoldersListOnlyDisabled(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MetathreadFoldersListOnly_Disabled_Action", linkedHashMap);
    }

    @Analytics(name = "MetathreadFoldersListOnly_Enabled", type = Analytics.Type.ACTION)
    public void onMetaThreadShowInFoldersListOnlyEnabled(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MetathreadFoldersListOnly_Enabled_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThread", type = Analytics.Type.VIEW)
    public void onMetaThreadShown(@Analytics.Param(name = "folder_id") long j10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("folder_id", String.valueOf(j10));
        this.mEventLogger.logEvent("MetaThread_View", linkedHashMap);
    }

    @Analytics(name = "metathread_to_myself_enabled_from_plate", type = Analytics.Type.ACTION)
    public void onMetaThreadToMyselfEnabledFromPromoPlate() {
        this.mEventLogger.logEvent("metathread_to_myself_enabled_from_plate_Action", new LinkedHashMap());
    }

    @Analytics(name = "metathreads_settings", type = Analytics.Type.VIEW)
    public void onMetaThreadsOptionShown() {
        this.mEventLogger.logEvent("metathreads_settings_View", new LinkedHashMap());
    }

    @Analytics(name = "metathreads_settings", type = Analytics.Type.ACTION)
    public void onMetaThreadsOptionStateChanged(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("metathreads_settings_Action", linkedHashMap);
    }

    @Analytics(name = "Miniapp_Clicked", type = Analytics.Type.ACTION)
    public void onMiniappClicked() {
        this.mEventLogger.logEvent("Miniapp_Clicked_Action", new LinkedHashMap());
    }

    @Analytics(name = "Miniapp_Shown", type = Analytics.Type.VIEW)
    public void onMiniappShown() {
        this.mEventLogger.logEvent("Miniapp_Shown_View", new LinkedHashMap());
    }

    @Analytics(name = "NewActionsAllContactsClicked", type = Analytics.Type.ACTION)
    public void onNewActionsAllContactsClicked(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("NewActionsAllContactsClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsContactClicked", type = Analytics.Type.ACTION)
    public void onNewActionsContactClicked(@Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i10, @Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i10));
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("NewActionsContactClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsContactsUpdated", type = Analytics.Type.EVENT)
    public void onNewActionsContactsUpdated() {
        this.mEventLogger.logEvent("NewActionsContactsUpdated_Event", new LinkedHashMap());
    }

    @Analytics(name = "NewActionsCreateCallClicked", type = Analytics.Type.ACTION)
    public void onNewActionsCreateCallClicked(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateCallClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateEmailClicked", type = Analytics.Type.ACTION)
    public void onNewActionsCreateEmailClicked(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateEmailClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateEmailShown", type = Analytics.Type.EVENT)
    public void onNewActionsCreateEmailShown(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateEmailShown_Event", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateEventClicked", type = Analytics.Type.ACTION)
    public void onNewActionsCreateEventClicked(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateEventClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateEventShown", type = Analytics.Type.EVENT)
    public void onNewActionsCreateEventShown(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateEventShown_Event", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateTaskClicked", type = Analytics.Type.ACTION)
    public void onNewActionsCreateTaskClicked(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateTaskClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsCreateTaskShown", type = Analytics.Type.EVENT)
    public void onNewActionsCreateTaskShown(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsCreateTaskShown_Event", linkedHashMap);
    }

    @Analytics(name = "NewActions", type = Analytics.Type.VIEW)
    public void onNewActionsDialogShown(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("NewActions_View", linkedHashMap);
    }

    @Analytics(name = "NewActionsDictateEmailClicked", type = Analytics.Type.ACTION)
    public void onNewActionsDictateEmailClicked(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsDictateEmailClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsDictateEmailShown", type = Analytics.Type.EVENT)
    public void onNewActionsDictateEmailShown(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsDictateEmailShown_Event", linkedHashMap);
    }

    @Analytics(name = "NewActionsEmailToMyselfClicked", type = Analytics.Type.ACTION)
    public void onNewActionsEmailToMyselfClicked(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("NewActionsEmailToMyselfClicked_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsToMySelfClickFromActs", type = Analytics.Type.ACTION)
    public void onNewActionsEmailToMyselfClickedFromActions(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsToMySelfClickFromActs_Action", linkedHashMap);
    }

    @Analytics(name = "NewActionsEmailToMyselfShown", type = Analytics.Type.EVENT)
    public void onNewActionsEmailToMyselfShown(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "orderActions") String str2, @Analytics.Param(name = "highlightedAction") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("orderActions", String.valueOf(str2));
        linkedHashMap.put("highlightedAction", String.valueOf(str3));
        this.mEventLogger.logEvent("NewActionsEmailToMyselfShown_Event", linkedHashMap);
    }

    @Analytics(name = "NewEmailClipboardSuggestApplied", type = Analytics.Type.ACTION)
    public void onNewEmailClipboardSuggestApplied(@Analytics.Param(name = "type") String str, @Analytics.Param(name = "is_link") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put("is_link", String.valueOf(z10));
        this.mEventLogger.logEvent("NewEmailClipboardSuggestApplied_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailClipboardSuggestDismissed", type = Analytics.Type.ACTION)
    public void onNewEmailClipboardSuggestDismissed(@Analytics.Param(name = "type") String str, @Analytics.Param(name = "is_link") boolean z10, @Analytics.Param(name = "automatically") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put("is_link", String.valueOf(z10));
        linkedHashMap.put("automatically", String.valueOf(z11));
        this.mEventLogger.logEvent("NewEmailClipboardSuggestDismissed_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailClipboardSuggest", type = Analytics.Type.VIEW)
    public void onNewEmailClipboardSuggestShown(@Analytics.Param(name = "type") String str, @Analytics.Param(name = "is_link") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        linkedHashMap.put("is_link", String.valueOf(z10));
        this.mEventLogger.logEvent("NewEmailClipboardSuggest_View", linkedHashMap);
    }

    @Analytics(name = "New_Metathreads_Settings", type = Analytics.Type.VIEW)
    public void onNewMetaThreadsSettingsShown(@Analytics.Param(name = "metathreads_count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("metathreads_count", String.valueOf(i10));
        this.mEventLogger.logEvent("New_Metathreads_Settings_View", linkedHashMap);
    }

    @Analytics(name = "Portal_Widget_Null_Click_Intent", type = Analytics.Type.EVENT)
    public void onNullWidgetClickIntent() {
        this.mEventLogger.logEvent("Portal_Widget_Null_Click_Intent_Event", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Null_Intent_Template", type = Analytics.Type.EVENT)
    public void onNullWidgetIntentTemplate() {
        this.mEventLogger.logEvent("Portal_Widget_Null_Intent_Template_Event", new LinkedHashMap());
    }

    @Analytics(name = "OrderShowPlate", type = Analytics.Type.VIEW)
    public void onOrderPlateShown() {
        this.mEventLogger.logEvent("OrderShowPlate_View", new LinkedHashMap());
    }

    @Analytics(name = "PhonePermissionRequest", params = {@Analytics.Param(name = "step", value = PreferenceHostProvider.URL_PARAM_FIRST)}, type = Analytics.Type.ACTION)
    public void onPhonePermissionRequest(@Analytics.Param(name = "Action") @NotNull String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", PreferenceHostProvider.URL_PARAM_FIRST);
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("PhonePermissionRequest_Action", linkedHashMap);
    }

    @Analytics(name = "PhonePermissionResult", type = Analytics.Type.ACTION)
    public void onPhonePermissionResult(@Analytics.Param(name = "Action") @NotNull String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("PhonePermissionResult_Action", linkedHashMap);
    }

    @Analytics(name = "Popular_Contacts_Showed", type = Analytics.Type.EVENT)
    public void onPopularContactsShowed(@Analytics.Param(name = "expected") int i10, @Analytics.Param(name = "actual") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("expected", String.valueOf(i10));
        linkedHashMap.put("actual", String.valueOf(i11));
        this.mEventLogger.logEvent("Popular_Contacts_Showed_Event", linkedHashMap);
    }

    @Analytics(name = "PortalChooseAppsInSettingsShown", type = Analytics.Type.EVENT)
    public void onPortalChooseAppsInSettingsShown() {
        this.mEventLogger.logEvent("PortalChooseAppsInSettingsShown_Event", new LinkedHashMap());
    }

    @Analytics(name = "PortalEnableButtonInSettingsClicked", type = Analytics.Type.ACTION)
    public void onPortalEnableButtonInSettingsClicked(@Analytics.Param(name = "activated") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("activated", String.valueOf(z10));
        this.mEventLogger.logEvent("PortalEnableButtonInSettingsClicked_Action", linkedHashMap);
    }

    @Analytics(name = "PortalEnableButtonInSettingsShown", type = Analytics.Type.EVENT)
    public void onPortalEnableButtonInSettingsShown() {
        this.mEventLogger.logEvent("PortalEnableButtonInSettingsShown_Event", new LinkedHashMap());
    }

    @Analytics(name = "PortalModeActivated", type = Analytics.Type.EVENT)
    public void onPortalModeActivated() {
        this.mEventLogger.logEvent("PortalModeActivated_Event", new LinkedHashMap());
    }

    @Analytics(name = "PortalModeLeft", type = Analytics.Type.EVENT)
    public void onPortalModeLeft() {
        this.mEventLogger.logEvent("PortalModeLeft_Event", new LinkedHashMap());
    }

    @Analytics(name = "PortalModePromoDialogShown", type = Analytics.Type.EVENT)
    public void onPortalModePromoDialogShown() {
        this.mEventLogger.logEvent("PortalModePromoDialogShown_Event", new LinkedHashMap());
    }

    @Analytics(name = "PortalNotificationPreferenceClick", type = Analytics.Type.ACTION)
    public void onPortalNotificationPreferenceClicked(@Analytics.Param(name = "app_id") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        this.mEventLogger.logEvent("PortalNotificationPreferenceClick_Action", linkedHashMap);
    }

    @Analytics(name = "PortalNotificationPreferenceDisabled", type = Analytics.Type.ACTION)
    public void onPortalNotificationPreferenceDisabled(@Analytics.Param(name = "app_id") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        this.mEventLogger.logEvent("PortalNotificationPreferenceDisabled_Action", linkedHashMap);
    }

    @Analytics(name = "PortalNotificationPreferenceEnabled", type = Analytics.Type.ACTION)
    public void onPortalNotificationPreferenceEnabled(@Analytics.Param(name = "app_id") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        this.mEventLogger.logEvent("PortalNotificationPreferenceEnabled_Action", linkedHashMap);
    }

    @Analytics(name = "PortalNotificationPreferenceShown", type = Analytics.Type.EVENT)
    public void onPortalNotificationPreferenceShown(@Analytics.Param(name = "app_id") String str, @Analytics.Param(name = "is_active") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.put("is_active", String.valueOf(z10));
        this.mEventLogger.logEvent("PortalNotificationPreferenceShown_Event", linkedHashMap);
    }

    @Analytics(name = "PortalOptionClicked", type = Analytics.Type.ACTION)
    public void onPortalOptionInAccountsMenuClicked(@Analytics.Param(name = "withPromoDialog") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("withPromoDialog", String.valueOf(z10));
        this.mEventLogger.logEvent("PortalOptionClicked_Action", linkedHashMap);
    }

    @Analytics(name = "PortalPushButtonClicked", type = Analytics.Type.ACTION)
    public void onPortalPushButtonClicked(@Analytics.Param(name = "app_id") String str, @Analytics.Param(name = "clicked_button_title") String str2, @Analytics.Param(name = "clicked_button_path") String str3, @Analytics.Param Map<String, String> map, @Analytics.Param(name = "push_campaign") String str4, @Analytics.Param(name = "email_from_push") String str5) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.put("clicked_button_title", String.valueOf(str2));
        linkedHashMap.put("clicked_button_path", String.valueOf(str3));
        linkedHashMap.putAll(map);
        linkedHashMap.put("push_campaign", String.valueOf(str4));
        linkedHashMap.put("email_from_push", String.valueOf(str5));
        this.mEventLogger.logEvent("PortalPushButtonClicked_Action", linkedHashMap);
    }

    @Analytics(name = "PortalPushClicked", type = Analytics.Type.ACTION)
    public void onPortalPushClicked(@Analytics.Param(name = "app_id") String str, @Analytics.Param(name = "path") String str2, @Analytics.Param Map<String, String> map, @Analytics.Param(name = "push_campaign") String str3, @Analytics.Param(name = "email_from_push") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.put("path", String.valueOf(str2));
        linkedHashMap.putAll(map);
        linkedHashMap.put("push_campaign", String.valueOf(str3));
        linkedHashMap.put("email_from_push", String.valueOf(str4));
        this.mEventLogger.logEvent("PortalPushClicked_Action", linkedHashMap);
    }

    @Analytics(name = "PortalPushDismissed", type = Analytics.Type.ACTION)
    public void onPortalPushDismissed(@Analytics.Param(name = "app_id") String str, @Analytics.Param(name = "path") String str2, @Analytics.Param Map<String, String> map, @Analytics.Param(name = "push_campaign") String str3, @Analytics.Param(name = "email_from_push") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.put("path", String.valueOf(str2));
        linkedHashMap.putAll(map);
        linkedHashMap.put("push_campaign", String.valueOf(str3));
        linkedHashMap.put("email_from_push", String.valueOf(str4));
        this.mEventLogger.logEvent("PortalPushDismissed_Action", linkedHashMap);
    }

    @Analytics(name = "PortalPushShowingDenied", type = Analytics.Type.EVENT)
    public void onPortalPushShowingDenied(@Analytics.Param(name = "app_id") String str, @Analytics.Param Map<String, String> map, @Analytics.Param(name = "push_campaign") String str2, @Analytics.Param(name = "email_from_push") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.putAll(map);
        linkedHashMap.put("push_campaign", String.valueOf(str2));
        linkedHashMap.put("email_from_push", String.valueOf(str3));
        this.mEventLogger.logEvent("PortalPushShowingDenied_Event", linkedHashMap);
    }

    @Analytics(name = "PortalPushShown", type = Analytics.Type.EVENT)
    public void onPortalPushShown(@Analytics.Param(name = "app_id") String str, @Analytics.Param(name = "path") String str2, @Analytics.Param Map<String, String> map, @Analytics.Param(name = "push_campaign") String str3, @Analytics.Param(name = "email_from_push") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_id", String.valueOf(str));
        linkedHashMap.put("path", String.valueOf(str2));
        linkedHashMap.putAll(map);
        linkedHashMap.put("push_campaign", String.valueOf(str3));
        linkedHashMap.put("email_from_push", String.valueOf(str4));
        this.mEventLogger.logEvent("PortalPushShown_Event", linkedHashMap);
    }

    @Analytics(name = "WebViewPortalUrlHandled", type = Analytics.Type.EVENT)
    public void onPortalUrlHandled(@Analytics.Param(name = "url") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("url", String.valueOf(str));
        this.mEventLogger.logEvent("WebViewPortalUrlHandled_Event", linkedHashMap);
    }

    @Analytics(name = "MailListRequest", type = Analytics.Type.EVENT)
    public void onPostExecuteMailListRequest() {
        this.mEventLogger.logEvent("MailListRequest_Event", new LinkedHashMap());
    }

    @Analytics(name = "PullToNavigateToApp", type = Analytics.Type.ACTION)
    public void onPullToNavigateToApp(@Analytics.Param(name = "app") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app", String.valueOf(str));
        this.mEventLogger.logEvent("PullToNavigateToApp_Action", linkedHashMap);
    }

    @Analytics(name = "Push_Register_Request", params = {@Analytics.Param(name = "cause", value = "checker")}, type = Analytics.Type.EVENT)
    public void onPushRegisteredAfterTokenCheck() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("cause", "checker");
        this.mEventLogger.logEvent("Push_Register_Request_Event", linkedHashMap);
    }

    @Analytics(name = "Push_Register_Request", type = Analytics.Type.EVENT)
    public void onPushRegisteredCheckComplete(@Analytics.Param(name = "Result") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", String.valueOf(str));
        this.mEventLogger.logEvent("Push_Register_Request_Event", linkedHashMap);
    }

    @Analytics(name = "Quick_Action_Option_Selected", type = Analytics.Type.ACTION)
    public void onQuickActionOptionSelected(@Analytics.Param(name = "option") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("option", String.valueOf(str));
        this.mEventLogger.logEvent("Quick_Action_Option_Selected_Action", linkedHashMap);
    }

    @Analytics(name = "Quick_Action_Dialog_Closed", type = Analytics.Type.ACTION)
    public void onQuickActionOptionsDialogClosed() {
        this.mEventLogger.logEvent("Quick_Action_Dialog_Closed_Action", new LinkedHashMap());
    }

    @Analytics(name = "Quick_Action_Dialog_Opened", type = Analytics.Type.ACTION)
    public void onQuickActionOptionsDialogOpened() {
        this.mEventLogger.logEvent("Quick_Action_Dialog_Opened_Action", new LinkedHashMap());
    }

    @Analytics(name = "SwipeTutorial", type = Analytics.Type.VIEW)
    public void onQuickActionsTutorialShown() {
        this.mEventLogger.logEvent("SwipeTutorial_View", new LinkedHashMap());
    }

    @Analytics(name = "Reattach_Request_Fail", type = Analytics.Type.ERROR)
    public void onReattachError(@Analytics.Param(name = "reason") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("reason", String.valueOf(str));
        this.mEventLogger.logEvent("Reattach_Request_Fail_Error", linkedHashMap);
    }

    @Analytics(name = "Register_With_VKC", type = Analytics.Type.EVENT)
    public void onRegisterWithVKC() {
        this.mEventLogger.logEvent("Register_With_VKC_Event", new LinkedHashMap());
    }

    @Analytics(name = "Push_Register_Request", type = Analytics.Type.EVENT)
    public void onRegisteredAfterAppUpgrade(@Analytics.Param(name = "cause") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("cause", String.valueOf(str));
        this.mEventLogger.logEvent("Push_Register_Request_Event", linkedHashMap);
    }

    @Analytics(name = "SRCH_Filter_All", type = Analytics.Type.ACTION)
    public void onSearchFilterAll(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_Filter_All_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_Filter_From", type = Analytics.Type.ACTION)
    public void onSearchFilterFrom(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_Filter_From_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_Filter_More", type = Analytics.Type.ACTION)
    public void onSearchFilterMore(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_Filter_More_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_Filter_Subject", type = Analytics.Type.ACTION)
    public void onSearchFilterSubject(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_Filter_Subject_Action", linkedHashMap);
    }

    @Analytics(name = "SRCH_Filter_To", type = Analytics.Type.ACTION)
    public void onSearchFilterTo(@Analytics.Param(name = "qid") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        this.mEventLogger.logEvent("SRCH_Filter_To_Action", linkedHashMap);
    }

    @Analytics(name = "OpenSettings", type = Analytics.Type.ACTION)
    public void onSettingsClicked(@Analytics.Param(name = "highlighted") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("highlighted", String.valueOf(str));
        this.mEventLogger.logEvent("OpenSettings_Action", linkedHashMap);
    }

    @Analytics(name = "Social_Links", params = {@Analytics.Param(name = "Type", value = "Share")}, type = Analytics.Type.VIEW)
    public void onShareAppsClick() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Share");
        this.mEventLogger.logEvent("Social_Links_View", linkedHashMap);
    }

    @Analytics(name = "OrderShowDetails", type = Analytics.Type.ACTION)
    public void onShowOrderDetails(@Analytics.Param(name = "onlyShopInfo") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("onlyShopInfo", String.valueOf(z10));
        this.mEventLogger.logEvent("OrderShowDetails_Action", linkedHashMap);
    }

    @Analytics(name = "OrderShowItemDetails", type = Analytics.Type.ACTION)
    public void onShowOrderItemDetails(@Analytics.Param(name = "hasDetails") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("hasDetails", String.valueOf(z10));
        this.mEventLogger.logEvent("OrderShowItemDetails_Action", linkedHashMap);
    }

    @Analytics(name = "PhonePermissionRequest", type = Analytics.Type.VIEW)
    public void onShowPhonePermissionRequestView() {
        this.mEventLogger.logEvent("PhonePermissionRequest_View", new LinkedHashMap());
    }

    @Analytics(name = "SwipeTutorial", params = {@Analytics.Param(name = "Action", value = "Show")}, type = Analytics.Type.ACTION)
    public void onShowSwipeQuickActionViewWithTutorial() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Show");
        this.mEventLogger.logEvent("SwipeTutorial_Action", linkedHashMap);
    }

    @Analytics(name = "Signup_Prepare", type = Analytics.Type.ERROR)
    public void onSignupPrepareError() {
        this.mEventLogger.logEvent("Signup_Prepare_Error", new LinkedHashMap());
    }

    @Analytics(name = "VkSocialAuthenticationSkipped", type = Analytics.Type.EVENT)
    public void onSkipRegistrationWithVkc() {
        this.mEventLogger.logEvent("VkSocialAuthenticationSkipped_Event", new LinkedHashMap());
    }

    @Analytics(name = "SRCH_ShowQuery", type = Analytics.Type.EVENT)
    public void onStartMailSearch(@Analytics.Param(name = "qid") String str, @Analytics.Param(name = "text") String str2, @Analytics.Param(name = AnimationDirectionValues.VERTICAL) String str3, @Analytics.Param(name = "submit_type") String str4, @Analytics.Param(name = "category") String str5, @Analytics.Param(name = "new_search") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("qid", String.valueOf(str));
        linkedHashMap.put("text", String.valueOf(str2));
        linkedHashMap.put(AnimationDirectionValues.VERTICAL, String.valueOf(str3));
        linkedHashMap.put("submit_type", String.valueOf(str4));
        linkedHashMap.put("new_search", String.valueOf(z10));
        linkedHashMap.put("category", String.valueOf(str5));
        this.mEventLogger.logEvent("SRCH_ShowQuery_Event", linkedHashMap);
    }

    @Analytics(name = "DeleteAccount", params = {@Analytics.Param(name = "Action", value = "ContactSupport")}, type = Analytics.Type.ACTION)
    public void onSupportButtonClick() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ContactSupport");
        this.mEventLogger.logEvent("DeleteAccount_Action", linkedHashMap);
    }

    @Analytics(name = "UserThemeSelected", type = Analytics.Type.EVENT)
    public void onThemeSelected(@Analytics.Param(name = "theme_name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("theme_name", String.valueOf(str));
        this.mEventLogger.logEvent("UserThemeSelected_Event", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_Archive", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadArchiveSelectedItems(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("ToMyselfMetaThread_Archive_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_Filter_Applied", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadFilterApplied(@Analytics.Param(name = "Filter") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Filter", String.valueOf(str));
        this.mEventLogger.logEvent("ToMyselfMetaThread_Filter_Applied_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_FlagMails", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadFlagMails(@Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("ToMyselfMetaThread_FlagMails_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_MarkMailsRead", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMarkMailsRead(@Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("ToMyselfMetaThread_MarkMailsRead_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_MarkMailsUnread", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMarkMailsUnread(@Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("ToMyselfMetaThread_MarkMailsUnread_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_MarkNotSpam", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMarkNoSpamSelectedItems(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("ToMyselfMetaThread_MarkNotSpam_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_MarkSpam", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMarkSpamSelectedItems(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("ToMyselfMetaThread_MarkSpam_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_Move", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMoveSelectedItems(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        this.mEventLogger.logEvent("ToMyselfMetaThread_Move_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_MoveToBin", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadMoveToBinSelectedItems(@Analytics.Param(name = "Count") int i10, @Analytics.Param(name = "CountBucket") String str, @Analytics.Param(name = "TrashType") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        linkedHashMap.put("CountBucket", String.valueOf(str));
        linkedHashMap.put("TrashType", str2);
        this.mEventLogger.logEvent("ToMyselfMetaThread_MoveToBin_Action", linkedHashMap);
    }

    @Analytics(name = "metathread_to_myself_setting_saved", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadSavingPreferenceSuccess(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("metathread_to_myself_setting_saved_Action", linkedHashMap);
    }

    @Analytics(name = "ToMyselfMetaThread_UnflagMails", type = Analytics.Type.ACTION)
    public void onToMyselfMetaThreadUnflagMails(@Analytics.Param(name = "Count") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Count", String.valueOf(i10));
        this.mEventLogger.logEvent("ToMyselfMetaThread_UnflagMails_Action", linkedHashMap);
    }

    @Analytics(name = "metathread_to_myself_settings", type = Analytics.Type.ACTION)
    public void onToMyselfOptionStateChanged(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("metathread_to_myself_settings_Action", linkedHashMap);
    }

    @Analytics(name = "TrustedURLClick", type = Analytics.Type.ACTION)
    public void onTrustedUrlClicked(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("TrustedURLClick_Action", linkedHashMap);
    }

    @Analytics(name = "WalletPushDismissed", type = Analytics.Type.ACTION)
    public void onWalletPushDismissed(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "ml_type") String str2, @Analytics.Param(name = "ml_subtype") String str3, @Analytics.Param(name = "ml_reminder") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("ml_type", String.valueOf(str2));
        linkedHashMap.put("ml_subtype", String.valueOf(str3));
        linkedHashMap.put("ml_reminder", String.valueOf(str4));
        this.mEventLogger.logEvent("WalletPushDismissed_Action", linkedHashMap);
    }

    @Analytics(name = "WalletPushShowingDenied", type = Analytics.Type.EVENT)
    public void onWalletPushShowingDenied(@Analytics.Param(name = "ml_type") String str, @Analytics.Param(name = "ml_subtype") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ml_type", String.valueOf(str));
        linkedHashMap.put("ml_subtype", String.valueOf(str2));
        this.mEventLogger.logEvent("WalletPushShowingDenied_Event", linkedHashMap);
    }

    @Analytics(name = "OneTimeCode", type = Analytics.Type.ACTION)
    public void oneTimeCode(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "source") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("source", String.valueOf(str2));
        this.mEventLogger.logEvent("OneTimeCode_Action", linkedHashMap);
    }

    @Analytics(name = "OneTimeCode", type = Analytics.Type.ACTION)
    public void oneTimeCodeAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("OneTimeCode_Action", linkedHashMap);
    }

    @Analytics(name = "OneTimeCode", params = {@Analytics.Param(name = "action", value = "click")}, type = Analytics.Type.ACTION)
    public void oneTimeCodeActionClick() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "click");
        this.mEventLogger.logEvent("OneTimeCode_Action", linkedHashMap);
    }

    @Analytics(name = "OneTimeCode", type = Analytics.Type.VIEW)
    protected void oneTimeCodeView() {
        this.mEventLogger.logEvent("OneTimeCode_View", new LinkedHashMap());
    }

    @Analytics(name = "OpenSettings", type = Analytics.Type.ACTION)
    public void openSettingsAction(@Analytics.Param(name = "highlighted") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("highlighted", String.valueOf(z10));
        this.mEventLogger.logEvent("OpenSettings_Action", linkedHashMap);
    }

    @Analytics(type = Analytics.Type.ACTION)
    public void operationConfirmDialogCancel(@Analytics.Name String str) {
        this.mEventLogger.logEvent(str + "_Action", new LinkedHashMap());
    }

    @Analytics(type = Analytics.Type.ACTION)
    public void operationConfirmDialogConfirm(@Analytics.Name String str) {
        this.mEventLogger.logEvent(str + "_Action", new LinkedHashMap());
    }

    @Analytics(type = Analytics.Type.VIEW)
    public void operationConfirmDialogShow(@Analytics.Name String str) {
        this.mEventLogger.logEvent(str + "_View", new LinkedHashMap());
    }

    @Analytics(type = Analytics.Type.ACTION)
    public void operationConfirmDialogToggleCheckBox(@Analytics.Name String str) {
        this.mEventLogger.logEvent(str + "_Action", new LinkedHashMap());
    }

    @Analytics(name = "OutdateSendingConfirmation", type = Analytics.Type.ACTION)
    public void outdateSendingConfirmationAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("OutdateSendingConfirmation_Action", linkedHashMap);
    }

    @Analytics(name = "OutdateSendingConfirmation", type = Analytics.Type.VIEW)
    public void outdateSendingConfirmationView() {
        this.mEventLogger.logEvent("OutdateSendingConfirmation_View", new LinkedHashMap());
    }

    @Analytics(name = "PartnerBuild", type = Analytics.Type.EVENT)
    public void partnerBuildEvent(@Analytics.Param(name = "Current") String str, @Analytics.Param(name = "First") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Current", String.valueOf(str));
        linkedHashMap.put("First", String.valueOf(str2));
        this.mEventLogger.logEvent("PartnerBuild_Event", linkedHashMap);
    }

    @Analytics(name = "PassAuth", type = Analytics.Type.VIEW)
    public void passAuthView(@Analytics.Param(name = "serviceType") String str, @Analytics.Param(name = "step") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("serviceType", String.valueOf(str));
        linkedHashMap.put("step", String.valueOf(str2));
        this.mEventLogger.logEvent("PassAuth_View", linkedHashMap);
    }

    @Analytics(name = "PermissionReqInstr", type = Analytics.Type.ACTION)
    public void permissionReqInstrAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("PermissionReqInstr_Action", linkedHashMap);
    }

    @Analytics(name = "PermissionsRequired", params = {@Analytics.Param(name = "Action", value = "Enable")}, type = Analytics.Type.ACTION)
    public void permissionsRequiredActionEnabled() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Enable");
        this.mEventLogger.logEvent("PermissionsRequired_Action", linkedHashMap);
    }

    @Analytics(name = "PermissionsRequired", type = Analytics.Type.VIEW)
    public void permissionsRequiredView() {
        this.mEventLogger.logEvent("PermissionsRequired_View", new LinkedHashMap());
    }

    @Analytics(name = "PhoneNumber", type = Analytics.Type.ACTION)
    public void phoneNumberAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("PhoneNumber_Action", linkedHashMap);
    }

    @Analytics(name = "PhoneNumber", type = Analytics.Type.ERROR)
    public void phoneNumberError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("PhoneNumber_Error", linkedHashMap);
    }

    @Analytics(name = "PhoneNumber", type = Analytics.Type.VIEW)
    public void phoneNumberView() {
        this.mEventLogger.logEvent("PhoneNumber_View", new LinkedHashMap());
    }

    @Analytics(name = "PinEnter", params = {@Analytics.Param(name = "Result", value = "Failed")}, type = Analytics.Type.ACTION)
    public void pinEnterResultFailed() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Failed");
        this.mEventLogger.logEvent("PinEnter_Action", linkedHashMap);
    }

    @Analytics(name = "PinEnter", params = {@Analytics.Param(name = "Result", value = "Success")}, type = Analytics.Type.ACTION)
    public void pinEnterResultSuccess() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Success");
        this.mEventLogger.logEvent("PinEnter_Action", linkedHashMap);
    }

    @Analytics(name = "Portal_Widget_Account", type = Analytics.Type.ACTION)
    public void portalWidgetAccountClicked(@Analytics.Param(name = "unreadCnt") int i10, @Analytics.Param(name = NewsAnalyticsHandler.PARAM_POSITION) int i11, @Analytics.Param(name = "qty") int i12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("unreadCnt", String.valueOf(i10));
        linkedHashMap.put(NewsAnalyticsHandler.PARAM_POSITION, String.valueOf(i11));
        linkedHashMap.put("qty", String.valueOf(i12));
        this.mEventLogger.logEvent("Portal_Widget_Account_Action", linkedHashMap);
    }

    @Analytics(name = "Portal_Widget_Mail_To_Myself", type = Analytics.Type.ACTION)
    public void portalWidgetMailToMyselfClicked() {
        this.mEventLogger.logEvent("Portal_Widget_Mail_To_Myself_Action", new LinkedHashMap());
    }

    @Analytics(name = "Widget_Data_Request", params = {@Analytics.Param(name = "status", value = "Error")}, type = Analytics.Type.RESULT)
    public void portalWidgetNetworkRequestError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("status", "Error");
        this.mEventLogger.logEvent("Widget_Data_Request_Result", linkedHashMap);
    }

    @Analytics(name = "Widget_Data_Request", params = {@Analytics.Param(name = "status", value = "Ok")}, type = Analytics.Type.RESULT)
    public void portalWidgetNetworkRequestSuccess() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("status", "Ok");
        this.mEventLogger.logEvent("Widget_Data_Request_Result", linkedHashMap);
    }

    @Analytics(name = "Portal_Widget_New_Msg", type = Analytics.Type.ACTION)
    public void portalWidgetNewEmailClicked() {
        this.mEventLogger.logEvent("Portal_Widget_New_Msg_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Open_Currencies", type = Analytics.Type.ACTION)
    public void portalWidgetOpenCurrencies() {
        this.mEventLogger.logEvent("Portal_Widget_Open_Currencies_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Search", type = Analytics.Type.ACTION)
    public void portalWidgetOpenSearch() {
        this.mEventLogger.logEvent("Portal_Widget_Search_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Open_Weather", type = Analytics.Type.ACTION)
    public void portalWidgetOpenWeather() {
        this.mEventLogger.logEvent("Portal_Widget_Open_Weather_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Uninstalled", type = Analytics.Type.EVENT)
    public void portalWidgetRemoved() {
        this.mEventLogger.logEvent("Portal_Widget_Uninstalled_Event", new LinkedHashMap());
    }

    @Analytics(name = "Widget_Data_Reload", type = Analytics.Type.ACTION)
    public void portalWidgetRetryButtonClicked() {
        this.mEventLogger.logEvent("Widget_Data_Reload_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Login", type = Analytics.Type.ACTION)
    public void portalWidgetSignInClicked() {
        this.mEventLogger.logEvent("Portal_Widget_Login_Action", new LinkedHashMap());
    }

    @Analytics(name = "Portal_Widget_Update", type = Analytics.Type.EVENT)
    public void portalWidgetUpdate(@Analytics.Param(name = "installed_count") int i10, @Analytics.Param(name = "updated_count") int i11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("installed_count", String.valueOf(i10));
        linkedHashMap.put("updated_count", String.valueOf(i11));
        this.mEventLogger.logEvent("Portal_Widget_Update_Event", linkedHashMap);
    }

    @Analytics(name = "PrefetchBodySmartReply", type = Analytics.Type.EVENT)
    public void prefetchBodySmartReplyEvent(@Analytics.Param(name = "hasSmartReply") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("hasSmartReply", String.valueOf(z10));
        this.mEventLogger.logEvent("PrefetchBodySmartReply_Event", linkedHashMap);
    }

    @Analytics(name = "RegistrationMycom", type = Analytics.Type.ERROR)
    public void processError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("RegistrationMycom_Error", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "LocalChecksPassed")}, type = Analytics.Type.VIEW)
    public void processNextButtonClicked() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "LocalChecksPassed");
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Profile_Share", params = {@Analytics.Param(name = "result", value = "Error_Internal")}, type = Analytics.Type.EVENT)
    public void profileShareErrorInternal(@Analytics.Param(name = "package") String str, @Analytics.Param(name = "error_msg") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", "Error_Internal");
        linkedHashMap.put("package", String.valueOf(str));
        linkedHashMap.put("error_msg", String.valueOf(str2));
        this.mEventLogger.logEvent("Profile_Share_Event", linkedHashMap);
    }

    @Analytics(name = "Profile_Share", params = {@Analytics.Param(name = "result", value = "Error_Invalid_Certificate")}, type = Analytics.Type.EVENT)
    public void profileShareErrorInvalidCertificate(@Analytics.Param(name = "package") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", "Error_Invalid_Certificate");
        linkedHashMap.put("package", String.valueOf(str));
        this.mEventLogger.logEvent("Profile_Share_Event", linkedHashMap);
    }

    @Analytics(name = "Profile_Share", params = {@Analytics.Param(name = "result", value = "Error_Unknown_Package")}, type = Analytics.Type.EVENT)
    public void profileShareErrorUnknownPackage(@Analytics.Param(name = "package") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", "Error_Unknown_Package");
        linkedHashMap.put("package", String.valueOf(str));
        this.mEventLogger.logEvent("Profile_Share_Event", linkedHashMap);
    }

    @Analytics(name = "Profile_Share", params = {@Analytics.Param(name = "result", value = "No_Profiles")}, type = Analytics.Type.EVENT)
    public void profileShareNoProfiles(@Analytics.Param(name = "package") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", "No_Profiles");
        linkedHashMap.put("package", String.valueOf(str));
        this.mEventLogger.logEvent("Profile_Share_Event", linkedHashMap);
    }

    @Analytics(name = "Profile_Share", params = {@Analytics.Param(name = "result", value = "OK")}, type = Analytics.Type.EVENT)
    public void profileShareOk(@Analytics.Param(name = "package") String str, @Analytics.Param(name = "profiles") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", "OK");
        linkedHashMap.put("package", String.valueOf(str));
        linkedHashMap.put("profiles", String.valueOf(i10));
        this.mEventLogger.logEvent("Profile_Share_Event", linkedHashMap);
    }

    @Analytics(name = "Push", type = Analytics.Type.ACTION)
    public void pushAction(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "hasSmartReplies") String str2, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "pushType") String str3, @Analytics.Param(name = "category") String str4, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("hasSmartReplies", String.valueOf(str2));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("pushType", String.valueOf(str3));
        linkedHashMap.put("category", String.valueOf(str4));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("Push_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginChooseAccountClosed", type = Analytics.Type.ACTION)
    public void qrWebLoginChooseAccountClosed(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginChooseAccountClosed_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginChooseAccountSelected", type = Analytics.Type.ACTION)
    public void qrWebLoginChooseAccountSelected(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginChooseAccountSelected_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginChooseAccount", type = Analytics.Type.VIEW)
    public void qrWebLoginChooseAccountShown(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginChooseAccount_View", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginConfirm", params = {@Analytics.Param(name = "action", value = "cancel")}, type = Analytics.Type.ACTION)
    public void qrWebLoginConfirmCancel(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "cancel");
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginConfirm_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginConfirm", params = {@Analytics.Param(name = "action", value = "close")}, type = Analytics.Type.ACTION)
    public void qrWebLoginConfirmClosed(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "close");
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginConfirm_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginConfirm", params = {@Analytics.Param(name = "action", value = "ok")}, type = Analytics.Type.ACTION)
    public void qrWebLoginConfirmOk(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "ok");
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginConfirm_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginConfirm", type = Analytics.Type.VIEW)
    public void qrWebLoginConfirmShown(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginConfirm_View", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginRequest", params = {@Analytics.Param(name = "error", value = "expired")}, type = Analytics.Type.ERROR)
    public void qrWebLoginExpiredTokenError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("error", "expired");
        this.mEventLogger.logEvent("QrWebLoginRequest_Error", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginFeatureDisabled", type = Analytics.Type.EVENT)
    public void qrWebLoginFeatureDisabled() {
        this.mEventLogger.logEvent("QrWebLoginFeatureDisabled_Event", new LinkedHashMap());
    }

    @Analytics(name = "QrWebLoginNoAccountsPopup", params = {@Analytics.Param(name = "action", value = "learnmore")}, type = Analytics.Type.ACTION)
    public void qrWebLoginNoAccountsPopupLearnMoreClicked() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", "learnmore");
        this.mEventLogger.logEvent("QrWebLoginNoAccountsPopup_Action", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginNoAccountsPopup", type = Analytics.Type.VIEW)
    public void qrWebLoginNoAccountsPopupShown() {
        this.mEventLogger.logEvent("QrWebLoginNoAccountsPopup_View", new LinkedHashMap());
    }

    @Analytics(name = "QrWebLoginRequest", type = Analytics.Type.ERROR)
    public void qrWebLoginRequestError(@Analytics.Param(name = "error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("error", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginRequest_Error", linkedHashMap);
    }

    @Analytics(name = "QrWebLoginSuccess", type = Analytics.Type.EVENT)
    public void qrWebLoginSuccess(@Analytics.Param(name = "step") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("step", String.valueOf(str));
        this.mEventLogger.logEvent("QrWebLoginSuccess_Event", linkedHashMap);
    }

    @Analytics(name = "RateApp", type = Analytics.Type.RESULT)
    public void rateAppResult(@Analytics.Param(name = "Result") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", String.valueOf(str));
        this.mEventLogger.logEvent("RateApp_Result", linkedHashMap);
    }

    @Analytics(name = "RateApp", type = Analytics.Type.VIEW)
    public void rateAppView() {
        this.mEventLogger.logEvent("RateApp_View", new LinkedHashMap());
    }

    @Analytics(name = "RefreshAccessTokenFailed", type = Analytics.Type.EVENT)
    public void refreshAccessTokenFailedEvent(@Analytics.Param(name = "will_refresh_token") boolean z10, @Analytics.Param(name = "status") String str, @Analytics.Param(name = VkUiActivityResultDelegate.KEY_REQUEST_ID) String str2, @Analytics.Param(name = "error_msg") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("will_refresh_token", String.valueOf(z10));
        linkedHashMap.put("status", String.valueOf(str));
        linkedHashMap.put(VkUiActivityResultDelegate.KEY_REQUEST_ID, String.valueOf(str2));
        linkedHashMap.put("error_msg", String.valueOf(str3));
        this.mEventLogger.logEvent("RefreshAccessTokenFailed_Event", linkedHashMap);
    }

    @Analytics(name = "RefreshToken", type = Analytics.Type.ERROR)
    public void refreshTokenError(@Analytics.Param(name = "error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("error", String.valueOf(str));
        this.mEventLogger.logEvent("RefreshToken_Error", linkedHashMap);
    }

    @Analytics(name = "RefreshToken", type = Analytics.Type.EVENT)
    public void refreshTokenEvent(@Analytics.Param(name = ApiUris.AUTHORITY_API) String str, @Analytics.Param(name = "result") String str2, @Analytics.Param(name = "account_type") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(ApiUris.AUTHORITY_API, String.valueOf(str));
        linkedHashMap.put("result", String.valueOf(str2));
        linkedHashMap.put("account_type", String.valueOf(str3));
        this.mEventLogger.logEvent("RefreshToken_Event", linkedHashMap);
    }

    @Analytics(name = "Social_Links", params = {@Analytics.Param(name = "Type", value = "Email")}, type = Analytics.Type.VIEW)
    public void regShareEmailChooserShown() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Email");
        this.mEventLogger.logEvent("Social_Links_View", linkedHashMap);
    }

    @Analytics(name = "Social_Links", params = {@Analytics.Param(name = "Type", value = "Sms")}, type = Analytics.Type.VIEW)
    public void regShareSmsChooserShown() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Sms");
        this.mEventLogger.logEvent("Social_Links_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "RegistrationNextClick"), @Analytics.Param(name = "Place", value = "Registration")}, type = Analytics.Type.ACTION)
    protected void registrationActionRegistrationNextClick(@Analytics.Param(name = "HasWeakPassword") boolean z10, @Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "RegistrationNextClick");
        linkedHashMap.put("Place", "Registration");
        linkedHashMap.put("HasWeakPassword", String.valueOf(z10));
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "UserAgreement")}, type = Analytics.Type.ACTION)
    public void registrationActionUserAgreement(@Analytics.Param(name = "State") boolean z10, @Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "UserAgreement");
        linkedHashMap.put("State", String.valueOf(z10));
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Action", value = "CaptchaDoneClick"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.ACTION)
    public void registrationCaptchaDoneClick() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CaptchaDoneClick");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Action", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Action", value = "LoadCaptcha"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.ACTION)
    public void registrationCaptchaLoadCaptcha() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "LoadCaptcha");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Action", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Result", value = "LoadCaptchaError"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.RESULT)
    public void registrationCaptchaLoadCaptchaError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "LoadCaptchaError");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Result", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Result", value = "LoadCaptchaSuccess"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.RESULT)
    public void registrationCaptchaLoadCaptchaSuccess() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "LoadCaptchaSuccess");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Result", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Result", value = "RegistrationCaptchaError"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.RESULT)
    public void registrationCaptchaRegistrationCaptchaError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "RegistrationCaptchaError");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Result", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Result", value = "RegistrationError"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.RESULT)
    public void registrationCaptchaRegistrationError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "RegistrationError");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Result", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Result", value = "RegistrationSuccess"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.RESULT)
    public void registrationCaptchaRegistrationSuccess() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "RegistrationSuccess");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Result", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "Action", value = "SwitchToSmsCode"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.ACTION)
    public void registrationCaptchaSwitchToSmsCode() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SwitchToSmsCode");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        this.mEventLogger.logEvent("RegistrationCaptcha_Action", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "type", value = "base"), @Analytics.Param(name = "Place", value = "RegistrationCaptcha")}, type = Analytics.Type.VIEW)
    public void registrationCaptchaView(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", "base");
        linkedHashMap.put("Place", "RegistrationCaptcha");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("RegistrationCaptcha_View", linkedHashMap);
    }

    @Analytics(name = "RegistrationCaptcha", params = {@Analytics.Param(name = "type", value = "recaptcha")}, type = Analytics.Type.VIEW)
    public void registrationCaptchaViewTypeRecapthca(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", "recaptcha");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("RegistrationCaptcha_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "CheckCaptcha"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationCheckCaptcha(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CheckCaptcha");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "CheckCode"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationCheckCode(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CheckCode");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Error", value = "password_remote_validator_failed"), @Analytics.Param(name = "Place", value = "Registration")}, type = Analytics.Type.ERROR)
    public void registrationError(@Analytics.Param(name = "Error") String str, @Analytics.Param(name = "Place") String str2, @Analytics.Param(name = "RegForm") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", "password_remote_validator_failed");
        linkedHashMap.put("Place", "Registration");
        linkedHashMap.put("Error", String.valueOf(str));
        linkedHashMap.put("Place", String.valueOf(str2));
        linkedHashMap.put("RegForm", String.valueOf(str3));
        this.mEventLogger.logEvent("Registration_Error", linkedHashMap);
    }

    @Analytics(name = "RegistrationInternetRu", type = Analytics.Type.EVENT)
    public void registrationInternetRu() {
        this.mEventLogger.logEvent("RegistrationInternetRu_Event", new LinkedHashMap());
    }

    @Analytics(name = "RegistrationInternetRuFromDeeplink", type = Analytics.Type.EVENT)
    public void registrationInternetRuFromDeeplink() {
        this.mEventLogger.logEvent("RegistrationInternetRuFromDeeplink_Event", new LinkedHashMap());
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "LoadCaptcha"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationLoadCaptcha(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "LoadCaptcha");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "RegistrationMycom", type = Analytics.Type.VIEW)
    public void registrationMycom() {
        this.mEventLogger.logEvent("RegistrationMycom_View", new LinkedHashMap());
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "OpenConfirmation"), @Analytics.Param(name = "Place", value = "ConfirmationRegistration")}, type = Analytics.Type.VIEW)
    public void registrationOpenConfirmation(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "OpenConfirmation");
        linkedHashMap.put("Place", "ConfirmationRegistration");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "Callin"), @Analytics.Param(name = "Place", value = "ConfirmationRegistration")}, type = Analytics.Type.VIEW)
    public void registrationOpenConfirmationCallIn() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CallIn");
        linkedHashMap.put("Place", "ConfirmationRegistration");
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "CallUI"), @Analytics.Param(name = "Place", value = "ConfirmationRegistration")}, type = Analytics.Type.VIEW)
    public void registrationOpenConfirmationCallUI() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "CallUI");
        linkedHashMap.put("Place", "ConfirmationRegistration");
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "Callin"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationReqestCallIn(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Callin");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "RequestCode"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationRequestCode(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "RequestCode");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Result", value = "RegistrationError"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.RESULT)
    public void registrationResultError(@Analytics.Param(name = "RegForm") String str, @Analytics.Param(name = "RegEmail") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "RegistrationError");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Result", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Result", value = "LoadCaptchaError"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.RESULT)
    public void registrationResultLoadCaptchaError(@Analytics.Param(name = "RegForm") String str, @Analytics.Param(name = "RegEmail") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "LoadCaptchaError");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Result", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Result", value = "LoadCaptchaSuccess"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.RESULT)
    public void registrationResultLoadCaptchaSuccess(@Analytics.Param(name = "RegForm") String str, @Analytics.Param(name = "RegEmail") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "LoadCaptchaSuccess");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        linkedHashMap.put("RegEmail", String.valueOf(str2));
        this.mEventLogger.logEvent("Registration_Result", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Result", value = "Success")}, type = Analytics.Type.RESULT)
    public void registrationResultSuccess(@Analytics.Param(name = "RestoreType") String str, @Analytics.Param(name = "IsRestore") String str2, @Analytics.Param(name = "HasActiveAcc") String str3, @Analytics.Param(name = "from") String str4, @Analytics.Param(name = "RegForm") String str5, @Analytics.Param(name = "RegEmail") String str6, @Analytics.Param(name = "is_migrated") boolean z10, @Analytics.Param(name = "migration_from") String str7, String str8) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "Success");
        linkedHashMap.put("RestoreType", String.valueOf(str));
        linkedHashMap.put("IsRestore", String.valueOf(str2));
        linkedHashMap.put("HasActiveAcc", String.valueOf(str3));
        linkedHashMap.put("from", String.valueOf(str4));
        linkedHashMap.put("BindType", str8);
        linkedHashMap.put("RegForm", String.valueOf(str5));
        linkedHashMap.put("RegEmail", String.valueOf(str6));
        linkedHashMap.put("is_migrated", String.valueOf(z10));
        linkedHashMap.put("migration_from", String.valueOf(str7));
        this.mEventLogger.logEvent("Registration_Result", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "SignUp"), @Analytics.Param(name = "Place", value = "Registration")}, type = Analytics.Type.ACTION)
    public void registrationSignUp(@Analytics.Param(name = "LocalErrorsCount") int i10, @Analytics.Param(name = "ServerErrorsCount") int i11, @Analytics.Param(name = "NextButtonClicksCount") int i12, @Analytics.Param(name = "HasExistingEmailErrors") boolean z10, @Analytics.Param(name = "HasNameErrors") boolean z11, @Analytics.Param(name = "HasSecondNameErrors") boolean z12, @Analytics.Param(name = "HasGenderErrors") boolean z13, @Analytics.Param(name = "SuggestedEmailClicked") boolean z14, @Analytics.Param(name = "SuggestedEmailUsed") boolean z15, @Analytics.Param(name = "BirthDayWasChosen") boolean z16, @Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SignUp");
        linkedHashMap.put("Place", "Registration");
        linkedHashMap.put("LocalErrorsCount", String.valueOf(i10));
        linkedHashMap.put("ServerErrorsCount", String.valueOf(i11));
        linkedHashMap.put("NextButtonClicksCount", String.valueOf(i12));
        linkedHashMap.put("HasExistingEmailErrors", String.valueOf(z10));
        linkedHashMap.put("HasNameErrors", String.valueOf(z11));
        linkedHashMap.put("HasSecondNameErrors", String.valueOf(z12));
        linkedHashMap.put("HasGenderErrors", String.valueOf(z13));
        linkedHashMap.put("SuggestedEmailClicked", String.valueOf(z14));
        linkedHashMap.put("SuggestedEmailUsed", String.valueOf(z15));
        linkedHashMap.put("BirthDayWasChosen", String.valueOf(z16));
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "SwitchToQuestion"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationSwitchToQuestion(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SwitchToQuestion");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "SwitchToSms"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.ACTION)
    public void registrationSwitchToSms(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SwitchToSms");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.VIEW)
    public void registrationView(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "Leave")}, type = Analytics.Type.VIEW)
    public void registrationViewActionLeave() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Leave");
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "Open")}, type = Analytics.Type.VIEW)
    public void registrationViewActionOpen(@Analytics.Param(name = "From") String str, @Analytics.Param(name = "RegForm") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Open");
        linkedHashMap.put("From", String.valueOf(str));
        linkedHashMap.put("RegForm", String.valueOf(str2));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "LocalChecksPassed"), @Analytics.Param(name = "Place", value = "Registration")}, type = Analytics.Type.VIEW)
    public void registrationViewLocalChecksPassed(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "LocalChecksPassed");
        linkedHashMap.put("Place", "Registration");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "View_Password"), @Analytics.Param(name = "Place", value = "Registration")}, type = Analytics.Type.ACTION)
    public void registrationViewPassword(@Analytics.Param(name = "State") boolean z10, @Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "View_Password");
        linkedHashMap.put("Place", "Registration");
        linkedHashMap.put("State", String.valueOf(z10));
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "ReturnWeakPassword")}, type = Analytics.Type.VIEW)
    public void registrationViewReturnWeakPassword(@Analytics.Param(name = "From") String str, @Analytics.Param(name = "RegForm") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ReturnWeakPassword");
        linkedHashMap.put("From", String.valueOf(str));
        linkedHashMap.put("RegForm", String.valueOf(str2));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "ShowSecondBtnOnCallInLayout", type = Analytics.Type.EVENT)
    public void registrationWithCallInShowSecondBtn(@Analytics.Param(name = "AltAction") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("AltAction", str);
        this.mEventLogger.logEvent("ShowSecondBtnOnCallInLayout_Event", linkedHashMap);
    }

    @Analytics(name = "ShowSecondBtnOnCallUILayout", type = Analytics.Type.EVENT)
    public void registrationWithCallUIShowSecondBtn(@Analytics.Param(name = "AltAction") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("AltAction", str);
        this.mEventLogger.logEvent("ShowSecondBtnOnCallUILayout_Event", linkedHashMap);
    }

    @Analytics(name = "ReplyWithoutSmartReply", type = Analytics.Type.ACTION)
    public void replyWithoutSmartReply(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "beenViewed") boolean z11, @Analytics.Param(name = "isDefault") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("beenViewed", String.valueOf(z11));
        linkedHashMap.put("isDefault", String.valueOf(z12));
        this.mEventLogger.logEvent("ReplyWithoutSmartReply_Action", linkedHashMap);
    }

    @Analytics(name = "ShrinkFail", type = Analytics.Type.RESULT)
    public void reportShrinkFail(@Analytics.Param(name = "FailType") String str, @Analytics.Param(name = "ClassName") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("FailType", String.valueOf(str));
        linkedHashMap.put("ClassName", String.valueOf(str2));
        this.mEventLogger.logEvent("ShrinkFail_Result", linkedHashMap);
    }

    @Analytics(name = "ShrinkRestart", type = Analytics.Type.EVENT)
    public void reportShrinkRestart() {
        this.mEventLogger.logEvent("ShrinkRestart_Event", new LinkedHashMap());
    }

    @Analytics(name = "ShrinkStart", type = Analytics.Type.EVENT)
    public void reportShrinkStart() {
        this.mEventLogger.logEvent("ShrinkStart_Event", new LinkedHashMap());
    }

    @Analytics(name = "ShrinkOk", type = Analytics.Type.RESULT)
    public void reportShrinkSuccess() {
        this.mEventLogger.logEvent("ShrinkOk_Result", new LinkedHashMap());
    }

    @Analytics(name = "DeleteAccount", params = {@Analytics.Param(name = "Action", value = "Delete")}, type = Analytics.Type.ACTION)
    public void requestDeleteAccount() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Delete");
        this.mEventLogger.logEvent("DeleteAccount_Action", linkedHashMap);
    }

    @Analytics(name = "DeleteAccount", params = {@Analytics.Param(name = "Action", value = "RequestCall")}, type = Analytics.Type.ACTION)
    public void requestMakeCall() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "RequestCall");
        this.mEventLogger.logEvent("DeleteAccount_Action", linkedHashMap);
    }

    @Analytics(name = "RestoreByCode", params = {@Analytics.Param(name = "Action", value = "click")}, type = Analytics.Type.ACTION)
    public void restoreByCodeActionClick() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "click");
        this.mEventLogger.logEvent("RestoreByCode_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Filters", params = {@Analytics.Param(name = "Action", value = "Add")}, type = Analytics.Type.ACTION)
    public void saveFilter(@Analytics.Param(name = "MarkAsRead") boolean z10, @Analytics.Param(name = "ApplyToAll") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Add");
        linkedHashMap.put("MarkAsRead", String.valueOf(z10));
        linkedHashMap.put("ApplyToAll", String.valueOf(z11));
        this.mEventLogger.logEvent("Settings_Filters_Action", linkedHashMap);
    }

    @Analytics(name = "ScheduleSend", type = Analytics.Type.ACTION)
    public void scheduleSendAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("ScheduleSend_Action", linkedHashMap);
    }

    @Analytics(name = "ScheduleSend", params = {@Analytics.Param(name = "Action", value = "Cancel")}, type = Analytics.Type.ACTION)
    public void scheduleSendActionCancel(@Analytics.Param(name = "Tab") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Cancel");
        linkedHashMap.put("Tab", String.valueOf(str));
        this.mEventLogger.logEvent("ScheduleSend_Action", linkedHashMap);
    }

    @Analytics(name = "ScheduleSend", params = {@Analytics.Param(name = "Action", value = "Ok")}, type = Analytics.Type.ACTION)
    public void scheduleSendActionOk(@Analytics.Param(name = "delay") String str, @Analytics.Param(name = MailCsatEventParam.TAB) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Ok");
        linkedHashMap.put("delay", String.valueOf(str));
        linkedHashMap.put(MailCsatEventParam.TAB, String.valueOf(str2));
        this.mEventLogger.logEvent("ScheduleSend_Action", linkedHashMap);
    }

    @Analytics(name = "ScheduleSend", type = Analytics.Type.ERROR)
    public void scheduleSendError() {
        this.mEventLogger.logEvent("ScheduleSend_Error", new LinkedHashMap());
    }

    @Analytics(name = "ScheduleSend", params = {@Analytics.Param(name = "Action", value = "Success")}, type = Analytics.Type.ACTION)
    public void scheduleSendSuccess(@Analytics.Param(name = "delay") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Success");
        linkedHashMap.put("delay", String.valueOf(str));
        this.mEventLogger.logEvent("ScheduleSend_Action", linkedHashMap);
    }

    @Analytics(name = "Search", params = {@Analytics.Param(name = "Action", value = "ToggleAdvancedSearch")}, type = Analytics.Type.ACTION)
    public void searchActionToggleAdvancedSearch() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "ToggleAdvancedSearch");
        this.mEventLogger.logEvent("Search_Action", linkedHashMap);
    }

    @Analytics(name = "Search", type = Analytics.Type.EVENT)
    public void searchEvent(@Analytics.Param(name = "Event") String str, @Analytics.Param(name = "Type") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", String.valueOf(str));
        linkedHashMap.put("Type", String.valueOf(str2));
        this.mEventLogger.logEvent("Search_Event", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", params = {@Analytics.Param(name = "Action", value = "Search")}, type = Analytics.Type.ACTION)
    public void searchMailsFromContact(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Search");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_Action", linkedHashMap);
    }

    @Analytics(name = "ContactInfo", params = {@Analytics.Param(name = "Action", value = "SearchWithAttachments")}, type = Analytics.Type.ACTION)
    public void searchMailsFromContactWithAttachments(@Analytics.Param(name = "from") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "SearchWithAttachments");
        linkedHashMap.put("from", String.valueOf(str));
        this.mEventLogger.logEvent("ContactInfo_Action", linkedHashMap);
    }

    @Analytics(name = "Search_Recent", type = Analytics.Type.ACTION)
    public void searchRecentAction() {
        this.mEventLogger.logEvent("Search_Recent_Action", new LinkedHashMap());
    }

    @Analytics(name = "Search_Recent", type = Analytics.Type.VIEW)
    public void searchRecentView() {
        this.mEventLogger.logEvent("Search_Recent_View", new LinkedHashMap());
    }

    @Analytics(name = "SearchResultListClick", type = Analytics.Type.ACTION)
    public void searchResultListClickAction(@Analytics.Param(name = MailCsatEventParam.TAB) String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "attach") boolean z11, @Analytics.Param(name = "date") String str2, @Analytics.Param(name = "folder") boolean z12, @Analytics.Param(name = "category") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(MailCsatEventParam.TAB, String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("attach", String.valueOf(z11));
        linkedHashMap.put("date", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(z12));
        linkedHashMap.put("category", String.valueOf(str3));
        this.mEventLogger.logEvent("SearchResultListClick_Action", linkedHashMap);
    }

    @Analytics(name = "SearchResultsList_Quick", type = Analytics.Type.ACTION)
    public void searchResultsListQuickAction(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "Thread") boolean z10, @Analytics.Param(name = "FromFolder") String str2, @Analytics.Param(name = "isBlocked") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String strValueOf = String.valueOf(str);
        linkedHashMap.put("Action", strValueOf);
        linkedHashMap.put("Thread", String.valueOf(z10));
        linkedHashMap.put("FromFolder", str2);
        if (strValueOf.equals("MoveToBin")) {
            linkedHashMap.put("isBlocked", String.valueOf(z11));
        }
        this.mEventLogger.logEvent("SearchResultsList_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "SearchUserStartSearch", params = {@Analytics.Param(name = "requestFrom", value = "searchList")}, type = Analytics.Type.ACTION)
    public void searchUserStartSearchAction(@Analytics.Param(name = MailCsatEventParam.TAB) String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "attach") boolean z11, @Analytics.Param(name = "date") String str2, @Analytics.Param(name = "folder") boolean z12, @Analytics.Param(name = "category") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("requestFrom", "searchList");
        linkedHashMap.put(MailCsatEventParam.TAB, String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("attach", String.valueOf(z11));
        linkedHashMap.put("date", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(z12));
        linkedHashMap.put("category", String.valueOf(str3));
        this.mEventLogger.logEvent("SearchUserStartSearch_Action", linkedHashMap);
    }

    @Analytics(name = "Search", type = Analytics.Type.VIEW)
    public void searchView() {
        this.mEventLogger.logEvent("Search_View", new LinkedHashMap());
    }

    @Analytics(name = "Security", params = {@Analytics.Param(name = "Action", value = "Terminate")}, type = Analytics.Type.ACTION)
    public void securityAction() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Terminate");
        this.mEventLogger.logEvent("Security_Action", linkedHashMap);
    }

    @Analytics(name = "Security", type = Analytics.Type.ERROR)
    public void securityError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("Security_Error", linkedHashMap);
    }

    @Analytics(name = "Security", type = Analytics.Type.VIEW)
    public void securityView() {
        this.mEventLogger.logEvent("Security_View", new LinkedHashMap());
    }

    @Analytics(name = "Account_Settings_Select_Account", type = Analytics.Type.VIEW)
    public void sendAccountSecuritySelectAccount(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("Account_Settings_Select_Account_View", linkedHashMap);
    }

    @Analytics(name = "Account_Settings", type = Analytics.Type.VIEW)
    public void sendAccountSecurityView(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("Account_Settings_View", linkedHashMap);
    }

    @Analytics(name = "SaveActivityBundle", type = Analytics.Type.EVENT)
    public void sendActivityAnalytic(@Analytics.Param(name = "name") String str, @Analytics.Param(name = "size") String str2, @Analytics.Param(name = "maxKey") String str3, @Analytics.Param(name = "maxSize") String str4, @Analytics.Param(name = "fragments") int i10, @Analytics.Param(name = "maxFragment") String str5, @Analytics.Param(name = "maxFragmentSize") String str6) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("name", String.valueOf(str));
        linkedHashMap.put("size", String.valueOf(str2));
        linkedHashMap.put("maxKey", String.valueOf(str3));
        linkedHashMap.put("maxSize", String.valueOf(str4));
        linkedHashMap.put("fragments", String.valueOf(i10));
        linkedHashMap.put("maxFragment", String.valueOf(str5));
        linkedHashMap.put("maxFragmentSize", String.valueOf(str6));
        this.mEventLogger.logEvent("SaveActivityBundle_Event", linkedHashMap);
    }

    @Analytics(name = "Addr_Book_Local", type = Analytics.Type.STATE)
    public void sendAddressBookLocalStateAnalytics(@Analytics.Param(name = "size") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("size", String.valueOf(str));
        this.mEventLogger.logEvent("Addr_Book_Local_State", linkedHashMap);
    }

    @Analytics(name = "Addr_Book_System", type = Analytics.Type.STATE)
    public void sendAddressBookSystemStateAnalytics(@Analytics.Param(name = "size") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("size", String.valueOf(str));
        this.mEventLogger.logEvent("Addr_Book_System_State", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_Dialog_shown", type = Analytics.Type.VIEW)
    public void sendAnalyticAppUpdateDialogShown(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_Dialog_shown_View", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_Success", type = Analytics.Type.EVENT)
    public void sendAnalyticAppUpdateSuccess() {
        this.mEventLogger.logEvent("InAppUpdate_Success_Event", new LinkedHashMap());
    }

    @Analytics(name = "InAppUpdate_bg_reload_app", type = Analytics.Type.ACTION)
    public void sendAnalyticBackgroundReinstallApp(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_bg_reload_app_Action", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_click_reload_app", type = Analytics.Type.ACTION)
    public void sendAnalyticClickReinstallApp(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_click_reload_app_Action", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_Dialog_cancel", type = Analytics.Type.ACTION)
    public void sendAnalyticOnCancelAppUpdate(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_Dialog_cancel_Action", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_click_update_btn", type = Analytics.Type.ACTION)
    public void sendAnalyticOnClickAppUpdateButton(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_click_update_btn_Action", linkedHashMap);
    }

    @Analytics(name = "WelcomeLogin_On_Image_Click", type = Analytics.Type.ACTION)
    public void sendAnalyticOnClickByImage() {
        this.mEventLogger.logEvent("WelcomeLogin_On_Image_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "InAppUpdate_install_failure", type = Analytics.Type.ERROR)
    public void sendAnalyticOnInstallationAppFailure(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_install_failure_Error", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_install_success", type = Analytics.Type.ERROR)
    public void sendAnalyticOnInstallationAppSuccess(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_install_success_Error", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_downloaded", type = Analytics.Type.EVENT)
    public void sendAnalyticOnNewVersionDownloaded(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_downloaded_Event", linkedHashMap);
    }

    @Analytics(name = "InAppUpdate_not_now", type = Analytics.Type.ACTION)
    public void sendAnalyticOnNotNowAppUpdate(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_not_now_Action", linkedHashMap);
    }

    @Analytics(name = "WelcomeLogin_Shown", type = Analytics.Type.VIEW)
    public void sendAnalyticOnShownScreen() {
        this.mEventLogger.logEvent("WelcomeLogin_Shown_View", new LinkedHashMap());
    }

    @Analytics(name = "NotificationsPushToken", type = Analytics.Type.EVENT)
    public void sendAnalyticPushTokenEvent(@Analytics.Param(name = "status") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("status", String.valueOf(str));
        this.mEventLogger.logEvent("NotificationsPushToken_Event", linkedHashMap);
    }

    @Analytics(name = "RestoreNotificationDeclined", type = Analytics.Type.ACTION)
    public void sendAnalyticRestoreDeclined(@Analytics.Param(name = "RestoreType") String str, @Analytics.Param(name = "IsRestore") String str2, @Analytics.Param(name = "HasActiveAcc") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RestoreType", String.valueOf(str));
        linkedHashMap.put("IsRestore", String.valueOf(str2));
        linkedHashMap.put("HasActiveAcc", String.valueOf(str3));
        this.mEventLogger.logEvent("RestoreNotificationDeclined_Action", linkedHashMap);
    }

    @Analytics(name = "RestoreNotificationShown", type = Analytics.Type.ACTION)
    public void sendAnalyticRestoreShown(@Analytics.Param(name = "TimePassed") String str, @Analytics.Param(name = "RestoreType") String str2, @Analytics.Param(name = "IsRestore") String str3, @Analytics.Param(name = "HasActiveAcc") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TimePassed", String.valueOf(str));
        linkedHashMap.put("RestoreType", String.valueOf(str2));
        linkedHashMap.put("IsRestore", String.valueOf(str3));
        linkedHashMap.put("HasActiveAcc", String.valueOf(str4));
        this.mEventLogger.logEvent("RestoreNotificationShown_Action", linkedHashMap);
    }

    @Analytics(name = "WelcomeLogin_Start_Auth", type = Analytics.Type.ACTION)
    public void sendAnalyticStartAuthScreen() {
        this.mEventLogger.logEvent("WelcomeLogin_Start_Auth_Action", new LinkedHashMap());
    }

    @Analytics(name = "WelcomeLogin_Start_Registration", type = Analytics.Type.ACTION)
    public void sendAnalyticStartRegScreen() {
        this.mEventLogger.logEvent("WelcomeLogin_Start_Registration_Action", new LinkedHashMap());
    }

    @Analytics(name = "InAppUpdate_start_reinstall_app", type = Analytics.Type.ACTION)
    public void sendAnalyticStartReinstallApp(@Analytics.Param(name = "RuleName") String str, @Analytics.Param(name = "ShowUpdateDialogCounter") long j10, @Analytics.Param(name = AnalyticsConstants.KEY.SOURCE) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RuleName", String.valueOf(str));
        linkedHashMap.put("ShowUpdateDialogCounter", String.valueOf(j10));
        linkedHashMap.put(AnalyticsConstants.KEY.SOURCE, String.valueOf(str2));
        this.mEventLogger.logEvent("InAppUpdate_start_reinstall_app_Action", linkedHashMap);
    }

    @Analytics(name = "NotificationsPushToken", type = Analytics.Type.EVENT)
    public void sendAnalyticTokenAbsentEvent(@Analytics.Param(name = "empty_token") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("empty_token", String.valueOf(str));
        this.mEventLogger.logEvent("NotificationsPushToken_Event", linkedHashMap);
    }

    @Analytics(name = "NotificationsDisable", params = {@Analytics.Param(name = "from", value = "app")}, type = Analytics.Type.EVENT)
    public void sendAppNotificationsDisableEvent(@Analytics.Param(name = "firstUsage") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", "app");
        linkedHashMap.put("firstUsage", String.valueOf(z10));
        this.mEventLogger.logEvent("NotificationsDisable_Event", linkedHashMap);
    }

    @Analytics(name = "NotificationsEnabled", params = {@Analytics.Param(name = "from", value = "app")}, type = Analytics.Type.EVENT)
    public void sendAppNotificationsEnableEvent(@Analytics.Param(name = "firstUsage") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", "app");
        linkedHashMap.put("firstUsage", String.valueOf(z10));
        this.mEventLogger.logEvent("NotificationsEnabled_Event", linkedHashMap);
    }

    @Analytics(name = "ApkSizeAnalytics", type = Analytics.Type.EVENT)
    public void sendAppSize(@Analytics.Param(name = "app_size") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("app_size", String.valueOf(str));
        this.mEventLogger.logEvent("ApkSizeAnalytics_Event", linkedHashMap);
    }

    @Analytics(name = "AppendingQueryParamsHandled", type = Analytics.Type.ACTION)
    public void sendAppendingQueryParamsHandledAnalytics(@Analytics.Param(name = "name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("name", String.valueOf(str));
        this.mEventLogger.logEvent("AppendingQueryParamsHandled_Action", linkedHashMap);
    }

    @Analytics(name = "ApplicationTimeSpent", type = Analytics.Type.ACTION)
    public void sendApplicationTimeSpent(@Analytics.Param(name = "start_timestamp") long j10, @Analytics.Param(name = "physical_duration_sec") long j11, @Analytics.Param(name = "logical_duration_sec") long j12, @Analytics.Param(name = "launch_timeout") long j13, @Analytics.Param(name = "sessions_cnt") int i10, @Analytics.Param(name = "app_mode") String str, @Analytics.Param(name = "session_saved_by_timer") Boolean bool) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("start_timestamp", String.valueOf(j10));
        linkedHashMap.put("physical_duration_sec", String.valueOf(j11));
        linkedHashMap.put("logical_duration_sec", String.valueOf(j12));
        linkedHashMap.put("launch_timeout", String.valueOf(j13));
        linkedHashMap.put("sessions_cnt", String.valueOf(i10));
        linkedHashMap.put("app_mode", String.valueOf(str));
        linkedHashMap.put("session_saved_by_timer", String.valueOf(bool));
        this.mEventLogger.logEvent("ApplicationTimeSpent_Action", linkedHashMap);
    }

    @Analytics(name = "EditorArchive", type = Analytics.Type.ACTION)
    public void sendArchiveAnalytics() {
        this.mEventLogger.logEvent("EditorArchive_Action", new LinkedHashMap());
    }

    @Analytics(name = "BackgroundSessionCompleteV2", type = Analytics.Type.EVENT)
    public void sendBackgroundSessionEventAnalytics(@Analytics.Param(name = "errors_count") String str, @Analytics.Param(name = "app_crashes") String str2, @Analytics.Param(name = "api_rx") String str3, @Analytics.Param(name = "api_tx") String str4, @Analytics.Param(name = "api_tx_send_v2") String str5, @Analytics.Param(name = "ad_slot_rx_v2") String str6, @Analytics.Param(name = "ad_slot_tx") String str7, @Analytics.Param(name = "ad_link_rx") String str8, @Analytics.Param(name = "ad_link_tx") String str9, @Analytics.Param(name = "app_traffic_rx_v2") String str10, @Analytics.Param(name = "app_traffic_tx_v2") String str11, @Analytics.Param(name = "last_crash_reason_id") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("errors_count", String.valueOf(str));
        linkedHashMap.put("app_crashes", String.valueOf(str2));
        linkedHashMap.put("api_rx", String.valueOf(str3));
        linkedHashMap.put("api_tx", String.valueOf(str4));
        linkedHashMap.put("api_tx_send_v2", String.valueOf(str5));
        linkedHashMap.put("ad_slot_rx_v2", String.valueOf(str6));
        linkedHashMap.put("ad_slot_tx", String.valueOf(str7));
        linkedHashMap.put("ad_link_rx", String.valueOf(str8));
        linkedHashMap.put("ad_link_tx", String.valueOf(str9));
        linkedHashMap.put("app_traffic_rx_v2", String.valueOf(str10));
        linkedHashMap.put("app_traffic_tx_v2", String.valueOf(str11));
        linkedHashMap.put("last_crash_reason_id", String.valueOf(i10));
        this.mEventLogger.logEvent("BackgroundSessionCompleteV2_Event", linkedHashMap);
    }

    @Analytics(name = "Bad_State_Exception_Account_Manager", type = Analytics.Type.ERROR)
    public void sendBadStateExceptionAccountManager() {
        this.mEventLogger.logEvent("Bad_State_Exception_Account_Manager_Error", new LinkedHashMap());
    }

    @Analytics(name = "MessageList_Banner", params = {@Analytics.Param(name = "Error", value = "TrackingRedirectFailed")}, type = Analytics.Type.ERROR)
    public void sendBannerTrackingRedirectFailedAnalytics() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", "TrackingRedirectFailed");
        this.mEventLogger.logEvent("MessageList_Banner_Error", linkedHashMap);
    }

    @Analytics(name = "CalendarPush", type = Analytics.Type.ACTION)
    public void sendCalendarPushActionClicked(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "meta_data") String str2, @Analytics.Param(name = "event_uid") String str3, @Analytics.Param(name = "type") String str4, @Analytics.Param(name = "subtype") String str5, @Analytics.Param(name = "ml_type") String str6, @Analytics.Param(name = "ml_subtype") String str7) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("meta_data", String.valueOf(str2));
        linkedHashMap.put("event_uid", String.valueOf(str3));
        linkedHashMap.put("type", String.valueOf(str4));
        linkedHashMap.put("subtype", String.valueOf(str5));
        linkedHashMap.put("ml_type", String.valueOf(str6));
        linkedHashMap.put("ml_subtype", String.valueOf(str7));
        this.mEventLogger.logEvent("CalendarPush_Action", linkedHashMap);
    }

    @Analytics(name = "CalendarPushAction", type = Analytics.Type.ERROR)
    public void sendCalendarPushActionError(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "meta_data") String str2, @Analytics.Param(name = "event_uid") String str3, @Analytics.Param(name = "type") String str4, @Analytics.Param(name = "subtype") String str5) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("meta_data", String.valueOf(str2));
        linkedHashMap.put("event_uid", String.valueOf(str3));
        linkedHashMap.put("type", String.valueOf(str4));
        linkedHashMap.put("subtype", String.valueOf(str5));
        this.mEventLogger.logEvent("CalendarPush_Error", linkedHashMap);
    }

    @Analytics(name = "CalendarPushClicked", type = Analytics.Type.ACTION)
    public void sendCalendarPushClicked(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "type") String str2, @Analytics.Param(name = "subtype") String str3, @Analytics.Param(name = "ml_type") String str4, @Analytics.Param(name = "ml_subtype") String str5) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        linkedHashMap.put("subtype", String.valueOf(str3));
        linkedHashMap.put("ml_type", String.valueOf(str4));
        linkedHashMap.put("ml_subtype", String.valueOf(str5));
        this.mEventLogger.logEvent("CalendarPushClicked_Action", linkedHashMap);
    }

    @Analytics(name = "CalendarPushClicked", type = Analytics.Type.ERROR)
    public void sendCalendarPushClickedError(@Analytics.Param(name = "ErrorMessage") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ErrorMessage", String.valueOf(str));
        this.mEventLogger.logEvent("CalendarPushClicked_Error", linkedHashMap);
    }

    @Analytics(name = "CalendarPush", type = Analytics.Type.VIEW)
    public void sendCalendarPushShow(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "type") String str2, @Analytics.Param(name = "subtype") String str3, @Analytics.Param(name = "ml_type") String str4, @Analytics.Param(name = "ml_subtype") String str5, @Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("type", String.valueOf(str2));
        linkedHashMap.put("subtype", String.valueOf(str3));
        linkedHashMap.put("ml_type", String.valueOf(str4));
        linkedHashMap.put("ml_subtype", String.valueOf(str5));
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("CalendarPush_View", linkedHashMap);
    }

    @Analytics(name = "SendCancel", type = Analytics.Type.ACTION)
    public void sendCancelAnalytic(@Analytics.Param(name = "sendType") String str, @Analytics.Param(name = "afterAction") String str2, @Analytics.Param(name = "fromSnackbar") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sendType", String.valueOf(str));
        linkedHashMap.put("afterAction", String.valueOf(str2));
        linkedHashMap.put("fromSnackbar", String.valueOf(z10));
        this.mEventLogger.logEvent("SendCancel_Action", linkedHashMap);
    }

    @Analytics(name = "ConfigPatternSyntaxError", type = Analytics.Type.EVENT)
    public void sendConfigPatternSyntaxError(@Analytics.Param(name = "field_full_name") String str, @Analytics.Param(name = "reason") String str2, @Analytics.Param(name = "action_taken") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("field_full_name", String.valueOf(str));
        linkedHashMap.put("reason", String.valueOf(str2));
        linkedHashMap.put("action_taken", String.valueOf(str3));
        this.mEventLogger.logEvent("ConfigPatternSyntaxError_Event", linkedHashMap);
    }

    @Analytics(name = "Connection_Sampling", type = Analytics.Type.EVENT)
    public void sendConnectionSamplings(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("Connection_Sampling_Event", linkedHashMap);
    }

    @Analytics(name = "Covid_Click", type = Analytics.Type.ACTION)
    public void sendCovidClickAnalytics() {
        this.mEventLogger.logEvent("Covid_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "DB_Update", type = Analytics.Type.EVENT)
    public void sendDBUpdateDurationAnalytics(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "result") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("result", String.valueOf(str2));
        this.mEventLogger.logEvent("DB_Update_Event", linkedHashMap);
    }

    @Analytics(name = "SettingsNightMode", type = Analytics.Type.ACTION)
    public void sendDarkThemeAnalytic(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("SettingsNightMode_Action", linkedHashMap);
    }

    @Analytics(name = "ConfigDecodeError", type = Analytics.Type.EVENT)
    public void sendDecodeConfigError(@Analytics.Param(name = "field_full_name") String str, @Analytics.Param(name = "reason") String str2, @Analytics.Param(name = "json") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("field_full_name", String.valueOf(str));
        linkedHashMap.put("reason", String.valueOf(str2));
        linkedHashMap.put("json", String.valueOf(str3));
        this.mEventLogger.logEvent("ConfigDecodeError_Event", linkedHashMap);
    }

    @Analytics(name = "EditorDelete", type = Analytics.Type.ACTION)
    public void sendDeleteAnalytics() {
        this.mEventLogger.logEvent("EditorDelete_Action", new LinkedHashMap());
    }

    @Analytics(name = "Login_Domain_Clicked", type = Analytics.Type.ACTION)
    public void sendDomainClickedAnalytics(@Analytics.Param(name = "indexInConfig") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("indexInConfig", String.valueOf(i10));
        this.mEventLogger.logEvent("Login_Domain_Clicked_Action", linkedHashMap);
    }

    @Analytics(name = "Login_Domain_Suggestion_Flow", type = Analytics.Type.EVENT)
    public void sendDomainSuggestionFlowAnalytics(@Analytics.Param(name = "flow") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("flow", String.valueOf(str));
        this.mEventLogger.logEvent("Login_Domain_Suggestion_Flow_Event", linkedHashMap);
    }

    @Analytics(name = "MultiSelectTutorial", type = Analytics.Type.ACTION)
    public void sendEditModeAnalyticEvent(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("MultiSelectTutorial_Action", linkedHashMap);
    }

    @Analytics(name = "EmailToMyselfAutoComplete", type = Analytics.Type.VIEW)
    public void sendEmailToMyselfDropDownSuggestAnalytics(@Analytics.Param(name = "email_forced") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("email_forced", String.valueOf(z10));
        this.mEventLogger.logEvent("EmailToMyselfAutoComplete_View", linkedHashMap);
    }

    @Analytics(name = "EmailToMyselfAddressBook", type = Analytics.Type.VIEW)
    public void sendEmailToMyselfSuggestionShownAnalytics() {
        this.mEventLogger.logEvent("EmailToMyselfAddressBook_View", new LinkedHashMap());
    }

    @Analytics(name = "EmailToMyselfAddressBook", type = Analytics.Type.VIEW)
    public void sendEmailToMyselfSuggestionShownInAddressBookAnalytics(@Analytics.Param(name = "with_email") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("with_email", String.valueOf(z10));
        this.mEventLogger.logEvent("EmailToMyselfAddressBook_View", linkedHashMap);
    }

    @Analytics(name = "EmailWithExtraPlateOpened", radar = true, type = Analytics.Type.ACTION)
    public void sendEmailWithPlateHasBeenOpened(@Analytics.Param(dynamic = true, name = "account") String str, @Analytics.Param(name = "name") String str2, @Analytics.Param(dynamic = true, name = "messageId") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("account", String.valueOf(str));
        String strValueOf = String.valueOf(str2);
        linkedHashMap2.put("name", strValueOf);
        linkedHashMap.put("name", strValueOf);
        linkedHashMap2.put("messageId", String.valueOf(str3));
        this.mEventLogger.logEvent("EmailWithExtraPlateOpened_Action", linkedHashMap);
        this.mEventLogger.logRadarEvent("EmailWithExtraPlateOpened_Action", linkedHashMap2);
    }

    @Analytics(name = "Feedback_Click", type = Analytics.Type.ACTION)
    public void sendFeedbackAnalytics() {
        this.mEventLogger.logEvent("Feedback_Click_Action", new LinkedHashMap());
    }

    @Analytics(name = "SaveFragmentBundle", type = Analytics.Type.EVENT)
    public void sendFragmentAnalytic(@Analytics.Param(name = "name") String str, @Analytics.Param(name = "activity") String str2, @Analytics.Param(name = "size") String str3, @Analytics.Param(name = "maxKey") String str4, @Analytics.Param(name = "maxSize") String str5) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("name", String.valueOf(str));
        linkedHashMap.put("activity", String.valueOf(str2));
        linkedHashMap.put("size", String.valueOf(str3));
        linkedHashMap.put("maxKey", String.valueOf(str4));
        linkedHashMap.put("maxSize", String.valueOf(str5));
        this.mEventLogger.logEvent("SaveFragmentBundle_Event", linkedHashMap);
    }

    @Analytics(name = "IMAPmatchingError", type = Analytics.Type.ERROR)
    public void sendIMAPmatchingFailureAnalytics(@Analytics.Param(name = "domain") String str, @Analytics.Param(name = "folder") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("domain", String.valueOf(str));
        linkedHashMap.put("folder", String.valueOf(str2));
        this.mEventLogger.logEvent("IMAPmatchingError_Error", linkedHashMap);
    }

    @Analytics(name = "InstallLaunchSource", type = Analytics.Type.ACTION)
    public void sendInstallInfoAnalytic(@Analytics.Param(name = "packagename") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("packagename", String.valueOf(str));
        this.mEventLogger.logEvent("InstallLaunchSource_Action", linkedHashMap);
    }

    @Analytics(name = "LeelooDesignEnabledByDarkTheme", type = Analytics.Type.ACTION)
    public void sendLeelooDesignEnabledByDarkTheme() {
        this.mEventLogger.logEvent("LeelooDesignEnabledByDarkTheme_Action", new LinkedHashMap());
    }

    @Analytics(name = "DeliveredToLibVerify", type = Analytics.Type.EVENT)
    public void sendLibVerifyAnalytic() {
        this.mEventLogger.logEvent("DeliveredToLibVerify_Event", new LinkedHashMap());
    }

    @Analytics(name = "LinkReplaced", type = Analytics.Type.ACTION)
    public void sendLinkHasBeenReplacedAnalytics(@Analytics.Param(name = "name") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("name", String.valueOf(str));
        this.mEventLogger.logEvent("LinkReplaced_Action", linkedHashMap);
    }

    @Analytics(name = "Login_Domain_Suggested", type = Analytics.Type.EVENT)
    public void sendLoginDomainSuggested() {
        this.mEventLogger.logEvent("Login_Domain_Suggested_Event", new LinkedHashMap());
    }

    @Analytics(name = "Login_Edit_Started", type = Analytics.Type.EVENT)
    public void sendLoginEditStarted() {
        this.mEventLogger.logEvent("Login_Edit_Started_Event", new LinkedHashMap());
    }

    @Analytics(params = {@Analytics.Param(name = "Action", value = "Login")}, type = Analytics.Type.ACTION)
    public void sendLoginSuggestFinish(@Analytics.Name String str, @Analytics.Param(name = "Domain") String str2, @Analytics.Param(name = "Success") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Login");
        linkedHashMap.put("Domain", String.valueOf(str2));
        linkedHashMap.put("Success", String.valueOf(z10));
        this.mEventLogger.logEvent(str + "_Action", linkedHashMap);
    }

    @Analytics(name = "Logout_User_On_Account_Manager_Bad_State", type = Analytics.Type.ERROR)
    public void sendLogoutUserOnAccountManagerBadState() {
        this.mEventLogger.logEvent("Logout_User_On_Account_Manager_Bad_State_Error", new LinkedHashMap());
    }

    @Analytics(name = "EditorMarkNoSpam", type = Analytics.Type.ACTION)
    public void sendMarkNoSpamAnalytics() {
        this.mEventLogger.logEvent("EditorMarkNoSpam_Action", new LinkedHashMap());
    }

    @Analytics(name = "EditorMarkSpam", type = Analytics.Type.ACTION)
    public void sendMarkSpamAnalytics() {
        this.mEventLogger.logEvent("EditorMarkSpam_Action", new LinkedHashMap());
    }

    @Analytics(name = "MessageList", type = Analytics.Type.EVENT)
    public void sendMessageListEventAnalytics(@Analytics.Param(name = "Event") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", String.valueOf(str));
        this.mEventLogger.logEvent("MessageList_Event", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Event", value = "ServerRequest_GetMore")}, type = Analytics.Type.EVENT)
    public void sendMessageListGetMoreAnalytics() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", "ServerRequest_GetMore");
        this.mEventLogger.logEvent("MessageList_Event", linkedHashMap);
    }

    @Analytics(name = "MessageList", params = {@Analytics.Param(name = "Event", value = "ServerRequest_Update")}, type = Analytics.Type.EVENT)
    public void sendMessageListUpdateAnalytics() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", "ServerRequest_Update");
        this.mEventLogger.logEvent("MessageList_Event", linkedHashMap);
    }

    @Analytics(name = "Message_Sent", type = Analytics.Type.EVENT)
    public void sendMessageSentAnalytics(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("Message_Sent_Event", linkedHashMap);
    }

    @Analytics(name = "EditorMoveToBin", type = Analytics.Type.ACTION)
    public void sendMoveToBinAnalytics(@Analytics.Param("TrashType") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TrashType", str);
        this.mEventLogger.logEvent("EditorMoveToBin_Action", linkedHashMap);
    }

    @Analytics(name = "NewEmailPushReceived", type = Analytics.Type.EVENT)
    public void sendNewMailPushReceived(@Analytics.Param(name = "category") String str, @Analytics.Param(name = "has_attachments") boolean z10, @Analytics.Param(name = "isReminder") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("category", String.valueOf(str));
        linkedHashMap.put("has_attachments", String.valueOf(z10));
        linkedHashMap.put("isReminder", String.valueOf(z11));
        this.mEventLogger.logEvent("NewEmailPushReceived_Event", linkedHashMap);
    }

    @Analytics(name = "NotificationsSettings", type = Analytics.Type.STATE)
    public void sendNotificationSettingAnalytics(@Analytics.Param(name = "enabledApp") boolean z10, @Analytics.Param(name = "enabledSystem") boolean z11, @Analytics.Param(name = "enabledCommon") boolean z12, @Analytics.Param(name = "firstUsage") boolean z13, @Analytics.Param(name = "soundEnabledFromSystem") boolean z14, @Analytics.Param(name = "soundEnabledFromApp") String str, @Analytics.Param(name = "soundEnabled") boolean z15, @Analytics.Param(name = "enabledFilterSender") boolean z16, @Analytics.Param(name = "enabledFilterMessagesInFolders") boolean z17, @Analytics.Param(name = "enabledFilterSocial ") boolean z18, @Analytics.Param(name = "enabledFilterNewsletter") boolean z19, @Analytics.Param(name = "mailAppNotificationEnabled") boolean z20) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("enabledApp", String.valueOf(z10));
        linkedHashMap.put("enabledSystem", String.valueOf(z11));
        linkedHashMap.put("enabledCommon", String.valueOf(z12));
        linkedHashMap.put("firstUsage", String.valueOf(z13));
        linkedHashMap.put("soundEnabledFromSystem", String.valueOf(z14));
        linkedHashMap.put("soundEnabledFromApp", String.valueOf(str));
        linkedHashMap.put("soundEnabled", String.valueOf(z15));
        linkedHashMap.put("enabledFilterSender", String.valueOf(z16));
        linkedHashMap.put("enabledFilterMessagesInFolders", String.valueOf(z17));
        linkedHashMap.put("enabledFilterSocial ", String.valueOf(z18));
        linkedHashMap.put("enabledFilterNewsletter", String.valueOf(z19));
        linkedHashMap.put("mailAppNotificationEnabled", String.valueOf(z20));
        this.mEventLogger.logEvent("NotificationsSettings_State", linkedHashMap);
    }

    @Analytics(name = "NotificationSwiped", type = Analytics.Type.ACTION)
    public void sendNotificationSwipedInfo(@Analytics.Param(name = "hasSmartReplies") String str, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11, @Analytics.Param(name = "pushType") String str2, @Analytics.Param(name = "category") String str3, @Analytics.Param(name = "isReminder") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("hasSmartReplies", String.valueOf(str));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        linkedHashMap.put("pushType", String.valueOf(str2));
        linkedHashMap.put("category", String.valueOf(str3));
        linkedHashMap.put("isReminder", String.valueOf(z12));
        this.mEventLogger.logEvent("NotificationSwiped_Action", linkedHashMap);
    }

    @Analytics(name = AnalyticsConstants.KEY.MSG, type = Analytics.Type.EVENT)
    public void sendNotifyOnPushFailedAnalytics(@Analytics.Param(name = TagAnalyticsImpl.ACTION_TAG) String str, @Analytics.Param(name = "Notification") String str2, @Analytics.Param(name = "ErrorMessage") String str3, @Analytics.Param(name = "Retry_number") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(TagAnalyticsImpl.ACTION_TAG, String.valueOf(str));
        linkedHashMap.put("Notification", String.valueOf(str2));
        linkedHashMap.put("ErrorMessage", String.valueOf(str3));
        linkedHashMap.put("Retry_number", String.valueOf(i10));
        this.mEventLogger.logEvent("Message_Event", linkedHashMap);
    }

    @Analytics(name = "MailApplication_Created", type = Analytics.Type.EVENT)
    public void sendOnApplicationCreated(@Analytics.Param(name = "time") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        this.mEventLogger.logEvent("MailApplication_Created_Event", linkedHashMap);
    }

    @Analytics(name = "CarouselBanner_Disclaimer", params = {@Analytics.Param(name = "Action", value = "Click")}, type = Analytics.Type.ACTION)
    public void sendOnDisclaimerClickAnalyticEvent() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Click");
        this.mEventLogger.logEvent("CarouselBanner_Disclaimer_Action", linkedHashMap);
    }

    @Analytics(name = "Folders_Drawer_Expanded", type = Analytics.Type.VIEW)
    public void sendOnDrawerExpanded(@Analytics.Param(name = "bannersCount") int i10, @Analytics.Param(name = "foldersCount") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("bannersCount", String.valueOf(i10));
        linkedHashMap.put("foldersCount", String.valueOf(str));
        this.mEventLogger.logEvent("Folders_Drawer_Expanded_View", linkedHashMap);
    }

    @Analytics(name = "Folders_Drawer_Opened", type = Analytics.Type.VIEW)
    public void sendOnDrawerOpened(@Analytics.Param(name = "bannersCount") int i10, @Analytics.Param(name = "foldersCount") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("bannersCount", String.valueOf(i10));
        linkedHashMap.put("foldersCount", String.valueOf(str));
        this.mEventLogger.logEvent("Folders_Drawer_Opened_View", linkedHashMap);
    }

    @Analytics(name = "EmailToMyselfAutocompleteClicked", type = Analytics.Type.ACTION)
    public void sendOnEmailToMyselfClickedAnalytics(@Analytics.Param(name = "email_visible") boolean z10, @Analytics.Param(name = "always_with_email") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("email_visible", String.valueOf(z10));
        linkedHashMap.put("always_with_email", String.valueOf(z11));
        this.mEventLogger.logEvent("EmailToMyselfAutocompleteClicked_Action", linkedHashMap);
    }

    @Analytics(name = "Activity_Entered", type = Analytics.Type.EVENT)
    public void sendOnEnteredInAnyActivity(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "activity") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("activity", String.valueOf(str2));
        this.mEventLogger.logEvent("Activity_Entered_Event", linkedHashMap);
    }

    @Analytics(name = "Login_Entered", type = Analytics.Type.EVENT)
    public void sendOnEnteredInLoginActivity(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "time_sec") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("time_sec", String.valueOf(str2));
        this.mEventLogger.logEvent("Login_Entered_Event", linkedHashMap);
    }

    @Analytics(name = "MailPortal_Entered", type = Analytics.Type.EVENT)
    public void sendOnEnteredInMailPortalActivity(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "time_sec") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("time_sec", String.valueOf(str2));
        this.mEventLogger.logEvent("MailPortal_Entered_Event", linkedHashMap);
    }

    @Analytics(name = "Read_From_Push_Entered", type = Analytics.Type.EVENT)
    public void sendOnEnteredInReadActivity(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "time_sec") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("time_sec", String.valueOf(str2));
        this.mEventLogger.logEvent("Read_From_Push_Entered_Event", linkedHashMap);
    }

    @Analytics(name = "SlideStack_Entered", type = Analytics.Type.EVENT)
    public void sendOnEnteredInSlideStackActivity(@Analytics.Param(name = "time") String str, @Analytics.Param(name = "time_sec") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("time", String.valueOf(str));
        linkedHashMap.put("time_sec", String.valueOf(str2));
        this.mEventLogger.logEvent("SlideStack_Entered_Event", linkedHashMap);
    }

    @Analytics(name = "MailPortal_Stopped", type = Analytics.Type.EVENT)
    public void sendOnStopMailPortalActivity() {
        this.mEventLogger.logEvent("MailPortal_Stopped_Event", new LinkedHashMap());
    }

    @Analytics(name = "SlideStack_Stopped", type = Analytics.Type.EVENT)
    public void sendOnStopSlideStackActivity() {
        this.mEventLogger.logEvent("SlideStack_Stopped_Event", new LinkedHashMap());
    }

    @Analytics(name = "CloudFixButton", type = Analytics.Type.ACTION)
    public void sendOpenAppCloudAnalytic(@Analytics.Param(name = "viewCase") String str, @Analytics.Param(name = "installed") Boolean bool) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("viewCase", String.valueOf(str));
        linkedHashMap.put("installed", String.valueOf(bool));
        this.mEventLogger.logEvent("CloudFixButton_Action", linkedHashMap);
    }

    @Analytics(name = "CarouselBanner_Disclaimer", params = {@Analytics.Param(name = "Action", value = "Open")}, type = Analytics.Type.ACTION)
    public void sendOpenDisclaimerDialogViewAnalyticEvent() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Open");
        this.mEventLogger.logEvent("CarouselBanner_Disclaimer_Action", linkedHashMap);
    }

    @Analytics(name = "Parse_Push_Message_Error", type = Analytics.Type.ERROR)
    public void sendParsePushMessageError(@Analytics.Param(name = "Bundle") Map<String, String> map, @Analytics.Param(name = "stackTrace") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Bundle", String.valueOf(map));
        linkedHashMap.put("stackTrace", String.valueOf(str));
        this.mEventLogger.logEvent("Parse_Push_Message_Error_Error", linkedHashMap);
    }

    @Analytics(name = "ConfigParsingError", type = Analytics.Type.EVENT)
    public void sendParsingConfigError(@Analytics.Param(name = "field_full_name") String str, @Analytics.Param(name = "reason") String str2, @Analytics.Param(name = "action_taken") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("field_full_name", String.valueOf(str));
        linkedHashMap.put("reason", String.valueOf(str2));
        linkedHashMap.put("action_taken", String.valueOf(str3));
        this.mEventLogger.logEvent("ConfigParsingError_Event", linkedHashMap);
    }

    @Analytics(name = "PermissionReqInstrFakeClk", type = Analytics.Type.ACTION)
    public void sendPermissionsInstructionFakeClick() {
        this.mEventLogger.logEvent("PermissionReqInstrFakeClk_Action", new LinkedHashMap());
    }

    @Analytics(type = Analytics.Type.STATE)
    public void sendPortalAppNotificationsState(@Analytics.Name String str, @Analytics.Param(name = "state") String str2, @Analytics.Param(name = "enabled_tag_ids") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("state", String.valueOf(str2));
        linkedHashMap.put("enabled_tag_ids", String.valueOf(str3));
        this.mEventLogger.logEvent(str + "_State", linkedHashMap);
    }

    @Analytics(name = "PrefetchSmartReplyRequest", type = Analytics.Type.ACTION)
    public void sendPrefetchSmartReplyAnalyticsRequest(@Analytics.Param(name = "count") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("count", String.valueOf(str));
        this.mEventLogger.logEvent("PrefetchSmartReplyRequest_Action", linkedHashMap);
    }

    @Analytics(name = "PrefetchSmartReplyRequest", type = Analytics.Type.RESULT)
    public void sendPrefetchSmartReplyAnalyticsResult(@Analytics.Param(name = "count") String str, @Analytics.Param(name = "status") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("count", String.valueOf(str));
        linkedHashMap.put("status", String.valueOf(str2));
        this.mEventLogger.logEvent("PrefetchSmartReplyRequest_Result", linkedHashMap);
    }

    @Analytics(name = "OnPromoPortalByCalendarClicked", type = Analytics.Type.ACTION)
    public void sendPromotePortalOnClickCalendarItem(@Analytics.Param(name = "withPromoDialog") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("withPromoDialog", String.valueOf(z10));
        this.mEventLogger.logEvent("OnPromoPortalByCalendarClicked_Action", linkedHashMap);
    }

    @Analytics(name = "OnPromoPortalByTodoClicked", type = Analytics.Type.ACTION)
    public void sendPromotePortalOnClickTodoItem(@Analytics.Param(name = "withPromoDialog") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("withPromoDialog", String.valueOf(z10));
        this.mEventLogger.logEvent("OnPromoPortalByTodoClicked_Action", linkedHashMap);
    }

    @Analytics(name = "PromotePush_Open", type = Analytics.Type.EVENT)
    public void sendPromotePushOpened(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("PromotePush_Open_Event", linkedHashMap);
    }

    @Analytics(name = "PromotePush_Receive", type = Analytics.Type.EVENT)
    public void sendPromotePushReceived(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("PromotePush_Receive_Event", linkedHashMap);
    }

    @Analytics(name = "PushReceived", type = Analytics.Type.EVENT)
    public void sendPushAnalytics(@Analytics.Param(name = "type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", String.valueOf(str));
        this.mEventLogger.logEvent("PushReceived_Event", linkedHashMap);
    }

    @Analytics(name = "pushReceivedButNotShow", type = Analytics.Type.ERROR)
    public void sendPushReceivedButNotShow(@Analytics.Param(name = "Error") String str, @Analytics.Param(name = "isReminder") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        linkedHashMap.put("isReminder", String.valueOf(z10));
        this.mEventLogger.logEvent("pushReceivedButNotShow_Error", linkedHashMap);
    }

    @Analytics(name = "Push_Register_Fail", type = Analytics.Type.EVENT)
    public void sendPushRegisterFailAnalytics(@Analytics.Param(name = AnalyticsConstants.KEY.MSG) Exception exc) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(AnalyticsConstants.KEY.MSG, String.valueOf(exc));
        this.mEventLogger.logEvent("Push_Register_Fail_Event", linkedHashMap);
    }

    @Analytics(name = "Push_Register_Success", type = Analytics.Type.EVENT)
    public void sendPushRegisterSuccessAnalytics() {
        this.mEventLogger.logEvent("Push_Register_Success_Event", new LinkedHashMap());
    }

    @Analytics(name = "Push_Unregister_Fail", type = Analytics.Type.EVENT)
    public void sendPushUnregisterFailAnalytics(@Analytics.Param(name = AnalyticsConstants.KEY.MSG) Exception exc) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(AnalyticsConstants.KEY.MSG, String.valueOf(exc));
        this.mEventLogger.logEvent("Push_Unregister_Fail_Event", linkedHashMap);
    }

    @Analytics(name = "Push_Unregister_Success", type = Analytics.Type.EVENT)
    public void sendPushUnregisterSuccessAnalytics() {
        this.mEventLogger.logEvent("Push_Unregister_Success_Event", new LinkedHashMap());
    }

    @Analytics(name = "SwipeTutorial", type = Analytics.Type.ACTION)
    public void sendQuickActionAnalyticEvent(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("SwipeTutorial_Action", linkedHashMap);
    }

    @Analytics(name = "MessageList_Quick", params = {@Analytics.Param(name = "Action", value = "Show")}, type = Analytics.Type.ACTION)
    public void sendQuickActionsOpenedAnalytics(@Analytics.Param(name = "Thread") boolean z10, @Analytics.Param(name = MailBoxFolder.COL_NAME_META_THREAD) String str, MailListDateInfo mailListDateInfo) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Show");
        linkedHashMap.put("Thread", String.valueOf(z10));
        linkedHashMap.put(MailBoxFolder.COL_NAME_META_THREAD, String.valueOf(str));
        linkedHashMap.put("DepartureDate", mailListDateInfo.getDepartureDate());
        linkedHashMap.put("DepartureDiffDay", mailListDateInfo.getDepartureDiffDay());
        linkedHashMap.put("DepartureDiffLetters", mailListDateInfo.getDepartureDiffLetters());
        this.mEventLogger.logEvent("MessageList_Quick_Action", linkedHashMap);
    }

    @Analytics(name = "RequestDuration", type = Analytics.Type.EVENT)
    public void sendRequestDurationAnalytics(@Analytics.Param(name = "durationMilliseconds") String str, @Analytics.Param(name = ru.mail.kotlett.services.billing.analytics.Event.Companion.Network.Fail.REQUEST_TAG) String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("durationMilliseconds", String.valueOf(str));
        linkedHashMap.put(ru.mail.kotlett.services.billing.analytics.Event.Companion.Network.Fail.REQUEST_TAG, String.valueOf(str2));
        this.mEventLogger.logEvent("RequestDuration_Event", linkedHashMap);
    }

    @Analytics(name = "RestoreNotificationClicked", type = Analytics.Type.ACTION)
    public void sendRestoreAuthFlowAnalytic(@Analytics.Param(name = "RestoreType") String str, @Analytics.Param(name = "IsRestore") String str2, @Analytics.Param(name = "HasActiveAcc") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("RestoreType", String.valueOf(str));
        linkedHashMap.put("IsRestore", String.valueOf(str2));
        linkedHashMap.put("HasActiveAcc", String.valueOf(str3));
        this.mEventLogger.logEvent("RestoreNotificationClicked_Action", linkedHashMap);
    }

    @Analytics(name = "RestoreScheduled", type = Analytics.Type.ACTION)
    public void sendRestoreScheduledAnalytic(@Analytics.Param(name = "TimeTillNotify") String str, @Analytics.Param(name = "RestoreType") String str2, @Analytics.Param(name = "IsRestore") String str3, @Analytics.Param(name = "HasActiveAcc") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("TimeTillNotify", String.valueOf(str));
        linkedHashMap.put("RestoreType", String.valueOf(str2));
        linkedHashMap.put("IsRestore", String.valueOf(str3));
        linkedHashMap.put("HasActiveAcc", String.valueOf(str4));
        this.mEventLogger.logEvent("RestoreScheduled_Action", linkedHashMap);
    }

    @Analytics(name = "Right_QuickActionUse", type = Analytics.Type.ACTION)
    public void sendRightQuickAction(@Analytics.Param(name = "action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        this.mEventLogger.logEvent("Right_QuickActionUse_Action", linkedHashMap);
    }

    @Analytics(name = "Right_QuickActionStart", type = Analytics.Type.ACTION)
    public void sendRightQuickActionStart() {
        this.mEventLogger.logEvent("Right_QuickActionStart_Action", new LinkedHashMap());
    }

    @Analytics(name = "ScopeTimeSpent", type = Analytics.Type.ACTION)
    public void sendScopeTimeSpent(@Analytics.Param(name = "scope_name") String str, @Analytics.Param(name = "application_start_timestamp") long j10, @Analytics.Param(name = "start_timestamp") long j11, @Analytics.Param(name = "logical_duration_sec") long j12, @Analytics.Param(name = "launch_timeout") long j13, @Analytics.Param(name = "sessions_cnt") int i10, @Analytics.Param(name = "app_mode") String str2, @Analytics.Param(name = "session_saved_by_timer") Boolean bool) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("scope_name", String.valueOf(str));
        linkedHashMap.put("application_start_timestamp", String.valueOf(j10));
        linkedHashMap.put("start_timestamp", String.valueOf(j11));
        linkedHashMap.put("logical_duration_sec", String.valueOf(j12));
        linkedHashMap.put("launch_timeout", String.valueOf(j13));
        linkedHashMap.put("sessions_cnt", String.valueOf(i10));
        linkedHashMap.put("app_mode", String.valueOf(str2));
        linkedHashMap.put("session_saved_by_timer", String.valueOf(bool));
        this.mEventLogger.logEvent("ScopeTimeSpent_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Notifications", type = Analytics.Type.VIEW)
    public void sendSettingsNotificationsAnalyticEvent(@Analytics.Param(name = "notifications") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("notifications", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_Notifications_View", linkedHashMap);
    }

    @Analytics(name = "Social_Links", params = {@Analytics.Param(name = "Type", value = "Email"), @Analytics.Param(name = "Action", value = "Send")}, type = Analytics.Type.ACTION)
    public void sendSharingRequestEmail() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Email");
        linkedHashMap.put("Action", "Send");
        this.mEventLogger.logEvent("Social_Links_Action", linkedHashMap);
    }

    @Analytics(name = "Social_Links", params = {@Analytics.Param(name = "Type", value = "Sms"), @Analytics.Param(name = "Action", value = "Send")}, type = Analytics.Type.ACTION)
    public void sendSharingRequestSms() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Sms");
        linkedHashMap.put("Action", "Send");
        this.mEventLogger.logEvent("Social_Links_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplyPush", type = Analytics.Type.VIEW)
    public void sendSmartReplyPushAnalytics(@Analytics.Param(name = "stage") String str, @Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stage", String.valueOf(str));
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplyPush_View", linkedHashMap);
    }

    @Analytics(name = "ShowSendingNotification", type = Analytics.Type.STATE)
    public void sendStartSending(@Analytics.Param(name = "sendType") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sendType", String.valueOf(str));
        this.mEventLogger.logEvent("ShowSendingNotification_State", linkedHashMap);
    }

    @Analytics(name = "NotificationsDisable", params = {@Analytics.Param(name = "from", value = "system")}, type = Analytics.Type.EVENT)
    public void sendSystemNotificationsDisableEvent(@Analytics.Param(name = "firstUsage") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", "system");
        linkedHashMap.put("firstUsage", String.valueOf(z10));
        this.mEventLogger.logEvent("NotificationsDisable_Event", linkedHashMap);
    }

    @Analytics(name = "NotificationsEnabled", params = {@Analytics.Param(name = "from", value = "system")}, type = Analytics.Type.EVENT)
    public void sendSystemNotificationsEnableEvent(@Analytics.Param(name = "firstUsage") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", "system");
        linkedHashMap.put("firstUsage", String.valueOf(z10));
        this.mEventLogger.logEvent("NotificationsEnabled_Event", linkedHashMap);
    }

    @Analytics(name = "Theme_Enable", type = Analytics.Type.EVENT)
    public void sendThemeEnabled(@Analytics.Param(name = "enable") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("enable", String.valueOf(z10));
        this.mEventLogger.logEvent("Theme_Enable_Event", linkedHashMap);
    }

    @Analytics(name = "Threads", params = {@Analytics.Param(name = "Event", value = "ServerRequest_GetMore")}, type = Analytics.Type.EVENT)
    public void sendThreadsGetMoreAnalytics() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", "ServerRequest_GetMore");
        this.mEventLogger.logEvent("Threads_Event", linkedHashMap);
    }

    @Analytics(name = "Threads", params = {@Analytics.Param(name = "Event", value = "ServerRequest_Update")}, type = Analytics.Type.EVENT)
    public void sendThreadsUpdateAnalytics() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Event", "ServerRequest_Update");
        this.mEventLogger.logEvent("Threads_Event", linkedHashMap);
    }

    @Analytics(name = "EditorUnsubscribe", type = Analytics.Type.ACTION)
    public void sendUnsubscribeAnalytics() {
        this.mEventLogger.logEvent("EditorUnsubscribe_Action", new LinkedHashMap());
    }

    @Analytics(name = "UserAppReport", type = Analytics.Type.EVENT)
    public void sendUserAppReport() {
        this.mEventLogger.logEvent("UserAppReport_Event", new LinkedHashMap());
    }

    @Analytics(name = "UserSessionCompleteV2", type = Analytics.Type.EVENT)
    public void sendUserSessionEventAnalytics(@Analytics.Param(name = "errors_count") String str, @Analytics.Param(name = "app_crashes") String str2, @Analytics.Param(name = "api_rx") String str3, @Analytics.Param(name = "api_tx") String str4, @Analytics.Param(name = "api_tx_send_v2") String str5, @Analytics.Param(name = "ad_slot_rx_v2") String str6, @Analytics.Param(name = "ad_slot_tx") String str7, @Analytics.Param(name = "ad_link_rx") String str8, @Analytics.Param(name = "ad_link_tx") String str9, @Analytics.Param(name = "app_traffic_rx_v2") String str10, @Analytics.Param(name = "app_traffic_tx_v2") String str11, @Analytics.Param(name = "last_crash_reason_id") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("errors_count", String.valueOf(str));
        linkedHashMap.put("app_crashes", String.valueOf(str2));
        linkedHashMap.put("api_rx", String.valueOf(str3));
        linkedHashMap.put("api_tx", String.valueOf(str4));
        linkedHashMap.put("api_tx_send_v2", String.valueOf(str5));
        linkedHashMap.put("ad_slot_rx_v2", String.valueOf(str6));
        linkedHashMap.put("ad_slot_tx", String.valueOf(str7));
        linkedHashMap.put("ad_link_rx", String.valueOf(str8));
        linkedHashMap.put("ad_link_tx", String.valueOf(str9));
        linkedHashMap.put("app_traffic_rx_v2", String.valueOf(str10));
        linkedHashMap.put("app_traffic_tx_v2", String.valueOf(str11));
        linkedHashMap.put("last_crash_reason_id", String.valueOf(i10));
        this.mEventLogger.logEvent("UserSessionCompleteV2_Event", linkedHashMap);
    }

    @Analytics(name = "SearchUserStartSearch", params = {@Analytics.Param(name = "requestFrom", value = "contactInfo")}, type = Analytics.Type.ACTION)
    public void sendUserStartSearch(@Analytics.Param(name = MailCsatEventParam.TAB) String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "attach") boolean z11, @Analytics.Param(name = "date") String str2, @Analytics.Param(name = "folder") boolean z12, @Analytics.Param(name = "category") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("requestFrom", "contactInfo");
        linkedHashMap.put(MailCsatEventParam.TAB, String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("attach", String.valueOf(z11));
        linkedHashMap.put("date", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(z12));
        linkedHashMap.put("category", String.valueOf(str3));
        this.mEventLogger.logEvent("SearchUserStartSearch_Action", linkedHashMap);
    }

    @Analytics(name = "MessageListScroll", type = Analytics.Type.EVENT)
    public void sendViewedMessagesCountAnalytic(@Analytics.Param(name = "messagesScrolled") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("messagesScrolled", String.valueOf(str));
        this.mEventLogger.logEvent("MessageListScroll_Event", linkedHashMap);
    }

    @Analytics(name = "VkCountersClicked", type = Analytics.Type.ACTION)
    public void sendVkCounterClickedAnalytics(@Analytics.Param(name = "vk_message_counter_shown") boolean z10, @Analytics.Param(name = "vk_message_counter_value") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("vk_message_counter_shown", String.valueOf(z10));
        linkedHashMap.put("vk_message_counter_value", String.valueOf(i10));
        this.mEventLogger.logEvent("VkCountersClicked_Action", linkedHashMap);
    }

    @Analytics(name = "VkCountersShown", type = Analytics.Type.VIEW)
    public void sendVkCounterShownAnalytics(@Analytics.Param(name = "vk_message_counter_shown") boolean z10, @Analytics.Param(name = "vk_message_counter_value") int i10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("vk_message_counter_shown", String.valueOf(z10));
        linkedHashMap.put("vk_message_counter_value", String.valueOf(i10));
        this.mEventLogger.logEvent("VkCountersShown_View", linkedHashMap);
    }

    @Analytics(name = "WalletPushClicked", type = Analytics.Type.ACTION)
    public void sendWalletPushClicked(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "ml_type") String str2, @Analytics.Param(name = "ml_subtype") String str3, @Analytics.Param(name = "ml_reminder") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("ml_type", String.valueOf(str2));
        linkedHashMap.put("ml_subtype", String.valueOf(str3));
        linkedHashMap.put("ml_reminder", String.valueOf(str4));
        this.mEventLogger.logEvent("WalletPushClicked_Action", linkedHashMap);
    }

    @Analytics(name = "WalletPushClicked", type = Analytics.Type.ERROR)
    public void sendWalletPushClickedError(@Analytics.Param(name = "ErrorMessage") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ErrorMessage", String.valueOf(str));
        this.mEventLogger.logEvent("WalletPushClicked_Error", linkedHashMap);
    }

    @Analytics(name = "WalletPush", type = Analytics.Type.VIEW)
    public void sendWalletPushShow(@Analytics.Param(name = "event_uid") String str, @Analytics.Param(name = "ml_type") String str2, @Analytics.Param(name = "ml_subtype") String str3, @Analytics.Param(name = "ml_reminder") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("event_uid", String.valueOf(str));
        linkedHashMap.put("ml_type", String.valueOf(str2));
        linkedHashMap.put("ml_subtype", String.valueOf(str3));
        linkedHashMap.put("ml_reminder", String.valueOf(str4));
        this.mEventLogger.logEvent("WalletPush_View", linkedHashMap);
    }

    @Analytics(name = "SentWithoutSmartReply", type = Analytics.Type.ACTION)
    public void sentWithoutSmartReplyAction(@Analytics.Param(name = "beenViewed") boolean z10, @Analytics.Param(name = "stage") boolean z11, @Analytics.Param(name = "isDefault") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("beenViewed", String.valueOf(z10));
        linkedHashMap.put("stage", String.valueOf(z11));
        linkedHashMap.put("isDefault", String.valueOf(z12));
        this.mEventLogger.logEvent("SentWithoutSmartReply_Action", linkedHashMap);
    }

    @Analytics(name = "SentWithoutSmartReplyStage", type = Analytics.Type.ACTION)
    public void sentWithoutSmartReplyStageAction(@Analytics.Param(name = "beenViewed") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("beenViewed", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("SentWithoutSmartReplyStage_Action", linkedHashMap);
    }

    @Analytics(name = "SetInternetRuFromDeeplink", type = Analytics.Type.EVENT)
    public void setInternetRuFromDeeplink() {
        this.mEventLogger.logEvent("SetInternetRuFromDeeplink_Event", new LinkedHashMap());
    }

    @Analytics(name = "Settings_bcc", type = Analytics.Type.ACTION)
    public void settingsBccAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_bcc_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_Filters", type = Analytics.Type.ERROR)
    public void settingsFiltersError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_Filters_Error", linkedHashMap);
    }

    @Analytics(name = "Settings_Filters", type = Analytics.Type.VIEW)
    public void settingsFiltersView() {
        this.mEventLogger.logEvent("Settings_Filters_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings_Folders", type = Analytics.Type.ERROR)
    public void settingsFoldersError(@Analytics.Param(name = "Error") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_Folders_Error", linkedHashMap);
    }

    @Analytics(name = "Settings_Folders", type = Analytics.Type.VIEW)
    public void settingsFoldersView() {
        this.mEventLogger.logEvent("Settings_Folders_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings_Help", type = Analytics.Type.ACTION)
    public void settingsHelpAction() {
        this.mEventLogger.logEvent("Settings_Help_Action", new LinkedHashMap());
    }

    @Analytics(name = "Settings_imap_sent", type = Analytics.Type.EVENT)
    public void settingsImapSentEvent(@Analytics.Param(name = "domain") String str, @Analytics.Param(name = "value") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("domain", String.valueOf(str));
        linkedHashMap.put("value", String.valueOf(str2));
        this.mEventLogger.logEvent("Settings_imap_sent_Event", linkedHashMap);
    }

    @Analytics(name = "ChooseSettingsItem", type = Analytics.Type.ACTION)
    public void settingsItemWasChosen(@Analytics.Param(name = DivActionSpec.Scroll.PARAM_ITEM_INDEX) String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(DivActionSpec.Scroll.PARAM_ITEM_INDEX, String.valueOf(str));
        this.mEventLogger.logEvent("ChooseSettingsItem_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_NameAvatar", type = Analytics.Type.ACTION)
    public void settingsNameAvatarAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("Settings_NameAvatar_Action", linkedHashMap);
    }

    @Analytics(name = "Settings_NameAvatar", type = Analytics.Type.ERROR)
    public void settingsNameAvatarError(@Analytics.Param(name = "Error") String str, @Analytics.Param(name = "Domain") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        linkedHashMap.put("Domain", String.valueOf(str2));
        this.mEventLogger.logEvent("Settings_NameAvatar_Error", linkedHashMap);
    }

    @Analytics(name = "Settings_NameAvatar", type = Analytics.Type.VIEW)
    public void settingsNameAvatarView() {
        this.mEventLogger.logEvent("Settings_NameAvatar_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings_Signature", type = Analytics.Type.VIEW)
    public void settingsSignatureView() {
        this.mEventLogger.logEvent("Settings_Signature_View", new LinkedHashMap());
    }

    @Analytics(name = "Settings", type = Analytics.Type.VIEW)
    public void settingsView(@Analytics.Param(name = "Archive") boolean z10, @Analytics.Param(name = "Settings_Pin") boolean z11, @Analytics.Param(name = "TouchID") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Archive", String.valueOf(z10));
        linkedHashMap.put("Settings_Pin", String.valueOf(z11));
        linkedHashMap.put("TouchID", String.valueOf(z12));
        this.mEventLogger.logEvent("Settings_View", linkedHashMap);
    }

    @Analytics(name = "Sharing_Provider", type = Analytics.Type.ACTION)
    public void sharingProviderAction() {
        this.mEventLogger.logEvent("Sharing_Provider_Action", new LinkedHashMap());
    }

    @Analytics(name = "AddContact_Dialogue", type = Analytics.Type.VIEW)
    public void showAddContactDialog() {
        this.mEventLogger.logEvent("AddContact_Dialogue_View", new LinkedHashMap());
    }

    @Analytics(name = "RegistrationMycom", params = {@Analytics.Param(name = "Action", value = "AddAvatar")}, type = Analytics.Type.ACTION)
    public void showAvatarSourceDialog() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "AddAvatar");
        this.mEventLogger.logEvent("RegistrationMycom_Action", linkedHashMap);
    }

    @Analytics(name = "LoginPickGoogle", type = Analytics.Type.VIEW)
    public void showGooglePickerScreen() {
        this.mEventLogger.logEvent("LoginPickGoogle_View", new LinkedHashMap());
    }

    @Analytics(name = "Show_Images_Btn_Pressed", type = Analytics.Type.EVENT)
    public void showImagesBtnPressed(@Analytics.Param(name = "preference_state") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("preference_state", String.valueOf(str));
        this.mEventLogger.logEvent("Show_Images_Btn_Pressed_Event", linkedHashMap);
    }

    @Analytics(name = "Show_Images_Btn_Visible", type = Analytics.Type.EVENT)
    public void showImagesBtnVisible(@Analytics.Param(name = "preference_state") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("preference_state", String.valueOf(str));
        this.mEventLogger.logEvent("Show_Images_Btn_Visible_Event", linkedHashMap);
    }

    @Analytics(name = "RegistrationMycom", params = {@Analytics.Param(name = "Error", value = ImageInfo.COMPRESSION_ALGORITHM_UNKNOWN)}, type = Analytics.Type.ERROR)
    public void showUnknownError() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", ImageInfo.COMPRESSION_ALGORITHM_UNKNOWN);
        this.mEventLogger.logEvent("RegistrationMycom_Error", linkedHashMap);
    }

    @Analytics(name = "Sign_Out", type = Analytics.Type.ACTION)
    public void signOutAction() {
        this.mEventLogger.logEvent("Sign_Out_Action", new LinkedHashMap());
    }

    @Analytics(name = "Sign_Out_From_Pop_Up", type = Analytics.Type.ACTION)
    public void signOutFromPopup() {
        this.mEventLogger.logEvent("Sign_Out_From_Pop_Up_Action", new LinkedHashMap());
    }

    @Analytics(name = AuthSource.SMARTLOCK, params = {@Analytics.Param(name = "Action", value = "Login"), @Analytics.Param(name = "Success", value = "true")}, type = Analytics.Type.ACTION)
    public void smartLockActionLogin(@Analytics.Param(name = "Domain") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Login");
        linkedHashMap.put("Success", "true");
        linkedHashMap.put("Domain", String.valueOf(str));
        this.mEventLogger.logEvent("SmartLock_Action", linkedHashMap);
    }

    @Analytics(name = "SmartLock_Dialogue", type = Analytics.Type.VIEW)
    public void smartLockDialogueView() {
        this.mEventLogger.logEvent("SmartLock_Dialogue_View", new LinkedHashMap());
    }

    @Analytics(name = "SmartLockLimitExceeded", type = Analytics.Type.STATE)
    public void smartLockLimitExceededState() {
        this.mEventLogger.logEvent("SmartLockLimitExceeded_State", new LinkedHashMap());
    }

    @Analytics(name = AuthSource.SMARTLOCK, type = Analytics.Type.RESULT)
    public void smartLockResult(@Analytics.Param(name = "result") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("result", String.valueOf(str));
        this.mEventLogger.logEvent("SmartLock_Result", linkedHashMap);
    }

    @Analytics(name = "SmartReplyPush", params = {@Analytics.Param(name = "Action", value = "Click"), @Analytics.Param(name = "Type", value = "Send")}, type = Analytics.Type.ACTION)
    public void smartReplyPushAction(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Click");
        linkedHashMap.put("Type", "Send");
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("SmartReplyPush_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplyPush", params = {@Analytics.Param(name = "Action", value = "Click"), @Analytics.Param(name = "Type", value = "Edit")}, type = Analytics.Type.ACTION)
    public void smartReplyPushActionClickTypeEdit(@Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Click");
        linkedHashMap.put("Type", "Edit");
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("SmartReplyPush_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplyPushClickStage", params = {@Analytics.Param(name = "Type", value = "Edit")}, type = Analytics.Type.ACTION)
    public void smartReplyPushClickStageTypeEdit(@Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Edit");
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplyPushClickStage_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplyPushClick", params = {@Analytics.Param(name = "Type", value = "Edit")}, type = Analytics.Type.ACTION)
    public void smartReplyPushClickTypeEdit(@Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", "Edit");
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplyPushClick_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplySent", type = Analytics.Type.ACTION)
    public void smartReplySentAction(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("SmartReplySent_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplySentStage", type = Analytics.Type.ACTION)
    public void smartReplySentStageAction(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplySentStage_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplySentWithEdit", type = Analytics.Type.ACTION)
    public void smartReplySentWithEditAction(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        this.mEventLogger.logEvent("SmartReplySentWithEdit_Action", linkedHashMap);
    }

    @Analytics(name = "SmartReplySentWithEditStage", type = Analytics.Type.ACTION)
    public void smartReplySentWithEditStageAction(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "isDefault") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("isDefault", String.valueOf(z10));
        this.mEventLogger.logEvent("SmartReplySentWithEditStage_Action", linkedHashMap);
    }

    @Analytics(name = "SmileInsert", type = Analytics.Type.EVENT)
    public void smileInsertEvent() {
        this.mEventLogger.logEvent("SmileInsert_Event", new LinkedHashMap());
    }

    @Analytics(name = "SMS_Libverify", type = Analytics.Type.ACTION)
    public void smsLibverifyAction() {
        this.mEventLogger.logEvent("SMS_Libverify_Action", new LinkedHashMap());
    }

    @Analytics(name = "Social_Links", type = Analytics.Type.VIEW)
    public void socialLinksView(@Analytics.Param(name = "Type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", String.valueOf(str));
        this.mEventLogger.logEvent("Social_Links_View", linkedHashMap);
    }

    @Analytics(name = "StartAuth", type = Analytics.Type.ACTION)
    public void startAuthAction(@Analytics.Param(name = "Type") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Type", String.valueOf(str));
        this.mEventLogger.logEvent("StartAuth_Action", linkedHashMap);
    }

    @Analytics(name = "Contacts_Export_Request", type = Analytics.Type.EVENT)
    public void startExportContacts() {
        this.mEventLogger.logEvent("Contacts_Export_Request_Event", new LinkedHashMap());
    }

    @Analytics(name = "LoginXmailExtra", type = Analytics.Type.VIEW)
    public void startXmailMigration() {
        this.mEventLogger.logEvent("LoginXmailExtra_View", new LinkedHashMap());
    }

    @Analytics(name = "StickerInsert", type = Analytics.Type.EVENT)
    public void stickerInsertEvent(@Analytics.Param(name = "from") String str, @Analytics.Param(name = "PackName") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("from", String.valueOf(str));
        linkedHashMap.put("PackName", String.valueOf(str2));
        this.mEventLogger.logEvent("StickerInsert_Event", linkedHashMap);
    }

    @Analytics(name = "ThreadEdit", type = Analytics.Type.ACTION)
    public void threadEditLogAction(@Analytics.Param(name = "Action") String str, @Analytics.Param(name = "Place") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        linkedHashMap.put("Place", String.valueOf(str2));
        this.mEventLogger.logEvent("ThreadEdit_Action", linkedHashMap);
    }

    @Analytics(name = "ThreadMessages", type = Analytics.Type.VIEW)
    public void threadMessagesView(@Analytics.Param(name = "fromPush") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("fromPush", String.valueOf(z10));
        this.mEventLogger.logEvent("ThreadMessages_View", linkedHashMap);
    }

    @Analytics(name = "Threads_Checkbox", type = Analytics.Type.STATE)
    public void threadsCheckboxState(@Analytics.Param(name = "State") boolean z10) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("State", String.valueOf(z10));
        this.mEventLogger.logEvent("Threads_Checkbox_State", linkedHashMap);
    }

    @Analytics(name = "ToggleMailDarkMode", type = Analytics.Type.ACTION)
    public void toggleMailDarkMode(@Analytics.Param(name = "enable_mail_dark_theme") boolean z10, @Analytics.Param(name = "user_dark_theme_setting") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("enable_mail_dark_theme", String.valueOf(z10));
        linkedHashMap.put("user_dark_theme_setting", String.valueOf(str));
        this.mEventLogger.logEvent("ToggleMailDarkMode_Action", linkedHashMap);
    }

    @Analytics(name = "Installed_Applications", type = Analytics.Type.RESULT)
    public void trackInstalledAppsAnalytic(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("Installed_Applications_Result", linkedHashMap);
    }

    @Analytics(name = "UnsubscribeDeletePromo", type = Analytics.Type.ACTION)
    public void unsubscribeDeletePromoAction() {
        this.mEventLogger.logEvent("UnsubscribeDeletePromo_Action", new LinkedHashMap());
    }

    @Analytics(name = "UnsubscribeDeletePromo", type = Analytics.Type.RESULT)
    public void unsubscribeDeletePromoResult(@Analytics.Param(name = "code") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("code", String.valueOf(str));
        this.mEventLogger.logEvent("UnsubscribeDeletePromo_Result", linkedHashMap);
    }

    @Analytics(name = "UpdateCredentials", type = Analytics.Type.EVENT)
    public void updateCredentialsAnalytics(@Analytics.Param(name = "domain") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("domain", String.valueOf(str));
        this.mEventLogger.logEvent("UpdateCredentials_Event", linkedHashMap);
    }

    @Analytics(name = "UserOptOut", type = Analytics.Type.ACTION)
    public void userOptOutAction(@Analytics.Param(name = "domain") String str, @Analytics.Param(name = "Action") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("domain", String.valueOf(str));
        linkedHashMap.put("Action", String.valueOf(str2));
        this.mEventLogger.logEvent("UserOptOut_Action", linkedHashMap);
    }

    @Analytics(name = "UserOptOut", type = Analytics.Type.VIEW)
    public void userOptOutView() {
        this.mEventLogger.logEvent("UserOptOut_View", new LinkedHashMap());
    }

    @Analytics(name = "WebView_Auth_Screen_Loading", type = Analytics.Type.RESULT)
    public void webViewAuthScreenLoading(@Analytics.Param(name = AnalyticsConstants.KEY.STATUS) String str, @Analytics.Param(name = "Error_Message") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(AnalyticsConstants.KEY.STATUS, String.valueOf(str));
        linkedHashMap.put("Error_Message", String.valueOf(str2));
        this.mEventLogger.logEvent("WebView_Auth_Screen_Loading_Result", linkedHashMap);
    }

    @Analytics(name = "WebViewCreatingError", type = Analytics.Type.EVENT)
    public void webViewCreatingError() {
        this.mEventLogger.logEvent("WebViewCreatingError_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebViewCreatingErrorToastShowed", type = Analytics.Type.EVENT)
    public void webViewCreatingErrorToastShowed() {
        this.mEventLogger.logEvent("WebViewCreatingErrorToastShowed_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebView_Inflate", type = Analytics.Type.ERROR)
    public void webViewInflateError() {
        this.mEventLogger.logEvent("WebView_Inflate_Error", new LinkedHashMap());
    }

    @Analytics(name = "WebViewUpdateDialogCancelled", type = Analytics.Type.EVENT)
    public void webViewUpdateDialogCancelled() {
        this.mEventLogger.logEvent("WebViewUpdateDialogCancelled_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebViewUpdateDialogNegativeButton", type = Analytics.Type.EVENT)
    public void webViewUpdateDialogNegativeButtonClicked() {
        this.mEventLogger.logEvent("WebViewUpdateDialogNegativeButton_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebViewUpdateDialogNeutralButton", type = Analytics.Type.EVENT)
    public void webViewUpdateDialogNeutralButtonClicked() {
        this.mEventLogger.logEvent("WebViewUpdateDialogNeutralButton_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebViewUpdateDialogPositiveButton", type = Analytics.Type.EVENT)
    public void webViewUpdateDialogPositiveButtonClicked() {
        this.mEventLogger.logEvent("WebViewUpdateDialogPositiveButton_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebViewUpdateDialogShowed", type = Analytics.Type.EVENT)
    public void webViewUpdateDialogShowed() {
        this.mEventLogger.logEvent("WebViewUpdateDialogShowed_Event", new LinkedHashMap());
    }

    @Analytics(name = "WebviewInterface_error", type = Analytics.Type.EVENT)
    public void webviewInterfaceError(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("WebviewInterface_error_Event", linkedHashMap);
    }

    @Analytics(name = "WebviewInterface_log", type = Analytics.Type.EVENT)
    public void webviewInterfaceLog(@Analytics.Param Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(map);
        this.mEventLogger.logEvent("WebviewInterface_log_Event", linkedHashMap);
    }

    @Analytics(name = "Welcome", type = Analytics.Type.VIEW)
    public void welcomeView() {
        this.mEventLogger.logEvent("Welcome_View", new LinkedHashMap());
    }

    public MailAnalytics() {
        this.mEventLogger = new DummyEventLogger();
    }

    @Analytics(name = "MRSDKAuth", type = Analytics.Type.EVENT)
    public void logCodeExchangeResult(@Analytics.Param(name = "app") String str, @Analytics.Param(name = "grantType") String str2, @Analytics.Param(name = "CodeExchangeResult") String str3) {
        String str4 = str + BundleUtil.UNDERLINE_TAG + str2 + "_MRSDKAuth_Event";
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("CodeExchangeResult", String.valueOf(str3));
        this.mEventLogger.logEvent(str4, linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Action", value = "Leave")}, type = Analytics.Type.VIEW)
    public void registrationViewActionLeave(@Analytics.Param(name = "RegForm") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Leave");
        linkedHashMap.put("RegForm", String.valueOf(str));
        this.mEventLogger.logEvent("Registration_View", linkedHashMap);
    }

    @Analytics(name = "Push", params = {@Analytics.Param(name = "Action", value = "Click")}, type = Analytics.Type.ACTION)
    public void sendPushAnalytics(@Analytics.Param(name = "hasSmartReplies") String str, @Analytics.Param(name = "stage") boolean z10, @Analytics.Param(name = "isDefault") boolean z11, @Analytics.Param(name = "pushType") String str2, @Analytics.Param(name = "category") String str3, @Analytics.Param(name = "isReminder") boolean z12) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "Click");
        linkedHashMap.put("hasSmartReplies", String.valueOf(str));
        linkedHashMap.put("stage", String.valueOf(z10));
        linkedHashMap.put("isDefault", String.valueOf(z11));
        linkedHashMap.put("pushType", String.valueOf(str2));
        linkedHashMap.put("category", String.valueOf(str3));
        linkedHashMap.put("isReminder", String.valueOf(z12));
        this.mEventLogger.logEvent("Push_Action", linkedHashMap);
    }

    @Analytics(name = "ContactNotification", type = Analytics.Type.ACTION)
    public void contactNotificationAction(@Analytics.Param(name = "Action") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", String.valueOf(str));
        this.mEventLogger.logEvent("ContactNotification_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "signin")}, type = Analytics.Type.ACTION)
    public void loginActionSignIn(@Analytics.Param(name = "step") String str, @Analytics.Param(name = "from") String str2, @Analytics.Param(name = "login_email") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "signin");
        linkedHashMap.put("step", String.valueOf(str));
        linkedHashMap.put("from", str2);
        linkedHashMap.put("login_email", str3);
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "Login", type = Analytics.Type.ERROR)
    public void loginError(@Analytics.Param(name = "Error") String str, @Analytics.Param(name = "step") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Error", String.valueOf(str));
        linkedHashMap.put("step", String.valueOf(str2));
        this.mEventLogger.logEvent("Login_Error", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.VIEW)
    public void metaThreadToolbarView(@Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str, @Analytics.Param(name = "folder") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str));
        linkedHashMap.put("folder", String.valueOf(str2));
        this.mEventLogger.logEvent("MetaThreadToolbar_View", linkedHashMap);
    }

    @Analytics(name = "ShrinkFail", type = Analytics.Type.RESULT)
    public void reportShrinkFail(@Analytics.Param(name = "FailType") String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("FailType", String.valueOf(str));
        this.mEventLogger.logEvent("ShrinkFail_Result", linkedHashMap);
    }

    @Analytics(name = "UserOptOut", type = Analytics.Type.ACTION)
    public void userOptOutAction() {
        this.mEventLogger.logEvent("UserOptOut_Action", new LinkedHashMap());
    }

    @Analytics(name = "LetterReminderDialog", params = {@Analytics.Param(name = DivActionSpec.Scroll.PARAM_ITEM_INDEX, value = "date")}, type = Analytics.Type.ACTION)
    public void letterReminderDialogAction(@Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_CURRENT_TIME) String str, @Analytics.Param(name = "currentDayOfWeek") String str2, @Analytics.Param(name = "reminderTime") String str3, @Analytics.Param(name = "reminderDate") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(DivActionSpec.Scroll.PARAM_ITEM_INDEX, "date");
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, String.valueOf(str));
        linkedHashMap.put("currentDayOfWeek", String.valueOf(str2));
        linkedHashMap.put("reminderTime", String.valueOf(str3));
        linkedHashMap.put("reminderDate", String.valueOf(str4));
        this.mEventLogger.logEvent("LetterReminderDialog_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "RestorePassword")}, type = Analytics.Type.ACTION)
    public void loginActionRestorePassword(@Analytics.Param(name = "passwError") boolean z10, @Analytics.Param(name = "withemail") boolean z11, @Analytics.Param(name = "step") String str, @Analytics.Param(name = "login_email") String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "RestorePassword");
        linkedHashMap.put("passwError", String.valueOf(z10));
        linkedHashMap.put("withemail", String.valueOf(z11));
        linkedHashMap.put("step", String.valueOf(str));
        linkedHashMap.put("login_email", str2);
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.ACTION)
    public void metaThreadToolbarAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = AnalyticsUtilsExtKt.FILTER_UNREAD) boolean z10, @Analytics.Param(name = "screen") String str2, @Analytics.Param(name = "folder") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put(AnalyticsUtilsExtKt.FILTER_UNREAD, String.valueOf(z10));
        linkedHashMap.put("screen", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(str3));
        this.mEventLogger.logEvent("MetaThreadToolbar_Action", linkedHashMap);
    }

    @Analytics(name = "Login", params = {@Analytics.Param(name = "Action", value = "signin")}, type = Analytics.Type.ACTION)
    public void loginActionSignIn(@Analytics.Param(name = "step") String str, @Analytics.Param(name = "isButtonLocked") boolean z10, @Analytics.Param(name = "from") String str2, @Analytics.Param(name = "login_email") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Action", "signin");
        linkedHashMap.put("step", String.valueOf(str));
        linkedHashMap.put("isButtonLocked", String.valueOf(z10));
        linkedHashMap.put("from", str2);
        linkedHashMap.put("login_email", str3);
        this.mEventLogger.logEvent("Login_Action", linkedHashMap);
    }

    @Analytics(name = "LetterReminder", type = Analytics.Type.ACTION)
    public void letterReminderAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "place") String str2, @Analytics.Param(name = RbParams.Default.URL_PARAM_KEY_CURRENT_TIME) String str3, @Analytics.Param(name = "currentDayOfWeek") String str4) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("place", String.valueOf(str2));
        linkedHashMap.put(RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, String.valueOf(str3));
        linkedHashMap.put("currentDayOfWeek", String.valueOf(str4));
        this.mEventLogger.logEvent("LetterReminder_Action", linkedHashMap);
    }

    @Analytics(name = "MetaThreadToolbar", type = Analytics.Type.ACTION)
    public void metaThreadToolbarAction(@Analytics.Param(name = "action") String str, @Analytics.Param(name = "screen") String str2, @Analytics.Param(name = "folder") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", String.valueOf(str));
        linkedHashMap.put("screen", String.valueOf(str2));
        linkedHashMap.put("folder", String.valueOf(str3));
        this.mEventLogger.logEvent("MetaThreadToolbar_Action", linkedHashMap);
    }

    @Analytics(name = "Registration", params = {@Analytics.Param(name = "Result", value = "RegistrationSuccess"), @Analytics.Param(name = "Place", value = "RegistrationCode")}, type = Analytics.Type.RESULT)
    public void registrationResultSuccess(@Analytics.Param(name = "RegForm") String str, @Analytics.Param(name = "RegEmail") String str2, @Analytics.Param(name = "is_migrated") boolean z10, @Analytics.Param(name = "migration_from") String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Result", "RegistrationSuccess");
        linkedHashMap.put("Place", "RegistrationCode");
        linkedHashMap.put("RegForm", String.valueOf(str));
        linkedHashMap.put("RegEmail", String.valueOf(str2));
        linkedHashMap.put("is_migrated", String.valueOf(z10));
        linkedHashMap.put("migration_from", String.valueOf(str3));
        this.mEventLogger.logEvent("Registration_Result", linkedHashMap);
    }
}
