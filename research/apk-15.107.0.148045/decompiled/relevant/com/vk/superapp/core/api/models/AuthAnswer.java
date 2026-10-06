package com.vk.superapp.core.api.models;

import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.sdk.auth.UtilityTokens;
import com.vk.api.sdk.exceptions.ApiErrorViewType;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.auth.verification.base.BaseCheckFragment;
import com.vk.dto.common.id.UserId;
import com.vk.dto.common.id.UserIdKt;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.sessionmanagment.impl.data.source.SessionSQLiteHelper;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.http2.Http2Connection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.smoothie.domain.web.load.usecase.InjectNativeParamToAppConfigUseCase;
import ru.mail.ui.fragments.settings.ConfirmPhoneFragment;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0091\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0003\b\u008b\u0001\u0018\u0000 Ï\u00012\u00020\u0001:\u0006Ð\u0001Ñ\u0001Ï\u0001B¹\u0004\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002\u0012\b\b\u0002\u0010 \u001a\u00020\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\u0007\u0012\b\b\u0002\u0010#\u001a\u00020\"\u0012\b\b\u0002\u0010$\u001a\u00020\u0002\u0012\b\b\u0002\u0010%\u001a\u00020\u0002\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010(\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*\u0012\b\b\u0002\u0010,\u001a\u00020\"\u0012\b\b\u0002\u0010-\u001a\u00020\u0002\u0012\b\b\u0002\u0010.\u001a\u00020\t\u0012\b\b\u0002\u0010/\u001a\u00020\u0002\u0012\b\b\u0002\u00100\u001a\u00020\u0002\u0012\b\b\u0002\u00101\u001a\u00020\u0007\u0012\b\b\u0002\u00102\u001a\u00020\u0007\u0012\u0010\b\u0002\u00104\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010\u0012\u0012\u0010\b\u0002\u00105\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010\u0012\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u000106\u0012\b\b\u0002\u00108\u001a\u00020\t\u0012\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u0002\u0012\u001c\b\u0002\u0010<\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010:j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`;\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010=\u0012\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010B\u001a\u0004\u0018\u00010A\u0012\n\b\u0002\u0010D\u001a\u0004\u0018\u00010C¢\u0006\u0004\bE\u0010FB\u0011\b\u0016\u0012\u0006\u0010H\u001a\u00020G¢\u0006\u0004\bE\u0010IJ\r\u0010J\u001a\u00020\t¢\u0006\u0004\bJ\u0010KR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bP\u0010M\u001a\u0004\bQ\u0010OR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010UR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010KR\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b]\u0010M\u001a\u0004\b^\u0010OR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bc\u0010M\u001a\u0004\bd\u0010OR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\be\u0010M\u001a\u0004\bf\u0010OR\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bg\u0010M\u001a\u0004\bh\u0010OR\u0017\u0010\u0011\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\bi\u0010W\u001a\u0004\bj\u0010YR\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00128\u0006¢\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bm\u0010nR\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00128\u0006¢\u0006\f\n\u0004\bo\u0010l\u001a\u0004\bp\u0010nR\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bq\u0010M\u001a\u0004\br\u0010OR\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u0017\u0010\u0018\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\bw\u0010t\u001a\u0004\bx\u0010vR\u0017\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\by\u0010M\u001a\u0004\bz\u0010OR\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b{\u0010M\u001a\u0004\b|\u0010OR\u0017\u0010\u001b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b}\u0010M\u001a\u0004\b~\u0010OR\u0018\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\r\n\u0004\b\u007f\u0010M\u001a\u0005\b\u0080\u0001\u0010OR\u0019\u0010\u001d\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010M\u001a\u0005\b\u0082\u0001\u0010OR\u0019\u0010\u001e\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0083\u0001\u0010M\u001a\u0005\b\u0084\u0001\u0010OR\u0019\u0010\u001f\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010M\u001a\u0005\b\u0086\u0001\u0010OR\u0019\u0010 \u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0087\u0001\u0010M\u001a\u0005\b\u0088\u0001\u0010OR\u0019\u0010!\u001a\u00020\u00078\u0006¢\u0006\u000e\n\u0005\b\u0089\u0001\u0010W\u001a\u0005\b\u008a\u0001\u0010YR\u001b\u0010#\u001a\u00020\"8\u0006¢\u0006\u0010\n\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R\u0019\u0010$\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u008f\u0001\u0010M\u001a\u0005\b\u0090\u0001\u0010OR\u0019\u0010%\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b\u0091\u0001\u0010M\u001a\u0005\b\u0092\u0001\u0010OR\u001d\u0010'\u001a\u0004\u0018\u00010&8\u0006¢\u0006\u0010\n\u0006\b\u0093\u0001\u0010\u0094\u0001\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001R\u001d\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\u0010\n\u0006\b\u0097\u0001\u0010\u0098\u0001\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u001d\u0010+\u001a\u0004\u0018\u00010*8\u0006¢\u0006\u0010\n\u0006\b\u009b\u0001\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u001b\u0010,\u001a\u00020\"8\u0006¢\u0006\u0010\n\u0006\b\u009f\u0001\u0010\u008c\u0001\u001a\u0006\b \u0001\u0010\u008e\u0001R\u0019\u0010-\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b¡\u0001\u0010M\u001a\u0005\b¢\u0001\u0010OR\u0019\u0010.\u001a\u00020\t8\u0006¢\u0006\u000e\n\u0005\b£\u0001\u0010[\u001a\u0005\b¤\u0001\u0010KR\u0019\u0010/\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b¥\u0001\u0010M\u001a\u0005\b¦\u0001\u0010OR\u0019\u00100\u001a\u00020\u00028\u0006¢\u0006\u000e\n\u0005\b§\u0001\u0010M\u001a\u0005\b¨\u0001\u0010OR\u0019\u00101\u001a\u00020\u00078\u0006¢\u0006\u000e\n\u0005\b©\u0001\u0010W\u001a\u0005\bª\u0001\u0010YR\u0019\u00102\u001a\u00020\u00078\u0006¢\u0006\u000e\n\u0005\b«\u0001\u0010W\u001a\u0005\b¬\u0001\u0010YR!\u00104\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010\u00128\u0006¢\u0006\u000e\n\u0005\b\u00ad\u0001\u0010l\u001a\u0005\b®\u0001\u0010nR!\u00105\u001a\n\u0012\u0004\u0012\u000203\u0018\u00010\u00128\u0006¢\u0006\u000e\n\u0005\b¯\u0001\u0010l\u001a\u0005\b°\u0001\u0010nR\u001d\u00107\u001a\u0004\u0018\u0001068\u0006¢\u0006\u0010\n\u0006\b±\u0001\u0010²\u0001\u001a\u0006\b³\u0001\u0010´\u0001R\u0019\u00108\u001a\u00020\t8\u0006¢\u0006\u000e\n\u0005\bµ\u0001\u0010[\u001a\u0005\b¶\u0001\u0010KR\u001b\u00109\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\u000e\n\u0005\b·\u0001\u0010M\u001a\u0005\b¸\u0001\u0010OR<\u0010<\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010:j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`;8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b¹\u0001\u0010º\u0001\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0006\b½\u0001\u0010¾\u0001R\u001d\u0010>\u001a\u0004\u0018\u00010=8\u0006¢\u0006\u0010\n\u0006\b¿\u0001\u0010À\u0001\u001a\u0006\bÁ\u0001\u0010Â\u0001R\u001b\u0010?\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\u000e\n\u0005\bÃ\u0001\u0010M\u001a\u0005\bÄ\u0001\u0010OR\u001b\u0010@\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\u000e\n\u0005\bÅ\u0001\u0010M\u001a\u0005\bÆ\u0001\u0010OR\u001d\u0010B\u001a\u0004\u0018\u00010A8\u0006¢\u0006\u0010\n\u0006\bÇ\u0001\u0010È\u0001\u001a\u0006\bÉ\u0001\u0010Ê\u0001R\u001d\u0010D\u001a\u0004\u0018\u00010C8\u0006¢\u0006\u0010\n\u0006\bË\u0001\u0010Ì\u0001\u001a\u0006\bÍ\u0001\u0010Î\u0001¨\u0006Ò\u0001"}, d2 = {"Lcom/vk/superapp/core/api/models/AuthAnswer;", "", "", CommonConstant.KEY_ACCESS_TOKEN, AccountManagerRepositoryImpl.SECRET_ARG, "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "expiresIn", "", "httpsRequired", "trustedHash", "Lcom/vk/api/sdk/auth/UtilityTokens;", "utilityTokens", "emailToActualize", "silentToken", "silentTokenUuid", "silentTokenTimeout", "", "providedHashes", "providedUuids", "redirectUrl", "Lcom/vk/superapp/core/api/models/ValidationType;", "validationType", "validationResendType", BaseCheckFragment.KEY_VALIDATION_SID, "validationExternalId", BaseCheckFragment.KEY_PHONE_MASK, "emailMask", "errorType", "email", "phone", InjectNativeParamToAppConfigUseCase.DEVICE_NAME_KEY, "codeLength", "", "delay", "error", "errorDescription", "Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "errorInfo", "Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "optional", "Lcom/vk/superapp/core/api/models/BanInfo;", "banInfo", "restoreRequestId", "restoreHash", "useLoginInRestore", "webviewAccessToken", "webviewRefreshToken", "webviewExpired", "webviewRefreshTokenExpired", "Lcom/vk/superapp/core/api/models/SignUpField;", "signUpFields", "signUpSkippableFields", "Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "signUpIncompleteFieldsModel", "signUpAgreementRequired", PasskeyBeginResult.SID_KEY, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "cookies", "Lcom/vk/api/sdk/exceptions/ApiErrorViewType;", "viewType", "whiteLabelFlowOutputSat", "responseType", "Lcom/vk/superapp/core/api/models/ValidateInfo;", "validateInfo", "Lcom/vk/superapp/core/api/models/SendOtpInfo;", "sendOtpInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;IZLjava/lang/String;Lcom/vk/api/sdk/auth/UtilityTokens;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Lcom/vk/superapp/core/api/models/ValidationType;Lcom/vk/superapp/core/api/models/ValidationType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;Ljava/lang/String;Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;Lcom/vk/superapp/core/api/models/BanInfo;JLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;IILjava/util/List;Ljava/util/List;Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;ZLjava/lang/String;Ljava/util/ArrayList;Lcom/vk/api/sdk/exceptions/ApiErrorViewType;Ljava/lang/String;Ljava/lang/String;Lcom/vk/superapp/core/api/models/ValidateInfo;Lcom/vk/superapp/core/api/models/SendOtpInfo;)V", "Lorg/json/JSONObject;", "jo", "(Lorg/json/JSONObject;)V", "isSuccess", "()Z", "erockvmoca", "Ljava/lang/String;", "getAccessToken", "()Ljava/lang/String;", "erockvmocb", "getSecret", "erockvmocc", "Lcom/vk/dto/common/id/UserId;", "getUserId", "()Lcom/vk/dto/common/id/UserId;", "erockvmocd", "I", "getExpiresIn", "()I", "erockvmoce", "Z", "getHttpsRequired", "erockvmocf", "getTrustedHash", "erockvmocg", "Lcom/vk/api/sdk/auth/UtilityTokens;", "getUtilityTokens", "()Lcom/vk/api/sdk/auth/UtilityTokens;", "erockvmoch", "getEmailToActualize", "erockvmoci", "getSilentToken", "erockvmocj", "getSilentTokenUuid", "erockvmock", "getSilentTokenTimeout", "erockvmocl", "Ljava/util/List;", "getProvidedHashes", "()Ljava/util/List;", "erockvmocm", "getProvidedUuids", "erockvmocn", "getRedirectUrl", "erockvmoco", "Lcom/vk/superapp/core/api/models/ValidationType;", "getValidationType", "()Lcom/vk/superapp/core/api/models/ValidationType;", "erockvmocp", "getValidationResendType", "erockvmocq", "getValidationSid", "erockvmocr", "getValidationExternalId", "erockvmocs", "getPhoneMask", "erockvmoct", "getEmailMask", "erockvmocu", "getErrorType", "erockvmocv", "getEmail", "erockvmocw", "getPhone", "erockvmocx", "getDeviceName", "erockvmocy", "getCodeLength", "erockvmocz", "J", "getDelay", "()J", "erockvmocaa", "getError", "erockvmocab", "getErrorDescription", "erockvmocac", "Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "getErrorInfo", "()Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "erockvmocad", "Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "getOptional", "()Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "erockvmocae", "Lcom/vk/superapp/core/api/models/BanInfo;", "getBanInfo", "()Lcom/vk/superapp/core/api/models/BanInfo;", "erockvmocaf", "getRestoreRequestId", "erockvmocag", "getRestoreHash", "erockvmocah", "getUseLoginInRestore", "erockvmocai", "getWebviewAccessToken", "erockvmocaj", "getWebviewRefreshToken", "erockvmocak", "getWebviewExpired", "erockvmocal", "getWebviewRefreshTokenExpired", "erockvmocam", "getSignUpFields", "erockvmocan", "getSignUpSkippableFields", "erockvmocao", "Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "getSignUpIncompleteFieldsModel", "()Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "erockvmocap", "getSignUpAgreementRequired", "erockvmocaq", "getSid", "erockvmocar", "Ljava/util/ArrayList;", "getCookies", "()Ljava/util/ArrayList;", "setCookies", "(Ljava/util/ArrayList;)V", "erockvmocas", "Lcom/vk/api/sdk/exceptions/ApiErrorViewType;", "getViewType", "()Lcom/vk/api/sdk/exceptions/ApiErrorViewType;", "erockvmocat", "getWhiteLabelFlowOutputSat", "erockvmocau", "getResponseType", "erockvmocav", "Lcom/vk/superapp/core/api/models/ValidateInfo;", "getValidateInfo", "()Lcom/vk/superapp/core/api/models/ValidateInfo;", "erockvmocaw", "Lcom/vk/superapp/core/api/models/SendOtpInfo;", "getSendOtpInfo", "()Lcom/vk/superapp/core/api/models/SendOtpInfo;", "Companion", "ErrorInfo", "Optional", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthAnswer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthAnswer.kt\ncom/vk/superapp/core/api/models/AuthAnswer\n+ 2 JsonExt.kt\ncom/vk/superapp/core/extensions/JsonExtKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,264:1\n48#2,11:265\n48#2,11:276\n1#3:287\n*S KotlinDebug\n*F\n+ 1 AuthAnswer.kt\ncom/vk/superapp/core/api/models/AuthAnswer\n*L\n110#1:265,11\n111#1:276,11\n*E\n"})
public final class AuthAnswer {

