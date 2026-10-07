package com.google.android.gms.internal.safetynet;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes21.dex */
abstract class zzx extends zze<SafetyNetApi.RecaptchaTokenResult> {
    protected final zzg zzb;

    public zzx(GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzb = new zzw(this);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected final /* bridge */ /* synthetic */ Result createFailedResult(Status status) {
        return new zzab(status, null);
    }
}
