package ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate;

import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.feature.authactivity.domain.interactor.AuthMailApiCommonResult;
import ru.mail.credentialsexchanger.analytics.AnalyticsConstants;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.log.InternalLogger;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ4\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0016H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthDelegate;", "", "analyticsSdk", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthRequestAnalytics;", "resources", "Lru/mail/android_utils/wrapper/Resources;", "baseLogger", "Lru/mail/util/log/InternalLogger;", "<init>", "(Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthRequestAnalytics;Lru/mail/android_utils/wrapper/Resources;Lru/mail/util/log/InternalLogger;)V", "log", BatchApiRequest.FIELD_NAME_ON_ERROR, "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "login", "", "password", "response", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse$Error;", "isEsiaBindType", "", "authFlowType", "getImapSettings", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse$Error$ApiError;", "isNeedCaptcha", "getSecondStepUrl", "getLudwigToken", "sendCgiBinAuthSecStepFlowAnalytics", "", "url", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthDelegate.kt\nru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthDelegate\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,306:1\n29#2:307\n29#2:308\n*S KotlinDebug\n*F\n+ 1 AuthDelegate.kt\nru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthDelegate\n*L\n262#1:307\n285#1:308\n*E\n"})
public final class AuthDelegate {

    @NotNull
    private static final String HEADER_CAPTCHA_REQUIRED = "X-SWA-CAPTCHA-REQUIRED";

    @NotNull
    private static final String HEADER_CAPTCHA_REQUIRED_VALUE = "1";

    @NotNull
    private static final String HEADER_EXTERNAL_ACCOUNT_REGISTRATION_ID = "X-SWA-UKEY";

    @NotNull
    private static final String HEADER_IMAP_SETTINGS = "X-SWA-IMAPSETTINGS";

    @NotNull
    private static final String HEADER_SOCIAL_TYPE = "x-swa-social-type";

    @NotNull
    private static final String LUDWIG_TOKEN_FIELD = "LudwigToken";

    @NotNull
    private static final String Ok0_ERROR = "Ok=0";

    @NotNull
    private static final String SECOND_STEP_URL = "Continue";

    @NotNull
    private static final String SET_COOKIE_HEADER = "Set-cookie";

    @NotNull
    private final AuthRequestAnalytics analyticsSdk;

    @NotNull
    private final InternalLogger log;

    @NotNull
    private final Resources resources;
    public static final int $stable = 8;

    public AuthDelegate(@NotNull AuthRequestAnalytics analyticsSdk, @NotNull Resources resources, @NotNull InternalLogger baseLogger) {
        Intrinsics.checkNotNullParameter(analyticsSdk, "analyticsSdk");
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        this.analyticsSdk = analyticsSdk;
        this.resources = resources;
        this.log = baseLogger.createLogger("AuthDelegate");
    }

    private final String getImapSettings(NetResponse.Error.ApiError response) {
        String str = response.getHeaders().get(HEADER_IMAP_SETTINGS);
        return str == null ? "" : str;
    }

    private final String getLudwigToken(String response) {
        try {
            return new JSONObject(response).getString(LUDWIG_TOKEN_FIELD);
        } catch (JSONException e10) {
            this.log.e("LudwigToken parsing exception " + e10, e10);
            return null;
        }
    }

    private final String getSecondStepUrl(String response) {
        try {
            JSONObject jSONObject = new JSONObject(response);
            if (!StringsKt.equals(jSONObject.getString(AnalyticsConstants.KEY.STATUS), "ok", true)) {
                return null;
            }
            String string = jSONObject.getString("Continue");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return Uri.parse(string).buildUpon().appendQueryParameter(PreferenceHostProvider.URL_PARAM_CLIENT, "mobile.app").build().toString();
        } catch (Exception e10) {
            this.log.e("Second step url parsing exception " + e10, e10);
            return null;
        }
    }

    private final boolean isNeedCaptcha(NetResponse.Error.ApiError response) {
        String str = response.getHeaders().get(HEADER_CAPTCHA_REQUIRED);
        if (str == null) {
            return false;
        }
        return Intrinsics.areEqual(StringsKt.trim((CharSequence) str).toString(), "1");
    }

