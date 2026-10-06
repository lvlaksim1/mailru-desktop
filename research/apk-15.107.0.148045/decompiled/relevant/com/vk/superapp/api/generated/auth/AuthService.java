package com.vk.superapp.api.generated.auth;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.api.generated.apps.dto.AppsGetScopesResponseDto;
import com.vk.api.generated.apps.dto.AppsGetSubAppInfoResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckAccessResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckAuthHashResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckBindExtOAuthResponseDto;
import com.vk.api.generated.auth.dto.AuthCheckValidationStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthCreateAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeSilentTokenToSidResponseDto;
import com.vk.api.generated.auth.dto.AuthExchangeTokenInfoDto;
import com.vk.api.generated.auth.dto.AuthExternalFlowOutPlatformDto;
import com.vk.api.generated.auth.dto.AuthExternalFlowOutResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAuthDataResponseDto;
import com.vk.api.generated.auth.dto.AuthGetAutologinCredentialsResponseDto;
import com.vk.api.generated.auth.dto.AuthGetContinuationForServiceResponseDto;
import com.vk.api.generated.auth.dto.AuthGetCredentialsForServiceMultiResponseDto;
import com.vk.api.generated.auth.dto.AuthGetExchangeTokenInfoResponseDto;
import com.vk.api.generated.auth.dto.AuthGetExchangeTokenResponseDto;
import com.vk.api.generated.auth.dto.AuthGetQrAuthDataResponseDto;
import com.vk.api.generated.auth.dto.AuthGetSilentTokensResponseDto;
import com.vk.api.generated.auth.dto.AuthGetUserInfoByPhoneResponseDto;
import com.vk.api.generated.auth.dto.AuthGetWebAuthLinkResponseDto;
import com.vk.api.generated.auth.dto.AuthInitPasswordCheckResponseDto;
import com.vk.api.generated.auth.dto.AuthInvalidateExchangeTokenMultiResponseDto;
import com.vk.api.generated.auth.dto.AuthOnSuccessValidationResponseDto;
import com.vk.api.generated.auth.dto.AuthProcessAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthProcessAuthHashResponseDto;
import com.vk.api.generated.auth.dto.AuthRefreshTokensResponseDto;
import com.vk.api.generated.auth.dto.AuthRefreshTrustedHashesResponseDto;
import com.vk.api.generated.auth.dto.AuthSetAuthCodeStatusResponseDto;
import com.vk.api.generated.auth.dto.AuthSignupResponseDto;
import com.vk.api.generated.auth.dto.AuthSignupSexDto;
import com.vk.api.generated.auth.dto.AuthSilentProviderDto;
import com.vk.api.generated.auth.dto.AuthSilentTokenDto;
import com.vk.api.generated.auth.dto.AuthSilentTokenShortDto;
import com.vk.api.generated.auth.dto.AuthTerminateAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateAccountResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateAccountSupportedWaysDto;
import com.vk.api.generated.auth.dto.AuthValidateAuthCodeResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateEmailResponseDto;
import com.vk.api.generated.auth.dto.AuthValidateLoginResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneCancelResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneCheckModeDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneCheckResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneConfirmResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneInfoResponseDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneSupportedWaysDto;
import com.vk.api.generated.auth.dto.AuthValidatePhoneSupportedWaysSettingsDto;
import com.vk.api.generated.auth.dto.AuthValidateSuperAppTokenResponseDto;
import com.vk.api.generated.base.dto.BaseBoolIntDto;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.core.ApiMethodCall;
import com.vk.api.generated.core.ApiResponseParser;
import com.vk.api.generated.core.ApiStreamResponseParser;
import com.vk.api.generated.core.RootResponseDto;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.api.generated.GsonHolder;
import com.vk.superapp.api.generated.InternalApiMethodCall;
import com.vk.superapp.api.generated.SingleRootResponseDto;
import com.vk.trustedhash.util.TrustedHashUtils;
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
import okhttp3.internal.http2.Http2Connection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.entity.SystemContactEntity;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.smoothie.domain.web.load.usecase.InjectNativeParamToAppConfigUseCase;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¾\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\n\u0086\u0002\u0087\u0002\u0088\u0002\u0089\u0002\u008a\u0002J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016JC\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010\u000fJ'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J%\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\t\u0012\u00070\u001c¢\u0006\u0002\b\u001d\u0018\u00010\u001bH\u0016J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\u0006\u0010 \u001a\u00020\u0006H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0003H\u0016J9\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00032\u0011\u0010\u001a\u001a\r\u0012\t\u0012\u00070\u001c¢\u0006\u0002\b\u001d0\u001b2\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0016JB\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020,2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0006H\u0016J3\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u001b0\u00032\u0011\u0010\u001a\u001a\r\u0012\t\u0012\u00070\u001c¢\u0006\u0002\b\u001d0\u001b2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u0006H\u0016J7\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u00105J\u008b\u0001\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00032\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010BJ\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00032\u0006\u0010 \u001a\u00020\u0006H\u0016J\u008f\u0003\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u00032\u0006\u0010G\u001a\u00020\u000b2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010`\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010a\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010bJJ\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u00032\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u0006H\u0016JO\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010kJ\u0016\u0010l\u001a\b\u0012\u0004\u0012\u00020m0\u00032\u0006\u0010G\u001a\u00020\u000bH\u0016JT\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0\u001b0\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u00062\u0006\u0010q\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\u00062\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u0006H\u0016Jv\u0010u\u001a\b\u0012\u0004\u0012\u00020v0\u00032\u0006\u0010G\u001a\u00020\u000b2\u0006\u0010p\u001a\u00020\u00062\u0006\u0010q\u001a\u00020\u00062\u0006\u0010r\u001a\u00020\u00062\u0010\b\u0002\u0010w\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u0006H\u0016J7\u0010x\u001a\b\u0012\u0004\u0012\u00020y0\u00032\n\b\u0002\u0010z\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010{\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010|\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010}J:\u0010~\u001a\b\u0012\u0004\u0012\u00020\u007f0\u00032\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0003\u0010\u0082\u0001JG\u0010\u0083\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0084\u00010\u001b0\u00032\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0003\u0010\u0085\u0001J\u008a\u0001\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010\u00032\u0006\u0010G\u001a\u00020\u000b2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010V\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0003\u0010\u0088\u0001J\u0016\u0010\u0089\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008a\u00010\u001b0\u0003H\u0016J:\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u00030\u008c\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0002\u00105J*\u0010\u008e\u0001\u001a\t\u0012\u0005\u0012\u00030\u008f\u00010\u00032\u0006\u0010*\u001a\u00020\u00062\u000b\b\u0002\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J%\u0010\u0091\u0001\u001a\t\u0012\u0005\u0012\u00030\u0092\u00010\u00032\u0006\u0010S\u001a\u00020\u00062\u000b\b\u0002\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0006H\u0016J;\u0010\u0094\u0001\u001a\t\u0012\u0005\u0012\u00030\u0095\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0003\u0010\u0097\u0001J\u001e\u0010\u0098\u0001\u001a\t\u0012\u0005\u0012\u00030\u0099\u00010\u00032\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001bH\u0016J\u0017\u0010\u009a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J-\u0010\u009b\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u000b\b\u0002\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0006H\u0016J}\u0010\u009e\u0001\u001a\t\u0012\u0005\u0012\u00030\u009f\u00010\u00032\u0006\u0010 \u001a\u00020\u00062\u0010\b\u0002\u0010w\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u000b2\u000b\b\u0002\u0010 \u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¡\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¢\u0001\u001a\u0004\u0018\u00010\u000b2\u000b\b\u0002\u0010£\u0001\u001a\u0004\u0018\u00010\u000b2\u000b\b\u0002\u0010¤\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0003\u0010¥\u0001J \u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030§\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010_\u001a\u00020\u000bH\u0016J\u0093\u0001\u0010¨\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u00062\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u0011\b\u0002\u0010ª\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\u000b\b\u0002\u0010«\u0001\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¬\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010®\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0003\u0010¯\u0001J9\u0010°\u0001\u001a\t\u0012\u0005\u0012\u00030±\u00010\u00032\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\r\u0010²\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u0006H\u0016J\u0018\u0010³\u0001\u001a\t\u0012\u0005\u0012\u00030´\u00010\u00032\u0006\u0010 \u001a\u00020\u0006H\u0016J¦\u0002\u0010µ\u0001\u001a\t\u0012\u0005\u0012\u00030¶\u00010\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u00062\u000b\b\u0002\u0010·\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¸\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¹\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010º\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010»\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010¼\u0001\u001a\u0004\u0018\u00010\u00142\f\b\u0002\u0010½\u0001\u001a\u0005\u0018\u00010¾\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¿\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010À\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Á\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Â\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010Ã\u0001\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ä\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010Å\u0001\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0003\u0010Æ\u0001J\u0018\u0010Ç\u0001\u001a\t\u0012\u0005\u0012\u00030È\u00010\u00032\u0006\u0010 \u001a\u00020\u0006H\u0016JÌ\u0002\u0010É\u0001\u001a\t\u0012\u0005\u0012\u00030Ê\u00010\u00032\u000b\b\u0002\u0010Ë\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ì\u0001\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Í\u0001\u001a\u0004\u0018\u00010\u000b2\u0012\b\u0002\u0010Î\u0001\u001a\u000b\u0012\u0005\u0012\u00030Ï\u0001\u0018\u00010\u001b2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ð\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010Ò\u0001\u001a\u0004\u0018\u00010\u00142\u0011\b\u0002\u0010Ó\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\u0010\b\u0002\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001b2\u000b\b\u0002\u0010Ô\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010Õ\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010Ö\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010×\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ø\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ù\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ú\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0003\u0010Û\u0001J%\u0010Ü\u0001\u001a\t\u0012\u0005\u0012\u00030Ý\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u00062\u000b\b\u0002\u0010Þ\u0001\u001a\u0004\u0018\u00010\u0006H\u0016J-\u0010ß\u0001\u001a\t\u0012\u0005\u0012\u00030à\u00010\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u000b\b\u0002\u0010á\u0001\u001a\u0004\u0018\u00010\u0006H\u0016J(\u0010â\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016JY\u0010ä\u0001\u001a\t\u0012\u0005\u0012\u00030å\u00010\u00032\u0007\u0010Ë\u0001\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010æ\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Í\u0001\u001a\u0004\u0018\u00010\u000bH\u0016¢\u0006\u0003\u0010ç\u0001Jâ\u0001\u0010è\u0001\u001a\t\u0012\u0005\u0012\u00030é\u00010\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010¿\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010¼\u0001\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010ê\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010ë\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010ì\u0001\u001a\u0004\u0018\u00010\u00142\u0012\b\u0002\u0010Î\u0001\u001a\u000b\u0012\u0005\u0012\u00030í\u0001\u0018\u00010\u001b2\u0012\b\u0002\u0010î\u0001\u001a\u000b\u0012\u0005\u0012\u00030ï\u0001\u0018\u00010\u001b2\u000b\b\u0002\u0010ð\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ú\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010ñ\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0003\u0010ò\u0001J%\u0010ó\u0001\u001a\t\u0012\u0005\u0012\u00030ô\u00010\u00032\u0006\u0010%\u001a\u00020\u00062\u000b\b\u0002\u0010õ\u0001\u001a\u0004\u0018\u00010\u0006H\u0016J9\u0010ö\u0001\u001a\t\u0012\u0005\u0012\u00030÷\u00010\u00032\u0007\u0010ø\u0001\u001a\u00020\u00142\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u000b2\f\b\u0002\u0010ù\u0001\u001a\u0005\u0018\u00010ú\u0001H\u0016¢\u0006\u0003\u0010û\u0001J\u001b\u0010ü\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0006H\u0016J©\u0001\u0010ý\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u00032\u0006\u0010%\u001a\u00020\u00062\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010þ\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Â\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010ÿ\u0001\u001a\u0004\u0018\u00010\u00142\u000b\b\u0002\u0010ð\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u00062\u000b\b\u0002\u0010Ò\u0001\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0003\u0010\u0081\u0002J \u0010\u0082\u0002\u001a\t\u0012\u0005\u0012\u00030\u0083\u00020\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0016J \u0010\u0084\u0002\u001a\t\u0012\u0005\u0012\u00030\u0085\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006H\u0016¨\u0006\u008b\u0002"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService;", "", "authBindExtOAuth", "Lcom/vk/api/generated/core/ApiMethodCall;", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "silentToken", "", SilentAuthInfo.KEY_UUID, "authCheckAccess", "Lcom/vk/api/generated/auth/dto/AuthCheckAccessResponseDto;", "clientId", "", "token", "password", "code", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authCheckAuthCode", "Lcom/vk/api/generated/auth/dto/AuthCheckAuthCodeResponseDto;", "authHash", "webAuth", "", "(Ljava/lang/String;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authCheckAuthHash", "Lcom/vk/api/generated/auth/dto/AuthCheckAuthHashResponseDto;", "authCheckBindExtOAuth", "Lcom/vk/api/generated/auth/dto/AuthCheckBindExtOAuthResponseDto;", "silentTokens", "", "Lcom/google/gson/JsonObject;", "Lkotlinx/parcelize/RawValue;", "authCheckValidationStatus", "Lcom/vk/api/generated/auth/dto/AuthCheckValidationStatusResponseDto;", "authCode", "authCreateAuthCode", "Lcom/vk/api/generated/auth/dto/AuthCreateAuthCodeResponseDto;", "authExchangeSilentTokensToSid", "Lcom/vk/api/generated/auth/dto/AuthExchangeSilentTokenToSidResponseDto;", PasskeyBeginResult.SID_KEY, "phone", "lang", "authExternalFlowOut", "Lcom/vk/api/generated/auth/dto/AuthExternalFlowOutResponseDto;", "superAppToken", "platform", "Lcom/vk/api/generated/auth/dto/AuthExternalFlowOutPlatformDto;", "state", "redirectUri", "authFilterSilentTokens", "Lcom/vk/api/generated/auth/dto/AuthSilentTokenShortDto;", "authGetAppScopes", "Lcom/vk/api/generated/apps/dto/AppsGetScopesResponseDto;", "clientSecret", "serviceId", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetAuthCode", "Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeResponseDto;", InjectNativeParamToAppConfigUseCase.DEVICE_NAME_KEY, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "authCodeType", "authCodeFlow", "qrCodeSize", "needQrCode", "verificationHash", "forceRegenerate", "isSwitcherFlow", "pageLink", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetAuthCodeStatus", "Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusResponseDto;", "authGetAuthData", "Lcom/vk/api/generated/auth/dto/AuthGetAuthDataResponseDto;", "appId", "origin", "sferumLogo", "vkmeFlowType", "typeCarousel", "screen", "redirectUriHash", "clientMetadata", "appSettings", "codeChallenge", "codeChallengeMethod", "scopeString", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "redirectState", "oauthVersion", "scheme", "sdkType", "httpReferer", "httpOrigin", "responseType", "forceHash", "restoreAuthToken", "returnAuthHash", "statsInfo", "action", "prompt", "initialUri", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetAutologinCredentials", "Lcom/vk/api/generated/auth/dto/AuthGetAutologinCredentialsResponseDto;", "exchangeTokens", "sessionId", "authGetContinuationForService", "Lcom/vk/api/generated/auth/dto/AuthGetContinuationForServiceResponseDto;", "silentTokenUuid", "phoneValidationSid", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetCredentialsForApp", "Lcom/vk/api/generated/auth/dto/AuthGetSilentTokensResponseDto;", "authGetCredentialsForService", "Lcom/vk/api/generated/auth/dto/AuthSilentTokenDto;", "packageValue", "timestamp", "digestHash", "clientDeviceId", "clientExternalDeviceId", "authGetCredentialsForServiceMulti", "Lcom/vk/api/generated/auth/dto/AuthGetCredentialsForServiceMultiResponseDto;", "accessTokens", "authGetExchangeToken", "Lcom/vk/api/generated/auth/dto/AuthGetExchangeTokenResponseDto;", "createCommonToken", "createTierTokens", "needServiceMa", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetExchangeTokenInfo", "Lcom/vk/api/generated/auth/dto/AuthGetExchangeTokenInfoResponseDto;", "exchangeToken", "targetAppId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetExchangeTokensInfo", "Lcom/vk/api/generated/auth/dto/AuthExchangeTokenInfoDto;", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetQrAuthData", "Lcom/vk/api/generated/auth/dto/AuthGetQrAuthDataResponseDto;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authGetSilentAuthProviders", "Lcom/vk/api/generated/auth/dto/AuthSilentProviderDto;", "authGetSubAppInfo", "Lcom/vk/api/generated/apps/dto/AppsGetSubAppInfoResponseDto;", "subappId", "authGetUserInfoByPhone", "Lcom/vk/api/generated/auth/dto/AuthGetUserInfoByPhoneResponseDto;", "isDev", "authGetWebAuthLink", "Lcom/vk/api/generated/auth/dto/AuthGetWebAuthLinkResponseDto;", "to", "authInitPasswordCheck", "Lcom/vk/api/generated/auth/dto/AuthInitPasswordCheckResponseDto;", "accessFactor", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authInvalidateExchangeTokenMulti", "Lcom/vk/api/generated/auth/dto/AuthInvalidateExchangeTokenMultiResponseDto;", "authLogout", "authOnSuccessValidation", "Lcom/vk/api/generated/auth/dto/AuthOnSuccessValidationResponseDto;", "maxMessengerHash", "authProcessAuthCodeMulti", "Lcom/vk/api/generated/auth/dto/AuthProcessAuthCodeResponseDto;", "deviceIp", "mapStyle", "mapHeight", "mapWidth", "isInternalCamera", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authProcessAuthHash", "Lcom/vk/api/generated/auth/dto/AuthProcessAuthHashResponseDto;", "authRefreshTokens", "Lcom/vk/api/generated/auth/dto/AuthRefreshTokensResponseDto;", "passwords", "activeIndex", "initiator", "validateSession", "silentAuthByLogin", "(ILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authRefreshTrustedHashes", "Lcom/vk/api/generated/auth/dto/AuthRefreshTrustedHashesResponseDto;", "trustedHashes", "authSetAuthCodeStatus", "Lcom/vk/api/generated/auth/dto/AuthSetAuthCodeStatusResponseDto;", "authSignup", "Lcom/vk/api/generated/auth/dto/AuthSignupResponseDto;", "firstName", "middleName", "lastName", "birthday", "testMode", "voice", "sex", "Lcom/vk/api/generated/auth/dto/AuthSignupSexDto;", "libverifySupport", "fullName", "extend", "canSkipPassword", "createEducational", "isExternalCarousel", "inviteHash", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/vk/api/generated/auth/dto/AuthSignupSexDto;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/vk/api/generated/core/ApiMethodCall;", "authTerminateAuthCode", "Lcom/vk/api/generated/auth/dto/AuthTerminateAuthCodeResponseDto;", "authValidateAccount", "Lcom/vk/api/generated/auth/dto/AuthValidateAccountResponseDto;", "login", "forcePassword", "passkeySupported", "supportedWays", "Lcom/vk/api/generated/auth/dto/AuthValidateAccountSupportedWaysDto;", "trustedHash", "isEduFlow", "isRegistration", "accountsTrustedHashes", "startMailruFlow", "isBindFlow", "saveAuth", "mailToken", "multiaccountUsers", "multiaccountUsersHash", "maxMessengerEnabled", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authValidateAuthCode", "Lcom/vk/api/generated/auth/dto/AuthValidateAuthCodeResponseDto;", "validationCode", "authValidateEmail", "Lcom/vk/api/generated/auth/dto/AuthValidateEmailResponseDto;", "email", "authValidateEmailConfirm", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneConfirmResponseDto;", "authValidateLogin", "Lcom/vk/api/generated/auth/dto/AuthValidateLoginResponseDto;", "source", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/vk/api/generated/core/ApiMethodCall;", "authValidatePhone", "Lcom/vk/api/generated/base/dto/BaseBoolIntDto;", "disablePartial", "force", "allowCallreset", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneSupportedWaysDto;", "supportedWaysSettings", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneSupportedWaysSettingsDto;", "flowStartState", "tvRegistrationSupport", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authValidatePhoneCancel", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneCancelResponseDto;", "reason", "authValidatePhoneCheck", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneCheckResponseDto;", "isAuth", "mode", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneCheckModeDto;", "(ZLjava/lang/Integer;Lcom/vk/api/generated/auth/dto/AuthValidatePhoneCheckModeDto;)Lcom/vk/api/generated/core/ApiMethodCall;", "authValidatePhoneCheckSkip", "authValidatePhoneConfirm", "validateToken", "isCodeAutocomplete", "verificationType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "authValidatePhoneInfo", "Lcom/vk/api/generated/auth/dto/AuthValidatePhoneInfoResponseDto;", "authValidateSuperAppToken", "Lcom/vk/api/generated/auth/dto/AuthValidateSuperAppTokenResponseDto;", "AuthGetCredentialsForAppRestrictions", "AuthGetCredentialsForServiceRestrictions", "AuthGetCredentialsForServiceMultiRestrictions", "AuthLogoutRestrictions", "AuthValidatePhoneCheckRestrictions", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface AuthService {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService$AuthGetCredentialsForAppRestrictions;", "", "<init>", "()V", "APP_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AuthGetCredentialsForAppRestrictions {
        public static final long APP_ID_MIN = 0;

        @NotNull
        public static final AuthGetCredentialsForAppRestrictions INSTANCE = new AuthGetCredentialsForAppRestrictions();

        private AuthGetCredentialsForAppRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService$AuthGetCredentialsForServiceMultiRestrictions;", "", "<init>", "()V", "APP_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AuthGetCredentialsForServiceMultiRestrictions {
        public static final long APP_ID_MIN = 0;

        @NotNull
        public static final AuthGetCredentialsForServiceMultiRestrictions INSTANCE = new AuthGetCredentialsForServiceMultiRestrictions();

        private AuthGetCredentialsForServiceMultiRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService$AuthGetCredentialsForServiceRestrictions;", "", "<init>", "()V", "UUID_MIN_LENGTH", "", "APP_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AuthGetCredentialsForServiceRestrictions {
        public static final long APP_ID_MIN = 0;

        @NotNull
        public static final AuthGetCredentialsForServiceRestrictions INSTANCE = new AuthGetCredentialsForServiceRestrictions();
        public static final int UUID_MIN_LENGTH = 10;

        private AuthGetCredentialsForServiceRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService$AuthLogoutRestrictions;", "", "<init>", "()V", "CLIENT_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AuthLogoutRestrictions {
        public static final long CLIENT_ID_MIN = 0;

        @NotNull
        public static final AuthLogoutRestrictions INSTANCE = new AuthLogoutRestrictions();

        private AuthLogoutRestrictions() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/vk/superapp/api/generated/auth/AuthService$AuthValidatePhoneCheckRestrictions;", "", "<init>", "()V", "APP_ID_MIN", "", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AuthValidatePhoneCheckRestrictions {
        public static final long APP_ID_MIN = 0;

        @NotNull
        public static final AuthValidatePhoneCheckRestrictions INSTANCE = new AuthValidatePhoneCheckRestrictions();

        private AuthValidatePhoneCheckRestrictions() {
        }
    }

    @NotNull
    ApiMethodCall<BaseOkResponseDto> authBindExtOAuth(@Nullable String silentToken, @Nullable String uuid);

    @NotNull
    ApiMethodCall<AuthCheckAccessResponseDto> authCheckAccess(@Nullable Integer clientId, @Nullable String token, @Nullable String password, @Nullable String code);

    @NotNull
    ApiMethodCall<AuthCheckAuthCodeResponseDto> authCheckAuthCode(@NotNull String authHash, @Nullable Boolean webAuth);

    @NotNull
    ApiMethodCall<AuthCheckAuthHashResponseDto> authCheckAuthHash(@NotNull String authHash);

    @NotNull
    ApiMethodCall<AuthCheckBindExtOAuthResponseDto> authCheckBindExtOAuth(@Nullable List<JsonObject> silentTokens);

    @NotNull
    ApiMethodCall<AuthCheckValidationStatusResponseDto> authCheckValidationStatus(@NotNull String authCode);

    @NotNull
    ApiMethodCall<AuthCreateAuthCodeResponseDto> authCreateAuthCode();

    @NotNull
    ApiMethodCall<AuthExchangeSilentTokenToSidResponseDto> authExchangeSilentTokensToSid(@NotNull List<JsonObject> silentTokens, @NotNull String sid, @NotNull String phone, @NotNull String lang);

    @NotNull
    ApiMethodCall<AuthExternalFlowOutResponseDto> authExternalFlowOut(@NotNull String superAppToken, @NotNull AuthExternalFlowOutPlatformDto platform, @Nullable String sid, @Nullable String state, @Nullable String redirectUri);

    @NotNull
    ApiMethodCall<List<AuthSilentTokenShortDto>> authFilterSilentTokens(@NotNull List<JsonObject> silentTokens, @Nullable String superAppToken);

    @NotNull
    ApiMethodCall<AppsGetScopesResponseDto> authGetAppScopes(@Nullable Integer clientId, @Nullable String clientSecret, @Nullable Integer serviceId);

    @NotNull
    ApiMethodCall<AuthGetAuthCodeResponseDto> authGetAuthCode(@Nullable String deviceName, @Nullable String scope, @Nullable Integer authCodeType, @Nullable Integer authCodeFlow, @Nullable Integer qrCodeSize, @Nullable Boolean needQrCode, @Nullable String verificationHash, @Nullable Boolean forceRegenerate, @Nullable Boolean isSwitcherFlow, @Nullable String pageLink);

    @NotNull
    ApiMethodCall<AuthGetAuthCodeStatusResponseDto> authGetAuthCodeStatus(@NotNull String authCode);

    @NotNull
    ApiMethodCall<AuthGetAuthDataResponseDto> authGetAuthData(int appId, @Nullable String origin, @Nullable String state, @Nullable Boolean sferumLogo, @Nullable String vkmeFlowType, @Nullable String typeCarousel, @Nullable String screen, @Nullable String redirectUriHash, @Nullable String clientMetadata, @Nullable String appSettings, @Nullable String uuid, @Nullable String codeChallenge, @Nullable String codeChallengeMethod, @Nullable String scope, @Nullable String scopeString, @Nullable String deviceId, @Nullable String redirectState, @Nullable String oauthVersion, @Nullable String scheme, @Nullable String sdkType, @Nullable String httpReferer, @Nullable String httpOrigin, @Nullable String responseType, @Nullable Boolean forceHash, @Nullable String restoreAuthToken, @Nullable String returnAuthHash, @Nullable String redirectUri, @Nullable String statsInfo, @Nullable String action, @Nullable String superAppToken, @Nullable String prompt, @Nullable String initialUri);

    @NotNull
    ApiMethodCall<AuthGetAutologinCredentialsResponseDto> authGetAutologinCredentials(@Nullable List<String> silentTokens, @Nullable List<String> exchangeTokens, @Nullable String uuid, @Nullable String sessionId);

    @NotNull
    ApiMethodCall<AuthGetContinuationForServiceResponseDto> authGetContinuationForService(@Nullable Integer clientId, @Nullable Integer appId, @Nullable String silentToken, @Nullable String silentTokenUuid, @Nullable String phoneValidationSid);

    @NotNull
    ApiMethodCall<AuthGetSilentTokensResponseDto> authGetCredentialsForApp(int appId);

    @NotNull
    ApiMethodCall<List<AuthSilentTokenDto>> authGetCredentialsForService(@NotNull String uuid, int appId, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable String clientDeviceId, @Nullable String clientExternalDeviceId);

    @NotNull
    ApiMethodCall<AuthGetCredentialsForServiceMultiResponseDto> authGetCredentialsForServiceMulti(int appId, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable List<String> accessTokens, @Nullable List<String> exchangeTokens, @Nullable String superAppToken, @Nullable String clientDeviceId, @Nullable String clientExternalDeviceId);

    @NotNull
    ApiMethodCall<AuthGetExchangeTokenResponseDto> authGetExchangeToken(@Nullable Boolean createCommonToken, @Nullable Boolean createTierTokens, @Nullable Boolean needServiceMa);

    @NotNull
    ApiMethodCall<AuthGetExchangeTokenInfoResponseDto> authGetExchangeTokenInfo(@Nullable String exchangeToken, @Nullable String deviceId, @Nullable Integer targetAppId);

    @NotNull
    ApiMethodCall<List<AuthExchangeTokenInfoDto>> authGetExchangeTokensInfo(@Nullable List<String> exchangeTokens, @Nullable String deviceId, @Nullable Integer targetAppId);

    @NotNull
    ApiMethodCall<AuthGetQrAuthDataResponseDto> authGetQrAuthData(int appId, @Nullable String origin, @Nullable String appSettings, @Nullable String uuid, @Nullable Integer scope, @Nullable String scopeString, @Nullable String oauthVersion, @Nullable String scheme, @Nullable String httpReferer, @Nullable String httpOrigin);

    @NotNull
    ApiMethodCall<List<AuthSilentProviderDto>> authGetSilentAuthProviders();

    @NotNull
    ApiMethodCall<AppsGetSubAppInfoResponseDto> authGetSubAppInfo(@Nullable Integer clientId, @Nullable String clientSecret, @Nullable Integer subappId);

    @NotNull
    ApiMethodCall<AuthGetUserInfoByPhoneResponseDto> authGetUserInfoByPhone(@NotNull String superAppToken, @Nullable Boolean isDev);

    @NotNull
    ApiMethodCall<AuthGetWebAuthLinkResponseDto> authGetWebAuthLink(@NotNull String deviceId, @Nullable String to);

    @NotNull
    ApiMethodCall<AuthInitPasswordCheckResponseDto> authInitPasswordCheck(@Nullable Integer clientId, @Nullable String token, @Nullable String accessFactor);

    @NotNull
    ApiMethodCall<AuthInvalidateExchangeTokenMultiResponseDto> authInvalidateExchangeTokenMulti(@NotNull List<String> exchangeTokens);

    @NotNull
    ApiMethodCall<BaseOkResponseDto> authLogout(int clientId);

    @NotNull
    ApiMethodCall<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull String sid, @NotNull String lang, @Nullable String maxMessengerHash);

    @NotNull
    ApiMethodCall<AuthProcessAuthCodeResponseDto> authProcessAuthCodeMulti(@NotNull String authCode, @Nullable List<String> accessTokens, @Nullable Integer action, @Nullable String deviceIp, @Nullable String mapStyle, @Nullable Integer mapHeight, @Nullable Integer mapWidth, @Nullable Boolean isInternalCamera);

    @NotNull
    ApiMethodCall<AuthProcessAuthHashResponseDto> authProcessAuthHash(@NotNull String authHash, int action);

    @NotNull
    ApiMethodCall<AuthRefreshTokensResponseDto> authRefreshTokens(int clientId, @NotNull String clientSecret, @NotNull List<String> exchangeTokens, @Nullable String deviceId, @Nullable List<String> passwords, @Nullable Integer activeIndex, @Nullable String scope, @Nullable String initiator, @Nullable String validateSession, @Nullable Boolean silentAuthByLogin);

    @NotNull
    ApiMethodCall<AuthRefreshTrustedHashesResponseDto> authRefreshTrustedHashes(@NotNull List<String> accessTokens, @NotNull List<String> trustedHashes, @Nullable String deviceId);

    @NotNull
    ApiMethodCall<AuthSetAuthCodeStatusResponseDto> authSetAuthCodeStatus(@NotNull String authCode);

    @NotNull
    ApiMethodCall<AuthSignupResponseDto> authSignup(int clientId, @NotNull String clientSecret, @Nullable String firstName, @Nullable String middleName, @Nullable String lastName, @Nullable String birthday, @Nullable String phone, @Nullable String password, @Nullable Boolean testMode, @Nullable Boolean voice, @Nullable AuthSignupSexDto sex, @Nullable String sid, @Nullable Boolean libverifySupport, @Nullable String fullName, @Nullable String deviceId, @Nullable Boolean extend, @Nullable String validateSession, @Nullable Boolean canSkipPassword, @Nullable Boolean createEducational, @Nullable String superAppToken, @Nullable Boolean isExternalCarousel, @Nullable String inviteHash);

    @NotNull
    ApiMethodCall<AuthTerminateAuthCodeResponseDto> authTerminateAuthCode(@NotNull String authCode);

    @NotNull
    ApiMethodCall<AuthValidateAccountResponseDto> authValidateAccount(@Nullable String login, @Nullable String sid, @Nullable Boolean forcePassword, @Nullable String superAppToken, @Nullable Integer passkeySupported, @Nullable List<? extends AuthValidateAccountSupportedWaysDto> supportedWays, @Nullable Boolean isSwitcherFlow, @Nullable String deviceId, @Nullable String trustedHash, @Nullable Boolean isEduFlow, @Nullable Boolean isRegistration, @Nullable List<String> accountsTrustedHashes, @Nullable List<String> exchangeTokens, @Nullable Boolean startMailruFlow, @Nullable Boolean isBindFlow, @Nullable Boolean saveAuth, @Nullable String mailToken, @Nullable String codeChallenge, @Nullable String codeChallengeMethod, @Nullable String platform, @Nullable String multiaccountUsers, @Nullable String multiaccountUsersHash, @Nullable Boolean maxMessengerEnabled);

    @NotNull
    ApiMethodCall<AuthValidateAuthCodeResponseDto> authValidateAuthCode(@NotNull String authHash, @Nullable String validationCode);

    @NotNull
    ApiMethodCall<AuthValidateEmailResponseDto> authValidateEmail(@NotNull String sid, int clientId, @Nullable String email);

    @NotNull
    ApiMethodCall<AuthValidatePhoneConfirmResponseDto> authValidateEmailConfirm(@NotNull String sid, @NotNull String code, int clientId);

    @NotNull
    ApiMethodCall<AuthValidateLoginResponseDto> authValidateLogin(@NotNull String login, int clientId, @Nullable String sid, @Nullable String source, @Nullable String superAppToken, @Nullable Integer passkeySupported);

    @NotNull
    ApiMethodCall<BaseBoolIntDto> authValidatePhone(@Nullable String sid, @Nullable String phone, @Nullable Boolean libverifySupport, @Nullable Boolean voice, @Nullable Integer clientId, @Nullable String deviceId, @Nullable Boolean disablePartial, @Nullable Boolean force, @Nullable Boolean allowCallreset, @Nullable List<? extends AuthValidatePhoneSupportedWaysDto> supportedWays, @Nullable List<? extends AuthValidatePhoneSupportedWaysSettingsDto> supportedWaysSettings, @Nullable String flowStartState, @Nullable String superAppToken, @Nullable Boolean maxMessengerEnabled, @Nullable Boolean tvRegistrationSupport);

    @NotNull
    ApiMethodCall<AuthValidatePhoneCancelResponseDto> authValidatePhoneCancel(@NotNull String sid, @Nullable String reason);

    @NotNull
    ApiMethodCall<AuthValidatePhoneCheckResponseDto> authValidatePhoneCheck(boolean isAuth, @Nullable Integer appId, @Nullable AuthValidatePhoneCheckModeDto mode);

    @NotNull
    ApiMethodCall<BaseOkResponseDto> authValidatePhoneCheckSkip(@Nullable String sid);

    @NotNull
    ApiMethodCall<AuthValidatePhoneConfirmResponseDto> authValidatePhoneConfirm(@NotNull String sid, @Nullable String phone, @Nullable String code, @Nullable String validateSession, @Nullable String validateToken, @Nullable String clientId, @Nullable String deviceId, @Nullable Boolean canSkipPassword, @Nullable Boolean isCodeAutocomplete, @Nullable String flowStartState, @Nullable String verificationType, @Nullable Boolean isRegistration);

    @NotNull
    ApiMethodCall<AuthValidatePhoneInfoResponseDto> authValidatePhoneInfo(@NotNull String sid, @NotNull String phone);

    @NotNull
    ApiMethodCall<AuthValidateSuperAppTokenResponseDto> authValidateSuperAppToken(int clientId, @NotNull String token);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAuthService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthService.kt\ncom/vk/superapp/api/generated/auth/AuthService$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 GsonExt.kt\ncom/vk/superapp/api/generated/GsonExtKt\n*L\n1#1,1410:1\n1#2:1411\n1563#3:1412\n1634#3,3:1413\n1563#3:1416\n1634#3,3:1417\n1563#3:1420\n1634#3,3:1421\n45#4,2:1424\n49#4,2:1426\n45#4,2:1428\n49#4,2:1430\n45#4,2:1432\n49#4,2:1434\n45#4,2:1436\n49#4,2:1438\n45#4,2:1440\n49#4,2:1442\n45#4,2:1444\n49#4,2:1446\n45#4,2:1448\n49#4,2:1450\n45#4,2:1452\n49#4,2:1454\n45#4,2:1456\n49#4,2:1458\n53#4,5:1460\n61#4,4:1465\n45#4,2:1469\n49#4,2:1471\n45#4,2:1473\n49#4,2:1475\n45#4,2:1477\n49#4,2:1479\n45#4,2:1481\n49#4,2:1483\n45#4,2:1485\n49#4,2:1487\n45#4,2:1489\n49#4,2:1491\n45#4,2:1493\n49#4,2:1495\n53#4,5:1497\n61#4,4:1502\n45#4,2:1506\n49#4,2:1508\n45#4,2:1510\n49#4,2:1512\n45#4,2:1514\n49#4,2:1516\n53#4,5:1518\n61#4,4:1523\n45#4,2:1527\n49#4,2:1529\n53#4,5:1531\n61#4,4:1536\n45#4,2:1540\n49#4,2:1542\n45#4,2:1544\n49#4,2:1546\n45#4,2:1548\n49#4,2:1550\n45#4,2:1552\n49#4,2:1554\n45#4,2:1556\n49#4,2:1558\n45#4,2:1560\n49#4,2:1562\n45#4,2:1564\n49#4,2:1566\n45#4,2:1568\n49#4,2:1570\n45#4,2:1572\n49#4,2:1574\n45#4,2:1576\n49#4,2:1578\n45#4,2:1580\n49#4,2:1582\n45#4,2:1584\n49#4,2:1586\n45#4,2:1588\n49#4,2:1590\n45#4,2:1592\n49#4,2:1594\n45#4,2:1596\n49#4,2:1598\n45#4,2:1600\n49#4,2:1602\n45#4,2:1604\n49#4,2:1606\n45#4,2:1608\n49#4,2:1610\n45#4,2:1612\n49#4,2:1614\n45#4,2:1616\n49#4,2:1618\n45#4,2:1620\n49#4,2:1622\n45#4,2:1624\n49#4,2:1626\n45#4,2:1628\n49#4,2:1630\n45#4,2:1632\n49#4,2:1634\n45#4,2:1636\n49#4,2:1638\n45#4,2:1640\n49#4,2:1642\n*S KotlinDebug\n*F\n+ 1 AuthService.kt\ncom/vk/superapp/api/generated/auth/AuthService$DefaultImpls\n*L\n1088#1:1412\n1088#1:1413,3\n1245#1:1416\n1245#1:1417,3\n1249#1:1420\n1249#1:1421,3\n110#1:1424,2\n111#1:1426,2\n131#1:1428,2\n132#1:1430,2\n148#1:1432,2\n149#1:1434,2\n162#1:1436,2\n163#1:1438,2\n175#1:1440,2\n176#1:1442,2\n188#1:1444,2\n189#1:1446,2\n199#1:1448,2\n200#1:1450,2\n216#1:1452,2\n217#1:1454,2\n241#1:1456,2\n242#1:1458,2\n259#1:1460,5\n260#1:1465,4\n278#1:1469,2\n279#1:1471,2\n312#1:1473,2\n313#1:1475,2\n334#1:1477,2\n335#1:1479,2\n410#1:1481,2\n411#1:1483,2\n461#1:1485,2\n462#1:1487,2\n486#1:1489,2\n487#1:1491,2\n503#1:1493,2\n504#1:1495,2\n529#1:1497,5\n530#1:1502,4\n565#1:1506,2\n566#1:1508,2\n591#1:1510,2\n592#1:1512,2\n613#1:1514,2\n614#1:1516,2\n635#1:1518,5\n636#1:1523,4\n669#1:1527,2\n670#1:1529,2\n690#1:1531,5\n691#1:1536,4\n705#1:1540,2\n706#1:1542,2\n721#1:1544,2\n722#1:1546,2\n736#1:1548,2\n737#1:1550,2\n755#1:1552,2\n756#1:1554,2\n770#1:1556,2\n771#1:1558,2\n782#1:1560,2\n783#1:1562,2\n800#1:1564,2\n801#1:1566,2\n830#1:1568,2\n831#1:1570,2\n851#1:1572,2\n852#1:1574,2\n884#1:1576,2\n885#1:1578,2\n911#1:1580,2\n912#1:1582,2\n926#1:1584,2\n927#1:1586,2\n988#1:1588,2\n989#1:1590,2\n1022#1:1592,2\n1023#1:1594,2\n1080#1:1596,2\n1081#1:1598,2\n1119#1:1600,2\n1120#1:1602,2\n1138#1:1604,2\n1139#1:1606,2\n1158#1:1608,2\n1159#1:1610,2\n1184#1:1612,2\n1185#1:1614,2\n1233#1:1616,2\n1234#1:1618,2\n1267#1:1620,2\n1268#1:1622,2\n1286#1:1624,2\n1287#1:1626,2\n1301#1:1628,2\n1302#1:1630,2\n1339#1:1632,2\n1340#1:1634,2\n1364#1:1636,2\n1365#1:1638,2\n1379#1:1640,2\n1380#1:1642,2\n*E\n"})
    public static final class DefaultImpls {
        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> authBindExtOAuth(@NotNull AuthService authService, @Nullable String str, @Nullable String str2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.bindExtOAuth", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.t2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoca(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.u2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoca(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SilentAuthInfo.KEY_UUID, str2, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authBindExtOAuth$default(AuthService authService, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authBindExtOAuth");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            return authService.authBindExtOAuth(str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthCheckAccessResponseDto> authCheckAccess(@NotNull AuthService authService, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.checkAccess", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.t1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocb(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.u1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocb(inputStream);
                }
            });
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "password", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code", str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authCheckAccess$default(AuthService authService, Integer num, String str, String str2, String str3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authCheckAccess");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            if ((i10 & 8) != 0) {
                str3 = null;
            }
            return authService.authCheckAccess(num, str, str2, str3);
        }

        @NotNull
        public static ApiMethodCall<AuthCheckAuthCodeResponseDto> authCheckAuthCode(@NotNull AuthService authService, @NotNull String authHash, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(authHash, "authHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.checkAuthCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.g2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocc(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.h2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocc(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_hash", authHash, 0, 0, 12, (Object) null);
            if (bool != null) {
                internalApiMethodCall.addParam("web_auth", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authCheckAuthCode$default(AuthService authService, String str, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authCheckAuthCode");
            }
            if ((i10 & 2) != 0) {
                bool = null;
            }
            return authService.authCheckAuthCode(str, bool);
        }

        @NotNull
        public static ApiMethodCall<AuthCheckAuthHashResponseDto> authCheckAuthHash(@NotNull AuthService authService, @NotNull String authHash) {
            Intrinsics.checkNotNullParameter(authHash, "authHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.checkAuthHash", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.s0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocd(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.d1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocd(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_hash", authHash, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthCheckBindExtOAuthResponseDto> authCheckBindExtOAuth(@NotNull AuthService authService, @Nullable List<JsonObject> list) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.checkBindExtOAuth", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.x0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoce(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.y0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoce(inputStream);
                }
            });
            if (list != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_tokens", GsonHolder.INSTANCE.getGson().toJson(list), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authCheckBindExtOAuth$default(AuthService authService, List list, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authCheckBindExtOAuth");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            return authService.authCheckBindExtOAuth(list);
        }

        @NotNull
        public static ApiMethodCall<AuthCheckValidationStatusResponseDto> authCheckValidationStatus(@NotNull AuthService authService, @NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.checkValidationStatus", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.f0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocf(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.g0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocf(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code", authCode, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthCreateAuthCodeResponseDto> authCreateAuthCode(@NotNull AuthService authService) {
            return new InternalApiMethodCall("auth.createAuthCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.q0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocg(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.r0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocg(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<AuthExchangeSilentTokenToSidResponseDto> authExchangeSilentTokensToSid(@NotNull AuthService authService, @NotNull List<JsonObject> silentTokens, @NotNull String sid, @NotNull String phone, @NotNull String lang) {
            Intrinsics.checkNotNullParameter(silentTokens, "silentTokens");
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(phone, "phone");
            Intrinsics.checkNotNullParameter(lang, "lang");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.exchangeSilentTokensToSid", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.d0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoch(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.e0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoch(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_tokens", GsonHolder.INSTANCE.getGson().toJson(silentTokens), 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", phone, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "lang", lang, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthExternalFlowOutResponseDto> authExternalFlowOut(@NotNull AuthService authService, @NotNull String superAppToken, @NotNull AuthExternalFlowOutPlatformDto platform, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            Intrinsics.checkNotNullParameter(superAppToken, "superAppToken");
            Intrinsics.checkNotNullParameter(platform, "platform");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.externalFlowOut", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.n2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoci(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.o2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoci(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", superAppToken, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "platform", platform.getValue(), 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "state", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "redirect_uri", str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authExternalFlowOut$default(AuthService authService, String str, AuthExternalFlowOutPlatformDto authExternalFlowOutPlatformDto, String str2, String str3, String str4, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authExternalFlowOut");
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            if ((i10 & 8) != 0) {
                str3 = null;
            }
            if ((i10 & 16) != 0) {
                str4 = null;
            }
            return authService.authExternalFlowOut(str, authExternalFlowOutPlatformDto, str2, str3, str4);
        }

        @NotNull
        public static ApiMethodCall<List<AuthSilentTokenShortDto>> authFilterSilentTokens(@NotNull AuthService authService, @NotNull List<JsonObject> silentTokens, @Nullable String str) {
            Intrinsics.checkNotNullParameter(silentTokens, "silentTokens");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.filterSilentTokens", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.t0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocj(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.u0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocj(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_tokens", GsonHolder.INSTANCE.getGson().toJson(silentTokens), 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authFilterSilentTokens$default(AuthService authService, List list, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authFilterSilentTokens");
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            return authService.authFilterSilentTokens(list, str);
        }

        @NotNull
        public static ApiMethodCall<AppsGetScopesResponseDto> authGetAppScopes(@NotNull AuthService authService, @Nullable Integer num, @Nullable String str, @Nullable Integer num2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getAppScopes", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.z
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmock(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.a0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmock(inputStream);
                }
            });
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SharedKt.PARAM_CLIENT_SECRET, str, 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "service_id", num2.intValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetAppScopes$default(AuthService authService, Integer num, String str, Integer num2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetAppScopes");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                num2 = null;
            }
            return authService.authGetAppScopes(num, str, num2);
        }

        @NotNull
        public static ApiMethodCall<AuthGetAuthCodeResponseDto> authGetAuthCode(@NotNull AuthService authService, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Boolean bool, @Nullable String str3, @Nullable Boolean bool2, @Nullable Boolean bool3, @Nullable String str4) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getAuthCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.i0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocl(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.j0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocl(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, DeviceInfo.PARAM_KEY_DEVICE_NAME, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, str2, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code_type", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code_flow", num2.intValue(), 0, 0, 12, (Object) null);
            }
            if (num3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "qr_code_size", num3.intValue(), 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("need_qr_code", bool.booleanValue());
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "verification_hash", str3, 0, 0, 12, (Object) null);
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("force_regenerate", bool2.booleanValue());
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("is_switcher_flow", bool3.booleanValue());
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "page_link", str4, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetAuthCode$default(AuthService authService, String str, String str2, Integer num, Integer num2, Integer num3, Boolean bool, String str3, Boolean bool2, Boolean bool3, String str4, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetAuthCode");
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
                bool = null;
            }
            if ((i10 & 64) != 0) {
                str3 = null;
            }
            if ((i10 & 128) != 0) {
                bool2 = null;
            }
            if ((i10 & 256) != 0) {
                bool3 = null;
            }
            if ((i10 & 512) != 0) {
                str4 = null;
            }
            return authService.authGetAuthCode(str, str2, num, num2, num3, bool, str3, bool2, bool3, str4);
        }

        @NotNull
        public static ApiMethodCall<AuthGetAuthCodeStatusResponseDto> authGetAuthCodeStatus(@NotNull AuthService authService, @NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getAuthCodeStatus", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.b0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocm(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.c0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocm(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code", authCode, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthGetAuthDataResponseDto> authGetAuthData(@NotNull AuthService authService, int i10, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14, @Nullable String str15, @Nullable String str16, @Nullable String str17, @Nullable String str18, @Nullable String str19, @Nullable String str20, @Nullable String str21, @Nullable Boolean bool2, @Nullable String str22, @Nullable String str23, @Nullable String str24, @Nullable String str25, @Nullable String str26, @Nullable String str27, @Nullable String str28, @Nullable String str29) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getAuthData", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.i2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocn(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.j2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocn(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "origin", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "state", str2, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("sferum_logo", bool.booleanValue());
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "vkme_flow_type", str3, 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "type_carousel", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "screen", str5, 0, 0, 12, (Object) null);
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "redirect_uri_hash", str6, 0, 0, 12, (Object) null);
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_metadata", str7, 0, 0, 12, (Object) null);
            }
            if (str8 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_settings", str8, 0, 0, 12, (Object) null);
            }
            if (str9 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SilentAuthInfo.KEY_UUID, str9, 0, 0, 12, (Object) null);
            }
            if (str10 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code_challenge", str10, 0, 0, 12, (Object) null);
            }
            if (str11 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code_challenge_method", str11, 0, 0, 12, (Object) null);
            }
            if (str12 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, str12, 0, 0, 12, (Object) null);
            }
            if (str13 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "scope_string", str13, 0, 0, 12, (Object) null);
            }
            if (str14 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str14, 0, 0, 12, (Object) null);
            }
            if (str15 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "redirect_state", str15, 0, 0, 12, (Object) null);
            }
            if (str16 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "oauth_version", str16, 0, 0, 12, (Object) null);
            }
            if (str17 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "scheme", str17, 0, 0, 12, (Object) null);
            }
            if (str18 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, HianalyticsBaseData.SDK_TYPE, str18, 0, 0, 12, (Object) null);
            }
            if (str19 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "http_referer", str19, 0, 0, 12, (Object) null);
            }
            if (str20 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "http_origin", str20, 0, 0, 12, (Object) null);
            }
            if (str21 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, str21, 0, 0, 12, (Object) null);
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("force_hash", bool2.booleanValue());
            }
            if (str22 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "restore_auth_token", str22, 0, 0, 12, (Object) null);
            }
            if (str23 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "return_auth_hash", str23, 0, 0, 12, (Object) null);
            }
            if (str24 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "redirect_uri", str24, 0, 0, 12, (Object) null);
            }
            if (str25 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "stats_info", str25, 0, 0, 12, (Object) null);
            }
            if (str26 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "action", str26, 0, 0, 12, (Object) null);
            }
            if (str27 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str27, 0, 0, 12, (Object) null);
            }
            if (str28 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "prompt", str28, 0, 0, 12, (Object) null);
            }
            if (str29 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "initial_uri", str29, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetAuthData$default(AuthService authService, int i10, String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, Boolean bool2, String str22, String str23, String str24, String str25, String str26, String str27, String str28, String str29, int i11, Object obj) {
            if (obj == null) {
                return authService.authGetAuthData(i10, (i11 & 2) != 0 ? null : str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : bool, (i11 & 16) != 0 ? null : str3, (i11 & 32) != 0 ? null : str4, (i11 & 64) != 0 ? null : str5, (i11 & 128) != 0 ? null : str6, (i11 & 256) != 0 ? null : str7, (i11 & 512) != 0 ? null : str8, (i11 & 1024) != 0 ? null : str9, (i11 & 2048) != 0 ? null : str10, (i11 & 4096) != 0 ? null : str11, (i11 & 8192) != 0 ? null : str12, (i11 & 16384) != 0 ? null : str13, (i11 & 32768) != 0 ? null : str14, (i11 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? null : str15, (i11 & 131072) != 0 ? null : str16, (i11 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? null : str17, (i11 & 524288) != 0 ? null : str18, (i11 & 1048576) != 0 ? null : str19, (i11 & 2097152) != 0 ? null : str20, (i11 & 4194304) != 0 ? null : str21, (i11 & RemoteFilesRepository.BYTE_ARRAY_SIZE) != 0 ? null : bool2, (i11 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 ? null : str22, (i11 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? null : str23, (i11 & 67108864) != 0 ? null : str24, (i11 & 134217728) != 0 ? null : str25, (i11 & SQLiteDatabase.CREATE_IF_NECESSARY) != 0 ? null : str26, (i11 & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? null : str27, (i11 & 1073741824) != 0 ? null : str28, (i11 & Integer.MIN_VALUE) != 0 ? null : str29);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetAuthData");
        }

        @NotNull
        public static ApiMethodCall<AuthGetAutologinCredentialsResponseDto> authGetAutologinCredentials(@NotNull AuthService authService, @Nullable List<String> list, @Nullable List<String> list2, @Nullable String str, @Nullable String str2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getAutologinCredentials", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.a
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoco(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.l
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoco(inputStream);
                }
            });
            if (list != null) {
                internalApiMethodCall.addParam("silent_tokens", list);
            }
            if (list2 != null) {
                internalApiMethodCall.addParam("exchange_tokens", list2);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SilentAuthInfo.KEY_UUID, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, EventParams.SESSION_ID, str2, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authGetAutologinCredentials$default(AuthService authService, List list, List list2, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetAutologinCredentials");
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
            return authService.authGetAutologinCredentials(list, list2, str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthGetContinuationForServiceResponseDto> authGetContinuationForService(@NotNull AuthService authService, @Nullable Integer num, @Nullable Integer num2, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getContinuationForService", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.q
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocp(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.r
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocp(inputStream);
                }
            });
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", num2.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "silent_token_uuid", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone_validation_sid", str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetContinuationForService$default(AuthService authService, Integer num, Integer num2, String str, String str2, String str3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetContinuationForService");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                num2 = null;
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            if ((i10 & 8) != 0) {
                str2 = null;
            }
            if ((i10 & 16) != 0) {
                str3 = null;
            }
            return authService.authGetContinuationForService(num, num2, str, str2, str3);
        }

        @NotNull
        public static ApiMethodCall<AuthGetSilentTokensResponseDto> authGetCredentialsForApp(@NotNull AuthService authService, int i10) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getCredentialsForApp", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.i1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocq(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.j1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocq(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 8, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<List<AuthSilentTokenDto>> authGetCredentialsForService(@NotNull AuthService authService, @NotNull String uuid, int i10, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable String str, @Nullable String str2) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(packageValue, "packageValue");
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(digestHash, "digestHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getCredentialsForService", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.m0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocr(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.n0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocr(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, SilentAuthInfo.KEY_UUID, uuid, 10, 0, 8, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 8, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "package", packageValue, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "timestamp", timestamp, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "digest_hash", digestHash, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_device_id", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_external_device_id", str2, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetCredentialsForService$default(AuthService authService, String str, int i10, String str2, String str3, String str4, String str5, String str6, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetCredentialsForService");
            }
            if ((i11 & 32) != 0) {
                str5 = null;
            }
            if ((i11 & 64) != 0) {
                str6 = null;
            }
            return authService.authGetCredentialsForService(str, i10, str2, str3, str4, str5, str6);
        }

        @NotNull
        public static ApiMethodCall<AuthGetCredentialsForServiceMultiResponseDto> authGetCredentialsForServiceMulti(@NotNull AuthService authService, int i10, @NotNull String packageValue, @NotNull String timestamp, @NotNull String digestHash, @Nullable List<String> list, @Nullable List<String> list2, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            Intrinsics.checkNotNullParameter(packageValue, "packageValue");
            Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            Intrinsics.checkNotNullParameter(digestHash, "digestHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getCredentialsForServiceMulti", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.o
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocs(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.p
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocs(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 8, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "package", packageValue, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "timestamp", timestamp, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "digest_hash", digestHash, 0, 0, 12, (Object) null);
            if (list != null) {
                internalApiMethodCall.addParam("access_tokens", list);
            }
            if (list2 != null) {
                internalApiMethodCall.addParam("exchange_tokens", list2);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_device_id", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_external_device_id", str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authGetCredentialsForServiceMulti$default(AuthService authService, int i10, String str, String str2, String str3, List list, List list2, String str4, String str5, String str6, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetCredentialsForServiceMulti");
            }
            if ((i11 & 16) != 0) {
                list = null;
            }
            if ((i11 & 32) != 0) {
                list2 = null;
            }
            if ((i11 & 64) != 0) {
                str4 = null;
            }
            if ((i11 & 128) != 0) {
                str5 = null;
            }
            if ((i11 & 256) != 0) {
                str6 = null;
            }
            return authService.authGetCredentialsForServiceMulti(i10, str, str2, str3, list, list2, str4, str5, str6);
        }

        @NotNull
        public static ApiMethodCall<AuthGetExchangeTokenResponseDto> authGetExchangeToken(@NotNull AuthService authService, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getExchangeToken", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.p1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmoct(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.q1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmoct(inputStream);
                }
            });
            if (bool != null) {
                internalApiMethodCall.addParam("create_common_token", bool.booleanValue());
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("create_tier_tokens", bool2.booleanValue());
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("need_service_ma", bool3.booleanValue());
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetExchangeToken$default(AuthService authService, Boolean bool, Boolean bool2, Boolean bool3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetExchangeToken");
            }
            if ((i10 & 1) != 0) {
                bool = null;
            }
            if ((i10 & 2) != 0) {
                bool2 = null;
            }
            if ((i10 & 4) != 0) {
                bool3 = null;
            }
            return authService.authGetExchangeToken(bool, bool2, bool3);
        }

        @NotNull
        public static ApiMethodCall<AuthGetExchangeTokenInfoResponseDto> authGetExchangeTokenInfo(@NotNull AuthService authService, @Nullable String str, @Nullable String str2, @Nullable Integer num) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getExchangeTokenInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.x
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocu(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.y
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocu(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "exchange_token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str2, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "target_app_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetExchangeTokenInfo$default(AuthService authService, String str, String str2, Integer num, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetExchangeTokenInfo");
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
            return authService.authGetExchangeTokenInfo(str, str2, num);
        }

        @NotNull
        public static ApiMethodCall<List<AuthExchangeTokenInfoDto>> authGetExchangeTokensInfo(@NotNull AuthService authService, @Nullable List<String> list, @Nullable String str, @Nullable Integer num) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getExchangeTokensInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.l2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocv(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.m2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocv(inputStream);
                }
            });
            if (list != null) {
                internalApiMethodCall.addParam("exchange_tokens", list);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "target_app_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authGetExchangeTokensInfo$default(AuthService authService, List list, String str, Integer num, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetExchangeTokensInfo");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            return authService.authGetExchangeTokensInfo(list, str, num);
        }

        @NotNull
        public static ApiMethodCall<AuthGetQrAuthDataResponseDto> authGetQrAuthData(@NotNull AuthService authService, int i10, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getQrAuthData", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.o1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocw(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.z1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocw(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", i10, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "origin", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_settings", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SilentAuthInfo.KEY_UUID, str3, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "scope_string", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "oauth_version", str5, 0, 0, 12, (Object) null);
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "scheme", str6, 0, 0, 12, (Object) null);
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "http_referer", str7, 0, 0, 12, (Object) null);
            }
            if (str8 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "http_origin", str8, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetQrAuthData$default(AuthService authService, int i10, String str, String str2, String str3, Integer num, String str4, String str5, String str6, String str7, String str8, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetQrAuthData");
            }
            if ((i11 & 2) != 0) {
                str = null;
            }
            if ((i11 & 4) != 0) {
                str2 = null;
            }
            if ((i11 & 8) != 0) {
                str3 = null;
            }
            if ((i11 & 16) != 0) {
                num = null;
            }
            if ((i11 & 32) != 0) {
                str4 = null;
            }
            if ((i11 & 64) != 0) {
                str5 = null;
            }
            if ((i11 & 128) != 0) {
                str6 = null;
            }
            if ((i11 & 256) != 0) {
                str7 = null;
            }
            if ((i11 & 512) != 0) {
                str8 = null;
            }
            return authService.authGetQrAuthData(i10, str, str2, str3, num, str4, str5, str6, str7, str8);
        }

        @NotNull
        public static ApiMethodCall<List<AuthSilentProviderDto>> authGetSilentAuthProviders(@NotNull AuthService authService) {
            return new InternalApiMethodCall("auth.getSilentAuthProviders", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.b
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocx(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.c
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocx(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<AppsGetSubAppInfoResponseDto> authGetSubAppInfo(@NotNull AuthService authService, @Nullable Integer num, @Nullable String str, @Nullable Integer num2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getSubAppInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.c2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocy(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.d2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocy(inputStream);
                }
            });
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SharedKt.PARAM_CLIENT_SECRET, str, 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "subapp_id", num2.intValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetSubAppInfo$default(AuthService authService, Integer num, String str, Integer num2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetSubAppInfo");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                num2 = null;
            }
            return authService.authGetSubAppInfo(num, str, num2);
        }

        @NotNull
        public static ApiMethodCall<AuthGetUserInfoByPhoneResponseDto> authGetUserInfoByPhone(@NotNull AuthService authService, @NotNull String superAppToken, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(superAppToken, "superAppToken");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getUserInfoByPhone", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.e2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocz(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.f2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocz(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", superAppToken, 0, 0, 12, (Object) null);
            if (bool != null) {
                internalApiMethodCall.addParam("is_dev", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetUserInfoByPhone$default(AuthService authService, String str, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetUserInfoByPhone");
            }
            if ((i10 & 2) != 0) {
                bool = null;
            }
            return authService.authGetUserInfoByPhone(str, bool);
        }

        @NotNull
        public static ApiMethodCall<AuthGetWebAuthLinkResponseDto> authGetWebAuthLink(@NotNull AuthService authService, @NotNull String deviceId, @Nullable String str) {
            Intrinsics.checkNotNullParameter(deviceId, "deviceId");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.getWebAuthLink", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.a2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocaa(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.b2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocaa(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", deviceId, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "to", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authGetWebAuthLink$default(AuthService authService, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authGetWebAuthLink");
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            return authService.authGetWebAuthLink(str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthInitPasswordCheckResponseDto> authInitPasswordCheck(@NotNull AuthService authService, @Nullable Integer num, @Nullable String str, @Nullable String str2) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.initPasswordCheck", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.x1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocab(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.y1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocab(inputStream);
                }
            });
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "token", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "access_factor", str2, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authInitPasswordCheck$default(AuthService authService, Integer num, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authInitPasswordCheck");
            }
            if ((i10 & 1) != 0) {
                num = null;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            return authService.authInitPasswordCheck(num, str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthInvalidateExchangeTokenMultiResponseDto> authInvalidateExchangeTokenMulti(@NotNull AuthService authService, @NotNull List<String> exchangeTokens) {
            Intrinsics.checkNotNullParameter(exchangeTokens, "exchangeTokens");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.invalidateExchangeTokenMulti", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.g1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocac(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.h1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocac(inputStream);
                }
            });
            internalApiMethodCall.addParam("exchange_tokens", exchangeTokens);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> authLogout(@NotNull AuthService authService, int i10) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.logout", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.k0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocad(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.l0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocad(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 8, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthOnSuccessValidationResponseDto> authOnSuccessValidation(@NotNull AuthService authService, @NotNull String sid, @NotNull String lang, @Nullable String str) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(lang, "lang");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.onSuccessValidation", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.h
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocae(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.i
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocae(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "lang", lang, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "max_messenger_hash", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authOnSuccessValidation$default(AuthService authService, String str, String str2, String str3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authOnSuccessValidation");
            }
            if ((i10 & 4) != 0) {
                str3 = null;
            }
            return authService.authOnSuccessValidation(str, str2, str3);
        }

        @NotNull
        public static ApiMethodCall<AuthProcessAuthCodeResponseDto> authProcessAuthCodeMulti(@NotNull AuthService authService, @NotNull String authCode, @Nullable List<String> list, @Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable Integer num2, @Nullable Integer num3, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.processAuthCodeMulti", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.p2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocaf(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.q2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocaf(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code", authCode, 0, 0, 12, (Object) null);
            if (list != null) {
                internalApiMethodCall.addParam("access_tokens", list);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "action", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_ip", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "map_style", str2, 0, 0, 12, (Object) null);
            }
            if (num2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "map_height", num2.intValue(), 0, 0, 12, (Object) null);
            }
            if (num3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "map_width", num3.intValue(), 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("is_internal_camera", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authProcessAuthCodeMulti$default(AuthService authService, String str, List list, Integer num, String str2, String str3, Integer num2, Integer num3, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authProcessAuthCodeMulti");
            }
            if ((i10 & 2) != 0) {
                list = null;
            }
            if ((i10 & 4) != 0) {
                num = null;
            }
            if ((i10 & 8) != 0) {
                str2 = null;
            }
            if ((i10 & 16) != 0) {
                str3 = null;
            }
            if ((i10 & 32) != 0) {
                num2 = null;
            }
            if ((i10 & 64) != 0) {
                num3 = null;
            }
            if ((i10 & 128) != 0) {
                bool = null;
            }
            return authService.authProcessAuthCodeMulti(str, list, num, str2, str3, num2, num3, bool);
        }

        @NotNull
        public static ApiMethodCall<AuthProcessAuthHashResponseDto> authProcessAuthHash(@NotNull AuthService authService, @NotNull String authHash, int i10) {
            Intrinsics.checkNotNullParameter(authHash, "authHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.processAuthHash", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.j
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocag(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.k
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocag(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_hash", authHash, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "action", i10, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthRefreshTokensResponseDto> authRefreshTokens(@NotNull AuthService authService, int i10, @NotNull String clientSecret, @NotNull List<String> exchangeTokens, @Nullable String str, @Nullable List<String> list, @Nullable Integer num, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool) {
            Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
            Intrinsics.checkNotNullParameter(exchangeTokens, "exchangeTokens");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.refreshTokens", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.o0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocah(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.p0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocah(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, SharedKt.PARAM_CLIENT_SECRET, clientSecret, 0, 0, 12, (Object) null);
            internalApiMethodCall.addParam("exchange_tokens", exchangeTokens);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            if (list != null) {
                internalApiMethodCall.addParam("passwords", list);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "active_index", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "initiator", str3, 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "validate_session", str4, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("silent_auth_by_login", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authRefreshTokens$default(AuthService authService, int i10, String str, List list, String str2, List list2, Integer num, String str3, String str4, String str5, Boolean bool, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authRefreshTokens");
            }
            if ((i11 & 8) != 0) {
                str2 = null;
            }
            if ((i11 & 16) != 0) {
                list2 = null;
            }
            if ((i11 & 32) != 0) {
                num = null;
            }
            if ((i11 & 64) != 0) {
                str3 = null;
            }
            if ((i11 & 128) != 0) {
                str4 = null;
            }
            if ((i11 & 256) != 0) {
                str5 = null;
            }
            if ((i11 & 512) != 0) {
                bool = null;
            }
            return authService.authRefreshTokens(i10, str, list, str2, list2, num, str3, str4, str5, bool);
        }

        @NotNull
        public static ApiMethodCall<AuthRefreshTrustedHashesResponseDto> authRefreshTrustedHashes(@NotNull AuthService authService, @NotNull List<String> accessTokens, @NotNull List<String> trustedHashes, @Nullable String str) {
            Intrinsics.checkNotNullParameter(accessTokens, "accessTokens");
            Intrinsics.checkNotNullParameter(trustedHashes, "trustedHashes");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.refreshTrustedHashes", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.r2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocai(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.s2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocai(inputStream);
                }
            });
            internalApiMethodCall.addParam("access_tokens", accessTokens);
            internalApiMethodCall.addParam(TrustedHashUtils.SOURCE_NAME, trustedHashes);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authRefreshTrustedHashes$default(AuthService authService, List list, List list2, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authRefreshTrustedHashes");
            }
            if ((i10 & 4) != 0) {
                str = null;
            }
            return authService.authRefreshTrustedHashes(list, list2, str);
        }

        @NotNull
        public static ApiMethodCall<AuthSetAuthCodeStatusResponseDto> authSetAuthCodeStatus(@NotNull AuthService authService, @NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.setAuthCodeStatus", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.u
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocaj(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.v
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocaj(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code", authCode, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthSignupResponseDto> authSignup(@NotNull AuthService authService, int i10, @NotNull String clientSecret, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable AuthSignupSexDto authSignupSexDto, @Nullable String str7, @Nullable Boolean bool3, @Nullable String str8, @Nullable String str9, @Nullable Boolean bool4, @Nullable String str10, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable String str11, @Nullable Boolean bool7, @Nullable String str12) {
            Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.signup", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.m1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocak(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.n1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocak(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, SharedKt.PARAM_CLIENT_SECRET, clientSecret, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "first_name", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, SystemContactEntity.COL_NAME_MIDDLE_NAME, str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "last_name", str3, 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "birthday", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", str5, 0, 0, 12, (Object) null);
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "password", str6, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("test_mode", bool.booleanValue());
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("voice", bool2.booleanValue());
            }
            if (authSignupSexDto != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "sex", authSignupSexDto.getValue(), 0, 0, 12, (Object) null);
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, str7, 0, 0, 12, (Object) null);
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("libverify_support", bool3.booleanValue());
            }
            if (str8 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "full_name", str8, 0, 0, 12, (Object) null);
            }
            if (str9 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str9, 0, 0, 12, (Object) null);
            }
            if (bool4 != null) {
                internalApiMethodCall.addParam("extend", bool4.booleanValue());
            }
            if (str10 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "validate_session", str10, 0, 0, 12, (Object) null);
            }
            if (bool5 != null) {
                internalApiMethodCall.addParam("can_skip_password", bool5.booleanValue());
            }
            if (bool6 != null) {
                internalApiMethodCall.addParam("create_educational", bool6.booleanValue());
            }
            if (str11 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str11, 0, 0, 12, (Object) null);
            }
            if (bool7 != null) {
                internalApiMethodCall.addParam("is_external_carousel", bool7.booleanValue());
            }
            if (str12 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "invite_hash", str12, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authSignup$default(AuthService authService, int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool, Boolean bool2, AuthSignupSexDto authSignupSexDto, String str8, Boolean bool3, String str9, String str10, Boolean bool4, String str11, Boolean bool5, Boolean bool6, String str12, Boolean bool7, String str13, int i11, Object obj) {
            if (obj == null) {
                return authService.authSignup(i10, str, (i11 & 4) != 0 ? null : str2, (i11 & 8) != 0 ? null : str3, (i11 & 16) != 0 ? null : str4, (i11 & 32) != 0 ? null : str5, (i11 & 64) != 0 ? null : str6, (i11 & 128) != 0 ? null : str7, (i11 & 256) != 0 ? null : bool, (i11 & 512) != 0 ? null : bool2, (i11 & 1024) != 0 ? null : authSignupSexDto, (i11 & 2048) != 0 ? null : str8, (i11 & 4096) != 0 ? null : bool3, (i11 & 8192) != 0 ? null : str9, (i11 & 16384) != 0 ? null : str10, (32768 & i11) != 0 ? null : bool4, (65536 & i11) != 0 ? null : str11, (131072 & i11) != 0 ? null : bool5, (262144 & i11) != 0 ? null : bool6, (524288 & i11) != 0 ? null : str12, (1048576 & i11) != 0 ? null : bool7, (i11 & 2097152) != 0 ? null : str13);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authSignup");
        }

        @NotNull
        public static ApiMethodCall<AuthTerminateAuthCodeResponseDto> authTerminateAuthCode(@NotNull AuthService authService, @NotNull String authCode) {
            Intrinsics.checkNotNullParameter(authCode, "authCode");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.terminateAuthCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.k2
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocal(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.v2
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocal(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_code", authCode, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthValidateAccountResponseDto> authValidateAccount(@NotNull AuthService authService, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, @Nullable String str3, @Nullable Integer num, @Nullable List<? extends AuthValidateAccountSupportedWaysDto> list, @Nullable Boolean bool2, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable List<String> list2, @Nullable List<String> list3, @Nullable Boolean bool5, @Nullable Boolean bool6, @Nullable Boolean bool7, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable Boolean bool8) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall;
            InternalApiMethodCall internalApiMethodCall2 = new InternalApiMethodCall("auth.validateAccount", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.s
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocam(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.t
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocam(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall2, "login", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall2, PasskeyBeginResult.SID_KEY, str2, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall2.addParam("force_password", bool.booleanValue());
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall2, "super_app_token", str3, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall2, "passkey_supported", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AuthValidateAccountSupportedWaysDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall2.addParam("supported_ways", arrayList);
            }
            if (bool2 != null) {
                internalApiMethodCall2.addParam("is_switcher_flow", bool2.booleanValue());
            }
            if (str4 != null) {
                internalApiMethodCall = internalApiMethodCall2;
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str4, 0, 0, 12, (Object) null);
            } else {
                internalApiMethodCall = internalApiMethodCall2;
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "trusted_hash", str5, 0, 0, 12, (Object) null);
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("is_edu_flow", bool3.booleanValue());
            }
            if (bool4 != null) {
                internalApiMethodCall.addParam("is_registration", bool4.booleanValue());
            }
            if (list2 != null) {
                internalApiMethodCall.addParam("accounts_trusted_hashes", list2);
            }
            if (list3 != null) {
                internalApiMethodCall.addParam("exchange_tokens", list3);
            }
            if (bool5 != null) {
                internalApiMethodCall.addParam("start_mailru_flow", bool5.booleanValue());
            }
            if (bool6 != null) {
                internalApiMethodCall.addParam("is_bind_flow", bool6.booleanValue());
            }
            if (bool7 != null) {
                internalApiMethodCall.addParam("save_auth", bool7.booleanValue());
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "mail_token", str6, 0, 0, 12, (Object) null);
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code_challenge", str7, 0, 0, 12, (Object) null);
            }
            if (str8 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code_challenge_method", str8, 0, 0, 12, (Object) null);
            }
            if (str9 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "platform", str9, 0, 0, 12, (Object) null);
            }
            if (str10 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "multiaccount_users", str10, 0, 0, 12, (Object) null);
            }
            if (str11 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "multiaccount_users_hash", str11, 0, 0, 12, (Object) null);
            }
            if (bool8 != null) {
                internalApiMethodCall.addParam("max_messenger_enabled", bool8.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authValidateAccount$default(AuthService authService, String str, String str2, Boolean bool, String str3, Integer num, List list, Boolean bool2, String str4, String str5, Boolean bool3, Boolean bool4, List list2, List list3, Boolean bool5, Boolean bool6, Boolean bool7, String str6, String str7, String str8, String str9, String str10, String str11, Boolean bool8, int i10, Object obj) {
            if (obj == null) {
                return authService.authValidateAccount((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : bool, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? null : num, (i10 & 32) != 0 ? null : list, (i10 & 64) != 0 ? null : bool2, (i10 & 128) != 0 ? null : str4, (i10 & 256) != 0 ? null : str5, (i10 & 512) != 0 ? null : bool3, (i10 & 1024) != 0 ? null : bool4, (i10 & 2048) != 0 ? null : list2, (i10 & 4096) != 0 ? null : list3, (i10 & 8192) != 0 ? null : bool5, (i10 & 16384) != 0 ? null : bool6, (i10 & 32768) != 0 ? null : bool7, (i10 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? null : str6, (i10 & 131072) != 0 ? null : str7, (i10 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? null : str8, (i10 & 524288) != 0 ? null : str9, (i10 & 1048576) != 0 ? null : str10, (i10 & 2097152) != 0 ? null : str11, (i10 & 4194304) != 0 ? null : bool8);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidateAccount");
        }

        @NotNull
        public static ApiMethodCall<AuthValidateAuthCodeResponseDto> authValidateAuthCode(@NotNull AuthService authService, @NotNull String authHash, @Nullable String str) {
            Intrinsics.checkNotNullParameter(authHash, "authHash");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validateAuthCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.r1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocan(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.s1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocan(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "auth_hash", authHash, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "validation_code", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidateAuthCode$default(AuthService authService, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidateAuthCode");
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            return authService.authValidateAuthCode(str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthValidateEmailResponseDto> authValidateEmail(@NotNull AuthService authService, @NotNull String sid, int i10, @Nullable String str) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validateEmail", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.v1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocao(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.w1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocao(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "email", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidateEmail$default(AuthService authService, String str, int i10, String str2, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidateEmail");
            }
            if ((i11 & 4) != 0) {
                str2 = null;
            }
            return authService.authValidateEmail(str, i10, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthValidatePhoneConfirmResponseDto> authValidateEmailConfirm(@NotNull AuthService authService, @NotNull String sid, @NotNull String code, int i10) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(code, "code");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validateEmailConfirm", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.k1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocap(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.l1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocap(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "code", code, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthValidateLoginResponseDto> authValidateLogin(@NotNull AuthService authService, @NotNull String login, int i10, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num) {
            Intrinsics.checkNotNullParameter(login, "login");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validateLogin", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.f
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocaq(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.g
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocaq(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "login", login, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "source", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str3, 0, 0, 12, (Object) null);
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "passkey_supported", num.intValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidateLogin$default(AuthService authService, String str, int i10, String str2, String str3, String str4, Integer num, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidateLogin");
            }
            if ((i11 & 4) != 0) {
                str2 = null;
            }
            if ((i11 & 8) != 0) {
                str3 = null;
            }
            if ((i11 & 16) != 0) {
                str4 = null;
            }
            if ((i11 & 32) != 0) {
                num = null;
            }
            return authService.authValidateLogin(str, i10, str2, str3, str4, num);
        }

        @NotNull
        public static ApiMethodCall<BaseBoolIntDto> authValidatePhone(@NotNull AuthService authService, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Integer num, @Nullable String str3, @Nullable Boolean bool3, @Nullable Boolean bool4, @Nullable Boolean bool5, @Nullable List<? extends AuthValidatePhoneSupportedWaysDto> list, @Nullable List<? extends AuthValidatePhoneSupportedWaysSettingsDto> list2, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool6, @Nullable Boolean bool7) {
            ArrayList arrayList;
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhone", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.d
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocar(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.e
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocar(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", str2, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("libverify_support", bool.booleanValue());
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("voice", bool2.booleanValue());
            }
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", num.intValue(), 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str3, 0, 0, 12, (Object) null);
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("disable_partial", bool3.booleanValue());
            }
            if (bool4 != null) {
                internalApiMethodCall.addParam("force", bool4.booleanValue());
            }
            if (bool5 != null) {
                internalApiMethodCall.addParam("allow_callreset", bool5.booleanValue());
            }
            ArrayList arrayList2 = null;
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AuthValidatePhoneSupportedWaysDto) it.next()).getValue());
                }
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                internalApiMethodCall.addParam("supported_ways", arrayList);
            }
            if (list2 != null) {
                arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((AuthValidatePhoneSupportedWaysSettingsDto) it2.next()).getValue());
                }
            }
            if (arrayList2 != null) {
                internalApiMethodCall.addParam("supported_ways_settings", arrayList2);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "flow_start_state", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "super_app_token", str5, 0, 0, 12, (Object) null);
            }
            if (bool6 != null) {
                internalApiMethodCall.addParam("max_messenger_enabled", bool6.booleanValue());
            }
            if (bool7 != null) {
                internalApiMethodCall.addParam("tv_registration_support", bool7.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall authValidatePhone$default(AuthService authService, String str, String str2, Boolean bool, Boolean bool2, Integer num, String str3, Boolean bool3, Boolean bool4, Boolean bool5, List list, List list2, String str4, String str5, Boolean bool6, Boolean bool7, int i10, Object obj) {
            if (obj == null) {
                return authService.authValidatePhone((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : bool, (i10 & 8) != 0 ? null : bool2, (i10 & 16) != 0 ? null : num, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : bool3, (i10 & 128) != 0 ? null : bool4, (i10 & 256) != 0 ? null : bool5, (i10 & 512) != 0 ? null : list, (i10 & 1024) != 0 ? null : list2, (i10 & 2048) != 0 ? null : str4, (i10 & 4096) != 0 ? null : str5, (i10 & 8192) != 0 ? null : bool6, (i10 & 16384) != 0 ? null : bool7);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidatePhone");
        }

        @NotNull
        public static ApiMethodCall<AuthValidatePhoneCancelResponseDto> authValidatePhoneCancel(@NotNull AuthService authService, @NotNull String sid, @Nullable String str) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhoneCancel", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.b1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocas(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.c1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocas(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "reason", str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidatePhoneCancel$default(AuthService authService, String str, String str2, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidatePhoneCancel");
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            return authService.authValidatePhoneCancel(str, str2);
        }

        @NotNull
        public static ApiMethodCall<AuthValidatePhoneCheckResponseDto> authValidatePhoneCheck(@NotNull AuthService authService, boolean z10, @Nullable Integer num, @Nullable AuthValidatePhoneCheckModeDto authValidatePhoneCheckModeDto) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhoneCheck", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.v0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocat(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.w0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocat(inputStream);
                }
            });
            internalApiMethodCall.addParam("is_auth", z10);
            if (num != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "app_id", num.intValue(), 0, 0, 8, (Object) null);
            }
            if (authValidatePhoneCheckModeDto != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "mode", authValidatePhoneCheckModeDto.getValue(), 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidatePhoneCheck$default(AuthService authService, boolean z10, Integer num, AuthValidatePhoneCheckModeDto authValidatePhoneCheckModeDto, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidatePhoneCheck");
            }
            if ((i10 & 2) != 0) {
                num = null;
            }
            if ((i10 & 4) != 0) {
                authValidatePhoneCheckModeDto = null;
            }
            return authService.authValidatePhoneCheck(z10, num, authValidatePhoneCheckModeDto);
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> authValidatePhoneCheckSkip(@NotNull AuthService authService, @Nullable String str) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhoneCheckSkip", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.w
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocau(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.h0
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocau(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidatePhoneCheckSkip$default(AuthService authService, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidatePhoneCheckSkip");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            return authService.authValidatePhoneCheckSkip(str);
        }

        @NotNull
        public static ApiMethodCall<AuthValidatePhoneConfirmResponseDto> authValidatePhoneConfirm(@NotNull AuthService authService, @NotNull String sid, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable String str7, @Nullable String str8, @Nullable Boolean bool3) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhoneConfirm", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.m
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocav(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.n
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocav(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "code", str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "validate_session", str3, 0, 0, 12, (Object) null);
            }
            if (str4 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "validate_token", str4, 0, 0, 12, (Object) null);
            }
            if (str5 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", str5, 0, 0, 12, (Object) null);
            }
            if (str6 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "device_id", str6, 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("can_skip_password", bool.booleanValue());
            }
            if (bool2 != null) {
                internalApiMethodCall.addParam("is_code_autocomplete", bool2.booleanValue());
            }
            if (str7 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "flow_start_state", str7, 0, 0, 12, (Object) null);
            }
            if (str8 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "verification_type", str8, 0, 0, 12, (Object) null);
            }
            if (bool3 != null) {
                internalApiMethodCall.addParam("is_registration", bool3.booleanValue());
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall authValidatePhoneConfirm$default(AuthService authService, String str, String str2, String str3, String str4, String str5, String str6, String str7, Boolean bool, Boolean bool2, String str8, String str9, Boolean bool3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: authValidatePhoneConfirm");
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
                str6 = null;
            }
            if ((i10 & 64) != 0) {
                str7 = null;
            }
            if ((i10 & 128) != 0) {
                bool = null;
            }
            if ((i10 & 256) != 0) {
                bool2 = null;
            }
            if ((i10 & 512) != 0) {
                str8 = null;
            }
            if ((i10 & 1024) != 0) {
                str9 = null;
            }
            if ((i10 & 2048) != 0) {
                bool3 = null;
            }
            return authService.authValidatePhoneConfirm(str, str2, str3, str4, str5, str6, str7, bool, bool2, str8, str9, bool3);
        }

        @NotNull
        public static ApiMethodCall<AuthValidatePhoneInfoResponseDto> authValidatePhoneInfo(@NotNull AuthService authService, @NotNull String sid, @NotNull String phone) {
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(phone, "phone");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validatePhoneInfo", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.z0
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocaw(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.a1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocaw(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, PasskeyBeginResult.SID_KEY, sid, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "phone", phone, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        @NotNull
        public static ApiMethodCall<AuthValidateSuperAppTokenResponseDto> authValidateSuperAppToken(@NotNull AuthService authService, int i10, @NotNull String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("auth.validateSuperAppToken", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.auth.e1
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return AuthService.DefaultImpls.detarenegipakvmocax(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.auth.f1
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return AuthService.DefaultImpls.detarenegipakvmocax(inputStream);
                }
            });
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "client_id", i10, 0, 0, 12, (Object) null);
            InternalApiMethodCall.addParam$default(internalApiMethodCall, "token", token, 0, 0, 12, (Object) null);
            return internalApiMethodCall;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmoca(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetWebAuthLinkResponseDto detarenegipakvmocaa(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetWebAuthLinkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetWebAuthLinkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthInitPasswordCheckResponseDto detarenegipakvmocab(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthInitPasswordCheckResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthInitPasswordCheckResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthInvalidateExchangeTokenMultiResponseDto detarenegipakvmocac(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthInvalidateExchangeTokenMultiResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthInvalidateExchangeTokenMultiResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocad(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthOnSuccessValidationResponseDto detarenegipakvmocae(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthOnSuccessValidationResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthOnSuccessValidationResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthProcessAuthCodeResponseDto detarenegipakvmocaf(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthProcessAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthProcessAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthProcessAuthHashResponseDto detarenegipakvmocag(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthProcessAuthHashResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthProcessAuthHashResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthRefreshTokensResponseDto detarenegipakvmocah(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthRefreshTokensResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthRefreshTokensResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthRefreshTrustedHashesResponseDto detarenegipakvmocai(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthRefreshTrustedHashesResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthRefreshTrustedHashesResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthSetAuthCodeStatusResponseDto detarenegipakvmocaj(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthSetAuthCodeStatusResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthSetAuthCodeStatusResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthSignupResponseDto detarenegipakvmocak(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthSignupResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthSignupResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthTerminateAuthCodeResponseDto detarenegipakvmocal(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthTerminateAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthTerminateAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidateAccountResponseDto detarenegipakvmocam(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidateAccountResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidateAccountResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidateAuthCodeResponseDto detarenegipakvmocan(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidateAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidateAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidateEmailResponseDto detarenegipakvmocao(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidateEmailResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidateEmailResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidatePhoneConfirmResponseDto detarenegipakvmocap(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidatePhoneConfirmResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidatePhoneConfirmResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidateLoginResponseDto detarenegipakvmocaq(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidateLoginResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidateLoginResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseBoolIntDto detarenegipakvmocar(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseBoolIntDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseBoolIntDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidatePhoneCancelResponseDto detarenegipakvmocas(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidatePhoneCancelResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidatePhoneCancelResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidatePhoneCheckResponseDto detarenegipakvmocat(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidatePhoneCheckResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidatePhoneCheckResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocau(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidatePhoneConfirmResponseDto detarenegipakvmocav(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidatePhoneConfirmResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidatePhoneConfirmResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidatePhoneInfoResponseDto detarenegipakvmocaw(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidatePhoneInfoResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidatePhoneInfoResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthValidateSuperAppTokenResponseDto detarenegipakvmocax(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthValidateSuperAppTokenResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthValidateSuperAppTokenResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCheckAccessResponseDto detarenegipakvmocb(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCheckAccessResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCheckAccessResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCheckAuthCodeResponseDto detarenegipakvmocc(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCheckAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCheckAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCheckAuthHashResponseDto detarenegipakvmocd(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCheckAuthHashResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCheckAuthHashResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCheckBindExtOAuthResponseDto detarenegipakvmoce(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCheckBindExtOAuthResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCheckBindExtOAuthResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCheckValidationStatusResponseDto detarenegipakvmocf(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCheckValidationStatusResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCheckValidationStatusResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthCreateAuthCodeResponseDto detarenegipakvmocg(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthCreateAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthCreateAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthExchangeSilentTokenToSidResponseDto detarenegipakvmoch(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthExchangeSilentTokenToSidResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthExchangeSilentTokenToSidResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthExternalFlowOutResponseDto detarenegipakvmoci(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthExternalFlowOutResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthExternalFlowOutResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List detarenegipakvmocj(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (List) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentTokenShortDto.class).getType()).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AppsGetScopesResponseDto detarenegipakvmock(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AppsGetScopesResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AppsGetScopesResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetAuthCodeResponseDto detarenegipakvmocl(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetAuthCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetAuthCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetAuthCodeStatusResponseDto detarenegipakvmocm(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetAuthCodeStatusResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetAuthCodeStatusResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetAuthDataResponseDto detarenegipakvmocn(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetAuthDataResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetAuthDataResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetAutologinCredentialsResponseDto detarenegipakvmoco(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetAutologinCredentialsResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetAutologinCredentialsResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetContinuationForServiceResponseDto detarenegipakvmocp(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetContinuationForServiceResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetContinuationForServiceResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetSilentTokensResponseDto detarenegipakvmocq(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetSilentTokensResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetSilentTokensResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List detarenegipakvmocr(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (List) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentTokenDto.class).getType()).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetCredentialsForServiceMultiResponseDto detarenegipakvmocs(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetCredentialsForServiceMultiResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetCredentialsForServiceMultiResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetExchangeTokenResponseDto detarenegipakvmoct(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetExchangeTokenResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetExchangeTokenResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetExchangeTokenInfoResponseDto detarenegipakvmocu(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetExchangeTokenInfoResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetExchangeTokenInfoResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List detarenegipakvmocv(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (List) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, TypeToken.getParameterized(List.class, AuthExchangeTokenInfoDto.class).getType()).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetQrAuthDataResponseDto detarenegipakvmocw(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetQrAuthDataResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetQrAuthDataResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List detarenegipakvmocx(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (List) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentProviderDto.class).getType()).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AppsGetSubAppInfoResponseDto detarenegipakvmocy(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AppsGetSubAppInfoResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AppsGetSubAppInfoResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AuthGetUserInfoByPhoneResponseDto detarenegipakvmocz(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (AuthGetUserInfoByPhoneResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, AuthGetUserInfoByPhoneResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoca(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocaa(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetWebAuthLinkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocab(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthInitPasswordCheckResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocac(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthInvalidateExchangeTokenMultiResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocad(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocae(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthOnSuccessValidationResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocaf(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthProcessAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocag(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthProcessAuthHashResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocah(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthRefreshTokensResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocai(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthRefreshTrustedHashesResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocaj(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthSetAuthCodeStatusResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocak(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthSignupResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocal(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthTerminateAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocam(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidateAccountResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocan(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidateAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocao(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidateEmailResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocap(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidatePhoneConfirmResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocaq(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidateLoginResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocar(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseBoolIntDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocas(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidatePhoneCancelResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocat(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidatePhoneCheckResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocau(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocav(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidatePhoneConfirmResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocaw(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidatePhoneInfoResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocax(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthValidateSuperAppTokenResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocb(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCheckAccessResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocc(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCheckAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocd(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCheckAuthHashResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoce(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCheckBindExtOAuthResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocf(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCheckValidationStatusResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocg(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthCreateAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoch(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthExchangeSilentTokenToSidResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoci(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthExternalFlowOutResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmock(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AppsGetScopesResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocl(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetAuthCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocm(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetAuthCodeStatusResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocn(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetAuthDataResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoco(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetAutologinCredentialsResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocp(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetContinuationForServiceResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocq(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetSilentTokensResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocs(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetCredentialsForServiceMultiResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoct(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetExchangeTokenResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocu(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetExchangeTokenInfoResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocw(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetQrAuthDataResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocy(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AppsGetSubAppInfoResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocz(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, AuthGetUserInfoByPhoneResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocj(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentTokenShortDto.class).getType()), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocr(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentTokenDto.class).getType()), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocv(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, TypeToken.getParameterized(List.class, AuthExchangeTokenInfoDto.class).getType()), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocx(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, TypeToken.getParameterized(List.class, AuthSilentProviderDto.class).getType()), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }
    }
}
