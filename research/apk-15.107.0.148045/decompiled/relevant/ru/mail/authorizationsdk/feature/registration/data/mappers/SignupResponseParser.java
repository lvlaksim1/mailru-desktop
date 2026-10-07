package ru.mail.authorizationsdk.feature.registration.data.mappers;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Response;
import ru.mail.authorizationsdk.feature.registration.data.model.UserExistsResponse;
import ru.mail.authorizationsdk.feature.registration.data.model.UserExistsResponseBody;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.Additional;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.BaseSignupResponse;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.Captcha;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.Options;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.SignupConfirmResponse;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.SignupResponse;
import ru.mail.authorizationsdk.feature.registration.data.model.signup.SignupResponseBody;
import ru.mail.authorizationsdk.feature.registration.domain.model.ErrorStatusKt;
import ru.mail.authorizationsdk.feature.registration.domain.signup.SignupConfirmResult;
import ru.mail.authorizationsdk.feature.registration.domain.signup.UserExistsResult;
import ru.mail.authorizationsdk.feature.registration.domain.signup.UserSignupResult;
import ru.mail.util.log.InternalLogger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0018\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¨\u0006\u001b"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/data/mappers/SignupResponseParser;", "Lru/mail/authorizationsdk/feature/registration/data/mappers/BaseSignupResponseParser;", "errorMapper", "Lru/mail/authorizationsdk/feature/registration/data/mappers/SignupErrorMapper;", "logger", "Lru/mail/util/log/InternalLogger;", "<init>", "(Lru/mail/authorizationsdk/feature/registration/data/mappers/SignupErrorMapper;Lru/mail/util/log/InternalLogger;)V", "parseSignup", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", "response", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "isSocialSignup", "", "parseSignupConfirm", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupConfirmResult;", "parseUserExists", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserExistsResult;", "parseSignupResponse", "Lru/mail/authorizationsdk/feature/registration/data/model/signup/SignupResponse;", "parseSocial", "body", "Lru/mail/authorizationsdk/feature/registration/data/model/signup/SignupResponseBody;", "token", "", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSignupResponseParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignupResponseParser.kt\nru/mail/authorizationsdk/feature/registration/data/mappers/SignupResponseParser\n+ 2 BaseSignupResponseParser.kt\nru/mail/authorizationsdk/feature/registration/data/mappers/BaseSignupResponseParser\n+ 3 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,91:1\n29#2,5:92\n34#2,4:98\n51#2,12:102\n38#2,5:114\n29#2,5:119\n34#2,4:125\n51#2,12:129\n38#2,5:141\n29#2,5:146\n34#2,4:152\n51#2,12:156\n38#2,5:168\n222#3:97\n222#3:124\n222#3:151\n*S KotlinDebug\n*F\n+ 1 SignupResponseParser.kt\nru/mail/authorizationsdk/feature/registration/data/mappers/SignupResponseParser\n*L\n18#1:92,5\n18#1:98,4\n18#1:102,12\n18#1:114,5\n29#1:119,5\n29#1:125,4\n29#1:129,12\n29#1:141,5\n40#1:146,5\n40#1:152,4\n40#1:156,12\n40#1:168,5\n18#1:97\n29#1:124\n40#1:151\n*E\n"})
public final class SignupResponseParser extends BaseSignupResponseParser {

    @NotNull
    private static final String RECAPTCHA = "recaptcha";
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignupResponseParser(@NotNull SignupErrorMapper errorMapper, @NotNull InternalLogger logger) {
        super(errorMapper, null, logger, 2, null);
        Intrinsics.checkNotNullParameter(errorMapper, "errorMapper");
        Intrinsics.checkNotNullParameter(logger, "logger");
    }

    private final UserSignupResult parseSignupResponse(SignupResponse response, boolean isSocialSignup) {
        String token;
        if (response.getBody() == null || (token = response.getBody().getToken()) == null || token.length() == 0) {
            return new UserSignupResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
        }
        if (isSocialSignup) {
            return parseSocial(response.getBody(), response.getBody().getToken());
        }
        Additional additional = response.getBody().getAdditional();
        Captcha captcha = additional != null ? additional.getCaptcha() : null;
        if (!Intrinsics.areEqual(captcha != null ? captcha.getType() : null, RECAPTCHA)) {
            return new UserSignupResult.Success(response.getBody().getToken());
        }
        String token2 = response.getBody().getToken();
        Options options = captcha.getOptions();
        return new UserSignupResult.Captcha(token2, options != null ? options.getSiteKey() : null);
    }

    private final UserSignupResult parseSocial(SignupResponseBody body, String token) {
        Additional additional = body.getAdditional();
        return additional != null ? Intrinsics.areEqual(additional.getTokenChecked(), Boolean.FALSE) : false ? new UserSignupResult.ErrorPhoneRequired(null, 1, null) : new UserSignupResult.Success(token);
    }