    public static /* synthetic */ AuthMailApiCommonResult.AdditionalCase onError$default(AuthDelegate authDelegate, String str, String str2, NetResponse.Error error, boolean z10, String str3, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z10 = false;
        }
        boolean z11 = z10;
        if ((i10 & 16) != 0) {
            str3 = null;
        }
        return authDelegate.onError(str, str2, error, z11, str3);
    }

    private final void sendCgiBinAuthSecStepFlowAnalytics(String url) {
        String str = "";
        if (url.length() > 0) {
            Uri uri = Uri.parse(url);
            String host = uri.getHost();
            if (host == null) {
                host = "";
            }
            String path = uri.getPath();
            str = host + (path != null ? path : "");
        }
        this.analyticsSdk.sendCgiBinAuthSecStepFlow(str);
    }

    @NotNull
    public final AuthMailApiCommonResult.AdditionalCase onError(@NotNull String login, @NotNull String password, @NotNull NetResponse.Error response, boolean isEsiaBindType, @Nullable String authFlowType) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response instanceof NetResponse.Error.UnknownError) {
            this.analyticsSdk.failedCgiBinAuth("unknown");
            this.analyticsSdk.customFailedCgiBinAuth(authFlowType, "unknown");
            InternalLogger.e$default(this.log, "onError, responseError = " + ((NetResponse.Error.UnknownError) response).getError(), null, 2, null);
            return new AuthMailApiCommonResult.AdditionalCase.Error.UnknownError(this.resources.getString(R.string.error_network));
        }
        if (response instanceof NetResponse.Error.NetworkError) {
            NetResponse.Error.NetworkError networkError = (NetResponse.Error.NetworkError) response;
            this.analyticsSdk.netErrorCgiBinAuth(networkError.getError());
            this.analyticsSdk.customFailedCgiBinAuth(authFlowType, Event.Companion.Network.TAG);
            return new AuthMailApiCommonResult.AdditionalCase.Error.NetworkError(networkError.getError().toString());
        }
        if (!(response instanceof NetResponse.Error.ApiError)) {
            this.analyticsSdk.failedCgiBinAuth("api_error");
            this.analyticsSdk.customFailedCgiBinAuth(authFlowType, "api_error");
            return new AuthMailApiCommonResult.AdditionalCase.Error.UnknownError(this.resources.getString(R.string.error_network));
        }
        NetResponse.Error.ApiError apiError = (NetResponse.Error.ApiError) response;
        InternalLogger.d$default(this.log, "onError, swaStatus = " + apiError.getSwaStatus(), null, 2, null);
        if (apiError.getSwaStatus() == null) {
            String body = apiError.getBody();
            if (body == null || !StringsKt.contains$default((CharSequence) body, (CharSequence) Ok0_ERROR, false, 2, (Object) null)) {
                this.analyticsSdk.failedCgiBinAuth("unknown");
                this.analyticsSdk.customFailedCgiBinAuth(authFlowType, "unknown");
                return new AuthMailApiCommonResult.AdditionalCase.Error.UnknownError(this.resources.getString(R.string.error_network));
            }
            this.analyticsSdk.failedCgiBinAuth(Ok0_ERROR);
            this.analyticsSdk.customFailedCgiBinAuth(authFlowType, Ok0_ERROR);
            return AuthMailApiCommonResult.AdditionalCase.Error.ErrorInvalidLogin.INSTANCE;
        }
        this.analyticsSdk.customFailedCgiBinAuth(authFlowType, String.valueOf(apiError.getSwaStatus().intValue()));
        this.analyticsSdk.failedCgiBinAuth(String.valueOf(apiError.getSwaStatus().intValue()));
        int iIntValue = apiError.getSwaStatus().intValue();
        if (iIntValue == 403) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_403), false, 2, null);
        }
        if (iIntValue == 404) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_404), false, 2, null);
        }
        if (iIntValue == 428) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_428), false, 2, null);
        }
        if (iIntValue == 429) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_429), false, 2, null);
        }
        if (iIntValue == 611) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_cant_bind_account), false, 2, null);
        }
        if (iIntValue == 612) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_612), false, 2, null);
        }
        if (iIntValue == 723) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithImapSettings(login, password, getImapSettings(apiError));
        }
        if (iIntValue == 724) {
            return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_724), false, 2, null);
        }
        switch (iIntValue) {
            case 400:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_400), false, 2, null);
            case 408:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_unknow_domain_408), false, 2, null);
            case 449:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_449), false, 2, null);
            case 500:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_500), false, 2, null);
            case 503:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_503), false, 2, null);
            case 601:
                String str = apiError.getHeaders().get(HEADER_SOCIAL_TYPE);
                return new AuthMailApiCommonResult.AdditionalCase.Error.TwoFactorBindForbidden(login, str != null ? str : "", this.resources.getString(isEsiaBindType ? R.string.error_code_601_esia : R.string.error_code_601_vk));
            case 603:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(isEsiaBindType ? R.string.error_code_603_esia : R.string.error_code_603_vk), false, 2, null);
            case 705:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_705), false, 2, null);
            case 706:
            case 710:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.mapp_err_auth), true);
            case 707:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_707), false, 2, null);
            case 708:
                return new AuthMailApiCommonResult.AdditionalCase.ServerSettingsRequired(login, password, this.resources.getString(R.string.error_code_708), isNeedCaptcha(apiError), false);
            case 709:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_709), false, 2, null);
            case 718:
                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_718), false, 2, null);
            case 812:
                return new AuthMailApiCommonResult.AdditionalCase.Error.OAuthImapFailed(login);
            default:
                switch (iIntValue) {
                    case 605:
                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_605), false, 2, null);
                    case 606:
                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(isEsiaBindType ? R.string.error_code_606_esia : R.string.error_code_606_vk), false, 2, null);
                    case 607:
                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_607), false, 2, null);
                    case 608:
                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_608), false, 2, null);
                    default:
                        switch (iIntValue) {
                            case 616:
                                return new AuthMailApiCommonResult.AdditionalCase.Error.ExternalAuthProhibit(login);
                            case 617:
                                return new AuthMailApiCommonResult.AdditionalCase.Error.BindTokenExpired(login);
                            case 618:
                                return AuthMailApiCommonResult.AdditionalCase.Error.B2bUserBlocked.INSTANCE;
                            default:
                                switch (iIntValue) {
                                    case 712:
                                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_712, login), false, 2, null);
                                    case 713:
                                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_713), false, 2, null);
                                    case 714:
                                        return new AuthMailApiCommonResult.AdditionalCase.DefaultLoginRequired(login);
                                    default:
                                        switch (iIntValue) {
                                            case 801:
                                                String str2 = apiError.getHeaders().get(HEADER_EXTERNAL_ACCOUNT_REGISTRATION_ID);
                                                String str3 = apiError.getHeaders().get(HEADER_CAPTCHA_REQUIRED);
                                                return (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) ? new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_709), false, 2, null) : new AuthMailApiCommonResult.AdditionalCase.Error.ExternalAccountRegistrationRequired(login, password, str2, isNeedCaptcha(apiError));
                                            case 802:
                                                return new AuthMailApiCommonResult.AdditionalCase.OAuthRequired.OAuthGoogleRequired(login);
                                            case 803:
                                                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_803), false, 2, null);
                                            case 804:
                                                return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_804), false, 2, null);
                                            case 805:
                                                return new AuthMailApiCommonResult.AdditionalCase.OAuthRequired.OAuthOutlookRequired(login);
                                            case 806:
                                                return new AuthMailApiCommonResult.AdditionalCase.Error.UserBlockedError(login);
                                            default:
                                                switch (iIntValue) {
                                                    case 808:
                                                        String body2 = apiError.getBody();
                                                        if (body2 == null) {
                                                            body2 = "";
                                                        }
                                                        String secondStepUrl = getSecondStepUrl(body2);
                                                        String str4 = apiError.getHeaders().get(SET_COOKIE_HEADER);
                                                        String str5 = str4 != null ? str4 : "";
                                                        String ludwigToken = getLudwigToken(body2);
                                                        if (ludwigToken != null && ludwigToken.length() > 0) {
                                                            return new AuthMailApiCommonResult.AdditionalCase.NeedCaptcha(login, ludwigToken, secondStepUrl, str5);
                                                        }
                                                        if (secondStepUrl != null) {
                                                            sendCgiBinAuthSecStepFlowAnalytics(secondStepUrl);
                                                            if (secondStepUrl.length() > 0) {
                                                                return new AuthMailApiCommonResult.AdditionalCase.NeedSecondStep(login, secondStepUrl, str5);
                                                            }
                                                        }
                                                        return new AuthMailApiCommonResult.AdditionalCase.Error.ErrorWithMessage(this.resources.getString(R.string.error_code_709), false, 2, null);
                                                    case 809:
                                                        return new AuthMailApiCommonResult.AdditionalCase.OAuthRequired.OAuthYandexRequired(login);
                                                    case 810:
                                                        return new AuthMailApiCommonResult.AdditionalCase.OAuthRequired.OAuthYahooRequired(login);
                                                    default:
                                                        return new AuthMailApiCommonResult.AdditionalCase.Error.UnknownError(apiError.getSwaStatus());
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