    @NotNull
    public static final String ERROR_DEACTIVATED = "deactivated";

    @NotNull
    public static final String ERROR_EXPIRED_ANONYMOUS_TOKEN = "anonymous_token_has_expired";

    @NotNull
    public static final String ERROR_INVALID_ANONYMOUS_TOKEN = "invalid_anonymous_token";

    @NotNull
    public static final String ERROR_INVALID_CLIENT = "invalid_client";

    @NotNull
    public static final String ERROR_INVALID_PASSWORD = "invalid_password";

    @NotNull
    public static final String ERROR_INVALID_REQUEST = "invalid_request";

    @NotNull
    public static final String ERROR_NEED_AUTH_CHECK = "need_authcheck";

    @NotNull
    public static final String ERROR_NEED_VALIDATE = "need_validation";

    @NotNull
    public static final String ERROR_NO_EXTENSION_NEEDED = "is_ok";

    @NotNull
    public static final String ERROR_OAUTH_SPECIFIC = "oauth_specific_error";

    @NotNull
    public static final String ERROR_SERVICE = "user_service_state";

    @NotNull
    public static final String ERROR_SID_IS_INVALID = "8210;Invalid sid";

    @NotNull
    public static final String ERROR_SIGN_UP_AGREEMENT_REQUIRED = "sign_in_agreement_required";

    @NotNull
    public static final String ERROR_TYPE_AGE_IS_TOO_YOUNG = "age_is_too_young";

    @NotNull
    public static final String ERROR_TYPE_CANCEL_BY_OWNER_NEEDED = "cancel_by_owner_needed";

    @NotNull
    public static final String ERROR_TYPE_INSTALL_CONFIRMATION_REQUIRED = "install_confirmation_required";

    @NotNull
    public static final String ERROR_TYPE_PARTIAL_TOKEN = "partial_token";

    @NotNull
    public static final String ERROR_TYPE_PHONE_VALIDATION_REQUIRED = "phone_validation_required";

    @NotNull
    public static final String ERROR_TYPE_PROFILE_EXTENSION_REQUIRED = "profile_extension_required";

    @NotNull
    public static final String ERROR_TYPE_SIGN_UP_REQUIRED = "need_signup";

    @NotNull
    public static final String ERROR_TYPE_TOO_MANY_ATTEMPTS = "too_much_tries";

    @NotNull
    public static final String ERROR_TYPE_TOO_MANY_REQUESTS = "too_many_requests";

    @NotNull
    public static final String ERROR_TYPE_USER_BANNED = "user_banned";

    @NotNull
    public static final String ERROR_TYPE_USER_DEACTIVATED = "user_deactivated";

    @NotNull
    public static final String ERROR_TYPE_VK_EMAIL_SIGN_UP_REQUIRED = "mail_signup_required";

    @NotNull
    public static final String ERROR_TYPE_WRONG_CODE = "wrong_otp";

    @NotNull
    public static final String ERROR_TYPE_WRONG_CODE_FORMAT = "otp_format_is_incorrect";

    @NotNull
    public static final String RESPONSE_TYPE_NEED_VALIDATE = "need_validate";

    /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
    @NotNull
    private final String accessToken;

    /* JADX INFO: renamed from: erockvmocaa, reason: from kotlin metadata */
    @NotNull
    private final String error;

    /* JADX INFO: renamed from: erockvmocab, reason: from kotlin metadata */
    @NotNull
    private final String errorDescription;

    /* JADX INFO: renamed from: erockvmocac, reason: from kotlin metadata */
    @Nullable
    private final ErrorInfo errorInfo;

    /* JADX INFO: renamed from: erockvmocad, reason: from kotlin metadata */
    @Nullable
    private final Optional optional;

    /* JADX INFO: renamed from: erockvmocae, reason: from kotlin metadata */
    @Nullable
    private final BanInfo banInfo;

    /* JADX INFO: renamed from: erockvmocaf, reason: from kotlin metadata */
    private final long restoreRequestId;

    /* JADX INFO: renamed from: erockvmocag, reason: from kotlin metadata */
    @NotNull
    private final String restoreHash;

    /* JADX INFO: renamed from: erockvmocah, reason: from kotlin metadata */
    private final boolean useLoginInRestore;

    /* JADX INFO: renamed from: erockvmocai, reason: from kotlin metadata */
    @NotNull
    private final String webviewAccessToken;

    /* JADX INFO: renamed from: erockvmocaj, reason: from kotlin metadata */
    @NotNull
    private final String webviewRefreshToken;