    @NotNull
    public final UserSignupResult parseSignup(@NotNull Response<ResponseBody> response, boolean isSocialSignup) {
        Object error;
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                ResponseBody responseBodyErrorBody = response.errorBody();
                strString = responseBodyErrorBody != null ? responseBodyErrorBody.string() : null;
            }
            int iCode = response.code();
            Json json = getJson();
            Intrinsics.checkNotNull(strString);
            json.getSerializersModule();
            int i10 = JsonElementKt.getInt(JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue((Map) json.decodeFromString(JsonObject.INSTANCE.serializer(), strString), "status")));
            if (iCode == 200) {
                try {
                    if (i10 != 200) {
                        error = i10 != 400 ? new ApiResult.Error(getErrorMapper().getErrorMessage(i10)) : new ApiResult.Error(getErrorMapper().checkValuesSignup(strString));
                    } else {
                        Json json2 = getJson();
                        json2.getSerializersModule();
                        error = new ApiResult.Success(parseSignupResponse((SignupResponse) ((BaseSignupResponse) json2.decodeFromString(SignupResponse.INSTANCE.serializer(), strString)), isSocialSignup));
                    }
                } catch (Exception e10) {
                    InternalLogger.e$default(getLogger(), "handleSuccessResponse error: " + e10, null, 2, null);
                    error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
                }
            } else {
                error = new ApiResult.Error(getErrorMapper().getErrorMessage(i10));
            }
        } catch (Exception e11) {
            InternalLogger.e$default(getLogger(), "parse error: " + e11, null, 2, null);
            error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
        }
        if (error instanceof ApiResult.Error) {
            return new UserSignupResult.Error(((ApiResult.Error) error).getErrors());
        }
        if (error instanceof ApiResult.Success) {
            return (UserSignupResult) ((ApiResult.Success) error).getData();
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public final SignupConfirmResult parseSignupConfirm(@NotNull Response<ResponseBody> response) {
        Object error;
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                ResponseBody responseBodyErrorBody = response.errorBody();
                strString = responseBodyErrorBody != null ? responseBodyErrorBody.string() : null;
            }
            int iCode = response.code();
            Json json = getJson();
            Intrinsics.checkNotNull(strString);
            json.getSerializersModule();
            int i10 = JsonElementKt.getInt(JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue((Map) json.decodeFromString(JsonObject.INSTANCE.serializer(), strString), "status")));
            if (iCode == 200) {
                try {
                    if (i10 != 200) {
                        error = i10 != 400 ? new ApiResult.Error(getErrorMapper().getErrorMessage(i10)) : new ApiResult.Error(getErrorMapper().checkValuesSignup(strString));
                    } else {
                        Json json2 = getJson();
                        json2.getSerializersModule();
                        String body = ((SignupConfirmResponse) ((BaseSignupResponse) json2.decodeFromString(SignupConfirmResponse.INSTANCE.serializer(), strString))).getBody();
                        Intrinsics.checkNotNull(body);
                        error = new ApiResult.Success(new SignupConfirmResult.Success(body));
                    }
                } catch (Exception e10) {
                    InternalLogger.e$default(getLogger(), "handleSuccessResponse error: " + e10, null, 2, null);
                    error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
                }
            } else {
                error = new ApiResult.Error(getErrorMapper().getErrorMessage(i10));
            }
        } catch (Exception e11) {
            InternalLogger.e$default(getLogger(), "parse error: " + e11, null, 2, null);
            error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
        }
        if (error instanceof ApiResult.Error) {
            return new SignupConfirmResult.Error(((ApiResult.Error) error).getErrors());
        }
        if (error instanceof ApiResult.Success) {
            return (SignupConfirmResult) ((ApiResult.Success) error).getData();
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public final UserExistsResult parseUserExists(@NotNull Response<ResponseBody> response) {
        Object error;
        String strString;
        Boolean exists;
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                ResponseBody responseBodyErrorBody = response.errorBody();
                strString = responseBodyErrorBody != null ? responseBodyErrorBody.string() : null;
            }
            int iCode = response.code();
            Json json = getJson();
            Intrinsics.checkNotNull(strString);
            json.getSerializersModule();
            int i10 = JsonElementKt.getInt(JsonElementKt.getJsonPrimitive((JsonElement) MapsKt.getValue((Map) json.decodeFromString(JsonObject.INSTANCE.serializer(), strString), "status")));
            if (iCode == 200) {
                try {
                    if (i10 != 200) {
                        error = i10 != 400 ? new ApiResult.Error(getErrorMapper().getErrorMessage(i10)) : new ApiResult.Error(getErrorMapper().checkValuesSignup(strString));
                    } else {
                        Json json2 = getJson();
                        json2.getSerializersModule();
                        UserExistsResponse userExistsResponse = (UserExistsResponse) ((BaseSignupResponse) json2.decodeFromString(UserExistsResponse.INSTANCE.serializer(), strString));
                        String email = userExistsResponse.getEmail();
                        UserExistsResponseBody body = userExistsResponse.getBody();
                        List<String> alternatives = body != null ? body.getAlternatives() : null;
                        if (alternatives == null) {
                            alternatives = CollectionsKt.emptyList();
                        }
                        UserExistsResponseBody body2 = userExistsResponse.getBody();
                        error = new ApiResult.Success(new UserExistsResult.Success((body2 == null || (exists = body2.getExists()) == null) ? false : exists.booleanValue(), alternatives, email));
                    }
                } catch (Exception e10) {
                    InternalLogger.e$default(getLogger(), "handleSuccessResponse error: " + e10, null, 2, null);
                    error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
                }
            } else {
                error = new ApiResult.Error(getErrorMapper().getErrorMessage(i10));
            }
        } catch (Exception e11) {
            InternalLogger.e$default(getLogger(), "parse error: " + e11, null, 2, null);
            error = new ApiResult.Error(ErrorStatusKt.getNETWORK_ERROR_VALUES());
        }
        if (error instanceof ApiResult.Error) {
            return new UserExistsResult.Error(((ApiResult.Error) error).getErrors());
        }
        if (error instanceof ApiResult.Success) {
            return (UserExistsResult) ((ApiResult.Success) error).getData();
        }
        throw new NoWhenBranchMatchedException();
    }
}
