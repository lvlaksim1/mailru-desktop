# Регистрация PushMe: оригинальный APK / статический разбор DEX

APK SHA-256: `42c976a0c2f3d5fb186a88bc855f782c81c8ce35f952fcf22f99c64828880af6`
Данный отчёт полностью построен из локального DEX без сетевых вызовов.

Classes: 11
## Class Lcom/vk/pushme/network/PushMeApiImpl; @ Mail-15.107.0.148045.apk/classes12.dex

### mapSubscriptions (Ljava/util/Collection;)Ljava/util/Collection;
~~~
00000 move-object/from16 v0, v19
00004 check-cast v0, Ljava/lang/Iterable;
00008 new-instance v1, Ljava/util/ArrayList;
0000c const/16 v2, 10
00010 invoke-static v0, v2, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00016 move-result v3
00018 invoke-direct v1, v3, Ljava/util/ArrayList;-><init>(I)V
0001e invoke-interface v0, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00024 move-result-object v0
00026 invoke-interface v0, Ljava/util/Iterator;->hasNext()Z
0002c move-result v3
0002e if-eqz v3, +0deh
00032 invoke-interface v0, Ljava/util/Iterator;->next()Ljava/lang/Object;
00038 move-result-object v3
0003a check-cast v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
0003e new-instance v4, Lkotlin/jvm/internal/Ref$ObjectRef;
00042 invoke-direct v4, Lkotlin/jvm/internal/Ref$ObjectRef;-><init>()V
00048 new-instance v5, Lkotlinx/serialization/json/JsonObjectBuilder;
0004c invoke-direct v5, Lkotlinx/serialization/json/JsonObjectBuilder;-><init>()V
00052 new-instance v6, Lkotlinx/serialization/json/JsonObjectBuilder;
00056 invoke-direct v6, Lkotlinx/serialization/json/JsonObjectBuilder;-><init>()V
0005c invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getSettings()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;
00062 move-result-object v7
00064 invoke-virtual v7, Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;->getTags()Ljava/util/Set;
0006a move-result-object v7
0006c check-cast v7, Ljava/lang/Iterable;
00070 new-instance v8, Ljava/util/ArrayList;
00074 invoke-static v7, v2, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
0007a move-result v9
0007c invoke-direct v8, v9, Ljava/util/ArrayList;-><init>(I)V
00082 invoke-interface v7, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00088 move-result-object v7
0008a invoke-interface v7, Ljava/util/Iterator;->hasNext()Z
00090 move-result v9
00092 if-eqz v9, +018h
00096 invoke-interface v7, Ljava/util/Iterator;->next()Ljava/lang/Object;
0009c move-result-object v9
0009e check-cast v9, Ljava/lang/Number;
000a2 invoke-virtual v9, Ljava/lang/Number;->intValue()I
000a8 move-result v9
000aa invoke-static v9, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
000b0 move-result-object v9
000b2 invoke-static v9, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonElement(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;
000b8 move-result-object v9
000ba invoke-interface v8, v9, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
000c0 goto -1bh
000c2 new-instance v7, Lkotlinx/serialization/json/JsonArray;
000c6 invoke-direct v7, v8, Lkotlinx/serialization/json/JsonArray;-><init>(Ljava/util/List;)V
000cc invoke-interface v7, Ljava/util/Collection;->isEmpty()Z
000d2 move-result v8
000d4 if-nez v8, +008h
000d8 const-string/jumbo v8, tags
000de invoke-virtual v6, v8, v7, Lkotlinx/serialization/json/JsonObjectBuilder;->put(Ljava/lang/String; Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;
000e4 invoke-virtual v6, Lkotlinx/serialization/json/JsonObjectBuilder;->build()Lkotlinx/serialization/json/JsonObject;
000ea move-result-object v6
000ec const-string v7, "capabilities"
000f0 invoke-virtual v5, v7, v6, Lkotlinx/serialization/json/JsonObjectBuilder;->put(Ljava/lang/String; Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;
000f6 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getClient()Ljava/util/Map;
000fc move-result-object v6
000fe invoke-static v6, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonObject(Ljava/util/Map;)Lkotlinx/serialization/json/JsonObject;
00104 move-result-object v6
00106 const-string v7, "client"
0010a invoke-virtual v5, v7, v6, Lkotlinx/serialization/json/JsonObjectBuilder;->put(Ljava/lang/String; Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;
00110 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getDeviceId()Ljava/lang/String;
00116 move-result-object v6
00118 invoke-static v6, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonElement(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;
0011e move-result-object v6
00120 const-string v7, "device_id"
00124 invoke-virtual v5, v7, v6, Lkotlinx/serialization/json/JsonObjectBuilder;->put(Ljava/lang/String; Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;
0012a invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getTimeZone()Ljava/lang/String;
00130 move-result-object v6
00132 invoke-static v6, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonElement(Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;
00138 move-result-object v6
0013a const-string v7, "client_time_zone"
0013e invoke-virtual v5, v7, v6, Lkotlinx/serialization/json/JsonObjectBuilder;->put(Ljava/lang/String; Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonElement;
00144 invoke-virtual v5, Lkotlinx/serialization/json/JsonObjectBuilder;->build()Lkotlinx/serialization/json/JsonObject;
0014a move-result-object v5
0014c iput-object v5, v4, Lkotlin/jvm/internal/Ref$ObjectRef;->element Ljava/lang/Object;
00150 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getSettings()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;
00156 move-result-object v5
00158 invoke-virtual v5, Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;->getExtraSettings()Lkotlinx/serialization/json/JsonObject;
0015e move-result-object v5
00160 if-eqz v5, +00fh
00164 iget-object v6, v4, Lkotlin/jvm/internal/Ref$ObjectRef;->element Ljava/lang/Object;
00168 check-cast v6, Lkotlinx/serialization/json/JsonObject;
0016c move-object/from16 v7, v18
00170 invoke-direct v7, v6, v5, Lcom/vk/pushme/network/PushMeApiImpl;->combineWith(Lkotlinx/serialization/json/JsonObject; Lkotlinx/serialization/json/JsonObject;)Lkotlinx/serialization/json/JsonObject;
00176 move-result-object v5
00178 iput-object v5, v4, Lkotlin/jvm/internal/Ref$ObjectRef;->element Ljava/lang/Object;
0017c goto +3h
0017e move-object/from16 v7, v18
00182 new-instance v8, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;
00186 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccount()Ljava/lang/String;
0018c move-result-object v9
0018e invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
00194 move-result-object v10
00196 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getTransport()Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;
0019c move-result-object v5
0019e invoke-virtual v5, Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;->getJsonValue()Ljava/lang/String;
001a4 move-result-object v11
001a6 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getPushToken()Ljava/lang/String;
001ac move-result-object v12
001ae invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccessToken()Ljava/lang/String;
001b4 move-result-object v13
001b6 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAndroidId()Ljava/lang/String;
001bc move-result-object v14
001be invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getSdkDeviceId()Ljava/lang/String;
001c4 move-result-object v15
001c6 iget-object v4, v4, Lkotlin/jvm/internal/Ref$ObjectRef;->element Ljava/lang/Object;
001ca move-object/from16 v16, v4
001ce check-cast v16, Lkotlinx/serialization/json/JsonObject;
001d2 invoke-virtual v3, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getStatus()I
001d8 move-result v17
001da invoke-direct/range v8 ... v17, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;-><init>(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Lkotlinx/serialization/json/JsonObject; I)V
001e0 invoke-interface v1, v8, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
001e6 goto/16 -0e0h
001ea move-object/from16 v7, v18
001ee return-object v1
~~~

### setSettingsInternal (Ljava/util/Collection; Lcom/vk/pushme/network/ApiVersion; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
~~~
00000 move-object/from16 v1, v17
00004 move-object/from16 v0, v20
00008 instance-of v2, v0, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
0000c if-eqz v2, +011h
00010 move-object v2, v0
00012 check-cast v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
00016 iget v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0001a const/high16 v4, -2147483648
0001e and-int v5, v3, v4
00022 if-eqz v5, +006h
00026 sub-int/2addr v3, v4
00028 iput v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0002c goto +6h
0002e new-instance v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;
00032 invoke-direct v2, v1, v0, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;-><init>(Lcom/vk/pushme/network/PushMeApiImpl; Lkotlin/coroutines/Continuation;)V
00038 iget-object v0, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->result Ljava/lang/Object;
0003c invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
00042 move-result-object v3
00044 iget v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
00048 const/4 v5, 2
0004a const/4 v6, 1
0004c if-eqz v4, +061h
00050 if-eq v4, v6, +034h
00054 if-ne v4, v5, +02ah
00058 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$7 Ljava/lang/Object;
0005c check-cast v3, Lokhttp3/Response;
00060 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
00064 check-cast v3, Lokhttp3/Call;
00068 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
0006c check-cast v3, Lokhttp3/Request;
00070 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
00074 check-cast v3, Ljava/lang/String;
00078 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
0007c check-cast v3, Ljava/util/Collection;
00080 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
00084 check-cast v3, Lokhttp3/HttpUrl;
00088 iget-object v3, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
0008c check-cast v3, Lcom/vk/pushme/network/ApiVersion;
00090 iget-object v2, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
00094 check-cast v2, Ljava/util/Collection;
00098 invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
0009e goto/16 +11eh
000a2 move-exception v0
000a4 goto/16 +14dh
000a8 new-instance v0, Ljava/lang/IllegalStateException;
000ac const-string v2, "call to 'resume' before 'invoke' with coroutine"
000b0 invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
000b6 throw v0
000b8 iget v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
000bc iget-object v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
000c0 check-cast v6, Lokhttp3/Call;
000c4 iget-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
000c8 check-cast v7, Lokhttp3/Request;
000cc iget-object v8, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
000d0 check-cast v8, Ljava/lang/String;
000d4 iget-object v9, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
000d8 check-cast v9, Ljava/util/Collection;
000dc iget-object v10, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
000e0 check-cast v10, Lokhttp3/HttpUrl;
000e4 iget-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
000e8 check-cast v11, Lcom/vk/pushme/network/ApiVersion;
000ec iget-object v12, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
000f0 check-cast v12, Ljava/util/Collection;
000f4 invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
000fa move-object/from16 v16, v8
000fe move-object v8, v7
00100 move-object v7, v11
00102 move-object v11, v10
00104 move-object v10, v9
00106 move-object/from16 v9, v16
0010a goto/16 +09bh
0010e invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00114 invoke-direct v1, Lcom/vk/pushme/network/PushMeApiImpl;->urlBuilder()Lokhttp3/HttpUrl$Builder;
0011a move-result-object v0
0011c iget-object v4, v1, Lcom/vk/pushme/network/PushMeApiImpl;->hostInfoProvider Lcom/vk/pushme/network/HostInfoProvider;
00120 move-object/from16 v7, v19
00124 invoke-interface v4, v7, Lcom/vk/pushme/network/HostInfoProvider;->getApiPathByVersion(Lcom/vk/pushme/network/ApiVersion;)Ljava/lang/String;
0012a move-result-object v4
0012c invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00132 move-result-object v0
00134 invoke-virtual v7, Lcom/vk/pushme/network/ApiVersion;->getValue()Ljava/lang/String;
0013a move-result-object v4
0013c invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00142 move-result-object v0
00144 const-string/jumbo v4, set_settings
0014a invoke-virtual v0, v4, Lokhttp3/HttpUrl$Builder;->addPathSegment(Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;
00150 move-result-object v0
00152 invoke-virtual v0, Lokhttp3/HttpUrl$Builder;->build()Lokhttp3/HttpUrl;
00158 move-result-object v10
0015a invoke-direct/range v17 ... v18, Lcom/vk/pushme/network/PushMeApiImpl;->mapSubscriptions(Ljava/util/Collection;)Ljava/util/Collection;
00160 move-result-object v9
00162 sget-object v0, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
00166 invoke-virtual v0, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
0016c new-instance v4, Lkotlinx/serialization/internal/ArrayListSerializer;
00170 sget-object v8, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest;->Companion Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest$Companion;
00174 invoke-virtual v8, Lcom/vk/pushme/network/model/request/InternalSubscriptionRequest$Companion;->serializer()Lkotlinx/serialization/KSerializer;
0017a move-result-object v8
0017c invoke-direct v4, v8, Lkotlinx/serialization/internal/ArrayListSerializer;-><init>(Lkotlinx/serialization/KSerializer;)V
00182 invoke-virtual v0, v4, v9, Lkotlinx/serialization/json/Json;->encodeToString(Lkotlinx/serialization/SerializationStrategy; Ljava/lang/Object;)Ljava/lang/String;
00188 move-result-object v8
0018a new-instance v0, Lokhttp3/Request$Builder;
0018e invoke-direct v0, Lokhttp3/Request$Builder;-><init>()V
00194 invoke-virtual v0, v10, Lokhttp3/Request$Builder;->url(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;
0019a move-result-object v0
0019c invoke-static v8, Lcom/vk/pushme/network/util/ExtensionsKt;->toJsonRequestBody(Ljava/lang/String;)Lokhttp3/RequestBody;
001a2 move-result-object v4
001a4 invoke-virtual v0, v4, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;
001aa move-result-object v0
001ac invoke-virtual v0, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;
001b2 move-result-object v0
001b4 iget-object v4, v1, Lcom/vk/pushme/network/PushMeApiImpl;->okHttpClient Lokhttp3/OkHttpClient;
001b8 invoke-virtual v4, v0, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;
001be move-result-object v4
001c0 invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001c6 move-result-object v11
001c8 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
001cc invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001d2 move-result-object v11
001d4 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
001d8 invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001de move-result-object v11
001e0 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
001e4 invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001ea move-result-object v11
001ec iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
001f0 invoke-static v8, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
001f6 move-result-object v11
001f8 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
001fc invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00202 move-result-object v11
00204 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
00208 invoke-static v4, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0020e move-result-object v11
00210 iput-object v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
00214 const/4 v11, 0
00216 iput v11, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
0021a iput v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
0021e invoke-static v4, v2, Lcom/vk/pushme/network/util/CallHandlerKt;->await(Lokhttp3/Call; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00224 move-result-object v6
00226 if-ne v6, v3, +003h
0022a goto +57h
0022c move-object v12, v8
0022e move-object v8, v0
00230 move-object v0, v6
00232 move-object v6, v4
00234 move v4, v11
00236 move-object v11, v10
00238 move-object v10, v9
0023a move-object v9, v12
0023c move-object/from16 v12, v18
00240 check-cast v0, Lokhttp3/Response;
00244 invoke-virtual v0, Lokhttp3/Response;->isSuccessful()Z
0024a move-result v13
0024c if-eqz v13, +061h
00250 invoke-static Lkotlinx/coroutines/Dispatchers;->getIO()Lkotlinx/coroutines/CoroutineDispatcher;
00256 move-result-object v13
00258 new-instance v14, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;
0025c const/4 v15, 0
0025e invoke-direct v14, v0, v15, Lcom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1;-><init>(Lokhttp3/Response; Lkotlin/coroutines/Continuation;)V
00264 invoke-static v12, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0026a move-result-object v12
0026c iput-object v12, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$0 Ljava/lang/Object;
00270 invoke-static v7, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00276 move-result-object v7
00278 iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$1 Ljava/lang/Object;
0027c invoke-static v11, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00282 move-result-object v7
00284 iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$2 Ljava/lang/Object;
00288 invoke-static v10, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0028e move-result-object v7
00290 iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$3 Ljava/lang/Object;
00294 invoke-static v9, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0029a move-result-object v7
0029c iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$4 Ljava/lang/Object;
002a0 invoke-static v8, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002a6 move-result-object v7
002a8 iput-object v7, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$5 Ljava/lang/Object;
002ac invoke-static v6, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002b2 move-result-object v6
002b4 iput-object v6, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$6 Ljava/lang/Object;
002b8 invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
002be move-result-object v0
002c0 iput-object v0, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->L$7 Ljava/lang/Object;
002c4 iput v4, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->I$0 I
002c8 iput v5, v2, Lcom/vk/pushme/network/PushMeApiImpl$setSettingsInternal$1;->label I
002cc invoke-static v13, v14, v2, Lkotlinx/coroutines/BuildersKt;->withContext(Lkotlin/coroutines/CoroutineContext; Lkotlin/jvm/functions/Function2; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
002d2 move-result-object v0
002d4 if-ne v0, v3, +003h
002d8 return-object v3
002da check-cast v0, Ljava/lang/String;
002de sget-object v2, Lkotlinx/serialization/json/Json;->Default Lkotlinx/serialization/json/Json$Default;
002e2 invoke-virtual v2, Lkotlinx/serialization/json/Json;->getSerializersModule()Lkotlinx/serialization/modules/SerializersModule;
002e8 sget-object v3, Lcom/vk/pushme/network/model/response/SubscriptionResponse;->Companion Lcom/vk/pushme/network/model/response/SubscriptionResponse$Companion;
002ec invoke-virtual v3, Lcom/vk/pushme/network/model/response/SubscriptionResponse$Companion;->serializer()Lkotlinx/serialization/KSerializer;
002f2 move-result-object v3
002f4 check-cast v3, Lkotlinx/serialization/DeserializationStrategy;
002f8 invoke-virtual v2, v3, v0, Lkotlinx/serialization/json/Json;->decodeFromString(Lkotlinx/serialization/DeserializationStrategy; Ljava/lang/String;)Ljava/lang/Object;
002fe move-result-object v0
00300 check-cast v0, Lcom/vk/pushme/network/model/response/SubscriptionResponse;
00304 invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
0030a move-result-object v0
0030c goto +23h
0030e new-instance v2, Lcom/vk/pushme/network/PushMeRequestException;
00312 invoke-virtual v0, Lokhttp3/Response;->message()Ljava/lang/String;
00318 move-result-object v3
0031a invoke-virtual v0, Lokhttp3/Response;->code()I
00320 move-result v0
00322 invoke-direct v2, v3, v0, Lcom/vk/pushme/network/PushMeRequestException;-><init>(Ljava/lang/String; I)V
00328 sget-object v0, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
0032c invoke-static v2, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
00332 move-result-object v0
00334 invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
0033a move-result-object v0
0033c goto +bh
0033e sget-object v2, Lkotlin/Result;->Companion Lkotlin/Result$Companion;
00342 invoke-static v0, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;
00348 move-result-object v0
0034a invoke-static v0, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;
00350 move-result-object v0
00352 invoke-static v0, Lkotlin/Result;->isSuccess-impl(Ljava/lang/Object;)Z
00358 move-result v2
0035a if-eqz v2, +00ch
0035e invoke-static v0, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00364 check-cast v0, Lcom/vk/pushme/network/model/response/SubscriptionResponse;
00368 invoke-direct v1, v0, Lcom/vk/pushme/network/PushMeApiImpl;->parseSubscriptionResponseToResult(Lcom/vk/pushme/network/model/response/SubscriptionResponse;)Lcom/vk/pushme/network/model/result/SubscriptionResult;
0036e move-result-object v0
00370 goto +eh
00372 new-instance v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00376 invoke-static v0, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;
0037c move-result-object v0
0037e invoke-static v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
00384 invoke-direct v2, v0, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;-><init>(Ljava/lang/Throwable;)V
0038a move-object v0, v2
0038c return-object v0
~~~

## Class Lru/mail/util/push/provider/CapabilitiesProvider; @ Mail-15.107.0.148045.apk/classes13.dex

## Class Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory; @ Mail-15.107.0.148045.apk/classes13.dex

### <clinit> ()V
~~~
00000 new-instance v0, Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;
00004 invoke-direct v0, Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;-><init>()V
0000a sput-object v0, Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;->INSTANCE Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;
0000e return-void 
~~~

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### createProviderForMailApp (Z Lru/mail/logic/pushfilters/FilterAccessor; Ljava/lang/Boolean;)Lru/mail/util/push/provider/CapabilitiesProvider;
~~~
00000 const-string v0, "accessor"
00004 invoke-static v3, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a new-instance v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;
0000e invoke-direct v0, v2, v3, v4, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;-><init>(Z Lru/mail/logic/pushfilters/FilterAccessor; Ljava/lang/Boolean;)V
00014 return-object v0
~~~

### createProviderForPortalApps ()Lru/mail/util/push/provider/CapabilitiesProvider;
~~~
00000 new-instance v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider;
00004 invoke-direct v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider;-><init>()V
0000a return-object v0
~~~

## Class Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Companion; @ Mail-15.107.0.148045.apk/classes13.dex

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### <init> (Lkotlin/jvm/internal/DefaultConstructorMarker;)V
~~~
00000 invoke-direct v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Companion;-><init>()V
00006 return-void 
~~~

## Class Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter$Companion; @ Mail-15.107.0.148045.apk/classes13.dex

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### <init> (Lkotlin/jvm/internal/DefaultConstructorMarker;)V
~~~
00000 invoke-direct v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter$Companion;-><init>()V
00006 return-void 
~~~

## Class Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter; @ Mail-15.107.0.148045.apk/classes13.dex

### <clinit> ()V
~~~
00000 new-instance v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter$Companion;
00004 const/4 v1, 0
00006 invoke-direct v0, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
0000c sput-object v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->Companion Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter$Companion;
00010 return-void 
~~~

### <init> (Ljava/lang/String; [Ljava/lang/Object; Z)V
~~~
00000 const-string v0, "arrayName"
00004 invoke-static v2, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a invoke-direct v1, Ljava/lang/Object;-><init>()V
00010 iput-object v2, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->arrayName Ljava/lang/String;
00014 iput-object v3, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->options [Ljava/lang/Object;
00018 iput-boolean v4, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->enabled Z
0001c return-void 
~~~

### getJsonIds ()Lorg/json/JSONArray;
~~~
00000 iget-object v0, v2, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->options [Ljava/lang/Object;
00004 if-nez v0, +008h
00008 new-instance v0, Lorg/json/JSONArray;
0000c invoke-direct v0, Lorg/json/JSONArray;-><init>()V
00012 return-object v0
00014 new-instance v0, Lorg/json/JSONArray;
00018 iget-object v1, v2, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->options [Ljava/lang/Object;
0001c invoke-static v1, Ljava/util/Arrays;->toString([Ljava/lang/Object;)Ljava/lang/String;
00022 move-result-object v1
00024 invoke-direct v0, v1, Lorg/json/JSONArray;-><init>(Ljava/lang/String;)V
0002a return-object v0
~~~

### toJson ()Lorg/json/JSONObject;
~~~
00000 new-instance v0, Lorg/json/JSONObject;
00004 invoke-direct v0, Lorg/json/JSONObject;-><init>()V
0000a iget-object v1, v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->arrayName Ljava/lang/String;
0000e invoke-direct v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->getJsonIds()Lorg/json/JSONArray;
00014 move-result-object v2
00016 invoke-virtual v0, v1, v2, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
0001c const-string v1, "enabled"
00020 iget-boolean v2, v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->enabled Z
00024 invoke-virtual v0, v1, v2, Lorg/json/JSONObject;->put(Ljava/lang/String; Z)Lorg/json/JSONObject;
0002a return-object v0
0002c move-exception v1
0002e invoke-virtual v1, Ljava/lang/Throwable;->printStackTrace()V
00034 return-object v0
~~~

### toString ()Ljava/lang/String;
~~~
00000 invoke-virtual v2, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->toJson()Lorg/json/JSONObject;
00006 move-result-object v0
00008 invoke-virtual v0, Lorg/json/JSONObject;->toString()Ljava/lang/String;
0000e move-result-object v0
00010 const-string v1, "toString(...)"
00014 invoke-static v0, v1, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object; Ljava/lang/String;)V
0001a return-object v0
~~~

## Class Lru/mail/util/push/provider/impl/MailCapabilitiesProvider; @ Mail-15.107.0.148045.apk/classes13.dex

### <clinit> ()V
~~~
00000 new-instance v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Companion;
00004 const/4 v1, 0
00006 invoke-direct v0, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
0000c sput-object v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->Companion Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Companion;
00010 const/16 v0, 8
00014 sput v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->$stable I
00018 sget-object v0, Lru/mail/util/log/Log;->Companion Lru/mail/util/log/Log$Companion;
0001c const-string v1, "MailCapabilitiesProvider"
00020 invoke-virtual v0, v1, Lru/mail/util/log/Log$Companion;->getLog(Ljava/lang/String;)Lru/mail/util/log/Log;
00026 move-result-object v0
00028 sput-object v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->LOG Lru/mail/util/log/Log;
0002c return-void 
~~~

### <init> (Z Lru/mail/logic/pushfilters/FilterAccessor; Ljava/lang/Boolean;)V
~~~
00000 const-string v0, "accessor"
00004 invoke-static v3, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a invoke-direct v1, Ljava/lang/Object;-><init>()V
00010 iput-boolean v2, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->needPushMsg Z
00014 iput-object v3, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->accessor Lru/mail/logic/pushfilters/FilterAccessor;
00018 iput-object v4, v1, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->isEnabledImportantReminder Ljava/lang/Boolean;
0001c return-void 
~~~

### addEnabledTags (Lorg/json/JSONObject; Ljava/util/Collection;)V
~~~
00000 invoke-interface v4, Ljava/util/Collection;->isEmpty()Z
00006 move-result v0
00008 if-nez v0, +035h
0000c check-cast v4, Ljava/lang/Iterable;
00010 new-instance v0, Ljava/util/ArrayList;
00014 const/16 v1, 10
00018 invoke-static v4, v1, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
0001e move-result v1
00020 invoke-direct v0, v1, Ljava/util/ArrayList;-><init>(I)V
00026 invoke-interface v4, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0002c move-result-object v4
0002e invoke-interface v4, Ljava/util/Iterator;->hasNext()Z
00034 move-result v1
00036 if-eqz v1, +014h
0003a invoke-interface v4, Ljava/util/Iterator;->next()Ljava/lang/Object;
00040 move-result-object v1
00042 check-cast v1, Lru/mail/portal/app/adapter/notifications/tags/Tag;
00046 invoke-interface v1, Lru/mail/portal/app/adapter/notifications/tags/Tag;->getIdForPusher()I
0004c move-result v1
0004e invoke-static v1, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
00054 move-result-object v1
00056 invoke-interface v0, v1, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0005c goto -17h
0005e new-instance v4, Lorg/json/JSONArray;
00062 invoke-direct v4, v0, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V
00068 const-string v0, "tags"
0006c invoke-virtual v3, v0, v4, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
00072 return-void 
~~~

### getCanMailJson (Lru/mail/logic/pushfilters/FilterAccessor; Ljava/lang/String;)Lorg/json/JSONObject;
~~~
00000 sget-object v0, Lru/mail/logic/pushfilters/PushFilter$Type;->FOLDER Lru/mail/logic/pushfilters/PushFilter$Type;
00004 invoke-virtual v10, v0, Lru/mail/logic/pushfilters/FilterAccessor;->get(Lru/mail/logic/pushfilters/PushFilter$Type;)Ljava/util/List;
0000a move-result-object v1
0000c check-cast v1, Ljava/util/Collection;
00010 check-cast v1, Ljava/lang/Iterable;
00014 new-instance v2, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;
00018 const/4 v3, 0
0001a invoke-direct v2, v11, v3, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;-><init>(Ljava/lang/String; Z)V
00020 invoke-static v1, v2, Lorg/apache/commons/collections4/CollectionUtils;->select(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Predicate;)Ljava/util/Collection;
00026 move-result-object v11
00028 check-cast v11, Ljava/lang/Iterable;
0002c sget-object v1, Lru/mail/logic/pushfilters/FilterAccessor;->CONVERTER_FILTERS_TO_ITEM_ID Lorg/apache/commons/collections4/Transformer;
00030 invoke-static v11, v1, Lorg/apache/commons/collections4/CollectionUtils;->collect(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Transformer;)Ljava/util/Collection;
00036 move-result-object v11
00038 sget-object v2, Lru/mail/logic/pushfilters/PushFilter$Type;->SOCIAL Lru/mail/logic/pushfilters/PushFilter$Type;
0003c invoke-virtual v10, v2, Lru/mail/logic/pushfilters/FilterAccessor;->get(Lru/mail/logic/pushfilters/PushFilter$Type;)Ljava/util/List;
00042 move-result-object v4
00044 check-cast v4, Ljava/lang/Iterable;
00048 new-instance v5, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;
0004c const/4 v6, 1
0004e invoke-direct v5, v6, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;-><init>(Z)V
00054 invoke-static v4, v5, Lorg/apache/commons/collections4/CollectionUtils;->select(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Predicate;)Ljava/util/Collection;
0005a move-result-object v4
0005c check-cast v4, Ljava/lang/Iterable;
00060 invoke-static v4, v1, Lorg/apache/commons/collections4/CollectionUtils;->collect(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Transformer;)Ljava/util/Collection;
00066 move-result-object v4
00068 sget-object v5, Lru/mail/logic/pushfilters/PushFilter$Type;->SERVICE Lru/mail/logic/pushfilters/PushFilter$Type;
0006c invoke-virtual v10, v5, Lru/mail/logic/pushfilters/FilterAccessor;->get(Lru/mail/logic/pushfilters/PushFilter$Type;)Ljava/util/List;
00072 move-result-object v7
00074 check-cast v7, Ljava/lang/Iterable;
00078 new-instance v8, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;
0007c invoke-direct v8, v6, Lru/mail/logic/pushfilters/FilterAccessor$PushFilterByParams;-><init>(Z)V
00082 invoke-static v7, v8, Lorg/apache/commons/collections4/CollectionUtils;->select(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Predicate;)Ljava/util/Collection;
00088 move-result-object v6
0008a check-cast v6, Ljava/lang/Iterable;
0008e invoke-static v6, v1, Lorg/apache/commons/collections4/CollectionUtils;->collect(Ljava/lang/Iterable; Lorg/apache/commons/collections4/Transformer;)Ljava/util/Collection;
00094 move-result-object v1
00096 invoke-virtual v10, v0, Lru/mail/logic/pushfilters/FilterAccessor;->getGroupFilter(Lru/mail/logic/pushfilters/PushFilter$Type;)Lru/mail/logic/pushfilters/PushFilter;
0009c move-result-object v0
0009e invoke-interface v0, Lru/mail/logic/pushfilters/PushFilter;->getState()Z
000a4 move-result v0
000a6 invoke-virtual v10, v2, Lru/mail/logic/pushfilters/FilterAccessor;->getGroupFilter(Lru/mail/logic/pushfilters/PushFilter$Type;)Lru/mail/logic/pushfilters/PushFilter;
000ac move-result-object v2
000ae invoke-interface v2, Lru/mail/logic/pushfilters/PushFilter;->getState()Z
000b4 move-result v2
000b6 invoke-virtual v10, v5, Lru/mail/logic/pushfilters/FilterAccessor;->getGroupFilter(Lru/mail/logic/pushfilters/PushFilter$Type;)Lru/mail/logic/pushfilters/PushFilter;
000bc move-result-object v10
000be invoke-interface v10, Lru/mail/logic/pushfilters/PushFilter;->getState()Z
000c4 move-result v10
000c6 new-instance v5, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;
000ca invoke-static v11, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
000d0 new-array v6, v3, [Ljava/lang/Object;
000d4 invoke-interface v11, v6, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;
000da move-result-object v11
000dc const-string v6, "filterList"
000e0 invoke-direct v5, v6, v11, v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;-><init>(Ljava/lang/String; [Ljava/lang/Object; Z)V
000e6 new-instance v11, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;
000ea invoke-static v4, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
000f0 new-array v0, v3, [Ljava/lang/Object;
000f4 invoke-interface v4, v0, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;
000fa move-result-object v0
000fc const-string v4, "excludeList"
00100 invoke-direct v11, v4, v0, v2, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;-><init>(Ljava/lang/String; [Ljava/lang/Object; Z)V
00106 new-instance v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;
0010a invoke-static v1, Lkotlin/jvm/internal/Intrinsics;->checkNotNull(Ljava/lang/Object;)V
00110 new-array v2, v3, [Ljava/lang/Object;
00114 invoke-interface v1, v2, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;
0011a move-result-object v1
0011c invoke-direct v0, v4, v1, v10, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;-><init>(Ljava/lang/String; [Ljava/lang/Object; Z)V
00122 new-instance v10, Lorg/json/JSONObject;
00126 invoke-direct v10, Lorg/json/JSONObject;-><init>()V
0012c new-instance v1, Lorg/json/JSONObject;
00130 invoke-direct v1, Lorg/json/JSONObject;-><init>()V
00136 const-string v2, "Folder"
0013a invoke-virtual v5, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->toJson()Lorg/json/JSONObject;
00140 move-result-object v3
00142 invoke-virtual v1, v2, v3, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
00148 const-string v2, "SocialNetwork"
0014c invoke-virtual v11, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->toJson()Lorg/json/JSONObject;
00152 move-result-object v11
00154 invoke-virtual v1, v2, v11, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
0015a const-string v11, "SocialService"
0015e invoke-virtual v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider$Filter;->toJson()Lorg/json/JSONObject;
00164 move-result-object v0
00166 invoke-virtual v1, v11, v0, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
0016c const-string v11, "Filter"
00170 invoke-virtual v10, v11, v1, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
00176 return-object v10
00178 move-exception v11
0017a sget-object v0, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->LOG Lru/mail/util/log/Log;
0017e const-string v1, "Error"
00182 invoke-interface v0, v1, v11, Lru/mail/util/log/Log;->e(Ljava/lang/String; Ljava/lang/Throwable;)V
00188 return-object v10
~~~

### toCapabilitiesValue (Z)I
~~~
00000 return v1
~~~

### getCapabilities (Ljava/lang/String; Ljava/util/Collection;)Lorg/json/JSONObject;
~~~
00000 const-string v0, "userIdentifier"
00004 invoke-static v4, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a const-string v0, "enabledTags"
0000e invoke-static v5, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
00014 new-instance v0, Lorg/json/JSONObject;
00018 invoke-direct v0, Lorg/json/JSONObject;-><init>()V
0001e iget-boolean v1, v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->needPushMsg Z
00022 const-string v2, "can_mail"
00026 if-eqz v1, +00eh
0002a iget-object v1, v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->accessor Lru/mail/logic/pushfilters/FilterAccessor;
0002e invoke-direct v3, v1, v4, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->getCanMailJson(Lru/mail/logic/pushfilters/FilterAccessor; Ljava/lang/String;)Lorg/json/JSONObject;
00034 move-result-object v4
00036 invoke-virtual v0, v2, v4, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
0003c goto +7h
0003e move-exception v4
00040 goto +1ah
00042 const/4 v4, 0
00044 invoke-virtual v0, v2, v4, Lorg/json/JSONObject;->put(Ljava/lang/String; I)Lorg/json/JSONObject;
0004a iget-object v4, v3, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->isEnabledImportantReminder Ljava/lang/Boolean;
0004e if-eqz v4, +00fh
00052 invoke-virtual v4, Ljava/lang/Boolean;->booleanValue()Z
00058 move-result v4
0005a const-string v1, "actual_support"
0005e invoke-direct v3, v4, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->toCapabilitiesValue(Z)I
00064 move-result v4
00066 invoke-virtual v0, v1, v4, Lorg/json/JSONObject;->put(Ljava/lang/String; I)Lorg/json/JSONObject;
0006c invoke-direct v3, v0, v5, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->addEnabledTags(Lorg/json/JSONObject; Ljava/util/Collection;)V
00072 return-object v0
00074 sget-object v5, Lru/mail/util/push/provider/impl/MailCapabilitiesProvider;->LOG Lru/mail/util/log/Log;
00078 const-string v1, "Failed to construct capabilities"
0007c invoke-interface v5, v1, v4, Lru/mail/util/log/Log;->e(Ljava/lang/String; Ljava/lang/Throwable;)V
00082 return-object v0
~~~

## Class Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider$Companion; @ Mail-15.107.0.148045.apk/classes13.dex

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### <init> (Lkotlin/jvm/internal/DefaultConstructorMarker;)V
~~~
00000 invoke-direct v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider$Companion;-><init>()V
00006 return-void 
~~~

## Class Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider; @ Mail-15.107.0.148045.apk/classes13.dex

### <clinit> ()V
~~~
00000 new-instance v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider$Companion;
00004 const/4 v1, 0
00006 invoke-direct v0, v1, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
0000c sput-object v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider;->Companion Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider$Companion;
00010 sget-object v0, Lru/mail/util/log/Log;->Companion Lru/mail/util/log/Log$Companion;
00014 const-string v1, "PortalAppCapabilitiesProvider"
00018 invoke-virtual v0, v1, Lru/mail/util/log/Log$Companion;->getLog(Ljava/lang/String;)Lru/mail/util/log/Log;
0001e move-result-object v0
00020 sput-object v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider;->LOG Lru/mail/util/log/Log;
00024 return-void 
~~~

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### getCapabilities (Ljava/lang/String; Ljava/util/Collection;)Lorg/json/JSONObject;
~~~
00000 const-string v0, "userIdentifier"
00004 invoke-static v3, v0, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
0000a const-string v3, "enabledTags"
0000e invoke-static v4, v3, Lkotlin/jvm/internal/Intrinsics;->checkNotNullParameter(Ljava/lang/Object; Ljava/lang/String;)V
00014 new-instance v3, Lorg/json/JSONObject;
00018 invoke-direct v3, Lorg/json/JSONObject;-><init>()V
0001e invoke-interface v4, Ljava/util/Collection;->isEmpty()Z
00024 move-result v0
00026 if-nez v0, +03fh
0002a check-cast v4, Ljava/lang/Iterable;
0002e new-instance v0, Ljava/util/ArrayList;
00032 const/16 v1, 10
00036 invoke-static v4, v1, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
0003c move-result v1
0003e invoke-direct v0, v1, Ljava/util/ArrayList;-><init>(I)V
00044 invoke-interface v4, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0004a move-result-object v4
0004c invoke-interface v4, Ljava/util/Iterator;->hasNext()Z
00052 move-result v1
00054 if-eqz v1, +016h
00058 invoke-interface v4, Ljava/util/Iterator;->next()Ljava/lang/Object;
0005e move-result-object v1
00060 check-cast v1, Lru/mail/portal/app/adapter/notifications/tags/Tag;
00064 invoke-interface v1, Lru/mail/portal/app/adapter/notifications/tags/Tag;->getIdForPusher()I
0006a move-result v1
0006c invoke-static v1, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;
00072 move-result-object v1
00074 invoke-interface v0, v1, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0007a goto -17h
0007c move-exception v4
0007e goto +ch
00080 new-instance v4, Lorg/json/JSONArray;
00084 invoke-direct v4, v0, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V
0008a const-string v0, "tags"
0008e invoke-virtual v3, v0, v4, Lorg/json/JSONObject;->put(Ljava/lang/String; Ljava/lang/Object;)Lorg/json/JSONObject;
00094 return-object v3
00096 sget-object v0, Lru/mail/util/push/provider/impl/PortalAppCapabilitiesProvider;->LOG Lru/mail/util/log/Log;
0009a const-string v1, "Failed to construct capabilities"
0009e invoke-interface v0, v1, v4, Lru/mail/util/log/Log;->e(Ljava/lang/String; Ljava/lang/Throwable;)V
000a4 return-object v3
~~~

## Class Lru/mail/util/analytics/Distributors; @ Mail-15.107.0.148045.apk/classes16.dex

### <clinit> ()V
~~~
00000 sget-object v0, Lru/mail/sdk/BuildConfigVariablesHolder;->distributor Ljava/lang/String;
00004 sput-object v0, Lru/mail/util/analytics/Distributors;->CURRENT_DISTRIBUTOR Ljava/lang/String;
00008 new-instance v0, Ljava/util/concurrent/atomic/AtomicReference;
0000c invoke-direct v0, Ljava/util/concurrent/atomic/AtomicReference;-><init>()V
00012 sput-object v0, Lru/mail/util/analytics/Distributors;->sPubNativeId Ljava/util/concurrent/atomic/AtomicReference;
00016 new-instance v0, Ljava/util/concurrent/atomic/AtomicReference;
0001a invoke-direct v0, Ljava/util/concurrent/atomic/AtomicReference;-><init>()V
00020 sput-object v0, Lru/mail/util/analytics/Distributors;->sPlacementId Ljava/util/concurrent/atomic/AtomicReference;
00024 return-void 
~~~

### <init> ()V
~~~
00000 invoke-direct v0, Ljava/lang/Object;-><init>()V
00006 return-void 
~~~

### getFirstDistributor (Landroid/content/Context;)Lru/mail/util/analytics/Distributors$Distributor;
~~~
00000 invoke-static v1, Lru/mail/locator/Locator;->from(Landroid/content/Context;)Lru/mail/locator/Locator;
00006 move-result-object v1
00008 const-class v0, Lru/mail/logic/content/DistributorStore;
0000c invoke-virtual v1, v0, Lru/mail/locator/Locator;->locate(Ljava/lang/Class;)Ljava/lang/Object;
00012 move-result-object v1
00014 check-cast v1, Lru/mail/logic/content/DistributorStore;
00018 invoke-virtual v1, Lru/mail/logic/content/DistributorStore;->getDistributor()Ljava/lang/String;
0001e move-result-object v1
00020 invoke-static v1, Lru/mail/util/analytics/Distributors$Distributor;->from(Ljava/lang/String;)Lru/mail/util/analytics/Distributors$Distributor;
00026 move-result-object v1
00028 return-object v1
~~~

### getLocalFbPlacementId ()Ljava/lang/String;
~~~
00000 sget-object v0, Lru/mail/util/analytics/Distributors;->sPlacementId Ljava/util/concurrent/atomic/AtomicReference;
00004 invoke-virtual v0, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;
0000a move-result-object v0
0000c check-cast v0, Ljava/lang/String;
00010 return-object v0
~~~

### getLocalPubNativeId ()Ljava/lang/String;
~~~
00000 sget-object v0, Lru/mail/util/analytics/Distributors;->sPubNativeId Ljava/util/concurrent/atomic/AtomicReference;
00004 invoke-virtual v0, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;
0000a move-result-object v0
0000c check-cast v0, Ljava/lang/String;
00010 return-object v0
~~~

### setFbPlacementId (Ljava/lang/String;)V
~~~
00000 sget-object v0, Lru/mail/util/analytics/Distributors;->sPlacementId Ljava/util/concurrent/atomic/AtomicReference;
00004 invoke-virtual v0, v1, Ljava/util/concurrent/atomic/AtomicReference;->getAndSet(Ljava/lang/Object;)Ljava/lang/Object;
0000a return-void 
~~~

### setFirstIfNeeded (Landroid/content/Context;)V
~~~
00000 invoke-static v2, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;
00006 move-result-object v2
00008 const/4 v0, 0
0000a const-string v1, "first_app_distributor"
0000e invoke-interface v2, v1, v0, Landroid/content/SharedPreferences;->getString(Ljava/lang/String; Ljava/lang/String;)Ljava/lang/String;
00014 move-result-object v0
00016 invoke-static v0, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z
0001c move-result v0
0001e if-eqz v0, +00fh
00022 invoke-interface v2, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;
00028 move-result-object v2
0002a sget-object v0, Lru/mail/util/analytics/Distributors;->CURRENT_DISTRIBUTOR Ljava/lang/String;
0002e invoke-interface v2, v1, v0, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String; Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
00034 move-result-object v2
00036 invoke-interface v2, Landroid/content/SharedPreferences$Editor;->apply()V
0003c return-void 
~~~

### setPubNativeId (Ljava/lang/String;)V
~~~
00000 sget-object v0, Lru/mail/util/analytics/Distributors;->sPubNativeId Ljava/util/concurrent/atomic/AtomicReference;
00004 invoke-virtual v0, v1, Ljava/util/concurrent/atomic/AtomicReference;->getAndSet(Ljava/lang/Object;)Ljava/lang/Object;
0000a return-void 
~~~

## Class Lcom/vk/pushme/logic/usecase/SubscriptionUseCase; @ Mail-15.107.0.148045.apk/classes19.dex

### mapToNetworkModels (Ljava/util/Collection; Ljava/util/Collection; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/util/Map; Ljava/lang/String;)Ljava/util/Collection;
~~~
00000 move-object/from16 v0, v21
00004 invoke-interface/range v23, Ljava/util/Collection;->isEmpty()Z
0000a move-result v1
0000c if-nez v1, +11ah
00010 new-instance v1, Ljava/util/LinkedHashMap;
00014 invoke-direct v1, Ljava/util/LinkedHashMap;-><init>()V
0001a new-instance v2, Ljava/util/ArrayList;
0001e invoke-direct v2, Ljava/util/ArrayList;-><init>()V
00024 invoke-interface/range v22, Ljava/util/Collection;->iterator()Ljava/util/Iterator;
0002a move-result-object v3
0002c invoke-interface v3, Ljava/util/Iterator;->hasNext()Z
00032 move-result v4
00034 if-eqz v4, +105h
00038 invoke-interface v3, Ljava/util/Iterator;->next()Ljava/lang/Object;
0003e move-result-object v4
00040 check-cast v4, Lcom/vk/pushme/logic/Subscription;
00044 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getAccount()Ljava/lang/String;
0004a move-result-object v5
0004c sget-object v6, Ljava/util/Locale;->ROOT Ljava/util/Locale;
00050 invoke-virtual v5, v6, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;
00056 move-result-object v8
00058 const-string v5, "toLowerCase(...)"
0005c invoke-static v8, v5, Lkotlin/jvm/internal/Intrinsics;->checkNotNullExpressionValue(Ljava/lang/Object; Ljava/lang/String;)V
00062 invoke-static v8, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00068 move-result v5
0006a if-eqz v5, +00ah
0006e invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
00074 move-result-object v4
00076 invoke-direct v0, v4, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logBlancAccount(Ljava/lang/String;)V
0007c goto -28h
0007e invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
00084 move-result-object v5
00086 invoke-direct v0, v5, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isV1(Ljava/lang/String;)Z
0008c move-result v5
0008e const/4 v6, 2
00090 const/4 v7, 0
00092 if-nez v5, +039h
00096 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
0009c move-result-object v5
0009e invoke-direct v0, v8, v1, v5, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->tryToPeekAuthToken(Ljava/lang/String; Ljava/util/Map; Ljava/lang/String;)Ljava/lang/String;
000a4 move-result-object v5
000a6 if-eqz v5, +00bh
000aa invoke-static v5, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
000b0 move-result v9
000b2 if-eqz v9, +003h
000b6 goto +3h
000b8 move-object v12, v5
000ba goto +26h
000bc iget-object v5, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
000c0 new-instance v9, Ljava/lang/StringBuilder;
000c4 invoke-direct v9, Ljava/lang/StringBuilder;-><init>()V
000ca const-string v10, "Unable to obtain auth token for account "
000ce invoke-virtual v9, v10, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000d4 invoke-virtual v9, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
000da invoke-virtual v9, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
000e0 move-result-object v8
000e2 invoke-static v5, v8, v7, v6, v7, Lcom/vk/pushme/common/Logger;->error$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
000e8 iget-object v5, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
000ec invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
000f2 move-result-object v4
000f4 const-string v6, "NO_PUSH_TOKEN_FOUND"
000f8 const-string v7, "Unable to obtain auth token for account"
000fc invoke-virtual v5, v4, v6, v7, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00102 goto -6bh
00104 move-object v12, v7
00106 new-instance v5, Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;
0010a invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getTags()Ljava/util/Set;
00110 move-result-object v9
00112 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getDeliveryTime()Lcom/vk/pushme/model/DeliveryTime;
00118 move-result-object v10
0011a invoke-direct v0, v10, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mapDeliveryTime(Lcom/vk/pushme/model/DeliveryTime;)Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;
00120 move-result-object v10
00122 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getExtras()Lkotlinx/serialization/json/JsonObject;
00128 move-result-object v11
0012a invoke-direct v5, v9, v10, v11, Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings;-><init>(Ljava/util/Set; Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime; Lkotlinx/serialization/json/JsonObject;)V
00130 invoke-interface/range v23, Ljava/util/Collection;->iterator()Ljava/util/Iterator;
00136 move-result-object v20
00138 invoke-interface/range v20, Ljava/util/Iterator;->hasNext()Z
0013e move-result v9
00140 if-eqz v9, -08ah
00144 invoke-interface/range v20, Ljava/util/Iterator;->next()Ljava/lang/Object;
0014a move-result-object v9
0014c check-cast v9, Lcom/vk/pushme/database/entity/PushToken;
00150 sget-object v10, Lcom/vk/pushme/model/Transport;->Companion Lcom/vk/pushme/model/Transport$Companion;
00154 invoke-virtual v9, Lcom/vk/pushme/database/entity/PushToken;->getTransport()Ljava/lang/String;
0015a move-result-object v11
0015c invoke-virtual v10, v11, Lcom/vk/pushme/model/Transport$Companion;->fromString(Ljava/lang/String;)Lcom/vk/pushme/model/Transport;
00162 move-result-object v10
00164 if-nez v10, +03bh
00168 iget-object v10, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
0016c invoke-virtual v9, Lcom/vk/pushme/database/entity/PushToken;->getTransport()Ljava/lang/String;
00172 move-result-object v11
00174 new-instance v13, Ljava/lang/StringBuilder;
00178 invoke-direct v13, Ljava/lang/StringBuilder;-><init>()V
0017e const-string v14, "Unable to parse transport: "
00182 invoke-virtual v13, v14, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00188 invoke-virtual v13, v11, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0018e invoke-virtual v13, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00194 move-result-object v11
00196 invoke-static v10, v11, v7, v6, v7, Lcom/vk/pushme/common/Logger;->error$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0019c iget-object v10, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
001a0 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
001a6 move-result-object v11
001a8 invoke-virtual v9, Lcom/vk/pushme/database/entity/PushToken;->getTransport()Ljava/lang/String;
001ae move-result-object v9
001b0 new-instance v13, Ljava/lang/StringBuilder;
001b4 invoke-direct v13, Ljava/lang/StringBuilder;-><init>()V
001ba invoke-virtual v13, v14, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
001c0 invoke-virtual v13, v9, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
001c6 invoke-virtual v13, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
001cc move-result-object v9
001ce const-string v13, "TRANSPORT_PARSE_ERROR"
001d2 invoke-virtual v10, v11, v13, v9, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
001d8 goto -50h
001da invoke-direct v0, v10, v4, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isSuitableForTransport(Lcom/vk/pushme/model/Transport; Lcom/vk/pushme/logic/Subscription;)Z
001e0 move-result v11
001e2 if-nez v11, +003h
001e6 goto -57h
001e8 move-object v11, v7
001ea new-instance v7, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
001ee move-object v13, v9
001f0 invoke-virtual v4, Lcom/vk/pushme/logic/Subscription;->getApplication()Ljava/lang/String;
001f6 move-result-object v9
001f8 invoke-direct v0, v10, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mapTransport(Lcom/vk/pushme/model/Transport;)Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;
001fe move-result-object v10
00200 invoke-virtual v13, Lcom/vk/pushme/database/entity/PushToken;->getToken()Ljava/lang/String;
00206 move-result-object v13
00208 const/16 v18, 0
0020c move-object/from16 v14, v25
00210 move-object/from16 v15, v26
00214 move-object/from16 v16, v27
00218 move-object/from16 v19, v28
0021c move-object/from16 v17, v5
00220 move-object v5, v11
00222 move-object v11, v13
00224 move-object/from16 v13, v24
00228 invoke-direct/range v7 ... v19, Lcom/vk/pushme/network/model/request/SubscriptionRequest;-><init>(Ljava/lang/String; Ljava/lang/String; Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/util/Map; Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings; I Ljava/lang/String;)V
0022e invoke-interface v2, v7, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
00234 move-object v7, v5
00236 move-object/from16 v5, v17
0023a goto/16 -081h
0023e return-object v2
00240 new-instance v1, Ljava/lang/IllegalStateException;
00244 const-string v2, "Check failed."
00248 invoke-direct v1, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
0024e throw v1
~~~

### invoke (Ljava/util/Collection; Z Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
~~~
00000 move-object/from16 v1, v33
00004 move-object/from16 v0, v34
00008 move-object/from16 v2, v36
0000c const-string v9, "Api result V2: invalid accounts ("
00010 const-string v10, "Api result V1: invalid accounts ("
00014 instance-of v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00018 if-eqz v3, +012h
0001c move-object v3, v2
0001e check-cast v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00022 iget v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00026 const/high16 v5, -2147483648
0002a and-int v6, v4, v5
0002e if-eqz v6, +007h
00032 sub-int/2addr v4, v5
00034 iput v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00038 move-object v11, v3
0003a goto +7h
0003c new-instance v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;
00040 invoke-direct v3, v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;-><init>(Lcom/vk/pushme/logic/usecase/SubscriptionUseCase; Lkotlin/coroutines/Continuation;)V
00046 goto -7h
00048 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->result Ljava/lang/Object;
0004c invoke-static Lkotlin/coroutines/intrinsics/IntrinsicsKt;->getCOROUTINE_SUSPENDED()Ljava/lang/Object;
00052 move-result-object v12
00054 iget v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00058 const-string v13, "INVALID_ACCOUNT_ERROR"
0005c const-string v14, ")"
00060 const/4 v4, 3
00062 const/4 v7, 2
00064 const/4 v8, 1
00066 if-eqz v3, +161h
0006a if-eq v3, v8, +14dh
0006e if-eq v3, v7, +133h
00072 if-eq v3, v4, +0a1h
00076 const/4 v0, 4
00078 if-ne v3, v0, +096h
0007c iget v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
00080 iget-boolean v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
00084 iget-object v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
00088 check-cast v4, Ljava/util/Set;
0008c iget-object v10, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
00090 check-cast v10, Ljava/util/Collection;
00094 iget-object v10, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
00098 check-cast v10, Ljava/util/Iterator;
0009c iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
000a0 check-cast v5, Ljava/lang/Iterable;
000a4 iget-object v6, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
000a8 check-cast v6, Lcom/vk/pushme/network/PushMeApi;
000ac iget-object v8, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
000b0 check-cast v8, Ljava/util/Set;
000b4 iget-object v7, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
000b8 check-cast v7, Ljava/util/Set;
000bc iget-object v15, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
000c0 check-cast v15, Lcom/vk/pushme/logic/SubscriptionBatcher;
000c4 move-object/from16 v19, v2
000c8 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
000cc check-cast v2, Ljava/lang/String;
000d0 move-object/from16 v34, v2
000d4 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
000d8 check-cast v2, Ljava/lang/String;
000dc move-object/from16 v35, v2
000e0 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
000e4 check-cast v2, Ljava/util/List;
000e8 move-object/from16 v20, v2
000ec iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
000f0 check-cast v2, Ljava/util/List;
000f4 move-object/from16 v21, v2
000f8 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
000fc check-cast v2, Ljava/util/Collection;
00100 move-object/from16 v22, v2
00104 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00108 check-cast v2, Ljava/lang/String;
0010c move-object/from16 v23, v2
00110 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00114 check-cast v2, Ljava/lang/String;
00118 move-object/from16 v24, v2
0011c iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00120 check-cast v2, Ljava/util/List;
00124 move-object/from16 v25, v2
00128 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
0012c check-cast v2, Ljava/lang/String;
00130 move-object/from16 v26, v2
00134 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00138 check-cast v2, Ljava/util/Collection;
0013c invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00142 move-object v1, v8
00144 move v8, v3
00146 move-object/from16 v3, v26
0014a move-object/from16 v26, v13
0014e move-object v13, v1
00150 move-object/from16 v16, v10
00154 move-object/from16 v27, v14
00158 move-object/from16 v17, v15
0015c move-object/from16 v30, v22
00160 move-object/from16 v18, v25
00164 const/4 v1, 0
00166 const/16 v10, 10
0016a const/4 v14, 4
0016c move-object/from16 v25, v5
00170 move-object v15, v7
00172 move-object/from16 v22, v9
00176 move-object/from16 v5, v35
0017a move v9, v0
0017c move-object v0, v2
0017e move-object v7, v6
00180 move-object/from16 v2, v19
00184 move-object/from16 v19, v23
00188 move-object/from16 v6, v34
0018c goto/16 +58dh
00190 move-exception v0
00192 const/4 v4, 1
00194 const/4 v5, 0
00196 goto/16 +6f3h
0019a move-exception v0
0019c move-object/from16 v2, v26
001a0 goto/16 +6e2h
001a4 new-instance v0, Ljava/lang/IllegalStateException;
001a8 const-string v2, "call to 'resume' before 'invoke' with coroutine"
001ac invoke-direct v0, v2, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V
001b2 throw v0
001b4 move-object/from16 v19, v2
001b8 iget v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
001bc iget-boolean v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
001c0 iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
001c4 check-cast v3, Ljava/util/Set;
001c8 iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
001cc check-cast v5, Ljava/util/Collection;
001d0 iget-object v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
001d4 check-cast v5, Ljava/util/Iterator;
001d8 iget-object v6, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
001dc check-cast v6, Ljava/lang/Iterable;
001e0 iget-object v7, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
001e4 check-cast v7, Lcom/vk/pushme/network/PushMeApi;
001e8 iget-object v8, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
001ec check-cast v8, Ljava/util/Set;
001f0 iget-object v15, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
001f4 check-cast v15, Ljava/util/Set;
001f8 iget-object v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
001fc check-cast v4, Lcom/vk/pushme/logic/SubscriptionBatcher;
00200 move/from16 v21, v2
00204 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
00208 check-cast v2, Ljava/lang/String;
0020c move-object/from16 v34, v2
00210 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
00214 check-cast v2, Ljava/lang/String;
00218 move-object/from16 v35, v2
0021c iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
00220 check-cast v2, Ljava/util/List;
00224 move-object/from16 v22, v2
00228 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
0022c check-cast v2, Ljava/util/List;
00230 move-object/from16 v23, v2
00234 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
00238 check-cast v2, Ljava/util/Collection;
0023c move-object/from16 v24, v2
00240 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00244 check-cast v2, Ljava/lang/String;
00248 move-object/from16 v25, v2
0024c iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00250 check-cast v2, Ljava/lang/String;
00254 move-object/from16 v26, v2
00258 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
0025c check-cast v2, Ljava/util/List;
00260 move-object/from16 v27, v2
00264 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00268 check-cast v2, Ljava/lang/String;
0026c move-object/from16 v28, v2
00270 iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00274 check-cast v2, Ljava/util/Collection;
00278 invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
0027e move-object/from16 v17, v6
00282 move-object/from16 v1, v22
00286 move-object/from16 v30, v24
0028a move-object/from16 v20, v26
0028e move-object/from16 v18, v27
00292 move v6, v0
00294 move-object v0, v2
00296 move-object/from16 v22, v9
0029a move-object/from16 v26, v10
0029e move-object/from16 v2, v19
002a2 move-object/from16 v19, v25
002a6 move-object v10, v4
002a8 move-object v9, v8
002aa move-object/from16 v25, v14
002ae move-object/from16 v14, v23
002b2 move-object/from16 v8, v34
002b6 move-object v4, v3
002b8 move-object/from16 v23, v13
002bc move-object v13, v15
002be move-object/from16 v15, v28
002c2 move-object/from16 v3, v35
002c6 goto/16 +341h
002ca move-exception v0
002cc move-object/from16 v2, v28
002d0 goto/16 +64ah
002d4 move-object/from16 v19, v2
002d8 iget-boolean v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
002dc iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
002e0 check-cast v2, Ljava/lang/String;
002e4 iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
002e8 check-cast v3, Ljava/util/Collection;
002ec invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
002f2 move-object v15, v2
002f4 move-object/from16 v2, v19
002f8 const/4 v5, 2
002fa goto/16 +086h
002fe move-exception v0
00300 goto/16 +632h
00304 move-object/from16 v19, v2
00308 iget-boolean v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
0030c iget-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00310 check-cast v2, Ljava/lang/String;
00314 iget-object v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00318 check-cast v3, Ljava/util/Collection;
0031c invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00322 move v4, v0
00324 move-object v0, v3
00326 goto +59h
00328 move-object/from16 v19, v2
0032c invoke-static/range v19, Lkotlin/ResultKt;->throwOnFailure(Ljava/lang/Object;)V
00332 iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00336 invoke-interface v0, Ljava/util/Collection;->size()I
0033c move-result v3
0033e new-instance v4, Ljava/lang/StringBuilder;
00342 invoke-direct v4, Ljava/lang/StringBuilder;-><init>()V
00348 const-string v5, "UseCase started, subscriptions count: "
0034c invoke-virtual v4, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00352 invoke-virtual v4, v3, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00358 invoke-virtual v4, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
0035e move-result-object v3
00360 const/4 v4, 2
00362 const/4 v5, 0
00364 invoke-static v2, v3, v5, v4, v5, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0036a invoke-interface v0, Ljava/util/Collection;->isEmpty()Z
00370 move-result v2
00372 if-nez v2, +60bh
00376 move-object/from16 v21, v0
0037a check-cast v21, Ljava/lang/Iterable;
0037e new-instance v27, Lcom/vk/pushme/logic/usecase/d;
00382 invoke-direct/range v27, Lcom/vk/pushme/logic/usecase/d;-><init>()V
00388 const/16 v28, 31
0038c const/16 v29, 0
00390 const/16 v22, 0
00394 const/16 v23, 0
00398 const/16 v24, 0
0039c const/16 v25, 0
003a0 const/16 v26, 0
003a4 invoke-static/range v21 ... v29, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
003aa move-result-object v2
003ac iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
003b0 iput-object v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
003b4 iput-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
003b8 move/from16 v4, v35
003bc iput-boolean v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
003c0 const/4 v5, 1
003c2 iput v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
003c6 const/4 v6, 0
003c8 invoke-static v3, v6, v11, v5, v6, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->lock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; Lkotlin/coroutines/Continuation; I Ljava/lang/Object;)Ljava/lang/Object;
003ce move-result-object v3
003d0 if-ne v3, v12, +004h
003d4 goto/16 +45bh
003d8 iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->pushTokenDao Lcom/vk/pushme/database/dao/PushTokenDao;
003dc iput-object v0, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
003e0 iput-object v2, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
003e4 iput-boolean v4, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
003e8 const/4 v5, 2
003ea iput v5, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
003ee invoke-virtual v3, v11, Lcom/vk/pushme/database/dao/PushTokenDao;->getAll(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
003f4 move-result-object v3
003f6 if-ne v3, v12, +004h
003fa goto/16 +448h
003fe move-object v15, v2
00400 move-object v2, v3
00402 move-object v3, v0
00404 move v0, v4
00406 move-object/from16 v18, v2
0040a check-cast v18, Ljava/util/List;
0040e invoke-interface/range v18, Ljava/util/List;->isEmpty()Z
00414 move-result v2
00416 if-eqz v2, +013h
0041a invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handlePushTokensEmpty(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00420 move-result-object v0
00422 iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00426 const/4 v4, 1
00428 const/4 v5, 0
0042a invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00430 return-object v0
00432 move-exception v0
00434 const/4 v4, 1
00436 move-object v2, v15
00438 goto/16 +596h
0043c const/4 v4, 1
0043e iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00442 invoke-interface v2, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getDeviceId()Ljava/lang/String;
00448 move-result-object v2
0044a if-eqz v2, +581h
0044e invoke-static v2, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00454 move-result v6
00456 if-eqz v6, +004h
0045a goto/16 +579h
0045e iget-object v6, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00462 invoke-interface v6, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getAndroidId()Ljava/lang/String;
00468 move-result-object v6
0046a if-eqz v6, +565h
0046e invoke-static v6, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z
00474 move-result v7
00476 if-eqz v7, +004h
0047a goto/16 +55dh
0047e move v7, v5
00480 move-object v5, v2
00482 move-object v2, v3
00484 move-object/from16 v3, v18
00488 check-cast v3, Ljava/util/Collection;
0048c if-eqz v0, +012h
00490 iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->deviceIdProvider Lcom/vk/pushme/util/provider/DeviceIdProvider;
00494 invoke-interface v8, Lcom/vk/pushme/util/provider/DeviceIdProvider;->getSdkDeviceId()Ljava/lang/String;
0049a move-result-object v8
0049c move/from16 v17, v4
004a0 move-object v4, v6
004a2 move-object v6, v8
004a4 goto +ah
004a6 move-exception v0
004a8 goto/16 -18ah
004ac move-exception v0
004ae goto -3ch
004b0 move/from16 v17, v4
004b4 move-object v4, v6
004b6 const/4 v6, 0
004b8 iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->clientInfoProvider Lcom/vk/pushme/util/provider/ClientInfoProvider;
004bc invoke-interface v8, Lcom/vk/pushme/util/provider/ClientInfoProvider;->getClientInfo()Ljava/util/Map;
004c2 move-result-object v8
004c4 iget-object v7, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->clientInfoProvider Lcom/vk/pushme/util/provider/ClientInfoProvider;
004c8 invoke-interface v7, Lcom/vk/pushme/util/provider/ClientInfoProvider;->getClientTimeZone()Ljava/lang/String;
004ce move-result-object v7
004d0 move-object/from16 v19, v8
004d4 move-object v8, v7
004d6 move-object/from16 v7, v19
004da move-object/from16 v19, v11
004de move/from16 v11, v17
004e2 invoke-direct/range v1 ... v8, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mapToNetworkModels(Ljava/util/Collection; Ljava/util/Collection; Ljava/lang/String; Ljava/lang/String; Ljava/lang/String; Ljava/util/Map; Ljava/lang/String;)Ljava/util/Collection;
004e8 move-result-object v3
004ea invoke-interface v3, Ljava/util/Collection;->isEmpty()Z
004f0 move-result v6
004f2 if-eqz v6, +011h
004f6 invoke-direct v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoSubscriptionsFound()Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
004fc move-result-object v0
004fe iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00502 const/4 v5, 0
00504 invoke-static v2, v5, v11, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
0050a return-object v0
0050c move-exception v0
0050e move v4, v11
00510 goto/16 -1beh
00514 move-object v6, v3
00516 check-cast v6, Ljava/lang/Iterable;
0051a new-instance v7, Ljava/util/ArrayList;
0051e invoke-direct v7, Ljava/util/ArrayList;-><init>()V
00524 invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0052a move-result-object v6
0052c invoke-interface v6, Ljava/util/Iterator;->hasNext()Z
00532 move-result v8
00534 if-eqz v8, +019h
00538 invoke-interface v6, Ljava/util/Iterator;->next()Ljava/lang/Object;
0053e move-result-object v8
00540 move-object/from16 v17, v8
00544 check-cast v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00548 invoke-virtual/range v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
0054e move-result-object v11
00550 invoke-direct v1, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isV1(Ljava/lang/String;)Z
00556 move-result v11
00558 if-eqz v11, +005h
0055c invoke-interface v7, v8, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
00562 const/4 v11, 1
00564 goto -1ch
00566 move-object v6, v3
00568 check-cast v6, Ljava/lang/Iterable;
0056c new-instance v8, Ljava/util/ArrayList;
00570 invoke-direct v8, Ljava/util/ArrayList;-><init>()V
00576 invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
0057c move-result-object v6
0057e invoke-interface v6, Ljava/util/Iterator;->hasNext()Z
00584 move-result v11
00586 if-eqz v11, +01ch
0058a invoke-interface v6, Ljava/util/Iterator;->next()Ljava/lang/Object;
00590 move-result-object v11
00592 move-object/from16 v17, v11
00596 check-cast v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
0059a move-object/from16 v35, v2
0059e invoke-virtual/range v17, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getApplication()Ljava/lang/String;
005a4 move-result-object v2
005a6 invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->isV1(Ljava/lang/String;)Z
005ac move-result v2
005ae if-nez v2, +005h
005b2 invoke-interface v8, v11, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
005b8 move-object/from16 v2, v35
005bc goto -1fh
005be move-object/from16 v35, v2
005c2 new-instance v26, Lcom/vk/pushme/logic/usecase/e;
005c6 invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/e;-><init>()V
005cc const/16 v27, 31
005d0 const/16 v28, 0
005d4 const/16 v21, 0
005d8 const/16 v22, 0
005dc const/16 v23, 0
005e0 const/16 v24, 0
005e4 const/16 v25, 0
005e8 move-object/from16 v20, v7
005ec invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
005f2 move-result-object v2
005f4 new-instance v26, Lcom/vk/pushme/logic/usecase/f;
005f8 invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/f;-><init>()V
005fe const/16 v27, 31
00602 const/16 v28, 0
00606 const/16 v21, 0
0060a const/16 v22, 0
0060e const/16 v23, 0
00612 const/16 v24, 0
00616 const/16 v25, 0
0061a invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
00620 move-result-object v6
00622 move-object/from16 v7, v20
00626 iget-object v11, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
0062a move-object/from16 v17, v2
0062e new-instance v2, Ljava/lang/StringBuilder;
00632 invoke-direct v2, Ljava/lang/StringBuilder;-><init>()V
00638 move-object/from16 v30, v3
0063c const-string v3, "Apps in v1 request: "
00640 invoke-virtual v2, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00646 invoke-virtual v2, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
0064c invoke-virtual v2, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00652 move-result-object v2
00654 const/4 v3, 2
00656 const/4 v6, 0
00658 invoke-static v11, v2, v6, v3, v6, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
0065e new-instance v26, Lcom/vk/pushme/logic/usecase/g;
00662 invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/g;-><init>()V
00668 const/16 v27, 31
0066c const/16 v28, 0
00670 const/16 v21, 0
00674 const/16 v22, 0
00678 const/16 v23, 0
0067c const/16 v24, 0
00680 const/16 v25, 0
00684 move-object/from16 v20, v8
00688 invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
0068e move-result-object v2
00690 new-instance v26, Lcom/vk/pushme/logic/usecase/h;
00694 invoke-direct/range v26, Lcom/vk/pushme/logic/usecase/h;-><init>()V
0069a const/16 v27, 31
0069e const/16 v28, 0
006a2 const/16 v21, 0
006a6 const/16 v22, 0
006aa const/16 v23, 0
006ae const/16 v24, 0
006b2 const/16 v25, 0
006b6 invoke-static/range v20 ... v28, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable; Ljava/lang/CharSequence; Ljava/lang/CharSequence; Ljava/lang/CharSequence; I Ljava/lang/CharSequence; Lkotlin/jvm/functions/Function1; I Ljava/lang/Object;)Ljava/lang/String;
006bc move-result-object v3
006be iget-object v6, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
006c2 new-instance v8, Ljava/lang/StringBuilder;
006c6 invoke-direct v8, Ljava/lang/StringBuilder;-><init>()V
006cc const-string v11, "Apps in v2 request: "
006d0 invoke-virtual v8, v11, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
006d6 invoke-virtual v8, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
006dc invoke-virtual v8, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
006e2 move-result-object v3
006e4 const/4 v8, 2
006e6 const/4 v11, 0
006e8 invoke-static v6, v3, v11, v8, v11, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
006ee new-instance v3, Lcom/vk/pushme/logic/SubscriptionBatcher;
006f2 const/16 v6, 20
006f6 invoke-direct v3, v6, Lcom/vk/pushme/logic/SubscriptionBatcher;-><init>(I)V
006fc move-object/from16 v6, v35
00700 check-cast v6, Ljava/lang/Iterable;
00704 new-instance v8, Ljava/util/ArrayList;
00708 move-object/from16 v21, v2
0070c const/16 v11, 10
00710 invoke-static v6, v11, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00716 move-result v2
00718 invoke-direct v8, v2, Ljava/util/ArrayList;-><init>(I)V
0071e invoke-interface v6, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00724 move-result-object v2
00726 invoke-interface v2, Ljava/util/Iterator;->hasNext()Z
0072c move-result v6
0072e if-eqz v6, +010h
00732 invoke-interface v2, Ljava/util/Iterator;->next()Ljava/lang/Object;
00738 move-result-object v6
0073a check-cast v6, Lcom/vk/pushme/logic/Subscription;
0073e invoke-virtual v6, Lcom/vk/pushme/logic/Subscription;->getAccount()Ljava/lang/String;
00744 move-result-object v6
00746 invoke-interface v8, v6, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0074c goto -13h
0074e invoke-static v8, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
00754 move-result-object v2
00756 new-instance v6, Ljava/util/LinkedHashSet;
0075a invoke-direct v6, Ljava/util/LinkedHashSet;-><init>()V
00760 iget-object v8, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->apiCreator Lkotlin/jvm/functions/Function0;
00764 invoke-interface v8, Lkotlin/jvm/functions/Function0;->invoke()Ljava/lang/Object;
0076a move-result-object v8
0076c check-cast v8, Lcom/vk/pushme/network/PushMeApi;
00770 invoke-virtual v3, v7, Lcom/vk/pushme/logic/SubscriptionBatcher;->batch(Ljava/util/Collection;)Ljava/util/Collection;
00776 move-result-object v11
00778 check-cast v11, Ljava/lang/Iterable;
0077c invoke-interface v11, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00782 move-result-object v22
00784 move-object/from16 v23, v11
00788 move-object v11, v3
0078a move-object/from16 v3, v17
0078e move-object/from16 v17, v23
00792 move-object/from16 v23, v7
00796 move-object v7, v6
00798 move-object/from16 v6, v21
0079c move-object/from16 v21, v23
007a0 move-object/from16 v23, v13
007a4 move-object v13, v2
007a6 move-object/from16 v2, v20
007aa move-object/from16 v20, v5
007ae move-object v5, v8
007b0 move v8, v0
007b2 move-object/from16 v0, v35
007b6 move-object/from16 v35, v22
007ba move-object/from16 v22, v9
007be move-object/from16 v9, v19
007c2 move-object/from16 v19, v4
007c6 const/4 v4, 0
007c8 invoke-interface/range v35, Ljava/util/Iterator;->hasNext()Z
007ce move-result v24
007d0 if-eqz v24, +196h
007d4 invoke-interface/range v35, Ljava/util/Iterator;->next()Ljava/lang/Object;
007da move-result-object v24
007dc move-object/from16 v25, v14
007e0 move-object/from16 v14, v24
007e4 check-cast v14, Ljava/util/Collection;
007e8 move-object/from16 v26, v10
007ec move-object v10, v14
007ee check-cast v10, Ljava/lang/Iterable;
007f2 new-instance v1, Ljava/util/ArrayList;
007f6 move-object/from16 v27, v12
007fa move-object/from16 v28, v14
007fe const/16 v12, 10
00802 invoke-static v10, v12, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00808 move-result v14
0080a invoke-direct v1, v14, Ljava/util/ArrayList;-><init>(I)V
00810 invoke-interface v10, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00816 move-result-object v10
00818 invoke-interface v10, Ljava/util/Iterator;->hasNext()Z
0081e move-result v12
00820 if-eqz v12, +01ah
00824 invoke-interface v10, Ljava/util/Iterator;->next()Ljava/lang/Object;
0082a move-result-object v12
0082c check-cast v12, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00830 invoke-virtual v12, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccount()Ljava/lang/String;
00836 move-result-object v12
00838 invoke-interface v1, v12, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
0083e goto -13h
00840 move-exception v0
00842 move-object/from16 v1, v33
00846 goto/16 -35ah
0084a move-exception v0
0084c move-object/from16 v1, v33
00850 goto/16 -20dh
00854 invoke-static v1, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
0085a move-result-object v1
0085c invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00862 move-result-object v10
00864 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00868 iput-object v15, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
0086c invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00872 move-result-object v10
00874 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00878 invoke-static/range v20, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0087e move-result-object v10
00880 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00884 invoke-static/range v19, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
0088a move-result-object v10
0088c iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00890 invoke-static/range v30, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00896 move-result-object v10
00898 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
0089c invoke-static/range v21, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008a2 move-result-object v10
008a4 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
008a8 iput-object v2, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
008ac iput-object v3, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
008b0 iput-object v6, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
008b4 iput-object v11, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
008b8 iput-object v13, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
008bc iput-object v7, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
008c0 iput-object v5, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
008c4 invoke-static/range v17, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008ca move-result-object v10
008cc iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
008d0 move-object/from16 v10, v35
008d4 iput-object v10, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
008d8 invoke-static/range v24, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008de move-result-object v12
008e0 iput-object v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$16 Ljava/lang/Object;
008e4 invoke-static/range v28, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
008ea move-result-object v12
008ec iput-object v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
008f0 iput-object v1, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
008f4 iput-boolean v8, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
008f8 iput v4, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
008fc const/4 v12, 0
008fe iput v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$1 I
00902 const/4 v12, 3
00904 iput v12, v9, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00908 move-object/from16 v14, v28
0090c invoke-interface v5, v14, v9, Lcom/vk/pushme/network/PushMeApi;->setSettingsV1(Ljava/util/Collection; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00912 move-result-object v14
00914 move-object/from16 v12, v27
00918 if-ne v14, v12, +004h
0091c goto/16 +1b7h
00920 move/from16 v32, v4
00924 move-object v4, v1
00926 move-object v1, v2
00928 move-object v2, v14
0092a move-object/from16 v14, v21
0092e move/from16 v21, v8
00932 move-object v8, v6
00934 move/from16 v6, v32
00938 move-object/from16 v32, v7
0093c move-object v7, v5
0093e move-object v5, v10
00940 move-object v10, v11
00942 move-object v11, v9
00944 move-object/from16 v9, v32
00948 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult;
0094c move-object/from16 v35, v1
00950 sget-object v1, Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;->INSTANCE Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;
00954 invoke-static v2, v1, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
0095a move-result v1
0095c if-eqz v1, +025h
00960 move-object/from16 v1, v33
00964 iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00968 move-object/from16 v24, v5
0096c const-string v5, "Api result V1: OK"
00970 move/from16 v27, v6
00974 move-object/from16 v28, v7
00978 const/4 v6, 2
0097a const/4 v7, 0
0097c invoke-static v2, v5, v7, v6, v7, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00982 move-object v2, v9
00984 check-cast v2, Ljava/util/Collection;
00988 check-cast v4, Ljava/lang/Iterable;
0098c invoke-static v2, v4, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00992 move-object/from16 v31, v8
00996 move-object/from16 v7, v23
0099a move-object/from16 v8, v26
0099e move-object/from16 v26, v9
009a2 goto/16 +08eh
009a6 move-object/from16 v1, v33
009aa move-object/from16 v24, v5
009ae move/from16 v27, v6
009b2 move-object/from16 v28, v7
009b6 instance-of v5, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
009ba if-eqz v5, +067h
009be iget-object v5, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
009c2 move-object v6, v2
009c4 check-cast v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
009c8 invoke-virtual v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
009ce move-result-object v6
009d0 invoke-interface v6, Ljava/util/Set;->size()I
009d6 move-result v6
009d8 new-instance v7, Ljava/lang/StringBuilder;
009dc invoke-direct v7, Ljava/lang/StringBuilder;-><init>()V
009e2 move-object/from16 v31, v8
009e6 move-object/from16 v8, v26
009ea invoke-virtual v7, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
009f0 invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
009f6 move-object/from16 v6, v25
009fa invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00a00 invoke-virtual v7, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00a06 move-result-object v7
00a08 move-object/from16 v25, v6
00a0c move-object/from16 v26, v9
00a10 const/4 v6, 2
00a12 const/4 v9, 0
00a14 invoke-static v5, v7, v9, v6, v9, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00a1a iget-object v5, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00a1e move-object v6, v2
00a20 check-cast v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00a24 invoke-virtual v6, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00a2a move-result-object v6
00a2c invoke-interface v6, Ljava/util/Set;->size()I
00a32 move-result v6
00a34 new-instance v7, Ljava/lang/StringBuilder;
00a38 invoke-direct v7, Ljava/lang/StringBuilder;-><init>()V
00a3e invoke-virtual v7, v8, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00a44 invoke-virtual v7, v6, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00a4a invoke-virtual v7, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00a50 move-result-object v6
00a52 move-object/from16 v7, v23
00a56 invoke-virtual v5, v3, v7, v6, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00a5c move-object/from16 v9, v26
00a60 check-cast v9, Ljava/util/Collection;
00a64 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00a68 invoke-virtual v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00a6e move-result-object v2
00a70 check-cast v2, Ljava/lang/Iterable;
00a74 invoke-static v4, v2, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00a7a move-result-object v2
00a7c check-cast v2, Ljava/lang/Iterable;
00a80 invoke-static v9, v2, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00a86 goto +1ch
00a88 move-object/from16 v31, v8
00a8c move-object/from16 v7, v23
00a90 move-object/from16 v8, v26
00a94 move-object/from16 v26, v9
00a98 instance-of v4, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00a9c if-eqz v4, +008h
00aa0 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00aa4 invoke-direct v1, v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV1ServerError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;)V
00aaa goto +ah
00aac instance-of v4, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00ab0 if-eqz v4, +020h
00ab4 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00ab8 invoke-direct v1, v3, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV1UnknownError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;)V
00abe move-object/from16 v2, v35
00ac2 move-object/from16 v23, v7
00ac6 move-object v9, v11
00ac8 move-object/from16 v35, v24
00acc move-object/from16 v7, v26
00ad0 move/from16 v4, v27
00ad4 move-object/from16 v5, v28
00ad8 move-object/from16 v6, v31
00adc move-object v11, v10
00ade move-object v10, v8
00ae0 move/from16 v8, v21
00ae4 move-object/from16 v21, v14
00ae8 move-object/from16 v14, v25
00aec goto/16 -192h
00af0 new-instance v0, Lkotlin/NoWhenBranchMatchedException;
00af4 invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
00afa throw v0
00afc move-object v10, v14
00afe move-object/from16 v4, v23
00b02 move-object v14, v2
00b04 check-cast v14, Ljava/util/Collection;
00b08 invoke-virtual v11, v14, Lcom/vk/pushme/logic/SubscriptionBatcher;->batch(Ljava/util/Collection;)Ljava/util/Collection;
00b0e move-result-object v14
00b10 check-cast v14, Ljava/lang/Iterable;
00b14 invoke-interface v14, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00b1a move-result-object v17
00b1c move-object/from16 v35, v2
00b20 move-object v2, v15
00b22 move-object/from16 v15, v17
00b26 move-object/from16 v17, v11
00b2a move-object v11, v7
00b2c move-object v7, v5
00b2e move-object v5, v3
00b30 move-object v3, v9
00b32 const/4 v9, 0
00b34 invoke-interface v15, Ljava/util/Iterator;->hasNext()Z
00b3a move-result v23
00b3c if-eqz v23, +199h
00b40 invoke-interface v15, Ljava/util/Iterator;->next()Ljava/lang/Object;
00b46 move-result-object v23
00b48 move-object/from16 v24, v5
00b4c move-object/from16 v5, v23
00b50 check-cast v5, Ljava/util/Collection;
00b54 move-object/from16 v25, v14
00b58 move-object v14, v5
00b5a check-cast v14, Ljava/lang/Iterable;
00b5e move-object/from16 v26, v4
00b62 new-instance v4, Ljava/util/ArrayList;
00b66 move-object/from16 v27, v10
00b6a const/16 v10, 10
00b6e invoke-static v14, v10, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable; I)I
00b74 move-result v1
00b76 invoke-direct v4, v1, Ljava/util/ArrayList;-><init>(I)V
00b7c invoke-interface v14, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;
00b82 move-result-object v1
00b84 invoke-interface v1, Ljava/util/Iterator;->hasNext()Z
00b8a move-result v14
00b8c if-eqz v14, +015h
00b90 invoke-interface v1, Ljava/util/Iterator;->next()Ljava/lang/Object;
00b96 move-result-object v14
00b98 check-cast v14, Lcom/vk/pushme/network/model/request/SubscriptionRequest;
00b9c invoke-virtual v14, Lcom/vk/pushme/network/model/request/SubscriptionRequest;->getAccount()Ljava/lang/String;
00ba2 move-result-object v14
00ba4 invoke-interface v4, v14, Ljava/util/Collection;->add(Ljava/lang/Object;)Z
00baa goto -13h
00bac move-exception v0
00bae move-object/from16 v1, v33
00bb2 goto/16 +1d9h
00bb6 invoke-static v4, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;
00bbc move-result-object v4
00bbe invoke-static v0, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bc4 move-result-object v1
00bc6 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$0 Ljava/lang/Object;
00bca iput-object v2, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$1 Ljava/lang/Object;
00bce invoke-static/range v18, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bd4 move-result-object v1
00bd6 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$2 Ljava/lang/Object;
00bda invoke-static/range v20, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00be0 move-result-object v1
00be2 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$3 Ljava/lang/Object;
00be6 invoke-static/range v19, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bec move-result-object v1
00bee iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$4 Ljava/lang/Object;
00bf2 invoke-static/range v30, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00bf8 move-result-object v1
00bfa iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$5 Ljava/lang/Object;
00bfe invoke-static/range v21, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c04 move-result-object v1
00c06 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$6 Ljava/lang/Object;
00c0a invoke-static/range v35, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c10 move-result-object v1
00c12 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$7 Ljava/lang/Object;
00c16 invoke-static/range v24, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c1c move-result-object v1
00c1e iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$8 Ljava/lang/Object;
00c22 iput-object v6, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$9 Ljava/lang/Object;
00c26 invoke-static/range v17, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c2c move-result-object v1
00c2e iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$10 Ljava/lang/Object;
00c32 iput-object v13, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$11 Ljava/lang/Object;
00c36 iput-object v11, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$12 Ljava/lang/Object;
00c3a iput-object v7, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$13 Ljava/lang/Object;
00c3e invoke-static/range v25, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c44 move-result-object v1
00c46 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$14 Ljava/lang/Object;
00c4a iput-object v15, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$15 Ljava/lang/Object;
00c4e invoke-static/range v23, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c54 move-result-object v1
00c56 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$16 Ljava/lang/Object;
00c5a invoke-static v5, Lkotlin/coroutines/jvm/internal/SpillingKt;->nullOutSpilledVariable(Ljava/lang/Object;)Ljava/lang/Object;
00c60 move-result-object v1
00c62 iput-object v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$17 Ljava/lang/Object;
00c66 iput-object v4, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->L$18 Ljava/lang/Object;
00c6a iput-boolean v8, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->Z$0 Z
00c6e iput v9, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$0 I
00c72 const/4 v1, 0
00c74 iput v1, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->I$1 I
00c78 const/4 v14, 4
00c7a iput v14, v3, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$invoke$1;->label I
00c7e invoke-interface v7, v5, v3, Lcom/vk/pushme/network/PushMeApi;->setSettingsV2(Ljava/util/Collection; Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
00c84 move-result-object v5
00c86 if-ne v5, v12, +003h
00c8a return-object v12
00c8c move-object/from16 v16, v15
00c90 move-object v15, v13
00c92 move-object v13, v11
00c94 move-object v11, v3
00c96 move-object v3, v2
00c98 move-object v2, v5
00c9a move-object/from16 v5, v24
00c9e move-object/from16 v24, v20
00ca2 move-object/from16 v20, v35
00ca6 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult;
00caa sget-object v1, Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;->INSTANCE Lcom/vk/pushme/network/model/result/SubscriptionResult$OK;
00cae invoke-static v2, v1, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object; Ljava/lang/Object;)Z
00cb4 move-result v1
00cb6 if-eqz v1, +02eh
00cba move-object/from16 v1, v33
00cbe iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00cc2 const-string v10, "Api result V2: OK"
00cc6 move-object/from16 v35, v3
00cca const/4 v3, 0
00ccc const/4 v14, 2
00cce invoke-static v2, v10, v3, v14, v3, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00cd4 move-object v2, v13
00cd6 check-cast v2, Ljava/util/Collection;
00cda check-cast v4, Ljava/lang/Iterable;
00cde invoke-static v2, v4, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00ce4 move-object/from16 v28, v5
00ce8 move-object/from16 v5, v22
00cec move-object/from16 v10, v27
00cf0 move-object/from16 v22, v7
00cf4 move/from16 v27, v8
00cf8 move-object/from16 v8, v26
00cfc goto/16 +096h
00d00 move-exception v0
00d02 move-object/from16 v2, v35
00d06 goto/16 +12fh
00d0a move-exception v0
00d0c move-object/from16 v35, v3
00d10 goto -7h
00d12 move-object/from16 v1, v33
00d16 move-object/from16 v35, v3
00d1a instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d1e if-eqz v3, +066h
00d22 iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00d26 move-object v10, v2
00d28 check-cast v10, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d2c invoke-virtual v10, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00d32 move-result-object v10
00d34 invoke-interface v10, Ljava/util/Set;->size()I
00d3a move-result v10
00d3c new-instance v14, Ljava/lang/StringBuilder;
00d40 invoke-direct v14, Ljava/lang/StringBuilder;-><init>()V
00d46 move-object/from16 v28, v5
00d4a move-object/from16 v5, v22
00d4e invoke-virtual v14, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00d54 invoke-virtual v14, v10, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00d5a move-object/from16 v10, v27
00d5e invoke-virtual v14, v10, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00d64 invoke-virtual v14, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00d6a move-result-object v14
00d6c move-object/from16 v22, v7
00d70 move/from16 v27, v8
00d74 const/4 v7, 2
00d76 const/4 v8, 0
00d78 invoke-static v3, v14, v8, v7, v8, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00d7e iget-object v3, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->analyticsHandler Lcom/vk/pushme/analytcis/AnalyticsHandler;
00d82 move-object v7, v2
00d84 check-cast v7, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00d88 invoke-virtual v7, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00d8e move-result-object v7
00d90 invoke-interface v7, Ljava/util/Set;->size()I
00d96 move-result v7
00d98 new-instance v8, Ljava/lang/StringBuilder;
00d9c invoke-direct v8, Ljava/lang/StringBuilder;-><init>()V
00da2 invoke-virtual v8, v5, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00da8 invoke-virtual v8, v7, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00dae invoke-virtual v8, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00db4 move-result-object v7
00db6 move-object/from16 v8, v26
00dba invoke-virtual v3, v6, v8, v7, Lcom/vk/pushme/analytcis/AnalyticsHandler;->subscriptionError(Ljava/lang/String; Ljava/lang/String; Ljava/lang/String;)V
00dc0 move-object v3, v13
00dc2 check-cast v3, Ljava/util/Collection;
00dc6 check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;
00dca invoke-virtual v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$HasInvalidAccounts;->getInvalidAccounts()Ljava/util/Set;
00dd0 move-result-object v2
00dd2 check-cast v2, Ljava/lang/Iterable;
00dd6 invoke-static v4, v2, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00ddc move-result-object v2
00dde check-cast v2, Ljava/lang/Iterable;
00de2 invoke-static v3, v2, Lkotlin/collections/CollectionsKt;->addAll(Ljava/util/Collection; Ljava/lang/Iterable;)Z
00de8 goto +20h
00dea move-object/from16 v28, v5
00dee move-object/from16 v5, v22
00df2 move-object/from16 v10, v27
00df6 move-object/from16 v22, v7
00dfa move/from16 v27, v8
00dfe move-object/from16 v8, v26
00e02 instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00e06 if-eqz v3, +008h
00e0a check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;
00e0e invoke-direct v1, v6, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV2ServerError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;)V
00e14 goto +ah
00e16 instance-of v3, v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00e1a if-eqz v3, +01fh
00e1e check-cast v2, Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;
00e22 invoke-direct v1, v6, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleV2UnknownError(Ljava/lang/String; Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;)V
00e28 move-object/from16 v2, v35
00e2c move-object v4, v8
00e2e move-object v3, v11
00e30 move-object v11, v13
00e32 move-object v13, v15
00e34 move-object/from16 v15, v16
00e38 move-object/from16 v35, v20
00e3c move-object/from16 v7, v22
00e40 move-object/from16 v20, v24
00e44 move-object/from16 v14, v25
00e48 move/from16 v8, v27
00e4c move-object/from16 v22, v5
00e50 move-object/from16 v5, v28
00e54 goto/16 -190h
00e58 new-instance v0, Lkotlin/NoWhenBranchMatchedException;
00e5c invoke-direct v0, Lkotlin/NoWhenBranchMatchedException;-><init>()V
00e62 throw v0
00e64 move-exception v0
00e66 move-object/from16 v1, v33
00e6a goto/16 -0afh
00e6e invoke-interface v13, Ljava/util/Set;->size()I
00e74 move-result v0
00e76 invoke-interface v11, Ljava/util/Set;->size()I
00e7c move-result v3
00e7e if-ne v0, v3, +00eh
00e82 invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleAllAccountsSubscribed(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00e88 move-result-object v0
00e8a iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00e8e const/4 v4, 1
00e90 const/4 v5, 0
00e92 invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00e98 return-object v0
00e9a invoke-interface v11, Ljava/util/Set;->isEmpty()Z
00ea0 move-result v0
00ea2 if-eqz v0, +00eh
00ea6 invoke-direct v1, v2, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoAccountsSubscribed(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00eac move-result-object v0
00eae iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00eb2 const/4 v4, 1
00eb4 const/4 v5, 0
00eb6 invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00ebc return-object v0
00ebe iget-object v0, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->logger Lcom/vk/pushme/common/Logger;
00ec2 invoke-interface v11, Ljava/util/Set;->size()I
00ec8 move-result v3
00eca invoke-interface v13, Ljava/util/Set;->size()I
00ed0 move-result v4
00ed2 new-instance v5, Ljava/lang/StringBuilder;
00ed6 invoke-direct v5, Ljava/lang/StringBuilder;-><init>()V
00edc invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00ee2 const-string v3, " of "
00ee6 invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00eec invoke-virtual v5, v4, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;
00ef2 const-string v3, " accounts are successful"
00ef6 invoke-virtual v5, v3, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;
00efc invoke-virtual v5, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
00f02 move-result-object v3
00f04 const/4 v5, 0
00f06 const/4 v6, 2
00f08 invoke-static v0, v3, v5, v6, v5, Lcom/vk/pushme/common/Logger;->info$default(Lcom/vk/pushme/common/Logger; Ljava/lang/String; Ljava/lang/Throwable; I Ljava/lang/Object;)V
00f0e new-instance v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;
00f12 move-object v3, v11
00f14 check-cast v3, Ljava/lang/Iterable;
00f18 invoke-static v13, v3, Lkotlin/collections/SetsKt;->minus(Ljava/util/Set; Ljava/lang/Iterable;)Ljava/util/Set;
00f1e move-result-object v3
00f20 invoke-direct v0, v3, v11, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;-><init>(Ljava/util/Set; Ljava/util/Set;)V
00f26 iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f2a const/4 v4, 1
00f2c invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f32 return-object v0
00f34 invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoAndroidId(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f3a move-result-object v0
00f3c iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f40 const/4 v4, 1
00f42 const/4 v5, 0
00f44 invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f4a return-object v0
00f4c invoke-direct v1, v15, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleNoDeviceId(Ljava/lang/String;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f52 move-result-object v0
00f54 iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f58 const/4 v4, 1
00f5a const/4 v5, 0
00f5c invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f62 return-object v0
00f64 invoke-direct v1, v2, v0, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->handleCouldNotCreateSubscription(Ljava/lang/String; Ljava/lang/Exception;)Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;
00f6a move-result-object v0
00f6c iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f70 const/4 v4, 1
00f72 const/4 v5, 0
00f74 invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f7a return-object v0
00f7c iget-object v2, v1, Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;->mutex Lkotlinx/coroutines/sync/Mutex;
00f80 invoke-static v2, v5, v4, v5, Lkotlinx/coroutines/sync/Mutex$DefaultImpls;->unlock$default(Lkotlinx/coroutines/sync/Mutex; Ljava/lang/Object; I Ljava/lang/Object;)V
00f86 throw v0
00f88 new-instance v0, Ljava/lang/IllegalArgumentException;
00f8c const-string v2, "Failed requirement."
00f90 invoke-direct v0, v2, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V
00f96 throw v0
~~~
