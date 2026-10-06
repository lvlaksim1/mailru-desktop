package ru.mail.auth.request;

import android.content.Context;
import java.util.Objects;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"oauth2_yandex_token"})
public class YandexOAuthLoginRequest extends BaseOAuthLoginRequest<Params> {
    private static final String ACCESS_TOKEN = "access_token";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends BaseOAuthLoginRequest.Params {

        @Param(method = HttpMethod.GET, name = "access_token")
        private final String mAccessToken;

        @Param(method = HttpMethod.GET, name = "expires")
        private final long mExpires;

        public Params(Context context, OauthParams oauthParams, String str, long j10) {
            super(context, oauthParams, "");
            this.mAccessToken = str;
            this.mExpires = j10;
        }

        @Override // ru.mail.auth.request.BaseOAuthLoginRequest.Params
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mExpires != params.mExpires) {
                return false;
            }
            return Objects.equals(this.mAccessToken, params.mAccessToken);
        }

        @Override // ru.mail.auth.request.BaseOAuthLoginRequest.Params
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mAccessToken;
            return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Long.hashCode(this.mExpires);
        }
    }

    public YandexOAuthLoginRequest(Context context, HostProvider hostProvider, String str, long j10, OauthParams oauthParams, boolean z10) {
        super(context, hostProvider, new Params(context, oauthParams, str, j10), z10);
    }

    @Override // ru.mail.auth.request.BaseOAuthLoginRequest, ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat("access_token"), Formats.newJsonFormat("access_token"));
        return logFilterPrepareTokenFilter;
    }
}
