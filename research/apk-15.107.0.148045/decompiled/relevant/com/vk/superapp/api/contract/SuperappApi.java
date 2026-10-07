package com.vk.superapp.api.contract;

import android.location.Location;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.gson.JsonObject;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.generated.account.dto.AccountGetProfilesSwitcherInfoResponseDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationGetSessionInfoPlatformDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationGetSessionInfoResponseDto;
import com.vk.api.generated.apps.dto.AppsGetTrackBridgeCallHandlersResponseDto;
import com.vk.api.generated.apps.dto.AppsNeedToShowActionPlaceIdDto;
import com.vk.api.generated.apps.dto.AppsStartCallResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckBindExtOAuthResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeSilentTokenToSidResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeTokenInfoDto;
import com.vk.api.generated.auth.dto.AuthExternalFlowOutResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthGetExchangeTokenResponseDto;
import com.vk.api.generated.auth.dto.AuthOnSuccessValidationResponseDto;
import com.vk.api.generated.auth.dto.AuthRefreshTokensResponseDto;
import com.vk.api.generated.auth.dto.AuthSetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthSilentTokenShortDto;
import com.vk.api.generated.auth.dto.AuthTerminateAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateEmailResponseDto;
import com.vk.api.generated.base.dto.BaseBoolIntDto;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckOtpResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckOtpVerificationMethodDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckPhoneReuseResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetMaxSessionStatusResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetValidationStatusResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetVerificationMethodsResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemSendOtpResponseDto;
import com.vk.api.generated.esia.dto.EsiaCheckEsiaLinkResponseDto;
import com.vk.api.generated.esia.dto.EsiaGetEsiaUserInfoResponseDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersGoodItemDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersNewOrderItemDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersOrderItemDto;
import com.vk.api.generated.healthCommon.dto.HealthCommonClientConfigDto;
import com.vk.api.generated.orders.dto.OrdersOrderDto;
import com.vk.api.generated.orders.dto.OrdersSubscriptionDto;
import com.vk.api.generated.stats.dto.StatsTrackVisitorTypeDto;
import com.vk.api.generated.translations.dto.TranslationsTranslateResponseDto;
import com.vk.api.generated.users.dto.UsersFieldsDto;
import com.vk.api.generated.users.dto.UsersUserFullDto;
import com.vk.api.generated.vkStart.dto.VkStartGetStatsActivityTypeDto;
import com.vk.api.generated.vkStart.dto.VkStartGetStatsAggregationTypeDto;
import com.vk.api.generated.vkStart.dto.VkStartStatsListItemDto;
import com.vk.api.generated.vkidmail.dto.VkidmailBindListResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailExchangeTokenResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailLinkedEmailsResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokCheckPasswordResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokCheckPersonalInfoResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokExternalItsMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokExternalItsNotMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokInternalItsMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokInternalItsNotMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokStartRegistrationResponseDto;
import com.vk.auth.api.models.AuthResult;
import com.vk.auth.verification.base.BaseCheckFragment;
import com.vk.dto.common.id.UserId;
import com.vk.external.miniapp.net.ad.AdvertisementConfig;
import com.vk.external.miniapp.net.app.AppFields;
import com.vk.external.miniapp.net.app.WebApiApplication;
import com.vk.external.miniapp.net.vkrun.StepCounterInfo;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.VkRelation;
import com.vk.superapp.api.dto.account.AccountAnonymousToggles;
import com.vk.superapp.api.dto.account.AccountCheckPasswordResponse;
import com.vk.superapp.api.dto.account.AccountSignedResponse;
import com.vk.superapp.api.dto.account.ProfileNavigationInfo;
import com.vk.superapp.api.dto.account.ProfileShortInfo;
import com.vk.superapp.api.dto.app.ActionMenuApps;
import com.vk.superapp.api.dto.app.AppAdvertisementConfig;
import com.vk.superapp.api.dto.app.AppIntent;
import com.vk.superapp.api.dto.app.AppLaunchParams;
import com.vk.superapp.api.dto.app.AppLifecycleEvent;
import com.vk.superapp.api.dto.app.AppPermissions;
import com.vk.superapp.api.dto.app.AppsGroupsContainer;
import com.vk.superapp.api.dto.app.AppsSearchResponse;
import com.vk.superapp.api.dto.app.AppsSecretHash;
import com.vk.superapp.api.dto.app.AppsSection;
import com.vk.superapp.api.dto.app.AutoBuyStatus;
import com.vk.superapp.api.dto.app.GameSubscription;
import com.vk.superapp.api.dto.app.ResolvingResult;
import com.vk.superapp.api.dto.app.WebAppActivities;
import com.vk.superapp.api.dto.app.WebAppEmbeddedUrl;
import com.vk.superapp.api.dto.app.WebGameLeaderboard;
import com.vk.superapp.api.dto.app.WebOrder;
import com.vk.superapp.api.dto.app.WebOrderInfo;
import com.vk.superapp.api.dto.app.catalog.AppsCatalogSectionsResponse;
import com.vk.superapp.api.dto.app.catalog.section.AppsCategory;
import com.vk.superapp.api.dto.auth.AuthSupportedWay;
import com.vk.superapp.api.dto.auth.CheckAccessResponse;
import com.vk.superapp.api.dto.auth.GetUserInfoByPhone;
import com.vk.superapp.api.dto.auth.InitPasswordCheckAccessFactor;
import com.vk.superapp.api.dto.auth.InitPasswordCheckResponse;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.api.dto.auth.VkAuthAppScope;
import com.vk.superapp.api.dto.auth.VkAuthExtendedSilentToken;
import com.vk.superapp.api.dto.auth.VkAuthGetContinuationForServiceResponse;
import com.vk.superapp.api.dto.auth.VkAuthHashes;
import com.vk.superapp.api.dto.auth.VkAuthSignUpResult;
import com.vk.superapp.api.dto.auth.VkAuthValidatePhoneInfo;
import com.vk.superapp.api.dto.auth.VkAuthValidatePhoneResult;
import com.vk.superapp.api.dto.auth.VkAuthValidateSuperappTokenResponse;
import com.vk.superapp.api.dto.auth.VkConnectRemoteConfig;
import com.vk.superapp.api.dto.auth.VkEsiaSignature;
import com.vk.superapp.api.dto.auth.appcredentials.VkAuthAppCredentials;
import com.vk.superapp.api.dto.auth.autologin.VkAuthAutologinCredentials;
import com.vk.superapp.api.dto.auth.exchangetokeninfo.VkAuthExchangeTokenInfo;
import com.vk.superapp.api.dto.auth.serviceauthmulti.AuthGetCredentialsForServiceMultiResponseModel;
import com.vk.superapp.api.dto.auth.silentauthprovider.AuthSilentAuthProvider;
import com.vk.superapp.api.dto.auth.validateaccount.VkAuthValidateAccountResponse;
import com.vk.superapp.api.dto.auth.validatelogin.VkAuthValidateLoginResponse;
import com.vk.superapp.api.dto.auth.validatephonecheck.AuthValidatePhoneCheckResponse;
import com.vk.superapp.api.dto.auth.validatephoneconfirm.VkAuthConfirmResponse;
import com.vk.superapp.api.dto.auth.vkidmail.VkidMailCheckRestoreResponseDto;
import com.vk.superapp.api.dto.auth.vkidmail.model.MailCheckPasswordResult;
import com.vk.superapp.api.dto.auth.vkidmail.model.MailSilentTokenResultDto;
import com.vk.superapp.api.dto.auth.vkidmail.userblocked.model.VkIDMailUserBlockStatusResult;
import com.vk.superapp.api.dto.birthday.SuperAppBirthdayResponse;
import com.vk.superapp.api.dto.common.VkList;
import com.vk.superapp.api.dto.email.EmailCreationResponse;
import com.vk.superapp.api.dto.esia.EsiaCheckEsiaLinkFlow;
import com.vk.superapp.api.dto.geo.GeoServicesConfig;
import com.vk.superapp.api.dto.geo.GeoServicesMethodVersion;
import com.vk.superapp.api.dto.geo.Position;
import com.vk.superapp.api.dto.geo.coder.GeoCoderExtra;
import com.vk.superapp.api.dto.geo.coder.GeoCodingResponse;
import com.vk.superapp.api.dto.geo.directions.DirectionsRequest;
import com.vk.superapp.api.dto.geo.directions.DirectionsResponse;
import com.vk.superapp.api.dto.geo.matrix.ReachabilityMatrixRequest;
import com.vk.superapp.api.dto.geo.matrix.ReachabilityMatrixResponse;
import com.vk.superapp.api.dto.geo.staticmap.StaticMapExtra;
import com.vk.superapp.api.dto.geo.staticmap.StaticMapResponse;
import com.vk.superapp.api.dto.group.WebGroup;
import com.vk.superapp.api.dto.group.WebGroupMessageStatus;
import com.vk.superapp.api.dto.group.WebGroupShortInfo;
import com.vk.superapp.api.dto.identity.WebCity;
import com.vk.superapp.api.dto.identity.WebIdentityAddress;
import com.vk.superapp.api.dto.identity.WebIdentityCardData;
import com.vk.superapp.api.dto.identity.WebIdentityEmail;
import com.vk.superapp.api.dto.identity.WebIdentityLabel;
import com.vk.superapp.api.dto.identity.WebIdentityPhone;
import com.vk.superapp.api.dto.menu.BadgeInfo;
import com.vk.superapp.api.dto.menu.SuperAppAnimationConfig;
import com.vk.superapp.api.dto.odnoklassniki.OkFlowSilentTokenResponse;
import com.vk.superapp.api.dto.personal.Banner;
import com.vk.superapp.api.dto.personal.PersonalDiscount;
import com.vk.superapp.api.dto.qr.QrInfoResponse;
import com.vk.superapp.api.dto.restore.VkRestoreConfirmInstantResult;
import com.vk.superapp.api.dto.restore.VkRestoreInstantAuth;
import com.vk.superapp.api.dto.store.FillBalanceUrl;
import com.vk.superapp.api.dto.user.CheckInviteUserData;
import com.vk.superapp.api.dto.user.WebUserShortInfo;
import com.vk.superapp.api.dto.vkworkout.WorkoutData;
import com.vk.superapp.api.dto.widgets.actions.WebActionCallback;
import com.vk.superapp.api.internal.WebApiRequest;
import com.vk.superapp.api.internal.oauthrequests.AuthByExchangeTokenInitiator;
import com.vk.superapp.api.internal.requests.app.AddActionSuggestion;
import com.vk.superapp.api.internal.requests.app.ConfirmResult;
import com.vk.superapp.api.internal.requests.app.CreateSubscriptionResult;
import com.vk.superapp.api.internal.requests.app.OrdersCancelUserSubscriptionResult;
import com.vk.superapp.api.internal.requests.app.SubscriptionConfirmResult;
import com.vk.superapp.api.internal.requests.vkrun.VkRunStepsResponse;
import com.vk.superapp.api.requests.app.WebAppsSearchType;
import com.vk.superapp.api.states.VkAuthState;
import com.vk.superapp.api.states.VkGetOauthTokenArgs;
import com.vk.superapp.catalog.impl.v1.fragment.BaseSuperappMiniAppsFragment;
import com.vk.superapp.core.api.models.VkGender;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.mail.ads.core.impl.analytics.SessionParamsProviderImpl;
import ru.mail.calendar.widget.utils.Constants;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.news_feed.util.analytics.NewsAnalyticsHandler;
import ru.mail.smoothie.presentation.request.handler.AppConfigRequestHandler;
import ru.ok.android.sdk.SharedKt;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b&\bf\u0018\u00002\u00020\u0001:%\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&¨\u0006'"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi;", "", "Common", "VkidOk", "VkIdMail", "Account", "App", "SuperApp", "Users", "Friends", "Group", "Notification", "Permission", "Stat", "Storage", "Advertisement", "Widgets", "VkAuth", "VkRestore", "VkUtils", "Settings", "VkRun", "Identity", "Geo", "Database", "Email", "Birthday", "Messages", "Esia", "AccountVerification", "VkWorkout", "VkHealth", "GoodsOrders", "Translations", "Orders", "Store", "Verification", "Captcha", "QrWebToApp", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SuperappApi {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH&J*\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0010H&J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0010H&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0003H&J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH&J>\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010\u001f\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010\u00102\b\u0010!\u001a\u0004\u0018\u00010\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u00102\b\u0010#\u001a\u0004\u0018\u00010\u0010H&J&\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(H&J2\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00032\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0010H&J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0006\u0010\"\u001a\u00020\u0010H&¨\u0006-"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Account;", "", "sendGetProfileSwitcherInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/account/dto/AccountGetProfilesSwitcherInfoResponseDto;", "sendAccountGetEmail", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/account/AccountSignedResponse;", "appId", "", "sendAccountGetPhoneNumber", "sendGetAccessToken", "Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "args", "Lcom/vk/superapp/api/states/VkGetOauthTokenArgs;", "tokenKey", "", "sendGetProfileShortInfo", "Lcom/vk/superapp/api/dto/account/ProfileShortInfo;", CommonConstant.KEY_ACCESS_TOKEN, "superappToken", "checkNeedServicePolicy", "", "getProfileNavigationInfo", "Lcom/vk/superapp/api/dto/account/ProfileNavigationInfo;", "getTogglesAnonym", "Lcom/vk/superapp/api/dto/account/AccountAnonymousToggles;", "toggles", "", "checkPassword", "Lcom/vk/superapp/api/dto/account/AccountCheckPasswordResponse;", "password", "firstName", "lastName", "birthday", "phone", "initPasswordCheck", "Lcom/vk/superapp/api/dto/auth/InitPasswordCheckResponse;", BaseCheckFragment.KEY_SAT_TOKEN, "accessFactor", "Lcom/vk/superapp/api/dto/auth/InitPasswordCheckAccessFactor;", "checkAccess", "Lcom/vk/superapp/api/dto/auth/CheckAccessResponse;", "smsCode", "validateBirthday", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Account {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single checkAccess$default(Account account, String str, String str2, String str3, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkAccess");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                if ((i10 & 4) != 0) {
                    str3 = null;
                }
                return account.checkAccess(str, str2, str3);
            }

            public static /* synthetic */ Single checkNeedServicePolicy$default(Account account, long j10, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkNeedServicePolicy");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                return account.checkNeedServicePolicy(j10, str);
            }

            public static /* synthetic */ Single initPasswordCheck$default(Account account, String str, InitPasswordCheckAccessFactor initPasswordCheckAccessFactor, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initPasswordCheck");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                if ((i10 & 2) != 0) {
                    initPasswordCheckAccessFactor = null;
                }
                return account.initPasswordCheck(str, initPasswordCheckAccessFactor);
            }

            public static /* synthetic */ Observable sendGetAccessToken$default(Account account, long j10, VkGetOauthTokenArgs vkGetOauthTokenArgs, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetAccessToken");
                }
                if ((i10 & 4) != 0) {
                    str = null;
                }
                return account.sendGetAccessToken(j10, vkGetOauthTokenArgs, str);
            }

            public static /* synthetic */ Single sendGetProfileShortInfo$default(Account account, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetProfileShortInfo");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return account.sendGetProfileShortInfo(str, str2);
            }
        }

        @NotNull
        Single<CheckAccessResponse> checkAccess(@Nullable String smsCode, @Nullable String password, @Nullable String satToken);

        @NotNull
        Single<Boolean> checkNeedServicePolicy(long appId, @Nullable String superappToken);

        @NotNull
        Single<AccountCheckPasswordResponse> checkPassword(@NotNull String password, @Nullable String firstName, @Nullable String lastName, @Nullable String birthday, @Nullable String phone);

        @NotNull
        Single<ProfileNavigationInfo> getProfileNavigationInfo();

        @NotNull
        Single<AccountAnonymousToggles> getTogglesAnonym(@NotNull List<String> toggles);

        @NotNull
        Single<InitPasswordCheckResponse> initPasswordCheck(@Nullable String satToken, @Nullable InitPasswordCheckAccessFactor accessFactor);

        @NotNull
        Observable<AccountSignedResponse> sendAccountGetEmail(long appId);

        @NotNull
        Observable<AccountSignedResponse> sendAccountGetPhoneNumber(long appId);

        @NotNull
        Observable<WebAuthAnswer> sendGetAccessToken(long appId, @NotNull VkGetOauthTokenArgs args, @Nullable String tokenKey);

        @NotNull
        Single<ProfileShortInfo> sendGetProfileShortInfo(@Nullable String accessToken, @Nullable String superappToken);

        @NotNull
        Single<AccountGetProfilesSwitcherInfoResponseDto> sendGetProfileSwitcherInfo();

        @NotNull
        Single<Boolean> validateBirthday(@NotNull String birthday);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H&J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H&¨\u0006\u0012"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "", "getSessionInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/accountVerification/dto/AccountVerificationGetSessionInfoResponseDto;", "code", "", "codeVerifier", "provider", "providerClientId", "platform", "Lcom/vk/api/generated/accountVerification/dto/AccountVerificationGetSessionInfoPlatformDto;", "createLink", "", PasskeyBeginResult.SID_KEY, "cuaToken", "linkWithVerify", "tinkoffSid", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface AccountVerification {
        @NotNull
        Single<Boolean> createLink(@NotNull String sid, @NotNull String cuaToken, @NotNull String provider);

        @NotNull
        Single<AccountVerificationGetSessionInfoResponseDto> getSessionInfo(@NotNull String code, @Nullable String codeVerifier, @NotNull String provider, @NotNull String providerClientId, @NotNull AccountVerificationGetSessionInfoPlatformDto platform);

        @NotNull
        Single<Boolean> linkWithVerify(@NotNull String tinkoffSid, @NotNull String cuaToken, @NotNull String provider);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\t\n\u000bJ\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\bH&¨\u0006\f"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "", "sendRetargetingHitRequest", "Lio/reactivex/rxjava3/core/Observable;", "", "params", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$RetargetingHitParams;", "sendConversionHitRequest", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$ConversionHitParams;", "BasePixelParams", "RetargetingHitParams", "ConversionHitParams", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Advertisement {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\nJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ2\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\nR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0019\u001a\u0004\b\u001c\u0010\nR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\r¨\u0006 "}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "", "", "code", "httpRef", "", "appId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Ljava/lang/Long;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "ipakvmoca", "Ljava/lang/String;", "getCode", "ipakvmocb", "getHttpRef", "ipakvmocc", "Ljava/lang/Long;", "getAppId", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class BasePixelParams {

            /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
            @NotNull
            private final String code;

            /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
            @Nullable
            private final String httpRef;

            /* JADX INFO: renamed from: ipakvmocc, reason: from kotlin metadata */
            @Nullable
            private final Long appId;

            public BasePixelParams(@NotNull String code, @Nullable String str, @Nullable Long l10) {
                Intrinsics.checkNotNullParameter(code, "code");
                this.code = code;
                this.httpRef = str;
                this.appId = l10;
            }

            public static /* synthetic */ BasePixelParams copy$default(BasePixelParams basePixelParams, String str, String str2, Long l10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = basePixelParams.code;
                }
                if ((i10 & 2) != 0) {
                    str2 = basePixelParams.httpRef;
                }
                if ((i10 & 4) != 0) {
                    l10 = basePixelParams.appId;
                }
                return basePixelParams.copy(str, str2, l10);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getCode() {
                return this.code;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getHttpRef() {
                return this.httpRef;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Long getAppId() {
                return this.appId;
            }

            @NotNull
            public final BasePixelParams copy(@NotNull String code, @Nullable String httpRef, @Nullable Long appId) {
                Intrinsics.checkNotNullParameter(code, "code");
                return new BasePixelParams(code, httpRef, appId);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof BasePixelParams)) {
                    return false;
                }
                BasePixelParams basePixelParams = (BasePixelParams) other;
                return Intrinsics.areEqual(this.code, basePixelParams.code) && Intrinsics.areEqual(this.httpRef, basePixelParams.httpRef) && Intrinsics.areEqual(this.appId, basePixelParams.appId);
            }

            @Nullable
            public final Long getAppId() {
                return this.appId;
            }

            @NotNull
            public final String getCode() {
                return this.code;
            }

            @Nullable
            public final String getHttpRef() {
                return this.httpRef;
            }

            public int hashCode() {
                int iHashCode = this.code.hashCode() * 31;
                String str = this.httpRef;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                Long l10 = this.appId;
                return iHashCode2 + (l10 != null ? l10.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                return "BasePixelParams(code=" + this.code + ", httpRef=" + this.httpRef + ", appId=" + this.appId + ')';
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ2\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0012\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000f¨\u0006#"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$ConversionHitParams;", "", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "baseParams", "", "conversionEvent", "", "conversionValue", "<init>", "(Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;Ljava/lang/String;Ljava/lang/Float;)V", "component1", "()Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/lang/Float;", "copy", "(Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;Ljava/lang/String;Ljava/lang/Float;)Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$ConversionHitParams;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "ipakvmoca", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "getBaseParams", "ipakvmocb", "Ljava/lang/String;", "getConversionEvent", "ipakvmocc", "Ljava/lang/Float;", "getConversionValue", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class ConversionHitParams {

            /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
            @NotNull
            private final BasePixelParams baseParams;

            /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
            @Nullable
            private final String conversionEvent;

            /* JADX INFO: renamed from: ipakvmocc, reason: from kotlin metadata */
            @Nullable
            private final Float conversionValue;

            public ConversionHitParams(@NotNull BasePixelParams baseParams, @Nullable String str, @Nullable Float f10) {
                Intrinsics.checkNotNullParameter(baseParams, "baseParams");
                this.baseParams = baseParams;
                this.conversionEvent = str;
                this.conversionValue = f10;
            }

            public static /* synthetic */ ConversionHitParams copy$default(ConversionHitParams conversionHitParams, BasePixelParams basePixelParams, String str, Float f10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    basePixelParams = conversionHitParams.baseParams;
                }
                if ((i10 & 2) != 0) {
                    str = conversionHitParams.conversionEvent;
                }
                if ((i10 & 4) != 0) {
                    f10 = conversionHitParams.conversionValue;
                }
                return conversionHitParams.copy(basePixelParams, str, f10);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final BasePixelParams getBaseParams() {
                return this.baseParams;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getConversionEvent() {
                return this.conversionEvent;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Float getConversionValue() {
                return this.conversionValue;
            }

            @NotNull
            public final ConversionHitParams copy(@NotNull BasePixelParams baseParams, @Nullable String conversionEvent, @Nullable Float conversionValue) {
                Intrinsics.checkNotNullParameter(baseParams, "baseParams");
                return new ConversionHitParams(baseParams, conversionEvent, conversionValue);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ConversionHitParams)) {
                    return false;
                }
                ConversionHitParams conversionHitParams = (ConversionHitParams) other;
                return Intrinsics.areEqual(this.baseParams, conversionHitParams.baseParams) && Intrinsics.areEqual(this.conversionEvent, conversionHitParams.conversionEvent) && Intrinsics.areEqual((Object) this.conversionValue, (Object) conversionHitParams.conversionValue);
            }

            @NotNull
            public final BasePixelParams getBaseParams() {
                return this.baseParams;
            }

            @Nullable
            public final String getConversionEvent() {
                return this.conversionEvent;
            }

            @Nullable
            public final Float getConversionValue() {
                return this.conversionValue;
            }

            public int hashCode() {
                int iHashCode = this.baseParams.hashCode() * 31;
                String str = this.conversionEvent;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                Float f10 = this.conversionValue;
                return iHashCode2 + (f10 != null ? f10.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                return "ConversionHitParams(baseParams=" + this.baseParams + ", conversionEvent=" + this.conversionEvent + ", conversionValue=" + this.conversionValue + ')';
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0012J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0010J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0010JT\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0010J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010\u0010R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010\u0012R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b)\u0010'\u001a\u0004\b*\u0010\u0012R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b+\u0010$\u001a\u0004\b,\u0010\u0010R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b-\u0010$\u001a\u0004\b.\u0010\u0010¨\u0006/"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$RetargetingHitParams;", "", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "baseParams", "", "event", "", "targetGroupId", "priceListId", "productsEvent", "productsParams", "<init>", "(Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "component2", "()Ljava/lang/String;", "component3", "()Ljava/lang/Long;", "component4", "component5", "component6", "copy", "(Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$RetargetingHitParams;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "ipakvmoca", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$BasePixelParams;", "getBaseParams", "ipakvmocb", "Ljava/lang/String;", "getEvent", "ipakvmocc", "Ljava/lang/Long;", "getTargetGroupId", "ipakvmocd", "getPriceListId", "ipakvmoce", "getProductsEvent", "ipakvmocf", "getProductsParams", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final /* data */ class RetargetingHitParams {

            /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
            @NotNull
            private final BasePixelParams baseParams;

            /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
            @NotNull
            private final String event;

            /* JADX INFO: renamed from: ipakvmocc, reason: from kotlin metadata */
            @Nullable
            private final Long targetGroupId;

            /* JADX INFO: renamed from: ipakvmocd, reason: from kotlin metadata */
            @Nullable
            private final Long priceListId;

            /* JADX INFO: renamed from: ipakvmoce, reason: from kotlin metadata */
            @Nullable
            private final String productsEvent;

            /* JADX INFO: renamed from: ipakvmocf, reason: from kotlin metadata */
            @Nullable
            private final String productsParams;

            public RetargetingHitParams(@NotNull BasePixelParams baseParams, @NotNull String event, @Nullable Long l10, @Nullable Long l11, @Nullable String str, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(baseParams, "baseParams");
                Intrinsics.checkNotNullParameter(event, "event");
                this.baseParams = baseParams;
                this.event = event;
                this.targetGroupId = l10;
                this.priceListId = l11;
                this.productsEvent = str;
                this.productsParams = str2;
            }

            public static /* synthetic */ RetargetingHitParams copy$default(RetargetingHitParams retargetingHitParams, BasePixelParams basePixelParams, String str, Long l10, Long l11, String str2, String str3, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    basePixelParams = retargetingHitParams.baseParams;
                }
                if ((i10 & 2) != 0) {
                    str = retargetingHitParams.event;
                }
                if ((i10 & 4) != 0) {
                    l10 = retargetingHitParams.targetGroupId;
                }
                if ((i10 & 8) != 0) {
                    l11 = retargetingHitParams.priceListId;
                }
                if ((i10 & 16) != 0) {
                    str2 = retargetingHitParams.productsEvent;
                }
                if ((i10 & 32) != 0) {
                    str3 = retargetingHitParams.productsParams;
                }
                String str4 = str2;
                String str5 = str3;
                return retargetingHitParams.copy(basePixelParams, str, l10, l11, str4, str5);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final BasePixelParams getBaseParams() {
                return this.baseParams;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getEvent() {
                return this.event;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Long getTargetGroupId() {
                return this.targetGroupId;
            }

            @Nullable
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Long getPriceListId() {
                return this.priceListId;
            }

            @Nullable
            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getProductsEvent() {
                return this.productsEvent;
            }

            @Nullable
            /* JADX INFO: renamed from: component6, reason: from getter */
            public final String getProductsParams() {
                return this.productsParams;
            }

            @NotNull
            public final RetargetingHitParams copy(@NotNull BasePixelParams baseParams, @NotNull String event, @Nullable Long targetGroupId, @Nullable Long priceListId, @Nullable String productsEvent, @Nullable String productsParams) {
                Intrinsics.checkNotNullParameter(baseParams, "baseParams");
                Intrinsics.checkNotNullParameter(event, "event");
                return new RetargetingHitParams(baseParams, event, targetGroupId, priceListId, productsEvent, productsParams);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof RetargetingHitParams)) {
                    return false;
                }
                RetargetingHitParams retargetingHitParams = (RetargetingHitParams) other;
                return Intrinsics.areEqual(this.baseParams, retargetingHitParams.baseParams) && Intrinsics.areEqual(this.event, retargetingHitParams.event) && Intrinsics.areEqual(this.targetGroupId, retargetingHitParams.targetGroupId) && Intrinsics.areEqual(this.priceListId, retargetingHitParams.priceListId) && Intrinsics.areEqual(this.productsEvent, retargetingHitParams.productsEvent) && Intrinsics.areEqual(this.productsParams, retargetingHitParams.productsParams);
            }

            @NotNull
            public final BasePixelParams getBaseParams() {
                return this.baseParams;
            }

            @NotNull
            public final String getEvent() {
                return this.event;
            }

            @Nullable
            public final Long getPriceListId() {
                return this.priceListId;
            }

            @Nullable
            public final String getProductsEvent() {
                return this.productsEvent;
            }

            @Nullable
            public final String getProductsParams() {
                return this.productsParams;
            }

            @Nullable
            public final Long getTargetGroupId() {
                return this.targetGroupId;
            }

            public int hashCode() {
                int iHashCode = (this.event.hashCode() + (this.baseParams.hashCode() * 31)) * 31;
                Long l10 = this.targetGroupId;
                int iHashCode2 = (iHashCode + (l10 == null ? 0 : l10.hashCode())) * 31;
                Long l11 = this.priceListId;
                int iHashCode3 = (iHashCode2 + (l11 == null ? 0 : l11.hashCode())) * 31;
                String str = this.productsEvent;
                int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.productsParams;
                return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
            }

            @NotNull
            public String toString() {
                return "RetargetingHitParams(baseParams=" + this.baseParams + ", event=" + this.event + ", targetGroupId=" + this.targetGroupId + ", priceListId=" + this.priceListId + ", productsEvent=" + this.productsEvent + ", productsParams=" + this.productsParams + ')';
            }
        }

        @NotNull
        Observable<Boolean> sendConversionHitRequest(@NotNull ConversionHitParams params);

        @NotNull
        Observable<Boolean> sendRetargetingHitRequest(@NotNull RetargetingHitParams params);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000Ø\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H&J\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J_\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00190\u0003H&J \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H&J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&JL\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&J]\u0010&\u001a\b\u0012\u0004\u0012\u00020\"0\n2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010(JB\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00190\u00032\b\u0010*\u001a\u0004\u0018\u00010\r2\u0006\u0010+\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u00152\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\rH&J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00032\u0006\u0010\u0005\u001a\u00020\u0015H&J*\u0010.\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0/0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00100\u001a\u00020\rH&J0\u00101\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00040/0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020\r0\u0019H&J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00032\u0006\u00106\u001a\u0002072\u0006\u0010\u0005\u001a\u00020\u0006H&J(\u00108\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u00106\u001a\u0002072\u0006\u0010\u0005\u001a\u00020\u00062\b\u00109\u001a\u0004\u0018\u00010\rH&J\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J.\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020\rH&Je\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u00190\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010E\u001a\u0004\u0018\u00010D2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H&¢\u0006\u0002\u0010FJD\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u00190\u00032\b\u0010\u001b\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020D2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H&J\u0014\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0\u00190\u0003H&J*\u0010J\u001a\b\u0012\u0004\u0012\u00020B0\u00032\u0006\u0010\u001b\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u0015H&J\u001a\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\rH&JJ\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\u00032\u0006\u0010N\u001a\u00020\r2\u000e\b\u0002\u0010O\u001a\b\u0012\u0004\u0012\u00020\r0P2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u00152\u000e\b\u0002\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00060PH&J:\u0010R\u001a\b\u0012\u0004\u0012\u00020M0\u00032\u0006\u0010N\u001a\u00020\r2\u000e\b\u0002\u0010O\u001a\b\u0012\u0004\u0012\u00020\r0P2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u0015H&J\"\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u00032\u0006\u0010U\u001a\u00020\r2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\rH&J\u0016\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J.\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010X\u001a\u0002072\u0006\u0010Y\u001a\u00020\r2\u0006\u00109\u001a\u00020\rH&J.\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010[\u001a\b\u0012\u0004\u0012\u0002070\u00192\b\u00109\u001a\u0004\u0018\u00010\rH&J\u000e\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u0003H&J\u001e\u0010^\u001a\b\u0012\u0004\u0012\u00020_0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010`\u001a\u00020\u0015H&J2\u0010a\u001a\b\u0012\u0004\u0012\u00020b0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010@\u001a\u0004\u0018\u00010\r2\b\u0010c\u001a\u0004\u0018\u00010\r2\u0006\u0010d\u001a\u00020\u0015H&J;\u0010e\u001a\b\u0012\u0004\u0012\u00020f0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010g\u001a\u00020\r2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010hJ;\u0010i\u001a\b\u0012\u0004\u0012\u00020j0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010g\u001a\u00020\r2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&¢\u0006\u0002\u0010hJ/\u0010k\u001a\b\u0012\u0004\u0012\u00020j0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010`\u001a\u00020\u00152\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010lJ2\u0010m\u001a\b\u0012\u0004\u0012\u00020n0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u00152\u0006\u0010o\u001a\u00020\r2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&J\u001e\u0010p\u001a\b\u0012\u0004\u0012\u00020q0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010`\u001a\u00020\u0015H&J\u001e\u0010r\u001a\b\u0012\u0004\u0012\u00020s0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010`\u001a\u00020\u0015H&J:\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u00152\u0006\u0010o\u001a\u00020\r2\u0006\u0010v\u001a\u00020w2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&J\u001e\u0010x\u001a\b\u0012\u0004\u0012\u00020y0\n2\u0006\u0010d\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010{\u001a\u00020\rH&J,\u0010|\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020}0\u00190\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010~\u001a\u00020\u00152\u0006\u0010\u007f\u001a\u00020\u0015H&JC\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\r2\t\b\u0002\u0010\u0082\u0001\u001a\u0002072\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH&J<\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010U\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\r2\f\b\u0002\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001H&J*\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u00020\rH&J8\u0010\u008b\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008c\u00010\u00190\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00152\b\b\u0002\u0010N\u001a\u00020\rH&J%\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u00030\u008e\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00062\u000b\b\u0002\u0010\u008f\u0001\u001a\u0004\u0018\u00010\rH&J!\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\rH&J<\u0010\u0091\u0001\u001a\t\u0012\u0005\u0012\u00030\u0092\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0007\u0010\u0093\u0001\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\t\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0006H&¢\u0006\u0003\u0010\u0095\u0001J!\u0010\u0096\u0001\u001a\t\u0012\u0005\u0012\u00030\u0097\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0007\u0010\u0098\u0001\u001a\u00020\u0004H&J!\u0010\u0099\u0001\u001a\t\u0012\u0005\u0012\u00030\u0097\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0007\u0010\u009a\u0001\u001a\u00020\u0004H&J\u0017\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u009c\u0001\u001a\t\u0012\u0005\u0012\u00030\u009d\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u009e\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009f\u00010\u00190\u00032\u0006\u0010\u0005\u001a\u00020\u0015H&J\u0018\u0010 \u0001\u001a\t\u0012\u0005\u0012\u00030¡\u00010\n2\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006¢\u0001"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$App;", "", "sendAppsAddToGroup", "Lio/reactivex/rxjava3/core/Observable;", "", "appId", "", "groupId", "shouldSendPush", "sendAppsGetAdvertisementConfig", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/external/miniapp/net/ad/AdvertisementConfig;", "activeFeatures", "", "sendAppsGetAppAdvertisementConfig", "Lcom/vk/superapp/api/dto/app/AppAdvertisementConfig;", "sendAppsGetMiniAppsCatalog", "Lcom/vk/superapp/api/dto/app/catalog/AppsCatalogSectionsResponse;", "location", "Landroid/location/Location;", "limit", "", "offset", "lastSeenSectionId", "appFields", "", "Lcom/vk/external/miniapp/net/app/AppFields;", BaseSuperappMiniAppsFragment.KEY_SECTION_ID, "(Landroid/location/Location;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "sendGetMiniAppCategories", "Lcom/vk/superapp/api/dto/app/catalog/section/AppsCategory;", "sendAppsGetMiniAppsCatalogSearch", "sendAppsAddToMenu", "sendAppsGet", "Lcom/vk/external/miniapp/net/app/WebApiApplication;", BlockParser.REF_TYPE, "specialUrl", "trackCode", "sendJoinAndGet", "needSettings", "(JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Single;", "sendAppsGetRecommendations", "platform", "count", "sendAppsGetActionMenuApps", "Lcom/vk/superapp/api/dto/app/ActionMenuApps;", "sendAppsGetScopes", "", "name", "sendAppsGetCheckAllowedScopes", SharedKt.PARAM_SCOPES, "sendAppsCheckAllowPosting", "sendAppsCheckInviteFriend", "Lcom/vk/superapp/api/dto/user/CheckInviteUserData;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", "sendAppsInviteFriend", "requestKey", "sendAppsRemove", "sendAppsRemoveFromMenu", "sendAppUninstall", "sendAppWidgetGetPreview", "Lorg/json/JSONObject;", "code", "type", "sendGetVkApps", "Lcom/vk/superapp/api/dto/app/AppsSection;", "lat", "", "lon", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "sendGetVkAppsUnauthorized", "sendGetAppsCatalogActivities", "Lcom/vk/superapp/api/dto/app/WebAppActivities;", "sendGetGamesSection", "sendAppsClearRecents", "sendAppsSearch", "Lcom/vk/superapp/api/dto/app/AppsSearchResponse;", "query", "filters", "", "tagIds", "sendAppsSearchUnauthorized", "sendAppResolveByUrl", "Lcom/vk/superapp/api/dto/app/ResolvingResult;", "url", "sendAppsConfirmPolicy", "sendAppRequest", "userTo", "message", "sendAppInviteRequest", "userIds", "getTrackBridgeCallHandlers", "Lcom/vk/api/generated/apps/dto/AppsGetTrackBridgeCallHandlersResponseDto;", "getAppSubscription", "Lcom/vk/api/generated/orders/dto/OrdersSubscriptionDto;", "subscriptionId", "sendAppOrder", "Lcom/vk/superapp/api/dto/app/WebOrder;", DivActionSpec.Scroll.PARAM_ITEM_INDEX, "orderId", "sendAppCreateOrder", "Lcom/vk/superapp/api/dto/app/WebOrderInfo;", "itemId", "(JLjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "sendAppCreateSubscription", "Lcom/vk/superapp/api/internal/requests/app/CreateSubscriptionResult;", "sendAppResumeSubscription", "(JILjava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "sendAppConfirmGameSubscription", "Lcom/vk/superapp/api/internal/requests/app/SubscriptionConfirmResult;", "confirmHash", "sendAppGetUserSubscription", "Lcom/vk/superapp/api/dto/app/GameSubscription;", "sendAppCancelUserSubscription", "Lcom/vk/superapp/api/internal/requests/app/OrdersCancelUserSubscriptionResult;", "sendAppConfirmOrder", "Lcom/vk/superapp/api/internal/requests/app/ConfirmResult;", "autoBuyStatus", "Lcom/vk/superapp/api/dto/app/AutoBuyStatus;", "sendAppOrdersGetById", "Lcom/vk/api/generated/orders/dto/OrdersOrderDto$StatusDto;", "sendAppUploadAttachedLinkWallPost", "attachments", "sendGetGameLeaderboardByApp", "Lcom/vk/superapp/api/dto/app/WebGameLeaderboard;", "global", "userResult", "sendGetEmbeddedUrl", "Lcom/vk/superapp/api/dto/app/WebAppEmbeddedUrl;", "ownerId", "needToShowAction", "Lcom/vk/superapp/api/internal/requests/app/AddActionSuggestion;", "placeId", "Lcom/vk/api/generated/apps/dto/AppsNeedToShowActionPlaceIdDto;", "sendActionShown", "event", "Lcom/vk/superapp/api/dto/app/AppLifecycleEvent;", "actionType", "sendGetFriendsList", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "sendGetSecretHash", "Lcom/vk/superapp/api/dto/app/AppsSecretHash;", "requestId", "sendSetGameIsInstalled", "sendGetLaunchParams", "Lcom/vk/superapp/api/dto/app/AppLaunchParams;", "referrer", "vkProfileId", "(JLjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsChangeAppBadgeStatus", "Lcom/vk/api/generated/base/dto/BaseBoolIntDto;", "isAllowed", "markAppAsRecommended", "isRecommended", "setUnverifiedScreenShown", "startAppCall", "Lcom/vk/api/generated/apps/dto/AppsStartCallResponseDto;", "getGroupsList", "Lcom/vk/superapp/api/dto/app/AppsGroupsContainer;", "getActionMenuBanner", "Lcom/vk/superapp/api/dto/personal/Banner;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface App {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable needToShowAction$default(App app, long j10, String str, String str2, AppsNeedToShowActionPlaceIdDto appsNeedToShowActionPlaceIdDto, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: needToShowAction");
                }
                if ((i10 & 4) != 0) {
                    str2 = null;
                }
                if ((i10 & 8) != 0) {
                    appsNeedToShowActionPlaceIdDto = null;
                }
                return app.needToShowAction(j10, str, str2, appsNeedToShowActionPlaceIdDto);
            }

            public static /* synthetic */ Observable sendAppConfirmGameSubscription$default(App app, long j10, int i10, String str, String str2, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppConfirmGameSubscription");
                }
                if ((i11 & 8) != 0) {
                    str2 = null;
                }
                return app.sendAppConfirmGameSubscription(j10, i10, str, str2);
            }

            public static /* synthetic */ Observable sendAppConfirmOrder$default(App app, long j10, int i10, String str, AutoBuyStatus autoBuyStatus, String str2, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppConfirmOrder");
                }
                if ((i11 & 16) != 0) {
                    str2 = null;
                }
                return app.sendAppConfirmOrder(j10, i10, str, autoBuyStatus, str2);
            }

            public static /* synthetic */ Observable sendAppCreateOrder$default(App app, long j10, String str, Integer num, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppCreateOrder");
                }
                if ((i10 & 4) != 0) {
                    num = null;
                }
                if ((i10 & 8) != 0) {
                    str2 = null;
                }
                return app.sendAppCreateOrder(j10, str, num, str2);
            }

            public static /* synthetic */ Observable sendAppCreateSubscription$default(App app, long j10, String str, Integer num, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppCreateSubscription");
                }
                if ((i10 & 4) != 0) {
                    num = null;
                }
                if ((i10 & 8) != 0) {
                    str2 = null;
                }
                return app.sendAppCreateSubscription(j10, str, num, str2);
            }

            public static /* synthetic */ Observable sendAppResolveByUrl$default(App app, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppResolveByUrl");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return app.sendAppResolveByUrl(str, str2);
            }

            public static /* synthetic */ Observable sendAppResumeSubscription$default(App app, long j10, int i10, Integer num, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppResumeSubscription");
                }
                if ((i11 & 4) != 0) {
                    num = null;
                }
                return app.sendAppResumeSubscription(j10, i10, num);
            }

            public static /* synthetic */ Observable sendAppsClearRecents$default(App app, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsClearRecents");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                return app.sendAppsClearRecents(str);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendAppsGet$default(App app, long j10, String str, List list, String str2, String str3, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsGet");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                if ((i10 & 4) != 0) {
                    list = null;
                }
                if ((i10 & 8) != 0) {
                    str2 = null;
                }
                if ((i10 & 16) != 0) {
                    str3 = null;
                }
                return app.sendAppsGet(j10, str, list, str2, str3);
            }

            public static /* synthetic */ Single sendAppsGetAdvertisementConfig$default(App app, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsGetAdvertisementConfig");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                return app.sendAppsGetAdvertisementConfig(str);
            }

            public static /* synthetic */ Observable sendAppsGetMiniAppsCatalog$default(App app, Location location, String str, int i10, Integer num, Integer num2, List list, String str2, int i11, Object obj) {
                if (obj == null) {
                    return app.sendAppsGetMiniAppsCatalog(location, str, i10, num, num2, list, (i11 & 64) != 0 ? null : str2);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsGetMiniAppsCatalog");
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendAppsGetMiniAppsCatalogSearch$default(App app, List list, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsGetMiniAppsCatalogSearch");
                }
                if ((i10 & 1) != 0) {
                    list = null;
                }
                return app.sendAppsGetMiniAppsCatalogSearch(list);
            }

            public static /* synthetic */ Observable sendAppsGetRecommendations$default(App app, String str, int i10, int i11, int i12, String str2, int i13, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsGetRecommendations");
                }
                if ((i13 & 16) != 0) {
                    str2 = null;
                }
                return app.sendAppsGetRecommendations(str, i10, i11, i12, str2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendAppsSearch$default(App app, String str, Collection collection, int i10, int i11, Collection collection2, int i12, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsSearch");
                }
                if ((i12 & 2) != 0) {
                    collection = Collections.singleton(WebAppsSearchType.TYPE_VK_APPS.getType());
                    Intrinsics.checkNotNullExpressionValue(collection, "singleton(...)");
                }
                Collection collection3 = collection;
                if ((i12 & 4) != 0) {
                    i10 = 0;
                }
                int i13 = i10;
                if ((i12 & 8) != 0) {
                    i11 = 10;
                }
                int i14 = i11;
                if ((i12 & 16) != 0) {
                    collection2 = SetsKt.emptySet();
                }
                return app.sendAppsSearch(str, collection3, i13, i14, collection2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendAppsSearchUnauthorized$default(App app, String str, Collection collection, int i10, int i11, int i12, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendAppsSearchUnauthorized");
                }
                if ((i12 & 2) != 0) {
                    collection = Collections.singleton(WebAppsSearchType.TYPE_VK_APPS.getType());
                    Intrinsics.checkNotNullExpressionValue(collection, "singleton(...)");
                }
                if ((i12 & 4) != 0) {
                    i10 = 0;
                }
                if ((i12 & 8) != 0) {
                    i11 = 10;
                }
                return app.sendAppsSearchUnauthorized(str, collection, i10, i11);
            }

            public static /* synthetic */ Observable sendGetEmbeddedUrl$default(App app, long j10, String str, UserId userId, String str2, String str3, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetEmbeddedUrl");
                }
                if ((i10 & 4) != 0) {
                    userId = UserId.DEFAULT;
                }
                return app.sendGetEmbeddedUrl(j10, str, userId, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : str3);
            }

            public static /* synthetic */ Observable sendGetFriendsList$default(App app, long j10, int i10, int i11, String str, int i12, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetFriendsList");
                }
                if ((i12 & 8) != 0) {
                    str = "";
                }
                return app.sendGetFriendsList(j10, i10, i11, str);
            }

            public static /* synthetic */ Observable sendGetGamesSection$default(App app, String str, int i10, int i11, int i12, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetGamesSection");
                }
                if ((i12 & 2) != 0) {
                    i10 = 0;
                }
                if ((i12 & 4) != 0) {
                    i11 = 20;
                }
                return app.sendGetGamesSection(str, i10, i11);
            }

            public static /* synthetic */ Single sendGetSecretHash$default(App app, long j10, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetSecretHash");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                return app.sendGetSecretHash(j10, str);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendGetVkApps$default(App app, String str, Integer num, Integer num2, Double d10, Double d11, List list, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetVkApps");
                }
                if ((i10 & 2) != 0) {
                    num = null;
                }
                if ((i10 & 4) != 0) {
                    num2 = null;
                }
                if ((i10 & 8) != 0) {
                    d10 = null;
                }
                if ((i10 & 16) != 0) {
                    d11 = null;
                }
                if ((i10 & 32) != 0) {
                    list = null;
                }
                return app.sendGetVkApps(str, num, num2, d10, d11, list);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable sendGetVkAppsUnauthorized$default(App app, String str, double d10, double d11, List list, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGetVkAppsUnauthorized");
                }
                if ((i10 & 2) != 0) {
                    d10 = 0.0d;
                }
                if ((i10 & 4) != 0) {
                    d11 = 0.0d;
                }
                if ((i10 & 8) != 0) {
                    list = null;
                }
                return app.sendGetVkAppsUnauthorized(str, d10, d11, list);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Single sendJoinAndGet$default(App app, long j10, String str, List list, String str2, String str3, Integer num, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendJoinAndGet");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                if ((i10 & 4) != 0) {
                    list = null;
                }
                if ((i10 & 8) != 0) {
                    str2 = null;
                }
                if ((i10 & 16) != 0) {
                    str3 = null;
                }
                if ((i10 & 32) != 0) {
                    num = null;
                }
                return app.sendJoinAndGet(j10, str, list, str2, str3, num);
            }
        }

        @NotNull
        Single<Banner> getActionMenuBanner(long appId);

        @NotNull
        Observable<OrdersSubscriptionDto> getAppSubscription(long appId, int subscriptionId);

        @NotNull
        Observable<List<AppsGroupsContainer>> getGroupsList(int appId);

        @NotNull
        Observable<AppsGetTrackBridgeCallHandlersResponseDto> getTrackBridgeCallHandlers();

        @NotNull
        Single<BaseBoolIntDto> markAppAsRecommended(long appId, boolean isRecommended);

        @NotNull
        Observable<AddActionSuggestion> needToShowAction(long appId, @Nullable String url, @Nullable String trackCode, @Nullable AppsNeedToShowActionPlaceIdDto placeId);

        @NotNull
        Observable<Boolean> sendActionShown(long appId, @NotNull AppLifecycleEvent event, @NotNull String actionType);

        @NotNull
        Observable<OrdersCancelUserSubscriptionResult> sendAppCancelUserSubscription(long appId, int subscriptionId);

        @NotNull
        Observable<SubscriptionConfirmResult> sendAppConfirmGameSubscription(long appId, int orderId, @NotNull String confirmHash, @Nullable String trackCode);

        @NotNull
        Observable<ConfirmResult> sendAppConfirmOrder(long appId, int orderId, @NotNull String confirmHash, @NotNull AutoBuyStatus autoBuyStatus, @Nullable String trackCode);

        @NotNull
        Observable<WebOrderInfo> sendAppCreateOrder(long appId, @NotNull String itemId, @Nullable Integer orderId, @Nullable String trackCode);

        @NotNull
        Observable<CreateSubscriptionResult> sendAppCreateSubscription(long appId, @NotNull String itemId, @Nullable Integer orderId, @Nullable String trackCode);

        @NotNull
        Observable<GameSubscription> sendAppGetUserSubscription(long appId, int subscriptionId);

        @NotNull
        Observable<Boolean> sendAppInviteRequest(long appId, @NotNull List<UserId> userIds, @Nullable String requestKey);

        @NotNull
        Observable<WebOrder> sendAppOrder(long appId, @Nullable String type, @Nullable String item, int orderId);

        @NotNull
        Single<OrdersOrderDto.StatusDto> sendAppOrdersGetById(int orderId, long appId);

        @NotNull
        Observable<Boolean> sendAppRequest(long appId, @NotNull UserId userTo, @NotNull String message, @NotNull String requestKey);

        @NotNull
        Observable<ResolvingResult> sendAppResolveByUrl(@NotNull String url, @Nullable String ref);

        @NotNull
        Observable<CreateSubscriptionResult> sendAppResumeSubscription(long appId, int subscriptionId, @Nullable Integer orderId);

        @NotNull
        Observable<Boolean> sendAppUninstall(long appId);

        @NotNull
        Observable<Boolean> sendAppUploadAttachedLinkWallPost(long appId, @NotNull String attachments);

        @NotNull
        Observable<JSONObject> sendAppWidgetGetPreview(long groupId, long appId, @NotNull String code, @NotNull String type);

        @NotNull
        Observable<Boolean> sendAppsAddToGroup(long appId, long groupId, boolean shouldSendPush);

        @NotNull
        Observable<Boolean> sendAppsAddToMenu(long appId);

        @NotNull
        Observable<BaseBoolIntDto> sendAppsChangeAppBadgeStatus(long appId, boolean isAllowed);

        @NotNull
        Observable<Boolean> sendAppsCheckAllowPosting(long appId);

        @NotNull
        Observable<CheckInviteUserData> sendAppsCheckInviteFriend(@NotNull UserId userId, long appId);

        @NotNull
        Observable<Boolean> sendAppsClearRecents(@Nullable String platform);

        @NotNull
        Observable<Boolean> sendAppsConfirmPolicy(long appId);

        @NotNull
        Observable<WebApiApplication> sendAppsGet(long appId, @Nullable String ref, @Nullable List<? extends AppFields> appFields, @Nullable String specialUrl, @Nullable String trackCode);

        @NotNull
        Observable<ActionMenuApps> sendAppsGetActionMenuApps(int appId);

        @NotNull
        Single<AdvertisementConfig> sendAppsGetAdvertisementConfig(@Nullable String activeFeatures);

        @NotNull
        Single<AppAdvertisementConfig> sendAppsGetAppAdvertisementConfig(long appId);

        @NotNull
        Observable<Map<String, Boolean>> sendAppsGetCheckAllowedScopes(long appId, @NotNull List<String> scopes);

        @NotNull
        Observable<AppsCatalogSectionsResponse> sendAppsGetMiniAppsCatalog(@Nullable Location location, @Nullable String activeFeatures, int limit, @Nullable Integer offset, @Nullable Integer lastSeenSectionId, @Nullable List<? extends AppFields> appFields, @Nullable String sectionId);

        @NotNull
        Observable<AppsCatalogSectionsResponse> sendAppsGetMiniAppsCatalogSearch(@Nullable List<? extends AppFields> appFields);

        @NotNull
        Observable<List<WebApiApplication>> sendAppsGetRecommendations(@Nullable String platform, int count, int offset, int appId, @Nullable String ref);

        @NotNull
        Observable<Map<String, String>> sendAppsGetScopes(long appId, @NotNull String name);

        @NotNull
        Observable<Boolean> sendAppsInviteFriend(@NotNull UserId userId, long appId, @Nullable String requestKey);

        @NotNull
        Observable<Boolean> sendAppsRemove(long appId);

        @NotNull
        Observable<Boolean> sendAppsRemoveFromMenu(long appId);

        @NotNull
        Observable<AppsSearchResponse> sendAppsSearch(@NotNull String query, @NotNull Collection<String> filters, int offset, int count, @NotNull Collection<Long> tagIds);

        @NotNull
        Observable<AppsSearchResponse> sendAppsSearchUnauthorized(@NotNull String query, @NotNull Collection<String> filters, int offset, int count);

        @NotNull
        Observable<List<WebAppActivities>> sendGetAppsCatalogActivities();

        @NotNull
        Observable<WebAppEmbeddedUrl> sendGetEmbeddedUrl(long appId, @NotNull String url, @NotNull UserId ownerId, @Nullable String ref, @Nullable String trackCode);

        @NotNull
        Observable<List<WebUserShortInfo>> sendGetFriendsList(long appId, int offset, int count, @NotNull String query);

        @NotNull
        Observable<List<WebGameLeaderboard>> sendGetGameLeaderboardByApp(long appId, int global, int userResult);

        @NotNull
        Observable<AppsSection> sendGetGamesSection(@NotNull String sectionId, int offset, int count);

        @NotNull
        Observable<AppLaunchParams> sendGetLaunchParams(long appId, @NotNull String referrer, @Nullable Long groupId, @Nullable Long vkProfileId);

        @NotNull
        Observable<List<AppsCategory>> sendGetMiniAppCategories();

        @NotNull
        Single<AppsSecretHash> sendGetSecretHash(long appId, @Nullable String requestId);

        @NotNull
        Observable<List<AppsSection>> sendGetVkApps(@Nullable String sectionId, @Nullable Integer limit, @Nullable Integer offset, @Nullable Double lat, @Nullable Double lon, @Nullable List<? extends AppFields> appFields);

        @NotNull
        Observable<List<AppsSection>> sendGetVkAppsUnauthorized(@Nullable String sectionId, double lat, double lon, @Nullable List<? extends AppFields> appFields);

        @NotNull
        Single<WebApiApplication> sendJoinAndGet(long appId, @Nullable String ref, @Nullable List<? extends AppFields> appFields, @Nullable String specialUrl, @Nullable String trackCode, @Nullable Integer needSettings);

        @NotNull
        Single<Boolean> sendSetGameIsInstalled(long appId, @Nullable String trackCode);

        @NotNull
        Single<Boolean> setUnverifiedScreenShown(long appId);

        @NotNull
        Single<AppsStartCallResponseDto> startAppCall(long appId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "", "getBirthday", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/birthday/SuperAppBirthdayResponse;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Birthday {
        @NotNull
        Observable<SuperAppBirthdayResponse> getBirthday();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001Jg\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH&¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "", "captchaForce", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "captchaSid", "", "captchaKey", "isSoundCaptcha", "", "uiuxChanges", "isRefreshEnabled", "isSoundCaptchaAvailable", "ui", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Captcha {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single captchaForce$default(Captcha captcha, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: captchaForce");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                if ((i10 & 4) != 0) {
                    num = null;
                }
                if ((i10 & 8) != 0) {
                    num2 = null;
                }
                if ((i10 & 16) != 0) {
                    num3 = null;
                }
                if ((i10 & 32) != 0) {
                    num4 = null;
                }
                if ((i10 & 64) != 0) {
                    num5 = null;
                }
                return captcha.captchaForce(str, str2, num, num2, num3, num4, num5);
            }
        }

        @NotNull
        Single<BaseOkResponseDto> captchaForce(@Nullable String captchaSid, @Nullable String captchaKey, @Nullable Integer isSoundCaptcha, @Nullable Integer uiuxChanges, @Nullable Integer isRefreshEnabled, @Nullable Integer isSoundCaptchaAvailable, @Nullable Integer ui);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J<\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u000bH&J<\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u000bH\u0016¨\u0006\u0010"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Common;", "", "sendApiRequest", "Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONObject;", "appId", "", "url", "", "method", "params", "", "sendVkApiRequest", "apiHost", "apiMethod", "methodParams", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Common {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SuperappApi.kt\ncom/vk/superapp/api/contract/SuperappApi$Common$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,2461:1\n1#2:2462\n*E\n"})
        public static final class DefaultImpls {
            @NotNull
            public static Observable<JSONObject> sendVkApiRequest(@NotNull Common common, long j10, @NotNull final String apiHost, @NotNull final String apiMethod, @Nullable final Map<String, String> map) {
                final String str;
                Intrinsics.checkNotNullParameter(apiHost, "apiHost");
                Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
                if (map == null || (str = map.get(Logger.METHOD_V)) == null || StringsKt.isBlank(str)) {
                    str = null;
                }
                WebApiRequest<JSONObject> webApiRequest = new WebApiRequest<JSONObject>(apiHost, str, map, apiMethod) { // from class: com.vk.superapp.api.contract.SuperappApi$Common$sendVkApiRequest$1

                    /* JADX INFO: renamed from: ipakvmocn, reason: from kotlin metadata */
                    private final String apiUrl;

                    /* JADX INFO: renamed from: ipakvmoco, reason: from kotlin metadata */
                    private final String apiVersion;

                    {
                        super(apiMethod);
                        this.apiUrl = apiHost;
                        this.apiVersion = str == null ? super.getApiVersion() : str;
                        if (map != null) {
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                param(entry.getKey(), entry.getValue());
                            }
                        }
                    }

                    @Override // com.vk.superapp.api.internal.WebApiRequest
                    public boolean getAllowVerification() {
                        return false;
                    }

                    @Override // com.vk.superapp.api.internal.WebApiRequest
                    public String getApiUrl() {
                        return this.apiUrl;
                    }

                    @Override // com.vk.superapp.api.internal.WebApiRequest
                    public String getApiVersion() {
                        return this.apiVersion;
                    }
                };
                webApiRequest.allowNoAuth();
                webApiRequest.forceRemoveAuth(true);
                webApiRequest.allowDeprecatedPhotoFields(true);
                return WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            }
        }

        @NotNull
        Observable<JSONObject> sendApiRequest(long appId, @NotNull String url, @NotNull String method, @Nullable Map<String, String> params);

        @NotNull
        Observable<JSONObject> sendVkApiRequest(long appId, @NotNull String apiHost, @NotNull String apiMethod, @Nullable Map<String, String> methodParams);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Database;", "", "getCities", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/vk/superapp/api/dto/identity/WebCity;", "country", "", "query", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Database {
        @NotNull
        Single<List<WebCity>> getCities(int country, @Nullable String query);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&J*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\n2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u000e"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Email;", "", "canCreateEmail", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/email/EmailCreationResponse;", "username", "", CommonConstant.KEY_ACCESS_TOKEN, "createEmail", "adsAcceptance", "", "setAdsAcceptance", "", "accepted", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Email {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single canCreateEmail$default(Email email, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: canCreateEmail");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return email.canCreateEmail(str, str2);
            }

            public static /* synthetic */ Single createEmail$default(Email email, String str, boolean z10, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createEmail");
                }
                if ((i10 & 4) != 0) {
                    str2 = null;
                }
                return email.createEmail(str, z10, str2);
            }

            public static /* synthetic */ Single setAdsAcceptance$default(Email email, boolean z10, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setAdsAcceptance");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                return email.setAdsAcceptance(z10, str);
            }
        }

        @NotNull
        Single<EmailCreationResponse> canCreateEmail(@NotNull String username, @Nullable String accessToken);

        @NotNull
        Single<EmailCreationResponse> createEmail(@NotNull String username, boolean adsAcceptance, @Nullable String accessToken);

        @NotNull
        Single<Unit> setAdsAcceptance(boolean accepted, @Nullable String accessToken);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\nH&J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH&¨\u0006\u0010"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "", "checkEsiaLink", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/esia/dto/EsiaCheckEsiaLinkResponseDto;", "flow", "Lcom/vk/superapp/api/dto/esia/EsiaCheckEsiaLinkFlow;", "getEsiaUserInfo", "Lcom/vk/api/generated/esia/dto/EsiaGetEsiaUserInfoResponseDto;", "esiaSid", "", "verifyUser", "", "cuaToken", "createLink", "linkAndVerify", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Esia {
        @NotNull
        Single<EsiaCheckEsiaLinkResponseDto> checkEsiaLink(@NotNull EsiaCheckEsiaLinkFlow flow);

        @NotNull
        Single<Boolean> createLink(@NotNull String esiaSid, @NotNull String cuaToken);

        @NotNull
        Single<EsiaGetEsiaUserInfoResponseDto> getEsiaUserInfo(@NotNull String esiaSid, @NotNull EsiaCheckEsiaLinkFlow flow);

        @NotNull
        Single<Boolean> linkAndVerify(@NotNull String esiaSid, @NotNull String cuaToken);

        @NotNull
        Single<Boolean> verifyUser(@NotNull String cuaToken);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "", "sendGetFriends", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "offset", "", "count", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Friends {
        @NotNull
        Observable<List<WebUserShortInfo>> sendGetFriends(int offset, int count);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J(\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH&J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00132\b\b\u0002\u0010\r\u001a\u00020\u0014H&J(\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00162\b\b\u0002\u0010\r\u001a\u00020\u0014H&J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001bH&J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00072\u0006\u0010\u0019\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\u001fH&¨\u0006 "}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "", "setHost", "", "host", "Lcom/vk/superapp/api/dto/geo/GeoServicesConfig$Host;", "staticMap", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/geo/staticmap/StaticMapResponse;", NewsAnalyticsHandler.PARAM_POSITION, "Lcom/vk/superapp/api/dto/geo/Position;", "version", "Lcom/vk/superapp/api/dto/geo/GeoServicesMethodVersion$StaticMapVersion;", "extra", "Lcom/vk/superapp/api/dto/geo/staticmap/StaticMapExtra;", "directGeoCoding", "Lcom/vk/superapp/api/dto/geo/coder/GeoCodingResponse;", "query", "", "Lcom/vk/superapp/api/dto/geo/GeoServicesMethodVersion$DirectGeoCodingVersion;", "Lcom/vk/superapp/api/dto/geo/coder/GeoCoderExtra;", "reverseGeoCoding", "Lcom/vk/superapp/api/dto/geo/GeoServicesMethodVersion$ReverseGeoCodingVersion;", "getDirections", "Lcom/vk/superapp/api/dto/geo/directions/DirectionsResponse;", Event.Companion.Network.Fail.REQUEST_TAG, "Lcom/vk/superapp/api/dto/geo/directions/DirectionsRequest;", "Lcom/vk/superapp/api/dto/geo/GeoServicesMethodVersion$DirectionsVersion;", "getReachabilityMatrix", "Lcom/vk/superapp/api/dto/geo/matrix/ReachabilityMatrixResponse;", "Lcom/vk/superapp/api/dto/geo/matrix/ReachabilityMatrixRequest;", "Lcom/vk/superapp/api/dto/geo/GeoServicesMethodVersion$ReachabilityMatrixVersion;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Geo {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single directGeoCoding$default(Geo geo, String str, GeoServicesMethodVersion.DirectGeoCodingVersion directGeoCodingVersion, GeoCoderExtra geoCoderExtra, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: directGeoCoding");
                }
                if ((i10 & 4) != 0) {
                    geoCoderExtra = new GeoCoderExtra(0, null, null, 7, null);
                }
                return geo.directGeoCoding(str, directGeoCodingVersion, geoCoderExtra);
            }

            public static /* synthetic */ Single reverseGeoCoding$default(Geo geo, String str, GeoServicesMethodVersion.ReverseGeoCodingVersion reverseGeoCodingVersion, GeoCoderExtra geoCoderExtra, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reverseGeoCoding");
                }
                if ((i10 & 4) != 0) {
                    geoCoderExtra = new GeoCoderExtra(0, null, null, 7, null);
                }
                return geo.reverseGeoCoding(str, reverseGeoCodingVersion, geoCoderExtra);
            }

            public static void setHost(@NotNull Geo geo, @NotNull GeoServicesConfig.Host host) {
                Intrinsics.checkNotNullParameter(host, "host");
                GeoServicesConfig.INSTANCE.setHost(host);
            }

            public static /* synthetic */ Single staticMap$default(Geo geo, Position position, GeoServicesMethodVersion.StaticMapVersion staticMapVersion, StaticMapExtra staticMapExtra, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: staticMap");
                }
                if ((i10 & 4) != 0) {
                    staticMapExtra = new StaticMapExtra(0, 0, 0, null, null, 31, null);
                }
                return geo.staticMap(position, staticMapVersion, staticMapExtra);
            }
        }

        @NotNull
        Single<GeoCodingResponse> directGeoCoding(@NotNull String query, @NotNull GeoServicesMethodVersion.DirectGeoCodingVersion version, @NotNull GeoCoderExtra extra);

        @NotNull
        Single<DirectionsResponse> getDirections(@NotNull DirectionsRequest request, @NotNull GeoServicesMethodVersion.DirectionsVersion version);

        @NotNull
        Single<ReachabilityMatrixResponse> getReachabilityMatrix(@NotNull ReachabilityMatrixRequest request, @NotNull GeoServicesMethodVersion.ReachabilityMatrixVersion version);

        @NotNull
        Single<GeoCodingResponse> reverseGeoCoding(@NotNull String query, @NotNull GeoServicesMethodVersion.ReverseGeoCodingVersion version, @NotNull GeoCoderExtra extra);

        void setHost(@NotNull GeoServicesConfig.Host host);

        @NotNull
        Single<StaticMapResponse> staticMap(@NotNull Position position, @NotNull GeoServicesMethodVersion.StaticMapVersion version, @NotNull StaticMapExtra extra);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\u000eH&¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "", "sendAppGetGoodsOrderInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersGoodItemDto;", "appId", "", "itemId", "", "sendAppCreateGoodsOrder", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersNewOrderItemDto;", "sendAppGetGoodsOrder", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersOrderItemDto;", "orderId", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface GoodsOrders {
        @NotNull
        Single<GoodsOrdersNewOrderItemDto> sendAppCreateGoodsOrder(long appId, @NotNull String itemId);

        @NotNull
        Single<GoodsOrdersOrderItemDto> sendAppGetGoodsOrder(int orderId);

        @NotNull
        Single<GoodsOrdersGoodItemDto> sendAppGetGoodsOrderInfo(long appId, @NotNull String itemId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\"\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\b0\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\bH&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H&J8\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H&J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J.\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0006H&¨\u0006\u001a"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Group;", "", "sendGetGroupShortInfo", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/group/WebGroupShortInfo;", "groupId", "", "sendGetGroupsShortInfo", "", "groupIds", "sendGroupsGetById", "Lcom/vk/superapp/api/dto/group/WebGroup;", "sendGroupsGetByIds", "sendGroupsIsMember", "", BlockstoreDeleteReceiver.PARAM_USER_ID, "sendGroupsJoin", "unsure", "refer", "", "inviteCode", "sendGroupsLeave", "sendGroupsSendPayload", "appId", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "time", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Group {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable sendGroupsJoin$default(Group group, long j10, boolean z10, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGroupsJoin");
                }
                if ((i10 & 2) != 0) {
                    z10 = false;
                }
                return group.sendGroupsJoin(j10, z10, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : str2);
            }
        }

        @NotNull
        Observable<WebGroupShortInfo> sendGetGroupShortInfo(long groupId);

        @NotNull
        Observable<List<WebGroupShortInfo>> sendGetGroupsShortInfo(@NotNull List<Long> groupIds);

        @NotNull
        Observable<WebGroup> sendGroupsGetById(long groupId);

        @NotNull
        Observable<List<WebGroup>> sendGroupsGetByIds(@NotNull List<Long> groupIds);

        @NotNull
        Observable<Boolean> sendGroupsIsMember(long groupId, long userId);

        @NotNull
        Observable<Boolean> sendGroupsJoin(long groupId, boolean unsure, @Nullable String refer, @Nullable String inviteCode);

        @NotNull
        Observable<Boolean> sendGroupsLeave(long groupId);

        @NotNull
        Observable<Boolean> sendGroupsSendPayload(long appId, long groupId, @NotNull String payload, long time);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u001c\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00032\u0006\u0010\b\u001a\u00020\tH&J6\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\tH&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\tH&J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\tH&J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u001a\u001a\u00020\u000fH&J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u001a\u001a\u00020\u000fH&J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u001a\u001a\u00020\u000fH&J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\u001e\u001a\u00020\u000bH&J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\u0006\u0010\u0014\u001a\u00020\u0013H&J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0006\u0010!\u001a\u00020\u0016H&¨\u0006\""}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "", "getCard", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/identity/WebIdentityCardData;", "getLabels", "", "Lcom/vk/superapp/api/dto/identity/WebIdentityLabel;", "type", "", "addAddress", "Lcom/vk/superapp/api/dto/identity/WebIdentityAddress;", "label", "specifiedAddress", "countryId", "", "cityId", "postalCode", "addEmail", "Lcom/vk/superapp/api/dto/identity/WebIdentityEmail;", "email", "addPhone", "Lcom/vk/superapp/api/dto/identity/WebIdentityPhone;", "phoneNumber", "deleteAddress", "", "id", "deleteEmail", "deletePhone", "editAddress", "address", "editEmail", "editPhone", "phone", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Identity {
        @NotNull
        Single<WebIdentityAddress> addAddress(@NotNull WebIdentityLabel label, @NotNull String specifiedAddress, int countryId, int cityId, @NotNull String postalCode);

        @NotNull
        Single<WebIdentityEmail> addEmail(@NotNull WebIdentityLabel label, @NotNull String email);

        @NotNull
        Single<WebIdentityPhone> addPhone(@NotNull WebIdentityLabel label, @NotNull String phoneNumber);

        @NotNull
        Single<Boolean> deleteAddress(int id2);

        @NotNull
        Single<Boolean> deleteEmail(int id2);

        @NotNull
        Single<Boolean> deletePhone(int id2);

        @NotNull
        Single<WebIdentityAddress> editAddress(@NotNull WebIdentityAddress address);

        @NotNull
        Single<WebIdentityEmail> editEmail(@NotNull WebIdentityEmail email);

        @NotNull
        Single<WebIdentityPhone> editPhone(@NotNull WebIdentityPhone phone);

        @NotNull
        Single<WebIdentityCardData> getCard();

        @NotNull
        Single<List<WebIdentityLabel>> getLabels(@NotNull String type);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J8\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0010H&¨\u0006\u0015"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "", "sendGroupsIsMessagesAllowed", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/group/WebGroupMessageStatus;", "groupId", "", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", "intents", "", "Lcom/vk/superapp/api/dto/app/AppIntent;", "sendGroupsAllowMessages", "", "appId", "key", "", "sendMessagesToChat", "Lio/reactivex/rxjava3/core/Single;", "peerId", TornadoSendRequest.FIELD_TEMPLATE, "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Messages {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable sendGroupsAllowMessages$default(Messages messages, long j10, long j11, List list, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendGroupsAllowMessages");
                }
                if ((i10 & 8) != 0) {
                    str = null;
                }
                return messages.sendGroupsAllowMessages(j10, j11, list, str);
            }
        }

        @NotNull
        Observable<Boolean> sendGroupsAllowMessages(long appId, long groupId, @NotNull List<? extends AppIntent> intents, @Nullable String key);

        @NotNull
        Observable<WebGroupMessageStatus> sendGroupsIsMessagesAllowed(long groupId, @NotNull UserId userId, @NotNull List<? extends AppIntent> intents);

        @NotNull
        Single<Boolean> sendMessagesToChat(long peerId, @NotNull String template);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&¨\u0006\r"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "", "sendAppsAllowNotifications", "Lio/reactivex/rxjava3/core/Observable;", "", "appId", "", "sendAppsDenyNotifications", "sendAppsIsNotificationsAllowed", "hideNotification", "Lio/reactivex/rxjava3/core/Single;", "query", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Notification {
        @NotNull
        Single<Boolean> hideNotification(@Nullable String query);

        @NotNull
        Observable<Boolean> sendAppsAllowNotifications(long appId);

        @NotNull
        Observable<Boolean> sendAppsDenyNotifications(long appId);

        @NotNull
        Observable<Boolean> sendAppsIsNotificationsAllowed(long appId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Orders;", "", "getPersonalDiscount", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/personal/PersonalDiscount;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Orders {
        @NotNull
        Single<PersonalDiscount> getPersonalDiscount();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "", "sendAppsGetDevicePermissionsRequest", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/AppPermissions;", "appId", "", "sendAppsSetDevicePermissionsRequest", "", "name", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Permission {
        @NotNull
        Observable<AppPermissions> sendAppsGetDevicePermissionsRequest(long appId);

        @NotNull
        Observable<Boolean> sendAppsSetDevicePermissionsRequest(long appId, @NotNull String name);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "", "setAuthCodeStatus", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/auth/dto/AuthSetAuthCodeStatusResponseDto;", "authCode", "", "terminateAuthCode", "Lcom/vk/api/generated/auth/dto/AuthTerminateAuthCodeResponseDto;", "getAuthCodeStatus", "Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface QrWebToApp {
        @NotNull
        Single<AuthGetAuthCodeStatusResponseDto> getAuthCodeStatus(@NotNull String authCode);

        @NotNull
        Single<AuthSetAuthCodeStatusResponseDto> setAuthCodeStatus(@NotNull String authCode);

        @NotNull
        Single<AuthTerminateAuthCodeResponseDto> terminateAuthCode(@NotNull String authCode);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006H&J3\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004H&¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u00100\u0003H&¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "", "sendActivateExternalOAuthService", "Lio/reactivex/rxjava3/core/Observable;", "", "externalCode", "", "vkExternalClient", "redirectUri", "service", "codeVerifier", "sendDeactivateExternalOAuthService", "authLabel", "isDeactivateAllAuthLabels", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/rxjava3/core/Observable;", "sendGetOAuthServices", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Settings {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable sendActivateExternalOAuthService$default(Settings settings, String str, String str2, String str3, String str4, String str5, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendActivateExternalOAuthService");
                }
                if ((i10 & 16) != 0) {
                    str5 = null;
                }
                return settings.sendActivateExternalOAuthService(str, str2, str3, str4, str5);
            }

            public static /* synthetic */ Observable sendDeactivateExternalOAuthService$default(Settings settings, String str, String str2, Boolean bool, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendDeactivateExternalOAuthService");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                if ((i10 & 4) != 0) {
                    bool = null;
                }
                return settings.sendDeactivateExternalOAuthService(str, str2, bool);
            }
        }

        @NotNull
        Observable<Boolean> sendActivateExternalOAuthService(@NotNull String externalCode, @NotNull String vkExternalClient, @NotNull String redirectUri, @NotNull String service, @Nullable String codeVerifier);

        @NotNull
        Observable<Boolean> sendDeactivateExternalOAuthService(@NotNull String service, @Nullable String authLabel, @Nullable Boolean isDeactivateAllAuthLabels);

        @NotNull
        Observable<Map<String, Boolean>> sendGetOAuthServices();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J7\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH&¨\u0006\u001a"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "", "sendStatsTrackVisitorRequest", "Lio/reactivex/rxjava3/core/Observable;", "", "appId", "", "sessionUuid", "", "sessionDuration", "", "type", "Lcom/vk/api/generated/stats/dto/StatsTrackVisitorTypeDto;", "(JLjava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/stats/dto/StatsTrackVisitorTypeDto;)Lio/reactivex/rxjava3/core/Observable;", "sendStatsTrackEventsRequest", "events", "sendStatEventsAddRequest", "", "Lcom/google/gson/JsonObject;", "sendStatEventsAnonymouslyAddRequest", "sendStatSAKMobileEventsAddRequest", "sendStatSAKMobileEventsAnonymouslyAddRequest", "sendStatAddLibverifyEvent", "Lio/reactivex/rxjava3/core/Single;", PasskeyBeginResult.SID_KEY, "validateSession", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Stat {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable sendStatsTrackVisitorRequest$default(Stat stat, long j10, String str, Integer num, StatsTrackVisitorTypeDto statsTrackVisitorTypeDto, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendStatsTrackVisitorRequest");
                }
                if ((i10 & 4) != 0) {
                    num = null;
                }
                return stat.sendStatsTrackVisitorRequest(j10, str, num, statsTrackVisitorTypeDto);
            }
        }

        @NotNull
        Single<Boolean> sendStatAddLibverifyEvent(@NotNull String sid, @NotNull String validateSession);

        @NotNull
        Observable<Boolean> sendStatEventsAddRequest(@NotNull List<JsonObject> events);

        @NotNull
        Observable<Boolean> sendStatEventsAnonymouslyAddRequest(@NotNull List<JsonObject> events);

        @NotNull
        Observable<Boolean> sendStatSAKMobileEventsAddRequest(@NotNull List<JsonObject> events);

        @NotNull
        Observable<Boolean> sendStatSAKMobileEventsAnonymouslyAddRequest(@NotNull List<JsonObject> events);

        @NotNull
        Observable<Object> sendStatsTrackEventsRequest(@NotNull String events, long appId);

        @NotNull
        Observable<Boolean> sendStatsTrackVisitorRequest(long appId, @NotNull String sessionUuid, @Nullable Integer sessionDuration, @NotNull StatsTrackVisitorTypeDto type);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0013"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "", "sendStorageGet", "Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONArray;", UserMetadata.KEYDATA_FILENAME, "", "", "appId", "", "([Ljava/lang/String;J)Lio/reactivex/rxjava3/core/Observable;", "sendStorageGetKeys", "offset", "", "count", "sendStorageSet", "", "key", "value", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Storage {
        @NotNull
        Observable<JSONArray> sendStorageGet(@NotNull String[] keys, long appId);

        @NotNull
        Observable<JSONArray> sendStorageGetKeys(long appId, int offset, int count);

        @NotNull
        Observable<Boolean> sendStorageSet(@NotNull String key, @NotNull String value, long appId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Store;", "", "getFillBalanceUrl", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/store/FillBalanceUrl;", "hasInAppBilling", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Store {
        @NotNull
        Single<FillBalanceUrl> getFillBalanceUrl(boolean hasInAppBilling);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H&J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tH&¨\u0006\u000e"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "", "sendSuperAppMarkBadgeAsClicked", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/menu/BadgeInfo;", "uid", "", "parentUid", "sendCloseOnboardingPanel", "Lio/reactivex/rxjava3/core/Single;", "", "trackCode", "sendGetSuperAppAnimation", "Lcom/vk/superapp/api/dto/menu/SuperAppAnimationConfig;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface SuperApp {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable sendSuperAppMarkBadgeAsClicked$default(SuperApp superApp, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendSuperAppMarkBadgeAsClicked");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return superApp.sendSuperAppMarkBadgeAsClicked(str, str2);
            }
        }

        @NotNull
        Single<Boolean> sendCloseOnboardingPanel(@NotNull String uid, @NotNull String trackCode);

        @NotNull
        Single<SuperAppAnimationConfig> sendGetSuperAppAnimation();

        @NotNull
        Observable<BadgeInfo> sendSuperAppMarkBadgeAsClicked(@NotNull String uid, @Nullable String parentUid);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "", "translate", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/translations/dto/TranslationsTranslateResponseDto;", "texts", "", "", "translateTo", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Translations {
        @NotNull
        Single<TranslationsTranslateResponseDto> translate(@NotNull List<String> texts, @NotNull String translateTo);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H&J\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004H&Jp\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\rH&J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004H&Jt\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00040\u001e2\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00042\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00042\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010$\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00042\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\tH&¨\u0006&"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Users;", "", "sendGetUsersShortInfo", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "appId", "", "userIds", "Lcom/vk/dto/common/id/UserId;", "sendSearchRestoreUsers", "Lcom/vk/superapp/api/dto/common/VkList;", CommonConstant.KEY_ACCESS_TOKEN, "", "query", "limit", "", "offset", "countryId", "cityId", "gender", "Lcom/vk/superapp/core/api/models/VkGender;", "ageFrom", "ageTo", "relationsStatus", "Lcom/vk/superapp/api/VkRelation;", "screenRef", "sendGetUserMyInfo", "Lorg/json/JSONObject;", "usersGet", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/users/dto/UsersUserFullDto;", "domains", "fields", "Lcom/vk/api/generated/users/dto/UsersFieldsDto;", "nameCase", "accessKeys", "fromGroupId", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Users {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Single usersGet$default(Users users, List list, List list2, List list3, String str, List list4, UserId userId, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: usersGet");
                }
                if ((i10 & 1) != 0) {
                    list = null;
                }
                if ((i10 & 2) != 0) {
                    list2 = null;
                }
                if ((i10 & 4) != 0) {
                    list3 = null;
                }
                if ((i10 & 8) != 0) {
                    str = null;
                }
                if ((i10 & 16) != 0) {
                    list4 = null;
                }
                if ((i10 & 32) != 0) {
                    userId = null;
                }
                return users.usersGet(list, list2, list3, str, list4, userId);
            }
        }

        @NotNull
        Observable<JSONObject> sendGetUserMyInfo(long appId, @NotNull List<Long> userIds);

        @NotNull
        Observable<List<WebUserShortInfo>> sendGetUsersShortInfo(long appId, @NotNull List<UserId> userIds);

        @NotNull
        Observable<List<WebUserShortInfo>> sendGetUsersShortInfo(@NotNull List<UserId> userIds);

        @NotNull
        Observable<VkList<WebUserShortInfo>> sendSearchRestoreUsers(@NotNull String accessToken, @Nullable String query, int limit, int offset, int countryId, int cityId, @NotNull VkGender gender, int ageFrom, int ageTo, @NotNull VkRelation relationsStatus, @Nullable String screenRef);

        @NotNull
        Single<List<UsersUserFullDto>> usersGet(@Nullable List<UserId> userIds, @Nullable List<UserId> domains, @Nullable List<? extends UsersFieldsDto> fields, @Nullable String nameCase, @Nullable List<String> accessKeys, @Nullable UserId fromGroupId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J2\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006H&J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u001e"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "", "getVerificationMethods", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetVerificationMethodsResponseDto;", PasskeyBeginResult.SID_KEY, "", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "sendOtpPush", "Lcom/vk/api/generated/ecosystem/dto/EcosystemSendOtpResponseDto;", "sendOtpSms", "sendOtpEmail", "sendOtpCallReset", "sendOtpOfficialMessenger", "sendMaxMessengerVerification", "getMaxSessionStatus", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetMaxSessionStatusResponseDto;", "maxMessengerHash", "sendMaxOtpCode", "checkOtp", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckOtpResponseDto;", "code", "verificationMethod", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckOtpVerificationMethodDto;", "checkPhoneReuse", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckPhoneReuseResponseDto;", "login", "superAppToken", "getValidationStatus", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetValidationStatusResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Verification {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single checkOtp$default(Verification verification, String str, String str2, String str3, EcosystemCheckOtpVerificationMethodDto ecosystemCheckOtpVerificationMethodDto, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkOtp");
                }
                if ((i10 & 8) != 0) {
                    ecosystemCheckOtpVerificationMethodDto = null;
                }
                return verification.checkOtp(str, str2, str3, ecosystemCheckOtpVerificationMethodDto);
            }
        }

        @NotNull
        Single<EcosystemCheckOtpResponseDto> checkOtp(@NotNull String sid, @NotNull String code, @NotNull String deviceId, @Nullable EcosystemCheckOtpVerificationMethodDto verificationMethod);

        @NotNull
        Single<EcosystemCheckPhoneReuseResponseDto> checkPhoneReuse(@NotNull String login, @Nullable String superAppToken);

        @NotNull
        Single<EcosystemGetMaxSessionStatusResponseDto> getMaxSessionStatus(@NotNull String sid, @NotNull String deviceId, @NotNull String maxMessengerHash);

        @NotNull
        Single<EcosystemGetValidationStatusResponseDto> getValidationStatus(@NotNull String sid);

        @NotNull
        Single<EcosystemGetVerificationMethodsResponseDto> getVerificationMethods(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendMaxMessengerVerification(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendMaxOtpCode(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendOtpCallReset(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendOtpEmail(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendOtpOfficialMessenger(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendOtpPush(@NotNull String sid, @NotNull String deviceId);

        @NotNull
        Single<EcosystemSendOtpResponseDto> sendOtpSms(@NotNull String sid, @NotNull String deviceId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000Â\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000bH&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u000bH&J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&J0\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bH&JX\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0006\u0010\u0014\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000b2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00042\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000bH&J'\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00032\u0006\u0010\u000f\u001a\u00020\u000bH&Jv\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\r2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u000b2\u0006\u00100\u001a\u00020'2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u00102\u001a\u00020'2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u00104\u001a\u00020'2\b\b\u0002\u00105\u001a\u00020'2\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000bH&J\u0016\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00032\u0006\u00109\u001a\u00020\u000bH&J\"\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;0\u00040\r2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004H&J\u0014\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020=0\u00040\rH&J\u0016\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00032\u0006\u0010\u0014\u001a\u00020\u001dH&J\u009c\u0001\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\r2\b\u0010B\u001a\u0004\u0018\u00010\u000b2\b\u0010C\u001a\u0004\u0018\u00010\u000b2\b\u0010D\u001a\u0004\u0018\u00010\u000b2\u0006\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u000b2\b\u0010H\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010I\u001a\u0004\u0018\u00010\u000b2\u0006\u0010J\u001a\u00020'2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010M\u001a\u00020'2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u000bH&Jh\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0\r2\b\u0010H\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010R\u001a\u0004\u0018\u00010\u000b2\b\u0010S\u001a\u0004\u0018\u00010\u000b2\b\u0010T\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010U\u001a\u00020'2\b\b\u0002\u0010M\u001a\u00020'2\b\b\u0002\u0010V\u001a\u00020'2\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u000bH&J\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020'0\r2\u0006\u0010\n\u001a\u00020\u000bH&Jz\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\u0010H\u001a\u0004\u0018\u00010\u000b2\u0006\u0010[\u001a\u00020'2\u0006\u00100\u001a\u00020'2\b\b\u0002\u0010U\u001a\u00020'2\b\b\u0002\u0010\\\u001a\u00020'2\b\b\u0002\u0010]\u001a\u00020'2\b\b\u0002\u0010^\u001a\u00020'2\b\b\u0002\u0010_\u001a\u00020'2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010a\u001a\u00020'H&J(\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\r2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020\u000b2\b\b\u0002\u0010U\u001a\u00020'H&J>\u0010d\u001a\b\u0012\u0004\u0012\u00020,0\r2\u0006\u0010-\u001a\u00020.2\u0006\u0010e\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u000bH&J.\u0010f\u001a\b\u0012\u0004\u0012\u00020,0\r2\u0006\u0010g\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010h\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u001dH&JB\u0010i\u001a\b\u0012\u0004\u0012\u00020j0\r2\u0006\u0010e\u001a\u00020\u000b2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010k\u001a\u00020\u000b2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004H&J8\u0010n\u001a\b\u0012\u0004\u0012\u00020,0\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010o\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u000bH&J\u001e\u0010q\u001a\b\u0012\u0004\u0012\u00020r0\r2\u0006\u0010s\u001a\u00020\u000b2\u0006\u0010t\u001a\u00020\u000bH&J&\u0010u\u001a\b\u0012\u0004\u0012\u00020r0\r2\u0006\u0010v\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020\u000b2\u0006\u0010x\u001a\u00020\u000bH&J\u001e\u0010y\u001a\b\u0012\u0004\u0012\u00020z0\r2\u0006\u0010{\u001a\u00020'2\u0006\u0010|\u001a\u00020\u000bH&J\u000e\u0010}\u001a\b\u0012\u0004\u0012\u00020~0\rH&J\u0017\u0010\u007f\u001a\t\u0012\u0005\u0012\u00030\u0080\u00010\r2\u0006\u0010\u000f\u001a\u00020\u000bH&J#\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020,0\r2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u000bH&J*\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\r2\u0007\u0010\u0084\u0001\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020'H&J,\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010\r2\u0007\u0010\u0088\u0001\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0007\u0010\u0089\u0001\u001a\u00020\u000bH&Ju\u0010\u008a\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010\r2\u0007\u0010\u0088\u0001\u001a\u00020\u000b2\u0007\u0010\u008c\u0001\u001a\u00020'2\b\u0010/\u001a\u0004\u0018\u00010\u000b2\r\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u000e\u0010\u008e\u0001\u001a\t\u0012\u0005\u0012\u00030\u008f\u00010\u00042\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010!\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000bH&J.\u0010\u0090\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\r2\u0006\u0010\u000f\u001a\u00020\u000b2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010U\u001a\u00020'H&J)\u0010\u0092\u0001\u001a\b\u0012\u0004\u0012\u00020Q0\r2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010R\u001a\u00020\u000b2\b\b\u0002\u0010U\u001a\u00020'H&J\u0018\u0010\u0093\u0001\u001a\t\u0012\u0005\u0012\u00030\u0094\u00010\u00032\u0006\u0010T\u001a\u00020\u000bH&J\"\u0010\u0095\u0001\u001a\t\u0012\u0005\u0012\u00030\u0096\u00010\r2\u0006\u00103\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bH&J\u0018\u0010\u0097\u0001\u001a\t\u0012\u0005\u0012\u00030\u0098\u00010\r2\u0006\u0010T\u001a\u00020\u000bH&J?\u0010\u0099\u0001\u001a\t\u0012\u0005\u0012\u00030\u009a\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u001d2\b\u0010e\u001a\u0004\u0018\u00010\u000b2\b\u0010k\u001a\u0004\u0018\u00010\u000b2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u000b2\u0006\u0010U\u001a\u00020'H&J=\u0010\u009c\u0001\u001a\t\u0012\u0005\u0012\u00030\u009d\u00010\u00032\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\t\b\u0002\u0010 \u0001\u001a\u00020\u001d2\b\b\u0002\u00101\u001a\u00020\u000bH&J.\u0010¡\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030¢\u00010\u00040\r2\u000e\u0010£\u0001\u001a\t\u0012\u0005\u0012\u00030¢\u00010\u00042\u0006\u0010`\u001a\u00020\u000bH&J\"\u0010¤\u0001\u001a\t\u0012\u0005\u0012\u00030¥\u00010\u00032\u0010\u0010£\u0001\u001a\u000b\u0012\u0005\u0012\u00030¢\u0001\u0018\u00010\u0004H&J(\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030§\u00010\u00032\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u000bH&¨\u0006¨\u0001"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "", "getSilentAuthProviders", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/vk/superapp/api/dto/auth/silentauthprovider/AuthSilentAuthProvider;", "getExchangeToken", "Lcom/vk/api/generated/auth/dto/AuthGetExchangeTokenResponseDto;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", CommonConstant.KEY_ACCESS_TOKEN, "", "authOnSuccessValidation", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/auth/dto/AuthOnSuccessValidationResponseDto;", PasskeyBeginResult.SID_KEY, "maxMessengerHash", "authMailOnSuccessValidation", "getCredentialsForApp", "Lcom/vk/superapp/api/dto/auth/appcredentials/VkAuthAppCredentials;", "appId", "", "getAutologinCredentials", "Lcom/vk/superapp/api/dto/auth/autologin/VkAuthAutologinCredentials;", SilentAuthInfo.KEY_UUID, "silentTokens", "sessionId", "authGetCredentialsForServiceMulti", "Lcom/vk/superapp/api/dto/auth/serviceauthmulti/AuthGetCredentialsForServiceMultiResponseModel;", "", "packageValue", "timestamp", "digestHash", "exchangeTokens", "clientDeviceId", "clientExternalDeviceId", "validatePhoneCheck", "Lcom/vk/superapp/api/dto/auth/validatephonecheck/AuthValidatePhoneCheckResponse;", "isAuth", "", "(ZLjava/lang/Long;)Lio/reactivex/rxjava3/core/Single;", "validatePhoneCheckSkip", "Lcom/vk/superapp/api/internal/requests/app/ConfirmResult;", "auth", "Lcom/vk/auth/api/models/AuthResult;", "authState", "Lcom/vk/superapp/api/states/VkAuthState;", "trustedHash", "libverifySupport", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "receiveCookiesSupport", "whiteLabelFlowInputSat", "fromBackup", "deviceTrustedHashSupported", "wereAction", "getExchangeTokenInfo", "Lcom/vk/superapp/api/dto/auth/exchangetokeninfo/VkAuthExchangeTokenInfo;", "exchangeToken", "getExchangeTokensInfo", "Lcom/vk/api/generated/auth/dto/AuthExchangeTokenInfoDto;", "getAppScopes", "Lcom/vk/superapp/api/dto/auth/VkAuthAppScope;", "getVkConnectConfig", "Lcom/vk/superapp/api/dto/auth/VkConnectRemoteConfig;", "signUp", "Lcom/vk/superapp/api/dto/auth/VkAuthSignUpResult;", "firstName", "lastName", "fullName", "gender", "Lcom/vk/superapp/core/api/models/VkGender;", "birthday", "phone", "password", "extendedAuth", "profileType", "email", "canSkipPassword", "inviteHash", "validateSession", "confirmPhone", "Lcom/vk/superapp/api/dto/auth/validatephoneconfirm/VkAuthConfirmResponse;", "code", SessionParamsProviderImpl.PARAM_SESSION_ID, "token", "forceRemoveAccessToken", "isCodeAutocomplete", "verificationType", "logout", "validatePhone", "Lcom/vk/superapp/api/dto/auth/VkAuthValidatePhoneResult;", "voice", "disablePartial", "allowPush", "allowEmail", "allowPasskey", "superAppToken", "allowSmsInbox", "getValidatePhoneInfo", "Lcom/vk/superapp/api/dto/auth/VkAuthValidatePhoneInfo;", "checkSilentToken", "silentToken", "extendPartialToken", "partialToken", "extendHash", "extendSilentToken", "Lcom/vk/superapp/api/dto/auth/VkAuthExtendedSilentToken;", "silentTokenUuid", "providedTokens", "providedUuids", "extendProvidedToken", "providedHash", "providedUuid", "getSilentTokenByExternalToken", "Lcom/vk/superapp/api/dto/odnoklassniki/OkFlowSilentTokenResponse;", "externalToken", "clientMetadata", "getSilentTokenByExternalCredentials", "externalLogin", "externalPassword", "externalData", "getEsiaSignature", "Lcom/vk/superapp/api/dto/auth/VkEsiaSignature;", "isVerificationFlow", "externalClientId", "getHashes", "Lcom/vk/superapp/api/dto/auth/VkAuthHashes;", "passkeyBegin", "Lcom/vk/superapp/api/dto/auth/PasskeyBeginResult;", "authByAccessToken", "allowAuthByQrCode", "Lcom/vk/superapp/api/dto/qr/QrInfoResponse;", "authCode", "isInternalCamera", "validateLogin", "Lcom/vk/superapp/api/dto/auth/validatelogin/VkAuthValidateLoginResponse;", "login", "source", "validateAccount", "Lcom/vk/superapp/api/dto/auth/validateaccount/VkAuthValidateAccountResponse;", "forcePassword", "trustedHashes", "supportedWays", "Lcom/vk/superapp/api/dto/auth/AuthSupportedWay;", "validateEmail", "Lcom/vk/api/generated/auth/dto/AuthValidateEmailResponseDto;", "validateEmailConfirm", "validateSuperappToken", "Lcom/vk/superapp/api/dto/auth/VkAuthValidateSuperappTokenResponse;", "externalFlowOut", "Lcom/vk/api/generated/auth/dto/AuthExternalFlowOutResponseDto;", "getUserInfoByToken", "Lcom/vk/superapp/api/dto/auth/GetUserInfoByPhone;", "getContinuationForService", "Lcom/vk/superapp/api/dto/auth/VkAuthGetContinuationForServiceResponse;", "phoneValidationSid", "refreshTokens", "Lcom/vk/api/generated/auth/dto/AuthRefreshTokensResponseDto;", "initiator", "Lcom/vk/superapp/api/internal/oauthrequests/AuthByExchangeTokenInitiator;", "activeIndex", "filterSilentTokens", "Lcom/vk/api/generated/auth/dto/AuthSilentTokenShortDto;", "silentTokensShort", "checkBindExtOAuth", "Lcom/vk/api/generated/auth/dto/AuthCheckBindExtOAuthResponseDto;", "bindExtOAuth", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkAuth {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Observable auth$default(VkAuth vkAuth, VkAuthState vkAuthState, String str, boolean z10, String str2, boolean z11, String str3, boolean z12, boolean z13, String str4, String str5, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: auth");
                }
                if ((i10 & 8) != 0) {
                    str2 = null;
                }
                if ((i10 & 16) != 0) {
                    z11 = false;
                }
                if ((i10 & 32) != 0) {
                    str3 = null;
                }
                if ((i10 & 64) != 0) {
                    z12 = false;
                }
                if ((i10 & 128) != 0) {
                    z13 = false;
                }
                if ((i10 & 256) != 0) {
                    str4 = null;
                }
                if ((i10 & 512) != 0) {
                    str5 = null;
                }
                return vkAuth.auth(vkAuthState, str, z10, str2, z11, str3, z12, z13, str4, str5);
            }

            public static /* synthetic */ Observable authByAccessToken$default(VkAuth vkAuth, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authByAccessToken");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return vkAuth.authByAccessToken(str, str2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Single authGetCredentialsForServiceMulti$default(VkAuth vkAuth, int i10, String str, String str2, String str3, List list, String str4, String str5, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetCredentialsForServiceMulti");
                }
                if ((i11 & 16) != 0) {
                    list = null;
                }
                if ((i11 & 32) != 0) {
                    str4 = null;
                }
                if ((i11 & 64) != 0) {
                    str5 = null;
                }
                return vkAuth.authGetCredentialsForServiceMulti(i10, str, str2, str3, list, str4, str5);
            }

            public static /* synthetic */ Observable authOnSuccessValidation$default(VkAuth vkAuth, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authOnSuccessValidation");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return vkAuth.authOnSuccessValidation(str, str2);
            }

            public static /* synthetic */ Single bindExtOAuth$default(VkAuth vkAuth, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bindExtOAuth");
                }
                if ((i10 & 1) != 0) {
                    str = null;
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return vkAuth.bindExtOAuth(str, str2);
            }

            public static /* synthetic */ Observable checkSilentToken$default(VkAuth vkAuth, VkAuthState vkAuthState, String str, String str2, String str3, String str4, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkSilentToken");
                }
                if ((i10 & 8) != 0) {
                    str3 = null;
                }
                if ((i10 & 16) != 0) {
                    str4 = null;
                }
                return vkAuth.checkSilentToken(vkAuthState, str, str2, str3, str4);
            }

            public static /* synthetic */ Observable confirmPhone$default(VkAuth vkAuth, String str, String str2, String str3, String str4, String str5, boolean z10, boolean z11, boolean z12, String str6, int i10, Object obj) {
                if (obj == null) {
                    return vkAuth.confirmPhone(str, str2, str3, str4, str5, (i10 & 32) != 0 ? true : z10, (i10 & 64) != 0 ? false : z11, (i10 & 128) != 0 ? false : z12, (i10 & 256) != 0 ? null : str6);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: confirmPhone");
            }

            public static /* synthetic */ Single getExchangeToken$default(VkAuth vkAuth, UserId userId, String str, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getExchangeToken");
                }
                if ((i10 & 2) != 0) {
                    str = null;
                }
                return vkAuth.getExchangeToken(userId, str);
            }

            public static /* synthetic */ Observable getValidatePhoneInfo$default(VkAuth vkAuth, String str, String str2, boolean z10, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValidatePhoneInfo");
                }
                if ((i10 & 4) != 0) {
                    z10 = true;
                }
                return vkAuth.getValidatePhoneInfo(str, str2, z10);
            }

            public static /* synthetic */ Single refreshTokens$default(VkAuth vkAuth, List list, AuthByExchangeTokenInitiator authByExchangeTokenInitiator, int i10, String str, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: refreshTokens");
                }
                if ((i11 & 4) != 0) {
                    i10 = 0;
                }
                if ((i11 & 8) != 0) {
                    str = "all";
                }
                return vkAuth.refreshTokens(list, authByExchangeTokenInitiator, i10, str);
            }

            public static /* synthetic */ Observable signUp$default(VkAuth vkAuth, String str, String str2, String str3, VkGender vkGender, String str4, String str5, String str6, String str7, boolean z10, String str8, String str9, boolean z11, String str10, String str11, int i10, Object obj) {
                if (obj == null) {
                    return vkAuth.signUp(str, str2, str3, vkGender, str4, str5, str6, str7, z10, (i10 & 512) != 0 ? null : str8, (i10 & 1024) != 0 ? null : str9, (i10 & 2048) != 0 ? false : z11, (i10 & 4096) != 0 ? null : str10, (i10 & 8192) != 0 ? null : str11);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: signUp");
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Observable validateAccount$default(VkAuth vkAuth, String str, boolean z10, String str2, List list, List list2, String str3, List list3, String str4, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validateAccount");
                }
                if ((i10 & 32) != 0) {
                    str3 = null;
                }
                if ((i10 & 64) != 0) {
                    list3 = null;
                }
                if ((i10 & 128) != 0) {
                    str4 = null;
                }
                return vkAuth.validateAccount(str, z10, str2, list, list2, str3, list3, str4);
            }

            public static /* synthetic */ Observable validateEmail$default(VkAuth vkAuth, String str, String str2, boolean z10, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validateEmail");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                if ((i10 & 4) != 0) {
                    z10 = true;
                }
                return vkAuth.validateEmail(str, str2, z10);
            }

            public static /* synthetic */ Observable validateEmailConfirm$default(VkAuth vkAuth, String str, String str2, boolean z10, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validateEmailConfirm");
                }
                if ((i10 & 4) != 0) {
                    z10 = true;
                }
                return vkAuth.validateEmailConfirm(str, str2, z10);
            }

            public static /* synthetic */ Observable validatePhone$default(VkAuth vkAuth, String str, String str2, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, String str3, boolean z17, int i10, Object obj) {
                if (obj == null) {
                    return vkAuth.validatePhone(str, str2, z10, z11, (i10 & 16) != 0 ? true : z12, (i10 & 32) != 0 ? false : z13, (i10 & 64) != 0 ? false : z14, (i10 & 128) != 0 ? false : z15, (i10 & 256) != 0 ? false : z16, (i10 & 512) != 0 ? null : str3, (i10 & 1024) != 0 ? false : z17);
                }
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validatePhone");
            }

            public static /* synthetic */ Single validatePhoneCheck$default(VkAuth vkAuth, boolean z10, Long l10, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: validatePhoneCheck");
                }
                if ((i10 & 2) != 0) {
                    l10 = null;
                }
                return vkAuth.validatePhoneCheck(z10, l10);
            }
        }

        @NotNull
        Observable<QrInfoResponse> allowAuthByQrCode(@NotNull String authCode, @NotNull UserId userId, boolean isInternalCamera);

        @NotNull
        Observable<AuthResult> auth(@NotNull VkAuthState authState, @Nullable String trustedHash, boolean libverifySupport, @Nullable String scope, boolean receiveCookiesSupport, @Nullable String whiteLabelFlowInputSat, boolean fromBackup, boolean deviceTrustedHashSupported, @Nullable String wereAction, @Nullable String maxMessengerHash);

        @NotNull
        Observable<AuthResult> authByAccessToken(@NotNull String accessToken, @Nullable String validateSession);

        @NotNull
        Single<AuthGetCredentialsForServiceMultiResponseModel> authGetCredentialsForServiceMulti(int appId, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable List<String> exchangeTokens, @Nullable String clientDeviceId, @Nullable String clientExternalDeviceId);

        @NotNull
        Observable<AuthOnSuccessValidationResponseDto> authMailOnSuccessValidation(@NotNull String sid);

        @NotNull
        Observable<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull String sid, @Nullable String maxMessengerHash);

        @NotNull
        Single<BaseOkResponseDto> bindExtOAuth(@Nullable String silentToken, @Nullable String uuid);

        @NotNull
        Single<AuthCheckBindExtOAuthResponseDto> checkBindExtOAuth(@Nullable List<AuthSilentTokenShortDto> silentTokensShort);

        @NotNull
        Observable<AuthResult> checkSilentToken(@NotNull VkAuthState authState, @NotNull String silentToken, @NotNull String uuid, @Nullable String sid, @Nullable String whiteLabelFlowInputSat);

        @NotNull
        Observable<VkAuthConfirmResponse> confirmPhone(@Nullable String phone, @NotNull String sid, @Nullable String code, @Nullable String session, @Nullable String token, boolean forceRemoveAccessToken, boolean canSkipPassword, boolean isCodeAutocomplete, @Nullable String verificationType);

        @NotNull
        Observable<AuthResult> extendPartialToken(@NotNull String partialToken, @NotNull String password, @NotNull String extendHash, int appId);

        @NotNull
        Observable<AuthResult> extendProvidedToken(@NotNull String accessToken, @NotNull String providedHash, @NotNull String providedUuid, @NotNull String clientDeviceId, @Nullable String clientExternalDeviceId);

        @NotNull
        Observable<VkAuthExtendedSilentToken> extendSilentToken(@NotNull String silentToken, @NotNull String password, @NotNull String silentTokenUuid, @NotNull List<String> providedTokens, @NotNull List<String> providedUuids);

        @NotNull
        Observable<AuthExternalFlowOutResponseDto> externalFlowOut(@NotNull String whiteLabelFlowInputSat, @Nullable String sid);

        @NotNull
        Observable<List<AuthSilentTokenShortDto>> filterSilentTokens(@NotNull List<AuthSilentTokenShortDto> silentTokensShort, @NotNull String superAppToken);

        @NotNull
        Observable<List<VkAuthAppScope>> getAppScopes();

        @NotNull
        Single<VkAuthAutologinCredentials> getAutologinCredentials(@Nullable String uuid, @NotNull List<String> silentTokens, @Nullable String sessionId);

        @NotNull
        Single<VkAuthGetContinuationForServiceResponse> getContinuationForService(int appId, @Nullable String silentToken, @Nullable String silentTokenUuid, @Nullable String phoneValidationSid, boolean forceRemoveAccessToken);

        @NotNull
        Single<List<VkAuthAppCredentials>> getCredentialsForApp(long appId);

        @NotNull
        Observable<VkEsiaSignature> getEsiaSignature(boolean isVerificationFlow, @NotNull String externalClientId);

        @NotNull
        Single<AuthGetExchangeTokenResponseDto> getExchangeToken(@NotNull UserId userId, @Nullable String accessToken);

        @NotNull
        Single<VkAuthExchangeTokenInfo> getExchangeTokenInfo(@NotNull String exchangeToken);

        @NotNull
        Observable<List<AuthExchangeTokenInfoDto>> getExchangeTokensInfo(@NotNull List<String> exchangeTokens);

        @NotNull
        Observable<VkAuthHashes> getHashes();

        @NotNull
        Single<List<AuthSilentAuthProvider>> getSilentAuthProviders();

        @NotNull
        Observable<OkFlowSilentTokenResponse> getSilentTokenByExternalCredentials(@NotNull String externalLogin, @NotNull String externalPassword, @NotNull String externalData);

        @NotNull
        Observable<OkFlowSilentTokenResponse> getSilentTokenByExternalToken(@NotNull String externalToken, @NotNull String clientMetadata);

        @NotNull
        Observable<GetUserInfoByPhone> getUserInfoByToken(@NotNull String token);

        @NotNull
        Observable<VkAuthValidatePhoneInfo> getValidatePhoneInfo(@NotNull String sid, @NotNull String phone, boolean forceRemoveAccessToken);

        @NotNull
        Single<VkConnectRemoteConfig> getVkConnectConfig(int appId);

        @NotNull
        Observable<Boolean> logout(@NotNull String accessToken);

        @NotNull
        Observable<PasskeyBeginResult> passkeyBegin(@NotNull String sid);

        @NotNull
        Single<AuthRefreshTokensResponseDto> refreshTokens(@NotNull List<String> exchangeTokens, @NotNull AuthByExchangeTokenInitiator initiator, int activeIndex, @NotNull String scope);

        @NotNull
        Observable<VkAuthSignUpResult> signUp(@Nullable String firstName, @Nullable String lastName, @Nullable String fullName, @NotNull VkGender gender, @Nullable String birthday, @Nullable String phone, @NotNull String sid, @Nullable String password, boolean extendedAuth, @Nullable String profileType, @Nullable String email, boolean canSkipPassword, @Nullable String inviteHash, @Nullable String validateSession);

        @NotNull
        Observable<VkAuthValidateAccountResponse> validateAccount(@NotNull String login, boolean forcePassword, @Nullable String trustedHash, @NotNull List<String> trustedHashes, @NotNull List<? extends AuthSupportedWay> supportedWays, @Nullable String superAppToken, @Nullable List<String> exchangeTokens, @Nullable String sid);

        @NotNull
        Observable<AuthValidateEmailResponseDto> validateEmail(@NotNull String sid, @Nullable String email, boolean forceRemoveAccessToken);

        @NotNull
        Observable<VkAuthConfirmResponse> validateEmailConfirm(@NotNull String sid, @NotNull String code, boolean forceRemoveAccessToken);

        @NotNull
        Observable<VkAuthValidateLoginResponse> validateLogin(@NotNull String login, @Nullable String sid, @NotNull String source);

        @NotNull
        Observable<VkAuthValidatePhoneResult> validatePhone(@Nullable String sid, @Nullable String phone, boolean voice, boolean libverifySupport, boolean forceRemoveAccessToken, boolean disablePartial, boolean allowPush, boolean allowEmail, boolean allowPasskey, @Nullable String superAppToken, boolean allowSmsInbox);

        @NotNull
        Single<AuthValidatePhoneCheckResponse> validatePhoneCheck(boolean isAuth, @Nullable Long appId);

        @NotNull
        Single<ConfirmResult> validatePhoneCheckSkip(@NotNull String sid);

        @NotNull
        Single<VkAuthValidateSuperappTokenResponse> validateSuperappToken(@NotNull String token);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "", "getHealthCommonClientConfig", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/healthCommon/dto/HealthCommonClientConfigDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkHealth {
        @NotNull
        Single<HealthCommonClientConfigDto> getHealthCommonClientConfig();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H&J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\bH&J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H&J\u001e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H&J&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\bH&J<\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\b2\b\u0010 \u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH&¨\u0006!"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "", "getMailAccBindList", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/vkidmail/dto/VkidmailBindListResponseDto;", "exchangeToken", "Lcom/vk/api/generated/vkidmail/dto/VkidmailExchangeTokenResponseDto;", "email", "", "clientAppId", "linkedEmails", "Lcom/vk/api/generated/vkidmail/dto/VkidmailLinkedEmailsResponseDto;", "checkRestore", "Lcom/vk/superapp/api/dto/auth/vkidmail/VkidMailCheckRestoreResponseDto;", AppConfigRequestHandler.FEATURES_KEY, "checkPassword", "Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailCheckPasswordResult;", "password", PasskeyBeginResult.SID_KEY, "bindFlow", "", "authOnSuccessValidation", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/auth/dto/AuthOnSuccessValidationResponseDto;", "getUserBlockStatusInfo", "Lcom/vk/superapp/api/dto/auth/vkidmail/userblocked/model/VkIDMailUserBlockStatusResult;", "silentToken", SilentAuthInfo.KEY_UUID, "getSilentToken", "Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailSilentTokenResultDto;", "redirectDomain", "codeChallenge", "codeChallengeMethod", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkIdMail {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single checkRestore$default(VkIdMail vkIdMail, String str, String str2, int i10, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkRestore");
                }
                if ((i10 & 2) != 0) {
                    str2 = null;
                }
                return vkIdMail.checkRestore(str, str2);
            }
        }

        @NotNull
        Observable<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull String sid, boolean bindFlow);

        @NotNull
        Single<MailCheckPasswordResult> checkPassword(@NotNull String password, @NotNull String sid, boolean bindFlow);

        @NotNull
        Single<VkidMailCheckRestoreResponseDto> checkRestore(@NotNull String email, @Nullable String features);

        @NotNull
        Single<VkidmailExchangeTokenResponseDto> exchangeToken(@NotNull String email, @NotNull String clientAppId);

        @NotNull
        Single<VkidmailBindListResponseDto> getMailAccBindList();

        @NotNull
        Single<MailSilentTokenResultDto> getSilentToken(@NotNull String email, @NotNull String redirectDomain, @Nullable String codeChallenge, @Nullable String codeChallengeMethod, @Nullable String sid);

        @NotNull
        Single<VkIDMailUserBlockStatusResult> getUserBlockStatusInfo(@NotNull String email, @NotNull String silentToken, @NotNull String uuid);

        @NotNull
        Single<VkidmailLinkedEmailsResponseDto> linkedEmails();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "", "getInstantAuthByNotifyInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/restore/VkRestoreInstantAuth;", "code", "", "confirmInstantAuthByNotify", "Lcom/vk/superapp/api/dto/restore/VkRestoreConfirmInstantResult;", "isConfirmed", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkRestore {
        @NotNull
        Single<VkRestoreConfirmInstantResult> confirmInstantAuthByNotify(int code, boolean isConfirmed);

        @NotNull
        Single<VkRestoreInstantAuth> getInstantAuthByNotifyInfo(int code);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&JN\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0004H&¨\u0006\u0015"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "", "importSteps", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/vk/external/miniapp/net/vkrun/StepCounterInfo;", "list", "source", "", "canSendManualData", "", "setSteps", "Lcom/vk/superapp/api/internal/requests/vkrun/VkRunStepsResponse;", "steps", "", "distanceKm", "", "manualSteps", "manualDistanceKm", ErrorBundle.DETAIL_ENTRY, "Lcom/google/gson/JsonObject;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkRun {
        @NotNull
        Single<List<StepCounterInfo>> importSteps(@NotNull List<StepCounterInfo> list, @NotNull String source, boolean canSendManualData);

        @NotNull
        Single<VkRunStepsResponse> setSteps(int steps, float distanceKm, int manualSteps, float manualDistanceKm, @NotNull String source, boolean canSendManualData, @Nullable List<JsonObject> details);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\u0006H&J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\b\u001a\u00020\u0006H&¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "", "guessUserSex", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/core/api/models/VkGender;", "firstName", "", "lastName", "fullName", "checkName", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkUtils {
        @NotNull
        Observable<Boolean> checkName(@NotNull String fullName);

        @NotNull
        Observable<Boolean> checkName(@NotNull String firstName, @NotNull String lastName);

        @NotNull
        Observable<VkGender> guessUserSex(@NotNull String fullName);

        @NotNull
        Observable<VkGender> guessUserSex(@NotNull String firstName, @NotNull String lastName);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J@\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00060\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "", "importWorkouts", "Lio/reactivex/rxjava3/core/Single;", "", "list", "", "Lcom/vk/superapp/api/dto/vkworkout/WorkoutData;", "getStats", "Lcom/vk/api/generated/vkStart/dto/VkStartStatsListItemDto;", "range", "", "aggregationType", "Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsAggregationTypeDto;", "activityType", "Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsActivityTypeDto;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkWorkout {
        @NotNull
        Single<List<VkStartStatsListItemDto>> getStats(@NotNull List<String> range, @Nullable VkStartGetStatsAggregationTypeDto aggregationType, @Nullable VkStartGetStatsActivityTypeDto activityType, @Nullable UserId userId);

        @NotNull
        Single<Boolean> importWorkouts(@NotNull List<WorkoutData> list);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0011\u0010\u0005\u001a\r\u0012\t\u0012\u00070\u0007¢\u0006\u0002\b\b0\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH&JB\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\nH&J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010\t\u001a\u00020\nH&J.\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00032\u0006\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\nH&¨\u0006%"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "", "authExchangeSilentTokensToSid", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/auth/dto/AuthExchangeSilentTokenToSidResponseDto;", "silentTokens", "", "Lcom/google/gson/JsonObject;", "Lkotlinx/parcelize/RawValue;", PasskeyBeginResult.SID_KEY, "", "phone", "checkPassword", "Lcom/vk/api/generated/vkidok/dto/VkidokCheckPasswordResponseDto;", "password", "checkPersonalInfo", "Lcom/vk/api/generated/vkidok/dto/VkidokCheckPersonalInfoResponseDto;", "firstName", "lastName", "birthday", "gender", "", "maxMessengerHash", "externalItsMe", "Lcom/vk/api/generated/vkidok/dto/VkidokExternalItsMeResponseDto;", "externalItsNotMe", "Lcom/vk/api/generated/vkidok/dto/VkidokExternalItsNotMeResponseDto;", "internalItsMe", "Lcom/vk/api/generated/vkidok/dto/VkidokInternalItsMeResponseDto;", "internalItsNotMe", "Lcom/vk/api/generated/vkidok/dto/VkidokInternalItsNotMeResponseDto;", "startRegistration", "Lcom/vk/api/generated/vkidok/dto/VkidokStartRegistrationResponseDto;", "platform", "lang", "clientMetadata", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface VkidOk {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class DefaultImpls {
            public static /* synthetic */ Single checkPersonalInfo$default(VkidOk vkidOk, String str, String str2, String str3, String str4, int i10, String str5, int i11, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkPersonalInfo");
                }
                if ((i11 & 32) != 0) {
                    str5 = null;
                }
                return vkidOk.checkPersonalInfo(str, str2, str3, str4, i10, str5);
            }
        }

        @NotNull
        Single<AuthExchangeSilentTokenToSidResponseDto> authExchangeSilentTokensToSid(@NotNull List<JsonObject> silentTokens, @NotNull String sid, @NotNull String phone);

        @NotNull
        Single<VkidokCheckPasswordResponseDto> checkPassword(@NotNull String sid, @NotNull String password);

        @NotNull
        Single<VkidokCheckPersonalInfoResponseDto> checkPersonalInfo(@NotNull String sid, @NotNull String firstName, @NotNull String lastName, @NotNull String birthday, int gender, @Nullable String maxMessengerHash);

        @NotNull
        Single<VkidokExternalItsMeResponseDto> externalItsMe(@NotNull String sid);

        @NotNull
        Single<VkidokExternalItsNotMeResponseDto> externalItsNotMe(@NotNull String sid);

        @NotNull
        Single<VkidokInternalItsMeResponseDto> internalItsMe(@NotNull String sid);

        @NotNull
        Single<VkidokInternalItsNotMeResponseDto> internalItsNotMe(@NotNull String sid);

        @NotNull
        Single<VkidokStartRegistrationResponseDto> startRegistration(@NotNull String platform, @NotNull String lang, @NotNull String clientMetadata, @NotNull String deviceId);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J&\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "", "getUniWidgets", "Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONObject;", "sendCallbackEvent", "Lio/reactivex/rxjava3/core/Single;", "", "peerId", "", Constants.WIDGET_ID_PARAMETER, "", "action", "Lcom/vk/superapp/api/dto/widgets/actions/WebActionCallback;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Widgets {
        @NotNull
        Observable<JSONObject> getUniWidgets();

        @NotNull
        Single<Boolean> sendCallbackEvent(@NotNull String peerId, int widgetId, @NotNull WebActionCallback action);
    }
}
