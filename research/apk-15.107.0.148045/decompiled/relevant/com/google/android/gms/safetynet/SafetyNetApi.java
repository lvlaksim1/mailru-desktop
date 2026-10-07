package com.google.android.gms.safetynet;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes21.dex */
@KeepForSdkWithMembers
public interface SafetyNetApi {

    /* JADX INFO: compiled from: ProGuard */
    public static class AttestationResponse extends Response<AttestationResult> {
        @Nullable
        public String getJwsResult() {
            return getResult().getJwsResult();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Deprecated
    public interface AttestationResult extends Result {
        @Nullable
        String getJwsResult();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class HarmfulAppsResponse extends Response<HarmfulAppsResult> {
        @NonNull
        public List<HarmfulAppsData> getHarmfulAppsList() {
            return getResult().getHarmfulAppsList();
        }

        public int getHoursSinceLastScanWithHarmfulApp() {
            return getResult().getHoursSinceLastScanWithHarmfulApp();
        }

        public long getLastScanTimeMs() {
            return getResult().getLastScanTimeMs();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Deprecated
    public interface HarmfulAppsResult extends Result {
        @NonNull
        List<HarmfulAppsData> getHarmfulAppsList();

        int getHoursSinceLastScanWithHarmfulApp();

        long getLastScanTimeMs();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class RecaptchaTokenResponse extends Response<RecaptchaTokenResult> {
        @Nullable
        public String getTokenResult() {
            return getResult().getTokenResult();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Deprecated
    public interface RecaptchaTokenResult extends Result {
        @Nullable
        String getTokenResult();
    }

    /* JADX INFO: compiled from: ProGuard */
    @KeepForSdkWithMembers
    public static class SafeBrowsingResponse extends Response<SafeBrowsingResult> {
        @NonNull
        public List<SafeBrowsingThreat> getDetectedThreats() {
            return getResult().getDetectedThreats();
        }

        @ShowFirstParty
        public long getLastUpdateTimeMs() {
            return getResult().getLastUpdateTimeMs();
        }

        @Nullable
        @ShowFirstParty
        public String getMetadata() {
            return getResult().getMetadata();
        }

        @Nullable
        @ShowFirstParty
        public byte[] getState() {
            return getResult().getState();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @KeepForSdkWithMembers
    @Deprecated
    public interface SafeBrowsingResult extends Result {
        @NonNull
        List<SafeBrowsingThreat> getDetectedThreats();

        long getLastUpdateTimeMs();

        @Nullable
        String getMetadata();

        @Nullable
        byte[] getState();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class VerifyAppsUserResponse extends Response<VerifyAppsUserResult> {
        public boolean isVerifyAppsEnabled() {
            return getResult().isVerifyAppsEnabled();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Deprecated
    public interface VerifyAppsUserResult extends Result {
        boolean isVerifyAppsEnabled();
    }

    @NonNull
    @Deprecated
    PendingResult<AttestationResult> attest(@NonNull GoogleApiClient googleApiClient, @NonNull byte[] bArr);

    @NonNull
    @Deprecated
    PendingResult<VerifyAppsUserResult> enableVerifyApps(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @Deprecated
    PendingResult<VerifyAppsUserResult> isVerifyAppsEnabled(@NonNull GoogleApiClient googleApiClient);

    @Deprecated
    boolean isVerifyAppsEnabled(@NonNull Context context);

    @NonNull
    @Deprecated
    PendingResult<HarmfulAppsResult> listHarmfulApps(@NonNull GoogleApiClient googleApiClient);

    @NonNull
    @Deprecated
    PendingResult<SafeBrowsingResult> lookupUri(@NonNull GoogleApiClient googleApiClient, @NonNull String str, @NonNull String str2, @NonNull int... iArr);

    @NonNull
    PendingResult<SafeBrowsingResult> lookupUri(@NonNull GoogleApiClient googleApiClient, @NonNull List<Integer> list, @NonNull String str);

    @NonNull
    @Deprecated
    PendingResult<RecaptchaTokenResult> verifyWithRecaptcha(@NonNull GoogleApiClient googleApiClient, @NonNull String str);
}
