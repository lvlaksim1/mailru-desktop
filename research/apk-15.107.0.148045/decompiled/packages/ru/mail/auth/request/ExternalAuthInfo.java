package ru.mail.auth.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/auth/request/ExternalAuthInfo;", "", "externalAuthJson", "", "json", "Lkotlinx/serialization/json/Json;", "<init>", "(Ljava/lang/String;Lkotlinx/serialization/json/Json;)V", "externalAuth", "Lru/mail/auth/request/ExternalAuth;", "redirect", "getRedirect", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExternalAuthInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExternalAuthInfo.kt\nru/mail/auth/request/ExternalAuthInfo\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,32:1\n222#2:33\n*S KotlinDebug\n*F\n+ 1 ExternalAuthInfo.kt\nru/mail/auth/request/ExternalAuthInfo\n*L\n20#1:33\n*E\n"})
public final class ExternalAuthInfo {

    @Nullable
    private final ExternalAuth externalAuth;

    @Nullable
    private final String externalAuthJson;

    @NotNull
    private final Json json;

    @NotNull
    private final String redirect;

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public ExternalAuthInfo(@Nullable String str) {
        this(str, null, 2, 0 == true ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    @NotNull
    public final String getRedirect() {
        return this.redirect;
    }

    @JvmOverloads
    public ExternalAuthInfo(@Nullable String str, @NotNull Json json) {
        String redirectUrl;
        Intrinsics.checkNotNullParameter(json, "json");
        this.externalAuthJson = str;
        this.json = json;
        ExternalAuth externalAuth = null;
        if (str != null) {
            try {
                if (!StringsKt.isBlank(str)) {
                    json.getSerializersModule();
                    externalAuth = (ExternalAuth) json.decodeFromString(ExternalAuth.INSTANCE.serializer(), str);
                }
            } catch (SerializationException unused) {
            }
        }
        this.externalAuth = externalAuth;
        this.redirect = (externalAuth == null || (redirectUrl = externalAuth.getRedirectUrl()) == null) ? "" : redirectUrl;
    }

    public /* synthetic */ ExternalAuthInfo(String str, Json json, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? JsonKt.Json$default(null, new Function1() { // from class: ru.mail.auth.request.c
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExternalAuthInfo._init_$lambda$0((JsonBuilder) obj);
            }
        }, 1, null) : json);
    }
}
