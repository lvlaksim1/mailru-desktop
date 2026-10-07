package ru.mail.authorizationsdk.data.pikachucaptcha;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.domain.usecase.pikachu.model.PikachuResult;
import ru.mail.data.entities.MailThreadRepresentation;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0086@¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaRepository;", "", "resources", "Lru/mail/android_utils/wrapper/Resources;", "pikachuCaptchaApi", "Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaApi;", "<init>", "(Lru/mail/android_utils/wrapper/Resources;Lru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaApi;)V", "getPikachuCaptcha", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPikachuCaptchaRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PikachuCaptchaRepository.kt\nru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,45:1\n295#2,2:46\n*S KotlinDebug\n*F\n+ 1 PikachuCaptchaRepository.kt\nru/mail/authorizationsdk/data/pikachucaptcha/PikachuCaptchaRepository\n*L\n26#1:46,2\n*E\n"})
public final class PikachuCaptchaRepository {

    @NotNull
    private static final String HEADER_CAPTCHA_ID = "X-Captcha-ID";

    @NotNull
    private static final String HEADER_COOKIE = "Set-cookie";

    @NotNull
    private static final String MRCU_COOKIE = "mrcu";

    @NotNull
    private final PikachuCaptchaApi pikachuCaptchaApi;

    @NotNull
    private final Resources resources;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaRepository$getPikachuCaptcha$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaRepository", f = "PikachuCaptchaRepository.kt", i = {}, l = {19}, m = "getPikachuCaptcha", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PikachuCaptchaRepository.this.getPikachuCaptcha(this);
        }
    }

    public PikachuCaptchaRepository(@NotNull Resources resources, @NotNull PikachuCaptchaApi pikachuCaptchaApi) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(pikachuCaptchaApi, "pikachuCaptchaApi");
        this.resources = resources;
        this.pikachuCaptchaApi = pikachuCaptchaApi;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Nullable
    public final Object getPikachuCaptcha(@NotNull Continuation<? super PikachuResult> continuation) {
        AnonymousClass1 anonymousClass1;
        byte[] bArrBytes;
        List listSplit$default;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object pikachuCaptcha$default = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        String str = null;
        Object obj = null;
        str = null;
        try {
            if (i11 == 0) {
                ResultKt.throwOnFailure(pikachuCaptcha$default);
                PikachuCaptchaApi pikachuCaptchaApi = this.pikachuCaptchaApi;
                anonymousClass1.label = 1;
                pikachuCaptcha$default = PikachuCaptchaApi.getPikachuCaptcha$default(pikachuCaptchaApi, null, anonymousClass1, 1, null);
                if (pikachuCaptcha$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(pikachuCaptcha$default);
            }
            Response response = (Response) pikachuCaptcha$default;
            Headers headers = response.headers();
            String str2 = headers.get(HEADER_CAPTCHA_ID);
            String str3 = headers.get(HEADER_COOKIE);
            if (str3 == null) {
                String lowerCase = HEADER_COOKIE.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                str3 = headers.get(lowerCase);
            }
            String str4 = str3;
            if (str4 != null && (listSplit$default = StringsKt.split$default((CharSequence) str4, new String[]{MailThreadRepresentation.PAYLOAD_DELIM_CHAR}, false, 0, 6, (Object) null)) != null) {
                for (Object obj2 : listSplit$default) {
                    if (StringsKt.contains$default((CharSequence) obj2, (CharSequence) MRCU_COOKIE, false, 2, (Object) null)) {
                        obj = obj2;
                        break;
                    }
                }
                str = (String) obj;
            }
            if (str == null || str.length() == 0) {
                return new PikachuResult.Error(this.resources.getString(R.string.authenticator_network_error));
            }
            ResponseBody responseBody = (ResponseBody) response.body();
            if (responseBody == null || (bArrBytes = responseBody.bytes()) == null) {
                return new PikachuResult.Error(this.resources.getString(R.string.check_your_internet));
            }
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrBytes, 0, bArrBytes.length);
            Intrinsics.checkNotNull(bitmapDecodeByteArray);
            return new PikachuResult.Success(bitmapDecodeByteArray, str, str2);
        } catch (Exception unused) {
            return new PikachuResult.Error(this.resources.getString(R.string.authenticator_network_error));
        }
    }
}
