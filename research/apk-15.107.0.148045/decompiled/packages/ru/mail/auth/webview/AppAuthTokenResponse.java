package ru.mail.auth.webview;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import net.openid.appauth.TokenResponse;
import org.json.JSONException;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class AppAuthTokenResponse implements OAuthTokenResponse {
    private final TokenResponse mTokenResponse;
    private static final Log LOG = Log.getLog("AppAuthTokenResponse");
    public static final Parcelable.Creator<AppAuthTokenResponse> CREATOR = new Parcelable.Creator<AppAuthTokenResponse>() { // from class: ru.mail.auth.webview.AppAuthTokenResponse.1
        @Override // android.os.Parcelable.Creator
        public AppAuthTokenResponse createFromParcel(Parcel parcel) {
            return new AppAuthTokenResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AppAuthTokenResponse[] newArray(int i10) {
            return new AppAuthTokenResponse[i10];
        }
    };

    public AppAuthTokenResponse(TokenResponse tokenResponse) {
        this.mTokenResponse = tokenResponse;
    }

    @Nullable
    private TokenResponse readTokenResponse(Parcel parcel) {
        try {
            return TokenResponse.jsonDeserialize(parcel.readString());
        } catch (JSONException e10) {
            LOG.e("Can't deserialize TokenResponse, Parcel was empty or null", e10);
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public String getAccessToken() {
        TokenResponse tokenResponse = this.mTokenResponse;
        if (tokenResponse != null) {
            return tokenResponse.f77522c;
        }
        return null;
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public Long getExpiresInSeconds() {
        TokenResponse tokenResponse = this.mTokenResponse;
        if (tokenResponse != null) {
            return tokenResponse.f77523d;
        }
        return null;
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public String getRefreshToken() {
        TokenResponse tokenResponse = this.mTokenResponse;
        if (tokenResponse != null) {
            return tokenResponse.f77525f;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i10) {
        parcel.writeString(this.mTokenResponse.jsonSerializeString());
    }

    public AppAuthTokenResponse(Parcel parcel) {
        this.mTokenResponse = readTokenResponse(parcel);
    }
}
