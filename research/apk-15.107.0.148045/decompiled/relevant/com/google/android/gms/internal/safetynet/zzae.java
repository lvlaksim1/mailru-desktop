package com.google.android.gms.internal.safetynet;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes21.dex */
public final class zzae implements SafetyNetApi {
    private static final String zza = "zzae";

    public static PendingResult<SafetyNetApi.AttestationResult> zza(GoogleApiClient googleApiClient, byte[] bArr, @Nullable String str) {
        return googleApiClient.enqueue(new zzi(googleApiClient, bArr, str));
    }

    public static PendingResult<SafetyNetApi.SafeBrowsingResult> zzb(GoogleApiClient googleApiClient, String str, int i10, String str2, int... iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
        return googleApiClient.enqueue(new zzk(googleApiClient, iArr, i10, str, str2));
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.AttestationResult> attest(GoogleApiClient googleApiClient, byte[] bArr) {
        return zza(googleApiClient, bArr, null);
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.VerifyAppsUserResult> enableVerifyApps(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzm(this, googleApiClient));
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final boolean isVerifyAppsEnabled(Context context) {
        Task<SafetyNetApi.VerifyAppsUserResponse> taskIsVerifyAppsEnabled = SafetyNet.getClient(context).isVerifyAppsEnabled();
        try {
            Tasks.await(taskIsVerifyAppsEnabled, 15000L, TimeUnit.MILLISECONDS);
            return taskIsVerifyAppsEnabled.getResult().isVerifyAppsEnabled();
        } catch (InterruptedException | ExecutionException | TimeoutException unused) {
            return false;
        }
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.HarmfulAppsResult> listHarmfulApps(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzn(this, googleApiClient));
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, String str, String str2, int... iArr) {
        return zzb(googleApiClient, str, 1, str2, iArr);
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.RecaptchaTokenResult> verifyWithRecaptcha(GoogleApiClient googleApiClient, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Null or empty site key in verifyWithRecaptcha");
        }
        return googleApiClient.enqueue(new zzo(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.SafeBrowsingResult> lookupUri(GoogleApiClient googleApiClient, List<Integer> list, String str) {
        if (list == null) {
            throw new IllegalArgumentException("Null threatTypes in lookupUri");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Null or empty uri in lookupUri");
        }
        return googleApiClient.enqueue(new zzj(this, googleApiClient, list, str, null));
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi
    public final PendingResult<SafetyNetApi.VerifyAppsUserResult> isVerifyAppsEnabled(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzl(this, googleApiClient));
    }
}
