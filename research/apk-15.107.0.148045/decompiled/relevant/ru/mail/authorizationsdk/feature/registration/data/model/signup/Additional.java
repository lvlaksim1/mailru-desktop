package ru.mail.authorizationsdk.feature.registration.data.model.signup;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J&\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\tHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0014\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006("}, d2 = {"Lru/mail/authorizationsdk/feature/registration/data/model/signup/Additional;", "", "captcha", "Lru/mail/authorizationsdk/feature/registration/data/model/signup/Captcha;", "tokenChecked", "", "<init>", "(Lru/mail/authorizationsdk/feature/registration/data/model/signup/Captcha;Ljava/lang/Boolean;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILru/mail/authorizationsdk/feature/registration/data/model/signup/Captcha;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCaptcha$annotations", "()V", "getCaptcha", "()Lru/mail/authorizationsdk/feature/registration/data/model/signup/Captcha;", "getTokenChecked$annotations", "getTokenChecked", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Lru/mail/authorizationsdk/feature/registration/data/model/signup/Captcha;Ljava/lang/Boolean;)Lru/mail/authorizationsdk/feature/registration/data/model/signup/Additional;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$authorizationsdk_release", "$serializer", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final /* data */ class Additional {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private final Captcha captcha;

    @Nullable
    private final Boolean tokenChecked;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/data/model/signup/Additional$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/authorizationsdk/feature/registration/data/model/signup/Additional;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<Additional> serializer() {
            return Additional$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Additional() {
        this((Captcha) null, (Boolean) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public static /* synthetic */ Additional copy$default(Additional additional, Captcha captcha, Boolean bool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            captcha = additional.captcha;
        }
        if ((i10 & 2) != 0) {
            bool = additional.tokenChecked;
        }
        return additional.copy(captcha, bool);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$authorizationsdk_release(Additional self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.captcha != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, Captcha$$serializer.INSTANCE, self.captcha);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.tokenChecked == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 1, BooleanSerializer.INSTANCE, self.tokenChecked);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Captcha getCaptcha() {
        return this.captcha;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getTokenChecked() {
        return this.tokenChecked;
    }

    @NotNull
    public final Additional copy(@Nullable Captcha captcha, @Nullable Boolean tokenChecked) {
        return new Additional(captcha, tokenChecked);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Additional)) {
            return false;
        }
        Additional additional = (Additional) other;
        return Intrinsics.areEqual(this.captcha, additional.captcha) && Intrinsics.areEqual(this.tokenChecked, additional.tokenChecked);
    }

    @Nullable
    public final Captcha getCaptcha() {
        return this.captcha;
    }

    @Nullable
    public final Boolean getTokenChecked() {
        return this.tokenChecked;
    }

    public int hashCode() {
        Captcha captcha = this.captcha;
        int iHashCode = (captcha == null ? 0 : captcha.hashCode()) * 31;
        Boolean bool = this.tokenChecked;
        return iHashCode + (bool != null ? bool.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Additional(captcha=" + this.captcha + ", tokenChecked=" + this.tokenChecked + ")";
    }

    public /* synthetic */ Additional(int i10, Captcha captcha, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i10 & 1) == 0) {
            this.captcha = null;
        } else {
            this.captcha = captcha;
        }
        if ((i10 & 2) == 0) {
            this.tokenChecked = null;
        } else {
            this.tokenChecked = bool;
        }
    }

    public Additional(@Nullable Captcha captcha, @Nullable Boolean bool) {
        this.captcha = captcha;
        this.tokenChecked = bool;
    }

    public /* synthetic */ Additional(Captcha captcha, Boolean bool, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : captcha, (i10 & 2) != 0 ? null : bool);
    }

    @SerialName("captcha")
    public static /* synthetic */ void getCaptcha$annotations() {
    }

    @SerialName("token_checked")
    public static /* synthetic */ void getTokenChecked$annotations() {
    }
}
