package com.vk.superapp.api.contract;

import android.location.Location;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.gson.JsonObject;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.vk.api.generated.account.dto.AccountCheckPasswordResponseDto;
import com.vk.api.generated.account.dto.AccountGetEmailResponseDto;
import com.vk.api.generated.account.dto.AccountGetPhoneResponseDto;
import com.vk.api.generated.account.dto.AccountGetProfileNavigationInfoResponseDto;
import com.vk.api.generated.account.dto.AccountGetProfilesSwitcherInfoResponseDto;
import com.vk.api.generated.account.dto.AccountGetTogglesResponseDto;
import com.vk.api.generated.account.dto.AccountGetUserObjectDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationCreateLinkPlatformDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationCreateLinkProviderDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationGetSessionInfoPlatformDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationGetSessionInfoProviderDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationGetSessionInfoResponseDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationLinkWithVerifyPlatformDto;
import com.vk.api.generated.accountVerification.dto.AccountVerificationLinkWithVerifyProviderDto;
import com.vk.api.generated.ads.dto.AdsRetargetingHitDto;
import com.vk.api.generated.appWidgets.dto.AppWidgetsGetWidgetPreviewTypeDto;
import com.vk.api.generated.apps.dto.AppsActionBannerDto;
import com.vk.api.generated.apps.dto.AppsAdsSlotsDto;
import com.vk.api.generated.apps.dto.AppsAdsSlotsWebConfigItemDto;
import com.vk.api.generated.apps.dto.AppsCatalogActivityItemDto;
import com.vk.api.generated.apps.dto.AppsCatalogListDto;
import com.vk.api.generated.apps.dto.AppsCheckAllowedScopesScopesDto;
import com.vk.api.generated.apps.dto.AppsCheckInviteFriendResponseDto;
import com.vk.api.generated.apps.dto.AppsClearRecentsPlatformDto;
import com.vk.api.generated.apps.dto.AppsGetActionMenuAppsResponseDto;
import com.vk.api.generated.apps.dto.AppsGetDevicePermissionsResponseDto;
import com.vk.api.generated.apps.dto.AppsGetEmbeddedUrlResponseDto;
import com.vk.api.generated.apps.dto.AppsGetFriendsListExtendedResponseDto;
import com.vk.api.generated.apps.dto.AppsGetGroupsListItemDto;
import com.vk.api.generated.apps.dto.AppsGetGroupsListResponseDto;
import com.vk.api.generated.apps.dto.AppsGetLeaderboardByAppResponseDto;
import com.vk.api.generated.apps.dto.AppsGetRecommendationsPlatformDto;
import com.vk.api.generated.apps.dto.AppsGetRecommendationsResponseDto;
import com.vk.api.generated.apps.dto.AppsGetResponseDto;
import com.vk.api.generated.apps.dto.AppsGetScopesResponseDto;
import com.vk.api.generated.apps.dto.AppsGetScopesTypeDto;
import com.vk.api.generated.apps.dto.AppsGetSecretHashResponseDto;
import com.vk.api.generated.apps.dto.AppsGetTrackBridgeCallHandlersResponseDto;
import com.vk.api.generated.apps.dto.AppsInviteMultipleFriendResponseDto;
import com.vk.api.generated.apps.dto.AppsIsNotificationsAllowedResponseDto;
import com.vk.api.generated.apps.dto.AppsJoinAndGetResponseDto;
import com.vk.api.generated.apps.dto.AppsMemberAllowedScopeItemDto;
import com.vk.api.generated.apps.dto.AppsMiniappsCatalogDto;
import com.vk.api.generated.apps.dto.AppsMiniappsCatalogItemPayloadListDto;
import com.vk.api.generated.apps.dto.AppsNeedToShowActionPlaceIdDto;
import com.vk.api.generated.apps.dto.AppsNeedToShowActionResponseDto;
import com.vk.api.generated.apps.dto.AppsSearchFiltersDto;
import com.vk.api.generated.apps.dto.AppsSearchResponseDto;
import com.vk.api.generated.apps.dto.AppsSetActionShownActionTypeDto;
import com.vk.api.generated.apps.dto.AppsSetActionShownShowTypeDto;
import com.vk.api.generated.apps.dto.AppsStartCallResponseDto;
import com.vk.api.generated.apps.dto.AppsVkAppsSectionDto;
import com.vk.api.generated.auth.dto.AuthCheckAccessResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckBindExtOAuthResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeSilentTokenToSidResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeTokenInfoDto;
import com.vk.api.generated.auth.dto.AuthExternalFlowOutPlatformDto;
import com.vk.api.generated.auth.dto.AuthExternalFlowOutResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAutologinCredentialsResponseDto;
import com.vk.api.generated.auth.dto.AuthGetCredentialsForServiceMultiResponseDto;
import com.vk.api.generated.auth.dto.AuthGetExchangeTokenInfoResponseDto;
import com.vk.api.generated.auth.dto.AuthGetExchangeTokenResponseDto;
import com.vk.api.generated.auth.dto.AuthGetSilentTokensResponseDto;
import com.vk.api.generated.auth.dto.AuthInitPasswordCheckResponseDto;
import com.vk.api.generated.auth.dto.AuthOnSuccessValidationResponseDto;
import com.vk.api.generated.auth.dto.AuthRefreshTokensResponseDto;
import com.vk.api.generated.auth.dto.AuthSetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthSignupSexDto;
import com.vk.api.generated.auth.dto.AuthSilentProviderDto;
import com.vk.api.generated.auth.dto.AuthSilentTokenShortDto;
import com.vk.api.generated.auth.dto.AuthTerminateAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateAccountResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateAccountSupportedWaysDto;
import com.vk.api.generated.auth.dto.AuthValidateEmailResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateLoginResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneCheckResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneConfirmResponseDto;
import com.vk.api.generated.base.dto.BaseBoolIntDto;
import com.vk.api.generated.base.dto.BaseCreateResponseDto;
import com.vk.api.generated.base.dto.BaseImageDto;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.base.dto.BaseUserGroupFieldsDto;
import com.vk.api.generated.database.dto.DatabaseGetCitiesResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemAddLibverifyEventEventTypeDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckOtpResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckOtpVerificationMethodDto;
import com.vk.api.generated.ecosystem.dto.EcosystemCheckPhoneReuseResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetMaxSessionStatusResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetValidationStatusResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemGetVerificationMethodsResponseDto;
import com.vk.api.generated.ecosystem.dto.EcosystemSendOtpResponseDto;
import com.vk.api.generated.email.dto.EmailCreationResponseDto;
import com.vk.api.generated.esia.dto.EsiaCheckEsiaLinkFlowDto;
import com.vk.api.generated.esia.dto.EsiaCheckEsiaLinkResponseDto;
import com.vk.api.generated.esia.dto.EsiaGetEsiaUserInfoFlowDto;
import com.vk.api.generated.esia.dto.EsiaGetEsiaUserInfoResponseDto;
import com.vk.api.generated.friends.dto.FriendsGetFieldsResponseDto;
import com.vk.api.generated.friends.dto.FriendsGetOrderDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersGoodItemDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersNewOrderItemDto;
import com.vk.api.generated.goodsOrders.dto.GoodsOrdersOrderItemDto;
import com.vk.api.generated.groups.dto.GroupsFieldsDto;
import com.vk.api.generated.groups.dto.GroupsGetByIdObjectResponseDto;
import com.vk.api.generated.healthCommon.dto.HealthCommonClientConfigDto;
import com.vk.api.generated.identity.dto.IdentityAddAddressLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityAddEmailLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityAddPhoneLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityAddressResponseDto;
import com.vk.api.generated.identity.dto.IdentityEditAddressLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityEditEmailLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityEditPhoneLabelIdDto;
import com.vk.api.generated.identity.dto.IdentityGetCardResponseDto;
import com.vk.api.generated.identity.dto.IdentityGetLabelsTypeDto;
import com.vk.api.generated.identity.dto.IdentityLabelDto;
import com.vk.api.generated.identity.dto.IdentityPhoneResponseDto;
import com.vk.api.generated.messages.dto.MessagesIsMessagesFromGroupAllowedResponseDto;
import com.vk.api.generated.messages.dto.MessagesSendResponseDto;
import com.vk.api.generated.orders.dto.OrdersAppOrderItemDto;
import com.vk.api.generated.orders.dto.OrdersAppSubscriptionItemDto;
import com.vk.api.generated.orders.dto.OrdersBuyItemResponseDto;
import com.vk.api.generated.orders.dto.OrdersConfirmOrderAutoBuyCheckedDto;
import com.vk.api.generated.orders.dto.OrdersConfirmSubscriptionResponseDto;
import com.vk.api.generated.orders.dto.OrdersOrderDto;
import com.vk.api.generated.orders.dto.OrdersPersonalDiscountDto;
import com.vk.api.generated.orders.dto.OrdersSubscriptionDto;
import com.vk.api.generated.restore.dto.RestoreConfirmInstantAuthByNotifyIsConfirmedDto;
import com.vk.api.generated.restore.dto.RestoreGetInstantAuthByNotifyInfoResponseDto;
import com.vk.api.generated.settings.dto.SettingsOAuthServicesResponseDto;
import com.vk.api.generated.statEvents.dto.StatEventsBaseResponseDto;
import com.vk.api.generated.stats.dto.StatsTrackVisitorTypeDto;
import com.vk.api.generated.superApp.dto.SuperAppBadgeInfoDto;
import com.vk.api.generated.superApp.dto.SuperAppGetAnimationsResponseDto;
import com.vk.api.generated.superApp.dto.SuperAppGetBirthdayResponseDto;
import com.vk.api.generated.superAppShowcase.dto.SuperAppShowcaseMarkBadgeAsClickedDataDto;
import com.vk.api.generated.translations.dto.TranslationsTranslateResponseDto;
import com.vk.api.generated.users.dto.UsersFieldsDto;
import com.vk.api.generated.users.dto.UsersSearchResponseDto;
import com.vk.api.generated.users.dto.UsersSearchSexDto;
import com.vk.api.generated.users.dto.UsersSearchStatusDto;
import com.vk.api.generated.users.dto.UsersUserFullDto;
import com.vk.api.generated.utils.dto.UtilsGuessUserSexResponseDto;
import com.vk.api.generated.vkRun.dto.VkRunImportSourceDto;
import com.vk.api.generated.vkRun.dto.VkRunSetStepsResponseDto;
import com.vk.api.generated.vkRun.dto.VkRunSetStepsSourceDto;
import com.vk.api.generated.vkRun.dto.VkRunStepsListItemDto;
import com.vk.api.generated.vkStart.dto.VkStartGetStatsActivityTypeDto;
import com.vk.api.generated.vkStart.dto.VkStartGetStatsAggregationTypeDto;
import com.vk.api.generated.vkStart.dto.VkStartStatsListItemDto;
import com.vk.api.generated.vkidmail.dto.VkidmailBindListResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailCheckPasswordResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailCheckRestoreResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailExchangeTokenResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailLinkedEmailsResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailSilentAuthTokenResponseDto;
import com.vk.api.generated.vkidmail.dto.VkidmailUserBlockStatusResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokCheckPasswordResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokCheckPersonalInfoResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokExternalItsMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokExternalItsNotMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokInternalItsMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokInternalItsNotMeResponseDto;
import com.vk.api.generated.vkidok.dto.VkidokStartRegistrationResponseDto;
import com.vk.api.sdk.auth.VKAccessTokenProvider;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.auth.api.models.AuthResult;
import com.vk.auth.verification.base.BaseCheckFragment;
import com.vk.common.api.generated.users.UsersService;
import com.vk.core.extensions.StringExtKt;
import com.vk.core.util.LangUtils;
import com.vk.dto.common.id.UserId;
import com.vk.external.miniapp.net.ad.AdvertisementConfig;
import com.vk.external.miniapp.net.app.AppFields;
import com.vk.external.miniapp.net.app.WebApiApplication;
import com.vk.external.miniapp.net.vkrun.StepCounterInfo;
import com.vk.lists.PaginationHelper;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.VkRelation;
import com.vk.superapp.api.contract.mappers.AccountMapper;
import com.vk.superapp.api.contract.mappers.AdvertisementMapper;
import com.vk.superapp.api.contract.mappers.AppMapper;
import com.vk.superapp.api.contract.mappers.BirthdayMapper;
import com.vk.superapp.api.contract.mappers.CommonMapper;
import com.vk.superapp.api.contract.mappers.DatabaseMapper;
import com.vk.superapp.api.contract.mappers.EmailMapper;
import com.vk.superapp.api.contract.mappers.FriendsMapper;
import com.vk.superapp.api.contract.mappers.GroupMapper;
import com.vk.superapp.api.contract.mappers.IdentityMapper;
import com.vk.superapp.api.contract.mappers.ImageMapper;
import com.vk.superapp.api.contract.mappers.MessagesMapper;
import com.vk.superapp.api.contract.mappers.RestoreMapper;
import com.vk.superapp.api.contract.mappers.SettingsMapper;
import com.vk.superapp.api.contract.mappers.UsersMapper;
import com.vk.superapp.api.contract.mappers.VkPermissionMapper;
import com.vk.superapp.api.contract.mappers.VkRunMapper;
import com.vk.superapp.api.contract.mappers.VkWorkoutMapper;
import com.vk.superapp.api.contract.mappers.personal.BannerMapper;
import com.vk.superapp.api.contract.mappers.personal.PersonalDiscountMapper;
import com.vk.superapp.api.contract.mappers.store.FillBalanceUrlMapper;
import com.vk.superapp.api.core.SuperappApiCore;
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
import com.vk.superapp.api.dto.app.AppsGroupsContainerKt;
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
import com.vk.superapp.api.dto.auth.AuthSupportedWayKt;
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
import com.vk.superapp.api.dto.auth.appcredentials.VkAuthAppCredentialsKt;
import com.vk.superapp.api.dto.auth.autologin.AuthGetAutologinCredentialsResponseMappersKt;
import com.vk.superapp.api.dto.auth.autologin.VkAuthAutologinCredentials;
import com.vk.superapp.api.dto.auth.exchangetokeninfo.VkAuthExchangeTokenInfo;
import com.vk.superapp.api.dto.auth.exchangetokeninfo.VkAuthExchangeTokenInfoKt;
import com.vk.superapp.api.dto.auth.serviceauthmulti.AuthGetCredentialsForServiceMultiMappersKt;
import com.vk.superapp.api.dto.auth.serviceauthmulti.AuthGetCredentialsForServiceMultiResponseModel;
import com.vk.superapp.api.dto.auth.silentauthprovider.AuthSilentAuthProvider;
import com.vk.superapp.api.dto.auth.silentauthprovider.AuthSilentAuthProviderMapperKt;
import com.vk.superapp.api.dto.auth.validateaccount.VkAuthValidateAccountResponse;
import com.vk.superapp.api.dto.auth.validateaccount.VkAuthValidateAccountResponseKt;
import com.vk.superapp.api.dto.auth.validatelogin.VkAuthValidateLoginResponse;
import com.vk.superapp.api.dto.auth.validatelogin.VkAuthValidateLoginResponseKt;
import com.vk.superapp.api.dto.auth.validatephonecheck.AuthValidatePhoneCheckMapperKt;
import com.vk.superapp.api.dto.auth.validatephonecheck.AuthValidatePhoneCheckResponse;
import com.vk.superapp.api.dto.auth.validatephoneconfirm.VkAuthConfirmResponse;
import com.vk.superapp.api.dto.auth.validatephoneconfirm.VkAuthConfirmResponseKt;
import com.vk.superapp.api.dto.auth.vkidmail.VkIdMailGetSilentTokenDtoMapper;
import com.vk.superapp.api.dto.auth.vkidmail.VkidMailCheckPasswordDtoMapper;
import com.vk.superapp.api.dto.auth.vkidmail.VkidMailCheckRestoreResponseDto;
import com.vk.superapp.api.dto.auth.vkidmail.VkidMailCheckRestoreResponseMapper;
import com.vk.superapp.api.dto.auth.vkidmail.model.MailCheckPasswordResult;
import com.vk.superapp.api.dto.auth.vkidmail.model.MailSilentTokenResultDto;
import com.vk.superapp.api.dto.auth.vkidmail.userblocked.VkIdMailUserBlockedStatusDtoMapper;
import com.vk.superapp.api.dto.auth.vkidmail.userblocked.model.VkIDMailUserBlockStatusResult;
import com.vk.superapp.api.dto.birthday.SuperAppBirthdayResponse;
import com.vk.superapp.api.dto.common.VkList;
import com.vk.superapp.api.dto.email.EmailCreationResponse;
import com.vk.superapp.api.dto.esia.EsiaCheckEsiaLinkFlow;
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
import com.vk.superapp.api.generated.account.AccountService;
import com.vk.superapp.api.generated.account.AccountServiceKt;
import com.vk.superapp.api.generated.accountVerification.AccountVerificationService;
import com.vk.superapp.api.generated.accountVerification.AccountVerificationServiceKt;
import com.vk.superapp.api.generated.ads.AdsService;
import com.vk.superapp.api.generated.ads.AdsServiceKt;
import com.vk.superapp.api.generated.appWidgets.AppWidgetsService;
import com.vk.superapp.api.generated.appWidgets.AppWidgetsServiceKt;
import com.vk.superapp.api.generated.apps.AppsService;
import com.vk.superapp.api.generated.apps.AppsServiceKt;
import com.vk.superapp.api.generated.auth.AuthService;
import com.vk.superapp.api.generated.auth.AuthServiceKt;
import com.vk.superapp.api.generated.captcha.CaptchaService;
import com.vk.superapp.api.generated.captcha.CaptchaServiceKt;
import com.vk.superapp.api.generated.database.DatabaseService;
import com.vk.superapp.api.generated.database.DatabaseServiceKt;
import com.vk.superapp.api.generated.ecosystem.EcosystemService;
import com.vk.superapp.api.generated.ecosystem.EcosystemServiceKt;
import com.vk.superapp.api.generated.email.EmailServiceKt;
import com.vk.superapp.api.generated.esia.EsiaService;
import com.vk.superapp.api.generated.esia.EsiaServiceKt;
import com.vk.superapp.api.generated.explore.ExploreServiceKt;
import com.vk.superapp.api.generated.friends.FriendsService;
import com.vk.superapp.api.generated.friends.FriendsServiceKt;
import com.vk.superapp.api.generated.goodsOrders.GoodsOrdersServiceKt;
import com.vk.superapp.api.generated.groups.GroupsService;
import com.vk.superapp.api.generated.groups.GroupsServiceKt;
import com.vk.superapp.api.generated.healthCommon.HealthCommonService;
import com.vk.superapp.api.generated.healthCommon.HealthCommonServiceKt;
import com.vk.superapp.api.generated.identity.IdentityService;
import com.vk.superapp.api.generated.identity.IdentityServiceKt;
import com.vk.superapp.api.generated.messages.MessagesService;
import com.vk.superapp.api.generated.messages.MessagesServiceKt;
import com.vk.superapp.api.generated.notifications.NotificationsService;
import com.vk.superapp.api.generated.notifications.NotificationsServiceKt;
import com.vk.superapp.api.generated.orders.OrdersService;
import com.vk.superapp.api.generated.orders.OrdersServiceKt;
import com.vk.superapp.api.generated.restore.RestoreServiceKt;
import com.vk.superapp.api.generated.settings.SettingsServiceKt;
import com.vk.superapp.api.generated.statEvents.StatEventsService;
import com.vk.superapp.api.generated.statEvents.StatEventsServiceKt;
import com.vk.superapp.api.generated.stats.StatsServiceKt;
import com.vk.superapp.api.generated.storage.StorageService;
import com.vk.superapp.api.generated.storage.StorageServiceKt;
import com.vk.superapp.api.generated.store.StoreServiceKt;
import com.vk.superapp.api.generated.superApp.SuperAppServiceKt;
import com.vk.superapp.api.generated.translations.TranslationsServiceKt;
import com.vk.superapp.api.generated.users.UsersServiceKt;
import com.vk.superapp.api.generated.utils.UtilsService;
import com.vk.superapp.api.generated.utils.UtilsServiceKt;
import com.vk.superapp.api.generated.vkRun.VkRunService;
import com.vk.superapp.api.generated.vkRun.VkRunServiceKt;
import com.vk.superapp.api.generated.vkStart.VkStartService;
import com.vk.superapp.api.generated.vkStart.VkStartServiceKt;
import com.vk.superapp.api.generated.vkidmail.VkidmailServiceKt;
import com.vk.superapp.api.generated.vkidok.VkidokService;
import com.vk.superapp.api.generated.vkidok.VkidokServiceKt;
import com.vk.superapp.api.generated.widgetsKit.WidgetsKitServiceKt;
import com.vk.superapp.api.internal.WebApiRequest;
import com.vk.superapp.api.internal.WebLinkUtilsGeneratedApi;
import com.vk.superapp.api.internal.extensions.ApiCallExtKt;
import com.vk.superapp.api.internal.extensions.ApiCommandExtKt;
import com.vk.superapp.api.internal.oauthrequests.AuthByAccessToken;
import com.vk.superapp.api.internal.oauthrequests.AuthByExchangeTokenInitiator;
import com.vk.superapp.api.internal.oauthrequests.AuthExtendProvidedTokenCommand;
import com.vk.superapp.api.internal.oauthrequests.AuthExtendSilentTokenCommand;
import com.vk.superapp.api.internal.oauthrequests.AuthExtendTokenCommand;
import com.vk.superapp.api.internal.oauthrequests.AuthGetEsiaSignature;
import com.vk.superapp.api.internal.oauthrequests.AuthGetHashes;
import com.vk.superapp.api.internal.oauthrequests.AuthGetVkConnectRemoteConfig;
import com.vk.superapp.api.internal.oauthrequests.AuthOkSilentTokenByExternalCredentials;
import com.vk.superapp.api.internal.oauthrequests.AuthOkSilentTokenByExternalToken;
import com.vk.superapp.api.internal.oauthrequests.AuthRequest;
import com.vk.superapp.api.internal.oauthrequests.CheckSilentTokenRequest;
import com.vk.superapp.api.internal.oauthrequests.PasskeyBeginCommand;
import com.vk.superapp.api.internal.oauthrequests.WebAuthApiCommand;
import com.vk.superapp.api.internal.requests.app.AddActionSuggestion;
import com.vk.superapp.api.internal.requests.app.ConfirmResult;
import com.vk.superapp.api.internal.requests.app.CreateSubscriptionResult;
import com.vk.superapp.api.internal.requests.app.OrdersCancelUserSubscriptionResult;
import com.vk.superapp.api.internal.requests.app.SubscriptionConfirmResult;
import com.vk.superapp.api.internal.requests.qr.ProcessAuthCode;
import com.vk.superapp.api.internal.requests.vkrun.VkRunStepsResponse;
import com.vk.superapp.api.states.VkAuthState;
import com.vk.superapp.api.states.VkGetOauthTokenArgs;
import com.vk.superapp.browser.internal.ui.identity.fragments.VkIdentityListFragment;
import com.vk.superapp.catalog.impl.v1.fragment.BaseSuperappMiniAppsFragment;
import com.vk.superapp.core.api.models.VkGender;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
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
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.smoothie.presentation.request.handler.AppConfigRequestHandler;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b&\b\u0016\u0018\u00002\u00020\u0001:#\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006'"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi;", "Lcom/vk/superapp/api/contract/SuperappApi;", "<init>", "()V", "VkidOk", "VkIdMail", "Account", "App", "SuperApp", "Users", "Friends", "Group", "Notification", "Permission", "Stat", "Storage", "Advertisement", "Widgets", "VkAuth", "VkRestore", "VkUtils", "Settings", "VkRun", "Identity", "Database", "Email", "Birthday", "Messages", "Esia", "AccountVerification", "VkWorkout", "VkHealth", "GoodsOrders", "Translations", "Orders", "Store", "Verification", "Captcha", "QrWebToApp", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class GeneratedSuperappApi implements SuperappApi {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000e\u0010\rJ/\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J)\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00042\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0004H\u0016¢\u0006\u0004\b\u001f\u0010\u0007J#\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110 H\u0016¢\u0006\u0004\b#\u0010$JE\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00042\u0006\u0010%\u001a\u00020\u00112\b\u0010&\u001a\u0004\u0018\u00010\u00112\b\u0010'\u001a\u0004\u0018\u00010\u00112\b\u0010(\u001a\u0004\u0018\u00010\u00112\b\u0010)\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b+\u0010,J)\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00042\b\u0010-\u001a\u0004\u0018\u00010\u00112\b\u0010/\u001a\u0004\u0018\u00010.H\u0016¢\u0006\u0004\b1\u00102J3\u00105\u001a\b\u0012\u0004\u0012\u0002040\u00042\b\u00103\u001a\u0004\u0018\u00010\u00112\b\u0010%\u001a\u0004\u0018\u00010\u00112\b\u0010-\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b5\u00106J\u001d\u00107\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00042\u0006\u0010(\u001a\u00020\u0011H\u0016¢\u0006\u0004\b7\u00108¨\u00069"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Account;", "Lcom/vk/superapp/api/contract/SuperappApi$Account;", "<init>", "()V", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/account/dto/AccountGetProfilesSwitcherInfoResponseDto;", "sendGetProfileSwitcherInfo", "()Lio/reactivex/rxjava3/core/Single;", "", "appId", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/account/AccountSignedResponse;", "sendAccountGetEmail", "(J)Lio/reactivex/rxjava3/core/Observable;", "sendAccountGetPhoneNumber", "Lcom/vk/superapp/api/states/VkGetOauthTokenArgs;", "args", "", "tokenKey", "Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "sendGetAccessToken", "(JLcom/vk/superapp/api/states/VkGetOauthTokenArgs;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", CommonConstant.KEY_ACCESS_TOKEN, "superappToken", "Lcom/vk/superapp/api/dto/account/ProfileShortInfo;", "sendGetProfileShortInfo", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "", "checkNeedServicePolicy", "(JLjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/account/ProfileNavigationInfo;", "getProfileNavigationInfo", "", "toggles", "Lcom/vk/superapp/api/dto/account/AccountAnonymousToggles;", "getTogglesAnonym", "(Ljava/util/List;)Lio/reactivex/rxjava3/core/Single;", "password", "firstName", "lastName", "birthday", "phone", "Lcom/vk/superapp/api/dto/account/AccountCheckPasswordResponse;", "checkPassword", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", BaseCheckFragment.KEY_SAT_TOKEN, "Lcom/vk/superapp/api/dto/auth/InitPasswordCheckAccessFactor;", "accessFactor", "Lcom/vk/superapp/api/dto/auth/InitPasswordCheckResponse;", "initPasswordCheck", "(Ljava/lang/String;Lcom/vk/superapp/api/dto/auth/InitPasswordCheckAccessFactor;)Lio/reactivex/rxjava3/core/Single;", "smsCode", "Lcom/vk/superapp/api/dto/auth/CheckAccessResponse;", "checkAccess", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "validateBirthday", "(Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Account\n+ 2 KotlinCommonExt.kt\ncom/vk/core/extensions/KotlinCommonExtKt\n*L\n1#1,3566:1\n9#2,2:3567\n9#2,2:3569\n9#2,2:3571\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Account\n*L\n592#1:3567,2\n593#1:3569,2\n601#1:3571,2\n*E\n"})
    public static final class Account implements SuperappApi.Account {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.x
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Account.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<AccountCheckPasswordResponseDto, AccountCheckPasswordResponse> {
            ipakvmoca(AccountMapper accountMapper) {
                super(1, accountMapper, AccountMapper.class, "mapToAccountCheckPasswordResponse", "mapToAccountCheckPasswordResponse(Lcom/vk/api/generated/account/dto/AccountCheckPasswordResponseDto;)Lcom/vk/superapp/api/dto/account/AccountCheckPasswordResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AccountCheckPasswordResponse invoke(AccountCheckPasswordResponseDto accountCheckPasswordResponseDto) {
                AccountCheckPasswordResponseDto p10 = accountCheckPasswordResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AccountMapper) this.receiver).mapToAccountCheckPasswordResponse(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<AccountGetProfileNavigationInfoResponseDto, ProfileNavigationInfo> {
            ipakvmocb(AccountMapper accountMapper) {
                super(1, accountMapper, AccountMapper.class, "mapToProfileNavigationInfo", "mapToProfileNavigationInfo(Lcom/vk/api/generated/account/dto/AccountGetProfileNavigationInfoResponseDto;)Lcom/vk/superapp/api/dto/account/ProfileNavigationInfo;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ProfileNavigationInfo invoke(AccountGetProfileNavigationInfoResponseDto accountGetProfileNavigationInfoResponseDto) {
                AccountGetProfileNavigationInfoResponseDto p10 = accountGetProfileNavigationInfoResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AccountMapper) this.receiver).mapToProfileNavigationInfo(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocc extends FunctionReferenceImpl implements Function1<AccountGetTogglesResponseDto, AccountAnonymousToggles> {
            ipakvmocc(AccountMapper accountMapper) {
                super(1, accountMapper, AccountMapper.class, "mapToAccountAnonymousToggles", "mapToAccountAnonymousToggles(Lcom/vk/api/generated/account/dto/AccountGetTogglesResponseDto;)Lcom/vk/superapp/api/dto/account/AccountAnonymousToggles;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AccountAnonymousToggles invoke(AccountGetTogglesResponseDto accountGetTogglesResponseDto) {
                AccountGetTogglesResponseDto p10 = accountGetTogglesResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AccountMapper) this.receiver).mapToAccountAnonymousToggles(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocd extends FunctionReferenceImpl implements Function1<AuthInitPasswordCheckResponseDto, InitPasswordCheckResponse> {
            ipakvmocd(AccountMapper accountMapper) {
                super(1, accountMapper, AccountMapper.class, "mapToInitPasswordCheckResponse", "mapToInitPasswordCheckResponse(Lcom/vk/api/generated/auth/dto/AuthInitPasswordCheckResponseDto;)Lcom/vk/superapp/api/dto/auth/InitPasswordCheckResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final InitPasswordCheckResponse invoke(AuthInitPasswordCheckResponseDto authInitPasswordCheckResponseDto) {
                AuthInitPasswordCheckResponseDto p10 = authInitPasswordCheckResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AccountMapper) this.receiver).mapToInitPasswordCheckResponse(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountMapper ipakvmoca() {
            return new AccountMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SingleSource ipakvmocc(Function1 function1, Object obj) {
            return (SingleSource) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountCheckPasswordResponse ipakvmocd(Function1 function1, Object obj) {
            return (AccountCheckPasswordResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ProfileNavigationInfo ipakvmoce(Function1 function1, Object obj) {
            return (ProfileNavigationInfo) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountAnonymousToggles ipakvmocf(Function1 function1, Object obj) {
            return (AccountAnonymousToggles) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final InitPasswordCheckResponse ipakvmocg(Function1 function1, Object obj) {
            return (InitPasswordCheckResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountSignedResponse ipakvmoch(Function1 function1, Object obj) {
            return (AccountSignedResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountSignedResponse ipakvmoci(Function1 function1, Object obj) {
            return (AccountSignedResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ProfileShortInfo ipakvmocj(Function1 function1, Object obj) {
            return (ProfileShortInfo) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmock(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<CheckAccessResponse> checkAccess(@Nullable String smsCode, @Nullable String password, @Nullable String satToken) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authCheckAccess$default(AuthServiceKt.AuthService(), null, satToken, password, smsCode, 1, null)).allowNoAuth().setAnonymous(true), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.y
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((AuthCheckAccessResponseDto) obj);
                }
            };
            Single<CheckAccessResponse> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.z
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<Boolean> checkNeedServicePolicy(long appId, @Nullable String superappToken) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AccountServiceKt.AccountService().accountNeedServicePolicy((int) appId, superappToken));
            if (superappToken != null) {
                Intrinsics.checkNotNull(superappToken);
                webApiRequest.setSuperappToken(superappToken);
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.d0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.e0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<AccountCheckPasswordResponse> checkPassword(@NotNull final String password, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String birthday, @Nullable final String phone) {
            Intrinsics.checkNotNullParameter(password, "password");
            Single singleFromCallable = Single.fromCallable(new Callable() { // from class: com.vk.superapp.api.contract.r
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return GeneratedSuperappApi.Account.ipakvmoca(password, lastName, firstName, birthday, phone);
                }
            });
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.s
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((WebApiRequest) obj);
                }
            };
            Single singleFlatMap = singleFromCallable.flatMap(new Function() { // from class: com.vk.superapp.api.contract.t
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocc(function1, obj);
                }
            });
            final ipakvmoca ipakvmocaVar = new ipakvmoca((AccountMapper) this.ipakvmoca.getValue());
            Single<AccountCheckPasswordResponse> map = singleFlatMap.map(new Function() { // from class: com.vk.superapp.api.contract.u
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocd(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<ProfileNavigationInfo> getProfileNavigationInfo() {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountServiceKt.AccountService().accountGetProfileNavigationInfo()), null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb((AccountMapper) this.ipakvmoca.getValue());
            Single<ProfileNavigationInfo> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.f0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoce(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<AccountAnonymousToggles> getTogglesAnonym(@NotNull List<String> toggles) {
            Intrinsics.checkNotNullParameter(toggles, "toggles");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountService.DefaultImpls.accountGetToggles$default(AccountServiceKt.AccountService(), toggles, null, null, null, 14, null)).forceRemoveAccessToken(true).allowNoAuth().setAnonymous(true), null, 1, null);
            final ipakvmocc ipakvmoccVar = new ipakvmocc((AccountMapper) this.ipakvmoca.getValue());
            Single<AccountAnonymousToggles> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.m
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocf(ipakvmoccVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<InitPasswordCheckResponse> initPasswordCheck(@Nullable String satToken, @Nullable InitPasswordCheckAccessFactor accessFactor) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authInitPasswordCheck$default(AuthServiceKt.AuthService(), null, satToken, accessFactor != null ? accessFactor.getValue() : null, 1, null)).allowNoAuth().setAnonymous(true), null, 1, null);
            final ipakvmocd ipakvmocdVar = new ipakvmocd((AccountMapper) this.ipakvmoca.getValue());
            Single<InitPasswordCheckResponse> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.c0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocg(ipakvmocdVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Observable<AccountSignedResponse> sendAccountGetEmail(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AccountService.DefaultImpls.accountGetEmail$default(AccountServiceKt.AccountService(), null, null, Integer.valueOf((int) appId), 3, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((AccountGetEmailResponseDto) obj);
                }
            };
            Observable<AccountSignedResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.b0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoch(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Observable<AccountSignedResponse> sendAccountGetPhoneNumber(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AccountService.DefaultImpls.accountGetPhone$default(AccountServiceKt.AccountService(), null, null, Integer.valueOf((int) appId), 3, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.p
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((AccountGetPhoneResponseDto) obj);
                }
            };
            Observable<AccountSignedResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.q
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoci(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Observable<WebAuthAnswer> sendGetAccessToken(long appId, @NotNull VkGetOauthTokenArgs args, @Nullable String tokenKey) {
            Intrinsics.checkNotNullParameter(args, "args");
            if (tokenKey == null) {
                tokenKey = "access_token";
            }
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new WebAuthApiCommand("https://" + superappApiCore.debugConfig().getDebugOAuthHost().invoke() + "/authorize", args, tokenKey), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<ProfileShortInfo> sendGetProfileShortInfo(@Nullable String accessToken, @Nullable String superappToken) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AccountService.DefaultImpls.accountGet$default(AccountServiceKt.AccountService(), null, CollectionsKt.listOf((Object[]) new String[]{"phone", "email"}), "nom", null, 8, null));
            if (accessToken != null) {
                webApiRequest.overrideAuth(accessToken, null);
            }
            if (superappToken != null) {
                Intrinsics.checkNotNull(superappToken);
                webApiRequest.setSuperappToken(superappToken);
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.n
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca(this.f51848a, (List) obj);
                }
            };
            Single<ProfileShortInfo> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.o
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmocj(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<AccountGetProfilesSwitcherInfoResponseDto> sendGetProfileSwitcherInfo() {
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountServiceKt.AccountService().accountGetProfilesSwitcherInfo(CollectionsKt.emptyList())).setAnonymous(true).forceAnonymous(true).setIsMultipleTokens(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Account
        @NotNull
        public Single<Boolean> validateBirthday(@NotNull String birthday) {
            Intrinsics.checkNotNullParameter(birthday, "birthday");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(AccountServiceKt.AccountService().accountValidateBirthday(birthday))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.v
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.w
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Account.ipakvmock(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ProfileShortInfo ipakvmoca(Account account, List list) {
            AccountMapper accountMapper = (AccountMapper) account.ipakvmoca.getValue();
            Intrinsics.checkNotNull(list);
            return accountMapper.mapToProfileShortInfo$api_release((AccountGetUserObjectDto) CollectionsKt.first(list));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountSignedResponse ipakvmoca(AccountGetEmailResponseDto accountGetEmailResponseDto) {
            String sign = accountGetEmailResponseDto.getSign();
            if (sign == null) {
                sign = "";
            }
            return new AccountSignedResponse(sign, accountGetEmailResponseDto.getEmail());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AccountSignedResponse ipakvmoca(AccountGetPhoneResponseDto accountGetPhoneResponseDto) {
            String sign = accountGetPhoneResponseDto.getSign();
            if (sign == null) {
                sign = "";
            }
            return new AccountSignedResponse(sign, accountGetPhoneResponseDto.getPhoneNumber());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebApiRequest ipakvmoca(String str, String str2, String str3, String str4, String str5) {
            return ApiCallExtKt.toWebApiRequest(AccountService.DefaultImpls.accountCheckPassword$default(AccountServiceKt.AccountService(), str, str2, str3, str4, str5, null, 32, null)).allowNoAuth().setAnonymous(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SingleSource ipakvmoca(WebApiRequest webApiRequest) {
            return WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CheckAccessResponse ipakvmoca(AuthCheckAccessResponseDto authCheckAccessResponseDto) {
            return new CheckAccessResponse(authCheckAccessResponseDto.getToken());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CheckAccessResponse ipakvmoca(Function1 function1, Object obj) {
            return (CheckAccessResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016J&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u0013"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$AccountVerification;", "Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "<init>", "()V", "getSessionInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/accountVerification/dto/AccountVerificationGetSessionInfoResponseDto;", "code", "", "codeVerifier", "provider", "providerClientId", "platform", "Lcom/vk/api/generated/accountVerification/dto/AccountVerificationGetSessionInfoPlatformDto;", "createLink", "", PasskeyBeginResult.SID_KEY, "cuaToken", "linkWithVerify", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$AccountVerification\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1#2:3567\n*E\n"})
    public static final class AccountVerification implements SuperappApi.AccountVerification {
        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.AccountVerification
        @NotNull
        public Single<Boolean> createLink(@NotNull String sid, @NotNull String cuaToken, @NotNull String provider) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(cuaToken, "cuaToken");
            Intrinsics.checkNotNullParameter(provider, "provider");
            AccountVerificationService AccountVerificationService = AccountVerificationServiceKt.AccountVerificationService();
            for (AccountVerificationCreateLinkProviderDto accountVerificationCreateLinkProviderDto : AccountVerificationCreateLinkProviderDto.values()) {
                if (Intrinsics.areEqual(accountVerificationCreateLinkProviderDto.getValue(), provider)) {
                    Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.DefaultImpls.accountVerificationCreateLink$default(AccountVerificationService, sid, cuaToken, accountVerificationCreateLinkProviderDto, AccountVerificationCreateLinkPlatformDto.MOBILE, null, 16, null)), null, 1, null);
                    final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.i0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return GeneratedSuperappApi.AccountVerification.ipakvmoca((BaseOkResponseDto) obj);
                        }
                    };
                    Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.j0
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.AccountVerification.ipakvmoca(function1, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
            }
            accountVerificationCreateLinkProviderDto = null;
            Single uiSingle$default2 = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.DefaultImpls.accountVerificationCreateLink$default(AccountVerificationService, sid, cuaToken, accountVerificationCreateLinkProviderDto, AccountVerificationCreateLinkPlatformDto.MOBILE, null, 16, null)), null, 1, null);
            final Function1 function2 = new Function1() { // from class: com.vk.superapp.api.contract.i0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.AccountVerification.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map2 = uiSingle$default2.map(new Function() { // from class: com.vk.superapp.api.contract.j0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.AccountVerification.ipakvmoca(function2, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            return map2;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.AccountVerification
        @NotNull
        public Single<AccountVerificationGetSessionInfoResponseDto> getSessionInfo(@NotNull String code, @Nullable String codeVerifier, @NotNull String provider, @NotNull String providerClientId, @NotNull AccountVerificationGetSessionInfoPlatformDto platform) {
            AccountVerificationGetSessionInfoProviderDto accountVerificationGetSessionInfoProviderDto;
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(providerClientId, "providerClientId");
            Intrinsics.checkNotNullParameter(platform, "platform");
            AccountVerificationService AccountVerificationService = AccountVerificationServiceKt.AccountVerificationService();
            AccountVerificationGetSessionInfoProviderDto[] accountVerificationGetSessionInfoProviderDtoArrValues = AccountVerificationGetSessionInfoProviderDto.values();
            int length = accountVerificationGetSessionInfoProviderDtoArrValues.length;
            for (int i10 = 0; i10 < length; i10++) {
                accountVerificationGetSessionInfoProviderDto = accountVerificationGetSessionInfoProviderDtoArrValues[i10];
                if (Intrinsics.areEqual(accountVerificationGetSessionInfoProviderDto.getValue(), provider)) {
                    return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.accountVerificationGetSessionInfo(code, codeVerifier, accountVerificationGetSessionInfoProviderDto, providerClientId, platform)), null, 1, null);
                }
            }
            accountVerificationGetSessionInfoProviderDto = null;
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.accountVerificationGetSessionInfo(code, codeVerifier, accountVerificationGetSessionInfoProviderDto, providerClientId, platform)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.AccountVerification
        @NotNull
        public Single<Boolean> linkWithVerify(@NotNull String sid, @NotNull String cuaToken, @NotNull String provider) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(cuaToken, "cuaToken");
            Intrinsics.checkNotNullParameter(provider, "provider");
            AccountVerificationService AccountVerificationService = AccountVerificationServiceKt.AccountVerificationService();
            for (AccountVerificationLinkWithVerifyProviderDto accountVerificationLinkWithVerifyProviderDto : AccountVerificationLinkWithVerifyProviderDto.values()) {
                if (Intrinsics.areEqual(accountVerificationLinkWithVerifyProviderDto.getValue(), provider)) {
                    Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.DefaultImpls.accountVerificationLinkWithVerify$default(AccountVerificationService, sid, cuaToken, accountVerificationLinkWithVerifyProviderDto, AccountVerificationLinkWithVerifyPlatformDto.MOBILE, null, null, 48, null)), null, 1, null);
                    final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.g0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return GeneratedSuperappApi.AccountVerification.ipakvmocb((BaseOkResponseDto) obj);
                        }
                    };
                    Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.h0
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.AccountVerification.ipakvmocb(function1, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
            }
            accountVerificationLinkWithVerifyProviderDto = null;
            Single uiSingle$default2 = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AccountVerificationService.DefaultImpls.accountVerificationLinkWithVerify$default(AccountVerificationService, sid, cuaToken, accountVerificationLinkWithVerifyProviderDto, AccountVerificationLinkWithVerifyPlatformDto.MOBILE, null, null, 48, null)), null, 1, null);
            final Function1 function2 = new Function1() { // from class: com.vk.superapp.api.contract.g0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.AccountVerification.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map2 = uiSingle$default2.map(new Function() { // from class: com.vk.superapp.api.contract.h0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.AccountVerification.ipakvmocb(function2, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            return map2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Advertisement;", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "<init>", "()V", "sendRetargetingHitRequest", "Lio/reactivex/rxjava3/core/Observable;", "", "params", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$RetargetingHitParams;", "sendConversionHitRequest", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement$ConversionHitParams;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Advertisement implements SuperappApi.Advertisement {
        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(AdsRetargetingHitDto adsRetargetingHitDto) {
            return Boolean.valueOf(Intrinsics.areEqual(adsRetargetingHitDto.getSuccess(), Boolean.TRUE));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Advertisement
        @NotNull
        public Observable<Boolean> sendConversionHitRequest(@NotNull SuperappApi.Advertisement.ConversionHitParams params) {
            Intrinsics.checkNotNullParameter(params, "params");
            AdsService AdsService = AdsServiceKt.AdsService();
            String code = params.getBaseParams().getCode();
            String conversionEvent = params.getConversionEvent();
            if (conversionEvent == null) {
                conversionEvent = "";
            }
            String str = conversionEvent;
            Float conversionValue = params.getConversionValue();
            String httpRef = params.getBaseParams().getHttpRef();
            Long appId = params.getBaseParams().getAppId();
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AdsService.adsConversionHit(code, str, conversionValue, httpRef, appId != null ? Integer.valueOf((int) appId.longValue()) : null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Advertisement.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.n0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Advertisement.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Advertisement
        @NotNull
        public Observable<Boolean> sendRetargetingHitRequest(@NotNull SuperappApi.Advertisement.RetargetingHitParams params) {
            Intrinsics.checkNotNullParameter(params, "params");
            AdsService AdsService = AdsServiceKt.AdsService();
            String code = params.getBaseParams().getCode();
            String event = params.getEvent();
            Long targetGroupId = params.getTargetGroupId();
            Integer numValueOf = targetGroupId != null ? Integer.valueOf((int) targetGroupId.longValue()) : null;
            Long priceListId = params.getPriceListId();
            Integer numValueOf2 = priceListId != null ? Integer.valueOf((int) priceListId.longValue()) : null;
            String productsEvent = params.getProductsEvent();
            String productsParams = params.getProductsParams();
            String httpRef = params.getBaseParams().getHttpRef();
            Long appId = params.getBaseParams().getAppId();
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AdsService.adsRetargetingHit(code, event, numValueOf, numValueOf2, productsEvent, productsParams, httpRef, appId != null ? Integer.valueOf((int) appId.longValue()) : null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.k0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Advertisement.ipakvmoca((AdsRetargetingHitDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Advertisement.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J_\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\b\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b \u0010!J\u001b\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u001b0\tH\u0016¢\u0006\u0004\b#\u0010$J%\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001f0\t2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0016¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b'\u0010(JK\u0010-\u001a\b\u0012\u0004\u0012\u00020,0\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010\f2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\b\u0010*\u001a\u0004\u0018\u00010\f2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b-\u0010.JU\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010\f2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\b\u0010*\u001a\u0004\u0018\u00010\f2\b\u0010+\u001a\u0004\u0018\u00010\f2\b\u0010/\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0004\b0\u00101JG\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u001b0\t2\b\u00102\u001a\u0004\u0018\u00010\f2\u0006\u00103\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b4\u00105J\u001d\u00107\u001a\b\u0012\u0004\u0012\u0002060\t2\u0006\u0010\u0005\u001a\u00020\u0017H\u0016¢\u0006\u0004\b7\u00108J1\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0:0\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u00109\u001a\u00020\fH\u0016¢\u0006\u0004\b;\u0010<J7\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070:0\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\f0\u001bH\u0016¢\u0006\u0004\b>\u0010?J\u001d\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b@\u0010(J%\u0010D\u001a\b\u0012\u0004\u0012\u00020C0\t2\u0006\u0010B\u001a\u00020A2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\bD\u0010EJ/\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010B\u001a\u00020A2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010F\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\bG\u0010HJ\u001d\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\bI\u0010(J\u001d\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\bJ\u0010(J\u001d\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\bK\u0010(J5\u0010O\u001a\b\u0012\u0004\u0012\u00020N0\t2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\f2\u0006\u0010M\u001a\u00020\fH\u0016¢\u0006\u0004\bO\u0010PJ]\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0\u001b0\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010S\u001a\u0004\u0018\u00010Q2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0016¢\u0006\u0004\bU\u0010VJE\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020T0\u001b0\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\f2\u0006\u0010R\u001a\u00020Q2\u0006\u0010S\u001a\u00020Q2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bH\u0016¢\u0006\u0004\bW\u0010XJ\u001b\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Y0\u001b0\tH\u0016¢\u0006\u0004\bZ\u0010$J-\u0010[\u001a\b\u0012\u0004\u0012\u00020T0\t2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0017H\u0016¢\u0006\u0004\b[\u0010\\J\u001f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\u00102\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b]\u0010^JI\u0010d\u001a\b\u0012\u0004\u0012\u00020c0\t2\u0006\u0010_\u001a\u00020\f2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\f0`2\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u00172\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\u00040`H\u0016¢\u0006\u0004\bd\u0010eJ;\u0010f\u001a\b\u0012\u0004\u0012\u00020c0\t2\u0006\u0010_\u001a\u00020\f2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\f0`2\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u0017H\u0016¢\u0006\u0004\bf\u0010gJ'\u0010j\u001a\b\u0012\u0004\u0012\u00020i0\t2\u0006\u0010h\u001a\u00020\f2\b\u0010)\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\bj\u0010kJ\u001d\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\bl\u0010(J5\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010m\u001a\u00020A2\u0006\u0010n\u001a\u00020\f2\u0006\u0010F\u001a\u00020\fH\u0016¢\u0006\u0004\bo\u0010pJ5\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010q\u001a\b\u0012\u0004\u0012\u00020A0\u001b2\b\u0010F\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\br\u0010sJ\u0015\u0010u\u001a\b\u0012\u0004\u0012\u00020t0\tH\u0016¢\u0006\u0004\bu\u0010$J%\u0010x\u001a\b\u0012\u0004\u0012\u00020w0\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u0017H\u0016¢\u0006\u0004\bx\u0010yJ9\u0010}\u001a\b\u0012\u0004\u0012\u00020|0\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010M\u001a\u0004\u0018\u00010\f2\b\u0010z\u001a\u0004\u0018\u00010\f2\u0006\u0010{\u001a\u00020\u0017H\u0016¢\u0006\u0004\b}\u0010~J=\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0080\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u007f\u001a\u00020\f2\b\u0010{\u001a\u0004\u0018\u00010\u00172\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J=\u0010\u0084\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u007f\u001a\u00020\f2\b\u0010{\u001a\u0004\u0018\u00010\u00172\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b\u0084\u0001\u0010\u0082\u0001J3\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u00172\b\u0010{\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J<\u0010\u0089\u0001\u001a\t\u0012\u0005\u0012\u00030\u0088\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010{\u001a\u00020\u00172\u0007\u0010\u0087\u0001\u001a\u00020\f2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J(\u0010\u008c\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u0017H\u0016¢\u0006\u0005\b\u008c\u0001\u0010yJ(\u0010\u008e\u0001\u001a\t\u0012\u0005\u0012\u00030\u008d\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u0017H\u0016¢\u0006\u0005\b\u008e\u0001\u0010yJF\u0010\u0092\u0001\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010{\u001a\u00020\u00172\u0007\u0010\u0087\u0001\u001a\u00020\f2\b\u0010\u0090\u0001\u001a\u00030\u008f\u00012\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J)\u0010\u0095\u0001\u001a\t\u0012\u0005\u0012\u00030\u0094\u00010\u000e2\u0006\u0010{\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J9\u0010\u009a\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0099\u00010\u001b0\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0007\u0010\u0097\u0001\u001a\u00020\u00172\u0007\u0010\u0098\u0001\u001a\u00020\u0017H\u0016¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001JF\u0010\u009e\u0001\u001a\t\u0012\u0005\u0012\u00030\u009d\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\f2\u0007\u0010\u009c\u0001\u001a\u00020A2\b\u0010)\u001a\u0004\u0018\u00010\f2\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001JA\u0010£\u0001\u001a\t\u0012\u0005\u0012\u00030¢\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010h\u001a\u0004\u0018\u00010\f2\b\u0010+\u001a\u0004\u0018\u00010\f2\n\u0010¡\u0001\u001a\u0005\u0018\u00010 \u0001H\u0016¢\u0006\u0006\b£\u0001\u0010¤\u0001J3\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010¦\u0001\u001a\u00030¥\u00012\u0007\u0010§\u0001\u001a\u00020\fH\u0016¢\u0006\u0006\b¨\u0001\u0010©\u0001J?\u0010«\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ª\u00010\u001b0\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u00103\u001a\u00020\u00172\u0006\u0010_\u001a\u00020\fH\u0016¢\u0006\u0006\b«\u0001\u0010¬\u0001J,\u0010¯\u0001\u001a\t\u0012\u0005\u0012\u00030®\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\t\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b¯\u0001\u0010°\u0001J*\u0010±\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010+\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0006\b±\u0001\u0010°\u0001J?\u0010µ\u0001\u001a\t\u0012\u0005\u0012\u00030´\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0007\u0010²\u0001\u001a\u00020\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\t\u0010³\u0001\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0006\bµ\u0001\u0010¶\u0001J*\u0010¹\u0001\u001a\t\u0012\u0005\u0012\u00030¸\u00010\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0007\u0010·\u0001\u001a\u00020\u0007H\u0016¢\u0006\u0006\b¹\u0001\u0010º\u0001J(\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0007\u0010»\u0001\u001a\u00020\fH\u0016¢\u0006\u0005\b¼\u0001\u0010<J*\u0010¾\u0001\u001a\t\u0012\u0005\u0012\u00030¸\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0007\u0010½\u0001\u001a\u00020\u0007H\u0016¢\u0006\u0006\b¾\u0001\u0010¿\u0001J\u001f\u0010À\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0005\bÀ\u0001\u0010\u0014J \u0010Â\u0001\u001a\t\u0012\u0005\u0012\u00030Á\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0005\bÂ\u0001\u0010\u0014J&\u0010Ä\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ã\u00010\u001b0\t2\u0006\u0010\u0005\u001a\u00020\u0017H\u0016¢\u0006\u0005\bÄ\u0001\u00108J \u0010Æ\u0001\u001a\t\u0012\u0005\u0012\u00030Å\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0005\bÆ\u0001\u0010\u0014¨\u0006Ç\u0001"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$App;", "Lcom/vk/superapp/api/contract/SuperappApi$App;", "<init>", "()V", "", "appId", "groupId", "", "shouldSendPush", "Lio/reactivex/rxjava3/core/Observable;", "sendAppsAddToGroup", "(JJZ)Lio/reactivex/rxjava3/core/Observable;", "", "activeFeatures", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/external/miniapp/net/ad/AdvertisementConfig;", "sendAppsGetAdvertisementConfig", "(Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/app/AppAdvertisementConfig;", "sendAppsGetAppAdvertisementConfig", "(J)Lio/reactivex/rxjava3/core/Single;", "Landroid/location/Location;", "location", "", "limit", "offset", "lastSeenSectionId", "", "Lcom/vk/external/miniapp/net/app/AppFields;", "appFields", BaseSuperappMiniAppsFragment.KEY_SECTION_ID, "Lcom/vk/superapp/api/dto/app/catalog/AppsCatalogSectionsResponse;", "sendAppsGetMiniAppsCatalog", "(Landroid/location/Location;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/catalog/section/AppsCategory;", "sendGetMiniAppCategories", "()Lio/reactivex/rxjava3/core/Observable;", "sendAppsGetMiniAppsCatalogSearch", "(Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsAddToMenu", "(J)Lio/reactivex/rxjava3/core/Observable;", BlockParser.REF_TYPE, "specialUrl", "trackCode", "Lcom/vk/external/miniapp/net/app/WebApiApplication;", "sendAppsGet", "(JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "needSettings", "sendJoinAndGet", "(JLjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Single;", "platform", "count", "sendAppsGetRecommendations", "(Ljava/lang/String;IIILjava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/ActionMenuApps;", "sendAppsGetActionMenuApps", "(I)Lio/reactivex/rxjava3/core/Observable;", "name", "", "sendAppsGetScopes", "(JLjava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", SharedKt.PARAM_SCOPES, "sendAppsGetCheckAllowedScopes", "(JLjava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsCheckAllowPosting", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/superapp/api/dto/user/CheckInviteUserData;", "sendAppsCheckInviteFriend", "(Lcom/vk/dto/common/id/UserId;J)Lio/reactivex/rxjava3/core/Observable;", "requestKey", "sendAppsInviteFriend", "(Lcom/vk/dto/common/id/UserId;JLjava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsRemove", "sendAppsRemoveFromMenu", "sendAppUninstall", "code", "type", "Lorg/json/JSONObject;", "sendAppWidgetGetPreview", "(JJLjava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "", "lat", "lon", "Lcom/vk/superapp/api/dto/app/AppsSection;", "sendGetVkApps", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/Double;Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "sendGetVkAppsUnauthorized", "(Ljava/lang/String;DDLjava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/WebAppActivities;", "sendGetAppsCatalogActivities", "sendGetGamesSection", "(Ljava/lang/String;II)Lio/reactivex/rxjava3/core/Observable;", "sendAppsClearRecents", "(Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "query", "", "filters", "tagIds", "Lcom/vk/superapp/api/dto/app/AppsSearchResponse;", "sendAppsSearch", "(Ljava/lang/String;Ljava/util/Collection;IILjava/util/Collection;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsSearchUnauthorized", "(Ljava/lang/String;Ljava/util/Collection;II)Lio/reactivex/rxjava3/core/Observable;", "url", "Lcom/vk/superapp/api/dto/app/ResolvingResult;", "sendAppResolveByUrl", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "sendAppsConfirmPolicy", "userTo", "message", "sendAppRequest", "(JLcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "userIds", "sendAppInviteRequest", "(JLjava/util/List;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/apps/dto/AppsGetTrackBridgeCallHandlersResponseDto;", "getTrackBridgeCallHandlers", "subscriptionId", "Lcom/vk/api/generated/orders/dto/OrdersSubscriptionDto;", "getAppSubscription", "(JI)Lio/reactivex/rxjava3/core/Observable;", DivActionSpec.Scroll.PARAM_ITEM_INDEX, "orderId", "Lcom/vk/superapp/api/dto/app/WebOrder;", "sendAppOrder", "(JLjava/lang/String;Ljava/lang/String;I)Lio/reactivex/rxjava3/core/Observable;", "itemId", "Lcom/vk/superapp/api/dto/app/WebOrderInfo;", "sendAppCreateOrder", "(JLjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/internal/requests/app/CreateSubscriptionResult;", "sendAppCreateSubscription", "sendAppResumeSubscription", "(JILjava/lang/Integer;)Lio/reactivex/rxjava3/core/Observable;", "confirmHash", "Lcom/vk/superapp/api/internal/requests/app/SubscriptionConfirmResult;", "sendAppConfirmGameSubscription", "(JILjava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/GameSubscription;", "sendAppGetUserSubscription", "Lcom/vk/superapp/api/internal/requests/app/OrdersCancelUserSubscriptionResult;", "sendAppCancelUserSubscription", "Lcom/vk/superapp/api/dto/app/AutoBuyStatus;", "autoBuyStatus", "Lcom/vk/superapp/api/internal/requests/app/ConfirmResult;", "sendAppConfirmOrder", "(JILjava/lang/String;Lcom/vk/superapp/api/dto/app/AutoBuyStatus;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/orders/dto/OrdersOrderDto$StatusDto;", "sendAppOrdersGetById", "(IJ)Lio/reactivex/rxjava3/core/Single;", "global", "userResult", "Lcom/vk/superapp/api/dto/app/WebGameLeaderboard;", "sendGetGameLeaderboardByApp", "(JII)Lio/reactivex/rxjava3/core/Observable;", "ownerId", "Lcom/vk/superapp/api/dto/app/WebAppEmbeddedUrl;", "sendGetEmbeddedUrl", "(JLjava/lang/String;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/apps/dto/AppsNeedToShowActionPlaceIdDto;", "placeId", "Lcom/vk/superapp/api/internal/requests/app/AddActionSuggestion;", "needToShowAction", "(JLjava/lang/String;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsNeedToShowActionPlaceIdDto;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/AppLifecycleEvent;", "event", "actionType", "sendActionShown", "(JLcom/vk/superapp/api/dto/app/AppLifecycleEvent;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "sendGetFriendsList", "(JIILjava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "requestId", "Lcom/vk/superapp/api/dto/app/AppsSecretHash;", "sendGetSecretHash", "(JLjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "sendSetGameIsInstalled", "referrer", "vkProfileId", "Lcom/vk/superapp/api/dto/app/AppLaunchParams;", "sendGetLaunchParams", "(JLjava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)Lio/reactivex/rxjava3/core/Observable;", "isAllowed", "Lcom/vk/api/generated/base/dto/BaseBoolIntDto;", "sendAppsChangeAppBadgeStatus", "(JZ)Lio/reactivex/rxjava3/core/Observable;", "attachments", "sendAppUploadAttachedLinkWallPost", "isRecommended", "markAppAsRecommended", "(JZ)Lio/reactivex/rxjava3/core/Single;", "setUnverifiedScreenShown", "Lcom/vk/api/generated/apps/dto/AppsStartCallResponseDto;", "startAppCall", "Lcom/vk/superapp/api/dto/app/AppsGroupsContainer;", "getGroupsList", "Lcom/vk/superapp/api/dto/personal/Banner;", "getActionMenuBanner", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$App\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1617#2,9:3567\n1869#2:3576\n1870#2:3579\n1626#2:3580\n1617#2,9:3581\n1869#2:3590\n1870#2:3592\n1626#2:3593\n1617#2,9:3594\n1869#2:3603\n1870#2:3605\n1626#2:3606\n295#2,2:3607\n1563#2:3609\n1634#2,3:3610\n1#3:3577\n1#3:3578\n1#3:3591\n1#3:3604\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$App\n*L\n861#1:3567,9\n861#1:3576\n861#1:3579\n861#1:3580\n1030#1:3581,9\n1030#1:3590\n1030#1:3592\n1030#1:3593\n1049#1:3594,9\n1049#1:3603\n1049#1:3605\n1049#1:3606\n1257#1:3607,2\n1453#1:3609\n1453#1:3610,3\n861#1:3578\n1030#1:3591\n1049#1:3604\n*E\n"})
    public static final class App implements SuperappApi.App {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.a1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.App.ipakvmocb();
            }
        });

        @NotNull
        private final Lazy ipakvmocb = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.b1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.App.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[AutoBuyStatus.values().length];
                try {
                    iArr[AutoBuyStatus.CHECKED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[AutoBuyStatus.UNCHECKED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[AutoBuyStatus.DISABLED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[AutoBuyStatus.NULL.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[AppLifecycleEvent.values().length];
                try {
                    iArr2[AppLifecycleEvent.ON_START.ordinal()] = 1;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr2[AppLifecycleEvent.ON_CLOSE.ordinal()] = 2;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<AppsNeedToShowActionResponseDto, AddActionSuggestion> {
            ipakvmoca(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAddActionSuggestion", "mapToAddActionSuggestion(Lcom/vk/api/generated/apps/dto/AppsNeedToShowActionResponseDto;)Lcom/vk/superapp/api/internal/requests/app/AddActionSuggestion;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AddActionSuggestion invoke(AppsNeedToShowActionResponseDto appsNeedToShowActionResponseDto) {
                AppsNeedToShowActionResponseDto p10 = appsNeedToShowActionResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAddActionSuggestion(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocaa extends FunctionReferenceImpl implements Function1<AppsGetSecretHashResponseDto, AppsSecretHash> {
            ipakvmocaa(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsSecretHash", "mapToAppsSecretHash(Lcom/vk/api/generated/apps/dto/AppsGetSecretHashResponseDto;)Lcom/vk/superapp/api/dto/app/AppsSecretHash;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppsSecretHash invoke(AppsGetSecretHashResponseDto appsGetSecretHashResponseDto) {
                AppsGetSecretHashResponseDto p10 = appsGetSecretHashResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsSecretHash(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocab extends FunctionReferenceImpl implements Function1<List<? extends AppsVkAppsSectionDto>, List<? extends AppsSection>> {
            ipakvmocab(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsSectionList", "mapToAppsSectionList(Ljava/util/List;)Ljava/util/List;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final List<? extends AppsSection> invoke(List<? extends AppsVkAppsSectionDto> list) {
                List<? extends AppsVkAppsSectionDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsSectionList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocac extends FunctionReferenceImpl implements Function1<List<? extends AppsVkAppsSectionDto>, List<? extends AppsSection>> {
            ipakvmocac(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsSectionList", "mapToAppsSectionList(Ljava/util/List;)Ljava/util/List;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final List<? extends AppsSection> invoke(List<? extends AppsVkAppsSectionDto> list) {
                List<? extends AppsVkAppsSectionDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsSectionList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocad extends FunctionReferenceImpl implements Function1<AppsJoinAndGetResponseDto, WebApiApplication> {
            ipakvmocad(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebApiApplication", "mapToWebApiApplication(Lcom/vk/api/generated/apps/dto/AppsJoinAndGetResponseDto;)Lcom/vk/external/miniapp/net/app/WebApiApplication;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebApiApplication invoke(AppsJoinAndGetResponseDto appsJoinAndGetResponseDto) {
                AppsJoinAndGetResponseDto p10 = appsJoinAndGetResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebApiApplication(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<BaseBoolIntDto, OrdersCancelUserSubscriptionResult> {
            ipakvmocb(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToCancelResult", "mapToCancelResult(Lcom/vk/api/generated/base/dto/BaseBoolIntDto;)Lcom/vk/superapp/api/internal/requests/app/OrdersCancelUserSubscriptionResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final OrdersCancelUserSubscriptionResult invoke(BaseBoolIntDto baseBoolIntDto) {
                BaseBoolIntDto p10 = baseBoolIntDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToCancelResult(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocc extends FunctionReferenceImpl implements Function1<OrdersConfirmSubscriptionResponseDto, SubscriptionConfirmResult> {
            ipakvmocc(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToSubscriptionConfirmResult", "mapToSubscriptionConfirmResult(Lcom/vk/api/generated/orders/dto/OrdersConfirmSubscriptionResponseDto;)Lcom/vk/superapp/api/internal/requests/app/SubscriptionConfirmResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final SubscriptionConfirmResult invoke(OrdersConfirmSubscriptionResponseDto ordersConfirmSubscriptionResponseDto) {
                OrdersConfirmSubscriptionResponseDto p10 = ordersConfirmSubscriptionResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToSubscriptionConfirmResult(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocd extends FunctionReferenceImpl implements Function1<BaseOkResponseDto, ConfirmResult> {
            ipakvmocd(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToConfirmResult", "mapToConfirmResult(Lcom/vk/api/generated/base/dto/BaseOkResponseDto;)Lcom/vk/superapp/api/internal/requests/app/ConfirmResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ConfirmResult invoke(BaseOkResponseDto baseOkResponseDto) {
                BaseOkResponseDto p10 = baseOkResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToConfirmResult(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoce extends FunctionReferenceImpl implements Function1<OrdersAppOrderItemDto, WebOrderInfo> {
            ipakvmoce(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebOrderInfo", "mapToWebOrderInfo(Lcom/vk/api/generated/orders/dto/OrdersAppOrderItemDto;)Lcom/vk/superapp/api/dto/app/WebOrderInfo;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebOrderInfo invoke(OrdersAppOrderItemDto ordersAppOrderItemDto) {
                OrdersAppOrderItemDto p10 = ordersAppOrderItemDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebOrderInfo(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocf extends FunctionReferenceImpl implements Function1<OrdersAppSubscriptionItemDto, CreateSubscriptionResult> {
            ipakvmocf(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToCreateSubscriptionResult", "mapToCreateSubscriptionResult(Lcom/vk/api/generated/orders/dto/OrdersAppSubscriptionItemDto;)Lcom/vk/superapp/api/internal/requests/app/CreateSubscriptionResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CreateSubscriptionResult invoke(OrdersAppSubscriptionItemDto ordersAppSubscriptionItemDto) {
                OrdersAppSubscriptionItemDto p10 = ordersAppSubscriptionItemDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToCreateSubscriptionResult(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocg extends FunctionReferenceImpl implements Function1<OrdersSubscriptionDto, GameSubscription> {
            ipakvmocg(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToGameSubscription", "mapToGameSubscription(Lcom/vk/api/generated/orders/dto/OrdersSubscriptionDto;)Lcom/vk/superapp/api/dto/app/GameSubscription;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final GameSubscription invoke(OrdersSubscriptionDto ordersSubscriptionDto) {
                OrdersSubscriptionDto p10 = ordersSubscriptionDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToGameSubscription(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoch extends FunctionReferenceImpl implements Function1<OrdersBuyItemResponseDto, WebOrder> {
            ipakvmoch(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebOrder", "mapToWebOrder(Lcom/vk/api/generated/orders/dto/OrdersBuyItemResponseDto;)Lcom/vk/superapp/api/dto/app/WebOrder;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebOrder invoke(OrdersBuyItemResponseDto ordersBuyItemResponseDto) {
                OrdersBuyItemResponseDto p10 = ordersBuyItemResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebOrder(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoci extends FunctionReferenceImpl implements Function1<OrdersAppSubscriptionItemDto, CreateSubscriptionResult> {
            ipakvmoci(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToCreateSubscriptionResult", "mapToCreateSubscriptionResult(Lcom/vk/api/generated/orders/dto/OrdersAppSubscriptionItemDto;)Lcom/vk/superapp/api/internal/requests/app/CreateSubscriptionResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CreateSubscriptionResult invoke(OrdersAppSubscriptionItemDto ordersAppSubscriptionItemDto) {
                OrdersAppSubscriptionItemDto p10 = ordersAppSubscriptionItemDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToCreateSubscriptionResult(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocj extends FunctionReferenceImpl implements Function1<AppsGetResponseDto, WebApiApplication> {
            ipakvmocj(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebApiApplication", "mapToWebApiApplication(Lcom/vk/api/generated/apps/dto/AppsGetResponseDto;)Lcom/vk/external/miniapp/net/app/WebApiApplication;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebApiApplication invoke(AppsGetResponseDto appsGetResponseDto) {
                AppsGetResponseDto p10 = appsGetResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebApiApplication(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmock extends FunctionReferenceImpl implements Function1<AppsGetActionMenuAppsResponseDto, ActionMenuApps> {
            ipakvmock(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToActionMenuApps", "mapToActionMenuApps(Lcom/vk/api/generated/apps/dto/AppsGetActionMenuAppsResponseDto;)Lcom/vk/superapp/api/dto/app/ActionMenuApps;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ActionMenuApps invoke(AppsGetActionMenuAppsResponseDto appsGetActionMenuAppsResponseDto) {
                AppsGetActionMenuAppsResponseDto p10 = appsGetActionMenuAppsResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToActionMenuApps(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocl extends FunctionReferenceImpl implements Function1<AppsAdsSlotsDto, AdvertisementConfig> {
            ipakvmocl(AdvertisementMapper advertisementMapper) {
                super(1, advertisementMapper, AdvertisementMapper.class, "mapToAdvertisementConfig", "mapToAdvertisementConfig(Lcom/vk/api/generated/apps/dto/AppsAdsSlotsDto;)Lcom/vk/external/miniapp/net/ad/AdvertisementConfig;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AdvertisementConfig invoke(AppsAdsSlotsDto appsAdsSlotsDto) {
                AppsAdsSlotsDto p10 = appsAdsSlotsDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AdvertisementMapper) this.receiver).mapToAdvertisementConfig(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocm extends FunctionReferenceImpl implements Function1<AppsAdsSlotsWebConfigItemDto, AppAdvertisementConfig> {
            ipakvmocm(AdvertisementMapper advertisementMapper) {
                super(1, advertisementMapper, AdvertisementMapper.class, "mapToAppAdvertisementConfig", "mapToAppAdvertisementConfig(Lcom/vk/api/generated/apps/dto/AppsAdsSlotsWebConfigItemDto;)Lcom/vk/superapp/api/dto/app/AppAdvertisementConfig;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppAdvertisementConfig invoke(AppsAdsSlotsWebConfigItemDto appsAdsSlotsWebConfigItemDto) {
                AppsAdsSlotsWebConfigItemDto p10 = appsAdsSlotsWebConfigItemDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AdvertisementMapper) this.receiver).mapToAppAdvertisementConfig(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocn extends FunctionReferenceImpl implements Function1<List<? extends AppsMemberAllowedScopeItemDto>, Map<String, ? extends Boolean>> {
            ipakvmocn(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAllowedScopesMap", "mapToAllowedScopesMap(Ljava/util/List;)Ljava/util/Map;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Map<String, ? extends Boolean> invoke(List<? extends AppsMemberAllowedScopeItemDto> list) {
                List<? extends AppsMemberAllowedScopeItemDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAllowedScopesMap(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoco extends FunctionReferenceImpl implements Function1<AppsMiniappsCatalogDto, AppsCatalogSectionsResponse> {
            ipakvmoco(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsCatalogSectionsResponse", "mapToAppsCatalogSectionsResponse(Lcom/vk/api/generated/apps/dto/AppsMiniappsCatalogDto;)Lcom/vk/superapp/api/dto/app/catalog/AppsCatalogSectionsResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppsCatalogSectionsResponse invoke(AppsMiniappsCatalogDto appsMiniappsCatalogDto) {
                AppsMiniappsCatalogDto p10 = appsMiniappsCatalogDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsCatalogSectionsResponse(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocp extends FunctionReferenceImpl implements Function1<AppsMiniappsCatalogDto, AppsCatalogSectionsResponse> {
            ipakvmocp(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsCatalogSectionsResponse", "mapToAppsCatalogSectionsResponse(Lcom/vk/api/generated/apps/dto/AppsMiniappsCatalogDto;)Lcom/vk/superapp/api/dto/app/catalog/AppsCatalogSectionsResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppsCatalogSectionsResponse invoke(AppsMiniappsCatalogDto appsMiniappsCatalogDto) {
                AppsMiniappsCatalogDto p10 = appsMiniappsCatalogDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsCatalogSectionsResponse(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocq extends FunctionReferenceImpl implements Function1<AppsGetRecommendationsResponseDto, List<? extends WebApiApplication>> {
            ipakvmocq(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebApiApplicationList", "mapToWebApiApplicationList(Lcom/vk/api/generated/apps/dto/AppsGetRecommendationsResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebApiApplication> invoke(AppsGetRecommendationsResponseDto appsGetRecommendationsResponseDto) {
                AppsGetRecommendationsResponseDto p10 = appsGetRecommendationsResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebApiApplicationList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocr extends FunctionReferenceImpl implements Function1<AppsGetScopesResponseDto, Map<String, ? extends String>> {
            ipakvmocr(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToScopesMap", "mapToScopesMap(Lcom/vk/api/generated/apps/dto/AppsGetScopesResponseDto;)Ljava/util/Map;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Map<String, ? extends String> invoke(AppsGetScopesResponseDto appsGetScopesResponseDto) {
                AppsGetScopesResponseDto p10 = appsGetScopesResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToScopesMap(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocs extends FunctionReferenceImpl implements Function1<AppsSearchResponseDto, AppsSearchResponse> {
            ipakvmocs(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsSearchResponse", "mapToAppsSearchResponse(Lcom/vk/api/generated/apps/dto/AppsSearchResponseDto;)Lcom/vk/superapp/api/dto/app/AppsSearchResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppsSearchResponse invoke(AppsSearchResponseDto appsSearchResponseDto) {
                AppsSearchResponseDto p10 = appsSearchResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsSearchResponse(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoct extends FunctionReferenceImpl implements Function1<AppsSearchResponseDto, AppsSearchResponse> {
            ipakvmoct(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsSearchResponse", "mapToAppsSearchResponse(Lcom/vk/api/generated/apps/dto/AppsSearchResponseDto;)Lcom/vk/superapp/api/dto/app/AppsSearchResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppsSearchResponse invoke(AppsSearchResponseDto appsSearchResponseDto) {
                AppsSearchResponseDto p10 = appsSearchResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsSearchResponse(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocu extends FunctionReferenceImpl implements Function1<List<? extends AppsCatalogActivityItemDto>, List<? extends WebAppActivities>> {
            ipakvmocu(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebAppActivitiesList", "mapToWebAppActivitiesList(Ljava/util/List;)Ljava/util/List;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebAppActivities> invoke(List<? extends AppsCatalogActivityItemDto> list) {
                List<? extends AppsCatalogActivityItemDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebAppActivitiesList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocv extends FunctionReferenceImpl implements Function1<AppsGetEmbeddedUrlResponseDto, WebAppEmbeddedUrl> {
            ipakvmocv(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebAppEmbeddedUrl", "mapToWebAppEmbeddedUrl(Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto;)Lcom/vk/superapp/api/dto/app/WebAppEmbeddedUrl;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebAppEmbeddedUrl invoke(AppsGetEmbeddedUrlResponseDto appsGetEmbeddedUrlResponseDto) {
                AppsGetEmbeddedUrlResponseDto p10 = appsGetEmbeddedUrlResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebAppEmbeddedUrl(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocw extends FunctionReferenceImpl implements Function1<AppsGetFriendsListExtendedResponseDto, List<? extends WebUserShortInfo>> {
            ipakvmocw(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebUserShortInfoList", "mapToWebUserShortInfoList(Lcom/vk/api/generated/apps/dto/AppsGetFriendsListExtendedResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebUserShortInfo> invoke(AppsGetFriendsListExtendedResponseDto appsGetFriendsListExtendedResponseDto) {
                AppsGetFriendsListExtendedResponseDto p10 = appsGetFriendsListExtendedResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebUserShortInfoList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocx extends FunctionReferenceImpl implements Function1<AppsGetLeaderboardByAppResponseDto, List<? extends WebGameLeaderboard>> {
            ipakvmocx(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToWebGameLeaderboardList", "mapToWebGameLeaderboardList(Lcom/vk/api/generated/apps/dto/AppsGetLeaderboardByAppResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebGameLeaderboard> invoke(AppsGetLeaderboardByAppResponseDto appsGetLeaderboardByAppResponseDto) {
                AppsGetLeaderboardByAppResponseDto p10 = appsGetLeaderboardByAppResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToWebGameLeaderboardList(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocy extends FunctionReferenceImpl implements Function1<JSONObject, AppLaunchParams> {
            ipakvmocy(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppLaunchParams", "mapToAppLaunchParams(Lorg/json/JSONObject;)Lcom/vk/superapp/api/dto/app/AppLaunchParams;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AppLaunchParams invoke(JSONObject jSONObject) {
                JSONObject p10 = jSONObject;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppLaunchParams(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocz extends FunctionReferenceImpl implements Function1<AppsMiniappsCatalogItemPayloadListDto, List<? extends AppsCategory>> {
            ipakvmocz(AppMapper appMapper) {
                super(1, appMapper, AppMapper.class, "mapToAppsCategoryList", "mapToAppsCategoryList(Lcom/vk/api/generated/apps/dto/AppsMiniappsCatalogItemPayloadListDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends AppsCategory> invoke(AppsMiniappsCatalogItemPayloadListDto appsMiniappsCatalogItemPayloadListDto) {
                AppsMiniappsCatalogItemPayloadListDto p10 = appsMiniappsCatalogItemPayloadListDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((AppMapper) this.receiver).mapToAppsCategoryList(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsSection ipakvmoca(App app, String str, AppsCatalogListDto appsCatalogListDto) {
            AppMapper appMapper = (AppMapper) app.ipakvmoca.getValue();
            Intrinsics.checkNotNull(appsCatalogListDto);
            return appMapper.mapToAppsSection(appsCatalogListDto, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppAdvertisementConfig ipakvmocaa(Function1 function1, Object obj) {
            return (AppAdvertisementConfig) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Map ipakvmocab(Function1 function1, Object obj) {
            return (Map) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsCatalogSectionsResponse ipakvmocac(Function1 function1, Object obj) {
            return (AppsCatalogSectionsResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsCatalogSectionsResponse ipakvmocad(Function1 function1, Object obj) {
            return (AppsCatalogSectionsResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocae(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Map ipakvmocaf(Function1 function1, Object obj) {
            return (Map) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocag(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocah(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocai(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsSearchResponse ipakvmocaj(Function1 function1, Object obj) {
            return (AppsSearchResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsSearchResponse ipakvmocak(Function1 function1, Object obj) {
            return (AppsSearchResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocal(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebAppEmbeddedUrl ipakvmocam(Function1 function1, Object obj) {
            return (WebAppEmbeddedUrl) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocan(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocao(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsSection ipakvmocap(Function1 function1, Object obj) {
            return (AppsSection) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONObject ipakvmocaq(Function1 function1, Object obj) {
            return (JSONObject) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppLaunchParams ipakvmocar(Function1 function1, Object obj) {
            return (AppLaunchParams) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocas(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppsSecretHash ipakvmocat(Function1 function1, Object obj) {
            return (AppsSecretHash) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocau(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocav(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebApiApplication ipakvmocaw(Function1 function1, Object obj) {
            return (WebApiApplication) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocax(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocay(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppMapper ipakvmocb() {
            return new AppMapper(new CommonMapper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoce(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocf(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocg(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoch(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CreateSubscriptionResult ipakvmoci(Function1 function1, Object obj) {
            return (CreateSubscriptionResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final GameSubscription ipakvmocj(Function1 function1, Object obj) {
            return (GameSubscription) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmock(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebOrder ipakvmocl(Function1 function1, Object obj) {
            return (WebOrder) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final OrdersOrderDto.StatusDto ipakvmocm(Function1 function1, Object obj) {
            return (OrdersOrderDto.StatusDto) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocn(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CreateSubscriptionResult ipakvmoco(Function1 function1, Object obj) {
            return (CreateSubscriptionResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocp(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocq(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocr(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocs(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoct(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CheckInviteUserData ipakvmocu(Function1 function1, Object obj) {
            return (CheckInviteUserData) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocv(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocw(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebApiApplication ipakvmocx(Function1 function1, Object obj) {
            return (WebApiApplication) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ActionMenuApps ipakvmocy(Function1 function1, Object obj) {
            return (ActionMenuApps) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AdvertisementConfig ipakvmocz(Function1 function1, Object obj) {
            return (AdvertisementConfig) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<Banner> getActionMenuBanner(long appId) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetActionMenuBanner((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((AppsActionBannerDto) obj);
                }
            };
            Single<Banner> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.x2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<OrdersSubscriptionDto> getAppSubscription(long appId, int subscriptionId) {
            return ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersGetUserSubscription((int) appId, subscriptionId)), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<AppsGroupsContainer>> getGroupsList(int appId) {
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetGroupsList(appId)), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((AppsGetGroupsListResponseDto) obj);
                }
            };
            Observable<List<AppsGroupsContainer>> map = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.n1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsGetTrackBridgeCallHandlersResponseDto> getTrackBridgeCallHandlers() {
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetTrackBridgeCallHandlers()), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<BaseBoolIntDto> markAppAsRecommended(long appId, boolean isRecommended) {
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsRecommend((int) appId, isRecommended)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AddActionSuggestion> needToShowAction(long appId, @Nullable String url, @Nullable String trackCode, @Nullable AppsNeedToShowActionPlaceIdDto placeId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsNeedToShowAction((int) appId, url, trackCode, placeId)), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((AppMapper) this.ipakvmoca.getValue());
            Observable<AddActionSuggestion> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.w0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocc(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendActionShown(long appId, @NotNull AppLifecycleEvent event, @NotNull String actionType) {
            AppsSetActionShownShowTypeDto appsSetActionShownShowTypeDto;
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(actionType, "actionType");
            AppsSetActionShownActionTypeDto appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.ADD_TO_MAIN_SCREEN;
            if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.RECOMMEND;
                if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                    appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.RECOMMENDATION_NOTIFICATION;
                    if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                        appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.NOTIFICATIONS_AUTO_PERMISSION;
                        if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                            appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.ADD_TO_COMMUNITY;
                            if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                                appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.PERSONAL_DISCOUNT;
                                if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                                    appsSetActionShownActionTypeDto = AppsSetActionShownActionTypeDto.PERSONAL_DISCOUNT_CASHBACK;
                                    if (!Intrinsics.areEqual(actionType, appsSetActionShownActionTypeDto.getValue())) {
                                        appsSetActionShownActionTypeDto = null;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (appsSetActionShownActionTypeDto == null) {
                Observable<Boolean> observableJust = Observable.just(Boolean.FALSE);
                Intrinsics.checkNotNullExpressionValue(observableJust, "just(...)");
                return observableJust;
            }
            AppsService AppsService = AppsServiceKt.AppsService();
            int i10 = (int) appId;
            int i11 = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
            if (i11 == 1) {
                appsSetActionShownShowTypeDto = AppsSetActionShownShowTypeDto.ON_START;
            } else {
                if (i11 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                appsSetActionShownShowTypeDto = AppsSetActionShownShowTypeDto.ON_CLOSE;
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.appsSetActionShown(i10, appsSetActionShownActionTypeDto, appsSetActionShownShowTypeDto)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.r1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.s1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocd(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<OrdersCancelUserSubscriptionResult> sendAppCancelUserSubscription(long appId, int subscriptionId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersCancelUserSubscription((int) appId, subscriptionId)), null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb((AppMapper) this.ipakvmoca.getValue());
            Observable<OrdersCancelUserSubscriptionResult> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.h3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoce(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<SubscriptionConfirmResult> sendAppConfirmGameSubscription(long appId, int orderId, @NotNull String confirmHash, @Nullable String trackCode) {
            Intrinsics.checkNotNullParameter(confirmHash, "confirmHash");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersConfirmSubscription((int) appId, orderId, confirmHash, trackCode)), null, 1, null);
            final ipakvmocc ipakvmoccVar = new ipakvmocc((AppMapper) this.ipakvmoca.getValue());
            Observable<SubscriptionConfirmResult> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.e1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocf(ipakvmoccVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<ConfirmResult> sendAppConfirmOrder(long appId, int orderId, @NotNull String confirmHash, @NotNull AutoBuyStatus autoBuyStatus, @Nullable String trackCode) {
            OrdersConfirmOrderAutoBuyCheckedDto ordersConfirmOrderAutoBuyCheckedDto;
            OrdersConfirmOrderAutoBuyCheckedDto ordersConfirmOrderAutoBuyCheckedDto2;
            Intrinsics.checkNotNullParameter(confirmHash, "confirmHash");
            Intrinsics.checkNotNullParameter(autoBuyStatus, "autoBuyStatus");
            int i10 = WhenMappings.$EnumSwitchMapping$0[autoBuyStatus.ordinal()];
            if (i10 == 1) {
                ordersConfirmOrderAutoBuyCheckedDto = OrdersConfirmOrderAutoBuyCheckedDto.CHECKED;
            } else {
                if (i10 != 2) {
                    if (i10 == 3) {
                        ordersConfirmOrderAutoBuyCheckedDto = OrdersConfirmOrderAutoBuyCheckedDto.DISABLED;
                    } else {
                        if (i10 != 4) {
                            throw new NoWhenBranchMatchedException();
                        }
                        ordersConfirmOrderAutoBuyCheckedDto2 = null;
                    }
                    Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersService.DefaultImpls.ordersConfirmOrder$default(OrdersServiceKt.OrdersService(), (int) appId, orderId, confirmHash, trackCode, ordersConfirmOrderAutoBuyCheckedDto2, null, 32, null)), null, 1, null);
                    final ipakvmocd ipakvmocdVar = new ipakvmocd((AppMapper) this.ipakvmoca.getValue());
                    Observable<ConfirmResult> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.y1
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.App.ipakvmocg(ipakvmocdVar, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
                ordersConfirmOrderAutoBuyCheckedDto = OrdersConfirmOrderAutoBuyCheckedDto.UNCHECKED;
            }
            ordersConfirmOrderAutoBuyCheckedDto2 = ordersConfirmOrderAutoBuyCheckedDto;
            Observable uiObservable$default2 = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersService.DefaultImpls.ordersConfirmOrder$default(OrdersServiceKt.OrdersService(), (int) appId, orderId, confirmHash, trackCode, ordersConfirmOrderAutoBuyCheckedDto2, null, 32, null)), null, 1, null);
            final Function1 ipakvmocdVar2 = new ipakvmocd((AppMapper) this.ipakvmoca.getValue());
            Observable<ConfirmResult> map2 = uiObservable$default2.map(new Function() { // from class: com.vk.superapp.api.contract.y1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocg(ipakvmocdVar2, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            return map2;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<WebOrderInfo> sendAppCreateOrder(long appId, @NotNull String itemId, @Nullable Integer orderId, @Nullable String trackCode) {
            Intrinsics.checkNotNullParameter(itemId, "itemId");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersCreateOrder((int) appId, itemId, orderId, trackCode)), null, 1, null);
            final ipakvmoce ipakvmoceVar = new ipakvmoce((AppMapper) this.ipakvmoca.getValue());
            Observable<WebOrderInfo> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.y2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoch(ipakvmoceVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<CreateSubscriptionResult> sendAppCreateSubscription(long appId, @NotNull String itemId, @Nullable Integer orderId, @Nullable String trackCode) {
            Intrinsics.checkNotNullParameter(itemId, "itemId");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersCreateSubscription((int) appId, itemId, orderId, trackCode)), null, 1, null);
            final ipakvmocf ipakvmocfVar = new ipakvmocf((AppMapper) this.ipakvmoca.getValue());
            Observable<CreateSubscriptionResult> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.b2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoci(ipakvmocfVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<GameSubscription> sendAppGetUserSubscription(long appId, int subscriptionId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersGetUserSubscription((int) appId, subscriptionId)), null, 1, null);
            final ipakvmocg ipakvmocgVar = new ipakvmocg((AppMapper) this.ipakvmoca.getValue());
            Observable<GameSubscription> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.e3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocj(ipakvmocgVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppInviteRequest(long appId, @NotNull final List<UserId> userIds, @Nullable String requestKey) {
            Intrinsics.checkNotNullParameter(userIds, "userIds");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsInviteMultipleFriend(userIds, Integer.valueOf((int) appId), requestKey)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.j1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca(userIds, (AppsInviteMultipleFriendResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmock(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<WebOrder> sendAppOrder(long appId, @Nullable String type, @Nullable String item, int orderId) {
            OrdersService OrdersService = OrdersServiceKt.OrdersService();
            int i10 = (int) appId;
            if (type == null) {
                type = "";
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersService.DefaultImpls.ordersBuyItem$default(OrdersService, i10, type, null, item, Integer.valueOf(orderId), 4, null)), null, 1, null);
            final ipakvmoch ipakvmochVar = new ipakvmoch((AppMapper) this.ipakvmoca.getValue());
            Observable<WebOrder> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.q1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocl(ipakvmochVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<OrdersOrderDto.StatusDto> sendAppOrdersGetById(final int orderId, long appId) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(OrdersService.DefaultImpls.ordersGetById$default(OrdersServiceKt.OrdersService(), Integer.valueOf(orderId), null, null, Integer.valueOf((int) appId), 6, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.x0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca(orderId, (List) obj);
                }
            };
            Single<OrdersOrderDto.StatusDto> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.y0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocm(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppRequest(long appId, @NotNull UserId userTo, @NotNull String message, @NotNull String requestKey) {
            Intrinsics.checkNotNullParameter(userTo, "userTo");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(requestKey, "requestKey");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsSendRequest$default(AppsServiceKt.AppsService(), userTo, Integer.valueOf((int) appId), message, null, null, null, requestKey, null, 184, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((Integer) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.b3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocn(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<ResolvingResult> sendAppResolveByUrl(@NotNull String url, @Nullable String ref) {
            Intrinsics.checkNotNullParameter(url, "url");
            return WebLinkUtilsGeneratedApi.INSTANCE.resolveAppByUrl(url, ref);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<CreateSubscriptionResult> sendAppResumeSubscription(long appId, int subscriptionId, @Nullable Integer orderId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersResumeSubscription((int) appId, subscriptionId, orderId)), null, 1, null);
            final ipakvmoci ipakvmociVar = new ipakvmoci((AppMapper) this.ipakvmoca.getValue());
            Observable<CreateSubscriptionResult> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.d3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoco(ipakvmociVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppUninstall(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsUninstall((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.s0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.t0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocp(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppUploadAttachedLinkWallPost(long appId, @NotNull String attachments) {
            Intrinsics.checkNotNullParameter(attachments, "attachments");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsUploadAttachedLinkWallPost((int) appId, attachments)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocc((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.x1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocq(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<JSONObject> sendAppWidgetGetPreview(long groupId, long appId, @NotNull String code, @NotNull String type) {
            AppWidgetsGetWidgetPreviewTypeDto appWidgetsGetWidgetPreviewTypeDto;
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(type, "type");
            AppWidgetsService AppWidgetsService = AppWidgetsServiceKt.AppWidgetsService();
            UserId userId = new UserId(groupId);
            int i10 = (int) appId;
            AppWidgetsGetWidgetPreviewTypeDto[] appWidgetsGetWidgetPreviewTypeDtoArrValues = AppWidgetsGetWidgetPreviewTypeDto.values();
            int length = appWidgetsGetWidgetPreviewTypeDtoArrValues.length;
            int i11 = 0;
            while (true) {
                if (i11 >= length) {
                    appWidgetsGetWidgetPreviewTypeDto = null;
                    break;
                }
                appWidgetsGetWidgetPreviewTypeDto = appWidgetsGetWidgetPreviewTypeDtoArrValues[i11];
                if (Intrinsics.areEqual(appWidgetsGetWidgetPreviewTypeDto.getValue(), type)) {
                    break;
                }
                i11++;
            }
            if (appWidgetsGetWidgetPreviewTypeDto == null) {
                appWidgetsGetWidgetPreviewTypeDto = AppWidgetsGetWidgetPreviewTypeDto.COMPACT_LIST;
            }
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(AppWidgetsService.appWidgetsGetWidgetPreview(userId, i10, code, appWidgetsGetWidgetPreviewTypeDto)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsAddToGroup(long appId, long groupId, boolean shouldSendPush) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsAddToGroup((int) appId, new UserId(groupId), Boolean.valueOf(shouldSendPush))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.v0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocr(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsAddToMenu(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsAddToMenu((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.f1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocd((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.g1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocs(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<BaseBoolIntDto> sendAppsChangeAppBadgeStatus(long appId, boolean isAllowed) {
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsChangeAppBadgeStatus((int) appId, isAllowed)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsCheckAllowPosting(long appId) {
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsCheckAllowPosting((int) appId)), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.c1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocb((BaseBoolIntDto) obj);
                }
            };
            Observable<Boolean> map = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.d1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoct(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<CheckInviteUserData> sendAppsCheckInviteFriend(@NotNull UserId userId, long appId) {
            Intrinsics.checkNotNullParameter(userId, "userId");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsCheckInviteFriend(userId, (int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.i3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((AppsCheckInviteFriendResponseDto) obj);
                }
            };
            Observable<CheckInviteUserData> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.j3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocu(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsClearRecents(@Nullable String platform) {
            AppsClearRecentsPlatformDto appsClearRecentsPlatformDto = AppsClearRecentsPlatformDto.HTML5;
            if (!Intrinsics.areEqual(platform, appsClearRecentsPlatformDto.getValue())) {
                appsClearRecentsPlatformDto = AppsClearRecentsPlatformDto.VK_APPS;
                if (!Intrinsics.areEqual(platform, appsClearRecentsPlatformDto.getValue())) {
                    appsClearRecentsPlatformDto = null;
                }
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsClearRecents(appsClearRecentsPlatformDto)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.o0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoce((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.z0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocv(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsConfirmPolicy(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsConfirmPolicy((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.t2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocf((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.u2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocw(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<WebApiApplication> sendAppsGet(long appId, @Nullable String ref, @Nullable List<? extends AppFields> appFields, @Nullable String specialUrl, @Nullable String trackCode) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGet$default(AppsServiceKt.AppsService(), Integer.valueOf((int) appId), null, specialUrl, null, null, null, null, null, null, ref, null, null, trackCode, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields), null, null, 52730, null)), null, 1, null);
            final ipakvmocj ipakvmocjVar = new ipakvmocj((AppMapper) this.ipakvmoca.getValue());
            Observable<WebApiApplication> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.v2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocx(ipakvmocjVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<ActionMenuApps> sendAppsGetActionMenuApps(int appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetActionMenuApps(appId)), null, 1, null);
            final ipakvmock ipakvmockVar = new ipakvmock((AppMapper) this.ipakvmoca.getValue());
            Observable<ActionMenuApps> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.t1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocy(ipakvmockVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<AdvertisementConfig> sendAppsGetAdvertisementConfig(@Nullable String activeFeatures) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetAdvertisementConfig(activeFeatures)), null, 1, null);
            final ipakvmocl ipakvmoclVar = new ipakvmocl((AdvertisementMapper) this.ipakvmocb.getValue());
            Single<AdvertisementConfig> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.o1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocz(ipakvmoclVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<AppAdvertisementConfig> sendAppsGetAppAdvertisementConfig(long appId) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetAppAdvertisementConfig$default(AppsServiceKt.AppsService(), (int) appId, null, null, 6, null)), null, 1, null);
            final ipakvmocm ipakvmocmVar = new ipakvmocm((AdvertisementMapper) this.ipakvmocb.getValue());
            Single<AppAdvertisementConfig> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.u1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocaa(ipakvmocmVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Map<String, Boolean>> sendAppsGetCheckAllowedScopes(long appId, @NotNull List<String> scopes) {
            Intrinsics.checkNotNullParameter(scopes, "scopes");
            AppsService AppsService = AppsServiceKt.AppsService();
            int i10 = (int) appId;
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = scopes.iterator();
            while (true) {
                AppsCheckAllowedScopesScopesDto appsCheckAllowedScopesScopesDto = null;
                if (!it.hasNext()) {
                    Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.appsCheckAllowedScopes(i10, arrayList)), null, 1, null);
                    final ipakvmocn ipakvmocnVar = new ipakvmocn((AppMapper) this.ipakvmoca.getValue());
                    Observable<Map<String, Boolean>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.z2
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.App.ipakvmocab(ipakvmocnVar, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
                String str = (String) it.next();
                for (AppsCheckAllowedScopesScopesDto appsCheckAllowedScopesScopesDto2 : AppsCheckAllowedScopesScopesDto.values()) {
                    if (Intrinsics.areEqual(appsCheckAllowedScopesScopesDto2.getValue(), str)) {
                        appsCheckAllowedScopesScopesDto = appsCheckAllowedScopesScopesDto2;
                        break;
                    }
                }
                if (appsCheckAllowedScopesScopesDto != null) {
                    arrayList.add(appsCheckAllowedScopesScopesDto);
                }
            }
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsCatalogSectionsResponse> sendAppsGetMiniAppsCatalog(@Nullable Location location, @Nullable String activeFeatures, int limit, @Nullable Integer offset, @Nullable Integer lastSeenSectionId, @Nullable List<? extends AppFields> appFields, @Nullable String sectionId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetMiniAppsCatalog$default(AppsServiceKt.AppsService(), Integer.valueOf(limit), null, offset, lastSeenSectionId, null, location != null ? Float.valueOf((float) location.getLatitude()) : null, location != null ? Float.valueOf((float) location.getLongitude()) : null, activeFeatures != null ? StringsKt.split$default((CharSequence) activeFeatures, new char[]{AbstractJsonLexerKt.COMMA}, false, 0, 6, (Object) null) : null, null, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields), sectionId, null, 2322, null)), null, 1, null);
            final ipakvmoco ipakvmocoVar = new ipakvmoco((AppMapper) this.ipakvmoca.getValue());
            Observable<AppsCatalogSectionsResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.p0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocac(ipakvmocoVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsCatalogSectionsResponse> sendAppsGetMiniAppsCatalogSearch(@Nullable List<? extends AppFields> appFields) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetMiniAppsCatalogSearch$default(AppsServiceKt.AppsService(), null, null, null, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields), 7, null)), null, 1, null);
            final ipakvmocp ipakvmocpVar = new ipakvmocp((AppMapper) this.ipakvmoca.getValue());
            Observable<AppsCatalogSectionsResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.z1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocad(ipakvmocpVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<WebApiApplication>> sendAppsGetRecommendations(@Nullable String platform, int count, int offset, int appId, @Nullable String ref) {
            AppsGetRecommendationsPlatformDto appsGetRecommendationsPlatformDto = (!Intrinsics.areEqual(platform, VkIdentityListFragment.SOURCE_VK_APPS) && Intrinsics.areEqual(platform, "html5")) ? AppsGetRecommendationsPlatformDto.HTML5 : AppsGetRecommendationsPlatformDto.VK_APPS;
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetRecommendations$default(AppsServiceKt.AppsService(), appsGetRecommendationsPlatformDto, Integer.valueOf(count), Integer.valueOf(offset), null, Integer.valueOf(appId), ref, 8, null)), null, 1, null);
            final ipakvmocq ipakvmocqVar = new ipakvmocq((AppMapper) this.ipakvmoca.getValue());
            Observable<List<WebApiApplication>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.c2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocae(ipakvmocqVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Map<String, String>> sendAppsGetScopes(long appId, @NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            AppsGetScopesTypeDto appsGetScopesTypeDto = AppsGetScopesTypeDto.USER;
            if (!Intrinsics.areEqual(name, appsGetScopesTypeDto.getValue())) {
                appsGetScopesTypeDto = AppsGetScopesTypeDto.GROUP;
                if (!Intrinsics.areEqual(name, appsGetScopesTypeDto.getValue())) {
                    throw new IllegalArgumentException("Unknown scope type (" + name + ") in apps.getScopes");
                }
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetScopes(appsGetScopesTypeDto, Integer.valueOf((int) appId))), null, 1, null);
            final ipakvmocr ipakvmocrVar = new ipakvmocr((AppMapper) this.ipakvmoca.getValue());
            Observable<Map<String, String>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.d2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocaf(ipakvmocrVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsInviteFriend(@NotNull UserId userId, long appId, @Nullable String requestKey) {
            Intrinsics.checkNotNullParameter(userId, "userId");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsInviteFriend$default(AppsServiceKt.AppsService(), userId, null, Integer.valueOf((int) appId), requestKey, 2, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.l2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocc((BaseBoolIntDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.m2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocag(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsRemove(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsRemove((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.i2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocg((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.j2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocah(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<Boolean> sendAppsRemoveFromMenu(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsRemoveFromMenu((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.q0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoch((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.r0
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocai(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsSearchResponse> sendAppsSearch(@NotNull String query, @NotNull Collection<String> filters, int offset, int count, @NotNull Collection<Long> tagIds) {
            Intrinsics.checkNotNullParameter(query, "query");
            Intrinsics.checkNotNullParameter(filters, "filters");
            Intrinsics.checkNotNullParameter(tagIds, "tagIds");
            AppsService AppsService = AppsServiceKt.AppsService();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = filters.iterator();
            while (true) {
                AppsSearchFiltersDto appsSearchFiltersDto = null;
                if (!it.hasNext()) {
                    Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsSearch$default(AppsService, query, arrayList, Integer.valueOf(offset), Integer.valueOf(count), null, null, 48, null)), null, 1, null);
                    final ipakvmocs ipakvmocsVar = new ipakvmocs((AppMapper) this.ipakvmoca.getValue());
                    Observable<AppsSearchResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.c3
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.App.ipakvmocaj(ipakvmocsVar, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
                String str = (String) it.next();
                for (AppsSearchFiltersDto appsSearchFiltersDto2 : AppsSearchFiltersDto.values()) {
                    if (Intrinsics.areEqual(appsSearchFiltersDto2.getValue(), str)) {
                        appsSearchFiltersDto = appsSearchFiltersDto2;
                        break;
                    }
                }
                if (appsSearchFiltersDto != null) {
                    arrayList.add(appsSearchFiltersDto);
                }
            }
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsSearchResponse> sendAppsSearchUnauthorized(@NotNull String query, @NotNull Collection<String> filters, int offset, int count) {
            Intrinsics.checkNotNullParameter(query, "query");
            Intrinsics.checkNotNullParameter(filters, "filters");
            AppsService AppsService = AppsServiceKt.AppsService();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = filters.iterator();
            while (true) {
                AppsSearchFiltersDto appsSearchFiltersDto = null;
                if (!it.hasNext()) {
                    WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsSearch$default(AppsService, query, arrayList, Integer.valueOf(offset), Integer.valueOf(count), null, null, 48, null));
                    webApiRequest.setAnonymous(true);
                    Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
                    final ipakvmoct ipakvmoctVar = new ipakvmoct((AppMapper) this.ipakvmoca.getValue());
                    Observable<AppsSearchResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.k1
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.App.ipakvmocak(ipakvmoctVar, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
                String str = (String) it.next();
                for (AppsSearchFiltersDto appsSearchFiltersDto2 : AppsSearchFiltersDto.values()) {
                    if (Intrinsics.areEqual(appsSearchFiltersDto2.getValue(), str)) {
                        appsSearchFiltersDto = appsSearchFiltersDto2;
                        break;
                    }
                }
                if (appsSearchFiltersDto != null) {
                    arrayList.add(appsSearchFiltersDto);
                }
            }
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<WebAppActivities>> sendGetAppsCatalogActivities() {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetCatalogActivities()), null, 1, null);
            final ipakvmocu ipakvmocuVar = new ipakvmocu((AppMapper) this.ipakvmoca.getValue());
            Observable<List<WebAppActivities>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.a2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocal(ipakvmocuVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<WebAppEmbeddedUrl> sendGetEmbeddedUrl(long appId, @NotNull String url, @NotNull UserId ownerId, @Nullable String ref, @Nullable String trackCode) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(ownerId, "ownerId");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetEmbeddedUrl$default(AppsServiceKt.AppsService(), (int) appId, ownerId, url, ref, trackCode, null, 32, null)), null, 1, null);
            final ipakvmocv ipakvmocvVar = new ipakvmocv((AppMapper) this.ipakvmoca.getValue());
            Observable<WebAppEmbeddedUrl> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.g2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocam(ipakvmocvVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<WebUserShortInfo>> sendGetFriendsList(long appId, int offset, int count, @NotNull String query) {
            Intrinsics.checkNotNullParameter(query, "query");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetFriendsListExtended$default(AppsServiceKt.AppsService(), Integer.valueOf((int) appId), Integer.valueOf(count), Integer.valueOf(offset), null, CollectionsKt.listOf(UsersFieldsDto.PHOTO_BASE), query, 8, null)), null, 1, null);
            final ipakvmocw ipakvmocwVar = new ipakvmocw((AppMapper) this.ipakvmoca.getValue());
            Observable<List<WebUserShortInfo>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.n2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocan(ipakvmocwVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<WebGameLeaderboard>> sendGetGameLeaderboardByApp(long appId, int global, int userResult) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetLeaderboardByApp(Boolean.valueOf(global == 1), Integer.valueOf(userResult), Integer.valueOf((int) appId))), null, 1, null);
            final ipakvmocx ipakvmocxVar = new ipakvmocx((AppMapper) this.ipakvmoca.getValue());
            Observable<List<WebGameLeaderboard>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.v1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocao(ipakvmocxVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppsSection> sendGetGamesSection(@NotNull final String sectionId, int offset, int count) {
            Intrinsics.checkNotNullParameter(sectionId, "sectionId");
            Integer intOrNull = StringsKt.toIntOrNull(sectionId);
            if (intOrNull == null) {
                Observable<AppsSection> observableEmpty = Observable.empty();
                Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
                return observableEmpty;
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetCatalog$default(AppsServiceKt.AppsService(), null, Integer.valueOf(offset), Integer.valueOf(count), null, null, null, null, null, null, null, null, null, intOrNull, null, null, null, 61433, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.h1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca(this.f51808a, sectionId, (AppsCatalogListDto) obj);
                }
            };
            Observable<AppsSection> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.i1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocap(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<AppLaunchParams> sendGetLaunchParams(long appId, @NotNull String referrer, @Nullable Long groupId, @Nullable Long vkProfileId) {
            Intrinsics.checkNotNullParameter(referrer, "referrer");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(AppsService.DefaultImpls.appsGetAppLaunchParams$default(AppsServiceKt.AppsService(), (int) appId, referrer, groupId != null ? new UserId(groupId.longValue()) : null, vkProfileId != null ? new UserId(vkProfileId.longValue()) : null, null, 16, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.e2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoca((JSONObject) obj);
                }
            };
            Observable map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.f2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocaq(function1, obj);
                }
            });
            final ipakvmocy ipakvmocyVar = new ipakvmocy((AppMapper) this.ipakvmoca.getValue());
            Observable<AppLaunchParams> map2 = map.map(new Function() { // from class: com.vk.superapp.api.contract.h2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocar(ipakvmocyVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            return map2;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<AppsCategory>> sendGetMiniAppCategories() {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsGetMiniAppCategories$default(AppsServiceKt.AppsService(), null, null, 3, null)), null, 1, null);
            final ipakvmocz ipakvmoczVar = new ipakvmocz((AppMapper) this.ipakvmoca.getValue());
            Observable<List<AppsCategory>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.k2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocas(ipakvmoczVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<AppsSecretHash> sendGetSecretHash(long appId, @Nullable String requestId) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetSecretHash((int) appId, requestId)), null, 1, null);
            final ipakvmocaa ipakvmocaaVar = new ipakvmocaa((AppMapper) this.ipakvmoca.getValue());
            Single<AppsSecretHash> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.o2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocat(ipakvmocaaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<AppsSection>> sendGetVkApps(@Nullable String sectionId, @Nullable Integer limit, @Nullable Integer offset, @Nullable Double lat, @Nullable Double lon, @Nullable List<? extends AppFields> appFields) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetVkApps(sectionId, lat != null ? Float.valueOf((float) lat.doubleValue()) : null, lon != null ? Float.valueOf((float) lon.doubleValue()) : null, null, limit, offset, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields))), null, 1, null);
            final ipakvmocab ipakvmocabVar = new ipakvmocab((AppMapper) this.ipakvmoca.getValue());
            Observable<List<AppsSection>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.r2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocau(ipakvmocabVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Observable<List<AppsSection>> sendGetVkAppsUnauthorized(@Nullable String sectionId, double lat, double lon, @Nullable List<? extends AppFields> appFields) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetVkApps(sectionId, Float.valueOf((float) lat), Float.valueOf((float) lon), null, null, null, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields)));
            webApiRequest.setAnonymous(true);
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            final ipakvmocac ipakvmocacVar = new ipakvmocac((AppMapper) this.ipakvmoca.getValue());
            Observable<List<AppsSection>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.p1
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocav(ipakvmocacVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<WebApiApplication> sendJoinAndGet(long appId, @Nullable String ref, @Nullable List<? extends AppFields> appFields, @Nullable String specialUrl, @Nullable String trackCode, @Nullable Integer needSettings) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsJoinAndGet$default(AppsServiceKt.AppsService(), Integer.valueOf((int) appId), needSettings, specialUrl, ref, null, null, trackCode, ((AppMapper) this.ipakvmoca.getValue()).mapAppsFields(appFields), null, 304, null)), null, 1, null);
            final ipakvmocad ipakvmocadVar = new ipakvmocad((AppMapper) this.ipakvmoca.getValue());
            Single<WebApiApplication> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.p2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocaw(ipakvmocadVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<Boolean> sendSetGameIsInstalled(long appId, @Nullable String trackCode) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsSetGameIsInstalled((int) appId, trackCode)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.q2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocd((BaseBoolIntDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.s2
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocax(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<Boolean> setUnverifiedScreenShown(long appId) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsSetUnverifiedScreenShown((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.f3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmoci((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.g3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.App.ipakvmocay(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.App
        @NotNull
        public Single<AppsStartCallResponseDto> startAppCall(long appId) {
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsStartCall((int) appId)), null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AddActionSuggestion ipakvmocc(Function1 function1, Object obj) {
            return (AddActionSuggestion) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final OrdersCancelUserSubscriptionResult ipakvmoce(Function1 function1, Object obj) {
            return (OrdersCancelUserSubscriptionResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SubscriptionConfirmResult ipakvmocf(Function1 function1, Object obj) {
            return (SubscriptionConfirmResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ConfirmResult ipakvmocg(Function1 function1, Object obj) {
            return (ConfirmResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebOrderInfo ipakvmoch(Function1 function1, Object obj) {
            return (WebOrderInfo) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoci(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AdvertisementMapper ipakvmoca() {
            return new AdvertisementMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocb(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CheckInviteUserData ipakvmoca(AppsCheckInviteFriendResponseDto appsCheckInviteFriendResponseDto) {
            String text = appsCheckInviteFriendResponseDto.getText();
            if (text == null) {
                text = "";
            }
            BaseImageDto photo = appsCheckInviteFriendResponseDto.getPhoto();
            String url = photo != null ? photo.getUrl() : null;
            return new CheckInviteUserData(text, url != null ? url : "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Integer num) {
            return Boolean.valueOf(num != null && num.intValue() == BaseBoolIntDto.YES.getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(List list, AppsInviteMultipleFriendResponseDto appsInviteMultipleFriendResponseDto) {
            Integer sentCount = appsInviteMultipleFriendResponseDto.getSentCount();
            return Boolean.valueOf(sentCount != null && sentCount.intValue() == list.size());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final OrdersOrderDto.StatusDto ipakvmoca(int i10, List list) {
            Object next;
            OrdersOrderDto.StatusDto status;
            Intrinsics.checkNotNull(list);
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((OrdersOrderDto) next).getId(), String.valueOf(i10)));
            OrdersOrderDto ordersOrderDto = (OrdersOrderDto) next;
            return (ordersOrderDto == null || (status = ordersOrderDto.getStatus()) == null) ? OrdersOrderDto.StatusDto.CANCELLED : status;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONObject ipakvmoca(JSONObject jSONObject) {
            return jSONObject.getJSONObject("response");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(AppsGetGroupsListResponseDto appsGetGroupsListResponseDto) {
            List<AppsGetGroupsListItemDto> items = appsGetGroupsListResponseDto.getItems();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(items, 10));
            Iterator<T> it = items.iterator();
            while (it.hasNext()) {
                arrayList.add(AppsGroupsContainerKt.toAppsGroupsContainer((AppsGetGroupsListItemDto) it.next()));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Banner ipakvmoca(AppsActionBannerDto appsActionBannerDto) {
            BannerMapper bannerMapper = new BannerMapper();
            Intrinsics.checkNotNull(appsActionBannerDto);
            return bannerMapper.map(appsActionBannerDto);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Banner ipakvmoca(Function1 function1, Object obj) {
            return (Banner) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JY\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Captcha;", "Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "<init>", "()V", "captchaForce", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "captchaSid", "", "captchaKey", "isSoundCaptcha", "", "uiuxChanges", "isRefreshEnabled", "isSoundCaptchaAvailable", "ui", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Captcha implements SuperappApi.Captcha {
        @Override // com.vk.superapp.api.contract.SuperappApi.Captcha
        @NotNull
        public Single<BaseOkResponseDto> captchaForce(@Nullable String captchaSid, @Nullable String captchaKey, @Nullable Integer isSoundCaptcha, @Nullable Integer uiuxChanges, @Nullable Integer isRefreshEnabled, @Nullable Integer isSoundCaptchaAvailable, @Nullable Integer ui) {
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(CaptchaService.DefaultImpls.captchaForce$default(CaptchaServiceKt.CaptchaService(), captchaSid, captchaKey, isSoundCaptcha, uiuxChanges, isRefreshEnabled, null, isSoundCaptchaAvailable, ui, null, null, 800, null)).allowNoAuth(), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Database;", "Lcom/vk/superapp/api/contract/SuperappApi$Database;", "<init>", "()V", "", "country", "", "query", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/vk/superapp/api/dto/identity/WebCity;", "getCities", "(ILjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Database\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1#2:3567\n*E\n"})
    public static final class Database implements SuperappApi.Database {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.n3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Database.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<DatabaseGetCitiesResponseDto, List<? extends WebCity>> {
            ipakvmoca(DatabaseMapper databaseMapper) {
                super(1, databaseMapper, DatabaseMapper.class, "mapCities", "mapCities(Lcom/vk/api/generated/database/dto/DatabaseGetCitiesResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebCity> invoke(DatabaseGetCitiesResponseDto databaseGetCitiesResponseDto) {
                DatabaseGetCitiesResponseDto p10 = databaseGetCitiesResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((DatabaseMapper) this.receiver).mapCities(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DatabaseMapper ipakvmoca() {
            return new DatabaseMapper();
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Database
        @NotNull
        public Single<List<WebCity>> getCities(int country, @Nullable String query) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(DatabaseService.DefaultImpls.databaseGetCities$default(DatabaseServiceKt.DatabaseService(), Integer.valueOf(country), null, query, null, null, null, null, null, 250, null));
            webApiRequest.setAnonymous(true);
            Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((DatabaseMapper) this.ipakvmoca.getValue());
            Single<List<WebCity>> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.o3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Database.ipakvmoca(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ/\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\u0006\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Email;", "Lcom/vk/superapp/api/contract/SuperappApi$Email;", "<init>", "()V", "", "username", CommonConstant.KEY_ACCESS_TOKEN, "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/email/EmailCreationResponse;", "canCreateEmail", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "", "adsAcceptance", "createEmail", "(Ljava/lang/String;ZLjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "accepted", "", "setAdsAcceptance", "(ZLjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Email\n+ 2 KotlinCommonExt.kt\ncom/vk/core/extensions/KotlinCommonExtKt\n*L\n1#1,3566:1\n9#2,2:3567\n9#2,2:3569\n9#2,2:3571\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Email\n*L\n2997#1:3567,2\n3013#1:3569,2\n3024#1:3571,2\n*E\n"})
    public static class Email implements SuperappApi.Email {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.p3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Email.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<EmailCreationResponseDto, EmailCreationResponse> {
            ipakvmoca(EmailMapper emailMapper) {
                super(1, emailMapper, EmailMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/email/dto/EmailCreationResponseDto;)Lcom/vk/superapp/api/dto/email/EmailCreationResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final EmailCreationResponse invoke(EmailCreationResponseDto emailCreationResponseDto) {
                EmailCreationResponseDto p10 = emailCreationResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((EmailMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<EmailCreationResponseDto, EmailCreationResponse> {
            ipakvmocb(EmailMapper emailMapper) {
                super(1, emailMapper, EmailMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/email/dto/EmailCreationResponseDto;)Lcom/vk/superapp/api/dto/email/EmailCreationResponse;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final EmailCreationResponse invoke(EmailCreationResponseDto emailCreationResponseDto) {
                EmailCreationResponseDto p10 = emailCreationResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((EmailMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final EmailMapper ipakvmoca() {
            return new EmailMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final EmailCreationResponse ipakvmocb(Function1 function1, Object obj) {
            return (EmailCreationResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit ipakvmocc(Function1 function1, Object obj) {
            return (Unit) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Email
        @NotNull
        public Single<EmailCreationResponse> canCreateEmail(@NotNull String username, @Nullable String accessToken) {
            Intrinsics.checkNotNullParameter(username, "username");
            try {
                WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(EmailServiceKt.EmailService().emailCanCreate(username));
                if (accessToken != null) {
                    webApiRequest.overrideAuth(accessToken, null);
                }
                Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
                final ipakvmoca ipakvmocaVar = new ipakvmoca((EmailMapper) this.ipakvmoca.getValue());
                Single<EmailCreationResponse> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.t3
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return GeneratedSuperappApi.Email.ipakvmoca(ipakvmocaVar, obj);
                    }
                });
                Intrinsics.checkNotNull(map);
                return map;
            } catch (Exception e10) {
                Single<EmailCreationResponse> singleError = Single.error(e10);
                Intrinsics.checkNotNull(singleError);
                return singleError;
            }
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Email
        @NotNull
        public Single<EmailCreationResponse> createEmail(@NotNull String username, boolean adsAcceptance, @Nullable String accessToken) {
            Intrinsics.checkNotNullParameter(username, "username");
            try {
                WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(EmailServiceKt.EmailService().emailCreate(username, Boolean.valueOf(adsAcceptance)));
                if (accessToken != null) {
                    webApiRequest.overrideAuth(accessToken, null);
                }
                Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
                final ipakvmocb ipakvmocbVar = new ipakvmocb((EmailMapper) this.ipakvmoca.getValue());
                Single<EmailCreationResponse> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.s3
                    @Override // io.reactivex.rxjava3.functions.Function
                    public final Object apply(Object obj) {
                        return GeneratedSuperappApi.Email.ipakvmocb(ipakvmocbVar, obj);
                    }
                });
                Intrinsics.checkNotNull(map);
                return map;
            } catch (Exception e10) {
                Single<EmailCreationResponse> singleError = Single.error(e10);
                Intrinsics.checkNotNull(singleError);
                return singleError;
            }
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Email
        @NotNull
        public Single<Unit> setAdsAcceptance(boolean accepted, @Nullable String accessToken) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(EmailServiceKt.EmailService().emailSetAdsAcceptance(accepted));
            if (accessToken != null) {
                webApiRequest.overrideAuth(accessToken, null);
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.q3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Email.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Unit> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.r3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Email.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final EmailCreationResponse ipakvmoca(Function1 function1, Object obj) {
            return (EmailCreationResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0016¨\u0006\u0012"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Esia;", "Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "<init>", "()V", "checkEsiaLink", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/esia/dto/EsiaCheckEsiaLinkResponseDto;", "flow", "Lcom/vk/superapp/api/dto/esia/EsiaCheckEsiaLinkFlow;", "getEsiaUserInfo", "Lcom/vk/api/generated/esia/dto/EsiaGetEsiaUserInfoResponseDto;", "esiaSid", "", "createLink", "", "cuaToken", "verifyUser", "linkAndVerify", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Esia implements SuperappApi.Esia {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EsiaCheckEsiaLinkFlow.values().length];
                try {
                    iArr[EsiaCheckEsiaLinkFlow.VERIFY.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EsiaCheckEsiaLinkFlow.LOGIN.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Esia
        @NotNull
        public Single<EsiaCheckEsiaLinkResponseDto> checkEsiaLink(@NotNull EsiaCheckEsiaLinkFlow flow) {
            EsiaCheckEsiaLinkFlowDto esiaCheckEsiaLinkFlowDto;
            Intrinsics.checkNotNullParameter(flow, "flow");
            int i10 = WhenMappings.$EnumSwitchMapping$0[flow.ordinal()];
            if (i10 == 1) {
                esiaCheckEsiaLinkFlowDto = EsiaCheckEsiaLinkFlowDto.VERIFY;
            } else {
                if (i10 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                esiaCheckEsiaLinkFlowDto = EsiaCheckEsiaLinkFlowDto.LOGIN;
            }
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(EsiaServiceKt.EsiaService().esiaCheckEsiaLink(esiaCheckEsiaLinkFlowDto)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Esia
        @NotNull
        public Single<Boolean> createLink(@NotNull String esiaSid, @NotNull String cuaToken) {
            Intrinsics.checkNotNullParameter(esiaSid, "esiaSid");
            Intrinsics.checkNotNullParameter(cuaToken, "cuaToken");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(EsiaService.DefaultImpls.esiaCreateLink$default(EsiaServiceKt.EsiaService(), esiaSid, cuaToken, null, 4, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.v3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Esia
        @NotNull
        public Single<EsiaGetEsiaUserInfoResponseDto> getEsiaUserInfo(@NotNull String esiaSid, @NotNull EsiaCheckEsiaLinkFlow flow) {
            EsiaGetEsiaUserInfoFlowDto esiaGetEsiaUserInfoFlowDto;
            Intrinsics.checkNotNullParameter(esiaSid, "esiaSid");
            Intrinsics.checkNotNullParameter(flow, "flow");
            int i10 = WhenMappings.$EnumSwitchMapping$0[flow.ordinal()];
            if (i10 == 1) {
                esiaGetEsiaUserInfoFlowDto = EsiaGetEsiaUserInfoFlowDto.VERIFY;
            } else {
                if (i10 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                esiaGetEsiaUserInfoFlowDto = EsiaGetEsiaUserInfoFlowDto.LOGIN;
            }
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(EsiaServiceKt.EsiaService().esiaGetEsiaUserInfo(esiaSid, esiaGetEsiaUserInfoFlowDto)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Esia
        @NotNull
        public Single<Boolean> linkAndVerify(@NotNull String esiaSid, @NotNull String cuaToken) {
            Intrinsics.checkNotNullParameter(esiaSid, "esiaSid");
            Intrinsics.checkNotNullParameter(cuaToken, "cuaToken");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(EsiaService.DefaultImpls.esiaLinkAndVerify$default(EsiaServiceKt.EsiaService(), esiaSid, cuaToken, null, 4, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.y3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.z3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Esia
        @NotNull
        public Single<Boolean> verifyUser(@NotNull String cuaToken) {
            Intrinsics.checkNotNullParameter(cuaToken, "cuaToken");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(EsiaService.DefaultImpls.esiaVerifyUser$default(EsiaServiceKt.EsiaService(), cuaToken, null, 2, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmocc((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.x3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Esia.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Friends;", "Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "<init>", "()V", "", "offset", "count", "Lio/reactivex/rxjava3/core/Observable;", "", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "sendGetFriends", "(II)Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static class Friends implements SuperappApi.Friends {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.c4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Friends.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final FriendsMapper ipakvmoca() {
            return new FriendsMapper(new ImageMapper());
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Friends
        @NotNull
        public Observable<List<WebUserShortInfo>> sendGetFriends(int offset, int count) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(FriendsService.DefaultImpls.friendsGet$default(FriendsServiceKt.FriendsService(), null, FriendsGetOrderDto.NAME, null, Integer.valueOf(count), Integer.valueOf(offset), CollectionsKt.listOf((Object[]) new UsersFieldsDto[]{UsersFieldsDto.FIRST_NAME_NOM, UsersFieldsDto.LAST_NAME_NOM, UsersFieldsDto.SEX, UsersFieldsDto.PHOTO_BASE}), null, null, null, null, VKApiCodes.CODE_CHAT_NOT_IN_ARCHIVE, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Friends.ipakvmoca(this.f51755a, (FriendsGetFieldsResponseDto) obj);
                }
            };
            Observable<List<WebUserShortInfo>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.b4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Friends.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(Friends friends, FriendsGetFieldsResponseDto friendsGetFieldsResponseDto) {
            FriendsMapper friendsMapper = (FriendsMapper) friends.ipakvmoca.getValue();
            Intrinsics.checkNotNull(friendsGetFieldsResponseDto);
            return friendsMapper.map(friendsGetFieldsResponseDto);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$GoodsOrders;", "Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "<init>", "()V", "sendAppGetGoodsOrderInfo", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersGoodItemDto;", "appId", "", "itemId", "", "sendAppCreateGoodsOrder", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersNewOrderItemDto;", "sendAppGetGoodsOrder", "Lcom/vk/api/generated/goodsOrders/dto/GoodsOrdersOrderItemDto;", "orderId", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class GoodsOrders implements SuperappApi.GoodsOrders {
        @Override // com.vk.superapp.api.contract.SuperappApi.GoodsOrders
        @NotNull
        public Single<GoodsOrdersNewOrderItemDto> sendAppCreateGoodsOrder(long appId, @NotNull String itemId) {
            Intrinsics.checkNotNullParameter(itemId, "itemId");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(GoodsOrdersServiceKt.GoodsOrdersService().goodsOrdersCreateOrder((int) appId, itemId)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.GoodsOrders
        @NotNull
        public Single<GoodsOrdersOrderItemDto> sendAppGetGoodsOrder(int orderId) {
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(GoodsOrdersServiceKt.GoodsOrdersService().goodsOrdersGetOrder(orderId)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.GoodsOrders
        @NotNull
        public Single<GoodsOrdersGoodItemDto> sendAppGetGoodsOrderInfo(long appId, @NotNull String itemId) {
            Intrinsics.checkNotNullParameter(itemId, "itemId");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(GoodsOrdersServiceKt.GoodsOrdersService().goodsOrdersGetItemData((int) appId, itemId)), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ)\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\tJ)\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\n0\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0016¢\u0006\u0004\b\u0010\u0010\rJ%\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001b\u0010\tJ5\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Group;", "Lcom/vk/superapp/api/contract/SuperappApi$Group;", "<init>", "()V", "", "groupId", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/group/WebGroupShortInfo;", "sendGetGroupShortInfo", "(J)Lio/reactivex/rxjava3/core/Observable;", "", "groupIds", "sendGetGroupsShortInfo", "(Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/group/WebGroup;", "sendGroupsGetById", "sendGroupsGetByIds", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "sendGroupsIsMember", "(JJ)Lio/reactivex/rxjava3/core/Observable;", "unsure", "", "refer", "inviteCode", "sendGroupsJoin", "(JZLjava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "sendGroupsLeave", "appId", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "time", "sendGroupsSendPayload", "(JJLjava/lang/String;J)Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Group\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3566:1\n1563#2:3567\n1634#2,3:3568\n1563#2:3571\n1634#2,3:3572\n12897#3,3:3575\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Group\n*L\n1651#1:3567\n1651#1:3568,3\n1675#1:3571\n1675#1:3572,3\n1763#1:3575,3\n*E\n"})
    public static final class Group implements SuperappApi.Group {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.r4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Group.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<GroupsGetByIdObjectResponseDto, List<? extends WebGroupShortInfo>> {
            ipakvmoca(GroupMapper groupMapper) {
                super(1, groupMapper, GroupMapper.class, "mapToWebGroupShortInfo", "mapToWebGroupShortInfo(Lcom/vk/api/generated/groups/dto/GroupsGetByIdObjectResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebGroupShortInfo> invoke(GroupsGetByIdObjectResponseDto groupsGetByIdObjectResponseDto) {
                GroupsGetByIdObjectResponseDto p10 = groupsGetByIdObjectResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((GroupMapper) this.receiver).mapToWebGroupShortInfo(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<GroupsGetByIdObjectResponseDto, List<? extends WebGroup>> {
            ipakvmocb(GroupMapper groupMapper) {
                super(1, groupMapper, GroupMapper.class, "mapToWebGroup", "mapToWebGroup(Lcom/vk/api/generated/groups/dto/GroupsGetByIdObjectResponseDto;)Ljava/util/List;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebGroup> invoke(GroupsGetByIdObjectResponseDto groupsGetByIdObjectResponseDto) {
                GroupsGetByIdObjectResponseDto p10 = groupsGetByIdObjectResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((GroupMapper) this.receiver).mapToWebGroup(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final GroupMapper ipakvmoca() {
            return new GroupMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocb(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebGroup ipakvmocc(Function1 function1, Object obj) {
            return (WebGroup) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocd(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoce(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocf(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocg(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoch(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<WebGroupShortInfo> sendGetGroupShortInfo(long groupId) {
            Observable<List<WebGroupShortInfo>> observableSendGetGroupsShortInfo = sendGetGroupsShortInfo(CollectionsKt.listOf(Long.valueOf(groupId)));
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.d4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoca((List) obj);
                }
            };
            Observable map = observableSendGetGroupsShortInfo.map(new Function() { // from class: com.vk.superapp.api.contract.j4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<List<WebGroupShortInfo>> sendGetGroupsShortInfo(@NotNull List<Long> groupIds) {
            Intrinsics.checkNotNullParameter(groupIds, "groupIds");
            GroupsService GroupsService = GroupsServiceKt.GroupsService();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(groupIds, 10));
            Iterator<T> it = groupIds.iterator();
            while (it.hasNext()) {
                arrayList.add(new UserId(((Number) it.next()).longValue()));
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(com.vk.common.api.generated.groups.GroupsService.DefaultImpls.groupsGetById$default(GroupsService, arrayList, CollectionsKt.listOf((Object[]) new GroupsFieldsDto[]{GroupsFieldsDto.ID, GroupsFieldsDto.NAME, GroupsFieldsDto.SCREEN_NAME, GroupsFieldsDto.IS_CLOSED, GroupsFieldsDto.TYPE, GroupsFieldsDto.IS_MEMBER, GroupsFieldsDto.DESCRIPTION, GroupsFieldsDto.MEMBERS_COUNT, GroupsFieldsDto.PHOTO_BASE}), null, 4, null)), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((GroupMapper) this.ipakvmoca.getValue());
            Observable<List<WebGroupShortInfo>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.o4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocb(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<WebGroup> sendGroupsGetById(long groupId) {
            Observable<List<WebGroup>> observableSendGroupsGetByIds = sendGroupsGetByIds(CollectionsKt.listOf(Long.valueOf(groupId)));
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.h4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocb((List) obj);
                }
            };
            Observable map = observableSendGroupsGetByIds.map(new Function() { // from class: com.vk.superapp.api.contract.i4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<List<WebGroup>> sendGroupsGetByIds(@NotNull List<Long> groupIds) {
            Intrinsics.checkNotNullParameter(groupIds, "groupIds");
            GroupsService GroupsService = GroupsServiceKt.GroupsService();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(groupIds, 10));
            Iterator<T> it = groupIds.iterator();
            while (it.hasNext()) {
                arrayList.add(new UserId(((Number) it.next()).longValue()));
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(com.vk.common.api.generated.groups.GroupsService.DefaultImpls.groupsGetById$default(GroupsService, arrayList, CollectionsKt.listOf((Object[]) new GroupsFieldsDto[]{GroupsFieldsDto.ID, GroupsFieldsDto.NAME, GroupsFieldsDto.IS_CLOSED, GroupsFieldsDto.PHOTO_BASE}), null, 4, null)), null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb((GroupMapper) this.ipakvmoca.getValue());
            Observable<List<WebGroup>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.g4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocd(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<Boolean> sendGroupsIsMember(long groupId, long userId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(GroupsService.DefaultImpls.groupsIsMember$default(GroupsServiceKt.GroupsService(), new UserId(groupId), new UserId(userId), null, null, 12, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.p4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.q4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoce(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<Boolean> sendGroupsJoin(long groupId, boolean unsure, @Nullable String refer, @Nullable String inviteCode) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(GroupsService.DefaultImpls.groupsJoin$default(GroupsServiceKt.GroupsService(), new UserId(groupId), unsure ? "1" : PaginationHelper.DEFAULT_NEXT_FROM, refer, null, inviteCode, null, null, null, null, 480, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.n4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocf(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<Boolean> sendGroupsLeave(long groupId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(GroupsService.DefaultImpls.groupsLeave$default(GroupsServiceKt.GroupsService(), new UserId(groupId), null, null, null, null, null, null, 126, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.e4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.f4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocg(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Group
        @NotNull
        public Observable<Boolean> sendGroupsSendPayload(long appId, long groupId, @NotNull String payload, long time) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            GroupsService GroupsService = GroupsServiceKt.GroupsService();
            int i10 = (int) appId;
            UserId userId = new UserId(groupId);
            int i11 = (int) time;
            byte[] bytes = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new String[]{String.valueOf(time), SuperappApiCore.INSTANCE.getApiAppSecret(), String.valueOf(appId), String.valueOf(groupId), "U$83gh9t)!0G9KXS]INXG(-q!dFY-["}), HiAnalyticsConstant.REPORT_VAL_SEPARATOR, null, null, 0, null, null, 62, null).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(bytes);
            Intrinsics.checkNotNull(bArrDigest);
            String string = "";
            for (byte b10 : bArrDigest) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                sb2.append(str);
                string = sb2.toString();
            }
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(GroupsService.groupsSendPayload(i10, userId, payload, i11, string)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.k4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmocc((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Group.ipakvmoch(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebGroupShortInfo ipakvmoca(List list) {
            Intrinsics.checkNotNull(list);
            return (WebGroupShortInfo) CollectionsKt.first(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebGroup ipakvmocb(List list) {
            Intrinsics.checkNotNull(list);
            return (WebGroup) CollectionsKt.first(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebGroupShortInfo ipakvmoca(Function1 function1, Object obj) {
            return (WebGroupShortInfo) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00042\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ=\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J%\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u001d\u0010\u001aJ\u001d\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u00042\u0006\u0010\u001e\u001a\u00020\u0010H\u0016¢\u0006\u0004\b \u0010!J\u001d\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00042\u0006\u0010\u001e\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\"\u0010!J\u001d\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00042\u0006\u0010\u001e\u001a\u00020\u0010H\u0016¢\u0006\u0004\b#\u0010!J\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00140\u00042\u0006\u0010$\u001a\u00020\u0014H\u0016¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0004\b'\u0010(J\u001d\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\u0006\u0010)\u001a\u00020\u001cH\u0016¢\u0006\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Identity;", "Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "<init>", "()V", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/identity/WebIdentityCardData;", "getCard", "()Lio/reactivex/rxjava3/core/Single;", "", "type", "", "Lcom/vk/superapp/api/dto/identity/WebIdentityLabel;", "getLabels", "(Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "label", "specifiedAddress", "", "countryId", "cityId", "postalCode", "Lcom/vk/superapp/api/dto/identity/WebIdentityAddress;", "addAddress", "(Lcom/vk/superapp/api/dto/identity/WebIdentityLabel;Ljava/lang/String;IILjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "email", "Lcom/vk/superapp/api/dto/identity/WebIdentityEmail;", "addEmail", "(Lcom/vk/superapp/api/dto/identity/WebIdentityLabel;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "phoneNumber", "Lcom/vk/superapp/api/dto/identity/WebIdentityPhone;", "addPhone", "id", "", "deleteAddress", "(I)Lio/reactivex/rxjava3/core/Single;", "deleteEmail", "deletePhone", "address", "editAddress", "(Lcom/vk/superapp/api/dto/identity/WebIdentityAddress;)Lio/reactivex/rxjava3/core/Single;", "editEmail", "(Lcom/vk/superapp/api/dto/identity/WebIdentityEmail;)Lio/reactivex/rxjava3/core/Single;", "phone", "editPhone", "(Lcom/vk/superapp/api/dto/identity/WebIdentityPhone;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Identity\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1310#2,2:3567\n1#3:3569\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Identity\n*L\n2805#1:3567,2\n*E\n"})
    public static final class Identity implements SuperappApi.Identity {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.i5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Identity.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<IdentityGetCardResponseDto, WebIdentityCardData> {
            ipakvmoca(IdentityMapper identityMapper) {
                super(1, identityMapper, IdentityMapper.class, "mapToWebIdentityCardData", "mapToWebIdentityCardData(Lcom/vk/api/generated/identity/dto/IdentityGetCardResponseDto;)Lcom/vk/superapp/api/dto/identity/WebIdentityCardData;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebIdentityCardData invoke(IdentityGetCardResponseDto identityGetCardResponseDto) {
                IdentityGetCardResponseDto p10 = identityGetCardResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((IdentityMapper) this.receiver).mapToWebIdentityCardData(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<List<? extends IdentityLabelDto>, List<? extends WebIdentityLabel>> {
            ipakvmocb(IdentityMapper identityMapper) {
                super(1, identityMapper, IdentityMapper.class, "mapToWebIdentityLabels", "mapToWebIdentityLabels(Ljava/util/List;)Ljava/util/List;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebIdentityLabel> invoke(List<? extends IdentityLabelDto> list) {
                List<? extends IdentityLabelDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((IdentityMapper) this.receiver).mapToWebIdentityLabels(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityEmail ipakvmoca(WebIdentityEmail webIdentityEmail, BaseOkResponseDto baseOkResponseDto) {
            return webIdentityEmail;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityEmail ipakvmocb(Function1 function1, Object obj) {
            return (WebIdentityEmail) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityPhone ipakvmocc(Function1 function1, Object obj) {
            return (WebIdentityPhone) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoce(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocf(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityAddress ipakvmocg(Function1 function1, Object obj) {
            return (WebIdentityAddress) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityEmail ipakvmoch(Function1 function1, Object obj) {
            return (WebIdentityEmail) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityPhone ipakvmoci(Function1 function1, Object obj) {
            return (WebIdentityPhone) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityCardData ipakvmocj(Function1 function1, Object obj) {
            return (WebIdentityCardData) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmock(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityAddress> addAddress(@NotNull final WebIdentityLabel label, @NotNull final String specifiedAddress, final int countryId, final int cityId, @NotNull final String postalCode) {
            IdentityAddAddressLabelIdDto identityAddAddressLabelIdDto;
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(specifiedAddress, "specifiedAddress");
            Intrinsics.checkNotNullParameter(postalCode, "postalCode");
            int id2 = label.getId();
            if (id2 != 1) {
                identityAddAddressLabelIdDto = id2 != 2 ? null : IdentityAddAddressLabelIdDto.TYPE_2;
            } else {
                identityAddAddressLabelIdDto = IdentityAddAddressLabelIdDto.TYPE_1;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityAddAddress(countryId, cityId, specifiedAddress, postalCode, !label.isCustom() ? identityAddAddressLabelIdDto : null, label.isCustom() ? label.getName() : null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.g5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(label, postalCode, specifiedAddress, cityId, countryId, (IdentityAddressResponseDto) obj);
                }
            };
            Single<WebIdentityAddress> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.h5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityEmail> addEmail(@NotNull final WebIdentityLabel label, @NotNull final String email) {
            IdentityAddEmailLabelIdDto identityAddEmailLabelIdDto;
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(email, "email");
            int id2 = label.getId();
            if (id2 != 1) {
                identityAddEmailLabelIdDto = id2 != 3 ? null : IdentityAddEmailLabelIdDto.TYPE_3;
            } else {
                identityAddEmailLabelIdDto = IdentityAddEmailLabelIdDto.TYPE_1;
            }
            IdentityService IdentityService = IdentityServiceKt.IdentityService();
            if (label.isCustom()) {
                identityAddEmailLabelIdDto = null;
            }
            String name = label.getName();
            if (!label.isCustom()) {
                name = null;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityService.identityAddEmail(email, identityAddEmailLabelIdDto, name)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(label, email, (BaseCreateResponseDto) obj);
                }
            };
            Single<WebIdentityEmail> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.x4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityPhone> addPhone(@NotNull final WebIdentityLabel label, @NotNull String phoneNumber) {
            IdentityAddPhoneLabelIdDto identityAddPhoneLabelIdDto;
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
            int id2 = label.getId();
            if (id2 == 1) {
                identityAddPhoneLabelIdDto = IdentityAddPhoneLabelIdDto.TYPE_1;
            } else if (id2 != 2) {
                identityAddPhoneLabelIdDto = id2 != 3 ? null : IdentityAddPhoneLabelIdDto.TYPE_3;
            } else {
                identityAddPhoneLabelIdDto = IdentityAddPhoneLabelIdDto.TYPE_2;
            }
            IdentityService IdentityService = IdentityServiceKt.IdentityService();
            if (label.isCustom()) {
                identityAddPhoneLabelIdDto = null;
            }
            String name = label.getName();
            if (!label.isCustom()) {
                name = null;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityService.identityAddPhone(phoneNumber, identityAddPhoneLabelIdDto, name)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.y4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(label, (IdentityPhoneResponseDto) obj);
                }
            };
            Single<WebIdentityPhone> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.z4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<Boolean> deleteAddress(int id2) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityDeleteAddress(id2)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.b5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocd(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<Boolean> deleteEmail(int id2) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityDeleteEmail(id2)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.s4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.d5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoce(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<Boolean> deletePhone(int id2) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityDeletePhone(id2)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.c5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocc((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.e5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocf(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityAddress> editAddress(@NotNull final WebIdentityAddress address) {
            IdentityEditAddressLabelIdDto identityEditAddressLabelIdDto;
            Intrinsics.checkNotNullParameter(address, "address");
            WebIdentityLabel label = address.getLabel();
            int id2 = label.getId();
            if (id2 != 1) {
                identityEditAddressLabelIdDto = id2 != 2 ? null : IdentityEditAddressLabelIdDto.TYPE_2;
            } else {
                identityEditAddressLabelIdDto = IdentityEditAddressLabelIdDto.TYPE_1;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityEditAddress(address.getId(), address.getCountryId(), address.getCityId(), address.getSpecifiedAddress(), address.getPostalCode(), !label.isCustom() ? identityEditAddressLabelIdDto : null, label.isCustom() ? label.getName() : null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(address, (IdentityAddressResponseDto) obj);
                }
            };
            Single<WebIdentityAddress> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.t4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocg(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityEmail> editEmail(@NotNull final WebIdentityEmail email) {
            IdentityEditEmailLabelIdDto identityEditEmailLabelIdDto;
            Intrinsics.checkNotNullParameter(email, "email");
            WebIdentityLabel label = email.getLabel();
            int id2 = label.getId();
            if (id2 != 1) {
                identityEditEmailLabelIdDto = id2 != 3 ? null : IdentityEditEmailLabelIdDto.TYPE_3;
            } else {
                identityEditEmailLabelIdDto = IdentityEditEmailLabelIdDto.TYPE_1;
            }
            IdentityService IdentityService = IdentityServiceKt.IdentityService();
            int id3 = email.getId();
            String email2 = email.getEmail();
            if (label.isCustom()) {
                identityEditEmailLabelIdDto = null;
            }
            String name = label.getName();
            if (!label.isCustom()) {
                name = null;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityService.identityEditEmail(id3, email2, identityEditEmailLabelIdDto, name)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.k5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(email, (BaseOkResponseDto) obj);
                }
            };
            Single<WebIdentityEmail> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.l5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoch(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityPhone> editPhone(@NotNull WebIdentityPhone phone) {
            IdentityEditPhoneLabelIdDto identityEditPhoneLabelIdDto;
            Intrinsics.checkNotNullParameter(phone, "phone");
            final WebIdentityLabel label = phone.getLabel();
            int id2 = phone.getLabel().getId();
            if (id2 == 1) {
                identityEditPhoneLabelIdDto = IdentityEditPhoneLabelIdDto.TYPE_1;
            } else if (id2 != 2) {
                identityEditPhoneLabelIdDto = id2 != 3 ? null : IdentityEditPhoneLabelIdDto.TYPE_3;
            } else {
                identityEditPhoneLabelIdDto = IdentityEditPhoneLabelIdDto.TYPE_2;
            }
            IdentityService IdentityService = IdentityServiceKt.IdentityService();
            int id3 = phone.getId();
            String phoneNumber = phone.getPhoneNumber();
            if (label.isCustom()) {
                identityEditPhoneLabelIdDto = null;
            }
            String name = label.getName();
            if (!label.isCustom()) {
                name = null;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityService.identityEditPhone(id3, phoneNumber, identityEditPhoneLabelIdDto, name)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoca(this.f51898a, label, (IdentityPhoneResponseDto) obj);
                }
            };
            Single<WebIdentityPhone> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.v4
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmoci(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<WebIdentityCardData> getCard() {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityGetCard()), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((IdentityMapper) this.ipakvmoca.getValue());
            Single<WebIdentityCardData> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.f5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmocj(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Identity
        @NotNull
        public Single<List<WebIdentityLabel>> getLabels(@NotNull String type) {
            Intrinsics.checkNotNullParameter(type, "type");
            for (IdentityGetLabelsTypeDto identityGetLabelsTypeDto : IdentityGetLabelsTypeDto.values()) {
                if (Intrinsics.areEqual(identityGetLabelsTypeDto.getValue(), type)) {
                    Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityGetLabels(identityGetLabelsTypeDto)), null, 1, null);
                    final ipakvmocb ipakvmocbVar = new ipakvmocb((IdentityMapper) this.ipakvmoca.getValue());
                    Single<List<WebIdentityLabel>> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.j5
                        @Override // io.reactivex.rxjava3.functions.Function
                        public final Object apply(Object obj) {
                            return GeneratedSuperappApi.Identity.ipakvmock(ipakvmocbVar, obj);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(map, "map(...)");
                    return map;
                }
            }
            identityGetLabelsTypeDto = null;
            Single uiSingle$default2 = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(IdentityServiceKt.IdentityService().identityGetLabels(identityGetLabelsTypeDto)), null, 1, null);
            final Function1 ipakvmocbVar2 = new ipakvmocb((IdentityMapper) this.ipakvmoca.getValue());
            Single<List<WebIdentityLabel>> map2 = uiSingle$default2.map(new Function() { // from class: com.vk.superapp.api.contract.j5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Identity.ipakvmock(ipakvmocbVar2, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            return map2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityPhone ipakvmoca(Identity identity, WebIdentityLabel webIdentityLabel, IdentityPhoneResponseDto identityPhoneResponseDto) {
            IdentityMapper identityMapper = (IdentityMapper) identity.ipakvmoca.getValue();
            Intrinsics.checkNotNull(identityPhoneResponseDto);
            return identityMapper.mapToWebIdentityPhone(identityPhoneResponseDto, webIdentityLabel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final IdentityMapper ipakvmoca() {
            return new IdentityMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityAddress ipakvmoca(Function1 function1, Object obj) {
            return (WebIdentityAddress) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityAddress ipakvmoca(WebIdentityLabel webIdentityLabel, String str, String str2, int i10, int i11, IdentityAddressResponseDto identityAddressResponseDto) {
            return new WebIdentityAddress(webIdentityLabel, identityAddressResponseDto.getFullAddress(), str, str2, identityAddressResponseDto.getId(), i10, i11);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityEmail ipakvmoca(WebIdentityLabel webIdentityLabel, String str, BaseCreateResponseDto baseCreateResponseDto) {
            return new WebIdentityEmail(webIdentityLabel, str, baseCreateResponseDto.getId());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityPhone ipakvmoca(WebIdentityLabel webIdentityLabel, IdentityPhoneResponseDto identityPhoneResponseDto) {
            return new WebIdentityPhone(webIdentityLabel, identityPhoneResponseDto.getPhone(), identityPhoneResponseDto.getId());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebIdentityAddress ipakvmoca(WebIdentityAddress webIdentityAddress, IdentityAddressResponseDto identityAddressResponseDto) {
            return WebIdentityAddress.copy$default(webIdentityAddress, null, identityAddressResponseDto.getFullAddress(), null, null, 0, 0, 0, 125, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ=\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Messages;", "Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "<init>", "()V", "", "groupId", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "Lcom/vk/superapp/api/dto/app/AppIntent;", "intents", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/group/WebGroupMessageStatus;", "sendGroupsIsMessagesAllowed", "(JLcom/vk/dto/common/id/UserId;Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "appId", "", "key", "", "sendGroupsAllowMessages", "(JJLjava/util/List;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "peerId", TornadoSendRequest.FIELD_TEMPLATE, "Lio/reactivex/rxjava3/core/Single;", "sendMessagesToChat", "(JLjava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static class Messages implements SuperappApi.Messages {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.s5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Messages.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<MessagesIsMessagesFromGroupAllowedResponseDto, WebGroupMessageStatus> {
            ipakvmoca(MessagesMapper messagesMapper) {
                super(1, messagesMapper, MessagesMapper.class, "mapToWebGroupMessageStatus", "mapToWebGroupMessageStatus(Lcom/vk/api/generated/messages/dto/MessagesIsMessagesFromGroupAllowedResponseDto;)Lcom/vk/superapp/api/dto/group/WebGroupMessageStatus;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final WebGroupMessageStatus invoke(MessagesIsMessagesFromGroupAllowedResponseDto messagesIsMessagesFromGroupAllowedResponseDto) {
                MessagesIsMessagesFromGroupAllowedResponseDto p10 = messagesIsMessagesFromGroupAllowedResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((MessagesMapper) this.receiver).mapToWebGroupMessageStatus(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MessagesMapper ipakvmoca() {
            return new MessagesMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final WebGroupMessageStatus ipakvmocb(Function1 function1, Object obj) {
            return (WebGroupMessageStatus) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Messages
        @NotNull
        public Observable<Boolean> sendGroupsAllowMessages(long appId, long groupId, @NotNull List<? extends AppIntent> intents, @Nullable String key) {
            Intrinsics.checkNotNullParameter(intents, "intents");
            MessagesService MessagesService = MessagesServiceKt.MessagesService();
            UserId userId = new UserId(groupId);
            Integer numValueOf = Integer.valueOf((int) appId);
            AppIntent.Companion companion = AppIntent.INSTANCE;
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(MessagesService.DefaultImpls.messagesAllowMessagesFromGroup$default(MessagesService, userId, null, key, numValueOf, companion.getIntentNames(intents), companion.getSubscribeIds(intents), null, null, 192, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.n5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Messages.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.o5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Messages.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Messages
        @NotNull
        public Observable<WebGroupMessageStatus> sendGroupsIsMessagesAllowed(long groupId, @NotNull UserId userId, @NotNull List<? extends AppIntent> intents) {
            Intrinsics.checkNotNullParameter(userId, "userId");
            Intrinsics.checkNotNullParameter(intents, "intents");
            MessagesService MessagesService = MessagesServiceKt.MessagesService();
            UserId userId2 = new UserId(groupId);
            AppIntent.Companion companion = AppIntent.INSTANCE;
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(MessagesService.messagesIsMessagesFromGroupAllowed(userId2, userId, companion.getIntentNames(intents), companion.getSubscribeIds(intents))), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((MessagesMapper) this.ipakvmoca.getValue());
            Observable<WebGroupMessageStatus> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.p5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Messages.ipakvmocb(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Messages
        @NotNull
        public Single<Boolean> sendMessagesToChat(long peerId, @NotNull String template) {
            Intrinsics.checkNotNullParameter(template, "template");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(MessagesService.DefaultImpls.messagesSend$default(MessagesServiceKt.MessagesService(), null, 0, new UserId(peerId), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, template, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, -1048583, 2047, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.q5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Messages.ipakvmoca((MessagesSendResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.r5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Messages.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:8:0x000f  */
        public static final Boolean ipakvmoca(MessagesSendResponseDto messagesSendResponseDto) {
            boolean z10;
            Integer messageId = messagesSendResponseDto.getMessageId();
            if (messageId != null) {
                z10 = messageId.intValue() == 1;
            }
            return Boolean.valueOf(z10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Notification;", "Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "<init>", "()V", "sendAppsAllowNotifications", "Lio/reactivex/rxjava3/core/Observable;", "", "appId", "", "sendAppsDenyNotifications", "sendAppsIsNotificationsAllowed", "hideNotification", "Lio/reactivex/rxjava3/core/Single;", "query", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Notification implements SuperappApi.Notification {
        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Notification
        @NotNull
        public Single<Boolean> hideNotification(@Nullable String query) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(NotificationsService.DefaultImpls.notificationsHide$default(NotificationsServiceKt.NotificationsService(), query, null, null, 6, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.v5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.w5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Notification
        @NotNull
        public Observable<Boolean> sendAppsAllowNotifications(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsAllowNotifications((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.x5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.y5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Notification
        @NotNull
        public Observable<Boolean> sendAppsDenyNotifications(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsDenyNotifications((int) appId)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.z5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.a6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Notification
        @NotNull
        public Observable<Boolean> sendAppsIsNotificationsAllowed(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsService.DefaultImpls.appsIsNotificationsAllowed$default(AppsServiceKt.AppsService(), null, Integer.valueOf((int) appId), 1, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.t5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmoca((AppsIsNotificationsAllowedResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.u5
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Notification.ipakvmocd(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(AppsIsNotificationsAllowedResponseDto appsIsNotificationsAllowedResponseDto) {
            return Boolean.valueOf(appsIsNotificationsAllowedResponseDto.isAllowed());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.valueOf(baseBoolIntDto == BaseBoolIntDto.YES);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Orders;", "Lcom/vk/superapp/api/contract/SuperappApi$Orders;", "<init>", "()V", "getPersonalDiscount", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/personal/PersonalDiscount;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Orders implements SuperappApi.Orders {
        /* JADX INFO: Access modifiers changed from: private */
        public static final PersonalDiscount ipakvmoca(OrdersPersonalDiscountDto ordersPersonalDiscountDto) {
            PersonalDiscountMapper personalDiscountMapper = new PersonalDiscountMapper();
            Intrinsics.checkNotNull(ordersPersonalDiscountDto);
            return personalDiscountMapper.map(ordersPersonalDiscountDto);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Orders
        @NotNull
        public Single<PersonalDiscount> getPersonalDiscount() {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(OrdersServiceKt.OrdersService().ordersGetPersonalDiscount()), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.b6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Orders.ipakvmoca((OrdersPersonalDiscountDto) obj);
                }
            };
            Single<PersonalDiscount> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.c6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Orders.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final PersonalDiscount ipakvmoca(Function1 function1, Object obj) {
            return (PersonalDiscount) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Permission;", "Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "<init>", "()V", "", "appId", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/app/AppPermissions;", "sendAppsGetDevicePermissionsRequest", "(J)Lio/reactivex/rxjava3/core/Observable;", "", "name", "", "sendAppsSetDevicePermissionsRequest", "(JLjava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Permission implements SuperappApi.Permission {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.f6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Permission.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkPermissionMapper ipakvmoca() {
            return new VkPermissionMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Permission
        @NotNull
        public Observable<AppPermissions> sendAppsGetDevicePermissionsRequest(long appId) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsGetDevicePermissions((int) appId, SuperappApiCore.INSTANCE.getDeviceId())), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.g6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Permission.ipakvmoca(this.f51804a, (AppsGetDevicePermissionsResponseDto) obj);
                }
            };
            Observable<AppPermissions> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.h6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Permission.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Permission
        @NotNull
        public Observable<Boolean> sendAppsSetDevicePermissionsRequest(long appId, @NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AppsServiceKt.AppsService().appsSetDevicePermissions((int) appId, SuperappApiCore.INSTANCE.getDeviceId(), name, true)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.d6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Permission.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.e6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Permission.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppPermissions ipakvmoca(Permission permission, AppsGetDevicePermissionsResponseDto appsGetDevicePermissionsResponseDto) {
            VkPermissionMapper vkPermissionMapper = (VkPermissionMapper) permission.ipakvmoca.getValue();
            Intrinsics.checkNotNull(appsGetDevicePermissionsResponseDto);
            return vkPermissionMapper.mapDtoToAppPermissions(appsGetDevicePermissionsResponseDto);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AppPermissions ipakvmoca(Function1 function1, Object obj) {
            return (AppPermissions) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\r"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$QrWebToApp;", "Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "<init>", "()V", "setAuthCodeStatus", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/auth/dto/AuthSetAuthCodeStatusResponseDto;", "authCode", "", "terminateAuthCode", "Lcom/vk/api/generated/auth/dto/AuthTerminateAuthCodeResponseDto;", "getAuthCodeStatus", "Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class QrWebToApp implements SuperappApi.QrWebToApp {
        @Override // com.vk.superapp.api.contract.SuperappApi.QrWebToApp
        @NotNull
        public Single<AuthGetAuthCodeStatusResponseDto> getAuthCodeStatus(@NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authGetAuthCodeStatus(authCode)).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.QrWebToApp
        @NotNull
        public Single<AuthSetAuthCodeStatusResponseDto> setAuthCodeStatus(@NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authSetAuthCodeStatus(authCode)).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.QrWebToApp
        @NotNull
        public Single<AuthTerminateAuthCodeResponseDto> terminateAuthCode(@NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authTerminateAuthCode(authCode)).allowNoAuth().setAnonymous(true), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J?\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\f\u0010\rJ1\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\u00120\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Settings;", "Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "<init>", "()V", "", "externalCode", "vkExternalClient", "redirectUri", "service", "codeVerifier", "Lio/reactivex/rxjava3/core/Observable;", "", "sendActivateExternalOAuthService", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "authLabel", "isDeactivateAllAuthLabels", "sendDeactivateExternalOAuthService", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/rxjava3/core/Observable;", "", "sendGetOAuthServices", "()Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Settings implements SuperappApi.Settings {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.i6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Settings.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<SettingsOAuthServicesResponseDto, Map<String, ? extends Boolean>> {
            ipakvmoca(SettingsMapper settingsMapper) {
                super(1, settingsMapper, SettingsMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/settings/dto/SettingsOAuthServicesResponseDto;)Ljava/util/Map;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Map<String, ? extends Boolean> invoke(SettingsOAuthServicesResponseDto settingsOAuthServicesResponseDto) {
                SettingsOAuthServicesResponseDto p10 = settingsOAuthServicesResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((SettingsMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SettingsMapper ipakvmoca() {
            return new SettingsMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Map ipakvmocc(Function1 function1, Object obj) {
            return (Map) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Settings
        @NotNull
        public Observable<Boolean> sendActivateExternalOAuthService(@NotNull String externalCode, @NotNull String vkExternalClient, @NotNull String redirectUri, @NotNull String service, @Nullable String codeVerifier) {
            Intrinsics.checkNotNullParameter(externalCode, "externalCode");
            Intrinsics.checkNotNullParameter(vkExternalClient, "vkExternalClient");
            Intrinsics.checkNotNullParameter(redirectUri, "redirectUri");
            Intrinsics.checkNotNullParameter(service, "service");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(SettingsServiceKt.SettingsService().settingsActivateExternalOAuthService(externalCode, vkExternalClient, redirectUri, service, null, codeVerifier)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.j6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Settings.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.k6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Settings.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Settings
        @NotNull
        public Observable<Boolean> sendDeactivateExternalOAuthService(@NotNull String service, @Nullable String authLabel, @Nullable Boolean isDeactivateAllAuthLabels) {
            Intrinsics.checkNotNullParameter(service, "service");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(SettingsServiceKt.SettingsService().settingsDeactivateExternalOAuthService(service, authLabel, isDeactivateAllAuthLabels)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Settings.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.n6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Settings.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Settings
        @NotNull
        public Observable<Map<String, Boolean>> sendGetOAuthServices() {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(SettingsServiceKt.SettingsService().settingsGetOAuthServices()), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((SettingsMapper) this.ipakvmoca.getValue());
            Observable<Map<String, Boolean>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Settings.ipakvmocc(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00052\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH\u0016¨\u0006\u001d"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Stat;", "Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "<init>", "()V", "sendStatsTrackVisitorRequest", "Lio/reactivex/rxjava3/core/Observable;", "", "appId", "", "sessionUuid", "", "sessionDuration", "", "type", "Lcom/vk/api/generated/stats/dto/StatsTrackVisitorTypeDto;", "(JLjava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/stats/dto/StatsTrackVisitorTypeDto;)Lio/reactivex/rxjava3/core/Observable;", "sendStatsTrackEventsRequest", "", "events", "sendStatEventsAddRequest", "", "Lcom/google/gson/JsonObject;", "sendStatEventsAnonymouslyAddRequest", "sendStatSAKMobileEventsAddRequest", "sendStatSAKMobileEventsAnonymouslyAddRequest", "sendStatAddLibverifyEvent", "Lio/reactivex/rxjava3/core/Single;", PasskeyBeginResult.SID_KEY, "validateSession", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Stat\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1#2:3567\n*E\n"})
    public static final class Stat implements SuperappApi.Stat {
        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoce(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Object ipakvmocf(Function1 function1, Object obj) {
            return function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocg(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Single<Boolean> sendStatAddLibverifyEvent(@NotNull String sid, @NotNull String validateSession) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(validateSession, "validateSession");
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemService.DefaultImpls.ecosystemAddLibverifyEvent$default(EcosystemServiceKt.EcosystemService(), EcosystemAddLibverifyEventEventTypeDto.AUTH_PHONE_REQUESTED, sid, validateSession, null, null, 24, null))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmoca((BaseBoolIntDto) obj);
                }
            };
            Single<Boolean> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.b7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Boolean> sendStatEventsAddRequest(@NotNull List<JsonObject> events) {
            Intrinsics.checkNotNullParameter(events, "events");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(StatEventsServiceKt.StatEventsService().statEventsAdd(events, null));
            ApiCallExtKt.addRegistrationStatsFields(webApiRequest);
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmoca((StatEventsBaseResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.v6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Boolean> sendStatEventsAnonymouslyAddRequest(@NotNull List<JsonObject> events) {
            Intrinsics.checkNotNullParameter(events, "events");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(StatEventsServiceKt.StatEventsService().statEventsAddAnonymously(events))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.p6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocb((StatEventsBaseResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.q6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Boolean> sendStatSAKMobileEventsAddRequest(@NotNull List<JsonObject> events) {
            Intrinsics.checkNotNullParameter(events, "events");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(StatEventsService.DefaultImpls.statEventsAddSAKMobile$default(StatEventsServiceKt.StatEventsService(), events, null, 2, null));
            ApiCallExtKt.addRegistrationStatsFields(webApiRequest);
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.o6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocc((StatEventsBaseResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.t6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocd(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Boolean> sendStatSAKMobileEventsAnonymouslyAddRequest(@NotNull List<JsonObject> events) {
            Intrinsics.checkNotNullParameter(events, "events");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(StatEventsServiceKt.StatEventsService().statEventsAddSAKMobileAnonymously(events))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.r6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocd((StatEventsBaseResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.s6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmoce(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Object> sendStatsTrackEventsRequest(@NotNull String events, long appId) {
            Intrinsics.checkNotNullParameter(events, "events");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(StatsServiceKt.StatsService().statsTrackEvents(events, null));
            ApiCallExtKt.addRegistrationStatsFields(webApiRequest);
            webApiRequest.allowNoAuth();
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Object> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.x6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocf(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Stat
        @NotNull
        public Observable<Boolean> sendStatsTrackVisitorRequest(long appId, @NotNull String sessionUuid, @Nullable Integer sessionDuration, @NotNull StatsTrackVisitorTypeDto type) {
            Intrinsics.checkNotNullParameter(sessionUuid, "sessionUuid");
            Intrinsics.checkNotNullParameter(type, "type");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(StatsServiceKt.StatsService().statsTrackVisitor(Integer.valueOf((int) appId), sessionUuid, sessionDuration, type)).skipValidation(true), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.y6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.z6
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Stat.ipakvmocg(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(StatEventsBaseResponseDto statEventsBaseResponseDto) {
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(StatEventsBaseResponseDto statEventsBaseResponseDto) {
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocd(StatEventsBaseResponseDto statEventsBaseResponseDto) {
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseBoolIntDto baseBoolIntDto) {
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(StatEventsBaseResponseDto statEventsBaseResponseDto) {
            return Boolean.TRUE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0015"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Storage;", "Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "<init>", "()V", "sendStorageGet", "Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONArray;", UserMetadata.KEYDATA_FILENAME, "", "", "appId", "", "([Ljava/lang/String;J)Lio/reactivex/rxjava3/core/Observable;", "sendStorageGetKeys", "offset", "", "count", "sendStorageSet", "", "key", "value", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Storage implements SuperappApi.Storage {
        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONArray ipakvmoca(JSONObject jSONObject) {
            return jSONObject.getJSONArray("response");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONArray ipakvmocb(JSONObject jSONObject) {
            return jSONObject.getJSONArray("response");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocc(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Storage
        @NotNull
        public Observable<JSONArray> sendStorageGet(@NotNull String[] keys, long appId) {
            Intrinsics.checkNotNullParameter(keys, "keys");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(StorageServiceKt.StorageService().storageGet(null, ArraysKt.toList(keys), null, Integer.valueOf((int) appId))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.g7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmoca((JSONObject) obj);
                }
            };
            Observable<JSONArray> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.h7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Storage
        @NotNull
        public Observable<JSONArray> sendStorageGetKeys(long appId, int offset, int count) {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(StorageServiceKt.StorageService().storageGetKeys(null, Integer.valueOf((int) appId), Integer.valueOf(offset), Integer.valueOf(count))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.e7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmocb((JSONObject) obj);
                }
            };
            Observable<JSONArray> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.f7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Storage
        @NotNull
        public Observable<Boolean> sendStorageSet(@NotNull String key, @NotNull String value, long appId) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(StorageService.DefaultImpls.storageSet$default(StorageServiceKt.StorageService(), key, value, null, Integer.valueOf((int) appId), null, 16, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.c7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.d7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Storage.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONArray ipakvmoca(Function1 function1, Object obj) {
            return (JSONArray) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONArray ipakvmocb(Function1 function1, Object obj) {
            return (JSONArray) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Store;", "Lcom/vk/superapp/api/contract/SuperappApi$Store;", "<init>", "()V", "getFillBalanceUrl", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/store/FillBalanceUrl;", "hasInAppBilling", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Store implements SuperappApi.Store {
        /* JADX INFO: Access modifiers changed from: private */
        public static final FillBalanceUrl ipakvmoca(Object obj) {
            return new FillBalanceUrl(null, null, 3, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Store
        @NotNull
        public Single<FillBalanceUrl> getFillBalanceUrl(boolean hasInAppBilling) {
            Single<FillBalanceUrl> singleOnErrorReturn = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(StoreServiceKt.StoreService().storeGetReplenishBalanceLink(Boolean.valueOf(!hasInAppBilling))).skipValidation(true), null, 1, null).map(new Function() { // from class: com.vk.superapp.api.contract.i7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Store.ipakvmoca(obj);
                }
            }).onErrorReturn(new Function() { // from class: com.vk.superapp.api.contract.j7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Store.ipakvmoca((Throwable) obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(singleOnErrorReturn, "onErrorReturn(...)");
            return singleOnErrorReturn;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FillBalanceUrl ipakvmoca(Throwable th2) {
            FillBalanceUrlMapper fillBalanceUrlMapper = new FillBalanceUrlMapper();
            Intrinsics.checkNotNull(th2);
            return fillBalanceUrlMapper.map(th2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$SuperApp;", "Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "<init>", "()V", "", "uid", "parentUid", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/menu/BadgeInfo;", "sendSuperAppMarkBadgeAsClicked", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "trackCode", "Lio/reactivex/rxjava3/core/Single;", "", "sendCloseOnboardingPanel", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/menu/SuperAppAnimationConfig;", "sendGetSuperAppAnimation", "()Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SuperApp implements SuperappApi.SuperApp {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.o7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.SuperApp.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final CommonMapper ipakvmoca() {
            return new CommonMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BadgeInfo ipakvmocb(Function1 function1, Object obj) {
            return (BadgeInfo) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.SuperApp
        @NotNull
        public Single<Boolean> sendCloseOnboardingPanel(@NotNull String uid, @NotNull String trackCode) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            Intrinsics.checkNotNullParameter(trackCode, "trackCode");
            Single<Boolean> singleNever = Single.never();
            Intrinsics.checkNotNullExpressionValue(singleNever, "never(...)");
            return singleNever;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.SuperApp
        @NotNull
        public Single<SuperAppAnimationConfig> sendGetSuperAppAnimation() {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(SuperAppServiceKt.SuperAppService().superAppGetAnimations()), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.m7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.SuperApp.ipakvmoca(this.f51846a, (SuperAppGetAnimationsResponseDto) obj);
                }
            };
            Single<SuperAppAnimationConfig> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.n7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.SuperApp.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.SuperApp
        @NotNull
        public Observable<BadgeInfo> sendSuperAppMarkBadgeAsClicked(@NotNull String uid, @Nullable String parentUid) {
            Intrinsics.checkNotNullParameter(uid, "uid");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(SuperAppServiceKt.SuperAppService().superAppMarkBadgeAsClicked(uid, parentUid)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.k7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.SuperApp.ipakvmoca(this.f51833a, (SuperAppShowcaseMarkBadgeAsClickedDataDto) obj);
                }
            };
            Observable<BadgeInfo> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.SuperApp.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SuperAppAnimationConfig ipakvmoca(SuperApp superApp, SuperAppGetAnimationsResponseDto superAppGetAnimationsResponseDto) {
            CommonMapper commonMapper = (CommonMapper) superApp.ipakvmoca.getValue();
            Intrinsics.checkNotNull(superAppGetAnimationsResponseDto);
            return commonMapper.mapToAnimationConfig(superAppGetAnimationsResponseDto);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:10:0x0030  */
        public static final BadgeInfo ipakvmoca(SuperApp superApp, SuperAppShowcaseMarkBadgeAsClickedDataDto superAppShowcaseMarkBadgeAsClickedDataDto) {
            BadgeInfo badgeInfoMapBadgeInfo;
            if (superAppShowcaseMarkBadgeAsClickedDataDto instanceof SuperAppShowcaseMarkBadgeAsClickedDataDto.SuperAppShowcaseMarkBadgeAsClickedShowcaseMenuDataDto) {
                SuperAppBadgeInfoDto badgeInfo = ((SuperAppShowcaseMarkBadgeAsClickedDataDto.SuperAppShowcaseMarkBadgeAsClickedShowcaseMenuDataDto) superAppShowcaseMarkBadgeAsClickedDataDto).getBadgeInfo();
                if (badgeInfo != null) {
                    badgeInfoMapBadgeInfo = ((CommonMapper) superApp.ipakvmoca.getValue()).mapToBadgeInfo(badgeInfo);
                } else {
                    badgeInfoMapBadgeInfo = null;
                }
            } else if (superAppShowcaseMarkBadgeAsClickedDataDto instanceof SuperAppShowcaseMarkBadgeAsClickedDataDto.SuperAppShowcaseMarkBadgeAsClickedServicesMenuDataDto) {
                badgeInfoMapBadgeInfo = ((CommonMapper) superApp.ipakvmoca.getValue()).mapBadgeInfo(((SuperAppShowcaseMarkBadgeAsClickedDataDto.SuperAppShowcaseMarkBadgeAsClickedServicesMenuDataDto) superAppShowcaseMarkBadgeAsClickedDataDto).getBadge());
            } else {
                badgeInfoMapBadgeInfo = null;
            }
            return badgeInfoMapBadgeInfo == null ? BadgeInfo.INSTANCE.getEMPTY() : badgeInfoMapBadgeInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SuperAppAnimationConfig ipakvmoca(Function1 function1, Object obj) {
            return (SuperAppAnimationConfig) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Translations;", "Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "<init>", "()V", "translate", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/translations/dto/TranslationsTranslateResponseDto;", "texts", "", "", "translateTo", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Translations implements SuperappApi.Translations {
        @Override // com.vk.superapp.api.contract.SuperappApi.Translations
        @NotNull
        public Single<TranslationsTranslateResponseDto> translate(@NotNull List<String> texts, @NotNull String translateTo) {
            Intrinsics.checkNotNullParameter(texts, "texts");
            Intrinsics.checkNotNullParameter(translateTo, "translateTo");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(TranslationsServiceKt.TranslationsService().translationsTranslate(texts, translateTo)), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\rJw\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001d0\t2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ+\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016¢\u0006\u0004\b!\u0010\fJo\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00060(2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u00062\b\u0010%\u001a\u0004\u0018\u00010\u000e2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00062\b\u0010'\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Users;", "Lcom/vk/superapp/api/contract/SuperappApi$Users;", "<init>", "()V", "", "appId", "", "Lcom/vk/dto/common/id/UserId;", "userIds", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/user/WebUserShortInfo;", "sendGetUsersShortInfo", "(JLjava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "(Ljava/util/List;)Lio/reactivex/rxjava3/core/Observable;", "", CommonConstant.KEY_ACCESS_TOKEN, "query", "", "limit", "offset", "countryId", "cityId", "Lcom/vk/superapp/core/api/models/VkGender;", "gender", "ageFrom", "ageTo", "Lcom/vk/superapp/api/VkRelation;", "relationsStatus", "screenRef", "Lcom/vk/superapp/api/dto/common/VkList;", "sendSearchRestoreUsers", "(Ljava/lang/String;Ljava/lang/String;IIIILcom/vk/superapp/core/api/models/VkGender;IILcom/vk/superapp/api/VkRelation;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONObject;", "sendGetUserMyInfo", "domains", "Lcom/vk/api/generated/users/dto/UsersFieldsDto;", "fields", "nameCase", "accessKeys", "fromGroupId", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/users/dto/UsersUserFullDto;", "usersGet", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Lcom/vk/dto/common/id/UserId;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Users\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,3566:1\n1#2:3567\n774#3:3568\n865#3,2:3569\n1563#3:3571\n1634#3,3:3572\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$Users\n*L\n1595#1:3568\n1595#1:3569,2\n1595#1:3571\n1595#1:3572,3\n*E\n"})
    public static class Users implements SuperappApi.Users {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.r7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Users.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[VkGender.values().length];
                try {
                    iArr[VkGender.UNDEFINED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[VkGender.FEMALE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[VkGender.MALE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[VkRelation.values().length];
                try {
                    iArr2[VkRelation.NONE.ordinal()] = 1;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr2[VkRelation.NOT_MARRIED.ordinal()] = 2;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr2[VkRelation.MEETS.ordinal()] = 3;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr2[VkRelation.ENGAGED.ordinal()] = 4;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr2[VkRelation.MARRIED.ordinal()] = 5;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr2[VkRelation.COMPLICATED.ordinal()] = 6;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr2[VkRelation.ACTIVELY_LOOKING.ordinal()] = 7;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr2[VkRelation.IN_LOVE.ordinal()] = 8;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr2[VkRelation.CIVIL_MARRIAGE.ordinal()] = 9;
                } catch (NoSuchFieldError unused12) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<List<? extends UsersUserFullDto>, List<? extends WebUserShortInfo>> {
            ipakvmoca(UsersMapper usersMapper) {
                super(1, usersMapper, UsersMapper.class, BlockParser.MAP_TYPE, "map(Ljava/util/List;)Ljava/util/List;", 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final List<? extends WebUserShortInfo> invoke(List<? extends UsersUserFullDto> list) {
                List<? extends UsersUserFullDto> p10 = list;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((UsersMapper) this.receiver).map((List<UsersUserFullDto>) p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<UsersSearchResponseDto, VkList<? extends WebUserShortInfo>> {
            ipakvmocb(UsersMapper usersMapper) {
                super(1, usersMapper, UsersMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/users/dto/UsersSearchResponseDto;)Lcom/vk/superapp/api/dto/common/VkList;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkList<? extends WebUserShortInfo> invoke(UsersSearchResponseDto usersSearchResponseDto) {
                UsersSearchResponseDto p10 = usersSearchResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((UsersMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final UsersMapper ipakvmoca() {
            return new UsersMapper(new ImageMapper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkList ipakvmocb(Function1 function1, Object obj) {
            return (VkList) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Users
        @NotNull
        public Observable<JSONObject> sendGetUserMyInfo(long appId, @NotNull List<Long> userIds) {
            Intrinsics.checkNotNullParameter(userIds, "userIds");
            List listListOf = CollectionsKt.listOf((Object[]) new UsersFieldsDto[]{UsersFieldsDto.CITY, UsersFieldsDto.SEX, UsersFieldsDto.COUNTRY, UsersFieldsDto.PHOTO_BASE, UsersFieldsDto.TIMEZONE, UsersFieldsDto.BDATE, UsersFieldsDto.BDATE_VISIBILITY});
            ArrayList arrayList = new ArrayList();
            for (Object obj : userIds) {
                if (((Number) obj).longValue() != 0) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            int size = arrayList.size();
            int i10 = 0;
            while (i10 < size) {
                Object obj2 = arrayList.get(i10);
                i10++;
                arrayList2.add(new UserId(((Number) obj2).longValue()));
            }
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(UsersService.DefaultImpls.usersGet$default(UsersServiceKt.UsersService(), arrayList2.isEmpty() ? null : arrayList2, null, listListOf, null, null, null, 58, null)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Users
        @NotNull
        public Observable<List<WebUserShortInfo>> sendGetUsersShortInfo(long appId, @NotNull List<UserId> userIds) {
            Intrinsics.checkNotNullParameter(userIds, "userIds");
            return sendGetUsersShortInfo(userIds);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Users
        @NotNull
        public Observable<VkList<WebUserShortInfo>> sendSearchRestoreUsers(@NotNull String accessToken, @Nullable String query, int limit, int offset, int countryId, int cityId, @NotNull VkGender gender, int ageFrom, int ageTo, @NotNull VkRelation relationsStatus, @Nullable String screenRef) {
            UsersSearchSexDto usersSearchSexDto;
            UsersSearchStatusDto usersSearchStatusDto;
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(gender, "gender");
            Intrinsics.checkNotNullParameter(relationsStatus, "relationsStatus");
            int i10 = WhenMappings.$EnumSwitchMapping$0[gender.ordinal()];
            if (i10 == 1) {
                usersSearchSexDto = UsersSearchSexDto.ANY;
            } else if (i10 == 2) {
                usersSearchSexDto = UsersSearchSexDto.FEMALE;
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                usersSearchSexDto = UsersSearchSexDto.MALE;
            }
            UsersSearchSexDto usersSearchSexDto2 = usersSearchSexDto;
            switch (WhenMappings.$EnumSwitchMapping$1[relationsStatus.ordinal()]) {
                case 1:
                    usersSearchStatusDto = UsersSearchStatusDto.NOT_SPECIFIED;
                    break;
                case 2:
                    usersSearchStatusDto = UsersSearchStatusDto.NOT_MARRIED;
                    break;
                case 3:
                    usersSearchStatusDto = UsersSearchStatusDto.RELATIONSHIP;
                    break;
                case 4:
                    usersSearchStatusDto = UsersSearchStatusDto.ENGAGED;
                    break;
                case 5:
                    usersSearchStatusDto = UsersSearchStatusDto.MARRIED;
                    break;
                case 6:
                    usersSearchStatusDto = UsersSearchStatusDto.COMPLICATED;
                    break;
                case 7:
                    usersSearchStatusDto = UsersSearchStatusDto.ACTIVELY_SEARCHING;
                    break;
                case 8:
                    usersSearchStatusDto = UsersSearchStatusDto.IN_LOVE;
                    break;
                case 9:
                    usersSearchStatusDto = UsersSearchStatusDto.NOT_SPECIFIED;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            UsersSearchStatusDto usersSearchStatusDto2 = usersSearchStatusDto;
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(com.vk.superapp.api.generated.users.UsersService.DefaultImpls.usersSearch$default(UsersServiceKt.UsersService(), query, null, Integer.valueOf(offset), Integer.valueOf(limit), CollectionsKt.listOf((Object[]) new UsersFieldsDto[]{UsersFieldsDto.FIRST_NAME_NOM, UsersFieldsDto.LAST_NAME_NOM, UsersFieldsDto.SEX, UsersFieldsDto.CITY, UsersFieldsDto.PHOTO, UsersFieldsDto.PHOTO_BASE}), Integer.valueOf(cityId), null, Integer.valueOf(countryId), null, null, null, null, null, null, null, usersSearchSexDto2, usersSearchStatusDto2, Integer.valueOf(ageFrom), Integer.valueOf(ageTo), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, screenRef, null, null, null, null, null, -491710, 251, null));
            webApiRequest.overrideAuth(accessToken, null);
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb((UsersMapper) this.ipakvmoca.getValue());
            Observable<VkList<WebUserShortInfo>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.p7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Users.ipakvmocb(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Users
        @NotNull
        public Single<List<UsersUserFullDto>> usersGet(@Nullable List<UserId> userIds, @Nullable List<UserId> domains, @Nullable List<? extends UsersFieldsDto> fields, @Nullable String nameCase, @Nullable List<String> accessKeys, @Nullable UserId fromGroupId) {
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(UsersServiceKt.UsersService().usersGet(userIds, domains, fields, nameCase, accessKeys, fromGroupId)), null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Users
        @NotNull
        public Observable<List<WebUserShortInfo>> sendGetUsersShortInfo(@NotNull List<UserId> userIds) {
            Intrinsics.checkNotNullParameter(userIds, "userIds");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(UsersService.DefaultImpls.usersGet$default(UsersServiceKt.UsersService(), userIds, null, CollectionsKt.listOf((Object[]) new UsersFieldsDto[]{UsersFieldsDto.FIRST_NAME_NOM, UsersFieldsDto.LAST_NAME_NOM, UsersFieldsDto.SEX, UsersFieldsDto.CITY, UsersFieldsDto.PHOTO, UsersFieldsDto.PHOTO_BASE}), null, null, null, 58, null)), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca((UsersMapper) this.ipakvmoca.getValue());
            Observable<List<WebUserShortInfo>> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.q7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Users.ipakvmoca(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0016J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J0\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00052\u0006\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006 "}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Verification;", "Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "<init>", "()V", "getVerificationMethods", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetVerificationMethodsResponseDto;", PasskeyBeginResult.SID_KEY, "", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "sendOtpPush", "Lcom/vk/api/generated/ecosystem/dto/EcosystemSendOtpResponseDto;", "sendOtpSms", "sendOtpEmail", "sendMaxMessengerVerification", "getMaxSessionStatus", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetMaxSessionStatusResponseDto;", "maxMessengerHash", "sendMaxOtpCode", "sendOtpCallReset", "sendOtpOfficialMessenger", "checkOtp", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckOtpResponseDto;", "code", "verificationMethod", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckOtpVerificationMethodDto;", "checkPhoneReuse", "Lcom/vk/api/generated/ecosystem/dto/EcosystemCheckPhoneReuseResponseDto;", "login", "superAppToken", "getValidationStatus", "Lcom/vk/api/generated/ecosystem/dto/EcosystemGetValidationStatusResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Verification implements SuperappApi.Verification {
        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemCheckOtpResponseDto> checkOtp(@NotNull String sid, @NotNull String code, @NotNull String deviceId, @Nullable EcosystemCheckOtpVerificationMethodDto verificationMethod) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemService.DefaultImpls.ecosystemCheckOtp$default(EcosystemServiceKt.EcosystemService(), sid, code, deviceId, verificationMethod, null, 16, null))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemCheckPhoneReuseResponseDto> checkPhoneReuse(@NotNull String login, @Nullable String superAppToken) {
            Intrinsics.checkNotNullParameter(login, "login");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemService.DefaultImpls.ecosystemCheckPhoneReuse$default(EcosystemServiceKt.EcosystemService(), login, superAppToken, null, 4, null))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemGetMaxSessionStatusResponseDto> getMaxSessionStatus(@NotNull String sid, @NotNull String deviceId, @NotNull String maxMessengerHash) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(maxMessengerHash, "maxMessengerHash");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemGetMaxSessionStatus(sid, maxMessengerHash, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemGetValidationStatusResponseDto> getValidationStatus(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemGetValidationStatus(sid, SuperappApiCore.INSTANCE.getDeviceId()))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemGetVerificationMethodsResponseDto> getVerificationMethods(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemService.DefaultImpls.ecosystemGetVerificationMethods$default(EcosystemServiceKt.EcosystemService(), sid, deviceId, null, 4, null))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendMaxMessengerVerification(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpMax(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendMaxOtpCode(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpMaxCode(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendOtpCallReset(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpCallReset(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendOtpEmail(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpEmail(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendOtpOfficialMessenger(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpOfficialMessenger(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendOtpPush(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpPush(sid, deviceId))), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Verification
        @NotNull
        public Single<EcosystemSendOtpResponseDto> sendOtpSms(@NotNull String sid, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(EcosystemServiceKt.EcosystemService().ecosystemSendOtpSms(sid, deviceId))), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\u0016J \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00060\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0016JR\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00052\u0006\u0010\u0016\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\f2\b\u0010 \u001a\u0004\u0018\u00010\fH\u0016J0\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00052\b\u0010#\u001a\u0004\u0018\u00010\f2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\b\u0010%\u001a\u0004\u0018\u00010\fH\u0016J \u0010&\u001a\b\u0012\u0004\u0012\u00020'0\t2\u0006\u0010(\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J%\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00052\u0006\u0010+\u001a\u00020,2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010-J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016Jh\u00100\u001a\b\u0012\u0004\u0012\u0002010\t2\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\f2\u0006\u00105\u001a\u00020,2\b\u00106\u001a\u0004\u0018\u00010\f2\u0006\u00107\u001a\u00020,2\b\u0010(\u001a\u0004\u0018\u00010\f2\u0006\u00108\u001a\u00020,2\u0006\u00109\u001a\u00020,2\b\u0010:\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J\u0016\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00052\u0006\u0010=\u001a\u00020\fH\u0016J\"\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00060\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u0006H\u0016J\u0014\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00060\tH\u0016J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00052\u0006\u0010\u0016\u001a\u00020\u001aH\u0016J\u0092\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\t2\b\u0010F\u001a\u0004\u0018\u00010\f2\b\u0010G\u001a\u0004\u0018\u00010\f2\b\u0010H\u001a\u0004\u0018\u00010\f2\u0006\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010\f2\b\u0010L\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010M\u001a\u0004\u0018\u00010\f2\u0006\u0010N\u001a\u00020,2\b\u0010O\u001a\u0004\u0018\u00010\f2\b\u0010P\u001a\u0004\u0018\u00010\f2\u0006\u0010Q\u001a\u00020,2\b\u0010R\u001a\u0004\u0018\u00010\f2\b\u0010S\u001a\u0004\u0018\u00010\fH\u0016J`\u0010T\u001a\b\u0012\u0004\u0012\u00020U0\t2\b\u0010L\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010V\u001a\u0004\u0018\u00010\f2\b\u0010W\u001a\u0004\u0018\u00010\f2\b\u0010X\u001a\u0004\u0018\u00010\f2\u0006\u0010Y\u001a\u00020,2\u0006\u0010Q\u001a\u00020,2\u0006\u0010Z\u001a\u00020,2\b\u0010[\u001a\u0004\u0018\u00010\fH\u0016J\u0016\u0010\\\u001a\b\u0012\u0004\u0012\u00020,0\t2\u0006\u0010\u0013\u001a\u00020\fH\u0016Jl\u0010]\u001a\b\u0012\u0004\u0012\u00020^0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010L\u001a\u0004\u0018\u00010\f2\u0006\u0010_\u001a\u00020,2\u0006\u00105\u001a\u00020,2\u0006\u0010Y\u001a\u00020,2\u0006\u0010`\u001a\u00020,2\u0006\u0010a\u001a\u00020,2\u0006\u0010b\u001a\u00020,2\u0006\u0010c\u001a\u00020,2\b\u0010d\u001a\u0004\u0018\u00010\f2\u0006\u0010e\u001a\u00020,H\u0016J&\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010L\u001a\u00020\f2\u0006\u0010Y\u001a\u00020,H\u0016J:\u0010h\u001a\b\u0012\u0004\u0012\u0002010\t2\u0006\u00102\u001a\u0002032\u0006\u0010i\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010(\u001a\u0004\u0018\u00010\fH\u0016J.\u0010j\u001a\b\u0012\u0004\u0012\u0002010\t2\u0006\u0010k\u001a\u00020\f2\u0006\u0010M\u001a\u00020\f2\u0006\u0010l\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u001aH\u0016JB\u0010m\u001a\b\u0012\u0004\u0012\u00020n0\t2\u0006\u0010i\u001a\u00020\f2\u0006\u0010M\u001a\u00020\f2\u0006\u0010o\u001a\u00020\f2\f\u0010p\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\f\u0010q\u001a\b\u0012\u0004\u0012\u00020\f0\u0006H\u0016J8\u0010r\u001a\b\u0012\u0004\u0012\u0002010\t2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010s\u001a\u00020\f2\u0006\u0010t\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\b\u0010 \u001a\u0004\u0018\u00010\fH\u0016J\u001e\u0010u\u001a\b\u0012\u0004\u0012\u00020v0\t2\u0006\u0010w\u001a\u00020\f2\u0006\u0010x\u001a\u00020\fH\u0016J&\u0010y\u001a\b\u0012\u0004\u0012\u00020v0\t2\u0006\u0010z\u001a\u00020\f2\u0006\u0010{\u001a\u00020\f2\u0006\u0010|\u001a\u00020\fH\u0016J\u001f\u0010}\u001a\b\u0012\u0004\u0012\u00020~0\t2\u0006\u0010\u007f\u001a\u00020,2\u0007\u0010\u0080\u0001\u001a\u00020\fH\u0016J\u0010\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0082\u00010\tH\u0016J\u0018\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J!\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u0002010\t2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010S\u001a\u0004\u0018\u00010\fH\u0016J*\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010\t2\u0007\u0010\u0088\u0001\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0007\u0010\u0089\u0001\u001a\u00020,H\u0016J,\u0010\u008a\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010\t2\u0007\u0010\u008c\u0001\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0007\u0010\u008d\u0001\u001a\u00020\fH\u0016Jo\u0010\u008e\u0001\u001a\t\u0012\u0005\u0012\u00030\u008f\u00010\t2\u0007\u0010\u008c\u0001\u001a\u00020\f2\u0007\u0010\u0090\u0001\u001a\u00020,2\b\u00104\u001a\u0004\u0018\u00010\f2\r\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u000e\u0010\u0092\u0001\u001a\t\u0012\u0005\u0012\u00030\u0093\u00010\u00062\b\u0010d\u001a\u0004\u0018\u00010\f2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J*\u0010\u0094\u0001\u001a\t\u0012\u0005\u0012\u00030\u0095\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010P\u001a\u0004\u0018\u00010\f2\u0006\u0010Y\u001a\u00020,H\u0016J'\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020U0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010V\u001a\u00020\f2\u0006\u0010Y\u001a\u00020,H\u0016J\u0018\u0010\u0097\u0001\u001a\t\u0012\u0005\u0012\u00030\u0098\u00010\u00052\u0006\u0010X\u001a\u00020\fH\u0016J\u0018\u0010\u0099\u0001\u001a\t\u0012\u0005\u0012\u00030\u009a\u00010\t2\u0006\u0010X\u001a\u00020\fH\u0016J?\u0010\u009b\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u001a2\b\u0010i\u001a\u0004\u0018\u00010\f2\b\u0010o\u001a\u0004\u0018\u00010\f2\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\f2\u0006\u0010Y\u001a\u00020,H\u0016J9\u0010\u009e\u0001\u001a\t\u0012\u0005\u0012\u00030\u009f\u00010\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u00020\u001a2\u0006\u00106\u001a\u00020\fH\u0016J.\u0010£\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030¤\u00010\u00060\t2\u000e\u0010¥\u0001\u001a\t\u0012\u0005\u0012\u00030¤\u00010\u00062\u0006\u0010d\u001a\u00020\fH\u0016J\"\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030§\u00010\u00052\u0010\u0010¥\u0001\u001a\u000b\u0012\u0005\u0012\u00030¤\u0001\u0018\u00010\u0006H\u0016J$\u0010¨\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010\u00052\b\u0010i\u001a\u0004\u0018\u00010\f2\b\u0010#\u001a\u0004\u0018\u00010\fH\u0016¨\u0006ª\u0001"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkAuth;", "Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "<init>", "()V", "getSilentAuthProviders", "Lio/reactivex/rxjava3/core/Single;", "", "Lcom/vk/superapp/api/dto/auth/silentauthprovider/AuthSilentAuthProvider;", "authOnSuccessValidation", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/auth/dto/AuthOnSuccessValidationResponseDto;", PasskeyBeginResult.SID_KEY, "", "maxMessengerHash", "authMailOnSuccessValidation", "getExchangeToken", "Lcom/vk/api/generated/auth/dto/AuthGetExchangeTokenResponseDto;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/dto/common/id/UserId;", CommonConstant.KEY_ACCESS_TOKEN, "getCredentialsForApp", "Lcom/vk/superapp/api/dto/auth/appcredentials/VkAuthAppCredentials;", "appId", "", "authGetCredentialsForServiceMulti", "Lcom/vk/superapp/api/dto/auth/serviceauthmulti/AuthGetCredentialsForServiceMultiResponseModel;", "", "packageValue", "timestamp", "digestHash", "exchangeTokens", "clientDeviceId", "clientExternalDeviceId", "getAutologinCredentials", "Lcom/vk/superapp/api/dto/auth/autologin/VkAuthAutologinCredentials;", SilentAuthInfo.KEY_UUID, "silentTokens", "sessionId", "externalFlowOut", "Lcom/vk/api/generated/auth/dto/AuthExternalFlowOutResponseDto;", "whiteLabelFlowInputSat", "validatePhoneCheck", "Lcom/vk/superapp/api/dto/auth/validatephonecheck/AuthValidatePhoneCheckResponse;", "isAuth", "", "(ZLjava/lang/Long;)Lio/reactivex/rxjava3/core/Single;", "validatePhoneCheckSkip", "Lcom/vk/superapp/api/internal/requests/app/ConfirmResult;", "auth", "Lcom/vk/auth/api/models/AuthResult;", "authState", "Lcom/vk/superapp/api/states/VkAuthState;", "trustedHash", "libverifySupport", com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "receiveCookiesSupport", "fromBackup", "deviceTrustedHashSupported", "wereAction", "getExchangeTokenInfo", "Lcom/vk/superapp/api/dto/auth/exchangetokeninfo/VkAuthExchangeTokenInfo;", "exchangeToken", "getExchangeTokensInfo", "Lcom/vk/api/generated/auth/dto/AuthExchangeTokenInfoDto;", "getAppScopes", "Lcom/vk/superapp/api/dto/auth/VkAuthAppScope;", "getVkConnectConfig", "Lcom/vk/superapp/api/dto/auth/VkConnectRemoteConfig;", "signUp", "Lcom/vk/superapp/api/dto/auth/VkAuthSignUpResult;", "firstName", "lastName", "fullName", "gender", "Lcom/vk/superapp/core/api/models/VkGender;", "birthday", "phone", "password", "extendedAuth", "profileType", "email", "canSkipPassword", "inviteHash", "validateSession", "confirmPhone", "Lcom/vk/superapp/api/dto/auth/validatephoneconfirm/VkAuthConfirmResponse;", "code", SessionParamsProviderImpl.PARAM_SESSION_ID, "token", "forceRemoveAccessToken", "isCodeAutocomplete", "verificationType", "logout", "validatePhone", "Lcom/vk/superapp/api/dto/auth/VkAuthValidatePhoneResult;", "voice", "disablePartial", "allowPush", "allowEmail", "allowPasskey", "superAppToken", "allowSmsInbox", "getValidatePhoneInfo", "Lcom/vk/superapp/api/dto/auth/VkAuthValidatePhoneInfo;", "checkSilentToken", "silentToken", "extendPartialToken", "partialToken", "extendHash", "extendSilentToken", "Lcom/vk/superapp/api/dto/auth/VkAuthExtendedSilentToken;", "silentTokenUuid", "providedTokens", "providedUuids", "extendProvidedToken", "providedHash", "providedUuid", "getSilentTokenByExternalToken", "Lcom/vk/superapp/api/dto/odnoklassniki/OkFlowSilentTokenResponse;", "externalToken", "clientMetadata", "getSilentTokenByExternalCredentials", "externalLogin", "externalPassword", "externalData", "getEsiaSignature", "Lcom/vk/superapp/api/dto/auth/VkEsiaSignature;", "isVerificationFlow", "externalClientId", "getHashes", "Lcom/vk/superapp/api/dto/auth/VkAuthHashes;", "passkeyBegin", "Lcom/vk/superapp/api/dto/auth/PasskeyBeginResult;", "authByAccessToken", "allowAuthByQrCode", "Lcom/vk/superapp/api/dto/qr/QrInfoResponse;", "authCode", "isInternalCamera", "validateLogin", "Lcom/vk/superapp/api/dto/auth/validatelogin/VkAuthValidateLoginResponse;", "login", "source", "validateAccount", "Lcom/vk/superapp/api/dto/auth/validateaccount/VkAuthValidateAccountResponse;", "forcePassword", "trustedHashes", "supportedWays", "Lcom/vk/superapp/api/dto/auth/AuthSupportedWay;", "validateEmail", "Lcom/vk/api/generated/auth/dto/AuthValidateEmailResponseDto;", "validateEmailConfirm", "validateSuperappToken", "Lcom/vk/superapp/api/dto/auth/VkAuthValidateSuperappTokenResponse;", "getUserInfoByToken", "Lcom/vk/superapp/api/dto/auth/GetUserInfoByPhone;", "getContinuationForService", "Lcom/vk/superapp/api/dto/auth/VkAuthGetContinuationForServiceResponse;", "phoneValidationSid", "refreshTokens", "Lcom/vk/api/generated/auth/dto/AuthRefreshTokensResponseDto;", "initiator", "Lcom/vk/superapp/api/internal/oauthrequests/AuthByExchangeTokenInitiator;", "activeIndex", "filterSilentTokens", "Lcom/vk/api/generated/auth/dto/AuthSilentTokenShortDto;", "silentTokensShort", "checkBindExtOAuth", "Lcom/vk/api/generated/auth/dto/AuthCheckBindExtOAuthResponseDto;", "bindExtOAuth", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$VkAuth\n+ 2 KotlinCommonExt.kt\ncom/vk/core/extensions/KotlinCommonExtKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n9#2,2:3567\n1617#3,9:3569\n1869#3:3578\n1870#3:3580\n1626#3:3581\n1563#3:3583\n1634#3,3:3584\n1563#3:3587\n1634#3,3:3588\n1563#3:3591\n1634#3,3:3592\n1#4:3579\n1#4:3582\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$VkAuth\n*L\n2058#1:3567,2\n2504#1:3569,9\n2504#1:3578\n2504#1:3580\n2504#1:3581\n2607#1:3583\n2607#1:3584,3\n2624#1:3587\n2624#1:3588,3\n2025#1:3591\n2025#1:3592,3\n2504#1:3579\n*E\n"})
    public static final class VkAuth implements SuperappApi.VkAuth {

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<AuthGetCredentialsForServiceMultiResponseDto, AuthGetCredentialsForServiceMultiResponseModel> {
            public static final ipakvmoca ipakvmoca = new ipakvmoca();

            ipakvmoca() {
                super(1, AuthGetCredentialsForServiceMultiMappersKt.class, "toDomainModel", "toDomainModel(Lcom/vk/api/generated/auth/dto/AuthGetCredentialsForServiceMultiResponseDto;)Lcom/vk/superapp/api/dto/auth/serviceauthmulti/AuthGetCredentialsForServiceMultiResponseModel;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AuthGetCredentialsForServiceMultiResponseModel invoke(AuthGetCredentialsForServiceMultiResponseDto authGetCredentialsForServiceMultiResponseDto) {
                AuthGetCredentialsForServiceMultiResponseDto p10 = authGetCredentialsForServiceMultiResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return AuthGetCredentialsForServiceMultiMappersKt.toDomainModel(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<AuthValidatePhoneConfirmResponseDto, VkAuthConfirmResponse> {
            public static final ipakvmocb ipakvmoca = new ipakvmocb();

            ipakvmocb() {
                super(1, VkAuthConfirmResponseKt.class, "toDomain", "toDomain(Lcom/vk/api/generated/auth/dto/AuthValidatePhoneConfirmResponseDto;)Lcom/vk/superapp/api/dto/auth/validatephoneconfirm/VkAuthConfirmResponse;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkAuthConfirmResponse invoke(AuthValidatePhoneConfirmResponseDto authValidatePhoneConfirmResponseDto) {
                AuthValidatePhoneConfirmResponseDto p10 = authValidatePhoneConfirmResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return VkAuthConfirmResponseKt.toDomain(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocc extends FunctionReferenceImpl implements Function1<AuthGetAutologinCredentialsResponseDto, VkAuthAutologinCredentials> {
            public static final ipakvmocc ipakvmoca = new ipakvmocc();

            ipakvmocc() {
                super(1, AuthGetAutologinCredentialsResponseMappersKt.class, "toDomainModel", "toDomainModel(Lcom/vk/api/generated/auth/dto/AuthGetAutologinCredentialsResponseDto;)Lcom/vk/superapp/api/dto/auth/autologin/VkAuthAutologinCredentials;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkAuthAutologinCredentials invoke(AuthGetAutologinCredentialsResponseDto authGetAutologinCredentialsResponseDto) {
                AuthGetAutologinCredentialsResponseDto p10 = authGetAutologinCredentialsResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return AuthGetAutologinCredentialsResponseMappersKt.toDomainModel(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocd extends FunctionReferenceImpl implements Function1<AuthGetSilentTokensResponseDto, List<? extends VkAuthAppCredentials>> {
            public static final ipakvmocd ipakvmoca = new ipakvmocd();

            ipakvmocd() {
                super(1, VkAuthAppCredentialsKt.class, "toDomainModel", "toDomainModel(Lcom/vk/api/generated/auth/dto/AuthGetSilentTokensResponseDto;)Ljava/util/List;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<? extends VkAuthAppCredentials> invoke(AuthGetSilentTokensResponseDto authGetSilentTokensResponseDto) {
                AuthGetSilentTokensResponseDto p10 = authGetSilentTokensResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return VkAuthAppCredentialsKt.toDomainModel(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmoce extends FunctionReferenceImpl implements Function1<AuthGetExchangeTokenInfoResponseDto, VkAuthExchangeTokenInfo> {
            public static final ipakvmoce ipakvmoca = new ipakvmoce();

            ipakvmoce() {
                super(1, VkAuthExchangeTokenInfoKt.class, "toDomain", "toDomain(Lcom/vk/api/generated/auth/dto/AuthGetExchangeTokenInfoResponseDto;)Lcom/vk/superapp/api/dto/auth/exchangetokeninfo/VkAuthExchangeTokenInfo;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkAuthExchangeTokenInfo invoke(AuthGetExchangeTokenInfoResponseDto authGetExchangeTokenInfoResponseDto) {
                AuthGetExchangeTokenInfoResponseDto p10 = authGetExchangeTokenInfoResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return VkAuthExchangeTokenInfoKt.toDomain(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocf extends FunctionReferenceImpl implements Function1<AuthValidateAccountResponseDto, VkAuthValidateAccountResponse> {
            public static final ipakvmocf ipakvmoca = new ipakvmocf();

            ipakvmocf() {
                super(1, VkAuthValidateAccountResponseKt.class, "toDomain", "toDomain(Lcom/vk/api/generated/auth/dto/AuthValidateAccountResponseDto;)Lcom/vk/superapp/api/dto/auth/validateaccount/VkAuthValidateAccountResponse;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkAuthValidateAccountResponse invoke(AuthValidateAccountResponseDto authValidateAccountResponseDto) {
                AuthValidateAccountResponseDto p10 = authValidateAccountResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return VkAuthValidateAccountResponseKt.toDomain(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmocg extends FunctionReferenceImpl implements Function1<AuthValidateLoginResponseDto, VkAuthValidateLoginResponse> {
            public static final ipakvmocg ipakvmoca = new ipakvmocg();

            ipakvmocg() {
                super(1, VkAuthValidateLoginResponseKt.class, "toDomain", "toDomain(Lcom/vk/api/generated/auth/dto/AuthValidateLoginResponseDto;)Lcom/vk/superapp/api/dto/auth/validatelogin/VkAuthValidateLoginResponse;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkAuthValidateLoginResponse invoke(AuthValidateLoginResponseDto authValidateLoginResponseDto) {
                AuthValidateLoginResponseDto p10 = authValidateLoginResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return VkAuthValidateLoginResponseKt.toDomain(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        static final /* synthetic */ class ipakvmoch extends FunctionReferenceImpl implements Function1<AuthValidatePhoneCheckResponseDto, AuthValidatePhoneCheckResponse> {
            public static final ipakvmoch ipakvmoca = new ipakvmoch();

            ipakvmoch() {
                super(1, AuthValidatePhoneCheckMapperKt.class, "toDomainModel", "toDomainModel(Lcom/vk/api/generated/auth/dto/AuthValidatePhoneCheckResponseDto;)Lcom/vk/superapp/api/dto/auth/validatephonecheck/AuthValidatePhoneCheckResponse;", 1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final AuthValidatePhoneCheckResponse invoke(AuthValidatePhoneCheckResponseDto authValidatePhoneCheckResponseDto) {
                AuthValidatePhoneCheckResponseDto p10 = authValidatePhoneCheckResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return AuthValidatePhoneCheckMapperKt.toDomainModel(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(List list) {
            Intrinsics.checkNotNull(list);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(AuthSilentAuthProviderMapperKt.toDomainModel((AuthSilentProviderDto) it.next()));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ConfirmResult ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return baseOkResponseDto == BaseOkResponseDto.OK ? ConfirmResult.OK : ConfirmResult.FAILURE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkAuthAutologinCredentials ipakvmocc(Function1 function1, Object obj) {
            return (VkAuthAutologinCredentials) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocd(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkAuthExchangeTokenInfo ipakvmoce(Function1 function1, Object obj) {
            return (VkAuthExchangeTokenInfo) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmocf(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocg(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkAuthValidateAccountResponse ipakvmoch(Function1 function1, Object obj) {
            return (VkAuthValidateAccountResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkAuthValidateLoginResponse ipakvmoci(Function1 function1, Object obj) {
            return (VkAuthValidateLoginResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AuthValidatePhoneCheckResponse ipakvmocj(Function1 function1, Object obj) {
            return (AuthValidatePhoneCheckResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ConfirmResult ipakvmock(Function1 function1, Object obj) {
            return (ConfirmResult) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<QrInfoResponse> allowAuthByQrCode(@NotNull String authCode, @NotNull UserId userId, boolean isInternalCamera) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            Intrinsics.checkNotNullParameter(userId, "userId");
            return ApiCommandExtKt.toUiObservable$default(new ProcessAuthCode(ProcessAuthCode.Companion.Action.ALLOW, null, null, authCode, isInternalCamera, 6, null).withAccessTokenOf(userId), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthResult> auth(@NotNull VkAuthState authState, @Nullable String trustedHash, boolean libverifySupport, @Nullable String scope, boolean receiveCookiesSupport, @Nullable String whiteLabelFlowInputSat, boolean fromBackup, boolean deviceTrustedHashSupported, @Nullable String wereAction, @Nullable String maxMessengerHash) {
            Intrinsics.checkNotNullParameter(authState, "authState");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthRequest(authState, superappApiCore.getOauthTokenHost(), trustedHash, superappApiCore.getApiAppId(), libverifySupport, scope, receiveCookiesSupport, whiteLabelFlowInputSat, fromBackup, deviceTrustedHashSupported, wereAction, maxMessengerHash), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthResult> authByAccessToken(@NotNull String accessToken, @Nullable String validateSession) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthByAccessToken(superappApiCore.getOauthHost(), superappApiCore.getApiAppId(), accessToken, validateSession), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<AuthGetCredentialsForServiceMultiResponseModel> authGetCredentialsForServiceMulti(int appId, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable List<String> exchangeTokens, @Nullable String clientDeviceId, @Nullable String clientExternalDeviceId) {
            Intrinsics.checkNotNullParameter(packageValue, "packageValue");
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(digestHash, "digestHash");
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetCredentialsForServiceMulti$default(AuthServiceKt.AuthService(), appId, packageValue, timestamp, digestHash, null, exchangeTokens, null, clientDeviceId, clientExternalDeviceId, 80, null)).setIsMultipleTokens(true).setAnonymous(true), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
            final ipakvmoca ipakvmocaVar = ipakvmoca.ipakvmoca;
            Single<AuthGetCredentialsForServiceMultiResponseModel> singleSingleOrError = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.e8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoca(ipakvmocaVar, obj);
                }
            }).singleOrError();
            Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
            return singleSingleOrError;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthOnSuccessValidationResponseDto> authMailOnSuccessValidation(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authOnSuccessValidation$default(AuthServiceKt.AuthService(), sid, LangUtils.getAppLanguage(), null, 4, null)).allowNoAuth().forceAnonymous(true).setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull String sid, @Nullable String maxMessengerHash) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authOnSuccessValidation(sid, LangUtils.getAppLanguage(), maxMessengerHash)).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<BaseOkResponseDto> bindExtOAuth(@Nullable String silentToken, @Nullable String uuid) {
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authBindExtOAuth(silentToken, uuid)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<AuthCheckBindExtOAuthResponseDto> checkBindExtOAuth(@Nullable List<AuthSilentTokenShortDto> silentTokensShort) {
            ArrayList arrayList;
            if (silentTokensShort != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(silentTokensShort, 10));
                for (AuthSilentTokenShortDto authSilentTokenShortDto : silentTokensShort) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("token", authSilentTokenShortDto.getToken());
                    jsonObject.addProperty(SilentAuthInfo.KEY_UUID, authSilentTokenShortDto.getUuid());
                    arrayList.add(jsonObject);
                }
            } else {
                arrayList = null;
            }
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authCheckBindExtOAuth(arrayList)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthResult> checkSilentToken(@NotNull VkAuthState authState, @NotNull String silentToken, @NotNull String uuid, @Nullable String sid, @Nullable String whiteLabelFlowInputSat) {
            Intrinsics.checkNotNullParameter(authState, "authState");
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new CheckSilentTokenRequest(superappApiCore.getOauthHost(), superappApiCore.getApiAppId(), silentToken, uuid, sid, authState, whiteLabelFlowInputSat), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthConfirmResponse> confirmPhone(@Nullable String phone, @NotNull String sid, @Nullable String code, @Nullable String session, @Nullable String token, boolean forceRemoveAccessToken, boolean canSkipPassword, boolean isCodeAutocomplete, @Nullable String verificationType) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            AuthService AuthService = AuthServiceKt.AuthService();
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authValidatePhoneConfirm$default(AuthService, sid, phone, code, session, token, String.valueOf(superappApiCore.getApiAppId()), superappApiCore.getDeviceId(), Boolean.valueOf(canSkipPassword), Boolean.valueOf(isCodeAutocomplete), null, verificationType, null, 2560, null))).forceRemoveAccessToken(forceRemoveAccessToken), superappApiCore.getApiManager(), null, null, false, null, 30, null);
            final ipakvmocb ipakvmocbVar = ipakvmocb.ipakvmoca;
            Observable<VkAuthConfirmResponse> map = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.a8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocb(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthResult> extendPartialToken(@NotNull String partialToken, @NotNull String password, @NotNull String extendHash, int appId) {
            Intrinsics.checkNotNullParameter(partialToken, "partialToken");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(extendHash, "extendHash");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthExtendTokenCommand(superappApiCore.getOauthHost(), appId, partialToken, password, extendHash), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthResult> extendProvidedToken(@NotNull String accessToken, @NotNull String providedHash, @NotNull String providedUuid, @NotNull String clientDeviceId, @Nullable String clientExternalDeviceId) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(providedHash, "providedHash");
            Intrinsics.checkNotNullParameter(providedUuid, "providedUuid");
            Intrinsics.checkNotNullParameter(clientDeviceId, "clientDeviceId");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthExtendProvidedTokenCommand(superappApiCore.getOauthHost(), superappApiCore.getApiAppId(), accessToken, providedHash, providedUuid, clientDeviceId, clientExternalDeviceId), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthExtendedSilentToken> extendSilentToken(@NotNull String silentToken, @NotNull String password, @NotNull String silentTokenUuid, @NotNull List<String> providedTokens, @NotNull List<String> providedUuids) {
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
            Intrinsics.checkNotNullParameter(providedTokens, "providedTokens");
            Intrinsics.checkNotNullParameter(providedUuids, "providedUuids");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthExtendSilentTokenCommand(superappApiCore.getOauthHost(), superappApiCore.getApiAppId(), silentToken, password, silentTokenUuid, providedTokens, providedUuids), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthExternalFlowOutResponseDto> externalFlowOut(@NotNull String whiteLabelFlowInputSat, @Nullable String sid) {
            Intrinsics.checkNotNullParameter(whiteLabelFlowInputSat, "whiteLabelFlowInputSat");
            return ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authExternalFlowOut$default(AuthServiceKt.AuthService(), whiteLabelFlowInputSat, AuthExternalFlowOutPlatformDto.MOBILE, sid, null, null, 24, null)).allowNoAuth().setAnonymous(true), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<List<AuthSilentTokenShortDto>> filterSilentTokens(@NotNull List<AuthSilentTokenShortDto> silentTokensShort, @NotNull String superAppToken) {
            Intrinsics.checkNotNullParameter(silentTokensShort, "silentTokensShort");
            Intrinsics.checkNotNullParameter(superAppToken, "superAppToken");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(silentTokensShort, 10));
            for (AuthSilentTokenShortDto authSilentTokenShortDto : silentTokensShort) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("token", authSilentTokenShortDto.getToken());
                jsonObject.addProperty(SilentAuthInfo.KEY_UUID, authSilentTokenShortDto.getUuid());
                arrayList.add(jsonObject);
            }
            return ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authFilterSilentTokens(arrayList, superAppToken)).allowNoAuth().forceRemoveAccessToken(true).setAnonymous(true), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<List<VkAuthAppScope>> getAppScopes() {
            WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetAppScopes$default(AuthServiceKt.AuthService(), null, null, null, 7, null)), null, 1, null);
            Observable<List<VkAuthAppScope>> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<VkAuthAutologinCredentials> getAutologinCredentials(@Nullable String uuid, @NotNull List<String> silentTokens, @Nullable String sessionId) {
            Intrinsics.checkNotNullParameter(silentTokens, "silentTokens");
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetAutologinCredentials$default(AuthServiceKt.AuthService(), silentTokens, null, uuid, sessionId, 2, null)).setAnonymous(true).skipValidation(true), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
            final ipakvmocc ipakvmoccVar = ipakvmocc.ipakvmoca;
            Single<VkAuthAutologinCredentials> singleSingleOrError = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.y7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocc(ipakvmoccVar, obj);
                }
            }).singleOrError();
            Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
            return singleSingleOrError;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<VkAuthGetContinuationForServiceResponse> getContinuationForService(int appId, @Nullable String silentToken, @Nullable String silentTokenUuid, @Nullable String phoneValidationSid, boolean forceRemoveAccessToken) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authGetContinuationForService(null, Integer.valueOf(appId), silentToken, silentTokenUuid, phoneValidationSid));
            webApiRequest.allowNoAuth();
            webApiRequest.forceRemoveAccessToken(forceRemoveAccessToken);
            WebApiRequest.toUiSingle$default(webApiRequest, null, 1, null);
            Single<VkAuthGetContinuationForServiceResponse> singleNever = Single.never();
            Intrinsics.checkNotNullExpressionValue(singleNever, "never(...)");
            return singleNever;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<List<VkAuthAppCredentials>> getCredentialsForApp(long appId) {
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authGetCredentialsForApp((int) appId)), null, 1, null);
            final ipakvmocd ipakvmocdVar = ipakvmocd.ipakvmoca;
            Single<List<VkAuthAppCredentials>> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.w7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocd(ipakvmocdVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkEsiaSignature> getEsiaSignature(boolean isVerificationFlow, @NotNull String externalClientId) {
            Intrinsics.checkNotNullParameter(externalClientId, "externalClientId");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toUiObservable$default(new AuthGetEsiaSignature(superappApiCore.getOauthHost(), superappApiCore.getApiAppId(), superappApiCore.getApiAppSecret(), isVerificationFlow, externalClientId), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<AuthGetExchangeTokenResponseDto> getExchangeToken(@NotNull UserId userId, @Nullable String accessToken) {
            Intrinsics.checkNotNullParameter(userId, "userId");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetExchangeToken$default(AuthServiceKt.AuthService(), null, null, null, 7, null));
            if (accessToken != null) {
                webApiRequest.overrideAuth(accessToken, null);
            }
            Single<AuthGetExchangeTokenResponseDto> singleSingleOrError = ApiCommandExtKt.toBgObservable$default(webApiRequest.withAccessTokenOf(userId), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null).singleOrError();
            Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
            return singleSingleOrError;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<VkAuthExchangeTokenInfo> getExchangeTokenInfo(@NotNull String exchangeToken) {
            Intrinsics.checkNotNullParameter(exchangeToken, "exchangeToken");
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetExchangeTokenInfo$default(AuthServiceKt.AuthService(), exchangeToken, null, Integer.valueOf(SuperappApiCore.INSTANCE.getApiAppId()), 2, null)).forceRemoveAccessToken(true).allowNoAuth().setAnonymous(true), null, 1, null);
            final ipakvmoce ipakvmoceVar = ipakvmoce.ipakvmoca;
            Single<VkAuthExchangeTokenInfo> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.z7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoce(ipakvmoceVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<List<AuthExchangeTokenInfoDto>> getExchangeTokensInfo(@NotNull List<String> exchangeTokens) {
            Intrinsics.checkNotNullParameter(exchangeTokens, "exchangeTokens");
            AuthService AuthService = AuthServiceKt.AuthService();
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            return ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetExchangeTokensInfo$default(AuthService, exchangeTokens, null, Integer.valueOf(superappApiCore.getApiAppId()), 2, null)).forceRemoveAccessToken(true).allowNoAuth().setAnonymous(true), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthHashes> getHashes() {
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            String oauthHost = superappApiCore.getOauthHost();
            int apiAppId = superappApiCore.getApiAppId();
            String apiAppSecret = superappApiCore.getApiAppSecret();
            VKAccessTokenProvider anonymousTokenProvider = superappApiCore.getAnonymousTokenProvider();
            return ApiCommandExtKt.toUiObservable$default(new AuthGetHashes(oauthHost, apiAppId, apiAppSecret, anonymousTokenProvider != null ? anonymousTokenProvider.getToken() : null), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<List<AuthSilentAuthProvider>> getSilentAuthProviders() {
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authGetSilentAuthProviders()).forceRemoveAccessToken(true).allowNoAuth().setAnonymous(true), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.s7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoca((List) obj);
                }
            };
            Single<List<AuthSilentAuthProvider>> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.x7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocf(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<OkFlowSilentTokenResponse> getSilentTokenByExternalCredentials(@NotNull String externalLogin, @NotNull String externalPassword, @NotNull String externalData) {
            Intrinsics.checkNotNullParameter(externalLogin, "externalLogin");
            Intrinsics.checkNotNullParameter(externalPassword, "externalPassword");
            Intrinsics.checkNotNullParameter(externalData, "externalData");
            return ApiCommandExtKt.toUiObservable$default(new AuthOkSilentTokenByExternalCredentials(externalLogin, externalPassword, externalData), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<OkFlowSilentTokenResponse> getSilentTokenByExternalToken(@NotNull String externalToken, @NotNull String clientMetadata) {
            Intrinsics.checkNotNullParameter(externalToken, "externalToken");
            Intrinsics.checkNotNullParameter(clientMetadata, "clientMetadata");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            int apiAppId = superappApiCore.getApiAppId();
            String apiAppSecret = superappApiCore.getApiAppSecret();
            VKAccessTokenProvider anonymousTokenProvider = superappApiCore.getAnonymousTokenProvider();
            return ApiCommandExtKt.toUiObservable$default(new AuthOkSilentTokenByExternalToken(apiAppId, apiAppSecret, anonymousTokenProvider != null ? anonymousTokenProvider.getToken() : null, externalToken, clientMetadata), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<GetUserInfoByPhone> getUserInfoByToken(@NotNull String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authGetUserInfoByPhone$default(AuthServiceKt.AuthService(), token, null, 2, null));
            webApiRequest.setSuperappToken(token);
            WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            Observable<GetUserInfoByPhone> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthValidatePhoneInfo> getValidatePhoneInfo(@NotNull String sid, @NotNull String phone, boolean forceRemoveAccessToken) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(phone, "phone");
            Observable<VkAuthValidatePhoneInfo> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<VkConnectRemoteConfig> getVkConnectConfig(int appId) {
            Single<VkConnectRemoteConfig> singleSingleOrError = ApiCommandExtKt.toUiObservable$default(new AuthGetVkConnectRemoteConfig(appId), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null).singleOrError();
            Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
            return singleSingleOrError;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<Boolean> logout(@NotNull String accessToken) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authLogout(SuperappApiCore.INSTANCE.getApiAppId())).overrideAuth(accessToken, null), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.f8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.t7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocg(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<PasskeyBeginResult> passkeyBegin(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            String oauthHost = superappApiCore.getOauthHost();
            VKAccessTokenProvider anonymousTokenProvider = superappApiCore.getAnonymousTokenProvider();
            return ApiCommandExtKt.toUiObservable$default(new PasskeyBeginCommand(oauthHost, sid, anonymousTokenProvider != null ? anonymousTokenProvider.getToken() : null), superappApiCore.getApiManager(), null, null, false, null, 30, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<AuthRefreshTokensResponseDto> refreshTokens(@NotNull List<String> exchangeTokens, @NotNull AuthByExchangeTokenInitiator initiator, int activeIndex, @NotNull String scope) {
            Intrinsics.checkNotNullParameter(exchangeTokens, "exchangeTokens");
            Intrinsics.checkNotNullParameter(initiator, "initiator");
            Intrinsics.checkNotNullParameter(scope, "scope");
            AuthService AuthService = AuthServiceKt.AuthService();
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authRefreshTokens$default(AuthService, superappApiCore.getApiAppId(), superappApiCore.getApiAppSecret(), exchangeTokens, null, null, Integer.valueOf(activeIndex), scope, initiator.getValue(), null, null, 792, null));
            webApiRequest.allowNoAuth();
            webApiRequest.setAnonymous(true);
            webApiRequest.forceRemoveAuth(true);
            return WebApiRequest.toBgSingle$default(webApiRequest, null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthSignUpResult> signUp(@Nullable String firstName, @Nullable String lastName, @Nullable String fullName, @NotNull VkGender gender, @Nullable String birthday, @Nullable String phone, @NotNull String sid, @Nullable String password, boolean extendedAuth, @Nullable String profileType, @Nullable String email, boolean canSkipPassword, @Nullable String inviteHash, @Nullable String validateSession) {
            Intrinsics.checkNotNullParameter(gender, "gender");
            Intrinsics.checkNotNullParameter(sid, "sid");
            AuthService AuthService = AuthServiceKt.AuthService();
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            AuthService.DefaultImpls.authSignup$default(AuthService, superappApiCore.getApiAppId(), superappApiCore.getApiAppSecret(), firstName, null, lastName, birthday, phone, password, null, null, AuthSignupSexDto.valueOf(gender.name()), sid, null, fullName, superappApiCore.getDeviceId(), Boolean.valueOf(extendedAuth), validateSession, null, null, null, null, null, 4063240, null);
            Observable<VkAuthSignUpResult> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthValidateAccountResponse> validateAccount(@NotNull String login, boolean forcePassword, @Nullable String trustedHash, @NotNull List<String> trustedHashes, @NotNull List<? extends AuthSupportedWay> supportedWays, @Nullable String superAppToken, @Nullable List<String> exchangeTokens, @Nullable String sid) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(trustedHashes, "trustedHashes");
            Intrinsics.checkNotNullParameter(supportedWays, "supportedWays");
            AuthService AuthService = AuthServiceKt.AuthService();
            String strEmptyToNull = StringExtKt.emptyToNull(trustedHash);
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = supportedWays.iterator();
            while (it.hasNext()) {
                AuthValidateAccountSupportedWaysDto validateAccountDto = AuthSupportedWayKt.toValidateAccountDto((AuthSupportedWay) it.next());
                if (validateAccountDto != null) {
                    arrayList.add(validateAccountDto);
                }
            }
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authValidateAccount$default(AuthService, login, sid, Boolean.valueOf(forcePassword), superAppToken, null, arrayList, null, null, strEmptyToNull, null, null, trustedHashes, exchangeTokens, null, null, null, null, null, null, null, null, null, null, 8382160, null))), SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null);
            final ipakvmocf ipakvmocfVar = ipakvmocf.ipakvmoca;
            Observable<VkAuthValidateAccountResponse> map = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.c8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoch(ipakvmocfVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<AuthValidateEmailResponseDto> validateEmail(@NotNull String sid, @Nullable String email, boolean forceRemoveAccessToken) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authValidateEmail(sid, SuperappApiCore.INSTANCE.getApiAppId(), email));
            webApiRequest.forceRemoveAccessToken(forceRemoveAccessToken);
            WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            Observable<AuthValidateEmailResponseDto> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthConfirmResponse> validateEmailConfirm(@NotNull String sid, @NotNull String code, boolean forceRemoveAccessToken) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(code, "code");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authValidateEmailConfirm(sid, code, SuperappApiCore.INSTANCE.getApiAppId()));
            webApiRequest.forceRemoveAccessToken(forceRemoveAccessToken);
            WebApiRequest.toUiObservable$default(webApiRequest, null, 1, null);
            Observable<VkAuthConfirmResponse> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthValidateLoginResponse> validateLogin(@NotNull String login, @Nullable String sid, @NotNull String source) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(source, "source");
            AuthService AuthService = AuthServiceKt.AuthService();
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            Observable bgObservable$default = ApiCommandExtKt.toBgObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authValidateLogin$default(AuthService, login, superappApiCore.getApiAppId(), sid, source, null, null, 48, null))), superappApiCore.getApiManager(), null, null, false, null, 30, null);
            final ipakvmocg ipakvmocgVar = ipakvmocg.ipakvmoca;
            Observable<VkAuthValidateLoginResponse> map = bgObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.b8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmoci(ipakvmocgVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Observable<VkAuthValidatePhoneResult> validatePhone(@Nullable String sid, @Nullable String phone, boolean voice, boolean libverifySupport, boolean forceRemoveAccessToken, boolean disablePartial, boolean allowPush, boolean allowEmail, boolean allowPasskey, @Nullable String superAppToken, boolean allowSmsInbox) {
            AuthService.DefaultImpls.authValidatePhone$default(AuthServiceKt.AuthService(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
            Observable<VkAuthValidatePhoneResult> observableEmpty = Observable.empty();
            Intrinsics.checkNotNullExpressionValue(observableEmpty, "empty(...)");
            return observableEmpty;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<AuthValidatePhoneCheckResponse> validatePhoneCheck(boolean isAuth, @Nullable Long appId) {
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authValidatePhoneCheck$default(AuthServiceKt.AuthService(), isAuth, appId != null ? Integer.valueOf((int) appId.longValue()) : null, null, 4, null));
            ApiCallExtKt.addRegistrationStatsFields(webApiRequest);
            webApiRequest.allowNoAuth();
            webApiRequest.setAnonymous(true);
            Single bgSingle$default = WebApiRequest.toBgSingle$default(webApiRequest, null, 1, null);
            final ipakvmoch ipakvmochVar = ipakvmoch.ipakvmoca;
            Single<AuthValidatePhoneCheckResponse> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.d8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocj(ipakvmochVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<ConfirmResult> validatePhoneCheckSkip(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authValidatePhoneCheckSkip(sid));
            webApiRequest.setAnonymous(true);
            webApiRequest.allowNoAuth();
            Single singleSingleOrError = ApiCommandExtKt.toBgObservable$default(webApiRequest, SuperappApiCore.INSTANCE.getApiManager(), null, null, false, null, 30, null).singleOrError();
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Single<ConfirmResult> map = singleSingleOrError.map(new Function() { // from class: com.vk.superapp.api.contract.v7
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkAuth.ipakvmock(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkAuth
        @NotNull
        public Single<VkAuthValidateSuperappTokenResponse> validateSuperappToken(@NotNull String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            Single<VkAuthValidateSuperappTokenResponse> singleNever = Single.never();
            Intrinsics.checkNotNullExpressionValue(singleNever, "never(...)");
            return singleNever;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkAuthConfirmResponse ipakvmocb(Function1 function1, Object obj) {
            return (VkAuthConfirmResponse) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final AuthGetCredentialsForServiceMultiResponseModel ipakvmoca(Function1 function1, Object obj) {
            return (AuthGetCredentialsForServiceMultiResponseModel) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkHealth;", "Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "<init>", "()V", "getHealthCommonClientConfig", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/healthCommon/dto/HealthCommonClientConfigDto;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkHealth implements SuperappApi.VkHealth {
        @Override // com.vk.superapp.api.contract.SuperappApi.VkHealth
        @NotNull
        public Single<HealthCommonClientConfigDto> getHealthCommonClientConfig() {
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(HealthCommonService.DefaultImpls.healthCommonGetClientConfig$default(HealthCommonServiceKt.HealthCommonService(), null, 1, null)), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005H\u0016J \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0016J<\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\n2\b\u0010\"\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0016¨\u0006#"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkIdMail;", "Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "<init>", "()V", "getMailAccBindList", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/vkidmail/dto/VkidmailBindListResponseDto;", "exchangeToken", "Lcom/vk/api/generated/vkidmail/dto/VkidmailExchangeTokenResponseDto;", "email", "", "clientAppId", "linkedEmails", "Lcom/vk/api/generated/vkidmail/dto/VkidmailLinkedEmailsResponseDto;", "checkRestore", "Lcom/vk/superapp/api/dto/auth/vkidmail/VkidMailCheckRestoreResponseDto;", AppConfigRequestHandler.FEATURES_KEY, "checkPassword", "Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailCheckPasswordResult;", "password", PasskeyBeginResult.SID_KEY, "bindFlow", "", "authOnSuccessValidation", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/api/generated/auth/dto/AuthOnSuccessValidationResponseDto;", "getUserBlockStatusInfo", "Lcom/vk/superapp/api/dto/auth/vkidmail/userblocked/model/VkIDMailUserBlockStatusResult;", "silentToken", SilentAuthInfo.KEY_UUID, "getSilentToken", "Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailSilentTokenResultDto;", "redirectDomain", "codeChallenge", "codeChallengeMethod", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$VkIdMail\n+ 2 KotlinCommonExt.kt\ncom/vk/core/extensions/KotlinCommonExtKt\n*L\n1#1,3566:1\n14#2,2:3567\n*S KotlinDebug\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$VkIdMail\n*L\n482#1:3567,2\n*E\n"})
    public static final class VkIdMail implements SuperappApi.VkIdMail {

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<VkidmailCheckPasswordResponseDto, MailCheckPasswordResult> {
            ipakvmoca(VkidMailCheckPasswordDtoMapper vkidMailCheckPasswordDtoMapper) {
                super(1, vkidMailCheckPasswordDtoMapper, VkidMailCheckPasswordDtoMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/vkidmail/dto/VkidmailCheckPasswordResponseDto;)Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailCheckPasswordResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final MailCheckPasswordResult invoke(VkidmailCheckPasswordResponseDto vkidmailCheckPasswordResponseDto) {
                VkidmailCheckPasswordResponseDto p10 = vkidmailCheckPasswordResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((VkidMailCheckPasswordDtoMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<VkidmailCheckRestoreResponseDto, VkidMailCheckRestoreResponseDto> {
            ipakvmocb(VkidMailCheckRestoreResponseMapper vkidMailCheckRestoreResponseMapper) {
                super(1, vkidMailCheckRestoreResponseMapper, VkidMailCheckRestoreResponseMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/vkidmail/dto/VkidmailCheckRestoreResponseDto;)Lcom/vk/superapp/api/dto/auth/vkidmail/VkidMailCheckRestoreResponseDto;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkidMailCheckRestoreResponseDto invoke(VkidmailCheckRestoreResponseDto vkidmailCheckRestoreResponseDto) {
                VkidmailCheckRestoreResponseDto p10 = vkidmailCheckRestoreResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((VkidMailCheckRestoreResponseMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocc extends FunctionReferenceImpl implements Function1<VkidmailSilentAuthTokenResponseDto, MailSilentTokenResultDto> {
            ipakvmocc(VkIdMailGetSilentTokenDtoMapper vkIdMailGetSilentTokenDtoMapper) {
                super(1, vkIdMailGetSilentTokenDtoMapper, VkIdMailGetSilentTokenDtoMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/vkidmail/dto/VkidmailSilentAuthTokenResponseDto;)Lcom/vk/superapp/api/dto/auth/vkidmail/model/MailSilentTokenResultDto;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final MailSilentTokenResultDto invoke(VkidmailSilentAuthTokenResponseDto vkidmailSilentAuthTokenResponseDto) {
                VkidmailSilentAuthTokenResponseDto p10 = vkidmailSilentAuthTokenResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((VkIdMailGetSilentTokenDtoMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocd extends FunctionReferenceImpl implements Function1<VkidmailUserBlockStatusResponseDto, VkIDMailUserBlockStatusResult> {
            ipakvmocd(VkIdMailUserBlockedStatusDtoMapper vkIdMailUserBlockedStatusDtoMapper) {
                super(1, vkIdMailUserBlockedStatusDtoMapper, VkIdMailUserBlockedStatusDtoMapper.class, BlockParser.MAP_TYPE, "map(Lcom/vk/api/generated/vkidmail/dto/VkidmailUserBlockStatusResponseDto;)Lcom/vk/superapp/api/dto/auth/vkidmail/userblocked/model/VkIDMailUserBlockStatusResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkIDMailUserBlockStatusResult invoke(VkidmailUserBlockStatusResponseDto vkidmailUserBlockStatusResponseDto) {
                VkidmailUserBlockStatusResponseDto p10 = vkidmailUserBlockStatusResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((VkIdMailUserBlockedStatusDtoMapper) this.receiver).map(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MailCheckPasswordResult ipakvmoca(Function1 function1, Object obj) {
            return (MailCheckPasswordResult) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkidMailCheckRestoreResponseDto ipakvmocb(Function1 function1, Object obj) {
            return (VkidMailCheckRestoreResponseDto) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final MailSilentTokenResultDto ipakvmocc(Function1 function1, Object obj) {
            return (MailSilentTokenResultDto) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkIDMailUserBlockStatusResult ipakvmocd(Function1 function1, Object obj) {
            return (VkIDMailUserBlockStatusResult) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Observable<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull String sid, boolean bindFlow) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(AuthService.DefaultImpls.authOnSuccessValidation$default(AuthServiceKt.AuthService(), sid, LangUtils.getAppLanguage(), null, 4, null)).forceAnonymous(true).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<MailCheckPasswordResult> checkPassword(@NotNull String password, @NotNull String sid, boolean bindFlow) {
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(sid, "sid");
            VkidMailCheckPasswordDtoMapper vkidMailCheckPasswordDtoMapper = new VkidMailCheckPasswordDtoMapper();
            WebApiRequest webApiRequest = ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailCheckPassword(sid, password, Boolean.valueOf(bindFlow)));
            if (!bindFlow) {
                webApiRequest.allowNoAuth();
                webApiRequest.setAnonymous(true);
                webApiRequest = webApiRequest.forceAnonymous(true);
            }
            Single bgSingle$default = WebApiRequest.toBgSingle$default(webApiRequest, null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca(vkidMailCheckPasswordDtoMapper);
            Single<MailCheckPasswordResult> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.g8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkIdMail.ipakvmoca(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<VkidMailCheckRestoreResponseDto> checkRestore(@NotNull String email, @Nullable String features) {
            Intrinsics.checkNotNullParameter(email, "email");
            VkidMailCheckRestoreResponseMapper vkidMailCheckRestoreResponseMapper = new VkidMailCheckRestoreResponseMapper();
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailCheckRestore(email, features)).allowNoAuth().setAnonymous(true).forceAnonymous(true), null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb(vkidMailCheckRestoreResponseMapper);
            Single<VkidMailCheckRestoreResponseDto> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.i8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkIdMail.ipakvmocb(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<VkidmailExchangeTokenResponseDto> exchangeToken(@NotNull String email, @NotNull String clientAppId) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(clientAppId, "clientAppId");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailExchangeToken(email, clientAppId)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<VkidmailBindListResponseDto> getMailAccBindList() {
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailBindList("e.mail.ru", Boolean.TRUE)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<MailSilentTokenResultDto> getSilentToken(@NotNull String email, @NotNull String redirectDomain, @Nullable String codeChallenge, @Nullable String codeChallengeMethod, @Nullable String sid) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(redirectDomain, "redirectDomain");
            VkIdMailGetSilentTokenDtoMapper vkIdMailGetSilentTokenDtoMapper = new VkIdMailGetSilentTokenDtoMapper();
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailSilentAuthToken(email, redirectDomain, sid, codeChallenge, codeChallengeMethod)), null, 1, null);
            final ipakvmocc ipakvmoccVar = new ipakvmocc(vkIdMailGetSilentTokenDtoMapper);
            Single<MailSilentTokenResultDto> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.h8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkIdMail.ipakvmocc(ipakvmoccVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<VkIDMailUserBlockStatusResult> getUserBlockStatusInfo(@NotNull String email, @NotNull String silentToken, @NotNull String uuid) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Single bgSingle$default = WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailUserBlockStatus(email, silentToken, uuid)).allowNoAuth().setAnonymous(true).forceAnonymous(true), null, 1, null);
            final ipakvmocd ipakvmocdVar = new ipakvmocd(new VkIdMailUserBlockedStatusDtoMapper());
            Single<VkIDMailUserBlockStatusResult> map = bgSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.j8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkIdMail.ipakvmocd(ipakvmocdVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkIdMail
        @NotNull
        public Single<VkidmailLinkedEmailsResponseDto> linkedEmails() {
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkidmailServiceKt.VkidmailService().vkidmailLinkedEmails()), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ%\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkRestore;", "Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "<init>", "()V", "", "code", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/superapp/api/dto/restore/VkRestoreInstantAuth;", "getInstantAuthByNotifyInfo", "(I)Lio/reactivex/rxjava3/core/Single;", "", "isConfirmed", "Lcom/vk/superapp/api/dto/restore/VkRestoreConfirmInstantResult;", "confirmInstantAuthByNotify", "(IZ)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkRestore implements SuperappApi.VkRestore {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.k8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.VkRestore.ipakvmoca();
            }
        });

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmoca extends FunctionReferenceImpl implements Function1<Integer, VkRestoreConfirmInstantResult> {
            ipakvmoca(VkRestoreConfirmInstantResult.Companion companion) {
                super(1, companion, VkRestoreConfirmInstantResult.Companion.class, "parse", "parse(I)Lcom/vk/superapp/api/dto/restore/VkRestoreConfirmInstantResult;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkRestoreConfirmInstantResult invoke(Integer num) {
                return ((VkRestoreConfirmInstantResult.Companion) this.receiver).parse(num.intValue());
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes5.dex */
        static final /* synthetic */ class ipakvmocb extends FunctionReferenceImpl implements Function1<RestoreGetInstantAuthByNotifyInfoResponseDto, VkRestoreInstantAuth> {
            ipakvmocb(RestoreMapper restoreMapper) {
                super(1, restoreMapper, RestoreMapper.class, "mapToVkRestoreInstantAuth", "mapToVkRestoreInstantAuth(Lcom/vk/api/generated/restore/dto/RestoreGetInstantAuthByNotifyInfoResponseDto;)Lcom/vk/superapp/api/dto/restore/VkRestoreInstantAuth;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public final VkRestoreInstantAuth invoke(RestoreGetInstantAuthByNotifyInfoResponseDto restoreGetInstantAuthByNotifyInfoResponseDto) {
                RestoreGetInstantAuthByNotifyInfoResponseDto p10 = restoreGetInstantAuthByNotifyInfoResponseDto;
                Intrinsics.checkNotNullParameter(p10, "p0");
                return ((RestoreMapper) this.receiver).mapToVkRestoreInstantAuth(p10);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final RestoreMapper ipakvmoca() {
            return new RestoreMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkRestoreInstantAuth ipakvmocb(Function1 function1, Object obj) {
            return (VkRestoreInstantAuth) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkRestore
        @NotNull
        public Single<VkRestoreConfirmInstantResult> confirmInstantAuthByNotify(int code, boolean isConfirmed) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(RestoreServiceKt.RestoreService().restoreConfirmInstantAuthByNotify(code, isConfirmed ? RestoreConfirmInstantAuthByNotifyIsConfirmedDto.TYPE_1 : RestoreConfirmInstantAuthByNotifyIsConfirmedDto.TYPE_0)), null, 1, null);
            final ipakvmoca ipakvmocaVar = new ipakvmoca(VkRestoreConfirmInstantResult.INSTANCE);
            Single<VkRestoreConfirmInstantResult> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.l8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkRestore.ipakvmoca(ipakvmocaVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkRestore
        @NotNull
        public Single<VkRestoreInstantAuth> getInstantAuthByNotifyInfo(int code) {
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(RestoreServiceKt.RestoreService().restoreGetInstantAuthByNotifyInfo(code)), null, 1, null);
            final ipakvmocb ipakvmocbVar = new ipakvmocb((RestoreMapper) this.ipakvmoca.getValue());
            Single<VkRestoreInstantAuth> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.m8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkRestore.ipakvmocb(ipakvmocbVar, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkRestoreConfirmInstantResult ipakvmoca(Function1 function1, Object obj) {
            return (VkRestoreConfirmInstantResult) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\n\u001a\u00020\bH\u0016J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\r"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkUtils;", "Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "<init>", "()V", "guessUserSex", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/core/api/models/VkGender;", "firstName", "", "lastName", "fullName", "checkName", "", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkUtils implements SuperappApi.VkUtils {
        /* JADX INFO: Access modifiers changed from: private */
        public static final VkGender ipakvmoca(UtilsGuessUserSexResponseDto utilsGuessUserSexResponseDto) {
            return VkGender.INSTANCE.fromValue(utilsGuessUserSexResponseDto.getSex().getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkGender ipakvmocb(UtilsGuessUserSexResponseDto utilsGuessUserSexResponseDto) {
            return VkGender.INSTANCE.fromValue(utilsGuessUserSexResponseDto.getSex().getValue());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkGender ipakvmocc(Function1 function1, Object obj) {
            return (VkGender) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkGender ipakvmocd(Function1 function1, Object obj) {
            return (VkGender) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkUtils
        @NotNull
        public Observable<Boolean> checkName(@NotNull String firstName, @NotNull String lastName) {
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(UtilsService.DefaultImpls.utilsCheckUserName$default(UtilsServiceKt.UtilsService(), firstName, lastName, null, 4, null))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.w8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.x8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkUtils
        @NotNull
        public Observable<VkGender> guessUserSex(@NotNull String firstName, @NotNull String lastName) {
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(UtilsService.DefaultImpls.utilsGuessUserSex$default(UtilsServiceKt.UtilsService(), firstName, lastName, null, 4, null))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.u8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmoca((UtilsGuessUserSexResponseDto) obj);
                }
            };
            Observable<VkGender> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.v8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmocc(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkUtils
        @NotNull
        public Observable<Boolean> checkName(@NotNull String fullName) {
            Intrinsics.checkNotNullParameter(fullName, "fullName");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(UtilsService.DefaultImpls.utilsCheckUserName$default(UtilsServiceKt.UtilsService(), null, null, fullName, 3, null))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.s8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmocb((BaseOkResponseDto) obj);
                }
            };
            Observable<Boolean> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.t8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkUtils
        @NotNull
        public Observable<VkGender> guessUserSex(@NotNull String fullName) {
            Intrinsics.checkNotNullParameter(fullName, "fullName");
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.addBaseAuthParams(ApiCallExtKt.toWebApiRequest(UtilsService.DefaultImpls.utilsGuessUserSex$default(UtilsServiceKt.UtilsService(), null, null, fullName, 3, null))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.y8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmocb((UtilsGuessUserSexResponseDto) obj);
                }
            };
            Observable<VkGender> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.z8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkUtils.ipakvmocd(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJG\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkWorkout;", "Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "<init>", "()V", "", "Lcom/vk/superapp/api/dto/vkworkout/WorkoutData;", "list", "Lio/reactivex/rxjava3/core/Single;", "", "importWorkouts", "(Ljava/util/List;)Lio/reactivex/rxjava3/core/Single;", "", "range", "Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsAggregationTypeDto;", "aggregationType", "Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsActivityTypeDto;", "activityType", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "Lcom/vk/api/generated/vkStart/dto/VkStartStatsListItemDto;", "getStats", "(Ljava/util/List;Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsAggregationTypeDto;Lcom/vk/api/generated/vkStart/dto/VkStartGetStatsActivityTypeDto;Lcom/vk/dto/common/id/UserId;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkWorkout implements SuperappApi.VkWorkout {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.c9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.VkWorkout.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkWorkoutMapper ipakvmoca() {
            return new VkWorkoutMapper();
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkWorkout
        @NotNull
        public Single<List<VkStartStatsListItemDto>> getStats(@NotNull List<String> range, @Nullable VkStartGetStatsAggregationTypeDto aggregationType, @Nullable VkStartGetStatsActivityTypeDto activityType, @Nullable UserId userId) {
            Intrinsics.checkNotNullParameter(range, "range");
            return WebApiRequest.toBgSingle$default(ApiCallExtKt.toWebApiRequest(VkStartServiceKt.VkStartService().vkStartGetStats(range, aggregationType, activityType, userId)), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkWorkout
        @NotNull
        public Single<Boolean> importWorkouts(@NotNull List<WorkoutData> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkStartService.DefaultImpls.vkStartImportActivities$default(VkStartServiceKt.VkStartService(), ((VkWorkoutMapper) this.ipakvmoca.getValue()).mapToVkActivityList(list), null, null, 6, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.a9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkWorkout.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.b9
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkWorkout.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0011\u0010\u0007\u001a\r\u0012\t\u0012\u00070\t¢\u0006\u0002\b\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016J@\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\fH\u0016J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J.\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00052\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0006\u0010&\u001a\u00020\fH\u0016¨\u0006'"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkidOk;", "Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "<init>", "()V", "authExchangeSilentTokensToSid", "Lio/reactivex/rxjava3/core/Single;", "Lcom/vk/api/generated/auth/dto/AuthExchangeSilentTokenToSidResponseDto;", "silentTokens", "", "Lcom/google/gson/JsonObject;", "Lkotlinx/parcelize/RawValue;", PasskeyBeginResult.SID_KEY, "", "phone", "checkPassword", "Lcom/vk/api/generated/vkidok/dto/VkidokCheckPasswordResponseDto;", "password", "checkPersonalInfo", "Lcom/vk/api/generated/vkidok/dto/VkidokCheckPersonalInfoResponseDto;", "firstName", "lastName", "birthday", "gender", "", "maxMessengerHash", "externalItsMe", "Lcom/vk/api/generated/vkidok/dto/VkidokExternalItsMeResponseDto;", "externalItsNotMe", "Lcom/vk/api/generated/vkidok/dto/VkidokExternalItsNotMeResponseDto;", "internalItsMe", "Lcom/vk/api/generated/vkidok/dto/VkidokInternalItsMeResponseDto;", "internalItsNotMe", "Lcom/vk/api/generated/vkidok/dto/VkidokInternalItsNotMeResponseDto;", "startRegistration", "Lcom/vk/api/generated/vkidok/dto/VkidokStartRegistrationResponseDto;", "platform", "lang", "clientMetadata", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkidOk implements SuperappApi.VkidOk {
        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<AuthExchangeSilentTokenToSidResponseDto> authExchangeSilentTokensToSid(@NotNull List<JsonObject> silentTokens, @NotNull String sid, @NotNull String phone) {
            Intrinsics.checkNotNullParameter(silentTokens, "silentTokens");
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(phone, "phone");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(AuthServiceKt.AuthService().authExchangeSilentTokensToSid(silentTokens, sid, phone, LangUtils.getAppLanguage())).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokCheckPasswordResponseDto> checkPassword(@NotNull String sid, @NotNull String password) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(password, "password");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokCheckPassword(sid, LangUtils.getAppLanguage(), password)).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokCheckPersonalInfoResponseDto> checkPersonalInfo(@NotNull String sid, @NotNull String firstName, @NotNull String lastName, @NotNull String birthday, int gender, @Nullable String maxMessengerHash) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Intrinsics.checkNotNullParameter(birthday, "birthday");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokService.DefaultImpls.vkidokCheckPersonalInfo$default(VkidokServiceKt.VkidokService(), sid, LangUtils.getAppLanguage(), firstName, lastName, birthday, gender, null, maxMessengerHash, 64, null)).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokExternalItsMeResponseDto> externalItsMe(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokExternalItsMe(sid, LangUtils.getAppLanguage())).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokExternalItsNotMeResponseDto> externalItsNotMe(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokExternalItsNotMe(sid, LangUtils.getAppLanguage())).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokInternalItsMeResponseDto> internalItsMe(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokInternalItsMe(sid, LangUtils.getAppLanguage())).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokInternalItsNotMeResponseDto> internalItsNotMe(@NotNull String sid) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokInternalItsNotMe(sid, LangUtils.getAppLanguage())).allowNoAuth().setAnonymous(true), null, 1, null);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkidOk
        @NotNull
        public Single<VkidokStartRegistrationResponseDto> startRegistration(@NotNull String platform, @NotNull String lang, @NotNull String clientMetadata, @NotNull String deviceId) {
            Intrinsics.checkNotNullParameter(platform, "platform");
            Intrinsics.checkNotNullParameter(lang, "lang");
            Intrinsics.checkNotNullParameter(clientMetadata, "clientMetadata");
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            return WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkidokServiceKt.VkidokService().vkidokStartRegistration(platform, lang, clientMetadata, deviceId)).allowNoAuth().setAnonymous(true), null, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016J&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Widgets;", "Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "<init>", "()V", "getUniWidgets", "Lio/reactivex/rxjava3/core/Observable;", "Lorg/json/JSONObject;", "sendCallbackEvent", "Lio/reactivex/rxjava3/core/Single;", "", "peerId", "", Constants.WIDGET_ID_PARAMETER, "", "action", "Lcom/vk/superapp/api/dto/widgets/actions/WebActionCallback;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Widgets implements SuperappApi.Widgets {
        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONObject ipakvmoca(JSONObject jSONObject) {
            return jSONObject.getJSONObject("response");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmocb(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Widgets
        @NotNull
        public Observable<JSONObject> getUniWidgets() {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toJsonWebApiRequest(ExploreServiceKt.ExploreService().exploreGetWidgetsTest(CollectionsKt.listOf(BaseUserGroupFieldsDto.PHOTO_BASE))), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.d9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Widgets.ipakvmoca((JSONObject) obj);
                }
            };
            Observable<JSONObject> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.e9
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Widgets.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Widgets
        @NotNull
        public Single<Boolean> sendCallbackEvent(@NotNull String peerId, int widgetId, @NotNull WebActionCallback action) {
            Intrinsics.checkNotNullParameter(peerId, "peerId");
            Intrinsics.checkNotNullParameter(action, "action");
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(WidgetsKitServiceKt.WidgetsKitService().widgetsKitSendCallbackEvent(widgetId, peerId, action.getPayload())), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.f9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Widgets.ipakvmoca((BaseOkResponseDto) obj);
                }
            };
            Single<Boolean> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.g9
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Widgets.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final JSONObject ipakvmoca(Function1 function1, Object obj) {
            return (JSONObject) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean ipakvmoca(BaseOkResponseDto baseOkResponseDto) {
            return Boolean.valueOf(baseOkResponseDto == BaseOkResponseDto.OK);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$Birthday;", "Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "<init>", "()V", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/vk/superapp/api/dto/birthday/SuperAppBirthdayResponse;", "getBirthday", "()Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static class Birthday implements SuperappApi.Birthday {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.m3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.Birthday.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final SuperAppBirthdayResponse ipakvmoca(Birthday birthday, SuperAppGetBirthdayResponseDto superAppGetBirthdayResponseDto) {
            BirthdayMapper birthdayMapper = (BirthdayMapper) birthday.ipakvmoca.getValue();
            Intrinsics.checkNotNull(superAppGetBirthdayResponseDto);
            return birthdayMapper.map(superAppGetBirthdayResponseDto);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.Birthday
        @NotNull
        public Observable<SuperAppBirthdayResponse> getBirthday() {
            Observable uiObservable$default = WebApiRequest.toUiObservable$default(ApiCallExtKt.toWebApiRequest(SuperAppServiceKt.SuperAppService().superAppGetBirthday()), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.k3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.Birthday.ipakvmoca(this.f51830a, (SuperAppGetBirthdayResponseDto) obj);
                }
            };
            Observable<SuperAppBirthdayResponse> map = uiObservable$default.map(new Function() { // from class: com.vk.superapp.api.contract.l3
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.Birthday.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BirthdayMapper ipakvmoca() {
            return new BirthdayMapper(new CommonMapper(), new ImageMapper());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SuperAppBirthdayResponse ipakvmoca(Function1 function1, Object obj) {
            return (SuperAppBirthdayResponse) function1.invoke(obj);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes12.dex */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJU\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/vk/superapp/api/contract/GeneratedSuperappApi$VkRun;", "Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "<init>", "()V", "", "Lcom/vk/external/miniapp/net/vkrun/StepCounterInfo;", "list", "", "source", "", "canSendManualData", "Lio/reactivex/rxjava3/core/Single;", "importSteps", "(Ljava/util/List;Ljava/lang/String;Z)Lio/reactivex/rxjava3/core/Single;", "", "steps", "", "distanceKm", "manualSteps", "manualDistanceKm", "Lcom/google/gson/JsonObject;", ErrorBundle.DETAIL_ENTRY, "Lcom/vk/superapp/api/internal/requests/vkrun/VkRunStepsResponse;", "setSteps", "(IFIFLjava/lang/String;ZLjava/util/List;)Lio/reactivex/rxjava3/core/Single;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGeneratedSuperappApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GeneratedSuperappApi.kt\ncom/vk/superapp/api/contract/GeneratedSuperappApi$VkRun\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3566:1\n1#2:3567\n*E\n"})
    public static final class VkRun implements SuperappApi.VkRun {

        @NotNull
        private final Lazy ipakvmoca = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.api.contract.r8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratedSuperappApi.VkRun.ipakvmoca();
            }
        });

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(VkRun vkRun, List list) {
            VkRunMapper vkRunMapper = (VkRunMapper) vkRun.ipakvmoca.getValue();
            Intrinsics.checkNotNull(list);
            return vkRunMapper.mapDtoToStepsCounterInfo(list);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkRunStepsResponse ipakvmocb(Function1 function1, Object obj) {
            return (VkRunStepsResponse) function1.invoke(obj);
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkRun
        @NotNull
        public Single<List<StepCounterInfo>> importSteps(@NotNull List<StepCounterInfo> list, @NotNull String source, boolean canSendManualData) {
            VkRunImportSourceDto vkRunImportSourceDto;
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(source, "source");
            VkRunService VkRunService = VkRunServiceKt.VkRunService();
            List<VkRunStepsListItemDto> listMapStepsCounterInfoToDto = ((VkRunMapper) this.ipakvmoca.getValue()).mapStepsCounterInfoToDto(list, canSendManualData);
            VkRunImportSourceDto[] vkRunImportSourceDtoArrValues = VkRunImportSourceDto.values();
            int length = vkRunImportSourceDtoArrValues.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    vkRunImportSourceDto = null;
                    break;
                }
                vkRunImportSourceDto = vkRunImportSourceDtoArrValues[i10];
                if (Intrinsics.areEqual(vkRunImportSourceDto.getValue(), source)) {
                    break;
                }
                i10++;
            }
            if (vkRunImportSourceDto == null) {
                vkRunImportSourceDto = VkRunImportSourceDto.BACKGROUND_SYNC;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkRunService.DefaultImpls.vkRunImport$default(VkRunService, listMapStepsCounterInfoToDto, null, vkRunImportSourceDto, null, 10, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.n8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkRun.ipakvmoca(this.f51855a, (List) obj);
                }
            };
            Single<List<StepCounterInfo>> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.o8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkRun.ipakvmoca(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        @Override // com.vk.superapp.api.contract.SuperappApi.VkRun
        @NotNull
        public Single<VkRunStepsResponse> setSteps(int steps, float distanceKm, int manualSteps, float manualDistanceKm, @NotNull String source, boolean canSendManualData, @Nullable List<JsonObject> details) {
            VkRunSetStepsSourceDto vkRunSetStepsSourceDto;
            Intrinsics.checkNotNullParameter(source, "source");
            VkRunService VkRunService = VkRunServiceKt.VkRunService();
            String str = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date(System.currentTimeMillis()));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            VkRunMapper.Companion companion = VkRunMapper.INSTANCE;
            int iFromKmToM = companion.fromKmToM(distanceKm);
            Integer numValueOf = canSendManualData ? Integer.valueOf(manualSteps) : null;
            Integer numValueOf2 = canSendManualData ? Integer.valueOf(companion.fromKmToM(manualDistanceKm)) : null;
            VkRunSetStepsSourceDto[] vkRunSetStepsSourceDtoArrValues = VkRunSetStepsSourceDto.values();
            int length = vkRunSetStepsSourceDtoArrValues.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    vkRunSetStepsSourceDto = null;
                    break;
                }
                vkRunSetStepsSourceDto = vkRunSetStepsSourceDtoArrValues[i10];
                if (Intrinsics.areEqual(vkRunSetStepsSourceDto.getValue(), source)) {
                    break;
                }
                i10++;
            }
            if (vkRunSetStepsSourceDto == null) {
                vkRunSetStepsSourceDto = VkRunSetStepsSourceDto.BACKGROUND_SYNC;
            }
            Single uiSingle$default = WebApiRequest.toUiSingle$default(ApiCallExtKt.toWebApiRequest(VkRunService.DefaultImpls.vkRunSetSteps$default(VkRunService, str, steps, iFromKmToM, numValueOf, numValueOf2, null, vkRunSetStepsSourceDto, details, null, 288, null)), null, 1, null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.api.contract.p8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratedSuperappApi.VkRun.ipakvmoca((VkRunSetStepsResponseDto) obj);
                }
            };
            Single<VkRunStepsResponse> map = uiSingle$default.map(new Function() { // from class: com.vk.superapp.api.contract.q8
                @Override // io.reactivex.rxjava3.functions.Function
                public final Object apply(Object obj) {
                    return GeneratedSuperappApi.VkRun.ipakvmocb(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkRunMapper ipakvmoca() {
            return new VkRunMapper();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List ipakvmoca(Function1 function1, Object obj) {
            return (List) function1.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final VkRunStepsResponse ipakvmoca(VkRunSetStepsResponseDto vkRunSetStepsResponseDto) {
            return new VkRunStepsResponse(vkRunSetStepsResponseDto.getSteps(), VkRunMapper.INSTANCE.fromMToKm(Integer.valueOf(vkRunSetStepsResponseDto.getDistance())));
        }
    }
}
