package ru.mail.auth.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@UrlPath(pathSegments = {"c", "{size}"})
public class GetCaptchaRequest extends SingleRequest<Params, Result> {
    private static final int CAPTCHA_SIZE = 6;
    private static final String MRCU_COOKE_NAME = "mrcu";
    public static final Formats.ParamFormat MRCU_COOKIE_FORMAT = Formats.newUrlFormat(MRCU_COOKE_NAME);

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class Params {
        private static final String PARAM_KEY_CAPTCHA_SIZE = "size";

        @Param(method = HttpMethod.URL, name = "size")
        private final int mCaptchaSize;

        public Params(int i10) {
            this.mCaptchaSize = i10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.mCaptchaSize == ((Params) obj).mCaptchaSize;
        }

        public int hashCode() {
            return this.mCaptchaSize;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class Result {
        private final Bitmap mBitmap;
        private final String mMrcuCookie;
        private final String mXcaptchaId;

        public Result(Bitmap bitmap, String str, String str2) {
            this.mBitmap = bitmap;
            this.mMrcuCookie = str;
            this.mXcaptchaId = str2;
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getMrcuCookie() {
            return this.mMrcuCookie;
        }

        public String getXcaptchaId() {
            return this.mXcaptchaId;
        }
    }

    public GetCaptchaRequest(Context context, HostProvider hostProvider, boolean z10) {
        super(context, new Params(6), hostProvider, z10);
    }

    public static FilteringStrategy.Constraint getConstraint() {
        return Constraints.newParamNamedConstraint(MRCU_COOKIE_FORMAT);
    }

    private String getXCaptchaId() {
        List<String> list = getNetworkService().getHeaderFields().get("X-Captcha-ID");
        return (list == null || list.size() <= 0) ? "" : list.get(0);
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected String getPathTag() {
        return "c_size";
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new Result(BitmapFactory.decodeByteArray(response.getData(), 0, response.getData().length), SingleRequest.extractCookie(getNetworkService(), MRCU_COOKE_NAME, MRCU_COOKIE_FORMAT), getXCaptchaId());
    }
}
