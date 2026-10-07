package com.google.android.gms.internal.safetynet;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes21.dex */
final class zzab implements SafetyNetApi.RecaptchaTokenResult {
    private final Status zza;

    @Nullable
    private final com.google.android.gms.safetynet.zzf zzb;

    public zzab(Status status, @Nullable com.google.android.gms.safetynet.zzf zzfVar) {
        this.zza = status;
        this.zzb = zzfVar;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.RecaptchaTokenResult
    @Nullable
    public final String getTokenResult() {
        com.google.android.gms.safetynet.zzf zzfVar = this.zzb;
        if (zzfVar == null) {
            return null;
        }
        return zzfVar.zza();
    }
}
