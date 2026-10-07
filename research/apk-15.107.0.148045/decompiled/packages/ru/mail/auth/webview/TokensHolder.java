package ru.mail.auth.webview;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.mail.authorizesdk.auth.providers.oauth.OAuthTokenResponseProvider;
import ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class TokensHolder implements TokensHolderProvider {
    private String mAccountType;
    private String mEmail;
    private String mErrorMessage;
    private OAuthTokenResponse mTokenResponse;
    private static final Log LOG = Log.getLog("TokensHolder");
    public static final Parcelable.Creator<TokensHolder> CREATOR = new Parcelable.Creator<TokensHolder>() { // from class: ru.mail.auth.webview.TokensHolder.1
        @Override // android.os.Parcelable.Creator
        public TokensHolder createFromParcel(Parcel parcel) {
            return new TokensHolder(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public TokensHolder[] newArray(int i10) {
            return new TokensHolder[i10];
        }
    };

    public TokensHolder() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    public String getEmail() {
        return this.mEmail;
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider
    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public void setAccountType(String str) {
        this.mAccountType = str;
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider
    public void setEmail(String str) {
        this.mEmail = str;
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider
    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    void setTokenResponse(OAuthTokenResponse oAuthTokenResponse) {
        this.mTokenResponse = oAuthTokenResponse;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i10) {
        parcel.writeParcelable(this.mTokenResponse, i10);
        parcel.writeString(this.mErrorMessage);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mAccountType);
    }

    public TokensHolder(Parcel parcel) {
        this.mTokenResponse = (OAuthTokenResponse) parcel.readParcelable(TokensHolder.class.getClassLoader());
        this.mErrorMessage = parcel.readString();
        this.mEmail = parcel.readString();
        this.mAccountType = parcel.readString();
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider
    public OAuthTokenResponse getTokenResponse() {
        return this.mTokenResponse;
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.TokensHolderProvider
    public void setTokenResponse(@Nullable OAuthTokenResponseProvider oAuthTokenResponseProvider) {
        this.mTokenResponse = (OAuthTokenResponse) oAuthTokenResponseProvider;
    }
}
