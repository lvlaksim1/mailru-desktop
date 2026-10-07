package ru.mail.auth.webview;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.api.client.auth.oauth2.TokenResponse;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class GoogleClientTokenResponse implements OAuthTokenResponse {
    public static final Parcelable.Creator<GoogleClientTokenResponse> CREATOR = new Parcelable.Creator<GoogleClientTokenResponse>() { // from class: ru.mail.auth.webview.GoogleClientTokenResponse.1
        @Override // android.os.Parcelable.Creator
        public GoogleClientTokenResponse createFromParcel(Parcel parcel) {
            return new GoogleClientTokenResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public GoogleClientTokenResponse[] newArray(int i10) {
            return new GoogleClientTokenResponse[i10];
        }
    };
    private final TokenResponse mTokenResponse;

    public GoogleClientTokenResponse(TokenResponse tokenResponse) {
        this.mTokenResponse = tokenResponse;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public String getAccessToken() {
        return this.mTokenResponse.getAccessToken();
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public Long getExpiresInSeconds() {
        return this.mTokenResponse.getExpiresInSeconds();
    }

    @Override // ru.mail.auth.webview.OAuthTokenResponse
    @Nullable
    public String getRefreshToken() {
        return this.mTokenResponse.getRefreshToken();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i10) {
        parcel.writeString(this.mTokenResponse.getAccessToken());
        parcel.writeString(this.mTokenResponse.getTokenType());
        parcel.writeLong(this.mTokenResponse.getExpiresInSeconds().longValue());
        parcel.writeString(this.mTokenResponse.getRefreshToken());
        parcel.writeString(this.mTokenResponse.getScope());
    }

    public GoogleClientTokenResponse(Parcel parcel) {
        TokenResponse tokenResponse = new TokenResponse();
        this.mTokenResponse = tokenResponse;
        tokenResponse.setAccessToken(parcel.readString());
        tokenResponse.setTokenType(parcel.readString());
        tokenResponse.setExpiresInSeconds(Long.valueOf(parcel.readLong()));
        tokenResponse.setRefreshToken(parcel.readString());
        tokenResponse.setScope(parcel.readString());
    }
}