    /* JADX INFO: renamed from: erockvmocak, reason: from kotlin metadata */
    private final int webviewExpired;

    /* JADX INFO: renamed from: erockvmocal, reason: from kotlin metadata */
    private final int webviewRefreshTokenExpired;

    /* JADX INFO: renamed from: erockvmocam, reason: from kotlin metadata */
    @Nullable
    private final List<SignUpField> signUpFields;

    /* JADX INFO: renamed from: erockvmocan, reason: from kotlin metadata */
    @Nullable
    private final List<SignUpField> signUpSkippableFields;

    /* JADX INFO: renamed from: erockvmocao, reason: from kotlin metadata */
    @Nullable
    private final SignUpIncompleteFieldsModel signUpIncompleteFieldsModel;

    /* JADX INFO: renamed from: erockvmocap, reason: from kotlin metadata */
    private final boolean signUpAgreementRequired;

    /* JADX INFO: renamed from: erockvmocaq, reason: from kotlin metadata */
    @Nullable
    private final String sid;

    /* JADX INFO: renamed from: erockvmocar, reason: from kotlin metadata */
    @Nullable
    private ArrayList<String> cookies;

    /* JADX INFO: renamed from: erockvmocas, reason: from kotlin metadata */
    @Nullable
    private final ApiErrorViewType viewType;

    /* JADX INFO: renamed from: erockvmocat, reason: from kotlin metadata */
    @Nullable
    private final String whiteLabelFlowOutputSat;

    /* JADX INFO: renamed from: erockvmocau, reason: from kotlin metadata */
    @Nullable
    private final String responseType;

    /* JADX INFO: renamed from: erockvmocav, reason: from kotlin metadata */
    @Nullable
    private final ValidateInfo validateInfo;

    /* JADX INFO: renamed from: erockvmocaw, reason: from kotlin metadata */
    @Nullable
    private final SendOtpInfo sendOtpInfo;

    /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
    @NotNull
    private final String secret;

    /* JADX INFO: renamed from: erockvmocc, reason: from kotlin metadata */
    @NotNull
    private final UserId userId;

    /* JADX INFO: renamed from: erockvmocd, reason: from kotlin metadata */
    private final int expiresIn;

    /* JADX INFO: renamed from: erockvmoce, reason: from kotlin metadata */
    private final boolean httpsRequired;

    /* JADX INFO: renamed from: erockvmocf, reason: from kotlin metadata */
    @NotNull
    private final String trustedHash;

    /* JADX INFO: renamed from: erockvmocg, reason: from kotlin metadata */
    @NotNull
    private final UtilityTokens utilityTokens;

    /* JADX INFO: renamed from: erockvmoch, reason: from kotlin metadata */
    @Nullable
    private final String emailToActualize;

    /* JADX INFO: renamed from: erockvmoci, reason: from kotlin metadata */
    @NotNull
    private final String silentToken;

    /* JADX INFO: renamed from: erockvmocj, reason: from kotlin metadata */
    @NotNull
    private final String silentTokenUuid;

    /* JADX INFO: renamed from: erockvmock, reason: from kotlin metadata */
    private final int silentTokenTimeout;

    /* JADX INFO: renamed from: erockvmocl, reason: from kotlin metadata */
    @NotNull
    private final List<String> providedHashes;

    /* JADX INFO: renamed from: erockvmocm, reason: from kotlin metadata */
    @NotNull
    private final List<String> providedUuids;

    /* JADX INFO: renamed from: erockvmocn, reason: from kotlin metadata */
    @NotNull
    private final String redirectUrl;

    /* JADX INFO: renamed from: erockvmoco, reason: from kotlin metadata */
    @NotNull
    private final ValidationType validationType;

    /* JADX INFO: renamed from: erockvmocp, reason: from kotlin metadata */
    @NotNull
    private final ValidationType validationResendType;

    /* JADX INFO: renamed from: erockvmocq, reason: from kotlin metadata */
    @NotNull
    private final String validationSid;

    /* JADX INFO: renamed from: erockvmocr, reason: from kotlin metadata */
    @Nullable
    private final String validationExternalId;

    /* JADX INFO: renamed from: erockvmocs, reason: from kotlin metadata */
    @NotNull
    private final String phoneMask;

    /* JADX INFO: renamed from: erockvmoct, reason: from kotlin metadata */
    @NotNull
    private final String emailMask;

    /* JADX INFO: renamed from: erockvmocu, reason: from kotlin metadata */
    @NotNull
    private final String errorType;

    /* JADX INFO: renamed from: erockvmocv, reason: from kotlin metadata */
    @NotNull
    private final String email;

    /* JADX INFO: renamed from: erockvmocw, reason: from kotlin metadata */
    @NotNull
    private final String phone;

    /* JADX INFO: renamed from: erockvmocx, reason: from kotlin metadata */
    @NotNull
    private final String deviceName;

    /* JADX INFO: renamed from: erockvmocy, reason: from kotlin metadata */
    private final int codeLength;

    /* JADX INFO: renamed from: erockvmocz, reason: from kotlin metadata */
    private final long delay;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\nJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u0010\u0010\u0011\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0011\u0010\fJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\n¨\u0006\u001f"}, d2 = {"Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "", "", "silentToken", "", "silentTokenTtl", "silentTokenUuid", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "()I", "component3", "copy", "(Ljava/lang/String;ILjava/lang/String;)Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "erockvmoca", "Ljava/lang/String;", "getSilentToken", "erockvmocb", "I", "getSilentTokenTtl", "erockvmocc", "getSilentTokenUuid", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Optional {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
        @NotNull
        private final String silentToken;

        /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
        private final int silentTokenTtl;

        /* JADX INFO: renamed from: erockvmocc, reason: from kotlin metadata */
        @NotNull
        private final String silentTokenUuid;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/core/api/models/AuthAnswer$Optional$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/core/api/models/AuthAnswer$Optional;", "jo", "Lorg/json/JSONObject;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Optional parse(@NotNull JSONObject jo) {
                Intrinsics.checkNotNullParameter(jo, "jo");
                String strOptString = jo.optString("silent_token");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                int iOptInt = jo.optInt("silent_token_ttl");
                String strOptString2 = jo.optString("silent_token_uuid");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                return new Optional(strOptString, iOptInt, strOptString2);
            }

