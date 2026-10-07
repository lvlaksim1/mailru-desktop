package ru.mail.auth;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class DoregistrationParameter implements Parcelable {
    public static final Parcelable.Creator<DoregistrationParameter> CREATOR = new Parcelable.Creator<DoregistrationParameter>() { // from class: ru.mail.auth.DoregistrationParameter.1
        @Override // android.os.Parcelable.Creator
        public DoregistrationParameter createFromParcel(Parcel parcel) {
            return new DoregistrationParameter(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DoregistrationParameter[] newArray(int i10) {
            return new DoregistrationParameter[i10];
        }
    };
    private final Bitmap captcha;
    private final boolean captchaRequired;
    private final String cookie;
    private final String firstName;
    private final String lastName;
    private final String regId;

    public static Builder builder() {
        return new Builder();
    }

    private byte convertBooleanToByte(boolean z10) {
        return z10 ? (byte) 1 : (byte) 0;
    }

    private boolean convertByteToBoolean(byte b10) {
        return b10 == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Builder getBuilder() {
        return new Builder();
    }

    public Bitmap getCaptcha() {
        return this.captcha;
    }

    public String getCookie() {
        return this.cookie;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRegId() {
        return this.regId;
    }

    public boolean isCaptchaRequired() {
        return this.captchaRequired;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.regId);
        parcel.writeByte(convertBooleanToByte(this.captchaRequired));
        parcel.writeParcelable(this.captcha, 0);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.cookie);
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Builder {
        private Bitmap captcha;
        private boolean captchaRequired;
        private String cookie;
        private String firstName;
        private String lastName;
        private String regId;

        public DoregistrationParameter build() {
            return new DoregistrationParameter(this.regId, this.captchaRequired, this.captcha, this.firstName, this.lastName, this.cookie);
        }

        public Builder setCaptcha(Bitmap bitmap) {
            this.captcha = bitmap;
            return this;
        }

        public Builder setCaptchaRequired(boolean z10) {
            this.captchaRequired = z10;
            return this;
        }

        public Builder setCookie(String str) {
            this.cookie = str;
            return this;
        }

        public Builder setFirstName(String str) {
            this.firstName = str;
            return this;
        }

        public Builder setLastName(String str) {
            this.lastName = str;
            return this;
        }

        public Builder setRegId(String str) {
            this.regId = str;
            return this;
        }

        private Builder(DoregistrationParameter doregistrationParameter) {
            this.regId = doregistrationParameter.regId;
            this.captchaRequired = doregistrationParameter.captchaRequired;
            this.captcha = doregistrationParameter.captcha;
            this.firstName = doregistrationParameter.getFirstName();
            this.lastName = doregistrationParameter.getLastName();
            this.cookie = doregistrationParameter.cookie;
        }

        private Builder() {
        }
    }

    private DoregistrationParameter(String str, boolean z10, Bitmap bitmap, String str2, String str3, String str4) {
        this.regId = str;
        this.captchaRequired = z10;
        this.captcha = bitmap;
        this.firstName = str2;
        this.lastName = str3;
        this.cookie = str4;
    }

    private DoregistrationParameter(Parcel parcel) {
        this.regId = parcel.readString();
        this.captchaRequired = convertByteToBoolean(parcel.readByte());
        this.captcha = (Bitmap) parcel.readParcelable(null);
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.cookie = parcel.readString();
    }
}
