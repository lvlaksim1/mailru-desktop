package com.vk.superapp.api.generated.multiaccount;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.vk.api.generated.base.dto.BaseOkResponseDto;
import com.vk.api.generated.core.ApiMethodCall;
import com.vk.api.generated.core.ApiResponseParser;
import com.vk.api.generated.core.ApiStreamResponseParser;
import com.vk.api.generated.core.RootResponseDto;
import com.vk.api.generated.multiaccount.dto.MultiaccountCheckRelatedUserPinCodeResponseDto;
import com.vk.api.generated.multiaccount.dto.MultiaccountGetRelatedUserUrlsResponseDto;
import com.vk.api.generated.multiaccount.dto.MultiaccountSetOnboardingTypeDto;
import com.vk.api.generated.multiaccount.dto.MultiaccountSetRelatedUserPinCodeResponseDto;
import com.vk.superapp.api.generated.GsonHolder;
import com.vk.superapp.api.generated.InternalApiMethodCall;
import com.vk.superapp.api.generated.SingleRootResponseDto;
import d.detarenegipakvmoca;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.ui.fragments.mailbox.editmode.PromoAvailableDelegate;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u0016J=\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0002\u0010\u0011J2\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0016"}, d2 = {"Lcom/vk/superapp/api/generated/multiaccount/MultiaccountService;", "", "multiaccountCheckRelatedUserPinCode", "Lcom/vk/api/generated/core/ApiMethodCall;", "Lcom/vk/api/generated/multiaccount/dto/MultiaccountCheckRelatedUserPinCodeResponseDto;", PromoAvailableDelegate.PIN, "", "multiaccountGetRelatedUserUrls", "Lcom/vk/api/generated/multiaccount/dto/MultiaccountGetRelatedUserUrlsResponseDto;", "multiaccountSetOnboarding", "Lcom/vk/api/generated/base/dto/BaseOkResponseDto;", "accessTokens", "", "type", "Lcom/vk/api/generated/multiaccount/dto/MultiaccountSetOnboardingTypeDto;", "status", "", "(Ljava/util/List;Lcom/vk/api/generated/multiaccount/dto/MultiaccountSetOnboardingTypeDto;Ljava/lang/Boolean;)Lcom/vk/api/generated/core/ApiMethodCall;", "multiaccountSetRelatedUserPinCode", "Lcom/vk/api/generated/multiaccount/dto/MultiaccountSetRelatedUserPinCodeResponseDto;", EventParams.HASH, "serviceName", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MultiaccountService {
    @NotNull
    ApiMethodCall<MultiaccountCheckRelatedUserPinCodeResponseDto> multiaccountCheckRelatedUserPinCode(@Nullable String pin);

    @NotNull
    ApiMethodCall<MultiaccountGetRelatedUserUrlsResponseDto> multiaccountGetRelatedUserUrls();

    @NotNull
    ApiMethodCall<BaseOkResponseDto> multiaccountSetOnboarding(@Nullable List<String> accessTokens, @Nullable MultiaccountSetOnboardingTypeDto type, @Nullable Boolean status);

    @NotNull
    ApiMethodCall<MultiaccountSetRelatedUserPinCodeResponseDto> multiaccountSetRelatedUserPinCode(@Nullable String pin, @Nullable String hash, @Nullable String serviceName);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMultiaccountService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MultiaccountService.kt\ncom/vk/superapp/api/generated/multiaccount/MultiaccountService$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 GsonExt.kt\ncom/vk/superapp/api/generated/GsonExtKt\n*L\n1#1,114:1\n1#2:115\n45#3,2:116\n49#3,2:118\n45#3,2:120\n49#3,2:122\n45#3,2:124\n49#3,2:126\n45#3,2:128\n49#3,2:130\n*S KotlinDebug\n*F\n+ 1 MultiaccountService.kt\ncom/vk/superapp/api/generated/multiaccount/MultiaccountService$DefaultImpls\n*L\n57#1:116,2\n58#1:118,2\n69#1:120,2\n70#1:122,2\n84#1:124,2\n85#1:126,2\n104#1:128,2\n105#1:130,2\n*E\n"})
    public static final class DefaultImpls {
        /* JADX INFO: Access modifiers changed from: private */
        public static MultiaccountCheckRelatedUserPinCodeResponseDto detarenegipakvmoca(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (MultiaccountCheckRelatedUserPinCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, MultiaccountCheckRelatedUserPinCodeResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static MultiaccountGetRelatedUserUrlsResponseDto detarenegipakvmocb(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (MultiaccountGetRelatedUserUrlsResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, MultiaccountGetRelatedUserUrlsResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static BaseOkResponseDto detarenegipakvmocc(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (BaseOkResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, BaseOkResponseDto.class).getType())).getResponse();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static MultiaccountSetRelatedUserPinCodeResponseDto detarenegipakvmocd(JsonReader it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (MultiaccountSetRelatedUserPinCodeResponseDto) ((SingleRootResponseDto) GsonHolder.INSTANCE.getGson().fromJson(it, TypeToken.getParameterized(SingleRootResponseDto.class, MultiaccountSetRelatedUserPinCodeResponseDto.class).getType())).getResponse();
        }

        @NotNull
        public static ApiMethodCall<MultiaccountCheckRelatedUserPinCodeResponseDto> multiaccountCheckRelatedUserPinCode(@NotNull MultiaccountService multiaccountService, @Nullable String str) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("multiaccount.checkRelatedUserPinCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.c
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmoca(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.d
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmoca(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PromoAvailableDelegate.PIN, str, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall multiaccountCheckRelatedUserPinCode$default(MultiaccountService multiaccountService, String str, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: multiaccountCheckRelatedUserPinCode");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            return multiaccountService.multiaccountCheckRelatedUserPinCode(str);
        }

        @NotNull
        public static ApiMethodCall<MultiaccountGetRelatedUserUrlsResponseDto> multiaccountGetRelatedUserUrls(@NotNull MultiaccountService multiaccountService) {
            return new InternalApiMethodCall("multiaccount.getRelatedUserUrls", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.a
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocb(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.b
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocb(inputStream);
                }
            });
        }

        @NotNull
        public static ApiMethodCall<BaseOkResponseDto> multiaccountSetOnboarding(@NotNull MultiaccountService multiaccountService, @Nullable List<String> list, @Nullable MultiaccountSetOnboardingTypeDto multiaccountSetOnboardingTypeDto, @Nullable Boolean bool) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("multiaccount.setOnboarding", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.e
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocc(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.f
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocc(inputStream);
                }
            });
            if (list != null) {
                internalApiMethodCall.addParam("access_tokens", list);
            }
            if (multiaccountSetOnboardingTypeDto != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "type", multiaccountSetOnboardingTypeDto.getValue(), 0, 0, 12, (Object) null);
            }
            if (bool != null) {
                internalApiMethodCall.addParam("status", bool.booleanValue());
            }
            return internalApiMethodCall;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiMethodCall multiaccountSetOnboarding$default(MultiaccountService multiaccountService, List list, MultiaccountSetOnboardingTypeDto multiaccountSetOnboardingTypeDto, Boolean bool, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: multiaccountSetOnboarding");
            }
            if ((i10 & 1) != 0) {
                list = null;
            }
            if ((i10 & 2) != 0) {
                multiaccountSetOnboardingTypeDto = null;
            }
            if ((i10 & 4) != 0) {
                bool = null;
            }
            return multiaccountService.multiaccountSetOnboarding(list, multiaccountSetOnboardingTypeDto, bool);
        }

        @NotNull
        public static ApiMethodCall<MultiaccountSetRelatedUserPinCodeResponseDto> multiaccountSetRelatedUserPinCode(@NotNull MultiaccountService multiaccountService, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            InternalApiMethodCall internalApiMethodCall = new InternalApiMethodCall("multiaccount.setRelatedUserPinCode", new ApiResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.g
                @Override // com.vk.api.generated.core.ApiResponseParser
                public final Object parseResponse(JsonReader jsonReader) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocd(jsonReader);
                }
            }, new ApiStreamResponseParser() { // from class: com.vk.superapp.api.generated.multiaccount.h
                @Override // com.vk.api.generated.core.ApiStreamResponseParser
                public final RootResponseDto parseResponse(InputStream inputStream) {
                    return MultiaccountService.DefaultImpls.detarenegipakvmocd(inputStream);
                }
            });
            if (str != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, PromoAvailableDelegate.PIN, str, 0, 0, 12, (Object) null);
            }
            if (str2 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, EventParams.HASH, str2, 0, 0, 12, (Object) null);
            }
            if (str3 != null) {
                InternalApiMethodCall.addParam$default(internalApiMethodCall, "service_name", str3, 0, 0, 12, (Object) null);
            }
            return internalApiMethodCall;
        }

        public static /* synthetic */ ApiMethodCall multiaccountSetRelatedUserPinCode$default(MultiaccountService multiaccountService, String str, String str2, String str3, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: multiaccountSetRelatedUserPinCode");
            }
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                str3 = null;
            }
            return multiaccountService.multiaccountSetRelatedUserPinCode(str, str2, str3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmoca(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, MultiaccountCheckRelatedUserPinCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocb(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, MultiaccountGetRelatedUserUrlsResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocc(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, BaseOkResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static RootResponseDto detarenegipakvmocd(InputStream it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return (RootResponseDto) detarenegipakvmoca.detarenegipakvmoca(TypeToken.getParameterized(RootResponseDto.class, MultiaccountSetRelatedUserPinCodeResponseDto.class), GsonHolder.INSTANCE.getGson(), new InputStreamReader(it), "fromJson(...)");
        }
    }
}
