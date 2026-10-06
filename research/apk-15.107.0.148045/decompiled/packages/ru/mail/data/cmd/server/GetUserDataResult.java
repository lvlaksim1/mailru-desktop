package ru.mail.data.cmd.server;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import kotlin.Deprecated;
import ru.mail.logic.child.DisablingParentalMode;
import ru.mail.logic.child.ParentalMode;
import ru.mail.logic.content.MetaThreadEnableState;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class GetUserDataResult {

    @Nullable
    private String mB2BFlags;
    private long mBillingBitmask;
    private long mBirthday;

    @Nullable
    private String mCityId;
    private final String mEmail;
    private String mGender;
    private boolean mHasAnyPhone;
    private boolean mIsEnteredBetaOnWeb;
    private boolean mIsMetaThreadsEnabled;
    private boolean mIsPhoneVerified;
    private long mRegDate;
    private String mVkcId;

    @NonNull
    private String mFirstName = "";

    @NonNull
    private String mLastName = "";

    @Nullable
    private String mPhone = "";
    private String mTheme = "";

    @Nullable
    private String mAvatarUrl = "";

    @NonNull
    private List<MetaThreadEnableState> mMetaThreadStates = Collections.EMPTY_LIST;

    @NonNull
    private ParentalMode mParentalMode = ParentalMode.OFF;
    private boolean mParentalModeHasChildren = false;

    @NonNull
    private DisablingParentalMode mDisablingParentalMode = DisablingParentalMode.NULL;

    public GetUserDataResult(@NonNull String str) {
        this.mEmail = str;
    }

    @Nullable
    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    @Nullable
    public String getB2BFlags() {
        return this.mB2BFlags;
    }

    public long getBillingBitmask() {
        return this.mBillingBitmask;
    }

    public long getBirthday() {
        return this.mBirthday;
    }

    @Nullable
    public String getCityId() {
        return this.mCityId;
    }

    @NonNull
    public DisablingParentalMode getDisablingParentalMode() {
        return this.mDisablingParentalMode;
    }

    @NonNull
    public String getEmail() {
        return this.mEmail;
    }

    public boolean getEnteredBetaOnWeb() {
        return this.mIsEnteredBetaOnWeb;
    }

    @NonNull
    public String getFirstName() {
        return this.mFirstName;
    }

    public String getGender() {
        return this.mGender;
    }

    @NonNull
    public String getLastName() {
        return this.mLastName;
    }

    @NonNull
    public List<MetaThreadEnableState> getMetaThreadStates() {
        return this.mMetaThreadStates;
    }

    @NonNull
    public ParentalMode getParentalMode() {
        return this.mParentalMode;
    }

    public boolean getParentalModeHasChildren() {
        return this.mParentalModeHasChildren;
    }

    @Nullable
    public String getPhone() {
        return this.mPhone;
    }

    public long getRegDate() {
        return this.mRegDate;
    }

    public String getTheme() {
        return this.mTheme;
    }

    public String getVkcId() {
        return this.mVkcId;
    }

    public boolean hasAnyPhone() {
        return this.mHasAnyPhone;
    }

    @Deprecated(message = "Will be replaced with getMetaThreadStates in future")
    public boolean isMetaThreadsEnabled() {
        return this.mIsMetaThreadsEnabled;
    }

    public boolean isPhoneVerified() {
        return this.mIsPhoneVerified;
    }

    public void setEnteredBetaOnWeb(boolean z10) {
        this.mIsEnteredBetaOnWeb = z10;
    }

    public GetUserDataResult withAnyPhoneAvailable(boolean z10) {
        this.mHasAnyPhone = z10;
        return this;
    }

    public GetUserDataResult withAvatar(@Nullable String str) {
        this.mAvatarUrl = str;
        return this;
    }

    public GetUserDataResult withB2BFlags(@Nullable String str) {
        this.mB2BFlags = str;
        return this;
    }

    public GetUserDataResult withBillingBitmask(long j10) {
        this.mBillingBitmask = j10;
        return this;
    }

    public GetUserDataResult withBirthday(long j10) {
        this.mBirthday = j10;
        return this;
    }

    public GetUserDataResult withCityId(@Nullable String str) {
        this.mCityId = str;
        return this;
    }

    public GetUserDataResult withDisablingParentalControl(@NonNull DisablingParentalMode disablingParentalMode) {
        this.mDisablingParentalMode = disablingParentalMode;
        return this;
    }

    public GetUserDataResult withEnteredBetaOnWeb(boolean z10) {
        this.mIsEnteredBetaOnWeb = z10;
        return this;
    }

    public GetUserDataResult withFirstName(@NonNull String str) {
        this.mFirstName = str;
        return this;
    }

    public GetUserDataResult withGender(String str) {
        this.mGender = str;
        return this;
    }

    public GetUserDataResult withLastName(@NonNull String str) {
        this.mLastName = str;
        return this;
    }

    public GetUserDataResult withMetaThreadStates(@NonNull List<MetaThreadEnableState> list) {
        this.mMetaThreadStates = list;
        return this;
    }

    @Deprecated(message = "Will be replaced with withMetaThreadStates in future")
    public GetUserDataResult withMetaThreadsEnabled(boolean z10) {
        this.mIsMetaThreadsEnabled = z10;
        return this;
    }

    public GetUserDataResult withParentalMode(@NonNull ParentalMode parentalMode) {
        this.mParentalMode = parentalMode;
        return this;
    }

    public GetUserDataResult withParentalModeHasChildren(boolean z10) {
        this.mParentalModeHasChildren = z10;
        return this;
    }

    public GetUserDataResult withPhone(@Nullable String str) {
        this.mPhone = str;
        return this;
    }

    public GetUserDataResult withPhoneVerifiedEnabled(boolean z10) {
        this.mIsPhoneVerified = z10;
        return this;
    }

    public GetUserDataResult withRegDate(long j10) {
        this.mRegDate = j10;
        return this;
    }

    public GetUserDataResult withTheme(String str) {
        this.mTheme = str;
        return this;
    }

    public GetUserDataResult withVkcId(String str) {
        this.mVkcId = str;
        return this;
    }
}
