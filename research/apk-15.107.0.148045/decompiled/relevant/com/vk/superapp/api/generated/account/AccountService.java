package com.vk.superapp.api.generated.account;

import com.google.firebase.messaging.Constants;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.huawei.hms.push.AttributionReporter;
import com.vk.api.generated.account.dto.AccountAccountCountersDto;
import com.vk.api.generated.account.dto.AccountCheckPasswordResponseDto;
import com.vk.api.generated.account.dto.AccountCountersFilterDto;
import com.vk.api.generated.account.dto.AccountGetEmailResponseDto;
import com.vk.api.generated.account.dto.AccountGetInfoFieldsDto;
import com.vk.api.generated.account.dto.AccountGetMultiResponseDto;
import com.vk.api.generated.account.dto.AccountGetPhoneResponseDto;
import com.vk.api.generated.account.dto.AccountGetProfileNavigationInfoResponseDto;
import com.vk.api.generated.account.dto.AccountGetProfilesSwitcherInfoResponseDto;
import com.vk.api.generated.account.dto.AccountGetTogglesAnonymResponseDto;
import com.vk.api.generated.account.dto.AccountGetTogglesResponseDto;
import com.vk.api.generated.account.dto.AccountGetUserObjectDto;
import com.vk.api.generated.account.dto.AccountInfoDto;
import com.vk.api.generated.account.dto.AccountManagePushDeviceMultiActionsDto;
import com.vk.api.generated.account.dto.AccountManagePushDeviceMultiPushProviderDto;
import com.vk.api.generated.account.dto.AccountManagePushDeviceMultiResponseDto;
import com.vk.api.generated.account.dto.AccountManagePushDeviceMultiTypesDto;
import com.vk.api.generated.account.dto.AccountMarkActualizeEmailActionDto;
import com.vk.api.generated.account.dto.AccountUserSettingsDto;
import com.vk.api.generated.base.dto.BaseBoolIntDto;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.core.ApiMethodCall;
import com.vk.api.generated.core.ApiResponseParser;
import com.vk.api.generated.core.ApiStreamResponseParser;
import com.vk.api.generated.core.RootResponseDto;
import com.vk.dto.common.id.UserId;
import com.vk.push.core.analytics.AnalyticsBaseParamsConstantsKt;
import com.vk.superapp.api.generated.GsonHolder;
import com.vk.superapp.api.generated.InternalApiMethodCall;
import com.vk.superapp.api.generated.SingleRootResponseDto;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import d.detarenegipakvmoca;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.MailApplication;
import ru.mail.cloud.app.viewer.ui.ViewerActivity;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.news_feed.util.pulsedeeplinks.ActionParser;
import ru.mail.smoothie.domain.web.load.usecase.InjectNativeParamToAppConfigUseCase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001:\babcdefghJX\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0016JP\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\f2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0016JI\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJI\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJI\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010\u001dJ7\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010$J \u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00032\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\fH\u0016J1\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010+J7\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010$J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u0003H\u0016J\u001a\u00100\u001a\b\u0012\u0004\u0012\u0002010\u00032\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0006H\u0016J\u001c\u00103\u001a\b\u0012\u0004\u0012\u0002040\u00032\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0016JI\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00032\u0010\b\u0002\u00108\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u00032\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0016J£\u0002\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00032\u0006\u0010\u0019\u001a\u00020\u00062\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\f2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\f2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010M2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u001c2\u0010\b\u0002\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\u0016\b\u0002\u0010Q\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\f\u0018\u00010\f2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u001cH\u0016¢\u0006\u0002\u0010VJ\u0016\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u00032\u0006\u0010Y\u001a\u00020ZH\u0016J\u000e\u0010[\u001a\b\u0012\u0004\u0012\u00020X0\u0003H\u0016J\"\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u00032\u0006\u0010#\u001a\u00020\r2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010^\u001a\b\u0012\u0004\u0012\u00020X0\u0003H\u0016J\u000e\u0010_\u001a\b\u0012\u0004\u0012\u00020X0\u0003H\u0016J\u0016\u0010`\u001a\b\u0012\u0004\u0012\u00020X0\u00032\u0006\u0010\t\u001a\u00020\u0006H\u0016¨\u0006i"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService;", "", "accountCheckPassword", "Lcom/vk/api/generated/core/ApiMethodCall;", "Lcom/vk/api/generated/account/dto/AccountCheckPasswordResponseDto;", "password", "", "lastName", "firstName", "birthday", "phone", "checks", "", "", "accountGet", "Lcom/vk/api/generated/account/dto/AccountGetUserObjectDto;", "userIds", "Lcom/vk/dto/common/id/UserId;", "fields", "nameCase", "serviceToken", "accountGetCounters", "Lcom/vk/api/generated/account/dto/AccountAccountCountersDto;", ViewerActivity.FILTER, "Lcom/vk/api/generated/account/dto/AccountCountersFilterDto;", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, BlockstoreDeleteReceiver.PARAM_USER_ID, "forCoupled", "", "(Ljava/util/List;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "getCounters", "secureGetCounters", "accountGetEmail", "Lcom/vk/api/generated/account/dto/AccountGetEmailResponseDto;", "groupId", "appId", "(Lcom/vk/dto/common/id/UserId;Lcom/vk/dto/common/id/UserId;Ljava/lang/Integer;)Lcom/vk/api/generated/core/ApiMethodCall;", "accountGetInfo", "Lcom/vk/api/generated/account/dto/AccountInfoDto;", "Lcom/vk/api/generated/account/dto/AccountGetInfoFieldsDto;", "accountGetMulti", "Lcom/vk/api/generated/account/dto/AccountGetMultiResponseDto;", "needServiceMa", "(Ljava/util/List;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "accountGetPhone", "Lcom/vk/api/generated/account/dto/AccountGetPhoneResponseDto;", "accountGetProfileNavigationInfo", "Lcom/vk/api/generated/account/dto/AccountGetProfileNavigationInfoResponseDto;", "accountGetProfileShortInfo", "Lcom/vk/api/generated/account/dto/AccountUserSettingsDto;", "superAppToken", "accountGetProfilesSwitcherInfo", "Lcom/vk/api/generated/account/dto/AccountGetProfilesSwitcherInfoResponseDto;", "accessTokens", "accountGetToggles", "Lcom/vk/api/generated/account/dto/AccountGetTogglesResponseDto;", "toggles", "version", EventParams.HASH, "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;)Lcom/vk/api/generated/core/ApiMethodCall;", "accountGetTogglesAnonym", "Lcom/vk/api/generated/account/dto/AccountGetTogglesAnonymResponseDto;", "accountManagePushDeviceMulti", "Lcom/vk/api/generated/account/dto/AccountManagePushDeviceMultiResponseDto;", ActionParser.KEY_MULTIPLE_ACTIONS, "Lcom/vk/api/generated/account/dto/AccountManagePushDeviceMultiActionsDto;", "types", "Lcom/vk/api/generated/account/dto/AccountManagePushDeviceMultiTypesDto;", "token", "tokenVoip", "deviceModel", "deviceYear", "tokenSig", InjectNativeParamToAppConfigUseCase.SYSTEM_VERSION_KEY, AttributionReporter.APP_VERSION, Constants.MessageTypes.MESSAGE, "pushProvider", "Lcom/vk/api/generated/account/dto/AccountManagePushDeviceMultiPushProviderDto;", "settings", "sandbox", "companionApps", "companionAppsArray", "hasGoogleServices", "reset", "statOnlyActiveAccount", "pushesGranted", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/account/dto/AccountManagePushDeviceMultiPushProviderDto;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "accountMarkActualizeEmail", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "action", "Lcom/vk/api/generated/account/dto/AccountMarkActualizeEmailActionDto;", "accountMarkActualizePhone", "accountNeedServicePolicy", "Lcom/vk/api/generated/base/dto/BaseBoolIntDto;", "accountUnmarkActualizeEmail", "accountUnmarkActualizePhone", "accountValidateBirthday", "AccountCheckPasswordRestrictions", "AccountGetCountersRestrictions", "GetCountersRestrictions", "SecureGetCountersRestrictions", "AccountGetEmailRestrictions", "AccountGetPhoneRestrictions", "AccountGetTogglesRestrictions", "AccountMarkActualizeEmailRestrictions", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface AccountService {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountCheckPasswordRestrictions;", "", "<init>", "()V", "LAST_NAME_MAX_LENGTH", "", "FIRST_NAME_MAX_LENGTH", "BIRTHDAY_MAX_LENGTH", "PHONE_MAX_LENGTH", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountCheckPasswordRestrictions {
        public static final int BIRTHDAY_MAX_LENGTH = 10;
        public static final int FIRST_NAME_MAX_LENGTH = 160;

        @NotNull
        public static final AccountCheckPasswordRestrictions INSTANCE = new AccountCheckPasswordRestrictions();
        public static final int LAST_NAME_MAX_LENGTH = 160;
        public static final int PHONE_MAX_LENGTH = 30;

        private AccountCheckPasswordRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountGetCountersRestrictions;", "", "<init>", "()V", "USER_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountGetCountersRestrictions {

        @NotNull
        public static final AccountGetCountersRestrictions INSTANCE = new AccountGetCountersRestrictions();
        public static final long USER_ID_MIN = 0;

        private AccountGetCountersRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountGetEmailRestrictions;", "", "<init>", "()V", "GROUP_ID_MIN", "", "USER_ID_MIN", "APP_ID_MIN", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountGetEmailRestrictions {
        public static final long APP_ID_MIN = 0;
        public static final long GROUP_ID_MIN = 0;

        @NotNull
        public static final AccountGetEmailRestrictions INSTANCE = new AccountGetEmailRestrictions();
        public static final long USER_ID_MIN = 0;

        private AccountGetEmailRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountGetPhoneRestrictions;", "", "<init>", "()V", "GROUP_ID_MIN", "", "USER_ID_MIN", "APP_ID_MIN", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountGetPhoneRestrictions {
        public static final long APP_ID_MIN = 0;
        public static final long GROUP_ID_MIN = 0;

        @NotNull
        public static final AccountGetPhoneRestrictions INSTANCE = new AccountGetPhoneRestrictions();
        public static final long USER_ID_MIN = 0;

        private AccountGetPhoneRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountGetTogglesRestrictions;", "", "<init>", "()V", "USER_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountGetTogglesRestrictions {

        @NotNull
        public static final AccountGetTogglesRestrictions INSTANCE = new AccountGetTogglesRestrictions();
        public static final long USER_ID_MIN = 0;

        private AccountGetTogglesRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$AccountMarkActualizeEmailRestrictions;", "", "<init>", "()V", "ACTION_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AccountMarkActualizeEmailRestrictions {
        public static final long ACTION_MIN = 0;

        @NotNull
        public static final AccountMarkActualizeEmailRestrictions INSTANCE = new AccountMarkActualizeEmailRestrictions();

        private AccountMarkActualizeEmailRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$GetCountersRestrictions;", "", "<init>", "()V", "USER_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class GetCountersRestrictions {

        @NotNull
        public static final GetCountersRestrictions INSTANCE = new GetCountersRestrictions();
        public static final long USER_ID_MIN = 0;

        private GetCountersRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/account/AccountService$SecureGetCountersRestrictions;", "", "<init>", "()V", "USER_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SecureGetCountersRestrictions {

        @NotNull
        public static final SecureGetCountersRestrictions INSTANCE = new SecureGetCountersRestrictions();
        public static final long USER_ID_MIN = 0;

        private SecureGetCountersRestrictions() {
        }
    }

    @NotNull
    ApiMethodCall<AccountCheckPasswordResponseDto> accountCheckPassword(@NotNull String password, @Nullable String lastName, @Nullable String firstName, @Nullable String birthday, @Nullable String phone, @Nullable List<Integer> checks);

    @NotNull
    ApiMethodCall<List<AccountGetUserObjectDto>> accountGet(@Nullable List<UserId> userIds, @Nullable List<String> fields, @Nullable String nameCase, @Nullable String serviceToken);

    @NotNull
    ApiMethodCall<AccountAccountCountersDto> accountGetCounters(@Nullable List<? extends AccountCountersFilterDto> filter, @Nullable String deviceId, @Nullable UserId userId, @Nullable Boolean forCoupled);

    @NotNull
    ApiMethodCall<AccountGetEmailResponseDto> accountGetEmail(@Nullable UserId groupId, @Nullable UserId userId, @Nullable Integer appId);

    @NotNull
    ApiMethodCall<AccountInfoDto> accountGetInfo(@Nullable List<? extends AccountGetInfoFieldsDto> fields);

    @NotNull
    ApiMethodCall<AccountGetMultiResponseDto> accountGetMulti(@Nullable List<String> fields, @Nullable Boolean needServiceMa);

    @NotNull
    ApiMethodCall<AccountGetPhoneResponseDto> accountGetPhone(@Nullable UserId groupId, @Nullable UserId userId, @Nullable Integer appId);

    @NotNull
    ApiMethodCall<AccountGetProfileNavigationInfoResponseDto> accountGetProfileNavigationInfo();

    @NotNull
    ApiMethodCall<AccountUserSettingsDto> accountGetProfileShortInfo(@Nullable String superAppToken);

    @NotNull
    ApiMethodCall<AccountGetProfilesSwitcherInfoResponseDto> accountGetProfilesSwitcherInfo(@NotNull List<String> accessTokens);

    @NotNull
    ApiMethodCall<AccountGetTogglesResponseDto> accountGetToggles(@Nullable List<String> toggles, @Nullable Integer version, @Nullable String hash, @Nullable UserId userId);

    @NotNull
    ApiMethodCall<AccountGetTogglesAnonymResponseDto> accountGetTogglesAnonym(@NotNull List<String> toggles);

    @NotNull
    ApiMethodCall<AccountManagePushDeviceMultiResponseDto> accountManagePushDeviceMulti(@NotNull String deviceId, @NotNull List<String> accessTokens, @NotNull List<? extends AccountManagePushDeviceMultiActionsDto> actions, @NotNull List<? extends AccountManagePushDeviceMultiTypesDto> types, @Nullable String token, @Nullable String tokenVoip, @Nullable String deviceModel, @Nullable Integer deviceYear, @Nullable String tokenSig, @Nullable String systemVersion, @Nullable String appVersion, @Nullable Integer gcm, @Nullable AccountManagePushDeviceMultiPushProviderDto pushProvider, @Nullable String settings, @Nullable Boolean sandbox, @Nullable List<String> companionApps, @Nullable List<? extends List<String>> companionAppsArray, @Nullable Boolean hasGoogleServices, @Nullable Boolean reset, @Nullable Boolean statOnlyActiveAccount, @Nullable Boolean pushesGranted);

    @NotNull
    ApiMethodCall<BaseOkResponseDto> accountMarkActualizeEmail(@NotNull AccountMarkActualizeEmailActionDto action);

    @NotNull
    ApiMethodCall<BaseOkResponseDto> accountMarkActualizePhone();

    @NotNull
    ApiMethodCall<BaseBoolIntDto> accountNeedServicePolicy(int appId, @Nullable String superAppToken);

    @NotNull
    ApiMethodCall<BaseOkResponseDto> accountUnmarkActualizeEmail();

    @NotNull
    ApiMethodCall<BaseOkResponseDto> accountUnmarkActualizePhone();

    @NotNull
    ApiMethodCall<BaseOkResponseDto> accountValidateBirthday(@NotNull String birthday);

    @NotNull
    ApiMethodCall<AccountAccountCountersDto> getCounters(@Nullable List<? extends AccountCountersFilterDto> filter, @Nullable String deviceId, @Nullable UserId userId, @Nullable Boolean forCoupled);

    @NotNull
    ApiMethodCall<AccountAccountCountersDto> secureGetCounters(@Nullable List<? extends AccountCountersFilterDto> filter, @Nullable String deviceId, @Nullable UserId userId, @Nullable Boolean forCoupled);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAccountService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AccountService.kt\ncom/vk/superapp/api/generated/account/AccountService$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 GsonExt.kt\ncom/vk/superapp/api/generated/GsonExtKt\n*L\n1#1,551:1\n1#2:552\n1563#3:553\n1634#3,3:554\n1563#3:557\n1634#3,3:558\n1563#3:561\n1634#3,3:562\n1563#3:565\n1634#3,3:566\n1563#3:569\n1634#3,3:570\n1563#3:573\n1634#3,3:574\n45#4,2:577\n49#4,2:579\n53#4,5:581\n61#4,4:586\n45#4,2:590\n49#4,2:592\n45#4,2:594\n49#4,2:596\n45#4,2:598\n49#4,2:600\n45#4,2:602\n49#4,2:604\n45#4,2:606\n49#4,2:608\n45#4,2:610\n49#4,2:612\n45#4,2:614\n49#4,2:616\n45#4,2:618\n49#4,2:620\n45#4,2:622\n49#4,2:624\n45#4,2:626\n49#4,2:628\n45#4,2:630\n49#4,2:632\n45#4,2:634\n49#4,2:636\n45#4,2:638\n49#4,2:640\n45#4,2:642\n49#4,2:644\n45#4,2:646\n49#4,2:648\n45#4,2:650\n49#4,2:652\n45#4,2:654\n49#4,2:656\n45#4,2:658\n49#4,2:660\n45#4,2:662\n49#4,2:664\n*S KotlinDebug\n*F\n+ 1 AccountService.kt\ncom/vk/superapp/api/generated/account/AccountService$DefaultImpls\n*L\n142#1:553\n142#1:554,3\n170#1:557\n170#1:558,3\n198#1:561\n198#1:562,3\n242#1:565\n242#1:566,3\n409#1:569\n409#1:570,3\n413#1:573\n413#1:574,3\n89#1:577,2\n90#1:579,2\n114#1:581,5\n115#1:586,4\n139#1:590,2\n140#1:592,2\n167#1:594,2\n168#1:596,2\n195#1:598,2\n196#1:600,2\n219#1:602,2\n220#1:604,2\n239#1:606,2\n240#1:608,2\n256#1:610,2\n257#1:612,2\n275#1:614,2\n276#1:616,2\n291#1:618,2\n292#1:620,2\n301#1:622,2\n302#1:624,2\n314#1:626,2\n315#1:628,2\n334#1:630,2\n335#1:632,2\n350#1:634,2\n351#1:636,2\n404#1:638,2\n405#1:640,2\n443#1:642,2\n444#1:644,2\n454#1:646,2\n455#1:648,2\n465#1:650,2\n466#1:652,2\n477#1:654,2\n478#1:656,2\n485#1:658,2\n486#1:660,2\n497#1:662,2\n498#1:664,2\n*E\n"})
    public static final class DefaultImpls {
        @NotNull
        public static ApiMethodCall<AccountCheckPasswordResponseDto> accountCheckPassword(@NotNull AccountService accountService, @NotNull String password, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable List<Integer> list) {
            Intrinsics.checkNotNullParameter(password, "password");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.checkPassword", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.a
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoca(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.l
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoca(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "password", password, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "last_name", str, 0, 160, 4, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "first_name", str2, 0, 160, 4, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "birthday", str3, 0, 10, 4, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", str4, 0, 30, 4, (Object) null);
            }
            if (list != null) {
                internalApiMethodCall.addParam("checks", list);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountCheckPassword$default(AccountService accountService, String str, String str2, String str3, String str4, String str5, List list, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountCheckPassword");
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                str3 = null;
            }
            if ((i10 & 8) != 0) {
                str4 = null;
            }
            if ((i10 & 16) != 0) {
                str5 = null;
            }
            if ((i10 & 32) != 0) {
                list = null;
            }
            return accountService.accountCheckPassword(str, str2, str3, str4, str5, list);
        }

        @NotNull
        public static ApiMethodCall<List<AccountGetUserObjectDto>> accountGet(@NotNull AccountService accountService, @Nullable List<UserId> list, @Nullable List<String> list2, @Nullable String str, @Nullable String str2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.get", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.m
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocb(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.n
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocb(inputStream);
                }
            });
            if (list != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_ids", list, 0L, 0L, 12, (Object) null);
            }
            if (list2 != null) {
                internalApiMethodCall.addParam("fields", list2);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "name_case", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "service_token", str2, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountGet$default(AccountService accountService, List list, List list2, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGet");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                list2 = null;
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            if ((i10 & 8) != 0) {
                str2 = null;
            }
            return accountService.accountGet(list, list2, str, str2);
        }

        @NotNull
        public static ApiMethodCall<AccountAccountCountersDto> accountGetCounters(@NotNull AccountService accountService, @Nullable List<? extends AccountCountersFilterDto> list, @Nullable String str, @Nullable UserId userId, @Nullable Boolean bool) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getCounters", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.m0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocc(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.n0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocc(inputStream);
                }
            });
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AccountCountersFilterDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall.addParam(ViewerActivity.FILTER, arrayList);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId, 0L, 0L, 8, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("for_coupled", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountGetCounters$default(AccountService accountService, List list, String str, UserId userId, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetCounters");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                userId = null;
            }
            if ((i10 & 8) != 0) {
                bool = null;
            }
            return accountService.accountGetCounters(list, str, userId, bool);
        }

        @NotNull
        public static ApiMethodCall<AccountGetEmailResponseDto> accountGetEmail(@NotNull AccountService accountService, @Nullable UserId userId, @Nullable UserId userId2, @Nullable Integer num) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getEmail", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.u
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocd(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.v
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocd(inputStream);
                }
            });
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "group_id", userId, 0L, 0L, 8, (Object) null);
            }
            if (userId2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId2, 0L, 0L, 8, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", num.intValue(), 0, 0, 8, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall accountGetEmail$default(AccountService accountService, UserId userId, UserId userId2, Integer num, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetEmail");
            }
            if ((i10 & 1) != 0) {
                userId = null;
            }
            if ((i10 & 2) != 0) {
                userId2 = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            return accountService.accountGetEmail(userId, userId2, num);
        }

        @NotNull
        public static ApiMethodCall<AccountInfoDto> accountGetInfo(@NotNull AccountService accountService, @Nullable List<? extends AccountGetInfoFieldsDto> list) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.z
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoce(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.a0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoce(inputStream);
                }
            });
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AccountGetInfoFieldsDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall.addParam("fields", arrayList);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountGetInfo$default(AccountService accountService, List list, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetInfo");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            return accountService.accountGetInfo(list);
        }

        @NotNull
        public static ApiMethodCall<AccountGetMultiResponseDto> accountGetMulti(@NotNull AccountService accountService, @Nullable List<String> list, @Nullable Boolean bool) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getMulti", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.h
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocf(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.i
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocf(inputStream);
                }
            });
            if (list != null) {
                internalApiMethodCall.addParam("fields", list);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("need_service_ma", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountGetMulti$default(AccountService accountService, List list, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetMulti");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                bool = null;
            }
            return accountService.accountGetMulti(list, bool);
        }

        @NotNull
        public static ApiMethodCall<AccountGetPhoneResponseDto> accountGetPhone(@NotNull AccountService accountService, @Nullable UserId userId, @Nullable UserId userId2, @Nullable Integer num) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getPhone", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.q
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocg(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.r
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocg(inputStream);
                }
            });
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "group_id", userId, 0L, 0L, 8, (Object) null);
            }
            if (userId2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId2, 0L, 0L, 8, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", num.intValue(), 0, 0, 8, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall accountGetPhone$default(AccountService accountService, UserId userId, UserId userId2, Integer num, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetPhone");
            }
            if ((i10 & 1) != 0) {
                userId = null;
            }
            if ((i10 & 2) != 0) {
                userId2 = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            return accountService.accountGetPhone(userId, userId2, num);
        }

        @NotNull
        public static ApiMethodCall<AccountGetProfileNavigationInfoResponseDto> accountGetProfileNavigationInfo(@NotNull AccountService accountService) {
            return new InternalApiMethodCall("account.getProfileNavigationInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.b0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoch(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.c0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoch(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<AccountUserSettingsDto> accountGetProfileShortInfo(@NotNull AccountService accountService, @Nullable String str) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getProfileShortInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.j
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoci(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.k
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoci(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall accountGetProfileShortInfo$default(AccountService accountService, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetProfileShortInfo");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            return accountService.accountGetProfileShortInfo(str);
        }

        @NotNull
        public static ApiMethodCall<AccountGetProfilesSwitcherInfoResponseDto> accountGetProfilesSwitcherInfo(@NotNull AccountService accountService, @NotNull List<String> accessTokens) {
            Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getProfilesSwitcherInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.o
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocj(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.p
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocj(inputStream);
                }
            });
            internalApiMethodCall.addParam("access_tokens", accessTokens);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AccountGetTogglesResponseDto> accountGetToggles(@NotNull AccountService accountService, @Nullable List<String> list, @Nullable Integer num, @Nullable String str, @Nullable UserId userId) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getToggles", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.d
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmock(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.e
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmock(inputStream);
                }
            });
            if (list != null) {
                internalApiMethodCall.addParam("toggles", list);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "version", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, EventParams.HASH, str, 0, 0, 12, (Object) null);
            }
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId, 0L, 0L, 8, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountGetToggles$default(AccountService accountService, List list, Integer num, String str, UserId userId, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountGetToggles");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                num = null;
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            if ((i10 & 8) != 0) {
                userId = null;
            }
            return accountService.accountGetToggles(list, num, str, userId);
        }

        @NotNull
        public static ApiMethodCall<AccountGetTogglesAnonymResponseDto> accountGetTogglesAnonym(@NotNull AccountService accountService, @NotNull List<String> toggles) {
            Intrinsics.checkNotNullParameter(toggles, "toggles");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.getTogglesAnonym", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.s
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocl(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.t
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocl(inputStream);
                }
            });
            internalApiMethodCall.addParam("toggles", toggles);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AccountManagePushDeviceMultiResponseDto> accountManagePushDeviceMulti(@NotNull AccountService accountService, @NotNull String deviceId, @NotNull List<String> accessTokens, @NotNull List<? extends AccountManagePushDeviceMultiActionsDto> actions, @NotNull List<? extends AccountManagePushDeviceMultiTypesDto> types, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Integer num2, @Nullable AccountManagePushDeviceMultiPushProviderDto accountManagePushDeviceMultiPushProviderDto, @Nullable String str7, @Nullable Boolean bool, @Nullable List<String> list, @Nullable List<? extends List<String>> list2, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable Boolean bool5) {
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
            Intrinsics.checkNotNullParameter(actions, "actions");
            Intrinsics.checkNotNullParameter(types, "types");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.managePushDeviceMulti", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.b
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocm(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.c
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocm(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", deviceId, 0, 0, 12, (Object) null);
            internalApiMethodCall.addParam("access_tokens", accessTokens);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(actions, 10));
            Iterator<T> it = actions.iterator();
            while (it.hasNext()) {
                arrayList.add(((AccountManagePushDeviceMultiActionsDto) it.next()).getValue());
            }
            internalApiMethodCall.addParam(ActionParser.KEY_MULTIPLE_ACTIONS, arrayList);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));
            Iterator<T> it2 = types.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((AccountManagePushDeviceMultiTypesDto) it2.next()).getValue());
            }
            internalApiMethodCall.addParam("types", arrayList2);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "token_voip", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, AnalyticsBaseParamsConstantsKt.DEVICE_MODEL, str3, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_year", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "token_sig", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "system_version", str5, 0, 0, 12, (Object) null);
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, MailApplication.KEY_PREF_APP_VERSION, str6, 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, Constants.MessageTypes.MESSAGE, num2.intValue(), 0, 0, 12, (Object) null);
            }
            if (accountManagePushDeviceMultiPushProviderDto != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "push_provider", accountManagePushDeviceMultiPushProviderDto.getValue(), 0, 0, 12, (Object) null);
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "settings", str7, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("sandbox", bool.booleanValue());
            }
            if (list != null) {
                internalApiMethodCall.addParam("companion_apps", list);
            }
            if (list2 != null) {
                internalApiMethodCall.addParam("companion_apps_array", list2);
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("has_google_services", bool2.booleanValue());
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("reset", bool3.booleanValue());
            }
            if (bool4 != null) {
                internalApiMethodCall.addParam("stat_only_active_account", bool4.booleanValue());
            }
            if (bool5 != null) {
                internalApiMethodCall.addParam("pushes_granted", bool5.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall accountManagePushDeviceMulti$default(AccountService accountService, String str, List list, List list2, List list3, String str2, String str3, String str4, Integer num, String str5, String str6, String str7, Integer num2, AccountManagePushDeviceMultiPushProviderDto accountManagePushDeviceMultiPushProviderDto, String str8, Boolean bool, List list4, List list5, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i10, Object obj) {
            if (obj == null) {
                return accountService.accountManagePushDeviceMulti(str, list, list2, list3, (i10 & 16) != 0 ? null : str2, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : str4, (i10 & 128) != 0 ? null : num, (i10 & 256) != 0 ? null : str5, (i10 & 512) != 0 ? null : str6, (i10 & 1024) != 0 ? null : str7, (i10 & 2048) != 0 ? null : num2, (i10 & 4096) != 0 ? null : accountManagePushDeviceMultiPushProviderDto, (i10 & 8192) != 0 ? null : str8, (i10 & 16384) != 0 ? null : bool, (32768 & i10) != 0 ? null : list4, (65536 & i10) != 0 ? null : list5, (131072 & i10) != 0 ? null : bool2, (262144 & i10) != 0 ? null : bool3, (524288 & i10) != 0 ? null : bool4, (i10 & 1048576) != 0 ? null : bool5);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountManagePushDeviceMulti");
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> accountMarkActualizeEmail(@NotNull AccountService accountService, @NotNull AccountMarkActualizeEmailActionDto action) {
            Intrinsics.checkNotNullParameter(action, "action");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.markActualizeEmail", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.d0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocn(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.e0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocn(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "action", action.getValue(), 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> accountMarkActualizePhone(@NotNull AccountService accountService) {
            return new InternalApiMethodCall("account.markActualizePhone", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.x
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoco(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.y
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoco(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<BaseBoolIntDto> accountNeedServicePolicy(@NotNull AccountService accountService, int i10, @Nullable String str) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.needServicePolicy", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.k0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocp(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.l0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocp(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall accountNeedServicePolicy$default(AccountService accountService, int i10, String str, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: accountNeedServicePolicy");
            }
            if ((i11 & 2) != 0) {
                str = null;
            }
            return accountService.accountNeedServicePolicy(i10, str);
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> accountUnmarkActualizeEmail(@NotNull AccountService accountService) {
            return new InternalApiMethodCall("account.unmarkActualizeEmail", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.o0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocq(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.p0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocq(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> accountUnmarkActualizePhone(@NotNull AccountService accountService) {
            return new InternalApiMethodCall("account.unmarkActualizePhone", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.w
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocr(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.h0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocr(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> accountValidateBirthday(@NotNull AccountService accountService, @NotNull String birthday) {
            Intrinsics.checkNotNullParameter(birthday, "birthday");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("account.validateBirthday", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.f0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocs(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.g0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocs(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "birthday", birthday, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountCheckPasswordResponseDto detarenegipakvmoca(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountCheckPasswordResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountCheckPasswordResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List detarenegipakvmocb(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (List) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, TypeToken.getParameterized(List.class, AccountGetUserObjectDto.class).getType()).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountAccountCountersDto detarenegipakvmocc(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountAccountCountersDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountAccountCountersDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetEmailResponseDto detarenegipakvmocd(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetEmailResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetEmailResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountInfoDto detarenegipakvmoce(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountInfoDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountInfoDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetMultiResponseDto detarenegipakvmocf(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetMultiResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetMultiResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetPhoneResponseDto detarenegipakvmocg(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetPhoneResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetPhoneResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetProfileNavigationInfoResponseDto detarenegipakvmoch(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetProfileNavigationInfoResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetProfileNavigationInfoResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountUserSettingsDto detarenegipakvmoci(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountUserSettingsDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountUserSettingsDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetProfilesSwitcherInfoResponseDto detarenegipakvmocj(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetProfilesSwitcherInfoResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetProfilesSwitcherInfoResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetTogglesResponseDto detarenegipakvmock(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetTogglesResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetTogglesResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountGetTogglesAnonymResponseDto detarenegipakvmocl(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountGetTogglesAnonymResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountGetTogglesAnonymResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountManagePushDeviceMultiResponseDto detarenegipakvmocm(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountManagePushDeviceMultiResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountManagePushDeviceMultiResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocn(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmoco(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseBoolIntDto detarenegipakvmocp(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseBoolIntDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseBoolIntDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocq(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocr(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocs(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountAccountCountersDto detarenegipakvmoct(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountAccountCountersDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountAccountCountersDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AccountAccountCountersDto detarenegipakvmocu(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AccountAccountCountersDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AccountAccountCountersDto.class).getType())).getResponse();
        }

        @NotNull
        public static ApiMethodCall<AccountAccountCountersDto> getCounters(@NotNull AccountService accountService, @Nullable List<? extends AccountCountersFilterDto> list, @Nullable String str, @Nullable UserId userId, @Nullable Boolean bool) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("getCounters", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.i0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmoct(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.j0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmoct(inputStream);
                }
            });
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AccountCountersFilterDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall.addParam(ViewerActivity.FILTER, arrayList);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId, 0L, 0L, 8, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("for_coupled", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall getCounters$default(AccountService accountService, List list, String str, UserId userId, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCounters");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                userId = null;
            }
            if ((i10 & 8) != 0) {
                bool = null;
            }
            return accountService.getCounters(list, str, userId, bool);
        }

        @NotNull
        public static ApiMethodCall<AccountAccountCountersDto> secureGetCounters(@NotNull AccountService accountService, @Nullable List<? extends AccountCountersFilterDto> list, @Nullable String str, @Nullable UserId userId, @Nullable Boolean bool) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("secure.getCounters", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.account.f
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AccountService.DefaultImpls.detarenegipakvmocu(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.account.g
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AccountService.DefaultImpls.detarenegipakvmocu(inputStream);
                }
            });
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AccountCountersFilterDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall.addParam(ViewerActivity.FILTER, arrayList);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            if (userId != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "user_id", userId, 0L, 0L, 8, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("for_coupled", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall secureGetCounters$default(AccountService accountService, List list, String str, UserId userId, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: secureGetCounters");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                userId = null;
            }
            if ((i10 & 8) != 0) {
                bool = null;
            }
            return accountService.secureGetCounters(list, str, userId, bool);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoca(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountCheckPasswordResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocc(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountAccountCountersDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocd(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetEmailResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoce(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountInfoDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocf(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetMultiResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocg(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetPhoneResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoch(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetProfileNavigationInfoResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoci(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountUserSettingsDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocj(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetProfilesSwitcherInfoResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmock(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetTogglesResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocl(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountGetTogglesAnonymResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocm(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountManagePushDeviceMultiResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocn(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoco(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocp(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseBoolIntDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocq(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocr(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocs(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoct(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountAccountCountersDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocu(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AccountAccountCountersDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocb(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, TypeToken.getParameterized(List.class, AccountGetUserObjectDto.class).getType()), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }
    }
}