            private Companion() {
            }
        }

        public Optional(@NotNull String silentToken, int i10, @NotNull String silentTokenUuid) {
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
            this.silentToken = silentToken;
            this.silentTokenTtl = i10;
            this.silentTokenUuid = silentTokenUuid;
        }

        public static /* synthetic */ Optional copy$default(Optional optional, String str, int i10, String str2, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = optional.silentToken;
            }
            if ((i11 & 2) != 0) {
                i10 = optional.silentTokenTtl;
            }
            if ((i11 & 4) != 0) {
                str2 = optional.silentTokenUuid;
            }
            return optional.copy(str, i10, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSilentToken() {
            return this.silentToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSilentTokenTtl() {
            return this.silentTokenTtl;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        @NotNull
        public final Optional copy(@NotNull String silentToken, int silentTokenTtl, @NotNull String silentTokenUuid) {
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
            return new Optional(silentToken, silentTokenTtl, silentTokenUuid);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Optional)) {
                return false;
            }
            Optional optional = (Optional) other;
            return Intrinsics.areEqual(this.silentToken, optional.silentToken) && this.silentTokenTtl == optional.silentTokenTtl && Intrinsics.areEqual(this.silentTokenUuid, optional.silentTokenUuid);
        }

        @NotNull
        public final String getSilentToken() {
            return this.silentToken;
        }

        public final int getSilentTokenTtl() {
            return this.silentTokenTtl;
        }

        @NotNull
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        public int hashCode() {
            return this.silentTokenUuid.hashCode() + ((Integer.hashCode(this.silentTokenTtl) + (this.silentToken.hashCode() * 31)) * 31);
        }

        @NotNull
        public String toString() {
            return "Optional(silentToken=" + this.silentToken + ", silentTokenTtl=" + this.silentTokenTtl + ", silentTokenUuid=" + this.silentTokenUuid + ')';
        }
    }

    public AuthAnswer() {
        this(null, null, null, 0, false, null, null, null, null, null, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0L, null, null, null, null, null, 0L, null, false, null, null, 0, 0, null, null, null, false, null, null, null, null, null, null, null, -1, 131071, null);
    }

    @NotNull
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final BanInfo getBanInfo() {
        return this.banInfo;
    }

    public final int getCodeLength() {
        return this.codeLength;
    }

    @Nullable
    public final ArrayList<String> getCookies() {
        return this.cookies;
    }

    public final long getDelay() {
        return this.delay;
    }

    @NotNull
    public final String getDeviceName() {
        return this.deviceName;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getEmailMask() {
        return this.emailMask;
    }

    @Nullable
    public final String getEmailToActualize() {
        return this.emailToActualize;
    }

    @NotNull
    public final String getError() {
        return this.error;
    }

    @NotNull
    public final String getErrorDescription() {
        return this.errorDescription;
    }

    @Nullable
    public final ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    @NotNull
    public final String getErrorType() {
        return this.errorType;
    }

    public final int getExpiresIn() {
        return this.expiresIn;
    }

    public final boolean getHttpsRequired() {
        return this.httpsRequired;
    }

    @Nullable
    public final Optional getOptional() {
        return this.optional;
    }

    @NotNull
    public final String getPhone() {
        return this.phone;
    }

    @NotNull
    public final String getPhoneMask() {
        return this.phoneMask;
    }

    @NotNull
    public final List<String> getProvidedHashes() {
        return this.providedHashes;
    }

    @NotNull
    public final List<String> getProvidedUuids() {
        return this.providedUuids;
    }

    @NotNull
    public final String getRedirectUrl() {
        return this.redirectUrl;
    }

    @Nullable
    public final String getResponseType() {
        return this.responseType;
    }

    @NotNull
    public final String getRestoreHash() {
        return this.restoreHash;
    }

    public final long getRestoreRequestId() {
        return this.restoreRequestId;
    }

    @NotNull
    public final String getSecret() {
        return this.secret;
    }

    @Nullable
    public final SendOtpInfo getSendOtpInfo() {
        return this.sendOtpInfo;
    }

    @Nullable
    public final String getSid() {
        return this.sid;
    }

    public final boolean getSignUpAgreementRequired() {
        return this.signUpAgreementRequired;
    }

    @Nullable
    public final List<SignUpField> getSignUpFields() {
        return this.signUpFields;
    }

    @Nullable
    public final SignUpIncompleteFieldsModel getSignUpIncompleteFieldsModel() {
        return this.signUpIncompleteFieldsModel;
    }

    @Nullable
    public final List<SignUpField> getSignUpSkippableFields() {
        return this.signUpSkippableFields;
    }

    @NotNull
    public final String getSilentToken() {
        return this.silentToken;
    }

    public final int getSilentTokenTimeout() {
        return this.silentTokenTimeout;
    }

    @NotNull
    public final String getSilentTokenUuid() {
        return this.silentTokenUuid;
    }

    @NotNull
    public final String getTrustedHash() {
        return this.trustedHash;
    }

    public final boolean getUseLoginInRestore() {
        return this.useLoginInRestore;
    }

    @NotNull
    public final UserId getUserId() {
        return this.userId;
    }

    @NotNull
    public final UtilityTokens getUtilityTokens() {
        return this.utilityTokens;
    }

    @Nullable
    public final ValidateInfo getValidateInfo() {
        return this.validateInfo;
    }

    @Nullable
    public final String getValidationExternalId() {
        return this.validationExternalId;
    }

    @NotNull
    public final ValidationType getValidationResendType() {
        return this.validationResendType;
    }

    @NotNull
    public final String getValidationSid() {
        return this.validationSid;
    }

    @NotNull
    public final ValidationType getValidationType() {
        return this.validationType;
    }

    @Nullable
    public final ApiErrorViewType getViewType() {
        return this.viewType;
    }

    @NotNull
    public final String getWebviewAccessToken() {
        return this.webviewAccessToken;
    }

    public final int getWebviewExpired() {
        return this.webviewExpired;
    }

    @NotNull
    public final String getWebviewRefreshToken() {
        return this.webviewRefreshToken;
    }

    public final int getWebviewRefreshTokenExpired() {
        return this.webviewRefreshTokenExpired;
    }

    @Nullable
    public final String getWhiteLabelFlowOutputSat() {
        return this.whiteLabelFlowOutputSat;
    }

    public final boolean isSuccess() {
        return UserIdKt.isReal(this.userId) && !StringsKt.isBlank(this.accessToken);
    }

    public final void setCookies(@Nullable ArrayList<String> arrayList) {
        this.cookies = arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AuthAnswer(@NotNull String accessToken, @NotNull String secret, @NotNull UserId userId, int i10, boolean z10, @NotNull String trustedHash, @NotNull UtilityTokens utilityTokens, @Nullable String str, @NotNull String silentToken, @NotNull String silentTokenUuid, int i11, @NotNull List<String> providedHashes, @NotNull List<String> providedUuids, @NotNull String redirectUrl, @NotNull ValidationType validationType, @NotNull ValidationType validationResendType, @NotNull String validationSid, @Nullable String str2, @NotNull String phoneMask, @NotNull String emailMask, @NotNull String errorType, @NotNull String email, @NotNull String phone, @NotNull String deviceName, int i12, long j10, @NotNull String error, @NotNull String errorDescription, @Nullable ErrorInfo errorInfo, @Nullable Optional optional, @Nullable BanInfo banInfo, long j11, @NotNull String restoreHash, boolean z11, @NotNull String webviewAccessToken, @NotNull String webviewRefreshToken, int i13, int i14, @Nullable List<? extends SignUpField> list, @Nullable List<? extends SignUpField> list2, @Nullable SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean z12, @Nullable String str3, @Nullable ArrayList<String> arrayList, @Nullable ApiErrorViewType apiErrorViewType, @Nullable String str4, @Nullable String str5, @Nullable ValidateInfo validateInfo, @Nullable SendOtpInfo sendOtpInfo) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(secret, "secret");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(trustedHash, "trustedHash");
        Intrinsics.checkNotNullParameter(utilityTokens, "utilityTokens");
        Intrinsics.checkNotNullParameter(silentToken, "silentToken");
        Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
        Intrinsics.checkNotNullParameter(providedHashes, "providedHashes");
        Intrinsics.checkNotNullParameter(providedUuids, "providedUuids");
        Intrinsics.checkNotNullParameter(redirectUrl, "redirectUrl");
        Intrinsics.checkNotNullParameter(validationType, "validationType");
        Intrinsics.checkNotNullParameter(validationResendType, "validationResendType");
        Intrinsics.checkNotNullParameter(validationSid, "validationSid");
        Intrinsics.checkNotNullParameter(phoneMask, "phoneMask");
        Intrinsics.checkNotNullParameter(emailMask, "emailMask");
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
        Intrinsics.checkNotNullParameter(restoreHash, "restoreHash");
        Intrinsics.checkNotNullParameter(webviewAccessToken, "webviewAccessToken");
        Intrinsics.checkNotNullParameter(webviewRefreshToken, "webviewRefreshToken");
        this.accessToken = accessToken;
        this.secret = secret;
        this.userId = userId;
        this.expiresIn = i10;
        this.httpsRequired = z10;
        this.trustedHash = trustedHash;
        this.utilityTokens = utilityTokens;
        this.emailToActualize = str;
        this.silentToken = silentToken;
        this.silentTokenUuid = silentTokenUuid;
        this.silentTokenTimeout = i11;
        this.providedHashes = providedHashes;
        this.providedUuids = providedUuids;
        this.redirectUrl = redirectUrl;
        this.validationType = validationType;
        this.validationResendType = validationResendType;
        this.validationSid = validationSid;
        this.validationExternalId = str2;
        this.phoneMask = phoneMask;
        this.emailMask = emailMask;
        this.errorType = errorType;
        this.email = email;
        this.phone = phone;
        this.deviceName = deviceName;
        this.codeLength = i12;
        this.delay = j10;
        this.error = error;
        this.errorDescription = errorDescription;
        this.errorInfo = errorInfo;
        this.optional = optional;
        this.banInfo = banInfo;
        this.restoreRequestId = j11;
        this.restoreHash = restoreHash;
        this.useLoginInRestore = z11;
        this.webviewAccessToken = webviewAccessToken;
        this.webviewRefreshToken = webviewRefreshToken;
        this.webviewExpired = i13;
        this.webviewRefreshTokenExpired = i14;
        this.signUpFields = list;
        this.signUpSkippableFields = list2;
        this.signUpIncompleteFieldsModel = signUpIncompleteFieldsModel;
        this.signUpAgreementRequired = z12;
        this.sid = str3;
        this.cookies = arrayList;
        this.viewType = apiErrorViewType;
        this.whiteLabelFlowOutputSat = str4;
        this.responseType = str5;
        this.validateInfo = validateInfo;
        this.sendOtpInfo = sendOtpInfo;
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\bb\b\u0086\b\u0018\u0000 \u0080\u00012\u00020\u0001:\u0002\u0080\u0001Bç\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\n\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u0006\u0012\u0006\u0010\u001e\u001a\u00020\u0006\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b%\u0010$J\u0012\u0010&\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b&\u0010$J\u0012\u0010'\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b)\u0010*J\u0018\u0010+\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b+\u0010,J\u0018\u0010-\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003¢\u0006\u0004\b-\u0010,J\u0012\u0010.\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b2\u0010$J\u0010\u00103\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b3\u0010$J\u0010\u00104\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b4\u0010$J\u0010\u00105\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b5\u0010*J\u0010\u00106\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b6\u0010$J\u0010\u00107\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b7\u0010$J\u0010\u00108\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b8\u0010$J\u0010\u00109\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b9\u0010$J\u0010\u0010:\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b:\u0010$J\u0016\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00020\nHÆ\u0003¢\u0006\u0004\b;\u0010,J\u0010\u0010<\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b<\u0010$J\u0010\u0010=\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b=\u0010$J\u0010\u0010>\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b>\u00101J\u0010\u0010?\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b?\u00101J\u0010\u0010@\u001a\u00020\u001fHÆ\u0003¢\u0006\u0004\b@\u0010AJ\u009e\u0002\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u00062\b\b\u0002\u0010 \u001a\u00020\u001fHÆ\u0001¢\u0006\u0004\bB\u0010CJ\u0010\u0010D\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\bD\u0010$J\u0010\u0010E\u001a\u00020\bHÖ\u0001¢\u0006\u0004\bE\u0010*J\u001a\u0010G\u001a\u00020\u00062\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bG\u0010HR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010$R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bL\u0010J\u001a\u0004\bM\u0010$R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\bN\u0010J\u001a\u0004\bO\u0010$R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010(R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010*R\u001f\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010,R\u001f\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\bY\u0010W\u001a\u0004\bZ\u0010,R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010/R\u0017\u0010\u0010\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b^\u0010_\u001a\u0004\b`\u00101R\u0017\u0010\u0011\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\ba\u0010J\u001a\u0004\bb\u0010$R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bc\u0010J\u001a\u0004\bd\u0010$R\u0017\u0010\u0013\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\be\u0010J\u001a\u0004\bf\u0010$R\u0017\u0010\u0014\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bg\u0010T\u001a\u0004\bh\u0010*R\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bi\u0010J\u001a\u0004\bj\u0010$R\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bk\u0010J\u001a\u0004\bl\u0010$R\u0017\u0010\u0017\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bm\u0010J\u001a\u0004\bn\u0010$R\u0017\u0010\u0018\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bo\u0010J\u001a\u0004\bp\u0010$R\u0017\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bq\u0010J\u001a\u0004\br\u0010$R\u001d\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\n8\u0006¢\u0006\f\n\u0004\bs\u0010W\u001a\u0004\bt\u0010,R\u0017\u0010\u001b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bu\u0010J\u001a\u0004\bv\u0010$R\u0017\u0010\u001c\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bw\u0010J\u001a\u0004\bx\u0010$R\u0017\u0010\u001d\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\by\u0010_\u001a\u0004\bz\u00101R\u0017\u0010\u001e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b{\u0010_\u001a\u0004\b|\u00101R\u0017\u0010 \u001a\u00020\u001f8\u0006¢\u0006\f\n\u0004\b}\u0010~\u001a\u0004\b\u007f\u0010A¨\u0006\u0081\u0001"}, d2 = {"Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "", "", CommonConstant.KEY_ACCESS_TOKEN, PasskeyBeginResult.SID_KEY, "phone", "", "instant", "", "status", "", "Lcom/vk/superapp/core/api/models/SignUpField;", "signUpFields", "signUpSkippableFields", "Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "signUpIncompleteFieldsModel", "signUpAgreementRequired", "memberName", "silentToken", "silentTokenUuid", "silentTokenTtl", "firstName", "lastName", "photo50", "photo100", "photo200", "domains", "domain", "username", "showAds", "adsIsOn", "Lcom/vk/api/sdk/auth/UtilityTokens;", "utilityTokens", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;ILjava/util/List;Ljava/util/List;Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLcom/vk/api/sdk/auth/UtilityTokens;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()Ljava/lang/Boolean;", "component5", "()I", "component6", "()Ljava/util/List;", "component7", "component8", "()Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "component9", "()Z", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "()Lcom/vk/api/sdk/auth/UtilityTokens;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;ILjava/util/List;Ljava/util/List;Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLcom/vk/api/sdk/auth/UtilityTokens;)Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "erockvmoca", "Ljava/lang/String;", "getAccessToken", "erockvmocb", "getSid", "erockvmocc", "getPhone", "erockvmocd", "Ljava/lang/Boolean;", "getInstant", "erockvmoce", "I", "getStatus", "erockvmocf", "Ljava/util/List;", "getSignUpFields", "erockvmocg", "getSignUpSkippableFields", "erockvmoch", "Lcom/vk/superapp/core/api/models/SignUpIncompleteFieldsModel;", "getSignUpIncompleteFieldsModel", "erockvmoci", "Z", "getSignUpAgreementRequired", "erockvmocj", "getMemberName", "erockvmock", "getSilentToken", "erockvmocl", "getSilentTokenUuid", "erockvmocm", "getSilentTokenTtl", "erockvmocn", "getFirstName", "erockvmoco", "getLastName", "erockvmocp", "getPhoto50", "erockvmocq", "getPhoto100", "erockvmocr", "getPhoto200", "erockvmocs", "getDomains", "erockvmoct", "getDomain", "erockvmocu", "getUsername", "erockvmocv", "getShowAds", "erockvmocw", "getAdsIsOn", "erockvmocx", "Lcom/vk/api/sdk/auth/UtilityTokens;", "getUtilityTokens", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class ErrorInfo {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: erockvmoca, reason: from kotlin metadata */
        @NotNull
        private final String accessToken;

        /* JADX INFO: renamed from: erockvmocb, reason: from kotlin metadata */
        @Nullable
        private final String sid;

        /* JADX INFO: renamed from: erockvmocc, reason: from kotlin metadata */
        @Nullable
        private final String phone;

        /* JADX INFO: renamed from: erockvmocd, reason: from kotlin metadata */
        @Nullable
        private final Boolean instant;

        /* JADX INFO: renamed from: erockvmoce, reason: from kotlin metadata */
        private final int status;

        /* JADX INFO: renamed from: erockvmocf, reason: from kotlin metadata */
        @Nullable
        private final List<SignUpField> signUpFields;

        /* JADX INFO: renamed from: erockvmocg, reason: from kotlin metadata */
        @Nullable
        private final List<SignUpField> signUpSkippableFields;

        /* JADX INFO: renamed from: erockvmoch, reason: from kotlin metadata */
        @Nullable
        private final SignUpIncompleteFieldsModel signUpIncompleteFieldsModel;

        /* JADX INFO: renamed from: erockvmoci, reason: from kotlin metadata */
        private final boolean signUpAgreementRequired;

        /* JADX INFO: renamed from: erockvmocj, reason: from kotlin metadata */
        @NotNull
        private final String memberName;

        /* JADX INFO: renamed from: erockvmock, reason: from kotlin metadata */
        @NotNull
        private final String silentToken;

        /* JADX INFO: renamed from: erockvmocl, reason: from kotlin metadata */
        @NotNull
        private final String silentTokenUuid;

        /* JADX INFO: renamed from: erockvmocm, reason: from kotlin metadata */
        private final int silentTokenTtl;

        /* JADX INFO: renamed from: erockvmocn, reason: from kotlin metadata */
        @NotNull
        private final String firstName;

        /* JADX INFO: renamed from: erockvmoco, reason: from kotlin metadata */
        @NotNull
        private final String lastName;

        /* JADX INFO: renamed from: erockvmocp, reason: from kotlin metadata */
        @NotNull
        private final String photo50;

        /* JADX INFO: renamed from: erockvmocq, reason: from kotlin metadata */
        @NotNull
        private final String photo100;

        /* JADX INFO: renamed from: erockvmocr, reason: from kotlin metadata */
        @NotNull
        private final String photo200;

        /* JADX INFO: renamed from: erockvmocs, reason: from kotlin metadata */
        @NotNull
        private final List<String> domains;

        /* JADX INFO: renamed from: erockvmoct, reason: from kotlin metadata */
        @NotNull
        private final String domain;

        /* JADX INFO: renamed from: erockvmocu, reason: from kotlin metadata */
        @NotNull
        private final String username;

        /* JADX INFO: renamed from: erockvmocv, reason: from kotlin metadata */
        private final boolean showAds;

        /* JADX INFO: renamed from: erockvmocw, reason: from kotlin metadata */
        private final boolean adsIsOn;

        /* JADX INFO: renamed from: erockvmocx, reason: from kotlin metadata */
        @NotNull
        private final UtilityTokens utilityTokens;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo;", "jo", "Lorg/json/JSONObject;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nAuthAnswer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthAnswer.kt\ncom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo$Companion\n+ 2 JsonExt.kt\ncom/vk/superapp/core/extensions/JsonExtKt\n*L\n1#1,264:1\n48#2,11:265\n*S KotlinDebug\n*F\n+ 1 AuthAnswer.kt\ncom/vk/superapp/core/api/models/AuthAnswer$ErrorInfo$Companion\n*L\n205#1:265,11\n*E\n"})
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final ErrorInfo parse(@NotNull JSONObject jo) throws JSONException {
                List listEmptyList;
                Intrinsics.checkNotNullParameter(jo, "jo");
                String strOptString = jo.optString("access_token");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                String strOptString2 = jo.optString(PasskeyBeginResult.SID_KEY);
                String strOptString3 = jo.optString("phone");
                boolean zOptBoolean = jo.optBoolean("instant");
                int iOptInt = jo.optInt("status");
                SignUpField.Companion companion = SignUpField.INSTANCE;
                List<SignUpField> list = companion.parseList(jo.optJSONArray("extend_fields"));
                JSONObject jSONObjectOptJSONObject = jo.optJSONObject("extend_fields_values");
                SignUpIncompleteFieldsModel signUpIncompleteFieldsModel = jSONObjectOptJSONObject != null ? SignUpIncompleteFieldsModel.INSTANCE.parse(jSONObjectOptJSONObject) : null;
                List<SignUpField> list2 = companion.parseList(jo.optJSONArray("extend_suggested_fields"));
                boolean z10 = false;
                if (jo.optInt("should_show_additional_sign_up_agreement") == 1) {
                    z10 = true;
                }
                String strOptString4 = jo.optString("member_name");
                Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(...)");
                String strOptString5 = jo.optString("silent_token");
                Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
                String strOptString6 = jo.optString("silent_token_uuid");
                Intrinsics.checkNotNullExpressionValue(strOptString6, "optString(...)");
                int iOptInt2 = jo.optInt("silent_token_ttl");
                String strOptString7 = jo.optString("first_name");
                Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
                String strOptString8 = jo.optString("last_name");
                Intrinsics.checkNotNullExpressionValue(strOptString8, "optString(...)");
                String strOptString9 = jo.optString("photo50");
                Intrinsics.checkNotNullExpressionValue(strOptString9, "optString(...)");
                String strOptString10 = jo.optString("photo100");
                Intrinsics.checkNotNullExpressionValue(strOptString10, "optString(...)");
                String strOptString11 = jo.optString("photo200");
                Intrinsics.checkNotNullExpressionValue(strOptString11, "optString(...)");
                JSONArray jSONArrayOptJSONArray = jo.optJSONArray("domains");
                if (jSONArrayOptJSONArray != null) {
                    listEmptyList = new ArrayList(jSONArrayOptJSONArray.length());
                    int length = jSONArrayOptJSONArray.length();
                    int i10 = 0;
                    while (i10 < length) {
                        int i11 = length;
                        String string = jSONArrayOptJSONArray.getString(i10);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        listEmptyList.add(string);
                        i10++;
                        length = i11;
                        jSONArrayOptJSONArray = jSONArrayOptJSONArray;
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                }
                String strOptString12 = jo.optString("domain");
                Intrinsics.checkNotNullExpressionValue(strOptString12, "optString(...)");
                String strOptString13 = jo.optString("username");
                Intrinsics.checkNotNullExpressionValue(strOptString13, "optString(...)");
                return new ErrorInfo(strOptString, strOptString2, strOptString3, Boolean.valueOf(zOptBoolean), iOptInt, list, list2, signUpIncompleteFieldsModel, z10, strOptString4, strOptString5, strOptString6, iOptInt2, strOptString7, strOptString8, strOptString9, strOptString10, strOptString11, listEmptyList, strOptString12, strOptString13, jo.optInt("ads") == 1, jo.optInt("ads_on") == 1, UtilityTokens.INSTANCE.parse(jo));
            }

            private Companion() {
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ErrorInfo(@NotNull String accessToken, @Nullable String str, @Nullable String str2, @Nullable Boolean bool, int i10, @Nullable List<? extends SignUpField> list, @Nullable List<? extends SignUpField> list2, @Nullable SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean z10, @NotNull String memberName, @NotNull String silentToken, @NotNull String silentTokenUuid, int i11, @NotNull String firstName, @NotNull String lastName, @NotNull String photo50, @NotNull String photo100, @NotNull String photo200, @NotNull List<String> domains, @NotNull String domain, @NotNull String username, boolean z11, boolean z12, @NotNull UtilityTokens utilityTokens) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(memberName, "memberName");
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Intrinsics.checkNotNullParameter(photo50, "photo50");
            Intrinsics.checkNotNullParameter(photo100, "photo100");
            Intrinsics.checkNotNullParameter(photo200, "photo200");
            Intrinsics.checkNotNullParameter(domains, "domains");
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(utilityTokens, "utilityTokens");
            this.accessToken = accessToken;
            this.sid = str;
            this.phone = str2;
            this.instant = bool;
            this.status = i10;
            this.signUpFields = list;
            this.signUpSkippableFields = list2;
            this.signUpIncompleteFieldsModel = signUpIncompleteFieldsModel;
            this.signUpAgreementRequired = z10;
            this.memberName = memberName;
            this.silentToken = silentToken;
            this.silentTokenUuid = silentTokenUuid;
            this.silentTokenTtl = i11;
            this.firstName = firstName;
            this.lastName = lastName;
            this.photo50 = photo50;
            this.photo100 = photo100;
            this.photo200 = photo200;
            this.domains = domains;
            this.domain = domain;
            this.username = username;
            this.showAds = z11;
            this.adsIsOn = z12;
            this.utilityTokens = utilityTokens;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ErrorInfo copy$default(ErrorInfo errorInfo, String str, String str2, String str3, Boolean bool, int i10, List list, List list2, SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean z10, String str4, String str5, String str6, int i11, String str7, String str8, String str9, String str10, String str11, List list3, String str12, String str13, boolean z11, boolean z12, UtilityTokens utilityTokens, int i12, Object obj) {
            UtilityTokens utilityTokens2;
            boolean z13;
            String str14 = (i12 & 1) != 0 ? errorInfo.accessToken : str;
            String str15 = (i12 & 2) != 0 ? errorInfo.sid : str2;
            String str16 = (i12 & 4) != 0 ? errorInfo.phone : str3;
            Boolean bool2 = (i12 & 8) != 0 ? errorInfo.instant : bool;
            int i13 = (i12 & 16) != 0 ? errorInfo.status : i10;
            List list4 = (i12 & 32) != 0 ? errorInfo.signUpFields : list;
            List list5 = (i12 & 64) != 0 ? errorInfo.signUpSkippableFields : list2;
            SignUpIncompleteFieldsModel signUpIncompleteFieldsModel2 = (i12 & 128) != 0 ? errorInfo.signUpIncompleteFieldsModel : signUpIncompleteFieldsModel;
            boolean z14 = (i12 & 256) != 0 ? errorInfo.signUpAgreementRequired : z10;
            String str17 = (i12 & 512) != 0 ? errorInfo.memberName : str4;
            String str18 = (i12 & 1024) != 0 ? errorInfo.silentToken : str5;
            String str19 = (i12 & 2048) != 0 ? errorInfo.silentTokenUuid : str6;
            int i14 = (i12 & 4096) != 0 ? errorInfo.silentTokenTtl : i11;
            String str20 = (i12 & 8192) != 0 ? errorInfo.firstName : str7;
            String str21 = str14;
            String str22 = (i12 & 16384) != 0 ? errorInfo.lastName : str8;
            String str23 = (i12 & 32768) != 0 ? errorInfo.photo50 : str9;
            String str24 = (i12 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? errorInfo.photo100 : str10;
            String str25 = (i12 & 131072) != 0 ? errorInfo.photo200 : str11;
            List list6 = (i12 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? errorInfo.domains : list3;
            String str26 = (i12 & 524288) != 0 ? errorInfo.domain : str12;
            String str27 = (i12 & 1048576) != 0 ? errorInfo.username : str13;
            boolean z15 = (i12 & 2097152) != 0 ? errorInfo.showAds : z11;
            boolean z16 = (i12 & 4194304) != 0 ? errorInfo.adsIsOn : z12;
            if ((i12 & RemoteFilesRepository.BYTE_ARRAY_SIZE) != 0) {
                z13 = z16;
                utilityTokens2 = errorInfo.utilityTokens;
            } else {
                utilityTokens2 = utilityTokens;
                z13 = z16;
            }
            return errorInfo.copy(str21, str15, str16, bool2, i13, list4, list5, signUpIncompleteFieldsModel2, z14, str17, str18, str19, i14, str20, str22, str23, str24, str25, list6, str26, str27, z15, z13, utilityTokens2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getMemberName() {
            return this.memberName;
        }

        @NotNull
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getSilentToken() {
            return this.silentToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getSilentTokenTtl() {
            return this.silentTokenTtl;
        }

        @NotNull
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getFirstName() {
            return this.firstName;
        }

        @NotNull
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getLastName() {
            return this.lastName;
        }

        @NotNull
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getPhoto50() {
            return this.photo50;
        }

        @NotNull
        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getPhoto100() {
            return this.photo100;
        }

        @NotNull
        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getPhoto200() {
            return this.photo200;
        }

        @NotNull
        public final List<String> component19() {
            return this.domains;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSid() {
            return this.sid;
        }

        @NotNull
        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getDomain() {
            return this.domain;
        }

        @NotNull
        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getUsername() {
            return this.username;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final boolean getShowAds() {
            return this.showAds;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final boolean getAdsIsOn() {
            return this.adsIsOn;
        }

        @NotNull
        /* JADX INFO: renamed from: component24, reason: from getter */
        public final UtilityTokens getUtilityTokens() {
            return this.utilityTokens;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPhone() {
            return this.phone;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getInstant() {
            return this.instant;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getStatus() {
            return this.status;
        }

        @Nullable
        public final List<SignUpField> component6() {
            return this.signUpFields;
        }

        @Nullable
        public final List<SignUpField> component7() {
            return this.signUpSkippableFields;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final SignUpIncompleteFieldsModel getSignUpIncompleteFieldsModel() {
            return this.signUpIncompleteFieldsModel;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getSignUpAgreementRequired() {
            return this.signUpAgreementRequired;
        }

        @NotNull
        public final ErrorInfo copy(@NotNull String accessToken, @Nullable String sid, @Nullable String phone, @Nullable Boolean instant, int status, @Nullable List<? extends SignUpField> signUpFields, @Nullable List<? extends SignUpField> signUpSkippableFields, @Nullable SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean signUpAgreementRequired, @NotNull String memberName, @NotNull String silentToken, @NotNull String silentTokenUuid, int silentTokenTtl, @NotNull String firstName, @NotNull String lastName, @NotNull String photo50, @NotNull String photo100, @NotNull String photo200, @NotNull List<String> domains, @NotNull String domain, @NotNull String username, boolean showAds, boolean adsIsOn, @NotNull UtilityTokens utilityTokens) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(memberName, "memberName");
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            Intrinsics.checkNotNullParameter(silentTokenUuid, "silentTokenUuid");
            Intrinsics.checkNotNullParameter(firstName, "firstName");
            Intrinsics.checkNotNullParameter(lastName, "lastName");
            Intrinsics.checkNotNullParameter(photo50, "photo50");
            Intrinsics.checkNotNullParameter(photo100, "photo100");
            Intrinsics.checkNotNullParameter(photo200, "photo200");
            Intrinsics.checkNotNullParameter(domains, "domains");
            Intrinsics.checkNotNullParameter(domain, "domain");
            Intrinsics.checkNotNullParameter(username, "username");
            Intrinsics.checkNotNullParameter(utilityTokens, "utilityTokens");
            return new ErrorInfo(accessToken, sid, phone, instant, status, signUpFields, signUpSkippableFields, signUpIncompleteFieldsModel, signUpAgreementRequired, memberName, silentToken, silentTokenUuid, silentTokenTtl, firstName, lastName, photo50, photo100, photo200, domains, domain, username, showAds, adsIsOn, utilityTokens);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ErrorInfo)) {
                return false;
            }
            ErrorInfo errorInfo = (ErrorInfo) other;
            return Intrinsics.areEqual(this.accessToken, errorInfo.accessToken) && Intrinsics.areEqual(this.sid, errorInfo.sid) && Intrinsics.areEqual(this.phone, errorInfo.phone) && Intrinsics.areEqual(this.instant, errorInfo.instant) && this.status == errorInfo.status && Intrinsics.areEqual(this.signUpFields, errorInfo.signUpFields) && Intrinsics.areEqual(this.signUpSkippableFields, errorInfo.signUpSkippableFields) && Intrinsics.areEqual(this.signUpIncompleteFieldsModel, errorInfo.signUpIncompleteFieldsModel) && this.signUpAgreementRequired == errorInfo.signUpAgreementRequired && Intrinsics.areEqual(this.memberName, errorInfo.memberName) && Intrinsics.areEqual(this.silentToken, errorInfo.silentToken) && Intrinsics.areEqual(this.silentTokenUuid, errorInfo.silentTokenUuid) && this.silentTokenTtl == errorInfo.silentTokenTtl && Intrinsics.areEqual(this.firstName, errorInfo.firstName) && Intrinsics.areEqual(this.lastName, errorInfo.lastName) && Intrinsics.areEqual(this.photo50, errorInfo.photo50) && Intrinsics.areEqual(this.photo100, errorInfo.photo100) && Intrinsics.areEqual(this.photo200, errorInfo.photo200) && Intrinsics.areEqual(this.domains, errorInfo.domains) && Intrinsics.areEqual(this.domain, errorInfo.domain) && Intrinsics.areEqual(this.username, errorInfo.username) && this.showAds == errorInfo.showAds && this.adsIsOn == errorInfo.adsIsOn && Intrinsics.areEqual(this.utilityTokens, errorInfo.utilityTokens);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        public final boolean getAdsIsOn() {
            return this.adsIsOn;
        }

        @NotNull
        public final String getDomain() {
            return this.domain;
        }

        @NotNull
        public final List<String> getDomains() {
            return this.domains;
        }

        @NotNull
        public final String getFirstName() {
            return this.firstName;
        }

        @Nullable
        public final Boolean getInstant() {
            return this.instant;
        }

        @NotNull
        public final String getLastName() {
            return this.lastName;
        }

        @NotNull
        public final String getMemberName() {
            return this.memberName;
        }

        @Nullable
        public final String getPhone() {
            return this.phone;
        }

        @NotNull
        public final String getPhoto100() {
            return this.photo100;
        }

        @NotNull
        public final String getPhoto200() {
            return this.photo200;
        }

        @NotNull
        public final String getPhoto50() {
            return this.photo50;
        }

        public final boolean getShowAds() {
            return this.showAds;
        }

        @Nullable
        public final String getSid() {
            return this.sid;
        }

        public final boolean getSignUpAgreementRequired() {
            return this.signUpAgreementRequired;
        }

        @Nullable
        public final List<SignUpField> getSignUpFields() {
            return this.signUpFields;
        }

        @Nullable
        public final SignUpIncompleteFieldsModel getSignUpIncompleteFieldsModel() {
            return this.signUpIncompleteFieldsModel;
        }

        @Nullable
        public final List<SignUpField> getSignUpSkippableFields() {
            return this.signUpSkippableFields;
        }

        @NotNull
        public final String getSilentToken() {
            return this.silentToken;
        }

        public final int getSilentTokenTtl() {
            return this.silentTokenTtl;
        }

        @NotNull
        public final String getSilentTokenUuid() {
            return this.silentTokenUuid;
        }

        public final int getStatus() {
            return this.status;
        }

        @NotNull
        public final String getUsername() {
            return this.username;
        }

        @NotNull
        public final UtilityTokens getUtilityTokens() {
            return this.utilityTokens;
        }

        public int hashCode() {
            int iHashCode = this.accessToken.hashCode() * 31;
            String str = this.sid;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.phone;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Boolean bool = this.instant;
            int iHashCode4 = (Integer.hashCode(this.status) + ((iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31)) * 31;
            List<SignUpField> list = this.signUpFields;
            int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
            List<SignUpField> list2 = this.signUpSkippableFields;
            int iHashCode6 = (iHashCode5 + (list2 == null ? 0 : list2.hashCode())) * 31;
            SignUpIncompleteFieldsModel signUpIncompleteFieldsModel = this.signUpIncompleteFieldsModel;
            return this.utilityTokens.hashCode() + ((Boolean.hashCode(this.adsIsOn) + ((Boolean.hashCode(this.showAds) + ((this.username.hashCode() + ((this.domain.hashCode() + ((this.domains.hashCode() + ((this.photo200.hashCode() + ((this.photo100.hashCode() + ((this.photo50.hashCode() + ((this.lastName.hashCode() + ((this.firstName.hashCode() + ((Integer.hashCode(this.silentTokenTtl) + ((this.silentTokenUuid.hashCode() + ((this.silentToken.hashCode() + ((this.memberName.hashCode() + ((Boolean.hashCode(this.signUpAgreementRequired) + ((iHashCode6 + (signUpIncompleteFieldsModel != null ? signUpIncompleteFieldsModel.hashCode() : 0)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
        }

        @NotNull
        public String toString() {
            return "ErrorInfo(accessToken=" + this.accessToken + ", sid=" + this.sid + ", phone=" + this.phone + ", instant=" + this.instant + ", status=" + this.status + ", signUpFields=" + this.signUpFields + ", signUpSkippableFields=" + this.signUpSkippableFields + ", signUpIncompleteFieldsModel=" + this.signUpIncompleteFieldsModel + ", signUpAgreementRequired=" + this.signUpAgreementRequired + ", memberName=" + this.memberName + ", silentToken=" + this.silentToken + ", silentTokenUuid=" + this.silentTokenUuid + ", silentTokenTtl=" + this.silentTokenTtl + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", photo50=" + this.photo50 + ", photo100=" + this.photo100 + ", photo200=" + this.photo200 + ", domains=" + this.domains + ", domain=" + this.domain + ", username=" + this.username + ", showAds=" + this.showAds + ", adsIsOn=" + this.adsIsOn + ", utilityTokens=" + this.utilityTokens + ')';
        }

        public /* synthetic */ ErrorInfo(String str, String str2, String str3, Boolean bool, int i10, List list, List list2, SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean z10, String str4, String str5, String str6, int i11, String str7, String str8, String str9, String str10, String str11, List list3, String str12, String str13, boolean z11, boolean z12, UtilityTokens utilityTokens, int i12, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, bool, i10, list, (i12 & 64) != 0 ? null : list2, signUpIncompleteFieldsModel, z10, str4, str5, str6, i11, str7, str8, str9, str10, str11, list3, str12, str13, z11, z12, utilityTokens);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ AuthAnswer(String str, String str2, UserId userId, int i10, boolean z10, String str3, UtilityTokens utilityTokens, String str4, String str5, String str6, int i11, List list, List list2, String str7, ValidationType validationType, ValidationType validationType2, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i12, long j10, String str16, String str17, ErrorInfo errorInfo, Optional optional, BanInfo banInfo, long j11, String str18, boolean z11, String str19, String str20, int i13, int i14, List list3, List list4, SignUpIncompleteFieldsModel signUpIncompleteFieldsModel, boolean z12, String str21, ArrayList arrayList, ApiErrorViewType apiErrorViewType, String str22, String str23, ValidateInfo validateInfo, SendOtpInfo sendOtpInfo, int i15, int i16, DefaultConstructorMarker defaultConstructorMarker) {
        String str24 = (i15 & 1) != 0 ? "" : str;
        this(str24, (i15 & 2) != 0 ? "" : str2, (i15 & 4) != 0 ? UserId.DEFAULT : userId, (i15 & 8) != 0 ? 0 : i10, (i15 & 16) != 0 ? true : z10, (i15 & 32) != 0 ? "" : str3, (i15 & 64) != 0 ? UtilityTokens.INSTANCE.getEmpty() : utilityTokens, (i15 & 128) != 0 ? null : str4, (i15 & 256) != 0 ? "" : str5, (i15 & 512) != 0 ? "" : str6, (i15 & 1024) != 0 ? 0 : i11, (i15 & 2048) != 0 ? CollectionsKt.emptyList() : list, (i15 & 4096) != 0 ? CollectionsKt.emptyList() : list2, (i15 & 8192) != 0 ? "" : str7, (i15 & 16384) != 0 ? ValidationType.URL : validationType, (i15 & 32768) != 0 ? ValidationType.URL : validationType2, (i15 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? "" : str8, (i15 & 131072) != 0 ? null : str9, (i15 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? "" : str10, (i15 & 524288) != 0 ? "" : str11, (i15 & 1048576) != 0 ? "" : str12, (i15 & 2097152) != 0 ? "" : str13, (i15 & 4194304) != 0 ? "" : str14, (i15 & RemoteFilesRepository.BYTE_ARRAY_SIZE) != 0 ? "" : str15, (i15 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 ? 0 : i12, (i15 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? 0L : j10, (i15 & 67108864) != 0 ? "" : str16, (i15 & 134217728) != 0 ? "" : str17, (i15 & SQLiteDatabase.CREATE_IF_NECESSARY) != 0 ? null : errorInfo, (i15 & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? null : optional, (i15 & 1073741824) != 0 ? null : banInfo, (i15 & Integer.MIN_VALUE) == 0 ? j11 : 0L, (i16 & 1) != 0 ? "" : str18, (i16 & 2) != 0 ? false : z11, (i16 & 4) != 0 ? "" : str19, (i16 & 8) == 0 ? str20 : "", (i16 & 16) != 0 ? 0 : i13, (i16 & 32) != 0 ? 0 : i14, (i16 & 64) != 0 ? null : list3, (i16 & 128) != 0 ? null : list4, (i16 & 256) != 0 ? null : signUpIncompleteFieldsModel, (i16 & 512) != 0 ? false : z12, (i16 & 1024) != 0 ? null : str21, (i16 & 2048) != 0 ? null : arrayList, (i16 & 4096) != 0 ? null : apiErrorViewType, (i16 & 8192) != 0 ? null : str22, (i16 & 16384) != 0 ? null : str23, (i16 & 32768) != 0 ? null : validateInfo, (i16 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? null : sendOtpInfo);
    }

    public AuthAnswer(@NotNull JSONObject jo) throws JSONException {
        List listEmptyList;
        UserId userId;
        List listEmptyList2;
        Intrinsics.checkNotNullParameter(jo, "jo");
        String strOptString = jo.optString("access_token");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String strOptString2 = jo.optString(AccountManagerRepositoryImpl.SECRET_ARG);
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        UserId userId2 = UserIdKt.toUserId(jo.optLong("user_id"));
        int iOptInt = jo.optInt("expires_in");
        boolean zAreEqual = Intrinsics.areEqual(jo.optString("https_required", "1"), "1");
        String strOptString3 = jo.optString("trusted_hash");
        Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
        UtilityTokens utilityTokens = UtilityTokens.INSTANCE.parse(jo);
        String strOptString4 = jo.has("email_to_actualize") ? jo.optString("email_to_actualize") : null;
        String strOptString5 = jo.optString("silent_token");
        Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(...)");
        String strOptString6 = jo.optString("silent_token_uuid");
        Intrinsics.checkNotNullExpressionValue(strOptString6, "optString(...)");
        int iOptInt2 = jo.optInt("silent_token_ttl");
        JSONArray jSONArrayOptJSONArray = jo.optJSONArray("provided_hashes");
        if (jSONArrayOptJSONArray != null) {
            listEmptyList = new ArrayList(jSONArrayOptJSONArray.length());
            int length = jSONArrayOptJSONArray.length();
            int i10 = 0;
            while (i10 < length) {
                int i11 = length;
                String string = jSONArrayOptJSONArray.getString(i10);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                listEmptyList.add(string);
                i10++;
                length = i11;
            }
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list = listEmptyList;
        JSONArray jSONArrayOptJSONArray2 = jo.optJSONArray("provided_uuids");
        if (jSONArrayOptJSONArray2 != null) {
            listEmptyList2 = new ArrayList(jSONArrayOptJSONArray2.length());
            int length2 = jSONArrayOptJSONArray2.length();
            int i12 = 0;
            while (true) {
                userId = userId2;
                if (i12 >= length2) {
                    break;
                }
                String string2 = jSONArrayOptJSONArray2.getString(i12);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                listEmptyList2.add(string2);
                i12++;
                userId2 = userId;
            }
        } else {
            userId = userId2;
            listEmptyList2 = CollectionsKt.emptyList();
        }
        List list2 = listEmptyList2;
        String strOptString7 = jo.optString("redirect_uri");
        Intrinsics.checkNotNullExpressionValue(strOptString7, "optString(...)");
        String strOptString8 = jo.optString("validation_type");
        ValidationType.Companion companion = ValidationType.INSTANCE;
        Intrinsics.checkNotNull(strOptString8);
        ValidationType validationType = companion.parse(strOptString8);
        String strOptString9 = jo.optString("validation_resend");
        Intrinsics.checkNotNull(strOptString9);
        ValidationType validationType2 = companion.parse(strOptString9);
        String strOptString10 = jo.optString("validation_sid");
        Intrinsics.checkNotNullExpressionValue(strOptString10, "optString(...)");
        String strOptString11 = jo.optString("validation_external_id", null);
        String strOptString12 = jo.optString("phone_mask");
        Intrinsics.checkNotNullExpressionValue(strOptString12, "optString(...)");
        String strOptString13 = jo.optString("masked_email");
        Intrinsics.checkNotNullExpressionValue(strOptString13, "optString(...)");
        String strOptString14 = jo.optString("error_type");
        Intrinsics.checkNotNullExpressionValue(strOptString14, "optString(...)");
        String strOptString15 = jo.optString("email");
        Intrinsics.checkNotNullExpressionValue(strOptString15, "optString(...)");
        String strOptString16 = jo.optString("phone");
        Intrinsics.checkNotNullExpressionValue(strOptString16, "optString(...)");
        String strOptString17 = jo.optString(DeviceInfo.PARAM_KEY_DEVICE_NAME);
        Intrinsics.checkNotNullExpressionValue(strOptString17, "optString(...)");
        int iOptInt3 = jo.optInt(ConfirmPhoneFragment.EXT_CODE_LENGTH);
        long jOptLong = jo.optLong("delay");
        String strOptString18 = jo.optString("error");
        Intrinsics.checkNotNullExpressionValue(strOptString18, "optString(...)");
        String strOptString19 = jo.optString("error_description");
        Intrinsics.checkNotNullExpressionValue(strOptString19, "optString(...)");
        JSONObject jSONObjectOptJSONObject = jo.optJSONObject(XmailMigrationPromoSheet.BUTTON_INFO);
        ErrorInfo errorInfo = jSONObjectOptJSONObject != null ? ErrorInfo.INSTANCE.parse(jSONObjectOptJSONObject) : null;
        JSONObject jSONObjectOptJSONObject2 = jo.optJSONObject("optional");
        ErrorInfo errorInfo2 = errorInfo;
        Optional optional = jSONObjectOptJSONObject2 != null ? Optional.INSTANCE.parse(jSONObjectOptJSONObject2) : null;
        JSONObject jSONObjectOptJSONObject3 = jo.optJSONObject(VKApiCodes.PARAM_BAN_INFO);
        Optional optional2 = optional;
        BanInfo banInfo = jSONObjectOptJSONObject3 != null ? BanInfo.INSTANCE.parse(jSONObjectOptJSONObject3) : null;
        long jOptLong2 = jo.optLong("restore_request_id");
        String strOptString20 = jo.optString("restore_hash");
        Intrinsics.checkNotNullExpressionValue(strOptString20, "optString(...)");
        BanInfo banInfo2 = banInfo;
        boolean zOptBoolean = jo.optBoolean("cant_get_code_open_restore");
        String strOptString21 = jo.optString(SessionSQLiteHelper.COLUMN_WEBVIEW_AT);
        Intrinsics.checkNotNullExpressionValue(strOptString21, "optString(...)");
        String strOptString22 = jo.optString(SessionSQLiteHelper.COLUMN_WEBVIEW_RT);
        Intrinsics.checkNotNullExpressionValue(strOptString22, "optString(...)");
        int iOptInt4 = jo.optInt("webview_access_token_expires_in");
        int iOptInt5 = jo.optInt("webview_refresh_token_expires_in");
        SignUpField.Companion companion2 = SignUpField.INSTANCE;
        List<SignUpField> list3 = companion2.parseList(jo.optJSONArray("extend_fields"));
        List<SignUpField> list4 = companion2.parseList(jo.optJSONArray("extend_suggested_fields"));
        JSONObject jSONObjectOptJSONObject4 = jo.optJSONObject("extend_fields_values");
        SignUpIncompleteFieldsModel signUpIncompleteFieldsModel = jSONObjectOptJSONObject4 != null ? SignUpIncompleteFieldsModel.INSTANCE.parse(jSONObjectOptJSONObject4) : null;
        boolean z10 = jo.optInt("should_show_additional_sign_up_agreement") == 1;
        String strOptString23 = jo.optString(PasskeyBeginResult.SID_KEY, null);
        String strOptString24 = jo.optString(Promotion.ACTION_VIEW);
        ApiErrorViewType apiErrorViewTypeFromString = strOptString24 != null ? ApiErrorViewType.INSTANCE.fromString(strOptString24) : null;
        String strOptString25 = jo.optString("super_app_token");
        String strOptString26 = jo.optString(com.huawei.hms.support.api.entity.common.CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, "");
        String str = !StringsKt.isBlank(strOptString26) ? strOptString26 : null;
        JSONObject jSONObjectOptJSONObject5 = jo.optJSONObject("validate_info");
        ValidateInfo validateInfo = jSONObjectOptJSONObject5 != null ? ValidateInfo.INSTANCE.parse(jSONObjectOptJSONObject5) : null;
        JSONObject jSONObjectOptJSONObject6 = jo.optJSONObject("send_otp_info");
        this(strOptString, strOptString2, userId, iOptInt, zAreEqual, strOptString3, utilityTokens, strOptString4, strOptString5, strOptString6, iOptInt2, list, list2, strOptString7, validationType, validationType2, strOptString10, strOptString11, strOptString12, strOptString13, strOptString14, strOptString15, strOptString16, strOptString17, iOptInt3, jOptLong, strOptString18, strOptString19, errorInfo2, optional2, banInfo2, jOptLong2, strOptString20, zOptBoolean, strOptString21, strOptString22, iOptInt4, iOptInt5, list3, list4, signUpIncompleteFieldsModel, z10, strOptString23, null, apiErrorViewTypeFromString, strOptString25, str, validateInfo, jSONObjectOptJSONObject6 != null ? SendOtpInfo.INSTANCE.parse(jSONObjectOptJSONObject6) : null, 0, 2048, null);
    }
}
