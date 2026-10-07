package ru.mail.config.section;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@SerialName("new_auth_sdk_config")
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0003\u001f !B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lru/mail/config/section/NewAuthSdkConfigDto;", "", "ludwigCaptcha", "Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;", "<init>", "(Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLudwigCaptcha$annotations", "()V", "getLudwigCaptcha", "()Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$mail_app_core_release", "LudwigCaptchaDto", "$serializer", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final /* data */ class NewAuthSdkConfigDto {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private final LudwigCaptchaDto ludwigCaptcha;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/config/section/NewAuthSdkConfigDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/config/section/NewAuthSdkConfigDto;", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<NewAuthSdkConfigDto> serializer() {
            return NewAuthSdkConfigDto$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B#\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\u0002\u0010\r¨\u0006\u001f"}, d2 = {"Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;", "", "isLudwigCaptchaEnabled", "", "<init>", "(Z)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "isLudwigCaptchaEnabled$annotations", "()V", "()Z", "component1", "copy", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$mail_app_core_release", "$serializer", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LudwigCaptchaDto {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        private final boolean isLudwigCaptchaEnabled;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/config/section/NewAuthSdkConfigDto$LudwigCaptchaDto;", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<LudwigCaptchaDto> serializer() {
                return NewAuthSdkConfigDto$LudwigCaptchaDto$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public LudwigCaptchaDto() {
            this(false, 1, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ LudwigCaptchaDto copy$default(LudwigCaptchaDto ludwigCaptchaDto, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = ludwigCaptchaDto.isLudwigCaptchaEnabled;
            }
            return ludwigCaptchaDto.copy(z10);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$mail_app_core_release(LudwigCaptchaDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.isLudwigCaptchaEnabled) {
                output.encodeBooleanElement(serialDesc, 0, self.isLudwigCaptchaEnabled);
            }
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsLudwigCaptchaEnabled() {
            return this.isLudwigCaptchaEnabled;
        }

        @NotNull
        public final LudwigCaptchaDto copy(boolean isLudwigCaptchaEnabled) {
            return new LudwigCaptchaDto(isLudwigCaptchaEnabled);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LudwigCaptchaDto) && this.isLudwigCaptchaEnabled == ((LudwigCaptchaDto) other).isLudwigCaptchaEnabled;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isLudwigCaptchaEnabled);
        }

        public final boolean isLudwigCaptchaEnabled() {
            return this.isLudwigCaptchaEnabled;
        }

        @NotNull
        public String toString() {
            return "LudwigCaptchaDto(isLudwigCaptchaEnabled=" + this.isLudwigCaptchaEnabled + ")";
        }

        public /* synthetic */ LudwigCaptchaDto(int i10, boolean z10, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i10 & 1) == 0) {
                this.isLudwigCaptchaEnabled = false;
            } else {
                this.isLudwigCaptchaEnabled = z10;
            }
        }

        public LudwigCaptchaDto(boolean z10) {
            this.isLudwigCaptchaEnabled = z10;
        }

        public /* synthetic */ LudwigCaptchaDto(boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10);
        }

        @SerialName("ludwig_captcha_enabled")
        public static /* synthetic */ void isLudwigCaptchaEnabled$annotations() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NewAuthSdkConfigDto() {
        this((LudwigCaptchaDto) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public static /* synthetic */ NewAuthSdkConfigDto copy$default(NewAuthSdkConfigDto newAuthSdkConfigDto, LudwigCaptchaDto ludwigCaptchaDto, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            ludwigCaptchaDto = newAuthSdkConfigDto.ludwigCaptcha;
        }
        return newAuthSdkConfigDto.copy(ludwigCaptchaDto);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$mail_app_core_release(NewAuthSdkConfigDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z10 = false;
        if (!output.shouldEncodeElementDefault(serialDesc, 0) && Intrinsics.areEqual(self.ludwigCaptcha, new LudwigCaptchaDto(z10, 1, (DefaultConstructorMarker) null))) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 0, NewAuthSdkConfigDto$LudwigCaptchaDto$$serializer.INSTANCE, self.ludwigCaptcha);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final LudwigCaptchaDto getLudwigCaptcha() {
        return this.ludwigCaptcha;
    }

    @NotNull
    public final NewAuthSdkConfigDto copy(@NotNull LudwigCaptchaDto ludwigCaptcha) {
        Intrinsics.checkNotNullParameter(ludwigCaptcha, "ludwigCaptcha");
        return new NewAuthSdkConfigDto(ludwigCaptcha);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof NewAuthSdkConfigDto) && Intrinsics.areEqual(this.ludwigCaptcha, ((NewAuthSdkConfigDto) other).ludwigCaptcha);
    }

    @NotNull
    public final LudwigCaptchaDto getLudwigCaptcha() {
        return this.ludwigCaptcha;
    }

    public int hashCode() {
        return this.ludwigCaptcha.hashCode();
    }

    @NotNull
    public String toString() {
        return "NewAuthSdkConfigDto(ludwigCaptcha=" + this.ludwigCaptcha + ")";
    }

    public /* synthetic */ NewAuthSdkConfigDto(int i10, LudwigCaptchaDto ludwigCaptchaDto, SerializationConstructorMarker serializationConstructorMarker) {
        int i11 = 1;
        if ((i10 & 1) == 0) {
            this.ludwigCaptcha = new LudwigCaptchaDto(false, i11, (DefaultConstructorMarker) null);
        } else {
            this.ludwigCaptcha = ludwigCaptchaDto;
        }
    }

    public NewAuthSdkConfigDto(@NotNull LudwigCaptchaDto ludwigCaptcha) {
        Intrinsics.checkNotNullParameter(ludwigCaptcha, "ludwigCaptcha");
        this.ludwigCaptcha = ludwigCaptcha;
    }

    public /* synthetic */ NewAuthSdkConfigDto(LudwigCaptchaDto ludwigCaptchaDto, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? new LudwigCaptchaDto(false, 1, (DefaultConstructorMarker) null) : ludwigCaptchaDto);
    }

    @SerialName("ludwig_captcha")
    public static /* synthetic */ void getLudwigCaptcha$annotations() {
    }
}
